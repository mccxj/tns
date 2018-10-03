/*    */ package oracle.jdbc.replay.driver;
/*    */ import java.lang.reflect.Method;
/*    */ import java.sql.Array;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Map;
/*    */ import java.util.logging.Logger;
/*    */ import oracle.jdbc.proxy.annotation.GetCreator;
/*    */ import oracle.jdbc.proxy.annotation.GetDelegate;
/*    */ import oracle.jdbc.proxy.annotation.OnError;
/*    */ import oracle.jdbc.proxy.annotation.Post;
/*    */ import oracle.jdbc.proxy.annotation.Pre;
/*    */ import oracle.jdbc.proxy.annotation.ProxyFor;
/*    */ import oracle.jdbc.proxy.annotation.ProxyResult;
/*    */ import oracle.jdbc.proxy.annotation.ProxyResultPolicy;
/*    */ import oracle.jdbc.proxy.annotation.SetDelegate;
/*    */ @ProxyFor({Array.class, oracle.jdbc.OracleArray.class, oracle.jdbc.internal.OracleArray.class})
/*    */ public abstract class NonTxnReplayableArray
/*    */   extends NonTxnReplayableBase
/*    */   implements Replayable
/*    */ {
/*    */   private static final String ARRAY_FEATURE_LOGGER_NAME = "oracle.jdbc.internal.replay.NonTxnReplayableArray";
/* 55 */   private static Logger ARRAY_REPLAY_LOGGER = null;
/*    */   
/*    */   static
/*    */   {
/* 59 */     if (ARRAY_REPLAY_LOGGER == null) {
/* 60 */       ARRAY_REPLAY_LOGGER = ReplayLoggerFactory.getLogger("oracle.jdbc.internal.replay.NonTxnReplayableArray");
/*    */     }
/*    */   }
/*    */   
/*    */   @Pre
/*    */   protected void preForAll(Method paramMethod, Object paramObject, Object... paramVarArgs)
/*    */   {
/* 70 */     super.preForAll(paramMethod, paramObject, paramVarArgs);
/*    */   }
/*    */   
/*    */   @Post
/*    */   protected Object postForAll(Method paramMethod, Object paramObject)
/*    */   {
/* 77 */     if ((paramObject instanceof NonTxnReplayableBase))
/*    */     {
/* 79 */       NonTxnReplayableBase localNonTxnReplayableBase = (NonTxnReplayableBase)paramObject;
/* 80 */       localNonTxnReplayableBase.setFailoverManager(getFailoverManager());
/*    */     }
/*    */     
/* 83 */     return super.postForAll(paramMethod, paramObject);
/*    */   }
/*    */   
/*    */   @OnError(SQLException.class)
/*    */   protected void onErrorVoidForAll(Method paramMethod, SQLException paramSQLException)
/*    */     throws SQLException
/*    */   {
/* 90 */     super.onErrorVoidForAll(paramMethod, paramSQLException);
/*    */   }
/*    */   
/*    */   @OnError(SQLException.class)
/*    */   protected Object onErrorForAll(Method paramMethod, SQLException paramSQLException)
/*    */     throws SQLException
/*    */   {
/* 97 */     return super.onErrorForAll(paramMethod, paramSQLException);
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
/*    */   
/*    */   @ProxyResult(ProxyResultPolicy.CREATE)
/*    */   public abstract ResultSet getResultSet()
/*    */     throws SQLException;
/*    */   
/*    */   @ProxyResult(ProxyResultPolicy.CREATE)
/*    */   public abstract ResultSet getResultSet(long paramLong, int paramInt)
/*    */     throws SQLException;
/*    */   
/*    */   @ProxyResult(ProxyResultPolicy.CREATE)
/*    */   public abstract ResultSet getResultSet(long paramLong, int paramInt, Map<String, Class<?>> paramMap)
/*    */     throws SQLException;
/*    */   
/*    */   @ProxyResult(ProxyResultPolicy.CREATE)
/*    */   public abstract ResultSet getResultSet(Map<String, Class<?>> paramMap)
/*    */     throws SQLException;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/driver/NonTxnReplayableArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */