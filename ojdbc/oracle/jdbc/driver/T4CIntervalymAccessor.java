package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class T4CIntervalymAccessor
  extends IntervalymAccessor
{
  T4CMAREngine mare;
/*  24 */   static int maxLength = 11;
  
  T4CIntervalymAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  46 */     super(paramOracleStatement, maxLength, paramShort, paramInt2, paramBoolean);
    
/*  48 */     this.mare = paramT4CMAREngine;
  }
  
  T4CIntervalymAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, int paramInt7, int paramInt8, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  66 */     super(paramOracleStatement, maxLength, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort);
    
/*  77 */     this.mare = paramT4CMAREngine;
/*  78 */     this.definedColumnType = paramInt7;
/*  79 */     this.definedColumnSize = paramInt8;
  }
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/*  87 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/*  94 */       this.mare.unmarshalUB2();
/*  95 */       this.mare.unmarshalUB2();
    }
/*  97 */     else if (this.statement.connection.versionNumber < 9200)
    {
/* 101 */       this.mare.unmarshalSB2();
      
/* 103 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/* 104 */         this.mare.unmarshalSB2();
      }
/* 106 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/* 108 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
/* 116 */   final int[] meta = new int[1];
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/* 131 */     if (this.isUseLess)
    {
/* 133 */       this.lastRowProcessed += 1;
      
/* 135 */       return false;
    }
    
/* 140 */     if (this.rowSpaceIndicator == null)
    {
/* 144 */       byte[] arrayOfByte = new byte['㺀'];
      
/* 146 */       this.mare.unmarshalCLR(arrayOfByte, 0, this.meta);
/* 147 */       processIndicator(this.meta[0]);
      
/* 149 */       this.lastRowProcessed += 1;
      
/* 151 */       return false;
    }
    
/* 155 */     int i = this.indicatorIndex + this.lastRowProcessed;
/* 156 */     int j = this.lengthIndex + this.lastRowProcessed;
    
/* 160 */     if (this.isNullByDescribe)
    {
/* 162 */       this.rowSpaceIndicator[i] = -1;
/* 163 */       this.rowSpaceIndicator[j] = 0;
/* 164 */       this.lastRowProcessed += 1;
      
/* 166 */       if (this.statement.connection.versionNumber < 9200) {
/* 167 */         processIndicator(0);
      }
/* 169 */       return false;
    }
    
/* 172 */     int k = this.columnIndex + this.lastRowProcessed * this.byteLength;
    
/* 176 */     this.mare.unmarshalCLR(this.rowSpaceByte, k, this.meta, this.byteLength);
    
/* 178 */     processIndicator(this.meta[0]);
    
/* 180 */     if (this.meta[0] == 0)
    {
/* 184 */       this.rowSpaceIndicator[i] = -1;
/* 185 */       this.rowSpaceIndicator[j] = 0;
    }
    else
    {
/* 189 */       this.rowSpaceIndicator[j] = ((short)this.meta[0]);
/* 190 */       this.rowSpaceIndicator[i] = 0;
    }
    
/* 193 */     this.lastRowProcessed += 1;
    
/* 195 */     return false;
  }
  
  void copyRow()
    throws SQLException, IOException
  {
    int i;
    
/* 205 */     if (this.lastRowProcessed == 0) {
/* 206 */       i = this.statement.rowPrefetchInLastFetch - 1;
    } else {
/* 208 */       i = this.lastRowProcessed - 1;
    }
    
/* 211 */     int j = this.columnIndex + this.lastRowProcessed * this.byteLength;
/* 212 */     int k = this.columnIndex + i * this.byteLength;
/* 213 */     int m = this.indicatorIndex + this.lastRowProcessed;
/* 214 */     int n = this.indicatorIndex + i;
/* 215 */     int i1 = this.lengthIndex + this.lastRowProcessed;
/* 216 */     int i2 = this.lengthIndex + i;
/* 217 */     int i3 = this.rowSpaceIndicator[i2];
/* 218 */     int i4 = this.metaDataIndex + this.lastRowProcessed * 1;
    
/* 220 */     int i5 = this.metaDataIndex + i * 1;
    
/* 224 */     this.rowSpaceIndicator[i1] = ((short)i3);
/* 225 */     this.rowSpaceIndicator[m] = this.rowSpaceIndicator[n];
    
/* 228 */     if (!this.isNullByDescribe)
    {
/* 230 */       System.arraycopy(this.rowSpaceByte, k, this.rowSpaceByte, j, i3);
    }
    
/* 235 */     System.arraycopy(this.rowSpaceMetaData, i5, this.rowSpaceMetaData, i4, 1);
    
/* 238 */     this.lastRowProcessed += 1;
  }
  
  void saveDataFromOldDefineBuffers(byte[] paramArrayOfByte, char[] paramArrayOfChar, short[] paramArrayOfShort, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 250 */     int i = this.columnIndex + (paramInt2 - 1) * this.byteLength;
    
/* 252 */     int j = this.columnIndexLastRow + (paramInt1 - 1) * this.byteLength;
    
/* 254 */     int k = this.indicatorIndex + paramInt2 - 1;
/* 255 */     int m = this.indicatorIndexLastRow + paramInt1 - 1;
/* 256 */     int n = this.lengthIndex + paramInt2 - 1;
/* 257 */     int i1 = this.lengthIndexLastRow + paramInt1 - 1;
/* 258 */     int i2 = paramArrayOfShort[i1];
    
/* 260 */     this.rowSpaceIndicator[n] = ((short)i2);
/* 261 */     this.rowSpaceIndicator[k] = paramArrayOfShort[m];
    
/* 264 */     if (i2 != 0)
    {
/* 266 */       System.arraycopy(paramArrayOfByte, j, this.rowSpaceByte, i, i2);
    }
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 284 */     String str = super.getString(paramInt);
    
/* 286 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
    {
/* 288 */       str = str.substring(0, this.definedColumnSize);
    }
/* 290 */     return str;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 300 */     if (this.definedColumnType == 0) {
/* 301 */       return super.getObject(paramInt);
    }
    
/* 304 */     Object localObject = null;
    SQLException localSQLException;
/* 306 */     if (this.rowSpaceIndicator == null)
    {
/* 308 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 309 */       localSQLException.fillInStackTrace();
/* 310 */       throw localSQLException;
    }
    
/* 313 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 315 */       switch (this.definedColumnType)
      {
      case -15: 
      case -9: 
      case -1: 
      case 1: 
      case 12: 
/* 329 */         return getString(paramInt);
      
      case -103: 
/* 332 */         return getINTERVALYM(paramInt);
      
      case -4: 
      case -3: 
      case -2: 
/* 339 */         return getBytes(paramInt);
      }
      
      
/* 343 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 344 */       localSQLException.fillInStackTrace();
/* 345 */       throw localSQLException;
    }
    
/* 351 */     return localObject;
  }
  
/* 357 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CIntervalymAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */