package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class T4COutRawAccessor
  extends OutRawAccessor
{
  T4CMAREngine mare;
  
  T4COutRawAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  41 */     super(paramOracleStatement, paramInt1, paramShort, paramInt2);
    
/*  43 */     this.mare = paramT4CMAREngine;
  }
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/*  50 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/*  57 */       this.mare.unmarshalUB2();
/*  58 */       this.mare.unmarshalUB2();
    }
/*  60 */     else if (this.statement.connection.versionNumber < 9200)
    {
/*  64 */       this.mare.unmarshalSB2();
      
/*  66 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/*  67 */         this.mare.unmarshalSB2();
      }
/*  69 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/*  71 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
/*  79 */   final int[] meta = new int[1];
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/*  94 */     if (this.isUseLess)
    {
/*  96 */       this.lastRowProcessed += 1;
      
/*  98 */       return false;
    }
    
/* 103 */     if (this.rowSpaceIndicator == null)
    {
/* 107 */       byte[] arrayOfByte = new byte['㺀'];
      
/* 109 */       this.mare.unmarshalCLR(arrayOfByte, 0, this.meta);
/* 110 */       processIndicator(this.meta[0]);
      
/* 112 */       this.lastRowProcessed += 1;
      
/* 114 */       return false;
    }
    
/* 118 */     int i = this.indicatorIndex + this.lastRowProcessed;
/* 119 */     int j = this.lengthIndex + this.lastRowProcessed;
    
/* 123 */     if (this.isNullByDescribe)
    {
/* 125 */       this.rowSpaceIndicator[i] = -1;
/* 126 */       this.rowSpaceIndicator[j] = 0;
/* 127 */       this.lastRowProcessed += 1;
      
/* 129 */       if (this.statement.connection.versionNumber < 9200) {
/* 130 */         processIndicator(0);
      }
/* 132 */       return false;
    }
    
/* 135 */     int k = this.columnIndex + this.lastRowProcessed * this.byteLength;
    
/* 139 */     this.mare.unmarshalCLR(this.rowSpaceByte, k, this.meta, this.byteLength);
    
/* 141 */     processIndicator(this.meta[0]);
    
/* 143 */     if (this.meta[0] == 0)
    {
/* 147 */       this.rowSpaceIndicator[i] = -1;
/* 148 */       this.rowSpaceIndicator[j] = 0;
    }
    else
    {
/* 152 */       this.rowSpaceIndicator[j] = ((short)this.meta[0]);
/* 153 */       this.rowSpaceIndicator[i] = 0;
    }
    
/* 156 */     this.lastRowProcessed += 1;
    
/* 158 */     return false;
  }
  
  void copyRow()
    throws SQLException, IOException
  {
    int i;
    
/* 168 */     if (this.lastRowProcessed == 0) {
/* 169 */       i = this.statement.rowPrefetchInLastFetch - 1;
    } else {
/* 171 */       i = this.lastRowProcessed - 1;
    }
    
/* 174 */     int j = this.columnIndex + this.lastRowProcessed * this.byteLength;
/* 175 */     int k = this.columnIndex + i * this.byteLength;
/* 176 */     int m = this.indicatorIndex + this.lastRowProcessed;
/* 177 */     int n = this.indicatorIndex + i;
/* 178 */     int i1 = this.lengthIndex + this.lastRowProcessed;
/* 179 */     int i2 = this.lengthIndex + i;
/* 180 */     int i3 = this.rowSpaceIndicator[i2];
/* 181 */     int i4 = this.metaDataIndex + this.lastRowProcessed * 1;
    
/* 183 */     int i5 = this.metaDataIndex + i * 1;
    
/* 187 */     this.rowSpaceIndicator[i1] = ((short)i3);
/* 188 */     this.rowSpaceIndicator[m] = this.rowSpaceIndicator[n];
    
/* 191 */     if (!this.isNullByDescribe)
    {
/* 193 */       System.arraycopy(this.rowSpaceByte, k, this.rowSpaceByte, j, i3);
    }
    
/* 198 */     System.arraycopy(this.rowSpaceMetaData, i5, this.rowSpaceMetaData, i4, 1);
    
/* 201 */     this.lastRowProcessed += 1;
  }
  
  void saveDataFromOldDefineBuffers(byte[] paramArrayOfByte, char[] paramArrayOfChar, short[] paramArrayOfShort, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 213 */     int i = this.columnIndex + (paramInt2 - 1) * this.byteLength;
    
/* 215 */     int j = this.columnIndexLastRow + (paramInt1 - 1) * this.byteLength;
    
/* 217 */     int k = this.indicatorIndex + paramInt2 - 1;
/* 218 */     int m = this.indicatorIndexLastRow + paramInt1 - 1;
/* 219 */     int n = this.lengthIndex + paramInt2 - 1;
/* 220 */     int i1 = this.lengthIndexLastRow + paramInt1 - 1;
/* 221 */     int i2 = paramArrayOfShort[i1];
    
/* 223 */     this.rowSpaceIndicator[n] = ((short)i2);
/* 224 */     this.rowSpaceIndicator[k] = paramArrayOfShort[m];
    
/* 227 */     if (i2 != 0)
    {
/* 229 */       System.arraycopy(paramArrayOfByte, j, this.rowSpaceByte, i, i2);
    }
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 247 */     String str = super.getString(paramInt);
    
/* 249 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
    {
/* 251 */       str = str.substring(0, this.definedColumnSize);
    }
/* 253 */     return str;
  }
  
  byte[] getBytes(int paramInt)
    throws SQLException
  {
/* 262 */     byte[] arrayOfByte = null;
    
/* 264 */     if (this.rowSpaceIndicator == null)
    {
/* 266 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 267 */       localSQLException.fillInStackTrace();
/* 268 */       throw localSQLException;
    }
    
/* 273 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 275 */       int i = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/* 276 */       int j = this.columnIndex + this.byteLength * paramInt;
      
/* 278 */       arrayOfByte = new byte[i];
      
/* 280 */       System.arraycopy(this.rowSpaceByte, j, arrayOfByte, 0, i);
    }
    
/* 283 */     return arrayOfByte;
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
      case -1: 
      case 1: 
      case 12: 
/* 314 */         return getString(paramInt);
      
      case -2: 
/* 317 */         return getRAW(paramInt);
      
      case -4: 
      case -3: 
/* 322 */         return getBytes(paramInt);
      }
      
      
/* 326 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 327 */       localSQLException.fillInStackTrace();
/* 328 */       throw localSQLException;
    }
    
/* 334 */     return localObject;
  }
  
/* 341 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4COutRawAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */