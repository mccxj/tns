/*      */ package oracle.jdbc;
/*      */ 
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationHandler;
/*      */ import java.lang.reflect.Method;
/*      */ import java.lang.reflect.Proxy;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.sql.Array;
/*      */ import java.sql.Blob;
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Connection;
/*      */ import java.sql.DatabaseMetaData;
/*      */ import java.sql.Date;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.SQLClientInfoException;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLXML;
/*      */ import java.sql.Savepoint;
/*      */ import java.sql.Statement;
/*      */ import java.sql.Struct;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.EnumSet;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.TimeZone;
/*      */ import oracle.jdbc.aq.AQDequeueOptions;
/*      */ import oracle.jdbc.aq.AQEnqueueOptions;
/*      */ import oracle.jdbc.aq.AQMessage;
/*      */ import oracle.jdbc.aq.AQNotificationRegistration;
/*      */ import oracle.jdbc.dcn.DatabaseChangeRegistration;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.pool.OracleConnectionCacheCallback;
/*      */ import oracle.sql.BINARY_DOUBLE;
/*      */ import oracle.sql.BINARY_FLOAT;
/*      */ import oracle.sql.DATE;
/*      */ import oracle.sql.NUMBER;
/*      */ import oracle.sql.TIMESTAMP;
/*      */ import oracle.sql.TIMESTAMPLTZ;
/*      */ import oracle.sql.TIMESTAMPTZ;
/*      */ import oracle.sql.TypeDescriptor;
/*      */ 
/*      */ public class OracleConnectionWrapper implements OracleConnection
/*      */ {
/*      */   protected OracleConnection connection;
/*      */   
/*      */   public OracleConnectionWrapper() {}
/*      */   
/*      */   public OracleConnectionWrapper(OracleConnection paramOracleConnection)
/*      */   {
/*   55 */     this.connection = paramOracleConnection;
/*      */     
/*   57 */     paramOracleConnection.setWrapper(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleConnection unwrap()
/*      */   {
/*   67 */     return this.connection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public oracle.jdbc.internal.OracleConnection physicalConnectionWithin()
/*      */   {
/*   75 */     return this.connection.physicalConnectionWithin();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getDatabaseTimeZone()
/*      */     throws SQLException
/*      */   {
/*   83 */     return physicalConnectionWithin().getDatabaseTimeZone();
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
/*      */   public void setWrapper(OracleConnection paramOracleConnection)
/*      */   {
/*   99 */     this.connection.setWrapper(paramOracleConnection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Statement createStatement()
/*      */     throws SQLException
/*      */   {
/*  110 */     return this.connection.createStatement();
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String paramString) throws SQLException
/*      */   {
/*  115 */     return this.connection.prepareStatement(paramString);
/*      */   }
/*      */   
/*      */   public CallableStatement prepareCall(String paramString) throws SQLException
/*      */   {
/*  120 */     return this.connection.prepareCall(paramString);
/*      */   }
/*      */   
/*      */   public String nativeSQL(String paramString) throws SQLException
/*      */   {
/*  125 */     return this.connection.nativeSQL(paramString);
/*      */   }
/*      */   
/*      */   public void setAutoCommit(boolean paramBoolean) throws SQLException
/*      */   {
/*  130 */     this.connection.setAutoCommit(paramBoolean);
/*      */   }
/*      */   
/*      */   public boolean getAutoCommit() throws SQLException
/*      */   {
/*  135 */     return this.connection.getAutoCommit();
/*      */   }
/*      */   
/*      */   public void commit() throws SQLException
/*      */   {
/*  140 */     this.connection.commit();
/*      */   }
/*      */   
/*      */   public void rollback() throws SQLException
/*      */   {
/*  145 */     this.connection.rollback();
/*      */   }
/*      */   
/*      */   public void close() throws SQLException
/*      */   {
/*  150 */     this.connection.close();
/*      */   }
/*      */   
/*      */   public boolean isClosed() throws SQLException
/*      */   {
/*  155 */     return this.connection.isClosed();
/*      */   }
/*      */   
/*      */   public DatabaseMetaData getMetaData() throws SQLException
/*      */   {
/*  160 */     return this.connection.getMetaData();
/*      */   }
/*      */   
/*      */   public void setReadOnly(boolean paramBoolean) throws SQLException
/*      */   {
/*  165 */     this.connection.setReadOnly(paramBoolean);
/*      */   }
/*      */   
/*      */   public boolean isReadOnly() throws SQLException
/*      */   {
/*  170 */     return this.connection.isReadOnly();
/*      */   }
/*      */   
/*      */   public void setCatalog(String paramString) throws SQLException
/*      */   {
/*  175 */     this.connection.setCatalog(paramString);
/*      */   }
/*      */   
/*      */   public String getCatalog() throws SQLException
/*      */   {
/*  180 */     return this.connection.getCatalog();
/*      */   }
/*      */   
/*      */   public void setTransactionIsolation(int paramInt) throws SQLException
/*      */   {
/*  185 */     this.connection.setTransactionIsolation(paramInt);
/*      */   }
/*      */   
/*      */   public int getTransactionIsolation() throws SQLException
/*      */   {
/*  190 */     return this.connection.getTransactionIsolation();
/*      */   }
/*      */   
/*      */   public java.sql.SQLWarning getWarnings() throws SQLException
/*      */   {
/*  195 */     return this.connection.getWarnings();
/*      */   }
/*      */   
/*      */   public void clearWarnings() throws SQLException
/*      */   {
/*  200 */     this.connection.clearWarnings();
/*      */   }
/*      */   
/*      */   public Statement createStatement(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  206 */     return this.connection.createStatement(paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String paramString, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  212 */     return this.connection.prepareStatement(paramString, paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */   public CallableStatement prepareCall(String paramString, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  219 */     return this.connection.prepareCall(paramString, paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */   public Map getTypeMap() throws SQLException
/*      */   {
/*  224 */     return this.connection.getTypeMap();
/*      */   }
/*      */   
/*      */   public void setTypeMap(Map paramMap) throws SQLException
/*      */   {
/*  229 */     this.connection.setTypeMap(paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isProxySession()
/*      */   {
/*  238 */     return this.connection.isProxySession();
/*      */   }
/*      */   
/*      */   public void openProxySession(int paramInt, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  244 */     this.connection.openProxySession(paramInt, paramProperties);
/*      */   }
/*      */   
/*      */   public void archive(int paramInt1, int paramInt2, String paramString) throws SQLException
/*      */   {
/*  249 */     this.connection.archive(paramInt1, paramInt2, paramString);
/*      */   }
/*      */   
/*      */   public boolean getAutoClose() throws SQLException
/*      */   {
/*  254 */     return this.connection.getAutoClose();
/*      */   }
/*      */   
/*      */   public CallableStatement getCallWithKey(String paramString) throws SQLException
/*      */   {
/*  259 */     return this.connection.getCallWithKey(paramString);
/*      */   }
/*      */   
/*      */   public int getDefaultExecuteBatch()
/*      */   {
/*  264 */     return this.connection.getDefaultExecuteBatch();
/*      */   }
/*      */   
/*      */   public int getDefaultRowPrefetch()
/*      */   {
/*  269 */     return this.connection.getDefaultRowPrefetch();
/*      */   }
/*      */   
/*      */   public Object getDescriptor(String paramString)
/*      */   {
/*  274 */     return this.connection.getDescriptor(paramString);
/*      */   }
/*      */   
/*      */   public String[] getEndToEndMetrics() throws SQLException
/*      */   {
/*  279 */     return this.connection.getEndToEndMetrics();
/*      */   }
/*      */   
/*      */   public short getEndToEndECIDSequenceNumber() throws SQLException
/*      */   {
/*  284 */     return this.connection.getEndToEndECIDSequenceNumber();
/*      */   }
/*      */   
/*      */   public boolean getIncludeSynonyms()
/*      */   {
/*  289 */     return this.connection.getIncludeSynonyms();
/*      */   }
/*      */   
/*      */   public boolean getRestrictGetTables()
/*      */   {
/*  294 */     return this.connection.getRestrictGetTables();
/*      */   }
/*      */   
/*      */   public boolean getImplicitCachingEnabled() throws SQLException
/*      */   {
/*  299 */     return this.connection.getImplicitCachingEnabled();
/*      */   }
/*      */   
/*      */   public boolean getExplicitCachingEnabled() throws SQLException
/*      */   {
/*  304 */     return this.connection.getExplicitCachingEnabled();
/*      */   }
/*      */   
/*      */   public Object getJavaObject(String paramString)
/*      */     throws SQLException
/*      */   {
/*  310 */     return this.connection.getJavaObject(paramString);
/*      */   }
/*      */   
/*      */   public boolean getRemarksReporting()
/*      */   {
/*  315 */     return this.connection.getRemarksReporting();
/*      */   }
/*      */   
/*      */   public String getSQLType(Object paramObject) throws SQLException
/*      */   {
/*  320 */     return this.connection.getSQLType(paramObject);
/*      */   }
/*      */   
/*      */   public int getStmtCacheSize()
/*      */   {
/*  325 */     return this.connection.getStmtCacheSize();
/*      */   }
/*      */   
/*      */   public int getStatementCacheSize() throws SQLException
/*      */   {
/*  330 */     return this.connection.getStatementCacheSize();
/*      */   }
/*      */   
/*      */   public PreparedStatement getStatementWithKey(String paramString) throws SQLException
/*      */   {
/*  335 */     return this.connection.getStatementWithKey(paramString);
/*      */   }
/*      */   
/*      */   public short getStructAttrCsId() throws SQLException
/*      */   {
/*  340 */     return this.connection.getStructAttrCsId();
/*      */   }
/*      */   
/*      */   public String getUserName() throws SQLException
/*      */   {
/*  345 */     return this.connection.getUserName();
/*      */   }
/*      */   
/*      */   public String getCurrentSchema() throws SQLException
/*      */   {
/*  350 */     return this.connection.getCurrentSchema();
/*      */   }
/*      */   
/*      */   public boolean getUsingXAFlag()
/*      */   {
/*  355 */     return this.connection.getUsingXAFlag();
/*      */   }
/*      */   
/*      */   public boolean getXAErrorFlag()
/*      */   {
/*  360 */     return this.connection.getXAErrorFlag();
/*      */   }
/*      */   
/*      */   public OracleSavepoint oracleSetSavepoint() throws SQLException
/*      */   {
/*  365 */     return this.connection.oracleSetSavepoint();
/*      */   }
/*      */   
/*      */   public OracleSavepoint oracleSetSavepoint(String paramString) throws SQLException
/*      */   {
/*  370 */     return this.connection.oracleSetSavepoint(paramString);
/*      */   }
/*      */   
/*      */   public void oracleRollback(OracleSavepoint paramOracleSavepoint) throws SQLException
/*      */   {
/*  375 */     this.connection.oracleRollback(paramOracleSavepoint);
/*      */   }
/*      */   
/*      */   public void oracleReleaseSavepoint(OracleSavepoint paramOracleSavepoint)
/*      */     throws SQLException
/*      */   {
/*  381 */     this.connection.oracleReleaseSavepoint(paramOracleSavepoint);
/*      */   }
/*      */   
/*      */   public int pingDatabase()
/*      */     throws SQLException
/*      */   {
/*  387 */     return this.connection.pingDatabase();
/*      */   }
/*      */   
/*      */   public int pingDatabase(int paramInt) throws SQLException
/*      */   {
/*  392 */     return this.connection.pingDatabase(paramInt);
/*      */   }
/*      */   
/*      */   public void purgeExplicitCache() throws SQLException
/*      */   {
/*  397 */     this.connection.purgeExplicitCache();
/*      */   }
/*      */   
/*      */   public void purgeImplicitCache() throws SQLException
/*      */   {
/*  402 */     this.connection.purgeImplicitCache();
/*      */   }
/*      */   
/*      */   public void putDescriptor(String paramString, Object paramObject) throws SQLException
/*      */   {
/*  407 */     this.connection.putDescriptor(paramString, paramObject);
/*      */   }
/*      */   
/*      */   public void registerSQLType(String paramString, Class paramClass)
/*      */     throws SQLException
/*      */   {
/*  413 */     this.connection.registerSQLType(paramString, paramClass);
/*      */   }
/*      */   
/*      */   public void registerSQLType(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  419 */     this.connection.registerSQLType(paramString1, paramString2);
/*      */   }
/*      */   
/*      */   public void setAutoClose(boolean paramBoolean) throws SQLException
/*      */   {
/*  424 */     this.connection.setAutoClose(paramBoolean);
/*      */   }
/*      */   
/*      */   public void setDefaultExecuteBatch(int paramInt) throws SQLException
/*      */   {
/*  429 */     this.connection.setDefaultExecuteBatch(paramInt);
/*      */   }
/*      */   
/*      */   public void setDefaultRowPrefetch(int paramInt) throws SQLException
/*      */   {
/*  434 */     this.connection.setDefaultRowPrefetch(paramInt);
/*      */   }
/*      */   
/*      */   public void setEndToEndMetrics(String[] paramArrayOfString, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  440 */     this.connection.setEndToEndMetrics(paramArrayOfString, paramShort);
/*      */   }
/*      */   
/*      */   public void setExplicitCachingEnabled(boolean paramBoolean) throws SQLException
/*      */   {
/*  445 */     this.connection.setExplicitCachingEnabled(paramBoolean);
/*      */   }
/*      */   
/*      */   public void setImplicitCachingEnabled(boolean paramBoolean) throws SQLException
/*      */   {
/*  450 */     this.connection.setImplicitCachingEnabled(paramBoolean);
/*      */   }
/*      */   
/*      */   public void setIncludeSynonyms(boolean paramBoolean)
/*      */   {
/*  455 */     this.connection.setIncludeSynonyms(paramBoolean);
/*      */   }
/*      */   
/*      */   public void setRemarksReporting(boolean paramBoolean)
/*      */   {
/*  460 */     this.connection.setRemarksReporting(paramBoolean);
/*      */   }
/*      */   
/*      */   public void setRestrictGetTables(boolean paramBoolean)
/*      */   {
/*  465 */     this.connection.setRestrictGetTables(paramBoolean);
/*      */   }
/*      */   
/*      */   public void setStmtCacheSize(int paramInt) throws SQLException
/*      */   {
/*  470 */     this.connection.setStmtCacheSize(paramInt);
/*      */   }
/*      */   
/*      */   public void setStatementCacheSize(int paramInt) throws SQLException
/*      */   {
/*  475 */     this.connection.setStatementCacheSize(paramInt);
/*      */   }
/*      */   
/*      */   public void setStmtCacheSize(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  481 */     this.connection.setStmtCacheSize(paramInt, paramBoolean);
/*      */   }
/*      */   
/*      */   public void setUsingXAFlag(boolean paramBoolean)
/*      */   {
/*  486 */     this.connection.setUsingXAFlag(paramBoolean);
/*      */   }
/*      */   
/*      */   public void setXAErrorFlag(boolean paramBoolean)
/*      */   {
/*  491 */     this.connection.setXAErrorFlag(paramBoolean);
/*      */   }
/*      */   
/*      */   public void shutdown(OracleConnection.DatabaseShutdownMode paramDatabaseShutdownMode) throws SQLException
/*      */   {
/*  496 */     this.connection.shutdown(paramDatabaseShutdownMode);
/*      */   }
/*      */   
/*      */   public void startup(String paramString, int paramInt) throws SQLException
/*      */   {
/*  501 */     this.connection.startup(paramString, paramInt);
/*      */   }
/*      */   
/*      */   public void startup(OracleConnection.DatabaseStartupMode paramDatabaseStartupMode) throws SQLException
/*      */   {
/*  506 */     this.connection.startup(paramDatabaseStartupMode);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatementWithKey(String paramString)
/*      */     throws SQLException
/*      */   {
/*  512 */     return this.connection.prepareStatementWithKey(paramString);
/*      */   }
/*      */   
/*      */   public CallableStatement prepareCallWithKey(String paramString) throws SQLException
/*      */   {
/*  517 */     return this.connection.prepareCallWithKey(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setCreateStatementAsRefCursor(boolean paramBoolean)
/*      */   {
/*  523 */     this.connection.setCreateStatementAsRefCursor(paramBoolean);
/*      */   }
/*      */   
/*      */   public boolean getCreateStatementAsRefCursor()
/*      */   {
/*  528 */     return this.connection.getCreateStatementAsRefCursor();
/*      */   }
/*      */   
/*      */   public void setSessionTimeZone(String paramString) throws SQLException
/*      */   {
/*  533 */     this.connection.setSessionTimeZone(paramString);
/*      */   }
/*      */   
/*      */   public String getSessionTimeZone()
/*      */   {
/*  538 */     return this.connection.getSessionTimeZone();
/*      */   }
/*      */   
/*      */   public String getSessionTimeZoneOffset() throws SQLException
/*      */   {
/*  543 */     return this.connection.getSessionTimeZoneOffset();
/*      */   }
/*      */   
/*      */   public Connection _getPC()
/*      */   {
/*  548 */     return this.connection._getPC();
/*      */   }
/*      */   
/*      */   public boolean isLogicalConnection()
/*      */   {
/*  553 */     return this.connection.isLogicalConnection();
/*      */   }
/*      */   
/*      */   public void registerTAFCallback(OracleOCIFailover paramOracleOCIFailover, Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  559 */     this.connection.registerTAFCallback(paramOracleOCIFailover, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */   public Properties getProperties()
/*      */   {
/*  565 */     return this.connection.getProperties();
/*      */   }
/*      */   
/*      */   public void close(Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  571 */     this.connection.close(paramProperties);
/*      */   }
/*      */   
/*      */   public void close(int paramInt) throws SQLException
/*      */   {
/*  576 */     this.connection.close(paramInt);
/*      */   }
/*      */   
/*      */   public void applyConnectionAttributes(Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  582 */     this.connection.applyConnectionAttributes(paramProperties);
/*      */   }
/*      */   
/*      */   public Properties getConnectionAttributes() throws SQLException
/*      */   {
/*  587 */     return this.connection.getConnectionAttributes();
/*      */   }
/*      */   
/*      */   public Properties getUnMatchedConnectionAttributes()
/*      */     throws SQLException
/*      */   {
/*  593 */     return this.connection.getUnMatchedConnectionAttributes();
/*      */   }
/*      */   
/*      */ 
/*      */   public void registerConnectionCacheCallback(OracleConnectionCacheCallback paramOracleConnectionCacheCallback, Object paramObject, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  600 */     this.connection.registerConnectionCacheCallback(paramOracleConnectionCacheCallback, paramObject, paramInt);
/*      */   }
/*      */   
/*      */   public void setConnectionReleasePriority(int paramInt) throws SQLException
/*      */   {
/*  605 */     this.connection.setConnectionReleasePriority(paramInt);
/*      */   }
/*      */   
/*      */   public int getConnectionReleasePriority() throws SQLException
/*      */   {
/*  610 */     return this.connection.getConnectionReleasePriority();
/*      */   }
/*      */   
/*      */   public void setPlsqlWarnings(String paramString) throws SQLException
/*      */   {
/*  615 */     this.connection.setPlsqlWarnings(paramString);
/*      */   }
/*      */   
/*      */   public void setHoldability(int paramInt) throws SQLException
/*      */   {
/*  620 */     this.connection.setHoldability(paramInt);
/*      */   }
/*      */   
/*      */   public int getHoldability() throws SQLException
/*      */   {
/*  625 */     return this.connection.getHoldability();
/*      */   }
/*      */   
/*      */   public Statement createStatement(int paramInt1, int paramInt2, int paramInt3) throws SQLException
/*      */   {
/*  630 */     return this.connection.createStatement(paramInt1, paramInt2, paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public PreparedStatement prepareStatement(String paramString, int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  638 */     return this.connection.prepareStatement(paramString, paramInt1, paramInt2, paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public CallableStatement prepareCall(String paramString, int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  647 */     return this.connection.prepareCall(paramString, paramInt1, paramInt2, paramInt3);
/*      */   }
/*      */   
/*      */   public synchronized Savepoint setSavepoint()
/*      */     throws SQLException
/*      */   {
/*  653 */     return this.connection.setSavepoint();
/*      */   }
/*      */   
/*      */   public synchronized Savepoint setSavepoint(String paramString)
/*      */     throws SQLException
/*      */   {
/*  659 */     return this.connection.setSavepoint(paramString);
/*      */   }
/*      */   
/*      */   public synchronized void rollback(Savepoint paramSavepoint)
/*      */     throws SQLException
/*      */   {
/*  665 */     this.connection.rollback(paramSavepoint);
/*      */   }
/*      */   
/*      */   public synchronized void releaseSavepoint(Savepoint paramSavepoint)
/*      */     throws SQLException
/*      */   {
/*  671 */     this.connection.releaseSavepoint(paramSavepoint);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  677 */     return this.connection.prepareStatement(paramString, paramInt);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String paramString, int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/*  683 */     return this.connection.prepareStatement(paramString, paramArrayOfInt);
/*      */   }
/*      */   
/*      */   public PreparedStatement prepareStatement(String paramString, String[] paramArrayOfString)
/*      */     throws SQLException
/*      */   {
/*  689 */     return this.connection.prepareStatement(paramString, paramArrayOfString);
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
/*      */   public oracle.sql.ARRAY createARRAY(String paramString, Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  704 */     return this.connection.createARRAY(paramString, paramObject);
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
/*      */   public Array createOracleArray(String paramString, Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  722 */     return this.connection.createOracleArray(paramString, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BINARY_DOUBLE createBINARY_DOUBLE(double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  735 */     return this.connection.createBINARY_DOUBLE(paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BINARY_FLOAT createBINARY_FLOAT(float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  748 */     return this.connection.createBINARY_FLOAT(paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DATE createDATE(Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  761 */     return this.connection.createDATE(paramDate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DATE createDATE(Time paramTime)
/*      */     throws SQLException
/*      */   {
/*  774 */     return this.connection.createDATE(paramTime);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DATE createDATE(Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/*  787 */     return this.connection.createDATE(paramTimestamp);
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
/*      */   public DATE createDATE(Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  803 */     return this.connection.createDATE(paramDate, paramCalendar);
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
/*      */   public DATE createDATE(Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  819 */     return this.connection.createDATE(paramTime, paramCalendar);
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
/*      */   public DATE createDATE(Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  835 */     return this.connection.createDATE(paramTimestamp, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DATE createDATE(String paramString)
/*      */     throws SQLException
/*      */   {
/*  848 */     return this.connection.createDATE(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public oracle.sql.INTERVALDS createINTERVALDS(String paramString)
/*      */     throws SQLException
/*      */   {
/*  861 */     return this.connection.createINTERVALDS(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public oracle.sql.INTERVALYM createINTERVALYM(String paramString)
/*      */     throws SQLException
/*      */   {
/*  874 */     return this.connection.createINTERVALYM(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER createNUMBER(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  887 */     return this.connection.createNUMBER(paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER createNUMBER(byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  900 */     return this.connection.createNUMBER(paramByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER createNUMBER(short paramShort)
/*      */     throws SQLException
/*      */   {
/*  913 */     return this.connection.createNUMBER(paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER createNUMBER(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  926 */     return this.connection.createNUMBER(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER createNUMBER(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  939 */     return this.connection.createNUMBER(paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER createNUMBER(float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  952 */     return this.connection.createNUMBER(paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER createNUMBER(double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  965 */     return this.connection.createNUMBER(paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER createNUMBER(BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/*  978 */     return this.connection.createNUMBER(paramBigDecimal);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER createNUMBER(BigInteger paramBigInteger)
/*      */     throws SQLException
/*      */   {
/*  991 */     return this.connection.createNUMBER(paramBigInteger);
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
/*      */   public NUMBER createNUMBER(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1005 */     return this.connection.createNUMBER(paramString, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMP createTIMESTAMP(Date paramDate)
/*      */     throws SQLException
/*      */   {
/* 1018 */     return this.connection.createTIMESTAMP(paramDate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMP createTIMESTAMP(DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/* 1031 */     return this.connection.createTIMESTAMP(paramDATE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMP createTIMESTAMP(Time paramTime)
/*      */     throws SQLException
/*      */   {
/* 1044 */     return this.connection.createTIMESTAMP(paramTime);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMP createTIMESTAMP(Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/* 1057 */     return this.connection.createTIMESTAMP(paramTimestamp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMP createTIMESTAMP(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1070 */     return this.connection.createTIMESTAMP(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ createTIMESTAMPTZ(Date paramDate)
/*      */     throws SQLException
/*      */   {
/* 1083 */     return this.connection.createTIMESTAMPTZ(paramDate);
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
/*      */   public TIMESTAMPTZ createTIMESTAMPTZ(Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1100 */     return this.connection.createTIMESTAMPTZ(paramDate, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ createTIMESTAMPTZ(Time paramTime)
/*      */     throws SQLException
/*      */   {
/* 1113 */     return this.connection.createTIMESTAMPTZ(paramTime);
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
/*      */   public TIMESTAMPTZ createTIMESTAMPTZ(Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1130 */     return this.connection.createTIMESTAMPTZ(paramTime, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ createTIMESTAMPTZ(Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/* 1143 */     return this.connection.createTIMESTAMPTZ(paramTimestamp);
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
/*      */   public TIMESTAMPTZ createTIMESTAMPTZ(Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1160 */     return this.connection.createTIMESTAMPTZ(paramTimestamp, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ createTIMESTAMPTZ(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1173 */     return this.connection.createTIMESTAMPTZ(paramString);
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
/*      */   public TIMESTAMPTZ createTIMESTAMPTZ(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1190 */     return this.connection.createTIMESTAMPTZ(paramString, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ createTIMESTAMPTZ(DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/* 1199 */     return this.connection.createTIMESTAMPTZ(paramDATE);
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
/*      */   public TIMESTAMPLTZ createTIMESTAMPLTZ(Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1216 */     return this.connection.createTIMESTAMPLTZ(paramDate, paramCalendar);
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
/*      */   public TIMESTAMPLTZ createTIMESTAMPLTZ(Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1233 */     return this.connection.createTIMESTAMPLTZ(paramTime, paramCalendar);
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
/*      */   public TIMESTAMPLTZ createTIMESTAMPLTZ(Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1250 */     return this.connection.createTIMESTAMPLTZ(paramTimestamp, paramCalendar);
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
/*      */   public TIMESTAMPLTZ createTIMESTAMPLTZ(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1267 */     return this.connection.createTIMESTAMPLTZ(paramString, paramCalendar);
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
/*      */   public TIMESTAMPLTZ createTIMESTAMPLTZ(DATE paramDATE, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1284 */     return this.connection.createTIMESTAMPLTZ(paramDATE, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Array createArrayOf(String paramString, Object[] paramArrayOfObject)
/*      */     throws SQLException
/*      */   {
/* 1294 */     return this.connection.createArrayOf(paramString, paramArrayOfObject);
/*      */   }
/*      */   
/*      */   public Blob createBlob() throws SQLException
/*      */   {
/* 1299 */     return this.connection.createBlob();
/*      */   }
/*      */   
/*      */   public Clob createClob() throws SQLException
/*      */   {
/* 1304 */     return this.connection.createClob();
/*      */   }
/*      */   
/*      */   public java.sql.NClob createNClob() throws SQLException
/*      */   {
/* 1309 */     return this.connection.createNClob();
/*      */   }
/*      */   
/*      */   public SQLXML createSQLXML() throws SQLException
/*      */   {
/* 1314 */     return this.connection.createSQLXML();
/*      */   }
/*      */   
/*      */   public Struct createStruct(String paramString, Object[] paramArrayOfObject)
/*      */     throws SQLException
/*      */   {
/* 1320 */     return this.connection.createStruct(paramString, paramArrayOfObject);
/*      */   }
/*      */   
/*      */   public boolean isValid(int paramInt) throws SQLException
/*      */   {
/* 1325 */     return this.connection.isValid(paramInt);
/*      */   }
/*      */   
/*      */   public void setClientInfo(String paramString1, String paramString2)
/*      */     throws SQLClientInfoException
/*      */   {
/* 1331 */     this.connection.setClientInfo(paramString1, paramString2);
/*      */   }
/*      */   
/*      */   public void setClientInfo(Properties paramProperties) throws SQLClientInfoException
/*      */   {
/* 1336 */     this.connection.setClientInfo(paramProperties);
/*      */   }
/*      */   
/*      */   public String getClientInfo(String paramString) throws SQLException
/*      */   {
/* 1341 */     return this.connection.getClientInfo(paramString);
/*      */   }
/*      */   
/*      */   public Properties getClientInfo() throws SQLException
/*      */   {
/* 1346 */     return this.connection.getClientInfo();
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
/*      */   public boolean isWrapperFor(Class<?> paramClass)
/*      */     throws SQLException
/*      */   {
/* 1360 */     if (paramClass.isInterface()) {
/* 1361 */       return (paramClass.isInstance(this)) || (this.connection.isWrapperFor(paramClass));
/*      */     }
/*      */     
/* 1364 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 1365 */     localSQLException.fillInStackTrace();
/* 1366 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */   protected class CloseInvocationHandler
/*      */     implements InvocationHandler
/*      */   {
/*      */     private OracleConnectionWrapper wrapper;
/*      */     
/*      */     protected CloseInvocationHandler(OracleConnectionWrapper paramOracleConnectionWrapper)
/*      */     {
/* 1377 */       this.wrapper = paramOracleConnectionWrapper;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
/*      */       throws Throwable
/*      */     {
/*      */       try
/*      */       {
/* 1387 */         return paramMethod.invoke(this.wrapper, paramArrayOfObject);
/*      */       }
/*      */       catch (IllegalArgumentException localIllegalArgumentException) {}
/*      */       
/* 1391 */       return paramMethod.invoke(this.wrapper.connection, paramArrayOfObject);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/* 1396 */   private Map<Class, Object> proxies = new HashMap(3);
/* 1397 */   private static Map<Class, Class> proxyClasses = new HashMap();
/*      */   
/*      */   protected <T> T proxyFor(Object paramObject, Class<T> paramClass) throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 1403 */       Object localObject1 = this.proxies.get(paramClass);
/* 1404 */       if (localObject1 == null) {
/* 1405 */         localObject2 = (Class)proxyClasses.get(paramClass);
/* 1406 */         if (localObject2 == null) {
/* 1407 */           localObject2 = Proxy.getProxyClass(paramClass.getClassLoader(), new Class[] { paramClass });
/*      */           
/* 1409 */           proxyClasses.put(paramClass, localObject2);
/*      */         }
/* 1411 */         localObject1 = ((Class)localObject2).getConstructor(new Class[] { InvocationHandler.class }).newInstance(new Object[] { new CloseInvocationHandler(this) });
/*      */         
/* 1413 */         this.proxies.put(paramClass, localObject1);
/*      */       }
/* 1415 */       return (T)localObject1;
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/* 1419 */       Object localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Cannot construct proxy");
/* 1420 */       ((SQLException)localObject2).fillInStackTrace();
/* 1421 */       throw ((Throwable)localObject2);
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
/*      */   public <T> T unwrap(Class<T> paramClass)
/*      */     throws SQLException
/*      */   {
/* 1439 */     if (paramClass.isInterface()) {
/* 1440 */       if (paramClass.isInstance(this)) return this;
/* 1441 */       return (T)proxyFor(this.connection.unwrap(paramClass), paramClass);
/*      */     }
/*      */     
/*      */ 
/* 1445 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 1446 */     localSQLException.fillInStackTrace();
/* 1447 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DatabaseChangeRegistration registerDatabaseChangeNotification(Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/* 1459 */     return this.connection.registerDatabaseChangeNotification(paramProperties);
/*      */   }
/*      */   
/*      */   public DatabaseChangeRegistration getDatabaseChangeRegistration(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1465 */     return this.connection.getDatabaseChangeRegistration(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */   public void unregisterDatabaseChangeNotification(DatabaseChangeRegistration paramDatabaseChangeRegistration)
/*      */     throws SQLException
/*      */   {
/* 1472 */     this.connection.unregisterDatabaseChangeNotification(paramDatabaseChangeRegistration);
/*      */   }
/*      */   
/*      */ 
/*      */   public void unregisterDatabaseChangeNotification(int paramInt1, String paramString, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1479 */     this.connection.unregisterDatabaseChangeNotification(paramInt1, paramString, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */   public void unregisterDatabaseChangeNotification(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1486 */     this.connection.unregisterDatabaseChangeNotification(paramInt);
/*      */   }
/*      */   
/*      */   public void unregisterDatabaseChangeNotification(long paramLong, String paramString)
/*      */     throws SQLException
/*      */   {
/* 1492 */     this.connection.unregisterDatabaseChangeNotification(paramLong, paramString);
/*      */   }
/*      */   
/*      */   public AQNotificationRegistration[] registerAQNotification(String[] paramArrayOfString, Properties[] paramArrayOfProperties, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/* 1498 */     return this.connection.registerAQNotification(paramArrayOfString, paramArrayOfProperties, paramProperties);
/*      */   }
/*      */   
/*      */ 
/*      */   public void unregisterAQNotification(AQNotificationRegistration paramAQNotificationRegistration)
/*      */     throws SQLException
/*      */   {
/* 1505 */     this.connection.unregisterAQNotification(paramAQNotificationRegistration);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public AQMessage dequeue(String paramString, AQDequeueOptions paramAQDequeueOptions, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1513 */     return this.connection.dequeue(paramString, paramAQDequeueOptions, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public AQMessage dequeue(String paramString1, AQDequeueOptions paramAQDequeueOptions, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 1525 */     return this.connection.dequeue(paramString1, paramAQDequeueOptions, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void enqueue(String paramString, AQEnqueueOptions paramAQEnqueueOptions, AQMessage paramAQMessage)
/*      */     throws SQLException
/*      */   {
/* 1536 */     this.connection.enqueue(paramString, paramAQEnqueueOptions, paramAQMessage);
/*      */   }
/*      */   
/*      */   public void commit(EnumSet<OracleConnection.CommitOption> paramEnumSet) throws SQLException
/*      */   {
/* 1541 */     this.connection.commit(paramEnumSet);
/*      */   }
/*      */   
/*      */   public void cancel() throws SQLException
/*      */   {
/* 1546 */     this.connection.cancel();
/*      */   }
/*      */   
/*      */   public void abort() throws SQLException
/*      */   {
/* 1551 */     this.connection.abort();
/*      */   }
/*      */   
/*      */   public TypeDescriptor[] getAllTypeDescriptorsInCurrentSchema() throws SQLException
/*      */   {
/* 1556 */     return this.connection.getAllTypeDescriptorsInCurrentSchema();
/*      */   }
/*      */   
/*      */   public TypeDescriptor[] getTypeDescriptorsFromListInCurrentSchema(String[] paramArrayOfString) throws SQLException
/*      */   {
/* 1561 */     return this.connection.getTypeDescriptorsFromListInCurrentSchema(paramArrayOfString);
/*      */   }
/*      */   
/*      */   public TypeDescriptor[] getTypeDescriptorsFromList(String[][] paramArrayOfString) throws SQLException
/*      */   {
/* 1566 */     return this.connection.getTypeDescriptorsFromList(paramArrayOfString);
/*      */   }
/*      */   
/*      */   public String getDataIntegrityAlgorithmName() throws SQLException
/*      */   {
/* 1571 */     return this.connection.getDataIntegrityAlgorithmName();
/*      */   }
/*      */   
/*      */   public String getEncryptionAlgorithmName() throws SQLException
/*      */   {
/* 1576 */     return this.connection.getEncryptionAlgorithmName();
/*      */   }
/*      */   
/*      */   public String getAuthenticationAdaptorName() throws SQLException
/*      */   {
/* 1581 */     return this.connection.getAuthenticationAdaptorName();
/*      */   }
/*      */   
/*      */   public boolean isUsable()
/*      */   {
/* 1586 */     return this.connection.isUsable();
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
/*      */   protected oracle.jdbc.internal.OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 1600 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDefaultTimeZone(TimeZone paramTimeZone)
/*      */     throws SQLException
/*      */   {
/* 1613 */     this.connection.setDefaultTimeZone(paramTimeZone);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TimeZone getDefaultTimeZone()
/*      */     throws SQLException
/*      */   {
/* 1624 */     return this.connection.getDefaultTimeZone();
/*      */   }
/*      */   
/*      */   public void setApplicationContext(String paramString1, String paramString2, String paramString3) throws SQLException
/*      */   {
/* 1629 */     this.connection.setApplicationContext(paramString1, paramString2, paramString3);
/*      */   }
/*      */   
/*      */   public void clearAllApplicationContext(String paramString) throws SQLException
/*      */   {
/* 1634 */     this.connection.clearAllApplicationContext(paramString);
/*      */   }
/*      */   
/*      */ 
/* 1638 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleConnectionWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */