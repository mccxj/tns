package oracle.jdbc.driver;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;
import oracle.sql.DATE;
import oracle.sql.Datum;
import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPTZ;
import oracle.sql.TIMEZONETAB;
import oracle.sql.ZONEIDMAP;
class TimestamptzAccessor
  extends DateTimeCommonAccessor
{
  static final int maxLength = 13;
/*  32 */   TimestampTzConverter tstzConverter = null;
  
  TimestamptzAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
    throws SQLException
  {
/*  38 */     init(paramOracleStatement, 181, 181, paramShort, paramBoolean);
/*  39 */     initForDataAccess(paramInt2, paramInt1, null);
    
/*  41 */     if (this.statement.connection.timestamptzInGmt) {
/*  42 */       this.tstzConverter = new GmtTimestampTzConverter();
    } else {
/*  44 */       this.tstzConverter = new OldTimestampTzConverter();
    }
  }
  
  TimestamptzAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
    throws SQLException
  {
/*  53 */     init(paramOracleStatement, 181, 181, paramShort, false);
/*  54 */     initForDescribe(181, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, null);
    
/*  56 */     initForDataAccess(0, paramInt1, null);
    
/*  58 */     if (this.statement.connection.timestamptzInGmt) {
/*  59 */       this.tstzConverter = new GmtTimestampTzConverter();
    } else {
/*  61 */       this.tstzConverter = new OldTimestampTzConverter();
    }
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  69 */     if (paramInt1 != 0) {
/*  70 */       this.externalType = paramInt1;
    }
/*  72 */     this.internalTypeMaxLength = 13;
    
/*  74 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*  75 */       this.internalTypeMaxLength = paramInt2;
    }
/*  77 */     this.byteLength = this.internalTypeMaxLength;
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/*  85 */     if (this.rowSpaceIndicator == null)
    {
/*  89 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  90 */       localSQLException.fillInStackTrace();
/*  91 */       throw localSQLException;
    }
    
/*  97 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  98 */       return null;
    }
/* 100 */     int i = this.columnIndex + this.byteLength * paramInt;
    
/* 103 */     int j = 0;
    String str;
/* 105 */     if ((oracleTZ1(i) & REGIONIDBIT) != 0)
    {
/* 107 */       j = getHighOrderbits(oracleTZ1(i));
/* 108 */       j += getLowOrderbits(oracleTZ2(i));
      
/* 111 */       TIMEZONETAB localTIMEZONETAB1 = this.statement.connection.getTIMEZONETAB();
/* 112 */       if (localTIMEZONETAB1.checkID(j)) {
/* 113 */         localTIMEZONETAB1.updateTable(this.statement.connection, j);
      }
/* 115 */       str = ZONEIDMAP.getRegion(j);
    }
    else
    {
/* 119 */       int k = oracleTZ1(i) - OFFSET_HOUR;
/* 120 */       m = oracleTZ2(i) - OFFSET_MINUTE;
      
/* 122 */       str = "GMT" + (k < 0 ? "-" : "+") + Math.abs(k) + ":" + (m < 10 ? "0" : "") + m;
    }
    
/* 130 */     Calendar localCalendar = this.statement.getGMTCalendar();
    
/* 132 */     int m = oracleYear(i);
    
/* 134 */     localCalendar.set(1, m);
/* 135 */     localCalendar.set(2, oracleMonth(i));
/* 136 */     localCalendar.set(5, oracleDay(i));
/* 137 */     localCalendar.set(11, oracleHour(i));
/* 138 */     localCalendar.set(12, oracleMin(i));
/* 139 */     localCalendar.set(13, oracleSec(i));
/* 140 */     localCalendar.set(14, 0);
    
/* 142 */     if ((oracleTZ1(i) & REGIONIDBIT) != 0)
    {
/* 144 */       TIMEZONETAB localTIMEZONETAB2 = this.statement.connection.getTIMEZONETAB();
      
/* 147 */       i1 = localTIMEZONETAB2.getOffset(localCalendar, j);
      
/* 150 */       localCalendar.add(14, i1);
    }
    else
    {
/* 154 */       localCalendar.add(10, oracleTZ1(i) - OFFSET_HOUR);
/* 155 */       localCalendar.add(12, oracleTZ2(i) - OFFSET_MINUTE);
    }
    
/* 159 */     m = localCalendar.get(1);
    
/* 161 */     int n = localCalendar.get(2) + 1;
/* 162 */     int i1 = localCalendar.get(5);
/* 163 */     int i2 = localCalendar.get(11);
/* 164 */     int i3 = localCalendar.get(12);
/* 165 */     int i4 = localCalendar.get(13);
/* 166 */     boolean bool = i2 < 12;
/* 167 */     if ((str.length() > 3) && (str.startsWith("GMT"))) {
/* 168 */       str = str.substring(3);
    }
/* 170 */     int i5 = oracleNanos(i);
    
/* 172 */     return toText(m, n, i1, i2, i3, i4, i5, bool, str);
  }
  
  java.sql.Date getDate(int paramInt)
    throws SQLException
  {
/* 179 */     return this.tstzConverter.getDate(paramInt);
  }
  
  java.sql.Date getDate(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
/* 187 */     return getDate(paramInt);
  }
  
  Time getTime(int paramInt)
    throws SQLException
  {
/* 194 */     return this.tstzConverter.getTime(paramInt);
  }
  
  Time getTime(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
/* 202 */     return getTime(paramInt);
  }
  
  Timestamp getTimestamp(int paramInt)
    throws SQLException
  {
/* 209 */     return this.tstzConverter.getTimestamp(paramInt);
  }
  
  Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
/* 217 */     return getTimestamp(paramInt);
  }
  
  Object getObject(int paramInt) throws SQLException
  {
/* 222 */     return this.tstzConverter.getObject(paramInt);
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 228 */     return this.tstzConverter.getOracleObject(paramInt);
  }
  
  DATE getDATE(int paramInt) throws SQLException
  {
/* 233 */     TIMESTAMPTZ localTIMESTAMPTZ = this.tstzConverter.getTIMESTAMPTZ(paramInt);
/* 234 */     return TIMESTAMPTZ.toDATE(this.statement.connection, localTIMESTAMPTZ.getBytes());
  }
  
  TIMESTAMP getTIMESTAMP(int paramInt) throws SQLException
  {
/* 239 */     TIMESTAMPTZ localTIMESTAMPTZ = this.tstzConverter.getTIMESTAMPTZ(paramInt);
/* 240 */     return TIMESTAMPTZ.toTIMESTAMP(this.statement.connection, localTIMESTAMPTZ.getBytes());
  }
  
  TIMESTAMPTZ getTIMESTAMPTZ(int paramInt) throws SQLException
  {
/* 245 */     return this.tstzConverter.getTIMESTAMPTZ(paramInt);
  }
  
/* 251 */   static int OFFSET_HOUR = 20;
/* 252 */   static int OFFSET_MINUTE = 60;
  
/* 255 */   static byte REGIONIDBIT = Byte.MIN_VALUE;
  
  static int setHighOrderbits(int paramInt)
  {
/* 265 */     return (paramInt & 0x1FC0) >> 6;
  }
  
  static int setLowOrderbits(int paramInt)
  {
/* 273 */     return (paramInt & 0x3F) << 2;
  }
  
  static int getHighOrderbits(int paramInt)
  {
/* 281 */     return (paramInt & 0x7F) << 6;
  }
  
  static int getLowOrderbits(int paramInt)
  {
/* 288 */     return (paramInt & 0xFC) >> 2;
  }
  
  class OldTimestampTzConverter
    extends TimestamptzAccessor.TimestampTzConverter
  {
    OldTimestampTzConverter()
    {
/* 297 */       super();
    }
    
    java.sql.Date getDate(int paramInt)
      throws SQLException
    {
/* 304 */       if (TimestamptzAccessor.this.rowSpaceIndicator == null)
      {
/* 308 */         SQLException localSQLException = DatabaseError.createSqlException(TimestamptzAccessor.this.getConnectionDuringExceptionHandling(), 21);
/* 309 */         localSQLException.fillInStackTrace();
/* 310 */         throw localSQLException;
      }
      
/* 316 */       if (TimestamptzAccessor.this.rowSpaceIndicator[(TimestamptzAccessor.this.indicatorIndex + paramInt)] == -1) {
/* 317 */         return null;
      }
/* 319 */       int i = TimestamptzAccessor.this.columnIndex + TimestamptzAccessor.this.byteLength * paramInt;
      
/* 321 */       TimeZone localTimeZone = TimestamptzAccessor.this.statement.getDefaultTimeZone();
/* 322 */       Calendar localCalendar = Calendar.getInstance(localTimeZone);
      
/* 324 */       int j = TimestamptzAccessor.this.oracleYear(i);
      
/* 326 */       localCalendar.set(1, j);
/* 327 */       localCalendar.set(2, TimestamptzAccessor.this.oracleMonth(i));
/* 328 */       localCalendar.set(5, TimestamptzAccessor.this.oracleDay(i));
/* 329 */       localCalendar.set(11, TimestamptzAccessor.this.oracleHour(i));
/* 330 */       localCalendar.set(12, TimestamptzAccessor.this.oracleMin(i));
/* 331 */       localCalendar.set(13, TimestamptzAccessor.this.oracleSec(i));
/* 332 */       localCalendar.set(14, 0);
      
/* 334 */       if ((TimestamptzAccessor.this.oracleTZ1(i) & TimestamptzAccessor.REGIONIDBIT) != 0)
      {
/* 338 */         int k = TimestamptzAccessor.getHighOrderbits(TimestamptzAccessor.this.oracleTZ1(i));
/* 339 */         k += TimestamptzAccessor.getLowOrderbits(TimestamptzAccessor.this.oracleTZ2(i));
        
/* 341 */         TIMEZONETAB localTIMEZONETAB = TimestamptzAccessor.this.statement.connection.getTIMEZONETAB();
        
/* 343 */         if (localTIMEZONETAB.checkID(k)) {
/* 344 */           localTIMEZONETAB.updateTable(TimestamptzAccessor.this.statement.connection, k);
        }
/* 346 */         int m = localTIMEZONETAB.getOffset(localCalendar, k);
        
/* 348 */         boolean bool1 = localTimeZone.inDaylightTime(localCalendar.getTime());
/* 349 */         boolean bool2 = localTimeZone.inDaylightTime(new java.util.Date(localCalendar.getTimeInMillis() + m));
        
/* 356 */         if ((!bool1) && (bool2))
        {
/* 358 */           localCalendar.add(14, -1 * localTimeZone.getDSTSavings());
        }
/* 366 */         else if ((bool1) && (!bool2))
        {
/* 368 */           localCalendar.add(14, localTimeZone.getDSTSavings());
        }
        
/* 373 */         localCalendar.add(10, m / 3600000);
/* 374 */         localCalendar.add(12, m % 3600000 / 60000);
      }
      else
      {
/* 378 */         localCalendar.add(10, TimestamptzAccessor.this.oracleTZ1(i) - TimestamptzAccessor.OFFSET_HOUR);
/* 379 */         localCalendar.add(12, TimestamptzAccessor.this.oracleTZ2(i) - TimestamptzAccessor.OFFSET_MINUTE);
      }
      
/* 384 */       long l = localCalendar.getTimeInMillis();
      
/* 387 */       return new java.sql.Date(l);
    }
    
    Time getTime(int paramInt)
      throws SQLException
    {
/* 395 */       if (TimestamptzAccessor.this.rowSpaceIndicator == null)
      {
/* 399 */         SQLException localSQLException = DatabaseError.createSqlException(TimestamptzAccessor.this.getConnectionDuringExceptionHandling(), 21);
/* 400 */         localSQLException.fillInStackTrace();
/* 401 */         throw localSQLException;
      }
      
/* 407 */       if (TimestamptzAccessor.this.rowSpaceIndicator[(TimestamptzAccessor.this.indicatorIndex + paramInt)] == -1) {
/* 408 */         return null;
      }
/* 410 */       int i = TimestamptzAccessor.this.columnIndex + TimestamptzAccessor.this.byteLength * paramInt;
      
/* 412 */       TimeZone localTimeZone = TimestamptzAccessor.this.statement.getDefaultTimeZone();
/* 413 */       Calendar localCalendar = Calendar.getInstance(localTimeZone);
      
/* 415 */       int j = TimestamptzAccessor.this.oracleYear(i);
      
/* 417 */       localCalendar.set(1, j);
/* 418 */       localCalendar.set(2, TimestamptzAccessor.this.oracleMonth(i));
/* 419 */       localCalendar.set(5, TimestamptzAccessor.this.oracleDay(i));
/* 420 */       localCalendar.set(11, TimestamptzAccessor.this.oracleHour(i));
/* 421 */       localCalendar.set(12, TimestamptzAccessor.this.oracleMin(i));
/* 422 */       localCalendar.set(13, TimestamptzAccessor.this.oracleSec(i));
/* 423 */       localCalendar.set(14, 0);
      
/* 425 */       if ((TimestamptzAccessor.this.oracleTZ1(i) & TimestamptzAccessor.REGIONIDBIT) != 0)
      {
/* 429 */         int k = TimestamptzAccessor.getHighOrderbits(TimestamptzAccessor.this.oracleTZ1(i));
/* 430 */         k += TimestamptzAccessor.getLowOrderbits(TimestamptzAccessor.this.oracleTZ2(i));
        
/* 432 */         TIMEZONETAB localTIMEZONETAB = TimestamptzAccessor.this.statement.connection.getTIMEZONETAB();
        
/* 434 */         if (localTIMEZONETAB.checkID(k)) {
/* 435 */           localTIMEZONETAB.updateTable(TimestamptzAccessor.this.statement.connection, k);
        }
/* 437 */         int m = localTIMEZONETAB.getOffset(localCalendar, k);
        
/* 439 */         boolean bool1 = localTimeZone.inDaylightTime(localCalendar.getTime());
/* 440 */         boolean bool2 = localTimeZone.inDaylightTime(new java.util.Date(localCalendar.getTimeInMillis() + m));
        
/* 447 */         if ((!bool1) && (bool2))
        {
/* 449 */           localCalendar.add(14, -1 * localTimeZone.getDSTSavings());
        }
/* 457 */         else if ((bool1) && (!bool2))
        {
/* 459 */           localCalendar.add(14, localTimeZone.getDSTSavings());
        }
        
/* 463 */         localCalendar.add(10, m / 3600000);
/* 464 */         localCalendar.add(12, m % 3600000 / 60000);
      }
      else
      {
/* 468 */         localCalendar.add(10, TimestamptzAccessor.this.oracleTZ1(i) - TimestamptzAccessor.OFFSET_HOUR);
/* 469 */         localCalendar.add(12, TimestamptzAccessor.this.oracleTZ2(i) - TimestamptzAccessor.OFFSET_MINUTE);
      }
      
/* 473 */       long l = localCalendar.getTimeInMillis();
      
/* 476 */       return new Time(l);
    }
    
    Timestamp getTimestamp(int paramInt)
      throws SQLException
    {
/* 484 */       if (TimestamptzAccessor.this.rowSpaceIndicator == null)
      {
/* 488 */         SQLException localSQLException = DatabaseError.createSqlException(TimestamptzAccessor.this.getConnectionDuringExceptionHandling(), 21);
/* 489 */         localSQLException.fillInStackTrace();
/* 490 */         throw localSQLException;
      }
      
/* 496 */       if (TimestamptzAccessor.this.rowSpaceIndicator[(TimestamptzAccessor.this.indicatorIndex + paramInt)] == -1) {
/* 497 */         return null;
      }
/* 499 */       int i = TimestamptzAccessor.this.columnIndex + TimestamptzAccessor.this.byteLength * paramInt;
      
/* 501 */       TimeZone localTimeZone = TimestamptzAccessor.this.statement.getDefaultTimeZone();
/* 502 */       Calendar localCalendar1 = Calendar.getInstance(localTimeZone);
/* 503 */       Calendar localCalendar2 = TimestamptzAccessor.this.statement.getGMTCalendar();
      
/* 505 */       int j = TimestamptzAccessor.this.oracleYear(i);
      
/* 507 */       localCalendar1.set(1, j);
/* 508 */       localCalendar1.set(2, TimestamptzAccessor.this.oracleMonth(i));
/* 509 */       localCalendar1.set(5, TimestamptzAccessor.this.oracleDay(i));
/* 510 */       localCalendar1.set(11, TimestamptzAccessor.this.oracleHour(i));
/* 511 */       localCalendar1.set(12, TimestamptzAccessor.this.oracleMin(i));
/* 512 */       localCalendar1.set(13, TimestamptzAccessor.this.oracleSec(i));
/* 513 */       localCalendar1.set(14, 0);
      
/* 515 */       localCalendar2.set(1, j);
/* 516 */       localCalendar2.set(2, TimestamptzAccessor.this.oracleMonth(i));
/* 517 */       localCalendar2.set(5, TimestamptzAccessor.this.oracleDay(i));
/* 518 */       localCalendar2.set(11, TimestamptzAccessor.this.oracleHour(i));
/* 519 */       localCalendar2.set(12, TimestamptzAccessor.this.oracleMin(i));
/* 520 */       localCalendar2.set(13, TimestamptzAccessor.this.oracleSec(i));
/* 521 */       localCalendar2.set(14, 0);
      
/* 523 */       if ((TimestamptzAccessor.this.oracleTZ1(i) & TimestamptzAccessor.REGIONIDBIT) != 0)
      {
/* 527 */         int k = TimestamptzAccessor.getHighOrderbits(TimestamptzAccessor.this.oracleTZ1(i));
/* 528 */         k += TimestamptzAccessor.getLowOrderbits(TimestamptzAccessor.this.oracleTZ2(i));
        
/* 531 */         TIMEZONETAB localTIMEZONETAB = TimestamptzAccessor.this.statement.connection.getTIMEZONETAB();
/* 532 */         if (localTIMEZONETAB.checkID(k)) {
/* 533 */           localTIMEZONETAB.updateTable(TimestamptzAccessor.this.statement.connection, k);
        }
/* 535 */         int m = localTIMEZONETAB.getOffset(localCalendar2, k);
        
/* 537 */         boolean bool1 = localTimeZone.inDaylightTime(localCalendar1.getTime());
/* 538 */         boolean bool2 = localTimeZone.inDaylightTime(new java.util.Date(localCalendar1.getTimeInMillis() + m));
        
/* 545 */         if ((!bool1) && (bool2))
        {
/* 548 */           localCalendar1.add(14, -1 * localTimeZone.getDSTSavings());
        }
/* 556 */         else if ((bool1) && (!bool2))
        {
/* 558 */           localCalendar1.add(14, localTimeZone.getDSTSavings());
        }
        
/* 562 */         localCalendar1.add(10, m / 3600000);
/* 563 */         localCalendar1.add(12, m % 3600000 / 60000);
      }
      else
      {
/* 567 */         localCalendar1.add(10, TimestamptzAccessor.this.oracleTZ1(i) - TimestamptzAccessor.OFFSET_HOUR);
/* 568 */         localCalendar1.add(12, TimestamptzAccessor.this.oracleTZ2(i) - TimestamptzAccessor.OFFSET_MINUTE);
      }
      
/* 572 */       long l = localCalendar1.getTimeInMillis();
      
/* 575 */       Timestamp localTimestamp = new Timestamp(l);
      
/* 578 */       int n = TimestamptzAccessor.this.oracleNanos(i);
      
/* 581 */       localTimestamp.setNanos(n);
      
/* 583 */       return localTimestamp;
    }
    
    TIMESTAMPTZ getTIMESTAMPTZ(int paramInt)
      throws SQLException
    {
/* 591 */       TIMESTAMPTZ localTIMESTAMPTZ = null;
      
/* 593 */       if (TimestamptzAccessor.this.rowSpaceIndicator == null)
      {
/* 597 */         SQLException localSQLException = DatabaseError.createSqlException(TimestamptzAccessor.this.getConnectionDuringExceptionHandling(), 21);
/* 598 */         localSQLException.fillInStackTrace();
/* 599 */         throw localSQLException;
      }
      
/* 605 */       if (TimestamptzAccessor.this.rowSpaceIndicator[(TimestamptzAccessor.this.indicatorIndex + paramInt)] != -1)
      {
/* 607 */         int i = TimestamptzAccessor.this.columnIndex + TimestamptzAccessor.this.byteLength * paramInt;
/* 608 */         byte[] arrayOfByte = new byte[13];
        
/* 610 */         System.arraycopy(TimestamptzAccessor.this.rowSpaceByte, i, arrayOfByte, 0, 13);
        
/* 612 */         localTIMESTAMPTZ = new TIMESTAMPTZ(arrayOfByte);
      }
      
/* 615 */       return localTIMESTAMPTZ;
    }
  }
  
  class GmtTimestampTzConverter
    extends TimestamptzAccessor.TimestampTzConverter
  {
    GmtTimestampTzConverter()
    {
/* 625 */       super();
    }
    
    java.sql.Date getDate(int paramInt)
      throws SQLException
    {
/* 633 */       if (TimestamptzAccessor.this.rowSpaceIndicator == null)
      {
/* 637 */         SQLException localSQLException = DatabaseError.createSqlException(TimestamptzAccessor.this.getConnectionDuringExceptionHandling(), 21);
/* 638 */         localSQLException.fillInStackTrace();
/* 639 */         throw localSQLException;
      }
      
/* 645 */       if (TimestamptzAccessor.this.rowSpaceIndicator[(TimestamptzAccessor.this.indicatorIndex + paramInt)] == -1) {
/* 646 */         return null;
      }
/* 648 */       int i = TimestamptzAccessor.this.columnIndex + TimestamptzAccessor.this.byteLength * paramInt;
      
/* 650 */       Calendar localCalendar = TimestamptzAccessor.this.statement.getGMTCalendar();
      
/* 652 */       int j = TimestamptzAccessor.this.oracleYear(i);
      
/* 654 */       localCalendar.set(1, j);
/* 655 */       localCalendar.set(2, TimestamptzAccessor.this.oracleMonth(i));
/* 656 */       localCalendar.set(5, TimestamptzAccessor.this.oracleDay(i));
/* 657 */       localCalendar.set(11, TimestamptzAccessor.this.oracleHour(i));
/* 658 */       localCalendar.set(12, TimestamptzAccessor.this.oracleMin(i));
/* 659 */       localCalendar.set(13, TimestamptzAccessor.this.oracleSec(i));
/* 660 */       localCalendar.set(14, 0);
      
/* 663 */       long l = localCalendar.getTimeInMillis();
      
/* 666 */       return new java.sql.Date(l);
    }
    
    Time getTime(int paramInt)
      throws SQLException
    {
/* 674 */       if (TimestamptzAccessor.this.rowSpaceIndicator == null)
      {
/* 678 */         SQLException localSQLException = DatabaseError.createSqlException(TimestamptzAccessor.this.getConnectionDuringExceptionHandling(), 21);
/* 679 */         localSQLException.fillInStackTrace();
/* 680 */         throw localSQLException;
      }
      
/* 686 */       if (TimestamptzAccessor.this.rowSpaceIndicator[(TimestamptzAccessor.this.indicatorIndex + paramInt)] == -1) {
/* 687 */         return null;
      }
/* 689 */       int i = TimestamptzAccessor.this.columnIndex + TimestamptzAccessor.this.byteLength * paramInt;
      
/* 691 */       Calendar localCalendar = TimestamptzAccessor.this.statement.getGMTCalendar();
      
/* 693 */       int j = TimestamptzAccessor.this.oracleYear(i);
      
/* 695 */       localCalendar.set(1, j);
/* 696 */       localCalendar.set(2, TimestamptzAccessor.this.oracleMonth(i));
/* 697 */       localCalendar.set(5, TimestamptzAccessor.this.oracleDay(i));
/* 698 */       localCalendar.set(11, TimestamptzAccessor.this.oracleHour(i));
/* 699 */       localCalendar.set(12, TimestamptzAccessor.this.oracleMin(i));
/* 700 */       localCalendar.set(13, TimestamptzAccessor.this.oracleSec(i));
/* 701 */       localCalendar.set(14, 0);
      
/* 703 */       return new Time(localCalendar.getTimeInMillis());
    }
    
    Timestamp getTimestamp(int paramInt)
      throws SQLException
    {
/* 711 */       if (TimestamptzAccessor.this.rowSpaceIndicator == null)
      {
/* 715 */         SQLException localSQLException = DatabaseError.createSqlException(TimestamptzAccessor.this.getConnectionDuringExceptionHandling(), 21);
/* 716 */         localSQLException.fillInStackTrace();
/* 717 */         throw localSQLException;
      }
      
/* 723 */       if (TimestamptzAccessor.this.rowSpaceIndicator[(TimestamptzAccessor.this.indicatorIndex + paramInt)] == -1) {
/* 724 */         return null;
      }
/* 726 */       int i = TimestamptzAccessor.this.columnIndex + TimestamptzAccessor.this.byteLength * paramInt;
      
/* 728 */       Calendar localCalendar = TimestamptzAccessor.this.statement.getGMTCalendar();
      
/* 730 */       int j = TimestamptzAccessor.this.oracleYear(i);
      
/* 732 */       localCalendar.set(1, j);
/* 733 */       localCalendar.set(2, TimestamptzAccessor.this.oracleMonth(i));
/* 734 */       localCalendar.set(5, TimestamptzAccessor.this.oracleDay(i));
/* 735 */       localCalendar.set(11, TimestamptzAccessor.this.oracleHour(i));
/* 736 */       localCalendar.set(12, TimestamptzAccessor.this.oracleMin(i));
/* 737 */       localCalendar.set(13, TimestamptzAccessor.this.oracleSec(i));
/* 738 */       localCalendar.set(14, 0);
      
/* 741 */       long l = localCalendar.getTimeInMillis();
      
/* 744 */       Timestamp localTimestamp = new Timestamp(l);
      
/* 747 */       int k = TimestamptzAccessor.this.oracleNanos(i);
      
/* 750 */       localTimestamp.setNanos(k);
      
/* 752 */       return localTimestamp;
    }
    
    TIMESTAMPTZ getTIMESTAMPTZ(int paramInt)
      throws SQLException
    {
/* 760 */       TIMESTAMPTZ localTIMESTAMPTZ = null;
      
/* 762 */       if (TimestamptzAccessor.this.rowSpaceIndicator == null)
      {
/* 766 */         SQLException localSQLException = DatabaseError.createSqlException(TimestamptzAccessor.this.getConnectionDuringExceptionHandling(), 21);
/* 767 */         localSQLException.fillInStackTrace();
/* 768 */         throw localSQLException;
      }
      
/* 774 */       if (TimestamptzAccessor.this.rowSpaceIndicator[(TimestamptzAccessor.this.indicatorIndex + paramInt)] != -1)
      {
/* 776 */         int i = TimestamptzAccessor.this.columnIndex + TimestamptzAccessor.this.byteLength * paramInt;
/* 777 */         byte[] arrayOfByte = new byte[13];
        
/* 779 */         System.arraycopy(TimestamptzAccessor.this.rowSpaceByte, i, arrayOfByte, 0, 13);
        
/* 781 */         localTIMESTAMPTZ = new TIMESTAMPTZ(arrayOfByte);
      }
      
/* 784 */       return localTIMESTAMPTZ;
    }
  }
  
  abstract class TimestampTzConverter
  {
    TimestampTzConverter() {}
    
    abstract java.sql.Date getDate(int paramInt)
      throws SQLException;
    
    abstract Time getTime(int paramInt)
      throws SQLException;
    
    abstract Timestamp getTimestamp(int paramInt)
      throws SQLException;
    
    Object getObject(int paramInt)
      throws SQLException
    {
/* 807 */       return getTIMESTAMPTZ(paramInt);
    }
    
    Datum getOracleObject(int paramInt)
      throws SQLException
    {
/* 815 */       return getTIMESTAMPTZ(paramInt);
    }
    
    Object getObject(int paramInt, Map paramMap)
      throws SQLException
    {
/* 823 */       return getTIMESTAMPTZ(paramInt);
    }
    
    abstract TIMESTAMPTZ getTIMESTAMPTZ(int paramInt)
      throws SQLException;
  }
  
/* 832 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/TimestamptzAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */