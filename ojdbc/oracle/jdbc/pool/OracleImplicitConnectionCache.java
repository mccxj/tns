/*      */ package oracle.jdbc.pool;
/*      */ 
/*      */ import java.sql.Connection;
/*      */ import java.sql.SQLException;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Properties;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.Vector;
/*      */ import javax.sql.PooledConnection;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.xa.client.OracleXADataSource;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ /**
/*      */  * @deprecated
/*      */  */
/*      */ class OracleImplicitConnectionCache
/*      */ {
/*   51 */   protected OracleDataSource cacheEnabledDS = null;
/*   52 */   protected String cacheName = null;
/*   53 */   protected OracleConnectionPoolDataSource connectionPoolDS = null;
/*   54 */   protected boolean fastConnectionFailoverEnabled = false;
/*      */   
/*      */ 
/*   57 */   protected String defaultUser = null;
/*   58 */   protected String defaultPassword = null;
/*      */   
/*      */   protected static final int DEFAULT_MIN_LIMIT = 0;
/*      */   
/*      */   protected static final int DEFAULT_MAX_LIMIT = Integer.MAX_VALUE;
/*      */   
/*      */   protected static final int DEFAULT_INITIAL_LIMIT = 0;
/*      */   
/*      */   protected static final int DEFAULT_MAX_STATEMENTS_LIMIT = 0;
/*      */   
/*      */   protected static final int DEFAULT_INACTIVITY_TIMEOUT = 0;
/*      */   
/*      */   protected static final int DEFAULT_TIMETOLIVE_TIMEOUT = 0;
/*      */   
/*      */   protected static final int DEFAULT_ABANDONED_CONN_TIMEOUT = 0;
/*      */   
/*      */   protected static final int DEFAULT_CONNECTION_WAIT_TIMEOUT = 0;
/*      */   protected static final String DEFAULT_ATTRIBUTE_WEIGHT = "0";
/*      */   protected static final int DEFAULT_LOWER_THRESHOLD_LIMIT = 20;
/*      */   protected static final int DEFAULT_PROPERTY_CHECK_INTERVAL = 900;
/*      */   protected static final int CLOSE_AND_REMOVE_ALL_CONNECTIONS = 1;
/*      */   protected static final int CLOSE_AND_REMOVE_FAILOVER_CONNECTIONS = 2;
/*      */   protected static final int PROCESS_INACTIVITY_TIMEOUT = 4;
/*      */   protected static final int CLOSE_AND_REMOVE_N_CONNECTIONS = 8;
/*      */   protected static final int DISABLE_STATEMENT_CACHING = 16;
/*      */   protected static final int RESET_STATEMENT_CACHE_SIZE = 18;
/*      */   protected static final int CLOSE_AND_REMOVE_RLB_CONNECTIONS = 24;
/*      */   protected static final int ABORT_AND_CLOSE_ALL_CONNECTIONS = 32;
/*      */   public static final int REFRESH_INVALID_CONNECTIONS = 4096;
/*      */   public static final int REFRESH_ALL_CONNECTIONS = 8192;
/*      */   private static final String ATTRKEY_DELIM = "0xffff";
/*   89 */   protected int cacheMinLimit = 0;
/*   90 */   protected int cacheMaxLimit = Integer.MAX_VALUE;
/*   91 */   protected int cacheInitialLimit = 0;
/*   92 */   protected int cacheMaxStatementsLimit = 0;
/*   93 */   protected Properties cacheAttributeWeights = null;
/*   94 */   protected int cacheInactivityTimeout = 0;
/*   95 */   protected int cacheTimeToLiveTimeout = 0;
/*   96 */   protected int cacheAbandonedConnectionTimeout = 0;
/*   97 */   protected int cacheLowerThresholdLimit = 20;
/*   98 */   protected int cachePropertyCheckInterval = 900;
/*   99 */   protected boolean cacheClosestConnectionMatch = false;
/*  100 */   protected boolean cacheValidateConnection = false;
/*  101 */   protected boolean cacheUseLIFO = false;
/*  102 */   protected int cacheConnectionWaitTimeout = 0;
/*      */   
/*      */   static final String MIN_LIMIT_KEY = "MinLimit";
/*      */   
/*      */   static final String MAX_LIMIT_KEY = "MaxLimit";
/*      */   
/*      */   static final String INITIAL_LIMIT_KEY = "InitialLimit";
/*      */   
/*      */   static final String MAX_STATEMENTS_LIMIT_KEY = "MaxStatementsLimit";
/*      */   
/*      */   static final String ATTRIBUTE_WEIGHTS_KEY = "AttributeWeights";
/*      */   
/*      */   static final String INACTIVITY_TIMEOUT_KEY = "InactivityTimeout";
/*      */   
/*      */   static final String TIME_TO_LIVE_TIMEOUT_KEY = "TimeToLiveTimeout";
/*      */   
/*      */   static final String ABANDONED_CONNECTION_TIMEOUT_KEY = "AbandonedConnectionTimeout";
/*      */   static final String LOWER_THRESHOLD_LIMIT_KEY = "LowerThresholdLimit";
/*      */   static final String PROPERTY_CHECK_INTERVAL_KEY = "PropertyCheckInterval";
/*      */   static final String VALIDATE_CONNECTION_KEY = "ValidateConnection";
/*      */   static final String CLOSEST_CONNECTION_MATCH_KEY = "ClosestConnectionMatch";
/*      */   static final String CONNECTION_WAIT_TIMEOUT_KEY = "ConnectionWaitTimeout";
/*      */   static final String LOCAL_TXN_COMMIT_ON_CLOSE = "LocalTransactionCommitOnClose";
/*      */   static final String USE_LIFO_KEY = "UseLIFO";
/*      */   static final int INSTANCE_GOOD = 1;
/*      */   static final int INSTANCE_UNKNOWN = 2;
/*      */   static final int INSTANCE_VIOLATING = 3;
/*      */   static final int INSTANCE_NO_DATA = 4;
/*      */   static final int INSTANCE_BLOCKED = 5;
/*      */   static final int RLB_NUMBER_OF_HITS_PER_INSTANCE = 1000;
/*  132 */   int dbInstancePercentTotal = 0;
/*  133 */   boolean useGoodGroup = false;
/*  134 */   Vector instancesToRetireQueue = null;
/*  135 */   OracleDatabaseInstance instanceToRetire = null;
/*  136 */   int retireConnectionsCount = 0;
/*  137 */   int countTotal = 0;
/*      */   
/*  139 */   protected OracleConnectionCacheManager cacheManager = null;
/*  140 */   protected boolean disableConnectionRequest = false;
/*  141 */   protected OracleImplicitConnectionCacheThread timeoutThread = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  148 */   protected OracleRuntimeLoadBalancingEventHandlerThread runtimeLoadBalancingThread = null;
/*      */   
/*      */ 
/*      */ 
/*  152 */   protected OracleGravitateConnectionCacheThread gravitateCacheThread = null;
/*  153 */   protected int connectionsToRemove = 0;
/*      */   
/*      */ 
/*      */ 
/*  157 */   private HashMap userMap = null;
/*  158 */   Vector checkedOutConnectionList = null;
/*      */   
/*      */ 
/*  161 */   LinkedList databaseInstancesList = null;
/*      */   
/*  163 */   int cacheSize = 0;
/*      */   
/*      */   protected static final String EVENT_DELIMITER = " ";
/*      */   
/*  167 */   protected boolean isEntireServiceDownProcessed = false;
/*  168 */   protected int defaultUserPreFailureSize = 0;
/*  169 */   protected String dataSourceServiceName = null;
/*  170 */   protected OracleFailoverWorkerThread failoverWorkerThread = null;
/*  171 */   protected Random rand = null;
/*  172 */   protected int downEventCount = 0;
/*  173 */   protected int upEventCount = 0;
/*  174 */   protected int pendingCreationRequests = 0;
/*      */   
/*  176 */   protected int connectionClosedCount = 0;
/*  177 */   protected int connectionCreatedCount = 0;
/*  178 */   boolean cacheLocalTxnCommitOnClose = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   OracleImplicitConnectionCache(OracleDataSource paramOracleDataSource, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  190 */     this.cacheEnabledDS = paramOracleDataSource;
/*      */     
/*  192 */     initializeConnectionCache();
/*  193 */     setConnectionCacheProperties(paramProperties);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  200 */     defaultUserPrePopulateCache(this.cacheInitialLimit);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void defaultUserPrePopulateCache(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  213 */     if (paramInt > 0)
/*      */     {
/*  215 */       String str1 = this.defaultUser;
/*  216 */       String str2 = this.defaultPassword;
/*      */       
/*  218 */       validateUser(str1, str2);
/*      */       
/*  220 */       OraclePooledConnection localOraclePooledConnection = null;
/*      */       
/*  222 */       for (int i = 0; i < paramInt; i++)
/*      */       {
/*  224 */         localOraclePooledConnection = makeOneConnection(str1, str2);
/*  225 */         synchronized (this)
/*      */         {
/*  227 */           if (localOraclePooledConnection != null)
/*      */           {
/*      */ 
/*      */ 
/*  231 */             this.cacheSize -= 1;
/*  232 */             storeCacheConnection(null, localOraclePooledConnection);
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
/*      */ 
/*      */   protected void initializeConnectionCache()
/*      */     throws SQLException
/*      */   {
/*  247 */     this.userMap = new HashMap();
/*  248 */     this.checkedOutConnectionList = new Vector();
/*      */     
/*  250 */     if (this.cacheManager == null) {
/*  251 */       this.cacheManager = OracleConnectionCacheManager.getConnectionCacheManagerInstance();
/*      */     }
/*      */     
/*  254 */     if ((this.cacheEnabledDS.user != null) && (!this.cacheEnabledDS.user.startsWith("\"")))
/*      */     {
/*  256 */       this.defaultUser = this.cacheEnabledDS.user.toLowerCase();
/*      */     } else {
/*  258 */       this.defaultUser = this.cacheEnabledDS.user;
/*      */     }
/*  260 */     this.defaultPassword = this.cacheEnabledDS.password;
/*  261 */     if (this.connectionPoolDS == null)
/*      */     {
/*  263 */       if ((this.cacheEnabledDS instanceof OracleXADataSource))
/*      */       {
/*  265 */         this.connectionPoolDS = new OracleXADataSource();
/*      */       }
/*      */       else
/*      */       {
/*  269 */         this.connectionPoolDS = new OracleConnectionPoolDataSource();
/*      */       }
/*      */       
/*      */ 
/*  273 */       this.cacheEnabledDS.copy(this.connectionPoolDS);
/*      */     }
/*      */     
/*  276 */     if ((this.fastConnectionFailoverEnabled = this.cacheEnabledDS.getFastConnectionFailoverEnabled()))
/*      */     {
/*      */ 
/*  279 */       this.rand = new Random(0L);
/*  280 */       this.instancesToRetireQueue = new Vector();
/*  281 */       this.cacheManager.failoverEnabledCacheCount += 1;
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
/*      */   private void validateUser(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  295 */     if ((paramString1 == null) || (paramString2 == null))
/*      */     {
/*  297 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 79);
/*  298 */       localSQLException.fillInStackTrace();
/*  299 */       throw localSQLException;
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
/*      */   protected Connection getConnection(String paramString1, String paramString2, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  319 */     OraclePooledConnection localOraclePooledConnection = null;
/*  320 */     Connection localConnection = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  329 */       if (this.disableConnectionRequest)
/*      */       {
/*      */ 
/*  332 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 142);
/*  333 */         localSQLException1.fillInStackTrace();
/*  334 */         throw localSQLException1;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  339 */       validateUser(paramString1, paramString2);
/*      */       
/*      */ 
/*  342 */       if (!paramString1.startsWith("\"")) {
/*  343 */         paramString1 = paramString1.toLowerCase();
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
/*      */ 
/*  358 */       if (getNumberOfCheckedOutConnections() < this.cacheMaxLimit)
/*      */       {
/*  360 */         localOraclePooledConnection = getCacheConnection(paramString1, paramString2, paramProperties);
/*      */       }
/*      */       
/*      */ 
/*  364 */       if (localOraclePooledConnection == null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  371 */         processConnectionCacheCallback();
/*      */         
/*  373 */         if (this.cacheSize > 0) {
/*  374 */           localOraclePooledConnection = getCacheConnection(paramString1, paramString2, paramProperties);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  383 */         if ((localOraclePooledConnection == null) && (this.cacheConnectionWaitTimeout > 0))
/*      */         {
/*  385 */           long l1 = this.cacheConnectionWaitTimeout * 1000L;
/*  386 */           long l2 = System.currentTimeMillis();
/*  387 */           long l3 = 0L;
/*      */           do
/*      */           {
/*  390 */             processConnectionWaitTimeout(l1);
/*      */             
/*  392 */             if (this.cacheSize > 0) {
/*  393 */               localOraclePooledConnection = getCacheConnection(paramString1, paramString2, paramProperties);
/*      */             }
/*  395 */             l3 = System.currentTimeMillis();
/*  396 */             l1 -= System.currentTimeMillis() - l2;
/*  397 */             l2 = l3;
/*      */ 
/*      */           }
/*  400 */           while ((localOraclePooledConnection == null) && (l1 > 0L));
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  405 */       if ((localOraclePooledConnection != null) && (localOraclePooledConnection.physicalConn != null))
/*      */       {
/*      */ 
/*      */ 
/*  409 */         localConnection = localOraclePooledConnection.getConnection();
/*      */         
/*  411 */         if (localConnection != null)
/*      */         {
/*  413 */           if ((this.cacheValidateConnection) && (testDatabaseConnection((OracleConnection)localConnection) != 0))
/*      */           {
/*      */ 
/*      */ 
/*  417 */             ((OracleConnection)localConnection).close(4096);
/*      */             
/*      */ 
/*  420 */             SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 143);
/*  421 */             localSQLException2.fillInStackTrace();
/*  422 */             throw localSQLException2;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*  427 */           if (this.cacheAbandonedConnectionTimeout > 0) {
/*  428 */             ((OracleConnection)localConnection).setAbandonedTimeoutEnabled(true);
/*      */           }
/*      */           
/*  431 */           if (this.cacheTimeToLiveTimeout > 0) {
/*  432 */             ((OracleConnection)localConnection).setStartTime(System.currentTimeMillis());
/*      */           }
/*  434 */           synchronized (this)
/*      */           {
/*      */ 
/*      */ 
/*  438 */             this.cacheSize -= 1;
/*  439 */             this.checkedOutConnectionList.addElement(localOraclePooledConnection);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (SQLException localSQLException3) {
/*  445 */       synchronized (this)
/*      */       {
/*  447 */         if (localOraclePooledConnection != null)
/*      */         {
/*  449 */           this.cacheSize -= 1;
/*  450 */           abortConnection(localOraclePooledConnection);
/*      */         }
/*      */       }
/*  453 */       throw localSQLException3;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  459 */     return localConnection;
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
/*      */   private OraclePooledConnection getCacheConnection(String paramString1, String paramString2, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  475 */     OraclePooledConnection localOraclePooledConnection = retrieveCacheConnection(paramString1, paramString2, paramProperties);
/*      */     
/*  477 */     if (localOraclePooledConnection == null)
/*      */     {
/*  479 */       localOraclePooledConnection = makeOneConnection(paramString1, paramString2);
/*      */       
/*  481 */       if ((localOraclePooledConnection != null) && (paramProperties != null) && (!paramProperties.isEmpty())) {
/*  482 */         setUnMatchedAttributes(paramProperties, localOraclePooledConnection);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  490 */     return localOraclePooledConnection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   OraclePooledConnection makeOneConnection(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  502 */     OraclePooledConnection localOraclePooledConnection = null;
/*  503 */     int i = 0;
/*  504 */     synchronized (this)
/*      */     {
/*      */ 
/*  507 */       if (getTotalCachedConnections() + this.pendingCreationRequests < this.cacheMaxLimit)
/*      */       {
/*      */ 
/*  510 */         this.pendingCreationRequests += 1;
/*  511 */         i = 1;
/*      */       }
/*      */     }
/*      */     
/*  515 */     if (i != 0)
/*      */     {
/*      */       try
/*      */       {
/*  519 */         localOraclePooledConnection = makeCacheConnection(paramString1, paramString2);
/*      */ 
/*      */ 
/*      */       }
/*      */       finally
/*      */       {
/*      */ 
/*      */ 
/*  527 */         synchronized (this)
/*      */         {
/*  529 */           if (localOraclePooledConnection != null)
/*  530 */             this.connectionCreatedCount += 1;
/*  531 */           this.pendingCreationRequests -= 1;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  536 */     return localOraclePooledConnection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int getTotalCachedConnections()
/*      */   {
/*  547 */     return this.cacheSize + getNumberOfCheckedOutConnections();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int getNumberOfCheckedOutConnections()
/*      */   {
/*  558 */     return this.checkedOutConnectionList.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private synchronized OraclePooledConnection retrieveCacheConnection(String paramString1, String paramString2, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  570 */     OraclePooledConnection localOraclePooledConnection = null;
/*      */     
/*  572 */     OracleConnectionCacheEntry localOracleConnectionCacheEntry = (OracleConnectionCacheEntry)this.userMap.get(OraclePooledConnection.generateKey(paramString1, paramString2));
/*      */     
/*      */ 
/*  575 */     if (localOracleConnectionCacheEntry != null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  580 */       if ((paramProperties == null) || ((paramProperties != null) && (paramProperties.isEmpty())))
/*      */       {
/*  582 */         if (localOracleConnectionCacheEntry.userConnList != null) {
/*  583 */           localOraclePooledConnection = retrieveFromConnectionList(localOracleConnectionCacheEntry.userConnList);
/*      */         }
/*  585 */       } else if (localOracleConnectionCacheEntry.attrConnMap != null)
/*      */       {
/*  587 */         String str = buildAttrKey(paramProperties);
/*  588 */         Vector localVector = (Vector)localOracleConnectionCacheEntry.attrConnMap.get(str);
/*      */         
/*      */ 
/*  591 */         if (localVector != null)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  598 */           localOraclePooledConnection = retrieveFromConnectionList(localVector);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  606 */         if ((localOraclePooledConnection == null) && (this.cacheClosestConnectionMatch)) {
/*  607 */           localOraclePooledConnection = retrieveClosestConnectionMatch(localOracleConnectionCacheEntry.attrConnMap, paramProperties);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  612 */         if ((localOraclePooledConnection == null) && (localOracleConnectionCacheEntry.userConnList != null)) {
/*  613 */           localOraclePooledConnection = retrieveFromConnectionList(localOracleConnectionCacheEntry.userConnList);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  618 */     if (localOraclePooledConnection != null)
/*      */     {
/*  620 */       if ((paramProperties != null) && (!paramProperties.isEmpty())) {
/*  621 */         setUnMatchedAttributes(paramProperties, localOraclePooledConnection);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  627 */     return localOraclePooledConnection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private OraclePooledConnection retrieveClosestConnectionMatch(HashMap paramHashMap, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  639 */     OraclePooledConnection localOraclePooledConnection1 = null;
/*  640 */     OraclePooledConnection localOraclePooledConnection2 = null;
/*  641 */     Object localObject = null;
/*      */     
/*  643 */     int i = paramProperties.size();
/*  644 */     int j = 0;
/*  645 */     int k = 0;
/*  646 */     int m = 0;
/*  647 */     int n = 0;
/*  648 */     int i1 = 0;
/*      */     
/*  650 */     if (this.cacheAttributeWeights != null) {
/*  651 */       j = getAttributesWeightCount(paramProperties, null);
/*      */     }
/*  653 */     if ((paramHashMap != null) && (!paramHashMap.isEmpty()))
/*      */     {
/*      */ 
/*      */ 
/*  657 */       Iterator localIterator = paramHashMap.entrySet().iterator();
/*      */       
/*  659 */       while (localIterator.hasNext())
/*      */       {
/*  661 */         Map.Entry localEntry = (Map.Entry)localIterator.next();
/*      */         
/*  663 */         Vector localVector = (Vector)localEntry.getValue();
/*  664 */         Object[] arrayOfObject = localVector.toArray();
/*  665 */         int i2 = localVector.size();
/*      */         
/*  667 */         for (int i3 = 0; i3 < i2; i3++)
/*      */         {
/*  669 */           localOraclePooledConnection1 = (OraclePooledConnection)arrayOfObject[i3];
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  676 */           if ((localOraclePooledConnection1.cachedConnectionAttributes != null) && (!localOraclePooledConnection1.cachedConnectionAttributes.isEmpty()) && (localOraclePooledConnection1.cachedConnectionAttributes.size() <= i))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  690 */             if (j > 0)
/*      */             {
/*  692 */               m = getAttributesWeightCount(paramProperties, localOraclePooledConnection1.cachedConnectionAttributes);
/*      */               
/*      */ 
/*      */ 
/*  696 */               if (m > k)
/*      */               {
/*  698 */                 localOraclePooledConnection2 = localOraclePooledConnection1;
/*  699 */                 k = m;
/*  700 */                 localObject = localVector;
/*      */               }
/*      */             }
/*      */             else
/*      */             {
/*  705 */               i1 = getAttributesMatchCount(paramProperties, localOraclePooledConnection1.cachedConnectionAttributes);
/*      */               
/*      */ 
/*  708 */               if (i1 > n)
/*      */               {
/*  710 */                 localOraclePooledConnection2 = localOraclePooledConnection1;
/*  711 */                 n = i1;
/*  712 */                 localObject = localVector;
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  720 */     if (localObject != null) {
/*  721 */       ((Vector)localObject).remove(localOraclePooledConnection2);
/*      */     }
/*  723 */     return localOraclePooledConnection2;
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
/*      */   private int getAttributesMatchCount(Properties paramProperties1, Properties paramProperties2)
/*      */     throws SQLException
/*      */   {
/*  738 */     int i = 0;
/*  739 */     Map.Entry localEntry = null;
/*  740 */     Object localObject1 = null;
/*  741 */     Object localObject2 = null;
/*      */     
/*  743 */     Iterator localIterator = paramProperties1.entrySet().iterator();
/*      */     
/*  745 */     while (localIterator.hasNext())
/*      */     {
/*  747 */       localEntry = (Map.Entry)localIterator.next();
/*  748 */       localObject1 = localEntry.getKey();
/*  749 */       localObject2 = localEntry.getValue();
/*      */       
/*  751 */       if ((paramProperties2.containsKey(localObject1)) && (localObject2.equals(paramProperties2.get(localObject1))))
/*      */       {
/*  753 */         i++; }
/*      */     }
/*  755 */     return i;
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
/*      */   private int getAttributesWeightCount(Properties paramProperties1, Properties paramProperties2)
/*      */     throws SQLException
/*      */   {
/*  784 */     Map.Entry localEntry = null;
/*  785 */     Object localObject1 = null;
/*  786 */     Object localObject2 = null;
/*  787 */     int i = 0;
/*      */     
/*  789 */     Iterator localIterator = paramProperties1.entrySet().iterator();
/*      */     
/*  791 */     while (localIterator.hasNext())
/*      */     {
/*  793 */       localEntry = (Map.Entry)localIterator.next();
/*  794 */       localObject1 = localEntry.getKey();
/*  795 */       localObject2 = localEntry.getValue();
/*      */       
/*      */ 
/*  798 */       if (paramProperties2 == null)
/*      */       {
/*  800 */         if (this.cacheAttributeWeights.containsKey(localObject1))
/*      */         {
/*  802 */           i += Integer.parseInt((String)this.cacheAttributeWeights.get(localObject1));
/*      */         }
/*      */         
/*      */       }
/*  806 */       else if ((paramProperties2.containsKey(localObject1)) && (localObject2.equals(paramProperties2.get(localObject1))))
/*      */       {
/*      */ 
/*  809 */         if (this.cacheAttributeWeights.containsKey(localObject1))
/*      */         {
/*  811 */           i += Integer.parseInt((String)this.cacheAttributeWeights.get(localObject1));
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*  817 */           i++;
/*      */         }
/*      */       }
/*      */     }
/*  821 */     return i;
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
/*      */   private void setUnMatchedAttributes(Properties paramProperties, OraclePooledConnection paramOraclePooledConnection)
/*      */     throws SQLException
/*      */   {
/*  835 */     if (paramOraclePooledConnection.unMatchedCachedConnAttr == null) {
/*  836 */       paramOraclePooledConnection.unMatchedCachedConnAttr = new Properties();
/*      */     } else {
/*  838 */       paramOraclePooledConnection.unMatchedCachedConnAttr.clear();
/*      */     }
/*  840 */     if (!this.cacheClosestConnectionMatch)
/*      */     {
/*  842 */       paramOraclePooledConnection.unMatchedCachedConnAttr.putAll(paramProperties);
/*      */     }
/*      */     else
/*      */     {
/*  846 */       Properties localProperties = paramOraclePooledConnection.cachedConnectionAttributes;
/*  847 */       Map.Entry localEntry = null;
/*  848 */       Object localObject1 = null;
/*  849 */       Object localObject2 = null;
/*      */       
/*  851 */       Iterator localIterator = paramProperties.entrySet().iterator();
/*      */       
/*  853 */       while (localIterator.hasNext())
/*      */       {
/*  855 */         localEntry = (Map.Entry)localIterator.next();
/*  856 */         localObject1 = localEntry.getKey();
/*  857 */         localObject2 = localEntry.getValue();
/*      */         
/*  859 */         if ((!localProperties.containsKey(localObject1)) && (!localObject2.equals(localProperties.get(localObject1))))
/*      */         {
/*  861 */           paramOraclePooledConnection.unMatchedCachedConnAttr.put(localObject1, localObject2);
/*      */         }
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
/*      */   private OraclePooledConnection retrieveFromConnectionList(Vector paramVector)
/*      */     throws SQLException
/*      */   {
/*  878 */     if (paramVector.isEmpty()) {
/*  879 */       return null;
/*      */     }
/*  881 */     Object localObject1 = null;
/*  882 */     if (this.fastConnectionFailoverEnabled) { int i;
/*      */       Object localObject2;
/*      */       Object localObject3;
/*  885 */       if ((this.useGoodGroup) && (this.databaseInstancesList != null) && (this.databaseInstancesList.size() > 0))
/*      */       {
/*      */         label234:
/*  888 */         synchronized (this.databaseInstancesList)
/*      */         {
/*  890 */           i = this.databaseInstancesList.size();
/*  891 */           localObject2 = null;
/*  892 */           localObject3 = 0;
/*      */           
/*  894 */           boolean[] arrayOfBoolean = new boolean[i];
/*  895 */           int k = this.dbInstancePercentTotal;
/*      */           
/*      */ 
/*  898 */           for (int m = 0; m < i; m++)
/*      */           {
/*  900 */             Object localObject4 = 0;
/*      */             
/*      */ 
/*  903 */             if (k <= 1) {
/*  904 */               localObject3 = 0;
/*      */             } else {
/*  906 */               localObject3 = this.rand.nextInt(k - 1);
/*      */             }
/*      */             
/*  909 */             for (int n = 0; n < i; n++)
/*      */             {
/*  911 */               localObject2 = (OracleDatabaseInstance)this.databaseInstancesList.get(n);
/*      */               
/*  913 */               if ((arrayOfBoolean[n] == 0) && (((OracleDatabaseInstance)localObject2).flag <= 3))
/*      */               {
/*  915 */                 localObject4 += ((OracleDatabaseInstance)localObject2).percent;
/*      */                 
/*      */ 
/*  918 */                 if (localObject3 <= localObject4)
/*      */                 {
/*      */ 
/*  921 */                   if (m == 0) {
/*  922 */                     localObject2.attemptedConnRequestCount += 1;
/*      */                   }
/*  924 */                   if ((localObject1 = selectConnectionFromList(paramVector, (OracleDatabaseInstance)localObject2)) != null) {
/*      */                     break label234;
/*      */                   }
/*      */                   
/*      */ 
/*      */ 
/*  930 */                   k -= ((OracleDatabaseInstance)localObject2).percent;
/*  931 */                   arrayOfBoolean[n] = true;
/*  932 */                   break;
/*      */                 }
/*      */                 
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*  940 */       else if (!this.cacheUseLIFO)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  948 */         ??? = paramVector.size();
/*  949 */         i = this.rand.nextInt(???);
/*  950 */         localObject2 = null;
/*      */         
/*  952 */         for (localObject3 = 0; localObject3 < ???; localObject3++)
/*      */         {
/*  954 */           int j = (i++ + ???) % ???;
/*  955 */           localObject2 = (OraclePooledConnection)paramVector.get(j);
/*      */           
/*      */ 
/*  958 */           if (!((OraclePooledConnection)localObject2).connectionMarkedDown)
/*      */           {
/*  960 */             localObject1 = localObject2;
/*  961 */             paramVector.remove(localObject1);
/*  962 */             break;
/*      */           }
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  968 */         localObject1 = (OraclePooledConnection)paramVector.remove(0);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  973 */       localObject1 = (OraclePooledConnection)paramVector.remove(0);
/*      */     }
/*  975 */     return (OraclePooledConnection)localObject1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private OraclePooledConnection selectConnectionFromList(Vector paramVector, OracleDatabaseInstance paramOracleDatabaseInstance)
/*      */   {
/*  987 */     Object localObject = null;
/*  988 */     OraclePooledConnection localOraclePooledConnection = null;
/*      */     
/*      */ 
/*  991 */     int i = paramVector.size();
/*  992 */     for (int j = 0; j < i; j++)
/*      */     {
/*  994 */       localOraclePooledConnection = (OraclePooledConnection)paramVector.get(j);
/*      */       
/*  996 */       if ((!localOraclePooledConnection.connectionMarkedDown) && (localOraclePooledConnection.dataSourceDbUniqNameKey == paramOracleDatabaseInstance.databaseUniqName) && (localOraclePooledConnection.dataSourceInstanceNameKey == paramOracleDatabaseInstance.instanceName))
/*      */       {
/*      */ 
/*      */ 
/* 1000 */         localObject = localOraclePooledConnection;
/* 1001 */         paramVector.remove(localObject);
/* 1002 */         break;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1009 */     return (OraclePooledConnection)localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void removeCacheConnection(OraclePooledConnection paramOraclePooledConnection)
/*      */     throws SQLException
/*      */   {
/* 1021 */     boolean bool = false;
/*      */     
/* 1023 */     OracleConnectionCacheEntry localOracleConnectionCacheEntry = paramOraclePooledConnection.removeFromImplictCache(this.userMap);
/*      */     
/*      */ 
/* 1026 */     if (localOracleConnectionCacheEntry != null)
/*      */     {
/* 1028 */       Properties localProperties = paramOraclePooledConnection.cachedConnectionAttributes;
/*      */       
/* 1030 */       if ((localProperties == null) || ((localProperties != null) && (localProperties.isEmpty())))
/*      */       {
/* 1032 */         if (localOracleConnectionCacheEntry.userConnList != null) {
/* 1033 */           bool = localOracleConnectionCacheEntry.userConnList.removeElement(paramOraclePooledConnection);
/*      */         }
/* 1035 */       } else if (localOracleConnectionCacheEntry.attrConnMap != null)
/*      */       {
/* 1037 */         String str = buildAttrKey(localProperties);
/*      */         
/* 1039 */         Vector localVector = (Vector)localOracleConnectionCacheEntry.attrConnMap.get(str);
/*      */         
/*      */ 
/* 1042 */         if (localVector != null)
/*      */         {
/*      */ 
/*      */ 
/* 1046 */           if (paramOraclePooledConnection.unMatchedCachedConnAttr != null)
/*      */           {
/* 1048 */             paramOraclePooledConnection.unMatchedCachedConnAttr.clear();
/* 1049 */             paramOraclePooledConnection.unMatchedCachedConnAttr = null;
/*      */           }
/*      */           
/* 1052 */           if (paramOraclePooledConnection.cachedConnectionAttributes != null)
/*      */           {
/* 1054 */             paramOraclePooledConnection.cachedConnectionAttributes.clear();
/* 1055 */             paramOraclePooledConnection.cachedConnectionAttributes = null;
/*      */           }
/*      */           
/* 1058 */           localProperties = null;
/* 1059 */           bool = localVector.removeElement(paramOraclePooledConnection);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1065 */     if (bool)
/*      */     {
/* 1067 */       this.cacheSize -= 1;
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
/*      */   protected void doForEveryCachedConnection(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1092 */     int i = 0;
/*      */     
/* 1094 */     synchronized (this)
/*      */     {
/* 1096 */       if ((this.userMap != null) && (!this.userMap.isEmpty()))
/*      */       {
/* 1098 */         Iterator localIterator = this.userMap.entrySet().iterator();
/*      */         
/* 1100 */         while (localIterator.hasNext())
/*      */         {
/* 1102 */           Map.Entry localEntry = (Map.Entry)localIterator.next();
/* 1103 */           OracleConnectionCacheEntry localOracleConnectionCacheEntry = (OracleConnectionCacheEntry)localEntry.getValue();
/*      */           Object localObject1;
/*      */           Object localObject2;
/*      */           OraclePooledConnection localOraclePooledConnection;
/* 1107 */           if ((localOracleConnectionCacheEntry.userConnList != null) && (!localOracleConnectionCacheEntry.userConnList.isEmpty()))
/*      */           {
/* 1109 */             localObject1 = localOracleConnectionCacheEntry.userConnList;
/* 1110 */             localObject2 = ((Vector)localObject1).toArray();
/*      */             
/* 1112 */             for (int j = 0; j < localObject2.length; j++)
/*      */             {
/* 1114 */               localOraclePooledConnection = (OraclePooledConnection)localObject2[j];
/*      */               
/* 1116 */               if ((localOraclePooledConnection != null) && (performPooledConnectionTask(localOraclePooledConnection, paramInt))) {
/* 1117 */                 i++;
/*      */               }
/*      */             }
/*      */           }
/* 1121 */           if ((localOracleConnectionCacheEntry.attrConnMap != null) && (!localOracleConnectionCacheEntry.attrConnMap.isEmpty()))
/*      */           {
/* 1123 */             localObject1 = localOracleConnectionCacheEntry.attrConnMap.entrySet().iterator();
/*      */             
/* 1125 */             while (((Iterator)localObject1).hasNext())
/*      */             {
/* 1127 */               localObject2 = (Map.Entry)((Iterator)localObject1).next();
/*      */               
/* 1129 */               Vector localVector = (Vector)((Map.Entry)localObject2).getValue();
/* 1130 */               Object[] arrayOfObject = localVector.toArray();
/*      */               
/* 1132 */               for (int k = 0; k < arrayOfObject.length; k++)
/*      */               {
/* 1134 */                 localOraclePooledConnection = (OraclePooledConnection)arrayOfObject[k];
/*      */                 
/* 1136 */                 if ((localOraclePooledConnection != null) && (performPooledConnectionTask(localOraclePooledConnection, paramInt))) {
/* 1137 */                   i++;
/*      */                 }
/*      */               }
/*      */             }
/* 1141 */             if ((paramInt == 1) || (paramInt == 32))
/*      */             {
/* 1143 */               localOracleConnectionCacheEntry.attrConnMap.clear();
/*      */             }
/*      */           }
/*      */         }
/*      */         
/* 1148 */         if ((paramInt == 1) || (paramInt == 32))
/*      */         {
/*      */ 
/* 1151 */           this.userMap.clear();
/*      */           
/* 1153 */           this.cacheSize = 0;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1163 */     if (i > 0)
/*      */     {
/*      */ 
/* 1166 */       defaultUserPrePopulateCache(i);
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
/*      */   private boolean performPooledConnectionTask(OraclePooledConnection paramOraclePooledConnection, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1187 */     boolean bool = false;
/*      */     
/* 1189 */     switch (paramInt)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */     case 2: 
/* 1195 */       if (paramOraclePooledConnection.connectionMarkedDown)
/*      */       {
/*      */ 
/*      */ 
/* 1199 */         paramOraclePooledConnection.needToAbort = true;
/* 1200 */         closeAndRemovePooledConnection(paramOraclePooledConnection);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       break;
/*      */     case 8: 
/* 1209 */       if (this.connectionsToRemove > 0)
/*      */       {
/* 1211 */         closeAndRemovePooledConnection(paramOraclePooledConnection);
/*      */         
/* 1213 */         this.connectionsToRemove -= 1;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       break;
/*      */     case 24: 
/* 1222 */       if (this.retireConnectionsCount > 0)
/*      */       {
/* 1224 */         if ((this.instanceToRetire.databaseUniqName == paramOraclePooledConnection.dataSourceDbUniqNameKey) && (this.instanceToRetire.instanceName == paramOraclePooledConnection.dataSourceInstanceNameKey))
/*      */         {
/*      */ 
/* 1227 */           closeAndRemovePooledConnection(paramOraclePooledConnection);
/* 1228 */           this.retireConnectionsCount -= 1;
/*      */           
/* 1230 */           if (getTotalCachedConnections() < this.cacheMinLimit) {
/* 1231 */             bool = true;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */       break;
/*      */     case 4096: 
/* 1240 */       Connection localConnection = paramOraclePooledConnection.getLogicalHandle();
/*      */       
/* 1242 */       if ((localConnection != null) || ((localConnection = paramOraclePooledConnection.getPhysicalHandle()) != null))
/*      */       {
/*      */ 
/* 1245 */         if (testDatabaseConnection((OracleConnection)localConnection) != 0)
/*      */         {
/*      */ 
/* 1248 */           closeAndRemovePooledConnection(paramOraclePooledConnection);
/*      */           
/* 1250 */           bool = true;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       break;
/*      */     case 8192: 
/* 1259 */       closeAndRemovePooledConnection(paramOraclePooledConnection);
/*      */       
/* 1261 */       bool = true;
/*      */       
/* 1263 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 1: 
/* 1268 */       closeAndRemovePooledConnection(paramOraclePooledConnection);
/*      */       
/* 1270 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 4: 
/* 1275 */       processInactivityTimeout(paramOraclePooledConnection);
/*      */       
/* 1277 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 16: 
/* 1282 */       setStatementCaching(paramOraclePooledConnection, this.cacheMaxStatementsLimit, false);
/*      */       
/* 1284 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 18: 
/* 1289 */       setStatementCaching(paramOraclePooledConnection, this.cacheMaxStatementsLimit, true);
/*      */       
/* 1291 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 32: 
/* 1296 */       abortConnection(paramOraclePooledConnection);
/* 1297 */       closeAndRemovePooledConnection(paramOraclePooledConnection);
/* 1298 */       break;
/*      */     }
/*      */     
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1305 */     return bool;
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
/*      */   protected synchronized void doForEveryCheckedOutConnection(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1322 */     int i = this.checkedOutConnectionList.size();
/*      */     int j;
/*      */     OraclePooledConnection localOraclePooledConnection;
/* 1325 */     switch (paramInt)
/*      */     {
/*      */     case 1: 
/* 1328 */       for (j = 0; j < i; j++)
/*      */       {
/* 1330 */         closeCheckedOutConnection((OraclePooledConnection)this.checkedOutConnectionList.get(j), false);
/*      */       }
/*      */       
/*      */ 
/* 1334 */       this.checkedOutConnectionList.removeAllElements();
/* 1335 */       break;
/*      */     
/*      */     case 24: 
/* 1338 */       for (j = 0; (j < i) && (this.retireConnectionsCount > 0); j++)
/*      */       {
/* 1340 */         localOraclePooledConnection = (OraclePooledConnection)this.checkedOutConnectionList.get(j);
/* 1341 */         if ((this.instanceToRetire.databaseUniqName == localOraclePooledConnection.dataSourceDbUniqNameKey) && (this.instanceToRetire.instanceName == localOraclePooledConnection.dataSourceInstanceNameKey))
/*      */         {
/*      */ 
/* 1344 */           localOraclePooledConnection.closeOption = 4096;
/*      */           
/*      */ 
/* 1347 */           this.retireConnectionsCount -= 2;
/*      */         }
/*      */       }
/* 1350 */       break;
/*      */     
/*      */     case 32: 
/* 1353 */       for (j = 0; j < i; j++)
/*      */       {
/* 1355 */         localOraclePooledConnection = null;
/* 1356 */         abortConnection(localOraclePooledConnection = (OraclePooledConnection)this.checkedOutConnectionList.get(j));
/* 1357 */         closeCheckedOutConnection(localOraclePooledConnection, false);
/*      */       }
/*      */       
/*      */ 
/* 1361 */       this.checkedOutConnectionList.removeAllElements();
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
/*      */   protected void closeCheckedOutConnection(OraclePooledConnection paramOraclePooledConnection, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1382 */     if (paramOraclePooledConnection != null)
/*      */     {
/* 1384 */       OracleConnection localOracleConnection1 = (OracleConnection)paramOraclePooledConnection.getLogicalHandle();
/* 1385 */       OracleConnection localOracleConnection2 = (OracleConnection)paramOraclePooledConnection.getPhysicalHandle();
/* 1386 */       boolean bool1 = localOracleConnection1.getAutoCommit();
/*      */       
/* 1388 */       if (paramBoolean)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1394 */         boolean bool2 = paramOraclePooledConnection.localTxnCommitOnClose;
/*      */         
/*      */         try
/*      */         {
/* 1398 */           paramOraclePooledConnection.localTxnCommitOnClose = false;
/* 1399 */           localOracleConnection1.cleanupAndClose(true);
/*      */           
/*      */           try
/*      */           {
/* 1403 */             if ((!bool1) && (!paramOraclePooledConnection.needToAbort)) {
/* 1404 */               localOracleConnection2.rollback();
/*      */ 
/*      */             }
/*      */             
/*      */ 
/*      */           }
/*      */           catch (SQLException localSQLException2) {}
/*      */ 
/*      */         }
/*      */         catch (SQLException localSQLException3) {}finally
/*      */         {
/*      */ 
/* 1416 */           if (paramOraclePooledConnection.localTxnCommitOnClose != bool2) {
/* 1417 */             paramOraclePooledConnection.localTxnCommitOnClose = bool2;
/*      */           }
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*      */         try
/*      */         {
/* 1425 */           if ((!bool1) && (!paramOraclePooledConnection.needToAbort))
/*      */           {
/* 1427 */             localOracleConnection2.cancel();
/* 1428 */             localOracleConnection2.rollback();
/*      */           }
/*      */         }
/*      */         catch (SQLException localSQLException1) {}
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1436 */         actualPooledConnectionClose(paramOraclePooledConnection);
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
/*      */   synchronized void storeCacheConnection(Properties paramProperties, OraclePooledConnection paramOraclePooledConnection)
/*      */     throws SQLException
/*      */   {
/* 1454 */     boolean bool = false;
/*      */     
/* 1456 */     if ((paramOraclePooledConnection == null) || (paramOraclePooledConnection.physicalConn == null))
/*      */     {
/* 1458 */       return;
/*      */     }
/*      */     
/* 1461 */     if (this.cacheInactivityTimeout > 0)
/*      */     {
/* 1463 */       paramOraclePooledConnection.setLastAccessedTime(System.currentTimeMillis());
/*      */     }
/*      */     
/* 1466 */     if (paramOraclePooledConnection.unMatchedCachedConnAttr != null)
/*      */     {
/* 1468 */       paramOraclePooledConnection.unMatchedCachedConnAttr.clear();
/* 1469 */       paramOraclePooledConnection.unMatchedCachedConnAttr = null;
/*      */     }
/*      */     
/* 1472 */     OracleConnectionCacheEntry localOracleConnectionCacheEntry = paramOraclePooledConnection.removeFromImplictCache(this.userMap);
/*      */     Object localObject1;
/*      */     Object localObject2;
/* 1475 */     if (localOracleConnectionCacheEntry != null)
/*      */     {
/* 1477 */       if ((paramProperties == null) || ((paramProperties != null) && (paramProperties.isEmpty())))
/*      */       {
/* 1479 */         if (localOracleConnectionCacheEntry.userConnList == null) {
/* 1480 */           localOracleConnectionCacheEntry.userConnList = new Vector();
/*      */         }
/* 1482 */         if (this.cacheUseLIFO)
/*      */         {
/* 1484 */           localOracleConnectionCacheEntry.userConnList.add(0, paramOraclePooledConnection);
/* 1485 */           bool = true;
/*      */         }
/*      */         else
/*      */         {
/* 1489 */           bool = localOracleConnectionCacheEntry.userConnList.add(paramOraclePooledConnection);
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/* 1495 */         paramOraclePooledConnection.cachedConnectionAttributes = paramProperties;
/*      */         
/* 1497 */         if (localOracleConnectionCacheEntry.attrConnMap == null) {
/* 1498 */           localOracleConnectionCacheEntry.attrConnMap = new HashMap();
/*      */         }
/* 1500 */         localObject1 = buildAttrKey(paramProperties);
/* 1501 */         localObject2 = (Vector)localOracleConnectionCacheEntry.attrConnMap.get(localObject1);
/*      */         
/*      */ 
/* 1504 */         if (localObject2 != null)
/*      */         {
/* 1506 */           if (this.cacheUseLIFO)
/*      */           {
/* 1508 */             ((Vector)localObject2).add(0, paramOraclePooledConnection);
/* 1509 */             bool = true;
/*      */           }
/*      */           else
/*      */           {
/* 1513 */             bool = ((Vector)localObject2).add(paramOraclePooledConnection);
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/* 1518 */           localObject2 = new Vector();
/*      */           
/* 1520 */           bool = ((Vector)localObject2).add(paramOraclePooledConnection);
/* 1521 */           localOracleConnectionCacheEntry.attrConnMap.put(localObject1, localObject2);
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 1527 */       localOracleConnectionCacheEntry = new OracleConnectionCacheEntry();
/*      */       
/* 1529 */       paramOraclePooledConnection.addToImplicitCache(this.userMap, localOracleConnectionCacheEntry);
/*      */       
/* 1531 */       if ((paramProperties == null) || ((paramProperties != null) && (paramProperties.isEmpty())))
/*      */       {
/* 1533 */         localObject1 = new Vector();
/*      */         
/* 1535 */         bool = ((Vector)localObject1).add(paramOraclePooledConnection);
/*      */         
/* 1537 */         localOracleConnectionCacheEntry.userConnList = ((Vector)localObject1);
/*      */       }
/*      */       else
/*      */       {
/* 1541 */         localObject1 = buildAttrKey(paramProperties);
/*      */         
/*      */ 
/* 1544 */         paramOraclePooledConnection.cachedConnectionAttributes = paramProperties;
/*      */         
/* 1546 */         localObject2 = new HashMap();
/* 1547 */         Vector localVector = new Vector();
/*      */         
/* 1549 */         bool = localVector.add(paramOraclePooledConnection);
/* 1550 */         ((HashMap)localObject2).put(localObject1, localVector);
/*      */         
/* 1552 */         localOracleConnectionCacheEntry.attrConnMap = ((HashMap)localObject2);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1557 */     if (bool) {
/* 1558 */       this.cacheSize += 1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1569 */     if (this.cacheConnectionWaitTimeout > 0)
/*      */     {
/* 1571 */       notifyAll();
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
/*      */   private String buildAttrKey(Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/* 1588 */     int i = paramProperties.keySet().size();
/* 1589 */     Object[] arrayOfObject = paramProperties.keySet().toArray();
/* 1590 */     int j = 1;
/* 1591 */     StringBuffer localStringBuffer = new StringBuffer();
/*      */     
/*      */ 
/* 1594 */     while (j != 0)
/*      */     {
/* 1596 */       j = 0;
/*      */       
/* 1598 */       for (k = 0; k < i - 1; k++)
/*      */       {
/* 1600 */         if (((String)arrayOfObject[k]).compareTo((String)arrayOfObject[(k + 1)]) > 0)
/*      */         {
/* 1602 */           j = 1;
/*      */           
/* 1604 */           Object localObject = arrayOfObject[k];
/*      */           
/* 1606 */           arrayOfObject[k] = arrayOfObject[(k + 1)];
/* 1607 */           arrayOfObject[(k + 1)] = localObject;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1613 */     for (int k = 0; k < i; k++) {
/* 1614 */       localStringBuffer.append(arrayOfObject[k] + "0xffff" + paramProperties.get(arrayOfObject[k]));
/*      */     }
/* 1616 */     return localStringBuffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected OraclePooledConnection makeCacheConnection(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 1629 */     OraclePooledConnection localOraclePooledConnection = (OraclePooledConnection)this.connectionPoolDS.getPooledConnection(paramString1, paramString2);
/*      */     
/*      */ 
/*      */ 
/* 1633 */     if (localOraclePooledConnection != null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1641 */       if (this.cacheMaxStatementsLimit > 0) {
/* 1642 */         setStatementCaching(localOraclePooledConnection, this.cacheMaxStatementsLimit, true);
/*      */       }
/*      */       
/* 1645 */       localOraclePooledConnection.registerImplicitCacheConnectionEventListener(new OracleConnectionCacheEventListener(this));
/*      */       
/*      */ 
/* 1648 */       localOraclePooledConnection.cachedConnectionAttributes = new Properties();
/*      */       
/*      */ 
/* 1651 */       if (this.fastConnectionFailoverEnabled)
/*      */       {
/* 1653 */         initFailoverParameters(localOraclePooledConnection);
/*      */       }
/*      */       
/*      */ 
/* 1657 */       synchronized (this)
/*      */       {
/* 1659 */         this.cacheSize += 1;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1664 */         if ((this.fastConnectionFailoverEnabled) && (this.runtimeLoadBalancingThread == null))
/*      */         {
/*      */ 
/* 1667 */           this.runtimeLoadBalancingThread = new OracleRuntimeLoadBalancingEventHandlerThread(this.dataSourceServiceName);
/*      */           
/* 1669 */           this.cacheManager.checkAndStartThread(this.runtimeLoadBalancingThread);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1675 */       localOraclePooledConnection.localTxnCommitOnClose = this.cacheLocalTxnCommitOnClose;
/*      */     }
/* 1677 */     return localOraclePooledConnection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void setStatementCaching(OraclePooledConnection paramOraclePooledConnection, int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1688 */     if (paramInt > 0) {
/* 1689 */       paramOraclePooledConnection.setStatementCacheSize(paramInt);
/*      */     }
/* 1691 */     paramOraclePooledConnection.setImplicitCachingEnabled(paramBoolean);
/* 1692 */     paramOraclePooledConnection.setExplicitCachingEnabled(paramBoolean);
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
/*      */   protected synchronized void reusePooledConnection(PooledConnection paramPooledConnection)
/*      */     throws SQLException
/*      */   {
/* 1714 */     OraclePooledConnection localOraclePooledConnection = (OraclePooledConnection)paramPooledConnection;
/* 1715 */     if ((localOraclePooledConnection != null) && (localOraclePooledConnection.physicalConn != null))
/*      */     {
/* 1717 */       if (localOraclePooledConnection.localTxnCommitOnClose)
/* 1718 */         localOraclePooledConnection.physicalConn.commit();
/* 1719 */       storeCacheConnection(localOraclePooledConnection.cachedConnectionAttributes, localOraclePooledConnection);
/*      */       
/*      */ 
/* 1722 */       this.checkedOutConnectionList.removeElement(localOraclePooledConnection);
/*      */       
/*      */ 
/*      */ 
/* 1726 */       localOraclePooledConnection.logicalHandle = null;
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
/*      */   protected void closePooledConnection(PooledConnection paramPooledConnection)
/*      */     throws SQLException
/*      */   {
/* 1749 */     if (paramPooledConnection != null)
/*      */     {
/* 1751 */       actualPooledConnectionClose((OraclePooledConnection)paramPooledConnection);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1757 */       if (((OraclePooledConnection)paramPooledConnection).closeOption == 4096) {
/* 1758 */         this.checkedOutConnectionList.removeElement(paramPooledConnection);
/*      */       }
/* 1760 */       paramPooledConnection = null;
/*      */       
/* 1762 */       if (getTotalCachedConnections() < this.cacheMinLimit) {
/* 1763 */         defaultUserPrePopulateCache(1);
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
/*      */   protected void refreshCacheConnections(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1779 */     doForEveryCachedConnection(paramInt);
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
/*      */   protected void reinitializeCacheConnections(Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/* 1795 */     int m = 0;
/*      */     
/*      */ 
/* 1798 */     synchronized (this)
/*      */     {
/* 1800 */       this.defaultUser = this.cacheEnabledDS.user;
/* 1801 */       this.defaultPassword = this.cacheEnabledDS.password;
/* 1802 */       this.fastConnectionFailoverEnabled = this.cacheEnabledDS.getFastConnectionFailoverEnabled();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1810 */       cleanupTimeoutThread();
/*      */       
/*      */ 
/*      */ 
/* 1814 */       doForEveryCheckedOutConnection(1);
/*      */       
/*      */ 
/* 1817 */       int i = this.cacheInitialLimit;
/* 1818 */       int j = this.cacheMaxLimit;
/* 1819 */       int k = this.cacheMaxStatementsLimit;
/*      */       
/*      */ 
/* 1822 */       setConnectionCacheProperties(paramProperties);
/*      */       
/* 1824 */       if (this.cacheInitialLimit > i) {
/* 1825 */         m = this.cacheInitialLimit - i;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1831 */       if (j != Integer.MAX_VALUE)
/*      */       {
/* 1833 */         if ((this.cacheMaxLimit < j) && (this.cacheSize > this.cacheMaxLimit))
/*      */         {
/* 1835 */           this.connectionsToRemove = (this.cacheSize - this.cacheMaxLimit);
/*      */           
/* 1837 */           doForEveryCachedConnection(8);
/*      */           
/* 1839 */           this.connectionsToRemove = 0;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1845 */       if (this.cacheMaxStatementsLimit != k)
/*      */       {
/* 1847 */         if (this.cacheMaxStatementsLimit == 0) {
/* 1848 */           doForEveryCachedConnection(16);
/*      */         } else {
/* 1850 */           doForEveryCachedConnection(18);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1857 */     if (m > 0)
/*      */     {
/* 1859 */       defaultUserPrePopulateCache(m);
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
/*      */   protected synchronized void setConnectionCacheProperties(Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 1886 */       if (paramProperties != null)
/*      */       {
/* 1888 */         String str = null;
/*      */         
/*      */ 
/* 1891 */         if ((str = paramProperties.getProperty("MinLimit")) != null)
/*      */         {
/* 1893 */           if ((this.cacheMinLimit = Integer.parseInt(str)) < 0) {
/* 1894 */             this.cacheMinLimit = 0;
/*      */           }
/*      */         }
/*      */         
/* 1898 */         if ((str = paramProperties.getProperty("MaxLimit")) != null)
/*      */         {
/* 1900 */           if ((this.cacheMaxLimit = Integer.parseInt(str)) < 0) {
/* 1901 */             this.cacheMaxLimit = Integer.MAX_VALUE;
/*      */           }
/*      */         }
/*      */         
/* 1905 */         if (this.cacheMaxLimit < this.cacheMinLimit) {
/* 1906 */           this.cacheMinLimit = this.cacheMaxLimit;
/*      */         }
/*      */         
/* 1909 */         if ((str = paramProperties.getProperty("InitialLimit")) != null)
/*      */         {
/* 1911 */           if ((this.cacheInitialLimit = Integer.parseInt(str)) < 0) {
/* 1912 */             this.cacheInitialLimit = 0;
/*      */           }
/*      */         }
/* 1915 */         if (this.cacheInitialLimit > this.cacheMaxLimit) {
/* 1916 */           this.cacheInitialLimit = this.cacheMaxLimit;
/*      */         }
/*      */         
/* 1919 */         if ((str = paramProperties.getProperty("MaxStatementsLimit")) != null)
/*      */         {
/* 1921 */           if ((this.cacheMaxStatementsLimit = Integer.parseInt(str)) < 0) {
/* 1922 */             this.cacheMaxStatementsLimit = 0;
/*      */           }
/*      */         }
/*      */         
/* 1926 */         localObject1 = (Properties)paramProperties.get("AttributeWeights");
/*      */         
/*      */ 
/* 1929 */         if (localObject1 != null)
/*      */         {
/* 1931 */           Map.Entry localEntry = null;
/* 1932 */           int i = 0;
/* 1933 */           Object localObject2 = null;
/*      */           
/* 1935 */           Iterator localIterator = ((Properties)localObject1).entrySet().iterator();
/*      */           
/* 1937 */           while (localIterator.hasNext())
/*      */           {
/* 1939 */             localEntry = (Map.Entry)localIterator.next();
/* 1940 */             localObject2 = localEntry.getKey();
/*      */             
/* 1942 */             if (((str = (String)((Properties)localObject1).get(localObject2)) != null) && 
/*      */             
/* 1944 */               ((i = Integer.parseInt(str)) < 0)) {
/* 1945 */               ((Properties)localObject1).put(localObject2, "0");
/*      */             }
/*      */           }
/*      */           
/* 1949 */           if (this.cacheAttributeWeights == null) {
/* 1950 */             this.cacheAttributeWeights = new Properties();
/*      */           }
/* 1952 */           this.cacheAttributeWeights.putAll((Map)localObject1);
/*      */         }
/*      */         
/*      */ 
/* 1956 */         if ((str = paramProperties.getProperty("InactivityTimeout")) != null)
/*      */         {
/* 1958 */           if ((this.cacheInactivityTimeout = Integer.parseInt(str)) < 0) {
/* 1959 */             this.cacheInactivityTimeout = 0;
/*      */           }
/*      */         }
/*      */         
/* 1963 */         if ((str = paramProperties.getProperty("TimeToLiveTimeout")) != null)
/*      */         {
/* 1965 */           if ((this.cacheTimeToLiveTimeout = Integer.parseInt(str)) < 0) {
/* 1966 */             this.cacheTimeToLiveTimeout = 0;
/*      */           }
/*      */         }
/*      */         
/* 1970 */         if ((str = paramProperties.getProperty("AbandonedConnectionTimeout")) != null)
/*      */         {
/* 1972 */           if ((this.cacheAbandonedConnectionTimeout = Integer.parseInt(str)) < 0) {
/* 1973 */             this.cacheAbandonedConnectionTimeout = 0;
/*      */           }
/*      */         }
/*      */         
/* 1977 */         if ((str = paramProperties.getProperty("LowerThresholdLimit")) != null)
/*      */         {
/* 1979 */           this.cacheLowerThresholdLimit = Integer.parseInt(str);
/*      */           
/* 1981 */           if ((this.cacheLowerThresholdLimit < 0) || (this.cacheLowerThresholdLimit > 100))
/*      */           {
/* 1983 */             this.cacheLowerThresholdLimit = 20;
/*      */           }
/*      */         }
/*      */         
/* 1987 */         if ((str = paramProperties.getProperty("PropertyCheckInterval")) != null)
/*      */         {
/* 1989 */           if ((this.cachePropertyCheckInterval = Integer.parseInt(str)) < 0)
/*      */           {
/* 1991 */             this.cachePropertyCheckInterval = 900;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1996 */         if ((str = paramProperties.getProperty("ValidateConnection")) != null) {
/* 1997 */           this.cacheValidateConnection = Boolean.valueOf(str).booleanValue();
/*      */         }
/*      */         
/* 2000 */         if ((str = paramProperties.getProperty("ClosestConnectionMatch")) != null)
/*      */         {
/* 2002 */           this.cacheClosestConnectionMatch = Boolean.valueOf(str).booleanValue();
/*      */         }
/*      */         
/*      */ 
/* 2006 */         if ((str = paramProperties.getProperty("UseLIFO")) != null) {
/* 2007 */           this.cacheUseLIFO = Boolean.valueOf(str).booleanValue();
/*      */         }
/*      */         
/* 2010 */         if ((str = paramProperties.getProperty("ConnectionWaitTimeout")) != null)
/*      */         {
/* 2012 */           if ((this.cacheConnectionWaitTimeout = Integer.parseInt(str)) < 0) {
/* 2013 */             this.cacheConnectionWaitTimeout = 0;
/*      */           }
/*      */         }
/*      */         
/* 2017 */         if ((str = paramProperties.getProperty("LocalTransactionCommitOnClose")) != null)
/*      */         {
/* 2019 */           this.cacheLocalTxnCommitOnClose = str.equalsIgnoreCase("true");
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/* 2025 */         this.cacheMinLimit = 0;
/* 2026 */         this.cacheMaxLimit = Integer.MAX_VALUE;
/* 2027 */         this.cacheInitialLimit = 0;
/* 2028 */         this.cacheMaxStatementsLimit = 0;
/* 2029 */         this.cacheAttributeWeights = null;
/* 2030 */         this.cacheInactivityTimeout = 0;
/* 2031 */         this.cacheTimeToLiveTimeout = 0;
/* 2032 */         this.cacheAbandonedConnectionTimeout = 0;
/* 2033 */         this.cacheLowerThresholdLimit = 20;
/* 2034 */         this.cachePropertyCheckInterval = 900;
/* 2035 */         this.cacheClosestConnectionMatch = false;
/* 2036 */         this.cacheValidateConnection = false;
/* 2037 */         this.cacheConnectionWaitTimeout = 0;
/* 2038 */         this.cacheLocalTxnCommitOnClose = false;
/* 2039 */         this.cacheUseLIFO = false;
/*      */       }
/*      */       
/*      */ 
/* 2043 */       if (((this.cacheInactivityTimeout > 0) || (this.cacheTimeToLiveTimeout > 0) || (this.cacheAbandonedConnectionTimeout > 0)) && (this.cachePropertyCheckInterval > 0))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 2048 */         if (this.timeoutThread == null) {
/* 2049 */           this.timeoutThread = new OracleImplicitConnectionCacheThread(this);
/*      */         }
/* 2051 */         this.cacheManager.checkAndStartThread(this.timeoutThread);
/*      */       }
/*      */       
/* 2054 */       if (this.cachePropertyCheckInterval == 0)
/*      */       {
/*      */ 
/*      */ 
/* 2058 */         cleanupTimeoutThread();
/*      */       }
/*      */       
/*      */     }
/*      */     catch (NumberFormatException localNumberFormatException)
/*      */     {
/* 2064 */       Object localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 139, "OracleImplicitConnectionCache:setConnectionCacheProperties() - NumberFormatException Occurred :" + localNumberFormatException.getMessage());
/*      */       
/*      */ 
/*      */ 
/* 2068 */       ((SQLException)localObject1).fillInStackTrace();
/* 2069 */       throw ((Throwable)localObject1);
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
/*      */   protected Properties getConnectionCacheProperties()
/*      */     throws SQLException
/*      */   {
/* 2083 */     Properties localProperties = new Properties();
/*      */     
/* 2085 */     localProperties.setProperty("MinLimit", String.valueOf(this.cacheMinLimit));
/* 2086 */     localProperties.setProperty("MaxLimit", String.valueOf(this.cacheMaxLimit));
/* 2087 */     localProperties.setProperty("InitialLimit", String.valueOf(this.cacheInitialLimit));
/*      */     
/* 2089 */     localProperties.setProperty("MaxStatementsLimit", String.valueOf(this.cacheMaxStatementsLimit));
/*      */     
/*      */ 
/* 2092 */     if (this.cacheAttributeWeights != null) {
/* 2093 */       localProperties.put("AttributeWeights", this.cacheAttributeWeights);
/*      */     } else {
/* 2095 */       localProperties.setProperty("AttributeWeights", "NULL");
/*      */     }
/* 2097 */     localProperties.setProperty("InactivityTimeout", String.valueOf(this.cacheInactivityTimeout));
/*      */     
/* 2099 */     localProperties.setProperty("TimeToLiveTimeout", String.valueOf(this.cacheTimeToLiveTimeout));
/*      */     
/* 2101 */     localProperties.setProperty("AbandonedConnectionTimeout", String.valueOf(this.cacheAbandonedConnectionTimeout));
/*      */     
/* 2103 */     localProperties.setProperty("LowerThresholdLimit", String.valueOf(this.cacheLowerThresholdLimit));
/*      */     
/* 2105 */     localProperties.setProperty("PropertyCheckInterval", String.valueOf(this.cachePropertyCheckInterval));
/*      */     
/* 2107 */     localProperties.setProperty("ConnectionWaitTimeout", String.valueOf(this.cacheConnectionWaitTimeout));
/*      */     
/* 2109 */     localProperties.setProperty("ValidateConnection", String.valueOf(this.cacheValidateConnection));
/*      */     
/* 2111 */     localProperties.setProperty("ClosestConnectionMatch", String.valueOf(this.cacheClosestConnectionMatch));
/*      */     
/* 2113 */     localProperties.setProperty("LocalTransactionCommitOnClose", String.valueOf(this.cacheLocalTxnCommitOnClose));
/*      */     
/* 2115 */     localProperties.setProperty("UseLIFO", String.valueOf(this.cacheUseLIFO));
/*      */     
/*      */ 
/* 2118 */     return localProperties;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int testDatabaseConnection(OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/* 2130 */     return paramOracleConnection.pingDatabase();
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
/*      */   protected synchronized void closeConnectionCache(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2147 */     cleanupTimeoutThread();
/*      */     
/*      */ 
/*      */ 
/* 2151 */     purgeCacheConnections(true, paramInt);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2158 */     this.connectionPoolDS = null;
/* 2159 */     this.cacheEnabledDS = null;
/* 2160 */     this.checkedOutConnectionList = null;
/* 2161 */     this.userMap = null;
/* 2162 */     this.cacheManager = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected synchronized void disableConnectionCache()
/*      */     throws SQLException
/*      */   {
/* 2174 */     this.disableConnectionRequest = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected synchronized void enableConnectionCache()
/*      */     throws SQLException
/*      */   {
/* 2186 */     this.disableConnectionRequest = false;
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
/*      */   protected void initFailoverParameters(OraclePooledConnection paramOraclePooledConnection)
/*      */     throws SQLException
/*      */   {
/* 2200 */     String str1 = null;
/* 2201 */     String str2 = null;
/* 2202 */     String str3 = null;
/*      */     
/* 2204 */     Properties localProperties = ((OracleConnection)paramOraclePooledConnection.getPhysicalHandle()).getServerSessionInfo();
/*      */     
/* 2206 */     str3 = localProperties.getProperty("INSTANCE_NAME");
/* 2207 */     if (str3 != null) {
/* 2208 */       str1 = paramOraclePooledConnection.dataSourceInstanceNameKey = str3.trim().toLowerCase().intern();
/*      */     }
/*      */     
/*      */ 
/* 2212 */     str3 = localProperties.getProperty("SERVER_HOST");
/* 2213 */     if (str3 != null) {
/* 2214 */       paramOraclePooledConnection.dataSourceHostNameKey = str3.trim().toLowerCase().intern();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2219 */     str3 = localProperties.getProperty("SERVICE_NAME");
/* 2220 */     if (str3 != null) {
/* 2221 */       this.dataSourceServiceName = str3.trim();
/*      */     }
/*      */     
/*      */ 
/* 2225 */     str3 = localProperties.getProperty("DATABASE_NAME");
/* 2226 */     if (str3 != null) {
/* 2227 */       str2 = paramOraclePooledConnection.dataSourceDbUniqNameKey = str3.trim().toLowerCase().intern();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2234 */     if (this.databaseInstancesList == null) {
/* 2235 */       this.databaseInstancesList = new LinkedList();
/*      */     }
/* 2237 */     int i = this.databaseInstancesList.size();
/* 2238 */     synchronized (this.databaseInstancesList)
/*      */     {
/* 2240 */       OracleDatabaseInstance localOracleDatabaseInstance1 = null;
/* 2241 */       int j = 0;
/*      */       
/* 2243 */       for (int k = 0; k < i; k++)
/*      */       {
/* 2245 */         localOracleDatabaseInstance1 = (OracleDatabaseInstance)this.databaseInstancesList.get(k);
/* 2246 */         if ((localOracleDatabaseInstance1.databaseUniqName == str2) && (localOracleDatabaseInstance1.instanceName == str1))
/*      */         {
/*      */ 
/* 2249 */           localOracleDatabaseInstance1.numberOfConnectionsCount += 1;
/* 2250 */           j = 1;
/* 2251 */           break;
/*      */         }
/*      */       }
/*      */       
/* 2255 */       if (j == 0)
/*      */       {
/* 2257 */         OracleDatabaseInstance localOracleDatabaseInstance2 = new OracleDatabaseInstance(str2, str1);
/*      */         
/*      */ 
/* 2260 */         localOracleDatabaseInstance2.numberOfConnectionsCount += 1;
/* 2261 */         this.databaseInstancesList.add(localOracleDatabaseInstance2);
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
/*      */   protected void processFailoverEvent(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt2)
/*      */   {
/* 2288 */     if (paramInt1 == 256)
/*      */     {
/* 2290 */       if ((paramString4.equalsIgnoreCase("down")) || (paramString4.equalsIgnoreCase("not_restarting")) || (paramString4.equalsIgnoreCase("restart_failed")))
/*      */       {
/*      */ 
/*      */ 
/* 2294 */         this.downEventCount += 1;
/*      */         
/* 2296 */         markDownLostConnections(true, false, paramString1, paramString2, paramString3, paramString4);
/*      */         
/* 2298 */         cleanupFailoverConnections(true, false, paramString1, paramString2, paramString3, paramString4);
/*      */ 
/*      */       }
/* 2301 */       else if (paramString4.equalsIgnoreCase("up"))
/*      */       {
/*      */ 
/*      */ 
/* 2305 */         if (this.downEventCount > 0) {
/* 2306 */           this.upEventCount += 1;
/*      */         }
/*      */         try
/*      */         {
/* 2310 */           processUpEvent(paramInt2);
/*      */         }
/*      */         catch (Exception localException) {}
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2318 */         this.isEntireServiceDownProcessed = false;
/*      */       }
/*      */     }
/* 2321 */     else if ((paramInt1 == 512) && (paramString4.equalsIgnoreCase("nodedown")))
/*      */     {
/*      */ 
/*      */ 
/* 2325 */       markDownLostConnections(false, true, paramString1, paramString2, paramString3, paramString4);
/*      */       
/* 2327 */       cleanupFailoverConnections(false, true, paramString1, paramString2, paramString3, paramString4);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void processUpEvent(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2404 */     int i = 0;
/* 2405 */     int j = 0;
/* 2406 */     int k = getTotalCachedConnections();
/* 2407 */     boolean bool = false;
/*      */     
/* 2409 */     synchronized (this)
/*      */     {
/*      */ 
/*      */ 
/* 2413 */       if (paramInt <= 1) {
/* 2414 */         paramInt = 2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2421 */       if ((this.downEventCount == 0) && (this.upEventCount == 0) && (getNumberOfDefaultUserConnections() > 0))
/*      */       {
/*      */ 
/*      */ 
/* 2425 */         i = (int)(this.cacheSize * 0.25D);
/*      */       }
/*      */       else
/*      */       {
/* 2429 */         i = this.defaultUserPreFailureSize;
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
/* 2441 */       if (i <= 0)
/*      */       {
/* 2443 */         if (getNumberOfDefaultUserConnections() > 0)
/*      */         {
/* 2445 */           j = (int)(this.cacheSize * 0.25D);
/* 2446 */           bool = true;
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 2455 */         j = i / paramInt;
/*      */         
/*      */ 
/*      */ 
/* 2459 */         if (j + k > this.cacheMaxLimit) {
/* 2460 */           bool = true;
/*      */         }
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
/* 2475 */       if (this.downEventCount == this.upEventCount)
/*      */       {
/* 2477 */         this.defaultUserPreFailureSize = 0;
/* 2478 */         this.downEventCount = 0;
/* 2479 */         this.upEventCount = 0;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 2484 */     if (j > 0) {
/* 2485 */       loadBalanceConnections(j, bool);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void loadBalanceConnections(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 2497 */     if (paramBoolean)
/*      */     {
/* 2499 */       this.connectionsToRemove = paramInt;
/*      */       
/* 2501 */       doForEveryCachedConnection(8);
/*      */       
/* 2503 */       this.connectionsToRemove = 0;
/*      */     }
/*      */     
/*      */ 
/* 2507 */     if (paramInt <= 10)
/*      */     {
/*      */       try
/*      */       {
/* 2511 */         defaultUserPrePopulateCache(paramInt);
/*      */ 
/*      */ 
/*      */       }
/*      */       catch (Exception localException1) {}
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/* 2523 */       int i = (int)(paramInt * 0.25D);
/*      */       
/* 2525 */       for (int j = 0; j < 4; j++)
/*      */       {
/*      */         try
/*      */         {
/* 2529 */           defaultUserPrePopulateCache(i);
/*      */         }
/*      */         catch (Exception localException2) {}
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
/*      */   private int getNumberOfDefaultUserConnections()
/*      */   {
/* 2548 */     int i = 0;
/*      */     
/* 2550 */     if ((this.userMap != null) && (!this.userMap.isEmpty()))
/*      */     {
/*      */ 
/* 2553 */       OracleConnectionCacheEntry localOracleConnectionCacheEntry = (OracleConnectionCacheEntry)this.userMap.get(OraclePooledConnection.generateKey(this.defaultUser, this.defaultPassword));
/*      */       
/*      */ 
/*      */ 
/* 2557 */       if ((localOracleConnectionCacheEntry != null) && (localOracleConnectionCacheEntry.userConnList != null) && (!localOracleConnectionCacheEntry.userConnList.isEmpty()))
/*      */       {
/*      */ 
/* 2560 */         i = localOracleConnectionCacheEntry.userConnList.size();
/*      */       }
/*      */     }
/* 2563 */     return i;
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
/*      */   synchronized void markDownLostConnections(boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2, String paramString3, String paramString4)
/*      */   {
/* 2584 */     if (!this.isEntireServiceDownProcessed)
/*      */     {
/* 2586 */       if ((this.userMap != null) && (!this.userMap.isEmpty()))
/*      */       {
/*      */ 
/*      */ 
/* 2590 */         Iterator localIterator1 = this.userMap.entrySet().iterator();
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2598 */         while (localIterator1.hasNext())
/*      */         {
/* 2600 */           int i = 0;
/*      */           
/* 2602 */           Map.Entry localEntry = (Map.Entry)localIterator1.next();
/* 2603 */           String str = null;
/* 2604 */           if ((this.defaultUser != null) && (this.defaultPassword != null)) {
/* 2605 */             str = this.defaultUser + this.defaultPassword;
/*      */           }
/*      */           
/* 2608 */           if ((str != null) && (str.equalsIgnoreCase((String)localEntry.getKey())))
/*      */           {
/* 2610 */             i = 1;
/*      */           }
/* 2612 */           OracleConnectionCacheEntry localOracleConnectionCacheEntry = (OracleConnectionCacheEntry)localEntry.getValue();
/*      */           
/*      */           Object localObject1;
/*      */           Object localObject2;
/* 2616 */           if ((localOracleConnectionCacheEntry != null) && (localOracleConnectionCacheEntry.userConnList != null) && (!localOracleConnectionCacheEntry.userConnList.isEmpty()))
/*      */           {
/*      */ 
/* 2619 */             boolean bool = false;
/* 2620 */             localObject1 = localOracleConnectionCacheEntry.userConnList.iterator();
/*      */             
/* 2622 */             while (((Iterator)localObject1).hasNext())
/*      */             {
/* 2624 */               localObject2 = (OraclePooledConnection)((Iterator)localObject1).next();
/*      */               
/* 2626 */               if (paramBoolean1) {
/* 2627 */                 bool = markDownConnectionsForServiceEvent(paramString1, paramString2, (OraclePooledConnection)localObject2);
/*      */               }
/* 2629 */               else if (paramBoolean2) {
/* 2630 */                 bool = markDownConnectionsForHostEvent(paramString3, (OraclePooledConnection)localObject2);
/*      */               }
/*      */               
/*      */ 
/* 2634 */               if ((bool) && (i != 0)) {
/* 2635 */                 this.defaultUserPreFailureSize += 1;
/*      */               }
/*      */             }
/*      */           }
/*      */           
/* 2640 */           if ((localOracleConnectionCacheEntry != null) && (localOracleConnectionCacheEntry.attrConnMap != null) && (!localOracleConnectionCacheEntry.attrConnMap.isEmpty()))
/*      */           {
/*      */ 
/* 2643 */             Iterator localIterator2 = localOracleConnectionCacheEntry.attrConnMap.entrySet().iterator();
/*      */             
/* 2645 */             while (localIterator2.hasNext())
/*      */             {
/* 2647 */               localObject1 = (Map.Entry)localIterator2.next();
/* 2648 */               localObject2 = ((Vector)((Map.Entry)localObject1).getValue()).iterator();
/*      */               
/* 2650 */               while (((Iterator)localObject2).hasNext())
/*      */               {
/* 2652 */                 OraclePooledConnection localOraclePooledConnection = (OraclePooledConnection)((Iterator)localObject2).next();
/*      */                 
/* 2654 */                 if (paramBoolean1) {
/* 2655 */                   markDownConnectionsForServiceEvent(paramString1, paramString2, localOraclePooledConnection);
/*      */                 }
/* 2657 */                 else if (paramBoolean2) {
/* 2658 */                   markDownConnectionsForHostEvent(paramString3, localOraclePooledConnection);
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/* 2665 */       if (paramString1 == null) {
/* 2666 */         this.isEntireServiceDownProcessed = true;
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
/*      */   private boolean markDownConnectionsForServiceEvent(String paramString1, String paramString2, OraclePooledConnection paramOraclePooledConnection)
/*      */   {
/* 2680 */     boolean bool = false;
/*      */     
/* 2682 */     if ((paramString1 == null) || ((paramString2 == paramOraclePooledConnection.dataSourceDbUniqNameKey) && (paramString1 == paramOraclePooledConnection.dataSourceInstanceNameKey)))
/*      */     {
/*      */ 
/*      */ 
/* 2686 */       paramOraclePooledConnection.connectionMarkedDown = true;
/* 2687 */       bool = true;
/*      */     }
/*      */     
/* 2690 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean markDownConnectionsForHostEvent(String paramString, OraclePooledConnection paramOraclePooledConnection)
/*      */   {
/* 2701 */     boolean bool = false;
/*      */     
/* 2703 */     if (paramString == paramOraclePooledConnection.dataSourceHostNameKey)
/*      */     {
/* 2705 */       paramOraclePooledConnection.connectionMarkedDown = true;
/* 2706 */       paramOraclePooledConnection.needToAbort = true;
/* 2707 */       bool = true;
/*      */     }
/* 2709 */     return bool;
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
/*      */   synchronized void cleanupFailoverConnections(boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2, String paramString3, String paramString4)
/*      */   {
/* 2731 */     OraclePooledConnection localOraclePooledConnection = null;
/* 2732 */     Object[] arrayOfObject = this.checkedOutConnectionList.toArray();
/* 2733 */     int i = this.checkedOutConnectionList.size();
/*      */     
/* 2735 */     OraclePooledConnection[] arrayOfOraclePooledConnection = new OraclePooledConnection[i];
/* 2736 */     int j = 0;
/*      */     
/* 2738 */     for (int k = 0; k < i; k++)
/*      */     {
/*      */       try
/*      */       {
/* 2742 */         localOraclePooledConnection = (OraclePooledConnection)arrayOfObject[k];
/*      */         
/* 2744 */         if (((paramBoolean1) && ((paramString1 == null) || (paramString1 == localOraclePooledConnection.dataSourceInstanceNameKey)) && (paramString2 == localOraclePooledConnection.dataSourceDbUniqNameKey)) || ((paramBoolean2) && (paramString3 == localOraclePooledConnection.dataSourceHostNameKey)))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2751 */           if ((localOraclePooledConnection.isSameUser(this.defaultUser, this.defaultPassword)) && (localOraclePooledConnection.cachedConnectionAttributes != null) && (localOraclePooledConnection.cachedConnectionAttributes.isEmpty()))
/*      */           {
/*      */ 
/* 2754 */             this.defaultUserPreFailureSize += 1;
/*      */           }
/*      */           
/* 2757 */           this.checkedOutConnectionList.removeElement(localOraclePooledConnection);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2764 */           abortConnection(localOraclePooledConnection);
/* 2765 */           localOraclePooledConnection.needToAbort = true;
/*      */           
/*      */ 
/* 2768 */           arrayOfOraclePooledConnection[(j++)] = localOraclePooledConnection;
/*      */         }
/*      */       }
/*      */       catch (Exception localException) {}
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2778 */     for (k = 0; k < j; k++)
/*      */     {
/*      */       try
/*      */       {
/* 2782 */         closeCheckedOutConnection(arrayOfOraclePooledConnection[k], false);
/*      */       }
/*      */       catch (SQLException localSQLException2) {}
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2791 */     if ((this.checkedOutConnectionList.size() < i) && (this.cacheConnectionWaitTimeout > 0))
/*      */     {
/*      */ 
/* 2794 */       notifyAll();
/*      */     }
/*      */     
/*      */ 
/*      */     try
/*      */     {
/* 2800 */       doForEveryCachedConnection(2);
/*      */     }
/*      */     catch (SQLException localSQLException1) {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2809 */     if ((this.databaseInstancesList != null) && ((i = this.databaseInstancesList.size()) > 0))
/*      */     {
/*      */ 
/* 2812 */       synchronized (this.databaseInstancesList)
/*      */       {
/* 2814 */         OracleDatabaseInstance localOracleDatabaseInstance = null;
/* 2815 */         arrayOfObject = this.databaseInstancesList.toArray();
/*      */         
/* 2817 */         for (int m = 0; m < i; m++)
/*      */         {
/* 2819 */           localOracleDatabaseInstance = (OracleDatabaseInstance)arrayOfObject[m];
/*      */           
/* 2821 */           if ((localOracleDatabaseInstance.databaseUniqName == paramString2) && (localOracleDatabaseInstance.instanceName == paramString1))
/*      */           {
/*      */ 
/*      */ 
/* 2825 */             if (localOracleDatabaseInstance.flag <= 3)
/* 2826 */               this.dbInstancePercentTotal -= localOracleDatabaseInstance.percent;
/* 2827 */             this.databaseInstancesList.remove(localOracleDatabaseInstance);
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
/*      */ 
/*      */   void zapRLBInfo()
/*      */   {
/* 2841 */     this.databaseInstancesList.clear();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected synchronized void closeAndRemovePooledConnection(OraclePooledConnection paramOraclePooledConnection)
/*      */     throws SQLException
/*      */   {
/* 2853 */     if (paramOraclePooledConnection != null)
/*      */     {
/* 2855 */       if (paramOraclePooledConnection.needToAbort) {
/* 2856 */         abortConnection(paramOraclePooledConnection);
/*      */       }
/* 2858 */       actualPooledConnectionClose(paramOraclePooledConnection);
/* 2859 */       removeCacheConnection(paramOraclePooledConnection);
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
/*      */   private void abortConnection(OraclePooledConnection paramOraclePooledConnection)
/*      */   {
/*      */     try
/*      */     {
/* 2878 */       ((OracleConnection)paramOraclePooledConnection.getPhysicalHandle()).abort();
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
/*      */ 
/*      */ 
/*      */ 
/*      */   private void actualPooledConnectionClose(OraclePooledConnection paramOraclePooledConnection)
/*      */     throws SQLException
/*      */   {
/* 2898 */     int i = 0;
/* 2899 */     if ((this.databaseInstancesList != null) && ((i = this.databaseInstancesList.size()) > 0))
/*      */     {
/*      */ 
/* 2902 */       synchronized (this.databaseInstancesList)
/*      */       {
/* 2904 */         OracleDatabaseInstance localOracleDatabaseInstance = null;
/*      */         
/* 2906 */         for (int j = 0; j < i; j++)
/*      */         {
/* 2908 */           localOracleDatabaseInstance = (OracleDatabaseInstance)this.databaseInstancesList.get(j);
/* 2909 */           if ((localOracleDatabaseInstance.databaseUniqName == paramOraclePooledConnection.dataSourceDbUniqNameKey) && (localOracleDatabaseInstance.instanceName == paramOraclePooledConnection.dataSourceInstanceNameKey))
/*      */           {
/*      */ 
/* 2912 */             if (localOracleDatabaseInstance.numberOfConnectionsCount <= 0) break;
/* 2913 */             localOracleDatabaseInstance.numberOfConnectionsCount -= 1; break;
/*      */           }
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
/*      */     try
/*      */     {
/* 2927 */       this.connectionClosedCount += 1;
/* 2928 */       paramOraclePooledConnection.close();
/*      */     }
/*      */     catch (SQLException localSQLException) {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int getCacheTimeToLiveTimeout()
/*      */   {
/* 2940 */     return this.cacheTimeToLiveTimeout;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected int getCacheInactivityTimeout()
/*      */   {
/* 2947 */     return this.cacheInactivityTimeout;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected int getCachePropertyCheckInterval()
/*      */   {
/* 2954 */     return this.cachePropertyCheckInterval;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected int getCacheAbandonedTimeout()
/*      */   {
/* 2961 */     return this.cacheAbandonedConnectionTimeout;
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
/*      */   private synchronized void processConnectionCacheCallback()
/*      */     throws SQLException
/*      */   {
/* 2976 */     float f = this.cacheMaxLimit / 100.0F;
/* 2977 */     int i = (int)(this.cacheLowerThresholdLimit * f);
/*      */     
/*      */ 
/* 2980 */     releaseBasedOnPriority(1024, i);
/*      */     
/*      */ 
/* 2983 */     if (this.cacheSize < i) {
/* 2984 */       releaseBasedOnPriority(512, i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void releaseBasedOnPriority(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2997 */     Object[] arrayOfObject = this.checkedOutConnectionList.toArray();
/*      */     
/* 2999 */     for (int i = 0; (i < arrayOfObject.length) && (this.cacheSize < paramInt2); i++)
/*      */     {
/* 3001 */       OraclePooledConnection localOraclePooledConnection = (OraclePooledConnection)arrayOfObject[i];
/* 3002 */       OracleConnection localOracleConnection = null;
/*      */       
/* 3004 */       if (localOraclePooledConnection != null) {
/* 3005 */         localOracleConnection = (OracleConnection)localOraclePooledConnection.getLogicalHandle();
/*      */       }
/* 3007 */       if (localOracleConnection != null)
/*      */       {
/* 3009 */         OracleConnectionCacheCallback localOracleConnectionCacheCallback = localOracleConnection.getConnectionCacheCallbackObj();
/*      */         
/*      */ 
/* 3012 */         if ((localOracleConnectionCacheCallback != null) && ((localOracleConnection.getConnectionCacheCallbackFlag() == 2) || (localOracleConnection.getConnectionCacheCallbackFlag() == 4)))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3018 */           if (paramInt1 == localOracleConnection.getConnectionReleasePriority())
/*      */           {
/* 3020 */             Object localObject = localOracleConnection.getConnectionCacheCallbackPrivObj();
/* 3021 */             localOracleConnectionCacheCallback.releaseConnection(localOracleConnection, localObject);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private synchronized void processConnectionWaitTimeout(long paramLong)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 3042 */       wait(paramLong);
/*      */     }
/*      */     catch (InterruptedException localInterruptedException) {}
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
/*      */   private void processInactivityTimeout(OraclePooledConnection paramOraclePooledConnection)
/*      */     throws SQLException
/*      */   {
/* 3064 */     long l1 = paramOraclePooledConnection.getLastAccessedTime();
/* 3065 */     long l2 = System.currentTimeMillis();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3073 */     if ((getTotalCachedConnections() > this.cacheMinLimit) && (l2 - l1 > this.cacheInactivityTimeout * 1000))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3079 */       closeAndRemovePooledConnection(paramOraclePooledConnection);
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
/*      */   private void cleanupTimeoutThread()
/*      */     throws SQLException
/*      */   {
/* 3093 */     if (this.timeoutThread != null)
/*      */     {
/* 3095 */       this.timeoutThread.timeToLive = false;
/*      */       
/*      */ 
/*      */ 
/* 3099 */       if (this.timeoutThread.isSleeping) {
/* 3100 */         this.timeoutThread.interrupt();
/*      */       }
/* 3102 */       this.timeoutThread = null;
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
/*      */   protected void purgeCacheConnections(boolean paramBoolean, int paramInt)
/*      */   {
/*      */     try
/*      */     {
/* 3119 */       if (paramBoolean) {
/* 3120 */         doForEveryCheckedOutConnection(paramInt);
/*      */       }
/*      */       
/* 3123 */       doForEveryCachedConnection(paramInt);
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
/*      */   protected void updateDatabaseInstance(String paramString1, String paramString2, int paramInt1, int paramInt2)
/*      */   {
/* 3140 */     if (this.databaseInstancesList == null) {
/* 3141 */       this.databaseInstancesList = new LinkedList();
/*      */     }
/* 3143 */     synchronized (this.databaseInstancesList)
/*      */     {
/* 3145 */       int i = this.databaseInstancesList.size();
/* 3146 */       int j = 0;
/*      */       
/* 3148 */       for (int k = 0; k < i; k++)
/*      */       {
/* 3150 */         OracleDatabaseInstance localOracleDatabaseInstance2 = (OracleDatabaseInstance)this.databaseInstancesList.get(k);
/*      */         
/*      */ 
/* 3153 */         if ((localOracleDatabaseInstance2.databaseUniqName == paramString1) && (localOracleDatabaseInstance2.instanceName == paramString2))
/*      */         {
/*      */ 
/* 3156 */           localOracleDatabaseInstance2.percent = paramInt1;
/* 3157 */           localOracleDatabaseInstance2.flag = paramInt2;
/* 3158 */           j = 1;
/* 3159 */           break;
/*      */         }
/*      */       }
/*      */       
/* 3163 */       if (j == 0)
/*      */       {
/* 3165 */         OracleDatabaseInstance localOracleDatabaseInstance1 = new OracleDatabaseInstance(paramString1, paramString2);
/*      */         
/*      */ 
/* 3168 */         localOracleDatabaseInstance1.percent = paramInt1;
/* 3169 */         localOracleDatabaseInstance1.flag = paramInt2;
/*      */         
/* 3171 */         this.databaseInstancesList.add(localOracleDatabaseInstance1);
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
/*      */   protected void processDatabaseInstances()
/*      */     throws SQLException
/*      */   {
/* 3186 */     OracleDatabaseInstance localOracleDatabaseInstance = null;
/*      */     
/* 3188 */     if (this.databaseInstancesList != null)
/*      */     {
/* 3190 */       synchronized (this.databaseInstancesList)
/*      */       {
/* 3192 */         int i = 0;
/* 3193 */         int j = 0;
/*      */         
/*      */ 
/* 3196 */         this.useGoodGroup = false;
/*      */         
/*      */ 
/* 3199 */         int k = this.databaseInstancesList.size();
/*      */         
/* 3201 */         for (int m = 0; m < k; m++)
/*      */         {
/* 3203 */           localOracleDatabaseInstance = (OracleDatabaseInstance)this.databaseInstancesList.get(m);
/*      */           
/*      */ 
/*      */ 
/* 3207 */           if (localOracleDatabaseInstance.flag <= 3) {
/* 3208 */             i += localOracleDatabaseInstance.percent;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 3214 */         if (i > 0)
/*      */         {
/* 3216 */           this.dbInstancePercentTotal = i;
/* 3217 */           this.useGoodGroup = true;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3224 */         if (k > 1)
/*      */         {
/*      */ 
/* 3227 */           for (m = 0; m < k; m++)
/*      */           {
/* 3229 */             localOracleDatabaseInstance = (OracleDatabaseInstance)this.databaseInstancesList.get(m);
/* 3230 */             this.countTotal += localOracleDatabaseInstance.attemptedConnRequestCount;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3238 */           if (this.countTotal > k * 1000)
/*      */           {
/* 3240 */             for (m = 0; m < k; m++)
/*      */             {
/* 3242 */               localOracleDatabaseInstance = (OracleDatabaseInstance)this.databaseInstancesList.get(m);
/*      */               
/*      */ 
/* 3245 */               float f1 = localOracleDatabaseInstance.attemptedConnRequestCount / this.countTotal;
/*      */               
/* 3247 */               float f2 = localOracleDatabaseInstance.numberOfConnectionsCount / getTotalCachedConnections();
/*      */               
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3260 */               if (f2 > f1 * 2.0F)
/*      */               {
/*      */ 
/*      */ 
/* 3264 */                 if ((int)(localOracleDatabaseInstance.numberOfConnectionsCount * 0.25D) >= 1) {
/* 3265 */                   this.instancesToRetireQueue.addElement(localOracleDatabaseInstance);
/*      */                 }
/* 3267 */                 j = 1;
/*      */               }
/*      */             }
/*      */             
/*      */ 
/* 3272 */             if (j != 0)
/*      */             {
/* 3274 */               for (m = 0; m < k; m++)
/*      */               {
/* 3276 */                 localOracleDatabaseInstance = (OracleDatabaseInstance)this.databaseInstancesList.get(m);
/*      */                 
/* 3278 */                 localOracleDatabaseInstance.attemptedConnRequestCount = 0;
/*      */               }
/* 3280 */               j = 0;
/*      */             }
/*      */           }
/*      */         }
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
/* 3297 */       if (this.instancesToRetireQueue.size() > 0)
/*      */       {
/* 3299 */         if (this.gravitateCacheThread != null)
/*      */         {
/*      */           try
/*      */           {
/* 3303 */             this.gravitateCacheThread.interrupt();
/* 3304 */             this.gravitateCacheThread.join();
/*      */           }
/*      */           catch (InterruptedException localInterruptedException) {}
/*      */           
/*      */ 
/*      */ 
/*      */ 
/* 3311 */           this.gravitateCacheThread = null;
/*      */         }
/*      */         
/*      */ 
/* 3315 */         this.gravitateCacheThread = new OracleGravitateConnectionCacheThread(this);
/*      */         
/*      */ 
/* 3318 */         this.cacheManager.checkAndStartThread(this.gravitateCacheThread);
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
/*      */   protected void gravitateCache()
/*      */   {
/* 3333 */     while (this.instancesToRetireQueue.size() > 0)
/*      */     {
/*      */ 
/* 3336 */       this.instanceToRetire = ((OracleDatabaseInstance)this.instancesToRetireQueue.remove(0));
/* 3337 */       this.retireConnectionsCount = ((int)(this.instanceToRetire.numberOfConnectionsCount * 0.25D));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       try
/*      */       {
/* 3345 */         doForEveryCachedConnection(24);
/*      */       }
/*      */       catch (SQLException localSQLException1) {}
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3359 */       if (this.retireConnectionsCount > 0)
/*      */       {
/*      */ 
/*      */         try
/*      */         {
/*      */ 
/* 3365 */           doForEveryCheckedOutConnection(24);
/*      */         }
/*      */         catch (SQLException localSQLException2) {}
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3376 */     this.retireConnectionsCount = 0;
/* 3377 */     this.instanceToRetire = null;
/* 3378 */     this.countTotal = 0;
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
/*      */   protected void cleanupRLBThreads()
/*      */   {
/* 3397 */     if (this.gravitateCacheThread != null)
/*      */     {
/*      */       try
/*      */       {
/* 3401 */         this.gravitateCacheThread.interrupt();
/* 3402 */         this.gravitateCacheThread.join();
/*      */       }
/*      */       catch (InterruptedException localInterruptedException) {}
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 3409 */       this.gravitateCacheThread = null;
/*      */     }
/*      */     
/* 3412 */     if (this.runtimeLoadBalancingThread != null)
/*      */     {
/*      */       try
/*      */       {
/* 3416 */         this.runtimeLoadBalancingThread.interrupt();
/*      */       }
/*      */       catch (Exception localException) {}
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 3423 */       this.runtimeLoadBalancingThread = null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   Map getStatistics()
/*      */     throws SQLException
/*      */   {
/* 3435 */     HashMap localHashMap = new HashMap(2);
/* 3436 */     localHashMap.put("PhysicalConnectionClosedCount", new Integer(this.connectionClosedCount));
/* 3437 */     localHashMap.put("PhysicalConnectionCreatedCount", new Integer(this.connectionCreatedCount));
/*      */     
/* 3439 */     return localHashMap;
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
/* 3454 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 3459 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleImplicitConnectionCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */