package oracle.jdbc.driver;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;
import oracle.sql.CHAR;
import oracle.sql.CharacterSet;
import oracle.sql.Datum;
abstract class CharCommonAccessor
  extends Accessor
{
  int internalMaxLengthNewer;
  int internalMaxLengthOlder;
  static final int MAX_NB_CHAR_PLSQL = 32766;
  
  void setOffsets(int paramInt)
  {
/*  42 */     this.columnIndex = this.statement.defineCharSubRange;
/*  43 */     this.statement.defineCharSubRange = (this.columnIndex + paramInt * this.charLength);
  }
  
  void init(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, int paramInt3, short paramShort, int paramInt4, boolean paramBoolean, int paramInt5, int paramInt6)
    throws SQLException
  {
/*  52 */     if (paramBoolean)
    {
/*  54 */       if (paramInt1 != 23) {
/*  55 */         paramInt1 = 1;
      }
/*  57 */       if ((paramOracleStatement.maxFieldSize > 0) && ((paramInt3 == -1) || (paramInt3 < paramOracleStatement.maxFieldSize))) {
/*  58 */         paramInt3 = paramOracleStatement.maxFieldSize;
      }
    }
/*  61 */     init(paramOracleStatement, paramInt1, paramInt2, paramShort, paramBoolean);
    
/*  64 */     if ((paramBoolean) && (paramOracleStatement.connection.defaultnchar)) {
/*  65 */       this.formOfUse = 2;
    }
/*  67 */     this.internalMaxLengthNewer = paramInt5;
/*  68 */     this.internalMaxLengthOlder = paramInt6;
    
/*  70 */     initForDataAccess(paramInt4, paramInt3, null);
  }
  
  void init(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, short paramShort, int paramInt9, int paramInt10)
    throws SQLException
  {
/*  80 */     init(paramOracleStatement, paramInt1, paramInt2, paramShort, false);
/*  81 */     initForDescribe(paramInt1, paramInt3, paramBoolean, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramShort, null);
    
/*  84 */     int i = paramOracleStatement.maxFieldSize;
    
/*  86 */     if ((i != 0) && (i <= paramInt3)) {
/*  87 */       paramInt3 = i;
    }
/*  89 */     this.internalMaxLengthNewer = paramInt9;
/*  90 */     this.internalMaxLengthOlder = paramInt10;
    
/*  92 */     initForDataAccess(0, paramInt3, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/* 100 */     if (paramInt1 != 0) {
/* 101 */       this.externalType = paramInt1;
    }
/* 103 */     if (this.statement.connection.getVersionNumber() >= 8000) {
/* 104 */       this.internalTypeMaxLength = this.internalMaxLengthNewer;
    } else {
/* 106 */       this.internalTypeMaxLength = this.internalMaxLengthOlder;
    }
/* 108 */     if (paramInt2 == 0) {
/* 109 */       this.internalTypeMaxLength = 0;
    }
/* 111 */     else if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/* 112 */       this.internalTypeMaxLength = paramInt2;
    }
    
/* 115 */     this.charLength = (this.isNullByDescribe ? 0 : this.internalTypeMaxLength + 1);
  }
  
  int getInt(int paramInt)
    throws SQLException
  {
/* 123 */     int i = 0;
    
/* 125 */     if (this.rowSpaceIndicator == null)
    {
/* 129 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 130 */       localSQLException1.fillInStackTrace();
/* 131 */       throw localSQLException1;
    }
    
/* 137 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
      try
      {
/* 141 */         i = Integer.parseInt(getString(paramInt).trim());
      }
      catch (NumberFormatException localNumberFormatException)
      {
/* 146 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 147 */         localSQLException2.fillInStackTrace();
/* 148 */         throw localSQLException2;
      }
    }
    
/* 153 */     return i;
  }
  
  boolean getBoolean(int paramInt)
    throws SQLException
  {
/* 170 */     String str = getString(paramInt);
/* 171 */     if (str == null)
    {
/* 173 */       return false;
    }
/* 175 */     str = str.trim();
    try
    {
/* 178 */       BigDecimal localBigDecimal = new BigDecimal(str);
/* 179 */       return localBigDecimal.signum() != 0;
    }
    catch (NumberFormatException localNumberFormatException)
    {
/* 184 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 185 */       localSQLException.fillInStackTrace();
/* 186 */       throw localSQLException;
    }
  }
  
  short getShort(int paramInt)
    throws SQLException
  {
/* 197 */     short s = 0;
    
/* 199 */     if (this.rowSpaceIndicator == null)
    {
/* 203 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 204 */       localSQLException1.fillInStackTrace();
/* 205 */       throw localSQLException1;
    }
    
/* 211 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
      try
      {
/* 215 */         s = Short.parseShort(getString(paramInt).trim());
      }
      catch (NumberFormatException localNumberFormatException)
      {
/* 220 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 221 */         localSQLException2.fillInStackTrace();
/* 222 */         throw localSQLException2;
      }
    }
    
/* 227 */     return s;
  }
  
  byte getByte(int paramInt)
    throws SQLException
  {
/* 235 */     byte b = 0;
    
/* 237 */     if (this.rowSpaceIndicator == null)
    {
/* 241 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 242 */       localSQLException1.fillInStackTrace();
/* 243 */       throw localSQLException1;
    }
    
/* 249 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
      try
      {
/* 253 */         b = Byte.parseByte(getString(paramInt).trim());
      }
      catch (NumberFormatException localNumberFormatException)
      {
/* 258 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 259 */         localSQLException2.fillInStackTrace();
/* 260 */         throw localSQLException2;
      }
    }
    
/* 265 */     return b;
  }
  
  long getLong(int paramInt)
    throws SQLException
  {
/* 273 */     long l = 0L;
    
/* 275 */     if (this.rowSpaceIndicator == null)
    {
/* 279 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 280 */       localSQLException1.fillInStackTrace();
/* 281 */       throw localSQLException1;
    }
    
/* 287 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
      try
      {
/* 291 */         l = Long.parseLong(getString(paramInt).trim());
      }
      catch (NumberFormatException localNumberFormatException)
      {
/* 296 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 297 */         localSQLException2.fillInStackTrace();
/* 298 */         throw localSQLException2;
      }
    }
    
/* 303 */     return l;
  }
  
  float getFloat(int paramInt)
    throws SQLException
  {
/* 311 */     float f = 0.0F;
    
/* 313 */     if (this.rowSpaceIndicator == null)
    {
/* 317 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 318 */       localSQLException1.fillInStackTrace();
/* 319 */       throw localSQLException1;
    }
    
/* 325 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
      try
      {
/* 329 */         f = Float.parseFloat(getString(paramInt).trim());
      }
      catch (NumberFormatException localNumberFormatException)
      {
/* 334 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 335 */         localSQLException2.fillInStackTrace();
/* 336 */         throw localSQLException2;
      }
    }
    
/* 341 */     return f;
  }
  
  double getDouble(int paramInt)
    throws SQLException
  {
/* 349 */     double d = 0.0D;
    
/* 351 */     if (this.rowSpaceIndicator == null)
    {
/* 355 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 356 */       localSQLException1.fillInStackTrace();
/* 357 */       throw localSQLException1;
    }
    
/* 363 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
      try
      {
/* 367 */         d = Double.parseDouble(getString(paramInt).trim());
      }
      catch (NumberFormatException localNumberFormatException)
      {
/* 372 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 373 */         localSQLException2.fillInStackTrace();
/* 374 */         throw localSQLException2;
      }
    }
    
/* 379 */     return d;
  }
  
  BigDecimal getBigDecimal(int paramInt)
    throws SQLException
  {
/* 387 */     BigDecimal localBigDecimal = null;
    Object localObject;
/* 389 */     if (this.rowSpaceIndicator == null)
    {
/* 393 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 394 */       ((SQLException)localObject).fillInStackTrace();
/* 395 */       throw ((Throwable)localObject);
    }
    
/* 401 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
      try
      {
/* 405 */         localObject = getString(paramInt);
        
/* 407 */         if (localObject != null) {
/* 408 */           localBigDecimal = new BigDecimal(((String)localObject).trim());
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
/* 413 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 414 */         localSQLException.fillInStackTrace();
/* 415 */         throw localSQLException;
      }
    }
    
/* 420 */     return localBigDecimal;
  }
  
  BigDecimal getBigDecimal(int paramInt1, int paramInt2)
    throws SQLException
  {
/* 428 */     BigDecimal localBigDecimal = getBigDecimal(paramInt1);
    
/* 430 */     if (localBigDecimal != null) {
/* 431 */       localBigDecimal.setScale(paramInt2, 6);
    }
/* 433 */     return localBigDecimal;
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 441 */     String str = null;
    
/* 443 */     if (this.rowSpaceIndicator == null)
    {
/* 447 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 448 */       localSQLException.fillInStackTrace();
/* 449 */       throw localSQLException;
    }
    
/* 455 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 457 */       int i = this.columnIndex + this.charLength * paramInt;
/* 458 */       int j = this.rowSpaceChar[i] >> '\001';
      
/* 460 */       if (j > this.internalTypeMaxLength) {
/* 461 */         j = this.internalTypeMaxLength;
      }
/* 463 */       str = new String(this.rowSpaceChar, i + 1, j);
    }
    
/* 466 */     return str;
  }
  
  Date getDate(int paramInt)
    throws SQLException
  {
/* 474 */     Date localDate = null;
    
/* 476 */     if (this.rowSpaceIndicator == null)
    {
/* 480 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 481 */       localSQLException.fillInStackTrace();
/* 482 */       throw localSQLException;
    }
    
/* 488 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 490 */       localDate = Date.valueOf(getString(paramInt).trim());
    }
    
/* 493 */     return localDate;
  }
  
  Time getTime(int paramInt)
    throws SQLException
  {
/* 501 */     Time localTime = null;
    
/* 503 */     if (this.rowSpaceIndicator == null)
    {
/* 507 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 508 */       localSQLException.fillInStackTrace();
/* 509 */       throw localSQLException;
    }
    
/* 515 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 517 */       localTime = Time.valueOf(getString(paramInt).trim());
    }
    
/* 520 */     return localTime;
  }
  
  Timestamp getTimestamp(int paramInt)
    throws SQLException
  {
/* 528 */     Timestamp localTimestamp = null;
    
/* 530 */     if (this.rowSpaceIndicator == null)
    {
/* 534 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 535 */       localSQLException.fillInStackTrace();
/* 536 */       throw localSQLException;
    }
    
/* 542 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 544 */       localTimestamp = Timestamp.valueOf(getString(paramInt).trim());
    }
    
/* 547 */     return localTimestamp;
  }
  
  byte[] getBytes(int paramInt)
    throws SQLException
  {
/* 560 */     return getBytesInternal(paramInt);
  }
  
  byte[] getBytesInternal(int paramInt) throws SQLException
  {
/* 565 */     byte[] arrayOfByte1 = null;
    
/* 567 */     if (this.rowSpaceIndicator == null)
    {
/* 571 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 572 */       localSQLException.fillInStackTrace();
/* 573 */       throw localSQLException;
    }
    
/* 579 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 581 */       int i = this.columnIndex + this.charLength * paramInt;
/* 582 */       int j = this.rowSpaceChar[i] >> '\001';
      
/* 584 */       if (j > this.internalTypeMaxLength) {
/* 585 */         j = this.internalTypeMaxLength;
      }
/* 587 */       DBConversion localDBConversion = this.statement.connection.conversion;
      
/* 591 */       byte[] arrayOfByte2 = new byte[j * 6];
/* 592 */       int k = this.formOfUse == 2 ? localDBConversion.javaCharsToNCHARBytes(this.rowSpaceChar, i + 1, arrayOfByte2, 0, j) : localDBConversion.javaCharsToCHARBytes(this.rowSpaceChar, i + 1, arrayOfByte2, 0, j);
      
/* 598 */       arrayOfByte1 = new byte[k];
      
/* 600 */       System.arraycopy(arrayOfByte2, 0, arrayOfByte1, 0, k);
    }
    
/* 603 */     return arrayOfByte1;
  }
  
  InputStream getAsciiStream(int paramInt)
    throws SQLException
  {
/* 620 */     InputStream localInputStream = null;
    
/* 622 */     if (this.rowSpaceIndicator == null)
    {
/* 626 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 627 */       localSQLException.fillInStackTrace();
/* 628 */       throw localSQLException;
    }
    
/* 633 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 635 */       int i = this.columnIndex + this.charLength * paramInt;
/* 636 */       int j = this.rowSpaceChar[i] >> '\001';
      
/* 638 */       if (j > this.internalTypeMaxLength) {
/* 639 */         j = this.internalTypeMaxLength;
      }
/* 641 */       PhysicalConnection localPhysicalConnection = this.statement.connection;
      
/* 643 */       localInputStream = localPhysicalConnection.conversion.CharsToStream(this.rowSpaceChar, i + 1, j, 10);
    }
    
/* 647 */     return localInputStream;
  }
  
  InputStream getUnicodeStream(int paramInt)
    throws SQLException
  {
/* 661 */     InputStream localInputStream = null;
    
/* 663 */     if (this.rowSpaceIndicator == null)
    {
/* 667 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 668 */       localSQLException.fillInStackTrace();
/* 669 */       throw localSQLException;
    }
    
/* 674 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 676 */       int i = this.columnIndex + this.charLength * paramInt;
/* 677 */       int j = this.rowSpaceChar[i] >> '\001';
      
/* 679 */       if (j > this.internalTypeMaxLength) {
/* 680 */         j = this.internalTypeMaxLength;
      }
/* 682 */       PhysicalConnection localPhysicalConnection = this.statement.connection;
      
/* 684 */       localInputStream = localPhysicalConnection.conversion.CharsToStream(this.rowSpaceChar, i + 1, j << 1, 11);
    }
    
/* 689 */     return localInputStream;
  }
  
  Reader getCharacterStream(int paramInt)
    throws SQLException
  {
/* 703 */     CharArrayReader localCharArrayReader = null;
/* 704 */     if (this.rowSpaceIndicator == null)
    {
/* 708 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 709 */       localSQLException.fillInStackTrace();
/* 710 */       throw localSQLException;
    }
    
/* 715 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 717 */       int i = this.columnIndex + this.charLength * paramInt;
/* 718 */       int j = this.rowSpaceChar[i] >> '\001';
      
/* 720 */       if (j > this.internalTypeMaxLength) {
/* 721 */         j = this.internalTypeMaxLength;
      }
/* 723 */       localCharArrayReader = new CharArrayReader(this.rowSpaceChar, i + 1, j);
    }
/* 725 */     return localCharArrayReader;
  }
  
  InputStream getBinaryStream(int paramInt)
    throws SQLException
  {
/* 739 */     ByteArrayInputStream localByteArrayInputStream = null;
    
/* 741 */     if (this.rowSpaceIndicator == null)
    {
/* 745 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 746 */       localSQLException.fillInStackTrace();
/* 747 */       throw localSQLException;
    }
    
/* 752 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 754 */       int i = this.columnIndex + this.charLength * paramInt;
/* 755 */       int j = this.rowSpaceChar[i] >> '\001';
      
/* 757 */       if (j > this.internalTypeMaxLength) {
/* 758 */         j = this.internalTypeMaxLength;
      }
/* 760 */       DBConversion localDBConversion = this.statement.connection.conversion;
      
/* 764 */       byte[] arrayOfByte = new byte[j * 6];
/* 765 */       int k = this.formOfUse == 2 ? localDBConversion.javaCharsToNCHARBytes(this.rowSpaceChar, i + 1, arrayOfByte, 0, j) : localDBConversion.javaCharsToCHARBytes(this.rowSpaceChar, i + 1, arrayOfByte, 0, j);
      
/* 771 */       localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte, 0, k);
    }
    
/* 774 */     return localByteArrayInputStream;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 788 */     return getString(paramInt);
  }
  
  Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
/* 803 */     return getString(paramInt);
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 817 */     return getCHAR(paramInt);
  }
  
  CHAR getCHAR(int paramInt)
    throws SQLException
  {
/* 831 */     byte[] arrayOfByte = getBytesInternal(paramInt);
    
/* 834 */     if ((arrayOfByte == null) || (arrayOfByte.length == 0))
    {
/* 836 */       return null;
    }
    CharacterSet localCharacterSet;
/* 839 */     if (this.formOfUse == 2)
    {
/* 841 */       localCharacterSet = this.statement.connection.conversion.getDriverNCharSetObj();
    }
    else
    {
/* 845 */       localCharacterSet = this.statement.connection.conversion.getDriverCharSetObj();
    }
    
/* 848 */     return new CHAR(arrayOfByte, localCharacterSet);
  }
  
  URL getURL(int paramInt)
    throws SQLException
  {
/* 862 */     URL localURL = null;
    
/* 864 */     if (this.rowSpaceIndicator == null)
    {
/* 868 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 869 */       localSQLException1.fillInStackTrace();
/* 870 */       throw localSQLException1;
    }
    
/* 876 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
      try
      {
/* 880 */         localURL = new URL(getString(paramInt));
      }
      catch (MalformedURLException localMalformedURLException)
      {
/* 885 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 136);
/* 886 */         localSQLException2.fillInStackTrace();
/* 887 */         throw localSQLException2;
      }
    }
    
/* 892 */     return localURL;
  }
  
  byte[] getBytesFromHexChars(int paramInt)
    throws SQLException
  {
/* 899 */     byte[] arrayOfByte = null;
    
/* 901 */     if (this.rowSpaceIndicator == null)
    {
/* 905 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 906 */       localSQLException.fillInStackTrace();
/* 907 */       throw localSQLException;
    }
    
/* 913 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 915 */       int i = this.columnIndex + this.charLength * paramInt;
/* 916 */       int j = this.rowSpaceChar[i] >> '\001';
      
/* 918 */       if (j > this.internalTypeMaxLength) {
/* 919 */         j = this.internalTypeMaxLength;
      }
/* 921 */       arrayOfByte = this.statement.connection.conversion.hexChars2Bytes(this.rowSpaceChar, i + 1, j);
    }
    
/* 924 */     return arrayOfByte;
  }
  
  long updateChecksum(long paramLong, int paramInt)
    throws SQLException
  {
/* 930 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1)
    {
/* 932 */       paramLong = CRC64.updateChecksum(paramLong, NULL_DATA_BYTES, 0, NULL_DATA_BYTES.length);
    }
    else
    {
/* 938 */       int i = this.columnIndex + this.charLength * paramInt;
/* 939 */       int j = this.rowSpaceChar[i] >> '\001';
      
/* 941 */       paramLong = CRC64.updateChecksum(paramLong, this.rowSpaceChar, i + 1, j);
    }
    
/* 947 */     return paramLong;
  }
  
/* 951 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/CharCommonAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */