/*      */ package oracle.sql;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.Serializable;
/*      */ import java.sql.Connection;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.TimeZone;
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
/*      */ public class TIMESTAMP
/*      */   extends Datum
/*      */   implements Serializable
/*      */ {
/*      */   static final int CENTURY_DEFAULT = 119;
/*      */   static final int DECADE_DEFAULT = 100;
/*      */   static final int MONTH_DEFAULT = 1;
/*      */   static final int DAY_DEFAULT = 1;
/*      */   static final int DECADE_INIT = 170;
/*      */   static final int JAVA_YEAR = 1970;
/*      */   static final int JAVA_MONTH = 0;
/*      */   static final int JAVA_DATE = 1;
/*      */   public static final int SIZE_DATE = 7;
/*      */   public static final int SIZE_TIMESTAMP = 11;
/*      */   public static final int SIZE_TIMESTAMP_NOFRAC = 7;
/*      */   static final int SIZE_TIMESTAMPTZ = 13;
/*      */   static final int MINYEAR = -4712;
/*      */   static final int MAXYEAR = 9999;
/*      */   static final int JANMONTH = 1;
/*      */   static final int DECMONTH = 12;
/*      */   static final int MINDAYS = 1;
/*      */   static final int MAXDAYS = 31;
/*      */   static final int MINHOURS = 1;
/*      */   static final int MAXHOURS = 24;
/*      */   static final int MINMINUTES = 1;
/*      */   static final int MAXMINUTES = 60;
/*      */   static final int MINSECONDS = 1;
/*      */   static final int MAXSECONDS = 60;
/*      */   
/*      */   public TIMESTAMP()
/*      */   {
/*   78 */     super(initTimestamp());
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
/*      */   public TIMESTAMP(byte[] paramArrayOfByte)
/*      */   {
/*   92 */     super(paramArrayOfByte);
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
/*      */   public TIMESTAMP(Time paramTime)
/*      */   {
/*  107 */     super(toBytes(paramTime));
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
/*      */   public TIMESTAMP(java.sql.Date paramDate)
/*      */   {
/*  121 */     super(toBytes(paramDate));
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
/*      */   public TIMESTAMP(Timestamp paramTimestamp)
/*      */   {
/*  135 */     super(toBytes(paramTimestamp));
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
/*      */   public TIMESTAMP(Timestamp paramTimestamp, Calendar paramCalendar)
/*      */   {
/*  152 */     super(toBytes(paramTimestamp, paramCalendar));
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
/*      */   public TIMESTAMP(DATE paramDATE)
/*      */   {
/*  167 */     super(toBytes(paramDATE));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final int getNanos(byte[] paramArrayOfByte, int paramInt)
/*      */   {
/*  175 */     int i = (paramArrayOfByte[paramInt] & 0xFF) << 24;
/*  176 */     i |= (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 16;
/*  177 */     i |= (paramArrayOfByte[(paramInt + 2)] & 0xFF) << 8;
/*  178 */     i |= paramArrayOfByte[(paramInt + 3)] & 0xFF & 0xFF;
/*  179 */     return i;
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
/*      */   public TIMESTAMP(String paramString)
/*      */   {
/*  193 */     super(toBytes(paramString));
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
/*      */ 
/*      */ 
/*      */   public static java.sql.Date toDate(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  225 */     int i = paramArrayOfByte.length;
/*      */     int[] arrayOfInt;
/*  227 */     if (i == 11) {
/*  228 */       arrayOfInt = new int[11];
/*      */     } else {
/*  230 */       arrayOfInt = new int[7];
/*      */     }
/*  232 */     for (int j = 0; j < paramArrayOfByte.length; j++) {
/*  233 */       paramArrayOfByte[j] &= 0xFF;
/*      */     }
/*      */     
/*  236 */     j = getJavaYear(arrayOfInt[0], arrayOfInt[1]);
/*      */     
/*      */ 
/*  239 */     Calendar localCalendar = Calendar.getInstance();
/*      */     
/*  241 */     localCalendar.set(1, j);
/*  242 */     localCalendar.set(2, arrayOfInt[2] - 1);
/*  243 */     localCalendar.set(5, arrayOfInt[3]);
/*  244 */     localCalendar.set(11, arrayOfInt[4] - 1);
/*  245 */     localCalendar.set(12, arrayOfInt[5] - 1);
/*  246 */     localCalendar.set(13, arrayOfInt[6] - 1);
/*  247 */     localCalendar.set(14, 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  253 */     long l = localCalendar.getTime().getTime();
/*      */     
/*      */ 
/*      */ 
/*  257 */     return new java.sql.Date(l);
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
/*      */   public static Time toTime(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  279 */     int i = paramArrayOfByte[4] & 0xFF;
/*  280 */     int j = paramArrayOfByte[5] & 0xFF;
/*  281 */     int k = paramArrayOfByte[6] & 0xFF;
/*      */     
/*  283 */     return new Time(i - 1, j - 1, k - 1);
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
/*      */   public static Timestamp toTimestamp(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  302 */     int i = paramArrayOfByte.length;
/*      */     int[] arrayOfInt;
/*  304 */     if (i == 11) {
/*  305 */       arrayOfInt = new int[11];
/*      */     } else {
/*  307 */       arrayOfInt = new int[7];
/*      */     }
/*  309 */     for (int j = 0; j < paramArrayOfByte.length; j++) {
/*  310 */       paramArrayOfByte[j] &= 0xFF;
/*      */     }
/*      */     
/*  313 */     j = getJavaYear(arrayOfInt[0], arrayOfInt[1]);
/*      */     
/*      */ 
/*  316 */     Calendar localCalendar = Calendar.getInstance();
/*      */     
/*  318 */     localCalendar.set(1, j);
/*  319 */     localCalendar.set(2, arrayOfInt[2] - 1);
/*  320 */     localCalendar.set(5, arrayOfInt[3]);
/*  321 */     localCalendar.set(11, arrayOfInt[4] - 1);
/*  322 */     localCalendar.set(12, arrayOfInt[5] - 1);
/*  323 */     localCalendar.set(13, arrayOfInt[6] - 1);
/*  324 */     localCalendar.set(14, 0);
/*      */     
/*      */ 
/*      */ 
/*  328 */     long l = localCalendar.getTime().getTime();
/*      */     
/*      */ 
/*      */ 
/*  332 */     Timestamp localTimestamp = new Timestamp(l);
/*      */     
/*      */ 
/*  335 */     int k = 0;
/*  336 */     if (i == 11) {
/*  337 */       k = getNanos(paramArrayOfByte, 7);
/*      */     }
/*      */     
/*  340 */     localTimestamp.setNanos(k);
/*      */     
/*  342 */     return localTimestamp;
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
/*      */   public static Timestamp toTimestamp(byte[] paramArrayOfByte, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  358 */     int i = paramArrayOfByte.length;
/*      */     int[] arrayOfInt;
/*  360 */     if (i == 11) {
/*  361 */       arrayOfInt = new int[11];
/*      */     } else {
/*  363 */       arrayOfInt = new int[7];
/*      */     }
/*  365 */     for (int j = 0; j < paramArrayOfByte.length; j++) {
/*  366 */       paramArrayOfByte[j] &= 0xFF;
/*      */     }
/*      */     
/*  369 */     j = getJavaYear(arrayOfInt[0], arrayOfInt[1]);
/*      */     
/*  371 */     if (paramCalendar == null) {
/*  372 */       paramCalendar = Calendar.getInstance();
/*      */     }
/*  374 */     paramCalendar.clear();
/*      */     
/*  376 */     paramCalendar.set(1, j);
/*  377 */     paramCalendar.set(2, arrayOfInt[2] - 1);
/*  378 */     paramCalendar.set(5, arrayOfInt[3]);
/*  379 */     paramCalendar.set(11, arrayOfInt[4] - 1);
/*  380 */     paramCalendar.set(12, arrayOfInt[5] - 1);
/*  381 */     paramCalendar.set(13, arrayOfInt[6] - 1);
/*  382 */     paramCalendar.set(14, 0);
/*      */     
/*      */ 
/*  385 */     long l = paramCalendar.getTime().getTime();
/*      */     
/*      */ 
/*  388 */     Timestamp localTimestamp = new Timestamp(l);
/*      */     
/*  390 */     int k = 0;
/*      */     
/*  392 */     if (i == 11) {
/*  393 */       k = getNanos(paramArrayOfByte, 7);
/*      */     }
/*      */     
/*  396 */     localTimestamp.setNanos(k);
/*      */     
/*  398 */     return localTimestamp;
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
/*      */   public static DATE toDATE(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  413 */     byte[] arrayOfByte = new byte[7];
/*      */     
/*  415 */     System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, 7);
/*      */     
/*  417 */     return new DATE(arrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp timestampValue()
/*      */     throws SQLException
/*      */   {
/*  430 */     return toTimestamp(getBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp timestampValue(Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  442 */     return toTimestamp(getBytes(), paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String toString(byte[] paramArrayOfByte)
/*      */   {
/*  454 */     int[] arrayOfInt = new int[paramArrayOfByte.length];
/*      */     
/*  456 */     for (int i = 0; i < paramArrayOfByte.length; i++)
/*      */     {
/*  458 */       if (paramArrayOfByte[i] < 0) {
/*  459 */         paramArrayOfByte[i] += 256;
/*      */       } else {
/*  461 */         arrayOfInt[i] = paramArrayOfByte[i];
/*      */       }
/*      */     }
/*  464 */     i = getJavaYear(arrayOfInt[0], arrayOfInt[1]);
/*  465 */     int j = arrayOfInt[2];
/*  466 */     int k = arrayOfInt[3];
/*  467 */     int m = arrayOfInt[4] - 1;
/*  468 */     int n = arrayOfInt[5] - 1;
/*  469 */     int i1 = arrayOfInt[6] - 1;
/*  470 */     int i2 = 0;
/*      */     
/*  472 */     if (paramArrayOfByte.length > 7)
/*      */     {
/*      */ 
/*  475 */       i2 = getNanos(paramArrayOfByte, 7);
/*      */     }
/*      */     
/*  478 */     return TIMESTAMPTZ.toString(i, j, k, m, n, i1, i2, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] toBytes()
/*      */   {
/*  488 */     return getBytes();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(Time paramTime)
/*      */   {
/*  500 */     if (paramTime == null) {
/*  501 */       return null;
/*      */     }
/*  503 */     byte[] arrayOfByte = new byte[7];
/*  504 */     Calendar localCalendar = Calendar.getInstance();
/*  505 */     localCalendar.setTime(paramTime);
/*      */     
/*  507 */     arrayOfByte[0] = 119;
/*  508 */     arrayOfByte[1] = -86;
/*  509 */     arrayOfByte[2] = 1;
/*  510 */     arrayOfByte[3] = 1;
/*  511 */     arrayOfByte[4] = ((byte)(localCalendar.get(11) + 1));
/*  512 */     arrayOfByte[5] = ((byte)(localCalendar.get(12) + 1));
/*  513 */     arrayOfByte[6] = ((byte)(localCalendar.get(13) + 1));
/*      */     
/*  515 */     return arrayOfByte;
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
/*      */   public static byte[] toBytes(java.sql.Date paramDate)
/*      */   {
/*  529 */     if (paramDate == null) {
/*  530 */       return null;
/*      */     }
/*  532 */     byte[] arrayOfByte = new byte[7];
/*  533 */     Calendar localCalendar = Calendar.getInstance();
/*      */     
/*  535 */     localCalendar.setTime(paramDate);
/*      */     
/*  537 */     arrayOfByte[0] = ((byte)(localCalendar.get(1) / 100 + 100));
/*  538 */     arrayOfByte[1] = ((byte)(localCalendar.get(1) % 100 + 100));
/*  539 */     arrayOfByte[2] = ((byte)(localCalendar.get(2) + 1));
/*  540 */     arrayOfByte[3] = ((byte)localCalendar.get(5));
/*  541 */     arrayOfByte[4] = 1;
/*  542 */     arrayOfByte[5] = 1;
/*  543 */     arrayOfByte[6] = 1;
/*      */     
/*  545 */     return arrayOfByte;
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
/*      */   public static byte[] toBytes(Timestamp paramTimestamp)
/*      */   {
/*  558 */     if (paramTimestamp == null) {
/*  559 */       return null;
/*      */     }
/*      */     
/*      */ 
/*  563 */     int i = paramTimestamp.getNanos();
/*      */     
/*      */     byte[] arrayOfByte;
/*      */     
/*  567 */     if (i == 0) {
/*  568 */       arrayOfByte = new byte[7];
/*      */     } else {
/*  570 */       arrayOfByte = new byte[11];
/*      */     }
/*  572 */     Calendar localCalendar = Calendar.getInstance();
/*      */     
/*  574 */     localCalendar.setTime(paramTimestamp);
/*      */     
/*      */ 
/*  577 */     arrayOfByte[0] = ((byte)(localCalendar.get(1) / 100 + 100));
/*  578 */     arrayOfByte[1] = ((byte)(localCalendar.get(1) % 100 + 100));
/*  579 */     arrayOfByte[2] = ((byte)(localCalendar.get(2) + 1));
/*  580 */     arrayOfByte[3] = ((byte)localCalendar.get(5));
/*  581 */     arrayOfByte[4] = ((byte)(localCalendar.get(11) + 1));
/*  582 */     arrayOfByte[5] = ((byte)(localCalendar.get(12) + 1));
/*  583 */     arrayOfByte[6] = ((byte)(localCalendar.get(13) + 1));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  590 */     if (i != 0)
/*      */     {
/*  592 */       arrayOfByte[7] = ((byte)(i >> 24));
/*  593 */       arrayOfByte[8] = ((byte)(i >> 16 & 0xFF));
/*  594 */       arrayOfByte[9] = ((byte)(i >> 8 & 0xFF));
/*  595 */       arrayOfByte[10] = ((byte)(i & 0xFF));
/*      */     }
/*      */     
/*  598 */     return arrayOfByte;
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
/*      */   public static byte[] toBytes(Timestamp paramTimestamp, Calendar paramCalendar)
/*      */   {
/*  614 */     if (paramTimestamp == null) {
/*  615 */       return null;
/*      */     }
/*      */     
/*      */ 
/*  619 */     int i = paramTimestamp.getNanos();
/*      */     
/*      */     byte[] arrayOfByte;
/*      */     
/*  623 */     if (i == 0) {
/*  624 */       arrayOfByte = new byte[7];
/*      */     } else {
/*  626 */       arrayOfByte = new byte[11];
/*      */     }
/*      */     
/*  629 */     if (paramCalendar == null) {
/*  630 */       paramCalendar = Calendar.getInstance();
/*      */     }
/*  632 */     paramCalendar.clear();
/*  633 */     paramCalendar.setTime(paramTimestamp);
/*      */     
/*      */ 
/*  636 */     int j = paramCalendar.get(1);
/*      */     
/*      */ 
/*  639 */     if (paramCalendar.get(0) == 0) {
/*  640 */       j = -(j - 1);
/*      */     }
/*  642 */     if ((j < 60824) || (j > 9999))
/*      */     {
/*  644 */       throw new IllegalArgumentException("Invalid year value");
/*      */     }
/*      */     
/*  647 */     arrayOfByte[0] = ((byte)(j / 100 + 100));
/*  648 */     arrayOfByte[1] = ((byte)(j % 100 + 100));
/*  649 */     arrayOfByte[2] = ((byte)(paramCalendar.get(2) + 1));
/*  650 */     arrayOfByte[3] = ((byte)paramCalendar.get(5));
/*  651 */     arrayOfByte[4] = ((byte)(paramCalendar.get(11) + 1));
/*  652 */     arrayOfByte[5] = ((byte)(paramCalendar.get(12) + 1));
/*  653 */     arrayOfByte[6] = ((byte)(paramCalendar.get(13) + 1));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  660 */     if (i != 0)
/*      */     {
/*  662 */       arrayOfByte[7] = ((byte)(i >> 24));
/*  663 */       arrayOfByte[8] = ((byte)(i >> 16 & 0xFF));
/*  664 */       arrayOfByte[9] = ((byte)(i >> 8 & 0xFF));
/*  665 */       arrayOfByte[10] = ((byte)(i & 0xFF));
/*      */     }
/*      */     
/*  668 */     return arrayOfByte;
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
/*      */   public static byte[] toBytes(DATE paramDATE)
/*      */   {
/*  682 */     if (paramDATE == null) {
/*  683 */       return null;
/*      */     }
/*  685 */     byte[] arrayOfByte = new byte[7];
/*      */     
/*  687 */     System.arraycopy(paramDATE.getBytes(), 0, arrayOfByte, 0, 7);
/*      */     
/*  689 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(String paramString)
/*      */   {
/*  701 */     return toBytes(Timestamp.valueOf(paramString));
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
/*  713 */     return timestampValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object makeJdbcArray(int paramInt)
/*      */   {
/*  724 */     Timestamp[] arrayOfTimestamp = new Timestamp[paramInt];
/*      */     
/*  726 */     return arrayOfTimestamp;
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
/*  738 */     if ((paramClass.getName().compareTo("java.sql.Date") == 0) || (paramClass.getName().compareTo("java.sql.Time") == 0) || (paramClass.getName().compareTo("java.sql.Timestamp") == 0) || (paramClass.getName().compareTo("java.lang.String") == 0))
/*      */     {
/*      */ 
/*      */ 
/*  742 */       return true;
/*      */     }
/*  744 */     return false;
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
/*      */   public static TIMESTAMP TimeZoneConvert(Connection paramConnection, TIMESTAMP paramTIMESTAMP, TimeZone paramTimeZone1, TimeZone paramTimeZone2)
/*      */     throws SQLException
/*      */   {
/*  772 */     byte[] arrayOfByte = paramTIMESTAMP.getBytes();
/*      */     
/*  774 */     int i = arrayOfByte.length;
/*      */     int[] arrayOfInt;
/*  776 */     if (i == 11) {
/*  777 */       arrayOfInt = new int[11];
/*      */     }
/*      */     else {
/*  780 */       arrayOfInt = new int[7];
/*      */     }
/*      */     
/*      */ 
/*  784 */     for (int j = 0; j < i; j++) {
/*  785 */       arrayOfByte[j] &= 0xFF;
/*      */     }
/*      */     
/*      */ 
/*  789 */     j = getJavaYear(arrayOfInt[0], arrayOfInt[1]);
/*  790 */     int k = arrayOfInt[2] - 1;
/*  791 */     int m = arrayOfInt[3];
/*  792 */     int n = arrayOfInt[4] - 1;
/*  793 */     int i1 = arrayOfInt[5] - 1;
/*  794 */     int i2 = arrayOfInt[6] - 1;
/*      */     
/*  796 */     Calendar localCalendar1 = Calendar.getInstance(paramTimeZone1);
/*      */     
/*      */ 
/*  799 */     localCalendar1.set(1, j);
/*  800 */     localCalendar1.set(2, k);
/*  801 */     localCalendar1.set(5, m);
/*  802 */     localCalendar1.set(11, n);
/*  803 */     localCalendar1.set(12, i1);
/*  804 */     localCalendar1.set(13, i2);
/*      */     
/*      */ 
/*      */ 
/*  808 */     localCalendar1.set(14, 0);
/*      */     
/*      */ 
/*  811 */     long l = localCalendar1.getTimeInMillis();
/*      */     
/*      */ 
/*  814 */     Timestamp localTimestamp = new Timestamp(l);
/*      */     
/*  816 */     int i3 = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  821 */     if (i == 11) {
/*  822 */       i3 = getNanos(arrayOfByte, 7);
/*      */     }
/*  824 */     localTimestamp.setNanos(i3);
/*      */     
/*      */ 
/*  827 */     Calendar localCalendar2 = Calendar.getInstance(paramTimeZone2);
/*  828 */     return new TIMESTAMP(localTimestamp, localCalendar2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String stringValue()
/*      */   {
/*  838 */     return toString(getBytes());
/*      */   }
/*      */   
/*      */   public String toString()
/*      */   {
/*  843 */     return stringValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public java.sql.Date dateValue()
/*      */     throws SQLException
/*      */   {
/*  855 */     return toDate(getBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time timeValue()
/*      */     throws SQLException
/*      */   {
/*  866 */     return toTime(getBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int getJavaYear(int paramInt1, int paramInt2)
/*      */   {
/*  874 */     int i = (paramInt1 - 100) * 100 + (paramInt2 - 100);
/*      */     
/*  876 */     if (i <= 0) {
/*  877 */       i++;
/*      */     }
/*      */     
/*  880 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static byte[] initTimestamp()
/*      */   {
/*  892 */     byte[] arrayOfByte = new byte[11];
/*      */     
/*  894 */     arrayOfByte[0] = 119;
/*  895 */     arrayOfByte[1] = -86;
/*  896 */     arrayOfByte[2] = 1;
/*  897 */     arrayOfByte[3] = 1;
/*  898 */     arrayOfByte[4] = 1;
/*  899 */     arrayOfByte[5] = 1;
/*  900 */     arrayOfByte[6] = 1;
/*      */     
/*  902 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean isLeapYear(int paramInt)
/*      */   {
/*  913 */     return (paramInt % 4 == 0) && (paramInt <= 1582 ? paramInt == 60824 : (paramInt % 100 != 0) || (paramInt % 400 == 0));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean isValid()
/*      */   {
/*  923 */     byte[] arrayOfByte = getBytes();
/*  924 */     if ((arrayOfByte.length != 11) && (arrayOfByte.length != 7))
/*      */     {
/*      */ 
/*  927 */       return false;
/*      */     }
/*      */     
/*  930 */     int i = ((arrayOfByte[0] & 0xFF) - 100) * 100 + ((arrayOfByte[1] & 0xFF) - 100);
/*  931 */     if ((i < 60824) || (i > 9999)) {
/*  932 */       return false;
/*      */     }
/*      */     
/*  935 */     if (i == 0) {
/*  936 */       return false;
/*      */     }
/*      */     
/*  939 */     int j = arrayOfByte[2] & 0xFF;
/*  940 */     if ((j < 1) || (j > 12)) {
/*  941 */       return false;
/*      */     }
/*      */     
/*  944 */     int k = arrayOfByte[3] & 0xFF;
/*  945 */     if ((k < 1) || (k > 31))
/*  946 */       return false;
/*  947 */     if ((k > daysInMonth[(j - 1)]) && (
/*  948 */       (!isLeapYear(i)) || (j != 2) || (k != 29))) {
/*  949 */       return false;
/*      */     }
/*      */     
/*  952 */     if ((i == 1582) && (j == 10) && (k >= 5) && (k < 15)) {
/*  953 */       return false;
/*      */     }
/*      */     
/*  956 */     int m = arrayOfByte[4] & 0xFF;
/*  957 */     if ((m < 1) || (m > 24)) {
/*  958 */       return false;
/*      */     }
/*      */     
/*  961 */     int n = arrayOfByte[5] & 0xFF;
/*  962 */     if ((n < 1) || (n > 60)) {
/*  963 */       return false;
/*      */     }
/*      */     
/*  966 */     int i1 = arrayOfByte[6] & 0xFF;
/*  967 */     if ((i1 < 1) || (i1 > 60)) {
/*  968 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  973 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */   private void readObject(ObjectInputStream paramObjectInputStream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/*  980 */     paramObjectInputStream.defaultReadObject();
/*  981 */     if (!isValid()) {
/*  982 */       throw new IOException("Invalid TIMESTAMP");
/*      */     }
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
/* 1020 */   static final int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
/*      */   
/*      */ 
/*      */ 
/*      */   static final long serialVersionUID = -7964732752952728545L;
/*      */   
/*      */ 
/* 1027 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/TIMESTAMP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */