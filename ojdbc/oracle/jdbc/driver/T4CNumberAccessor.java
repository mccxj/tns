package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class T4CNumberAccessor
  extends NumberAccessor
{
  T4CMAREngine mare;
/*  39 */   boolean underlyingLongRaw = false;
  
  T4CNumberAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  45 */     super(paramOracleStatement, paramInt1, paramShort, paramInt2, paramBoolean);
    
/*  47 */     this.mare = paramT4CMAREngine;
  }
  
  T4CNumberAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, int paramInt7, int paramInt8, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  56 */     super(paramOracleStatement, paramInt1 == -1 ? paramInt8 : paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort);
    
/*  59 */     this.mare = paramT4CMAREngine;
    
/*  61 */     if ((paramOracleStatement != null) && (paramOracleStatement.implicitDefineForLobPrefetchDone))
    {
/*  63 */       this.definedColumnType = 0;
/*  64 */       this.definedColumnSize = 0;
    }
    else
    {
/*  68 */       this.definedColumnType = paramInt7;
/*  69 */       this.definedColumnSize = paramInt8;
    }
    
/*  72 */     if (paramInt1 == -1) {
/*  73 */       this.underlyingLongRaw = true;
    }
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/*  85 */     String str = super.getString(paramInt);
    
/*  87 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
    {
/*  89 */       str = str.substring(0, this.definedColumnSize);
    }
/*  91 */     return str;
  }
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/* 100 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/* 107 */       this.mare.unmarshalUB2();
/* 108 */       this.mare.unmarshalUB2();
    }
/* 110 */     else if (this.statement.connection.versionNumber < 9200)
    {
/* 114 */       this.mare.unmarshalSB2();
      
/* 116 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/* 117 */         this.mare.unmarshalSB2();
      }
/* 119 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/* 121 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
/* 130 */   final int[] meta = new int[1];
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/* 152 */     if (this.isUseLess)
    {
/* 154 */       this.lastRowProcessed += 1;
      
/* 156 */       return false;
    }
    
/* 161 */     if (this.rowSpaceIndicator == null)
    {
/* 165 */       byte[] arrayOfByte = new byte['㺀'];
      
/* 167 */       this.mare.unmarshalCLR(arrayOfByte, 0, this.meta);
/* 168 */       processIndicator(this.meta[0]);
      
/* 170 */       this.lastRowProcessed += 1;
      
/* 172 */       return false;
    }
    
/* 176 */     int i = this.indicatorIndex + this.lastRowProcessed;
/* 177 */     int j = this.lengthIndex + this.lastRowProcessed;
    
/* 181 */     if (this.isNullByDescribe)
    {
/* 183 */       this.rowSpaceIndicator[i] = -1;
/* 184 */       this.rowSpaceIndicator[j] = 0;
/* 185 */       this.lastRowProcessed += 1;
      
/* 187 */       if (this.statement.connection.versionNumber < 9200) {
/* 188 */         processIndicator(0);
      }
/* 190 */       return false;
    }
    
/* 193 */     int k = this.columnIndex + this.lastRowProcessed * this.byteLength;
    
/* 197 */     this.mare.unmarshalCLR(this.rowSpaceByte, k + 1, this.meta, this.byteLength - 1);
    
/* 201 */     this.rowSpaceByte[k] = ((byte)this.meta[0]);
    
/* 207 */     processIndicator(this.meta[0]);
    
/* 209 */     if (this.meta[0] == 0)
    {
/* 213 */       this.rowSpaceIndicator[i] = -1;
/* 214 */       this.rowSpaceIndicator[j] = 0;
    }
    else
    {
/* 218 */       this.rowSpaceIndicator[j] = ((short)this.meta[0]);
      
/* 223 */       this.rowSpaceIndicator[i] = 0;
    }
    
/* 226 */     this.lastRowProcessed += 1;
    
/* 228 */     return false;
  }
  
  void copyRow()
    throws SQLException, IOException
  {
    int i;
    
/* 238 */     if (this.lastRowProcessed == 0) {
/* 239 */       i = this.statement.rowPrefetchInLastFetch - 1;
    } else {
/* 241 */       i = this.lastRowProcessed - 1;
    }
    
/* 244 */     int j = this.columnIndex + this.lastRowProcessed * this.byteLength;
/* 245 */     int k = this.columnIndex + i * this.byteLength;
/* 246 */     int m = this.indicatorIndex + this.lastRowProcessed;
/* 247 */     int n = this.indicatorIndex + i;
/* 248 */     int i1 = this.lengthIndex + this.lastRowProcessed;
/* 249 */     int i2 = this.lengthIndex + i;
/* 250 */     int i3 = this.rowSpaceIndicator[i2];
/* 251 */     int i4 = this.metaDataIndex + this.lastRowProcessed * 1;
    
/* 253 */     int i5 = this.metaDataIndex + i * 1;
    
/* 257 */     this.rowSpaceIndicator[i1] = ((short)i3);
/* 258 */     this.rowSpaceIndicator[m] = this.rowSpaceIndicator[n];
    
/* 261 */     if (!this.isNullByDescribe)
    {
/* 263 */       System.arraycopy(this.rowSpaceByte, k, this.rowSpaceByte, j, i3 + 1);
    }
    
/* 268 */     System.arraycopy(this.rowSpaceMetaData, i5, this.rowSpaceMetaData, i4, 1);
    
/* 271 */     this.lastRowProcessed += 1;
  }
  
  void saveDataFromOldDefineBuffers(byte[] paramArrayOfByte, char[] paramArrayOfChar, short[] paramArrayOfShort, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 283 */     int i = this.columnIndex + (paramInt2 - 1) * this.byteLength;
    
/* 285 */     int j = this.columnIndexLastRow + (paramInt1 - 1) * this.byteLength;
    
/* 287 */     int k = this.indicatorIndex + paramInt2 - 1;
/* 288 */     int m = this.indicatorIndexLastRow + paramInt1 - 1;
/* 289 */     int n = this.lengthIndex + paramInt2 - 1;
/* 290 */     int i1 = this.lengthIndexLastRow + paramInt1 - 1;
/* 291 */     int i2 = paramArrayOfShort[i1];
    
/* 293 */     this.rowSpaceIndicator[n] = ((short)i2);
/* 294 */     this.rowSpaceIndicator[k] = paramArrayOfShort[m];
    
/* 297 */     if (i2 != 0)
    {
/* 299 */       System.arraycopy(paramArrayOfByte, j, this.rowSpaceByte, i, i2 + 1);
    }
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 312 */     if (this.definedColumnType == 0) {
/* 313 */       return super.getObject(paramInt);
    }
    
/* 316 */     Object localObject = null;
    SQLException localSQLException;
/* 318 */     if (this.rowSpaceIndicator == null)
    {
/* 320 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 321 */       localSQLException.fillInStackTrace();
/* 322 */       throw localSQLException;
    }
    
/* 325 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 327 */       switch (this.definedColumnType)
      {
      case -15: 
      case -9: 
      case -1: 
      case 1: 
      case 12: 
/* 341 */         return getString(paramInt);
      
      case 2: 
      case 3: 
/* 346 */         return getBigDecimal(paramInt);
      
      case 4: 
/* 349 */         return Integer.valueOf(getInt(paramInt));
      
      case -6: 
/* 352 */         return Byte.valueOf(getByte(paramInt));
      
      case 5: 
/* 355 */         return Short.valueOf(getShort(paramInt));
      
      case -7: 
      case 16: 
/* 360 */         return Boolean.valueOf(getBoolean(paramInt));
      
      case -5: 
/* 363 */         return Long.valueOf(getLong(paramInt));
      
      case 7: 
/* 366 */         return Float.valueOf(getFloat(paramInt));
      
      case 6: 
      case 8: 
/* 371 */         return Double.valueOf(getDouble(paramInt));
      
      case 91: 
/* 374 */         return getDate(paramInt);
      
      case 92: 
/* 377 */         return getTime(paramInt);
      
      case 93: 
/* 380 */         return getTimestamp(paramInt);
      
      case -4: 
      case -3: 
      case -2: 
/* 387 */         return getBytes(paramInt);
      }
      
      
/* 391 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 392 */       localSQLException.fillInStackTrace();
/* 393 */       throw localSQLException;
    }
    
/* 399 */     return localObject;
  }
  
/* 405 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CNumberAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */