/*    */ package oracle.jdbc.driver;
/*    */ import java.io.IOException;
/*    */ import java.sql.SQLException;
/*    */ class T4CInputStream
/*    */   extends OracleInputStream
/*    */ {
/*    */   T4CInputStream(OracleStatement paramOracleStatement, int paramInt, Accessor paramAccessor)
/*    */   {
/* 27 */     super(paramOracleStatement, paramInt, paramAccessor);
/*    */   }
/*    */   
/*    */   public boolean isNull()
/*    */     throws IOException
/*    */   {
/* 35 */     if (!this.statement.connection.useFetchSizeWithLongColumn) {
/* 36 */       return super.isNull();
/*    */     }
/* 38 */     boolean bool = false;
/*    */     
/*    */     try
/*    */     {
/* 42 */       int i = this.statement.currentRow;
/*    */       
/* 44 */       if (i < 0) {
/* 45 */         i = 0;
/*    */       }
/* 47 */       if (i >= this.statement.validRows) {
/* 48 */         return true;
/*    */       }
/* 50 */       bool = this.accessor.isNull(i);
/*    */     }
/*    */     catch (SQLException localSQLException)
/*    */     {
/* 55 */       IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 56 */       localIOException.fillInStackTrace();
/* 57 */       throw localIOException;
/*    */     }
/*    */     
/* 61 */     return bool;
/*    */   }
/*    */   
/*    */   public int getBytes(int paramInt)
/*    */     throws IOException
/*    */   {
/* 68 */     synchronized (this.statement.connection) {
/* 69 */       int i = -1;
/*    */       
/*    */       try
/*    */       {
/* 73 */         if ((this.statement.connection.lifecycle == 1) || (this.statement.connection.lifecycle == 2))
/*    */         {
/* 75 */           i = this.accessor.readStream(this.resizableBuffer, this.initialBufferSize);
/*    */         }
/*    */       }
/*    */       catch (SQLException localSQLException1) {
/* 79 */         throw new IOException(localSQLException1.getMessage());
/*    */       }
/*    */       catch (IOException localIOException)
/*    */       {
/*    */         try
/*    */         {
/* 86 */           ((T4CConnection)this.statement.connection).handleIOException(localIOException);
/*    */         }
/*    */         catch (SQLException localSQLException2) {}
/*    */         
/* 90 */         throw localIOException;
/*    */       }
/*    */       
/* 93 */       return i;
/*    */     }
/*    */   }
/*    */   
/* 98 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */