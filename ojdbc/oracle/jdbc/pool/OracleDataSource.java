/*      */ package oracle.jdbc.pool;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Serializable;
/*      */ import java.sql.Connection;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Properties;
/*      */ import javax.naming.NamingException;
/*      */ import javax.naming.Reference;
/*      */ import javax.naming.Referenceable;
/*      */ import javax.naming.StringRefAddr;
/*      */ import javax.sql.DataSource;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.driver.OracleDriver;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class OracleDataSource
/*      */   implements DataSource, Serializable, Referenceable
/*      */ {
/*      */   static final long serialVersionUID = 3349652938965166731L;
/*   48 */   protected PrintWriter logWriter = null;
/*   49 */   protected int loginTimeout = 0;
/*      */   
/*      */ 
/*   52 */   protected String databaseName = null;
/*   53 */   protected String serviceName = null;
/*   54 */   protected String dataSourceName = "OracleDataSource";
/*   55 */   protected String description = null;
/*   56 */   protected String networkProtocol = "tcp";
/*   57 */   protected int portNumber = 0;
/*   58 */   protected String user = null;
/*   59 */   protected String password = null;
/*   60 */   protected String serverName = null;
/*   61 */   protected String url = null;
/*   62 */   protected String driverType = null;
/*   63 */   protected String tnsEntry = null;
/*   64 */   protected int maxStatements = 0;
/*   65 */   protected boolean implicitCachingEnabled = false;
/*   66 */   protected boolean explicitCachingEnabled = false;
/*      */   
/*   68 */   protected transient OracleImplicitConnectionCache odsCache = null;
/*   69 */   protected transient OracleConnectionCacheManager cacheManager = null;
/*   70 */   protected String connCacheName = null;
/*   71 */   protected Properties connCacheProperties = null;
/*   72 */   protected Properties connectionProperties = null;
/*   73 */   protected boolean connCachingEnabled = false;
/*   74 */   protected boolean fastConnFailover = false;
/*   75 */   protected String onsConfigStr = null;
/*   76 */   public boolean isOracleDataSource = true;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   95 */   private static final boolean fastConnectionFailoverSysProperty = "true".equalsIgnoreCase(OracleDriver.getSystemPropertyFastConnectionFailover("false"));
/*      */   
/*      */ 
/*      */ 
/*   99 */   private boolean urlExplicit = false;
/*  100 */   private boolean useDefaultConnection = false;
/*  101 */   protected transient OracleDriver driver = new OracleDriver();
/*      */   private static final String spawnNewThreadToCancelProperty = "oracle.jdbc.spawnNewThreadToCancel";
/*      */   
/*      */   public OracleDataSource() throws SQLException
/*      */   {
/*  106 */     processFastConnectionFailoverSysProperty();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void processFastConnectionFailoverSysProperty()
/*      */   {
/*  114 */     if ((this.isOracleDataSource) && (fastConnectionFailoverSysProperty))
/*      */     {
/*      */ 
/*      */ 
/*  118 */       this.connCachingEnabled = true;
/*      */       
/*      */ 
/*  121 */       if (this.cacheManager == null)
/*      */       {
/*      */         try
/*      */         {
/*  125 */           this.cacheManager = OracleConnectionCacheManager.getConnectionCacheManagerInstance();
/*      */         }
/*      */         catch (SQLException localSQLException) {}
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  135 */       this.fastConnFailover = true;
/*  136 */       setSpawnNewThreadToCancel(true);
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
/*      */   public Connection getConnection()
/*      */     throws SQLException
/*      */   {
/*  150 */     String str1 = null;
/*  151 */     String str2 = null;
/*  152 */     synchronized (this)
/*      */     {
/*  154 */       str1 = this.user;
/*  155 */       str2 = this.password;
/*      */     }
/*  157 */     return getConnection(str1, str2);
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
/*      */   public Connection getConnection(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  174 */     Connection localConnection = null;
/*  175 */     Properties localProperties = null;
/*  176 */     if (this.connCachingEnabled)
/*      */     {
/*      */ 
/*  179 */       localConnection = getConnection(paramString1, paramString2, null);
/*      */     }
/*      */     else
/*      */     {
/*  183 */       synchronized (this)
/*      */       {
/*  185 */         makeURL();
/*      */         
/*  187 */         localProperties = this.connectionProperties == null ? new Properties() : (Properties)this.connectionProperties.clone();
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  194 */         if (this.url != null)
/*  195 */           localProperties.setProperty("connection_url", this.url);
/*  196 */         if (paramString1 != null)
/*  197 */           localProperties.setProperty("user", paramString1);
/*  198 */         if (paramString2 != null)
/*  199 */           localProperties.setProperty("password", paramString2);
/*  200 */         if (this.loginTimeout != 0) {
/*  201 */           localProperties.setProperty("LoginTimeout", "" + this.loginTimeout);
/*      */         }
/*  203 */         if (this.maxStatements != 0) {
/*  204 */           localProperties.setProperty("stmt_cache_size", "" + this.maxStatements);
/*      */         }
/*      */       }
/*  207 */       localConnection = getPhysicalConnection(localProperties);
/*  208 */       if (localConnection == null)
/*      */       {
/*  210 */         ??? = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 67);
/*  211 */         ((SQLException)???).fillInStackTrace();
/*  212 */         throw ((Throwable)???);
/*      */       }
/*      */     }
/*  215 */     return localConnection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected Connection getPhysicalConnection(Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  223 */     Connection localConnection = null;
/*  224 */     Properties localProperties = paramProperties;
/*  225 */     String str1 = paramProperties.getProperty("connection_url");
/*  226 */     String str2 = paramProperties.getProperty("user");
/*  227 */     String str3 = localProperties.getProperty("password");
/*  228 */     String str4 = null;
/*  229 */     boolean bool1 = false;
/*      */     
/*      */ 
/*      */ 
/*  233 */     synchronized (this)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  239 */       if (this.connectionProperties != null)
/*      */       {
/*  241 */         localProperties = (Properties)this.connectionProperties.clone();
/*      */         
/*  243 */         if (str2 != null) {
/*  244 */           localProperties.put("user", str2);
/*      */         }
/*  246 */         if (str3 != null)
/*  247 */           localProperties.put("password", str3);
/*      */       }
/*  249 */       if ((str2 == null) && (this.user != null))
/*  250 */         localProperties.put("user", this.user);
/*  251 */       if ((str3 == null) && (this.password != null)) {
/*  252 */         localProperties.put("password", this.password);
/*      */       }
/*  254 */       if (str1 == null) {
/*  255 */         str1 = this.url;
/*      */       }
/*  257 */       String str5 = paramProperties.getProperty("LoginTimeout");
/*      */       
/*  259 */       if (str5 != null) {
/*  260 */         localProperties.put("oracle.net.CONNECT_TIMEOUT", "" + Integer.parseInt(str5) * 1000);
/*      */       }
/*      */       
/*  263 */       bool1 = this.useDefaultConnection;
/*      */       
/*  265 */       if (this.driver == null) {
/*  266 */         this.driver = new OracleDriver();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  274 */     if (bool1)
/*      */     {
/*  276 */       localConnection = this.driver.defaultConnection();
/*      */     }
/*      */     else
/*      */     {
/*  280 */       localConnection = this.driver.connect(str1, localProperties);
/*      */     }
/*      */     
/*  283 */     if (localConnection == null)
/*      */     {
/*  285 */       ??? = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 67);
/*  286 */       ((SQLException)???).fillInStackTrace();
/*  287 */       throw ((Throwable)???);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  292 */     str4 = paramProperties.getProperty("stmt_cache_size");
/*      */     
/*  294 */     int i = 0;
/*  295 */     if (str4 != null) {
/*  296 */       ((oracle.jdbc.OracleConnection)localConnection).setStatementCacheSize(i = Integer.parseInt(str4));
/*      */     }
/*      */     
/*  299 */     boolean bool2 = false;
/*  300 */     str4 = paramProperties.getProperty("ExplicitStatementCachingEnabled");
/*      */     
/*  302 */     if (str4 != null) {
/*  303 */       ((oracle.jdbc.OracleConnection)localConnection).setExplicitCachingEnabled(bool2 = str4.equals("true"));
/*      */     }
/*      */     
/*  306 */     boolean bool3 = false;
/*  307 */     str4 = paramProperties.getProperty("ImplicitStatementCachingEnabled");
/*      */     
/*  309 */     if (str4 != null)
/*      */     {
/*  311 */       ((oracle.jdbc.OracleConnection)localConnection).setImplicitCachingEnabled(bool3 = str4.equals("true"));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  316 */     if ((i > 0) && (!bool2) && (!bool3))
/*      */     {
/*      */ 
/*      */ 
/*  320 */       ((oracle.jdbc.OracleConnection)localConnection).setImplicitCachingEnabled(true);
/*  321 */       ((oracle.jdbc.OracleConnection)localConnection).setExplicitCachingEnabled(true);
/*      */     }
/*  323 */     return localConnection;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public Connection getConnection(Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  342 */     String str1 = null;
/*  343 */     String str2 = null;
/*  344 */     synchronized (this)
/*      */     {
/*  346 */       if (!this.connCachingEnabled)
/*      */       {
/*      */ 
/*  349 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 137);
/*      */         
/*  351 */         localSQLException.fillInStackTrace();
/*  352 */         throw localSQLException;
/*      */       }
/*      */       
/*  355 */       str1 = this.user;
/*  356 */       str2 = this.password;
/*      */     }
/*      */     
/*  359 */     ??? = getConnection(str1, str2, paramProperties);
/*      */     
/*  361 */     return (Connection)???;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public Connection getConnection(String paramString1, String paramString2, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  379 */     if (!this.connCachingEnabled)
/*      */     {
/*      */ 
/*  382 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 137);
/*  383 */       ((SQLException)localObject).fillInStackTrace();
/*  384 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  392 */     if (this.odsCache == null) {
/*  393 */       cacheInitialize();
/*      */     }
/*  395 */     Object localObject = this.odsCache.getConnection(paramString1, paramString2, paramProperties);
/*      */     
/*  397 */     return (Connection)localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private synchronized void cacheInitialize()
/*      */     throws SQLException
/*      */   {
/*  409 */     if (this.odsCache == null)
/*      */     {
/*  411 */       if (this.connCacheName != null) {
/*  412 */         this.cacheManager.createCache(this.connCacheName, this, this.connCacheProperties);
/*      */       } else {
/*  414 */         this.connCacheName = this.cacheManager.createCache(this, this.connCacheProperties);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public synchronized void close()
/*      */     throws SQLException
/*      */   {
/*  431 */     if ((this.connCachingEnabled) && (this.odsCache != null))
/*      */     {
/*  433 */       this.cacheManager.removeCache(this.odsCache.cacheName, 0L);
/*      */       
/*  435 */       this.odsCache = null;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public synchronized void setConnectionCachingEnabled(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  453 */     if (this.isOracleDataSource)
/*      */     {
/*  455 */       if (paramBoolean)
/*      */       {
/*  457 */         this.connCachingEnabled = true;
/*      */         
/*  459 */         if (this.cacheManager == null)
/*      */         {
/*  461 */           this.cacheManager = OracleConnectionCacheManager.getConnectionCacheManagerInstance();
/*      */ 
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*  470 */       else if (this.odsCache == null)
/*      */       {
/*  472 */         this.connCachingEnabled = false;
/*  473 */         this.fastConnFailover = false;
/*  474 */         setSpawnNewThreadToCancel(false);
/*  475 */         this.connCacheName = null;
/*  476 */         this.connCacheProperties = null;
/*      */ 
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/*  489 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 137);
/*  490 */       localSQLException.fillInStackTrace();
/*  491 */       throw localSQLException;
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
/*      */   public boolean getConnectionCachingEnabled()
/*      */     throws SQLException
/*      */   {
/*  510 */     return this.connCachingEnabled;
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
/*      */   public synchronized void setConnectionCacheName(String paramString)
/*      */     throws SQLException
/*      */   {
/*  525 */     if (paramString == null)
/*      */     {
/*      */ 
/*  528 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 138);
/*  529 */       localSQLException.fillInStackTrace();
/*  530 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  535 */     this.connCacheName = paramString;
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
/*      */   public String getConnectionCacheName()
/*      */     throws SQLException
/*      */   {
/*  553 */     if ((this.connCachingEnabled) && (this.odsCache != null)) {
/*  554 */       return this.odsCache.cacheName;
/*      */     }
/*  556 */     return this.connCacheName;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public synchronized void setConnectionCacheProperties(Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  637 */     this.connCacheProperties = paramProperties;
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
/*      */   public Properties getConnectionCacheProperties()
/*      */     throws SQLException
/*      */   {
/*  654 */     if ((this.connCachingEnabled) && (this.odsCache != null)) {
/*  655 */       return this.odsCache.getConnectionCacheProperties();
/*      */     }
/*  657 */     return this.connCacheProperties;
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
/*      */   public synchronized void setFastConnectionFailoverEnabled(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  682 */     if (!this.fastConnFailover)
/*      */     {
/*  684 */       this.fastConnFailover = paramBoolean;
/*  685 */       setSpawnNewThreadToCancel(paramBoolean);
/*  686 */     } else if (!paramBoolean)
/*      */     {
/*      */ 
/*      */ 
/*  690 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 255);
/*  691 */       localSQLException.fillInStackTrace();
/*  692 */       throw localSQLException;
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
/*      */   public boolean getFastConnectionFailoverEnabled()
/*      */     throws SQLException
/*      */   {
/*  710 */     return this.fastConnFailover;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public String getONSConfiguration()
/*      */     throws SQLException
/*      */   {
/*  727 */     return this.onsConfigStr;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public synchronized void setONSConfiguration(String paramString)
/*      */     throws SQLException
/*      */   {
/*  759 */     this.onsConfigStr = paramString;
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
/*      */   public synchronized int getLoginTimeout()
/*      */   {
/*  778 */     return this.loginTimeout;
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
/*      */   public synchronized void setLoginTimeout(int paramInt)
/*      */   {
/*  796 */     this.loginTimeout = paramInt;
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
/*      */   public synchronized void setLogWriter(PrintWriter paramPrintWriter)
/*      */   {
/*  820 */     this.logWriter = paramPrintWriter;
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
/*      */   public synchronized PrintWriter getLogWriter()
/*      */   {
/*  844 */     return this.logWriter;
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
/*      */   public synchronized void setTNSEntryName(String paramString)
/*      */   {
/*  859 */     this.tnsEntry = paramString;
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
/*      */   public synchronized String getTNSEntryName()
/*      */   {
/*  873 */     return this.tnsEntry;
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
/*      */   public synchronized void setDataSourceName(String paramString)
/*      */   {
/*  888 */     this.dataSourceName = paramString;
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
/*      */   public synchronized String getDataSourceName()
/*      */   {
/*  902 */     return this.dataSourceName;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized String getDatabaseName()
/*      */   {
/*  914 */     return this.databaseName;
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
/*      */   public synchronized void setDatabaseName(String paramString)
/*      */   {
/*  928 */     this.databaseName = paramString;
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
/*      */   public synchronized void setServiceName(String paramString)
/*      */   {
/*  946 */     this.serviceName = paramString;
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
/*      */   public synchronized String getServiceName()
/*      */   {
/*  963 */     return this.serviceName;
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
/*      */   public synchronized void setServerName(String paramString)
/*      */   {
/*  976 */     this.serverName = paramString;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized String getServerName()
/*      */   {
/*  988 */     return this.serverName;
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
/*      */   public synchronized void setURL(String paramString)
/*      */   {
/* 1002 */     this.url = paramString;
/*      */     
/* 1004 */     if (this.url != null) {
/* 1005 */       this.urlExplicit = true;
/*      */     }
/* 1007 */     if ((this.connCachingEnabled) && (this.odsCache != null) && (this.odsCache.connectionPoolDS != null))
/*      */     {
/* 1009 */       this.odsCache.connectionPoolDS.url = paramString;
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
/*      */   public synchronized String getURL()
/*      */     throws SQLException
/*      */   {
/* 1024 */     if (!this.urlExplicit)
/* 1025 */       makeURL();
/* 1026 */     return this.url;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void setUser(String paramString)
/*      */   {
/* 1038 */     this.user = paramString;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getUser()
/*      */   {
/* 1049 */     return this.user;
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
/*      */   public synchronized void setPassword(String paramString)
/*      */   {
/* 1062 */     this.password = paramString;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected String getPassword()
/*      */   {
/* 1069 */     return this.password;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized String getDescription()
/*      */   {
/* 1081 */     return this.description;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void setDescription(String paramString)
/*      */   {
/* 1093 */     this.description = paramString;
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
/*      */   public synchronized String getDriverType()
/*      */   {
/* 1106 */     return this.driverType;
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
/*      */   public synchronized void setDriverType(String paramString)
/*      */   {
/* 1126 */     this.driverType = paramString;
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
/*      */   public synchronized String getNetworkProtocol()
/*      */   {
/* 1139 */     return this.networkProtocol;
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
/*      */   public synchronized void setNetworkProtocol(String paramString)
/*      */   {
/* 1153 */     this.networkProtocol = paramString;
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
/*      */   public synchronized void setPortNumber(int paramInt)
/*      */   {
/* 1166 */     this.portNumber = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized int getPortNumber()
/*      */   {
/* 1178 */     return this.portNumber;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized Reference getReference()
/*      */     throws NamingException
/*      */   {
/* 1188 */     Reference localReference = new Reference(getClass().getName(), "oracle.jdbc.pool.OracleDataSourceFactory", null);
/*      */     
/* 1190 */     addRefProperties(localReference);
/* 1191 */     return localReference;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void addRefProperties(Reference paramReference)
/*      */   {
/* 1199 */     if (this.url != null) {
/* 1200 */       paramReference.add(new StringRefAddr("url", this.url));
/*      */     }
/* 1202 */     if (this.user != null) {
/* 1203 */       paramReference.add(new StringRefAddr("userName", this.user));
/*      */     }
/* 1205 */     if (this.password != null) {
/* 1206 */       paramReference.add(new StringRefAddr("passWord", this.password));
/*      */     }
/* 1208 */     if (this.description != null) {
/* 1209 */       paramReference.add(new StringRefAddr("description", this.description));
/*      */     }
/* 1211 */     if (this.driverType != null) {
/* 1212 */       paramReference.add(new StringRefAddr("driverType", this.driverType));
/*      */     }
/* 1214 */     if (this.serverName != null) {
/* 1215 */       paramReference.add(new StringRefAddr("serverName", this.serverName));
/*      */     }
/* 1217 */     if (this.databaseName != null) {
/* 1218 */       paramReference.add(new StringRefAddr("databaseName", this.databaseName));
/*      */     }
/* 1220 */     if (this.serviceName != null) {
/* 1221 */       paramReference.add(new StringRefAddr("serviceName", this.serviceName));
/*      */     }
/* 1223 */     if (this.networkProtocol != null) {
/* 1224 */       paramReference.add(new StringRefAddr("networkProtocol", this.networkProtocol));
/*      */     }
/* 1226 */     if (this.portNumber != 0) {
/* 1227 */       paramReference.add(new StringRefAddr("portNumber", Integer.toString(this.portNumber)));
/*      */     }
/* 1229 */     if (this.tnsEntry != null) {
/* 1230 */       paramReference.add(new StringRefAddr("tnsentryname", this.tnsEntry));
/*      */     }
/* 1232 */     if (this.maxStatements != 0) {
/* 1233 */       paramReference.add(new StringRefAddr("maxStatements", Integer.toString(this.maxStatements)));
/*      */     }
/*      */     
/* 1236 */     if (this.implicitCachingEnabled) {
/* 1237 */       paramReference.add(new StringRefAddr("implicitCachingEnabled", "true"));
/*      */     }
/* 1239 */     if (this.explicitCachingEnabled) {
/* 1240 */       paramReference.add(new StringRefAddr("explicitCachingEnabled", "true"));
/*      */     }
/*      */     
/*      */ 
/* 1244 */     if (this.connCachingEnabled) {
/* 1245 */       paramReference.add(new StringRefAddr("connectionCachingEnabled", "true"));
/*      */     }
/* 1247 */     if (this.connCacheName != null) {
/* 1248 */       paramReference.add(new StringRefAddr("connectionCacheName", this.connCacheName));
/*      */     }
/* 1250 */     if (this.connCacheProperties != null) {
/* 1251 */       paramReference.add(new StringRefAddr("connectionCacheProperties", this.connCacheProperties.toString()));
/*      */     }
/*      */     
/* 1254 */     if (this.fastConnFailover) {
/* 1255 */       paramReference.add(new StringRefAddr("fastConnectionFailoverEnabled", "true"));
/*      */     }
/* 1257 */     if (this.onsConfigStr != null) {
/* 1258 */       paramReference.add(new StringRefAddr("onsConfigStr", this.onsConfigStr));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void makeURL()
/*      */     throws SQLException
/*      */   {
/* 1268 */     if (this.urlExplicit) {
/*      */       return;
/*      */     }
/*      */     SQLException localSQLException;
/* 1272 */     if ((this.driverType == null) || ((!this.driverType.equals("oci8")) && (!this.driverType.equals("oci")) && (!this.driverType.equals("thin")) && (!this.driverType.equals("kprb"))))
/*      */     {
/*      */ 
/*      */ 
/* 1276 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 67, "OracleDataSource.makeURL");
/* 1277 */       localSQLException.fillInStackTrace();
/* 1278 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1283 */     if (this.driverType.equals("kprb"))
/*      */     {
/* 1285 */       this.useDefaultConnection = true;
/* 1286 */       this.url = "jdbc:oracle:kprb:@";
/*      */       
/*      */ 
/* 1289 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1294 */     if (((this.driverType.equals("oci8")) || (this.driverType.equals("oci"))) && (this.networkProtocol != null) && (this.networkProtocol.equals("ipc")))
/*      */     {
/*      */ 
/* 1297 */       this.url = "jdbc:oracle:oci:@";
/*      */       
/*      */ 
/* 1300 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1304 */     if (this.tnsEntry != null)
/*      */     {
/* 1306 */       this.url = ("jdbc:oracle:" + this.driverType + ":@" + this.tnsEntry);
/*      */       
/*      */ 
/* 1309 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1313 */     if (this.serviceName != null)
/*      */     {
/* 1315 */       this.url = ("jdbc:oracle:" + this.driverType + ":@(DESCRIPTION=(ADDRESS=(PROTOCOL=" + this.networkProtocol + ")(PORT=" + this.portNumber + ")(HOST=" + this.serverName + "))(CONNECT_DATA=(SERVICE_NAME=" + this.serviceName + ")))");
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 1321 */       this.url = ("jdbc:oracle:" + this.driverType + ":@(DESCRIPTION=(ADDRESS=(PROTOCOL=" + this.networkProtocol + ")(PORT=" + this.portNumber + ")(HOST=" + this.serverName + "))(CONNECT_DATA=(SID=" + this.databaseName + ")))");
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1328 */       DatabaseError.addSqlWarning(null, new SQLWarning("URL with SID jdbc:subprotocol:@host:port:sid will be deprecated in 10i\nPlease use URL with SERVICE_NAME as jdbc:subprotocol:@//host:port/service_name"));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1338 */       if (this.fastConnFailover)
/*      */       {
/*      */ 
/* 1341 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 67, "OracleDataSource.makeURL");
/* 1342 */         localSQLException.fillInStackTrace();
/* 1343 */         throw localSQLException;
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
/*      */   protected void trace(String paramString)
/*      */   {
/* 1357 */     if (this.logWriter != null) {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void copy(OracleDataSource paramOracleDataSource)
/*      */     throws SQLException
/*      */   {
/* 1367 */     paramOracleDataSource.setUser(this.user);
/* 1368 */     paramOracleDataSource.setPassword(this.password);
/* 1369 */     paramOracleDataSource.setTNSEntryName(this.tnsEntry);
/* 1370 */     makeURL();
/* 1371 */     paramOracleDataSource.setURL(this.url);
/* 1372 */     if (this.loginTimeout > 0)
/* 1373 */       paramOracleDataSource.setLoginTimeout(this.loginTimeout);
/* 1374 */     paramOracleDataSource.connectionProperties = this.connectionProperties;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setMaxStatements(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1399 */     this.maxStatements = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMaxStatements()
/*      */     throws SQLException
/*      */   {
/* 1411 */     return this.maxStatements;
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
/*      */   public void setImplicitCachingEnabled(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1429 */     this.implicitCachingEnabled = paramBoolean;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getImplicitCachingEnabled()
/*      */     throws SQLException
/*      */   {
/* 1441 */     return this.implicitCachingEnabled;
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
/*      */   public void setExplicitCachingEnabled(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1460 */     this.explicitCachingEnabled = paramBoolean;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getExplicitCachingEnabled()
/*      */     throws SQLException
/*      */   {
/* 1472 */     return this.explicitCachingEnabled;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setConnectionProperties(Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/* 1779 */     if (paramProperties == null) this.connectionProperties = paramProperties; else
/* 1780 */       this.connectionProperties = ((Properties)paramProperties.clone());
/* 1781 */     setSpawnNewThreadToCancel(this.fastConnFailover);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Properties getConnectionProperties()
/*      */     throws SQLException
/*      */   {
/* 1793 */     return filterConnectionProperties(this.connectionProperties);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final Properties filterConnectionProperties(Properties paramProperties)
/*      */   {
/* 1805 */     Properties localProperties = null;
/*      */     
/*      */ 
/* 1808 */     if (paramProperties != null)
/*      */     {
/* 1810 */       localProperties = (Properties)paramProperties.clone();
/* 1811 */       Enumeration localEnumeration = localProperties.propertyNames();
/* 1812 */       Object localObject = null;
/* 1813 */       while (localEnumeration.hasMoreElements())
/*      */       {
/* 1815 */         String str = (String)localEnumeration.nextElement();
/* 1816 */         if ((str != null) && (str.matches(".*[P,p][A,a][S,s][S,s][W,w][O,o][R,r][D,d].*")))
/*      */         {
/* 1818 */           localProperties.remove(str);
/*      */         }
/*      */       }
/* 1821 */       paramProperties.remove("oracle.jdbc.spawnNewThreadToCancel");
/*      */     }
/* 1823 */     return localProperties;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void setSpawnNewThreadToCancel(boolean paramBoolean)
/*      */   {
/* 1831 */     if (paramBoolean) {
/* 1832 */       if (this.connectionProperties == null) { this.connectionProperties = new Properties();
/*      */       }
/* 1834 */       this.connectionProperties.setProperty("oracle.jdbc.spawnNewThreadToCancel", "true");
/*      */     }
/* 1836 */     else if (this.connectionProperties != null) {
/* 1837 */       this.connectionProperties.remove("oracle.jdbc.spawnNewThreadToCancel");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void writeObject(ObjectOutputStream paramObjectOutputStream)
/*      */     throws IOException
/*      */   {
/* 1850 */     paramObjectOutputStream.defaultWriteObject();
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
/*      */   private void readObject(ObjectInputStream paramObjectInputStream)
/*      */     throws IOException, ClassNotFoundException, SQLException
/*      */   {
/* 1866 */     paramObjectInputStream.defaultReadObject();
/*      */     
/*      */ 
/*      */ 
/* 1870 */     if (this.connCachingEnabled) {
/* 1871 */       setConnectionCachingEnabled(this.connCachingEnabled);
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
/*      */   public boolean isWrapperFor(Class<?> paramClass)
/*      */     throws SQLException
/*      */   {
/* 1891 */     if (paramClass.isInterface()) { return paramClass.isInstance(this);
/*      */     }
/* 1893 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 1894 */     localSQLException.fillInStackTrace();
/* 1895 */     throw localSQLException;
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
/*      */   public <T> T unwrap(Class<T> paramClass)
/*      */     throws SQLException
/*      */   {
/* 1912 */     if ((paramClass.isInterface()) && (paramClass.isInstance(this))) { return this;
/*      */     }
/* 1914 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 1915 */     localSQLException.fillInStackTrace();
/* 1916 */     throw localSQLException;
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
/*      */   protected oracle.jdbc.internal.OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 1933 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1938 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */