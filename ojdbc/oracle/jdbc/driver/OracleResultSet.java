/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.URL;
/*      */ import java.sql.Array;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Date;
/*      */ import java.sql.NClob;
/*      */ import java.sql.Ref;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.RowId;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.SQLXML;
/*      */ import java.sql.Statement;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.Map;
/*      */ import oracle.jdbc.OracleDataFactory;
/*      */ import oracle.jdbc.OracleResultSet.AuthorizationIndicator;
/*      */ import oracle.jdbc.internal.OracleConnection;
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
/*      */ abstract class OracleResultSet
/*      */   implements oracle.jdbc.internal.OracleResultSet
/*      */ {
/*      */   static final boolean DEBUG = false;
/*      */   
/*      */   int getFirstUserColumnIndex()
/*      */     throws SQLException
/*      */   {
/*   87 */     return 0;
/*      */   }
/*      */   
/*      */   public abstract void closeStatementOnClose()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract Array getArray(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract BigDecimal getBigDecimal(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract BigDecimal getBigDecimal(int paramInt1, int paramInt2)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract Blob getBlob(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract boolean getBoolean(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract byte getByte(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract byte[] getBytes(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract Clob getClob(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract Date getDate(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract Date getDate(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract double getDouble(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract float getFloat(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract int getInt(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract long getLong(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract NClob getNClob(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract String getNString(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract Object getObject(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract Object getObject(int paramInt, Map paramMap) throws SQLException;
/*      */   
/*      */   public abstract Ref getRef(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract RowId getRowId(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract short getShort(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract SQLXML getSQLXML(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract String getString(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract Time getTime(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract Time getTime(int paramInt, Calendar paramCalendar) throws SQLException;
/*      */   
/*      */   public abstract Timestamp getTimestamp(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract Timestamp getTimestamp(int paramInt, Calendar paramCalendar) throws SQLException;
/*      */   
/*      */   public abstract URL getURL(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract ARRAY getARRAY(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract BFILE getBFILE(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract BFILE getBfile(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract BLOB getBLOB(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract CHAR getCHAR(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract CLOB getCLOB(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract ResultSet getCursor(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract CustomDatum getCustomDatum(int paramInt, CustomDatumFactory paramCustomDatumFactory) throws SQLException;
/*      */   
/*      */   public abstract DATE getDATE(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract INTERVALDS getINTERVALDS(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract INTERVALYM getINTERVALYM(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract NUMBER getNUMBER(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract OPAQUE getOPAQUE(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract Datum getOracleObject(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract ORAData getORAData(int paramInt, ORADataFactory paramORADataFactory) throws SQLException;
/*      */   
/*      */   public abstract Object getObject(int paramInt, OracleDataFactory paramOracleDataFactory) throws SQLException;
/*      */   
/*      */   public abstract RAW getRAW(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract REF getREF(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract ROWID getROWID(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract STRUCT getSTRUCT(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPLTZ getTIMESTAMPLTZ(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPTZ getTIMESTAMPTZ(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMP getTIMESTAMP(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract InputStream getAsciiStream(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract InputStream getBinaryStream(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract Reader getCharacterStream(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract Reader getNCharacterStream(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract InputStream getUnicodeStream(int paramInt) throws SQLException;
/*      */   
/*      */   public abstract void updateArray(int paramInt, Array paramArray) throws SQLException;
/*      */   
/*      */   public abstract void updateBigDecimal(int paramInt, BigDecimal paramBigDecimal) throws SQLException;
/*      */   
/*      */   public abstract void updateBlob(int paramInt, Blob paramBlob) throws SQLException;
/*      */   
/*      */   public abstract void updateBoolean(int paramInt, boolean paramBoolean) throws SQLException;
/*      */   
/*      */   public abstract void updateByte(int paramInt, byte paramByte) throws SQLException;
/*      */   
/*      */   public abstract void updateBytes(int paramInt, byte[] paramArrayOfByte) throws SQLException;
/*      */   
/*      */   public abstract void updateClob(int paramInt, Clob paramClob) throws SQLException;
/*      */   
/*      */   public abstract void updateDate(int paramInt, Date paramDate) throws SQLException;
/*      */   
/*      */   public abstract void updateDate(int paramInt, Date paramDate, Calendar paramCalendar) throws SQLException;
/*      */   
/*      */   public abstract void updateDouble(int paramInt, double paramDouble) throws SQLException;
/*      */   
/*      */   public abstract void updateFloat(int paramInt, float paramFloat) throws SQLException;
/*      */   
/*      */   public abstract void updateInt(int paramInt1, int paramInt2) throws SQLException;
/*      */   
/*      */   public abstract void updateLong(int paramInt, long paramLong) throws SQLException;
/*      */   
/*      */   public abstract void updateNClob(int paramInt, NClob paramNClob) throws SQLException;
/*      */   
/*      */   public abstract void updateNString(int paramInt, String paramString) throws SQLException;
/*      */   
/*      */   public abstract void updateObject(int paramInt, Object paramObject) throws SQLException;
/*      */   
/*      */   public abstract void updateObject(int paramInt1, Object paramObject, int paramInt2) throws SQLException;
/*      */   
/*      */   public abstract void updateRef(int paramInt, Ref paramRef) throws SQLException;
/*      */   
/*      */   public abstract void updateRowId(int paramInt, RowId paramRowId) throws SQLException;
/*      */   
/*      */   public abstract void updateShort(int paramInt, short paramShort) throws SQLException;
/*      */   
/*      */   public abstract void updateSQLXML(int paramInt, SQLXML paramSQLXML) throws SQLException;
/*      */   
/*      */   public abstract void updateString(int paramInt, String paramString) throws SQLException;
/*      */   
/*      */   public abstract void updateTime(int paramInt, Time paramTime) throws SQLException;
/*      */   
/*      */   public abstract void updateTime(int paramInt, Time paramTime, Calendar paramCalendar) throws SQLException;
/*      */   
/*      */   public abstract void updateTimestamp(int paramInt, Timestamp paramTimestamp) throws SQLException;
/*      */   
/*      */   public abstract void updateTimestamp(int paramInt, Timestamp paramTimestamp, Calendar paramCalendar) throws SQLException;
/*      */   
/*      */   public abstract void updateURL(int paramInt, URL paramURL) throws SQLException;
/*      */   
/*      */   public abstract void updateARRAY(int paramInt, ARRAY paramARRAY) throws SQLException;
/*      */   
/*      */   public abstract void updateBFILE(int paramInt, BFILE paramBFILE) throws SQLException;
/*      */   
/*      */   public abstract void updateBfile(int paramInt, BFILE paramBFILE) throws SQLException;
/*      */   
/*      */   public abstract void updateBinaryFloat(int paramInt, float paramFloat) throws SQLException;
/*      */   
/*      */   public abstract void updateBinaryFloat(int paramInt, BINARY_FLOAT paramBINARY_FLOAT) throws SQLException;
/*      */   
/*      */   public abstract void updateBinaryDouble(int paramInt, double paramDouble) throws SQLException;
/*      */   
/*      */   public abstract void updateBinaryDouble(int paramInt, BINARY_DOUBLE paramBINARY_DOUBLE) throws SQLException;
/*      */   
/*      */   public abstract void updateBLOB(int paramInt, BLOB paramBLOB) throws SQLException;
/*      */   
/*      */   public abstract void updateCHAR(int paramInt, CHAR paramCHAR) throws SQLException;
/*      */   
/*      */   public abstract void updateCLOB(int paramInt, CLOB paramCLOB) throws SQLException;
/*      */   
/*      */   public abstract void updateCursor(int paramInt, ResultSet paramResultSet) throws SQLException;
/*      */   
/*      */   public abstract void updateCustomDatum(int paramInt, CustomDatum paramCustomDatum) throws SQLException;
/*      */   
/*      */   public abstract void updateDATE(int paramInt, DATE paramDATE) throws SQLException;
/*      */   
/*      */   public abstract void updateFixedCHAR(int paramInt, String paramString) throws SQLException;
/*      */   
/*      */   public abstract void updateINTERVALDS(int paramInt, INTERVALDS paramINTERVALDS) throws SQLException;
/*      */   
/*      */   public abstract void updateINTERVALYM(int paramInt, INTERVALYM paramINTERVALYM) throws SQLException;
/*      */   
/*      */   public abstract void updateNUMBER(int paramInt, NUMBER paramNUMBER) throws SQLException;
/*      */   
/*      */   public abstract void updateOPAQUE(int paramInt, OPAQUE paramOPAQUE) throws SQLException;
/*      */   
/*      */   public abstract void updateOracleObject(int paramInt, Datum paramDatum) throws SQLException;
/*      */   
/*      */   public abstract void updateORAData(int paramInt, ORAData paramORAData) throws SQLException;
/*      */   
/*      */   public abstract void updateRAW(int paramInt, RAW paramRAW) throws SQLException;
/*      */   
/*      */   public abstract void updateREF(int paramInt, REF paramREF) throws SQLException;
/*      */   
/*      */   public abstract void updateRefType(int paramInt, REF paramREF) throws SQLException;
/*      */   
/*      */   public abstract void updateROWID(int paramInt, ROWID paramROWID) throws SQLException;
/*      */   
/*      */   public abstract void updateSTRUCT(int paramInt, STRUCT paramSTRUCT) throws SQLException;
/*      */   
/*      */   public abstract void updateTIMESTAMPLTZ(int paramInt, TIMESTAMPLTZ paramTIMESTAMPLTZ) throws SQLException;
/*      */   
/*      */   public abstract void updateTIMESTAMPTZ(int paramInt, TIMESTAMPTZ paramTIMESTAMPTZ) throws SQLException;
/*      */   
/*      */   public abstract void updateTIMESTAMP(int paramInt, TIMESTAMP paramTIMESTAMP) throws SQLException;
/*      */   
/*      */   public abstract void updateBlob(int paramInt, InputStream paramInputStream) throws SQLException;
/*      */   
/*      */   public abstract void updateBlob(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException;
/*      */   
/*      */   public abstract void updateClob(int paramInt, Reader paramReader) throws SQLException;
/*      */   
/*      */   public abstract void updateClob(int paramInt, Reader paramReader, long paramLong) throws SQLException;
/*      */   
/*      */   public abstract void updateNClob(int paramInt, Reader paramReader) throws SQLException;
/*      */   
/*      */   public abstract void updateNClob(int paramInt, Reader paramReader, long paramLong) throws SQLException;
/*      */   
/*      */   public abstract void updateAsciiStream(int paramInt, InputStream paramInputStream) throws SQLException;
/*      */   
/*      */   public abstract void updateAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2) throws SQLException;
/*      */   
/*      */   public abstract void updateAsciiStream(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException;
/*      */   
/*      */   public abstract void updateBinaryStream(int paramInt, InputStream paramInputStream) throws SQLException;
/*      */   
/*      */   public abstract void updateBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2) throws SQLException;
/*      */   
/*      */   public abstract void updateBinaryStream(int paramInt, InputStream paramInputStream, long paramLong) throws SQLException;
/*      */   
/*      */   public abstract void updateCharacterStream(int paramInt, Reader paramReader) throws SQLException;
/*      */   
/*      */   public abstract void updateCharacterStream(int paramInt1, Reader paramReader, int paramInt2) throws SQLException;
/*      */   
/*      */   public abstract void updateCharacterStream(int paramInt, Reader paramReader, long paramLong) throws SQLException;
/*      */   
/*      */   public abstract void updateNCharacterStream(int paramInt, Reader paramReader) throws SQLException;
/*      */   
/*      */   public abstract void updateNCharacterStream(int paramInt, Reader paramReader, long paramLong) throws SQLException;
/*      */   
/*      */   public abstract void updateUnicodeStream(int paramInt1, InputStream paramInputStream, int paramInt2) throws SQLException;
/*      */   
/*      */   public Array getArray(String paramString) throws SQLException
/*      */   {
/*  366 */     return getArray(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public BigDecimal getBigDecimal(String paramString)
/*      */     throws SQLException
/*      */   {
/*  372 */     return getBigDecimal(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public BigDecimal getBigDecimal(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  378 */     return getBigDecimal(findColumn(paramString), paramInt);
/*      */   }
/*      */   
/*      */   public Blob getBlob(String paramString)
/*      */     throws SQLException
/*      */   {
/*  384 */     return getBlob(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public boolean getBoolean(String paramString)
/*      */     throws SQLException
/*      */   {
/*  390 */     return getBoolean(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public byte getByte(String paramString)
/*      */     throws SQLException
/*      */   {
/*  396 */     return getByte(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public byte[] getBytes(String paramString)
/*      */     throws SQLException
/*      */   {
/*  402 */     return getBytes(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public Clob getClob(String paramString)
/*      */     throws SQLException
/*      */   {
/*  408 */     return getClob(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public Date getDate(String paramString)
/*      */     throws SQLException
/*      */   {
/*  414 */     return getDate(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public Date getDate(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  420 */     return getDate(findColumn(paramString), paramCalendar);
/*      */   }
/*      */   
/*      */   public double getDouble(String paramString)
/*      */     throws SQLException
/*      */   {
/*  426 */     return getDouble(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public float getFloat(String paramString)
/*      */     throws SQLException
/*      */   {
/*  432 */     return getFloat(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public int getInt(String paramString)
/*      */     throws SQLException
/*      */   {
/*  438 */     return getInt(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public long getLong(String paramString)
/*      */     throws SQLException
/*      */   {
/*  444 */     return getLong(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public NClob getNClob(String paramString)
/*      */     throws SQLException
/*      */   {
/*  450 */     return getNClob(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public String getNString(String paramString)
/*      */     throws SQLException
/*      */   {
/*  456 */     return getNString(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public Object getObject(String paramString)
/*      */     throws SQLException
/*      */   {
/*  462 */     return getObject(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public Object getObject(String paramString, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  468 */     return getObject(findColumn(paramString), paramMap);
/*      */   }
/*      */   
/*      */   public Ref getRef(String paramString)
/*      */     throws SQLException
/*      */   {
/*  474 */     return getRef(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public RowId getRowId(String paramString)
/*      */     throws SQLException
/*      */   {
/*  480 */     return getRowId(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public short getShort(String paramString)
/*      */     throws SQLException
/*      */   {
/*  486 */     return getShort(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public SQLXML getSQLXML(String paramString)
/*      */     throws SQLException
/*      */   {
/*  492 */     return getSQLXML(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public String getString(String paramString)
/*      */     throws SQLException
/*      */   {
/*  498 */     return getString(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public Time getTime(String paramString)
/*      */     throws SQLException
/*      */   {
/*  504 */     return getTime(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public Time getTime(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  510 */     return getTime(findColumn(paramString), paramCalendar);
/*      */   }
/*      */   
/*      */   public Timestamp getTimestamp(String paramString)
/*      */     throws SQLException
/*      */   {
/*  516 */     return getTimestamp(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public Timestamp getTimestamp(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  522 */     return getTimestamp(findColumn(paramString), paramCalendar);
/*      */   }
/*      */   
/*      */   public URL getURL(String paramString)
/*      */     throws SQLException
/*      */   {
/*  528 */     return getURL(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public ARRAY getARRAY(String paramString)
/*      */     throws SQLException
/*      */   {
/*  534 */     return getARRAY(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public BFILE getBFILE(String paramString)
/*      */     throws SQLException
/*      */   {
/*  540 */     return getBFILE(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public BFILE getBfile(String paramString)
/*      */     throws SQLException
/*      */   {
/*  546 */     return getBfile(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public BLOB getBLOB(String paramString)
/*      */     throws SQLException
/*      */   {
/*  552 */     return getBLOB(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public CHAR getCHAR(String paramString)
/*      */     throws SQLException
/*      */   {
/*  558 */     return getCHAR(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public CLOB getCLOB(String paramString)
/*      */     throws SQLException
/*      */   {
/*  564 */     return getCLOB(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public ResultSet getCursor(String paramString)
/*      */     throws SQLException
/*      */   {
/*  570 */     return getCursor(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public CustomDatum getCustomDatum(String paramString, CustomDatumFactory paramCustomDatumFactory)
/*      */     throws SQLException
/*      */   {
/*  576 */     return getCustomDatum(findColumn(paramString), paramCustomDatumFactory);
/*      */   }
/*      */   
/*      */   public DATE getDATE(String paramString)
/*      */     throws SQLException
/*      */   {
/*  582 */     return getDATE(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public INTERVALDS getINTERVALDS(String paramString)
/*      */     throws SQLException
/*      */   {
/*  588 */     return getINTERVALDS(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public INTERVALYM getINTERVALYM(String paramString)
/*      */     throws SQLException
/*      */   {
/*  594 */     return getINTERVALYM(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public NUMBER getNUMBER(String paramString)
/*      */     throws SQLException
/*      */   {
/*  600 */     return getNUMBER(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public OPAQUE getOPAQUE(String paramString)
/*      */     throws SQLException
/*      */   {
/*  606 */     return getOPAQUE(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public Datum getOracleObject(String paramString)
/*      */     throws SQLException
/*      */   {
/*  612 */     return getOracleObject(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public ORAData getORAData(String paramString, ORADataFactory paramORADataFactory)
/*      */     throws SQLException
/*      */   {
/*  618 */     return getORAData(findColumn(paramString), paramORADataFactory);
/*      */   }
/*      */   
/*      */   public Object getObject(String paramString, OracleDataFactory paramOracleDataFactory)
/*      */     throws SQLException
/*      */   {
/*  624 */     return getObject(findColumn(paramString), paramOracleDataFactory);
/*      */   }
/*      */   
/*      */   public RAW getRAW(String paramString)
/*      */     throws SQLException
/*      */   {
/*  630 */     return getRAW(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public REF getREF(String paramString)
/*      */     throws SQLException
/*      */   {
/*  636 */     return getREF(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public ROWID getROWID(String paramString)
/*      */     throws SQLException
/*      */   {
/*  642 */     return getROWID(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public STRUCT getSTRUCT(String paramString)
/*      */     throws SQLException
/*      */   {
/*  648 */     return getSTRUCT(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public TIMESTAMPLTZ getTIMESTAMPLTZ(String paramString)
/*      */     throws SQLException
/*      */   {
/*  654 */     return getTIMESTAMPLTZ(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public TIMESTAMPTZ getTIMESTAMPTZ(String paramString)
/*      */     throws SQLException
/*      */   {
/*  660 */     return getTIMESTAMPTZ(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public TIMESTAMP getTIMESTAMP(String paramString)
/*      */     throws SQLException
/*      */   {
/*  666 */     return getTIMESTAMP(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public InputStream getAsciiStream(String paramString)
/*      */     throws SQLException
/*      */   {
/*  672 */     return getAsciiStream(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public InputStream getBinaryStream(String paramString)
/*      */     throws SQLException
/*      */   {
/*  678 */     return getBinaryStream(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public Reader getCharacterStream(String paramString)
/*      */     throws SQLException
/*      */   {
/*  684 */     return getCharacterStream(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public Reader getNCharacterStream(String paramString)
/*      */     throws SQLException
/*      */   {
/*  690 */     return getNCharacterStream(findColumn(paramString));
/*      */   }
/*      */   
/*      */   public InputStream getUnicodeStream(String paramString)
/*      */     throws SQLException
/*      */   {
/*  696 */     return getUnicodeStream(findColumn(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateArray(String paramString, Array paramArray)
/*      */     throws SQLException
/*      */   {
/*  703 */     updateArray(findColumn(paramString), paramArray);
/*      */   }
/*      */   
/*      */   public void updateBigDecimal(String paramString, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/*  709 */     updateBigDecimal(findColumn(paramString), paramBigDecimal);
/*      */   }
/*      */   
/*      */   public void updateBlob(String paramString, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/*  715 */     updateBlob(findColumn(paramString), paramBlob);
/*      */   }
/*      */   
/*      */   public void updateBoolean(String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  721 */     updateBoolean(findColumn(paramString), paramBoolean);
/*      */   }
/*      */   
/*      */   public void updateByte(String paramString, byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  727 */     updateByte(findColumn(paramString), paramByte);
/*      */   }
/*      */   
/*      */   public void updateBytes(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  733 */     updateBytes(findColumn(paramString), paramArrayOfByte);
/*      */   }
/*      */   
/*      */   public void updateClob(String paramString, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/*  739 */     updateClob(findColumn(paramString), paramClob);
/*      */   }
/*      */   
/*      */   public void updateDate(String paramString, Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  745 */     updateDate(findColumn(paramString), paramDate);
/*      */   }
/*      */   
/*      */   public void updateDate(String paramString, Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  751 */     updateDate(findColumn(paramString), paramDate, paramCalendar);
/*      */   }
/*      */   
/*      */   public void updateDouble(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  757 */     updateDouble(findColumn(paramString), paramDouble);
/*      */   }
/*      */   
/*      */   public void updateFloat(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  763 */     updateFloat(findColumn(paramString), paramFloat);
/*      */   }
/*      */   
/*      */   public void updateInt(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  769 */     updateInt(findColumn(paramString), paramInt);
/*      */   }
/*      */   
/*      */   public void updateLong(String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  775 */     updateLong(findColumn(paramString), paramLong);
/*      */   }
/*      */   
/*      */   public void updateNClob(String paramString, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/*  781 */     updateNClob(findColumn(paramString), paramNClob);
/*      */   }
/*      */   
/*      */   public void updateNString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  787 */     updateNString(findColumn(paramString1), paramString2);
/*      */   }
/*      */   
/*      */   public void updateObject(String paramString, Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  793 */     updateObject(findColumn(paramString), paramObject);
/*      */   }
/*      */   
/*      */   public void updateObject(String paramString, Object paramObject, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  799 */     updateObject(findColumn(paramString), paramObject, paramInt);
/*      */   }
/*      */   
/*      */   public void updateRef(String paramString, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/*  805 */     updateRef(findColumn(paramString), paramRef);
/*      */   }
/*      */   
/*      */   public void updateRowId(String paramString, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/*  811 */     updateRowId(findColumn(paramString), paramRowId);
/*      */   }
/*      */   
/*      */   public void updateShort(String paramString, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  817 */     updateShort(findColumn(paramString), paramShort);
/*      */   }
/*      */   
/*      */   public void updateSQLXML(String paramString, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/*  823 */     updateSQLXML(findColumn(paramString), paramSQLXML);
/*      */   }
/*      */   
/*      */   public void updateString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  829 */     updateString(findColumn(paramString1), paramString2);
/*      */   }
/*      */   
/*      */   public void updateTime(String paramString, Time paramTime)
/*      */     throws SQLException
/*      */   {
/*  835 */     updateTime(findColumn(paramString), paramTime);
/*      */   }
/*      */   
/*      */   public void updateTime(String paramString, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  841 */     updateTime(findColumn(paramString), paramTime, paramCalendar);
/*      */   }
/*      */   
/*      */   public void updateTimestamp(String paramString, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/*  847 */     updateTimestamp(findColumn(paramString), paramTimestamp);
/*      */   }
/*      */   
/*      */   public void updateTimestamp(String paramString, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  853 */     updateTimestamp(findColumn(paramString), paramTimestamp, paramCalendar);
/*      */   }
/*      */   
/*      */   public void updateURL(String paramString, URL paramURL)
/*      */     throws SQLException
/*      */   {
/*  859 */     updateURL(findColumn(paramString), paramURL);
/*      */   }
/*      */   
/*      */   public void updateARRAY(String paramString, ARRAY paramARRAY)
/*      */     throws SQLException
/*      */   {
/*  865 */     updateARRAY(findColumn(paramString), paramARRAY);
/*      */   }
/*      */   
/*      */   public void updateBFILE(String paramString, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  871 */     updateBFILE(findColumn(paramString), paramBFILE);
/*      */   }
/*      */   
/*      */   public void updateBfile(String paramString, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  877 */     updateBfile(findColumn(paramString), paramBFILE);
/*      */   }
/*      */   
/*      */   public void updateBinaryFloat(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  883 */     updateBinaryFloat(findColumn(paramString), paramFloat);
/*      */   }
/*      */   
/*      */   public void updateBinaryFloat(String paramString, BINARY_FLOAT paramBINARY_FLOAT)
/*      */     throws SQLException
/*      */   {
/*  889 */     updateBinaryFloat(findColumn(paramString), paramBINARY_FLOAT);
/*      */   }
/*      */   
/*      */   public void updateBinaryDouble(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  895 */     updateBinaryDouble(findColumn(paramString), paramDouble);
/*      */   }
/*      */   
/*      */   public void updateBinaryDouble(String paramString, BINARY_DOUBLE paramBINARY_DOUBLE)
/*      */     throws SQLException
/*      */   {
/*  901 */     updateBinaryDouble(findColumn(paramString), paramBINARY_DOUBLE);
/*      */   }
/*      */   
/*      */   public void updateBLOB(String paramString, BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/*  907 */     updateBLOB(findColumn(paramString), paramBLOB);
/*      */   }
/*      */   
/*      */   public void updateCHAR(String paramString, CHAR paramCHAR)
/*      */     throws SQLException
/*      */   {
/*  913 */     updateCHAR(findColumn(paramString), paramCHAR);
/*      */   }
/*      */   
/*      */   public void updateCLOB(String paramString, CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/*  919 */     updateCLOB(findColumn(paramString), paramCLOB);
/*      */   }
/*      */   
/*      */   public void updateCursor(String paramString, ResultSet paramResultSet)
/*      */     throws SQLException
/*      */   {
/*  925 */     updateCursor(findColumn(paramString), paramResultSet);
/*      */   }
/*      */   
/*      */   public void updateCustomDatum(String paramString, CustomDatum paramCustomDatum)
/*      */     throws SQLException
/*      */   {
/*  931 */     updateCustomDatum(findColumn(paramString), paramCustomDatum);
/*      */   }
/*      */   
/*      */   public void updateDATE(String paramString, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/*  937 */     updateDATE(findColumn(paramString), paramDATE);
/*      */   }
/*      */   
/*      */   public void updateFixedCHAR(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  943 */     updateFixedCHAR(findColumn(paramString1), paramString2);
/*      */   }
/*      */   
/*      */   public void updateINTERVALDS(String paramString, INTERVALDS paramINTERVALDS)
/*      */     throws SQLException
/*      */   {
/*  949 */     updateINTERVALDS(findColumn(paramString), paramINTERVALDS);
/*      */   }
/*      */   
/*      */   public void updateINTERVALYM(String paramString, INTERVALYM paramINTERVALYM)
/*      */     throws SQLException
/*      */   {
/*  955 */     updateINTERVALYM(findColumn(paramString), paramINTERVALYM);
/*      */   }
/*      */   
/*      */   public void updateNUMBER(String paramString, NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/*  961 */     updateNUMBER(findColumn(paramString), paramNUMBER);
/*      */   }
/*      */   
/*      */   public void updateOPAQUE(String paramString, OPAQUE paramOPAQUE)
/*      */     throws SQLException
/*      */   {
/*  967 */     updateOPAQUE(findColumn(paramString), paramOPAQUE);
/*      */   }
/*      */   
/*      */   public void updateOracleObject(String paramString, Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/*  973 */     updateOracleObject(findColumn(paramString), paramDatum);
/*      */   }
/*      */   
/*      */   public void updateORAData(String paramString, ORAData paramORAData)
/*      */     throws SQLException
/*      */   {
/*  979 */     updateORAData(findColumn(paramString), paramORAData);
/*      */   }
/*      */   
/*      */   public void updateRAW(String paramString, RAW paramRAW)
/*      */     throws SQLException
/*      */   {
/*  985 */     updateRAW(findColumn(paramString), paramRAW);
/*      */   }
/*      */   
/*      */   public void updateREF(String paramString, REF paramREF)
/*      */     throws SQLException
/*      */   {
/*  991 */     updateREF(findColumn(paramString), paramREF);
/*      */   }
/*      */   
/*      */   public void updateRefType(String paramString, REF paramREF)
/*      */     throws SQLException
/*      */   {
/*  997 */     updateRefType(findColumn(paramString), paramREF);
/*      */   }
/*      */   
/*      */   public void updateROWID(String paramString, ROWID paramROWID)
/*      */     throws SQLException
/*      */   {
/* 1003 */     updateROWID(findColumn(paramString), paramROWID);
/*      */   }
/*      */   
/*      */   public void updateSTRUCT(String paramString, STRUCT paramSTRUCT)
/*      */     throws SQLException
/*      */   {
/* 1009 */     updateSTRUCT(findColumn(paramString), paramSTRUCT);
/*      */   }
/*      */   
/*      */   public void updateTIMESTAMPLTZ(String paramString, TIMESTAMPLTZ paramTIMESTAMPLTZ)
/*      */     throws SQLException
/*      */   {
/* 1015 */     updateTIMESTAMPLTZ(findColumn(paramString), paramTIMESTAMPLTZ);
/*      */   }
/*      */   
/*      */   public void updateTIMESTAMPTZ(String paramString, TIMESTAMPTZ paramTIMESTAMPTZ)
/*      */     throws SQLException
/*      */   {
/* 1021 */     updateTIMESTAMPTZ(findColumn(paramString), paramTIMESTAMPTZ);
/*      */   }
/*      */   
/*      */   public void updateTIMESTAMP(String paramString, TIMESTAMP paramTIMESTAMP)
/*      */     throws SQLException
/*      */   {
/* 1027 */     updateTIMESTAMP(findColumn(paramString), paramTIMESTAMP);
/*      */   }
/*      */   
/*      */   public void updateBlob(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 1033 */     updateBlob(findColumn(paramString), paramInputStream);
/*      */   }
/*      */   
/*      */   public void updateBlob(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1039 */     updateBlob(findColumn(paramString), paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */   public void updateClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 1045 */     updateClob(findColumn(paramString), paramReader);
/*      */   }
/*      */   
/*      */   public void updateClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1051 */     updateClob(findColumn(paramString), paramReader, paramLong);
/*      */   }
/*      */   
/*      */   public void updateNClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 1057 */     updateNClob(findColumn(paramString), paramReader);
/*      */   }
/*      */   
/*      */   public void updateNClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1063 */     updateNClob(findColumn(paramString), paramReader, paramLong);
/*      */   }
/*      */   
/*      */   public void updateAsciiStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 1069 */     updateAsciiStream(findColumn(paramString), paramInputStream);
/*      */   }
/*      */   
/*      */   public void updateAsciiStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1075 */     updateAsciiStream(findColumn(paramString), paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */   public void updateAsciiStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1081 */     updateAsciiStream(findColumn(paramString), paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */   public void updateBinaryStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 1087 */     updateBinaryStream(findColumn(paramString), paramInputStream);
/*      */   }
/*      */   
/*      */   public void updateBinaryStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1093 */     updateBinaryStream(findColumn(paramString), paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */   public void updateBinaryStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1099 */     updateBinaryStream(findColumn(paramString), paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */   public void updateCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 1105 */     updateCharacterStream(findColumn(paramString), paramReader);
/*      */   }
/*      */   
/*      */   public void updateCharacterStream(String paramString, Reader paramReader, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1111 */     updateCharacterStream(findColumn(paramString), paramReader, paramInt);
/*      */   }
/*      */   
/*      */   public void updateCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1117 */     updateCharacterStream(findColumn(paramString), paramReader, paramLong);
/*      */   }
/*      */   
/*      */   public void updateNCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 1123 */     updateNCharacterStream(findColumn(paramString), paramReader);
/*      */   }
/*      */   
/*      */   public void updateNCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1129 */     updateNCharacterStream(findColumn(paramString), paramReader, paramLong);
/*      */   }
/*      */   
/*      */   public void updateUnicodeStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1135 */     updateUnicodeStream(findColumn(paramString), paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract OracleResultSet.AuthorizationIndicator getAuthorizationIndicator(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */   public OracleResultSet.AuthorizationIndicator getAuthorizationIndicator(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1149 */     return getAuthorizationIndicator(findColumn(paramString));
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
/*      */   public abstract void setAutoRefetch(boolean paramBoolean)
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean getAutoRefetch()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract SQLWarning getWarnings()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void clearWarnings()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract String getCursorName()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract ResultSetMetaData getMetaData()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract int findColumn(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean next()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void close()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean wasNull()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean isBeforeFirst()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean isAfterLast()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean isFirst()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean isLast()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void beforeFirst()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void afterLast()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean first()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean last()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract int getRow()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean absolute(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean relative(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean previous()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setFetchDirection(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract int getFetchDirection()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setFetchSize(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract int getFetchSize()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract int getType()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract int getConcurrency()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void insertRow()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void updateRow()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void deleteRow()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void refreshRow()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void moveToInsertRow()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void cancelRowUpdates()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void moveToCurrentRow()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract Statement getStatement()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean rowUpdated()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean rowInserted()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract boolean rowDeleted()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void updateNull(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNull(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1676 */     updateNull(findColumn(paramString));
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
/*      */   public boolean isWrapperFor(Class<?> paramClass)
/*      */     throws SQLException
/*      */   {
/* 1693 */     if (paramClass.isInterface()) { return paramClass.isInstance(this);
/*      */     }
/* 1695 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 1696 */     localSQLException.fillInStackTrace();
/* 1697 */     throw localSQLException;
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
/*      */   public <T> T unwrap(Class<T> paramClass)
/*      */     throws SQLException
/*      */   {
/* 1714 */     if ((paramClass.isInterface()) && (paramClass.isInstance(this))) { return this;
/*      */     }
/* 1716 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 1717 */     localSQLException.fillInStackTrace();
/* 1718 */     throw localSQLException;
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
/*      */   protected OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 1735 */     return null;
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
/* 1746 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */   
/*      */   abstract OracleStatement getOracleStatement()
/*      */     throws SQLException;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */