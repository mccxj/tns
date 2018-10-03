/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.sql.Array;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Date;
/*      */ import java.sql.NClob;
/*      */ import java.sql.Ref;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import oracle.sql.BFILE;
/*      */ import oracle.sql.BINARY_FLOAT;
/*      */ import oracle.sql.CLOB;
/*      */ import oracle.sql.DATE;
/*      */ import oracle.sql.Datum;
/*      */ import oracle.sql.INTERVALDS;
/*      */ import oracle.sql.NUMBER;
/*      */ import oracle.sql.OPAQUE;
/*      */ import oracle.sql.ORAData;
/*      */ import oracle.sql.REF;
/*      */ import oracle.sql.STRUCT;
/*      */ import oracle.sql.TIMESTAMP;
/*      */ import oracle.sql.TIMESTAMPTZ;
/*      */ 
/*      */ abstract class BaseResultSet extends OracleResultSet
/*      */ {
/*   33 */   SQLWarning sqlWarning = null;
/*   34 */   boolean autoRefetch = true;
/*   35 */   boolean closed = false;
/*      */   
/*      */ 
/*      */ 
/*   39 */   boolean close_statement_on_close = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void closeStatementOnClose()
/*      */   {
/*   48 */     this.close_statement_on_close = true;
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
/*      */   public void beforeFirst()
/*      */     throws SQLException
/*      */   {
/*   64 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 75, "beforeFirst");
/*   65 */     localSQLException.fillInStackTrace();
/*   66 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void afterLast()
/*      */     throws SQLException
/*      */   {
/*   75 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 75, "afterLast");
/*   76 */     localSQLException.fillInStackTrace();
/*   77 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean first()
/*      */     throws SQLException
/*      */   {
/*   86 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 75, "first");
/*   87 */     localSQLException.fillInStackTrace();
/*   88 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean last()
/*      */     throws SQLException
/*      */   {
/*   97 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 75, "last");
/*   98 */     localSQLException.fillInStackTrace();
/*   99 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean absolute(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  108 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 75, "absolute");
/*  109 */     localSQLException.fillInStackTrace();
/*  110 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean relative(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  119 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 75, "relative");
/*  120 */     localSQLException.fillInStackTrace();
/*  121 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean previous()
/*      */     throws SQLException
/*      */   {
/*  130 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 75, "previous");
/*  131 */     localSQLException.fillInStackTrace();
/*  132 */     throw localSQLException;
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
/*      */   public void setFetchDirection(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  146 */     if (paramInt == 1000)
/*  147 */       return;
/*  148 */     if ((paramInt == 1001) || (paramInt == 1002))
/*      */     {
/*  150 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 75, "setFetchDirection(FETCH_REVERSE, FETCH_UNKNOWN)");
/*  151 */       localSQLException.fillInStackTrace();
/*  152 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  156 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "setFetchDirection");
/*  157 */     localSQLException.fillInStackTrace();
/*  158 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getFetchDirection()
/*      */     throws SQLException
/*      */   {
/*  166 */     return 1000;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getType()
/*      */     throws SQLException
/*      */   {
/*  173 */     return 1003;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getConcurrency()
/*      */     throws SQLException
/*      */   {
/*  180 */     return 1007;
/*      */   }
/*      */   
/*      */ 
/*      */   public SQLWarning getWarnings()
/*      */     throws SQLException
/*      */   {
/*  187 */     return this.sqlWarning;
/*      */   }
/*      */   
/*      */ 
/*      */   public void clearWarnings()
/*      */     throws SQLException
/*      */   {
/*  194 */     this.sqlWarning = null;
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
/*      */   public void updateArray(int paramInt, Array paramArray)
/*      */     throws SQLException
/*      */   {
/*  208 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateArray");
/*  209 */     localSQLException.fillInStackTrace();
/*  210 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBigDecimal(int paramInt, java.math.BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/*  218 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBigDecimal");
/*  219 */     localSQLException.fillInStackTrace();
/*  220 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBlob(int paramInt, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/*  228 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBlob");
/*  229 */     localSQLException.fillInStackTrace();
/*  230 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBoolean(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  238 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBoolean");
/*  239 */     localSQLException.fillInStackTrace();
/*  240 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateByte(int paramInt, byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  248 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateByte");
/*  249 */     localSQLException.fillInStackTrace();
/*  250 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBytes(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  258 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBytes");
/*  259 */     localSQLException.fillInStackTrace();
/*  260 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateClob(int paramInt, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/*  268 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateClob");
/*  269 */     localSQLException.fillInStackTrace();
/*  270 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateDate(int paramInt, Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  278 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateDate");
/*  279 */     localSQLException.fillInStackTrace();
/*  280 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateDate(int paramInt, Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  288 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateDate");
/*  289 */     localSQLException.fillInStackTrace();
/*  290 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateDouble(int paramInt, double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  298 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateDouble");
/*  299 */     localSQLException.fillInStackTrace();
/*  300 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateFloat(int paramInt, float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  308 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateFloat");
/*  309 */     localSQLException.fillInStackTrace();
/*  310 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateInt(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  318 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateInt");
/*  319 */     localSQLException.fillInStackTrace();
/*  320 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateLong(int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  328 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateLong");
/*  329 */     localSQLException.fillInStackTrace();
/*  330 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateNClob(int paramInt, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/*  338 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateNClob");
/*  339 */     localSQLException.fillInStackTrace();
/*  340 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateNString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/*  348 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateNString");
/*  349 */     localSQLException.fillInStackTrace();
/*  350 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateObject(int paramInt, Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  358 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateObject");
/*  359 */     localSQLException.fillInStackTrace();
/*  360 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateObject(int paramInt1, Object paramObject, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  368 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateObject");
/*  369 */     localSQLException.fillInStackTrace();
/*  370 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateRef(int paramInt, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/*  378 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateRef");
/*  379 */     localSQLException.fillInStackTrace();
/*  380 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateRowId(int paramInt, java.sql.RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/*  388 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateRowId");
/*  389 */     localSQLException.fillInStackTrace();
/*  390 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateShort(int paramInt, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  398 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateShort");
/*  399 */     localSQLException.fillInStackTrace();
/*  400 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateSQLXML(int paramInt, java.sql.SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/*  408 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateSQLXML");
/*  409 */     localSQLException.fillInStackTrace();
/*  410 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/*  418 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateString");
/*  419 */     localSQLException.fillInStackTrace();
/*  420 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateTime(int paramInt, Time paramTime)
/*      */     throws SQLException
/*      */   {
/*  428 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateTime");
/*  429 */     localSQLException.fillInStackTrace();
/*  430 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateTime(int paramInt, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  438 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateTime");
/*  439 */     localSQLException.fillInStackTrace();
/*  440 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateTimestamp(int paramInt, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/*  448 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateTimestamp");
/*  449 */     localSQLException.fillInStackTrace();
/*  450 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateTimestamp(int paramInt, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  458 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateTimestamp");
/*  459 */     localSQLException.fillInStackTrace();
/*  460 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateURL(int paramInt, java.net.URL paramURL)
/*      */     throws SQLException
/*      */   {
/*  468 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateURL");
/*  469 */     localSQLException.fillInStackTrace();
/*  470 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateARRAY(int paramInt, oracle.sql.ARRAY paramARRAY)
/*      */     throws SQLException
/*      */   {
/*  478 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateARRAY");
/*  479 */     localSQLException.fillInStackTrace();
/*  480 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBFILE(int paramInt, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  488 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBFILE");
/*  489 */     localSQLException.fillInStackTrace();
/*  490 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBfile(int paramInt, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  498 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBfile");
/*  499 */     localSQLException.fillInStackTrace();
/*  500 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBinaryFloat(int paramInt, float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  508 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBinaryFloat");
/*  509 */     localSQLException.fillInStackTrace();
/*  510 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBinaryFloat(int paramInt, BINARY_FLOAT paramBINARY_FLOAT)
/*      */     throws SQLException
/*      */   {
/*  518 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBinaryFloat");
/*  519 */     localSQLException.fillInStackTrace();
/*  520 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBinaryDouble(int paramInt, double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  528 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBinaryDouble");
/*  529 */     localSQLException.fillInStackTrace();
/*  530 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBinaryDouble(int paramInt, oracle.sql.BINARY_DOUBLE paramBINARY_DOUBLE)
/*      */     throws SQLException
/*      */   {
/*  538 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBinaryDouble");
/*  539 */     localSQLException.fillInStackTrace();
/*  540 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBLOB(int paramInt, oracle.sql.BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/*  548 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBLOB");
/*  549 */     localSQLException.fillInStackTrace();
/*  550 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateCHAR(int paramInt, oracle.sql.CHAR paramCHAR)
/*      */     throws SQLException
/*      */   {
/*  558 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateCHAR");
/*  559 */     localSQLException.fillInStackTrace();
/*  560 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateCLOB(int paramInt, CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/*  568 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateCLOB");
/*  569 */     localSQLException.fillInStackTrace();
/*  570 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateCursor(int paramInt, ResultSet paramResultSet)
/*      */     throws SQLException
/*      */   {
/*  578 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateCursor");
/*  579 */     localSQLException.fillInStackTrace();
/*  580 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateCustomDatum(int paramInt, oracle.sql.CustomDatum paramCustomDatum)
/*      */     throws SQLException
/*      */   {
/*  588 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateCustomDatum");
/*  589 */     localSQLException.fillInStackTrace();
/*  590 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateDATE(int paramInt, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/*  598 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateDATE");
/*  599 */     localSQLException.fillInStackTrace();
/*  600 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateFixedCHAR(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/*  608 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateFixedCHAR");
/*  609 */     localSQLException.fillInStackTrace();
/*  610 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateINTERVALDS(int paramInt, INTERVALDS paramINTERVALDS)
/*      */     throws SQLException
/*      */   {
/*  618 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateINTERVALDS");
/*  619 */     localSQLException.fillInStackTrace();
/*  620 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateINTERVALYM(int paramInt, oracle.sql.INTERVALYM paramINTERVALYM)
/*      */     throws SQLException
/*      */   {
/*  628 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateINTERVALYM");
/*  629 */     localSQLException.fillInStackTrace();
/*  630 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateNUMBER(int paramInt, NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/*  638 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateNUMBER");
/*  639 */     localSQLException.fillInStackTrace();
/*  640 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateOPAQUE(int paramInt, OPAQUE paramOPAQUE)
/*      */     throws SQLException
/*      */   {
/*  648 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateOPAQUE");
/*  649 */     localSQLException.fillInStackTrace();
/*  650 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateOracleObject(int paramInt, Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/*  658 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateOracleObject");
/*  659 */     localSQLException.fillInStackTrace();
/*  660 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateORAData(int paramInt, ORAData paramORAData)
/*      */     throws SQLException
/*      */   {
/*  668 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateORAData");
/*  669 */     localSQLException.fillInStackTrace();
/*  670 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateRAW(int paramInt, oracle.sql.RAW paramRAW)
/*      */     throws SQLException
/*      */   {
/*  678 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateRAW");
/*  679 */     localSQLException.fillInStackTrace();
/*  680 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateREF(int paramInt, REF paramREF)
/*      */     throws SQLException
/*      */   {
/*  688 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateREF");
/*  689 */     localSQLException.fillInStackTrace();
/*  690 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateRefType(int paramInt, REF paramREF)
/*      */     throws SQLException
/*      */   {
/*  698 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateRefType");
/*  699 */     localSQLException.fillInStackTrace();
/*  700 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateROWID(int paramInt, oracle.sql.ROWID paramROWID)
/*      */     throws SQLException
/*      */   {
/*  708 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateROWID");
/*  709 */     localSQLException.fillInStackTrace();
/*  710 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateSTRUCT(int paramInt, STRUCT paramSTRUCT)
/*      */     throws SQLException
/*      */   {
/*  718 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateSTRUCT");
/*  719 */     localSQLException.fillInStackTrace();
/*  720 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateTIMESTAMPLTZ(int paramInt, oracle.sql.TIMESTAMPLTZ paramTIMESTAMPLTZ)
/*      */     throws SQLException
/*      */   {
/*  728 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateTIMESTAMPLTZ");
/*  729 */     localSQLException.fillInStackTrace();
/*  730 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateTIMESTAMPTZ(int paramInt, TIMESTAMPTZ paramTIMESTAMPTZ)
/*      */     throws SQLException
/*      */   {
/*  738 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateTIMESTAMPTZ");
/*  739 */     localSQLException.fillInStackTrace();
/*  740 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateTIMESTAMP(int paramInt, TIMESTAMP paramTIMESTAMP)
/*      */     throws SQLException
/*      */   {
/*  748 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateTIMESTAMP");
/*  749 */     localSQLException.fillInStackTrace();
/*  750 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBlob(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/*  758 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBlob");
/*  759 */     localSQLException.fillInStackTrace();
/*  760 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBlob(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  768 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBlob");
/*  769 */     localSQLException.fillInStackTrace();
/*  770 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/*  778 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateClob");
/*  779 */     localSQLException.fillInStackTrace();
/*  780 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  788 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateClob");
/*  789 */     localSQLException.fillInStackTrace();
/*  790 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateNClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/*  798 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateNClob");
/*  799 */     localSQLException.fillInStackTrace();
/*  800 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateNClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  808 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateNClob");
/*  809 */     localSQLException.fillInStackTrace();
/*  810 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateAsciiStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/*  818 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateAsciiStream");
/*  819 */     localSQLException.fillInStackTrace();
/*  820 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  828 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateAsciiStream");
/*  829 */     localSQLException.fillInStackTrace();
/*  830 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateAsciiStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  838 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateAsciiStream");
/*  839 */     localSQLException.fillInStackTrace();
/*  840 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/*  848 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBinaryStream");
/*  849 */     localSQLException.fillInStackTrace();
/*  850 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  858 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBinaryStream");
/*  859 */     localSQLException.fillInStackTrace();
/*  860 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  868 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateBinaryStream");
/*  869 */     localSQLException.fillInStackTrace();
/*  870 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/*  878 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateCharacterStream");
/*  879 */     localSQLException.fillInStackTrace();
/*  880 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateCharacterStream(int paramInt1, Reader paramReader, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  888 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateCharacterStream");
/*  889 */     localSQLException.fillInStackTrace();
/*  890 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  898 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateCharacterStream");
/*  899 */     localSQLException.fillInStackTrace();
/*  900 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateNCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/*  908 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateNCharacterStream");
/*  909 */     localSQLException.fillInStackTrace();
/*  910 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateNCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  918 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateNCharacterStream");
/*  919 */     localSQLException.fillInStackTrace();
/*  920 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateUnicodeStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  928 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateUnicodeStream");
/*  929 */     localSQLException.fillInStackTrace();
/*  930 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNull(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  943 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateNull");
/*  944 */     localSQLException.fillInStackTrace();
/*  945 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean rowUpdated()
/*      */     throws SQLException
/*      */   {
/*  954 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean rowInserted()
/*      */     throws SQLException
/*      */   {
/*  961 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean rowDeleted()
/*      */     throws SQLException
/*      */   {
/*  968 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void insertRow()
/*      */     throws SQLException
/*      */   {
/*  976 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "insertRow");
/*  977 */     localSQLException.fillInStackTrace();
/*  978 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateRow()
/*      */     throws SQLException
/*      */   {
/*  987 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "updateRow");
/*  988 */     localSQLException.fillInStackTrace();
/*  989 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void deleteRow()
/*      */     throws SQLException
/*      */   {
/*  998 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "deleteRow");
/*  999 */     localSQLException.fillInStackTrace();
/* 1000 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void refreshRow()
/*      */     throws SQLException
/*      */   {
/* 1009 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23, null);
/* 1010 */     localSQLException.fillInStackTrace();
/* 1011 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void cancelRowUpdates()
/*      */     throws SQLException
/*      */   {
/* 1020 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "cancelRowUpdates");
/* 1021 */     localSQLException.fillInStackTrace();
/* 1022 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void moveToInsertRow()
/*      */     throws SQLException
/*      */   {
/* 1031 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "moveToInsertRow");
/* 1032 */     localSQLException.fillInStackTrace();
/* 1033 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void moveToCurrentRow()
/*      */     throws SQLException
/*      */   {
/* 1042 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 76, "moveToCurrentRow");
/* 1043 */     localSQLException.fillInStackTrace();
/* 1044 */     throw localSQLException;
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
/*      */   public void setAutoRefetch(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1073 */     this.autoRefetch = paramBoolean;
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
/*      */   public boolean getAutoRefetch()
/*      */     throws SQLException
/*      */   {
/* 1090 */     return this.autoRefetch;
/*      */   }
/*      */   
/*      */ 
/*      */   public void close()
/*      */     throws SQLException
/*      */   {
/* 1097 */     this.closed = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   OracleStatement getOracleStatement()
/*      */     throws SQLException
/*      */   {
/* 1108 */     return null;
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
/*      */   public int getHoldability()
/*      */     throws SQLException
/*      */   {
/* 1123 */     return 1;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isClosed()
/*      */     throws SQLException
/*      */   {
/* 1130 */     return this.closed;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1137 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/BaseResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */