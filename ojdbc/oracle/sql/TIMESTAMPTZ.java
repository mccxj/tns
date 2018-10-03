/*      */ package oracle.sql;
/*      */ 
/*      */ import java.sql.Connection;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.text.ParseException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Calendar;
/*      */ import java.util.Locale;
/*      */ import java.util.SimpleTimeZone;
/*      */ import java.util.TimeZone;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TIMESTAMPTZ
/*      */   extends Datum
/*      */ {
/*      */   static final long serialVersionUID = 6708361144588335769L;
/*   76 */   static final Calendar CAL_GMT_US = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.US);
/*      */   
/*      */ 
/*   79 */   static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ()
/*      */   {
/*   90 */     super(initTimestamptz());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ(byte[] paramArrayOfByte)
/*      */   {
/*  105 */     super(paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ(Connection paramConnection, java.sql.Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  123 */     super(toBytes(paramConnection, paramDate));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ(Connection paramConnection, java.sql.Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  143 */     super(toBytes(paramConnection, paramDate, paramCalendar));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ(Connection paramConnection, Time paramTime)
/*      */     throws SQLException
/*      */   {
/*  160 */     super(toBytes(paramConnection, paramTime));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ(Connection paramConnection, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  180 */     super(toBytes(paramConnection, paramTime, paramCalendar));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ(Connection paramConnection, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/*  198 */     super(toBytes(paramConnection, paramTimestamp));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ(Connection paramConnection, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  217 */     super(toBytes(paramConnection, paramTimestamp, paramCalendar));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ(Connection paramConnection, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/*  235 */     super(toBytes(paramConnection, paramDATE));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ(Connection paramConnection, String paramString)
/*      */     throws SQLException
/*      */   {
/*  253 */     super(toBytes(paramConnection, paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ(Connection paramConnection, String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  270 */     super(toBytes(paramConnection, paramString, paramCalendar));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static java.sql.Date toDate(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  300 */     int[] arrayOfInt = new int[13];
/*      */     
/*      */ 
/*  303 */     for (int j = 0; j < 13; j++) {
/*  304 */       paramArrayOfByte[j] &= 0xFF;
/*      */     }
/*      */     
/*      */ 
/*  308 */     j = TIMESTAMP.getJavaYear(arrayOfInt[0], arrayOfInt[1]);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  313 */     Calendar localCalendar = Calendar.getInstance();
/*      */     
/*  315 */     localCalendar.set(1, j);
/*  316 */     localCalendar.set(2, arrayOfInt[2] - 1);
/*  317 */     localCalendar.set(5, arrayOfInt[3]);
/*  318 */     localCalendar.set(11, arrayOfInt[4] - 1);
/*  319 */     localCalendar.set(12, arrayOfInt[5] - 1);
/*  320 */     localCalendar.set(13, arrayOfInt[6] - 1);
/*  321 */     localCalendar.set(14, 0);
/*      */     
/*      */ 
/*      */ 
/*  325 */     if ((arrayOfInt[11] & REGIONIDBIT) != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  332 */       int k = getHighOrderbits(arrayOfInt[11]);
/*  333 */       k += getLowOrderbits(arrayOfInt[12]);
/*      */       
/*      */ 
/*      */ 
/*  337 */       TIMEZONETAB localTIMEZONETAB = getTIMEZONETAB(paramConnection);
/*  338 */       if (localTIMEZONETAB.checkID(k)) {
/*  339 */         localTIMEZONETAB.updateTable(paramConnection, k);
/*      */       }
/*      */       
/*      */ 
/*  343 */       int i = localTIMEZONETAB.getOffset(localCalendar, k);
/*      */       
/*      */ 
/*      */ 
/*  347 */       localCalendar.add(10, i / HOUR_MILLISECOND);
/*  348 */       localCalendar.add(12, i % HOUR_MILLISECOND / MINUTE_MILLISECOND);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*  354 */       localCalendar.add(10, arrayOfInt[11] - OFFSET_HOUR);
/*  355 */       localCalendar.add(12, arrayOfInt[12] - OFFSET_MINUTE);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  360 */     long l = localCalendar.getTime().getTime();
/*      */     
/*      */ 
/*      */ 
/*  364 */     return new java.sql.Date(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static java.sql.Date toDate2(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  383 */     int[] arrayOfInt = new int[13];
/*      */     
/*      */ 
/*  386 */     for (int i = 0; i < 13; i++) {
/*  387 */       paramArrayOfByte[i] &= 0xFF;
/*      */     }
/*      */     
/*      */ 
/*  391 */     i = TIMESTAMP.getJavaYear(arrayOfInt[0], arrayOfInt[1]);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  399 */     Calendar localCalendar = (Calendar)CAL_GMT_US.clone();
/*  400 */     localCalendar.set(1, i);
/*  401 */     localCalendar.set(2, arrayOfInt[2] - 1);
/*  402 */     localCalendar.set(5, arrayOfInt[3]);
/*  403 */     localCalendar.set(11, arrayOfInt[4] - 1);
/*  404 */     localCalendar.set(12, arrayOfInt[5] - 1);
/*  405 */     localCalendar.set(13, arrayOfInt[6] - 1);
/*  406 */     localCalendar.set(14, 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  411 */     long l = localCalendar.getTime().getTime();
/*      */     
/*      */ 
/*      */ 
/*  415 */     return new java.sql.Date(l);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Time toTime(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  437 */     int[] arrayOfInt = new int[13];
/*      */     
/*      */ 
/*      */ 
/*  441 */     for (int i = 0; i < 13; i++) {
/*  442 */       paramArrayOfByte[i] &= 0xFF;
/*      */     }
/*      */     
/*  445 */     Calendar localCalendar = Calendar.getInstance();
/*  446 */     localCalendar.setTimeZone(TIMEZONE_UTC);
/*      */     
/*      */ 
/*      */ 
/*  450 */     int j = TIMESTAMP.getJavaYear(arrayOfInt[0], arrayOfInt[1]);
/*      */     
/*  452 */     localCalendar.set(1, j);
/*  453 */     localCalendar.set(2, arrayOfInt[2] - 1);
/*  454 */     localCalendar.set(5, arrayOfInt[3]);
/*  455 */     localCalendar.set(11, arrayOfInt[4] - 1);
/*  456 */     localCalendar.set(12, arrayOfInt[5] - 1);
/*  457 */     localCalendar.set(13, arrayOfInt[6] - 1);
/*  458 */     localCalendar.set(14, 0);
/*      */     
/*      */ 
/*      */ 
/*  462 */     return new Time(localCalendar.getTimeInMillis());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DATE toDATE(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  482 */     return new DATE(toTimestampInSessionTimezone(paramConnection, paramArrayOfByte));
/*      */   }
/*      */   
/*      */   public static TIMESTAMP toTIMESTAMP(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  488 */     return new TIMESTAMP(toTimestampInSessionTimezone(paramConnection, paramArrayOfByte));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Timestamp toTimestamp(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  504 */     int[] arrayOfInt = new int[13];
/*      */     
/*  506 */     for (int j = 0; j < 13; j++) {
/*  507 */       paramArrayOfByte[j] &= 0xFF;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  513 */     Calendar localCalendar1 = Calendar.getInstance();
/*      */     
/*      */ 
/*      */ 
/*  517 */     Calendar localCalendar2 = (Calendar)CAL_GMT_US.clone();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  522 */     Calendar localCalendar3 = Calendar.getInstance();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  527 */     int k = TIMESTAMP.getJavaYear(arrayOfInt[0], arrayOfInt[1]);
/*      */     
/*  529 */     localCalendar1.set(1, k);
/*  530 */     localCalendar1.set(2, arrayOfInt[2] - 1);
/*  531 */     localCalendar1.set(5, arrayOfInt[3]);
/*  532 */     localCalendar1.set(11, arrayOfInt[4] - 1);
/*  533 */     localCalendar1.set(12, arrayOfInt[5] - 1);
/*  534 */     localCalendar1.set(13, arrayOfInt[6] - 1);
/*  535 */     localCalendar1.set(14, 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  540 */     localCalendar2.set(1, k);
/*  541 */     localCalendar2.set(2, arrayOfInt[2] - 1);
/*  542 */     localCalendar2.set(5, arrayOfInt[3]);
/*  543 */     localCalendar2.set(11, arrayOfInt[4] - 1);
/*  544 */     localCalendar2.set(12, arrayOfInt[5] - 1);
/*  545 */     localCalendar2.set(13, arrayOfInt[6] - 1);
/*  546 */     localCalendar2.set(14, 0);
/*      */     
/*  548 */     long l1 = localCalendar1.getTime().getTime();
/*      */     
/*  550 */     if ((arrayOfInt[11] & REGIONIDBIT) != 0)
/*      */     {
/*      */ 
/*  553 */       int m = getHighOrderbits(arrayOfInt[11]);
/*  554 */       m += getLowOrderbits(arrayOfInt[12]);
/*      */       
/*      */ 
/*      */ 
/*  558 */       TIMEZONETAB localTIMEZONETAB = getTIMEZONETAB(paramConnection);
/*  559 */       if (localTIMEZONETAB.checkID(m)) {
/*  560 */         localTIMEZONETAB.updateTable(paramConnection, m);
/*      */       }
/*      */       
/*      */ 
/*  564 */       int i = localTIMEZONETAB.getOffset(localCalendar2, m);
/*      */       
/*      */ 
/*      */ 
/*  568 */       l1 += i;
/*      */       
/*  570 */       TimeZone localTimeZone = localCalendar1.getTimeZone();
/*  571 */       localObject = localCalendar3.getTimeZone();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  579 */       if ((!localTimeZone.inDaylightTime(localCalendar1.getTime())) && (((TimeZone)localObject).inDaylightTime(new Timestamp(l1)) == true))
/*      */       {
/*      */ 
/*      */ 
/*  583 */         if ((localObject instanceof SimpleTimeZone)) {
/*  584 */           l1 -= ((SimpleTimeZone)localObject).getDSTSavings();
/*      */         } else {
/*  586 */           l1 -= 3600000L;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  596 */       if ((localTimeZone.inDaylightTime(localCalendar1.getTime()) == true) && (!((TimeZone)localObject).inDaylightTime(new Timestamp(l1))))
/*      */       {
/*      */ 
/*      */ 
/*  600 */         if ((localObject instanceof SimpleTimeZone)) {
/*  601 */           l1 += ((SimpleTimeZone)localTimeZone).getDSTSavings();
/*      */         } else {
/*  603 */           l1 += 3600000L;
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/*  608 */       localCalendar1.add(10, arrayOfInt[11] - OFFSET_HOUR);
/*  609 */       localCalendar1.add(12, arrayOfInt[12] - OFFSET_MINUTE);
/*      */       
/*  611 */       l1 = localCalendar1.getTime().getTime();
/*      */     }
/*      */     
/*      */ 
/*  615 */     Timestamp localTimestamp = new Timestamp(l1);
/*      */     
/*      */ 
/*      */ 
/*  619 */     long l2 = localCalendar2.getTime().getTime();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  624 */     Object localObject = Calendar.getInstance();
/*  625 */     ((Calendar)localObject).setTimeInMillis(l2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  630 */     Calendar localCalendar4 = Calendar.getInstance();
/*  631 */     localCalendar4.setTime(localTimestamp);
/*      */     
/*  633 */     boolean bool1 = ((Calendar)localObject).getTimeZone().inDaylightTime(((Calendar)localObject).getTime());
/*  634 */     boolean bool2 = localCalendar4.getTimeZone().inDaylightTime(localCalendar4.getTime());
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  640 */     if ((bool1 == true) && (!bool2)) {
/*  641 */       localTimestamp = new Timestamp(l1 - ((Calendar)localObject).getTimeZone().getDSTSavings());
/*  642 */     } else if ((!bool1) && (bool2 == true)) {
/*  643 */       localTimestamp = new Timestamp(l1 + localCalendar4.getTimeZone().getDSTSavings());
/*      */     }
/*      */     
/*      */ 
/*  647 */     int n = TIMESTAMP.getNanos(paramArrayOfByte, 7);
/*      */     
/*      */ 
/*  650 */     localTimestamp.setNanos(n);
/*      */     
/*  652 */     return localTimestamp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Timestamp toTimestamp2(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  679 */     int[] arrayOfInt = new int[13];
/*      */     
/*  681 */     for (int i = 0; i < 13; i++) {
/*  682 */       paramArrayOfByte[i] &= 0xFF;
/*      */     }
/*      */     
/*  685 */     i = TIMESTAMP.getJavaYear(arrayOfInt[0], arrayOfInt[1]);
/*      */     
/*  687 */     Calendar localCalendar = (Calendar)CAL_GMT_US.clone();
/*      */     
/*  689 */     localCalendar.clear();
/*      */     
/*  691 */     localCalendar.set(1, i);
/*  692 */     localCalendar.set(2, arrayOfInt[2] - 1);
/*  693 */     localCalendar.set(5, arrayOfInt[3]);
/*  694 */     localCalendar.set(11, arrayOfInt[4] - 1);
/*  695 */     localCalendar.set(12, arrayOfInt[5] - 1);
/*  696 */     localCalendar.set(13, arrayOfInt[6] - 1);
/*  697 */     localCalendar.set(14, 0);
/*      */     
/*  699 */     long l = localCalendar.getTime().getTime();
/*      */     
/*      */ 
/*      */ 
/*  703 */     Timestamp localTimestamp = new Timestamp(l);
/*      */     
/*      */ 
/*      */ 
/*  707 */     int j = TIMESTAMP.getNanos(paramArrayOfByte, 7);
/*      */     
/*      */ 
/*  710 */     localTimestamp.setNanos(j);
/*      */     
/*  712 */     return localTimestamp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static Timestamp toTimestampInSessionTimezone(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  721 */     int[] arrayOfInt = new int[13];
/*  722 */     for (int i = 0; i < 13; i++) {
/*  723 */       paramArrayOfByte[i] &= 0xFF;
/*      */     }
/*      */     
/*  726 */     i = TIMESTAMP.getJavaYear(arrayOfInt[0], arrayOfInt[1]);
/*      */     
/*  728 */     Calendar localCalendar1 = (Calendar)CAL_GMT_US.clone();
/*  729 */     localCalendar1.clear();
/*      */     
/*  731 */     localCalendar1.set(1, i);
/*  732 */     localCalendar1.set(2, arrayOfInt[2] - 1);
/*  733 */     localCalendar1.set(5, arrayOfInt[3]);
/*  734 */     localCalendar1.set(11, arrayOfInt[4] - 1);
/*  735 */     localCalendar1.set(12, arrayOfInt[5] - 1);
/*  736 */     localCalendar1.set(13, arrayOfInt[6] - 1);
/*  737 */     localCalendar1.set(14, 0);
/*      */     
/*      */ 
/*      */ 
/*  741 */     Calendar localCalendar2 = TIMESTAMPLTZ.getSessCalendar(paramConnection);
/*  742 */     TIMESTAMPLTZ.TimeZoneAdjust(paramConnection, localCalendar1, localCalendar2);
/*      */     
/*  744 */     long l = localCalendar2.getTime().getTime();
/*      */     
/*      */ 
/*      */ 
/*  748 */     Timestamp localTimestamp = new Timestamp(l);
/*      */     
/*      */ 
/*      */ 
/*  752 */     int j = TIMESTAMP.getNanos(paramArrayOfByte, 7);
/*      */     
/*      */ 
/*  755 */     localTimestamp.setNanos(j);
/*      */     
/*  757 */     return localTimestamp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String toString(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  772 */     Timestamp localTimestamp = toTimestamp(paramConnection, paramArrayOfByte);
/*      */     
/*  774 */     Calendar localCalendar = Calendar.getInstance();
/*      */     
/*  776 */     localCalendar.setTime(localTimestamp);
/*      */     
/*      */ 
/*      */ 
/*  780 */     int i = localCalendar.get(1);
/*  781 */     int j = localCalendar.get(2) + 1;
/*  782 */     int k = localCalendar.get(5);
/*  783 */     int m = localCalendar.get(11);
/*  784 */     int n = localCalendar.get(12);
/*  785 */     int i1 = localCalendar.get(13);
/*  786 */     int i2 = 0;
/*  787 */     i2 = (paramArrayOfByte[7] & 0xFF) << 24;
/*  788 */     i2 |= (paramArrayOfByte[8] & 0xFF) << 16;
/*  789 */     i2 |= (paramArrayOfByte[9] & 0xFF) << 8;
/*  790 */     i2 |= paramArrayOfByte[10] & 0xFF & 0xFF;
/*      */     int i3;
/*  792 */     String str; if ((paramArrayOfByte[11] & REGIONIDBIT) != 0)
/*      */     {
/*      */ 
/*  795 */       i3 = getHighOrderbits(paramArrayOfByte[11]);
/*  796 */       i3 += getLowOrderbits(paramArrayOfByte[12]);
/*  797 */       str = ZONEIDMAP.getRegion(i3);
/*      */     }
/*      */     else
/*      */     {
/*  801 */       i3 = paramArrayOfByte[11] - OFFSET_HOUR;
/*  802 */       int i4 = paramArrayOfByte[12] - OFFSET_MINUTE;
/*  803 */       str = i3 + ":";
/*  804 */       if (i4 == 0) {
/*  805 */         str = str + "00";
/*      */       } else {
/*  807 */         str = str + "" + i4;
/*      */       }
/*      */     }
/*  810 */     return toString(i, j, k, m, n, i1, i2, str);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String toString(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, String paramString)
/*      */   {
/*  829 */     String str1 = "" + paramInt1 + "-" + toStr(paramInt2) + "-" + toStr(paramInt3) + " " + toStr(paramInt4) + ":" + toStr(paramInt5) + ":" + toStr(paramInt6);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  837 */     if (paramInt7 >= 0)
/*      */     {
/*  839 */       String str2 = String.format("%09d", new Object[] { Integer.valueOf(paramInt7) });
/*      */       
/*  841 */       char[] arrayOfChar = str2.toCharArray();
/*  842 */       int i = arrayOfChar.length;
/*      */       
/*  844 */       while ((i > 1) && (arrayOfChar[(i - 1)] == '0')) {
/*  845 */         i--;
/*      */       }
/*  847 */       str2 = str2.substring(0, i);
/*      */       
/*  849 */       str1 = str1 + "." + str2;
/*      */     }
/*      */     
/*  852 */     if (paramString != null) {
/*  853 */       str1 = str1 + " " + paramString;
/*      */     }
/*  855 */     return str1;
/*      */   }
/*      */   
/*      */   private static final String toStr(int paramInt) {
/*  859 */     return paramInt < 10 ? "0" + paramInt : Integer.toString(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp timestampValue(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  881 */     if (((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin().getTimestamptzInGmt())
/*      */     {
/*      */ 
/*  884 */       return toTimestamp2(paramConnection, getBytes());
/*      */     }
/*      */     
/*      */ 
/*  888 */     return toTimestamp(paramConnection, getBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] toBytes()
/*      */   {
/*  901 */     return getBytes();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(Connection paramConnection, java.sql.Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  916 */     if (paramDate == null) {
/*  917 */       return null;
/*      */     }
/*  919 */     byte[] arrayOfByte = new byte[13];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  925 */     String str1 = ((oracle.jdbc.OracleConnection)paramConnection).getSessionTimeZone();
/*      */     Calendar localCalendar;
/*  927 */     if (str1 == null) {
/*  928 */       localCalendar = Calendar.getInstance();
/*      */     } else {
/*  930 */       localCalendar = Calendar.getInstance(TimeZone.getTimeZone(str1));
/*      */     }
/*  932 */     localCalendar.setTime(paramDate);
/*      */     
/*      */ 
/*      */     int j;
/*      */     
/*  937 */     if (localCalendar.getTimeZone().inDaylightTime(paramDate)) {
/*  938 */       j = 1;
/*      */     } else {
/*  940 */       j = 0;
/*      */     }
/*  942 */     localCalendar.set(11, 0);
/*  943 */     localCalendar.set(12, 0);
/*  944 */     localCalendar.set(13, 0);
/*      */     int i;
/*  946 */     if (localCalendar.getTimeZone().getID() == "Custom")
/*      */     {
/*  948 */       i = localCalendar.getTimeZone().getRawOffset();
/*  949 */       arrayOfByte[11] = ((byte)(i / HOUR_MILLISECOND + OFFSET_HOUR));
/*  950 */       arrayOfByte[12] = ((byte)(i % HOUR_MILLISECOND / MINUTE_MILLISECOND + OFFSET_MINUTE));
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*  957 */       String str2 = localCalendar.getTimeZone().getID();
/*      */       
/*      */ 
/*      */ 
/*  961 */       int m = ZONEIDMAP.getID(str2);
/*      */       
/*  963 */       if (!ZONEIDMAP.isValidID(m))
/*      */       {
/*  965 */         if (localCalendar.getTimeZone().useDaylightTime()) {
/*  966 */           throw new SQLException("Timezone not supported");
/*      */         }
/*      */         
/*  969 */         i = localCalendar.getTimeZone().getRawOffset();
/*  970 */         arrayOfByte[11] = ((byte)(i / HOUR_MILLISECOND + OFFSET_HOUR));
/*  971 */         arrayOfByte[12] = ((byte)(i % HOUR_MILLISECOND / MINUTE_MILLISECOND + OFFSET_MINUTE));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*  977 */         TIMEZONETAB localTIMEZONETAB = getTIMEZONETAB(paramConnection);
/*  978 */         if (localTIMEZONETAB.checkID(m)) {
/*  979 */           localTIMEZONETAB.updateTable(paramConnection, m);
/*      */         }
/*      */         
/*  982 */         OffsetDST localOffsetDST = new OffsetDST();
/*      */         
/*      */ 
/*      */ 
/*  986 */         int n = localTIMEZONETAB.getLocalOffset(localCalendar, m, localOffsetDST);
/*  987 */         i = localOffsetDST.getOFFSET();
/*      */         
/*  989 */         if ((j != 0) && (n == 1))
/*      */         {
/*  991 */           if (localOffsetDST.getDSTFLAG() == 0) {
/*  992 */             i += HOUR_MILLISECOND;
/*      */           } else {
/*  994 */             throw new SQLException();
/*      */           }
/*      */         }
/*      */         
/*  998 */         int i1 = ZONEIDMAP.getID(str2);
/*      */         
/* 1000 */         arrayOfByte[11] = ((byte)setHighOrderbits(i1)); byte[] 
/* 1001 */           tmp351_348 = arrayOfByte;tmp351_348[11] = ((byte)(tmp351_348[11] | REGIONIDBIT));
/* 1002 */         arrayOfByte[12] = ((byte)setLowOrderbits(i1));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1007 */     localCalendar.add(10, -(i / HOUR_MILLISECOND));
/* 1008 */     localCalendar.add(12, -(i % HOUR_MILLISECOND) / MINUTE_MILLISECOND);
/*      */     
/* 1010 */     int k = localCalendar.get(1);
/* 1011 */     if ((k < 60824) || (k > 9999))
/*      */     {
/*      */ 
/* 1014 */       SQLException localSQLException = DatabaseError.createSqlException(null, 268);
/* 1015 */       localSQLException.fillInStackTrace();
/* 1016 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1020 */     arrayOfByte[0] = ((byte)(localCalendar.get(1) / 100 + 100));
/* 1021 */     arrayOfByte[1] = ((byte)(localCalendar.get(1) % 100 + 100));
/* 1022 */     arrayOfByte[2] = ((byte)(localCalendar.get(2) + 1));
/* 1023 */     arrayOfByte[3] = ((byte)localCalendar.get(5));
/* 1024 */     arrayOfByte[4] = ((byte)(localCalendar.get(11) + 1));
/* 1025 */     arrayOfByte[5] = ((byte)(localCalendar.get(12) + 1));
/* 1026 */     arrayOfByte[6] = ((byte)(localCalendar.get(13) + 1));
/* 1027 */     arrayOfByte[7] = 0;
/* 1028 */     arrayOfByte[8] = 0;
/* 1029 */     arrayOfByte[9] = 0;
/* 1030 */     arrayOfByte[10] = 0;
/*      */     
/* 1032 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(Connection paramConnection, java.sql.Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1050 */     if (paramDate == null) {
/* 1051 */       return null;
/*      */     }
/*      */     
/* 1054 */     byte[] arrayOfByte = new byte[13];
/*      */     
/*      */ 
/* 1057 */     Calendar localCalendar = Calendar.getInstance();
/* 1058 */     localCalendar.setTime(paramDate);
/*      */     
/*      */     int j;
/*      */     
/* 1062 */     if (paramCalendar.getTimeZone().inDaylightTime(paramDate)) {
/* 1063 */       j = 1;
/*      */     } else {
/* 1065 */       j = 0;
/*      */     }
/* 1067 */     localCalendar.set(11, 0);
/* 1068 */     localCalendar.set(12, 0);
/* 1069 */     localCalendar.set(13, 0);
/*      */     
/*      */     int i;
/* 1072 */     if (paramCalendar.getTimeZone().getID() == "Custom")
/*      */     {
/* 1074 */       i = paramCalendar.getTimeZone().getRawOffset();
/* 1075 */       arrayOfByte[11] = ((byte)(i / HOUR_MILLISECOND + OFFSET_HOUR));
/* 1076 */       arrayOfByte[12] = ((byte)(i % HOUR_MILLISECOND / MINUTE_MILLISECOND + OFFSET_MINUTE));
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/* 1084 */       String str = paramCalendar.getTimeZone().getID();
/*      */       
/*      */ 
/*      */ 
/* 1088 */       int m = ZONEIDMAP.getID(str);
/*      */       
/* 1090 */       if (!ZONEIDMAP.isValidID(m))
/*      */       {
/* 1092 */         if (paramCalendar.getTimeZone().useDaylightTime()) {
/* 1093 */           throw new SQLException("Timezone not supported");
/*      */         }
/*      */         
/* 1096 */         i = paramCalendar.getTimeZone().getRawOffset();
/* 1097 */         arrayOfByte[11] = ((byte)(i / HOUR_MILLISECOND + OFFSET_HOUR));
/* 1098 */         arrayOfByte[12] = ((byte)(i % HOUR_MILLISECOND / MINUTE_MILLISECOND + OFFSET_MINUTE));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 1104 */         TIMEZONETAB localTIMEZONETAB = getTIMEZONETAB(paramConnection);
/* 1105 */         if (localTIMEZONETAB.checkID(m)) {
/* 1106 */           localTIMEZONETAB.updateTable(paramConnection, m);
/*      */         }
/*      */         
/* 1109 */         OffsetDST localOffsetDST = new OffsetDST();
/*      */         
/*      */ 
/*      */ 
/* 1113 */         int n = localTIMEZONETAB.getLocalOffset(localCalendar, m, localOffsetDST);
/* 1114 */         i = localOffsetDST.getOFFSET();
/*      */         
/* 1116 */         if ((j != 0) && (n == 1))
/*      */         {
/* 1118 */           if (localOffsetDST.getDSTFLAG() == 0) {
/* 1119 */             i += HOUR_MILLISECOND;
/*      */           } else {
/* 1121 */             throw new SQLException();
/*      */           }
/*      */         }
/*      */         
/* 1125 */         arrayOfByte[11] = ((byte)setHighOrderbits(ZONEIDMAP.getID(str))); byte[] 
/* 1126 */           tmp319_315 = arrayOfByte;tmp319_315[11] = ((byte)(tmp319_315[11] | REGIONIDBIT));
/* 1127 */         arrayOfByte[12] = ((byte)setLowOrderbits(ZONEIDMAP.getID(str)));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1132 */     localCalendar.add(10, -(i / HOUR_MILLISECOND));
/* 1133 */     localCalendar.add(12, -(i % HOUR_MILLISECOND) / MINUTE_MILLISECOND);
/*      */     
/* 1135 */     int k = localCalendar.get(1);
/* 1136 */     if ((k < 60824) || (k > 9999))
/*      */     {
/*      */ 
/* 1139 */       SQLException localSQLException = DatabaseError.createSqlException(null, 268);
/* 1140 */       localSQLException.fillInStackTrace();
/* 1141 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1145 */     arrayOfByte[0] = ((byte)(localCalendar.get(1) / 100 + 100));
/* 1146 */     arrayOfByte[1] = ((byte)(localCalendar.get(1) % 100 + 100));
/* 1147 */     arrayOfByte[2] = ((byte)(localCalendar.get(2) + 1));
/* 1148 */     arrayOfByte[3] = ((byte)localCalendar.get(5));
/* 1149 */     arrayOfByte[4] = ((byte)(localCalendar.get(11) + 1));
/* 1150 */     arrayOfByte[5] = ((byte)(localCalendar.get(12) + 1));
/* 1151 */     arrayOfByte[6] = ((byte)(localCalendar.get(13) + 1));
/* 1152 */     arrayOfByte[7] = 0;
/* 1153 */     arrayOfByte[8] = 0;
/* 1154 */     arrayOfByte[9] = 0;
/* 1155 */     arrayOfByte[10] = 0;
/*      */     
/* 1157 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(Connection paramConnection, Time paramTime)
/*      */     throws SQLException
/*      */   {
/* 1174 */     if (paramTime == null) {
/* 1175 */       return null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1181 */     byte[] arrayOfByte = new byte[13];
/*      */     
/*      */ 
/*      */ 
/* 1185 */     String str1 = ((oracle.jdbc.OracleConnection)paramConnection).getSessionTimeZone();
/*      */     Calendar localCalendar;
/* 1187 */     if (str1 == null) {
/* 1188 */       localCalendar = Calendar.getInstance();
/*      */     } else
/* 1190 */       localCalendar = Calendar.getInstance(TimeZone.getTimeZone(str1));
/* 1191 */     localCalendar.setTime(paramTime);
/*      */     
/*      */     int i;
/* 1194 */     if (localCalendar.getTimeZone().inDaylightTime(paramTime)) {
/* 1195 */       i = 1;
/*      */     } else {
/* 1197 */       i = 0;
/*      */     }
/*      */     
/*      */     int k;
/* 1201 */     if (((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin().getUse1900AsYearForTime())
/*      */     {
/*      */ 
/* 1204 */       k = 1900;
/*      */     }
/*      */     else
/*      */     {
/* 1208 */       k = 1970;
/*      */     }
/*      */     
/* 1211 */     localCalendar.set(1, k);
/* 1212 */     localCalendar.set(2, 0);
/* 1213 */     localCalendar.set(5, 1);
/*      */     
/*      */     int j;
/* 1216 */     if (localCalendar.getTimeZone().getID() == "Custom")
/*      */     {
/* 1218 */       j = localCalendar.getTimeZone().getRawOffset();
/* 1219 */       arrayOfByte[11] = ((byte)(j / HOUR_MILLISECOND + OFFSET_HOUR));
/* 1220 */       arrayOfByte[12] = ((byte)(j % HOUR_MILLISECOND / MINUTE_MILLISECOND + OFFSET_MINUTE));
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 1227 */       String str2 = localCalendar.getTimeZone().getID();
/*      */       
/*      */ 
/*      */ 
/* 1231 */       int n = ZONEIDMAP.getID(str2);
/*      */       
/* 1233 */       if (!ZONEIDMAP.isValidID(n))
/*      */       {
/* 1235 */         if (localCalendar.getTimeZone().useDaylightTime()) {
/* 1236 */           throw new SQLException("Timezone not supported");
/*      */         }
/*      */         
/* 1239 */         j = localCalendar.getTimeZone().getRawOffset();
/* 1240 */         arrayOfByte[11] = ((byte)(j / HOUR_MILLISECOND + OFFSET_HOUR));
/* 1241 */         arrayOfByte[12] = ((byte)(j % HOUR_MILLISECOND / MINUTE_MILLISECOND + OFFSET_MINUTE));
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 1248 */         TIMEZONETAB localTIMEZONETAB = getTIMEZONETAB(paramConnection);
/* 1249 */         if (localTIMEZONETAB.checkID(n)) {
/* 1250 */           localTIMEZONETAB.updateTable(paramConnection, n);
/*      */         }
/*      */         
/* 1253 */         OffsetDST localOffsetDST = new OffsetDST();
/*      */         
/*      */ 
/* 1256 */         int i1 = localTIMEZONETAB.getLocalOffset(localCalendar, n, localOffsetDST);
/*      */         
/* 1258 */         j = localOffsetDST.getOFFSET();
/*      */         
/*      */ 
/* 1261 */         arrayOfByte[11] = ((byte)setHighOrderbits(ZONEIDMAP.getID(str2))); byte[] 
/* 1262 */           tmp338_334 = arrayOfByte;tmp338_334[11] = ((byte)(tmp338_334[11] | REGIONIDBIT));
/* 1263 */         arrayOfByte[12] = ((byte)setLowOrderbits(ZONEIDMAP.getID(str2)));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1268 */     localCalendar.add(10, -(j / HOUR_MILLISECOND));
/* 1269 */     localCalendar.add(12, -(j % HOUR_MILLISECOND) / MINUTE_MILLISECOND);
/*      */     
/* 1271 */     int m = localCalendar.get(1);
/* 1272 */     if ((m < 60824) || (m > 9999))
/*      */     {
/*      */ 
/* 1275 */       SQLException localSQLException = DatabaseError.createSqlException(null, 268);
/* 1276 */       localSQLException.fillInStackTrace();
/* 1277 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1281 */     arrayOfByte[0] = ((byte)(localCalendar.get(1) / 100 + 100));
/* 1282 */     arrayOfByte[1] = ((byte)(localCalendar.get(1) % 100 + 100));
/* 1283 */     arrayOfByte[2] = ((byte)(localCalendar.get(2) + 1));
/* 1284 */     arrayOfByte[3] = ((byte)localCalendar.get(5));
/* 1285 */     arrayOfByte[4] = ((byte)(localCalendar.get(11) + 1));
/* 1286 */     arrayOfByte[5] = ((byte)(localCalendar.get(12) + 1));
/* 1287 */     arrayOfByte[6] = ((byte)(localCalendar.get(13) + 1));
/* 1288 */     arrayOfByte[7] = 0;
/* 1289 */     arrayOfByte[8] = 0;
/* 1290 */     arrayOfByte[9] = 0;
/* 1291 */     arrayOfByte[10] = 0;
/*      */     
/* 1293 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(Connection paramConnection, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1312 */     if (paramTime == null) {
/* 1313 */       return null;
/*      */     }
/*      */     
/* 1316 */     Calendar localCalendar = Calendar.getInstance();
/*      */     
/*      */ 
/*      */ 
/* 1320 */     byte[] arrayOfByte = new byte[13];
/*      */     
/* 1322 */     localCalendar.setTime(paramTime);
/*      */     
/*      */     int j;
/* 1325 */     if (paramCalendar.getTimeZone().inDaylightTime(paramTime)) {
/* 1326 */       j = 1;
/*      */     } else {
/* 1328 */       j = 0;
/*      */     }
/*      */     
/*      */     int k;
/* 1332 */     if (((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin().getUse1900AsYearForTime())
/*      */     {
/*      */ 
/* 1335 */       k = 1900;
/*      */     }
/*      */     else
/*      */     {
/* 1339 */       k = 1970;
/*      */     }
/*      */     
/* 1342 */     localCalendar.set(1, k);
/* 1343 */     localCalendar.set(2, 0);
/* 1344 */     localCalendar.set(5, 1);
/*      */     int i;
/* 1346 */     if (paramCalendar.getTimeZone().getID() == "Custom")
/*      */     {
/* 1348 */       i = paramCalendar.getTimeZone().getRawOffset();
/* 1349 */       arrayOfByte[11] = ((byte)(i / HOUR_MILLISECOND + OFFSET_HOUR));
/* 1350 */       arrayOfByte[12] = ((byte)(i % HOUR_MILLISECOND / MINUTE_MILLISECOND + OFFSET_MINUTE));
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 1357 */       String str = paramCalendar.getTimeZone().getID();
/*      */       
/*      */ 
/*      */ 
/* 1361 */       int n = ZONEIDMAP.getID(str);
/*      */       
/* 1363 */       if (!ZONEIDMAP.isValidID(n))
/*      */       {
/* 1365 */         if (paramCalendar.getTimeZone().useDaylightTime()) {
/* 1366 */           throw new SQLException("Timezone not supported");
/*      */         }
/*      */         
/* 1369 */         i = paramCalendar.getTimeZone().getRawOffset();
/* 1370 */         arrayOfByte[11] = ((byte)(i / HOUR_MILLISECOND + OFFSET_HOUR));
/* 1371 */         arrayOfByte[12] = ((byte)(i % HOUR_MILLISECOND / MINUTE_MILLISECOND + OFFSET_MINUTE));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 1377 */         TIMEZONETAB localTIMEZONETAB = getTIMEZONETAB(paramConnection);
/* 1378 */         if (localTIMEZONETAB.checkID(n)) {
/* 1379 */           localTIMEZONETAB.updateTable(paramConnection, n);
/*      */         }
/*      */         
/* 1382 */         OffsetDST localOffsetDST = new OffsetDST();
/*      */         
/*      */ 
/*      */ 
/* 1386 */         int i1 = localTIMEZONETAB.getLocalOffset(localCalendar, n, localOffsetDST);
/*      */         
/* 1388 */         i = localOffsetDST.getOFFSET();
/*      */         
/*      */ 
/* 1391 */         if ((j != 0) && (i1 == 1))
/*      */         {
/* 1393 */           if (localOffsetDST.getDSTFLAG() == 0) {
/* 1394 */             i += HOUR_MILLISECOND;
/*      */           } else {
/* 1396 */             throw new SQLException();
/*      */           }
/*      */         }
/* 1399 */         arrayOfByte[11] = ((byte)setHighOrderbits(ZONEIDMAP.getID(str))); byte[] 
/* 1400 */           tmp350_346 = arrayOfByte;tmp350_346[11] = ((byte)(tmp350_346[11] | REGIONIDBIT));
/* 1401 */         arrayOfByte[12] = ((byte)setLowOrderbits(ZONEIDMAP.getID(str)));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1406 */     localCalendar.add(11, -(i / HOUR_MILLISECOND));
/* 1407 */     localCalendar.add(12, -(i % HOUR_MILLISECOND) / MINUTE_MILLISECOND);
/*      */     
/* 1409 */     int m = localCalendar.get(1);
/* 1410 */     if ((m < 60824) || (m > 9999))
/*      */     {
/*      */ 
/* 1413 */       SQLException localSQLException = DatabaseError.createSqlException(null, 268);
/* 1414 */       localSQLException.fillInStackTrace();
/* 1415 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1419 */     arrayOfByte[0] = ((byte)(localCalendar.get(1) / 100 + 100));
/* 1420 */     arrayOfByte[1] = ((byte)(localCalendar.get(1) % 100 + 100));
/* 1421 */     arrayOfByte[2] = ((byte)(localCalendar.get(2) + 1));
/* 1422 */     arrayOfByte[3] = ((byte)localCalendar.get(5));
/* 1423 */     arrayOfByte[4] = ((byte)(localCalendar.get(11) + 1));
/* 1424 */     arrayOfByte[5] = ((byte)(localCalendar.get(12) + 1));
/* 1425 */     arrayOfByte[6] = ((byte)(localCalendar.get(13) + 1));
/* 1426 */     arrayOfByte[7] = 0;
/* 1427 */     arrayOfByte[8] = 0;
/* 1428 */     arrayOfByte[9] = 0;
/* 1429 */     arrayOfByte[10] = 0;
/*      */     
/* 1431 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(Connection paramConnection, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/* 1448 */     if (paramTimestamp == null) {
/* 1449 */       return null;
/*      */     }
/* 1451 */     byte[] arrayOfByte = new byte[13];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1458 */     long l = 0L;
/*      */     
/*      */ 
/*      */ 
/* 1462 */     String str1 = ((oracle.jdbc.OracleConnection)paramConnection).getSessionTimeZone();
/*      */     int i3;
/* 1464 */     if (str1 == null)
/*      */     {
/*      */ 
/*      */ 
/* 1468 */       str1 = TimeZone.getDefault().getID();
/*      */       
/*      */ 
/*      */ 
/* 1472 */       l = paramTimestamp.getTime();
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 1478 */       int k = paramTimestamp.getNanos();
/*      */       
/* 1480 */       Calendar localCalendar2 = Calendar.getInstance();
/* 1481 */       localCalendar2.setTime(paramTimestamp);
/*      */       
/* 1483 */       int n = localCalendar2.get(1);
/* 1484 */       int i2 = localCalendar2.get(2) + 1;
/* 1485 */       i3 = localCalendar2.get(5);
/* 1486 */       int i4 = localCalendar2.get(11);
/* 1487 */       int i5 = localCalendar2.get(12);
/* 1488 */       int i6 = localCalendar2.get(13);
/* 1489 */       double d = k / 1000000;
/*      */       
/* 1491 */       String str2 = Integer.valueOf(n).toString() + "/" + Integer.valueOf(i2).toString() + "/" + Integer.valueOf(i3).toString() + " " + Integer.valueOf(i4).toString() + ":" + Integer.valueOf(i5).toString() + ":" + Integer.valueOf(i6).toString() + ":" + Double.valueOf(d).toString();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1500 */       TimeZone localTimeZone = TimeZone.getTimeZone(str1);
/*      */       
/*      */ 
/*      */ 
/* 1504 */       SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("y/M/d H:m:s:S");
/* 1505 */       localSimpleDateFormat.setTimeZone(localTimeZone);
/*      */       
/* 1507 */       java.util.Date localDate = null;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       try
/*      */       {
/* 1515 */         localDate = localSimpleDateFormat.parse(str2);
/*      */       }
/*      */       catch (ParseException localParseException)
/*      */       {
/* 1519 */         throw new SQLException(localParseException.getMessage());
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1524 */       l = localDate.getTime();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1529 */     Calendar localCalendar1 = Calendar.getInstance(TimeZone.getTimeZone(str1));
/*      */     
/*      */     int j;
/* 1532 */     if (localCalendar1.getTimeZone().inDaylightTime(paramTimestamp)) {
/* 1533 */       j = 1;
/*      */     } else {
/* 1535 */       j = 0;
/*      */     }
/* 1537 */     localCalendar1.setTime(new Timestamp(l));
/*      */     int i;
/* 1539 */     Object localObject2; if (localCalendar1.getTimeZone().getID() == "Custom")
/*      */     {
/* 1541 */       i = localCalendar1.getTimeZone().getRawOffset();
/* 1542 */       arrayOfByte[11] = ((byte)(i / HOUR_MILLISECOND + OFFSET_HOUR));
/* 1543 */       arrayOfByte[12] = ((byte)(i % HOUR_MILLISECOND / MINUTE_MILLISECOND + OFFSET_MINUTE));
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 1550 */       localObject1 = localCalendar1.getTimeZone().getID();
/*      */       
/*      */ 
/*      */ 
/* 1554 */       int m = ZONEIDMAP.getID((String)localObject1);
/*      */       
/* 1556 */       if (!ZONEIDMAP.isValidID(m))
/*      */       {
/* 1558 */         if (localCalendar1.getTimeZone().useDaylightTime()) {
/* 1559 */           throw new SQLException("Timezone not supported");
/*      */         }
/*      */         
/* 1562 */         i = localCalendar1.getTimeZone().getRawOffset();
/* 1563 */         arrayOfByte[11] = ((byte)(i / HOUR_MILLISECOND + OFFSET_HOUR));
/* 1564 */         arrayOfByte[12] = ((byte)(i % HOUR_MILLISECOND / MINUTE_MILLISECOND + OFFSET_MINUTE));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 1570 */         TIMEZONETAB localTIMEZONETAB = getTIMEZONETAB(paramConnection);
/* 1571 */         if (localTIMEZONETAB.checkID(m)) {
/* 1572 */           localTIMEZONETAB.updateTable(paramConnection, m);
/*      */         }
/*      */         
/* 1575 */         localObject2 = new OffsetDST();
/*      */         
/*      */ 
/*      */ 
/* 1579 */         i3 = localTIMEZONETAB.getLocalOffset(localCalendar1, m, (OffsetDST)localObject2);
/*      */         
/* 1581 */         i = ((OffsetDST)localObject2).getOFFSET();
/*      */         
/* 1583 */         if ((j != 0) && (i3 == 1))
/*      */         {
/* 1585 */           if (((OffsetDST)localObject2).getDSTFLAG() == 0) {
/* 1586 */             i += HOUR_MILLISECOND;
/*      */           } else {
/* 1588 */             throw new SQLException();
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1593 */         arrayOfByte[11] = ((byte)setHighOrderbits(ZONEIDMAP.getID((String)localObject1))); byte[] 
/* 1594 */           tmp602_599 = arrayOfByte;tmp602_599[11] = ((byte)(tmp602_599[11] | REGIONIDBIT));
/* 1595 */         arrayOfByte[12] = ((byte)setLowOrderbits(ZONEIDMAP.getID((String)localObject1)));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1605 */     Object localObject1 = TimeZone.getTimeZone("GMT");
/* 1606 */     Calendar localCalendar3 = Calendar.getInstance((TimeZone)localObject1, Locale.US);
/*      */     
/* 1608 */     localCalendar3.set(1, localCalendar1.get(1));
/* 1609 */     localCalendar3.set(2, localCalendar1.get(2));
/* 1610 */     localCalendar3.set(5, localCalendar1.get(5));
/* 1611 */     localCalendar3.set(11, localCalendar1.get(11));
/* 1612 */     localCalendar3.set(12, localCalendar1.get(12));
/* 1613 */     localCalendar3.set(13, localCalendar1.get(13));
/*      */     
/*      */ 
/* 1616 */     localCalendar3.add(14, -1 * i);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1624 */     int i1 = localCalendar3.get(1);
/* 1625 */     if ((i1 < 60824) || (i1 > 9999))
/*      */     {
/*      */ 
/* 1628 */       localObject2 = DatabaseError.createSqlException(null, 268);
/* 1629 */       ((SQLException)localObject2).fillInStackTrace();
/* 1630 */       throw ((Throwable)localObject2);
/*      */     }
/*      */     
/*      */ 
/* 1634 */     arrayOfByte[0] = ((byte)(localCalendar3.get(1) / 100 + 100));
/* 1635 */     arrayOfByte[1] = ((byte)(localCalendar3.get(1) % 100 + 100));
/* 1636 */     arrayOfByte[2] = ((byte)(localCalendar3.get(2) + 1));
/* 1637 */     arrayOfByte[3] = ((byte)localCalendar3.get(5));
/* 1638 */     arrayOfByte[4] = ((byte)(localCalendar3.get(11) + 1));
/* 1639 */     arrayOfByte[5] = ((byte)(localCalendar3.get(12) + 1));
/* 1640 */     arrayOfByte[6] = ((byte)(localCalendar3.get(13) + 1));
/*      */     
/* 1642 */     arrayOfByte[7] = ((byte)(paramTimestamp.getNanos() >> 24));
/* 1643 */     arrayOfByte[8] = ((byte)(paramTimestamp.getNanos() >> 16 & 0xFF));
/* 1644 */     arrayOfByte[9] = ((byte)(paramTimestamp.getNanos() >> 8 & 0xFF));
/* 1645 */     arrayOfByte[10] = ((byte)(paramTimestamp.getNanos() & 0xFF));
/*      */     
/* 1647 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(Connection paramConnection, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1668 */     if (paramTimestamp == null) {
/* 1669 */       return null;
/*      */     }
/*      */     
/* 1672 */     byte[] arrayOfByte = new byte[13];
/*      */     
/*      */     Calendar localCalendar1;
/* 1675 */     if (paramCalendar == null) {
/* 1676 */       localCalendar1 = Calendar.getInstance();
/*      */     } else {
/* 1678 */       localCalendar1 = Calendar.getInstance(paramCalendar.getTimeZone());
/*      */     }
/* 1680 */     localCalendar1.setTime(paramTimestamp);
/*      */     
/*      */ 
/* 1683 */     TimeZone localTimeZone = localCalendar1.getTimeZone();
/*      */     
/*      */ 
/*      */     int j;
/*      */     
/* 1688 */     if (paramCalendar.getTimeZone().inDaylightTime(paramTimestamp)) {
/* 1689 */       j = 1;
/*      */     } else
/* 1691 */       j = 0;
/*      */     int i;
/* 1693 */     Object localObject2; if (paramCalendar.getTimeZone().getID() == "Custom")
/*      */     {
/* 1695 */       i = paramCalendar.getTimeZone().getRawOffset();
/* 1696 */       arrayOfByte[11] = ((byte)(i / HOUR_MILLISECOND + OFFSET_HOUR));
/* 1697 */       arrayOfByte[12] = ((byte)(i % HOUR_MILLISECOND / MINUTE_MILLISECOND + OFFSET_MINUTE));
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 1704 */       localObject1 = paramCalendar.getTimeZone().getID();
/*      */       
/*      */ 
/*      */ 
/* 1708 */       int k = ZONEIDMAP.getID((String)localObject1);
/*      */       
/* 1710 */       if (!ZONEIDMAP.isValidID(k))
/*      */       {
/* 1712 */         if (paramCalendar.getTimeZone().useDaylightTime()) {
/* 1713 */           throw new SQLException("Timezone not supported");
/*      */         }
/*      */         
/* 1716 */         i = paramCalendar.getTimeZone().getRawOffset();
/* 1717 */         arrayOfByte[11] = ((byte)(i / HOUR_MILLISECOND + OFFSET_HOUR));
/* 1718 */         arrayOfByte[12] = ((byte)(i % HOUR_MILLISECOND / MINUTE_MILLISECOND + OFFSET_MINUTE));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 1724 */         TIMEZONETAB localTIMEZONETAB = getTIMEZONETAB(paramConnection);
/* 1725 */         if (localTIMEZONETAB.checkID(k)) {
/* 1726 */           localTIMEZONETAB.updateTable(paramConnection, k);
/*      */         }
/*      */         
/* 1729 */         localObject2 = new OffsetDST();
/*      */         
/*      */ 
/*      */ 
/* 1733 */         int n = localTIMEZONETAB.getLocalOffset(localCalendar1, k, (OffsetDST)localObject2);
/*      */         
/* 1735 */         i = ((OffsetDST)localObject2).getOFFSET();
/*      */         
/* 1737 */         if ((j != 0) && (n == 1))
/*      */         {
/* 1739 */           if (((OffsetDST)localObject2).getDSTFLAG() == 0) {
/* 1740 */             i += HOUR_MILLISECOND;
/*      */           } else {
/* 1742 */             throw new SQLException();
/*      */           }
/*      */         }
/* 1745 */         arrayOfByte[11] = ((byte)setHighOrderbits(ZONEIDMAP.getID((String)localObject1))); byte[] 
/*      */         
/* 1747 */           tmp326_322 = arrayOfByte;tmp326_322[11] = ((byte)(tmp326_322[11] | REGIONIDBIT));
/* 1748 */         arrayOfByte[12] = ((byte)setLowOrderbits(ZONEIDMAP.getID((String)localObject1)));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1753 */     Object localObject1 = TimeZone.getTimeZone("GMT");
/* 1754 */     Calendar localCalendar2 = Calendar.getInstance((TimeZone)localObject1, Locale.US);
/*      */     
/* 1756 */     localCalendar2.set(1, localCalendar1.get(1));
/* 1757 */     localCalendar2.set(2, localCalendar1.get(2));
/* 1758 */     localCalendar2.set(5, localCalendar1.get(5));
/* 1759 */     localCalendar2.set(11, localCalendar1.get(11));
/* 1760 */     localCalendar2.set(12, localCalendar1.get(12));
/* 1761 */     localCalendar2.set(13, localCalendar1.get(13));
/*      */     
/*      */ 
/* 1764 */     localCalendar2.add(14, -1 * i);
/*      */     
/* 1766 */     int m = localCalendar2.get(1);
/* 1767 */     if ((m < 60824) || (m > 9999))
/*      */     {
/*      */ 
/* 1770 */       localObject2 = DatabaseError.createSqlException(null, 268);
/* 1771 */       ((SQLException)localObject2).fillInStackTrace();
/* 1772 */       throw ((Throwable)localObject2);
/*      */     }
/*      */     
/*      */ 
/* 1776 */     arrayOfByte[0] = ((byte)(localCalendar2.get(1) / 100 + 100));
/* 1777 */     arrayOfByte[1] = ((byte)(localCalendar2.get(1) % 100 + 100));
/* 1778 */     arrayOfByte[2] = ((byte)(localCalendar2.get(2) + 1));
/* 1779 */     arrayOfByte[3] = ((byte)localCalendar2.get(5));
/* 1780 */     arrayOfByte[4] = ((byte)(localCalendar2.get(11) + 1));
/* 1781 */     arrayOfByte[5] = ((byte)(localCalendar2.get(12) + 1));
/* 1782 */     arrayOfByte[6] = ((byte)(localCalendar2.get(13) + 1));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1789 */     arrayOfByte[7] = ((byte)(paramTimestamp.getNanos() >> 24));
/* 1790 */     arrayOfByte[8] = ((byte)(paramTimestamp.getNanos() >> 16 & 0xFF));
/* 1791 */     arrayOfByte[9] = ((byte)(paramTimestamp.getNanos() >> 8 & 0xFF));
/* 1792 */     arrayOfByte[10] = ((byte)(paramTimestamp.getNanos() & 0xFF));
/*      */     
/* 1794 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(Connection paramConnection, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/* 1809 */     if (paramDATE == null) {
/* 1810 */       return null;
/*      */     }
/* 1812 */     byte[] arrayOfByte = new byte[13];
/*      */     
/*      */ 
/*      */ 
/* 1816 */     Calendar localCalendar = Calendar.getInstance();
/*      */     
/*      */ 
/* 1819 */     localCalendar.setTime(DATE.toDate(paramDATE.toBytes()));
/*      */     
/*      */     int j;
/* 1822 */     if (localCalendar.getTimeZone().inDaylightTime(DATE.toDate(paramDATE.toBytes()))) {
/* 1823 */       j = 1;
/*      */     } else {
/* 1825 */       j = 0;
/*      */     }
/*      */     
/*      */     int i;
/*      */     
/* 1830 */     if (localCalendar.getTimeZone().getID() == "Custom")
/*      */     {
/* 1832 */       i = localCalendar.getTimeZone().getRawOffset();
/* 1833 */       arrayOfByte[11] = ((byte)(i / HOUR_MILLISECOND + OFFSET_HOUR));
/* 1834 */       arrayOfByte[12] = ((byte)(i % HOUR_MILLISECOND / MINUTE_MILLISECOND + OFFSET_MINUTE));
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 1841 */       String str = localCalendar.getTimeZone().getID();
/*      */       
/*      */ 
/*      */ 
/* 1845 */       int k = ZONEIDMAP.getID(str);
/*      */       
/* 1847 */       if (!ZONEIDMAP.isValidID(k))
/*      */       {
/* 1849 */         if (localCalendar.getTimeZone().useDaylightTime()) {
/* 1850 */           throw new SQLException("Timezone not supported");
/*      */         }
/*      */         
/* 1853 */         i = localCalendar.getTimeZone().getRawOffset();
/* 1854 */         arrayOfByte[11] = ((byte)(i / HOUR_MILLISECOND + OFFSET_HOUR));
/* 1855 */         arrayOfByte[12] = ((byte)(i % HOUR_MILLISECOND / MINUTE_MILLISECOND + OFFSET_MINUTE));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 1861 */         TIMEZONETAB localTIMEZONETAB = getTIMEZONETAB(paramConnection);
/* 1862 */         if (localTIMEZONETAB.checkID(k)) {
/* 1863 */           localTIMEZONETAB.updateTable(paramConnection, k);
/*      */         }
/*      */         
/* 1866 */         OffsetDST localOffsetDST = new OffsetDST();
/*      */         
/*      */ 
/*      */ 
/* 1870 */         int m = localTIMEZONETAB.getLocalOffset(localCalendar, k, localOffsetDST);
/*      */         
/* 1872 */         i = localOffsetDST.getOFFSET();
/*      */         
/* 1874 */         if ((j != 0) && (m == 1))
/*      */         {
/* 1876 */           if (localOffsetDST.getDSTFLAG() == 0) {
/* 1877 */             i += HOUR_MILLISECOND;
/*      */           } else {
/* 1879 */             throw new SQLException();
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 1884 */         arrayOfByte[11] = ((byte)setHighOrderbits(ZONEIDMAP.getID(str))); byte[] 
/* 1885 */           tmp306_303 = arrayOfByte;tmp306_303[11] = ((byte)(tmp306_303[11] | REGIONIDBIT));
/* 1886 */         arrayOfByte[12] = ((byte)setLowOrderbits(ZONEIDMAP.getID(str)));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1891 */     localCalendar.add(10, -(i / HOUR_MILLISECOND));
/* 1892 */     localCalendar.add(12, -(i % HOUR_MILLISECOND) / MINUTE_MILLISECOND);
/*      */     
/* 1894 */     arrayOfByte[0] = ((byte)(localCalendar.get(1) / 100 + 100));
/* 1895 */     arrayOfByte[1] = ((byte)(localCalendar.get(1) % 100 + 100));
/* 1896 */     arrayOfByte[2] = ((byte)(localCalendar.get(2) + 1));
/* 1897 */     arrayOfByte[3] = ((byte)localCalendar.get(5));
/* 1898 */     arrayOfByte[4] = ((byte)(localCalendar.get(11) + 1));
/* 1899 */     arrayOfByte[5] = ((byte)(localCalendar.get(12) + 1));
/* 1900 */     arrayOfByte[6] = ((byte)(localCalendar.get(13) + 1));
/* 1901 */     arrayOfByte[7] = 0;
/* 1902 */     arrayOfByte[8] = 0;
/* 1903 */     arrayOfByte[9] = 0;
/* 1904 */     arrayOfByte[10] = 0;
/*      */     
/* 1906 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(Connection paramConnection, String paramString)
/*      */     throws SQLException
/*      */   {
/* 1919 */     return toBytes(paramConnection, Timestamp.valueOf(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(Connection paramConnection, String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1935 */     Calendar localCalendar1 = (Calendar)CAL_GMT_US.clone();
/*      */     
/* 1937 */     Timestamp localTimestamp = parseTime(paramString);
/* 1938 */     localCalendar1.setTime(localTimestamp);
/*      */     
/*      */ 
/*      */     Calendar localCalendar2;
/*      */     
/*      */ 
/* 1944 */     if (paramCalendar == null) {
/* 1945 */       localCalendar2 = Calendar.getInstance();
/*      */     } else {
/* 1947 */       localCalendar2 = Calendar.getInstance(paramCalendar.getTimeZone());
/*      */     }
/* 1949 */     localCalendar2.set(1, localCalendar1.get(1));
/* 1950 */     localCalendar2.set(2, localCalendar1.get(2));
/* 1951 */     localCalendar2.set(5, localCalendar1.get(5));
/* 1952 */     localCalendar2.set(11, localCalendar1.get(11));
/* 1953 */     localCalendar2.set(12, localCalendar1.get(12));
/* 1954 */     localCalendar2.set(13, localCalendar1.get(13));
/* 1955 */     localCalendar2.set(14, localCalendar1.get(14));
/*      */     
/*      */ 
/* 1958 */     int i = localTimestamp.getNanos();
/* 1959 */     localTimestamp = new Timestamp(localCalendar2.getTime().getTime());
/* 1960 */     localTimestamp.setNanos(i);
/*      */     
/* 1962 */     return toBytes(paramConnection, localTimestamp, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String stringValue(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/* 1974 */     return toString(paramConnection, getBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public java.sql.Date dateValue(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/* 1996 */     if (((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin().getTimestamptzInGmt())
/*      */     {
/*      */ 
/* 1999 */       return toDate2(paramConnection, getBytes());
/*      */     }
/*      */     
/*      */ 
/* 2003 */     return toDate(paramConnection, getBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time timeValue(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/* 2015 */     return toTime(paramConnection, getBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static byte[] initTimestamptz()
/*      */   {
/* 2028 */     byte[] arrayOfByte = new byte[13];
/* 2029 */     Calendar localCalendar = Calendar.getInstance();
/*      */     
/* 2031 */     arrayOfByte[0] = 119;
/* 2032 */     arrayOfByte[1] = -86;
/* 2033 */     arrayOfByte[2] = 1;
/* 2034 */     arrayOfByte[3] = 1;
/* 2035 */     arrayOfByte[4] = 1;
/* 2036 */     arrayOfByte[5] = 1;
/* 2037 */     arrayOfByte[6] = 1;
/* 2038 */     arrayOfByte[7] = 0;
/* 2039 */     arrayOfByte[8] = 0;
/* 2040 */     arrayOfByte[9] = 0;
/* 2041 */     arrayOfByte[10] = 0;
/*      */     
/* 2043 */     String str = localCalendar.getTimeZone().getID();
/*      */     
/*      */ 
/* 2046 */     arrayOfByte[11] = ((byte)setHighOrderbits(ZONEIDMAP.getID(str)));
/* 2047 */     arrayOfByte[11] = ((byte)(arrayOfByte[11] | REGIONIDBIT));
/* 2048 */     arrayOfByte[12] = ((byte)setLowOrderbits(ZONEIDMAP.getID(str)));
/*      */     
/*      */ 
/* 2051 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object toJdbc()
/*      */     throws SQLException
/*      */   {
/* 2063 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object makeJdbcArray(int paramInt)
/*      */   {
/* 2075 */     Timestamp[] arrayOfTimestamp = new Timestamp[paramInt];
/*      */     
/* 2077 */     return arrayOfTimestamp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isConvertibleTo(Class paramClass)
/*      */   {
/* 2089 */     if ((paramClass.getName().compareTo("java.sql.Date") == 0) || (paramClass.getName().compareTo("java.sql.Time") == 0) || (paramClass.getName().compareTo("java.sql.Timestamp") == 0) || (paramClass.getName().compareTo("java.lang.String") == 0))
/*      */     {
/*      */ 
/*      */ 
/* 2093 */       return true;
/*      */     }
/* 2095 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static Timestamp parseTime(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2110 */     if (paramString == null)
/*      */     {
/* 2112 */       localObject1 = DatabaseError.createSqlException(null, 68);
/* 2113 */       ((SQLException)localObject1).fillInStackTrace();
/* 2114 */       throw ((Throwable)localObject1);
/*      */     }
/*      */     
/*      */ 
/* 2118 */     Object localObject1 = paramString.trim();
/* 2119 */     int i = ((String)localObject1).indexOf(' ');
/*      */     
/* 2121 */     if (i == 0)
/*      */     {
/*      */ 
/* 2124 */       localObject2 = DatabaseError.createSqlException(null, 68);
/* 2125 */       ((SQLException)localObject2).fillInStackTrace();
/* 2126 */       throw ((Throwable)localObject2);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2134 */     Object localObject2 = ((String)localObject1).substring(0, i);
/* 2135 */     String str1 = ((String)localObject1).substring(i + 1);
/*      */     
/* 2137 */     if (((localObject2 == null ? 1 : 0) | (str1 == null ? 1 : 0)) != 0)
/*      */     {
/*      */ 
/* 2140 */       SQLException localSQLException1 = DatabaseError.createSqlException(null, 68);
/* 2141 */       localSQLException1.fillInStackTrace();
/* 2142 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2149 */     int j = ((String)localObject2).indexOf('-');
/* 2150 */     int k = ((String)localObject2).indexOf('-', j + 1);
/*      */     
/* 2152 */     if ((j < 1) || (k < 1) || (k == ((String)localObject2).length()))
/*      */     {
/*      */ 
/* 2155 */       localObject3 = DatabaseError.createSqlException(null, 68);
/* 2156 */       ((SQLException)localObject3).fillInStackTrace();
/* 2157 */       throw ((Throwable)localObject3);
/*      */     }
/*      */     
/*      */ 
/* 2161 */     Object localObject3 = ((String)localObject2).substring(0, j);
/* 2162 */     String str2 = ((String)localObject2).substring(j + 1, k);
/* 2163 */     String str3 = ((String)localObject2).substring(k + 1);
/*      */     
/* 2165 */     if ((((String)localObject3).length() != 4) || (str2.length() != 2) || (str3.length() != 2))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2170 */       SQLException localSQLException2 = DatabaseError.createSqlException(null, 68);
/* 2171 */       localSQLException2.fillInStackTrace();
/* 2172 */       throw localSQLException2;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2177 */     int m = Integer.parseInt((String)localObject3);
/* 2178 */     int n = Integer.parseInt(str2) - 1;
/* 2179 */     int i1 = Integer.parseInt(str3);
/*      */     
/*      */ 
/* 2182 */     int i2 = str1.indexOf(':');
/* 2183 */     int i3 = str1.indexOf(':', i2 + 1);
/*      */     
/*      */ 
/* 2186 */     if ((i2 < 1) || (i3 < 1) || (i3 == str1.length()))
/*      */     {
/*      */ 
/* 2189 */       localObject4 = DatabaseError.createSqlException(null, 68);
/* 2190 */       ((SQLException)localObject4).fillInStackTrace();
/* 2191 */       throw ((Throwable)localObject4);
/*      */     }
/*      */     
/*      */ 
/* 2195 */     Object localObject4 = str1.substring(0, i2);
/* 2196 */     String str4 = str1.substring(i2 + 1, i3);
/*      */     
/* 2198 */     if ((((String)localObject4).length() != 2) || (str4.length() != 2))
/*      */     {
/*      */ 
/* 2201 */       SQLException localSQLException3 = DatabaseError.createSqlException(null, 68);
/* 2202 */       localSQLException3.fillInStackTrace();
/* 2203 */       throw localSQLException3;
/*      */     }
/*      */     
/*      */ 
/* 2207 */     int i4 = Integer.parseInt((String)localObject4);
/* 2208 */     int i5 = Integer.parseInt(str4);
/*      */     
/*      */ 
/* 2211 */     int i7 = 0;
/* 2212 */     int i8 = str1.indexOf('.', i3 + 1);
/*      */     int i6;
/* 2214 */     if (i8 == -1)
/*      */     {
/*      */ 
/* 2217 */       i6 = Integer.parseInt(str1.substring(i3 + 1));
/*      */     }
/* 2219 */     else if ((i8 > 0) && (i8 < str1.length() - 1))
/*      */     {
/*      */ 
/* 2222 */       i6 = Integer.parseInt(str1.substring(i3 + 1, i8));
/*      */       
/* 2224 */       localObject5 = str1.substring(i8 + 1);
/* 2225 */       localObject6 = "000000000";
/*      */       
/* 2227 */       if ((((String)localObject5).length() > ((String)localObject6).length()) || (!Character.isDigit(((String)localObject5).charAt(0))))
/*      */       {
/*      */ 
/*      */ 
/* 2231 */         SQLException localSQLException4 = DatabaseError.createSqlException(null, 68);
/* 2232 */         localSQLException4.fillInStackTrace();
/* 2233 */         throw localSQLException4;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 2238 */       localObject5 = (String)localObject5 + ((String)localObject6).substring(0, ((String)localObject6).length() - ((String)localObject5).length());
/*      */       
/* 2240 */       i7 = Integer.parseInt((String)localObject5);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 2245 */       localObject5 = DatabaseError.createSqlException(null, 68);
/* 2246 */       ((SQLException)localObject5).fillInStackTrace();
/* 2247 */       throw ((Throwable)localObject5);
/*      */     }
/*      */     
/*      */ 
/* 2251 */     Object localObject5 = (Calendar)CAL_GMT_US.clone();
/* 2252 */     ((Calendar)localObject5).set(1, m);
/* 2253 */     ((Calendar)localObject5).set(2, n);
/* 2254 */     ((Calendar)localObject5).set(5, i1);
/* 2255 */     ((Calendar)localObject5).set(11, i4);
/* 2256 */     ((Calendar)localObject5).set(12, i5);
/* 2257 */     ((Calendar)localObject5).set(13, i6);
/* 2258 */     ((Calendar)localObject5).set(14, 0);
/*      */     
/* 2260 */     Object localObject6 = new Timestamp(((Calendar)localObject5).getTime().getTime());
/* 2261 */     ((Timestamp)localObject6).setNanos(i7);
/*      */     
/* 2263 */     return (Timestamp)localObject6;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 2268 */   private static int HOUR_MILLISECOND = 3600000;
/*      */   
/*      */ 
/* 2271 */   private static int MINUTE_MILLISECOND = 60000;
/*      */   
/*      */ 
/*      */ 
/* 2275 */   private static int OFFSET_HOUR = 20;
/* 2276 */   private static int OFFSET_MINUTE = 60;
/*      */   
/*      */ 
/*      */ 
/*      */   private static int setHighOrderbits(int paramInt)
/*      */   {
/* 2282 */     return (paramInt & 0x1FC0) >> 6;
/*      */   }
/*      */   
/*      */   private static int setLowOrderbits(int paramInt)
/*      */   {
/* 2287 */     return (paramInt & 0x3F) << 2;
/*      */   }
/*      */   
/*      */   private static int getHighOrderbits(int paramInt)
/*      */   {
/* 2292 */     return (paramInt & 0x7F) << 6;
/*      */   }
/*      */   
/*      */   private static int getLowOrderbits(int paramInt)
/*      */   {
/* 2297 */     return (paramInt & 0xFC) >> 2;
/*      */   }
/*      */   
/*      */ 
/* 2301 */   private static byte REGIONIDBIT = Byte.MIN_VALUE;
/*      */   
/*      */   static TIMEZONETAB getTIMEZONETAB(Connection paramConnection) throws SQLException
/*      */   {
/* 2305 */     oracle.jdbc.internal.OracleConnection localOracleConnection = ((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin();
/* 2306 */     return localOracleConnection.getTIMEZONETAB();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2312 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/TIMESTAMPTZ.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */