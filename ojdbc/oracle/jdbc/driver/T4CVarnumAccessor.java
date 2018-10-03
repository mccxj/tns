package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class T4CVarnumAccessor
  extends VarnumAccessor
{
  T4CMAREngine mare;
/*  37 */   boolean underlyingLongRaw = false;
  
  T4CVarnumAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  43 */     super(paramOracleStatement, paramInt1, paramShort, paramInt2, paramBoolean);
    
/*  45 */     this.mare = paramT4CMAREngine;
  }
  
  T4CVarnumAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, int paramInt7, int paramInt8, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  54 */     super(paramOracleStatement, paramInt1 == -1 ? paramInt8 : paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort);
    
/*  57 */     this.mare = paramT4CMAREngine;
    
/*  59 */     if ((paramOracleStatement != null) && (paramOracleStatement.implicitDefineForLobPrefetchDone))
    {
/*  61 */       this.definedColumnType = 0;
/*  62 */       this.definedColumnSize = 0;
    }
    else
    {
/*  66 */       this.definedColumnType = paramInt7;
/*  67 */       this.definedColumnSize = paramInt8;
    }
    
/*  70 */     if (paramInt1 == -1) {
/*  71 */       this.underlyingLongRaw = true;
    }
  }
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/*  78 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/*  85 */       this.mare.unmarshalUB2();
/*  86 */       this.mare.unmarshalUB2();
    }
/*  88 */     else if (this.statement.connection.versionNumber < 9200)
    {
/*  92 */       this.mare.unmarshalSB2();
      
/*  94 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/*  95 */         this.mare.unmarshalSB2();
      }
/*  97 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/*  99 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
/* 107 */   final int[] meta = new int[1];
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/* 129 */     if (this.isUseLess)
    {
/* 131 */       this.lastRowProcessed += 1;
      
/* 133 */       return false;
    }
    
/* 138 */     if (this.rowSpaceIndicator == null)
    {
/* 142 */       byte[] arrayOfByte = new byte['㺀'];
      
/* 144 */       this.mare.unmarshalCLR(arrayOfByte, 0, this.meta);
/* 145 */       processIndicator(this.meta[0]);
      
/* 147 */       this.lastRowProcessed += 1;
      
/* 149 */       return false;
    }
    
/* 153 */     int i = this.indicatorIndex + this.lastRowProcessed;
/* 154 */     int j = this.lengthIndex + this.lastRowProcessed;
    
/* 158 */     if (this.isNullByDescribe)
    {
/* 160 */       this.rowSpaceIndicator[i] = -1;
/* 161 */       this.rowSpaceIndicator[j] = 0;
/* 162 */       this.lastRowProcessed += 1;
      
/* 164 */       if (this.statement.connection.versionNumber < 9200) {
/* 165 */         processIndicator(0);
      }
/* 167 */       return false;
    }
    
/* 170 */     int k = this.columnIndex + this.lastRowProcessed * this.byteLength;
    
/* 174 */     this.mare.unmarshalCLR(this.rowSpaceByte, k + 1, this.meta, this.byteLength - 1);
    
/* 178 */     this.rowSpaceByte[k] = ((byte)this.meta[0]);
    
/* 184 */     processIndicator(this.meta[0]);
    
/* 186 */     if (this.meta[0] == 0)
    {
/* 190 */       this.rowSpaceIndicator[i] = -1;
/* 191 */       this.rowSpaceIndicator[j] = 0;
    }
    else
    {
/* 195 */       this.rowSpaceIndicator[j] = ((short)this.meta[0]);
      
/* 200 */       this.rowSpaceIndicator[i] = 0;
    }
    
/* 203 */     this.lastRowProcessed += 1;
    
/* 205 */     return false;
  }
  
  void copyRow()
    throws SQLException, IOException
  {
    int i;
    
/* 215 */     if (this.lastRowProcessed == 0) {
/* 216 */       i = this.statement.rowPrefetchInLastFetch - 1;
    } else {
/* 218 */       i = this.lastRowProcessed - 1;
    }
    
/* 221 */     int j = this.columnIndex + this.lastRowProcessed * this.byteLength;
/* 222 */     int k = this.columnIndex + i * this.byteLength;
/* 223 */     int m = this.indicatorIndex + this.lastRowProcessed;
/* 224 */     int n = this.indicatorIndex + i;
/* 225 */     int i1 = this.lengthIndex + this.lastRowProcessed;
/* 226 */     int i2 = this.lengthIndex + i;
/* 227 */     int i3 = this.rowSpaceIndicator[i2];
/* 228 */     int i4 = this.metaDataIndex + this.lastRowProcessed * 1;
    
/* 230 */     int i5 = this.metaDataIndex + i * 1;
    
/* 234 */     this.rowSpaceIndicator[i1] = ((short)i3);
/* 235 */     this.rowSpaceIndicator[m] = this.rowSpaceIndicator[n];
    
/* 238 */     if (!this.isNullByDescribe)
    {
/* 240 */       System.arraycopy(this.rowSpaceByte, k, this.rowSpaceByte, j, i3 + 1);
    }
    
/* 245 */     System.arraycopy(this.rowSpaceMetaData, i5, this.rowSpaceMetaData, i4, 1);
    
/* 248 */     this.lastRowProcessed += 1;
  }
  
  void saveDataFromOldDefineBuffers(byte[] paramArrayOfByte, char[] paramArrayOfChar, short[] paramArrayOfShort, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 260 */     int i = this.columnIndex + (paramInt2 - 1) * this.byteLength;
    
/* 262 */     int j = this.columnIndexLastRow + (paramInt1 - 1) * this.byteLength;
    
/* 264 */     int k = this.indicatorIndex + paramInt2 - 1;
/* 265 */     int m = this.indicatorIndexLastRow + paramInt1 - 1;
/* 266 */     int n = this.lengthIndex + paramInt2 - 1;
/* 267 */     int i1 = this.lengthIndexLastRow + paramInt1 - 1;
/* 268 */     int i2 = paramArrayOfShort[i1];
    
/* 270 */     this.rowSpaceIndicator[n] = ((short)i2);
/* 271 */     this.rowSpaceIndicator[k] = paramArrayOfShort[m];
    
/* 274 */     if (i2 != 0)
    {
/* 276 */       System.arraycopy(paramArrayOfByte, j, this.rowSpaceByte, i, i2 + 1);
    }
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 294 */     String str = super.getString(paramInt);
    
/* 296 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
    {
/* 298 */       str = str.substring(0, this.definedColumnSize);
    }
/* 300 */     return str;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 309 */     if (this.definedColumnType == 0) {
/* 310 */       return super.getObject(paramInt);
    }
    
/* 313 */     Object localObject = null;
    SQLException localSQLException;
/* 315 */     if (this.rowSpaceIndicator == null)
    {
/* 317 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 318 */       localSQLException.fillInStackTrace();
/* 319 */       throw localSQLException;
    }
    
/* 322 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 324 */       switch (this.definedColumnType)
      {
      case -1: 
      case 1: 
      case 12: 
/* 332 */         return getString(paramInt);
      
      case 2: 
      case 3: 
/* 337 */         return getBigDecimal(paramInt);
      
      case 4: 
/* 340 */         return Integer.valueOf(getInt(paramInt));
      
      case -6: 
/* 343 */         return Byte.valueOf(getByte(paramInt));
      
      case 5: 
/* 346 */         return Short.valueOf(getShort(paramInt));
      
      case -7: 
      case 16: 
/* 351 */         return Boolean.valueOf(getBoolean(paramInt));
      
      case -5: 
/* 354 */         return Long.valueOf(getLong(paramInt));
      
      case 7: 
/* 357 */         return Float.valueOf(getFloat(paramInt));
      
      case 6: 
      case 8: 
/* 362 */         return Double.valueOf(getDouble(paramInt));
      
      case 91: 
/* 365 */         return getDate(paramInt);
      
      case 92: 
/* 368 */         return getTime(paramInt);
      
      case 93: 
/* 371 */         return getTimestamp(paramInt);
      
      case -4: 
      case -3: 
      case -2: 
/* 378 */         return getBytes(paramInt);
      }
      
      
/* 382 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 383 */       localSQLException.fillInStackTrace();
/* 384 */       throw localSQLException;
    }
    
/* 390 */     return localObject;
  }
  
/* 396 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CVarnumAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */