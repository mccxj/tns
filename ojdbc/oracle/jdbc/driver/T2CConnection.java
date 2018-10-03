/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.Writer;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.nio.CharBuffer;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.sql.Connection;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Properties;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.TimeZone;
/*      */ import oracle.jdbc.OracleOCIFailover;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.internal.OracleConnection.InstanceProperty;
/*      */ import oracle.jdbc.internal.OracleStatement.SqlKind;
/*      */ import oracle.jdbc.oracore.OracleTypeADT;
/*      */ import oracle.jdbc.oracore.OracleTypeCLOB;
/*      */ import oracle.jdbc.pool.OracleOCIConnectionPool;
/*      */ import oracle.jdbc.pool.OraclePooledConnection;
/*      */ import oracle.sql.BFILE;
/*      */ import oracle.sql.BLOB;
/*      */ import oracle.sql.BfileDBAccess;
/*      */ import oracle.sql.BlobDBAccess;
/*      */ import oracle.sql.CLOB;
/*      */ import oracle.sql.ClobDBAccess;
/*      */ import oracle.sql.LobPlsqlUtil;
/*      */ import oracle.sql.NCLOB;
/*      */ import oracle.sql.SQLName;
/*      */ import oracle.sql.ZONEIDMAP;
/*      */ import oracle.sql.converter.CharacterSetMetaData;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class T2CConnection
/*      */   extends PhysicalConnection
/*      */   implements BfileDBAccess, BlobDBAccess, ClobDBAccess
/*      */ {
/*   60 */   static final long JDBC_OCI_LIBRARY_VERSION = Long.parseLong("11.2.0.4.0".replaceAll("\\.", ""));
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   66 */   short[] queryMetaData1 = null;
/*   67 */   byte[] queryMetaData2 = null;
/*   68 */   int queryMetaData1Offset = 0;
/*   69 */   int queryMetaData2Offset = 0;
/*      */   private String password;
/*   71 */   int fatalErrorNumber = 0;
/*   72 */   String fatalErrorMessage = null;
/*      */   
/*      */   static final int QMD_dbtype = 0;
/*      */   
/*      */   static final int QMD_dbsize = 1;
/*      */   
/*      */   static final int QMD_nullok = 2;
/*      */   
/*      */   static final int QMD_precision = 3;
/*      */   
/*      */   static final int QMD_scale = 4;
/*      */   
/*      */   static final int QMD_formOfUse = 5;
/*      */   
/*      */   static final int QMD_columnNameLength = 6;
/*      */   
/*      */   static final int QMD_tdo0 = 7;
/*      */   
/*      */   static final int QMD_tdo1 = 8;
/*      */   
/*      */   static final int QMD_tdo2 = 9;
/*      */   
/*      */   static final int QMD_tdo3 = 10;
/*      */   
/*      */   static final int QMD_charLength = 11;
/*      */   
/*      */   static final int QMD_typeNameLength = 12;
/*      */   
/*      */   static final int T2C_LOCATOR_MAX_LEN = 16;
/*      */   static final int T2C_LINEARIZED_LOCATOR_MAX_LEN = 4000;
/*      */   static final int T2C_LINEARIZED_BFILE_LOCATOR_MAX_LEN = 530;
/*      */   static final int METADATA1_INDICES_PER_COLUMN = 13;
/*      */   protected static final int SIZEOF_QUERYMETADATA2 = 8;
/*      */   static final String defaultDriverNameAttribute = "jdbcoci";
/*  106 */   int queryMetaData1Size = 100;
/*  107 */   int queryMetaData2Size = 800;
/*      */   
/*      */   long m_nativeState;
/*      */   
/*      */   short m_clientCharacterSet;
/*      */   
/*      */   byte byteAlign;
/*      */   
/*      */   private static final int EOJ_SUCCESS = 0;
/*      */   
/*      */   private static final int EOJ_ERROR = -1;
/*      */   
/*      */   private static final int EOJ_WARNING = 1;
/*      */   
/*      */   private static final int EOJ_GET_STORAGE_ERROR = -4;
/*      */   
/*      */   private static final int EOJ_ORA3113_SERVER_NORMAL = -6;
/*      */   
/*      */   private static final String OCILIBRARY = "ocijdbc11";
/*  126 */   private int logon_mode = 0;
/*      */   
/*      */   static final int LOGON_MODE_DEFAULT = 0;
/*      */   
/*      */   static final int LOGON_MODE_SYSDBA = 2;
/*      */   
/*      */   static final int LOGON_MODE_SYSOPER = 4;
/*      */   
/*      */   static final int LOGON_MODE_SYSASM = 32768;
/*      */   
/*      */   static final int LOGON_MODE_SYSBKP = 131072;
/*      */   
/*      */   static final int LOGON_MODE_SYSDGD = 262144;
/*      */   
/*      */   static final int LOGON_MODE_SYSKMT = 524288;
/*      */   
/*      */   static final int LOGON_MODE_CONNECTION_POOL = 5;
/*      */   
/*      */   static final int LOGON_MODE_CONNPOOL_CONNECTION = 6;
/*      */   
/*      */   static final int LOGON_MODE_CONNPOOL_PROXY_CONNECTION = 7;
/*      */   
/*      */   static final int LOGON_MODE_CONNPOOL_ALIASED_CONNECTION = 8;
/*      */   static final int T2C_PROXYTYPE_NONE = 0;
/*      */   static final int T2C_PROXYTYPE_USER_NAME = 1;
/*      */   static final int T2C_PROXYTYPE_DISTINGUISHED_NAME = 2;
/*      */   static final int T2C_PROXYTYPE_CERTIFICATE = 3;
/*      */   static final int T2C_CONNECTION_FLAG_DEFAULT_LOB_PREFETCH = 0;
/*      */   static final int T2C_CONNECTION_FLAG_PRELIM_AUTH = 1;
/*      */   private static boolean isLibraryLoaded;
/*  156 */   OracleOCIFailover appCallback = null;
/*  157 */   Object appCallbackObject = null;
/*      */   
/*      */   private Properties nativeInfo;
/*      */   ByteBuffer nioBufferForLob;
/*      */   
/*      */   protected T2CConnection(String paramString, Properties paramProperties, OracleDriverExtension paramOracleDriverExtension)
/*      */     throws SQLException
/*      */   {
/*  165 */     super(paramString, paramProperties, paramOracleDriverExtension);
/*      */     
/*      */ 
/*  168 */     initialize();
/*      */   }
/*      */   
/*      */ 
/*      */   final void initializePassword(String paramString)
/*      */     throws SQLException
/*      */   {
/*  175 */     this.password = paramString;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void initialize()
/*      */   {
/*  182 */     allocQueryMetaDataBuffers();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void allocQueryMetaDataBuffers()
/*      */   {
/*  214 */     this.queryMetaData1Offset = 0;
/*  215 */     this.queryMetaData1 = new short[this.queryMetaData1Size * 13];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  220 */     this.queryMetaData2Offset = 0;
/*  221 */     this.queryMetaData2 = new byte[this.queryMetaData2Size];
/*      */     
/*  223 */     this.namedTypeAccessorByteLen = 0;
/*  224 */     this.refTypeAccessorByteLen = 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void reallocateQueryMetaData(int paramInt1, int paramInt2)
/*      */   {
/*  231 */     this.queryMetaData1 = null;
/*  232 */     this.queryMetaData2 = null;
/*      */     
/*  234 */     this.queryMetaData1Size = Math.max(paramInt1, this.queryMetaData1Size);
/*  235 */     this.queryMetaData2Size = Math.max(paramInt2, this.queryMetaData2Size);
/*      */     
/*  237 */     allocQueryMetaDataBuffers();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void logon()
/*      */     throws SQLException
/*      */   {
/*  261 */     if (this.database == null)
/*      */     {
/*  263 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 64);
/*  264 */       localSQLException.fillInStackTrace();
/*  265 */       throw localSQLException;
/*      */     }
/*      */     
/*  268 */     if (!isLibraryLoaded) {
/*  269 */       loadNativeLibrary();
/*      */     }
/*      */     
/*      */ 
/*  273 */     if (this.ociConnectionPoolIsPooling)
/*      */     {
/*  275 */       processOCIConnectionPooling();
/*      */     }
/*      */     else
/*      */     {
/*  279 */       long l1 = this.ociSvcCtxHandle;
/*  280 */       long l2 = this.ociEnvHandle;
/*  281 */       long l3 = this.ociErrHandle;
/*      */       
/*      */ 
/*  284 */       if ((l1 != 0L) && (l2 != 0L))
/*      */       {
/*      */ 
/*  287 */         if (this.ociDriverCharset != null) {
/*  288 */           this.m_clientCharacterSet = new Integer(this.ociDriverCharset).shortValue();
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  293 */           localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 89);
/*  294 */           ((SQLException)localObject1).fillInStackTrace();
/*  295 */           throw ((Throwable)localObject1);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  301 */         this.conversion = new DBConversion(this.m_clientCharacterSet, this.m_clientCharacterSet, this.m_clientCharacterSet);
/*      */         
/*      */ 
/*  304 */         localObject1 = new short[5];
/*  305 */         localObject2 = new long[] { this.defaultLobPrefetchSize };
/*      */         
/*  307 */         this.sqlWarning = checkError(t2cUseConnection(this.m_nativeState, l2, l1, l3, (short[])localObject1, (long[])localObject2), this.sqlWarning);
/*      */         
/*      */ 
/*      */ 
/*  311 */         this.conversion = new DBConversion(localObject1[0], this.m_clientCharacterSet, localObject1[1]);
/*  312 */         this.byteAlign = ((byte)(localObject1[2] & 0xFF));
/*  313 */         this.timeZoneVersionNumber = ((localObject1[3] << 16) + (localObject1[4] & 0xFFFF));
/*      */         
/*  315 */         return;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  320 */       if (this.internalLogon == null) {
/*  321 */         this.logon_mode = 0;
/*  322 */       } else if (this.internalLogon.equalsIgnoreCase("SYSDBA")) {
/*  323 */         this.logon_mode = 2;
/*  324 */       } else if (this.internalLogon.equalsIgnoreCase("SYSOPER")) {
/*  325 */         this.logon_mode = 4;
/*  326 */       } else if (this.internalLogon.equalsIgnoreCase("SYSASM")) {
/*  327 */         this.logon_mode = 32768;
/*  328 */       } else if (this.internalLogon.equalsIgnoreCase("SYSBACKUP")) {
/*  329 */         this.logon_mode = 131072;
/*  330 */       } else if (this.internalLogon.equalsIgnoreCase("SYSDG")) {
/*  331 */         this.logon_mode = 262144;
/*  332 */       } else if (this.internalLogon.equalsIgnoreCase("SYSKM")) {
/*  333 */         this.logon_mode = 524288;
/*      */       }
/*  335 */       Object localObject1 = null;
/*  336 */       Object localObject2 = null;
/*  337 */       byte[] arrayOfByte1 = null;
/*  338 */       String str1 = this.setNewPassword;
/*  339 */       byte[] arrayOfByte2 = new byte[0];
/*  340 */       byte[] arrayOfByte3 = new byte[0];
/*  341 */       byte[] arrayOfByte4 = new byte[0];
/*      */       
/*  343 */       if (this.nlsLangBackdoor)
/*      */       {
/*      */ 
/*  346 */         this.m_clientCharacterSet = getDriverCharSetIdFromNLS_LANG();
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  351 */         this.m_clientCharacterSet = getClientCharSetId();
/*      */       }
/*      */       
/*  354 */       if (str1 != null) {
/*  355 */         arrayOfByte2 = DBConversion.stringToDriverCharBytes(str1, this.m_clientCharacterSet);
/*      */       }
/*  357 */       if (this.editionName != null) {
/*  358 */         arrayOfByte3 = DBConversion.stringToDriverCharBytes(this.editionName, this.m_clientCharacterSet);
/*      */       }
/*  360 */       if (this.driverNameAttribute == null) {
/*  361 */         arrayOfByte4 = DBConversion.stringToDriverCharBytes("jdbcoci", this.m_clientCharacterSet);
/*      */       } else {
/*  363 */         arrayOfByte4 = DBConversion.stringToDriverCharBytes(this.driverNameAttribute, this.m_clientCharacterSet);
/*      */       }
/*  365 */       localObject1 = this.userName == null ? new byte[0] : DBConversion.stringToDriverCharBytes(this.userName, this.m_clientCharacterSet);
/*      */       
/*      */ 
/*  368 */       localObject2 = this.proxyClientName == null ? new byte[0] : DBConversion.stringToDriverCharBytes(this.proxyClientName, this.m_clientCharacterSet);
/*      */       
/*      */ 
/*  371 */       arrayOfByte1 = this.password == null ? new byte[0] : DBConversion.stringToDriverCharBytes(this.password, this.m_clientCharacterSet);
/*      */       
/*      */ 
/*  374 */       byte[] arrayOfByte5 = DBConversion.stringToDriverCharBytes(this.database, this.m_clientCharacterSet);
/*      */       
/*  376 */       short[] arrayOfShort = new short[5];
/*  377 */       String str2 = null;
/*  378 */       byte[] arrayOfByte6 = (str2 = CharacterSetMetaData.getNLSLanguage(ClassRef.LOCALE.getDefault())) != null ? str2.getBytes() : null;
/*      */       
/*  380 */       byte[] arrayOfByte7 = (str2 = CharacterSetMetaData.getNLSTerritory(ClassRef.LOCALE.getDefault())) != null ? str2.getBytes() : null;
/*      */       
/*      */ 
/*  383 */       if ((arrayOfByte6 == null) || (arrayOfByte7 == null))
/*      */       {
/*  385 */         localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 176);
/*  386 */         ((SQLException)localObject3).fillInStackTrace();
/*  387 */         throw ((Throwable)localObject3);
/*      */       }
/*      */       
/*  390 */       Object localObject3 = TimeZone.getDefault();
/*  391 */       String str3 = ((TimeZone)localObject3).getID();
/*      */       
/*  393 */       if ((!ZONEIDMAP.isValidRegion(str3)) || (!this.timezoneAsRegion))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  402 */         int i = ((TimeZone)localObject3).getOffset(System.currentTimeMillis());
/*  403 */         int j = i / 3600000;
/*  404 */         int k = i / 60000 % 60;
/*      */         
/*  406 */         str3 = (j < 0 ? "" + j : new StringBuilder().append("+").append(j).toString()) + (k < 10 ? ":0" + k : new StringBuilder().append(":").append(k).toString());
/*      */       }
/*      */       
/*      */ 
/*  410 */       doSetSessionTimeZone(str3);
/*      */       
/*      */ 
/*  413 */       this.sessionTimeZone = str3;
/*      */       
/*      */ 
/*  416 */       this.conversion = new DBConversion(this.m_clientCharacterSet, this.m_clientCharacterSet, this.m_clientCharacterSet);
/*      */       
/*      */ 
/*  419 */       long[] arrayOfLong = { this.defaultLobPrefetchSize, this.prelimAuth ? 1 : 0 };
/*      */       
/*  421 */       if (this.m_nativeState == 0L)
/*      */       {
/*  423 */         this.sqlWarning = checkError(t2cCreateState((byte[])localObject1, localObject1.length, (byte[])localObject2, localObject2.length, arrayOfByte1, arrayOfByte1.length, arrayOfByte2, arrayOfByte2.length, arrayOfByte3, arrayOfByte3.length, arrayOfByte4, arrayOfByte4.length, arrayOfByte5, arrayOfByte5.length, this.m_clientCharacterSet, this.logon_mode, arrayOfShort, arrayOfByte6, arrayOfByte7, this.retainV9BindBehavior, arrayOfLong), this.sqlWarning);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  440 */         this.sqlWarning = checkError(t2cLogon(this.m_nativeState, (byte[])localObject1, localObject1.length, (byte[])localObject2, localObject2.length, arrayOfByte1, arrayOfByte1.length, arrayOfByte2, arrayOfByte2.length, arrayOfByte3, arrayOfByte3.length, arrayOfByte4, arrayOfByte4.length, arrayOfByte5, arrayOfByte5.length, this.logon_mode, arrayOfShort, arrayOfByte6, arrayOfByte7, arrayOfLong), this.sqlWarning);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  454 */       this.conversion = new DBConversion(arrayOfShort[0], this.m_clientCharacterSet, arrayOfShort[1]);
/*  455 */       this.byteAlign = ((byte)(arrayOfShort[2] & 0xFF));
/*  456 */       this.timeZoneVersionNumber = ((arrayOfShort[3] << 16) + (arrayOfShort[4] & 0xFFFF));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void logoff()
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/*  475 */       if (this.lifecycle == 2)
/*      */       {
/*  477 */         checkError(t2cLogoff(this.m_nativeState));
/*      */       }
/*      */     }
/*      */     catch (NullPointerException localNullPointerException) {}
/*      */     
/*  482 */     this.m_nativeState = 0L;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void open(OracleStatement paramOracleStatement)
/*      */     throws SQLException
/*      */   {
/*  500 */     byte[] arrayOfByte = paramOracleStatement.sqlObject.getSql(paramOracleStatement.processEscapes, paramOracleStatement.convertNcharLiterals).getBytes();
/*      */     
/*      */ 
/*  503 */     checkError(t2cCreateStatement(this.m_nativeState, 0L, arrayOfByte, arrayOfByte.length, paramOracleStatement, false, paramOracleStatement.rowPrefetch));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void cancelOperationOnServer(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  519 */     checkError(t2cCancel(this.m_nativeState));
/*      */   }
/*      */   
/*      */ 
/*      */   native int t2cAbort(long paramLong);
/*      */   
/*      */   void doAbort()
/*      */     throws SQLException
/*      */   {
/*  528 */     checkError(t2cAbort(this.m_nativeState));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void doSetAutoCommit(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  545 */     checkError(t2cSetAutoCommit(this.m_nativeState, paramBoolean));
/*  546 */     this.autocommit = paramBoolean;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void doCommit(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  562 */     checkError(t2cCommit(this.m_nativeState, paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void doRollback()
/*      */     throws SQLException
/*      */   {
/*  578 */     checkError(t2cRollback(this.m_nativeState));
/*      */   }
/*      */   
/*      */ 
/*      */   synchronized int doPingDatabase()
/*      */     throws SQLException
/*      */   {
/*  585 */     if (t2cPingDatabase(this.m_nativeState) == 0) {
/*  586 */       return 0;
/*      */     }
/*  588 */     return -1;
/*      */   }
/*      */   
/*      */ 
/*      */   protected String doGetDatabaseProductVersion()
/*      */     throws SQLException
/*      */   {
/*  595 */     byte[] arrayOfByte = t2cGetProductionVersion(this.m_nativeState);
/*      */     
/*  597 */     return this.conversion.CharBytesToString(arrayOfByte, arrayOfByte.length);
/*      */   }
/*      */   
/*      */ 
/*      */   protected short doGetVersionNumber()
/*      */     throws SQLException
/*      */   {
/*  604 */     short s = 0;
/*      */     
/*      */     try
/*      */     {
/*  608 */       String str1 = doGetDatabaseProductVersion();
/*      */       
/*  610 */       StringTokenizer localStringTokenizer = new StringTokenizer(str1.trim(), " .", false);
/*  611 */       String str2 = null;
/*  612 */       int i = 0;
/*  613 */       int j = 0;
/*      */       
/*  615 */       while (localStringTokenizer.hasMoreTokens())
/*      */       {
/*  617 */         str2 = localStringTokenizer.nextToken();
/*      */         
/*      */         try
/*      */         {
/*  621 */           j = Integer.decode(str2).shortValue();
/*  622 */           s = (short)(s * 10 + j);
/*  623 */           i++;
/*      */           
/*      */ 
/*  626 */           if (i == 4) {
/*      */             break;
/*      */           }
/*      */         }
/*      */         catch (NumberFormatException localNumberFormatException) {}
/*      */       }
/*      */     }
/*      */     catch (NoSuchElementException localNoSuchElementException) {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  641 */     if (s == -1) {
/*  642 */       s = 0;
/*      */     }
/*      */     
/*  645 */     return s;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ClobDBAccess createClobDBAccess()
/*      */   {
/*  652 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BlobDBAccess createBlobDBAccess()
/*      */   {
/*  659 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BfileDBAccess createBfileDBAccess()
/*      */   {
/*  666 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */   protected SQLWarning checkError(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  673 */     return checkError(paramInt, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected SQLWarning checkError(int paramInt, SQLWarning paramSQLWarning)
/*      */     throws SQLException
/*      */   {
/*      */     Object localObject1;
/*      */     
/*  683 */     switch (paramInt)
/*      */     {
/*      */     case 0: 
/*      */       break;
/*      */     
/*      */ 
/*      */     case -1: 
/*      */     case 1: 
/*  691 */       localObject1 = new T2CError();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  697 */       int i = -1;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  702 */       if ((this.lifecycle == 1) || (this.lifecycle == 16))
/*      */       {
/*  704 */         i = t2cDescribeError(this.m_nativeState, (T2CError)localObject1, ((T2CError)localObject1).m_errorMessage);
/*      */       }
/*      */       else {
/*  707 */         if (this.fatalErrorNumber != 0)
/*      */         {
/*      */ 
/*  710 */           localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 269);
/*  711 */           ((SQLException)localObject2).fillInStackTrace();
/*  712 */           throw ((Throwable)localObject2);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  717 */         localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  718 */         ((SQLException)localObject2).fillInStackTrace();
/*  719 */         throw ((Throwable)localObject2);
/*      */       }
/*      */       
/*  722 */       Object localObject2 = null;
/*  723 */       if (i != -1)
/*      */       {
/*      */ 
/*      */ 
/*  727 */         int j = 0;
/*      */         
/*  729 */         while ((j < ((T2CError)localObject1).m_errorMessage.length) && (localObject1.m_errorMessage[j] != 0)) {
/*  730 */           j++;
/*      */         }
/*  732 */         if (this.conversion == null) throw new Error("conversion == null");
/*  733 */         if (localObject1 == null) throw new Error("l_error == null");
/*  734 */         localObject2 = this.conversion.CharBytesToString(((T2CError)localObject1).m_errorMessage, j, true);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  741 */       switch (((T2CError)localObject1).m_errorNumber)
/*      */       {
/*      */       case 28: 
/*      */       case 600: 
/*      */       case 1012: 
/*      */       case 1041: 
/*  747 */         internalClose();
/*  748 */         break;
/*      */       
/*      */ 
/*      */       case 902: 
/*  752 */         removeAllDescriptor();
/*  753 */         break;
/*      */       
/*      */       case 3113: 
/*      */       case 3114: 
/*  757 */         setUsable(false);
/*  758 */         close();
/*  759 */         break;
/*      */       case -6: 
/*  761 */         ((T2CError)localObject1).m_errorNumber = 3113;
/*      */       }
/*      */       
/*      */       
/*      */       SQLException localSQLException;
/*  766 */       if (i == -1)
/*      */       {
/*  768 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Fetch error message failed!");
/*  769 */         localSQLException.fillInStackTrace();
/*  770 */         throw localSQLException;
/*      */       }
/*      */       
/*  773 */       if (paramInt == -1)
/*      */       {
/*      */ 
/*  776 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), (String)localObject2, ((T2CError)localObject1).m_errorNumber);
/*  777 */         localSQLException.fillInStackTrace();
/*  778 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  783 */       paramSQLWarning = DatabaseError.addSqlWarning(paramSQLWarning, (String)localObject2, ((T2CError)localObject1).m_errorNumber);
/*      */       
/*      */ 
/*  786 */       break;
/*      */     
/*      */ 
/*      */     case -4: 
/*  790 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 254);
/*  791 */       ((SQLException)localObject1).fillInStackTrace();
/*  792 */       throw ((Throwable)localObject1);
/*      */     }
/*      */     
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  799 */     return paramSQLWarning;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   OracleStatement RefCursorBytesToStatement(byte[] paramArrayOfByte, OracleStatement paramOracleStatement)
/*      */     throws SQLException
/*      */   {
/*  807 */     T2CStatement localT2CStatement = new T2CStatement(this, 1, this.defaultRowPrefetch, -1, -1);
/*      */     
/*  809 */     localT2CStatement.needToParse = false;
/*  810 */     localT2CStatement.serverCursor = true;
/*  811 */     localT2CStatement.isOpen = true;
/*  812 */     localT2CStatement.processEscapes = false;
/*      */     
/*  814 */     localT2CStatement.prepareForNewResults(true, false);
/*  815 */     localT2CStatement.sqlObject.initialize("select unknown as ref cursor from whatever");
/*      */     
/*  817 */     localT2CStatement.sqlKind = OracleStatement.SqlKind.SELECT;
/*      */     
/*  819 */     checkError(t2cCreateStatement(this.m_nativeState, paramOracleStatement.c_state, paramArrayOfByte, paramArrayOfByte.length, localT2CStatement, true, this.defaultRowPrefetch));
/*      */     
/*      */ 
/*      */ 
/*  823 */     paramOracleStatement.addChild(localT2CStatement);
/*  824 */     return localT2CStatement;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void getForm(OracleTypeADT paramOracleTypeADT, OracleTypeCLOB paramOracleTypeCLOB, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  833 */     int i = 0;
/*      */     
/*  835 */     if (paramOracleTypeCLOB != null)
/*      */     {
/*  837 */       String[] arrayOfString1 = new String[1];
/*  838 */       String[] arrayOfString2 = new String[1];
/*      */       
/*  840 */       SQLName.parse(paramOracleTypeADT.getFullName(), arrayOfString1, arrayOfString2, true);
/*      */       
/*  842 */       String str = "\"" + arrayOfString1[0] + "\".\"" + arrayOfString2[0] + "\"";
/*      */       
/*      */ 
/*  845 */       byte[] arrayOfByte = this.conversion.StringToCharBytes(str);
/*      */       
/*  847 */       int j = t2cGetFormOfUse(this.m_nativeState, paramOracleTypeCLOB, arrayOfByte, arrayOfByte.length, paramInt);
/*      */       
/*      */ 
/*      */ 
/*  851 */       if (j < 0) {
/*  852 */         checkError(j);
/*      */       }
/*      */       
/*  855 */       paramOracleTypeCLOB.setForm(j);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getTdoCState(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  870 */     String str = "\"" + paramString1 + "\".\"" + paramString2 + "\"";
/*  871 */     byte[] arrayOfByte = this.conversion.StringToCharBytes(str);
/*  872 */     int[] arrayOfInt = new int[1];
/*  873 */     long l = t2cGetTDO(this.m_nativeState, arrayOfByte, arrayOfByte.length, arrayOfInt);
/*  874 */     if (l == 0L)
/*      */     {
/*  876 */       checkError(arrayOfInt[0]);
/*      */     }
/*  878 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public Properties getDBAccessProperties()
/*      */     throws SQLException
/*      */   {
/*  894 */     return getOCIHandles();
/*      */   }
/*      */   
/*      */   public synchronized Properties getOCIHandles()
/*      */     throws SQLException
/*      */   {
/*      */     Object localObject;
/*  901 */     if (this.lifecycle != 1)
/*      */     {
/*  903 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  904 */       ((SQLException)localObject).fillInStackTrace();
/*  905 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  908 */     if (this.nativeInfo == null)
/*      */     {
/*  910 */       localObject = new long[3];
/*      */       
/*      */ 
/*  913 */       checkError(t2cGetHandles(this.m_nativeState, (long[])localObject));
/*      */       
/*      */ 
/*  916 */       this.nativeInfo = new Properties();
/*      */       
/*  918 */       this.nativeInfo.put("OCIEnvHandle", String.valueOf(localObject[0]));
/*  919 */       this.nativeInfo.put("OCISvcCtxHandle", String.valueOf(localObject[1]));
/*  920 */       this.nativeInfo.put("OCIErrHandle", String.valueOf(localObject[2]));
/*  921 */       this.nativeInfo.put("ClientCharSet", String.valueOf(this.m_clientCharacterSet));
/*      */     }
/*      */     
/*  924 */     return this.nativeInfo;
/*      */   }
/*      */   
/*      */ 
/*      */   public Properties getServerSessionInfo()
/*      */     throws SQLException
/*      */   {
/*  931 */     if (this.lifecycle != 1)
/*      */     {
/*  933 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  934 */       localSQLException.fillInStackTrace();
/*  935 */       throw localSQLException;
/*      */     }
/*      */     
/*  938 */     if (this.sessionProperties == null) {
/*  939 */       this.sessionProperties = new Properties();
/*      */     }
/*      */     
/*      */ 
/*  943 */     if (getVersionNumber() < 10200) {
/*  944 */       queryFCFProperties(this.sessionProperties);
/*      */     } else {
/*  946 */       checkError(t2cGetServerSessionInfo(this.m_nativeState, this.sessionProperties));
/*      */     }
/*  948 */     return this.sessionProperties;
/*      */   }
/*      */   
/*      */   public byte getInstanceProperty(OracleConnection.InstanceProperty paramInstanceProperty)
/*      */     throws SQLException
/*      */   {
/*  954 */     byte b = 0;
/*  955 */     if (paramInstanceProperty == OracleConnection.InstanceProperty.ASM_VOLUME_SUPPORTED)
/*      */     {
/*  957 */       b = t2cGetAsmVolProperty(this.m_nativeState);
/*      */     }
/*  959 */     else if (paramInstanceProperty == OracleConnection.InstanceProperty.INSTANCE_TYPE)
/*      */     {
/*  961 */       b = t2cGetInstanceType(this.m_nativeState);
/*      */     }
/*  963 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Properties getConnectionPoolInfo()
/*      */     throws SQLException
/*      */   {
/*  973 */     if (this.lifecycle != 1)
/*      */     {
/*  975 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  976 */       ((SQLException)localObject).fillInStackTrace();
/*  977 */       throw ((Throwable)localObject);
/*      */     }
/*  979 */     Object localObject = new Properties();
/*      */     
/*  981 */     checkError(t2cGetConnPoolInfo(this.m_nativeState, (Properties)localObject));
/*      */     
/*  983 */     return (Properties)localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setConnectionPoolInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */     throws SQLException
/*      */   {
/*  993 */     checkError(t2cSetConnPoolInfo(this.m_nativeState, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void ociPasswordChange(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 1003 */     if (this.lifecycle != 1)
/*      */     {
/* 1005 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 1006 */       ((SQLException)localObject).fillInStackTrace();
/* 1007 */       throw ((Throwable)localObject);
/*      */     }
/* 1009 */     Object localObject = paramString1 == null ? new byte[0] : DBConversion.stringToDriverCharBytes(paramString1, this.m_clientCharacterSet);
/*      */     
/*      */ 
/*      */ 
/* 1013 */     byte[] arrayOfByte1 = paramString2 == null ? new byte[0] : DBConversion.stringToDriverCharBytes(paramString2, this.m_clientCharacterSet);
/*      */     
/*      */ 
/* 1016 */     byte[] arrayOfByte2 = paramString3 == null ? new byte[0] : DBConversion.stringToDriverCharBytes(paramString3, this.m_clientCharacterSet);
/*      */     
/*      */ 
/* 1019 */     this.sqlWarning = checkError(t2cPasswordChange(this.m_nativeState, (byte[])localObject, localObject.length, arrayOfByte1, arrayOfByte1.length, arrayOfByte2, arrayOfByte2.length), this.sqlWarning);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void processOCIConnectionPooling()
/*      */     throws SQLException
/*      */   {
/* 1030 */     if (this.lifecycle != 1)
/*      */     {
/* 1032 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 1033 */       ((SQLException)localObject1).fillInStackTrace();
/* 1034 */       throw ((Throwable)localObject1);
/*      */     }
/*      */     
/*      */ 
/* 1038 */     Object localObject1 = null;
/*      */     
/* 1040 */     if (this.ociConnectionPoolLogonMode == "connection_pool")
/*      */     {
/* 1042 */       if (this.nlsLangBackdoor)
/*      */       {
/*      */ 
/* 1045 */         this.m_clientCharacterSet = getDriverCharSetIdFromNLS_LANG();
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1050 */         this.m_clientCharacterSet = getClientCharSetId();
/*      */       }
/*      */     }
/*      */     else {
/* 1054 */       localObject1 = (T2CConnection)this.ociConnectionPoolObject;
/* 1055 */       this.m_clientCharacterSet = ((T2CConnection)localObject1).m_clientCharacterSet;
/*      */     }
/*      */     
/* 1058 */     byte[] arrayOfByte1 = null;
/*      */     
/* 1060 */     byte[] arrayOfByte2 = this.password == null ? new byte[0] : DBConversion.stringToDriverCharBytes(this.password, this.m_clientCharacterSet);
/*      */     
/*      */ 
/* 1063 */     byte[] arrayOfByte3 = this.editionName == null ? new byte[0] : DBConversion.stringToDriverCharBytes(this.editionName, this.m_clientCharacterSet);
/*      */     
/*      */ 
/* 1066 */     byte[] arrayOfByte4 = DBConversion.stringToDriverCharBytes(this.driverNameAttribute == null ? "jdbcoci" : this.driverNameAttribute, this.m_clientCharacterSet);
/*      */     
/*      */ 
/*      */ 
/* 1070 */     byte[] arrayOfByte5 = DBConversion.stringToDriverCharBytes(this.database, this.m_clientCharacterSet);
/*      */     
/* 1072 */     byte[] arrayOfByte6 = CharacterSetMetaData.getNLSLanguage(ClassRef.LOCALE.getDefault()).getBytes();
/*      */     
/* 1074 */     byte[] arrayOfByte7 = CharacterSetMetaData.getNLSTerritory(ClassRef.LOCALE.getDefault()).getBytes();
/*      */     
/*      */ 
/*      */ 
/* 1078 */     if ((arrayOfByte6 == null) || (arrayOfByte7 == null))
/*      */     {
/* 1080 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 176);
/* 1081 */       ((SQLException)localObject2).fillInStackTrace();
/* 1082 */       throw ((Throwable)localObject2);
/*      */     }
/*      */     
/* 1085 */     Object localObject2 = new short[5];
/* 1086 */     long[] arrayOfLong = { this.defaultLobPrefetchSize };
/*      */     Object localObject3;
/* 1088 */     if (this.ociConnectionPoolLogonMode == "connection_pool")
/*      */     {
/* 1090 */       arrayOfByte1 = this.userName == null ? new byte[0] : DBConversion.stringToDriverCharBytes(this.userName, this.m_clientCharacterSet);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1096 */       this.conversion = new DBConversion(this.m_clientCharacterSet, this.m_clientCharacterSet, this.m_clientCharacterSet);
/*      */       
/*      */ 
/* 1099 */       this.logon_mode = 5;
/*      */       
/* 1101 */       if (this.lifecycle == 1)
/*      */       {
/* 1103 */         localObject3 = new int[6];
/*      */         
/* 1105 */         OracleOCIConnectionPool.readPoolConfig(this.ociConnectionPoolMinLimit, this.ociConnectionPoolMaxLimit, this.ociConnectionPoolIncrement, this.ociConnectionPoolTimeout, this.ociConnectionPoolNoWait, this.ociConnectionPoolTransactionDistributed, (int[])localObject3);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1113 */         this.sqlWarning = checkError(t2cCreateConnPool(arrayOfByte1, arrayOfByte1.length, arrayOfByte2, arrayOfByte2.length, arrayOfByte5, arrayOfByte5.length, this.m_clientCharacterSet, this.logon_mode, localObject3[0], localObject3[1], localObject3[2], localObject3[3], localObject3[4], localObject3[5]), this.sqlWarning);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1124 */         this.versionNumber = 10000;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 1130 */         localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 0, "Internal Error: ");
/* 1131 */         ((SQLException)localObject3).fillInStackTrace();
/* 1132 */         throw ((Throwable)localObject3);
/*      */       }
/*      */       
/*      */ 
/*      */     }
/* 1137 */     else if (this.ociConnectionPoolLogonMode == "connpool_connection")
/*      */     {
/* 1139 */       this.logon_mode = 6;
/*      */       
/* 1141 */       arrayOfByte1 = this.userName == null ? new byte[0] : DBConversion.stringToDriverCharBytes(this.userName, this.m_clientCharacterSet);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1147 */       this.conversion = new DBConversion(this.m_clientCharacterSet, this.m_clientCharacterSet, this.m_clientCharacterSet);
/*      */       
/*      */ 
/* 1150 */       this.sqlWarning = checkError(t2cConnPoolLogon(((T2CConnection)localObject1).m_nativeState, arrayOfByte1, arrayOfByte1.length, arrayOfByte2, arrayOfByte2.length, arrayOfByte3, arrayOfByte3.length, arrayOfByte4, arrayOfByte4.length, arrayOfByte5, arrayOfByte5.length, this.logon_mode, 0, 0, null, null, 0, null, 0, null, 0, null, 0, null, 0, (short[])localObject2, arrayOfByte6, arrayOfByte7, arrayOfLong), this.sqlWarning);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/* 1183 */     else if (this.ociConnectionPoolLogonMode == "connpool_alias_connection")
/*      */     {
/* 1185 */       this.logon_mode = 8;
/*      */       
/*      */ 
/* 1188 */       localObject3 = null;
/*      */       
/* 1190 */       localObject3 = (byte[])this.ociConnectionPoolConnID;
/*      */       
/*      */ 
/* 1193 */       arrayOfByte1 = this.userName == null ? new byte[0] : DBConversion.stringToDriverCharBytes(this.userName, this.m_clientCharacterSet);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1199 */       this.conversion = new DBConversion(this.m_clientCharacterSet, this.m_clientCharacterSet, this.m_clientCharacterSet);
/*      */       
/*      */ 
/* 1202 */       this.sqlWarning = checkError(t2cConnPoolLogon(((T2CConnection)localObject1).m_nativeState, arrayOfByte1, arrayOfByte1.length, arrayOfByte2, arrayOfByte2.length, arrayOfByte3, arrayOfByte3.length, arrayOfByte4, arrayOfByte4.length, arrayOfByte5, arrayOfByte5.length, this.logon_mode, 0, 0, null, null, 0, null, 0, null, 0, null, 0, (byte[])localObject3, localObject3 == null ? 0 : localObject3.length, (short[])localObject2, arrayOfByte6, arrayOfByte7, arrayOfLong), this.sqlWarning);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/* 1235 */     else if (this.ociConnectionPoolLogonMode == "connpool_proxy_connection")
/*      */     {
/* 1237 */       this.logon_mode = 7;
/*      */       
/*      */ 
/* 1240 */       localObject3 = this.ociConnectionPoolProxyType;
/*      */       
/*      */ 
/* 1243 */       int i = this.ociConnectionPoolProxyNumRoles.intValue();
/*      */       
/* 1245 */       String[] arrayOfString = null;
/*      */       
/* 1247 */       if (i > 0)
/*      */       {
/* 1249 */         arrayOfString = (String[])this.ociConnectionPoolProxyRoles;
/*      */       }
/*      */       
/*      */ 
/* 1253 */       byte[] arrayOfByte8 = null;
/* 1254 */       byte[] arrayOfByte9 = null;
/* 1255 */       byte[] arrayOfByte10 = null;
/* 1256 */       byte[] arrayOfByte11 = null;
/*      */       
/*      */ 
/* 1259 */       int j = 0;
/*      */       
/*      */       String str;
/* 1262 */       if (localObject3 == "proxytype_user_name")
/*      */       {
/* 1264 */         j = 1;
/*      */         
/* 1266 */         str = this.ociConnectionPoolProxyUserName;
/*      */         
/* 1268 */         if (str != null) {
/* 1269 */           arrayOfByte8 = str.getBytes();
/*      */         }
/* 1271 */         str = this.ociConnectionPoolProxyPassword;
/*      */         
/* 1273 */         if (str != null) {
/* 1274 */           arrayOfByte9 = str.getBytes();
/*      */         }
/* 1276 */       } else if (localObject3 == "proxytype_distinguished_name")
/*      */       {
/*      */ 
/* 1279 */         j = 2;
/*      */         
/* 1281 */         str = this.ociConnectionPoolProxyDistinguishedName;
/*      */         
/* 1283 */         if (str != null) {
/* 1284 */           arrayOfByte10 = str.getBytes();
/*      */         }
/* 1286 */       } else if (localObject3 == "proxytype_certificate")
/*      */       {
/* 1288 */         j = 3;
/*      */         
/* 1290 */         arrayOfByte11 = (byte[])this.ociConnectionPoolProxyCertificate;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 1296 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 107);
/* 1297 */         localSQLException.fillInStackTrace();
/* 1298 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1303 */       arrayOfByte1 = this.userName == null ? new byte[0] : DBConversion.stringToDriverCharBytes(this.userName, this.m_clientCharacterSet);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1309 */       this.conversion = new DBConversion(this.m_clientCharacterSet, this.m_clientCharacterSet, this.m_clientCharacterSet);
/*      */       
/*      */ 
/* 1312 */       this.sqlWarning = checkError(t2cConnPoolLogon(((T2CConnection)localObject1).m_nativeState, arrayOfByte1, arrayOfByte1.length, arrayOfByte2, arrayOfByte2.length, arrayOfByte3, arrayOfByte3.length, arrayOfByte4, arrayOfByte4.length, arrayOfByte5, arrayOfByte5.length, this.logon_mode, j, i, arrayOfString, arrayOfByte8, arrayOfByte8 == null ? 0 : arrayOfByte8.length, arrayOfByte9, arrayOfByte9 == null ? 0 : arrayOfByte9.length, arrayOfByte10, arrayOfByte10 == null ? 0 : arrayOfByte10.length, arrayOfByte11, arrayOfByte11 == null ? 0 : arrayOfByte11.length, null, 0, (short[])localObject2, arrayOfByte6, arrayOfByte7, arrayOfLong), this.sqlWarning);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1339 */       localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23, "connection-pool-logon");
/* 1340 */       ((SQLException)localObject3).fillInStackTrace();
/* 1341 */       throw ((Throwable)localObject3);
/*      */     }
/*      */     
/*      */ 
/* 1345 */     this.conversion = new DBConversion(localObject2[0], this.m_clientCharacterSet, localObject2[1]);
/* 1346 */     this.byteAlign = ((byte)(localObject2[2] & 0xFF));
/* 1347 */     this.timeZoneVersionNumber = ((localObject2[3] << 16) + (localObject2[4] & 0xFFFF));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDescriptorSharable(OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/* 1363 */     T2CConnection localT2CConnection = this;
/* 1364 */     PhysicalConnection localPhysicalConnection = (PhysicalConnection)paramOracleConnection.getPhysicalConnection();
/*      */     
/* 1366 */     return localT2CConnection == localPhysicalConnection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cBlobRead(long paramLong1, byte[] paramArrayOfByte1, int paramInt1, long paramLong2, int paramInt2, byte[] paramArrayOfByte2, int paramInt3, boolean paramBoolean, ByteBuffer paramByteBuffer);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cClobRead(long paramLong1, byte[] paramArrayOfByte, int paramInt1, long paramLong2, int paramInt2, char[] paramArrayOfChar, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, ByteBuffer paramByteBuffer);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cBlobWrite(long paramLong1, byte[] paramArrayOfByte1, int paramInt1, long paramLong2, int paramInt2, byte[] paramArrayOfByte2, int paramInt3, byte[][] paramArrayOfByte);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cClobWrite(long paramLong1, byte[] paramArrayOfByte, int paramInt1, long paramLong2, int paramInt2, char[] paramArrayOfChar, int paramInt3, byte[][] paramArrayOfByte1, boolean paramBoolean);
/*      */   
/*      */ 
/*      */ 
/*      */   native long t2cLobGetLength(long paramLong, byte[] paramArrayOfByte, int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cBfileOpen(long paramLong, byte[] paramArrayOfByte, int paramInt, byte[][] paramArrayOfByte1);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cBfileIsOpen(long paramLong, byte[] paramArrayOfByte, int paramInt, boolean[] paramArrayOfBoolean);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cBfileExists(long paramLong, byte[] paramArrayOfByte, int paramInt, boolean[] paramArrayOfBoolean);
/*      */   
/*      */ 
/*      */ 
/*      */   native String t2cBfileGetName(long paramLong, byte[] paramArrayOfByte, int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */   native String t2cBfileGetDirAlias(long paramLong, byte[] paramArrayOfByte, int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cBfileClose(long paramLong, byte[] paramArrayOfByte, int paramInt, byte[][] paramArrayOfByte1);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cLobGetChunkSize(long paramLong, byte[] paramArrayOfByte, int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cLobTrim(long paramLong1, int paramInt1, long paramLong2, byte[] paramArrayOfByte, int paramInt2, byte[][] paramArrayOfByte1);
/*      */   
/*      */ 
/*      */   native int t2cLobCreateTemporary(long paramLong, int paramInt1, boolean paramBoolean, int paramInt2, short paramShort, byte[][] paramArrayOfByte);
/*      */   
/*      */ 
/*      */   native int t2cLobFreeTemporary(long paramLong, int paramInt1, byte[] paramArrayOfByte, int paramInt2, byte[][] paramArrayOfByte1);
/*      */   
/*      */ 
/*      */   native int t2cLobIsTemporary(long paramLong, int paramInt1, byte[] paramArrayOfByte, int paramInt2, boolean[] paramArrayOfBoolean);
/*      */   
/*      */ 
/*      */   native int t2cLobOpen(long paramLong, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3, byte[][] paramArrayOfByte1);
/*      */   
/*      */ 
/*      */   native int t2cLobIsOpen(long paramLong, int paramInt1, byte[] paramArrayOfByte, int paramInt2, boolean[] paramArrayOfBoolean);
/*      */   
/*      */ 
/*      */   native int t2cLobClose(long paramLong, int paramInt1, byte[] paramArrayOfByte, int paramInt2, byte[][] paramArrayOfByte1);
/*      */   
/*      */ 
/*      */   private long lobLength(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1443 */     long l = 0L;
/* 1444 */     l = t2cLobGetLength(this.m_nativeState, paramArrayOfByte, paramArrayOfByte.length);
/*      */     
/* 1446 */     checkError((int)l);
/*      */     
/* 1448 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int blobRead(byte[] paramArrayOfByte1, long paramLong, int paramInt, byte[] paramArrayOfByte2, boolean paramBoolean, ByteBuffer paramByteBuffer)
/*      */     throws SQLException
/*      */   {
/* 1463 */     int i = 0;
/*      */     
/* 1465 */     i = t2cBlobRead(this.m_nativeState, paramArrayOfByte1, paramArrayOfByte1.length, paramLong, paramInt, paramArrayOfByte2, paramArrayOfByte2.length, paramBoolean, paramByteBuffer);
/*      */     
/*      */ 
/* 1468 */     checkError(i);
/*      */     
/* 1470 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int blobWrite(byte[] paramArrayOfByte1, long paramLong, byte[] paramArrayOfByte2, byte[][] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1485 */     int i = 0;
/*      */     
/* 1487 */     i = t2cBlobWrite(this.m_nativeState, paramArrayOfByte1, paramArrayOfByte1.length, paramLong, paramInt2, paramArrayOfByte2, paramInt1, paramArrayOfByte);
/*      */     
/*      */ 
/* 1490 */     checkError(i);
/*      */     
/* 1492 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int clobWrite(byte[] paramArrayOfByte, long paramLong, char[] paramArrayOfChar, byte[][] paramArrayOfByte1, boolean paramBoolean, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1508 */     int i = 0;
/*      */     
/* 1510 */     i = t2cClobWrite(this.m_nativeState, paramArrayOfByte, paramArrayOfByte.length, paramLong, paramInt2, paramArrayOfChar, paramInt1, paramArrayOfByte1, paramBoolean);
/*      */     
/*      */ 
/* 1513 */     checkError(i);
/*      */     
/* 1515 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private int lobGetChunkSize(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1523 */     int i = 0;
/* 1524 */     i = t2cLobGetChunkSize(this.m_nativeState, paramArrayOfByte, paramArrayOfByte.length);
/*      */     
/* 1526 */     checkError(i);
/*      */     
/* 1528 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized long length(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 1541 */     byte[] arrayOfByte = null;
/*      */     
/* 1543 */     checkTrue(this.lifecycle == 1, 8);
/* 1544 */     checkTrue((paramBFILE != null) && ((arrayOfByte = paramBFILE.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 1547 */     return lobLength(arrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized long position(BFILE paramBFILE, byte[] paramArrayOfByte, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1565 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 1568 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 1569 */       localSQLException.fillInStackTrace();
/* 1570 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1574 */     long l = LobPlsqlUtil.hasPattern(paramBFILE, paramArrayOfByte, paramLong);
/*      */     
/* 1576 */     l = l == 0L ? -1L : l;
/*      */     
/* 1578 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized long position(BFILE paramBFILE1, BFILE paramBFILE2, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1596 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 1599 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 1600 */       localSQLException.fillInStackTrace();
/* 1601 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1605 */     long l = LobPlsqlUtil.isSubLob(paramBFILE1, paramBFILE2, paramLong);
/*      */     
/* 1607 */     l = l == 0L ? -1L : l;
/*      */     
/* 1609 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized int getBytes(BFILE paramBFILE, long paramLong, int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1625 */     byte[] arrayOfByte = null;
/*      */     
/* 1627 */     checkTrue(this.lifecycle == 1, 8);
/* 1628 */     checkTrue((paramBFILE != null) && ((arrayOfByte = paramBFILE.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 1631 */     if ((paramInt <= 0) || (paramArrayOfByte == null)) {
/* 1632 */       return 0;
/*      */     }
/* 1634 */     if (paramInt > paramArrayOfByte.length) {
/* 1635 */       paramInt = paramArrayOfByte.length;
/*      */     }
/* 1637 */     if (this.useNio)
/*      */     {
/* 1639 */       i = paramArrayOfByte.length;
/* 1640 */       if ((this.nioBufferForLob == null) || (this.nioBufferForLob.capacity() < i)) {
/* 1641 */         this.nioBufferForLob = ByteBuffer.allocateDirect(i);
/*      */       } else {
/* 1643 */         this.nioBufferForLob.rewind();
/*      */       }
/*      */     }
/* 1646 */     int i = blobRead(arrayOfByte, paramLong, paramInt, paramArrayOfByte, this.useNio, this.nioBufferForLob);
/* 1647 */     if (this.useNio)
/*      */     {
/* 1649 */       this.nioBufferForLob.get(paramArrayOfByte);
/*      */     }
/*      */     
/* 1652 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized String getName(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 1666 */     byte[] arrayOfByte = null;
/* 1667 */     String str = null;
/*      */     
/* 1669 */     checkTrue(this.lifecycle == 1, 8);
/* 1670 */     checkTrue((paramBFILE != null) && ((arrayOfByte = paramBFILE.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 1673 */     str = t2cBfileGetName(this.m_nativeState, arrayOfByte, arrayOfByte.length);
/*      */     
/* 1675 */     checkError(str.length());
/*      */     
/* 1677 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized String getDirAlias(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 1691 */     byte[] arrayOfByte = null;
/* 1692 */     String str = null;
/*      */     
/* 1694 */     checkTrue(this.lifecycle == 1, 8);
/* 1695 */     checkTrue((paramBFILE != null) && ((arrayOfByte = paramBFILE.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 1698 */     str = t2cBfileGetDirAlias(this.m_nativeState, arrayOfByte, arrayOfByte.length);
/*      */     
/* 1700 */     checkError(str.length());
/*      */     
/* 1702 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void openFile(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 1715 */     byte[] arrayOfByte = null;
/*      */     
/* 1717 */     checkTrue(this.lifecycle == 1, 8);
/* 1718 */     checkTrue((paramBFILE != null) && ((arrayOfByte = paramBFILE.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 1721 */     byte[][] arrayOfByte1 = new byte[1][];
/*      */     
/* 1723 */     checkError(t2cBfileOpen(this.m_nativeState, arrayOfByte, arrayOfByte.length, arrayOfByte1));
/*      */     
/*      */ 
/* 1726 */     paramBFILE.setLocator(arrayOfByte1[0]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean isFileOpen(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 1743 */     byte[] arrayOfByte = null;
/*      */     
/* 1745 */     checkTrue(this.lifecycle == 1, 8);
/* 1746 */     checkTrue((paramBFILE != null) && ((arrayOfByte = paramBFILE.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 1749 */     boolean[] arrayOfBoolean = new boolean[1];
/*      */     
/* 1751 */     checkError(t2cBfileIsOpen(this.m_nativeState, arrayOfByte, arrayOfByte.length, arrayOfBoolean));
/*      */     
/* 1753 */     return arrayOfBoolean[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean fileExists(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 1769 */     byte[] arrayOfByte = null;
/*      */     
/* 1771 */     checkTrue(this.lifecycle == 1, 8);
/* 1772 */     checkTrue((paramBFILE != null) && ((arrayOfByte = paramBFILE.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 1775 */     boolean[] arrayOfBoolean = new boolean[1];
/*      */     
/* 1777 */     checkError(t2cBfileExists(this.m_nativeState, arrayOfByte, arrayOfByte.length, arrayOfBoolean));
/*      */     
/* 1779 */     return arrayOfBoolean[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void closeFile(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 1792 */     byte[] arrayOfByte = null;
/*      */     
/* 1794 */     checkTrue(this.lifecycle == 1, 8);
/* 1795 */     checkTrue((paramBFILE != null) && ((arrayOfByte = paramBFILE.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 1798 */     byte[][] arrayOfByte1 = new byte[1][];
/*      */     
/* 1800 */     checkError(t2cBfileClose(this.m_nativeState, arrayOfByte, arrayOfByte.length, arrayOfByte1));
/*      */     
/*      */ 
/* 1803 */     paramBFILE.setLocator(arrayOfByte1[0]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void open(BFILE paramBFILE, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1818 */     byte[] arrayOfByte = null;
/*      */     
/* 1820 */     checkTrue(this.lifecycle == 1, 8);
/* 1821 */     checkTrue((paramBFILE != null) && ((arrayOfByte = paramBFILE.shareBytes()) != null), 54);
/*      */     
/*      */ 
/* 1824 */     byte[][] arrayOfByte1 = new byte[1][];
/*      */     
/* 1826 */     checkError(t2cLobOpen(this.m_nativeState, 114, arrayOfByte, arrayOfByte.length, paramInt, arrayOfByte1));
/*      */     
/*      */ 
/* 1829 */     paramBFILE.setShareBytes(arrayOfByte1[0]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void close(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 1842 */     byte[] arrayOfByte = null;
/*      */     
/* 1844 */     checkTrue(this.lifecycle == 1, 8);
/* 1845 */     checkTrue((paramBFILE != null) && ((arrayOfByte = paramBFILE.shareBytes()) != null), 54);
/*      */     
/*      */ 
/* 1848 */     byte[][] arrayOfByte1 = new byte[1][];
/*      */     
/* 1850 */     checkError(t2cLobClose(this.m_nativeState, 114, arrayOfByte, arrayOfByte.length, arrayOfByte1));
/*      */     
/*      */ 
/* 1853 */     paramBFILE.setShareBytes(arrayOfByte1[0]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean isOpen(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 1867 */     byte[] arrayOfByte = null;
/*      */     
/* 1869 */     checkTrue(this.lifecycle == 1, 8);
/* 1870 */     checkTrue((paramBFILE != null) && ((arrayOfByte = paramBFILE.shareBytes()) != null), 54);
/*      */     
/*      */ 
/* 1873 */     boolean[] arrayOfBoolean = new boolean[1];
/*      */     
/* 1875 */     checkError(t2cLobIsOpen(this.m_nativeState, 114, arrayOfByte, arrayOfByte.length, arrayOfBoolean));
/*      */     
/*      */ 
/* 1878 */     return arrayOfBoolean[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream newInputStream(BFILE paramBFILE, int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1898 */     if (paramLong == 0L)
/*      */     {
/* 1900 */       return new OracleBlobInputStream(paramBFILE, paramInt);
/*      */     }
/*      */     
/*      */ 
/* 1904 */     return new OracleBlobInputStream(paramBFILE, paramInt, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream newConversionInputStream(BFILE paramBFILE, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1924 */     checkTrue((paramBFILE != null) && (paramBFILE.shareBytes() != null), 54);
/*      */     
/*      */ 
/* 1927 */     OracleConversionInputStream localOracleConversionInputStream = new OracleConversionInputStream(this.conversion, paramBFILE.getBinaryStream(), paramInt);
/*      */     
/* 1929 */     return localOracleConversionInputStream;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader newConversionReader(BFILE paramBFILE, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1949 */     checkTrue((paramBFILE != null) && (paramBFILE.shareBytes() != null), 54);
/*      */     
/*      */ 
/* 1952 */     OracleConversionReader localOracleConversionReader = new OracleConversionReader(this.conversion, paramBFILE.getBinaryStream(), paramInt);
/*      */     
/* 1954 */     return localOracleConversionReader;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized long length(BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 1968 */     byte[] arrayOfByte = null;
/*      */     
/* 1970 */     checkTrue(this.lifecycle == 1, 8);
/* 1971 */     checkTrue((paramBLOB != null) && ((arrayOfByte = paramBLOB.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 1974 */     return lobLength(arrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized long position(BLOB paramBLOB, byte[] paramArrayOfByte, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1993 */     checkTrue(this.lifecycle == 1, 8);
/* 1994 */     checkTrue((paramBLOB != null) && (paramBLOB.shareBytes() != null), 54);
/*      */     
/*      */ 
/* 1997 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 2000 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 2001 */       localSQLException.fillInStackTrace();
/* 2002 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 2006 */     long l = LobPlsqlUtil.hasPattern(paramBLOB, paramArrayOfByte, paramLong);
/*      */     
/* 2008 */     l = l == 0L ? -1L : l;
/* 2009 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized long position(BLOB paramBLOB1, BLOB paramBLOB2, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2026 */     checkTrue(this.lifecycle == 1, 8);
/* 2027 */     checkTrue((paramBLOB1 != null) && (paramBLOB1.shareBytes() != null), 54);
/*      */     
/* 2029 */     checkTrue((paramBLOB2 != null) && (paramBLOB2.shareBytes() != null), 54);
/*      */     
/*      */ 
/* 2032 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 2035 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 2036 */       localSQLException.fillInStackTrace();
/* 2037 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 2041 */     long l = LobPlsqlUtil.isSubLob(paramBLOB1, paramBLOB2, paramLong);
/*      */     
/* 2043 */     l = l == 0L ? -1L : l;
/* 2044 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized int getBytes(BLOB paramBLOB, long paramLong, int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 2061 */     byte[] arrayOfByte1 = null;
/* 2062 */     int i = 0;
/*      */     
/* 2064 */     checkTrue(this.lifecycle == 1, 8);
/* 2065 */     checkTrue((paramBLOB != null) && ((arrayOfByte1 = paramBLOB.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 2068 */     if ((paramInt <= 0) || (paramArrayOfByte == null)) {
/* 2069 */       return 0;
/*      */     }
/* 2071 */     if (paramInt > paramArrayOfByte.length) {
/* 2072 */       paramInt = paramArrayOfByte.length;
/*      */     }
/* 2074 */     long l = -1L;
/*      */     byte[] arrayOfByte2;
/* 2076 */     int j; if (paramBLOB.isActivePrefetch())
/*      */     {
/* 2078 */       arrayOfByte2 = paramBLOB.getPrefetchedData();
/* 2079 */       l = paramBLOB.length();
/* 2080 */       if ((arrayOfByte2 != null) && (arrayOfByte2 != null) && (paramLong <= arrayOfByte2.length))
/*      */       {
/*      */ 
/* 2083 */         j = Math.min(arrayOfByte2.length - (int)paramLong + 1, paramInt);
/*      */         
/* 2085 */         System.arraycopy(arrayOfByte2, (int)paramLong - 1, paramArrayOfByte, 0, j);
/*      */         
/* 2087 */         i += j;
/*      */       }
/*      */     }
/*      */     
/* 2091 */     if ((i < paramInt) && ((l == -1L) || (paramLong - 1L + i < l)))
/*      */     {
/*      */ 
/*      */ 
/* 2095 */       arrayOfByte2 = paramArrayOfByte;
/* 2096 */       j = i;
/* 2097 */       int k = ((l > 0L) && (l < paramInt) ? (int)l : paramInt) - i;
/*      */       
/* 2099 */       if (i > 0)
/*      */       {
/* 2101 */         arrayOfByte2 = new byte[k];
/*      */       }
/*      */       
/* 2104 */       if (this.useNio)
/*      */       {
/* 2106 */         int m = paramArrayOfByte.length;
/* 2107 */         if ((this.nioBufferForLob == null) || (this.nioBufferForLob.capacity() < m))
/*      */         {
/* 2109 */           this.nioBufferForLob = ByteBuffer.allocateDirect(m);
/*      */         } else {
/* 2111 */           this.nioBufferForLob.rewind();
/*      */         }
/*      */       }
/* 2114 */       i += blobRead(arrayOfByte1, paramLong + i, k, arrayOfByte2, this.useNio, this.nioBufferForLob);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2120 */       if (this.useNio)
/*      */       {
/* 2122 */         this.nioBufferForLob.get(arrayOfByte2);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 2127 */       if (j > 0)
/*      */       {
/* 2129 */         System.arraycopy(arrayOfByte2, 0, paramArrayOfByte, j, arrayOfByte2.length);
/*      */       }
/*      */     }
/*      */     
/* 2133 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized int putBytes(BLOB paramBLOB, long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2155 */     checkTrue((paramLong != 0L) || (paramInt2 > 0), 68);
/*      */     
/* 2157 */     checkTrue(paramLong >= 0L, 68);
/* 2158 */     if ((paramArrayOfByte == null) || (paramInt2 <= 0)) {
/* 2159 */       return 0;
/*      */     }
/* 2161 */     int i = 0;
/*      */     
/* 2163 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0) || (paramInt2 <= 0)) {
/* 2164 */       i = 0;
/*      */     }
/*      */     else {
/* 2167 */       byte[] arrayOfByte = null;
/*      */       
/* 2169 */       checkTrue(this.lifecycle == 1, 8);
/* 2170 */       checkTrue((paramBLOB != null) && ((arrayOfByte = paramBLOB.getLocator()) != null), 54);
/*      */       
/*      */ 
/* 2173 */       byte[][] arrayOfByte1 = new byte[1][];
/*      */       
/* 2175 */       paramBLOB.setActivePrefetch(false);
/* 2176 */       paramBLOB.clearCachedData();
/* 2177 */       i = blobWrite(arrayOfByte, paramLong, paramArrayOfByte, arrayOfByte1, paramInt1, paramInt2);
/*      */       
/*      */ 
/* 2180 */       paramBLOB.setLocator(arrayOfByte1[0]);
/*      */     }
/*      */     
/* 2183 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized int getChunkSize(BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 2195 */     byte[] arrayOfByte = null;
/*      */     
/* 2197 */     checkTrue(this.lifecycle == 1, 8);
/* 2198 */     checkTrue((paramBLOB != null) && ((arrayOfByte = paramBLOB.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 2201 */     return lobGetChunkSize(arrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void trim(BLOB paramBLOB, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2215 */     byte[] arrayOfByte = null;
/*      */     
/* 2217 */     checkTrue(this.lifecycle == 1, 8);
/* 2218 */     checkTrue((paramBLOB != null) && ((arrayOfByte = paramBLOB.shareBytes()) != null), 54);
/*      */     
/*      */ 
/* 2221 */     byte[][] arrayOfByte1 = new byte[1][];
/*      */     
/* 2223 */     paramBLOB.setActivePrefetch(false);
/* 2224 */     paramBLOB.clearCachedData();
/* 2225 */     checkError(t2cLobTrim(this.m_nativeState, 113, paramLong, arrayOfByte, arrayOfByte.length, arrayOfByte1));
/*      */     
/*      */ 
/* 2228 */     paramBLOB.setShareBytes(arrayOfByte1[0]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized BLOB createTemporaryBlob(Connection paramConnection, boolean paramBoolean, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2247 */     BLOB localBLOB = null;
/*      */     
/* 2249 */     checkTrue(this.lifecycle == 1, 8);
/*      */     
/* 2251 */     localBLOB = new BLOB((PhysicalConnection)paramConnection);
/*      */     
/* 2253 */     byte[][] arrayOfByte = new byte[1][];
/*      */     
/* 2255 */     checkError(t2cLobCreateTemporary(this.m_nativeState, 113, paramBoolean, paramInt, (short)0, arrayOfByte));
/*      */     
/*      */ 
/* 2258 */     localBLOB.setShareBytes(arrayOfByte[0]);
/*      */     
/* 2260 */     return localBLOB;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void freeTemporary(BLOB paramBLOB, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 2276 */       byte[] arrayOfByte = null;
/*      */       
/* 2278 */       checkTrue(this.lifecycle == 1, 8);
/* 2279 */       checkTrue((paramBLOB != null) && ((arrayOfByte = paramBLOB.shareBytes()) != null), 54);
/*      */       
/*      */ 
/* 2282 */       byte[][] arrayOfByte1 = new byte[1][];
/*      */       
/* 2284 */       checkError(t2cLobFreeTemporary(this.m_nativeState, 113, arrayOfByte, arrayOfByte.length, arrayOfByte1));
/*      */       
/*      */ 
/* 2287 */       paramBLOB.setShareBytes(arrayOfByte1[0]);
/*      */     }
/*      */     catch (SQLException localSQLException) {
/* 2290 */       if ((paramBoolean & localSQLException.getErrorCode() == 64201)) {
/* 2291 */         LobPlsqlUtil.freeTemporaryLob(this, paramBLOB, 2004);
/*      */       } else {
/* 2293 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean isTemporary(BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 2309 */     byte[] arrayOfByte = null;
/*      */     
/* 2311 */     checkTrue((paramBLOB != null) && ((arrayOfByte = paramBLOB.shareBytes()) != null), 54);
/*      */     
/*      */ 
/* 2314 */     boolean[] arrayOfBoolean = new boolean[1];
/*      */     
/* 2316 */     checkError(t2cLobIsTemporary(this.m_nativeState, 113, arrayOfByte, arrayOfByte.length, arrayOfBoolean));
/*      */     
/*      */ 
/* 2319 */     return arrayOfBoolean[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void open(BLOB paramBLOB, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2332 */     byte[] arrayOfByte = null;
/*      */     
/* 2334 */     checkTrue(this.lifecycle == 1, 8);
/* 2335 */     checkTrue((paramBLOB != null) && ((arrayOfByte = paramBLOB.shareBytes()) != null), 54);
/*      */     
/*      */ 
/* 2338 */     byte[][] arrayOfByte1 = new byte[1][];
/*      */     
/* 2340 */     checkError(t2cLobOpen(this.m_nativeState, 113, arrayOfByte, arrayOfByte.length, paramInt, arrayOfByte1));
/*      */     
/*      */ 
/* 2343 */     paramBLOB.setShareBytes(arrayOfByte1[0]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void close(BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 2356 */     byte[] arrayOfByte = null;
/*      */     
/* 2358 */     checkTrue(this.lifecycle == 1, 8);
/* 2359 */     checkTrue((paramBLOB != null) && ((arrayOfByte = paramBLOB.shareBytes()) != null), 54);
/*      */     
/*      */ 
/* 2362 */     byte[][] arrayOfByte1 = new byte[1][];
/*      */     
/* 2364 */     checkError(t2cLobClose(this.m_nativeState, 113, arrayOfByte, arrayOfByte.length, arrayOfByte1));
/*      */     
/*      */ 
/* 2367 */     paramBLOB.setShareBytes(arrayOfByte1[0]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean isOpen(BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 2381 */     byte[] arrayOfByte = null;
/*      */     
/* 2383 */     checkTrue(this.lifecycle == 1, 8);
/* 2384 */     checkTrue((paramBLOB != null) && ((arrayOfByte = paramBLOB.shareBytes()) != null), 54);
/*      */     
/*      */ 
/* 2387 */     boolean[] arrayOfBoolean = new boolean[1];
/*      */     
/* 2389 */     checkError(t2cLobIsOpen(this.m_nativeState, 113, arrayOfByte, arrayOfByte.length, arrayOfBoolean));
/*      */     
/*      */ 
/* 2392 */     return arrayOfBoolean[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream newInputStream(BLOB paramBLOB, int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2411 */     if (paramLong == 0L)
/*      */     {
/* 2413 */       return new OracleBlobInputStream(paramBLOB, paramInt);
/*      */     }
/*      */     
/*      */ 
/* 2417 */     return new OracleBlobInputStream(paramBLOB, paramInt, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream newInputStream(BLOB paramBLOB, int paramInt, long paramLong1, long paramLong2)
/*      */     throws SQLException
/*      */   {
/* 2440 */     return new OracleBlobInputStream(paramBLOB, paramInt, paramLong1, paramLong2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OutputStream newOutputStream(BLOB paramBLOB, int paramInt, long paramLong, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 2461 */     if (paramLong == 0L)
/*      */     {
/* 2463 */       if ((paramBoolean & this.lobStreamPosStandardCompliant))
/*      */       {
/*      */ 
/* 2466 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 2467 */         localSQLException.fillInStackTrace();
/* 2468 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 2473 */       return new OracleBlobOutputStream(paramBLOB, paramInt);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2478 */     return new OracleBlobOutputStream(paramBLOB, paramInt, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream newConversionInputStream(BLOB paramBLOB, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2499 */     checkTrue((paramBLOB != null) && (paramBLOB.shareBytes() != null), 54);
/*      */     
/*      */ 
/* 2502 */     OracleConversionInputStream localOracleConversionInputStream = new OracleConversionInputStream(this.conversion, paramBLOB.getBinaryStream(), paramInt);
/*      */     
/* 2504 */     return localOracleConversionInputStream;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader newConversionReader(BLOB paramBLOB, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2523 */     checkTrue((paramBLOB != null) && (paramBLOB.shareBytes() != null), 54);
/*      */     
/*      */ 
/* 2526 */     OracleConversionReader localOracleConversionReader = new OracleConversionReader(this.conversion, paramBLOB.getBinaryStream(), paramInt);
/*      */     
/* 2528 */     return localOracleConversionReader;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized long length(CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 2546 */     byte[] arrayOfByte = null;
/*      */     
/* 2548 */     checkTrue(this.lifecycle == 1, 8);
/* 2549 */     checkTrue((paramCLOB != null) && ((arrayOfByte = paramCLOB.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 2552 */     return lobLength(arrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized long position(CLOB paramCLOB, String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2570 */     if (paramString == null)
/*      */     {
/* 2572 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 2573 */       ((SQLException)localObject).fillInStackTrace();
/* 2574 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2577 */     checkTrue(this.lifecycle == 1, 8);
/* 2578 */     checkTrue((paramCLOB != null) && (paramCLOB.shareBytes() != null), 54);
/*      */     
/*      */ 
/* 2581 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 2584 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 2585 */       ((SQLException)localObject).fillInStackTrace();
/* 2586 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2590 */     Object localObject = new char[paramString.length()];
/*      */     
/* 2592 */     paramString.getChars(0, localObject.length, (char[])localObject, 0);
/*      */     
/* 2594 */     long l = LobPlsqlUtil.hasPattern(paramCLOB, (char[])localObject, paramLong);
/*      */     
/* 2596 */     l = l == 0L ? -1L : l;
/* 2597 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized long position(CLOB paramCLOB1, CLOB paramCLOB2, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2614 */     checkTrue(this.lifecycle == 1, 8);
/* 2615 */     checkTrue((paramCLOB1 != null) && (paramCLOB1.shareBytes() != null), 54);
/*      */     
/* 2617 */     checkTrue((paramCLOB2 != null) && (paramCLOB2.shareBytes() != null), 54);
/*      */     
/*      */ 
/* 2620 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 2623 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 2624 */       localSQLException.fillInStackTrace();
/* 2625 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 2629 */     long l = LobPlsqlUtil.isSubLob(paramCLOB1, paramCLOB2, paramLong);
/*      */     
/* 2631 */     l = l == 0L ? -1L : l;
/* 2632 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized int getChars(CLOB paramCLOB, long paramLong, int paramInt, char[] paramArrayOfChar)
/*      */     throws SQLException
/*      */   {
/* 2649 */     byte[] arrayOfByte = null;
/*      */     
/* 2651 */     checkTrue(this.lifecycle == 1, 8);
/* 2652 */     checkTrue((paramCLOB != null) && ((arrayOfByte = paramCLOB.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 2655 */     if ((paramInt <= 0) || (paramArrayOfChar == null)) {
/* 2656 */       return 0;
/*      */     }
/* 2658 */     if (paramInt > paramArrayOfChar.length) {
/* 2659 */       paramInt = paramArrayOfChar.length;
/*      */     }
/* 2661 */     int i = 0;
/*      */     
/*      */ 
/* 2664 */     long l = -1L;
/*      */     char[] arrayOfChar;
/*      */     int j;
/* 2667 */     if (paramCLOB.isActivePrefetch())
/*      */     {
/* 2669 */       l = paramCLOB.length();
/* 2670 */       arrayOfChar = paramCLOB.getPrefetchedData();
/* 2671 */       if ((arrayOfChar != null) && (paramLong <= arrayOfChar.length))
/*      */       {
/*      */ 
/* 2674 */         j = Math.min(arrayOfChar.length - (int)paramLong + 1, paramInt);
/*      */         
/*      */ 
/* 2677 */         System.arraycopy(arrayOfChar, (int)paramLong - 1, paramArrayOfChar, 0, j);
/*      */         
/* 2679 */         i += j;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 2684 */     if ((i < paramInt) && ((l == -1L) || (paramLong - 1L + i < l)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2689 */       arrayOfChar = paramArrayOfChar;
/* 2690 */       j = i;
/* 2691 */       int k = ((l > 0L) && (l < paramInt) ? (int)l : paramInt) - i;
/*      */       
/* 2693 */       if (i > 0)
/*      */       {
/* 2695 */         arrayOfChar = new char[k];
/*      */       }
/*      */       
/* 2698 */       if (this.useNio)
/*      */       {
/*      */ 
/*      */ 
/* 2702 */         int m = paramArrayOfChar.length * 2;
/* 2703 */         if ((this.nioBufferForLob == null) || (this.nioBufferForLob.capacity() < m)) {
/* 2704 */           this.nioBufferForLob = ByteBuffer.allocateDirect(m);
/*      */         } else {
/* 2706 */           this.nioBufferForLob.rewind();
/*      */         }
/*      */       }
/* 2709 */       i += t2cClobRead(this.m_nativeState, arrayOfByte, arrayOfByte.length, paramLong + i, k, arrayOfChar, arrayOfChar.length, paramCLOB.isNCLOB(), this.useNio, this.nioBufferForLob);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2721 */       if (this.useNio)
/*      */       {
/* 2723 */         ByteBuffer localByteBuffer = this.nioBufferForLob.order(ByteOrder.LITTLE_ENDIAN);
/* 2724 */         CharBuffer localCharBuffer = localByteBuffer.asCharBuffer();
/* 2725 */         localCharBuffer.get(arrayOfChar);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 2730 */       if (j > 0)
/*      */       {
/* 2732 */         System.arraycopy(arrayOfChar, 0, paramArrayOfChar, j, arrayOfChar.length);
/*      */       }
/*      */       
/* 2735 */       checkError(i);
/*      */     }
/*      */     
/* 2738 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized int putChars(CLOB paramCLOB, long paramLong, char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2759 */     byte[] arrayOfByte = null;
/*      */     
/* 2761 */     checkTrue(this.lifecycle == 1, 8);
/* 2762 */     checkTrue(paramLong >= 0L, 68);
/*      */     
/* 2764 */     checkTrue((paramCLOB != null) && ((arrayOfByte = paramCLOB.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 2767 */     if (paramArrayOfChar == null) {
/* 2768 */       return 0;
/*      */     }
/* 2770 */     byte[][] arrayOfByte1 = new byte[1][];
/* 2771 */     paramCLOB.setActivePrefetch(false);
/* 2772 */     paramCLOB.clearCachedData();
/* 2773 */     int i = clobWrite(arrayOfByte, paramLong, paramArrayOfChar, arrayOfByte1, paramCLOB.isNCLOB(), paramInt1, paramInt2);
/*      */     
/*      */ 
/* 2776 */     paramCLOB.setLocator(arrayOfByte1[0]);
/*      */     
/* 2778 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized int getChunkSize(CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 2790 */     byte[] arrayOfByte = null;
/*      */     
/* 2792 */     checkTrue(this.lifecycle == 1, 8);
/* 2793 */     checkTrue((paramCLOB != null) && ((arrayOfByte = paramCLOB.getLocator()) != null), 54);
/*      */     
/*      */ 
/* 2796 */     return lobGetChunkSize(arrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void trim(CLOB paramCLOB, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2810 */     byte[] arrayOfByte = null;
/*      */     
/* 2812 */     checkTrue(this.lifecycle == 1, 8);
/* 2813 */     checkTrue((paramCLOB != null) && ((arrayOfByte = paramCLOB.shareBytes()) != null), 54);
/*      */     
/*      */ 
/* 2816 */     byte[][] arrayOfByte1 = new byte[1][];
/*      */     
/* 2818 */     paramCLOB.setActivePrefetch(false);
/* 2819 */     paramCLOB.clearCachedData();
/* 2820 */     checkError(t2cLobTrim(this.m_nativeState, 112, paramLong, arrayOfByte, arrayOfByte.length, arrayOfByte1));
/*      */     
/*      */ 
/* 2823 */     paramCLOB.setShareBytes(arrayOfByte1[0]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized CLOB createTemporaryClob(Connection paramConnection, boolean paramBoolean, int paramInt, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 2843 */     Object localObject = null;
/*      */     
/* 2845 */     checkTrue(this.lifecycle == 1, 8);
/*      */     
/* 2847 */     if (paramShort == 1) {
/* 2848 */       localObject = new CLOB((PhysicalConnection)paramConnection);
/*      */     }
/*      */     else {
/* 2851 */       localObject = new NCLOB((PhysicalConnection)paramConnection);
/*      */     }
/*      */     
/* 2854 */     byte[][] arrayOfByte = new byte[1][];
/*      */     
/* 2856 */     checkError(t2cLobCreateTemporary(this.m_nativeState, 112, paramBoolean, paramInt, paramShort, arrayOfByte));
/*      */     
/*      */ 
/* 2859 */     ((CLOB)localObject).setShareBytes(arrayOfByte[0]);
/*      */     
/* 2861 */     return (CLOB)localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void freeTemporary(CLOB paramCLOB, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 2876 */       byte[] arrayOfByte = null;
/*      */       
/* 2878 */       checkTrue(this.lifecycle == 1, 8);
/* 2879 */       checkTrue((paramCLOB != null) && ((arrayOfByte = paramCLOB.shareBytes()) != null), 54);
/*      */       
/*      */ 
/* 2882 */       byte[][] arrayOfByte1 = new byte[1][];
/*      */       
/* 2884 */       checkError(t2cLobFreeTemporary(this.m_nativeState, 112, arrayOfByte, arrayOfByte.length, arrayOfByte1));
/*      */       
/*      */ 
/* 2887 */       paramCLOB.setShareBytes(arrayOfByte1[0]);
/*      */     }
/*      */     catch (SQLException localSQLException) {
/* 2890 */       if ((paramBoolean & localSQLException.getErrorCode() == 64201)) {
/* 2891 */         LobPlsqlUtil.freeTemporaryLob(this, paramCLOB, 2005);
/*      */       } else {
/* 2893 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean isTemporary(CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 2910 */     byte[] arrayOfByte = null;
/*      */     
/* 2912 */     checkTrue((paramCLOB != null) && ((arrayOfByte = paramCLOB.shareBytes()) != null), 54);
/*      */     
/*      */ 
/* 2915 */     boolean[] arrayOfBoolean = new boolean[1];
/*      */     
/* 2917 */     checkError(t2cLobIsTemporary(this.m_nativeState, 112, arrayOfByte, arrayOfByte.length, arrayOfBoolean));
/*      */     
/*      */ 
/* 2920 */     return arrayOfBoolean[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void open(CLOB paramCLOB, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2933 */     byte[] arrayOfByte = null;
/*      */     
/* 2935 */     checkTrue(this.lifecycle == 1, 8);
/* 2936 */     checkTrue((paramCLOB != null) && ((arrayOfByte = paramCLOB.shareBytes()) != null), 54);
/*      */     
/*      */ 
/* 2939 */     byte[][] arrayOfByte1 = new byte[1][];
/*      */     
/* 2941 */     checkError(t2cLobOpen(this.m_nativeState, 112, arrayOfByte, arrayOfByte.length, paramInt, arrayOfByte1));
/*      */     
/*      */ 
/* 2944 */     paramCLOB.setShareBytes(arrayOfByte1[0]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void close(CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 2957 */     byte[] arrayOfByte = null;
/*      */     
/* 2959 */     checkTrue(this.lifecycle == 1, 8);
/* 2960 */     checkTrue((paramCLOB != null) && ((arrayOfByte = paramCLOB.shareBytes()) != null), 54);
/*      */     
/*      */ 
/* 2963 */     byte[][] arrayOfByte1 = new byte[1][];
/*      */     
/* 2965 */     checkError(t2cLobClose(this.m_nativeState, 112, arrayOfByte, arrayOfByte.length, arrayOfByte1));
/*      */     
/*      */ 
/* 2968 */     paramCLOB.setShareBytes(arrayOfByte1[0]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean isOpen(CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 2982 */     byte[] arrayOfByte = null;
/*      */     
/* 2984 */     checkTrue(this.lifecycle == 1, 8);
/* 2985 */     checkTrue((paramCLOB != null) && ((arrayOfByte = paramCLOB.shareBytes()) != null), 54);
/*      */     
/*      */ 
/* 2988 */     boolean[] arrayOfBoolean = new boolean[1];
/*      */     
/* 2990 */     checkError(t2cLobIsOpen(this.m_nativeState, 112, arrayOfByte, arrayOfByte.length, arrayOfBoolean));
/*      */     
/*      */ 
/* 2993 */     return arrayOfBoolean[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream newInputStream(CLOB paramCLOB, int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3013 */     if (paramLong == 0L)
/*      */     {
/* 3015 */       return new OracleClobInputStream(paramCLOB, paramInt);
/*      */     }
/*      */     
/*      */ 
/* 3019 */     return new OracleClobInputStream(paramCLOB, paramInt, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OutputStream newOutputStream(CLOB paramCLOB, int paramInt, long paramLong, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 3040 */     if (paramLong == 0L)
/*      */     {
/* 3042 */       if ((paramBoolean & this.lobStreamPosStandardCompliant))
/*      */       {
/*      */ 
/* 3045 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 3046 */         localSQLException.fillInStackTrace();
/* 3047 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 3052 */       return new OracleClobOutputStream(paramCLOB, paramInt);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3057 */     return new OracleClobOutputStream(paramCLOB, paramInt, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader newReader(CLOB paramCLOB, int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3077 */     if (paramLong == 0L)
/*      */     {
/* 3079 */       return new OracleClobReader(paramCLOB, paramInt);
/*      */     }
/*      */     
/*      */ 
/* 3083 */     return new OracleClobReader(paramCLOB, paramInt, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader newReader(CLOB paramCLOB, int paramInt, long paramLong1, long paramLong2)
/*      */     throws SQLException
/*      */   {
/* 3104 */     return new OracleClobReader(paramCLOB, paramInt, paramLong1, paramLong2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Writer newWriter(CLOB paramCLOB, int paramInt, long paramLong, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 3124 */     if (paramLong == 0L)
/*      */     {
/* 3126 */       if ((paramBoolean & this.lobStreamPosStandardCompliant))
/*      */       {
/*      */ 
/* 3129 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 3130 */         localSQLException.fillInStackTrace();
/* 3131 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 3136 */       return new OracleClobWriter(paramCLOB, paramInt);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3141 */     return new OracleClobWriter(paramCLOB, paramInt, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void registerTAFCallback(OracleOCIFailover paramOracleOCIFailover, Object paramObject)
/*      */     throws SQLException
/*      */   {
/* 3168 */     this.appCallback = paramOracleOCIFailover;
/* 3169 */     this.appCallbackObject = paramObject;
/*      */     
/* 3171 */     checkError(t2cRegisterTAFCallback(this.m_nativeState));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   synchronized int callTAFCallbackMethod(int paramInt1, int paramInt2)
/*      */   {
/* 3181 */     int i = 0;
/*      */     
/*      */ 
/* 3184 */     if (this.appCallback != null) {
/* 3185 */       i = this.appCallback.callbackFn(this, this.appCallbackObject, paramInt1, paramInt2);
/*      */     }
/* 3187 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getHeapAllocSize()
/*      */     throws SQLException
/*      */   {
/* 3201 */     if (this.lifecycle != 1)
/*      */     {
/* 3203 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 3204 */       localSQLException1.fillInStackTrace();
/* 3205 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3210 */     int i = t2cGetHeapAllocSize(this.m_nativeState);
/*      */     
/* 3212 */     if (i < 0)
/*      */     {
/* 3214 */       if (i == 64537)
/*      */       {
/* 3216 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23);
/* 3217 */         localSQLException2.fillInStackTrace();
/* 3218 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 3224 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 89);
/* 3225 */       localSQLException2.fillInStackTrace();
/* 3226 */       throw localSQLException2;
/*      */     }
/*      */     
/*      */ 
/* 3230 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getOCIEnvHeapAllocSize()
/*      */     throws SQLException
/*      */   {
/* 3243 */     if (this.lifecycle != 1)
/*      */     {
/* 3245 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 3246 */       localSQLException1.fillInStackTrace();
/* 3247 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3252 */     int i = t2cGetOciEnvHeapAllocSize(this.m_nativeState);
/*      */     
/* 3254 */     if (i < 0)
/*      */     {
/* 3256 */       if (i == 64537)
/*      */       {
/* 3258 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23);
/* 3259 */         localSQLException2.fillInStackTrace();
/* 3260 */         throw localSQLException2;
/*      */       }
/*      */       
/* 3263 */       checkError(i);
/*      */       
/*      */ 
/*      */ 
/* 3267 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 89);
/* 3268 */       localSQLException2.fillInStackTrace();
/* 3269 */       throw localSQLException2;
/*      */     }
/*      */     
/*      */ 
/* 3273 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final short getClientCharSetId()
/*      */   {
/* 3282 */     return 871;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static short getDriverCharSetIdFromNLS_LANG()
/*      */     throws SQLException
/*      */   {
/* 3297 */     if (!isLibraryLoaded) {
/* 3298 */       loadNativeLibrary();
/*      */     }
/*      */     
/* 3301 */     short s = t2cGetDriverCharSetFromNlsLang();
/*      */     
/*      */ 
/* 3304 */     if (s < 0)
/*      */     {
/* 3306 */       SQLException localSQLException = DatabaseError.createSqlException(null, 8);
/* 3307 */       localSQLException.fillInStackTrace();
/* 3308 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 3312 */     return s;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void doProxySession(int paramInt, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/* 3325 */     byte[][] arrayOfByte = (byte[][])null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3331 */     int i = 0;
/*      */     
/* 3333 */     this.savedUser = this.userName;
/* 3334 */     this.userName = null;
/*      */     byte[] arrayOfByte4;
/* 3336 */     byte[] arrayOfByte3; byte[] arrayOfByte2; byte[] arrayOfByte1 = arrayOfByte2 = arrayOfByte3 = arrayOfByte4 = new byte[0];
/*      */     
/* 3338 */     switch (paramInt)
/*      */     {
/*      */     case 1: 
/* 3341 */       this.userName = paramProperties.getProperty("PROXY_USER_NAME");
/* 3342 */       String str1 = paramProperties.getProperty("PROXY_USER_PASSWORD");
/* 3343 */       if (this.userName != null) {
/* 3344 */         arrayOfByte1 = DBConversion.stringToDriverCharBytes(this.userName, this.m_clientCharacterSet);
/*      */       }
/* 3346 */       if (str1 != null)
/* 3347 */         arrayOfByte2 = DBConversion.stringToDriverCharBytes(str1, this.m_clientCharacterSet);
/*      */       break;
/*      */     case 2: 
/* 3350 */       String str2 = paramProperties.getProperty("PROXY_DISTINGUISHED_NAME");
/* 3351 */       if (str2 != null) {
/* 3352 */         arrayOfByte3 = DBConversion.stringToDriverCharBytes(str2, this.m_clientCharacterSet);
/*      */       }
/*      */       break;
/*      */     case 3: 
/* 3356 */       Object localObject = paramProperties.get("PROXY_CERTIFICATE");
/* 3357 */       arrayOfByte4 = (byte[])localObject;
/*      */     }
/*      */     
/* 3360 */     String[] arrayOfString = (String[])paramProperties.get("PROXY_ROLES");
/*      */     
/* 3362 */     if (arrayOfString != null)
/*      */     {
/* 3364 */       i = arrayOfString.length;
/* 3365 */       arrayOfByte = new byte[i][];
/* 3366 */       for (int j = 0; j < i; j++)
/*      */       {
/* 3368 */         if (arrayOfString[j] == null)
/*      */         {
/* 3370 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 150);
/* 3371 */           localSQLException.fillInStackTrace();
/* 3372 */           throw localSQLException;
/*      */         }
/*      */         
/* 3375 */         arrayOfByte[j] = DBConversion.stringToDriverCharBytes(arrayOfString[j], this.m_clientCharacterSet);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 3380 */     this.sqlWarning = checkError(t2cDoProxySession(this.m_nativeState, paramInt, arrayOfByte1, arrayOfByte1.length, arrayOfByte2, arrayOfByte2.length, arrayOfByte3, arrayOfByte3.length, arrayOfByte4, arrayOfByte4.length, i, arrayOfByte), this.sqlWarning);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3394 */     this.isProxy = true;
/*      */   }
/*      */   
/*      */ 
/*      */   void closeProxySession()
/*      */     throws SQLException
/*      */   {
/* 3401 */     checkError(t2cCloseProxySession(this.m_nativeState));
/* 3402 */     this.userName = this.savedUser;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void doDescribeTable(AutoKeyInfo paramAutoKeyInfo)
/*      */     throws SQLException
/*      */   {
/* 3410 */     String str = paramAutoKeyInfo.getTableName();
/*      */     
/* 3412 */     byte[] arrayOfByte = DBConversion.stringToDriverCharBytes(str, this.m_clientCharacterSet);
/*      */     
/*      */     int i;
/*      */     
/*      */     int j;
/*      */     
/*      */     do
/*      */     {
/* 3420 */       i = 0;
/* 3421 */       j = t2cDescribeTable(this.m_nativeState, arrayOfByte, arrayOfByte.length, this.queryMetaData1, this.queryMetaData2, this.queryMetaData1Offset, this.queryMetaData2Offset, this.queryMetaData1Size, this.queryMetaData2Size);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3431 */       if (j == -1)
/*      */       {
/* 3433 */         checkError(j);
/*      */       }
/*      */       
/*      */ 
/* 3437 */       if (j == T2CStatement.T2C_EXTEND_BUFFER)
/*      */       {
/* 3439 */         i = 1;
/*      */         
/* 3441 */         reallocateQueryMetaData(this.queryMetaData1Size * 2, this.queryMetaData2Size * 2);
/*      */       }
/*      */       
/* 3444 */     } while (i != 0);
/*      */     
/* 3446 */     processDescribeTableData(j, paramAutoKeyInfo);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void processDescribeTableData(int paramInt, AutoKeyInfo paramAutoKeyInfo)
/*      */     throws SQLException
/*      */   {
/* 3455 */     short[] arrayOfShort = this.queryMetaData1;
/* 3456 */     byte[] arrayOfByte = this.queryMetaData2;
/* 3457 */     int i = this.queryMetaData1Offset;
/* 3458 */     int j = this.queryMetaData2Offset;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3472 */     paramAutoKeyInfo.allocateSpaceForDescribedData(paramInt);
/*      */     
/* 3474 */     for (int i5 = 0; i5 < paramInt; i5++)
/*      */     {
/* 3476 */       int m = arrayOfShort[(i + 0)];
/* 3477 */       int k = arrayOfShort[(i + 6)];
/* 3478 */       String str1 = bytes2String(arrayOfByte, j, k, this.conversion);
/*      */       
/*      */ 
/* 3481 */       int n = arrayOfShort[(i + 1)];
/* 3482 */       int i1 = arrayOfShort[(i + 11)];
/* 3483 */       boolean bool = arrayOfShort[(i + 2)] != 0;
/* 3484 */       short s = arrayOfShort[(i + 5)];
/* 3485 */       int i2 = arrayOfShort[(i + 3)];
/* 3486 */       int i3 = arrayOfShort[(i + 4)];
/* 3487 */       int i4 = arrayOfShort[(i + 12)];
/*      */       
/* 3489 */       j += k;
/* 3490 */       i += 13;
/*      */       
/* 3492 */       String str2 = null;
/* 3493 */       if (i4 > 0)
/*      */       {
/* 3495 */         str2 = bytes2String(arrayOfByte, j, i4, this.conversion);
/*      */         
/* 3497 */         j += i4;
/*      */       }
/*      */       
/*      */ 
/* 3501 */       paramAutoKeyInfo.fillDescribedData(i5, str1, m, i1 > 0 ? i1 : n, bool, s, i2, i3, str2);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void doSetApplicationContext(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 3515 */     checkError(t2cSetApplicationContext(this.m_nativeState, paramString1, paramString2, paramString3));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void doClearAllApplicationContext(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3524 */     checkError(t2cClearAllApplicationContext(this.m_nativeState, paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void doStartup(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3532 */     checkError(t2cStartupDatabase(this.m_nativeState, paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void doShutdown(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3540 */     checkError(t2cShutdownDatabase(this.m_nativeState, paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final void loadNativeLibrary()
/*      */     throws SQLException
/*      */   {
/* 3552 */     synchronized (T2CConnection.class)
/*      */     {
/* 3554 */       if (!isLibraryLoaded)
/*      */       {
/* 3556 */         AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/* 3560 */             System.loadLibrary("ocijdbc11");
/* 3561 */             int i = T2CConnection.getLibraryVersionNumber();
/* 3562 */             if (i != T2CConnection.JDBC_OCI_LIBRARY_VERSION) {
/* 3563 */               throw new Error("Incompatible version of libocijdbc[Jdbc:" + T2CConnection.JDBC_OCI_LIBRARY_VERSION + ", Jdbc-OCI:" + i);
/*      */             }
/*      */             
/* 3566 */             return null;
/*      */           }
/* 3568 */         });
/* 3569 */         isLibraryLoaded = true;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private final void checkTrue(boolean paramBoolean, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3579 */     if (!paramBoolean)
/*      */     {
/* 3581 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), paramInt);
/* 3582 */       localSQLException.fillInStackTrace();
/* 3583 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   boolean useLittleEndianSetCHARBinder()
/*      */     throws SQLException
/*      */   {
/* 3591 */     return t2cPlatformIsLittleEndian(this.m_nativeState);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void getPropertyForPooledConnection(OraclePooledConnection paramOraclePooledConnection)
/*      */     throws SQLException
/*      */   {
/* 3599 */     super.getPropertyForPooledConnection(paramOraclePooledConnection, this.password);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static final char[] getCharArray(String paramString)
/*      */   {
/* 3606 */     char[] arrayOfChar = null;
/*      */     
/* 3608 */     if (paramString == null)
/*      */     {
/* 3610 */       arrayOfChar = new char[0];
/*      */     }
/*      */     else
/*      */     {
/* 3614 */       arrayOfChar = new char[paramString.length()];
/*      */       
/* 3616 */       paramString.getChars(0, paramString.length(), arrayOfChar, 0);
/*      */     }
/*      */     
/* 3619 */     return arrayOfChar;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static String bytes2String(byte[] paramArrayOfByte, int paramInt1, int paramInt2, DBConversion paramDBConversion)
/*      */     throws SQLException
/*      */   {
/* 3629 */     byte[] arrayOfByte = new byte[paramInt2];
/* 3630 */     System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
/*      */     
/* 3632 */     return paramDBConversion.CharBytesToString(arrayOfByte, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void disableNio()
/*      */   {
/* 3640 */     this.useNio = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static synchronized void doSetSessionTimeZone(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3648 */     t2cSetSessionTimeZone(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static native int getLibraryVersionNumber();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static native short t2cGetServerSessionInfo(long paramLong, Properties paramProperties);
/*      */   
/*      */ 
/*      */ 
/*      */   static native short t2cGetDriverCharSetFromNlsLang();
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cDescribeError(long paramLong, T2CError paramT2CError, byte[] paramArrayOfByte);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cCreateState(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, byte[] paramArrayOfByte3, int paramInt3, byte[] paramArrayOfByte4, int paramInt4, byte[] paramArrayOfByte5, int paramInt5, byte[] paramArrayOfByte6, int paramInt6, byte[] paramArrayOfByte7, int paramInt7, short paramShort, int paramInt8, short[] paramArrayOfShort, byte[] paramArrayOfByte8, byte[] paramArrayOfByte9, boolean paramBoolean, long[] paramArrayOfLong);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cLogon(long paramLong, byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, byte[] paramArrayOfByte3, int paramInt3, byte[] paramArrayOfByte4, int paramInt4, byte[] paramArrayOfByte5, int paramInt5, byte[] paramArrayOfByte6, int paramInt6, byte[] paramArrayOfByte7, int paramInt7, int paramInt8, short[] paramArrayOfShort, byte[] paramArrayOfByte8, byte[] paramArrayOfByte9, long[] paramArrayOfLong);
/*      */   
/*      */ 
/*      */ 
/*      */   private native int t2cLogoff(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   private native int t2cCancel(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   private native byte t2cGetAsmVolProperty(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   private native byte t2cGetInstanceType(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   private native int t2cCreateStatement(long paramLong1, long paramLong2, byte[] paramArrayOfByte, int paramInt1, OracleStatement paramOracleStatement, boolean paramBoolean, int paramInt2);
/*      */   
/*      */ 
/*      */ 
/*      */   private native int t2cSetAutoCommit(long paramLong, boolean paramBoolean);
/*      */   
/*      */ 
/*      */ 
/*      */   private native int t2cCommit(long paramLong, int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */   private native int t2cRollback(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   private native int t2cPingDatabase(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   private native byte[] t2cGetProductionVersion(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   private native int t2cGetVersionNumber(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   private native int t2cGetDefaultStreamChunkSize(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cGetFormOfUse(long paramLong, OracleTypeCLOB paramOracleTypeCLOB, byte[] paramArrayOfByte, int paramInt1, int paramInt2);
/*      */   
/*      */ 
/*      */ 
/*      */   native long t2cGetTDO(long paramLong, byte[] paramArrayOfByte, int paramInt, int[] paramArrayOfInt);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cCreateConnPool(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, byte[] paramArrayOfByte3, int paramInt3, short paramShort, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cConnPoolLogon(long paramLong, byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, byte[] paramArrayOfByte3, int paramInt3, byte[] paramArrayOfByte4, int paramInt4, byte[] paramArrayOfByte5, int paramInt5, int paramInt6, int paramInt7, int paramInt8, String[] paramArrayOfString, byte[] paramArrayOfByte6, int paramInt9, byte[] paramArrayOfByte7, int paramInt10, byte[] paramArrayOfByte8, int paramInt11, byte[] paramArrayOfByte9, int paramInt12, byte[] paramArrayOfByte10, int paramInt13, short[] paramArrayOfShort, byte[] paramArrayOfByte11, byte[] paramArrayOfByte12, long[] paramArrayOfLong);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cGetConnPoolInfo(long paramLong, Properties paramProperties);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cSetConnPoolInfo(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cPasswordChange(long paramLong, byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, byte[] paramArrayOfByte3, int paramInt3);
/*      */   
/*      */ 
/*      */ 
/*      */   protected native byte[] t2cGetConnectionId(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cGetHandles(long paramLong, long[] paramArrayOfLong);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cUseConnection(long paramLong1, long paramLong2, long paramLong3, long paramLong4, short[] paramArrayOfShort, long[] paramArrayOfLong);
/*      */   
/*      */ 
/*      */ 
/*      */   native boolean t2cPlatformIsLittleEndian(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cRegisterTAFCallback(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cGetHeapAllocSize(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cGetOciEnvHeapAllocSize(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cDoProxySession(long paramLong, int paramInt1, byte[] paramArrayOfByte1, int paramInt2, byte[] paramArrayOfByte2, int paramInt3, byte[] paramArrayOfByte3, int paramInt4, byte[] paramArrayOfByte4, int paramInt5, int paramInt6, byte[][] paramArrayOfByte);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cCloseProxySession(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */   static native int t2cDescribeTable(long paramLong, byte[] paramArrayOfByte1, int paramInt1, short[] paramArrayOfShort, byte[] paramArrayOfByte2, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cSetApplicationContext(long paramLong, String paramString1, String paramString2, String paramString3);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cClearAllApplicationContext(long paramLong, String paramString);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cStartupDatabase(long paramLong, int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */   native int t2cShutdownDatabase(long paramLong, int paramInt);
/*      */   
/*      */ 
/*      */   static native void t2cSetSessionTimeZone(String paramString);
/*      */   
/*      */ 
/*      */   public void incrementTempLobReferenceCount(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {}
/*      */   
/*      */ 
/* 3819 */   public int decrementTempLobReferenceCount(byte[] paramArrayOfByte)
/* 3819 */     throws SQLException { return 0; }
/*      */   
/* 3821 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T2CConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */