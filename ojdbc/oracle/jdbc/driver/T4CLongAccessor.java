package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleStatement.SqlKind;
import oracle.net.ns.BreakNetException;
class T4CLongAccessor
  extends LongAccessor
{
  T4CMAREngine mare;
  static final int t4MaxLength = Integer.MAX_VALUE;
  static final int t4PlsqlMaxLength = 32760;
/*  37 */   byte[][] data = (byte[][])null;
/*  38 */   int[] nbBytesRead = null;
/*  39 */   int[] bytesReadSoFar = null;
  
  T4CLongAccessor(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, short paramShort, int paramInt3, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  45 */     super(paramOracleStatement, paramInt1, paramInt2, paramShort, paramInt3);
    
/*  48 */     this.mare = paramT4CMAREngine;
    
/*  50 */     if (paramOracleStatement.connection.useFetchSizeWithLongColumn)
    {
/*  52 */       this.data = new byte[paramOracleStatement.rowPrefetch][];
      
/*  54 */       for (int i = 0; i < paramOracleStatement.rowPrefetch; i++) {
/*  55 */         this.data[i] = new byte['࿰'];
      }
/*  57 */       this.nbBytesRead = new int[paramOracleStatement.rowPrefetch];
/*  58 */       this.bytesReadSoFar = new int[paramOracleStatement.rowPrefetch];
    }
  }
  
  T4CLongAccessor(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, short paramShort, int paramInt8, int paramInt9, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  69 */     super(paramOracleStatement, paramInt1, paramInt2, paramBoolean, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramShort);
    
/*  72 */     this.mare = paramT4CMAREngine;
/*  73 */     this.definedColumnType = paramInt8;
/*  74 */     this.definedColumnSize = paramInt9;
    
/*  76 */     if (paramOracleStatement.connection.useFetchSizeWithLongColumn)
    {
/*  78 */       this.data = new byte[paramOracleStatement.rowPrefetch][];
      
/*  80 */       for (int i = 0; i < paramOracleStatement.rowPrefetch; i++) {
/*  81 */         this.data[i] = new byte['࿰'];
      }
/*  83 */       this.nbBytesRead = new int[paramOracleStatement.rowPrefetch];
/*  84 */       this.bytesReadSoFar = new int[paramOracleStatement.rowPrefetch];
    }
  }
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/*  94 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/* 101 */       this.mare.unmarshalUB2();
/* 102 */       this.mare.unmarshalUB2();
    }
/* 104 */     else if (this.statement.connection.versionNumber < 9200)
    {
/* 108 */       this.mare.unmarshalSB2();
      
/* 110 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/* 111 */         this.mare.unmarshalSB2();
      }
/* 113 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/* 115 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
/* 122 */   final int[] escapeSequenceArr = new int[1];
/* 123 */   final boolean[] readHeaderArr = new boolean[1];
/* 124 */   final boolean[] readAsNonStreamArr = new boolean[1];
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/* 139 */     if (this.isUseLess)
    {
/* 141 */       this.lastRowProcessed += 1;
      
/* 143 */       return false;
    }
    
/* 146 */     boolean bool = false;
/* 147 */     int i = this.indicatorIndex + this.lastRowProcessed;
    
/* 153 */     this.escapeSequenceArr[0] = this.mare.unmarshalUB1();
    
    int j;
/* 156 */     if (this.mare.escapeSequenceNull(this.escapeSequenceArr[0]))
    {
/* 160 */       this.rowSpaceIndicator[i] = -1;
      
/* 162 */       this.mare.processIndicator(false, 0);
      
/* 164 */       j = (int)this.mare.unmarshalUB4();
      
/* 166 */       bool = false;
      
/* 168 */       this.escapeSequenceArr[0] = 0;
/* 169 */       this.lastRowProcessed += 1;
    }
    else
    {
/* 175 */       this.rowSpaceIndicator[i] = 0;
/* 176 */       this.readHeaderArr[0] = true;
/* 177 */       this.readAsNonStreamArr[0] = false;
      
/* 180 */       if (this.statement.connection.useFetchSizeWithLongColumn)
      {
/* 185 */         j = 0;
        
/* 187 */         while (j != -1)
        {
/* 191 */           if (this.data[this.lastRowProcessed].length < this.nbBytesRead[this.lastRowProcessed] + 255)
          {
/* 194 */             byte[] arrayOfByte = new byte[this.data[this.lastRowProcessed].length * 4];
            
/* 196 */             System.arraycopy(this.data[this.lastRowProcessed], 0, arrayOfByte, 0, this.nbBytesRead[this.lastRowProcessed]);
            
/* 199 */             this.data[this.lastRowProcessed] = arrayOfByte;
          }
          
/* 202 */           j = readStreamFromWire(this.data[this.lastRowProcessed], this.nbBytesRead[this.lastRowProcessed], 255, this.escapeSequenceArr, this.readHeaderArr, this.readAsNonStreamArr, this.mare, ((T4CConnection)this.statement.connection).oer);
          
/* 208 */           if ((this.statement.connection.calculateChecksum) && (j != -1))
          {
/* 210 */             long l = CRC64.updateChecksum(this.statement.checkSum, this.data[this.lastRowProcessed], this.nbBytesRead[this.lastRowProcessed], j);
            
/* 214 */             this.statement.checkSum = l;
          }
          
/* 217 */           if (j != -1)
          {
/* 219 */             this.nbBytesRead[this.lastRowProcessed] += j;
          }
        }
        
/* 223 */         this.lastRowProcessed += 1;
      }
      else
      {
/* 227 */         bool = true;
      }
    }
    
/* 231 */     return bool;
  }
  
  void fetchNextColumns()
    throws SQLException
  {
/* 245 */     this.statement.continueReadRow(this.columnPosition);
  }
  
  int readStream(byte[] paramArrayOfByte, int paramInt)
    throws SQLException, IOException
  {
/* 264 */     int i = this.statement.currentRow;
    
/* 266 */     if (this.statement.connection.useFetchSizeWithLongColumn)
    {
/* 268 */       byte[] arrayOfByte = this.data[i];
/* 269 */       int k = this.nbBytesRead[i];
/* 270 */       int m = this.bytesReadSoFar[i];
      
/* 272 */       if (m == k) {
/* 273 */         return -1;
      }
      
/* 276 */       int n = 0;
      
/* 278 */       if (paramInt <= k - m) {
/* 279 */         n = paramInt;
      } else {
/* 281 */         n = k - m;
      }
/* 283 */       System.arraycopy(arrayOfByte, m, paramArrayOfByte, 0, n);
      
/* 285 */       this.bytesReadSoFar[i] += n;
      
/* 287 */       return n;
    }
    
/* 292 */     int j = readStreamFromWire(paramArrayOfByte, 0, paramInt, this.escapeSequenceArr, this.readHeaderArr, this.readAsNonStreamArr, this.mare, ((T4CConnection)this.statement.connection).oer);
    
/* 295 */     if ((this.statement.connection.calculateChecksum) && (j != -1))
    {
/* 297 */       long l = CRC64.updateChecksum(this.statement.checkSum, paramArrayOfByte, 0, j);
      
/* 302 */       this.statement.checkSum = l;
    }
    
/* 306 */     return j;
  }
  
  protected static final int readStreamFromWire(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int[] paramArrayOfInt, boolean[] paramArrayOfBoolean1, boolean[] paramArrayOfBoolean2, T4CMAREngine paramT4CMAREngine, T4CTTIoer paramT4CTTIoer)
    throws SQLException, IOException
  {
/* 334 */     int i = -1;
    
    try
    {
/* 338 */       if (paramArrayOfBoolean2[0] == 0)
      {
        SQLException localSQLException;
        
/* 342 */         if ((paramInt2 > 255) || (paramInt2 < 0))
        {
/* 349 */           localSQLException = DatabaseError.createSqlException(null, 433);
/* 350 */           localSQLException.fillInStackTrace();
/* 351 */           throw localSQLException;
        }
        
/* 355 */         if (paramArrayOfBoolean1[0] != 0)
        {
/* 360 */           if (paramArrayOfInt[0] == 254)
          {
/* 362 */             i = paramT4CMAREngine.unmarshalUB1();
          }
          else
          {
/* 366 */             if (paramArrayOfInt[0] == 0)
            {
/* 369 */               paramT4CTTIoer.connection.internalClose();
              
/* 371 */               localSQLException = DatabaseError.createSqlException(null, 401);
/* 372 */               localSQLException.fillInStackTrace();
/* 373 */               throw localSQLException;
            }
            
/* 379 */             paramArrayOfBoolean2[0] = true;
/* 380 */             i = paramArrayOfInt[0];
          }
          
/* 383 */           paramArrayOfBoolean1[0] = false;
/* 384 */           paramArrayOfInt[0] = 0;
        }
        else
        {
/* 388 */           i = paramT4CMAREngine.unmarshalUB1();
        }
        
      }
      else
      {
/* 395 */         paramArrayOfBoolean2[0] = false;
      }
      
/* 399 */       if (i > 0)
      {
/* 403 */         paramT4CMAREngine.unmarshalNBytes(paramArrayOfByte, paramInt1, i);
      }
      else
      {
/* 407 */         i = -1;
      }
      
    }
    catch (BreakNetException localBreakNetException)
    {
/* 416 */       i = paramT4CMAREngine.unmarshalUB1();
/* 417 */       if (i == 4)
      {
/* 419 */         paramT4CTTIoer.init();
/* 420 */         paramT4CTTIoer.processError();
      }
    }
    
/* 425 */     if (i == -1)
    {
/* 427 */       paramArrayOfBoolean1[0] = true;
      
/* 431 */       paramT4CMAREngine.unmarshalUB2();
/* 432 */       paramT4CMAREngine.unmarshalUB2();
    }
    
/* 435 */     return i;
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 450 */     String str = super.getString(paramInt);
    
/* 452 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
    {
/* 454 */       str = str.substring(0, this.definedColumnSize);
    }
/* 456 */     return str;
  }
  
  long updateChecksum(long paramLong, int paramInt)
    throws SQLException
  {
/* 465 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1)
    {
/* 467 */       paramLong = CRC64.updateChecksum(paramLong, NULL_DATA_BYTES, 0, NULL_DATA_BYTES.length);
    }
    
/* 473 */     return paramLong;
  }
  
/* 477 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CLongAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */