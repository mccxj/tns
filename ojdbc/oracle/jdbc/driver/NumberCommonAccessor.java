/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.sql.SQLException;
/*      */ import java.util.Map;
/*      */ import oracle.sql.Datum;
/*      */ import oracle.sql.NUMBER;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class NumberCommonAccessor
/*      */   extends Accessor
/*      */ {
/*      */   static final boolean GET_XXX_ROUNDS = false;
/*      */   
/*      */   void init(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*   27 */     init(paramOracleStatement, 6, 6, paramShort, paramBoolean);
/*   28 */     initForDataAccess(paramInt2, paramInt1, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void init(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, short paramShort)
/*      */     throws SQLException
/*      */   {
/*   37 */     init(paramOracleStatement, 6, 6, paramShort, false);
/*   38 */     initForDescribe(paramInt1, paramInt2, paramBoolean, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramShort, null);
/*      */     
/*   40 */     initForDataAccess(0, paramInt2, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void initForDataAccess(int paramInt1, int paramInt2, String paramString)
/*      */     throws SQLException
/*      */   {
/*   48 */     if (paramInt1 != 0) {
/*   49 */       this.externalType = paramInt1;
/*      */     }
/*   51 */     this.internalTypeMaxLength = 21;
/*      */     
/*   53 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*   54 */       this.internalTypeMaxLength = paramInt2;
/*      */     }
/*   56 */     this.byteLength = (this.internalTypeMaxLength + 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   int getInt(int paramInt)
/*      */     throws SQLException
/*      */   {
/*   74 */     int i = 0;
/*      */     Object localObject;
/*   76 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*   78 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*   79 */       ((SQLException)localObject).fillInStackTrace();
/*   80 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*   85 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*   87 */       localObject = this.rowSpaceByte;
/*   88 */       int j = this.columnIndex + this.byteLength * paramInt + 1;
/*   89 */       int k = localObject[(j - 1)];
/*   90 */       int m = localObject[j];
/*      */       
/*   92 */       int n = 0;
/*      */       int i1;
/*      */       int i2;
/*      */       int i3;
/*   96 */       int i4; int i7; if ((m & 0xFFFFFF80) != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  101 */         i1 = (byte)((m & 0xFF7F) - 65);
/*  102 */         i2 = (byte)(k - 1);
/*      */         
/*  104 */         i3 = i2 > i1 + 1 ? i1 + 2 : i2 + 1;
/*  105 */         i4 = i3 + j;
/*      */         
/*  107 */         if (i1 >= 4)
/*      */         {
/*  109 */           if (i1 > 4)
/*      */           {
/*      */ 
/*  112 */             throwOverflow();
/*      */           }
/*  114 */           long l1 = 0L;
/*      */           
/*  116 */           if (i3 > 1)
/*      */           {
/*  118 */             l1 = localObject[(j + 1)] - 1;
/*      */             
/*  120 */             for (i7 = 2 + j; i7 < i4; i7++) {
/*  121 */               l1 = l1 * 100L + (localObject[i7] - 1);
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  128 */           for (i7 = i1 - i2; i7 >= 0; i7--) {
/*  129 */             l1 *= 100L;
/*      */           }
/*  131 */           if (l1 > 2147483647L) {
/*  132 */             throwOverflow();
/*      */           }
/*  134 */           n = (int)l1;
/*      */         }
/*      */         else
/*      */         {
/*  138 */           if (i3 > 1)
/*      */           {
/*  140 */             n = localObject[(j + 1)] - 1;
/*      */             
/*  142 */             for (i5 = 2 + j; i5 < i4; i5++) {
/*  143 */               n = n * 100 + (localObject[i5] - 1);
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  150 */           for (int i5 = i1 - i2; i5 >= 0; i5--) {
/*  151 */             n *= 100;
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/*  159 */         i1 = (byte)(((m ^ 0xFFFFFFFF) & 0xFF7F) - 65);
/*  160 */         i2 = (byte)(k - 1);
/*      */         
/*  162 */         if ((i2 != 20) || (localObject[(j + i2)] == 102)) {
/*  163 */           i2 = (byte)(i2 - 1);
/*      */         }
/*  165 */         i3 = i2 > i1 + 1 ? i1 + 2 : i2 + 1;
/*  166 */         i4 = i3 + j;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  176 */         if (i1 >= 4)
/*      */         {
/*  178 */           if (i1 > 4)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  192 */             throwOverflow();
/*      */           }
/*      */           
/*  195 */           long l2 = 0L;
/*      */           
/*  197 */           if (i3 > 1)
/*      */           {
/*  199 */             l2 = 101 - localObject[(j + 1)];
/*      */             
/*  201 */             for (i7 = 2 + j; i7 < i4; i7++) {
/*  202 */               l2 = l2 * 100L + (101 - localObject[i7]);
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  209 */           for (i7 = i1 - i2; i7 >= 0; i7--) {
/*  210 */             l2 *= 100L;
/*      */           }
/*  212 */           l2 = -l2;
/*      */           
/*  214 */           if (l2 < -2147483648L) {
/*  215 */             throwOverflow();
/*      */           }
/*  217 */           n = (int)l2;
/*      */         }
/*      */         else
/*      */         {
/*  221 */           if (i3 > 1)
/*      */           {
/*  223 */             n = 101 - localObject[(j + 1)];
/*      */             
/*  225 */             for (i6 = 2 + j; i6 < i4; i6++) {
/*  226 */               n = n * 100 + (101 - localObject[i6]);
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  233 */           for (int i6 = i1 - i2; i6 >= 0; i6--) {
/*  234 */             n *= 100;
/*      */           }
/*  236 */           n = -n;
/*      */         }
/*      */       }
/*      */       
/*  240 */       i = n;
/*      */     }
/*      */     
/*  243 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean getBoolean(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  259 */     boolean bool = false;
/*      */     Object localObject;
/*  261 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*  263 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  264 */       ((SQLException)localObject).fillInStackTrace();
/*  265 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  270 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*  272 */       localObject = this.rowSpaceByte;
/*  273 */       int i = this.columnIndex + this.byteLength * paramInt + 1;
/*  274 */       int j = localObject[(i - 1)];
/*      */       
/*  276 */       bool = (j != 1) || (localObject[i] != Byte.MIN_VALUE);
/*      */     }
/*      */     
/*  279 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   short getShort(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  295 */     short s = 0;
/*      */     Object localObject;
/*  297 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*  299 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  300 */       ((SQLException)localObject).fillInStackTrace();
/*  301 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  306 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*      */ 
/*  309 */       localObject = this.rowSpaceByte;
/*  310 */       int i = this.columnIndex + this.byteLength * paramInt + 1;
/*  311 */       int j = localObject[(i - 1)];
/*      */       
/*  313 */       int k = localObject[i];
/*      */       
/*  315 */       int m = 0;
/*      */       int n;
/*      */       int i1;
/*      */       int i2;
/*  319 */       int i3; int i4; if ((k & 0xFFFFFF80) != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  324 */         n = (byte)((k & 0xFF7F) - 65);
/*      */         
/*  326 */         if (n > 2)
/*      */         {
/*      */ 
/*  329 */           throwOverflow();
/*      */         }
/*  331 */         i1 = (byte)(j - 1);
/*      */         
/*  333 */         i2 = i1 > n + 1 ? n + 2 : i1 + 1;
/*  334 */         i3 = i2 + i;
/*      */         
/*  336 */         if (i2 > 1)
/*      */         {
/*  338 */           m = localObject[(i + 1)] - 1;
/*      */           
/*  340 */           for (i4 = 2 + i; i4 < i3; i4++) {
/*  341 */             m = m * 100 + (localObject[i4] - 1);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  348 */         for (i4 = n - i1; i4 >= 0; i4--) {
/*  349 */           m *= 100;
/*      */         }
/*  351 */         if ((n == 2) && 
/*  352 */           (m > 32767)) {
/*  353 */           throwOverflow();
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  360 */         n = (byte)(((k ^ 0xFFFFFFFF) & 0xFF7F) - 65);
/*      */         
/*  362 */         if (n > 2)
/*      */         {
/*      */ 
/*  365 */           throwOverflow();
/*      */         }
/*  367 */         i1 = (byte)(j - 1);
/*      */         
/*  369 */         if ((i1 != 20) || (localObject[(i + i1)] == 102)) {
/*  370 */           i1 = (byte)(i1 - 1);
/*      */         }
/*  372 */         i2 = i1 > n + 1 ? n + 2 : i1 + 1;
/*  373 */         i3 = i2 + i;
/*      */         
/*  375 */         if (i2 > 1)
/*      */         {
/*  377 */           m = 101 - localObject[(i + 1)];
/*      */           
/*  379 */           for (i4 = 2 + i; i4 < i3; i4++) {
/*  380 */             m = m * 100 + (101 - localObject[i4]);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  387 */         for (i4 = n - i1; i4 >= 0; i4--) {
/*  388 */           m *= 100;
/*      */         }
/*  390 */         m = -m;
/*      */         
/*  392 */         if ((n == 2) && 
/*  393 */           (m < 32768)) {
/*  394 */           throwOverflow();
/*      */         }
/*      */       }
/*  397 */       s = (short)m;
/*      */     }
/*      */     
/*  400 */     return s;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   byte getByte(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  416 */     byte b = 0;
/*      */     Object localObject;
/*  418 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*  420 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  421 */       ((SQLException)localObject).fillInStackTrace();
/*  422 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  427 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*      */ 
/*  430 */       localObject = this.rowSpaceByte;
/*  431 */       int i = this.columnIndex + this.byteLength * paramInt + 1;
/*  432 */       int j = localObject[(i - 1)];
/*      */       
/*  434 */       int k = localObject[i];
/*      */       
/*  436 */       int m = 0;
/*      */       
/*      */       int n;
/*      */       int i1;
/*  440 */       if ((k & 0xFFFFFF80) != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  445 */         n = (byte)((k & 0xFF7F) - 65);
/*      */         
/*  447 */         if (n > 1)
/*      */         {
/*      */ 
/*  450 */           throwOverflow();
/*      */         }
/*  452 */         i1 = (byte)(j - 1);
/*      */         
/*  454 */         if (i1 > n + 1)
/*      */         {
/*  456 */           switch (n)
/*      */           {
/*      */           default: 
/*      */             break;
/*      */           
/*      */ 
/*      */ 
/*      */           case -1: 
/*      */             break;
/*      */           
/*      */ 
/*      */ 
/*      */           case 0: 
/*  469 */             m = localObject[(i + 1)] - 1;
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*  474 */             break;
/*      */           
/*      */           case 1: 
/*  477 */             m = (localObject[(i + 1)] - 1) * 100 + (localObject[(i + 2)] - 1);
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  483 */             if (m <= 127) break;
/*  484 */             throwOverflow();break;
/*      */           
/*      */           }
/*      */           
/*      */         }
/*  489 */         else if (i1 == 1)
/*      */         {
/*  491 */           if (n == 1)
/*      */           {
/*  493 */             m = (localObject[(i + 1)] - 1) * 100;
/*      */             
/*  495 */             if (m > 127) {
/*  496 */               throwOverflow();
/*      */             }
/*      */           } else {
/*  499 */             m = localObject[(i + 1)] - 1;
/*      */           }
/*  501 */         } else if (i1 == 2)
/*      */         {
/*  503 */           m = (localObject[(i + 1)] - 1) * 100 + (localObject[(i + 2)] - 1);
/*      */           
/*      */ 
/*  506 */           if (m > 127) {
/*  507 */             throwOverflow();
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/*  515 */         n = (byte)(((k ^ 0xFFFFFFFF) & 0xFF7F) - 65);
/*      */         
/*  517 */         if (n > 1)
/*      */         {
/*      */ 
/*  520 */           throwOverflow();
/*      */         }
/*  522 */         i1 = (byte)(j - 1);
/*      */         
/*  524 */         if ((i1 != 20) || (localObject[(i + i1)] == 102)) {
/*  525 */           i1 = (byte)(i1 - 1);
/*      */         }
/*  527 */         if (i1 > n + 1)
/*      */         {
/*  529 */           switch (n)
/*      */           {
/*      */           default: 
/*      */             break;
/*      */           
/*      */ 
/*      */ 
/*      */           case -1: 
/*      */             break;
/*      */           
/*      */ 
/*      */ 
/*      */           case 0: 
/*  542 */             m = -(101 - localObject[(i + 1)]);
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*  547 */             break;
/*      */           
/*      */           case 1: 
/*  550 */             m = -((101 - localObject[(i + 1)]) * 100 + (101 - localObject[(i + 2)]));
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  556 */             if (m >= -128) break;
/*  557 */             throwOverflow();break;
/*      */           
/*      */           }
/*      */           
/*      */         }
/*  562 */         else if (i1 == 1)
/*      */         {
/*  564 */           if (n == 1)
/*      */           {
/*  566 */             m = -(101 - localObject[(i + 1)]) * 100;
/*      */             
/*  568 */             if (m < -128) {
/*  569 */               throwOverflow();
/*      */             }
/*      */           } else {
/*  572 */             m = -(101 - localObject[(i + 1)]);
/*      */           }
/*  574 */         } else if (i1 == 2)
/*      */         {
/*  576 */           m = -((101 - localObject[(i + 1)]) * 100 + (101 - localObject[(i + 2)]));
/*      */           
/*      */ 
/*  579 */           if (m < -128) {
/*  580 */             throwOverflow();
/*      */           }
/*      */         }
/*      */       }
/*  584 */       b = (byte)m;
/*      */     }
/*      */     
/*  587 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   long getLong(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  603 */     long l1 = 0L;
/*      */     Object localObject;
/*  605 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*  607 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  608 */       ((SQLException)localObject).fillInStackTrace();
/*  609 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  614 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*      */ 
/*  617 */       localObject = this.rowSpaceByte;
/*  618 */       int i = this.columnIndex + this.byteLength * paramInt + 1;
/*  619 */       int j = localObject[(i - 1)];
/*      */       
/*  621 */       int k = localObject[i];
/*  622 */       long l2 = 0L;
/*      */       
/*      */       int m;
/*      */       int i1;
/*      */       int i2;
/*      */       int i3;
/*      */       int i4;
/*      */       int n;
/*  630 */       if ((k & 0xFFFFFF80) != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  635 */         if ((k == -128) && (j == 1)) {
/*  636 */           return 0L;
/*      */         }
/*  638 */         m = (byte)((k & 0xFF7F) - 65);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  643 */         if (m > 9)
/*      */         {
/*      */ 
/*  646 */           throwOverflow();
/*      */         }
/*  648 */         if (m == 9)
/*      */         {
/*  650 */           i1 = 1;
/*  651 */           i2 = j;
/*      */           
/*  653 */           if (j > 11) {
/*  654 */             i2 = 11;
/*      */           }
/*  656 */           while (i1 < i2)
/*      */           {
/*      */ 
/*      */ 
/*  660 */             i3 = localObject[(i + i1)] & 0xFF;
/*  661 */             i4 = MAX_LONG[i1];
/*      */             
/*  663 */             if (i3 != i4)
/*      */             {
/*  665 */               if (i3 < i4) {
/*      */                 break;
/*      */               }
/*  668 */               throwOverflow();
/*      */             }
/*      */             
/*  671 */             i1++;
/*      */           }
/*      */           
/*  674 */           if ((i1 == i2) && (j > 11)) {
/*  675 */             throwOverflow();
/*      */           }
/*      */         }
/*  678 */         n = (byte)(j - 1);
/*      */         
/*  680 */         i1 = n > m + 1 ? m + 2 : n + 1;
/*  681 */         i2 = i1 + i;
/*      */         
/*  683 */         if (i1 > 1)
/*      */         {
/*  685 */           l2 = localObject[(i + 1)] - 1;
/*      */           
/*  687 */           for (i3 = 2 + i; i3 < i2; i3++) {
/*  688 */             l2 = l2 * 100L + (localObject[i3] - 1);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  695 */         for (i3 = m - n; i3 >= 0; i3--) {
/*  696 */           l2 *= 100L;
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  703 */         m = (byte)(((k ^ 0xFFFFFFFF) & 0xFF7F) - 65);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  708 */         if (m > 9)
/*      */         {
/*      */ 
/*  711 */           throwOverflow();
/*      */         }
/*  713 */         if (m == 9)
/*      */         {
/*  715 */           i1 = 1;
/*  716 */           i2 = j;
/*      */           
/*  718 */           if (j > 12) {
/*  719 */             i2 = 12;
/*      */           }
/*  721 */           while (i1 < i2)
/*      */           {
/*      */ 
/*      */ 
/*  725 */             i3 = localObject[(i + i1)] & 0xFF;
/*  726 */             i4 = MIN_LONG[i1];
/*      */             
/*  728 */             if (i3 != i4)
/*      */             {
/*  730 */               if (i3 > i4) {
/*      */                 break;
/*      */               }
/*  733 */               throwOverflow();
/*      */             }
/*      */             
/*  736 */             i1++;
/*      */           }
/*      */           
/*  739 */           if ((i1 == i2) && (j < 12)) {
/*  740 */             throwOverflow();
/*      */           }
/*      */         }
/*  743 */         n = (byte)(j - 1);
/*      */         
/*  745 */         if ((n != 20) || (localObject[(i + n)] == 102)) {
/*  746 */           n = (byte)(n - 1);
/*      */         }
/*  748 */         i1 = n > m + 1 ? m + 2 : n + 1;
/*  749 */         i2 = i1 + i;
/*      */         
/*  751 */         if (i1 > 1)
/*      */         {
/*  753 */           l2 = 101 - localObject[(i + 1)];
/*      */           
/*  755 */           for (i3 = 2 + i; i3 < i2; i3++) {
/*  756 */             l2 = l2 * 100L + (101 - localObject[i3]);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  763 */         for (i3 = m - n; i3 >= 0; i3--) {
/*  764 */           l2 *= 100L;
/*      */         }
/*  766 */         l2 = -l2;
/*      */       }
/*      */       
/*  769 */       l1 = l2;
/*      */     }
/*      */     
/*  772 */     return l1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   float getFloat(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  788 */     float f = 0.0F;
/*      */     Object localObject;
/*  790 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*  792 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  793 */       ((SQLException)localObject).fillInStackTrace();
/*  794 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  799 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*      */ 
/*  802 */       localObject = this.rowSpaceByte;
/*  803 */       int i = this.columnIndex + this.byteLength * paramInt + 1;
/*  804 */       int j = localObject[(i - 1)];
/*      */       
/*  806 */       int k = localObject[i];
/*      */       
/*      */ 
/*  809 */       double d = 0.0D;
/*      */       
/*  811 */       int i1 = i + 1;
/*      */       
/*      */ 
/*      */       int m;
/*      */       
/*      */ 
/*      */       int i2;
/*      */       
/*      */ 
/*      */       int n;
/*      */       
/*  822 */       if ((k & 0xFFFFFF80) != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  827 */         if ((k == -128) && (j == 1)) {
/*  828 */           return 0.0F;
/*      */         }
/*  830 */         if ((j == 2) && (k == -1) && (localObject[(i + 1)] == 101))
/*      */         {
/*  832 */           return Float.POSITIVE_INFINITY;
/*      */         }
/*  834 */         m = (byte)((k & 0xFF7F) - 65);
/*      */         
/*  836 */         i2 = j - 1;
/*      */         
/*  838 */         while ((localObject[i1] == 1) && (i2 > 0))
/*      */         {
/*  840 */           i1++;
/*  841 */           i2--;
/*  842 */           m = (byte)(m - 1);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  850 */         n = (int)(127.0D - m);
/*      */         
/*  852 */         switch (i2)
/*      */         {
/*      */ 
/*      */         case 1: 
/*  856 */           d = (localObject[i1] - 1) * factorTable[n];
/*      */           
/*  858 */           break;
/*      */         
/*      */         case 2: 
/*  861 */           d = ((localObject[i1] - 1) * 100 + (localObject[(i1 + 1)] - 1)) * factorTable[(n + 1)];
/*      */           
/*      */ 
/*  864 */           break;
/*      */         
/*      */         case 3: 
/*  867 */           d = ((localObject[i1] - 1) * 10000 + (localObject[(i1 + 1)] - 1) * 100 + (localObject[(i1 + 2)] - 1)) * factorTable[(n + 2)];
/*      */           
/*      */ 
/*      */ 
/*  871 */           break;
/*      */         
/*      */         case 4: 
/*  874 */           d = ((localObject[i1] - 1) * 1000000 + (localObject[(i1 + 1)] - 1) * 10000 + (localObject[(i1 + 2)] - 1) * 100 + (localObject[(i1 + 3)] - 1)) * factorTable[(n + 3)];
/*      */           
/*      */ 
/*      */ 
/*  878 */           break;
/*      */         
/*      */         case 5: 
/*  881 */           d = ((localObject[(i1 + 1)] - 1) * 1000000 + (localObject[(i1 + 2)] - 1) * 10000 + (localObject[(i1 + 3)] - 1) * 100 + (localObject[(i1 + 4)] - 1)) * factorTable[(n + 4)] + (localObject[i1] - 1) * factorTable[n];
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  887 */           break;
/*      */         
/*      */         case 6: 
/*  890 */           d = ((localObject[(i1 + 2)] - 1) * 1000000 + (localObject[(i1 + 3)] - 1) * 10000 + (localObject[(i1 + 4)] - 1) * 100 + (localObject[(i1 + 5)] - 1)) * factorTable[(n + 5)] + ((localObject[i1] - 1) * 100 + (localObject[(i1 + 1)] - 1)) * factorTable[(n + 1)];
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  896 */           break;
/*      */         
/*      */         default: 
/*  899 */           d = ((localObject[(i1 + 3)] - 1) * 1000000 + (localObject[(i1 + 4)] - 1) * 10000 + (localObject[(i1 + 5)] - 1) * 100 + (localObject[(i1 + 6)] - 1)) * factorTable[(n + 6)] + ((localObject[i1] - 1) * 10000 + (localObject[(i1 + 1)] - 1) * 100 + (localObject[(i1 + 2)] - 1)) * factorTable[(n + 2)];
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  905 */           break;
/*      */         
/*      */ 
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/*  913 */         if ((k == 0) && (j == 1)) {
/*  914 */           return Float.NEGATIVE_INFINITY;
/*      */         }
/*  916 */         m = (byte)(((k ^ 0xFFFFFFFF) & 0xFF7F) - 65);
/*      */         
/*  918 */         i2 = j - 1;
/*      */         
/*  920 */         if ((i2 != 20) || (localObject[(i + i2)] == 102)) {
/*  921 */           i2--;
/*      */         }
/*  923 */         while ((localObject[i1] == 1) && (i2 > 0))
/*      */         {
/*  925 */           i1++;
/*  926 */           i2--;
/*  927 */           m = (byte)(m - 1);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  935 */         n = (int)(127.0D - m);
/*      */         
/*  937 */         switch (i2)
/*      */         {
/*      */ 
/*      */         case 1: 
/*  941 */           d = -(101 - localObject[i1]) * factorTable[n];
/*      */           
/*  943 */           break;
/*      */         
/*      */         case 2: 
/*  946 */           d = -((101 - localObject[i1]) * 100 + (101 - localObject[(i1 + 1)])) * factorTable[(n + 1)];
/*      */           
/*      */ 
/*  949 */           break;
/*      */         
/*      */         case 3: 
/*  952 */           d = -((101 - localObject[i1]) * 10000 + (101 - localObject[(i1 + 1)]) * 100 + (101 - localObject[(i1 + 2)])) * factorTable[(n + 2)];
/*      */           
/*      */ 
/*      */ 
/*  956 */           break;
/*      */         
/*      */         case 4: 
/*  959 */           d = -((101 - localObject[i1]) * 1000000 + (101 - localObject[(i1 + 1)]) * 10000 + (101 - localObject[(i1 + 2)]) * 100 + (101 - localObject[(i1 + 3)])) * factorTable[(n + 3)];
/*      */           
/*      */ 
/*      */ 
/*  963 */           break;
/*      */         
/*      */         case 5: 
/*  966 */           d = -(((101 - localObject[(i1 + 1)]) * 1000000 + (101 - localObject[(i1 + 2)]) * 10000 + (101 - localObject[(i1 + 3)]) * 100 + (101 - localObject[(i1 + 4)])) * factorTable[(n + 4)] + (101 - localObject[i1]) * factorTable[n]);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  972 */           break;
/*      */         
/*      */         case 6: 
/*  975 */           d = -(((101 - localObject[(i1 + 2)]) * 1000000 + (101 - localObject[(i1 + 3)]) * 10000 + (101 - localObject[(i1 + 4)]) * 100 + (101 - localObject[(i1 + 5)])) * factorTable[(n + 5)] + ((101 - localObject[i1]) * 100 + (101 - localObject[(i1 + 1)])) * factorTable[(n + 1)]);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  981 */           break;
/*      */         
/*      */         default: 
/*  984 */           d = -(((101 - localObject[(i1 + 3)]) * 1000000 + (101 - localObject[(i1 + 4)]) * 10000 + (101 - localObject[(i1 + 5)]) * 100 + (101 - localObject[(i1 + 6)])) * factorTable[(n + 6)] + ((101 - localObject[i1]) * 10000 + (101 - localObject[(i1 + 1)]) * 100 + (101 - localObject[(i1 + 2)])) * factorTable[(n + 2)]);
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  995 */       f = (float)d;
/*      */     }
/*      */     
/*  998 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   double getDouble(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1014 */     double d1 = 0.0D;
/*      */     Object localObject;
/* 1016 */     if (this.rowSpaceIndicator == null)
/*      */     {
/* 1018 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1019 */       ((SQLException)localObject).fillInStackTrace();
/* 1020 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1025 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*      */ 
/* 1028 */       localObject = this.rowSpaceByte;
/* 1029 */       int i = this.columnIndex + this.byteLength * paramInt + 1;
/* 1030 */       int j = localObject[(i - 1)];
/*      */       
/*      */ 
/* 1033 */       int k = localObject[i];
/*      */       
/* 1035 */       int n = i + 1;
/* 1036 */       int i1 = j - 1;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1041 */       int i7 = 1;
/*      */       
/*      */ 
/*      */       int m;
/*      */       
/*      */ 
/*      */       int i6;
/*      */       
/*      */       int i4;
/*      */       
/* 1051 */       if ((k & 0xFFFFFF80) != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 1056 */         if ((k == -128) && (j == 1)) {
/* 1057 */           return 0.0D;
/*      */         }
/* 1059 */         if ((j == 2) && (k == -1) && (localObject[(i + 1)] == 101))
/*      */         {
/* 1061 */           return Double.POSITIVE_INFINITY;
/*      */         }
/* 1063 */         m = (byte)((k & 0xFF7F) - 65);
/*      */         
/* 1065 */         i6 = (localObject[(n + i1 - 1)] - 1) % 10 == 0 ? 1 : 0;
/*      */         
/* 1067 */         i4 = localObject[n] - 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1080 */         i7 = 0;
/*      */         
/* 1082 */         if ((k == 0) && (j == 1)) {
/* 1083 */           return Double.NEGATIVE_INFINITY;
/*      */         }
/* 1085 */         m = (byte)(((k ^ 0xFFFFFFFF) & 0xFF7F) - 65);
/*      */         
/* 1087 */         if ((i1 != 20) || (localObject[(i + i1)] == 102)) {
/* 1088 */           i1--;
/*      */         }
/* 1090 */         i6 = (101 - localObject[(n + i1 - 1)]) % 10 == 0 ? 1 : 0;
/*      */         
/* 1092 */         i4 = 101 - localObject[n];
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1101 */       int i5 = i1 << 1;
/*      */       
/* 1103 */       if (i6 != 0) {
/* 1104 */         i5--;
/*      */       }
/* 1106 */       int i8 = (m + 1 << 1) - i5;
/*      */       
/* 1108 */       if (i4 < 10)
/* 1109 */         i5--;
/*      */       int i9;
/* 1111 */       int i10; int i11; int i12; int i13; int i14; int i15; int i18; int i19; double d2; int i20; if ((i5 <= 15) && (((i8 >= 0) && (i8 <= 37 - i5)) || ((i8 < 0) && (i8 >= -22))))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 1116 */         i9 = 0;
/* 1117 */         i10 = 0;
/* 1118 */         i11 = 0;
/* 1119 */         i12 = 0;
/* 1120 */         i13 = 0;
/* 1121 */         i14 = 0;
/* 1122 */         i15 = 0;
/*      */         
/* 1124 */         if (i7 != 0) {
/* 1125 */           switch (i1)
/*      */           {
/*      */ 
/*      */           default: 
/* 1129 */             i15 = localObject[(n + 7)] - 1;
/*      */           
/*      */           case 7: 
/* 1132 */             i14 = localObject[(n + 6)] - 1;
/*      */           
/*      */           case 6: 
/* 1135 */             i13 = localObject[(n + 5)] - 1;
/*      */           
/*      */           case 5: 
/* 1138 */             i12 = localObject[(n + 4)] - 1;
/*      */           
/*      */           case 4: 
/* 1141 */             i11 = localObject[(n + 3)] - 1;
/*      */           
/*      */           case 3: 
/* 1144 */             i10 = localObject[(n + 2)] - 1;
/*      */           
/*      */           case 2: 
/* 1147 */             i9 = localObject[(n + 1)] - 1;
/*      */           
/*      */ 
/*      */           }
/*      */           
/*      */         } else {
/* 1153 */           switch (i1)
/*      */           {
/*      */ 
/*      */           default: 
/* 1157 */             i15 = 101 - localObject[(n + 7)];
/*      */           
/*      */           case 7: 
/* 1160 */             i14 = 101 - localObject[(n + 6)];
/*      */           
/*      */           case 6: 
/* 1163 */             i13 = 101 - localObject[(n + 5)];
/*      */           
/*      */           case 5: 
/* 1166 */             i12 = 101 - localObject[(n + 4)];
/*      */           
/*      */           case 4: 
/* 1169 */             i11 = 101 - localObject[(n + 3)];
/*      */           
/*      */           case 3: 
/* 1172 */             i10 = 101 - localObject[(n + 2)];
/*      */           
/*      */           case 2: 
/* 1175 */             i9 = 101 - localObject[(n + 1)];
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */ 
/*      */         double d3;
/*      */         
/*      */ 
/* 1184 */         if (i6 != 0)
/* 1185 */           switch (i1)
/*      */           {
/*      */ 
/*      */           default: 
/* 1189 */             d3 = i4 / 10;
/*      */             
/* 1191 */             break;
/*      */           
/*      */           case 2: 
/* 1194 */             d3 = i4 * 10 + i9 / 10;
/*      */             
/* 1196 */             break;
/*      */           
/*      */           case 3: 
/* 1199 */             d3 = i4 * 1000 + i9 * 10 + i10 / 10;
/*      */             
/* 1201 */             break;
/*      */           
/*      */           case 4: 
/* 1204 */             d3 = i4 * 100000 + i9 * 1000 + i10 * 10 + i11 / 10;
/*      */             
/*      */ 
/* 1207 */             break;
/*      */           
/*      */           case 5: 
/* 1210 */             d3 = i4 * 10000000 + i9 * 100000 + i10 * 1000 + i11 * 10 + i12 / 10;
/*      */             
/*      */ 
/* 1213 */             break;
/*      */           
/*      */           case 6: 
/* 1216 */             i18 = i9 * 10000000 + i10 * 100000 + i11 * 1000 + i12 * 10 + i13 / 10;
/*      */             
/* 1218 */             d3 = i4 * 1000000000L + i18;
/*      */             
/* 1220 */             break;
/*      */           
/*      */           case 7: 
/* 1223 */             i18 = i10 * 10000000 + i11 * 100000 + i12 * 1000 + i13 * 10 + i14 / 10;
/*      */             
/* 1225 */             i19 = i4 * 100 + i9;
/* 1226 */             d3 = i19 * 1000000000L + i18;
/*      */             
/* 1228 */             break;
/*      */           
/*      */           case 8: 
/* 1231 */             i18 = i11 * 10000000 + i12 * 100000 + i13 * 1000 + i14 * 10 + i15 / 10;
/*      */             
/* 1233 */             i19 = i4 * 10000 + i9 * 100 + i10;
/* 1234 */             d3 = i19 * 1000000000L + i18;
/*      */             
/* 1236 */             break;
/*      */           }
/*      */            else {
/* 1239 */           switch (i1)
/*      */           {
/*      */ 
/*      */           default: 
/* 1243 */             d3 = i4;
/*      */             
/* 1245 */             break;
/*      */           
/*      */           case 2: 
/* 1248 */             d3 = i4 * 100 + i9;
/*      */             
/* 1250 */             break;
/*      */           
/*      */           case 3: 
/* 1253 */             d3 = i4 * 10000 + i9 * 100 + i10;
/*      */             
/* 1255 */             break;
/*      */           
/*      */           case 4: 
/* 1258 */             d3 = i4 * 1000000 + i9 * 10000 + i10 * 100 + i11;
/*      */             
/*      */ 
/* 1261 */             break;
/*      */           
/*      */           case 5: 
/* 1264 */             i18 = i9 * 1000000 + i10 * 10000 + i11 * 100 + i12;
/* 1265 */             d3 = i4 * 100000000L + i18;
/*      */             
/* 1267 */             break;
/*      */           
/*      */           case 6: 
/* 1270 */             i18 = i10 * 1000000 + i11 * 10000 + i12 * 100 + i13;
/* 1271 */             i19 = i4 * 100 + i9;
/* 1272 */             d3 = i19 * 100000000L + i18;
/*      */             
/* 1274 */             break;
/*      */           
/*      */           case 7: 
/* 1277 */             i18 = i11 * 1000000 + i12 * 10000 + i13 * 100 + i14;
/* 1278 */             i19 = i4 * 10000 + i9 * 100 + i10;
/* 1279 */             d3 = i19 * 100000000L + i18;
/*      */             
/* 1281 */             break;
/*      */           
/*      */           case 8: 
/* 1284 */             i18 = i12 * 1000000 + i13 * 10000 + i14 * 100 + i15;
/* 1285 */             i19 = i4 * 1000000 + i9 * 10000 + i10 * 100 + i11;
/* 1286 */             d3 = i19 * 100000000L + i18;
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1294 */         if ((i8 == 0) || (d3 == 0.0D)) {
/* 1295 */           d2 = d3;
/* 1296 */         } else if (i8 >= 0)
/*      */         {
/* 1298 */           if (i8 <= 22)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1305 */             d2 = d3 * small10pow[i8];
/*      */           }
/*      */           else
/*      */           {
/* 1309 */             i20 = 15 - i5;
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1317 */             d3 *= small10pow[i20];
/* 1318 */             d2 = d3 * small10pow[(i8 - i20)];
/*      */ 
/*      */ 
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*      */ 
/* 1331 */           d2 = d3 / small10pow[(-i8)];
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1336 */         i9 = 0;
/* 1337 */         i10 = 0;
/* 1338 */         i11 = 0;
/* 1339 */         i12 = 0;
/* 1340 */         i13 = 0;
/* 1341 */         i14 = 0;
/* 1342 */         i15 = 0;
/* 1343 */         int i16 = 0;
/* 1344 */         int i17 = 0;
/* 1345 */         i18 = 0;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1352 */         int i24 = 0;
/* 1353 */         int i25 = 0;
/* 1354 */         int i26 = 0;
/* 1355 */         int i27 = 0;
/* 1356 */         int i28 = 0;
/*      */         
/*      */ 
/*      */ 
/* 1360 */         int i32 = 0;
/* 1361 */         int i33 = 0;
/*      */         int i2;
/* 1363 */         if (i7 != 0)
/*      */         {
/* 1365 */           if ((i1 & 0x1) != 0)
/*      */           {
/* 1367 */             i2 = 2;
/* 1368 */             i9 = i4;
/*      */           }
/*      */           else
/*      */           {
/* 1372 */             i2 = 3;
/* 1373 */             i9 = i4 * 100 + (localObject[(n + 1)] - 1);
/*      */           }
/* 1376 */           for (; 
/* 1376 */               i2 < i1; i2 += 2)
/*      */           {
/* 1378 */             i34 = (localObject[(n + i2 - 1)] - 1) * 100 + (localObject[(n + i2)] - 1) + i9 * 10000;
/*      */             
/*      */ 
/* 1381 */             switch (i18)
/*      */             {
/*      */ 
/*      */             default: 
/* 1385 */               i9 = i34 & 0xFFFF;
/* 1386 */               i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1387 */               i10 = i34 & 0xFFFF;
/* 1388 */               i34 = (i34 >> 16 & 0xFFFF) + i11 * 10000;
/* 1389 */               i11 = i34 & 0xFFFF;
/* 1390 */               i34 = (i34 >> 16 & 0xFFFF) + i12 * 10000;
/* 1391 */               i12 = i34 & 0xFFFF;
/* 1392 */               i34 = (i34 >> 16 & 0xFFFF) + i13 * 10000;
/* 1393 */               i13 = i34 & 0xFFFF;
/* 1394 */               i34 = (i34 >> 16 & 0xFFFF) + i14 * 10000;
/* 1395 */               i14 = i34 & 0xFFFF;
/* 1396 */               i34 = (i34 >> 16 & 0xFFFF) + i15 * 10000;
/* 1397 */               i15 = i34 & 0xFFFF;
/* 1398 */               i34 = (i34 >> 16 & 0xFFFF) + i16 * 10000;
/* 1399 */               i16 = i34 & 0xFFFF;
/* 1400 */               i34 = (i34 >> 16 & 0xFFFF) + i17 * 10000;
/* 1401 */               i17 = i34 & 0xFFFF;
/*      */               
/* 1403 */               break;
/*      */             
/*      */             case 7: 
/* 1406 */               i9 = i34 & 0xFFFF;
/* 1407 */               i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1408 */               i10 = i34 & 0xFFFF;
/* 1409 */               i34 = (i34 >> 16 & 0xFFFF) + i11 * 10000;
/* 1410 */               i11 = i34 & 0xFFFF;
/* 1411 */               i34 = (i34 >> 16 & 0xFFFF) + i12 * 10000;
/* 1412 */               i12 = i34 & 0xFFFF;
/* 1413 */               i34 = (i34 >> 16 & 0xFFFF) + i13 * 10000;
/* 1414 */               i13 = i34 & 0xFFFF;
/* 1415 */               i34 = (i34 >> 16 & 0xFFFF) + i14 * 10000;
/* 1416 */               i14 = i34 & 0xFFFF;
/* 1417 */               i34 = (i34 >> 16 & 0xFFFF) + i15 * 10000;
/* 1418 */               i15 = i34 & 0xFFFF;
/* 1419 */               i34 = (i34 >> 16 & 0xFFFF) + i16 * 10000;
/* 1420 */               i16 = i34 & 0xFFFF;
/*      */               
/* 1422 */               break;
/*      */             
/*      */             case 6: 
/* 1425 */               i9 = i34 & 0xFFFF;
/* 1426 */               i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1427 */               i10 = i34 & 0xFFFF;
/* 1428 */               i34 = (i34 >> 16 & 0xFFFF) + i11 * 10000;
/* 1429 */               i11 = i34 & 0xFFFF;
/* 1430 */               i34 = (i34 >> 16 & 0xFFFF) + i12 * 10000;
/* 1431 */               i12 = i34 & 0xFFFF;
/* 1432 */               i34 = (i34 >> 16 & 0xFFFF) + i13 * 10000;
/* 1433 */               i13 = i34 & 0xFFFF;
/* 1434 */               i34 = (i34 >> 16 & 0xFFFF) + i14 * 10000;
/* 1435 */               i14 = i34 & 0xFFFF;
/* 1436 */               i34 = (i34 >> 16 & 0xFFFF) + i15 * 10000;
/* 1437 */               i15 = i34 & 0xFFFF;
/*      */               
/* 1439 */               break;
/*      */             
/*      */             case 5: 
/* 1442 */               i9 = i34 & 0xFFFF;
/* 1443 */               i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1444 */               i10 = i34 & 0xFFFF;
/* 1445 */               i34 = (i34 >> 16 & 0xFFFF) + i11 * 10000;
/* 1446 */               i11 = i34 & 0xFFFF;
/* 1447 */               i34 = (i34 >> 16 & 0xFFFF) + i12 * 10000;
/* 1448 */               i12 = i34 & 0xFFFF;
/* 1449 */               i34 = (i34 >> 16 & 0xFFFF) + i13 * 10000;
/* 1450 */               i13 = i34 & 0xFFFF;
/* 1451 */               i34 = (i34 >> 16 & 0xFFFF) + i14 * 10000;
/* 1452 */               i14 = i34 & 0xFFFF;
/*      */               
/* 1454 */               break;
/*      */             
/*      */             case 4: 
/* 1457 */               i9 = i34 & 0xFFFF;
/* 1458 */               i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1459 */               i10 = i34 & 0xFFFF;
/* 1460 */               i34 = (i34 >> 16 & 0xFFFF) + i11 * 10000;
/* 1461 */               i11 = i34 & 0xFFFF;
/* 1462 */               i34 = (i34 >> 16 & 0xFFFF) + i12 * 10000;
/* 1463 */               i12 = i34 & 0xFFFF;
/* 1464 */               i34 = (i34 >> 16 & 0xFFFF) + i13 * 10000;
/* 1465 */               i13 = i34 & 0xFFFF;
/*      */               
/* 1467 */               break;
/*      */             
/*      */             case 3: 
/* 1470 */               i9 = i34 & 0xFFFF;
/* 1471 */               i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1472 */               i10 = i34 & 0xFFFF;
/* 1473 */               i34 = (i34 >> 16 & 0xFFFF) + i11 * 10000;
/* 1474 */               i11 = i34 & 0xFFFF;
/* 1475 */               i34 = (i34 >> 16 & 0xFFFF) + i12 * 10000;
/* 1476 */               i12 = i34 & 0xFFFF;
/*      */               
/* 1478 */               break;
/*      */             
/*      */             case 2: 
/* 1481 */               i9 = i34 & 0xFFFF;
/* 1482 */               i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1483 */               i10 = i34 & 0xFFFF;
/* 1484 */               i34 = (i34 >> 16 & 0xFFFF) + i11 * 10000;
/* 1485 */               i11 = i34 & 0xFFFF;
/*      */               
/* 1487 */               break;
/*      */             
/*      */             case 1: 
/* 1490 */               i9 = i34 & 0xFFFF;
/* 1491 */               i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1492 */               i10 = i34 & 0xFFFF;
/*      */               
/* 1494 */               break;
/*      */             
/*      */             case 0: 
/* 1497 */               i9 = i34 & 0xFFFF;
/*      */             }
/*      */             
/*      */             
/*      */ 
/* 1502 */             i34 = i34 >> 16 & 0xFFFF;
/*      */             
/* 1504 */             if (i34 != 0)
/*      */             {
/* 1506 */               i18++;
/*      */               
/* 1508 */               switch (i18)
/*      */               {
/*      */ 
/*      */               case 8: 
/* 1512 */                 i17 = i34;
/*      */                 
/* 1514 */                 break;
/*      */               
/*      */               case 7: 
/* 1517 */                 i16 = i34;
/*      */                 
/* 1519 */                 break;
/*      */               
/*      */               case 6: 
/* 1522 */                 i15 = i34;
/*      */                 
/* 1524 */                 break;
/*      */               
/*      */               case 5: 
/* 1527 */                 i14 = i34;
/*      */                 
/* 1529 */                 break;
/*      */               
/*      */               case 4: 
/* 1532 */                 i13 = i34;
/*      */                 
/* 1534 */                 break;
/*      */               
/*      */               case 3: 
/* 1537 */                 i12 = i34;
/*      */                 
/* 1539 */                 break;
/*      */               
/*      */               case 2: 
/* 1542 */                 i11 = i34;
/*      */                 
/* 1544 */                 break;
/*      */               
/*      */               case 1: 
/* 1547 */                 i10 = i34;
/*      */               }
/*      */               
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1559 */         if ((i1 & 0x1) != 0)
/*      */         {
/* 1561 */           i2 = 2;
/* 1562 */           i9 = i4;
/*      */         }
/*      */         else
/*      */         {
/* 1566 */           i2 = 3;
/* 1567 */           i9 = i4 * 100 + (101 - localObject[(n + 1)]);
/*      */         }
/* 1570 */         for (; 
/* 1570 */             i2 < i1; i2 += 2)
/*      */         {
/* 1572 */           i34 = (101 - localObject[(n + i2 - 1)]) * 100 + (101 - localObject[(n + i2)]) + i9 * 10000;
/*      */           
/*      */ 
/* 1575 */           switch (i18)
/*      */           {
/*      */ 
/*      */           default: 
/* 1579 */             i9 = i34 & 0xFFFF;
/* 1580 */             i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1581 */             i10 = i34 & 0xFFFF;
/* 1582 */             i34 = (i34 >> 16 & 0xFFFF) + i11 * 10000;
/* 1583 */             i11 = i34 & 0xFFFF;
/* 1584 */             i34 = (i34 >> 16 & 0xFFFF) + i12 * 10000;
/* 1585 */             i12 = i34 & 0xFFFF;
/* 1586 */             i34 = (i34 >> 16 & 0xFFFF) + i13 * 10000;
/* 1587 */             i13 = i34 & 0xFFFF;
/* 1588 */             i34 = (i34 >> 16 & 0xFFFF) + i14 * 10000;
/* 1589 */             i14 = i34 & 0xFFFF;
/* 1590 */             i34 = (i34 >> 16 & 0xFFFF) + i15 * 10000;
/* 1591 */             i15 = i34 & 0xFFFF;
/* 1592 */             i34 = (i34 >> 16 & 0xFFFF) + i16 * 10000;
/* 1593 */             i16 = i34 & 0xFFFF;
/* 1594 */             i34 = (i34 >> 16 & 0xFFFF) + i17 * 10000;
/* 1595 */             i17 = i34 & 0xFFFF;
/*      */             
/* 1597 */             break;
/*      */           
/*      */           case 7: 
/* 1600 */             i9 = i34 & 0xFFFF;
/* 1601 */             i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1602 */             i10 = i34 & 0xFFFF;
/* 1603 */             i34 = (i34 >> 16 & 0xFFFF) + i11 * 10000;
/* 1604 */             i11 = i34 & 0xFFFF;
/* 1605 */             i34 = (i34 >> 16 & 0xFFFF) + i12 * 10000;
/* 1606 */             i12 = i34 & 0xFFFF;
/* 1607 */             i34 = (i34 >> 16 & 0xFFFF) + i13 * 10000;
/* 1608 */             i13 = i34 & 0xFFFF;
/* 1609 */             i34 = (i34 >> 16 & 0xFFFF) + i14 * 10000;
/* 1610 */             i14 = i34 & 0xFFFF;
/* 1611 */             i34 = (i34 >> 16 & 0xFFFF) + i15 * 10000;
/* 1612 */             i15 = i34 & 0xFFFF;
/* 1613 */             i34 = (i34 >> 16 & 0xFFFF) + i16 * 10000;
/* 1614 */             i16 = i34 & 0xFFFF;
/*      */             
/* 1616 */             break;
/*      */           
/*      */           case 6: 
/* 1619 */             i9 = i34 & 0xFFFF;
/* 1620 */             i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1621 */             i10 = i34 & 0xFFFF;
/* 1622 */             i34 = (i34 >> 16 & 0xFFFF) + i11 * 10000;
/* 1623 */             i11 = i34 & 0xFFFF;
/* 1624 */             i34 = (i34 >> 16 & 0xFFFF) + i12 * 10000;
/* 1625 */             i12 = i34 & 0xFFFF;
/* 1626 */             i34 = (i34 >> 16 & 0xFFFF) + i13 * 10000;
/* 1627 */             i13 = i34 & 0xFFFF;
/* 1628 */             i34 = (i34 >> 16 & 0xFFFF) + i14 * 10000;
/* 1629 */             i14 = i34 & 0xFFFF;
/* 1630 */             i34 = (i34 >> 16 & 0xFFFF) + i15 * 10000;
/* 1631 */             i15 = i34 & 0xFFFF;
/*      */             
/* 1633 */             break;
/*      */           
/*      */           case 5: 
/* 1636 */             i9 = i34 & 0xFFFF;
/* 1637 */             i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1638 */             i10 = i34 & 0xFFFF;
/* 1639 */             i34 = (i34 >> 16 & 0xFFFF) + i11 * 10000;
/* 1640 */             i11 = i34 & 0xFFFF;
/* 1641 */             i34 = (i34 >> 16 & 0xFFFF) + i12 * 10000;
/* 1642 */             i12 = i34 & 0xFFFF;
/* 1643 */             i34 = (i34 >> 16 & 0xFFFF) + i13 * 10000;
/* 1644 */             i13 = i34 & 0xFFFF;
/* 1645 */             i34 = (i34 >> 16 & 0xFFFF) + i14 * 10000;
/* 1646 */             i14 = i34 & 0xFFFF;
/*      */             
/* 1648 */             break;
/*      */           
/*      */           case 4: 
/* 1651 */             i9 = i34 & 0xFFFF;
/* 1652 */             i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1653 */             i10 = i34 & 0xFFFF;
/* 1654 */             i34 = (i34 >> 16 & 0xFFFF) + i11 * 10000;
/* 1655 */             i11 = i34 & 0xFFFF;
/* 1656 */             i34 = (i34 >> 16 & 0xFFFF) + i12 * 10000;
/* 1657 */             i12 = i34 & 0xFFFF;
/* 1658 */             i34 = (i34 >> 16 & 0xFFFF) + i13 * 10000;
/* 1659 */             i13 = i34 & 0xFFFF;
/*      */             
/* 1661 */             break;
/*      */           
/*      */           case 3: 
/* 1664 */             i9 = i34 & 0xFFFF;
/* 1665 */             i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1666 */             i10 = i34 & 0xFFFF;
/* 1667 */             i34 = (i34 >> 16 & 0xFFFF) + i11 * 10000;
/* 1668 */             i11 = i34 & 0xFFFF;
/* 1669 */             i34 = (i34 >> 16 & 0xFFFF) + i12 * 10000;
/* 1670 */             i12 = i34 & 0xFFFF;
/*      */             
/* 1672 */             break;
/*      */           
/*      */           case 2: 
/* 1675 */             i9 = i34 & 0xFFFF;
/* 1676 */             i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1677 */             i10 = i34 & 0xFFFF;
/* 1678 */             i34 = (i34 >> 16 & 0xFFFF) + i11 * 10000;
/* 1679 */             i11 = i34 & 0xFFFF;
/*      */             
/* 1681 */             break;
/*      */           
/*      */           case 1: 
/* 1684 */             i9 = i34 & 0xFFFF;
/* 1685 */             i34 = (i34 >> 16 & 0xFFFF) + i10 * 10000;
/* 1686 */             i10 = i34 & 0xFFFF;
/*      */             
/* 1688 */             break;
/*      */           
/*      */           case 0: 
/* 1691 */             i9 = i34 & 0xFFFF;
/*      */           }
/*      */           
/*      */           
/*      */ 
/* 1696 */           i34 = i34 >> 16 & 0xFFFF;
/*      */           
/* 1698 */           if (i34 != 0)
/*      */           {
/* 1700 */             i18++;
/*      */             
/* 1702 */             switch (i18)
/*      */             {
/*      */ 
/*      */             case 8: 
/* 1706 */               i17 = i34;
/*      */               
/* 1708 */               break;
/*      */             
/*      */             case 7: 
/* 1711 */               i16 = i34;
/*      */               
/* 1713 */               break;
/*      */             
/*      */             case 6: 
/* 1716 */               i15 = i34;
/*      */               
/* 1718 */               break;
/*      */             
/*      */             case 5: 
/* 1721 */               i14 = i34;
/*      */               
/* 1723 */               break;
/*      */             
/*      */             case 4: 
/* 1726 */               i13 = i34;
/*      */               
/* 1728 */               break;
/*      */             
/*      */             case 3: 
/* 1731 */               i12 = i34;
/*      */               
/* 1733 */               break;
/*      */             
/*      */             case 2: 
/* 1736 */               i11 = i34;
/*      */               
/* 1738 */               break;
/*      */             
/*      */             case 1: 
/* 1741 */               i10 = i34;
/*      */             }
/*      */             
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1785 */         int i31 = i18;
/*      */         
/* 1787 */         i18++;
/*      */         
/* 1789 */         int i21 = 62 - m + i1;
/*      */         
/* 1791 */         int i22 = nexpdigstable[i21];
/* 1792 */         int[] arrayOfInt = expdigstable[i21];
/* 1793 */         i33 = i18 + 5;
/*      */         
/* 1795 */         int i34 = 0;
/*      */         
/* 1797 */         if (i22 > i33)
/*      */         {
/* 1799 */           i34 = i22 - i33;
/* 1800 */           i22 = i33;
/*      */         }
/*      */         
/* 1803 */         i20 = 0;
/* 1804 */         int i23 = 0;
/* 1805 */         int i29 = i22 - 1 + (i18 - 1) - 4;
/*      */         int i35;
/* 1807 */         int i3; for (i19 = 0; i19 < i29; i19++)
/*      */         {
/* 1809 */           i35 = i23 & 0xFFFF;
/*      */           
/* 1811 */           i23 = i23 >> 16 & 0xFFFF;
/*      */           
/* 1813 */           i36 = i18 < i19 + 1 ? i18 : i19 + 1;
/*      */           
/* 1815 */           for (i3 = i19 - i22 + 1 > 0 ? i19 - i22 + 1 : 0; i3 < i36; 
/* 1816 */               i3++)
/*      */           {
/* 1818 */             i37 = i34 + i19 - i3;
/*      */             
/*      */ 
/* 1821 */             switch (i3)
/*      */             {
/*      */ 
/*      */             case 8: 
/* 1825 */               i38 = i17 * arrayOfInt[i37];
/*      */               
/* 1827 */               break;
/*      */             
/*      */             case 7: 
/* 1830 */               i38 = i16 * arrayOfInt[i37];
/*      */               
/* 1832 */               break;
/*      */             
/*      */             case 6: 
/* 1835 */               i38 = i15 * arrayOfInt[i37];
/*      */               
/* 1837 */               break;
/*      */             
/*      */             case 5: 
/* 1840 */               i38 = i14 * arrayOfInt[i37];
/*      */               
/* 1842 */               break;
/*      */             
/*      */             case 4: 
/* 1845 */               i38 = i13 * arrayOfInt[i37];
/*      */               
/* 1847 */               break;
/*      */             
/*      */             case 3: 
/* 1850 */               i38 = i12 * arrayOfInt[i37];
/*      */               
/* 1852 */               break;
/*      */             
/*      */             case 2: 
/* 1855 */               i38 = i11 * arrayOfInt[i37];
/*      */               
/* 1857 */               break;
/*      */             
/*      */             case 1: 
/* 1860 */               i38 = i10 * arrayOfInt[i37];
/*      */               
/* 1862 */               break;
/*      */             
/*      */             default: 
/* 1865 */               i38 = i9 * arrayOfInt[i37];
/*      */             }
/*      */             
/*      */             
/*      */ 
/* 1870 */             i35 += (i38 & 0xFFFF);
/* 1871 */             i23 += (i38 >> 16 & 0xFFFF);
/*      */           }
/*      */           
/* 1874 */           i32 = (i32 != 0) || ((i35 & 0xFFFF) != 0) ? 1 : 0;
/* 1875 */           i23 += (i35 >> 16 & 0xFFFF);
/*      */         }
/*      */         
/* 1878 */         i29 += 5;
/* 1880 */         for (; 
/* 1880 */             i19 < i29; i19++)
/*      */         {
/* 1882 */           i35 = i23 & 0xFFFF;
/*      */           
/* 1884 */           i23 = i23 >> 16 & 0xFFFF;
/*      */           
/* 1886 */           i36 = i18 < i19 + 1 ? i18 : i19 + 1;
/*      */           
/* 1888 */           for (i3 = i19 - i22 + 1 > 0 ? i19 - i22 + 1 : 0; i3 < i36; 
/* 1889 */               i3++)
/*      */           {
/* 1891 */             i37 = i34 + i19 - i3;
/*      */             
/*      */ 
/* 1894 */             switch (i3)
/*      */             {
/*      */ 
/*      */             case 8: 
/* 1898 */               i38 = i17 * arrayOfInt[i37];
/*      */               
/*      */ 
/*      */ 
/*      */ 
/* 1903 */               break;
/*      */             
/*      */             case 7: 
/* 1906 */               i38 = i16 * arrayOfInt[i37];
/*      */               
/*      */ 
/*      */ 
/*      */ 
/* 1911 */               break;
/*      */             
/*      */             case 6: 
/* 1914 */               i38 = i15 * arrayOfInt[i37];
/*      */               
/*      */ 
/*      */ 
/*      */ 
/* 1919 */               break;
/*      */             
/*      */             case 5: 
/* 1922 */               i38 = i14 * arrayOfInt[i37];
/*      */               
/*      */ 
/*      */ 
/*      */ 
/* 1927 */               break;
/*      */             
/*      */             case 4: 
/* 1930 */               i38 = i13 * arrayOfInt[i37];
/*      */               
/*      */ 
/*      */ 
/*      */ 
/* 1935 */               break;
/*      */             
/*      */             case 3: 
/* 1938 */               i38 = i12 * arrayOfInt[i37];
/*      */               
/*      */ 
/*      */ 
/*      */ 
/* 1943 */               break;
/*      */             
/*      */             case 2: 
/* 1946 */               i38 = i11 * arrayOfInt[i37];
/*      */               
/*      */ 
/*      */ 
/*      */ 
/* 1951 */               break;
/*      */             
/*      */             case 1: 
/* 1954 */               i38 = i10 * arrayOfInt[i37];
/*      */               
/*      */ 
/*      */ 
/*      */ 
/* 1959 */               break;
/*      */             
/*      */             default: 
/* 1962 */               i38 = i9 * arrayOfInt[i37];
/*      */             }
/*      */             
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1970 */             i35 += (i38 & 0xFFFF);
/* 1971 */             i23 += (i38 >> 16 & 0xFFFF);
/*      */           }
/*      */           
/* 1974 */           switch (i20++)
/*      */           {
/*      */ 
/*      */           case 4: 
/* 1978 */             i28 = i35 & 0xFFFF;
/*      */             
/* 1980 */             break;
/*      */           
/*      */           case 3: 
/* 1983 */             i27 = i35 & 0xFFFF;
/*      */             
/* 1985 */             break;
/*      */           
/*      */           case 2: 
/* 1988 */             i26 = i35 & 0xFFFF;
/*      */             
/* 1990 */             break;
/*      */           
/*      */           case 1: 
/* 1993 */             i25 = i35 & 0xFFFF;
/*      */             
/* 1995 */             break;
/*      */           
/*      */           default: 
/* 1998 */             i24 = i35 & 0xFFFF;
/*      */           }
/*      */           
/*      */           
/*      */ 
/* 2003 */           i23 += (i35 >> 16 & 0xFFFF);
/*      */         }
/*      */         
/* 2006 */         while (i23 != 0)
/*      */         {
/* 2008 */           if (i20 < 5) {
/* 2009 */             switch (i20++)
/*      */             {
/*      */ 
/*      */             case 4: 
/* 2013 */               i28 = i23 & 0xFFFF;
/*      */               
/* 2015 */               break;
/*      */             
/*      */             case 3: 
/* 2018 */               i27 = i23 & 0xFFFF;
/*      */               
/* 2020 */               break;
/*      */             
/*      */             case 2: 
/* 2023 */               i26 = i23 & 0xFFFF;
/*      */               
/* 2025 */               break;
/*      */             
/*      */             case 1: 
/* 2028 */               i25 = i23 & 0xFFFF;
/*      */               
/* 2030 */               break;
/*      */             
/*      */             default: 
/* 2033 */               i24 = i23 & 0xFFFF;
/*      */               
/* 2035 */               break;
/*      */             }
/*      */             
/*      */           } else {
/* 2039 */             i32 = (i32 != 0) || (i24 != 0) ? 1 : 0;
/* 2040 */             i24 = i25;
/* 2041 */             i25 = i26;
/* 2042 */             i26 = i27;
/* 2043 */             i27 = i28;
/* 2044 */             i28 = i23 & 0xFFFF;
/*      */           }
/*      */           
/* 2047 */           i23 = i23 >> 16 & 0xFFFF;
/* 2048 */           i31++;
/*      */         }
/*      */         
/* 2051 */         int i30 = (binexpstable[i21] + i31) * 16 - 1;
/*      */         
/*      */ 
/*      */ 
/* 2055 */         switch (i20)
/*      */         {
/*      */ 
/*      */         case 5: 
/* 2059 */           i35 = i28;
/*      */           
/* 2061 */           break;
/*      */         
/*      */         case 4: 
/* 2064 */           i35 = i27;
/*      */           
/* 2066 */           break;
/*      */         
/*      */         case 3: 
/* 2069 */           i35 = i26;
/*      */           
/* 2071 */           break;
/*      */         
/*      */         case 2: 
/* 2074 */           i35 = i25;
/*      */           
/* 2076 */           break;
/*      */         
/*      */         default: 
/* 2079 */           i35 = i24;
/*      */         }
/*      */         
/*      */         
/*      */ 
/* 2084 */         for (int i36 = i35 >> 1; i36 != 0; i36 >>= 1) {
/* 2085 */           i30++;
/*      */         }
/*      */         
/* 2088 */         i36 = 5;
/* 2089 */         int i37 = i35 << 5;
/* 2090 */         int i38 = 0;
/*      */         
/* 2092 */         i23 = 0;
/*      */         
/* 2094 */         while ((i37 & 0x100000) == 0)
/*      */         {
/* 2096 */           i37 <<= 1;
/* 2097 */           i36++;
/*      */         }
/*      */         
/* 2100 */         switch (i20)
/*      */         {
/*      */ 
/*      */         case 5: 
/* 2104 */           if (i36 > 16)
/*      */           {
/*      */ 
/* 2107 */             i37 |= i27 << i36 - 16 | i26 >> 32 - i36;
/* 2108 */             i38 = i26 << i36 | i25 << i36 - 16 | i24 >> 32 - i36;
/*      */             
/* 2110 */             i23 = i24 & 1 << 31 - i36;
/* 2111 */             i32 = (i32 != 0) || (i24 << i36 + 1 != 0) ? 1 : 0;
/*      */ 
/*      */           }
/* 2114 */           else if (i36 == 16)
/*      */           {
/*      */ 
/* 2117 */             i37 |= i27;
/* 2118 */             i38 = i26 << 16 | i25;
/* 2119 */             i23 = i24 & 0x8000;
/* 2120 */             i32 = (i32 != 0) || ((i24 & 0x7FFF) != 0) ? 1 : 0;
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/* 2126 */             i37 |= i27 >> 16 - i36;
/* 2127 */             i38 = i27 << 16 + i36 | i26 << i36 | i25 >> 16 - i36;
/*      */             
/* 2129 */             i23 = i25 & 1 << 15 - i36;
/*      */             
/* 2131 */             if (i36 < 15) {
/* 2132 */               i32 = (i32 != 0) || (i25 << i36 + 17 != 0) ? 1 : 0;
/*      */             }
/* 2134 */             i32 = (i32 != 0) || (i24 != 0) ? 1 : 0;
/*      */           }
/*      */           
/*      */ 
/* 2138 */           break;
/*      */         
/*      */         case 4: 
/* 2141 */           if (i36 > 16)
/*      */           {
/*      */ 
/* 2144 */             i37 |= i26 << i36 - 16 | i25 >> 32 - i36;
/* 2145 */             i38 = i25 << i36 | i24 << i36 - 16;
/*      */ 
/*      */           }
/* 2148 */           else if (i36 == 16)
/*      */           {
/*      */ 
/* 2151 */             i37 |= i26;
/*      */             
/* 2153 */             i38 = i25 << 16 | i24;
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/* 2159 */             i37 |= i26 >> 16 - i36;
/* 2160 */             i38 = i26 << 16 + i36 | i25 << i36 | i24 >> 16 - i36;
/*      */             
/* 2162 */             i23 = i24 & 1 << 15 - i36;
/*      */             
/* 2164 */             if (i36 < 15) {
/* 2165 */               i32 = (i32 != 0) || (i24 << i36 + 17 != 0) ? 1 : 0;
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */           break;
/*      */         case 3: 
/* 2172 */           if (i36 > 16)
/*      */           {
/*      */ 
/* 2175 */             i37 |= i25 << i36 - 16 | i24 >> 32 - i36;
/* 2176 */             i38 = i24 << i36;
/*      */ 
/*      */           }
/* 2179 */           else if (i36 == 16)
/*      */           {
/*      */ 
/* 2182 */             i37 |= i25;
/*      */             
/* 2184 */             i38 = i24 << 16;
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/* 2190 */             i37 |= i25 >> 16 - i36;
/* 2191 */             i38 = i25 << 16 + i36;
/*      */             
/* 2193 */             i38 |= i24 << i36;
/*      */           }
/*      */           
/*      */ 
/* 2197 */           break;
/*      */         
/*      */         case 2: 
/* 2200 */           if (i36 > 16)
/*      */           {
/*      */ 
/* 2203 */             i37 |= i24 << i36 - 16;
/*      */ 
/*      */           }
/* 2206 */           else if (i36 == 16)
/*      */           {
/*      */ 
/* 2209 */             i37 |= i24;
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/* 2215 */             i37 |= i24 >> 16 - i36;
/* 2216 */             i38 = i24 << 16 + i36;
/*      */           }
/*      */           
/*      */ 
/* 2220 */           break;
/*      */         }
/*      */         
/*      */         
/*      */ 
/*      */ 
/* 2226 */         if ((i23 != 0) && ((i32 != 0) || ((i38 & 0x1) != 0)))
/*      */         {
/* 2228 */           if (i38 == -1)
/*      */           {
/* 2230 */             i38 = 0;
/* 2231 */             i37++;
/*      */             
/* 2233 */             if ((i37 & 0x200000) != 0)
/*      */             {
/* 2235 */               i38 = i38 >> 1 | i37 << 31;
/* 2236 */               i37 >>= 1;
/* 2237 */               i30++;
/*      */             }
/*      */           }
/*      */           else {
/* 2241 */             i38++;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2254 */         long l = i30 << 52 | (i37 & 0xFFFFF) << 32 | i38 & 0xFFFFFFFF;
/*      */         
/*      */ 
/* 2257 */         d2 = Double.longBitsToDouble(l);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2266 */       d1 = i7 != 0 ? d2 : -d2;
/*      */     }
/*      */     
/* 2269 */     return d1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   double getDoubleImprecise(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2286 */     double d1 = 0.0D;
/*      */     Object localObject;
/* 2288 */     if (this.rowSpaceIndicator == null)
/*      */     {
/* 2290 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 2291 */       ((SQLException)localObject).fillInStackTrace();
/* 2292 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2297 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*      */ 
/* 2300 */       localObject = this.rowSpaceByte;
/* 2301 */       int i = this.columnIndex + this.byteLength * paramInt + 1;
/* 2302 */       int j = localObject[(i - 1)];
/*      */       
/* 2304 */       int k = localObject[i];
/*      */       
/*      */ 
/* 2307 */       double d2 = 0.0D;
/*      */       
/* 2309 */       int i1 = i + 1;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2320 */       if ((k & 0xFFFFFF80) != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 2325 */         if ((k == -128) && (j == 1)) {
/* 2326 */           return 0.0D;
/*      */         }
/* 2328 */         if ((j == 2) && (k == -1) && (localObject[(i + 1)] == 101))
/*      */         {
/* 2330 */           return Double.POSITIVE_INFINITY;
/*      */         }
/* 2332 */         m = (byte)((k & 0xFF7F) - 65);
/*      */         
/* 2334 */         i3 = j - 1;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2342 */         n = (int)(127.0D - m);
/*      */         
/* 2344 */         i2 = i3 % 4;
/*      */         
/* 2346 */         switch (i2)
/*      */         {
/*      */ 
/*      */         case 1: 
/* 2350 */           d2 = (localObject[i1] - 1) * factorTable[n];
/*      */           
/* 2352 */           break;
/*      */         
/*      */         case 2: 
/* 2355 */           d2 = ((localObject[i1] - 1) * 100 + (localObject[(i1 + 1)] - 1)) * factorTable[(n + 1)];
/*      */           
/*      */ 
/* 2358 */           break;
/*      */         
/*      */         case 3: 
/* 2361 */           d2 = ((localObject[i1] - 1) * 10000 + (localObject[(i1 + 1)] - 1) * 100 + (localObject[(i1 + 2)] - 1)) * factorTable[(n + 2)];
/*      */           
/*      */ 
/*      */ 
/* 2365 */           break;
/*      */         }
/*      */         
/* 2371 */         for (; 
/*      */             
/*      */ 
/* 2371 */             i2 < i3; i2 += 4) {
/* 2372 */           d2 += ((localObject[(i1 + i2)] - 1) * 1000000 + (localObject[(i1 + i2 + 1)] - 1) * 10000 + (localObject[(i1 + i2 + 2)] - 1) * 100 + (localObject[(i1 + i2 + 3)] - 1)) * factorTable[(n + i2 + 3)];
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2382 */       if ((k == 0) && (j == 1)) {
/* 2383 */         return Double.NEGATIVE_INFINITY;
/*      */       }
/* 2385 */       int m = (byte)(((k ^ 0xFFFFFFFF) & 0xFF7F) - 65);
/*      */       
/* 2387 */       int i3 = j - 1;
/*      */       
/* 2389 */       if ((i3 != 20) || (localObject[(i + i3)] == 102)) {
/* 2390 */         i3--;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2398 */       int n = (int)(127.0D - m);
/*      */       
/* 2400 */       int i2 = i3 % 4;
/*      */       
/* 2402 */       switch (i2)
/*      */       {
/*      */ 
/*      */       case 1: 
/* 2406 */         d2 = (101 - localObject[i1]) * factorTable[n];
/*      */         
/* 2408 */         break;
/*      */       
/*      */       case 2: 
/* 2411 */         d2 = ((101 - localObject[i1]) * 100 + (101 - localObject[(i1 + 1)])) * factorTable[(n + 1)];
/*      */         
/*      */ 
/* 2414 */         break;
/*      */       
/*      */       case 3: 
/* 2417 */         d2 = ((101 - localObject[i1]) * 10000 + (101 - localObject[(i1 + 1)]) * 100 + (101 - localObject[(i1 + 2)])) * factorTable[(n + 2)];
/*      */         
/*      */ 
/*      */ 
/* 2421 */         break;
/*      */       }
/*      */       
/* 2427 */       for (; 
/*      */           
/*      */ 
/* 2427 */           i2 < i3; i2 += 4) {
/* 2428 */         d2 += ((101 - localObject[(i1 + i2)]) * 1000000 + (101 - localObject[(i1 + i2 + 1)]) * 10000 + (101 - localObject[(i1 + i2 + 2)]) * 100 + (101 - localObject[(i1 + i2 + 3)])) * factorTable[(n + i2 + 3)];
/*      */       }
/*      */       
/*      */ 
/* 2432 */       d2 = -d2;
/*      */       
/*      */ 
/*      */ 
/* 2436 */       d1 = d2;
/*      */     }
/*      */     
/* 2439 */     return d1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2454 */   int[] digs = new int[27];
/*      */   static final int LNXSGNBT = 128;
/*      */   static final byte LNXDIGS = 20;
/*      */   
/*      */   BigDecimal getBigDecimal(int paramInt) throws SQLException {
/* 2459 */     BigDecimal localBigDecimal = null;
/*      */     Object localObject;
/* 2461 */     if (this.rowSpaceIndicator == null)
/*      */     {
/* 2463 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 2464 */       ((SQLException)localObject).fillInStackTrace();
/* 2465 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2470 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*      */ 
/* 2473 */       localObject = this.rowSpaceByte;
/* 2474 */       int i = this.columnIndex + this.byteLength * paramInt + 1;
/* 2475 */       int j = localObject[(i - 1)];
/*      */       
/* 2477 */       for (int k = 0; k < 27; k++) {
/* 2478 */         this.digs[k] = 0;
/*      */       }
/* 2480 */       k = 0;
/* 2481 */       int m = 1;
/*      */       
/* 2483 */       int i1 = 26;
/* 2484 */       int i2 = 0;
/*      */       
/*      */ 
/* 2487 */       int i5 = localObject[i];
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 2492 */       int i10 = 0;
/*      */       
/* 2494 */       if ((i5 & 0xFFFFFF80) != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2502 */         if ((i5 == -128) && (j == 1)) {
/* 2503 */           return BIGDEC_ZERO;
/*      */         }
/* 2505 */         if ((j == 2) && (i5 == -1) && (localObject[(i + 1)] == 101))
/*      */         {
/*      */ 
/*      */ 
/* 2509 */           throwOverflow();
/*      */         }
/* 2511 */         i6 = 1;
/* 2512 */         i7 = (byte)((i5 & 0xFF7F) - 65);
/*      */         
/*      */ 
/* 2515 */         i4 = j - 1;
/* 2516 */         n = i4 - 1;
/* 2517 */         i8 = i7 - i4 + 1 << 1;
/*      */         
/* 2519 */         if (i8 > 0)
/*      */         {
/* 2521 */           i8 = 0;
/* 2522 */           n = i7;
/*      */         }
/* 2524 */         else if (i8 < 0) {
/* 2525 */           i10 = (localObject[(i + i4)] - 1) % 10 == 0 ? 1 : 0;
/*      */         }
/* 2527 */         m = (byte)(m + 1);k = localObject[(i + m)] - 1;
/*      */         
/* 2529 */         while ((n & 0x1) != 0)
/*      */         {
/* 2531 */           if (m > i4) {
/* 2532 */             k *= 100;
/*      */           } else {
/* 2534 */             m = (byte)(m + 1);k = k * 100 + (localObject[(i + m)] - 1);
/*      */           }
/* 2536 */           n--;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2547 */       if ((i5 == 0) && (j == 1))
/*      */       {
/*      */ 
/* 2550 */         throwOverflow();
/*      */       }
/* 2552 */       int i6 = -1;
/* 2553 */       int i7 = (byte)(((i5 ^ 0xFFFFFFFF) & 0xFF7F) - 65);
/*      */       
/*      */ 
/* 2556 */       int i4 = j - 1;
/*      */       
/* 2558 */       if ((i4 != 20) || (localObject[(i + i4)] == 102)) {
/* 2559 */         i4--;
/*      */       }
/* 2561 */       int n = i4 - 1;
/*      */       
/* 2563 */       int i8 = i7 - i4 + 1 << 1;
/*      */       
/* 2565 */       if (i8 > 0)
/*      */       {
/* 2567 */         i8 = 0;
/* 2568 */         n = i7;
/*      */       }
/* 2570 */       else if (i8 < 0) {
/* 2571 */         i10 = (101 - localObject[(i + i4)]) % 10 == 0 ? 1 : 0;
/*      */       }
/* 2573 */       m = (byte)(m + 1);k = 101 - localObject[(i + m)];
/*      */       
/* 2575 */       while ((n & 0x1) != 0)
/*      */       {
/* 2577 */         if (m > i4) {
/* 2578 */           k *= 100;
/*      */         } else {
/* 2580 */           m = (byte)(m + 1);k = k * 100 + (101 - localObject[(i + m)]);
/*      */         }
/* 2582 */         n--;
/*      */       }
/*      */       
/*      */ 
/* 2586 */       if (i10 != 0)
/*      */       {
/* 2588 */         i8++;
/* 2589 */         k /= 10;
/*      */       }
/*      */       
/* 2592 */       int i9 = i4 - 1;
/*      */       
/* 2594 */       while (n != 0)
/*      */       {
/* 2596 */         if (i6 == 1)
/*      */         {
/* 2598 */           if (i10 != 0)
/*      */           {
/* 2600 */             i2 = (localObject[(i + m - 1)] - 1) % 10 * 1000 + (localObject[(i + m)] - 1) * 10 + (localObject[(i + m + 1)] - 1) / 10 + k * 10000;
/*      */             
/*      */ 
/* 2603 */             m = (byte)(m + 2);
/*      */           }
/* 2605 */           else if (m < i9)
/*      */           {
/* 2607 */             i2 = (localObject[(i + m)] - 1) * 100 + (localObject[(i + m + 1)] - 1) + k * 10000;
/*      */             
/*      */ 
/* 2610 */             m = (byte)(m + 2);
/*      */           }
/*      */           else
/*      */           {
/* 2614 */             i2 = 0;
/*      */             
/* 2616 */             if (m <= i4)
/*      */             {
/* 2618 */               for (i11 = 0; 
/*      */                   
/* 2620 */                   m <= i4; i11++) {
/* 2621 */                 m = (byte)(m + 1);i2 = i2 * 100 + (localObject[(i + m)] - 1);
/*      */               }
/* 2623 */               for (; i11 < 2; i11++) {
/* 2624 */                 i2 *= 100;
/*      */               }
/*      */             }
/* 2627 */             i2 += k * 10000;
/*      */           }
/*      */         }
/* 2630 */         else if (i10 != 0)
/*      */         {
/* 2632 */           i2 = (101 - localObject[(i + m - 1)]) % 10 * 1000 + (101 - localObject[(i + m)]) * 10 + (101 - localObject[(i + m + 1)]) / 10 + k * 10000;
/*      */           
/*      */ 
/* 2635 */           m = (byte)(m + 2);
/*      */         }
/* 2637 */         else if (m < i9)
/*      */         {
/* 2639 */           i2 = (101 - localObject[(i + m)]) * 100 + (101 - localObject[(i + m + 1)]) + k * 10000;
/*      */           
/*      */ 
/* 2642 */           m = (byte)(m + 2);
/*      */         }
/*      */         else
/*      */         {
/* 2646 */           i2 = 0;
/*      */           
/* 2648 */           if (m <= i4)
/*      */           {
/* 2650 */             for (i11 = 0; 
/*      */                 
/* 2652 */                 m <= i4; i11++) {
/* 2653 */               m = (byte)(m + 1);i2 = i2 * 100 + (101 - localObject[(i + m)]);
/*      */             }
/* 2655 */             for (; i11 < 2; i11++) {
/* 2656 */               i2 *= 100;
/*      */             }
/*      */           }
/* 2659 */           i2 += k * 10000;
/*      */         }
/*      */         
/* 2662 */         k = i2 & 0xFFFF;
/*      */         
/* 2664 */         for (int i11 = 25; i11 >= i1; i11--)
/*      */         {
/* 2666 */           i2 = (i2 >> 16) + this.digs[i11] * 10000;
/* 2667 */           this.digs[i11] = (i2 & 0xFFFF);
/*      */         }
/*      */         
/* 2670 */         if (i1 > 0)
/*      */         {
/* 2672 */           i2 >>= 16;
/* 2673 */           if (i2 != 0) {
/* 2674 */             i1 = (byte)(i1 - 1);this.digs[i1] = i2;
/*      */           }
/*      */         }
/* 2677 */         n -= 2;
/*      */       }
/*      */       
/* 2680 */       this.digs[26] = k;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2691 */       int i12 = (byte)(this.digs[i1] >> 8 & 0xFF);
/*      */       int i3;
/* 2693 */       byte[] arrayOfByte; int i13; int i14; if (i12 == 0)
/*      */       {
/* 2695 */         i3 = 53 - (i1 << 1);
/* 2696 */         arrayOfByte = new byte[i3];
/*      */         
/* 2698 */         for (i13 = 26; i13 > i1; i13--)
/*      */         {
/* 2700 */           i14 = i13 - i1 << 1;
/*      */           
/* 2702 */           arrayOfByte[(i14 - 1)] = ((byte)(this.digs[i13] >> 8 & 0xFF));
/* 2703 */           arrayOfByte[i14] = ((byte)(this.digs[i13] & 0xFF));
/*      */         }
/*      */         
/* 2706 */         arrayOfByte[0] = ((byte)(this.digs[i1] & 0xFF));
/*      */       }
/*      */       else
/*      */       {
/* 2710 */         i3 = 54 - (i1 << 1);
/* 2711 */         arrayOfByte = new byte[i3];
/*      */         
/* 2713 */         for (i13 = 26; i13 > i1; i13--)
/*      */         {
/* 2715 */           i14 = i13 - i1 << 1;
/*      */           
/* 2717 */           arrayOfByte[i14] = ((byte)(this.digs[i13] >> 8 & 0xFF));
/* 2718 */           arrayOfByte[(i14 + 1)] = ((byte)(this.digs[i13] & 0xFF));
/*      */         }
/*      */         
/* 2721 */         arrayOfByte[0] = i12;
/* 2722 */         arrayOfByte[1] = ((byte)(this.digs[i1] & 0xFF));
/*      */       }
/*      */       
/* 2725 */       if ((i8 == 0) && (i3 < 8) && (i3 > 0)) {
/* 2726 */         long l = arrayOfByte[0] & 0xFF;
/* 2727 */         for (int i15 = 1; i15 < i3; i15++) l = l << 8 | arrayOfByte[i15] & 0xFF;
/* 2728 */         l *= i6;
/* 2729 */         localBigDecimal = new BigDecimal(l);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 2734 */         BigInteger localBigInteger = new BigInteger(i6, arrayOfByte);
/*      */         
/* 2736 */         localBigDecimal = new BigDecimal(localBigInteger, -i8);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 2741 */     return localBigDecimal;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   BigDecimal getBigDecimal(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2749 */     if (this.rowSpaceIndicator == null)
/*      */     {
/* 2751 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 2752 */       localSQLException.fillInStackTrace();
/* 2753 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2758 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt1)] == -1)
/*      */     {
/* 2760 */       return null;
/*      */     }
/*      */     
/*      */ 
/* 2764 */     return getBigDecimal(paramInt1).setScale(paramInt2, 6);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   String getString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2782 */     String str1 = null;
/*      */     Object localObject;
/* 2784 */     if (this.rowSpaceIndicator == null)
/*      */     {
/* 2786 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 2787 */       ((SQLException)localObject).fillInStackTrace();
/* 2788 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2793 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*      */ 
/* 2796 */       localObject = this.rowSpaceByte;
/* 2797 */       int i = this.columnIndex + this.byteLength * paramInt + 1;
/* 2798 */       int j = localObject[(i - 1)];
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3079 */       byte[] arrayOfByte = new byte[j];
/*      */       
/* 3081 */       System.arraycopy(localObject, i, arrayOfByte, 0, j);
/*      */       
/* 3083 */       NUMBER localNUMBER = new NUMBER(arrayOfByte);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3092 */       String str2 = NUMBER.toString(arrayOfByte);
/* 3093 */       int k = str2.length();
/*      */       
/* 3095 */       if ((str2.startsWith("0.")) || (str2.startsWith("-0."))) {
/* 3096 */         k--;
/*      */       }
/* 3098 */       if (k > 38)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3108 */         str2 = localNUMBER.toText(-44, null);
/*      */         
/*      */ 
/*      */ 
/* 3112 */         int m = str2.indexOf('E');
/* 3113 */         int n = str2.indexOf('+');
/*      */         
/* 3115 */         if (m == -1)
/*      */         {
/* 3117 */           m = str2.indexOf('e');
/*      */         }
/*      */         
/* 3120 */         int i1 = m - 1;
/*      */         
/* 3122 */         while (str2.charAt(i1) == '0')
/*      */         {
/* 3124 */           i1--;
/*      */         }
/*      */         
/* 3127 */         String str3 = str2.substring(0, i1 + 1);
/* 3128 */         String str4 = null;
/*      */         
/* 3130 */         if (n > 0)
/*      */         {
/* 3132 */           str4 = str2.substring(n + 1);
/*      */         }
/*      */         else
/*      */         {
/* 3136 */           str4 = str2.substring(m + 1);
/*      */         }
/*      */         
/* 3139 */         return (str3 + "E" + str4).trim();
/*      */       }
/*      */       
/* 3142 */       return localNUMBER.toText(38, null).trim();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3148 */     return str1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   NUMBER getNUMBER(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3164 */     NUMBER localNUMBER = null;
/*      */     
/* 3166 */     if (this.rowSpaceIndicator == null)
/*      */     {
/* 3168 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 3169 */       localSQLException.fillInStackTrace();
/* 3170 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3175 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/* 3177 */       int i = this.columnIndex + this.byteLength * paramInt + 1;
/* 3178 */       int j = this.rowSpaceByte[(i - 1)];
/* 3179 */       byte[] arrayOfByte = new byte[j];
/*      */       
/* 3181 */       System.arraycopy(this.rowSpaceByte, i, arrayOfByte, 0, j);
/*      */       
/* 3183 */       localNUMBER = new NUMBER(arrayOfByte);
/*      */     }
/*      */     
/* 3186 */     return localNUMBER;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   Object getObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3202 */     Object localObject = null;
/*      */     SQLException localSQLException;
/* 3204 */     if (this.rowSpaceIndicator == null)
/*      */     {
/* 3206 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 3207 */       localSQLException.fillInStackTrace();
/* 3208 */       throw localSQLException;
/*      */     }
/*      */     
/* 3211 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/* 3213 */       if (this.externalType == 0)
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3243 */         if ((this.statement.connection.j2ee13Compliant) && (this.precision != 0) && (this.scale == -127))
/*      */         {
/* 3245 */           localObject = new Double(getDouble(paramInt));
/*      */         } else {
/* 3247 */           localObject = getBigDecimal(paramInt);
/*      */         }
/*      */       }
/*      */       else {
/* 3251 */         switch (this.externalType)
/*      */         {
/*      */ 
/*      */         case -7: 
/* 3255 */           return Boolean.valueOf(getBoolean(paramInt));
/*      */         
/*      */         case -6: 
/* 3258 */           return Byte.valueOf(getByte(paramInt));
/*      */         
/*      */         case 5: 
/* 3261 */           return Short.valueOf(getShort(paramInt));
/*      */         
/*      */         case 4: 
/* 3264 */           return Integer.valueOf(getInt(paramInt));
/*      */         
/*      */         case -5: 
/* 3267 */           return Long.valueOf(getLong(paramInt));
/*      */         
/*      */ 
/*      */         case 6: 
/*      */         case 8: 
/* 3272 */           return Double.valueOf(getDouble(paramInt));
/*      */         
/*      */         case 7: 
/* 3275 */           return Float.valueOf(getFloat(paramInt));
/*      */         
/*      */ 
/*      */         case 2: 
/*      */         case 3: 
/* 3280 */           return getBigDecimal(paramInt);
/*      */         }
/*      */         
/*      */         
/* 3284 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 3285 */         localSQLException.fillInStackTrace();
/* 3286 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3293 */     return localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   Object getObject(int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 3309 */     return getObject(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   Datum getOracleObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3325 */     return getNUMBER(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   byte[] getBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3345 */     byte[] arrayOfByte = null;
/*      */     
/* 3347 */     if (this.rowSpaceIndicator == null)
/*      */     {
/* 3349 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 3350 */       localSQLException.fillInStackTrace();
/* 3351 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3356 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/* 3358 */       int i = this.columnIndex + this.byteLength * paramInt + 1;
/* 3359 */       int j = this.rowSpaceByte[(i - 1)];
/*      */       
/* 3361 */       arrayOfByte = new byte[j];
/*      */       
/* 3363 */       System.arraycopy(this.rowSpaceByte, i, arrayOfByte, 0, j);
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
/*      */ 
/*      */ 
/*      */   static final byte LNXEXPBS = 64;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int LNXEXPMX = 127;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3392 */   static final BigDecimal BIGDEC_ZERO = BigDecimal.valueOf(0L);
/*      */   
/*      */   static final byte MAX_LONG_EXPONENT = 9;
/*      */   
/*      */   static final byte MIN_LONG_EXPONENT = 9;
/*      */   static final byte MAX_INT_EXPONENT = 4;
/*      */   static final byte MIN_INT_EXPONENT = 4;
/*      */   static final byte MAX_SHORT_EXPONENT = 2;
/*      */   static final byte MIN_SHORT_EXPONENT = 2;
/*      */   static final byte MAX_BYTE_EXPONENT = 1;
/*      */   static final byte MIN_BYTE_EXPONENT = 1;
/* 3403 */   static final int[] MAX_LONG = { 202, 10, 23, 34, 73, 4, 69, 55, 78, 59, 8 };
/*      */   
/*      */ 
/*      */ 
/* 3407 */   static final int[] MIN_LONG = { 53, 92, 79, 68, 29, 98, 33, 47, 24, 43, 93, 102 };
/*      */   
/*      */ 
/*      */ 
/*      */   static final int MAX_LONG_length = 11;
/*      */   
/*      */ 
/*      */   static final int MIN_LONG_length = 12;
/*      */   
/*      */ 
/* 3417 */   static final double[] factorTable = { 1.0E254D, 1.0E252D, 1.0E250D, 1.0E248D, 1.0E246D, 1.0E244D, 1.0E242D, 1.0E240D, 1.0E238D, 1.0E236D, 1.0E234D, 1.0E232D, 1.0E230D, 1.0E228D, 1.0E226D, 1.0E224D, 1.0E222D, 1.0E220D, 1.0E218D, 1.0E216D, 1.0E214D, 1.0E212D, 1.0E210D, 1.0E208D, 1.0E206D, 1.0E204D, 1.0E202D, 1.0E200D, 1.0E198D, 1.0E196D, 1.0E194D, 1.0E192D, 1.0E190D, 1.0E188D, 1.0E186D, 1.0E184D, 1.0E182D, 1.0E180D, 1.0E178D, 1.0E176D, 1.0E174D, 1.0E172D, 1.0E170D, 1.0E168D, 1.0E166D, 1.0E164D, 1.0E162D, 1.0E160D, 1.0E158D, 1.0E156D, 1.0E154D, 1.0E152D, 1.0E150D, 1.0E148D, 1.0E146D, 1.0E144D, 1.0E142D, 1.0E140D, 1.0E138D, 1.0E136D, 1.0E134D, 1.0E132D, 1.0E130D, 1.0E128D, 1.0E126D, 1.0E124D, 1.0E122D, 1.0E120D, 1.0E118D, 1.0E116D, 1.0E114D, 1.0E112D, 1.0E110D, 1.0E108D, 1.0E106D, 1.0E104D, 1.0E102D, 1.0E100D, 1.0E98D, 1.0E96D, 1.0E94D, 1.0E92D, 1.0E90D, 1.0E88D, 1.0E86D, 1.0E84D, 1.0E82D, 1.0E80D, 1.0E78D, 1.0E76D, 1.0E74D, 1.0E72D, 1.0E70D, 1.0E68D, 1.0E66D, 1.0E64D, 1.0E62D, 1.0E60D, 1.0E58D, 1.0E56D, 1.0E54D, 1.0E52D, 1.0E50D, 1.0E48D, 1.0E46D, 1.0E44D, 1.0E42D, 1.0E40D, 1.0E38D, 1.0E36D, 1.0E34D, 1.0E32D, 1.0E30D, 1.0E28D, 1.0E26D, 1.0E24D, 1.0E22D, 1.0E20D, 1.0E18D, 1.0E16D, 1.0E14D, 1.0E12D, 1.0E10D, 1.0E8D, 1000000.0D, 10000.0D, 100.0D, 1.0D, 0.01D, 1.0E-4D, 1.0E-6D, 1.0E-8D, 1.0E-10D, 1.0E-12D, 1.0E-14D, 1.0E-16D, 1.0E-18D, 1.0E-20D, 1.0E-22D, 1.0E-24D, 1.0E-26D, 1.0E-28D, 1.0E-30D, 1.0E-32D, 1.0E-34D, 1.0E-36D, 1.0E-38D, 1.0E-40D, 1.0E-42D, 1.0E-44D, 1.0E-46D, 1.0E-48D, 1.0E-50D, 1.0E-52D, 1.0E-54D, 1.0E-56D, 1.0E-58D, 1.0E-60D, 1.0E-62D, 1.0E-64D, 1.0E-66D, 1.0E-68D, 1.0E-70D, 1.0E-72D, 1.0E-74D, 1.0E-76D, 1.0E-78D, 1.0E-80D, 1.0E-82D, 1.0E-84D, 1.0E-86D, 1.0E-88D, 1.0E-90D, 1.0E-92D, 1.0E-94D, 1.0E-96D, 1.0E-98D, 1.0E-100D, 1.0E-102D, 1.0E-104D, 1.0E-106D, 1.0E-108D, 1.0E-110D, 1.0E-112D, 1.0E-114D, 1.0E-116D, 1.0E-118D, 1.0E-120D, 1.0E-122D, 1.0E-124D, 1.0E-126D, 1.0E-128D, 1.0E-130D, 1.0E-132D, 1.0E-134D, 1.0E-136D, 1.0E-138D, 1.0E-140D, 1.0E-142D, 1.0E-144D, 1.0E-146D, 1.0E-148D, 1.0E-150D, 1.0E-152D, 1.0E-154D, 1.0E-156D, 1.0E-158D, 1.0E-160D, 1.0E-162D, 1.0E-164D, 1.0E-166D, 1.0E-168D, 1.0E-170D, 1.0E-172D, 1.0E-174D, 1.0E-176D, 1.0E-178D, 1.0E-180D, 1.0E-182D, 1.0E-184D, 1.0E-186D, 1.0E-188D, 1.0E-190D, 1.0E-192D, 1.0E-194D, 1.0E-196D, 1.0E-198D, 1.0E-200D, 1.0E-202D, 1.0E-204D, 1.0E-206D, 1.0E-208D, 1.0E-210D, 1.0E-212D, 1.0E-214D, 1.0E-216D, 1.0E-218D, 1.0E-220D, 1.0E-222D, 1.0E-224D, 1.0E-226D, 1.0E-228D, 1.0E-230D, 1.0E-232D, 1.0E-234D, 1.0E-236D, 1.0E-238D, 1.0E-240D, 1.0E-242D, 1.0E-244D, 1.0E-246D, 1.0E-248D, 1.0E-250D, 1.0E-252D, 1.0E-254D };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3454 */   static final double[] small10pow = { 1.0D, 10.0D, 100.0D, 1000.0D, 10000.0D, 100000.0D, 1000000.0D, 1.0E7D, 1.0E8D, 1.0E9D, 1.0E10D, 1.0E11D, 1.0E12D, 1.0E13D, 1.0E14D, 1.0E15D, 1.0E16D, 1.0E17D, 1.0E18D, 1.0E19D, 1.0E20D, 1.0E21D, 1.0E22D };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3461 */   static final int tablemax = factorTable.length;
/*      */   static final double tablemaxexponent = 127.0D;
/* 3463 */   static final double tableminexponent = 127.0D - (tablemax - 20);
/*      */   
/*      */ 
/*      */   static final int MANTISSA_SIZE = 53;
/*      */   
/* 3468 */   static final int[] expdigs0 = { 25597, 55634, 18440, 18324, 42485, 50370, 56862, 11593, 45703, 57341, 10255, 12549, 59579, 5 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3474 */   static final int[] expdigs1 = { 50890, 19916, 24149, 23777, 11324, 41057, 14921, 56274, 30917, 19462, 54968, 47943, 38791, 3872 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3480 */   static final int[] expdigs2 = { 24101, 29690, 40218, 29073, 29604, 22037, 27674, 9082, 56670, 55244, 20865, 54874, 47573, 38 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3486 */   static final int[] expdigs3 = { 22191, 40873, 1607, 45622, 23883, 24544, 32988, 43530, 61694, 55616, 43150, 32976, 27418, 25379 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3492 */   static final int[] expdigs4 = { 55927, 44317, 6569, 54851, 238, 63160, 51447, 12231, 55667, 25459, 5674, 40962, 52047, 253 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3498 */   static final int[] expdigs5 = { 56264, 8962, 51839, 64773, 39323, 49783, 15587, 30924, 36601, 56615, 27581, 36454, 35254, 2 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3504 */   static final int[] expdigs6 = { 21545, 25466, 59727, 37873, 13099, 7602, 15571, 49963, 37664, 46896, 14328, 59258, 17403, 1663 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3510 */   static final int[] expdigs7 = { 12011, 4842, 3874, 57395, 38141, 46606, 49307, 60792, 31833, 21440, 9318, 47123, 41461, 16 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3516 */   static final int[] expdigs8 = { 52383, 25023, 56409, 43947, 51036, 17420, 62725, 5735, 53692, 44882, 64439, 36137, 24719, 10900 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3522 */   static final int[] expdigs9 = { 65404, 27119, 57580, 26653, 42453, 19179, 26186, 42000, 1847, 62708, 14406, 12813, 247, 109 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3528 */   static final int[] expdigs10 = { 36698, 50078, 40552, 35000, 49576, 56552, 261, 49572, 31475, 59609, 45363, 46658, 5900, 1 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3534 */   static final int[] expdigs11 = { 33321, 54106, 42443, 60698, 47535, 24088, 45785, 18352, 47026, 40291, 5183, 35843, 24059, 714 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3540 */   static final int[] expdigs12 = { 12129, 44450, 22706, 34030, 37175, 8760, 31915, 56544, 23407, 52176, 7260, 41646, 9415, 7 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3546 */   static final int[] expdigs13 = { 43054, 17160, 43698, 6780, 36385, 52800, 62346, 52747, 33988, 2855, 31979, 38083, 44325, 4681 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3552 */   static final int[] expdigs14 = { 60723, 40803, 16165, 19073, 2985, 9703, 41911, 37227, 41627, 1994, 38986, 27250, 53527, 46 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3558 */   static final int[] expdigs15 = { 36481, 57623, 45627, 58488, 53274, 7238, 2063, 31221, 62631, 25319, 35409, 25293, 54667, 30681 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3564 */   static final int[] expdigs16 = { 52138, 47106, 3077, 4517, 41165, 38738, 39997, 10142, 13078, 16637, 53438, 54647, 53630, 306 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3570 */   static final int[] expdigs17 = { 25425, 24719, 55736, 8564, 12208, 3664, 51518, 17140, 61079, 30312, 2500, 30693, 4468, 3 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3576 */   static final int[] expdigs18 = { 58368, 65134, 52675, 3178, 26300, 7986, 11833, 515, 23109, 63525, 29138, 19030, 50114, 2010 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3582 */   static final int[] expdigs19 = { 41216, 15724, 12323, 26246, 59245, 58406, 46648, 13767, 11372, 15053, 61895, 48686, 7054, 20 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3588 */   static final int[] expdigs20 = { 0, 29248, 62416, 1433, 14025, 43846, 39905, 44375, 137, 47955, 62409, 33386, 48983, 13177 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3594 */   static final int[] expdigs21 = { 0, 21264, 53708, 60962, 25043, 64008, 31200, 50906, 9831, 56185, 43877, 36378, 50952, 131 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3600 */   static final int[] expdigs22 = { 0, 50020, 25440, 60247, 44814, 39961, 6865, 26068, 34832, 9081, 17478, 44928, 20825, 1 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3606 */   static final int[] expdigs23 = { 0, 0, 52929, 10084, 25506, 6346, 61348, 31525, 52689, 61296, 27615, 15903, 40426, 863 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3612 */   static final int[] expdigs24 = { 0, 16384, 24122, 53840, 43508, 13170, 51076, 37670, 58198, 31414, 57292, 61762, 41691, 8 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3618 */   static final int[] expdigs25 = { 0, 0, 4096, 29077, 42481, 30581, 10617, 59493, 46251, 1892, 5557, 4505, 52391, 5659 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3624 */   static final int[] expdigs26 = { 0, 0, 58368, 11431, 1080, 29797, 47947, 36639, 42405, 50481, 29546, 9875, 39190, 56 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3630 */   static final int[] expdigs27 = { 0, 0, 0, 57600, 63028, 53094, 12749, 18174, 21993, 48265, 14922, 59933, 4030, 37092 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3636 */   static final int[] expdigs28 = { 0, 0, 0, 576, 1941, 35265, 9302, 42780, 50682, 28007, 29640, 28124, 60333, 370 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3642 */   static final int[] expdigs29 = { 0, 0, 0, 5904, 8539, 12149, 36793, 43681, 12958, 60573, 21267, 35015, 46478, 3 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3648 */   static final int[] expdigs30 = { 0, 0, 0, 0, 7268, 50548, 47962, 3644, 22719, 26999, 41893, 7421, 56711, 2430 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3653 */   static final int[] expdigs31 = { 0, 0, 0, 0, 7937, 49002, 60772, 28216, 38893, 55975, 63988, 59711, 20227, 24 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3658 */   static final int[] expdigs32 = { 0, 0, 0, 16384, 38090, 63404, 55657, 8801, 62648, 13666, 57656, 60234, 15930 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3663 */   static final int[] expdigs33 = { 0, 0, 0, 4096, 37081, 37989, 16940, 55138, 17665, 39458, 9751, 20263, 159 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3668 */   static final int[] expdigs34 = { 0, 0, 0, 58368, 35104, 16108, 61773, 14313, 30323, 54789, 57113, 38868, 1 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3673 */   static final int[] expdigs35 = { 0, 0, 0, 8448, 18701, 29652, 51080, 65023, 27172, 37903, 3192, 1044 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3678 */   static final int[] expdigs36 = { 0, 0, 0, 37440, 63101, 2917, 39177, 50457, 25830, 50186, 28867, 10 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3683 */   static final int[] expdigs37 = { 0, 0, 0, 56080, 45850, 37384, 3668, 12301, 38269, 18196, 6842 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3688 */   static final int[] expdigs38 = { 0, 0, 0, 46436, 13565, 50181, 34770, 37478, 5625, 27707, 68 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3693 */   static final int[] expdigs39 = { 0, 0, 0, 32577, 45355, 38512, 38358, 3651, 36101, 44841 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3698 */   static final int[] expdigs40 = { 0, 0, 16384, 28506, 5696, 56746, 15456, 50499, 27230, 448 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3703 */   static final int[] expdigs41 = { 0, 0, 4096, 285, 9232, 58239, 57170, 38515, 31729, 4 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3708 */   static final int[] expdigs42 = { 0, 0, 58368, 41945, 57108, 12378, 28752, 48226, 2938 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3713 */   static final int[] expdigs43 = { 0, 0, 24832, 47605, 49067, 23716, 61891, 25385, 29 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3718 */   static final int[] expdigs44 = { 0, 0, 8768, 2442, 50298, 23174, 19624, 19259 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3723 */   static final int[] expdigs45 = { 0, 0, 40720, 45899, 1813, 31689, 38862, 192 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3728 */   static final int[] expdigs46 = { 0, 0, 36452, 14221, 34752, 48813, 60681, 1 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3733 */   static final int[] expdigs47 = { 0, 0, 61313, 34220, 16731, 11629, 1262 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3738 */   static final int[] expdigs48 = { 0, 16384, 60906, 18036, 40144, 40748, 12 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3743 */   static final int[] expdigs49 = { 0, 4096, 609, 15909, 52830, 8271 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3748 */   static final int[] expdigs50 = { 0, 58368, 3282, 56520, 47058, 82 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3753 */   static final int[] expdigs51 = { 0, 41216, 52461, 7118, 54210 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3758 */   static final int[] expdigs52 = { 0, 45632, 51642, 6624, 542 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3763 */   static final int[] expdigs53 = { 0, 25360, 24109, 27591, 5 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3768 */   static final int[] expdigs54 = { 0, 42852, 46771, 3552 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3773 */   static final int[] expdigs55 = { 0, 28609, 34546, 35 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3778 */   static final int[] expdigs56 = { 16384, 4218, 23283 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3783 */   static final int[] expdigs57 = { 4096, 54437, 232 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3788 */   static final int[] expdigs58 = { 58368, 21515, 2 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3793 */   static final int[] expdigs59 = { 57600, 1525 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3798 */   static final int[] expdigs60 = { 16960, 15 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3803 */   static final int[] expdigs61 = { 10000 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3808 */   static final int[] expdigs62 = { 100 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3813 */   static final int[] expdigs63 = { 1 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3818 */   static final int[] expdigs64 = { 36700, 62914, 23592, 49807, 10485, 36700, 62914, 23592, 49807, 10485, 36700, 62914, 23592, 655 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3824 */   static final int[] expdigs65 = { 14784, 18979, 33659, 19503, 2726, 9542, 629, 2202, 40475, 10590, 4299, 47815, 36280, 6 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3830 */   static final int[] expdigs66 = { 16332, 9978, 33613, 31138, 35584, 64252, 13857, 14424, 62281, 46279, 36150, 46573, 63392, 4294 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3836 */   static final int[] expdigs67 = { 6716, 24348, 22618, 23904, 21327, 3919, 44703, 19149, 28803, 48959, 6259, 50273, 62237, 42 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3842 */   static final int[] expdigs68 = { 8471, 23660, 38254, 26440, 33662, 38879, 9869, 11588, 41479, 23225, 60127, 24310, 32615, 28147 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3848 */   static final int[] expdigs69 = { 13191, 6790, 63297, 30410, 12788, 42987, 23691, 28296, 32527, 38898, 41233, 4830, 31128, 281 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3854 */   static final int[] expdigs70 = { 4064, 53152, 62236, 29139, 46658, 12881, 31694, 4870, 19986, 24637, 9587, 28884, 53395, 2 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3860 */   static final int[] expdigs71 = { 26266, 10526, 16260, 55017, 35680, 40443, 19789, 17356, 30195, 55905, 28426, 63010, 44197, 1844 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3866 */   static final int[] expdigs72 = { 38273, 7969, 37518, 26764, 23294, 63974, 18547, 17868, 24550, 41191, 17323, 53714, 29277, 18 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3872 */   static final int[] expdigs73 = { 16739, 37738, 38090, 26589, 43521, 1543, 15713, 10671, 11975, 41533, 18106, 9348, 16921, 12089 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3878 */   static final int[] expdigs74 = { 14585, 61981, 58707, 16649, 25994, 39992, 28337, 17801, 37475, 22697, 31638, 16477, 58496, 120 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3884 */   static final int[] expdigs75 = { 58472, 2585, 40564, 27691, 44824, 27269, 58610, 54572, 35108, 30373, 35050, 10650, 13692, 1 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3890 */   static final int[] expdigs76 = { 50392, 58911, 41968, 49557, 29112, 29939, 43526, 63500, 55595, 27220, 25207, 38361, 18456, 792 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3896 */   static final int[] expdigs77 = { 26062, 32046, 3696, 45060, 46821, 40931, 50242, 60272, 24148, 20588, 6150, 44948, 60477, 7 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3902 */   static final int[] expdigs78 = { 12430, 30407, 320, 41980, 58777, 41755, 41041, 13609, 45167, 13348, 40838, 60354, 19454, 5192 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3908 */   static final int[] expdigs79 = { 30926, 26518, 13110, 43018, 54982, 48258, 24658, 15209, 63366, 11929, 20069, 43857, 60487, 51 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3914 */   static final int[] expdigs80 = { 51263, 54048, 48761, 48627, 30576, 49046, 4414, 61195, 61755, 48474, 19124, 55906, 15511, 34028 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3920 */   static final int[] expdigs81 = { 39834, 11681, 47018, 3107, 64531, 54229, 41331, 41899, 51735, 42427, 59173, 13010, 18505, 340 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3926 */   static final int[] expdigs82 = { 27268, 6670, 31272, 9861, 45865, 10372, 12865, 62678, 23454, 35158, 20252, 29621, 26399, 3 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3932 */   static final int[] expdigs83 = { 57738, 46147, 66, 48154, 11239, 21430, 55809, 46003, 15044, 25138, 52780, 48043, 4883, 2230 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3938 */   static final int[] expdigs84 = { 20893, 62065, 64225, 52254, 59094, 55919, 60195, 5702, 48647, 50058, 7736, 41768, 19709, 22 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3944 */   static final int[] expdigs85 = { 37714, 32321, 45840, 36031, 33290, 47121, 5146, 28127, 9887, 25390, 52929, 2698, 1073, 14615 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3950 */   static final int[] expdigs86 = { 35111, 8187, 18153, 56721, 40309, 59453, 51824, 4868, 45974, 3530, 43783, 8546, 9841, 146 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3956 */   static final int[] expdigs87 = { 23288, 61030, 42779, 19572, 29894, 47780, 45082, 32816, 43713, 33458, 25341, 63655, 30244, 1 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3962 */   static final int[] expdigs88 = { 58138, 33000, 62869, 37127, 61799, 298, 46353, 5693, 63898, 62040, 989, 23191, 53065, 957 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3968 */   static final int[] expdigs89 = { 42524, 32442, 36673, 15444, 22900, 658, 61412, 32824, 21610, 64190, 1975, 11373, 37886, 9 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3974 */   static final int[] expdigs90 = { 26492, 4357, 32437, 10852, 34233, 53968, 55056, 34692, 64553, 38226, 41929, 21646, 6667, 6277 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3980 */   static final int[] expdigs91 = { 61213, 698, 16053, 50571, 2963, 50347, 13657, 48188, 46520, 19387, 33187, 25775, 50529, 62 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3986 */   static final int[] expdigs92 = { 42864, 54351, 45226, 20476, 23443, 17724, 3780, 44701, 52910, 23402, 28374, 46862, 40234, 41137 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3992 */   static final int[] expdigs93 = { 23366, 62147, 58123, 44113, 55284, 39498, 3314, 9622, 9704, 27759, 25187, 43722, 24650, 411 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3998 */   static final int[] expdigs94 = { 38899, 44530, 19586, 37141, 1863, 9570, 32801, 31553, 51870, 62536, 51369, 30583, 7455, 4 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4004 */   static final int[] expdigs95 = { 10421, 4321, 43699, 3472, 65252, 17057, 13858, 29819, 14733, 21490, 40602, 31315, 65186, 2695 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4010 */   static final int[] expdigs96 = { 6002, 54438, 29272, 34113, 17036, 25074, 36183, 953, 25051, 12011, 20722, 4245, 62911, 26 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4016 */   static final int[] expdigs97 = { 14718, 45935, 8408, 42891, 21312, 56531, 44159, 45581, 20325, 36295, 35509, 24455, 30844, 17668 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4022 */   static final int[] expdigs98 = { 54542, 45023, 23021, 3050, 31015, 20881, 50904, 40432, 33626, 14125, 44264, 60537, 44872, 176 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4028 */   static final int[] expdigs99 = { 60183, 8969, 14648, 17725, 11451, 50016, 34587, 46279, 19341, 42084, 16826, 5848, 50256, 1 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4034 */   static final int[] expdigs100 = { 64999, 53685, 60382, 19151, 25736, 5357, 31302, 23283, 14225, 52622, 56781, 39489, 60351, 1157 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4040 */   static final int[] expdigs101 = { 1305, 4469, 39270, 18541, 63827, 59035, 54707, 16616, 32910, 48367, 64137, 2360, 37959, 11 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4046 */   static final int[] expdigs102 = { 45449, 32125, 19705, 56098, 51958, 5225, 18285, 13654, 9341, 25888, 50946, 26855, 36068, 7588 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4052 */   static final int[] expdigs103 = { 27324, 53405, 43450, 25464, 3796, 3329, 46058, 53220, 26307, 53998, 33932, 23861, 58032, 75 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4058 */   static final int[] expdigs104 = { 63080, 50735, 1844, 21406, 57926, 63607, 24936, 52889, 23469, 64488, 539, 8859, 21210, 49732 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4064 */   static final int[] expdigs105 = { 62890, 39828, 3950, 32982, 39245, 21607, 40226, 50991, 18584, 10475, 59643, 40720, 21183, 497 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4070 */   static final int[] expdigs106 = { 37329, 64623, 11835, 985, 46923, 48712, 28582, 21481, 28366, 41392, 13703, 49559, 63781, 4 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4076 */   static final int[] expdigs107 = { 3316, 60011, 41933, 47959, 54404, 39790, 12283, 941, 46090, 42226, 18108, 38803, 16879, 3259 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4082 */   static final int[] expdigs108 = { 46563, 56305, 5006, 45044, 49040, 12849, 778, 6563, 46336, 3043, 7390, 2354, 38835, 32 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4088 */   static final int[] expdigs109 = { 28653, 3742, 33331, 2671, 39772, 29981, 56489, 1973, 26280, 26022, 56391, 56434, 57039, 21359 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4094 */   static final int[] expdigs110 = { 9461, 17732, 7542, 26241, 8917, 24548, 61513, 13126, 59245, 41547, 1874, 41852, 39236, 213 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4100 */   static final int[] expdigs111 = { 36794, 22459, 63645, 14024, 42032, 53329, 25518, 11272, 18287, 20076, 62933, 3039, 8912, 2 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4106 */   static final int[] expdigs112 = { 14926, 15441, 32337, 42579, 26354, 35154, 22815, 36955, 12564, 8047, 856, 41917, 55080, 1399 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4112 */   static final int[] expdigs113 = { 8668, 50617, 10153, 17465, 1574, 28532, 15301, 58041, 38791, 60373, 663, 29255, 65431, 13 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4118 */   static final int[] expdigs114 = { 21589, 32199, 24754, 45321, 9349, 26230, 35019, 37508, 20896, 42986, 31405, 12458, 65173, 9173 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4124 */   static final int[] expdigs115 = { 46746, 1632, 61196, 50915, 64318, 41549, 2971, 23968, 59191, 58756, 61917, 779, 48493, 91 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4130 */   static final int[] expdigs116 = { 1609, 63382, 15744, 15685, 51627, 56348, 33838, 52458, 44148, 11077, 56293, 41906, 45227, 60122 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4136 */   static final int[] expdigs117 = { 19676, 45198, 6055, 38823, 8380, 49060, 17377, 58196, 43039, 21737, 59545, 12870, 14870, 601 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4142 */   static final int[] expdigs118 = { 4128, 2418, 28241, 13495, 26298, 3767, 31631, 5169, 8950, 27087, 56956, 4060, 804, 6 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4148 */   static final int[] expdigs119 = { 39930, 40673, 19029, 54677, 38145, 23200, 41325, 24564, 24955, 54484, 23863, 52998, 13147, 3940 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4154 */   static final int[] expdigs120 = { 3676, 24655, 34924, 27416, 23974, 887, 10899, 4833, 21221, 28725, 19899, 57546, 26345, 39 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4160 */   static final int[] expdigs121 = { 28904, 41324, 18596, 42292, 12070, 52013, 30810, 61057, 55753, 32324, 38953, 6752, 32688, 25822 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4166 */   static final int[] expdigs122 = { 42232, 26627, 2807, 27948, 50583, 49016, 32420, 64180, 3178, 3600, 21361, 52496, 14744, 258 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4172 */   static final int[] expdigs123 = { 2388, 59904, 28863, 7488, 31963, 8354, 47510, 15059, 2653, 58363, 31670, 21496, 38158, 2 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4178 */   static final int[] expdigs124 = { 50070, 5266, 26158, 10774, 15148, 6873, 30230, 33898, 63720, 51799, 4515, 50124, 19875, 1692 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4184 */   static final int[] expdigs125 = { 54240, 3984, 12058, 2729, 13914, 11865, 38313, 39660, 10467, 20834, 36745, 57517, 60491, 16 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4190 */   static final int[] expdigs126 = { 5387, 58214, 9214, 13883, 14445, 34873, 21745, 13490, 23334, 25008, 58535, 19372, 44484, 11090 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4196 */   static final int[] expdigs127 = { 27578, 64807, 12543, 794, 13907, 61297, 12013, 64360, 15961, 20566, 24178, 15922, 59427, 110 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4202 */   static final int[] expdigs128 = { 49427, 41935, 46000, 59645, 45358, 51075, 15848, 32756, 38170, 14623, 35631, 57175, 7147, 1 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4208 */   static final int[] expdigs129 = { 33941, 39160, 55469, 45679, 22878, 60091, 37210, 18508, 1638, 57398, 65026, 41643, 54966, 726 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4214 */   static final int[] expdigs130 = { 60632, 24639, 41842, 62060, 20544, 59583, 52800, 1495, 48513, 43827, 10480, 1727, 17589, 7 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4220 */   static final int[] expdigs131 = { 5590, 60244, 53985, 26632, 53049, 33628, 58267, 54922, 21641, 62744, 58109, 2070, 26887, 4763 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4226 */   static final int[] expdigs132 = { 62970, 37957, 34618, 29757, 24123, 2302, 17622, 58876, 44780, 6525, 33349, 36065, 41556, 47 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4232 */   static final int[] expdigs133 = { 1615, 24878, 20040, 11487, 23235, 27766, 59005, 57847, 60881, 11588, 63635, 61281, 31817, 31217 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4238 */   static final int[] expdigs134 = { 14434, 2870, 65081, 44023, 40864, 40254, 47120, 6476, 32066, 23053, 17020, 19618, 11459, 312 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4244 */   static final int[] expdigs135 = { 43398, 40005, 36695, 8304, 12205, 16131, 42414, 38075, 63890, 2851, 61774, 59833, 7978, 3 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4250 */   static final int[] expdigs136 = { 56426, 22060, 15473, 31824, 19088, 38788, 64386, 12875, 35770, 65519, 11824, 19623, 56959, 2045 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4256 */   static final int[] expdigs137 = { 16292, 32333, 10640, 47504, 29026, 30534, 23581, 6682, 10188, 24248, 44027, 51969, 30060, 20 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4262 */   static final int[] expdigs138 = { 29432, 37518, 55373, 2727, 33243, 22572, 16689, 35625, 34145, 15830, 59880, 32552, 52948, 13407 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4268 */   static final int[] expdigs139 = { 61898, 27244, 41841, 33450, 18682, 13988, 24415, 11497, 1652, 34237, 34677, 325, 5117, 134 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4274 */   static final int[] expdigs140 = { 16347, 3549, 48915, 22616, 21158, 51913, 32356, 21086, 3293, 8862, 1002, 26873, 22333, 1 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4280 */   static final int[] expdigs141 = { 25966, 63733, 28215, 31946, 40858, 58538, 11004, 6877, 6109, 3965, 35478, 37365, 45488, 878 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4286 */   static final int[] expdigs142 = { 45479, 34060, 17321, 19980, 1719, 16314, 29601, 8588, 58388, 22321, 14117, 63288, 51572, 8 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4292 */   static final int[] expdigs143 = { 46861, 47640, 11481, 23766, 46730, 53756, 8682, 60589, 42028, 27453, 29714, 31598, 39954, 5758 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4298 */   static final int[] expdigs144 = { 29304, 58803, 51232, 27762, 60760, 17576, 19092, 26820, 11561, 48771, 6850, 27841, 38410, 57 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4304 */   static final int[] expdigs145 = { 2916, 49445, 34666, 46387, 18627, 58279, 60468, 190, 3545, 51889, 51605, 47909, 40910, 37739 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4310 */   static final int[] expdigs146 = { 19034, 62098, 15419, 33887, 38852, 53011, 28129, 37357, 11176, 48360, 9035, 9654, 25968, 377 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4316 */   static final int[] expdigs147 = { 25094, 10451, 7363, 55389, 57404, 27399, 11422, 39695, 28947, 12935, 61694, 26310, 50722, 3 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4322 */   static final int[][] expdigstable = { expdigs0, expdigs1, expdigs2, expdigs3, expdigs4, expdigs5, expdigs6, expdigs7, expdigs8, expdigs9, expdigs10, expdigs11, expdigs12, expdigs13, expdigs14, expdigs15, expdigs16, expdigs17, expdigs18, expdigs19, expdigs20, expdigs21, expdigs22, expdigs23, expdigs24, expdigs25, expdigs26, expdigs27, expdigs28, expdigs29, expdigs30, expdigs31, expdigs32, expdigs33, expdigs34, expdigs35, expdigs36, expdigs37, expdigs38, expdigs39, expdigs40, expdigs41, expdigs42, expdigs43, expdigs44, expdigs45, expdigs46, expdigs47, expdigs48, expdigs49, expdigs50, expdigs51, expdigs52, expdigs53, expdigs54, expdigs55, expdigs56, expdigs57, expdigs58, expdigs59, expdigs60, expdigs61, expdigs62, expdigs63, expdigs64, expdigs65, expdigs66, expdigs67, expdigs68, expdigs69, expdigs70, expdigs71, expdigs72, expdigs73, expdigs74, expdigs75, expdigs76, expdigs77, expdigs78, expdigs79, expdigs80, expdigs81, expdigs82, expdigs83, expdigs84, expdigs85, expdigs86, expdigs87, expdigs88, expdigs89, expdigs90, expdigs91, expdigs92, expdigs93, expdigs94, expdigs95, expdigs96, expdigs97, expdigs98, expdigs99, expdigs100, expdigs101, expdigs102, expdigs103, expdigs104, expdigs105, expdigs106, expdigs107, expdigs108, expdigs109, expdigs110, expdigs111, expdigs112, expdigs113, expdigs114, expdigs115, expdigs116, expdigs117, expdigs118, expdigs119, expdigs120, expdigs121, expdigs122, expdigs123, expdigs124, expdigs125, expdigs126, expdigs127, expdigs128, expdigs129, expdigs130, expdigs131, expdigs132, expdigs133, expdigs134, expdigs135, expdigs136, expdigs137, expdigs138, expdigs139, expdigs140, expdigs141, expdigs142, expdigs143, expdigs144, expdigs145, expdigs146, expdigs147 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4351 */   static final int[] nexpdigstable = { 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 13, 13, 13, 12, 12, 11, 11, 10, 10, 10, 9, 9, 8, 8, 8, 7, 7, 6, 6, 5, 5, 5, 4, 4, 3, 3, 3, 2, 2, 1, 1, 1, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4363 */   static final int[] binexpstable = { 90, 89, 89, 88, 88, 88, 87, 87, 86, 86, 86, 85, 85, 84, 84, 83, 83, 83, 82, 82, 81, 81, 81, 80, 80, 79, 79, 78, 78, 78, 77, 77, 76, 76, 76, 75, 75, 74, 74, 73, 73, 73, 72, 72, 71, 71, 71, 70, 70, 69, 69, 68, 68, 68, 67, 67, 66, 66, 66, 65, 65, 64, 64, 64, 63, 63, 62, 62, 61, 61, 61, 60, 60, 59, 59, 59, 58, 58, 57, 57, 56, 56, 56, 55, 55, 54, 54, 54, 53, 53, 52, 52, 51, 51, 51, 50, 50, 49, 49, 49, 48, 48, 47, 47, 46, 46, 46, 45, 45, 44, 44, 44, 43, 43, 42, 42, 41, 41, 41, 40, 40, 39, 39, 39, 38, 38, 37, 37, 37, 36, 36, 35, 35, 34, 34, 34, 33, 33, 32, 32, 32, 31, 31, 30, 30, 29, 29, 29 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void throwOverflow()
/*      */     throws SQLException
/*      */   {
/* 4380 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 26);
/* 4381 */     localSQLException.fillInStackTrace();
/* 4382 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   long updateChecksum(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4390 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1)
/*      */     {
/* 4392 */       paramLong = CRC64.updateChecksum(paramLong, NULL_DATA_BYTES, 0, NULL_DATA_BYTES.length);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 4398 */       int i = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/* 4399 */       int j = this.columnIndex + this.byteLength * paramInt;
/*      */       
/* 4401 */       paramLong = CRC64.updateChecksum(paramLong, this.rowSpaceByte, j + 1, i);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 4407 */     return paramLong;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 4412 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NumberCommonAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */