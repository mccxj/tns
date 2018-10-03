package oracle.jdbc.replay.driver;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.internal.OracleStatement;
import oracle.jdbc.proxy.annotation.GetCreator;
import oracle.jdbc.proxy.annotation.GetDelegate;
import oracle.jdbc.proxy.annotation.Methods;
import oracle.jdbc.proxy.annotation.OnError;
import oracle.jdbc.proxy.annotation.Post;
import oracle.jdbc.proxy.annotation.Pre;
import oracle.jdbc.proxy.annotation.ProxyFor;
import oracle.jdbc.proxy.annotation.ProxyResult;
import oracle.jdbc.proxy.annotation.ProxyResultPolicy;
import oracle.jdbc.proxy.annotation.SetDelegate;
@ProxyFor({ResultSet.class, oracle.jdbc.OracleResultSet.class, oracle.jdbc.internal.OracleResultSet.class})
@ProxyResult(ProxyResultPolicy.CREATE)
public abstract class NonTxnReplayableResultSet
  extends NonTxnReplayableBase
  implements Replayable
{
  private static final String RSET_FEATURE_LOGGER_NAME = "oracle.jdbc.internal.replay.NonTxnReplayableResultSet";
/*  62 */   private static Logger RSET_REPLAY_LOGGER = null;
  
  static
  {
/*  66 */     if (RSET_REPLAY_LOGGER == null) {
/*  67 */       RSET_REPLAY_LOGGER = ReplayLoggerFactory.getLogger("oracle.jdbc.internal.replay.NonTxnReplayableResultSet");
    }
  }
  
  @Pre
  protected void preForAll(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
/*  79 */     super.preForAll(paramMethod, paramObject, paramVarArgs);
  }
  
  @Pre
  @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="deleteRow", args={}), @oracle.jdbc.proxy.annotation.Signature(name="insertRow", args={}), @oracle.jdbc.proxy.annotation.Signature(name="updateRow", args={})})
  protected void preForRowUpdates(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
/*  91 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
    
/*  93 */     if (localReplayLifecycle != FailoverManagerImpl.ReplayLifecycle.ENABLED_NOT_REPLAYING) {
/*  94 */       return;
    }
/*  96 */     RSET_REPLAY_LOGGER.log(Level.FINER, "On result set {0}, entering preForRowUpdates({1})", new Object[] { this, paramMethod.getName() });
    
/* 100 */     if (this.failoverMngr != null) {
/* 101 */       this.failoverMngr.disableReplayInternal(paramMethod, 371, "Replay disabled because of active transaction", null);
    }
    else
    {
/* 107 */       RSET_REPLAY_LOGGER.log(Level.SEVERE, "On result set {0}, failover manager not set", this);
    }
    
/* 110 */     RSET_REPLAY_LOGGER.log(Level.FINER, "On result set {0}, exiting preForRowUpdates()", this);
  }
  
  @Pre
  @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="updateAsciiStream", args={String.class, java.io.InputStream.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateAsciiStream", args={String.class, java.io.InputStream.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateAsciiStream", args={String.class, java.io.InputStream.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateBinaryStream", args={String.class, java.io.InputStream.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateBinaryStream", args={String.class, java.io.InputStream.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateBinaryStream", args={String.class, java.io.InputStream.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateCharacterStream", args={String.class, java.io.Reader.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateCharacterStream", args={String.class, java.io.Reader.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateCharacterStream", args={String.class, java.io.Reader.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateNCharacterStream", args={String.class, java.io.Reader.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateNCharacterStream", args={String.class, java.io.Reader.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateAsciiStream", args={int.class, java.io.InputStream.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateAsciiStream", args={int.class, java.io.InputStream.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateAsciiStream", args={int.class, java.io.InputStream.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateBinaryStream", args={int.class, java.io.InputStream.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateBinaryStream", args={int.class, java.io.InputStream.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateBinaryStream", args={int.class, java.io.InputStream.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateCharacterStream", args={int.class, java.io.Reader.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateCharacterStream", args={int.class, java.io.Reader.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateCharacterStream", args={int.class, java.io.Reader.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateNCharacterStream", args={int.class, java.io.Reader.class}), @oracle.jdbc.proxy.annotation.Signature(name="updateNCharacterStream", args={int.class, java.io.Reader.class, long.class})})
  protected void preForUpdateStreams(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
/* 166 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
    
/* 168 */     if (localReplayLifecycle != FailoverManagerImpl.ReplayLifecycle.ENABLED_NOT_REPLAYING) {
/* 169 */       return;
    }
/* 171 */     RSET_REPLAY_LOGGER.log(Level.FINER, "On result set {0}, entering preForRowUpdates({1})", new Object[] { this, paramMethod.getName() });
    
/* 175 */     if (this.failoverMngr != null) {
/* 176 */       this.failoverMngr.disableReplayInternal(paramMethod, 371, "Replay disabled because of active transaction", null);
    }
    else
    {
/* 182 */       RSET_REPLAY_LOGGER.log(Level.SEVERE, "On result set {0}, failover manager not set", this);
    }
    
/* 185 */     RSET_REPLAY_LOGGER.log(Level.FINER, "On result set {0}, exiting preForRowUpdates()", this);
  }
  
  @Post
  protected void postForAll(Method paramMethod)
  {
/* 192 */     postForAll(paramMethod, null);
  }
  
  @Post
  protected Object postForAll(Method paramMethod, Object paramObject)
  {
/* 198 */     return super.postForAll(paramMethod, paramObject);
  }
  
  @Post
  @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="next", args={})})
  protected boolean postForNext(Method paramMethod, boolean paramBoolean)
  {
/* 206 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
    
/* 209 */     switch (localReplayLifecycle)
    {
    case ENABLED_NOT_REPLAYING: 
    case REPLAYING_LASTCALL: 
/* 215 */       doPostWhenRecordingNext(paramMethod, Boolean.valueOf(paramBoolean), null);
/* 216 */       break;
    
    case INTERNALLY_FAILED: 
    case ALWAYS_DISABLED: 
    case INTERNALLY_DISABLED: 
    case EXTERNALLY_DISABLED: 
    case REPLAYING_CALLBACK: 
      break;
    
    case REPLAYING: 
/* 228 */       doPostWhenReplaying(paramMethod, Boolean.valueOf(paramBoolean), null);
    }
    
/* 231 */     return paramBoolean;
  }
  
  @Post
  @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="close", args={})})
  protected void postForClose(Method paramMethod)
  {
/* 239 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
    
/* 242 */     switch (localReplayLifecycle)
    {
    case ENABLED_NOT_REPLAYING: 
/* 246 */       doPostWhenRecordingClose(paramMethod, null);
/* 247 */       break;
    case INTERNALLY_FAILED: 
    case ALWAYS_DISABLED: 
    case INTERNALLY_DISABLED: 
    case EXTERNALLY_DISABLED: 
    case REPLAYING_CALLBACK: 
      break;
    }
    
  }
  
  @OnError(SQLException.class)
  protected void onErrorVoidForAll(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/* 267 */     onErrorForAll(paramMethod, paramSQLException);
  }
  
  @OnError(SQLException.class)
  protected Object onErrorForAll(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/* 274 */     return super.onErrorForAll(paramMethod, paramSQLException);
  }
  
  @OnError(SQLException.class)
  @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="last", args={})})
  protected boolean onErrorForLast(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/* 284 */     if (this.isClosedAndNoReplay) {
/* 285 */       throw paramSQLException;
    }
/* 287 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
    
/* 289 */     if (((paramSQLException instanceof SQLRecoverableException)) && (localReplayLifecycle == FailoverManagerImpl.ReplayLifecycle.ENABLED_NOT_REPLAYING))
    {
/* 292 */       RSET_REPLAY_LOGGER.log(Level.FINER, "On proxy {0}, failed call for initial outage is last()", this);
      
/* 295 */       this.failoverMngr.disableReplayInternal(paramMethod, 372, "Replay disabled because of nonreplayable call", null);
    }
    
/* 302 */     return ((Boolean)super.onErrorForAll(paramMethod, paramSQLException)).booleanValue();
  }
  
  public void fillInChecksum(FailoverManagerImpl.CallHistoryEntry paramCallHistoryEntry)
    throws SQLException
  {
/* 324 */     OracleStatement localOracleStatement = (OracleStatement)((NonTxnReplayableBase)getCreator()).getDelegate();
    
/* 328 */     long l = localOracleStatement.getChecksum();
    
/* 330 */     RSET_REPLAY_LOGGER.log(Level.FINEST, "On proxy {0}, method {1}, filling in checksum: {2}", new Object[] { paramCallHistoryEntry.jdbcProxy, paramCallHistoryEntry.method, Long.valueOf(l) });
    
/* 335 */     this.failoverMngr.update(this, paramCallHistoryEntry, paramCallHistoryEntry.result, paramCallHistoryEntry.callStatus, l, paramCallHistoryEntry.scn, paramCallHistoryEntry.callException);
  }
  
  public Object replayOneCall(FailoverManagerImpl.CallHistoryEntry paramCallHistoryEntry, SQLRecoverableException paramSQLRecoverableException)
    throws SQLException
  {
/* 344 */     Object localObject = super.replayOneCall(paramCallHistoryEntry, paramSQLRecoverableException);
    
/* 347 */     return localObject;
  }
  
  protected void doPostWhenRecordingNext(Method paramMethod, Object paramObject, SQLException paramSQLException)
  {
/* 355 */     long l = 0L;
    
/* 357 */     OracleStatement localOracleStatement = (OracleStatement)((NonTxnReplayableBase)getCreator()).getDelegate();
    
    try
    {
/* 363 */       l = localOracleStatement.getChecksum();
    }
    catch (SQLException localSQLException)
    {
/* 367 */       l = 0L;
/* 368 */       RSET_REPLAY_LOGGER.log(Level.WARNING, "On result set {0}, getChecksum() gets exception: {1}", new Object[] { this, localSQLException });
    }
    
/* 374 */     this.failoverMngr.update(this, null, paramObject, "completed", l, -1L, paramSQLException);
  }
  
  protected void doPostWhenRecordingClose(Method paramMethod, SQLException paramSQLException)
  {
/* 381 */     NonTxnReplayableStatement localNonTxnReplayableStatement = (NonTxnReplayableStatement)getCreator();
    
/* 385 */     if (localNonTxnReplayableStatement.okToPurgeSameProxyList()) {
/* 386 */       purgeSameProxyList();
    }
    
/* 389 */     this.isClosedAndNoReplay = true;
  }
  
  protected void doPostWhenReplaying(Method paramMethod, Object paramObject, SQLException paramSQLException)
  {
    try
    {
/* 400 */       FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
      
/* 402 */       switch (localReplayLifecycle)
      {
      case REPLAYING: 
/* 412 */         if (this.replayingCallEntry.checksum != 0L)
        {
/* 414 */           OracleStatement localOracleStatement = (OracleStatement)((NonTxnReplayableBase)getCreator()).getDelegate();
          
/* 420 */           long l = localOracleStatement.getChecksum();
          
/* 422 */           RSET_REPLAY_LOGGER.log(Level.FINER, "On proxy {0}, replaying method {1}, new checksum: {2}, original checksum: {3}", new Object[] { this.replayingCallEntry.jdbcProxy, this.replayingCallEntry.method, Long.valueOf(l), Long.valueOf(this.replayingCallEntry.checksum) });
          
/* 430 */           if (this.replayingCallEntry.checksum != l)
          {
/* 432 */             this.failoverMngr.disableReplayAndThrowException(this.replayingCallEntry.method, 386, "Replay failed because of checksum mismatch", this.originalError);
          }
        }
        
        break;
      }
      
    }
    catch (SQLException localSQLException)
    {
/* 447 */       RSET_REPLAY_LOGGER.log(Level.WARNING, "On result set {0}, doPostWhenReplaying exception: {1}", new Object[] { this, localSQLException });
    }
  }
  
  @ProxyResult(ProxyResultPolicy.MANUAL)
  public Statement getStatement()
  {
/* 462 */     return (Statement)getCreator();
  }
  
  @ProxyResult(ProxyResultPolicy.MANUAL)
  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
/* 469 */     return (T)getDelegate();
  }
  
  @GetDelegate
  protected abstract Object getDelegate();
  
  @SetDelegate
  protected abstract void setDelegate(Object paramObject);
  
  @GetCreator
  protected abstract Object getCreator();
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/driver/NonTxnReplayableResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */