/*      */ package oracle.jdbc.pool;
/*      */ 
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedActionException;
/*      */ import java.security.PrivilegedExceptionAction;
/*      */ import java.sql.SQLException;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.concurrent.atomic.AtomicInteger;
/*      */ import javax.sql.ConnectionPoolDataSource;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.ons.ONS;
/*      */ import oracle.ons.ONSException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */ public class OracleConnectionCacheManager
/*      */ {
/*   59 */   private static OracleConnectionCacheManager cacheManagerInstance = null;
/*      */   
/*   61 */   protected Hashtable m_connCache = null;
/*      */   
/*      */ 
/*      */   public static final int REFRESH_INVALID_CONNECTIONS = 4096;
/*      */   
/*      */ 
/*      */   public static final int REFRESH_ALL_CONNECTIONS = 8192;
/*      */   
/*      */ 
/*      */   public static final String PHYSICAL_CONNECTION_CREATED_COUNT = "PhysicalConnectionCreatedCount";
/*      */   
/*      */ 
/*      */   public static final String PHYSICAL_CONNECTION_CLOSED_COUNT = "PhysicalConnectionClosedCount";
/*      */   
/*      */ 
/*      */   protected static final int FAILOVER_EVENT_TYPE_SERVICE = 256;
/*      */   
/*      */ 
/*      */   protected static final int FAILOVER_EVENT_TYPE_HOST = 512;
/*      */   
/*      */   protected static final String EVENT_DELIMITER = "{} =";
/*      */   
/*   83 */   protected OracleFailoverEventHandlerThread failoverEventHandlerThread = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   88 */   private static boolean isONSInitializedForRemoteSubscription = false;
/*      */   
/*      */   static final int ORAERROR_END_OF_FILE_ON_COM_CHANNEL = 3113;
/*      */   
/*      */   static final int ORAERROR_NOT_CONNECTED_TO_ORACLE = 3114;
/*      */   
/*      */   static final int ORAERROR_INIT_SHUTDOWN_IN_PROGRESS = 1033;
/*      */   
/*      */   static final int ORAERROR_ORACLE_NOT_AVAILABLE = 1034;
/*      */   
/*      */   static final int ORAERROR_IMMEDIATE_SHUTDOWN_IN_PROGRESS = 1089;
/*      */   static final int ORAERROR_SHUTDOWN_IN_PROGRESS_NO_CONN = 1090;
/*      */   static final int ORAERROR_NET_IO_EXCEPTION = 17002;
/*  101 */   protected int[] fatalErrorCodes = null;
/*  102 */   protected int failoverEnabledCacheCount = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static AtomicInteger UNNAMED_CACHE_COUNT;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private OracleConnectionCacheManager()
/*      */   {
/*  114 */     this.m_connCache = new Hashtable();
/*      */     
/*      */ 
/*  117 */     UNNAMED_CACHE_COUNT = new AtomicInteger();
/*      */   }
/*      */   
/*      */ 
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
/*      */   public static synchronized OracleConnectionCacheManager getConnectionCacheManagerInstance()
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/*  138 */       if (cacheManagerInstance == null) {
/*  139 */         cacheManagerInstance = new OracleConnectionCacheManager();
/*      */       }
/*      */     }
/*      */     catch (RuntimeException localRuntimeException) {}
/*      */     
/*      */ 
/*  145 */     return cacheManagerInstance;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public String createCache(OracleDataSource paramOracleDataSource, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  166 */     String str = null;
/*      */     
/*  168 */     if ((paramOracleDataSource == null) || (!paramOracleDataSource.getConnectionCachingEnabled()))
/*      */     {
/*      */ 
/*  171 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 137);
/*  172 */       localSQLException.fillInStackTrace();
/*  173 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  181 */     if (paramOracleDataSource.connCacheName != null)
/*      */     {
/*  183 */       str = paramOracleDataSource.connCacheName;
/*      */     }
/*      */     else
/*      */     {
/*  187 */       str = paramOracleDataSource.dataSourceName + "#0x" + Integer.toHexString(UNNAMED_CACHE_COUNT.getAndIncrement());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  192 */     createCache(str, paramOracleDataSource, paramProperties);
/*      */     
/*  194 */     return str;
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
/*      */   public void createCache(String paramString, OracleDataSource paramOracleDataSource, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  216 */     if ((paramOracleDataSource == null) || (!paramOracleDataSource.getConnectionCachingEnabled()))
/*      */     {
/*      */ 
/*  219 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 137);
/*  220 */       localSQLException1.fillInStackTrace();
/*  221 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*  225 */     if (paramString == null)
/*      */     {
/*      */ 
/*  228 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 138);
/*  229 */       localSQLException1.fillInStackTrace();
/*  230 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  235 */     if (this.m_connCache.containsKey(paramString))
/*      */     {
/*      */ 
/*  238 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 140);
/*  239 */       localSQLException1.fillInStackTrace();
/*  240 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*  244 */     boolean bool = paramOracleDataSource.getFastConnectionFailoverEnabled();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  252 */     if ((bool) && (this.failoverEventHandlerThread == null))
/*      */     {
/*      */ 
/*      */ 
/*  256 */       localObject1 = paramOracleDataSource.getONSConfiguration();
/*      */       
/*      */ 
/*      */ 
/*  260 */       if ((localObject1 != null) && (!((String)localObject1).equals("")))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  268 */         synchronized (this)
/*      */         {
/*  270 */           if (!isONSInitializedForRemoteSubscription)
/*      */           {
/*      */ 
/*      */             try
/*      */             {
/*  275 */               AccessController.doPrivileged(new PrivilegedExceptionAction()
/*      */               {
/*      */ 
/*      */ 
/*      */                 public Object run()
/*      */                   throws ONSException
/*      */                 {
/*      */ 
/*  283 */                   ONS localONS = new ONS(localObject1);
/*  284 */                   return null;
/*      */                 }
/*      */                 
/*      */               });
/*      */             }
/*      */             catch (PrivilegedActionException localPrivilegedActionException)
/*      */             {
/*  291 */               SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 175, localPrivilegedActionException);
/*  292 */               localSQLException2.fillInStackTrace();
/*  293 */               throw localSQLException2;
/*      */             }
/*      */             
/*      */ 
/*  297 */             isONSInitializedForRemoteSubscription = true;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  302 */       this.failoverEventHandlerThread = new OracleFailoverEventHandlerThread();
/*      */     }
/*      */     
/*      */ 
/*  306 */     final Object localObject1 = new OracleImplicitConnectionCache(paramOracleDataSource, paramProperties);
/*      */     
/*      */ 
/*  309 */     ((OracleImplicitConnectionCache)localObject1).cacheName = paramString;
/*  310 */     paramOracleDataSource.odsCache = ((OracleImplicitConnectionCache)localObject1);
/*      */     
/*      */ 
/*  313 */     this.m_connCache.put(paramString, localObject1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  321 */     if (bool)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  326 */       checkAndStartThread(this.failoverEventHandlerThread);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void removeCache(String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  357 */     OracleImplicitConnectionCache localOracleImplicitConnectionCache = paramString != null ? (OracleImplicitConnectionCache)this.m_connCache.remove(paramString) : null;
/*      */     
/*      */ 
/*  360 */     if (localOracleImplicitConnectionCache != null)
/*      */     {
/*  362 */       localOracleImplicitConnectionCache.disableConnectionCache();
/*      */       
/*      */ 
/*  365 */       if (paramLong > 0L)
/*      */       {
/*      */         try
/*      */         {
/*  369 */           Thread.currentThread();Thread.sleep(paramLong * 1000L);
/*      */         }
/*      */         catch (InterruptedException localInterruptedException) {}
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  378 */       if (localOracleImplicitConnectionCache.cacheEnabledDS.getFastConnectionFailoverEnabled()) {
/*  379 */         cleanupFCFThreads(localOracleImplicitConnectionCache);
/*      */       }
/*      */       
/*  382 */       localOracleImplicitConnectionCache.closeConnectionCache(paramLong < 0L ? 32 : 1);
/*      */       
/*  384 */       localOracleImplicitConnectionCache = null;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  389 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 141);
/*  390 */       localSQLException.fillInStackTrace();
/*  391 */       throw localSQLException;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void reinitializeCache(String paramString, Properties paramProperties)
/*      */     throws SQLException
/*      */   {
/*  412 */     Object localObject = paramString != null ? (OracleImplicitConnectionCache)this.m_connCache.get(paramString) : null;
/*      */     
/*      */ 
/*  415 */     if (localObject != null)
/*      */     {
/*  417 */       disableCache(paramString);
/*  418 */       ((OracleImplicitConnectionCache)localObject).reinitializeCacheConnections(paramProperties);
/*  419 */       enableCache(paramString);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  424 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 141);
/*  425 */       localSQLException.fillInStackTrace();
/*  426 */       throw localSQLException;
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
/*      */   public boolean existsCache(String paramString)
/*      */     throws SQLException
/*      */   {
/*  444 */     return this.m_connCache.containsKey(paramString);
/*      */   }
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
/*      */   public void enableCache(String paramString)
/*      */     throws SQLException
/*      */   {
/*  461 */     Object localObject = paramString != null ? (OracleImplicitConnectionCache)this.m_connCache.get(paramString) : null;
/*      */     
/*      */ 
/*  464 */     if (localObject != null)
/*      */     {
/*  466 */       ((OracleImplicitConnectionCache)localObject).enableConnectionCache();
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  471 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 141);
/*  472 */       localSQLException.fillInStackTrace();
/*  473 */       throw localSQLException;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void disableCache(String paramString)
/*      */     throws SQLException
/*      */   {
/*  492 */     Object localObject = paramString != null ? (OracleImplicitConnectionCache)this.m_connCache.get(paramString) : null;
/*      */     
/*      */ 
/*  495 */     if (localObject != null)
/*      */     {
/*  497 */       ((OracleImplicitConnectionCache)localObject).disableConnectionCache();
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  502 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 141);
/*  503 */       localSQLException.fillInStackTrace();
/*  504 */       throw localSQLException;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void refreshCache(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  528 */     Object localObject = paramString != null ? (OracleImplicitConnectionCache)this.m_connCache.get(paramString) : null;
/*      */     
/*      */     SQLException localSQLException;
/*  531 */     if (localObject != null)
/*      */     {
/*  533 */       switch (paramInt)
/*      */       {
/*      */       case 4096: 
/*      */       case 8192: 
/*  537 */         ((OracleImplicitConnectionCache)localObject).refreshCacheConnections(paramInt);
/*  538 */         break;
/*      */       
/*      */ 
/*      */       default: 
/*  542 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  543 */         localSQLException.fillInStackTrace();
/*  544 */         throw localSQLException;
/*      */       
/*      */       }
/*      */       
/*      */     }
/*      */     else
/*      */     {
/*  551 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 141);
/*  552 */       localSQLException.fillInStackTrace();
/*  553 */       throw localSQLException;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void purgeCache(String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  575 */     Object localObject = paramString != null ? (OracleImplicitConnectionCache)this.m_connCache.get(paramString) : null;
/*      */     
/*      */ 
/*  578 */     if (localObject != null)
/*      */     {
/*  580 */       ((OracleImplicitConnectionCache)localObject).purgeCacheConnections(paramBoolean, 1);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*  586 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 141);
/*  587 */       localSQLException.fillInStackTrace();
/*  588 */       throw localSQLException;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public Properties getCacheProperties(String paramString)
/*      */     throws SQLException
/*      */   {
/*  608 */     Object localObject = paramString != null ? (OracleImplicitConnectionCache)this.m_connCache.get(paramString) : null;
/*      */     
/*      */ 
/*  611 */     if (localObject != null)
/*      */     {
/*  613 */       return ((OracleImplicitConnectionCache)localObject).getConnectionCacheProperties();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  618 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 141);
/*  619 */     localSQLException.fillInStackTrace();
/*  620 */     throw localSQLException;
/*      */   }
/*      */   
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
/*      */   public String[] getCacheNameList()
/*      */     throws SQLException
/*      */   {
/*  638 */     String[] arrayOfString = (String[])this.m_connCache.keySet().toArray(new String[this.m_connCache.size()]);
/*      */     
/*  640 */     return arrayOfString;
/*      */   }
/*      */   
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
/*      */   public int getNumberOfAvailableConnections(String paramString)
/*      */     throws SQLException
/*      */   {
/*  658 */     Object localObject = paramString != null ? (OracleImplicitConnectionCache)this.m_connCache.get(paramString) : null;
/*      */     
/*      */ 
/*  661 */     if (localObject != null)
/*      */     {
/*  663 */       return ((OracleImplicitConnectionCache)localObject).cacheSize;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  668 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 141);
/*  669 */     localSQLException.fillInStackTrace();
/*  670 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
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
/*      */   public int getNumberOfActiveConnections(String paramString)
/*      */     throws SQLException
/*      */   {
/*  689 */     Object localObject = paramString != null ? (OracleImplicitConnectionCache)this.m_connCache.get(paramString) : null;
/*      */     
/*      */ 
/*  692 */     if (localObject != null)
/*      */     {
/*  694 */       return ((OracleImplicitConnectionCache)localObject).getNumberOfCheckedOutConnections();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  699 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 141);
/*  700 */     localSQLException.fillInStackTrace();
/*  701 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public synchronized void setConnectionPoolDataSource(String paramString, ConnectionPoolDataSource paramConnectionPoolDataSource)
/*      */     throws SQLException
/*      */   {
/*  729 */     Object localObject = paramString != null ? (OracleImplicitConnectionCache)this.m_connCache.get(paramString) : null;
/*      */     
/*      */     SQLException localSQLException;
/*  732 */     if (localObject != null)
/*      */     {
/*  734 */       if (((OracleImplicitConnectionCache)localObject).cacheSize > 0)
/*      */       {
/*      */ 
/*  737 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 78);
/*  738 */         localSQLException.fillInStackTrace();
/*  739 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  744 */       ((OracleConnectionPoolDataSource)paramConnectionPoolDataSource).makeURL();
/*  745 */       ((OracleConnectionPoolDataSource)paramConnectionPoolDataSource).setURL(((OracleConnectionPoolDataSource)paramConnectionPoolDataSource).url);
/*      */       
/*  747 */       ((OracleImplicitConnectionCache)localObject).connectionPoolDS = ((OracleConnectionPoolDataSource)paramConnectionPoolDataSource);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*  753 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 141);
/*  754 */       localSQLException.fillInStackTrace();
/*  755 */       throw localSQLException;
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
/*      */   protected void verifyAndHandleEvent(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  777 */     Object localObject1 = null;
/*  778 */     String str1 = null;
/*  779 */     String str2 = null;
/*  780 */     String str3 = null;
/*  781 */     Object localObject2 = null;
/*      */     
/*  783 */     int i = 0;
/*  784 */     StringTokenizer localStringTokenizer = null;
/*      */     
/*      */     try
/*      */     {
/*  788 */       localStringTokenizer = new StringTokenizer(new String(paramArrayOfByte, "UTF-8"), "{} =", true);
/*      */     }
/*      */     catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  795 */     String str4 = null;
/*  796 */     String str5 = null;
/*  797 */     String str6 = null;
/*      */     
/*      */ 
/*      */ 
/*  801 */     while (localStringTokenizer.hasMoreTokens())
/*      */     {
/*  803 */       str5 = null;
/*  804 */       str4 = localStringTokenizer.nextToken();
/*  805 */       if ((str4.equals("=")) && (localStringTokenizer.hasMoreTokens()))
/*      */       {
/*  807 */         str5 = localStringTokenizer.nextToken();
/*      */       }
/*      */       else
/*      */       {
/*  811 */         str6 = str4;
/*      */       }
/*      */       
/*  814 */       if ((str6.equalsIgnoreCase("version")) && (str5 != null) && (!str5.equals("1.0")))
/*      */       {
/*      */ 
/*      */ 
/*  818 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 146);
/*  819 */         localSQLException.fillInStackTrace();
/*  820 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  824 */       if ((str6.equalsIgnoreCase("service")) && (str5 != null)) {
/*  825 */         localObject1 = str5;
/*      */       }
/*  827 */       if ((str6.equalsIgnoreCase("instance")) && (str5 != null) && (!str5.equals(" ")))
/*      */       {
/*      */ 
/*  830 */         str1 = str5.toLowerCase().intern();
/*      */       }
/*      */       
/*  833 */       if ((str6.equalsIgnoreCase("database")) && (str5 != null)) {
/*  834 */         str2 = str5.toLowerCase().intern();
/*      */       }
/*  836 */       if ((str6.equalsIgnoreCase("host")) && (str5 != null)) {
/*  837 */         str3 = str5.toLowerCase().intern();
/*      */       }
/*  839 */       if ((str6.equalsIgnoreCase("status")) && (str5 != null)) {
/*  840 */         localObject2 = str5;
/*      */       }
/*  842 */       if ((str6.equalsIgnoreCase("card")) && (str5 != null))
/*      */       {
/*      */         try
/*      */         {
/*  846 */           i = Integer.parseInt(str5);
/*      */         }
/*      */         catch (NumberFormatException localNumberFormatException) {}
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  855 */     invokeFailoverProcessingThreads(paramInt, (String)localObject1, str1, str2, str3, (String)localObject2, i);
/*      */     
/*      */ 
/*  858 */     localStringTokenizer = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void invokeFailoverProcessingThreads(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  875 */     OracleImplicitConnectionCache localOracleImplicitConnectionCache = null;
/*  876 */     int i = 0;
/*  877 */     int j = 0;
/*      */     
/*  879 */     if (paramInt1 == 256) {
/*  880 */       i = 1;
/*      */     }
/*  882 */     if (paramInt1 == 512) {
/*  883 */       j = 1;
/*      */     }
/*  885 */     Iterator localIterator = this.m_connCache.values().iterator();
/*      */     
/*  887 */     while (localIterator.hasNext())
/*      */     {
/*  889 */       localOracleImplicitConnectionCache = (OracleImplicitConnectionCache)localIterator.next();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  899 */       if (((i != 0) && (paramString1.equalsIgnoreCase(localOracleImplicitConnectionCache.dataSourceServiceName))) || (j != 0))
/*      */       {
/*      */ 
/*      */ 
/*  903 */         OracleFailoverWorkerThread localOracleFailoverWorkerThread = new OracleFailoverWorkerThread(localOracleImplicitConnectionCache, paramInt1, paramString2, paramString3, paramString4, paramString5, paramInt2);
/*      */         
/*      */ 
/*      */ 
/*  907 */         checkAndStartThread(localOracleFailoverWorkerThread);
/*      */         
/*  909 */         localOracleImplicitConnectionCache.failoverWorkerThread = localOracleFailoverWorkerThread;
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
/*      */   protected void checkAndStartThread(Thread paramThread)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/*  927 */       if (!paramThread.isAlive())
/*      */       {
/*  929 */         paramThread.setDaemon(true);
/*  930 */         paramThread.start();
/*      */       }
/*      */     }
/*      */     catch (IllegalThreadStateException localIllegalThreadStateException) {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected boolean failoverEnabledCacheExists()
/*      */   {
/*  948 */     if (this.failoverEnabledCacheCount > 0) {
/*  949 */       return true;
/*      */     }
/*  951 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void parseRuntimeLoadBalancingEvent(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  964 */     OracleImplicitConnectionCache localOracleImplicitConnectionCache = null;
/*  965 */     Enumeration localEnumeration = this.m_connCache.elements();
/*      */     
/*  967 */     while (localEnumeration.hasMoreElements())
/*      */     {
/*      */       try
/*      */       {
/*  971 */         localOracleImplicitConnectionCache = (OracleImplicitConnectionCache)localEnumeration.nextElement();
/*  972 */         if (paramString.equalsIgnoreCase(localOracleImplicitConnectionCache.dataSourceServiceName))
/*      */         {
/*  974 */           if (paramArrayOfByte == null) {
/*  975 */             localOracleImplicitConnectionCache.zapRLBInfo();
/*      */           } else {
/*  977 */             retrieveServiceMetrics(localOracleImplicitConnectionCache, paramArrayOfByte);
/*      */           }
/*      */         }
/*      */       }
/*      */       catch (Exception localException) {}
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
/*      */   private void retrieveServiceMetrics(OracleImplicitConnectionCache paramOracleImplicitConnectionCache, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1000 */     StringTokenizer localStringTokenizer = null;
/* 1001 */     String str1 = null;
/* 1002 */     String str2 = null;
/* 1003 */     int i = 0;
/* 1004 */     int j = 0;
/* 1005 */     int k = 0;
/*      */     
/*      */     try
/*      */     {
/* 1009 */       localStringTokenizer = new StringTokenizer(new String(paramArrayOfByte, "UTF-8"), "{} =", true);
/*      */     }
/*      */     catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1017 */     String str3 = null;
/* 1018 */     String str4 = null;
/* 1019 */     String str5 = null;
/*      */     
/* 1021 */     while (localStringTokenizer.hasMoreTokens())
/*      */     {
/* 1023 */       str4 = null;
/* 1024 */       str3 = localStringTokenizer.nextToken();
/*      */       
/* 1026 */       if ((str3.equals("=")) && (localStringTokenizer.hasMoreTokens()))
/*      */       {
/* 1028 */         str4 = localStringTokenizer.nextToken();
/*      */       } else {
/* 1030 */         if (str3.equals("}"))
/*      */         {
/*      */ 
/* 1033 */           if (k == 0)
/*      */             continue;
/* 1035 */           paramOracleImplicitConnectionCache.updateDatabaseInstance(str2, str1, i, j);
/* 1036 */           k = 0; continue;
/*      */         }
/*      */         
/*      */ 
/* 1040 */         if ((str3.equals("{")) || (str3.equals(" "))) {
/*      */           continue;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1046 */         str5 = str3;
/* 1047 */         k = 1;
/*      */       }
/*      */       
/* 1050 */       if ((str5.equalsIgnoreCase("version")) && (str4 != null))
/*      */       {
/* 1052 */         if (!str4.equals("1.0"))
/*      */         {
/*      */ 
/* 1055 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 146);
/* 1056 */           localSQLException.fillInStackTrace();
/* 1057 */           throw localSQLException;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1062 */       if ((str5.equalsIgnoreCase("database")) && (str4 != null)) {
/* 1063 */         str2 = str4.toLowerCase().intern();
/*      */       }
/* 1065 */       if ((str5.equalsIgnoreCase("instance")) && (str4 != null)) {
/* 1066 */         str1 = str4.toLowerCase().intern();
/*      */       }
/* 1068 */       if ((str5.equalsIgnoreCase("percent")) && (str4 != null))
/*      */       {
/*      */         try
/*      */         {
/* 1072 */           i = Integer.parseInt(str4);
/* 1073 */           if (i == 0) { i = 1;
/*      */           }
/*      */         }
/*      */         catch (NumberFormatException localNumberFormatException) {}
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1081 */       if ((str5.equalsIgnoreCase("flag")) && (str4 != null))
/*      */       {
/* 1083 */         if (str4.equalsIgnoreCase("good")) {
/* 1084 */           j = 1;
/* 1085 */         } else if (str4.equalsIgnoreCase("violating")) {
/* 1086 */           j = 3;
/* 1087 */         } else if (str4.equalsIgnoreCase("NO_DATA")) {
/* 1088 */           j = 4;
/* 1089 */         } else if (str4.equalsIgnoreCase("UNKNOWN")) {
/* 1090 */           j = 2;
/* 1091 */         } else if (str4.equalsIgnoreCase("BLOCKED")) {
/* 1092 */           j = 5;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1097 */     paramOracleImplicitConnectionCache.processDatabaseInstances();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void cleanupFCFThreads(OracleImplicitConnectionCache paramOracleImplicitConnectionCache)
/*      */     throws SQLException
/*      */   {
/* 1110 */     cleanupFCFWorkerThread(paramOracleImplicitConnectionCache);
/* 1111 */     paramOracleImplicitConnectionCache.cleanupRLBThreads();
/*      */     
/*      */ 
/* 1114 */     if (this.failoverEnabledCacheCount <= 0) {
/* 1115 */       cleanupFCFEventHandlerThread();
/*      */     }
/*      */     
/* 1118 */     this.failoverEnabledCacheCount -= 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void cleanupFCFWorkerThread(OracleImplicitConnectionCache paramOracleImplicitConnectionCache)
/*      */     throws SQLException
/*      */   {
/* 1133 */     if (paramOracleImplicitConnectionCache.failoverWorkerThread != null)
/*      */     {
/*      */       try
/*      */       {
/* 1137 */         if (paramOracleImplicitConnectionCache.failoverWorkerThread.isAlive()) {
/* 1138 */           paramOracleImplicitConnectionCache.failoverWorkerThread.join();
/*      */         }
/*      */       }
/*      */       catch (InterruptedException localInterruptedException) {}
/*      */       
/*      */ 
/*      */ 
/* 1145 */       paramOracleImplicitConnectionCache.failoverWorkerThread = null;
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
/*      */   protected void cleanupFCFEventHandlerThread()
/*      */     throws SQLException
/*      */   {
/* 1163 */     if (this.failoverEventHandlerThread != null)
/*      */     {
/*      */       try
/*      */       {
/* 1167 */         this.failoverEventHandlerThread.interrupt();
/*      */       }
/*      */       catch (Exception localException) {}
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1174 */       this.failoverEventHandlerThread = null;
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
/*      */   public boolean isFatalConnectionError(SQLException paramSQLException)
/*      */   {
/* 1192 */     boolean bool = false;
/* 1193 */     int i = paramSQLException.getErrorCode();
/*      */     
/*      */ 
/* 1196 */     if ((i == 3113) || (i == 3114) || (i == 1033) || (i == 1034) || (i == 1089) || (i == 1090) || (i == 17002))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1204 */       bool = true;
/*      */     }
/*      */     
/*      */ 
/* 1208 */     if ((!bool) && (this.fatalErrorCodes != null))
/*      */     {
/* 1210 */       for (int j = 0; j < this.fatalErrorCodes.length; j++)
/* 1211 */         if (i == this.fatalErrorCodes[j])
/*      */         {
/* 1213 */           bool = true;
/* 1214 */           break;
/*      */         }
/*      */     }
/* 1217 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void setConnectionErrorCodes(int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/* 1232 */     if (paramArrayOfInt != null) {
/* 1233 */       this.fatalErrorCodes = paramArrayOfInt;
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
/*      */   public int[] getConnectionErrorCodes()
/*      */     throws SQLException
/*      */   {
/* 1248 */     return this.fatalErrorCodes;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public Map getStatistics(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1269 */     Map localMap = null;
/* 1270 */     OracleImplicitConnectionCache localOracleImplicitConnectionCache = null;
/*      */     
/* 1272 */     if ((this.m_connCache != null) && ((localOracleImplicitConnectionCache = (OracleImplicitConnectionCache)this.m_connCache.get(paramString)) != null))
/*      */     {
/* 1274 */       localMap = localOracleImplicitConnectionCache.getStatistics();
/*      */     }
/* 1276 */     return localMap;
/*      */   }
/*      */   
/*      */ 
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
/* 1291 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1296 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleConnectionCacheManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */