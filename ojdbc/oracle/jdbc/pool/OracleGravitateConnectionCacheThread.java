/*    */ package oracle.jdbc.pool;
/*    */ import java.sql.SQLException;
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ class OracleGravitateConnectionCacheThread
/*    */   extends Thread
/*    */ {
/* 23 */   protected OracleImplicitConnectionCache implicitCache = null;
/*    */   
/*    */   OracleGravitateConnectionCacheThread(OracleImplicitConnectionCache paramOracleImplicitConnectionCache)
/*    */     throws SQLException
/*    */   {
/* 30 */     this.implicitCache = paramOracleImplicitConnectionCache;
/*    */   }
/*    */   
/*    */   public void run()
/*    */   {
/* 38 */     this.implicitCache.gravitateCache();
/*    */   }
/*    */   
/* 43 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleGravitateConnectionCacheThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */