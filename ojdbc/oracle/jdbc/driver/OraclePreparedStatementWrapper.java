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
/*      */ import java.sql.ParameterMetaData;
/*      */ import java.sql.Ref;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.RowId;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLXML;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import oracle.jdbc.OracleParameterMetaData;
/*      */ import oracle.sql.ARRAY;
/*      */ import oracle.sql.BFILE;
/*      */ import oracle.sql.BINARY_DOUBLE;
/*      */ import oracle.sql.BINARY_FLOAT;
/*      */ import oracle.sql.BLOB;
/*      */ import oracle.sql.CHAR;
/*      */ import oracle.sql.CLOB;
/*      */ import oracle.sql.CustomDatum;
/*      */ import oracle.sql.DATE;
/*      */ import oracle.sql.Datum;
/*      */ import oracle.sql.INTERVALDS;
/*      */ import oracle.sql.INTERVALYM;
/*      */ import oracle.sql.NUMBER;
/*      */ import oracle.sql.OPAQUE;
/*      */ import oracle.sql.ORAData;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ class OraclePreparedStatementWrapper
/*      */   extends OracleStatementWrapper
/*      */   implements oracle.jdbc.internal.OraclePreparedStatement
/*      */ {
/*   70 */   protected oracle.jdbc.internal.OraclePreparedStatement preparedStatement = null;
/*      */   
/*      */   OraclePreparedStatementWrapper(oracle.jdbc.OraclePreparedStatement paramOraclePreparedStatement) throws SQLException
/*      */   {
/*   74 */     super(paramOraclePreparedStatement);
/*   75 */     this.preparedStatement = ((oracle.jdbc.internal.OraclePreparedStatement)paramOraclePreparedStatement);
/*      */   }
/*      */   
/*      */ 
/*      */   public void close()
/*      */     throws SQLException
/*      */   {
/*   82 */     super.close();
/*   83 */     this.preparedStatement = OracleStatementWrapper.closedStatement;
/*      */   }
/*      */   
/*      */ 
/*      */   public void closeWithKey(String paramString)
/*      */     throws SQLException
/*      */   {
/*   90 */     this.preparedStatement.closeWithKey(paramString);
/*   91 */     this.statement = (this.preparedStatement = closedStatement);
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
/*      */   public void setArray(int paramInt, Array paramArray)
/*      */     throws SQLException
/*      */   {
/*  114 */     this.preparedStatement.setArray(paramInt, paramArray);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBigDecimal(int paramInt, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/*  122 */     this.preparedStatement.setBigDecimal(paramInt, paramBigDecimal);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlob(int paramInt, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/*  130 */     this.preparedStatement.setBlob(paramInt, paramBlob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBoolean(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  138 */     this.preparedStatement.setBoolean(paramInt, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setByte(int paramInt, byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  146 */     this.preparedStatement.setByte(paramInt, paramByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBytes(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  154 */     this.preparedStatement.setBytes(paramInt, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClob(int paramInt, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/*  162 */     this.preparedStatement.setClob(paramInt, paramClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDate(int paramInt, Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  170 */     this.preparedStatement.setDate(paramInt, paramDate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDate(int paramInt, Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  178 */     this.preparedStatement.setDate(paramInt, paramDate, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDouble(int paramInt, double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  186 */     this.preparedStatement.setDouble(paramInt, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFloat(int paramInt, float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  194 */     this.preparedStatement.setFloat(paramInt, paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setInt(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  202 */     this.preparedStatement.setInt(paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setLong(int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  210 */     this.preparedStatement.setLong(paramInt, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClob(int paramInt, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/*  218 */     this.preparedStatement.setNClob(paramInt, paramNClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/*  226 */     this.preparedStatement.setNString(paramInt, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setObject(int paramInt, Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  234 */     this.preparedStatement.setObject(paramInt, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setObject(int paramInt1, Object paramObject, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  242 */     this.preparedStatement.setObject(paramInt1, paramObject, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRef(int paramInt, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/*  250 */     this.preparedStatement.setRef(paramInt, paramRef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRowId(int paramInt, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/*  258 */     this.preparedStatement.setRowId(paramInt, paramRowId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setShort(int paramInt, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  266 */     this.preparedStatement.setShort(paramInt, paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSQLXML(int paramInt, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/*  274 */     this.preparedStatement.setSQLXML(paramInt, paramSQLXML);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/*  282 */     this.preparedStatement.setString(paramInt, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTime(int paramInt, Time paramTime)
/*      */     throws SQLException
/*      */   {
/*  290 */     this.preparedStatement.setTime(paramInt, paramTime);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTime(int paramInt, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  298 */     this.preparedStatement.setTime(paramInt, paramTime, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTimestamp(int paramInt, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/*  306 */     this.preparedStatement.setTimestamp(paramInt, paramTimestamp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTimestamp(int paramInt, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  314 */     this.preparedStatement.setTimestamp(paramInt, paramTimestamp, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setURL(int paramInt, URL paramURL)
/*      */     throws SQLException
/*      */   {
/*  322 */     this.preparedStatement.setURL(paramInt, paramURL);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setARRAY(int paramInt, ARRAY paramARRAY)
/*      */     throws SQLException
/*      */   {
/*  330 */     this.preparedStatement.setARRAY(paramInt, paramARRAY);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBFILE(int paramInt, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  338 */     this.preparedStatement.setBFILE(paramInt, paramBFILE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBfile(int paramInt, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  346 */     this.preparedStatement.setBfile(paramInt, paramBFILE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryFloat(int paramInt, float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  354 */     this.preparedStatement.setBinaryFloat(paramInt, paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryFloat(int paramInt, BINARY_FLOAT paramBINARY_FLOAT)
/*      */     throws SQLException
/*      */   {
/*  362 */     this.preparedStatement.setBinaryFloat(paramInt, paramBINARY_FLOAT);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryDouble(int paramInt, double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  370 */     this.preparedStatement.setBinaryDouble(paramInt, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryDouble(int paramInt, BINARY_DOUBLE paramBINARY_DOUBLE)
/*      */     throws SQLException
/*      */   {
/*  378 */     this.preparedStatement.setBinaryDouble(paramInt, paramBINARY_DOUBLE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBLOB(int paramInt, BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/*  386 */     this.preparedStatement.setBLOB(paramInt, paramBLOB);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCHAR(int paramInt, CHAR paramCHAR)
/*      */     throws SQLException
/*      */   {
/*  394 */     this.preparedStatement.setCHAR(paramInt, paramCHAR);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCLOB(int paramInt, CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/*  402 */     this.preparedStatement.setCLOB(paramInt, paramCLOB);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCursor(int paramInt, ResultSet paramResultSet)
/*      */     throws SQLException
/*      */   {
/*  410 */     this.preparedStatement.setCursor(paramInt, paramResultSet);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCustomDatum(int paramInt, CustomDatum paramCustomDatum)
/*      */     throws SQLException
/*      */   {
/*  418 */     this.preparedStatement.setCustomDatum(paramInt, paramCustomDatum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDATE(int paramInt, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/*  426 */     this.preparedStatement.setDATE(paramInt, paramDATE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFixedCHAR(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/*  434 */     this.preparedStatement.setFixedCHAR(paramInt, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setINTERVALDS(int paramInt, INTERVALDS paramINTERVALDS)
/*      */     throws SQLException
/*      */   {
/*  442 */     this.preparedStatement.setINTERVALDS(paramInt, paramINTERVALDS);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setINTERVALYM(int paramInt, INTERVALYM paramINTERVALYM)
/*      */     throws SQLException
/*      */   {
/*  450 */     this.preparedStatement.setINTERVALYM(paramInt, paramINTERVALYM);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNUMBER(int paramInt, NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/*  458 */     this.preparedStatement.setNUMBER(paramInt, paramNUMBER);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setOPAQUE(int paramInt, OPAQUE paramOPAQUE)
/*      */     throws SQLException
/*      */   {
/*  466 */     this.preparedStatement.setOPAQUE(paramInt, paramOPAQUE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setOracleObject(int paramInt, Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/*  474 */     this.preparedStatement.setOracleObject(paramInt, paramDatum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setORAData(int paramInt, ORAData paramORAData)
/*      */     throws SQLException
/*      */   {
/*  482 */     this.preparedStatement.setORAData(paramInt, paramORAData);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRAW(int paramInt, RAW paramRAW)
/*      */     throws SQLException
/*      */   {
/*  490 */     this.preparedStatement.setRAW(paramInt, paramRAW);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setREF(int paramInt, REF paramREF)
/*      */     throws SQLException
/*      */   {
/*  498 */     this.preparedStatement.setREF(paramInt, paramREF);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRefType(int paramInt, REF paramREF)
/*      */     throws SQLException
/*      */   {
/*  506 */     this.preparedStatement.setRefType(paramInt, paramREF);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setROWID(int paramInt, ROWID paramROWID)
/*      */     throws SQLException
/*      */   {
/*  514 */     this.preparedStatement.setROWID(paramInt, paramROWID);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSTRUCT(int paramInt, STRUCT paramSTRUCT)
/*      */     throws SQLException
/*      */   {
/*  522 */     this.preparedStatement.setSTRUCT(paramInt, paramSTRUCT);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPLTZ(int paramInt, TIMESTAMPLTZ paramTIMESTAMPLTZ)
/*      */     throws SQLException
/*      */   {
/*  530 */     this.preparedStatement.setTIMESTAMPLTZ(paramInt, paramTIMESTAMPLTZ);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPTZ(int paramInt, TIMESTAMPTZ paramTIMESTAMPTZ)
/*      */     throws SQLException
/*      */   {
/*  538 */     this.preparedStatement.setTIMESTAMPTZ(paramInt, paramTIMESTAMPTZ);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMP(int paramInt, TIMESTAMP paramTIMESTAMP)
/*      */     throws SQLException
/*      */   {
/*  546 */     this.preparedStatement.setTIMESTAMP(paramInt, paramTIMESTAMP);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlob(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/*  554 */     this.preparedStatement.setBlob(paramInt, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlob(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  562 */     this.preparedStatement.setBlob(paramInt, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/*  570 */     this.preparedStatement.setClob(paramInt, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  578 */     this.preparedStatement.setClob(paramInt, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/*  586 */     this.preparedStatement.setNClob(paramInt, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  594 */     this.preparedStatement.setNClob(paramInt, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/*  602 */     this.preparedStatement.setAsciiStream(paramInt, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  610 */     this.preparedStatement.setAsciiStream(paramInt1, paramInputStream, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  618 */     this.preparedStatement.setAsciiStream(paramInt, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/*  626 */     this.preparedStatement.setBinaryStream(paramInt, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  634 */     this.preparedStatement.setBinaryStream(paramInt1, paramInputStream, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  642 */     this.preparedStatement.setBinaryStream(paramInt, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/*  650 */     this.preparedStatement.setCharacterStream(paramInt, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(int paramInt1, Reader paramReader, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  658 */     this.preparedStatement.setCharacterStream(paramInt1, paramReader, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  666 */     this.preparedStatement.setCharacterStream(paramInt, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/*  674 */     this.preparedStatement.setNCharacterStream(paramInt, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  682 */     this.preparedStatement.setNCharacterStream(paramInt, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setUnicodeStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  690 */     this.preparedStatement.setUnicodeStream(paramInt1, paramInputStream, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setArrayAtName(String paramString, Array paramArray)
/*      */     throws SQLException
/*      */   {
/*  699 */     this.preparedStatement.setArrayAtName(paramString, paramArray);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBigDecimalAtName(String paramString, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/*  707 */     this.preparedStatement.setBigDecimalAtName(paramString, paramBigDecimal);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlobAtName(String paramString, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/*  715 */     this.preparedStatement.setBlobAtName(paramString, paramBlob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBooleanAtName(String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  723 */     this.preparedStatement.setBooleanAtName(paramString, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setByteAtName(String paramString, byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  731 */     this.preparedStatement.setByteAtName(paramString, paramByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBytesAtName(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  739 */     this.preparedStatement.setBytesAtName(paramString, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClobAtName(String paramString, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/*  747 */     this.preparedStatement.setClobAtName(paramString, paramClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDateAtName(String paramString, Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  755 */     this.preparedStatement.setDateAtName(paramString, paramDate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDateAtName(String paramString, Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  763 */     this.preparedStatement.setDateAtName(paramString, paramDate, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDoubleAtName(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  771 */     this.preparedStatement.setDoubleAtName(paramString, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFloatAtName(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  779 */     this.preparedStatement.setFloatAtName(paramString, paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setIntAtName(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  787 */     this.preparedStatement.setIntAtName(paramString, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setLongAtName(String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  795 */     this.preparedStatement.setLongAtName(paramString, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClobAtName(String paramString, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/*  803 */     this.preparedStatement.setNClobAtName(paramString, paramNClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNStringAtName(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  811 */     this.preparedStatement.setNStringAtName(paramString1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setObjectAtName(String paramString, Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  819 */     this.preparedStatement.setObjectAtName(paramString, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setObjectAtName(String paramString, Object paramObject, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  827 */     this.preparedStatement.setObjectAtName(paramString, paramObject, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRefAtName(String paramString, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/*  835 */     this.preparedStatement.setRefAtName(paramString, paramRef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRowIdAtName(String paramString, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/*  843 */     this.preparedStatement.setRowIdAtName(paramString, paramRowId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setShortAtName(String paramString, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  851 */     this.preparedStatement.setShortAtName(paramString, paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSQLXMLAtName(String paramString, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/*  859 */     this.preparedStatement.setSQLXMLAtName(paramString, paramSQLXML);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setStringAtName(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/*  867 */     this.preparedStatement.setStringAtName(paramString1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTimeAtName(String paramString, Time paramTime)
/*      */     throws SQLException
/*      */   {
/*  875 */     this.preparedStatement.setTimeAtName(paramString, paramTime);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTimeAtName(String paramString, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  883 */     this.preparedStatement.setTimeAtName(paramString, paramTime, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTimestampAtName(String paramString, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/*  891 */     this.preparedStatement.setTimestampAtName(paramString, paramTimestamp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTimestampAtName(String paramString, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  899 */     this.preparedStatement.setTimestampAtName(paramString, paramTimestamp, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setURLAtName(String paramString, URL paramURL)
/*      */     throws SQLException
/*      */   {
/*  907 */     this.preparedStatement.setURLAtName(paramString, paramURL);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setARRAYAtName(String paramString, ARRAY paramARRAY)
/*      */     throws SQLException
/*      */   {
/*  915 */     this.preparedStatement.setARRAYAtName(paramString, paramARRAY);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBFILEAtName(String paramString, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  923 */     this.preparedStatement.setBFILEAtName(paramString, paramBFILE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBfileAtName(String paramString, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/*  931 */     this.preparedStatement.setBfileAtName(paramString, paramBFILE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryFloatAtName(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  939 */     this.preparedStatement.setBinaryFloatAtName(paramString, paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryFloatAtName(String paramString, BINARY_FLOAT paramBINARY_FLOAT)
/*      */     throws SQLException
/*      */   {
/*  947 */     this.preparedStatement.setBinaryFloatAtName(paramString, paramBINARY_FLOAT);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryDoubleAtName(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  955 */     this.preparedStatement.setBinaryDoubleAtName(paramString, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryDoubleAtName(String paramString, BINARY_DOUBLE paramBINARY_DOUBLE)
/*      */     throws SQLException
/*      */   {
/*  963 */     this.preparedStatement.setBinaryDoubleAtName(paramString, paramBINARY_DOUBLE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBLOBAtName(String paramString, BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/*  971 */     this.preparedStatement.setBLOBAtName(paramString, paramBLOB);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCHARAtName(String paramString, CHAR paramCHAR)
/*      */     throws SQLException
/*      */   {
/*  979 */     this.preparedStatement.setCHARAtName(paramString, paramCHAR);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCLOBAtName(String paramString, CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/*  987 */     this.preparedStatement.setCLOBAtName(paramString, paramCLOB);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCursorAtName(String paramString, ResultSet paramResultSet)
/*      */     throws SQLException
/*      */   {
/*  995 */     this.preparedStatement.setCursorAtName(paramString, paramResultSet);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCustomDatumAtName(String paramString, CustomDatum paramCustomDatum)
/*      */     throws SQLException
/*      */   {
/* 1003 */     this.preparedStatement.setCustomDatumAtName(paramString, paramCustomDatum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDATEAtName(String paramString, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/* 1011 */     this.preparedStatement.setDATEAtName(paramString, paramDATE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFixedCHARAtName(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 1019 */     this.preparedStatement.setFixedCHARAtName(paramString1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setINTERVALDSAtName(String paramString, INTERVALDS paramINTERVALDS)
/*      */     throws SQLException
/*      */   {
/* 1027 */     this.preparedStatement.setINTERVALDSAtName(paramString, paramINTERVALDS);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setINTERVALYMAtName(String paramString, INTERVALYM paramINTERVALYM)
/*      */     throws SQLException
/*      */   {
/* 1035 */     this.preparedStatement.setINTERVALYMAtName(paramString, paramINTERVALYM);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNUMBERAtName(String paramString, NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 1043 */     this.preparedStatement.setNUMBERAtName(paramString, paramNUMBER);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setOPAQUEAtName(String paramString, OPAQUE paramOPAQUE)
/*      */     throws SQLException
/*      */   {
/* 1051 */     this.preparedStatement.setOPAQUEAtName(paramString, paramOPAQUE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setOracleObjectAtName(String paramString, Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/* 1059 */     this.preparedStatement.setOracleObjectAtName(paramString, paramDatum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setORADataAtName(String paramString, ORAData paramORAData)
/*      */     throws SQLException
/*      */   {
/* 1067 */     this.preparedStatement.setORADataAtName(paramString, paramORAData);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRAWAtName(String paramString, RAW paramRAW)
/*      */     throws SQLException
/*      */   {
/* 1075 */     this.preparedStatement.setRAWAtName(paramString, paramRAW);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setREFAtName(String paramString, REF paramREF)
/*      */     throws SQLException
/*      */   {
/* 1083 */     this.preparedStatement.setREFAtName(paramString, paramREF);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRefTypeAtName(String paramString, REF paramREF)
/*      */     throws SQLException
/*      */   {
/* 1091 */     this.preparedStatement.setRefTypeAtName(paramString, paramREF);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setROWIDAtName(String paramString, ROWID paramROWID)
/*      */     throws SQLException
/*      */   {
/* 1099 */     this.preparedStatement.setROWIDAtName(paramString, paramROWID);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSTRUCTAtName(String paramString, STRUCT paramSTRUCT)
/*      */     throws SQLException
/*      */   {
/* 1107 */     this.preparedStatement.setSTRUCTAtName(paramString, paramSTRUCT);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPLTZAtName(String paramString, TIMESTAMPLTZ paramTIMESTAMPLTZ)
/*      */     throws SQLException
/*      */   {
/* 1115 */     this.preparedStatement.setTIMESTAMPLTZAtName(paramString, paramTIMESTAMPLTZ);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPTZAtName(String paramString, TIMESTAMPTZ paramTIMESTAMPTZ)
/*      */     throws SQLException
/*      */   {
/* 1123 */     this.preparedStatement.setTIMESTAMPTZAtName(paramString, paramTIMESTAMPTZ);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPAtName(String paramString, TIMESTAMP paramTIMESTAMP)
/*      */     throws SQLException
/*      */   {
/* 1131 */     this.preparedStatement.setTIMESTAMPAtName(paramString, paramTIMESTAMP);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlobAtName(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 1139 */     this.preparedStatement.setBlobAtName(paramString, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBlobAtName(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1147 */     this.preparedStatement.setBlobAtName(paramString, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClobAtName(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 1155 */     this.preparedStatement.setClobAtName(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setClobAtName(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1163 */     this.preparedStatement.setClobAtName(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClobAtName(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 1171 */     this.preparedStatement.setNClobAtName(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNClobAtName(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1179 */     this.preparedStatement.setNClobAtName(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStreamAtName(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 1187 */     this.preparedStatement.setAsciiStreamAtName(paramString, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStreamAtName(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1195 */     this.preparedStatement.setAsciiStreamAtName(paramString, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAsciiStreamAtName(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1203 */     this.preparedStatement.setAsciiStreamAtName(paramString, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStreamAtName(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 1211 */     this.preparedStatement.setBinaryStreamAtName(paramString, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStreamAtName(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1219 */     this.preparedStatement.setBinaryStreamAtName(paramString, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBinaryStreamAtName(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1227 */     this.preparedStatement.setBinaryStreamAtName(paramString, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStreamAtName(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 1235 */     this.preparedStatement.setCharacterStreamAtName(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStreamAtName(String paramString, Reader paramReader, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1243 */     this.preparedStatement.setCharacterStreamAtName(paramString, paramReader, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCharacterStreamAtName(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1251 */     this.preparedStatement.setCharacterStreamAtName(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNCharacterStreamAtName(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 1259 */     this.preparedStatement.setNCharacterStreamAtName(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNCharacterStreamAtName(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1267 */     this.preparedStatement.setNCharacterStreamAtName(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setUnicodeStreamAtName(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1275 */     this.preparedStatement.setUnicodeStreamAtName(paramString, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNull(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1285 */     this.preparedStatement.setNull(paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNull(int paramInt1, int paramInt2, String paramString)
/*      */     throws SQLException
/*      */   {
/* 1292 */     this.preparedStatement.setNull(paramInt1, paramInt2, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNullAtName(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1299 */     this.preparedStatement.setNullAtName(paramString, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNullAtName(String paramString1, int paramInt, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 1306 */     this.preparedStatement.setNullAtName(paramString1, paramInt, paramString2);
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
/* 1317 */     this.preparedStatement.setObject(paramInt1, paramObject, paramInt2, paramInt3);
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
/* 1328 */     this.preparedStatement.setObjectAtName(paramString, paramObject, paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setStructDescriptor(int paramInt, StructDescriptor paramStructDescriptor)
/*      */     throws SQLException
/*      */   {
/* 1337 */     this.preparedStatement.setStructDescriptor(paramInt, paramStructDescriptor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setStructDescriptorAtName(String paramString, StructDescriptor paramStructDescriptor)
/*      */     throws SQLException
/*      */   {
/* 1346 */     this.preparedStatement.setStructDescriptorAtName(paramString, paramStructDescriptor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int executeUpdate()
/*      */     throws SQLException
/*      */   {
/* 1354 */     return this.preparedStatement.executeUpdate();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void addBatch()
/*      */     throws SQLException
/*      */   {
/* 1362 */     this.preparedStatement.addBatch();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void clearParameters()
/*      */     throws SQLException
/*      */   {
/* 1370 */     this.preparedStatement.clearParameters();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean execute()
/*      */     throws SQLException
/*      */   {
/* 1378 */     return this.preparedStatement.execute();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCheckBindTypes(boolean paramBoolean)
/*      */   {
/* 1385 */     this.preparedStatement.setCheckBindTypes(paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSet getReturnResultSet()
/*      */     throws SQLException
/*      */   {
/* 1393 */     return this.preparedStatement.getReturnResultSet();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void defineParameterTypeBytes(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1401 */     this.preparedStatement.defineParameterTypeBytes(paramInt1, paramInt2, paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void defineParameterTypeChars(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1409 */     this.preparedStatement.defineParameterTypeChars(paramInt1, paramInt2, paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void defineParameterType(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1417 */     this.preparedStatement.defineParameterType(paramInt1, paramInt2, paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getExecuteBatch()
/*      */   {
/* 1424 */     return this.preparedStatement.getExecuteBatch();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int sendBatch()
/*      */     throws SQLException
/*      */   {
/* 1432 */     return this.preparedStatement.sendBatch();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPlsqlIndexTable(int paramInt1, Object paramObject, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*      */     throws SQLException
/*      */   {
/* 1441 */     this.preparedStatement.setPlsqlIndexTable(paramInt1, paramObject, paramInt2, paramInt3, paramInt4, paramInt5);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setFormOfUse(int paramInt, short paramShort)
/*      */   {
/* 1448 */     this.preparedStatement.setFormOfUse(paramInt, paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDisableStmtCaching(boolean paramBoolean)
/*      */   {
/* 1455 */     this.preparedStatement.setDisableStmtCaching(paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public OracleParameterMetaData OracleGetParameterMetaData()
/*      */     throws SQLException
/*      */   {
/* 1463 */     return this.preparedStatement.OracleGetParameterMetaData();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerReturnParameter(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1471 */     this.preparedStatement.registerReturnParameter(paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerReturnParameter(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1479 */     this.preparedStatement.registerReturnParameter(paramInt1, paramInt2, paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void registerReturnParameter(int paramInt1, int paramInt2, String paramString)
/*      */     throws SQLException
/*      */   {
/* 1487 */     this.preparedStatement.registerReturnParameter(paramInt1, paramInt2, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSet executeQuery()
/*      */     throws SQLException
/*      */   {
/* 1495 */     return this.preparedStatement.executeQuery();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSetMetaData getMetaData()
/*      */     throws SQLException
/*      */   {
/* 1503 */     return this.preparedStatement.getMetaData();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBytesForBlob(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1512 */     this.preparedStatement.setBytesForBlob(paramInt, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBytesForBlobAtName(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1520 */     this.preparedStatement.setBytesForBlobAtName(paramString, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setStringForClob(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 1529 */     this.preparedStatement.setStringForClob(paramInt, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setStringForClobAtName(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 1537 */     this.preparedStatement.setStringForClobAtName(paramString1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ParameterMetaData getParameterMetaData()
/*      */     throws SQLException
/*      */   {
/* 1545 */     return this.preparedStatement.getParameterMetaData();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setExecuteBatch(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1554 */     this.preparedStatement.setExecuteBatch(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isPoolable()
/*      */     throws SQLException
/*      */   {
/* 1566 */     return this.preparedStatement.isPoolable();
/*      */   }
/*      */   
/*      */   public void setPoolable(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1572 */     this.preparedStatement.setPoolable(paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setInternalBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1585 */     this.preparedStatement.setInternalBytes(paramInt1, paramArrayOfByte, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void enterImplicitCache()
/*      */     throws SQLException
/*      */   {
/* 1593 */     this.preparedStatement.enterImplicitCache();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void enterExplicitCache()
/*      */     throws SQLException
/*      */   {
/* 1601 */     this.preparedStatement.enterExplicitCache();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void exitImplicitCacheToActive()
/*      */     throws SQLException
/*      */   {
/* 1609 */     this.preparedStatement.exitImplicitCacheToActive();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void exitExplicitCacheToActive()
/*      */     throws SQLException
/*      */   {
/* 1617 */     this.preparedStatement.exitExplicitCacheToActive();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void exitImplicitCacheToClose()
/*      */     throws SQLException
/*      */   {
/* 1625 */     this.preparedStatement.exitImplicitCacheToClose();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void exitExplicitCacheToClose()
/*      */     throws SQLException
/*      */   {
/* 1633 */     this.preparedStatement.exitExplicitCacheToClose();
/*      */   }
/*      */   
/*      */ 
/*      */   public String getOriginalSql()
/*      */     throws SQLException
/*      */   {
/* 1640 */     return this.preparedStatement.getOriginalSql();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1645 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OraclePreparedStatementWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */