package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class T4CBinaryFloatAccessor
  extends BinaryFloatAccessor
{
  T4CMAREngine mare;
/*  38 */   boolean underlyingLongRaw = false;
  
  T4CBinaryFloatAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  44 */     super(paramOracleStatement, paramInt1, paramShort, paramInt2, paramBoolean);
    
/*  46 */     this.mare = paramT4CMAREngine;
  }
  
  T4CBinaryFloatAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, int paramInt7, int paramInt8, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  55 */     super(paramOracleStatement, paramInt1 == -1 ? paramInt8 : paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort);
    
/*  58 */     this.mare = paramT4CMAREngine;
    
/*  60 */     if ((paramOracleStatement != null) && (paramOracleStatement.implicitDefineForLobPrefetchDone))
    {
/*  62 */       this.definedColumnType = 0;
/*  63 */       this.definedColumnSize = 0;
    }
    else
    {
/*  67 */       this.definedColumnType = paramInt7;
/*  68 */       this.definedColumnSize = paramInt8;
    }
    
/*  71 */     if (paramInt1 == -1) {
/*  72 */       this.underlyingLongRaw = true;
    }
  }
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/*  80 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/*  87 */       this.mare.unmarshalUB2();
/*  88 */       this.mare.unmarshalUB2();
    }
/*  90 */     else if (this.statement.connection.versionNumber < 9200)
    {
/*  94 */       this.mare.unmarshalSB2();
      
/*  96 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/*  97 */         this.mare.unmarshalSB2();
      }
/*  99 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/* 101 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 115 */     String str = super.getString(paramInt);
    
/* 117 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
    {
/* 119 */       str = str.substring(0, this.definedColumnSize);
    }
/* 121 */     return str;
  }
  
/* 129 */   final int[] meta = new int[1];
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/* 144 */     if (this.isUseLess)
    {
/* 146 */       this.lastRowProcessed += 1;
      
/* 148 */       return false;
    }
    
/* 153 */     if (this.rowSpaceIndicator == null)
    {
/* 157 */       byte[] arrayOfByte = new byte['㺀'];
      
/* 159 */       this.mare.unmarshalCLR(arrayOfByte, 0, this.meta);
/* 160 */       processIndicator(this.meta[0]);
      
/* 162 */       this.lastRowProcessed += 1;
      
/* 164 */       return false;
    }
    
/* 168 */     int i = this.indicatorIndex + this.lastRowProcessed;
/* 169 */     int j = this.lengthIndex + this.lastRowProcessed;
    
/* 173 */     if (this.isNullByDescribe)
    {
/* 175 */       this.rowSpaceIndicator[i] = -1;
/* 176 */       this.rowSpaceIndicator[j] = 0;
/* 177 */       this.lastRowProcessed += 1;
      
/* 179 */       if (this.statement.connection.versionNumber < 9200) {
/* 180 */         processIndicator(0);
      }
/* 182 */       return false;
    }
    
/* 185 */     int k = this.columnIndex + this.lastRowProcessed * this.byteLength;
    
/* 189 */     this.mare.unmarshalCLR(this.rowSpaceByte, k, this.meta, this.byteLength);
    
/* 191 */     processIndicator(this.meta[0]);
    
/* 193 */     if (this.meta[0] == 0)
    {
/* 197 */       this.rowSpaceIndicator[i] = -1;
/* 198 */       this.rowSpaceIndicator[j] = 0;
    }
    else
    {
/* 202 */       this.rowSpaceIndicator[j] = ((short)this.meta[0]);
/* 203 */       this.rowSpaceIndicator[i] = 0;
    }
    
/* 206 */     this.lastRowProcessed += 1;
    
/* 208 */     return false;
  }
  
  void copyRow()
    throws SQLException, IOException
  {
    int i;
    
/* 218 */     if (this.lastRowProcessed == 0) {
/* 219 */       i = this.statement.rowPrefetchInLastFetch - 1;
    } else {
/* 221 */       i = this.lastRowProcessed - 1;
    }
    
/* 224 */     int j = this.columnIndex + this.lastRowProcessed * this.byteLength;
/* 225 */     int k = this.columnIndex + i * this.byteLength;
/* 226 */     int m = this.indicatorIndex + this.lastRowProcessed;
/* 227 */     int n = this.indicatorIndex + i;
/* 228 */     int i1 = this.lengthIndex + this.lastRowProcessed;
/* 229 */     int i2 = this.lengthIndex + i;
/* 230 */     int i3 = this.rowSpaceIndicator[i2];
/* 231 */     int i4 = this.metaDataIndex + this.lastRowProcessed * 1;
    
/* 233 */     int i5 = this.metaDataIndex + i * 1;
    
/* 237 */     this.rowSpaceIndicator[i1] = ((short)i3);
/* 238 */     this.rowSpaceIndicator[m] = this.rowSpaceIndicator[n];
    
/* 241 */     if (!this.isNullByDescribe)
    {
/* 243 */       System.arraycopy(this.rowSpaceByte, k, this.rowSpaceByte, j, i3);
    }
    
/* 248 */     System.arraycopy(this.rowSpaceMetaData, i5, this.rowSpaceMetaData, i4, 1);
    
/* 251 */     this.lastRowProcessed += 1;
  }
  
  void saveDataFromOldDefineBuffers(byte[] paramArrayOfByte, char[] paramArrayOfChar, short[] paramArrayOfShort, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 263 */     int i = this.columnIndex + (paramInt2 - 1) * this.byteLength;
    
/* 265 */     int j = this.columnIndexLastRow + (paramInt1 - 1) * this.byteLength;
    
/* 267 */     int k = this.indicatorIndex + paramInt2 - 1;
/* 268 */     int m = this.indicatorIndexLastRow + paramInt1 - 1;
/* 269 */     int n = this.lengthIndex + paramInt2 - 1;
/* 270 */     int i1 = this.lengthIndexLastRow + paramInt1 - 1;
/* 271 */     int i2 = paramArrayOfShort[i1];
    
/* 273 */     this.rowSpaceIndicator[n] = ((short)i2);
/* 274 */     this.rowSpaceIndicator[k] = paramArrayOfShort[m];
    
/* 277 */     if (i2 != 0)
    {
/* 279 */       System.arraycopy(paramArrayOfByte, j, this.rowSpaceByte, i, i2);
    }
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 291 */     if (this.definedColumnType == 0) {
/* 292 */       return super.getObject(paramInt);
    }
    
/* 295 */     Object localObject = null;
    SQLException localSQLException;
/* 297 */     if (this.rowSpaceIndicator == null)
    {
/* 299 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 300 */       localSQLException.fillInStackTrace();
/* 301 */       throw localSQLException;
    }
    
/* 304 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 306 */       switch (this.definedColumnType)
      {
      case -15: 
      case -9: 
      case -1: 
      case 1: 
      case 12: 
/* 320 */         return getString(paramInt);
      
      case 4: 
/* 323 */         return Integer.valueOf(getInt(paramInt));
      
      case -6: 
/* 326 */         return Byte.valueOf(getByte(paramInt));
      
      case 5: 
/* 329 */         return Short.valueOf(getShort(paramInt));
      
      case 6: 
      case 8: 
/* 334 */         return Double.valueOf(getDouble(paramInt));
      
      case 2: 
      case 3: 
/* 339 */         return getBigDecimal(paramInt);
      
      case 7: 
/* 342 */         return Float.valueOf(getFloat(paramInt));
      
      case 100: 
/* 345 */         return getBINARY_FLOAT(paramInt);
      
      case -5: 
/* 348 */         return Long.valueOf(getLong(paramInt));
      
      case -4: 
      case -3: 
      case -2: 
/* 355 */         return getBytes(paramInt);
      }
      
      
/* 359 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 360 */       localSQLException.fillInStackTrace();
/* 361 */       throw localSQLException;
    }
    
/* 367 */     return localObject;
  }
  
/* 372 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CBinaryFloatAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */