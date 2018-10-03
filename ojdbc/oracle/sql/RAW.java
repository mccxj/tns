package oracle.sql;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import oracle.jdbc.driver.DBConversion;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.util.RepConversion;
public class RAW
  extends Datum
{
  static final long serialVersionUID = -3298750557928937840L;
  
  static int hexDigit2Nibble(char paramChar)
    throws SQLException
  {
/*  67 */     int i = Character.digit(paramChar, 16);
    
/*  69 */     if (i == -1)
    {
/*  72 */       SQLException localSQLException = DatabaseError.createSqlException(null, 59, "Invalid hex digit: " + paramChar);
/*  73 */       localSQLException.fillInStackTrace();
/*  74 */       throw localSQLException;
    }
    
/*  78 */     return i;
  }
  
  public static byte[] hexString2Bytes(String paramString)
    throws SQLException
  {
/*  97 */     int i = paramString.length();
/*  98 */     char[] arrayOfChar = new char[i];
    
/* 100 */     paramString.getChars(0, i, arrayOfChar, 0);
    
/* 103 */     int j = 0;
/* 104 */     int k = 0;
    
/* 106 */     if (i == 0)
/* 107 */       return new byte[0];
    byte[] arrayOfByte;
/* 109 */     if (i % 2 > 0)
    {
/* 111 */       arrayOfByte = new byte[(i + 1) / 2];
/* 112 */       arrayOfByte[(j++)] = ((byte)hexDigit2Nibble(arrayOfChar[(k++)]));
    }
    else
    {
/* 116 */       arrayOfByte = new byte[i / 2];
    }
/* 119 */     for (; 
/* 119 */         j < arrayOfByte.length; j++)
    {
/* 121 */       arrayOfByte[j] = ((byte)(hexDigit2Nibble(arrayOfChar[(k++)]) << 4 | hexDigit2Nibble(arrayOfChar[(k++)])));
    }
    
/* 124 */     return arrayOfByte;
  }
  
  public static RAW newRAW(Object paramObject)
    throws SQLException
  {
/* 154 */     RAW localRAW = new RAW(paramObject);
/* 155 */     return localRAW;
  }
  
  public static RAW oldRAW(Object paramObject)
    throws SQLException
  {
    RAW localRAW;
    
/* 187 */     if ((paramObject instanceof String))
    {
/* 189 */       String str = (String)paramObject;
/* 190 */       byte[] arrayOfByte = null;
      
      try
      {
/* 194 */         arrayOfByte = str.getBytes("ISO8859_1");
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
/* 199 */         SQLException localSQLException = DatabaseError.createSqlException(null, 109, "ISO8859_1 character encoding not found");
/* 200 */         localSQLException.fillInStackTrace();
/* 201 */         throw localSQLException;
      }
      
/* 205 */       localRAW = new RAW(arrayOfByte);
    }
    else
    {
/* 209 */       localRAW = new RAW(paramObject);
    }
/* 211 */     return localRAW;
  }
  
  public RAW() {}
  
  public RAW(byte[] paramArrayOfByte)
  {
/* 241 */     super(paramArrayOfByte);
  }
  
  /**
   * @deprecated
   */
  public RAW(Object paramObject)
    throws SQLException
  {
/* 268 */     this();
    
/* 270 */     if ((paramObject instanceof byte[]))
    {
/* 272 */       setShareBytes((byte[])paramObject);
    }
/* 274 */     else if ((paramObject instanceof String))
    {
/* 276 */       setShareBytes(hexString2Bytes((String)paramObject));
    }
    else
    {
/* 281 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/* 282 */       localSQLException.fillInStackTrace();
/* 283 */       throw localSQLException;
    }
  }
  
  public Object toJdbc()
    throws SQLException
  {
/* 306 */     return getBytes();
  }
  
  public boolean isConvertibleTo(Class paramClass)
  {
/* 324 */     String str = paramClass.getName();
    
/* 326 */     if ((str.compareTo("java.lang.String") == 0) || (str.compareTo("java.io.InputStream") == 0) || (str.compareTo("java.io.Reader") == 0))
    {
/* 330 */       return true;
    }
    
/* 334 */     return false;
  }
  
  public String stringValue()
  {
/* 347 */     String str = RepConversion.bArray2String(getBytes());
/* 348 */     return str;
  }
  
  public Reader characterStreamValue()
    throws SQLException
  {
/* 362 */     int i = (int)getLength();
/* 363 */     char[] arrayOfChar = new char[i * 2];
/* 364 */     byte[] arrayOfByte = shareBytes();
    
/* 366 */     DBConversion.RAWBytesToHexChars(arrayOfByte, i, arrayOfChar);
    
/* 368 */     CharArrayReader localCharArrayReader = new CharArrayReader(arrayOfChar);
    
/* 370 */     return localCharArrayReader;
  }
  
  public InputStream asciiStreamValue()
    throws SQLException
  {
/* 384 */     int i = (int)getLength();
/* 385 */     char[] arrayOfChar = new char[i * 2];
/* 386 */     byte[] arrayOfByte1 = shareBytes();
    
/* 388 */     DBConversion.RAWBytesToHexChars(arrayOfByte1, i, arrayOfChar);
    
/* 390 */     byte[] arrayOfByte2 = new byte[i * 2];
    
/* 392 */     DBConversion.javaCharsToAsciiBytes(arrayOfChar, i * 2, arrayOfByte2);
    
/* 394 */     ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte2);
/* 395 */     return localByteArrayInputStream;
  }
  
  public InputStream binaryStreamValue()
    throws SQLException
  {
/* 409 */     return getStream();
  }
  
  public Object makeJdbcArray(int paramInt)
  {
/* 432 */     return new byte[paramInt][];
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 447 */     return null;
  }
  
/* 527 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/RAW.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */