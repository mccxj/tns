/*    */ package oracle.jdbc.driver;
/*    */ import java.sql.SQLException;
/*    */ abstract class OracleTimeout
/*    */ {
/*    */   static OracleTimeout newTimeout(String paramString)
/*    */     throws SQLException
/*    */   {
/* 39 */     OracleTimeoutThreadPerVM localOracleTimeoutThreadPerVM = new OracleTimeoutThreadPerVM(paramString);
/* 40 */     return localOracleTimeoutThreadPerVM;
/*    */   }
/*    */   
/* 74 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */   
/*    */   abstract void setTimeout(long paramLong, OracleStatement paramOracleStatement)
/*    */     throws SQLException;
/*    */   
/*    */   abstract void cancelTimeout()
/*    */     throws SQLException;
/*    */   
/*    */   abstract void close()
/*    */     throws SQLException;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleTimeout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */