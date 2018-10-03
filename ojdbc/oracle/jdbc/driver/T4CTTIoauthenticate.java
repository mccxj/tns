/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.net.InetAddress;
/*      */ import java.sql.DatabaseMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.util.Properties;
/*      */ import java.util.TimeZone;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.util.RepConversion;
/*      */ import oracle.net.ano.AuthenticationService;
/*      */ import oracle.net.ns.Communication;
/*      */ import oracle.net.ns.SessionAtts;
/*      */ import oracle.net.nt.TcpsNTAdapter;
/*      */ import oracle.security.o3logon.O3LoginClientHelper;
/*      */ import oracle.security.o5logon.O5Logon;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ final class T4CTTIoauthenticate
/*      */   extends T4CTTIfun
/*      */ {
/*      */   byte[] terminal;
/*      */   byte[] enableTempLobRefCnt;
/*      */   byte[] machine;
/*      */   byte[] sysUserName;
/*      */   byte[] processID;
/*      */   byte[] programName;
/*      */   byte[] encryptedSK;
/*      */   byte[] internalName;
/*      */   byte[] externalName;
/*      */   byte[] alterSession;
/*      */   byte[] aclValue;
/*      */   byte[] clientname;
/*   86 */   byte[] editionName = null;
/*      */   
/*      */   byte[] driverName;
/*      */   
/*      */   String ressourceManagerId;
/*      */   
/*      */   boolean bUseO5Logon;
/*      */   
/*      */   int verifierType;
/*      */   
/*      */   static final int ZTVT_ORCL_7 = 2361;
/*      */   
/*      */   static final int ZTVT_SSH1 = 6949;
/*      */   static final int ZTVT_NTV = 7809;
/*      */   static final int ZTVT_SMD5 = 59694;
/*      */   static final int ZTVT_MD5 = 40674;
/*      */   static final int ZTVT_SH1 = 45394;
/*      */   byte[] salt;
/*      */   byte[] encryptedKB;
/*  105 */   boolean isSessionTZ = true;
/*      */   
/*      */   static final int SERVER_VERSION_81 = 8100;
/*      */   
/*      */   static final int KPZ_LOGON = 1;
/*      */   
/*      */   static final int KPZ_CPW = 2;
/*      */   
/*      */   static final int KPZ_SRVAUTH = 4;
/*      */   
/*      */   static final int KPZ_ENCRYPTED_PASSWD = 256;
/*      */   
/*      */   static final int KPZ_LOGON_MIGRATE = 16;
/*      */   
/*      */   static final int KPZ_LOGON_SYSDBA = 32;
/*      */   
/*      */   static final int KPZ_LOGON_SYSOPER = 64;
/*      */   
/*      */   static final int KPZ_LOGON_PRELIMAUTH = 128;
/*      */   
/*      */   static final int KPZ_PASSWD_ENCRYPTED = 256;
/*      */   
/*      */   static final int KPZ_LOGON_DBCONC = 512;
/*      */   
/*      */   static final int KPZ_PROXY_AUTH = 1024;
/*      */   
/*      */   static final int KPZ_SESSION_CACHE = 2048;
/*      */   
/*      */   static final int KPZ_PASSWD_IS_VFR = 4096;
/*      */   
/*      */   static final int KPZ_LOGON_SYSASM = 4194304;
/*      */   
/*      */   static final int KPZ_SESSION_QCACHE = 8388608;
/*      */   
/*      */   static final int KPZ_LOGON_SYSBKP = 16777216;
/*      */   
/*      */   static final int KPZ_LOGON_SYSDGD = 33554432;
/*      */   
/*      */   static final int KPZ_LOGON_SYSKMT = 67108864;
/*      */   
/*      */   static final String AUTH_TERMINAL = "AUTH_TERMINAL";
/*      */   
/*      */   static final String AUTH_PROGRAM_NM = "AUTH_PROGRAM_NM";
/*      */   
/*      */   static final String AUTH_MACHINE = "AUTH_MACHINE";
/*      */   
/*      */   static final String AUTH_PID = "AUTH_PID";
/*      */   
/*      */   static final String AUTH_SID = "AUTH_SID";
/*      */   
/*      */   static final String AUTH_SESSKEY = "AUTH_SESSKEY";
/*      */   
/*      */   static final String AUTH_VFR_DATA = "AUTH_VFR_DATA";
/*      */   
/*      */   static final String AUTH_PASSWORD = "AUTH_PASSWORD";
/*      */   
/*      */   static final String AUTH_INTERNALNAME = "AUTH_INTERNALNAME_";
/*      */   
/*      */   static final String AUTH_EXTERNALNAME = "AUTH_EXTERNALNAME_";
/*      */   
/*      */   static final String AUTH_ACL = "AUTH_ACL";
/*      */   
/*      */   static final String AUTH_ALTER_SESSION = "AUTH_ALTER_SESSION";
/*      */   
/*      */   static final String AUTH_INITIAL_CLIENT_ROLE = "INITIAL_CLIENT_ROLE";
/*      */   
/*      */   static final String AUTH_VERSION_SQL = "AUTH_VERSION_SQL";
/*      */   
/*      */   static final String AUTH_VERSION_NO = "AUTH_VERSION_NO";
/*      */   
/*      */   static final String AUTH_XACTION_TRAITS = "AUTH_XACTION_TRAITS";
/*      */   static final String AUTH_VERSION_STATUS = "AUTH_VERSION_STATUS";
/*      */   static final String AUTH_SERIAL_NUM = "AUTH_SERIAL_NUM";
/*      */   static final String AUTH_SESSION_ID = "AUTH_SESSION_ID";
/*      */   static final String AUTH_CLIENT_CERTIFICATE = "AUTH_CLIENT_CERTIFICATE";
/*      */   static final String AUTH_PROXY_CLIENT_NAME = "PROXY_CLIENT_NAME";
/*      */   static final String AUTH_CLIENT_DN = "AUTH_CLIENT_DISTINGUISHED_NAME";
/*      */   static final String AUTH_INSTANCENAME = "AUTH_INSTANCENAME";
/*      */   static final String AUTH_DBNAME = "AUTH_DBNAME";
/*      */   static final String AUTH_INSTANCE_NO = "AUTH_INSTANCE_NO";
/*      */   static final String AUTH_SC_SERVER_HOST = "AUTH_SC_SERVER_HOST";
/*      */   static final String AUTH_SC_INSTANCE_NAME = "AUTH_SC_INSTANCE_NAME";
/*      */   static final String AUTH_SC_INSTANCE_ID = "AUTH_SC_INSTANCE_ID";
/*      */   static final String AUTH_SC_INSTANCE_START_TIME = "AUTH_SC_INSTANCE_START_TIME";
/*      */   static final String AUTH_SC_DBUNIQUE_NAME = "AUTH_SC_DBUNIQUE_NAME";
/*      */   static final String AUTH_SC_SERVICE_NAME = "AUTH_SC_SERVICE_NAME";
/*      */   static final String AUTH_SC_SVC_FLAGS = "AUTH_SC_SVC_FLAGS";
/*      */   static final String AUTH_SESSION_CLIENT_CSET = "SESSION_CLIENT_CHARSET";
/*      */   static final String AUTH_SESSION_CLIENT_LTYPE = "SESSION_CLIENT_LIB_TYPE";
/*      */   static final String AUTH_SESSION_CLIENT_DRVNM = "SESSION_CLIENT_DRIVER_NAME";
/*      */   static final String AUTH_SESSION_CLIENT_VSN = "SESSION_CLIENT_VERSION";
/*      */   static final String AUTH_NLS_LXLAN = "AUTH_NLS_LXLAN";
/*      */   static final String AUTH_NLS_LXCTERRITORY = "AUTH_NLS_LXCTERRITORY";
/*      */   static final String AUTH_NLS_LXCCURRENCY = "AUTH_NLS_LXCCURRENCY";
/*      */   static final String AUTH_NLS_LXCISOCURR = "AUTH_NLS_LXCISOCURR";
/*      */   static final String AUTH_NLS_LXCNUMERICS = "AUTH_NLS_LXCNUMERICS";
/*      */   static final String AUTH_NLS_LXCDATEFM = "AUTH_NLS_LXCDATEFM";
/*      */   static final String AUTH_NLS_LXCDATELANG = "AUTH_NLS_LXCDATELANG";
/*      */   static final String AUTH_NLS_LXCSORT = "AUTH_NLS_LXCSORT";
/*      */   static final String AUTH_NLS_LXCCALENDAR = "AUTH_NLS_LXCCALENDAR";
/*      */   static final String AUTH_NLS_LXCUNIONCUR = "AUTH_NLS_LXCUNIONCUR";
/*      */   static final String AUTH_NLS_LXCTIMEFM = "AUTH_NLS_LXCTIMEFM";
/*      */   static final String AUTH_NLS_LXCSTMPFM = "AUTH_NLS_LXCSTMPFM";
/*      */   static final String AUTH_NLS_LXCTTZNFM = "AUTH_NLS_LXCTTZNFM";
/*      */   static final String AUTH_NLS_LXCSTZNFM = "AUTH_NLS_LXCSTZNFM";
/*      */   static final String SESSION_CLIENT_LOBATTR = "SESSION_CLIENT_LOBATTR";
/*      */   static final String DRIVER_NAME_DEFAULT = "jdbcthin";
/*      */   static final int KPU_LIB_UNKN = 0;
/*      */   static final int KPU_LIB_DEF = 1;
/*      */   static final int KPU_LIB_EI = 2;
/*      */   static final int KPU_LIB_XE = 3;
/*      */   static final int KPU_LIB_ICUS = 4;
/*      */   static final int KPU_LIB_OCI = 5;
/*      */   static final int KPU_LIB_THIN = 10;
/*      */   static final String AUTH_ORA_EDITION = "AUTH_ORA_EDITION";
/*      */   static final String AUTH_COPYRIGHT = "AUTH_COPYRIGHT";
/*      */   static final String COPYRIGHT_STR = "\"Oracle\nEverybody follows\nSpeedy bits exchange\nStars await to glow\"\nThe preceding key is copyrighted by Oracle Corporation.\nDuplication of this key is not allowed without permission\nfrom Oracle Corporation. Copyright 2003 Oracle Corporation.";
/*      */   static final String SESSION_TIME_ZONE = "SESSION_TIME_ZONE";
/*      */   static final String SESSION_NLS_LXCCHARSET = "SESSION_NLS_LXCCHARSET";
/*      */   static final String SESSION_NLS_LXCNLSLENSEM = "SESSION_NLS_LXCNLSLENSEM";
/*      */   static final String SESSION_NLS_LXCNCHAREXCP = "SESSION_NLS_LXCNCHAREXCP";
/*      */   static final String SESSION_NLS_LXCNCHARIMP = "SESSION_NLS_LXCNCHARIMP";
/*  227 */   String sessionTimeZone = null;
/*  228 */   byte[] serverCompileTimeCapabilities = null;
/*      */   
/*      */ 
/*      */   T4CTTIoauthenticate(T4CConnection paramT4CConnection, String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  234 */     super(paramT4CConnection, (byte)3);
/*      */     
/*  236 */     this.ressourceManagerId = paramString;
/*  237 */     this.serverCompileTimeCapabilities = paramArrayOfByte;
/*  238 */     setSessionFields(paramT4CConnection);
/*      */     
/*      */ 
/*      */ 
/*  242 */     this.isSessionTZ = true;
/*      */     
/*  244 */     this.bUseO5Logon = false;
/*      */   }
/*      */   
/*      */ 
/*  248 */   private T4CKvaldfList keyValList = null;
/*  249 */   private byte[] user = null;
/*      */   
/*      */   private long logonMode;
/*      */   
/*  253 */   private byte[][] outKeys = (byte[][])null;
/*  254 */   private byte[][] outValues = (byte[][])null;
/*  255 */   private int[] outFlags = new int[0];
/*  256 */   private int outNbPairs = 0;
/*      */   
/*      */ 
/*      */   void marshal()
/*      */     throws IOException
/*      */   {
/*  262 */     if ((this.user != null) && (this.user.length > 0))
/*      */     {
/*  264 */       this.meg.marshalPTR();
/*  265 */       this.meg.marshalSB4(this.user.length);
/*      */     }
/*      */     else
/*      */     {
/*  269 */       this.meg.marshalNULLPTR();
/*  270 */       this.meg.marshalSB4(0);
/*      */     }
/*  272 */     this.meg.marshalUB4(this.logonMode);
/*  273 */     this.meg.marshalPTR();
/*      */     
/*  275 */     this.meg.marshalUB4(this.keyValList.size());
/*  276 */     this.meg.marshalPTR();
/*  277 */     this.meg.marshalPTR();
/*      */     
/*      */ 
/*  280 */     if ((this.user != null) && (this.user.length > 0))
/*  281 */       this.meg.marshalCHR(this.user);
/*  282 */     this.meg.marshalKEYVAL(this.keyValList.getKeys(), this.keyValList.getValues(), this.keyValList.getFlags(), this.keyValList.size());
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
/*      */   private void doOAUTH(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, long paramLong, String paramString, boolean paramBoolean, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[][] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws IOException, SQLException
/*      */   {
/*  298 */     setFunCode((short)115);
/*      */     
/*  300 */     this.user = paramArrayOfByte1;
/*      */     
/*      */ 
/*  303 */     this.logonMode = (paramLong | 1L);
/*      */     
/*      */ 
/*  306 */     if (paramBoolean) {
/*  307 */       this.logonMode |= 0x400;
/*      */     }
/*  309 */     if ((paramArrayOfByte1 != null) && (paramArrayOfByte1.length != 0) && (paramArrayOfByte2 != null) && (paramString != "RADIUS"))
/*      */     {
/*      */ 
/*      */ 
/*  313 */       this.logonMode |= 0x100;
/*      */     }
/*  315 */     this.keyValList = new T4CKvaldfList(this.meg.conv);
/*      */     
/*  317 */     if (paramArrayOfByte2 != null) {
/*  318 */       this.keyValList.add("AUTH_PASSWORD", paramArrayOfByte2);
/*      */     }
/*  320 */     if (paramArrayOfByte != null) {
/*  321 */       for (int i = 0; i < paramArrayOfByte.length; i++)
/*  322 */         this.keyValList.add("INITIAL_CLIENT_ROLE", paramArrayOfByte[i]);
/*      */     }
/*  324 */     if (paramArrayOfByte3 != null) {
/*  325 */       this.keyValList.add("AUTH_CLIENT_DISTINGUISHED_NAME", paramArrayOfByte3);
/*      */     }
/*  327 */     if (paramArrayOfByte4 != null) {
/*  328 */       this.keyValList.add("AUTH_CLIENT_CERTIFICATE", paramArrayOfByte4);
/*      */     }
/*  330 */     this.keyValList.add("AUTH_TERMINAL", this.terminal);
/*      */     
/*  332 */     if ((this.bUseO5Logon) && (this.encryptedKB != null)) {
/*  333 */       this.keyValList.add("AUTH_SESSKEY", this.encryptedKB, (byte)1);
/*      */     }
/*  335 */     if (this.programName != null) {
/*  336 */       this.keyValList.add("AUTH_PROGRAM_NM", this.programName);
/*      */     }
/*  338 */     if (this.clientname != null) {
/*  339 */       this.keyValList.add("PROXY_CLIENT_NAME", this.clientname);
/*      */     }
/*  341 */     this.keyValList.add("AUTH_MACHINE", this.machine);
/*  342 */     this.keyValList.add("AUTH_PID", this.processID);
/*      */     
/*  344 */     if (!this.ressourceManagerId.equals("0000"))
/*      */     {
/*  346 */       byte[] arrayOfByte = this.meg.conv.StringToCharBytes("AUTH_INTERNALNAME_");
/*      */       
/*      */ 
/*  349 */       arrayOfByte[(arrayOfByte.length - 1)] = 0;
/*  350 */       this.keyValList.add(arrayOfByte, this.internalName);
/*      */       
/*  352 */       arrayOfByte = this.meg.conv.StringToCharBytes("AUTH_EXTERNALNAME_");
/*  353 */       arrayOfByte[(arrayOfByte.length - 1)] = 0;
/*  354 */       this.keyValList.add(arrayOfByte, this.externalName);
/*      */     }
/*      */     
/*  357 */     this.keyValList.add("AUTH_ACL", this.aclValue);
/*      */     
/*  359 */     this.keyValList.add("AUTH_ALTER_SESSION", this.alterSession, (byte)1);
/*      */     
/*  361 */     if (this.editionName != null) {
/*  362 */       this.keyValList.add("AUTH_ORA_EDITION", this.editionName);
/*      */     }
/*      */     
/*      */ 
/*  366 */     this.keyValList.add("SESSION_CLIENT_LOBATTR", this.enableTempLobRefCnt);
/*      */     
/*  368 */     this.keyValList.add("SESSION_CLIENT_DRIVER_NAME", this.driverName);
/*  369 */     this.keyValList.add("SESSION_CLIENT_VERSION", this.meg.conv.StringToCharBytes(Integer.toString(versionStringToInt(this.connection.getMetaData().getDriverVersion()), 10)));
/*      */     
/*  371 */     if (paramInt1 != -1) {
/*  372 */       this.keyValList.add("AUTH_SESSION_ID", this.meg.conv.StringToCharBytes(Integer.toString(paramInt1)));
/*      */     }
/*  374 */     if (paramInt2 != -1) {
/*  375 */       this.keyValList.add("AUTH_SERIAL_NUM", this.meg.conv.StringToCharBytes(Integer.toString(paramInt2)));
/*      */     }
/*  377 */     this.keyValList.add("AUTH_COPYRIGHT", this.meg.conv.StringToCharBytes("\"Oracle\nEverybody follows\nSpeedy bits exchange\nStars await to glow\"\nThe preceding key is copyrighted by Oracle Corporation.\nDuplication of this key is not allowed without permission\nfrom Oracle Corporation. Copyright 2003 Oracle Corporation."));
/*      */     
/*  379 */     this.outNbPairs = 0;
/*  380 */     this.outKeys = ((byte[][])null);
/*  381 */     this.outValues = ((byte[][])null);
/*  382 */     this.outFlags = new int[0];
/*  383 */     doRPC();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void doOSESSKEY(String paramString, long paramLong)
/*      */     throws IOException, SQLException
/*      */   {
/*  392 */     setFunCode((short)118);
/*  393 */     this.user = this.meg.conv.StringToCharBytes(paramString);
/*  394 */     this.logonMode = (paramLong | 1L);
/*      */     
/*  396 */     this.keyValList = new T4CKvaldfList(this.meg.conv);
/*  397 */     this.keyValList.add("AUTH_TERMINAL", this.terminal);
/*  398 */     if (this.programName != null)
/*  399 */       this.keyValList.add("AUTH_PROGRAM_NM", this.programName);
/*  400 */     this.keyValList.add("AUTH_MACHINE", this.machine);
/*  401 */     this.keyValList.add("AUTH_PID", this.processID);
/*  402 */     this.keyValList.add("AUTH_SID", this.sysUserName);
/*  403 */     this.outNbPairs = 0;
/*  404 */     this.outKeys = ((byte[][])null);
/*  405 */     this.outValues = ((byte[][])null);
/*  406 */     this.outFlags = new int[0];
/*  407 */     doRPC();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void readRPA()
/*      */     throws IOException, SQLException
/*      */   {
/*  419 */     this.outNbPairs = this.meg.unmarshalUB2();
/*      */     
/*  421 */     this.outKeys = new byte[this.outNbPairs][];
/*  422 */     this.outValues = new byte[this.outNbPairs][];
/*      */     
/*      */ 
/*  425 */     this.outFlags = this.meg.unmarshalKEYVAL(this.outKeys, this.outValues, this.outNbPairs);
/*      */   }
/*      */   
/*      */ 
/*      */   void processError()
/*      */     throws SQLException
/*      */   {
/*  432 */     if (getFunCode() == 118)
/*      */     {
/*  434 */       if ((this.oer.getRetCode() != 28035) || (this.connection.net.getAuthenticationAdaptorName() != "RADIUS"))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  444 */         this.oer.processError();
/*      */       }
/*      */     }
/*      */     else {
/*  448 */       super.processError();
/*      */     }
/*      */   }
/*      */   
/*      */   protected void processRPA() throws SQLException {
/*      */     Object localObject;
/*      */     String str3;
/*  455 */     if (getFunCode() == 115)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  461 */       localObject = new Properties();
/*      */       
/*  463 */       for (int j = 0; j < this.outNbPairs; j++)
/*      */       {
/*  465 */         String str2 = this.meg.conv.CharBytesToString(this.outKeys[j], this.outKeys[j].length).trim();
/*  466 */         str3 = "";
/*  467 */         if (this.outValues[j] != null) {
/*  468 */           str3 = this.meg.conv.CharBytesToString(this.outValues[j], this.outValues[j].length).trim();
/*      */         }
/*  470 */         ((Properties)localObject).setProperty(str2, str3);
/*      */       }
/*      */       
/*  473 */       String str1 = ((Properties)localObject).getProperty("AUTH_VERSION_NO");
/*      */       
/*      */ 
/*  476 */       if (str1 != null)
/*      */       {
/*      */         try
/*      */         {
/*  480 */           int m = new Integer(str1).intValue();
/*      */         }
/*      */         catch (NumberFormatException localNumberFormatException) {}
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  488 */       ((Properties)localObject).setProperty("SERVER_HOST", ((Properties)localObject).getProperty("AUTH_SC_SERVER_HOST", ""));
/*      */       
/*      */ 
/*      */ 
/*  492 */       ((Properties)localObject).setProperty("INSTANCE_NAME", ((Properties)localObject).getProperty("AUTH_SC_INSTANCE_NAME", ""));
/*      */       
/*      */ 
/*      */ 
/*  496 */       ((Properties)localObject).setProperty("DATABASE_NAME", ((Properties)localObject).getProperty("AUTH_SC_DBUNIQUE_NAME", ""));
/*      */       
/*      */ 
/*      */ 
/*  500 */       ((Properties)localObject).setProperty("SERVICE_NAME", ((Properties)localObject).getProperty("AUTH_SC_SERVICE_NAME", ""));
/*      */       
/*      */ 
/*      */ 
/*  504 */       ((Properties)localObject).setProperty("SESSION_TIME_ZONE", this.sessionTimeZone);
/*      */       
/*  506 */       this.connection.sessionProperties = ((Properties)localObject);
/*      */     }
/*  508 */     else if (getFunCode() == 118)
/*      */     {
/*  510 */       if (this.connection.net.getAuthenticationAdaptorName() != "RADIUS")
/*      */       {
/*      */ 
/*  513 */         if ((this.outKeys == null) || (this.outKeys.length < 1))
/*      */         {
/*  515 */           localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 438);
/*  516 */           ((SQLException)localObject).fillInStackTrace();
/*  517 */           throw ((Throwable)localObject);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  524 */         int i = -1;
/*  525 */         int k = -1;
/*      */         
/*      */         try
/*      */         {
/*  529 */           for (int n = 0; n < this.outKeys.length; n++)
/*      */           {
/*  531 */             str3 = new String(this.outKeys[n], "US-ASCII");
/*      */             
/*  533 */             if (str3.equals("AUTH_SESSKEY"))
/*      */             {
/*  535 */               i = n;
/*      */             }
/*  537 */             else if (str3.equals("AUTH_VFR_DATA"))
/*      */             {
/*  539 */               k = n;
/*      */             }
/*      */             
/*  542 */             if ((k != -1) && (i != -1)) {
/*      */               break;
/*      */             }
/*      */           }
/*      */         }
/*      */         catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
/*      */         
/*      */         SQLException localSQLException;
/*  550 */         if (i == -1)
/*      */         {
/*  552 */           localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 438);
/*  553 */           localSQLException.fillInStackTrace();
/*  554 */           throw localSQLException;
/*      */         }
/*      */         
/*  557 */         this.encryptedSK = this.outValues[i];
/*      */         
/*  559 */         if (k != -1)
/*      */         {
/*  561 */           this.bUseO5Logon = true;
/*  562 */           this.salt = this.outValues[k];
/*  563 */           this.verifierType = this.outFlags[k];
/*      */         }
/*      */         
/*  566 */         if (!this.bUseO5Logon)
/*      */         {
/*      */ 
/*  569 */           if ((this.encryptedSK == null) || (this.encryptedSK.length != 16))
/*      */           {
/*  571 */             localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 438);
/*  572 */             localSQLException.fillInStackTrace();
/*  573 */             throw localSQLException;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*  582 */   O5Logon o5logonHelper = new O5Logon();
/*      */   
/*      */   void doOAUTH(String paramString1, String paramString2, long paramLong) throws IOException, SQLException
/*      */   {
/*  586 */     byte[] arrayOfByte1 = null;
/*  587 */     if ((paramString1 != null) && (paramString1.length() > 0)) {
/*  588 */       arrayOfByte1 = this.meg.conv.StringToCharBytes(paramString1);
/*      */     }
/*  590 */     byte[] arrayOfByte2 = null;
/*  591 */     byte[] arrayOfByte3 = null;
/*  592 */     byte[] arrayOfByte4 = null;
/*      */     
/*  594 */     String str1 = this.connection.net.getAuthenticationAdaptorName();
/*      */     
/*      */     Object localObject1;
/*      */     Object localObject2;
/*  598 */     if ((paramString1 != null) && (paramString1.length() != 0))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  603 */       if ((str1 != "RADIUS") && (this.encryptedSK.length > 16) && (!this.bUseO5Logon))
/*      */       {
/*      */ 
/*      */ 
/*  607 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 413);
/*  608 */         ((SQLException)localObject1).fillInStackTrace();
/*  609 */         throw ((Throwable)localObject1);
/*      */       }
/*      */       
/*  612 */       if ((this.bUseO5Logon) && ((this.encryptedSK == null) || ((this.encryptedSK.length != 64) && (this.encryptedSK.length != 96))))
/*      */       {
/*      */ 
/*  615 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 413);
/*  616 */         ((SQLException)localObject1).fillInStackTrace();
/*  617 */         throw ((Throwable)localObject1);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  627 */       localObject1 = paramString1.trim();
/*  628 */       localObject2 = null;
/*  629 */       if (paramString2 != null)
/*      */       {
/*      */ 
/*  632 */         localObject2 = paramString2.trim();
/*      */       }
/*  634 */       paramString2 = null;
/*      */       
/*  636 */       Object localObject3 = localObject1;
/*  637 */       Object localObject4 = localObject2;
/*      */       
/*  639 */       if ((((String)localObject1).startsWith("\"")) || (((String)localObject1).endsWith("\""))) {
/*  640 */         localObject3 = removeQuotes((String)localObject1);
/*      */       }
/*  642 */       if ((localObject2 != null) && ((((String)localObject2).startsWith("\"")) || (((String)localObject2).endsWith("\""))))
/*      */       {
/*  644 */         localObject4 = removeQuotes((String)localObject2);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  650 */       if (localObject4 != null)
/*  651 */         arrayOfByte2 = this.meg.conv.StringToCharBytes((String)localObject4);
/*      */       Object localObject5;
/*  653 */       if (str1 != "RADIUS")
/*      */       {
/*  655 */         if (arrayOfByte2 == null)
/*      */         {
/*  657 */           arrayOfByte4 = null;
/*      */         } else { byte[] arrayOfByte5;
/*  659 */           if (this.bUseO5Logon)
/*      */           {
/*  661 */             this.encryptedKB = new byte[this.encryptedSK.length];
/*  662 */             for (int k = 0; k < this.encryptedKB.length; k++) this.encryptedKB[k] = 1;
/*  663 */             localObject5 = new int[1];
/*  664 */             arrayOfByte5 = new byte['Ā'];
/*  665 */             for (int n = 0; n < 256; n++) arrayOfByte5[n] = 0;
/*      */             try
/*      */             {
/*  668 */               this.o5logonHelper.generateOAuthResponse(this.verifierType, this.salt, (String)localObject3, (String)localObject4, arrayOfByte2, this.encryptedSK, this.encryptedKB, arrayOfByte5, (int[])localObject5, this.meg.conv.isServerCSMultiByte, this.serverCompileTimeCapabilities[4]);
/*      */             }
/*      */             catch (Exception localException2) {}
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  690 */             arrayOfByte4 = new byte[localObject5[0]];
/*  691 */             System.arraycopy(arrayOfByte5, 0, arrayOfByte4, 0, localObject5[0]);
/*      */           }
/*      */           else
/*      */           {
/*  695 */             localObject5 = new O3LoginClientHelper(this.meg.conv.isServerCSMultiByte);
/*      */             
/*  697 */             arrayOfByte5 = ((O3LoginClientHelper)localObject5).getSessionKey((String)localObject3, (String)localObject4, this.encryptedSK);
/*      */             
/*      */ 
/*      */             int i;
/*      */             
/*      */ 
/*  703 */             if (arrayOfByte2.length % 8 > 0) {
/*  704 */               i = (byte)(8 - arrayOfByte2.length % 8);
/*      */             } else {
/*  706 */               i = 0;
/*      */             }
/*  708 */             arrayOfByte3 = new byte[arrayOfByte2.length + i];
/*      */             
/*  710 */             System.arraycopy(arrayOfByte2, 0, arrayOfByte3, 0, arrayOfByte2.length);
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*  715 */             byte[] arrayOfByte6 = ((O3LoginClientHelper)localObject5).getEPasswd(arrayOfByte5, arrayOfByte3);
/*      */             
/*      */ 
/*  718 */             arrayOfByte4 = new byte[2 * arrayOfByte3.length + 1];
/*      */             
/*      */ 
/*  721 */             if (arrayOfByte4.length < 2 * arrayOfByte6.length)
/*      */             {
/*  723 */               SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 413);
/*  724 */               localSQLException.fillInStackTrace();
/*  725 */               throw localSQLException;
/*      */             }
/*      */             
/*  728 */             RepConversion.bArray2Nibbles(arrayOfByte6, arrayOfByte4);
/*      */             
/*      */ 
/*  731 */             arrayOfByte4[(arrayOfByte4.length - 1)] = RepConversion.nibbleToHex(i);
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/*  737 */       else if (arrayOfByte2 != null)
/*      */       {
/*  739 */         if ((this.connection.net.getSessionAttributes().getNTAdapter() instanceof TcpsNTAdapter))
/*      */         {
/*  741 */           arrayOfByte4 = arrayOfByte2;
/*      */         }
/*      */         else
/*      */         {
/*      */           int j;
/*      */           
/*      */ 
/*      */ 
/*  749 */           if ((arrayOfByte2.length + 1) % 8 > 0) {
/*  750 */             j = (byte)(8 - (arrayOfByte2.length + 1) % 8);
/*      */           } else {
/*  752 */             j = 0;
/*      */           }
/*  754 */           arrayOfByte3 = new byte[arrayOfByte2.length + 1 + j];
/*      */           
/*  756 */           System.arraycopy(arrayOfByte2, 0, arrayOfByte3, 0, arrayOfByte2.length);
/*  757 */           localObject5 = AuthenticationService.obfuscatePasswordForRadius(arrayOfByte3);
/*      */           
/*      */ 
/*  760 */           arrayOfByte4 = new byte[localObject5.length * 2];
/*      */           
/*  762 */           for (int i2 = 0; i2 < localObject5.length; i2++)
/*      */           {
/*  764 */             int m = (byte)((localObject5[i2] & 0xF0) >> 4);
/*  765 */             int i1 = (byte)(localObject5[i2] & 0xF);
/*  766 */             arrayOfByte4[(i2 * 2)] = ((byte)(m < 10 ? m + 48 : m - 10 + 97));
/*      */             
/*  768 */             arrayOfByte4[(i2 * 2 + 1)] = ((byte)(i1 < 10 ? i1 + 48 : i1 - 10 + 97));
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  776 */     doOAUTH(arrayOfByte1, arrayOfByte4, paramLong, str1, false, null, null, (byte[][])null, -1, -1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  787 */     if ((str1 != "RADIUS") && (this.bUseO5Logon))
/*      */     {
/*  789 */       String str2 = this.connection.sessionProperties.getProperty("AUTH_SVR_RESPONSE");
/*      */       try {
/*  791 */         if (!this.o5logonHelper.validateServerIdentity(str2))
/*      */         {
/*      */ 
/*  794 */           localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 452);
/*  795 */           ((SQLException)localObject1).fillInStackTrace();
/*  796 */           throw ((Throwable)localObject1);
/*      */         }
/*      */       }
/*      */       catch (Exception localException1)
/*      */       {
/*  801 */         localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 452);
/*  802 */         ((SQLException)localObject2).fillInStackTrace();
/*  803 */         throw ((Throwable)localObject2);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void doOAUTH(int paramInt1, Properties paramProperties, int paramInt2, int paramInt3)
/*      */     throws IOException, SQLException
/*      */   {
/*  815 */     byte[] arrayOfByte1 = null;
/*  816 */     byte[] arrayOfByte2 = null;
/*  817 */     String[] arrayOfString = null;
/*  818 */     byte[][] arrayOfByte = (byte[][])null;
/*  819 */     byte[] arrayOfByte3 = null;
/*      */     Object localObject;
/*  821 */     String str; if (paramInt1 == 1)
/*      */     {
/*  823 */       localObject = paramProperties.getProperty("PROXY_USER_NAME");
/*  824 */       str = paramProperties.getProperty("PROXY_USER_PASSWORD");
/*  825 */       if (str != null)
/*  826 */         localObject = (String)localObject + "/" + str;
/*  827 */       arrayOfByte3 = this.meg.conv.StringToCharBytes((String)localObject);
/*      */     }
/*  829 */     else if (paramInt1 == 2)
/*      */     {
/*  831 */       localObject = paramProperties.getProperty("PROXY_DISTINGUISHED_NAME");
/*      */       
/*      */ 
/*  834 */       arrayOfByte1 = this.meg.conv.StringToCharBytes((String)localObject);
/*      */     }
/*      */     else
/*      */     {
/*      */       try
/*      */       {
/*  840 */         arrayOfByte2 = (byte[])paramProperties.get("PROXY_CERTIFICATE");
/*      */         
/*  842 */         localObject = new StringBuffer();
/*      */         
/*      */ 
/*      */ 
/*  846 */         for (int k = 0; k < arrayOfByte2.length; k++)
/*      */         {
/*  848 */           str = Integer.toHexString(0xFF & arrayOfByte2[k]);
/*  849 */           int j = str.length();
/*      */           
/*  851 */           if (j == 0) {
/*  852 */             ((StringBuffer)localObject).append("00");
/*  853 */           } else if (j == 1)
/*      */           {
/*  855 */             ((StringBuffer)localObject).append('0');
/*  856 */             ((StringBuffer)localObject).append(str);
/*      */           }
/*      */           else {
/*  859 */             ((StringBuffer)localObject).append(str);
/*      */           }
/*      */         }
/*  862 */         arrayOfByte2 = ((StringBuffer)localObject).toString().getBytes();
/*      */       }
/*      */       catch (Exception localException1) {}
/*      */     }
/*      */     
/*      */ 
/*      */     try
/*      */     {
/*  870 */       arrayOfString = (String[])paramProperties.get("PROXY_ROLES");
/*      */     }
/*      */     catch (Exception localException2) {}
/*      */     
/*  874 */     if (arrayOfString != null)
/*      */     {
/*  876 */       arrayOfByte = new byte[arrayOfString.length][];
/*      */       
/*  878 */       for (int i = 0; i < arrayOfString.length; i++) {
/*  879 */         arrayOfByte[i] = this.meg.conv.StringToCharBytes(arrayOfString[i]);
/*      */       }
/*      */     }
/*  882 */     doOAUTH(arrayOfByte3, null, 0L, null, true, arrayOfByte1, arrayOfByte2, arrayOfByte, paramInt2, paramInt3);
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
/*      */   private void setSessionFields(T4CConnection paramT4CConnection)
/*      */     throws SQLException
/*      */   {
/*  906 */     String str1 = this.connection.thinVsessionTerminal;
/*  907 */     String str2 = this.connection.thinVsessionMachine;
/*  908 */     String str3 = this.connection.thinVsessionOsuser;
/*  909 */     String str4 = this.connection.thinVsessionProgram;
/*  910 */     String str5 = this.connection.thinVsessionProcess;
/*  911 */     String str6 = this.connection.thinVsessionIname;
/*  912 */     String str7 = this.connection.thinVsessionEname;
/*  913 */     String str8 = this.connection.proxyClientName;
/*  914 */     String str9 = this.connection.driverNameAttribute;
/*  915 */     String str10 = this.connection.editionName;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  928 */       if (this.connection.enableTempLobRefCnt) {
/*  929 */         this.enableTempLobRefCnt = new String("1").getBytes("US-ASCII");
/*      */       }
/*      */       else {
/*  932 */         this.enableTempLobRefCnt = new String("0").getBytes("US-ASCII");
/*      */       }
/*      */     }
/*      */     catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  940 */     if (str2 == null)
/*      */     {
/*      */       try
/*      */       {
/*      */ 
/*  945 */         str2 = InetAddress.getLocalHost().getHostName();
/*      */       }
/*      */       catch (Exception localException)
/*      */       {
/*  949 */         str2 = "jdbcclient";
/*      */       }
/*      */     }
/*      */     
/*  953 */     if (str7 == null) {
/*  954 */       str7 = "jdbc_" + this.ressourceManagerId;
/*      */     }
/*  956 */     if (str9 == null) {
/*  957 */       str9 = "jdbcthin";
/*      */     }
/*      */     
/*  960 */     this.terminal = this.meg.conv.StringToCharBytes(str1);
/*  961 */     this.machine = this.meg.conv.StringToCharBytes(str2);
/*  962 */     this.sysUserName = this.meg.conv.StringToCharBytes(str3);
/*  963 */     this.programName = this.meg.conv.StringToCharBytes(str4);
/*  964 */     this.processID = this.meg.conv.StringToCharBytes(str5);
/*  965 */     this.internalName = this.meg.conv.StringToCharBytes(str6);
/*  966 */     this.externalName = this.meg.conv.StringToCharBytes(str7);
/*  967 */     if (str8 != null)
/*  968 */       this.clientname = this.meg.conv.StringToCharBytes(str8);
/*  969 */     if (str10 != null)
/*  970 */       this.editionName = this.meg.conv.StringToCharBytes(str10);
/*  971 */     this.driverName = this.meg.conv.StringToCharBytes(str9);
/*      */     
/*  973 */     TimeZone localTimeZone = TimeZone.getDefault();
/*      */     
/*      */ 
/*  976 */     String str11 = localTimeZone.getID();
/*      */     
/*  978 */     if ((!ZONEIDMAP.isValidRegion(str11)) || (!paramT4CConnection.timezoneAsRegion))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  987 */       int i = localTimeZone.getOffset(System.currentTimeMillis());
/*  988 */       int j = i / 3600000;
/*  989 */       int k = i / 60000 % 60;
/*      */       
/*  991 */       str11 = (j < 0 ? "" + j : new StringBuilder().append("+").append(j).toString()) + (k < 10 ? ":0" + k : new StringBuilder().append(":").append(k).toString());
/*      */     }
/*      */     
/*      */ 
/*  995 */     this.sessionTimeZone = str11;
/*  996 */     paramT4CConnection.sessionTimeZone = str11;
/*      */     
/*  998 */     String str12 = CharacterSetMetaData.getNLSLanguage(ClassRef.LOCALE.getDefault());
/*      */     
/* 1000 */     String str13 = CharacterSetMetaData.getNLSTerritory(ClassRef.LOCALE.getDefault());
/*      */     
/*      */ 
/* 1003 */     if ((str12 == null) || (str13 == null))
/*      */     {
/* 1005 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 176);
/* 1006 */       localSQLException.fillInStackTrace();
/* 1007 */       throw localSQLException;
/*      */     }
/*      */     
/* 1010 */     this.alterSession = this.meg.conv.StringToCharBytes("ALTER SESSION SET " + (this.isSessionTZ ? "TIME_ZONE='" + this.sessionTimeZone + "'" : "") + " NLS_LANGUAGE='" + str12 + "' NLS_TERRITORY='" + str13 + "' ");
/*      */     
/*      */ 
/*      */ 
/* 1014 */     this.aclValue = this.meg.conv.StringToCharBytes("4400");
/* 1015 */     this.alterSession[(this.alterSession.length - 1)] = 0;
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
/*      */   String removeQuotes(String paramString)
/*      */   {
/* 1029 */     int i = 0;int j = paramString.length() - 1;
/*      */     
/* 1031 */     for (int k = 0; k < paramString.length(); k++)
/*      */     {
/* 1033 */       if (paramString.charAt(k) != '"')
/*      */       {
/* 1035 */         i = k;
/*      */         
/* 1037 */         break;
/*      */       }
/*      */     }
/*      */     
/* 1041 */     for (k = paramString.length() - 1; k >= 0; k--)
/*      */     {
/* 1043 */       if (paramString.charAt(k) != '"')
/*      */       {
/* 1045 */         j = k;
/*      */         
/* 1047 */         break;
/*      */       }
/*      */     }
/*      */     
/* 1051 */     String str = paramString.substring(i, j + 1);
/*      */     
/* 1053 */     return str;
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
/*      */   private int versionStringToInt(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1080 */     String[] arrayOfString = paramString.split("\\.");
/* 1081 */     int i = Integer.parseInt(arrayOfString[0].replaceAll("\\D", ""));
/* 1082 */     int j = Integer.parseInt(arrayOfString[1].replaceAll("\\D", ""));
/* 1083 */     int k = Integer.parseInt(arrayOfString[2].replaceAll("\\D", ""));
/* 1084 */     int m = Integer.parseInt(arrayOfString[3].replaceAll("\\D", ""));
/* 1085 */     int n = Integer.parseInt(arrayOfString[4].replaceAll("\\D", ""));
/* 1086 */     int i1 = i << 24 | j << 20 | k << 12 | m << 8 | n;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1091 */     return i1;
/*      */   }
/*      */   
/*      */ 
/*      */   private String versionIntToString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1098 */     int i = (paramInt & 0xFF000000) >> 24 & 0xFF;
/* 1099 */     int j = (paramInt & 0xF00000) >> 20 & 0xFF;
/* 1100 */     int k = (paramInt & 0xFF000) >> 12 & 0xFF;
/* 1101 */     int m = (paramInt & 0xF00) >> 8 & 0xFF;
/* 1102 */     int n = paramInt & 0xFF;
/* 1103 */     String str = "" + i + "." + j + "." + k + "." + m + "." + n;
/* 1104 */     return str;
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
/*      */   protected OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 1119 */     return this.connection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1124 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIoauthenticate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */