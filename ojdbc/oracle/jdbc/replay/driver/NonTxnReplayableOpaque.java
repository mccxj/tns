package oracle.jdbc.replay.driver;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import oracle.jdbc.proxy.annotation.GetCreator;
import oracle.jdbc.proxy.annotation.GetDelegate;
import oracle.jdbc.proxy.annotation.OnError;
import oracle.jdbc.proxy.annotation.Post;
import oracle.jdbc.proxy.annotation.Pre;
import oracle.jdbc.proxy.annotation.ProxyFor;
import oracle.jdbc.proxy.annotation.ProxyResult;
import oracle.jdbc.proxy.annotation.ProxyResultPolicy;
import oracle.jdbc.proxy.annotation.SetDelegate;
@ProxyFor({oracle.jdbc.OracleOpaque.class, oracle.jdbc.internal.OracleOpaque.class})
public abstract class NonTxnReplayableOpaque
  extends NonTxnReplayableBase
  implements Replayable
{
  private static final String OPAQUE_FEATURE_LOGGER_NAME = "oracle.jdbc.internal.replay.NonTxnReplayableOpaque";
/*  51 */   private static Logger OPAQUE_REPLAY_LOGGER = null;
  
  static
  {
/*  55 */     if (OPAQUE_REPLAY_LOGGER == null) {
/*  56 */       OPAQUE_REPLAY_LOGGER = ReplayLoggerFactory.getLogger("oracle.jdbc.internal.replay.NonTxnReplayableOpaque");
    }
  }
  
  @Pre
  protected void preForAll(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
/*  66 */     super.preForAll(paramMethod, paramObject, paramVarArgs);
  }
  
  @Post
  protected Object postForAll(Method paramMethod, Object paramObject)
  {
/*  73 */     if ((paramObject instanceof NonTxnReplayableBase))
    {
/*  75 */       NonTxnReplayableBase localNonTxnReplayableBase = (NonTxnReplayableBase)paramObject;
/*  76 */       localNonTxnReplayableBase.setFailoverManager(getFailoverManager());
    }
    
/*  79 */     return super.postForAll(paramMethod, paramObject);
  }
  
  @OnError(SQLException.class)
  protected void onErrorVoidForAll(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/*  86 */     super.onErrorVoidForAll(paramMethod, paramSQLException);
  }
  
  @OnError(SQLException.class)
  protected Object onErrorForAll(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/*  93 */     return super.onErrorForAll(paramMethod, paramSQLException);
  }
  
  @ProxyResult(ProxyResultPolicy.MANUAL)
  public Connection getJavaSqlConnection()
  {
/* 114 */     return (Connection)getFailoverManager().getConnectionProxy();
  }
  
  @GetDelegate
  protected abstract Object getDelegate();
  
  @SetDelegate
  protected abstract void setDelegate(Object paramObject);
  
  @GetCreator
  protected abstract Object getCreator();
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/driver/NonTxnReplayableOpaque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */