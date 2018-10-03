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
/*      */ import java.sql.RowId;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLXML;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.Map;
/*      */ import oracle.jdbc.OracleDataFactory;
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
/*      */ class OracleCallableStatementWrapper
/*      */   extends OraclePreparedStatementWrapper
/*      */   implements oracle.jdbc.internal.OracleCallableStatement
/*      */ {
/*   58 */   protected oracle.jdbc.internal.OracleCallableStatement callableStatement = null;
/*      */   
/*      */   OracleCallableStatementWrapper(oracle.jdbc.OracleCallableStatement paramOracleCallableStatement) throws SQLException
/*      */   {
/*   62 */     super(paramOracleCallableStatement);
/*   63 */     this.callableStatement = ((oracle.jdbc.internal.OracleCallableStatement)paramOracleCallableStatement);
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
/*      */   public void setArray(String paramString, Array paramArray)
/*      */     throws SQLException
/*      */   {
/*   85 */     this.callableStatement.setArray(paramString, paramArray);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBigDecimal(String paramString, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/*   93 */     this.callableStatement.setBigDecimal(paramString, paramBigDecimal);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/*  101 */     this.callableStatement.setBlob(paramString, paramBlob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBoolean(String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  109 */     this.callableStatement.setBoolean(paramString, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setByte(String paramString, byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  117 */     this.callableStatement.setByte(paramString, paramByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBytes(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  125 */     this.callableStatement.setBytes(paramString, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/*  133 */     this.callableStatement.setClob(paramString, paramClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDate(String paramString, Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  141 */     this.callableStatement.setDate(paramString, paramDate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDate(String paramString, Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  149 */     this.callableStatement.setDate(paramString, paramDate, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDouble(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  157 */     this.callableStatement.setDouble(paramString, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFloat(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  165 */     this.callableStatement.setFloat(paramString, paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setInt(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  173 */     this.callableStatement.setInt(paramString, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setLong(String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  181 */     this.callableStatement.setLong(paramString, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/*  189 */     this.callableStatement.setNClob(paramString, paramNClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  197 */     this.callableStatement.setNString(paramString1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setObject(String paramString, Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  205 */     this.callableStatement.setObject(paramString, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setObject(String paramString, Object paramObject, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  213 */     this.callableStatement.setObject(paramString, paramObject, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRef(String paramString, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/*  221 */     this.callableStatement.setRef(paramString, paramRef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRowId(String paramString, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/*  229 */     this.callableStatement.setRowId(paramString, paramRowId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setShort(String paramString, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  237 */     this.callableStatement.setShort(paramString, paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSQLXML(String paramString, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/*  245 */     this.callableStatement.setSQLXML(paramString, paramSQLXML);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  253 */     this.callableStatement.setString(paramString1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTime(String paramString, Time paramTime)
/*      */     throws SQLException
/*      */   {
/*  261 */     this.callableStatement.setTime(paramString, paramTime);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTime(String paramString, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  269 */     this.callableStatement.setTime(paramString, paramTime, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTimestamp(String paramString, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/*  277 */     this.callableStatement.setTimestamp(paramString, paramTimestamp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTimestamp(String paramString, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  285 */     this.callableStatement.setTimestamp(paramString, paramTimestamp, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setURL(String paramString, URL paramURL)
/*      */     throws SQLException
/*      */   {
/*  293 */     this.callableStatement.setURL(paramString, paramURL);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setARRAY(String paramString, ARRAY paramARRAY)
/*      */     throws SQLException
/*      */   {
/*  301 */     this.callableStatement.setARRAY(paramString, paramARRAY);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBFILE(String paramString, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  309 */     this.callableStatement.setBFILE(paramString, paramBFILE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBfile(String paramString, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  317 */     this.callableStatement.setBfile(paramString, paramBFILE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryFloat(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  325 */     this.callableStatement.setBinaryFloat(paramString, paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryFloat(String paramString, BINARY_FLOAT paramBINARY_FLOAT)
/*      */     throws SQLException
/*      */   {
/*  333 */     this.callableStatement.setBinaryFloat(paramString, paramBINARY_FLOAT);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryDouble(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  341 */     this.callableStatement.setBinaryDouble(paramString, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryDouble(String paramString, BINARY_DOUBLE paramBINARY_DOUBLE)
/*      */     throws SQLException
/*      */   {
/*  349 */     this.callableStatement.setBinaryDouble(paramString, paramBINARY_DOUBLE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBLOB(String paramString, BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/*  357 */     this.callableStatement.setBLOB(paramString, paramBLOB);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCHAR(String paramString, CHAR paramCHAR)
/*      */     throws SQLException
/*      */   {
/*  365 */     this.callableStatement.setCHAR(paramString, paramCHAR);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCLOB(String paramString, CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/*  373 */     this.callableStatement.setCLOB(paramString, paramCLOB);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCursor(String paramString, ResultSet paramResultSet)
/*      */     throws SQLException
/*      */   {
/*  381 */     this.callableStatement.setCursor(paramString, paramResultSet);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCustomDatum(String paramString, CustomDatum paramCustomDatum)
/*      */     throws SQLException
/*      */   {
/*  389 */     this.callableStatement.setCustomDatum(paramString, paramCustomDatum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDATE(String paramString, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/*  397 */     this.callableStatement.setDATE(paramString, paramDATE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFixedCHAR(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  405 */     this.callableStatement.setFixedCHAR(paramString1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setINTERVALDS(String paramString, INTERVALDS paramINTERVALDS)
/*      */     throws SQLException
/*      */   {
/*  413 */     this.callableStatement.setINTERVALDS(paramString, paramINTERVALDS);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setINTERVALYM(String paramString, INTERVALYM paramINTERVALYM)
/*      */     throws SQLException
/*      */   {
/*  421 */     this.callableStatement.setINTERVALYM(paramString, paramINTERVALYM);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNUMBER(String paramString, NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/*  429 */     this.callableStatement.setNUMBER(paramString, paramNUMBER);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setOPAQUE(String paramString, OPAQUE paramOPAQUE)
/*      */     throws SQLException
/*      */   {
/*  437 */     this.callableStatement.setOPAQUE(paramString, paramOPAQUE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setOracleObject(String paramString, Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/*  445 */     this.callableStatement.setOracleObject(paramString, paramDatum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setORAData(String paramString, ORAData paramORAData)
/*      */     throws SQLException
/*      */   {
/*  453 */     this.callableStatement.setORAData(paramString, paramORAData);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRAW(String paramString, RAW paramRAW)
/*      */     throws SQLException
/*      */   {
/*  461 */     this.callableStatement.setRAW(paramString, paramRAW);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setREF(String paramString, REF paramREF)
/*      */     throws SQLException
/*      */   {
/*  469 */     this.callableStatement.setREF(paramString, paramREF);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRefType(String paramString, REF paramREF)
/*      */     throws SQLException
/*      */   {
/*  477 */     this.callableStatement.setRefType(paramString, paramREF);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setROWID(String paramString, ROWID paramROWID)
/*      */     throws SQLException
/*      */   {
/*  485 */     this.callableStatement.setROWID(paramString, paramROWID);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSTRUCT(String paramString, STRUCT paramSTRUCT)
/*      */     throws SQLException
/*      */   {
/*  493 */     this.callableStatement.setSTRUCT(paramString, paramSTRUCT);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPLTZ(String paramString, TIMESTAMPLTZ paramTIMESTAMPLTZ)
/*      */     throws SQLException
/*      */   {
/*  501 */     this.callableStatement.setTIMESTAMPLTZ(paramString, paramTIMESTAMPLTZ);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPTZ(String paramString, TIMESTAMPTZ paramTIMESTAMPTZ)
/*      */     throws SQLException
/*      */   {
/*  509 */     this.callableStatement.setTIMESTAMPTZ(paramString, paramTIMESTAMPTZ);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMP(String paramString, TIMESTAMP paramTIMESTAMP)
/*      */     throws SQLException
/*      */   {
/*  517 */     this.callableStatement.setTIMESTAMP(paramString, paramTIMESTAMP);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/*  525 */     this.callableStatement.setBlob(paramString, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  533 */     this.callableStatement.setBlob(paramString, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/*  541 */     this.callableStatement.setClob(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  549 */     this.callableStatement.setClob(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/*  557 */     this.callableStatement.setNClob(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  565 */     this.callableStatement.setNClob(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/*  573 */     this.callableStatement.setAsciiStream(paramString, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  581 */     this.callableStatement.setAsciiStream(paramString, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  589 */     this.callableStatement.setAsciiStream(paramString, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/*  597 */     this.callableStatement.setBinaryStream(paramString, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  605 */     this.callableStatement.setBinaryStream(paramString, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  613 */     this.callableStatement.setBinaryStream(paramString, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/*  621 */     this.callableStatement.setCharacterStream(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  629 */     this.callableStatement.setCharacterStream(paramString, paramReader, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  637 */     this.callableStatement.setCharacterStream(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/*  645 */     this.callableStatement.setNCharacterStream(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  653 */     this.callableStatement.setNCharacterStream(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setUnicodeStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  661 */     this.callableStatement.setUnicodeStream(paramString, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNull(String paramString1, int paramInt, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  671 */     this.callableStatement.setNull(paramString1, paramInt, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNull(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  680 */     this.callableStatement.setNull(paramString, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Array getArray(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  690 */     return this.callableStatement.getArray(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  698 */     return this.callableStatement.getBigDecimal(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  706 */     return this.callableStatement.getBigDecimal(paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Blob getBlob(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  714 */     return this.callableStatement.getBlob(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getBoolean(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  722 */     return this.callableStatement.getBoolean(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte getByte(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  730 */     return this.callableStatement.getByte(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte[] getBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  738 */     return this.callableStatement.getBytes(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Clob getClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  746 */     return this.callableStatement.getClob(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Date getDate(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  754 */     return this.callableStatement.getDate(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Date getDate(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  762 */     return this.callableStatement.getDate(paramInt, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getDouble(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  770 */     return this.callableStatement.getDouble(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public float getFloat(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  778 */     return this.callableStatement.getFloat(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getInt(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  786 */     return this.callableStatement.getInt(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public long getLong(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  794 */     return this.callableStatement.getLong(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public NClob getNClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  802 */     return this.callableStatement.getNClob(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getNString(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  810 */     return this.callableStatement.getNString(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  818 */     return this.callableStatement.getObject(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getObject(int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  826 */     return this.callableStatement.getObject(paramInt, paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Ref getRef(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  834 */     return this.callableStatement.getRef(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public RowId getRowId(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  842 */     return this.callableStatement.getRowId(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public short getShort(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  850 */     return this.callableStatement.getShort(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public SQLXML getSQLXML(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  858 */     return this.callableStatement.getSQLXML(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getString(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  866 */     return this.callableStatement.getString(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Time getTime(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  874 */     return this.callableStatement.getTime(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Time getTime(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  882 */     return this.callableStatement.getTime(paramInt, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  890 */     return this.callableStatement.getTimestamp(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  898 */     return this.callableStatement.getTimestamp(paramInt, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public URL getURL(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  906 */     return this.callableStatement.getURL(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ARRAY getARRAY(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  914 */     return this.callableStatement.getARRAY(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BFILE getBFILE(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  922 */     return this.callableStatement.getBFILE(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BFILE getBfile(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  930 */     return this.callableStatement.getBfile(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BLOB getBLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  938 */     return this.callableStatement.getBLOB(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public CHAR getCHAR(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  946 */     return this.callableStatement.getCHAR(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public CLOB getCLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  954 */     return this.callableStatement.getCLOB(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSet getCursor(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  962 */     return this.callableStatement.getCursor(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getCustomDatum(int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */     throws SQLException
/*      */   {
/*  970 */     return this.callableStatement.getCustomDatum(paramInt, paramCustomDatumFactory);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public DATE getDATE(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  978 */     return this.callableStatement.getDATE(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public INTERVALDS getINTERVALDS(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  986 */     return this.callableStatement.getINTERVALDS(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public INTERVALYM getINTERVALYM(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  994 */     return this.callableStatement.getINTERVALYM(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public NUMBER getNUMBER(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1002 */     return this.callableStatement.getNUMBER(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public OPAQUE getOPAQUE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1010 */     return this.callableStatement.getOPAQUE(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Datum getOracleObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1018 */     return this.callableStatement.getOracleObject(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getORAData(int paramInt, ORADataFactory paramORADataFactory)
/*      */     throws SQLException
/*      */   {
/* 1026 */     return this.callableStatement.getORAData(paramInt, paramORADataFactory);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getObject(int paramInt, OracleDataFactory paramOracleDataFactory)
/*      */     throws SQLException
/*      */   {
/* 1034 */     return this.callableStatement.getObject(paramInt, paramOracleDataFactory);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public RAW getRAW(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1042 */     return this.callableStatement.getRAW(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public REF getREF(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1050 */     return this.callableStatement.getREF(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ROWID getROWID(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1058 */     return this.callableStatement.getROWID(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public STRUCT getSTRUCT(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1066 */     return this.callableStatement.getSTRUCT(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public TIMESTAMPLTZ getTIMESTAMPLTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1074 */     return this.callableStatement.getTIMESTAMPLTZ(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ getTIMESTAMPTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1082 */     return this.callableStatement.getTIMESTAMPTZ(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public TIMESTAMP getTIMESTAMP(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1090 */     return this.callableStatement.getTIMESTAMP(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public InputStream getAsciiStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1098 */     return this.callableStatement.getAsciiStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public InputStream getBinaryStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1106 */     return this.callableStatement.getBinaryStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Reader getCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1114 */     return this.callableStatement.getCharacterStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Reader getNCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1122 */     return this.callableStatement.getNCharacterStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public InputStream getUnicodeStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1130 */     return this.callableStatement.getUnicodeStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Array getArray(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1139 */     return this.callableStatement.getArray(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1147 */     return this.callableStatement.getBigDecimal(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1155 */     return this.callableStatement.getBigDecimal(paramString, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Blob getBlob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1163 */     return this.callableStatement.getBlob(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getBoolean(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1171 */     return this.callableStatement.getBoolean(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte getByte(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1179 */     return this.callableStatement.getByte(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte[] getBytes(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1187 */     return this.callableStatement.getBytes(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Clob getClob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1195 */     return this.callableStatement.getClob(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Date getDate(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1203 */     return this.callableStatement.getDate(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Date getDate(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1211 */     return this.callableStatement.getDate(paramString, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getDouble(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1219 */     return this.callableStatement.getDouble(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public float getFloat(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1227 */     return this.callableStatement.getFloat(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getInt(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1235 */     return this.callableStatement.getInt(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public long getLong(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1243 */     return this.callableStatement.getLong(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public NClob getNClob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1251 */     return this.callableStatement.getNClob(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getNString(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1259 */     return this.callableStatement.getNString(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getObject(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1267 */     return this.callableStatement.getObject(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getObject(String paramString, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1275 */     return this.callableStatement.getObject(paramString, paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Ref getRef(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1283 */     return this.callableStatement.getRef(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public RowId getRowId(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1291 */     return this.callableStatement.getRowId(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public short getShort(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1299 */     return this.callableStatement.getShort(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public SQLXML getSQLXML(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1307 */     return this.callableStatement.getSQLXML(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getString(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1315 */     return this.callableStatement.getString(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Time getTime(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1323 */     return this.callableStatement.getTime(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Time getTime(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1331 */     return this.callableStatement.getTime(paramString, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1339 */     return this.callableStatement.getTimestamp(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1347 */     return this.callableStatement.getTimestamp(paramString, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public URL getURL(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1355 */     return this.callableStatement.getURL(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public InputStream getAsciiStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1363 */     return this.callableStatement.getAsciiStream(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public InputStream getBinaryStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1371 */     return this.callableStatement.getBinaryStream(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Reader getCharacterStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1379 */     return this.callableStatement.getCharacterStream(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Reader getNCharacterStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1387 */     return this.callableStatement.getNCharacterStream(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public InputStream getUnicodeStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1395 */     return this.callableStatement.getUnicodeStream(paramString);
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
/*      */   public void setObject(String paramString, Object paramObject, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1409 */     this.callableStatement.setObject(paramString, paramObject, paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getAnyDataEmbeddedObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1417 */     return this.callableStatement.getAnyDataEmbeddedObject(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setStructDescriptor(int paramInt, StructDescriptor paramStructDescriptor)
/*      */     throws SQLException
/*      */   {
/* 1426 */     this.callableStatement.setStructDescriptor(paramInt, paramStructDescriptor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setStructDescriptor(String paramString, StructDescriptor paramStructDescriptor)
/*      */     throws SQLException
/*      */   {
/* 1435 */     this.callableStatement.setStructDescriptor(paramString, paramStructDescriptor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void close()
/*      */     throws SQLException
/*      */   {
/* 1444 */     super.close();
/*      */   }
/*      */   
/*      */ 
/*      */   public void closeWithKey(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1451 */     this.callableStatement.closeWithKey(paramString);
/* 1452 */     this.statement = (this.preparedStatement = this.callableStatement = closedStatement);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/* 1465 */     this.callableStatement.registerOutParameter(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameterBytes(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/* 1473 */     this.callableStatement.registerOutParameterBytes(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameterChars(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/* 1481 */     this.callableStatement.registerOutParameterChars(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerIndexTableOutParameter(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/* 1489 */     this.callableStatement.registerIndexTableOutParameter(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(String paramString, int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1497 */     this.callableStatement.registerOutParameter(paramString, paramInt1, paramInt2, paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(String paramString1, int paramInt, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 1507 */     this.callableStatement.registerOutParameter(paramString1, paramInt, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int sendBatch()
/*      */     throws SQLException
/*      */   {
/* 1515 */     return this.callableStatement.sendBatch();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setExecuteBatch(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1523 */     this.callableStatement.setExecuteBatch(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getPlsqlIndexTable(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1531 */     return this.callableStatement.getPlsqlIndexTable(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getPlsqlIndexTable(int paramInt, Class paramClass)
/*      */     throws SQLException
/*      */   {
/* 1539 */     return this.callableStatement.getPlsqlIndexTable(paramInt, paramClass);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Datum[] getOraclePlsqlIndexTable(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1547 */     return this.callableStatement.getOraclePlsqlIndexTable(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setStringForClob(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 1555 */     this.callableStatement.setStringForClob(paramString1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBytesForBlob(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1563 */     this.callableStatement.setBytesForBlob(paramString, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean wasNull()
/*      */     throws SQLException
/*      */   {
/* 1571 */     return this.callableStatement.wasNull();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1579 */     this.callableStatement.registerOutParameter(paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1587 */     this.callableStatement.registerOutParameter(paramInt1, paramInt2, paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(int paramInt1, int paramInt2, String paramString)
/*      */     throws SQLException
/*      */   {
/* 1595 */     this.callableStatement.registerOutParameter(paramInt1, paramInt2, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1604 */     this.callableStatement.registerOutParameter(paramString, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(String paramString, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1612 */     this.callableStatement.registerOutParameter(paramString, paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public byte[] privateGetBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1620 */     return ((OracleCallableStatement)this.callableStatement).privateGetBytes(paramInt);
/*      */   }
/*      */   
/*      */ 
/* 1624 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleCallableStatementWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */