/*    */ package oracle.jdbc.replay.driver;
/*    */ import java.lang.reflect.Method;
/*    */ import java.sql.SQLException;
/*    */ import java.util.logging.Logger;
/*    */ import oracle.jdbc.proxy.annotation.GetCreator;
/*    */ import oracle.jdbc.proxy.annotation.GetDelegate;
/*    */ import oracle.jdbc.proxy.annotation.OnError;
/*    */ import oracle.jdbc.proxy.annotation.Post;
/*    */ import oracle.jdbc.proxy.annotation.Pre;
/*    */ import oracle.jdbc.proxy.annotation.ProxyFor;
/*    */ import oracle.jdbc.proxy.annotation.SetDelegate;
/*    */ @ProxyFor({oracle.jdbc.OracleBfile.class, oracle.jdbc.internal.OracleBfile.class})
/*    */ public abstract class NonTxnReplayableBfile
/*    */   extends NonTxnReplayableBase
/*    */   implements Replayable
/*    */ {
/*    */   private static final String BFILE_FEATURE_LOGGER_NAME = "oracle.jdbc.internal.replay.NonTxnReplayableBfile";
/* 47 */   private static Logger BFILE_REPLAY_LOGGER = null;
/*    */   
/*    */   static
/*    */   {
/* 51 */     if (BFILE_REPLAY_LOGGER == null) {
/* 52 */       BFILE_REPLAY_LOGGER = ReplayLoggerFactory.getLogger("oracle.jdbc.internal.replay.NonTxnReplayableBfile");
/*    */     }
/*    */   }
/*    */   
/*    */   @Pre
/*    */   protected void preForAll(Method paramMethod, Object paramObject, Object... paramVarArgs)
/*    */   {
/* 62 */     super.preForAll(paramMethod, paramObject, paramVarArgs);
/*    */   }
/*    */   
/*    */   @Post
/*    */   protected Object postForAll(Method paramMethod, Object paramObject)
/*    */   {
/* 69 */     if ((paramObject instanceof NonTxnReplayableBase))
/*    */     {
/* 71 */       NonTxnReplayableBase localNonTxnReplayableBase = (NonTxnReplayableBase)paramObject;
/* 72 */       localNonTxnReplayableBase.setFailoverManager(getFailoverManager());
/*    */     }
/*    */     
/* 75 */     return super.postForAll(paramMethod, paramObject);
/*    */   }
/*    */   
/*    */   @OnError(SQLException.class)
/*    */   protected void onErrorVoidForAll(Method paramMethod, SQLException paramSQLException)
/*    */     throws SQLException
/*    */   {
/* 82 */     super.onErrorVoidForAll(paramMethod, paramSQLException);
/*    */   }
/*    */   
/*    */   @OnError(SQLException.class)
/*    */   protected Object onErrorForAll(Method paramMethod, SQLException paramSQLException)
/*    */     throws SQLException
/*    */   {
/* 89 */     return super.onErrorForAll(paramMethod, paramSQLException);
/*    */   }
/*    */   
/*    */   @GetDelegate
/*    */   protected abstract Object getDelegate();
/*    */   
/*    */   @SetDelegate
/*    */   protected abstract void setDelegate(Object paramObject);
/*    */   
/*    */   @GetCreator
/*    */   protected abstract Object getCreator();
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/driver/NonTxnReplayableBfile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */