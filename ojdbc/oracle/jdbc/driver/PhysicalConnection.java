/*       */ package oracle.jdbc.driver;
/*       */ 
/*       */ import java.lang.reflect.Field;
/*       */ import java.math.BigDecimal;
/*       */ import java.math.BigInteger;
/*       */ import java.net.InetAddress;
/*       */ import java.net.UnknownHostException;
/*       */ import java.sql.Array;
/*       */ import java.sql.CallableStatement;
/*       */ import java.sql.Connection;
/*       */ import java.sql.Date;
/*       */ import java.sql.DriverManager;
/*       */ import java.sql.NClob;
/*       */ import java.sql.PreparedStatement;
/*       */ import java.sql.ResultSet;
/*       */ import java.sql.SQLException;
/*       */ import java.sql.SQLWarning;
/*       */ import java.sql.SQLXML;
/*       */ import java.sql.Savepoint;
/*       */ import java.sql.Statement;
/*       */ import java.sql.Time;
/*       */ import java.sql.Timestamp;
/*       */ import java.util.ArrayList;
/*       */ import java.util.Calendar;
/*       */ import java.util.EnumSet;
/*       */ import java.util.Enumeration;
/*       */ import java.util.Hashtable;
/*       */ import java.util.Iterator;
/*       */ import java.util.Map;
/*       */ import java.util.Properties;
/*       */ import java.util.TimeZone;
/*       */ import java.util.Vector;
/*       */ import java.util.concurrent.Executor;
/*       */ import java.util.regex.Pattern;
/*       */ import oracle.jdbc.OracleConnection.CommitOption;
/*       */ import oracle.jdbc.OracleConnection.DatabaseShutdownMode;
/*       */ import oracle.jdbc.OracleConnection.DatabaseStartupMode;
/*       */ import oracle.jdbc.OracleOCIFailover;
/*       */ import oracle.jdbc.OracleSQLPermission;
/*       */ import oracle.jdbc.aq.AQDequeueOptions;
/*       */ import oracle.jdbc.aq.AQEnqueueOptions;
/*       */ import oracle.jdbc.aq.AQMessage;
/*       */ import oracle.jdbc.aq.AQNotificationRegistration;
/*       */ import oracle.jdbc.dcn.DatabaseChangeRegistration;
/*       */ import oracle.jdbc.internal.KeywordValueLong;
/*       */ import oracle.jdbc.internal.OracleConnection.BufferCacheStatistics;
/*       */ import oracle.jdbc.internal.OracleConnection.InstanceProperty;
/*       */ import oracle.jdbc.internal.OracleConnection.TransactionState;
/*       */ import oracle.jdbc.internal.OracleConnection.XSOperationCode;
/*       */ import oracle.jdbc.internal.XSEventListener;
/*       */ import oracle.jdbc.internal.XSNamespace;
/*       */ import oracle.jdbc.oracore.OracleTypeADT;
/*       */ import oracle.jdbc.oracore.OracleTypeCLOB;
/*       */ import oracle.jdbc.pool.OracleConnectionCacheCallback;
/*       */ import oracle.jdbc.pool.OraclePooledConnection;
/*       */ import oracle.net.nt.CustomSSLSocketFactory;
/*       */ import oracle.security.pki.OracleSecretStore;
/*       */ import oracle.security.pki.OracleWallet;
/*       */ import oracle.sql.ARRAY;
/*       */ import oracle.sql.ArrayDescriptor;
/*       */ import oracle.sql.BFILE;
/*       */ import oracle.sql.BINARY_DOUBLE;
/*       */ import oracle.sql.BINARY_FLOAT;
/*       */ import oracle.sql.BLOB;
/*       */ import oracle.sql.CLOB;
/*       */ import oracle.sql.CharacterSet;
/*       */ import oracle.sql.CustomDatum;
/*       */ import oracle.sql.DATE;
/*       */ import oracle.sql.Datum;
/*       */ import oracle.sql.INTERVALDS;
/*       */ import oracle.sql.INTERVALYM;
/*       */ import oracle.sql.NCLOB;
/*       */ import oracle.sql.NUMBER;
/*       */ import oracle.sql.SQLName;
/*       */ import oracle.sql.StructDescriptor;
/*       */ import oracle.sql.TIMESTAMP;
/*       */ import oracle.sql.TIMESTAMPLTZ;
/*       */ import oracle.sql.TIMESTAMPTZ;
/*       */ import oracle.sql.TIMEZONETAB;
/*       */ import oracle.sql.TypeDescriptor;
/*       */ 
/*       */ abstract class PhysicalConnection extends OracleConnection
/*       */ {
/*       */   public static final String SECRET_STORE_CONNECT = "oracle.security.client.connect_string";
/*       */   public static final String SECRET_STORE_USERNAME = "oracle.security.client.username";
/*       */   public static final String SECRET_STORE_PASSWORD = "oracle.security.client.password";
/*       */   public static final String SECRET_STORE_DEFAULT_USERNAME = "oracle.security.client.default_username";
/*       */   public static final String SECRET_STORE_DEFAULT_PASSWORD = "oracle.security.client.default_password";
/*    89 */   static final CRC64 CHECKSUM = new CRC64();
/*       */   public static final char slash_character = '/';
/*       */   public static final char at_sign_character = '@';
/*       */   public static final char left_square_bracket_character = '[';
/*       */   public static final char right_square_bracket_character = ']';
/*    94 */   static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*    99 */   long outScn = 0L;
/*       */   
/*   101 */   char[][] charOutput = new char[1][];
/*   102 */   byte[][] byteOutput = new byte[1][];
/*   103 */   short[][] shortOutput = new short[1][];
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*   109 */   Properties sessionProperties = null;
/*       */   
/*       */   boolean retainV9BindBehavior;
/*       */   
/*       */   String userName;
/*       */   
/*       */   String database;
/*       */   
/*       */   boolean autocommit;
/*       */   
/*       */   String protocol;
/*       */   
/*       */   int streamChunkSize;
/*       */   
/*       */   boolean setFloatAndDoubleUseBinary;
/*       */   String thinVsessionTerminal;
/*       */   String thinVsessionMachine;
/*       */   String thinVsessionOsuser;
/*       */   String thinVsessionProgram;
/*       */   String thinVsessionProcess;
/*       */   String thinVsessionIname;
/*       */   String thinVsessionEname;
/*       */   String thinNetProfile;
/*       */   String thinNetAuthenticationServices;
/*       */   String thinNetAuthenticationKrb5Mutual;
/*       */   String thinNetAuthenticationKrb5CcName;
/*       */   String thinNetEncryptionLevel;
/*       */   String thinNetEncryptionTypes;
/*       */   String thinNetChecksumLevel;
/*       */   String thinNetChecksumTypes;
/*       */   String thinNetCryptoSeed;
/*       */   boolean thinTcpNoDelay;
/*       */   String thinReadTimeout;
/*       */   String thinNetConnectTimeout;
/*       */   boolean thinNetDisableOutOfBandBreak;
/*       */   boolean thinNetUseZeroCopyIO;
/*       */   boolean thinNetEnableSDP;
/*       */   boolean use1900AsYearForTime;
/*       */   boolean timestamptzInGmt;
/*       */   boolean timezoneAsRegion;
/*       */   String thinSslServerDnMatch;
/*       */   String thinSslVersion;
/*       */   String thinSslCipherSuites;
/*       */   String thinJavaxNetSslKeystore;
/*       */   String thinJavaxNetSslKeystoretype;
/*       */   String thinJavaxNetSslKeystorepassword;
/*       */   String thinJavaxNetSslTruststore;
/*       */   String thinJavaxNetSslTruststoretype;
/*       */   String thinJavaxNetSslTruststorepassword;
/*       */   String thinSslKeymanagerfactoryAlgorithm;
/*       */   String thinSslTrustmanagerfactoryAlgorithm;
/*       */   String thinNetOldsyntax;
/*       */   String thinNamingContextInitial;
/*       */   String thinNamingProviderUrl;
/*       */   String thinNamingSecurityAuthentication;
/*       */   String thinNamingSecurityPrincipal;
/*       */   String thinNamingSecurityCredentials;
/*       */   String walletLocation;
/*       */   String walletPassword;
/*       */   String proxyClientName;
/*       */   boolean useNio;
/*       */   String ociDriverCharset;
/*       */   String editionName;
/*       */   String logonCap;
/*       */   String internalLogon;
/*       */   boolean createDescriptorUseCurrentSchemaForSchemaName;
/*       */   long ociSvcCtxHandle;
/*       */   long ociEnvHandle;
/*       */   long ociErrHandle;
/*       */   boolean prelimAuth;
/*       */   boolean nlsLangBackdoor;
/*       */   String setNewPassword;
/*       */   boolean spawnNewThreadToCancel;
/*       */   int defaultExecuteBatch;
/*       */   int defaultRowPrefetch;
/*       */   int defaultLobPrefetchSize;
/*       */   boolean enableDataInLocator;
/*       */   boolean enableReadDataInLocator;
/*       */   boolean overrideEnableReadDataInLocator;
/*       */   boolean reportRemarks;
/*       */   boolean includeSynonyms;
/*       */   boolean restrictGettables;
/*       */   boolean accumulateBatchResult;
/*       */   boolean useFetchSizeWithLongColumn;
/*       */   boolean processEscapes;
/*       */   boolean fixedString;
/*       */   boolean defaultnchar;
/*       */   boolean permitTimestampDateMismatch;
/*       */   String resourceManagerId;
/*       */   boolean disableDefinecolumntype;
/*       */   boolean convertNcharLiterals;
/*       */   boolean j2ee13Compliant;
/*       */   boolean mapDateToTimestamp;
/*       */   boolean useThreadLocalBufferCache;
/*       */   String driverNameAttribute;
/*       */   int maxCachedBufferSize;
/*       */   int implicitStatementCacheSize;
/*       */   boolean lobStreamPosStandardCompliant;
/*       */   boolean isStrictAsciiConversion;
/*       */   boolean isQuickAsciiConversion;
/*       */   boolean thinForceDnsLoadBalancing;
/*       */   boolean enableJavaNetFastPath;
/*       */   boolean enableTempLobRefCnt;
/*       */   boolean plsqlVarcharParameter4KOnly;
/*       */   boolean keepAlive;
/*       */   public boolean calculateChecksum;
/*       */   String url;
/*       */   String savedUser;
/*       */   int commitOption;
/*   218 */   int ociConnectionPoolMinLimit = 0;
/*   219 */   int ociConnectionPoolMaxLimit = 0;
/*   220 */   int ociConnectionPoolIncrement = 0;
/*   221 */   int ociConnectionPoolTimeout = 0;
/*   222 */   boolean ociConnectionPoolNoWait = false;
/*   223 */   boolean ociConnectionPoolTransactionDistributed = false;
/*   224 */   String ociConnectionPoolLogonMode = null;
/*   225 */   boolean ociConnectionPoolIsPooling = false;
/*   226 */   Object ociConnectionPoolObject = null;
/*   227 */   Object ociConnectionPoolConnID = null;
/*   228 */   String ociConnectionPoolProxyType = null;
/*   229 */   Integer ociConnectionPoolProxyNumRoles = Integer.valueOf(0);
/*   230 */   Object ociConnectionPoolProxyRoles = null;
/*   231 */   String ociConnectionPoolProxyUserName = null;
/*   232 */   String ociConnectionPoolProxyPassword = null;
/*   233 */   String ociConnectionPoolProxyDistinguishedName = null;
/*   234 */   Object ociConnectionPoolProxyCertificate = null;
/*       */   
/*   236 */   static NTFManager ntfManager = new NTFManager();
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*   247 */   public int protocolId = -3;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   OracleTimeout timeout;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   DBConversion conversion;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   boolean xaWantsError;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   boolean usingXA;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*   272 */   int txnMode = 0;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   byte[] fdo;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   Boolean bigEndian;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   OracleStatement statements;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   int lifecycle;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   static final int OPEN = 1;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   static final int CLOSING = 2;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   static final int CLOSED = 4;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   static final int ABORTED = 8;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   static final int BLOCKED = 16;
/*       */   
/*       */ 
/*       */ 
/*   321 */   boolean clientIdSet = false;
/*   322 */   String clientId = null;
/*       */   
/*       */ 
/*       */ 
/*       */   int txnLevel;
/*       */   
/*       */ 
/*       */ 
/*       */   Map map;
/*       */   
/*       */ 
/*       */ 
/*       */   Map javaObjectMap;
/*       */   
/*       */ 
/*       */ 
/*   338 */   final Hashtable[] descriptorCacheStack = new Hashtable[2];
/*       */   
/*   340 */   int dci = 0;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   OracleStatement statementHoldingLine;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*   352 */   oracle.jdbc.OracleDatabaseMetaData databaseMetaData = null;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   LogicalConnection logicalConnectionAttached;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*   362 */   boolean isProxy = false;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*   367 */   OracleSql sqlObj = null;
/*       */   
/*       */ 
/*   370 */   SQLWarning sqlWarning = null;
/*       */   
/*       */ 
/*   373 */   boolean readOnly = false;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*   378 */   LRUStatementCache statementCache = null;
/*       */   
/*       */ 
/*   381 */   boolean clearStatementMetaData = false;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*   387 */   OracleCloseCallback closeCallback = null;
/*   388 */   Object privateData = null;
/*       */   
/*       */ 
/*   391 */   Statement savepointStatement = null;
/*       */   
/*       */ 
/*   394 */   boolean isUsable = true;
/*       */   
/*   396 */   TimeZone defaultTimeZone = null;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*   421 */   final int[] endToEndMaxLength = new int[4];
/*       */   
/*   423 */   boolean endToEndAnyChanged = false;
/*   424 */   final boolean[] endToEndHasChanged = new boolean[4];
/*   425 */   short endToEndECIDSequenceNumber = Short.MIN_VALUE;
/*       */   
/*       */ 
/*       */   static final int DMS_NONE = 0;
/*       */   
/*       */ 
/*       */   static final int DMS_10G = 1;
/*       */   
/*       */   static final int DMS_11 = 2;
/*       */   
/*   435 */   String[] endToEndValues = null;
/*   436 */   final int whichDMS = 0;
/*       */   
/*       */ 
/*       */ 
/*   440 */   oracle.jdbc.OracleConnection wrapper = null;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int minVcsBindSize;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int maxRawBytesSql;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int maxRawBytesPlsql;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int maxVcsCharsSql;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int maxVcsNCharsSql;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int maxVcsBytesPlsql;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int maxIbtVarcharElementLength;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*   493 */   String instanceName = null;
/*       */   OracleDriverExtension driverExtension;
/*       */   static final String uninitializedMarker = "";
/*   496 */   String databaseProductVersion = "";
/*   497 */   short versionNumber = -1;
/*       */   
/*       */   int namedTypeAccessorByteLen;
/*       */   
/*       */   int refTypeAccessorByteLen;
/*       */   
/*       */   CharacterSet setCHARCharSetObj;
/*       */   
/*       */   CharacterSet setCHARNCharSetObj;
/*       */   
/*       */   protected final Object cancelInProgressLockForThin;
/*   508 */   boolean plsqlCompilerWarnings = false;
/*       */   
/*       */   protected PhysicalConnection()
/*       */   {
/*   512 */     this.cancelInProgressLockForThin = new Object();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   PhysicalConnection(String paramString, Properties paramProperties, OracleDriverExtension paramOracleDriverExtension)
/*       */     throws SQLException
/*       */   {
/*   532 */     this.cancelInProgressLockForThin = new Object();
/*       */     
/*   534 */     readConnectionProperties(paramString, paramProperties);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*   540 */     this.driverExtension = paramOracleDriverExtension;
/*       */     
/*       */ 
/*   543 */     initialize(null, null, null);
/*       */     
/*   545 */     this.logicalConnectionAttached = null;
/*       */     
/*       */     try
/*       */     {
/*   549 */       needLine();
/*       */       
/*       */ 
/*       */ 
/*   553 */       logon();
/*       */       
/*   555 */       setAutoCommit(this.autocommit);
/*       */       
/*       */ 
/*   558 */       if (getVersionNumber() >= 11202)
/*       */       {
/*   560 */         this.minVcsBindSize = 4001;
/*   561 */         this.maxRawBytesSql = 4000;
/*   562 */         this.maxRawBytesPlsql = 32766;
/*   563 */         this.maxVcsCharsSql = 32766;
/*   564 */         this.maxVcsNCharsSql = 32766;
/*   565 */         this.maxVcsBytesPlsql = 32766;
/*   566 */         this.maxIbtVarcharElementLength = 32766;
/*       */         
/*   568 */         this.endToEndMaxLength[0] = 64;
/*   569 */         this.endToEndMaxLength[1] = 64;
/*   570 */         this.endToEndMaxLength[2] = 64;
/*   571 */         this.endToEndMaxLength[3] = 64;
/*       */       }
/*   573 */       else if (getVersionNumber() >= 11000)
/*       */       {
/*   575 */         this.minVcsBindSize = 4001;
/*   576 */         this.maxRawBytesSql = 4000;
/*   577 */         this.maxRawBytesPlsql = 32766;
/*   578 */         this.maxVcsCharsSql = 32766;
/*   579 */         this.maxVcsNCharsSql = 32766;
/*   580 */         this.maxVcsBytesPlsql = 32766;
/*   581 */         this.maxIbtVarcharElementLength = 32766;
/*       */         
/*   583 */         this.endToEndMaxLength[0] = 32;
/*   584 */         this.endToEndMaxLength[1] = 64;
/*   585 */         this.endToEndMaxLength[2] = 64;
/*   586 */         this.endToEndMaxLength[3] = 48;
/*       */       }
/*   588 */       else if (getVersionNumber() >= 10000)
/*       */       {
/*   590 */         this.minVcsBindSize = 4001;
/*   591 */         this.maxRawBytesSql = 2000;
/*   592 */         this.maxRawBytesPlsql = 32512;
/*   593 */         this.maxVcsCharsSql = 32766;
/*   594 */         this.maxVcsNCharsSql = 32766;
/*   595 */         this.maxVcsBytesPlsql = 32512;
/*   596 */         this.maxIbtVarcharElementLength = 32766;
/*       */         
/*   598 */         this.endToEndMaxLength[0] = 32;
/*   599 */         this.endToEndMaxLength[1] = 64;
/*   600 */         this.endToEndMaxLength[2] = 64;
/*   601 */         this.endToEndMaxLength[3] = 48;
/*       */       }
/*   603 */       else if (getVersionNumber() >= 9200)
/*       */       {
/*   605 */         this.minVcsBindSize = 4001;
/*   606 */         this.maxRawBytesSql = 2000;
/*   607 */         this.maxRawBytesPlsql = 32512;
/*   608 */         this.maxVcsCharsSql = 32766;
/*   609 */         this.maxVcsNCharsSql = 32766;
/*   610 */         this.maxVcsBytesPlsql = 32512;
/*   611 */         this.maxIbtVarcharElementLength = 32766;
/*       */         
/*   613 */         this.endToEndMaxLength[0] = 32;
/*   614 */         this.endToEndMaxLength[1] = 64;
/*   615 */         this.endToEndMaxLength[2] = 64;
/*   616 */         this.endToEndMaxLength[3] = 48;
/*       */       }
/*       */       else
/*       */       {
/*   620 */         this.minVcsBindSize = 4001;
/*   621 */         this.maxRawBytesSql = 2000;
/*   622 */         this.maxRawBytesPlsql = 2000;
/*   623 */         this.maxVcsCharsSql = 4000;
/*   624 */         this.maxVcsNCharsSql = 4000;
/*   625 */         this.maxVcsBytesPlsql = 4000;
/*   626 */         this.maxIbtVarcharElementLength = 4000;
/*       */         
/*   628 */         this.endToEndMaxLength[0] = 32;
/*   629 */         this.endToEndMaxLength[1] = 64;
/*   630 */         this.endToEndMaxLength[2] = 64;
/*   631 */         this.endToEndMaxLength[3] = 48;
/*       */       }
/*       */       
/*   634 */       if (getVersionNumber() >= 10000)
/*       */       {
/*       */ 
/*   637 */         this.retainV9BindBehavior = false;
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*   642 */       initializeSetCHARCharSetObjs();
/*       */       
/*       */ 
/*   645 */       if (this.implicitStatementCacheSize > 0) {
/*   646 */         setStatementCacheSize(this.implicitStatementCacheSize);
/*   647 */         setImplicitCachingEnabled(true);
/*       */ 
/*       */ 
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*       */     }
/*       */     catch (SQLException localSQLException1)
/*       */     {
/*       */ 
/*       */ 
/*   659 */       this.lifecycle = 2;
/*       */       try
/*       */       {
/*   662 */         logoff();
/*       */       }
/*       */       catch (SQLException localSQLException2) {}
/*   665 */       this.lifecycle = 4;
/*       */       
/*   667 */       throw localSQLException1;
/*       */     }
/*       */     
/*       */ 
/*   671 */     this.txnMode = 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private static final String propertyVariableName(String paramString)
/*       */   {
/*   687 */     char[] arrayOfChar = new char[paramString.length()];
/*   688 */     paramString.getChars(0, paramString.length(), arrayOfChar, 0);
/*   689 */     String str = "";
/*   690 */     for (int i = 0; i < arrayOfChar.length; i++)
/*       */     {
/*   692 */       if (Character.isUpperCase(arrayOfChar[i]))
/*   693 */         str = str + "_";
/*   694 */       str = str + Character.toUpperCase(arrayOfChar[i]);
/*       */     }
/*   696 */     return str;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private void initializeUserDefaults(Properties paramProperties)
/*       */   {
/*   711 */     for (String str : OracleDriver.DEFAULT_CONNECTION_PROPERTIES.stringPropertyNames()) {
/*   712 */       if (!paramProperties.containsKey(str)) {
/*   713 */         paramProperties.setProperty(str, OracleDriver.DEFAULT_CONNECTION_PROPERTIES.getProperty(str));
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private void readConnectionProperties(String paramString, Properties paramProperties)
/*       */     throws SQLException
/*       */   {
/*   742 */     initializeUserDefaults(paramProperties);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*   747 */     String str1 = null;
/*       */     
/*       */ 
/*   750 */     str1 = null;
/*   751 */     if (paramProperties != null)
/*       */     {
/*   753 */       str1 = paramProperties.getProperty("oracle.jdbc.RetainV9LongBindBehavior");
/*       */     }
/*   755 */     if (str1 == null)
/*   756 */       str1 = getSystemProperty("oracle.jdbc.RetainV9LongBindBehavior", null);
/*   757 */     if (str1 == null) {
/*   758 */       str1 = "false";
/*       */     }
/*       */     
/*   761 */     this.retainV9BindBehavior = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*   764 */     str1 = null;
/*   765 */     if (paramProperties != null)
/*       */     {
/*   767 */       str1 = paramProperties.getProperty("user");
/*   768 */       if (str1 == null)
/*   769 */         str1 = paramProperties.getProperty("oracle.jdbc.user");
/*       */     }
/*   771 */     if (str1 == null)
/*   772 */       str1 = getSystemProperty("oracle.jdbc.user", null);
/*   773 */     if (str1 == null) {
/*   774 */       str1 = null;
/*       */     }
/*       */     
/*   777 */     this.userName = str1;
/*       */     
/*       */ 
/*   780 */     str1 = null;
/*   781 */     if (paramProperties != null)
/*       */     {
/*   783 */       str1 = paramProperties.getProperty("database");
/*   784 */       if (str1 == null)
/*   785 */         str1 = paramProperties.getProperty("oracle.jdbc.database");
/*       */     }
/*   787 */     if (str1 == null)
/*   788 */       str1 = getSystemProperty("oracle.jdbc.database", null);
/*   789 */     if (str1 == null) {
/*   790 */       str1 = null;
/*       */     }
/*       */     
/*   793 */     this.database = str1;
/*       */     
/*       */ 
/*   796 */     str1 = null;
/*   797 */     if (paramProperties != null)
/*       */     {
/*   799 */       str1 = paramProperties.getProperty("autoCommit");
/*   800 */       if (str1 == null)
/*   801 */         str1 = paramProperties.getProperty("oracle.jdbc.autoCommit");
/*       */     }
/*   803 */     if (str1 == null)
/*   804 */       str1 = getSystemProperty("oracle.jdbc.autoCommit", null);
/*   805 */     if (str1 == null) {
/*   806 */       str1 = "true";
/*       */     }
/*       */     
/*   809 */     this.autocommit = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*   812 */     str1 = null;
/*   813 */     if (paramProperties != null)
/*       */     {
/*   815 */       str1 = paramProperties.getProperty("protocol");
/*   816 */       if (str1 == null)
/*   817 */         str1 = paramProperties.getProperty("oracle.jdbc.protocol");
/*       */     }
/*   819 */     if (str1 == null)
/*   820 */       str1 = getSystemProperty("oracle.jdbc.protocol", null);
/*   821 */     if (str1 == null) {
/*   822 */       str1 = null;
/*       */     }
/*       */     
/*   825 */     this.protocol = str1;
/*       */     
/*       */ 
/*   828 */     str1 = null;
/*   829 */     if (paramProperties != null)
/*       */     {
/*   831 */       str1 = paramProperties.getProperty("oracle.jdbc.StreamChunkSize");
/*       */     }
/*   833 */     if (str1 == null)
/*   834 */       str1 = getSystemProperty("oracle.jdbc.StreamChunkSize", null);
/*   835 */     if (str1 == null) {
/*   836 */       str1 = "16384";
/*       */     }
/*       */     try {
/*   839 */       this.streamChunkSize = Integer.parseInt(str1);
/*       */ 
/*       */     }
/*       */     catch (NumberFormatException localNumberFormatException1)
/*       */     {
/*   844 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 190, "Property is 'streamChunkSize'");
/*   845 */       ((SQLException)localObject2).fillInStackTrace();
/*   846 */       throw ((Throwable)localObject2);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*   852 */     str1 = null;
/*   853 */     if (paramProperties != null)
/*       */     {
/*   855 */       str1 = paramProperties.getProperty("SetFloatAndDoubleUseBinary");
/*   856 */       if (str1 == null)
/*   857 */         str1 = paramProperties.getProperty("oracle.jdbc.SetFloatAndDoubleUseBinary");
/*       */     }
/*   859 */     if (str1 == null)
/*   860 */       str1 = getSystemProperty("oracle.jdbc.SetFloatAndDoubleUseBinary", null);
/*   861 */     if (str1 == null) {
/*   862 */       str1 = "false";
/*       */     }
/*       */     
/*   865 */     this.setFloatAndDoubleUseBinary = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*   868 */     str1 = null;
/*   869 */     if (paramProperties != null)
/*       */     {
/*   871 */       str1 = paramProperties.getProperty("v$session.terminal");
/*   872 */       if (str1 == null)
/*   873 */         str1 = paramProperties.getProperty("oracle.jdbc.v$session.terminal");
/*       */     }
/*   875 */     if (str1 == null)
/*   876 */       str1 = getSystemProperty("oracle.jdbc.v$session.terminal", null);
/*   877 */     if (str1 == null) {
/*   878 */       str1 = "unknown";
/*       */     }
/*       */     
/*   881 */     this.thinVsessionTerminal = str1;
/*       */     
/*       */ 
/*   884 */     str1 = null;
/*   885 */     if (paramProperties != null)
/*       */     {
/*   887 */       str1 = paramProperties.getProperty("v$session.machine");
/*   888 */       if (str1 == null)
/*   889 */         str1 = paramProperties.getProperty("oracle.jdbc.v$session.machine");
/*       */     }
/*   891 */     if (str1 == null)
/*   892 */       str1 = getSystemProperty("oracle.jdbc.v$session.machine", null);
/*   893 */     if (str1 == null) {
/*   894 */       str1 = null;
/*       */     }
/*       */     
/*   897 */     this.thinVsessionMachine = str1;
/*       */     
/*       */ 
/*   900 */     str1 = null;
/*   901 */     if (paramProperties != null)
/*       */     {
/*   903 */       str1 = paramProperties.getProperty("v$session.osuser");
/*   904 */       if (str1 == null)
/*   905 */         str1 = paramProperties.getProperty("oracle.jdbc.v$session.osuser");
/*       */     }
/*   907 */     if (str1 == null)
/*   908 */       str1 = getSystemProperty("oracle.jdbc.v$session.osuser", null);
/*   909 */     if (str1 == null) {
/*   910 */       str1 = null;
/*       */     }
/*       */     
/*   913 */     this.thinVsessionOsuser = str1;
/*       */     
/*       */ 
/*   916 */     str1 = null;
/*   917 */     if (paramProperties != null)
/*       */     {
/*   919 */       str1 = paramProperties.getProperty("v$session.program");
/*   920 */       if (str1 == null)
/*   921 */         str1 = paramProperties.getProperty("oracle.jdbc.v$session.program");
/*       */     }
/*   923 */     if (str1 == null)
/*   924 */       str1 = getSystemProperty("oracle.jdbc.v$session.program", null);
/*   925 */     if (str1 == null) {
/*   926 */       str1 = "JDBC Thin Client";
/*       */     }
/*       */     
/*   929 */     this.thinVsessionProgram = str1;
/*       */     
/*       */ 
/*   932 */     str1 = null;
/*   933 */     if (paramProperties != null)
/*       */     {
/*   935 */       str1 = paramProperties.getProperty("v$session.process");
/*   936 */       if (str1 == null)
/*   937 */         str1 = paramProperties.getProperty("oracle.jdbc.v$session.process");
/*       */     }
/*   939 */     if (str1 == null)
/*   940 */       str1 = getSystemProperty("oracle.jdbc.v$session.process", null);
/*   941 */     if (str1 == null) {
/*   942 */       str1 = "1234";
/*       */     }
/*       */     
/*   945 */     this.thinVsessionProcess = str1;
/*       */     
/*       */ 
/*   948 */     str1 = null;
/*   949 */     if (paramProperties != null)
/*       */     {
/*   951 */       str1 = paramProperties.getProperty("v$session.iname");
/*   952 */       if (str1 == null)
/*   953 */         str1 = paramProperties.getProperty("oracle.jdbc.v$session.iname");
/*       */     }
/*   955 */     if (str1 == null)
/*   956 */       str1 = getSystemProperty("oracle.jdbc.v$session.iname", null);
/*   957 */     if (str1 == null) {
/*   958 */       str1 = "jdbc_ttc_impl";
/*       */     }
/*       */     
/*   961 */     this.thinVsessionIname = str1;
/*       */     
/*       */ 
/*   964 */     str1 = null;
/*   965 */     if (paramProperties != null)
/*       */     {
/*   967 */       str1 = paramProperties.getProperty("v$session.ename");
/*   968 */       if (str1 == null)
/*   969 */         str1 = paramProperties.getProperty("oracle.jdbc.v$session.ename");
/*       */     }
/*   971 */     if (str1 == null)
/*   972 */       str1 = getSystemProperty("oracle.jdbc.v$session.ename", null);
/*   973 */     if (str1 == null) {
/*   974 */       str1 = null;
/*       */     }
/*       */     
/*   977 */     this.thinVsessionEname = str1;
/*       */     
/*       */ 
/*   980 */     str1 = null;
/*   981 */     if (paramProperties != null)
/*       */     {
/*   983 */       str1 = paramProperties.getProperty("oracle.net.profile");
/*       */     }
/*   985 */     if (str1 == null)
/*   986 */       str1 = getSystemProperty("oracle.net.profile", null);
/*   987 */     if (str1 == null) {
/*   988 */       str1 = null;
/*       */     }
/*       */     
/*   991 */     this.thinNetProfile = str1;
/*       */     
/*       */ 
/*   994 */     str1 = null;
/*   995 */     if (paramProperties != null)
/*       */     {
/*   997 */       str1 = paramProperties.getProperty("oracle.net.authentication_services");
/*       */     }
/*   999 */     if (str1 == null)
/*  1000 */       str1 = getSystemProperty("oracle.net.authentication_services", null);
/*  1001 */     if (str1 == null) {
/*  1002 */       str1 = null;
/*       */     }
/*       */     
/*  1005 */     this.thinNetAuthenticationServices = str1;
/*       */     
/*       */ 
/*  1008 */     str1 = null;
/*  1009 */     if (paramProperties != null)
/*       */     {
/*  1011 */       str1 = paramProperties.getProperty("oracle.net.kerberos5_mutual_authentication");
/*       */     }
/*  1013 */     if (str1 == null)
/*  1014 */       str1 = getSystemProperty("oracle.net.kerberos5_mutual_authentication", null);
/*  1015 */     if (str1 == null) {
/*  1016 */       str1 = null;
/*       */     }
/*       */     
/*  1019 */     this.thinNetAuthenticationKrb5Mutual = str1;
/*       */     
/*       */ 
/*  1022 */     str1 = null;
/*  1023 */     if (paramProperties != null)
/*       */     {
/*  1025 */       str1 = paramProperties.getProperty("oracle.net.kerberos5_cc_name");
/*       */     }
/*  1027 */     if (str1 == null)
/*  1028 */       str1 = getSystemProperty("oracle.net.kerberos5_cc_name", null);
/*  1029 */     if (str1 == null) {
/*  1030 */       str1 = null;
/*       */     }
/*       */     
/*  1033 */     this.thinNetAuthenticationKrb5CcName = str1;
/*       */     
/*       */ 
/*  1036 */     str1 = null;
/*  1037 */     if (paramProperties != null)
/*       */     {
/*  1039 */       str1 = paramProperties.getProperty("oracle.net.encryption_client");
/*       */     }
/*  1041 */     if (str1 == null)
/*  1042 */       str1 = getSystemProperty("oracle.net.encryption_client", null);
/*  1043 */     if (str1 == null) {
/*  1044 */       str1 = null;
/*       */     }
/*       */     
/*  1047 */     this.thinNetEncryptionLevel = str1;
/*       */     
/*       */ 
/*  1050 */     str1 = null;
/*  1051 */     if (paramProperties != null)
/*       */     {
/*  1053 */       str1 = paramProperties.getProperty("oracle.net.encryption_types_client");
/*       */     }
/*  1055 */     if (str1 == null)
/*  1056 */       str1 = getSystemProperty("oracle.net.encryption_types_client", null);
/*  1057 */     if (str1 == null) {
/*  1058 */       str1 = null;
/*       */     }
/*       */     
/*  1061 */     this.thinNetEncryptionTypes = str1;
/*       */     
/*       */ 
/*  1064 */     str1 = null;
/*  1065 */     if (paramProperties != null)
/*       */     {
/*  1067 */       str1 = paramProperties.getProperty("oracle.net.crypto_checksum_client");
/*       */     }
/*  1069 */     if (str1 == null)
/*  1070 */       str1 = getSystemProperty("oracle.net.crypto_checksum_client", null);
/*  1071 */     if (str1 == null) {
/*  1072 */       str1 = null;
/*       */     }
/*       */     
/*  1075 */     this.thinNetChecksumLevel = str1;
/*       */     
/*       */ 
/*  1078 */     str1 = null;
/*  1079 */     if (paramProperties != null)
/*       */     {
/*  1081 */       str1 = paramProperties.getProperty("oracle.net.crypto_checksum_types_client");
/*       */     }
/*  1083 */     if (str1 == null)
/*  1084 */       str1 = getSystemProperty("oracle.net.crypto_checksum_types_client", null);
/*  1085 */     if (str1 == null) {
/*  1086 */       str1 = null;
/*       */     }
/*       */     
/*  1089 */     this.thinNetChecksumTypes = str1;
/*       */     
/*       */ 
/*  1092 */     str1 = null;
/*  1093 */     if (paramProperties != null)
/*       */     {
/*  1095 */       str1 = paramProperties.getProperty("oracle.net.crypto_seed");
/*       */     }
/*  1097 */     if (str1 == null)
/*  1098 */       str1 = getSystemProperty("oracle.net.crypto_seed", null);
/*  1099 */     if (str1 == null) {
/*  1100 */       str1 = null;
/*       */     }
/*       */     
/*  1103 */     this.thinNetCryptoSeed = str1;
/*       */     
/*       */ 
/*  1106 */     str1 = null;
/*  1107 */     if (paramProperties != null)
/*       */     {
/*  1109 */       str1 = paramProperties.getProperty("oracle.jdbc.TcpNoDelay");
/*       */     }
/*  1111 */     if (str1 == null)
/*  1112 */       str1 = getSystemProperty("oracle.jdbc.TcpNoDelay", null);
/*  1113 */     if (str1 == null) {
/*  1114 */       str1 = "false";
/*       */     }
/*       */     
/*  1117 */     this.thinTcpNoDelay = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1120 */     str1 = null;
/*  1121 */     if (paramProperties != null)
/*       */     {
/*  1123 */       str1 = paramProperties.getProperty("oracle.jdbc.ReadTimeout");
/*       */     }
/*  1125 */     if (str1 == null)
/*  1126 */       str1 = getSystemProperty("oracle.jdbc.ReadTimeout", null);
/*  1127 */     if (str1 == null) {
/*  1128 */       str1 = null;
/*       */     }
/*       */     
/*  1131 */     this.thinReadTimeout = str1;
/*       */     
/*       */ 
/*  1134 */     str1 = null;
/*  1135 */     if (paramProperties != null)
/*       */     {
/*  1137 */       str1 = paramProperties.getProperty("oracle.net.CONNECT_TIMEOUT");
/*       */     }
/*  1139 */     if (str1 == null)
/*  1140 */       str1 = getSystemProperty("oracle.net.CONNECT_TIMEOUT", null);
/*  1141 */     if (str1 == null) {
/*  1142 */       str1 = null;
/*       */     }
/*       */     
/*  1145 */     this.thinNetConnectTimeout = str1;
/*       */     
/*       */ 
/*  1148 */     str1 = null;
/*  1149 */     if (paramProperties != null)
/*       */     {
/*  1151 */       str1 = paramProperties.getProperty("oracle.net.disableOob");
/*       */     }
/*  1153 */     if (str1 == null)
/*  1154 */       str1 = getSystemProperty("oracle.net.disableOob", null);
/*  1155 */     if (str1 == null) {
/*  1156 */       str1 = "false";
/*       */     }
/*       */     
/*  1159 */     this.thinNetDisableOutOfBandBreak = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1162 */     str1 = null;
/*  1163 */     if (paramProperties != null)
/*       */     {
/*  1165 */       str1 = paramProperties.getProperty("oracle.net.useZeroCopyIO");
/*       */     }
/*  1167 */     if (str1 == null)
/*  1168 */       str1 = getSystemProperty("oracle.net.useZeroCopyIO", null);
/*  1169 */     if (str1 == null) {
/*  1170 */       str1 = "true";
/*       */     }
/*       */     
/*  1173 */     this.thinNetUseZeroCopyIO = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1176 */     str1 = null;
/*  1177 */     if (paramProperties != null)
/*       */     {
/*  1179 */       str1 = paramProperties.getProperty("oracle.net.SDP");
/*       */     }
/*  1181 */     if (str1 == null)
/*  1182 */       str1 = getSystemProperty("oracle.net.SDP", null);
/*  1183 */     if (str1 == null) {
/*  1184 */       str1 = "false";
/*       */     }
/*       */     
/*  1187 */     this.thinNetEnableSDP = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1190 */     str1 = null;
/*  1191 */     if (paramProperties != null)
/*       */     {
/*  1193 */       str1 = paramProperties.getProperty("oracle.jdbc.use1900AsYearForTime");
/*       */     }
/*  1195 */     if (str1 == null)
/*  1196 */       str1 = getSystemProperty("oracle.jdbc.use1900AsYearForTime", null);
/*  1197 */     if (str1 == null) {
/*  1198 */       str1 = "false";
/*       */     }
/*       */     
/*  1201 */     this.use1900AsYearForTime = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1204 */     str1 = null;
/*  1205 */     if (paramProperties != null)
/*       */     {
/*  1207 */       str1 = paramProperties.getProperty("oracle.jdbc.timestampTzInGmt");
/*       */     }
/*  1209 */     if (str1 == null)
/*  1210 */       str1 = getSystemProperty("oracle.jdbc.timestampTzInGmt", null);
/*  1211 */     if (str1 == null) {
/*  1212 */       str1 = "true";
/*       */     }
/*       */     
/*  1215 */     this.timestamptzInGmt = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1218 */     str1 = null;
/*  1219 */     if (paramProperties != null)
/*       */     {
/*  1221 */       str1 = paramProperties.getProperty("oracle.jdbc.timezoneAsRegion");
/*       */     }
/*  1223 */     if (str1 == null)
/*  1224 */       str1 = getSystemProperty("oracle.jdbc.timezoneAsRegion", null);
/*  1225 */     if (str1 == null) {
/*  1226 */       str1 = "true";
/*       */     }
/*       */     
/*  1229 */     this.timezoneAsRegion = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1232 */     str1 = null;
/*  1233 */     if (paramProperties != null)
/*       */     {
/*  1235 */       str1 = paramProperties.getProperty("oracle.net.ssl_server_dn_match");
/*       */     }
/*  1237 */     if (str1 == null)
/*  1238 */       str1 = getSystemProperty("oracle.net.ssl_server_dn_match", null);
/*  1239 */     if (str1 == null) {
/*  1240 */       str1 = null;
/*       */     }
/*       */     
/*  1243 */     this.thinSslServerDnMatch = str1;
/*       */     
/*       */ 
/*  1246 */     str1 = null;
/*  1247 */     if (paramProperties != null)
/*       */     {
/*  1249 */       str1 = paramProperties.getProperty("oracle.net.ssl_version");
/*       */     }
/*  1251 */     if (str1 == null)
/*  1252 */       str1 = getSystemProperty("oracle.net.ssl_version", null);
/*  1253 */     if (str1 == null) {
/*  1254 */       str1 = null;
/*       */     }
/*       */     
/*  1257 */     this.thinSslVersion = str1;
/*       */     
/*       */ 
/*  1260 */     str1 = null;
/*  1261 */     if (paramProperties != null)
/*       */     {
/*  1263 */       str1 = paramProperties.getProperty("oracle.net.ssl_cipher_suites");
/*       */     }
/*  1265 */     if (str1 == null)
/*  1266 */       str1 = getSystemProperty("oracle.net.ssl_cipher_suites", null);
/*  1267 */     if (str1 == null) {
/*  1268 */       str1 = null;
/*       */     }
/*       */     
/*  1271 */     this.thinSslCipherSuites = str1;
/*       */     
/*       */ 
/*  1274 */     str1 = null;
/*  1275 */     if (paramProperties != null)
/*       */     {
/*  1277 */       str1 = paramProperties.getProperty("javax.net.ssl.keyStore");
/*       */     }
/*  1279 */     if (str1 == null)
/*  1280 */       str1 = getSystemProperty("javax.net.ssl.keyStore", null);
/*  1281 */     if (str1 == null) {
/*  1282 */       str1 = null;
/*       */     }
/*       */     
/*  1285 */     this.thinJavaxNetSslKeystore = str1;
/*       */     
/*       */ 
/*  1288 */     str1 = null;
/*  1289 */     if (paramProperties != null)
/*       */     {
/*  1291 */       str1 = paramProperties.getProperty("javax.net.ssl.keyStoreType");
/*       */     }
/*  1293 */     if (str1 == null)
/*  1294 */       str1 = getSystemProperty("javax.net.ssl.keyStoreType", null);
/*  1295 */     if (str1 == null) {
/*  1296 */       str1 = null;
/*       */     }
/*       */     
/*  1299 */     this.thinJavaxNetSslKeystoretype = str1;
/*       */     
/*       */ 
/*  1302 */     str1 = null;
/*  1303 */     if (paramProperties != null)
/*       */     {
/*  1305 */       str1 = paramProperties.getProperty("javax.net.ssl.keyStorePassword");
/*       */     }
/*  1307 */     if (str1 == null)
/*  1308 */       str1 = getSystemProperty("javax.net.ssl.keyStorePassword", null);
/*  1309 */     if (str1 == null) {
/*  1310 */       str1 = null;
/*       */     }
/*       */     
/*  1313 */     this.thinJavaxNetSslKeystorepassword = str1;
/*       */     
/*       */ 
/*  1316 */     str1 = null;
/*  1317 */     if (paramProperties != null)
/*       */     {
/*  1319 */       str1 = paramProperties.getProperty("javax.net.ssl.trustStore");
/*       */     }
/*  1321 */     if (str1 == null)
/*  1322 */       str1 = getSystemProperty("javax.net.ssl.trustStore", null);
/*  1323 */     if (str1 == null) {
/*  1324 */       str1 = null;
/*       */     }
/*       */     
/*  1327 */     this.thinJavaxNetSslTruststore = str1;
/*       */     
/*       */ 
/*  1330 */     str1 = null;
/*  1331 */     if (paramProperties != null)
/*       */     {
/*  1333 */       str1 = paramProperties.getProperty("javax.net.ssl.trustStoreType");
/*       */     }
/*  1335 */     if (str1 == null)
/*  1336 */       str1 = getSystemProperty("javax.net.ssl.trustStoreType", null);
/*  1337 */     if (str1 == null) {
/*  1338 */       str1 = null;
/*       */     }
/*       */     
/*  1341 */     this.thinJavaxNetSslTruststoretype = str1;
/*       */     
/*       */ 
/*  1344 */     str1 = null;
/*  1345 */     if (paramProperties != null)
/*       */     {
/*  1347 */       str1 = paramProperties.getProperty("javax.net.ssl.trustStorePassword");
/*       */     }
/*  1349 */     if (str1 == null)
/*  1350 */       str1 = getSystemProperty("javax.net.ssl.trustStorePassword", null);
/*  1351 */     if (str1 == null) {
/*  1352 */       str1 = null;
/*       */     }
/*       */     
/*  1355 */     this.thinJavaxNetSslTruststorepassword = str1;
/*       */     
/*       */ 
/*  1358 */     str1 = null;
/*  1359 */     if (paramProperties != null)
/*       */     {
/*  1361 */       str1 = paramProperties.getProperty("ssl.keyManagerFactory.algorithm");
/*  1362 */       if (str1 == null)
/*  1363 */         str1 = paramProperties.getProperty("oracle.jdbc.ssl.keyManagerFactory.algorithm");
/*       */     }
/*  1365 */     if (str1 == null)
/*  1366 */       str1 = getSystemProperty("oracle.jdbc.ssl.keyManagerFactory.algorithm", null);
/*  1367 */     if (str1 == null) {
/*  1368 */       str1 = null;
/*       */     }
/*       */     
/*  1371 */     this.thinSslKeymanagerfactoryAlgorithm = str1;
/*       */     
/*       */ 
/*  1374 */     str1 = null;
/*  1375 */     if (paramProperties != null)
/*       */     {
/*  1377 */       str1 = paramProperties.getProperty("ssl.trustManagerFactory.algorithm");
/*  1378 */       if (str1 == null)
/*  1379 */         str1 = paramProperties.getProperty("oracle.jdbc.ssl.trustManagerFactory.algorithm");
/*       */     }
/*  1381 */     if (str1 == null)
/*  1382 */       str1 = getSystemProperty("oracle.jdbc.ssl.trustManagerFactory.algorithm", null);
/*  1383 */     if (str1 == null) {
/*  1384 */       str1 = null;
/*       */     }
/*       */     
/*  1387 */     this.thinSslTrustmanagerfactoryAlgorithm = str1;
/*       */     
/*       */ 
/*  1390 */     str1 = null;
/*  1391 */     if (paramProperties != null)
/*       */     {
/*  1393 */       str1 = paramProperties.getProperty("oracle.net.oldSyntax");
/*       */     }
/*  1395 */     if (str1 == null)
/*  1396 */       str1 = getSystemProperty("oracle.net.oldSyntax", null);
/*  1397 */     if (str1 == null) {
/*  1398 */       str1 = null;
/*       */     }
/*       */     
/*  1401 */     this.thinNetOldsyntax = str1;
/*       */     
/*       */ 
/*  1404 */     str1 = null;
/*  1405 */     if (paramProperties != null)
/*       */     {
/*  1407 */       str1 = paramProperties.getProperty("java.naming.factory.initial");
/*       */     }
/*  1409 */     if (str1 == null) {
/*  1410 */       str1 = null;
/*       */     }
/*       */     
/*  1413 */     this.thinNamingContextInitial = str1;
/*       */     
/*       */ 
/*  1416 */     str1 = null;
/*  1417 */     if (paramProperties != null)
/*       */     {
/*  1419 */       str1 = paramProperties.getProperty("java.naming.provider.url");
/*       */     }
/*  1421 */     if (str1 == null) {
/*  1422 */       str1 = null;
/*       */     }
/*       */     
/*  1425 */     this.thinNamingProviderUrl = str1;
/*       */     
/*       */ 
/*  1428 */     str1 = null;
/*  1429 */     if (paramProperties != null)
/*       */     {
/*  1431 */       str1 = paramProperties.getProperty("java.naming.security.authentication");
/*       */     }
/*  1433 */     if (str1 == null) {
/*  1434 */       str1 = null;
/*       */     }
/*       */     
/*  1437 */     this.thinNamingSecurityAuthentication = str1;
/*       */     
/*       */ 
/*  1440 */     str1 = null;
/*  1441 */     if (paramProperties != null)
/*       */     {
/*  1443 */       str1 = paramProperties.getProperty("java.naming.security.principal");
/*       */     }
/*  1445 */     if (str1 == null) {
/*  1446 */       str1 = null;
/*       */     }
/*       */     
/*  1449 */     this.thinNamingSecurityPrincipal = str1;
/*       */     
/*       */ 
/*  1452 */     str1 = null;
/*  1453 */     if (paramProperties != null)
/*       */     {
/*  1455 */       str1 = paramProperties.getProperty("java.naming.security.credentials");
/*       */     }
/*  1457 */     if (str1 == null) {
/*  1458 */       str1 = null;
/*       */     }
/*       */     
/*  1461 */     this.thinNamingSecurityCredentials = str1;
/*       */     
/*       */ 
/*  1464 */     str1 = null;
/*  1465 */     if (paramProperties != null)
/*       */     {
/*  1467 */       str1 = paramProperties.getProperty("oracle.net.wallet_location");
/*       */     }
/*  1469 */     if (str1 == null)
/*  1470 */       str1 = getSystemProperty("oracle.net.wallet_location", null);
/*  1471 */     if (str1 == null) {
/*  1472 */       str1 = null;
/*       */     }
/*       */     
/*  1475 */     this.walletLocation = str1;
/*       */     
/*       */ 
/*  1478 */     str1 = null;
/*  1479 */     if (paramProperties != null)
/*       */     {
/*  1481 */       str1 = paramProperties.getProperty("oracle.net.wallet_password");
/*       */     }
/*  1483 */     if (str1 == null)
/*  1484 */       str1 = getSystemProperty("oracle.net.wallet_password", null);
/*  1485 */     if (str1 == null) {
/*  1486 */       str1 = null;
/*       */     }
/*       */     
/*  1489 */     this.walletPassword = str1;
/*       */     
/*       */ 
/*  1492 */     str1 = null;
/*  1493 */     if (paramProperties != null)
/*       */     {
/*  1495 */       str1 = paramProperties.getProperty("oracle.jdbc.proxyClientName");
/*       */     }
/*  1497 */     if (str1 == null)
/*  1498 */       str1 = getSystemProperty("oracle.jdbc.proxyClientName", null);
/*  1499 */     if (str1 == null) {
/*  1500 */       str1 = null;
/*       */     }
/*       */     
/*  1503 */     this.proxyClientName = str1;
/*       */     
/*       */ 
/*  1506 */     str1 = null;
/*  1507 */     if (paramProperties != null)
/*       */     {
/*  1509 */       str1 = paramProperties.getProperty("oracle.jdbc.useNio");
/*       */     }
/*  1511 */     if (str1 == null)
/*  1512 */       str1 = getSystemProperty("oracle.jdbc.useNio", null);
/*  1513 */     if (str1 == null) {
/*  1514 */       str1 = "false";
/*       */     }
/*       */     
/*  1517 */     this.useNio = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1520 */     str1 = null;
/*  1521 */     if (paramProperties != null)
/*       */     {
/*  1523 */       str1 = paramProperties.getProperty("JDBCDriverCharSetId");
/*  1524 */       if (str1 == null)
/*  1525 */         str1 = paramProperties.getProperty("oracle.jdbc.JDBCDriverCharSetId");
/*       */     }
/*  1527 */     if (str1 == null)
/*  1528 */       str1 = getSystemProperty("oracle.jdbc.JDBCDriverCharSetId", null);
/*  1529 */     if (str1 == null) {
/*  1530 */       str1 = null;
/*       */     }
/*       */     
/*  1533 */     this.ociDriverCharset = str1;
/*       */     
/*       */ 
/*  1536 */     str1 = null;
/*  1537 */     if (paramProperties != null)
/*       */     {
/*  1539 */       str1 = paramProperties.getProperty("oracle.jdbc.editionName");
/*       */     }
/*  1541 */     if (str1 == null)
/*  1542 */       str1 = getSystemProperty("oracle.jdbc.editionName", null);
/*  1543 */     if (str1 == null) {
/*  1544 */       str1 = null;
/*       */     }
/*       */     
/*  1547 */     this.editionName = str1;
/*       */     
/*       */ 
/*  1550 */     str1 = null;
/*  1551 */     if (paramProperties != null)
/*       */     {
/*  1553 */       str1 = paramProperties.getProperty("oracle.jdbc.thinLogonCapability");
/*       */     }
/*  1555 */     if (str1 == null)
/*  1556 */       str1 = getSystemProperty("oracle.jdbc.thinLogonCapability", null);
/*  1557 */     if (str1 == null) {
/*  1558 */       str1 = "o5";
/*       */     }
/*       */     
/*  1561 */     this.logonCap = str1;
/*       */     
/*       */ 
/*  1564 */     str1 = null;
/*  1565 */     if (paramProperties != null)
/*       */     {
/*  1567 */       str1 = paramProperties.getProperty("internal_logon");
/*  1568 */       if (str1 == null)
/*  1569 */         str1 = paramProperties.getProperty("oracle.jdbc.internal_logon");
/*       */     }
/*  1571 */     if (str1 == null)
/*  1572 */       str1 = getSystemProperty("oracle.jdbc.internal_logon", null);
/*  1573 */     if (str1 == null) {
/*  1574 */       str1 = null;
/*       */     }
/*       */     
/*  1577 */     this.internalLogon = str1;
/*       */     
/*       */ 
/*  1580 */     str1 = null;
/*  1581 */     if (paramProperties != null)
/*       */     {
/*  1583 */       str1 = paramProperties.getProperty("oracle.jdbc.createDescriptorUseCurrentSchemaForSchemaName");
/*       */     }
/*  1585 */     if (str1 == null)
/*  1586 */       str1 = getSystemProperty("oracle.jdbc.createDescriptorUseCurrentSchemaForSchemaName", null);
/*  1587 */     if (str1 == null) {
/*  1588 */       str1 = "false";
/*       */     }
/*       */     
/*  1591 */     this.createDescriptorUseCurrentSchemaForSchemaName = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1594 */     str1 = null;
/*  1595 */     if (paramProperties != null)
/*       */     {
/*  1597 */       str1 = paramProperties.getProperty("OCISvcCtxHandle");
/*  1598 */       if (str1 == null)
/*  1599 */         str1 = paramProperties.getProperty("oracle.jdbc.OCISvcCtxHandle");
/*       */     }
/*  1601 */     if (str1 == null)
/*  1602 */       str1 = getSystemProperty("oracle.jdbc.OCISvcCtxHandle", null);
/*  1603 */     if (str1 == null) {
/*  1604 */       str1 = "0";
/*       */     }
/*       */     try {
/*  1607 */       this.ociSvcCtxHandle = Long.parseLong(str1);
/*       */ 
/*       */     }
/*       */     catch (NumberFormatException localNumberFormatException2)
/*       */     {
/*  1612 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 190, "Property is 'ociSvcCtxHandle'");
/*  1613 */       ((SQLException)localObject2).fillInStackTrace();
/*  1614 */       throw ((Throwable)localObject2);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  1620 */     str1 = null;
/*  1621 */     if (paramProperties != null)
/*       */     {
/*  1623 */       str1 = paramProperties.getProperty("OCIEnvHandle");
/*  1624 */       if (str1 == null)
/*  1625 */         str1 = paramProperties.getProperty("oracle.jdbc.OCIEnvHandle");
/*       */     }
/*  1627 */     if (str1 == null)
/*  1628 */       str1 = getSystemProperty("oracle.jdbc.OCIEnvHandle", null);
/*  1629 */     if (str1 == null) {
/*  1630 */       str1 = "0";
/*       */     }
/*       */     try {
/*  1633 */       this.ociEnvHandle = Long.parseLong(str1);
/*       */ 
/*       */     }
/*       */     catch (NumberFormatException localNumberFormatException3)
/*       */     {
/*  1638 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 190, "Property is 'ociEnvHandle'");
/*  1639 */       ((SQLException)localObject2).fillInStackTrace();
/*  1640 */       throw ((Throwable)localObject2);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  1646 */     str1 = null;
/*  1647 */     if (paramProperties != null)
/*       */     {
/*  1649 */       str1 = paramProperties.getProperty("OCIErrHandle");
/*  1650 */       if (str1 == null)
/*  1651 */         str1 = paramProperties.getProperty("oracle.jdbc.OCIErrHandle");
/*       */     }
/*  1653 */     if (str1 == null)
/*  1654 */       str1 = getSystemProperty("oracle.jdbc.OCIErrHandle", null);
/*  1655 */     if (str1 == null) {
/*  1656 */       str1 = "0";
/*       */     }
/*       */     try {
/*  1659 */       this.ociErrHandle = Long.parseLong(str1);
/*       */ 
/*       */     }
/*       */     catch (NumberFormatException localNumberFormatException4)
/*       */     {
/*  1664 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 190, "Property is 'ociErrHandle'");
/*  1665 */       ((SQLException)localObject2).fillInStackTrace();
/*  1666 */       throw ((Throwable)localObject2);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  1672 */     str1 = null;
/*  1673 */     if (paramProperties != null)
/*       */     {
/*  1675 */       str1 = paramProperties.getProperty("prelim_auth");
/*  1676 */       if (str1 == null)
/*  1677 */         str1 = paramProperties.getProperty("oracle.jdbc.prelim_auth");
/*       */     }
/*  1679 */     if (str1 == null)
/*  1680 */       str1 = getSystemProperty("oracle.jdbc.prelim_auth", null);
/*  1681 */     if (str1 == null) {
/*  1682 */       str1 = "false";
/*       */     }
/*       */     
/*  1685 */     this.prelimAuth = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1688 */     str1 = null;
/*  1689 */     if (paramProperties != null)
/*       */     {
/*  1691 */       str1 = paramProperties.getProperty("oracle.jdbc.ociNlsLangBackwardCompatible");
/*       */     }
/*  1693 */     if (str1 == null)
/*  1694 */       str1 = getSystemProperty("oracle.jdbc.ociNlsLangBackwardCompatible", null);
/*  1695 */     if (str1 == null) {
/*  1696 */       str1 = "false";
/*       */     }
/*       */     
/*  1699 */     this.nlsLangBackdoor = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1702 */     str1 = null;
/*  1703 */     if (paramProperties != null)
/*       */     {
/*  1705 */       str1 = paramProperties.getProperty("OCINewPassword");
/*  1706 */       if (str1 == null)
/*  1707 */         str1 = paramProperties.getProperty("oracle.jdbc.OCINewPassword");
/*       */     }
/*  1709 */     if (str1 == null)
/*  1710 */       str1 = getSystemProperty("oracle.jdbc.OCINewPassword", null);
/*  1711 */     if (str1 == null) {
/*  1712 */       str1 = null;
/*       */     }
/*       */     
/*  1715 */     this.setNewPassword = str1;
/*       */     
/*       */ 
/*  1718 */     str1 = null;
/*  1719 */     if (paramProperties != null)
/*       */     {
/*  1721 */       str1 = paramProperties.getProperty("oracle.jdbc.spawnNewThreadToCancel");
/*       */     }
/*  1723 */     if (str1 == null)
/*  1724 */       str1 = getSystemProperty("oracle.jdbc.spawnNewThreadToCancel", null);
/*  1725 */     if (str1 == null) {
/*  1726 */       str1 = "false";
/*       */     }
/*       */     
/*  1729 */     this.spawnNewThreadToCancel = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1732 */     str1 = null;
/*  1733 */     if (paramProperties != null)
/*       */     {
/*  1735 */       str1 = paramProperties.getProperty("defaultExecuteBatch");
/*  1736 */       if (str1 == null)
/*  1737 */         str1 = paramProperties.getProperty("oracle.jdbc.defaultExecuteBatch");
/*       */     }
/*  1739 */     if (str1 == null)
/*  1740 */       str1 = getSystemProperty("oracle.jdbc.defaultExecuteBatch", null);
/*  1741 */     if (str1 == null) {
/*  1742 */       str1 = "1";
/*       */     }
/*       */     try {
/*  1745 */       this.defaultExecuteBatch = Integer.parseInt(str1);
/*       */ 
/*       */     }
/*       */     catch (NumberFormatException localNumberFormatException5)
/*       */     {
/*  1750 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 190, "Property is 'defaultExecuteBatch'");
/*  1751 */       ((SQLException)localObject2).fillInStackTrace();
/*  1752 */       throw ((Throwable)localObject2);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  1758 */     str1 = null;
/*  1759 */     if (paramProperties != null)
/*       */     {
/*  1761 */       str1 = paramProperties.getProperty("defaultRowPrefetch");
/*  1762 */       if (str1 == null)
/*  1763 */         str1 = paramProperties.getProperty("oracle.jdbc.defaultRowPrefetch");
/*       */     }
/*  1765 */     if (str1 == null)
/*  1766 */       str1 = getSystemProperty("oracle.jdbc.defaultRowPrefetch", null);
/*  1767 */     if (str1 == null) {
/*  1768 */       str1 = "10";
/*       */     }
/*       */     try {
/*  1771 */       this.defaultRowPrefetch = Integer.parseInt(str1);
/*       */ 
/*       */     }
/*       */     catch (NumberFormatException localNumberFormatException6)
/*       */     {
/*  1776 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 190, "Property is 'defaultRowPrefetch'");
/*  1777 */       ((SQLException)localObject2).fillInStackTrace();
/*  1778 */       throw ((Throwable)localObject2);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  1784 */     str1 = null;
/*  1785 */     if (paramProperties != null)
/*       */     {
/*  1787 */       str1 = paramProperties.getProperty("oracle.jdbc.defaultLobPrefetchSize");
/*       */     }
/*  1789 */     if (str1 == null)
/*  1790 */       str1 = getSystemProperty("oracle.jdbc.defaultLobPrefetchSize", null);
/*  1791 */     if (str1 == null) {
/*  1792 */       str1 = "4000";
/*       */     }
/*       */     try {
/*  1795 */       this.defaultLobPrefetchSize = Integer.parseInt(str1);
/*       */ 
/*       */     }
/*       */     catch (NumberFormatException localNumberFormatException7)
/*       */     {
/*  1800 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 190, "Property is 'defaultLobPrefetchSize'");
/*  1801 */       ((SQLException)localObject2).fillInStackTrace();
/*  1802 */       throw ((Throwable)localObject2);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  1808 */     str1 = null;
/*  1809 */     if (paramProperties != null)
/*       */     {
/*  1811 */       str1 = paramProperties.getProperty("oracle.jdbc.enableDataInLocator");
/*       */     }
/*  1813 */     if (str1 == null)
/*  1814 */       str1 = getSystemProperty("oracle.jdbc.enableDataInLocator", null);
/*  1815 */     if (str1 == null) {
/*  1816 */       str1 = "true";
/*       */     }
/*       */     
/*  1819 */     this.enableDataInLocator = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1822 */     str1 = null;
/*  1823 */     if (paramProperties != null)
/*       */     {
/*  1825 */       str1 = paramProperties.getProperty("oracle.jdbc.enableReadDataInLocator");
/*       */     }
/*  1827 */     if (str1 == null)
/*  1828 */       str1 = getSystemProperty("oracle.jdbc.enableReadDataInLocator", null);
/*  1829 */     if (str1 == null) {
/*  1830 */       str1 = "true";
/*       */     }
/*       */     
/*  1833 */     this.enableReadDataInLocator = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1836 */     str1 = null;
/*  1837 */     if (paramProperties != null)
/*       */     {
/*  1839 */       str1 = paramProperties.getProperty("oracle.jdbc.overrideEnableReadDataInLocator");
/*       */     }
/*  1841 */     if (str1 == null)
/*  1842 */       str1 = getSystemProperty("oracle.jdbc.overrideEnableReadDataInLocator", null);
/*  1843 */     if (str1 == null) {
/*  1844 */       str1 = "false";
/*       */     }
/*       */     
/*  1847 */     this.overrideEnableReadDataInLocator = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1850 */     str1 = null;
/*  1851 */     if (paramProperties != null)
/*       */     {
/*  1853 */       str1 = paramProperties.getProperty("remarksReporting");
/*  1854 */       if (str1 == null)
/*  1855 */         str1 = paramProperties.getProperty("oracle.jdbc.remarksReporting");
/*       */     }
/*  1857 */     if (str1 == null)
/*  1858 */       str1 = getSystemProperty("oracle.jdbc.remarksReporting", null);
/*  1859 */     if (str1 == null) {
/*  1860 */       str1 = "false";
/*       */     }
/*       */     
/*  1863 */     this.reportRemarks = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1866 */     str1 = null;
/*  1867 */     if (paramProperties != null)
/*       */     {
/*  1869 */       str1 = paramProperties.getProperty("includeSynonyms");
/*  1870 */       if (str1 == null)
/*  1871 */         str1 = paramProperties.getProperty("oracle.jdbc.includeSynonyms");
/*       */     }
/*  1873 */     if (str1 == null)
/*  1874 */       str1 = getSystemProperty("oracle.jdbc.includeSynonyms", null);
/*  1875 */     if (str1 == null) {
/*  1876 */       str1 = "false";
/*       */     }
/*       */     
/*  1879 */     this.includeSynonyms = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1882 */     str1 = null;
/*  1883 */     if (paramProperties != null)
/*       */     {
/*  1885 */       str1 = paramProperties.getProperty("restrictGetTables");
/*  1886 */       if (str1 == null)
/*  1887 */         str1 = paramProperties.getProperty("oracle.jdbc.restrictGetTables");
/*       */     }
/*  1889 */     if (str1 == null)
/*  1890 */       str1 = getSystemProperty("oracle.jdbc.restrictGetTables", null);
/*  1891 */     if (str1 == null) {
/*  1892 */       str1 = "false";
/*       */     }
/*       */     
/*  1895 */     this.restrictGettables = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1898 */     str1 = null;
/*  1899 */     if (paramProperties != null)
/*       */     {
/*  1901 */       str1 = paramProperties.getProperty("AccumulateBatchResult");
/*  1902 */       if (str1 == null)
/*  1903 */         str1 = paramProperties.getProperty("oracle.jdbc.AccumulateBatchResult");
/*       */     }
/*  1905 */     if (str1 == null)
/*  1906 */       str1 = getSystemProperty("oracle.jdbc.AccumulateBatchResult", null);
/*  1907 */     if (str1 == null) {
/*  1908 */       str1 = "true";
/*       */     }
/*       */     
/*  1911 */     this.accumulateBatchResult = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1914 */     str1 = null;
/*  1915 */     if (paramProperties != null)
/*       */     {
/*  1917 */       str1 = paramProperties.getProperty("useFetchSizeWithLongColumn");
/*  1918 */       if (str1 == null)
/*  1919 */         str1 = paramProperties.getProperty("oracle.jdbc.useFetchSizeWithLongColumn");
/*       */     }
/*  1921 */     if (str1 == null)
/*  1922 */       str1 = getSystemProperty("oracle.jdbc.useFetchSizeWithLongColumn", null);
/*  1923 */     if (str1 == null) {
/*  1924 */       str1 = "false";
/*       */     }
/*       */     
/*  1927 */     this.useFetchSizeWithLongColumn = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1930 */     str1 = null;
/*  1931 */     if (paramProperties != null)
/*       */     {
/*  1933 */       str1 = paramProperties.getProperty("processEscapes");
/*  1934 */       if (str1 == null)
/*  1935 */         str1 = paramProperties.getProperty("oracle.jdbc.processEscapes");
/*       */     }
/*  1937 */     if (str1 == null)
/*  1938 */       str1 = getSystemProperty("oracle.jdbc.processEscapes", null);
/*  1939 */     if (str1 == null) {
/*  1940 */       str1 = "true";
/*       */     }
/*       */     
/*  1943 */     this.processEscapes = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1946 */     str1 = null;
/*  1947 */     if (paramProperties != null)
/*       */     {
/*  1949 */       str1 = paramProperties.getProperty("fixedString");
/*  1950 */       if (str1 == null)
/*  1951 */         str1 = paramProperties.getProperty("oracle.jdbc.fixedString");
/*       */     }
/*  1953 */     if (str1 == null)
/*  1954 */       str1 = getSystemProperty("oracle.jdbc.fixedString", null);
/*  1955 */     if (str1 == null) {
/*  1956 */       str1 = "false";
/*       */     }
/*       */     
/*  1959 */     this.fixedString = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1962 */     str1 = null;
/*  1963 */     if (paramProperties != null)
/*       */     {
/*  1965 */       str1 = paramProperties.getProperty("defaultNChar");
/*  1966 */       if (str1 == null)
/*  1967 */         str1 = paramProperties.getProperty("oracle.jdbc.defaultNChar");
/*       */     }
/*  1969 */     if (str1 == null)
/*  1970 */       str1 = getSystemProperty("oracle.jdbc.defaultNChar", null);
/*  1971 */     if (str1 == null) {
/*  1972 */       str1 = "false";
/*       */     }
/*       */     
/*  1975 */     this.defaultnchar = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1978 */     str1 = null;
/*  1979 */     if (paramProperties != null)
/*       */     {
/*  1981 */       str1 = paramProperties.getProperty("oracle.jdbc.internal.permitBindDateDefineTimestampMismatch");
/*       */     }
/*  1983 */     if (str1 == null)
/*  1984 */       str1 = getSystemProperty("oracle.jdbc.internal.permitBindDateDefineTimestampMismatch", null);
/*  1985 */     if (str1 == null) {
/*  1986 */       str1 = "false";
/*       */     }
/*       */     
/*  1989 */     this.permitTimestampDateMismatch = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  1992 */     str1 = null;
/*  1993 */     if (paramProperties != null)
/*       */     {
/*  1995 */       str1 = paramProperties.getProperty("RessourceManagerId");
/*  1996 */       if (str1 == null)
/*  1997 */         str1 = paramProperties.getProperty("oracle.jdbc.RessourceManagerId");
/*       */     }
/*  1999 */     if (str1 == null)
/*  2000 */       str1 = getSystemProperty("oracle.jdbc.RessourceManagerId", null);
/*  2001 */     if (str1 == null) {
/*  2002 */       str1 = "0000";
/*       */     }
/*       */     
/*  2005 */     this.resourceManagerId = str1;
/*       */     
/*       */ 
/*  2008 */     str1 = null;
/*  2009 */     if (paramProperties != null)
/*       */     {
/*  2011 */       str1 = paramProperties.getProperty("disableDefineColumnType");
/*  2012 */       if (str1 == null)
/*  2013 */         str1 = paramProperties.getProperty("oracle.jdbc.disableDefineColumnType");
/*       */     }
/*  2015 */     if (str1 == null)
/*  2016 */       str1 = getSystemProperty("oracle.jdbc.disableDefineColumnType", null);
/*  2017 */     if (str1 == null) {
/*  2018 */       str1 = "false";
/*       */     }
/*       */     
/*  2021 */     this.disableDefinecolumntype = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  2024 */     str1 = null;
/*  2025 */     if (paramProperties != null)
/*       */     {
/*  2027 */       str1 = paramProperties.getProperty("oracle.jdbc.convertNcharLiterals");
/*       */     }
/*  2029 */     if (str1 == null)
/*  2030 */       str1 = getSystemProperty("oracle.jdbc.convertNcharLiterals", null);
/*  2031 */     if (str1 == null) {
/*  2032 */       str1 = "false";
/*       */     }
/*       */     
/*  2035 */     this.convertNcharLiterals = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  2038 */     str1 = null;
/*  2039 */     if (paramProperties != null)
/*       */     {
/*  2041 */       str1 = paramProperties.getProperty("oracle.jdbc.J2EE13Compliant");
/*       */     }
/*  2043 */     if (str1 == null)
/*  2044 */       str1 = getSystemProperty("oracle.jdbc.J2EE13Compliant", null);
/*  2045 */     if (str1 == null) {
/*  2046 */       str1 = "false";
/*       */     }
/*       */     
/*  2049 */     this.j2ee13Compliant = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  2052 */     str1 = null;
/*  2053 */     if (paramProperties != null)
/*       */     {
/*  2055 */       str1 = paramProperties.getProperty("oracle.jdbc.mapDateToTimestamp");
/*       */     }
/*  2057 */     if (str1 == null)
/*  2058 */       str1 = getSystemProperty("oracle.jdbc.mapDateToTimestamp", null);
/*  2059 */     if (str1 == null) {
/*  2060 */       str1 = "true";
/*       */     }
/*       */     
/*  2063 */     this.mapDateToTimestamp = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  2066 */     str1 = null;
/*  2067 */     if (paramProperties != null)
/*       */     {
/*  2069 */       str1 = paramProperties.getProperty("oracle.jdbc.useThreadLocalBufferCache");
/*       */     }
/*  2071 */     if (str1 == null)
/*  2072 */       str1 = getSystemProperty("oracle.jdbc.useThreadLocalBufferCache", null);
/*  2073 */     if (str1 == null) {
/*  2074 */       str1 = "false";
/*       */     }
/*       */     
/*  2077 */     this.useThreadLocalBufferCache = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  2080 */     str1 = null;
/*  2081 */     if (paramProperties != null)
/*       */     {
/*  2083 */       str1 = paramProperties.getProperty("oracle.jdbc.driverNameAttribute");
/*       */     }
/*  2085 */     if (str1 == null)
/*  2086 */       str1 = getSystemProperty("oracle.jdbc.driverNameAttribute", null);
/*  2087 */     if (str1 == null) {
/*  2088 */       str1 = null;
/*       */     }
/*       */     
/*  2091 */     this.driverNameAttribute = str1;
/*       */     
/*       */ 
/*  2094 */     str1 = null;
/*  2095 */     if (paramProperties != null)
/*       */     {
/*  2097 */       str1 = paramProperties.getProperty("oracle.jdbc.maxCachedBufferSize");
/*       */     }
/*  2099 */     if (str1 == null)
/*  2100 */       str1 = getSystemProperty("oracle.jdbc.maxCachedBufferSize", null);
/*  2101 */     if (str1 == null) {
/*  2102 */       str1 = "30";
/*       */     }
/*       */     try {
/*  2105 */       this.maxCachedBufferSize = Integer.parseInt(str1);
/*       */ 
/*       */     }
/*       */     catch (NumberFormatException localNumberFormatException8)
/*       */     {
/*  2110 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 190, "Property is 'maxCachedBufferSize'");
/*  2111 */       ((SQLException)localObject2).fillInStackTrace();
/*  2112 */       throw ((Throwable)localObject2);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  2118 */     str1 = null;
/*  2119 */     if (paramProperties != null)
/*       */     {
/*  2121 */       str1 = paramProperties.getProperty("oracle.jdbc.implicitStatementCacheSize");
/*       */     }
/*  2123 */     if (str1 == null)
/*  2124 */       str1 = getSystemProperty("oracle.jdbc.implicitStatementCacheSize", null);
/*  2125 */     if (str1 == null) {
/*  2126 */       str1 = "0";
/*       */     }
/*       */     try {
/*  2129 */       this.implicitStatementCacheSize = Integer.parseInt(str1);
/*       */ 
/*       */     }
/*       */     catch (NumberFormatException localNumberFormatException9)
/*       */     {
/*  2134 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 190, "Property is 'implicitStatementCacheSize'");
/*  2135 */       ((SQLException)localObject2).fillInStackTrace();
/*  2136 */       throw ((Throwable)localObject2);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  2142 */     str1 = null;
/*  2143 */     if (paramProperties != null)
/*       */     {
/*  2145 */       str1 = paramProperties.getProperty("oracle.jdbc.LobStreamPosStandardCompliant");
/*       */     }
/*  2147 */     if (str1 == null)
/*  2148 */       str1 = getSystemProperty("oracle.jdbc.LobStreamPosStandardCompliant", null);
/*  2149 */     if (str1 == null) {
/*  2150 */       str1 = "false";
/*       */     }
/*       */     
/*  2153 */     this.lobStreamPosStandardCompliant = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  2156 */     str1 = null;
/*  2157 */     if (paramProperties != null)
/*       */     {
/*  2159 */       str1 = paramProperties.getProperty("oracle.jdbc.strictASCIIConversion");
/*       */     }
/*  2161 */     if (str1 == null)
/*  2162 */       str1 = getSystemProperty("oracle.jdbc.strictASCIIConversion", null);
/*  2163 */     if (str1 == null) {
/*  2164 */       str1 = "false";
/*       */     }
/*       */     
/*  2167 */     this.isStrictAsciiConversion = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  2170 */     str1 = null;
/*  2171 */     if (paramProperties != null)
/*       */     {
/*  2173 */       str1 = paramProperties.getProperty("oracle.jdbc.quickASCIIConversion");
/*       */     }
/*  2175 */     if (str1 == null)
/*  2176 */       str1 = getSystemProperty("oracle.jdbc.quickASCIIConversion", null);
/*  2177 */     if (str1 == null) {
/*  2178 */       str1 = "false";
/*       */     }
/*       */     
/*  2181 */     this.isQuickAsciiConversion = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  2184 */     str1 = null;
/*  2185 */     if (paramProperties != null)
/*       */     {
/*  2187 */       str1 = paramProperties.getProperty("oracle.jdbc.thinForceDNSLoadBalancing");
/*       */     }
/*  2189 */     if (str1 == null)
/*  2190 */       str1 = getSystemProperty("oracle.jdbc.thinForceDNSLoadBalancing", null);
/*  2191 */     if (str1 == null) {
/*  2192 */       str1 = "false";
/*       */     }
/*       */     
/*  2195 */     this.thinForceDnsLoadBalancing = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  2198 */     str1 = null;
/*  2199 */     if (paramProperties != null)
/*       */     {
/*  2201 */       str1 = paramProperties.getProperty("oracle.jdbc.enableJavaNetFastPath");
/*       */     }
/*  2203 */     if (str1 == null)
/*  2204 */       str1 = getSystemProperty("oracle.jdbc.enableJavaNetFastPath", null);
/*  2205 */     if (str1 == null) {
/*  2206 */       str1 = "false";
/*       */     }
/*       */     
/*  2209 */     this.enableJavaNetFastPath = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  2212 */     str1 = null;
/*  2213 */     if (paramProperties != null)
/*       */     {
/*  2215 */       str1 = paramProperties.getProperty("oracle.jdbc.enableTempLobRefCnt");
/*       */     }
/*  2217 */     if (str1 == null)
/*  2218 */       str1 = getSystemProperty("oracle.jdbc.enableTempLobRefCnt", null);
/*  2219 */     if (str1 == null) {
/*  2220 */       str1 = "true";
/*       */     }
/*       */     
/*  2223 */     this.enableTempLobRefCnt = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  2226 */     str1 = null;
/*  2227 */     if (paramProperties != null)
/*       */     {
/*  2229 */       str1 = paramProperties.getProperty("oracle.jdbc.plsqlVarcharParameter4KOnly");
/*       */     }
/*  2231 */     if (str1 == null)
/*  2232 */       str1 = getSystemProperty("oracle.jdbc.plsqlVarcharParameter4KOnly", null);
/*  2233 */     if (str1 == null) {
/*  2234 */       str1 = "false";
/*       */     }
/*       */     
/*  2237 */     this.plsqlVarcharParameter4KOnly = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*  2240 */     str1 = null;
/*  2241 */     if (paramProperties != null)
/*       */     {
/*  2243 */       str1 = paramProperties.getProperty("oracle.net.keepAlive");
/*       */     }
/*  2245 */     if (str1 == null)
/*  2246 */       str1 = getSystemProperty("oracle.net.keepAlive", null);
/*  2247 */     if (str1 == null) {
/*  2248 */       str1 = "false";
/*       */     }
/*       */     
/*  2251 */     this.keepAlive = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*       */ 
/*  2255 */     str1 = null;
/*  2256 */     if (paramProperties != null)
/*  2257 */       str1 = paramProperties.getProperty("oracle.jdbc.commitOption");
/*  2258 */     if (str1 == null)
/*  2259 */       str1 = getSystemProperty("oracle.jdbc.commitOption", null);
/*  2260 */     Object localObject1; Object localObject3; if (str1 != null)
/*       */     {
/*  2262 */       this.commitOption = 0;
/*  2263 */       localObject1 = str1.split(",");
/*  2264 */       if ((localObject1 != null) && (localObject1.length > 0))
/*       */       {
/*  2266 */         for (localObject3 : localObject1) {
/*  2267 */           if (((String)localObject3).trim() != "") {
/*  2268 */             this.commitOption |= OracleConnection.CommitOption.valueOf(((String)localObject3).trim()).getCode();
/*       */           }
/*       */         }
/*       */       }
/*       */     }
/*  2273 */     str1 = null;
/*  2274 */     if (paramProperties != null)
/*       */     {
/*  2276 */       str1 = paramProperties.getProperty("oracle.jdbc.calculateChecksum");
/*       */     }
/*  2278 */     if (str1 == null)
/*  2279 */       str1 = getSystemProperty("oracle.jdbc.calculateChecksum", null);
/*  2280 */     if (str1 == null) {
/*  2281 */       str1 = "false";
/*       */     }
/*       */     
/*  2284 */     this.calculateChecksum = ((str1 != null) && (str1.equalsIgnoreCase("true")));
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  2289 */     this.includeSynonyms = parseConnectionProperty_boolean(paramProperties, "synonyms", (byte)3, this.includeSynonyms);
/*       */     
/*       */ 
/*  2292 */     this.reportRemarks = parseConnectionProperty_boolean(paramProperties, "remarks", (byte)3, this.reportRemarks);
/*       */     
/*       */ 
/*  2295 */     this.defaultRowPrefetch = parseConnectionProperty_int(paramProperties, "prefetch", (byte)3, this.defaultRowPrefetch);
/*       */     
/*       */ 
/*  2298 */     this.defaultRowPrefetch = parseConnectionProperty_int(paramProperties, "rowPrefetch", (byte)3, this.defaultRowPrefetch);
/*       */     
/*       */ 
/*  2301 */     this.defaultExecuteBatch = parseConnectionProperty_int(paramProperties, "batch", (byte)3, this.defaultExecuteBatch);
/*       */     
/*       */ 
/*  2304 */     this.defaultExecuteBatch = parseConnectionProperty_int(paramProperties, "executeBatch", (byte)3, this.defaultExecuteBatch);
/*       */     
/*       */ 
/*  2307 */     this.proxyClientName = parseConnectionProperty_String(paramProperties, "PROXY_CLIENT_NAME", (byte)1, this.proxyClientName);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2313 */     if (this.defaultRowPrefetch <= 0) {
/*  2314 */       this.defaultRowPrefetch = Integer.parseInt("10");
/*       */     }
/*  2316 */     if (this.defaultExecuteBatch <= 0) {
/*  2317 */       this.defaultExecuteBatch = Integer.parseInt("1");
/*       */     }
/*  2319 */     if (this.defaultLobPrefetchSize < -1)
/*       */     {
/*  2321 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 267);
/*  2322 */       ((SQLException)localObject1).fillInStackTrace();
/*  2323 */       throw ((Throwable)localObject1);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2330 */     if (this.streamChunkSize > 0) {
/*  2331 */       this.streamChunkSize = Math.max(4096, this.streamChunkSize);
/*       */     } else {
/*  2333 */       this.streamChunkSize = Integer.parseInt("16384");
/*       */     }
/*       */     
/*       */ 
/*  2337 */     if (this.thinVsessionOsuser == null)
/*       */     {
/*  2339 */       this.thinVsessionOsuser = getSystemProperty("user.name", null);
/*  2340 */       if (this.thinVsessionOsuser == null) {
/*  2341 */         this.thinVsessionOsuser = "jdbcuser";
/*       */       }
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  2348 */     if (this.thinNetConnectTimeout == CONNECTION_PROPERTY_THIN_NET_CONNECT_TIMEOUT_DEFAULT)
/*       */     {
/*  2350 */       int i = DriverManager.getLoginTimeout();
/*  2351 */       if (i != 0) {
/*  2352 */         this.thinNetConnectTimeout = ("" + i * 1000);
/*       */       }
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2360 */     this.url = paramString;
/*  2361 */     Hashtable localHashtable = parseUrl(this.url, this.walletLocation, this.walletPassword);
/*       */     
/*  2363 */     if (this.userName == CONNECTION_PROPERTY_USER_NAME_DEFAULT)
/*  2364 */       this.userName = ((String)localHashtable.get("user"));
/*  2365 */     Object localObject2 = new String[1];
/*  2366 */     String[] arrayOfString = new String[1];
/*  2367 */     this.userName = parseLoginOption(this.userName, paramProperties, (String[])localObject2, arrayOfString);
/*  2368 */     if (localObject2[0] != null)
/*  2369 */       this.internalLogon = localObject2[0];
/*  2370 */     if (arrayOfString[0] != null) {
/*  2371 */       this.proxyClientName = arrayOfString[0];
/*       */     }
/*  2373 */     String str2 = paramProperties.getProperty("password", CONNECTION_PROPERTY_PASSWORD_DEFAULT);
/*       */     
/*  2375 */     if (str2 == CONNECTION_PROPERTY_PASSWORD_DEFAULT)
/*  2376 */       str2 = (String)localHashtable.get("password");
/*  2377 */     initializePassword(str2);
/*       */     
/*  2379 */     if (this.database == CONNECTION_PROPERTY_DATABASE_DEFAULT) {
/*  2380 */       this.database = paramProperties.getProperty("server", CONNECTION_PROPERTY_DATABASE_DEFAULT);
/*       */     }
/*  2382 */     if (this.database == CONNECTION_PROPERTY_DATABASE_DEFAULT) {
/*  2383 */       this.database = ((String)localHashtable.get("database"));
/*       */     }
/*  2385 */     this.protocol = ((String)localHashtable.get("protocol"));
/*       */     
/*       */ 
/*  2388 */     if (this.protocol == null)
/*       */     {
/*       */ 
/*  2391 */       localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 40, "Protocol is not specified in URL");
/*  2392 */       ((SQLException)localObject3).fillInStackTrace();
/*  2393 */       throw ((Throwable)localObject3);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  2398 */     if ((this.protocol.equals("oci8")) || (this.protocol.equals("oci"))) {
/*  2399 */       this.database = translateConnStr(this.database);
/*       */     }
/*       */     
/*  2402 */     if (paramProperties.getProperty("is_connection_pooling") == "true")
/*       */     {
/*       */ 
/*  2405 */       if (this.database == null) {
/*  2406 */         this.database = "";
/*       */       }
/*       */     }
/*  2409 */     if ((this.userName != null) && (!this.userName.startsWith("\"")))
/*       */     {
/*       */ 
/*  2412 */       localObject3 = this.userName.toCharArray();
/*  2413 */       for (int m = 0; m < localObject3.length; m++)
/*  2414 */         localObject3[m] = Character.toUpperCase(localObject3[m]);
/*  2415 */       this.userName = String.copyValueOf((char[])localObject3);
/*       */     }
/*       */     
/*       */ 
/*  2419 */     this.xaWantsError = false;
/*  2420 */     this.usingXA = false;
/*       */     
/*  2422 */     readOCIConnectionPoolProperties(paramProperties);
/*  2423 */     validateConnectionProperties();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   private void readOCIConnectionPoolProperties(Properties paramProperties)
/*       */     throws SQLException
/*       */   {
/*  2432 */     this.ociConnectionPoolMinLimit = parseConnectionProperty_int(paramProperties, "connpool_min_limit", (byte)1, 0);
/*       */     
/*       */ 
/*  2435 */     this.ociConnectionPoolMaxLimit = parseConnectionProperty_int(paramProperties, "connpool_max_limit", (byte)1, 0);
/*       */     
/*       */ 
/*  2438 */     this.ociConnectionPoolIncrement = parseConnectionProperty_int(paramProperties, "connpool_increment", (byte)1, 0);
/*       */     
/*       */ 
/*  2441 */     this.ociConnectionPoolTimeout = parseConnectionProperty_int(paramProperties, "connpool_timeout", (byte)1, 0);
/*       */     
/*       */ 
/*  2444 */     this.ociConnectionPoolNoWait = parseConnectionProperty_boolean(paramProperties, "connpool_nowait", (byte)1, false);
/*       */     
/*       */ 
/*  2447 */     this.ociConnectionPoolTransactionDistributed = parseConnectionProperty_boolean(paramProperties, "transactions_distributed", (byte)1, false);
/*       */     
/*       */ 
/*  2450 */     this.ociConnectionPoolLogonMode = parseConnectionProperty_String(paramProperties, "connection_pool", (byte)1, null);
/*       */     
/*       */ 
/*  2453 */     this.ociConnectionPoolIsPooling = parseConnectionProperty_boolean(paramProperties, "is_connection_pooling", (byte)1, false);
/*       */     
/*       */ 
/*  2456 */     this.ociConnectionPoolObject = parseConnectionProperty_Object(paramProperties, "connpool_object", null);
/*       */     
/*  2458 */     this.ociConnectionPoolConnID = parseConnectionProperty_Object(paramProperties, "connection_id", null);
/*       */     
/*  2460 */     this.ociConnectionPoolProxyType = parseConnectionProperty_String(paramProperties, "proxytype", (byte)1, null);
/*       */     
/*       */ 
/*  2463 */     this.ociConnectionPoolProxyNumRoles = ((Integer)parseConnectionProperty_Object(paramProperties, "proxy_num_roles", Integer.valueOf(0)));
/*       */     
/*  2465 */     this.ociConnectionPoolProxyRoles = parseConnectionProperty_Object(paramProperties, "proxy_roles", null);
/*       */     
/*  2467 */     this.ociConnectionPoolProxyUserName = parseConnectionProperty_String(paramProperties, "proxy_user_name", (byte)1, null);
/*       */     
/*       */ 
/*  2470 */     this.ociConnectionPoolProxyPassword = parseConnectionProperty_String(paramProperties, "proxy_password", (byte)1, null);
/*       */     
/*       */ 
/*  2473 */     this.ociConnectionPoolProxyDistinguishedName = parseConnectionProperty_String(paramProperties, "proxy_distinguished_name", (byte)1, null);
/*       */     
/*       */ 
/*  2476 */     this.ociConnectionPoolProxyCertificate = parseConnectionProperty_Object(paramProperties, "proxy_certificate", null);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2483 */   private static final Pattern driverNameAttributePattern = Pattern.compile("[\\x20-\\x7e]{0,8}");
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void validateConnectionProperties()
/*       */     throws SQLException
/*       */   {
/*  2493 */     if ((this.driverNameAttribute != null) && (!driverNameAttributePattern.matcher(this.driverNameAttribute).matches()))
/*       */     {
/*       */ 
/*  2496 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 257);
/*  2497 */       localSQLException.fillInStackTrace();
/*  2498 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private static final Object parseConnectionProperty_Object(Properties paramProperties, String paramString, Object paramObject)
/*       */     throws SQLException
/*       */   {
/*  2515 */     Object localObject1 = paramObject;
/*  2516 */     if (paramProperties != null)
/*       */     {
/*  2518 */       Object localObject2 = paramProperties.get(paramString);
/*  2519 */       if (localObject2 != null)
/*  2520 */         localObject1 = localObject2;
/*       */     }
/*  2522 */     return localObject1;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private static final String parseConnectionProperty_String(Properties paramProperties, String paramString1, byte paramByte, String paramString2)
/*       */     throws SQLException
/*       */   {
/*  2551 */     String str = null;
/*  2552 */     if (((paramByte == 1) || (paramByte == 3)) && (paramProperties != null))
/*       */     {
/*       */ 
/*  2555 */       str = paramProperties.getProperty(paramString1);
/*  2556 */       if ((str == null) && (!paramString1.startsWith("oracle.")) && (!paramString1.startsWith("java.")) && (!paramString1.startsWith("javax.")))
/*  2557 */         str = paramProperties.getProperty("oracle.jdbc." + paramString1);
/*       */     }
/*  2559 */     if ((str == null) && ((paramByte == 2) || (paramByte == 3)))
/*       */     {
/*       */ 
/*  2562 */       if ((paramString1.startsWith("oracle.")) || (paramString1.startsWith("java.")) || (paramString1.startsWith("javax."))) {
/*  2563 */         str = getSystemProperty(paramString1, null);
/*       */       } else
/*  2565 */         str = getSystemProperty("oracle.jdbc." + paramString1, null);
/*       */     }
/*  2567 */     if (str == null)
/*  2568 */       str = paramString2;
/*  2569 */     return str;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private static final int parseConnectionProperty_int(Properties paramProperties, String paramString, byte paramByte, int paramInt)
/*       */     throws SQLException
/*       */   {
/*  2580 */     int i = paramInt;
/*  2581 */     String str = parseConnectionProperty_String(paramProperties, paramString, paramByte, null);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  2586 */     if (str != null)
/*       */     {
/*       */       try
/*       */       {
/*  2590 */         i = Integer.parseInt(str);
/*       */ 
/*       */       }
/*       */       catch (NumberFormatException localNumberFormatException)
/*       */       {
/*       */ 
/*  2596 */         SQLException localSQLException = DatabaseError.createSqlException(null, 190, "Property is '" + paramString + "' and value is '" + str + "'");
/*  2597 */         localSQLException.fillInStackTrace();
/*  2598 */         throw localSQLException;
/*       */       }
/*       */     }
/*       */     
/*       */ 
/*  2603 */     return i;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private static final long parseConnectionProperty_long(Properties paramProperties, String paramString, byte paramByte, long paramLong)
/*       */     throws SQLException
/*       */   {
/*  2614 */     long l = paramLong;
/*  2615 */     String str = parseConnectionProperty_String(paramProperties, paramString, paramByte, null);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  2620 */     if (str != null)
/*       */     {
/*       */       try
/*       */       {
/*  2624 */         l = Long.parseLong(str);
/*       */ 
/*       */       }
/*       */       catch (NumberFormatException localNumberFormatException)
/*       */       {
/*       */ 
/*  2630 */         SQLException localSQLException = DatabaseError.createSqlException(null, 190, "Property is '" + paramString + "' and value is '" + str + "'");
/*  2631 */         localSQLException.fillInStackTrace();
/*  2632 */         throw localSQLException;
/*       */       }
/*       */     }
/*       */     
/*       */ 
/*  2637 */     return l;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private static final boolean parseConnectionProperty_boolean(Properties paramProperties, String paramString, byte paramByte, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  2648 */     boolean bool = paramBoolean;
/*  2649 */     String str = parseConnectionProperty_String(paramProperties, paramString, paramByte, null);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  2654 */     if (str != null)
/*       */     {
/*  2656 */       if (str.equalsIgnoreCase("false")) {
/*  2657 */         bool = false;
/*  2658 */       } else if (str.equalsIgnoreCase("true"))
/*  2659 */         bool = true;
/*       */     }
/*  2661 */     return bool;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private static String parseLoginOption(String paramString, Properties paramProperties, String[] paramArrayOfString1, String[] paramArrayOfString2)
/*       */   {
/*  2681 */     int j = 0;
/*  2682 */     String str1 = null;
/*  2683 */     String str2 = null;
/*       */     
/*       */ 
/*  2686 */     if (paramString == null) {
/*  2687 */       return null;
/*       */     }
/*  2689 */     int k = paramString.length();
/*       */     
/*  2691 */     if (k == 0) {
/*  2692 */       return null;
/*       */     }
/*       */     
/*  2695 */     int i = paramString.indexOf('[');
/*  2696 */     if (i > 0) {
/*  2697 */       j = paramString.indexOf(']');
/*  2698 */       str2 = paramString.substring(i + 1, j);
/*  2699 */       str2 = str2.trim();
/*       */       
/*  2701 */       if (str2.length() > 0) {
/*  2702 */         paramArrayOfString2[0] = str2;
/*       */       }
/*       */       
/*  2705 */       paramString = paramString.substring(0, i) + paramString.substring(j + 1, k);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  2710 */     String str3 = paramString.toLowerCase();
/*       */     
/*       */ 
/*  2713 */     i = str3.lastIndexOf(" as ");
/*       */     
/*  2715 */     if ((i == -1) || (i < str3.lastIndexOf("\""))) {
/*  2716 */       return paramString;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  2721 */     str1 = paramString.substring(0, i);
/*       */     
/*  2723 */     i += 4;
/*       */     
/*       */ 
/*  2726 */     while ((i < k) && (str3.charAt(i) == ' ')) {
/*  2727 */       i++;
/*       */     }
/*  2729 */     if (i == k) {
/*  2730 */       return paramString;
/*       */     }
/*  2732 */     String str4 = str3.substring(i).trim();
/*       */     
/*  2734 */     if (str4.length() > 0) {
/*  2735 */       paramArrayOfString1[0] = str4;
/*       */     }
/*  2737 */     return str1;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private static final Hashtable parseUrl(String paramString1, String paramString2, String paramString3)
/*       */     throws SQLException
/*       */   {
/*  2758 */     Hashtable localHashtable = new Hashtable(5);
/*  2759 */     int i = paramString1.indexOf(':', paramString1.indexOf(58) + 1) + 1;
/*  2760 */     int j = paramString1.length();
/*       */     
/*       */ 
/*  2763 */     if (i == j) {
/*  2764 */       return localHashtable;
/*       */     }
/*  2766 */     int k = paramString1.indexOf(':', i);
/*       */     
/*       */ 
/*  2769 */     if (k == -1)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2775 */       return localHashtable;
/*       */     }
/*       */     
/*       */ 
/*  2779 */     localHashtable.put("protocol", paramString1.substring(i, k));
/*       */     
/*  2781 */     int m = k + 1;
/*  2782 */     int n = paramString1.indexOf('/', m);
/*       */     
/*  2784 */     int i1 = paramString1.indexOf('@', m);
/*       */     
/*       */ 
/*       */     Object localObject;
/*       */     
/*  2789 */     if ((i1 > m) && (m > i) && (n == -1))
/*       */     {
/*       */ 
/*  2792 */       localObject = DatabaseError.createSqlException(null, 67);
/*  2793 */       ((SQLException)localObject).fillInStackTrace();
/*  2794 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*  2798 */     if (i1 == -1) {
/*  2799 */       i1 = j;
/*       */     }
/*  2801 */     if (n == -1) {
/*  2802 */       n = i1;
/*       */     }
/*  2804 */     if ((n < i1) && (n != m) && (i1 != m))
/*       */     {
/*  2806 */       localHashtable.put("user", paramString1.substring(m, n));
/*  2807 */       localHashtable.put("password", paramString1.substring(n + 1, i1));
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  2813 */     if ((n <= i1) && ((n == m) || (i1 == m)))
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2820 */       if (i1 < j)
/*       */       {
/*  2822 */         localObject = paramString1.substring(i1 + 1);
/*  2823 */         String[] arrayOfString = getSecretStoreCredentials((String)localObject, paramString2, paramString3);
/*  2824 */         if ((arrayOfString[0] != null) || (arrayOfString[1] != null))
/*       */         {
/*  2826 */           localHashtable.put("user", arrayOfString[0]);
/*  2827 */           localHashtable.put("password", arrayOfString[1]);
/*       */         }
/*       */       }
/*       */     }
/*       */     
/*       */ 
/*  2833 */     if (i1 < j) {
/*  2834 */       localHashtable.put("database", paramString1.substring(i1 + 1));
/*       */     }
/*  2836 */     return localHashtable;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private static final synchronized String[] getSecretStoreCredentials(String paramString1, String paramString2, String paramString3)
/*       */     throws SQLException
/*       */   {
/*  2998 */     String[] arrayOfString = new String[2];
/*  2999 */     arrayOfString[0] = null;
/*  3000 */     arrayOfString[1] = null;
/*       */     
/*  3002 */     if (paramString2 != null)
/*       */     {
/*       */       try
/*       */       {
/*  3006 */         if (paramString2.startsWith("(")) {
/*  3007 */           paramString2 = "file:" + CustomSSLSocketFactory.processWalletLocation(paramString2);
/*       */         }
/*  3009 */         OracleWallet localOracleWallet = new OracleWallet();
/*  3010 */         if (localOracleWallet.exists(paramString2))
/*       */         {
/*       */ 
/*       */ 
/*  3014 */           localObject = null;
/*  3015 */           if (paramString3 != null) {
/*  3016 */             localObject = paramString3.toCharArray();
/*       */           }
/*       */           
/*       */ 
/*  3020 */           localOracleWallet.open(paramString2, (char[])localObject);
/*  3021 */           OracleSecretStore localOracleSecretStore = localOracleWallet.getSecretStore();
/*       */           
/*       */ 
/*       */ 
/*  3025 */           if (localOracleSecretStore.containsAlias("oracle.security.client.default_username")) {
/*  3026 */             arrayOfString[0] = new String(localOracleSecretStore.getSecret("oracle.security.client.default_username"));
/*       */           }
/*  3028 */           if (localOracleSecretStore.containsAlias("oracle.security.client.default_password")) {
/*  3029 */             arrayOfString[1] = new String(localOracleSecretStore.getSecret("oracle.security.client.default_password"));
/*       */           }
/*       */           
/*  3032 */           Enumeration localEnumeration = localOracleWallet.getSecretStore().internalAliases();
/*       */           
/*  3034 */           String str1 = null;
/*  3035 */           while (localEnumeration.hasMoreElements())
/*       */           {
/*  3037 */             str1 = (String)localEnumeration.nextElement();
/*  3038 */             if ((str1.startsWith("oracle.security.client.connect_string")) && 
/*       */             
/*  3040 */               (paramString1.equalsIgnoreCase(new String(localOracleSecretStore.getSecret(str1)))))
/*       */             {
/*       */ 
/*  3043 */               String str2 = str1.substring("oracle.security.client.connect_string".length());
/*  3044 */               arrayOfString[0] = new String(localOracleSecretStore.getSecret("oracle.security.client.username" + str2));
/*       */               
/*  3046 */               arrayOfString[1] = new String(localOracleSecretStore.getSecret("oracle.security.client.password" + str2));
/*       */             }
/*       */             
/*       */           }
/*       */           
/*       */         }
/*       */         
/*       */ 
/*       */       }
/*       */       catch (NoClassDefFoundError localNoClassDefFoundError)
/*       */       {
/*  3057 */         localObject = DatabaseError.createSqlException(null, 167, localNoClassDefFoundError);
/*  3058 */         ((SQLException)localObject).fillInStackTrace();
/*  3059 */         throw ((Throwable)localObject);
/*       */ 
/*       */       }
/*       */       catch (Exception localException)
/*       */       {
/*  3064 */         if ((localException instanceof RuntimeException)) { throw ((RuntimeException)localException);
/*       */         }
/*       */         
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3074 */         Object localObject = DatabaseError.createSqlException(null, 168, localException);
/*  3075 */         ((SQLException)localObject).fillInStackTrace();
/*  3076 */         throw ((Throwable)localObject);
/*       */       }
/*       */     }
/*       */     
/*       */ 
/*  3081 */     return arrayOfString;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private String translateConnStr(String paramString)
/*       */     throws SQLException
/*       */   {
/*  3100 */     int i = 0;
/*  3101 */     int j = 0;
/*       */     
/*  3103 */     if ((paramString == null) || (paramString.equals(""))) {
/*  3104 */       return paramString;
/*       */     }
/*       */     
/*  3107 */     if (paramString.indexOf(')') != -1) {
/*  3108 */       return paramString;
/*       */     }
/*  3110 */     int k = 0;
/*  3111 */     if (paramString.indexOf('[') != -1)
/*       */     {
/*       */ 
/*  3114 */       i = paramString.indexOf(']');
/*  3115 */       if (i == -1)
/*       */       {
/*  3117 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 67, paramString);
/*  3118 */         ((SQLException)localObject).fillInStackTrace();
/*  3119 */         throw ((Throwable)localObject);
/*       */       }
/*  3121 */       k = 1;
/*       */     }
/*       */     
/*  3124 */     i = paramString.indexOf(':', i);
/*  3125 */     if (i == -1)
/*  3126 */       return paramString;
/*  3127 */     j = paramString.indexOf(':', i + 1);
/*  3128 */     if (j == -1) {
/*  3129 */       return paramString;
/*       */     }
/*       */     
/*  3132 */     if (paramString.indexOf(':', j + 1) != -1)
/*       */     {
/*       */ 
/*  3135 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 67, paramString);
/*  3136 */       ((SQLException)localObject).fillInStackTrace();
/*  3137 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*  3141 */     Object localObject = null;
/*  3142 */     if (k != 0) {
/*  3143 */       localObject = paramString.substring(1, i - 1);
/*       */     } else {
/*  3145 */       localObject = paramString.substring(0, i);
/*       */     }
/*  3147 */     String str2 = paramString.substring(i + 1, j);
/*  3148 */     String str3 = paramString.substring(j + 1, paramString.length());
/*       */     
/*  3150 */     String str1 = "(DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=" + (String)localObject + ")(PORT=" + str2 + "))(CONNECT_DATA=(SID=" + str3 + ")))";
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3156 */     return str1;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   protected static String getSystemPropertyPollInterval()
/*       */   {
/*  3163 */     return getSystemProperty("oracle.jdbc.TimeoutPollInterval", "1000");
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   static String getSystemPropertyFastConnectionFailover(String paramString)
/*       */   {
/*  3172 */     return getSystemProperty("oracle.jdbc.FastConnectionFailover", paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   static String getSystemPropertyJserverVersion()
/*       */   {
/*  3180 */     return getSystemProperty("oracle.jserver.version", null);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   private static String getSystemProperty(String paramString1, String paramString2)
/*       */   {
/*  3187 */     if (paramString1 != null)
/*       */     {
/*  3189 */       final String str1 = paramString1;
/*  3190 */       final String str2 = paramString2;
/*  3191 */       String[] arrayOfString = { paramString2 };
/*  3192 */       java.security.AccessController.doPrivileged(new java.security.PrivilegedAction()
/*       */       {
/*       */         public Object run()
/*       */         {
/*  3196 */           this.val$rets[0] = System.getProperty(str1, str2);
/*  3197 */           return null;
/*       */         }
/*  3199 */       });
/*  3200 */       return arrayOfString[0];
/*       */     }
/*       */     
/*  3203 */     return paramString2;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   abstract void initializePassword(String paramString)
/*       */     throws SQLException;
/*       */   
/*       */ 
/*       */   public Properties getProperties()
/*       */   {
/*  3214 */     Properties localProperties = new Properties();
/*       */     try
/*       */     {
/*  3217 */       Class localClass1 = null;
/*  3218 */       Class localClass2 = null;
/*       */       try
/*       */       {
/*  3221 */         localClass1 = ClassRef.newInstance("oracle.jdbc.OracleConnection").get();
/*  3222 */         localClass2 = ClassRef.newInstance("oracle.jdbc.driver.PhysicalConnection").get();
/*       */       }
/*       */       catch (ClassNotFoundException localClassNotFoundException) {}
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3231 */       Field[] arrayOfField = localClass2.getDeclaredFields();
/*  3232 */       for (int i = 0; i < arrayOfField.length; i++)
/*       */       {
/*  3234 */         int j = arrayOfField[i].getModifiers();
/*  3235 */         if (!java.lang.reflect.Modifier.isStatic(j))
/*       */         {
/*       */ 
/*  3238 */           String str1 = arrayOfField[i].getName();
/*       */           
/*       */ 
/*  3241 */           String str2 = "CONNECTION_PROPERTY_" + propertyVariableName(str1);
/*       */           
/*       */ 
/*       */ 
/*  3245 */           Field localField = null;
/*       */           
/*       */           try
/*       */           {
/*  3249 */             localField = localClass1.getField(str2);
/*       */           }
/*       */           catch (NoSuchFieldException localNoSuchFieldException)
/*       */           {
/*       */             continue;
/*       */           }
/*       */           
/*       */ 
/*  3257 */           if (!str2.matches(".*PASSWORD.*"))
/*       */           {
/*       */ 
/*  3260 */             String str3 = (String)localField.get(null);
/*  3261 */             String str4 = arrayOfField[i].getType().getName();
/*  3262 */             if (str4.equals("boolean"))
/*       */             {
/*  3264 */               boolean bool = arrayOfField[i].getBoolean(this);
/*  3265 */               if (bool) {
/*  3266 */                 localProperties.setProperty(str3, "true");
/*       */               } else {
/*  3268 */                 localProperties.setProperty(str3, "false");
/*       */               }
/*  3270 */             } else if (str4.equals("int"))
/*       */             {
/*  3272 */               int k = arrayOfField[i].getInt(this);
/*  3273 */               localProperties.setProperty(str3, Integer.toString(k));
/*       */             }
/*  3275 */             else if (str4.equals("long"))
/*       */             {
/*  3277 */               long l = arrayOfField[i].getLong(this);
/*  3278 */               localProperties.setProperty(str3, Long.toString(l));
/*       */             }
/*  3280 */             else if (str4.equals("java.lang.String"))
/*       */             {
/*  3282 */               String str5 = (String)arrayOfField[i].get(this);
/*  3283 */               if (str5 != null) {
/*  3284 */                 localProperties.setProperty(str3, str5);
/*       */               }
/*       */             }
/*       */           }
/*       */         }
/*       */       }
/*       */     }
/*       */     catch (IllegalAccessException localIllegalAccessException) {}
/*       */     
/*  3293 */     return localProperties;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   /**
/*       */    * @deprecated
/*       */    */
/*       */   public synchronized Connection _getPC()
/*       */   {
/*  3314 */     return null;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized oracle.jdbc.internal.OracleConnection getPhysicalConnection()
/*       */   {
/*  3330 */     return this;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized boolean isLogicalConnection()
/*       */   {
/*  3343 */     return false;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void initialize(Hashtable paramHashtable, Map paramMap1, Map paramMap2)
/*       */     throws SQLException
/*       */   {
/*  3356 */     this.clearStatementMetaData = false;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3362 */     if (paramHashtable != null) {
/*  3363 */       this.descriptorCacheStack[this.dci] = paramHashtable;
/*       */     } else {
/*  3365 */       this.descriptorCacheStack[this.dci] = new Hashtable(10);
/*       */     }
/*  3367 */     this.map = paramMap1;
/*       */     
/*  3369 */     if (paramMap2 != null) {
/*  3370 */       this.javaObjectMap = paramMap2;
/*       */     } else {
/*  3372 */       this.javaObjectMap = new Hashtable(10);
/*       */     }
/*  3374 */     this.lifecycle = 1;
/*  3375 */     this.txnLevel = 2;
/*       */     
/*       */ 
/*  3378 */     this.clientIdSet = false;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void initializeSetCHARCharSetObjs()
/*       */   {
/*  3386 */     this.setCHARNCharSetObj = this.conversion.getDriverNCharSetObj();
/*  3387 */     this.setCHARCharSetObj = this.conversion.getDriverCharSetObj();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   OracleTimeout getTimeout()
/*       */     throws SQLException
/*       */   {
/*  3401 */     if (this.timeout == null)
/*       */     {
/*  3403 */       this.timeout = OracleTimeout.newTimeout(this.url);
/*       */     }
/*       */     
/*  3406 */     return this.timeout;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized Statement createStatement()
/*       */     throws SQLException
/*       */   {
/*  3425 */     return createStatement(-1, -1);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized Statement createStatement(int paramInt1, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  3447 */     if (this.lifecycle != 1)
/*       */     {
/*  3449 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  3450 */       ((SQLException)localObject).fillInStackTrace();
/*  3451 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*  3454 */     Object localObject = null;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3460 */     localObject = this.driverExtension.allocateStatement(this, paramInt1, paramInt2);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3467 */     return new OracleStatementWrapper((oracle.jdbc.OracleStatement)localObject);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized PreparedStatement prepareStatement(String paramString)
/*       */     throws SQLException
/*       */   {
/*  3488 */     return prepareStatement(paramString, -1, -1);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   /**
/*       */    * @deprecated
/*       */    */
/*       */   public synchronized PreparedStatement prepareStatementWithKey(String paramString)
/*       */     throws SQLException
/*       */   {
/*  3509 */     if (this.lifecycle != 1)
/*       */     {
/*  3511 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  3512 */       ((SQLException)localObject).fillInStackTrace();
/*  3513 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*  3516 */     if (paramString == null) {
/*  3517 */       return null;
/*       */     }
/*  3519 */     if (!isStatementCacheInitialized())
/*       */     {
/*  3521 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 95);
/*  3522 */       ((SQLException)localObject).fillInStackTrace();
/*  3523 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3530 */     Object localObject = null;
/*       */     
/*  3532 */     localObject = (OraclePreparedStatement)this.statementCache.searchExplicitCache(paramString);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3543 */     if (localObject != null) {
/*  3544 */       localObject = new OraclePreparedStatementWrapper((oracle.jdbc.OraclePreparedStatement)localObject);
/*       */     }
/*  3546 */     return (PreparedStatement)localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized PreparedStatement prepareStatement(String paramString, int paramInt1, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  3577 */     if ((paramString == null) || (paramString.length() == 0))
/*       */     {
/*  3579 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 104);
/*  3580 */       ((SQLException)localObject).fillInStackTrace();
/*  3581 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*  3584 */     if (this.lifecycle != 1)
/*       */     {
/*  3586 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  3587 */       ((SQLException)localObject).fillInStackTrace();
/*  3588 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*  3591 */     Object localObject = null;
/*       */     
/*       */ 
/*  3594 */     if (this.statementCache != null) {
/*  3595 */       localObject = (OraclePreparedStatement)this.statementCache.searchImplicitCache(paramString, 1, (paramInt1 != -1) || (paramInt2 != -1) ? ResultSetUtil.getRsetTypeCode(paramInt1, paramInt2) : 1);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  3601 */     if (localObject == null) {
/*  3602 */       localObject = this.driverExtension.allocatePreparedStatement(this, paramString, paramInt1, paramInt2);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3614 */     return new OraclePreparedStatementWrapper((oracle.jdbc.OraclePreparedStatement)localObject);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized CallableStatement prepareCall(String paramString)
/*       */     throws SQLException
/*       */   {
/*  3633 */     return prepareCall(paramString, -1, -1);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized CallableStatement prepareCall(String paramString, int paramInt1, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  3662 */     if ((paramString == null) || (paramString.length() == 0))
/*       */     {
/*  3664 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 104);
/*  3665 */       ((SQLException)localObject).fillInStackTrace();
/*  3666 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*  3669 */     if (this.lifecycle != 1)
/*       */     {
/*  3671 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  3672 */       ((SQLException)localObject).fillInStackTrace();
/*  3673 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*  3676 */     Object localObject = null;
/*       */     
/*  3678 */     if (this.statementCache != null) {
/*  3679 */       localObject = (OracleCallableStatement)this.statementCache.searchImplicitCache(paramString, 2, (paramInt1 != -1) || (paramInt2 != -1) ? ResultSetUtil.getRsetTypeCode(paramInt1, paramInt2) : 1);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  3685 */     if (localObject == null) {
/*  3686 */       localObject = this.driverExtension.allocateCallableStatement(this, paramString, paramInt1, paramInt2);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3697 */     return new OracleCallableStatementWrapper((oracle.jdbc.OracleCallableStatement)localObject);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized CallableStatement prepareCallWithKey(String paramString)
/*       */     throws SQLException
/*       */   {
/*  3717 */     if (this.lifecycle != 1)
/*       */     {
/*  3719 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  3720 */       ((SQLException)localObject).fillInStackTrace();
/*  3721 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*  3724 */     if (paramString == null) {
/*  3725 */       return null;
/*       */     }
/*  3727 */     if (!isStatementCacheInitialized())
/*       */     {
/*  3729 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 95);
/*  3730 */       ((SQLException)localObject).fillInStackTrace();
/*  3731 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3738 */     Object localObject = null;
/*       */     
/*  3740 */     localObject = (OracleCallableStatement)this.statementCache.searchExplicitCache(paramString);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3750 */     if (localObject != null) {
/*  3751 */       localObject = new OracleCallableStatementWrapper((oracle.jdbc.OracleCallableStatement)localObject);
/*       */     }
/*  3753 */     return (CallableStatement)localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public String nativeSQL(String paramString)
/*       */     throws SQLException
/*       */   {
/*  3765 */     if (this.sqlObj == null)
/*       */     {
/*  3767 */       this.sqlObj = new OracleSql(this.conversion);
/*       */     }
/*       */     
/*  3770 */     this.sqlObj.initialize(paramString);
/*       */     
/*  3772 */     String str = this.sqlObj.getSql(this.processEscapes, this.convertNcharLiterals);
/*       */     
/*  3774 */     return str;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setAutoCommit(boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  3784 */     if (paramBoolean) {
/*  3785 */       disallowGlobalTxnMode(116);
/*       */     }
/*  3787 */     if (this.lifecycle != 1)
/*       */     {
/*  3789 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  3790 */       localSQLException.fillInStackTrace();
/*  3791 */       throw localSQLException;
/*       */     }
/*       */     
/*  3794 */     needLine();
/*  3795 */     doSetAutoCommit(paramBoolean);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public boolean getAutoCommit()
/*       */     throws SQLException
/*       */   {
/*  3803 */     return this.autocommit;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void cancel()
/*       */     throws SQLException
/*       */   {
/*  3822 */     OracleStatement localOracleStatement = this.statements;
/*       */     
/*  3824 */     if ((this.lifecycle != 1) && (this.lifecycle != 16))
/*       */     {
/*  3826 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  3827 */       localSQLException1.fillInStackTrace();
/*  3828 */       throw localSQLException1;
/*       */     }
/*       */     
/*  3831 */     int i = 0;
/*       */     
/*  3833 */     while (localOracleStatement != null)
/*       */     {
/*       */ 
/*       */ 
/*       */       try
/*       */       {
/*       */ 
/*       */ 
/*  3841 */         if (localOracleStatement.doCancel()) {
/*  3842 */           i = 1;
/*       */         }
/*       */       }
/*       */       catch (SQLException localSQLException2) {}
/*       */       
/*       */ 
/*  3848 */       localOracleStatement = localOracleStatement.next;
/*       */     }
/*       */     
/*       */ 
/*  3852 */     if (i == 0) {
/*  3853 */       cancelOperationOnServer(false);
/*       */     }
/*       */   }
/*       */   
/*       */   public void commit(EnumSet<OracleConnection.CommitOption> paramEnumSet)
/*       */     throws SQLException
/*       */   {
/*  3860 */     int i = 0;
/*  3861 */     Object localObject; if (paramEnumSet != null)
/*       */     {
/*  3863 */       if (((paramEnumSet.contains(OracleConnection.CommitOption.WRITEBATCH)) && (paramEnumSet.contains(OracleConnection.CommitOption.WRITEIMMED))) || ((paramEnumSet.contains(OracleConnection.CommitOption.WAIT)) && (paramEnumSet.contains(OracleConnection.CommitOption.NOWAIT))))
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3869 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 191);
/*  3870 */         ((SQLException)localObject).fillInStackTrace();
/*  3871 */         throw ((Throwable)localObject);
/*       */       }
/*       */       
/*       */ 
/*  3875 */       for (localObject = paramEnumSet.iterator(); ((Iterator)localObject).hasNext();) { OracleConnection.CommitOption localCommitOption = (OracleConnection.CommitOption)((Iterator)localObject).next();
/*  3876 */         i |= localCommitOption.getCode();
/*       */       } }
/*  3878 */     commit(i);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   synchronized void commit(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  3889 */     disallowGlobalTxnMode(114);
/*       */     
/*  3891 */     if (this.lifecycle != 1)
/*       */     {
/*  3893 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  3894 */       ((SQLException)localObject).fillInStackTrace();
/*  3895 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*  3898 */     Object localObject = this.statements;
/*       */     
/*  3900 */     while (localObject != null)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*  3905 */       if (!((OracleStatement)localObject).closed) {
/*  3906 */         ((OracleStatement)localObject).sendBatch();
/*       */       }
/*  3908 */       localObject = ((OracleStatement)localObject).next;
/*       */     }
/*  3910 */     if ((((paramInt & OracleConnection.CommitOption.WRITEBATCH.getCode()) != 0) && ((paramInt & OracleConnection.CommitOption.WRITEIMMED.getCode()) != 0)) || (((paramInt & OracleConnection.CommitOption.WAIT.getCode()) != 0) && ((paramInt & OracleConnection.CommitOption.NOWAIT.getCode()) != 0)))
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3916 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 191);
/*  3917 */       localSQLException.fillInStackTrace();
/*  3918 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3925 */     registerHeartbeat();
/*       */     
/*  3927 */     needLine();
/*  3928 */     doCommit(paramInt);
/*       */   }
/*       */   
/*       */   public void commit()
/*       */     throws SQLException
/*       */   {
/*  3934 */     commit(this.commitOption);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void rollback()
/*       */     throws SQLException
/*       */   {
/*  3943 */     disallowGlobalTxnMode(115);
/*       */     
/*  3945 */     if (this.lifecycle != 1)
/*       */     {
/*  3947 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  3948 */       ((SQLException)localObject).fillInStackTrace();
/*  3949 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*  3953 */     Object localObject = this.statements;
/*       */     
/*  3955 */     while (localObject != null)
/*       */     {
/*  3957 */       if (((OracleStatement)localObject).isOracleBatchStyle()) {
/*  3958 */         ((OracleStatement)localObject).clearBatch();
/*       */       }
/*  3960 */       localObject = ((OracleStatement)localObject).next;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3967 */     registerHeartbeat();
/*       */     
/*  3969 */     needLine();
/*  3970 */     doRollback();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void close()
/*       */     throws SQLException
/*       */   {
/*  3984 */     if ((this.lifecycle == 2) || (this.lifecycle == 4)) {
/*  3985 */       return;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  3990 */     needLineUnchecked();
/*       */     
/*       */     try
/*       */     {
/*  3994 */       if (this.closeCallback != null) {
/*  3995 */         this.closeCallback.beforeClose(this, this.privateData);
/*       */       }
/*  3997 */       closeStatementCache();
/*  3998 */       closeStatements(false);
/*       */       
/*  4000 */       if (this.lifecycle == 1) { this.lifecycle = 2;
/*       */       }
/*       */       
/*  4003 */       if (this.isProxy)
/*       */       {
/*  4005 */         close(1);
/*       */       }
/*       */       
/*  4008 */       if (this.timeZoneTab != null) {
/*  4009 */         this.timeZoneTab.freeInstance();
/*       */       }
/*  4011 */       logoff();
/*  4012 */       cleanup();
/*       */       
/*  4014 */       if (this.timeout != null) {
/*  4015 */         this.timeout.close();
/*       */       }
/*  4017 */       if (this.closeCallback != null) {
/*  4018 */         this.closeCallback.afterClose(this.privateData);
/*       */       }
/*       */     }
/*       */     finally {
/*  4022 */       this.lifecycle = 4;
/*  4023 */       this.isUsable = false;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public String getDataIntegrityAlgorithmName()
/*       */     throws SQLException
/*       */   {
/*  4034 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  4035 */     localSQLException.fillInStackTrace();
/*  4036 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */   public String getEncryptionAlgorithmName()
/*       */     throws SQLException
/*       */   {
/*  4043 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  4044 */     localSQLException.fillInStackTrace();
/*  4045 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */   public String getAuthenticationAdaptorName()
/*       */     throws SQLException
/*       */   {
/*  4052 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  4053 */     localSQLException.fillInStackTrace();
/*  4054 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void closeInternal(boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  4064 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  4065 */     localSQLException.fillInStackTrace();
/*  4066 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void cleanupAndClose(boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  4077 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  4078 */     localSQLException.fillInStackTrace();
/*  4079 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void cleanupAndClose()
/*       */     throws SQLException
/*       */   {
/*  4090 */     if (this.lifecycle != 1) {
/*  4091 */       return;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  4096 */     this.lifecycle = 16;
/*       */     
/*       */ 
/*  4099 */     cancel();
/*       */   }
/*       */   
/*       */ 
/*       */   synchronized void closeLogicalConnection()
/*       */     throws SQLException
/*       */   {
/*  4106 */     if ((this.lifecycle == 1) || (this.lifecycle == 16) || (this.lifecycle == 2))
/*       */     {
/*       */ 
/*       */ 
/*  4110 */       this.savepointStatement = null;
/*  4111 */       closeStatements(true);
/*       */       
/*  4113 */       if (this.clientIdSet) {
/*  4114 */         clearClientIdentifier(this.clientId);
/*       */       }
/*  4116 */       this.logicalConnectionAttached = null;
/*  4117 */       this.lifecycle = 1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void close(Properties paramProperties)
/*       */     throws SQLException
/*       */   {
/*  4137 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 152);
/*  4138 */     localSQLException.fillInStackTrace();
/*  4139 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void close(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  4155 */     if ((paramInt & 0x1000) != 0)
/*       */     {
/*  4157 */       close();
/*       */       
/*  4159 */       return;
/*       */     }
/*       */     
/*  4162 */     if (((paramInt & 0x1) != 0) && (this.isProxy))
/*       */     {
/*  4164 */       purgeStatementCache();
/*  4165 */       closeStatements(false);
/*  4166 */       this.descriptorCacheStack[(this.dci--)] = null;
/*       */       
/*  4168 */       closeProxySession();
/*       */       
/*  4170 */       this.isProxy = false;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*  4177 */   private static final OracleSQLPermission CALL_ABORT_PERMISSION = new OracleSQLPermission("callAbort");
/*       */   static final String DATABASE_NAME = "DATABASE_NAME";
/*       */   static final String SERVER_HOST = "SERVER_HOST";
/*       */   
/*       */   public void abort() throws SQLException {
/*  4182 */     SecurityManager localSecurityManager = System.getSecurityManager();
/*  4183 */     if (localSecurityManager != null) {
/*  4184 */       localSecurityManager.checkPermission(CALL_ABORT_PERMISSION);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  4189 */     if ((this.lifecycle == 4) || (this.lifecycle == 8)) {
/*  4190 */       return;
/*       */     }
/*       */     
/*  4193 */     this.lifecycle = 8;
/*       */     
/*       */ 
/*  4196 */     doAbort();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   abstract void doAbort()
/*       */     throws SQLException;
/*       */   
/*       */ 
/*       */ 
/*       */   void closeProxySession()
/*       */     throws SQLException
/*       */   {
/*  4209 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  4210 */     localSQLException.fillInStackTrace();
/*  4211 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public Properties getServerSessionInfo()
/*       */     throws SQLException
/*       */   {
/*  4228 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  4229 */     localSQLException.fillInStackTrace();
/*  4230 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void applyConnectionAttributes(Properties paramProperties)
/*       */     throws SQLException
/*       */   {
/*  4243 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 152);
/*  4244 */     localSQLException.fillInStackTrace();
/*  4245 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized Properties getConnectionAttributes()
/*       */     throws SQLException
/*       */   {
/*  4258 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 152);
/*  4259 */     localSQLException.fillInStackTrace();
/*  4260 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized Properties getUnMatchedConnectionAttributes()
/*       */     throws SQLException
/*       */   {
/*  4273 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 152);
/*  4274 */     localSQLException.fillInStackTrace();
/*  4275 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setAbandonedTimeoutEnabled(boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  4289 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 152);
/*  4290 */     localSQLException.fillInStackTrace();
/*  4291 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void registerConnectionCacheCallback(OracleConnectionCacheCallback paramOracleConnectionCacheCallback, Object paramObject, int paramInt)
/*       */     throws SQLException
/*       */   {
/*  4304 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 152);
/*  4305 */     localSQLException.fillInStackTrace();
/*  4306 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public OracleConnectionCacheCallback getConnectionCacheCallbackObj()
/*       */     throws SQLException
/*       */   {
/*  4319 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 152);
/*  4320 */     localSQLException.fillInStackTrace();
/*  4321 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public Object getConnectionCacheCallbackPrivObj()
/*       */     throws SQLException
/*       */   {
/*  4333 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 152);
/*  4334 */     localSQLException.fillInStackTrace();
/*  4335 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int getConnectionCacheCallbackFlag()
/*       */     throws SQLException
/*       */   {
/*  4347 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 152);
/*  4348 */     localSQLException.fillInStackTrace();
/*  4349 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setConnectionReleasePriority(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  4362 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 152);
/*  4363 */     localSQLException.fillInStackTrace();
/*  4364 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int getConnectionReleasePriority()
/*       */     throws SQLException
/*       */   {
/*  4376 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 152);
/*  4377 */     localSQLException.fillInStackTrace();
/*  4378 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized boolean isClosed()
/*       */     throws SQLException
/*       */   {
/*  4391 */     return this.lifecycle != 1;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public synchronized boolean isProxySession()
/*       */   {
/*  4398 */     return this.isProxy;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void openProxySession(int paramInt, Properties paramProperties)
/*       */     throws SQLException
/*       */   {
/*  4407 */     int i = 1;
/*       */     
/*  4409 */     if (this.isProxy)
/*       */     {
/*  4411 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 149);
/*  4412 */       ((SQLException)localObject1).fillInStackTrace();
/*  4413 */       throw ((Throwable)localObject1);
/*       */     }
/*       */     
/*  4416 */     Object localObject1 = paramProperties.getProperty("PROXY_USER_NAME");
/*  4417 */     String str1 = paramProperties.getProperty("PROXY_USER_PASSWORD");
/*  4418 */     String str2 = paramProperties.getProperty("PROXY_DISTINGUISHED_NAME");
/*       */     
/*  4420 */     Object localObject2 = paramProperties.get("PROXY_CERTIFICATE");
/*       */     Object localObject3;
/*  4422 */     if (paramInt == 1)
/*       */     {
/*  4424 */       if ((localObject1 == null) && (str1 == null))
/*       */       {
/*  4426 */         localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 150);
/*  4427 */         ((SQLException)localObject3).fillInStackTrace();
/*  4428 */         throw ((Throwable)localObject3);
/*       */       }
/*       */     }
/*  4431 */     else if (paramInt == 2)
/*       */     {
/*  4433 */       if (str2 == null)
/*       */       {
/*  4435 */         localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 150);
/*  4436 */         ((SQLException)localObject3).fillInStackTrace();
/*  4437 */         throw ((Throwable)localObject3);
/*       */       }
/*       */     }
/*  4440 */     else if (paramInt == 3)
/*       */     {
/*  4442 */       if (localObject2 == null)
/*       */       {
/*  4444 */         localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 150);
/*  4445 */         ((SQLException)localObject3).fillInStackTrace();
/*  4446 */         throw ((Throwable)localObject3);
/*       */       }
/*       */       
/*       */       try
/*       */       {
/*  4451 */         localObject3 = (byte[])localObject2;
/*       */ 
/*       */       }
/*       */       catch (ClassCastException localClassCastException)
/*       */       {
/*  4456 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 150);
/*  4457 */         localSQLException2.fillInStackTrace();
/*  4458 */         throw localSQLException2;
/*       */       }
/*       */       
/*       */     }
/*       */     else
/*       */     {
/*  4464 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 150);
/*  4465 */       localSQLException1.fillInStackTrace();
/*  4466 */       throw localSQLException1;
/*       */     }
/*       */     
/*  4469 */     purgeStatementCache();
/*  4470 */     closeStatements(false);
/*       */     
/*       */ 
/*       */     try
/*       */     {
/*  4475 */       doProxySession(paramInt, paramProperties);
/*  4476 */       this.dci += 1;
/*       */       
/*  4478 */       i = 0;
/*       */ 
/*       */     }
/*       */     finally
/*       */     {
/*  4483 */       if (i == 1) {
/*  4484 */         closeProxySession();
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void doProxySession(int paramInt, Properties paramProperties)
/*       */     throws SQLException
/*       */   {
/*  4495 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  4496 */     localSQLException.fillInStackTrace();
/*  4497 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void cleanup()
/*       */   {
/*  4507 */     this.fdo = null;
/*  4508 */     this.conversion = null;
/*  4509 */     this.statements = null;
/*  4510 */     this.descriptorCacheStack[this.dci] = null;
/*  4511 */     this.map = null;
/*  4512 */     this.javaObjectMap = null;
/*  4513 */     this.statementHoldingLine = null;
/*  4514 */     this.sqlObj = null;
/*  4515 */     this.isProxy = false;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized java.sql.DatabaseMetaData getMetaData()
/*       */     throws SQLException
/*       */   {
/*  4532 */     if (this.lifecycle != 1)
/*       */     {
/*  4534 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  4535 */       localSQLException.fillInStackTrace();
/*  4536 */       throw localSQLException;
/*       */     }
/*       */     
/*  4539 */     if (this.databaseMetaData == null) {
/*  4540 */       this.databaseMetaData = new OracleDatabaseMetaData(this);
/*       */     }
/*  4542 */     return this.databaseMetaData;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setReadOnly(boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  4559 */     this.readOnly = paramBoolean;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public boolean isReadOnly()
/*       */     throws SQLException
/*       */   {
/*  4575 */     return this.readOnly;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCatalog(String paramString)
/*       */     throws SQLException
/*       */   {}
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public String getCatalog()
/*       */     throws SQLException
/*       */   {
/*  4597 */     return null;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setTransactionIsolation(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  4607 */     if (this.txnLevel == paramInt) {
/*  4608 */       return;
/*       */     }
/*  4610 */     Statement localStatement = createStatement();
/*       */     
/*       */     try
/*       */     {
/*  4614 */       switch (paramInt)
/*       */       {
/*       */ 
/*       */ 
/*       */       case 2: 
/*  4619 */         localStatement.execute("ALTER SESSION SET ISOLATION_LEVEL = READ COMMITTED");
/*       */         
/*  4621 */         this.txnLevel = 2;
/*       */         
/*  4623 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case 8: 
/*  4628 */         localStatement.execute("ALTER SESSION SET ISOLATION_LEVEL = SERIALIZABLE");
/*       */         
/*  4630 */         this.txnLevel = 8;
/*       */         
/*  4632 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       default: 
/*  4637 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 30);
/*  4638 */         localSQLException.fillInStackTrace();
/*  4639 */         throw localSQLException;
/*       */       
/*       */ 
/*       */       }
/*       */       
/*       */     }
/*       */     finally
/*       */     {
/*  4647 */       localStatement.close();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public int getTransactionIsolation()
/*       */     throws SQLException
/*       */   {
/*  4656 */     return this.txnLevel;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setAutoClose(boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  4669 */     if (!paramBoolean)
/*       */     {
/*  4671 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 31);
/*  4672 */       localSQLException.fillInStackTrace();
/*  4673 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public boolean getAutoClose()
/*       */     throws SQLException
/*       */   {
/*  4685 */     return true;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public SQLWarning getWarnings()
/*       */     throws SQLException
/*       */   {
/*  4695 */     return this.sqlWarning;
/*       */   }
/*       */   
/*       */ 
/*       */   public void clearWarnings()
/*       */     throws SQLException
/*       */   {
/*  4702 */     this.sqlWarning = null;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public void setWarnings(SQLWarning paramSQLWarning)
/*       */   {
/*  4709 */     this.sqlWarning = paramSQLWarning;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setDefaultRowPrefetch(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  4753 */     if (paramInt <= 0)
/*       */     {
/*  4755 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 20);
/*  4756 */       localSQLException.fillInStackTrace();
/*  4757 */       throw localSQLException;
/*       */     }
/*       */     
/*  4760 */     this.defaultRowPrefetch = paramInt;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int getDefaultRowPrefetch()
/*       */   {
/*  4787 */     return this.defaultRowPrefetch;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public boolean getTimestamptzInGmt()
/*       */   {
/*  4794 */     return this.timestamptzInGmt;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public boolean getUse1900AsYearForTime()
/*       */   {
/*  4801 */     return this.use1900AsYearForTime;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setDefaultExecuteBatch(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  4843 */     if (paramInt <= 0)
/*       */     {
/*  4845 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 42);
/*  4846 */       localSQLException.fillInStackTrace();
/*  4847 */       throw localSQLException;
/*       */     }
/*       */     
/*  4850 */     this.defaultExecuteBatch = paramInt;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized int getDefaultExecuteBatch()
/*       */   {
/*  4878 */     return this.defaultExecuteBatch;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setRemarksReporting(boolean paramBoolean)
/*       */   {
/*  4901 */     this.reportRemarks = paramBoolean;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized boolean getRemarksReporting()
/*       */   {
/*  4914 */     return this.reportRemarks;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setIncludeSynonyms(boolean paramBoolean)
/*       */   {
/*  4935 */     this.includeSynonyms = paramBoolean;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized String[] getEndToEndMetrics()
/*       */     throws SQLException
/*       */   {
/*       */     String[] arrayOfString;
/*       */     
/*       */ 
/*       */ 
/*  4949 */     if (this.endToEndValues == null)
/*       */     {
/*  4951 */       arrayOfString = null;
/*       */     }
/*       */     else
/*       */     {
/*  4955 */       arrayOfString = new String[this.endToEndValues.length];
/*       */       
/*  4957 */       System.arraycopy(this.endToEndValues, 0, arrayOfString, 0, this.endToEndValues.length);
/*       */     }
/*  4959 */     return arrayOfString;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public short getEndToEndECIDSequenceNumber()
/*       */     throws SQLException
/*       */   {
/*  4977 */     return this.endToEndECIDSequenceNumber;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setEndToEndMetrics(String[] paramArrayOfString, short paramShort)
/*       */     throws SQLException
/*       */   {
/*  4992 */     String[] arrayOfString = new String[paramArrayOfString.length];
/*       */     
/*  4994 */     System.arraycopy(paramArrayOfString, 0, arrayOfString, 0, paramArrayOfString.length);
/*  4995 */     setEndToEndMetricsInternal(arrayOfString, paramShort);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setEndToEndMetricsInternal(String[] paramArrayOfString, short paramShort)
/*       */     throws SQLException
/*       */   {
/*  5010 */     if (paramArrayOfString != this.endToEndValues)
/*       */     {
/*  5012 */       if (paramArrayOfString.length != 4)
/*       */       {
/*       */ 
/*       */ 
/*  5016 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 156);
/*  5017 */         localSQLException1.fillInStackTrace();
/*  5018 */         throw localSQLException1;
/*       */       }
/*       */       
/*       */       String str;
/*  5022 */       for (int i = 0; i < 4; i++)
/*       */       {
/*  5024 */         str = paramArrayOfString[i];
/*       */         
/*  5026 */         if ((str != null) && (str.length() > this.endToEndMaxLength[i]))
/*       */         {
/*       */ 
/*  5029 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 159, str);
/*  5030 */           localSQLException2.fillInStackTrace();
/*  5031 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */       
/*       */ 
/*  5036 */       if (this.endToEndValues != null)
/*       */       {
/*  5038 */         for (i = 0; i < 4; i++)
/*       */         {
/*  5040 */           str = paramArrayOfString[i];
/*       */           
/*  5042 */           if (((str == null) && (this.endToEndValues[i] != null)) || ((str != null) && (!str.equals(this.endToEndValues[i]))))
/*       */           {
/*       */ 
/*  5045 */             this.endToEndHasChanged[i] = true;
/*  5046 */             this.endToEndAnyChanged = true;
/*       */           }
/*       */         }
/*       */         
/*       */ 
/*  5051 */         this.endToEndHasChanged[0] |= this.endToEndHasChanged[3];
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  5056 */         for (i = 0; i < 4; i++)
/*       */         {
/*  5058 */           this.endToEndHasChanged[i] = true;
/*       */         }
/*       */         
/*  5061 */         this.endToEndAnyChanged = true;
/*       */       }
/*       */       
/*       */ 
/*  5065 */       this.endToEndValues = paramArrayOfString;
/*       */     }
/*       */     
/*  5068 */     this.endToEndECIDSequenceNumber = paramShort;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void updateSystemContext()
/*       */     throws SQLException
/*       */   {}
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void resetSystemContext() {}
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void updateSystemContext11()
/*       */     throws SQLException
/*       */   {}
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public boolean getIncludeSynonyms()
/*       */   {
/*  5127 */     return this.includeSynonyms;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setRestrictGetTables(boolean paramBoolean)
/*       */   {
/*  5168 */     this.restrictGettables = paramBoolean;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public boolean getRestrictGetTables()
/*       */   {
/*  5184 */     return this.restrictGettables;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setDefaultFixedString(boolean paramBoolean)
/*       */   {
/*  5213 */     this.fixedString = paramBoolean;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setDefaultNChar(boolean paramBoolean)
/*       */   {
/*  5222 */     this.defaultnchar = paramBoolean;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public boolean getDefaultFixedString()
/*       */   {
/*  5250 */     return this.fixedString;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int getNlsRatio()
/*       */   {
/*  5263 */     return 1;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public int getC2SNlsRatio()
/*       */   {
/*  5270 */     return 1;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   synchronized void addStatement(OracleStatement paramOracleStatement)
/*       */   {
/*  5279 */     if (paramOracleStatement.next != null) {
/*  5280 */       throw new Error("add_statement called twice on " + paramOracleStatement);
/*       */     }
/*  5282 */     paramOracleStatement.next = this.statements;
/*       */     
/*  5284 */     if (this.statements != null) {
/*  5285 */       this.statements.prev = paramOracleStatement;
/*       */     }
/*  5287 */     this.statements = paramOracleStatement;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   synchronized void removeStatement(OracleStatement paramOracleStatement)
/*       */   {
/*  5306 */     OracleStatement localOracleStatement1 = paramOracleStatement.prev;
/*  5307 */     OracleStatement localOracleStatement2 = paramOracleStatement.next;
/*       */     
/*  5309 */     if (localOracleStatement1 == null)
/*       */     {
/*  5311 */       if (this.statements != paramOracleStatement) {
/*  5312 */         return;
/*       */       }
/*  5314 */       this.statements = localOracleStatement2;
/*       */     }
/*       */     else {
/*  5317 */       localOracleStatement1.next = localOracleStatement2;
/*       */     }
/*  5319 */     if (localOracleStatement2 != null) {
/*  5320 */       localOracleStatement2.prev = localOracleStatement1;
/*       */     }
/*  5322 */     paramOracleStatement.next = null;
/*  5323 */     paramOracleStatement.prev = null;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   synchronized void closeStatements(boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  5345 */     Object localObject = this.statements;
/*       */     OracleStatement localOracleStatement;
/*  5347 */     while (localObject != null)
/*       */     {
/*  5349 */       localOracleStatement = ((OracleStatement)localObject).nextChild;
/*       */       
/*  5351 */       if (((OracleStatement)localObject).serverCursor)
/*       */       {
/*  5353 */         ((OracleStatement)localObject).close();
/*  5354 */         removeStatement((OracleStatement)localObject);
/*       */       }
/*       */       
/*  5357 */       localObject = localOracleStatement;
/*       */     }
/*       */     
/*       */ 
/*  5361 */     localObject = this.statements;
/*       */     
/*  5363 */     while (localObject != null)
/*       */     {
/*  5365 */       localOracleStatement = ((OracleStatement)localObject).next;
/*       */       
/*  5367 */       if (paramBoolean) {
/*  5368 */         ((OracleStatement)localObject).close();
/*       */       } else
/*  5370 */         ((OracleStatement)localObject).hardClose();
/*  5371 */       removeStatement((OracleStatement)localObject);
/*       */       
/*  5373 */       localObject = localOracleStatement;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   final void purgeStatementCache()
/*       */     throws SQLException
/*       */   {
/*  5384 */     if (isStatementCacheInitialized())
/*       */     {
/*  5386 */       this.statementCache.purgeImplicitCache();
/*  5387 */       this.statementCache.purgeExplicitCache();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   final void closeStatementCache()
/*       */     throws SQLException
/*       */   {
/*  5397 */     if (isStatementCacheInitialized())
/*       */     {
/*       */ 
/*       */ 
/*  5401 */       this.statementCache.close();
/*       */       
/*  5403 */       this.statementCache = null;
/*  5404 */       this.clearStatementMetaData = true;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void needLine()
/*       */     throws SQLException
/*       */   {
/*  5413 */     if (this.lifecycle != 1)
/*       */     {
/*  5415 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  5416 */       localSQLException.fillInStackTrace();
/*  5417 */       throw localSQLException;
/*       */     }
/*       */     
/*  5420 */     needLineUnchecked();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   synchronized void needLineUnchecked()
/*       */     throws SQLException
/*       */   {
/*  5430 */     if (this.statementHoldingLine != null)
/*       */     {
/*  5432 */       this.statementHoldingLine.freeLine();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   synchronized void holdLine(oracle.jdbc.internal.OracleStatement paramOracleStatement)
/*       */   {
/*  5440 */     holdLine((OracleStatement)paramOracleStatement);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   synchronized void holdLine(OracleStatement paramOracleStatement)
/*       */   {
/*  5449 */     this.statementHoldingLine = paramOracleStatement;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   synchronized void releaseLine()
/*       */   {
/*  5457 */     releaseLineForCancel();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void releaseLineForCancel()
/*       */   {
/*  5466 */     this.statementHoldingLine = null;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public synchronized void startup(String paramString, int paramInt)
/*       */     throws SQLException
/*       */   {
/*  5474 */     if (this.lifecycle != 1)
/*       */     {
/*  5476 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  5477 */       localSQLException.fillInStackTrace();
/*  5478 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*  5482 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  5483 */     localSQLException.fillInStackTrace();
/*  5484 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */   public synchronized void startup(OracleConnection.DatabaseStartupMode paramDatabaseStartupMode)
/*       */     throws SQLException
/*       */   {
/*       */     SQLException localSQLException;
/*  5492 */     if (this.lifecycle != 1)
/*       */     {
/*  5494 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  5495 */       localSQLException.fillInStackTrace();
/*  5496 */       throw localSQLException;
/*       */     }
/*  5498 */     if (paramDatabaseStartupMode == null)
/*       */     {
/*  5500 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  5501 */       localSQLException.fillInStackTrace();
/*  5502 */       throw localSQLException;
/*       */     }
/*       */     
/*  5505 */     needLine();
/*  5506 */     doStartup(paramDatabaseStartupMode.getMode());
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void doStartup(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  5515 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  5516 */     localSQLException.fillInStackTrace();
/*  5517 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */   public synchronized void shutdown(OracleConnection.DatabaseShutdownMode paramDatabaseShutdownMode)
/*       */     throws SQLException
/*       */   {
/*       */     SQLException localSQLException;
/*       */     
/*  5526 */     if (this.lifecycle != 1)
/*       */     {
/*  5528 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  5529 */       localSQLException.fillInStackTrace();
/*  5530 */       throw localSQLException;
/*       */     }
/*  5532 */     if (paramDatabaseShutdownMode == null)
/*       */     {
/*  5534 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  5535 */       localSQLException.fillInStackTrace();
/*  5536 */       throw localSQLException;
/*       */     }
/*       */     
/*  5539 */     needLine();
/*  5540 */     doShutdown(paramDatabaseShutdownMode.getMode());
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void doShutdown(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  5549 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  5550 */     localSQLException.fillInStackTrace();
/*  5551 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void archive(int paramInt1, int paramInt2, String paramString)
/*       */     throws SQLException
/*       */   {
/*  5562 */     if (this.lifecycle != 1)
/*       */     {
/*  5564 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  5565 */       localSQLException.fillInStackTrace();
/*  5566 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*  5570 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  5571 */     localSQLException.fillInStackTrace();
/*  5572 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void registerSQLType(String paramString1, String paramString2)
/*       */     throws SQLException
/*       */   {
/*  5586 */     if ((paramString1 == null) || (paramString2 == null))
/*       */     {
/*  5588 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  5589 */       localSQLException1.fillInStackTrace();
/*  5590 */       throw localSQLException1;
/*       */     }
/*       */     
/*       */     try
/*       */     {
/*  5595 */       registerSQLType(paramString1, Class.forName(paramString2));
/*       */ 
/*       */     }
/*       */     catch (ClassNotFoundException localClassNotFoundException)
/*       */     {
/*  5600 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Class not found: " + paramString2);
/*  5601 */       localSQLException2.fillInStackTrace();
/*  5602 */       throw localSQLException2;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void registerSQLType(String paramString, Class paramClass)
/*       */     throws SQLException
/*       */   {
/*  5613 */     if ((paramString == null) || (paramClass == null))
/*       */     {
/*  5615 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  5616 */       localSQLException.fillInStackTrace();
/*  5617 */       throw localSQLException;
/*       */     }
/*       */     
/*  5620 */     if (this.map == null) { this.map = new Hashtable(10);
/*       */     }
/*  5622 */     this.map.put(paramString, paramClass);
/*  5623 */     this.map.put(paramClass.getName(), paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public synchronized String getSQLType(Object paramObject)
/*       */     throws SQLException
/*       */   {
/*  5631 */     if ((paramObject != null) && (this.map != null))
/*       */     {
/*  5633 */       String str = paramObject.getClass().getName();
/*       */       
/*  5635 */       return (String)this.map.get(str);
/*       */     }
/*       */     
/*  5638 */     return null;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public synchronized Object getJavaObject(String paramString)
/*       */     throws SQLException
/*       */   {
/*  5646 */     Object localObject = null;
/*       */     
/*       */     try
/*       */     {
/*  5650 */       if ((paramString != null) && (this.map != null))
/*       */       {
/*  5652 */         Class localClass = (Class)this.map.get(paramString);
/*       */         
/*  5654 */         localObject = localClass.newInstance();
/*       */       }
/*       */     }
/*       */     catch (IllegalAccessException localIllegalAccessException)
/*       */     {
/*  5659 */       localIllegalAccessException.printStackTrace();
/*       */     }
/*       */     catch (InstantiationException localInstantiationException)
/*       */     {
/*  5663 */       localInstantiationException.printStackTrace();
/*       */     }
/*       */     
/*  5666 */     return localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void putDescriptor(String paramString, Object paramObject)
/*       */     throws SQLException
/*       */   {
/*  5679 */     if ((paramString != null) && (paramObject != null))
/*       */     {
/*  5681 */       if (this.descriptorCacheStack[this.dci] == null) {
/*  5682 */         this.descriptorCacheStack[this.dci] = new Hashtable(10);
/*       */       }
/*  5684 */       ((TypeDescriptor)paramObject).fixupConnection(this);
/*  5685 */       this.descriptorCacheStack[this.dci].put(paramString, paramObject);
/*       */     }
/*       */     else
/*       */     {
/*  5689 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  5690 */       localSQLException.fillInStackTrace();
/*  5691 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized Object getDescriptor(String paramString)
/*       */   {
/*  5703 */     Object localObject = null;
/*       */     
/*  5705 */     if (paramString != null) {
/*  5706 */       if (this.descriptorCacheStack[this.dci] != null)
/*  5707 */         localObject = this.descriptorCacheStack[this.dci].get(paramString);
/*  5708 */       if ((localObject == null) && (this.dci == 1) && (this.descriptorCacheStack[0] != null)) {
/*  5709 */         localObject = this.descriptorCacheStack[0].get(paramString);
/*       */       }
/*       */     }
/*  5712 */     return localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   /**
/*       */    * @deprecated
/*       */    */
/*       */   public synchronized void removeDecriptor(String paramString)
/*       */   {
/*  5724 */     removeDescriptor(paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void removeDescriptor(String paramString)
/*       */   {
/*  5738 */     if ((paramString != null) && (this.descriptorCacheStack[this.dci] != null))
/*  5739 */       this.descriptorCacheStack[this.dci].remove(paramString);
/*  5740 */     if ((paramString != null) && (this.dci == 1) && (this.descriptorCacheStack[0] != null)) {
/*  5741 */       this.descriptorCacheStack[0].remove(paramString);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void removeAllDescriptor()
/*       */   {
/*  5752 */     for (int i = 0; i <= this.dci; i++) {
/*  5753 */       if (this.descriptorCacheStack[i] != null) {
/*  5754 */         this.descriptorCacheStack[i].clear();
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int numberOfDescriptorCacheEntries()
/*       */   {
/*  5771 */     int i = 0;
/*  5772 */     for (int j = 0; j <= this.dci; j++) {
/*  5773 */       if (this.descriptorCacheStack[j] != null)
/*  5774 */         i += this.descriptorCacheStack[j].size();
/*       */     }
/*  5776 */     return i;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public Enumeration descriptorCacheKeys()
/*       */   {
/*  5789 */     if (this.dci == 0) {
/*  5790 */       if (this.descriptorCacheStack[this.dci] != null) {
/*  5791 */         return this.descriptorCacheStack[this.dci].keys();
/*       */       }
/*  5793 */       return null;
/*       */     }
/*  5795 */     if ((this.descriptorCacheStack[0] == null) && (this.descriptorCacheStack[1] != null))
/*  5796 */       return this.descriptorCacheStack[1].keys();
/*  5797 */     if ((this.descriptorCacheStack[1] == null) && (this.descriptorCacheStack[0] != null))
/*  5798 */       return this.descriptorCacheStack[0].keys();
/*  5799 */     if ((this.descriptorCacheStack[0] == null) && (this.descriptorCacheStack[1] == null)) {
/*  5800 */       return null;
/*       */     }
/*  5802 */     Vector localVector = new Vector(this.descriptorCacheStack[1].keySet());
/*  5803 */     localVector.addAll(this.descriptorCacheStack[0].keySet());
/*  5804 */     return localVector.elements();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void putDescriptor(byte[] paramArrayOfByte, Object paramObject)
/*       */     throws SQLException
/*       */   {
/*  5818 */     if ((paramArrayOfByte != null) && (paramObject != null))
/*       */     {
/*  5820 */       if (this.descriptorCacheStack[this.dci] == null) {
/*  5821 */         this.descriptorCacheStack[this.dci] = new Hashtable(10);
/*       */       }
/*  5823 */       this.descriptorCacheStack[this.dci].put(new ByteArrayKey(paramArrayOfByte), paramObject);
/*       */     }
/*       */     else
/*       */     {
/*  5827 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  5828 */       localSQLException.fillInStackTrace();
/*  5829 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized Object getDescriptor(byte[] paramArrayOfByte)
/*       */   {
/*  5841 */     Object localObject = null;
/*       */     
/*  5843 */     if (paramArrayOfByte != null) {
/*  5844 */       ByteArrayKey localByteArrayKey = new ByteArrayKey(paramArrayOfByte);
/*  5845 */       if (this.descriptorCacheStack[this.dci] != null)
/*  5846 */         localObject = this.descriptorCacheStack[this.dci].get(localByteArrayKey);
/*  5847 */       if ((localObject == null) && (this.dci == 1) && (this.descriptorCacheStack[0] != null)) {
/*  5848 */         localObject = this.descriptorCacheStack[0].get(localByteArrayKey);
/*       */       }
/*       */     }
/*  5851 */     return localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void removeDecriptor(byte[] paramArrayOfByte)
/*       */   {
/*  5863 */     if (paramArrayOfByte != null) {
/*  5864 */       ByteArrayKey localByteArrayKey = new ByteArrayKey(paramArrayOfByte);
/*  5865 */       if (this.descriptorCacheStack[this.dci] != null)
/*  5866 */         this.descriptorCacheStack[this.dci].remove(localByteArrayKey);
/*  5867 */       if ((this.dci == 1) && (this.descriptorCacheStack[0] != null)) {
/*  5868 */         this.descriptorCacheStack[0].remove(localByteArrayKey);
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public short getJdbcCsId()
/*       */     throws SQLException
/*       */   {
/*  5891 */     if (this.conversion == null)
/*       */     {
/*       */ 
/*  5894 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 65);
/*  5895 */       localSQLException.fillInStackTrace();
/*  5896 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*  5900 */     return this.conversion.getClientCharSet();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public short getDbCsId()
/*       */     throws SQLException
/*       */   {
/*  5915 */     if (this.conversion == null)
/*       */     {
/*       */ 
/*  5918 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 65);
/*  5919 */       localSQLException.fillInStackTrace();
/*  5920 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*  5924 */     return this.conversion.getServerCharSetId();
/*       */   }
/*       */   
/*       */ 
/*       */   public short getNCsId()
/*       */     throws SQLException
/*       */   {
/*  5931 */     if (this.conversion == null)
/*       */     {
/*       */ 
/*  5934 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 65);
/*  5935 */       localSQLException.fillInStackTrace();
/*  5936 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*  5940 */     return this.conversion.getNCharSetId();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public short getStructAttrCsId()
/*       */     throws SQLException
/*       */   {
/*  5956 */     return getDbCsId();
/*       */   }
/*       */   
/*       */ 
/*       */   public short getStructAttrNCsId()
/*       */     throws SQLException
/*       */   {
/*  5963 */     return getNCsId();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized Map getTypeMap()
/*       */   {
/*  5973 */     if (this.map == null) this.map = new Hashtable(10);
/*  5974 */     return this.map;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public synchronized void setTypeMap(Map paramMap)
/*       */   {
/*  5981 */     this.map = paramMap;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setUsingXAFlag(boolean paramBoolean)
/*       */   {
/*  5989 */     this.usingXA = paramBoolean;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public synchronized boolean getUsingXAFlag()
/*       */   {
/*  5996 */     return this.usingXA;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setXAErrorFlag(boolean paramBoolean)
/*       */   {
/*  6004 */     this.xaWantsError = paramBoolean;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public synchronized boolean getXAErrorFlag()
/*       */   {
/*  6011 */     return this.xaWantsError;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   String getPropertyFromDatabase(String paramString)
/*       */     throws SQLException
/*       */   {
/*  6019 */     String str = null;
/*  6020 */     Statement localStatement = null;
/*  6021 */     ResultSet localResultSet = null;
/*       */     try
/*       */     {
/*  6024 */       localStatement = createStatement();
/*  6025 */       localStatement.setFetchSize(1);
/*  6026 */       localResultSet = localStatement.executeQuery(paramString);
/*  6027 */       if (localResultSet.next()) {
/*  6028 */         str = localResultSet.getString(1);
/*       */       }
/*       */     }
/*       */     finally {
/*  6032 */       if (localResultSet != null)
/*  6033 */         localResultSet.close();
/*  6034 */       if (localStatement != null)
/*  6035 */         localStatement.close();
/*       */     }
/*  6037 */     return str;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public synchronized String getUserName()
/*       */     throws SQLException
/*       */   {
/*  6045 */     if (this.userName == null) {
/*  6046 */       this.userName = getPropertyFromDatabase("SELECT USER FROM DUAL");
/*       */     }
/*  6048 */     return this.userName;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public String getCurrentSchema()
/*       */     throws SQLException
/*       */   {
/*  6063 */     return getPropertyFromDatabase("SELECT SYS_CONTEXT('USERENV', 'CURRENT_SCHEMA') FROM DUAL");
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public String getDefaultSchemaNameForNamedTypes()
/*       */     throws SQLException
/*       */   {
/*  6081 */     String str = null;
/*       */     
/*  6083 */     if (this.createDescriptorUseCurrentSchemaForSchemaName) {
/*  6084 */       str = getCurrentSchema();
/*       */     } else {
/*  6086 */       str = getUserName();
/*       */     }
/*  6088 */     return str;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setStartTime(long paramLong)
/*       */     throws SQLException
/*       */   {
/*  6106 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 152);
/*  6107 */     localSQLException.fillInStackTrace();
/*  6108 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized long getStartTime()
/*       */     throws SQLException
/*       */   {
/*  6124 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 152);
/*  6125 */     localSQLException.fillInStackTrace();
/*  6126 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void registerHeartbeat()
/*       */     throws SQLException
/*       */   {
/*  6139 */     if (this.logicalConnectionAttached != null) {
/*  6140 */       this.logicalConnectionAttached.registerHeartbeat();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int getHeartbeatNoChangeCount()
/*       */     throws SQLException
/*       */   {
/*  6152 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 152);
/*  6153 */     localSQLException.fillInStackTrace();
/*  6154 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized byte[] getFDO(boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  6166 */     if ((this.fdo == null) && (paramBoolean))
/*       */     {
/*  6168 */       CallableStatement localCallableStatement = null;
/*       */       
/*       */       try
/*       */       {
/*  6172 */         localCallableStatement = prepareCall("begin :1 := dbms_pickler.get_format (:2); end;");
/*       */         
/*       */ 
/*  6175 */         localCallableStatement.registerOutParameter(1, 2);
/*  6176 */         localCallableStatement.registerOutParameter(2, -4);
/*  6177 */         localCallableStatement.execute();
/*       */         
/*  6179 */         this.fdo = localCallableStatement.getBytes(2);
/*       */       }
/*       */       finally
/*       */       {
/*  6183 */         if (localCallableStatement != null) {
/*  6184 */           localCallableStatement.close();
/*       */         }
/*  6186 */         localCallableStatement = null;
/*       */       }
/*       */     }
/*       */     
/*  6190 */     return this.fdo;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setFDO(byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  6201 */     this.fdo = paramArrayOfByte;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized boolean getBigEndian()
/*       */     throws SQLException
/*       */   {
/*  6212 */     if (this.bigEndian == null)
/*       */     {
/*  6214 */       int[] arrayOfInt = oracle.jdbc.oracore.Util.toJavaUnsignedBytes(getFDO(true));
/*       */       
/*       */ 
/*       */ 
/*  6218 */       int i = arrayOfInt[(6 + arrayOfInt[5] + arrayOfInt[6] + 5)];
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*  6223 */       int j = (byte)(i & 0x10);
/*       */       
/*  6225 */       if (j < 0) {
/*  6226 */         j += 256;
/*       */       }
/*  6228 */       if (j > 0) {
/*  6229 */         this.bigEndian = Boolean.TRUE;
/*       */       } else {
/*  6231 */         this.bigEndian = Boolean.FALSE;
/*       */       }
/*       */     }
/*  6234 */     return this.bigEndian.booleanValue();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setHoldability(int paramInt)
/*       */     throws SQLException
/*       */   {}
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int getHoldability()
/*       */     throws SQLException
/*       */   {
/*  6287 */     return 1;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized Savepoint setSavepoint()
/*       */     throws SQLException
/*       */   {
/*  6296 */     return oracleSetSavepoint();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized Savepoint setSavepoint(String paramString)
/*       */     throws SQLException
/*       */   {
/*  6306 */     return oracleSetSavepoint(paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void rollback(Savepoint paramSavepoint)
/*       */     throws SQLException
/*       */   {
/*  6317 */     disallowGlobalTxnMode(122);
/*       */     
/*       */ 
/*  6320 */     if (this.autocommit)
/*       */     {
/*  6322 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 121);
/*  6323 */       ((SQLException)localObject).fillInStackTrace();
/*  6324 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6329 */     if (this.savepointStatement == null)
/*       */     {
/*  6331 */       this.savepointStatement = createStatement();
/*       */     }
/*       */     
/*  6334 */     Object localObject = null;
/*       */     
/*       */     try
/*       */     {
/*  6338 */       localObject = paramSavepoint.getSavepointName();
/*       */     }
/*       */     catch (SQLException localSQLException)
/*       */     {
/*  6342 */       localObject = "ORACLE_SVPT_" + paramSavepoint.getSavepointId();
/*       */     }
/*       */     
/*  6345 */     this.savepointStatement.executeUpdate("ROLLBACK TO " + (String)localObject);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void releaseSavepoint(Savepoint paramSavepoint)
/*       */     throws SQLException
/*       */   {
/*  6355 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  6356 */     localSQLException.fillInStackTrace();
/*  6357 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   static final String INSTANCE_NAME = "INSTANCE_NAME";
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   static final String SERVICE_NAME = "SERVICE_NAME";
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public Statement createStatement(int paramInt1, int paramInt2, int paramInt3)
/*       */     throws SQLException
/*       */   {
/*  6403 */     return createStatement(paramInt1, paramInt2);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   Hashtable clientData;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private BufferCacheStore connectionBufferCacheStore;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private static ThreadLocal<BufferCacheStore> threadLocalBufferCacheStore;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private int pingResult;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public PreparedStatement prepareStatement(String paramString, int paramInt1, int paramInt2, int paramInt3)
/*       */     throws SQLException
/*       */   {
/*  6454 */     return prepareStatement(paramString, paramInt1, paramInt2);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public CallableStatement prepareCall(String paramString, int paramInt1, int paramInt2, int paramInt3)
/*       */     throws SQLException
/*       */   {
/*  6502 */     return prepareCall(paramString, paramInt1, paramInt2);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public PreparedStatement prepareStatement(String paramString, int paramInt)
/*       */     throws SQLException
/*       */   {
/*  6552 */     AutoKeyInfo localAutoKeyInfo = new AutoKeyInfo(paramString);
/*  6553 */     if ((paramInt == 2) || (!localAutoKeyInfo.isInsertSqlStmt()))
/*       */     {
/*  6555 */       return prepareStatement(paramString);
/*       */     }
/*  6557 */     if (paramInt != 1)
/*       */     {
/*  6559 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  6560 */       ((SQLException)localObject).fillInStackTrace();
/*  6561 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*  6564 */     Object localObject = localAutoKeyInfo.getNewSql();
/*  6565 */     oracle.jdbc.OraclePreparedStatement localOraclePreparedStatement = (oracle.jdbc.OraclePreparedStatement)prepareStatement((String)localObject);
/*  6566 */     OraclePreparedStatement localOraclePreparedStatement1 = (OraclePreparedStatement)((OraclePreparedStatementWrapper)localOraclePreparedStatement).preparedStatement;
/*       */     
/*  6568 */     localOraclePreparedStatement1.isAutoGeneratedKey = true;
/*  6569 */     localOraclePreparedStatement1.autoKeyInfo = localAutoKeyInfo;
/*  6570 */     localOraclePreparedStatement1.registerReturnParamsForAutoKey();
/*  6571 */     return localOraclePreparedStatement;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public PreparedStatement prepareStatement(String paramString, int[] paramArrayOfInt)
/*       */     throws SQLException
/*       */   {
/*  6620 */     AutoKeyInfo localAutoKeyInfo = new AutoKeyInfo(paramString, paramArrayOfInt);
/*       */     
/*  6622 */     if (!localAutoKeyInfo.isInsertSqlStmt()) { return prepareStatement(paramString);
/*       */     }
/*  6624 */     if ((paramArrayOfInt == null) || (paramArrayOfInt.length == 0))
/*       */     {
/*  6626 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  6627 */       ((SQLException)localObject).fillInStackTrace();
/*  6628 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6633 */     doDescribeTable(localAutoKeyInfo);
/*       */     
/*  6635 */     Object localObject = localAutoKeyInfo.getNewSql();
/*  6636 */     oracle.jdbc.OraclePreparedStatement localOraclePreparedStatement = (oracle.jdbc.OraclePreparedStatement)prepareStatement((String)localObject);
/*  6637 */     OraclePreparedStatement localOraclePreparedStatement1 = (OraclePreparedStatement)((OraclePreparedStatementWrapper)localOraclePreparedStatement).preparedStatement;
/*       */     
/*  6639 */     localOraclePreparedStatement1.isAutoGeneratedKey = true;
/*  6640 */     localOraclePreparedStatement1.autoKeyInfo = localAutoKeyInfo;
/*  6641 */     localOraclePreparedStatement1.registerReturnParamsForAutoKey();
/*  6642 */     return localOraclePreparedStatement;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public PreparedStatement prepareStatement(String paramString, String[] paramArrayOfString)
/*       */     throws SQLException
/*       */   {
/*  6692 */     AutoKeyInfo localAutoKeyInfo = new AutoKeyInfo(paramString, paramArrayOfString);
/*  6693 */     if (!localAutoKeyInfo.isInsertSqlStmt()) { return prepareStatement(paramString);
/*       */     }
/*  6695 */     if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
/*       */     {
/*  6697 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  6698 */       ((SQLException)localObject).fillInStackTrace();
/*  6699 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*  6703 */     doDescribeTable(localAutoKeyInfo);
/*       */     
/*  6705 */     Object localObject = localAutoKeyInfo.getNewSql();
/*  6706 */     oracle.jdbc.OraclePreparedStatement localOraclePreparedStatement = (oracle.jdbc.OraclePreparedStatement)prepareStatement((String)localObject);
/*  6707 */     OraclePreparedStatement localOraclePreparedStatement1 = (OraclePreparedStatement)((OraclePreparedStatementWrapper)localOraclePreparedStatement).preparedStatement;
/*       */     
/*  6709 */     localOraclePreparedStatement1.isAutoGeneratedKey = true;
/*  6710 */     localOraclePreparedStatement1.autoKeyInfo = localAutoKeyInfo;
/*  6711 */     localOraclePreparedStatement1.registerReturnParamsForAutoKey();
/*  6712 */     return localOraclePreparedStatement;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized oracle.jdbc.OracleSavepoint oracleSetSavepoint()
/*       */     throws SQLException
/*       */   {
/*  6738 */     disallowGlobalTxnMode(117);
/*       */     
/*       */ 
/*  6741 */     if (this.autocommit)
/*       */     {
/*  6743 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 120);
/*  6744 */       ((SQLException)localObject).fillInStackTrace();
/*  6745 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6750 */     if (this.savepointStatement == null)
/*       */     {
/*  6752 */       this.savepointStatement = createStatement();
/*       */     }
/*       */     
/*  6755 */     Object localObject = new OracleSavepoint();
/*       */     
/*  6757 */     String str = "SAVEPOINT ORACLE_SVPT_" + ((OracleSavepoint)localObject).getSavepointId();
/*       */     
/*  6759 */     this.savepointStatement.executeUpdate(str);
/*       */     
/*       */ 
/*  6762 */     return (oracle.jdbc.OracleSavepoint)localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized oracle.jdbc.OracleSavepoint oracleSetSavepoint(String paramString)
/*       */     throws SQLException
/*       */   {
/*  6789 */     disallowGlobalTxnMode(117);
/*       */     
/*       */ 
/*  6792 */     if (this.autocommit)
/*       */     {
/*  6794 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 120);
/*  6795 */       ((SQLException)localObject).fillInStackTrace();
/*  6796 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6801 */     if (this.savepointStatement == null)
/*       */     {
/*  6803 */       this.savepointStatement = createStatement();
/*       */     }
/*       */     
/*  6806 */     Object localObject = new OracleSavepoint(paramString);
/*       */     
/*  6808 */     String str = "SAVEPOINT " + ((OracleSavepoint)localObject).getSavepointName();
/*       */     
/*  6810 */     this.savepointStatement.executeUpdate(str);
/*       */     
/*       */ 
/*  6813 */     return (oracle.jdbc.OracleSavepoint)localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void oracleRollback(oracle.jdbc.OracleSavepoint paramOracleSavepoint)
/*       */     throws SQLException
/*       */   {
/*  6841 */     disallowGlobalTxnMode(115);
/*       */     
/*       */ 
/*  6844 */     if (this.autocommit)
/*       */     {
/*  6846 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 121);
/*  6847 */       ((SQLException)localObject).fillInStackTrace();
/*  6848 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6853 */     if (this.savepointStatement == null)
/*       */     {
/*  6855 */       this.savepointStatement = createStatement();
/*       */     }
/*       */     
/*  6858 */     Object localObject = null;
/*       */     
/*       */     try
/*       */     {
/*  6862 */       localObject = paramOracleSavepoint.getSavepointName();
/*       */     }
/*       */     catch (SQLException localSQLException)
/*       */     {
/*  6866 */       localObject = "ORACLE_SVPT_" + paramOracleSavepoint.getSavepointId();
/*       */     }
/*       */     
/*  6869 */     this.savepointStatement.executeUpdate("ROLLBACK TO " + (String)localObject);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void oracleReleaseSavepoint(oracle.jdbc.OracleSavepoint paramOracleSavepoint)
/*       */     throws SQLException
/*       */   {
/*  6897 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  6898 */     localSQLException.fillInStackTrace();
/*  6899 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void disallowGlobalTxnMode(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  6920 */     if (this.txnMode == 1)
/*       */     {
/*  6922 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), paramInt);
/*  6923 */       localSQLException.fillInStackTrace();
/*  6924 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTxnMode(int paramInt)
/*       */   {
/*  6938 */     this.txnMode = paramInt;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public int getTxnMode()
/*       */   {
/*  6945 */     return this.txnMode;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized Object getClientData(Object paramObject)
/*       */   {
/*  6974 */     if (this.clientData == null)
/*       */     {
/*  6976 */       return null;
/*       */     }
/*       */     
/*  6979 */     return this.clientData.get(paramObject);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized Object setClientData(Object paramObject1, Object paramObject2)
/*       */   {
/*  7006 */     if (this.clientData == null)
/*       */     {
/*  7008 */       this.clientData = new Hashtable();
/*       */     }
/*       */     
/*  7011 */     return this.clientData.put(paramObject1, paramObject2);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized Object removeClientData(Object paramObject)
/*       */   {
/*  7032 */     if (this.clientData == null)
/*       */     {
/*  7034 */       return null;
/*       */     }
/*       */     
/*  7037 */     return this.clientData.remove(paramObject);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public oracle.sql.BlobDBAccess createBlobDBAccess()
/*       */     throws SQLException
/*       */   {
/*  7045 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  7046 */     localSQLException.fillInStackTrace();
/*  7047 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public oracle.sql.ClobDBAccess createClobDBAccess()
/*       */     throws SQLException
/*       */   {
/*  7056 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  7057 */     localSQLException.fillInStackTrace();
/*  7058 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public oracle.sql.BfileDBAccess createBfileDBAccess()
/*       */     throws SQLException
/*       */   {
/*  7067 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  7068 */     localSQLException.fillInStackTrace();
/*  7069 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void printState()
/*       */   {
/*       */     try
/*       */     {
/*  7095 */       int i = getJdbcCsId();
/*       */       
/*       */ 
/*  7098 */       int j = getDbCsId();
/*       */       
/*       */ 
/*  7101 */       int k = getStructAttrCsId();
/*       */ 
/*       */     }
/*       */     catch (SQLException localSQLException)
/*       */     {
/*  7106 */       localSQLException.printStackTrace();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public String getProtocolType()
/*       */   {
/*  7118 */     return this.protocol;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public String getURL()
/*       */   {
/*  7129 */     return this.url;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   /**
/*       */    * @deprecated
/*       */    */
/*       */   public synchronized void setStmtCacheSize(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  7143 */     setStatementCacheSize(paramInt);
/*  7144 */     setImplicitCachingEnabled(true);
/*  7145 */     setExplicitCachingEnabled(true);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   /**
/*       */    * @deprecated
/*       */    */
/*       */   public synchronized void setStmtCacheSize(int paramInt, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  7160 */     setStatementCacheSize(paramInt);
/*  7161 */     setImplicitCachingEnabled(true);
/*       */     
/*       */ 
/*  7164 */     setExplicitCachingEnabled(true);
/*       */     
/*  7166 */     this.clearStatementMetaData = paramBoolean;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   /**
/*       */    * @deprecated
/*       */    */
/*       */   public synchronized int getStmtCacheSize()
/*       */   {
/*  7180 */     int i = 0;
/*       */     
/*       */     try
/*       */     {
/*  7184 */       i = getStatementCacheSize();
/*       */     }
/*       */     catch (SQLException localSQLException) {}
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  7192 */     if (i == -1)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*  7197 */       i = 0;
/*       */     }
/*       */     
/*  7200 */     return i;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setStatementCacheSize(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  7221 */     if (this.statementCache == null)
/*       */     {
/*  7223 */       this.statementCache = new LRUStatementCache(paramInt);
/*       */ 
/*       */ 
/*       */     }
/*       */     else
/*       */     {
/*       */ 
/*  7230 */       this.statementCache.resize(paramInt);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized int getStatementCacheSize()
/*       */     throws SQLException
/*       */   {
/*  7249 */     if (this.statementCache == null) {
/*  7250 */       return -1;
/*       */     }
/*  7252 */     return this.statementCache.getCacheSize();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setImplicitCachingEnabled(boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  7275 */     if (this.statementCache == null)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*  7280 */       this.statementCache = new LRUStatementCache(0);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  7287 */     this.statementCache.setImplicitCachingEnabled(paramBoolean);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized boolean getImplicitCachingEnabled()
/*       */     throws SQLException
/*       */   {
/*  7306 */     if (this.statementCache == null) {
/*  7307 */       return false;
/*       */     }
/*  7309 */     return this.statementCache.getImplicitCachingEnabled();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setExplicitCachingEnabled(boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  7332 */     if (this.statementCache == null)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*  7337 */       this.statementCache = new LRUStatementCache(0);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  7344 */     this.statementCache.setExplicitCachingEnabled(paramBoolean);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized boolean getExplicitCachingEnabled()
/*       */     throws SQLException
/*       */   {
/*  7363 */     if (this.statementCache == null) {
/*  7364 */       return false;
/*       */     }
/*  7366 */     return this.statementCache.getExplicitCachingEnabled();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void purgeImplicitCache()
/*       */     throws SQLException
/*       */   {
/*  7385 */     if (this.statementCache != null) {
/*  7386 */       this.statementCache.purgeImplicitCache();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void purgeExplicitCache()
/*       */     throws SQLException
/*       */   {
/*  7405 */     if (this.statementCache != null) {
/*  7406 */       this.statementCache.purgeExplicitCache();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized PreparedStatement getStatementWithKey(String paramString)
/*       */     throws SQLException
/*       */   {
/*  7428 */     if (this.statementCache != null)
/*       */     {
/*  7430 */       OracleStatement localOracleStatement = this.statementCache.searchExplicitCache(paramString);
/*       */       
/*       */ 
/*  7433 */       if ((localOracleStatement == null) || (localOracleStatement.statementType == 1)) {
/*  7434 */         return (PreparedStatement)localOracleStatement;
/*       */       }
/*       */       
/*       */ 
/*  7438 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 125);
/*  7439 */       localSQLException.fillInStackTrace();
/*  7440 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  7445 */     return null;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized CallableStatement getCallWithKey(String paramString)
/*       */     throws SQLException
/*       */   {
/*  7467 */     if (this.statementCache != null)
/*       */     {
/*  7469 */       OracleStatement localOracleStatement = this.statementCache.searchExplicitCache(paramString);
/*       */       
/*       */ 
/*  7472 */       if ((localOracleStatement == null) || (localOracleStatement.statementType == 2)) {
/*  7473 */         return (CallableStatement)localOracleStatement;
/*       */       }
/*       */       
/*       */ 
/*  7477 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 125);
/*  7478 */       localSQLException.fillInStackTrace();
/*  7479 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  7484 */     return null;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void cacheImplicitStatement(OraclePreparedStatement paramOraclePreparedStatement, String paramString, int paramInt1, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  7500 */     if (this.statementCache == null)
/*       */     {
/*  7502 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 95);
/*  7503 */       localSQLException.fillInStackTrace();
/*  7504 */       throw localSQLException;
/*       */     }
/*       */     
/*  7507 */     this.statementCache.addToImplicitCache(paramOraclePreparedStatement, paramString, paramInt1, paramInt2);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void cacheExplicitStatement(OraclePreparedStatement paramOraclePreparedStatement, String paramString)
/*       */     throws SQLException
/*       */   {
/*  7524 */     if (this.statementCache == null)
/*       */     {
/*  7526 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 95);
/*  7527 */       localSQLException.fillInStackTrace();
/*  7528 */       throw localSQLException;
/*       */     }
/*       */     
/*  7531 */     this.statementCache.addToExplicitCache(paramOraclePreparedStatement, paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized boolean isStatementCacheInitialized()
/*       */   {
/*  7546 */     if (this.statementCache == null)
/*  7547 */       return false;
/*  7548 */     if (this.statementCache.getCacheSize() == 0) {
/*  7549 */       return false;
/*       */     }
/*  7551 */     return true;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private static final class BufferCacheStore
/*       */   {
/*  7587 */     static int MAX_CACHED_BUFFER_SIZE = Integer.MAX_VALUE;
/*       */     final BufferCache<byte[]> byteBufferCache;
/*       */     final BufferCache<char[]> charBufferCache;
/*       */     
/*       */     BufferCacheStore() {
/*  7592 */       this(MAX_CACHED_BUFFER_SIZE);
/*       */     }
/*       */     
/*       */     BufferCacheStore(int paramInt) {
/*  7596 */       this.byteBufferCache = new BufferCache(paramInt);
/*  7597 */       this.charBufferCache = new BufferCache(paramInt);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private BufferCacheStore getBufferCacheStore()
/*       */   {
/*  7608 */     if (this.useThreadLocalBufferCache) {
/*  7609 */       if (threadLocalBufferCacheStore == null)
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  7622 */         BufferCacheStore.MAX_CACHED_BUFFER_SIZE = this.maxCachedBufferSize;
/*  7623 */         threadLocalBufferCacheStore = new ThreadLocal()
/*       */         {
/*       */           protected PhysicalConnection.BufferCacheStore initialValue()
/*       */           {
/*  7627 */             return new PhysicalConnection.BufferCacheStore();
/*       */           }
/*       */         };
/*       */       }
/*  7631 */       return (BufferCacheStore)threadLocalBufferCacheStore.get();
/*       */     }
/*       */     
/*  7634 */     if (this.connectionBufferCacheStore == null)
/*       */     {
/*  7636 */       this.connectionBufferCacheStore = new BufferCacheStore(this.maxCachedBufferSize);
/*       */     }
/*  7638 */     return this.connectionBufferCacheStore;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void cacheBuffer(byte[] paramArrayOfByte)
/*       */   {
/*  7649 */     if (paramArrayOfByte != null) {
/*  7650 */       BufferCacheStore localBufferCacheStore = getBufferCacheStore();
/*  7651 */       localBufferCacheStore.byteBufferCache.put(paramArrayOfByte);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void cacheBuffer(char[] paramArrayOfChar)
/*       */   {
/*  7663 */     if (paramArrayOfChar != null) {
/*  7664 */       BufferCacheStore localBufferCacheStore = getBufferCacheStore();
/*  7665 */       localBufferCacheStore.charBufferCache.put(paramArrayOfChar);
/*       */     }
/*       */   }
/*       */   
/*       */   public void cacheBufferSync(char[] paramArrayOfChar)
/*       */   {
/*  7671 */     synchronized (this) {
/*  7672 */       cacheBuffer(paramArrayOfChar);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   byte[] getByteBuffer(int paramInt)
/*       */   {
/*  7684 */     BufferCacheStore localBufferCacheStore = getBufferCacheStore();
/*  7685 */     return (byte[])localBufferCacheStore.byteBufferCache.get(Byte.TYPE, paramInt);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   char[] getCharBuffer(int paramInt)
/*       */   {
/*  7697 */     BufferCacheStore localBufferCacheStore = getBufferCacheStore();
/*  7698 */     return (char[])localBufferCacheStore.charBufferCache.get(Character.TYPE, paramInt);
/*       */   }
/*       */   
/*       */   /* Error */
/*       */   public char[] getCharBufferSync(int paramInt)
/*       */   {
/*       */     // Byte code:
/*       */     //   0: aload_0
/*       */     //   1: dup
/*       */     //   2: astore_2
/*       */     //   3: monitorenter
/*       */     //   4: aload_0
/*       */     //   5: iload_1
/*       */     //   6: invokevirtual 750	oracle/jdbc/driver/PhysicalConnection:getCharBuffer	(I)[C
/*       */     //   9: aload_2
/*       */     //   10: monitorexit
/*       */     //   11: areturn
/*       */     //   12: astore_3
/*       */     //   13: aload_2
/*       */     //   14: monitorexit
/*       */     //   15: aload_3
/*       */     //   16: athrow
/*       */     // Line number table:
/*       */     //   Java source line #7703	-> byte code offset #0
/*       */     //   Java source line #7704	-> byte code offset #4
/*       */     //   Java source line #7705	-> byte code offset #12
/*       */     // Local variable table:
/*       */     //   start	length	slot	name	signature
/*       */     //   0	17	0	this	PhysicalConnection
/*       */     //   0	17	1	paramInt	int
/*       */     //   2	12	2	Ljava/lang/Object;	Object
/*       */     //   12	4	3	localObject1	Object
/*       */     // Exception table:
/*       */     //   from	to	target	type
/*       */     //   4	11	12	finally
/*       */     //   12	15	12	finally
/*       */   }
/*       */   
/*       */   public OracleConnection.BufferCacheStatistics getByteBufferCacheStatistics()
/*       */   {
/*  7715 */     BufferCacheStore localBufferCacheStore = getBufferCacheStore();
/*  7716 */     return localBufferCacheStore.byteBufferCache.getStatistics();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public OracleConnection.BufferCacheStatistics getCharBufferCacheStatistics()
/*       */   {
/*  7727 */     BufferCacheStore localBufferCacheStore = getBufferCacheStore();
/*  7728 */     return localBufferCacheStore.charBufferCache.getStatistics();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void registerTAFCallback(OracleOCIFailover paramOracleOCIFailover, Object paramObject)
/*       */     throws SQLException
/*       */   {
/*  7751 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  7752 */     localSQLException.fillInStackTrace();
/*  7753 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public String getDatabaseProductVersion()
/*       */     throws SQLException
/*       */   {
/*  7766 */     if (this.databaseProductVersion == "")
/*       */     {
/*  7768 */       needLine();
/*       */       
/*  7770 */       this.databaseProductVersion = doGetDatabaseProductVersion();
/*       */     }
/*       */     
/*  7773 */     return this.databaseProductVersion;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public synchronized boolean getReportRemarks()
/*       */   {
/*  7780 */     return this.reportRemarks;
/*       */   }
/*       */   
/*       */ 
/*       */   public short getVersionNumber()
/*       */     throws SQLException
/*       */   {
/*  7787 */     if (this.versionNumber == -1)
/*       */     {
/*  7789 */       synchronized (this)
/*       */       {
/*  7791 */         if (this.versionNumber == -1)
/*       */         {
/*  7793 */           needLine();
/*       */           
/*  7795 */           this.versionNumber = doGetVersionNumber();
/*       */         }
/*       */       }
/*       */     }
/*       */     
/*  7800 */     return this.versionNumber;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void registerCloseCallback(OracleCloseCallback paramOracleCloseCallback, Object paramObject)
/*       */   {
/*  7808 */     this.closeCallback = paramOracleCloseCallback;
/*  7809 */     this.privateData = paramObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCreateStatementAsRefCursor(boolean paramBoolean) {}
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public boolean getCreateStatementAsRefCursor()
/*       */   {
/*  7857 */     return false;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int pingDatabase()
/*       */     throws SQLException
/*       */   {
/*  7868 */     if (this.lifecycle != 1)
/*  7869 */       return -1;
/*  7870 */     return doPingDatabase();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   /**
/*       */    * @deprecated
/*       */    */
/*       */   public int pingDatabase(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  7883 */     if (this.lifecycle != 1)
/*  7884 */       return -1;
/*  7885 */     if (paramInt == 0) {
/*  7886 */       return pingDatabase();
/*       */     }
/*       */     try
/*       */     {
/*  7890 */       this.pingResult = -2;
/*  7891 */       Thread localThread = new Thread(new Runnable() {
/*       */         public void run() {
/*       */           try {
/*  7894 */             PhysicalConnection.this.pingResult = PhysicalConnection.this.doPingDatabase();
/*       */           }
/*       */           catch (Throwable localThrowable) {}
/*       */         }
/*  7898 */       });
/*  7899 */       localThread.start();
/*  7900 */       localThread.join(paramInt * 1000);
/*  7901 */       return this.pingResult;
/*       */     }
/*       */     catch (InterruptedException localInterruptedException) {}
/*  7904 */     return -3;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   int doPingDatabase()
/*       */     throws SQLException
/*       */   {
/*  7913 */     Statement localStatement = null;
/*       */     
/*       */     try
/*       */     {
/*  7917 */       localStatement = createStatement();
/*       */       
/*  7919 */       ((oracle.jdbc.OracleStatement)localStatement).defineColumnType(1, 12, 1);
/*  7920 */       localStatement.executeQuery("SELECT 'x' FROM DUAL");
/*       */     }
/*       */     catch (SQLException localSQLException)
/*       */     {
/*  7924 */       return -1;
/*       */     }
/*       */     finally
/*       */     {
/*  7928 */       if (localStatement != null) {
/*  7929 */         localStatement.close();
/*       */       }
/*       */     }
/*  7932 */     return 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized Map getJavaObjectTypeMap()
/*       */   {
/*  7948 */     return this.javaObjectMap;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized void setJavaObjectTypeMap(Map paramMap)
/*       */   {
/*  7958 */     this.javaObjectMap = paramMap;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   /**
/*       */    * @deprecated
/*       */    */
/*       */   public void clearClientIdentifier(String paramString)
/*       */     throws SQLException
/*       */   {
/*  7973 */     if ((paramString != null) && (paramString.length() != 0))
/*       */     {
/*       */ 
/*       */ 
/*  7977 */       String[] arrayOfString = getEndToEndMetrics();
/*       */       
/*  7979 */       if ((arrayOfString != null) && (paramString.equals(arrayOfString[1])))
/*       */       {
/*       */ 
/*  7982 */         arrayOfString[1] = null;
/*       */         
/*  7984 */         setEndToEndMetrics(arrayOfString, getEndToEndECIDSequenceNumber());
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   /**
/*       */    * @deprecated
/*       */    */
/*       */   public void setClientIdentifier(String paramString)
/*       */     throws SQLException
/*       */   {
/*  8006 */     String[] arrayOfString = getEndToEndMetrics();
/*       */     
/*  8008 */     if (arrayOfString == null)
/*       */     {
/*  8010 */       arrayOfString = new String[4];
/*       */     }
/*       */     
/*       */ 
/*  8014 */     arrayOfString[1] = paramString;
/*       */     
/*  8016 */     setEndToEndMetrics(arrayOfString, getEndToEndECIDSequenceNumber());
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  8028 */   String sessionTimeZone = null;
/*  8029 */   String databaseTimeZone = null;
/*  8030 */   Calendar dbTzCalendar = null;
/*       */   
/*       */ 
/*       */ 
/*       */   static final String RAW_STR = "RAW";
/*       */   
/*       */ 
/*       */ 
/*       */   static final String SYS_RAW_STR = "SYS.RAW";
/*       */   
/*       */ 
/*       */ 
/*       */   static final String SYS_ANYDATA_STR = "SYS.ANYDATA";
/*       */   
/*       */ 
/*       */ 
/*       */   static final String SYS_XMLTYPE_STR = "SYS.XMLTYPE";
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setSessionTimeZone(String paramString)
/*       */     throws SQLException
/*       */   {
/*  8054 */     Statement localStatement = null;
/*  8055 */     Object localObject1 = null;
/*       */     
/*       */     try
/*       */     {
/*  8059 */       localStatement = createStatement();
/*       */       
/*       */ 
/*  8062 */       localStatement.executeUpdate("ALTER SESSION SET TIME_ZONE = '" + paramString + "'");
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*  8067 */       if (this.dbTzCalendar == null) {
/*  8068 */         setDbTzCalendar(getDatabaseTimeZone());
/*       */       }
/*       */     }
/*       */     catch (SQLException localSQLException)
/*       */     {
/*  8073 */       throw localSQLException;
/*       */     }
/*       */     finally
/*       */     {
/*  8077 */       if (localStatement != null) {
/*  8078 */         localStatement.close();
/*       */       }
/*       */     }
/*  8081 */     this.sessionTimeZone = paramString;
/*       */   }
/*       */   
/*       */   public String getDatabaseTimeZone()
/*       */     throws SQLException
/*       */   {
/*  8087 */     if (this.databaseTimeZone == null)
/*       */     {
/*  8089 */       this.databaseTimeZone = getPropertyFromDatabase("SELECT DBTIMEZONE FROM DUAL");
/*       */     }
/*       */     
/*  8092 */     return this.databaseTimeZone;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public String getSessionTimeZone()
/*       */   {
/*  8106 */     return this.sessionTimeZone;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   private static String to2DigitString(int paramInt)
/*       */   {
/*       */     String str;
/*       */     
/*  8115 */     if (paramInt < 10) {
/*  8116 */       str = "0" + paramInt;
/*       */     } else {
/*  8118 */       str = "" + paramInt;
/*       */     }
/*  8120 */     return str;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public String tzToOffset(String paramString)
/*       */   {
/*  8128 */     if (paramString == null) {
/*  8129 */       return paramString;
/*       */     }
/*  8131 */     int i = paramString.charAt(0);
/*       */     
/*  8133 */     if ((i != 45) && (i != 43))
/*       */     {
/*       */ 
/*  8136 */       TimeZone localTimeZone = TimeZone.getTimeZone(paramString);
/*       */       
/*  8138 */       int j = localTimeZone.getOffset(System.currentTimeMillis());
/*       */       
/*  8140 */       if (j != 0)
/*       */       {
/*  8142 */         int k = j / 60000;
/*  8143 */         int m = k / 60;
/*  8144 */         k -= m * 60;
/*       */         
/*  8146 */         if (j > 0)
/*       */         {
/*  8148 */           paramString = "+" + to2DigitString(m) + ":" + to2DigitString(k);
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/*  8153 */           paramString = "-" + to2DigitString(-m) + ":" + to2DigitString(-k);
/*       */         }
/*       */         
/*       */       }
/*       */       else
/*       */       {
/*  8159 */         paramString = "+00:00";
/*       */       }
/*       */     }
/*       */     
/*  8163 */     return paramString;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public String getSessionTimeZoneOffset()
/*       */     throws SQLException
/*       */   {
/*  8173 */     String str = getPropertyFromDatabase("SELECT SESSIONTIMEZONE FROM DUAL");
/*       */     
/*       */ 
/*  8176 */     if (str != null)
/*       */     {
/*  8178 */       str = tzToOffset(str.trim());
/*       */     }
/*       */     
/*  8181 */     return str;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private void setDbTzCalendar(String paramString)
/*       */   {
/*  8196 */     int i = paramString.charAt(0);
/*       */     
/*  8198 */     if ((i == 45) || (i == 43)) {
/*  8199 */       paramString = "GMT" + paramString;
/*       */     }
/*  8201 */     TimeZone localTimeZone = TimeZone.getTimeZone(paramString);
/*       */     
/*  8203 */     this.dbTzCalendar = new java.util.GregorianCalendar(localTimeZone);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public Calendar getDbTzCalendar()
/*       */     throws SQLException
/*       */   {
/*  8218 */     if (this.dbTzCalendar == null)
/*       */     {
/*       */ 
/*  8221 */       setDbTzCalendar(getDatabaseTimeZone());
/*       */     }
/*       */     
/*  8224 */     return this.dbTzCalendar;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setAccumulateBatchResult(boolean paramBoolean)
/*       */   {
/*  8241 */     this.accumulateBatchResult = paramBoolean;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public boolean isAccumulateBatchResult()
/*       */   {
/*  8259 */     return this.accumulateBatchResult;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setJ2EE13Compliant(boolean paramBoolean)
/*       */   {
/*  8276 */     this.j2ee13Compliant = paramBoolean;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public boolean getJ2EE13Compliant()
/*       */   {
/*  8293 */     return this.j2ee13Compliant;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public Class classForNameAndSchema(String paramString1, String paramString2)
/*       */     throws ClassNotFoundException
/*       */   {
/*  8310 */     return Class.forName(paramString1);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public Class safelyGetClassForName(String paramString)
/*       */     throws ClassNotFoundException
/*       */   {
/*  8330 */     return Class.forName(paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int getHeapAllocSize()
/*       */     throws SQLException
/*       */   {
/*  8344 */     if (this.lifecycle != 1)
/*       */     {
/*  8346 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  8347 */       localSQLException.fillInStackTrace();
/*  8348 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*  8352 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  8353 */     localSQLException.fillInStackTrace();
/*  8354 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int getOCIEnvHeapAllocSize()
/*       */     throws SQLException
/*       */   {
/*  8368 */     if (this.lifecycle != 1)
/*       */     {
/*  8370 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  8371 */       localSQLException.fillInStackTrace();
/*  8372 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*  8376 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  8377 */     localSQLException.fillInStackTrace();
/*  8378 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public static OracleConnection unwrapCompletely(oracle.jdbc.OracleConnection paramOracleConnection)
/*       */   {
/*  8393 */     Object localObject1 = paramOracleConnection;
/*  8394 */     Object localObject2 = localObject1;
/*       */     
/*       */     for (;;)
/*       */     {
/*  8398 */       if (localObject2 == null) {
/*  8399 */         return (OracleConnection)localObject1;
/*       */       }
/*  8401 */       localObject1 = localObject2;
/*  8402 */       localObject2 = ((oracle.jdbc.OracleConnection)localObject1).unwrap();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public void setWrapper(oracle.jdbc.OracleConnection paramOracleConnection)
/*       */   {
/*  8410 */     this.wrapper = paramOracleConnection;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public oracle.jdbc.OracleConnection unwrap()
/*       */   {
/*  8417 */     return null;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public oracle.jdbc.OracleConnection getWrapper()
/*       */   {
/*  8424 */     if (this.wrapper != null) {
/*  8425 */       return this.wrapper;
/*       */     }
/*  8427 */     return this;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   static oracle.jdbc.internal.OracleConnection _physicalConnectionWithin(Connection paramConnection)
/*       */   {
/*  8445 */     OracleConnection localOracleConnection = null;
/*       */     
/*       */ 
/*       */ 
/*  8449 */     if (paramConnection != null)
/*       */     {
/*  8451 */       localOracleConnection = unwrapCompletely((oracle.jdbc.OracleConnection)paramConnection);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  8456 */     return localOracleConnection;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public oracle.jdbc.internal.OracleConnection physicalConnectionWithin()
/*       */   {
/*  8463 */     return this;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public long getTdoCState(String paramString1, String paramString2)
/*       */     throws SQLException
/*       */   {
/*  8471 */     return 0L;
/*       */   }
/*       */   
/*       */   public void getOracleTypeADT(OracleTypeADT paramOracleTypeADT)
/*       */     throws SQLException
/*       */   {}
/*       */   
/*       */   public Datum toDatum(CustomDatum paramCustomDatum)
/*       */     throws SQLException
/*       */   {
/*  8481 */     return paramCustomDatum.toDatum(this);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public short getNCharSet()
/*       */   {
/*  8488 */     return this.conversion.getNCharSetId();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public ResultSet newArrayDataResultSet(Datum[] paramArrayOfDatum, long paramLong, int paramInt, Map paramMap)
/*       */     throws SQLException
/*       */   {
/*  8496 */     return new ArrayDataResultSet(this, paramArrayOfDatum, paramLong, paramInt, paramMap);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public ResultSet newArrayDataResultSet(ARRAY paramARRAY, long paramLong, int paramInt, Map paramMap)
/*       */     throws SQLException
/*       */   {
/*  8504 */     return new ArrayDataResultSet(this, paramARRAY, paramLong, paramInt, paramMap);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public ResultSet newArrayLocatorResultSet(ArrayDescriptor paramArrayDescriptor, byte[] paramArrayOfByte, long paramLong, int paramInt, Map paramMap)
/*       */     throws SQLException
/*       */   {
/*  8514 */     return new ArrayLocatorResultSet(this, paramArrayDescriptor, paramArrayOfByte, paramLong, paramInt, paramMap);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public java.sql.ResultSetMetaData newStructMetaData(StructDescriptor paramStructDescriptor)
/*       */     throws SQLException
/*       */   {
/*  8522 */     return new StructMetaData(paramStructDescriptor);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public int CHARBytesToJavaChars(byte[] paramArrayOfByte, int paramInt, char[] paramArrayOfChar)
/*       */     throws SQLException
/*       */   {
/*  8530 */     int[] arrayOfInt = new int[1];
/*       */     
/*  8532 */     arrayOfInt[0] = paramInt;
/*       */     
/*  8534 */     return this.conversion.CHARBytesToJavaChars(paramArrayOfByte, 0, paramArrayOfChar, 0, arrayOfInt, paramArrayOfChar.length);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public int NCHARBytesToJavaChars(byte[] paramArrayOfByte, int paramInt, char[] paramArrayOfChar)
/*       */     throws SQLException
/*       */   {
/*  8543 */     int[] arrayOfInt = new int[1];
/*       */     
/*  8545 */     return this.conversion.NCHARBytesToJavaChars(paramArrayOfByte, 0, paramArrayOfChar, 0, arrayOfInt, paramArrayOfChar.length);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public boolean IsNCharFixedWith()
/*       */   {
/*  8553 */     return this.conversion.IsNCharFixedWith();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public short getDriverCharSet()
/*       */   {
/*  8560 */     return this.conversion.getClientCharSet();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public int getMaxCharSize()
/*       */     throws SQLException
/*       */   {
/*  8568 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 58);
/*  8569 */     localSQLException.fillInStackTrace();
/*  8570 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public int getMaxCharbyteSize()
/*       */   {
/*  8578 */     return this.conversion.getMaxCharbyteSize();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public int getMaxNCharbyteSize()
/*       */   {
/*  8585 */     return this.conversion.getMaxNCharbyteSize();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public boolean isCharSetMultibyte(short paramShort)
/*       */   {
/*  8592 */     return DBConversion.isCharSetMultibyte(paramShort);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public int javaCharsToCHARBytes(char[] paramArrayOfChar, int paramInt, byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  8600 */     return this.conversion.javaCharsToCHARBytes(paramArrayOfChar, paramInt, paramArrayOfByte);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public int javaCharsToNCHARBytes(char[] paramArrayOfChar, int paramInt, byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  8608 */     return this.conversion.javaCharsToNCHARBytes(paramArrayOfChar, paramInt, paramArrayOfByte);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public abstract void getPropertyForPooledConnection(OraclePooledConnection paramOraclePooledConnection)
/*       */     throws SQLException;
/*       */   
/*       */ 
/*       */   final void getPropertyForPooledConnection(OraclePooledConnection paramOraclePooledConnection, String paramString)
/*       */     throws SQLException
/*       */   {
/*  8620 */     Hashtable localHashtable = new Hashtable();
/*       */     
/*  8622 */     localHashtable.put("obj_type_map", this.javaObjectMap);
/*       */     
/*  8624 */     Properties localProperties = new Properties();
/*       */     
/*  8626 */     localProperties.put("user", this.userName);
/*  8627 */     localProperties.put("password", paramString);
/*       */     
/*  8629 */     localProperties.put("connection_url", this.url);
/*  8630 */     localProperties.put("connect_auto_commit", "" + this.autocommit);
/*       */     
/*       */ 
/*  8633 */     localProperties.put("trans_isolation", "" + this.txnLevel);
/*       */     
/*  8635 */     if (getStatementCacheSize() != -1)
/*       */     {
/*  8637 */       localProperties.put("stmt_cache_size", "" + getStatementCacheSize());
/*       */       
/*  8639 */       localProperties.put("implicit_cache_enabled", "" + getImplicitCachingEnabled());
/*       */       
/*  8641 */       localProperties.put("explict_cache_enabled", "" + getExplicitCachingEnabled());
/*       */     }
/*       */     
/*       */ 
/*  8645 */     localProperties.put("defaultExecuteBatch", "" + this.defaultExecuteBatch);
/*  8646 */     localProperties.put("defaultRowPrefetch", "" + this.defaultRowPrefetch);
/*  8647 */     localProperties.put("remarksReporting", "" + this.reportRemarks);
/*  8648 */     localProperties.put("AccumulateBatchResult", "" + this.accumulateBatchResult);
/*  8649 */     localProperties.put("oracle.jdbc.J2EE13Compliant", "" + this.j2ee13Compliant);
/*  8650 */     localProperties.put("processEscapes", "" + this.processEscapes);
/*       */     
/*  8652 */     localProperties.put("restrictGetTables", "" + this.restrictGettables);
/*  8653 */     localProperties.put("includeSynonyms", "" + this.includeSynonyms);
/*  8654 */     localProperties.put("fixedString", "" + this.fixedString);
/*       */     
/*  8656 */     localHashtable.put("connection_properties", localProperties);
/*       */     
/*  8658 */     paramOraclePooledConnection.setProperties(localHashtable);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public Properties getDBAccessProperties()
/*       */     throws SQLException
/*       */   {
/*  8685 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  8686 */     localSQLException.fillInStackTrace();
/*  8687 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public Properties getOCIHandles()
/*       */     throws SQLException
/*       */   {
/*  8704 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  8705 */     localSQLException.fillInStackTrace();
/*  8706 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   abstract void logon()
/*       */     throws SQLException;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void logoff()
/*       */     throws SQLException
/*       */   {}
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   abstract void open(OracleStatement paramOracleStatement)
/*       */     throws SQLException;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   abstract void cancelOperationOnServer(boolean paramBoolean)
/*       */     throws SQLException;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   abstract void doSetAutoCommit(boolean paramBoolean)
/*       */     throws SQLException;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   abstract void doCommit(int paramInt)
/*       */     throws SQLException;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   abstract void doRollback()
/*       */     throws SQLException;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   abstract String doGetDatabaseProductVersion()
/*       */     throws SQLException;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   abstract short doGetVersionNumber()
/*       */     throws SQLException;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int getDefaultStreamChunkSize()
/*       */   {
/*  8806 */     return this.streamChunkSize;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   abstract OracleStatement RefCursorBytesToStatement(byte[] paramArrayOfByte, OracleStatement paramOracleStatement)
/*       */     throws SQLException;
/*       */   
/*       */ 
/*       */   public oracle.jdbc.internal.OracleStatement refCursorCursorToStatement(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  8818 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  8819 */     localSQLException.fillInStackTrace();
/*  8820 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public Connection getLogicalConnection(OraclePooledConnection paramOraclePooledConnection, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  8833 */     if ((this.logicalConnectionAttached != null) || (paramOraclePooledConnection.getPhysicalHandle() != this))
/*       */     {
/*  8835 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 143);
/*  8836 */       ((SQLException)localObject).fillInStackTrace();
/*  8837 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*  8840 */     Object localObject = new LogicalConnection(paramOraclePooledConnection, this, paramBoolean);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  8845 */     this.logicalConnectionAttached = ((LogicalConnection)localObject);
/*       */     
/*  8847 */     return (Connection)localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public void getForm(OracleTypeADT paramOracleTypeADT, OracleTypeCLOB paramOracleTypeCLOB, int paramInt)
/*       */     throws SQLException
/*       */   {}
/*       */   
/*       */ 
/*       */   public CLOB createClob(byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  8860 */     Object localObject = new CLOB(this, paramArrayOfByte);
/*  8861 */     if (((CLOB)localObject).isNCLOB())
/*       */     {
/*  8863 */       localObject = new NCLOB((CLOB)localObject);
/*       */     }
/*       */     
/*  8866 */     return (CLOB)localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public CLOB createClobWithUnpickledBytes(byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  8875 */     Object localObject = new CLOB(this, paramArrayOfByte, true);
/*  8876 */     if (((CLOB)localObject).isNCLOB())
/*       */     {
/*  8878 */       localObject = new NCLOB((CLOB)localObject);
/*       */     }
/*       */     
/*  8881 */     return (CLOB)localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public CLOB createClob(byte[] paramArrayOfByte, short paramShort)
/*       */     throws SQLException
/*       */   {
/*  8892 */     if (paramShort == 2) {
/*  8893 */       return new NCLOB(this, paramArrayOfByte);
/*       */     }
/*       */     
/*       */ 
/*  8897 */     return new CLOB(this, paramArrayOfByte, paramShort);
/*       */   }
/*       */   
/*       */ 
/*       */   public BLOB createBlob(byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  8904 */     return new BLOB(this, paramArrayOfByte);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public BLOB createBlobWithUnpickledBytes(byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  8912 */     return new BLOB(this, paramArrayOfByte, true);
/*       */   }
/*       */   
/*       */ 
/*       */   public BFILE createBfile(byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  8919 */     return new BFILE(this, paramArrayOfByte);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public ARRAY createARRAY(String paramString, Object paramObject)
/*       */     throws SQLException
/*       */   {
/*  8938 */     ArrayDescriptor localArrayDescriptor = ArrayDescriptor.createDescriptor(paramString, this);
/*  8939 */     return new ARRAY(localArrayDescriptor, this, paramObject);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public Array createOracleArray(String paramString, Object paramObject)
/*       */     throws SQLException
/*       */   {
/*  8959 */     return createARRAY(paramString, paramObject);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public BINARY_DOUBLE createBINARY_DOUBLE(double paramDouble)
/*       */     throws SQLException
/*       */   {
/*  8976 */     return new BINARY_DOUBLE(paramDouble);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public BINARY_FLOAT createBINARY_FLOAT(float paramFloat)
/*       */     throws SQLException
/*       */   {
/*  8991 */     return new BINARY_FLOAT(paramFloat);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public DATE createDATE(Date paramDate)
/*       */     throws SQLException
/*       */   {
/*  9006 */     return new DATE(paramDate);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public DATE createDATE(Time paramTime)
/*       */     throws SQLException
/*       */   {
/*  9021 */     return new DATE(paramTime);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public DATE createDATE(Timestamp paramTimestamp)
/*       */     throws SQLException
/*       */   {
/*  9036 */     return new DATE(paramTimestamp);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public DATE createDATE(Date paramDate, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9054 */     return new DATE(paramDate);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public DATE createDATE(Time paramTime, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9072 */     return new DATE(paramTime);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public DATE createDATE(Timestamp paramTimestamp, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9090 */     return new DATE(paramTimestamp);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public DATE createDATE(String paramString)
/*       */     throws SQLException
/*       */   {
/*  9105 */     return new DATE(paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public INTERVALDS createINTERVALDS(String paramString)
/*       */     throws SQLException
/*       */   {
/*  9120 */     return new INTERVALDS(paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public INTERVALYM createINTERVALYM(String paramString)
/*       */     throws SQLException
/*       */   {
/*  9135 */     return new INTERVALYM(paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public NUMBER createNUMBER(boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  9150 */     return new NUMBER(paramBoolean);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public NUMBER createNUMBER(byte paramByte)
/*       */     throws SQLException
/*       */   {
/*  9165 */     return new NUMBER(paramByte);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public NUMBER createNUMBER(short paramShort)
/*       */     throws SQLException
/*       */   {
/*  9180 */     return new NUMBER(paramShort);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public NUMBER createNUMBER(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  9195 */     return new NUMBER(paramInt);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public NUMBER createNUMBER(long paramLong)
/*       */     throws SQLException
/*       */   {
/*  9210 */     return new NUMBER(paramLong);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public NUMBER createNUMBER(float paramFloat)
/*       */     throws SQLException
/*       */   {
/*  9225 */     return new NUMBER(paramFloat);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public NUMBER createNUMBER(double paramDouble)
/*       */     throws SQLException
/*       */   {
/*  9240 */     return new NUMBER(paramDouble);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public NUMBER createNUMBER(BigDecimal paramBigDecimal)
/*       */     throws SQLException
/*       */   {
/*  9255 */     return new NUMBER(paramBigDecimal);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public NUMBER createNUMBER(BigInteger paramBigInteger)
/*       */     throws SQLException
/*       */   {
/*  9270 */     return new NUMBER(paramBigInteger);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public NUMBER createNUMBER(String paramString, int paramInt)
/*       */     throws SQLException
/*       */   {
/*  9286 */     return new NUMBER(paramString, paramInt);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public Array createArrayOf(String paramString, Object[] paramArrayOfObject)
/*       */     throws SQLException
/*       */   {
/*  9308 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  9309 */     localSQLException.fillInStackTrace();
/*  9310 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public java.sql.Struct createStruct(String paramString, Object[] paramArrayOfObject)
/*       */     throws SQLException
/*       */   {
/*       */     try
/*       */     {
/*  9329 */       StructDescriptor localStructDescriptor = StructDescriptor.createDescriptor(paramString, this);
/*  9330 */       return new oracle.sql.STRUCT(localStructDescriptor, this, paramArrayOfObject);
/*       */     }
/*       */     catch (SQLException localSQLException)
/*       */     {
/*  9334 */       if (localSQLException.getErrorCode() == 17049)
/*  9335 */         removeAllDescriptor();
/*  9336 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMP createTIMESTAMP(Date paramDate)
/*       */     throws SQLException
/*       */   {
/*  9352 */     return new TIMESTAMP(paramDate);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMP createTIMESTAMP(DATE paramDATE)
/*       */     throws SQLException
/*       */   {
/*  9367 */     return new TIMESTAMP(paramDATE);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMP createTIMESTAMP(Time paramTime)
/*       */     throws SQLException
/*       */   {
/*  9382 */     return new TIMESTAMP(paramTime);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMP createTIMESTAMP(Timestamp paramTimestamp)
/*       */     throws SQLException
/*       */   {
/*  9397 */     return new TIMESTAMP(paramTimestamp);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMP createTIMESTAMP(String paramString)
/*       */     throws SQLException
/*       */   {
/*  9412 */     return new TIMESTAMP(paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMPTZ createTIMESTAMPTZ(Date paramDate)
/*       */     throws SQLException
/*       */   {
/*  9427 */     return new TIMESTAMPTZ(this, paramDate);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMPTZ createTIMESTAMPTZ(Date paramDate, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9446 */     return new TIMESTAMPTZ(this, paramDate, paramCalendar);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMPTZ createTIMESTAMPTZ(Time paramTime)
/*       */     throws SQLException
/*       */   {
/*  9461 */     return new TIMESTAMPTZ(this, paramTime);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMPTZ createTIMESTAMPTZ(Time paramTime, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9480 */     return new TIMESTAMPTZ(this, paramTime, paramCalendar);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMPTZ createTIMESTAMPTZ(Timestamp paramTimestamp)
/*       */     throws SQLException
/*       */   {
/*  9495 */     return new TIMESTAMPTZ(this, paramTimestamp);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMPTZ createTIMESTAMPTZ(Timestamp paramTimestamp, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9514 */     return new TIMESTAMPTZ(this, paramTimestamp, paramCalendar);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMPTZ createTIMESTAMPTZ(String paramString)
/*       */     throws SQLException
/*       */   {
/*  9529 */     return new TIMESTAMPTZ(this, paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMPTZ createTIMESTAMPTZ(String paramString, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9548 */     return new TIMESTAMPTZ(this, paramString, paramCalendar);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMPTZ createTIMESTAMPTZ(DATE paramDATE)
/*       */     throws SQLException
/*       */   {
/*  9559 */     return new TIMESTAMPTZ(this, paramDATE);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMPLTZ createTIMESTAMPLTZ(Date paramDate, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9578 */     return new TIMESTAMPLTZ(this, paramCalendar, paramDate);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMPLTZ createTIMESTAMPLTZ(Time paramTime, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9597 */     return new TIMESTAMPLTZ(this, paramCalendar, paramTime);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMPLTZ createTIMESTAMPLTZ(Timestamp paramTimestamp, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9616 */     return new TIMESTAMPLTZ(this, paramCalendar, paramTimestamp);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMPLTZ createTIMESTAMPLTZ(String paramString, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9635 */     return new TIMESTAMPLTZ(this, paramCalendar, paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMESTAMPLTZ createTIMESTAMPLTZ(DATE paramDATE, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9654 */     return new TIMESTAMPLTZ(this, paramCalendar, paramDATE);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public abstract BLOB createTemporaryBlob(Connection paramConnection, boolean paramBoolean, int paramInt)
/*       */     throws SQLException;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public abstract CLOB createTemporaryClob(Connection paramConnection, boolean paramBoolean, int paramInt, short paramShort)
/*       */     throws SQLException;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public java.sql.Blob createBlob()
/*       */     throws SQLException
/*       */   {
/*  9678 */     if (this.lifecycle != 1)
/*       */     {
/*  9680 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  9681 */       localSQLException.fillInStackTrace();
/*  9682 */       throw localSQLException;
/*       */     }
/*       */     
/*  9685 */     return createTemporaryBlob(this, true, 10);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public java.sql.Clob createClob()
/*       */     throws SQLException
/*       */   {
/*  9699 */     if (this.lifecycle != 1)
/*       */     {
/*  9701 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  9702 */       localSQLException.fillInStackTrace();
/*  9703 */       throw localSQLException;
/*       */     }
/*       */     
/*  9706 */     return createTemporaryClob(this, true, 10, (short)1);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public NClob createNClob()
/*       */     throws SQLException
/*       */   {
/*  9721 */     if (this.lifecycle != 1)
/*       */     {
/*  9723 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  9724 */       localSQLException.fillInStackTrace();
/*  9725 */       throw localSQLException;
/*       */     }
/*       */     
/*  9728 */     return (NClob)createTemporaryClob(this, true, 10, (short)2);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public SQLXML createSQLXML()
/*       */     throws SQLException
/*       */   {
/*  9743 */     if (this.lifecycle != 1)
/*       */     {
/*  9745 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  9746 */       localSQLException.fillInStackTrace();
/*  9747 */       throw localSQLException;
/*       */     }
/*       */     
/*  9750 */     return (SQLXML)new oracle.xdb.XMLType(this, (String)null);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public boolean isDescriptorSharable(oracle.jdbc.internal.OracleConnection paramOracleConnection)
/*       */     throws SQLException
/*       */   {
/*  9767 */     PhysicalConnection localPhysicalConnection1 = this;
/*  9768 */     PhysicalConnection localPhysicalConnection2 = (PhysicalConnection)paramOracleConnection.getPhysicalConnection();
/*       */     
/*  9770 */     return (localPhysicalConnection1 == localPhysicalConnection2) || (localPhysicalConnection1.url.equals(localPhysicalConnection2.url)) || ((localPhysicalConnection2.protocol != null) && (localPhysicalConnection2.protocol.equals("kprb")));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   boolean useLittleEndianSetCHARBinder()
/*       */     throws SQLException
/*       */   {
/*  9779 */     return false;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setPlsqlWarnings(String paramString)
/*       */     throws SQLException
/*       */   {
/*  9800 */     if (paramString == null)
/*       */     {
/*  9802 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  9803 */       ((SQLException)localObject1).fillInStackTrace();
/*  9804 */       throw ((Throwable)localObject1);
/*       */     }
/*       */     
/*  9807 */     if ((paramString != null) && ((paramString = paramString.trim()).length() > 0) && (!OracleSql.isValidPlsqlWarning(paramString)))
/*       */     {
/*       */ 
/*       */ 
/*  9811 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  9812 */       ((SQLException)localObject1).fillInStackTrace();
/*  9813 */       throw ((Throwable)localObject1);
/*       */     }
/*       */     
/*  9816 */     Object localObject1 = "ALTER SESSION SET PLSQL_WARNINGS=" + paramString;
/*       */     
/*  9818 */     String str = "ALTER SESSION SET EVENTS='10933 TRACE NAME CONTEXT LEVEL 32768'";
/*       */     
/*       */ 
/*  9821 */     Statement localStatement = null;
/*       */     try
/*       */     {
/*  9824 */       localStatement = createStatement(-1, -1);
/*       */       
/*  9826 */       localStatement.execute((String)localObject1);
/*       */       
/*  9828 */       if (paramString.equals("'DISABLE:ALL'"))
/*       */       {
/*  9830 */         this.plsqlCompilerWarnings = false;
/*       */       }
/*       */       else
/*       */       {
/*  9834 */         localStatement.execute(str);
/*       */         
/*  9836 */         this.plsqlCompilerWarnings = true;
/*       */       }
/*       */     }
/*       */     finally {
/*  9840 */       if (localStatement != null) {
/*  9841 */         localStatement.close();
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void internalClose()
/*       */     throws SQLException
/*       */   {
/*  9854 */     this.lifecycle = 4;
/*       */     
/*  9856 */     Object localObject = this.statements;
/*       */     OracleStatement localOracleStatement;
/*  9858 */     while (localObject != null)
/*       */     {
/*  9860 */       localOracleStatement = ((OracleStatement)localObject).nextChild;
/*       */       
/*  9862 */       if (((OracleStatement)localObject).serverCursor)
/*       */       {
/*  9864 */         ((OracleStatement)localObject).internalClose();
/*  9865 */         removeStatement((OracleStatement)localObject);
/*       */       }
/*       */       
/*  9868 */       localObject = localOracleStatement;
/*       */     }
/*       */     
/*  9871 */     localObject = this.statements;
/*       */     
/*  9873 */     while (localObject != null)
/*       */     {
/*  9875 */       localOracleStatement = ((OracleStatement)localObject).next;
/*  9876 */       ((OracleStatement)localObject).internalClose();
/*  9877 */       localObject = localOracleStatement;
/*       */     }
/*       */     
/*  9880 */     this.statements = null;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public javax.transaction.xa.XAResource getXAResource()
/*       */     throws SQLException
/*       */   {
/*  9896 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 164);
/*  9897 */     localSQLException.fillInStackTrace();
/*  9898 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   protected void doDescribeTable(AutoKeyInfo paramAutoKeyInfo)
/*       */     throws SQLException
/*       */   {
/*  9911 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  9912 */     localSQLException.fillInStackTrace();
/*  9913 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setApplicationContext(String paramString1, String paramString2, String paramString3)
/*       */     throws SQLException
/*       */   {
/*  9926 */     if ((paramString1 == null) || (paramString2 == null) || (paramString3 == null))
/*       */     {
/*       */ 
/*  9929 */       throw new NullPointerException(); }
/*       */     SQLException localSQLException;
/*  9931 */     if (paramString1.equals(""))
/*       */     {
/*  9933 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 170);
/*       */       
/*  9935 */       localSQLException.fillInStackTrace();
/*  9936 */       throw localSQLException;
/*       */     }
/*       */     
/*  9939 */     if (paramString1.compareToIgnoreCase("CLIENTCONTEXT") != 0)
/*       */     {
/*  9941 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 174);
/*       */       
/*  9943 */       localSQLException.fillInStackTrace();
/*  9944 */       throw localSQLException;
/*       */     }
/*       */     
/*  9947 */     if (paramString2.length() > 30)
/*       */     {
/*  9949 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 171);
/*       */       
/*  9951 */       localSQLException.fillInStackTrace();
/*  9952 */       throw localSQLException;
/*       */     }
/*       */     
/*  9955 */     if (paramString3.length() > 4000)
/*       */     {
/*  9957 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 172);
/*       */       
/*  9959 */       localSQLException.fillInStackTrace();
/*  9960 */       throw localSQLException;
/*       */     }
/*       */     
/*  9963 */     doSetApplicationContext(paramString1, paramString2, paramString3);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void doSetApplicationContext(String paramString1, String paramString2, String paramString3)
/*       */     throws SQLException
/*       */   {
/*  9976 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  9977 */     localSQLException.fillInStackTrace();
/*  9978 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void clearAllApplicationContext(String paramString)
/*       */     throws SQLException
/*       */   {
/*  9989 */     if (paramString == null)
/*  9990 */       throw new NullPointerException();
/*  9991 */     if (paramString.equals(""))
/*       */     {
/*  9993 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 170);
/*       */       
/*  9995 */       localSQLException.fillInStackTrace();
/*  9996 */       throw localSQLException;
/*       */     }
/*       */     
/*  9999 */     doClearAllApplicationContext(paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void doClearAllApplicationContext(String paramString)
/*       */     throws SQLException
/*       */   {
/* 10009 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10010 */     localSQLException.fillInStackTrace();
/* 10011 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public byte[] createLightweightSession(String paramString, KeywordValueLong[] paramArrayOfKeywordValueLong, int paramInt, KeywordValueLong[][] paramArrayOfKeywordValueLong1, int[] paramArrayOfInt)
/*       */     throws SQLException
/*       */   {
/* 10025 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10026 */     localSQLException.fillInStackTrace();
/* 10027 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void executeLightweightSessionRoundtrip(int paramInt1, byte[] paramArrayOfByte, KeywordValueLong[] paramArrayOfKeywordValueLong, int paramInt2, KeywordValueLong[][] paramArrayOfKeywordValueLong1, int[] paramArrayOfInt)
/*       */     throws SQLException
/*       */   {
/* 10041 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10042 */     localSQLException.fillInStackTrace();
/* 10043 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void executeLightweightSessionPiggyback(int paramInt1, byte[] paramArrayOfByte, KeywordValueLong[] paramArrayOfKeywordValueLong, int paramInt2)
/*       */     throws SQLException
/*       */   {
/* 10055 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10056 */     localSQLException.fillInStackTrace();
/* 10057 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void doXSNamespaceOp(OracleConnection.XSOperationCode paramXSOperationCode, byte[] paramArrayOfByte, XSNamespace[] paramArrayOfXSNamespace, XSNamespace[][] paramArrayOfXSNamespace1)
/*       */     throws SQLException
/*       */   {
/* 10070 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10071 */     localSQLException.fillInStackTrace();
/* 10072 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void doXSNamespaceOp(OracleConnection.XSOperationCode paramXSOperationCode, byte[] paramArrayOfByte, XSNamespace[] paramArrayOfXSNamespace)
/*       */     throws SQLException
/*       */   {
/* 10083 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10084 */     localSQLException.fillInStackTrace();
/* 10085 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void enqueue(String paramString, AQEnqueueOptions paramAQEnqueueOptions, AQMessage paramAQMessage)
/*       */     throws SQLException
/*       */   {
/* 10096 */     AQMessageI localAQMessageI = (AQMessageI)paramAQMessage;
/*       */     
/* 10098 */     byte[][] arrayOfByte = new byte[1][];
/*       */     
/*       */ 
/*       */ 
/* 10102 */     doEnqueue(paramString, paramAQEnqueueOptions, localAQMessageI.getMessagePropertiesI(), localAQMessageI.getPayloadTOID(), localAQMessageI.getPayload(), arrayOfByte, localAQMessageI.isRAWPayload());
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/* 10113 */     if (arrayOfByte[0] != null) {
/* 10114 */       localAQMessageI.setMessageId(arrayOfByte[0]);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public AQMessage dequeue(String paramString, AQDequeueOptions paramAQDequeueOptions, byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/* 10124 */     byte[][] arrayOfByte1 = new byte[1][];
/* 10125 */     AQMessagePropertiesI localAQMessagePropertiesI = new AQMessagePropertiesI();
/* 10126 */     byte[][] arrayOfByte2 = new byte[1][];
/* 10127 */     boolean bool = false;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/* 10132 */     bool = doDequeue(paramString, paramAQDequeueOptions, localAQMessagePropertiesI, paramArrayOfByte, arrayOfByte2, arrayOfByte1, AQMessageI.compareToid(paramArrayOfByte, TypeDescriptor.RAWTOID));
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/* 10144 */     AQMessageI localAQMessageI = null;
/* 10145 */     if (bool)
/*       */     {
/* 10147 */       localAQMessageI = new AQMessageI(localAQMessagePropertiesI, this);
/* 10148 */       localAQMessageI.setPayload(arrayOfByte2[0], paramArrayOfByte);
/* 10149 */       localAQMessageI.setMessageId(arrayOfByte1[0]);
/*       */     }
/* 10151 */     return localAQMessageI;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public AQMessage dequeue(String paramString1, AQDequeueOptions paramAQDequeueOptions, String paramString2)
/*       */     throws SQLException
/*       */   {
/* 10165 */     byte[] arrayOfByte = null;
/* 10166 */     TypeDescriptor localTypeDescriptor = null;
/* 10167 */     if (("RAW".equals(paramString2)) || ("SYS.RAW".equals(paramString2)))
/*       */     {
/* 10169 */       arrayOfByte = TypeDescriptor.RAWTOID;
/* 10170 */     } else if ("SYS.ANYDATA".equals(paramString2)) {
/* 10171 */       arrayOfByte = TypeDescriptor.ANYDATATOID;
/* 10172 */     } else if ("SYS.XMLTYPE".equals(paramString2)) {
/* 10173 */       arrayOfByte = TypeDescriptor.XMLTYPETOID;
/*       */     }
/*       */     else {
/* 10176 */       localTypeDescriptor = TypeDescriptor.getTypeDescriptor(paramString2, this);
/* 10177 */       arrayOfByte = ((OracleTypeADT)localTypeDescriptor.getPickler()).getTOID();
/*       */     }
/* 10179 */     AQMessageI localAQMessageI = (AQMessageI)dequeue(paramString1, paramAQDequeueOptions, arrayOfByte);
/* 10180 */     if (localAQMessageI != null)
/*       */     {
/* 10182 */       localAQMessageI.setTypeName(paramString2);
/* 10183 */       localAQMessageI.setTypeDescriptor(localTypeDescriptor);
/*       */     }
/* 10185 */     return localAQMessageI;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   synchronized void doEnqueue(String paramString, AQEnqueueOptions paramAQEnqueueOptions, AQMessagePropertiesI paramAQMessagePropertiesI, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[][] paramArrayOfByte, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/* 10203 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10204 */     localSQLException.fillInStackTrace();
/* 10205 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   synchronized boolean doDequeue(String paramString, AQDequeueOptions paramAQDequeueOptions, AQMessagePropertiesI paramAQMessagePropertiesI, byte[] paramArrayOfByte, byte[][] paramArrayOfByte1, byte[][] paramArrayOfByte2, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/* 10227 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10228 */     localSQLException.fillInStackTrace();
/* 10229 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   /**
/*       */    * @deprecated
/*       */    */
/*       */   public boolean isV8Compatible()
/*       */     throws SQLException
/*       */   {
/* 10247 */     return this.mapDateToTimestamp;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public boolean getMapDateToTimestamp()
/*       */   {
/* 10260 */     return this.mapDateToTimestamp;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public byte getInstanceProperty(OracleConnection.InstanceProperty paramInstanceProperty)
/*       */     throws SQLException
/*       */   {
/* 10268 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10269 */     localSQLException.fillInStackTrace();
/* 10270 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public AQNotificationRegistration[] registerAQNotification(String[] paramArrayOfString, Properties[] paramArrayOfProperties, Properties paramProperties)
/*       */     throws SQLException
/*       */   {
/* 10280 */     String str = readNTFlocalhost(paramProperties);
/* 10281 */     int i = readNTFtcpport(paramProperties);
/*       */     
/* 10283 */     NTFAQRegistration[] arrayOfNTFAQRegistration = doRegisterAQNotification(paramArrayOfString, str, i, paramArrayOfProperties);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/* 10289 */     return (AQNotificationRegistration[])arrayOfNTFAQRegistration;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   NTFAQRegistration[] doRegisterAQNotification(String[] paramArrayOfString, String paramString, int paramInt, Properties[] paramArrayOfProperties)
/*       */     throws SQLException
/*       */   {
/* 10300 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10301 */     localSQLException.fillInStackTrace();
/* 10302 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void unregisterAQNotification(AQNotificationRegistration paramAQNotificationRegistration)
/*       */     throws SQLException
/*       */   {
/* 10312 */     NTFAQRegistration localNTFAQRegistration = (NTFAQRegistration)paramAQNotificationRegistration;
/* 10313 */     doUnregisterAQNotification(localNTFAQRegistration);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void doUnregisterAQNotification(NTFAQRegistration paramNTFAQRegistration)
/*       */     throws SQLException
/*       */   {
/* 10323 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10324 */     localSQLException.fillInStackTrace();
/* 10325 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   private String readNTFlocalhost(Properties paramProperties)
/*       */     throws SQLException
/*       */   {
/* 10333 */     String str = null;
/*       */     try
/*       */     {
/* 10336 */       str = paramProperties.getProperty("NTF_LOCAL_HOST", InetAddress.getLocalHost().getHostAddress());
/*       */ 
/*       */     }
/*       */     catch (UnknownHostException localUnknownHostException)
/*       */     {
/*       */ 
/* 10342 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 240);
/*       */       
/* 10344 */       localSQLException.fillInStackTrace();
/* 10345 */       throw localSQLException;
/*       */ 
/*       */ 
/*       */     }
/*       */     catch (SecurityException localSecurityException)
/*       */     {
/*       */ 
/*       */ 
/* 10353 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 241);
/*       */       
/* 10355 */       localSQLException.fillInStackTrace();
/* 10356 */       throw localSQLException;
/*       */     }
/*       */     
/* 10359 */     return str;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private int readNTFtcpport(Properties paramProperties)
/*       */     throws SQLException
/*       */   {
/* 10369 */     int i = 0;
/*       */     try
/*       */     {
/* 10372 */       i = Integer.parseInt(paramProperties.getProperty("NTF_LOCAL_TCP_PORT", "0"));
/*       */       
/*       */ 
/*       */ 
/* 10376 */       if (i < 0)
/*       */       {
/* 10378 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 242);
/*       */         
/* 10380 */         localSQLException1.fillInStackTrace();
/* 10381 */         throw localSQLException1;
/*       */       }
/*       */       
/*       */     }
/*       */     catch (NumberFormatException localNumberFormatException)
/*       */     {
/* 10387 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 242);
/*       */       
/* 10389 */       localSQLException2.fillInStackTrace();
/* 10390 */       throw localSQLException2;
/*       */     }
/*       */     
/* 10393 */     return i;
/*       */   }
/*       */   
/*       */ 
/*       */   int readNTFtimeout(Properties paramProperties)
/*       */     throws SQLException
/*       */   {
/* 10400 */     int i = 0;
/*       */     try
/*       */     {
/* 10403 */       i = Integer.parseInt(paramProperties.getProperty("NTF_TIMEOUT", "0"));
/*       */ 
/*       */     }
/*       */     catch (NumberFormatException localNumberFormatException)
/*       */     {
/* 10408 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 243);
/*       */       
/* 10410 */       localSQLException.fillInStackTrace();
/* 10411 */       throw localSQLException;
/*       */     }
/*       */     
/* 10414 */     return i;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public DatabaseChangeRegistration registerDatabaseChangeNotification(Properties paramProperties)
/*       */     throws SQLException
/*       */   {
/* 10423 */     String str = readNTFlocalhost(paramProperties);
/* 10424 */     int i = readNTFtcpport(paramProperties);
/* 10425 */     int j = readNTFtimeout(paramProperties);
/* 10426 */     int k = 0;
/*       */     
/*       */     try
/*       */     {
/* 10430 */       k = Integer.parseInt(paramProperties.getProperty("DCN_NOTIFY_CHANGELAG", "0"));
/*       */     }
/*       */     catch (NumberFormatException localNumberFormatException)
/*       */     {
/* 10434 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 244);
/*       */       
/* 10436 */       localSQLException.fillInStackTrace();
/* 10437 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/* 10443 */     NTFDCNRegistration localNTFDCNRegistration = doRegisterDatabaseChangeNotification(str, i, paramProperties, j, k);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/* 10450 */     ntfManager.addRegistration(localNTFDCNRegistration);
/* 10451 */     return localNTFDCNRegistration;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   NTFDCNRegistration doRegisterDatabaseChangeNotification(String paramString, int paramInt1, Properties paramProperties, int paramInt2, int paramInt3)
/*       */     throws SQLException
/*       */   {
/* 10560 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10561 */     localSQLException.fillInStackTrace();
/* 10562 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public DatabaseChangeRegistration getDatabaseChangeRegistration(int paramInt)
/*       */     throws SQLException
/*       */   {
/* 10627 */     NTFDCNRegistration localNTFDCNRegistration = new NTFDCNRegistration(this.instanceName, paramInt, this.userName, this.versionNumber);
/* 10628 */     return localNTFDCNRegistration;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void unregisterDatabaseChangeNotification(DatabaseChangeRegistration paramDatabaseChangeRegistration)
/*       */     throws SQLException
/*       */   {
/* 10638 */     NTFDCNRegistration localNTFDCNRegistration = (NTFDCNRegistration)paramDatabaseChangeRegistration;
/* 10639 */     if (localNTFDCNRegistration.getDatabaseName().compareToIgnoreCase(this.instanceName) != 0)
/*       */     {
/*       */ 
/*       */ 
/* 10643 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 245);
/*       */       
/* 10645 */       localSQLException.fillInStackTrace();
/* 10646 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/* 10650 */     doUnregisterDatabaseChangeNotification(localNTFDCNRegistration);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void doUnregisterDatabaseChangeNotification(NTFDCNRegistration paramNTFDCNRegistration)
/*       */     throws SQLException
/*       */   {
/* 10660 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10661 */     localSQLException.fillInStackTrace();
/* 10662 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void unregisterDatabaseChangeNotification(int paramInt)
/*       */     throws SQLException
/*       */   {
/* 10672 */     String str = null;
/*       */     try
/*       */     {
/* 10675 */       str = InetAddress.getLocalHost().getHostAddress();
/*       */     }
/*       */     catch (Exception localException) {}
/*       */     
/*       */ 
/*       */ 
/* 10681 */     unregisterDatabaseChangeNotification(paramInt, str, 47632);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public void unregisterDatabaseChangeNotification(int paramInt1, String paramString, int paramInt2)
/*       */     throws SQLException
/*       */   {
/* 10690 */     String str = "(ADDRESS=(PROTOCOL=tcp)(HOST=" + paramString + ")(PORT=" + paramInt2 + "))?PR=0";
/*       */     
/* 10692 */     unregisterDatabaseChangeNotification(paramInt1, str);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public void unregisterDatabaseChangeNotification(long paramLong, String paramString)
/*       */     throws SQLException
/*       */   {
/* 10701 */     doUnregisterDatabaseChangeNotification(paramLong, paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void doUnregisterDatabaseChangeNotification(long paramLong, String paramString)
/*       */     throws SQLException
/*       */   {
/* 10727 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10728 */     localSQLException.fillInStackTrace();
/* 10729 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void addXSEventListener(XSEventListener paramXSEventListener)
/*       */     throws SQLException
/*       */   {
/* 10739 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10740 */     localSQLException.fillInStackTrace();
/* 10741 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void addXSEventListener(XSEventListener paramXSEventListener, Executor paramExecutor)
/*       */     throws SQLException
/*       */   {
/* 10752 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10753 */     localSQLException.fillInStackTrace();
/* 10754 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void removeXSEventListener(XSEventListener paramXSEventListener)
/*       */     throws SQLException
/*       */   {
/* 10764 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10765 */     localSQLException.fillInStackTrace();
/* 10766 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TypeDescriptor[] getAllTypeDescriptorsInCurrentSchema()
/*       */     throws SQLException
/*       */   {
/* 10777 */     TypeDescriptor[] arrayOfTypeDescriptor = null;
/* 10778 */     Statement localStatement = null;
/*       */     try
/*       */     {
/* 10781 */       localStatement = createStatement();
/* 10782 */       ResultSet localResultSet = localStatement.executeQuery("SELECT schema_name, typename, typoid, typecode, version, tds  FROM TABLE(private_jdbc.Get_Type_Shape_Info())");
/*       */       
/* 10784 */       arrayOfTypeDescriptor = getTypeDescriptorsFromResultSet(localResultSet);
/* 10785 */       localResultSet.close();
/*       */     }
/*       */     catch (SQLException localSQLException1)
/*       */     {
/* 10789 */       if (localSQLException1.getErrorCode() == 904)
/*       */       {
/*       */ 
/* 10792 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 165);
/* 10793 */         localSQLException2.fillInStackTrace();
/* 10794 */         throw localSQLException2;
/*       */       }
/*       */       
/*       */ 
/*       */ 
/* 10799 */       throw localSQLException1;
/*       */ 
/*       */     }
/*       */     finally
/*       */     {
/* 10804 */       if (localStatement != null) localStatement.close();
/*       */     }
/* 10806 */     return arrayOfTypeDescriptor;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TypeDescriptor[] getTypeDescriptorsFromListInCurrentSchema(String[] paramArrayOfString)
/*       */     throws SQLException
/*       */   {
/* 10816 */     String str = "SELECT schema_name, typename, typoid, typecode, version, tds  FROM TABLE(private_jdbc.Get_Type_Shape_Info(?))";
/* 10817 */     TypeDescriptor[] arrayOfTypeDescriptor = null;
/* 10818 */     PreparedStatement localPreparedStatement = null;
/*       */     try
/*       */     {
/* 10821 */       localPreparedStatement = prepareStatement(str);
/* 10822 */       int i = paramArrayOfString.length;
/* 10823 */       localObject1 = new StringBuffer(i * 8);
/* 10824 */       for (int j = 0; j < i; j++)
/*       */       {
/* 10826 */         ((StringBuffer)localObject1).append(paramArrayOfString[j]);
/* 10827 */         if (j < i - 1) ((StringBuffer)localObject1).append(',');
/*       */       }
/* 10829 */       localPreparedStatement.setString(1, ((StringBuffer)localObject1).toString());
/* 10830 */       ResultSet localResultSet = localPreparedStatement.executeQuery();
/* 10831 */       arrayOfTypeDescriptor = getTypeDescriptorsFromResultSet(localResultSet);
/* 10832 */       localResultSet.close();
/*       */     }
/*       */     catch (SQLException localSQLException) {
/*       */       Object localObject1;
/* 10836 */       if (localSQLException.getErrorCode() == 904)
/*       */       {
/*       */ 
/* 10839 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 165);
/* 10840 */         ((SQLException)localObject1).fillInStackTrace();
/* 10841 */         throw ((Throwable)localObject1);
/*       */       }
/*       */       
/*       */ 
/*       */ 
/* 10846 */       throw localSQLException;
/*       */ 
/*       */     }
/*       */     finally
/*       */     {
/* 10851 */       if (localPreparedStatement != null) localPreparedStatement.close();
/*       */     }
/* 10853 */     return arrayOfTypeDescriptor;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TypeDescriptor[] getTypeDescriptorsFromList(String[][] paramArrayOfString)
/*       */     throws SQLException
/*       */   {
/* 10864 */     TypeDescriptor[] arrayOfTypeDescriptor = null;
/* 10865 */     PreparedStatement localPreparedStatement = null;
/* 10866 */     int i = paramArrayOfString.length;
/* 10867 */     StringBuffer localStringBuffer1 = new StringBuffer(i * 8);
/* 10868 */     StringBuffer localStringBuffer2 = new StringBuffer(i * 8);
/* 10869 */     for (int j = 0; j < i; j++)
/*       */     {
/* 10871 */       localStringBuffer1.append(paramArrayOfString[j][0]);
/* 10872 */       localStringBuffer2.append(paramArrayOfString[j][1]);
/* 10873 */       if (j < i - 1)
/*       */       {
/* 10875 */         localStringBuffer1.append(',');
/* 10876 */         localStringBuffer2.append(',');
/*       */       }
/*       */     }
/*       */     
/*       */     try
/*       */     {
/* 10882 */       String str = "SELECT schema_name, typename, typoid, typecode, version, tds FROM TABLE(private_jdbc.Get_All_Type_Shape_Info(?,?))";
/*       */       
/* 10884 */       localPreparedStatement = prepareStatement(str);
/* 10885 */       localPreparedStatement.setString(1, localStringBuffer1.toString());
/* 10886 */       localPreparedStatement.setString(2, localStringBuffer2.toString());
/*       */       
/* 10888 */       localObject1 = localPreparedStatement.executeQuery();
/* 10889 */       arrayOfTypeDescriptor = getTypeDescriptorsFromResultSet((ResultSet)localObject1);
/* 10890 */       ((ResultSet)localObject1).close();
/*       */     }
/*       */     catch (SQLException localSQLException) {
/*       */       Object localObject1;
/* 10894 */       if (localSQLException.getErrorCode() == 904)
/*       */       {
/*       */ 
/* 10897 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 165);
/* 10898 */         ((SQLException)localObject1).fillInStackTrace();
/* 10899 */         throw ((Throwable)localObject1);
/*       */       }
/*       */       
/*       */ 
/*       */ 
/* 10904 */       throw localSQLException;
/*       */ 
/*       */     }
/*       */     finally
/*       */     {
/* 10909 */       if (localPreparedStatement != null) localPreparedStatement.close();
/*       */     }
/* 10911 */     return arrayOfTypeDescriptor;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   TypeDescriptor[] getTypeDescriptorsFromResultSet(ResultSet paramResultSet)
/*       */     throws SQLException
/*       */   {
/* 10932 */     ArrayList localArrayList = new ArrayList();
/* 10933 */     Object localObject2; while (paramResultSet.next())
/*       */     {
/* 10935 */       localObject1 = paramResultSet.getString(1);
/* 10936 */       String str1 = paramResultSet.getString(2);
/* 10937 */       localObject2 = paramResultSet.getBytes(3);
/* 10938 */       String str2 = paramResultSet.getString(4);
/* 10939 */       int j = paramResultSet.getInt(5);
/* 10940 */       byte[] arrayOfByte = paramResultSet.getBytes(6);
/* 10941 */       SQLName localSQLName = new SQLName((String)localObject1, str1, this);
/* 10942 */       Object localObject3; if (str2.equals("OBJECT"))
/*       */       {
/* 10944 */         localObject3 = StructDescriptor.createDescriptor(localSQLName, (byte[])localObject2, j, arrayOfByte, this);
/*       */         
/*       */ 
/*       */ 
/* 10948 */         putDescriptor((byte[])localObject2, localObject3);
/* 10949 */         putDescriptor(((TypeDescriptor)localObject3).getName(), localObject3);
/* 10950 */         localArrayList.add(localObject3);
/*       */       }
/* 10952 */       else if (str2.equals("COLLECTION"))
/*       */       {
/* 10954 */         localObject3 = ArrayDescriptor.createDescriptor(localSQLName, (byte[])localObject2, j, arrayOfByte, this);
/*       */         
/*       */ 
/*       */ 
/* 10958 */         putDescriptor((byte[])localObject2, localObject3);
/* 10959 */         putDescriptor(((TypeDescriptor)localObject3).getName(), localObject3);
/* 10960 */         localArrayList.add(localObject3);
/*       */       }
/*       */     }
/* 10963 */     Object localObject1 = new TypeDescriptor[localArrayList.size()];
/* 10964 */     for (int i = 0; i < localArrayList.size(); i++)
/*       */     {
/* 10966 */       localObject2 = (TypeDescriptor)localArrayList.get(i);
/* 10967 */       localObject1[i] = localObject2;
/*       */     }
/* 10969 */     return (TypeDescriptor[])localObject1;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public synchronized boolean isUsable()
/*       */   {
/* 10981 */     return this.isUsable;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setUsable(boolean paramBoolean)
/*       */   {
/* 10996 */     this.isUsable = paramBoolean;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void queryFCFProperties(Properties paramProperties)
/*       */     throws SQLException
/*       */   {
/* 11014 */     Statement localStatement = null;
/* 11015 */     ResultSet localResultSet = null;
/* 11016 */     String str1 = "select sys_context('userenv', 'instance_name'),sys_context('userenv', 'server_host'),sys_context('userenv', 'service_name'),sys_context('userenv', 'db_unique_name') from dual";
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     try
/*       */     {
/* 11024 */       localStatement = createStatement();
/* 11025 */       localStatement.setFetchSize(1);
/* 11026 */       localResultSet = localStatement.executeQuery(str1);
/* 11027 */       while (localResultSet.next())
/*       */       {
/* 11029 */         String str2 = null;
/* 11030 */         str2 = localResultSet.getString(1);
/* 11031 */         if (str2 != null) {
/* 11032 */           paramProperties.put("INSTANCE_NAME", str2.trim());
/*       */         }
/* 11034 */         str2 = localResultSet.getString(2);
/* 11035 */         if (str2 != null) {
/* 11036 */           paramProperties.put("SERVER_HOST", str2.trim());
/*       */         }
/*       */         
/*       */ 
/* 11040 */         str2 = localResultSet.getString(3);
/* 11041 */         if (str2 != null) {
/* 11042 */           paramProperties.put("SERVICE_NAME", str2.trim());
/*       */         }
/* 11044 */         str2 = localResultSet.getString(4);
/* 11045 */         if (str2 != null) {
/* 11046 */           paramProperties.put("DATABASE_NAME", str2.trim());
/*       */         }
/*       */       }
/*       */     } finally {
/* 11050 */       if (localResultSet != null)
/* 11051 */         localResultSet.close();
/* 11052 */       if (localStatement != null) {
/* 11053 */         localStatement.close();
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */   public void setDefaultTimeZone(TimeZone paramTimeZone)
/*       */     throws SQLException
/*       */   {
/* 11061 */     this.defaultTimeZone = paramTimeZone;
/*       */   }
/*       */   
/*       */ 
/*       */   public TimeZone getDefaultTimeZone()
/*       */     throws SQLException
/*       */   {
/* 11068 */     return this.defaultTimeZone;
/*       */   }
/*       */   
/*       */ 
/* 11072 */   int timeZoneVersionNumber = -1;
/* 11073 */   TIMEZONETAB timeZoneTab = null;
/*       */   
/*       */   public int getTimezoneVersionNumber() throws SQLException
/*       */   {
/* 11077 */     return this.timeZoneVersionNumber;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public TIMEZONETAB getTIMEZONETAB()
/*       */     throws SQLException
/*       */   {
/* 11087 */     if (this.timeZoneTab == null) {
/* 11088 */       this.timeZoneTab = TIMEZONETAB.getInstance(getTimezoneVersionNumber());
/*       */     }
/* 11090 */     return this.timeZoneTab;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public boolean isDataInLocatorEnabled()
/*       */     throws SQLException
/*       */   {
/* 11104 */     return (getVersionNumber() >= 10200 ? 1 : 0) & (getVersionNumber() < 11000 ? 1 : 0) & this.enableReadDataInLocator | this.overrideEnableReadDataInLocator;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public boolean isLobStreamPosStandardCompliant()
/*       */     throws SQLException
/*       */   {
/* 11113 */     return this.lobStreamPosStandardCompliant;
/*       */   }
/*       */   
/*       */ 
/*       */   public long getCurrentSCN()
/*       */     throws SQLException
/*       */   {
/* 11120 */     return doGetCurrentSCN();
/*       */   }
/*       */   
/*       */   long doGetCurrentSCN()
/*       */     throws SQLException
/*       */   {
/* 11126 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 11127 */     localSQLException.fillInStackTrace();
/* 11128 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */   void doSetSnapshotSCN(long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11135 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 11136 */     localSQLException.fillInStackTrace();
/* 11137 */     throw localSQLException;
/*       */   }
/*       */   
/*       */   public EnumSet<OracleConnection.TransactionState> getTransactionState()
/*       */     throws SQLException
/*       */   {
/* 11143 */     return doGetTransactionState();
/*       */   }
/*       */   
/*       */   EnumSet<OracleConnection.TransactionState> doGetTransactionState()
/*       */     throws SQLException
/*       */   {
/* 11149 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 11150 */     localSQLException.fillInStackTrace();
/* 11151 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */   public boolean isConnectionSocketKeepAlive()
/*       */     throws java.net.SocketException, SQLException
/*       */   {
/* 11158 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 11159 */     localSQLException.fillInStackTrace();
/* 11160 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/* 11165 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*       */   public static final boolean TRACE = false;
/*       */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/PhysicalConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */