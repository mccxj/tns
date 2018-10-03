package oracle.jdbc.driver;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
class OracleConversionReader
  extends Reader
{
  static final int CHUNK_SIZE = 4096;
  DBConversion dbConversion;
  int conversion;
  InputStream istream;
  char[] buf;
  byte[] byteBuf;
  int pos;
  int count;
  int numUnconvertedBytes;
  boolean isClosed;
  boolean endOfStream;
  private short csform;
  int[] nbytes;
  
  public OracleConversionReader(DBConversion paramDBConversion, InputStream paramInputStream, int paramInt)
    throws SQLException
  {
/*  58 */     if ((paramDBConversion == null) || (paramInputStream == null) || ((paramInt != 8) && (paramInt != 9)))
    {
/*  62 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  63 */       localSQLException.fillInStackTrace();
/*  64 */       throw localSQLException;
    }
    
/*  67 */     this.dbConversion = paramDBConversion;
/*  68 */     this.conversion = paramInt;
/*  69 */     this.istream = paramInputStream;
/*  70 */     this.pos = (this.count = 0);
/*  71 */     this.numUnconvertedBytes = 0;
    
/*  73 */     this.isClosed = false;
/*  74 */     this.nbytes = new int[1];
    
/*  76 */     if (paramInt == 8)
    {
/*  78 */       this.byteBuf = new byte['ࠀ'];
/*  79 */       this.buf = new char['က'];
    }
/*  81 */     else if (paramInt == 9)
    {
/*  83 */       this.byteBuf = new byte['က'];
/*  84 */       this.buf = new char['က'];
    }
  }
  
  public void setFormOfUse(short paramShort)
  {
/*  93 */     this.csform = paramShort;
  }
  
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
/* 115 */     ensureOpen();
    
/* 117 */     if (!needChars()) {
/* 118 */       return -1;
    }
/* 120 */     int i = paramInt1;
/* 121 */     int j = i + Math.min(paramInt2, paramArrayOfChar.length - paramInt1);
    
/* 123 */     i += writeChars(paramArrayOfChar, i, j - i);
    
/* 125 */     while ((i < j) && (needChars()))
    {
/* 127 */       i += writeChars(paramArrayOfChar, i, j - i);
    }
    
/* 130 */     return i - paramInt1;
  }
  
  protected boolean needChars()
    throws IOException
  {
/* 144 */     ensureOpen();
    
/* 146 */     if (this.pos >= this.count)
    {
/* 150 */       if (!this.endOfStream)
      {
        try
        {
/* 154 */           int i = this.istream.read(this.byteBuf, this.numUnconvertedBytes, this.byteBuf.length - this.numUnconvertedBytes);
          
/* 157 */           if (i == -1)
          {
/* 159 */             this.endOfStream = true;
            
/* 161 */             this.istream.close();
            
/* 163 */             if (this.numUnconvertedBytes != 0)
            {
/* 165 */               SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 55);
/* 166 */               localSQLException2.fillInStackTrace();
/* 167 */               throw localSQLException2;
            }
          }
          
/* 171 */           i += this.numUnconvertedBytes;
          
/* 173 */           if (i > 0)
          {
/* 175 */             switch (this.conversion)
            {
            case 8: 
/* 180 */               this.count = DBConversion.RAWBytesToHexChars(this.byteBuf, i, this.buf);
              
/* 182 */               break;
            
            case 9: 
/* 187 */               this.nbytes[0] = i;
              
/* 189 */               if (this.csform == 2) {
/* 190 */                 this.count = this.dbConversion.NCHARBytesToJavaChars(this.byteBuf, 0, this.buf, 0, this.nbytes, this.buf.length);
              }
              else
              {
/* 195 */                 this.count = this.dbConversion.CHARBytesToJavaChars(this.byteBuf, 0, this.buf, 0, this.nbytes, this.buf.length);
              }
              
/* 200 */               this.numUnconvertedBytes = this.nbytes[0];
              
/* 202 */               for (int j = 0; j < this.numUnconvertedBytes; j++) {
/* 203 */                 this.byteBuf[j] = this.byteBuf[(i - this.numUnconvertedBytes + j)];
              }
/* 205 */               break;
            
            default: 
/* 210 */               localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23);
/* 211 */               ((SQLException)localObject).fillInStackTrace();
/* 212 */               throw ((Throwable)localObject);
            }
            
            
/* 216 */             if (this.count > 0)
            {
/* 218 */               this.pos = 0;
              
/* 220 */               return true;
            }
            
          }
          
        }
        catch (SQLException localSQLException1)
        {
/* 229 */           Object localObject = DatabaseError.createIOException(localSQLException1);
/* 230 */           ((IOException)localObject).fillInStackTrace();
/* 231 */           throw ((Throwable)localObject);
        }
      }
      
/* 236 */       return false;
    }
    
/* 239 */     return true;
  }
  
  protected int writeChars(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
/* 254 */     int i = Math.min(paramInt2, this.count - this.pos);
    
/* 256 */     System.arraycopy(this.buf, this.pos, paramArrayOfChar, paramInt1, i);
    
/* 258 */     this.pos += i;
    
/* 260 */     return i;
  }
  
  public boolean ready()
    throws IOException
  {
/* 277 */     ensureOpen();
    
/* 279 */     return this.pos < this.count;
  }
  
  public void close()
    throws IOException
  {
/* 294 */     if (!this.isClosed)
    {
/* 296 */       this.isClosed = true;
      
/* 298 */       this.istream.close();
    }
  }
  
  void ensureOpen()
    throws IOException
  {
    try
    {
/* 314 */       if (this.isClosed)
      {
/* 316 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 57, null);
/* 317 */         localSQLException1.fillInStackTrace();
/* 318 */         throw localSQLException1;
      }
      
    }
    catch (SQLException localSQLException2)
    {
/* 324 */       IOException localIOException = DatabaseError.createIOException(localSQLException2);
/* 325 */       localIOException.fillInStackTrace();
/* 326 */       throw localIOException;
    }
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 343 */     return null;
  }
  
/* 348 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleConversionReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */