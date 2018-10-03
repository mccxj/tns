/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.io.Writer;
/*      */ import java.net.SocketException;
/*      */ import java.sql.Connection;
/*      */ import java.sql.SQLException;
/*      */ import java.util.Collection;
/*      */ import java.util.EnumSet;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Properties;
/*      */ import java.util.concurrent.Executor;
/*      */ import java.util.zip.CRC32;
/*      */ import oracle.jdbc.NotificationRegistration.RegistrationState;
/*      */ import oracle.jdbc.OracleConnection.CommitOption;
/*      */ import oracle.jdbc.OracleConnection.DatabaseShutdownMode;
/*      */ import oracle.jdbc.OracleConnection.DatabaseStartupMode;
/*      */ import oracle.jdbc.aq.AQDequeueOptions;
/*      */ import oracle.jdbc.aq.AQEnqueueOptions;
/*      */ import oracle.jdbc.internal.KeywordValue;
/*      */ import oracle.jdbc.internal.KeywordValueLong;
/*      */ import oracle.jdbc.internal.OracleConnection.InstanceProperty;
/*      */ import oracle.jdbc.internal.OracleConnection.TransactionState;
/*      */ import oracle.jdbc.internal.OracleConnection.XSOperationCode;
/*      */ import oracle.jdbc.internal.XSEventListener;
/*      */ import oracle.jdbc.internal.XSNamespace;
/*      */ import oracle.jdbc.pool.OraclePooledConnection;
/*      */ import oracle.net.ns.Communication;
/*      */ import oracle.net.ns.NSProtocol;
/*      */ import oracle.net.ns.NetException;
/*      */ import oracle.net.ns.SessionAtts;
/*      */ import oracle.sql.BFILE;
/*      */ import oracle.sql.BLOB;
/*      */ import oracle.sql.BfileDBAccess;
/*      */ import oracle.sql.BlobDBAccess;
/*      */ import oracle.sql.CLOB;
/*      */ import oracle.sql.ClobDBAccess;
/*      */ import oracle.sql.LobPlsqlUtil;
/*      */ import oracle.sql.TIMESTAMPTZ;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class T4CConnection
/*      */   extends PhysicalConnection
/*      */   implements BfileDBAccess, BlobDBAccess, ClobDBAccess
/*      */ {
/*      */   static final short MIN_TTCVER_SUPPORTED = 4;
/*      */   static final short V8_TTCVER_SUPPORTED = 5;
/*      */   static final short MAX_TTCVER_SUPPORTED = 6;
/*      */   static final int DEFAULT_LONG_PREFETCH_SIZE = 4080;
/*      */   static final String DEFAULT_CONNECT_STRING = "localhost:1521:orcl";
/*      */   static final int STREAM_CHUNK_SIZE = 255;
/*      */   static final int REFCURSOR_SIZE = 5;
/*   94 */   long LOGON_MODE = 0L;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean isLoggedOn;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean useZeroCopyIO;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean useLobPrefetch;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private String password;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Communication net;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   int eocs;
/*      */   
/*      */ 
/*      */ 
/*  128 */   private NTFEventListener[] xsListeners = new NTFEventListener[0];
/*      */   
/*      */   boolean readAsNonStream;
/*      */   
/*      */   T4CTTIoer oer;
/*      */   
/*      */   T4CMAREngine mare;
/*      */   
/*      */   T4C8TTIpro pro;
/*      */   
/*      */   T4CTTIrxd rxd;
/*      */   
/*      */   T4CTTIsto sto;
/*      */   
/*      */   T4CTTIspfp spfp;
/*      */   
/*      */   T4CTTIoauthenticate auth;
/*      */   
/*      */   T4C8Odscrarr describe;
/*      */   
/*      */   T4C8Oall all8;
/*      */   
/*      */   T4C8Oclose close8;
/*      */   T4C7Ocommoncall commoncall;
/*      */   T4Caqe aqe;
/*      */   T4Caqdq aqdq;
/*      */   T4C8TTIBfile bfileMsg;
/*      */   T4C8TTIBlob blobMsg;
/*      */   T4C8TTIClob clobMsg;
/*      */   T4CTTIoses oses;
/*      */   T4CTTIoping oping;
/*      */   T4CTTIokpn okpn;
/*  160 */   byte[] EMPTY_BYTE = new byte[0];
/*      */   
/*      */   T4CTTIOtxen otxen;
/*      */   
/*      */   T4CTTIOtxse otxse;
/*      */   
/*      */   T4CTTIk2rpc k2rpc;
/*      */   
/*      */   T4CTTIoscid oscid;
/*      */   
/*      */   T4CTTIokeyval okeyval;
/*      */   
/*      */   T4CTTIoxsscs oxsscs;
/*      */   
/*      */   T4CTTIoxssro oxssro;
/*      */   
/*      */   T4CTTIoxsspo oxsspo;
/*      */   
/*      */   T4CTTIxsnsop xsnsop;
/*      */   
/*      */   int[] cursorToClose;
/*      */   
/*      */   int cursorToCloseOffset;
/*      */   
/*      */   int lastCursorToCloseOffset;
/*      */   
/*      */   int[] queryToClose;
/*      */   
/*      */   int queryToCloseOffset;
/*      */   
/*      */   int[] lusFunctionId2;
/*      */   
/*      */   byte[][] lusSessionId2;
/*      */   
/*      */   KeywordValueLong[][] lusInKeyVal2;
/*      */   
/*      */   int[] lusInFlags2;
/*      */   
/*      */   int lusOffset2;
/*      */   
/*      */   int sessionId;
/*      */   
/*      */   int serialNumber;
/*      */   
/*      */   byte negotiatedTTCversion;
/*      */   
/*      */   byte[] serverRuntimeCapabilities;
/*      */   
/*      */   byte[] serverCompileTimeCapabilities;
/*      */   
/*      */   Hashtable namespaces;
/*      */   
/*      */   byte[] internalName;
/*      */   
/*      */   byte[] externalName;
/*      */   static final int FREE = -1;
/*      */   static final int SEND = 1;
/*      */   static final int RECEIVE = 2;
/*  218 */   int pipeState = -1;
/*  219 */   boolean sentCancel = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   String currentSchema;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  230 */   boolean cancelInProgressFlag = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  237 */   boolean statementCancel = true;
/*      */   
/*      */ 
/*  240 */   byte currentTTCSeqNumber = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   T4CConnection(String paramString, Properties paramProperties, OracleDriverExtension paramOracleDriverExtension)
/*      */     throws SQLException
/*      */   {
/*  254 */     super(paramString, paramProperties, paramOracleDriverExtension);
/*      */     
/*  256 */     this.cursorToClose = new int[4];
/*  257 */     this.cursorToCloseOffset = 0;
/*      */     
/*      */ 
/*  260 */     this.queryToClose = new int[10];
/*  261 */     this.queryToCloseOffset = 0;
/*      */     
/*  263 */     this.lusFunctionId2 = new int[10];
/*  264 */     this.lusSessionId2 = new byte[10][];
/*  265 */     this.lusInKeyVal2 = new KeywordValueLong[10][];
/*  266 */     this.lusInFlags2 = new int[10];
/*  267 */     this.lusOffset2 = 0;
/*      */     
/*  269 */     this.minVcsBindSize = 0;
/*  270 */     this.streamChunkSize = 255;
/*      */     
/*  272 */     this.namespaces = new Hashtable(5);
/*      */     
/*  274 */     this.currentSchema = null;
/*      */   }
/*      */   
/*      */   final void initializePassword(String paramString)
/*      */     throws SQLException
/*      */   {
/*  280 */     this.password = paramString;
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
/*      */   void logon()
/*      */     throws SQLException
/*      */   {
/*  311 */     Object localObject1 = null;
/*      */     
/*      */ 
/*      */     try
/*      */     {
/*  316 */       if (this.isLoggedOn)
/*      */       {
/*      */ 
/*  319 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 428);
/*  320 */         localSQLException1.fillInStackTrace();
/*  321 */         throw localSQLException1;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  328 */       if (this.database == null)
/*      */       {
/*  330 */         this.database = "localhost:1521:orcl";
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  340 */       connect(this.database);
/*      */       
/*  342 */       this.all8 = new T4C8Oall(this);
/*  343 */       this.okpn = new T4CTTIokpn(this);
/*  344 */       this.close8 = new T4C8Oclose(this);
/*  345 */       this.sto = new T4CTTIsto(this);
/*  346 */       this.spfp = new T4CTTIspfp(this);
/*  347 */       this.commoncall = new T4C7Ocommoncall(this);
/*  348 */       this.describe = new T4C8Odscrarr(this);
/*  349 */       this.bfileMsg = new T4C8TTIBfile(this);
/*  350 */       this.blobMsg = new T4C8TTIBlob(this);
/*  351 */       this.clobMsg = new T4C8TTIClob(this);
/*  352 */       this.otxen = new T4CTTIOtxen(this);
/*  353 */       this.otxse = new T4CTTIOtxse(this);
/*  354 */       this.oping = new T4CTTIoping(this);
/*  355 */       this.k2rpc = new T4CTTIk2rpc(this);
/*  356 */       this.oses = new T4CTTIoses(this);
/*  357 */       this.okeyval = new T4CTTIokeyval(this);
/*  358 */       this.oxssro = new T4CTTIoxssro(this);
/*  359 */       this.oxsspo = new T4CTTIoxsspo(this);
/*  360 */       this.oxsscs = new T4CTTIoxsscs(this);
/*  361 */       this.xsnsop = new T4CTTIxsnsop(this);
/*  362 */       this.aqe = new T4Caqe(this);
/*  363 */       this.aqdq = new T4Caqdq(this);
/*  364 */       this.oscid = new T4CTTIoscid(this);
/*      */       
/*      */ 
/*  367 */       this.LOGON_MODE = 0L;
/*  368 */       if (this.internalLogon != null)
/*      */       {
/*      */ 
/*      */ 
/*  372 */         if (this.internalLogon.equalsIgnoreCase("sysoper"))
/*      */         {
/*  374 */           this.LOGON_MODE = 64L;
/*      */         }
/*  376 */         else if (this.internalLogon.equalsIgnoreCase("sysdba"))
/*      */         {
/*  378 */           this.LOGON_MODE = 32L;
/*      */         }
/*  380 */         else if (this.internalLogon.equalsIgnoreCase("sysasm"))
/*      */         {
/*  382 */           this.LOGON_MODE = 4194304L;
/*      */         }
/*  384 */         else if (this.internalLogon.equalsIgnoreCase("sysbackup"))
/*      */         {
/*  386 */           this.LOGON_MODE = 16777216L;
/*      */         }
/*  388 */         else if (this.internalLogon.equalsIgnoreCase("sysdg"))
/*      */         {
/*  390 */           this.LOGON_MODE = 33554432L;
/*      */         }
/*  392 */         else if (this.internalLogon.equalsIgnoreCase("syskm"))
/*      */         {
/*  394 */           this.LOGON_MODE = 67108864L;
/*      */         }
/*      */       }
/*      */       
/*  398 */       if (this.prelimAuth) {
/*  399 */         this.LOGON_MODE |= 0x80;
/*      */       }
/*  401 */       this.auth = new T4CTTIoauthenticate(this, this.resourceManagerId, this.serverCompileTimeCapabilities);
/*      */       
/*  403 */       if ((this.userName != null) && (this.userName.length() != 0))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         try
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  416 */           this.auth.doOSESSKEY(this.userName, this.LOGON_MODE);
/*      */         }
/*      */         catch (SQLException localSQLException2)
/*      */         {
/*  420 */           if (localSQLException2.getErrorCode() == 1017)
/*      */           {
/*  422 */             localObject1 = localSQLException2;
/*  423 */             this.userName = null;
/*      */           }
/*      */           else
/*      */           {
/*  427 */             throw localSQLException2;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  432 */       this.auth.doOAUTH(this.userName, this.password, this.LOGON_MODE);
/*      */       
/*  434 */       this.sessionId = getSessionId();
/*  435 */       this.serialNumber = getSerialNumber();
/*  436 */       this.internalName = this.auth.internalName;
/*  437 */       this.externalName = this.auth.externalName;
/*  438 */       this.instanceName = this.sessionProperties.getProperty("AUTH_INSTANCENAME");
/*      */       
/*  440 */       if (!this.prelimAuth)
/*      */       {
/*      */ 
/*  443 */         T4C7Oversion localT4C7Oversion = new T4C7Oversion(this);
/*  444 */         localT4C7Oversion.doOVERSION();
/*      */         
/*  446 */         localObject2 = localT4C7Oversion.getVersion();
/*      */         try
/*      */         {
/*  449 */           this.databaseProductVersion = new String((byte[])localObject2, "UTF8");
/*      */ 
/*      */ 
/*      */         }
/*      */         catch (UnsupportedEncodingException localUnsupportedEncodingException)
/*      */         {
/*      */ 
/*  456 */           SQLException localSQLException4 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localUnsupportedEncodingException);
/*  457 */           localSQLException4.fillInStackTrace();
/*  458 */           throw localSQLException4;
/*      */         }
/*      */         
/*      */ 
/*  462 */         this.versionNumber = localT4C7Oversion.getVersionNumber();
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*  469 */         this.versionNumber = 0;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  478 */       this.isLoggedOn = true;
/*      */       
/*      */ 
/*      */ 
/*  482 */       if (getVersionNumber() < 11000) {
/*  483 */         this.enableTempLobRefCnt = false;
/*      */       }
/*      */     }
/*      */     catch (NetException localNetException)
/*      */     {
/*  488 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localNetException);
/*  489 */       ((SQLException)localObject2).fillInStackTrace();
/*  490 */       throw ((Throwable)localObject2);
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*  495 */       handleIOException(localIOException);
/*      */       
/*  497 */       Object localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  498 */       ((SQLException)localObject2).fillInStackTrace();
/*  499 */       throw ((Throwable)localObject2);
/*      */ 
/*      */ 
/*      */     }
/*      */     catch (SQLException localSQLException3)
/*      */     {
/*      */ 
/*  506 */       if (localObject1 != null)
/*      */       {
/*  508 */         localSQLException3.initCause((Throwable)localObject1);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       try
/*      */       {
/*  516 */         this.net.disconnect();
/*      */       }
/*      */       catch (Exception localException) {}
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  523 */       this.isLoggedOn = false;
/*      */       
/*  525 */       throw localSQLException3;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void handleIOException(IOException paramIOException)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/*  535 */       this.pipeState = -1;
/*  536 */       this.net.disconnect();
/*  537 */       this.net = null;
/*      */     }
/*      */     catch (Exception localException) {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  544 */     this.isLoggedOn = false;
/*  545 */     this.lifecycle = 4;
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
/*      */   synchronized void logoff()
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/*  569 */       assertLoggedOn("T4CConnection.logoff");
/*  570 */       if (this.lifecycle == 8) {
/*      */         return;
/*      */       }
/*  573 */       sendPiggyBackedMessages();
/*  574 */       this.commoncall.doOLOGOFF();
/*  575 */       this.net.disconnect();
/*  576 */       this.net = null;
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*  581 */       handleIOException(localIOException);
/*      */       
/*  583 */       if (this.lifecycle != 8)
/*      */       {
/*      */ 
/*  586 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  587 */         localSQLException.fillInStackTrace();
/*  588 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*      */       try
/*      */       {
/*  597 */         if (this.net != null) {
/*  598 */           this.net.disconnect();
/*      */         }
/*      */       }
/*      */       catch (Exception localException4) {}
/*      */       
/*      */ 
/*      */ 
/*  605 */       this.isLoggedOn = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   T4CMAREngine getMarshalEngine()
/*      */   {
/*  614 */     return this.mare;
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
/*      */   synchronized void doCommit(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  635 */     assertLoggedOn("T4CConnection.do_commit");
/*      */     try
/*      */     {
/*  638 */       sendPiggyBackedMessages();
/*  639 */       if (paramInt == 0)
/*      */       {
/*  641 */         this.commoncall.doOCOMMIT();
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
/*      */ 
/*  659 */         int i = 0;
/*  660 */         if ((paramInt & OracleConnection.CommitOption.WRITEBATCH.getCode()) != 0) {
/*  661 */           i = i | 0x2 | 0x1;
/*      */         }
/*  663 */         else if ((paramInt & OracleConnection.CommitOption.WRITEIMMED.getCode()) != 0) {
/*  664 */           i |= 0x2;
/*      */         }
/*  666 */         if ((paramInt & OracleConnection.CommitOption.NOWAIT.getCode()) != 0) {
/*  667 */           i = i | 0x8 | 0x4;
/*      */         }
/*  669 */         else if ((paramInt & OracleConnection.CommitOption.WAIT.getCode()) != 0) {
/*  670 */           i |= 0x8;
/*      */         }
/*  672 */         this.otxen.doOTXEN(1, null, null, 0, 0, 0, 0, 4, i);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  681 */         int j = this.otxen.getOutStateFromServer();
/*      */         
/*  683 */         if ((j == 2) || (j == 4)) {}
/*      */       }
/*      */       
/*      */ 
/*      */       SQLException localSQLException;
/*      */       
/*      */       return;
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*  693 */       handleIOException(localIOException);
/*      */       
/*  695 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  696 */       localSQLException.fillInStackTrace();
/*  697 */       throw localSQLException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   synchronized void doRollback()
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/*  723 */       assertLoggedOn("T4CConnection.do_rollback");
/*  724 */       sendPiggyBackedMessages();
/*  725 */       this.commoncall.doOROLLBACK();
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*  730 */       handleIOException(localIOException);
/*      */       
/*  732 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  733 */       localSQLException.fillInStackTrace();
/*  734 */       throw localSQLException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   synchronized void doSetAutoCommit(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  758 */     this.autocommit = paramBoolean;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void open(OracleStatement paramOracleStatement)
/*      */     throws SQLException
/*      */   {
/*  795 */     assertLoggedOn("T4CConnection.open");
/*  796 */     paramOracleStatement.setCursorId(0);
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
/*      */   synchronized String doGetDatabaseProductVersion()
/*      */     throws SQLException
/*      */   {
/*  810 */     assertLoggedOn("T4CConnection.do_getDatabaseProductVersion");
/*      */     
/*  812 */     T4C7Oversion localT4C7Oversion = new T4C7Oversion(this);
/*      */     try
/*      */     {
/*  815 */       localT4C7Oversion.doOVERSION();
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*  820 */       handleIOException(localIOException);
/*      */       
/*  822 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  823 */       ((SQLException)localObject).fillInStackTrace();
/*  824 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  828 */     String str = null;
/*      */     
/*  830 */     Object localObject = localT4C7Oversion.getVersion();
/*      */     try
/*      */     {
/*  833 */       str = new String((byte[])localObject, "UTF8");
/*      */ 
/*      */ 
/*      */     }
/*      */     catch (UnsupportedEncodingException localUnsupportedEncodingException)
/*      */     {
/*      */ 
/*  840 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localUnsupportedEncodingException);
/*  841 */       localSQLException.fillInStackTrace();
/*  842 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  846 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   synchronized short doGetVersionNumber()
/*      */     throws SQLException
/*      */   {
/*  859 */     assertLoggedOn("T4CConnection.do_getVersionNumber");
/*      */     
/*  861 */     T4C7Oversion localT4C7Oversion = new T4C7Oversion(this);
/*      */     try
/*      */     {
/*  864 */       localT4C7Oversion.doOVERSION();
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*  869 */       handleIOException(localIOException);
/*      */       
/*  871 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  872 */       localSQLException.fillInStackTrace();
/*  873 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  877 */     return localT4C7Oversion.getVersionNumber();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   OracleStatement RefCursorBytesToStatement(byte[] paramArrayOfByte, OracleStatement paramOracleStatement)
/*      */     throws SQLException
/*      */   {
/*  885 */     T4CStatement localT4CStatement = new T4CStatement(this, -1, -1);
/*      */     
/*      */     try
/*      */     {
/*  889 */       int i = this.mare.unmarshalRefCursor(paramArrayOfByte);
/*      */       
/*  891 */       localT4CStatement.setCursorId(i);
/*      */       
/*  893 */       localT4CStatement.isOpen = true;
/*      */       
/*      */ 
/*      */ 
/*  897 */       localT4CStatement.sqlObject = paramOracleStatement.sqlObject;
/*      */       
/*  899 */       localT4CStatement.serverCursor = true;
/*  900 */       paramOracleStatement.addChild(localT4CStatement);
/*  901 */       localT4CStatement.prepareForNewResults(true, false);
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*  906 */       handleIOException(localIOException);
/*      */       
/*  908 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  909 */       localSQLException.fillInStackTrace();
/*  910 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  915 */     localT4CStatement.sqlStringChanged = false;
/*  916 */     localT4CStatement.needToParse = false;
/*      */     
/*  918 */     return localT4CStatement;
/*      */   }
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
/*  931 */     synchronized (this.cancelInProgressLockForThin)
/*      */     {
/*  933 */       if (!this.cancelInProgressFlag)
/*      */       {
/*      */         try
/*      */         {
/*  937 */           switch (this.pipeState)
/*      */           {
/*      */ 
/*      */           case -1: 
/*  941 */             return;
/*      */           case 1: 
/*  943 */             this.net.sendBreak();
/*  944 */             break;
/*      */           case 2: 
/*  946 */             this.net.sendInterrupt();
/*      */           }
/*      */           
/*  949 */           this.sentCancel = true;
/*      */ 
/*      */         }
/*      */         catch (NetException localNetException)
/*      */         {
/*      */ 
/*  955 */           localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localNetException);
/*  956 */           localSQLException.fillInStackTrace();
/*  957 */           throw localSQLException;
/*      */ 
/*      */ 
/*      */         }
/*      */         catch (IOException localIOException)
/*      */         {
/*      */ 
/*  964 */           handleIOException(localIOException);
/*      */           
/*  966 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  967 */           localSQLException.fillInStackTrace();
/*  968 */           throw localSQLException;
/*      */         }
/*      */         
/*      */ 
/*  972 */         this.cancelInProgressFlag = true;
/*  973 */         this.statementCancel = paramBoolean;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void connect(String paramString)
/*      */     throws IOException, SQLException
/*      */   {
/* 1016 */     if (paramString == null)
/*      */     {
/*      */ 
/*      */ 
/* 1020 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 433);
/* 1021 */       ((SQLException)localObject).fillInStackTrace();
/* 1022 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1027 */     Object localObject = new Properties();
/* 1028 */     if (this.thinNetProfile != null)
/* 1029 */       ((Properties)localObject).setProperty("oracle.net.profile", this.thinNetProfile);
/* 1030 */     if (this.thinNetAuthenticationServices != null) {
/* 1031 */       ((Properties)localObject).setProperty("oracle.net.authentication_services", this.thinNetAuthenticationServices);
/*      */     }
/* 1033 */     if (this.thinNetAuthenticationKrb5Mutual != null) {
/* 1034 */       ((Properties)localObject).setProperty("oracle.net.kerberos5_mutual_authentication", this.thinNetAuthenticationKrb5Mutual);
/*      */     }
/* 1036 */     if (this.thinNetAuthenticationKrb5CcName != null) {
/* 1037 */       ((Properties)localObject).setProperty("oracle.net.kerberos5_cc_name", this.thinNetAuthenticationKrb5CcName);
/*      */     }
/* 1039 */     if (this.thinNetEncryptionLevel != null) {
/* 1040 */       ((Properties)localObject).setProperty("oracle.net.encryption_client", this.thinNetEncryptionLevel);
/*      */     }
/* 1042 */     if (this.thinNetEncryptionTypes != null) {
/* 1043 */       ((Properties)localObject).setProperty("oracle.net.encryption_types_client", this.thinNetEncryptionTypes);
/*      */     }
/* 1045 */     if (this.thinNetChecksumLevel != null) {
/* 1046 */       ((Properties)localObject).setProperty("oracle.net.crypto_checksum_client", this.thinNetChecksumLevel);
/*      */     }
/* 1048 */     if (this.thinNetChecksumTypes != null) {
/* 1049 */       ((Properties)localObject).setProperty("oracle.net.crypto_checksum_types_client", this.thinNetChecksumTypes);
/*      */     }
/* 1051 */     if (this.thinNetCryptoSeed != null)
/* 1052 */       ((Properties)localObject).setProperty("oracle.net.crypto_seed", this.thinNetCryptoSeed);
/* 1053 */     if (this.thinTcpNoDelay)
/* 1054 */       ((Properties)localObject).setProperty("TCP.NODELAY", "YES");
/* 1055 */     if (this.thinReadTimeout != null) {
/* 1056 */       ((Properties)localObject).setProperty("oracle.net.READ_TIMEOUT", this.thinReadTimeout);
/*      */     }
/* 1058 */     if (this.thinNetConnectTimeout != null) {
/* 1059 */       ((Properties)localObject).setProperty("oracle.net.CONNECT_TIMEOUT", this.thinNetConnectTimeout);
/*      */     }
/* 1061 */     if (this.thinSslServerDnMatch != null) {
/* 1062 */       ((Properties)localObject).setProperty("oracle.net.ssl_server_dn_match", this.thinSslServerDnMatch);
/*      */     }
/* 1064 */     if (this.walletLocation != null) {
/* 1065 */       ((Properties)localObject).setProperty("oracle.net.wallet_location", this.walletLocation);
/*      */     }
/* 1067 */     if (this.walletPassword != null) {
/* 1068 */       ((Properties)localObject).setProperty("oracle.net.wallet_password", this.walletPassword);
/*      */     }
/* 1070 */     if (this.thinSslVersion != null) {
/* 1071 */       ((Properties)localObject).setProperty("oracle.net.ssl_version", this.thinSslVersion);
/*      */     }
/* 1073 */     if (this.thinSslCipherSuites != null) {
/* 1074 */       ((Properties)localObject).setProperty("oracle.net.ssl_cipher_suites", this.thinSslCipherSuites);
/*      */     }
/* 1076 */     if (this.thinJavaxNetSslKeystore != null) {
/* 1077 */       ((Properties)localObject).setProperty("javax.net.ssl.keyStore", this.thinJavaxNetSslKeystore);
/*      */     }
/* 1079 */     if (this.thinJavaxNetSslKeystoretype != null) {
/* 1080 */       ((Properties)localObject).setProperty("javax.net.ssl.keyStoreType", this.thinJavaxNetSslKeystoretype);
/*      */     }
/* 1082 */     if (this.thinJavaxNetSslKeystorepassword != null) {
/* 1083 */       ((Properties)localObject).setProperty("javax.net.ssl.keyStorePassword", this.thinJavaxNetSslKeystorepassword);
/*      */     }
/* 1085 */     if (this.thinJavaxNetSslTruststore != null) {
/* 1086 */       ((Properties)localObject).setProperty("javax.net.ssl.trustStore", this.thinJavaxNetSslTruststore);
/*      */     }
/* 1088 */     if (this.thinJavaxNetSslTruststoretype != null) {
/* 1089 */       ((Properties)localObject).setProperty("javax.net.ssl.trustStoreType", this.thinJavaxNetSslTruststoretype);
/*      */     }
/* 1091 */     if (this.thinJavaxNetSslTruststorepassword != null) {
/* 1092 */       ((Properties)localObject).setProperty("javax.net.ssl.trustStorePassword", this.thinJavaxNetSslTruststorepassword);
/*      */     }
/* 1094 */     if (this.thinSslKeymanagerfactoryAlgorithm != null) {
/* 1095 */       ((Properties)localObject).setProperty("ssl.keyManagerFactory.algorithm", this.thinSslKeymanagerfactoryAlgorithm);
/*      */     }
/* 1097 */     if (this.thinSslTrustmanagerfactoryAlgorithm != null) {
/* 1098 */       ((Properties)localObject).setProperty("ssl.trustManagerFactory.algorithm", this.thinSslTrustmanagerfactoryAlgorithm);
/*      */     }
/* 1100 */     if (this.thinNetOldsyntax != null)
/* 1101 */       ((Properties)localObject).setProperty("oracle.net.oldSyntax", this.thinNetOldsyntax);
/* 1102 */     if (this.thinNamingContextInitial != null)
/* 1103 */       ((Properties)localObject).setProperty("java.naming.factory.initial", this.thinNamingContextInitial);
/* 1104 */     if (this.thinNamingProviderUrl != null)
/* 1105 */       ((Properties)localObject).setProperty("java.naming.provider.url", this.thinNamingProviderUrl);
/* 1106 */     if (this.thinNamingSecurityAuthentication != null) {
/* 1107 */       ((Properties)localObject).setProperty("java.naming.security.authentication", this.thinNamingSecurityAuthentication);
/*      */     }
/* 1109 */     if (this.thinNamingSecurityPrincipal != null) {
/* 1110 */       ((Properties)localObject).setProperty("java.naming.security.principal", this.thinNamingSecurityPrincipal);
/*      */     }
/* 1112 */     if (this.thinNamingSecurityCredentials != null) {
/* 1113 */       ((Properties)localObject).setProperty("java.naming.security.credentials", this.thinNamingSecurityCredentials);
/*      */     }
/* 1115 */     if (this.thinNetDisableOutOfBandBreak) {
/* 1116 */       ((Properties)localObject).setProperty("DISABLE_OOB", "" + this.thinNetDisableOutOfBandBreak);
/*      */     }
/* 1118 */     if (this.thinNetEnableSDP) {
/* 1119 */       ((Properties)localObject).setProperty("oracle.net.SDP", "" + this.thinNetEnableSDP);
/*      */     }
/* 1121 */     ((Properties)localObject).setProperty("USE_ZERO_COPY_IO", "" + this.thinNetUseZeroCopyIO);
/*      */     
/* 1123 */     ((Properties)localObject).setProperty("FORCE_DNS_LOAD_BALANCING", "" + this.thinForceDnsLoadBalancing);
/*      */     
/* 1125 */     ((Properties)localObject).setProperty("ENABLE_JAVANET_FASTPATH", "" + this.enableJavaNetFastPath);
/*      */     
/*      */ 
/* 1128 */     ((Properties)localObject).setProperty("oracle.jdbc.v$session.osuser", this.thinVsessionOsuser);
/* 1129 */     ((Properties)localObject).setProperty("oracle.jdbc.v$session.program", this.thinVsessionProgram);
/* 1130 */     ((Properties)localObject).setProperty("T4CConnection.hashCode", Integer.toHexString(hashCode()).toUpperCase());
/*      */     
/*      */ 
/* 1133 */     ((Properties)localObject).setProperty("oracle.net.keepAlive", Boolean.toString(this.keepAlive));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1138 */     this.net = new NSProtocol();
/*      */     
/* 1140 */     this.net.connect(paramString, (Properties)localObject);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1150 */     this.mare = new T4CMAREngine(this.net, this.enableJavaNetFastPath);
/* 1151 */     this.oer = new T4CTTIoer(this);
/* 1152 */     this.mare.setConnectionDuringExceptionHandling(this);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1158 */     this.pro = new T4C8TTIpro(this);
/*      */     
/* 1160 */     this.pro.marshal();
/*      */     
/* 1162 */     this.serverCompileTimeCapabilities = this.pro.receive();
/* 1163 */     this.serverRuntimeCapabilities = this.pro.getServerRuntimeCapabilities();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1168 */     short s1 = this.pro.getOracleVersion();
/* 1169 */     short s2 = this.pro.getCharacterSet();
/* 1170 */     short s3 = DBConversion.findDriverCharSet(s2, s1);
/*      */     
/*      */ 
/* 1173 */     this.conversion = new DBConversion(s2, s3, this.pro.getncharCHARSET(), this.isStrictAsciiConversion, this.isQuickAsciiConversion);
/*      */     
/*      */ 
/* 1176 */     this.mare.types.setServerConversion(s3 != s2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1181 */     if (DBConversion.isCharSetMultibyte(s3))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1187 */       if (DBConversion.isCharSetMultibyte(this.pro.getCharacterSet())) {
/* 1188 */         this.mare.types.setFlags((byte)1);
/*      */       } else {
/* 1190 */         this.mare.types.setFlags((byte)2);
/*      */       }
/*      */     } else {
/* 1193 */       this.mare.types.setFlags(this.pro.getFlags());
/*      */     }
/* 1195 */     this.mare.conv = this.conversion;
/*      */     
/*      */ 
/*      */ 
/* 1199 */     T4C8TTIdty localT4C8TTIdty = new T4C8TTIdty(this, this.serverCompileTimeCapabilities, this.serverRuntimeCapabilities, (this.logonCap != null) && (this.logonCap.trim().equals("o3")), this.thinNetUseZeroCopyIO);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1204 */     localT4C8TTIdty.doRPC();
/*      */     
/*      */ 
/*      */ 
/* 1208 */     this.negotiatedTTCversion = this.serverCompileTimeCapabilities[7];
/* 1209 */     if (localT4C8TTIdty.jdbcThinCompileTimeCapabilities[7] < this.serverCompileTimeCapabilities[7]) {
/* 1210 */       this.negotiatedTTCversion = localT4C8TTIdty.jdbcThinCompileTimeCapabilities[7];
/*      */     }
/*      */     
/*      */ 
/* 1214 */     if ((this.serverRuntimeCapabilities != null) && (this.serverRuntimeCapabilities.length > 6) && ((this.serverRuntimeCapabilities[6] & T4C8TTIdty.KPCCAP_RTB_TTC_ZCPY) != 0) && (this.thinNetUseZeroCopyIO) && ((this.net.getSessionAttributes().getNegotiatedOptions() & 0x40) != 0) && (getDataIntegrityAlgorithmName().equals("")) && (getEncryptionAlgorithmName().equals("")))
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
/* 1225 */       this.useZeroCopyIO = true;
/*      */     } else {
/* 1227 */       this.useZeroCopyIO = false;
/*      */     }
/*      */     
/*      */ 
/* 1231 */     if ((this.serverCompileTimeCapabilities.length > 23) && ((this.serverCompileTimeCapabilities[23] & 0x40) != 0) && ((localT4C8TTIdty.jdbcThinCompileTimeCapabilities[23] & 0x40) != 0))
/*      */     {
/*      */ 
/* 1234 */       this.useLobPrefetch = true;
/*      */     } else {
/* 1236 */       this.useLobPrefetch = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean isZeroCopyIOEnabled()
/*      */   {
/* 1247 */     return this.useZeroCopyIO;
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
/*      */   final T4CTTIoer getT4CTTIoer()
/*      */   {
/* 1266 */     return this.oer;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final byte getTTCVersion()
/*      */   {
/* 1276 */     return this.negotiatedTTCversion;
/*      */   }
/*      */   
/*      */ 
/*      */   void doStartup(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 1285 */       int i = 0;
/* 1286 */       if (paramInt == OracleConnection.DatabaseStartupMode.FORCE.getMode()) {
/* 1287 */         i = 16;
/* 1288 */       } else if (paramInt == OracleConnection.DatabaseStartupMode.RESTRICT.getMode())
/* 1289 */         i = 1;
/* 1290 */       this.spfp.doOSPFPPUT();
/* 1291 */       this.sto.doOV6STRT(i);
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 1295 */       handleIOException(localIOException);
/*      */       
/* 1297 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1298 */       localSQLException.fillInStackTrace();
/* 1299 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void doShutdown(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 1311 */       int i = 4;
/*      */       
/* 1313 */       if (paramInt == OracleConnection.DatabaseShutdownMode.TRANSACTIONAL.getMode()) {
/* 1314 */         i = 128;
/* 1315 */       } else if (paramInt == OracleConnection.DatabaseShutdownMode.TRANSACTIONAL_LOCAL.getMode()) {
/* 1316 */         i = 256;
/* 1317 */       } else if (paramInt == OracleConnection.DatabaseShutdownMode.IMMEDIATE.getMode()) {
/* 1318 */         i = 2;
/* 1319 */       } else if (paramInt == OracleConnection.DatabaseShutdownMode.FINAL.getMode()) {
/* 1320 */         i = 8;
/* 1321 */       } else if (paramInt == OracleConnection.DatabaseShutdownMode.ABORT.getMode()) {
/* 1322 */         i = 64;
/*      */       }
/* 1324 */       sendPiggyBackedMessages();
/* 1325 */       this.sto.doOV6STOP(i);
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 1329 */       handleIOException(localIOException);
/*      */       
/* 1331 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1332 */       localSQLException.fillInStackTrace();
/* 1333 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void sendPiggyBackedMessages()
/*      */     throws SQLException, IOException
/*      */   {
/* 1342 */     sendPiggyBackedClose();
/*      */     
/*      */ 
/*      */ 
/* 1346 */     if ((this.endToEndAnyChanged) && (getTTCVersion() >= 3))
/*      */     {
/*      */ 
/* 1349 */       this.oscid.doOSCID(this.endToEndHasChanged, this.endToEndValues, this.endToEndECIDSequenceNumber);
/*      */       
/*      */ 
/* 1352 */       for (int i = 0; i < 4; i++) {
/* 1353 */         if (this.endToEndHasChanged[i] != 0)
/* 1354 */           this.endToEndHasChanged[i] = false;
/*      */       }
/*      */     }
/* 1357 */     this.endToEndAnyChanged = false;
/*      */     
/* 1359 */     if (!this.namespaces.isEmpty())
/*      */     {
/* 1361 */       if (getTTCVersion() >= 4)
/*      */       {
/* 1363 */         Object[] arrayOfObject = this.namespaces.values().toArray();
/* 1364 */         for (int k = 0; k < arrayOfObject.length; k++)
/*      */         {
/* 1366 */           this.okeyval.doOKEYVAL((Namespace)arrayOfObject[k]);
/*      */         }
/*      */       }
/* 1369 */       this.namespaces.clear();
/*      */     }
/*      */     
/*      */ 
/* 1373 */     if (this.lusOffset2 > 0)
/*      */     {
/* 1375 */       for (int j = 0; j < this.lusOffset2; j++) {
/* 1376 */         this.oxsspo.doOXSSPO(this.lusFunctionId2[j], this.lusSessionId2[j], this.lusInKeyVal2[j], this.lusInFlags2[j]);
/*      */       }
/*      */       
/*      */ 
/* 1380 */       this.lusOffset2 = 0;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void sendPiggyBackedClose()
/*      */     throws SQLException, IOException
/*      */   {
/* 1391 */     if (this.queryToCloseOffset > 0)
/*      */     {
/* 1393 */       this.close8.doOCANA(this.queryToClose, this.queryToCloseOffset);
/* 1394 */       this.queryToCloseOffset = 0;
/*      */     }
/*      */     
/*      */ 
/* 1398 */     if (this.cursorToCloseOffset > 0)
/*      */     {
/* 1400 */       this.close8.doOCCA(this.cursorToClose, this.cursorToCloseOffset);
/* 1401 */       this.lastCursorToCloseOffset = this.cursorToCloseOffset;
/* 1402 */       this.cursorToCloseOffset = 0;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void redoCursorClose()
/*      */   {
/* 1410 */     if ((this.cursorToCloseOffset == 0) && (this.lastCursorToCloseOffset != 0)) {
/* 1411 */       this.cursorToCloseOffset = this.lastCursorToCloseOffset;
/* 1412 */       this.lastCursorToCloseOffset = 0;
/*      */     }
/*      */   }
/*      */   
/*      */   synchronized void closeCursor(int paramInt) throws SQLException
/*      */   {
/* 1418 */     if (this.cursorToCloseOffset == this.cursorToClose.length)
/*      */     {
/* 1420 */       int[] arrayOfInt = new int[this.cursorToClose.length * 2];
/* 1421 */       System.arraycopy(this.cursorToClose, 0, arrayOfInt, 0, this.cursorToClose.length);
/*      */       
/* 1423 */       this.cursorToClose = arrayOfInt;
/*      */     }
/* 1425 */     this.cursorToClose[(this.cursorToCloseOffset++)] = paramInt;
/*      */   }
/*      */   
/*      */   void doProxySession(int paramInt, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 1433 */       sendPiggyBackedMessages();
/*      */       
/*      */ 
/*      */ 
/* 1437 */       this.auth.doOAUTH(paramInt, paramProperties, this.sessionId, this.serialNumber);
/*      */       
/* 1439 */       int i = getSessionId();
/* 1440 */       int j = getSerialNumber();
/*      */       
/*      */ 
/* 1443 */       this.oses.doO80SES(i, j, 1);
/*      */       
/* 1445 */       this.savedUser = this.userName;
/*      */       
/* 1447 */       if (paramInt == 1) {
/* 1448 */         this.userName = paramProperties.getProperty("PROXY_USER_NAME");
/*      */       } else {
/* 1450 */         this.userName = null;
/*      */       }
/* 1452 */       this.isProxy = true;
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*      */ 
/* 1458 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1459 */       localSQLException.fillInStackTrace();
/* 1460 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void closeProxySession()
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 1472 */       sendPiggyBackedMessages();
/* 1473 */       this.commoncall.doOLOGOFF();
/*      */       
/* 1475 */       this.oses.doO80SES(this.sessionId, this.serialNumber, 1);
/*      */       
/* 1477 */       this.userName = this.savedUser;
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*      */ 
/* 1483 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1484 */       localSQLException.fillInStackTrace();
/* 1485 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void updateSessionProperties(KeywordValue[] paramArrayOfKeywordValue)
/*      */     throws SQLException
/*      */   {
/* 1495 */     for (int i = 0; i < paramArrayOfKeywordValue.length; i++)
/*      */     {
/* 1497 */       int j = paramArrayOfKeywordValue[i].getKeyword();
/* 1498 */       byte[] arrayOfByte = paramArrayOfKeywordValue[i].getBinaryValue();
/* 1499 */       if (j < T4C8Oall.NLS_KEYS.length)
/*      */       {
/* 1501 */         String str1 = T4C8Oall.NLS_KEYS[j];
/* 1502 */         if (str1 != null)
/*      */         {
/* 1504 */           if (arrayOfByte != null)
/*      */           {
/* 1506 */             this.sessionProperties.setProperty(str1, this.mare.conv.CharBytesToString(arrayOfByte, arrayOfByte.length));
/*      */ 
/*      */ 
/*      */           }
/* 1510 */           else if (paramArrayOfKeywordValue[i].getTextValue() != null)
/*      */           {
/* 1512 */             this.sessionProperties.setProperty(str1, paramArrayOfKeywordValue[i].getTextValue().trim());
/*      */           }
/*      */           
/*      */         }
/*      */       }
/* 1517 */       else if (j == 163)
/*      */       {
/* 1519 */         if (arrayOfByte != null)
/*      */         {
/*      */ 
/* 1522 */           int k = arrayOfByte[4];
/* 1523 */           int m = arrayOfByte[5];
/*      */           
/* 1525 */           if ((arrayOfByte[4] & 0xFF) > 120)
/*      */           {
/* 1527 */             k = (arrayOfByte[4] & 0xFF) - 181;
/* 1528 */             m = (arrayOfByte[5] & 0xFF) - 60;
/*      */           }
/*      */           else {
/* 1531 */             k = (arrayOfByte[4] & 0xFF) - 60;
/* 1532 */             m = (arrayOfByte[5] & 0xFF) - 60;
/*      */           }
/*      */           
/* 1535 */           String str3 = (k > 0 ? "+" : "") + k + (m <= 9 ? ":0" : ":") + m;
/*      */           
/*      */ 
/*      */ 
/*      */ 
/* 1540 */           this.sessionProperties.setProperty("SESSION_TIME_ZONE", str3);
/*      */         }
/*      */         
/*      */       }
/* 1544 */       else if (j != 165)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1557 */         if (j != 166)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1569 */           if (j != 167)
/*      */           {
/*      */ 
/*      */ 
/* 1573 */             if (j == 168)
/*      */             {
/* 1575 */               String str2 = paramArrayOfKeywordValue[i].getTextValue();
/* 1576 */               if (str2 != null)
/*      */               {
/* 1578 */                 this.currentSchema = str2.trim();
/*      */               }
/*      */             }
/* 1581 */             else if (j != 169)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1593 */               if (j != 170)
/*      */               {
/*      */ 
/* 1596 */                 if (j != 171) {}
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getCurrentSchema()
/*      */     throws SQLException
/*      */   {
/* 1612 */     if (this.lifecycle != 1)
/*      */     {
/* 1614 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 1615 */       localSQLException.fillInStackTrace();
/* 1616 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1621 */     if ((this.currentSchema == null) || (getVersionNumber() < 11100)) {
/* 1622 */       this.currentSchema = super.getCurrentSchema();
/*      */     }
/* 1624 */     return this.currentSchema;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Properties getServerSessionInfo()
/*      */     throws SQLException
/*      */   {
/* 1632 */     if ((getVersionNumber() >= 10000) && (getVersionNumber() < 10200))
/* 1633 */       queryFCFProperties(this.sessionProperties);
/* 1634 */     return this.sessionProperties;
/*      */   }
/*      */   
/*      */ 
/*      */   public String getSessionTimeZoneOffset()
/*      */     throws SQLException
/*      */   {
/* 1641 */     String str = getServerSessionInfo().getProperty("SESSION_TIME_ZONE");
/* 1642 */     if (str == null)
/*      */     {
/* 1644 */       str = super.getSessionTimeZoneOffset();
/*      */     }
/*      */     else
/*      */     {
/* 1648 */       str = tzToOffset(str);
/*      */     }
/* 1650 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   int getSessionId()
/*      */   {
/* 1657 */     int i = -1;
/* 1658 */     String str = this.sessionProperties.getProperty("AUTH_SESSION_ID");
/*      */     
/*      */     try
/*      */     {
/* 1662 */       i = Integer.parseInt(str);
/*      */     }
/*      */     catch (NumberFormatException localNumberFormatException) {}
/* 1665 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   int getSerialNumber()
/*      */   {
/* 1672 */     int i = -1;
/* 1673 */     String str = this.sessionProperties.getProperty("AUTH_SERIAL_NUM");
/*      */     
/*      */     try
/*      */     {
/* 1677 */       i = Integer.parseInt(str);
/*      */     }
/*      */     catch (NumberFormatException localNumberFormatException) {}
/* 1680 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte getInstanceProperty(OracleConnection.InstanceProperty paramInstanceProperty)
/*      */     throws SQLException
/*      */   {
/* 1689 */     byte b = 0;
/* 1690 */     SQLException localSQLException; if (paramInstanceProperty == OracleConnection.InstanceProperty.ASM_VOLUME_SUPPORTED)
/*      */     {
/*      */ 
/* 1693 */       if ((this.serverRuntimeCapabilities == null) || (this.serverRuntimeCapabilities.length < 6))
/*      */       {
/* 1695 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 256);
/* 1696 */         localSQLException.fillInStackTrace();
/* 1697 */         throw localSQLException;
/*      */       }
/*      */       
/* 1700 */       b = this.serverRuntimeCapabilities[5];
/*      */     }
/* 1702 */     else if (paramInstanceProperty == OracleConnection.InstanceProperty.INSTANCE_TYPE)
/*      */     {
/*      */ 
/* 1705 */       if ((this.serverRuntimeCapabilities == null) || (this.serverRuntimeCapabilities.length < 4))
/*      */       {
/* 1707 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 256);
/* 1708 */         localSQLException.fillInStackTrace();
/* 1709 */         throw localSQLException;
/*      */       }
/*      */       
/* 1712 */       b = this.serverRuntimeCapabilities[3];
/*      */     }
/*      */     
/* 1715 */     return b;
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
/*      */   public synchronized BlobDBAccess createBlobDBAccess()
/*      */     throws SQLException
/*      */   {
/* 1735 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public synchronized ClobDBAccess createClobDBAccess()
/*      */     throws SQLException
/*      */   {
/* 1743 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public synchronized BfileDBAccess createBfileDBAccess()
/*      */     throws SQLException
/*      */   {
/* 1751 */     return this;
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
/*      */   public synchronized long length(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 1772 */     assertLoggedOn("length");
/* 1773 */     assertNotNull(paramBFILE.shareBytes(), "length");
/*      */     
/* 1775 */     needLine();
/*      */     
/* 1777 */     long l = 0L;
/*      */     
/*      */     try
/*      */     {
/* 1781 */       l = this.bfileMsg.getLength(paramBFILE.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 1786 */       handleIOException(localIOException);
/*      */       
/* 1788 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1789 */       localSQLException.fillInStackTrace();
/* 1790 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1795 */     return l;
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
/*      */   public synchronized long position(BFILE paramBFILE, byte[] paramArrayOfByte, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1812 */     assertNotNull(paramBFILE.shareBytes(), "position");
/*      */     
/* 1814 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 1817 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 1818 */       localSQLException.fillInStackTrace();
/* 1819 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1824 */     long l = LobPlsqlUtil.hasPattern(paramBFILE, paramArrayOfByte, paramLong);
/*      */     
/* 1826 */     l = l == 0L ? -1L : l;
/*      */     
/* 1828 */     return l;
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
/*      */   public long position(BFILE paramBFILE1, BFILE paramBFILE2, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1845 */     assertNotNull(paramBFILE1.shareBytes(), "position");
/*      */     
/* 1847 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 1850 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 1851 */       localSQLException.fillInStackTrace();
/* 1852 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1857 */     long l = LobPlsqlUtil.isSubLob(paramBFILE1, paramBFILE2, paramLong);
/*      */     
/* 1859 */     l = l == 0L ? -1L : l;
/*      */     
/* 1861 */     return l;
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
/* 1877 */     assertLoggedOn("getBytes");
/* 1878 */     assertNotNull(paramBFILE.shareBytes(), "getBytes");
/*      */     
/*      */     SQLException localSQLException1;
/* 1881 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 1884 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "getBytes()");
/* 1885 */       localSQLException1.fillInStackTrace();
/* 1886 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1892 */     if ((paramInt <= 0) || (paramArrayOfByte == null)) {
/* 1893 */       return 0;
/*      */     }
/* 1895 */     if (this.pipeState != -1)
/*      */     {
/*      */ 
/* 1898 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 453, "getBytes()");
/* 1899 */       localSQLException1.fillInStackTrace();
/* 1900 */       throw localSQLException1;
/*      */     }
/*      */     
/* 1903 */     needLine();
/*      */     
/* 1905 */     long l = 0L;
/*      */     
/* 1907 */     if (paramInt != 0)
/*      */     {
/*      */       try
/*      */       {
/* 1911 */         l = this.bfileMsg.read(paramBFILE.shareBytes(), paramLong, paramInt, paramArrayOfByte, 0);
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/* 1916 */         handleIOException(localIOException);
/*      */         
/* 1918 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1919 */         localSQLException2.fillInStackTrace();
/* 1920 */         throw localSQLException2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1926 */     return (int)l;
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
/*      */   public String getName(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 1940 */     assertLoggedOn("getName");
/* 1941 */     assertNotNull(paramBFILE.shareBytes(), "getName");
/*      */     
/* 1943 */     String str = LobPlsqlUtil.fileGetName(paramBFILE);
/*      */     
/* 1945 */     return str;
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
/*      */   public String getDirAlias(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 1959 */     assertLoggedOn("getDirAlias");
/* 1960 */     assertNotNull(paramBFILE.shareBytes(), "getDirAlias");
/*      */     
/* 1962 */     String str = LobPlsqlUtil.fileGetDirAlias(paramBFILE);
/*      */     
/* 1964 */     return str;
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
/* 1977 */     assertLoggedOn("openFile");
/* 1978 */     assertNotNull(paramBFILE.shareBytes(), "openFile");
/*      */     
/* 1980 */     needLine();
/*      */     
/*      */     try
/*      */     {
/* 1984 */       this.bfileMsg.open(paramBFILE.shareBytes(), 11);
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 1989 */       handleIOException(localIOException);
/*      */       
/* 1991 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1992 */       localSQLException.fillInStackTrace();
/* 1993 */       throw localSQLException;
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
/*      */ 
/*      */ 
/*      */   public synchronized boolean isFileOpen(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 2012 */     assertLoggedOn("openFile");
/* 2013 */     assertNotNull(paramBFILE.shareBytes(), "openFile");
/*      */     
/* 2015 */     needLine();
/*      */     
/* 2017 */     boolean bool = false;
/*      */     
/*      */     try
/*      */     {
/* 2021 */       bool = this.bfileMsg.isOpen(paramBFILE.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2026 */       handleIOException(localIOException);
/*      */       
/* 2028 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2029 */       localSQLException.fillInStackTrace();
/* 2030 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2035 */     return bool;
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
/* 2051 */     assertLoggedOn("fileExists");
/* 2052 */     assertNotNull(paramBFILE.shareBytes(), "fileExists");
/*      */     
/* 2054 */     needLine();
/*      */     
/* 2056 */     boolean bool = false;
/*      */     
/*      */     try
/*      */     {
/* 2060 */       bool = this.bfileMsg.doesExist(paramBFILE.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2065 */       handleIOException(localIOException);
/*      */       
/* 2067 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2068 */       localSQLException.fillInStackTrace();
/* 2069 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2074 */     return bool;
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
/* 2087 */     assertLoggedOn("closeFile");
/* 2088 */     assertNotNull(paramBFILE.shareBytes(), "closeFile");
/*      */     
/* 2090 */     needLine();
/*      */     
/*      */     try
/*      */     {
/* 2094 */       this.bfileMsg.close(paramBFILE.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2099 */       handleIOException(localIOException);
/*      */       
/* 2101 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2102 */       localSQLException.fillInStackTrace();
/* 2103 */       throw localSQLException;
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
/*      */ 
/*      */   public synchronized void open(BFILE paramBFILE, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2121 */     assertLoggedOn("open");
/* 2122 */     assertNotNull(paramBFILE.shareBytes(), "open");
/*      */     
/* 2124 */     needLine();
/*      */     
/*      */     try
/*      */     {
/* 2128 */       this.bfileMsg.open(paramBFILE.shareBytes(), paramInt);
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2133 */       handleIOException(localIOException);
/*      */       
/* 2135 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2136 */       localSQLException.fillInStackTrace();
/* 2137 */       throw localSQLException;
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
/*      */   public synchronized void close(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 2151 */     assertLoggedOn("close");
/* 2152 */     assertNotNull(paramBFILE.shareBytes(), "close");
/*      */     
/* 2154 */     needLine();
/*      */     
/*      */     try
/*      */     {
/* 2158 */       this.bfileMsg.close(paramBFILE.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2163 */       handleIOException(localIOException);
/*      */       
/* 2165 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2166 */       localSQLException.fillInStackTrace();
/* 2167 */       throw localSQLException;
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
/*      */   public synchronized boolean isOpen(BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 2182 */     assertLoggedOn("isOpen");
/* 2183 */     assertNotNull(paramBFILE.shareBytes(), "isOpen");
/*      */     
/* 2185 */     needLine();
/*      */     
/* 2187 */     boolean bool = false;
/*      */     
/*      */     try
/*      */     {
/* 2191 */       bool = this.bfileMsg.isOpen(paramBFILE.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2196 */       handleIOException(localIOException);
/*      */       
/* 2198 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2199 */       localSQLException.fillInStackTrace();
/* 2200 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 2204 */     return bool;
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
/*      */   public InputStream newInputStream(BFILE paramBFILE, int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2223 */     if (paramLong == 0L)
/*      */     {
/* 2225 */       return new OracleBlobInputStream(paramBFILE, paramInt);
/*      */     }
/*      */     
/*      */ 
/* 2229 */     return new OracleBlobInputStream(paramBFILE, paramInt, paramLong);
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
/*      */   public InputStream newConversionInputStream(BFILE paramBFILE, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2248 */     assertNotNull(paramBFILE.shareBytes(), "newConversionInputStream");
/*      */     
/* 2250 */     OracleConversionInputStream localOracleConversionInputStream = new OracleConversionInputStream(this.conversion, paramBFILE.getBinaryStream(), paramInt);
/*      */     
/* 2252 */     return localOracleConversionInputStream;
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
/*      */   public Reader newConversionReader(BFILE paramBFILE, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2270 */     assertNotNull(paramBFILE.shareBytes(), "newConversionReader");
/*      */     
/* 2272 */     OracleConversionReader localOracleConversionReader = new OracleConversionReader(this.conversion, paramBFILE.getBinaryStream(), paramInt);
/*      */     
/*      */ 
/* 2275 */     return localOracleConversionReader;
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
/*      */   public synchronized long length(BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 2297 */     assertLoggedOn("length");
/* 2298 */     assertNotNull(paramBLOB.shareBytes(), "length");
/*      */     
/* 2300 */     needLine();
/*      */     
/* 2302 */     long l = 0L;
/*      */     
/*      */     try
/*      */     {
/* 2306 */       l = this.blobMsg.getLength(paramBLOB.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2311 */       handleIOException(localIOException);
/*      */       
/* 2313 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2314 */       localSQLException.fillInStackTrace();
/* 2315 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2320 */     return l;
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
/*      */   public long position(BLOB paramBLOB, byte[] paramArrayOfByte, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2337 */     assertLoggedOn("position");
/* 2338 */     assertNotNull(paramBLOB.shareBytes(), "position");
/*      */     
/*      */ 
/* 2341 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 2344 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 2345 */       localSQLException.fillInStackTrace();
/* 2346 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2351 */     long l = LobPlsqlUtil.hasPattern(paramBLOB, paramArrayOfByte, paramLong);
/*      */     
/* 2353 */     l = l == 0L ? -1L : l;
/*      */     
/* 2355 */     return l;
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
/*      */   public long position(BLOB paramBLOB1, BLOB paramBLOB2, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2371 */     assertLoggedOn("position");
/* 2372 */     assertNotNull(paramBLOB1.shareBytes(), "position");
/* 2373 */     assertNotNull(paramBLOB2.shareBytes(), "position");
/*      */     
/*      */ 
/* 2376 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 2379 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 2380 */       localSQLException.fillInStackTrace();
/* 2381 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2386 */     long l = LobPlsqlUtil.isSubLob(paramBLOB1, paramBLOB2, paramLong);
/*      */     
/* 2388 */     l = l == 0L ? -1L : l;
/*      */     
/* 2390 */     return l;
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
/* 2407 */     assertLoggedOn("getBytes");
/* 2408 */     assertNotNull(paramBLOB.shareBytes(), "getBytes");
/*      */     
/*      */     SQLException localSQLException1;
/* 2411 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 2414 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "getBytes()");
/* 2415 */       localSQLException1.fillInStackTrace();
/* 2416 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2423 */     if (this.pipeState != -1)
/*      */     {
/*      */ 
/* 2426 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 453, "getBytes()");
/* 2427 */       localSQLException1.fillInStackTrace();
/* 2428 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/* 2432 */     if ((paramInt <= 0) || (paramArrayOfByte == null)) {
/* 2433 */       return 0;
/*      */     }
/*      */     
/* 2436 */     long l1 = 0L;
/*      */     
/* 2438 */     long l2 = -1L;
/*      */     
/* 2440 */     if (paramBLOB.isActivePrefetch())
/*      */     {
/* 2442 */       byte[] arrayOfByte = paramBLOB.getPrefetchedData();
/* 2443 */       int i = paramBLOB.getPrefetchedDataSize();
/* 2444 */       l2 = paramBLOB.length();
/* 2445 */       int j = 0;
/*      */       
/* 2447 */       if (arrayOfByte != null) {
/* 2448 */         j = Math.min(i, arrayOfByte.length);
/*      */       }
/* 2450 */       if ((j > 0) && (paramLong <= j))
/*      */       {
/*      */ 
/*      */ 
/* 2454 */         int k = Math.min(j - (int)paramLong + 1, paramInt);
/*      */         
/*      */ 
/* 2457 */         System.arraycopy(arrayOfByte, (int)paramLong - 1, paramArrayOfByte, 0, k);
/* 2458 */         l1 += k;
/*      */       }
/*      */     }
/*      */     
/* 2462 */     if ((l1 < paramInt) && ((l2 == -1L) || (paramLong - 1L + l1 < l2)))
/*      */     {
/* 2464 */       needLine();
/*      */       
/*      */       try
/*      */       {
/* 2468 */         l1 += this.blobMsg.read(paramBLOB.shareBytes(), paramLong + l1, paramInt - l1, paramArrayOfByte, (int)l1);
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/*      */ 
/*      */ 
/* 2477 */         handleIOException(localIOException);
/*      */         
/* 2479 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2480 */         localSQLException2.fillInStackTrace();
/* 2481 */         throw localSQLException2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2488 */     return (int)l1;
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
/*      */   public synchronized int putBytes(BLOB paramBLOB, long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2508 */     assertLoggedOn("putBytes");
/* 2509 */     assertNotNull(paramBLOB.shareBytes(), "putBytes");
/*      */     
/*      */ 
/* 2512 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 2515 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "putBytes()");
/* 2516 */       localSQLException1.fillInStackTrace();
/* 2517 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2522 */     if ((paramArrayOfByte == null) || (paramInt2 <= 0)) {
/* 2523 */       return 0;
/*      */     }
/* 2525 */     needLine();
/*      */     
/* 2527 */     long l = 0L;
/*      */     
/* 2529 */     if (paramInt2 != 0)
/*      */     {
/*      */       try
/*      */       {
/*      */ 
/* 2534 */         paramBLOB.setActivePrefetch(false);
/* 2535 */         paramBLOB.clearCachedData();
/* 2536 */         l = this.blobMsg.write(paramBLOB.shareBytes(), paramLong, paramArrayOfByte, paramInt1, paramInt2);
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/*      */ 
/* 2542 */         handleIOException(localIOException);
/*      */         
/* 2544 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2545 */         localSQLException2.fillInStackTrace();
/* 2546 */         throw localSQLException2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2552 */     return (int)l;
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
/* 2564 */     assertLoggedOn("getChunkSize");
/* 2565 */     assertNotNull(paramBLOB.shareBytes(), "getChunkSize");
/*      */     
/* 2567 */     needLine();
/*      */     
/* 2569 */     long l = 0L;
/*      */     
/*      */     try
/*      */     {
/* 2573 */       l = this.blobMsg.getChunkSize(paramBLOB.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2578 */       handleIOException(localIOException);
/*      */       
/* 2580 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2581 */       localSQLException.fillInStackTrace();
/* 2582 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2587 */     return (int)l;
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
/* 2601 */     assertLoggedOn("trim");
/* 2602 */     assertNotNull(paramBLOB.shareBytes(), "trim");
/*      */     
/*      */ 
/* 2605 */     if (paramLong < 0L)
/*      */     {
/*      */ 
/* 2608 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "trim()");
/* 2609 */       localSQLException1.fillInStackTrace();
/* 2610 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2615 */     needLine();
/*      */     
/*      */ 
/*      */     try
/*      */     {
/* 2620 */       paramBLOB.setActivePrefetch(false);
/* 2621 */       paramBLOB.clearCachedData();
/* 2622 */       this.blobMsg.trim(paramBLOB.shareBytes(), paramLong);
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2627 */       handleIOException(localIOException);
/*      */       
/* 2629 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2630 */       localSQLException2.fillInStackTrace();
/* 2631 */       throw localSQLException2;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized BLOB createTemporaryBlob(Connection paramConnection, boolean paramBoolean, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2653 */     assertLoggedOn("createTemporaryBlob");
/*      */     
/* 2655 */     needLine();
/*      */     
/* 2657 */     BLOB localBLOB = null;
/*      */     
/*      */     try
/*      */     {
/* 2661 */       localBLOB = (BLOB)this.blobMsg.createTemporaryLob(this, paramBoolean, paramInt);
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2666 */       handleIOException(localIOException);
/*      */       
/* 2668 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2669 */       localSQLException.fillInStackTrace();
/* 2670 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2675 */     return localBLOB;
/*      */   }
/*      */   
/*      */ 
/* 2679 */   private final CRC32 checksumEngine = new CRC32();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2696 */   private final Hashtable<Long, Integer> tempLobRefCount = new Hashtable();
/*      */   static final int MAX_SIZE_VSESSION_OSUSER = 30;
/*      */   static final int MAX_SIZE_VSESSION_PROCESS = 24;
/*      */   
/*      */   private final synchronized Long getLocatorHash(byte[] paramArrayOfByte) {
/* 2701 */     this.checksumEngine.reset();
/*      */     
/* 2703 */     this.checksumEngine.update(paramArrayOfByte, 10, 10);
/* 2704 */     long l = this.checksumEngine.getValue();
/* 2705 */     Long localLong = Long.valueOf(l);
/*      */     
/* 2707 */     return localLong;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final synchronized int decrementTempLobReferenceCount(byte[] paramArrayOfByte)
/*      */   {
/* 2717 */     int i = 0;
/* 2718 */     if ((this.enableTempLobRefCnt) && (paramArrayOfByte != null) && (paramArrayOfByte.length == 40) && (((paramArrayOfByte[7] & 0x1) > 0) || ((paramArrayOfByte[4] & 0x40) > 0)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2724 */       Long localLong = getLocatorHash(paramArrayOfByte);
/* 2725 */       Integer localInteger = (Integer)this.tempLobRefCount.get(localLong);
/* 2726 */       if (localInteger != null)
/*      */       {
/* 2728 */         i = localInteger.intValue() - 1;
/* 2729 */         if (i == 0) {
/* 2730 */           this.tempLobRefCount.remove(localLong);
/*      */         } else {
/* 2732 */           this.tempLobRefCount.put(localLong, Integer.valueOf(i));
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2743 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */   public final synchronized void incrementTempLobReferenceCount(byte[] paramArrayOfByte)
/*      */   {
/* 2749 */     if ((this.enableTempLobRefCnt) && (paramArrayOfByte != null) && (paramArrayOfByte.length == 40) && (((paramArrayOfByte[7] & 0x1) > 0) || ((paramArrayOfByte[4] & 0x40) > 0)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2755 */       Long localLong = getLocatorHash(paramArrayOfByte);
/* 2756 */       Integer localInteger = (Integer)this.tempLobRefCount.get(localLong);
/*      */       
/* 2758 */       if (localInteger != null)
/*      */       {
/* 2760 */         int i = localInteger.intValue();
/*      */         
/* 2762 */         this.tempLobRefCount.put(localLong, Integer.valueOf(i + 1));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 2767 */         this.tempLobRefCount.put(localLong, Integer.valueOf(1));
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
/*      */   public synchronized void freeTemporary(BLOB paramBLOB, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 2784 */     assertLoggedOn("freeTemporary");
/* 2785 */     assertNotNull(paramBLOB.shareBytes(), "freeTemporary");
/*      */     
/* 2787 */     needLine();
/*      */     
/*      */     try
/*      */     {
/* 2791 */       this.blobMsg.freeTemporaryLob(paramBLOB.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2796 */       handleIOException(localIOException);
/*      */       
/* 2798 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2799 */       localSQLException.fillInStackTrace();
/* 2800 */       throw localSQLException;
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
/*      */ 
/*      */ 
/*      */   public boolean isTemporary(BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 2819 */     assertNotNull(paramBLOB.shareBytes(), "isTemporary");
/*      */     
/*      */ 
/*      */ 
/* 2823 */     boolean bool = false;
/* 2824 */     byte[] arrayOfByte = paramBLOB.shareBytes();
/*      */     
/* 2826 */     if (((arrayOfByte[7] & 0x1) > 0) || ((arrayOfByte[4] & 0x40) > 0)) {
/* 2827 */       bool = true;
/*      */     }
/* 2829 */     return bool;
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
/* 2842 */     assertLoggedOn("open");
/* 2843 */     assertNotNull(paramBLOB.shareBytes(), "open");
/*      */     
/* 2845 */     needLine();
/*      */     
/*      */     try
/*      */     {
/* 2849 */       this.blobMsg.open(paramBLOB.shareBytes(), paramInt);
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2854 */       handleIOException(localIOException);
/*      */       
/* 2856 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2857 */       localSQLException.fillInStackTrace();
/* 2858 */       throw localSQLException;
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
/*      */   public synchronized void close(BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 2873 */     assertLoggedOn("close");
/* 2874 */     assertNotNull(paramBLOB.shareBytes(), "close");
/*      */     
/* 2876 */     needLine();
/*      */     
/*      */     try
/*      */     {
/* 2880 */       this.blobMsg.close(paramBLOB.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2885 */       handleIOException(localIOException);
/*      */       
/* 2887 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2888 */       localSQLException.fillInStackTrace();
/* 2889 */       throw localSQLException;
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
/*      */   public synchronized boolean isOpen(BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 2905 */     assertLoggedOn("isOpen");
/* 2906 */     assertNotNull(paramBLOB.shareBytes(), "isOpen");
/*      */     
/* 2908 */     needLine();
/*      */     
/* 2910 */     boolean bool = false;
/*      */     
/*      */     try
/*      */     {
/* 2914 */       bool = this.blobMsg.isOpen(paramBLOB.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2919 */       handleIOException(localIOException);
/*      */       
/* 2921 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2922 */       localSQLException.fillInStackTrace();
/* 2923 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2928 */     return bool;
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
/* 2947 */     if (paramLong == 0L)
/*      */     {
/* 2949 */       return new OracleBlobInputStream(paramBLOB, paramInt);
/*      */     }
/*      */     
/*      */ 
/* 2953 */     return new OracleBlobInputStream(paramBLOB, paramInt, paramLong);
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
/* 2976 */     return new OracleBlobInputStream(paramBLOB, paramInt, paramLong1, paramLong2);
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
/*      */   public OutputStream newOutputStream(BLOB paramBLOB, int paramInt, long paramLong, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 2996 */     if (paramLong == 0L)
/*      */     {
/* 2998 */       if ((paramBoolean & this.lobStreamPosStandardCompliant))
/*      */       {
/*      */ 
/* 3001 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 3002 */         localSQLException.fillInStackTrace();
/* 3003 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 3008 */       return new OracleBlobOutputStream(paramBLOB, paramInt);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3013 */     return new OracleBlobOutputStream(paramBLOB, paramInt, paramLong);
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
/*      */   public InputStream newConversionInputStream(BLOB paramBLOB, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3032 */     assertNotNull(paramBLOB.shareBytes(), "newConversionInputStream");
/*      */     
/* 3034 */     OracleConversionInputStream localOracleConversionInputStream = new OracleConversionInputStream(this.conversion, paramBLOB.getBinaryStream(), paramInt);
/*      */     
/*      */ 
/* 3037 */     return localOracleConversionInputStream;
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
/*      */   public Reader newConversionReader(BLOB paramBLOB, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3055 */     assertNotNull(paramBLOB.shareBytes(), "newConversionReader");
/*      */     
/* 3057 */     OracleConversionReader localOracleConversionReader = new OracleConversionReader(this.conversion, paramBLOB.getBinaryStream(), paramInt);
/*      */     
/*      */ 
/* 3060 */     return localOracleConversionReader;
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
/*      */   public synchronized long length(CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 3085 */     assertLoggedOn("length");
/* 3086 */     assertNotNull(paramCLOB.shareBytes(), "length");
/*      */     
/* 3088 */     needLine();
/*      */     
/* 3090 */     long l = 0L;
/*      */     
/*      */     try
/*      */     {
/* 3094 */       l = this.clobMsg.getLength(paramCLOB.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 3099 */       handleIOException(localIOException);
/*      */       
/* 3101 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3102 */       localSQLException.fillInStackTrace();
/* 3103 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3108 */     return l;
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
/*      */   public long position(CLOB paramCLOB, String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3125 */     if (paramString == null)
/*      */     {
/* 3127 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 3128 */       ((SQLException)localObject).fillInStackTrace();
/* 3129 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 3132 */     assertLoggedOn("position");
/* 3133 */     assertNotNull(paramCLOB.shareBytes(), "position");
/*      */     
/*      */ 
/* 3136 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 3139 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 3140 */       ((SQLException)localObject).fillInStackTrace();
/* 3141 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3146 */     Object localObject = new char[paramString.length()];
/*      */     
/* 3148 */     paramString.getChars(0, localObject.length, (char[])localObject, 0);
/*      */     
/* 3150 */     long l = LobPlsqlUtil.hasPattern(paramCLOB, (char[])localObject, paramLong);
/*      */     
/* 3152 */     l = l == 0L ? -1L : l;
/*      */     
/* 3154 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long position(CLOB paramCLOB1, CLOB paramCLOB2, long paramLong)
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3170 */     if (paramCLOB2 == null)
/*      */     {
/* 3172 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 3173 */       localSQLException.fillInStackTrace();
/* 3174 */       throw localSQLException;
/*      */     }
/*      */     
/* 3177 */     assertLoggedOn("position");
/* 3178 */     assertNotNull(paramCLOB1.shareBytes(), "position");
/* 3179 */     assertNotNull(paramCLOB2.shareBytes(), "position");
/*      */     
/* 3181 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 3184 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 3185 */       localSQLException.fillInStackTrace();
/* 3186 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 3190 */     long l = LobPlsqlUtil.isSubLob(paramCLOB1, paramCLOB2, paramLong);
/*      */     
/* 3192 */     l = l == 0L ? -1L : l;
/*      */     
/* 3194 */     return l;
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
/* 3211 */     assertLoggedOn("getChars");
/* 3212 */     assertNotNull(paramCLOB.shareBytes(), "getChars");
/*      */     SQLException localSQLException1;
/* 3214 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 3217 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "getChars()");
/* 3218 */       localSQLException1.fillInStackTrace();
/* 3219 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3225 */     if (this.pipeState != -1)
/*      */     {
/*      */ 
/* 3228 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 453, "getChars()");
/* 3229 */       localSQLException1.fillInStackTrace();
/* 3230 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3235 */     if ((paramInt <= 0) || (paramArrayOfChar == null)) {
/* 3236 */       return 0;
/*      */     }
/*      */     
/* 3239 */     long l1 = 0L;
/*      */     
/*      */ 
/* 3242 */     long l2 = -1L;
/*      */     
/*      */ 
/* 3245 */     if (paramCLOB.isActivePrefetch())
/*      */     {
/* 3247 */       l2 = paramCLOB.length();
/* 3248 */       char[] arrayOfChar = paramCLOB.getPrefetchedData();
/* 3249 */       int i = paramCLOB.getPrefetchedDataSize();
/*      */       
/* 3251 */       int j = 0;
/* 3252 */       if (arrayOfChar != null) {
/* 3253 */         j = Math.min(i, arrayOfChar.length);
/*      */       }
/* 3255 */       if ((j > 0) && (paramLong <= j))
/*      */       {
/*      */ 
/*      */ 
/* 3259 */         int k = Math.min(j - (int)paramLong + 1, paramInt);
/*      */         
/*      */ 
/*      */ 
/* 3263 */         System.arraycopy(arrayOfChar, (int)paramLong - 1, paramArrayOfChar, 0, k);
/* 3264 */         l1 += k;
/*      */       }
/*      */     }
/*      */     
/* 3268 */     if ((l1 < paramInt) && ((l2 == -1L) || (paramLong - 1L + l1 < l2)))
/*      */     {
/*      */ 
/* 3271 */       needLine();
/*      */       try
/*      */       {
/* 3274 */         boolean bool = paramCLOB.isNCLOB();
/*      */         
/* 3276 */         l1 += this.clobMsg.read(paramCLOB.shareBytes(), paramLong + l1, paramInt - l1, bool, paramArrayOfChar, (int)l1);
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 3286 */         handleIOException(localIOException);
/*      */         
/* 3288 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3289 */         localSQLException2.fillInStackTrace();
/* 3290 */         throw localSQLException2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3297 */     return (int)l1;
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
/*      */   public synchronized int putChars(CLOB paramCLOB, long paramLong, char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3317 */     assertLoggedOn("putChars");
/* 3318 */     assertNotNull(paramCLOB.shareBytes(), "putChars");
/*      */     
/* 3320 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 3323 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "putChars()");
/* 3324 */       localSQLException1.fillInStackTrace();
/* 3325 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/* 3329 */     if ((paramArrayOfChar == null) || (paramInt2 <= 0)) {
/* 3330 */       return 0;
/*      */     }
/* 3332 */     needLine();
/*      */     
/* 3334 */     long l = 0L;
/*      */     
/* 3336 */     if (paramInt2 != 0)
/*      */     {
/*      */       try
/*      */       {
/* 3340 */         boolean bool = paramCLOB.isNCLOB();
/*      */         
/* 3342 */         paramCLOB.setActivePrefetch(false);
/* 3343 */         paramCLOB.clearCachedData();
/* 3344 */         l = this.clobMsg.write(paramCLOB.shareBytes(), paramLong, bool, paramArrayOfChar, paramInt1, paramInt2);
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/*      */ 
/* 3350 */         handleIOException(localIOException);
/*      */         
/* 3352 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3353 */         localSQLException2.fillInStackTrace();
/* 3354 */         throw localSQLException2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3360 */     return (int)l;
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
/* 3372 */     assertLoggedOn("getChunkSize");
/* 3373 */     assertNotNull(paramCLOB.shareBytes(), "getChunkSize");
/*      */     
/* 3375 */     needLine();
/*      */     
/* 3377 */     long l = 0L;
/*      */     
/*      */     try
/*      */     {
/* 3381 */       l = this.clobMsg.getChunkSize(paramCLOB.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 3386 */       handleIOException(localIOException);
/*      */       
/* 3388 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3389 */       localSQLException.fillInStackTrace();
/* 3390 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3395 */     return (int)l;
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
/* 3409 */     assertLoggedOn("trim");
/* 3410 */     assertNotNull(paramCLOB.shareBytes(), "trim");
/*      */     
/*      */ 
/* 3413 */     if (paramLong < 0L)
/*      */     {
/*      */ 
/* 3416 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "trim()");
/* 3417 */       localSQLException1.fillInStackTrace();
/* 3418 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3423 */     needLine();
/*      */     
/*      */ 
/*      */     try
/*      */     {
/* 3428 */       paramCLOB.setActivePrefetch(false);
/* 3429 */       paramCLOB.clearCachedData();
/* 3430 */       this.clobMsg.trim(paramCLOB.shareBytes(), paramLong);
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 3435 */       handleIOException(localIOException);
/*      */       
/* 3437 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3438 */       localSQLException2.fillInStackTrace();
/* 3439 */       throw localSQLException2;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized CLOB createTemporaryClob(Connection paramConnection, boolean paramBoolean, int paramInt, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 3462 */     assertLoggedOn("createTemporaryClob");
/*      */     
/*      */ 
/* 3465 */     if ((paramShort != 2) && (paramShort != 1))
/*      */     {
/*      */ 
/*      */ 
/* 3469 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 184);
/* 3470 */       ((SQLException)localObject).fillInStackTrace();
/* 3471 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3475 */     needLine();
/*      */     
/* 3477 */     Object localObject = null;
/*      */     
/*      */     try
/*      */     {
/* 3481 */       localObject = (CLOB)this.clobMsg.createTemporaryLob(this, paramBoolean, paramInt, paramShort);
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*      */ 
/* 3487 */       handleIOException(localIOException);
/*      */       
/* 3489 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3490 */       localSQLException.fillInStackTrace();
/* 3491 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3496 */     return (CLOB)localObject;
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
/*      */   public synchronized void freeTemporary(CLOB paramCLOB, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 3511 */     assertLoggedOn("freeTemporary");
/* 3512 */     assertNotNull(paramCLOB.shareBytes(), "freeTemporary");
/*      */     
/* 3514 */     needLine();
/*      */     
/*      */     try
/*      */     {
/* 3518 */       this.clobMsg.freeTemporaryLob(paramCLOB.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 3523 */       handleIOException(localIOException);
/*      */       
/* 3525 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3526 */       localSQLException.fillInStackTrace();
/* 3527 */       throw localSQLException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isTemporary(CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 3548 */     boolean bool = false;
/* 3549 */     byte[] arrayOfByte = paramCLOB.shareBytes();
/*      */     
/* 3551 */     if (((arrayOfByte[7] & 0x1) > 0) || ((arrayOfByte[4] & 0x40) > 0)) {
/* 3552 */       bool = true;
/*      */     }
/* 3554 */     return bool;
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
/* 3567 */     assertLoggedOn("open");
/* 3568 */     assertNotNull(paramCLOB.shareBytes(), "open");
/*      */     
/* 3570 */     needLine();
/*      */     
/*      */     try
/*      */     {
/* 3574 */       this.clobMsg.open(paramCLOB.shareBytes(), paramInt);
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 3579 */       handleIOException(localIOException);
/*      */       
/* 3581 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3582 */       localSQLException.fillInStackTrace();
/* 3583 */       throw localSQLException;
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
/*      */   public synchronized void close(CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 3598 */     assertLoggedOn("close");
/* 3599 */     assertNotNull(paramCLOB.shareBytes(), "close");
/*      */     
/* 3601 */     needLine();
/*      */     
/*      */     try
/*      */     {
/* 3605 */       this.clobMsg.close(paramCLOB.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 3610 */       handleIOException(localIOException);
/*      */       
/* 3612 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3613 */       localSQLException.fillInStackTrace();
/* 3614 */       throw localSQLException;
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
/*      */   public synchronized boolean isOpen(CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 3630 */     assertLoggedOn("isOpen");
/* 3631 */     assertNotNull(paramCLOB.shareBytes(), "isOpen");
/*      */     
/* 3633 */     boolean bool = false;
/*      */     
/* 3635 */     needLine();
/*      */     
/*      */     try
/*      */     {
/* 3639 */       bool = this.clobMsg.isOpen(paramCLOB.shareBytes());
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 3644 */       handleIOException(localIOException);
/*      */       
/* 3646 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3647 */       localSQLException.fillInStackTrace();
/* 3648 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3653 */     return bool;
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
/*      */   public InputStream newInputStream(CLOB paramCLOB, int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3672 */     if (paramLong == 0L)
/*      */     {
/* 3674 */       return new OracleClobInputStream(paramCLOB, paramInt);
/*      */     }
/*      */     
/*      */ 
/* 3678 */     return new OracleClobInputStream(paramCLOB, paramInt, paramLong);
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
/* 3699 */     if (paramLong == 0L)
/*      */     {
/* 3701 */       if ((paramBoolean & this.lobStreamPosStandardCompliant))
/*      */       {
/*      */ 
/* 3704 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 3705 */         localSQLException.fillInStackTrace();
/* 3706 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 3711 */       return new OracleClobOutputStream(paramCLOB, paramInt);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3716 */     return new OracleClobOutputStream(paramCLOB, paramInt, paramLong);
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
/* 3736 */     if (paramLong == 0L)
/*      */     {
/* 3738 */       return new OracleClobReader(paramCLOB, paramInt);
/*      */     }
/*      */     
/*      */ 
/* 3742 */     return new OracleClobReader(paramCLOB, paramInt, paramLong);
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
/* 3763 */     return new OracleClobReader(paramCLOB, paramInt, paramLong1, paramLong2);
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
/*      */   public Writer newWriter(CLOB paramCLOB, int paramInt, long paramLong, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 3782 */     if (paramLong == 0L)
/*      */     {
/* 3784 */       if ((paramBoolean & this.lobStreamPosStandardCompliant))
/*      */       {
/*      */ 
/* 3787 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 3788 */         localSQLException.fillInStackTrace();
/* 3789 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 3794 */       return new OracleClobWriter(paramCLOB, paramInt);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3799 */     return new OracleClobWriter(paramCLOB, paramInt, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int MAX_SIZE_VSESSION_MACHINE = 64;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int MAX_SIZE_VSESSION_TERMINAL = 30;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int MAX_SIZE_VSESSION_PROGRAM = 48;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void assertLoggedOn(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3825 */     if (!this.isLoggedOn)
/*      */     {
/*      */ 
/* 3828 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 430);
/* 3829 */       localSQLException.fillInStackTrace();
/* 3830 */       throw localSQLException;
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
/*      */   boolean isLoggedOn()
/*      */   {
/* 3844 */     return this.isLoggedOn;
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
/*      */   void assertNotNull(byte[] paramArrayOfByte, String paramString)
/*      */     throws NullPointerException
/*      */   {
/* 3860 */     if (paramArrayOfByte == null)
/*      */     {
/* 3862 */       throw new NullPointerException("bytes are null");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void internalClose()
/*      */     throws SQLException
/*      */   {
/* 3871 */     super.internalClose();
/*      */     
/* 3873 */     this.isLoggedOn = false;
/*      */     try
/*      */     {
/* 3876 */       if (this.net != null) {
/* 3877 */         this.net.disconnect();
/*      */       }
/*      */     }
/*      */     catch (Exception localException) {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void doAbort()
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 3891 */       this.net.abort();
/*      */ 
/*      */     }
/*      */     catch (NetException localNetException)
/*      */     {
/*      */ 
/* 3897 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localNetException);
/* 3898 */       localSQLException.fillInStackTrace();
/* 3899 */       throw localSQLException;
/*      */ 
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*      */ 
/* 3906 */       handleIOException(localIOException);
/*      */       
/* 3908 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3909 */       localSQLException.fillInStackTrace();
/* 3910 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void doDescribeTable(AutoKeyInfo paramAutoKeyInfo)
/*      */     throws SQLException
/*      */   {
/* 3921 */     T4CStatement localT4CStatement = new T4CStatement(this, -1, -1);
/* 3922 */     localT4CStatement.open();
/*      */     
/* 3924 */     String str1 = paramAutoKeyInfo.getTableName();
/* 3925 */     String str2 = "SELECT * FROM " + str1;
/*      */     
/* 3927 */     localT4CStatement.sqlObject.initialize(str2);
/*      */     
/* 3929 */     Accessor[] arrayOfAccessor = null;
/*      */     Object localObject;
/*      */     try
/*      */     {
/* 3933 */       this.describe.doODNY(localT4CStatement, 0, arrayOfAccessor, localT4CStatement.sqlObject.getSqlBytes(false, false));
/* 3934 */       arrayOfAccessor = this.describe.getAccessors();
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 3938 */       handleIOException(localIOException);
/*      */       
/* 3940 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3941 */       ((SQLException)localObject).fillInStackTrace();
/* 3942 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3946 */     int i = this.describe.numuds;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3957 */     paramAutoKeyInfo.allocateSpaceForDescribedData(i);
/*      */     
/* 3959 */     for (int i1 = 0; i1 < i; i1++)
/*      */     {
/* 3961 */       Accessor localAccessor = arrayOfAccessor[i1];
/* 3962 */       localObject = localAccessor.columnName;
/* 3963 */       int j = localAccessor.describeType;
/* 3964 */       int k = localAccessor.describeMaxLength;
/* 3965 */       boolean bool = localAccessor.nullable;
/* 3966 */       short s = localAccessor.formOfUse;
/* 3967 */       int m = localAccessor.precision;
/* 3968 */       int n = localAccessor.scale;
/* 3969 */       String str3 = localAccessor.describeTypeName;
/*      */       
/* 3971 */       paramAutoKeyInfo.fillDescribedData(i1, (String)localObject, j, k, bool, s, m, n, str3);
/*      */     }
/*      */     
/*      */ 
/* 3975 */     localT4CStatement.close();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void doSetApplicationContext(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 3986 */     Namespace localNamespace = (Namespace)this.namespaces.get(paramString1);
/* 3987 */     if (localNamespace == null)
/*      */     {
/* 3989 */       localNamespace = new Namespace(paramString1);
/* 3990 */       this.namespaces.put(paramString1, localNamespace);
/*      */     }
/* 3992 */     localNamespace.setAttribute(paramString2, paramString3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void doClearAllApplicationContext(String paramString)
/*      */     throws SQLException
/*      */   {
/* 4001 */     Namespace localNamespace = new Namespace(paramString);
/* 4002 */     localNamespace.clear();
/* 4003 */     this.namespaces.put(paramString, localNamespace);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void getPropertyForPooledConnection(OraclePooledConnection paramOraclePooledConnection)
/*      */     throws SQLException
/*      */   {
/* 4011 */     super.getPropertyForPooledConnection(paramOraclePooledConnection, this.password);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   final void getPasswordInternal(T4CXAResource paramT4CXAResource)
/*      */     throws SQLException
/*      */   {
/* 4019 */     paramT4CXAResource.setPasswordInternal(this.password);
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
/*      */   synchronized void doEnqueue(String paramString, AQEnqueueOptions paramAQEnqueueOptions, AQMessagePropertiesI paramAQMessagePropertiesI, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[][] paramArrayOfByte, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 4037 */       needLine();
/* 4038 */       sendPiggyBackedMessages();
/* 4039 */       this.aqe.doOAQEQ(paramString, paramAQEnqueueOptions, paramAQMessagePropertiesI, paramArrayOfByte2, paramArrayOfByte1, paramBoolean);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4046 */       if (paramAQEnqueueOptions.getRetrieveMessageId()) {
/* 4047 */         paramArrayOfByte[0] = this.aqe.getMessageId();
/*      */       }
/*      */     }
/*      */     catch (IOException localIOException) {
/* 4051 */       handleIOException(localIOException);
/*      */       
/* 4053 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 4054 */       localSQLException.fillInStackTrace();
/* 4055 */       throw localSQLException;
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
/*      */   synchronized boolean doDequeue(String paramString, AQDequeueOptions paramAQDequeueOptions, AQMessagePropertiesI paramAQMessagePropertiesI, byte[] paramArrayOfByte, byte[][] paramArrayOfByte1, byte[][] paramArrayOfByte2, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 4072 */     boolean bool = false;
/*      */     try
/*      */     {
/* 4075 */       needLine();
/* 4076 */       sendPiggyBackedMessages();
/* 4077 */       this.aqdq.doOAQDQ(paramString, paramAQDequeueOptions, paramArrayOfByte, paramBoolean, paramAQMessagePropertiesI);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4083 */       paramArrayOfByte1[0] = this.aqdq.getPayload();
/* 4084 */       paramArrayOfByte2[0] = this.aqdq.getDequeuedMessageId();
/* 4085 */       bool = this.aqdq.hasAMessageBeenDequeued();
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 4089 */       handleIOException(localIOException);
/*      */       
/* 4091 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 4092 */       localSQLException.fillInStackTrace();
/* 4093 */       throw localSQLException;
/*      */     }
/*      */     
/* 4096 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */   synchronized int doPingDatabase()
/*      */     throws SQLException
/*      */   {
/* 4103 */     if (this.versionNumber >= 10102)
/*      */     {
/*      */       try
/*      */       {
/* 4107 */         needLine();
/* 4108 */         sendPiggyBackedMessages();
/* 4109 */         this.oping.doOPING();
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/* 4113 */         return -1;
/*      */       }
/*      */       catch (SQLException localSQLException)
/*      */       {
/* 4117 */         return -1;
/*      */       }
/* 4119 */       return 0;
/*      */     }
/*      */     
/*      */ 
/* 4123 */     return super.doPingDatabase();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   synchronized NTFAQRegistration[] doRegisterAQNotification(String[] paramArrayOfString, String paramString, int paramInt, Properties[] paramArrayOfProperties)
/*      */     throws SQLException
/*      */   {
/* 4136 */     int i = paramArrayOfString.length;
/* 4137 */     int[] arrayOfInt1 = new int[i];
/* 4138 */     byte[][] arrayOfByte = new byte[i][];
/* 4139 */     int[] arrayOfInt2 = new int[i];
/* 4140 */     int[] arrayOfInt3 = new int[i];
/* 4141 */     int[] arrayOfInt4 = new int[i];
/* 4142 */     int[] arrayOfInt5 = new int[i];
/* 4143 */     int[] arrayOfInt6 = new int[i];
/* 4144 */     int[] arrayOfInt7 = new int[i];
/* 4145 */     long[] arrayOfLong = new long[i];
/* 4146 */     byte[] arrayOfByte1 = new byte[i];
/* 4147 */     int[] arrayOfInt8 = new int[i];
/* 4148 */     byte[] arrayOfByte2 = new byte[i];
/* 4149 */     TIMESTAMPTZ[] arrayOfTIMESTAMPTZ = new TIMESTAMPTZ[i];
/* 4150 */     int[] arrayOfInt9 = new int[i];
/*      */     
/* 4152 */     boolean bool1 = false;
/* 4153 */     if (paramInt == 0)
/*      */     {
/*      */ 
/* 4156 */       bool1 = true;
/* 4157 */       paramInt = 47632;
/*      */     }
/*      */     
/* 4160 */     for (int j = 0; j < i; j++)
/*      */     {
/* 4162 */       arrayOfInt1[j] = PhysicalConnection.ntfManager.getNextJdbcRegId();
/* 4163 */       arrayOfByte[j] = new byte[4];
/* 4164 */       arrayOfByte[j][0] = ((byte)((arrayOfInt1[j] & 0xFF000000) >> 24));
/* 4165 */       arrayOfByte[j][1] = ((byte)((arrayOfInt1[j] & 0xFF0000) >> 16));
/* 4166 */       arrayOfByte[j][2] = ((byte)((arrayOfInt1[j] & 0xFF00) >> 8));
/* 4167 */       arrayOfByte[j][3] = ((byte)(arrayOfInt1[j] & 0xFF));
/* 4168 */       arrayOfInt2[j] = 1;
/* 4169 */       arrayOfInt3[j] = 23;
/*      */       
/*      */ 
/* 4172 */       if ((paramArrayOfProperties.length > j) && (paramArrayOfProperties[j] != null))
/*      */       {
/* 4174 */         if (paramArrayOfProperties[j].getProperty("NTF_QOS_RELIABLE", "false").compareToIgnoreCase("true") == 0)
/*      */         {
/* 4176 */           arrayOfInt4[j] |= 0x1; }
/* 4177 */         if (paramArrayOfProperties[j].getProperty("NTF_QOS_PURGE_ON_NTFN", "false").compareToIgnoreCase("true") == 0)
/*      */         {
/* 4179 */           arrayOfInt4[j] |= 0x10; }
/* 4180 */         if (paramArrayOfProperties[j].getProperty("NTF_AQ_PAYLOAD", "false").compareToIgnoreCase("true") == 0)
/*      */         {
/* 4182 */           arrayOfInt4[j] |= 0x2; }
/* 4183 */         arrayOfInt5[j] = readNTFtimeout(paramArrayOfProperties[j]);
/*      */       }
/*      */     }
/*      */     
/* 4187 */     setNtfGroupingOptions(arrayOfByte1, arrayOfInt8, arrayOfByte2, arrayOfTIMESTAMPTZ, arrayOfInt9, paramArrayOfProperties);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4194 */     int[] arrayOfInt10 = new int[1];
/* 4195 */     arrayOfInt10[0] = paramInt;
/*      */     
/*      */ 
/*      */ 
/* 4199 */     boolean bool2 = PhysicalConnection.ntfManager.listenOnPortT4C(arrayOfInt10, bool1);
/* 4200 */     paramInt = arrayOfInt10[0];
/*      */     
/* 4202 */     String str = "(ADDRESS=(PROTOCOL=tcp)(HOST=" + paramString + ")(PORT=" + paramInt + "))?PR=0";
/*      */     
/*      */ 
/*      */     try
/*      */     {
/*      */       try
/*      */       {
/* 4209 */         int k = bool2 ? 1 : 0;
/* 4210 */         sendPiggyBackedMessages();
/* 4211 */         this.okpn.doOKPN(1, k, this.userName, str, i, arrayOfInt2, paramArrayOfString, arrayOfByte, arrayOfInt3, arrayOfInt4, arrayOfInt5, arrayOfInt6, arrayOfInt7, arrayOfLong, arrayOfByte1, arrayOfInt8, arrayOfByte2, arrayOfTIMESTAMPTZ, arrayOfInt9);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4235 */         handleIOException(localIOException);
/*      */         
/* 4237 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 4238 */         localSQLException2.fillInStackTrace();
/* 4239 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */     }
/*      */     catch (SQLException localSQLException1)
/*      */     {
/* 4245 */       if (bool2) {
/* 4246 */         PhysicalConnection.ntfManager.cleanListenersT4C(paramInt);
/*      */       }
/* 4248 */       throw localSQLException1;
/*      */     }
/* 4250 */     NTFAQRegistration[] arrayOfNTFAQRegistration = new NTFAQRegistration[i];
/*      */     
/* 4252 */     for (int m = 0; m < i; m++) {
/* 4253 */       arrayOfNTFAQRegistration[m] = new NTFAQRegistration(arrayOfInt1[m], true, this.instanceName, this.userName, paramString, paramInt, paramArrayOfProperties[m], paramArrayOfString[m], this.versionNumber);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4263 */     for (m = 0; m < arrayOfNTFAQRegistration.length; m++)
/* 4264 */       PhysicalConnection.ntfManager.addRegistration(arrayOfNTFAQRegistration[m]);
/* 4265 */     return arrayOfNTFAQRegistration;
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
/*      */   private void setNtfGroupingOptions(byte[] paramArrayOfByte1, int[] paramArrayOfInt1, byte[] paramArrayOfByte2, TIMESTAMPTZ[] paramArrayOfTIMESTAMPTZ, int[] paramArrayOfInt2, Properties[] paramArrayOfProperties)
/*      */     throws SQLException
/*      */   {
/* 4284 */     for (int i = 0; i < paramArrayOfProperties.length; i++)
/*      */     {
/* 4286 */       String str1 = paramArrayOfProperties[i].getProperty("NTF_GROUPING_CLASS", "NTF_GROUPING_CLASS_NONE");
/* 4287 */       String str2 = paramArrayOfProperties[i].getProperty("NTF_GROUPING_VALUE");
/* 4288 */       String str3 = paramArrayOfProperties[i].getProperty("NTF_GROUPING_TYPE");
/* 4289 */       TIMESTAMPTZ localTIMESTAMPTZ = null;
/* 4290 */       if (paramArrayOfProperties[i].get("NTF_GROUPING_START_TIME") != null)
/* 4291 */         localTIMESTAMPTZ = (TIMESTAMPTZ)paramArrayOfProperties[i].get("NTF_GROUPING_START_TIME");
/* 4292 */       String str4 = paramArrayOfProperties[i].getProperty("NTF_GROUPING_REPEAT_TIME", "NTF_GROUPING_REPEAT_FOREVER");
/*      */       
/*      */       SQLException localSQLException;
/* 4295 */       if ((str1.compareTo("NTF_GROUPING_CLASS_TIME") != 0) && (str1.compareTo("NTF_GROUPING_CLASS_NONE") != 0))
/*      */       {
/*      */ 
/*      */ 
/* 4299 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 4300 */         localSQLException.fillInStackTrace();
/* 4301 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 4306 */       if ((str1.compareTo("NTF_GROUPING_CLASS_NONE") != 0) && (getTTCVersion() < 5))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 4311 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23);
/* 4312 */         localSQLException.fillInStackTrace();
/* 4313 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 4319 */       if (str1.compareTo("NTF_GROUPING_CLASS_TIME") == 0)
/*      */       {
/* 4321 */         paramArrayOfByte1[i] = 1;
/*      */         
/* 4323 */         paramArrayOfInt1[i] = 600;
/* 4324 */         if (str2 != null) {
/* 4325 */           paramArrayOfInt1[i] = Integer.parseInt(str2);
/*      */         }
/* 4327 */         paramArrayOfByte2[i] = 1;
/* 4328 */         if (str3 != null)
/*      */         {
/* 4330 */           if (str3.compareTo("NTF_GROUPING_TYPE_SUMMARY") == 0) {
/* 4331 */             paramArrayOfByte2[i] = 1;
/* 4332 */           } else if (str3.compareTo("NTF_GROUPING_TYPE_LAST") == 0) {
/* 4333 */             paramArrayOfByte2[i] = 2;
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/* 4339 */             localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 4340 */             localSQLException.fillInStackTrace();
/* 4341 */             throw localSQLException;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 4346 */         paramArrayOfTIMESTAMPTZ[i] = localTIMESTAMPTZ;
/* 4347 */         if (str4.compareTo("NTF_GROUPING_REPEAT_FOREVER") == 0) {
/* 4348 */           paramArrayOfInt2[i] = 0;
/*      */         } else {
/* 4350 */           paramArrayOfInt2[i] = Integer.parseInt(str4);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   synchronized void doUnregisterAQNotification(NTFAQRegistration paramNTFAQRegistration)
/*      */     throws SQLException
/*      */   {
/* 4362 */     String str1 = paramNTFAQRegistration.getClientHost();
/* 4363 */     int i = paramNTFAQRegistration.getClientTCPPort();
/* 4364 */     if (str1 == null) {
/* 4365 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4375 */     PhysicalConnection.ntfManager.removeRegistration(paramNTFAQRegistration);
/* 4376 */     PhysicalConnection.ntfManager.freeJdbcRegId(paramNTFAQRegistration.getJdbcRegId());
/* 4377 */     PhysicalConnection.ntfManager.cleanListenersT4C(paramNTFAQRegistration.getClientTCPPort());
/* 4378 */     paramNTFAQRegistration.setState(NotificationRegistration.RegistrationState.CLOSED);
/*      */     
/* 4380 */     String str2 = "(ADDRESS=(PROTOCOL=tcp)(HOST=" + str1 + ")(PORT=" + i + "))?PR=0";
/*      */     
/*      */ 
/* 4383 */     int[] arrayOfInt1 = { 1 };
/* 4384 */     String[] arrayOfString = new String[1];
/* 4385 */     arrayOfString[0] = paramNTFAQRegistration.getQueueName();
/* 4386 */     int[] arrayOfInt2 = { 0 };
/* 4387 */     int[] arrayOfInt3 = { 0 };
/* 4388 */     int[] arrayOfInt4 = { 0 };
/* 4389 */     int[] arrayOfInt5 = { 0 };
/* 4390 */     int[] arrayOfInt6 = { 0 };
/* 4391 */     long[] arrayOfLong = { 0L };
/* 4392 */     byte[] arrayOfByte1 = { 0 };
/* 4393 */     int[] arrayOfInt7 = { 0 };
/* 4394 */     byte[] arrayOfByte2 = { 0 };
/* 4395 */     TIMESTAMPTZ[] arrayOfTIMESTAMPTZ = { null };
/* 4396 */     int[] arrayOfInt8 = { 0 };
/* 4397 */     byte[][] arrayOfByte = new byte[1][];
/* 4398 */     int j = paramNTFAQRegistration.getJdbcRegId();
/* 4399 */     arrayOfByte[0] = new byte[4];
/* 4400 */     arrayOfByte[0][0] = ((byte)((j & 0xFF000000) >> 24));
/* 4401 */     arrayOfByte[0][1] = ((byte)((j & 0xFF0000) >> 16));
/* 4402 */     arrayOfByte[0][2] = ((byte)((j & 0xFF00) >> 8));
/* 4403 */     arrayOfByte[0][3] = ((byte)(j & 0xFF));
/*      */     try
/*      */     {
/* 4406 */       sendPiggyBackedMessages();
/* 4407 */       this.okpn.doOKPN(2, 0, this.userName, str2, 1, arrayOfInt1, arrayOfString, arrayOfByte, arrayOfInt2, arrayOfInt3, arrayOfInt4, arrayOfInt5, arrayOfInt6, arrayOfLong, arrayOfByte1, arrayOfInt7, arrayOfByte2, arrayOfTIMESTAMPTZ, arrayOfInt8);
/*      */ 
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
/*      */     catch (IOException localIOException)
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
/* 4431 */       handleIOException(localIOException);
/*      */       
/* 4433 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 4434 */       localSQLException.fillInStackTrace();
/* 4435 */       throw localSQLException;
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
/*      */   synchronized NTFDCNRegistration doRegisterDatabaseChangeNotification(String paramString, int paramInt1, Properties paramProperties, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 4451 */     int i = 0;
/* 4452 */     int j = 0;
/* 4453 */     int k = 0;
/* 4454 */     int m = 0;
/* 4455 */     int n = 0;
/* 4456 */     Object localObject = null;
/* 4457 */     int i1 = 0;
/* 4458 */     boolean bool1 = false;
/* 4459 */     if (paramInt1 == 0)
/*      */     {
/*      */ 
/* 4462 */       bool1 = true;
/* 4463 */       paramInt1 = 47632;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4468 */     if (paramProperties.getProperty("NTF_QOS_RELIABLE", "false").compareToIgnoreCase("true") == 0)
/*      */     {
/* 4470 */       j |= 0x1; }
/* 4471 */     if (paramProperties.getProperty("NTF_QOS_PURGE_ON_NTFN", "false").compareToIgnoreCase("true") == 0)
/*      */     {
/* 4473 */       j |= 0x10;
/*      */     }
/*      */     
/*      */ 
/* 4477 */     if (paramProperties.getProperty("DCN_NOTIFY_ROWIDS", "false").compareToIgnoreCase("true") == 0)
/*      */     {
/* 4479 */       i |= 0x10;
/*      */     }
/*      */     
/* 4482 */     if (paramProperties.getProperty("DCN_QUERY_CHANGE_NOTIFICATION", "false").compareToIgnoreCase("true") == 0)
/*      */     {
/* 4484 */       i |= 0x20;
/*      */     }
/*      */     
/* 4487 */     if (paramProperties.getProperty("DCN_BEST_EFFORT", "false").compareToIgnoreCase("true") == 0)
/*      */     {
/* 4489 */       i |= 0x40;
/*      */     }
/* 4491 */     int i2 = 0;
/* 4492 */     int i3 = 0;
/* 4493 */     int i4 = 0;
/* 4494 */     if (paramProperties.getProperty("DCN_IGNORE_INSERTOP", "false").compareToIgnoreCase("true") == 0)
/*      */     {
/* 4496 */       i2 = 1; }
/* 4497 */     if (paramProperties.getProperty("DCN_IGNORE_UPDATEOP", "false").compareToIgnoreCase("true") == 0)
/*      */     {
/* 4499 */       i3 = 1; }
/* 4500 */     if (paramProperties.getProperty("DCN_IGNORE_DELETEOP", "false").compareToIgnoreCase("true") == 0)
/*      */     {
/* 4502 */       i4 = 1;
/*      */     }
/* 4504 */     if ((i2 != 0) || (i3 != 0) || (i4 != 0))
/*      */     {
/* 4506 */       i |= 0xF;
/*      */       
/*      */ 
/*      */ 
/* 4510 */       if (i2 != 0)
/* 4511 */         i -= 2;
/* 4512 */       if (i3 != 0)
/* 4513 */         i -= 4;
/* 4514 */       if (i4 != 0) {
/* 4515 */         i -= 8;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4521 */     byte[] arrayOfByte1 = new byte[1];
/* 4522 */     int[] arrayOfInt1 = new int[1];
/* 4523 */     byte[] arrayOfByte2 = new byte[1];
/* 4524 */     TIMESTAMPTZ[] arrayOfTIMESTAMPTZ = new TIMESTAMPTZ[1];
/* 4525 */     int[] arrayOfInt2 = new int[1];
/* 4526 */     Properties[] arrayOfProperties = { paramProperties };
/* 4527 */     setNtfGroupingOptions(arrayOfByte1, arrayOfInt1, arrayOfByte2, arrayOfTIMESTAMPTZ, arrayOfInt2, arrayOfProperties);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4543 */     int[] arrayOfInt3 = new int[1];
/* 4544 */     arrayOfInt3[0] = paramInt1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 4549 */     boolean bool2 = PhysicalConnection.ntfManager.listenOnPortT4C(arrayOfInt3, bool1);
/* 4550 */     paramInt1 = arrayOfInt3[0];
/*      */     
/* 4552 */     String str = "(ADDRESS=(PROTOCOL=tcp)(HOST=" + paramString + ")(PORT=" + paramInt1 + "))?PR=0";
/*      */     
/*      */ 
/*      */ 
/* 4556 */     int[] arrayOfInt4 = { 2 };
/* 4557 */     String[] arrayOfString = new String[1];
/* 4558 */     int[] arrayOfInt5 = { 23 };
/*      */     
/*      */ 
/* 4561 */     int[] arrayOfInt6 = { j };
/* 4562 */     int[] arrayOfInt7 = { paramInt2 };
/* 4563 */     int[] arrayOfInt8 = { i };
/* 4564 */     int[] arrayOfInt9 = { paramInt3 };
/* 4565 */     long[] arrayOfLong = { 0L };
/* 4566 */     int i5 = PhysicalConnection.ntfManager.getNextJdbcRegId();
/* 4567 */     byte[][] arrayOfByte = new byte[1][];
/* 4568 */     arrayOfByte[0] = new byte[4];
/* 4569 */     arrayOfByte[0][0] = ((byte)((i5 & 0xFF000000) >> 24));
/* 4570 */     arrayOfByte[0][1] = ((byte)((i5 & 0xFF0000) >> 16));
/* 4571 */     arrayOfByte[0][2] = ((byte)((i5 & 0xFF00) >> 8));
/* 4572 */     arrayOfByte[0][3] = ((byte)(i5 & 0xFF));
/* 4573 */     long l = 0L;
/*      */     try
/*      */     {
/*      */       try
/*      */       {
/* 4578 */         int i6 = bool2 ? 1 : 0;
/* 4579 */         sendPiggyBackedMessages();
/* 4580 */         this.okpn.doOKPN(1, i6, this.userName, str, 1, arrayOfInt4, arrayOfString, arrayOfByte, arrayOfInt5, arrayOfInt6, arrayOfInt7, arrayOfInt8, arrayOfInt9, arrayOfLong, arrayOfByte1, arrayOfInt1, arrayOfByte2, arrayOfTIMESTAMPTZ, arrayOfInt2);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4601 */         l = this.okpn.getRegistrationId();
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/* 4605 */         handleIOException(localIOException);
/*      */         
/* 4607 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 4608 */         localSQLException2.fillInStackTrace();
/* 4609 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */     }
/*      */     catch (SQLException localSQLException1)
/*      */     {
/* 4615 */       if (bool2) {
/* 4616 */         PhysicalConnection.ntfManager.cleanListenersT4C(paramInt1);
/*      */       }
/* 4618 */       throw localSQLException1;
/*      */     }
/* 4620 */     NTFDCNRegistration localNTFDCNRegistration = new NTFDCNRegistration(i5, true, this.instanceName, l, this.userName, paramString, paramInt1, paramProperties, this.versionNumber);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4631 */     return localNTFDCNRegistration;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   synchronized void doUnregisterDatabaseChangeNotification(long paramLong, String paramString)
/*      */     throws SQLException
/*      */   {
/* 4644 */     int[] arrayOfInt1 = { 2 };
/* 4645 */     String[] arrayOfString = new String[1];
/* 4646 */     int[] arrayOfInt2 = { 0 };
/* 4647 */     int[] arrayOfInt3 = { 0 };
/* 4648 */     int[] arrayOfInt4 = { 0 };
/* 4649 */     int[] arrayOfInt5 = { 0 };
/* 4650 */     int[] arrayOfInt6 = { 0 };
/* 4651 */     byte[] arrayOfByte1 = { 0 };
/* 4652 */     int[] arrayOfInt7 = { 0 };
/* 4653 */     byte[] arrayOfByte2 = { 0 };
/* 4654 */     TIMESTAMPTZ[] arrayOfTIMESTAMPTZ = { null };
/* 4655 */     int[] arrayOfInt8 = { 0 };
/* 4656 */     long[] arrayOfLong = { paramLong };
/* 4657 */     byte[][] arrayOfByte = new byte[1][];
/*      */     try
/*      */     {
/* 4660 */       sendPiggyBackedMessages();
/* 4661 */       this.okpn.doOKPN(2, 0, null, paramString, 1, arrayOfInt1, arrayOfString, arrayOfByte, arrayOfInt2, arrayOfInt3, arrayOfInt4, arrayOfInt5, arrayOfInt6, arrayOfLong, arrayOfByte1, arrayOfInt7, arrayOfByte2, arrayOfTIMESTAMPTZ, arrayOfInt8);
/*      */ 
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
/*      */     catch (IOException localIOException)
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
/* 4685 */       handleIOException(localIOException);
/*      */       
/* 4687 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 4688 */       localSQLException.fillInStackTrace();
/* 4689 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   synchronized void doUnregisterDatabaseChangeNotification(NTFDCNRegistration paramNTFDCNRegistration)
/*      */     throws SQLException
/*      */   {
/* 4702 */     PhysicalConnection.ntfManager.removeRegistration(paramNTFDCNRegistration);
/* 4703 */     PhysicalConnection.ntfManager.freeJdbcRegId(paramNTFDCNRegistration.getJdbcRegId());
/* 4704 */     PhysicalConnection.ntfManager.cleanListenersT4C(paramNTFDCNRegistration.getClientTCPPort());
/* 4705 */     paramNTFDCNRegistration.setState(NotificationRegistration.RegistrationState.CLOSED);
/*      */     
/* 4707 */     doUnregisterDatabaseChangeNotification(paramNTFDCNRegistration.getRegId(), "(ADDRESS=(PROTOCOL=tcp)(HOST=" + paramNTFDCNRegistration.getClientHost() + ")(PORT=" + paramNTFDCNRegistration.getClientTCPPort() + "))?PR=0");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getDataIntegrityAlgorithmName()
/*      */     throws SQLException
/*      */   {
/* 4717 */     return this.net.getDataIntegrityName();
/*      */   }
/*      */   
/*      */ 
/*      */   public String getEncryptionAlgorithmName()
/*      */     throws SQLException
/*      */   {
/* 4724 */     return this.net.getEncryptionName();
/*      */   }
/*      */   
/*      */ 
/*      */   public String getAuthenticationAdaptorName()
/*      */     throws SQLException
/*      */   {
/* 4731 */     return this.net.getAuthenticationAdaptorName();
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
/*      */   void validateConnectionProperties()
/*      */     throws SQLException
/*      */   {
/* 4746 */     super.validateConnectionProperties();
/*      */     
/* 4748 */     String str = ".*[\\00\\(\\)].*";
/* 4749 */     SQLException localSQLException; if ((this.thinVsessionOsuser != null) && ((this.thinVsessionOsuser.matches(str)) || (this.thinVsessionOsuser.length() > 30)))
/*      */     {
/*      */ 
/*      */ 
/* 4753 */       localSQLException = DatabaseError.createSqlException(null, 190, "Property is 'v$session.osuser' and value is '" + this.thinVsessionOsuser + "'");
/* 4754 */       localSQLException.fillInStackTrace();
/* 4755 */       throw localSQLException;
/*      */     }
/*      */     
/* 4758 */     if ((this.thinVsessionTerminal != null) && ((this.thinVsessionTerminal.matches(str)) || (this.thinVsessionTerminal.length() > 30)))
/*      */     {
/*      */ 
/*      */ 
/* 4762 */       localSQLException = DatabaseError.createSqlException(null, 190, "Property is 'v$session.terminal' and value is '" + this.thinVsessionTerminal + "'");
/* 4763 */       localSQLException.fillInStackTrace();
/* 4764 */       throw localSQLException;
/*      */     }
/*      */     
/* 4767 */     if ((this.thinVsessionMachine != null) && ((this.thinVsessionMachine.matches(str)) || (this.thinVsessionMachine.length() > 64)))
/*      */     {
/*      */ 
/*      */ 
/* 4771 */       localSQLException = DatabaseError.createSqlException(null, 190, "Property is 'v$session.machine' and value is '" + this.thinVsessionMachine + "'");
/* 4772 */       localSQLException.fillInStackTrace();
/* 4773 */       throw localSQLException;
/*      */     }
/*      */     
/* 4776 */     if ((this.thinVsessionProgram != null) && ((this.thinVsessionProgram.matches(str)) || (this.thinVsessionProgram.length() > 48)))
/*      */     {
/*      */ 
/*      */ 
/* 4780 */       localSQLException = DatabaseError.createSqlException(null, 190, "Property is 'v$session.program' and value is '" + this.thinVsessionProgram + "'");
/* 4781 */       localSQLException.fillInStackTrace();
/* 4782 */       throw localSQLException;
/*      */     }
/*      */     
/* 4785 */     if ((this.thinVsessionProcess != null) && ((this.thinVsessionProcess.matches(str)) || (this.thinVsessionProcess.length() > 24)))
/*      */     {
/*      */ 
/*      */ 
/* 4789 */       localSQLException = DatabaseError.createSqlException(null, 190, "Property is 'v$session.process' and value is '" + this.thinVsessionProcess + "'");
/* 4790 */       localSQLException.fillInStackTrace();
/* 4791 */       throw localSQLException;
/*      */     }
/*      */     
/* 4794 */     if ((this.thinVsessionIname != null) && (this.thinVsessionIname.matches(str)))
/*      */     {
/* 4796 */       localSQLException = DatabaseError.createSqlException(null, 190, "Property is 'v$session.iname' and value is '" + this.thinVsessionIname + "'");
/* 4797 */       localSQLException.fillInStackTrace();
/* 4798 */       throw localSQLException;
/*      */     }
/*      */     
/* 4801 */     if ((this.thinVsessionEname != null) && (this.thinVsessionEname.matches(str)))
/*      */     {
/* 4803 */       localSQLException = DatabaseError.createSqlException(null, 190, "Property is 'v$session.ename' and value is '" + this.thinVsessionEname + "'");
/* 4804 */       localSQLException.fillInStackTrace();
/* 4805 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 4811 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final boolean TRACE = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized byte[] createLightweightSession(String paramString, KeywordValueLong[] paramArrayOfKeywordValueLong, int paramInt, KeywordValueLong[][] paramArrayOfKeywordValueLong1, int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/* 4825 */     if ((paramArrayOfKeywordValueLong1.length != 1) || (paramArrayOfInt.length != 1))
/*      */     {
/* 4827 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 4828 */       ((SQLException)localObject).fillInStackTrace();
/* 4829 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 4832 */     Object localObject = null;
/*      */     try
/*      */     {
/* 4835 */       sendPiggyBackedMessages();
/* 4836 */       this.oxsscs.doOXSSCS(paramString, paramArrayOfKeywordValueLong, paramInt);
/* 4837 */       localObject = this.oxsscs.getSessionId();
/* 4838 */       paramArrayOfKeywordValueLong1[0] = this.oxsscs.getOutKV();
/* 4839 */       paramArrayOfInt[0] = this.oxsscs.getOutFlags();
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 4843 */       handleIOException(localIOException);
/*      */       
/* 4845 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 4846 */       localSQLException.fillInStackTrace();
/* 4847 */       throw localSQLException;
/*      */     }
/*      */     
/* 4850 */     return (byte[])localObject;
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
/*      */   private synchronized void doXSNamespaceOp(OracleConnection.XSOperationCode paramXSOperationCode, byte[] paramArrayOfByte, XSNamespace[] paramArrayOfXSNamespace, XSNamespace[][] paramArrayOfXSNamespace1, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 4866 */     XSNamespace[] arrayOfXSNamespace = null;
/*      */     try
/*      */     {
/* 4869 */       if (paramBoolean)
/* 4870 */         sendPiggyBackedMessages();
/* 4871 */       this.xsnsop.doOXSNS(paramXSOperationCode, paramArrayOfByte, paramArrayOfXSNamespace, paramBoolean);
/* 4872 */       if (paramBoolean) {
/* 4873 */         arrayOfXSNamespace = this.xsnsop.getNamespaces();
/*      */       }
/*      */     }
/*      */     catch (IOException localIOException) {
/* 4877 */       handleIOException(localIOException);
/*      */       
/* 4879 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 4880 */       localSQLException.fillInStackTrace();
/* 4881 */       throw localSQLException;
/*      */     }
/*      */     
/* 4884 */     if ((paramArrayOfXSNamespace1 != null) && (paramArrayOfXSNamespace1.length > 0)) {
/* 4885 */       paramArrayOfXSNamespace1[0] = arrayOfXSNamespace;
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
/*      */   public void doXSNamespaceOp(OracleConnection.XSOperationCode paramXSOperationCode, byte[] paramArrayOfByte, XSNamespace[] paramArrayOfXSNamespace, XSNamespace[][] paramArrayOfXSNamespace1)
/*      */     throws SQLException
/*      */   {
/* 4899 */     doXSNamespaceOp(paramXSOperationCode, paramArrayOfByte, paramArrayOfXSNamespace, paramArrayOfXSNamespace1, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void doXSNamespaceOp(OracleConnection.XSOperationCode paramXSOperationCode, byte[] paramArrayOfByte, XSNamespace[] paramArrayOfXSNamespace)
/*      */     throws SQLException
/*      */   {
/* 4911 */     doXSNamespaceOp(paramXSOperationCode, paramArrayOfByte, paramArrayOfXSNamespace, (XSNamespace[][])null, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void executeLightweightSessionRoundtrip(int paramInt1, byte[] paramArrayOfByte, KeywordValueLong[] paramArrayOfKeywordValueLong, int paramInt2, KeywordValueLong[][] paramArrayOfKeywordValueLong1, int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/* 4923 */     if ((paramArrayOfKeywordValueLong1.length != 1) || (paramArrayOfInt.length != 1))
/*      */     {
/* 4925 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 4926 */       localSQLException1.fillInStackTrace();
/* 4927 */       throw localSQLException1;
/*      */     }
/*      */     try
/*      */     {
/* 4931 */       sendPiggyBackedMessages();
/* 4932 */       this.oxssro.doOXSSRO(paramInt1, paramArrayOfByte, paramArrayOfKeywordValueLong, paramInt2);
/* 4933 */       paramArrayOfKeywordValueLong1[0] = this.oxssro.getOutKV();
/* 4934 */       paramArrayOfInt[0] = this.oxssro.getOutFlags();
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 4938 */       handleIOException(localIOException);
/*      */       
/* 4940 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 4941 */       localSQLException2.fillInStackTrace();
/* 4942 */       throw localSQLException2;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void executeLightweightSessionPiggyback(int paramInt1, byte[] paramArrayOfByte, KeywordValueLong[] paramArrayOfKeywordValueLong, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 4955 */     if (this.lusOffset2 == this.lusFunctionId2.length)
/*      */     {
/* 4957 */       int i = this.lusFunctionId2.length;
/* 4958 */       int[] arrayOfInt1 = new int[i * 2];
/* 4959 */       System.arraycopy(this.lusFunctionId2, 0, arrayOfInt1, 0, i);
/* 4960 */       byte[][] arrayOfByte = new byte[i * 2][];
/* 4961 */       System.arraycopy(this.lusSessionId2, 0, arrayOfByte, 0, i);
/* 4962 */       KeywordValueLong[][] arrayOfKeywordValueLong = new KeywordValueLong[i * 2][];
/* 4963 */       System.arraycopy(this.lusInKeyVal2, 0, arrayOfKeywordValueLong, 0, i);
/* 4964 */       int[] arrayOfInt2 = new int[i * 2];
/* 4965 */       System.arraycopy(this.lusInFlags2, 0, arrayOfInt2, 0, i);
/* 4966 */       this.lusFunctionId2 = arrayOfInt1;
/* 4967 */       this.lusSessionId2 = arrayOfByte;
/* 4968 */       this.lusInKeyVal2 = arrayOfKeywordValueLong;
/* 4969 */       this.lusInFlags2 = arrayOfInt2;
/*      */     }
/* 4971 */     this.lusFunctionId2[this.lusOffset2] = paramInt1;
/* 4972 */     this.lusSessionId2[this.lusOffset2] = paramArrayOfByte;
/* 4973 */     this.lusInKeyVal2[this.lusOffset2] = paramArrayOfKeywordValueLong;
/* 4974 */     this.lusInFlags2[this.lusOffset2] = paramInt2;
/* 4975 */     this.lusOffset2 += 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addXSEventListener(XSEventListener paramXSEventListener, Executor paramExecutor)
/*      */     throws SQLException
/*      */   {
/* 4986 */     if (this.lifecycle != 1)
/*      */     {
/* 4988 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 4989 */       ((SQLException)localObject1).fillInStackTrace();
/* 4990 */       throw ((Throwable)localObject1);
/*      */     }
/*      */     
/* 4993 */     Object localObject1 = new NTFEventListener(paramXSEventListener);
/* 4994 */     ((NTFEventListener)localObject1).setExecutor(paramExecutor);
/* 4995 */     synchronized (this.xsListeners)
/*      */     {
/* 4997 */       int i = this.xsListeners.length;
/* 4998 */       for (int j = 0; j < i; j++) {
/* 4999 */         if (this.xsListeners[j].getXSEventListener() == paramXSEventListener)
/*      */         {
/*      */ 
/*      */ 
/* 5003 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 248);
/* 5004 */           localSQLException.fillInStackTrace();
/* 5005 */           throw localSQLException;
/*      */         }
/*      */       }
/*      */       
/* 5009 */       NTFEventListener[] arrayOfNTFEventListener = new NTFEventListener[i + 1];
/* 5010 */       System.arraycopy(this.xsListeners, 0, arrayOfNTFEventListener, 0, i);
/*      */       
/* 5012 */       arrayOfNTFEventListener[i] = localObject1;
/*      */       
/* 5014 */       this.xsListeners = arrayOfNTFEventListener;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addXSEventListener(XSEventListener paramXSEventListener)
/*      */     throws SQLException
/*      */   {
/* 5025 */     addXSEventListener(paramXSEventListener, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void removeXSEventListener(XSEventListener paramXSEventListener)
/*      */     throws SQLException
/*      */   {
/* 5033 */     synchronized (this.xsListeners)
/*      */     {
/*      */ 
/* 5036 */       int i = 0;
/* 5037 */       int j = this.xsListeners.length;
/*      */       
/* 5039 */       for (i = 0; i < j; i++)
/* 5040 */         if (this.xsListeners[i].getXSEventListener() == paramXSEventListener)
/*      */           break;
/* 5042 */       if (i == j)
/*      */       {
/*      */ 
/*      */ 
/* 5046 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 249);
/* 5047 */         ((SQLException)localObject1).fillInStackTrace();
/* 5048 */         throw ((Throwable)localObject1);
/*      */       }
/*      */       
/*      */ 
/* 5052 */       Object localObject1 = new NTFEventListener[j - 1];
/* 5053 */       int k = 0;
/* 5054 */       for (i = 0; i < j; i++) {
/* 5055 */         if (this.xsListeners[i].getXSEventListener() != paramXSEventListener) {
/* 5056 */           localObject1[(k++)] = this.xsListeners[i];
/*      */         }
/*      */       }
/* 5059 */       this.xsListeners = ((NTFEventListener[])localObject1);
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
/*      */   void notify(final NTFXSEvent paramNTFXSEvent)
/*      */   {
/* 5072 */     NTFEventListener[] arrayOfNTFEventListener = this.xsListeners;
/*      */     
/*      */ 
/*      */ 
/* 5076 */     int i = arrayOfNTFEventListener.length;
/* 5077 */     for (int j = 0; j < i; j++)
/*      */     {
/* 5079 */       Executor localExecutor = arrayOfNTFEventListener[j].getExecutor();
/* 5080 */       if (localExecutor != null)
/*      */       {
/* 5082 */         final XSEventListener localXSEventListener = arrayOfNTFEventListener[j].getXSEventListener();
/*      */         
/* 5084 */         localExecutor.execute(new Runnable() {
/*      */           public void run() {
/* 5086 */             localXSEventListener.onXSEvent(paramNTFXSEvent);
/*      */           }
/*      */         });
/*      */       }
/*      */       else
/*      */       {
/* 5092 */         arrayOfNTFEventListener[j].getXSEventListener().onXSEvent(paramNTFXSEvent);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final boolean hasServerCompileTimeCapability(int paramInt1, int paramInt2)
/*      */   {
/* 5115 */     boolean bool = false;
/* 5116 */     if ((this.serverCompileTimeCapabilities != null) && (this.serverCompileTimeCapabilities.length > paramInt1) && ((this.serverCompileTimeCapabilities[paramInt1] & paramInt2) != 0))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 5121 */       bool = true;
/*      */     }
/* 5123 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   long doGetCurrentSCN()
/*      */     throws SQLException
/*      */   {
/* 5131 */     return this.outScn;
/*      */   }
/*      */   
/*      */ 
/*      */   EnumSet<OracleConnection.TransactionState> doGetTransactionState()
/*      */     throws SQLException
/*      */   {
/* 5138 */     EnumSet localEnumSet = EnumSet.noneOf(OracleConnection.TransactionState.class);
/* 5139 */     if ((this.eocs & 0x1) != 0)
/*      */     {
/* 5141 */       localEnumSet.add(OracleConnection.TransactionState.TRANSACTION_READONLY);
/*      */     }
/* 5143 */     if ((this.eocs & 0x2) != 0)
/*      */     {
/* 5145 */       localEnumSet.add(OracleConnection.TransactionState.TRANSACTION_STARTED);
/*      */     }
/* 5147 */     if ((this.eocs & 0x4) != 0)
/*      */     {
/* 5149 */       localEnumSet.add(OracleConnection.TransactionState.TRANSACTION_ENDED);
/*      */     }
/* 5151 */     if ((this.eocs & 0x400) != 0)
/*      */     {
/* 5153 */       localEnumSet.add(OracleConnection.TransactionState.TRANSACTION_INTENTION);
/*      */     }
/* 5155 */     return localEnumSet;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isConnectionSocketKeepAlive()
/*      */     throws SocketException
/*      */   {
/* 5162 */     return this.net.isConnectionSocketKeepAlive();
/*      */   }
/*      */   
/*      */ 
/*      */   byte getNextSeqNumber()
/*      */   {
/* 5168 */     if (this.currentTTCSeqNumber == Byte.MAX_VALUE)
/*      */     {
/* 5170 */       this.currentTTCSeqNumber = 1;
/* 5171 */       return this.currentTTCSeqNumber;
/*      */     }
/*      */     
/* 5174 */     return this.currentTTCSeqNumber = (byte)(this.currentTTCSeqNumber + 1);
/*      */   }
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */