package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleStatement.SqlKind;
import oracle.net.ns.BreakNetException;
class T4CLongRawAccessor
  extends LongRawAccessor
{
  T4CMAREngine mare;
/*  35 */   byte[][] data = (byte[][])null;
/*  36 */   int[] nbBytesRead = null;
/*  37 */   int[] bytesReadSoFar = null;
  
  T4CLongRawAccessor(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, short paramShort, int paramInt3, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  43 */     super(paramOracleStatement, paramInt1, paramInt2, paramShort, paramInt3);
    
/*  46 */     this.mare = paramT4CMAREngine;
    
/*  48 */     if (paramOracleStatement.connection.useFetchSizeWithLongColumn)
    {
/*  50 */       this.data = new byte[paramOracleStatement.rowPrefetch][];
      
/*  52 */       for (int i = 0; i < paramOracleStatement.rowPrefetch; i++) {
/*  53 */         this.data[i] = new byte['࿰'];
      }
/*  55 */       this.nbBytesRead = new int[paramOracleStatement.rowPrefetch];
/*  56 */       this.bytesReadSoFar = new int[paramOracleStatement.rowPrefetch];
    }
  }
  
  T4CLongRawAccessor(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, short paramShort, int paramInt8, int paramInt9, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  67 */     super(paramOracleStatement, paramInt1, paramInt2, paramBoolean, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramShort);
    
/*  70 */     this.mare = paramT4CMAREngine;
/*  71 */     this.definedColumnType = paramInt8;
/*  72 */     this.definedColumnSize = paramInt9;
    
/*  74 */     if (paramOracleStatement.connection.useFetchSizeWithLongColumn)
    {
/*  76 */       this.data = new byte[paramOracleStatement.rowPrefetch][];
      
/*  78 */       for (int i = 0; i < paramOracleStatement.rowPrefetch; i++) {
/*  79 */         this.data[i] = new byte['࿰'];
      }
/*  81 */       this.nbBytesRead = new int[paramOracleStatement.rowPrefetch];
/*  82 */       this.bytesReadSoFar = new int[paramOracleStatement.rowPrefetch];
    }
  }
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/*  92 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/*  99 */       this.mare.unmarshalUB2();
/* 100 */       this.mare.unmarshalUB2();
    }
/* 102 */     else if (this.statement.connection.versionNumber < 9200)
    {
/* 106 */       this.mare.unmarshalSB2();
      
/* 108 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/* 109 */         this.mare.unmarshalSB2();
      }
/* 111 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/* 113 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
/* 120 */   final int[] escapeSequenceArr = new int[1];
/* 121 */   final boolean[] readHeaderArr = new boolean[1];
/* 122 */   final boolean[] readAsNonStreamArr = new boolean[1];
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/* 137 */     if (this.isUseLess)
    {
/* 139 */       this.lastRowProcessed += 1;
      
/* 141 */       return false;
    }
    
/* 144 */     boolean bool = false;
/* 145 */     int i = this.indicatorIndex + this.lastRowProcessed;
    
/* 151 */     this.escapeSequenceArr[0] = this.mare.unmarshalUB1();
    
    int j;
/* 154 */     if (this.mare.escapeSequenceNull(this.escapeSequenceArr[0]))
    {
/* 158 */       this.rowSpaceIndicator[i] = -1;
      
/* 160 */       this.mare.processIndicator(false, 0);
      
/* 162 */       j = (int)this.mare.unmarshalUB4();
      
/* 164 */       bool = false;
      
/* 166 */       this.escapeSequenceArr[0] = 0;
/* 167 */       this.lastRowProcessed += 1;
    }
    else
    {
/* 173 */       this.rowSpaceIndicator[i] = 0;
/* 174 */       this.readHeaderArr[0] = true;
/* 175 */       this.readAsNonStreamArr[0] = false;
      
/* 178 */       if (this.statement.connection.useFetchSizeWithLongColumn)
      {
/* 183 */         j = 0;
        
/* 185 */         while (j != -1)
        {
/* 189 */           if (this.data[this.lastRowProcessed].length < this.nbBytesRead[this.lastRowProcessed] + 255)
          {
/* 192 */             byte[] arrayOfByte = new byte[this.data[this.lastRowProcessed].length * 4];
            
/* 194 */             System.arraycopy(this.data[this.lastRowProcessed], 0, arrayOfByte, 0, this.nbBytesRead[this.lastRowProcessed]);
            
/* 197 */             this.data[this.lastRowProcessed] = arrayOfByte;
          }
          
/* 200 */           j = readStreamFromWire(this.data[this.lastRowProcessed], this.nbBytesRead[this.lastRowProcessed], 255, this.escapeSequenceArr, this.readHeaderArr, this.readAsNonStreamArr, this.mare, ((T4CConnection)this.statement.connection).oer);
          
/* 206 */           if ((this.statement.connection.calculateChecksum) && (j != -1))
          {
/* 208 */             long l = CRC64.updateChecksum(this.statement.checkSum, this.data[this.lastRowProcessed], this.nbBytesRead[this.lastRowProcessed], j);
            
/* 212 */             this.statement.checkSum = l;
          }
          
/* 215 */           if (j != -1)
          {
/* 217 */             this.nbBytesRead[this.lastRowProcessed] += j;
          }
        }
        
/* 221 */         this.lastRowProcessed += 1;
      }
      else
      {
/* 225 */         bool = true;
      }
    }
    
/* 229 */     return bool;
  }
  
  void fetchNextColumns()
    throws SQLException
  {
/* 243 */     this.statement.continueReadRow(this.columnPosition);
  }
  
  int readStream(byte[] paramArrayOfByte, int paramInt)
    throws SQLException, IOException
  {
/* 262 */     int i = this.statement.currentRow;
    
/* 264 */     if (this.statement.connection.useFetchSizeWithLongColumn)
    {
/* 266 */       byte[] arrayOfByte = this.data[i];
/* 267 */       int k = this.nbBytesRead[i];
/* 268 */       int m = this.bytesReadSoFar[i];
      
/* 270 */       if (m == k) {
/* 271 */         return -1;
      }
      
/* 274 */       int n = 0;
      
/* 276 */       if (paramInt <= k - m) {
/* 277 */         n = paramInt;
      } else {
/* 279 */         n = k - m;
      }
/* 281 */       System.arraycopy(arrayOfByte, m, paramArrayOfByte, 0, n);
      
/* 283 */       this.bytesReadSoFar[i] += n;
      
/* 285 */       return n;
    }
    
/* 290 */     int j = readStreamFromWire(paramArrayOfByte, 0, paramInt, this.escapeSequenceArr, this.readHeaderArr, this.readAsNonStreamArr, this.mare, ((T4CConnection)this.statement.connection).oer);
    
/* 293 */     if ((this.statement.connection.calculateChecksum) && (j != -1))
    {
/* 295 */       long l = CRC64.updateChecksum(this.statement.checkSum, paramArrayOfByte, 0, j);
      
/* 300 */       this.statement.checkSum = l;
    }
    
/* 304 */     return j;
  }
  
  protected static final int readStreamFromWire(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int[] paramArrayOfInt, boolean[] paramArrayOfBoolean1, boolean[] paramArrayOfBoolean2, T4CMAREngine paramT4CMAREngine, T4CTTIoer paramT4CTTIoer)
    throws SQLException, IOException
  {
/* 332 */     int i = -1;
    
    try
    {
/* 336 */       if (paramArrayOfBoolean2[0] == 0)
      {
        SQLException localSQLException;
        
/* 340 */         if ((paramInt2 > 255) || (paramInt2 < 0))
        {
/* 347 */           localSQLException = DatabaseError.createSqlException(null, 433);
/* 348 */           localSQLException.fillInStackTrace();
/* 349 */           throw localSQLException;
        }
        
/* 353 */         if (paramArrayOfBoolean1[0] != 0)
        {
/* 358 */           if (paramArrayOfInt[0] == 254)
          {
/* 360 */             i = paramT4CMAREngine.unmarshalUB1();
          }
          else
          {
/* 364 */             if (paramArrayOfInt[0] == 0)
            {
/* 367 */               paramT4CTTIoer.connection.internalClose();
              
/* 369 */               localSQLException = DatabaseError.createSqlException(null, 401);
/* 370 */               localSQLException.fillInStackTrace();
/* 371 */               throw localSQLException;
            }
            
/* 377 */             paramArrayOfBoolean2[0] = true;
/* 378 */             i = paramArrayOfInt[0];
          }
          
/* 381 */           paramArrayOfBoolean1[0] = false;
/* 382 */           paramArrayOfInt[0] = 0;
        }
        else
        {
/* 386 */           i = paramT4CMAREngine.unmarshalUB1();
        }
        
      }
      else
      {
/* 393 */         paramArrayOfBoolean2[0] = false;
      }
      
/* 397 */       if (i > 0)
      {
/* 401 */         paramT4CMAREngine.unmarshalNBytes(paramArrayOfByte, paramInt1, i);
      }
      else
      {
/* 405 */         i = -1;
      }
      
    }
    catch (BreakNetException localBreakNetException)
    {
/* 414 */       i = paramT4CMAREngine.unmarshalUB1();
/* 415 */       if (i == 4)
      {
/* 417 */         paramT4CTTIoer.init();
/* 418 */         paramT4CTTIoer.processError();
      }
    }
    
/* 423 */     if (i == -1)
    {
/* 425 */       paramArrayOfBoolean1[0] = true;
      
/* 429 */       paramT4CMAREngine.unmarshalUB2();
/* 430 */       paramT4CMAREngine.unmarshalUB2();
    }
    
/* 433 */     return i;
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 443 */     String str = super.getString(paramInt);
    
/* 447 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize * 2))
    {
/* 449 */       str = str.substring(0, this.definedColumnSize * 2);
    }
/* 451 */     return str;
  }
  
  long updateChecksum(long paramLong, int paramInt)
    throws SQLException
  {
/* 458 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1)
    {
/* 460 */       paramLong = CRC64.updateChecksum(paramLong, NULL_DATA_BYTES, 0, NULL_DATA_BYTES.length);
    }
    
/* 466 */     return paramLong;
  }
  
/* 471 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CLongRawAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */