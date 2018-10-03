package oracle.jdbc.driver;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Map;
import oracle.sql.BINARY_FLOAT;
import oracle.sql.Datum;
import oracle.sql.NUMBER;
class BinaryFloatAccessor
  extends Accessor
{
  static final int MAXLENGTH = 4;
  
  BinaryFloatAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
    throws SQLException
  {
/*  27 */     init(paramOracleStatement, 100, 100, paramShort, paramBoolean);
/*  28 */     initForDataAccess(paramInt2, paramInt1, null);
  }
  
  BinaryFloatAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
    throws SQLException
  {
/*  37 */     init(paramOracleStatement, 100, 100, paramShort, false);
/*  38 */     initForDescribe(100, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, null);
    
/*  41 */     int i = paramOracleStatement.maxFieldSize;
    
/*  43 */     if ((i > 0) && ((paramInt1 == 0) || (i < paramInt1))) {
/*  44 */       paramInt1 = i;
    }
/*  46 */     initForDataAccess(0, paramInt1, null);
  }
  
  void init(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, int paramInt3, short paramShort, int paramInt4)
    throws SQLException
  {
/*  54 */     init(paramOracleStatement, paramInt1, paramInt2, paramShort, false);
/*  55 */     initForDataAccess(paramInt4, paramInt3, null);
  }
  
  void init(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, short paramShort)
    throws SQLException
  {
/*  64 */     init(paramOracleStatement, paramInt1, paramInt2, paramShort, false);
/*  65 */     initForDescribe(paramInt1, paramInt3, paramBoolean, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramShort, null);
    
/*  68 */     int i = paramOracleStatement.maxFieldSize;
    
/*  70 */     if ((i > 0) && ((paramInt3 == 0) || (i < paramInt3))) {
/*  71 */       paramInt3 = i;
    }
/*  73 */     initForDataAccess(0, paramInt3, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  81 */     if (paramInt1 != 0) {
/*  82 */       this.externalType = paramInt1;
    }
/*  84 */     this.internalTypeMaxLength = 4;
    
/*  86 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*  87 */       this.internalTypeMaxLength = paramInt2;
    }
/*  89 */     this.byteLength = this.internalTypeMaxLength;
  }
  
  float getFloat(int paramInt)
    throws SQLException
  {
/* 130 */     if (this.rowSpaceIndicator == null)
    {
/* 134 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 135 */       localSQLException.fillInStackTrace();
/* 136 */       throw localSQLException;
    }
    
/* 141 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 142 */       return 0.0F;
    }
/* 144 */     int i = this.columnIndex + this.byteLength * paramInt;
/* 145 */     int j = this.rowSpaceByte[i];
/* 146 */     int k = this.rowSpaceByte[(i + 1)];
/* 147 */     int m = this.rowSpaceByte[(i + 2)];
/* 148 */     int n = this.rowSpaceByte[(i + 3)];
    
/* 150 */     if ((j & 0x80) != 0)
    {
/* 152 */       j &= 0x7F;
/* 153 */       k &= 0xFF;
/* 154 */       m &= 0xFF;
/* 155 */       n &= 0xFF;
    }
    else
    {
/* 159 */       j = (j ^ 0xFFFFFFFF) & 0xFF;
/* 160 */       k = (k ^ 0xFFFFFFFF) & 0xFF;
/* 161 */       m = (m ^ 0xFFFFFFFF) & 0xFF;
/* 162 */       n = (n ^ 0xFFFFFFFF) & 0xFF;
    }
    
/* 165 */     int i1 = j << 24 | k << 16 | m << 8 | n;
    
/* 167 */     return Float.intBitsToFloat(i1);
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 181 */     if (this.rowSpaceIndicator == null)
    {
/* 185 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 186 */       localSQLException.fillInStackTrace();
/* 187 */       throw localSQLException;
    }
    
/* 192 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) {
/* 193 */       return Float.toString(getFloat(paramInt));
    }
/* 195 */     return null;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 209 */     if (this.rowSpaceIndicator == null)
    {
/* 213 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 214 */       localSQLException.fillInStackTrace();
/* 215 */       throw localSQLException;
    }
    
/* 220 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) {
/* 221 */       return new Float(getFloat(paramInt));
    }
/* 223 */     return null;
  }
  
  Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
/* 238 */     if (this.rowSpaceIndicator == null)
    {
/* 242 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 243 */       localSQLException.fillInStackTrace();
/* 244 */       throw localSQLException;
    }
    
/* 249 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) {
/* 250 */       return new Float(getFloat(paramInt));
    }
/* 252 */     return null;
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 266 */     return getBINARY_FLOAT(paramInt);
  }
  
  BINARY_FLOAT getBINARY_FLOAT(int paramInt)
    throws SQLException
  {
/* 280 */     BINARY_FLOAT localBINARY_FLOAT = null;
    
/* 282 */     if (this.rowSpaceIndicator == null)
    {
/* 286 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 287 */       localSQLException.fillInStackTrace();
/* 288 */       throw localSQLException;
    }
    
/* 293 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 295 */       int i = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/* 296 */       int j = this.columnIndex + this.byteLength * paramInt;
/* 297 */       byte[] arrayOfByte = new byte[i];
      
/* 299 */       System.arraycopy(this.rowSpaceByte, j, arrayOfByte, 0, i);
      
/* 301 */       localBINARY_FLOAT = new BINARY_FLOAT(arrayOfByte);
    }
    
/* 304 */     return localBINARY_FLOAT;
  }
  
  NUMBER getNUMBER(int paramInt)
    throws SQLException
  {
/* 311 */     if (this.rowSpaceIndicator == null)
    {
/* 315 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 316 */       localSQLException.fillInStackTrace();
/* 317 */       throw localSQLException;
    }
    
/* 322 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) {
/* 323 */       return new NUMBER(getFloat(paramInt));
    }
/* 325 */     return null;
  }
  
  BigInteger getBigInteger(int paramInt)
    throws SQLException
  {
/* 332 */     if (this.rowSpaceIndicator == null)
    {
/* 336 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 337 */       localSQLException.fillInStackTrace();
/* 338 */       throw localSQLException;
    }
    
/* 343 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) {
/* 344 */       return new BigInteger(getString(paramInt));
    }
/* 346 */     return null;
  }
  
  BigDecimal getBigDecimal(int paramInt)
    throws SQLException
  {
/* 353 */     if (this.rowSpaceIndicator == null)
    {
/* 357 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 358 */       localSQLException.fillInStackTrace();
/* 359 */       throw localSQLException;
    }
    
/* 364 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) {
/* 365 */       return new BigDecimal(getString(paramInt));
    }
/* 367 */     return null;
  }
  
  byte getByte(int paramInt)
    throws SQLException
  {
/* 374 */     return (byte)(int)getFloat(paramInt);
  }
  
  short getShort(int paramInt)
    throws SQLException
  {
/* 381 */     return (short)(int)getFloat(paramInt);
  }
  
  int getInt(int paramInt)
    throws SQLException
  {
/* 388 */     return (int)getFloat(paramInt);
  }
  
  long getLong(int paramInt)
    throws SQLException
  {
/* 395 */     return getFloat(paramInt);
  }
  
  double getDouble(int paramInt)
    throws SQLException
  {
/* 402 */     return getFloat(paramInt);
  }
  
/* 407 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/BinaryFloatAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */