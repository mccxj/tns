package oracle.jdbc.replay.driver;
import java.lang.reflect.Method;
import java.sql.NClob;
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
@ProxyFor({NClob.class, oracle.jdbc.OracleNClob.class, oracle.jdbc.internal.OracleNClob.class})
public abstract class NonTxnReplayableNClob
  extends NonTxnReplayableClob
  implements Replayable
{
  private static final String NCLOB_FEATURE_LOGGER_NAME = "oracle.jdbc.internal.replay.NonTxnReplayableNClob";
/*  51 */   private static Logger NCLOB_REPLAY_LOGGER = null;
  
  static
  {
/*  55 */     if (NCLOB_REPLAY_LOGGER == null) {
/*  56 */       NCLOB_REPLAY_LOGGER = ReplayLoggerFactory.getLogger("oracle.jdbc.internal.replay.NonTxnReplayableNClob");
    }
  }
  
  @Pre
  protected void preForAll(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
/*  66 */     super.preForAll(paramMethod, paramObject, paramVarArgs);
  }
  
  @Pre
  @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="setString", args={long.class, String.class}), @oracle.jdbc.proxy.annotation.Signature(name="setString", args={long.class, String.class, int.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="truncate", args={long.class})})
  protected void preForClobWrites(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
/*  79 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
    
/*  81 */     if (localReplayLifecycle != FailoverManagerImpl.ReplayLifecycle.ENABLED_NOT_REPLAYING) {
/*  82 */       return;
    }
/*  84 */     NCLOB_REPLAY_LOGGER.log(Level.FINER, "On nclob {0}, entering preForClobWrites({1})", new Object[] { this, paramMethod.getName() });
    
/*  88 */     super.preForClobWrites(paramMethod, paramObject, paramVarArgs);
    
/*  90 */     NCLOB_REPLAY_LOGGER.log(Level.FINER, "On nclob {0}, exiting preForClobWrites()", this);
  }
  
  @Post
  protected Object postForAll(Method paramMethod, Object paramObject)
  {
/*  98 */     if ((paramObject instanceof NonTxnReplayableBase))
    {
/* 100 */       NonTxnReplayableBase localNonTxnReplayableBase = (NonTxnReplayableBase)paramObject;
/* 101 */       localNonTxnReplayableBase.setFailoverManager(getFailoverManager());
    }
    
/* 104 */     return super.postForAll(paramMethod, paramObject);
  }
  
  @OnError(SQLException.class)
  protected void onErrorVoidForAll(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/* 111 */     super.onErrorVoidForAll(paramMethod, paramSQLException);
  }
  
  @OnError(SQLException.class)
  protected Object onErrorForAll(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/* 118 */     return super.onErrorForAll(paramMethod, paramSQLException);
  }
  
  @GetDelegate
  protected abstract Object getDelegate();
  
  @SetDelegate
  protected abstract void setDelegate(Object paramObject);
  
  @GetCreator
  protected abstract Object getCreator();
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/driver/NonTxnReplayableNClob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */