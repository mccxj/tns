/*      */ package oracle.sql;
/*      */ 
/*      */ import java.sql.Connection;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.GregorianCalendar;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TIMESTAMPLTZ
/*      */   extends Datum
/*      */ {
/*      */   public TIMESTAMPLTZ()
/*      */   {
/*   97 */     super(initTimestampltz());
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
/*      */   public TIMESTAMPLTZ(byte[] paramArrayOfByte)
/*      */   {
/*  112 */     super(paramArrayOfByte);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public TIMESTAMPLTZ(Connection paramConnection, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  130 */     super(toBytes(paramConnection, paramTime, paramCalendar));
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public TIMESTAMPLTZ(Connection paramConnection, java.sql.Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  148 */     super(toBytes(paramConnection, paramDate, paramCalendar));
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public TIMESTAMPLTZ(Connection paramConnection, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  166 */     super(toBytes(paramConnection, paramTimestamp, paramCalendar));
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public TIMESTAMPLTZ(Connection paramConnection, DATE paramDATE, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  185 */     super(toBytes(paramConnection, paramDATE, paramCalendar));
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public TIMESTAMPLTZ(Connection paramConnection, String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  206 */     super(toBytes(paramConnection, paramString, paramCalendar));
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
/*      */   public TIMESTAMPLTZ(Connection paramConnection, Calendar paramCalendar, Time paramTime)
/*      */     throws SQLException
/*      */   {
/*  228 */     super(toBytes(paramConnection, paramCalendar, paramTime));
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
/*      */   public TIMESTAMPLTZ(Connection paramConnection, Calendar paramCalendar, java.sql.Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  245 */     super(toBytes(paramConnection, paramCalendar, paramDate));
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
/*      */   public TIMESTAMPLTZ(Connection paramConnection, Calendar paramCalendar, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/*  262 */     super(toBytes(paramConnection, paramCalendar, paramTimestamp));
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
/*      */   public TIMESTAMPLTZ(Connection paramConnection, Calendar paramCalendar, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/*  280 */     super(toBytes(paramConnection, paramCalendar, paramDATE));
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public TIMESTAMPLTZ(Connection paramConnection, Calendar paramCalendar, String paramString)
/*      */     throws SQLException
/*      */   {
/*  298 */     super(toBytes(paramConnection, getSessCalendar(paramConnection), paramString));
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
/*      */   public TIMESTAMPLTZ(Connection paramConnection, Time paramTime)
/*      */     throws SQLException
/*      */   {
/*  315 */     super(toBytes(paramConnection, getSessCalendar(paramConnection), paramTime));
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
/*      */   public TIMESTAMPLTZ(Connection paramConnection, java.sql.Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  331 */     super(toBytes(paramConnection, getSessCalendar(paramConnection), paramDate));
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
/*      */   public TIMESTAMPLTZ(Connection paramConnection, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/*  348 */     super(toBytes(paramConnection, getSessCalendar(paramConnection), paramTimestamp));
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
/*      */   public TIMESTAMPLTZ(Connection paramConnection, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/*  365 */     super(toBytes(paramConnection, getSessCalendar(paramConnection), paramDATE));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public TIMESTAMPLTZ(Connection paramConnection, String paramString)
/*      */     throws SQLException
/*      */   {
/*  381 */     super(toBytes(paramConnection, getSessCalendar(paramConnection), Timestamp.valueOf(paramString)));
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
/*      */   public static java.sql.Date toDate(Connection paramConnection, byte[] paramArrayOfByte, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  407 */     Calendar localCalendar = toCalendar(paramConnection, Calendar.getInstance(), paramArrayOfByte, paramCalendar);
/*      */     
/*  409 */     long l = localCalendar.getTime().getTime();
/*      */     
/*  411 */     return new java.sql.Date(l);
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
/*      */   public static Time toTime(Connection paramConnection, byte[] paramArrayOfByte, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  431 */     Calendar localCalendar = toCalendar(paramConnection, Calendar.getInstance(), paramArrayOfByte, paramCalendar);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  437 */     return new Time(localCalendar.get(11), localCalendar.get(12), localCalendar.get(13));
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
/*      */   public static Timestamp toTimestamp(Connection paramConnection, byte[] paramArrayOfByte, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  455 */     return toTimestamp(paramConnection, Calendar.getInstance(), paramArrayOfByte, paramCalendar);
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
/*      */   public static DATE toDATE(Connection paramConnection, byte[] paramArrayOfByte, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  472 */     return new DATE(toTimestamp(paramConnection, getSessCalendar(paramConnection), paramArrayOfByte, null));
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
/*      */   public Timestamp timestampValue(Connection paramConnection, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  486 */     return toTimestamp(paramConnection, getBytes(), paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static String toString(Connection paramConnection, byte[] paramArrayOfByte, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  501 */     Calendar localCalendar = toCalendar(paramConnection, null, paramArrayOfByte, paramCalendar);
/*      */     
/*      */ 
/*  504 */     int i = localCalendar.get(1);
/*  505 */     int j = localCalendar.get(2) + 1;
/*  506 */     int k = localCalendar.get(5);
/*  507 */     int m = localCalendar.get(11);
/*  508 */     int n = localCalendar.get(12);
/*  509 */     int i1 = localCalendar.get(13);
/*  510 */     int i2 = -1;
/*      */     
/*  512 */     if (paramArrayOfByte.length == SIZE_TIMESTAMPLTZ) {
/*  513 */       i2 = TIMESTAMP.getNanos(paramArrayOfByte, 7);
/*      */     }
/*  515 */     return TIMESTAMPTZ.toString(i, j, k, m, n, i1, i2, localCalendar.getTimeZone().getID());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] toBytes()
/*      */   {
/*  526 */     return getBytes();
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static byte[] toBytes(Connection paramConnection, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  543 */     if (paramTime == null) {
/*  544 */       return null;
/*      */     }
/*      */     
/*  547 */     Calendar localCalendar = Calendar.getInstance();
/*  548 */     localCalendar.setTime(paramTime);
/*      */     
/*      */     int i;
/*      */     
/*  552 */     if (((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin().getUse1900AsYearForTime())
/*      */     {
/*      */ 
/*  555 */       i = 1900;
/*      */     }
/*      */     else
/*      */     {
/*  559 */       i = 1970;
/*      */     }
/*      */     
/*  562 */     localCalendar.set(1, i);
/*  563 */     localCalendar.set(2, 0);
/*  564 */     localCalendar.set(5, 1);
/*  565 */     byte[] arrayOfByte = toBytes(paramConnection, localCalendar, paramCalendar, 0);
/*  566 */     return arrayOfByte;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static byte[] toBytes(Connection paramConnection, java.sql.Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  585 */     if (paramDate == null)
/*  586 */       return null;
/*  587 */     Calendar localCalendar = Calendar.getInstance();
/*  588 */     localCalendar.setTime(paramDate);
/*  589 */     localCalendar.set(11, 0);
/*  590 */     localCalendar.set(12, 0);
/*  591 */     localCalendar.set(13, 0);
/*  592 */     byte[] arrayOfByte = toBytes(paramConnection, localCalendar, paramCalendar, 0);
/*  593 */     return arrayOfByte;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static byte[] toBytes(Connection paramConnection, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  612 */     if (paramTimestamp == null)
/*  613 */       return null;
/*  614 */     Calendar localCalendar = Calendar.getInstance();
/*  615 */     localCalendar.setTime(paramTimestamp);
/*      */     
/*      */ 
/*  618 */     int i = paramTimestamp.getNanos();
/*      */     
/*  620 */     byte[] arrayOfByte = toBytes(paramConnection, localCalendar, paramCalendar, i);
/*  621 */     return arrayOfByte;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static byte[] toBytes(Connection paramConnection, DATE paramDATE, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  640 */     if (paramDATE == null)
/*  641 */       return null;
/*  642 */     Calendar localCalendar = Calendar.getInstance();
/*  643 */     localCalendar.setTime(DATE.toDate(paramDATE.toBytes()));
/*  644 */     byte[] arrayOfByte = toBytes(paramConnection, localCalendar, paramCalendar, 0);
/*  645 */     return arrayOfByte;
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
/*      */   public static byte[] toBytes(Connection paramConnection, String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  660 */     return toBytes(paramConnection, Timestamp.valueOf(paramString), paramCalendar);
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
/*      */   public static java.sql.Date toDate(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  677 */     Calendar localCalendar = toCalendar(paramConnection, null, paramArrayOfByte, null);
/*      */     
/*  679 */     long l = localCalendar.getTime().getTime();
/*      */     
/*  681 */     return new java.sql.Date(l);
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
/*      */   public static Time toTime(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  695 */     Calendar localCalendar = toCalendar(paramConnection, null, paramArrayOfByte, null);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  701 */     return new Time(localCalendar.get(11), localCalendar.get(12), localCalendar.get(13));
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
/*      */   public static Timestamp toTimestamp(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  716 */     return toTimestamp(paramConnection, null, paramArrayOfByte, null);
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
/*      */   public static DATE toDATE(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  731 */     Calendar localCalendar = toCalendar(paramConnection, null, paramArrayOfByte, null);
/*      */     
/*  733 */     long l = localCalendar.getTime().getTime();
/*      */     
/*  735 */     return new DATE(new Timestamp(l));
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
/*      */   public static TIMESTAMP toTIMESTAMP(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  750 */     return new TIMESTAMP(toTimestamp(paramConnection, getSessCalendar(paramConnection), paramArrayOfByte, null));
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
/*      */   public static TIMESTAMPTZ toTIMESTAMPTZ(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  766 */     return new TIMESTAMPTZ(paramConnection, toTimestamp(paramConnection, getSessCalendar(paramConnection), paramArrayOfByte, null), getSessCalendar(paramConnection));
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
/*      */   public static String toString(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  780 */     return toString(paramConnection, paramArrayOfByte, null);
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
/*      */   public static byte[] toBytes(Connection paramConnection, Calendar paramCalendar, Time paramTime)
/*      */     throws SQLException
/*      */   {
/*  795 */     if (paramTime == null) {
/*  796 */       return null;
/*      */     }
/*      */     
/*  799 */     paramCalendar.setTime(paramTime);
/*      */     
/*      */     int i;
/*      */     
/*  803 */     if (((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin().getUse1900AsYearForTime())
/*      */     {
/*      */ 
/*  806 */       i = 1900;
/*      */     }
/*      */     else
/*      */     {
/*  810 */       i = 1970;
/*      */     }
/*      */     
/*  813 */     paramCalendar.set(1, i);
/*  814 */     paramCalendar.set(2, 0);
/*  815 */     paramCalendar.set(5, 1);
/*      */     
/*      */ 
/*  818 */     initDbTimeZone(paramConnection);
/*  819 */     Calendar localCalendar = (Calendar)dbtz.clone();
/*      */     
/*  821 */     byte[] arrayOfByte = toBytes(paramConnection, paramCalendar, localCalendar, 0);
/*  822 */     return arrayOfByte;
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
/*      */   public static byte[] toBytes(Connection paramConnection, Calendar paramCalendar, java.sql.Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  838 */     if (paramDate == null) {
/*  839 */       return null;
/*      */     }
/*  841 */     paramCalendar.setTime(paramDate);
/*  842 */     paramCalendar.set(11, 0);
/*  843 */     paramCalendar.set(12, 0);
/*  844 */     paramCalendar.set(13, 0);
/*      */     
/*      */ 
/*  847 */     initDbTimeZone(paramConnection);
/*  848 */     Calendar localCalendar = (Calendar)dbtz.clone();
/*      */     
/*  850 */     byte[] arrayOfByte = toBytes(paramConnection, paramCalendar, localCalendar, 0);
/*  851 */     return arrayOfByte;
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
/*      */   public static byte[] toBytes(Connection paramConnection, Calendar paramCalendar, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/*  869 */     if (paramTimestamp == null) {
/*  870 */       return null;
/*      */     }
/*  872 */     paramCalendar.setTime(paramTimestamp);
/*      */     
/*  874 */     int i = paramTimestamp.getNanos();
/*      */     
/*      */ 
/*  877 */     initDbTimeZone(paramConnection);
/*  878 */     Calendar localCalendar = (Calendar)dbtz.clone();
/*      */     
/*  880 */     byte[] arrayOfByte = toBytes(paramConnection, paramCalendar, localCalendar, i);
/*  881 */     return arrayOfByte;
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
/*      */   public static byte[] toBytes(Connection paramConnection, Calendar paramCalendar, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/*  898 */     if (paramDATE == null) {
/*  899 */       return null;
/*      */     }
/*  901 */     paramCalendar.setTime(DATE.toDate(paramDATE.toBytes()));
/*      */     
/*  903 */     initDbTimeZone(paramConnection);
/*  904 */     Calendar localCalendar = (Calendar)dbtz.clone();
/*  905 */     byte[] arrayOfByte = toBytes(paramConnection, paramCalendar, localCalendar, 0);
/*  906 */     return arrayOfByte;
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
/*      */   public static byte[] toBytes(Connection paramConnection, Calendar paramCalendar, String paramString)
/*      */     throws SQLException
/*      */   {
/*  921 */     return toBytes(paramConnection, paramCalendar, Timestamp.valueOf(paramString));
/*      */   }
/*      */   
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
/*  934 */     return toString(paramConnection, getBytes());
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
/*      */   public String stringValue(Connection paramConnection, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  949 */     return toString(paramConnection, getBytes(), paramCalendar);
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
/*      */   public java.sql.Date dateValue(Connection paramConnection, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  964 */     return toDate(paramConnection, getBytes(), paramCalendar);
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
/*      */   public java.sql.Date dateValue(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  978 */     return toDate(paramConnection, getBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time timeValue(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  989 */     return toTime(paramConnection, getBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time timeValue(Connection paramConnection, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1000 */     return toTime(paramConnection, getBytes(), paramCalendar);
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
/*      */   public Object toJdbc()
/*      */     throws SQLException
/*      */   {
/* 1014 */     return null;
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
/* 1025 */     Timestamp[] arrayOfTimestamp = new Timestamp[paramInt];
/*      */     
/* 1027 */     return arrayOfTimestamp;
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
/* 1039 */     if ((paramClass.getName().compareTo("java.sql.Date") == 0) || (paramClass.getName().compareTo("java.sql.Time") == 0) || (paramClass.getName().compareTo("java.sql.Timestamp") == 0) || (paramClass.getName().compareTo("java.lang.String") == 0))
/*      */     {
/*      */ 
/*      */ 
/* 1043 */       return true;
/*      */     }
/* 1045 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static byte[] initTimestampltz()
/*      */   {
/* 1056 */     byte[] arrayOfByte = new byte[SIZE_TIMESTAMPLTZ];
/*      */     
/* 1058 */     arrayOfByte[0] = 119;
/* 1059 */     arrayOfByte[1] = -86;
/* 1060 */     arrayOfByte[2] = 1;
/* 1061 */     arrayOfByte[3] = 1;
/* 1062 */     arrayOfByte[4] = 1;
/* 1063 */     arrayOfByte[5] = 1;
/* 1064 */     arrayOfByte[6] = 1;
/*      */     
/* 1066 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 1072 */   private static int SIZE_TIMESTAMPLTZ = 11;
/* 1073 */   private static int SIZE_TIMESTAMPLTZ_NOFRAC = 7;
/*      */   
/*      */ 
/* 1076 */   private static int SIZE_DATE = 7;
/*      */   
/*      */ 
/* 1079 */   private static int HOUR_MILLISECOND = 3600000;
/*      */   
/*      */ 
/* 1082 */   private static int MINUTE_MILLISECOND = 60000;
/*      */   
/*      */ 
/*      */ 
/* 1086 */   private static int JAVA_YEAR = 1970;
/* 1087 */   private static int JAVA_MONTH = 0;
/* 1088 */   private static int JAVA_DATE = 1;
/*      */   
/* 1090 */   private static int MINYEAR = 60824;
/* 1091 */   private static int MAXYEAR = 9999;
/*      */   
/*      */ 
/*      */   private static byte[] toBytes(Connection paramConnection, Calendar paramCalendar1, Calendar paramCalendar2, int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     byte[] arrayOfByte;
/* 1098 */     if (paramInt == 0) {
/* 1099 */       arrayOfByte = new byte[SIZE_TIMESTAMPLTZ_NOFRAC];
/*      */     } else {
/* 1101 */       arrayOfByte = new byte[SIZE_TIMESTAMPLTZ];
/*      */     }
/* 1103 */     TimeZoneAdjust(paramConnection, paramCalendar1, paramCalendar2);
/*      */     
/* 1105 */     int i = paramCalendar2.get(1);
/* 1106 */     if ((i < MINYEAR) || (i > MAXYEAR))
/*      */     {
/*      */ 
/* 1109 */       SQLException localSQLException = DatabaseError.createSqlException(null, 268);
/* 1110 */       localSQLException.fillInStackTrace();
/* 1111 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1115 */     arrayOfByte[0] = ((byte)(paramCalendar2.get(1) / 100 + 100));
/* 1116 */     arrayOfByte[1] = ((byte)(paramCalendar2.get(1) % 100 + 100));
/* 1117 */     arrayOfByte[2] = ((byte)(paramCalendar2.get(2) + 1));
/* 1118 */     arrayOfByte[3] = ((byte)paramCalendar2.get(5));
/* 1119 */     arrayOfByte[4] = ((byte)(paramCalendar2.get(11) + 1));
/* 1120 */     arrayOfByte[5] = ((byte)(paramCalendar2.get(12) + 1));
/* 1121 */     arrayOfByte[6] = ((byte)(paramCalendar2.get(13) + 1));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1127 */     if (paramInt != 0)
/*      */     {
/* 1129 */       arrayOfByte[7] = ((byte)(paramInt >> 24));
/* 1130 */       arrayOfByte[8] = ((byte)(paramInt >> 16 & 0xFF));
/* 1131 */       arrayOfByte[9] = ((byte)(paramInt >> 8 & 0xFF));
/* 1132 */       arrayOfByte[10] = ((byte)(paramInt & 0xFF));
/*      */     }
/*      */     
/* 1135 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */   private static Timestamp toTimestamp(Connection paramConnection, Calendar paramCalendar1, byte[] paramArrayOfByte, Calendar paramCalendar2)
/*      */     throws SQLException
/*      */   {
/* 1142 */     Calendar localCalendar = toCalendar(paramConnection, paramCalendar1, paramArrayOfByte, paramCalendar2);
/*      */     
/*      */ 
/* 1145 */     long l = localCalendar.getTime().getTime();
/*      */     
/*      */ 
/* 1148 */     Timestamp localTimestamp = new Timestamp(l);
/* 1149 */     int i = 0;
/*      */     
/*      */ 
/* 1152 */     if (paramArrayOfByte.length == SIZE_TIMESTAMPLTZ) {
/* 1153 */       i = TIMESTAMP.getNanos(paramArrayOfByte, 7);
/*      */     }
/* 1155 */     localTimestamp.setNanos(i);
/*      */     
/* 1157 */     return localTimestamp;
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
/*      */   private static final Calendar toCalendar(Connection paramConnection, Calendar paramCalendar1, byte[] paramArrayOfByte, Calendar paramCalendar2)
/*      */     throws SQLException
/*      */   {
/* 1171 */     int i = paramArrayOfByte.length;
/*      */     int[] arrayOfInt;
/* 1173 */     if (i == SIZE_TIMESTAMPLTZ) {
/* 1174 */       arrayOfInt = new int[SIZE_TIMESTAMPLTZ];
/*      */     } else {
/* 1176 */       arrayOfInt = new int[SIZE_TIMESTAMPLTZ_NOFRAC];
/*      */     }
/* 1178 */     for (int j = 0; j < paramArrayOfByte.length; j++) {
/* 1179 */       paramArrayOfByte[j] &= 0xFF;
/*      */     }
/*      */     
/* 1182 */     j = TIMESTAMP.getJavaYear(arrayOfInt[0], arrayOfInt[1]);
/*      */     
/* 1184 */     if (paramCalendar2 == null)
/*      */     {
/*      */ 
/* 1187 */       initDbTimeZone(paramConnection);
/*      */       
/*      */ 
/*      */ 
/* 1191 */       paramCalendar2 = (Calendar)dbtz.clone();
/*      */     }
/*      */     
/* 1194 */     paramCalendar2.set(1, j);
/* 1195 */     paramCalendar2.set(2, arrayOfInt[2] - 1);
/* 1196 */     paramCalendar2.set(5, arrayOfInt[3]);
/* 1197 */     paramCalendar2.set(11, arrayOfInt[4] - 1);
/* 1198 */     paramCalendar2.set(12, arrayOfInt[5] - 1);
/* 1199 */     paramCalendar2.set(13, arrayOfInt[6] - 1);
/* 1200 */     paramCalendar2.set(14, 0);
/*      */     
/* 1202 */     if (paramCalendar1 == null) {
/* 1203 */       paramCalendar1 = getSessCalendar(paramConnection);
/*      */     }
/*      */     
/* 1206 */     TimeZoneAdjust(paramConnection, paramCalendar2, paramCalendar1);
/*      */     
/* 1208 */     return paramCalendar1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static void TimeZoneAdjust(Connection paramConnection, Calendar paramCalendar1, Calendar paramCalendar2)
/*      */     throws SQLException
/*      */   {
/* 1218 */     TimeZone localTimeZone = paramCalendar1.getTimeZone();
/*      */     
/* 1220 */     String str1 = new String(paramCalendar1.getTimeZone().getID());
/* 1221 */     String str2 = new String(paramCalendar2.getTimeZone().getID());
/*      */     
/*      */ 
/*      */ 
/* 1225 */     if ((!str2.equals(str1)) && ((!str2.equals("Custom")) || (!str1.equals("Custom"))))
/*      */     {
/*      */ 
/*      */ 
/* 1229 */       OffsetDST localOffsetDST = new OffsetDST();
/*      */       
/*      */ 
/*      */ 
/* 1233 */       k = getZoneOffset(paramConnection, paramCalendar1, localOffsetDST);
/* 1234 */       m = localOffsetDST.getOFFSET();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1243 */       boolean bool1 = localTimeZone.inDaylightTime(paramCalendar1.getTime());
/*      */       
/* 1245 */       paramCalendar1.add(11, -(m / HOUR_MILLISECOND));
/* 1246 */       paramCalendar1.add(12, -(m % HOUR_MILLISECOND) / MINUTE_MILLISECOND);
/*      */       
/* 1248 */       boolean bool2 = localTimeZone.inDaylightTime(paramCalendar1.getTime());
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1254 */       if ((bool1) && (!bool2)) {
/* 1255 */         paramCalendar1.add(14, 3600000);
/* 1256 */       } else if ((!bool1) && (bool2))
/* 1257 */         paramCalendar1.add(14, -3600000);
/*      */       int i;
/* 1259 */       if (str2.equals("Custom")) {
/* 1260 */         i = paramCalendar2.getTimeZone().getRawOffset();
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 1266 */         n = ZONEIDMAP.getID(str2);
/*      */         
/* 1268 */         if (!ZONEIDMAP.isValidID(n))
/*      */         {
/* 1270 */           if (paramCalendar2.getTimeZone().useDaylightTime())
/* 1271 */             throw new SQLException("Timezone not supported");
/* 1272 */           i = paramCalendar2.getTimeZone().getRawOffset();
/*      */         }
/*      */         else
/*      */         {
/* 1276 */           TIMEZONETAB localTIMEZONETAB = getTIMEZONETAB(paramConnection);
/* 1277 */           if (localTIMEZONETAB.checkID(n)) {
/* 1278 */             localTIMEZONETAB.updateTable(paramConnection, n);
/*      */           }
/*      */           
/* 1281 */           i = localTIMEZONETAB.getOffset(paramCalendar1, n);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1291 */       bool1 = localTimeZone.inDaylightTime(paramCalendar1.getTime());
/*      */       
/*      */ 
/* 1294 */       paramCalendar1.add(11, i / HOUR_MILLISECOND);
/* 1295 */       paramCalendar1.add(12, i % HOUR_MILLISECOND / MINUTE_MILLISECOND);
/*      */       
/* 1297 */       bool2 = localTimeZone.inDaylightTime(paramCalendar1.getTime());
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1303 */       if ((bool1) && (!bool2)) {
/* 1304 */         paramCalendar1.add(14, 3600000);
/* 1305 */       } else if ((!bool1) && (bool2)) {
/* 1306 */         paramCalendar1.add(14, -3600000);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1313 */     if ((str2.equals("Custom")) && (str1.equals("Custom")))
/*      */     {
/* 1315 */       j = paramCalendar1.getTimeZone().getRawOffset();
/* 1316 */       k = paramCalendar2.getTimeZone().getRawOffset();
/* 1317 */       m = 0;
/*      */       
/* 1319 */       if (j != k)
/*      */       {
/*      */ 
/* 1322 */         m = j - k;
/* 1323 */         m = m > 0 ? m : -m;
/*      */       }
/*      */       
/* 1326 */       if (j > k) {
/* 1327 */         m = -m;
/*      */       }
/* 1329 */       paramCalendar1.add(11, m / HOUR_MILLISECOND);
/* 1330 */       paramCalendar1.add(12, m % HOUR_MILLISECOND / MINUTE_MILLISECOND);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1335 */     int j = paramCalendar1.get(1);
/* 1336 */     int k = paramCalendar1.get(2);
/* 1337 */     int m = paramCalendar1.get(5);
/* 1338 */     int n = paramCalendar1.get(11);
/* 1339 */     int i1 = paramCalendar1.get(12);
/* 1340 */     int i2 = paramCalendar1.get(13);
/* 1341 */     int i3 = paramCalendar1.get(14);
/*      */     
/*      */ 
/*      */ 
/* 1345 */     paramCalendar2.set(1, j);
/* 1346 */     paramCalendar2.set(2, k);
/* 1347 */     paramCalendar2.set(5, m);
/* 1348 */     paramCalendar2.set(11, n);
/* 1349 */     paramCalendar2.set(12, i1);
/* 1350 */     paramCalendar2.set(13, i2);
/* 1351 */     paramCalendar2.set(14, i3);
/*      */   }
/*      */   
/*      */ 
/*      */   private static byte getZoneOffset(Connection paramConnection, Calendar paramCalendar, OffsetDST paramOffsetDST)
/*      */     throws SQLException
/*      */   {
/* 1358 */     byte b = 0;
/*      */     
/*      */ 
/*      */ 
/* 1362 */     if (paramCalendar.getTimeZone().getID() == "Custom") {
/* 1363 */       paramOffsetDST.setOFFSET(paramCalendar.getTimeZone().getRawOffset());
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1368 */       String str = new String(paramCalendar.getTimeZone().getID());
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1373 */       int i = ZONEIDMAP.getID(str);
/* 1374 */       if (!ZONEIDMAP.isValidID(i))
/*      */       {
/* 1376 */         if (paramCalendar.getTimeZone().useDaylightTime()) {
/* 1377 */           throw new SQLException("Timezone not supported");
/*      */         }
/* 1379 */         paramOffsetDST.setOFFSET(paramCalendar.getTimeZone().getRawOffset());
/*      */       }
/*      */       else
/*      */       {
/* 1383 */         TIMEZONETAB localTIMEZONETAB = getTIMEZONETAB(paramConnection);
/* 1384 */         if (localTIMEZONETAB.checkID(i)) {
/* 1385 */           localTIMEZONETAB.updateTable(paramConnection, i);
/*      */         }
/*      */         
/* 1388 */         b = localTIMEZONETAB.getLocalOffset(paramCalendar, i, paramOffsetDST);
/*      */       }
/*      */     }
/*      */     
/* 1392 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static Calendar getDbTzCalendar(String paramString)
/*      */   {
/* 1401 */     int i = paramString.charAt(0);
/*      */     String str;
/* 1403 */     if ((i == 43) || (i == 45))
/*      */     {
/* 1405 */       str = "GMT" + paramString;
/*      */     }
/*      */     else
/*      */     {
/* 1409 */       str = paramString;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1414 */     TimeZone localTimeZone = TimeZone.getTimeZone(str);
/*      */     
/* 1416 */     return new GregorianCalendar(localTimeZone);
/*      */   }
/*      */   
/*      */ 
/*      */   static Calendar getSessCalendar(Connection paramConnection)
/*      */   {
/* 1422 */     String str = ((oracle.jdbc.OracleConnection)paramConnection).getSessionTimeZone();
/*      */     
/*      */     Calendar localCalendar;
/* 1425 */     if (str == null)
/*      */     {
/*      */ 
/* 1428 */       localCalendar = Calendar.getInstance();
/*      */     }
/*      */     else
/*      */     {
/* 1432 */       TimeZone localTimeZone = TimeZone.getTimeZone(str);
/* 1433 */       localCalendar = Calendar.getInstance(localTimeZone);
/*      */     }
/*      */     
/* 1436 */     return localCalendar;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static synchronized void initDbTimeZone(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/* 1448 */     if (!cached)
/*      */     {
/* 1450 */       oracle.jdbc.internal.OracleConnection localOracleConnection = ((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin();
/*      */       
/* 1452 */       String str = localOracleConnection.getDatabaseTimeZone();
/* 1453 */       dbtz = getDbTzCalendar(str);
/* 1454 */       cached = true;
/*      */     }
/*      */   }
/*      */   
/*      */   static TIMEZONETAB getTIMEZONETAB(Connection paramConnection) throws SQLException
/*      */   {
/* 1460 */     oracle.jdbc.internal.OracleConnection localOracleConnection = ((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin();
/*      */     
/* 1462 */     return localOracleConnection.getTIMEZONETAB();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1467 */   private static boolean cached = false;
/*      */   
/*      */   private static Calendar dbtz;
/*      */   static final long serialVersionUID = 2045880772054757133L;
/* 1471 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/TIMESTAMPLTZ.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */