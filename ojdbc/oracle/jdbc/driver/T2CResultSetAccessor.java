/*    */ package oracle.jdbc.driver;
/*    */ import java.sql.SQLException;
/*    */ class T2CResultSetAccessor
/*    */   extends ResultSetAccessor
/*    */ {
/*    */   T2CResultSetAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
/*    */     throws SQLException
/*    */   {
/* 20 */     super(paramOracleStatement, paramInt1 * 2, paramShort, paramInt2, paramBoolean);
/*    */   }
/*    */   
/*    */   T2CResultSetAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
/*    */     throws SQLException
/*    */   {
/* 27 */     super(paramOracleStatement, paramInt1 * 2, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort);
/*    */   }
/*    */   
/*    */   byte[] getBytes(int paramInt)
/*    */     throws SQLException
/*    */   {
/* 41 */     byte[] arrayOfByte = null;
/*    */     
/* 43 */     if (this.rowSpaceIndicator == null)
/*    */     {
/* 47 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 48 */       localSQLException.fillInStackTrace();
/* 49 */       throw localSQLException;
/*    */     }
/*    */     
/* 54 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*    */     {
/* 56 */       int i = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/* 57 */       int j = ((T2CConnection)this.statement.connection).byteAlign;
/* 58 */       int k = this.columnIndex + (j - 1) & (j - 1 ^ 0xFFFFFFFF);
/*    */       
/* 60 */       int m = k + i * paramInt;
/*    */       
/* 62 */       arrayOfByte = new byte[i];
/* 63 */       System.arraycopy(this.rowSpaceByte, m, arrayOfByte, 0, i);
/*    */     }
/*    */     
/* 66 */     return arrayOfByte;
/*    */   }
/*    */   
/* 70 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T2CResultSetAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */