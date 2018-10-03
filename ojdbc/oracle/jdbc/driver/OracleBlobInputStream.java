package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.BFILE;
import oracle.sql.BLOB;
import oracle.sql.Datum;
class OracleBlobInputStream
  extends OracleBufferedStream
{
  long lobOffset;
  Datum lob;
  long markedByte;
/*  33 */   boolean endOfStream = false;
/*  34 */   long maxPosition = Long.MAX_VALUE;
  
  public OracleBlobInputStream(BLOB paramBLOB)
    throws SQLException
  {
/*  43 */     this(paramBLOB, ((PhysicalConnection)paramBLOB.getJavaSqlConnection()).getDefaultStreamChunkSize(), 1L);
  }
  
  public OracleBlobInputStream(BLOB paramBLOB, int paramInt)
    throws SQLException
  {
/*  59 */     this(paramBLOB, paramInt, 1L);
  }
  
  public OracleBlobInputStream(BLOB paramBLOB, int paramInt, long paramLong)
    throws SQLException
  {
/*  75 */     super(paramInt);
    
/*  78 */     if ((paramBLOB == null) || (paramInt <= 0) || (paramLong < 1L))
    {
/*  80 */       throw new IllegalArgumentException("Illegal Arguments");
    }
    
/*  83 */     this.lob = paramBLOB;
/*  84 */     this.markedByte = -1L;
/*  85 */     this.lobOffset = paramLong;
  }
  
  public OracleBlobInputStream(BLOB paramBLOB, int paramInt, long paramLong1, long paramLong2)
    throws SQLException
  {
/* 100 */     this(paramBLOB, paramInt, paramLong1);
/* 101 */     this.maxPosition = (paramLong1 + paramLong2);
  }
  
  public OracleBlobInputStream(BFILE paramBFILE)
    throws SQLException
  {
/* 111 */     this(paramBFILE, ((PhysicalConnection)paramBFILE.getJavaSqlConnection()).getDefaultStreamChunkSize(), 1L);
  }
  
  public OracleBlobInputStream(BFILE paramBFILE, int paramInt)
    throws SQLException
  {
/* 126 */     this(paramBFILE, paramInt, 1L);
  }
  
  public OracleBlobInputStream(BFILE paramBFILE, int paramInt, long paramLong)
    throws SQLException
  {
/* 141 */     super(paramInt);
    
/* 144 */     if ((paramBFILE == null) || (paramInt <= 0) || (paramLong < 1L))
    {
/* 146 */       throw new IllegalArgumentException("Illegal Arguments");
    }
    
/* 149 */     this.lob = paramBFILE;
/* 150 */     this.markedByte = -1L;
/* 151 */     this.lobOffset = paramLong;
  }
  
  public boolean needBytes(int paramInt)
    throws IOException
  {
/* 162 */     ensureOpen();
    
/* 164 */     if (this.pos >= this.count)
    {
/* 166 */       if (!this.endOfStream)
      {
/* 168 */         if (paramInt > this.currentBufferSize)
        {
/* 170 */           this.currentBufferSize = Math.max(paramInt, this.initialBufferSize);
/* 171 */           this.resizableBuffer = new byte[this.currentBufferSize];
        }
        try
        {
          int i;
/* 176 */           if (this.currentBufferSize < this.maxPosition - this.lobOffset) i = this.currentBufferSize; else {
/* 177 */             i = (int)(this.maxPosition - this.lobOffset);
          }
          
/* 180 */           if ((this.lob instanceof BLOB)) {
/* 181 */             this.count = ((BLOB)this.lob).getBytes(this.lobOffset, i, this.resizableBuffer);
          } else {
/* 183 */             this.count = ((BFILE)this.lob).getBytes(this.lobOffset, i, this.resizableBuffer);
          }
          
/* 186 */           if (this.count < this.currentBufferSize) {
/* 187 */             this.endOfStream = true;
          }
/* 189 */           if (this.count > 0)
          {
/* 193 */             this.pos = 0;
/* 194 */             this.lobOffset += this.count;
/* 195 */             if (this.lobOffset > this.maxPosition) { this.endOfStream = true;
            }
/* 197 */             return true;
          }
          
        }
        catch (SQLException localSQLException)
        {
/* 203 */           IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 204 */           localIOException.fillInStackTrace();
/* 205 */           throw localIOException;
        }
      }
      
/* 210 */       return false;
    }
    
/* 213 */     return true;
  }
  
  void ensureOpen()
    throws IOException
  {
    try
    {
/* 228 */       if (this.closed)
      {
/* 230 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 57, null);
/* 231 */         localSQLException1.fillInStackTrace();
/* 232 */         throw localSQLException1;
      }
      
    }
    catch (SQLException localSQLException2)
    {
/* 238 */       IOException localIOException = DatabaseError.createIOException(localSQLException2);
/* 239 */       localIOException.fillInStackTrace();
/* 240 */       throw localIOException;
    }
  }
  
  public boolean markSupported()
  {
/* 256 */     return true;
  }
  
  public void mark(int paramInt)
  {
/* 274 */     if (paramInt < 0) {
/* 275 */       throw new IllegalArgumentException("Read-ahead limit < 0");
    }
/* 277 */     this.markedByte = (this.lobOffset - this.count + this.pos);
  }
  
  public void markInternal(int paramInt) {}
  
  public void reset()
    throws IOException
  {
/* 295 */     ensureOpen();
    
/* 297 */     if (this.markedByte < 0L) {
/* 298 */       throw new IOException("Mark invalid or stream not marked.");
    }
/* 300 */     this.lobOffset = this.markedByte;
/* 301 */     this.pos = this.count;
/* 302 */     this.endOfStream = false;
  }
  
  public long skip(long paramLong)
    throws IOException
  {
/* 321 */     ensureOpen();
    
/* 323 */     long l1 = 0L;
    
/* 325 */     if (this.count - this.pos >= paramLong)
    {
/* 327 */       this.pos = ((int)(this.pos + paramLong));
/* 328 */       l1 += paramLong;
    }
    else
    {
/* 332 */       l1 += this.count - this.pos;
/* 333 */       this.pos = this.count;
      
      try
      {
/* 337 */         long l2 = 0L;
        
/* 339 */         if ((this.lob instanceof BLOB)) {
/* 340 */           l2 = ((BLOB)this.lob).length() - this.lobOffset + 1L;
        } else {
/* 342 */           l2 = ((BFILE)this.lob).length() - this.lobOffset + 1L;
        }
/* 344 */         if (l2 >= paramLong - l1)
        {
/* 346 */           this.lobOffset += paramLong - l1;
/* 347 */           l1 += paramLong - l1;
        }
        else
        {
/* 351 */           this.lobOffset += l2;
/* 352 */           l1 += l2;
        }
        
      }
      catch (SQLException localSQLException)
      {
/* 358 */         IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 359 */         localIOException.fillInStackTrace();
/* 360 */         throw localIOException;
      }
    }
    
/* 365 */     return l1;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 381 */     OracleConnection localOracleConnection = null;
    
/* 383 */     if (this.lob != null)
    {
      try
      {
/* 387 */         if ((this.lob instanceof BLOB)) {
/* 388 */           localOracleConnection = ((BLOB)this.lob).getInternalConnection();
        } else {
/* 390 */           localOracleConnection = ((BFILE)this.lob).getInternalConnection();
        }
      }
      catch (Exception localException) {
/* 394 */         localOracleConnection = null;
      }
    }
    
/* 398 */     return localOracleConnection;
  }
  
/* 403 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleBlobInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */