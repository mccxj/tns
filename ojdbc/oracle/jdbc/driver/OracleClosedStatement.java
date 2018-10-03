/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.URL;
/*      */ import java.sql.Array;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Connection;
/*      */ import java.sql.Date;
/*      */ import java.sql.NClob;
/*      */ import java.sql.ParameterMetaData;
/*      */ import java.sql.Ref;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.RowId;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.SQLXML;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.Map;
/*      */ import oracle.jdbc.OracleDataFactory;
/*      */ import oracle.jdbc.OracleParameterMetaData;
/*      */ import oracle.jdbc.OracleResultSetCache;
/*      */ import oracle.jdbc.dcn.DatabaseChangeRegistration;
/*      */ import oracle.jdbc.internal.OracleCallableStatement;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.internal.OracleStatement.SqlKind;
/*      */ import oracle.sql.ARRAY;
/*      */ import oracle.sql.BFILE;
/*      */ import oracle.sql.BINARY_DOUBLE;
/*      */ import oracle.sql.BINARY_FLOAT;
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
/*      */ import oracle.sql.StructDescriptor;
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
/*      */ class OracleClosedStatement
/*      */   implements OracleCallableStatement
/*      */ {
/*      */   private OracleStatement wrapper;
/*      */   
/*      */   public void setArray(int paramInt, Array paramArray)
/*      */     throws SQLException
/*      */   {
/*   82 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*   83 */     localSQLException.fillInStackTrace();
/*   84 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setArrayAtName(String paramString, Array paramArray)
/*      */     throws SQLException
/*      */   {
/*   92 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*   93 */     localSQLException.fillInStackTrace();
/*   94 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setArray(String paramString, Array paramArray)
/*      */     throws SQLException
/*      */   {
/*  102 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  103 */     localSQLException.fillInStackTrace();
/*  104 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBigDecimal(int paramInt, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/*  113 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  114 */     localSQLException.fillInStackTrace();
/*  115 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBigDecimalAtName(String paramString, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/*  123 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  124 */     localSQLException.fillInStackTrace();
/*  125 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBigDecimal(String paramString, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/*  133 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  134 */     localSQLException.fillInStackTrace();
/*  135 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(int paramInt, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/*  144 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  145 */     localSQLException.fillInStackTrace();
/*  146 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlobAtName(String paramString, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/*  154 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  155 */     localSQLException.fillInStackTrace();
/*  156 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/*  164 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  165 */     localSQLException.fillInStackTrace();
/*  166 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBoolean(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  175 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  176 */     localSQLException.fillInStackTrace();
/*  177 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBooleanAtName(String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  185 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  186 */     localSQLException.fillInStackTrace();
/*  187 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBoolean(String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  195 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  196 */     localSQLException.fillInStackTrace();
/*  197 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setByte(int paramInt, byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  206 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  207 */     localSQLException.fillInStackTrace();
/*  208 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setByteAtName(String paramString, byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  216 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  217 */     localSQLException.fillInStackTrace();
/*  218 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setByte(String paramString, byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  226 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  227 */     localSQLException.fillInStackTrace();
/*  228 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBytes(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  237 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  238 */     localSQLException.fillInStackTrace();
/*  239 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBytesAtName(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  247 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  248 */     localSQLException.fillInStackTrace();
/*  249 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBytes(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  257 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  258 */     localSQLException.fillInStackTrace();
/*  259 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(int paramInt, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/*  268 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  269 */     localSQLException.fillInStackTrace();
/*  270 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClobAtName(String paramString, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/*  278 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  279 */     localSQLException.fillInStackTrace();
/*  280 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/*  288 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  289 */     localSQLException.fillInStackTrace();
/*  290 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(int paramInt, Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  299 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  300 */     localSQLException.fillInStackTrace();
/*  301 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDateAtName(String paramString, Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  309 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  310 */     localSQLException.fillInStackTrace();
/*  311 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDate(String paramString, Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  319 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  320 */     localSQLException.fillInStackTrace();
/*  321 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(int paramInt, Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  330 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  331 */     localSQLException.fillInStackTrace();
/*  332 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDateAtName(String paramString, Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  340 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  341 */     localSQLException.fillInStackTrace();
/*  342 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDate(String paramString, Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  350 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  351 */     localSQLException.fillInStackTrace();
/*  352 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDouble(int paramInt, double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  361 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  362 */     localSQLException.fillInStackTrace();
/*  363 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDoubleAtName(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  371 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  372 */     localSQLException.fillInStackTrace();
/*  373 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDouble(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  381 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  382 */     localSQLException.fillInStackTrace();
/*  383 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFloat(int paramInt, float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  392 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  393 */     localSQLException.fillInStackTrace();
/*  394 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFloatAtName(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  402 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  403 */     localSQLException.fillInStackTrace();
/*  404 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFloat(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  412 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  413 */     localSQLException.fillInStackTrace();
/*  414 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setInt(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  423 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  424 */     localSQLException.fillInStackTrace();
/*  425 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setIntAtName(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  433 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  434 */     localSQLException.fillInStackTrace();
/*  435 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setInt(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  443 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  444 */     localSQLException.fillInStackTrace();
/*  445 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLong(int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  454 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  455 */     localSQLException.fillInStackTrace();
/*  456 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setLongAtName(String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  464 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  465 */     localSQLException.fillInStackTrace();
/*  466 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setLong(String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  474 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  475 */     localSQLException.fillInStackTrace();
/*  476 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(int paramInt, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/*  485 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  486 */     localSQLException.fillInStackTrace();
/*  487 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClobAtName(String paramString, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/*  495 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  496 */     localSQLException.fillInStackTrace();
/*  497 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/*  505 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  506 */     localSQLException.fillInStackTrace();
/*  507 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/*  516 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  517 */     localSQLException.fillInStackTrace();
/*  518 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNStringAtName(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  526 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  527 */     localSQLException.fillInStackTrace();
/*  528 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  536 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  537 */     localSQLException.fillInStackTrace();
/*  538 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(int paramInt, Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  547 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  548 */     localSQLException.fillInStackTrace();
/*  549 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setObjectAtName(String paramString, Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  557 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  558 */     localSQLException.fillInStackTrace();
/*  559 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setObject(String paramString, Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  567 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  568 */     localSQLException.fillInStackTrace();
/*  569 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(int paramInt1, Object paramObject, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  578 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  579 */     localSQLException.fillInStackTrace();
/*  580 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setObjectAtName(String paramString, Object paramObject, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  588 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  589 */     localSQLException.fillInStackTrace();
/*  590 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setObject(String paramString, Object paramObject, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  598 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  599 */     localSQLException.fillInStackTrace();
/*  600 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRef(int paramInt, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/*  609 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  610 */     localSQLException.fillInStackTrace();
/*  611 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRefAtName(String paramString, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/*  619 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  620 */     localSQLException.fillInStackTrace();
/*  621 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRef(String paramString, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/*  629 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  630 */     localSQLException.fillInStackTrace();
/*  631 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRowId(int paramInt, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/*  640 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  641 */     localSQLException.fillInStackTrace();
/*  642 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRowIdAtName(String paramString, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/*  650 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  651 */     localSQLException.fillInStackTrace();
/*  652 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRowId(String paramString, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/*  660 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  661 */     localSQLException.fillInStackTrace();
/*  662 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShort(int paramInt, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  671 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  672 */     localSQLException.fillInStackTrace();
/*  673 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setShortAtName(String paramString, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  681 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  682 */     localSQLException.fillInStackTrace();
/*  683 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setShort(String paramString, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  691 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  692 */     localSQLException.fillInStackTrace();
/*  693 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSQLXML(int paramInt, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/*  702 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  703 */     localSQLException.fillInStackTrace();
/*  704 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSQLXMLAtName(String paramString, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/*  712 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  713 */     localSQLException.fillInStackTrace();
/*  714 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSQLXML(String paramString, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/*  722 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  723 */     localSQLException.fillInStackTrace();
/*  724 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/*  733 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  734 */     localSQLException.fillInStackTrace();
/*  735 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setStringAtName(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  743 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  744 */     localSQLException.fillInStackTrace();
/*  745 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  753 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  754 */     localSQLException.fillInStackTrace();
/*  755 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(int paramInt, Time paramTime)
/*      */     throws SQLException
/*      */   {
/*  764 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  765 */     localSQLException.fillInStackTrace();
/*  766 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTimeAtName(String paramString, Time paramTime)
/*      */     throws SQLException
/*      */   {
/*  774 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  775 */     localSQLException.fillInStackTrace();
/*  776 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTime(String paramString, Time paramTime)
/*      */     throws SQLException
/*      */   {
/*  784 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  785 */     localSQLException.fillInStackTrace();
/*  786 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(int paramInt, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  795 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  796 */     localSQLException.fillInStackTrace();
/*  797 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTimeAtName(String paramString, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  805 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  806 */     localSQLException.fillInStackTrace();
/*  807 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTime(String paramString, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  815 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  816 */     localSQLException.fillInStackTrace();
/*  817 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimestamp(int paramInt, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/*  826 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  827 */     localSQLException.fillInStackTrace();
/*  828 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTimestampAtName(String paramString, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/*  836 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  837 */     localSQLException.fillInStackTrace();
/*  838 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTimestamp(String paramString, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/*  846 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  847 */     localSQLException.fillInStackTrace();
/*  848 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimestamp(int paramInt, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  857 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  858 */     localSQLException.fillInStackTrace();
/*  859 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTimestampAtName(String paramString, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  867 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  868 */     localSQLException.fillInStackTrace();
/*  869 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTimestamp(String paramString, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  877 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  878 */     localSQLException.fillInStackTrace();
/*  879 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setURL(int paramInt, URL paramURL)
/*      */     throws SQLException
/*      */   {
/*  888 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  889 */     localSQLException.fillInStackTrace();
/*  890 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setURLAtName(String paramString, URL paramURL)
/*      */     throws SQLException
/*      */   {
/*  898 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  899 */     localSQLException.fillInStackTrace();
/*  900 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setURL(String paramString, URL paramURL)
/*      */     throws SQLException
/*      */   {
/*  908 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  909 */     localSQLException.fillInStackTrace();
/*  910 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setARRAY(int paramInt, ARRAY paramARRAY)
/*      */     throws SQLException
/*      */   {
/*  919 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  920 */     localSQLException.fillInStackTrace();
/*  921 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setARRAYAtName(String paramString, ARRAY paramARRAY)
/*      */     throws SQLException
/*      */   {
/*  929 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  930 */     localSQLException.fillInStackTrace();
/*  931 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setARRAY(String paramString, ARRAY paramARRAY)
/*      */     throws SQLException
/*      */   {
/*  939 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  940 */     localSQLException.fillInStackTrace();
/*  941 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBFILE(int paramInt, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  950 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  951 */     localSQLException.fillInStackTrace();
/*  952 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBFILEAtName(String paramString, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  960 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  961 */     localSQLException.fillInStackTrace();
/*  962 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBFILE(String paramString, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  970 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  971 */     localSQLException.fillInStackTrace();
/*  972 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBfile(int paramInt, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  981 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  982 */     localSQLException.fillInStackTrace();
/*  983 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBfileAtName(String paramString, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  991 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  992 */     localSQLException.fillInStackTrace();
/*  993 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBfile(String paramString, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 1001 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1002 */     localSQLException.fillInStackTrace();
/* 1003 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryFloat(int paramInt, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 1012 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1013 */     localSQLException.fillInStackTrace();
/* 1014 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryFloatAtName(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 1022 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1023 */     localSQLException.fillInStackTrace();
/* 1024 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryFloat(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 1032 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1033 */     localSQLException.fillInStackTrace();
/* 1034 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryFloat(int paramInt, BINARY_FLOAT paramBINARY_FLOAT)
/*      */     throws SQLException
/*      */   {
/* 1043 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1044 */     localSQLException.fillInStackTrace();
/* 1045 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryFloatAtName(String paramString, BINARY_FLOAT paramBINARY_FLOAT)
/*      */     throws SQLException
/*      */   {
/* 1053 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1054 */     localSQLException.fillInStackTrace();
/* 1055 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryFloat(String paramString, BINARY_FLOAT paramBINARY_FLOAT)
/*      */     throws SQLException
/*      */   {
/* 1063 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1064 */     localSQLException.fillInStackTrace();
/* 1065 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryDouble(int paramInt, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 1074 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1075 */     localSQLException.fillInStackTrace();
/* 1076 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryDoubleAtName(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 1084 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1085 */     localSQLException.fillInStackTrace();
/* 1086 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryDouble(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 1094 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1095 */     localSQLException.fillInStackTrace();
/* 1096 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryDouble(int paramInt, BINARY_DOUBLE paramBINARY_DOUBLE)
/*      */     throws SQLException
/*      */   {
/* 1105 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1106 */     localSQLException.fillInStackTrace();
/* 1107 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryDoubleAtName(String paramString, BINARY_DOUBLE paramBINARY_DOUBLE)
/*      */     throws SQLException
/*      */   {
/* 1115 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1116 */     localSQLException.fillInStackTrace();
/* 1117 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryDouble(String paramString, BINARY_DOUBLE paramBINARY_DOUBLE)
/*      */     throws SQLException
/*      */   {
/* 1125 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1126 */     localSQLException.fillInStackTrace();
/* 1127 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBLOB(int paramInt, BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 1136 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1137 */     localSQLException.fillInStackTrace();
/* 1138 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBLOBAtName(String paramString, BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 1146 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1147 */     localSQLException.fillInStackTrace();
/* 1148 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBLOB(String paramString, BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 1156 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1157 */     localSQLException.fillInStackTrace();
/* 1158 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCHAR(int paramInt, CHAR paramCHAR)
/*      */     throws SQLException
/*      */   {
/* 1167 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1168 */     localSQLException.fillInStackTrace();
/* 1169 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCHARAtName(String paramString, CHAR paramCHAR)
/*      */     throws SQLException
/*      */   {
/* 1177 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1178 */     localSQLException.fillInStackTrace();
/* 1179 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCHAR(String paramString, CHAR paramCHAR)
/*      */     throws SQLException
/*      */   {
/* 1187 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1188 */     localSQLException.fillInStackTrace();
/* 1189 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCLOB(int paramInt, CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 1198 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1199 */     localSQLException.fillInStackTrace();
/* 1200 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCLOBAtName(String paramString, CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 1208 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1209 */     localSQLException.fillInStackTrace();
/* 1210 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCLOB(String paramString, CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 1218 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1219 */     localSQLException.fillInStackTrace();
/* 1220 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCursor(int paramInt, ResultSet paramResultSet)
/*      */     throws SQLException
/*      */   {
/* 1229 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1230 */     localSQLException.fillInStackTrace();
/* 1231 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCursorAtName(String paramString, ResultSet paramResultSet)
/*      */     throws SQLException
/*      */   {
/* 1239 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1240 */     localSQLException.fillInStackTrace();
/* 1241 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCursor(String paramString, ResultSet paramResultSet)
/*      */     throws SQLException
/*      */   {
/* 1249 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1250 */     localSQLException.fillInStackTrace();
/* 1251 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCustomDatum(int paramInt, CustomDatum paramCustomDatum)
/*      */     throws SQLException
/*      */   {
/* 1260 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1261 */     localSQLException.fillInStackTrace();
/* 1262 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCustomDatumAtName(String paramString, CustomDatum paramCustomDatum)
/*      */     throws SQLException
/*      */   {
/* 1270 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1271 */     localSQLException.fillInStackTrace();
/* 1272 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCustomDatum(String paramString, CustomDatum paramCustomDatum)
/*      */     throws SQLException
/*      */   {
/* 1280 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1281 */     localSQLException.fillInStackTrace();
/* 1282 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDATE(int paramInt, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/* 1291 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1292 */     localSQLException.fillInStackTrace();
/* 1293 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDATEAtName(String paramString, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/* 1301 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1302 */     localSQLException.fillInStackTrace();
/* 1303 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDATE(String paramString, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/* 1311 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1312 */     localSQLException.fillInStackTrace();
/* 1313 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFixedCHAR(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 1322 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1323 */     localSQLException.fillInStackTrace();
/* 1324 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFixedCHARAtName(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 1332 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1333 */     localSQLException.fillInStackTrace();
/* 1334 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFixedCHAR(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 1342 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1343 */     localSQLException.fillInStackTrace();
/* 1344 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setINTERVALDS(int paramInt, INTERVALDS paramINTERVALDS)
/*      */     throws SQLException
/*      */   {
/* 1353 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1354 */     localSQLException.fillInStackTrace();
/* 1355 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setINTERVALDSAtName(String paramString, INTERVALDS paramINTERVALDS)
/*      */     throws SQLException
/*      */   {
/* 1363 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1364 */     localSQLException.fillInStackTrace();
/* 1365 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setINTERVALDS(String paramString, INTERVALDS paramINTERVALDS)
/*      */     throws SQLException
/*      */   {
/* 1373 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1374 */     localSQLException.fillInStackTrace();
/* 1375 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setINTERVALYM(int paramInt, INTERVALYM paramINTERVALYM)
/*      */     throws SQLException
/*      */   {
/* 1384 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1385 */     localSQLException.fillInStackTrace();
/* 1386 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setINTERVALYMAtName(String paramString, INTERVALYM paramINTERVALYM)
/*      */     throws SQLException
/*      */   {
/* 1394 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1395 */     localSQLException.fillInStackTrace();
/* 1396 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setINTERVALYM(String paramString, INTERVALYM paramINTERVALYM)
/*      */     throws SQLException
/*      */   {
/* 1404 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1405 */     localSQLException.fillInStackTrace();
/* 1406 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNUMBER(int paramInt, NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 1415 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1416 */     localSQLException.fillInStackTrace();
/* 1417 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNUMBERAtName(String paramString, NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 1425 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1426 */     localSQLException.fillInStackTrace();
/* 1427 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNUMBER(String paramString, NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 1435 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1436 */     localSQLException.fillInStackTrace();
/* 1437 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setOPAQUE(int paramInt, OPAQUE paramOPAQUE)
/*      */     throws SQLException
/*      */   {
/* 1446 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1447 */     localSQLException.fillInStackTrace();
/* 1448 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setOPAQUEAtName(String paramString, OPAQUE paramOPAQUE)
/*      */     throws SQLException
/*      */   {
/* 1456 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1457 */     localSQLException.fillInStackTrace();
/* 1458 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setOPAQUE(String paramString, OPAQUE paramOPAQUE)
/*      */     throws SQLException
/*      */   {
/* 1466 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1467 */     localSQLException.fillInStackTrace();
/* 1468 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setOracleObject(int paramInt, Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/* 1477 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1478 */     localSQLException.fillInStackTrace();
/* 1479 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setOracleObjectAtName(String paramString, Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/* 1487 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1488 */     localSQLException.fillInStackTrace();
/* 1489 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setOracleObject(String paramString, Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/* 1497 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1498 */     localSQLException.fillInStackTrace();
/* 1499 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setORAData(int paramInt, ORAData paramORAData)
/*      */     throws SQLException
/*      */   {
/* 1508 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1509 */     localSQLException.fillInStackTrace();
/* 1510 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setORADataAtName(String paramString, ORAData paramORAData)
/*      */     throws SQLException
/*      */   {
/* 1518 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1519 */     localSQLException.fillInStackTrace();
/* 1520 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setORAData(String paramString, ORAData paramORAData)
/*      */     throws SQLException
/*      */   {
/* 1528 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1529 */     localSQLException.fillInStackTrace();
/* 1530 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRAW(int paramInt, RAW paramRAW)
/*      */     throws SQLException
/*      */   {
/* 1539 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1540 */     localSQLException.fillInStackTrace();
/* 1541 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRAWAtName(String paramString, RAW paramRAW)
/*      */     throws SQLException
/*      */   {
/* 1549 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1550 */     localSQLException.fillInStackTrace();
/* 1551 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRAW(String paramString, RAW paramRAW)
/*      */     throws SQLException
/*      */   {
/* 1559 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1560 */     localSQLException.fillInStackTrace();
/* 1561 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setREF(int paramInt, REF paramREF)
/*      */     throws SQLException
/*      */   {
/* 1570 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1571 */     localSQLException.fillInStackTrace();
/* 1572 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setREFAtName(String paramString, REF paramREF)
/*      */     throws SQLException
/*      */   {
/* 1580 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1581 */     localSQLException.fillInStackTrace();
/* 1582 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setREF(String paramString, REF paramREF)
/*      */     throws SQLException
/*      */   {
/* 1590 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1591 */     localSQLException.fillInStackTrace();
/* 1592 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRefType(int paramInt, REF paramREF)
/*      */     throws SQLException
/*      */   {
/* 1601 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1602 */     localSQLException.fillInStackTrace();
/* 1603 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRefTypeAtName(String paramString, REF paramREF)
/*      */     throws SQLException
/*      */   {
/* 1611 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1612 */     localSQLException.fillInStackTrace();
/* 1613 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRefType(String paramString, REF paramREF)
/*      */     throws SQLException
/*      */   {
/* 1621 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1622 */     localSQLException.fillInStackTrace();
/* 1623 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setROWID(int paramInt, ROWID paramROWID)
/*      */     throws SQLException
/*      */   {
/* 1632 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1633 */     localSQLException.fillInStackTrace();
/* 1634 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setROWIDAtName(String paramString, ROWID paramROWID)
/*      */     throws SQLException
/*      */   {
/* 1642 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1643 */     localSQLException.fillInStackTrace();
/* 1644 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setROWID(String paramString, ROWID paramROWID)
/*      */     throws SQLException
/*      */   {
/* 1652 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1653 */     localSQLException.fillInStackTrace();
/* 1654 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSTRUCT(int paramInt, STRUCT paramSTRUCT)
/*      */     throws SQLException
/*      */   {
/* 1663 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1664 */     localSQLException.fillInStackTrace();
/* 1665 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSTRUCTAtName(String paramString, STRUCT paramSTRUCT)
/*      */     throws SQLException
/*      */   {
/* 1673 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1674 */     localSQLException.fillInStackTrace();
/* 1675 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSTRUCT(String paramString, STRUCT paramSTRUCT)
/*      */     throws SQLException
/*      */   {
/* 1683 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1684 */     localSQLException.fillInStackTrace();
/* 1685 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPLTZ(int paramInt, TIMESTAMPLTZ paramTIMESTAMPLTZ)
/*      */     throws SQLException
/*      */   {
/* 1694 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1695 */     localSQLException.fillInStackTrace();
/* 1696 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPLTZAtName(String paramString, TIMESTAMPLTZ paramTIMESTAMPLTZ)
/*      */     throws SQLException
/*      */   {
/* 1704 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1705 */     localSQLException.fillInStackTrace();
/* 1706 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPLTZ(String paramString, TIMESTAMPLTZ paramTIMESTAMPLTZ)
/*      */     throws SQLException
/*      */   {
/* 1714 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1715 */     localSQLException.fillInStackTrace();
/* 1716 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPTZ(int paramInt, TIMESTAMPTZ paramTIMESTAMPTZ)
/*      */     throws SQLException
/*      */   {
/* 1725 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1726 */     localSQLException.fillInStackTrace();
/* 1727 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPTZAtName(String paramString, TIMESTAMPTZ paramTIMESTAMPTZ)
/*      */     throws SQLException
/*      */   {
/* 1735 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1736 */     localSQLException.fillInStackTrace();
/* 1737 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPTZ(String paramString, TIMESTAMPTZ paramTIMESTAMPTZ)
/*      */     throws SQLException
/*      */   {
/* 1745 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1746 */     localSQLException.fillInStackTrace();
/* 1747 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMP(int paramInt, TIMESTAMP paramTIMESTAMP)
/*      */     throws SQLException
/*      */   {
/* 1756 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1757 */     localSQLException.fillInStackTrace();
/* 1758 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPAtName(String paramString, TIMESTAMP paramTIMESTAMP)
/*      */     throws SQLException
/*      */   {
/* 1766 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1767 */     localSQLException.fillInStackTrace();
/* 1768 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMP(String paramString, TIMESTAMP paramTIMESTAMP)
/*      */     throws SQLException
/*      */   {
/* 1776 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1777 */     localSQLException.fillInStackTrace();
/* 1778 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 1787 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1788 */     localSQLException.fillInStackTrace();
/* 1789 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlobAtName(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 1797 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1798 */     localSQLException.fillInStackTrace();
/* 1799 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 1807 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1808 */     localSQLException.fillInStackTrace();
/* 1809 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1818 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1819 */     localSQLException.fillInStackTrace();
/* 1820 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlobAtName(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1828 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1829 */     localSQLException.fillInStackTrace();
/* 1830 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1838 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1839 */     localSQLException.fillInStackTrace();
/* 1840 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 1849 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1850 */     localSQLException.fillInStackTrace();
/* 1851 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClobAtName(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 1859 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1860 */     localSQLException.fillInStackTrace();
/* 1861 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 1869 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1870 */     localSQLException.fillInStackTrace();
/* 1871 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1880 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1881 */     localSQLException.fillInStackTrace();
/* 1882 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClobAtName(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1890 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1891 */     localSQLException.fillInStackTrace();
/* 1892 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1900 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1901 */     localSQLException.fillInStackTrace();
/* 1902 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 1911 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1912 */     localSQLException.fillInStackTrace();
/* 1913 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClobAtName(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 1921 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1922 */     localSQLException.fillInStackTrace();
/* 1923 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 1931 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1932 */     localSQLException.fillInStackTrace();
/* 1933 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1942 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1943 */     localSQLException.fillInStackTrace();
/* 1944 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClobAtName(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1952 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1953 */     localSQLException.fillInStackTrace();
/* 1954 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1962 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1963 */     localSQLException.fillInStackTrace();
/* 1964 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 1973 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1974 */     localSQLException.fillInStackTrace();
/* 1975 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStreamAtName(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 1983 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1984 */     localSQLException.fillInStackTrace();
/* 1985 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 1993 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1994 */     localSQLException.fillInStackTrace();
/* 1995 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2004 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2005 */     localSQLException.fillInStackTrace();
/* 2006 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStreamAtName(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2014 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2015 */     localSQLException.fillInStackTrace();
/* 2016 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2024 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2025 */     localSQLException.fillInStackTrace();
/* 2026 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2035 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2036 */     localSQLException.fillInStackTrace();
/* 2037 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStreamAtName(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2045 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2046 */     localSQLException.fillInStackTrace();
/* 2047 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2055 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2056 */     localSQLException.fillInStackTrace();
/* 2057 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 2066 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2067 */     localSQLException.fillInStackTrace();
/* 2068 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStreamAtName(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 2076 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2077 */     localSQLException.fillInStackTrace();
/* 2078 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 2086 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2087 */     localSQLException.fillInStackTrace();
/* 2088 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2097 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2098 */     localSQLException.fillInStackTrace();
/* 2099 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStreamAtName(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2107 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2108 */     localSQLException.fillInStackTrace();
/* 2109 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2117 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2118 */     localSQLException.fillInStackTrace();
/* 2119 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2128 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2129 */     localSQLException.fillInStackTrace();
/* 2130 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStreamAtName(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2138 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2139 */     localSQLException.fillInStackTrace();
/* 2140 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2148 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2149 */     localSQLException.fillInStackTrace();
/* 2150 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 2159 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2160 */     localSQLException.fillInStackTrace();
/* 2161 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStreamAtName(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 2169 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2170 */     localSQLException.fillInStackTrace();
/* 2171 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 2179 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2180 */     localSQLException.fillInStackTrace();
/* 2181 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(int paramInt1, Reader paramReader, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2190 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2191 */     localSQLException.fillInStackTrace();
/* 2192 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStreamAtName(String paramString, Reader paramReader, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2200 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2201 */     localSQLException.fillInStackTrace();
/* 2202 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2210 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2211 */     localSQLException.fillInStackTrace();
/* 2212 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2221 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2222 */     localSQLException.fillInStackTrace();
/* 2223 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStreamAtName(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2231 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2232 */     localSQLException.fillInStackTrace();
/* 2233 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2241 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2242 */     localSQLException.fillInStackTrace();
/* 2243 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 2252 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2253 */     localSQLException.fillInStackTrace();
/* 2254 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNCharacterStreamAtName(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 2262 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2263 */     localSQLException.fillInStackTrace();
/* 2264 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 2272 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2273 */     localSQLException.fillInStackTrace();
/* 2274 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2283 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2284 */     localSQLException.fillInStackTrace();
/* 2285 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNCharacterStreamAtName(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2293 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2294 */     localSQLException.fillInStackTrace();
/* 2295 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2303 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2304 */     localSQLException.fillInStackTrace();
/* 2305 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setUnicodeStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2314 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2315 */     localSQLException.fillInStackTrace();
/* 2316 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setUnicodeStreamAtName(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2324 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2325 */     localSQLException.fillInStackTrace();
/* 2326 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setUnicodeStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2334 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2335 */     localSQLException.fillInStackTrace();
/* 2336 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Array getArray(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2347 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2348 */     localSQLException.fillInStackTrace();
/* 2349 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Array getArray(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2357 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2358 */     localSQLException.fillInStackTrace();
/* 2359 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2367 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2368 */     localSQLException.fillInStackTrace();
/* 2369 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2377 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2378 */     localSQLException.fillInStackTrace();
/* 2379 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2387 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2388 */     localSQLException.fillInStackTrace();
/* 2389 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2397 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2398 */     localSQLException.fillInStackTrace();
/* 2399 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Blob getBlob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2407 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2408 */     localSQLException.fillInStackTrace();
/* 2409 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Blob getBlob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2417 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2418 */     localSQLException.fillInStackTrace();
/* 2419 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getBoolean(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2427 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2428 */     localSQLException.fillInStackTrace();
/* 2429 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getBoolean(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2437 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2438 */     localSQLException.fillInStackTrace();
/* 2439 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte getByte(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2447 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2448 */     localSQLException.fillInStackTrace();
/* 2449 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte getByte(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2457 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2458 */     localSQLException.fillInStackTrace();
/* 2459 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte[] getBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2467 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2468 */     localSQLException.fillInStackTrace();
/* 2469 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte[] getBytes(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2477 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2478 */     localSQLException.fillInStackTrace();
/* 2479 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Clob getClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2487 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2488 */     localSQLException.fillInStackTrace();
/* 2489 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Clob getClob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2497 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2498 */     localSQLException.fillInStackTrace();
/* 2499 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Date getDate(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2507 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2508 */     localSQLException.fillInStackTrace();
/* 2509 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Date getDate(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2517 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2518 */     localSQLException.fillInStackTrace();
/* 2519 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Date getDate(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 2527 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2528 */     localSQLException.fillInStackTrace();
/* 2529 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Date getDate(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 2537 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2538 */     localSQLException.fillInStackTrace();
/* 2539 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getDouble(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2547 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2548 */     localSQLException.fillInStackTrace();
/* 2549 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getDouble(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2557 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2558 */     localSQLException.fillInStackTrace();
/* 2559 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public float getFloat(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2567 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2568 */     localSQLException.fillInStackTrace();
/* 2569 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public float getFloat(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2577 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2578 */     localSQLException.fillInStackTrace();
/* 2579 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getInt(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2587 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2588 */     localSQLException.fillInStackTrace();
/* 2589 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getInt(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2597 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2598 */     localSQLException.fillInStackTrace();
/* 2599 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public long getLong(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2607 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2608 */     localSQLException.fillInStackTrace();
/* 2609 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public long getLong(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2617 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2618 */     localSQLException.fillInStackTrace();
/* 2619 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public NClob getNClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2627 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2628 */     localSQLException.fillInStackTrace();
/* 2629 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public NClob getNClob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2637 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2638 */     localSQLException.fillInStackTrace();
/* 2639 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getNString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2647 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2648 */     localSQLException.fillInStackTrace();
/* 2649 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getNString(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2657 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2658 */     localSQLException.fillInStackTrace();
/* 2659 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2667 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2668 */     localSQLException.fillInStackTrace();
/* 2669 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getObject(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2677 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2678 */     localSQLException.fillInStackTrace();
/* 2679 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getObject(int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 2687 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2688 */     localSQLException.fillInStackTrace();
/* 2689 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getObject(String paramString, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 2697 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2698 */     localSQLException.fillInStackTrace();
/* 2699 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Ref getRef(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2707 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2708 */     localSQLException.fillInStackTrace();
/* 2709 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Ref getRef(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2717 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2718 */     localSQLException.fillInStackTrace();
/* 2719 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public RowId getRowId(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2727 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2728 */     localSQLException.fillInStackTrace();
/* 2729 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public RowId getRowId(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2737 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2738 */     localSQLException.fillInStackTrace();
/* 2739 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public short getShort(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2747 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2748 */     localSQLException.fillInStackTrace();
/* 2749 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public short getShort(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2757 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2758 */     localSQLException.fillInStackTrace();
/* 2759 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public SQLXML getSQLXML(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2767 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2768 */     localSQLException.fillInStackTrace();
/* 2769 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public SQLXML getSQLXML(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2777 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2778 */     localSQLException.fillInStackTrace();
/* 2779 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2787 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2788 */     localSQLException.fillInStackTrace();
/* 2789 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getString(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2797 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2798 */     localSQLException.fillInStackTrace();
/* 2799 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Time getTime(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2807 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2808 */     localSQLException.fillInStackTrace();
/* 2809 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Time getTime(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2817 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2818 */     localSQLException.fillInStackTrace();
/* 2819 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Time getTime(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 2827 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2828 */     localSQLException.fillInStackTrace();
/* 2829 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Time getTime(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 2837 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2838 */     localSQLException.fillInStackTrace();
/* 2839 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2847 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2848 */     localSQLException.fillInStackTrace();
/* 2849 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2857 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2858 */     localSQLException.fillInStackTrace();
/* 2859 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 2867 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2868 */     localSQLException.fillInStackTrace();
/* 2869 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 2877 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2878 */     localSQLException.fillInStackTrace();
/* 2879 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public URL getURL(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2887 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2888 */     localSQLException.fillInStackTrace();
/* 2889 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public URL getURL(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2897 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2898 */     localSQLException.fillInStackTrace();
/* 2899 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ARRAY getARRAY(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2907 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2908 */     localSQLException.fillInStackTrace();
/* 2909 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ARRAY getARRAY(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2917 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2918 */     localSQLException.fillInStackTrace();
/* 2919 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BFILE getBFILE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2927 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2928 */     localSQLException.fillInStackTrace();
/* 2929 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BFILE getBFILE(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2937 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2938 */     localSQLException.fillInStackTrace();
/* 2939 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BFILE getBfile(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2947 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2948 */     localSQLException.fillInStackTrace();
/* 2949 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BFILE getBfile(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2957 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2958 */     localSQLException.fillInStackTrace();
/* 2959 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BLOB getBLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2967 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2968 */     localSQLException.fillInStackTrace();
/* 2969 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BLOB getBLOB(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2977 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2978 */     localSQLException.fillInStackTrace();
/* 2979 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public CHAR getCHAR(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2987 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2988 */     localSQLException.fillInStackTrace();
/* 2989 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public CHAR getCHAR(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2997 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2998 */     localSQLException.fillInStackTrace();
/* 2999 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public CLOB getCLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3007 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3008 */     localSQLException.fillInStackTrace();
/* 3009 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public CLOB getCLOB(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3017 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3018 */     localSQLException.fillInStackTrace();
/* 3019 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSet getCursor(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3027 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3028 */     localSQLException.fillInStackTrace();
/* 3029 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSet getCursor(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3037 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3038 */     localSQLException.fillInStackTrace();
/* 3039 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public CustomDatum getCustomDatum(int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */     throws SQLException
/*      */   {
/* 3047 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3048 */     localSQLException.fillInStackTrace();
/* 3049 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public CustomDatum getCustomDatum(String paramString, CustomDatumFactory paramCustomDatumFactory)
/*      */     throws SQLException
/*      */   {
/* 3057 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3058 */     localSQLException.fillInStackTrace();
/* 3059 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public DATE getDATE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3067 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3068 */     localSQLException.fillInStackTrace();
/* 3069 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public DATE getDATE(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3077 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3078 */     localSQLException.fillInStackTrace();
/* 3079 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public INTERVALDS getINTERVALDS(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3087 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3088 */     localSQLException.fillInStackTrace();
/* 3089 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public INTERVALDS getINTERVALDS(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3097 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3098 */     localSQLException.fillInStackTrace();
/* 3099 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public INTERVALYM getINTERVALYM(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3107 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3108 */     localSQLException.fillInStackTrace();
/* 3109 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public INTERVALYM getINTERVALYM(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3117 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3118 */     localSQLException.fillInStackTrace();
/* 3119 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public NUMBER getNUMBER(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3127 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3128 */     localSQLException.fillInStackTrace();
/* 3129 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public NUMBER getNUMBER(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3137 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3138 */     localSQLException.fillInStackTrace();
/* 3139 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public OPAQUE getOPAQUE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3147 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3148 */     localSQLException.fillInStackTrace();
/* 3149 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public OPAQUE getOPAQUE(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3157 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3158 */     localSQLException.fillInStackTrace();
/* 3159 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Datum getOracleObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3167 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3168 */     localSQLException.fillInStackTrace();
/* 3169 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Datum getOracleObject(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3177 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3178 */     localSQLException.fillInStackTrace();
/* 3179 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ORAData getORAData(int paramInt, ORADataFactory paramORADataFactory)
/*      */     throws SQLException
/*      */   {
/* 3187 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3188 */     localSQLException.fillInStackTrace();
/* 3189 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ORAData getORAData(String paramString, ORADataFactory paramORADataFactory)
/*      */     throws SQLException
/*      */   {
/* 3197 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3198 */     localSQLException.fillInStackTrace();
/* 3199 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getObject(int paramInt, OracleDataFactory paramOracleDataFactory)
/*      */     throws SQLException
/*      */   {
/* 3207 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3208 */     localSQLException.fillInStackTrace();
/* 3209 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getObject(String paramString, OracleDataFactory paramOracleDataFactory)
/*      */     throws SQLException
/*      */   {
/* 3217 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3218 */     localSQLException.fillInStackTrace();
/* 3219 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public RAW getRAW(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3227 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3228 */     localSQLException.fillInStackTrace();
/* 3229 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public RAW getRAW(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3237 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3238 */     localSQLException.fillInStackTrace();
/* 3239 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public REF getREF(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3247 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3248 */     localSQLException.fillInStackTrace();
/* 3249 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public REF getREF(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3257 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3258 */     localSQLException.fillInStackTrace();
/* 3259 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ROWID getROWID(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3267 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3268 */     localSQLException.fillInStackTrace();
/* 3269 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ROWID getROWID(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3277 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3278 */     localSQLException.fillInStackTrace();
/* 3279 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public STRUCT getSTRUCT(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3287 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3288 */     localSQLException.fillInStackTrace();
/* 3289 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public STRUCT getSTRUCT(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3297 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3298 */     localSQLException.fillInStackTrace();
/* 3299 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public TIMESTAMPLTZ getTIMESTAMPLTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3307 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3308 */     localSQLException.fillInStackTrace();
/* 3309 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public TIMESTAMPLTZ getTIMESTAMPLTZ(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3317 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3318 */     localSQLException.fillInStackTrace();
/* 3319 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ getTIMESTAMPTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3327 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3328 */     localSQLException.fillInStackTrace();
/* 3329 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ getTIMESTAMPTZ(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3337 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3338 */     localSQLException.fillInStackTrace();
/* 3339 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public TIMESTAMP getTIMESTAMP(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3347 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3348 */     localSQLException.fillInStackTrace();
/* 3349 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public TIMESTAMP getTIMESTAMP(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3357 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3358 */     localSQLException.fillInStackTrace();
/* 3359 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public InputStream getAsciiStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3367 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3368 */     localSQLException.fillInStackTrace();
/* 3369 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public InputStream getAsciiStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3377 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3378 */     localSQLException.fillInStackTrace();
/* 3379 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public InputStream getBinaryStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3387 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3388 */     localSQLException.fillInStackTrace();
/* 3389 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public InputStream getBinaryStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3397 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3398 */     localSQLException.fillInStackTrace();
/* 3399 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Reader getCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3407 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3408 */     localSQLException.fillInStackTrace();
/* 3409 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Reader getCharacterStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3417 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3418 */     localSQLException.fillInStackTrace();
/* 3419 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Reader getNCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3427 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3428 */     localSQLException.fillInStackTrace();
/* 3429 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Reader getNCharacterStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3437 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3438 */     localSQLException.fillInStackTrace();
/* 3439 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public InputStream getUnicodeStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3447 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3448 */     localSQLException.fillInStackTrace();
/* 3449 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public InputStream getUnicodeStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3457 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3458 */     localSQLException.fillInStackTrace();
/* 3459 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNull(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3468 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3469 */     localSQLException.fillInStackTrace();
/* 3470 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNull(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3477 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3478 */     localSQLException.fillInStackTrace();
/* 3479 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNull(int paramInt1, int paramInt2, String paramString)
/*      */     throws SQLException
/*      */   {
/* 3486 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3487 */     localSQLException.fillInStackTrace();
/* 3488 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNull(String paramString1, int paramInt, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 3495 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3496 */     localSQLException.fillInStackTrace();
/* 3497 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNullAtName(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3504 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3505 */     localSQLException.fillInStackTrace();
/* 3506 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNullAtName(String paramString1, int paramInt, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 3513 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3514 */     localSQLException.fillInStackTrace();
/* 3515 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(int paramInt1, Object paramObject, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 3526 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3527 */     localSQLException.fillInStackTrace();
/* 3528 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(String paramString, Object paramObject, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3539 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3540 */     localSQLException.fillInStackTrace();
/* 3541 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObjectAtName(String paramString, Object paramObject, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3552 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3553 */     localSQLException.fillInStackTrace();
/* 3554 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getFetchDirection()
/*      */     throws SQLException
/*      */   {
/* 3567 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3568 */     localSQLException.fillInStackTrace();
/* 3569 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getFetchSize()
/*      */     throws SQLException
/*      */   {
/* 3577 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3578 */     localSQLException.fillInStackTrace();
/* 3579 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getMaxFieldSize()
/*      */     throws SQLException
/*      */   {
/* 3587 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3588 */     localSQLException.fillInStackTrace();
/* 3589 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getMaxRows()
/*      */     throws SQLException
/*      */   {
/* 3597 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3598 */     localSQLException.fillInStackTrace();
/* 3599 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getQueryTimeout()
/*      */     throws SQLException
/*      */   {
/* 3607 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3608 */     localSQLException.fillInStackTrace();
/* 3609 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getResultSetConcurrency()
/*      */     throws SQLException
/*      */   {
/* 3617 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3618 */     localSQLException.fillInStackTrace();
/* 3619 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getResultSetHoldability()
/*      */     throws SQLException
/*      */   {
/* 3627 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3628 */     localSQLException.fillInStackTrace();
/* 3629 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getResultSetType()
/*      */     throws SQLException
/*      */   {
/* 3637 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3638 */     localSQLException.fillInStackTrace();
/* 3639 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getUpdateCount()
/*      */     throws SQLException
/*      */   {
/* 3647 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3648 */     localSQLException.fillInStackTrace();
/* 3649 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void cancel()
/*      */     throws SQLException
/*      */   {
/* 3657 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3658 */     localSQLException.fillInStackTrace();
/* 3659 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void clearBatch()
/*      */     throws SQLException
/*      */   {
/* 3667 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3668 */     localSQLException.fillInStackTrace();
/* 3669 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void clearWarnings()
/*      */     throws SQLException
/*      */   {
/* 3677 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3678 */     localSQLException.fillInStackTrace();
/* 3679 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void close()
/*      */     throws SQLException
/*      */   {}
/*      */   
/*      */ 
/*      */   public boolean getMoreResults()
/*      */     throws SQLException
/*      */   {
/* 3692 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3693 */     localSQLException.fillInStackTrace();
/* 3694 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int[] executeBatch()
/*      */     throws SQLException
/*      */   {
/* 3702 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3703 */     localSQLException.fillInStackTrace();
/* 3704 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFetchDirection(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3712 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3713 */     localSQLException.fillInStackTrace();
/* 3714 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFetchSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3722 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3723 */     localSQLException.fillInStackTrace();
/* 3724 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setMaxFieldSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3732 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3733 */     localSQLException.fillInStackTrace();
/* 3734 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setMaxRows(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3742 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3743 */     localSQLException.fillInStackTrace();
/* 3744 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setQueryTimeout(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3752 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3753 */     localSQLException.fillInStackTrace();
/* 3754 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getMoreResults(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3762 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3763 */     localSQLException.fillInStackTrace();
/* 3764 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setEscapeProcessing(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 3772 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3773 */     localSQLException.fillInStackTrace();
/* 3774 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int executeUpdate(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3782 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3783 */     localSQLException.fillInStackTrace();
/* 3784 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void addBatch(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3792 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3793 */     localSQLException.fillInStackTrace();
/* 3794 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCursorName(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3802 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3803 */     localSQLException.fillInStackTrace();
/* 3804 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean execute(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3812 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3813 */     localSQLException.fillInStackTrace();
/* 3814 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int executeUpdate(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3822 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3823 */     localSQLException.fillInStackTrace();
/* 3824 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean execute(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3832 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3833 */     localSQLException.fillInStackTrace();
/* 3834 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int executeUpdate(String paramString, int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/* 3842 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3843 */     localSQLException.fillInStackTrace();
/* 3844 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean execute(String paramString, int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/* 3852 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3853 */     localSQLException.fillInStackTrace();
/* 3854 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Connection getConnection()
/*      */     throws SQLException
/*      */   {
/* 3862 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3863 */     localSQLException.fillInStackTrace();
/* 3864 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSet getGeneratedKeys()
/*      */     throws SQLException
/*      */   {
/* 3872 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3873 */     localSQLException.fillInStackTrace();
/* 3874 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSet getResultSet()
/*      */     throws SQLException
/*      */   {
/* 3882 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3883 */     localSQLException.fillInStackTrace();
/* 3884 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public SQLWarning getWarnings()
/*      */     throws SQLException
/*      */   {
/* 3892 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3893 */     localSQLException.fillInStackTrace();
/* 3894 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int executeUpdate(String paramString, String[] paramArrayOfString)
/*      */     throws SQLException
/*      */   {
/* 3902 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3903 */     localSQLException.fillInStackTrace();
/* 3904 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean execute(String paramString, String[] paramArrayOfString)
/*      */     throws SQLException
/*      */   {
/* 3912 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3913 */     localSQLException.fillInStackTrace();
/* 3914 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSet executeQuery(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3922 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3923 */     localSQLException.fillInStackTrace();
/* 3924 */     throw localSQLException;
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
/*      */   public void clearDefines()
/*      */     throws SQLException
/*      */   {
/* 3938 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3939 */     localSQLException.fillInStackTrace();
/* 3940 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void defineColumnType(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3948 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3949 */     localSQLException.fillInStackTrace();
/* 3950 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void defineColumnType(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 3958 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3959 */     localSQLException.fillInStackTrace();
/* 3960 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void defineColumnType(int paramInt1, int paramInt2, int paramInt3, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 3968 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3969 */     localSQLException.fillInStackTrace();
/* 3970 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void defineColumnTypeBytes(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 3978 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3979 */     localSQLException.fillInStackTrace();
/* 3980 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void defineColumnTypeChars(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 3988 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3989 */     localSQLException.fillInStackTrace();
/* 3990 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void defineColumnType(int paramInt1, int paramInt2, String paramString)
/*      */     throws SQLException
/*      */   {
/* 3998 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 3999 */     localSQLException.fillInStackTrace();
/* 4000 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getRowPrefetch()
/*      */   {
/* 4006 */     return -1;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setResultSetCache(OracleResultSetCache paramOracleResultSetCache)
/*      */     throws SQLException
/*      */   {
/* 4013 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4014 */     localSQLException.fillInStackTrace();
/* 4015 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRowPrefetch(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4023 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4024 */     localSQLException.fillInStackTrace();
/* 4025 */     throw localSQLException;
/*      */   }
/*      */   
/*      */   public int getLobPrefetchSize()
/*      */   {
/* 4030 */     return -1;
/*      */   }
/*      */   
/*      */   public void setLobPrefetchSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4036 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4037 */     localSQLException.fillInStackTrace();
/* 4038 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void closeWithKey(String paramString)
/*      */     throws SQLException
/*      */   {
/* 4046 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4047 */     localSQLException.fillInStackTrace();
/* 4048 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */   public int creationState()
/*      */   {
/* 4054 */     return -1;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isNCHAR(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4061 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4062 */     localSQLException.fillInStackTrace();
/* 4063 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int executeUpdate()
/*      */     throws SQLException
/*      */   {
/* 4075 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4076 */     localSQLException.fillInStackTrace();
/* 4077 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void addBatch()
/*      */     throws SQLException
/*      */   {
/* 4085 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4086 */     localSQLException.fillInStackTrace();
/* 4087 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void clearParameters()
/*      */     throws SQLException
/*      */   {
/* 4095 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4096 */     localSQLException.fillInStackTrace();
/* 4097 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean execute()
/*      */     throws SQLException
/*      */   {
/* 4105 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4106 */     localSQLException.fillInStackTrace();
/* 4107 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ParameterMetaData getParameterMetaData()
/*      */     throws SQLException
/*      */   {
/* 4115 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4116 */     localSQLException.fillInStackTrace();
/* 4117 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSet executeQuery()
/*      */     throws SQLException
/*      */   {
/* 4125 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4126 */     localSQLException.fillInStackTrace();
/* 4127 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSetMetaData getMetaData()
/*      */     throws SQLException
/*      */   {
/* 4135 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4136 */     localSQLException.fillInStackTrace();
/* 4137 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSet getReturnResultSet()
/*      */     throws SQLException
/*      */   {
/* 4145 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4146 */     localSQLException.fillInStackTrace();
/* 4147 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void defineParameterTypeBytes(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 4158 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4159 */     localSQLException.fillInStackTrace();
/* 4160 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void defineParameterTypeChars(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 4168 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4169 */     localSQLException.fillInStackTrace();
/* 4170 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void defineParameterType(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 4178 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4179 */     localSQLException.fillInStackTrace();
/* 4180 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getExecuteBatch()
/*      */   {
/* 4186 */     return -1;
/*      */   }
/*      */   
/*      */ 
/*      */   public int sendBatch()
/*      */     throws SQLException
/*      */   {
/* 4193 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4194 */     localSQLException.fillInStackTrace();
/* 4195 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setExecuteBatch(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4203 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4204 */     localSQLException.fillInStackTrace();
/* 4205 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setPlsqlIndexTable(int paramInt1, Object paramObject, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*      */     throws SQLException
/*      */   {
/* 4213 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4214 */     localSQLException.fillInStackTrace();
/* 4215 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFormOfUse(int paramInt, short paramShort) {}
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDisableStmtCaching(boolean paramBoolean) {}
/*      */   
/*      */ 
/*      */ 
/*      */   public OracleParameterMetaData OracleGetParameterMetaData()
/*      */     throws SQLException
/*      */   {
/* 4231 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4232 */     localSQLException.fillInStackTrace();
/* 4233 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerReturnParameter(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 4241 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4242 */     localSQLException.fillInStackTrace();
/* 4243 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerReturnParameter(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 4251 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4252 */     localSQLException.fillInStackTrace();
/* 4253 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerReturnParameter(int paramInt1, int paramInt2, String paramString)
/*      */     throws SQLException
/*      */   {
/* 4261 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4262 */     localSQLException.fillInStackTrace();
/* 4263 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBytesForBlob(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 4271 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4272 */     localSQLException.fillInStackTrace();
/* 4273 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBytesForBlobAtName(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 4281 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4282 */     localSQLException.fillInStackTrace();
/* 4283 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setStringForClob(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 4292 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4293 */     localSQLException.fillInStackTrace();
/* 4294 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setStringForClobAtName(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 4302 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4303 */     localSQLException.fillInStackTrace();
/* 4304 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setStructDescriptor(int paramInt, StructDescriptor paramStructDescriptor)
/*      */     throws SQLException
/*      */   {
/* 4313 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4314 */     localSQLException.fillInStackTrace();
/* 4315 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setStructDescriptor(String paramString, StructDescriptor paramStructDescriptor)
/*      */     throws SQLException
/*      */   {
/* 4324 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4325 */     localSQLException.fillInStackTrace();
/* 4326 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setStructDescriptorAtName(String paramString, StructDescriptor paramStructDescriptor)
/*      */     throws SQLException
/*      */   {
/* 4335 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4336 */     localSQLException.fillInStackTrace();
/* 4337 */     throw localSQLException;
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
/*      */   public Object getAnyDataEmbeddedObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4351 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4352 */     localSQLException.fillInStackTrace();
/* 4353 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/* 4361 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4362 */     localSQLException.fillInStackTrace();
/* 4363 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(int paramInt1, int paramInt2, String paramString)
/*      */     throws SQLException
/*      */   {
/* 4371 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4372 */     localSQLException.fillInStackTrace();
/* 4373 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 4381 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4382 */     localSQLException.fillInStackTrace();
/* 4383 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 4391 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4392 */     localSQLException.fillInStackTrace();
/* 4393 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameterBytes(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/* 4401 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4402 */     localSQLException.fillInStackTrace();
/* 4403 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameterChars(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/* 4411 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4412 */     localSQLException.fillInStackTrace();
/* 4413 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getPlsqlIndexTable(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4421 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4422 */     localSQLException.fillInStackTrace();
/* 4423 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getPlsqlIndexTable(int paramInt, Class paramClass)
/*      */     throws SQLException
/*      */   {
/* 4431 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4432 */     localSQLException.fillInStackTrace();
/* 4433 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Datum[] getOraclePlsqlIndexTable(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4441 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4442 */     localSQLException.fillInStackTrace();
/* 4443 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerIndexTableOutParameter(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/* 4451 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4452 */     localSQLException.fillInStackTrace();
/* 4453 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setStringForClob(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 4461 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4462 */     localSQLException.fillInStackTrace();
/* 4463 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBytesForBlob(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 4471 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4472 */     localSQLException.fillInStackTrace();
/* 4473 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(String paramString, int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 4481 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4482 */     localSQLException.fillInStackTrace();
/* 4483 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean wasNull()
/*      */     throws SQLException
/*      */   {
/* 4492 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4493 */     localSQLException.fillInStackTrace();
/* 4494 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4502 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4503 */     localSQLException.fillInStackTrace();
/* 4504 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(String paramString, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 4512 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4513 */     localSQLException.fillInStackTrace();
/* 4514 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(String paramString1, int paramInt, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 4522 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4523 */     localSQLException.fillInStackTrace();
/* 4524 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte[] privateGetBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4532 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4533 */     localSQLException.fillInStackTrace();
/* 4534 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDatabaseChangeRegistration(DatabaseChangeRegistration paramDatabaseChangeRegistration)
/*      */     throws SQLException
/*      */   {
/* 4543 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4544 */     localSQLException.fillInStackTrace();
/* 4545 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isClosed()
/*      */     throws SQLException
/*      */   {
/* 4557 */     return true;
/*      */   }
/*      */   
/*      */   public boolean isPoolable()
/*      */     throws SQLException
/*      */   {
/* 4563 */     return false;
/*      */   }
/*      */   
/*      */   public void setPoolable(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 4569 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4570 */     localSQLException.fillInStackTrace();
/* 4571 */     throw localSQLException;
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
/*      */   public boolean isWrapperFor(Class<?> paramClass)
/*      */     throws SQLException
/*      */   {
/* 4587 */     if (paramClass.isInterface()) { return paramClass.isInstance(this);
/*      */     }
/* 4589 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 4590 */     localSQLException.fillInStackTrace();
/* 4591 */     throw localSQLException;
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
/*      */   public <T> T unwrap(Class<T> paramClass)
/*      */     throws SQLException
/*      */   {
/* 4609 */     if ((paramClass.isInterface()) && (paramClass.isInstance(this))) { return this;
/*      */     }
/* 4611 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 4612 */     localSQLException.fillInStackTrace();
/* 4613 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFixedString(boolean paramBoolean) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getFixedString()
/*      */   {
/* 4630 */     return false;
/*      */   }
/*      */   
/*      */   public boolean getserverCursor()
/*      */   {
/* 4635 */     return false;
/*      */   }
/*      */   
/*      */   public int getcacheState()
/*      */   {
/* 4640 */     return 0;
/*      */   }
/*      */   
/*      */   public int getstatementType()
/*      */   {
/* 4645 */     return 3;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCheckBindTypes(boolean paramBoolean) {}
/*      */   
/*      */ 
/*      */   public void setInternalBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 4656 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4657 */     localSQLException.fillInStackTrace();
/* 4658 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void enterImplicitCache()
/*      */     throws SQLException
/*      */   {
/* 4666 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4667 */     localSQLException.fillInStackTrace();
/* 4668 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void enterExplicitCache()
/*      */     throws SQLException
/*      */   {
/* 4676 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4677 */     localSQLException.fillInStackTrace();
/* 4678 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void exitImplicitCacheToActive()
/*      */     throws SQLException
/*      */   {
/* 4686 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4687 */     localSQLException.fillInStackTrace();
/* 4688 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void exitExplicitCacheToActive()
/*      */     throws SQLException
/*      */   {
/* 4696 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4697 */     localSQLException.fillInStackTrace();
/* 4698 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void exitImplicitCacheToClose()
/*      */     throws SQLException
/*      */   {
/* 4706 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4707 */     localSQLException.fillInStackTrace();
/* 4708 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void exitExplicitCacheToClose()
/*      */     throws SQLException
/*      */   {
/* 4716 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4717 */     localSQLException.fillInStackTrace();
/* 4718 */     throw localSQLException;
/*      */   }
/*      */   
/*      */   public String[] getRegisteredTableNames() throws SQLException
/*      */   {
/* 4723 */     return null;
/*      */   }
/*      */   
/*      */   public long getRegisteredQueryId() throws SQLException {
/* 4727 */     return -1L;
/*      */   }
/*      */   
/*      */   public String getOriginalSql() throws SQLException
/*      */   {
/* 4732 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4733 */     localSQLException.fillInStackTrace();
/* 4734 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSnapshotSCN(long paramLong)
/*      */     throws SQLException
/*      */   {
/* 4741 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4742 */     localSQLException.fillInStackTrace();
/* 4743 */     throw localSQLException;
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
/* 4758 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public OracleStatement.SqlKind getSqlKind()
/*      */     throws SQLException
/*      */   {
/* 4766 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4767 */     localSQLException.fillInStackTrace();
/* 4768 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getChecksum()
/*      */     throws SQLException
/*      */   {
/* 4777 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4778 */     localSQLException.fillInStackTrace();
/* 4779 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 4785 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleClosedStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */