/*    */ package oracle.jdbc.driver;
/*    */ import java.sql.SQLException;
/*    */ class OutRawAccessor
/*    */   extends RawCommonAccessor
/*    */ {
/*    */   static final int MAXLENGTH_NEW = 32767;
/*    */   static final int MAXLENGTH_OLD = 32767;
/*    */   
/*    */   OutRawAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2)
/*    */     throws SQLException
/*    */   {
/* 27 */     init(paramOracleStatement, 23, 23, paramShort, true);
/* 28 */     initForDataAccess(paramInt2, paramInt1, null);
/*    */   }
/*    */   
/*    */   void initForDataAccess(int paramInt1, int paramInt2, String paramString)
/*    */     throws SQLException
/*    */   {
/* 36 */     if (paramInt1 != 0) {
/* 37 */       this.externalType = paramInt1;
/*    */     }
/* 39 */     if (this.statement.connection.getVersionNumber() >= 8000) {
/* 40 */       this.internalTypeMaxLength = 32767;
/*    */     } else {
/* 42 */       this.internalTypeMaxLength = 32767;
/*    */     }
/* 44 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/* 45 */       this.internalTypeMaxLength = paramInt2;
/*    */     }
/* 47 */     this.byteLength = this.internalTypeMaxLength;
/*    */   }
/*    */   
/*    */   byte[] getBytes(int paramInt)
/*    */     throws SQLException
/*    */   {
/* 54 */     byte[] arrayOfByte = null;
/*    */     
/* 56 */     if (this.rowSpaceIndicator == null)
/*    */     {
/* 60 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 61 */       localSQLException.fillInStackTrace();
/* 62 */       throw localSQLException;
/*    */     }
/*    */     
/* 68 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*    */     {
/* 70 */       int i = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/* 71 */       int j = this.columnIndex + this.byteLength * paramInt;
/*    */       
/* 73 */       arrayOfByte = new byte[i];
/*    */       
/* 75 */       System.arraycopy(this.rowSpaceByte, j, arrayOfByte, 0, i);
/*    */     }
/*    */     
/* 78 */     return arrayOfByte;
/*    */   }
/*    */   
/* 83 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OutRawAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */