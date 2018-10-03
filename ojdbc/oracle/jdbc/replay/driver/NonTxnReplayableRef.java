/*    */ package oracle.jdbc.replay.driver;
/*    */ import java.lang.reflect.Method;
/*    */ import java.sql.Ref;
/*    */ import java.sql.SQLException;
/*    */ import java.util.logging.Logger;
/*    */ import oracle.jdbc.proxy.annotation.GetCreator;
/*    */ import oracle.jdbc.proxy.annotation.GetDelegate;
/*    */ import oracle.jdbc.proxy.annotation.OnError;
/*    */ import oracle.jdbc.proxy.annotation.Post;
/*    */ import oracle.jdbc.proxy.annotation.Pre;
/*    */ import oracle.jdbc.proxy.annotation.ProxyFor;
/*    */ import oracle.jdbc.proxy.annotation.SetDelegate;
/*    */ @ProxyFor({Ref.class, oracle.jdbc.OracleRef.class, oracle.jdbc.internal.OracleRef.class})
/*    */ public abstract class NonTxnReplayableRef
/*    */   extends NonTxnReplayableBase
/*    */   implements Replayable
/*    */ {
/*    */   private static final String REF_FEATURE_LOGGER_NAME = "oracle.jdbc.internal.replay.NonTxnReplayableRef";
/* 48 */   private static Logger REF_REPLAY_LOGGER = null;
/*    */   
/*    */   static
/*    */   {
/* 52 */     if (REF_REPLAY_LOGGER == null) {
/* 53 */       REF_REPLAY_LOGGER = ReplayLoggerFactory.getLogger("oracle.jdbc.internal.replay.NonTxnReplayableRef");
/*    */     }
/*    */   }
/*    */   
/*    */   @Pre
/*    */   protected void preForAll(Method paramMethod, Object paramObject, Object... paramVarArgs)
/*    */   {
/* 63 */     super.preForAll(paramMethod, paramObject, paramVarArgs);
/*    */   }
/*    */   
/*    */   @Post
/*    */   protected Object postForAll(Method paramMethod, Object paramObject)
/*    */   {
/* 70 */     if ((paramObject instanceof NonTxnReplayableBase))
/*    */     {
/* 72 */       NonTxnReplayableBase localNonTxnReplayableBase = (NonTxnReplayableBase)paramObject;
/* 73 */       localNonTxnReplayableBase.setFailoverManager(getFailoverManager());
/*    */     }
/*    */     
/* 76 */     return super.postForAll(paramMethod, paramObject);
/*    */   }
/*    */   
/*    */   @OnError(SQLException.class)
/*    */   protected void onErrorVoidForAll(Method paramMethod, SQLException paramSQLException)
/*    */     throws SQLException
/*    */   {
/* 83 */     super.onErrorVoidForAll(paramMethod, paramSQLException);
/*    */   }
/*    */   
/*    */   @OnError(SQLException.class)
/*    */   protected Object onErrorForAll(Method paramMethod, SQLException paramSQLException)
/*    */     throws SQLException
/*    */   {
/* 90 */     return super.onErrorForAll(paramMethod, paramSQLException);
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
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/driver/NonTxnReplayableRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */