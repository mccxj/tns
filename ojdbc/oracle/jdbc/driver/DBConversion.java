/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.sql.SQLException;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.util.RepConversion;
/*      */ import oracle.sql.CharacterSet;
/*      */ import oracle.sql.converter.CharacterSetMetaData;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DBConversion
/*      */ {
/*      */   public static final boolean DO_CONVERSION_WITH_REPLACEMENT = true;
/*      */   public static final short ORACLE8_PROD_VERSION = 8030;
/*      */   protected short serverNCharSetId;
/*      */   protected short serverCharSetId;
/*      */   protected short clientCharSetId;
/*      */   protected CharacterSet serverCharSet;
/*      */   protected CharacterSet serverNCharSet;
/*      */   protected CharacterSet clientCharSet;
/*      */   protected CharacterSet asciiCharSet;
/*      */   protected boolean isServerCharSetFixedWidth;
/*      */   protected boolean isServerNCharSetFixedWidth;
/*      */   protected int c2sNlsRatio;
/*      */   protected int s2cNlsRatio;
/*      */   protected int sMaxCharSize;
/*      */   protected int cMaxCharSize;
/*      */   protected int maxNCharSize;
/*      */   protected boolean isServerCSMultiByte;
/*   79 */   private boolean isStrictASCIIConversion = false;
/*   80 */   private boolean isQuickASCIIConversion = false;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final short DBCS_CHARSET = -1;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final short UCS2_CHARSET = -5;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final short ASCII_CHARSET = 1;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final short ISO_LATIN_1_CHARSET = 31;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final short AL24UTFFSS_CHARSET = 870;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final short UTF8_CHARSET = 871;
/*      */   
/*      */ 
/*      */   public static final short AL32UTF8_CHARSET = 873;
/*      */   
/*      */ 
/*      */   public static final short AL16UTF16_CHARSET = 2000;
/*      */   
/*      */ 
/*      */ 
/*      */   public DBConversion(short paramShort1, short paramShort2, short paramShort3, boolean paramBoolean1, boolean paramBoolean2)
/*      */     throws SQLException
/*      */   {
/*  117 */     this.isStrictASCIIConversion = paramBoolean1;
/*  118 */     this.isQuickASCIIConversion = paramBoolean2;
/*      */     
/*  120 */     if (paramShort2 != -1)
/*      */     {
/*  122 */       init(paramShort1, paramShort2, paramShort3);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public DBConversion(short paramShort1, short paramShort2, short paramShort3)
/*      */     throws SQLException
/*      */   {
/*  131 */     this(paramShort1, paramShort2, paramShort3, false, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void init(short paramShort1, short paramShort2, short paramShort3)
/*      */     throws SQLException
/*      */   {
/*  142 */     switch (paramShort2)
/*      */     {
/*      */     case -5: 
/*      */     case 1: 
/*      */     case 2: 
/*      */     case 31: 
/*      */     case 178: 
/*      */     case 870: 
/*      */     case 871: 
/*      */     case 873: 
/*      */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     default: 
/*  164 */       unexpectedCharset(paramShort2);
/*      */     }
/*      */     
/*      */     
/*      */ 
/*  169 */     this.serverCharSetId = paramShort1;
/*  170 */     this.clientCharSetId = paramShort2;
/*  171 */     this.serverCharSet = CharacterSet.make(this.serverCharSetId);
/*      */     
/*  173 */     this.serverNCharSetId = paramShort3;
/*  174 */     this.serverNCharSet = CharacterSet.make(this.serverNCharSetId);
/*      */     
/*  176 */     this.clientCharSet = CharacterSet.make(this.clientCharSetId);
/*      */     
/*  178 */     this.c2sNlsRatio = CharacterSetMetaData.getRatio(paramShort1, paramShort2);
/*  179 */     this.s2cNlsRatio = CharacterSetMetaData.getRatio(paramShort2, paramShort1);
/*  180 */     this.sMaxCharSize = CharacterSetMetaData.getRatio(paramShort1, 1);
/*  181 */     this.cMaxCharSize = CharacterSetMetaData.getRatio(paramShort2, 1);
/*  182 */     this.maxNCharSize = CharacterSetMetaData.getRatio(paramShort3, 1);
/*      */     
/*  184 */     findFixedWidthInfo();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void findFixedWidthInfo()
/*      */     throws SQLException
/*      */   {
/*  195 */     this.isServerCharSetFixedWidth = CharacterSetMetaData.isFixedWidth(this.serverCharSetId);
/*  196 */     this.isServerNCharSetFixedWidth = CharacterSetMetaData.isFixedWidth(this.serverNCharSetId);
/*  197 */     this.isServerCSMultiByte = (this.sMaxCharSize > 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short getServerCharSetId()
/*      */   {
/*  209 */     return this.serverCharSetId;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short getNCharSetId()
/*      */   {
/*  221 */     return this.serverNCharSetId;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean IsNCharFixedWith()
/*      */   {
/*  230 */     return this.serverNCharSetId == 2000;
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
/*      */   public short getClientCharSet()
/*      */   {
/*  245 */     if (this.clientCharSetId == -1) {
/*  246 */       return this.serverCharSetId;
/*      */     }
/*  248 */     return this.clientCharSetId;
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
/*      */   public CharacterSet getDbCharSetObj()
/*      */   {
/*  262 */     return this.serverCharSet;
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
/*      */   public CharacterSet getDriverCharSetObj()
/*      */   {
/*  277 */     return this.clientCharSet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public CharacterSet getDriverNCharSetObj()
/*      */   {
/*  285 */     return this.serverNCharSet;
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
/*      */   public static final short findDriverCharSet(short paramShort1, short paramShort2)
/*      */   {
/*  348 */     short s = 0;
/*      */     
/*  350 */     switch (paramShort1)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 1: 
/*      */     case 2: 
/*      */     case 31: 
/*      */     case 178: 
/*      */     case 873: 
/*  364 */       s = paramShort1;
/*      */       
/*  366 */       break;
/*      */     
/*      */     default: 
/*  369 */       s = paramShort2 >= 8030 ? 871 : 870;
/*      */     }
/*      */     
/*      */     
/*      */ 
/*      */ 
/*  375 */     return s;
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
/*      */   public static final byte[] stringToDriverCharBytes(String paramString, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  410 */     if (paramString == null)
/*      */     {
/*  412 */       return null;
/*      */     }
/*      */     
/*  415 */     byte[] arrayOfByte = null;
/*      */     
/*  417 */     switch (paramShort)
/*      */     {
/*      */ 
/*      */ 
/*      */     case -5: 
/*      */     case 2000: 
/*  423 */       arrayOfByte = CharacterSet.stringToAL16UTF16Bytes(paramString);
/*      */       
/*      */ 
/*  426 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 1: 
/*      */     case 2: 
/*  433 */       arrayOfByte = CharacterSet.stringToASCII(paramString);
/*      */       
/*      */ 
/*  436 */       break;
/*      */     
/*      */ 
/*      */     case 870: 
/*      */     case 871: 
/*  441 */       arrayOfByte = CharacterSet.stringToUTF(paramString);
/*      */       
/*      */ 
/*  444 */       break;
/*      */     
/*      */     case 873: 
/*  447 */       arrayOfByte = CharacterSet.stringToAL32UTF8(paramString);
/*      */       
/*      */ 
/*  450 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case -1: 
/*      */     default: 
/*  456 */       unexpectedCharset(paramShort);
/*      */     }
/*      */     
/*      */     
/*      */ 
/*  461 */     return arrayOfByte;
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
/*      */   public byte[] StringToCharBytes(String paramString)
/*      */     throws SQLException
/*      */   {
/*  483 */     if (paramString.length() == 0) {
/*  484 */       return null;
/*      */     }
/*  486 */     switch (this.clientCharSetId)
/*      */     {
/*      */ 
/*      */     case -1: 
/*  490 */       return this.serverCharSet.convertWithReplacement(paramString);
/*      */     
/*      */     case 2: 
/*      */     case 31: 
/*      */     case 178: 
/*  495 */       return this.clientCharSet.convertWithReplacement(paramString);
/*      */     
/*      */     case 1: 
/*  498 */       if (this.isQuickASCIIConversion)
/*      */       {
/*  500 */         byte[] arrayOfByte = new byte[paramString.length()];
/*  501 */         CharacterSet.convertJavaCharsToASCIIBytes(paramString.toCharArray(), 0, arrayOfByte, 0, paramString.length(), false);
/*  502 */         return arrayOfByte;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */       break;
/*      */     }
/*      */     
/*      */     
/*      */ 
/*  512 */     return stringToDriverCharBytes(paramString, this.clientCharSetId);
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
/*      */   public String CharBytesToString(byte[] paramArrayOfByte, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  562 */     return CharBytesToString(paramArrayOfByte, paramInt, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String CharBytesToString(byte[] paramArrayOfByte, int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  571 */     String str = null;
/*  572 */     if (paramArrayOfByte.length == 0) {
/*  573 */       return str;
/*      */     }
/*  575 */     switch (this.clientCharSetId)
/*      */     {
/*      */ 
/*      */     case -5: 
/*  579 */       str = CharacterSet.AL16UTF16BytesToString(paramArrayOfByte, paramInt);
/*      */       
/*      */ 
/*  582 */       break;
/*      */     
/*      */ 
/*      */     case 1: 
/*  586 */       str = new String(paramArrayOfByte, 0, 0, paramInt);
/*      */       
/*  588 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 2: 
/*      */     case 31: 
/*      */     case 178: 
/*  596 */       if (paramBoolean) {
/*  597 */         str = this.clientCharSet.toStringWithReplacement(paramArrayOfByte, 0, paramInt);
/*      */       } else {
/*  599 */         str = this.clientCharSet.toString(paramArrayOfByte, 0, paramInt);
/*      */       }
/*  601 */       break;
/*      */     
/*      */ 
/*      */     case 870: 
/*      */     case 871: 
/*  606 */       str = CharacterSet.UTFToString(paramArrayOfByte, 0, paramInt, paramBoolean);
/*      */       
/*      */ 
/*  609 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 873: 
/*  615 */       str = CharacterSet.AL32UTF8ToString(paramArrayOfByte, 0, paramInt, paramBoolean);
/*      */       
/*  617 */       break;
/*      */     
/*      */     case -1: 
/*  620 */       str = this.serverCharSet.toStringWithReplacement(paramArrayOfByte, 0, paramInt);
/*      */       
/*  622 */       break;
/*      */     
/*      */     default: 
/*  625 */       unexpectedCharset(this.clientCharSetId);
/*      */     }
/*      */     
/*      */     
/*      */ 
/*  630 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String NCharBytesToString(byte[] paramArrayOfByte, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  638 */     String str = null;
/*      */     
/*  640 */     if (this.clientCharSetId == -1)
/*      */     {
/*      */ 
/*      */ 
/*  644 */       str = this.serverNCharSet.toStringWithReplacement(paramArrayOfByte, 0, paramInt);
/*      */     }
/*      */     else
/*      */     {
/*  648 */       switch (this.serverNCharSetId)
/*      */       {
/*      */ 
/*      */ 
/*      */       case -5: 
/*      */       case 2000: 
/*  654 */         str = CharacterSet.AL16UTF16BytesToString(paramArrayOfByte, paramInt);
/*      */         
/*      */ 
/*  657 */         break;
/*      */       
/*      */ 
/*      */ 
/*      */       case 1: 
/*      */       case 2: 
/*  663 */         str = new String(paramArrayOfByte, 0, 0, paramInt);
/*      */         
/*  665 */         break;
/*      */       
/*      */ 
/*      */ 
/*      */       case 31: 
/*      */       case 178: 
/*  671 */         str = this.serverNCharSet.toStringWithReplacement(paramArrayOfByte, 0, paramInt);
/*  672 */         break;
/*      */       
/*      */ 
/*      */       case 870: 
/*      */       case 871: 
/*  677 */         str = CharacterSet.UTFToString(paramArrayOfByte, 0, paramInt);
/*      */         
/*      */ 
/*  680 */         break;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       case 873: 
/*  686 */         str = CharacterSet.AL32UTF8ToString(paramArrayOfByte, 0, paramInt);
/*      */         
/*  688 */         break;
/*      */       
/*      */       case -1: 
/*  691 */         str = this.serverCharSet.toStringWithReplacement(paramArrayOfByte, 0, paramInt);
/*      */         
/*  693 */         break;
/*      */       
/*      */       default: 
/*  696 */         unexpectedCharset(this.clientCharSetId);
/*      */       }
/*      */       
/*      */     }
/*      */     
/*      */ 
/*  702 */     return str;
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
/*      */   public int javaCharsToCHARBytes(char[] paramArrayOfChar, int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  729 */     return javaCharsToCHARBytes(paramArrayOfChar, paramInt, paramArrayOfByte, this.clientCharSetId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int javaCharsToCHARBytes(char[] paramArrayOfChar, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  739 */     return javaCharsToCHARBytes(paramArrayOfChar, paramInt1, paramArrayOfByte, paramInt2, this.clientCharSetId, paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int javaCharsToNCHARBytes(char[] paramArrayOfChar, int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  749 */     return javaCharsToCHARBytes(paramArrayOfChar, paramInt, paramArrayOfByte, this.serverNCharSetId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int javaCharsToNCHARBytes(char[] paramArrayOfChar, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  759 */     return javaCharsToCHARBytes(paramArrayOfChar, paramInt1, paramArrayOfByte, paramInt2, this.serverNCharSetId, paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int javaCharsToCHARBytes(char[] paramArrayOfChar, int paramInt, byte[] paramArrayOfByte, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  769 */     return javaCharsToCHARBytes(paramArrayOfChar, 0, paramArrayOfByte, 0, paramShort, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int javaCharsToCHARBytes(char[] paramArrayOfChar, int paramInt1, byte[] paramArrayOfByte, int paramInt2, short paramShort, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  778 */     int i = 0;
/*      */     
/*  780 */     switch (paramShort)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case -5: 
/*      */     case 2000: 
/*  788 */       i = CharacterSet.convertJavaCharsToAL16UTF16Bytes(paramArrayOfChar, paramInt1, paramArrayOfByte, paramInt2, paramInt3);
/*      */       
/*      */ 
/*  791 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 2: 
/*      */     case 178: 
/*  797 */       byte[] arrayOfByte = this.clientCharSet.convertWithReplacement(new String(paramArrayOfChar, paramInt1, paramInt3));
/*  798 */       System.arraycopy(arrayOfByte, 0, paramArrayOfByte, 0, arrayOfByte.length);
/*      */       
/*  800 */       i = arrayOfByte.length;
/*      */       
/*  802 */       break;
/*      */     
/*      */ 
/*      */     case 1: 
/*  806 */       i = CharacterSet.convertJavaCharsToASCIIBytes(paramArrayOfChar, paramInt1, paramArrayOfByte, paramInt2, paramInt3, this.isStrictASCIIConversion);
/*      */       
/*      */ 
/*  809 */       break;
/*      */     
/*      */     case 31: 
/*  812 */       i = CharacterSet.convertJavaCharsToISOLATIN1Bytes(paramArrayOfChar, paramInt1, paramArrayOfByte, paramInt2, paramInt3);
/*      */       
/*      */ 
/*  815 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 870: 
/*      */     case 871: 
/*  822 */       i = CharacterSet.convertJavaCharsToUTFBytes(paramArrayOfChar, paramInt1, paramArrayOfByte, paramInt2, paramInt3);
/*      */       
/*      */ 
/*  825 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 873: 
/*  831 */       i = CharacterSet.convertJavaCharsToAL32UTF8Bytes(paramArrayOfChar, paramInt1, paramArrayOfByte, paramInt2, paramInt3);
/*      */       
/*      */ 
/*  834 */       break;
/*      */     
/*      */     case -1: 
/*  837 */       i = javaCharsToDbCsBytes(paramArrayOfChar, paramInt1, paramArrayOfByte, paramInt2, paramInt3);
/*      */       
/*  839 */       break;
/*      */     
/*      */     default: 
/*  842 */       unexpectedCharset(this.clientCharSetId);
/*      */     }
/*      */     
/*      */     
/*      */ 
/*  847 */     return i;
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
/*      */   public int CHARBytesToJavaChars(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, int[] paramArrayOfInt, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  878 */     return _CHARBytesToJavaChars(paramArrayOfByte, paramInt1, paramArrayOfChar, paramInt2, this.clientCharSetId, paramArrayOfInt, paramInt3, this.serverCharSet, this.serverNCharSet, this.clientCharSet, false);
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
/*      */   public int NCHARBytesToJavaChars(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, int[] paramArrayOfInt, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  896 */     return _CHARBytesToJavaChars(paramArrayOfByte, paramInt1, paramArrayOfChar, paramInt2, this.serverNCharSetId, paramArrayOfInt, paramInt3, this.serverCharSet, this.serverNCharSet, this.clientCharSet, true);
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
/*      */   static final int _CHARBytesToJavaChars(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, short paramShort, int[] paramArrayOfInt, int paramInt3, CharacterSet paramCharacterSet1, CharacterSet paramCharacterSet2, CharacterSet paramCharacterSet3, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  923 */     int i = 0;
/*  924 */     int j = 0;
/*      */     
/*  926 */     switch (paramShort)
/*      */     {
/*      */ 
/*      */ 
/*      */     case -5: 
/*      */     case 2000: 
/*  932 */       j = paramArrayOfInt[0] - paramArrayOfInt[0] % 2;
/*      */       
/*      */ 
/*  935 */       if (paramInt3 > paramArrayOfChar.length - paramInt2) {
/*  936 */         paramInt3 = paramArrayOfChar.length - paramInt2;
/*      */       }
/*      */       
/*      */ 
/*  940 */       if (paramInt3 * 2 < j) {
/*  941 */         j = paramInt3 * 2;
/*      */       }
/*  943 */       i = CharacterSet.convertAL16UTF16BytesToJavaChars(paramArrayOfByte, paramInt1, paramArrayOfChar, paramInt2, j, true);
/*      */       
/*      */ 
/*      */ 
/*  947 */       paramArrayOfInt[0] -= j;
/*      */       
/*  949 */       break;
/*      */     
/*      */ 
/*      */     case 1: 
/*  953 */       j = paramArrayOfInt[0];
/*      */       
/*      */ 
/*  956 */       if (paramInt3 > paramArrayOfChar.length - paramInt2) {
/*  957 */         paramInt3 = paramArrayOfChar.length - paramInt2;
/*      */       }
/*      */       
/*      */ 
/*  961 */       if (paramInt3 < j) {
/*  962 */         j = paramInt3;
/*      */       }
/*  964 */       i = CharacterSet.convertASCIIBytesToJavaChars(paramArrayOfByte, paramInt1, paramArrayOfChar, paramInt2, j);
/*      */       
/*  966 */       paramArrayOfInt[0] -= j;
/*      */       
/*  968 */       break;
/*      */     
/*      */ 
/*      */     case 31: 
/*      */     case 178: 
/*  973 */       j = paramArrayOfInt[0];
/*      */       
/*  975 */       i = paramCharacterSet1.toCharWithReplacement(paramArrayOfByte, 0, paramArrayOfChar, paramInt2, j);
/*  976 */       paramArrayOfInt[0] -= i;
/*      */       
/*  978 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 870: 
/*      */     case 871: 
/*  985 */       if (paramInt3 > paramArrayOfChar.length - paramInt2) {
/*  986 */         paramInt3 = paramArrayOfChar.length - paramInt2;
/*      */       }
/*  988 */       i = CharacterSet.convertUTFBytesToJavaChars(paramArrayOfByte, paramInt1, paramArrayOfChar, paramInt2, paramArrayOfInt, true, paramInt3);
/*      */       
/*      */ 
/*      */ 
/*  992 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 873: 
/*  997 */       if (paramInt3 > paramArrayOfChar.length - paramInt2) {
/*  998 */         paramInt3 = paramArrayOfChar.length - paramInt2;
/*      */       }
/* 1000 */       i = CharacterSet.convertAL32UTF8BytesToJavaChars(paramArrayOfByte, paramInt1, paramArrayOfChar, paramInt2, paramArrayOfInt, true, paramInt3);
/*      */       
/*      */ 
/*      */ 
/* 1004 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case -1: 
/* 1021 */       unexpectedCharset((short)-1);
/* 1022 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     default: 
/* 1027 */       CharacterSet localCharacterSet = paramCharacterSet3;
/*      */       
/* 1029 */       if (paramBoolean) {
/* 1030 */         localCharacterSet = paramCharacterSet2;
/*      */       }
/* 1032 */       String str = localCharacterSet.toStringWithReplacement(paramArrayOfByte, paramInt1, paramArrayOfInt[0]);
/* 1033 */       char[] arrayOfChar = str.toCharArray();
/* 1034 */       int k = arrayOfChar.length;
/*      */       
/* 1036 */       if (k > paramInt3) {
/* 1037 */         k = paramInt3;
/*      */       }
/* 1039 */       i = k;
/* 1040 */       paramArrayOfInt[0] -= k;
/*      */       
/* 1042 */       System.arraycopy(arrayOfChar, 0, paramArrayOfChar, paramInt2, k);
/*      */     }
/*      */     
/*      */     
/*      */ 
/* 1047 */     return i;
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
/*      */   public byte[] asciiBytesToCHARBytes(byte[] paramArrayOfByte)
/*      */   {
/* 1069 */     byte[] arrayOfByte = null;
/*      */     
/*      */ 
/*      */ 
/*      */     int i;
/*      */     
/*      */ 
/*      */     int j;
/*      */     
/*      */ 
/* 1079 */     switch (this.clientCharSetId)
/*      */     {
/*      */ 
/*      */     case -5: 
/* 1083 */       arrayOfByte = new byte[paramArrayOfByte.length * 2];
/*      */       
/* 1085 */       i = 0; for (j = 0; i < paramArrayOfByte.length;)
/*      */       {
/* 1087 */         arrayOfByte[(j++)] = 0;
/* 1088 */         arrayOfByte[(j++)] = paramArrayOfByte[i];i++; continue;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1094 */         if (this.asciiCharSet == null) {
/* 1095 */           this.asciiCharSet = CharacterSet.make(1);
/*      */         }
/*      */         try
/*      */         {
/* 1099 */           arrayOfByte = this.serverCharSet.convert(this.asciiCharSet, paramArrayOfByte, 0, paramArrayOfByte.length);
/*      */         }
/*      */         catch (SQLException localSQLException) {}
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1109 */         arrayOfByte = paramArrayOfByte;
/*      */       }
/*      */     }
/*      */     
/*      */     
/* 1114 */     return arrayOfByte;
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
/*      */   public int javaCharsToDbCsBytes(char[] paramArrayOfChar, int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1151 */     int i = javaCharsToDbCsBytes(paramArrayOfChar, 0, paramArrayOfByte, 0, paramInt);
/*      */     
/* 1153 */     return i;
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
/*      */   public int javaCharsToDbCsBytes(char[] paramArrayOfChar, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1190 */     int i = 0;
/*      */     
/*      */ 
/* 1193 */     catchCharsLen(paramArrayOfChar, paramInt1, paramInt3);
/*      */     
/* 1195 */     String str = new String(paramArrayOfChar, paramInt1, paramInt3);
/* 1196 */     byte[] arrayOfByte = this.serverCharSet.convertWithReplacement(str);
/*      */     
/* 1198 */     str = null;
/*      */     
/* 1200 */     if (arrayOfByte != null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1206 */       i = arrayOfByte.length;
/*      */       
/* 1208 */       catchBytesLen(paramArrayOfByte, paramInt2, i);
/* 1209 */       System.arraycopy(arrayOfByte, 0, paramArrayOfByte, paramInt2, i);
/*      */       
/* 1211 */       arrayOfByte = null;
/*      */     }
/*      */     
/* 1214 */     return i;
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
/*      */   public static final int javaCharsToUcs2Bytes(char[] paramArrayOfChar, int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1247 */     int i = javaCharsToUcs2Bytes(paramArrayOfChar, 0, paramArrayOfByte, 0, paramInt);
/*      */     
/* 1249 */     return i;
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
/*      */   public static final int javaCharsToUcs2Bytes(char[] paramArrayOfChar, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1285 */     catchCharsLen(paramArrayOfChar, paramInt1, paramInt3);
/* 1286 */     catchBytesLen(paramArrayOfByte, paramInt2, paramInt3 * 2);
/*      */     
/* 1288 */     int k = paramInt3 + paramInt1;
/*      */     
/* 1290 */     int i = paramInt1; for (int j = paramInt2; i < k; i++)
/*      */     {
/* 1292 */       paramArrayOfByte[(j++)] = ((byte)(paramArrayOfChar[i] >> '\b' & 0xFF));
/* 1293 */       paramArrayOfByte[(j++)] = ((byte)(paramArrayOfChar[i] & 0xFF));
/*      */     }
/*      */     
/* 1296 */     return j - paramInt2;
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
/*      */   public static final int ucs2BytesToJavaChars(byte[] paramArrayOfByte, int paramInt, char[] paramArrayOfChar)
/*      */     throws SQLException
/*      */   {
/* 1346 */     return CharacterSet.AL16UTF16BytesToJavaChars(paramArrayOfByte, paramInt, paramArrayOfChar);
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
/*      */   public static final byte[] stringToAsciiBytes(String paramString)
/*      */   {
/* 1368 */     return CharacterSet.stringToASCII(paramString);
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
/*      */   public static final int asciiBytesToJavaChars(byte[] paramArrayOfByte, int paramInt, char[] paramArrayOfChar)
/*      */     throws SQLException
/*      */   {
/* 1395 */     return CharacterSet.convertASCIIBytesToJavaChars(paramArrayOfByte, 0, paramArrayOfChar, 0, paramInt);
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
/*      */   public static final int javaCharsToAsciiBytes(char[] paramArrayOfChar, int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1423 */     return CharacterSet.convertJavaCharsToASCIIBytes(paramArrayOfChar, 0, paramArrayOfByte, 0, paramInt);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final boolean isCharSetMultibyte(short paramShort)
/*      */   {
/* 1602 */     switch (paramShort)
/*      */     {
/*      */ 
/*      */ 
/*      */     case 1: 
/*      */     case 31: 
/* 1608 */       return false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case -5: 
/*      */     case -1: 
/*      */     case 870: 
/*      */     case 871: 
/*      */     case 873: 
/* 1619 */       return true;
/*      */     }
/*      */     
/* 1622 */     return false;
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
/*      */   public int getMaxCharbyteSize()
/*      */   {
/* 1660 */     return _getMaxCharbyteSize(this.clientCharSetId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMaxNCharbyteSize()
/*      */   {
/* 1668 */     return _getMaxCharbyteSize(this.serverNCharSetId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int _getMaxCharbyteSize(short paramShort)
/*      */   {
/* 1676 */     switch (paramShort)
/*      */     {
/*      */ 
/*      */     case 1: 
/* 1680 */       return 1;
/*      */     
/*      */     case 31: 
/* 1683 */       return 1;
/*      */     
/*      */ 
/*      */     case 870: 
/*      */     case 871: 
/* 1688 */       return 3;
/*      */     
/*      */ 
/*      */     case -5: 
/*      */     case 2000: 
/* 1693 */       return 2;
/*      */     
/*      */     case -1: 
/* 1696 */       return 4;
/*      */     
/*      */     case 873: 
/* 1699 */       return 4;
/*      */     }
/*      */     
/* 1702 */     return 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isUcs2CharSet()
/*      */   {
/* 1713 */     return this.clientCharSetId == -5;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final int RAWBytesToHexChars(byte[] paramArrayOfByte, int paramInt, char[] paramArrayOfChar)
/*      */   {
/* 1725 */     int i = 0; for (int j = 0; i < paramInt; i++)
/*      */     {
/* 1727 */       paramArrayOfChar[(j++)] = ((char)RepConversion.nibbleToHex((byte)(paramArrayOfByte[i] >> 4 & 0xF)));
/*      */       
/*      */ 
/* 1730 */       paramArrayOfChar[(j++)] = ((char)RepConversion.nibbleToHex((byte)(paramArrayOfByte[i] & 0xF)));
/*      */     }
/*      */     
/*      */ 
/* 1734 */     return j;
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
/*      */   public final int hexDigit2Nibble(char paramChar)
/*      */     throws SQLException
/*      */   {
/* 1750 */     int i = Character.digit(paramChar, 16);
/*      */     
/* 1752 */     if (i == -1)
/*      */     {
/*      */ 
/* 1755 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, "Invalid hex digit: " + paramChar);
/* 1756 */       localSQLException.fillInStackTrace();
/* 1757 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1761 */     return i;
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
/*      */   public final byte[] hexString2Bytes(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1777 */     int i = paramString.length();
/* 1778 */     char[] arrayOfChar = new char[i];
/*      */     
/* 1780 */     paramString.getChars(0, i, arrayOfChar, 0);
/* 1781 */     return hexChars2Bytes(arrayOfChar, 0, i);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final byte[] hexChars2Bytes(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1789 */     int i = 0;
/* 1790 */     int j = paramInt1;
/*      */     
/* 1792 */     if (paramInt2 == 0)
/* 1793 */       return new byte[0];
/*      */     byte[] arrayOfByte;
/* 1795 */     if (paramInt2 % 2 > 0)
/*      */     {
/* 1797 */       arrayOfByte = new byte[(paramInt2 + 1) / 2];
/* 1798 */       arrayOfByte[(i++)] = ((byte)hexDigit2Nibble(paramArrayOfChar[(j++)]));
/*      */     }
/*      */     else
/*      */     {
/* 1802 */       arrayOfByte = new byte[paramInt2 / 2];
/*      */     }
/* 1805 */     for (; 
/* 1805 */         i < arrayOfByte.length; i++)
/*      */     {
/* 1807 */       arrayOfByte[i] = ((byte)(hexDigit2Nibble(paramArrayOfChar[(j++)]) << 4 | hexDigit2Nibble(paramArrayOfChar[(j++)])));
/*      */     }
/*      */     
/* 1810 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream ConvertStream(InputStream paramInputStream, int paramInt)
/*      */   {
/* 1819 */     return new OracleConversionInputStream(this, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream ConvertStream(InputStream paramInputStream, int paramInt1, int paramInt2)
/*      */   {
/* 1828 */     return new OracleConversionInputStream(this, paramInputStream, paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream ConvertStreamInternal(InputStream paramInputStream, int paramInt1, int paramInt2)
/*      */   {
/* 1837 */     return new OracleConversionInputStreamInternal(this, paramInputStream, paramInt1, paramInt2);
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
/*      */   public InputStream ConvertStream(Reader paramReader, int paramInt1, int paramInt2, short paramShort)
/*      */   {
/* 1859 */     OracleConversionInputStream localOracleConversionInputStream = new OracleConversionInputStream(this, paramReader, paramInt1, paramInt2, paramShort);
/*      */     
/*      */ 
/* 1862 */     return localOracleConversionInputStream;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream ConvertStreamInternal(Reader paramReader, int paramInt1, int paramInt2, short paramShort)
/*      */   {
/* 1871 */     OracleConversionInputStreamInternal localOracleConversionInputStreamInternal = new OracleConversionInputStreamInternal(this, paramReader, paramInt1, paramInt2, paramShort);
/*      */     
/*      */ 
/* 1874 */     return localOracleConversionInputStreamInternal;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader ConvertCharacterStream(InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1884 */     return new OracleConversionReader(this, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader ConvertCharacterStream(InputStream paramInputStream, int paramInt, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 1893 */     OracleConversionReader localOracleConversionReader = new OracleConversionReader(this, paramInputStream, paramInt);
/*      */     
/*      */ 
/* 1896 */     localOracleConversionReader.setFormOfUse(paramShort);
/*      */     
/* 1898 */     return localOracleConversionReader;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream CharsToStream(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1907 */     if (paramInt3 == 10) {
/* 1908 */       return new AsciiStream(paramArrayOfChar, paramInt1, paramInt2);
/*      */     }
/* 1910 */     if (paramInt3 == 11) {
/* 1911 */       return new UnicodeStream(paramArrayOfChar, paramInt1, paramInt2);
/*      */     }
/*      */     
/* 1914 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 39, "unknownConversion");
/* 1915 */     localSQLException.fillInStackTrace();
/* 1916 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   class AsciiStream
/*      */     extends OracleBufferedStream
/*      */   {
/*      */     AsciiStream(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*      */     {
/* 1926 */       super();
/* 1927 */       this.currentBufferSize = this.initialBufferSize;
/* 1928 */       this.resizableBuffer = new byte[this.currentBufferSize];
/*      */       
/* 1930 */       if ((DBConversion.this.serverCharSetId == 1) || (!DBConversion.this.isStrictASCIIConversion))
/*      */       {
/* 1932 */         int i = paramInt1; for (int j = 0; j < paramInt2; j++) {
/* 1933 */           this.resizableBuffer[j] = ((byte)paramArrayOfChar[(i++)]);
/*      */         }
/*      */       }
/*      */       else {
/* 1937 */         if (DBConversion.this.asciiCharSet == null)
/* 1938 */           DBConversion.this.asciiCharSet = CharacterSet.make(1);
/* 1939 */         this.resizableBuffer = DBConversion.this.asciiCharSet.convertWithReplacement(new String(paramArrayOfChar, paramInt1, paramInt2));
/*      */       }
/*      */       
/* 1942 */       this.count = paramInt2;
/*      */     }
/*      */     
/*      */     public boolean needBytes()
/*      */     {
/* 1947 */       return (!this.closed) && (this.pos < this.count);
/*      */     }
/*      */     
/*      */     public boolean needBytes(int paramInt)
/*      */     {
/* 1952 */       return (!this.closed) && (this.pos < this.count);
/*      */     }
/*      */   }
/*      */   
/*      */   class UnicodeStream
/*      */     extends OracleBufferedStream
/*      */   {
/*      */     UnicodeStream(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*      */     {
/* 1961 */       super();
/* 1962 */       this.currentBufferSize = this.initialBufferSize;
/* 1963 */       this.resizableBuffer = new byte[this.currentBufferSize];
/*      */       
/* 1965 */       int i = paramInt1; for (int j = 0; j < paramInt2;)
/*      */       {
/* 1967 */         int k = paramArrayOfChar[(i++)];
/*      */         
/* 1969 */         this.resizableBuffer[(j++)] = ((byte)(k >> 8 & 0xFF));
/* 1970 */         this.resizableBuffer[(j++)] = ((byte)(k & 0xFF));
/*      */       }
/*      */       
/* 1973 */       this.count = paramInt2;
/*      */     }
/*      */     
/*      */     public boolean needBytes()
/*      */     {
/* 1978 */       return (!this.closed) && (this.pos < this.count);
/*      */     }
/*      */     
/*      */     public boolean needBytes(int paramInt)
/*      */     {
/* 1983 */       return (!this.closed) && (this.pos < this.count);
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
/*      */   static final void unexpectedCharset(short paramShort)
/*      */     throws SQLException
/*      */   {
/* 2001 */     SQLException localSQLException = DatabaseError.createSqlException(null, 35, "DBConversion");
/* 2002 */     localSQLException.fillInStackTrace();
/* 2003 */     throw localSQLException;
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
/*      */   protected static final void catchBytesLen(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2034 */     if (paramInt1 + paramInt2 > paramArrayOfByte.length)
/*      */     {
/*      */ 
/* 2037 */       SQLException localSQLException = DatabaseError.createSqlException(null, 39, "catchBytesLen");
/* 2038 */       localSQLException.fillInStackTrace();
/* 2039 */       throw localSQLException;
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
/*      */   protected static final void catchCharsLen(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2070 */     if (paramInt1 + paramInt2 > paramArrayOfChar.length)
/*      */     {
/*      */ 
/* 2073 */       SQLException localSQLException = DatabaseError.createSqlException(null, 39, "catchCharsLen");
/* 2074 */       localSQLException.fillInStackTrace();
/* 2075 */       throw localSQLException;
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
/*      */   public static final int getUtfLen(char paramChar)
/*      */   {
/* 2092 */     int i = 0;
/*      */     
/* 2094 */     if ((paramChar & 0xFF80) == 0)
/*      */     {
/* 2096 */       i = 1;
/*      */     }
/* 2098 */     else if ((paramChar & 0xF800) == 0)
/*      */     {
/* 2100 */       i = 2;
/*      */     }
/*      */     else
/*      */     {
/* 2104 */       i = 3;
/*      */     }
/*      */     
/* 2107 */     return i;
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
/*      */   int encodedByteLength(String paramString, boolean paramBoolean)
/*      */   {
/* 2123 */     int i = 0;
/* 2124 */     if (paramString != null)
/*      */     {
/* 2126 */       i = paramString.length();
/* 2127 */       if (i != 0)
/*      */       {
/* 2129 */         if (paramBoolean)
/*      */         {
/* 2131 */           i = this.isServerNCharSetFixedWidth ? i * this.maxNCharSize : this.serverNCharSet.encodedByteLength(paramString);
/*      */         }
/*      */         else
/*      */         {
/* 2135 */           i = this.isServerCharSetFixedWidth ? i * this.sMaxCharSize : this.serverCharSet.encodedByteLength(paramString);
/*      */         }
/*      */       }
/*      */     }
/* 2139 */     return i;
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
/*      */   int encodedByteLength(char[] paramArrayOfChar, boolean paramBoolean)
/*      */   {
/* 2154 */     int i = 0;
/* 2155 */     if (paramArrayOfChar != null)
/*      */     {
/* 2157 */       i = paramArrayOfChar.length;
/* 2158 */       if (i != 0)
/*      */       {
/* 2160 */         if (paramBoolean)
/*      */         {
/* 2162 */           i = this.isServerNCharSetFixedWidth ? i * this.maxNCharSize : this.serverNCharSet.encodedByteLength(paramArrayOfChar);
/*      */         }
/*      */         else
/*      */         {
/* 2166 */           i = this.isServerCharSetFixedWidth ? i * this.sMaxCharSize : this.serverCharSet.encodedByteLength(paramArrayOfChar);
/*      */         }
/*      */       }
/*      */     }
/* 2170 */     return i;
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
/* 2185 */     return null;
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
/* 2214 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/DBConversion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */