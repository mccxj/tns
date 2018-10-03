/*      */ package oracle.sql;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.text.ParseException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Calendar;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DATE
/*      */   extends Datum
/*      */ {
/*      */   static final long serialVersionUID = 5229717576495161269L;
/*      */   public static final int BDA = 1;
/*      */   public static final int BDAL = 2;
/*      */   public static final int BMO = 4;
/*      */   public static final int BMOL = 8;
/*      */   public static final int BYR = 16;
/*      */   public static final int BYRL = 32;
/*      */   public static final int BHR = 64;
/*      */   public static final int BHRL = 128;
/*      */   public static final int BMN = 256;
/*      */   public static final int BMNL = 512;
/*      */   public static final int BSC = 1024;
/*      */   public static final int BSCL = 2048;
/*      */   public static final int MSD = 4096;
/*      */   public static final int YR0 = 8192;
/*      */   public static final int BDT = 32768;
/*      */   public static final int HRZER0 = 65536;
/*      */   public static final int MIZERO = 131072;
/*      */   public static final int SEZERO = 262144;
/*      */   private static final byte LDXTCE = 0;
/*      */   private static final byte LDXTYE = 1;
/*      */   private static final byte LDXTMO = 2;
/*      */   private static final byte LDXTDA = 3;
/*      */   private static final byte LDXTHO = 4;
/*      */   private static final byte LDXTMI = 5;
/*      */   private static final byte LDXTSE = 6;
/*      */   private static LdxLib _sldxlib;
/*      */   
/*      */   public DATE()
/*      */   {
/*   70 */     super(_initDate());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DATE(byte[] paramArrayOfByte)
/*      */   {
/*   81 */     super(paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DATE(java.sql.Date paramDate)
/*      */   {
/*   93 */     super(toBytes(paramDate));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DATE(Time paramTime)
/*      */   {
/*  105 */     super(toBytes(paramTime));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DATE(Timestamp paramTimestamp)
/*      */   {
/*  117 */     super(toBytes(paramTimestamp));
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
/*      */   public DATE(java.sql.Date paramDate, Calendar paramCalendar)
/*      */   {
/*  131 */     super(toBytes(paramDate, paramCalendar));
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
/*      */   public DATE(Time paramTime, Calendar paramCalendar)
/*      */   {
/*  145 */     super(toBytes(paramTime, paramCalendar));
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
/*      */   public DATE(Timestamp paramTimestamp, Calendar paramCalendar)
/*      */   {
/*  159 */     super(toBytes(paramTimestamp, paramCalendar));
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
/*      */   public DATE(String paramString)
/*      */   {
/*  172 */     super(toBytes(paramString));
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
/*      */   public DATE(String paramString, boolean paramBoolean)
/*      */     throws ParseException
/*      */   {
/*  188 */     super(toBytes(paramString));
/*  189 */     if (!paramBoolean)
/*      */     {
/*      */ 
/*      */ 
/*  193 */       SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*      */       
/*      */ 
/*  196 */       localSimpleDateFormat.setLenient(false);
/*      */       
/*  198 */       java.util.Date localDate = localSimpleDateFormat.parse(paramString);
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
/*      */   public DATE(String paramString, Calendar paramCalendar)
/*      */   {
/*  213 */     super(toBytes(paramString, paramCalendar));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DATE(Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  226 */     if ((paramObject instanceof java.sql.Date)) {
/*  227 */       setShareBytes(toBytes((java.sql.Date)paramObject));
/*  228 */     } else if ((paramObject instanceof Time)) {
/*  229 */       setShareBytes(toBytes((Time)paramObject));
/*  230 */     } else if ((paramObject instanceof Timestamp)) {
/*  231 */       setShareBytes(toBytes((Timestamp)paramObject));
/*  232 */     } else if ((paramObject instanceof String)) {
/*  233 */       setShareBytes(toBytes((String)paramObject));
/*      */     } else {
/*  235 */       throw new SQLException("Initialization failed");
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
/*      */   public DATE(Object paramObject, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  251 */     if ((paramObject instanceof java.sql.Date)) {
/*  252 */       setShareBytes(toBytes((java.sql.Date)paramObject, paramCalendar));
/*  253 */     } else if ((paramObject instanceof Time)) {
/*  254 */       setShareBytes(toBytes((Time)paramObject, paramCalendar));
/*  255 */     } else if ((paramObject instanceof Timestamp)) {
/*  256 */       setShareBytes(toBytes((Timestamp)paramObject, paramCalendar));
/*  257 */     } else if ((paramObject instanceof String)) {
/*  258 */       setShareBytes(toBytes((String)paramObject, paramCalendar));
/*      */     } else {
/*  260 */       throw new SQLException("Initialization failed");
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
/*      */   public static java.sql.Date toDate(byte[] paramArrayOfByte)
/*      */   {
/*  281 */     int[] arrayOfInt = new int[7];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  286 */     for (int i = 0; i < 7; i++) {
/*  287 */       paramArrayOfByte[i] &= 0xFF;
/*      */     }
/*      */     
/*      */ 
/*  291 */     i = (arrayOfInt[0] - 100) * 100 + (arrayOfInt[1] - 100);
/*  292 */     int j = i - 1900;
/*      */     
/*  294 */     if (i <= 0) {
/*  295 */       j++;
/*      */     }
/*  297 */     return new java.sql.Date(j, arrayOfInt[2] - 1, arrayOfInt[3]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Time toTime(byte[] paramArrayOfByte)
/*      */   {
/*  309 */     int[] arrayOfInt = new int[7];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  314 */     for (int i = 0; i < 7; i++) {
/*  315 */       paramArrayOfByte[i] &= 0xFF;
/*      */     }
/*  317 */     return new Time(arrayOfInt[4] - 1, arrayOfInt[5] - 1, arrayOfInt[6] - 1);
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
/*      */   public static Timestamp toTimestamp(byte[] paramArrayOfByte)
/*      */   {
/*  330 */     int[] arrayOfInt = new int[7];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  335 */     for (int i = 0; i < 7; i++) {
/*  336 */       paramArrayOfByte[i] &= 0xFF;
/*      */     }
/*      */     
/*  339 */     i = (arrayOfInt[0] - 100) * 100 + (arrayOfInt[1] - 100);
/*  340 */     int j = i - 1900;
/*      */     
/*  342 */     if (i <= 0) {
/*  343 */       j++;
/*      */     }
/*  345 */     return new Timestamp(j, arrayOfInt[2] - 1, arrayOfInt[3], arrayOfInt[4] - 1, arrayOfInt[5] - 1, arrayOfInt[6] - 1, 0);
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
/*      */   public static java.sql.Date toDate(byte[] paramArrayOfByte, Calendar paramCalendar)
/*      */   {
/*  362 */     int[] arrayOfInt = new int[7];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  367 */     for (int i = 0; i < 7; i++) {
/*  368 */       paramArrayOfByte[i] &= 0xFF;
/*      */     }
/*      */     
/*  371 */     i = (arrayOfInt[0] - 100) * 100 + (arrayOfInt[1] - 100);
/*      */     
/*  373 */     if (paramCalendar == null) {
/*  374 */       paramCalendar = Calendar.getInstance();
/*      */     }
/*  376 */     paramCalendar.clear();
/*      */     
/*  378 */     paramCalendar.set(1, i);
/*  379 */     paramCalendar.set(2, arrayOfInt[2] - 1);
/*  380 */     paramCalendar.set(5, arrayOfInt[3]);
/*  381 */     paramCalendar.set(11, 0);
/*  382 */     paramCalendar.set(12, 0);
/*  383 */     paramCalendar.set(13, 0);
/*  384 */     paramCalendar.set(14, 0);
/*      */     
/*  386 */     java.sql.Date localDate = new java.sql.Date(paramCalendar.getTime().getTime());
/*      */     
/*  388 */     return localDate;
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
/*      */   public static Time toTime(byte[] paramArrayOfByte, Calendar paramCalendar)
/*      */   {
/*  403 */     int[] arrayOfInt = new int[7];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  408 */     for (int i = 0; i < 7; i++) {
/*  409 */       paramArrayOfByte[i] &= 0xFF;
/*      */     }
/*  411 */     if (paramCalendar == null) {
/*  412 */       paramCalendar = Calendar.getInstance();
/*      */     }
/*  414 */     paramCalendar.clear();
/*      */     
/*  416 */     paramCalendar.set(1, 1970);
/*  417 */     paramCalendar.set(2, 0);
/*  418 */     paramCalendar.set(5, 1);
/*  419 */     paramCalendar.set(11, arrayOfInt[4] - 1);
/*  420 */     paramCalendar.set(12, arrayOfInt[5] - 1);
/*  421 */     paramCalendar.set(13, arrayOfInt[6] - 1);
/*  422 */     paramCalendar.set(14, 0);
/*      */     
/*  424 */     Time localTime = new Time(paramCalendar.getTime().getTime());
/*      */     
/*  426 */     return localTime;
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
/*      */   public static Timestamp toTimestamp(byte[] paramArrayOfByte, Calendar paramCalendar)
/*      */   {
/*  440 */     int[] arrayOfInt = new int[7];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  445 */     for (int i = 0; i < 7; i++) {
/*  446 */       paramArrayOfByte[i] &= 0xFF;
/*      */     }
/*      */     
/*  449 */     i = (arrayOfInt[0] - 100) * 100 + (arrayOfInt[1] - 100);
/*      */     
/*  451 */     if (paramCalendar == null) {
/*  452 */       paramCalendar = Calendar.getInstance();
/*      */     }
/*  454 */     paramCalendar.clear();
/*      */     
/*  456 */     paramCalendar.set(1, i);
/*  457 */     paramCalendar.set(2, arrayOfInt[2] - 1);
/*  458 */     paramCalendar.set(5, arrayOfInt[3]);
/*  459 */     paramCalendar.set(11, arrayOfInt[4] - 1);
/*  460 */     paramCalendar.set(12, arrayOfInt[5] - 1);
/*  461 */     paramCalendar.set(13, arrayOfInt[6] - 1);
/*  462 */     paramCalendar.set(14, 0);
/*      */     
/*  464 */     Timestamp localTimestamp = new Timestamp(paramCalendar.getTime().getTime());
/*      */     
/*      */ 
/*  467 */     return localTimestamp;
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
/*  479 */     int[] arrayOfInt = new int[7];
/*  480 */     for (int i = 0; i < 7; i++)
/*      */     {
/*  482 */       if (paramArrayOfByte[i] < 0) {
/*  483 */         paramArrayOfByte[i] += 256;
/*      */       } else
/*  485 */         arrayOfInt[i] = paramArrayOfByte[i];
/*      */     }
/*  487 */     i = (arrayOfInt[0] - 100) * 100 + (arrayOfInt[1] - 100);
/*  488 */     int j = arrayOfInt[2];
/*  489 */     int k = arrayOfInt[3];
/*  490 */     int m = arrayOfInt[4] - 1;
/*  491 */     int n = arrayOfInt[5] - 1;
/*  492 */     int i1 = arrayOfInt[6] - 1;
/*  493 */     int i2 = -1;
/*      */     
/*  495 */     return TIMESTAMPTZ.toString(i, j, k, m, n, i1, -1, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] toBytes()
/*      */   {
/*  505 */     return getBytes();
/*      */   }
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
/*  517 */     if (paramDate == null) {
/*  518 */       return null;
/*      */     }
/*  520 */     byte[] arrayOfByte = new byte[7];
/*  521 */     Calendar localCalendar = Calendar.getInstance();
/*      */     
/*      */ 
/*  524 */     localCalendar.setTime(paramDate);
/*      */     
/*      */ 
/*  527 */     int i = localCalendar.get(1);
/*      */     
/*      */ 
/*  530 */     if (localCalendar.get(0) == 0) {
/*  531 */       i = -i;
/*      */     }
/*  533 */     if ((i < 60824) || (i > 9999))
/*      */     {
/*  535 */       throw new IllegalArgumentException("Invalid year value");
/*      */     }
/*      */     
/*  538 */     arrayOfByte[0] = ((byte)(i / 100 + 100));
/*  539 */     arrayOfByte[1] = ((byte)(i % 100 + 100));
/*  540 */     arrayOfByte[2] = ((byte)(localCalendar.get(2) + 1));
/*  541 */     arrayOfByte[3] = ((byte)localCalendar.get(5));
/*  542 */     arrayOfByte[4] = 1;
/*  543 */     arrayOfByte[5] = 1;
/*  544 */     arrayOfByte[6] = 1;
/*      */     
/*  546 */     return arrayOfByte;
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
/*  558 */     if (paramTime == null) {
/*  559 */       return null;
/*      */     }
/*  561 */     byte[] arrayOfByte = new byte[7];
/*  562 */     Calendar localCalendar = Calendar.getInstance();
/*      */     
/*  564 */     localCalendar.setTime(paramTime);
/*      */     
/*      */ 
/*  567 */     arrayOfByte[0] = 119;
/*  568 */     arrayOfByte[1] = -86;
/*  569 */     arrayOfByte[2] = 1;
/*  570 */     arrayOfByte[3] = 1;
/*  571 */     arrayOfByte[4] = ((byte)(localCalendar.get(11) + 1));
/*  572 */     arrayOfByte[5] = ((byte)(localCalendar.get(12) + 1));
/*  573 */     arrayOfByte[6] = ((byte)(localCalendar.get(13) + 1));
/*      */     
/*  575 */     return arrayOfByte;
/*      */   }
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
/*  587 */     if (paramTimestamp == null) {
/*  588 */       return null;
/*      */     }
/*  590 */     byte[] arrayOfByte = new byte[7];
/*  591 */     Calendar localCalendar = Calendar.getInstance();
/*      */     
/*  593 */     localCalendar.setTime(paramTimestamp);
/*      */     
/*      */ 
/*  596 */     int i = localCalendar.get(1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  601 */     if (localCalendar.get(0) == 0) {
/*  602 */       i = -i;
/*      */     }
/*  604 */     if ((i < 60824) || (i > 9999))
/*      */     {
/*  606 */       throw new IllegalArgumentException("Invalid year value");
/*      */     }
/*      */     
/*  609 */     arrayOfByte[0] = ((byte)(i / 100 + 100));
/*  610 */     arrayOfByte[1] = ((byte)(i % 100 + 100));
/*  611 */     arrayOfByte[2] = ((byte)(localCalendar.get(2) + 1));
/*  612 */     arrayOfByte[3] = ((byte)localCalendar.get(5));
/*  613 */     arrayOfByte[4] = ((byte)(localCalendar.get(11) + 1));
/*  614 */     arrayOfByte[5] = ((byte)(localCalendar.get(12) + 1));
/*  615 */     arrayOfByte[6] = ((byte)(localCalendar.get(13) + 1));
/*      */     
/*  617 */     return arrayOfByte;
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
/*      */   public static byte[] toBytes(java.sql.Date paramDate, Calendar paramCalendar)
/*      */   {
/*  632 */     if (paramDate == null) {
/*  633 */       return null;
/*      */     }
/*  635 */     if (paramCalendar == null) {
/*  636 */       paramCalendar = Calendar.getInstance();
/*      */     }
/*  638 */     paramCalendar.clear();
/*  639 */     paramCalendar.setTime(paramDate);
/*      */     
/*  641 */     byte[] arrayOfByte = new byte[7];
/*      */     
/*      */ 
/*  644 */     int i = paramCalendar.get(1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  649 */     if (paramCalendar.get(0) == 0) {
/*  650 */       i = -i;
/*      */     }
/*  652 */     if ((i < 60824) || (i > 9999))
/*      */     {
/*  654 */       throw new IllegalArgumentException("Invalid year value");
/*      */     }
/*      */     
/*  657 */     arrayOfByte[0] = ((byte)(i / 100 + 100));
/*  658 */     arrayOfByte[1] = ((byte)(i % 100 + 100));
/*  659 */     arrayOfByte[2] = ((byte)(paramCalendar.get(2) + 1));
/*  660 */     arrayOfByte[3] = ((byte)paramCalendar.get(5));
/*  661 */     arrayOfByte[4] = 1;
/*  662 */     arrayOfByte[5] = 1;
/*  663 */     arrayOfByte[6] = 1;
/*      */     
/*  665 */     return arrayOfByte;
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
/*      */   public static byte[] toBytes(Time paramTime, Calendar paramCalendar)
/*      */   {
/*  679 */     if (paramTime == null) {
/*  680 */       return null;
/*      */     }
/*  682 */     if (paramCalendar == null) {
/*  683 */       paramCalendar = Calendar.getInstance();
/*      */     }
/*  685 */     paramCalendar.clear();
/*  686 */     paramCalendar.setTime(paramTime);
/*      */     
/*  688 */     byte[] arrayOfByte = new byte[7];
/*      */     
/*  690 */     arrayOfByte[0] = 119;
/*  691 */     arrayOfByte[1] = -86;
/*  692 */     arrayOfByte[2] = 1;
/*  693 */     arrayOfByte[3] = 1;
/*  694 */     arrayOfByte[4] = ((byte)(paramCalendar.get(11) + 1));
/*  695 */     arrayOfByte[5] = ((byte)(paramCalendar.get(12) + 1));
/*  696 */     arrayOfByte[6] = ((byte)(paramCalendar.get(13) + 1));
/*      */     
/*  698 */     return arrayOfByte;
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
/*      */   public static byte[] toBytes(Timestamp paramTimestamp, Calendar paramCalendar)
/*      */   {
/*  712 */     if (paramTimestamp == null) {
/*  713 */       return null;
/*      */     }
/*  715 */     if (paramCalendar == null) {
/*  716 */       paramCalendar = Calendar.getInstance();
/*      */     }
/*  718 */     paramCalendar.clear();
/*  719 */     paramCalendar.setTime(paramTimestamp);
/*      */     
/*      */ 
/*  722 */     byte[] arrayOfByte = new byte[7];
/*      */     
/*      */ 
/*  725 */     int i = paramCalendar.get(1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  730 */     if (paramCalendar.get(0) == 0) {
/*  731 */       i = -i;
/*      */     }
/*  733 */     if ((i < 60824) || (i > 9999))
/*      */     {
/*  735 */       throw new IllegalArgumentException("Invalid year value");
/*      */     }
/*      */     
/*  738 */     arrayOfByte[0] = ((byte)(i / 100 + 100));
/*  739 */     arrayOfByte[1] = ((byte)(i % 100 + 100));
/*  740 */     arrayOfByte[2] = ((byte)(paramCalendar.get(2) + 1));
/*  741 */     arrayOfByte[3] = ((byte)paramCalendar.get(5));
/*  742 */     arrayOfByte[4] = ((byte)(paramCalendar.get(11) + 1));
/*  743 */     arrayOfByte[5] = ((byte)(paramCalendar.get(12) + 1));
/*  744 */     arrayOfByte[6] = ((byte)(paramCalendar.get(13) + 1));
/*      */     
/*  746 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(String paramString)
/*      */   {
/*  757 */     return toBytes(Timestamp.valueOf(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(String paramString, Calendar paramCalendar)
/*      */   {
/*  769 */     return toBytes(Timestamp.valueOf(paramString), paramCalendar);
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
/*      */   public java.sql.Date dateValue()
/*      */   {
/*  785 */     return toDate(getBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time timeValue()
/*      */   {
/*  795 */     return toTime(getBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp timestampValue()
/*      */   {
/*  805 */     return toTimestamp(getBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public java.sql.Date dateValue(Calendar paramCalendar)
/*      */   {
/*  816 */     return toDate(getBytes(), paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time timeValue(Calendar paramCalendar)
/*      */   {
/*  827 */     return toTime(getBytes(), paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp timestampValue(Calendar paramCalendar)
/*      */   {
/*  839 */     return toTimestamp(getBytes(), paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String stringValue()
/*      */   {
/*  849 */     return toString(getBytes());
/*      */   }
/*      */   
/*      */   public String toString() {
/*  853 */     return stringValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object toJdbc()
/*      */   {
/*  862 */     return timestampValue();
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
/*  873 */     Timestamp[] arrayOfTimestamp = new Timestamp[paramInt];
/*      */     
/*  875 */     return arrayOfTimestamp;
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
/*  887 */     if ((paramClass.getName().compareTo("java.sql.Date") == 0) || (paramClass.getName().compareTo("java.sql.Time") == 0) || (paramClass.getName().compareTo("java.sql.Timestamp") == 0) || (paramClass.getName().compareTo("java.lang.String") == 0))
/*      */     {
/*      */ 
/*      */ 
/*  891 */       return true;
/*      */     }
/*  893 */     return false;
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
/*      */   public DATE addJulianDays(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  912 */     return new DATE(_getLdxLib().ldxads(shareBytes(), paramInt1, paramInt2));
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
/*      */   public DATE addMonths(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  928 */     return new DATE(_getLdxLib().ldxadm(shareBytes(), paramInt));
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
/*      */   public void diffInJulianDays(DATE paramDATE, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*      */     throws SQLException
/*      */   {
/*  944 */     _getLdxLib().ldxsub(shareBytes(), paramDATE.shareBytes(), paramArrayOfInt1, paramArrayOfInt2);
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
/*      */   public NUMBER diffInMonths(DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/*  958 */     return new NUMBER(_getLdxLib().ldxsbm(shareBytes(), paramDATE.shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DATE getCurrentDate()
/*      */     throws SQLException
/*      */   {
/*  971 */     return new DATE(_getLdxLib().ldxgdt());
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
/*      */   public static int checkValidity(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1005 */     return _getLdxLib().ldxchk(paramArrayOfByte);
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
/*      */   public static DATE fromJulianDays(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1021 */     return new DATE(_getLdxLib().ldxdfd(paramInt1, paramInt2));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DATE fromText(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 1105 */     return new DATE(_getLdxLib().ldxstd(paramString1, paramString2, paramString3));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DATE lastDayOfMonth()
/*      */     throws SQLException
/*      */   {
/* 1117 */     return new DATE(_getLdxLib().ldxldd(shareBytes()));
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
/*      */   public static void numberToJulianDays(NUMBER paramNUMBER, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*      */     throws SQLException
/*      */   {
/* 1132 */     _getLdxLib().ldxftd(paramNUMBER.toBytes(), paramArrayOfInt1, paramArrayOfInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DATE round(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1145 */     return new DATE(_getLdxLib().ldxrnd(shareBytes(), paramString));
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
/*      */   public DATE setDayOfWeek(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1159 */     return new DATE(_getLdxLib().ldxnxd(shareBytes(), paramInt));
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
/*      */   public void toJulianDays(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*      */     throws SQLException
/*      */   {
/* 1174 */     _getLdxLib().ldxdtd(shareBytes(), paramArrayOfInt1, paramArrayOfInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER toNumber()
/*      */     throws SQLException
/*      */   {
/* 1186 */     return new NUMBER(_getLdxLib().ldxdyf(shareBytes()));
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
/*      */   public String toText(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 1201 */     return _getLdxLib().ldxdts(shareBytes(), paramString1, paramString2);
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
/*      */   public String toText(byte[] paramArrayOfByte, String paramString)
/*      */     throws SQLException
/*      */   {
/* 1216 */     return _getLdxLib().ldxdts(shareBytes(), paramArrayOfByte, paramString);
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
/*      */   public static byte[] parseFormat(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 1232 */     return (byte[])_getLdxLib().ldxsto(paramString1, paramString2);
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
/*      */   public DATE truncate(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1246 */     return new DATE(_getLdxLib().ldxtrn(shareBytes(), paramString));
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
/*      */   public int compareTo(DATE paramDATE)
/*      */   {
/* 1260 */     return compareBytes(shareBytes(), paramDATE.shareBytes());
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static byte[] _initDate()
/*      */   {
/* 1415 */     byte[] arrayOfByte = new byte[7];
/*      */     
/* 1417 */     arrayOfByte[0] = 119;
/* 1418 */     arrayOfByte[1] = -86;
/* 1419 */     arrayOfByte[2] = 1;
/* 1420 */     arrayOfByte[3] = 1;
/* 1421 */     arrayOfByte[4] = 1;
/* 1422 */     arrayOfByte[5] = 1;
/* 1423 */     arrayOfByte[6] = 1;
/*      */     
/* 1425 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static LdxLib _getLdxLib()
/*      */   {
/* 1435 */     if (_sldxlib == null)
/*      */     {
/*      */       try
/*      */       {
/*      */ 
/* 1440 */         if (System.getProperty("oracle.jserver.version") != null)
/*      */         {
/* 1442 */           _sldxlib = new LdxLibServer();
/*      */         }
/*      */         else
/*      */         {
/* 1446 */           _sldxlib = new LdxLibThin();
/*      */         }
/*      */       }
/*      */       catch (SecurityException localSecurityException)
/*      */       {
/* 1451 */         _sldxlib = new LdxLibThin();
/*      */       }
/*      */     }
/*      */     
/* 1455 */     return _sldxlib;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void _printBytes(byte[] paramArrayOfByte)
/*      */   {
/* 1467 */     System.out.println(toString(paramArrayOfByte));
/*      */   }
/*      */   
/* 1470 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/DATE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */