/*      */ package oracle.jdbc.replay.driver;
/*      */ 
/*      */ import java.lang.reflect.Method;
/*      */ import java.sql.Connection;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLRecoverableException;
/*      */ import java.sql.Statement;
/*      */ import java.util.EnumSet;
/*      */ import java.util.HashSet;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.ExecutorService;
/*      */ import java.util.concurrent.Executors;
/*      */ import java.util.concurrent.ThreadFactory;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.internal.OracleConnection.TransactionState;
/*      */ import oracle.jdbc.replay.OracleDataSource;
/*      */ import oracle.jdbc.replay.internal.ConnectionInitializationCallback;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class FailoverManagerImpl
/*      */   implements FailoverManager
/*      */ {
/*      */   private static final String MNGR_FEATURE_LOGGER_NAME = "oracle.jdbc.internal.replay.FailoverManagerImpl";
/*   47 */   private static Logger MNGR_REPLAY_LOGGER = null;
/*      */   
/*      */   private static final String MONITOR_TXN = "BEGIN DBMS_APP_CONT_PRVT.MONITOR_TXN; END;";
/*      */   
/*      */   private static final String BEGIN_REPLAY = "BEGIN DBMS_APP_CONT_PRVT.BEGIN_REPLAY; END;";
/*      */   
/*      */   private static final String END_REPLAY = "BEGIN DBMS_APP_CONT_PRVT.END_REPLAY; END;";
/*      */   
/*      */   private CallHistoryEntry head;
/*      */   
/*      */   private CallHistoryEntry tail;
/*      */   
/*      */ 
/*      */   static class CallHistoryEntry
/*      */   {
/*      */     Object jdbcProxy;
/*      */     Method method;
/*      */     Object[] args;
/*      */     Object result;
/*      */     String callStatus;
/*      */     long scn;
/*      */     long checksum;
/*      */     SQLException callException;
/*   70 */     CallHistoryEntry nextEntry = null;
/*   71 */     CallHistoryEntry prevEntry = null;
/*      */     
/*      */ 
/*   74 */     CallHistoryEntry nextEntrySameProxy = null;
/*   75 */     CallHistoryEntry prevEntrySameProxy = null;
/*      */     
/*      */ 
/*      */     CallHistoryEntry(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, String paramString)
/*      */     {
/*   80 */       this.jdbcProxy = paramObject;
/*   81 */       this.method = paramMethod;
/*   82 */       this.args = paramArrayOfObject;
/*   83 */       this.result = null;
/*   84 */       this.callStatus = paramString;
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
/*      */   static enum ReplayLifecycle
/*      */   {
/*  130 */     ENABLED_NOT_REPLAYING, 
/*  131 */     INTERNALLY_FAILED, 
/*  132 */     INTERNALLY_DISABLED, 
/*  133 */     ALWAYS_DISABLED, 
/*  134 */     EXTERNALLY_DISABLED, 
/*  135 */     REPLAYING_CALLBACK, 
/*  136 */     REPLAYING, 
/*  137 */     REPLAYING_LASTCALL;
/*      */     
/*      */     private ReplayLifecycle() {} }
/*  140 */   private ReplayLifecycle lifecycle = ReplayLifecycle.INTERNALLY_DISABLED;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  145 */   private boolean replayInCurrentMode = false;
/*      */   
/*      */ 
/*      */   private Object replayResult;
/*      */   
/*      */ 
/*      */   private long requestStartTime;
/*      */   
/*      */ 
/*  154 */   private long replayInitiationTimeout = 900L;
/*      */   
/*      */   private static final int REPLAY_RETRIES = 3;
/*      */   
/*  158 */   private int replayRetries = 0;
/*      */   
/*  160 */   private OracleDataSource replayDataSource = null;
/*      */   
/*      */ 
/*      */   private NonTxnReplayableBase connectionProxy;
/*      */   
/*      */   private Method callCausingReplayError;
/*      */   
/*      */   private int replayErrorCode;
/*      */   
/*      */   private String replayErrorMessage;
/*      */   
/*  171 */   private boolean doNotAbortConn = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  179 */   private static final ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactory()
/*      */   {
/*      */     private static final String THREAD_NAME = "OJDBC-AC-WORKER-THREAD";
/*      */     
/*      */ 
/*      */ 
/*      */     public Thread newThread(Runnable paramAnonymousRunnable)
/*      */     {
/*  187 */       Thread localThread = new Thread(null, paramAnonymousRunnable, "OJDBC-AC-WORKER-THREAD");
/*      */       
/*  189 */       localThread.setPriority(5);
/*  190 */       localThread.setDaemon(true);
/*      */       
/*  192 */       return localThread;
/*      */     }
/*  179 */   });
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static
/*      */   {
/*  198 */     if (MNGR_REPLAY_LOGGER == null) {
/*  199 */       MNGR_REPLAY_LOGGER = ReplayLoggerFactory.getLogger("oracle.jdbc.internal.replay.FailoverManagerImpl");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private FailoverManagerImpl(NonTxnReplayableBase paramNonTxnReplayableBase, OracleDataSource paramOracleDataSource)
/*      */     throws SQLException
/*      */   {
/*  209 */     this.connectionProxy = paramNonTxnReplayableBase;
/*  210 */     this.replayDataSource = paramOracleDataSource;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  217 */     enableTxnMonitoring((OracleConnection)this.connectionProxy.getDelegate());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static FailoverManager getFailoverManager(NonTxnReplayableBase paramNonTxnReplayableBase, OracleDataSource paramOracleDataSource)
/*      */     throws SQLException
/*      */   {
/*  227 */     return new FailoverManagerImpl(paramNonTxnReplayableBase, paramOracleDataSource);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void append(CallHistoryEntry paramCallHistoryEntry)
/*      */   {
/*  236 */     paramCallHistoryEntry.prevEntry = this.tail;
/*  237 */     paramCallHistoryEntry.nextEntry = null;
/*      */     
/*  239 */     if (this.tail != null) {
/*  240 */       this.tail.nextEntry = paramCallHistoryEntry;
/*      */     }
/*  242 */     this.tail = paramCallHistoryEntry;
/*      */     
/*      */ 
/*  245 */     if (this.head == null) {
/*  246 */       this.head = paramCallHistoryEntry;
/*      */     }
/*      */     
/*  249 */     Replayable localReplayable = (Replayable)paramCallHistoryEntry.jdbcProxy;
/*  250 */     localReplayable.addToSameProxyList(paramCallHistoryEntry);
/*      */   }
/*      */   
/*      */   private void remove(CallHistoryEntry paramCallHistoryEntry)
/*      */   {
/*  255 */     if (paramCallHistoryEntry.nextEntry != null) {
/*  256 */       paramCallHistoryEntry.nextEntry.prevEntry = paramCallHistoryEntry.prevEntry;
/*      */     }
/*  258 */     if (paramCallHistoryEntry.prevEntry != null) {
/*  259 */       paramCallHistoryEntry.prevEntry.nextEntry = paramCallHistoryEntry.nextEntry;
/*      */     }
/*  261 */     if (this.head == paramCallHistoryEntry) {
/*  262 */       this.head = paramCallHistoryEntry.nextEntry;
/*      */     }
/*  264 */     if (this.tail == paramCallHistoryEntry) {
/*  265 */       this.tail = paramCallHistoryEntry.prevEntry;
/*      */     }
/*      */     
/*  268 */     Replayable localReplayable = (Replayable)paramCallHistoryEntry.jdbcProxy;
/*  269 */     localReplayable.removeFromSameProxyList(paramCallHistoryEntry);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   CallHistoryEntry record(Object paramObject, Method paramMethod, Object[] paramArrayOfObject, String paramString)
/*      */   {
/*  278 */     synchronized (this)
/*      */     {
/*  280 */       String str = paramMethod == null ? "NULL METHOD" : paramMethod.getName();
/*  281 */       StringBuilder localStringBuilder = new StringBuilder();
/*  282 */       if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
/*      */       {
/*  284 */         for (int i = 0; i < paramArrayOfObject.length - 1; i++)
/*      */         {
/*  286 */           localStringBuilder.append(paramArrayOfObject[i]);
/*  287 */           localStringBuilder.append(",");
/*      */         }
/*  289 */         localStringBuilder.append(paramArrayOfObject[(paramArrayOfObject.length - 1)]);
/*      */       }
/*      */       
/*  292 */       MNGR_REPLAY_LOGGER.log(Level.FINEST, "On connection {0}, recording method {1}({2})", new Object[] { this.connectionProxy, str, localStringBuilder.toString() });
/*      */       
/*      */ 
/*      */ 
/*  296 */       CallHistoryEntry localCallHistoryEntry = new CallHistoryEntry(paramObject, paramMethod, paramArrayOfObject, paramString);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  301 */       append(localCallHistoryEntry);
/*      */       
/*  303 */       MNGR_REPLAY_LOGGER.log(Level.FINEST, "On connection {0}, recorded method {1}", new Object[] { this.connectionProxy, str });
/*      */       
/*      */ 
/*      */ 
/*  307 */       return localCallHistoryEntry;
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
/*      */   void update(Object paramObject1, CallHistoryEntry paramCallHistoryEntry, Object paramObject2, String paramString, long paramLong1, long paramLong2, SQLException paramSQLException)
/*      */   {
/*  323 */     synchronized (this)
/*      */     {
/*  325 */       CallHistoryEntry localCallHistoryEntry = paramCallHistoryEntry == null ? this.tail : paramCallHistoryEntry;
/*  326 */       String str = (localCallHistoryEntry == null) || (localCallHistoryEntry.method == null) ? "NULL METHOD" : localCallHistoryEntry.method.getName();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  331 */       MNGR_REPLAY_LOGGER.log(Level.FINEST, "On connection {0}, updating entry for method {1}", new Object[] { this.connectionProxy, str });
/*      */       
/*      */ 
/*      */ 
/*  335 */       localCallHistoryEntry.result = paramObject2;
/*  336 */       localCallHistoryEntry.checksum = paramLong1;
/*  337 */       localCallHistoryEntry.scn = paramLong2;
/*  338 */       localCallHistoryEntry.callException = paramSQLException;
/*      */       
/*      */ 
/*  341 */       localCallHistoryEntry.callStatus = paramString;
/*      */       
/*  343 */       MNGR_REPLAY_LOGGER.log(Level.FINEST, "On connection {0}, updated entry for method {1} - result: {2}, checksum: {3}, SCN: {4}, SQLException: {5}", new Object[] { this.connectionProxy, str, paramObject2, Long.valueOf(paramLong1), Long.valueOf(paramLong2), paramSQLException });
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   synchronized void purge()
/*      */   {
/*  353 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "On connection {0}, calling explicit purge", this.connectionProxy);
/*      */     
/*      */ 
/*      */ 
/*  357 */     for (CallHistoryEntry localCallHistoryEntry = this.head; 
/*  358 */         localCallHistoryEntry != null; 
/*  359 */         localCallHistoryEntry = localCallHistoryEntry.nextEntry) {
/*  360 */       remove(localCallHistoryEntry);
/*      */     }
/*  362 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "On connection {0}, calling explicit purge succeeds", this.connectionProxy);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   synchronized void purgeForSameProxy(Set<Object> paramSet, CallHistoryEntry paramCallHistoryEntry)
/*      */   {
/*  371 */     Object localObject1 = paramCallHistoryEntry == null ? null : paramCallHistoryEntry.jdbcProxy;
/*      */     
/*      */ 
/*  374 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "On connection {0}, calling implicit purge for {1}", new Object[] { this.connectionProxy, localObject1 });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  381 */     for (CallHistoryEntry localCallHistoryEntry = paramCallHistoryEntry; 
/*  382 */         localCallHistoryEntry != null; 
/*  383 */         localCallHistoryEntry = localCallHistoryEntry.nextEntrySameProxy)
/*      */     {
/*  385 */       Object localObject2 = localCallHistoryEntry.result;
/*      */       
/*  387 */       if ((localObject2 != null) && ((localObject2 instanceof Replayable)) && (!paramSet.contains(localObject2)))
/*      */       {
/*      */ 
/*      */ 
/*  391 */         Replayable localReplayable = (Replayable)localObject2;
/*  392 */         localReplayable.purgeSameProxyList();
/*      */         
/*      */ 
/*  395 */         paramSet.add(localReplayable);
/*      */       }
/*      */       
/*  398 */       MNGR_REPLAY_LOGGER.log(Level.FINEST, "On connection {0}, implicit purge {1}", new Object[] { this.connectionProxy, localCallHistoryEntry.method });
/*      */       
/*      */ 
/*      */ 
/*  402 */       remove(localCallHistoryEntry);
/*      */     }
/*      */     
/*  405 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "On connection {0}, calling implicit purge for {1} succeeds", new Object[] { this.connectionProxy, localObject1 });
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   synchronized boolean isEmpty()
/*      */   {
/*  412 */     return this.head == null;
/*      */   }
/*      */   
/*      */   void fillInAllChecksums()
/*      */     throws SQLException
/*      */   {
/*  418 */     synchronized (this)
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
/*  432 */       HashSet localHashSet = new HashSet();
/*  433 */       for (CallHistoryEntry localCallHistoryEntry = this.tail.prevEntry; 
/*  434 */           localCallHistoryEntry != null; 
/*  435 */           localCallHistoryEntry = localCallHistoryEntry.prevEntry)
/*      */       {
/*      */ 
/*      */ 
/*  439 */         if (!localHashSet.contains(localCallHistoryEntry.jdbcProxy))
/*      */         {
/*      */ 
/*      */ 
/*  443 */           NonTxnReplayableBase localNonTxnReplayableBase = (NonTxnReplayableBase)localCallHistoryEntry.jdbcProxy;
/*      */           
/*  445 */           localNonTxnReplayableBase.fillInChecksum(localCallHistoryEntry);
/*      */           
/*  447 */           localHashSet.add(localCallHistoryEntry.jdbcProxy);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  454 */           if ((localCallHistoryEntry.jdbcProxy instanceof ResultSet)) {
/*  455 */             localHashSet.add(localNonTxnReplayableBase.getCreator());
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   Object replayAll(SQLRecoverableException paramSQLRecoverableException)
/*      */     throws SQLException
/*      */   {
/*  465 */     synchronized (this)
/*      */     {
/*  467 */       this.replayRetries = 0;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       do
/*      */       {
/*      */         try
/*      */         {
/*  492 */           return replayAllInternal(paramSQLRecoverableException, this.replayRetries);
/*      */         }
/*      */         catch (SQLRecoverableException localSQLRecoverableException)
/*      */         {
/*  496 */           localReplayLifecycle = this.lifecycle;
/*  497 */           MNGR_REPLAY_LOGGER.log(Level.FINEST, "replayAll caught new exception: {0}, current lifecycle: {1}", new Object[] { localSQLRecoverableException, localReplayLifecycle });
/*      */           
/*      */ 
/*      */ 
/*  501 */           switch (localReplayLifecycle)
/*      */           {
/*      */ 
/*      */ 
/*      */           case REPLAYING: 
/*      */           case REPLAYING_CALLBACK: 
/*      */           case REPLAYING_LASTCALL: 
/*  508 */             this.replayRetries += 1;
/*  509 */             MNGR_REPLAY_LOGGER.log(Level.FINEST, "NEW replay attempt {0}", Integer.valueOf(this.replayRetries));
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           }
/*      */           
/*      */         }
/*      */         catch (SQLException localSQLException)
/*      */         {
/*  526 */           ReplayLifecycle localReplayLifecycle = this.lifecycle;
/*  527 */           MNGR_REPLAY_LOGGER.log(Level.FINEST, "replayAll caught new exception: {0}, current lifecycle: {1}", new Object[] { localSQLException, localReplayLifecycle });
/*      */           
/*      */ 
/*      */ 
/*  531 */           switch (localReplayLifecycle)
/*      */           {
/*      */ 
/*      */           case INTERNALLY_FAILED: 
/*  535 */             this.replayRetries += 1;
/*      */             
/*  537 */             this.lifecycle = ReplayLifecycle.REPLAYING;
/*      */             
/*  539 */             MNGR_REPLAY_LOGGER.log(Level.FINEST, "NEW replay attempt {0} after failed replay", Integer.valueOf(this.replayRetries));
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  550 */         throwOriginalExceptionWithReplayError(this.replayErrorCode, this.replayErrorMessage, paramSQLRecoverableException);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  556 */         MNGR_REPLAY_LOGGER.log(Level.FINEST, "Replaying last call throws: {0}, rethrowing back", localSQLException);
/*      */         
/*      */ 
/*  559 */         throw localSQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*  568 */       while (this.replayRetries <= 3);
/*      */       
/*  570 */       MNGR_REPLAY_LOGGER.log(Level.WARNING, "Maximum replay retries exceeded");
/*      */       
/*      */ 
/*  573 */       disableReplayAndThrowOriginalError(null, 378, "Replay disabled because maximum number of retries is exceeded", paramSQLRecoverableException);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  579 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   synchronized Object replayAllInternal(SQLRecoverableException paramSQLRecoverableException, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  589 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "Entering replayAllInternal(connection proxy={0}, original error={1})", new Object[] { this.connectionProxy, paramSQLRecoverableException });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  594 */     ReplayLifecycle localReplayLifecycle = this.lifecycle;
/*  595 */     MNGR_REPLAY_LOGGER.log(Level.FINEST, "current lifecycle:{0}", localReplayLifecycle);
/*      */     
/*      */ 
/*      */ 
/*  599 */     if ((this.lifecycle != ReplayLifecycle.ENABLED_NOT_REPLAYING) && (this.lifecycle != ReplayLifecycle.REPLAYING) && (this.lifecycle != ReplayLifecycle.REPLAYING_LASTCALL) && (this.lifecycle != ReplayLifecycle.REPLAYING_CALLBACK))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  605 */       if (this.replayErrorCode == 0)
/*      */       {
/*  607 */         this.doNotAbortConn = true;
/*  608 */         this.replayErrorCode = 370;
/*  609 */         this.replayErrorMessage = "Replay disabled";
/*      */       }
/*      */       
/*  612 */       throwReplayExceptionInternal(this.replayErrorCode, this.replayErrorMessage, paramSQLRecoverableException);
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
/*  623 */     OracleConnection localOracleConnection = (OracleConnection)this.replayDataSource.getConnectionNoProxy();
/*      */     
/*      */ 
/*  626 */     if (localOracleConnection == null)
/*      */     {
/*  628 */       MNGR_REPLAY_LOGGER.log(Level.FINE, "FAILOVER_RETRIES exceeded");
/*  629 */       disableReplayAndThrowException(null, 382, "Replay disabled because Failover_Retries is exceeded", paramSQLRecoverableException);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  636 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "Reconnect succeeded, new connection={0}", localOracleConnection);
/*      */     
/*      */ 
/*      */ 
/*  640 */     long l = System.currentTimeMillis();
/*  641 */     MNGR_REPLAY_LOGGER.log(Level.FINEST, "timestamp at replay start: {0}", Long.valueOf(l));
/*      */     
/*      */ 
/*  644 */     if (this.requestStartTime + this.replayInitiationTimeout * 1000L < l)
/*      */     {
/*  646 */       MNGR_REPLAY_LOGGER.log(Level.WARNING, "ReplayInitiationTimeout exceeded");
/*      */       
/*  648 */       disableReplayAndThrowException(null, 377, "Replay disabled because ReplayInitiationTimeout is exceeded", paramSQLRecoverableException);
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
/*  659 */     this.connectionProxy.setDelegate(localOracleConnection);
/*      */     
/*      */ 
/*  662 */     this.lifecycle = ReplayLifecycle.REPLAYING_CALLBACK;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  671 */     ConnectionInitializationCallback localConnectionInitializationCallback = this.replayDataSource.getConnectionInitializationCallback();
/*      */     
/*  673 */     if (localConnectionInitializationCallback != null)
/*      */     {
/*      */       try
/*      */       {
/*  677 */         MNGR_REPLAY_LOGGER.log(Level.FINER, "Invoking Replay Driver initialization callback with {0}", this.connectionProxy);
/*      */         
/*      */ 
/*  680 */         localConnectionInitializationCallback.initialize((Connection)this.connectionProxy);
/*  681 */         MNGR_REPLAY_LOGGER.log(Level.FINER, "Invoking initialization callback with {0} succeeded", this.connectionProxy);
/*      */ 
/*      */       }
/*      */       catch (SQLException localSQLException)
/*      */       {
/*      */ 
/*  687 */         MNGR_REPLAY_LOGGER.log(Level.FINER, "Invoking initialization callback with {0} failed", this.connectionProxy);
/*      */         
/*      */ 
/*  690 */         disableReplayAndThrowException(null, 379, "Replay disabled because Init callback failed", paramSQLRecoverableException);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  697 */       EnumSet localEnumSet = localOracleConnection.getTransactionState();
/*      */       
/*  699 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "On connection {0}, after init-callback, transaction state={1}", new Object[] { this.connectionProxy, localEnumSet });
/*      */       
/*      */ 
/*      */ 
/*  703 */       if (localEnumSet.contains(OracleConnection.TransactionState.TRANSACTION_STARTED))
/*      */       {
/*  705 */         disableReplayAndThrowException(null, 380, "Replay disabled because of open transaction in Init callback", paramSQLRecoverableException);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  713 */     this.lifecycle = ReplayLifecycle.REPLAYING;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  718 */     if (paramInt == 0) {
/*  719 */       fillInAllChecksums();
/*      */     }
/*  721 */     beginReplay(localOracleConnection, paramSQLRecoverableException);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  726 */     this.replayResult = replayAllBeforeLastCall(paramSQLRecoverableException);
/*      */     
/*      */ 
/*      */ 
/*  730 */     endReplay(localOracleConnection, paramSQLRecoverableException);
/*      */     
/*  732 */     MNGR_REPLAY_LOGGER.log(Level.FINEST, "On connection {0}, replaying last call", this.connectionProxy);
/*      */     
/*      */ 
/*      */ 
/*  736 */     if (this.tail != null) {
/*  737 */       this.replayResult = ((Replayable)this.tail.jdbcProxy).replayOneCall(this.tail, paramSQLRecoverableException);
/*      */     }
/*      */     
/*  740 */     this.lifecycle = ReplayLifecycle.ENABLED_NOT_REPLAYING;
/*      */     
/*  742 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "On connection {0}, replay succeeds", this.connectionProxy);
/*      */     
/*      */ 
/*  745 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "Exiting replayAll(connection proxy={0}, original error={1}), result={2}", new Object[] { this.connectionProxy, paramSQLRecoverableException, this.replayResult });
/*      */     
/*      */ 
/*      */ 
/*  749 */     return this.replayResult;
/*      */   }
/*      */   
/*      */ 
/*      */   private synchronized Object replayAllBeforeLastCall(SQLRecoverableException paramSQLRecoverableException)
/*      */     throws SQLException
/*      */   {
/*  756 */     Object localObject = null;
/*      */     
/*  758 */     for (CallHistoryEntry localCallHistoryEntry = this.head; 
/*  759 */         localCallHistoryEntry != this.tail; 
/*  760 */         localCallHistoryEntry = localCallHistoryEntry.nextEntry)
/*      */     {
/*  762 */       Replayable localReplayable = (Replayable)localCallHistoryEntry.jdbcProxy;
/*  763 */       MNGR_REPLAY_LOGGER.log(Level.FINEST, "On proxy {0}, replaying {1}", new Object[] { localReplayable, localCallHistoryEntry.method });
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  768 */       localObject = localReplayable.replayOneCall(localCallHistoryEntry, paramSQLRecoverableException);
/*      */       
/*      */ 
/*  771 */       if ((this.lifecycle != ReplayLifecycle.ENABLED_NOT_REPLAYING) && (this.lifecycle != ReplayLifecycle.REPLAYING) && (this.lifecycle != ReplayLifecycle.REPLAYING_LASTCALL) && (this.lifecycle != ReplayLifecycle.REPLAYING_CALLBACK))
/*      */       {
/*      */ 
/*      */ 
/*  775 */         throwReplayExceptionInternal(this.replayErrorCode, this.replayErrorMessage, paramSQLRecoverableException);
/*      */       }
/*      */     }
/*      */     
/*  779 */     return localObject;
/*      */   }
/*      */   
/*      */   boolean isReplayInCurrentMode()
/*      */   {
/*  784 */     return this.replayInCurrentMode;
/*      */   }
/*      */   
/*      */   void setReplayInCurrentMode()
/*      */   {
/*  789 */     this.replayInCurrentMode = true;
/*      */   }
/*      */   
/*      */   ReplayLifecycle getReplayLifecycle()
/*      */   {
/*  794 */     return this.lifecycle;
/*      */   }
/*      */   
/*      */   void setDataSource(OracleDataSource paramOracleDataSource)
/*      */   {
/*  799 */     this.replayDataSource = paramOracleDataSource;
/*      */   }
/*      */   
/*      */   void setReplayInitiationTimeout(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  805 */     this.replayInitiationTimeout = paramInt;
/*      */   }
/*      */   
/*      */   void beginRequest()
/*      */     throws SQLException
/*      */   {
/*  811 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "On connection {0}, Entering beginRequest()", this.connectionProxy);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  816 */     if (this.lifecycle == ReplayLifecycle.ALWAYS_DISABLED)
/*      */     {
/*  818 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "Exiting beginRequest(), MONITOR_TXN failed, no-op");
/*      */       
/*  820 */       return;
/*      */     }
/*      */     
/*  823 */     if (this.lifecycle != ReplayLifecycle.INTERNALLY_DISABLED) {
/*  824 */       throw DatabaseError.createSqlException(390);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  829 */     this.requestStartTime = System.currentTimeMillis();
/*  830 */     MNGR_REPLAY_LOGGER.log(Level.FINEST, "Request start timestamp: {0}", Long.valueOf(this.requestStartTime));
/*      */     
/*      */ 
/*      */ 
/*  834 */     OracleConnection localOracleConnection = (OracleConnection)this.connectionProxy.getDelegate();
/*      */     
/*      */ 
/*      */ 
/*  838 */     EnumSet localEnumSet = localOracleConnection.getTransactionState();
/*      */     
/*  840 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "transaction state: {0}", localEnumSet);
/*      */     
/*      */ 
/*  843 */     if ((localEnumSet.contains(OracleConnection.TransactionState.TRANSACTION_STARTED)) && (!localEnumSet.contains(OracleConnection.TransactionState.TRANSACTION_READONLY)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  849 */       SQLException localSQLException = DatabaseError.createSqlException(391);
/*      */       
/*      */ 
/*  852 */       MNGR_REPLAY_LOGGER.throwing(getClass().getName(), "beginRequest", localSQLException);
/*      */       
/*  854 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  858 */     this.replayErrorCode = 0;
/*  859 */     this.replayErrorMessage = "";
/*  860 */     this.callCausingReplayError = null;
/*      */     
/*  862 */     this.lifecycle = ReplayLifecycle.ENABLED_NOT_REPLAYING;
/*      */     
/*  864 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "Recording begins on connection {0}", this.connectionProxy);
/*      */     
/*      */ 
/*  867 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "Exiting beginRequest()");
/*      */   }
/*      */   
/*      */   void endRequest()
/*      */     throws SQLException
/*      */   {
/*  873 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "Entering endRequest()");
/*      */     
/*      */ 
/*  876 */     OracleConnection localOracleConnection = (OracleConnection)this.connectionProxy.getDelegate();
/*      */     
/*      */ 
/*      */ 
/*  880 */     EnumSet localEnumSet = localOracleConnection.getTransactionState();
/*      */     
/*  882 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "transaction state: {0}", localEnumSet);
/*      */     
/*      */ 
/*  885 */     if ((localEnumSet.contains(OracleConnection.TransactionState.TRANSACTION_STARTED)) && (!localEnumSet.contains(OracleConnection.TransactionState.TRANSACTION_READONLY)))
/*      */     {
/*      */ 
/*      */ 
/*      */       try
/*      */       {
/*      */ 
/*  892 */         localOracleConnection.rollback();
/*      */       }
/*      */       catch (SQLException localSQLException1)
/*      */       {
/*  896 */         MNGR_REPLAY_LOGGER.log(Level.FINEST, "Rollback open transaction failed before throwing exception");
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  901 */       SQLException localSQLException2 = DatabaseError.createSqlException(392);
/*      */       
/*      */ 
/*  904 */       MNGR_REPLAY_LOGGER.throwing(getClass().getName(), "endRequest", localSQLException2);
/*      */       
/*  906 */       throw localSQLException2;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  911 */     if (this.lifecycle == ReplayLifecycle.ALWAYS_DISABLED)
/*      */     {
/*  913 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "Exiting endRequest() -- MONITOR_TXN failed");
/*      */       
/*      */ 
/*  916 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  921 */     if ((this.lifecycle == ReplayLifecycle.INTERNALLY_DISABLED) || (this.lifecycle == ReplayLifecycle.EXTERNALLY_DISABLED))
/*      */     {
/*      */ 
/*  924 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "Exiting endRequest() -- replay already disabled");
/*      */       
/*      */ 
/*  927 */       this.lifecycle = ReplayLifecycle.INTERNALLY_DISABLED;
/*      */       
/*  929 */       return;
/*      */     }
/*      */     
/*      */ 
/*  933 */     disableReplayInternal(null, 381, "Replay disabled after endRequest is called", null);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  938 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "Exiting endRequest()");
/*      */   }
/*      */   
/*      */   void disableReplay()
/*      */     throws SQLException
/*      */   {
/*  944 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "Entering disableReplay");
/*      */     
/*      */ 
/*      */ 
/*  948 */     if (this.lifecycle == ReplayLifecycle.ALWAYS_DISABLED)
/*      */     {
/*  950 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "Exiting disableReplay(), MONITOR_TXN failed, no-op");
/*      */       
/*  952 */       return;
/*      */     }
/*      */     
/*  955 */     disableReplayInternal(null, 370, "Replay disabled", null);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  960 */     this.lifecycle = ReplayLifecycle.EXTERNALLY_DISABLED;
/*  961 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "On connection {0}, replay is externally disabled", this.connectionProxy);
/*      */     
/*      */ 
/*      */ 
/*  965 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "Exiting disableReplay");
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
/*      */   void disableReplayInternal(Method paramMethod, int paramInt, String paramString, SQLRecoverableException paramSQLRecoverableException)
/*      */   {
/*  978 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "Entering disableReplayInternal");
/*      */     
/*      */ 
/*  981 */     ReplayLifecycle localReplayLifecycle = this.lifecycle;
/*      */     
/*      */ 
/*      */ 
/*  985 */     if (this.lifecycle != ReplayLifecycle.ALWAYS_DISABLED) {
/*  986 */       this.lifecycle = ReplayLifecycle.INTERNALLY_DISABLED;
/*      */     }
/*      */     
/*  989 */     purge();
/*      */     
/*      */ 
/*  992 */     this.replayErrorCode = paramInt;
/*  993 */     this.replayErrorMessage = paramString;
/*      */     
/*  995 */     this.callCausingReplayError = paramMethod;
/*      */     
/*  997 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "On connection {0}, replay is internally disabled", this.connectionProxy);
/*      */     
/*      */ 
/*      */ 
/* 1001 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "Exiting disableReplayInternal");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void failReplayInternal(Method paramMethod, int paramInt, String paramString, SQLRecoverableException paramSQLRecoverableException)
/*      */   {
/* 1010 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "Entering failReplayInternal");
/*      */     
/*      */ 
/* 1013 */     ReplayLifecycle localReplayLifecycle = this.lifecycle;
/*      */     
/*      */ 
/*      */ 
/* 1017 */     if ((this.lifecycle == ReplayLifecycle.REPLAYING) || (this.lifecycle == ReplayLifecycle.REPLAYING_CALLBACK) || (this.lifecycle == ReplayLifecycle.REPLAYING_LASTCALL))
/*      */     {
/*      */ 
/* 1020 */       this.lifecycle = ReplayLifecycle.INTERNALLY_FAILED;
/*      */     }
/*      */     
/* 1023 */     this.replayErrorCode = paramInt;
/* 1024 */     this.replayErrorMessage = paramString;
/*      */     
/* 1026 */     this.callCausingReplayError = paramMethod;
/*      */     
/* 1028 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "On connection {0}, replay failed", this.connectionProxy);
/*      */     
/*      */ 
/*      */ 
/* 1032 */     MNGR_REPLAY_LOGGER.log(Level.FINER, "Exiting failReplayInternal");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void throwReplayExceptionInternal(int paramInt, String paramString, SQLRecoverableException paramSQLRecoverableException)
/*      */     throws SQLException
/*      */   {
/* 1041 */     if (paramInt == 0) {
/* 1042 */       return;
/*      */     }
/* 1044 */     String str = this.callCausingReplayError == null ? "" : this.callCausingReplayError.getName();
/*      */     
/*      */ 
/*      */ 
/* 1048 */     SQLException localSQLException = DatabaseError.createSqlException(this.replayErrorCode, str);
/*      */     
/*      */ 
/* 1051 */     throw localSQLException;
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
/*      */   void disableReplayAndThrowException(Method paramMethod, int paramInt, String paramString, SQLRecoverableException paramSQLRecoverableException)
/*      */     throws SQLException
/*      */   {
/* 1071 */     disableReplayInternal(paramMethod, paramInt, paramString, paramSQLRecoverableException);
/*      */     
/* 1073 */     throwReplayExceptionInternal(paramInt, paramString, paramSQLRecoverableException);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void disableReplayAndThrowOriginalError(Method paramMethod, int paramInt, String paramString, SQLRecoverableException paramSQLRecoverableException)
/*      */     throws SQLException
/*      */   {
/* 1083 */     disableReplayInternal(paramMethod, paramInt, paramString, paramSQLRecoverableException);
/*      */     
/* 1085 */     throwOriginalExceptionWithReplayError(paramInt, paramString, paramSQLRecoverableException);
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
/*      */   void failReplayAndThrowException(Method paramMethod, int paramInt, String paramString, SQLRecoverableException paramSQLRecoverableException)
/*      */     throws SQLException
/*      */   {
/* 1103 */     failReplayInternal(paramMethod, paramInt, paramString, paramSQLRecoverableException);
/*      */     
/* 1105 */     throwReplayExceptionInternal(paramInt, paramString, paramSQLRecoverableException);
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
/*      */   void throwOriginalExceptionWithReplayError(int paramInt, String paramString, SQLRecoverableException paramSQLRecoverableException)
/*      */     throws SQLRecoverableException
/*      */   {
/* 1140 */     if (!this.doNotAbortConn)
/*      */     {
/*      */ 
/* 1143 */       killConnectionBeforeReplayDisabledException();
/*      */     }
/* 1145 */     String str = this.callCausingReplayError == null ? "" : this.callCausingReplayError.getName();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1150 */     SQLException localSQLException = DatabaseError.createSqlException(this.replayErrorCode, str);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1155 */     paramSQLRecoverableException.setNextException(localSQLException);
/*      */     
/*      */ 
/* 1158 */     throw paramSQLRecoverableException;
/*      */   }
/*      */   
/*      */   void killConnectionBeforeReplayDisabledException()
/*      */   {
/* 1163 */     final OracleConnection localOracleConnection = (OracleConnection)this.connectionProxy.getDelegate();
/*      */     
/*      */ 
/*      */     try
/*      */     {
/* 1168 */       localOracleConnection.abort();
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/* 1172 */       MNGR_REPLAY_LOGGER.log(Level.FINE, "Aborting connection failed before throwing replay-disabled exception");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/* 1180 */       executor.submit(new Runnable()
/*      */       {
/*      */ 
/*      */         public void run()
/*      */         {
/* 1185 */           FailoverManagerImpl.this.closePhysicalConnection(localOracleConnection);
/*      */         }
/*      */       });
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/* 1191 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "On connection {0}, ASYNC close() submission during replay failed", this.connectionProxy);
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
/*      */   void enableTxnMonitoring(OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 1208 */       Statement localStatement = paramOracleConnection.createStatement();
/*      */       
/* 1210 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "Calling MONITOR_TXN");
/* 1211 */       localStatement.execute("BEGIN DBMS_APP_CONT_PRVT.MONITOR_TXN; END;");
/* 1212 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "Calling MONITOR_TXN succeeded");
/*      */       
/* 1214 */       localStatement.close();
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/* 1218 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "Calling MONITOR_TXN failed");
/* 1219 */       disableReplayInternal(null, 374, "Replay disabled because transaction monitoring failed to be enabled", null);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1226 */       this.lifecycle = ReplayLifecycle.ALWAYS_DISABLED;
/*      */       
/*      */ 
/* 1229 */       throw DatabaseError.createSqlException(394);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void beginReplay(OracleConnection paramOracleConnection, SQLRecoverableException paramSQLRecoverableException)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 1242 */       Statement localStatement = paramOracleConnection.createStatement();
/*      */       
/* 1244 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "Calling BEGIN_REPLAY");
/* 1245 */       localStatement.execute("BEGIN DBMS_APP_CONT_PRVT.BEGIN_REPLAY; END;");
/* 1246 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "Calling BEGIN_REPLAY succeeded");
/*      */       
/* 1248 */       localStatement.close();
/*      */       
/* 1250 */       this.lifecycle = ReplayLifecycle.REPLAYING;
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/* 1254 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "Calling BEGIN_REPLAY failed");
/* 1255 */       disableReplayAndThrowException(null, 375, "Replay disabled because server begin_replay call failed", paramSQLRecoverableException);
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
/*      */   void endReplay(OracleConnection paramOracleConnection, SQLRecoverableException paramSQLRecoverableException)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 1271 */       Statement localStatement = paramOracleConnection.createStatement();
/*      */       
/* 1273 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "Calling END_REPLAY");
/* 1274 */       localStatement.execute("BEGIN DBMS_APP_CONT_PRVT.END_REPLAY; END;");
/* 1275 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "Calling END_REPLAY succeeded");
/*      */       
/* 1277 */       localStatement.close();
/*      */       
/* 1279 */       this.lifecycle = ReplayLifecycle.REPLAYING_LASTCALL;
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/* 1283 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "Calling END_REPLAY failed");
/* 1284 */       disableReplayAndThrowException(null, 376, "Replay disabled because server end_replay call failed", paramSQLRecoverableException);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   Replayable getConnectionProxy()
/*      */   {
/* 1294 */     return this.connectionProxy;
/*      */   }
/*      */   
/*      */   private boolean isReplayFailure(SQLException paramSQLException)
/*      */   {
/* 1299 */     boolean bool = false;
/*      */     
/* 1301 */     if ((paramSQLException instanceof SQLException))
/*      */     {
/* 1303 */       int i = paramSQLException.getErrorCode();
/* 1304 */       if ((i >= 370) && (i < 400))
/*      */       {
/* 1306 */         bool = true;
/*      */       }
/*      */     }
/* 1309 */     return bool;
/*      */   }
/*      */   
/*      */   private void closePhysicalConnection(Connection paramConnection)
/*      */   {
/*      */     try
/*      */     {
/* 1316 */       paramConnection.close();
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/* 1320 */       MNGR_REPLAY_LOGGER.log(Level.FINER, "On connection {0}, connection close() during replay failed", this.connectionProxy);
/*      */     }
/*      */   }
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/driver/FailoverManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */