/*    */ package oracle.jdbc.driver;
/*    */ import java.sql.SQLException;
/*    */ import oracle.jdbc.internal.OracleStatement.SqlKind;
/*    */ class CharAccessor
/*    */   extends CharCommonAccessor
/*    */ {
/*    */   CharAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
/*    */     throws SQLException
/*    */   {
/* 23 */     int i = 2000;
/*    */     
/* 25 */     if (paramInt1 > i) {
/* 26 */       i = paramInt1;
/*    */     }
/* 28 */     if (paramOracleStatement.sqlKind == OracleStatement.SqlKind.PLSQL_BLOCK) {
/* 29 */       i = 32766;
/*    */     }
/* 31 */     init(paramOracleStatement, 96, 9, paramInt1, paramShort, paramInt2, paramBoolean, i, 255);
/*    */   }
/*    */   
/*    */   CharAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
/*    */     throws SQLException
/*    */   {
/* 45 */     int i = 2000;
/*    */     
/* 47 */     if (paramInt1 > i) {
/* 48 */       i = paramInt1;
/*    */     }
/* 50 */     if (paramOracleStatement.sqlKind == OracleStatement.SqlKind.PLSQL_BLOCK) {
/* 51 */       i = 32766;
/*    */     }
/* 53 */     init(paramOracleStatement, 96, 9, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, i, 255);
/*    */   }
/*    */   
/* 65 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/CharAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */