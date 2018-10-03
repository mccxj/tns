package oracle.jdbc.driver;
import java.sql.SQLException;
class RawAccessor
  extends RawCommonAccessor
{
  static final int MAXLENGTH_NEW = 2000;
  static final int MAXLENGTH_OLD = 255;
  
  RawAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
    throws SQLException
  {
/*  27 */     init(paramOracleStatement, 23, 15, paramShort, paramBoolean);
/*  28 */     initForDataAccess(paramInt2, paramInt1, null);
  }
  
  RawAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
    throws SQLException
  {
/*  37 */     init(paramOracleStatement, 23, 15, paramShort, false);
/*  38 */     initForDescribe(23, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, null);
    
/*  41 */     int i = paramOracleStatement.maxFieldSize;
    
/*  43 */     if ((i > 0) && ((paramInt1 == 0) || (i < paramInt1))) {
/*  44 */       paramInt1 = i;
    }
/*  46 */     initForDataAccess(0, paramInt1, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  54 */     if (paramInt1 != 0) {
/*  55 */       this.externalType = paramInt1;
    }
/*  57 */     if (this.statement.connection.getVersionNumber() >= 8000) {
/*  58 */       this.internalTypeMaxLength = 2000;
    } else {
/*  60 */       this.internalTypeMaxLength = 255;
    }
/*  62 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*  63 */       this.internalTypeMaxLength = paramInt2;
    }
/*  65 */     this.byteLength = (this.internalTypeMaxLength + 2);
  }
  
  byte[] getBytes(int paramInt)
    throws SQLException
  {
/*  72 */     byte[] arrayOfByte = null;
    
/*  74 */     if (this.rowSpaceIndicator == null)
    {
/*  78 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  79 */       localSQLException.fillInStackTrace();
/*  80 */       throw localSQLException;
    }
    
/*  86 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/*  90 */       int i = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/*  91 */       int j = this.columnIndex + this.byteLength * paramInt;
      
/*  93 */       arrayOfByte = new byte[i];
      
/*  95 */       System.arraycopy(this.rowSpaceByte, j + 2, arrayOfByte, 0, i);
    }
    
/*  98 */     return arrayOfByte;
  }
  
/* 103 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/RawAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */