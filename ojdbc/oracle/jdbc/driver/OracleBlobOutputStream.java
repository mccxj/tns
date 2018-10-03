package oracle.jdbc.driver;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.BLOB;
class OracleBlobOutputStream
  extends OutputStream
{
  long lobOffset;
  BLOB blob;
  byte[] buf;
  int count;
  int bufSize;
  boolean isClosed;
  
  public OracleBlobOutputStream(BLOB paramBLOB, int paramInt)
    throws SQLException
  {
/*  43 */     this(paramBLOB, paramInt, 1L);
  }
  
  public OracleBlobOutputStream(BLOB paramBLOB, int paramInt, long paramLong)
    throws SQLException
  {
/*  60 */     if ((paramBLOB == null) || (paramInt <= 0) || (paramLong < 1L))
    {
/*  62 */       throw new IllegalArgumentException("Illegal Arguments");
    }
    
/*  65 */     this.blob = paramBLOB;
/*  66 */     this.lobOffset = paramLong;
    
/*  68 */     PhysicalConnection localPhysicalConnection = (PhysicalConnection)paramBLOB.getInternalConnection();
/*  69 */     synchronized (localPhysicalConnection) {
/*  70 */       this.buf = localPhysicalConnection.getByteBuffer(paramInt);
    }
/*  72 */     this.count = 0;
/*  73 */     this.bufSize = paramInt;
    
/*  75 */     this.isClosed = false;
  }
  
  public void write(int paramInt)
    throws IOException
  {
/*  89 */     ensureOpen();
    
/*  91 */     if (this.count >= this.bufSize) {
/*  92 */       flushBuffer();
    }
/*  94 */     this.buf[(this.count++)] = ((byte)paramInt);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
/* 111 */     ensureOpen();
    
/* 113 */     int i = paramInt1;
/* 114 */     int j = Math.min(paramInt2, paramArrayOfByte.length - paramInt1);
    
/* 116 */     if (j >= 2 * this.bufSize)
    {
/* 123 */       if (this.count > 0) flushBuffer();
      try
      {
/* 126 */         this.lobOffset += this.blob.setBytes(this.lobOffset, paramArrayOfByte, paramInt1, j);
      }
      catch (SQLException localSQLException)
      {
/* 131 */         IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 132 */         localIOException.fillInStackTrace();
/* 133 */         throw localIOException;
      }
      
    }
    else
    {
/* 139 */       int k = i + j;
      
/* 141 */       while (i < k)
      {
/* 143 */         int m = Math.min(this.bufSize - this.count, k - i);
        
/* 145 */         System.arraycopy(paramArrayOfByte, i, this.buf, this.count, m);
        
/* 147 */         i += m;
/* 148 */         this.count += m;
        
/* 150 */         if (this.count >= this.bufSize) {
/* 151 */           flushBuffer();
        }
      }
    }
  }
  
  public void flush()
    throws IOException
  {
/* 167 */     ensureOpen();
    
/* 169 */     flushBuffer();
  }
  
  public void close()
    throws IOException
  {
/* 183 */     if (this.isClosed) {
/* 184 */       return;
    }
    try
    {
/* 188 */       this.isClosed = true;
/* 189 */       flushBuffer();
      PhysicalConnection localPhysicalConnection1;
      PhysicalConnection localPhysicalConnection2;
      return;
    } finally {
/* 194 */       try { localPhysicalConnection2 = (PhysicalConnection)this.blob.getInternalConnection();
/* 195 */         synchronized (localPhysicalConnection2)
        {
/* 197 */           if (this.buf != null)
          {
/* 199 */             localPhysicalConnection2.cacheBuffer(this.buf);
/* 200 */             this.buf = null;
          }
        }
      }
      catch (SQLException localSQLException2)
      {
/* 206 */         ??? = DatabaseError.createIOException(localSQLException2);
/* 207 */         ((IOException)???).fillInStackTrace();
/* 208 */         throw ((Throwable)???);
      }
    }
  }
  
  private void flushBuffer()
    throws IOException
  {
    try
    {
/* 226 */       if (this.count > 0)
      {
/* 228 */         this.lobOffset += this.blob.setBytes(this.lobOffset, this.buf, 0, this.count);
/* 229 */         this.count = 0;
      }
      
    }
    catch (SQLException localSQLException)
    {
/* 235 */       IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 236 */       localIOException.fillInStackTrace();
/* 237 */       throw localIOException;
    }
  }
  
  void ensureOpen()
    throws IOException
  {
    try
    {
/* 254 */       if (this.isClosed)
      {
/* 256 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 57, null);
/* 257 */         localSQLException1.fillInStackTrace();
/* 258 */         throw localSQLException1;
      }
      
    }
    catch (SQLException localSQLException2)
    {
/* 264 */       IOException localIOException = DatabaseError.createIOException(localSQLException2);
/* 265 */       localIOException.fillInStackTrace();
/* 266 */       throw localIOException;
    }
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
    try
    {
/* 286 */       return this.blob.getInternalConnection();
    }
    catch (Exception localException) {}
    
/* 290 */     return null;
  }
  
/* 297 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleBlobOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */