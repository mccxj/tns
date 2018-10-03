/*    */ package oracle.jdbc.driver;
/*    */ import java.sql.SQLException;
/*    */ import oracle.jdbc.internal.OracleStatement.SqlKind;
/*    */ import oracle.sql.ROWID;
/*    */ class VarcharAccessor
/*    */   extends CharCommonAccessor
/*    */ {
/*    */   VarcharAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
/*    */     throws SQLException
/*    */   {
/* 24 */     int i = 4000;
/*    */     
/* 26 */     if (paramOracleStatement.sqlKind == OracleStatement.SqlKind.PLSQL_BLOCK) {
/* 27 */       i = paramOracleStatement.connection.plsqlVarcharParameter4KOnly ? 4000 : 32766;
/*    */     }
/*    */     
/* 30 */     init(paramOracleStatement, 1, 9, paramInt1, paramShort, paramInt2, paramBoolean, i, 2000);
/*    */   }
/*    */   
/*    */   VarcharAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
/*    */     throws SQLException
/*    */   {
/* 44 */     int i = 4000;
/*    */     
/* 46 */     if (paramOracleStatement.sqlKind == OracleStatement.SqlKind.PLSQL_BLOCK) {
/* 47 */       i = paramOracleStatement.connection.plsqlVarcharParameter4KOnly ? 4000 : 32766;
/*    */     }
/*    */     
/* 50 */     init(paramOracleStatement, 1, 9, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, i, 2000);
/*    */   }
/*    */   
/*    */   ROWID getROWID(int paramInt)
/*    */     throws SQLException
/*    */   {
/* 64 */     if (this.rowSpaceIndicator == null)
/*    */     {
/* 68 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 69 */       ((SQLException)localObject).fillInStackTrace();
/* 70 */       throw ((Throwable)localObject);
/*    */     }
/*    */     
/* 75 */     Object localObject = null;
/*    */     
/* 77 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*    */     {
/* 79 */       byte[] arrayOfByte = getBytesInternal(paramInt);
/* 80 */       if (arrayOfByte != null)
/* 81 */         localObject = new ROWID(arrayOfByte);
/*    */     }
/* 83 */     return (ROWID)localObject;
/*    */   }
/*    */   
/* 88 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/VarcharAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */