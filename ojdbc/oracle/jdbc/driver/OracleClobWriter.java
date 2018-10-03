package oracle.jdbc.driver;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.CLOB;
class OracleClobWriter
  extends Writer
{
  DBConversion dbConversion;
  CLOB clob;
  long lobOffset;
  char[] charBuf;
  byte[] nativeBuf;
  int pos;
  int count;
  int chunkSize;
  boolean isClosed;
  
  public OracleClobWriter(CLOB paramCLOB, int paramInt)
    throws SQLException
  {
/*  43 */     this(paramCLOB, paramInt, 1L);
  }
  
  public OracleClobWriter(CLOB paramCLOB, int paramInt, long paramLong)
    throws SQLException
  {
/*  60 */     if ((paramCLOB == null) || (paramInt <= 0) || (paramCLOB.getJavaSqlConnection() == null) || (paramLong < 1L))
    {
/*  63 */       throw new IllegalArgumentException();
    }
    
/*  66 */     this.dbConversion = ((PhysicalConnection)paramCLOB.getInternalConnection()).conversion;
    
/*  69 */     this.clob = paramCLOB;
/*  70 */     this.lobOffset = paramLong;
    
/*  72 */     this.charBuf = new char[paramInt];
/*  73 */     this.nativeBuf = new byte[paramInt * 3];
/*  74 */     this.pos = (this.count = 0);
/*  75 */     this.chunkSize = paramInt;
    
/*  77 */     this.isClosed = false;
  }
  
  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
/*  94 */     synchronized (this.lock)
    {
/*  96 */       ensureOpen();
      
/*  98 */       int i = paramInt1;
/*  99 */       int j = Math.min(paramInt2, paramArrayOfChar.length - paramInt1);
      
/* 101 */       if (j >= 2 * this.chunkSize)
      {
/* 108 */         if (this.count > 0) flushBuffer();
        try
        {
/* 111 */           this.lobOffset += this.clob.putChars(this.lobOffset, paramArrayOfChar, paramInt1, j);
        }
        catch (SQLException localSQLException)
        {
/* 116 */           IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 117 */           localIOException.fillInStackTrace();
/* 118 */           throw localIOException;
        }
        
/* 121 */         return;
      }
      
/* 124 */       int k = i + j;
      
/* 126 */       while (i < k)
      {
/* 128 */         int m = Math.min(this.chunkSize - this.count, k - i);
        
/* 130 */         System.arraycopy(paramArrayOfChar, i, this.charBuf, this.count, m);
        
/* 132 */         i += m;
/* 133 */         this.count += m;
        
/* 135 */         if (this.count >= this.chunkSize) {
/* 136 */           flushBuffer();
        }
      }
    }
  }
  
  public void flush()
    throws IOException
  {
/* 155 */     synchronized (this.lock)
    {
/* 157 */       ensureOpen();
/* 158 */       flushBuffer();
    }
  }
  
  public void close()
    throws IOException
  {
/* 174 */     synchronized (this.lock)
    {
/* 176 */       flushBuffer();
      
/* 178 */       this.isClosed = true;
    }
  }
  
  private void flushBuffer()
    throws IOException
  {
/* 190 */     synchronized (this.lock)
    {
      try
      {
/* 194 */         if (this.count > 0)
        {
/* 196 */           this.lobOffset += this.clob.putChars(this.lobOffset, this.charBuf, 0, this.count);
/* 197 */           this.count = 0;
        }
        
      }
      catch (SQLException localSQLException)
      {
/* 203 */         IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 204 */         localIOException.fillInStackTrace();
/* 205 */         throw localIOException;
      }
    }
  }
  
  void ensureOpen()
    throws IOException
  {
    try
    {
/* 223 */       if (this.isClosed)
      {
/* 225 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 57, null);
/* 226 */         localSQLException1.fillInStackTrace();
/* 227 */         throw localSQLException1;
      }
      
    }
    catch (SQLException localSQLException2)
    {
/* 233 */       IOException localIOException = DatabaseError.createIOException(localSQLException2);
/* 234 */       localIOException.fillInStackTrace();
/* 235 */       throw localIOException;
    }
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
    try
    {
/* 255 */       return this.clob.getInternalConnection();
    }
    catch (Exception localException) {}
    
/* 259 */     return null;
  }
  
/* 266 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleClobWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */