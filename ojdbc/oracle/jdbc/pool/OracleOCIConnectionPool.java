/*      */ package oracle.jdbc.pool;
/*      */ 
/*      */ import java.sql.Connection;
/*      */ import java.sql.SQLException;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.Properties;
/*      */ import javax.naming.NamingException;
/*      */ import javax.naming.Reference;
/*      */ import javax.naming.StringRefAddr;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.driver.OracleDriver;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.oci.OracleOCIConnection;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class OracleOCIConnectionPool
/*      */   extends OracleDataSource
/*      */ {
/*      */   public OracleOCIConnection m_connection_pool;
/*      */   public static final String IS_CONNECTION_POOLING = "is_connection_pooling";
/*   46 */   private int m_conn_min_limit = 0;
/*   47 */   private int m_conn_max_limit = 0;
/*   48 */   private int m_conn_increment = 0;
/*   49 */   private int m_conn_active_size = 0;
/*   50 */   private int m_conn_pool_size = 0;
/*   51 */   private int m_conn_timeout = 0;
/*   52 */   private String m_conn_nowait = "false";
/*   53 */   private int m_is_transactions_distributed = 0;
/*      */   
/*      */   public static final String CONNPOOL_OBJECT = "connpool_object";
/*      */   
/*      */   public static final String CONNPOOL_LOGON_MODE = "connection_pool";
/*      */   
/*      */   public static final String CONNECTION_POOL = "connection_pool";
/*      */   
/*      */   public static final String CONNPOOL_CONNECTION = "connpool_connection";
/*      */   
/*      */   public static final String CONNPOOL_PROXY_CONNECTION = "connpool_proxy_connection";
/*      */   
/*      */   public static final String CONNPOOL_ALIASED_CONNECTION = "connpool_alias_connection";
/*      */   
/*      */   public static final String PROXY_USER_NAME = "proxy_user_name";
/*      */   
/*      */   public static final String PROXY_DISTINGUISHED_NAME = "proxy_distinguished_name";
/*      */   
/*      */   public static final String PROXY_CERTIFICATE = "proxy_certificate";
/*      */   
/*      */   public static final String PROXY_ROLES = "proxy_roles";
/*      */   
/*      */   public static final String PROXY_NUM_ROLES = "proxy_num_roles";
/*      */   
/*      */   public static final String PROXY_PASSWORD = "proxy_password";
/*      */   
/*      */   public static final String PROXYTYPE = "proxytype";
/*      */   
/*      */   public static final String PROXYTYPE_USER_NAME = "proxytype_user_name";
/*      */   
/*      */   public static final String PROXYTYPE_DISTINGUISHED_NAME = "proxytype_distinguished_name";
/*      */   
/*      */   public static final String PROXYTYPE_CERTIFICATE = "proxytype_certificate";
/*      */   
/*      */   public static final String CONNECTION_ID = "connection_id";
/*      */   
/*      */   public static final String CONNPOOL_MIN_LIMIT = "connpool_min_limit";
/*      */   
/*      */   public static final String CONNPOOL_MAX_LIMIT = "connpool_max_limit";
/*      */   
/*      */   public static final String CONNPOOL_INCREMENT = "connpool_increment";
/*      */   
/*      */   public static final String CONNPOOL_ACTIVE_SIZE = "connpool_active_size";
/*      */   public static final String CONNPOOL_POOL_SIZE = "connpool_pool_size";
/*      */   public static final String CONNPOOL_TIMEOUT = "connpool_timeout";
/*      */   public static final String CONNPOOL_NOWAIT = "connpool_nowait";
/*      */   public static final String CONNPOOL_IS_POOLCREATED = "connpool_is_poolcreated";
/*      */   public static final String TRANSACTIONS_DISTRIBUTED = "transactions_distributed";
/*  101 */   private Hashtable m_lconnections = null;
/*      */   
/*  103 */   private static enum Lifecycle { NEW,  OPEN,  CLOSING,  CLOSED;
/*  104 */     private Lifecycle() {} } private Lifecycle lifecycle = Lifecycle.NEW;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void ensureOpen()
/*      */     throws SQLException
/*      */   {
/*  114 */     if (this.lifecycle == Lifecycle.NEW) {
/*  115 */       createConnectionPool(null);
/*      */     }
/*  117 */     if (this.lifecycle != Lifecycle.OPEN)
/*      */     {
/*  119 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  120 */       localSQLException.fillInStackTrace();
/*  121 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  129 */   private OracleDriver m_oracleDriver = new OracleDriver();
/*      */   
/*      */ 
/*  132 */   protected int m_stmtCacheSize = 0;
/*  133 */   protected boolean m_stmtClearMetaData = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleOCIConnectionPool(String paramString1, String paramString2, String paramString3, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  149 */     this();
/*      */     
/*  151 */     setURL(paramString3);
/*  152 */     setUser(paramString1);
/*  153 */     setPassword(paramString2);
/*  154 */     createConnectionPool(paramProperties);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public OracleOCIConnectionPool(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/*  165 */     this();
/*      */     
/*  167 */     setURL(paramString3);
/*  168 */     setUser(paramString1);
/*  169 */     setPassword(paramString2);
/*  170 */     createConnectionPool(null);
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
/*      */   public OracleOCIConnectionPool()
/*      */     throws SQLException
/*      */   {
/*  188 */     this.isOracleDataSource = false;
/*  189 */     this.m_lconnections = new Hashtable(10);
/*      */     
/*  191 */     setDriverType("oci8");
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
/*      */   public synchronized Connection getConnection()
/*      */     throws SQLException
/*      */   {
/*  211 */     ensureOpen();
/*      */     
/*      */ 
/*      */ 
/*  215 */     Connection localConnection = getConnection(this.user, this.password);
/*      */     
/*  217 */     return localConnection;
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
/*      */   public synchronized Connection getConnection(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  231 */     ensureOpen();
/*      */     
/*      */     Properties localProperties;
/*  234 */     if (this.connectionProperties != null) {
/*  235 */       localProperties = new Properties(this.connectionProperties);
/*      */     } else {
/*  237 */       localProperties = new Properties();
/*      */     }
/*  239 */     localProperties.put("is_connection_pooling", "true");
/*  240 */     localProperties.put("user", paramString1);
/*  241 */     localProperties.put("password", paramString2);
/*  242 */     localProperties.put("connection_pool", "connpool_connection");
/*  243 */     localProperties.put("connpool_object", this.m_connection_pool);
/*      */     
/*  245 */     OracleOCIConnection localOracleOCIConnection = (OracleOCIConnection)this.m_oracleDriver.connect(this.url, localProperties);
/*      */     
/*  247 */     if (localOracleOCIConnection == null)
/*      */     {
/*  249 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 67);
/*  250 */       localSQLException.fillInStackTrace();
/*  251 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  257 */     localOracleOCIConnection.setStmtCacheSize(this.m_stmtCacheSize, this.m_stmtClearMetaData);
/*      */     
/*  259 */     this.m_lconnections.put(localOracleOCIConnection, localOracleOCIConnection);
/*      */     
/*  261 */     localOracleOCIConnection.setConnectionPool(this);
/*      */     
/*  263 */     return localOracleOCIConnection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized Reference getReference()
/*      */     throws NamingException
/*      */   {
/*  276 */     Reference localReference = new Reference(getClass().getName(), "oracle.jdbc.pool.OracleDataSourceFactory", null);
/*      */     
/*      */ 
/*  279 */     super.addRefProperties(localReference);
/*      */     
/*      */ 
/*      */ 
/*  283 */     localReference.add(new StringRefAddr("connpool_min_limit", String.valueOf(this.m_conn_min_limit)));
/*      */     
/*      */ 
/*  286 */     localReference.add(new StringRefAddr("connpool_max_limit", String.valueOf(this.m_conn_max_limit)));
/*      */     
/*  288 */     localReference.add(new StringRefAddr("connpool_increment", String.valueOf(this.m_conn_increment)));
/*      */     
/*      */ 
/*  291 */     localReference.add(new StringRefAddr("connpool_active_size", String.valueOf(this.m_conn_active_size)));
/*      */     
/*      */ 
/*  294 */     localReference.add(new StringRefAddr("connpool_pool_size", String.valueOf(this.m_conn_pool_size)));
/*      */     
/*      */ 
/*  297 */     localReference.add(new StringRefAddr("connpool_timeout", String.valueOf(this.m_conn_timeout)));
/*      */     
/*      */ 
/*  300 */     localReference.add(new StringRefAddr("connpool_nowait", this.m_conn_nowait));
/*      */     
/*  302 */     localReference.add(new StringRefAddr("connpool_is_poolcreated", String.valueOf(isPoolCreated())));
/*      */     
/*      */ 
/*  305 */     localReference.add(new StringRefAddr("transactions_distributed", String.valueOf(isDistributedTransEnabled())));
/*      */     
/*      */ 
/*  308 */     return localReference;
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
/*      */   public synchronized OracleConnection getProxyConnection(String paramString, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  338 */     ensureOpen();
/*      */     
/*      */ 
/*      */ 
/*  342 */     if ("proxytype_user_name".equals(paramString)) {
/*  343 */       paramProperties.put("user", paramProperties.getProperty("proxy_user_name"));
/*  344 */     } else if ("proxytype_distinguished_name".equals(paramString)) {
/*  345 */       paramProperties.put("user", paramProperties.getProperty("proxy_distinguished_name"));
/*      */     }
/*  347 */     else if ("proxytype_certificate".equals(paramString)) {
/*  348 */       paramProperties.put("user", String.valueOf(paramProperties.getProperty("proxy_user_name")));
/*      */     }
/*      */     else
/*      */     {
/*  352 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 107, "null properties");
/*      */       
/*  354 */       ((SQLException)localObject).fillInStackTrace();
/*  355 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  358 */     paramProperties.put("is_connection_pooling", "true");
/*  359 */     paramProperties.put("proxytype", paramString);
/*      */     String[] arrayOfString;
/*  361 */     if ((arrayOfString = (String[])paramProperties.get("proxy_roles")) != null)
/*      */     {
/*  363 */       paramProperties.put("proxy_num_roles", new Integer(arrayOfString.length));
/*      */     }
/*      */     else
/*      */     {
/*  367 */       paramProperties.put("proxy_num_roles", new Integer(0));
/*      */     }
/*      */     
/*  370 */     paramProperties.put("connection_pool", "connpool_proxy_connection");
/*  371 */     paramProperties.put("connpool_object", this.m_connection_pool);
/*      */     
/*  373 */     Object localObject = (OracleOCIConnection)this.m_oracleDriver.connect(this.url, paramProperties);
/*      */     
/*      */ 
/*  376 */     if (localObject == null)
/*      */     {
/*  378 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 67);
/*  379 */       localSQLException.fillInStackTrace();
/*  380 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  385 */     ((OracleOCIConnection)localObject).setStmtCacheSize(this.m_stmtCacheSize, this.m_stmtClearMetaData);
/*      */     
/*  387 */     this.m_lconnections.put(localObject, localObject);
/*  388 */     ((OracleOCIConnection)localObject).setConnectionPool(this);
/*      */     
/*  390 */     return (OracleConnection)localObject;
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
/*      */   public synchronized OracleConnection getAliasedConnection(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  409 */     ensureOpen();
/*  410 */     Properties localProperties = new Properties();
/*      */     
/*  412 */     localProperties.put("is_connection_pooling", "true");
/*  413 */     localProperties.put("connection_id", paramArrayOfByte);
/*  414 */     localProperties.put("connection_pool", "connpool_alias_connection");
/*  415 */     localProperties.put("connpool_object", this.m_connection_pool);
/*      */     
/*  417 */     OracleOCIConnection localOracleOCIConnection = (OracleOCIConnection)this.m_oracleDriver.connect(this.url, localProperties);
/*      */     
/*  419 */     if (localOracleOCIConnection == null)
/*      */     {
/*  421 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 67);
/*  422 */       localSQLException.fillInStackTrace();
/*  423 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  427 */     localOracleOCIConnection.setStmtCacheSize(this.m_stmtCacheSize, this.m_stmtClearMetaData);
/*      */     
/*  429 */     this.m_lconnections.put(localOracleOCIConnection, localOracleOCIConnection);
/*  430 */     localOracleOCIConnection.setConnectionPool(this);
/*      */     
/*  432 */     return localOracleOCIConnection;
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
/*      */   public synchronized void close()
/*      */     throws SQLException
/*      */   {
/*  448 */     if (this.lifecycle != Lifecycle.OPEN) {
/*  449 */       return;
/*      */     }
/*  451 */     this.lifecycle = Lifecycle.CLOSING;
/*      */     
/*      */ 
/*  454 */     Iterator localIterator = this.m_lconnections.values().iterator();
/*      */     
/*  456 */     while (localIterator.hasNext())
/*      */     {
/*  458 */       OracleOCIConnection localOracleOCIConnection = (OracleOCIConnection)localIterator.next();
/*      */       
/*  460 */       if ((localOracleOCIConnection != null) && (localOracleOCIConnection != this.m_connection_pool))
/*      */       {
/*  462 */         localOracleOCIConnection.close();
/*      */       }
/*  464 */       localIterator.remove();
/*      */     }
/*      */     
/*      */ 
/*  468 */     this.m_connection_pool.close();
/*      */     
/*  470 */     this.lifecycle = Lifecycle.CLOSED;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void setPoolConfig(Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*      */     Object localObject;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  490 */     if (paramProperties == null)
/*      */     {
/*  492 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 106, "null properties");
/*  493 */       ((SQLException)localObject).fillInStackTrace();
/*  494 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  499 */     if (!isPoolCreated())
/*      */     {
/*  501 */       createConnectionPool(paramProperties);
/*      */     }
/*      */     else
/*      */     {
/*  505 */       localObject = new Properties();
/*      */       
/*  507 */       checkPoolConfig(paramProperties, (Properties)localObject);
/*      */       
/*  509 */       int[] arrayOfInt = new int[6];
/*      */       
/*  511 */       readPoolConfig((Properties)localObject, arrayOfInt);
/*      */       
/*      */ 
/*  514 */       this.m_connection_pool.setConnectionPoolInfo(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfInt[3], arrayOfInt[4], arrayOfInt[5]);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  520 */     storePoolProperties();
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
/*      */   public static void readPoolConfig(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2, int[] paramArrayOfInt)
/*      */   {
/*  541 */     for (int i = 0; i < 6; i++)
/*  542 */       paramArrayOfInt[i] = 0;
/*  543 */     paramArrayOfInt[0] = paramInt1;
/*  544 */     paramArrayOfInt[1] = paramInt2;
/*  545 */     paramArrayOfInt[2] = paramInt3;
/*  546 */     paramArrayOfInt[3] = paramInt4;
/*  547 */     if (paramBoolean1)
/*  548 */       paramArrayOfInt[4] = 1;
/*  549 */     if (paramBoolean2) {
/*  550 */       paramArrayOfInt[5] = 1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void readPoolConfig(Properties paramProperties, int[] paramArrayOfInt)
/*      */   {
/*  561 */     String str = paramProperties.getProperty("connpool_min_limit");
/*      */     
/*  563 */     if (str != null) {
/*  564 */       paramArrayOfInt[0] = Integer.parseInt(str);
/*      */     }
/*  566 */     str = paramProperties.getProperty("connpool_max_limit");
/*      */     
/*  568 */     if (str != null) {
/*  569 */       paramArrayOfInt[1] = Integer.parseInt(str);
/*      */     }
/*  571 */     str = paramProperties.getProperty("connpool_increment");
/*      */     
/*  573 */     if (str != null) {
/*  574 */       paramArrayOfInt[2] = Integer.parseInt(str);
/*      */     }
/*  576 */     str = paramProperties.getProperty("connpool_timeout");
/*      */     
/*  578 */     if (str != null) {
/*  579 */       paramArrayOfInt[3] = Integer.parseInt(str);
/*      */     }
/*  581 */     str = paramProperties.getProperty("connpool_nowait");
/*      */     
/*  583 */     if ((str != null) && (str.equalsIgnoreCase("true"))) {
/*  584 */       paramArrayOfInt[4] = 1;
/*      */     }
/*  586 */     str = paramProperties.getProperty("transactions_distributed");
/*      */     
/*  588 */     if ((str != null) && (str.equalsIgnoreCase("true"))) {
/*  589 */       paramArrayOfInt[5] = 1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void checkPoolConfig(Properties paramProperties1, Properties paramProperties2)
/*      */     throws SQLException
/*      */   {
/*  598 */     String str1 = (String)paramProperties1.get("transactions_distributed");
/*  599 */     String str2 = (String)paramProperties1.get("connpool_nowait");
/*      */     
/*  601 */     if (((str1 != null) && (!str1.equalsIgnoreCase("true"))) || ((str2 != null) && (!str2.equalsIgnoreCase("true"))) || (paramProperties1.get("connpool_min_limit") == null) || (paramProperties1.get("connpool_max_limit") == null) || (paramProperties1.get("connpool_increment") == null) || (Integer.decode((String)paramProperties1.get("connpool_min_limit")).intValue() < 0) || (Integer.decode((String)paramProperties1.get("connpool_max_limit")).intValue() < 0) || (Integer.decode((String)paramProperties1.get("connpool_increment")).intValue() < 0))
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  623 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 106, "");
/*  624 */       ((SQLException)localObject).fillInStackTrace();
/*  625 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  631 */     Object localObject = paramProperties1.propertyNames();
/*      */     
/*      */ 
/*      */ 
/*  635 */     while (((Enumeration)localObject).hasMoreElements())
/*      */     {
/*  637 */       String str3 = (String)((Enumeration)localObject).nextElement();
/*  638 */       String str4 = paramProperties1.getProperty(str3);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  643 */       if (("transactions_distributed".equals(str3)) || ("connpool_nowait".equals(str3))) {
/*  644 */         paramProperties2.put(str3, "true");
/*      */       } else {
/*  646 */         paramProperties2.put(str3, str4);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private synchronized void storePoolProperties()
/*      */     throws SQLException
/*      */   {
/*  655 */     Properties localProperties = getPoolConfig();
/*      */     
/*  657 */     this.m_conn_min_limit = Integer.decode(localProperties.getProperty("connpool_min_limit")).intValue();
/*      */     
/*  659 */     this.m_conn_max_limit = Integer.decode(localProperties.getProperty("connpool_max_limit")).intValue();
/*      */     
/*  661 */     this.m_conn_increment = Integer.decode(localProperties.getProperty("connpool_increment")).intValue();
/*      */     
/*  663 */     this.m_conn_active_size = Integer.decode(localProperties.getProperty("connpool_active_size")).intValue();
/*      */     
/*  665 */     this.m_conn_pool_size = Integer.decode(localProperties.getProperty("connpool_pool_size")).intValue();
/*      */     
/*  667 */     this.m_conn_timeout = Integer.decode(localProperties.getProperty("connpool_timeout")).intValue();
/*      */     
/*  669 */     this.m_conn_nowait = localProperties.getProperty("connpool_nowait");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized Properties getPoolConfig()
/*      */     throws SQLException
/*      */   {
/*  678 */     ensureOpen();
/*      */     
/*      */ 
/*      */ 
/*  682 */     Properties localProperties = this.m_connection_pool.getConnectionPoolInfo();
/*      */     
/*  684 */     localProperties.put("connpool_is_poolcreated", String.valueOf(isPoolCreated()));
/*      */     
/*  686 */     return localProperties;
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
/*      */   public synchronized int getActiveSize()
/*      */     throws SQLException
/*      */   {
/*  702 */     ensureOpen();
/*      */     
/*  704 */     Properties localProperties = this.m_connection_pool.getConnectionPoolInfo();
/*      */     
/*  706 */     String str = localProperties.getProperty("connpool_active_size");
/*  707 */     int i = Integer.decode(str).intValue();
/*      */     
/*  709 */     return i;
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
/*      */   public synchronized int getPoolSize()
/*      */     throws SQLException
/*      */   {
/*  725 */     ensureOpen();
/*      */     
/*  727 */     Properties localProperties = this.m_connection_pool.getConnectionPoolInfo();
/*      */     
/*  729 */     String str = localProperties.getProperty("connpool_pool_size");
/*  730 */     int i = Integer.decode(str).intValue();
/*      */     
/*  732 */     return i;
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
/*      */   public synchronized int getTimeout()
/*      */     throws SQLException
/*      */   {
/*  749 */     ensureOpen();
/*      */     
/*  751 */     Properties localProperties = this.m_connection_pool.getConnectionPoolInfo();
/*      */     
/*  753 */     String str = localProperties.getProperty("connpool_timeout");
/*  754 */     int i = Integer.decode(str).intValue();
/*      */     
/*  756 */     return i;
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
/*      */   public synchronized String getNoWait()
/*      */     throws SQLException
/*      */   {
/*  776 */     ensureOpen();
/*      */     
/*  778 */     Properties localProperties = this.m_connection_pool.getConnectionPoolInfo();
/*      */     
/*  780 */     return localProperties.getProperty("connpool_nowait");
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
/*      */   public synchronized int getMinLimit()
/*      */     throws SQLException
/*      */   {
/*  795 */     ensureOpen();
/*      */     
/*  797 */     Properties localProperties = this.m_connection_pool.getConnectionPoolInfo();
/*      */     
/*  799 */     String str = localProperties.getProperty("connpool_min_limit");
/*  800 */     int i = Integer.decode(str).intValue();
/*      */     
/*  802 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized int getMaxLimit()
/*      */     throws SQLException
/*      */   {
/*  815 */     ensureOpen();
/*      */     
/*  817 */     Properties localProperties = this.m_connection_pool.getConnectionPoolInfo();
/*      */     
/*  819 */     String str = localProperties.getProperty("connpool_max_limit");
/*  820 */     int i = Integer.decode(str).intValue();
/*      */     
/*  822 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized int getConnectionIncrement()
/*      */     throws SQLException
/*      */   {
/*  835 */     ensureOpen();
/*      */     
/*  837 */     Properties localProperties = this.m_connection_pool.getConnectionPoolInfo();
/*      */     
/*  839 */     String str = localProperties.getProperty("connpool_increment");
/*  840 */     int i = Integer.decode(str).intValue();
/*      */     
/*  842 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean isDistributedTransEnabled()
/*      */   {
/*  851 */     if (this.m_is_transactions_distributed == 1) {
/*  852 */       return true;
/*      */     }
/*  854 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void createConnectionPool(Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  866 */     if (this.lifecycle != Lifecycle.NEW) {
/*  867 */       return;
/*      */     }
/*  869 */     if ((this.user == null) || (this.password == null))
/*      */     {
/*      */ 
/*  872 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 106, " ");
/*  873 */       ((SQLException)localObject).fillInStackTrace();
/*  874 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  879 */     Object localObject = new Properties();
/*      */     
/*      */ 
/*  882 */     if (paramProperties != null) {
/*  883 */       checkPoolConfig(paramProperties, (Properties)localObject);
/*      */     }
/*  885 */     ((Properties)localObject).put("is_connection_pooling", "true");
/*  886 */     ((Properties)localObject).put("user", this.user);
/*  887 */     ((Properties)localObject).put("password", this.password);
/*  888 */     ((Properties)localObject).put("connection_pool", "connection_pool");
/*      */     
/*  890 */     if (getURL() == null) {
/*  891 */       makeURL();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  897 */     this.m_connection_pool = ((OracleOCIConnection)this.m_oracleDriver.connect(this.url, (Properties)localObject));
/*      */     
/*  899 */     if (this.m_connection_pool == null)
/*      */     {
/*  901 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 67);
/*  902 */       localSQLException.fillInStackTrace();
/*  903 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  910 */     this.m_connection_pool.setConnectionPool(this);
/*      */     
/*      */ 
/*  913 */     this.m_lconnections.put(this.m_connection_pool, this.m_connection_pool);
/*      */     
/*  915 */     this.lifecycle = Lifecycle.OPEN;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  921 */     storePoolProperties();
/*      */     
/*  923 */     if (paramProperties != null)
/*      */     {
/*  925 */       if ("true".equalsIgnoreCase(paramProperties.getProperty("transactions_distributed"))) {
/*  926 */         this.m_is_transactions_distributed = 1;
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
/*      */   public synchronized boolean isPoolCreated()
/*      */   {
/*  941 */     return this.lifecycle == Lifecycle.OPEN;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void connectionClosed(OracleOCIConnection paramOracleOCIConnection)
/*      */     throws SQLException
/*      */   {
/*  950 */     if (this.lifecycle != Lifecycle.CLOSING)
/*      */     {
/*  952 */       if (this.m_lconnections.remove(paramOracleOCIConnection) == null)
/*      */       {
/*  954 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "internal OracleOCIConnectionPool error");
/*  955 */         localSQLException.fillInStackTrace();
/*  956 */         throw localSQLException;
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
/*      */   public synchronized void setStmtCacheSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  980 */     setStmtCacheSize(paramInt, false);
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
/*      */   public synchronized void setStmtCacheSize(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1005 */     if (paramInt < 0)
/*      */     {
/* 1007 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 1008 */       localSQLException.fillInStackTrace();
/* 1009 */       throw localSQLException;
/*      */     }
/*      */     
/* 1012 */     this.m_stmtCacheSize = paramInt;
/* 1013 */     this.m_stmtClearMetaData = paramBoolean;
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
/*      */   public synchronized int getStmtCacheSize()
/*      */   {
/* 1026 */     return this.m_stmtCacheSize;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean isStmtCacheEnabled()
/*      */   {
/* 1038 */     if (this.m_stmtCacheSize > 0) {
/* 1039 */       return true;
/*      */     }
/* 1041 */     return false;
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
/*      */   protected OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 1057 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1062 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleOCIConnectionPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */