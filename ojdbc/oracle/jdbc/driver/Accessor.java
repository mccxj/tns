/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.URL;
/*      */ import java.sql.Date;
/*      */ import java.sql.NClob;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLXML;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.Map;
/*      */ import oracle.jdbc.OracleDataFactory;
/*      */ import oracle.jdbc.OracleResultSet.AuthorizationIndicator;
/*      */ import oracle.jdbc.OracleResultSetMetaData.SecurityAttribute;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.oracore.OracleType;
/*      */ import oracle.sql.ARRAY;
/*      */ import oracle.sql.BFILE;
/*      */ import oracle.sql.BLOB;
/*      */ import oracle.sql.CHAR;
/*      */ import oracle.sql.CLOB;
/*      */ import oracle.sql.CustomDatum;
/*      */ import oracle.sql.CustomDatumFactory;
/*      */ import oracle.sql.DATE;
/*      */ import oracle.sql.Datum;
/*      */ import oracle.sql.INTERVALDS;
/*      */ import oracle.sql.INTERVALYM;
/*      */ import oracle.sql.NUMBER;
/*      */ import oracle.sql.OPAQUE;
/*      */ import oracle.sql.ORAData;
/*      */ import oracle.sql.ORADataFactory;
/*      */ import oracle.sql.RAW;
/*      */ import oracle.sql.REF;
/*      */ import oracle.sql.ROWID;
/*      */ import oracle.sql.STRUCT;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class Accessor
/*      */ {
/*      */   static final int FIXED_CHAR = 999;
/*      */   static final int CHAR = 96;
/*      */   static final int VARCHAR = 1;
/*      */   static final int VCS = 9;
/*      */   static final int LONG = 8;
/*      */   static final int NUMBER = 2;
/*      */   static final int VARNUM = 6;
/*      */   static final int BINARY_FLOAT = 100;
/*      */   static final int BINARY_DOUBLE = 101;
/*      */   static final int RAW = 23;
/*      */   static final int VBI = 15;
/*      */   static final int LONG_RAW = 24;
/*      */   static final int ROWID = 104;
/*      */   static final int RESULT_SET = 102;
/*      */   static final int RSET = 116;
/*      */   static final int DATE = 12;
/*      */   static final int BLOB = 113;
/*      */   static final int CLOB = 112;
/*      */   static final int BFILE = 114;
/*      */   static final int NAMED_TYPE = 109;
/*      */   static final int REF_TYPE = 111;
/*      */   static final int TIMESTAMP = 180;
/*      */   static final int TIMESTAMPTZ = 181;
/*      */   static final int TIMESTAMPLTZ = 231;
/*      */   static final int INTERVALYM = 182;
/*      */   static final int INTERVALDS = 183;
/*      */   static final int UROWID = 208;
/*      */   static final int PLSQL_INDEX_TABLE = 998;
/*      */   static final int T2S_OVERLONG_RAW = 997;
/*      */   static final int SET_CHAR_BYTES = 996;
/*      */   static final int NULL_TYPE = 995;
/*      */   static final int DML_RETURN_PARAM = 994;
/*      */   static final int ONLY_FORM_USABLE = 0;
/*      */   static final int NOT_USABLE = 1;
/*      */   static final int NO_NEED_TO_PREPARE = 2;
/*      */   static final int NEED_TO_PREPARE = 3;
/*      */   OracleStatement statement;
/*      */   boolean outBind;
/*      */   int internalType;
/*      */   int internalTypeMaxLength;
/*  138 */   boolean isStream = false;
/*      */   
/*      */ 
/*  141 */   boolean isColumnNumberAware = false;
/*      */   
/*  143 */   short formOfUse = 2;
/*      */   
/*      */   OracleType internalOtype;
/*      */   
/*      */   int externalType;
/*      */   
/*      */   String internalTypeName;
/*      */   
/*      */   String columnName;
/*      */   
/*      */   int describeType;
/*      */   
/*      */   int describeMaxLength;
/*      */   
/*      */   boolean nullable;
/*      */   
/*      */   int precision;
/*      */   
/*      */   int scale;
/*      */   
/*      */   int flags;
/*      */   
/*      */   int contflag;
/*      */   
/*      */   int total_elems;
/*      */   
/*      */   OracleType describeOtype;
/*      */   String describeTypeName;
/*  171 */   int definedColumnType = 0;
/*  172 */   int definedColumnSize = 0;
/*  173 */   int oacmxl = 0;
/*      */   
/*      */ 
/*  176 */   short udskpos = -1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  186 */   int lobPrefetchSizeForThisColumn = -1;
/*      */   
/*      */ 
/*  189 */   long[] prefetchedLobSize = null;
/*      */   
/*  191 */   int[] prefetchedLobChunkSize = null;
/*      */   
/*      */ 
/*  194 */   byte[] prefetchedClobFormOfUse = null;
/*      */   
/*      */ 
/*      */ 
/*  198 */   byte[][] prefetchedLobData = (byte[][])null;
/*  199 */   char[][] prefetchedLobCharData = (char[][])null;
/*      */   
/*      */ 
/*  202 */   int[] prefetchedLobDataL = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   OracleResultSetMetaData.SecurityAttribute securityAttribute;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  213 */   byte[] rowSpaceByte = null;
/*  214 */   char[] rowSpaceChar = null;
/*  215 */   short[] rowSpaceIndicator = null;
/*      */   
/*      */   static final byte DATA_UNAUTHORIZED = 1;
/*      */   
/*  219 */   byte[] rowSpaceMetaData = null;
/*      */   
/*  221 */   int columnIndex = 0;
/*  222 */   int lengthIndex = 0;
/*  223 */   int indicatorIndex = 0;
/*  224 */   int metaDataIndex = 0;
/*  225 */   int columnIndexLastRow = 0;
/*  226 */   int lengthIndexLastRow = 0;
/*  227 */   int indicatorIndexLastRow = 0;
/*  228 */   int metaDataIndexLastRow = 0;
/*  229 */   int byteLength = 0;
/*  230 */   int charLength = 0;
/*      */   
/*      */ 
/*      */   int defineType;
/*      */   
/*      */ 
/*  236 */   boolean isDMLReturnedParam = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void setOffsets(int paramInt)
/*      */   {
/*  247 */     this.columnIndex = this.statement.defineByteSubRange;
/*  248 */     this.statement.defineByteSubRange = (this.columnIndex + paramInt * this.byteLength);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void init(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, short paramShort, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  256 */     this.statement = paramOracleStatement;
/*  257 */     this.outBind = paramBoolean;
/*  258 */     this.internalType = paramInt1;
/*  259 */     this.defineType = paramInt2;
/*  260 */     this.formOfUse = paramShort;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   abstract void initForDataAccess(int paramInt1, int paramInt2, String paramString)
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */   void initForDescribe(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  272 */     this.describeType = paramInt1;
/*  273 */     this.describeMaxLength = paramInt2;
/*  274 */     this.nullable = paramBoolean;
/*  275 */     this.precision = paramInt4;
/*  276 */     this.scale = paramInt5;
/*  277 */     this.flags = paramInt3;
/*  278 */     this.contflag = paramInt6;
/*  279 */     this.total_elems = paramInt7;
/*  280 */     this.formOfUse = paramShort;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void initForDescribe(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, short paramShort, String paramString)
/*      */     throws SQLException
/*      */   {
/*  289 */     this.describeTypeName = paramString;
/*  290 */     this.describeOtype = null;
/*      */     
/*  292 */     initForDescribe(paramInt1, paramInt2, paramBoolean, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   OracleInputStream initForNewRow()
/*      */     throws SQLException
/*      */   {
/*  305 */     unimpl("initForNewRow");
/*  306 */     return null;
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
/*      */   int useForDataAccessIfPossible(int paramInt1, int paramInt2, int paramInt3, String paramString)
/*      */     throws SQLException
/*      */   {
/*  321 */     int i = 3;
/*  322 */     int j = 0;
/*  323 */     int k = 0;
/*      */     
/*  325 */     if (this.internalType != 0)
/*      */     {
/*  327 */       if (this.internalType != paramInt1) {
/*  328 */         i = 0;
/*  329 */       } else if (this.rowSpaceIndicator != null)
/*      */       {
/*  331 */         j = this.byteLength;
/*  332 */         k = this.charLength;
/*      */       }
/*      */     }
/*      */     
/*  336 */     if (i == 3)
/*      */     {
/*  338 */       initForDataAccess(paramInt2, paramInt3, paramString);
/*      */       
/*  340 */       if ((!this.outBind) && (j >= this.byteLength) && (k >= this.charLength))
/*      */       {
/*  342 */         i = 2;
/*      */       }
/*      */     }
/*  345 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean useForDescribeIfPossible(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, short paramShort, String paramString)
/*      */     throws SQLException
/*      */   {
/*  357 */     if ((this.externalType == 0) && (paramInt1 != this.describeType)) {
/*  358 */       return false;
/*      */     }
/*  360 */     initForDescribe(paramInt1, paramInt2, paramBoolean, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramShort, paramString);
/*      */     
/*      */ 
/*  363 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void setFormOfUse(short paramShort)
/*      */   {
/*  372 */     this.formOfUse = paramShort;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void updateColumnNumber(int paramInt) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/*  389 */     return super.toString() + ", statement=" + this.statement + ", outBind=" + this.outBind + ", internalType=" + this.internalType + ", internalTypeMaxLength=" + this.internalTypeMaxLength + ", isStream=" + this.isStream + ", formOfUse=" + this.formOfUse + ", internalOtype=" + this.internalOtype + ", externalType=" + this.externalType + ", internalTypeName=" + this.internalTypeName + ", columnName=" + this.columnName + ", describeType=" + this.describeType + ", describeMaxLength=" + this.describeMaxLength + ", nullable=" + this.nullable + ", precision=" + this.precision + ", scale=" + this.scale + ", flags=" + this.flags + ", contflag=" + this.contflag + ", total_elems=" + this.total_elems + ", describeOtype=" + this.describeOtype + ", describeTypeName=" + this.describeTypeName + ", rowSpaceByte=" + this.rowSpaceByte + ", rowSpaceChar=" + this.rowSpaceChar + ", rowSpaceIndicator=" + this.rowSpaceIndicator + ", columnIndex=" + this.columnIndex + ", lengthIndex=" + this.lengthIndex + ", indicatorIndex=" + this.indicatorIndex + ", byteLength=" + this.byteLength + ", charLength=" + this.charLength;
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
/*      */   void unimpl(String paramString)
/*      */     throws SQLException
/*      */   {
/*  412 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, paramString + " not implemented for " + getClass());
/*      */     
/*  414 */     localSQLException.fillInStackTrace();
/*  415 */     throw localSQLException;
/*      */   }
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
/*  427 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*  430 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  431 */       localSQLException.fillInStackTrace();
/*  432 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  436 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  437 */       return false;
/*      */     }
/*  439 */     unimpl("getBoolean");
/*      */     
/*  441 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   byte getByte(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  449 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*  452 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  453 */       localSQLException.fillInStackTrace();
/*  454 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  458 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  459 */       return 0;
/*      */     }
/*  461 */     unimpl("getByte");
/*      */     
/*  463 */     return 0;
/*      */   }
/*      */   
/*      */   OracleResultSet.AuthorizationIndicator getAuthorizationIndicator(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  469 */     if (this.rowSpaceMetaData == null)
/*      */     {
/*      */ 
/*      */ 
/*  473 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  474 */       localSQLException.fillInStackTrace();
/*  475 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  479 */     int i = this.rowSpaceMetaData[(this.metaDataIndex + 1 * paramInt)];
/*      */     
/*      */ 
/*  482 */     if ((i & 0x1) != 0)
/*  483 */       return OracleResultSet.AuthorizationIndicator.UNAUTHORIZED;
/*  484 */     if ((this.securityAttribute == OracleResultSetMetaData.SecurityAttribute.ENABLED) || (this.securityAttribute == OracleResultSetMetaData.SecurityAttribute.NONE))
/*      */     {
/*  486 */       return OracleResultSet.AuthorizationIndicator.NONE;
/*      */     }
/*  488 */     return OracleResultSet.AuthorizationIndicator.UNKNOWN;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   short getShort(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  497 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*  500 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  501 */       localSQLException.fillInStackTrace();
/*  502 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  506 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  507 */       return 0;
/*      */     }
/*  509 */     unimpl("getShort");
/*      */     
/*  511 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   int getInt(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  519 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*  522 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  523 */       localSQLException.fillInStackTrace();
/*  524 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  528 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  529 */       return 0;
/*      */     }
/*  531 */     unimpl("getInt");
/*      */     
/*  533 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   long getLong(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  541 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*  544 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  545 */       localSQLException.fillInStackTrace();
/*  546 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  550 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  551 */       return 0L;
/*      */     }
/*  553 */     unimpl("getLong");
/*      */     
/*  555 */     return 0L;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   float getFloat(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  563 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*  566 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  567 */       localSQLException.fillInStackTrace();
/*  568 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  572 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  573 */       return 0.0F;
/*      */     }
/*  575 */     unimpl("getFloat");
/*      */     
/*      */ 
/*  578 */     return 0.0F;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   double getDouble(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  587 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*  590 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  591 */       localSQLException.fillInStackTrace();
/*  592 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  596 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  597 */       return 0.0D;
/*      */     }
/*  599 */     unimpl("getDouble");
/*      */     
/*  601 */     return 0.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   BigDecimal getBigDecimal(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  609 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/*  613 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  614 */       localSQLException.fillInStackTrace();
/*  615 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  620 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  621 */       return null;
/*      */     }
/*  623 */     unimpl("getBigDecimal");
/*      */     
/*      */ 
/*  626 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   BigDecimal getBigDecimal(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  635 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/*  639 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  640 */       localSQLException.fillInStackTrace();
/*  641 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  646 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt1)] == -1) {
/*  647 */       return null;
/*      */     }
/*  649 */     unimpl("getBigDecimal");
/*      */     
/*      */ 
/*  652 */     return null;
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
/*  670 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   Date getDate(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  678 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/*  682 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  683 */       localSQLException.fillInStackTrace();
/*  684 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  689 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  690 */       return null;
/*      */     }
/*  692 */     unimpl("getDate");
/*      */     
/*      */ 
/*  695 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Date getDate(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  704 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/*  708 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  709 */       localSQLException.fillInStackTrace();
/*  710 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  715 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  716 */       return null;
/*      */     }
/*  718 */     unimpl("getDate");
/*      */     
/*      */ 
/*  721 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Time getTime(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  730 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/*  734 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  735 */       localSQLException.fillInStackTrace();
/*  736 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  741 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  742 */       return null;
/*      */     }
/*  744 */     unimpl("getTime");
/*      */     
/*      */ 
/*  747 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Time getTime(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  756 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/*  760 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  761 */       localSQLException.fillInStackTrace();
/*  762 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  767 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  768 */       return null;
/*      */     }
/*  770 */     unimpl("getTime");
/*      */     
/*      */ 
/*  773 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Timestamp getTimestamp(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  782 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/*  786 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  787 */       localSQLException.fillInStackTrace();
/*  788 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  793 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  794 */       return null;
/*      */     }
/*  796 */     unimpl("getTimestamp");
/*      */     
/*      */ 
/*  799 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  808 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/*  812 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  813 */       localSQLException.fillInStackTrace();
/*  814 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  819 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  820 */       return null;
/*      */     }
/*  822 */     unimpl("getTimestamp");
/*      */     
/*      */ 
/*  825 */     return null;
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
/*      */   byte[] privateGetBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  840 */     return getBytes(paramInt);
/*      */   }
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
/*  852 */     byte[] arrayOfByte = null;
/*      */     
/*  854 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/*  858 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  859 */       localSQLException.fillInStackTrace();
/*  860 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  865 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
/*      */     {
/*  867 */       int i = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/*  868 */       int j = this.columnIndex + this.byteLength * paramInt;
/*      */       
/*  870 */       arrayOfByte = new byte[i];
/*  871 */       System.arraycopy(this.rowSpaceByte, j, arrayOfByte, 0, i);
/*      */     }
/*      */     
/*  874 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   InputStream getAsciiStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  882 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/*  886 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  887 */       localSQLException.fillInStackTrace();
/*  888 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  893 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  894 */       return null;
/*      */     }
/*  896 */     unimpl("getAsciiStream");
/*      */     
/*      */ 
/*  899 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   InputStream getUnicodeStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  908 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/*  912 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  913 */       localSQLException.fillInStackTrace();
/*  914 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  919 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  920 */       return null;
/*      */     }
/*  922 */     unimpl("getUnicodeStream");
/*      */     
/*      */ 
/*  925 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   InputStream getBinaryStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  934 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/*  938 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  939 */       localSQLException.fillInStackTrace();
/*  940 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  945 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  946 */       return null;
/*      */     }
/*  948 */     unimpl("getBinaryStream");
/*      */     
/*      */ 
/*  951 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Object getObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  960 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/*  964 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  965 */       localSQLException.fillInStackTrace();
/*  966 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  971 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/*  972 */       return null;
/*      */     }
/*  974 */     unimpl("getObject");
/*      */     
/*      */ 
/*  977 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   ResultSet getCursor(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  987 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/*  988 */     localSQLException.fillInStackTrace();
/*  989 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Datum getOracleObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  998 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1002 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1003 */       localSQLException.fillInStackTrace();
/* 1004 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1009 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1010 */       return null;
/*      */     }
/* 1012 */     unimpl("getOracleObject");
/*      */     
/*      */ 
/* 1015 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   ROWID getROWID(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1024 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1028 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1029 */       localSQLException.fillInStackTrace();
/* 1030 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1035 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1036 */       return null;
/*      */     }
/*      */     
/* 1039 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 1040 */     localSQLException.fillInStackTrace();
/* 1041 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   NUMBER getNUMBER(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1050 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1054 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1055 */       localSQLException.fillInStackTrace();
/* 1056 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1061 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1062 */       return null;
/*      */     }
/* 1064 */     unimpl("getNUMBER");
/*      */     
/*      */ 
/* 1067 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   DATE getDATE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1076 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1080 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1081 */       localSQLException.fillInStackTrace();
/* 1082 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1087 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1088 */       return null;
/*      */     }
/* 1090 */     unimpl("getDATE");
/*      */     
/*      */ 
/* 1093 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   ARRAY getARRAY(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1102 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1106 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1107 */       localSQLException.fillInStackTrace();
/* 1108 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1113 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1114 */       return null;
/*      */     }
/* 1116 */     unimpl("getARRAY");
/*      */     
/*      */ 
/* 1119 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   STRUCT getSTRUCT(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1128 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1132 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1133 */       localSQLException.fillInStackTrace();
/* 1134 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1139 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1140 */       return null;
/*      */     }
/* 1142 */     unimpl("getSTRUCT");
/*      */     
/*      */ 
/* 1145 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   OPAQUE getOPAQUE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1154 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1158 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1159 */       localSQLException.fillInStackTrace();
/* 1160 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1165 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1166 */       return null;
/*      */     }
/* 1168 */     unimpl("getOPAQUE");
/*      */     
/*      */ 
/* 1171 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   REF getREF(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1180 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1184 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1185 */       localSQLException.fillInStackTrace();
/* 1186 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1191 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1192 */       return null;
/*      */     }
/* 1194 */     unimpl("getREF");
/*      */     
/*      */ 
/* 1197 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   CHAR getCHAR(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1206 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1210 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1211 */       localSQLException.fillInStackTrace();
/* 1212 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1217 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1218 */       return null;
/*      */     }
/* 1220 */     unimpl("getCHAR");
/*      */     
/*      */ 
/* 1223 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   RAW getRAW(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1232 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1236 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1237 */       localSQLException.fillInStackTrace();
/* 1238 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1243 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1244 */       return null;
/*      */     }
/* 1246 */     unimpl("getRAW");
/*      */     
/*      */ 
/* 1249 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   BLOB getBLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1258 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1262 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1263 */       localSQLException.fillInStackTrace();
/* 1264 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1269 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1270 */       return null;
/*      */     }
/* 1272 */     unimpl("getBLOB");
/*      */     
/*      */ 
/* 1275 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   CLOB getCLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1284 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1288 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1289 */       localSQLException.fillInStackTrace();
/* 1290 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1295 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1296 */       return null;
/*      */     }
/* 1298 */     unimpl("getCLOB");
/*      */     
/*      */ 
/* 1301 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   BFILE getBFILE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1310 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1314 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1315 */       localSQLException.fillInStackTrace();
/* 1316 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1321 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1322 */       return null;
/*      */     }
/* 1324 */     unimpl("getBFILE");
/*      */     
/*      */ 
/* 1327 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   CustomDatum getCustomDatum(int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */     throws SQLException
/*      */   {
/* 1337 */     Datum localDatum = getOracleObject(paramInt);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1342 */     return paramCustomDatumFactory.create(localDatum, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   ORAData getORAData(int paramInt, ORADataFactory paramORADataFactory)
/*      */     throws SQLException
/*      */   {
/* 1350 */     Datum localDatum = getOracleObject(paramInt);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1355 */     return paramORADataFactory.create(localDatum, 0);
/*      */   }
/*      */   
/*      */ 
/*      */   Object getObject(int paramInt, OracleDataFactory paramOracleDataFactory)
/*      */     throws SQLException
/*      */   {
/* 1362 */     Datum localDatum = getOracleObject(paramInt);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1367 */     Object localObject = localDatum.toJdbc();
/* 1368 */     return paramOracleDataFactory.create(localObject, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   Object getObject(int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1376 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1380 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1381 */       localSQLException.fillInStackTrace();
/* 1382 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1387 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1388 */       return null;
/*      */     }
/* 1390 */     unimpl("getObject");
/*      */     
/*      */ 
/* 1393 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Reader getCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1402 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1406 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1407 */       localSQLException.fillInStackTrace();
/* 1408 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1413 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1414 */       return null;
/*      */     }
/* 1416 */     unimpl("getCharacterStream");
/*      */     
/*      */ 
/* 1419 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   INTERVALYM getINTERVALYM(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1428 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1432 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1433 */       localSQLException.fillInStackTrace();
/* 1434 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1439 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1440 */       return null;
/*      */     }
/* 1442 */     unimpl("getINTERVALYM");
/*      */     
/*      */ 
/* 1445 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   INTERVALDS getINTERVALDS(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1454 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1458 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1459 */       localSQLException.fillInStackTrace();
/* 1460 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1465 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1466 */       return null;
/*      */     }
/* 1468 */     unimpl("getINTERVALDS");
/*      */     
/*      */ 
/* 1471 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   TIMESTAMP getTIMESTAMP(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1480 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1484 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1485 */       localSQLException.fillInStackTrace();
/* 1486 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1491 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1492 */       return null;
/*      */     }
/* 1494 */     unimpl("getTIMESTAMP");
/*      */     
/*      */ 
/* 1497 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   TIMESTAMPTZ getTIMESTAMPTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1506 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1510 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1511 */       localSQLException.fillInStackTrace();
/* 1512 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1517 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1518 */       return null;
/*      */     }
/* 1520 */     unimpl("getTIMESTAMPTZ");
/*      */     
/*      */ 
/* 1523 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   TIMESTAMPLTZ getTIMESTAMPLTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1532 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1536 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1537 */       localSQLException.fillInStackTrace();
/* 1538 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1543 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1544 */       return null;
/*      */     }
/* 1546 */     unimpl("getTIMESTAMPLTZ");
/*      */     
/*      */ 
/* 1549 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   URL getURL(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1558 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1562 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1563 */       localSQLException.fillInStackTrace();
/* 1564 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1569 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1570 */       return null;
/*      */     }
/* 1572 */     unimpl("getURL");
/*      */     
/*      */ 
/* 1575 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Datum[] getOraclePlsqlIndexTable(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1584 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1588 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1589 */       localSQLException.fillInStackTrace();
/* 1590 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1595 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1596 */       return null;
/*      */     }
/* 1598 */     unimpl("getOraclePlsqlIndexTable");
/*      */     
/*      */ 
/* 1601 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   NClob getNClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1612 */     if (this.formOfUse != 2)
/*      */     {
/* 1614 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, Integer.valueOf(this.columnIndex));
/* 1615 */       localSQLException.fillInStackTrace();
/* 1616 */       throw localSQLException;
/*      */     }
/*      */     
/* 1619 */     return (NClob)getCLOB(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */   String getNString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1626 */     return getString(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */   SQLXML getSQLXML(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1633 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/* 1636 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1637 */       localSQLException.fillInStackTrace();
/* 1638 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1642 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 1643 */       return null;
/*      */     }
/* 1645 */     unimpl("getSQLXML");
/*      */     
/* 1647 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   Reader getNCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1655 */     return getCharacterStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean isNull(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1664 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1668 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1669 */       localSQLException.fillInStackTrace();
/* 1670 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1675 */     return this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void setNull(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1683 */     if (this.rowSpaceIndicator == null)
/*      */     {
/*      */ 
/*      */ 
/* 1687 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1688 */       localSQLException.fillInStackTrace();
/* 1689 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1694 */     this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] = ((short)(paramBoolean ? -1 : 0));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void fetchNextColumns()
/*      */     throws SQLException
/*      */   {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void calculateSizeTmpByteArray() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean unmarshalOneRow()
/*      */     throws SQLException, IOException
/*      */   {
/* 1726 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 148);
/* 1727 */     localSQLException.fillInStackTrace();
/* 1728 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void copyRow()
/*      */     throws SQLException, IOException
/*      */   {
/* 1739 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 148);
/* 1740 */     localSQLException.fillInStackTrace();
/* 1741 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   int readStream(byte[] paramArrayOfByte, int paramInt)
/*      */     throws SQLException, IOException
/*      */   {
/* 1750 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 148);
/* 1751 */     localSQLException.fillInStackTrace();
/* 1752 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void initMetadata()
/*      */     throws SQLException
/*      */   {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void setDisplaySize(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1769 */     this.describeMaxLength = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1774 */   int lastRowProcessed = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1780 */   boolean isUseLess = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1788 */   int physicalColumnIndex = -2;
/*      */   
/*      */ 
/*      */ 
/* 1792 */   boolean isNullByDescribe = false;
/*      */   
/*      */ 
/*      */ 
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
/*      */   {}
/*      */   
/*      */ 
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
/* 1820 */     return this.statement.getConnectionDuringExceptionHandling();
/*      */   }
/*      */   
/*      */ 
/* 1824 */   static final byte[] NULL_DATA_BYTES = { 2, 3, 5, 7, 11, 13, 17, 19 };
/*      */   
/*      */   long updateChecksum(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1829 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1)
/*      */     {
/* 1831 */       paramLong = CRC64.updateChecksum(paramLong, NULL_DATA_BYTES, 0, NULL_DATA_BYTES.length);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 1837 */       int i = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/* 1838 */       int j = this.columnIndex + this.byteLength * paramInt;
/*      */       
/* 1840 */       paramLong = CRC64.updateChecksum(paramLong, this.rowSpaceByte, j, i);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1846 */     return paramLong;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1851 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/Accessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */