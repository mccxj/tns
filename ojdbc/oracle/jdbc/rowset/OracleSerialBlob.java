package oracle.jdbc.rowset;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
public class OracleSerialBlob
  implements Blob, Serializable, Cloneable
{
  private byte[] buffer;
  private long length;
/*  34 */   private boolean isFreed = false;
  
  public OracleSerialBlob(byte[] paramArrayOfByte)
    throws SQLException
  {
/*  41 */     this.length = paramArrayOfByte.length;
/*  42 */     this.buffer = new byte[(int)this.length];
/*  43 */     for (int i = 0; i < this.length; i++) {
/*  44 */       this.buffer[i] = paramArrayOfByte[i];
    }
  }
  
  public OracleSerialBlob(Blob paramBlob)
    throws SQLException
  {
/*  54 */     this.length = paramBlob.length();
/*  55 */     this.buffer = new byte[(int)this.length];
/*  56 */     BufferedInputStream localBufferedInputStream = new BufferedInputStream(paramBlob.getBinaryStream());
    
    try
    {
/*  60 */       int i = 0;
/*  61 */       int j = 0;
      
      do
      {
/*  66 */         i = localBufferedInputStream.read(this.buffer, j, (int)(this.length - j));
        
/*  68 */         j += i;
/*  69 */       } while (i > 0);
      SQLException localSQLException1;
      SQLException localSQLException2;
      return;
    } catch (IOException localIOException2) {
/*  74 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 346, localIOException2.getMessage());
/*  75 */       localSQLException1.fillInStackTrace();
/*  76 */       throw localSQLException1;
    }
    finally
    {
      try {
/*  81 */         if (localBufferedInputStream != null) {
/*  82 */           localBufferedInputStream.close();
        }
      }
      catch (IOException localIOException3) {
/*  86 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 346, localIOException3.getMessage());
/*  87 */         localSQLException2.fillInStackTrace();
/*  88 */         throw localSQLException2;
      }
    }
  }
  
  public InputStream getBinaryStream()
    throws SQLException
  {
/* 101 */     if (this.isFreed)
    {
/* 103 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 104 */       localSQLException.fillInStackTrace();
/* 105 */       throw localSQLException;
    }
    
/* 108 */     return new ByteArrayInputStream(this.buffer);
  }
  
  public byte[] getBytes(long paramLong, int paramInt)
    throws SQLException
  {
/* 119 */     if (this.isFreed)
    {
/* 121 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 122 */       ((SQLException)localObject).fillInStackTrace();
/* 123 */       throw ((Throwable)localObject);
    }
    
/* 126 */     Object localObject = null;
    
/* 128 */     paramLong -= 1L;
/* 129 */     if ((paramLong < 0L) || (paramInt > this.length) || (paramLong + paramInt > this.length))
    {
/* 131 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 132 */       localSQLException.fillInStackTrace();
/* 133 */       throw localSQLException;
    }
    
/* 137 */     localObject = new byte[paramInt];
/* 138 */     System.arraycopy(this.buffer, (int)paramLong, localObject, 0, paramInt);
    
/* 141 */     return (byte[])localObject;
  }
  
  public long length()
    throws SQLException
  {
/* 150 */     if (this.isFreed)
    {
/* 152 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 153 */       localSQLException.fillInStackTrace();
/* 154 */       throw localSQLException;
    }
    
/* 157 */     return this.length;
  }
  
  public long position(byte[] paramArrayOfByte, long paramLong)
    throws SQLException
  {
    SQLException localSQLException;
    
/* 168 */     if (this.isFreed)
    {
/* 170 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 171 */       localSQLException.fillInStackTrace();
/* 172 */       throw localSQLException;
    }
    
/* 175 */     if (paramLong < 1L)
    {
/* 177 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 178 */       localSQLException.fillInStackTrace();
/* 179 */       throw localSQLException;
    }
    
/* 182 */     if ((paramLong > this.length) || (paramLong + paramArrayOfByte.length - 1L > this.length)) {
/* 183 */       return -1L;
    }
/* 185 */     int i = (int)(paramLong - 1L);
/* 186 */     int j = 0;
/* 187 */     long l1 = paramArrayOfByte.length;
    
/* 189 */     while (i < this.length)
    {
/* 191 */       int k = 0;
/* 192 */       long l2 = i + 1;
/* 193 */       int m = i;
/* 194 */       while ((k < l1) && (m < this.length) && (paramArrayOfByte[k] == this.buffer[m]))
      {
/* 196 */         k++;
/* 197 */         m++;
/* 198 */         if (k == l1) {
/* 199 */           return l2;
        }
      }
/* 202 */       i++;
    }
    
/* 205 */     return -1L;
  }
  
  public long position(Blob paramBlob, long paramLong)
    throws SQLException
  {
/* 214 */     if (this.isFreed)
    {
/* 216 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 217 */       localSQLException.fillInStackTrace();
/* 218 */       throw localSQLException;
    }
    
/* 221 */     return position(paramBlob.getBytes(1L, (int)paramBlob.length()), paramLong);
  }
  
  public int setBytes(long paramLong, byte[] paramArrayOfByte)
    throws SQLException
  {
/* 250 */     if (this.isFreed)
    {
/* 252 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 253 */       localSQLException.fillInStackTrace();
/* 254 */       throw localSQLException;
    }
    
/* 258 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 259 */     localSQLException.fillInStackTrace();
/* 260 */     throw localSQLException;
  }
  
  public int setBytes(long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 293 */     if (this.isFreed)
    {
/* 295 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 296 */       localSQLException.fillInStackTrace();
/* 297 */       throw localSQLException;
    }
    
/* 301 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 302 */     localSQLException.fillInStackTrace();
/* 303 */     throw localSQLException;
  }
  
  public OutputStream setBinaryStream(long paramLong)
    throws SQLException
  {
/* 329 */     if (this.isFreed)
    {
/* 331 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 332 */       localSQLException.fillInStackTrace();
/* 333 */       throw localSQLException;
    }
    
/* 337 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 338 */     localSQLException.fillInStackTrace();
/* 339 */     throw localSQLException;
  }
  
  public void truncate(long paramLong)
    throws SQLException
  {
/* 361 */     if (this.isFreed)
    {
/* 363 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 364 */       localSQLException.fillInStackTrace();
/* 365 */       throw localSQLException;
    }
    
/* 369 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 370 */     localSQLException.fillInStackTrace();
/* 371 */     throw localSQLException;
  }
  
  public void free()
    throws SQLException
  {
/* 384 */     if (this.isFreed) { return;
    }
/* 386 */     this.isFreed = true;
/* 387 */     this.buffer = null;
/* 388 */     this.length = 0L;
  }
  
  public InputStream getBinaryStream(long paramLong1, long paramLong2)
    throws SQLException
  {
    SQLException localSQLException;
/* 396 */     if (this.isFreed)
    {
/* 398 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 399 */       localSQLException.fillInStackTrace();
/* 400 */       throw localSQLException;
    }
    
/* 407 */     paramLong1 -= 1L;
/* 408 */     if ((paramLong1 < 0L) || (paramLong1 + 1L > this.length) || (paramLong2 > this.length) || (paramLong1 > 2147483647L) || (this.length > 2147483647L) || (paramLong1 + paramLong2 > this.length))
    {
/* 413 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 414 */       localSQLException.fillInStackTrace();
/* 415 */       throw localSQLException;
    }
    
/* 420 */     return new ByteArrayInputStream(this.buffer, (int)paramLong1, (int)paramLong2);
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 436 */     return null;
  }
  
/* 441 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleSerialBlob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */