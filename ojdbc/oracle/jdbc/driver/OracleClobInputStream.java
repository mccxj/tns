package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.CLOB;
class OracleClobInputStream
  extends OracleBufferedStream
{
  protected long lobOffset;
  protected CLOB clob;
  protected long markedByte;
  protected boolean endOfStream;
  protected char[] charBuf;
  
  public OracleClobInputStream(CLOB paramCLOB)
    throws SQLException
  {
/*  37 */     this(paramCLOB, ((PhysicalConnection)paramCLOB.getJavaSqlConnection()).getDefaultStreamChunkSize(), 1L);
  }
  
  public OracleClobInputStream(CLOB paramCLOB, int paramInt)
    throws SQLException
  {
/*  52 */     this(paramCLOB, paramInt, 1L);
  }
  
  public OracleClobInputStream(CLOB paramCLOB, int paramInt, long paramLong)
    throws SQLException
  {
/*  66 */     super(paramInt);
    
/*  69 */     if ((paramCLOB == null) || (paramInt <= 0) || (paramLong < 1L))
    {
/*  71 */       throw new IllegalArgumentException();
    }
    
/*  74 */     this.lobOffset = paramLong;
/*  75 */     this.clob = paramCLOB;
/*  76 */     this.markedByte = -1L;
/*  77 */     this.endOfStream = false;
  }
  
  public boolean needBytes(int paramInt)
    throws IOException
  {
/*  90 */     ensureOpen();
    
/*  92 */     if (this.pos >= this.count)
    {
/*  94 */       if (!this.endOfStream)
      {
        try
        {
/*  98 */           if (paramInt > this.currentBufferSize)
          {
/* 100 */             this.currentBufferSize = Math.max(paramInt, this.initialBufferSize);
/* 101 */             PhysicalConnection localPhysicalConnection = (PhysicalConnection)this.clob.getInternalConnection();
            
/* 103 */             synchronized (localPhysicalConnection) {
/* 104 */               this.resizableBuffer = localPhysicalConnection.getByteBuffer(this.currentBufferSize);
/* 105 */               this.charBuf = localPhysicalConnection.getCharBuffer(this.currentBufferSize);
            }
          }
/* 108 */           this.count = this.clob.getChars(this.lobOffset, this.currentBufferSize, this.charBuf);
          
/* 111 */           for (int i = 0; i < this.count; i++) {
/* 112 */             this.resizableBuffer[i] = ((byte)this.charBuf[i]);
          }
/* 114 */           if (this.count < this.currentBufferSize) {
/* 115 */             this.endOfStream = true;
          }
/* 117 */           if (this.count > 0)
          {
/* 119 */             this.pos = 0;
/* 120 */             this.lobOffset += this.count;
            
/* 122 */             return true;
          }
          
        }
        catch (SQLException localSQLException)
        {
/* 128 */           ??? = DatabaseError.createIOException(localSQLException);
/* 129 */           ((IOException)???).fillInStackTrace();
/* 130 */           throw ((Throwable)???);
        }
      }
      
/* 135 */       return false;
    }
    
/* 138 */     return true;
  }
  
  protected void ensureOpen()
    throws IOException
  {
    try
    {
/* 153 */       if (this.closed)
      {
/* 155 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 57, null);
/* 156 */         localSQLException1.fillInStackTrace();
/* 157 */         throw localSQLException1;
      }
      
    }
    catch (SQLException localSQLException2)
    {
/* 163 */       IOException localIOException = DatabaseError.createIOException(localSQLException2);
/* 164 */       localIOException.fillInStackTrace();
/* 165 */       throw localIOException;
    }
  }
  
  public boolean markSupported()
  {
/* 181 */     return true;
  }
  
  public void mark(int paramInt)
  {
/* 199 */     if (paramInt < 0) {
/* 200 */       throw new IllegalArgumentException(DatabaseError.findMessage(196, null));
    }
/* 202 */     this.markedByte = (this.lobOffset - this.count + this.pos);
  }
  
  public void markInternal(int paramInt) {}
  
  public void reset()
    throws IOException
  {
/* 218 */     ensureOpen();
    
/* 220 */     if (this.markedByte < 0L) {
/* 221 */       throw new IOException(DatabaseError.findMessage(195, null));
    }
/* 223 */     this.lobOffset = this.markedByte;
/* 224 */     this.pos = this.count;
/* 225 */     this.endOfStream = false;
  }
  
  public long skip(long paramLong)
    throws IOException
  {
/* 244 */     ensureOpen();
    
/* 246 */     long l1 = 0L;
    
/* 248 */     if (this.count - this.pos >= paramLong)
    {
/* 250 */       this.pos = ((int)(this.pos + paramLong));
/* 251 */       l1 += paramLong;
    }
    else
    {
/* 255 */       l1 += this.count - this.pos;
/* 256 */       this.pos = this.count;
      
      try
      {
/* 260 */         long l2 = 0L;
        
/* 262 */         l2 = this.clob.length() - this.lobOffset + 1L;
        
/* 264 */         if (l2 >= paramLong - l1)
        {
/* 266 */           this.lobOffset += paramLong - l1;
/* 267 */           l1 += paramLong - l1;
        }
        else
        {
/* 271 */           this.lobOffset += l2;
/* 272 */           l1 += l2;
        }
        
      }
      catch (SQLException localSQLException)
      {
/* 278 */         IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 279 */         localIOException.fillInStackTrace();
/* 280 */         throw localIOException;
      }
    }
    
/* 285 */     return l1;
  }
  
  public void close()
    throws IOException
  {
/* 293 */     if (this.closed) {
/* 294 */       return;
    }
    try {
/* 297 */       super.close();
      PhysicalConnection localPhysicalConnection1;
      PhysicalConnection localPhysicalConnection2;
      return;
    } finally {
/* 302 */       try { localPhysicalConnection2 = (PhysicalConnection)this.clob.getInternalConnection();
        
/* 304 */         synchronized (localPhysicalConnection2)
        {
/* 306 */           if (this.charBuf != null)
          {
/* 308 */             localPhysicalConnection2.cacheBuffer(this.charBuf);
/* 309 */             this.charBuf = null;
          }
/* 311 */           if (this.resizableBuffer != null)
          {
/* 313 */             localPhysicalConnection2.cacheBuffer(this.resizableBuffer);
/* 314 */             this.resizableBuffer = null;
          }
/* 316 */           this.currentBufferSize = 0;
        }
      }
      catch (SQLException localSQLException2)
      {
/* 321 */         ??? = DatabaseError.createIOException(localSQLException2);
/* 322 */         ((IOException)???).fillInStackTrace();
/* 323 */         throw ((Throwable)???);
      }
    }
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
    try
    {
/* 346 */       return this.clob.getInternalConnection();
    }
    catch (Exception localException) {}
    
/* 350 */     return null;
  }
  
/* 357 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleClobInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */