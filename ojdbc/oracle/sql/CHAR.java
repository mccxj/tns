package oracle.sql;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
public class CHAR
  extends Datum
{
  static final long serialVersionUID = 5559010489982176244L;
/*  73 */   public static final CharacterSet DEFAULT_CHARSET = CharacterSet.make(-1);
  
  private CharacterSet charSet;
  
  private int oracleId;
  
  protected CHAR() {}
  
  public CHAR(byte[] paramArrayOfByte, CharacterSet paramCharacterSet)
  {
/* 116 */     setValue(paramArrayOfByte, paramCharacterSet);
  }
  
  public CHAR(byte[] paramArrayOfByte, int paramInt1, int paramInt2, CharacterSet paramCharacterSet)
  {
/* 136 */     byte[] arrayOfByte = new byte[paramInt2];
    
/* 138 */     System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
/* 139 */     setValue(arrayOfByte, paramCharacterSet);
  }
  
  public CHAR(String paramString, CharacterSet paramCharacterSet, int paramInt)
    throws SQLException
  {
/* 158 */     this(paramString, paramCharacterSet);
    
/* 161 */     byte[] arrayOfByte1 = shareBytes();
    
/* 163 */     if (arrayOfByte1.length < paramInt) {
/* 164 */       byte[] arrayOfByte2 = new byte[paramInt];
/* 165 */       System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte1.length);
/* 166 */       for (int i = arrayOfByte1.length; i < paramInt; i++) arrayOfByte2[i] = 32;
/* 167 */       setShareBytes(arrayOfByte2);
    }
  }
  
  public CHAR(String paramString, CharacterSet paramCharacterSet)
    throws SQLException
  {
/* 190 */     if (paramCharacterSet == null)
    {
/* 192 */       paramCharacterSet = DEFAULT_CHARSET;
    }
    
/* 195 */     setValue(paramCharacterSet.convertWithReplacement(paramString), paramCharacterSet);
  }
  
  public CHAR(Object paramObject, CharacterSet paramCharacterSet)
    throws SQLException
  {
/* 216 */     this(paramObject.toString(), paramCharacterSet);
  }
  
  public CharacterSet getCharacterSet()
  {
/* 228 */     if (this.charSet == null)
    {
/* 232 */       if (this.oracleId == 0)
      {
/* 234 */         this.oracleId = -1;
      }
      
/* 237 */       if ((DEFAULT_CHARSET != null) && ((this.oracleId == -1) || (this.oracleId == DEFAULT_CHARSET.getOracleId())))
      {
/* 240 */         this.charSet = DEFAULT_CHARSET;
      } else {
/* 242 */         this.charSet = CharacterSet.make(this.oracleId);
      }
    }
/* 245 */     return this.charSet;
  }
  
  public int oracleId()
  {
/* 257 */     return this.oracleId;
  }
  
  public String getString()
    throws SQLException
  {
/* 272 */     return getCharacterSet().toString(shareBytes(), 0, (int)getLength());
  }
  
  public String getStringWithReplacement()
  {
/* 291 */     byte[] arrayOfByte = shareBytes();
/* 292 */     return getCharacterSet().toStringWithReplacement(arrayOfByte, 0, arrayOfByte.length);
  }
  
  public String toString()
  {
/* 305 */     return getStringWithReplacement();
  }
  
  public boolean equals(Object paramObject)
  {
/* 316 */     return ((paramObject instanceof CHAR)) && (getCharacterSet().equals(((CHAR)paramObject).getCharacterSet())) && (super.equals(paramObject));
  }
  
  void setValue(byte[] paramArrayOfByte, CharacterSet paramCharacterSet)
  {
/* 334 */     this.charSet = (paramCharacterSet == null ? DEFAULT_CHARSET : paramCharacterSet);
/* 335 */     this.oracleId = this.charSet.getOracleId();
    
/* 341 */     setShareBytes(paramArrayOfByte == null ? empty : paramArrayOfByte);
  }
  
/* 345 */   private static final byte[] empty = new byte[0];
  
  public Object toJdbc()
    throws SQLException
  {
/* 364 */     return stringValue();
  }
  
  public boolean isConvertibleTo(Class paramClass)
  {
/* 382 */     String str = paramClass.getName();
/* 383 */     return (str.compareTo("java.lang.String") == 0) || (str.compareTo("java.lang.Long") == 0) || (str.compareTo("java.math.BigDecimal") == 0) || (str.compareTo("java.io.InputStream") == 0) || (str.compareTo("java.sql.Date") == 0) || (str.compareTo("java.sql.Time") == 0) || (str.compareTo("java.sql.Timestamp") == 0) || (str.compareTo("java.io.Reader") == 0);
  }
  
  public String stringValue()
  {
/* 402 */     return toString();
  }
  
  public boolean booleanValue()
    throws SQLException
  {
/* 424 */     String str = stringValue();
/* 425 */     if (str == null)
    {
/* 427 */       return false;
    }
/* 429 */     str = str.trim();
    try
    {
/* 432 */       BigDecimal localBigDecimal = new BigDecimal(str);
/* 433 */       return localBigDecimal.signum() != 0;
    }
    catch (NumberFormatException localNumberFormatException)
    {
/* 438 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 439 */       localSQLException.fillInStackTrace();
/* 440 */       throw localSQLException;
    }
  }
  
  public int intValue()
    throws SQLException
  {
/* 455 */     long l = longValue();
    
/* 457 */     if ((l > 2147483647L) || (l < -2147483648L))
    {
/* 460 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 26);
/* 461 */       localSQLException.fillInStackTrace();
/* 462 */       throw localSQLException;
    }
    
/* 465 */     return (int)l;
  }
  
  public long longValue()
    throws SQLException
  {
/* 478 */     long l = 0L;
    
    try
    {
/* 482 */       l = Long.valueOf(stringValue().trim()).longValue();
    }
    catch (NumberFormatException localNumberFormatException)
    {
/* 487 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 488 */       localSQLException.fillInStackTrace();
/* 489 */       throw localSQLException;
    }
    
/* 492 */     return l;
  }
  
  public float floatValue()
    throws SQLException
  {
/* 505 */     float f = 0.0F;
    
    try
    {
/* 509 */       f = Float.valueOf(stringValue().trim()).floatValue();
    }
    catch (NumberFormatException localNumberFormatException)
    {
/* 514 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 515 */       localSQLException.fillInStackTrace();
/* 516 */       throw localSQLException;
    }
    
/* 519 */     return f;
  }
  
  public double doubleValue()
    throws SQLException
  {
/* 532 */     double d = 0.0D;
    
    try
    {
/* 536 */       d = Double.valueOf(stringValue().trim()).doubleValue();
    }
    catch (NumberFormatException localNumberFormatException)
    {
/* 541 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 542 */       localSQLException.fillInStackTrace();
/* 543 */       throw localSQLException;
    }
    
/* 546 */     return d;
  }
  
  public byte byteValue()
    throws SQLException
  {
/* 559 */     long l = longValue();
    
/* 561 */     if ((l > 127L) || (l < -128L))
    {
/* 564 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 26);
/* 565 */       localSQLException.fillInStackTrace();
/* 566 */       throw localSQLException;
    }
    
/* 570 */     return (byte)(int)l;
  }
  
  public Date dateValue()
    throws SQLException
  {
/* 583 */     return Date.valueOf(stringValue().trim());
  }
  
  public Time timeValue()
    throws SQLException
  {
/* 596 */     return Time.valueOf(stringValue().trim());
  }
  
  public Timestamp timestampValue()
    throws SQLException
  {
/* 609 */     return Timestamp.valueOf(stringValue().trim());
  }
  
  public BigDecimal bigDecimalValue()
    throws SQLException
  {
/* 621 */     BigDecimal localBigDecimal = null;
    
    try
    {
/* 625 */       localBigDecimal = new BigDecimal(stringValue().trim());
    }
    catch (NumberFormatException localNumberFormatException)
    {
/* 630 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "bigDecimalValue");
/* 631 */       localSQLException.fillInStackTrace();
/* 632 */       throw localSQLException;
    }
    
/* 635 */     return localBigDecimal;
  }
  
  public Reader characterStreamValue()
    throws SQLException
  {
/* 648 */     return new StringReader(getString());
  }
  
  public InputStream asciiStreamValue()
    throws SQLException
  {
/* 661 */     return getStream();
  }
  
  public InputStream binaryStreamValue()
    throws SQLException
  {
/* 674 */     return getStream();
  }
  
  public Object makeJdbcArray(int paramInt)
  {
/* 697 */     return new String[paramInt];
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 712 */     return null;
  }
  
/* 807 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CHAR.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */