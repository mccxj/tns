package oracle.jdbc.driver;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
class OracleConversionInputStream
  extends OracleBufferedStream
{
  static final int CHUNK_SIZE = 4096;
  DBConversion converter;
  int conversion;
  InputStream istream;
  Reader reader;
  byte[] convbuf;
  char[] javaChars;
  int maxSize;
  int totalSize;
  int numUnconvertedBytes;
  boolean endOfStream;
  private short csform;
  int[] nbytes;
  
  public OracleConversionInputStream(DBConversion paramDBConversion, InputStream paramInputStream, int paramInt)
  {
/*  52 */     this(paramDBConversion, paramInputStream, paramInt, (short)1);
  }
  
  public OracleConversionInputStream(DBConversion paramDBConversion, InputStream paramInputStream, int paramInt, short paramShort)
  {
/*  62 */     super(4096);
    
/*  64 */     this.istream = paramInputStream;
/*  65 */     this.conversion = paramInt;
/*  66 */     this.converter = paramDBConversion;
/*  67 */     this.maxSize = 0;
/*  68 */     this.totalSize = 0;
/*  69 */     this.numUnconvertedBytes = 0;
/*  70 */     this.endOfStream = false;
/*  71 */     this.nbytes = new int[1];
/*  72 */     this.csform = paramShort;
/*  73 */     this.currentBufferSize = this.initialBufferSize;
/*  74 */     this.resizableBuffer = new byte[this.currentBufferSize];
    int i;
/*  76 */     switch (paramInt)
    {
    case 0: 
/*  81 */       this.javaChars = new char['က'];
/*  82 */       this.convbuf = new byte['က'];
      
/*  85 */       break;
    
    case 1: 
/*  89 */       this.convbuf = new byte['ࠀ'];
/*  90 */       this.javaChars = new char['ࠀ'];
      
/*  93 */       break;
    
    case 2: 
/*  97 */       this.convbuf = new byte['ࠀ'];
/*  98 */       this.javaChars = new char['က'];
      
/* 101 */       break;
    
    case 3: 
/* 105 */       this.convbuf = new byte['Ѐ'];
/* 106 */       this.javaChars = new char['ࠀ'];
      
/* 109 */       break;
    
    case 4: 
/* 113 */       i = 4096 / this.converter.getMaxCharbyteSize();
      
/* 115 */       this.convbuf = new byte[i * 2];
/* 116 */       this.javaChars = new char[i];
      
/* 119 */       break;
    
    case 5: 
/* 123 */       if (this.converter.isUcs2CharSet())
      {
/* 125 */         this.convbuf = new byte['ࠀ'];
/* 126 */         this.javaChars = new char['ࠀ'];
      }
      else
      {
/* 130 */         this.convbuf = new byte['က'];
/* 131 */         this.javaChars = new char['က'];
      }
      
/* 135 */       break;
    
    case 7: 
/* 139 */       i = 4096 / (paramShort == 2 ? this.converter.getMaxNCharbyteSize() : this.converter.getMaxCharbyteSize());
      
/* 141 */       this.javaChars = new char[i];
      
/* 144 */       break;
    
    case 6: 
    default: 
/* 148 */       this.convbuf = new byte['က'];
/* 149 */       this.javaChars = new char['က'];
    }
    
  }
  
  public OracleConversionInputStream(DBConversion paramDBConversion, InputStream paramInputStream, int paramInt1, int paramInt2)
  {
/* 160 */     this(paramDBConversion, paramInputStream, paramInt1, (short)1);
    
/* 162 */     this.maxSize = paramInt2;
/* 163 */     this.totalSize = 0;
  }
  
  public OracleConversionInputStream(DBConversion paramDBConversion, Reader paramReader, int paramInt1, int paramInt2, short paramShort)
  {
/* 172 */     this(paramDBConversion, (InputStream)null, paramInt1, paramShort);
    
/* 174 */     this.reader = paramReader;
/* 175 */     this.maxSize = paramInt2;
/* 176 */     this.totalSize = 0;
  }
  
  public void setFormOfUse(short paramShort)
  {
/* 184 */     this.csform = paramShort;
  }
  
  public boolean needBytes(int paramInt)
    throws IOException
  {
/* 191 */     return needBytes();
  }
  
  public boolean needBytes()
    throws IOException
  {
/* 201 */     if (this.closed) {
/* 202 */       return false;
    }
    
/* 206 */     if (this.pos < this.count) {
/* 207 */       return true;
    }
/* 209 */     if (this.istream != null)
    {
/* 211 */       return needBytesFromStream();
    }
/* 213 */     if (this.reader != null)
    {
/* 215 */       return needBytesFromReader();
    }
    
/* 218 */     return false;
  }
  
  public boolean needBytesFromReader()
    throws IOException
  {
    try
    {
/* 228 */       int i = 0;
      
/* 230 */       if (this.maxSize == 0)
      {
/* 232 */         i = this.javaChars.length;
      }
      else
      {
/* 236 */         i = Math.min(this.maxSize - this.totalSize, this.javaChars.length);
      }
      
/* 239 */       if (i <= 0)
      {
/* 241 */         return false;
      }
      
/* 244 */       int j = this.reader.read(this.javaChars, 0, i);
      
/* 246 */       if (j == -1)
      {
/* 248 */         return false;
      }
      
/* 252 */       this.totalSize += j;
      
/* 256 */       switch (this.conversion)
      {
      case 7: 
/* 261 */         if (this.csform == 2) {
/* 262 */           this.count = this.converter.javaCharsToNCHARBytes(this.javaChars, j, this.resizableBuffer);
        } else {
/* 264 */           this.count = this.converter.javaCharsToCHARBytes(this.javaChars, j, this.resizableBuffer);
        }
        
/* 267 */         break;
      
      default: 
/* 271 */         System.arraycopy(this.convbuf, 0, this.resizableBuffer, 0, j);
        
/* 273 */         this.count = j;
      
      }
      
    }
    catch (SQLException localSQLException)
    {
/* 280 */       IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 281 */       localIOException.fillInStackTrace();
/* 282 */       throw localIOException;
    }
    
/* 287 */     this.pos = 0;
    
/* 290 */     return true;
  }
  
  public boolean needBytesFromStream()
    throws IOException
  {
/* 298 */     if (!this.endOfStream)
    {
      try
      {
/* 302 */         int i = 0;
        
/* 304 */         if (this.maxSize == 0)
        {
/* 306 */           i = this.convbuf.length;
        }
        else
        {
/* 310 */           i = Math.min(this.maxSize - this.totalSize, this.convbuf.length);
        }
        
/* 313 */         int j = 0;
        SQLException localSQLException2;
/* 315 */         if (i <= 0)
        {
/* 319 */           this.endOfStream = true;
          
/* 321 */           this.istream.close();
          
/* 323 */           if (this.numUnconvertedBytes != 0)
          {
/* 325 */             localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 55);
/* 326 */             localSQLException2.fillInStackTrace();
/* 327 */             throw localSQLException2;
          }
          
        }
        else
        {
/* 334 */           j = this.istream.read(this.convbuf, this.numUnconvertedBytes, i - this.numUnconvertedBytes);
        }
        
/* 340 */         if (j == -1)
        {
/* 344 */           this.endOfStream = true;
          
/* 346 */           this.istream.close();
          
/* 348 */           if (this.numUnconvertedBytes != 0)
          {
/* 350 */             localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 55);
/* 351 */             localSQLException2.fillInStackTrace();
/* 352 */             throw localSQLException2;
          }
        }
        else
        {
/* 357 */           j += this.numUnconvertedBytes;
/* 358 */           this.totalSize += j;
        }
        
/* 361 */         if (j <= 0)
        {
/* 363 */           return false;
        }
        
        int k;
        
        int m;
/* 369 */         switch (this.conversion)
        {
        case 0: 
/* 376 */           this.nbytes[0] = j;
          
/* 378 */           k = this.converter.CHARBytesToJavaChars(this.convbuf, 0, this.javaChars, 0, this.nbytes, this.javaChars.length);
          
/* 383 */           this.numUnconvertedBytes = this.nbytes[0];
          
/* 385 */           for (m = 0; m < this.numUnconvertedBytes; m++) {
/* 386 */             this.convbuf[m] = this.convbuf[(j - this.numUnconvertedBytes)];
          }
          
/* 389 */           this.count = DBConversion.javaCharsToAsciiBytes(this.javaChars, k, this.resizableBuffer);
          
/* 392 */           break;
        
        case 1: 
/* 398 */           this.nbytes[0] = j;
          
/* 400 */           k = this.converter.CHARBytesToJavaChars(this.convbuf, 0, this.javaChars, 0, this.nbytes, this.javaChars.length);
          
/* 405 */           this.numUnconvertedBytes = this.nbytes[0];
          
/* 407 */           for (m = 0; m < this.numUnconvertedBytes; m++) {
/* 408 */             this.convbuf[m] = this.convbuf[(j - this.numUnconvertedBytes)];
          }
          
/* 411 */           this.count = DBConversion.javaCharsToUcs2Bytes(this.javaChars, k, this.resizableBuffer);
          
/* 414 */           break;
        
        case 2: 
/* 420 */           k = DBConversion.RAWBytesToHexChars(this.convbuf, j, this.javaChars);
          
/* 424 */           this.count = DBConversion.javaCharsToAsciiBytes(this.javaChars, k, this.resizableBuffer);
          
/* 427 */           break;
        
        case 3: 
/* 433 */           k = DBConversion.RAWBytesToHexChars(this.convbuf, j, this.javaChars);
          
/* 437 */           this.count = DBConversion.javaCharsToUcs2Bytes(this.javaChars, k, this.resizableBuffer);
          
/* 440 */           break;
        
        case 4: 
/* 446 */           k = DBConversion.ucs2BytesToJavaChars(this.convbuf, j, this.javaChars);
          
/* 450 */           this.count = this.converter.javaCharsToCHARBytes(this.javaChars, k, this.resizableBuffer);
          
/* 453 */           break;
        
        case 12: 
/* 459 */           k = DBConversion.ucs2BytesToJavaChars(this.convbuf, j, this.javaChars);
          
/* 464 */           this.count = DBConversion.javaCharsToAsciiBytes(this.javaChars, k, this.resizableBuffer);
          
/* 469 */           break;
        
        case 5: 
/* 475 */           DBConversion.asciiBytesToJavaChars(this.convbuf, j, this.javaChars);
          
/* 478 */           this.count = this.converter.javaCharsToCHARBytes(this.javaChars, j, this.resizableBuffer);
          
/* 481 */           break;
        case 6: case 7: case 8: 
        case 9: case 10: 
        case 11: default: 
/* 485 */           System.arraycopy(this.convbuf, 0, this.resizableBuffer, 0, j);
          
/* 487 */           this.count = j;
        
        }
        
      }
      catch (SQLException localSQLException1)
      {
/* 495 */         IOException localIOException = DatabaseError.createIOException(localSQLException1);
/* 496 */         localIOException.fillInStackTrace();
/* 497 */         throw localIOException;
      }
      
/* 502 */       this.pos = 0;
      
/* 505 */       return true;
    }
    
/* 508 */     return false;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 523 */     return null;
  }
  
/* 528 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleConversionInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */