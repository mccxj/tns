/*    */ package oracle.jdbc.pool;
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ class OracleDatabaseInstance
/*    */ {
/* 38 */   String databaseUniqName = null;
/* 39 */   String instanceName = null;
/* 40 */   int percent = 0;
/* 41 */   int flag = 0;
/* 42 */   int attemptedConnRequestCount = 0;
/* 43 */   int numberOfConnectionsCount = 0;
/*    */   
/*    */   OracleDatabaseInstance(String paramString1, String paramString2)
/*    */   {
/* 49 */     this.databaseUniqName = paramString1;
/* 50 */     this.instanceName = paramString2;
/*    */   }
/*    */   
/* 55 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleDatabaseInstance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */