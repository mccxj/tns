/*      */ package oracle.sql;
/*      */ 
/*      */ import java.sql.SQLException;
/*      */ 
/*      */ class LdxLibThin implements LdxLib
/*      */ {
/*      */   private static final int LDXFDLSZ = 50;
/*      */   private static final byte LDX_CC = 1;
/*      */   private static final byte LDX_SCC = 2;
/*      */   private static final byte LDX_I = 3;
/*      */   private static final byte LDX_Y = 4;
/*      */   private static final byte LDX_IY = 5;
/*      */   private static final byte LDX_YY = 6;
/*      */   private static final byte LDX_IYY = 7;
/*      */   private static final byte LDX_YYY = 8;
/*      */   private static final byte LDX_IYYY = 9;
/*      */   private static final byte LDX_YYYY = 10;
/*      */   private static final byte LDX_YCYYY = 11;
/*      */   private static final byte LDX_SYYYY = 12;
/*      */   private static final byte LDX_SYCYYY = 13;
/*      */   private static final byte LDX_YEAR = 14;
/*      */   private static final byte LDX_SYEAR = 15;
/*      */   private static final byte LDX_Q = 16;
/*      */   private static final byte LDX_MM = 17;
/*      */   private static final byte LDX_IW = 18;
/*      */   private static final byte LDX_WW = 19;
/*      */   private static final byte LDX_W = 20;
/*      */   private static final byte LDX_D = 21;
/*      */   private static final byte LDX_DD = 22;
/*      */   private static final byte LDX_DDD = 23;
/*      */   private static final byte LDX_HH24 = 24;
/*      */   private static final byte LDX_HH = 25;
/*      */   private static final byte LDX_MI = 26;
/*      */   private static final byte LDX_SS = 27;
/*      */   private static final byte LDX_SSSSS = 28;
/*      */   private static final byte LDX_J = 29;
/*      */   private static final byte LDX_MONTH = 30;
/*      */   private static final byte LDX_MON = 31;
/*      */   private static final byte LDX_DAY = 32;
/*      */   private static final byte LDX_DY = 33;
/*      */   private static final byte LDX_AMPM = 34;
/*      */   private static final byte LDX_A_M_P_M = 35;
/*      */   private static final byte LDX_BCAD = 36;
/*      */   private static final byte LDX_B_C_A_D = 37;
/*      */   private static final byte LDX_RM = 38;
/*      */   private static final byte LDX_FM = 39;
/*      */   private static final byte LDX_RR = 40;
/*      */   private static final byte LDX_RRRR = 41;
/*      */   private static final byte LDX_FX = 42;
/*      */   private static final byte LDX_E = 43;
/*      */   private static final byte LDX_EE = 44;
/*      */   private static final byte LDX_LIT = 45;
/*      */   private static final byte LDX_JUS = 16;
/*      */   private static final byte LDX_NTH = 1;
/*      */   private static final byte LDX_SPL = 2;
/*      */   private static final byte LDX_CAP = 4;
/*      */   private static final byte LDX_UPR = 8;
/*      */   private static final byte LDX_QUO = 1;
/*      */   private static final byte LDX_SPA = 2;
/*      */   private static final byte LDX_PUN = 4;
/*      */   private static final byte LDX_ALPHA = -128;
/*      */   private static final byte LDXFNJUS = 64;
/*      */   private static final byte LDX_NEG = 32;
/*      */   private static final byte LDX_COMMA = 16;
/*      */   private static final byte LDX_LEN = 15;
/*      */   private static final byte LDXFL_NOT = 0;
/*      */   private static final byte LDXFL_FLEX = 1;
/*      */   private static final byte LDXFL_STD = 2;
/*      */   private static final byte LDXFL_MDONE = 4;
/*      */   private static final byte LDXFL_YDONE = 8;
/*      */   private static final byte LDXFL_PUNC = 16;
/*      */   private static final byte LDXFL_MSEC = 32;
/*      */   private static final int LDXSBUFFERSIZE = 64;
/*      */   private static final int LDXWBUFSIZE = 64;
/*      */   private static final int LDXTCE = 0;
/*      */   private static final int LDXTYE = 1;
/*      */   private static final int LDXTMO = 2;
/*      */   private static final int LDXTDA = 3;
/*      */   private static final int LDXTHO = 4;
/*      */   private static final int LDXTMI = 5;
/*      */   private static final int LDXTSO = 6;
/*      */   private static final int LDXTSIZ = 7;
/*      */   private static final int LDX_SUNDAY = 2445029;
/*      */   private static final int LDXPMXYR = 9999;
/*      */   private static final int LDXPMNYR = -4712;
/*      */   
/*      */   public byte[] ldxadm(byte[] paramArrayOfByte, int paramInt) throws SQLException
/*      */   {
/*   89 */     int j = ((paramArrayOfByte[0] & 0xFF) - 100) * 100 + ((paramArrayOfByte[1] & 0xFF) - 100);
/*      */     
/*   91 */     int i = j;
/*      */     
/*   93 */     int k = paramArrayOfByte[2] & 0xFF;
/*      */     
/*   95 */     int m = paramArrayOfByte[3] & 0xFF;
/*      */     
/*   97 */     paramInt += (paramArrayOfByte[2] & 0xFF) + i * 12;
/*      */     
/*      */ 
/*  100 */     if (paramInt > 0)
/*      */     {
/*  102 */       if ((i = paramInt / 12) > 9999) {
/*  103 */         throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)8));
/*      */       }
/*  105 */       if (paramInt %= 12 == 0)
/*      */       {
/*  107 */         i--;
/*  108 */         paramInt = 12;
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  113 */       if ((i = paramInt / 12 - 1) < 60824) {
/*  114 */         throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)8));
/*      */       }
/*  116 */       paramInt = paramInt % 12 + 12;
/*      */     }
/*      */     
/*      */ 
/*  120 */     int i1 = (paramInt == 2) && (ldxisl(j)) ? 29 : ldxdom[(k + 1)] - ldxdom[k];
/*      */     
/*      */ 
/*      */ 
/*  124 */     paramArrayOfByte[0] = ((byte)(i / 100 + 100));
/*  125 */     paramArrayOfByte[1] = ((byte)(i % 100 + 100));
/*      */     
/*  127 */     paramArrayOfByte[2] = ((byte)paramInt);
/*  128 */     int n = (paramInt == 2) && (ldxisl(i)) ? 29 : ldxdom[(paramInt + 1)] - ldxdom[paramInt];
/*      */     
/*      */ 
/*  131 */     paramArrayOfByte[3] = ((byte)((m == i1) || (m > n) ? n : m));
/*      */     
/*  133 */     return paramArrayOfByte;
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
/*      */   public byte[] ldxads(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  153 */     if (paramInt2 != 0)
/*      */     {
/*  155 */       paramInt2 += ((paramArrayOfByte[4] - 1) * 60 + (paramArrayOfByte[5] - 1)) * 60 + (paramArrayOfByte[6] - 1);
/*  156 */       paramInt1 += paramInt2 / 86400;
/*      */       
/*  158 */       if (paramInt2 %= 86400 < 0)
/*      */       {
/*  160 */         paramInt2 += 86400;
/*  161 */         paramInt1--;
/*      */       }
/*      */       
/*  164 */       paramArrayOfByte[4] = ((byte)(paramInt2 / 3600 + 1));
/*  165 */       paramArrayOfByte[5] = ((byte)(paramInt2 % 3600 / 60 + 1));
/*  166 */       paramArrayOfByte[6] = ((byte)(paramInt2 % 3600 % 60 + 1));
/*      */     }
/*      */     
/*      */ 
/*  170 */     int i = ((paramArrayOfByte[0] & 0xFF) - 100) * 100 + ((paramArrayOfByte[1] & 0xFF) - 100);
/*      */     
/*      */ 
/*  173 */     if (paramInt1 != 0)
/*      */     {
/*  175 */       paramInt1 += ldxctj(i, paramArrayOfByte[2], paramArrayOfByte[3]);
/*      */       
/*  177 */       if (paramInt1 < 1) {
/*  178 */         throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)8));
/*      */       }
/*      */       
/*      */ 
/*  182 */       paramArrayOfByte = ldxjtc(paramInt1, paramArrayOfByte);
/*      */     }
/*      */     
/*      */ 
/*  186 */     i = ((paramArrayOfByte[0] & 0xFF) - 100) * 100 + ((paramArrayOfByte[1] & 0xFF) - 100);
/*      */     
/*      */ 
/*  189 */     if (i > 9999) {
/*  190 */       throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)8));
/*      */     }
/*      */     
/*  193 */     return paramArrayOfByte;
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
/*      */   public int ldxchk(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  225 */     throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
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
/*      */   public byte[] ldxdfd(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  241 */     throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
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
/*      */   public void ldxdtd(byte[] paramArrayOfByte, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*      */     throws SQLException
/*      */   {
/*  257 */     throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
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
/*      */   public String ldxdts(byte[] paramArrayOfByte, String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  281 */     return ldxdts(paramArrayOfByte, ldxsto(paramString1, paramString2), paramString2);
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
/*      */   public String ldxdts(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, String paramString)
/*      */     throws SQLException
/*      */   {
/*  305 */     int i = 0;
/*      */     
/*  307 */     int k = 0;
/*  308 */     int m = 0;
/*      */     
/*  310 */     int i1 = 0;
/*      */     
/*  312 */     int i3 = 0;
/*  313 */     int i4 = 0;
/*  314 */     String str1 = null;
/*      */     
/*      */ 
/*  317 */     StringBuffer localStringBuffer1 = new StringBuffer(64);
/*      */     
/*  319 */     StringBuffer localStringBuffer2 = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  331 */     if ((paramArrayOfByte2.length == 0) || (paramArrayOfByte2 == null) || ((paramArrayOfByte2[0] == 0) && (paramArrayOfByte2[1] == 16)))
/*      */     {
/*      */ 
/*  334 */       paramArrayOfByte2 = DEFAULT_FORMAT;
/*      */     }
/*      */     
/*  337 */     int n = paramArrayOfByte2.length;
/*      */     
/*      */     java.util.Locale localLocale;
/*      */     
/*  341 */     if ((paramString != null) && (paramString.compareTo("") != 0))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  349 */       i4 = paramString.indexOf("_");
/*  350 */       if (i4 == -1)
/*      */       {
/*  352 */         localLocale = LxMetaData.getJavaLocale(paramString, "");
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  357 */         String str2 = paramString.substring(0, i4);
/*  358 */         String str3 = paramString.substring(i4 + 1);
/*  359 */         localLocale = LxMetaData.getJavaLocale(str2, str3);
/*      */       }
/*  361 */       if (localLocale == null)
/*      */       {
/*  363 */         localLocale = oracle.jdbc.driver.ClassRef.LOCALE.getDefault();
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  368 */       localLocale = oracle.jdbc.driver.ClassRef.LOCALE.getDefault();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  376 */     i3 = ((paramArrayOfByte1[0] & 0xFF) - 100) * 100 + ((paramArrayOfByte1[1] & 0xFF) - 100);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  381 */     while (m < n)
/*      */     {
/*      */ 
/*  384 */       i = paramArrayOfByte2[(m++)];
/*  385 */       k = paramArrayOfByte2[(m++)];
/*      */       
/*      */ 
/*  388 */       if (i == 0) {
/*      */         break;
/*      */       }
/*      */       
/*  392 */       if (i >= 45)
/*      */       {
/*      */ 
/*  395 */         int i2 = i - 45;
/*      */         
/*      */ 
/*      */ 
/*  399 */         localStringBuffer1.append(new String(paramArrayOfByte2, m, i2));
/*      */         
/*  401 */         m += i2;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*  407 */         int j = ldxfcdlen[i];
/*      */         
/*      */ 
/*      */         StringBuffer localStringBuffer3;
/*      */         
/*      */ 
/*  413 */         if ((((j & 0xFFFFFF80) != 0) && ((k & 0xC) != 0)) || (((k & 0x10) != 0) && ((j & 0x40) == 0)) || (((k & 0xC) != 0) && ((k & 0x3) != 0)))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  419 */           localStringBuffer2 = new StringBuffer(64);
/*  420 */           localStringBuffer3 = localStringBuffer2;
/*      */         }
/*      */         else {
/*  423 */           localStringBuffer3 = localStringBuffer1; }
/*      */         int i5;
/*  425 */         int i6; switch (i)
/*      */         {
/*      */         case 37: 
/*  428 */           if ((localLocale.getCountry().compareTo("US") == 0) && (localLocale.getLanguage().compareTo("en") == 0))
/*      */           {
/*      */ 
/*  431 */             str1 = paramArrayOfByte1[0] < 100 ? ldxpaa[2] : ldxpaa[0];
/*      */           }
/*      */           else
/*  434 */             i--;
/*      */         case 36: 
/*  436 */           localStringBuffer3.append(new java.text.DateFormatSymbols(localLocale).getEras()[1]);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  441 */           i5 = localStringBuffer3.length();
/*  442 */           break;
/*      */         case 35: 
/*  444 */           if ((localLocale.getCountry().compareTo("US") == 0) && (localLocale.getLanguage().compareTo("en") == 0))
/*      */           {
/*      */ 
/*  447 */             str1 = paramArrayOfByte1[4] - 1 >= 12 ? ldxpaa[3] : ldxpaa[1];
/*      */           }
/*      */           else
/*  450 */             i--;
/*      */         case 34: 
/*  452 */           localStringBuffer3.append(new java.text.DateFormatSymbols(localLocale).getAmPmStrings()[0]);
/*      */           
/*      */ 
/*      */ 
/*  456 */           i5 = localStringBuffer3.length();
/*  457 */           break;
/*      */         case 29: 
/*  459 */           i4 = ldxctj(i3, paramArrayOfByte1[2], paramArrayOfByte1[3]);
/*  460 */           break;
/*      */         case 21: 
/*  462 */           i4 = ldxdow(i3, paramArrayOfByte1[2], paramArrayOfByte1[3], localLocale);
/*  463 */           break;
/*      */         
/*      */         case 32: 
/*      */         case 33: 
/*  467 */           i6 = ldxdow(i3, paramArrayOfByte1[2], paramArrayOfByte1[3], localLocale);
/*      */           
/*      */ 
/*      */ 
/*  471 */           java.util.Calendar localCalendar = java.util.GregorianCalendar.getInstance(localLocale);
/*  472 */           if (localCalendar.getFirstDayOfWeek() > 1)
/*  473 */             i6++;
/*  474 */           if (i6 > 7)
/*  475 */             i6 -= 7;
/*  476 */           if (i6 == 0) {
/*  477 */             i6++;
/*      */           }
/*      */           String[] arrayOfString1;
/*  480 */           if (i == 32) {
/*  481 */             arrayOfString1 = new java.text.DateFormatSymbols(localLocale).getWeekdays();
/*      */           } else {
/*  483 */             arrayOfString1 = new java.text.DateFormatSymbols(localLocale).getShortWeekdays();
/*      */           }
/*  485 */           localStringBuffer3.append(arrayOfString1[i6]);
/*  486 */           i5 = localStringBuffer3.length();
/*  487 */           break;
/*      */         
/*      */ 
/*      */         case 1: 
/*      */         case 2: 
/*  492 */           if ((i4 = i3) > 0) {
/*  493 */             i4 = (i4 - 1) / 100 + 1;
/*      */           } else
/*  495 */             i4 = -((-1 - i4) / 100) - 1;
/*  496 */           break;
/*      */         case 22: 
/*  498 */           i4 = paramArrayOfByte1[3];
/*  499 */           break;
/*      */         case 43: 
/*      */         case 44: 
/*  502 */           throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */         
/*      */ 
/*      */         case 24: 
/*      */         case 25: 
/*  507 */           i4 = paramArrayOfByte1[4] - 1;
/*  508 */           if (i == 25)
/*  509 */             i4 = i4 == 0 ? 12 : i4 > 12 ? i4 - 12 : i4;
/*      */           break;
/*      */         case 26: 
/*  512 */           i4 = paramArrayOfByte1[5] - 1;
/*  513 */           break;
/*      */         case 16: 
/*  515 */           i4 = (paramArrayOfByte1[2] + 2) / 3;
/*  516 */           break;
/*      */         case 17: 
/*  518 */           i4 = paramArrayOfByte1[2];
/*  519 */           break;
/*      */         case 30: 
/*      */         case 31: 
/*  522 */           i4 = paramArrayOfByte1[2];
/*  523 */           String[] arrayOfString2; if (i == 30) {
/*  524 */             arrayOfString2 = new java.text.DateFormatSymbols(localLocale).getMonths();
/*      */           } else {
/*  526 */             arrayOfString2 = new java.text.DateFormatSymbols(localLocale).getShortMonths();
/*      */           }
/*      */           
/*  529 */           localStringBuffer3.append(arrayOfString2[(--i4)]);
/*  530 */           i5 = localStringBuffer3.length();
/*  531 */           break;
/*      */         case 38: 
/*  533 */           throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */         
/*      */ 
/*      */         case 27: 
/*  537 */           i4 = paramArrayOfByte1[6] - 1;
/*  538 */           break;
/*      */         case 28: 
/*  540 */           i4 = ((paramArrayOfByte1[4] - 1) * 60 + (paramArrayOfByte1[5] - 1)) * 60 + (paramArrayOfByte1[6] - 1);
/*      */           
/*  542 */           break;
/*      */         case 20: 
/*  544 */           i4 = (paramArrayOfByte1[3] + 6) / 7;
/*  545 */           break;
/*      */         case 23: 
/*  547 */           i4 = ldxcty(i3, paramArrayOfByte1[2], paramArrayOfByte1[3]);
/*  548 */           break;
/*      */         case 18: 
/*  550 */           throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */         
/*      */ 
/*      */         case 19: 
/*  554 */           i4 = (ldxcty(i3, paramArrayOfByte1[2], paramArrayOfByte1[3]) + 6) / 7;
/*  555 */           break;
/*      */         case 3: 
/*  557 */           throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */         
/*      */ 
/*      */         case 4: 
/*  561 */           i4 = i3 % 10;
/*  562 */           break;
/*      */         case 5: 
/*  564 */           throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */         
/*      */ 
/*      */         case 6: 
/*      */         case 40: 
/*  569 */           i4 = i3 % 100;
/*  570 */           break;
/*      */         case 7: 
/*  572 */           throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */         
/*      */ 
/*      */         case 8: 
/*  576 */           i4 = i3 % 1000;
/*  577 */           break;
/*      */         case 10: 
/*      */         case 11: 
/*      */         case 12: 
/*      */         case 13: 
/*      */         case 41: 
/*  583 */           i4 = i3;
/*  584 */           break;
/*      */         case 9: 
/*  586 */           throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */         
/*      */ 
/*      */         case 14: 
/*      */         case 15: 
/*  591 */           throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */         case 42: 
/*      */           break;
/*      */         
/*      */         case 39: 
/*      */         default: 
/*  597 */           throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)7));
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  602 */           if ((j & 0xFFFFFF80) == 0)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*  607 */             if ((j & 0x20) == 0)
/*      */             {
/*  609 */               if (i4 < 0) {
/*  610 */                 i4 = -i4;
/*      */ 
/*      */ 
/*      */               }
/*      */               
/*      */ 
/*      */ 
/*      */             }
/*  618 */             else if (i4 >= 0)
/*      */             {
/*  620 */               localStringBuffer3.insert(0, " ");
/*  621 */               j = (byte)(j - 1);
/*      */             }
/*  623 */             if ((k & 0x2) != 0)
/*      */             {
/*      */ 
/*  626 */               throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */             }
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  633 */             if ((k & 0x10) != 0) {
/*  634 */               i5 = j & 0xF;
/*      */             } else {
/*  636 */               i5 = 0;
/*      */             }
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*  642 */             String str4 = lxi42b(64, i4, i5, (j & 0x10) != 0, localLocale);
/*      */             
/*      */ 
/*  645 */             localStringBuffer3.append(str4);
/*      */             
/*  647 */             if ((k & 0x1) != 0)
/*      */             {
/*      */ 
/*  650 */               throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             }
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           }
/*  662 */           else if (((k & 0x10) != 0) && ((j & 0x40) == 0))
/*      */           {
/*      */             String[] arrayOfString3;
/*      */             
/*      */ 
/*  667 */             switch (i)
/*      */             {
/*      */             case 33: 
/*  670 */               arrayOfString3 = new java.text.DateFormatSymbols(localLocale).getShortWeekdays();
/*  671 */               break;
/*      */             case 32: 
/*  673 */               arrayOfString3 = new java.text.DateFormatSymbols(localLocale).getWeekdays();
/*  674 */               break;
/*      */             case 31: 
/*  676 */               arrayOfString3 = new java.text.DateFormatSymbols(localLocale).getShortMonths();
/*  677 */               break;
/*      */             case 30: 
/*  679 */               arrayOfString3 = new java.text.DateFormatSymbols(localLocale).getMonths();
/*  680 */               break;
/*      */             
/*      */             default: 
/*  683 */               throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)7));
/*      */             }
/*      */             
/*      */             
/*      */ 
/*  688 */             i6 = 0;
/*      */             
/*      */ 
/*  691 */             for (int i7 = 0; i7 < arrayOfString3.length; i7++)
/*      */             {
/*  693 */               i4 = arrayOfString3[i7].length();
/*  694 */               if (i4 > i6) {
/*  695 */                 i6 = i4;
/*      */               }
/*      */             }
/*      */             
/*  699 */             i6 -= localStringBuffer3.length();
/*  700 */             for (i7 = 0; i7 < i6; i7++) {
/*  701 */               localStringBuffer3.append(" ");
/*      */             }
/*      */           }
/*      */           
/*  705 */           if (str1 != null)
/*      */           {
/*      */ 
/*  708 */             i5 = 4;
/*      */             
/*  710 */             if ((k & 0xC) == 0)
/*  711 */               localStringBuffer3 = localStringBuffer1;
/*  712 */             localStringBuffer3.append(str1.toLowerCase(localLocale));
/*  713 */             str1 = null;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*  718 */           if ((k & 0x4) != 0)
/*      */           {
/*      */ 
/*  721 */             if (Character.isLowerCase(localStringBuffer3.charAt(0))) {
/*  722 */               localStringBuffer3.setCharAt(0, Character.toUpperCase(localStringBuffer3.charAt(0)));
/*      */             }
/*  724 */             localStringBuffer1.append(localStringBuffer3.toString());
/*      */ 
/*      */           }
/*  727 */           else if ((k & 0x8) != 0)
/*      */           {
/*  729 */             localStringBuffer1.append(localStringBuffer3.toString().toUpperCase());
/*      */           }
/*  731 */           else if (localStringBuffer3 != localStringBuffer1)
/*      */           {
/*  733 */             localStringBuffer1.append(localStringBuffer3.toString());
/*      */           }
/*      */           break;
/*      */         }
/*      */       }
/*      */     }
/*  739 */     return localStringBuffer1.toString();
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
/*      */   private int ldxdow(int paramInt1, int paramInt2, int paramInt3, java.util.Locale paramLocale)
/*      */   {
/*  762 */     java.util.Calendar localCalendar = java.util.GregorianCalendar.getInstance(paramLocale);
/*      */     
/*      */ 
/*  765 */     int i = ldxctj(paramInt1, paramInt2, paramInt3);
/*      */     
/*      */ 
/*  768 */     int j = (i - (2445029 + (localCalendar.getFirstDayOfWeek() - 1))) % 7;
/*  769 */     if (j < 0)
/*  770 */       j += 7;
/*  771 */     return j + 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int ldxctj(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*      */     int i;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  787 */     if (paramInt1 == 60824) {
/*  788 */       i = 0;
/*      */     }
/*      */     else {
/*  791 */       i = paramInt1 + 4712;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  797 */       i = 365 * i + (i - 1) / 4;
/*      */     }
/*      */     
/*      */ 
/*  801 */     if (paramInt1 >= 1583) {
/*  802 */       i = i - 10 - (paramInt1 - 1501) / 100 + (paramInt1 - 1201) / 400;
/*      */     }
/*      */     
/*      */ 
/*  806 */     i += ldxcty(paramInt1, paramInt2, paramInt3);
/*      */     
/*      */ 
/*  809 */     if ((paramInt1 == 1582) && (((paramInt2 == 10) && (paramInt3 >= 15)) || (paramInt2 >= 11))) {
/*  810 */       i -= 10;
/*      */     }
/*  812 */     return i;
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
/*      */   private byte[] ldxjtc(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  829 */     if (paramInt < 1) {
/*  830 */       throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)10));
/*      */     }
/*  832 */     if (paramInt < 366)
/*      */     {
/*      */ 
/*  835 */       paramArrayOfByte[0] = 53;
/*  836 */       paramArrayOfByte[1] = 28;
/*      */       
/*      */ 
/*  839 */       paramArrayOfByte = ldxdyc(60824, paramInt, paramArrayOfByte); } else { int k;
/*      */       int i;
/*      */       int j;
/*  842 */       if (paramInt < 2299161)
/*      */       {
/*      */ 
/*      */ 
/*  846 */         paramInt -= 366;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  851 */         k = 60825 + paramInt / 1461 * 4;
/*  852 */         paramInt %= 1461;
/*      */         
/*  854 */         i = paramInt / 365;
/*  855 */         j = paramInt % 365;
/*      */         
/*  857 */         if ((j == 0) && (i == 4))
/*      */         {
/*      */ 
/*  860 */           j = 366;
/*  861 */           i = 3;
/*      */         }
/*      */         else {
/*  864 */           j++;
/*      */         }
/*  866 */         k += i;
/*      */         
/*  868 */         paramArrayOfByte = ldxdyc(k, j, paramArrayOfByte);
/*      */         
/*  870 */         paramArrayOfByte[0] = ((byte)(k / 100 + 100));
/*  871 */         paramArrayOfByte[1] = ((byte)(k % 100 + 100));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*  877 */         paramInt = 4 * (paramInt - 1721119) - 1;
/*  878 */         k = paramInt / 146097;
/*  879 */         paramInt %= 146097;
/*      */         
/*  881 */         j = paramInt / 4;
/*      */         
/*  883 */         paramInt = 4 * j + 3;
/*  884 */         j = paramInt % 1461;
/*  885 */         paramInt /= 1461;
/*      */         
/*  887 */         j /= 4;
/*  888 */         j++;
/*      */         
/*  890 */         i = 5 * j - 3;
/*  891 */         j = i % 153;
/*  892 */         i /= 153;
/*      */         
/*  894 */         j /= 5;
/*  895 */         j++;
/*      */         
/*  897 */         k *= 100;
/*  898 */         k += paramInt;
/*      */         
/*  900 */         if (i < 10) {
/*  901 */           i += 3;
/*      */         }
/*      */         else {
/*  904 */           i -= 9;
/*  905 */           k++;
/*      */         }
/*      */         
/*  908 */         paramArrayOfByte[3] = ((byte)j);
/*  909 */         paramArrayOfByte[2] = ((byte)i);
/*  910 */         paramArrayOfByte[0] = ((byte)(k / 100 + 100));
/*  911 */         paramArrayOfByte[1] = ((byte)(k % 100 + 100));
/*      */       }
/*      */     }
/*      */     
/*  915 */     return paramArrayOfByte;
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
/*      */   private byte[] ldxdyc(int paramInt1, int paramInt2, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  934 */     boolean bool = ldxisl(paramInt1);
/*      */     
/*  936 */     if ((paramInt2 == 366) && (!bool)) {
/*  937 */       throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)9));
/*      */     }
/*      */     
/*  940 */     int i = paramInt2;
/*      */     
/*  942 */     if (paramInt2 > 59 + (bool ? 1 : 0)) {
/*  943 */       i += 2 - (bool ? 1 : 0);
/*      */     }
/*  945 */     i += 91;int j = i * 100;
/*  946 */     paramArrayOfByte[3] = ((byte)(i - (j - j % 3055) / 100));
/*  947 */     paramArrayOfByte[2] = ((byte)(j / 3055 - 2));
/*      */     
/*  949 */     return paramArrayOfByte;
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
/*      */   private int ldxcty(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  968 */     return ldxdom[paramInt2] + paramInt3 + ((paramInt2 >= 3) && (ldxisl(paramInt1)) ? 1 : 0);
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
/*      */   private boolean ldxisl(int paramInt)
/*      */   {
/*  981 */     return (paramInt % 4 == 0) && (paramInt <= 1582 ? paramInt == 60824 : (paramInt % 100 != 0) || (paramInt % 400 == 0));
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
/*      */   private String lxi42b(int paramInt1, long paramLong, int paramInt2, boolean paramBoolean, java.util.Locale paramLocale)
/*      */     throws SQLException
/*      */   {
/* 1009 */     int i = 0;
/* 1010 */     int j = 0;
/* 1011 */     long l = paramLong;
/* 1012 */     java.text.NumberFormat localNumberFormat = java.text.NumberFormat.getInstance(paramLocale);
/* 1013 */     java.text.DecimalFormat localDecimalFormat = (java.text.DecimalFormat)localNumberFormat;
/* 1014 */     StringBuffer localStringBuffer = new StringBuffer();
/* 1015 */     int k = localDecimalFormat.getGroupingSize();
/* 1016 */     char c1 = '\000';
/* 1017 */     char c2 = localDecimalFormat.getDecimalFormatSymbols().getZeroDigit();
/*      */     
/*      */ 
/* 1020 */     for (j = 1; l /= 10L != 0L; j++) {}
/*      */     
/* 1022 */     if (paramLong < 0L) {
/* 1023 */       j++;
/*      */     }
/* 1025 */     if (paramBoolean) {
/* 1026 */       j += (j - 1) / k;
/*      */     }
/*      */     
/* 1029 */     if (!paramBoolean) {
/* 1030 */       localNumberFormat.setGroupingUsed(false);
/*      */     }
/* 1032 */     localStringBuffer.append(localDecimalFormat.format(paramLong));
/*      */     
/* 1034 */     if ((j > paramInt1) || (paramInt2 > paramInt1) || ((paramInt2 != 0) && (j > paramInt2)))
/*      */     {
/* 1036 */       throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)3));
/*      */     }
/*      */     
/* 1039 */     if (paramBoolean) {
/* 1040 */       c1 = localDecimalFormat.getDecimalFormatSymbols().getGroupingSeparator();
/*      */     }
/* 1042 */     if (paramInt2 != 0)
/*      */     {
/* 1044 */       for (paramInt2 -= j; paramInt2-- != 0;)
/*      */       {
/* 1046 */         if ((paramBoolean) && (i++ == k) && (paramInt2 != 0))
/*      */         {
/* 1048 */           localStringBuffer.insert(0, c1);
/* 1049 */           i = 1;
/* 1050 */           paramInt2--;
/*      */         }
/* 1052 */         localStringBuffer.insert(0, c2);
/*      */       }
/*      */     }
/*      */     
/* 1056 */     return localStringBuffer.toString();
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
/*      */   public byte[] ldxsto(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 1071 */     int i = 0;
/* 1072 */     int j = 0;
/*      */     
/* 1074 */     int m = 0;
/* 1075 */     byte[] arrayOfByte1 = new byte['Ȁ'];
/* 1076 */     int n = 16;
/* 1077 */     int i1 = 0;
/* 1078 */     int i2 = 0;
/* 1079 */     char[] arrayOfChar = new char['Ā'];
/* 1080 */     int i3 = 0;
/*      */     
/*      */ 
/* 1083 */     int i4 = 0;
/*      */     
/*      */ 
/* 1086 */     if ((paramString1 == null) || (paramString1.compareTo("") == 0))
/*      */     {
/*      */ 
/* 1089 */       return NULLFMT;
/*      */     }
/*      */     
/* 1092 */     int k = paramString1.length();
/*      */     
/*      */ 
/* 1095 */     while (i < k)
/*      */     {
/*      */ 
/* 1098 */       n = 16;
/* 1099 */       i2 = 0;
/*      */       
/*      */ 
/*      */ 
/*      */       do
/*      */       {
/* 1105 */         if ((i < k) && (paramString1.charAt(i) == '|'))
/*      */         {
/* 1107 */           i++;
/* 1108 */           break;
/*      */         }
/*      */         
/*      */ 
/* 1112 */         for (i3 = 0; (i < k) && (!Character.isLetterOrDigit(paramString1.charAt(i)));)
/*      */         {
/*      */ 
/* 1115 */           if (paramString1.charAt(i) == '"')
/*      */           {
/*      */ 
/* 1118 */             i1 = 1;
/*      */             
/* 1120 */             while ((i != k) && (paramString1.charAt(++i) != '"'))
/*      */             {
/* 1122 */               arrayOfChar[(i2++)] = paramString1.charAt(i);
/* 1123 */               i3++;
/*      */             }
/* 1125 */             if (paramString1.charAt(i) == '"') {
/* 1126 */               i++;
/*      */             }
/*      */           }
/*      */           else {
/* 1130 */             arrayOfChar[(i2++)] = paramString1.charAt(i++);
/* 1131 */             i3++;
/*      */           }
/*      */         }
/*      */         
/*      */         int i5;
/* 1136 */         if (i3 > 0)
/*      */         {
/* 1138 */           if (i3 > 210)
/*      */           {
/*      */ 
/* 1141 */             throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)7));
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 1146 */           if (Character.isWhitespace(arrayOfChar[0]))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/* 1151 */             int i10 = 0;
/*      */             
/* 1153 */             i5 = i3;int i8 = 0;
/* 1154 */             for (; (i5 > 0) && (Character.isWhitespace(arrayOfChar[i10])); 
/* 1155 */                 i5--) { i10++;i8++; }
/* 1156 */             arrayOfByte1[(j++)] = ((byte)(45 + i8));
/*      */             
/* 1158 */             arrayOfByte1[(j++)] = 2;
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1164 */             byte[] arrayOfByte4 = new String(arrayOfChar, 0, i8).getBytes();
/* 1165 */             System.arraycopy(arrayOfByte4, 0, arrayOfByte1, j, arrayOfByte4.length);
/*      */             
/*      */ 
/* 1168 */             j += arrayOfByte4.length;
/*      */             
/* 1170 */             i3 -= i8;
/* 1171 */             if (i3 == 0) {
/*      */               continue;
/*      */             }
/*      */             
/*      */ 
/*      */ 
/* 1177 */             i += i8 + 1;
/*      */             
/* 1179 */             i2 = i10;
/*      */           }
/*      */           else {
/* 1182 */             i2 = 0;
/*      */           }
/* 1184 */           if (i1 != 1) {
/* 1185 */             i1 = 4;
/*      */           }
/* 1187 */           arrayOfByte1[(j++)] = ((byte)(45 + i3));
/* 1188 */           arrayOfByte1[(j++)] = i1;
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1194 */           byte[] arrayOfByte2 = new String(arrayOfChar, 0, i3).getBytes();
/* 1195 */           System.arraycopy(arrayOfByte2, 0, arrayOfByte1, j, arrayOfByte2.length);
/*      */           
/* 1197 */           j += arrayOfByte2.length;
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 1204 */           if (!Character.isLetterOrDigit(paramString1.charAt(i)))
/*      */           {
/* 1206 */             throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)7));
/*      */           }
/*      */           
/*      */ 
/* 1210 */           char c = Character.toUpperCase(paramString1.charAt(i));
/* 1211 */           i5 = c - 'A';
/* 1212 */           if ((i5 > 25) || (ldxfdi[i5] == Integer.MIN_VALUE))
/*      */           {
/*      */ 
/* 1215 */             throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)7));
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 1220 */           i4 = ldxfdi[i5];
/*      */           
/*      */ 
/*      */ 
/*      */ 
/* 1225 */           i5 = 50;
/* 1226 */           int i9; int i6; int i7; for (; i4 < ldxfda.length; i4++)
/*      */           {
/*      */ 
/* 1229 */             i9 = ldxfda[i4].length;
/* 1230 */             i6 = 0; for (i7 = i; 
/* 1231 */                 (i6 < i9) && (i7 < k); i7++)
/*      */             {
/* 1233 */               if (Character.toUpperCase(paramString1.charAt(i7)) != ldxfda[i4][i6]) {
/*      */                 break;
/*      */               }
/* 1231 */               i6++;
/*      */             }
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1238 */             if (i6 == i9)
/*      */             {
/* 1240 */               i5 = i4;
/*      */             }
/*      */             
/* 1243 */             if (ldxfda[(i4 + 1)][0] != c) {
/*      */               break;
/*      */             }
/*      */           }
/* 1247 */           i4 = i5;
/*      */           
/*      */ 
/* 1250 */           if (i4 >= ldxfda.length)
/*      */           {
/* 1252 */             throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)7));
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 1257 */           if (k - i > 1)
/*      */           {
/* 1259 */             if (Character.isUpperCase(paramString1.charAt(i)))
/*      */             {
/* 1261 */               c = Character.isLetterOrDigit(paramString1.charAt(i + 1)) ? paramString1.charAt(i + 1) : paramString1.charAt(i + 2);
/*      */               
/*      */ 
/* 1264 */               if (Character.isLowerCase(c)) {
/* 1265 */                 n = (byte)(n | 0x4);
/*      */               } else {
/* 1267 */                 n = (byte)(n | 0x8);
/*      */               }
/*      */             }
/*      */           }
/* 1271 */           i += ldxfda[i4].length;
/* 1272 */           m = ldxfdc[i4];
/*      */           
/* 1274 */           if ((ldxfcdlen[m] & 0xFFFFFF80) == 0)
/*      */           {
/* 1276 */             i4 = 0; for (i6 = -1; i4 < ldxfdx.length; i4++)
/*      */             {
/* 1278 */               i9 = ldxfdx[i4].length;
/* 1279 */               i5 = 0; for (i7 = i; 
/* 1280 */                   (i5 < i9) && (i7 < k); i7++)
/*      */               {
/*      */ 
/* 1283 */                 if (Character.toUpperCase(paramString1.charAt(i7)) != ldxfdx[i4][i5]) {
/*      */                   break;
/*      */                 }
/* 1280 */                 i5++;
/*      */               }
/*      */               
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1289 */               if (i5 == i9)
/*      */               {
/* 1291 */                 i6 = i4;
/*      */               }
/*      */             }
/*      */             
/* 1295 */             i4 = i6;
/*      */             
/*      */ 
/* 1298 */             if ((i4 >= 0) && (i4 < ldxfdx.length))
/*      */             {
/* 1300 */               n = (byte)(n | ldxfdxc[i4]);
/* 1301 */               i += ldxfdx[i4].length;
/*      */             }
/*      */           }
/*      */           
/*      */ 
/* 1306 */           if (512 - j < 2)
/*      */           {
/*      */ 
/*      */ 
/* 1310 */             throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)7));
/*      */           }
/*      */           
/*      */ 
/* 1314 */           arrayOfByte1[(j++)] = ((byte)m);
/* 1315 */           arrayOfByte1[(j++)] = n;
/*      */         }
/*      */         
/*      */ 
/* 1319 */         if (m == 39) {
/* 1320 */           n = (n & 0x10) == 1 ? 0 : 16;
/*      */         }
/* 1322 */       } while (m == 39);
/*      */     }
/*      */     
/*      */ 
/* 1326 */     byte[] arrayOfByte3 = new byte[j];
/* 1327 */     System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 0, arrayOfByte3.length);
/* 1328 */     return arrayOfByte3;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] ldxdyf(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1338 */     throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void ldxftd(byte[] paramArrayOfByte, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*      */     throws SQLException
/*      */   {
/* 1350 */     throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] ldxgdt()
/*      */     throws SQLException
/*      */   {
/* 1361 */     throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] ldxldd(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1372 */     throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
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
/*      */   public byte[] ldxnxd(byte[] paramArrayOfByte, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1386 */     throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
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
/*      */   public byte[] ldxrnd(byte[] paramArrayOfByte, String paramString)
/*      */     throws SQLException
/*      */   {
/* 1400 */     throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
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
/*      */   public byte[] ldxsbm(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
/*      */     throws SQLException
/*      */   {
/* 1414 */     throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
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
/*      */   public void ldxsub(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*      */     throws SQLException
/*      */   {
/* 1428 */     throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
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
/*      */   public byte[] ldxstd(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 1510 */     Object localObject = null;
/* 1511 */     byte[] arrayOfByte = null;
/* 1512 */     int i = 0;
/* 1513 */     int j = 0;
/* 1514 */     char[] arrayOfChar = new char['Ȁ'];
/* 1515 */     int k = 0;
/* 1516 */     int m = 0;
/* 1517 */     int n = 0;
/* 1518 */     int i1 = 0;
/* 1519 */     java.text.ParsePosition localParsePosition = new java.text.ParsePosition(0);
/* 1520 */     java.text.SimpleDateFormat localSimpleDateFormat = new java.text.SimpleDateFormat();
/*      */     
/*      */ 
/* 1523 */     arrayOfByte = ldxsto(paramString2, paramString3);
/*      */     
/*      */ 
/* 1526 */     ldxsti(arrayOfByte);
/*      */     
/* 1528 */     m = arrayOfByte.length;
/*      */     
/*      */ 
/*      */ 
/* 1532 */     while (k < m)
/*      */     {
/* 1534 */       i = arrayOfByte[(k++)];
/* 1535 */       j = arrayOfByte[(k++)];
/*      */       int i2;
/* 1537 */       switch (i)
/*      */       {
/*      */       case 43: 
/*      */       case 44: 
/* 1541 */         throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */       
/*      */ 
/*      */       case 41: 
/* 1545 */         throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */       
/*      */ 
/*      */       case 40: 
/* 1549 */         throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */       
/*      */ 
/*      */       case 4: 
/* 1553 */         arrayOfChar[(n++)] = 'y';
/* 1554 */         break;
/*      */       
/*      */       case 6: 
/* 1557 */         for (i2 = 0; i2 < 2; i2++)
/* 1558 */           arrayOfChar[(n++)] = 'y';
/* 1559 */         break;
/*      */       
/*      */       case 8: 
/* 1562 */         for (i2 = 0; i2 < 3; i2++)
/* 1563 */           arrayOfChar[(n++)] = 'y';
/* 1564 */         break;
/*      */       
/*      */       case 10: 
/* 1567 */         for (i2 = 0; i2 < 4; i2++)
/* 1568 */           arrayOfChar[(n++)] = 'y';
/* 1569 */         break;
/*      */       
/*      */       case 11: 
/*      */       case 12: 
/*      */       case 13: 
/* 1574 */         throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */       
/*      */ 
/*      */       case 38: 
/* 1578 */         throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */       
/*      */ 
/*      */       case 17: 
/* 1582 */         arrayOfChar[(n++)] = 'M';
/* 1583 */         arrayOfChar[(n++)] = 'M';
/* 1584 */         break;
/*      */       
/*      */       case 31: 
/* 1587 */         for (i2 = 0; i2 < 3; i2++)
/* 1588 */           arrayOfChar[(n++)] = 'M';
/* 1589 */         break;
/*      */       
/*      */       case 30: 
/* 1592 */         for (i2 = 0; i2 < 4; i2++)
/* 1593 */           arrayOfChar[(n++)] = 'M';
/* 1594 */         break;
/*      */       
/*      */       case 21: 
/*      */       case 33: 
/* 1598 */         arrayOfChar[(n++)] = 'E';
/* 1599 */         break;
/*      */       
/*      */       case 32: 
/* 1602 */         for (i2 = 0; i2 < 4; i2++)
/* 1603 */           arrayOfChar[(n++)] = 'E';
/* 1604 */         break;
/*      */       
/*      */       case 22: 
/* 1607 */         arrayOfChar[(n++)] = 'd';
/* 1608 */         break;
/*      */       
/*      */       case 23: 
/* 1611 */         arrayOfChar[(n++)] = 'D';
/* 1612 */         break;
/*      */       
/*      */       case 29: 
/* 1615 */         throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */       
/*      */ 
/*      */       case 25: 
/* 1619 */         arrayOfChar[(n++)] = 'h';
/* 1620 */         break;
/*      */       
/*      */       case 24: 
/* 1623 */         arrayOfChar[(n++)] = 'H';
/* 1624 */         break;
/*      */       
/*      */       case 26: 
/* 1627 */         arrayOfChar[(n++)] = 'm';
/* 1628 */         break;
/*      */       
/*      */       case 27: 
/* 1631 */         arrayOfChar[(n++)] = 's';
/* 1632 */         break;
/*      */       
/*      */       case 28: 
/* 1635 */         throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */       
/*      */ 
/*      */       case 34: 
/*      */       case 35: 
/* 1640 */         arrayOfChar[(n++)] = 'a';
/* 1641 */         break;
/*      */       
/*      */       case 36: 
/*      */       case 37: 
/* 1645 */         arrayOfChar[(n++)] = 'G';
/* 1646 */         break;
/*      */       case 39: 
/*      */       case 42: 
/* 1649 */         throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */       case 5: case 7: case 9: case 14: case 15: 
/*      */       case 16: case 18: case 19: case 20: default: 
/* 1652 */         i1 = i - 45;
/* 1653 */         str = new String(arrayOfByte, k, i1);
/*      */         
/* 1655 */         if (j == 1)
/*      */         {
/* 1657 */           arrayOfChar[(n++)] = '\'';
/* 1658 */           System.arraycopy(str.toCharArray(), 0, arrayOfChar, n, i1);
/*      */           
/* 1660 */           n += i1;
/* 1661 */           k += i1;
/* 1662 */           arrayOfChar[(n++)] = '\'';
/*      */         }
/*      */         else
/*      */         {
/* 1666 */           System.arraycopy(str.toCharArray(), 0, arrayOfChar, n, i1);
/*      */           
/* 1668 */           n += i1;
/* 1669 */           k += i1;
/*      */         }
/*      */         
/*      */ 
/*      */         break;
/*      */       }
/*      */       
/*      */     }
/*      */     
/* 1678 */     String str = new String(arrayOfChar, 0, n);
/* 1679 */     localSimpleDateFormat.applyPattern(str);
/* 1680 */     localSimpleDateFormat.setLenient(false);
/* 1681 */     java.util.Date localDate = localSimpleDateFormat.parse(paramString1, localParsePosition);
/* 1682 */     if (localDate != null)
/*      */     {
/* 1684 */       return DATE.toBytes(new java.sql.Timestamp(localDate.getTime()));
/*      */     }
/*      */     
/* 1687 */     throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)6));
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
/*      */   public byte[] ldxtrn(byte[] paramArrayOfByte, String paramString)
/*      */     throws SQLException
/*      */   {
/* 1702 */     throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)1));
/*      */   }
/*      */   
/*      */ 
/*      */   private void ldxsti(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1709 */     int[] arrayOfInt = new int[46];
/*      */     
/*      */     int j;
/* 1712 */     for (int i = 0; i < paramArrayOfByte.length; i += 2)
/*      */     {
/*      */ 
/* 1715 */       if (paramArrayOfByte[i] < 45)
/*      */       {
/* 1717 */         j = paramArrayOfByte[i];
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1722 */         if ((paramArrayOfByte[i] != 42) && (paramArrayOfByte[i] != 39) && (arrayOfInt[paramArrayOfByte[i]] != 0))
/*      */         {
/* 1724 */           throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)7));
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1729 */         arrayOfInt[paramArrayOfByte[i]] += 1;
/*      */         
/*      */ 
/* 1732 */         switch (paramArrayOfByte[i])
/*      */         {
/*      */         case 1: 
/*      */         case 2: 
/*      */         case 3: 
/*      */         case 5: 
/*      */         case 7: 
/*      */         case 9: 
/*      */         case 14: 
/*      */         case 15: 
/*      */         case 16: 
/*      */         case 18: 
/*      */         case 19: 
/*      */         case 20: 
/* 1746 */           throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)7));
/*      */         
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/* 1753 */         i += paramArrayOfByte[i] - 45;
/*      */       }
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
/* 1766 */     for (i = 0; i < this.ldxpmxa.length; i++)
/*      */     {
/* 1768 */       j = 0;
/*      */       
/* 1770 */       for (int k = 0; k < this.ldxpmxa[i].length; k++) {
/* 1771 */         j += arrayOfInt[this.ldxpmxa[i][k]];
/*      */       }
/* 1773 */       if (j > 1)
/*      */       {
/* 1775 */         throw new SQLException(oracle.core.lmx.CoreException.getMessage((byte)7));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2024 */   private static final char[][] ldxfda = { { 'A', '.', 'D', '.' }, { 'A', '.', 'M', '.' }, { 'A', 'D' }, { 'A', 'M' }, { 'B', '.', 'C', '.' }, { 'B', 'C' }, { 'C', 'C' }, { 'D' }, { 'D', 'A', 'Y' }, { 'D', 'D' }, { 'D', 'D', 'D' }, { 'D', 'Y' }, { 'E' }, { 'E', 'E' }, { 'F', 'M' }, { 'F', 'X' }, { 'H', 'H' }, { 'H', 'H', '1', '2' }, { 'H', 'H', '2', '4' }, { 'I' }, { 'I', 'W' }, { 'I', 'Y' }, { 'I', 'Y', 'Y' }, { 'I', 'Y', 'Y', 'Y' }, { 'J' }, { 'M', 'I' }, { 'M', 'M' }, { 'M', 'O', 'N' }, { 'M', 'O', 'N', 'T', 'H' }, { 'P', '.', 'M', '.' }, { 'P', 'M' }, { 'Q' }, { 'R', 'M' }, { 'R', 'R' }, { 'R', 'R', 'R', 'R' }, { 'S', 'C', 'C' }, { 'S', 'S' }, { 'S', 'S', 'S', 'S', 'S' }, { 'S', 'Y', ',', 'Y', 'Y', 'Y' }, { 'S', 'Y', 'E', 'A', 'R' }, { 'S', 'Y', 'Y', 'Y', 'Y' }, { 'W' }, { 'W', 'W' }, { 'Y' }, { 'Y', ',', 'Y', 'Y', 'Y' }, { 'Y', 'E', 'A', 'R' }, { 'Y', 'Y' }, { 'Y', 'Y', 'Y' }, { 'Y', 'Y', 'Y', 'Y' }, { '\000' } };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2080 */   private static final byte[] ldxfdc = { 37, 35, 36, 34, 37, 36, 1, 21, 32, 22, 23, 33, 43, 44, 39, 42, 25, 25, 24, 3, 18, 5, 7, 9, 29, 26, 17, 31, 30, 35, 34, 16, 38, 40, 41, 2, 27, 28, 13, 15, 12, 20, 19, 4, 11, 14, 6, 8, 10, 0 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2143 */   private static final byte[] ldxfcdlen = { 0, 2, 35, 1, 1, 2, 2, 3, 3, 4, 4, 21, 37, 54, -60, -27, 1, 2, 2, 2, 1, 1, 2, 3, 2, 2, 2, 2, 5, 7, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, -62, -60, -62, -60, -124, 0, 2, 4, 0, -113, -98, Byte.MIN_VALUE, Byte.MIN_VALUE };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2198 */   private static int[] ldxfdi = { 0, 4, 6, 7, 12, 14, Integer.MIN_VALUE, 16, 19, 24, Integer.MIN_VALUE, Integer.MIN_VALUE, 25, Integer.MIN_VALUE, Integer.MIN_VALUE, 29, 31, 32, 35, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 41, Integer.MIN_VALUE, 43, Integer.MIN_VALUE };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2228 */   private static final char[][] ldxfdx = { { 'S', 'P' }, { 'S', 'P', 'T', 'H' }, { 'T', 'H' }, { 'T', 'H', 'S', 'P' }, { '\000' } };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2237 */   private static final byte[] ldxfdxc = { 2, 3, 1, 3, 0 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2248 */   private static final byte[] NULLFMT = { 0, 16 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2253 */   private static final byte[] DEFAULT_FORMAT = { 22, 24, 46, 4, 47, 31, 24, 46, 4, 47, 10, 24 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2260 */   private static final String[] ldxpaa = { "A.D.", "A.M.", "B.C.", "P.M." };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2269 */   private static final int[] ldxdom = { 0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2287 */   private final byte[][] ldxpmxa = { { 23, 29 }, { 4, 6, 8, 10, 12, 11, 13 }, { 25, 24 }, { 34, 35 }, { 36, 37 }, { 30, 31, 17, 38 }, { 32, 33, 21 }, { 34, 35, 24 }, { 12, 13, 36, 37 } };
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/LdxLibThin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */