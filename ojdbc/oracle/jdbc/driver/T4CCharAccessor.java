/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.sql.Date;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
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
/*      */ 
/*      */ class T4CCharAccessor
/*      */   extends CharAccessor
/*      */ {
/*      */   T4CMAREngine mare;
/*   57 */   boolean underlyingLong = false;
/*      */   
/*      */ 
/*      */   T4CCharAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
/*      */     throws SQLException
/*      */   {
/*   63 */     super(paramOracleStatement, paramInt1, paramShort, paramInt2, paramBoolean);
/*      */     
/*   65 */     this.mare = paramT4CMAREngine;
/*      */     
/*   67 */     calculateSizeTmpByteArray();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   T4CCharAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, int paramInt7, int paramInt8, int paramInt9, T4CMAREngine paramT4CMAREngine)
/*      */     throws SQLException
/*      */   {
/*   77 */     super(paramOracleStatement, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort);
/*      */     
/*      */ 
/*      */ 
/*   81 */     this.mare = paramT4CMAREngine;
/*   82 */     this.definedColumnType = paramInt8;
/*   83 */     this.definedColumnSize = paramInt9;
/*      */     
/*   85 */     calculateSizeTmpByteArray();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   91 */     this.oacmxl = paramInt7;
/*      */     
/*   93 */     if (this.oacmxl == -1)
/*      */     {
/*   95 */       this.underlyingLong = true;
/*   96 */       this.oacmxl = 4000;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void processIndicator(int paramInt)
/*      */     throws IOException, SQLException
/*      */   {
/*  105 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  112 */       this.mare.unmarshalUB2();
/*  113 */       this.mare.unmarshalUB2();
/*      */     }
/*  115 */     else if (this.statement.connection.versionNumber < 9200)
/*      */     {
/*      */ 
/*      */ 
/*  119 */       this.mare.unmarshalSB2();
/*      */       
/*  121 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/*  122 */         this.mare.unmarshalSB2();
/*      */       }
/*  124 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
/*      */     {
/*  126 */       this.mare.processIndicator(paramInt <= 0, paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  134 */   final int[] meta = new int[1];
/*  135 */   final int[] tmp = new int[1];
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean unmarshalOneRow()
/*      */     throws SQLException, IOException
/*      */   {
/*  144 */     if (this.isUseLess)
/*      */     {
/*  146 */       this.lastRowProcessed += 1;
/*      */       
/*  148 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  152 */     int i = this.indicatorIndex + this.lastRowProcessed;
/*  153 */     int j = this.lengthIndex + this.lastRowProcessed;
/*      */     
/*      */ 
/*      */ 
/*  157 */     byte[] arrayOfByte1 = this.statement.tmpByteArray;
/*  158 */     int k = this.columnIndex + this.lastRowProcessed * this.charLength;
/*      */     
/*      */ 
/*      */ 
/*  162 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/*  166 */       byte[] arrayOfByte2 = new byte['㺀'];
/*      */       
/*  168 */       this.mare.unmarshalCLR(arrayOfByte2, 0, this.meta);
/*  169 */       processIndicator(this.meta[0]);
/*      */       
/*  171 */       this.lastRowProcessed += 1;
/*      */       
/*  173 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  178 */     if (this.isNullByDescribe)
/*      */     {
/*  180 */       this.rowSpaceIndicator[i] = -1;
/*  181 */       this.rowSpaceIndicator[j] = 0;
/*  182 */       this.lastRowProcessed += 1;
/*      */       
/*  184 */       if (this.statement.connection.versionNumber < 9200) {
/*  185 */         processIndicator(0);
/*      */       }
/*  187 */       return false;
/*      */     }
/*      */     
/*  190 */     if (this.statement.maxFieldSize > 0) {
/*  191 */       this.mare.unmarshalCLR(arrayOfByte1, 0, this.meta, this.statement.maxFieldSize);
/*      */     } else {
/*  193 */       this.mare.unmarshalCLR(arrayOfByte1, 0, this.meta);
/*      */     }
/*  195 */     this.tmp[0] = this.meta[0];
/*      */     
/*  197 */     int m = 0;
/*      */     
/*  199 */     if (this.formOfUse == 2) {
/*  200 */       m = this.statement.connection.conversion.NCHARBytesToJavaChars(arrayOfByte1, 0, this.rowSpaceChar, k + 1, this.tmp, this.charLength - 1);
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
/*  214 */       m = this.statement.connection.conversion.CHARBytesToJavaChars(arrayOfByte1, 0, this.rowSpaceChar, k + 1, this.tmp, this.charLength - 1);
/*      */     }
/*      */     
/*      */ 
/*  218 */     this.rowSpaceChar[k] = ((char)(m * 2));
/*      */     
/*      */ 
/*      */ 
/*  222 */     processIndicator(this.meta[0]);
/*      */     
/*  224 */     if (this.meta[0] == 0)
/*      */     {
/*      */ 
/*      */ 
/*  228 */       this.rowSpaceIndicator[i] = -1;
/*  229 */       this.rowSpaceIndicator[j] = 0;
/*      */     }
/*      */     else
/*      */     {
/*  233 */       this.rowSpaceIndicator[j] = ((short)(this.meta[0] * 2));
/*      */       
/*      */ 
/*      */ 
/*  237 */       this.rowSpaceIndicator[i] = 0;
/*      */     }
/*      */     
/*  240 */     this.lastRowProcessed += 1;
/*      */     
/*  242 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void copyRow()
/*      */     throws SQLException, IOException
/*      */   {
/*      */     int i;
/*      */     
/*  252 */     if (this.lastRowProcessed == 0) {
/*  253 */       i = this.statement.rowPrefetchInLastFetch - 1;
/*      */     } else {
/*  255 */       i = this.lastRowProcessed - 1;
/*      */     }
/*      */     
/*  258 */     int j = this.columnIndex + this.lastRowProcessed * this.charLength;
/*  259 */     int k = this.columnIndex + i * this.charLength;
/*  260 */     int m = this.indicatorIndex + this.lastRowProcessed;
/*  261 */     int n = this.indicatorIndex + i;
/*  262 */     int i1 = this.lengthIndex + this.lastRowProcessed;
/*  263 */     int i2 = this.lengthIndex + i;
/*  264 */     int i3 = this.rowSpaceIndicator[i2];
/*  265 */     int i4 = this.metaDataIndex + this.lastRowProcessed * 1;
/*      */     
/*  267 */     int i5 = this.metaDataIndex + i * 1;
/*      */     
/*      */ 
/*      */ 
/*  271 */     this.rowSpaceIndicator[i1] = ((short)i3);
/*  272 */     this.rowSpaceIndicator[m] = this.rowSpaceIndicator[n];
/*      */     
/*      */ 
/*  275 */     if (!this.isNullByDescribe)
/*      */     {
/*  277 */       System.arraycopy(this.rowSpaceChar, k, this.rowSpaceChar, j, this.rowSpaceChar[k] / '\002' + 1);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  282 */     System.arraycopy(this.rowSpaceMetaData, i5, this.rowSpaceMetaData, i4, 1);
/*      */     
/*      */ 
/*  285 */     this.lastRowProcessed += 1;
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
/*  298 */     int i = this.columnIndex + (paramInt2 - 1) * this.charLength;
/*      */     
/*  300 */     int j = this.columnIndexLastRow + (paramInt1 - 1) * this.charLength;
/*      */     
/*  302 */     int k = this.indicatorIndex + paramInt2 - 1;
/*  303 */     int m = this.indicatorIndexLastRow + paramInt1 - 1;
/*  304 */     int n = this.lengthIndex + paramInt2 - 1;
/*  305 */     int i1 = this.lengthIndexLastRow + paramInt1 - 1;
/*  306 */     int i2 = paramArrayOfShort[i1];
/*      */     
/*  308 */     this.rowSpaceIndicator[n] = ((short)i2);
/*  309 */     this.rowSpaceIndicator[k] = paramArrayOfShort[m];
/*      */     
/*      */ 
/*  312 */     if (i2 != 0)
/*      */     {
/*  314 */       System.arraycopy(paramArrayOfChar, j, this.rowSpaceChar, i, paramArrayOfChar[j] / '\002' + 1);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  319 */       this.rowSpaceChar[i] = '\000';
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
/*      */   void calculateSizeTmpByteArray()
/*      */   {
/*      */     int i;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  342 */     if (this.formOfUse == 2)
/*      */     {
/*      */ 
/*      */ 
/*  346 */       i = (this.charLength - 1) * this.statement.connection.conversion.maxNCharSize;
/*      */     }
/*      */     else
/*      */     {
/*  350 */       i = (this.charLength - 1) * this.statement.connection.conversion.cMaxCharSize;
/*      */     }
/*      */     
/*  353 */     if (this.statement.sizeTmpByteArray < i) {
/*  354 */       this.statement.sizeTmpByteArray = i;
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
/*  367 */     String str = super.getString(paramInt);
/*      */     
/*  369 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
/*      */     {
/*  371 */       str = str.substring(0, this.definedColumnSize);
/*      */     }
/*  373 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   NUMBER getNUMBER(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  383 */     NUMBER localNUMBER = null;
/*      */     
/*  385 */     if (this.definedColumnType == 0) {
/*  386 */       localNUMBER = super.getNUMBER(paramInt);
/*      */     }
/*      */     else {
/*  389 */       String str = getString(paramInt);
/*  390 */       if (str != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  399 */         return T4CVarcharAccessor.StringToNUMBER(str);
/*      */       }
/*      */     }
/*      */     
/*  403 */     return localNUMBER;
/*      */   }
/*      */   
/*      */ 
/*      */   DATE getDATE(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  410 */     DATE localDATE = null;
/*      */     
/*  412 */     if (this.definedColumnType == 0) {
/*  413 */       localDATE = super.getDATE(paramInt);
/*      */     }
/*      */     else {
/*  416 */       Date localDate = getDate(paramInt);
/*  417 */       if (localDate != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  426 */         localDATE = new DATE(localDate);
/*      */       }
/*      */     }
/*      */     
/*  430 */     return localDATE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   TIMESTAMP getTIMESTAMP(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  438 */     TIMESTAMP localTIMESTAMP = null;
/*      */     
/*  440 */     if (this.definedColumnType == 0) {
/*  441 */       localTIMESTAMP = super.getTIMESTAMP(paramInt);
/*      */     }
/*      */     else {
/*  444 */       String str = getString(paramInt);
/*  445 */       if (str != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  455 */         int[] arrayOfInt = new int[1];
/*  456 */         Calendar localCalendar = T4CVarcharAccessor.DATEStringToCalendar(str, (String)this.statement.connection.sessionProperties.get("AUTH_NLS_LXCSTMPFM"), arrayOfInt);
/*      */         
/*      */ 
/*  459 */         Timestamp localTimestamp = new Timestamp(localCalendar.getTimeInMillis());
/*  460 */         localTimestamp.setNanos(arrayOfInt[0]);
/*  461 */         localTIMESTAMP = new TIMESTAMP(localTimestamp);
/*      */       }
/*      */     }
/*      */     
/*  465 */     return localTIMESTAMP;
/*      */   }
/*      */   
/*      */ 
/*      */   TIMESTAMPTZ getTIMESTAMPTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  472 */     TIMESTAMPTZ localTIMESTAMPTZ = null;
/*      */     
/*  474 */     if (this.definedColumnType == 0) {
/*  475 */       localTIMESTAMPTZ = super.getTIMESTAMPTZ(paramInt);
/*      */     }
/*      */     else {
/*  478 */       String str = getString(paramInt);
/*  479 */       if (str != null)
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
/*  490 */         int[] arrayOfInt = new int[1];
/*  491 */         Calendar localCalendar = T4CVarcharAccessor.DATEStringToCalendar(str, (String)this.statement.connection.sessionProperties.get("AUTH_NLS_LXCSTZNFM"), arrayOfInt);
/*      */         
/*      */ 
/*  494 */         Timestamp localTimestamp = new Timestamp(localCalendar.getTimeInMillis());
/*  495 */         localTimestamp.setNanos(arrayOfInt[0]);
/*  496 */         localTIMESTAMPTZ = new TIMESTAMPTZ(this.statement.connection, localTimestamp, localCalendar);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  502 */     return localTIMESTAMPTZ;
/*      */   }
/*      */   
/*      */ 
/*      */   TIMESTAMPLTZ getTIMESTAMPLTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  509 */     TIMESTAMPLTZ localTIMESTAMPLTZ = null;
/*      */     
/*  511 */     if (this.definedColumnType == 0) {
/*  512 */       localTIMESTAMPLTZ = super.getTIMESTAMPLTZ(paramInt);
/*      */     }
/*      */     else {
/*  515 */       String str = getString(paramInt);
/*  516 */       if (str != null)
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
/*  527 */         int[] arrayOfInt = new int[1];
/*  528 */         Calendar localCalendar = T4CVarcharAccessor.DATEStringToCalendar(str, (String)this.statement.connection.sessionProperties.get("AUTH_NLS_LXCSTZNFM"), arrayOfInt);
/*      */         
/*      */ 
/*  531 */         Timestamp localTimestamp = new Timestamp(localCalendar.getTimeInMillis());
/*  532 */         localTimestamp.setNanos(arrayOfInt[0]);
/*  533 */         localTIMESTAMPLTZ = new TIMESTAMPLTZ(this.statement.connection, localTimestamp, localCalendar);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  539 */     return localTIMESTAMPLTZ;
/*      */   }
/*      */   
/*      */ 
/*      */   RAW getRAW(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  546 */     RAW localRAW = null;
/*      */     
/*  548 */     if (this.definedColumnType == 0) {
/*  549 */       localRAW = super.getRAW(paramInt);
/*      */     }
/*      */     else {
/*  552 */       if (this.rowSpaceIndicator == null)
/*      */       {
/*      */ 
/*      */ 
/*  556 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  557 */         localSQLException.fillInStackTrace();
/*  558 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  564 */       if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */       {
/*  566 */         if ((this.definedColumnType == -2) || (this.definedColumnType == -3) || (this.definedColumnType == -4))
/*      */         {
/*      */ 
/*  569 */           localRAW = new RAW(getBytesFromHexChars(paramInt));
/*      */         } else {
/*  571 */           localRAW = new RAW(super.getBytes(paramInt));
/*      */         }
/*      */       }
/*      */     }
/*  575 */     return localRAW;
/*      */   }
/*      */   
/*      */ 
/*      */   Datum getOracleObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  582 */     if (this.definedColumnType == 0) {
/*  583 */       return super.getOracleObject(paramInt);
/*      */     }
/*      */     
/*  586 */     Datum localDatum = null;
/*      */     SQLException localSQLException;
/*  588 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*  590 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  591 */       localSQLException.fillInStackTrace();
/*  592 */       throw localSQLException;
/*      */     }
/*      */     
/*  595 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*  597 */       switch (this.definedColumnType)
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
/*  610 */         return super.getOracleObject(paramInt);
/*      */       
/*      */ 
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
/*  633 */         return getNUMBER(paramInt);
/*      */       
/*      */       case 91: 
/*  636 */         return getDATE(paramInt);
/*      */       
/*      */       case 92: 
/*  639 */         return getDATE(paramInt);
/*      */       
/*      */       case 93: 
/*  642 */         return getTIMESTAMP(paramInt);
/*      */       
/*      */       case -101: 
/*  645 */         return getTIMESTAMPTZ(paramInt);
/*      */       
/*      */       case -102: 
/*  648 */         return getTIMESTAMPLTZ(paramInt);
/*      */       
/*      */ 
/*      */ 
/*      */       case -4: 
/*      */       case -3: 
/*      */       case -2: 
/*  655 */         return getRAW(paramInt);
/*      */       
/*      */       case -8: 
/*  658 */         return getROWID(paramInt);
/*      */       }
/*      */       
/*      */       
/*  662 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/*  663 */       localSQLException.fillInStackTrace();
/*  664 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  670 */     return localDatum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   byte[] getBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  679 */     if (this.definedColumnType == 0) {
/*  680 */       return super.getBytes(paramInt);
/*      */     }
/*  682 */     Datum localDatum = getOracleObject(paramInt);
/*  683 */     if (localDatum != null) {
/*  684 */       return localDatum.shareBytes();
/*      */     }
/*  686 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   boolean getBoolean(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  694 */     boolean bool = false;
/*      */     
/*  696 */     if (this.definedColumnType == 0) {
/*  697 */       bool = super.getBoolean(paramInt);
/*      */     }
/*      */     else {
/*  700 */       bool = getNUMBER(paramInt).booleanValue();
/*      */     }
/*      */     
/*  703 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   byte getByte(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  711 */     byte b = 0;
/*      */     
/*  713 */     if (this.definedColumnType == 0) {
/*  714 */       b = super.getByte(paramInt);
/*      */     }
/*      */     else {
/*  717 */       NUMBER localNUMBER = getNUMBER(paramInt);
/*  718 */       if (localNUMBER != null) {
/*  719 */         b = localNUMBER.byteValue();
/*      */       }
/*      */     }
/*  722 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   int getInt(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  730 */     int i = 0;
/*      */     
/*  732 */     if (this.definedColumnType == 0) {
/*  733 */       i = super.getInt(paramInt);
/*      */     }
/*      */     else {
/*  736 */       NUMBER localNUMBER = getNUMBER(paramInt);
/*  737 */       if (localNUMBER != null) {
/*  738 */         i = localNUMBER.intValue();
/*      */       }
/*      */     }
/*  741 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   short getShort(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  749 */     short s = 0;
/*      */     
/*  751 */     if (this.definedColumnType == 0) {
/*  752 */       s = super.getShort(paramInt);
/*      */     }
/*      */     else {
/*  755 */       NUMBER localNUMBER = getNUMBER(paramInt);
/*  756 */       if (localNUMBER != null) {
/*  757 */         s = localNUMBER.shortValue();
/*      */       }
/*      */     }
/*  760 */     return s;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   long getLong(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  768 */     long l = 0L;
/*      */     
/*  770 */     if (this.definedColumnType == 0) {
/*  771 */       l = super.getLong(paramInt);
/*      */     }
/*      */     else {
/*  774 */       NUMBER localNUMBER = getNUMBER(paramInt);
/*  775 */       if (localNUMBER != null) {
/*  776 */         l = localNUMBER.longValue();
/*      */       }
/*      */     }
/*  779 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   float getFloat(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  787 */     float f = 0.0F;
/*      */     
/*  789 */     if (this.definedColumnType == 0) {
/*  790 */       f = super.getFloat(paramInt);
/*      */     }
/*      */     else {
/*  793 */       NUMBER localNUMBER = getNUMBER(paramInt);
/*  794 */       if (localNUMBER != null) {
/*  795 */         f = localNUMBER.floatValue();
/*      */       }
/*      */     }
/*  798 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   double getDouble(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  806 */     double d = 0.0D;
/*      */     
/*  808 */     if (this.definedColumnType == 0) {
/*  809 */       d = super.getDouble(paramInt);
/*      */     }
/*      */     else {
/*  812 */       NUMBER localNUMBER = getNUMBER(paramInt);
/*  813 */       if (localNUMBER != null) {
/*  814 */         d = localNUMBER.doubleValue();
/*      */       }
/*      */     }
/*  817 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   Date getDate(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  827 */     Date localDate = null;
/*      */     
/*  829 */     if (this.definedColumnType == 0) {
/*  830 */       localDate = super.getDate(paramInt);
/*      */     }
/*      */     else {
/*  833 */       String str = getString(paramInt);
/*  834 */       if (str != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  843 */         int[] arrayOfInt = new int[1];
/*      */         
/*  845 */         localDate = new Date(T4CVarcharAccessor.DATEStringToCalendar(str, (String)this.statement.connection.sessionProperties.get("AUTH_NLS_LXCDATEFM"), arrayOfInt).getTimeInMillis());
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  851 */     return localDate;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   Timestamp getTimestamp(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  859 */     Timestamp localTimestamp = null;
/*      */     
/*  861 */     if (this.definedColumnType == 0) {
/*  862 */       localTimestamp = super.getTimestamp(paramInt);
/*      */     }
/*      */     else {
/*  865 */       String str = getString(paramInt);
/*  866 */       if (str != null)
/*      */       {
/*  868 */         int[] arrayOfInt = new int[1];
/*  869 */         Calendar localCalendar = T4CVarcharAccessor.DATEStringToCalendar(str, (String)this.statement.connection.sessionProperties.get("AUTH_NLS_LXCSTMPFM"), arrayOfInt);
/*      */         
/*      */ 
/*  872 */         localTimestamp = new Timestamp(localCalendar.getTimeInMillis());
/*  873 */         localTimestamp.setNanos(arrayOfInt[0]);
/*      */       }
/*      */     }
/*      */     
/*  877 */     return localTimestamp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   Time getTime(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  885 */     Time localTime = null;
/*      */     
/*  887 */     if (this.definedColumnType == 0) {
/*  888 */       localTime = super.getTime(paramInt);
/*      */     }
/*      */     else {
/*  891 */       String str = getString(paramInt);
/*  892 */       if (str != null)
/*      */       {
/*  894 */         int[] arrayOfInt = new int[1];
/*  895 */         Calendar localCalendar = T4CVarcharAccessor.DATEStringToCalendar(str, (String)this.statement.connection.sessionProperties.get("AUTH_NLS_LXCSTZNFM"), arrayOfInt);
/*      */         
/*      */ 
/*  898 */         localTime = new Time(localCalendar.getTimeInMillis());
/*      */       }
/*      */     }
/*      */     
/*  902 */     return localTime;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   Object getObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  910 */     if (this.definedColumnType == 0) {
/*  911 */       return super.getObject(paramInt);
/*      */     }
/*      */     
/*  914 */     Object localObject = null;
/*      */     SQLException localSQLException;
/*  916 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*  918 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  919 */       localSQLException.fillInStackTrace();
/*  920 */       throw localSQLException;
/*      */     }
/*      */     
/*  923 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*  925 */       switch (this.definedColumnType)
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
/*  938 */         return getString(paramInt);
/*      */       
/*      */ 
/*      */       case 2: 
/*      */       case 3: 
/*  943 */         return getBigDecimal(paramInt);
/*      */       
/*      */       case 4: 
/*  946 */         return Integer.valueOf(getInt(paramInt));
/*      */       
/*      */       case -6: 
/*  949 */         return Byte.valueOf(getByte(paramInt));
/*      */       
/*      */       case 5: 
/*  952 */         return Short.valueOf(getShort(paramInt));
/*      */       
/*      */ 
/*      */       case -7: 
/*      */       case 16: 
/*  957 */         return Boolean.valueOf(getBoolean(paramInt));
/*      */       
/*      */       case -5: 
/*  960 */         return Long.valueOf(getLong(paramInt));
/*      */       
/*      */       case 7: 
/*  963 */         return Float.valueOf(getFloat(paramInt));
/*      */       
/*      */ 
/*      */       case 6: 
/*      */       case 8: 
/*  968 */         return Double.valueOf(getDouble(paramInt));
/*      */       
/*      */       case 91: 
/*  971 */         return getDate(paramInt);
/*      */       
/*      */       case 92: 
/*  974 */         return getTime(paramInt);
/*      */       
/*      */       case 93: 
/*  977 */         return getTimestamp(paramInt);
/*      */       
/*      */ 
/*      */ 
/*      */       case -4: 
/*      */       case -3: 
/*      */       case -2: 
/*  984 */         return getBytesFromHexChars(paramInt);
/*      */       }
/*      */       
/*      */       
/*  988 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/*  989 */       localSQLException.fillInStackTrace();
/*  990 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  996 */     return localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1001 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CCharAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */