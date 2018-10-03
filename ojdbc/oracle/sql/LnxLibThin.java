/*      */ package oracle.sql;
/*      */ 
/*      */ import java.sql.SQLException;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.util.Locale;
/*      */ import oracle.core.lmx.CoreException;
/*      */ import oracle.jdbc.driver.ClassRef;
/*      */ import oracle.jdbc.driver.ClassRef.Locale;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class LnxLibThin
/*      */   implements LnxLib
/*      */ {
/*      */   public byte[] lnxabs(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  101 */     byte[] arrayOfByte = new byte[paramArrayOfByte.length];
/*      */     
/*      */ 
/*      */ 
/*  105 */     if (NUMBER._isPositive(paramArrayOfByte))
/*      */     {
/*      */ 
/*  108 */       System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, paramArrayOfByte.length);
/*  109 */       return arrayOfByte;
/*      */     }
/*      */     
/*      */ 
/*  113 */     if (NUMBER._isNegInf(paramArrayOfByte))
/*      */     {
/*  115 */       return NUMBER.posInf().shareBytes();
/*      */     }
/*      */     
/*      */ 
/*  119 */     int i = paramArrayOfByte.length;
/*  120 */     if (paramArrayOfByte[(i - 1)] == 102)
/*      */     {
/*  122 */       i--;
/*      */     }
/*      */     
/*  125 */     System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, i);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  130 */     _negateNumber(arrayOfByte);
/*      */     
/*  132 */     return _setLength(arrayOfByte, i);
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
/*      */   public byte[] lnxacos(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  146 */     return lnxqtri(paramArrayOfByte, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte[] lnxadd(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
/*      */     throws SQLException
/*      */   {
/*  154 */     int i = paramArrayOfByte1.length;
/*  155 */     int j = 0;
/*  156 */     int k = paramArrayOfByte2.length;
/*  157 */     int m = 0;
/*      */     
/*  159 */     byte[] arrayOfByte1 = new byte[41];
/*  160 */     int n = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  167 */     int i5 = 0;
/*  168 */     int i6 = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  179 */     int i15 = 0;
/*  180 */     int i16 = 0;
/*  181 */     int i17 = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  198 */     int i9 = n + 1;
/*      */     
/*      */ 
/*      */ 
/*  202 */     boolean bool1 = NUMBER._isPositive(paramArrayOfByte1);
/*  203 */     int i19 = paramArrayOfByte1[0] < 0 ? 256 + paramArrayOfByte1[0] : 255 - paramArrayOfByte1[0];
/*      */     
/*      */ 
/*      */ 
/*  207 */     if (!bool1)
/*      */     {
/*  209 */       if (paramArrayOfByte1[(i - 1)] == 102) {
/*  210 */         i--;
/*      */       }
/*      */     }
/*  213 */     int i18 = i - 1;
/*      */     
/*      */ 
/*      */ 
/*  217 */     boolean bool2 = NUMBER._isPositive(paramArrayOfByte2);
/*  218 */     int i21 = paramArrayOfByte2[0] < 0 ? 256 + paramArrayOfByte2[0] : 255 - paramArrayOfByte2[0];
/*      */     
/*  220 */     if (!bool2)
/*      */     {
/*  222 */       if (paramArrayOfByte2[(k - 1)] == 102)
/*  223 */         k--;
/*      */     }
/*  225 */     int i20 = k - 1;
/*      */     
/*      */     boolean bool3;
/*      */     
/*  229 */     if ((i19 == 255) && ((i18 == 0) || (paramArrayOfByte1[1] == 101)))
/*      */     {
/*      */ 
/*      */ 
/*  233 */       bool3 = bool1;
/*      */       
/*  235 */       if (bool3) {
/*  236 */         return NUMBER._makePosInf();
/*      */       }
/*  238 */       return NUMBER._makeNegInf();
/*      */     }
/*      */     
/*  241 */     if ((i21 == 255) && ((i20 == 0) || (paramArrayOfByte2[1] == 101)))
/*      */     {
/*      */ 
/*      */ 
/*  245 */       bool3 = bool2;
/*      */       
/*  247 */       if (bool3) {
/*  248 */         return NUMBER._makePosInf();
/*      */       }
/*  250 */       return NUMBER._makeNegInf();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  255 */     if ((i19 == 128) && (i18 == 0))
/*      */     {
/*      */ 
/*  258 */       arrayOfByte1 = new byte[k];
/*  259 */       System.arraycopy(paramArrayOfByte2, 0, arrayOfByte1, 0, k);
/*  260 */       bool3 = bool2;
/*  261 */       i26 = k;
/*      */       
/*  263 */       return _setLength(arrayOfByte1, i26);
/*      */     }
/*      */     
/*  266 */     if ((i21 == 128) && (i20 == 0))
/*      */     {
/*      */ 
/*  269 */       arrayOfByte1 = new byte[i];
/*  270 */       System.arraycopy(paramArrayOfByte1, 0, arrayOfByte1, 0, i);
/*  271 */       bool3 = bool1;
/*  272 */       i26 = i;
/*      */       
/*  274 */       return _setLength(arrayOfByte1, i26);
/*      */     }
/*      */     
/*      */ 
/*  278 */     int i14 = i19 - i21;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     byte[][] arrayOfByte;
/*      */     
/*      */ 
/*      */ 
/*      */     int i24;
/*      */     
/*      */ 
/*      */ 
/*      */     int i25;
/*      */     
/*      */ 
/*      */ 
/*      */     int i27;
/*      */     
/*      */ 
/*      */ 
/*      */     int i28;
/*      */     
/*      */ 
/*      */ 
/*  303 */     if (bool1 == bool2)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  308 */       bool3 = bool1;
/*      */       
/*      */ 
/*  311 */       if (bool3)
/*      */       {
/*      */ 
/*  314 */         arrayOfByte = LnxqAdd_PPP;
/*  315 */         i24 = 1;
/*  316 */         i25 = 1;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  321 */         arrayOfByte = LnxqAdd_NNN;
/*  322 */         i24 = 101;
/*  323 */         i25 = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  338 */       int i30 = i14;
/*      */       
/*      */ 
/*  341 */       if (i30 == 0)
/*      */       {
/*      */ 
/*      */ 
/*  345 */         i27 = j + 1;
/*  346 */         i28 = m + 1;
/*  347 */         int i29 = j + (i18 < i20 ? i18 : i20);
/*  348 */         while ((i27 <= i29) && (paramArrayOfByte1[i27] + paramArrayOfByte2[i28] == 102))
/*      */         {
/*      */ 
/*  351 */           i27++;
/*  352 */           i28++;
/*      */         }
/*      */         
/*      */ 
/*  356 */         if (i27 <= i29)
/*      */         {
/*      */ 
/*  359 */           i30 = bool1 ? paramArrayOfByte1[i27] + paramArrayOfByte2[i28] - 102 : 102 - (paramArrayOfByte1[i27] + paramArrayOfByte2[i28]);
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*      */ 
/*  367 */           i30 = i18 - i20;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  372 */       if (i30 == 0)
/*      */       {
/*      */ 
/*  375 */         return NUMBER._makeZero();
/*      */       }
/*      */       
/*      */ 
/*  379 */       bool3 = i30 > 0 ? bool1 : bool2;
/*      */       
/*      */ 
/*  382 */       if (bool3)
/*      */       {
/*      */ 
/*  385 */         arrayOfByte = LnxqAdd_PNP;
/*  386 */         i24 = 1;
/*  387 */         i25 = -1;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  392 */         arrayOfByte = LnxqAdd_PNN;
/*  393 */         i24 = 101;
/*  394 */         i25 = 1;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     int i22;
/*      */     
/*      */ 
/*      */     int i3;
/*      */     
/*      */ 
/*      */     int i4;
/*      */     
/*      */ 
/*      */     int i7;
/*      */     
/*      */ 
/*      */     int i8;
/*      */     
/*      */ 
/*      */     int i23;
/*      */     
/*      */ 
/*      */     int i13;
/*      */     
/*      */ 
/*  421 */     if (i14 >= 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  426 */       i22 = i19;
/*      */       
/*      */ 
/*  429 */       if (i14 + i20 <= i18)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  436 */         i15 = i14;
/*  437 */         i16 = i20;
/*  438 */         i17 = i18 - (i14 + i20);
/*      */         
/*      */ 
/*  441 */         i3 = j + i15;
/*  442 */         i4 = 1;
/*  443 */         i5 = i3 + i16;
/*  444 */         i6 = m + i20;
/*  445 */         i7 = j + i18;
/*  446 */         i8 = 1;
/*      */         
/*      */ 
/*  449 */         i23 = i18;
/*  450 */         i13 = (i17 != 0) && (bool1 != bool3) ? 1 : 0;
/*      */       }
/*  452 */       else if (i14 < i18)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  459 */         i15 = i14;
/*  460 */         i16 = i18 - i14;
/*  461 */         i17 = i20 - i16;
/*      */         
/*      */ 
/*  464 */         i3 = j + i15;
/*  465 */         i4 = 1;
/*  466 */         i5 = j + i18;
/*  467 */         i6 = m + i16;
/*  468 */         i7 = m + i20;
/*  469 */         i8 = 2;
/*      */         
/*      */ 
/*  472 */         i23 = i14 + i20;
/*  473 */         i13 = bool2 != bool3 ? 1 : 0;
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*  482 */         i15 = i18;
/*  483 */         i16 = -(i14 - i18);
/*  484 */         i17 = i20;
/*      */         
/*      */ 
/*  487 */         i3 = j + i18;
/*  488 */         i4 = 1;
/*  489 */         i7 = m + i20;
/*  490 */         i8 = 2;
/*      */         
/*      */ 
/*  493 */         i23 = i14 + i20;
/*  494 */         i13 = bool2 != bool3 ? 1 : 0;
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*  502 */       i22 = i21;
/*      */       
/*      */ 
/*  505 */       i14 = -i14;
/*  506 */       if (i14 + i18 <= i20)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  513 */         i15 = i14;
/*  514 */         i16 = i18;
/*  515 */         i17 = i20 - (i14 + i18);
/*      */         
/*      */ 
/*  518 */         i3 = m + i15;
/*  519 */         i4 = 2;
/*  520 */         i5 = j + i18;
/*  521 */         i6 = i3 + i16;
/*  522 */         i7 = m + i20;
/*  523 */         i8 = 2;
/*      */         
/*      */ 
/*  526 */         i23 = i20;
/*  527 */         i13 = (i17 != 0) && (bool2 != bool3) ? 1 : 0;
/*      */       }
/*  529 */       else if (i14 < i20)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  536 */         i15 = i14;
/*  537 */         i16 = i20 - i14;
/*  538 */         i17 = i18 - i16;
/*      */         
/*      */ 
/*  541 */         i3 = m + i15;
/*  542 */         i4 = 2;
/*  543 */         i5 = j + i16;
/*  544 */         i6 = m + i20;
/*  545 */         i7 = j + i18;
/*  546 */         i8 = 1;
/*      */         
/*      */ 
/*  549 */         i23 = i14 + i18;
/*  550 */         i13 = bool1 != bool3 ? 1 : 0;
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*  559 */         i15 = i20;
/*  560 */         i16 = -(i14 - i20);
/*  561 */         i17 = i18;
/*      */         
/*      */ 
/*  564 */         i3 = m + i20;
/*  565 */         i4 = 2;
/*  566 */         i7 = j + i18;
/*  567 */         i8 = 1;
/*      */         
/*      */ 
/*  570 */         i23 = i14 + i18;
/*  571 */         i13 = bool1 != bool3 ? 1 : 0;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  576 */     if (i23 > 20)
/*      */     {
/*  578 */       if (i14 > 20)
/*      */       {
/*      */ 
/*  581 */         i16 = 0;
/*  582 */         i17 = 0;
/*  583 */         i23 = i15;
/*  584 */         i13 = 0;
/*      */       }
/*      */       else
/*      */       {
/*  588 */         i9 = 1;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  593 */     int i10 = i9 + (i23 - 1);
/*  594 */     int i11 = i10;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     int i12;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  605 */     if (i17 != 0)
/*      */     {
/*      */ 
/*  608 */       i12 = i11 - i17;
/*      */       
/*      */ 
/*  611 */       if (i8 == 1) {
/*  612 */         arrayOfByte1[i11] = paramArrayOfByte1[i7];
/*      */       } else
/*  614 */         arrayOfByte1[i11] = paramArrayOfByte2[i7];
/*  615 */       i7--;
/*  616 */       i11--;
/*      */       
/*      */ 
/*  619 */       if (i13 != 0)
/*      */       {
/*      */ 
/*  622 */         while (i11 > i12)
/*      */         {
/*  624 */           if (i8 == 1) {
/*  625 */             arrayOfByte1[i11] = ((byte)(paramArrayOfByte1[i7] + i25));
/*      */           } else
/*  627 */             arrayOfByte1[i11] = ((byte)(paramArrayOfByte2[i7] + i25));
/*  628 */           i7--;
/*  629 */           i11--;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  635 */       while (i11 > i12)
/*      */       {
/*  637 */         if (i8 == 1) {
/*  638 */           arrayOfByte1[i11] = paramArrayOfByte1[i7];
/*      */         } else
/*  640 */           arrayOfByte1[i11] = paramArrayOfByte2[i7];
/*  641 */         i7--;
/*  642 */         i11--;
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
/*  654 */     if (i16 > 0)
/*      */     {
/*      */ 
/*      */ 
/*  658 */       i12 = i11 - i16;
/*      */       
/*      */ 
/*  661 */       int i1 = 0;
/*  662 */       int i2 = i13 != 0 ? i1 + 1 : i1;
/*      */       
/*      */ 
/*      */       do
/*      */       {
/*  667 */         i2 = i1 + paramArrayOfByte1[i5] + paramArrayOfByte2[i6] + arrayOfByte[i2][1];
/*      */         
/*      */ 
/*  670 */         arrayOfByte1[i11] = arrayOfByte[i2][0];
/*  671 */         i5--;
/*  672 */         i6--;
/*  673 */         i11--;
/*      */       }
/*  675 */       while (i11 > i12);
/*      */       
/*      */ 
/*  678 */       i13 = (arrayOfByte[i2][1] & 0x1) == 0 ? 0 : 1;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  683 */       i27 = i13 != 0 ? 100 : i25 == 1 ? 2 : i24;
/*      */       
/*      */ 
/*      */ 
/*  687 */       i12 = i11 + i16;
/*      */       
/*      */ 
/*  690 */       while (i11 > i12)
/*      */       {
/*  692 */         arrayOfByte1[i11] = ((byte)i27);
/*  693 */         i11--;
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
/*  705 */     if (i15 != 0)
/*      */     {
/*      */ 
/*  708 */       i12 = i11 - i15;
/*      */       
/*      */ 
/*  711 */       if (i13 != 0)
/*      */       {
/*      */ 
/*  714 */         i27 = (i25 == 1 ? 100 : 1) + (bool3 ? 0 : 1);
/*      */         
/*  716 */         i28 = (i25 == 1 ? 1 : 100) + (bool3 ? 0 : 1);
/*      */         
/*      */ 
/*      */         do
/*      */         {
/*  721 */           if (i4 == 1)
/*      */           {
/*  723 */             i13 = paramArrayOfByte1[i3] == i27 ? 1 : 0;
/*  724 */             arrayOfByte1[i11] = ((byte)(i13 != 0 ? i28 : paramArrayOfByte1[i3] + i25));
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*  729 */             i13 = paramArrayOfByte2[i3] == i27 ? 1 : 0;
/*  730 */             arrayOfByte1[i11] = ((byte)(i13 != 0 ? i28 : paramArrayOfByte2[i3] + i25));
/*      */           }
/*      */           
/*  733 */           i3--;
/*  734 */           i11--;
/*      */         }
/*  736 */         while ((i13 != 0) && (i11 > i12));
/*      */       }
/*      */       
/*      */ 
/*  740 */       while (i11 > i12)
/*      */       {
/*  742 */         if (i4 == 1) {
/*  743 */           arrayOfByte1[i11] = paramArrayOfByte1[i3];
/*      */         } else
/*  745 */           arrayOfByte1[i11] = paramArrayOfByte2[i3];
/*  746 */         i3--;
/*  747 */         i11--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  756 */     if (i13 != 0)
/*      */     {
/*      */ 
/*      */ 
/*  760 */       if (i22 == 255)
/*      */       {
/*  762 */         if (bool3) {
/*  763 */           return NUMBER._makePosInf();
/*      */         }
/*  765 */         return NUMBER._makeNegInf();
/*      */       }
/*      */       
/*      */ 
/*  769 */       i9--;
/*  770 */       arrayOfByte1[i9] = ((byte)(bool3 ? 2 : 100));
/*  771 */       i22++;
/*  772 */       i23++;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  777 */     if (arrayOfByte1[i9] == i24)
/*      */     {
/*      */ 
/*      */       do
/*      */       {
/*  782 */         i9++;
/*  783 */         i22--;
/*  784 */         i23--;
/*      */       }
/*  786 */       while (arrayOfByte1[i9] == i24);
/*      */       
/*      */ 
/*  789 */       if (i22 < 128) {
/*  790 */         return NUMBER._makeZero();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  798 */     if (i23 > 20)
/*      */     {
/*      */ 
/*  801 */       i10 = i9 + 19;
/*  802 */       i23 = 20;
/*      */       
/*      */ 
/*  805 */       if ((bool3 ? arrayOfByte1[(i10 + 1)] : LnxqNegate[arrayOfByte1[(i10 + 1)]]) > 50)
/*      */       {
/*      */ 
/*      */ 
/*  809 */         i27 = bool3 ? 100 : 2;
/*      */         
/*      */ 
/*  812 */         if (i13 == 0) {
/*  813 */           arrayOfByte1[(i9 - 1)] = ((byte)i24);
/*      */         }
/*      */         
/*  816 */         while (arrayOfByte1[i10] == i27)
/*      */         {
/*  818 */           i10--;
/*  819 */           i23--;
/*      */         }
/*      */         
/*  822 */         if (i10 < i9)
/*      */         {
/*  824 */           if (i22 == 255)
/*      */           {
/*  826 */             if (bool3) {
/*  827 */               return NUMBER._makePosInf();
/*      */             }
/*  829 */             return NUMBER._makeNegInf();
/*      */           }
/*  831 */           i9--;
/*  832 */           i22++;
/*  833 */           i23 = 1;
/*      */         }
/*      */         
/*  836 */         int tmp1810_1808 = i10; byte[] tmp1810_1806 = arrayOfByte1;tmp1810_1806[tmp1810_1808] = ((byte)(tmp1810_1806[tmp1810_1808] + (bool3 ? 1 : -1)));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  842 */     while (arrayOfByte1[i10] == i24)
/*      */     {
/*  844 */       i10--;
/*  845 */       i23--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  851 */     if (i9 != 1)
/*      */     {
/*  853 */       byte[] arrayOfByte2 = new byte[41];
/*  854 */       System.arraycopy(arrayOfByte1, i9, arrayOfByte2, 1, i23);
/*  855 */       System.arraycopy(arrayOfByte2, 1, arrayOfByte1, 1, i23);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  860 */     int i26 = i23 + 1;
/*      */     
/*  862 */     if (!bool3)
/*      */     {
/*      */ 
/*      */ 
/*  866 */       if (i26 <= 20)
/*      */       {
/*  868 */         arrayOfByte1[i26] = 102;
/*  869 */         i26++;
/*      */       }
/*      */     }
/*      */     
/*  873 */     arrayOfByte1[n] = ((byte)(bool3 ? i22 - 256 : 255 - i22));
/*  874 */     return _setLength(arrayOfByte1, i26);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxasin(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  887 */     return lnxqtri(paramArrayOfByte, 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxatan(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  899 */     return lnxqtri(paramArrayOfByte, 2);
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
/*      */   public byte[] lnxatan2(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
/*      */     throws SQLException
/*      */   {
/*  919 */     if ((NUMBER._isZero(paramArrayOfByte1)) && (NUMBER._isZero(paramArrayOfByte2)))
/*      */     {
/*      */ 
/*  922 */       throw new SQLException(CoreException.getMessage((byte)11));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  928 */     byte[] arrayOfByte1 = lnxdiv(paramArrayOfByte1, paramArrayOfByte2);
/*  929 */     arrayOfByte1 = lnxatan(arrayOfByte1);
/*      */     
/*  931 */     if (NUMBER._isPositive(paramArrayOfByte2))
/*      */     {
/*  933 */       return arrayOfByte1;
/*      */     }
/*      */     
/*  936 */     byte[] arrayOfByte2 = NUMBER.pi().shareBytes();
/*      */     
/*  938 */     if (NUMBER._isPositive(paramArrayOfByte1))
/*      */     {
/*      */ 
/*  941 */       return lnxadd(arrayOfByte1, arrayOfByte2);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  946 */     return lnxsub(arrayOfByte1, arrayOfByte2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxbex(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
/*      */     throws SQLException
/*      */   {
/*      */     byte[] arrayOfByte;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  961 */     switch (lnxsgn(paramArrayOfByte1))
/*      */     {
/*      */     case 1: 
/*  964 */       arrayOfByte = lnxln(paramArrayOfByte1);
/*  965 */       arrayOfByte = lnxmul(paramArrayOfByte2, arrayOfByte);
/*  966 */       return lnxexp(arrayOfByte);
/*      */     
/*      */     case 0: 
/*  969 */       if (NUMBER._isZero(paramArrayOfByte2))
/*      */       {
/*      */ 
/*  972 */         arrayOfByte = new byte[lnxqone.length];
/*  973 */         System.arraycopy(lnxqone, 0, arrayOfByte, 0, lnxqone.length);
/*  974 */         return arrayOfByte;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  979 */       return NUMBER._makeZero();
/*      */     
/*      */ 
/*      */     case -1: 
/*  983 */       if (new NUMBER(paramArrayOfByte2).isInt())
/*      */       {
/*      */ 
/*      */ 
/*  987 */         arrayOfByte = lnxneg(paramArrayOfByte1);
/*  988 */         arrayOfByte = lnxln(arrayOfByte);
/*  989 */         arrayOfByte = lnxmul(paramArrayOfByte2, arrayOfByte);
/*  990 */         arrayOfByte = lnxexp(arrayOfByte);
/*      */         
/*      */ 
/*  993 */         if (!NUMBER._isZero(lnxmod(paramArrayOfByte2, lnxqtwo)))
/*      */         {
/*  995 */           arrayOfByte = lnxneg(arrayOfByte);
/*      */         }
/*  997 */         return arrayOfByte;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1003 */       return NUMBER._makePosInf();
/*      */     }
/*      */     
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1011 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxcos(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1023 */     return lnxqtra(paramArrayOfByte, 3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxcsh(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1035 */     return lnxqtra(paramArrayOfByte, 6);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxdec(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1046 */     int m = paramArrayOfByte.length;
/* 1047 */     byte[] arrayOfByte1 = new byte[22];
/*      */     
/* 1049 */     System.arraycopy(paramArrayOfByte, 0, arrayOfByte1, 0, m);
/*      */     
/* 1051 */     if (NUMBER._isPositive(arrayOfByte1))
/*      */     {
/*      */ 
/* 1054 */       int i = (byte)((arrayOfByte1[0] & 0xFF7F) - 65);
/*      */       
/*      */ 
/*      */ 
/* 1058 */       if ((i >= 0) && (i <= 18))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1064 */         int j = i + 1;
/* 1065 */         int k = m - 1;
/*      */         
/*      */ 
/* 1068 */         if (j <= k)
/*      */         {
/*      */ 
/* 1071 */           int tmp70_69 = j; byte[] tmp70_67 = arrayOfByte1;tmp70_67[tmp70_69] = ((byte)(tmp70_67[tmp70_69] - 1));
/*      */           
/*      */ 
/* 1074 */           if ((arrayOfByte1[j] == 1) && (j == k))
/*      */           {
/* 1076 */             m--;
/* 1077 */             if (m == 1) {
/* 1078 */               return NUMBER._makeZero();
/*      */             }
/*      */             
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/* 1085 */           int tmp107_105 = k; byte[] tmp107_103 = arrayOfByte1;tmp107_103[tmp107_105] = ((byte)(tmp107_103[tmp107_105] - 1));
/*      */           
/*      */ 
/* 1088 */           while (j > k)
/*      */           {
/* 1090 */             arrayOfByte1[j] = 100;
/* 1091 */             j--;
/*      */           }
/*      */           
/*      */ 
/* 1095 */           if (arrayOfByte1[1] == 1)
/*      */           {
/* 1097 */             for (int n = 1; n <= i; n++)
/* 1098 */               arrayOfByte1[n] = arrayOfByte1[(n + 1)];
/* 1099 */             i--;
/*      */           }
/*      */           
/*      */ 
/* 1103 */           m = i + 2;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1108 */         arrayOfByte1[0] = ((byte)(i + 128 + 64 + 1));
/* 1109 */         byte[] arrayOfByte2 = new byte[m];
/* 1110 */         System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, m);
/*      */         
/* 1112 */         return arrayOfByte2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1117 */     return NUMBER._makeZero();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte[] lnxdiv(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
/*      */     throws SQLException
/*      */   {
/* 1125 */     byte[] arrayOfByte1 = paramArrayOfByte1;
/* 1126 */     int i = arrayOfByte1.length;
/* 1127 */     byte[] arrayOfByte2 = paramArrayOfByte2;
/* 1128 */     int j = arrayOfByte2.length;
/* 1129 */     byte[] arrayOfByte3 = new byte[22];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1136 */     int[] arrayOfInt1 = new int[22];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1142 */     int[] arrayOfInt2 = new int[10];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1151 */     int[] arrayOfInt3 = new int[13];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1162 */     int n = arrayOfByte1[0] >> 7 != 0 ? 1 : 0;
/* 1163 */     int i1 = arrayOfByte1[0];
/*      */     
/* 1165 */     if (n == 0)
/*      */     {
/* 1167 */       i1 = (byte)(i1 ^ 0xFFFFFFFF);
/* 1168 */       if (arrayOfByte1[(i - 1)] == 102) { i--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1173 */     int i4 = arrayOfByte2[0] >> 7 != 0 ? 1 : 0;
/* 1174 */     int i5 = arrayOfByte2[0];
/*      */     
/* 1176 */     if (i4 == 0)
/*      */     {
/* 1178 */       i5 = (byte)(i5 ^ 0xFFFFFFFF);
/* 1179 */       if (arrayOfByte2[(j - 1)] == 102) { j--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1185 */     if (((i5 & 0xFF) == 128) && (j == 1))
/*      */     {
/*      */ 
/* 1188 */       if (n == i4)
/*      */       {
/* 1190 */         return NUMBER._makePosInf();
/*      */       }
/* 1192 */       return NUMBER._makeNegInf();
/*      */     }
/*      */     
/*      */ 
/* 1196 */     if (((i1 & 0xFF) == 128) && (i == 1))
/*      */     {
/*      */ 
/* 1199 */       return NUMBER._makeZero();
/*      */     }
/*      */     
/*      */     int i17;
/* 1203 */     if (i == 1) i17 = 0; else i17 = 1;
/* 1204 */     if ((((i1 & 0xFF) == 255) && ((i == 2) || (arrayOfByte1[i17] == 101))) || ((i == 1) && (arrayOfByte1[0] == 0)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1209 */       if (n == i4)
/*      */       {
/* 1211 */         return NUMBER._makePosInf();
/*      */       }
/*      */       
/* 1214 */       return NUMBER._makeNegInf();
/*      */     }
/*      */     
/*      */ 
/* 1218 */     if (j == 1) i17 = 0; else i17 = 1;
/* 1219 */     if ((((i5 & 0xFF) == 255) && ((j == 2) || (arrayOfByte2[i17] == 101))) || ((j == 1) && (arrayOfByte2[0] == 0)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1224 */       return NUMBER._makeZero();
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
/* 1240 */     int i2 = i / 2 - 1;
/* 1241 */     int i3 = 21;
/* 1242 */     int k = i - 2;
/*      */     
/* 1244 */     while (i3 > i2)
/*      */     {
/* 1246 */       arrayOfInt1[i3] = 0;
/* 1247 */       i3--;
/*      */     }
/*      */     
/* 1250 */     if (n != 0)
/*      */     {
/*      */ 
/* 1253 */       if ((i & 0x1) == 0)
/*      */       {
/* 1255 */         arrayOfInt1[i3] = (arrayOfByte1[(k + 1)] * 100 - 100);
/* 1256 */         k--;
/* 1257 */         i3--;
/*      */       }
/*      */       
/* 1260 */       while (k > 0)
/*      */       {
/* 1262 */         arrayOfInt1[i3] = (arrayOfByte1[k] * 100 + arrayOfByte1[(k + 1)] - 101);
/* 1263 */         k -= 2;
/* 1264 */         i3--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1270 */     if ((i & 0x1) == 0)
/*      */     {
/* 1272 */       arrayOfInt1[i3] = (10100 - arrayOfByte1[(k + 1)] * 100);
/* 1273 */       k--;
/* 1274 */       i3--;
/*      */     }
/*      */     
/* 1277 */     while (k > 0)
/*      */     {
/* 1279 */       arrayOfInt1[i3] = (10201 - (arrayOfByte1[k] * 100 + arrayOfByte1[(k + 1)]));
/* 1280 */       k -= 2;
/* 1281 */       i3--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1287 */     int i6 = j / 2 - 1;
/* 1288 */     int i7 = i6;
/* 1289 */     int m = j - 2;
/*      */     
/* 1291 */     if (i4 != 0)
/*      */     {
/*      */ 
/* 1294 */       if ((j & 0x1) == 0)
/*      */       {
/* 1296 */         arrayOfInt2[i7] = (arrayOfByte2[(m + 1)] * 100 - 100);
/* 1297 */         m--;
/* 1298 */         i7--;
/*      */       }
/*      */       
/* 1301 */       while (m > 0)
/*      */       {
/* 1303 */         arrayOfInt2[i7] = (arrayOfByte2[m] * 100 + arrayOfByte2[(m + 1)] - 101);
/* 1304 */         m -= 2;
/* 1305 */         i7--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1311 */     if ((j & 0x1) == 0)
/*      */     {
/* 1313 */       arrayOfInt2[i7] = (10100 - arrayOfByte2[(m + 1)] * 100);
/* 1314 */       m--;
/* 1315 */       i7--;
/*      */     }
/*      */     
/* 1318 */     while (m > 0)
/*      */     {
/* 1320 */       arrayOfInt2[i7] = (10201 - (arrayOfByte2[m] * 100 + arrayOfByte2[(m + 1)]));
/* 1321 */       m -= 2;
/* 1322 */       i7--;
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
/* 1336 */     int i12 = 0;
/* 1337 */     int i13 = -1;
/*      */     
/*      */     int i20;
/*      */     
/* 1341 */     if (j <= 3)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1352 */       i3 = 0;
/*      */       
/*      */ 
/*      */ 
/* 1356 */       int i18 = arrayOfInt1[0];
/* 1357 */       i20 = arrayOfInt2[0];
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       do
/*      */       {
/* 1364 */         int i21 = i18 / i20;
/*      */         
/* 1366 */         i3++;
/* 1367 */         i18 -= i21 * i20;
/* 1368 */         i18 = i18 * 10000 + arrayOfInt1[i3];
/*      */         
/* 1370 */         i13++;
/* 1371 */         arrayOfInt3[i13] = i21;
/*      */         
/* 1373 */         if ((i18 == 0) && (i3 >= i2))
/*      */           break;
/* 1375 */       } while (i13 < 10 + (arrayOfInt3[0] == 0 ? 2 : 1));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1391 */       int i23 = 0;
/* 1392 */       int i24 = i6;
/*      */       
/*      */ 
/*      */ 
/* 1396 */       double d1 = arrayOfInt1[i23] * 10000 + arrayOfInt1[(i23 + 1)];
/* 1397 */       double d2 = arrayOfInt2[0] * 10000 + arrayOfInt2[1];
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       do
/*      */       {
/* 1404 */         int i22 = (int)(d1 / d2);
/*      */         
/* 1406 */         if (i22 != 0)
/*      */         {
/*      */ 
/* 1409 */           i3 = i23 + 2;
/* 1410 */           i7 = 2;
/* 1411 */           while (i3 <= i24)
/*      */           {
/* 1413 */             arrayOfInt1[i3] -= i22 * arrayOfInt2[i7];
/* 1414 */             i3++;
/* 1415 */             i7++;
/*      */           }
/*      */         }
/*      */         
/* 1419 */         d1 -= i22 * d2;
/* 1420 */         d1 = d1 * 10000.0D + arrayOfInt1[(i23 + 2)];
/*      */         
/* 1422 */         if (i22 >= 10000)
/*      */         {
/* 1424 */           i14 = i13;
/* 1425 */           while (arrayOfInt3[i14] == 9999)
/*      */           {
/* 1427 */             arrayOfInt3[i14] = 0;
/* 1428 */             i14--;
/*      */           }
/* 1430 */           arrayOfInt3[i14] += 1;
/* 1431 */           i22 -= 10000;
/*      */         }
/*      */         
/* 1434 */         if (i22 < 0)
/*      */         {
/* 1436 */           i14 = i13;
/* 1437 */           while (arrayOfInt3[i14] == 0)
/*      */           {
/* 1439 */             arrayOfInt3[i14] = 9999;
/* 1440 */             i14--;
/*      */           }
/* 1442 */           arrayOfInt3[i14] -= 1;
/* 1443 */           i22 += 10000;
/*      */         }
/*      */         
/* 1446 */         i13++;
/* 1447 */         arrayOfInt3[i13] = i22;
/*      */         
/*      */ 
/* 1450 */         if (i23 >= i2) { if ((d1 < 0.0D ? -d1 : d1) < 0.1D)
/*      */           {
/* 1452 */             i3 = i23 + 2;
/* 1453 */             while ((i3 <= i24) && (arrayOfInt1[i3] == 0))
/*      */             {
/* 1455 */               i3++;
/*      */             }
/* 1457 */             if (i3 > i24) {
/*      */               break;
/*      */             }
/*      */           }
/*      */         }
/*      */         
/* 1463 */         i23++;
/* 1464 */         i24++;
/*      */       }
/* 1466 */       while (i13 < 10 + (arrayOfInt3[0] == 0 ? 2 : 1));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1474 */     if (arrayOfInt3[0] == 0)
/*      */     {
/* 1476 */       i12++;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1481 */     while (arrayOfInt3[i13] == 0)
/*      */     {
/* 1483 */       i13--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1488 */     int i15 = arrayOfInt3[i12] >= 100 ? 1 : 0;
/* 1489 */     int i16 = arrayOfInt3[i13] % 100 != 0 ? 1 : 0;
/*      */     
/* 1491 */     int i10 = 2 * (i13 - i12) + i15 + i16;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1497 */     if (i10 > 20)
/*      */     {
/*      */ 
/*      */ 
/* 1501 */       if (i15 > 0)
/*      */       {
/* 1503 */         i13 = i12 + 9;
/* 1504 */         arrayOfInt3[i13] += (arrayOfInt3[(i13 + 1)] >= 5000 ? 1 : 0);
/*      */       }
/*      */       else
/*      */       {
/* 1508 */         i13 = i12 + 10;
/* 1509 */         arrayOfInt3[i13] = ((arrayOfInt3[i13] + 50) / 100 * 100);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1514 */       if (arrayOfInt3[i13] == 10000)
/*      */       {
/*      */         do
/*      */         {
/* 1518 */           i13--;
/*      */         }
/* 1520 */         while (arrayOfInt3[i13] == 9999);
/* 1521 */         arrayOfInt3[i13] += 1;
/*      */       }
/*      */       
/*      */ 
/* 1525 */       if (arrayOfInt3[0] != 0)
/*      */       {
/* 1527 */         i12 = 0;
/*      */       }
/*      */       
/*      */ 
/* 1531 */       while (arrayOfInt3[i13] == 0)
/*      */       {
/* 1533 */         i13--;
/*      */       }
/*      */       
/*      */ 
/* 1537 */       i15 = arrayOfInt3[i12] >= 100 ? 1 : 0;
/* 1538 */       i16 = arrayOfInt3[i13] % 100 != 0 ? 1 : 0;
/* 1539 */       i10 = 2 * (i13 - i12) + i15 + i16;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1545 */     int i8 = (i1 & 0xFF) - (i5 & 0xFF) - (arrayOfInt3[0] == 0 ? 1 : 0) + 193;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1550 */     if (i8 > 255)
/*      */     {
/*      */ 
/* 1553 */       if (n == i4)
/*      */       {
/* 1555 */         return NUMBER._makePosInf();
/*      */       }
/* 1557 */       return NUMBER._makeNegInf();
/*      */     }
/*      */     
/*      */ 
/* 1561 */     if (i8 < 128)
/*      */     {
/*      */ 
/* 1564 */       return NUMBER._makeZero();
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
/* 1578 */     int i9 = i10 + 1;
/* 1579 */     arrayOfByte3 = new byte[i9];
/*      */     
/*      */ 
/*      */ 
/* 1583 */     int i11 = i10;
/* 1584 */     int i14 = i13;
/*      */     
/* 1586 */     if (i16 == 0)
/*      */     {
/*      */ 
/* 1589 */       arrayOfByte3[i11] = ((byte)(arrayOfInt3[i14] / 100 + 1));
/* 1590 */       i11--;
/*      */       
/* 1592 */       i14--;
/*      */     }
/*      */     
/*      */     int i19;
/* 1596 */     while (i11 > 1)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1604 */       i19 = arrayOfInt3[i14] / 100;
/* 1605 */       i20 = arrayOfInt3[i14] - i19 * 100;
/* 1606 */       arrayOfByte3[i11] = ((byte)(i20 + 1));
/* 1607 */       i11--;
/* 1608 */       arrayOfByte3[i11] = ((byte)(i19 + 1));
/* 1609 */       i11--;
/*      */       
/*      */ 
/* 1612 */       i14--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1617 */     if (i15 == 0)
/*      */     {
/*      */ 
/* 1620 */       arrayOfByte3[i11] = ((byte)(arrayOfInt3[i14] + 1));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1625 */     arrayOfByte3[0] = ((byte)i8);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1630 */     if (n != i4)
/*      */     {
/*      */ 
/*      */ 
/* 1634 */       i9++;
/* 1635 */       byte[] arrayOfByte4; if (i9 > 20)
/*      */       {
/* 1637 */         arrayOfByte4 = new byte[21];
/* 1638 */         i9 = 21;
/*      */       }
/*      */       else {
/* 1641 */         arrayOfByte4 = new byte[i9];
/*      */       }
/* 1643 */       arrayOfByte4[0] = ((byte)(i8 ^ 0xFFFFFFFF));
/* 1644 */       for (i19 = 0; i19 < i9 - 2; i19++)
/*      */       {
/* 1646 */         arrayOfByte4[(i19 + 1)] = ((byte)(102 - arrayOfByte3[(i19 + 1)]));
/*      */       }
/* 1648 */       if (i9 <= 20) {
/* 1649 */         arrayOfByte4[(i9 - 1)] = 102;
/*      */ 
/*      */ 
/*      */       }
/* 1653 */       else if (arrayOfByte3.length == 20) {
/* 1654 */         arrayOfByte4[(i9 - 1)] = 102;
/*      */       } else {
/* 1656 */         arrayOfByte4[(i19 + 1)] = ((byte)(102 - arrayOfByte3[(i19 + 1)]));
/*      */       }
/* 1658 */       return arrayOfByte4;
/*      */     }
/*      */     
/* 1661 */     return arrayOfByte3;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxexp(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1674 */     return lnxqtra(paramArrayOfByte, 9);
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
/*      */   public byte[] lnxflo(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1688 */     byte[] arrayOfByte = lnxtru(paramArrayOfByte, 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1694 */     if ((NUMBER.compareBytes(arrayOfByte, paramArrayOfByte) != 0) && (!NUMBER._isPositive(paramArrayOfByte))) {
/* 1695 */       arrayOfByte = lnxsub(arrayOfByte, lnxqone);
/*      */     }
/* 1697 */     return arrayOfByte;
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
/*      */   public byte[] lnxceil(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1711 */     byte[] arrayOfByte = lnxtru(paramArrayOfByte, 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1716 */     if ((NUMBER.compareBytes(arrayOfByte, paramArrayOfByte) != 0) && (NUMBER._isPositive(paramArrayOfByte))) {
/* 1717 */       arrayOfByte = lnxadd(arrayOfByte, lnxqone);
/*      */     }
/* 1719 */     return arrayOfByte;
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
/*      */   public byte[] lnxfpr(byte[] paramArrayOfByte, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1737 */     int i3 = paramArrayOfByte.length;
/*      */     
/*      */ 
/*      */ 
/* 1741 */     if (NUMBER._isZero(paramArrayOfByte))
/*      */     {
/* 1743 */       return NUMBER._makeZero();
/*      */     }
/* 1745 */     if (NUMBER._isNegInf(paramArrayOfByte))
/*      */     {
/* 1747 */       return NUMBER._makeNegInf();
/*      */     }
/* 1749 */     if (NUMBER._isPosInf(paramArrayOfByte))
/*      */     {
/* 1751 */       return NUMBER._makePosInf();
/*      */     }
/*      */     
/*      */ 
/* 1755 */     if (paramInt < 0)
/*      */     {
/* 1757 */       return NUMBER._makeZero(); }
/*      */     boolean bool;
/*      */     int k;
/* 1760 */     int m; int n; int i1; int i2; if ((bool = NUMBER._isPositive(paramArrayOfByte)))
/*      */     {
/* 1762 */       paramInt += ((paramArrayOfByte[1] & 0xFF) < 11 ? 2 : 1);
/* 1763 */       k = paramInt >> 1;
/* 1764 */       m = (paramInt & 0x1) == 1 ? 1 : 0;
/* 1765 */       n = 1;
/* 1766 */       i1 = 100;
/* 1767 */       i2 = 1;
/*      */     }
/*      */     else
/*      */     {
/* 1771 */       paramInt += ((paramArrayOfByte[1] & 0xFF) > 91 ? 2 : 1);
/* 1772 */       k = paramInt >> 1;
/* 1773 */       m = (paramInt & 0x1) == 1 ? 1 : 0;
/* 1774 */       n = 101;
/* 1775 */       i1 = 2;
/* 1776 */       i2 = -1;
/* 1777 */       i3 -= ((paramArrayOfByte[(i3 - 1)] & 0xFF) == 102 ? 1 : 0);
/*      */     }
/*      */     
/*      */ 
/* 1781 */     byte[] arrayOfByte = new byte[i3];
/* 1782 */     System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, i3);
/*      */     
/*      */ 
/*      */ 
/* 1786 */     if ((k > i3 - 1) || ((k == i3 - 1) && ((m != 0) || (LnxqFirstDigit[paramArrayOfByte[k]] == 1))))
/*      */     {
/*      */ 
/*      */ 
/* 1790 */       return _setLength(paramArrayOfByte, i3);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1796 */     if ((k != 0) || ((m != 0) && (bool ? paramArrayOfByte[1] >= 51 : paramArrayOfByte[1] <= 51))) { if ((k != 1) || (m != 0) || (bool ? paramArrayOfByte[1] >= 6 : paramArrayOfByte[1] <= 96)) {}
/*      */ 
/*      */     }
/*      */     else {
/* 1800 */       return NUMBER._makeZero();
/*      */     }
/*      */     
/* 1803 */     if (k == 0)
/*      */     {
/*      */ 
/* 1806 */       if (NUMBER._isInf(paramArrayOfByte))
/*      */       {
/* 1808 */         if (bool)
/*      */         {
/* 1810 */           return NUMBER._makePosInf();
/*      */         }
/*      */         
/*      */ 
/* 1814 */         return NUMBER._makeNegInf();
/*      */       }
/*      */       
/*      */ 
/* 1818 */       arrayOfByte[0] = ((byte)(paramArrayOfByte[0] + i2));
/* 1819 */       arrayOfByte[1] = ((byte)(n + i2));
/*      */       
/* 1821 */       return _setLength(arrayOfByte, 2);
/*      */     }
/*      */     
/* 1824 */     int i = j = (byte)k;
/*      */     
/* 1826 */     if (m != 0)
/*      */     {
/*      */ 
/* 1829 */       if (bool ? paramArrayOfByte[(j + 1)] > 50 : paramArrayOfByte[(j + 1)] < 52)
/*      */       {
/*      */ 
/* 1832 */         arrayOfByte[i] = ((byte)(paramArrayOfByte[j] + i2));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1837 */         arrayOfByte[i] = paramArrayOfByte[j];
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else {
/* 1843 */       arrayOfByte[i] = (bool ? LnxqRound_P[paramArrayOfByte[j]] : LnxqRound_N[paramArrayOfByte[j]]);
/*      */     }
/*      */     
/* 1846 */     int j = (byte)(j - 1);
/*      */     
/*      */     int i4;
/*      */     
/* 1850 */     if (arrayOfByte[i] == i1 + i2)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1855 */       while ((j > 0) && (paramArrayOfByte[j] == i1))
/*      */       {
/* 1857 */         j = (byte)(j - 1);
/*      */       }
/*      */       
/*      */ 
/* 1861 */       if (j == 0)
/*      */       {
/*      */ 
/* 1864 */         if (NUMBER._isInf(paramArrayOfByte))
/*      */         {
/* 1866 */           if (bool)
/*      */           {
/* 1868 */             return NUMBER._makePosInf();
/*      */           }
/*      */           
/*      */ 
/* 1872 */           return NUMBER._makeNegInf();
/*      */         }
/*      */         
/*      */ 
/* 1876 */         arrayOfByte[0] = ((byte)(paramArrayOfByte[0] + i2));
/* 1877 */         arrayOfByte[1] = ((byte)(n + i2));
/*      */         
/* 1879 */         return _setLength(arrayOfByte, 2);
/*      */       }
/*      */       
/*      */ 
/* 1883 */       arrayOfByte[j] = ((byte)(paramArrayOfByte[j] + i2));
/*      */       
/*      */ 
/* 1886 */       i4 = j + 1;
/*      */       
/*      */ 
/* 1889 */       j = (byte)(j - 1);
/*      */     }
/* 1891 */     else if (arrayOfByte[i] == n)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1896 */       while (paramArrayOfByte[j] == n)
/*      */       {
/* 1898 */         j = (byte)(j - 1);
/*      */       }
/*      */       
/*      */ 
/* 1902 */       i4 = j + 1;
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 1909 */       i4 = k + 1;
/*      */     }
/*      */     
/* 1912 */     return _setLength(arrayOfByte, i4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxinc(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1924 */     int n = paramArrayOfByte.length;
/* 1925 */     byte[] arrayOfByte1 = new byte[22];
/*      */     
/*      */ 
/* 1928 */     System.arraycopy(paramArrayOfByte, 0, arrayOfByte1, 0, n);
/* 1929 */     int i = 0;
/*      */     
/*      */ 
/* 1932 */     int m = (byte)((arrayOfByte1[0] & 0xFF7F) - 65);
/*      */     
/*      */ 
/* 1935 */     if ((m >= 0) && (m <= 18))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1941 */       int j = (byte)(m + 1);
/* 1942 */       int k = (byte)(n - 1);
/*      */       
/*      */ 
/*      */ 
/* 1946 */       if (j <= k)
/*      */       {
/*      */ 
/*      */ 
/* 1950 */         if (arrayOfByte1[j] < 100)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/* 1955 */           int tmp79_78 = j; byte[] tmp79_76 = arrayOfByte1;tmp79_76[tmp79_78] = ((byte)(tmp79_76[tmp79_78] + 1));
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 1962 */           arrayOfByte1[i] = 0;
/*      */           
/*      */ 
/*      */           do
/*      */           {
/* 1967 */             j = (byte)(j - 1);
/*      */           }
/* 1969 */           while (arrayOfByte1[j] == 100);
/*      */           
/*      */ 
/* 1972 */           if (j > i)
/*      */           {
/*      */ 
/* 1975 */             int tmp115_114 = j; byte[] tmp115_112 = arrayOfByte1;tmp115_112[tmp115_114] = ((byte)(tmp115_112[tmp115_114] + 1));
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/* 1980 */             m++;
/* 1981 */             j = (byte)(j + 1);
/* 1982 */             arrayOfByte1[j] = 2;
/*      */           }
/*      */           
/*      */ 
/* 1986 */           arrayOfByte1[i] = ((byte)(m + 128 + 64 + 1));
/*      */           
/*      */ 
/* 1989 */           n = j - i + 1;
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 1997 */         arrayOfByte1[j] = 2;
/*      */         
/*      */ 
/* 2000 */         j = (byte)(j - 1);
/* 2001 */         while (j > k)
/*      */         {
/* 2003 */           arrayOfByte1[j] = 1;
/* 2004 */           j = (byte)(j - 1);
/*      */         }
/*      */         
/*      */ 
/* 2008 */         n = m + 2;
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 2016 */       arrayOfByte1[0] = -63;
/* 2017 */       arrayOfByte1[1] = 2;
/*      */       
/*      */ 
/* 2020 */       n = 2;
/*      */     }
/*      */     
/*      */ 
/* 2024 */     byte[] arrayOfByte2 = new byte[n];
/* 2025 */     System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, n);
/*      */     
/* 2027 */     return arrayOfByte2;
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
/*      */   public byte[] lnxln(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 2047 */     if (lnxsgn(paramArrayOfByte) <= 0) { return NUMBER._makeNegInf();
/*      */     }
/*      */     
/* 2050 */     if (NUMBER._isPosInf(paramArrayOfByte)) { return NUMBER._makePosInf();
/*      */     }
/*      */     
/* 2053 */     byte[] arrayOfByte1 = new byte[paramArrayOfByte.length];
/* 2054 */     System.arraycopy(paramArrayOfByte, 0, arrayOfByte1, 0, paramArrayOfByte.length);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2059 */     int i = (arrayOfByte1[0] & 0xFF) - 193;
/* 2060 */     arrayOfByte1[0] = -63;
/* 2061 */     double d1 = lnxnur(arrayOfByte1);
/*      */     
/*      */ 
/* 2064 */     double d2 = Math.log(d1);
/* 2065 */     byte[] arrayOfByte2 = NUMBER.toBytes(d2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2079 */     byte[] arrayOfByte3 = lnxexp(arrayOfByte2);
/* 2080 */     byte[] arrayOfByte4 = lnxdiv(arrayOfByte1, arrayOfByte3);
/* 2081 */     arrayOfByte4 = lnxsub(arrayOfByte4, lnxqone);
/*      */     
/*      */ 
/*      */ 
/* 2085 */     byte[] arrayOfByte5 = new byte[arrayOfByte4.length];
/* 2086 */     System.arraycopy(arrayOfByte4, 0, arrayOfByte5, 0, arrayOfByte4.length);
/*      */     
/*      */ 
/* 2089 */     byte[] arrayOfByte6 = lnxmul(arrayOfByte4, arrayOfByte4);
/*      */     
/* 2091 */     int j = 1;
/* 2092 */     while ((arrayOfByte6[0] & 0xFF) > 172)
/*      */     {
/* 2094 */       j++;
/* 2095 */       arrayOfByte3 = lnxqIDiv(arrayOfByte6, j);
/* 2096 */       arrayOfByte5 = lnxsub(arrayOfByte5, arrayOfByte3);
/* 2097 */       arrayOfByte6 = lnxmul(arrayOfByte4, arrayOfByte6);
/* 2098 */       j++;
/* 2099 */       arrayOfByte3 = lnxqIDiv(arrayOfByte6, j);
/* 2100 */       arrayOfByte5 = lnxadd(arrayOfByte5, arrayOfByte3);
/* 2101 */       arrayOfByte6 = lnxmul(arrayOfByte4, arrayOfByte6);
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
/* 2114 */     i *= 2;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2121 */     byte[] arrayOfByte7 = NUMBER.ln10().shareBytes();
/* 2122 */     arrayOfByte4 = lnxmin(i);
/* 2123 */     arrayOfByte3 = lnxmul(arrayOfByte4, arrayOfByte7);
/*      */     
/* 2125 */     arrayOfByte3 = lnxadd(arrayOfByte3, arrayOfByte2);
/* 2126 */     return lnxadd(arrayOfByte3, arrayOfByte5);
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
/*      */   public byte[] lnxlog(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
/*      */     throws SQLException
/*      */   {
/* 2143 */     double d = NUMBER.toDouble(paramArrayOfByte2);
/*      */     
/*      */ 
/*      */ 
/* 2147 */     if (d > 0.0D)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2153 */       if (d == 10.0D)
/*      */       {
/*      */ 
/* 2156 */         arrayOfByte1 = lnxln(paramArrayOfByte1);
/* 2157 */         byte[] arrayOfByte3 = NUMBER.ln10().shareBytes();
/* 2158 */         return lnxdiv(arrayOfByte1, arrayOfByte3);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 2163 */       byte[] arrayOfByte1 = lnxln(paramArrayOfByte1);
/* 2164 */       byte[] arrayOfByte2 = lnxln(paramArrayOfByte2);
/* 2165 */       return lnxdiv(arrayOfByte1, arrayOfByte2);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2171 */     return NUMBER._makeNegInf();
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
/*      */   public byte[] lnxmod(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
/*      */     throws SQLException
/*      */   {
/* 2185 */     byte[] arrayOfByte1 = lnxdiv(paramArrayOfByte1, paramArrayOfByte2);
/*      */     
/*      */ 
/* 2188 */     byte[] arrayOfByte2 = lnxtru(arrayOfByte1, 0);
/*      */     
/* 2190 */     arrayOfByte1 = lnxmul(paramArrayOfByte2, arrayOfByte2);
/* 2191 */     byte[] arrayOfByte3 = lnxsub(paramArrayOfByte1, arrayOfByte1);
/* 2192 */     return arrayOfByte3;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxmul(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
/*      */     throws SQLException
/*      */   {
/* 2201 */     Object localObject1 = paramArrayOfByte1;
/* 2202 */     int i = localObject1.length;
/* 2203 */     Object localObject2 = paramArrayOfByte2;
/* 2204 */     int j = localObject2.length;
/* 2205 */     byte[] arrayOfByte1 = new byte[22];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2212 */     int[] arrayOfInt1 = new int[10];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2218 */     int[] arrayOfInt2 = new int[10];
/*      */     
/*      */ 
/*      */ 
/* 2222 */     int i8 = 0;
/*      */     
/* 2224 */     byte[] arrayOfByte2 = new byte[41];
/* 2225 */     int i10 = 0;
/*      */     
/* 2227 */     int i12 = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2232 */     int n = localObject1[0] >> 7 != 0 ? 1 : 0;
/* 2233 */     int i1 = localObject1[0];
/*      */     
/* 2235 */     if (n == 0)
/*      */     {
/* 2237 */       i1 = (byte)(i1 ^ 0xFFFFFFFF);
/* 2238 */       if (localObject1[(i - 1)] == 102) { i--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 2243 */     int i4 = localObject2[0] >> 7 != 0 ? 1 : 0;
/* 2244 */     int i5 = localObject2[0];
/*      */     
/* 2246 */     if (i4 == 0)
/*      */     {
/* 2248 */       i5 = (byte)(i5 ^ 0xFFFFFFFF);
/* 2249 */       if (localObject2[(j - 1)] == 102) { j--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2255 */     if ((-i1 == 128) && (i == 1))
/*      */     {
/*      */ 
/*      */ 
/* 2259 */       arrayOfByte1 = NUMBER._makeZero();
/* 2260 */       return arrayOfByte1;
/*      */     }
/*      */     
/* 2263 */     if ((-i5 == 128) && (j == 1))
/*      */     {
/*      */ 
/* 2266 */       arrayOfByte1 = NUMBER._makeZero();
/* 2267 */       return arrayOfByte1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2272 */     if (((i1 & 0xFF) == 255) && ((i == 1) || (localObject1[1] == 101)))
/*      */     {
/*      */ 
/* 2275 */       if (n == i4)
/*      */       {
/* 2277 */         arrayOfByte1 = NUMBER._makePosInf();
/*      */       } else {
/* 2279 */         arrayOfByte1 = NUMBER._makeNegInf();
/*      */       }
/* 2281 */       return arrayOfByte1;
/*      */     }
/* 2283 */     if (((i5 & 0xFF) == 255) && ((j == 1) || (localObject2[1] == 101)))
/*      */     {
/*      */ 
/* 2286 */       if (n == i4)
/*      */       {
/* 2288 */         arrayOfByte1 = NUMBER._makePosInf();
/*      */       } else {
/* 2290 */         arrayOfByte1 = NUMBER._makeNegInf();
/*      */       }
/*      */       
/* 2293 */       return arrayOfByte1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2300 */     if (i > j)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2307 */       Object localObject3 = localObject1;
/* 2308 */       localObject1 = localObject2;
/* 2309 */       localObject2 = localObject3;
/*      */       
/*      */ 
/* 2312 */       int i14 = i;
/* 2313 */       i = j;
/* 2314 */       j = i14;
/*      */       
/*      */ 
/* 2317 */       int i15 = n;
/* 2318 */       n = i4;
/* 2319 */       i4 = i15;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2325 */     int i2 = i / 2 - 1;
/* 2326 */     int i3 = i2;
/* 2327 */     int k = i - 2;
/*      */     
/* 2329 */     if (n != 0)
/*      */     {
/*      */ 
/* 2332 */       if ((i & 0x1) == 0)
/*      */       {
/* 2334 */         arrayOfInt1[i3] = (localObject1[(k + 1)] * 100 - 100);
/* 2335 */         k--;
/* 2336 */         i3--;
/*      */       }
/*      */       
/* 2339 */       while (k > 0)
/*      */       {
/* 2341 */         arrayOfInt1[i3] = (localObject1[k] * 100 + localObject1[(k + 1)] - 101);
/* 2342 */         k -= 2;
/* 2343 */         i3--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2349 */     if ((i & 0x1) == 0)
/*      */     {
/* 2351 */       arrayOfInt1[i3] = (10100 - localObject1[(k + 1)] * 100);
/* 2352 */       k--;
/* 2353 */       i3--;
/*      */     }
/*      */     
/* 2356 */     while (k > 0)
/*      */     {
/* 2358 */       arrayOfInt1[i3] = (10201 - (localObject1[k] * 100 + localObject1[(k + 1)]));
/* 2359 */       k -= 2;
/* 2360 */       i3--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2366 */     int i6 = j / 2 - 1;
/* 2367 */     int i7 = i6;
/* 2368 */     int m = j - 2;
/*      */     
/* 2370 */     if (i4 != 0)
/*      */     {
/*      */ 
/* 2373 */       if ((j & 0x1) == 0)
/*      */       {
/* 2375 */         arrayOfInt2[i7] = (localObject2[(m + 1)] * 100 - 100);
/* 2376 */         m--;
/* 2377 */         i7--;
/*      */       }
/*      */       
/* 2380 */       while (m > 0)
/*      */       {
/* 2382 */         arrayOfInt2[i7] = (localObject2[m] * 100 + localObject2[(m + 1)] - 101);
/* 2383 */         m -= 2;
/* 2384 */         i7--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2390 */     if ((j & 0x1) == 0)
/*      */     {
/* 2392 */       arrayOfInt2[i7] = (10100 - localObject2[(m + 1)] * 100);
/* 2393 */       m--;
/* 2394 */       i7--;
/*      */     }
/*      */     
/* 2397 */     while (m > 0)
/*      */     {
/* 2399 */       arrayOfInt2[i7] = (10201 - (localObject2[m] * 100 + localObject2[(m + 1)]));
/* 2400 */       m -= 2;
/* 2401 */       i7--;
/*      */     }
/*      */     
/*      */ 
/*      */     int i9;
/*      */     
/* 2407 */     if (arrayOfInt1[0] * arrayOfInt2[0] < 1000000)
/*      */     {
/* 2409 */       i8 = (short)((i1 & 0xFF) + (i5 & 0xFF) - 193);
/* 2410 */       i9 = (i & 0xFE) + (j & 0xFE);
/*      */     }
/*      */     else
/*      */     {
/* 2414 */       i8 = (short)((i1 & 0xFF) + (i5 & 0xFF) - 192);
/* 2415 */       i9 = (i & 0xFE) + (j & 0xFE) + 1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2420 */     i10 = 1;
/* 2421 */     int i11 = i9;
/*      */     
/*      */ 
/*      */ 
/* 2425 */     if (i <= 3)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2431 */       i12 = arrayOfInt1[0] * arrayOfInt2[i6];
/*      */       
/* 2433 */       i12 = LnxmulSetDigit1(arrayOfByte2, i11, i12);i11 -= 2;
/*      */       
/*      */ 
/* 2436 */       i7 = i6 - 1;
/*      */       
/* 2438 */       while (i7 >= 0)
/*      */       {
/*      */ 
/* 2441 */         i12 += arrayOfInt1[0] * arrayOfInt2[i7];
/* 2442 */         i12 = LnxmulSetDigit1(arrayOfByte2, i11, i12);i11 -= 2;
/*      */         
/* 2444 */         i7--;
/*      */       }
/*      */       
/*      */ 
/* 2448 */       LnxmulSetDigit2(arrayOfByte2, i11, i12);i11 -= 2;
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2458 */       i12 += arrayOfInt1[i2] * arrayOfInt2[i6];
/*      */       
/* 2460 */       i12 = LnxmulSetDigit1(arrayOfByte2, i11, i12);i11 -= 2;
/*      */       
/*      */ 
/*      */ 
/* 2464 */       i7 = i6 - 1;
/*      */       
/* 2466 */       while (i7 > i6 - (i / 2 - 1))
/*      */       {
/*      */ 
/* 2469 */         switch (i6 - i7 + 1) {
/*      */         case 9: 
/* 2471 */           i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 8, i12);
/*      */         case 8: 
/* 2473 */           i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 7, i12);
/*      */         case 7: 
/* 2475 */           i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 6, i12);
/*      */         case 6: 
/* 2477 */           i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 5, i12);
/*      */         case 5: 
/* 2479 */           i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 4, i12);
/*      */         case 4: 
/* 2481 */           i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 3, i12);
/*      */         case 3: 
/* 2483 */           i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 2, i12);
/*      */         case 2: 
/* 2485 */           i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 1, i12);
/*      */         }
/* 2487 */         i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 0, i12);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 2492 */         i12 = LnxmulSetDigit1(arrayOfByte2, i11, i12);i11 -= 2;
/*      */         
/* 2494 */         i7--;
/*      */       }
/*      */       
/*      */ 
/*      */       do
/*      */       {
/* 2500 */         switch (i / 2)
/*      */         {
/*      */         case 10: 
/* 2503 */           i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 9, i12);
/* 2504 */         case 9:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 8, i12);
/* 2505 */         case 8:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 7, i12);
/* 2506 */         case 7:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 6, i12);
/* 2507 */         case 6:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 5, i12);
/* 2508 */         case 5:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 4, i12);
/* 2509 */         case 4:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 3, i12);
/* 2510 */         case 3:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 2, i12);
/* 2511 */         case 2:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 1, i12); }
/* 2512 */         i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i2, i7, 0, i12);
/*      */         
/*      */ 
/*      */ 
/* 2516 */         i12 = LnxmulSetDigit1(arrayOfByte2, i11, i12);i11 -= 2;
/*      */         
/*      */ 
/* 2519 */         i7--;
/*      */ 
/*      */       }
/* 2522 */       while (i7 >= 0);
/*      */       
/*      */ 
/*      */ 
/* 2526 */       i3 = i2 - 1;
/*      */       
/* 2528 */       while (i3 > 0)
/*      */       {
/*      */ 
/*      */ 
/* 2532 */         switch (i3 + 1) {
/*      */         case 9: 
/* 2534 */           i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i3, 0, 8, i12);
/* 2535 */         case 8:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i3, 0, 7, i12);
/* 2536 */         case 7:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i3, 0, 6, i12);
/* 2537 */         case 6:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i3, 0, 5, i12);
/* 2538 */         case 5:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i3, 0, 4, i12);
/* 2539 */         case 4:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i3, 0, 3, i12);
/* 2540 */         case 3:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i3, 0, 2, i12);
/* 2541 */         case 2:  i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i3, 0, 1, i12); }
/* 2542 */         i12 = LnxmulSetSum(arrayOfInt1, arrayOfInt2, i3, 0, 0, i12);
/*      */         
/*      */ 
/*      */ 
/* 2546 */         i12 = LnxmulSetDigit1(arrayOfByte2, i11, i12);i11 -= 2;
/*      */         
/* 2548 */         i3--;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 2553 */       i12 += arrayOfInt1[0] * arrayOfInt2[0];
/*      */       
/* 2555 */       i12 = LnxmulSetDigit1(arrayOfByte2, i11, i12);i11 -= 2;
/*      */       
/*      */ 
/*      */ 
/* 2559 */       LnxmulSetDigit2(arrayOfByte2, i11, i12);i11 -= 2;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2565 */     if (((i9 & 0x1) == 0) && (arrayOfByte2[i11] != 1))
/*      */     {
/* 2567 */       i8 = (short)(i8 + 1);
/* 2568 */       i9++;
/* 2569 */       i10--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2574 */     while (arrayOfByte2[(i10 + i9 - 2)] == 1)
/*      */     {
/* 2576 */       i9--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2581 */     if (i9 > 21)
/*      */     {
/*      */ 
/* 2584 */       i11 = i10 + 19;
/* 2585 */       i9 = 21;
/*      */       
/*      */ 
/* 2588 */       if (arrayOfByte2[(i11 + 1)] > 50)
/*      */       {
/*      */ 
/* 2591 */         while (arrayOfByte2[i11] == 100)
/*      */         {
/* 2593 */           i11--;
/* 2594 */           i9--;
/*      */         }
/*      */         
/* 2597 */         if (i11 < i10)
/*      */         {
/* 2599 */           arrayOfByte2[i10] = 2;
/* 2600 */           i8 = (short)(i8 + 1);
/* 2601 */           i9++;
/*      */         }
/*      */         
/*      */ 
/* 2605 */         int tmp1764_1762 = i11; byte[] tmp1764_1760 = arrayOfByte2;tmp1764_1760[tmp1764_1762] = ((byte)(tmp1764_1760[tmp1764_1762] + 1));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 2610 */         while (arrayOfByte2[(i10 + i9 - 2)] == 1)
/*      */         {
/* 2612 */           i9--;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2619 */     if ((i8 & 0xFFFF) > 255)
/*      */     {
/*      */ 
/* 2622 */       if (n == i4)
/*      */       {
/* 2624 */         return NUMBER._makePosInf();
/*      */       }
/*      */       
/*      */ 
/* 2628 */       return NUMBER._makeNegInf();
/*      */     }
/*      */     
/* 2631 */     if ((i8 & 0xFFFF) < 128)
/*      */     {
/*      */ 
/*      */ 
/* 2635 */       return NUMBER._makeZero();
/*      */     }
/*      */     
/*      */     int i13;
/*      */     
/* 2640 */     if (n != i4)
/*      */     {
/* 2642 */       i9++;
/* 2643 */       arrayOfByte1 = new byte[i9];
/* 2644 */       arrayOfByte1[0] = ((byte)(i8 ^ 0xFFFFFFFF));
/* 2645 */       for (i13 = 0; i13 < i9 - 1; i13++)
/*      */       {
/* 2647 */         arrayOfByte1[(i13 + 1)] = ((byte)(102 - arrayOfByte2[(i10 + i13)]));
/*      */       }
/* 2649 */       arrayOfByte1[(i9 - 1)] = 102;
/*      */     } else {
/* 2651 */       arrayOfByte1 = new byte[i9];
/* 2652 */       arrayOfByte1[0] = ((byte)i8);
/* 2653 */       for (i13 = 0; i13 < i9 - 1; i13++)
/*      */       {
/* 2655 */         arrayOfByte1[(i13 + 1)] = arrayOfByte2[(i10 + i13)];
/*      */       }
/*      */     }
/*      */     
/* 2659 */     return arrayOfByte1;
/*      */   }
/*      */   
/*      */ 
/*      */   private static int LnxmulSetSum(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/* 2666 */     int i = 0;
/*      */     try
/*      */     {
/* 2669 */       i = paramInt4 + paramArrayOfInt1[(paramInt1 - paramInt3)] * paramArrayOfInt2[(paramInt2 + paramInt3)];
/*      */     }
/*      */     catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
/*      */     {
/* 2673 */       throw new SQLException(CoreException.getMessage((byte)4));
/*      */     }
/*      */     
/* 2676 */     return i;
/*      */   }
/*      */   
/*      */   private static int LnxmulSetDigit1(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/* 2681 */     int i = paramInt2 / 100;
/* 2682 */     int j = paramInt2 / 10000;
/* 2683 */     paramInt1 -= 2;
/* 2684 */     paramArrayOfByte[(paramInt1 + 1)] = ((byte)(paramInt2 - i * 100 + 1));
/* 2685 */     paramArrayOfByte[paramInt1] = ((byte)(i - j * 100 + 1));
/* 2686 */     return j;
/*      */   }
/*      */   
/*      */   private static void LnxmulSetDigit2(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
/* 2690 */     int i = paramInt2 / 100;
/* 2691 */     paramInt1 -= 2;
/* 2692 */     paramArrayOfByte[paramInt1] = ((byte)(i + 1));
/* 2693 */     paramArrayOfByte[(paramInt1 + 1)] = ((byte)(paramInt2 - i * 100 + 1));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte[] lnxneg(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 2701 */     if (NUMBER._isZero(paramArrayOfByte))
/*      */     {
/* 2703 */       return NUMBER._makeZero();
/*      */     }
/* 2705 */     if (NUMBER._isPosInf(paramArrayOfByte))
/*      */     {
/* 2707 */       return NUMBER._makeNegInf();
/*      */     }
/* 2709 */     if (NUMBER._isNegInf(paramArrayOfByte))
/*      */     {
/* 2711 */       return NUMBER._makePosInf();
/*      */     }
/*      */     
/*      */ 
/* 2715 */     int i = paramArrayOfByte.length;
/* 2716 */     if ((!NUMBER._isPositive(paramArrayOfByte)) && (paramArrayOfByte[(i - 1)] == 102)) {
/* 2717 */       i--;
/*      */     }
/* 2719 */     byte[] arrayOfByte = new byte[i];
/*      */     
/* 2721 */     System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, i);
/*      */     
/* 2723 */     _negateNumber(arrayOfByte);
/*      */     
/* 2725 */     return _setLength(arrayOfByte, i);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxpow(byte[] paramArrayOfByte, int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     byte[] arrayOfByte1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2743 */     if (paramInt >= 0)
/*      */     {
/*      */ 
/* 2746 */       arrayOfByte1 = new byte[paramArrayOfByte.length];
/* 2747 */       System.arraycopy(paramArrayOfByte, 0, arrayOfByte1, 0, paramArrayOfByte.length);
/*      */     }
/*      */     else
/*      */     {
/* 2751 */       int i = Integer.MIN_VALUE;
/*      */       
/*      */ 
/* 2754 */       if (paramInt == i)
/*      */       {
/* 2756 */         arrayOfByte1 = lnxpow(paramArrayOfByte, i + 1);
/* 2757 */         return lnxdiv(arrayOfByte1, paramArrayOfByte);
/*      */       }
/*      */       
/*      */ 
/* 2761 */       paramInt = -paramInt;
/*      */       
/*      */ 
/* 2764 */       arrayOfByte1 = lnxdiv(lnxqone, paramArrayOfByte);
/*      */     }
/*      */     
/*      */ 
/* 2768 */     byte[] arrayOfByte2 = lnxqone;
/*      */     
/*      */ 
/* 2771 */     while (paramInt > 0)
/*      */     {
/*      */ 
/* 2774 */       if ((paramInt & 0x1) == 1)
/*      */       {
/*      */ 
/* 2777 */         arrayOfByte2 = lnxmul(arrayOfByte2, arrayOfByte1);
/*      */       }
/*      */       
/* 2780 */       if (paramInt >>= 1 > 0)
/*      */       {
/*      */ 
/* 2783 */         arrayOfByte1 = lnxmul(arrayOfByte1, arrayOfByte1);
/*      */       }
/*      */     }
/*      */     
/* 2787 */     return arrayOfByte2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte[] lnxrou(byte[] paramArrayOfByte, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2795 */     int i = paramArrayOfByte.length;
/* 2796 */     int j = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2810 */     if (i == 1)
/*      */     {
/* 2812 */       if (paramArrayOfByte[j] == Byte.MIN_VALUE) {
/* 2813 */         return NUMBER._makeZero();
/*      */       }
/* 2815 */       return NUMBER._makeNegInf();
/*      */     }
/*      */     
/*      */ 
/* 2819 */     if (i == 2)
/*      */     {
/* 2821 */       if ((paramArrayOfByte[0] == -1) && (paramArrayOfByte[1] == 101))
/*      */       {
/* 2823 */         return NUMBER._makePosInf();
/*      */       }
/*      */     }
/* 2826 */     int i6 = paramArrayOfByte[0] < 0 ? 256 + paramArrayOfByte[0] : paramArrayOfByte[0];
/*      */     boolean bool;
/*      */     int i2;
/* 2829 */     int i3; int m; int n; int i1; if ((bool = NUMBER._isPositive(paramArrayOfByte)))
/*      */     {
/* 2831 */       if (paramInt >= 0)
/*      */       {
/* 2833 */         i2 = i6 + (paramInt + 1 >> 1) - 192;
/*      */         
/* 2835 */         i3 = (paramInt & 0x1) == 0 ? 0 : 1;
/*      */       }
/*      */       else
/*      */       {
/* 2839 */         paramInt = -paramInt;
/* 2840 */         i2 = i6 - (paramInt >> 1) - 192;
/*      */         
/* 2842 */         i3 = (paramInt & 0x1) == 0 ? 0 : 1;
/*      */       }
/* 2844 */       m = 1;
/* 2845 */       n = 100;
/* 2846 */       i1 = 1;
/*      */     }
/*      */     else
/*      */     {
/* 2850 */       if (paramInt >= 0)
/*      */       {
/* 2852 */         i2 = 63 + (paramInt + 1 >> 1) - i6;
/*      */         
/* 2854 */         i3 = (paramInt & 0x1) == 0 ? 0 : 1;
/*      */       }
/*      */       else
/*      */       {
/* 2858 */         paramInt = -paramInt;
/* 2859 */         i2 = 63 - (paramInt >> 1) - i6;
/*      */         
/* 2861 */         i3 = (paramInt & 0x1) == 0 ? 0 : 1;
/*      */       }
/* 2863 */       m = 101;
/* 2864 */       n = 2;
/* 2865 */       i1 = -1;
/*      */       
/*      */ 
/* 2868 */       i -= (paramArrayOfByte[(i - 1)] == 102 ? 1 : 0);
/*      */     }
/*      */     
/*      */ 
/* 2872 */     byte[] arrayOfByte = new byte[i];
/* 2873 */     System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, i);
/*      */     
/*      */ 
/*      */ 
/* 2877 */     if ((i2 > i - 1) || ((i2 == i - 1) && ((i3 == 0) || (LnxqFirstDigit[paramArrayOfByte[i2]] == 1))))
/*      */     {
/*      */ 
/*      */ 
/* 2881 */       return _setLength(paramArrayOfByte, i);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2887 */     if ((i2 >= 0) && ((i2 != 0) || ((i3 == 0) && (bool ? paramArrayOfByte[1] >= 51 : paramArrayOfByte[1] <= 51)))) { if ((i2 != 1) || (i3 == 0) || (bool ? paramArrayOfByte[1] >= 6 : paramArrayOfByte[1] <= 96)) {}
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 2892 */       return NUMBER._makeZero();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2897 */     if (i2 == 0)
/*      */     {
/*      */ 
/* 2900 */       if (bool ? paramArrayOfByte[j] == -1 : paramArrayOfByte[j] == 0)
/*      */       {
/* 2902 */         if (bool) {
/* 2903 */           return NUMBER._makePosInf();
/*      */         }
/* 2905 */         return NUMBER._makeNegInf();
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 2910 */       arrayOfByte[0] = ((byte)(paramArrayOfByte[j] + i1));
/* 2911 */       arrayOfByte[1] = ((byte)(m + i1));
/*      */       
/* 2913 */       return _setLength(arrayOfByte, 2);
/*      */     }
/*      */     
/*      */     int i4;
/*      */     
/* 2918 */     int k = i4 = (byte)i2;
/*      */     
/* 2920 */     if (i3 != 0)
/*      */     {
/*      */ 
/* 2923 */       arrayOfByte[i4] = (bool ? LnxqRound_P[paramArrayOfByte[k]] : LnxqRound_N[paramArrayOfByte[k]]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/* 2929 */     else if (bool ? paramArrayOfByte[(k + 1)] > 50 : paramArrayOfByte[(k + 1)] < 52)
/*      */     {
/*      */ 
/* 2932 */       arrayOfByte[i4] = ((byte)(paramArrayOfByte[k] + i1));
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 2937 */       arrayOfByte[i4] = paramArrayOfByte[k];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2943 */     k = (byte)(k - 1);
/*      */     
/*      */     int i5;
/*      */     
/* 2947 */     if (arrayOfByte[i4] == n + i1)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2952 */       while ((k > j) && (paramArrayOfByte[k] == n)) {
/* 2953 */         k = (byte)(k - 1);
/*      */       }
/*      */       
/* 2956 */       if (k == j)
/*      */       {
/*      */ 
/* 2959 */         if (bool ? paramArrayOfByte[j] == -1 : paramArrayOfByte[j] == 0)
/*      */         {
/*      */ 
/* 2962 */           if (bool) {
/* 2963 */             return NUMBER._makePosInf();
/*      */           }
/* 2965 */           return NUMBER._makeNegInf();
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 2970 */         arrayOfByte[0] = ((byte)(paramArrayOfByte[j] + i1));
/* 2971 */         arrayOfByte[1] = ((byte)(m + i1));
/*      */         
/* 2973 */         return _setLength(arrayOfByte, 2);
/*      */       }
/*      */       
/*      */ 
/* 2977 */       arrayOfByte[(k - j)] = ((byte)(paramArrayOfByte[k] + i1));
/*      */       
/*      */ 
/* 2980 */       i5 = k - j + 1;
/*      */       
/*      */ 
/* 2983 */       k = (byte)(k - 1);
/*      */     }
/* 2985 */     else if (arrayOfByte[i4] == m)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2990 */       while (paramArrayOfByte[k] == m) {
/* 2991 */         k = (byte)(k - 1);
/*      */       }
/*      */       
/* 2994 */       i5 = k - j + 1;
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 3001 */       i5 = i2 + 1;
/*      */     }
/*      */     
/*      */ 
/* 3005 */     return _setLength(arrayOfByte, i5);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxsca(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean[] paramArrayOfBoolean)
/*      */     throws SQLException
/*      */   {
/* 3017 */     int m = paramArrayOfByte.length;
/*      */     
/* 3019 */     if (m != 1)
/*      */     {
/*      */       int i;
/*      */       int j;
/*      */       int k;
/* 3024 */       if (NUMBER._isPositive(paramArrayOfByte))
/*      */       {
/* 3026 */         i = (byte)((paramArrayOfByte[0] & 0xFF7F) - 65);
/* 3027 */         j = paramArrayOfByte[1];
/* 3028 */         k = paramArrayOfByte[(m - 1)];
/*      */       }
/*      */       else
/*      */       {
/* 3032 */         m--;
/* 3033 */         i = (byte)(((paramArrayOfByte[0] ^ 0xFFFFFFFF) & 0xFF7F) - 65);
/* 3034 */         j = LnxqNegate[paramArrayOfByte[1]];
/* 3035 */         k = LnxqNegate[paramArrayOfByte[(m - 1)]];
/*      */       }
/*      */       
/*      */ 
/* 3039 */       if (2 * (m - i) - (k % 10 == 1 ? 1 : 0) > paramInt2)
/*      */       {
/*      */ 
/* 3042 */         byte[] arrayOfByte1 = lnxrou(paramArrayOfByte, paramInt2);
/*      */         
/* 3044 */         if (NUMBER._isPositive(arrayOfByte1))
/*      */         {
/* 3046 */           i = (byte)((arrayOfByte1[0] & 0xFF7F) - 65);
/* 3047 */           j = arrayOfByte1.length != 1 ? arrayOfByte1[1] : 1;
/*      */         }
/*      */         else
/*      */         {
/* 3051 */           i = (byte)(((arrayOfByte1[0] ^ 0xFFFFFFFF) & 0xFF7F) - 65);
/* 3052 */           j = LnxqNegate[arrayOfByte1[1]];
/*      */         }
/*      */         
/*      */ 
/* 3056 */         paramArrayOfBoolean[0] = (2 * (i + 1) - (j < 11 ? 1 : 0) > paramInt1 ? 1 : false);
/*      */         
/* 3058 */         return arrayOfByte1;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 3063 */       int n = paramArrayOfByte.length;
/*      */       
/* 3065 */       byte[] arrayOfByte2 = new byte[n];
/* 3066 */       System.arraycopy(paramArrayOfByte, 0, arrayOfByte2, 0, n);
/*      */       
/*      */ 
/* 3069 */       paramArrayOfBoolean[0] = (2 * (i + 1) - (j < 11 ? 1 : 0) > paramInt1 ? 1 : false);
/*      */       
/* 3071 */       return arrayOfByte2;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3079 */     paramArrayOfBoolean[0] = false;
/* 3080 */     return NUMBER._makeZero();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxshift(byte[] paramArrayOfByte, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3090 */     byte[] arrayOfByte1 = paramArrayOfByte;
/* 3091 */     int i = arrayOfByte1.length;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3096 */     byte[] arrayOfByte3 = new byte[22];
/*      */     
/* 3098 */     int j = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3109 */     int i3 = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3114 */     if (i == 1) i4 = 0; else i4 = 1;
/* 3115 */     if ((((arrayOfByte1[0] & 0xFF) == 128) && (i == 1)) || ((i == 2) && ((arrayOfByte1[0] & 0xFF) == 255) && (arrayOfByte1[i4] == 101)) || ((i == 1) && (arrayOfByte1[0] == 0)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3124 */       arrayOfByte2 = new byte[i];
/* 3125 */       for (i4 = 0; i4 < i; i4++) arrayOfByte2[i4] = arrayOfByte1[i4];
/* 3126 */       return arrayOfByte2;
/*      */     }
/*      */     
/* 3129 */     int k = arrayOfByte1[0] >> 7 == 0 ? 1 : 0;
/*      */     
/*      */ 
/*      */ 
/* 3133 */     int n = k != 0 ? 255 - arrayOfByte1[0] & 0xFF : arrayOfByte1[0] & 0xFF;
/* 3134 */     int i1 = i;
/*      */     
/*      */ 
/* 3137 */     if ((paramInt & 0x1) > 0)
/*      */     {
/*      */       byte[][] arrayOfByte4;
/*      */       
/*      */       byte[][] arrayOfByte5;
/*      */       int i2;
/* 3143 */       if (k != 0)
/*      */       {
/* 3145 */         if (arrayOfByte1[(i1 - 1)] == 102) i1--;
/* 3146 */         arrayOfByte4 = LnxqComponents_N;
/* 3147 */         arrayOfByte5 = LnxqDigit_N;
/* 3148 */         i2 = 101;
/*      */       }
/*      */       else
/*      */       {
/* 3152 */         arrayOfByte4 = LnxqComponents_P;
/* 3153 */         arrayOfByte5 = LnxqDigit_P;
/* 3154 */         i2 = 1;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 3159 */       if (arrayOfByte4[arrayOfByte1[1]][0] != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3165 */         n = paramInt >= 0 ? n + (paramInt / 2 + 1) : n - -paramInt / 2;
/*      */         
/*      */ 
/* 3168 */         j = i1 - 2;
/* 3169 */         i3 = i1 - 1;
/*      */         
/*      */         int m;
/* 3172 */         if (i1 > 20)
/*      */         {
/* 3174 */           m = arrayOfByte4[arrayOfByte1[(j + 1)]][1] >= 5 ? 1 : 0;
/*      */         }
/*      */         else
/*      */         {
/* 3178 */           arrayOfByte3[(i3 + 1)] = arrayOfByte5[arrayOfByte4[arrayOfByte1[(j + 1)]][1]][0];
/* 3179 */           i1++;
/* 3180 */           m = 0;
/*      */         }
/*      */         
/*      */ 
/* 3184 */         while (j > 0)
/*      */         {
/* 3186 */           arrayOfByte3[i3] = arrayOfByte5[arrayOfByte4[arrayOfByte1[(j + 0)]][1]][arrayOfByte4[arrayOfByte1[(j + 1)]][0]];
/* 3187 */           j--;
/* 3188 */           i3--;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 3193 */         arrayOfByte3[1] = arrayOfByte5[0][arrayOfByte4[arrayOfByte1[(j + 1)]][0]];
/*      */         
/*      */ 
/* 3196 */         if (m != 0)
/*      */         {
/*      */ 
/* 3199 */           int i5 = k != 0 ? 2 : 100;
/* 3200 */           int i6 = k != 0 ? -1 : 1;
/*      */           
/* 3202 */           i3 = 20;
/* 3203 */           while (arrayOfByte3[i3] == i5)
/*      */           {
/* 3205 */             i3--;
/* 3206 */             i1--;
/*      */           }
/*      */           
/* 3209 */           int tmp459_457 = i3; byte[] tmp459_455 = arrayOfByte3;tmp459_455[tmp459_457] = ((byte)(tmp459_455[tmp459_457] + i6));
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/* 3220 */         n = paramInt >= 0 ? n + paramInt / 2 : n - (-paramInt / 2 + 1);
/*      */         
/*      */ 
/* 3223 */         j = 1;
/* 3224 */         i3 = 1;
/*      */         
/*      */ 
/* 3227 */         while (i3 < i1 - 1)
/*      */         {
/* 3229 */           arrayOfByte3[i3] = arrayOfByte5[arrayOfByte4[arrayOfByte1[(j + 0)]][1]][arrayOfByte4[arrayOfByte1[(j + 1)]][0]];
/* 3230 */           j++;
/* 3231 */           i3++;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 3236 */         arrayOfByte3[i3] = arrayOfByte5[arrayOfByte4[arrayOfByte1[(j + 0)]][1]][0];
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 3242 */       while (arrayOfByte3[(i1 - 1)] == i2)
/*      */       {
/* 3244 */         i1--;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 3249 */       if (k != 0)
/*      */       {
/* 3251 */         i1++;
/* 3252 */         arrayOfByte3[(i1 - 1)] = 102;
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 3262 */       n = paramInt >= 0 ? n + paramInt / 2 : n - -paramInt / 2;
/*      */       
/*      */ 
/* 3265 */       for (i4 = 1; i4 < i1; i4++)
/*      */       {
/* 3267 */         arrayOfByte3[i4] = arrayOfByte1[i4];
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3273 */     if (n > 255)
/*      */     {
/*      */ 
/* 3276 */       if (k != 0)
/*      */       {
/* 3278 */         arrayOfByte2 = new byte[1];
/* 3279 */         arrayOfByte2[0] = 0;
/*      */       } else {
/* 3281 */         arrayOfByte2 = new byte[2];
/* 3282 */         arrayOfByte2[0] = -1;arrayOfByte2[1] = 101;
/*      */       }
/* 3284 */       return arrayOfByte2;
/*      */     }
/* 3286 */     if (n < 128)
/*      */     {
/*      */ 
/* 3289 */       arrayOfByte2 = new byte[1];
/* 3290 */       arrayOfByte2[0] = Byte.MIN_VALUE;
/* 3291 */       return arrayOfByte2;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3297 */     arrayOfByte3[0] = (k != 0 ? (byte)(255 - n) : (byte)n);
/* 3298 */     byte[] arrayOfByte2 = new byte[i1];
/* 3299 */     for (int i4 = 0; i4 < i1; i4++)
/*      */     {
/* 3301 */       arrayOfByte2[i4] = arrayOfByte3[i4];
/*      */     }
/* 3303 */     return arrayOfByte2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxsin(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 3315 */     return lnxqtra(paramArrayOfByte, 4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxsnh(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 3327 */     return lnxqtra(paramArrayOfByte, 7);
/*      */   }
/*      */   
/*      */ 
/*      */   public byte[] lnxsqr(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 3334 */     int i = paramArrayOfByte.length;
/* 3335 */     int[] arrayOfInt1 = new int[29];
/* 3336 */     int[] arrayOfInt2 = new int[29];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3359 */     if (!NUMBER._isPositive(paramArrayOfByte)) {
/* 3360 */       return NUMBER._makeNegInf();
/*      */     }
/*      */     
/* 3363 */     if (NUMBER._isPosInf(paramArrayOfByte)) {
/* 3364 */       return NUMBER._makePosInf();
/*      */     }
/*      */     
/* 3367 */     if (NUMBER._isZero(paramArrayOfByte)) {
/* 3368 */       return NUMBER._makeZero();
/*      */     }
/*      */     
/* 3371 */     int i8 = (paramArrayOfByte[0] & 0xFF) - 193;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3378 */     for (int i9 = 1; i9 < i; i9++) {
/* 3379 */       paramArrayOfByte[i9] -= 1;
/*      */     }
/*      */     
/* 3382 */     int i4 = 1;
/* 3383 */     int i5 = i4 + 20 + 3;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3388 */     if ((i8 + 128 & 0x1) != 0)
/*      */     {
/* 3390 */       k = ((arrayOfInt1[i4] * 100 + arrayOfInt1[(i4 + 1)]) * 100 + arrayOfInt1[(i4 + 2)]) * 100 + arrayOfInt1[(i4 + 3)];
/*      */       
/* 3392 */       i4 += 3;
/*      */     }
/*      */     else
/*      */     {
/* 3396 */       k = (arrayOfInt1[i4] * 100 + arrayOfInt1[(i4 + 1)]) * 100 + arrayOfInt1[(i4 + 2)];
/*      */       
/* 3398 */       i4 += 2;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3403 */     int j = (int)(Math.sqrt(k) * 100.0D);
/*      */     
/*      */ 
/* 3406 */     arrayOfInt2[1] = (j / 10000);
/* 3407 */     arrayOfInt2[2] = (j / 100 % 100);
/* 3408 */     arrayOfInt2[3] = (j % 100);
/*      */     
/*      */ 
/*      */ 
/* 3412 */     k -= arrayOfInt2[1] * j;
/* 3413 */     int k = k * 100 + arrayOfInt1[(i4 + 1)];
/* 3414 */     k -= arrayOfInt2[2] * j;
/* 3415 */     k = k * 100 + arrayOfInt1[(i4 + 2)];
/* 3416 */     k -= arrayOfInt2[3] * j;
/*      */     
/* 3418 */     i4 += 3;
/*      */     
/*      */ 
/* 3421 */     j *= 2;
/*      */     
/*      */ 
/* 3424 */     int n = 3;
/* 3425 */     int i1 = n + 1;
/*      */     
/* 3427 */     while (i4 < i5)
/*      */     {
/*      */ 
/* 3430 */       k = k * 100 + arrayOfInt1[i4];
/*      */       
/*      */ 
/* 3433 */       int m = k / j;
/*      */       
/*      */ 
/* 3436 */       k -= m * j;
/*      */       
/*      */ 
/* 3439 */       arrayOfInt2[i1] = m;
/*      */       
/*      */ 
/* 3442 */       i2 = n + (i5 - i4) < i1 ? n + (i5 - i4) : i1;
/*      */       
/*      */       int i6;
/*      */       
/* 3446 */       if (m != 0)
/*      */       {
/*      */ 
/* 3449 */         i6 = i4 + 1;
/* 3450 */         int i7 = n + 1;
/* 3451 */         while (i7 < i2)
/*      */         {
/* 3453 */           arrayOfInt1[i6] -= 2 * m * arrayOfInt2[i7];
/* 3454 */           i6++;
/* 3455 */           i7++;
/*      */         }
/* 3457 */         if (i6 < i5) {
/* 3458 */           arrayOfInt1[i6] -= m * m;
/*      */         }
/* 3460 */       } else if (k == 0)
/*      */       {
/*      */ 
/* 3463 */         i6 = i4 + 1;
/* 3464 */         while ((i6 < i5) && (arrayOfInt1[i6] == 0)) {
/* 3465 */           i6++;
/*      */         }
/* 3467 */         if (i6 == i5)
/*      */           break;
/*      */       }
/* 3470 */       i4++;
/* 3471 */       i1++;
/*      */     }
/*      */     
/*      */ 
/* 3475 */     int i2 = i1;
/* 3476 */     i1--;
/*      */     
/*      */ 
/* 3479 */     arrayOfInt2[0] = 0;
/*      */     
/* 3481 */     while (i1 > 0)
/*      */     {
/* 3483 */       while (arrayOfInt2[i1] > 99)
/*      */       {
/* 3485 */         arrayOfInt2[i1] -= 100;
/* 3486 */         arrayOfInt2[(i1 - 1)] += 1;
/*      */       }
/* 3488 */       while (arrayOfInt2[i1] < 0)
/*      */       {
/* 3490 */         arrayOfInt2[i1] += 100;
/* 3491 */         arrayOfInt2[(i1 - 1)] -= 1;
/*      */       }
/* 3493 */       i1--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3499 */     i8 = (i8 - (i8 + 128 & 0x1)) / 2 + 1;
/*      */     
/*      */ 
/* 3502 */     while (arrayOfInt2[i1] == 0)
/*      */     {
/* 3504 */       i1++;
/* 3505 */       i8--;
/* 3506 */       if (i8 < -65) {
/* 3507 */         return NUMBER._makeZero();
/*      */       }
/*      */     }
/*      */     
/*      */     do
/*      */     {
/* 3513 */       i2--;
/*      */     }
/* 3515 */     while (arrayOfInt2[i2] == 0);
/*      */     
/*      */ 
/* 3518 */     int i3 = i2 - i1 + 2;
/* 3519 */     if (i3 > 21)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3528 */       i2 = i1 + 20;
/* 3529 */       if (arrayOfInt2[i2] >= 50)
/*      */       {
/*      */ 
/*      */         do
/*      */         {
/* 3534 */           i2--;
/*      */         }
/* 3536 */         while (arrayOfInt2[i2] == 99);
/* 3537 */         arrayOfInt2[i2] += 1;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */         do
/*      */         {
/* 3544 */           i2--;
/*      */         }
/* 3546 */         while (arrayOfInt2[i2] == 0);
/*      */       }
/*      */       
/*      */ 
/* 3550 */       if (i2 < i1)
/*      */       {
/* 3552 */         i1 = i2;
/* 3553 */         i8++;
/* 3554 */         if (i8 > 62) {
/* 3555 */           return NUMBER._makePosInf();
/*      */         }
/*      */       }
/* 3558 */       i3 = i2 - i1 + 2;
/*      */     }
/*      */     
/*      */ 
/* 3562 */     byte[] arrayOfByte = new byte[i3];
/*      */     
/*      */ 
/* 3565 */     arrayOfByte[0] = ((byte)(i8 - 63));
/*      */     
/*      */ 
/*      */ 
/* 3569 */     for (i9 = i1; i9 <= i2; i9++) {
/* 3570 */       arrayOfByte[(i9 - (i1 - 1))] = ((byte)(arrayOfInt2[i9] + 1));
/*      */     }
/*      */     
/* 3573 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxsub(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
/*      */     throws SQLException
/*      */   {
/* 3584 */     return lnxadd(paramArrayOfByte1, lnxneg(paramArrayOfByte2));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxtan(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 3596 */     return lnxqtra(paramArrayOfByte, 5);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxtnh(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 3608 */     return lnxqtra(paramArrayOfByte, 8);
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
/*      */   public byte[] lnxtru(byte[] paramArrayOfByte, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3622 */     int i1 = paramArrayOfByte.length;
/*      */     
/*      */ 
/*      */ 
/* 3626 */     if (NUMBER._isZero(paramArrayOfByte))
/*      */     {
/* 3628 */       return NUMBER._makeZero();
/*      */     }
/* 3630 */     if (NUMBER._isNegInf(paramArrayOfByte))
/*      */     {
/* 3632 */       return NUMBER._makeNegInf();
/*      */     }
/* 3634 */     if (NUMBER._isPosInf(paramArrayOfByte))
/*      */     {
/* 3636 */       return NUMBER._makePosInf();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3641 */     int i3 = paramArrayOfByte[0] < 0 ? 256 + paramArrayOfByte[0] : paramArrayOfByte[0];
/*      */     boolean bool;
/* 3643 */     int k; int m; int n; if ((bool = NUMBER._isPositive(paramArrayOfByte)))
/*      */     {
/* 3645 */       if (paramInt >= 0)
/*      */       {
/* 3647 */         k = i3 + (paramInt + 1 >> 1) - 192;
/* 3648 */         m = (paramInt & 0x1) == 1 ? 1 : 0;
/*      */       }
/*      */       else
/*      */       {
/* 3652 */         paramInt = -paramInt;
/* 3653 */         k = i3 - (paramInt >> 1) - 192;
/* 3654 */         m = (paramInt & 0x1) == 1 ? 1 : 0;
/*      */       }
/* 3656 */       n = 1;
/*      */     }
/*      */     else
/*      */     {
/* 3660 */       if (paramInt >= 0)
/*      */       {
/* 3662 */         k = 63 + (paramInt + 1 >> 1) - i3;
/* 3663 */         m = (paramInt & 0x1) == 1 ? 1 : 0;
/*      */       }
/*      */       else
/*      */       {
/* 3667 */         paramInt = -paramInt;
/* 3668 */         k = 63 - (paramInt >> 1) - i3;
/* 3669 */         m = (paramInt & 0x1) == 1 ? 1 : 0;
/*      */       }
/*      */       
/* 3672 */       n = 101;
/*      */       
/* 3674 */       if (paramArrayOfByte[(i1 - 1)] == 102)
/*      */       {
/* 3676 */         i1--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3682 */     byte[] arrayOfByte = new byte[i1];
/* 3683 */     System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, i1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3688 */     if ((k > i1 - 1) || ((k == i1 - 1) && ((m != 0) || (LnxqFirstDigit[paramArrayOfByte[k]] == 1))))
/*      */     {
/*      */ 
/* 3691 */       return _setLength(paramArrayOfByte, i1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3697 */     if ((k <= 0) || ((k == 1) && (m != 0) && (bool ? paramArrayOfByte[1] < 11 : paramArrayOfByte[1] > 91)))
/*      */     {
/*      */ 
/* 3700 */       return NUMBER._makeZero();
/*      */     }
/*      */     
/*      */ 
/* 3704 */     int i = j = (byte)k;
/*      */     
/*      */ 
/* 3707 */     if (m != 0)
/*      */     {
/*      */ 
/* 3710 */       if (bool)
/*      */       {
/* 3712 */         arrayOfByte[i] = LnxqTruncate_P[paramArrayOfByte[j]];
/*      */       }
/*      */       else
/*      */       {
/* 3716 */         arrayOfByte[i] = LnxqTruncate_N[paramArrayOfByte[j]];
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 3723 */       arrayOfByte[i] = paramArrayOfByte[j];
/*      */     }
/*      */     
/*      */ 
/* 3727 */     int j = (byte)(j - 1);
/*      */     int i2;
/* 3729 */     if (arrayOfByte[i] == n)
/*      */     {
/*      */ 
/*      */ 
/* 3733 */       while (paramArrayOfByte[j] == n)
/*      */       {
/* 3735 */         j = (byte)(j - 1);
/*      */       }
/*      */       
/*      */ 
/* 3739 */       i2 = j + 1;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 3744 */       i2 = k + 1;
/*      */     }
/*      */     
/* 3747 */     return _setLength(arrayOfByte, i2);
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
/*      */   public byte[] lnxcpn(String paramString1, boolean paramBoolean1, int paramInt1, boolean paramBoolean2, int paramInt2, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 3763 */     int n = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3768 */     int i4 = 0;
/* 3769 */     boolean bool = false;
/* 3770 */     int i5 = 0;
/*      */     
/*      */ 
/* 3773 */     int i6 = 0;
/* 3774 */     int i7 = 0;
/* 3775 */     int i8 = 0;
/* 3776 */     int i9 = 0;
/* 3777 */     int i10 = 0;
/* 3778 */     int i11 = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3783 */     int i15 = 0;
/* 3784 */     int i16 = 0;
/* 3785 */     int i17 = 0;
/* 3786 */     int i18 = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3796 */     int i27 = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     Locale localLocale;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3807 */     if (paramString2 != null)
/*      */     {
/*      */ 
/* 3810 */       int i28 = paramString2.indexOf("_");
/* 3811 */       if (i28 == -1)
/*      */       {
/* 3813 */         localLocale = LxMetaData.getJavaLocale(paramString2, "");
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 3818 */         String str1 = paramString2.substring(0, i28);
/* 3819 */         String str2 = paramString2.substring(i28 + 1);
/* 3820 */         localLocale = LxMetaData.getJavaLocale(str1, str2);
/*      */       }
/*      */       
/* 3823 */       if (localLocale == null) {
/* 3824 */         localLocale = ClassRef.LOCALE.getDefault();
/*      */       }
/*      */     }
/*      */     else {
/* 3828 */       localLocale = ClassRef.LOCALE.getDefault();
/*      */     }
/*      */     
/* 3831 */     DecimalFormatSymbols localDecimalFormatSymbols = new DecimalFormatSymbols(localLocale);
/* 3832 */     int i22 = localDecimalFormatSymbols.getDecimalSeparator();
/* 3833 */     int i26 = localDecimalFormatSymbols.getMinusSign();
/*      */     
/*      */ 
/* 3836 */     char[] arrayOfChar = paramString1.toCharArray();
/*      */     
/*      */ 
/*      */ 
/* 3840 */     int j = 0;
/* 3841 */     int k = arrayOfChar.length - 1;
/*      */     
/*      */ 
/* 3844 */     while ((j <= k) && (Character.isSpaceChar(arrayOfChar[j])))
/*      */     {
/* 3846 */       j++;
/*      */     }
/*      */     
/*      */ 
/* 3850 */     if ((j <= k) && ((arrayOfChar[j] == i26) || (arrayOfChar[j] == '+')))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3855 */       bool = arrayOfChar[j] == i26;
/* 3856 */       j++;
/*      */     }
/*      */     
/*      */ 
/* 3860 */     int i = j;
/*      */     
/*      */ 
/* 3863 */     while ((j <= k) && (arrayOfChar[j] == '0'))
/*      */     {
/* 3865 */       j++;
/*      */     }
/*      */     
/*      */ 
/* 3869 */     int m = j;
/*      */     
/*      */ 
/* 3872 */     while ((j <= k) && (Character.isDigit(arrayOfChar[j])))
/*      */     {
/* 3874 */       j++;
/*      */     }
/*      */     
/*      */ 
/* 3878 */     i6 = j - i;
/* 3879 */     i7 = j - m;
/*      */     
/*      */ 
/* 3882 */     if ((j <= k) && (arrayOfChar[j] == i22))
/*      */     {
/* 3884 */       j++;
/*      */       
/*      */ 
/* 3887 */       n = j;
/*      */       
/*      */ 
/* 3890 */       while ((j <= k) && (Character.isDigit(arrayOfChar[j])))
/*      */       {
/* 3892 */         j++;
/*      */       }
/*      */       
/*      */ 
/* 3896 */       i8 = j - n;
/*      */       
/*      */ 
/* 3899 */       i = j;
/*      */       
/*      */ 
/*      */ 
/*      */       do
/*      */       {
/* 3905 */         j--;
/*      */       }
/* 3907 */       while ((j >= n) && (arrayOfChar[j] == '0'));
/*      */       
/*      */ 
/* 3910 */       i9 = j - n + 1;
/*      */       
/*      */ 
/* 3913 */       j = i;
/*      */     }
/*      */     
/*      */ 
/* 3917 */     if (i6 + i8 != 0)
/*      */     {
/*      */ 
/* 3920 */       if ((j <= k) && ((arrayOfChar[j] == 'E') || (arrayOfChar[j] == 'e')))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3926 */         j++;
/*      */         
/* 3928 */         if ((j <= k) && ((arrayOfChar[j] == i26) || (arrayOfChar[j] == '+')))
/*      */         {
/*      */ 
/*      */ 
/* 3932 */           i5 = arrayOfChar[j] == i26 ? 1 : 0;
/*      */           
/* 3934 */           j++;
/*      */         }
/*      */         
/*      */ 
/* 3938 */         i = j;
/*      */         
/*      */ 
/* 3941 */         while ((j <= k) && (arrayOfChar[j] == '0'))
/*      */         {
/* 3943 */           j++;
/*      */         }
/*      */         
/*      */ 
/* 3947 */         int i1 = j;
/*      */         
/*      */ 
/* 3950 */         while ((j <= k) && (Character.isDigit(arrayOfChar[j])))
/*      */         {
/* 3952 */           i4 = i4 * 10 + (arrayOfChar[j] - '0');
/* 3953 */           j++;
/*      */         }
/*      */         
/*      */ 
/* 3957 */         i10 = j - i;
/* 3958 */         i11 = j - i1;
/*      */         
/* 3960 */         if (i10 != 0)
/*      */         {
/* 3962 */           if (i5 != 0)
/*      */           {
/* 3964 */             i4 = -i4;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3973 */       while ((j <= k) && (Character.isSpaceChar(arrayOfChar[j])))
/*      */       {
/* 3975 */         j++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */     int i19;
/*      */     
/*      */     int i20;
/*      */     
/*      */     int i3;
/*      */     
/* 3986 */     if (i7 != 0)
/*      */     {
/* 3988 */       if (i9 != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 3993 */         i4 += i7 - 1;
/*      */         
/*      */ 
/* 3996 */         i19 = i7;
/* 3997 */         i20 = i9;
/*      */         
/*      */ 
/* 4000 */         i3 = m;
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 4007 */         i4 += i7 - 1;
/*      */         
/*      */ 
/* 4010 */         j = m + (i7 - 1);
/* 4011 */         while (arrayOfChar[j] == '0')
/*      */         {
/* 4013 */           j--;
/*      */         }
/*      */         
/*      */ 
/* 4017 */         i19 = j - m + 1;
/* 4018 */         i20 = 0;
/*      */         
/*      */ 
/* 4021 */         i3 = m;
/*      */       }
/*      */       
/*      */ 
/*      */     }
/* 4026 */     else if (i9 != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4032 */       j = n;
/* 4033 */       while (arrayOfChar[j] == '0')
/*      */       {
/* 4035 */         j++;
/*      */       }
/*      */       
/*      */ 
/* 4039 */       i4 -= j - n + 1;
/*      */       
/*      */ 
/* 4042 */       i19 = i9 - (j - n);
/* 4043 */       i20 = 0;
/*      */       
/*      */ 
/* 4046 */       i3 = j;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 4051 */       return NUMBER._makeZero();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4056 */     int i14 = (i4 & 0x1) == 1 ? 40 : 39;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 4061 */     int i12 = i19 + i20;
/*      */     
/*      */ 
/* 4064 */     if ((paramBoolean1) || (paramBoolean2))
/*      */     {
/*      */ 
/* 4067 */       if (!paramBoolean1)
/*      */       {
/* 4069 */         paramInt1 = Integer.MAX_VALUE;
/*      */       }
/*      */       
/* 4072 */       if (!paramBoolean2)
/*      */       {
/* 4074 */         paramInt2 = 0;
/*      */       }
/*      */       
/* 4077 */       i13 = i4 + 1 + paramInt2;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 4082 */       i13 = i12;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4087 */     int i13 = Math.min(i13, i14);
/*      */     
/*      */ 
/*      */ 
/* 4091 */     if ((i13 < 0) || ((i13 == 0) && (arrayOfChar[i3] < '5')))
/*      */     {
/*      */ 
/* 4094 */       return NUMBER._makeZero();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4099 */     int i21 = 0;
/*      */     
/* 4101 */     if (i13 < i12)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4107 */       j = i3 + i13 + (i13 < i19 ? 0 : 1);
/*      */       
/*      */ 
/* 4110 */       if (arrayOfChar[j] < '5')
/*      */       {
/*      */ 
/*      */         do
/*      */         {
/* 4115 */           j--;
/*      */           
/* 4117 */           if (j < i3) break; } while ((arrayOfChar[j] == '0') || (arrayOfChar[j] == i22));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */         do
/*      */         {
/* 4125 */           j--;
/*      */         }
/* 4127 */         while ((j >= i3) && ((arrayOfChar[j] == '9') || (arrayOfChar[j] == i22)));
/*      */         
/* 4129 */         i21 = 1;
/*      */       }
/*      */       
/*      */ 
/* 4133 */       if (j < i3)
/*      */       {
/* 4135 */         arrayOfChar[1] = '1';
/* 4136 */         arrayOfChar[2] = '0';
/* 4137 */         i3 = 1;
/*      */         
/* 4139 */         i4++;
/*      */         
/*      */ 
/* 4142 */         i15 = 0;
/* 4143 */         i17 = (i4 & 0x1) == 1 ? 0 : 1;
/* 4144 */         i18 = 0;
/* 4145 */         i19 = (i4 & 0x1) == 1 ? 2 : 0;
/* 4146 */         i20 = 0;
/* 4147 */         i21 = 0;
/* 4148 */         i27 = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/* 4153 */       else if (i20 != 0)
/*      */       {
/*      */ 
/* 4156 */         if (j - i3 < i19)
/*      */         {
/*      */ 
/* 4159 */           i19 = j - i3 + 1;
/* 4160 */           i20 = 0;
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/* 4165 */           i20 = j - i3 - i19;
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/* 4171 */         i19 = j - i3 + 1;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4177 */     if (i27 == 0)
/*      */     {
/* 4179 */       if (i20 != 0)
/*      */       {
/*      */ 
/* 4182 */         i15 = 1;
/* 4183 */         i16 = (i4 & 0x1) == (i19 & 0x1) ? 1 : 0;
/* 4184 */         i17 = (i4 & 0x1) == 1 ? 0 : 1;
/* 4185 */         i18 = i16 != ((i20 & 0x1) == 1 ? 1 : 0) ? 1 : 0;
/*      */         
/* 4187 */         i19 -= (i17 != 0 ? 1 : 0) + (i16 != 0 ? 1 : 0);
/* 4188 */         i20 -= (i16 != 0 ? 1 : 0) + (i18 != 0 ? 1 : 0);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 4193 */         i15 = 0;
/* 4194 */         i17 = (i4 & 0x1) == 1 ? 0 : 1;
/* 4195 */         i18 = (i4 & 0x1) == (i19 & 0x1) ? 1 : 0;
/* 4196 */         i19 -= (i17 != 0 ? 1 : 0) + (i18 != 0 ? 1 : 0);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 4201 */     if ((i5 == 0) && ((i11 > 3) || (i4 > 125)))
/*      */     {
/* 4203 */       if (bool)
/*      */       {
/* 4205 */         return NUMBER._makeNegInf();
/*      */       }
/*      */       
/*      */ 
/* 4209 */       return NUMBER._makePosInf();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4214 */     if ((i5 != 0) && ((i11 > 3) || (i4 < 65406)))
/*      */     {
/* 4216 */       return NUMBER._makeZero();
/*      */     }
/*      */     
/* 4219 */     byte[] arrayOfByte = new byte[22];
/* 4220 */     int i25 = 1;
/* 4221 */     j = i3;
/*      */     
/*      */ 
/*      */ 
/* 4225 */     if (i17 != 0)
/*      */     {
/*      */ 
/* 4228 */       arrayOfByte[i25] = digitPtr(0, lnxqctn(arrayOfChar[j]), bool);
/*      */       
/*      */ 
/* 4231 */       i25++;
/* 4232 */       j++;
/*      */     }
/*      */     
/*      */     int i2;
/*      */     
/* 4237 */     if (i19 != 0)
/*      */     {
/* 4239 */       i2 = j + i19;
/* 4240 */       while (j < i2)
/*      */       {
/*      */ 
/* 4243 */         arrayOfByte[i25] = digitPtr(lnxqctn(arrayOfChar[j]), lnxqctn(arrayOfChar[(j + 1)]), bool);
/*      */         
/*      */ 
/* 4246 */         i25++;
/* 4247 */         j += 2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4253 */     if (i15 != 0)
/*      */     {
/* 4255 */       if (i16 != 0)
/*      */       {
/* 4257 */         arrayOfByte[i25] = digitPtr(lnxqctn(arrayOfChar[j]), lnxqctn(arrayOfChar[(j + 2)]), bool);
/*      */         
/*      */ 
/* 4260 */         i25++;
/* 4261 */         j += 3;
/*      */       }
/*      */       else
/*      */       {
/* 4265 */         j++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4271 */     if (i20 != 0)
/*      */     {
/* 4273 */       i2 = j + i20;
/* 4274 */       while (j < i2)
/*      */       {
/*      */ 
/*      */ 
/* 4278 */         arrayOfByte[i25] = digitPtr(lnxqctn(arrayOfChar[j]), lnxqctn(arrayOfChar[(j + 1)]), bool);
/*      */         
/*      */ 
/* 4281 */         i25++;
/* 4282 */         j += 2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4288 */     if (i18 != 0)
/*      */     {
/* 4290 */       arrayOfByte[i25] = digitPtr(lnxqctn(arrayOfChar[j]), 0, bool);
/*      */       
/*      */ 
/* 4293 */       i25++;
/* 4294 */       j++;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4299 */     if (i21 != 0)
/*      */     {
/* 4301 */       int tmp1577_1576 = (i25 - 1); byte[] tmp1577_1571 = arrayOfByte;tmp1577_1571[tmp1577_1576] = ((byte)(tmp1577_1571[tmp1577_1576] + (bool ? -1 : 1) * (i18 != 0 ? 10 : 1)));
/*      */     }
/*      */     
/*      */     int i24;
/*      */     
/* 4306 */     if (i4 < 0)
/*      */     {
/* 4308 */       i24 = (byte)(193 - (1 - i4) / 2);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 4313 */       i24 = (byte)(193 + i4 / 2);
/*      */     }
/*      */     
/*      */ 
/* 4317 */     int i23 = i25;
/*      */     
/*      */ 
/* 4320 */     arrayOfByte[0] = ((byte)(bool ? i24 ^ 0xFFFFFFFF : i24));
/*      */     
/* 4322 */     return _setLength(arrayOfByte, i23);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static byte digitPtr(int paramInt1, int paramInt2, boolean paramBoolean)
/*      */   {
/* 4334 */     return paramBoolean ? LnxqDigit_N[paramInt1][paramInt2] : LnxqDigit_P[paramInt1][paramInt2];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int lnxqctn(char paramChar)
/*      */   {
/* 4343 */     return Character.digit(paramChar, 10);
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
/*      */   public byte[] lnxfcn(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 4378 */     if ((paramString3 != null) && (!paramString3.equals("AMERICAN_AMERICAN")))
/*      */     {
/* 4380 */       throw new SQLException(CoreException.getMessage((byte)12));
/*      */     }
/*      */     
/*      */ 
/* 4384 */     LnxLibThinFormat localLnxLibThinFormat = new LnxLibThinFormat();
/* 4385 */     localLnxLibThinFormat.parseFormat(paramString2);
/*      */     
/* 4387 */     localLnxLibThinFormat.LNXNFRDX = true;
/*      */     
/* 4389 */     int i = localLnxLibThinFormat.lnxnflhd;
/* 4390 */     int j = localLnxLibThinFormat.lnxnfrhd;
/* 4391 */     int k = localLnxLibThinFormat.lnxnfsiz;
/* 4392 */     int m = localLnxLibThinFormat.lnxnfzld;
/*      */     
/*      */ 
/* 4395 */     if ((localLnxLibThinFormat.LNXNFFDA | localLnxLibThinFormat.LNXNFFED | localLnxLibThinFormat.LNXNFFRN | localLnxLibThinFormat.LNXNFFTM))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 4400 */       throw new SQLException(CoreException.getMessage((byte)5));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4405 */     if ((localLnxLibThinFormat.LNXNFFRC | localLnxLibThinFormat.LNXNFFCH | localLnxLibThinFormat.LNXNFFCT))
/*      */     {
/* 4407 */       throw new SQLException(CoreException.getMessage((byte)12));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4412 */     char[] arrayOfChar1 = paramString1.toCharArray();
/* 4413 */     int n = 0;
/* 4414 */     int i1 = arrayOfChar1.length - 1;
/*      */     
/* 4416 */     int i2 = 0;
/*      */     
/*      */ 
/* 4419 */     while ((n <= i1) && (Character.isSpaceChar(arrayOfChar1[n])))
/*      */     {
/* 4421 */       n++;
/* 4422 */       i2++;
/*      */     }
/*      */     
/* 4425 */     if ((localLnxLibThinFormat.LNXNFFBL) && (i2 == k) && (i2 == arrayOfChar1.length))
/*      */     {
/*      */ 
/* 4428 */       return NUMBER._makeZero();
/*      */     }
/*      */     
/*      */ 
/* 4432 */     if (n > i1)
/*      */     {
/* 4434 */       throw new SQLException(CoreException.getMessage((byte)14));
/*      */     }
/*      */     
/*      */ 
/* 4438 */     char[] arrayOfChar2 = new char['ÿ'];
/* 4439 */     int i3 = 0;
/*      */     
/*      */ 
/*      */ 
/* 4443 */     if (localLnxLibThinFormat.LNXNFFSH)
/*      */     {
/*      */ 
/* 4446 */       if ((arrayOfChar1[n] != '-') && (arrayOfChar1[n] != '+'))
/*      */       {
/* 4448 */         throw new SQLException(CoreException.getMessage((byte)14));
/*      */       }
/*      */       
/* 4451 */       arrayOfChar2[(i3++)] = arrayOfChar1[(n++)];
/*      */     }
/* 4453 */     else if (localLnxLibThinFormat.LNXNFFPR)
/*      */     {
/*      */ 
/* 4456 */       if (arrayOfChar1[n] == '<')
/*      */       {
/* 4458 */         arrayOfChar2[(i3++)] = '-';
/* 4459 */         n++;
/*      */       }
/*      */       else
/*      */       {
/* 4463 */         arrayOfChar2[(i3++)] = '+';
/*      */       }
/*      */     }
/* 4466 */     else if (localLnxLibThinFormat.LNXNFFPT)
/*      */     {
/*      */ 
/* 4469 */       if (arrayOfChar1[n] == '(')
/*      */       {
/* 4471 */         arrayOfChar2[(i3++)] = '-';
/* 4472 */         n++;
/*      */       }
/*      */       else
/*      */       {
/* 4476 */         arrayOfChar2[(i3++)] = '+';
/*      */       }
/*      */     }
/* 4479 */     else if ((!localLnxLibThinFormat.LNXNFFMI) && (!localLnxLibThinFormat.LNXNFFST))
/*      */     {
/*      */ 
/* 4482 */       if (arrayOfChar1[n] == '-')
/*      */       {
/* 4484 */         arrayOfChar2[(i3++)] = arrayOfChar1[(n++)];
/*      */       }
/*      */       
/*      */     }
/*      */     else
/*      */     {
/* 4490 */       i3++;
/*      */     }
/*      */     
/*      */ 
/* 4494 */     if (n > i1)
/*      */     {
/* 4496 */       throw new SQLException(CoreException.getMessage((byte)14));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 4502 */     if (localLnxLibThinFormat.LNXNFFDS)
/*      */     {
/*      */ 
/* 4505 */       if (arrayOfChar1[n] != '$')
/*      */       {
/* 4507 */         throw new SQLException(CoreException.getMessage((byte)14));
/*      */       }
/*      */       
/*      */ 
/* 4511 */       n++;
/*      */       
/* 4513 */       if (n > i1)
/*      */       {
/* 4515 */         throw new SQLException(CoreException.getMessage((byte)14));
/*      */       }
/*      */       
/*      */     }
/* 4519 */     else if (localLnxLibThinFormat.LNXNFFCH)
/*      */     {
/*      */ 
/*      */ 
/* 4523 */       throw new SQLException(CoreException.getMessage((byte)12));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 4529 */     byte[] arrayOfByte = new byte[40];
/* 4530 */     int i4 = 0;
/*      */     
/* 4532 */     int i5 = 0;
/*      */     
/* 4534 */     int i6 = 0;
/* 4535 */     int i7 = 0;
/*      */     int i8;
/* 4537 */     while (n <= i1)
/*      */     {
/*      */ 
/* 4540 */       if ((Character.isDigit(arrayOfChar1[n])) || ((localLnxLibThinFormat.LNXNFFHX) && (((arrayOfChar1[n] >= 'a') && (arrayOfChar1[n] <= 'f')) || ((arrayOfChar1[n] >= 'A') && (arrayOfChar1[n] <= 'F')))))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 4545 */         arrayOfChar2[(i3++)] = arrayOfChar1[(n++)];
/* 4546 */         i6++;
/*      */       }
/*      */       else {
/* 4549 */         if (i7 == 0)
/*      */         {
/* 4551 */           if (localLnxLibThinFormat.LNXNFRDX)
/*      */           {
/*      */ 
/* 4554 */             if (arrayOfChar1[n] == ',')
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4560 */               n++;
/* 4561 */               arrayOfByte[(i4++)] = ((byte)i6);
/* 4562 */               continue;
/*      */             }
/* 4564 */             if (arrayOfChar1[n] == '.')
/*      */             {
/*      */ 
/* 4567 */               if ((i6 > i) || (i6 < m))
/*      */               {
/* 4569 */                 throw new SQLException(CoreException.getMessage((byte)14));
/*      */               }
/*      */               
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4577 */               i6 = i - i6;
/*      */               
/*      */ 
/*      */ 
/* 4581 */               i4 = 0;
/* 4582 */               while (localLnxLibThinFormat.lnxnfgps[i5] != 0)
/*      */               {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4593 */                 i8 = (byte)(localLnxLibThinFormat.lnxnfgps[i5] & 0x7F);
/*      */                 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4607 */                 if (i8 > i6)
/*      */                 {
/* 4609 */                   if (arrayOfByte[i4] != i8 - i6)
/*      */                   {
/* 4611 */                     throw new SQLException(CoreException.getMessage((byte)14));
/*      */                   }
/*      */                   
/*      */ 
/* 4615 */                   i4++;
/*      */                 }
/*      */                 
/*      */ 
/* 4619 */                 i5++;
/*      */               }
/*      */               
/*      */ 
/* 4623 */               i6 = 0;
/* 4624 */               i7 = 1;
/* 4625 */               arrayOfChar2[i3] = '.';
/* 4626 */               i3++;
/* 4627 */               n++;
/*      */             }
/*      */             
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/* 4634 */             throw new SQLException(CoreException.getMessage((byte)12));
/*      */           }
/*      */         }
/*      */         
/*      */ 
/* 4639 */         if (((arrayOfChar1[n] == 'E') || (arrayOfChar1[n] == 'e')) && (localLnxLibThinFormat.LNXNFFSN))
/*      */         {
/*      */ 
/* 4642 */           if ((i6 <= 0) && (i7 == 0))
/*      */           {
/* 4644 */             throw new SQLException(CoreException.getMessage((byte)14));
/*      */           }
/*      */           
/* 4647 */           arrayOfChar2[(i3++)] = arrayOfChar1[(n++)];
/* 4648 */           if (n > i1)
/*      */           {
/* 4650 */             throw new SQLException(CoreException.getMessage((byte)14));
/*      */           }
/*      */           
/* 4653 */           if ((arrayOfChar1[n] == '+') || (arrayOfChar1[n] == '-'))
/*      */           {
/* 4655 */             arrayOfChar2[(i3++)] = arrayOfChar1[(n++)];
/*      */           }
/* 4657 */           i8 = n;
/* 4658 */           while ((n <= i1) && (Character.isDigit(arrayOfChar1[n])))
/*      */           {
/* 4660 */             arrayOfChar2[(i3++)] = arrayOfChar1[(n++)];
/*      */           }
/*      */           
/* 4663 */           if (i8 == n)
/*      */           {
/* 4665 */             throw new SQLException(CoreException.getMessage((byte)14));
/*      */           }
/*      */           
/*      */         }
/* 4669 */         else if (localLnxLibThinFormat.LNXNFFRC)
/*      */         {
/*      */ 
/*      */ 
/* 4673 */           throw new SQLException(CoreException.getMessage((byte)12));
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4684 */     if (i7 == 0)
/*      */     {
/* 4686 */       i8 = i - i6;
/* 4687 */       i4 = 0;
/*      */       
/* 4689 */       while (localLnxLibThinFormat.lnxnfgps[i5] != 0)
/*      */       {
/* 4691 */         int i9 = (byte)(localLnxLibThinFormat.lnxnfgps[i5] & 0x7F);
/* 4692 */         if (i9 > i8)
/*      */         {
/* 4694 */           if (arrayOfByte[i4] != i9 - i8)
/*      */           {
/* 4696 */             throw new SQLException(CoreException.getMessage((byte)14));
/*      */           }
/*      */           
/* 4699 */           i4++;
/*      */         }
/* 4701 */         i5++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4707 */     if (localLnxLibThinFormat.LNXNFFCT)
/*      */     {
/* 4709 */       throw new SQLException(CoreException.getMessage((byte)12));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 4715 */     if (localLnxLibThinFormat.LNXNFFST)
/*      */     {
/*      */ 
/* 4718 */       if (n > i1)
/*      */       {
/* 4720 */         throw new SQLException(CoreException.getMessage((byte)14));
/*      */       }
/*      */       
/* 4723 */       if ((arrayOfChar1[n] != '-') && (arrayOfChar1[n] != '+'))
/*      */       {
/* 4725 */         throw new SQLException(CoreException.getMessage((byte)14));
/*      */       }
/*      */       
/* 4728 */       arrayOfChar2[0] = arrayOfChar1[n];
/* 4729 */       n++;
/*      */     }
/* 4731 */     else if (localLnxLibThinFormat.LNXNFFMI)
/*      */     {
/*      */ 
/* 4734 */       if (n > i1)
/*      */       {
/* 4736 */         if ((localLnxLibThinFormat.LNXNFFIL) || (n != arrayOfChar1.length))
/*      */         {
/* 4738 */           throw new SQLException(CoreException.getMessage((byte)14));
/*      */         }
/*      */         
/*      */ 
/* 4742 */         arrayOfChar2[0] = '+';
/*      */       }
/*      */       else
/*      */       {
/* 4746 */         arrayOfChar2[0] = (arrayOfChar1[n] == '-' ? 45 : '+');
/* 4747 */         n++;
/*      */       }
/*      */     }
/* 4750 */     else if (localLnxLibThinFormat.LNXNFFPR)
/*      */     {
/*      */ 
/* 4753 */       if ((arrayOfChar1[n] == '>') && (arrayOfChar2[0] == '-'))
/*      */       {
/* 4755 */         n++;
/*      */       }
/*      */     }
/* 4758 */     else if (localLnxLibThinFormat.LNXNFFPT)
/*      */     {
/*      */ 
/* 4761 */       if ((n < arrayOfChar1.length) && (arrayOfChar1[n] == ')') && (arrayOfChar2[0] == '-'))
/*      */       {
/*      */ 
/*      */ 
/* 4765 */         n++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4771 */     if (n <= i1)
/*      */     {
/* 4773 */       throw new SQLException(CoreException.getMessage((byte)14));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 4779 */     if (i7 != 0)
/*      */     {
/* 4781 */       if (i6 > j)
/*      */       {
/* 4783 */         throw new SQLException(CoreException.getMessage((byte)14));
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 4790 */       if (i6 > i)
/*      */       {
/* 4792 */         throw new SQLException(CoreException.getMessage((byte)14));
/*      */       }
/*      */       
/*      */ 
/* 4796 */       if (i6 < m)
/*      */       {
/* 4798 */         throw new SQLException(CoreException.getMessage((byte)14));
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
/* 4809 */     if ((localLnxLibThinFormat.LNXNFF05) && (((i7 != 0) && (i6 == j)) || (j == 0)))
/*      */     {
/*      */ 
/* 4812 */       i3--;
/* 4813 */       if ((arrayOfChar2[i3] != '0') && (arrayOfChar2[i3] != '5'))
/*      */       {
/* 4815 */         throw new SQLException(CoreException.getMessage((byte)14));
/*      */       }
/*      */       
/* 4818 */       i3++;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4823 */     if (localLnxLibThinFormat.LNXNFFHX)
/*      */     {
/* 4825 */       return lnxqh2n(arrayOfChar2);
/*      */     }
/*      */     
/*      */ 
/* 4829 */     return lnxcpn(new String(arrayOfChar2), false, 0, false, 0, paramString3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String lnxnfn(byte[] paramArrayOfByte, String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 4841 */     char[] arrayOfChar1 = null;
/* 4842 */     int i = 0;
/* 4843 */     int j = 0;
/* 4844 */     int k = 0;
/* 4845 */     int m = 0;
/* 4846 */     int n = 0;
/* 4847 */     int i1 = 0;
/* 4848 */     int i2 = 0;
/* 4849 */     int i3 = 0;
/* 4850 */     int i4 = 0;
/* 4851 */     int i5 = 0;
/* 4852 */     int i6 = 0;
/* 4853 */     int i7 = 0;
/* 4854 */     int i8 = 0;
/* 4855 */     int i9 = 0;
/* 4856 */     int i10 = 0;
/* 4857 */     int i11 = 0;
/* 4858 */     int i12 = 0;
/* 4859 */     int i13 = 0;
/*      */     
/* 4861 */     Object localObject = null;
/* 4862 */     boolean[] arrayOfBoolean = new boolean[1];
/* 4863 */     int i14 = 0;
/* 4864 */     int i15 = 1;
/*      */     
/* 4866 */     int i17 = 1;
/*      */     
/* 4868 */     String str = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4875 */     LnxLibThinFormat localLnxLibThinFormat = new LnxLibThinFormat();
/* 4876 */     localLnxLibThinFormat.parseFormat(paramString1);
/*      */     
/* 4878 */     i = localLnxLibThinFormat.lnxnflhd;
/* 4879 */     j = localLnxLibThinFormat.lnxnfrhd;
/* 4880 */     k = localLnxLibThinFormat.lnxnfsiz;
/* 4881 */     m = localLnxLibThinFormat.lnxnfzld;
/* 4882 */     n = localLnxLibThinFormat.lnxnfztr;
/*      */     
/* 4884 */     if ((localLnxLibThinFormat.LNXNFFRN) || (localLnxLibThinFormat.LNXNFFHX))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4894 */       if (localLnxLibThinFormat.LNXNFFRN)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4911 */         throw new SQLException(CoreException.getMessage((byte)1));
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 4916 */       throw new SQLException(CoreException.getMessage((byte)1));
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
/*      */     int i19;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     int i20;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4959 */     if (localLnxLibThinFormat.LNXNFFTM)
/*      */     {
/* 4961 */       i19 = paramArrayOfByte.length;
/*      */       
/*      */       int i21;
/*      */       
/*      */       int i22;
/*      */       
/*      */       int i23;
/*      */       
/* 4969 */       if (!NUMBER._isZero(paramArrayOfByte))
/*      */       {
/* 4971 */         if (NUMBER._isPositive(paramArrayOfByte))
/*      */         {
/* 4973 */           i20 = i19 - 1;
/* 4974 */           i21 = 2 * ((paramArrayOfByte[0] & 0xFF) - 193) + ((paramArrayOfByte[1] & 0xFF) > 10 ? 1 : 0);
/*      */           
/* 4976 */           i22 = 2 * i20 - ((paramArrayOfByte[1] & 0xFF) < 11 ? 1 : 0) - LnxqFirstDigit[paramArrayOfByte[i20]];
/* 4977 */           i23 = 0;
/*      */         }
/*      */         else
/*      */         {
/* 4981 */           if ((paramArrayOfByte[(i19 - 1)] & 0xFF) == 102) {
/* 4982 */             i19--;
/*      */           }
/* 4984 */           i20 = i19 - 1;
/* 4985 */           i21 = 2 * (62 - paramArrayOfByte[0]) + ((paramArrayOfByte[1] & 0xFF) < 92 ? 1 : 0);
/*      */           
/* 4987 */           i22 = 2 * i20 - ((paramArrayOfByte[1] & 0xFF) > 91 ? 1 : 0) - LnxqFirstDigit[paramArrayOfByte[i20]];
/* 4988 */           i23 = 1;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 4993 */         i21 = 0;
/* 4994 */         i22 = 1;
/* 4995 */         i23 = 0;
/*      */       }
/*      */       
/*      */ 
/* 4999 */       if (i21 >= 0)
/*      */       {
/* 5001 */         i23 += (i22 > i21 + 1 ? i22 + 1 : i21 + 1);
/*      */       }
/*      */       else
/*      */       {
/* 5005 */         i23 += -(i21 + 1) + i22 + 1;
/*      */       }
/*      */       
/*      */ 
/* 5009 */       if ((!localLnxLibThinFormat.LNXNFFSN) && (i23 > 64))
/*      */       {
/* 5011 */         localLnxLibThinFormat.LNXNFFSN = true;
/*      */       }
/*      */       
/*      */ 
/* 5015 */       if (localLnxLibThinFormat.LNXNFFSN)
/*      */       {
/* 5017 */         i = 1;
/* 5018 */         j = i22 - 1;
/* 5019 */         k = i22 > 1 ? i22 + 7 : 7;
/*      */       }
/*      */       else
/*      */       {
/* 5023 */         i = i21 + 1 > 0 ? i21 + 1 : 0;
/* 5024 */         j = i22 - (i21 + 1) > 0 ? i22 - (i21 + 1) : 0;
/* 5025 */         k = j != 0 ? i + j + 2 : i + 1;
/*      */       }
/*      */       
/*      */ 
/* 5029 */       if (j == 0)
/*      */       {
/* 5031 */         localLnxLibThinFormat.LNXNFNRD = true;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5041 */     int i18 = (paramArrayOfByte[0] & 0xFF) >= 128 ? 1 : 0;
/*      */     
/*      */ 
/*      */     byte[] arrayOfByte;
/*      */     
/* 5046 */     if (localLnxLibThinFormat.LNXNFFSN)
/*      */     {
/* 5048 */       arrayOfByte = lnxfpr(paramArrayOfByte, j + i);
/*      */     }
/*      */     else
/*      */     {
/* 5052 */       arrayOfByte = lnxsca(paramArrayOfByte, i, j, arrayOfBoolean);
/*      */       
/* 5054 */       if (arrayOfBoolean[0] != 0)
/*      */       {
/* 5056 */         throw new SQLException(CoreException.getMessage((byte)4));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 5061 */     arrayOfChar1 = new char[64];
/* 5062 */     i5 = 0;
/*      */     
/* 5064 */     if (NUMBER._isZero(arrayOfByte))
/*      */     {
/*      */ 
/* 5067 */       if (localLnxLibThinFormat.LNXNFFBL)
/*      */       {
/* 5069 */         if (localLnxLibThinFormat.LNXNFFIL)
/*      */         {
/*      */ 
/* 5072 */           arrayOfChar1 = new char[k];
/*      */           
/* 5074 */           for (i19 = 0; i19 < k; i19++) {
/* 5075 */             arrayOfChar1[i19] = ' ';
/*      */           }
/* 5077 */           return new String(arrayOfChar1);
/*      */         }
/*      */         
/*      */ 
/* 5081 */         return null;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 5087 */       i15 = i18;
/* 5088 */       i14 = 0;
/* 5089 */       i2 = 0;
/* 5090 */       i4 = 0;
/* 5091 */       i1 = i2;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5103 */       i3 = (i > 0) && (n == 0) ? 1 : 0;
/*      */     }
/*      */     else {
/* 5106 */       if ((NUMBER._isNegInf(arrayOfByte)) || (NUMBER._isPosInf(arrayOfByte)))
/*      */       {
/* 5108 */         throw new SQLException(CoreException.getMessage((byte)4));
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5117 */       i4 = arrayOfByte.length - 1;
/* 5118 */       i15 = (arrayOfByte[0] & 0x80) != 0 ? 1 : 0;
/*      */       
/* 5120 */       if (i15 == 0)
/*      */       {
/* 5122 */         localObject = new byte[i4];
/* 5123 */         if (arrayOfByte[i4] == 102) {
/* 5124 */           i4--;
/*      */         }
/* 5126 */         for (i19 = 1; i19 <= i4; i19++) {
/* 5127 */           localObject[i19] = ((byte)(102 - arrayOfByte[i19]));
/*      */         }
/* 5129 */         localObject[0] = ((byte)(arrayOfByte[0] ^ 0xFFFFFFFF));
/*      */       }
/*      */       else
/*      */       {
/* 5133 */         localObject = arrayOfByte;
/*      */       }
/*      */       
/*      */ 
/* 5137 */       int i16 = (localObject[i4] & 0xFF) % 10 == 1 ? 1 : 0;
/* 5138 */       i1 = 2 * ((localObject[0] & 0xFF) - 192);
/*      */       
/*      */ 
/* 5141 */       i6 = 1;
/* 5142 */       if ((i17 = (localObject[i6] & 0xFF) < 11 ? 1 : 0) != 0)
/*      */       {
/* 5144 */         i10 = ((localObject[i6] & 0xFF) - 1) / 10;
/*      */       }
/*      */       
/* 5147 */       if (localLnxLibThinFormat.LNXNFFSN)
/*      */       {
/* 5149 */         i2 = 2 * i4 - (i17 != 0 ? 1 : 0) - (i16 != 0 ? 1 : 0) - (i3 = 1);
/* 5150 */         i1 -= (i17 != 0 ? 1 : 0) + 1;
/*      */         
/* 5152 */         if ((i14 = i1 < 0 ? 1 : 0) != 0)
/*      */         {
/* 5154 */           i1 = -i1;
/*      */         }
/* 5156 */         if ((i1 < 100) && (localLnxLibThinFormat.LNXNFFIL))
/*      */         {
/*      */ 
/* 5159 */           arrayOfChar1[i5] = ' ';
/* 5160 */           i5++;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 5165 */         i2 = 2 * i4 - i1 - (i16 != 0 ? 1 : 0);
/* 5166 */         i3 = i1 - (i17 != 0 ? 1 : 0);
/*      */         
/*      */ 
/* 5169 */         if ((localLnxLibThinFormat.LNXNFF05) && ((j == 0) || (i2 == j)))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5180 */           i19 = i6 + i4 - 1;
/*      */           
/*      */ 
/* 5183 */           if (i16 == 0)
/*      */           {
/*      */ 
/* 5186 */             i20 = (localObject[i19] & 0xFF) % 10;
/* 5187 */             i20 = i20 != 0 ? i20 - 1 : 9;
/* 5188 */             if (i20 <= 2)
/*      */             {
/* 5190 */               localObject[i19] = ((byte)((localObject[i19] & 0xFF) - i20));
/*      */             }
/* 5192 */             else if (i20 <= 7)
/*      */             {
/* 5194 */               localObject[i19] = ((byte)((localObject[i19] & 0xFF) + (5 - i20)));
/*      */             }
/*      */             
/*      */           }
/*      */           else
/*      */           {
/* 5200 */             i20 = (localObject[i19] & 0xFF) / 10;
/* 5201 */             if (i20 <= 2)
/*      */             {
/* 5203 */               localObject[i19] = 1;
/*      */             }
/* 5205 */             else if (i20 <= 7)
/*      */             {
/* 5207 */               localObject[i19] = 51;
/*      */             }
/*      */           }
/*      */           
/* 5211 */           if (i20 > 7)
/*      */           {
/*      */ 
/*      */ 
/* 5215 */             i6--;
/* 5216 */             localObject = lnxrou((byte[])localObject, i2 - 1);
/*      */             
/*      */ 
/* 5219 */             i4 = localObject.length - 1;
/* 5220 */             i16 = (localObject[i6] & 0xFF) % 10 == 1 ? 1 : 0;
/* 5221 */             i1 = 2 * ((localObject[i6] & 0xFF) - 192);
/*      */             
/*      */ 
/* 5224 */             i6++;
/*      */             
/* 5226 */             if ((i17 = (localObject[i6] & 0xFF) < 11 ? 1 : 0) != 0)
/*      */             {
/* 5228 */               i10 = ((localObject[i6] & 0xFF) - 1) / 10;
/*      */             }
/*      */             
/* 5231 */             i2 = 2 * i4 - i1 - (i16 != 0 ? 1 : 0);
/* 5232 */             i3 = i1 - (i17 != 0 ? 1 : 0);
/*      */             
/* 5234 */             if (i3 > i)
/*      */             {
/* 5236 */               throw new SQLException(CoreException.getMessage((byte)4));
/*      */             }
/*      */           }
/*      */         }
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
/*      */ 
/* 5253 */     i11 = i - (m > i3 ? m : i3);
/*      */     
/* 5255 */     if ((i11 != 0) && (localLnxLibThinFormat.LNXNFFIL))
/*      */     {
/* 5257 */       i19 = i11 + i5;
/* 5258 */       for (i20 = 0; i20 < i19; i5++) {
/* 5259 */         arrayOfChar1[i20] = ' ';i20++;
/*      */       }
/*      */     }
/*      */     
/* 5263 */     if ((!localLnxLibThinFormat.LNXNFFMI) && (!localLnxLibThinFormat.LNXNFFST))
/*      */     {
/*      */ 
/*      */ 
/* 5267 */       if (i15 != 0)
/*      */       {
/*      */ 
/* 5270 */         i19 = localLnxLibThinFormat.LNXNFFSH ? 43 : 32;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 5275 */         i19 = localLnxLibThinFormat.LNXNFFPT ? 40 : localLnxLibThinFormat.LNXNFFPR ? 60 : 45;
/*      */       }
/*      */       
/* 5278 */       if ((localLnxLibThinFormat.LNXNFFIL) || (i19 != 32))
/*      */       {
/*      */ 
/* 5281 */         arrayOfChar1[i5] = i19;
/* 5282 */         i5++;
/*      */       }
/*      */     }
/*      */     
/* 5286 */     if (localLnxLibThinFormat.LNXNFFIC)
/*      */     {
/*      */ 
/* 5289 */       str = "USD";
/*      */     }
/* 5291 */     else if (localLnxLibThinFormat.LNXNFFUN)
/*      */     {
/*      */ 
/* 5294 */       str = "$";
/*      */     }
/*      */     else
/*      */     {
/* 5298 */       str = "$";
/*      */     }
/*      */     
/* 5301 */     if (localLnxLibThinFormat.LNXNFFDS)
/*      */     {
/*      */ 
/*      */ 
/* 5305 */       arrayOfChar1[i5] = '$';
/* 5306 */       i5++;
/*      */     }
/* 5308 */     else if (localLnxLibThinFormat.LNXNFFCH)
/*      */     {
/* 5310 */       for (i19 = 0; i19 < str.length(); i5++) {
/* 5311 */         arrayOfChar1[i5] = str.charAt(i19);i19++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 5316 */     i7 = 0;
/* 5317 */     while ((i8 = localLnxLibThinFormat.lnxnfgps[i7] & 0x7F) != 0)
/*      */     {
/* 5319 */       if (i8 > i11) {
/*      */         break;
/*      */       }
/*      */       
/* 5323 */       i7++;
/*      */     }
/*      */     
/*      */ 
/* 5327 */     if ((i9 = m - (i3 > 0 ? i3 : 0)) > 0)
/*      */     {
/* 5329 */       while (i9 > 0)
/*      */       {
/*      */ 
/* 5332 */         arrayOfChar1[i5] = '0';
/* 5333 */         i5++;
/* 5334 */         i11++;
/*      */         
/*      */ 
/* 5337 */         while (i11 == i8)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/* 5342 */           arrayOfChar1[i5] = ',';
/* 5343 */           i5++;
/* 5344 */           i7++;
/*      */           
/* 5346 */           i8 = localLnxLibThinFormat.lnxnfgps[i7] & 0x7F;
/*      */         }
/* 5348 */         i9--;
/*      */       }
/*      */     }
/*      */     
/* 5352 */     if (i3 > 0)
/*      */     {
/*      */ 
/* 5355 */       while ((i3 > 0) && (i4 != 0))
/*      */       {
/*      */ 
/* 5358 */         if (i17 != 0)
/*      */         {
/* 5360 */           i13 = (localObject[i6] & 0xFF) - 1 - i10 * 10;
/* 5361 */           i6++;
/* 5362 */           i4--;
/*      */         }
/*      */         else
/*      */         {
/* 5366 */           i13 = ((localObject[i6] & 0xFF) - 1) / 10;
/* 5367 */           i10 = i13;
/*      */         }
/*      */         
/*      */ 
/* 5371 */         arrayOfChar1[i5] = this.lnx_chars[i13];
/* 5372 */         i5++;
/* 5373 */         i11++;
/*      */         
/* 5375 */         while (i11 == i8)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/* 5380 */           arrayOfChar1[i5] = ',';
/* 5381 */           i5++;
/* 5382 */           i7++;
/* 5383 */           i8 = localLnxLibThinFormat.lnxnfgps[i7] & 0x7F;
/*      */         }
/* 5385 */         i3--;
/* 5386 */         i17 = i17 == 0 ? 1 : 0;
/*      */       }
/*      */       
/*      */ 
/* 5390 */       while (i3 > 0)
/*      */       {
/*      */ 
/* 5393 */         arrayOfChar1[i5] = '0';
/* 5394 */         i5++;
/* 5395 */         i11++;
/*      */         
/*      */ 
/* 5398 */         while (i11 == i8)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/* 5403 */           arrayOfChar1[i5] = ',';
/* 5404 */           i5++;
/* 5405 */           i7++;
/* 5406 */           i8 = localLnxLibThinFormat.lnxnfgps[i7] & 0x7F;
/*      */         }
/*      */         
/* 5409 */         i3--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 5415 */     if (!localLnxLibThinFormat.LNXNFNRD)
/*      */     {
/*      */ 
/* 5418 */       if (localLnxLibThinFormat.LNXNFRDX)
/*      */       {
/*      */ 
/* 5421 */         arrayOfChar1[i5] = '.';
/* 5422 */         i5++;
/*      */ 
/*      */ 
/*      */       }
/* 5426 */       else if (localLnxLibThinFormat.LNXNFFRC)
/*      */       {
/*      */ 
/* 5429 */         for (i19 = 0; i19 < str.length(); i5++) {
/* 5430 */           arrayOfChar1[i5] = str.charAt(i19);i19++;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 5435 */         arrayOfChar1[i5] = '.';
/* 5436 */         i5++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 5441 */     i11 = 0;
/* 5442 */     if ((i12 = n - (i2 > 0 ? i2 : 0)) < 0)
/*      */     {
/* 5444 */       i12 = 0;
/*      */     }
/*      */     
/*      */ 
/* 5448 */     if (i3 != 0)
/*      */     {
/*      */ 
/* 5451 */       i3 = -i3;
/* 5452 */       i2 -= i3;
/* 5453 */       while (i3 != 0)
/*      */       {
/*      */ 
/* 5456 */         arrayOfChar1[i5] = '0';
/* 5457 */         i5++;
/* 5458 */         i3--;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 5463 */     while ((i4 != 0) && (i2 != 0))
/*      */     {
/* 5465 */       if (i17 != 0)
/*      */       {
/* 5467 */         i13 = (localObject[i6] & 0xFF) - 1 - i10 * 10;
/* 5468 */         i6++;
/* 5469 */         i4--;
/*      */       }
/*      */       else
/*      */       {
/* 5473 */         i13 = ((localObject[i6] & 0xFF) - 1) / 10;
/* 5474 */         i10 = i13;
/*      */       }
/*      */       
/*      */ 
/* 5478 */       arrayOfChar1[i5] = this.lnx_chars[i13];
/* 5479 */       i5++;
/* 5480 */       i2--;
/* 5481 */       i17 = i17 == 0 ? 1 : 0;
/*      */     }
/*      */     
/* 5484 */     while (i12 != 0)
/*      */     {
/*      */ 
/* 5487 */       arrayOfChar1[i5] = '0';
/* 5488 */       i5++;
/* 5489 */       i12--;
/*      */     }
/*      */     
/*      */ 
/* 5493 */     if (localLnxLibThinFormat.LNXNFFSN)
/*      */     {
/*      */ 
/* 5496 */       arrayOfChar1[i5] = 'E';
/* 5497 */       i5++;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 5502 */       arrayOfChar1[i5] = (i14 != 0 ? 45 : '+');
/* 5503 */       i5++;
/*      */       
/*      */ 
/* 5506 */       if (i1 > 99)
/*      */       {
/*      */ 
/* 5509 */         arrayOfChar1[i5] = '1';
/* 5510 */         i5++;
/* 5511 */         i1 -= 100;
/*      */       }
/*      */       
/*      */ 
/* 5515 */       arrayOfChar1[i5] = this.lnx_chars[(i1 / 10)];
/* 5516 */       i5++;
/*      */       
/*      */ 
/* 5519 */       arrayOfChar1[i5] = this.lnx_chars[(i1 % 10)];
/* 5520 */       i5++;
/*      */     }
/*      */     
/* 5523 */     if (localLnxLibThinFormat.LNXNFFCT)
/*      */     {
/*      */ 
/* 5526 */       for (i19 = 0; i19 < str.length(); i5++) {
/* 5527 */         arrayOfChar1[i5] = str.charAt(i19);i19++;
/*      */       }
/*      */     }
/*      */     
/* 5531 */     if (i15 != 0)
/*      */     {
/* 5533 */       if (localLnxLibThinFormat.LNXNFFST)
/*      */       {
/*      */ 
/*      */ 
/* 5537 */         arrayOfChar1[i5] = '+';
/* 5538 */         i5++;
/*      */       }
/* 5540 */       else if (((localLnxLibThinFormat.LNXNFFPR) || (localLnxLibThinFormat.LNXNFFMI) || (localLnxLibThinFormat.LNXNFFPT)) && (localLnxLibThinFormat.LNXNFFIL))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 5545 */         arrayOfChar1[i5] = ' ';
/* 5546 */         i5++;
/*      */       }
/*      */       
/*      */ 
/*      */     }
/* 5551 */     else if (localLnxLibThinFormat.LNXNFFPR)
/*      */     {
/*      */ 
/* 5554 */       arrayOfChar1[i5] = '>';
/* 5555 */       i5++;
/*      */ 
/*      */     }
/* 5558 */     else if (localLnxLibThinFormat.LNXNFFPT)
/*      */     {
/*      */ 
/* 5561 */       arrayOfChar1[i5] = ')';
/* 5562 */       i5++;
/*      */     }
/* 5564 */     else if ((localLnxLibThinFormat.LNXNFFMI) || (localLnxLibThinFormat.LNXNFFST))
/*      */     {
/*      */ 
/* 5567 */       arrayOfChar1[i5] = '-';
/* 5568 */       i5++;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 5573 */     i12 = i5;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 5578 */     if ((localLnxLibThinFormat.LNXNFFIL) && (i12 != k))
/*      */     {
/*      */ 
/*      */ 
/* 5582 */       i9 = k - i12;
/*      */       
/* 5584 */       char[] arrayOfChar2 = new char[i9];
/* 5585 */       for (i20 = 0; i20 < i9; i20++) {
/* 5586 */         arrayOfChar2[i20] = ' ';
/*      */       }
/* 5588 */       StringBuffer localStringBuffer = new StringBuffer();
/* 5589 */       localStringBuffer.append(arrayOfChar2);
/* 5590 */       localStringBuffer.append(arrayOfChar1, 0, i12);
/*      */       
/* 5592 */       return localStringBuffer.toString();
/*      */     }
/*      */     
/* 5595 */     return new String(arrayOfChar1, 0, i12);
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
/*      */   public String lnxnuc(byte[] paramArrayOfByte, int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 5615 */     byte[] arrayOfByte1 = new byte[22];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5637 */     int i13 = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5646 */     if (paramString != null)
/*      */     {
/* 5648 */       throw new SQLException(CoreException.getMessage((byte)12));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 5654 */     char[] arrayOfChar1 = this.lnx_chars;
/* 5655 */     int i5 = 46;
/*      */     
/*      */ 
/* 5658 */     if (paramInt == 0)
/*      */     {
/* 5660 */       throw new SQLException(CoreException.getMessage((byte)13));
/*      */     }
/*      */     
/*      */ 
/*      */     int k;
/*      */     
/* 5666 */     if ((k = paramInt >= 0 ? 1 : 0) == 0)
/*      */     {
/* 5668 */       paramInt = -paramInt;
/*      */     }
/*      */     
/* 5671 */     char[] arrayOfChar2 = new char[paramInt];
/*      */     int i7;
/*      */     byte[][] arrayOfByte;
/*      */     int i6;
/* 5675 */     if ((i7 = !NUMBER._isPositive(paramArrayOfByte) ? 1 : 0) != 0)
/*      */     {
/* 5677 */       arrayOfByte = LnxqComponents_N;
/* 5678 */       i6 = paramInt - 1;
/*      */     }
/*      */     else
/*      */     {
/* 5682 */       arrayOfByte = LnxqComponents_P;
/* 5683 */       i6 = paramInt;
/*      */     }
/*      */     
/* 5686 */     int i19 = 1;
/*      */     
/* 5688 */     while (i19 != 0)
/*      */     {
/*      */ 
/*      */ 
/* 5692 */       int m = k;
/*      */       
/*      */ 
/*      */ 
/* 5696 */       int i20 = paramArrayOfByte.length;
/*      */       int i4;
/* 5698 */       int i18; if (i20 == 1)
/*      */       {
/*      */ 
/* 5701 */         if ((paramArrayOfByte[0] & 0xFF) == 128)
/*      */         {
/*      */ 
/* 5704 */           if (m != 0)
/*      */           {
/* 5706 */             i4 = paramInt - 1;
/* 5707 */             arrayOfChar2[i4] = arrayOfChar1[0];
/*      */           }
/*      */           else
/*      */           {
/* 5711 */             if (paramInt < 5)
/*      */             {
/* 5713 */               throw new SQLException(CoreException.getMessage((byte)13));
/*      */             }
/*      */             
/* 5716 */             i4 = paramInt - 5;
/* 5717 */             arrayOfChar2[i4] = arrayOfChar1[0];
/* 5718 */             arrayOfChar2[(i4 + 1)] = arrayOfChar1[41];
/* 5719 */             arrayOfChar2[(i4 + 2)] = arrayOfChar1[10];
/* 5720 */             arrayOfChar2[(i4 + 3)] = arrayOfChar1[0];
/* 5721 */             arrayOfChar2[(i4 + 4)] = arrayOfChar1[0];
/*      */           }
/*      */           
/* 5724 */           if (i4 != 0) {
/* 5725 */             for (i18 = 0; i18 < i4; i18++) arrayOfChar2[i18] = arrayOfChar1[12];
/*      */           }
/* 5727 */           return new String(arrayOfChar2);
/*      */         }
/*      */         
/*      */ 
/* 5731 */         if (paramInt < 2)
/*      */         {
/* 5733 */           throw new SQLException(CoreException.getMessage((byte)13));
/*      */         }
/*      */         
/* 5736 */         i4 = paramInt - 2;
/* 5737 */         arrayOfChar2[i4] = arrayOfChar1[11];
/* 5738 */         arrayOfChar2[(i4 + 1)] = arrayOfChar1[21];
/*      */         
/* 5740 */         if (i4 != 0) {
/* 5741 */           for (i18 = 0; i18 < i4; i18++) arrayOfChar2[i18] = arrayOfChar1[12];
/*      */         }
/* 5743 */         return new String(arrayOfChar2);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 5749 */       if ((i20 == 2) && ((paramArrayOfByte[0] & 0xFF) == 255) && ((paramArrayOfByte[1] & 0xFF) == 101))
/*      */       {
/*      */ 
/*      */ 
/* 5753 */         i4 = paramInt - 1;
/* 5754 */         arrayOfChar2[i4] = arrayOfChar1[21];
/*      */         
/* 5756 */         if (i4 != 0) {
/* 5757 */           for (i18 = 0; i18 < i4; i18++) arrayOfChar2[i18] = arrayOfChar1[12];
/*      */         }
/* 5759 */         return new String(arrayOfChar2);
/*      */       }
/*      */       int i2;
/*      */       int i8;
/*      */       int i3;
/* 5764 */       if (i7 != 0)
/*      */       {
/* 5766 */         if (paramArrayOfByte[(i20 - 1)] == 102) i20--;
/* 5767 */         i2 = i20 - 1;
/* 5768 */         i8 = 2 * (62 - (paramArrayOfByte[0] & 0xFF)) + ((paramArrayOfByte[1] & 0xFF) < 92 ? 1 : 0);
/*      */         
/* 5770 */         i3 = 2 * i2 - ((paramArrayOfByte[1] & 0xFF) > 91 ? 1 : 0) - LnxqFirstDigit[paramArrayOfByte[i2]];
/*      */       }
/*      */       else
/*      */       {
/* 5774 */         i2 = i20 - 1;
/* 5775 */         i8 = 2 * ((paramArrayOfByte[0] & 0xFF) - 193) + ((paramArrayOfByte[1] & 0xFF) > 10 ? 1 : 0);
/*      */         
/* 5777 */         i3 = 2 * i2 - ((paramArrayOfByte[1] & 0xFF) < 11 ? 1 : 0) - LnxqFirstDigit[paramArrayOfByte[i2]];
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 5782 */       if (m != 0)
/*      */       {
/*      */ 
/* 5785 */         if (i8 >= 0)
/*      */         {
/*      */ 
/* 5788 */           m = i8 < i6 ? 1 : 0;
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/* 5793 */           m = (i8 >= -5) || (i3 - i8 <= i6) || (i6 <= 6) ? 1 : 0; } }
/*      */       int i9;
/*      */       int i12;
/*      */       int i14;
/*      */       int i15;
/*      */       int i16;
/* 5799 */       int i17; int i1; int i11; if (m != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5806 */         if (i8 >= 0)
/*      */         {
/* 5808 */           i9 = i6 <= i8 + 1 ? i6 : i6 - 1;
/*      */         }
/*      */         else
/*      */         {
/* 5812 */           i9 = i6 + i8;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 5817 */         if (i9 < i3)
/*      */         {
/*      */ 
/* 5820 */           paramArrayOfByte = lnxfpr(paramArrayOfByte, i9);
/*      */           
/* 5822 */           continue;
/*      */         }
/*      */         
/*      */ 
/* 5826 */         i19 = 0;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 5831 */         if (i8 >= 0)
/*      */         {
/* 5833 */           if (i3 > i8 + 1)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5839 */             i4 = i6 - (i3 + 1);
/* 5840 */             i12 = 1;
/* 5841 */             i13 = 0;
/* 5842 */             i14 = (i8 & 0x1) > 0 ? 0 : 1;
/* 5843 */             i15 = (i3 - i8 & 0x1) > 0 ? 0 : 1;
/* 5844 */             int i21 = Integer.MAX_VALUE;
/* 5845 */             i16 = i8 + 1 & i21 - 1;
/* 5846 */             i17 = i3 - (i8 + 1) & i21 - 1;
/*      */             
/*      */ 
/* 5849 */             i1 = i4;
/*      */ 
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/*      */ 
/* 5857 */             i4 = i6 - (i8 + 1);
/* 5858 */             i11 = i8 + 1 - i3;
/* 5859 */             i12 = 0;
/* 5860 */             i14 = (i8 & 0x1) > 0 ? 0 : 1;
/* 5861 */             i15 = (i11 & 0x1) > 0 ? 1 : 0;
/* 5862 */             i16 = i3 - (i14 != 0 ? 1 : 0) - (i15 != 0 ? 1 : 0);
/* 5863 */             i17 = 0;
/*      */             
/*      */ 
/* 5866 */             if (i11 != 0)
/*      */             {
/* 5868 */               n = i4 + (i7 != 0 ? 1 : 0) + i3;
/* 5869 */               for (i18 = 0; i18 < i11; i18++) {
/* 5870 */                 arrayOfChar2[(n + i18)] = arrayOfChar1[0];
/*      */               }
/*      */             }
/*      */             
/* 5874 */             i1 = i4;
/*      */ 
/*      */           }
/*      */           
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 5883 */           i4 = i6 - (i3 - i8);
/* 5884 */           int i10 = -(i8 + 1);
/* 5885 */           i12 = 0;
/* 5886 */           i14 = (i10 & 0x1) > 0 ? 1 : 0;
/* 5887 */           i15 = (i10 + i3 & 0x1) > 0 ? 1 : 0;
/* 5888 */           i16 = 0;
/* 5889 */           i17 = i3 - (i14 != 0 ? 1 : 0) - (i15 != 0 ? 1 : 0);
/*      */           
/*      */ 
/* 5892 */           n = i4;
/*      */           
/*      */ 
/* 5895 */           if (i7 != 0)
/*      */           {
/* 5897 */             arrayOfChar2[n] = arrayOfChar1[11];
/* 5898 */             n++;
/* 5899 */             i7 = 0;
/*      */           }
/*      */           
/*      */ 
/* 5903 */           arrayOfChar2[n] = i5;
/* 5904 */           n++;
/*      */           
/*      */ 
/* 5907 */           if (i10 != 0)
/*      */           {
/* 5909 */             for (i18 = 0; i18 < i10; i18++)
/* 5910 */               arrayOfChar2[(n + i18)] = arrayOfChar1[0];
/* 5911 */             n += i10;
/*      */           }
/*      */           
/*      */ 
/* 5915 */           i1 = n;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 5921 */         if (i4 != 0)
/*      */         {
/* 5923 */           for (i18 = 0; i18 < i4; i18++) { arrayOfChar2[i18] = arrayOfChar1[12];
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/* 5931 */         i9 = i6 - ((i8 > 99) || (i8 < -99) ? 6 : 5);
/*      */         
/*      */ 
/*      */ 
/* 5935 */         if (i9 < 2)
/*      */         {
/* 5937 */           throw new SQLException(CoreException.getMessage((byte)13));
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 5943 */         if (i9 < i3)
/*      */         {
/*      */ 
/* 5946 */           paramArrayOfByte = lnxfpr(paramArrayOfByte, i9);
/*      */           
/* 5948 */           continue;
/*      */         }
/*      */         
/*      */ 
/* 5952 */         i19 = 0;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 5957 */         if (i3 == 1)
/*      */         {
/*      */ 
/* 5960 */           i11 = i9 - 1;
/* 5961 */           i12 = 1;
/* 5962 */           i13 = (i8 & 0x1) > 0 ? 1 : 0;
/* 5963 */           i14 = i13 == 0 ? 1 : 0;
/* 5964 */           i15 = 0;
/* 5965 */           i16 = 0;
/* 5966 */           i17 = 0;
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/* 5971 */           i11 = i9 - i3;
/* 5972 */           i12 = 1;
/* 5973 */           i13 = (i8 & 0x1) > 0 ? 1 : 0;
/* 5974 */           i14 = i13 == 0 ? 1 : 0;
/* 5975 */           i15 = i13 == ((i3 & 0x1) > 0 ? 1 : 0) ? 1 : 0;
/* 5976 */           i16 = 0;
/* 5977 */           i17 = i3 - (i14 != 0 ? 1 : 2) - (i15 != 0 ? 1 : 0);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 5982 */         n = (i7 != 0 ? 1 : 0) + 1 + i3;
/*      */         
/*      */ 
/*      */ 
/* 5986 */         if (i11 != 0)
/*      */         {
/* 5988 */           for (i18 = 0; i18 < i11; i18++)
/* 5989 */             arrayOfChar2[(n + i18)] = arrayOfChar1[0];
/* 5990 */           n += i11;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 5995 */         if (i8 < 0)
/*      */         {
/* 5997 */           i8 = -i8;
/* 5998 */           arrayOfChar2[n] = arrayOfChar1[41];
/* 5999 */           arrayOfChar2[(n + 1)] = arrayOfChar1[11];
/*      */         }
/*      */         else
/*      */         {
/* 6003 */           arrayOfChar2[n] = arrayOfChar1[41];
/* 6004 */           arrayOfChar2[(n + 1)] = arrayOfChar1[10];
/*      */         }
/*      */         
/* 6007 */         if (i8 > 99)
/*      */         {
/* 6009 */           arrayOfChar2[(n + 2)] = arrayOfChar1[1];
/* 6010 */           arrayOfByte1[0] = ((byte)(i8 - 99));
/* 6011 */           arrayOfChar2[(n + 3)] = arrayOfChar1[LnxqComponents_P[(arrayOfByte1[0] & 0xFF)][0]];
/* 6012 */           arrayOfChar2[(n + 4)] = arrayOfChar1[LnxqComponents_P[(arrayOfByte1[0] & 0xFF)][1]];
/*      */         }
/*      */         else
/*      */         {
/* 6016 */           arrayOfByte1[0] = ((byte)(i8 + 1));
/* 6017 */           arrayOfChar2[(n + 2)] = arrayOfChar1[LnxqComponents_P[(arrayOfByte1[0] & 0xFF)][0]];
/* 6018 */           arrayOfChar2[(n + 3)] = arrayOfChar1[LnxqComponents_P[(arrayOfByte1[0] & 0xFF)][1]];
/*      */         }
/*      */         
/*      */ 
/* 6022 */         i1 = 0;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 6028 */       int i = 1;
/* 6029 */       int n = i1;
/*      */       
/*      */ 
/*      */ 
/* 6033 */       if (i7 != 0)
/*      */       {
/* 6035 */         arrayOfChar2[n] = arrayOfChar1[11];
/* 6036 */         n++;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 6041 */       if (i14 != 0)
/*      */       {
/* 6043 */         arrayOfChar2[n] = arrayOfChar1[arrayOfByte[paramArrayOfByte[i]][1]];
/* 6044 */         n++;
/* 6045 */         i++;
/*      */       }
/*      */       
/*      */       int j;
/*      */       
/* 6050 */       if (i16 != 0)
/*      */       {
/* 6052 */         j = n + i16;
/* 6053 */         while (n < j)
/*      */         {
/* 6055 */           arrayOfChar2[n] = arrayOfChar1[arrayOfByte[paramArrayOfByte[i]][0]];
/* 6056 */           n++;
/* 6057 */           arrayOfChar2[n] = arrayOfChar1[arrayOfByte[paramArrayOfByte[i]][1]];
/* 6058 */           n++;
/* 6059 */           i++;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 6065 */       if (i12 != 0)
/*      */       {
/* 6067 */         if (i13 != 0)
/*      */         {
/* 6069 */           arrayOfChar2[n] = arrayOfChar1[arrayOfByte[paramArrayOfByte[i]][0]];
/* 6070 */           n++;
/* 6071 */           arrayOfChar2[n] = i5;
/* 6072 */           n++;
/* 6073 */           arrayOfChar2[n] = arrayOfChar1[arrayOfByte[paramArrayOfByte[i]][1]];
/* 6074 */           n++;
/* 6075 */           i++;
/*      */         }
/*      */         else
/*      */         {
/* 6079 */           arrayOfChar2[n] = i5;
/* 6080 */           n++;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 6086 */       if (i17 != 0)
/*      */       {
/* 6088 */         j = n + i17;
/* 6089 */         while (n < j)
/*      */         {
/* 6091 */           arrayOfChar2[n] = arrayOfChar1[arrayOfByte[paramArrayOfByte[i]][0]];
/* 6092 */           n++;
/* 6093 */           arrayOfChar2[n] = arrayOfChar1[arrayOfByte[paramArrayOfByte[i]][1]];
/* 6094 */           n++;
/* 6095 */           i++;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 6101 */       if (i15 != 0)
/*      */       {
/* 6103 */         arrayOfChar2[n] = arrayOfChar1[arrayOfByte[paramArrayOfByte[i]][0]];
/* 6104 */         n++;
/* 6105 */         i++;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 6110 */     return new String(arrayOfChar2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] lnxren(double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 6119 */     byte[] arrayOfByte1 = new byte[20];
/* 6120 */     int i = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6130 */     boolean bool = paramDouble >= 0.0D;
/*      */     
/* 6132 */     paramDouble = Math.abs(paramDouble);
/*      */     
/*      */     int i1;
/*      */     
/* 6136 */     if (paramDouble < 1.0D)
/*      */     {
/* 6138 */       for (i1 = 0; i1 < 8; i1++)
/*      */       {
/* 6140 */         if (powerTable[i1][2] >= paramDouble)
/*      */         {
/* 6142 */           i -= (int)powerTable[i1][0];
/* 6143 */           paramDouble *= powerTable[i1][1];
/*      */         }
/*      */       }
/*      */       
/* 6147 */       if (paramDouble < 1.0D)
/*      */       {
/* 6149 */         i--;
/* 6150 */         paramDouble *= 100.0D;
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 6155 */       for (i1 = 0; i1 < 8; i1++)
/*      */       {
/* 6157 */         if (powerTable[i1][1] <= paramDouble)
/*      */         {
/* 6159 */           i += (int)powerTable[i1][0];
/* 6160 */           paramDouble *= powerTable[i1][2];
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6169 */     if (i > 62) {
/* 6170 */       throw new SQLException(CoreException.getMessage((byte)3));
/*      */     }
/*      */     
/* 6173 */     if (i < -65) {
/* 6174 */       throw new SQLException(CoreException.getMessage((byte)2));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 6180 */     int j = paramDouble >= 10.0D ? 0 : 1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6186 */     int k = 8;
/*      */     
/*      */ 
/*      */ 
/* 6190 */     int n = 0; for (int m = (byte)(int)paramDouble; n < k; n++)
/*      */     {
/* 6192 */       arrayOfByte1[n] = m;
/* 6193 */       paramDouble = (paramDouble - m) * 100.0D;
/* 6194 */       m = (byte)(int)paramDouble;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6201 */     n = 7;
/* 6202 */     if (j != 0)
/*      */     {
/* 6204 */       if (m >= 50) {
/* 6205 */         int tmp276_274 = n; byte[] tmp276_273 = arrayOfByte1;tmp276_273[tmp276_274] = ((byte)(tmp276_273[tmp276_274] + 1));
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */     }
/* 6212 */     else if ((i == 62) && ((arrayOfByte1[n] + 5) / 10 * 10 == 100)) {
/* 6213 */       arrayOfByte1[n] = ((byte)((arrayOfByte1[n] - 5) / 10 * 10));
/*      */     } else {
/* 6215 */       arrayOfByte1[n] = ((byte)((arrayOfByte1[n] + 5) / 10 * 10));
/*      */     }
/* 6219 */     for (; 
/*      */         
/* 6219 */         arrayOfByte1[n] == 100; 
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6229 */         tmp382_379[tmp382_380] = ((byte)(tmp382_379[tmp382_380] + 1)))
/*      */     {
/* 6221 */       if (n == 0)
/*      */       {
/* 6223 */         i++;
/* 6224 */         arrayOfByte1[n] = 1;
/* 6225 */         break;
/*      */       }
/* 6227 */       arrayOfByte1[n] = 0;
/* 6228 */       n--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 6234 */     n = 7;
/* 6235 */     while ((n != 0) && (arrayOfByte1[n] == 0))
/*      */     {
/* 6237 */       k = (byte)(k - 1);
/* 6238 */       n--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 6243 */     byte[] arrayOfByte2 = new byte[k + 1];
/* 6244 */     arrayOfByte2[0] = ((byte)i);
/* 6245 */     System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 1, k);
/*      */     
/*      */ 
/*      */ 
/* 6249 */     return NUMBER._toLnxFmt(arrayOfByte2, bool);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte[] lnxmin(long paramLong)
/*      */   {
/* 6256 */     byte[] arrayOfByte1 = new byte[20];
/*      */     
/*      */ 
/* 6259 */     byte[] arrayOfByte2 = new byte[20];
/* 6260 */     int k = 0;
/*      */     
/* 6262 */     if (paramLong == 0L)
/*      */     {
/* 6264 */       return NUMBER._makeZero();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 6269 */     boolean bool = paramLong >= 0L;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6280 */     for (int i = 0; paramLong != 0L; i++)
/*      */     {
/*      */ 
/* 6283 */       arrayOfByte1[i] = ((byte)(int)Math.abs(paramLong % 100L));
/* 6284 */       paramLong /= 100L;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 6289 */     i--;int j = (byte)i;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 6294 */     for (int m = j; k <= j; m--) {
/* 6295 */       arrayOfByte2[k] = arrayOfByte1[m];k = (byte)(k + 1);
/*      */     }
/*      */     
/*      */ 
/* 6299 */     while (i > 0)
/*      */     {
/* 6301 */       if (arrayOfByte2[(i--)] != 0) {
/*      */         break;
/*      */       }
/* 6304 */       k = (byte)(k - 1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 6309 */     byte[] arrayOfByte3 = new byte[k + 1];
/* 6310 */     arrayOfByte3[0] = j;
/* 6311 */     System.arraycopy(arrayOfByte2, 0, arrayOfByte3, 1, k);
/*      */     
/*      */ 
/*      */ 
/* 6315 */     return NUMBER._toLnxFmt(arrayOfByte3, bool);
/*      */   }
/*      */   
/*      */   public double lnxnur(byte[] paramArrayOfByte)
/*      */   {
/* 6320 */     double d1 = 0.0D;
/*      */     
/* 6322 */     int j = 1;
/*      */     
/*      */ 
/*      */ 
/* 6326 */     int i1 = 0;
/*      */     
/*      */ 
/*      */ 
/* 6330 */     int i4 = factorTable.length;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6337 */     byte[] arrayOfByte = NUMBER._fromLnxFmt(paramArrayOfByte);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6343 */     int i2 = arrayOfByte[1] < 10 ? 1 : 0;
/*      */     
/* 6345 */     double d3 = factorTable[0][0];
/* 6346 */     double d4 = factorTable[0][0] - (i4 - 20);
/*      */     int i;
/* 6348 */     int i3; if ((arrayOfByte[0] > d3) || (arrayOfByte[0] < d4))
/*      */     {
/* 6350 */       if (arrayOfByte[0] > d3)
/*      */       {
/* 6352 */         i = -1;
/* 6353 */         i3 = (int)(arrayOfByte[0] - d3);
/*      */       }
/*      */       else
/*      */       {
/* 6357 */         i = -1 + (i4 - 20);
/* 6358 */         i3 = (int)(arrayOfByte[0] - d4);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 6363 */       i = -1 + (int)(d3 - arrayOfByte[0]);
/* 6364 */       i3 = 0;
/*      */     }
/*      */     
/*      */ 
/* 6368 */     int n = arrayOfByte.length - 1;
/*      */     
/* 6370 */     if (i2 != 0 ? n > 8 : n >= 8)
/*      */     {
/* 6372 */       n = 8;
/* 6373 */       i1 = 1; }
/*      */     int m;
/*      */     double d2;
/* 6376 */     switch (n % 4)
/*      */     {
/*      */     case 3: 
/* 6379 */       m = (arrayOfByte[1] * 100 + arrayOfByte[2]) * 100 + arrayOfByte[3];
/* 6380 */       i += 3;
/* 6381 */       d2 = factorTable[i][1];
/* 6382 */       if (d2 < 1.0D) {
/* 6383 */         d1 = m / factorTable[i][2];
/*      */       } else
/* 6385 */         d1 = m * factorTable[i][1];
/* 6386 */       j += 3;
/* 6387 */       n -= 3;
/* 6388 */       break;
/*      */     case 2: 
/* 6390 */       m = arrayOfByte[1] * 100 + arrayOfByte[2];
/* 6391 */       i += 2;
/* 6392 */       d2 = factorTable[i][1];
/* 6393 */       if (d2 < 1.0D) {
/* 6394 */         d1 = m / factorTable[i][2];
/*      */       } else
/* 6396 */         d1 = m * factorTable[i][1];
/* 6397 */       j += 2;
/* 6398 */       n -= 2;
/* 6399 */       break;
/*      */     case 1: 
/* 6401 */       m = arrayOfByte[1];
/* 6402 */       i++;
/* 6403 */       d2 = factorTable[i][1];
/* 6404 */       if (d2 < 1.0D) {
/* 6405 */         d1 = m / factorTable[i][2];
/*      */       } else
/* 6407 */         d1 = m * factorTable[i][1];
/* 6408 */       j++;
/* 6409 */       n--;
/* 6410 */       break;
/*      */     default: 
/* 6412 */       d1 = 0.0D;
/*      */     }
/*      */     
/*      */     
/* 6416 */     while (n > 0)
/*      */     {
/* 6418 */       m = ((arrayOfByte[j] * 100 + arrayOfByte[(j + 1)]) * 100 + arrayOfByte[(j + 2)]) * 100 + arrayOfByte[(j + 3)];
/*      */       
/*      */ 
/*      */ 
/* 6422 */       i += 4;
/* 6423 */       d2 = factorTable[i][1];
/* 6424 */       if (d2 < 1.0D) {
/* 6425 */         d1 += m / factorTable[i][2];
/*      */       } else
/* 6427 */         d1 += m * factorTable[i][1];
/* 6428 */       j += 4;
/* 6429 */       n -= 4;
/*      */     }
/*      */     
/* 6432 */     if (i1 != 0)
/*      */     {
/* 6434 */       if (i2 != 0)
/*      */       {
/* 6436 */         if (arrayOfByte[j] > 50)
/*      */         {
/* 6438 */           m = 1;
/* 6439 */           d1 += m * factorTable[i][1];
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 6444 */         j--;
/* 6445 */         if (arrayOfByte[j] % 10 >= 5)
/*      */         {
/* 6447 */           m = (arrayOfByte[j] / 10 + 1) * 10;
/*      */         }
/*      */         else
/*      */         {
/* 6451 */           m = arrayOfByte[j] / 10 * 10;
/*      */         }
/* 6453 */         m -= arrayOfByte[j];
/* 6454 */         d1 += m * factorTable[i][1];
/*      */       }
/*      */     }
/*      */     
/* 6458 */     if (i3 != 0)
/*      */     {
/* 6460 */       int k = 0;
/* 6461 */       while (i3 > 0)
/*      */       {
/* 6463 */         if ((int)powerTable[k][0] <= i3)
/*      */         {
/* 6465 */           i3 -= (int)powerTable[k][0];
/* 6466 */           d1 *= powerTable[k][1];
/*      */         }
/* 6468 */         k++;
/*      */       }
/* 6470 */       while (i3 < 0)
/*      */       {
/* 6472 */         if ((int)powerTable[k][0] <= -i3)
/*      */         {
/* 6474 */           i3 += (int)powerTable[k][0];
/* 6475 */           d1 *= powerTable[k][2];
/*      */         }
/* 6477 */         k++;
/*      */       }
/*      */     }
/*      */     
/* 6481 */     return NUMBER._isPositive(paramArrayOfByte) ? d1 : -d1;
/*      */   }
/*      */   
/*      */   public long lnxsni(byte[] paramArrayOfByte) throws SQLException
/*      */   {
/* 6486 */     long l = 0L;
/* 6487 */     byte[] arrayOfByte = NUMBER._fromLnxFmt(paramArrayOfByte);
/* 6488 */     int i = arrayOfByte[0];
/* 6489 */     int j = (byte)(arrayOfByte.length - 1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6496 */     int k = j > i + 1 ? i + 1 : j;
/*      */     
/* 6498 */     for (int m = 0; m < k; m++) {
/* 6499 */       l = l * 100L + arrayOfByte[(m + 1)];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 6504 */     for (m = i - j; m >= 0; m--) {
/* 6505 */       l *= 100L;
/*      */     }
/* 6507 */     return NUMBER._isPositive(paramArrayOfByte) ? l : -l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 6513 */   private static final byte[] lnxqone = { -63, 2 };
/*      */   
/*      */ 
/* 6516 */   private static final byte[] lnxqtwo = { -63, 3 };
/*      */   
/*      */ 
/*      */   private static final int LNXQACOS = 0;
/*      */   
/*      */ 
/*      */   private static final int LNXQASIN = 1;
/*      */   
/*      */ 
/*      */   private static final int LNXQATAN = 2;
/*      */   
/*      */ 
/*      */   private static final int LNXQCOS = 3;
/*      */   
/*      */ 
/*      */   private static final int LNXQSIN = 4;
/*      */   
/*      */ 
/*      */   private static final int LNXQTAN = 5;
/*      */   
/*      */ 
/*      */   private static final int LNXQCSH = 6;
/*      */   
/*      */ 
/*      */   private static final int LNXQSNH = 7;
/*      */   
/*      */   private static final int LNXQTNH = 8;
/*      */   
/*      */   private static final int LNXQEXP = 9;
/*      */   
/*      */   private static final int LNXM_NUM = 22;
/*      */   
/*      */   private static final int LNXDIGS = 20;
/*      */   
/*      */   private static final int LNXSGNBT = 128;
/*      */   
/*      */   private static final int LNXEXPMX = 127;
/*      */   
/*      */   private static final int LNXEXPMN = 0;
/*      */   
/*      */   private static final int LNXEXPBS = 64;
/*      */   
/*      */   private static final int LNXBASE = 100;
/*      */   
/*      */   private static final int LNXMXFMT = 64;
/*      */   
/*      */   private static final int LNXMXOUT = 40;
/*      */   
/*      */   private static final int LNXDIV_LNXBASE_SQUARED = 10000;
/*      */   
/*      */   private static final int MINUB1MAXVAL = 255;
/*      */   
/*      */   private static final double ORANUM_FBASE = 100.0D;
/*      */   
/*      */   private static final int LNXQNOSGN = 127;
/*      */   
/*      */   private static final char LNXNFT_COMMA = ',';
/*      */   
/*      */   private static final int LNXBYTEMASK = 255;
/*      */   
/*      */   private static final int LNXSHORTMASK = 65535;
/*      */   
/* 6578 */   private static byte[] LnxqFirstDigit = { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6585 */   private static byte[] LnxqNegate = { 0, 101, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 73, 72, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6593 */   private static byte[] LnxqTruncate_P = { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 61, 61, 61, 61, 61, 61, 61, 61, 61, 61, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6600 */   private static byte[] LnxqTruncate_N = { 0, 0, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 61, 61, 61, 61, 61, 61, 61, 61, 61, 61, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6608 */   private static byte[] LnxqRound_P = { 0, 1, 1, 1, 1, 1, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 61, 61, 61, 61, 61, 61, 61, 61, 61, 61, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 101, 101, 101, 101, 101 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6616 */   private static byte[] LnxqRound_N = { 0, 0, 1, 1, 1, 1, 1, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 61, 61, 61, 61, 61, 61, 61, 61, 61, 61, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 91, 91, 91, 91, 91, 91, 91, 91, 91, 91, 101, 101, 101, 101, 101 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6625 */   private static byte[][] LnxqComponents_P = { { 0, 0 }, { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 }, { 0, 8 }, { 0, 9 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 5 }, { 1, 6 }, { 1, 7 }, { 1, 8 }, { 1, 9 }, { 2, 0 }, { 2, 1 }, { 2, 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, { 2, 6 }, { 2, 7 }, { 2, 8 }, { 2, 9 }, { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 3 }, { 3, 4 }, { 3, 5 }, { 3, 6 }, { 3, 7 }, { 3, 8 }, { 3, 9 }, { 4, 0 }, { 4, 1 }, { 4, 2 }, { 4, 3 }, { 4, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 4, 8 }, { 4, 9 }, { 5, 0 }, { 5, 1 }, { 5, 2 }, { 5, 3 }, { 5, 4 }, { 5, 5 }, { 5, 6 }, { 5, 7 }, { 5, 8 }, { 5, 9 }, { 6, 0 }, { 6, 1 }, { 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 5 }, { 6, 6 }, { 6, 7 }, { 6, 8 }, { 6, 9 }, { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 7, 6 }, { 7, 7 }, { 7, 8 }, { 7, 9 }, { 8, 0 }, { 8, 1 }, { 8, 2 }, { 8, 3 }, { 8, 4 }, { 8, 5 }, { 8, 6 }, { 8, 7 }, { 8, 8 }, { 8, 9 }, { 9, 0 }, { 9, 1 }, { 9, 2 }, { 9, 3 }, { 9, 4 }, { 9, 5 }, { 9, 6 }, { 9, 7 }, { 9, 8 }, { 9, 9 } };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6730 */   private static byte[][] LnxqComponents_N = { { 0, 0 }, { 0, 0 }, { 9, 9 }, { 9, 8 }, { 9, 7 }, { 9, 6 }, { 9, 5 }, { 9, 4 }, { 9, 3 }, { 9, 2 }, { 9, 1 }, { 9, 0 }, { 8, 9 }, { 8, 8 }, { 8, 7 }, { 8, 6 }, { 8, 5 }, { 8, 4 }, { 8, 3 }, { 8, 2 }, { 8, 1 }, { 8, 0 }, { 7, 9 }, { 7, 8 }, { 7, 7 }, { 7, 6 }, { 7, 5 }, { 7, 4 }, { 7, 3 }, { 7, 2 }, { 7, 1 }, { 7, 0 }, { 6, 9 }, { 6, 8 }, { 6, 7 }, { 6, 6 }, { 6, 5 }, { 6, 4 }, { 6, 3 }, { 6, 2 }, { 6, 1 }, { 6, 0 }, { 5, 9 }, { 5, 8 }, { 5, 7 }, { 5, 6 }, { 5, 5 }, { 5, 4 }, { 5, 3 }, { 5, 2 }, { 5, 1 }, { 5, 0 }, { 4, 9 }, { 4, 8 }, { 4, 7 }, { 4, 6 }, { 4, 5 }, { 4, 4 }, { 4, 3 }, { 4, 2 }, { 4, 1 }, { 4, 0 }, { 3, 9 }, { 3, 8 }, { 3, 7 }, { 3, 6 }, { 3, 5 }, { 3, 4 }, { 3, 3 }, { 3, 2 }, { 3, 1 }, { 3, 0 }, { 2, 9 }, { 2, 8 }, { 2, 7 }, { 2, 6 }, { 2, 5 }, { 2, 4 }, { 2, 3 }, { 2, 2 }, { 2, 1 }, { 2, 0 }, { 1, 9 }, { 1, 8 }, { 1, 7 }, { 1, 6 }, { 1, 5 }, { 1, 4 }, { 1, 3 }, { 1, 2 }, { 1, 1 }, { 1, 0 }, { 0, 9 }, { 0, 8 }, { 0, 7 }, { 0, 6 }, { 0, 5 }, { 0, 4 }, { 0, 3 }, { 0, 2 }, { 0, 1 }, { 0, 0 } };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 6836 */   private static byte[][] LnxqAdd_PPP = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, { 5, 0 }, { 6, 0 }, { 7, 0 }, { 8, 0 }, { 9, 0 }, { 10, 0 }, { 11, 0 }, { 12, 0 }, { 13, 0 }, { 14, 0 }, { 15, 0 }, { 16, 0 }, { 17, 0 }, { 18, 0 }, { 19, 0 }, { 20, 0 }, { 21, 0 }, { 22, 0 }, { 23, 0 }, { 24, 0 }, { 25, 0 }, { 26, 0 }, { 27, 0 }, { 28, 0 }, { 29, 0 }, { 30, 0 }, { 31, 0 }, { 32, 0 }, { 33, 0 }, { 34, 0 }, { 35, 0 }, { 36, 0 }, { 37, 0 }, { 38, 0 }, { 39, 0 }, { 40, 0 }, { 41, 0 }, { 42, 0 }, { 43, 0 }, { 44, 0 }, { 45, 0 }, { 46, 0 }, { 47, 0 }, { 48, 0 }, { 49, 0 }, { 50, 0 }, { 51, 0 }, { 52, 0 }, { 53, 0 }, { 54, 0 }, { 55, 0 }, { 56, 0 }, { 57, 0 }, { 58, 0 }, { 59, 0 }, { 60, 0 }, { 61, 0 }, { 62, 0 }, { 63, 0 }, { 64, 0 }, { 65, 0 }, { 66, 0 }, { 67, 0 }, { 68, 0 }, { 69, 0 }, { 70, 0 }, { 71, 0 }, { 72, 0 }, { 73, 0 }, { 74, 0 }, { 75, 0 }, { 76, 0 }, { 77, 0 }, { 78, 0 }, { 79, 0 }, { 80, 0 }, { 81, 0 }, { 82, 0 }, { 83, 0 }, { 84, 0 }, { 85, 0 }, { 86, 0 }, { 87, 0 }, { 88, 0 }, { 89, 0 }, { 90, 0 }, { 91, 0 }, { 92, 0 }, { 93, 0 }, { 94, 0 }, { 95, 0 }, { 96, 0 }, { 97, 0 }, { 98, 0 }, { 99, 0 }, { 100, 0 }, { 1, 1 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 5, 1 }, { 6, 1 }, { 7, 1 }, { 8, 1 }, { 9, 1 }, { 10, 1 }, { 11, 1 }, { 12, 1 }, { 13, 1 }, { 14, 1 }, { 15, 1 }, { 16, 1 }, { 17, 1 }, { 18, 1 }, { 19, 1 }, { 20, 1 }, { 21, 1 }, { 22, 1 }, { 23, 1 }, { 24, 1 }, { 25, 1 }, { 26, 1 }, { 27, 1 }, { 28, 1 }, { 29, 1 }, { 30, 1 }, { 31, 1 }, { 32, 1 }, { 33, 1 }, { 34, 1 }, { 35, 1 }, { 36, 1 }, { 37, 1 }, { 38, 1 }, { 39, 1 }, { 40, 1 }, { 41, 1 }, { 42, 1 }, { 43, 1 }, { 44, 1 }, { 45, 1 }, { 46, 1 }, { 47, 1 }, { 48, 1 }, { 49, 1 }, { 50, 1 }, { 51, 1 }, { 52, 1 }, { 53, 1 }, { 54, 1 }, { 55, 1 }, { 56, 1 }, { 57, 1 }, { 58, 1 }, { 59, 1 }, { 60, 1 }, { 61, 1 }, { 62, 1 }, { 63, 1 }, { 64, 1 }, { 65, 1 }, { 66, 1 }, { 67, 1 }, { 68, 1 }, { 69, 1 }, { 70, 1 }, { 71, 1 }, { 72, 1 }, { 73, 1 }, { 74, 1 }, { 75, 1 }, { 76, 1 }, { 77, 1 }, { 78, 1 }, { 79, 1 }, { 80, 1 }, { 81, 1 }, { 82, 1 }, { 83, 1 }, { 84, 1 }, { 85, 1 }, { 86, 1 }, { 87, 1 }, { 88, 1 }, { 89, 1 }, { 90, 1 }, { 91, 1 }, { 92, 1 }, { 93, 1 }, { 94, 1 }, { 95, 1 }, { 96, 1 }, { 97, 1 }, { 98, 1 }, { 99, 1 }, { 100, 1 } };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 7045 */   private static byte[][] LnxqAdd_NNN = { { 0, 2 }, { 0, 1 }, { 0, 0 }, { 0, 0 }, { 0, 0 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 5, 1 }, { 6, 1 }, { 7, 1 }, { 8, 1 }, { 9, 1 }, { 10, 1 }, { 11, 1 }, { 12, 1 }, { 13, 1 }, { 14, 1 }, { 15, 1 }, { 16, 1 }, { 17, 1 }, { 18, 1 }, { 19, 1 }, { 20, 1 }, { 21, 1 }, { 22, 1 }, { 23, 1 }, { 24, 1 }, { 25, 1 }, { 26, 1 }, { 27, 1 }, { 28, 1 }, { 29, 1 }, { 30, 1 }, { 31, 1 }, { 32, 1 }, { 33, 1 }, { 34, 1 }, { 35, 1 }, { 36, 1 }, { 37, 1 }, { 38, 1 }, { 39, 1 }, { 40, 1 }, { 41, 1 }, { 42, 1 }, { 43, 1 }, { 44, 1 }, { 45, 1 }, { 46, 1 }, { 47, 1 }, { 48, 1 }, { 49, 1 }, { 50, 1 }, { 51, 1 }, { 52, 1 }, { 53, 1 }, { 54, 1 }, { 55, 1 }, { 56, 1 }, { 57, 1 }, { 58, 1 }, { 59, 1 }, { 60, 1 }, { 61, 1 }, { 62, 1 }, { 63, 1 }, { 64, 1 }, { 65, 1 }, { 66, 1 }, { 67, 1 }, { 68, 1 }, { 69, 1 }, { 70, 1 }, { 71, 1 }, { 72, 1 }, { 73, 1 }, { 74, 1 }, { 75, 1 }, { 76, 1 }, { 77, 1 }, { 78, 1 }, { 79, 1 }, { 80, 1 }, { 81, 1 }, { 82, 1 }, { 83, 1 }, { 84, 1 }, { 85, 1 }, { 86, 1 }, { 87, 1 }, { 88, 1 }, { 89, 1 }, { 90, 1 }, { 91, 1 }, { 92, 1 }, { 93, 1 }, { 94, 1 }, { 95, 1 }, { 96, 1 }, { 97, 1 }, { 98, 1 }, { 99, 1 }, { 100, 1 }, { 101, 1 }, { 2, 2 }, { 3, 2 }, { 4, 2 }, { 5, 2 }, { 6, 2 }, { 7, 2 }, { 8, 2 }, { 9, 2 }, { 10, 2 }, { 11, 2 }, { 12, 2 }, { 13, 2 }, { 14, 2 }, { 15, 2 }, { 16, 2 }, { 17, 2 }, { 18, 2 }, { 19, 2 }, { 20, 2 }, { 21, 2 }, { 22, 2 }, { 23, 2 }, { 24, 2 }, { 25, 2 }, { 26, 2 }, { 27, 2 }, { 28, 2 }, { 29, 2 }, { 30, 2 }, { 31, 2 }, { 32, 2 }, { 33, 2 }, { 34, 2 }, { 35, 2 }, { 36, 2 }, { 37, 2 }, { 38, 2 }, { 39, 2 }, { 40, 2 }, { 41, 2 }, { 42, 2 }, { 43, 2 }, { 44, 2 }, { 45, 2 }, { 46, 2 }, { 47, 2 }, { 48, 2 }, { 49, 2 }, { 50, 2 }, { 51, 2 }, { 52, 2 }, { 53, 2 }, { 54, 2 }, { 55, 2 }, { 56, 2 }, { 57, 2 }, { 58, 2 }, { 59, 2 }, { 60, 2 }, { 61, 2 }, { 62, 2 }, { 63, 2 }, { 64, 2 }, { 65, 2 }, { 66, 2 }, { 67, 2 }, { 68, 2 }, { 69, 2 }, { 70, 2 }, { 71, 2 }, { 72, 2 }, { 73, 2 }, { 74, 2 }, { 75, 2 }, { 76, 2 }, { 77, 2 }, { 78, 2 }, { 79, 2 }, { 80, 2 }, { 81, 2 }, { 82, 2 }, { 83, 2 }, { 84, 2 }, { 85, 2 }, { 86, 2 }, { 87, 2 }, { 88, 2 }, { 89, 2 }, { 90, 2 }, { 91, 2 }, { 92, 2 }, { 93, 2 }, { 94, 2 }, { 95, 2 }, { 96, 2 }, { 97, 2 }, { 98, 2 }, { 99, 2 }, { 100, 2 }, { 101, 2 } };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 7258 */   private static byte[][] LnxqAdd_PNP = { { 0, 2 }, { 0, 1 }, { 0, 0 }, { 0, 0 }, { 1, 1 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 5, 1 }, { 6, 1 }, { 7, 1 }, { 8, 1 }, { 9, 1 }, { 10, 1 }, { 11, 1 }, { 12, 1 }, { 13, 1 }, { 14, 1 }, { 15, 1 }, { 16, 1 }, { 17, 1 }, { 18, 1 }, { 19, 1 }, { 20, 1 }, { 21, 1 }, { 22, 1 }, { 23, 1 }, { 24, 1 }, { 25, 1 }, { 26, 1 }, { 27, 1 }, { 28, 1 }, { 29, 1 }, { 30, 1 }, { 31, 1 }, { 32, 1 }, { 33, 1 }, { 34, 1 }, { 35, 1 }, { 36, 1 }, { 37, 1 }, { 38, 1 }, { 39, 1 }, { 40, 1 }, { 41, 1 }, { 42, 1 }, { 43, 1 }, { 44, 1 }, { 45, 1 }, { 46, 1 }, { 47, 1 }, { 48, 1 }, { 49, 1 }, { 50, 1 }, { 51, 1 }, { 52, 1 }, { 53, 1 }, { 54, 1 }, { 55, 1 }, { 56, 1 }, { 57, 1 }, { 58, 1 }, { 59, 1 }, { 60, 1 }, { 61, 1 }, { 62, 1 }, { 63, 1 }, { 64, 1 }, { 65, 1 }, { 66, 1 }, { 67, 1 }, { 68, 1 }, { 69, 1 }, { 70, 1 }, { 71, 1 }, { 72, 1 }, { 73, 1 }, { 74, 1 }, { 75, 1 }, { 76, 1 }, { 77, 1 }, { 78, 1 }, { 79, 1 }, { 80, 1 }, { 81, 1 }, { 82, 1 }, { 83, 1 }, { 84, 1 }, { 85, 1 }, { 86, 1 }, { 87, 1 }, { 88, 1 }, { 89, 1 }, { 90, 1 }, { 91, 1 }, { 92, 1 }, { 93, 1 }, { 94, 1 }, { 95, 1 }, { 96, 1 }, { 97, 1 }, { 98, 1 }, { 99, 1 }, { 100, 1 }, { 1, 2 }, { 2, 2 }, { 3, 2 }, { 4, 2 }, { 5, 2 }, { 6, 2 }, { 7, 2 }, { 8, 2 }, { 9, 2 }, { 10, 2 }, { 11, 2 }, { 12, 2 }, { 13, 2 }, { 14, 2 }, { 15, 2 }, { 16, 2 }, { 17, 2 }, { 18, 2 }, { 19, 2 }, { 20, 2 }, { 21, 2 }, { 22, 2 }, { 23, 2 }, { 24, 2 }, { 25, 2 }, { 26, 2 }, { 27, 2 }, { 28, 2 }, { 29, 2 }, { 30, 2 }, { 31, 2 }, { 32, 2 }, { 33, 2 }, { 34, 2 }, { 35, 2 }, { 36, 2 }, { 37, 2 }, { 38, 2 }, { 39, 2 }, { 40, 2 }, { 41, 2 }, { 42, 2 }, { 43, 2 }, { 44, 2 }, { 45, 2 }, { 46, 2 }, { 47, 2 }, { 48, 2 }, { 49, 2 }, { 50, 2 }, { 51, 2 }, { 52, 2 }, { 53, 2 }, { 54, 2 }, { 55, 2 }, { 56, 2 }, { 57, 2 }, { 58, 2 }, { 59, 2 }, { 60, 2 }, { 61, 2 }, { 62, 2 }, { 63, 2 }, { 64, 2 }, { 65, 2 }, { 66, 2 }, { 67, 2 }, { 68, 2 }, { 69, 2 }, { 70, 2 }, { 71, 2 }, { 72, 2 }, { 73, 2 }, { 74, 2 }, { 75, 2 }, { 76, 2 }, { 77, 2 }, { 78, 2 }, { 79, 2 }, { 80, 2 }, { 81, 2 }, { 82, 2 }, { 83, 2 }, { 84, 2 }, { 85, 2 }, { 86, 2 }, { 87, 2 }, { 88, 2 }, { 89, 2 }, { 90, 2 }, { 91, 2 }, { 92, 2 }, { 93, 2 }, { 94, 2 }, { 95, 2 }, { 96, 2 }, { 97, 2 }, { 98, 2 }, { 99, 2 }, { 100, 2 } };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 7470 */   private static byte[][] LnxqAdd_PNN = { { 0, 0 }, { 0, 1 }, { 0, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, { 5, 0 }, { 6, 0 }, { 7, 0 }, { 8, 0 }, { 9, 0 }, { 10, 0 }, { 11, 0 }, { 12, 0 }, { 13, 0 }, { 14, 0 }, { 15, 0 }, { 16, 0 }, { 17, 0 }, { 18, 0 }, { 19, 0 }, { 20, 0 }, { 21, 0 }, { 22, 0 }, { 23, 0 }, { 24, 0 }, { 25, 0 }, { 26, 0 }, { 27, 0 }, { 28, 0 }, { 29, 0 }, { 30, 0 }, { 31, 0 }, { 32, 0 }, { 33, 0 }, { 34, 0 }, { 35, 0 }, { 36, 0 }, { 37, 0 }, { 38, 0 }, { 39, 0 }, { 40, 0 }, { 41, 0 }, { 42, 0 }, { 43, 0 }, { 44, 0 }, { 45, 0 }, { 46, 0 }, { 47, 0 }, { 48, 0 }, { 49, 0 }, { 50, 0 }, { 51, 0 }, { 52, 0 }, { 53, 0 }, { 54, 0 }, { 55, 0 }, { 56, 0 }, { 57, 0 }, { 58, 0 }, { 59, 0 }, { 60, 0 }, { 61, 0 }, { 62, 0 }, { 63, 0 }, { 64, 0 }, { 65, 0 }, { 66, 0 }, { 67, 0 }, { 68, 0 }, { 69, 0 }, { 70, 0 }, { 71, 0 }, { 72, 0 }, { 73, 0 }, { 74, 0 }, { 75, 0 }, { 76, 0 }, { 77, 0 }, { 78, 0 }, { 79, 0 }, { 80, 0 }, { 81, 0 }, { 82, 0 }, { 83, 0 }, { 84, 0 }, { 85, 0 }, { 86, 0 }, { 87, 0 }, { 88, 0 }, { 89, 0 }, { 90, 0 }, { 91, 0 }, { 92, 0 }, { 93, 0 }, { 94, 0 }, { 95, 0 }, { 96, 0 }, { 97, 0 }, { 98, 0 }, { 99, 0 }, { 100, 0 }, { 101, 0 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 5, 1 }, { 6, 1 }, { 7, 1 }, { 8, 1 }, { 9, 1 }, { 10, 1 }, { 11, 1 }, { 12, 1 }, { 13, 1 }, { 14, 1 }, { 15, 1 }, { 16, 1 }, { 17, 1 }, { 18, 1 }, { 19, 1 }, { 20, 1 }, { 21, 1 }, { 22, 1 }, { 23, 1 }, { 24, 1 }, { 25, 1 }, { 26, 1 }, { 27, 1 }, { 28, 1 }, { 29, 1 }, { 30, 1 }, { 31, 1 }, { 32, 1 }, { 33, 1 }, { 34, 1 }, { 35, 1 }, { 36, 1 }, { 37, 1 }, { 38, 1 }, { 39, 1 }, { 40, 1 }, { 41, 1 }, { 42, 1 }, { 43, 1 }, { 44, 1 }, { 45, 1 }, { 46, 1 }, { 47, 1 }, { 48, 1 }, { 49, 1 }, { 50, 1 }, { 51, 1 }, { 52, 1 }, { 53, 1 }, { 54, 1 }, { 55, 1 }, { 56, 1 }, { 57, 1 }, { 58, 1 }, { 59, 1 }, { 60, 1 }, { 61, 1 }, { 62, 1 }, { 63, 1 }, { 64, 1 }, { 65, 1 }, { 66, 1 }, { 67, 1 }, { 68, 1 }, { 69, 1 }, { 70, 1 }, { 71, 1 }, { 72, 1 }, { 73, 1 }, { 74, 1 }, { 75, 1 }, { 76, 1 }, { 77, 1 }, { 78, 1 }, { 79, 1 }, { 80, 1 }, { 81, 1 }, { 82, 1 }, { 83, 1 }, { 84, 1 }, { 85, 1 }, { 86, 1 }, { 87, 1 }, { 88, 1 }, { 89, 1 }, { 90, 1 }, { 91, 1 }, { 92, 1 }, { 93, 1 }, { 94, 1 }, { 95, 1 }, { 96, 1 }, { 97, 1 }, { 98, 1 }, { 99, 1 }, { 100, 1 }, { 101, 1 } };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 7681 */   private static byte[] LnxsubIdentity = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 7701 */   private static byte[][] LnxqDigit_P = { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 }, { 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 }, { 31, 32, 33, 34, 35, 36, 37, 38, 39, 40 }, { 41, 42, 43, 44, 45, 46, 47, 48, 49, 50 }, { 51, 52, 53, 54, 55, 56, 57, 58, 59, 60 }, { 61, 62, 63, 64, 65, 66, 67, 68, 69, 70 }, { 71, 72, 73, 74, 75, 76, 77, 78, 79, 80 }, { 81, 82, 83, 84, 85, 86, 87, 88, 89, 90 }, { 91, 92, 93, 94, 95, 96, 97, 98, 99, 100 } };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 7719 */   private static byte[][] LnxqDigit_N = { { 101, 100, 99, 98, 97, 96, 95, 94, 93, 92 }, { 91, 90, 89, 88, 87, 86, 85, 84, 83, 82 }, { 81, 80, 79, 78, 77, 76, 75, 74, 73, 72 }, { 71, 70, 69, 68, 67, 66, 65, 64, 63, 62 }, { 61, 60, 59, 58, 57, 56, 55, 54, 53, 52 }, { 51, 50, 49, 48, 47, 46, 45, 44, 43, 42 }, { 41, 40, 39, 38, 37, 36, 35, 34, 33, 32 }, { 31, 30, 29, 28, 27, 26, 25, 24, 23, 22 }, { 21, 20, 19, 18, 17, 16, 15, 14, 13, 12 }, { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 } };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 7733 */   private static final double[][] powerTable = { { 128.0D, 1.0E256D, 1.0E-256D }, { 64.0D, 1.0E128D, 1.0E-128D }, { 32.0D, 1.0E64D, 1.0E-64D }, { 16.0D, 1.0E32D, 1.0E-32D }, { 8.0D, 1.0E16D, 1.0E-16D }, { 4.0D, 1.0E8D, 1.0E-8D }, { 2.0D, 10000.0D, 1.0E-4D }, { 1.0D, 100.0D, 0.01D } };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 7746 */   private static final double[][] factorTable = { { 15.0D, 1.0E30D, 1.0E-30D }, { 14.0D, 1.0E28D, 1.0E-28D }, { 13.0D, 1.0E26D, 1.0E-26D }, { 12.0D, 1.0E24D, 1.0E-24D }, { 11.0D, 1.0E22D, 1.0E-22D }, { 10.0D, 1.0E20D, 1.0E-20D }, { 9.0D, 1.0E18D, 1.0E-18D }, { 8.0D, 1.0E16D, 1.0E-16D }, { 7.0D, 1.0E14D, 1.0E-14D }, { 6.0D, 1.0E12D, 1.0E-12D }, { 5.0D, 1.0E10D, 1.0E-10D }, { 4.0D, 1.0E8D, 1.0E-8D }, { 3.0D, 1000000.0D, 1.0E-6D }, { 2.0D, 10000.0D, 1.0E-4D }, { 1.0D, 100.0D, 0.01D }, { 0.0D, 1.0D, 1.0D }, { -1.0D, 0.01D, 100.0D }, { -2.0D, 1.0E-4D, 10000.0D }, { -3.0D, 1.0E-6D, 1000000.0D }, { -4.0D, 1.0E-8D, 1.0E8D }, { -5.0D, 1.0E-10D, 1.0E10D }, { -6.0D, 1.0E-12D, 1.0E12D }, { -7.0D, 1.0E-14D, 1.0E14D }, { -8.0D, 1.0E-16D, 1.0E16D }, { -9.0D, 1.0E-18D, 1.0E18D }, { -10.0D, 1.0E-20D, 1.0E20D }, { -11.0D, 1.0E-22D, 1.0E22D }, { -12.0D, 1.0E-24D, 1.0E24D }, { -13.0D, 1.0E-26D, 1.0E26D }, { -14.0D, 1.0E-28D, 1.0E28D }, { -15.0D, 1.0E-30D, 1.0E30D }, { -16.0D, 1.0E-32D, 1.0E32D }, { -17.0D, 1.0E-34D, 1.0E34D }, { -18.0D, 1.0E-36D, 1.0E36D }, { -19.0D, 1.0E-38D, 1.0E38D }, { -20.0D, 1.0E-40D, 1.0E40D }, { -21.0D, 1.0E-42D, 1.0E42D }, { -22.0D, 1.0E-44D, 1.0E44D }, { -23.0D, 1.0E-46D, 1.0E46D }, { -24.0D, 1.0E-48D, 1.0E48D }, { -25.0D, 1.0E-50D, 1.0E50D }, { -26.0D, 1.0E-52D, 1.0E52D }, { -27.0D, 1.0E-54D, 1.0E54D }, { -28.0D, 1.0E-56D, 1.0E56D }, { -29.0D, 1.0E-58D, 1.0E58D }, { -30.0D, 1.0E-60D, 1.0E60D }, { -31.0D, 1.0E-62D, 1.0E62D }, { -32.0D, 1.0E-64D, 1.0E64D }, { -33.0D, 1.0E-66D, 1.0E66D }, { -34.0D, 1.0E-68D, 1.0E68D } };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 7806 */   private char[] lnx_chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', ' ', '.', ',', '$', '<', '>', '(', ')', '#', '~', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'i', 'l', 'm', 'p', 'r', 's', 't', 'v', 'A', 'B', 'C', 'D', 'E', 'F', 'I', 'L', 'M', 'P', 'R', 'S', 'T' };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private byte[] lnxqh2n(char[] paramArrayOfChar)
/*      */   {
/* 7924 */     int i = 0;
/* 7925 */     int j = paramArrayOfChar.length;
/*      */     
/* 7927 */     long[] arrayOfLong = new long[14];
/* 7928 */     int k = 13;
/* 7929 */     int m = 13;
/*      */     
/*      */ 
/* 7932 */     byte[] arrayOfByte1 = new byte[42];
/* 7933 */     int i1 = 1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 7942 */     while ((j != 0) && (paramArrayOfChar[(j - 1)] == 0))
/*      */     {
/* 7944 */       j--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 7949 */     while ((j != 0) && (paramArrayOfChar[i] == '0'))
/*      */     {
/* 7951 */       i++;
/* 7952 */       j--;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 7957 */     if (j == 0)
/*      */     {
/* 7959 */       return NUMBER._makeZero();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 7964 */     arrayOfLong[m] = 0L;
/*      */     
/* 7966 */     switch (j % 3)
/*      */     {
/*      */     case 0: 
/* 7969 */       arrayOfLong[m] = LNXQH2N_DIGIT(paramArrayOfChar[i], 8, arrayOfLong[m]);
/* 7970 */       i++;
/* 7971 */       j--;
/*      */     
/*      */     case 2: 
/* 7974 */       arrayOfLong[m] = LNXQH2N_DIGIT(paramArrayOfChar[i], 4, arrayOfLong[m]);
/* 7975 */       i++;
/* 7976 */       j--;
/*      */     
/*      */     case 1: 
/* 7979 */       arrayOfLong[m] = LNXQH2N_DIGIT(paramArrayOfChar[i], 0, arrayOfLong[m]);
/* 7980 */       i++;
/* 7981 */       j--;
/* 7982 */       break;
/*      */     }
/*      */     
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 7990 */     while (j != 0)
/*      */     {
/*      */ 
/* 7993 */       long l = 0L;
/* 7994 */       l = LNXQH2N_DIGIT(paramArrayOfChar[i], 8, l);
/* 7995 */       l = LNXQH2N_DIGIT(paramArrayOfChar[(i + 1)], 4, l);
/* 7996 */       l = LNXQH2N_DIGIT(paramArrayOfChar[(i + 2)], 0, l);
/*      */       
/* 7998 */       for (n = m; n >= k; n--)
/*      */       {
/* 8000 */         l += (arrayOfLong[n] << 12);
/* 8001 */         arrayOfLong[n] = (l % 1000000L);
/* 8002 */         l /= 1000000L;
/*      */       }
/*      */       
/* 8005 */       if (l != 0L)
/*      */       {
/* 8007 */         k--;
/* 8008 */         arrayOfLong[k] = l;
/*      */       }
/*      */       
/* 8011 */       i += 3;
/* 8012 */       j -= 3;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 8017 */     int i4 = 3 * (m - k) + 1;
/* 8018 */     i4 += (arrayOfLong[k] >= 100L ? 1 : 0);
/* 8019 */     i4 += (arrayOfLong[k] >= 10000L ? 1 : 0);
/*      */     
/*      */ 
/*      */ 
/* 8023 */     byte[] arrayOfByte3 = new byte[22];
/* 8024 */     int i5 = 0;
/*      */     
/*      */ 
/*      */ 
/* 8028 */     arrayOfByte3[i5] = ((byte)(i4 + 192));
/*      */     
/*      */     byte[] arrayOfByte2;
/*      */     int i3;
/* 8032 */     if (i4 > 20)
/*      */     {
/* 8034 */       arrayOfByte2 = arrayOfByte1;
/* 8035 */       i2 = i1;
/* 8036 */       i3 = 21;
/*      */     }
/*      */     else
/*      */     {
/* 8040 */       arrayOfByte2 = arrayOfByte3;
/* 8041 */       i2 = i5 + 1;
/* 8042 */       i3 = i4 + 1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 8047 */     switch (i4 % 3)
/*      */     {
/*      */     case 0: 
/* 8050 */       arrayOfByte2[i2] = ((byte)(int)(arrayOfLong[k] / 10000L + 1L));
/* 8051 */       i2++;
/*      */     
/*      */     case 2: 
/* 8054 */       arrayOfByte2[i2] = ((byte)(int)(arrayOfLong[k] % 10000L / 100L + 1L));
/* 8055 */       i2++;
/*      */     
/*      */     case 1: 
/* 8058 */       arrayOfByte2[i2] = ((byte)(int)(arrayOfLong[k] % 100L + 1L));
/* 8059 */       i2++;
/* 8060 */       break;
/*      */     }
/*      */     
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 8068 */     for (int n = k + 1; n <= m; n++)
/*      */     {
/*      */ 
/* 8071 */       arrayOfByte2[i2] = ((byte)(int)(arrayOfLong[n] / 10000L + 1L));
/* 8072 */       arrayOfByte2[(i2 + 1)] = ((byte)(int)(arrayOfLong[n] % 10000L / 100L + 1L));
/* 8073 */       arrayOfByte2[(i2 + 2)] = ((byte)(int)(arrayOfLong[n] % 100L + 1L));
/*      */       
/* 8075 */       i2 += 3;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 8080 */     if (i4 > 20)
/*      */     {
/*      */ 
/* 8083 */       i2 = i1 + 20;
/*      */       
/* 8085 */       if (arrayOfByte1[i2] > 50)
/*      */       {
/*      */ 
/* 8088 */         arrayOfByte1[(i1 - 1)] = 1;
/*      */         
/* 8090 */         i2--;
/* 8091 */         while (arrayOfByte1[i2] == 100)
/*      */         {
/* 8093 */           i2--;
/* 8094 */           i3--;
/*      */         }
/*      */         
/* 8097 */         int tmp672_670 = i2; byte[] tmp672_668 = arrayOfByte1;tmp672_668[tmp672_670] = ((byte)(tmp672_668[tmp672_670] + 1));
/*      */         
/* 8099 */         if (i2 < i1)
/*      */         {
/* 8101 */           i1--; int 
/* 8102 */             tmp692_690 = i5; byte[] tmp692_688 = arrayOfByte3;tmp692_688[tmp692_690] = ((byte)(tmp692_688[tmp692_690] + 1));
/* 8103 */           i3 = 2;
/*      */         }
/*      */       }
/* 8106 */       for (int i6 = 0; i6 < i3; i6++) {
/* 8107 */         arrayOfByte3[(i5 + 1 + i6)] = arrayOfByte1[(i1 + i6)];
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 8112 */     int i2 = i5 + (i3 - 1);
/*      */     
/* 8114 */     while (arrayOfByte2[i2] == 1)
/*      */     {
/* 8116 */       i2--;
/* 8117 */       i3--;
/*      */     }
/*      */     
/* 8120 */     byte[] arrayOfByte4 = new byte[i3];
/* 8121 */     System.arraycopy(arrayOfByte3, 0, arrayOfByte4, 0, i3);
/*      */     
/* 8123 */     return arrayOfByte4;
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
/*      */   private long LNXQH2N_DIGIT(char paramChar, int paramInt, long paramLong)
/*      */   {
/* 8139 */     if ((paramChar >= 'a') && (paramChar <= 'f'))
/*      */     {
/* 8141 */       l = paramLong + (paramChar - 'a' + 10 << paramInt);
/* 8142 */       return l;
/*      */     }
/*      */     
/* 8145 */     if ((paramChar >= 'A') && (paramChar <= 'F'))
/*      */     {
/* 8147 */       l = paramLong + (paramChar - 'A' + 10 << paramInt);
/* 8148 */       return l;
/*      */     }
/*      */     
/* 8151 */     long l = paramLong + (paramChar - '0' << paramInt);
/* 8152 */     return l;
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
/*      */   private byte[] lnxqtra(byte[] paramArrayOfByte, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 8184 */     byte[] arrayOfByte1 = null;
/* 8185 */     byte[] arrayOfByte2 = null;
/* 8186 */     byte[] arrayOfByte3 = null;
/*      */     
/* 8188 */     byte[] arrayOfByte4 = NUMBER.pi().shareBytes();
/* 8189 */     byte[] arrayOfByte5 = lnxmin(-1L);
/*      */     
/* 8191 */     long l = 0L;
/*      */     
/*      */ 
/*      */ 
/* 8195 */     if ((paramInt == 3) || (paramInt == 4) || (paramInt == 5))
/*      */     {
/*      */ 
/* 8198 */       arrayOfByte3 = lnxmul(lnxqtwo, arrayOfByte4);
/*      */       
/*      */ 
/* 8201 */       arrayOfByte1 = lnxabs(paramArrayOfByte);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 8208 */       arrayOfByte1 = lnxmod(arrayOfByte1, arrayOfByte3);
/* 8209 */       if (lnxcmp(arrayOfByte1, arrayOfByte4) > 0)
/*      */       {
/* 8211 */         arrayOfByte1 = lnxsub(arrayOfByte1, arrayOfByte3);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 8219 */       if (lnxsgn(paramArrayOfByte) == -1)
/*      */       {
/* 8221 */         arrayOfByte1 = lnxneg(arrayOfByte1);
/*      */       }
/*      */       
/*      */ 
/* 8225 */       arrayOfByte2 = lnxmul(arrayOfByte1, arrayOfByte1);
/*      */     }
/* 8227 */     else if (paramInt == 9)
/*      */     {
/*      */ 
/* 8230 */       arrayOfByte1 = lnxmod(paramArrayOfByte, lnxqone);
/* 8231 */       arrayOfByte3 = lnxsub(paramArrayOfByte, arrayOfByte1);
/*      */       
/*      */ 
/* 8234 */       if ((arrayOfByte3[0] & 0xFF) < 60)
/*      */       {
/* 8236 */         return NUMBER._makeZero();
/*      */       }
/*      */       
/*      */ 
/* 8240 */       if ((arrayOfByte3[0] & 0xFF) > 195)
/*      */       {
/* 8242 */         return NUMBER._makePosInf();
/*      */       }
/*      */       
/*      */ 
/* 8246 */       l = lnxsni(arrayOfByte3);
/*      */       
/*      */ 
/* 8249 */       arrayOfByte2 = lnxmul(arrayOfByte1, arrayOfByte1);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 8254 */       arrayOfByte1 = new byte[paramArrayOfByte.length];
/* 8255 */       System.arraycopy(paramArrayOfByte, 0, arrayOfByte1, 0, paramArrayOfByte.length);
/*      */       
/*      */ 
/* 8258 */       arrayOfByte2 = lnxmul(arrayOfByte1, arrayOfByte1);
/*      */     }
/*      */     
/*      */ 
/* 8262 */     byte[] arrayOfByte6 = null;
/* 8263 */     byte[] arrayOfByte7 = null;
/*      */     
/*      */     int i;
/*      */     
/* 8267 */     if ((paramInt != 4) && (paramInt != 7))
/*      */     {
/*      */ 
/* 8270 */       arrayOfByte3 = lnxqone;
/* 8271 */       arrayOfByte6 = lnxqone;
/* 8272 */       arrayOfByte7 = NUMBER._makeZero();
/*      */       
/*      */ 
/* 8275 */       int j = 0;
/*      */       for (;;)
/*      */       {
/* 8278 */         arrayOfByte3 = lnxmul(arrayOfByte2, arrayOfByte3);
/* 8279 */         i = (j + 1) * (j + 2);
/* 8280 */         j += 2;
/*      */         
/* 8282 */         arrayOfByte3 = lnxqIDiv(arrayOfByte3, i);
/*      */         
/* 8284 */         arrayOfByte7 = lnxadd(arrayOfByte7, arrayOfByte3);
/* 8285 */         arrayOfByte3 = lnxmul(arrayOfByte2, arrayOfByte3);
/* 8286 */         i = (j + 1) * (j + 2);
/* 8287 */         j += 2;
/*      */         
/* 8289 */         arrayOfByte3 = lnxqIDiv(arrayOfByte3, i);
/*      */         
/* 8291 */         arrayOfByte6 = lnxadd(arrayOfByte6, arrayOfByte3);
/*      */         
/* 8293 */         if ((arrayOfByte3[0] & 0xFF) + 20 >= (arrayOfByte6[0] & 0xFF)) { if ((arrayOfByte7[0] & 0xFF) == 255) {
/*      */             break;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 8301 */     byte[] arrayOfByte8 = null;
/* 8302 */     byte[] arrayOfByte9 = null;
/*      */     
/*      */ 
/* 8305 */     if ((paramInt != 3) && (paramInt != 6))
/*      */     {
/*      */ 
/* 8308 */       arrayOfByte3 = new byte[arrayOfByte1.length];
/* 8309 */       System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 0, arrayOfByte1.length);
/*      */       
/* 8311 */       arrayOfByte8 = new byte[arrayOfByte1.length];
/* 8312 */       System.arraycopy(arrayOfByte1, 0, arrayOfByte8, 0, arrayOfByte1.length);
/*      */       
/* 8314 */       arrayOfByte9 = NUMBER._makeZero();
/*      */       
/*      */ 
/* 8317 */       int k = 1;
/*      */       for (;;)
/*      */       {
/* 8320 */         arrayOfByte3 = lnxmul(arrayOfByte2, arrayOfByte3);
/* 8321 */         i = (k + 1) * (k + 2);
/* 8322 */         k += 2;
/*      */         
/* 8324 */         arrayOfByte3 = lnxqIDiv(arrayOfByte3, i);
/*      */         
/* 8326 */         arrayOfByte9 = lnxadd(arrayOfByte9, arrayOfByte3);
/* 8327 */         arrayOfByte3 = lnxmul(arrayOfByte2, arrayOfByte3);
/* 8328 */         i = (k + 1) * (k + 2);
/* 8329 */         k += 2;
/*      */         
/* 8331 */         arrayOfByte3 = lnxqIDiv(arrayOfByte3, i);
/*      */         
/* 8333 */         arrayOfByte8 = lnxadd(arrayOfByte8, arrayOfByte3);
/*      */         
/* 8335 */         if (((arrayOfByte3[0] & 0xFF) != 128) || (arrayOfByte3.length != 1))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/* 8340 */           if (((arrayOfByte3[0] & 0xFF) < 128) || ((arrayOfByte3[0] & 0xFF) + 20 >= (arrayOfByte8[0] & 0xFF)))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 8346 */             if (((arrayOfByte3[0] & 0xFF) >= 128) || ((arrayOfByte3[0] & 0xFF) <= (arrayOfByte8[0] & 0xFF) + 20))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 8352 */               if ((arrayOfByte9[0] & 0xFF) != 255) if ((arrayOfByte9[0] & 0xFF) == 0)
/*      */                   break;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 8359 */     byte[] arrayOfByte10 = null;
/* 8360 */     byte[] arrayOfByte11 = null;
/*      */     
/*      */ 
/* 8363 */     if ((paramInt == 3) || (paramInt == 4) || (paramInt == 5))
/*      */     {
/*      */ 
/* 8366 */       if ((paramInt == 3) || (paramInt == 5))
/*      */       {
/*      */ 
/* 8369 */         arrayOfByte10 = lnxsub(arrayOfByte6, arrayOfByte7);
/* 8370 */         if (lnxcmp(arrayOfByte10, lnxqone) > 0)
/*      */         {
/*      */ 
/* 8373 */           arrayOfByte10 = lnxqone;
/*      */         }
/* 8375 */         else if (lnxcmp(arrayOfByte10, arrayOfByte5) < 0)
/*      */         {
/*      */ 
/* 8378 */           arrayOfByte10 = arrayOfByte5;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 8383 */       if (paramInt == 3)
/*      */       {
/* 8385 */         return arrayOfByte10;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 8391 */       arrayOfByte11 = lnxsub(arrayOfByte8, arrayOfByte9);
/* 8392 */       if (lnxcmp(arrayOfByte11, lnxqone) > 0)
/*      */       {
/*      */ 
/* 8395 */         arrayOfByte11 = lnxqone;
/*      */       }
/* 8397 */       else if (lnxcmp(arrayOfByte11, arrayOfByte5) < 0)
/*      */       {
/*      */ 
/* 8400 */         arrayOfByte11 = arrayOfByte5;
/*      */       }
/*      */       
/*      */ 
/* 8404 */       if (paramInt == 4)
/*      */       {
/* 8406 */         return arrayOfByte11;
/*      */       }
/*      */       
/*      */ 
/* 8410 */       return lnxdiv(arrayOfByte11, arrayOfByte10);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 8415 */     if (paramInt == 6)
/*      */     {
/* 8417 */       return lnxadd(arrayOfByte6, arrayOfByte7);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 8422 */     if (paramInt == 7)
/*      */     {
/* 8424 */       return lnxadd(arrayOfByte8, arrayOfByte9);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 8429 */     arrayOfByte11 = lnxadd(arrayOfByte8, arrayOfByte9);
/* 8430 */     arrayOfByte10 = lnxadd(arrayOfByte6, arrayOfByte7);
/*      */     
/*      */ 
/*      */ 
/* 8434 */     if (paramInt == 8)
/*      */     {
/* 8436 */       return lnxdiv(arrayOfByte11, arrayOfByte10);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 8441 */     byte[] arrayOfByte12 = NUMBER.e().shareBytes();
/*      */     
/* 8443 */     byte[] arrayOfByte13 = lnxadd(arrayOfByte10, arrayOfByte11);
/* 8444 */     arrayOfByte1 = lnxpow(arrayOfByte12, (int)l);
/*      */     
/* 8446 */     arrayOfByte13 = lnxmul(arrayOfByte13, arrayOfByte1);
/*      */     
/* 8448 */     return arrayOfByte13;
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
/*      */   private byte[] lnxqtri(byte[] paramArrayOfByte, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 8481 */     Object localObject = null;
/* 8482 */     byte[] arrayOfByte1 = null;
/* 8483 */     byte[] arrayOfByte2 = null;
/*      */     
/*      */ 
/* 8486 */     byte[] arrayOfByte3 = NUMBER.pi().shareBytes();
/* 8487 */     byte[] arrayOfByte4 = lnxdiv(arrayOfByte3, lnxqtwo);
/*      */     
/*      */ 
/* 8490 */     if (paramInt == 2)
/*      */     {
/* 8492 */       if (NUMBER._isPosInf(paramArrayOfByte))
/*      */       {
/*      */ 
/* 8495 */         return arrayOfByte4;
/*      */       }
/* 8497 */       if (NUMBER._isNegInf(paramArrayOfByte))
/*      */       {
/*      */ 
/* 8500 */         return lnxneg(arrayOfByte4);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 8508 */     byte[] arrayOfByte5 = lnxabs(paramArrayOfByte);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 8513 */     if ((paramInt == 1) || (paramInt == 0))
/*      */     {
/*      */ 
/* 8516 */       if (lnxcmp(arrayOfByte5, lnxqone) > 0)
/*      */       {
/*      */ 
/*      */ 
/* 8520 */         throw new SQLException(CoreException.getMessage((byte)11));
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 8530 */       if ((arrayOfByte5[0] & 0xFF) <= 183)
/*      */       {
/* 8532 */         if (paramInt == 1)
/*      */         {
/* 8534 */           byte[] arrayOfByte6 = new byte[paramArrayOfByte.length];
/* 8535 */           System.arraycopy(paramArrayOfByte, 0, arrayOfByte6, 0, paramArrayOfByte.length);
/* 8536 */           return arrayOfByte6;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 8541 */         return lnxsub(arrayOfByte4, paramArrayOfByte);
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
/*      */ 
/*      */ 
/* 8554 */       localObject = lnxsub(lnxqone, arrayOfByte5);
/* 8555 */       arrayOfByte1 = lnxadd(lnxqone, arrayOfByte5);
/* 8556 */       arrayOfByte5 = lnxdiv((byte[])localObject, arrayOfByte1);
/*      */       
/* 8558 */       arrayOfByte5 = lnxsqr(arrayOfByte5);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     int i;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 8570 */     if ((i = lnxcmp(arrayOfByte5, lnxqone)) > 0)
/*      */     {
/* 8572 */       arrayOfByte5 = lnxdiv(lnxqone, arrayOfByte5);
/*      */     }
/*      */     
/*      */ 
/* 8576 */     localObject = new byte[arrayOfByte5.length];
/* 8577 */     System.arraycopy(arrayOfByte5, 0, localObject, 0, arrayOfByte5.length);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 8586 */     int j = 1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     for (;;)
/*      */     {
/* 8609 */       arrayOfByte1 = lnxtan((byte[])localObject);
/* 8610 */       arrayOfByte2 = lnxsub(arrayOfByte5, arrayOfByte1);
/* 8611 */       arrayOfByte1 = lnxmul(arrayOfByte1, arrayOfByte1);
/* 8612 */       arrayOfByte1 = lnxadd(arrayOfByte1, lnxqone);
/* 8613 */       arrayOfByte1 = lnxdiv(arrayOfByte2, arrayOfByte1);
/*      */       
/*      */ 
/*      */ 
/* 8617 */       int k = (arrayOfByte1[0] & 0xFF) >= 128 ? (arrayOfByte1[0] & 0xFF) - 193 : 62 - (arrayOfByte1[0] & 0xFF);
/*      */       
/*      */ 
/* 8620 */       int m = (localObject[0] & 0xFF) >= 128 ? (localObject[0] & 0xFF) - 193 : 62 - (localObject[0] & 0xFF);
/*      */       
/*      */ 
/*      */ 
/* 8624 */       if (((arrayOfByte1[0] & 0xFF) == 128) && (arrayOfByte1.length == 1)) {
/*      */         break;
/*      */       }
/*      */       
/*      */ 
/* 8629 */       if ((k & 0xFF) + 15 < (m & 0xFF)) {
/*      */         break;
/*      */       }
/*      */       
/*      */ 
/* 8634 */       if (j > 15) {
/*      */         break;
/*      */       }
/*      */       
/* 8638 */       localObject = lnxadd((byte[])localObject, arrayOfByte1);
/* 8639 */       j++;
/*      */     }
/*      */     
/*      */ 
/* 8643 */     if (i > 0)
/*      */     {
/*      */ 
/* 8646 */       localObject = lnxsub(arrayOfByte4, (byte[])localObject);
/*      */     }
/*      */     
/*      */ 
/* 8650 */     if ((localObject[0] & 0xFF) < 128)
/*      */     {
/* 8652 */       localObject = NUMBER._makeZero();
/*      */     }
/*      */     
/*      */ 
/* 8656 */     if (lnxcmp((byte[])localObject, arrayOfByte4) > 0)
/*      */     {
/* 8658 */       localObject = arrayOfByte4;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 8666 */     if ((paramInt == 1) || (paramInt == 0))
/*      */     {
/* 8668 */       localObject = lnxmul((byte[])localObject, lnxqtwo);
/*      */     }
/*      */     
/* 8671 */     switch (paramInt)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 1: 
/* 8678 */       if (NUMBER._isPositive(paramArrayOfByte))
/*      */       {
/* 8680 */         return lnxsub(arrayOfByte4, (byte[])localObject);
/*      */       }
/*      */       
/*      */ 
/* 8684 */       return lnxsub((byte[])localObject, arrayOfByte4);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 0: 
/* 8692 */       if (NUMBER._isPositive(paramArrayOfByte))
/*      */       {
/* 8694 */         return (byte[])localObject;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 8699 */       return lnxsub(arrayOfByte3, (byte[])localObject);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 2: 
/* 8707 */       if (NUMBER._isPositive(paramArrayOfByte))
/*      */       {
/* 8709 */         return (byte[])localObject;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 8714 */       return lnxneg((byte[])localObject);
/*      */     }
/*      */     
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 8723 */     throw new SQLException(CoreException.getMessage((byte)11));
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
/*      */   private int lnxcmp(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
/*      */   {
/* 8738 */     return NUMBER.compareBytes(paramArrayOfByte1, paramArrayOfByte2);
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
/*      */   private int lnxsgn(byte[] paramArrayOfByte)
/*      */   {
/* 8754 */     if (NUMBER._isZero(paramArrayOfByte)) return 0;
/* 8755 */     if (NUMBER._isPositive(paramArrayOfByte)) {
/* 8756 */       return 1;
/*      */     }
/* 8758 */     return -1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private byte[] lnxqIDiv(byte[] paramArrayOfByte, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 8770 */     byte[] arrayOfByte = lnxmin(paramInt);
/*      */     
/*      */ 
/* 8773 */     return lnxdiv(paramArrayOfByte, arrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void _negateNumber(byte[] paramArrayOfByte)
/*      */   {
/* 8784 */     for (int i = paramArrayOfByte.length - 1; i > 0; i--)
/*      */     {
/* 8786 */       paramArrayOfByte[i] = LnxqNegate[paramArrayOfByte[i]];
/*      */     }
/* 8788 */     paramArrayOfByte[0] = ((byte)(paramArrayOfByte[0] ^ 0xFFFFFFFF));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static byte[] _setLength(byte[] paramArrayOfByte, int paramInt)
/*      */   {
/* 8798 */     boolean bool = NUMBER._isPositive(paramArrayOfByte);
/*      */     
/*      */     byte[] arrayOfByte;
/*      */     
/* 8802 */     if (bool)
/*      */     {
/* 8804 */       arrayOfByte = new byte[paramInt];
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/* 8809 */     else if ((paramInt <= 20) && (paramArrayOfByte[(paramInt - 1)] != 102))
/*      */     {
/*      */ 
/* 8812 */       arrayOfByte = new byte[paramInt + 1];
/* 8813 */       arrayOfByte[paramInt] = 102;
/*      */     }
/*      */     else
/*      */     {
/* 8817 */       arrayOfByte = new byte[paramInt];
/*      */     }
/*      */     
/* 8820 */     System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, paramInt);
/* 8821 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */   private static class lnxqc
/*      */   {
/*      */     static final int LNXQC0 = 0;
/*      */     static final int LNXQC1 = 1;
/*      */     static final int LNXQC2 = 2;
/*      */     static final int LNXQC3 = 3;
/*      */     static final int LNXQC4 = 4;
/*      */     static final int LNXQC5 = 5;
/*      */     static final int LNXQC6 = 6;
/*      */     static final int LNXQC7 = 7;
/*      */     static final int LNXQC8 = 8;
/*      */     static final int LNXQC9 = 9;
/*      */     static final int LNXQCPLUS = 10;
/*      */     static final int LNXQCMINUS = 11;
/*      */     static final int LNXQCSPACE = 12;
/*      */     static final int LNXQCDOT = 13;
/*      */     static final int LNXQCCOMMA = 14;
/*      */     static final int LNXQCDOLLR = 15;
/*      */     static final int LNXQCLT = 16;
/*      */     static final int LNXQCGRT = 17;
/*      */     static final int LNXQCLPT = 18;
/*      */     static final int LNXQCRPT = 19;
/*      */     static final int LNXQCHASH = 20;
/*      */     static final int LNXQCTILDE = 21;
/*      */     static final int LNXQCASML = 22;
/*      */     static final int LNXQCBSML = 23;
/*      */     static final int LNXQCCSML = 24;
/*      */     static final int LNXQCDSML = 25;
/*      */     static final int LNXQCESML = 26;
/*      */     static final int LNXQCFSML = 27;
/*      */     static final int LNXQCGSML = 28;
/*      */     static final int LNXQCISML = 29;
/*      */     static final int LNXQCLSML = 30;
/*      */     static final int LNXQCMSML = 31;
/*      */     static final int LNXQCPSML = 32;
/*      */     static final int LNXQCRSML = 33;
/*      */     static final int LNXQCSSML = 34;
/*      */     static final int LNXQCTSML = 35;
/*      */     static final int LNXQCVSML = 36;
/*      */     static final int LNXQCALRG = 37;
/*      */     static final int LNXQCBLRG = 38;
/*      */     static final int LNXQCCLRG = 39;
/*      */     static final int LNXQCDLRG = 40;
/*      */     static final int LNXQCELRG = 41;
/*      */     static final int LNXQCFLRG = 42;
/*      */     static final int LNXQCILRG = 43;
/*      */     static final int LNXQCLLRG = 44;
/*      */     static final int LNXQCMLRG = 45;
/*      */     static final int LNXQCPLRG = 46;
/*      */     static final int LNXQCRLRG = 47;
/*      */     static final int LNXQCSLRG = 48;
/*      */     static final int LNXQCTLRG = 49;
/*      */   }
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/LnxLibThin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */