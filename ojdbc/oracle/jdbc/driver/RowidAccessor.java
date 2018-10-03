package oracle.jdbc.driver;
import java.sql.SQLException;
import java.util.Map;
import oracle.sql.Datum;
import oracle.sql.ROWID;
class RowidAccessor
  extends Accessor
{
  static final int maxLength = 128;
  static final int EXTENDED_ROWID_MAX_LENGTH = 18;
  
  RowidAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
    throws SQLException
  {
/*  29 */     init(paramOracleStatement, 104, 9, paramShort, paramBoolean);
/*  30 */     initForDataAccess(paramInt2, paramInt1, null);
  }
  
  RowidAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
    throws SQLException
  {
/*  39 */     init(paramOracleStatement, 104, 9, paramShort, false);
/*  40 */     initForDescribe(104, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, null);
    
/*  42 */     initForDataAccess(0, paramInt1, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  50 */     if (paramInt1 != 0) {
/*  51 */       this.externalType = paramInt1;
    }
/*  53 */     this.internalTypeMaxLength = 128;
    
/*  57 */     this.byteLength = (this.internalTypeMaxLength + 2);
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/*  73 */     String str = null;
    
/*  75 */     if (this.rowSpaceIndicator == null)
    {
/*  79 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  80 */       localSQLException.fillInStackTrace();
/*  81 */       throw localSQLException;
    }
    
/*  87 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/*  89 */       int i = this.columnIndex + this.byteLength * paramInt;
      
/*  93 */       int j = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
      
/*  95 */       str = new String(this.rowSpaceByte, i + 2, j);
    }
    
/*  98 */     return str;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 114 */     return getROWID(paramInt);
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 130 */     return getROWID(paramInt);
  }
  
  ROWID getROWID(int paramInt)
    throws SQLException
  {
/* 146 */     byte[] arrayOfByte = getBytes(paramInt);
    
/* 148 */     return arrayOfByte == null ? null : new ROWID(arrayOfByte);
  }
  
  byte[] getBytes(int paramInt)
    throws SQLException
  {
/* 155 */     byte[] arrayOfByte = null;
    
/* 157 */     if (this.rowSpaceIndicator == null)
    {
/* 161 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 162 */       localSQLException.fillInStackTrace();
/* 163 */       throw localSQLException;
    }
    
/* 169 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 174 */       int i = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/* 175 */       int j = this.columnIndex + this.byteLength * paramInt;
      
/* 177 */       arrayOfByte = new byte[i];
      
/* 179 */       System.arraycopy(this.rowSpaceByte, j + 2, arrayOfByte, 0, i);
    }
    
/* 189 */     return arrayOfByte;
  }
  
  Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
/* 206 */     return getROWID(paramInt);
  }
  
/* 211 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/RowidAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */