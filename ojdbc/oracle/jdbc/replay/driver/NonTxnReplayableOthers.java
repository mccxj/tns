package oracle.jdbc.replay.driver;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Savepoint;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleSavepoint;
import oracle.jdbc.proxy.annotation.GetCreator;
import oracle.jdbc.proxy.annotation.GetDelegate;
import oracle.jdbc.proxy.annotation.OnError;
import oracle.jdbc.proxy.annotation.Post;
import oracle.jdbc.proxy.annotation.Pre;
import oracle.jdbc.proxy.annotation.ProxyFor;
import oracle.jdbc.proxy.annotation.SetDelegate;
@ProxyFor({Savepoint.class, SQLOutput.class, OracleSavepoint.class})
public abstract class NonTxnReplayableOthers
  extends NonTxnReplayableBase
  implements Replayable
{
  private static final String OTHERS_FEATURE_LOGGER_NAME = "oracle.jdbc.internal.replay.NonTxnReplayableOthers";
/*  49 */   private static Logger OTHERS_REPLAY_LOGGER = null;
  
  static
  {
/*  53 */     if (OTHERS_REPLAY_LOGGER == null) {
/*  54 */       OTHERS_REPLAY_LOGGER = ReplayLoggerFactory.getLogger("oracle.jdbc.internal.replay.NonTxnReplayableOthers");
    }
  }
  
  @Pre
  protected void preForAll(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
/*  64 */     OTHERS_REPLAY_LOGGER.log(Level.FINER, "On proxy {0}, entering preForAll({1})", new Object[] { this, paramMethod.getName() });
    
/*  68 */     if (this.failoverMngr != null) {
/*  69 */       this.failoverMngr.disableReplayInternal(paramMethod, 372, "Replay disabled because of nonreplayable call", null);
    }
    else
    {
/*  75 */       OTHERS_REPLAY_LOGGER.log(Level.SEVERE, "On proxy {0}, failover manager not set", this);
    }
    
/*  79 */     OTHERS_REPLAY_LOGGER.log(Level.FINER, "On proxy {0}, exiting preForAll()", this);
  }
  
  @Post
  protected Object postForAll(Method paramMethod, Object paramObject)
  {
/*  87 */     return super.postForAll(paramMethod, paramObject);
  }
  
  @OnError(SQLException.class)
  protected void onErrorVoidForAll(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/*  94 */     super.onErrorVoidForAll(paramMethod, paramSQLException);
  }
  
  @OnError(SQLException.class)
  protected Object onErrorForAll(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/* 101 */     return super.onErrorForAll(paramMethod, paramSQLException);
  }
  
  @GetDelegate
  protected abstract Object getDelegate();
  
  @SetDelegate
  protected abstract void setDelegate(Object paramObject);
  
  @GetCreator
  protected abstract Object getCreator();
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/driver/NonTxnReplayableOthers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */