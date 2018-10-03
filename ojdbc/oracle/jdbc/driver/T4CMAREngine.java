/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.IOException;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.concurrent.atomic.AtomicReference;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.net.ns.BreakNetException;
/*      */ import oracle.net.ns.Communication;
/*      */ import oracle.net.ns.NetException;
/*      */ import oracle.net.ns.NetInputStream;
/*      */ import oracle.net.ns.NetOutputStream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class T4CMAREngine
/*      */ {
/*      */   static final int TTCC_MXL = 252;
/*      */   static final int TTCC_ESC = 253;
/*      */   static final int TTCC_LNG = 254;
/*      */   static final int TTCC_ERR = 255;
/*      */   static final int TTCC_MXIN = 64;
/*      */   static final byte TTCLXMULTI = 1;
/*      */   static final byte TTCLXMCONV = 2;
/*      */   T4CTypeRep types;
/*      */   Communication net;
/*      */   DBConversion conv;
/*      */   short proSvrVer;
/*      */   NetInputStream inStream;
/*      */   NetOutputStream outStream;
/*  134 */   final byte[] ignored = new byte['ÿ'];
/*  135 */   final byte[] tmpBuffer1 = new byte[1];
/*  136 */   final byte[] tmpBuffer2 = new byte[2];
/*  137 */   final byte[] tmpBuffer3 = new byte[3];
/*  138 */   final byte[] tmpBuffer4 = new byte[4];
/*  139 */   final byte[] tmpBuffer5 = new byte[5];
/*  140 */   final byte[] tmpBuffer6 = new byte[6];
/*  141 */   final byte[] tmpBuffer7 = new byte[7];
/*  142 */   final byte[] tmpBuffer8 = new byte[8];
/*  143 */   final int[] retLen = new int[1];
/*      */   
/*      */ 
/*  146 */   AtomicReference<OracleConnection> connForException = new AtomicReference();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static String toHex(long paramLong, int paramInt)
/*      */   {
/*      */     String str;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  159 */     switch (paramInt)
/*      */     {
/*      */ 
/*      */     case 1: 
/*  163 */       str = "00" + Long.toString(paramLong & 0xFF, 16);
/*      */       
/*  165 */       break;
/*      */     
/*      */     case 2: 
/*  168 */       str = "0000" + Long.toString(paramLong & 0xFFFF, 16);
/*      */       
/*  170 */       break;
/*      */     
/*      */     case 3: 
/*  173 */       str = "000000" + Long.toString(paramLong & 0xFFFFFF, 16);
/*      */       
/*  175 */       break;
/*      */     
/*      */     case 4: 
/*  178 */       str = "00000000" + Long.toString(paramLong & 0xFFFFFFFF, 16);
/*      */       
/*  180 */       break;
/*      */     
/*      */     case 5: 
/*  183 */       str = "0000000000" + Long.toString(paramLong & 0xFFFFFFFFFF, 16);
/*      */       
/*  185 */       break;
/*      */     
/*      */     case 6: 
/*  188 */       str = "000000000000" + Long.toString(paramLong & 0xFFFFFFFFFFFF, 16);
/*      */       
/*  190 */       break;
/*      */     
/*      */     case 7: 
/*  193 */       str = "00000000000000" + Long.toString(paramLong & 0xFFFFFFFFFFFFFF, 16);
/*      */       
/*      */ 
/*  196 */       break;
/*      */     
/*      */     case 8: 
/*  199 */       return toHex(paramLong >> 32, 4) + toHex(paramLong, 4).substring(2);
/*      */     
/*      */     default: 
/*  202 */       return "more than 8 bytes";
/*      */     }
/*      */     
/*  205 */     return "0x" + str.substring(str.length() - 2 * paramInt);
/*      */   }
/*      */   
/*      */   static String toHex(byte paramByte)
/*      */   {
/*  210 */     String str = "00" + Integer.toHexString(paramByte & 0xFF);
/*  211 */     return "0x" + str.substring(str.length() - 2);
/*      */   }
/*      */   
/*      */   static String toHex(short paramShort)
/*      */   {
/*  216 */     return toHex(paramShort, 2);
/*      */   }
/*      */   
/*      */   static String toHex(int paramInt)
/*      */   {
/*  221 */     return toHex(paramInt, 4);
/*      */   }
/*      */   
/*      */   static String toHex(byte[] paramArrayOfByte, int paramInt)
/*      */   {
/*  226 */     if (paramArrayOfByte == null) {
/*  227 */       return "null";
/*      */     }
/*  229 */     if (paramInt > paramArrayOfByte.length) {
/*  230 */       return "byte array not long enough";
/*      */     }
/*  232 */     String str = "[";
/*  233 */     int i = Math.min(64, paramInt);
/*      */     
/*  235 */     for (int j = 0; j < i; j++)
/*      */     {
/*  237 */       str = str + toHex(paramArrayOfByte[j]) + " ";
/*      */     }
/*      */     
/*  240 */     if (i < paramInt) {
/*  241 */       str = str + "...";
/*      */     }
/*  243 */     return str + "]";
/*      */   }
/*      */   
/*      */   static String toHex(byte[] paramArrayOfByte)
/*      */   {
/*  248 */     if (paramArrayOfByte == null) {
/*  249 */       return "null";
/*      */     }
/*  251 */     return toHex(paramArrayOfByte, paramArrayOfByte.length);
/*      */   }
/*      */   
/*      */ 
/*      */   T4CMAREngine(Communication paramCommunication)
/*      */     throws SQLException, IOException
/*      */   {
/*  258 */     this(paramCommunication, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   T4CMAREngine(Communication paramCommunication, boolean paramBoolean)
/*      */     throws SQLException, IOException
/*      */   {
/*  269 */     if (paramCommunication == null)
/*      */     {
/*      */ 
/*      */ 
/*  273 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 433);
/*  274 */       localSQLException.fillInStackTrace();
/*  275 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  280 */     this.net = paramCommunication;
/*      */     try
/*      */     {
/*  283 */       if (paramBoolean)
/*      */       {
/*  285 */         this.outStream = paramCommunication.getNetOutputStream();
/*  286 */         this.inStream = paramCommunication.getNetInputStream();
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  291 */         this.outStream = new T4CSocketOutputStreamWrapper((NetOutputStream)paramCommunication.getOutputStream());
/*  292 */         this.inStream = new T4CSocketInputStreamWrapper((NetInputStream)paramCommunication.getInputStream(), (T4CSocketOutputStreamWrapper)this.outStream);
/*      */       }
/*      */       
/*      */     }
/*      */     catch (NetException localNetException)
/*      */     {
/*  298 */       throw new IOException(localNetException.getMessage());
/*      */     }
/*      */     
/*  301 */     this.types = new T4CTypeRep();
/*      */     
/*  303 */     this.types.setRep((byte)1, (byte)2);
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
/*      */   void initBuffers() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final void marshalSB1(byte paramByte)
/*      */     throws IOException
/*      */   {
/*      */     try
/*      */     {
/*  357 */       marshalSB2((short)paramByte);
/*      */     }
/*      */     finally {}
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
/*      */   final void marshalUB1(short paramShort)
/*      */     throws IOException
/*      */   {
/*      */     try
/*      */     {
/*  376 */       this.outStream.write((byte)(paramShort & 0xFF));
/*      */     }
/*      */     finally {}
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
/*      */   final void marshalSB2(short paramShort)
/*      */     throws IOException
/*      */   {
/*  397 */     int i = value2Buffer(paramShort, this.tmpBuffer2, (byte)1);
/*      */     
/*  399 */     if (i != 0)
/*      */     {
/*      */       try
/*      */       {
/*  403 */         this.outStream.write(this.tmpBuffer2, 0, i);
/*      */       }
/*      */       finally {}
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
/*      */   final void marshalUB2(int paramInt)
/*      */     throws IOException
/*      */   {
/*  421 */     marshalSB2((short)(paramInt & 0xFFFF));
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
/*      */   final void marshalSB4(int paramInt)
/*      */     throws IOException
/*      */   {
/*  438 */     int i = value2Buffer(paramInt, this.tmpBuffer4, (byte)2);
/*      */     
/*      */ 
/*  441 */     if (i != 0)
/*      */     {
/*      */       try
/*      */       {
/*  445 */         this.outStream.write(this.tmpBuffer4, 0, i);
/*      */       }
/*      */       finally {}
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
/*      */   final void marshalUB4(long paramLong)
/*      */     throws IOException
/*      */   {
/*  462 */     marshalSB4((int)(paramLong & 0xFFFFFFFFFFFFFFFF));
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
/*      */   final void marshalSB8(long paramLong)
/*      */     throws IOException
/*      */   {
/*  478 */     int i = value2Buffer(paramLong, this.tmpBuffer8, (byte)3);
/*      */     
/*      */ 
/*  481 */     if (i != 0)
/*      */     {
/*      */       try
/*      */       {
/*  485 */         this.outStream.write(this.tmpBuffer8, 0, i);
/*      */       }
/*      */       finally {}
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
/*      */   final void marshalSWORD(int paramInt)
/*      */     throws IOException
/*      */   {
/*  502 */     marshalSB4(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final void marshalUWORD(long paramLong)
/*      */     throws IOException
/*      */   {
/*  514 */     marshalSB4((int)(paramLong & 0xFFFFFFFFFFFFFFFF));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final void marshalB1Array(byte[] paramArrayOfByte)
/*      */     throws IOException
/*      */   {
/*  527 */     if (paramArrayOfByte.length > 0)
/*      */     {
/*      */       try
/*      */       {
/*  531 */         this.outStream.write(paramArrayOfByte);
/*      */       }
/*      */       finally {}
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
/*      */   final void marshalB1Array(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws IOException
/*      */   {
/*  552 */     if (paramArrayOfByte.length > 0)
/*      */     {
/*      */       try
/*      */       {
/*  556 */         this.outStream.write(paramArrayOfByte, paramInt1, paramInt2);
/*      */       }
/*      */       finally {}
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
/*      */   final void marshalUB4Array(long[] paramArrayOfLong)
/*      */     throws IOException
/*      */   {
/*  573 */     for (int i = 0; i < paramArrayOfLong.length; i++) {
/*  574 */       marshalSB4((int)(paramArrayOfLong[i] & 0xFFFFFFFFFFFFFFFF));
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
/*      */ 
/*      */   final void marshalO2U(boolean paramBoolean)
/*      */     throws IOException
/*      */   {
/*  616 */     if (paramBoolean) {
/*  617 */       addPtr((byte)1);
/*      */     } else {
/*  619 */       addPtr((byte)0);
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
/*      */   final void marshalNULLPTR()
/*      */     throws IOException
/*      */   {
/*  635 */     addPtr((byte)0);
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
/*      */   final void marshalPTR()
/*      */     throws IOException
/*      */   {
/*  653 */     addPtr((byte)1);
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
/*      */   final void marshalCHR(byte[] paramArrayOfByte)
/*      */     throws IOException
/*      */   {
/*  668 */     marshalCHR(paramArrayOfByte, 0, paramArrayOfByte.length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   final void marshalCHR(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws IOException
/*      */   {
/*  677 */     if (paramInt2 > 0)
/*      */     {
/*  679 */       if (this.types.isConvNeeded()) {
/*  680 */         marshalCLR(paramArrayOfByte, paramInt1, paramInt2);
/*      */       }
/*      */       else {
/*      */         try
/*      */         {
/*  685 */           this.outStream.write(paramArrayOfByte, paramInt1, paramInt2);
/*      */         }
/*      */         finally {}
/*      */       }
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
/*      */   final void marshalCLR(byte[] paramArrayOfByte, int paramInt)
/*      */     throws IOException
/*      */   {
/*  704 */     marshalCLR(paramArrayOfByte, 0, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final void marshalCLR(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws IOException
/*      */   {
/*      */     try
/*      */     {
/*  716 */       if (paramInt2 > 64)
/*      */       {
/*  718 */         int k = 0;
/*      */         
/*      */ 
/*  721 */         this.outStream.write(-2);
/*      */         
/*      */         do
/*      */         {
/*  725 */           int i = paramInt2 - k;
/*  726 */           int j = i > 64 ? 64 : i;
/*      */           
/*      */ 
/*  729 */           this.outStream.write((byte)(j & 0xFF));
/*  730 */           this.outStream.write(paramArrayOfByte, paramInt1 + k, j);
/*      */           
/*      */ 
/*  733 */           k += j;
/*      */         }
/*  735 */         while (k < paramInt2);
/*      */         
/*  737 */         this.outStream.write(0);
/*      */       }
/*      */       else
/*      */       {
/*  741 */         this.outStream.write((byte)(paramInt2 & 0xFF));
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  748 */         if (paramArrayOfByte.length != 0) {
/*  749 */           this.outStream.write(paramArrayOfByte, paramInt1, paramInt2);
/*      */         }
/*      */       }
/*      */     }
/*      */     finally {}
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
/*      */   final void marshalKEYVAL(byte[][] paramArrayOfByte1, int[] paramArrayOfInt1, byte[][] paramArrayOfByte2, int[] paramArrayOfInt2, byte[] paramArrayOfByte, int paramInt)
/*      */     throws IOException
/*      */   {
/*  767 */     for (int i = 0; i < paramInt; i++)
/*      */     {
/*  769 */       if ((paramArrayOfByte1[i] != null) && (paramArrayOfInt1[i] > 0))
/*      */       {
/*  771 */         marshalUB4(paramArrayOfInt1[i]);
/*  772 */         marshalCLR(paramArrayOfByte1[i], 0, paramArrayOfInt1[i]);
/*      */       }
/*      */       else {
/*  775 */         marshalUB4(0L);
/*      */       }
/*  777 */       if ((paramArrayOfByte2[i] != null) && (paramArrayOfInt2[i] > 0))
/*      */       {
/*  779 */         marshalUB4(paramArrayOfInt2[i]);
/*  780 */         marshalCLR(paramArrayOfByte2[i], 0, paramArrayOfInt2[i]);
/*      */       }
/*      */       else {
/*  783 */         marshalUB4(0L);
/*      */       }
/*      */       
/*  786 */       if (paramArrayOfByte[i] != 0) {
/*  787 */         marshalUB4(1L);
/*      */       } else {
/*  789 */         marshalUB4(0L);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   final void marshalKEYVAL(byte[][] paramArrayOfByte1, byte[][] paramArrayOfByte2, byte[] paramArrayOfByte, int paramInt)
/*      */     throws IOException
/*      */   {
/*  800 */     int[] arrayOfInt1 = new int[paramInt];
/*  801 */     int[] arrayOfInt2 = new int[paramInt];
/*  802 */     for (int i = 0; i < paramInt; i++)
/*      */     {
/*  804 */       if (paramArrayOfByte1[i] != null)
/*  805 */         arrayOfInt1[i] = paramArrayOfByte1[i].length;
/*  806 */       if (paramArrayOfByte2[i] != null)
/*  807 */         arrayOfInt2[i] = paramArrayOfByte2[i].length;
/*      */     }
/*  809 */     marshalKEYVAL(paramArrayOfByte1, arrayOfInt1, paramArrayOfByte2, arrayOfInt2, paramArrayOfByte, paramInt);
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
/*      */   final void marshalDALC(byte[] paramArrayOfByte)
/*      */     throws IOException
/*      */   {
/*  827 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 1))
/*      */     {
/*      */       try
/*      */       {
/*  831 */         this.outStream.write(0);
/*      */ 
/*      */       }
/*      */       finally {}
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  839 */       marshalSB4(0xFFFFFFFF & paramArrayOfByte.length);
/*  840 */       marshalCLR(paramArrayOfByte, paramArrayOfByte.length);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final void marshalKPDKV(byte[][] paramArrayOfByte1, byte[][] paramArrayOfByte2, int[] paramArrayOfInt)
/*      */     throws IOException
/*      */   {
/*  852 */     for (int i = 0; i < paramArrayOfByte1.length; i++)
/*      */     {
/*  854 */       if (paramArrayOfByte1[i] != null)
/*      */       {
/*  856 */         marshalUB4(paramArrayOfByte1[i].length);
/*  857 */         marshalCLR(paramArrayOfByte1[i], 0, paramArrayOfByte1[i].length);
/*      */       }
/*      */       else {
/*  860 */         marshalUB4(0L); }
/*  861 */       if (paramArrayOfByte2[i] != null)
/*      */       {
/*  863 */         marshalUB4(paramArrayOfByte2[i].length);
/*  864 */         marshalCLR(paramArrayOfByte2[i], 0, paramArrayOfByte2[i].length);
/*      */       }
/*      */       else {
/*  867 */         marshalUB4(0L); }
/*  868 */       marshalUB2(paramArrayOfInt[i]);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final void unmarshalKPDKV(byte[][] paramArrayOfByte1, int[] paramArrayOfInt1, byte[][] paramArrayOfByte2, int[] paramArrayOfInt2)
/*      */     throws IOException, SQLException
/*      */   {
/*  880 */     int i = 0;
/*  881 */     int[] arrayOfInt = new int[1];
/*      */     
/*  883 */     for (int j = 0; j < paramArrayOfByte1.length; j++)
/*      */     {
/*  885 */       i = (int)unmarshalUB4();
/*  886 */       if (i > 0)
/*      */       {
/*  888 */         paramArrayOfByte1[j] = new byte[i];
/*  889 */         unmarshalCLR(paramArrayOfByte1[j], 0, arrayOfInt, i);
/*  890 */         paramArrayOfInt1[j] = arrayOfInt[0];
/*      */       }
/*  892 */       i = (int)unmarshalUB4();
/*  893 */       if (i > 0)
/*      */       {
/*  895 */         paramArrayOfByte2[j] = new byte[i];
/*  896 */         unmarshalCLR(paramArrayOfByte2[j], 0, arrayOfInt, i);
/*      */       }
/*  898 */       paramArrayOfInt2[j] = unmarshalUB2();
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
/*      */   final void addPtr(byte paramByte)
/*      */     throws IOException
/*      */   {
/*      */     try
/*      */     {
/*  921 */       if ((this.types.rep[4] & 0x1) > 0) {
/*  922 */         this.outStream.write(paramByte);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*  928 */         int i = value2Buffer(paramByte, this.tmpBuffer4, (byte)4);
/*      */         
/*      */ 
/*  931 */         if (i != 0) {
/*  932 */           this.outStream.write(this.tmpBuffer4, 0, i);
/*      */         }
/*      */       }
/*      */     }
/*      */     finally {}
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
/*      */   final byte value2Buffer(int paramInt, byte[] paramArrayOfByte, byte paramByte)
/*      */     throws IOException
/*      */   {
/*  959 */     int i = (this.types.rep[paramByte] & 0x1) > 0 ? 1 : 0;
/*  960 */     int j = 1;
/*  961 */     byte b = 0;
/*      */     
/*      */ 
/*      */ 
/*  965 */     for (int k = paramArrayOfByte.length - 1; k >= 0; k--)
/*      */     {
/*  967 */       paramArrayOfByte[b] = ((byte)(paramInt >>> 8 * k & 0xFF));
/*      */       
/*      */ 
/*      */ 
/*  971 */       if (i != 0)
/*      */       {
/*  973 */         if ((j == 0) || (paramArrayOfByte[b] != 0))
/*      */         {
/*  975 */           j = 0;
/*  976 */           b = (byte)(b + 1);
/*      */         }
/*      */       }
/*      */       else {
/*  980 */         b = (byte)(b + 1);
/*      */       }
/*      */     }
/*      */     
/*  984 */     if (i != 0)
/*      */     {
/*      */       try
/*      */       {
/*  988 */         this.outStream.write(b);
/*      */       }
/*      */       finally {}
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  998 */     if ((this.types.rep[paramByte] & 0x2) > 0) {
/*  999 */       reverseArray(paramArrayOfByte, b);
/*      */     }
/* 1001 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   final byte value2Buffer(long paramLong, byte[] paramArrayOfByte, byte paramByte)
/*      */     throws IOException
/*      */   {
/* 1010 */     int i = (this.types.rep[paramByte] & 0x1) > 0 ? 1 : 0;
/* 1011 */     int j = 1;
/* 1012 */     byte b = 0;
/*      */     
/*      */ 
/*      */ 
/* 1016 */     for (int k = paramArrayOfByte.length - 1; k >= 0; k--)
/*      */     {
/* 1018 */       paramArrayOfByte[b] = ((byte)(int)(paramLong >>> 8 * k & 0xFF));
/*      */       
/*      */ 
/*      */ 
/* 1022 */       if (i != 0)
/*      */       {
/* 1024 */         if ((j == 0) || (paramArrayOfByte[b] != 0))
/*      */         {
/* 1026 */           j = 0;
/* 1027 */           b = (byte)(b + 1);
/*      */         }
/*      */       }
/*      */       else {
/* 1031 */         b = (byte)(b + 1);
/*      */       }
/*      */     }
/*      */     
/* 1035 */     if (i != 0)
/*      */     {
/*      */       try
/*      */       {
/* 1039 */         this.outStream.write(b);
/*      */       }
/*      */       finally {}
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1049 */     if ((this.types.rep[paramByte] & 0x2) > 0) {
/* 1050 */       reverseArray(paramArrayOfByte, b);
/*      */     }
/* 1052 */     return b;
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
/*      */   final void reverseArray(byte[] paramArrayOfByte, byte paramByte)
/*      */   {
/* 1067 */     int j = paramByte / 2;
/*      */     
/* 1069 */     for (int k = 0; k < j; k++)
/*      */     {
/* 1071 */       int i = paramArrayOfByte[k];
/* 1072 */       paramArrayOfByte[k] = paramArrayOfByte[(paramByte - 1 - k)];
/* 1073 */       paramArrayOfByte[(paramByte - 1 - k)] = i;
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
/*      */   final byte unmarshalSB1()
/*      */     throws SQLException, IOException
/*      */   {
/* 1113 */     byte b = (byte)unmarshalSB2();
/* 1114 */     return b;
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
/*      */   final short unmarshalUB1()
/*      */     throws SQLException, IOException
/*      */   {
/* 1132 */     short s = 0;
/*      */     
/*      */ 
/*      */     try
/*      */     {
/* 1137 */       s = (short)this.inStream.read();
/*      */     }
/*      */     catch (BreakNetException localBreakNetException) {
/* 1140 */       localBreakNetException = localBreakNetException;
/*      */       
/*      */ 
/* 1143 */       this.net.sendReset();
/* 1144 */       throw localBreakNetException;
/*      */     }
/*      */     finally {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1152 */     if (s < 0)
/*      */     {
/*      */ 
/*      */ 
/* 1156 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 410);
/* 1157 */       localSQLException.fillInStackTrace();
/* 1158 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1167 */     return s;
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
/*      */   final short unmarshalSB2()
/*      */     throws SQLException, IOException
/*      */   {
/* 1185 */     short s = (short)unmarshalUB2();
/* 1186 */     return s;
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
/*      */   final int unmarshalUB2()
/*      */     throws SQLException, IOException
/*      */   {
/* 1207 */     int i = (int)buffer2Value((byte)1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1215 */     return i & 0xFFFF;
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
/*      */   final int unmarshalUCS2(byte[] paramArrayOfByte, long paramLong)
/*      */     throws SQLException, IOException
/*      */   {
/* 1233 */     int i = unmarshalUB2();
/*      */     
/* 1235 */     this.tmpBuffer2[0] = ((byte)((i & 0xFF00) >> 8));
/* 1236 */     this.tmpBuffer2[1] = ((byte)(i & 0xFF));
/*      */     
/*      */ 
/* 1239 */     if (paramLong + 1L < paramArrayOfByte.length)
/*      */     {
/*      */ 
/*      */ 
/* 1243 */       paramArrayOfByte[((int)paramLong)] = this.tmpBuffer2[0];
/* 1244 */       paramArrayOfByte[((int)paramLong + 1)] = this.tmpBuffer2[1];
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1270 */     return this.tmpBuffer2[0] == 0 ? 2 : this.tmpBuffer2[1] == 0 ? 1 : 3;
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
/*      */   final int unmarshalSB4()
/*      */     throws SQLException, IOException
/*      */   {
/* 1288 */     int i = (int)unmarshalUB4();
/* 1289 */     return i;
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
/*      */   final long unmarshalUB4()
/*      */     throws SQLException, IOException
/*      */   {
/* 1310 */     long l = buffer2Value((byte)2);
/*      */     
/* 1312 */     return l;
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
/*      */   final int unmarshalSB4(byte[] paramArrayOfByte)
/*      */     throws SQLException, IOException
/*      */   {
/* 1335 */     long l = buffer2Value((byte)2, new ByteArrayInputStream(paramArrayOfByte));
/*      */     
/* 1337 */     return (int)l;
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
/*      */   final long unmarshalSB8()
/*      */     throws SQLException, IOException
/*      */   {
/* 1358 */     long l = buffer2Value((byte)3);
/* 1359 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final int unmarshalRefCursor(byte[] paramArrayOfByte)
/*      */     throws SQLException, IOException
/*      */   {
/* 1369 */     int i = unmarshalSB4(paramArrayOfByte);
/* 1370 */     return i;
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
/*      */   int unmarshalSWORD()
/*      */     throws SQLException, IOException
/*      */   {
/* 1390 */     int i = (int)unmarshalUB4();
/* 1391 */     return i;
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
/*      */   long unmarshalUWORD()
/*      */     throws SQLException, IOException
/*      */   {
/* 1410 */     long l = unmarshalUB4();
/* 1411 */     return l;
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
/*      */   byte[] unmarshalNBytes(int paramInt)
/*      */     throws SQLException, IOException
/*      */   {
/* 1428 */     byte[] arrayOfByte = new byte[paramInt];
/*      */     
/* 1430 */     if (paramInt > 0)
/*      */     {
/*      */       try
/*      */       {
/*      */ 
/* 1435 */         if (this.inStream.read(arrayOfByte) < 0)
/*      */         {
/*      */ 
/*      */ 
/* 1439 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 410);
/* 1440 */           localSQLException.fillInStackTrace();
/* 1441 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */       catch (BreakNetException localBreakNetException)
/*      */       {
/* 1447 */         localBreakNetException = localBreakNetException;
/*      */         
/*      */ 
/* 1450 */         this.net.sendReset();
/* 1451 */         throw localBreakNetException;
/*      */       }
/*      */       finally {}
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1458 */     return arrayOfByte;
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
/*      */   int unmarshalNBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException, IOException
/*      */   {
/* 1477 */     if (paramInt1 + paramInt2 > paramArrayOfByte.length)
/*      */     {
/* 1479 */       paramInt2 = paramArrayOfByte.length - paramInt1;
/*      */     }
/*      */     
/* 1482 */     int i = 0;
/*      */     
/* 1484 */     while (i < paramInt2) {
/* 1485 */       i += getNBytes(paramArrayOfByte, paramInt1 + i, paramInt2 - i);
/*      */     }
/* 1487 */     return i;
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
/*      */   int getNBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException, IOException
/*      */   {
/* 1506 */     if (paramInt1 + paramInt2 > paramArrayOfByte.length)
/*      */     {
/* 1508 */       paramInt2 = paramArrayOfByte.length - paramInt1;
/*      */     }
/*      */     
/* 1511 */     int i = 0;
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/* 1517 */       if ((i = this.inStream.read(paramArrayOfByte, paramInt1, paramInt2)) < 0)
/*      */       {
/*      */ 
/*      */ 
/* 1521 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 410);
/* 1522 */         localSQLException.fillInStackTrace();
/* 1523 */         throw localSQLException;
/*      */       }
/*      */       
/*      */     }
/*      */     catch (BreakNetException localBreakNetException)
/*      */     {
/* 1529 */       localBreakNetException = localBreakNetException;
/*      */       
/*      */ 
/*      */ 
/* 1533 */       this.net.sendReset();
/* 1534 */       throw localBreakNetException;
/*      */     }
/*      */     finally {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1541 */     return i;
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
/*      */   byte[] getNBytes(int paramInt)
/*      */     throws SQLException, IOException
/*      */   {
/* 1557 */     byte[] arrayOfByte = new byte[paramInt];
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/* 1563 */       if (this.inStream.read(arrayOfByte) < 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 1568 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 410);
/* 1569 */         localSQLException.fillInStackTrace();
/* 1570 */         throw localSQLException;
/*      */       }
/*      */       
/*      */     }
/*      */     catch (BreakNetException localBreakNetException)
/*      */     {
/* 1576 */       localBreakNetException = localBreakNetException;
/*      */       
/*      */ 
/*      */ 
/* 1580 */       this.net.sendReset();
/* 1581 */       throw localBreakNetException;
/*      */     }
/*      */     finally {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1588 */     return arrayOfByte;
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
/*      */   byte[] unmarshalTEXT(int paramInt)
/*      */     throws SQLException, IOException
/*      */   {
/* 1609 */     int i = 0;
/*      */     
/*      */ 
/*      */ 
/* 1613 */     byte[] arrayOfByte1 = new byte[paramInt];
/*      */     
/*      */ 
/* 1616 */     while (i < paramInt)
/*      */     {
/*      */ 
/*      */       try
/*      */       {
/*      */ 
/* 1622 */         if (this.inStream.read(arrayOfByte1, i, 1) < 0)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/* 1627 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 410);
/* 1628 */           localSQLException.fillInStackTrace();
/* 1629 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */       catch (BreakNetException localBreakNetException)
/*      */       {
/* 1635 */         localBreakNetException = localBreakNetException;
/*      */         
/*      */ 
/*      */ 
/* 1639 */         this.net.sendReset();
/* 1640 */         throw localBreakNetException;
/*      */       }
/*      */       finally {}
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1647 */       if (arrayOfByte1[(i++)] == 0) {
/*      */         break;
/*      */       }
/*      */     }
/*      */     byte[] arrayOfByte2;
/* 1652 */     if (arrayOfByte1.length == --i)
/*      */     {
/* 1654 */       arrayOfByte2 = arrayOfByte1;
/*      */     }
/*      */     else
/*      */     {
/* 1658 */       arrayOfByte2 = new byte[i];
/*      */       
/* 1660 */       System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, i);
/*      */     }
/*      */     
/* 1663 */     return arrayOfByte2;
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
/*      */   byte[] unmarshalCHR(int paramInt)
/*      */     throws SQLException, IOException
/*      */   {
/* 1682 */     Object localObject = null;
/*      */     
/* 1684 */     if (this.types.isConvNeeded())
/*      */     {
/* 1686 */       localObject = unmarshalCLR(paramInt, this.retLen);
/*      */       
/* 1688 */       if (localObject.length != this.retLen[0])
/*      */       {
/* 1690 */         byte[] arrayOfByte = new byte[this.retLen[0]];
/*      */         
/* 1692 */         System.arraycopy(localObject, 0, arrayOfByte, 0, this.retLen[0]);
/*      */         
/* 1694 */         localObject = arrayOfByte;
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 1699 */       localObject = getNBytes(paramInt);
/*      */     }
/*      */     
/* 1702 */     return (byte[])localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void unmarshalCLR(byte[] paramArrayOfByte, int paramInt, int[] paramArrayOfInt)
/*      */     throws SQLException, IOException
/*      */   {
/* 1711 */     unmarshalCLR(paramArrayOfByte, paramInt, paramArrayOfInt, Integer.MAX_VALUE);
/*      */   }
/*      */   
/*      */ 
/*      */   void unmarshalCLR(byte[] paramArrayOfByte, int paramInt1, int[] paramArrayOfInt, int paramInt2)
/*      */     throws SQLException, IOException
/*      */   {
/* 1718 */     unmarshalCLR(paramArrayOfByte, paramInt1, paramArrayOfInt, paramInt2, 0);
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
/*      */   void unmarshalCLR(byte[] paramArrayOfByte, int paramInt1, int[] paramArrayOfInt, int paramInt2, int paramInt3)
/*      */     throws SQLException, IOException
/*      */   {
/* 1741 */     int i = 0;
/* 1742 */     int j = 0;
/* 1743 */     int k = paramInt1;
/* 1744 */     int m = 0;
/* 1745 */     int n = 0;
/* 1746 */     int i1 = 0;
/* 1747 */     int i2 = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1753 */     int i3 = -1;
/*      */     
/* 1755 */     i = unmarshalUB1();
/*      */     
/*      */ 
/* 1758 */     if (i < 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1763 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 1764 */       localSQLException.fillInStackTrace();
/* 1765 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1769 */     if (i == 0)
/*      */     {
/* 1771 */       paramArrayOfInt[0] = 0;
/* 1772 */       return;
/*      */     }
/*      */     
/* 1775 */     if (escapeSequenceNull(i))
/*      */     {
/* 1777 */       paramArrayOfInt[0] = 0; return;
/*      */     }
/*      */     
/*      */     int i4;
/* 1781 */     if (i != 254)
/*      */     {
/* 1783 */       if (paramInt3 - i2 >= i)
/*      */       {
/*      */ 
/* 1786 */         unmarshalBuffer(this.ignored, 0, i);
/* 1787 */         i2 += i;
/*      */         
/* 1789 */         i = 0;
/*      */       }
/* 1791 */       else if (paramInt3 - i2 > 0)
/*      */       {
/*      */ 
/*      */ 
/* 1795 */         unmarshalBuffer(this.ignored, 0, paramInt3 - i2);
/*      */         
/* 1797 */         i -= paramInt3 - i2;
/* 1798 */         i2 += paramInt3 - i2;
/*      */       }
/*      */       
/* 1801 */       if (i > 0)
/*      */       {
/*      */ 
/* 1804 */         i1 = Math.min(paramInt2 - n, i);
/* 1805 */         k = unmarshalBuffer(paramArrayOfByte, k, i1);
/* 1806 */         n += i1;
/*      */         
/*      */ 
/* 1809 */         i4 = i - i1;
/*      */         
/* 1811 */         if (i4 > 0) {
/* 1812 */           unmarshalBuffer(this.ignored, 0, i4);
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/* 1817 */       i3 = -1;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       for (;;)
/*      */       {
/* 1824 */         if (i3 != -1)
/*      */         {
/* 1826 */           i = unmarshalUB1();
/*      */           
/*      */ 
/* 1829 */           if (i <= 0) {
/*      */             break;
/*      */           }
/*      */         }
/* 1833 */         if (i == 254)
/*      */         {
/* 1835 */           switch (i3)
/*      */           {
/*      */ 
/*      */           case -1: 
/* 1839 */             i3 = 1;
/*      */             
/* 1841 */             break;
/*      */           
/*      */           case 1: 
/* 1844 */             i3 = 0;
/*      */             
/* 1846 */             break;
/*      */           
/*      */           case 0: 
/* 1849 */             if (m != 0)
/*      */             {
/* 1851 */               i3 = 0;
/*      */ 
/*      */             }
/*      */             else
/*      */             {
/*      */ 
/* 1857 */               i3 = 0;
/*      */             }
/* 1859 */             break;
/*      */           }
/*      */           
/*      */         } else {
/* 1863 */           if (k == -1)
/*      */           {
/* 1865 */             unmarshalBuffer(this.ignored, 0, i);
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/* 1870 */             j = i;
/* 1871 */             if (paramInt3 - i2 >= j)
/*      */             {
/*      */ 
/* 1874 */               unmarshalBuffer(this.ignored, 0, j);
/* 1875 */               i2 += j;
/*      */               
/* 1877 */               j = 0;
/*      */             }
/* 1879 */             else if (paramInt3 - i2 > 0)
/*      */             {
/*      */ 
/*      */ 
/* 1883 */               unmarshalBuffer(this.ignored, 0, paramInt3 - i2);
/*      */               
/* 1885 */               j -= paramInt3 - i2;
/* 1886 */               i2 += paramInt3 - i2;
/*      */             }
/*      */             
/* 1889 */             if (j > 0)
/*      */             {
/*      */ 
/* 1892 */               i1 = Math.min(paramInt2 - n, j);
/* 1893 */               k = unmarshalBuffer(paramArrayOfByte, k, i1);
/* 1894 */               n += i1;
/*      */               
/*      */ 
/* 1897 */               i4 = j - i1;
/*      */               
/* 1899 */               if (i4 > 0) {
/* 1900 */                 unmarshalBuffer(this.ignored, 0, i4);
/*      */               }
/*      */             }
/*      */           }
/*      */           
/* 1905 */           i3 = 0;
/*      */           
/* 1907 */           if (i > 252) {
/* 1908 */             m = 1;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1914 */     if (paramArrayOfInt != null)
/*      */     {
/* 1916 */       if (k != -1) {
/* 1917 */         paramArrayOfInt[0] = n;
/*      */       }
/*      */       else {
/* 1920 */         paramArrayOfInt[0] = (paramArrayOfByte.length - paramInt1);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final byte[] unmarshalCLR(int paramInt, int[] paramArrayOfInt)
/*      */     throws SQLException, IOException
/*      */   {
/* 1934 */     byte[] arrayOfByte = new byte[paramInt * this.conv.c2sNlsRatio];
/*      */     
/* 1936 */     unmarshalCLR(arrayOfByte, 0, paramArrayOfInt, paramInt);
/* 1937 */     return arrayOfByte;
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
/*      */   final int[] unmarshalKEYVAL(byte[][] paramArrayOfByte1, byte[][] paramArrayOfByte2, int paramInt)
/*      */     throws SQLException, IOException
/*      */   {
/* 1979 */     byte[] arrayOfByte = new byte['Ϩ'];
/* 1980 */     int[] arrayOfInt1 = new int[1];
/* 1981 */     int[] arrayOfInt2 = new int[paramInt];
/*      */     
/*      */ 
/* 1984 */     for (int j = 0; j < paramInt; j++)
/*      */     {
/* 1986 */       int i = unmarshalSB4();
/*      */       
/* 1988 */       if (i > 0)
/*      */       {
/* 1990 */         unmarshalCLR(arrayOfByte, 0, arrayOfInt1);
/*      */         
/* 1992 */         paramArrayOfByte1[j] = new byte[arrayOfInt1[0]];
/*      */         
/* 1994 */         System.arraycopy(arrayOfByte, 0, paramArrayOfByte1[j], 0, arrayOfInt1[0]);
/*      */       }
/*      */       
/* 1997 */       i = unmarshalSB4();
/*      */       
/* 1999 */       if (i > 0)
/*      */       {
/* 2001 */         unmarshalCLR(arrayOfByte, 0, arrayOfInt1);
/*      */         
/* 2003 */         paramArrayOfByte2[j] = new byte[arrayOfInt1[0]];
/*      */         
/* 2005 */         System.arraycopy(arrayOfByte, 0, paramArrayOfByte2[j], 0, arrayOfInt1[0]);
/*      */       }
/*      */       
/* 2008 */       arrayOfInt2[j] = unmarshalSB4();
/*      */     }
/*      */     
/* 2011 */     arrayOfByte = null;
/*      */     
/*      */ 
/* 2014 */     return arrayOfInt2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   final int unmarshalBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException, IOException
/*      */   {
/* 2023 */     if (paramInt2 <= 0) {
/* 2024 */       return paramInt1;
/*      */     }
/* 2026 */     if (paramArrayOfByte.length < paramInt1 + paramInt2)
/*      */     {
/* 2028 */       unmarshalNBytes(paramArrayOfByte, paramInt1, paramArrayOfByte.length - paramInt1);
/*      */       
/*      */ 
/* 2031 */       unmarshalNBytes(this.ignored, 0, paramInt1 + paramInt2 - paramArrayOfByte.length);
/*      */       
/* 2033 */       paramInt1 = -1;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 2039 */       unmarshalNBytes(paramArrayOfByte, paramInt1, paramInt2);
/*      */       
/* 2041 */       paramInt1 += paramInt2;
/*      */     }
/* 2043 */     return paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 2048 */   ArrayList refVector = null;
/*      */   
/*      */   final byte[] unmarshalCLRforREFS()
/*      */     throws SQLException, IOException
/*      */   {
/* 2053 */     int i = 0;
/* 2054 */     int j = 0;
/* 2055 */     byte[] arrayOfByte1 = null;
/*      */     
/*      */ 
/* 2058 */     int k = unmarshalUB1();
/*      */     
/*      */ 
/*      */ 
/* 2062 */     if (k < 0)
/*      */     {
/*      */ 
/*      */ 
/* 2066 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 2067 */       localSQLException.fillInStackTrace();
/* 2068 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 2072 */     if (k == 0)
/*      */     {
/* 2074 */       return null;
/*      */     }
/*      */     
/*      */ 
/* 2078 */     boolean bool = escapeSequenceNull(k);
/* 2079 */     if (!bool)
/*      */     {
/* 2081 */       if (this.refVector == null) {
/* 2082 */         this.refVector = new ArrayList(10);
/*      */       } else {
/* 2084 */         this.refVector.clear();
/*      */       }
/*      */     }
/* 2087 */     if (!bool)
/*      */     {
/* 2089 */       if (k == 254)
/*      */       {
/*      */ 
/*      */ 
/* 2093 */         while ((i = unmarshalUB1()) > 0)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2099 */           if ((i != 254) || 
/*      */           
/* 2101 */             (!this.types.isServerConversion()))
/*      */           {
/*      */ 
/*      */ 
/* 2105 */             j = (short)(j + i);
/* 2106 */             arrayOfByte2 = new byte[i];
/*      */             
/* 2108 */             unmarshalBuffer(arrayOfByte2, 0, i);
/* 2109 */             this.refVector.add(arrayOfByte2);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 2114 */       j = k;
/*      */       
/* 2116 */       byte[] arrayOfByte2 = new byte[k];
/*      */       
/* 2118 */       unmarshalBuffer(arrayOfByte2, 0, k);
/* 2119 */       this.refVector.add(arrayOfByte2);
/*      */       
/*      */ 
/*      */ 
/* 2123 */       arrayOfByte1 = new byte[j];
/*      */       
/* 2125 */       int m = 0;
/*      */       
/* 2127 */       while (this.refVector.size() > 0)
/*      */       {
/* 2129 */         int n = ((byte[])this.refVector.get(0)).length;
/*      */         
/* 2131 */         System.arraycopy(this.refVector.get(0), 0, arrayOfByte1, m, n);
/*      */         
/*      */ 
/* 2134 */         m += n;
/*      */         
/*      */ 
/*      */ 
/* 2138 */         this.refVector.remove(0);
/*      */       }
/*      */       
/*      */     }
/*      */     else
/*      */     {
/* 2144 */       arrayOfByte1 = null;
/*      */     }
/* 2146 */     return arrayOfByte1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final boolean escapeSequenceNull(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2159 */     boolean bool = false;
/*      */     
/* 2161 */     switch (paramInt)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */     case 0: 
/* 2167 */       bool = true;
/*      */       
/* 2169 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 253: 
/* 2174 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 2175 */       localSQLException.fillInStackTrace();
/* 2176 */       throw localSQLException;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 255: 
/* 2186 */       bool = true;
/* 2187 */       break;
/*      */     case 254: 
/*      */       break;
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
/*      */ 
/*      */ 
/*      */ 
/* 2204 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final int processIndicator(boolean paramBoolean, int paramInt)
/*      */     throws SQLException, IOException
/*      */   {
/* 2217 */     int i = unmarshalSB2();
/* 2218 */     int j = 0;
/*      */     
/* 2220 */     if (!paramBoolean)
/*      */     {
/* 2222 */       if (i == 0) {
/* 2223 */         j = paramInt;
/* 2224 */       } else if ((i == -2) || (i > 0)) {
/* 2225 */         j = i;
/*      */       }
/*      */       else
/*      */       {
/* 2229 */         j = 65536 + i; }
/*      */     }
/* 2231 */     return j;
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
/*      */   final long unmarshalDALC(byte[] paramArrayOfByte, int paramInt, int[] paramArrayOfInt)
/*      */     throws SQLException, IOException
/*      */   {
/* 2262 */     long l = unmarshalUB4();
/*      */     
/* 2264 */     if (l > 0L)
/* 2265 */       unmarshalCLR(paramArrayOfByte, paramInt, paramArrayOfInt);
/* 2266 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   final byte[] unmarshalDALC()
/*      */     throws SQLException, IOException
/*      */   {
/* 2274 */     long l = unmarshalUB4();
/* 2275 */     byte[] arrayOfByte = new byte[(int)(0xFFFFFFFFFFFFFFFF & l)];
/*      */     
/* 2277 */     if (arrayOfByte.length > 0)
/*      */     {
/* 2279 */       arrayOfByte = unmarshalCLR(arrayOfByte.length, this.retLen);
/*      */       
/* 2281 */       if (arrayOfByte == null)
/*      */       {
/*      */ 
/*      */ 
/* 2285 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 2286 */         localSQLException.fillInStackTrace();
/* 2287 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 2292 */       arrayOfByte = new byte[0]; }
/* 2293 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   final byte[] unmarshalDALC(int[] paramArrayOfInt)
/*      */     throws SQLException, IOException
/*      */   {
/* 2301 */     long l = unmarshalUB4();
/* 2302 */     byte[] arrayOfByte = new byte[(int)(0xFFFFFFFFFFFFFFFF & l)];
/*      */     
/*      */ 
/* 2305 */     if (arrayOfByte.length > 0)
/*      */     {
/* 2307 */       arrayOfByte = unmarshalCLR(arrayOfByte.length, paramArrayOfInt);
/*      */       
/* 2309 */       if (arrayOfByte == null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 2314 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 2315 */         localSQLException.fillInStackTrace();
/* 2316 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 2321 */       arrayOfByte = new byte[0]; }
/* 2322 */     return arrayOfByte;
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
/*      */   final long buffer2Value(byte paramByte)
/*      */     throws SQLException, IOException
/*      */   {
/* 2350 */     long l = 0L;
/* 2351 */     int i = 1;
/*      */     
/*      */     try
/*      */     {
/* 2355 */       if ((this.types.rep[paramByte] & 0x1) > 0)
/*      */       {
/* 2357 */         i = this.inStream.readB1();
/*      */       }
/*      */       else {
/* 2360 */         switch (paramByte)
/*      */         {
/*      */         case 1: 
/* 2363 */           i = 2;
/* 2364 */           break;
/*      */         case 2: 
/* 2366 */           i = 4;
/* 2367 */           break;
/*      */         case 3: 
/* 2369 */           i = 8;
/*      */         }
/*      */         
/*      */       }
/*      */       
/* 2374 */       if ((this.types.rep[paramByte] & 0x2) > 0) {
/* 2375 */         l = this.inStream.readLongLSB(i);
/*      */       }
/* 2377 */       return this.inStream.readLongMSB(i);
/*      */ 
/*      */ 
/*      */     }
/*      */     catch (BreakNetException localBreakNetException)
/*      */     {
/*      */ 
/* 2384 */       this.net.sendReset();
/* 2385 */       throw localBreakNetException;
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
/*      */   final long buffer2Value(byte paramByte, ByteArrayInputStream paramByteArrayInputStream)
/*      */     throws SQLException, IOException
/*      */   {
/* 2410 */     int j = 0;
/*      */     
/* 2412 */     long l = 0L;
/* 2413 */     int k = 0;
/*      */     
/*      */     SQLException localSQLException;
/* 2416 */     if ((this.types.rep[paramByte] & 0x1) > 0)
/*      */     {
/* 2418 */       j = paramByteArrayInputStream.read();
/*      */       
/*      */ 
/* 2421 */       if ((j & 0x80) > 0)
/*      */       {
/* 2423 */         j &= 0x7F;
/* 2424 */         k = 1;
/*      */       }
/*      */       
/* 2427 */       if (j < 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 2432 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 410);
/* 2433 */         localSQLException.fillInStackTrace();
/* 2434 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2441 */       if (j == 0)
/*      */       {
/*      */ 
/* 2444 */         return 0L;
/*      */       }
/*      */       
/*      */ 
/* 2448 */       if (((paramByte == 1) && (j > 2)) || ((paramByte == 2) && (j > 4)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2454 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 412);
/* 2455 */         localSQLException.fillInStackTrace();
/* 2456 */         throw localSQLException;
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */     }
/* 2463 */     else if (paramByte == 1) {
/* 2464 */       j = 2;
/* 2465 */     } else if (paramByte == 2) {
/* 2466 */       j = 4;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2474 */     byte[] arrayOfByte = new byte[j];
/*      */     
/* 2476 */     if (paramByteArrayInputStream.read(arrayOfByte) < 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2481 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 410);
/* 2482 */       localSQLException.fillInStackTrace();
/* 2483 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 2487 */     for (int m = 0; m < arrayOfByte.length; m++)
/*      */     {
/*      */       int i;
/* 2490 */       if ((this.types.rep[paramByte] & 0x2) > 0) {
/* 2491 */         i = (short)(arrayOfByte[(arrayOfByte.length - 1 - m)] & 0xFF);
/*      */       } else {
/* 2493 */         i = (short)(arrayOfByte[m] & 0xFF);
/*      */       }
/* 2495 */       l |= i << 8 * (arrayOfByte.length - 1 - m);
/*      */     }
/*      */     
/*      */ 
/* 2499 */     l &= 0xFFFFFFFFFFFFFFFF;
/*      */     
/* 2501 */     if (k != 0) {
/* 2502 */       l = -l;
/*      */     }
/* 2504 */     return l;
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
/*      */   protected OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 2519 */     return (OracleConnection)this.connForException.get();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void setConnectionDuringExceptionHandling(OracleConnection paramOracleConnection)
/*      */   {
/* 2530 */     this.connForException.set(paramOracleConnection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 2535 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CMAREngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */