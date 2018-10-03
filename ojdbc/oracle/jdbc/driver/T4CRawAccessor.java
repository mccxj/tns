package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class T4CRawAccessor
  extends RawAccessor
{
  T4CMAREngine mare;
/*  38 */   boolean underlyingLongRaw = false;
  
  T4CRawAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  44 */     super(paramOracleStatement, paramInt1, paramShort, paramInt2, paramBoolean);
    
/*  46 */     this.mare = paramT4CMAREngine;
  }
  
  T4CRawAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, int paramInt7, int paramInt8, T4CMAREngine paramT4CMAREngine)
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
/*  79 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/*  86 */       this.mare.unmarshalUB2();
/*  87 */       this.mare.unmarshalUB2();
    }
/*  89 */     else if (this.statement.connection.versionNumber < 9200)
    {
/*  93 */       this.mare.unmarshalSB2();
      
/*  95 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/*  96 */         this.mare.unmarshalSB2();
      }
/*  98 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/* 100 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
/* 108 */   final int[] meta = new int[1];
/* 109 */   final int[] escapeSequenceArr = new int[1];
/* 110 */   final boolean[] readHeaderArr = new boolean[1];
/* 111 */   final boolean[] readAsNonStreamArr = new boolean[1];
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/* 126 */     if (this.isUseLess)
    {
/* 128 */       this.lastRowProcessed += 1;
      
/* 130 */       return false;
    }
    
/* 134 */     int i = this.indicatorIndex + this.lastRowProcessed;
/* 135 */     int j = this.lengthIndex + this.lastRowProcessed;
/* 136 */     int k = this.columnIndex + this.lastRowProcessed * this.byteLength;
    
/* 138 */     if (!this.underlyingLongRaw)
    {
/* 143 */       if (this.rowSpaceIndicator == null)
      {
/* 147 */         byte[] arrayOfByte1 = new byte['㺀'];
        
/* 149 */         this.mare.unmarshalCLR(arrayOfByte1, 0, this.meta);
/* 150 */         processIndicator(this.meta[0]);
        
/* 152 */         this.lastRowProcessed += 1;
        
/* 154 */         return false;
      }
      
/* 159 */       if (this.isNullByDescribe)
      {
/* 161 */         this.rowSpaceIndicator[i] = -1;
/* 162 */         this.rowSpaceIndicator[j] = 0;
/* 163 */         this.lastRowProcessed += 1;
        
/* 165 */         if (this.statement.connection.versionNumber < 9200) {
/* 166 */           processIndicator(0);
        }
/* 168 */         return false;
      }
      
/* 173 */       this.mare.unmarshalCLR(this.rowSpaceByte, k + 2, this.meta, this.byteLength - 2);
    }
    else
    {
/* 180 */       this.escapeSequenceArr[0] = this.mare.unmarshalUB1();
      
      int m;
/* 183 */       if (this.mare.escapeSequenceNull(this.escapeSequenceArr[0]))
      {
/* 187 */         this.meta[0] = 0;
        
/* 189 */         this.mare.processIndicator(false, 0);
        
/* 191 */         m = (int)this.mare.unmarshalUB4();
      }
      else
      {
/* 195 */         m = 0;
/* 196 */         int n = 0;
/* 197 */         byte[] arrayOfByte2 = this.rowSpaceByte;
/* 198 */         int i1 = k + 2;
        
/* 200 */         this.readHeaderArr[0] = true;
/* 201 */         this.readAsNonStreamArr[0] = false;
        
/* 203 */         while (m != -1)
        {
/* 205 */           if ((arrayOfByte2 == this.rowSpaceByte) && (n + 255 > this.byteLength - 2))
          {
/* 208 */             arrayOfByte2 = new byte['ÿ'];
          }
/* 210 */           if (arrayOfByte2 != this.rowSpaceByte) {
/* 211 */             i1 = 0;
          }
/* 213 */           m = T4CLongRawAccessor.readStreamFromWire(arrayOfByte2, i1, 255, this.escapeSequenceArr, this.readHeaderArr, this.readAsNonStreamArr, this.mare, ((T4CConnection)this.statement.connection).oer);
          
/* 217 */           if ((this.statement.connection.calculateChecksum) && (m != -1))
          {
/* 220 */             long l = CRC64.updateChecksum(this.statement.checkSum, arrayOfByte2, i1, m);
            
/* 225 */             this.statement.checkSum = l;
          }
          
/* 229 */           if (m != -1)
          {
/* 231 */             if (arrayOfByte2 == this.rowSpaceByte)
            {
/* 233 */               n += m;
/* 234 */               i1 += m;
            }
/* 236 */             else if (this.byteLength - 2 - n > 0)
            {
/* 240 */               int i2 = this.byteLength - 2 - n;
              
/* 242 */               System.arraycopy(arrayOfByte2, 0, this.rowSpaceByte, k, i2);
              
/* 245 */               n += i2;
            }
          }
        }
        
/* 250 */         if (arrayOfByte2 != this.rowSpaceByte) {
/* 251 */           arrayOfByte2 = null;
        }
/* 253 */         this.meta[0] = n;
      }
    }
    
/* 258 */     this.rowSpaceByte[k] = ((byte)((this.meta[0] & 0xFF00) >> 8));
/* 259 */     this.rowSpaceByte[(k + 1)] = ((byte)(this.meta[0] & 0xFF));
    
/* 265 */     if (!this.underlyingLongRaw) {
/* 266 */       processIndicator(this.meta[0]);
    }
/* 268 */     if (this.meta[0] == 0)
    {
/* 272 */       this.rowSpaceIndicator[i] = -1;
/* 273 */       this.rowSpaceIndicator[j] = 0;
    }
    else
    {
/* 277 */       this.rowSpaceIndicator[j] = ((short)this.meta[0]);
      
/* 282 */       this.rowSpaceIndicator[i] = 0;
    }
    
/* 285 */     this.lastRowProcessed += 1;
    
/* 287 */     return false;
  }
  
  void copyRow()
    throws SQLException, IOException
  {
    int i;
    
/* 297 */     if (this.lastRowProcessed == 0) {
/* 298 */       i = this.statement.rowPrefetchInLastFetch - 1;
    } else {
/* 300 */       i = this.lastRowProcessed - 1;
    }
    
/* 303 */     int j = this.columnIndex + this.lastRowProcessed * this.byteLength;
/* 304 */     int k = this.columnIndex + i * this.byteLength;
/* 305 */     int m = this.indicatorIndex + this.lastRowProcessed;
/* 306 */     int n = this.indicatorIndex + i;
/* 307 */     int i1 = this.lengthIndex + this.lastRowProcessed;
/* 308 */     int i2 = this.lengthIndex + i;
/* 309 */     int i3 = this.rowSpaceIndicator[i2];
/* 310 */     int i4 = this.metaDataIndex + this.lastRowProcessed * 1;
    
/* 312 */     int i5 = this.metaDataIndex + i * 1;
    
/* 316 */     this.rowSpaceIndicator[i1] = ((short)i3);
/* 317 */     this.rowSpaceIndicator[m] = this.rowSpaceIndicator[n];
    
/* 320 */     if (!this.isNullByDescribe)
    {
/* 322 */       System.arraycopy(this.rowSpaceByte, k, this.rowSpaceByte, j, i3 + 2);
    }
    
/* 327 */     System.arraycopy(this.rowSpaceMetaData, i5, this.rowSpaceMetaData, i4, 1);
    
/* 330 */     this.lastRowProcessed += 1;
  }
  
  void saveDataFromOldDefineBuffers(byte[] paramArrayOfByte, char[] paramArrayOfChar, short[] paramArrayOfShort, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 342 */     int i = this.columnIndex + (paramInt2 - 1) * this.byteLength;
    
/* 344 */     int j = this.columnIndexLastRow + (paramInt1 - 1) * this.byteLength;
    
/* 346 */     int k = this.indicatorIndex + paramInt2 - 1;
/* 347 */     int m = this.indicatorIndexLastRow + paramInt1 - 1;
/* 348 */     int n = this.lengthIndex + paramInt2 - 1;
/* 349 */     int i1 = this.lengthIndexLastRow + paramInt1 - 1;
/* 350 */     int i2 = paramArrayOfShort[i1];
    
/* 352 */     this.rowSpaceIndicator[n] = ((short)i2);
/* 353 */     this.rowSpaceIndicator[k] = paramArrayOfShort[m];
    
/* 356 */     if (i2 != 0)
    {
/* 358 */       System.arraycopy(paramArrayOfByte, j, this.rowSpaceByte, i, i2 + 2);
    }
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 371 */     String str = super.getString(paramInt);
    
/* 375 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize * 2))
    {
/* 377 */       str = str.substring(0, this.definedColumnSize * 2);
    }
/* 379 */     return str;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 386 */     if (this.definedColumnType == 0) {
/* 387 */       return super.getObject(paramInt);
    }
    
/* 390 */     Object localObject = null;
    SQLException localSQLException;
/* 392 */     if (this.rowSpaceIndicator == null)
    {
/* 394 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 395 */       localSQLException.fillInStackTrace();
/* 396 */       throw localSQLException;
    }
    
/* 399 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 401 */       switch (this.definedColumnType)
      {
      case -16: 
      case -15: 
      case -9: 
      case -1: 
      case 1: 
      case 12: 
/* 418 */         return getString(paramInt);
      
      case -2: 
/* 421 */         return getRAW(paramInt);
      }
      
      
/* 425 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 426 */       localSQLException.fillInStackTrace();
/* 427 */       throw localSQLException;
    }
    
/* 433 */     return localObject;
  }
  
  byte[] getBytes(int paramInt)
    throws SQLException
  {
/* 441 */     Object localObject = super.getBytes(paramInt);
    
/* 443 */     if ((localObject != null) && (this.definedColumnSize > 0) && (this.definedColumnSize < localObject.length))
    {
/* 447 */       byte[] arrayOfByte = new byte[this.definedColumnSize];
      
/* 449 */       System.arraycopy(localObject, 0, arrayOfByte, 0, this.definedColumnSize);
      
/* 451 */       localObject = arrayOfByte;
    }
    
/* 454 */     return (byte[])localObject;
  }
  
/* 459 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CRawAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */