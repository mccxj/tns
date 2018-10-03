package oracle.sql;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.LargeObjectAccessMode;
import oracle.jdbc.driver.DatabaseError;
public class BFILE
  extends DatumWithConnection
  implements oracle.jdbc.internal.OracleBfile
{
  public static final int MAX_CHUNK_SIZE = 32512;
  public static final int MODE_READONLY = 0;
  public static final int MODE_READWRITE = 1;
  BfileDBAccess dbaccess;
/*  37 */   private long bfileLength = -1L;
  
  protected BFILE() {}
  
  public BFILE(oracle.jdbc.OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  54 */     this(paramOracleConnection, null);
  }
  
  public BFILE(oracle.jdbc.OracleConnection paramOracleConnection, byte[] paramArrayOfByte)
    throws SQLException
  {
/*  66 */     super(paramArrayOfByte);
    
/*  69 */     setPhysicalConnectionOf(paramOracleConnection);
    
/*  71 */     this.dbaccess = getInternalConnection().createBfileDBAccess();
  }
  
  public long length()
    throws SQLException
  {
/*  86 */     this.bfileLength = getDBAccess().length(this);
/*  87 */     return this.bfileLength;
  }
  
  public byte[] getBytes(long paramLong, int paramInt)
    throws SQLException
  {
/* 102 */     if ((paramLong < 1L) || (paramInt < 0))
    {
/* 106 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, null);
/* 107 */       ((SQLException)localObject).fillInStackTrace();
/* 108 */       throw ((Throwable)localObject);
    }
    
/* 112 */     Object localObject = null;
    
/* 114 */     if (paramInt == 0)
    {
/* 116 */       localObject = new byte[0];
    }
    else
    {
/* 120 */       long l = 0L;
/* 121 */       byte[] arrayOfByte = new byte[paramInt];
      
/* 123 */       l = getBytes(paramLong, paramInt, arrayOfByte);
      
/* 125 */       if (l > 0L)
      {
/* 127 */         if (l == paramInt)
        {
/* 129 */           localObject = arrayOfByte;
        }
        else
        {
/* 136 */           localObject = new byte[(int)l];
          
/* 138 */           System.arraycopy(arrayOfByte, 0, localObject, 0, (int)l);
        }
        
      }
      else {
/* 143 */         localObject = new byte[0];
      }
    }
    
/* 147 */     return (byte[])localObject;
  }
  
  public int getBytes(long paramLong, int paramInt, byte[] paramArrayOfByte)
    throws SQLException
  {
/* 163 */     int i = getDBAccess().getBytes(this, paramLong, paramInt, paramArrayOfByte);
    
/* 165 */     return i;
  }
  
  public InputStream getBinaryStream()
    throws SQLException
  {
/* 177 */     InputStream localInputStream = getDBAccess().newInputStream(this, 32512, 0L);
    
/* 179 */     return localInputStream;
  }
  
  public long position(byte[] paramArrayOfByte, long paramLong)
    throws SQLException
  {
/* 195 */     long l = getDBAccess().position(this, paramArrayOfByte, paramLong);
    
/* 197 */     return l;
  }
  
  public long position(BFILE paramBFILE, long paramLong)
    throws SQLException
  {
/* 213 */     long l = getDBAccess().position(this, paramBFILE, paramLong);
    
/* 215 */     return l;
  }
  
  public long position(oracle.jdbc.OracleBfile paramOracleBfile, long paramLong)
    throws SQLException
  {
/* 231 */     return position((BFILE)paramOracleBfile, paramLong);
  }
  
  public String getName()
    throws SQLException
  {
/* 245 */     String str = getDBAccess().getName(this);
/* 246 */     return str;
  }
  
  public String getDirAlias()
    throws SQLException
  {
/* 260 */     String str = getDBAccess().getDirAlias(this);
/* 261 */     return str;
  }
  
  public void openFile()
    throws SQLException
  {
/* 274 */     getDBAccess().openFile(this);
  }
  
  public boolean isFileOpen()
    throws SQLException
  {
/* 289 */     boolean bool = getDBAccess().isFileOpen(this);
/* 290 */     return bool;
  }
  
  public boolean fileExists()
    throws SQLException
  {
/* 306 */     boolean bool = getDBAccess().fileExists(this);
/* 307 */     return bool;
  }
  
  public void closeFile()
    throws SQLException
  {
/* 320 */     getDBAccess().closeFile(this);
  }
  
  public byte[] getLocator()
  {
/* 331 */     return getBytes();
  }
  
  public void setLocator(byte[] paramArrayOfByte)
  {
/* 342 */     setBytes(paramArrayOfByte);
  }
  
  public InputStream getBinaryStream(long paramLong)
    throws SQLException
  {
/* 356 */     return getDBAccess().newInputStream(this, 32512, paramLong);
  }
  
  public void open()
    throws SQLException
  {
/* 368 */     getDBAccess().open(this, 0);
  }
  
  public void open(LargeObjectAccessMode paramLargeObjectAccessMode)
    throws SQLException
  {
/* 377 */     open(paramLargeObjectAccessMode.getCode());
  }
  
  public void open(int paramInt)
    throws SQLException
  {
/* 389 */     if (paramInt != 0)
    {
/* 393 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 102);
/* 394 */       localSQLException.fillInStackTrace();
/* 395 */       throw localSQLException;
    }
    
/* 399 */     getDBAccess().open(this, paramInt);
  }
  
  public void close()
    throws SQLException
  {
/* 410 */     getDBAccess().close(this);
  }
  
  public boolean isOpen()
    throws SQLException
  {
/* 422 */     return getDBAccess().isOpen(this);
  }
  
  public Object toJdbc()
    throws SQLException
  {
/* 443 */     return this;
  }
  
  public boolean isConvertibleTo(Class paramClass)
  {
/* 461 */     String str = paramClass.getName();
    
/* 463 */     boolean bool = (str.compareTo("java.io.InputStream") == 0) || (str.compareTo("java.io.Reader") == 0);
    
/* 466 */     return bool;
  }
  
  public Reader characterStreamValue()
    throws SQLException
  {
/* 480 */     getInternalConnection();return getDBAccess().newConversionReader(this, 8);
  }
  
  public InputStream asciiStreamValue()
    throws SQLException
  {
/* 494 */     getInternalConnection();return getDBAccess().newConversionInputStream(this, 2);
  }
  
  public InputStream binaryStreamValue()
    throws SQLException
  {
/* 509 */     return getBinaryStream();
  }
  
  public Object makeJdbcArray(int paramInt)
  {
/* 532 */     return new BFILE[paramInt];
  }
  
  public BfileDBAccess getDBAccess()
    throws SQLException
  {
/* 543 */     if (this.dbaccess == null) {
/* 544 */       this.dbaccess = getInternalConnection().createBfileDBAccess();
    }
/* 546 */     if (getPhysicalConnection().isClosed())
    {
/* 548 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 549 */       localSQLException.fillInStackTrace();
/* 550 */       throw localSQLException;
    }
    
/* 553 */     return this.dbaccess;
  }
  
  public final void setLength(long paramLong)
  {
/* 564 */     this.bfileLength = paramLong;
  }
  
  public Connection getJavaSqlConnection()
    throws SQLException
  {
/* 571 */     return super.getJavaSqlConnection();
  }
  
/* 576 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/BFILE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */