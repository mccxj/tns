/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.net.SocketException;
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.Connection;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.util.EnumSet;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.concurrent.Executor;
/*      */ import javax.transaction.xa.XAResource;
/*      */ import oracle.jdbc.internal.KeywordValueLong;
/*      */ import oracle.jdbc.internal.OracleConnection.BufferCacheStatistics;
/*      */ import oracle.jdbc.internal.OracleConnection.InstanceProperty;
/*      */ import oracle.jdbc.internal.OracleConnection.TransactionState;
/*      */ import oracle.jdbc.internal.OracleConnection.XSOperationCode;
/*      */ import oracle.jdbc.internal.OracleStatement;
/*      */ import oracle.jdbc.internal.XSEventListener;
/*      */ import oracle.jdbc.internal.XSNamespace;
/*      */ import oracle.jdbc.oracore.OracleTypeADT;
/*      */ import oracle.jdbc.oracore.OracleTypeCLOB;
/*      */ import oracle.jdbc.pool.OracleConnectionCacheCallback;
/*      */ import oracle.jdbc.pool.OraclePooledConnection;
/*      */ import oracle.sql.ARRAY;
/*      */ import oracle.sql.ArrayDescriptor;
/*      */ import oracle.sql.BFILE;
/*      */ import oracle.sql.BLOB;
/*      */ import oracle.sql.BfileDBAccess;
/*      */ import oracle.sql.BlobDBAccess;
/*      */ import oracle.sql.CLOB;
/*      */ import oracle.sql.ClobDBAccess;
/*      */ import oracle.sql.CustomDatum;
/*      */ import oracle.sql.Datum;
/*      */ import oracle.sql.StructDescriptor;
/*      */ import oracle.sql.TIMEZONETAB;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class LogicalConnection
/*      */   extends OracleConnection
/*      */ {
/*   62 */   static final ClosedConnection closedConnection = new ClosedConnection();
/*      */   
/*      */ 
/*      */   PhysicalConnection internalConnection;
/*      */   
/*      */ 
/*      */   OraclePooledConnection pooledConnection;
/*      */   
/*      */   boolean closed;
/*      */   
/*   72 */   OracleCloseCallback closeCallback = null;
/*   73 */   Object privateData = null;
/*      */   
/*      */ 
/*   76 */   long startTime = 0L;
/*      */   
/*      */ 
/*      */ 
/*   80 */   OracleConnectionCacheCallback connectionCacheCallback = null;
/*   81 */   Object connectionCacheCallbackUserObj = null;
/*   82 */   int callbackFlag = 0;
/*   83 */   int releasePriority = 0;
/*      */   
/*      */ 
/*   86 */   int heartbeatCount = 0;
/*   87 */   int heartbeatLastCount = 0;
/*   88 */   int heartbeatNoChangeCount = 0;
/*   89 */   boolean isAbandonedTimeoutEnabled = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   LogicalConnection(OraclePooledConnection paramOraclePooledConnection, PhysicalConnection paramPhysicalConnection, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*   97 */     this.internalConnection = paramPhysicalConnection;
/*   98 */     this.pooledConnection = paramOraclePooledConnection;
/*   99 */     this.connection = this.internalConnection;
/*      */     
/*  101 */     this.connection.setWrapper(this);
/*      */     
/*  103 */     this.closed = false;
/*      */     
/*  105 */     this.internalConnection.setAutoCommit(paramBoolean);
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
/*      */   void registerHeartbeat()
/*      */     throws SQLException
/*      */   {
/*  120 */     if (this.isAbandonedTimeoutEnabled)
/*      */     {
/*      */       try
/*      */       {
/*  124 */         this.heartbeatCount += 1;
/*      */       }
/*      */       catch (ArithmeticException localArithmeticException)
/*      */       {
/*  128 */         this.heartbeatCount = 0;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getHeartbeatNoChangeCount()
/*      */     throws SQLException
/*      */   {
/*  141 */     if (this.heartbeatCount == this.heartbeatLastCount)
/*      */     {
/*  143 */       this.heartbeatNoChangeCount += 1;
/*      */     }
/*      */     else
/*      */     {
/*  147 */       this.heartbeatLastCount = this.heartbeatCount;
/*  148 */       this.heartbeatNoChangeCount = 0;
/*      */     }
/*      */     
/*  151 */     return this.heartbeatNoChangeCount;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public oracle.jdbc.internal.OracleConnection physicalConnectionWithin()
/*      */   {
/*  158 */     return this.internalConnection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void registerCloseCallback(OracleCloseCallback paramOracleCloseCallback, Object paramObject)
/*      */   {
/*  166 */     this.closeCallback = paramOracleCloseCallback;
/*  167 */     this.privateData = paramObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Connection _getPC()
/*      */   {
/*  174 */     return this.internalConnection;
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
/*      */   public synchronized boolean isLogicalConnection()
/*      */   {
/*  187 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public oracle.jdbc.internal.OracleConnection getPhysicalConnection()
/*      */   {
/*  194 */     return this.internalConnection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Connection getLogicalConnection(OraclePooledConnection paramOraclePooledConnection, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  207 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 153);
/*  208 */     localSQLException.fillInStackTrace();
/*  209 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void getPropertyForPooledConnection(OraclePooledConnection paramOraclePooledConnection)
/*      */     throws SQLException
/*      */   {
/*  222 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 153);
/*  223 */     localSQLException.fillInStackTrace();
/*  224 */     throw localSQLException;
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
/*      */   public synchronized void close()
/*      */     throws SQLException
/*      */   {
/*  238 */     closeInternal(true);
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
/*      */   public void closeInternal(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  252 */     if (this.closed) {
/*  253 */       return;
/*      */     }
/*  255 */     if (this.closeCallback != null) {
/*  256 */       this.closeCallback.beforeClose(this, this.privateData);
/*      */     }
/*      */     
/*      */ 
/*  260 */     this.internalConnection.closeLogicalConnection();
/*      */     
/*      */ 
/*  263 */     this.startTime = 0L;
/*      */     
/*  265 */     this.closed = true;
/*      */     
/*      */ 
/*  268 */     if ((this.pooledConnection != null) && (paramBoolean)) {
/*  269 */       this.pooledConnection.logicalClose();
/*      */     }
/*      */     
/*      */ 
/*  273 */     this.internalConnection = closedConnection;
/*  274 */     this.connection = closedConnection;
/*      */     
/*  276 */     if (this.closeCallback != null) {
/*  277 */       this.closeCallback.afterClose(this.privateData);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void cleanupAndClose(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  288 */     if (this.closed) {
/*  289 */       return;
/*      */     }
/*      */     
/*  292 */     this.closed = true;
/*      */     
/*      */ 
/*  295 */     PhysicalConnection localPhysicalConnection = this.internalConnection;
/*  296 */     OraclePooledConnection localOraclePooledConnection = this.pooledConnection;
/*  297 */     this.internalConnection = closedConnection;
/*  298 */     this.connection = closedConnection;
/*  299 */     this.startTime = 0L;
/*      */     
/*  301 */     if (this.closeCallback != null) {
/*  302 */       this.closeCallback.beforeClose(this, this.privateData);
/*      */     }
/*      */     
/*  305 */     localPhysicalConnection.cleanupAndClose();
/*  306 */     localPhysicalConnection.closeLogicalConnection();
/*      */     
/*      */ 
/*  309 */     if ((localOraclePooledConnection != null) && (paramBoolean)) {
/*  310 */       localOraclePooledConnection.logicalClose();
/*      */     }
/*  312 */     if (this.closeCallback != null) {
/*  313 */       this.closeCallback.afterClose(this.privateData);
/*      */     }
/*      */   }
/*      */   
/*      */   public void abort()
/*      */     throws SQLException
/*      */   {
/*  320 */     if (this.closed)
/*  321 */       return;
/*  322 */     this.internalConnection.abort();
/*  323 */     this.closed = true;
/*      */     
/*  325 */     this.internalConnection = closedConnection;
/*  326 */     this.connection = closedConnection;
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
/*      */   public synchronized void close(Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  340 */     if (this.pooledConnection != null)
/*      */     {
/*  342 */       this.pooledConnection.cachedConnectionAttributes.clear();
/*  343 */       this.pooledConnection.cachedConnectionAttributes.putAll(paramProperties);
/*      */     }
/*      */     
/*  346 */     close();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public synchronized void close(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  354 */     if ((paramInt & 0x1000) != 0)
/*      */     {
/*      */ 
/*      */ 
/*  358 */       if (this.pooledConnection != null) {
/*  359 */         this.pooledConnection.closeOption = paramInt;
/*      */       }
/*  361 */       close();
/*      */       
/*  363 */       return;
/*      */     }
/*      */     
/*  366 */     if ((paramInt & 0x1) != 0)
/*      */     {
/*      */ 
/*      */ 
/*  370 */       this.internalConnection.close(1);
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
/*      */   public synchronized void applyConnectionAttributes(Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  385 */     if (this.pooledConnection != null) {
/*  386 */       this.pooledConnection.cachedConnectionAttributes.putAll(paramProperties);
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
/*      */   public synchronized Properties getConnectionAttributes()
/*      */     throws SQLException
/*      */   {
/*  401 */     if (this.pooledConnection != null) {
/*  402 */       return this.pooledConnection.cachedConnectionAttributes;
/*      */     }
/*  404 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized Properties getUnMatchedConnectionAttributes()
/*      */     throws SQLException
/*      */   {
/*  417 */     if (this.pooledConnection != null) {
/*  418 */       return this.pooledConnection.unMatchedCachedConnAttr;
/*      */     }
/*  420 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void setAbandonedTimeoutEnabled(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  432 */     this.isAbandonedTimeoutEnabled = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void registerConnectionCacheCallback(OracleConnectionCacheCallback paramOracleConnectionCacheCallback, Object paramObject, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  444 */     this.connectionCacheCallback = paramOracleConnectionCacheCallback;
/*  445 */     this.connectionCacheCallbackUserObj = paramObject;
/*  446 */     this.callbackFlag = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleConnectionCacheCallback getConnectionCacheCallbackObj()
/*      */     throws SQLException
/*      */   {
/*  457 */     return this.connectionCacheCallback;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getConnectionCacheCallbackPrivObj()
/*      */     throws SQLException
/*      */   {
/*  467 */     return this.connectionCacheCallbackUserObj;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getConnectionCacheCallbackFlag()
/*      */     throws SQLException
/*      */   {
/*  477 */     return this.callbackFlag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void setConnectionReleasePriority(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  488 */     this.releasePriority = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getConnectionReleasePriority()
/*      */     throws SQLException
/*      */   {
/*  499 */     return this.releasePriority;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean isClosed()
/*      */     throws SQLException
/*      */   {
/*  512 */     return this.closed;
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
/*      */   public void setStartTime(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  531 */     if (paramLong <= 0L)
/*      */     {
/*      */ 
/*  534 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  535 */       localSQLException.fillInStackTrace();
/*  536 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  541 */     this.startTime = paramLong;
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
/*      */   public long getStartTime()
/*      */     throws SQLException
/*      */   {
/*  557 */     return this.startTime;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getDatabaseTimeZone()
/*      */     throws SQLException
/*      */   {
/*  567 */     return this.internalConnection.getDatabaseTimeZone();
/*      */   }
/*      */   
/*      */   public Properties getServerSessionInfo()
/*      */     throws SQLException
/*      */   {
/*  573 */     return this.internalConnection.getServerSessionInfo();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getClientData(Object paramObject)
/*      */   {
/*  580 */     return this.internalConnection.getClientData(paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object setClientData(Object paramObject1, Object paramObject2)
/*      */   {
/*  587 */     return this.internalConnection.setClientData(paramObject1, paramObject2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object removeClientData(Object paramObject)
/*      */   {
/*  594 */     return this.internalConnection.removeClientData(paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setClientIdentifier(String paramString)
/*      */     throws SQLException
/*      */   {
/*  601 */     this.internalConnection.setClientIdentifier(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */   public void clearClientIdentifier(String paramString)
/*      */     throws SQLException
/*      */   {
/*  608 */     this.internalConnection.clearClientIdentifier(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */   public short getStructAttrNCsId()
/*      */     throws SQLException
/*      */   {
/*  615 */     return this.internalConnection.getStructAttrNCsId();
/*      */   }
/*      */   
/*      */ 
/*      */   public Map getTypeMap()
/*      */     throws SQLException
/*      */   {
/*  622 */     return this.internalConnection.getTypeMap();
/*      */   }
/*      */   
/*      */ 
/*      */   public Properties getDBAccessProperties()
/*      */     throws SQLException
/*      */   {
/*  629 */     return this.internalConnection.getDBAccessProperties();
/*      */   }
/*      */   
/*      */ 
/*      */   public Properties getOCIHandles()
/*      */     throws SQLException
/*      */   {
/*  636 */     return this.internalConnection.getOCIHandles();
/*      */   }
/*      */   
/*      */ 
/*      */   public String getDatabaseProductVersion()
/*      */     throws SQLException
/*      */   {
/*  643 */     return this.internalConnection.getDatabaseProductVersion();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void cancel()
/*      */     throws SQLException
/*      */   {
/*  654 */     registerHeartbeat();
/*  655 */     this.internalConnection.cancel();
/*      */   }
/*      */   
/*      */ 
/*      */   public String getURL()
/*      */     throws SQLException
/*      */   {
/*  662 */     return this.internalConnection.getURL();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getIncludeSynonyms()
/*      */   {
/*  669 */     return this.internalConnection.getIncludeSynonyms();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getRemarksReporting()
/*      */   {
/*  676 */     return this.internalConnection.getRemarksReporting();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getRestrictGetTables()
/*      */   {
/*  683 */     return this.internalConnection.getRestrictGetTables();
/*      */   }
/*      */   
/*      */ 
/*      */   public short getVersionNumber()
/*      */     throws SQLException
/*      */   {
/*  690 */     return this.internalConnection.getVersionNumber();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Map getJavaObjectTypeMap()
/*      */   {
/*  697 */     return this.internalConnection.getJavaObjectTypeMap();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setJavaObjectTypeMap(Map paramMap)
/*      */   {
/*  704 */     this.internalConnection.setJavaObjectTypeMap(paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */   public BfileDBAccess createBfileDBAccess()
/*      */     throws SQLException
/*      */   {
/*  711 */     return this.internalConnection.createBfileDBAccess();
/*      */   }
/*      */   
/*      */ 
/*      */   public BlobDBAccess createBlobDBAccess()
/*      */     throws SQLException
/*      */   {
/*  718 */     return this.internalConnection.createBlobDBAccess();
/*      */   }
/*      */   
/*      */ 
/*      */   public ClobDBAccess createClobDBAccess()
/*      */     throws SQLException
/*      */   {
/*  725 */     return this.internalConnection.createClobDBAccess();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDefaultFixedString(boolean paramBoolean)
/*      */   {
/*  732 */     this.internalConnection.setDefaultFixedString(paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getTimestamptzInGmt()
/*      */   {
/*  739 */     return this.internalConnection.getTimestamptzInGmt();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getUse1900AsYearForTime()
/*      */   {
/*  746 */     return this.internalConnection.getUse1900AsYearForTime();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getDefaultFixedString()
/*      */   {
/*  753 */     return this.internalConnection.getDefaultFixedString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public oracle.jdbc.OracleConnection getWrapper()
/*      */   {
/*  760 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Class classForNameAndSchema(String paramString1, String paramString2)
/*      */     throws ClassNotFoundException
/*      */   {
/*  768 */     return this.internalConnection.classForNameAndSchema(paramString1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setFDO(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  775 */     this.internalConnection.setFDO(paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */   public byte[] getFDO(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  782 */     return this.internalConnection.getFDO(paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean getBigEndian()
/*      */     throws SQLException
/*      */   {
/*  789 */     return this.internalConnection.getBigEndian();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getDescriptor(byte[] paramArrayOfByte)
/*      */   {
/*  796 */     return this.internalConnection.getDescriptor(paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */   public void putDescriptor(byte[] paramArrayOfByte, Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  803 */     this.internalConnection.putDescriptor(paramArrayOfByte, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void removeDescriptor(String paramString)
/*      */   {
/*  810 */     this.internalConnection.removeDescriptor(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void removeAllDescriptor()
/*      */   {
/*  817 */     this.internalConnection.removeAllDescriptor();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int numberOfDescriptorCacheEntries()
/*      */   {
/*  824 */     return this.internalConnection.numberOfDescriptorCacheEntries();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Enumeration descriptorCacheKeys()
/*      */   {
/*  831 */     return this.internalConnection.descriptorCacheKeys();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void getOracleTypeADT(OracleTypeADT paramOracleTypeADT)
/*      */     throws SQLException
/*      */   {
/*  839 */     this.internalConnection.getOracleTypeADT(paramOracleTypeADT);
/*      */   }
/*      */   
/*      */ 
/*      */   public short getDbCsId()
/*      */     throws SQLException
/*      */   {
/*  846 */     return this.internalConnection.getDbCsId();
/*      */   }
/*      */   
/*      */ 
/*      */   public short getJdbcCsId()
/*      */     throws SQLException
/*      */   {
/*  853 */     return this.internalConnection.getJdbcCsId();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public short getNCharSet()
/*      */   {
/*  860 */     return this.internalConnection.getNCharSet();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSet newArrayDataResultSet(Datum[] paramArrayOfDatum, long paramLong, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  868 */     return this.internalConnection.newArrayDataResultSet(paramArrayOfDatum, paramLong, paramInt, paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSet newArrayDataResultSet(ARRAY paramARRAY, long paramLong, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  876 */     return this.internalConnection.newArrayDataResultSet(paramARRAY, paramLong, paramInt, paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ResultSet newArrayLocatorResultSet(ArrayDescriptor paramArrayDescriptor, byte[] paramArrayOfByte, long paramLong, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  886 */     return this.internalConnection.newArrayLocatorResultSet(paramArrayDescriptor, paramArrayOfByte, paramLong, paramInt, paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ResultSetMetaData newStructMetaData(StructDescriptor paramStructDescriptor)
/*      */     throws SQLException
/*      */   {
/*  895 */     return this.internalConnection.newStructMetaData(paramStructDescriptor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void getForm(OracleTypeADT paramOracleTypeADT, OracleTypeCLOB paramOracleTypeCLOB, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  904 */     this.internalConnection.getForm(paramOracleTypeADT, paramOracleTypeCLOB, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int CHARBytesToJavaChars(byte[] paramArrayOfByte, int paramInt, char[] paramArrayOfChar)
/*      */     throws SQLException
/*      */   {
/*  912 */     return this.internalConnection.CHARBytesToJavaChars(paramArrayOfByte, paramInt, paramArrayOfChar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int NCHARBytesToJavaChars(byte[] paramArrayOfByte, int paramInt, char[] paramArrayOfChar)
/*      */     throws SQLException
/*      */   {
/*  920 */     return this.internalConnection.NCHARBytesToJavaChars(paramArrayOfByte, paramInt, paramArrayOfChar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean IsNCharFixedWith()
/*      */   {
/*  927 */     return this.internalConnection.IsNCharFixedWith();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public short getDriverCharSet()
/*      */   {
/*  934 */     return this.internalConnection.getDriverCharSet();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getC2SNlsRatio()
/*      */   {
/*  941 */     return this.internalConnection.getC2SNlsRatio();
/*      */   }
/*      */   
/*      */ 
/*      */   public int getMaxCharSize()
/*      */     throws SQLException
/*      */   {
/*  948 */     return this.internalConnection.getMaxCharSize();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getMaxCharbyteSize()
/*      */   {
/*  955 */     return this.internalConnection.getMaxCharbyteSize();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getMaxNCharbyteSize()
/*      */   {
/*  962 */     return this.internalConnection.getMaxNCharbyteSize();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isCharSetMultibyte(short paramShort)
/*      */   {
/*  969 */     return this.internalConnection.isCharSetMultibyte(paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int javaCharsToCHARBytes(char[] paramArrayOfChar, int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  977 */     return this.internalConnection.javaCharsToCHARBytes(paramArrayOfChar, paramInt, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int javaCharsToNCHARBytes(char[] paramArrayOfChar, int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  985 */     return this.internalConnection.javaCharsToNCHARBytes(paramArrayOfChar, paramInt, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getStmtCacheSize()
/*      */   {
/*  992 */     return this.internalConnection.getStmtCacheSize();
/*      */   }
/*      */   
/*      */ 
/*      */   public int getStatementCacheSize()
/*      */     throws SQLException
/*      */   {
/*  999 */     return this.internalConnection.getStatementCacheSize();
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean getImplicitCachingEnabled()
/*      */     throws SQLException
/*      */   {
/* 1006 */     return this.internalConnection.getImplicitCachingEnabled();
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean getExplicitCachingEnabled()
/*      */     throws SQLException
/*      */   {
/* 1013 */     return this.internalConnection.getExplicitCachingEnabled();
/*      */   }
/*      */   
/*      */ 
/*      */   public void purgeImplicitCache()
/*      */     throws SQLException
/*      */   {
/* 1020 */     this.internalConnection.purgeImplicitCache();
/*      */   }
/*      */   
/*      */ 
/*      */   public void purgeExplicitCache()
/*      */     throws SQLException
/*      */   {
/* 1027 */     this.internalConnection.purgeExplicitCache();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public PreparedStatement getStatementWithKey(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1035 */     return this.internalConnection.getStatementWithKey(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public CallableStatement getCallWithKey(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1043 */     return this.internalConnection.getCallWithKey(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isStatementCacheInitialized()
/*      */   {
/* 1050 */     return this.internalConnection.isStatementCacheInitialized();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTypeMap(Map paramMap)
/*      */   {
/* 1057 */     this.internalConnection.setTypeMap(paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getProtocolType()
/*      */   {
/* 1064 */     return this.internalConnection.getProtocolType();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTxnMode(int paramInt)
/*      */   {
/* 1073 */     this.internalConnection.setTxnMode(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getTxnMode()
/*      */   {
/* 1080 */     return this.internalConnection.getTxnMode();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getHeapAllocSize()
/*      */     throws SQLException
/*      */   {
/* 1088 */     return this.internalConnection.getHeapAllocSize();
/*      */   }
/*      */   
/*      */ 
/*      */   public int getOCIEnvHeapAllocSize()
/*      */     throws SQLException
/*      */   {
/* 1095 */     return this.internalConnection.getOCIEnvHeapAllocSize();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CLOB createClob(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1106 */     registerHeartbeat();
/* 1107 */     return this.internalConnection.createClob(paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CLOB createClobWithUnpickledBytes(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1119 */     registerHeartbeat();
/* 1120 */     return this.internalConnection.createClobWithUnpickledBytes(paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CLOB createClob(byte[] paramArrayOfByte, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 1132 */     registerHeartbeat();
/* 1133 */     return this.internalConnection.createClob(paramArrayOfByte, paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BLOB createBlob(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1144 */     registerHeartbeat();
/* 1145 */     return this.internalConnection.createBlob(paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BLOB createBlobWithUnpickledBytes(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1156 */     registerHeartbeat();
/* 1157 */     return this.internalConnection.createBlobWithUnpickledBytes(paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BFILE createBfile(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1168 */     registerHeartbeat();
/* 1169 */     return this.internalConnection.createBfile(paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isDescriptorSharable(oracle.jdbc.internal.OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/* 1177 */     return this.internalConnection.isDescriptorSharable(paramOracleConnection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public OracleStatement refCursorCursorToStatement(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1185 */     return this.internalConnection.refCursorCursorToStatement(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */   public long getTdoCState(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 1192 */     return this.internalConnection.getTdoCState(paramString1, paramString2);
/*      */   }
/*      */   
/*      */   public Datum toDatum(CustomDatum paramCustomDatum)
/*      */     throws SQLException
/*      */   {
/* 1198 */     return this.internalConnection.toDatum(paramCustomDatum);
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
/*      */   public XAResource getXAResource()
/*      */     throws SQLException
/*      */   {
/* 1215 */     return this.pooledConnection.getXAResource();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setApplicationContext(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 1226 */     this.internalConnection.setApplicationContext(paramString1, paramString2, paramString3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearAllApplicationContext(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1235 */     this.internalConnection.clearAllApplicationContext(paramString);
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
/*      */   public boolean isV8Compatible()
/*      */     throws SQLException
/*      */   {
/* 1252 */     return getMapDateToTimestamp();
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
/*      */   public boolean getMapDateToTimestamp()
/*      */   {
/* 1265 */     return this.internalConnection.getMapDateToTimestamp();
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
/*      */   public byte[] createLightweightSession(String paramString, KeywordValueLong[] paramArrayOfKeywordValueLong, int paramInt, KeywordValueLong[][] paramArrayOfKeywordValueLong1, int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/* 1279 */     return this.internalConnection.createLightweightSession(paramString, paramArrayOfKeywordValueLong, paramInt, paramArrayOfKeywordValueLong1, paramArrayOfInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void executeLightweightSessionRoundtrip(int paramInt1, byte[] paramArrayOfByte, KeywordValueLong[] paramArrayOfKeywordValueLong, int paramInt2, KeywordValueLong[][] paramArrayOfKeywordValueLong1, int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/* 1292 */     this.internalConnection.executeLightweightSessionRoundtrip(paramInt1, paramArrayOfByte, paramArrayOfKeywordValueLong, paramInt2, paramArrayOfKeywordValueLong1, paramArrayOfInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void executeLightweightSessionPiggyback(int paramInt1, byte[] paramArrayOfByte, KeywordValueLong[] paramArrayOfKeywordValueLong, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1304 */     this.internalConnection.executeLightweightSessionPiggyback(paramInt1, paramArrayOfByte, paramArrayOfKeywordValueLong, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void doXSNamespaceOp(OracleConnection.XSOperationCode paramXSOperationCode, byte[] paramArrayOfByte, XSNamespace[] paramArrayOfXSNamespace, XSNamespace[][] paramArrayOfXSNamespace1)
/*      */     throws SQLException
/*      */   {
/* 1315 */     this.internalConnection.doXSNamespaceOp(paramXSOperationCode, paramArrayOfByte, paramArrayOfXSNamespace, paramArrayOfXSNamespace1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void doXSNamespaceOp(OracleConnection.XSOperationCode paramXSOperationCode, byte[] paramArrayOfByte, XSNamespace[] paramArrayOfXSNamespace)
/*      */     throws SQLException
/*      */   {
/* 1323 */     this.internalConnection.doXSNamespaceOp(paramXSOperationCode, paramArrayOfByte, paramArrayOfXSNamespace);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BLOB createTemporaryBlob(Connection paramConnection, boolean paramBoolean, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1336 */     registerHeartbeat();
/* 1337 */     return this.internalConnection.createTemporaryBlob(paramConnection, paramBoolean, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CLOB createTemporaryClob(Connection paramConnection, boolean paramBoolean, int paramInt, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 1349 */     registerHeartbeat();
/* 1350 */     return this.internalConnection.createTemporaryClob(paramConnection, paramBoolean, paramInt, paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getDefaultSchemaNameForNamedTypes()
/*      */     throws SQLException
/*      */   {
/* 1358 */     return this.internalConnection.getDefaultSchemaNameForNamedTypes();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isUsable()
/*      */   {
/* 1370 */     return (!this.closed) && (this.internalConnection.isUsable());
/*      */   }
/*      */   
/*      */ 
/*      */   public byte getInstanceProperty(OracleConnection.InstanceProperty paramInstanceProperty)
/*      */     throws SQLException
/*      */   {
/* 1377 */     return this.internalConnection.getInstanceProperty(paramInstanceProperty);
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
/*      */   public void setUsable(boolean paramBoolean)
/*      */   {
/* 1391 */     this.internalConnection.setUsable(paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getTimezoneVersionNumber()
/*      */     throws SQLException
/*      */   {
/* 1399 */     return this.internalConnection.getTimezoneVersionNumber();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMEZONETAB getTIMEZONETAB()
/*      */     throws SQLException
/*      */   {
/* 1412 */     return this.internalConnection.getTIMEZONETAB();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void addXSEventListener(XSEventListener paramXSEventListener)
/*      */     throws SQLException
/*      */   {
/* 1420 */     this.internalConnection.addXSEventListener(paramXSEventListener);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addXSEventListener(XSEventListener paramXSEventListener, Executor paramExecutor)
/*      */     throws SQLException
/*      */   {
/* 1429 */     this.internalConnection.addXSEventListener(paramXSEventListener, paramExecutor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void removeXSEventListener(XSEventListener paramXSEventListener)
/*      */     throws SQLException
/*      */   {
/* 1437 */     this.internalConnection.removeXSEventListener(paramXSEventListener);
/*      */   }
/*      */   
/*      */   public OracleConnection.BufferCacheStatistics getByteBufferCacheStatistics()
/*      */   {
/* 1442 */     return this.internalConnection.getByteBufferCacheStatistics();
/*      */   }
/*      */   
/*      */   public OracleConnection.BufferCacheStatistics getCharBufferCacheStatistics() {
/* 1446 */     return this.internalConnection.getCharBufferCacheStatistics();
/*      */   }
/*      */   
/*      */   public boolean isDataInLocatorEnabled() throws SQLException
/*      */   {
/* 1451 */     return this.internalConnection.isDataInLocatorEnabled();
/*      */   }
/*      */   
/*      */   public boolean isLobStreamPosStandardCompliant() throws SQLException
/*      */   {
/* 1456 */     return this.internalConnection.isLobStreamPosStandardCompliant();
/*      */   }
/*      */   
/*      */   public long getCurrentSCN() throws SQLException
/*      */   {
/* 1461 */     return this.internalConnection.getCurrentSCN();
/*      */   }
/*      */   
/*      */   public EnumSet<OracleConnection.TransactionState> getTransactionState() throws SQLException
/*      */   {
/* 1466 */     return this.internalConnection.getTransactionState();
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isConnectionSocketKeepAlive()
/*      */     throws SocketException, SQLException
/*      */   {
/* 1473 */     return this.internalConnection.isConnectionSocketKeepAlive();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1478 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/LogicalConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */