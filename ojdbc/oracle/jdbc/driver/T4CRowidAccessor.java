/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.sql.SQLException;
/*      */ import oracle.jdbc.internal.OracleStatement.SqlKind;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class T4CRowidAccessor
/*      */   extends RowidAccessor
/*      */ {
/*      */   T4CMAREngine mare;
/*      */   static final int maxLength = 128;
/*      */   
/*      */   T4CRowidAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
/*      */     throws SQLException
/*      */   {
/*   40 */     super(paramOracleStatement, paramInt1, paramShort, paramInt2, paramBoolean);
/*      */     
/*   42 */     this.mare = paramT4CMAREngine;
/*   43 */     this.defineType = 104;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   T4CRowidAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, int paramInt7, int paramInt8, T4CMAREngine paramT4CMAREngine)
/*      */     throws SQLException
/*      */   {
/*   53 */     super(paramOracleStatement, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort);
/*      */     
/*      */ 
/*   56 */     this.mare = paramT4CMAREngine;
/*   57 */     this.definedColumnType = paramInt7;
/*   58 */     this.definedColumnSize = paramInt8;
/*   59 */     this.defineType = 104;
/*      */   }
/*      */   
/*      */ 
/*      */   void processIndicator(int paramInt)
/*      */     throws IOException, SQLException
/*      */   {
/*   66 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   73 */       this.mare.unmarshalUB2();
/*   74 */       this.mare.unmarshalUB2();
/*      */     }
/*   76 */     else if (this.statement.connection.versionNumber < 9200)
/*      */     {
/*      */ 
/*      */ 
/*   80 */       this.mare.unmarshalSB2();
/*      */       
/*   82 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/*   83 */         this.mare.unmarshalSB2();
/*      */       }
/*   85 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
/*      */     {
/*   87 */       this.mare.processIndicator(paramInt <= 0, paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   95 */   final int[] meta = new int[1];
/*      */   static final int KGRD_EXTENDED_OBJECT = 6;
/*      */   static final int KGRD_EXTENDED_BLOCK = 6;
/*      */   static final int KGRD_EXTENDED_FILE = 3;
/*      */   static final int KGRD_EXTENDED_SLOT = 3;
/*      */   static final int kd4_ubridtype_physical = 1;
/*      */   static final int kd4_ubridtype_logical = 2;
/*      */   static final int kd4_ubridtype_remote = 3;
/*      */   static final int kd4_ubridtype_exttab = 4;
/*      */   static final int kd4_ubridtype_future2 = 5;
/*      */   static final int kd4_ubridtype_max = 5;
/*      */   static final int kd4_ubridlen_typeind = 1;
/*      */   
/*      */   boolean unmarshalOneRow() throws SQLException, IOException
/*      */   {
/*  110 */     if (this.isUseLess)
/*      */     {
/*  112 */       this.lastRowProcessed += 1;
/*      */       
/*  114 */       return false;
/*      */     }
/*      */     
/*      */     int m;
/*      */     int i2;
/*  119 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*  121 */       i = this.mare.unmarshalUB1();
/*  122 */       long l1 = 0L;
/*  123 */       m = 0;
/*  124 */       int n = 0;
/*  125 */       long l3 = 0L;
/*  126 */       i2 = 0;
/*      */       
/*      */ 
/*      */ 
/*  130 */       if (i > 0)
/*      */       {
/*  132 */         l1 = this.mare.unmarshalUB4();
/*  133 */         m = this.mare.unmarshalUB2();
/*  134 */         n = this.mare.unmarshalUB1();
/*  135 */         l3 = this.mare.unmarshalUB4();
/*  136 */         i2 = this.mare.unmarshalUB2();
/*      */       }
/*      */       
/*  139 */       processIndicator(this.meta[0]);
/*      */       
/*  141 */       this.lastRowProcessed += 1;
/*      */       
/*  143 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  147 */     int i = this.indicatorIndex + this.lastRowProcessed;
/*  148 */     int j = this.lengthIndex + this.lastRowProcessed;
/*      */     
/*  150 */     if (this.isNullByDescribe)
/*      */     {
/*  152 */       this.rowSpaceIndicator[i] = -1;
/*  153 */       this.rowSpaceIndicator[j] = 0;
/*  154 */       this.lastRowProcessed += 1;
/*      */       
/*  156 */       if (this.statement.connection.versionNumber < 9200) {
/*  157 */         processIndicator(0);
/*      */       }
/*  159 */       return false;
/*      */     }
/*      */     
/*  162 */     int k = this.columnIndex + this.lastRowProcessed * this.byteLength;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  167 */     if (this.describeType != 208)
/*      */     {
/*  169 */       m = this.mare.unmarshalUB1();
/*  170 */       long l2 = 0L;
/*  171 */       int i1 = 0;
/*  172 */       i2 = 0;
/*  173 */       long l4 = 0L;
/*  174 */       int i3 = 0;
/*      */       
/*      */ 
/*      */ 
/*  178 */       if (m > 0)
/*      */       {
/*  180 */         l2 = this.mare.unmarshalUB4();
/*  181 */         i1 = this.mare.unmarshalUB2();
/*  182 */         i2 = this.mare.unmarshalUB1();
/*  183 */         l4 = this.mare.unmarshalUB4();
/*  184 */         i3 = this.mare.unmarshalUB2();
/*      */       }
/*      */       
/*      */ 
/*  188 */       if ((l2 == 0L) && (i1 == 0) && (i2 == 0) && (l4 == 0L) && (i3 == 0))
/*      */       {
/*  190 */         this.meta[0] = 0;
/*      */       }
/*      */       else {
/*  193 */         long[] arrayOfLong = { l2, i1, l4, i3 };
/*      */         
/*      */ 
/*      */ 
/*  197 */         byte[] arrayOfByte2 = rowidToString(arrayOfLong);
/*  198 */         int i4 = 18;
/*      */         
/*  200 */         if (this.byteLength - 2 < 18) {
/*  201 */           i4 = this.byteLength - 2;
/*      */         }
/*  203 */         System.arraycopy(arrayOfByte2, 0, this.rowSpaceByte, k + 2, i4);
/*      */         
/*      */ 
/*  206 */         this.meta[0] = i4;
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*  211 */     else if ((this.meta[0] = (int)this.mare.unmarshalUB4()) > 0)
/*      */     {
/*  213 */       byte[] arrayOfByte1 = new byte[this.meta[0]];
/*  214 */       this.mare.unmarshalCLR(arrayOfByte1, 0, this.meta);
/*  215 */       this.meta[0] = kgrdub2c(arrayOfByte1, this.meta[0], 0, this.rowSpaceByte, k + 2);
/*      */     }
/*      */     
/*      */ 
/*  219 */     this.rowSpaceByte[k] = ((byte)((this.meta[0] & 0xFF00) >> 8));
/*  220 */     this.rowSpaceByte[(k + 1)] = ((byte)(this.meta[0] & 0xFF));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  228 */     processIndicator(this.meta[0]);
/*      */     
/*  230 */     if (this.meta[0] == 0)
/*      */     {
/*      */ 
/*      */ 
/*  234 */       this.rowSpaceIndicator[i] = -1;
/*  235 */       this.rowSpaceIndicator[j] = 0;
/*      */     }
/*      */     else
/*      */     {
/*  239 */       this.rowSpaceIndicator[j] = ((short)this.meta[0]);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  244 */       this.rowSpaceIndicator[i] = 0;
/*      */     }
/*      */     
/*  247 */     this.lastRowProcessed += 1;
/*      */     
/*  249 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   String getString(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  260 */     String str = null;
/*      */     
/*  262 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*  264 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  265 */       localSQLException.fillInStackTrace();
/*  266 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  271 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*  273 */       int i = this.columnIndex + this.byteLength * paramInt;
/*      */       
/*      */ 
/*      */ 
/*  277 */       int j = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/*      */       
/*  279 */       if ((this.describeType != 208) || (this.rowSpaceByte[i] == 1))
/*      */       {
/*      */ 
/*  282 */         str = new String(this.rowSpaceByte, i + 2, j);
/*      */         
/*  284 */         long[] arrayOfLong = stringToRowid(str.getBytes(), 0, str.length());
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  290 */         str = new String(rowidToString(arrayOfLong));
/*      */       }
/*      */       else
/*      */       {
/*  294 */         str = new String(this.rowSpaceByte, i + 2, j);
/*      */       }
/*      */     }
/*      */     
/*  298 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   Object getObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  306 */     if (this.definedColumnType == 0) {
/*  307 */       return super.getObject(paramInt);
/*      */     }
/*      */     
/*  310 */     Object localObject = null;
/*      */     SQLException localSQLException;
/*  312 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*  314 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  315 */       localSQLException.fillInStackTrace();
/*  316 */       throw localSQLException;
/*      */     }
/*      */     
/*  319 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*  321 */       switch (this.definedColumnType)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       case -15: 
/*      */       case -9: 
/*      */       case -1: 
/*      */       case 1: 
/*      */       case 12: 
/*  335 */         return getString(paramInt);
/*      */       
/*      */       case -8: 
/*  338 */         return getROWID(paramInt);
/*      */       }
/*      */       
/*      */       
/*  342 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/*  343 */       localSQLException.fillInStackTrace();
/*  344 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  349 */     return localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void copyRow()
/*      */     throws SQLException, IOException
/*      */   {
/*      */     int i;
/*      */     
/*      */ 
/*      */ 
/*  362 */     if (this.lastRowProcessed == 0) {
/*  363 */       i = this.statement.rowPrefetchInLastFetch - 1;
/*      */     } else {
/*  365 */       i = this.lastRowProcessed - 1;
/*      */     }
/*      */     
/*  368 */     int j = this.columnIndex + this.lastRowProcessed * this.byteLength;
/*  369 */     int k = this.columnIndex + i * this.byteLength;
/*  370 */     int m = this.indicatorIndex + this.lastRowProcessed;
/*  371 */     int n = this.indicatorIndex + i;
/*  372 */     int i1 = this.lengthIndex + this.lastRowProcessed;
/*  373 */     int i2 = this.lengthIndex + i;
/*  374 */     int i3 = this.rowSpaceIndicator[i2];
/*  375 */     int i4 = this.metaDataIndex + this.lastRowProcessed * 1;
/*      */     
/*  377 */     int i5 = this.metaDataIndex + i * 1;
/*      */     
/*      */ 
/*      */ 
/*  381 */     this.rowSpaceIndicator[i1] = ((short)i3);
/*  382 */     this.rowSpaceIndicator[m] = this.rowSpaceIndicator[n];
/*      */     
/*      */ 
/*      */ 
/*  386 */     System.arraycopy(this.rowSpaceByte, k, this.rowSpaceByte, j, i3 + 2);
/*      */     
/*      */ 
/*      */ 
/*  390 */     System.arraycopy(this.rowSpaceMetaData, i5, this.rowSpaceMetaData, i4, 1);
/*      */     
/*      */ 
/*      */ 
/*  394 */     this.lastRowProcessed += 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void saveDataFromOldDefineBuffers(byte[] paramArrayOfByte, char[] paramArrayOfChar, short[] paramArrayOfShort, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  407 */     int i = this.columnIndex + (paramInt2 - 1) * this.byteLength;
/*      */     
/*  409 */     int j = this.columnIndexLastRow + (paramInt1 - 1) * this.byteLength;
/*      */     
/*  411 */     int k = this.indicatorIndex + paramInt2 - 1;
/*  412 */     int m = this.indicatorIndexLastRow + paramInt1 - 1;
/*  413 */     int n = this.lengthIndex + paramInt2 - 1;
/*  414 */     int i1 = this.lengthIndexLastRow + paramInt1 - 1;
/*  415 */     int i2 = paramArrayOfShort[i1];
/*      */     
/*  417 */     this.rowSpaceIndicator[n] = ((short)i2);
/*  418 */     this.rowSpaceIndicator[k] = paramArrayOfShort[m];
/*      */     
/*      */ 
/*  421 */     if (i2 != 0)
/*      */     {
/*  423 */       System.arraycopy(paramArrayOfByte, j, this.rowSpaceByte, i, i2 + 2);
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
/*      */   static final byte[] rowidToString(long[] paramArrayOfLong)
/*      */   {
/*  437 */     long l1 = paramArrayOfLong[0];
/*      */     
/*      */ 
/*  440 */     long l2 = paramArrayOfLong[1];
/*      */     
/*      */ 
/*  443 */     long l3 = paramArrayOfLong[2];
/*      */     
/*      */ 
/*  446 */     long l4 = paramArrayOfLong[3];
/*      */     
/*  448 */     int i = 18;
/*      */     
/*      */ 
/*      */ 
/*  452 */     byte[] arrayOfByte = new byte[i];
/*  453 */     int j = 0;
/*      */     
/*  455 */     j = kgrd42b(arrayOfByte, l1, 6, j);
/*      */     
/*      */ 
/*  458 */     j = kgrd42b(arrayOfByte, l2, 3, j);
/*      */     
/*      */ 
/*  461 */     j = kgrd42b(arrayOfByte, l3, 6, j);
/*      */     
/*      */ 
/*  464 */     j = kgrd42b(arrayOfByte, l4, 3, j);
/*      */     
/*      */ 
/*      */ 
/*  468 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final long[] rcToRowid(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  484 */     int i = 18;
/*      */     
/*  486 */     if (paramInt2 != i)
/*      */     {
/*  488 */       throw new SQLException("Rowid size incorrect.");
/*      */     }
/*      */     
/*  491 */     long[] arrayOfLong = new long[3];
/*  492 */     String str = new String(paramArrayOfByte, paramInt1, paramInt2);
/*      */     
/*      */ 
/*  495 */     long l1 = Long.parseLong(str.substring(0, 8), 16);
/*  496 */     long l2 = Long.parseLong(str.substring(9, 13), 16);
/*  497 */     long l3 = Long.parseLong(str.substring(14, 8), 16);
/*      */     
/*  499 */     arrayOfLong[0] = l3;
/*  500 */     arrayOfLong[1] = l1;
/*  501 */     arrayOfLong[2] = l2;
/*      */     
/*  503 */     return arrayOfLong;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final void kgrdr2rc(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, byte[] paramArrayOfByte, int paramInt6)
/*      */     throws SQLException
/*      */   {
/*  523 */     paramInt6 = lmx42h(paramArrayOfByte, paramInt4, 8, paramInt6);
/*  524 */     paramArrayOfByte[(paramInt6++)] = 46;
/*      */     
/*  526 */     paramInt6 = lmx42h(paramArrayOfByte, paramInt5, 4, paramInt6);
/*  527 */     paramArrayOfByte[(paramInt6++)] = 46;
/*      */     
/*  529 */     paramInt6 = lmx42h(paramArrayOfByte, paramInt2, 4, paramInt6);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int lmx42h(byte[] paramArrayOfByte, long paramLong, int paramInt1, int paramInt2)
/*      */   {
/*  544 */     String str = Long.toHexString(paramLong).toUpperCase();
/*      */     
/*      */ 
/*  547 */     int i = paramInt1;
/*  548 */     int j = 0;
/*      */     
/*      */     do
/*      */     {
/*  552 */       if (j < str.length())
/*      */       {
/*  554 */         paramArrayOfByte[(paramInt2 + paramInt1 - 1)] = ((byte)str.charAt(str.length() - j - 1));
/*      */         
/*  556 */         j++;
/*      */       }
/*      */       else
/*      */       {
/*  560 */         paramArrayOfByte[(paramInt2 + paramInt1 - 1)] = 48;
/*      */       }
/*      */       
/*  563 */       paramInt1--; } while (paramInt1 > 0);
/*      */     
/*  565 */     return i + paramInt2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int kgrdc2ub(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  582 */     int i = getRowidType(paramArrayOfByte1, paramInt1);
/*  583 */     byte[] arrayOfByte1 = paramArrayOfByte2;
/*  584 */     int j = paramInt3 - 1;
/*  585 */     int k = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  590 */     byte[] arrayOfByte2 = kgrd_index_64;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  597 */     int i2 = 1 + (3 * ((paramInt3 - 1) / 4) + ((paramInt3 - 1) % 4 != 0 ? (paramInt3 - 1) % 4 - 1 : 0));
/*      */     
/*      */ 
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*  603 */     if (j == 0)
/*      */     {
/*      */ 
/*  606 */       localSQLException = DatabaseError.createSqlException(null, 132);
/*  607 */       localSQLException.fillInStackTrace();
/*  608 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  613 */     arrayOfByte1[(paramInt2 + 0)] = i;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  621 */     k = paramInt1 + 1;
/*  622 */     int i1 = 1;
/*      */     
/*  624 */     while (j > 0)
/*      */     {
/*      */ 
/*  627 */       if (j == 1)
/*      */       {
/*      */ 
/*  630 */         localSQLException = DatabaseError.createSqlException(null, 132);
/*  631 */         localSQLException.fillInStackTrace();
/*  632 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  637 */       int m = arrayOfByte2[paramArrayOfByte1[k]];
/*  638 */       if (m == -1)
/*      */       {
/*      */ 
/*  641 */         localSQLException = DatabaseError.createSqlException(null, 132);
/*  642 */         localSQLException.fillInStackTrace();
/*  643 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  647 */       k++;
/*  648 */       int n = arrayOfByte2[paramArrayOfByte1[k]];
/*  649 */       if (n == -1)
/*      */       {
/*      */ 
/*  652 */         localSQLException = DatabaseError.createSqlException(null, 132);
/*  653 */         localSQLException.fillInStackTrace();
/*  654 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  662 */       arrayOfByte1[(paramInt2 + i1)] = ((byte)((m & 0xFF) << 2 | (n & 0x30) >> 4));
/*      */       
/*      */ 
/*  665 */       if (j == 2) {
/*      */         break;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  672 */       i1++;
/*  673 */       m = n;
/*  674 */       k++;
/*  675 */       n = arrayOfByte2[paramArrayOfByte1[k]];
/*  676 */       if (n == -1)
/*      */       {
/*      */ 
/*  679 */         localSQLException = DatabaseError.createSqlException(null, 132);
/*  680 */         localSQLException.fillInStackTrace();
/*  681 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  685 */       arrayOfByte1[(paramInt2 + i1)] = ((byte)((m & 0xFF) << 4 | (n & 0x3C) >> 2));
/*      */       
/*      */ 
/*  688 */       if (j == 3) {
/*      */         break;
/*      */       }
/*      */       
/*      */ 
/*  693 */       i1++;
/*  694 */       m = n;
/*  695 */       k++;
/*  696 */       n = arrayOfByte2[paramArrayOfByte1[k]];
/*  697 */       if (n == -1)
/*      */       {
/*      */ 
/*  700 */         localSQLException = DatabaseError.createSqlException(null, 132);
/*  701 */         localSQLException.fillInStackTrace();
/*  702 */         throw localSQLException;
/*      */       }
/*      */       
/*  705 */       arrayOfByte1[(paramInt2 + i1)] = ((byte)((m & 0x3) << 6 | n));
/*      */       
/*      */ 
/*      */ 
/*  709 */       j -= 4;
/*  710 */       k++;
/*  711 */       i1++;
/*      */     }
/*      */     
/*  714 */     return i2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final long[] stringToRowid(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  724 */     int i = 18;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  729 */     if (paramInt2 != i)
/*      */     {
/*  731 */       throw new SQLException("Rowid size incorrect.");
/*      */     }
/*      */     
/*  734 */     long[] arrayOfLong = new long[4];
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  740 */       arrayOfLong[0] = kgrdb42(paramArrayOfByte, 6, paramInt1);
/*      */       
/*      */ 
/*  743 */       paramInt1 += 6;
/*      */       
/*      */ 
/*  746 */       arrayOfLong[1] = kgrdb42(paramArrayOfByte, 3, paramInt1);
/*      */       
/*      */ 
/*  749 */       paramInt1 += 3;
/*      */       
/*      */ 
/*  752 */       arrayOfLong[2] = kgrdb42(paramArrayOfByte, 6, paramInt1);
/*      */       
/*      */ 
/*  755 */       paramInt1 += 6;
/*      */       
/*      */ 
/*  758 */       arrayOfLong[3] = kgrdb42(paramArrayOfByte, 3, paramInt1);
/*      */       
/*      */ 
/*  761 */       paramInt1 += 3;
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*  765 */       arrayOfLong[0] = 0L;
/*  766 */       arrayOfLong[1] = 0L;
/*  767 */       arrayOfLong[2] = 0L;
/*  768 */       arrayOfLong[3] = 0L;
/*      */     }
/*      */     
/*  771 */     return arrayOfLong;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int kgrd42b(byte[] paramArrayOfByte, long paramLong, int paramInt1, int paramInt2)
/*      */   {
/*  783 */     int i = paramInt1;
/*  784 */     long l = paramLong;
/*  786 */     for (; 
/*  786 */         paramInt1 > 0; paramInt1--)
/*      */     {
/*  788 */       paramArrayOfByte[(paramInt2 + paramInt1 - 1)] = kgrd_basis_64[((int)l & 0x3F)];
/*  789 */       l = l >>> 6 & 0x3FFFFFF;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  795 */     return i + paramInt2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final long kgrdb42(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  806 */     long l = 0L;
/*      */     
/*  808 */     for (int i = 0; i < paramInt1; i++)
/*      */     {
/*  810 */       int j = paramArrayOfByte[(paramInt2 + i)];
/*      */       
/*  812 */       j = kgrd_index_64[j];
/*      */       
/*  814 */       if (j == -1) {
/*  815 */         throw new SQLException("Char data to rowid conversion failed.");
/*      */       }
/*  817 */       l <<= 6;
/*  818 */       l |= j;
/*      */     }
/*      */     
/*  821 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final void kgrdr2ec(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, byte[] paramArrayOfByte, int paramInt6)
/*      */     throws SQLException
/*      */   {
/*  834 */     paramInt6 = kgrd42b(paramInt1, paramArrayOfByte, paramInt6, 6);
/*  835 */     paramInt6 = kgrd42b(paramInt2, paramArrayOfByte, paramInt6, 3);
/*  836 */     paramInt6 = kgrd42b(paramInt4, paramArrayOfByte, paramInt6, 6);
/*  837 */     paramInt6 = kgrd42b(paramInt5, paramArrayOfByte, paramInt6, 3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int kgrd42b(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  848 */     int i = paramInt3;
/*  849 */     while (paramInt3 > 0)
/*      */     {
/*  851 */       paramInt3--;
/*  852 */       paramArrayOfByte[(paramInt2 + paramInt3)] = kgrd_basis_64[(paramInt1 & 0x3F)];
/*      */       
/*  854 */       paramInt1 >>= 6;
/*      */     }
/*      */     
/*  857 */     return paramInt2 + i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int kgrdub2c(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  870 */     int i = -1;
/*  871 */     int j = paramArrayOfByte1[paramInt2];
/*      */     int m;
/*  873 */     int n; int i1; int i2; int i3; if (j == 1)
/*      */     {
/*      */ 
/*      */ 
/*  877 */       int[] arrayOfInt = new int[paramArrayOfByte1.length];
/*  878 */       for (m = 0; m < paramArrayOfByte1.length; m++) { paramArrayOfByte1[m] &= 0xFF;
/*      */       }
/*  880 */       m = paramInt2 + 1;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  888 */       n = (((arrayOfInt[(m + 0)] << 8) + arrayOfInt[(m + 1)] << 8) + arrayOfInt[(m + 2)] << 8) + arrayOfInt[(m + 3)];
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  893 */       m = paramInt2 + 5;
/*      */       
/*  895 */       i1 = (arrayOfInt[(m + 0)] << 8) + arrayOfInt[(m + 1)];
/*  896 */       i2 = 0;
/*      */       
/*      */ 
/*  899 */       m = paramInt2 + 7;
/*  900 */       i3 = (((arrayOfInt[(m + 0)] << 8) + arrayOfInt[(m + 1)] << 8) + arrayOfInt[(m + 2)] << 8) + arrayOfInt[(m + 3)];
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  905 */       m = paramInt2 + 11;
/*      */       
/*  907 */       int i4 = (arrayOfInt[(m + 0)] << 8) + arrayOfInt[(m + 1)];
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  916 */       if (n == 0) {
/*  917 */         kgrdr2rc(n, i1, i2, i3, i4, paramArrayOfByte2, paramInt3);
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*  925 */         kgrdr2ec(n, i1, i2, i3, i4, paramArrayOfByte2, paramInt3);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  933 */       i = 18;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*  939 */       int k = 0;
/*  940 */       m = paramInt1 - 1;
/*  941 */       n = 4 * (paramInt1 / 3) + (paramInt1 % 3 == 0 ? paramInt1 % 3 + 1 : 0);
/*      */       
/*  943 */       i1 = 1 + n - 1;
/*      */       
/*  945 */       if (i1 != 0)
/*      */       {
/*  947 */         paramArrayOfByte2[(paramInt3 + 0)] = kgrd_indbyte_char[(j - 1)];
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  961 */         i2 = paramInt2 + 1;
/*      */         
/*  963 */         k = 1;
/*      */         
/*  965 */         i3 = 0;
/*      */         
/*  967 */         while (m > 0)
/*      */         {
/*  969 */           paramArrayOfByte2[(paramInt3 + k++)] = kgrd_basis_64[((paramArrayOfByte1[i2] & 0xFF) >> 2)];
/*      */           
/*      */ 
/*      */ 
/*  973 */           if (m == 1)
/*      */           {
/*      */ 
/*      */ 
/*  977 */             paramArrayOfByte2[(paramInt3 + k++)] = kgrd_basis_64[((paramArrayOfByte1[i2] & 0x3) << 4)];
/*      */             
/*      */ 
/*  980 */             break;
/*      */           }
/*      */           
/*      */ 
/*  984 */           i3 = (byte)(paramArrayOfByte1[(i2 + 1)] & 0xFF);
/*      */           
/*  986 */           paramArrayOfByte2[(paramInt3 + k++)] = kgrd_basis_64[((paramArrayOfByte1[i2] & 0x3) << 4 | (i3 & 0xF0) >> 4)];
/*      */           
/*      */ 
/*      */ 
/*  990 */           if (m == 2)
/*      */           {
/*  992 */             paramArrayOfByte2[(paramInt3 + k++)] = kgrd_basis_64[((i3 & 0xF) << 2)];
/*      */             
/*      */ 
/*  995 */             break;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1002 */           i2 += 2;
/* 1003 */           paramArrayOfByte2[(paramInt3 + k++)] = kgrd_basis_64[((i3 & 0xF) << 2 | (paramArrayOfByte1[i2] & 0xC0) >> 6)];
/*      */           
/*      */ 
/*      */ 
/* 1007 */           paramArrayOfByte2[(paramInt3 + k)] = kgrd_basis_64[(paramArrayOfByte1[i2] & 0x3F)];
/*      */           
/*      */ 
/*      */ 
/* 1011 */           m -= 3;
/* 1012 */           i2++;
/* 1013 */           k++;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1019 */       i = k;
/*      */     }
/*      */     
/* 1022 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */   static final boolean isUROWID(byte[] paramArrayOfByte, int paramInt)
/*      */   {
/* 1028 */     return getRowidType(paramArrayOfByte, paramInt) == 2;
/*      */   }
/*      */   
/*      */ 
/*      */   static final byte getRowidType(byte[] paramArrayOfByte, int paramInt)
/*      */   {
/* 1034 */     byte b = 5;
/* 1035 */     switch (paramArrayOfByte[paramInt])
/*      */     {
/*      */     case 65: 
/* 1038 */       b = 1;
/* 1039 */       break;
/*      */     
/*      */     case 42: 
/* 1042 */       b = 2;
/* 1043 */       break;
/*      */     
/*      */     case 45: 
/* 1046 */       b = 3;
/* 1047 */       break;
/*      */     
/*      */     case 40: 
/* 1050 */       b = 4;
/* 1051 */       break;
/*      */     
/*      */     case 41: 
/* 1054 */       b = 5;
/*      */     }
/*      */     
/*      */     
/* 1058 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1086 */   static final byte[] kgrd_indbyte_char = { 65, 42, 45, 40, 41 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1093 */   static final byte[] kgrd_basis_64 = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1108 */   static final byte[] kgrd_index_64 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1134 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CRowidAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */