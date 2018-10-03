package oracle.jdbc.replay.driver;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.proxy.annotation.GetCreator;
import oracle.jdbc.proxy.annotation.GetDelegate;
import oracle.jdbc.proxy.annotation.Methods;
import oracle.jdbc.proxy.annotation.OnError;
import oracle.jdbc.proxy.annotation.Post;
import oracle.jdbc.proxy.annotation.Pre;
import oracle.jdbc.proxy.annotation.ProxyFor;
import oracle.jdbc.proxy.annotation.SetDelegate;
@ProxyFor({Blob.class, oracle.jdbc.OracleBlob.class, oracle.jdbc.internal.OracleBlob.class})
public abstract class NonTxnReplayableBlob
  extends NonTxnReplayableBase
  implements Replayable
{
  private static final String BLOB_FEATURE_LOGGER_NAME = "oracle.jdbc.internal.replay.NonTxnReplayableBlob";
/*  51 */   private static Logger BLOB_REPLAY_LOGGER = null;
  
  static
  {
/*  55 */     if (BLOB_REPLAY_LOGGER == null) {
/*  56 */       BLOB_REPLAY_LOGGER = ReplayLoggerFactory.getLogger("oracle.jdbc.internal.replay.NonTxnReplayableBlob");
    }
  }
  
  @Pre
  protected void preForAll(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
/*  66 */     super.preForAll(paramMethod, paramObject, paramVarArgs);
  }
  
  @Pre
  @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="setBytes", args={long.class, byte[].class}), @oracle.jdbc.proxy.annotation.Signature(name="setBytes", args={long.class, byte[].class, int.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="truncate", args={long.class})})
  protected void preForBlobWrites(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
/*  79 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
    
/*  81 */     if (localReplayLifecycle != FailoverManagerImpl.ReplayLifecycle.ENABLED_NOT_REPLAYING) {
/*  82 */       return;
    }
/*  84 */     BLOB_REPLAY_LOGGER.log(Level.FINER, "On blob {0}, entering preForBlobWrites({1})", new Object[] { this, paramMethod.getName() });
    
/*  88 */     if (this.failoverMngr != null) {
/*  89 */       this.failoverMngr.disableReplayInternal(paramMethod, 371, "Replay disabled because of active transaction", null);
    }
    else
    {
/*  95 */       BLOB_REPLAY_LOGGER.log(Level.SEVERE, "On blob {0}, failover manager not set", this);
    }
    
/*  98 */     BLOB_REPLAY_LOGGER.log(Level.FINER, "On blob {0}, exiting preForBlobWrites()", this);
  }
  
  @Post
  protected Object postForAll(Method paramMethod, Object paramObject)
  {
/* 106 */     if ((paramObject instanceof NonTxnReplayableBase))
    {
/* 108 */       NonTxnReplayableBase localNonTxnReplayableBase = (NonTxnReplayableBase)paramObject;
/* 109 */       localNonTxnReplayableBase.setFailoverManager(getFailoverManager());
    }
    
/* 112 */     return super.postForAll(paramMethod, paramObject);
  }
  
  @OnError(SQLException.class)
  protected void onErrorVoidForAll(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/* 119 */     super.onErrorVoidForAll(paramMethod, paramSQLException);
  }
  
  @OnError(SQLException.class)
  protected Object onErrorForAll(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/* 126 */     return super.onErrorForAll(paramMethod, paramSQLException);
  }
  
  @GetDelegate
  protected abstract Object getDelegate();
  
  @SetDelegate
  protected abstract void setDelegate(Object paramObject);
  
  @GetCreator
  protected abstract Object getCreator();
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/driver/NonTxnReplayableBlob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */