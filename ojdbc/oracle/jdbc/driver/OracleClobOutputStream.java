package oracle.jdbc.driver;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.CLOB;
class OracleClobOutputStream
  extends OutputStream
{
  long lobOffset;
  CLOB clob;
  byte[] buf;
  int count;
  int bufSize;
  boolean isClosed;
  
  public OracleClobOutputStream(CLOB paramCLOB, int paramInt)
    throws SQLException
  {
/*  44 */     this(paramCLOB, paramInt, 1L);
  }
  
  public OracleClobOutputStream(CLOB paramCLOB, int paramInt, long paramLong)
    throws SQLException
  {
/*  60 */     if ((paramCLOB == null) || (paramInt <= 0) || (paramLong < 1L))
    {
/*  62 */       throw new IllegalArgumentException();
    }
    
/*  65 */     this.clob = paramCLOB;
/*  66 */     this.lobOffset = paramLong;
    
/*  68 */     PhysicalConnection localPhysicalConnection = (PhysicalConnection)paramCLOB.getInternalConnection();
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
/* 126 */         char[] arrayOfChar = new char[j];
        
/* 128 */         for (int m = 0; m < j; m++) {
/* 129 */           arrayOfChar[m] = ((char)paramArrayOfByte[(m + paramInt1)]);
        }
/* 131 */         this.lobOffset += this.clob.putChars(this.lobOffset, arrayOfChar);
      }
      catch (SQLException localSQLException)
      {
/* 136 */         IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 137 */         localIOException.fillInStackTrace();
/* 138 */         throw localIOException;
      }
      
/* 141 */       return;
    }
    
/* 144 */     int k = i + j;
    
/* 146 */     while (i < k)
    {
/* 148 */       int n = Math.min(this.bufSize - this.count, k - i);
      
/* 150 */       System.arraycopy(paramArrayOfByte, i, this.buf, this.count, n);
      
/* 152 */       i += n;
/* 153 */       this.count += n;
      
/* 155 */       if (this.count >= this.bufSize) {
/* 156 */         flushBuffer();
      }
    }
  }
  
  public void flush()
    throws IOException
  {
/* 173 */     ensureOpen();
    
/* 175 */     flushBuffer();
  }
  
  public void close()
    throws IOException
  {
/* 189 */     if (this.isClosed) {
/* 190 */       return;
    }
    try
    {
/* 194 */       this.isClosed = true;
/* 195 */       flushBuffer();
      PhysicalConnection localPhysicalConnection1;
      PhysicalConnection localPhysicalConnection2;
      return;
    } finally {
/* 200 */       try { localPhysicalConnection2 = (PhysicalConnection)this.clob.getInternalConnection();
/* 201 */         synchronized (localPhysicalConnection2)
        {
/* 203 */           if (this.buf != null)
          {
/* 205 */             localPhysicalConnection2.cacheBuffer(this.buf);
/* 206 */             this.buf = null;
          }
        }
      }
      catch (SQLException localSQLException2)
      {
/* 212 */         ??? = DatabaseError.createIOException(localSQLException2);
/* 213 */         ((IOException)???).fillInStackTrace();
/* 214 */         throw ((Throwable)???);
      }
    }
  }
  
  private void flushBuffer()
    throws IOException
  {
    try
    {
/* 232 */       if (this.count > 0)
      {
/* 234 */         char[] arrayOfChar = new char[this.count];
        
/* 236 */         for (int i = 0; i < this.count; i++) {
/* 237 */           arrayOfChar[i] = ((char)this.buf[i]);
        }
/* 239 */         this.lobOffset += this.clob.putChars(this.lobOffset, arrayOfChar);
        
/* 241 */         this.count = 0;
      }
      
    }
    catch (SQLException localSQLException)
    {
/* 247 */       IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 248 */       localIOException.fillInStackTrace();
/* 249 */       throw localIOException;
    }
  }
  
  void ensureOpen()
    throws IOException
  {
    try
    {
/* 266 */       if (this.isClosed)
      {
/* 268 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 57, null);
/* 269 */         localSQLException1.fillInStackTrace();
/* 270 */         throw localSQLException1;
      }
      
    }
    catch (SQLException localSQLException2)
    {
/* 276 */       IOException localIOException = DatabaseError.createIOException(localSQLException2);
/* 277 */       localIOException.fillInStackTrace();
/* 278 */       throw localIOException;
    }
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
    try
    {
/* 298 */       return this.clob.getInternalConnection();
    }
    catch (Exception localException) {}
    
/* 302 */     return null;
  }
  
/* 309 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleClobOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */