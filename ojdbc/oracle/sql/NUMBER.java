/*      */ package oracle.sql;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.sql.SQLException;
/*      */ import oracle.core.lmx.CoreException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class NUMBER
/*      */   extends Datum
/*      */ {
/*      */   public NUMBER()
/*      */   {
/*  136 */     super(_makeZero());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER(byte[] paramArrayOfByte)
/*      */   {
/*  148 */     super(paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER(byte paramByte)
/*      */   {
/*  158 */     super(toBytes(paramByte));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER(int paramInt)
/*      */   {
/*  168 */     super(toBytes(paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER(long paramLong)
/*      */   {
/*  177 */     super(toBytes(paramLong));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER(short paramShort)
/*      */   {
/*  186 */     super(toBytes(paramShort));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER(float paramFloat)
/*      */   {
/*  195 */     super(toBytes(paramFloat));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER(double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  206 */     super(toBytes(paramDouble));
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
/*      */   public NUMBER(BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/*  233 */     super(toBytes(paramBigDecimal));
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
/*      */   public NUMBER(BigInteger paramBigInteger)
/*      */     throws SQLException
/*      */   {
/*  248 */     super(toBytes(paramBigInteger));
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
/*      */   public NUMBER(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  264 */     super(toBytes(paramString, paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER(boolean paramBoolean)
/*      */   {
/*  274 */     super(toBytes(paramBoolean));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER(Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  285 */     if ((paramObject instanceof Integer)) {
/*  286 */       setShareBytes(toBytes(((Integer)paramObject).intValue()));
/*  287 */     } else if ((paramObject instanceof Long)) {
/*  288 */       setShareBytes(toBytes(((Long)paramObject).longValue()));
/*  289 */     } else if ((paramObject instanceof Float)) {
/*  290 */       setShareBytes(toBytes(((Float)paramObject).floatValue()));
/*  291 */     } else if ((paramObject instanceof Double)) {
/*  292 */       setShareBytes(toBytes(((Double)paramObject).doubleValue()));
/*  293 */     } else if ((paramObject instanceof BigInteger)) {
/*  294 */       setShareBytes(toBytes((BigInteger)paramObject));
/*  295 */     } else if ((paramObject instanceof BigDecimal)) {
/*  296 */       setShareBytes(toBytes((BigDecimal)paramObject));
/*  297 */     } else if ((paramObject instanceof Boolean)) {
/*  298 */       setShareBytes(toBytes(((Boolean)paramObject).booleanValue()));
/*  299 */     } else if ((paramObject instanceof String)) {
/*  300 */       setShareBytes(stringToBytes((String)paramObject));
/*      */     }
/*      */     else {
/*  303 */       throw new SQLException("Initialization failed");
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
/*      */   public static double toDouble(byte[] paramArrayOfByte)
/*      */   {
/*  326 */     if (_isZero(paramArrayOfByte)) {
/*  327 */       return 0.0D;
/*      */     }
/*  329 */     if (_isPosInf(paramArrayOfByte)) {
/*  330 */       return Double.POSITIVE_INFINITY;
/*      */     }
/*  332 */     if (_isNegInf(paramArrayOfByte)) {
/*  333 */       return Double.NEGATIVE_INFINITY;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  340 */     String str = null;
/*      */     
/*      */     try
/*      */     {
/*  344 */       if (drvType == null)
/*      */       {
/*  346 */         str = _slnxlib.lnxnuc(paramArrayOfByte, DBL_MAX, null);
/*      */       } else {
/*  348 */         str = _slnxlib.lnxnuc(paramArrayOfByte, DBL_MAX, LANGID);
/*      */       }
/*      */     }
/*      */     catch (Exception localException) {}
/*      */     
/*      */ 
/*  354 */     double d = Double.valueOf(str).doubleValue();
/*      */     
/*  356 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static float toFloat(byte[] paramArrayOfByte)
/*      */   {
/*  367 */     return (float)toDouble(paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  375 */   static byte[] MAX_LONG = toBytes(Long.MAX_VALUE);
/*  376 */   static byte[] MIN_LONG = toBytes(Long.MIN_VALUE);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int CHARACTER_ZERO = 48;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static long toLong(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  391 */     if (_isZero(paramArrayOfByte)) {
/*  392 */       return 0L;
/*      */     }
/*      */     
/*      */ 
/*  396 */     if ((_isInf(paramArrayOfByte)) || (compareBytes(paramArrayOfByte, MAX_LONG) > 0) || (compareBytes(paramArrayOfByte, MIN_LONG) < 0))
/*      */     {
/*      */ 
/*  399 */       throw new SQLException(CoreException.getMessage((byte)3));
/*      */     }
/*  401 */     return _getLnxLib().lnxsni(paramArrayOfByte);
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
/*      */   public static int toInt(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  417 */     if (_isInf(paramArrayOfByte)) {
/*  418 */       throw new SQLException(CoreException.getMessage((byte)3));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     String str;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  433 */     if (drvType == null)
/*      */     {
/*  435 */       str = _slnxlib.lnxnuc(paramArrayOfByte, INT_MAX, null);
/*      */     } else {
/*  437 */       str = _slnxlib.lnxnuc(paramArrayOfByte, INT_MAX, LANGID);
/*      */     }
/*      */     
/*  440 */     double d = Double.valueOf(str).doubleValue();
/*      */     
/*      */ 
/*  443 */     if (((float)d > FLOAT_MAX_INT) || ((float)d < FLOAT_MIN_INT)) {
/*  444 */       throw new SQLException(CoreException.getMessage((byte)3));
/*      */     }
/*      */     
/*  447 */     if ((d > DOUBLE_MAX_INT) && (d <= DOUBLE_MAX_INT_2)) {
/*  448 */       throw new SQLException(CoreException.getMessage((byte)3));
/*      */     }
/*  450 */     if ((d < DOUBLE_MIN_INT) && (d >= DOUBLE_MIN_INT_2))
/*  451 */       throw new SQLException(CoreException.getMessage((byte)3));
/*  452 */     int i = (int)d;
/*  453 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static short toShort(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  465 */     long l = 0L;
/*      */     try
/*      */     {
/*  468 */       l = toLong(paramArrayOfByte);
/*      */       
/*  470 */       if ((l > 32767L) || (l < -32768L)) {
/*  471 */         throw new SQLException(CoreException.getMessage((byte)3));
/*      */       }
/*      */     }
/*      */     finally {}
/*      */     
/*      */ 
/*      */ 
/*  478 */     return (short)(int)l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte toByte(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  489 */     long l = 0L;
/*      */     try
/*      */     {
/*  492 */       l = toLong(paramArrayOfByte);
/*      */       
/*  494 */       if ((l > 127L) || (l < -128L)) {
/*  495 */         throw new SQLException(CoreException.getMessage((byte)3));
/*      */       }
/*      */     }
/*      */     finally {}
/*      */     
/*      */ 
/*      */ 
/*  502 */     return (byte)(int)l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static BigInteger toBigInteger(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  514 */     long[] arrayOfLong = new long[10];
/*  515 */     int i = 9;
/*  516 */     int j = 1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  521 */     int i1 = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  527 */     if (_isZero(paramArrayOfByte)) {
/*  528 */       return BIGINT_ZERO;
/*      */     }
/*  530 */     if (_isInf(paramArrayOfByte)) {
/*  531 */       throw new SQLException(CoreException.getMessage((byte)3));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  537 */     boolean bool = _isPositive(paramArrayOfByte);
/*      */     
/*      */ 
/*  540 */     byte[] arrayOfByte1 = _fromLnxFmt(paramArrayOfByte);
/*      */     
/*      */ 
/*  543 */     if (arrayOfByte1[0] < 0)
/*      */     {
/*  545 */       return BIGINT_ZERO;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  551 */     int i2 = Math.min(arrayOfByte1[0] + 1, arrayOfByte1.length - 1);
/*  552 */     int k = i2;
/*      */     
/*      */ 
/*  555 */     if ((i2 & 0x1) == 1)
/*      */     {
/*  557 */       arrayOfLong[i] = arrayOfByte1[j];
/*  558 */       j = (byte)(j + 1);
/*  559 */       k--;
/*      */     }
/*      */     else
/*      */     {
/*  563 */       arrayOfLong[i] = (arrayOfByte1[j] * 100 + arrayOfByte1[(j + 1)]);
/*  564 */       j = (byte)(j + 2);
/*  565 */       k -= 2;
/*      */     }
/*      */     
/*      */ 
/*  569 */     int m = i;
/*  570 */     while (k != 0)
/*      */     {
/*  572 */       long l = arrayOfByte1[j] * 100 + arrayOfByte1[(j + 1)];
/*      */       
/*  574 */       for (i = 9; i >= m; i = (byte)(i - 1))
/*      */       {
/*  576 */         l += arrayOfLong[i] * 10000L;
/*  577 */         arrayOfLong[i] = (l & 0xFFFF);
/*  578 */         l >>= 16;
/*      */       }
/*      */       
/*  581 */       if (l != 0L) {}
/*      */       
/*  583 */       m = (byte)(m - 1);
/*  584 */       arrayOfLong[m] = l;
/*      */       
/*      */ 
/*  587 */       j = (byte)(j + 2);
/*  588 */       k -= 2;
/*      */     }
/*      */     
/*      */     int n;
/*  592 */     if (arrayOfLong[m] >> 8 != 0L) {
/*  593 */       n = 2 * (9 - m) + 2;
/*      */     } else {
/*  595 */       n = 2 * (9 - m) + 1;
/*      */     }
/*  597 */     byte[] arrayOfByte2 = new byte[n];
/*      */     
/*  599 */     if ((n & 0x1) == 1)
/*      */     {
/*  601 */       arrayOfByte2[i1] = ((byte)(int)arrayOfLong[m]);
/*  602 */       i1++;
/*      */     }
/*      */     else
/*      */     {
/*  606 */       arrayOfByte2[i1] = ((byte)(int)(arrayOfLong[m] >> 8));
/*  607 */       i1++;
/*  608 */       arrayOfByte2[i1] = ((byte)(int)(arrayOfLong[m] & 0xFF));
/*  609 */       i1++;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  614 */     for (m = (byte)(m + 1); m <= 9; m = (byte)(m + 1))
/*      */     {
/*  616 */       arrayOfByte2[i1] = ((byte)(int)(arrayOfLong[m] >> 8));
/*  617 */       arrayOfByte2[(i1 + 1)] = ((byte)(int)(arrayOfLong[m] & 0xFF));
/*  618 */       i1 += 2;
/*      */     }
/*      */     
/*      */ 
/*  622 */     BigInteger localBigInteger = new BigInteger(bool ? 1 : -1, arrayOfByte2);
/*      */     
/*      */ 
/*  625 */     int i3 = arrayOfByte1[0] - (i2 - 1);
/*  626 */     return localBigInteger.multiply(BIGINT_HUND.pow(i3));
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
/*      */   public static BigDecimal toBigDecimal(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  640 */     long[] arrayOfLong = new long[10];
/*  641 */     int i = 9;
/*  642 */     int j = 1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  647 */     int i1 = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  653 */     if (_isZero(paramArrayOfByte)) {
/*  654 */       return BIGDEC_ZERO;
/*      */     }
/*  656 */     if (_isInf(paramArrayOfByte)) {
/*  657 */       throw new SQLException(CoreException.getMessage((byte)3));
/*      */     }
/*      */     
/*      */ 
/*  661 */     boolean bool = _isPositive(paramArrayOfByte);
/*      */     
/*      */ 
/*  664 */     byte[] arrayOfByte1 = _fromLnxFmt(paramArrayOfByte);
/*      */     
/*      */     int i2;
/*      */     
/*  668 */     int k = i2 = arrayOfByte1.length - 1;
/*      */     
/*      */ 
/*  671 */     if ((i2 & 0x1) == 1)
/*      */     {
/*  673 */       arrayOfLong[i] = arrayOfByte1[j];
/*  674 */       j = (byte)(j + 1);
/*  675 */       k--;
/*      */     }
/*      */     else
/*      */     {
/*  679 */       arrayOfLong[i] = (arrayOfByte1[j] * 100 + arrayOfByte1[(j + 1)]);
/*  680 */       j = (byte)(j + 2);
/*  681 */       k -= 2;
/*      */     }
/*      */     
/*      */ 
/*  685 */     int m = i;
/*  686 */     while (k != 0)
/*      */     {
/*  688 */       long l = arrayOfByte1[j] * 100 + arrayOfByte1[(j + 1)];
/*      */       
/*  690 */       for (i = 9; i >= m; i = (byte)(i - 1))
/*      */       {
/*  692 */         l += arrayOfLong[i] * 10000L;
/*  693 */         arrayOfLong[i] = (l & 0xFFFF);
/*  694 */         l >>= 16;
/*      */       }
/*      */       
/*  697 */       if (l != 0L) {}
/*      */       
/*  699 */       m = (byte)(m - 1);
/*  700 */       arrayOfLong[m] = l;
/*      */       
/*      */ 
/*  703 */       j = (byte)(j + 2);
/*  704 */       k -= 2;
/*      */     }
/*      */     
/*      */     int n;
/*  708 */     if (arrayOfLong[m] >> 8 != 0L) {
/*  709 */       n = 2 * (9 - m) + 2;
/*      */     } else {
/*  711 */       n = 2 * (9 - m) + 1;
/*      */     }
/*  713 */     byte[] arrayOfByte2 = new byte[n];
/*      */     
/*  715 */     if ((n & 0x1) == 1)
/*      */     {
/*  717 */       arrayOfByte2[i1] = ((byte)(int)arrayOfLong[m]);
/*  718 */       i1++;
/*      */     }
/*      */     else
/*      */     {
/*  722 */       arrayOfByte2[i1] = ((byte)(int)(arrayOfLong[m] >> 8));
/*  723 */       i1++;
/*  724 */       arrayOfByte2[i1] = ((byte)(int)(arrayOfLong[m] & 0xFF));
/*  725 */       i1++;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  730 */     for (m = (byte)(m + 1); m <= 9; m = (byte)(m + 1))
/*      */     {
/*  732 */       arrayOfByte2[i1] = ((byte)(int)(arrayOfLong[m] >> 8));
/*  733 */       arrayOfByte2[(i1 + 1)] = ((byte)(int)(arrayOfLong[m] & 0xFF));
/*  734 */       i1 += 2;
/*      */     }
/*      */     
/*      */ 
/*  738 */     BigInteger localBigInteger = new BigInteger(bool ? 1 : -1, arrayOfByte2);
/*  739 */     BigDecimal localBigDecimal = new BigDecimal(localBigInteger);
/*      */     
/*      */ 
/*  742 */     int i3 = arrayOfByte1[0] - i2 + 1;
/*      */     
/*      */ 
/*  745 */     localBigDecimal = localBigDecimal.movePointRight(i3 * 2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  751 */     if ((i3 < 0) && (arrayOfByte1[i2] % 10 == 0))
/*  752 */       localBigDecimal = localBigDecimal.setScale(-(i3 * 2 + 1));
/*  753 */     return localBigDecimal;
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
/*      */   public static String toString(byte[] paramArrayOfByte)
/*      */   {
/*  769 */     int i = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  774 */     if (_isZero(paramArrayOfByte)) {
/*  775 */       return "0";
/*      */     }
/*  777 */     if (_isPosInf(paramArrayOfByte)) {
/*  778 */       return new Double(Double.POSITIVE_INFINITY).toString();
/*      */     }
/*  780 */     if (_isNegInf(paramArrayOfByte)) {
/*  781 */       return new Double(Double.NEGATIVE_INFINITY).toString();
/*      */     }
/*      */     
/*  784 */     byte[] arrayOfByte = _fromLnxFmt(paramArrayOfByte);
/*      */     
/*  786 */     int k = arrayOfByte[0];
/*  787 */     int m = arrayOfByte.length - 1;
/*  788 */     int n = k - (m - 1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     int i2;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  814 */     if (n >= 0)
/*      */     {
/*  816 */       i2 = 2 * (k + 1) + 1;
/*      */ 
/*      */ 
/*      */     }
/*  820 */     else if (k >= 0) {
/*  821 */       i2 = 2 * (m + 1);
/*      */     } else {
/*  823 */       i2 = 2 * (m - k) + 3;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  830 */     char[] arrayOfChar = new char[i2];
/*      */     
/*  832 */     if (!_isPositive(paramArrayOfByte))
/*      */     {
/*  834 */       arrayOfChar[(i++)] = '-';
/*      */     }
/*      */     int j;
/*  837 */     if (n >= 0)
/*      */     {
/*  839 */       i += _byteToChars(arrayOfByte[1], arrayOfChar, i);
/*      */       
/*  841 */       for (j = 2; j <= m; k--)
/*      */       {
/*  843 */         _byteTo2Chars(arrayOfByte[j], arrayOfChar, i);
/*  844 */         i += 2;j++;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  848 */       if (k > 0) {
/*  849 */         for (; k > 0; k--)
/*      */         {
/*  851 */           arrayOfChar[(i++)] = '0';
/*  852 */           arrayOfChar[(i++)] = '0';
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/*  857 */       int i1 = m + n;
/*      */       
/*  859 */       if (i1 > 0)
/*      */       {
/*  861 */         i += _byteToChars(arrayOfByte[1], arrayOfChar, i);
/*      */         
/*  863 */         if (i1 == 1)
/*      */         {
/*  865 */           arrayOfChar[(i++)] = '.';
/*      */         }
/*      */         
/*  868 */         for (j = 2; j < m; j++)
/*      */         {
/*  870 */           _byteTo2Chars(arrayOfByte[j], arrayOfChar, i);
/*  871 */           i += 2;
/*      */           
/*  873 */           if (i1 == j)
/*      */           {
/*  875 */             arrayOfChar[(i++)] = '.';
/*      */           }
/*      */         }
/*  878 */         if (arrayOfByte[j] % 10 == 0)
/*      */         {
/*  880 */           i += _byteToChars((byte)(arrayOfByte[j] / 10), arrayOfChar, i);
/*      */         }
/*      */         else
/*      */         {
/*  884 */           _byteTo2Chars(arrayOfByte[j], arrayOfChar, i);
/*  885 */           i += 2;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  890 */         arrayOfChar[(i++)] = '0';
/*  891 */         arrayOfChar[(i++)] = '.';
/*  893 */         for (; 
/*  893 */             i1 < 0; i1++)
/*      */         {
/*  895 */           arrayOfChar[(i++)] = '0';
/*  896 */           arrayOfChar[(i++)] = '0';
/*      */         }
/*      */         
/*  899 */         for (j = 1; j < m; j++)
/*      */         {
/*  901 */           _byteTo2Chars(arrayOfByte[j], arrayOfChar, i);
/*  902 */           i += 2;
/*      */         }
/*      */         
/*  905 */         if (arrayOfByte[j] % 10 == 0)
/*      */         {
/*  907 */           i += _byteToChars((byte)(arrayOfByte[j] / 10), arrayOfChar, i);
/*      */         }
/*      */         else
/*      */         {
/*  911 */           _byteTo2Chars(arrayOfByte[j], arrayOfChar, i);
/*  912 */           i += 2;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  917 */     return new String(arrayOfChar, 0, i);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean toBoolean(byte[] paramArrayOfByte)
/*      */   {
/*  929 */     if (_isZero(paramArrayOfByte)) {
/*  930 */       return false;
/*      */     }
/*  932 */     return true;
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
/*      */   public static byte[] toBytes(double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  948 */     if (Double.isNaN(paramDouble)) {
/*  949 */       throw new IllegalArgumentException(CoreException.getMessage((byte)11));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  954 */     if ((paramDouble == 0.0D) || (paramDouble == -0.0D)) {
/*  955 */       return _makeZero();
/*      */     }
/*  957 */     if (paramDouble == Double.POSITIVE_INFINITY) {
/*  958 */       return _makePosInf();
/*      */     }
/*      */     
/*  961 */     if (paramDouble == Double.NEGATIVE_INFINITY) {
/*  962 */       return _makeNegInf();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  967 */     return _getThinLib().lnxren(paramDouble);
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
/*      */   public static byte[] toBytes(float paramFloat)
/*      */   {
/*  981 */     if (Float.isNaN(paramFloat)) {
/*  982 */       throw new IllegalArgumentException(CoreException.getMessage((byte)11));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  987 */     if ((paramFloat == 0.0F) || (paramFloat == -0.0F)) {
/*  988 */       return _makeZero();
/*      */     }
/*  990 */     if (paramFloat == Float.POSITIVE_INFINITY) {
/*  991 */       return _makePosInf();
/*      */     }
/*  993 */     if (paramFloat == Float.NEGATIVE_INFINITY) {
/*  994 */       return _makeNegInf();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  999 */     String str = Float.toString(paramFloat);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/* 1009 */       return _getLnxLib().lnxcpn(str, false, 0, false, 0, "AMERICAN_AMERICA");
/*      */     }
/*      */     catch (Exception localException) {}
/*      */     
/* 1013 */     return null;
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
/*      */   public static byte[] toBytes(long paramLong)
/*      */   {
/* 1028 */     return _getLnxLib().lnxmin(paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(int paramInt)
/*      */   {
/* 1040 */     return toBytes(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(short paramShort)
/*      */   {
/* 1052 */     return toBytes(paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static byte[] toBytes(byte paramByte)
/*      */   {
/* 1064 */     return toBytes(paramByte);
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
/*      */   public static byte[] toBytes(BigInteger paramBigInteger)
/*      */     throws SQLException
/*      */   {
/* 1082 */     byte[] arrayOfByte1 = new byte[66];
/* 1083 */     long[] arrayOfLong1 = new long[54];
/* 1084 */     long[] arrayOfLong2 = new long[22];
/* 1085 */     int i = 21;
/* 1086 */     int j = 0;
/*      */     
/* 1088 */     int m = 21;
/*      */     
/* 1090 */     int n = 0;
/*      */     
/* 1092 */     int i2 = 0;
/*      */     
/* 1094 */     boolean bool = true;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1101 */     if (paramBigInteger.signum() == 0)
/*      */     {
/* 1103 */       return _makeZero();
/*      */     }
/*      */     
/*      */     byte[] arrayOfByte2;
/*      */     
/*      */     int i3;
/* 1109 */     if (paramBigInteger.signum() == -1)
/*      */     {
/* 1111 */       localObject = paramBigInteger.abs();
/* 1112 */       bool = false;
/* 1113 */       arrayOfByte2 = ((BigInteger)localObject).toByteArray();
/* 1114 */       i3 = (int)Math.floor(((BigInteger)localObject).bitLength() * 0.1505149978319906D);
/*      */     }
/*      */     else
/*      */     {
/* 1118 */       arrayOfByte2 = paramBigInteger.toByteArray();
/* 1119 */       i3 = (int)Math.floor(paramBigInteger.bitLength() * 0.1505149978319906D);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1131 */     if (paramBigInteger.abs().compareTo(BIGINT_HUND.pow(i3)) < 0) {
/* 1132 */       i3--;
/*      */     }
/* 1134 */     if (arrayOfByte2.length > 54) {
/* 1135 */       throw new SQLException(CoreException.getMessage((byte)3));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1140 */     for (int i4 = 0; i4 < arrayOfByte2.length; i4++)
/*      */     {
/* 1142 */       if (arrayOfByte2[i4] < 0) {
/* 1143 */         arrayOfLong1[i4] = (arrayOfByte2[i4] + 256);
/*      */       } else {
/* 1145 */         arrayOfLong1[i4] = arrayOfByte2[i4];
/*      */       }
/*      */     }
/* 1148 */     int k = arrayOfByte2.length;
/*      */     
/*      */     long l;
/* 1151 */     switch (k % 3)
/*      */     {
/*      */     case 2: 
/* 1154 */       arrayOfLong2[i] = ((arrayOfLong1[j] << 8) + arrayOfLong1[(j + 1)]);
/* 1155 */       j = (byte)(j + 2);
/* 1156 */       k -= 2;
/* 1157 */       break;
/*      */     case 1: 
/* 1159 */       arrayOfLong2[i] = arrayOfLong1[j];
/* 1160 */       j = (byte)(j + 1);
/* 1161 */       k--;
/* 1162 */       break;
/*      */     default: 
/* 1164 */       l = (arrayOfLong1[j] << 16) + (arrayOfLong1[(j + 1)] << 8) + arrayOfLong1[(j + 2)];
/* 1165 */       arrayOfLong2[i] = (l % 1000000L);
/* 1166 */       arrayOfLong2[(i - 1)] = (l / 1000000L);
/* 1167 */       m = (byte)(m - (arrayOfLong2[(i - 1)] != 0L ? 1 : 0));
/* 1168 */       j = (byte)(j + 3);
/* 1169 */       k -= 3;
/*      */     }
/*      */     
/*      */     
/*      */ 
/* 1174 */     while (k != 0)
/*      */     {
/* 1176 */       l = (arrayOfLong1[j] << 4) + (arrayOfLong1[(j + 1)] >> 4);
/*      */       
/* 1178 */       for (i = 21; i >= m; i = (byte)(i - 1))
/*      */       {
/* 1180 */         l += (arrayOfLong2[i] << 12);
/* 1181 */         arrayOfLong2[i] = (l % 1000000L);
/* 1182 */         l /= 1000000L;
/*      */       }
/*      */       
/* 1185 */       if (l != 0L)
/*      */       {
/* 1187 */         m = (byte)(m - 1);
/* 1188 */         arrayOfLong2[m] = l;
/*      */       }
/*      */       
/* 1191 */       l = ((arrayOfLong1[(j + 1)] & 0xF) << 8) + arrayOfLong1[(j + 2)];
/*      */       
/* 1193 */       for (i = 21; i >= m; i = (byte)(i - 1))
/*      */       {
/* 1195 */         l += (arrayOfLong2[i] << 12);
/* 1196 */         arrayOfLong2[i] = (l % 1000000L);
/* 1197 */         l /= 1000000L;
/*      */       }
/*      */       
/* 1200 */       if (l != 0L)
/*      */       {
/* 1202 */         m = (byte)(m - 1);
/* 1203 */         arrayOfLong2[m] = l;
/*      */       }
/*      */       
/* 1206 */       j = (byte)(j + 3);
/* 1207 */       k -= 3;
/*      */     }
/*      */     
/*      */     int i1;
/*      */     
/* 1212 */     if ((arrayOfByte1[i2] = (byte)(int)(arrayOfLong2[m] / 10000L)) != 0)
/*      */     {
/* 1214 */       i1 = 3 * (21 - m) + 3;
/* 1215 */       arrayOfByte1[(i2 + 1)] = ((byte)(int)(arrayOfLong2[m] % 10000L / 100L));
/* 1216 */       arrayOfByte1[(i2 + 2)] = ((byte)(int)(arrayOfLong2[m] % 100L));
/* 1217 */       i2 += 3;
/*      */ 
/*      */ 
/*      */     }
/* 1221 */     else if ((arrayOfByte1[i2] = (byte)(int)(arrayOfLong2[m] % 10000L / 100L)) != 0)
/*      */     {
/* 1223 */       i1 = 3 * (21 - m) + 2;
/* 1224 */       arrayOfByte1[(i2 + 1)] = ((byte)(int)(arrayOfLong2[m] % 100L));
/* 1225 */       i2 += 2;
/*      */     }
/*      */     else
/*      */     {
/* 1229 */       arrayOfByte1[i2] = ((byte)(int)arrayOfLong2[m]);
/* 1230 */       i1 = 3 * (21 - m) + 1;
/* 1231 */       i2++;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1237 */     for (i = (byte)(m + 1); i <= 21; i = (byte)(i + 1))
/*      */     {
/* 1239 */       arrayOfByte1[i2] = ((byte)(int)(arrayOfLong2[i] / 10000L));
/* 1240 */       arrayOfByte1[(i2 + 1)] = ((byte)(int)(arrayOfLong2[i] % 10000L / 100L));
/* 1241 */       arrayOfByte1[(i2 + 2)] = ((byte)(int)(arrayOfLong2[i] % 100L));
/* 1242 */       i2 += 3;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1247 */     for (i4 = i2 - 1; i4 >= 0; i4--)
/*      */     {
/* 1249 */       if (arrayOfByte1[i4] != 0)
/*      */         break;
/* 1251 */       i1--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1260 */     if (i1 > 19)
/*      */     {
/* 1262 */       i4 = 20;
/* 1263 */       i1 = 19;
/* 1264 */       if (arrayOfByte1[i4] >= 50)
/*      */       {
/* 1266 */         i4--; int 
/* 1267 */           tmp855_853 = i4; byte[] tmp855_852 = arrayOfByte1;tmp855_852[tmp855_853] = ((byte)(tmp855_852[tmp855_853] + 1));
/* 1268 */         for (; arrayOfByte1[i4] == 100; 
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1278 */             tmp897_894[tmp897_895] = ((byte)(tmp897_894[tmp897_895] + 1)))
/*      */         {
/* 1270 */           if (i4 == 0)
/*      */           {
/* 1272 */             i3++;
/* 1273 */             arrayOfByte1[i4] = 1;
/* 1274 */             break;
/*      */           }
/* 1276 */           arrayOfByte1[i4] = 0;
/* 1277 */           i4--;
/*      */         }
/*      */         
/*      */ 
/* 1281 */         for (i4 = i1 - 1; i4 >= 0; i4--)
/*      */         {
/* 1283 */           if (arrayOfByte1[i4] != 0) break;
/* 1284 */           i1--;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1291 */     if (i3 > 62) {
/* 1292 */       throw new SQLException(CoreException.getMessage((byte)3));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1297 */     int tmp855_853 = new byte[i1 + 1];
/* 1298 */     tmp855_853[0] = ((byte)i3);
/* 1299 */     System.arraycopy(arrayOfByte1, 0, tmp855_853, 1, i1);
/*      */     
/*      */ 
/*      */ 
/* 1303 */     return _toLnxFmt((byte[])tmp855_853, bool);
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
/*      */   public static byte[] toBytes(BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/* 1319 */     byte[] arrayOfByte1 = new byte[66];
/* 1320 */     long[] arrayOfLong1 = new long[54];
/* 1321 */     long[] arrayOfLong2 = new long[22];
/* 1322 */     int i = 21;
/* 1323 */     int j = 0;
/*      */     
/* 1325 */     int m = 21;
/*      */     
/*      */ 
/* 1328 */     int i1 = 0;
/*      */     
/*      */ 
/* 1331 */     int i2 = 0;
/*      */     
/*      */ 
/*      */ 
/* 1335 */     BigDecimal localBigDecimal1 = paramBigDecimal.abs();
/*      */     
/* 1337 */     int i6 = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1343 */     if (paramBigDecimal.signum() == 0)
/*      */     {
/* 1345 */       return _makeZero();
/*      */     }
/*      */     
/*      */ 
/* 1349 */     boolean bool = paramBigDecimal.signum() != -1;
/*      */     
/*      */ 
/* 1352 */     int i4 = paramBigDecimal.scale();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1361 */     if (i4 < 0)
/*      */     {
/* 1363 */       paramBigDecimal = paramBigDecimal.setScale(0);
/* 1364 */       i4 = 0;
/*      */     }
/*      */     
/*      */ 
/* 1368 */     int i5 = localBigDecimal1.compareTo(BIGDEC_ONE);
/* 1369 */     int i7 = 0;
/*      */     BigDecimal localBigDecimal2;
/* 1371 */     if (i5 == -1)
/*      */     {
/*      */       do {
/* 1374 */         i7++;
/* 1375 */         localBigDecimal2 = localBigDecimal1.movePointRight(i7);
/* 1376 */       } while (localBigDecimal2.compareTo(BIGDEC_ONE) < 0);
/*      */       
/* 1378 */       i6 = -i7;
/*      */     }
/*      */     else
/*      */     {
/*      */       do {
/* 1383 */         i7++;
/* 1384 */         localBigDecimal2 = localBigDecimal1.movePointLeft(i7);
/* 1385 */       } while (localBigDecimal2.compareTo(BIGDEC_ONE) >= 0);
/*      */       
/* 1387 */       i6 = i7;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1392 */     byte[] arrayOfByte2 = localBigDecimal1.movePointRight(i4).toBigInteger().toByteArray();
/*      */     
/*      */ 
/* 1395 */     if (arrayOfByte2.length > 54) {
/* 1396 */       throw new SQLException(CoreException.getMessage((byte)3));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1404 */     for (int i3 = 0; i3 < arrayOfByte2.length; i3++)
/*      */     {
/* 1406 */       if (arrayOfByte2[i3] < 0) {
/* 1407 */         arrayOfLong1[i3] = (arrayOfByte2[i3] + 256);
/*      */       } else {
/* 1409 */         arrayOfLong1[i3] = arrayOfByte2[i3];
/*      */       }
/*      */     }
/* 1412 */     int k = arrayOfByte2.length;
/*      */     
/*      */     long l;
/* 1415 */     switch (k % 3)
/*      */     {
/*      */     case 2: 
/* 1418 */       arrayOfLong2[i] = ((arrayOfLong1[j] << 8) + arrayOfLong1[(j + 1)]);
/* 1419 */       j = (byte)(j + 2);
/* 1420 */       k -= 2;
/* 1421 */       break;
/*      */     case 1: 
/* 1423 */       arrayOfLong2[i] = arrayOfLong1[j];
/* 1424 */       j = (byte)(j + 1);
/* 1425 */       k--;
/* 1426 */       break;
/*      */     default: 
/* 1428 */       l = (arrayOfLong1[j] << 16) + (arrayOfLong1[(j + 1)] << 8) + arrayOfLong1[(j + 2)];
/* 1429 */       arrayOfLong2[i] = (l % 1000000L);
/* 1430 */       arrayOfLong2[(i - 1)] = (l / 1000000L);
/* 1431 */       m = (byte)(m - (arrayOfLong2[(i - 1)] != 0L ? 1 : 0));
/* 1432 */       j = (byte)(j + 3);
/* 1433 */       k -= 3;
/*      */     }
/*      */     
/*      */     
/*      */ 
/* 1438 */     while (k != 0)
/*      */     {
/* 1440 */       l = (arrayOfLong1[j] << 4) + (arrayOfLong1[(j + 1)] >> 4);
/*      */       
/* 1442 */       for (i = 21; i >= m; i = (byte)(i - 1))
/*      */       {
/* 1444 */         l += (arrayOfLong2[i] << 12);
/* 1445 */         arrayOfLong2[i] = (l % 1000000L);
/* 1446 */         l /= 1000000L;
/*      */       }
/*      */       
/* 1449 */       if (l != 0L)
/*      */       {
/* 1451 */         m = (byte)(m - 1);
/* 1452 */         arrayOfLong2[m] = l;
/*      */       }
/*      */       
/* 1455 */       l = ((arrayOfLong1[(j + 1)] & 0xF) << 8) + arrayOfLong1[(j + 2)];
/*      */       
/* 1457 */       for (i = 21; i >= m; i = (byte)(i - 1))
/*      */       {
/* 1459 */         l += (arrayOfLong2[i] << 12);
/* 1460 */         arrayOfLong2[i] = (l % 1000000L);
/* 1461 */         l /= 1000000L;
/*      */       }
/*      */       
/* 1464 */       if (l != 0L)
/*      */       {
/* 1466 */         m = (byte)(m - 1);
/* 1467 */         arrayOfLong2[m] = l;
/*      */       }
/*      */       
/* 1470 */       j = (byte)(j + 3);
/* 1471 */       k -= 3;
/*      */     }
/*      */     
/*      */     int n;
/*      */     
/* 1476 */     if ((arrayOfByte1[i1] = (byte)(int)(arrayOfLong2[m] / 10000L)) != 0)
/*      */     {
/* 1478 */       n = 3 * (21 - m) + 3;
/* 1479 */       arrayOfByte1[(i1 + 1)] = ((byte)(int)(arrayOfLong2[m] % 10000L / 100L));
/* 1480 */       arrayOfByte1[(i1 + 2)] = ((byte)(int)(arrayOfLong2[m] % 100L));
/* 1481 */       i1 += 3;
/*      */ 
/*      */ 
/*      */     }
/* 1485 */     else if ((arrayOfByte1[i1] = (byte)(int)(arrayOfLong2[m] % 10000L / 100L)) != 0)
/*      */     {
/* 1487 */       n = 3 * (21 - m) + 2;
/* 1488 */       arrayOfByte1[(i1 + 1)] = ((byte)(int)(arrayOfLong2[m] % 100L));
/* 1489 */       i1 += 2;
/*      */     }
/*      */     else
/*      */     {
/* 1493 */       arrayOfByte1[i1] = ((byte)(int)arrayOfLong2[m]);
/* 1494 */       n = 3 * (21 - m) + 1;
/* 1495 */       i1++;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1501 */     for (i = (byte)(m + 1); i <= 21; i = (byte)(i + 1))
/*      */     {
/* 1503 */       arrayOfByte1[i1] = ((byte)(int)(arrayOfLong2[i] / 10000L));
/* 1504 */       arrayOfByte1[(i1 + 1)] = ((byte)(int)(arrayOfLong2[i] % 10000L / 100L));
/* 1505 */       arrayOfByte1[(i1 + 2)] = ((byte)(int)(arrayOfLong2[i] % 100L));
/* 1506 */       i1 += 3;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1511 */     for (i3 = i1 - 1; i3 >= 0; i3--)
/*      */     {
/* 1513 */       if (arrayOfByte1[i3] != 0)
/*      */         break;
/* 1515 */       n--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1526 */     if ((i4 > 0) && ((i4 & 0x1) != 0))
/*      */     {
/*      */ 
/* 1529 */       int i8 = n;
/* 1530 */       byte[] arrayOfByte4 = new byte[i8 + 1];
/*      */       
/* 1532 */       if (arrayOfByte1[0] <= 9)
/*      */       {
/* 1534 */         for (i3 = 0; i3 < i8 - 1; i3++)
/*      */         {
/* 1536 */           arrayOfByte4[i3] = ((byte)(arrayOfByte1[i3] % 10 * 10 + arrayOfByte1[(i3 + 1)] / 10));
/*      */         }
/* 1538 */         arrayOfByte4[i3] = ((byte)(arrayOfByte1[i3] % 10 * 10));
/* 1539 */         if (arrayOfByte4[(i8 - 1)] == 0)
/*      */         {
/* 1541 */           n--;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1546 */         arrayOfByte4[i8] = ((byte)(arrayOfByte1[(i8 - 1)] % 10 * 10));
/* 1547 */         for (i3 = i8 - 1; i3 > 0; i3--)
/*      */         {
/* 1549 */           arrayOfByte4[i3] = ((byte)(arrayOfByte1[i3] / 10 + arrayOfByte1[(i3 - 1)] % 10 * 10));
/*      */         }
/* 1551 */         arrayOfByte4[i3] = ((byte)(arrayOfByte1[i3] / 10));
/* 1552 */         if (arrayOfByte4[i8] > 0)
/*      */         {
/* 1554 */           n++;
/*      */         }
/*      */       }
/* 1557 */       System.arraycopy(arrayOfByte4, 0, arrayOfByte1, 0, n);
/*      */     }
/*      */     
/*      */ 
/* 1561 */     if (n > 20)
/*      */     {
/* 1563 */       i3 = 20;
/* 1564 */       n = 20;
/* 1565 */       if (arrayOfByte1[i3] >= 50)
/*      */       {
/* 1567 */         i3--; int 
/* 1568 */           tmp1106_1104 = i3; byte[] tmp1106_1103 = arrayOfByte1;tmp1106_1103[tmp1106_1104] = ((byte)(tmp1106_1103[tmp1106_1104] + 1));
/* 1569 */         for (; arrayOfByte1[i3] == 100; 
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1579 */             tmp1148_1145[tmp1148_1146] = ((byte)(tmp1148_1145[tmp1148_1146] + 1)))
/*      */         {
/* 1571 */           if (i3 == 0)
/*      */           {
/* 1573 */             i6++;
/* 1574 */             arrayOfByte1[i3] = 1;
/* 1575 */             break;
/*      */           }
/* 1577 */           arrayOfByte1[i3] = 0;
/* 1578 */           i3--;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1583 */       for (i3 = n - 1; i3 >= 0; i3--)
/*      */       {
/* 1585 */         if (arrayOfByte1[i3] != 0) break;
/* 1586 */         n--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1592 */     if (i6 <= 0)
/*      */     {
/* 1594 */       if (arrayOfByte1[0] < 10) {
/* 1595 */         i2 = -(2 - i6) / 2 + 1;
/*      */       } else {
/* 1597 */         i2 = -(2 - i6) / 2;
/*      */       }
/*      */     }
/*      */     else {
/* 1601 */       i2 = (i6 - 1) / 2;
/*      */     }
/*      */     
/*      */ 
/* 1605 */     if (i2 > 62) {
/* 1606 */       throw new SQLException(CoreException.getMessage((byte)3));
/*      */     }
/*      */     
/* 1609 */     if (i2 <= -65) {
/* 1610 */       throw new SQLException(CoreException.getMessage((byte)2));
/*      */     }
/*      */     
/*      */ 
/* 1614 */     byte[] arrayOfByte3 = new byte[n + 1];
/* 1615 */     arrayOfByte3[0] = ((byte)i2);
/* 1616 */     System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 1, n);
/*      */     
/*      */ 
/* 1619 */     return _toLnxFmt(arrayOfByte3, bool);
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
/*      */   public static byte[] toBytes(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1648 */     int i = 0;
/* 1649 */     int j = 0;
/*      */     
/*      */ 
/* 1652 */     byte[] arrayOfByte1 = new byte[22];
/*      */     
/* 1654 */     int n = 0;
/*      */     
/* 1656 */     boolean bool = true;
/* 1657 */     int i1 = 0;
/* 1658 */     int i2 = 0;
/* 1659 */     int i3 = 0;
/*      */     
/* 1661 */     int i5 = 0;
/* 1662 */     int i6 = 0;
/* 1663 */     int i7 = 40;
/*      */     
/*      */ 
/* 1666 */     int i8 = 0;
/* 1667 */     int i9 = 0;
/* 1668 */     int i10 = 0;
/*      */     
/* 1670 */     int i12 = 0;
/*      */     
/*      */ 
/*      */     int i13;
/*      */     
/*      */     int i14;
/*      */     
/* 1677 */     if (((i13 = paramString.indexOf("E")) != -1) || ((i13 = paramString.indexOf("e")) != -1))
/*      */     {
/*      */ 
/* 1680 */       localObject = new StringBuffer(paramString.length() + 5);
/* 1681 */       i14 = 0;
/* 1682 */       BigDecimal localBigDecimal = null;
/* 1683 */       int i15 = paramString.charAt(0) == '-' ? 1 : 0;
/* 1684 */       String str1 = paramString.substring(i13 + 1);
/* 1685 */       String str2 = paramString.substring(i15 != 0 ? 1 : 0, i13);
/* 1686 */       localBigDecimal = new BigDecimal(str2);
/* 1687 */       int i16 = str1.charAt(0) == '-' ? 1 : 0;
/* 1688 */       str1 = str1.substring(1);
/* 1689 */       i14 = Integer.parseInt(str1);
/*      */       
/* 1691 */       String str3 = localBigDecimal.toString();
/* 1692 */       int i17 = str3.indexOf(".");
/* 1693 */       int i18 = str3.length();
/* 1694 */       int i19 = i18;
/*      */       
/* 1696 */       if (i17 != -1)
/*      */       {
/* 1698 */         str3 = str3.substring(0, i17) + str3.substring(i17 + 1);
/*      */         
/* 1700 */         i18--;
/* 1701 */         if (i16 != 0)
/*      */         {
/* 1703 */           i14 -= i17;
/*      */         }
/*      */         else {
/* 1706 */           i14++;
/* 1707 */           i19 = i14;
/*      */         }
/*      */         
/*      */ 
/*      */       }
/* 1712 */       else if (i16 != 0)
/*      */       {
/* 1714 */         i14 -= i18;
/*      */       }
/*      */       else {
/* 1717 */         i14++;
/* 1718 */         i19 = i14;
/*      */       }
/*      */       
/*      */ 
/* 1722 */       if (i15 != 0)
/* 1723 */         ((StringBuffer)localObject).append("-");
/*      */       int i20;
/* 1725 */       if (i16 != 0)
/*      */       {
/* 1727 */         ((StringBuffer)localObject).append("0.");
/* 1728 */         for (i20 = 0; i20 < i14; i20++)
/*      */         {
/* 1730 */           ((StringBuffer)localObject).append("0");
/*      */         }
/* 1732 */         ((StringBuffer)localObject).append(str3);
/*      */       }
/*      */       else
/*      */       {
/* 1736 */         i20 = i14 > i18 ? i14 : i18;
/* 1737 */         for (int i21 = 0; i21 < i20; i21++)
/*      */         {
/* 1739 */           if (i19 == i21)
/* 1740 */             ((StringBuffer)localObject).append(".");
/* 1741 */           ((StringBuffer)localObject).append(i18 > i21 ? str3.charAt(i21) : '0');
/*      */         }
/*      */       }
/*      */       
/* 1745 */       paramString = ((StringBuffer)localObject).toString();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1750 */     paramString = paramString.trim();
/*      */     
/* 1752 */     int i11 = paramString.length();
/*      */     
/*      */ 
/*      */ 
/* 1756 */     if (paramString.charAt(0) == '-')
/*      */     {
/* 1758 */       i11--;
/* 1759 */       bool = false;
/* 1760 */       i10 = 1;
/*      */     }
/*      */     
/* 1763 */     i = i11;
/*      */     
/*      */ 
/*      */ 
/* 1767 */     Object localObject = new char[i11];
/* 1768 */     paramString.getChars(i10, i11 + i10, (char[])localObject, 0);
/*      */     
/*      */ 
/* 1771 */     for (int k = 0; k < i11; k++)
/*      */     {
/* 1773 */       if (localObject[k] == '.')
/*      */       {
/* 1775 */         i2 = 1;
/* 1776 */         break;
/*      */       }
/*      */     }
/*      */     
/* 1780 */     if (i2 == 0) { paramInt = 0;
/*      */     }
/*      */     
/* 1783 */     while ((j < i) && (localObject[j] == '0'))
/*      */     {
/* 1785 */       j++;
/* 1786 */       if (i2 == 1) {
/* 1787 */         i12++;
/*      */       }
/*      */     }
/* 1790 */     if (j == i) {
/* 1791 */       return _makeZero();
/*      */     }
/*      */     
/*      */     int m;
/* 1795 */     if ((i11 >= 2) && (localObject[j] == '.'))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1800 */       while ((i > 0) && (localObject[(i - 1)] == '0')) { i--;
/*      */       }
/*      */       
/*      */ 
/* 1804 */       j++;
/*      */       
/*      */ 
/* 1807 */       if (j == i)
/*      */       {
/* 1809 */         return _makeZero();
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1820 */       i3--;
/* 1822 */       for (; 
/* 1822 */           (j < i - 1) && (localObject[j] == '0') && (localObject[(j + 1)] == '0'); 
/* 1823 */           j += 2)
/*      */       {
/* 1825 */         i3--;
/* 1826 */         i6 += 2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1831 */       if (i3 < -65) {
/* 1832 */         throw new SQLException(CoreException.getMessage((byte)2));
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1838 */       if (i - j > i7)
/*      */       {
/*      */ 
/* 1841 */         m = 2 + i7;
/*      */         
/*      */ 
/* 1844 */         if (i6 > 0) {
/* 1845 */           m += i6;
/*      */         }
/* 1847 */         if (m <= i)
/* 1848 */           i = m;
/* 1849 */         i8 = i;
/* 1850 */         i1 = 1;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1856 */       n = i - j >> 1;
/*      */       
/* 1858 */       if ((i - j) % 2 != 0)
/*      */       {
/* 1860 */         arrayOfByte1[n] = ((byte)(Integer.parseInt(new String((char[])localObject, i - 1, 1)) * 10));
/*      */         
/* 1862 */         i5++;
/* 1863 */         i--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1868 */     while (i > j)
/*      */     {
/* 1870 */       n--;
/* 1871 */       arrayOfByte1[n] = ((byte)Integer.parseInt(new String((char[])localObject, i - 2, 2)));
/*      */       
/* 1873 */       i -= 2;
/* 1874 */       i5++; continue;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1890 */       while ((paramInt > 0) && (i > 0) && (localObject[(i - 1)] == '0'))
/*      */       {
/* 1892 */         i--;
/* 1893 */         paramInt--;
/*      */       }
/*      */       
/*      */ 
/* 1897 */       if ((paramInt == 0) && (i > 1))
/*      */       {
/* 1899 */         if (localObject[(i - 1)] == '.') {
/* 1900 */           i--;
/*      */         }
/* 1902 */         if (j == i) {
/* 1903 */           return _makeZero();
/*      */         }
/*      */         
/*      */ 
/* 1907 */         while ((i > 1) && (localObject[(i - 2)] == '0') && (localObject[(i - 1)] == '0'))
/*      */         {
/* 1909 */           i -= 2;
/* 1910 */           i3++;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1917 */       if (i3 > 62) {
/* 1918 */         throw new SQLException(CoreException.getMessage((byte)3));
/*      */       }
/*      */       
/*      */ 
/* 1922 */       if (i - j - (i2 != 0 ? 1 : 0) > i7)
/*      */       {
/*      */ 
/* 1925 */         m = i7 + (i2 != 0 ? 1 : 0);
/* 1926 */         i14 = i - m;
/* 1927 */         i = m;
/* 1928 */         paramInt -= i14;
/* 1929 */         if (paramInt < 0)
/* 1930 */           paramInt = 0;
/* 1931 */         i1 = 1;
/* 1932 */         i8 = i;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1939 */       int i4 = paramInt == 0 ? i - j : i - paramInt - 1;
/*      */       
/*      */ 
/* 1942 */       if (i12 > 0) {
/* 1943 */         i4 -= i12;
/*      */       }
/*      */       
/*      */ 
/* 1947 */       if (i4 % 2 != 0)
/*      */       {
/* 1949 */         i9 = Integer.parseInt(new String((char[])localObject, j, 1));
/* 1950 */         j++;
/* 1951 */         i4--;
/* 1952 */         if (i - 1 == i7) {
/* 1953 */           paramInt--;
/* 1954 */           i--;
/* 1955 */           i1 = 1;
/* 1956 */           i8 = i;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1961 */         i9 = Integer.parseInt(new String((char[])localObject, j, 2));
/* 1962 */         j += 2;
/* 1963 */         i4 -= 2;
/*      */       }
/*      */       
/* 1966 */       arrayOfByte1[n] = ((byte)i9);
/* 1967 */       n++;
/* 1968 */       i5++;
/*      */       
/*      */ 
/*      */ 
/* 1972 */       while (i4 > 0)
/*      */       {
/* 1974 */         arrayOfByte1[n] = ((byte)Integer.parseInt(new String((char[])localObject, j, 2)));
/*      */         
/* 1976 */         n++;
/* 1977 */         j += 2;
/* 1978 */         i3++;
/* 1979 */         i4 -= 2;
/* 1980 */         i5++;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1985 */       if (j < i)
/*      */       {
/* 1987 */         if (paramInt % 2 != 0)
/*      */         {
/* 1989 */           n += paramInt / 2;
/* 1990 */           arrayOfByte1[n] = ((byte)(Integer.parseInt(new String((char[])localObject, i - 1, 1)) * 10));
/*      */           
/* 1992 */           i--;
/* 1993 */           paramInt--;
/*      */         }
/*      */         else
/*      */         {
/* 1997 */           n += paramInt / 2 - 1;
/* 1998 */           arrayOfByte1[n] = ((byte)Integer.parseInt(new String((char[])localObject, i - 2, 2)));
/*      */           
/* 2000 */           i -= 2;
/* 2001 */           paramInt -= 2;
/*      */         }
/*      */         
/* 2004 */         i5++;
/* 2005 */         n--;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 2010 */       while (paramInt > 0)
/*      */       {
/* 2012 */         arrayOfByte1[n] = ((byte)Integer.parseInt(new String((char[])localObject, i - 2, 2)));
/*      */         
/* 2014 */         n--;
/* 2015 */         i -= 2;
/* 2016 */         paramInt -= 2;
/* 2017 */         i5++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2023 */     if (i1 != 0)
/*      */     {
/* 2025 */       i14 = i5;
/* 2026 */       i9 = Integer.parseInt(new String((char[])localObject, i8, 1));
/* 2027 */       if (i9 >= 5)
/*      */       {
/* 2029 */         i14--; int 
/* 2030 */           tmp1317_1315 = i14; byte[] tmp1317_1313 = arrayOfByte1;tmp1317_1313[tmp1317_1315] = ((byte)(tmp1317_1313[tmp1317_1315] + 1));
/* 2031 */         for (; arrayOfByte1[i14] == 100; 
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2041 */             tmp1363_1359[tmp1363_1361] = ((byte)(tmp1363_1359[tmp1363_1361] + 1)))
/*      */         {
/* 2033 */           if (i14 == 0)
/*      */           {
/* 2035 */             i3++;
/* 2036 */             arrayOfByte1[i14] = 1;
/* 2037 */             break;
/*      */           }
/* 2039 */           arrayOfByte1[i14] = 0;
/* 2040 */           i14--;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2049 */         for (k = i5 - 1; k >= 0; k--)
/*      */         {
/* 2051 */           if (arrayOfByte1[k] != 0) break;
/* 2052 */           i5--;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2061 */     byte[] arrayOfByte2 = new byte[i5 + 1];
/* 2062 */     arrayOfByte2[0] = ((byte)i3);
/* 2063 */     System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 1, i5);
/*      */     
/*      */ 
/*      */ 
/* 2067 */     return _toLnxFmt(arrayOfByte2, bool);
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
/*      */   public static byte[] toBytes(boolean paramBoolean)
/*      */   {
/* 2081 */     if (paramBoolean) {
/* 2082 */       return toBytes(1L);
/*      */     }
/* 2084 */     return toBytes(0L);
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
/*      */   public byte[] toBytes()
/*      */   {
/* 2102 */     return getBytes();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double doubleValue()
/*      */   {
/* 2112 */     return toDouble(shareBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float floatValue()
/*      */   {
/* 2122 */     return toFloat(shareBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long longValue()
/*      */     throws SQLException
/*      */   {
/* 2134 */     return toLong(shareBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int intValue()
/*      */     throws SQLException
/*      */   {
/* 2146 */     return toInt(shareBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short shortValue()
/*      */     throws SQLException
/*      */   {
/* 2158 */     return toShort(shareBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte byteValue()
/*      */     throws SQLException
/*      */   {
/* 2170 */     return toByte(shareBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigInteger bigIntegerValue()
/*      */     throws SQLException
/*      */   {
/* 2181 */     return toBigInteger(shareBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal bigDecimalValue()
/*      */     throws SQLException
/*      */   {
/* 2194 */     return toBigDecimal(shareBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String stringValue()
/*      */   {
/* 2204 */     return toString(shareBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean booleanValue()
/*      */   {
/* 2214 */     return toBoolean(shareBytes());
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
/*      */     try
/*      */     {
/* 2228 */       return bigDecimalValue();
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/* 2232 */       return new SQLException(localSQLException.getMessage());
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
/*      */   public Object makeJdbcArray(int paramInt)
/*      */   {
/* 2246 */     BigDecimal[] arrayOfBigDecimal = new BigDecimal[paramInt];
/*      */     
/* 2248 */     return arrayOfBigDecimal;
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
/* 2260 */     String str = paramClass.getName();
/*      */     
/* 2262 */     if ((str.compareTo("java.lang.Integer") == 0) || (str.compareTo("java.lang.Long") == 0) || (str.compareTo("java.lang.Float") == 0) || (str.compareTo("java.lang.Double") == 0) || (str.compareTo("java.math.BigInteger") == 0) || (str.compareTo("java.math.BigDecimal") == 0) || (str.compareTo("java.lang.String") == 0) || (str.compareTo("java.lang.Boolean") == 0))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2270 */       return true;
/*      */     }
/*      */     
/* 2273 */     return false;
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
/*      */   public NUMBER abs()
/*      */     throws SQLException
/*      */   {
/* 2289 */     return new NUMBER(_getLnxLib().lnxabs(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER acos()
/*      */     throws SQLException
/*      */   {
/* 2301 */     return new NUMBER(_getLnxLib().lnxacos(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER add(NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 2314 */     return new NUMBER(_getLnxLib().lnxadd(shareBytes(), paramNUMBER.shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER asin()
/*      */     throws SQLException
/*      */   {
/* 2326 */     return new NUMBER(_getLnxLib().lnxasin(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER atan()
/*      */     throws SQLException
/*      */   {
/* 2339 */     return new NUMBER(_getLnxLib().lnxatan(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER atan2(NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 2351 */     return new NUMBER(_getLnxLib().lnxatan2(shareBytes(), paramNUMBER.shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER ceil()
/*      */     throws SQLException
/*      */   {
/* 2364 */     return new NUMBER(_getLnxLib().lnxceil(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER cos()
/*      */     throws SQLException
/*      */   {
/* 2376 */     return new NUMBER(_getLnxLib().lnxcos(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER cosh()
/*      */     throws SQLException
/*      */   {
/* 2389 */     return new NUMBER(_getLnxLib().lnxcsh(shareBytes()));
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
/*      */   public NUMBER decrement()
/*      */     throws SQLException
/*      */   {
/* 2404 */     return new NUMBER(_getLnxLib().lnxdec(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER div(NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 2417 */     return new NUMBER(_getLnxLib().lnxdiv(shareBytes(), paramNUMBER.shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER exp()
/*      */     throws SQLException
/*      */   {
/* 2430 */     return new NUMBER(_getLnxLib().lnxexp(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER floatingPointRound(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2443 */     return new NUMBER(_getLnxLib().lnxfpr(shareBytes(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER floor()
/*      */     throws SQLException
/*      */   {
/* 2455 */     return new NUMBER(_getLnxLib().lnxflo(shareBytes()));
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
/*      */   public NUMBER increment()
/*      */     throws SQLException
/*      */   {
/* 2470 */     return new NUMBER(_getLnxLib().lnxinc(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER ln()
/*      */     throws SQLException
/*      */   {
/* 2483 */     return new NUMBER(_getLnxLib().lnxln(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER log(NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 2496 */     return new NUMBER(_getLnxLib().lnxlog(shareBytes(), paramNUMBER.shareBytes()));
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
/*      */   public NUMBER mod(NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 2510 */     return new NUMBER(_getLnxLib().lnxmod(shareBytes(), paramNUMBER.shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER mul(NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 2522 */     return new NUMBER(_getLnxLib().lnxmul(shareBytes(), paramNUMBER.shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER negate()
/*      */     throws SQLException
/*      */   {
/* 2534 */     return new NUMBER(_getLnxLib().lnxneg(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER pow(NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 2547 */     return new NUMBER(_getLnxLib().lnxbex(shareBytes(), paramNUMBER.shareBytes()));
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
/*      */   public NUMBER pow(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2562 */     return new NUMBER(_getLnxLib().lnxpow(shareBytes(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER round(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2575 */     return new NUMBER(_getLnxLib().lnxrou(shareBytes(), paramInt));
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
/*      */   public NUMBER scale(int paramInt1, int paramInt2, boolean[] paramArrayOfBoolean)
/*      */     throws SQLException
/*      */   {
/* 2597 */     return new NUMBER(_getLnxLib().lnxsca(shareBytes(), paramInt1, paramInt2, paramArrayOfBoolean));
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
/*      */   public NUMBER shift(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2615 */     return new NUMBER(_getLnxLib().lnxshift(shareBytes(), paramInt));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER sin()
/*      */     throws SQLException
/*      */   {
/* 2626 */     return new NUMBER(_getLnxLib().lnxsin(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER sinh()
/*      */     throws SQLException
/*      */   {
/* 2637 */     return new NUMBER(_getLnxLib().lnxsnh(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER sqroot()
/*      */     throws SQLException
/*      */   {
/* 2648 */     return new NUMBER(_getLnxLib().lnxsqr(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER sub(NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 2659 */     return new NUMBER(_getLnxLib().lnxsub(shareBytes(), paramNUMBER.shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER tan()
/*      */     throws SQLException
/*      */   {
/* 2670 */     return new NUMBER(_getLnxLib().lnxtan(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER tanh()
/*      */     throws SQLException
/*      */   {
/* 2682 */     return new NUMBER(_getLnxLib().lnxtnh(shareBytes()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER truncate(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2695 */     return new NUMBER(_getLnxLib().lnxtru(shareBytes(), paramInt));
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
/*      */   public static NUMBER formattedTextToNumber(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 2718 */     return new NUMBER(_getLnxLib().lnxfcn(paramString1, paramString2, paramString3));
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
/*      */   public static NUMBER textToPrecisionNumber(String paramString1, boolean paramBoolean1, int paramInt1, boolean paramBoolean2, int paramInt2, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 2756 */     return new NUMBER(_getLnxLib().lnxcpn(paramString1, paramBoolean1, paramInt1, paramBoolean2, paramInt2, paramString2));
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
/*      */   public String toFormattedText(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 2779 */     return _getLnxLib().lnxnfn(shareBytes(), paramString1, paramString2);
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
/*      */   public String toText(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 2796 */     return _getLnxLib().lnxnuc(shareBytes(), paramInt, paramString);
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
/*      */   public int compareTo(NUMBER paramNUMBER)
/*      */   {
/* 2810 */     return compareBytes(shareBytes(), paramNUMBER.shareBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isInf()
/*      */   {
/* 2821 */     return _isInf(shareBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isNegInf()
/*      */   {
/* 2831 */     return _isNegInf(shareBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isPosInf()
/*      */   {
/* 2841 */     return _isPosInf(shareBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isInt()
/*      */   {
/* 2851 */     return _isInt(shareBytes());
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
/*      */   public static boolean isValid(byte[] paramArrayOfByte)
/*      */   {
/* 2864 */     int i = (byte)paramArrayOfByte.length;
/*      */     int j;
/* 2866 */     if (_isPositive(paramArrayOfByte))
/*      */     {
/* 2868 */       if (i == 1) {
/* 2869 */         return _isZero(paramArrayOfByte);
/*      */       }
/*      */       
/* 2872 */       if ((paramArrayOfByte[0] == -1) && (paramArrayOfByte[1] == 101))
/*      */       {
/* 2874 */         return i == 2;
/*      */       }
/* 2876 */       if (i > 21) {
/* 2877 */         return false;
/*      */       }
/*      */       
/* 2880 */       if ((paramArrayOfByte[1] < 2) || (paramArrayOfByte[(i - 1)] < 2)) {
/* 2881 */         return false;
/*      */       }
/*      */       
/* 2884 */       for (k = 1; k < i; k++)
/*      */       {
/* 2886 */         j = paramArrayOfByte[k];
/* 2887 */         if ((j < 1) || (j > 100)) {
/* 2888 */           return false;
/*      */         }
/*      */       }
/* 2891 */       return true;
/*      */     }
/*      */     
/*      */ 
/* 2895 */     if (i < 3) {
/* 2896 */       return _isNegInf(paramArrayOfByte);
/*      */     }
/* 2898 */     if (i > 21) {
/* 2899 */       return false;
/*      */     }
/*      */     
/* 2902 */     if (paramArrayOfByte[(i - 1)] != 102)
/*      */     {
/* 2904 */       if (i <= 20) {
/* 2905 */         return false;
/*      */       }
/*      */     }
/*      */     else {
/* 2909 */       i = (byte)(i - 1);
/*      */     }
/*      */     
/*      */ 
/* 2913 */     if ((paramArrayOfByte[1] > 100) || (paramArrayOfByte[(i - 1)] > 100)) {
/* 2914 */       return false;
/*      */     }
/*      */     
/* 2917 */     for (int k = 1; k < i; k++)
/*      */     {
/* 2919 */       j = paramArrayOfByte[k];
/*      */       
/* 2921 */       if ((j < 2) || (j > 101)) {
/* 2922 */         return false;
/*      */       }
/*      */     }
/* 2925 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isZero()
/*      */   {
/* 2936 */     return _isZero(shareBytes());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static NUMBER e()
/*      */   {
/* 2946 */     return new NUMBER(E);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static NUMBER ln10()
/*      */   {
/* 2956 */     return new NUMBER(LN10);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static NUMBER negInf()
/*      */   {
/* 2966 */     return new NUMBER(_makeNegInf());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static NUMBER pi()
/*      */   {
/* 2976 */     return new NUMBER(PI);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static NUMBER posInf()
/*      */   {
/* 2986 */     return new NUMBER(_makePosInf());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static NUMBER zero()
/*      */   {
/* 2996 */     return new NUMBER(_makeZero());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int sign()
/*      */   {
/* 3007 */     if (_isZero(shareBytes())) {
/* 3008 */       return 0;
/*      */     }
/* 3010 */     return _isPositive(shareBytes()) ? 1 : -1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3020 */   private static final BigDecimal BIGDEC_NEGZERO = new BigDecimal("-0");
/* 3021 */   private static final BigDecimal BIGDEC_ZERO = BigDecimal.valueOf(0L);
/* 3022 */   private static final BigDecimal BIGDEC_ONE = BigDecimal.valueOf(1L);
/* 3023 */   private static final BigInteger BIGINT_ZERO = BigInteger.valueOf(0L);
/* 3024 */   private static final BigInteger BIGINT_HUND = BigInteger.valueOf(100L);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final byte DIGEND = 21;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final byte ODIGEND = 9;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int HUNDIGMAX = 66;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int BIGINTARRAYMAX = 54;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final double BIGRATIO = 0.1505149978319906D;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final int BIGLENMAX = 22;
/*      */   
/*      */ 
/*      */ 
/*      */   static final byte LNXM_NUM = 22;
/*      */   
/*      */ 
/*      */ 
/*      */   static final int LNXSGNBT = 128;
/*      */   
/*      */ 
/*      */ 
/*      */   static final byte LNXDIGS = 20;
/*      */   
/*      */ 
/*      */ 
/*      */   static final byte LNXEXPBS = 64;
/*      */   
/*      */ 
/*      */ 
/*      */   static final double ORANUM_FBASE = 100.0D;
/*      */   
/*      */ 
/*      */ 
/*      */   static final int LNXBASE = 100;
/*      */   
/*      */ 
/*      */ 
/*      */   static final byte IEEE_DBL_DIG = 15;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final byte IEEE_FLT_DIG = 6;
/*      */   
/*      */ 
/*      */ 
/*      */   static final int LNXEXPMX = 127;
/*      */   
/*      */ 
/*      */ 
/*      */   static final int LNXEXPMN = 0;
/*      */   
/*      */ 
/*      */ 
/*      */   static final int LNXMXOUT = 40;
/*      */   
/*      */ 
/*      */ 
/*      */   static final int LNXMXFMT = 64;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final byte BYTE_MAX_VALUE = 127;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final byte BYTE_MIN_VALUE = -128;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final short SHORT_MAX_VALUE = 32767;
/*      */   
/*      */ 
/*      */ 
/*      */   private static final short SHORT_MIN_VALUE = -32768;
/*      */   
/*      */ 
/*      */ 
/* 3120 */   private static final byte[] PI = { -63, 4, 15, 16, 93, 66, 36, 90, 80, 33, 39, 47, 27, 44, 39, 33, 80, 51, 29, 85, 21 };
/*      */   
/*      */ 
/*      */ 
/* 3124 */   private static final byte[] E = { -63, 3, 72, 83, 82, 83, 85, 60, 5, 53, 36, 37, 3, 88, 48, 14, 53, 67, 25, 98, 77 };
/*      */   
/*      */ 
/*      */ 
/* 3128 */   private static final byte[] LN10 = { -63, 3, 31, 26, 86, 10, 30, 95, 5, 57, 85, 2, 80, 92, 46, 47, 85, 37, 43, 8, 61 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static LnxLib _slnxlib;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3141 */   private static LnxLib _thinlib = null;
/*      */   
/*      */ 
/* 3144 */   private static int DBL_MAX = 40;
/*      */   
/* 3146 */   private static int INT_MAX = 15;
/* 3147 */   private static float FLOAT_MAX_INT = 2.14748365E9F;
/* 3148 */   private static float FLOAT_MIN_INT = -2.14748365E9F;
/* 3149 */   private static double DOUBLE_MAX_INT = 2.147483647E9D;
/* 3150 */   private static double DOUBLE_MIN_INT = -2.147483648E9D;
/* 3151 */   private static double DOUBLE_MAX_INT_2 = 2.147483649E9D;
/* 3152 */   private static double DOUBLE_MIN_INT_2 = -2.147483649E9D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static boolean _isInf(byte[] paramArrayOfByte)
/*      */   {
/* 3162 */     if (((paramArrayOfByte.length == 2) && (paramArrayOfByte[0] == -1) && (paramArrayOfByte[1] == 101)) || ((paramArrayOfByte[0] == 0) && (paramArrayOfByte.length == 1)))
/*      */     {
/*      */ 
/*      */ 
/* 3166 */       return true;
/*      */     }
/* 3168 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static boolean _isInt(byte[] paramArrayOfByte)
/*      */   {
/* 3178 */     if (_isZero(paramArrayOfByte)) {
/* 3179 */       return true;
/*      */     }
/* 3181 */     if (_isInf(paramArrayOfByte)) {
/* 3182 */       return false;
/*      */     }
/* 3184 */     byte[] arrayOfByte = _fromLnxFmt(paramArrayOfByte);
/* 3185 */     int i = arrayOfByte[0];
/* 3186 */     int j = (byte)(arrayOfByte.length - 1);
/*      */     
/* 3188 */     if (j > i + 1) {
/* 3189 */       return false;
/*      */     }
/* 3191 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static boolean _isNegInf(byte[] paramArrayOfByte)
/*      */   {
/* 3200 */     if ((paramArrayOfByte[0] == 0) && (paramArrayOfByte.length == 1)) {
/* 3201 */       return true;
/*      */     }
/* 3203 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static boolean _isPosInf(byte[] paramArrayOfByte)
/*      */   {
/* 3212 */     if ((paramArrayOfByte.length == 2) && (paramArrayOfByte[0] == -1) && (paramArrayOfByte[1] == 101))
/*      */     {
/*      */ 
/* 3215 */       return true;
/*      */     }
/* 3217 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static boolean _isPositive(byte[] paramArrayOfByte)
/*      */   {
/* 3226 */     if ((paramArrayOfByte[0] & 0xFFFFFF80) != 0) {
/* 3227 */       return true;
/*      */     }
/* 3229 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static boolean _isZero(byte[] paramArrayOfByte)
/*      */   {
/* 3238 */     if ((paramArrayOfByte[0] == Byte.MIN_VALUE) && (paramArrayOfByte.length == 1)) {
/* 3239 */       return true;
/*      */     }
/* 3241 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static byte[] _makePosInf()
/*      */   {
/* 3249 */     byte[] arrayOfByte = new byte[2];
/*      */     
/*      */ 
/* 3252 */     arrayOfByte[0] = -1;
/* 3253 */     arrayOfByte[1] = 101;
/*      */     
/* 3255 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static byte[] _makeNegInf()
/*      */   {
/* 3263 */     byte[] arrayOfByte = new byte[1];
/*      */     
/*      */ 
/* 3266 */     arrayOfByte[0] = 0;
/*      */     
/* 3268 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static byte[] _makeZero()
/*      */   {
/* 3276 */     byte[] arrayOfByte = new byte[1];
/*      */     
/*      */ 
/* 3279 */     arrayOfByte[0] = Byte.MIN_VALUE;
/*      */     
/* 3281 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static byte[] _fromLnxFmt(byte[] paramArrayOfByte)
/*      */   {
/* 3292 */     int j = paramArrayOfByte.length;
/*      */     
/*      */ 
/*      */     byte[] arrayOfByte;
/*      */     
/* 3297 */     if (_isPositive(paramArrayOfByte))
/*      */     {
/* 3299 */       arrayOfByte = new byte[j];
/* 3300 */       arrayOfByte[0] = ((byte)((paramArrayOfByte[0] & 0xFF7F) - 65));
/*      */       
/* 3302 */       for (i = 1; i < j; i++) {
/* 3303 */         arrayOfByte[i] = ((byte)(paramArrayOfByte[i] - 1));
/*      */       }
/*      */     }
/*      */     
/* 3307 */     if ((j - 1 == 20) && (paramArrayOfByte[(j - 1)] != 102))
/*      */     {
/* 3309 */       arrayOfByte = new byte[j];
/*      */     } else {
/* 3311 */       arrayOfByte = new byte[j - 1];
/*      */     }
/* 3313 */     arrayOfByte[0] = ((byte)(((paramArrayOfByte[0] ^ 0xFFFFFFFF) & 0xFF7F) - 65));
/*      */     
/* 3315 */     for (int i = 1; i < arrayOfByte.length; i++) {
/* 3316 */       arrayOfByte[i] = ((byte)(101 - paramArrayOfByte[i]));
/*      */     }
/*      */     
/* 3319 */     return arrayOfByte;
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
/*      */   static byte[] _toLnxFmt(byte[] paramArrayOfByte, boolean paramBoolean)
/*      */   {
/* 3335 */     int j = paramArrayOfByte.length;
/*      */     
/*      */ 
/*      */     byte[] arrayOfByte;
/*      */     
/* 3340 */     if (paramBoolean)
/*      */     {
/* 3342 */       arrayOfByte = new byte[j];
/* 3343 */       arrayOfByte[0] = ((byte)(paramArrayOfByte[0] + 128 + 64 + 1));
/*      */       
/* 3345 */       for (i = 1; i < j; i++) {
/* 3346 */         arrayOfByte[i] = ((byte)(paramArrayOfByte[i] + 1));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3352 */     if (j - 1 < 20) {
/* 3353 */       arrayOfByte = new byte[j + 1];
/*      */     } else {
/* 3355 */       arrayOfByte = new byte[j];
/*      */     }
/* 3357 */     arrayOfByte[0] = ((byte)(paramArrayOfByte[0] + 128 + 64 + 1 ^ 0xFFFFFFFF));
/*      */     
/* 3359 */     for (int i = 1; i < j; i++) {
/* 3360 */       arrayOfByte[i] = ((byte)(101 - paramArrayOfByte[i]));
/*      */     }
/* 3362 */     if (i <= 20) {
/* 3363 */       arrayOfByte[i] = 102;
/*      */     }
/*      */     
/* 3366 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static LnxLib _getLnxLib()
/*      */   {
/* 3376 */     if (_slnxlib == null)
/*      */     {
/*      */       try
/*      */       {
/*      */ 
/* 3381 */         if (System.getProperty("oracle.jserver.version") != null)
/*      */         {
/* 3383 */           _slnxlib = new LnxLibServer();
/*      */         }
/*      */         else
/*      */         {
/* 3387 */           _slnxlib = new LnxLibThin();
/*      */         }
/*      */       }
/*      */       catch (SecurityException localSecurityException)
/*      */       {
/* 3392 */         _slnxlib = new LnxLibThin();
/*      */       }
/*      */     }
/*      */     
/* 3396 */     return _slnxlib;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static LnxLib _getThinLib()
/*      */   {
/* 3405 */     if (_thinlib == null)
/*      */     {
/* 3407 */       _thinlib = new LnxLibThin();
/*      */     }
/*      */     
/* 3410 */     return _thinlib;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int _byteToChars(byte paramByte, char[] paramArrayOfChar, int paramInt)
/*      */   {
/* 3420 */     if (paramByte < 0)
/*      */     {
/* 3422 */       return 0;
/*      */     }
/* 3424 */     if (paramByte < 10)
/*      */     {
/* 3426 */       paramArrayOfChar[paramInt] = ((char)(48 + paramByte));
/* 3427 */       return 1;
/*      */     }
/* 3429 */     if (paramByte < 100)
/*      */     {
/* 3431 */       paramArrayOfChar[paramInt] = ((char)(48 + paramByte / 10));
/* 3432 */       paramArrayOfChar[(paramInt + 1)] = ((char)(48 + paramByte % 10));
/* 3433 */       return 2;
/*      */     }
/*      */     
/*      */ 
/* 3437 */     paramArrayOfChar[paramInt] = '1';
/* 3438 */     paramArrayOfChar[(paramInt + 1)] = ((char)(48 + paramByte / 10 - 10));
/* 3439 */     paramArrayOfChar[(paramInt + 2)] = ((char)(48 + paramByte % 10));
/* 3440 */     return 3;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void _byteTo2Chars(byte paramByte, char[] paramArrayOfChar, int paramInt)
/*      */   {
/* 3449 */     if (paramByte < 0)
/*      */     {
/*      */ 
/* 3452 */       paramArrayOfChar[paramInt] = '0';
/* 3453 */       paramArrayOfChar[(paramInt + 1)] = '0';
/*      */     }
/* 3455 */     else if (paramByte < 10)
/*      */     {
/* 3457 */       paramArrayOfChar[paramInt] = '0';
/* 3458 */       paramArrayOfChar[(paramInt + 1)] = ((char)(48 + paramByte));
/*      */     }
/* 3460 */     else if (paramByte < 100)
/*      */     {
/* 3462 */       paramArrayOfChar[paramInt] = ((char)(48 + paramByte / 10));
/* 3463 */       paramArrayOfChar[(paramInt + 1)] = ((char)(48 + paramByte % 10));
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 3468 */       paramArrayOfChar[paramInt] = '0';
/* 3469 */       paramArrayOfChar[(paramInt + 1)] = '0';
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void _printBytes(byte[] paramArrayOfByte)
/*      */   {
/* 3481 */     int i = paramArrayOfByte.length;
/*      */     
/* 3483 */     System.out.print(i + ": ");
/*      */     
/* 3485 */     for (int j = 0; j < i; j++)
/*      */     {
/* 3487 */       System.out.print(paramArrayOfByte[j] + " ");
/*      */     }
/*      */     
/* 3490 */     System.out.println();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private byte[] stringToBytes(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3501 */     int i = 0;
/*      */     
/* 3503 */     paramString = paramString.trim();
/*      */     
/* 3505 */     if (paramString.indexOf('.') >= 0)
/*      */     {
/*      */ 
/* 3508 */       i = paramString.length() - 1 - paramString.indexOf('.');
/*      */     }
/*      */     
/*      */ 
/* 3512 */     return toBytes(paramString, i);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 3517 */   private static Object drvType = null;
/*      */   
/*      */   static
/*      */   {
/*      */     try
/*      */     {
/* 3523 */       drvType = System.getProperty("oracle.jserver.version");
/*      */ 
/*      */     }
/*      */     catch (SecurityException localSecurityException)
/*      */     {
/* 3528 */       drvType = null;
/*      */     }
/*      */   }
/*      */   
/* 3532 */   private static String LANGID = "AMERICAN";
/*      */   static final long serialVersionUID = -1656085588913430059L;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/NUMBER.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */