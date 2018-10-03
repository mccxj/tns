package oracle.jdbc.driver;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;
import oracle.sql.DATE;
import oracle.sql.Datum;
import oracle.sql.OffsetDST;
import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPLTZ;
import oracle.sql.TIMESTAMPTZ;
import oracle.sql.TIMEZONETAB;
import oracle.sql.ZONEIDMAP;
class TimestampltzAccessor
  extends DateTimeCommonAccessor
{
  TimestampltzAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
    throws SQLException
  {
/*  33 */     init(paramOracleStatement, 231, 231, paramShort, paramBoolean);
/*  34 */     initForDataAccess(paramInt2, paramInt1, null);
  }
  
  TimestampltzAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
    throws SQLException
  {
/*  43 */     init(paramOracleStatement, 231, 231, paramShort, false);
/*  44 */     initForDescribe(231, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, null);
    
/*  46 */     initForDataAccess(0, paramInt1, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  54 */     if (paramInt1 != 0) {
/*  55 */       this.externalType = paramInt1;
    }
/*  57 */     this.internalTypeMaxLength = 11;
    
/*  59 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*  60 */       this.internalTypeMaxLength = paramInt2;
    }
/*  62 */     this.byteLength = this.internalTypeMaxLength;
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/*  70 */     if (this.rowSpaceIndicator == null)
    {
/*  74 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  75 */       ((SQLException)localObject1).fillInStackTrace();
/*  76 */       throw ((Throwable)localObject1);
    }
    
/*  82 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  83 */       return null;
    }
    
/*  86 */     Object localObject1 = this.statement.connection.getDbTzCalendar();
    
/*  90 */     String str1 = this.statement.connection.getSessionTimeZone();
    
/*  92 */     if (str1 == null)
    {
/*  95 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 198);
/*  96 */       ((SQLException)localObject2).fillInStackTrace();
/*  97 */       throw ((Throwable)localObject2);
    }
    
/* 101 */     Object localObject2 = TimeZone.getTimeZone(str1);
    
/* 103 */     Calendar localCalendar = Calendar.getInstance((TimeZone)localObject2);
    
/* 105 */     int i = this.columnIndex + this.byteLength * paramInt;
/* 106 */     int j = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
    
/* 108 */     int k = oracleYear(i);
    
/* 110 */     ((Calendar)localObject1).set(1, k);
/* 111 */     ((Calendar)localObject1).set(2, oracleMonth(i));
/* 112 */     ((Calendar)localObject1).set(5, oracleDay(i));
/* 113 */     ((Calendar)localObject1).set(11, oracleHour(i));
/* 114 */     ((Calendar)localObject1).set(12, oracleMin(i));
/* 115 */     ((Calendar)localObject1).set(13, oracleSec(i));
/* 116 */     ((Calendar)localObject1).set(14, 0);
    
/* 119 */     TimeZoneAdjust((Calendar)localObject1, localCalendar);
    
/* 122 */     k = localCalendar.get(1);
    
/* 124 */     int m = localCalendar.get(2) + 1;
/* 125 */     int n = localCalendar.get(5);
/* 126 */     int i1 = localCalendar.get(11);
/* 127 */     int i2 = localCalendar.get(12);
/* 128 */     int i3 = localCalendar.get(13);
/* 129 */     int i4 = 0;
/* 130 */     boolean bool = i1 < 12;
/* 131 */     String str2 = localCalendar.getTimeZone().getID();
/* 132 */     if ((str2.length() > 3) && (str2.startsWith("GMT"))) {
/* 133 */       str2 = str2.substring(3);
    }
/* 135 */     if (j == 11)
    {
/* 137 */       i4 = oracleNanos(i);
    }
    
/* 140 */     return toText(k, m, n, i1, i2, i3, i4, bool, str2);
  }
  
  Date getDate(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
/* 151 */     return getDate(paramInt);
  }
  
  Date getDate(int paramInt)
    throws SQLException
  {
/* 157 */     if (this.rowSpaceIndicator == null)
    {
/* 161 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 162 */       ((SQLException)localObject1).fillInStackTrace();
/* 163 */       throw ((Throwable)localObject1);
    }
    
/* 169 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 170 */       return null;
    }
    
/* 173 */     Object localObject1 = this.statement.connection.getDbTzCalendar();
    
/* 177 */     String str = this.statement.connection.getSessionTimeZone();
    
/* 179 */     if (str == null)
    {
/* 182 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 198);
/* 183 */       ((SQLException)localObject2).fillInStackTrace();
/* 184 */       throw ((Throwable)localObject2);
    }
    
/* 188 */     Object localObject2 = TimeZone.getTimeZone(str);
    
/* 190 */     Calendar localCalendar = Calendar.getInstance((TimeZone)localObject2);
    
/* 192 */     int i = this.columnIndex + this.byteLength * paramInt;
/* 193 */     int j = oracleYear(i);
    
/* 195 */     ((Calendar)localObject1).set(1, j);
/* 196 */     ((Calendar)localObject1).set(2, oracleMonth(i));
/* 197 */     ((Calendar)localObject1).set(5, oracleDay(i));
/* 198 */     ((Calendar)localObject1).set(11, oracleHour(i));
/* 199 */     ((Calendar)localObject1).set(12, oracleMin(i));
/* 200 */     ((Calendar)localObject1).set(13, oracleSec(i));
/* 201 */     ((Calendar)localObject1).set(14, 0);
    
/* 203 */     long l = TimeZoneAdjustUTC((Calendar)localObject1);
    
/* 205 */     return new Date(l);
  }
  
  Time getTime(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
/* 214 */     return getTime(paramInt);
  }
  
  Time getTime(int paramInt)
    throws SQLException
  {
/* 220 */     if (this.rowSpaceIndicator == null)
    {
/* 224 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 225 */       ((SQLException)localObject1).fillInStackTrace();
/* 226 */       throw ((Throwable)localObject1);
    }
    
/* 231 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 232 */       return null;
    }
    
/* 235 */     Object localObject1 = this.statement.connection.getDbTzCalendar();
    
/* 239 */     String str = this.statement.connection.getSessionTimeZone();
    
/* 241 */     if (str == null)
    {
/* 244 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 198);
/* 245 */       ((SQLException)localObject2).fillInStackTrace();
/* 246 */       throw ((Throwable)localObject2);
    }
    
/* 250 */     Object localObject2 = TimeZone.getTimeZone(str);
    
/* 252 */     Calendar localCalendar = Calendar.getInstance((TimeZone)localObject2);
    
/* 254 */     int i = this.columnIndex + this.byteLength * paramInt;
/* 255 */     int j = oracleYear(i);
    
/* 257 */     ((Calendar)localObject1).set(1, j);
/* 258 */     ((Calendar)localObject1).set(2, oracleMonth(i));
/* 259 */     ((Calendar)localObject1).set(5, oracleDay(i));
/* 260 */     ((Calendar)localObject1).set(11, oracleHour(i));
/* 261 */     ((Calendar)localObject1).set(12, oracleMin(i));
/* 262 */     ((Calendar)localObject1).set(13, oracleSec(i));
/* 263 */     ((Calendar)localObject1).set(14, 0);
    
/* 266 */     long l = TimeZoneAdjustUTC((Calendar)localObject1);
    
/* 268 */     return new Time(l);
  }
  
  Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
/* 278 */     return getTimestamp(paramInt);
  }
  
  Timestamp getTimestamp(int paramInt)
    throws SQLException
  {
/* 285 */     if (this.rowSpaceIndicator == null)
    {
/* 289 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 290 */       ((SQLException)localObject1).fillInStackTrace();
/* 291 */       throw ((Throwable)localObject1);
    }
    
/* 297 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 298 */       return null;
    }
    
/* 301 */     Object localObject1 = this.statement.connection.getDbTzCalendar();
    
/* 305 */     String str = this.statement.connection.getSessionTimeZone();
    
/* 307 */     if (str == null)
    {
/* 310 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 198);
/* 311 */       ((SQLException)localObject2).fillInStackTrace();
/* 312 */       throw ((Throwable)localObject2);
    }
    
/* 316 */     Object localObject2 = TimeZone.getTimeZone(str);
/* 317 */     Calendar localCalendar = Calendar.getInstance((TimeZone)localObject2);
    
/* 319 */     int i = this.columnIndex + this.byteLength * paramInt;
/* 320 */     int j = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
    
/* 322 */     int k = oracleYear(i);
    
/* 324 */     ((Calendar)localObject1).set(1, k);
/* 325 */     ((Calendar)localObject1).set(2, oracleMonth(i));
/* 326 */     ((Calendar)localObject1).set(5, oracleDay(i));
/* 327 */     ((Calendar)localObject1).set(11, oracleHour(i));
/* 328 */     ((Calendar)localObject1).set(12, oracleMin(i));
/* 329 */     ((Calendar)localObject1).set(13, oracleSec(i));
/* 330 */     ((Calendar)localObject1).set(14, 0);
    
/* 333 */     long l = TimeZoneAdjustUTC((Calendar)localObject1);
    
/* 335 */     Timestamp localTimestamp = new Timestamp(l);
    
/* 337 */     if (j == 11)
    {
/* 339 */       localTimestamp.setNanos(oracleNanos(i));
    }
    
/* 342 */     return localTimestamp;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 350 */     return getTIMESTAMPLTZ(paramInt);
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 358 */     return getTIMESTAMPLTZ(paramInt);
  }
  
  Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
/* 366 */     return getTIMESTAMPLTZ(paramInt);
  }
  
  TIMESTAMPLTZ getTIMESTAMPLTZ(int paramInt)
    throws SQLException
  {
/* 374 */     TIMESTAMPLTZ localTIMESTAMPLTZ = null;
    
/* 376 */     if (this.rowSpaceIndicator == null)
    {
/* 380 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 381 */       localSQLException.fillInStackTrace();
/* 382 */       throw localSQLException;
    }
    
/* 388 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 390 */       int i = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/* 391 */       int j = this.columnIndex + this.byteLength * paramInt;
/* 392 */       byte[] arrayOfByte = new byte[i];
      
/* 394 */       System.arraycopy(this.rowSpaceByte, j, arrayOfByte, 0, i);
      
/* 396 */       localTIMESTAMPLTZ = new TIMESTAMPLTZ(arrayOfByte);
    }
    
/* 399 */     return localTIMESTAMPLTZ;
  }
  
  TIMESTAMPTZ getTIMESTAMPTZ(int paramInt)
    throws SQLException
  {
/* 406 */     TIMESTAMPTZ localTIMESTAMPTZ = null;
    
/* 408 */     if (this.rowSpaceIndicator == null)
    {
/* 412 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 413 */       localSQLException.fillInStackTrace();
/* 414 */       throw localSQLException;
    }
    
/* 420 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 422 */       int i = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/* 423 */       int j = this.columnIndex + this.byteLength * paramInt;
/* 424 */       byte[] arrayOfByte = new byte[i];
      
/* 426 */       System.arraycopy(this.rowSpaceByte, j, arrayOfByte, 0, i);
      
/* 428 */       localTIMESTAMPTZ = TIMESTAMPLTZ.toTIMESTAMPTZ(this.statement.connection, arrayOfByte);
    }
    
/* 431 */     return localTIMESTAMPTZ;
  }
  
  TIMESTAMP getTIMESTAMP(int paramInt)
    throws SQLException
  {
/* 437 */     TIMESTAMPTZ localTIMESTAMPTZ = getTIMESTAMPTZ(paramInt);
/* 438 */     return TIMESTAMPTZ.toTIMESTAMP(this.statement.connection, localTIMESTAMPTZ.getBytes());
  }
  
  DATE getDATE(int paramInt) throws SQLException
  {
/* 443 */     TIMESTAMPTZ localTIMESTAMPTZ = getTIMESTAMPTZ(paramInt);
/* 444 */     return TIMESTAMPTZ.toDATE(this.statement.connection, localTIMESTAMPTZ.getBytes());
  }
  
  void TimeZoneAdjust(Calendar paramCalendar1, Calendar paramCalendar2)
    throws SQLException
  {
/* 454 */     String str1 = paramCalendar1.getTimeZone().getID();
/* 455 */     String str2 = paramCalendar2.getTimeZone().getID();
    
/* 458 */     if (!str2.equals(str1))
    {
/* 460 */       OffsetDST localOffsetDST = new OffsetDST();
      
/* 463 */       k = getZoneOffset(paramCalendar1, localOffsetDST);
      
/* 465 */       m = localOffsetDST.getOFFSET();
      
/* 468 */       paramCalendar1.add(11, -(m / 3600000));
/* 469 */       paramCalendar1.add(12, -(m % 3600000) / 60000);
      
      int i;
      
/* 475 */       if ((str2.equals("Custom")) || ((str2.startsWith("GMT")) && (str2.length() > 3)))
      {
/* 479 */         i = paramCalendar2.getTimeZone().getRawOffset();
      }
      else
      {
/* 484 */         n = ZONEIDMAP.getID(str2);
        
/* 486 */         if (!ZONEIDMAP.isValidID(n))
        {
/* 488 */           localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 199);
/* 489 */           ((SQLException)localObject).fillInStackTrace();
/* 490 */           throw ((Throwable)localObject);
        }
        
/* 493 */         Object localObject = this.statement.connection.getTIMEZONETAB();
/* 494 */         if (((TIMEZONETAB)localObject).checkID(n))
        {
/* 496 */           ((TIMEZONETAB)localObject).updateTable(this.statement.connection, n);
        }
        
/* 499 */         Calendar localCalendar = this.statement.getGMTCalendar();
        
/* 501 */         localCalendar.set(1, paramCalendar1.get(1));
/* 502 */         localCalendar.set(2, paramCalendar1.get(2));
/* 503 */         localCalendar.set(5, paramCalendar1.get(5));
/* 504 */         localCalendar.set(11, paramCalendar1.get(11));
/* 505 */         localCalendar.set(12, paramCalendar1.get(12));
/* 506 */         localCalendar.set(13, paramCalendar1.get(13));
/* 507 */         localCalendar.set(14, paramCalendar1.get(14));
        
/* 510 */         i = ((TIMEZONETAB)localObject).getOffset(localCalendar, n);
      }
      
/* 514 */       paramCalendar1.add(11, i / 3600000);
/* 515 */       paramCalendar1.add(12, i % 3600000 / 60000);
    }
    
/* 522 */     if (((str2.equals("Custom")) && (str1.equals("Custom"))) || ((str2.startsWith("GMT")) && (str2.length() > 3) && (str1.startsWith("GMT")) && (str1.length() > 3)))
    {
/* 527 */       j = paramCalendar1.getTimeZone().getRawOffset();
/* 528 */       k = paramCalendar2.getTimeZone().getRawOffset();
/* 529 */       m = 0;
      
/* 532 */       if (j != k)
      {
/* 535 */         m = j - k;
/* 536 */         m = m > 0 ? m : -m;
      }
      
/* 539 */       if (j > k) {
/* 540 */         m = -m;
      }
/* 542 */       paramCalendar1.add(11, m / 3600000);
/* 543 */       paramCalendar1.add(12, m % 3600000 / 60000);
    }
    
/* 547 */     int j = paramCalendar1.get(1);
/* 548 */     int k = paramCalendar1.get(2);
/* 549 */     int m = paramCalendar1.get(5);
/* 550 */     int n = paramCalendar1.get(11);
/* 551 */     int i1 = paramCalendar1.get(12);
/* 552 */     int i2 = paramCalendar1.get(13);
/* 553 */     int i3 = paramCalendar1.get(14);
    
/* 556 */     paramCalendar2.set(1, j);
/* 557 */     paramCalendar2.set(2, k);
/* 558 */     paramCalendar2.set(5, m);
/* 559 */     paramCalendar2.set(11, n);
/* 560 */     paramCalendar2.set(12, i1);
/* 561 */     paramCalendar2.set(13, i2);
/* 562 */     paramCalendar2.set(14, i3);
  }
  
  long TimeZoneAdjustUTC(Calendar paramCalendar)
    throws SQLException
  {
/* 572 */     String str = paramCalendar.getTimeZone().getID();
    
/* 574 */     if ((str.equals("Custom")) || ((str.startsWith("GMT")) && (str.length() > 3)))
    {
/* 578 */       int i = paramCalendar.getTimeZone().getRawOffset();
/* 579 */       paramCalendar.add(11, -(i / 3600000));
/* 580 */       paramCalendar.add(12, -(i % 3600000) / 60000);
    }
/* 582 */     else if ((!str.equals("GMT")) && (!str.equals("UTC")))
    {
/* 584 */       OffsetDST localOffsetDST = new OffsetDST();
      
/* 587 */       k = getZoneOffset(paramCalendar, localOffsetDST);
      
/* 589 */       m = localOffsetDST.getOFFSET();
      
/* 593 */       paramCalendar.add(11, -(m / 3600000));
/* 594 */       paramCalendar.add(12, -(m % 3600000) / 60000);
    }
    
/* 602 */     int j = paramCalendar.get(1);
/* 603 */     int k = paramCalendar.get(2);
/* 604 */     int m = paramCalendar.get(5);
/* 605 */     int n = paramCalendar.get(11);
/* 606 */     int i1 = paramCalendar.get(12);
/* 607 */     int i2 = paramCalendar.get(13);
/* 608 */     int i3 = paramCalendar.get(14);
    
/* 610 */     Calendar localCalendar = this.statement.getGMTCalendar();
    
/* 613 */     localCalendar.set(1, j);
/* 614 */     localCalendar.set(2, k);
/* 615 */     localCalendar.set(5, m);
/* 616 */     localCalendar.set(11, n);
/* 617 */     localCalendar.set(12, i1);
/* 618 */     localCalendar.set(13, i2);
/* 619 */     localCalendar.set(14, i3);
    
/* 621 */     long l = localCalendar.getTimeInMillis();
    
/* 623 */     return l;
  }
  
  byte getZoneOffset(Calendar paramCalendar, OffsetDST paramOffsetDST)
    throws SQLException
  {
/* 631 */     byte b = 0;
    
/* 634 */     String str = paramCalendar.getTimeZone().getID();
    
/* 637 */     if ((str == "Custom") || ((str.startsWith("GMT")) && (str.length() > 3)))
    {
/* 642 */       paramOffsetDST.setOFFSET(paramCalendar.getTimeZone().getRawOffset());
    }
    else
    {
/* 648 */       int i = ZONEIDMAP.getID(str);
      
/* 650 */       if (!ZONEIDMAP.isValidID(i))
      {
/* 652 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 199);
/* 653 */         ((SQLException)localObject).fillInStackTrace();
/* 654 */         throw ((Throwable)localObject);
      }
      
/* 657 */       Object localObject = this.statement.connection.getTIMEZONETAB();
/* 658 */       if (((TIMEZONETAB)localObject).checkID(i)) {
/* 659 */         ((TIMEZONETAB)localObject).updateTable(this.statement.connection, i);
      }
      
/* 663 */       b = ((TIMEZONETAB)localObject).getLocalOffset(paramCalendar, i, paramOffsetDST);
    }
    
/* 666 */     return b;
  }
  
/* 671 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/TimestampltzAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */