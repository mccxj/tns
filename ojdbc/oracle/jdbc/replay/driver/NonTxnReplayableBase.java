package oracle.jdbc.replay.driver;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLRecoverableException;
import java.sql.SQLXML;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.AdditionalDatabaseMetaData;
import oracle.jdbc.OracleParameterMetaData;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.OracleTypeMetaData.Array;
import oracle.jdbc.OracleTypeMetaData.Opaque;
import oracle.jdbc.OracleTypeMetaData.Struct;
import oracle.jdbc.internal.OracleDatumWithConnection;
import oracle.jdbc.proxy.annotation.GetCreator;
import oracle.jdbc.proxy.annotation.GetDelegate;
import oracle.jdbc.proxy.annotation.OnError;
import oracle.jdbc.proxy.annotation.Post;
import oracle.jdbc.proxy.annotation.Pre;
import oracle.jdbc.proxy.annotation.ProxyFor;
import oracle.jdbc.proxy.annotation.SetDelegate;
import oracle.jdbc.replay.ReplayableConnection;
@ProxyFor({DatabaseMetaData.class, ParameterMetaData.class, ResultSetMetaData.class, RowId.class, SQLData.class, SQLInput.class, SQLXML.class, AdditionalDatabaseMetaData.class, OracleParameterMetaData.class, oracle.jdbc.OracleResultSetCache.class, oracle.jdbc.OracleResultSetMetaData.class, OracleTypeMetaData.class, OracleTypeMetaData.Array.class, OracleTypeMetaData.Opaque.class, OracleTypeMetaData.Struct.class, OracleDatumWithConnection.class, oracle.jdbc.internal.OracleResultSetCache.class, oracle.jdbc.internal.OracleResultSetMetaData.class})
public abstract class NonTxnReplayableBase
  implements Replayable, InvocationHandler
{
  private static final String BASE_FEATURE_LOGGER_NAME = "oracle.jdbc.internal.replay.NonTxnReplayableBase";
/*  70 */   private static Logger BASE_REPLAY_LOGGER = null;
  
  protected FailoverManagerImpl failoverMngr;
  
/*  77 */   protected FailoverManagerImpl.CallHistoryEntry headSameProxy = null;
/*  78 */   protected FailoverManagerImpl.CallHistoryEntry tailSameProxy = null;
  
/*  81 */   protected FailoverManagerImpl.CallHistoryEntry replayingCallEntry = null;
  
/*  84 */   protected SQLRecoverableException originalError = null;
  
  protected static final int SVR_TXN_IN_REPLAY_ERROR_CODE1 = 603;
  
  protected static final int SVR_TXN_IN_REPLAY_ERROR_CODE2 = 29791;
  
/*  96 */   protected boolean isClosedAndNoReplay = false;
  
  static
  {
/* 100 */     if (BASE_REPLAY_LOGGER == null) {
/* 101 */       BASE_REPLAY_LOGGER = ReplayLoggerFactory.getLogger("oracle.jdbc.internal.replay.NonTxnReplayableBase");
    }
  }
  
  @Pre
  protected void preForAll(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
/* 113 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
    
/* 116 */     switch (localReplayLifecycle)
    {
    case ENABLED_NOT_REPLAYING: 
/* 119 */       this.failoverMngr.record(this, paramMethod, paramVarArgs, "started");
      
/* 121 */       break;
    }
    
  }
  
  @Post
  protected void postForAll(Method paramMethod)
  {
/* 137 */     postForAll(paramMethod, null);
  }
  
  @Post
  protected Object postForAll(Method paramMethod, Object paramObject)
  {
/* 144 */     if ((paramObject instanceof NonTxnReplayableBase))
    {
/* 146 */       localObject = (NonTxnReplayableBase)paramObject;
/* 147 */       ((NonTxnReplayableBase)localObject).setFailoverManager(getFailoverManager());
    }
    
/* 150 */     Object localObject = this.failoverMngr.getReplayLifecycle();
    
/* 153 */     switch (localObject)
    {
    case ENABLED_NOT_REPLAYING: 
    case REPLAYING_LASTCALL: 
/* 159 */       doPostWhenRecording(paramMethod, paramObject, null);
/* 160 */       break;
    
    case INTERNALLY_FAILED: 
    case ALWAYS_DISABLED: 
    case INTERNALLY_DISABLED: 
    case EXTERNALLY_DISABLED: 
    case REPLAYING_CALLBACK: 
      break;
    
    case REPLAYING: 
/* 173 */       doPostWhenReplaying(paramMethod, paramObject, null);
    }
    
/* 176 */     return paramObject;
  }
  
  protected void doPostWhenRecording(Method paramMethod, Object paramObject, SQLException paramSQLException)
  {
/* 188 */     this.failoverMngr.update(this, null, paramObject, "completed", 0L, -1L, paramSQLException);
  }
  
  @OnError(SQLException.class)
  protected void onErrorVoidForAll(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/* 206 */     onErrorForAll(paramMethod, paramSQLException);
  }
  
  @OnError(SQLException.class)
  protected Object onErrorForAll(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/* 214 */     if (this.isClosedAndNoReplay) {
/* 215 */       throw paramSQLException;
    }
/* 217 */     if ((paramSQLException instanceof SQLRecoverableException))
    {
/* 219 */       localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
      
/* 221 */       switch (localReplayLifecycle)
      {
      case ENABLED_NOT_REPLAYING: 
      case ALWAYS_DISABLED: 
      case INTERNALLY_DISABLED: 
      case EXTERNALLY_DISABLED: 
/* 229 */         return this.failoverMngr.replayAll((SQLRecoverableException)paramSQLException);
      }
      
      
/* 239 */       throw paramSQLException;
    }
    
/* 246 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
    
/* 248 */     switch (localReplayLifecycle)
    {
    case ENABLED_NOT_REPLAYING: 
    case REPLAYING_LASTCALL: 
/* 253 */       doPostWhenRecording(paramMethod, null, paramSQLException);
      
/* 255 */       break;
    }
    
    
/* 267 */     throw paramSQLException;
  }
  
  public Object replayOneCall(FailoverManagerImpl.CallHistoryEntry paramCallHistoryEntry, SQLRecoverableException paramSQLRecoverableException)
    throws SQLException
  {
/* 327 */     Object localObject1 = null;
    
    try
    {
/* 332 */       ((Replayable)paramCallHistoryEntry.jdbcProxy).setReplayingCallContext(paramCallHistoryEntry, paramSQLRecoverableException);
      
/* 335 */       Object localObject2 = paramCallHistoryEntry.method.invoke(paramCallHistoryEntry.jdbcProxy, paramCallHistoryEntry.args);
/* 336 */       localObject1 = localObject2;
      
/* 342 */       if ((localObject2 instanceof NonTxnReplayableBase))
      {
/* 344 */         localObject3 = this.failoverMngr.getReplayLifecycle();
        
/* 346 */         if ((localObject3 == FailoverManagerImpl.ReplayLifecycle.REPLAYING) && (localObject2 != null) && (paramCallHistoryEntry.result != null))
        {
/* 350 */           localObject4 = ((NonTxnReplayableBase)localObject2).getDelegate();
/* 351 */           ((NonTxnReplayableBase)paramCallHistoryEntry.result).setDelegate(localObject4);
/* 352 */           localObject1 = paramCallHistoryEntry.result;
        }
      }
    }
    catch (InvocationTargetException localInvocationTargetException) {
      Object localObject4;
/* 358 */       Object localObject3 = localInvocationTargetException.getCause();
      
/* 360 */       BASE_REPLAY_LOGGER.log(Level.FINEST, "On {0}, replaying {1} got InvocationTargetException with cause: {2}", new Object[] { paramCallHistoryEntry.jdbcProxy, paramCallHistoryEntry.method, localObject3 });
      
/* 365 */       if ((localObject3 instanceof SQLRecoverableException))
      {
/* 367 */         localObject4 = (SQLRecoverableException)localObject3;
        
/* 370 */         throw ((Throwable)localObject4);
      }
      
/* 374 */       if ((localObject3 instanceof SQLException))
      {
/* 376 */         localObject4 = (SQLException)localObject3;
        
/* 378 */         int i = ((SQLException)localObject4).getErrorCode();
        
/* 380 */         if (i == 29791)
        {
/* 382 */           BASE_REPLAY_LOGGER.log(Level.WARNING, "On {0}, replaying {1} receives ORA-29791", new Object[] { paramCallHistoryEntry.jdbcProxy, paramCallHistoryEntry.method });
          
/* 386 */           this.failoverMngr.disableReplayAndThrowException(paramCallHistoryEntry.method, 388, "Replay failed because of active transaction during replay", paramSQLRecoverableException);
        }
/* 394 */         else if ((paramCallHistoryEntry.callException == null) || (i != paramCallHistoryEntry.callException.getErrorCode()))
        {
/* 396 */           this.failoverMngr.disableReplayAndThrowException(paramCallHistoryEntry.method, 387, "Replay failed because of error code or message mismatch", paramSQLRecoverableException);
        }
        
      }
      else
      {
/* 407 */         this.failoverMngr.disableReplayAndThrowException(paramCallHistoryEntry.method, 370, "Replay disabled", paramSQLRecoverableException);
      }
      
    }
    catch (Throwable localThrowable)
    {
/* 416 */       BASE_REPLAY_LOGGER.log(Level.FINEST, "On {0}, replaying {1} caught throwable: {2}", new Object[] { paramCallHistoryEntry.jdbcProxy, paramCallHistoryEntry.method, localThrowable });
      
/* 420 */       localThrowable.printStackTrace();
      
/* 423 */       this.failoverMngr.disableReplayAndThrowException(paramCallHistoryEntry.method, 370, "Replay disabled", paramSQLRecoverableException);
    }
    
/* 430 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
    
/* 432 */     switch (localReplayLifecycle)
    {
    case INTERNALLY_FAILED: 
    case INTERNALLY_DISABLED: 
/* 438 */       this.failoverMngr.throwReplayExceptionInternal(0, null, null);
    }
    
/* 441 */     return localObject1;
  }
  
  public void addToSameProxyList(FailoverManagerImpl.CallHistoryEntry paramCallHistoryEntry)
  {
/* 446 */     paramCallHistoryEntry.prevEntrySameProxy = this.tailSameProxy;
/* 447 */     paramCallHistoryEntry.nextEntrySameProxy = null;
    
/* 449 */     if (this.tailSameProxy != null) {
/* 450 */       this.tailSameProxy.nextEntrySameProxy = paramCallHistoryEntry;
    }
/* 452 */     this.tailSameProxy = paramCallHistoryEntry;
    
/* 455 */     if (this.headSameProxy == null) {
/* 456 */       this.headSameProxy = paramCallHistoryEntry;
    }
  }
  
  public void removeFromSameProxyList(FailoverManagerImpl.CallHistoryEntry paramCallHistoryEntry) {
/* 461 */     if (paramCallHistoryEntry.nextEntrySameProxy != null) {
/* 462 */       paramCallHistoryEntry.nextEntrySameProxy.prevEntrySameProxy = paramCallHistoryEntry.prevEntrySameProxy;
    }
    
/* 465 */     if (paramCallHistoryEntry.prevEntrySameProxy != null) {
/* 466 */       paramCallHistoryEntry.prevEntrySameProxy.nextEntrySameProxy = paramCallHistoryEntry.nextEntrySameProxy;
    }
    
/* 469 */     if (this.headSameProxy == paramCallHistoryEntry) {
/* 470 */       this.headSameProxy = paramCallHistoryEntry.nextEntrySameProxy;
    }
/* 472 */     if (this.tailSameProxy == paramCallHistoryEntry) {
/* 473 */       this.tailSameProxy = paramCallHistoryEntry.prevEntrySameProxy;
    }
  }
  
  public void purgeSameProxyList() {
/* 478 */     if (this.failoverMngr != null)
    {
/* 480 */       HashSet localHashSet = new HashSet();
      
/* 483 */       for (Object localObject = this; 
          
/* 485 */           (localObject != null) && ((localObject instanceof NonTxnReplayableBase)) && (!(localObject instanceof ReplayableConnection)); 
          
/* 487 */           localObject = ((NonTxnReplayableBase)localObject).getCreator())
      {
/* 489 */         localHashSet.add(localObject);
      }
      
/* 493 */       this.failoverMngr.purgeForSameProxy(localHashSet, this.headSameProxy);
    }
  }
  
  public void setReplayingCallContext(FailoverManagerImpl.CallHistoryEntry paramCallHistoryEntry, SQLRecoverableException paramSQLRecoverableException)
  {
/* 500 */     this.replayingCallEntry = paramCallHistoryEntry;
/* 501 */     this.originalError = paramSQLRecoverableException;
  }
  
  public synchronized void setFailoverManager(FailoverManagerImpl paramFailoverManagerImpl)
  {
/* 506 */     this.failoverMngr = paramFailoverManagerImpl;
  }
  
  public synchronized FailoverManagerImpl getFailoverManager()
  {
/* 511 */     return this.failoverMngr;
  }
  
  private boolean isReplayFailure(Throwable paramThrowable)
  {
/* 521 */     boolean bool = false;
    
/* 523 */     if ((paramThrowable instanceof SQLException))
    {
/* 525 */       int i = ((SQLException)paramThrowable).getErrorCode();
/* 526 */       if ((i >= 370) && (i < 400))
      {
/* 528 */         bool = true;
      }
    }
/* 531 */     return bool;
  }
  
  protected boolean assertThrowablesMatch(Throwable paramThrowable1, Throwable paramThrowable2)
  {
/* 538 */     boolean bool1 = paramThrowable2 == null ? false : paramThrowable1.getClass().getName().equals(paramThrowable2.getClass().getName());
    
/* 544 */     BASE_REPLAY_LOGGER.log(Level.FINEST, "Errors at original execution and replay are of same type: {0}", Boolean.valueOf(bool1));
    
    boolean bool2;
    
/* 549 */     if (((paramThrowable1 instanceof SQLException)) && (bool1))
    {
/* 552 */       bool2 = ((SQLException)paramThrowable1).getErrorCode() == ((SQLException)paramThrowable2).getErrorCode();
      
/* 556 */       BASE_REPLAY_LOGGER.log(Level.FINEST, "Errors at original execution and replay are SQLException, error codes match: {0}", Boolean.valueOf(bool2));
    }
/* 560 */     else if (bool1)
    {
/* 563 */       String str1 = paramThrowable1.getMessage();
/* 564 */       String str2 = paramThrowable2.getMessage();
/* 565 */       bool2 = ((str1 == null) && (str2 == null)) || ((str1 != null) && (str2 != null) && (paramThrowable1.getMessage().equals(paramThrowable2.getMessage())));
      
/* 570 */       BASE_REPLAY_LOGGER.log(Level.FINEST, "Errors at original execution and replay are same type but not SQLException, error messages match: {0}", Boolean.valueOf(bool2));
    }
    else
    {
/* 576 */       bool2 = false;
    }
/* 578 */     BASE_REPLAY_LOGGER.log(Level.FINEST, "Errors at original execution and replay match: {0}", Boolean.valueOf(bool2));
    
/* 582 */     return bool2;
  }
  
  protected void doPostWhenReplaying(Method paramMethod, Object paramObject, SQLException paramSQLException) {}
  
  @GetDelegate
  protected abstract Object getDelegate();
  
  @SetDelegate
  protected abstract void setDelegate(Object paramObject);
  
  @GetCreator
  protected abstract Object getCreator();
  
  public void fillInChecksum(FailoverManagerImpl.CallHistoryEntry paramCallHistoryEntry)
    throws SQLException
  {}
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/driver/NonTxnReplayableBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */