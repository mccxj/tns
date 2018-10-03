/*    */ package oracle.jdbc.driver;
/*    */ import java.sql.SQLException;
/*    */ class NumberAccessor
/*    */   extends NumberCommonAccessor
/*    */ {
/*    */   NumberAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
/*    */     throws SQLException
/*    */   {
/* 18 */     init(paramOracleStatement, paramInt1, paramShort, paramInt2, paramBoolean);
/*    */   }
/*    */   
/*    */   NumberAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
/*    */     throws SQLException
/*    */   {
/* 31 */     init(paramOracleStatement, 2, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort);
/*    */   }
/*    */   
/* 41 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NumberAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */