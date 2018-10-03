package oracle.jdbc.driver;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import oracle.sql.DATE;
import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPTZ;
abstract class DateTimeCommonAccessor
  extends Accessor
{
  static final int GREGORIAN_CUTOVER_YEAR = 1582;
  static final long GREGORIAN_CUTOVER = -12219292800000L;
  static final int JAN_1_1_JULIAN_DAY = 1721426;
  static final int EPOCH_JULIAN_DAY = 2440588;
  static final int ONE_SECOND = 1000;
  static final int ONE_MINUTE = 60000;
  static final int ONE_HOUR = 3600000;
  static final long ONE_DAY = 86400000L;
/*  40 */   static final int[] NUM_DAYS = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
  
/*  45 */   static final int[] LEAP_NUM_DAYS = { 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335 };
  
  static final int ORACLE_CENTURY = 0;
  
  static final int ORACLE_YEAR = 1;
  
  static final int ORACLE_MONTH = 2;
  
  static final int ORACLE_DAY = 3;
  
  static final int ORACLE_HOUR = 4;
  
  static final int ORACLE_MIN = 5;
  
  static final int ORACLE_SEC = 6;
  
  static final int ORACLE_NANO1 = 7;
  static final int ORACLE_NANO2 = 8;
  static final int ORACLE_NANO3 = 9;
  static final int ORACLE_NANO4 = 10;
  static final int ORACLE_TZ1 = 11;
  static final int ORACLE_TZ2 = 12;
  static final int SIZE_DATE = 7;
  static final int MAX_TIMESTAMP_LENGTH = 11;
  static TimeZone epochTimeZone;
  static long epochTimeZoneOffset;
  
  Time getTime(int paramInt)
    throws SQLException
  {
/*  75 */     Time localTime = null;
    
/*  77 */     if (this.rowSpaceIndicator == null)
    {
/*  81 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  82 */       localSQLException.fillInStackTrace();
/*  83 */       throw localSQLException;
    }
    
/*  89 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/*  91 */       int i = this.columnIndex + this.byteLength * paramInt;
      
/*  95 */       TimeZone localTimeZone = this.statement.getDefaultTimeZone();
      
/*  97 */       if (localTimeZone != epochTimeZone)
      {
/*  99 */         epochTimeZoneOffset = calculateEpochOffset(localTimeZone);
/* 100 */         epochTimeZone = localTimeZone;
      }
      
/* 103 */       localTime = new Time(oracleTime(i) - epochTimeZoneOffset);
    }
    
/* 106 */     return localTime;
  }
  
  Date getDate(int paramInt)
    throws SQLException
  {
/* 115 */     return getDate(paramInt, this.statement.getDefaultCalendar());
  }
  
  Date getDate(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
/* 124 */     if (paramCalendar == null) {
/* 125 */       return getDate(paramInt);
    }
/* 127 */     Date localDate = null;
    
/* 129 */     if (this.rowSpaceIndicator == null)
    {
/* 133 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 134 */       localSQLException.fillInStackTrace();
/* 135 */       throw localSQLException;
    }
    
/* 141 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 143 */       int i = this.columnIndex + this.byteLength * paramInt;
/* 144 */       int j = oracleYear(i);
      
/* 146 */       Calendar localCalendar = (Calendar)paramCalendar.clone();
      
/* 148 */       localCalendar.set(j, oracleMonth(i), oracleDay(i), 0, 0, 0);
/* 149 */       localCalendar.set(14, 0);
      
/* 153 */       if ((j > 0) && (localCalendar.isSet(0))) {
/* 154 */         localCalendar.set(0, 1);
      }
/* 156 */       localDate = new Date(localCalendar.getTimeInMillis());
    }
    
/* 159 */     return localDate;
  }
  
  Time getTime(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
/* 168 */     if (paramCalendar == null)
    {
/* 170 */       return getTime(paramInt);
    }
    
/* 173 */     Time localTime = null;
    
/* 175 */     if (this.rowSpaceIndicator == null)
    {
/* 179 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 180 */       localSQLException.fillInStackTrace();
/* 181 */       throw localSQLException;
    }
    
/* 187 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 189 */       int i = this.columnIndex + this.byteLength * paramInt;
/* 190 */       int j = oracleYear(i);
      
/* 192 */       Calendar localCalendar = (Calendar)paramCalendar.clone();
      
/* 194 */       localCalendar.set(1, 1970);
/* 195 */       localCalendar.set(2, 0);
/* 196 */       localCalendar.set(5, 1);
/* 197 */       localCalendar.set(11, oracleHour(i));
/* 198 */       localCalendar.set(12, oracleMin(i));
/* 199 */       localCalendar.set(13, oracleSec(i));
/* 200 */       localCalendar.set(14, 0);
      
/* 204 */       if ((j > 0) && (localCalendar.isSet(0))) {
/* 205 */         localCalendar.set(0, 1);
      }
/* 207 */       localTime = new Time(localCalendar.getTimeInMillis());
    }
    
/* 210 */     return localTime;
  }
  
  Timestamp getTimestamp(int paramInt)
    throws SQLException
  {
/* 218 */     return getTimestamp(paramInt, this.statement.getDefaultCalendar());
  }
  
  Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
/* 227 */     if (paramCalendar == null)
    {
/* 229 */       return getTimestamp(paramInt);
    }
    
/* 232 */     Timestamp localTimestamp = null;
    
/* 234 */     if (this.rowSpaceIndicator == null)
    {
/* 238 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 239 */       localSQLException.fillInStackTrace();
/* 240 */       throw localSQLException;
    }
    
/* 246 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 248 */       int i = this.columnIndex + this.byteLength * paramInt;
/* 249 */       int j = oracleYear(i);
      
/* 251 */       Calendar localCalendar = (Calendar)paramCalendar.clone();
/* 252 */       localCalendar.set(j, oracleMonth(i), oracleDay(i), oracleHour(i), oracleMin(i), oracleSec(i));
      
/* 254 */       localCalendar.set(14, 0);
      
/* 258 */       if ((j > 0) && (localCalendar.isSet(0))) {
/* 259 */         localCalendar.set(0, 1);
      }
/* 261 */       localTimestamp = new Timestamp(localCalendar.getTimeInMillis());
      
/* 263 */       int k = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/* 264 */       if (k >= 11)
      {
/* 266 */         localTimestamp.setNanos(oracleNanos(i));
      }
    }
    
/* 270 */     return localTimestamp;
  }
  
  DATE getDATE(int paramInt)
    throws SQLException
  {
/* 280 */     DATE localDATE = null;
    
/* 282 */     if (this.rowSpaceIndicator == null)
    {
/* 286 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 287 */       localSQLException.fillInStackTrace();
/* 288 */       throw localSQLException;
    }
    
/* 294 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 296 */       int i = this.columnIndex + this.byteLength * paramInt;
/* 297 */       byte[] arrayOfByte = new byte[7];
      
/* 299 */       System.arraycopy(this.rowSpaceByte, i, arrayOfByte, 0, 7);
      
/* 301 */       localDATE = new DATE(arrayOfByte);
    }
    
/* 304 */     return localDATE;
  }
  
  TIMESTAMP getTIMESTAMP(int paramInt)
    throws SQLException
  {
/* 312 */     TIMESTAMP localTIMESTAMP = null;
    
/* 314 */     if (this.rowSpaceIndicator == null)
    {
/* 318 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 319 */       localSQLException.fillInStackTrace();
/* 320 */       throw localSQLException;
    }
    
/* 326 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 328 */       int i = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/* 329 */       int j = this.columnIndex + this.byteLength * paramInt;
/* 330 */       byte[] arrayOfByte = new byte[i];
/* 331 */       System.arraycopy(this.rowSpaceByte, j, arrayOfByte, 0, i);
/* 332 */       localTIMESTAMP = new TIMESTAMP(arrayOfByte);
    }
    
/* 335 */     return localTIMESTAMP;
  }
  
  final int oracleYear(int paramInt)
  {
/* 343 */     int i = ((this.rowSpaceByte[(0 + paramInt)] & 0xFF) - 100) * 100 + (this.rowSpaceByte[(1 + paramInt)] & 0xFF) - 100;
    
/* 347 */     return i <= 0 ? i + 1 : i;
  }
  
  final int oracleMonth(int paramInt)
  {
/* 355 */     return this.rowSpaceByte[(2 + paramInt)] - 1;
  }
  
  final int oracleDay(int paramInt)
  {
/* 363 */     return this.rowSpaceByte[(3 + paramInt)];
  }
  
  final int oracleHour(int paramInt)
  {
/* 371 */     return this.rowSpaceByte[(4 + paramInt)] - 1;
  }
  
  final int oracleMin(int paramInt)
  {
/* 379 */     return this.rowSpaceByte[(5 + paramInt)] - 1;
  }
  
  final int oracleSec(int paramInt)
  {
/* 387 */     return this.rowSpaceByte[(6 + paramInt)] - 1;
  }
  
  final int oracleTZ1(int paramInt)
  {
/* 395 */     return this.rowSpaceByte[(11 + paramInt)];
  }
  
  final int oracleTZ2(int paramInt)
  {
/* 403 */     return this.rowSpaceByte[(12 + paramInt)];
  }
  
  final int oracleTime(int paramInt)
  {
/* 410 */     int i = oracleHour(paramInt);
    
/* 412 */     i *= 60;
/* 413 */     i += oracleMin(paramInt);
/* 414 */     i *= 60;
/* 415 */     i += oracleSec(paramInt);
/* 416 */     i *= 1000;
    
/* 418 */     return i;
  }
  
  final int oracleNanos(int paramInt)
  {
/* 425 */     int i = (this.rowSpaceByte[(7 + paramInt)] & 0xFF) << 24;
    
/* 427 */     i |= (this.rowSpaceByte[(8 + paramInt)] & 0xFF) << 16;
/* 428 */     i |= (this.rowSpaceByte[(9 + paramInt)] & 0xFF) << 8;
/* 429 */     i |= this.rowSpaceByte[(10 + paramInt)] & 0xFF & 0xFF;
    
/* 431 */     return i;
  }
  
  static final long computeJulianDay(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
/* 439 */     int i = paramInt1 % 4 == 0 ? 1 : 0;
/* 440 */     int j = paramInt1 - 1;
/* 441 */     long l = 365L * j + floorDivide(j, 4L) + 1721423L;
    
/* 443 */     if (paramBoolean)
    {
/* 445 */       i = (i != 0) && ((paramInt1 % 100 != 0) || (paramInt1 % 400 == 0)) ? 1 : 0;
      
/* 448 */       l += floorDivide(j, 400L) - floorDivide(j, 100L) + 2L;
    }
    
/* 455 */     return l + paramInt3 + (i != 0 ? LEAP_NUM_DAYS[paramInt2] : NUM_DAYS[paramInt2]);
  }
  
  static final long floorDivide(long paramLong1, long paramLong2)
  {
/* 462 */     return paramLong1 >= 0L ? paramLong1 / paramLong2 : (paramLong1 + 1L) / paramLong2 - 1L;
  }
  
  static final long julianDayToMillis(long paramLong)
  {
/* 470 */     return (paramLong - 2440588L) * 86400000L;
  }
  
  static final long zoneOffset(TimeZone paramTimeZone, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
/* 480 */     return paramTimeZone.getOffset(paramInt1 < 0 ? 0 : 1, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
  }
  
  static long getMillis(int paramInt1, int paramInt2, int paramInt3, int paramInt4, TimeZone paramTimeZone)
  {
/* 501 */     boolean bool = paramInt1 >= 1582;
/* 502 */     long l1 = computeJulianDay(bool, paramInt1, paramInt2, paramInt3);
/* 503 */     long l2 = (l1 - 2440588L) * 86400000L;
    
/* 507 */     if (bool != l2 >= -12219292800000L)
    {
/* 509 */       l1 = computeJulianDay(!bool, paramInt1, paramInt2, paramInt3);
/* 510 */       l2 = (l1 - 2440588L) * 86400000L;
    }
    
/* 515 */     l2 += paramInt4;
    
/* 520 */     return l2 - zoneOffset(paramTimeZone, paramInt1, paramInt2, paramInt3, julianDayToDayOfWeek(l1), paramInt4);
  }
  
  static final int julianDayToDayOfWeek(long paramLong)
  {
/* 538 */     int i = (int)((paramLong + 1L) % 7L);
    
/* 540 */     return i + (i < 0 ? 8 : 1);
  }
  
  static long calculateEpochOffset(TimeZone paramTimeZone)
  {
/* 559 */     return zoneOffset(paramTimeZone, 1970, 0, 1, 5, 0);
  }
  
  String toText(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, String paramString)
    throws SQLException
  {
/* 574 */     return TIMESTAMPTZ.toString(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramString);
  }
  
/* 587 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/DateTimeCommonAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */