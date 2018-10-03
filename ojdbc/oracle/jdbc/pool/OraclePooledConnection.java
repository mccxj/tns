/*      */ package oracle.jdbc.pool;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.Connection;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.SQLException;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import javax.sql.ConnectionEvent;
/*      */ import javax.sql.ConnectionEventListener;
/*      */ import javax.sql.PooledConnection;
/*      */ import javax.sql.StatementEventListener;
/*      */ import javax.transaction.xa.XAResource;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.driver.OracleCloseCallback;
/*      */ import oracle.jdbc.driver.OracleDriver;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class OraclePooledConnection
/*      */   implements PooledConnection, Serializable
/*      */ {
/*      */   static final long serialVersionUID = -203725628718322873L;
/*      */   public static final String url_string = "connection_url";
/*      */   public static final String pool_auto_commit_string = "pool_auto_commit";
/*      */   public static final String object_type_map = "obj_type_map";
/*      */   public static final String transaction_isolation = "trans_isolation";
/*      */   public static final String statement_cache_size = "stmt_cache_size";
/*      */   public static final String isClearMetaData = "stmt_cache_clear_metadata";
/*      */   public static final String ImplicitStatementCachingEnabled = "ImplicitStatementCachingEnabled";
/*      */   public static final String ExplicitStatementCachingEnabled = "ExplicitStatementCachingEnabled";
/*      */   public static final String LoginTimeout = "LoginTimeout";
/*      */   public static final String connect_auto_commit_string = "connect_auto_commit";
/*      */   public static final String implicit_caching_enabled = "implicit_cache_enabled";
/*      */   public static final String explicit_caching_enabled = "explict_cache_enabled";
/*      */   public static final String connection_properties_string = "connection_properties";
/*      */   public static final String event_listener_string = "event_listener";
/*      */   public static final String sql_exception_string = "sql_exception";
/*      */   public static final String close_callback_string = "close_callback";
/*      */   public static final String private_data = "private_data";
/*      */   static final int CONNECTION_CLOSED_EVENT = 101;
/*      */   static final int CONNECTION_ERROROCCURED_EVENT = 102;
/*   84 */   private Hashtable eventListeners = null;
/*   85 */   private SQLException sqlException = null;
/*   86 */   protected boolean autoCommit = true;
/*      */   
/*      */ 
/*      */ 
/*   90 */   private ConnectionEventListener iccEventListener = null;
/*      */   
/*      */ 
/*   93 */   protected transient OracleConnection logicalHandle = null;
/*      */   
/*      */ 
/*   96 */   protected transient OracleConnection physicalConn = null;
/*      */   
/*   98 */   private Hashtable connectionProperty = null;
/*      */   
/*  100 */   public Properties cachedConnectionAttributes = null;
/*  101 */   public Properties unMatchedCachedConnAttr = null;
/*  102 */   public int closeOption = 0;
/*      */   
/*  104 */   private String pcKey = null;
/*      */   
/*      */ 
/*  107 */   private OracleCloseCallback closeCallback = null;
/*  108 */   private Object privateData = null;
/*      */   
/*      */ 
/*  111 */   private long lastAccessedTime = 0L;
/*      */   
/*      */ 
/*  114 */   protected String dataSourceInstanceNameKey = null;
/*  115 */   protected String dataSourceHostNameKey = null;
/*  116 */   protected String dataSourceDbUniqNameKey = null;
/*  117 */   protected boolean connectionMarkedDown = false;
/*  118 */   protected boolean needToAbort = false;
/*      */   
/*  120 */   protected transient OracleDriver oracleDriver = new OracleDriver();
/*  121 */   boolean localTxnCommitOnClose = false;
/*      */   
/*      */ 
/*      */   public OraclePooledConnection()
/*      */   {
/*  126 */     this((Connection)null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OraclePooledConnection(String paramString)
/*      */     throws SQLException
/*      */   {
/*  139 */     Connection localConnection = this.oracleDriver.connect(paramString, new Properties());
/*      */     
/*  141 */     if (localConnection == null)
/*      */     {
/*  143 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 67);
/*  144 */       localSQLException.fillInStackTrace();
/*  145 */       throw localSQLException;
/*      */     }
/*      */     
/*  148 */     initialize(localConnection);
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
/*      */   public OraclePooledConnection(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/*  163 */     Properties localProperties = new Properties();
/*      */     
/*  165 */     localProperties.put("user", paramString2);
/*  166 */     localProperties.put("password", paramString3);
/*  167 */     Connection localConnection = this.oracleDriver.connect(paramString1, localProperties);
/*  168 */     if (localConnection == null)
/*      */     {
/*  170 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 67);
/*  171 */       localSQLException.fillInStackTrace();
/*  172 */       throw localSQLException;
/*      */     }
/*      */     
/*  175 */     initialize(localConnection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OraclePooledConnection(Connection paramConnection)
/*      */   {
/*  187 */     initialize(paramConnection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OraclePooledConnection(Connection paramConnection, boolean paramBoolean)
/*      */   {
/*  199 */     this(paramConnection);
/*      */     
/*  201 */     this.autoCommit = paramBoolean;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void initialize(Connection paramConnection)
/*      */   {
/*  210 */     this.physicalConn = ((OracleConnection)paramConnection);
/*  211 */     this.eventListeners = new Hashtable(10);
/*      */     
/*  213 */     this.closeCallback = null;
/*  214 */     this.privateData = null;
/*  215 */     this.lastAccessedTime = 0L;
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
/*      */   public synchronized void addConnectionEventListener(ConnectionEventListener paramConnectionEventListener)
/*      */   {
/*  230 */     if (this.eventListeners == null) {
/*  231 */       this.sqlException = new SQLException("Listener Hashtable Null");
/*      */     } else {
/*  233 */       this.eventListeners.put(paramConnectionEventListener, paramConnectionEventListener);
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
/*      */   public synchronized void close()
/*      */     throws SQLException
/*      */   {
/*  247 */     if (this.closeCallback != null) {
/*  248 */       this.closeCallback.beforeClose(this.physicalConn, this.privateData);
/*      */     }
/*  250 */     if (this.physicalConn != null)
/*      */     {
/*      */       try
/*      */       {
/*  254 */         this.physicalConn.close();
/*      */       }
/*      */       catch (SQLException localSQLException) {}
/*      */       
/*      */ 
/*      */ 
/*  260 */       this.physicalConn = null;
/*      */     }
/*      */     
/*  263 */     if (this.closeCallback != null) {
/*  264 */       this.closeCallback.afterClose(this.privateData);
/*      */     }
/*      */     
/*  267 */     this.lastAccessedTime = 0L;
/*  268 */     this.iccEventListener = null;
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
/*      */   public synchronized Connection getConnection()
/*      */     throws SQLException
/*      */   {
/*  285 */     if (this.physicalConn == null)
/*      */     {
/*  287 */       this.sqlException = new SQLException("Physical Connection doesn't exist");
/*      */       
/*      */ 
/*  290 */       callListener(102);
/*      */       
/*      */ 
/*  293 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  294 */       localSQLException1.fillInStackTrace();
/*  295 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  303 */       if (this.logicalHandle != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  308 */         this.logicalHandle.closeInternal(false);
/*      */       }
/*      */       
/*      */ 
/*  312 */       this.logicalHandle = ((OracleConnection)this.physicalConn.getLogicalConnection(this, this.autoCommit));
/*      */ 
/*      */     }
/*      */     catch (SQLException localSQLException2)
/*      */     {
/*      */ 
/*  318 */       this.sqlException = localSQLException2;
/*      */       
/*  320 */       callListener(102);
/*      */       
/*  322 */       callImplicitCacheListener(102);
/*      */       
/*  324 */       SQLException localSQLException3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8, "OraclePooledConnection.getConnection() - SQLException Ocurred:" + localSQLException2.getMessage());
/*      */       
/*  326 */       localSQLException3.fillInStackTrace();
/*  327 */       throw localSQLException3;
/*      */     }
/*      */     
/*      */ 
/*  331 */     return this.logicalHandle;
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
/*      */   public Connection getLogicalHandle()
/*      */     throws SQLException
/*      */   {
/*  345 */     return this.logicalHandle;
/*      */   }
/*      */   
/*      */ 
/*      */   public Connection getPhysicalHandle()
/*      */     throws SQLException
/*      */   {
/*  352 */     return this.physicalConn;
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
/*      */   public synchronized void setLastAccessedTime(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  368 */     this.lastAccessedTime = paramLong;
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
/*      */   public long getLastAccessedTime()
/*      */     throws SQLException
/*      */   {
/*  382 */     return this.lastAccessedTime;
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
/*      */   public synchronized void registerCloseCallback(OracleCloseCallback paramOracleCloseCallback, Object paramObject)
/*      */   {
/*  397 */     this.closeCallback = paramOracleCloseCallback;
/*  398 */     this.privateData = paramObject;
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
/*      */   public synchronized void removeConnectionEventListener(ConnectionEventListener paramConnectionEventListener)
/*      */   {
/*  412 */     if (this.eventListeners == null) {
/*  413 */       this.sqlException = new SQLException("Listener Hashtable Null");
/*      */     } else {
/*  415 */       this.eventListeners.remove(paramConnectionEventListener);
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
/*      */   public synchronized void registerImplicitCacheConnectionEventListener(ConnectionEventListener paramConnectionEventListener)
/*      */   {
/*  429 */     if (this.iccEventListener != null) {
/*  430 */       this.sqlException = new SQLException("Implicit cache listeneralready registered");
/*      */     }
/*      */     else {
/*  433 */       this.iccEventListener = paramConnectionEventListener;
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
/*      */   public void logicalCloseForImplicitConnectionCache()
/*      */   {
/*  456 */     if (this.closeOption == 4096)
/*      */     {
/*  458 */       callImplicitCacheListener(102);
/*      */     }
/*      */     else
/*      */     {
/*  462 */       callImplicitCacheListener(101);
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
/*      */   public void logicalClose()
/*      */   {
/*  476 */     if (this.cachedConnectionAttributes != null)
/*      */     {
/*  478 */       logicalCloseForImplicitConnectionCache();
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  483 */       callListener(101);
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
/*      */   private void callListener(int paramInt)
/*      */   {
/*  498 */     if (this.eventListeners == null) {
/*  499 */       return;
/*      */     }
/*      */     
/*      */ 
/*  503 */     Enumeration localEnumeration = this.eventListeners.keys();
/*      */     
/*  505 */     ConnectionEvent localConnectionEvent = new ConnectionEvent(this, this.sqlException);
/*      */     
/*  507 */     while (localEnumeration.hasMoreElements())
/*      */     {
/*  509 */       ConnectionEventListener localConnectionEventListener1 = (ConnectionEventListener)localEnumeration.nextElement();
/*      */       
/*  511 */       ConnectionEventListener localConnectionEventListener2 = (ConnectionEventListener)this.eventListeners.get(localConnectionEventListener1);
/*      */       
/*      */ 
/*      */ 
/*  515 */       if (paramInt == 101) {
/*  516 */         localConnectionEventListener2.connectionClosed(localConnectionEvent);
/*  517 */       } else if (paramInt == 102) {
/*  518 */         localConnectionEventListener2.connectionErrorOccurred(localConnectionEvent);
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
/*      */   private void callImplicitCacheListener(int paramInt)
/*      */   {
/*  532 */     if (this.iccEventListener == null) {
/*  533 */       return;
/*      */     }
/*  535 */     ConnectionEvent localConnectionEvent = new ConnectionEvent(this, this.sqlException);
/*      */     
/*      */ 
/*  538 */     switch (paramInt)
/*      */     {
/*      */ 
/*      */ 
/*      */     case 101: 
/*  543 */       this.iccEventListener.connectionClosed(localConnectionEvent);
/*      */       
/*  545 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 102: 
/*  550 */       this.iccEventListener.connectionErrorOccurred(localConnectionEvent);
/*      */     }
/*      */     
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public synchronized void setStmtCacheSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  579 */     setStmtCacheSize(paramInt, false);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public synchronized void setStmtCacheSize(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  605 */     if (paramInt < 0)
/*      */     {
/*  607 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  608 */       localSQLException.fillInStackTrace();
/*  609 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  613 */     if (this.physicalConn != null) {
/*  614 */       this.physicalConn.setStmtCacheSize(paramInt, paramBoolean);
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
/*      */   public synchronized int getStmtCacheSize()
/*      */   {
/*  629 */     if (this.physicalConn != null) {
/*  630 */       return this.physicalConn.getStmtCacheSize();
/*      */     }
/*  632 */     return 0;
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
/*      */   public void setStatementCacheSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  651 */     if (this.physicalConn != null) {
/*  652 */       this.physicalConn.setStatementCacheSize(paramInt);
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
/*      */   public int getStatementCacheSize()
/*      */     throws SQLException
/*      */   {
/*  669 */     if (this.physicalConn != null) {
/*  670 */       return this.physicalConn.getStatementCacheSize();
/*      */     }
/*  672 */     return 0;
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
/*      */   public void setImplicitCachingEnabled(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  693 */     if (this.physicalConn != null) {
/*  694 */       this.physicalConn.setImplicitCachingEnabled(paramBoolean);
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
/*      */   public boolean getImplicitCachingEnabled()
/*      */     throws SQLException
/*      */   {
/*  710 */     if (this.physicalConn != null) {
/*  711 */       return this.physicalConn.getImplicitCachingEnabled();
/*      */     }
/*  713 */     return false;
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
/*      */   public void setExplicitCachingEnabled(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  734 */     if (this.physicalConn != null) {
/*  735 */       this.physicalConn.setExplicitCachingEnabled(paramBoolean);
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
/*      */   public boolean getExplicitCachingEnabled()
/*      */     throws SQLException
/*      */   {
/*  751 */     if (this.physicalConn != null) {
/*  752 */       return this.physicalConn.getExplicitCachingEnabled();
/*      */     }
/*      */     
/*  755 */     return false;
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
/*      */   public void purgeImplicitCache()
/*      */     throws SQLException
/*      */   {
/*  772 */     if (this.physicalConn != null) {
/*  773 */       this.physicalConn.purgeImplicitCache();
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
/*      */   public void purgeExplicitCache()
/*      */     throws SQLException
/*      */   {
/*  790 */     if (this.physicalConn != null) {
/*  791 */       this.physicalConn.purgeExplicitCache();
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
/*      */   public PreparedStatement getStatementWithKey(String paramString)
/*      */     throws SQLException
/*      */   {
/*  812 */     if (this.physicalConn != null) {
/*  813 */       return this.physicalConn.getStatementWithKey(paramString);
/*      */     }
/*      */     
/*  816 */     return null;
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
/*      */   public CallableStatement getCallWithKey(String paramString)
/*      */     throws SQLException
/*      */   {
/*  837 */     if (this.physicalConn != null) {
/*  838 */       return this.physicalConn.getCallWithKey(paramString);
/*      */     }
/*      */     
/*  841 */     return null;
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
/*      */   public boolean isStatementCacheInitialized()
/*      */   {
/*  854 */     if (this.physicalConn != null) {
/*  855 */       return this.physicalConn.isStatementCacheInitialized();
/*      */     }
/*      */     
/*  858 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public final void setProperties(Hashtable paramHashtable)
/*      */   {
/*  866 */     this.connectionProperty = paramHashtable;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final void setUserName(String paramString1, String paramString2)
/*      */   {
/*  876 */     this.pcKey = generateKey(paramString1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static final String generateKey(String paramString1, String paramString2)
/*      */   {
/*  883 */     return paramString1.toUpperCase() + paramString2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final OracleConnectionCacheEntry addToImplicitCache(HashMap paramHashMap, OracleConnectionCacheEntry paramOracleConnectionCacheEntry)
/*      */   {
/*  894 */     return (OracleConnectionCacheEntry)paramHashMap.put(this.pcKey, paramOracleConnectionCacheEntry);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final OracleConnectionCacheEntry removeFromImplictCache(HashMap paramHashMap)
/*      */   {
/*  905 */     return (OracleConnectionCacheEntry)paramHashMap.get(this.pcKey);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final boolean isSameUser(String paramString1, String paramString2)
/*      */   {
/*  916 */     return (paramString1 != null) && (paramString2 != null) && (this.pcKey.equalsIgnoreCase(paramString1 + paramString2));
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
/*      */   public XAResource getXAResource()
/*      */     throws SQLException
/*      */   {
/*  932 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  933 */     localSQLException.fillInStackTrace();
/*  934 */     throw localSQLException;
/*      */   }
/*      */   
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
/*  947 */     paramObjectOutputStream.defaultWriteObject();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  956 */       this.physicalConn.getPropertyForPooledConnection(this);
/*      */       
/*  958 */       if (this.eventListeners != null) {
/*  959 */         this.connectionProperty.put("event_listener", this.eventListeners);
/*      */       }
/*  961 */       if (this.sqlException != null) {
/*  962 */         this.connectionProperty.put("sql_exception", this.sqlException);
/*      */       }
/*  964 */       this.connectionProperty.put("pool_auto_commit", "" + this.autoCommit);
/*      */       
/*  966 */       if (this.closeCallback != null) {
/*  967 */         this.connectionProperty.put("close_callback", this.closeCallback);
/*      */       }
/*  969 */       if (this.privateData != null) {
/*  970 */         this.connectionProperty.put("private_data", this.privateData);
/*      */       }
/*  972 */       paramObjectOutputStream.writeObject(this.connectionProperty);
/*  973 */       this.physicalConn.close();
/*      */     }
/*      */     catch (SQLException localSQLException) {}
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
/*  991 */     paramObjectInputStream.defaultReadObject();
/*      */     
/*  993 */     this.connectionProperty = ((Hashtable)paramObjectInputStream.readObject());
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/* 1000 */       Properties localProperties = (Properties)this.connectionProperty.get("connection_properties");
/*      */       
/* 1002 */       String str1 = localProperties.getProperty("connection_url");
/*      */       
/* 1004 */       this.oracleDriver = new OracleDriver();
/*      */       
/* 1006 */       Connection localConnection = this.oracleDriver.connect(str1, localProperties);
/*      */       
/*      */ 
/*      */ 
/* 1010 */       initialize(localConnection);
/*      */       
/* 1012 */       this.eventListeners = ((Hashtable)this.connectionProperty.get("event_listener"));
/*      */       
/* 1014 */       this.sqlException = ((SQLException)this.connectionProperty.get("sql_exception"));
/*      */       
/* 1016 */       this.autoCommit = ((String)this.connectionProperty.get("pool_auto_commit")).equals("true");
/*      */       
/* 1018 */       this.closeCallback = ((OracleCloseCallback)this.connectionProperty.get("close_callback"));
/*      */       
/* 1020 */       this.privateData = this.connectionProperty.get("private_data");
/*      */       
/* 1022 */       Map localMap = (Map)this.connectionProperty.get("obj_type_map");
/*      */       
/*      */ 
/* 1025 */       if (localMap != null) {
/* 1026 */         ((OracleConnection)localConnection).setTypeMap(localMap);
/*      */       }
/* 1028 */       String str2 = localProperties.getProperty("trans_isolation");
/*      */       
/* 1030 */       localConnection.setTransactionIsolation(Integer.parseInt(str2));
/*      */       
/* 1032 */       str2 = localProperties.getProperty("stmt_cache_size");
/*      */       
/* 1034 */       int i = Integer.parseInt(str2);
/*      */       
/* 1036 */       if (i != -1)
/*      */       {
/* 1038 */         setStatementCacheSize(i);
/*      */         
/* 1040 */         str2 = localProperties.getProperty("implicit_cache_enabled");
/* 1041 */         if ((str2 != null) && (str2.equalsIgnoreCase("true"))) {
/* 1042 */           setImplicitCachingEnabled(true);
/*      */         } else {
/* 1044 */           setImplicitCachingEnabled(false);
/*      */         }
/* 1046 */         str2 = localProperties.getProperty("explict_cache_enabled");
/* 1047 */         if ((str2 != null) && (str2.equalsIgnoreCase("true"))) {
/* 1048 */           setExplicitCachingEnabled(true);
/*      */         } else
/* 1050 */           setExplicitCachingEnabled(false);
/*      */       }
/* 1052 */       this.physicalConn.setAutoCommit(((String)localProperties.get("connect_auto_commit")).equals("true"));
/*      */     }
/*      */     catch (Exception localException) {}
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
/*      */   public void addStatementEventListener(StatementEventListener paramStatementEventListener) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void removeStatementEventListener(StatementEventListener paramStatementEventListener) {}
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
/* 1089 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1094 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OraclePooledConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */