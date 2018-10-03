package oracle.jdbc.driver;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.CLOB;
class OracleClobReader
  extends Reader
{
  CLOB clob;
  DBConversion dbConversion;
  long lobOffset;
  long markedChar;
  char[] resizableBuffer;
  int initialBufferSize;
  int currentBufferSize;
  int pos;
  int count;
/*  39 */   long maxPosition = Long.MAX_VALUE;
  
  boolean isClosed;
  
  boolean endOfStream;
  
  public OracleClobReader(CLOB paramCLOB)
    throws SQLException
  {
/*  51 */     this(paramCLOB, ((PhysicalConnection)paramCLOB.getInternalConnection()).getDefaultStreamChunkSize() / 3);
  }
  
  public OracleClobReader(CLOB paramCLOB, int paramInt)
    throws SQLException
  {
/*  66 */     this(paramCLOB, paramInt, 1L);
  }
  
  public OracleClobReader(CLOB paramCLOB, int paramInt, long paramLong)
    throws SQLException
  {
/*  83 */     if ((paramCLOB == null) || (paramInt <= 0) || (paramCLOB.getInternalConnection() == null) || (paramLong < 1L))
    {
/*  86 */       throw new IllegalArgumentException();
    }
    
/*  89 */     this.dbConversion = ((PhysicalConnection)paramCLOB.getInternalConnection()).conversion;
    
/*  92 */     this.clob = paramCLOB;
/*  93 */     this.lobOffset = paramLong;
/*  94 */     this.markedChar = -1L;
    
/*  96 */     this.resizableBuffer = null;
/*  97 */     this.initialBufferSize = paramInt;
/*  98 */     this.currentBufferSize = 0;
/*  99 */     this.pos = (this.count = 0);
    
/* 101 */     this.isClosed = false;
  }
  
  public OracleClobReader(CLOB paramCLOB, int paramInt, long paramLong1, long paramLong2)
    throws SQLException
  {
/* 115 */     this(paramCLOB, paramInt, paramLong1);
  }
  
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
/* 137 */     ensureOpen();
    
/* 139 */     int i = paramInt1;
/* 140 */     int j = i + Math.min(paramInt2, paramArrayOfChar.length - paramInt1);
    
/* 142 */     if (!needChars(j - i)) {
/* 143 */       return -1;
    }
    
/* 146 */     i += writeChars(paramArrayOfChar, i, j - i);
    
/* 148 */     while ((i < j) && (needChars(j - i)))
    {
/* 150 */       i += writeChars(paramArrayOfChar, i, j - i);
    }
    
/* 153 */     return i - paramInt1;
  }
  
  protected boolean needChars(int paramInt)
    throws IOException
  {
/* 166 */     ensureOpen();
    
/* 168 */     if (this.pos >= this.count)
    {
/* 170 */       if (!this.endOfStream)
      {
        try
        {
/* 174 */           if (paramInt > this.currentBufferSize)
          {
/* 176 */             this.currentBufferSize = Math.max(paramInt, this.initialBufferSize);
/* 177 */             PhysicalConnection localPhysicalConnection = (PhysicalConnection)this.clob.getInternalConnection();
            
/* 179 */             synchronized (localPhysicalConnection) {
/* 180 */               this.resizableBuffer = localPhysicalConnection.getCharBuffer(this.currentBufferSize);
            }
          }
          
/* 184 */           int i = this.currentBufferSize;
/* 185 */           if (this.maxPosition - this.lobOffset < this.currentBufferSize) { i = (int)(this.maxPosition - this.lobOffset);
          }
/* 187 */           this.count = this.clob.getChars(this.lobOffset, i, this.resizableBuffer);
          
/* 190 */           if (this.count < this.currentBufferSize) {
/* 191 */             this.endOfStream = true;
          }
/* 193 */           if (this.count > 0)
          {
/* 195 */             this.pos = 0;
/* 196 */             this.lobOffset += this.count;
/* 197 */             if (this.lobOffset >= this.maxPosition) { this.endOfStream = true;
            }
/* 199 */             return true;
          }
          
        }
        catch (SQLException localSQLException)
        {
/* 205 */           ??? = DatabaseError.createIOException(localSQLException);
/* 206 */           ((IOException)???).fillInStackTrace();
/* 207 */           throw ((Throwable)???);
        }
      }
      
/* 212 */       return false;
    }
    
/* 215 */     return true;
  }
  
  protected int writeChars(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
/* 230 */     int i = Math.min(paramInt2, this.count - this.pos);
    
/* 232 */     System.arraycopy(this.resizableBuffer, this.pos, paramArrayOfChar, paramInt1, i);
    
/* 234 */     this.pos += i;
    
/* 236 */     return i;
  }
  
  public boolean ready()
    throws IOException
  {
/* 253 */     ensureOpen();
    
/* 255 */     return this.pos < this.count;
  }
  
  public void close()
    throws IOException
  {
/* 269 */     if (this.isClosed) {
/* 270 */       return;
    }
    try
    {
/* 274 */       this.isClosed = true;
      
/* 276 */       PhysicalConnection localPhysicalConnection = (PhysicalConnection)this.clob.getInternalConnection();
/* 277 */       synchronized (localPhysicalConnection)
      {
/* 279 */         if (this.resizableBuffer != null)
        {
/* 281 */           localPhysicalConnection.cacheBuffer(this.resizableBuffer);
/* 282 */           this.resizableBuffer = null;
        }
      }
    }
    catch (SQLException localSQLException)
    {
/* 288 */       ??? = DatabaseError.createIOException(localSQLException);
/* 289 */       ((IOException)???).fillInStackTrace();
/* 290 */       throw ((Throwable)???);
    }
  }
  
  void ensureOpen()
    throws IOException
  {
    try
    {
/* 308 */       if (this.isClosed)
      {
/* 310 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 57, null);
/* 311 */         localSQLException1.fillInStackTrace();
/* 312 */         throw localSQLException1;
      }
      
    }
    catch (SQLException localSQLException2)
    {
/* 318 */       IOException localIOException = DatabaseError.createIOException(localSQLException2);
/* 319 */       localIOException.fillInStackTrace();
/* 320 */       throw localIOException;
    }
  }
  
  public boolean markSupported()
  {
/* 333 */     return true;
  }
  
  public void mark(int paramInt)
    throws IOException
  {
/* 354 */     if (paramInt < 0) {
/* 355 */       throw new IllegalArgumentException(DatabaseError.findMessage(195, null));
    }
/* 357 */     this.markedChar = (this.lobOffset - this.count + this.pos);
  }
  
  public void reset()
    throws IOException
  {
/* 378 */     ensureOpen();
    
/* 380 */     if (this.markedChar < 0L) {
/* 381 */       throw new IOException(DatabaseError.findMessage(195, null));
    }
/* 383 */     this.lobOffset = this.markedChar;
/* 384 */     this.pos = this.count;
/* 385 */     this.endOfStream = false;
  }
  
  public long skip(long paramLong)
    throws IOException
  {
/* 403 */     ensureOpen();
    
/* 405 */     long l1 = 0L;
    
/* 407 */     if (this.count - this.pos >= paramLong)
    {
/* 409 */       this.pos = ((int)(this.pos + paramLong));
/* 410 */       l1 += paramLong;
    }
    else
    {
/* 414 */       l1 += this.count - this.pos;
/* 415 */       this.pos = this.count;
      
      try
      {
/* 419 */         long l2 = this.clob.length() - this.lobOffset + 1L;
        
/* 421 */         if (l2 >= paramLong - l1)
        {
/* 423 */           this.lobOffset += paramLong - l1;
/* 424 */           l1 += paramLong - l1;
        }
        else
        {
/* 428 */           this.lobOffset += l2;
/* 429 */           l1 += l2;
        }
        
      }
      catch (SQLException localSQLException)
      {
/* 435 */         IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 436 */         localIOException.fillInStackTrace();
/* 437 */         throw localIOException;
      }
    }
    
/* 442 */     return l1;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
    try
    {
/* 460 */       return this.clob.getInternalConnection();
    }
    catch (Exception localException) {}
    
/* 464 */     return null;
  }
  
/* 471 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleClobReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */