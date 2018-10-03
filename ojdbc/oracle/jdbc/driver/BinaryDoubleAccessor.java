package oracle.jdbc.driver;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Map;
import oracle.sql.BINARY_DOUBLE;
import oracle.sql.Datum;
import oracle.sql.NUMBER;
class BinaryDoubleAccessor
  extends Accessor
{
  static final int MAXLENGTH = 8;
  
  BinaryDoubleAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
    throws SQLException
  {
/*  27 */     init(paramOracleStatement, 101, 101, paramShort, paramBoolean);
/*  28 */     initForDataAccess(paramInt2, paramInt1, null);
  }
  
  BinaryDoubleAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
    throws SQLException
  {
/*  37 */     init(paramOracleStatement, 101, 101, paramShort, false);
/*  38 */     initForDescribe(101, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, null);
    
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
/*  84 */     this.internalTypeMaxLength = 8;
    
/*  86 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*  87 */       this.internalTypeMaxLength = paramInt2;
    }
/*  89 */     this.byteLength = this.internalTypeMaxLength;
  }
  
  double getDouble(int paramInt)
    throws SQLException
  {
/* 122 */     if (this.rowSpaceIndicator == null)
    {
/* 126 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 127 */       localSQLException.fillInStackTrace();
/* 128 */       throw localSQLException;
    }
    
/* 133 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 134 */       return 0.0D;
    }
/* 136 */     int i = this.columnIndex + this.byteLength * paramInt;
/* 137 */     int j = this.rowSpaceByte[i];
/* 138 */     int k = this.rowSpaceByte[(i + 1)];
/* 139 */     int m = this.rowSpaceByte[(i + 2)];
/* 140 */     int n = this.rowSpaceByte[(i + 3)];
/* 141 */     int i1 = this.rowSpaceByte[(i + 4)];
/* 142 */     int i2 = this.rowSpaceByte[(i + 5)];
/* 143 */     int i3 = this.rowSpaceByte[(i + 6)];
/* 144 */     int i4 = this.rowSpaceByte[(i + 7)];
    
/* 146 */     if ((j & 0x80) != 0)
    {
/* 148 */       j &= 0x7F;
/* 149 */       k &= 0xFF;
/* 150 */       m &= 0xFF;
/* 151 */       n &= 0xFF;
/* 152 */       i1 &= 0xFF;
/* 153 */       i2 &= 0xFF;
/* 154 */       i3 &= 0xFF;
/* 155 */       i4 &= 0xFF;
    }
    else
    {
/* 159 */       j = (j ^ 0xFFFFFFFF) & 0xFF;
/* 160 */       k = (k ^ 0xFFFFFFFF) & 0xFF;
/* 161 */       m = (m ^ 0xFFFFFFFF) & 0xFF;
/* 162 */       n = (n ^ 0xFFFFFFFF) & 0xFF;
/* 163 */       i1 = (i1 ^ 0xFFFFFFFF) & 0xFF;
/* 164 */       i2 = (i2 ^ 0xFFFFFFFF) & 0xFF;
/* 165 */       i3 = (i3 ^ 0xFFFFFFFF) & 0xFF;
/* 166 */       i4 = (i4 ^ 0xFFFFFFFF) & 0xFF;
    }
    
/* 169 */     int i5 = j << 24 | k << 16 | m << 8 | n;
/* 170 */     int i6 = i1 << 24 | i2 << 16 | i3 << 8 | i4;
/* 171 */     long l = i5 << 32 | i6 & 0xFFFFFFFF;
    
/* 173 */     return Double.longBitsToDouble(l);
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 187 */     if (this.rowSpaceIndicator == null)
    {
/* 191 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 192 */       localSQLException.fillInStackTrace();
/* 193 */       throw localSQLException;
    }
    
/* 198 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) {
/* 199 */       return Double.toString(getDouble(paramInt));
    }
/* 201 */     return null;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 215 */     if (this.rowSpaceIndicator == null)
    {
/* 219 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 220 */       localSQLException.fillInStackTrace();
/* 221 */       throw localSQLException;
    }
    
/* 226 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) {
/* 227 */       return new Double(getDouble(paramInt));
    }
/* 229 */     return null;
  }
  
  Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
/* 244 */     return new Double(getDouble(paramInt));
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 258 */     return getBINARY_DOUBLE(paramInt);
  }
  
  BINARY_DOUBLE getBINARY_DOUBLE(int paramInt)
    throws SQLException
  {
/* 272 */     BINARY_DOUBLE localBINARY_DOUBLE = null;
    
/* 274 */     if (this.rowSpaceIndicator == null)
    {
/* 278 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 279 */       localSQLException.fillInStackTrace();
/* 280 */       throw localSQLException;
    }
    
/* 285 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 287 */       int i = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/* 288 */       int j = this.columnIndex + this.byteLength * paramInt;
/* 289 */       byte[] arrayOfByte = new byte[i];
      
/* 291 */       System.arraycopy(this.rowSpaceByte, j, arrayOfByte, 0, i);
      
/* 293 */       localBINARY_DOUBLE = new BINARY_DOUBLE(arrayOfByte);
    }
    
/* 296 */     return localBINARY_DOUBLE;
  }
  
  NUMBER getNUMBER(int paramInt)
    throws SQLException
  {
/* 303 */     if (this.rowSpaceIndicator == null)
    {
/* 307 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 308 */       localSQLException.fillInStackTrace();
/* 309 */       throw localSQLException;
    }
    
/* 314 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) {
/* 315 */       return new NUMBER(getDouble(paramInt));
    }
/* 317 */     return null;
  }
  
  BigInteger getBigInteger(int paramInt)
    throws SQLException
  {
/* 324 */     if (this.rowSpaceIndicator == null)
    {
/* 328 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 329 */       localSQLException.fillInStackTrace();
/* 330 */       throw localSQLException;
    }
    
/* 335 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) {
/* 336 */       return new BigInteger(getString(paramInt));
    }
/* 338 */     return null;
  }
  
  BigDecimal getBigDecimal(int paramInt)
    throws SQLException
  {
/* 345 */     if (this.rowSpaceIndicator == null)
    {
/* 349 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 350 */       localSQLException.fillInStackTrace();
/* 351 */       throw localSQLException;
    }
    
/* 356 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) {
/* 357 */       return new BigDecimal(getString(paramInt));
    }
/* 359 */     return null;
  }
  
  byte getByte(int paramInt)
    throws SQLException
  {
/* 366 */     return (byte)(int)getDouble(paramInt);
  }
  
  short getShort(int paramInt)
    throws SQLException
  {
/* 373 */     return (short)(int)getDouble(paramInt);
  }
  
  int getInt(int paramInt)
    throws SQLException
  {
/* 380 */     return (int)getDouble(paramInt);
  }
  
  long getLong(int paramInt)
    throws SQLException
  {
/* 387 */     return getDouble(paramInt);
  }
  
  float getFloat(int paramInt)
    throws SQLException
  {
/* 394 */     return (float)getDouble(paramInt);
  }
  
/* 399 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/BinaryDoubleAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */