package oracle.jdbc.rowset;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringBufferInputStream;
import java.io.Writer;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
public class OracleSerialClob
  implements Clob, NClob, Serializable, Cloneable
{
  private static final int MAX_CHAR_BUFFER_SIZE = 1024;
  private char[] buffer;
  private long length;
/*  45 */   private boolean isFreed = false;
  
  public OracleSerialClob(char[] paramArrayOfChar)
    throws SQLException
  {
/*  52 */     this.length = paramArrayOfChar.length;
/*  53 */     this.buffer = new char[(int)this.length];
/*  54 */     for (int i = 0; i < this.length; i++) {
/*  55 */       this.buffer[i] = paramArrayOfChar[i];
    }
  }
  
  public OracleSerialClob(Clob paramClob)
    throws SQLException
  {
/*  65 */     this.length = paramClob.length();
/*  66 */     this.buffer = new char[(int)this.length];
/*  67 */     BufferedReader localBufferedReader = new BufferedReader(paramClob.getCharacterStream());
    
    try
    {
/*  71 */       int i = 0;
/*  72 */       int j = 0;
      
      do
      {
/*  77 */         i = localBufferedReader.read(this.buffer, j, (int)(this.length - j));
        
/*  79 */         j += i;
/*  80 */       } while (i > 0);
      SQLException localSQLException1;
      SQLException localSQLException2;
      return;
    } catch (IOException localIOException2) {
/*  85 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 347, localIOException2.getMessage());
/*  86 */       localSQLException1.fillInStackTrace();
/*  87 */       throw localSQLException1;
    }
    finally
    {
      try {
/*  92 */         if (localBufferedReader != null) {
/*  93 */           localBufferedReader.close();
        }
      }
      catch (IOException localIOException3) {
/*  97 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 347, localIOException3.getMessage());
/*  98 */         localSQLException2.fillInStackTrace();
/*  99 */         throw localSQLException2;
      }
    }
  }
  
  public OracleSerialClob(Reader paramReader)
    throws SQLException
  {
    try
    {
/* 114 */       int i = 0;
/* 115 */       localObject = new char['Ѐ'];
/* 116 */       StringBuilder localStringBuilder = new StringBuilder(1024);
      
      for (;;)
      {
/* 120 */         i = paramReader.read((char[])localObject);
        
/* 124 */         if (i == -1) {
          break;
        }
/* 127 */         localStringBuilder.append((char[])localObject, 0, i);
      }
      
/* 130 */       paramReader.close();
      
/* 132 */       this.buffer = localStringBuilder.toString().toCharArray();
/* 133 */       this.length = this.buffer.length;
    }
    catch (Exception localException)
    {
/* 138 */       Object localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 347, localException.getMessage());
/* 139 */       ((SQLException)localObject).fillInStackTrace();
/* 140 */       throw ((Throwable)localObject);
    }
  }
  
  public OracleSerialClob(Reader paramReader, long paramLong)
    throws SQLException
  {
    try
    {
/* 154 */       int i = 0;
/* 155 */       long l = paramLong;
/* 156 */       char[] arrayOfChar = new char['Ѐ'];
/* 157 */       StringBuilder localStringBuilder = new StringBuilder(1024);
      
/* 159 */       while (l > 0L)
      {
/* 161 */         i = paramReader.read(arrayOfChar, 0, Math.min(1024, (int)l));
        
/* 166 */         if (i == -1) {
          break;
        }
/* 169 */         localStringBuilder.append(arrayOfChar, 0, i);
/* 170 */         l -= i;
      }
      
/* 173 */       paramReader.close();
      
/* 175 */       this.buffer = localStringBuilder.toString().toCharArray();
/* 176 */       this.length = this.buffer.length;
    }
    catch (Exception localException)
    {
/* 181 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 347, localException.getMessage());
/* 182 */       localSQLException.fillInStackTrace();
/* 183 */       throw localSQLException;
    }
  }
  
  public InputStream getAsciiStream()
    throws SQLException
  {
/* 195 */     if (this.isFreed)
    {
/* 197 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 198 */       localSQLException.fillInStackTrace();
/* 199 */       throw localSQLException;
    }
    
/* 202 */     return new StringBufferInputStream(new String(this.buffer));
  }
  
  public Reader getCharacterStream()
    throws SQLException
  {
/* 211 */     if (this.isFreed)
    {
/* 213 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 214 */       localSQLException.fillInStackTrace();
/* 215 */       throw localSQLException;
    }
    
/* 218 */     return new CharArrayReader(this.buffer);
  }
  
  public String getSubString(long paramLong, int paramInt)
    throws SQLException
  {
    SQLException localSQLException;
    
/* 229 */     if (this.isFreed)
    {
/* 231 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 232 */       localSQLException.fillInStackTrace();
/* 233 */       throw localSQLException;
    }
    
/* 236 */     if ((paramLong < 1L) || (paramInt < 0) || (paramInt > this.length) || (paramLong + paramInt - 1L > this.length))
    {
/* 240 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 241 */       localSQLException.fillInStackTrace();
/* 242 */       throw localSQLException;
    }
/* 244 */     if (paramInt == 0) {
/* 245 */       return new String();
    }
/* 247 */     return new String(this.buffer, (int)paramLong - 1, paramInt);
  }
  
  public long length()
    throws SQLException
  {
/* 257 */     if (this.isFreed)
    {
/* 259 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 260 */       localSQLException.fillInStackTrace();
/* 261 */       throw localSQLException;
    }
    
/* 264 */     return this.length;
  }
  
  public long position(String paramString, long paramLong)
    throws SQLException
  {
/* 275 */     if (this.isFreed)
    {
/* 277 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 278 */       ((SQLException)localObject).fillInStackTrace();
/* 279 */       throw ((Throwable)localObject);
    }
    
/* 282 */     if (paramLong < 1L)
    {
/* 284 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "position()");
/* 285 */       ((SQLException)localObject).fillInStackTrace();
/* 286 */       throw ((Throwable)localObject);
    }
    
/* 289 */     if ((paramLong > this.length) || (paramLong + paramString.length() - 1L > this.length)) {
/* 290 */       return -1L;
    }
/* 292 */     Object localObject = paramString.toCharArray();
/* 293 */     int i = (int)(paramLong - 1L);
/* 294 */     int j = 0;
/* 295 */     long l1 = localObject.length;
    
/* 297 */     while (i < this.length)
    {
/* 299 */       int k = 0;
/* 300 */       long l2 = i + 1;
/* 301 */       int m = i;
/* 302 */       while ((k < l1) && (m < this.length) && (localObject[k] == this.buffer[m]))
      {
/* 304 */         k++;
/* 305 */         m++;
/* 306 */         if (k == l1) {
/* 307 */           return l2;
        }
      }
/* 310 */       i++;
    }
/* 312 */     return -1L;
  }
  
  public long position(Clob paramClob, long paramLong)
    throws SQLException
  {
/* 321 */     if (this.isFreed)
    {
/* 323 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 324 */       localSQLException.fillInStackTrace();
/* 325 */       throw localSQLException;
    }
    
/* 328 */     return position(paramClob.getSubString(1L, (int)paramClob.length()), paramLong);
  }
  
  public int setString(long paramLong, String paramString)
    throws SQLException
  {
/* 357 */     if (this.isFreed)
    {
/* 359 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 360 */       localSQLException.fillInStackTrace();
/* 361 */       throw localSQLException;
    }
    
/* 365 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 366 */     localSQLException.fillInStackTrace();
/* 367 */     throw localSQLException;
  }
  
  public int setString(long paramLong, String paramString, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 397 */     if (this.isFreed)
    {
/* 399 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 400 */       localSQLException.fillInStackTrace();
/* 401 */       throw localSQLException;
    }
    
/* 405 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 406 */     localSQLException.fillInStackTrace();
/* 407 */     throw localSQLException;
  }
  
  public OutputStream setAsciiStream(long paramLong)
    throws SQLException
  {
/* 433 */     if (this.isFreed)
    {
/* 435 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 436 */       localSQLException.fillInStackTrace();
/* 437 */       throw localSQLException;
    }
    
/* 441 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 442 */     localSQLException.fillInStackTrace();
/* 443 */     throw localSQLException;
  }
  
  public Writer setCharacterStream(long paramLong)
    throws SQLException
  {
/* 470 */     if (this.isFreed)
    {
/* 472 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 473 */       localSQLException.fillInStackTrace();
/* 474 */       throw localSQLException;
    }
    
/* 478 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 479 */     localSQLException.fillInStackTrace();
/* 480 */     throw localSQLException;
  }
  
  public void truncate(long paramLong)
    throws SQLException
  {
/* 503 */     if (this.isFreed)
    {
/* 505 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 506 */       localSQLException.fillInStackTrace();
/* 507 */       throw localSQLException;
    }
    
/* 511 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 512 */     localSQLException.fillInStackTrace();
/* 513 */     throw localSQLException;
  }
  
  public void free()
    throws SQLException
  {
/* 526 */     if (this.isFreed) { return;
    }
/* 528 */     this.isFreed = true;
/* 529 */     this.buffer = null;
/* 530 */     this.length = 0L;
  }
  
  public Reader getCharacterStream(long paramLong1, long paramLong2)
    throws SQLException
  {
    SQLException localSQLException;
/* 538 */     if (this.isFreed)
    {
/* 540 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 541 */       localSQLException.fillInStackTrace();
/* 542 */       throw localSQLException;
    }
    
/* 549 */     paramLong1 -= 1L;
/* 550 */     if ((paramLong1 < 0L) || (paramLong1 + 1L > this.length) || (paramLong2 < 0L) || (paramLong2 > this.length) || (paramLong1 + paramLong2 > this.length))
    {
/* 554 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 555 */       localSQLException.fillInStackTrace();
/* 556 */       throw localSQLException;
    }
    
/* 561 */     return new CharArrayReader(this.buffer, (int)paramLong1, (int)paramLong2);
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 577 */     return null;
  }
  
/* 582 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleSerialClob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */