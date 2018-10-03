/*    */ package oracle.jdbc.pool;
/*    */ import java.sql.SQLException;
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ class OracleFailoverWorkerThread
/*    */   extends Thread
/*    */ {
/* 24 */   protected OracleImplicitConnectionCache implicitCache = null;
/* 25 */   protected int eventType = 0;
/* 26 */   protected String eventServiceName = null;
/* 27 */   protected String instanceNameKey = null;
/* 28 */   protected String databaseNameKey = null;
/* 29 */   protected String hostNameKey = null;
/* 30 */   protected String status = null;
/* 31 */   protected int cardinality = 0;
/*    */   
/*    */   OracleFailoverWorkerThread(OracleImplicitConnectionCache paramOracleImplicitConnectionCache, int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt2)
/*    */     throws SQLException
/*    */   {
/* 40 */     this.implicitCache = paramOracleImplicitConnectionCache;
/* 41 */     this.eventType = paramInt1;
/* 42 */     this.instanceNameKey = paramString1;
/* 43 */     this.databaseNameKey = paramString2;
/* 44 */     this.hostNameKey = paramString3;
/* 45 */     this.status = paramString4;
/* 46 */     this.cardinality = paramInt2;
/*    */   }
/*    */   
/*    */   public void run()
/*    */   {
/*    */     try
/*    */     {
/* 56 */       if (this.status != null)
/*    */       {
/* 58 */         this.implicitCache.processFailoverEvent(this.eventType, this.instanceNameKey, this.databaseNameKey, this.hostNameKey, this.status, this.cardinality);
/*    */       }
/*    */     }
/*    */     catch (Exception localException) {}
/*    */   }
/*    */   
/* 70 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleFailoverWorkerThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */