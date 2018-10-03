/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.sql.Date;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.text.DateFormatSymbols;
/*      */ import java.util.Calendar;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.Properties;
/*      */ import oracle.jdbc.internal.OracleStatement.SqlKind;
/*      */ import oracle.sql.DATE;
/*      */ import oracle.sql.Datum;
/*      */ import oracle.sql.NUMBER;
/*      */ import oracle.sql.RAW;
/*      */ import oracle.sql.TIMESTAMP;
/*      */ import oracle.sql.TIMESTAMPLTZ;
/*      */ import oracle.sql.TIMESTAMPTZ;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class T4CVarcharAccessor
/*      */   extends VarcharAccessor
/*      */ {
/*      */   T4CMAREngine mare;
/*      */   static final int t4MaxLength = 4000;
/*      */   static final int t4CallMaxLength = 4001;
/*      */   static final int t4PlsqlMaxLength = 32766;
/*      */   static final int t4SqlMinLength = 32;
/*   64 */   boolean underlyingLong = false;
/*      */   
/*      */ 
/*      */   T4CVarcharAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
/*      */     throws SQLException
/*      */   {
/*   70 */     super(paramOracleStatement, paramInt1, paramShort, paramInt2, paramBoolean);
/*      */     
/*   72 */     this.mare = paramT4CMAREngine;
/*      */     
/*   74 */     calculateSizeTmpByteArray();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   T4CVarcharAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, int paramInt7, int paramInt8, int paramInt9, T4CMAREngine paramT4CMAREngine)
/*      */     throws SQLException
/*      */   {
/*   84 */     super(paramOracleStatement, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort);
/*      */     
/*      */ 
/*      */ 
/*   88 */     this.mare = paramT4CMAREngine;
/*   89 */     this.definedColumnType = paramInt8;
/*   90 */     this.definedColumnSize = paramInt9;
/*      */     
/*   92 */     calculateSizeTmpByteArray();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   98 */     this.oacmxl = paramInt7;
/*      */     
/*  100 */     if (this.oacmxl == -1)
/*      */     {
/*  102 */       this.underlyingLong = true;
/*  103 */       this.oacmxl = 4000;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void processIndicator(int paramInt)
/*      */     throws IOException, SQLException
/*      */   {
/*  112 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  119 */       this.mare.unmarshalUB2();
/*  120 */       this.mare.unmarshalUB2();
/*      */     }
/*  122 */     else if (this.statement.connection.versionNumber < 9200)
/*      */     {
/*      */ 
/*      */ 
/*  126 */       this.mare.unmarshalSB2();
/*      */       
/*  128 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/*  129 */         this.mare.unmarshalSB2();
/*      */       }
/*  131 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
/*      */     {
/*  133 */       this.mare.processIndicator(paramInt <= 0, paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  141 */   final int[] meta = new int[1];
/*  142 */   final int[] tmp = new int[1];
/*  143 */   final int[] escapeSequenceArr = new int[1];
/*  144 */   final boolean[] readHeaderArr = new boolean[1];
/*  145 */   final boolean[] readAsNonStreamArr = new boolean[1];
/*      */   static final int NONE = -1;
/*      */   static final int DAY = 1;
/*      */   static final int MM_MONTH = 2;
/*      */   static final int FULL_MONTH = 3;
/*      */   
/*      */   boolean unmarshalOneRow()
/*      */     throws SQLException, IOException
/*      */   {
/*  154 */     if (this.isUseLess)
/*      */     {
/*  156 */       this.lastRowProcessed += 1;
/*      */       
/*  158 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  162 */     int i = this.indicatorIndex + this.lastRowProcessed;
/*  163 */     int j = this.lengthIndex + this.lastRowProcessed;
/*      */     
/*      */ 
/*      */ 
/*  167 */     byte[] arrayOfByte1 = this.statement.tmpByteArray;
/*  168 */     int k = this.columnIndex + this.lastRowProcessed * this.charLength;
/*      */     
/*  170 */     if (!this.underlyingLong)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  175 */       if (this.rowSpaceIndicator == null)
/*      */       {
/*      */ 
/*      */ 
/*  179 */         byte[] arrayOfByte2 = new byte['㺀'];
/*      */         
/*  181 */         this.mare.unmarshalCLR(arrayOfByte2, 0, this.meta);
/*  182 */         processIndicator(this.meta[0]);
/*      */         
/*  184 */         this.lastRowProcessed += 1;
/*      */         
/*  186 */         return false;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  191 */       if (this.isNullByDescribe)
/*      */       {
/*  193 */         this.rowSpaceIndicator[i] = -1;
/*  194 */         this.rowSpaceIndicator[j] = 0;
/*  195 */         this.lastRowProcessed += 1;
/*      */         
/*  197 */         if (this.statement.connection.versionNumber < 9200) {
/*  198 */           processIndicator(0);
/*      */         }
/*  200 */         return false;
/*      */       }
/*      */       
/*  203 */       if (this.statement.maxFieldSize > 0) {
/*  204 */         this.mare.unmarshalCLR(arrayOfByte1, 0, this.meta, this.statement.maxFieldSize);
/*      */       } else {
/*  206 */         this.mare.unmarshalCLR(arrayOfByte1, 0, this.meta);
/*      */       }
/*      */       
/*      */     }
/*      */     else
/*      */     {
/*  212 */       this.escapeSequenceArr[0] = this.mare.unmarshalUB1();
/*      */       
/*      */ 
/*  215 */       if (this.mare.escapeSequenceNull(this.escapeSequenceArr[0]))
/*      */       {
/*      */ 
/*      */ 
/*  219 */         this.meta[0] = 0;
/*      */         
/*  221 */         this.mare.processIndicator(false, 0);
/*      */         
/*  223 */         m = this.mare.unmarshalUB2();
/*      */       }
/*      */       else
/*      */       {
/*  227 */         m = 0;
/*  228 */         int n = 0;
/*  229 */         byte[] arrayOfByte3 = arrayOfByte1;
/*  230 */         int i1 = 0;
/*      */         
/*  232 */         this.readHeaderArr[0] = true;
/*  233 */         this.readAsNonStreamArr[0] = false;
/*      */         
/*  235 */         while (m != -1)
/*      */         {
/*  237 */           if ((arrayOfByte3 == arrayOfByte1) && (n + 255 > arrayOfByte1.length))
/*      */           {
/*      */ 
/*      */ 
/*  241 */             arrayOfByte3 = new byte['ÿ'];
/*      */           }
/*  243 */           if (arrayOfByte3 == arrayOfByte1) {
/*  244 */             i1 = n;
/*      */           } else {
/*  246 */             i1 = 0;
/*      */           }
/*  248 */           m = T4CLongAccessor.readStreamFromWire(arrayOfByte3, i1, 255, this.escapeSequenceArr, this.readHeaderArr, this.readAsNonStreamArr, this.mare, ((T4CConnection)this.statement.connection).oer);
/*      */           
/*      */ 
/*      */ 
/*  252 */           if ((this.statement.connection.calculateChecksum) && (m != -1))
/*      */           {
/*      */ 
/*  255 */             long l = CRC64.updateChecksum(this.statement.checkSum, arrayOfByte3, i1, m);
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*  260 */             this.statement.checkSum = l;
/*      */           }
/*      */           
/*      */ 
/*  264 */           if (m != -1)
/*      */           {
/*  266 */             if (arrayOfByte3 == arrayOfByte1) {
/*  267 */               n += m;
/*  268 */             } else if (arrayOfByte1.length - n > 0)
/*      */             {
/*      */ 
/*      */ 
/*  272 */               int i2 = arrayOfByte1.length - n;
/*      */               
/*  274 */               System.arraycopy(arrayOfByte3, 0, arrayOfByte1, n, i2);
/*      */               
/*      */ 
/*  277 */               n += i2;
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*  282 */         if (arrayOfByte3 != arrayOfByte1) {
/*  283 */           arrayOfByte3 = null;
/*      */         }
/*  285 */         this.meta[0] = n;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  290 */     this.tmp[0] = this.meta[0];
/*      */     
/*  292 */     int m = 0;
/*      */     
/*  294 */     if (this.formOfUse == 2) {
/*  295 */       m = this.statement.connection.conversion.NCHARBytesToJavaChars(arrayOfByte1, 0, this.rowSpaceChar, k + 1, this.tmp, this.charLength - 1);
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
/*  308 */       m = this.statement.connection.conversion.CHARBytesToJavaChars(arrayOfByte1, 0, this.rowSpaceChar, k + 1, this.tmp, this.charLength - 1);
/*      */     }
/*      */     
/*      */ 
/*  312 */     this.rowSpaceChar[k] = ((char)(m * 2));
/*      */     
/*      */ 
/*      */ 
/*  316 */     if (!this.underlyingLong) {
/*  317 */       processIndicator(this.meta[0]);
/*      */     }
/*  319 */     if (this.meta[0] == 0)
/*      */     {
/*      */ 
/*      */ 
/*  323 */       this.rowSpaceIndicator[i] = -1;
/*  324 */       this.rowSpaceIndicator[j] = 0;
/*      */     }
/*      */     else
/*      */     {
/*  328 */       this.rowSpaceIndicator[j] = ((short)(this.meta[0] * 2));
/*      */       
/*      */ 
/*      */ 
/*  332 */       this.rowSpaceIndicator[i] = 0;
/*      */     }
/*      */     
/*  335 */     this.lastRowProcessed += 1;
/*      */     
/*  337 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void copyRow()
/*      */     throws SQLException, IOException
/*      */   {
/*      */     int i;
/*      */     
/*      */ 
/*  348 */     if (this.lastRowProcessed == 0) {
/*  349 */       i = this.statement.rowPrefetchInLastFetch - 1;
/*      */     } else {
/*  351 */       i = this.lastRowProcessed - 1;
/*      */     }
/*      */     
/*  354 */     int j = this.columnIndex + this.lastRowProcessed * this.charLength;
/*  355 */     int k = this.columnIndex + i * this.charLength;
/*  356 */     int m = this.indicatorIndex + this.lastRowProcessed;
/*  357 */     int n = this.indicatorIndex + i;
/*  358 */     int i1 = this.lengthIndex + this.lastRowProcessed;
/*  359 */     int i2 = this.lengthIndex + i;
/*  360 */     int i3 = this.rowSpaceIndicator[i2];
/*  361 */     int i4 = this.metaDataIndex + this.lastRowProcessed * 1;
/*      */     
/*  363 */     int i5 = this.metaDataIndex + i * 1;
/*      */     
/*      */ 
/*      */ 
/*  367 */     this.rowSpaceIndicator[i1] = ((short)i3);
/*  368 */     this.rowSpaceIndicator[m] = this.rowSpaceIndicator[n];
/*      */     
/*      */ 
/*  371 */     if (!this.isNullByDescribe)
/*      */     {
/*  373 */       System.arraycopy(this.rowSpaceChar, k, this.rowSpaceChar, j, this.rowSpaceChar[k] / '\002' + 1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  378 */     System.arraycopy(this.rowSpaceMetaData, i5, this.rowSpaceMetaData, i4, 1);
/*      */     
/*      */ 
/*      */ 
/*  382 */     this.lastRowProcessed += 1;
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
/*  395 */     int i = this.columnIndex + (paramInt2 - 1) * this.charLength;
/*      */     
/*  397 */     int j = this.columnIndexLastRow + (paramInt1 - 1) * this.charLength;
/*      */     
/*  399 */     int k = this.indicatorIndex + paramInt2 - 1;
/*  400 */     int m = this.indicatorIndexLastRow + paramInt1 - 1;
/*  401 */     int n = this.lengthIndex + paramInt2 - 1;
/*  402 */     int i1 = this.lengthIndexLastRow + paramInt1 - 1;
/*  403 */     int i2 = paramArrayOfShort[i1];
/*      */     
/*  405 */     this.rowSpaceIndicator[n] = ((short)i2);
/*  406 */     this.rowSpaceIndicator[k] = paramArrayOfShort[m];
/*      */     
/*      */ 
/*  409 */     if (i2 != 0)
/*      */     {
/*  411 */       System.arraycopy(paramArrayOfChar, j, this.rowSpaceChar, i, paramArrayOfChar[j] / '\002' + 1);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  416 */       this.rowSpaceChar[i] = '\000';
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static final int MON_MONTH = 4;
/*      */   
/*      */ 
/*      */   static final int YY_YEAR = 5;
/*      */   
/*      */ 
/*      */   static final int RR_YEAR = 6;
/*      */   
/*      */ 
/*      */   static final int HH_HOUR = 7;
/*      */   
/*      */ 
/*      */   void calculateSizeTmpByteArray()
/*      */   {
/*      */     int i;
/*      */     
/*      */ 
/*  439 */     if (this.formOfUse == 2)
/*      */     {
/*      */ 
/*      */ 
/*  443 */       i = (this.charLength - 1) * this.statement.connection.conversion.maxNCharSize;
/*      */     }
/*      */     else
/*      */     {
/*  447 */       i = (this.charLength - 1) * this.statement.connection.conversion.cMaxCharSize;
/*      */     }
/*      */     
/*  450 */     if (this.statement.sizeTmpByteArray < i) {
/*  451 */       this.statement.sizeTmpByteArray = i;
/*      */     }
/*      */   }
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
/*  464 */     String str = super.getString(paramInt);
/*      */     
/*  466 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
/*      */     {
/*  468 */       str = str.substring(0, this.definedColumnSize);
/*      */     }
/*  470 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   NUMBER getNUMBER(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  480 */     NUMBER localNUMBER = null;
/*      */     
/*  482 */     if (this.definedColumnType == 0) {
/*  483 */       localNUMBER = super.getNUMBER(paramInt);
/*      */     }
/*      */     else {
/*  486 */       String str = getString(paramInt);
/*  487 */       if (str != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  496 */         return StringToNUMBER(str);
/*      */       }
/*      */     }
/*      */     
/*  500 */     return localNUMBER;
/*      */   }
/*      */   
/*      */ 
/*      */   DATE getDATE(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  507 */     DATE localDATE = null;
/*      */     
/*  509 */     if (this.definedColumnType == 0) {
/*  510 */       localDATE = super.getDATE(paramInt);
/*      */     }
/*      */     else {
/*  513 */       Date localDate = getDate(paramInt);
/*  514 */       if (localDate != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  523 */         localDATE = new DATE(localDate);
/*      */       }
/*      */     }
/*      */     
/*  527 */     return localDATE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   TIMESTAMP getTIMESTAMP(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  535 */     TIMESTAMP localTIMESTAMP = null;
/*      */     
/*  537 */     if (this.definedColumnType == 0) {
/*  538 */       localTIMESTAMP = super.getTIMESTAMP(paramInt);
/*      */     }
/*      */     else {
/*  541 */       String str = getString(paramInt);
/*  542 */       if (str != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  552 */         int[] arrayOfInt = new int[1];
/*  553 */         Calendar localCalendar = DATEStringToCalendar(str, (String)this.statement.connection.sessionProperties.get("AUTH_NLS_LXCSTMPFM"), arrayOfInt);
/*      */         
/*      */ 
/*  556 */         Timestamp localTimestamp = new Timestamp(localCalendar.getTimeInMillis());
/*  557 */         localTimestamp.setNanos(arrayOfInt[0]);
/*  558 */         localTIMESTAMP = new TIMESTAMP(localTimestamp);
/*      */       }
/*      */     }
/*      */     
/*  562 */     return localTIMESTAMP;
/*      */   }
/*      */   
/*      */ 
/*      */   TIMESTAMPTZ getTIMESTAMPTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  569 */     TIMESTAMPTZ localTIMESTAMPTZ = null;
/*      */     
/*  571 */     if (this.definedColumnType == 0) {
/*  572 */       localTIMESTAMPTZ = super.getTIMESTAMPTZ(paramInt);
/*      */     }
/*      */     else {
/*  575 */       String str = getString(paramInt);
/*  576 */       if (str != null)
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
/*  587 */         int[] arrayOfInt = new int[1];
/*  588 */         Calendar localCalendar = DATEStringToCalendar(str, (String)this.statement.connection.sessionProperties.get("AUTH_NLS_LXCSTZNFM"), arrayOfInt);
/*      */         
/*      */ 
/*  591 */         Timestamp localTimestamp = new Timestamp(localCalendar.getTimeInMillis());
/*  592 */         localTimestamp.setNanos(arrayOfInt[0]);
/*  593 */         localTIMESTAMPTZ = new TIMESTAMPTZ(this.statement.connection, localTimestamp, localCalendar);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  599 */     return localTIMESTAMPTZ;
/*      */   }
/*      */   
/*      */ 
/*      */   TIMESTAMPLTZ getTIMESTAMPLTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  606 */     TIMESTAMPLTZ localTIMESTAMPLTZ = null;
/*      */     
/*  608 */     if (this.definedColumnType == 0) {
/*  609 */       localTIMESTAMPLTZ = super.getTIMESTAMPLTZ(paramInt);
/*      */     }
/*      */     else {
/*  612 */       String str = getString(paramInt);
/*  613 */       if (str != null)
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
/*  624 */         int[] arrayOfInt = new int[1];
/*  625 */         Calendar localCalendar = DATEStringToCalendar(str, (String)this.statement.connection.sessionProperties.get("AUTH_NLS_LXCSTZNFM"), arrayOfInt);
/*      */         
/*      */ 
/*  628 */         Timestamp localTimestamp = new Timestamp(localCalendar.getTimeInMillis());
/*  629 */         localTimestamp.setNanos(arrayOfInt[0]);
/*  630 */         localTIMESTAMPLTZ = new TIMESTAMPLTZ(this.statement.connection, localTimestamp, localCalendar);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  636 */     return localTIMESTAMPLTZ;
/*      */   }
/*      */   
/*      */ 
/*      */   RAW getRAW(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  643 */     RAW localRAW = null;
/*      */     
/*  645 */     if (this.definedColumnType == 0) {
/*  646 */       localRAW = super.getRAW(paramInt);
/*      */     }
/*      */     else {
/*  649 */       if (this.rowSpaceIndicator == null)
/*      */       {
/*      */ 
/*      */ 
/*  653 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  654 */         localSQLException.fillInStackTrace();
/*  655 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  661 */       if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */       {
/*  663 */         if ((this.definedColumnType == -2) || (this.definedColumnType == -3) || (this.definedColumnType == -4))
/*      */         {
/*      */ 
/*  666 */           localRAW = new RAW(getBytesFromHexChars(paramInt));
/*      */         } else {
/*  668 */           localRAW = new RAW(super.getBytes(paramInt));
/*      */         }
/*      */       }
/*      */     }
/*  672 */     return localRAW;
/*      */   }
/*      */   
/*      */ 
/*      */   Datum getOracleObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  679 */     if (this.definedColumnType == 0) {
/*  680 */       return super.getOracleObject(paramInt);
/*      */     }
/*      */     
/*  683 */     Datum localDatum = null;
/*      */     SQLException localSQLException;
/*  685 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*  687 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  688 */       localSQLException.fillInStackTrace();
/*  689 */       throw localSQLException;
/*      */     }
/*      */     
/*  692 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*  694 */       switch (this.definedColumnType)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       case -16: 
/*      */       case -15: 
/*      */       case -9: 
/*      */       case -1: 
/*      */       case 1: 
/*      */       case 12: 
/*  707 */         return super.getOracleObject(paramInt);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       case -7: 
/*      */       case -6: 
/*      */       case -5: 
/*      */       case 2: 
/*      */       case 3: 
/*      */       case 4: 
/*      */       case 5: 
/*      */       case 6: 
/*      */       case 7: 
/*      */       case 8: 
/*      */       case 16: 
/*  730 */         return getNUMBER(paramInt);
/*      */       
/*      */       case 91: 
/*  733 */         return getDATE(paramInt);
/*      */       
/*      */       case 92: 
/*  736 */         return getDATE(paramInt);
/*      */       
/*      */       case 93: 
/*  739 */         return getTIMESTAMP(paramInt);
/*      */       
/*      */       case -101: 
/*  742 */         return getTIMESTAMPTZ(paramInt);
/*      */       
/*      */       case -102: 
/*  745 */         return getTIMESTAMPLTZ(paramInt);
/*      */       
/*      */ 
/*      */ 
/*      */       case -4: 
/*      */       case -3: 
/*      */       case -2: 
/*  752 */         return getRAW(paramInt);
/*      */       
/*      */       case -8: 
/*  755 */         return getROWID(paramInt);
/*      */       }
/*      */       
/*      */       
/*  759 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/*  760 */       localSQLException.fillInStackTrace();
/*  761 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  767 */     return localDatum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   byte[] getBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  776 */     if (this.definedColumnType == 0) {
/*  777 */       return super.getBytes(paramInt);
/*      */     }
/*  779 */     Datum localDatum = getOracleObject(paramInt);
/*  780 */     if (localDatum != null) {
/*  781 */       return localDatum.shareBytes();
/*      */     }
/*  783 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   boolean getBoolean(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  791 */     boolean bool = false;
/*      */     
/*  793 */     if (this.definedColumnType == 0) {
/*  794 */       bool = super.getBoolean(paramInt);
/*      */     }
/*      */     else {
/*  797 */       bool = getNUMBER(paramInt).booleanValue();
/*      */     }
/*      */     
/*  800 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   byte getByte(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  808 */     byte b = 0;
/*      */     
/*  810 */     if (this.definedColumnType == 0) {
/*  811 */       b = super.getByte(paramInt);
/*      */     }
/*      */     else {
/*  814 */       NUMBER localNUMBER = getNUMBER(paramInt);
/*  815 */       if (localNUMBER != null) {
/*  816 */         b = localNUMBER.byteValue();
/*      */       }
/*      */     }
/*  819 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   int getInt(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  827 */     int i = 0;
/*      */     
/*  829 */     if (this.definedColumnType == 0) {
/*  830 */       i = super.getInt(paramInt);
/*      */     }
/*      */     else {
/*  833 */       NUMBER localNUMBER = getNUMBER(paramInt);
/*  834 */       if (localNUMBER != null) {
/*  835 */         i = localNUMBER.intValue();
/*      */       }
/*      */     }
/*  838 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   short getShort(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  846 */     short s = 0;
/*      */     
/*  848 */     if (this.definedColumnType == 0) {
/*  849 */       s = super.getShort(paramInt);
/*      */     }
/*      */     else {
/*  852 */       NUMBER localNUMBER = getNUMBER(paramInt);
/*  853 */       if (localNUMBER != null) {
/*  854 */         s = localNUMBER.shortValue();
/*      */       }
/*      */     }
/*  857 */     return s;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   long getLong(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  865 */     long l = 0L;
/*      */     
/*  867 */     if (this.definedColumnType == 0) {
/*  868 */       l = super.getLong(paramInt);
/*      */     }
/*      */     else {
/*  871 */       NUMBER localNUMBER = getNUMBER(paramInt);
/*  872 */       if (localNUMBER != null) {
/*  873 */         l = localNUMBER.longValue();
/*      */       }
/*      */     }
/*  876 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   float getFloat(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  884 */     float f = 0.0F;
/*      */     
/*  886 */     if (this.definedColumnType == 0) {
/*  887 */       f = super.getFloat(paramInt);
/*      */     }
/*      */     else {
/*  890 */       NUMBER localNUMBER = getNUMBER(paramInt);
/*  891 */       if (localNUMBER != null) {
/*  892 */         f = localNUMBER.floatValue();
/*      */       }
/*      */     }
/*  895 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   double getDouble(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  903 */     double d = 0.0D;
/*      */     
/*  905 */     if (this.definedColumnType == 0) {
/*  906 */       d = super.getDouble(paramInt);
/*      */     }
/*      */     else {
/*  909 */       NUMBER localNUMBER = getNUMBER(paramInt);
/*  910 */       if (localNUMBER != null) {
/*  911 */         d = localNUMBER.doubleValue();
/*      */       }
/*      */     }
/*  914 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   Date getDate(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  924 */     Date localDate = null;
/*      */     
/*  926 */     if (this.definedColumnType == 0) {
/*  927 */       localDate = super.getDate(paramInt);
/*      */     }
/*      */     else {
/*  930 */       String str = getString(paramInt);
/*  931 */       if (str != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  940 */         int[] arrayOfInt = new int[1];
/*      */         
/*  942 */         localDate = new Date(DATEStringToCalendar(str, (String)this.statement.connection.sessionProperties.get("AUTH_NLS_LXCDATEFM"), arrayOfInt).getTimeInMillis());
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  948 */     return localDate;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   Timestamp getTimestamp(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  956 */     Timestamp localTimestamp = null;
/*      */     
/*  958 */     if (this.definedColumnType == 0) {
/*  959 */       localTimestamp = super.getTimestamp(paramInt);
/*      */     }
/*      */     else {
/*  962 */       String str = getString(paramInt);
/*  963 */       if (str != null)
/*      */       {
/*  965 */         int[] arrayOfInt = new int[1];
/*  966 */         Calendar localCalendar = DATEStringToCalendar(str, (String)this.statement.connection.sessionProperties.get("AUTH_NLS_LXCSTMPFM"), arrayOfInt);
/*      */         
/*      */ 
/*  969 */         localTimestamp = new Timestamp(localCalendar.getTimeInMillis());
/*  970 */         localTimestamp.setNanos(arrayOfInt[0]);
/*      */       }
/*      */     }
/*      */     
/*  974 */     return localTimestamp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   Time getTime(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  982 */     Time localTime = null;
/*      */     
/*  984 */     if (this.definedColumnType == 0) {
/*  985 */       localTime = super.getTime(paramInt);
/*      */     }
/*      */     else {
/*  988 */       String str = getString(paramInt);
/*  989 */       if (str != null)
/*      */       {
/*  991 */         int[] arrayOfInt = new int[1];
/*  992 */         Calendar localCalendar = DATEStringToCalendar(str, (String)this.statement.connection.sessionProperties.get("AUTH_NLS_LXCSTZNFM"), arrayOfInt);
/*      */         
/*      */ 
/*  995 */         localTime = new Time(localCalendar.getTimeInMillis());
/*      */       }
/*      */     }
/*      */     
/*  999 */     return localTime;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Object getObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1008 */     if (this.definedColumnType == 0) {
/* 1009 */       return super.getObject(paramInt);
/*      */     }
/*      */     
/* 1012 */     Object localObject = null;
/*      */     SQLException localSQLException;
/* 1014 */     if (this.rowSpaceIndicator == null)
/*      */     {
/* 1016 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1017 */       localSQLException.fillInStackTrace();
/* 1018 */       throw localSQLException;
/*      */     }
/*      */     
/* 1021 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/* 1023 */       switch (this.definedColumnType)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       case -16: 
/*      */       case -15: 
/*      */       case -9: 
/*      */       case -1: 
/*      */       case 1: 
/*      */       case 12: 
/* 1036 */         return getString(paramInt);
/*      */       
/*      */ 
/*      */       case 2: 
/*      */       case 3: 
/* 1041 */         return getBigDecimal(paramInt);
/*      */       
/*      */       case 4: 
/* 1044 */         return Integer.valueOf(getInt(paramInt));
/*      */       
/*      */       case -6: 
/* 1047 */         return Byte.valueOf(getByte(paramInt));
/*      */       
/*      */       case 5: 
/* 1050 */         return Short.valueOf(getShort(paramInt));
/*      */       
/*      */ 
/*      */       case -7: 
/*      */       case 16: 
/* 1055 */         return Boolean.valueOf(getBoolean(paramInt));
/*      */       
/*      */       case -5: 
/* 1058 */         return Long.valueOf(getLong(paramInt));
/*      */       
/*      */       case 7: 
/* 1061 */         return Float.valueOf(getFloat(paramInt));
/*      */       
/*      */ 
/*      */       case 6: 
/*      */       case 8: 
/* 1066 */         return Double.valueOf(getDouble(paramInt));
/*      */       
/*      */       case 91: 
/* 1069 */         return getDate(paramInt);
/*      */       
/*      */       case 92: 
/* 1072 */         return getTime(paramInt);
/*      */       
/*      */       case 93: 
/* 1075 */         return getTimestamp(paramInt);
/*      */       
/*      */       case -101: 
/* 1078 */         return getTIMESTAMPTZ(paramInt);
/*      */       
/*      */       case -102: 
/* 1081 */         return getTIMESTAMPLTZ(paramInt);
/*      */       
/*      */ 
/*      */ 
/*      */       case -4: 
/*      */       case -3: 
/*      */       case -2: 
/* 1088 */         return getBytesFromHexChars(paramInt);
/*      */       
/*      */       case -8: 
/* 1091 */         return getROWID(paramInt);
/*      */       }
/*      */       
/*      */       
/* 1095 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 1096 */       localSQLException.fillInStackTrace();
/* 1097 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1103 */     return localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static final NUMBER StringToNUMBER(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1112 */     return new NUMBER(new BigDecimal(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static final int HH24_HOUR = 8;
/*      */   
/*      */ 
/*      */   static final int MINUTE = 9;
/*      */   
/*      */ 
/*      */   static final int SECOND = 10;
/*      */   
/*      */ 
/*      */   static final int NSECOND = 11;
/*      */   
/*      */ 
/*      */   static final int AM = 12;
/*      */   
/*      */ 
/*      */   static final int TZR = 13;
/*      */   
/*      */   static final int TZH = 14;
/*      */   
/*      */   static final int TZM = 15;
/*      */   
/*      */   static final Calendar DATEStringToCalendar(String paramString1, String paramString2, int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/* 1141 */     char[] arrayOfChar = (paramString2 + " ").toCharArray();
/* 1142 */     paramString1 = paramString1 + " ";
/*      */     
/*      */ 
/* 1145 */     int i = Math.min(paramString1.length(), arrayOfChar.length);
/*      */     
/* 1147 */     int j = -1;
/* 1148 */     int k = -1;
/*      */     
/* 1150 */     int m = 0;
/* 1151 */     int n = 0;
/*      */     
/* 1153 */     String str1 = 0;
/* 1154 */     int i1 = 0;
/*      */     
/* 1156 */     int i2 = 0;
/* 1157 */     int i3 = 0;
/* 1158 */     int i4 = 0;
/*      */     
/* 1160 */     int i5 = 0;
/* 1161 */     int i6 = 0;
/* 1162 */     int i7 = 0;
/* 1163 */     int i8 = 0;
/*      */     
/* 1165 */     String str2 = null;
/* 1166 */     String str3 = null;
/*      */     
/* 1168 */     int i9 = 0;
/*      */     
/*      */ 
/* 1171 */     String[] arrayOfString1 = null;
/* 1172 */     String[] arrayOfString2 = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1190 */     for (int i10 = 0; i10 < i; i10++)
/*      */     {
/* 1192 */       switch (arrayOfChar[i10])
/*      */       {
/*      */       case 'R': 
/*      */       case 'r': 
/* 1196 */         if (j != 6)
/*      */         {
/* 1198 */           j = 6;
/* 1199 */           m = i10;
/*      */         }
/*      */         
/*      */         break;
/*      */       case 'Y': 
/*      */       case 'y': 
/* 1205 */         if (j != 5)
/*      */         {
/* 1207 */           j = 5;
/* 1208 */           m = i10;
/*      */         }
/*      */         
/*      */         break;
/*      */       case 'D': 
/*      */       case 'd': 
/* 1214 */         if (j != 1)
/*      */         {
/* 1216 */           j = 1;
/* 1217 */           m = i10;
/*      */         }
/*      */         
/*      */         break;
/*      */       case 'M': 
/*      */       case 'm': 
/* 1223 */         if ((j != 2) || (j != 4) || (j != 3) || (j != 9))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1229 */           m = i10;
/*      */           
/* 1231 */           if ((i10 + 4 < i) && ((arrayOfChar[(i10 + 1)] == 'O') || (arrayOfChar[(i10 + 1)] == 'o')) && ((arrayOfChar[(i10 + 2)] == 'N') || (arrayOfChar[(i10 + 2)] == 'n')) && ((arrayOfChar[(i10 + 3)] == 'T') || (arrayOfChar[(i10 + 3)] == 't')) && ((arrayOfChar[(i10 + 4)] == 'H') || (arrayOfChar[(i10 + 4)] == 'h')))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1237 */             j = 3;
/* 1238 */             i10 += 4;
/* 1239 */           } else if ((i10 + 2 < i) && ((arrayOfChar[(i10 + 1)] == 'O') || (arrayOfChar[(i10 + 1)] == 'o')) && ((arrayOfChar[(i10 + 2)] == 'N') || (arrayOfChar[(i10 + 2)] == 'n')))
/*      */           {
/*      */ 
/*      */ 
/* 1243 */             j = 4;
/* 1244 */             i10 += 2;
/* 1245 */           } else if ((i10 + 1 < i) && ((arrayOfChar[(i10 + 1)] == 'M') || (arrayOfChar[(i10 + 1)] == 'm')))
/*      */           {
/*      */ 
/* 1248 */             j = 2;
/* 1249 */             i10++;
/* 1250 */           } else if ((i10 + 1 < i) && ((arrayOfChar[(i10 + 1)] == 'I') || (arrayOfChar[(i10 + 1)] == 'i')))
/*      */           {
/*      */ 
/* 1253 */             j = 9;
/* 1254 */             i10++;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */         break;
/*      */       case 'H': 
/*      */       case 'h': 
/* 1263 */         if (j != 7)
/*      */         {
/* 1265 */           j = 7;
/* 1266 */           m = i10;
/* 1267 */         } else if ((i10 + 2 < i) && ((arrayOfChar[(i10 + 1)] == '2') || (arrayOfChar[(i10 + 4)] == '4')))
/*      */         {
/*      */ 
/* 1270 */           j = 8;
/* 1271 */           i10 += 2;
/*      */         }
/*      */         
/*      */         break;
/*      */       case 'S': 
/*      */       case 's': 
/* 1277 */         if ((i10 + 1 < i) && ((arrayOfChar[(i10 + 1)] == 'S') || (arrayOfChar[(i10 + 1)] == 's')))
/*      */         {
/*      */ 
/* 1280 */           j = 10;
/* 1281 */           m = i10;
/* 1282 */           i10++;
/*      */         }
/*      */         
/*      */ 
/*      */         break;
/*      */       case 'F': 
/*      */       case 'f': 
/* 1289 */         if (j != 11)
/*      */         {
/* 1291 */           j = 11;
/* 1292 */           m = i10;
/*      */         }
/*      */         
/*      */         break;
/*      */       case 'A': 
/*      */       case 'a': 
/* 1298 */         if ((i10 + 1 < i) && ((arrayOfChar[(i10 + 1)] == 'M') || (arrayOfChar[(i10 + 1)] == 'm')))
/*      */         {
/*      */ 
/* 1301 */           j = 12;
/* 1302 */           m = i10;
/* 1303 */           i10++;
/*      */         }
/*      */         
/*      */         break;
/*      */       case 'T': 
/*      */       case 't': 
/* 1309 */         if ((i10 + 2 < i) && ((arrayOfChar[(i10 + 1)] == 'Z') || (arrayOfChar[(i10 + 1)] == 'z')) && ((arrayOfChar[(i10 + 2)] == 'R') || (arrayOfChar[(i10 + 2)] == 'r')))
/*      */         {
/*      */ 
/*      */ 
/* 1313 */           j = 13;
/* 1314 */           m = i10;
/* 1315 */           i10 += 2;
/*      */         }
/*      */         break;
/*      */       case 'B': case 'C': case 'E': case 'G': case 'I': case 'J': case 'K': case 'L': case 'N': case 'O': 
/*      */       case 'P': case 'Q': case 'U': case 'V': case 'W': case 'X': case 'Z': case '[': case '\\': case ']': 
/*      */       case '^': case '_': case '`': case 'b': case 'c': case 'e': case 'g': case 'i': case 'j': case 'k': 
/*      */       case 'l': case 'n': case 'o': case 'p': case 'q': case 'u': case 'v': case 'w': case 'x': default: 
/* 1322 */         i9 = 1;
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
/* 1333 */       if ((i9 != 0) && (j != -1))
/*      */       {
/* 1335 */         int i11 = i10 - m;
/* 1336 */         int i12 = m - n;
/* 1337 */         str1 = i1 + i12;
/*      */         
/* 1339 */         i1 = str1 + i11;
/* 1340 */         Object localObject; String str4; int i15; switch (j)
/*      */         {
/*      */         case 1: 
/* 1343 */           i2 = Integer.parseInt(paramString1.substring(str1, i1));
/* 1344 */           break;
/*      */         
/*      */         case 2: 
/* 1347 */           i3 = Integer.parseInt(paramString1.substring(str1, i1));
/* 1348 */           break;
/*      */         
/*      */ 
/*      */ 
/*      */         case 3: 
/* 1353 */           int i16 = str1;
/* 1354 */           i1 = str1;
/* 1355 */           for (i16 = str1; i16 < paramString1.length(); i16++)
/* 1356 */             if (paramString1.charAt(i16) == arrayOfChar[i10])
/*      */               break;
/* 1358 */           i1 = i16;
/*      */           
/* 1360 */           localObject = null;
/* 1361 */           if (i1 != str1) {
/* 1362 */             localObject = paramString1.substring(str1, i1);
/*      */             
/*      */ 
/*      */ 
/* 1366 */             localObject = ((String)localObject).trim();
/*      */             
/* 1368 */             if (arrayOfString2 == null)
/* 1369 */               arrayOfString2 = new DateFormatSymbols().getMonths();
/* 1370 */             for (i3 = 0; i3 < arrayOfString2.length; i3++) {
/* 1371 */               if (((String)localObject).equalsIgnoreCase(arrayOfString2[i3]))
/*      */                 break;
/*      */             }
/* 1374 */             if (i3 >= 12)
/*      */             {
/* 1376 */               SQLException localSQLException = DatabaseError.createSqlException(null, 59);
/* 1377 */               localSQLException.fillInStackTrace();
/* 1378 */               throw localSQLException;
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1387 */           break;
/*      */         
/*      */         case 4: 
/* 1390 */           int i13 = str1;
/* 1391 */           i1 = str1;
/* 1392 */           for (int i14 = str1; i14 < paramString1.length(); i14++)
/* 1393 */             if (paramString1.charAt(i14) == arrayOfChar[i10])
/*      */               break;
/* 1395 */           i1 = i14;
/*      */           
/* 1397 */           str4 = null;
/* 1398 */           if (i1 != str1) {
/* 1399 */             str4 = paramString1.substring(str1, i1);
/*      */             
/*      */ 
/*      */ 
/* 1403 */             str4 = str4.trim();
/*      */             
/* 1405 */             if (arrayOfString1 == null)
/* 1406 */               arrayOfString1 = new DateFormatSymbols().getShortMonths();
/* 1407 */             for (i3 = 0; i3 < arrayOfString1.length; i3++) {
/* 1408 */               if (str4.equalsIgnoreCase(arrayOfString1[i3]))
/*      */                 break;
/*      */             }
/* 1411 */             if (i3 >= 12)
/*      */             {
/* 1413 */               localObject = DatabaseError.createSqlException(null, 59);
/* 1414 */               ((SQLException)localObject).fillInStackTrace();
/* 1415 */               throw ((Throwable)localObject);
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1424 */           break;
/*      */         
/*      */         case 5: 
/* 1427 */           i4 = Integer.parseInt(paramString1.substring(str1, i1));
/*      */           
/*      */ 
/*      */ 
/* 1431 */           if (i11 == 2) {
/* 1432 */             i4 += 2000;
/*      */           }
/*      */           break;
/*      */         case 6: 
/* 1436 */           i4 = Integer.parseInt(paramString1.substring(str1, i1));
/*      */           
/*      */ 
/*      */ 
/* 1440 */           if ((i11 == 2) && (i4 < 50)) {
/* 1441 */             i4 += 2000;
/*      */           } else
/* 1443 */             i4 += 1900;
/* 1444 */           break;
/*      */         
/*      */ 
/*      */ 
/*      */         case 7: 
/*      */         case 8: 
/* 1450 */           i1 = str1 + 2;
/* 1451 */           i5 = Integer.parseInt(paramString1.substring(str1, i1));
/* 1452 */           break;
/*      */         
/*      */         case 9: 
/* 1455 */           i6 = Integer.parseInt(paramString1.substring(str1, i1));
/* 1456 */           break;
/*      */         
/*      */         case 10: 
/* 1459 */           i7 = Integer.parseInt(paramString1.substring(str1, i1));
/* 1460 */           break;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         case 11: 
/* 1469 */           str4 = str1;
/* 1470 */           i1 = str1;
/* 1471 */           for (str4 = str1; str4 < paramString1.length(); str4++)
/* 1472 */             if (((i15 = paramString1.charAt(str4)) < '0') || (i15 > 57))
/*      */               break;
/* 1474 */           i1 += str4 - str1;
/*      */           
/* 1476 */           if (i1 != str1) {
/* 1477 */             i8 = Integer.parseInt(paramString1.substring(str1, i1));
/*      */           }
/*      */           break;
/*      */         case 12: 
/* 1481 */           if (i1 > 0) {
/* 1482 */             str2 = paramString1.substring(str1, i1);
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           break;
/*      */         case 13: 
/* 1492 */           int i17 = str1;
/* 1493 */           i1 = str1;
/* 1494 */           for (int i18 = str1; i18 < paramString1.length(); i18++) {
/* 1495 */             if ((((i15 = paramString1.charAt(i18)) < '0') || (i15 > 57)) && ((i15 < 97) || (i15 > 122)) && ((i15 < 65) || (i15 > 90))) {
/*      */               break;
/*      */             }
/*      */             
/*      */ 
/* 1500 */             i1 = i18;
/*      */           }
/* 1502 */           if (i1 != str1) {
/* 1503 */             str3 = paramString1.substring(str1, i1);
/*      */           }
/*      */           
/*      */           break;
/*      */         default: 
/* 1508 */           System.out.println("\n\n\n             ***** ERROR(1) ****\n");
/*      */         }
/*      */         
/*      */         
/* 1512 */         n = i10;
/* 1513 */         j = -1;
/* 1514 */         i9 = 0;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1521 */     GregorianCalendar localGregorianCalendar = new GregorianCalendar(i4, i3, i2, i5, i6, i7);
/* 1522 */     if (str2 != null) {
/* 1523 */       localGregorianCalendar.set(9, str2.equalsIgnoreCase("AM") ? 0 : 1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1528 */     if ((str3 == null) || 
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1534 */       (i8 != 0)) {
/* 1535 */       paramArrayOfInt[0] = i8;
/*      */     }
/* 1537 */     return localGregorianCalendar;
/*      */   }
/*      */   
/*      */ 
/* 1541 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CVarcharAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */