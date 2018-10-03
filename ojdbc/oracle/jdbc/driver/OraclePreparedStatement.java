/*       */ package oracle.jdbc.driver;
/*       */ 
/*       */ import java.io.BufferedInputStream;
/*       */ import java.io.BufferedReader;
/*       */ import java.io.ByteArrayInputStream;
/*       */ import java.io.IOException;
/*       */ import java.io.InputStream;
/*       */ import java.io.Reader;
/*       */ import java.io.StringReader;
/*       */ import java.math.BigDecimal;
/*       */ import java.math.BigInteger;
/*       */ import java.net.URL;
/*       */ import java.security.AccessController;
/*       */ import java.security.PrivilegedAction;
/*       */ import java.sql.Array;
/*       */ import java.sql.BatchUpdateException;
/*       */ import java.sql.Blob;
/*       */ import java.sql.Clob;
/*       */ import java.sql.Date;
/*       */ import java.sql.NClob;
/*       */ import java.sql.ParameterMetaData;
/*       */ import java.sql.Ref;
/*       */ import java.sql.ResultSet;
/*       */ import java.sql.ResultSetMetaData;
/*       */ import java.sql.RowId;
/*       */ import java.sql.SQLData;
/*       */ import java.sql.SQLException;
/*       */ import java.sql.SQLXML;
/*       */ import java.sql.Statement;
/*       */ import java.sql.Time;
/*       */ import java.sql.Timestamp;
/*       */ import java.util.Calendar;
/*       */ import java.util.Locale;
/*       */ import java.util.TimeZone;
/*       */ import oracle.jdbc.OracleData;
/*       */ import oracle.jdbc.internal.ObjectData;
/*       */ import oracle.jdbc.internal.OracleStatement.SqlKind;
/*       */ import oracle.jdbc.oracore.OracleTypeADT;
/*       */ import oracle.jdbc.oracore.OracleTypeNUMBER;
/*       */ import oracle.jdbc.proxy.OracleProxy;
/*       */ import oracle.jdbc.proxy.ProxyFactory;
/*       */ import oracle.sql.ARRAY;
/*       */ import oracle.sql.ArrayDescriptor;
/*       */ import oracle.sql.BFILE;
/*       */ import oracle.sql.BINARY_DOUBLE;
/*       */ import oracle.sql.BINARY_FLOAT;
/*       */ import oracle.sql.BLOB;
/*       */ import oracle.sql.CHAR;
/*       */ import oracle.sql.CLOB;
/*       */ import oracle.sql.CharacterSet;
/*       */ import oracle.sql.CustomDatum;
/*       */ import oracle.sql.DATE;
/*       */ import oracle.sql.Datum;
/*       */ import oracle.sql.INTERVALDS;
/*       */ import oracle.sql.INTERVALYM;
/*       */ import oracle.sql.NUMBER;
/*       */ import oracle.sql.OPAQUE;
/*       */ import oracle.sql.ORAData;
/*       */ import oracle.sql.OpaqueDescriptor;
/*       */ import oracle.sql.RAW;
/*       */ import oracle.sql.REF;
/*       */ import oracle.sql.ROWID;
/*       */ import oracle.sql.STRUCT;
/*       */ import oracle.sql.StructDescriptor;
/*       */ import oracle.sql.TIMESTAMP;
/*       */ import oracle.sql.TIMESTAMPLTZ;
/*       */ import oracle.sql.TIMESTAMPTZ;
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ abstract class OraclePreparedStatement
/*       */   extends OracleStatement
/*       */   implements oracle.jdbc.internal.OraclePreparedStatement, ScrollRsetStatement
/*       */ {
/*       */   int numberOfBindRowsAllocated;
/*   112 */   static Binder theStaticVarnumCopyingBinder = OraclePreparedStatementReadOnly.theStaticVarnumCopyingBinder;
/*       */   
/*   114 */   static Binder theStaticVarnumNullBinder = OraclePreparedStatementReadOnly.theStaticVarnumNullBinder;
/*       */   
/*   116 */   Binder theVarnumNullBinder = theStaticVarnumNullBinder;
/*       */   
/*   118 */   static Binder theStaticBooleanBinder = OraclePreparedStatementReadOnly.theStaticBooleanBinder;
/*       */   
/*   120 */   Binder theBooleanBinder = theStaticBooleanBinder;
/*       */   
/*   122 */   static Binder theStaticByteBinder = OraclePreparedStatementReadOnly.theStaticByteBinder;
/*       */   
/*   124 */   Binder theByteBinder = theStaticByteBinder;
/*       */   
/*   126 */   static Binder theStaticShortBinder = OraclePreparedStatementReadOnly.theStaticShortBinder;
/*       */   
/*   128 */   Binder theShortBinder = theStaticShortBinder;
/*       */   
/*   130 */   static Binder theStaticIntBinder = OraclePreparedStatementReadOnly.theStaticIntBinder;
/*       */   
/*   132 */   Binder theIntBinder = theStaticIntBinder;
/*       */   
/*   134 */   static Binder theStaticLongBinder = OraclePreparedStatementReadOnly.theStaticLongBinder;
/*       */   
/*   136 */   Binder theLongBinder = theStaticLongBinder;
/*       */   
/*   138 */   static Binder theStaticFloatBinder = OraclePreparedStatementReadOnly.theStaticFloatBinder;
/*       */   
/*   140 */   Binder theFloatBinder = null;
/*       */   
/*   142 */   static Binder theStaticDoubleBinder = OraclePreparedStatementReadOnly.theStaticDoubleBinder;
/*       */   
/*   144 */   Binder theDoubleBinder = null;
/*       */   
/*   146 */   static Binder theStaticBigDecimalBinder = OraclePreparedStatementReadOnly.theStaticBigDecimalBinder;
/*       */   
/*   148 */   Binder theBigDecimalBinder = theStaticBigDecimalBinder;
/*       */   
/*   150 */   static Binder theStaticVarcharCopyingBinder = OraclePreparedStatementReadOnly.theStaticVarcharCopyingBinder;
/*       */   
/*   152 */   static Binder theStaticVarcharNullBinder = OraclePreparedStatementReadOnly.theStaticVarcharNullBinder;
/*       */   
/*   154 */   Binder theVarcharNullBinder = theStaticVarcharNullBinder;
/*       */   
/*   156 */   static Binder theStaticStringBinder = OraclePreparedStatementReadOnly.theStaticStringBinder;
/*       */   
/*   158 */   Binder theStringBinder = theStaticStringBinder;
/*       */   
/*   160 */   static Binder theStaticSetCHARCopyingBinder = OraclePreparedStatementReadOnly.theStaticSetCHARCopyingBinder;
/*       */   
/*   162 */   static Binder theStaticSetCHARBinder = OraclePreparedStatementReadOnly.theStaticSetCHARBinder;
/*       */   
/*   164 */   static Binder theStaticLittleEndianSetCHARBinder = OraclePreparedStatementReadOnly.theStaticLittleEndianSetCHARBinder;
/*       */   
/*   166 */   static Binder theStaticSetCHARNullBinder = OraclePreparedStatementReadOnly.theStaticSetCHARNullBinder;
/*       */   
/*       */   Binder theSetCHARBinder;
/*   169 */   Binder theSetCHARNullBinder = theStaticSetCHARNullBinder;
/*       */   
/*   171 */   static Binder theStaticFixedCHARCopyingBinder = OraclePreparedStatementReadOnly.theStaticFixedCHARCopyingBinder;
/*       */   
/*   173 */   static Binder theStaticFixedCHARBinder = OraclePreparedStatementReadOnly.theStaticFixedCHARBinder;
/*       */   
/*   175 */   static Binder theStaticFixedCHARNullBinder = OraclePreparedStatementReadOnly.theStaticFixedCHARNullBinder;
/*       */   
/*   177 */   Binder theFixedCHARBinder = theStaticFixedCHARBinder;
/*   178 */   Binder theFixedCHARNullBinder = theStaticFixedCHARNullBinder;
/*       */   
/*   180 */   static Binder theStaticDateCopyingBinder = OraclePreparedStatementReadOnly.theStaticDateCopyingBinder;
/*       */   
/*   182 */   static Binder theStaticDateBinder = OraclePreparedStatementReadOnly.theStaticDateBinder;
/*       */   
/*   184 */   static Binder theStaticDateNullBinder = OraclePreparedStatementReadOnly.theStaticDateNullBinder;
/*       */   
/*   186 */   Binder theDateBinder = theStaticDateBinder;
/*   187 */   Binder theDateNullBinder = theStaticDateNullBinder;
/*       */   
/*   189 */   static Binder theStaticTimeCopyingBinder = OraclePreparedStatementReadOnly.theStaticTimeCopyingBinder;
/*       */   
/*   191 */   static Binder theStaticTimeBinder = OraclePreparedStatementReadOnly.theStaticTimeBinder;
/*       */   
/*   193 */   Binder theTimeBinder = theStaticTimeBinder;
/*       */   
/*   195 */   static Binder theStaticTimestampCopyingBinder = OraclePreparedStatementReadOnly.theStaticTimestampCopyingBinder;
/*       */   
/*   197 */   static Binder theStaticTimestampBinder = OraclePreparedStatementReadOnly.theStaticTimestampBinder;
/*       */   
/*   199 */   static Binder theStaticTimestampNullBinder = OraclePreparedStatementReadOnly.theStaticTimestampNullBinder;
/*       */   
/*   201 */   Binder theTimestampBinder = theStaticTimestampBinder;
/*   202 */   Binder theTimestampNullBinder = theStaticTimestampNullBinder;
/*       */   
/*   204 */   static Binder theStaticOracleNumberBinder = OraclePreparedStatementReadOnly.theStaticOracleNumberBinder;
/*       */   
/*   206 */   Binder theOracleNumberBinder = theStaticOracleNumberBinder;
/*       */   
/*   208 */   static Binder theStaticOracleDateBinder = OraclePreparedStatementReadOnly.theStaticOracleDateBinder;
/*       */   
/*   210 */   Binder theOracleDateBinder = theStaticOracleDateBinder;
/*       */   
/*   212 */   static Binder theStaticOracleTimestampBinder = OraclePreparedStatementReadOnly.theStaticOracleTimestampBinder;
/*       */   
/*   214 */   Binder theOracleTimestampBinder = theStaticOracleTimestampBinder;
/*       */   
/*   216 */   static Binder theStaticTSTZCopyingBinder = OraclePreparedStatementReadOnly.theStaticTSTZCopyingBinder;
/*       */   
/*   218 */   static Binder theStaticTSTZBinder = OraclePreparedStatementReadOnly.theStaticTSTZBinder;
/*       */   
/*   220 */   static Binder theStaticTSTZNullBinder = OraclePreparedStatementReadOnly.theStaticTSTZNullBinder;
/*       */   
/*   222 */   Binder theTSTZBinder = theStaticTSTZBinder;
/*   223 */   Binder theTSTZNullBinder = theStaticTSTZNullBinder;
/*       */   
/*   225 */   static Binder theStaticTSLTZCopyingBinder = OraclePreparedStatementReadOnly.theStaticTSLTZCopyingBinder;
/*       */   
/*   227 */   static Binder theStaticTSLTZBinder = OraclePreparedStatementReadOnly.theStaticTSLTZBinder;
/*       */   
/*   229 */   static Binder theStaticTSLTZNullBinder = OraclePreparedStatementReadOnly.theStaticTSLTZNullBinder;
/*       */   
/*   231 */   Binder theTSLTZBinder = theStaticTSLTZBinder;
/*   232 */   Binder theTSLTZNullBinder = theStaticTSLTZNullBinder;
/*       */   
/*   234 */   static Binder theStaticRowidCopyingBinder = OraclePreparedStatementReadOnly.theStaticRowidCopyingBinder;
/*       */   
/*   236 */   static Binder theStaticRowidBinder = OraclePreparedStatementReadOnly.theStaticRowidBinder;
/*       */   
/*   238 */   static Binder theStaticLittleEndianRowidBinder = OraclePreparedStatementReadOnly.theStaticLittleEndianRowidBinder;
/*       */   
/*   240 */   static Binder theStaticRowidNullBinder = OraclePreparedStatementReadOnly.theStaticRowidNullBinder;
/*       */   
/*   242 */   static Binder theStaticURowidNullBinder = OraclePreparedStatementReadOnly.theStaticURowidNullBinder;
/*       */   
/*       */   Binder theRowidBinder;
/*   245 */   Binder theRowidNullBinder = theStaticRowidNullBinder;
/*       */   
/*       */   Binder theURowidBinder;
/*   248 */   Binder theURowidNullBinder = theStaticURowidNullBinder;
/*       */   
/*   250 */   static Binder theStaticIntervalDSCopyingBinder = OraclePreparedStatementReadOnly.theStaticIntervalDSCopyingBinder;
/*       */   
/*   252 */   static Binder theStaticIntervalDSBinder = OraclePreparedStatementReadOnly.theStaticIntervalDSBinder;
/*       */   
/*   254 */   static Binder theStaticIntervalDSNullBinder = OraclePreparedStatementReadOnly.theStaticIntervalDSNullBinder;
/*       */   
/*   256 */   Binder theIntervalDSBinder = theStaticIntervalDSBinder;
/*   257 */   Binder theIntervalDSNullBinder = theStaticIntervalDSNullBinder;
/*       */   
/*   259 */   static Binder theStaticIntervalYMCopyingBinder = OraclePreparedStatementReadOnly.theStaticIntervalYMCopyingBinder;
/*       */   
/*   261 */   static Binder theStaticIntervalYMBinder = OraclePreparedStatementReadOnly.theStaticIntervalYMBinder;
/*       */   
/*   263 */   static Binder theStaticIntervalYMNullBinder = OraclePreparedStatementReadOnly.theStaticIntervalYMNullBinder;
/*       */   
/*   265 */   Binder theIntervalYMBinder = theStaticIntervalYMBinder;
/*   266 */   Binder theIntervalYMNullBinder = theStaticIntervalYMNullBinder;
/*       */   
/*   268 */   static Binder theStaticBfileCopyingBinder = OraclePreparedStatementReadOnly.theStaticBfileCopyingBinder;
/*       */   
/*   270 */   static Binder theStaticBfileBinder = OraclePreparedStatementReadOnly.theStaticBfileBinder;
/*       */   
/*   272 */   static Binder theStaticBfileNullBinder = OraclePreparedStatementReadOnly.theStaticBfileNullBinder;
/*       */   
/*   274 */   Binder theBfileBinder = theStaticBfileBinder;
/*   275 */   Binder theBfileNullBinder = theStaticBfileNullBinder;
/*       */   
/*   277 */   static Binder theStaticBlobCopyingBinder = OraclePreparedStatementReadOnly.theStaticBlobCopyingBinder;
/*       */   
/*   279 */   static Binder theStaticBlobBinder = OraclePreparedStatementReadOnly.theStaticBlobBinder;
/*       */   
/*   281 */   static Binder theStaticBlobNullBinder = OraclePreparedStatementReadOnly.theStaticBlobNullBinder;
/*       */   
/*   283 */   Binder theBlobBinder = theStaticBlobBinder;
/*   284 */   Binder theBlobNullBinder = theStaticBlobNullBinder;
/*       */   
/*   286 */   static Binder theStaticClobCopyingBinder = OraclePreparedStatementReadOnly.theStaticClobCopyingBinder;
/*       */   
/*   288 */   static Binder theStaticClobBinder = OraclePreparedStatementReadOnly.theStaticClobBinder;
/*       */   
/*   290 */   static Binder theStaticClobNullBinder = OraclePreparedStatementReadOnly.theStaticClobNullBinder;
/*       */   
/*   292 */   Binder theClobBinder = theStaticClobBinder;
/*   293 */   Binder theClobNullBinder = theStaticClobNullBinder;
/*       */   
/*   295 */   static Binder theStaticRawCopyingBinder = OraclePreparedStatementReadOnly.theStaticRawCopyingBinder;
/*       */   
/*   297 */   static Binder theStaticRawBinder = OraclePreparedStatementReadOnly.theStaticRawBinder;
/*       */   
/*   299 */   static Binder theStaticRawNullBinder = OraclePreparedStatementReadOnly.theStaticRawNullBinder;
/*       */   
/*   301 */   Binder theRawBinder = theStaticRawBinder;
/*   302 */   Binder theRawNullBinder = theStaticRawNullBinder;
/*       */   
/*   304 */   static Binder theStaticPlsqlRawCopyingBinder = OraclePreparedStatementReadOnly.theStaticPlsqlRawCopyingBinder;
/*       */   
/*   306 */   static Binder theStaticPlsqlRawBinder = OraclePreparedStatementReadOnly.theStaticPlsqlRawBinder;
/*       */   
/*   308 */   Binder thePlsqlRawBinder = theStaticPlsqlRawBinder;
/*       */   
/*   310 */   static Binder theStaticBinaryFloatCopyingBinder = OraclePreparedStatementReadOnly.theStaticBinaryFloatCopyingBinder;
/*       */   
/*   312 */   static Binder theStaticBinaryFloatBinder = OraclePreparedStatementReadOnly.theStaticBinaryFloatBinder;
/*       */   
/*   314 */   static Binder theStaticBinaryFloatNullBinder = OraclePreparedStatementReadOnly.theStaticBinaryFloatNullBinder;
/*       */   
/*   316 */   Binder theBinaryFloatBinder = theStaticBinaryFloatBinder;
/*   317 */   Binder theBinaryFloatNullBinder = theStaticBinaryFloatNullBinder;
/*       */   
/*   319 */   static Binder theStaticBINARY_FLOATCopyingBinder = OraclePreparedStatementReadOnly.theStaticBINARY_FLOATCopyingBinder;
/*       */   
/*   321 */   static Binder theStaticBINARY_FLOATBinder = OraclePreparedStatementReadOnly.theStaticBINARY_FLOATBinder;
/*       */   
/*   323 */   static Binder theStaticBINARY_FLOATNullBinder = OraclePreparedStatementReadOnly.theStaticBINARY_FLOATNullBinder;
/*       */   
/*   325 */   Binder theBINARY_FLOATBinder = theStaticBINARY_FLOATBinder;
/*   326 */   Binder theBINARY_FLOATNullBinder = theStaticBINARY_FLOATNullBinder;
/*       */   
/*   328 */   static Binder theStaticBinaryDoubleCopyingBinder = OraclePreparedStatementReadOnly.theStaticBinaryDoubleCopyingBinder;
/*       */   
/*   330 */   static Binder theStaticBinaryDoubleBinder = OraclePreparedStatementReadOnly.theStaticBinaryDoubleBinder;
/*       */   
/*   332 */   static Binder theStaticBinaryDoubleNullBinder = OraclePreparedStatementReadOnly.theStaticBinaryDoubleNullBinder;
/*       */   
/*   334 */   Binder theBinaryDoubleBinder = theStaticBinaryDoubleBinder;
/*   335 */   Binder theBinaryDoubleNullBinder = theStaticBinaryDoubleNullBinder;
/*       */   
/*   337 */   static Binder theStaticBINARY_DOUBLECopyingBinder = OraclePreparedStatementReadOnly.theStaticBINARY_DOUBLECopyingBinder;
/*       */   
/*   339 */   static Binder theStaticBINARY_DOUBLEBinder = OraclePreparedStatementReadOnly.theStaticBINARY_DOUBLEBinder;
/*       */   
/*   341 */   static Binder theStaticBINARY_DOUBLENullBinder = OraclePreparedStatementReadOnly.theStaticBINARY_DOUBLENullBinder;
/*       */   
/*   343 */   Binder theBINARY_DOUBLEBinder = theStaticBINARY_DOUBLEBinder;
/*   344 */   Binder theBINARY_DOUBLENullBinder = theStaticBINARY_DOUBLENullBinder;
/*       */   
/*   346 */   static Binder theStaticLongStreamBinder = OraclePreparedStatementReadOnly.theStaticLongStreamBinder;
/*       */   
/*   348 */   Binder theLongStreamBinder = theStaticLongStreamBinder;
/*       */   
/*   350 */   static Binder theStaticLongStreamForStringBinder = OraclePreparedStatementReadOnly.theStaticLongStreamForStringBinder;
/*       */   
/*   352 */   Binder theLongStreamForStringBinder = theStaticLongStreamForStringBinder;
/*   353 */   static Binder theStaticLongStreamForStringCopyingBinder = OraclePreparedStatementReadOnly.theStaticLongStreamForStringCopyingBinder;
/*       */   
/*       */ 
/*   356 */   static Binder theStaticLongRawStreamBinder = OraclePreparedStatementReadOnly.theStaticLongRawStreamBinder;
/*       */   
/*   358 */   Binder theLongRawStreamBinder = theStaticLongRawStreamBinder;
/*       */   
/*   360 */   static Binder theStaticLongRawStreamForBytesBinder = OraclePreparedStatementReadOnly.theStaticLongRawStreamForBytesBinder;
/*       */   
/*   362 */   Binder theLongRawStreamForBytesBinder = theStaticLongRawStreamForBytesBinder;
/*   363 */   static Binder theStaticLongRawStreamForBytesCopyingBinder = OraclePreparedStatementReadOnly.theStaticLongRawStreamForBytesCopyingBinder;
/*       */   
/*       */ 
/*   366 */   static Binder theStaticNamedTypeCopyingBinder = OraclePreparedStatementReadOnly.theStaticNamedTypeCopyingBinder;
/*       */   
/*   368 */   static Binder theStaticNamedTypeBinder = OraclePreparedStatementReadOnly.theStaticNamedTypeBinder;
/*       */   
/*   370 */   static Binder theStaticNamedTypeNullBinder = OraclePreparedStatementReadOnly.theStaticNamedTypeNullBinder;
/*       */   
/*   372 */   Binder theNamedTypeBinder = theStaticNamedTypeBinder;
/*   373 */   Binder theNamedTypeNullBinder = theStaticNamedTypeNullBinder;
/*       */   
/*   375 */   static Binder theStaticRefTypeCopyingBinder = OraclePreparedStatementReadOnly.theStaticRefTypeCopyingBinder;
/*       */   
/*   377 */   static Binder theStaticRefTypeBinder = OraclePreparedStatementReadOnly.theStaticRefTypeBinder;
/*       */   
/*   379 */   static Binder theStaticRefTypeNullBinder = OraclePreparedStatementReadOnly.theStaticRefTypeNullBinder;
/*       */   
/*   381 */   Binder theRefTypeBinder = theStaticRefTypeBinder;
/*   382 */   Binder theRefTypeNullBinder = theStaticRefTypeNullBinder;
/*       */   
/*   384 */   static Binder theStaticPlsqlIbtCopyingBinder = OraclePreparedStatementReadOnly.theStaticPlsqlIbtCopyingBinder;
/*       */   
/*   386 */   static Binder theStaticPlsqlIbtBinder = OraclePreparedStatementReadOnly.theStaticPlsqlIbtBinder;
/*       */   
/*   388 */   static Binder theStaticPlsqlIbtNullBinder = OraclePreparedStatementReadOnly.theStaticPlsqlIbtNullBinder;
/*       */   
/*   390 */   Binder thePlsqlIbtBinder = theStaticPlsqlIbtBinder;
/*   391 */   Binder thePlsqlNullBinder = theStaticPlsqlIbtNullBinder;
/*       */   
/*   393 */   static Binder theStaticOutBinder = OraclePreparedStatementReadOnly.theStaticOutBinder;
/*       */   
/*   395 */   Binder theOutBinder = theStaticOutBinder;
/*       */   
/*   397 */   static Binder theStaticReturnParamBinder = OraclePreparedStatementReadOnly.theStaticReturnParamBinder;
/*       */   
/*   399 */   Binder theReturnParamBinder = theStaticReturnParamBinder;
/*       */   
/*   401 */   static Binder theStaticT4CRowidBinder = OraclePreparedStatementReadOnly.theStaticT4CRowidBinder;
/*       */   
/*   403 */   static Binder theStaticT4CURowidBinder = OraclePreparedStatementReadOnly.theStaticT4CURowidBinder;
/*       */   
/*   405 */   static Binder theStaticT4CRowidNullBinder = OraclePreparedStatementReadOnly.theStaticT4CRowidNullBinder;
/*       */   
/*   407 */   static Binder theStaticT4CURowidNullBinder = OraclePreparedStatementReadOnly.theStaticT4CURowidNullBinder;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*   414 */   private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");
/*   415 */   private static final Calendar UTC_US_CALENDAR = Calendar.getInstance(UTC_TIME_ZONE, Locale.US);
/*       */   
/*   417 */   protected Calendar cachedUTCUSCalendar = (Calendar)UTC_US_CALENDAR.clone();
/*       */   
/*       */   public static final int TypeBinder_BYTELEN = 24;
/*       */   
/*   421 */   char[] digits = new char[20];
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   Binder[][] binders;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int[][] parameterInt;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   long[][] parameterLong;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   float[][] parameterFloat;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   double[][] parameterDouble;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   BigDecimal[][] parameterBigDecimal;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   String[][] parameterString;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   Date[][] parameterDate;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   Time[][] parameterTime;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   Timestamp[][] parameterTimestamp;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   byte[][][] parameterDatum;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   OracleTypeADT[][] parameterOtype;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   CLOB[] lastBoundClobs;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   BLOB[] lastBoundBlobs;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   PlsqlIbtBindInfo[][] parameterPlsqlIbt;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   Binder[] currentRowBinders;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int[] currentRowCharLens;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   Accessor[] currentRowBindAccessors;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   short[] currentRowFormOfUse;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*   539 */   boolean currentRowNeedToPrepareBinds = true;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int[] currentBatchCharLens;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   Accessor[] currentBatchBindAccessors;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   short[] currentBatchFormOfUse;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   boolean currentBatchNeedToPrepareBinds;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   PushedBatch pushedBatches;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   PushedBatch pushedBatchesTail;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*   879 */   int cachedBindByteSize = 0;
/*   880 */   int cachedBindCharSize = 0;
/*   881 */   int cachedBindIndicatorSize = 0;
/*       */   
/*       */ 
/*       */ 
/*       */   int totalBindByteLength;
/*       */   
/*       */ 
/*       */ 
/*       */   int totalBindCharLength;
/*       */   
/*       */ 
/*       */ 
/*       */   int totalBindIndicatorLength;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_NUMBER_OF_BIND_POSITIONS_OFFSET = 0;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_BIND_BUFFER_CAPACITY_OFFSET_HI = 1;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_BIND_BUFFER_CAPACITY_OFFSET_LO = 2;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_NUMBER_OF_BOUND_ROWS_OFFSET_HI = 3;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_NUMBER_OF_BOUND_ROWS_OFFSET_LO = 4;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_PER_POSITION_DATA_OFFSET = 5;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_TYPE_OFFSET = 0;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_BYTE_PITCH_OFFSET = 1;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_CHAR_PITCH_OFFSET = 2;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_VALUE_DATA_OFFSET_HI = 3;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_VALUE_DATA_OFFSET_LO = 4;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_NULL_INDICATORS_OFFSET_HI = 5;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_NULL_INDICATORS_OFFSET_LO = 6;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_VALUE_LENGTHS_OFFSET_HI = 7;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_VALUE_LENGTHS_OFFSET_LO = 8;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_FORM_OF_USE_OFFSET = 9;
/*       */   
/*       */ 
/*       */   static final int BIND_METADATA_PER_POSITION_SIZE = 10;
/*       */   
/*       */ 
/*       */   static final int SETLOB_NO_LENGTH = -1;
/*       */   
/*       */ 
/*       */   int bindBufferCapacity;
/*       */   
/*       */ 
/*       */   int numberOfBoundRows;
/*       */   
/*       */ 
/*       */   int indicatorsOffset;
/*       */   
/*       */ 
/*       */   int valueLengthsOffset;
/*       */   
/*       */ 
/*       */   boolean preparedAllBinds;
/*       */   
/*       */ 
/*       */   boolean preparedCharBinds;
/*       */   
/*       */ 
/*       */   Binder[] lastBinders;
/*       */   
/*       */ 
/*       */   byte[] lastBoundBytes;
/*       */   
/*       */ 
/*       */   int lastBoundByteOffset;
/*       */   
/*       */ 
/*       */   char[] lastBoundChars;
/*       */   
/*       */ 
/*       */   int lastBoundCharOffset;
/*       */   
/*       */ 
/*       */   int[] lastBoundByteOffsets;
/*       */   
/*       */ 
/*       */   int[] lastBoundCharOffsets;
/*       */   
/*       */ 
/*       */   int[] lastBoundByteLens;
/*       */   
/*       */ 
/*       */   int[] lastBoundCharLens;
/*       */   
/*       */ 
/*       */   short[] lastBoundInds;
/*       */   
/*       */ 
/*       */   short[] lastBoundLens;
/*       */   
/*       */ 
/*  1001 */   boolean lastBoundNeeded = false;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   byte[][] lastBoundTypeBytes;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   OracleTypeADT[] lastBoundTypeOtypes;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   InputStream[] lastBoundStream;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private static final int STREAM_MAX_BYTES_SQL = Integer.MAX_VALUE;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int maxRawBytesSql;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int maxRawBytesPlsql;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int maxVcsCharsSql;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int maxVcsNCharsSql;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int maxVcsBytesPlsql;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1102 */   private int maxCharSize = 0;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1108 */   private int maxNCharSize = 0;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1116 */   private int charMaxCharsSql = 0;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1124 */   private int charMaxNCharsSql = 0;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1131 */   private int maxVcsCharsPlsql = 0;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1138 */   private int maxVcsNCharsPlsql = 0;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1144 */   int maxIbtVarcharElementLength = 0;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1152 */   private int maxStreamCharsSql = 0;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1160 */   private int maxStreamNCharsSql = 0;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1166 */   protected boolean isServerCharSetFixedWidth = false;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1172 */   private boolean isServerNCharSetFixedWidth = false;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int minVcsBindSize;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int prematureBatchCount;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1223 */   boolean checkBindTypes = true;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   boolean scrollRsetTypeSolved;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private static final double MIN_NUMBER = 1.0E-130D;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private static final double MAX_NUMBER = 1.0E126D;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   OraclePreparedStatement(PhysicalConnection paramPhysicalConnection, String paramString, int paramInt1, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  1256 */     this(paramPhysicalConnection, paramString, paramInt1, paramInt2, 1003, 1007);
/*       */     
/*       */ 
/*  1259 */     this.cacheState = 1;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   OraclePreparedStatement(PhysicalConnection paramPhysicalConnection, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*       */     throws SQLException
/*       */   {
/*  1271 */     super(paramPhysicalConnection, paramInt1, paramInt2, paramInt3, paramInt4);
/*       */     
/*       */ 
/*       */ 
/*  1275 */     this.cacheState = 1;
/*       */     
/*  1277 */     if (paramInt1 > 1) {
/*  1278 */       setOracleBatchStyle();
/*       */     }
/*  1280 */     this.theSetCHARBinder = (paramPhysicalConnection.useLittleEndianSetCHARBinder() ? theStaticLittleEndianSetCHARBinder : theStaticSetCHARBinder);
/*       */     
/*       */ 
/*       */ 
/*  1284 */     this.theURowidBinder = (this.theRowidBinder = paramPhysicalConnection.useLittleEndianSetCHARBinder() ? theStaticLittleEndianRowidBinder : theStaticRowidBinder);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1290 */     this.statementType = 1;
/*  1291 */     this.currentRow = -1;
/*  1292 */     this.needToParse = true;
/*       */     
/*  1294 */     this.processEscapes = paramPhysicalConnection.processEscapes;
/*  1295 */     this.sqlObject.initialize(paramString);
/*       */     
/*  1297 */     this.sqlKind = this.sqlObject.getSqlKind();
/*       */     
/*  1299 */     this.clearParameters = true;
/*  1300 */     this.scrollRsetTypeSolved = false;
/*  1301 */     this.prematureBatchCount = 0;
/*       */     
/*  1303 */     initializeBinds();
/*       */     
/*  1305 */     this.minVcsBindSize = paramPhysicalConnection.minVcsBindSize;
/*  1306 */     this.maxRawBytesSql = paramPhysicalConnection.maxRawBytesSql;
/*  1307 */     this.maxRawBytesPlsql = paramPhysicalConnection.maxRawBytesPlsql;
/*  1308 */     this.maxVcsCharsSql = paramPhysicalConnection.maxVcsCharsSql;
/*  1309 */     this.maxVcsNCharsSql = paramPhysicalConnection.maxVcsNCharsSql;
/*  1310 */     this.maxVcsBytesPlsql = paramPhysicalConnection.maxVcsBytesPlsql;
/*  1311 */     this.maxIbtVarcharElementLength = paramPhysicalConnection.maxIbtVarcharElementLength;
/*  1312 */     this.maxCharSize = this.connection.conversion.sMaxCharSize;
/*  1313 */     this.maxNCharSize = this.connection.conversion.maxNCharSize;
/*  1314 */     this.maxVcsCharsPlsql = (this.maxVcsBytesPlsql / this.maxCharSize);
/*  1315 */     this.maxVcsNCharsPlsql = (this.maxVcsBytesPlsql / this.maxNCharSize);
/*  1316 */     this.maxStreamCharsSql = (Integer.MAX_VALUE / this.maxCharSize);
/*  1317 */     this.maxStreamNCharsSql = (this.maxRawBytesSql / this.maxNCharSize);
/*  1318 */     this.isServerCharSetFixedWidth = this.connection.conversion.isServerCharSetFixedWidth;
/*  1319 */     this.isServerNCharSetFixedWidth = this.connection.conversion.isServerNCharSetFixedWidth;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void allocBinds(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  1335 */     int i = paramInt > this.numberOfBindRowsAllocated ? 1 : 0;
/*       */     
/*       */ 
/*       */ 
/*  1339 */     initializeIndicatorSubRange();
/*       */     
/*       */ 
/*       */ 
/*  1343 */     int j = this.bindIndicatorSubRange + 5 + this.numberOfBindPositions * 10;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1350 */     int k = paramInt * this.numberOfBindPositions;
/*       */     
/*       */ 
/*  1353 */     int m = j + 2 * k;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1359 */     if (m > this.totalBindIndicatorLength)
/*       */     {
/*  1361 */       short[] arrayOfShort = this.bindIndicators;
/*  1362 */       i1 = this.bindIndicatorOffset;
/*       */       
/*  1364 */       this.bindIndicatorOffset = 0;
/*  1365 */       this.bindIndicators = new short[m];
/*  1366 */       this.totalBindIndicatorLength = m;
/*       */       
/*  1368 */       if ((arrayOfShort != null) && (i != 0))
/*       */       {
/*       */ 
/*  1371 */         System.arraycopy(arrayOfShort, i1, this.bindIndicators, this.bindIndicatorOffset, j);
/*       */       }
/*       */     }
/*       */     
/*       */ 
/*  1376 */     this.bindIndicatorSubRange += this.bindIndicatorOffset;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1383 */     this.bindIndicators[(this.bindIndicatorSubRange + 0)] = ((short)this.numberOfBindPositions);
/*       */     
/*       */ 
/*       */ 
/*  1387 */     this.indicatorsOffset = (this.bindIndicatorOffset + j);
/*  1388 */     this.valueLengthsOffset = (this.indicatorsOffset + k);
/*       */     
/*  1390 */     int n = this.indicatorsOffset;
/*  1391 */     int i1 = this.valueLengthsOffset;
/*  1392 */     int i2 = this.bindIndicatorSubRange + 5;
/*       */     
/*       */ 
/*       */ 
/*  1396 */     for (int i3 = 0; i3 < this.numberOfBindPositions; i3++)
/*       */     {
/*       */ 
/*       */ 
/*  1400 */       this.bindIndicators[(i2 + 5)] = ((short)(n >> 16));
/*       */       
/*       */ 
/*  1403 */       this.bindIndicators[(i2 + 6)] = ((short)(n & 0xFFFF));
/*       */       
/*       */ 
/*  1406 */       this.bindIndicators[(i2 + 7)] = ((short)(i1 >> 16));
/*       */       
/*  1408 */       this.bindIndicators[(i2 + 8)] = ((short)(i1 & 0xFFFF));
/*       */       
/*       */ 
/*       */ 
/*  1412 */       n += paramInt;
/*  1413 */       i1 += paramInt;
/*  1414 */       i2 += 10;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void initializeBinds()
/*       */     throws SQLException
/*       */   {
/*  1434 */     this.numberOfBindPositions = this.sqlObject.getParameterCount();
/*  1435 */     this.numReturnParams = this.sqlObject.getReturnParameterCount();
/*       */     
/*  1437 */     if (this.numberOfBindPositions == 0)
/*       */     {
/*       */ 
/*       */ 
/*  1441 */       this.currentRowNeedToPrepareBinds = false;
/*       */       
/*  1443 */       return;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  1449 */     this.numberOfBindRowsAllocated = this.batch;
/*       */     
/*       */ 
/*  1452 */     this.binders = new Binder[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     
/*  1454 */     this.currentRowBinders = this.binders[0];
/*       */     
/*  1456 */     this.currentRowCharLens = new int[this.numberOfBindPositions];
/*  1457 */     this.currentBatchCharLens = new int[this.numberOfBindPositions];
/*       */     
/*  1459 */     this.currentRowFormOfUse = new short[this.numberOfBindPositions];
/*  1460 */     this.currentBatchFormOfUse = new short[this.numberOfBindPositions];
/*       */     
/*  1462 */     this.lastBoundClobs = new CLOB[this.numberOfBindPositions];
/*  1463 */     this.lastBoundBlobs = new BLOB[this.numberOfBindPositions];
/*       */     
/*  1465 */     int i = 1;
/*       */     
/*  1467 */     if (this.connection.defaultnchar) {
/*  1468 */       i = 2;
/*       */     }
/*  1470 */     for (int j = 0; j < this.numberOfBindPositions; j++)
/*       */     {
/*  1472 */       this.currentRowFormOfUse[j] = i;
/*  1473 */       this.currentBatchFormOfUse[j] = i;
/*       */     }
/*       */     
/*  1476 */     this.lastBinders = new Binder[this.numberOfBindPositions];
/*  1477 */     this.lastBoundCharLens = new int[this.numberOfBindPositions];
/*  1478 */     this.lastBoundByteOffsets = new int[this.numberOfBindPositions];
/*  1479 */     this.lastBoundCharOffsets = new int[this.numberOfBindPositions];
/*  1480 */     this.lastBoundByteLens = new int[this.numberOfBindPositions];
/*  1481 */     this.lastBoundInds = new short[this.numberOfBindPositions];
/*  1482 */     this.lastBoundLens = new short[this.numberOfBindPositions];
/*       */     
/*  1484 */     this.lastBoundTypeBytes = new byte[this.numberOfBindPositions][];
/*  1485 */     this.lastBoundTypeOtypes = new OracleTypeADT[this.numberOfBindPositions];
/*       */     
/*       */ 
/*  1488 */     allocBinds(this.numberOfBindRowsAllocated);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void growBinds(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  1507 */     Binder[][] arrayOfBinder = this.binders;
/*       */     
/*  1509 */     this.binders = new Binder[paramInt][];
/*       */     
/*       */ 
/*  1512 */     if (arrayOfBinder != null) {
/*  1513 */       System.arraycopy(arrayOfBinder, 0, this.binders, 0, this.numberOfBindRowsAllocated);
/*       */     }
/*       */     
/*       */ 
/*  1517 */     for (int i = this.numberOfBindRowsAllocated; i < paramInt; 
/*  1518 */         i++) {
/*  1519 */       this.binders[i] = new Binder[this.numberOfBindPositions];
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  1525 */     allocBinds(paramInt);
/*       */     
/*       */     Object localObject;
/*       */     
/*  1529 */     if (this.parameterInt != null)
/*       */     {
/*  1531 */       localObject = this.parameterInt;
/*       */       
/*  1533 */       this.parameterInt = new int[paramInt][];
/*       */       
/*  1535 */       System.arraycopy(localObject, 0, this.parameterInt, 0, this.numberOfBindRowsAllocated);
/*       */       
/*       */ 
/*  1538 */       for (i = this.numberOfBindRowsAllocated; 
/*  1539 */           i < paramInt; i++) {
/*  1540 */         this.parameterInt[i] = new int[this.numberOfBindPositions];
/*       */       }
/*       */     }
/*  1543 */     if (this.parameterLong != null)
/*       */     {
/*  1545 */       localObject = this.parameterLong;
/*       */       
/*  1547 */       this.parameterLong = new long[paramInt][];
/*       */       
/*  1549 */       System.arraycopy(localObject, 0, this.parameterLong, 0, this.numberOfBindRowsAllocated);
/*       */       
/*       */ 
/*  1552 */       for (i = this.numberOfBindRowsAllocated; 
/*  1553 */           i < paramInt; i++) {
/*  1554 */         this.parameterLong[i] = new long[this.numberOfBindPositions];
/*       */       }
/*       */     }
/*  1557 */     if (this.parameterFloat != null)
/*       */     {
/*  1559 */       localObject = this.parameterFloat;
/*       */       
/*  1561 */       this.parameterFloat = new float[paramInt][];
/*       */       
/*  1563 */       System.arraycopy(localObject, 0, this.parameterFloat, 0, this.numberOfBindRowsAllocated);
/*       */       
/*       */ 
/*  1566 */       for (i = this.numberOfBindRowsAllocated; 
/*  1567 */           i < paramInt; i++) {
/*  1568 */         this.parameterFloat[i] = new float[this.numberOfBindPositions];
/*       */       }
/*       */     }
/*  1571 */     if (this.parameterDouble != null)
/*       */     {
/*  1573 */       localObject = this.parameterDouble;
/*       */       
/*  1575 */       this.parameterDouble = new double[paramInt][];
/*       */       
/*  1577 */       System.arraycopy(localObject, 0, this.parameterDouble, 0, this.numberOfBindRowsAllocated);
/*       */       
/*       */ 
/*  1580 */       for (i = this.numberOfBindRowsAllocated; 
/*  1581 */           i < paramInt; i++) {
/*  1582 */         this.parameterDouble[i] = new double[this.numberOfBindPositions];
/*       */       }
/*       */     }
/*  1585 */     if (this.parameterBigDecimal != null)
/*       */     {
/*  1587 */       localObject = this.parameterBigDecimal;
/*       */       
/*  1589 */       this.parameterBigDecimal = new BigDecimal[paramInt][];
/*       */       
/*       */ 
/*  1592 */       System.arraycopy(localObject, 0, this.parameterBigDecimal, 0, this.numberOfBindRowsAllocated);
/*       */       
/*       */ 
/*  1595 */       for (i = this.numberOfBindRowsAllocated; 
/*  1596 */           i < paramInt; i++) {
/*  1597 */         this.parameterBigDecimal[i] = new BigDecimal[this.numberOfBindPositions];
/*       */       }
/*       */     }
/*  1600 */     if (this.parameterString != null)
/*       */     {
/*  1602 */       localObject = this.parameterString;
/*       */       
/*  1604 */       this.parameterString = new String[paramInt][];
/*       */       
/*  1606 */       System.arraycopy(localObject, 0, this.parameterString, 0, this.numberOfBindRowsAllocated);
/*       */       
/*       */ 
/*  1609 */       for (i = this.numberOfBindRowsAllocated; 
/*  1610 */           i < paramInt; i++) {
/*  1611 */         this.parameterString[i] = new String[this.numberOfBindPositions];
/*       */       }
/*       */     }
/*  1614 */     if (this.parameterDate != null)
/*       */     {
/*  1616 */       localObject = this.parameterDate;
/*       */       
/*  1618 */       this.parameterDate = new Date[paramInt][];
/*       */       
/*  1620 */       System.arraycopy(localObject, 0, this.parameterDate, 0, this.numberOfBindRowsAllocated);
/*       */       
/*       */ 
/*  1623 */       for (i = this.numberOfBindRowsAllocated; 
/*  1624 */           i < paramInt; i++) {
/*  1625 */         this.parameterDate[i] = new Date[this.numberOfBindPositions];
/*       */       }
/*       */     }
/*  1628 */     if (this.parameterTime != null)
/*       */     {
/*  1630 */       localObject = this.parameterTime;
/*       */       
/*  1632 */       this.parameterTime = new Time[paramInt][];
/*       */       
/*  1634 */       System.arraycopy(localObject, 0, this.parameterTime, 0, this.numberOfBindRowsAllocated);
/*       */       
/*       */ 
/*  1637 */       for (i = this.numberOfBindRowsAllocated; 
/*  1638 */           i < paramInt; i++) {
/*  1639 */         this.parameterTime[i] = new Time[this.numberOfBindPositions];
/*       */       }
/*       */     }
/*  1642 */     if (this.parameterTimestamp != null)
/*       */     {
/*  1644 */       localObject = this.parameterTimestamp;
/*       */       
/*  1646 */       this.parameterTimestamp = new Timestamp[paramInt][];
/*       */       
/*  1648 */       System.arraycopy(localObject, 0, this.parameterTimestamp, 0, this.numberOfBindRowsAllocated);
/*       */       
/*       */ 
/*  1651 */       for (i = this.numberOfBindRowsAllocated; 
/*  1652 */           i < paramInt; i++) {
/*  1653 */         this.parameterTimestamp[i] = new Timestamp[this.numberOfBindPositions];
/*       */       }
/*       */     }
/*  1656 */     if (this.parameterDatum != null)
/*       */     {
/*  1658 */       localObject = this.parameterDatum;
/*       */       
/*  1660 */       this.parameterDatum = new byte[paramInt][][];
/*       */       
/*  1662 */       System.arraycopy(localObject, 0, this.parameterDatum, 0, this.numberOfBindRowsAllocated);
/*       */       
/*       */ 
/*  1665 */       for (i = this.numberOfBindRowsAllocated; 
/*  1666 */           i < paramInt; i++) {
/*  1667 */         this.parameterDatum[i] = new byte[this.numberOfBindPositions][];
/*       */       }
/*       */     }
/*  1670 */     if (this.parameterOtype != null)
/*       */     {
/*  1672 */       localObject = this.parameterOtype;
/*       */       
/*  1674 */       this.parameterOtype = new OracleTypeADT[paramInt][];
/*       */       
/*  1676 */       System.arraycopy(localObject, 0, this.parameterOtype, 0, this.numberOfBindRowsAllocated);
/*       */       
/*       */ 
/*  1679 */       for (i = this.numberOfBindRowsAllocated; 
/*  1680 */           i < paramInt; i++) {
/*  1681 */         this.parameterOtype[i] = new OracleTypeADT[this.numberOfBindPositions];
/*       */       }
/*       */     }
/*  1684 */     if (this.parameterStream != null)
/*       */     {
/*  1686 */       localObject = this.parameterStream;
/*       */       
/*  1688 */       this.parameterStream = new InputStream[paramInt][];
/*       */       
/*  1690 */       System.arraycopy(localObject, 0, this.parameterStream, 0, this.numberOfBindRowsAllocated);
/*       */       
/*       */ 
/*  1693 */       for (i = this.numberOfBindRowsAllocated; 
/*  1694 */           i < paramInt; i++) {
/*  1695 */         this.parameterStream[i] = new InputStream[this.numberOfBindPositions];
/*       */       }
/*       */     }
/*  1698 */     if (this.userStream != null)
/*       */     {
/*  1700 */       localObject = this.userStream;
/*       */       
/*  1702 */       this.userStream = new Object[paramInt][];
/*       */       
/*  1704 */       System.arraycopy(localObject, 0, this.userStream, 0, this.numberOfBindRowsAllocated);
/*       */       
/*       */ 
/*  1707 */       for (i = this.numberOfBindRowsAllocated; 
/*  1708 */           i < paramInt; i++) {
/*  1709 */         this.userStream[i] = new Object[this.numberOfBindPositions];
/*       */       }
/*       */     }
/*  1712 */     if (this.parameterPlsqlIbt != null)
/*       */     {
/*  1714 */       localObject = this.parameterPlsqlIbt;
/*       */       
/*  1716 */       this.parameterPlsqlIbt = new PlsqlIbtBindInfo[paramInt][];
/*       */       
/*       */ 
/*  1719 */       System.arraycopy(localObject, 0, this.parameterPlsqlIbt, 0, this.numberOfBindRowsAllocated);
/*       */       
/*       */ 
/*  1722 */       for (i = this.numberOfBindRowsAllocated; 
/*  1723 */           i < paramInt; i++) {
/*  1724 */         this.parameterPlsqlIbt[i] = new PlsqlIbtBindInfo[this.numberOfBindPositions];
/*       */       }
/*       */     }
/*       */     
/*  1728 */     this.numberOfBindRowsAllocated = paramInt;
/*       */     
/*       */ 
/*  1731 */     this.currentRowNeedToPrepareBinds = true;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void processCompletedBindRow(int paramInt, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  1805 */     if (this.numberOfBindPositions == 0)
/*       */     {
/*       */ 
/*  1808 */       return;
/*       */     }
/*       */     
/*  1811 */     int j = 0;
/*  1812 */     int k = 0;
/*  1813 */     int m = 0;
/*  1814 */     int n = this.currentRank == this.firstRowInBatch ? 1 : 0;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1821 */     Binder[] arrayOfBinder = this.currentRank == 0 ? this.lastBinders : this.lastBinders[0] == null ? null : this.binders[(this.currentRank - 1)];
/*       */     
/*       */     Object localObject2;
/*       */     Object localObject3;
/*  1825 */     if (this.currentRowBindAccessors == null)
/*       */     {
/*  1827 */       int i1 = (this.isAutoGeneratedKey) && (this.clearParameters) ? 1 : 0;
/*       */       
/*       */ 
/*  1830 */       if (arrayOfBinder == null)
/*       */       {
/*       */ 
/*       */ 
/*  1834 */         for (i = 0; i < this.numberOfBindPositions; i++) {
/*  1835 */           if (this.currentRowBinders[i] == null)
/*       */           {
/*  1837 */             if (i1 != 0)
/*       */             {
/*  1839 */               registerReturnParamsForAutoKey();
/*  1840 */               i1 = 0;
/*       */             }
/*       */             else {
/*  1843 */               localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 41, Integer.valueOf(i + 1));
/*  1844 */               ((SQLException)localObject2).fillInStackTrace();
/*  1845 */               throw ((Throwable)localObject2);
/*       */             } }
/*       */         }
/*       */       }
/*  1849 */       if (this.checkBindTypes)
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*  1854 */         localObject2 = this.parameterOtype == null ? null : this.currentRank == 0 ? this.lastBoundTypeOtypes : this.parameterOtype[(this.currentRank - 1)];
/*       */         
/*       */ 
/*       */ 
/*  1858 */         for (i = 0; i < this.numberOfBindPositions; i++)
/*       */         {
/*  1860 */           if ((this.currentRowBinders[i] == null) && (i1 != 0))
/*       */           {
/*  1862 */             registerReturnParamsForAutoKey();
/*  1863 */             i1 = 0;
/*       */           }
/*  1865 */           localObject3 = this.currentRowBinders[i];
/*       */           
/*  1867 */           if (localObject3 == null)
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*  1872 */             if (this.clearParameters)
/*       */             {
/*  1874 */               SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 41, Integer.valueOf(i + 1));
/*  1875 */               localSQLException2.fillInStackTrace();
/*  1876 */               throw localSQLException2;
/*       */             }
/*       */             
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1885 */             this.currentRowBinders[i] = arrayOfBinder[i].copyingBinder();
/*       */             
/*       */ 
/*  1888 */             if (this.currentRank == 0) {
/*  1889 */               this.currentRowBinders[i].lastBoundValueCleanup(this, i);
/*       */             }
/*       */             
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1896 */             this.currentRowCharLens[i] = -1;
/*       */             
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1902 */             k = 1;
/*       */           }
/*       */           else
/*       */           {
/*  1906 */             int i6 = ((Binder)localObject3).type;
/*       */             
/*  1908 */             if ((i6 == arrayOfBinder[i].type) && (((i6 != 109) && (i6 != 111)) || (this.parameterOtype[this.currentRank][i].isInHierarchyOf(localObject2[i])))) { if (i6 == 9) { if ((((Binder)localObject3).bytelen == 0 ? 1 : 0) == (arrayOfBinder[i].bytelen == 0 ? 1 : 0)) {}
/*       */               }
/*       */               
/*       */ 
/*       */ 
/*       */ 
/*       */             }
/*       */             else
/*       */             {
/*  1917 */               j = 1;
/*       */             }
/*       */           }
/*  1920 */           if (this.currentBatchFormOfUse[i] != this.currentRowFormOfUse[i])
/*       */           {
/*       */ 
/*       */ 
/*  1924 */             j = 1;
/*       */           }
/*       */           
/*       */         }
/*       */         
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  1933 */         for (i = 0; i < this.numberOfBindPositions; i++)
/*       */         {
/*  1935 */           localObject2 = this.currentRowBinders[i];
/*       */           
/*  1937 */           if (localObject2 == null)
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*  1942 */             if (this.clearParameters)
/*       */             {
/*  1944 */               localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 41, Integer.valueOf(i + 1));
/*  1945 */               ((SQLException)localObject3).fillInStackTrace();
/*  1946 */               throw ((Throwable)localObject3);
/*       */             }
/*       */             
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1955 */             this.currentRowBinders[i] = arrayOfBinder[i].copyingBinder();
/*       */             
/*       */ 
/*  1958 */             if (this.currentRank == 0) {
/*  1959 */               this.currentRowBinders[i].lastBoundValueCleanup(this, i);
/*       */             }
/*       */             
/*       */ 
/*       */ 
/*       */ 
/*  1965 */             this.currentRowCharLens[i] = -1;
/*       */             
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1971 */             k = 1;
/*       */           }
/*       */         }
/*       */       }
/*       */       
/*  1976 */       if ((k != 0) && ((n != 0) || (this.m_batchStyle == 2)))
/*       */       {
/*  1978 */         this.lastBoundNeeded = true;
/*       */       }
/*       */     }
/*       */     else
/*       */     {
/*       */       Object localObject1;
/*  1984 */       if (arrayOfBinder == null)
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  1990 */         for (i = 0; i < this.numberOfBindPositions; i++)
/*       */         {
/*  1992 */           localObject1 = this.currentRowBinders[i];
/*  1993 */           localObject2 = this.currentRowBindAccessors[i];
/*       */           
/*  1995 */           if (localObject1 == null)
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*  2000 */             if (localObject2 == null)
/*       */             {
/*       */ 
/*       */ 
/*       */ 
/*  2005 */               localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 41, Integer.valueOf(i + 1));
/*  2006 */               ((SQLException)localObject3).fillInStackTrace();
/*  2007 */               throw ((Throwable)localObject3);
/*       */             }
/*       */             
/*       */ 
/*       */ 
/*       */ 
/*  2013 */             this.currentRowBinders[i] = this.theOutBinder;
/*       */           }
/*  2015 */           else if ((localObject2 != null) && (((Accessor)localObject2).defineType != ((Binder)localObject1).type))
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2023 */             if ((!this.connection.permitTimestampDateMismatch) || (((Binder)localObject1).type != 180) || (((Accessor)localObject2).defineType != 12))
/*       */             {
/*       */ 
/*       */ 
/*  2027 */               m = 1;
/*       */             }
/*       */           }
/*       */         }
/*       */       }
/*  2032 */       if (this.checkBindTypes)
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2039 */         localObject1 = this.parameterOtype == null ? null : this.currentRank == 0 ? this.lastBoundTypeOtypes : this.parameterOtype[(this.currentRank - 1)];
/*       */         
/*       */ 
/*       */ 
/*  2043 */         for (i = 0; i < this.numberOfBindPositions; i++)
/*       */         {
/*  2045 */           localObject2 = this.currentRowBinders[i];
/*  2046 */           localObject3 = this.currentRowBindAccessors[i];
/*       */           
/*  2048 */           if (localObject2 == null)
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2054 */             if ((this.clearParameters) && (arrayOfBinder[i] != this.theOutBinder))
/*       */             {
/*       */ 
/*  2057 */               SQLException localSQLException3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 41, Integer.valueOf(i + 1));
/*  2058 */               localSQLException3.fillInStackTrace();
/*  2059 */               throw localSQLException3;
/*       */             }
/*       */             
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2070 */             localObject2 = arrayOfBinder[i];
/*  2071 */             this.currentRowBinders[i] = localObject2;
/*  2072 */             this.currentRowCharLens[i] = -1;
/*       */             
/*  2074 */             if (localObject2 != this.theOutBinder)
/*       */             {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2080 */               k = 1;
/*       */             }
/*       */           }
/*       */           else {
/*  2084 */             int i7 = ((Binder)localObject2).type;
/*       */             
/*  2086 */             if ((i7 == arrayOfBinder[i].type) && (((i7 != 109) && (i7 != 111)) || (this.parameterOtype[this.currentRank][i].isInHierarchyOf(localObject1[i])))) { if (i7 == 9) { if ((((Binder)localObject2).bytelen == 0 ? 1 : 0) == (arrayOfBinder[i].bytelen == 0 ? 1 : 0)) {}
/*       */               }
/*       */               
/*       */ 
/*       */ 
/*       */ 
/*       */             }
/*       */             else
/*       */             {
/*  2095 */               j = 1;
/*       */             }
/*       */           }
/*  2098 */           if (this.currentBatchFormOfUse[i] != this.currentRowFormOfUse[i])
/*       */           {
/*       */ 
/*       */ 
/*  2102 */             j = 1;
/*       */           }
/*  2104 */           Accessor localAccessor = this.currentBatchBindAccessors[i];
/*       */           
/*  2106 */           if (localObject3 == null)
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2112 */             localObject3 = localAccessor;
/*  2113 */             this.currentRowBindAccessors[i] = localObject3;
/*       */           }
/*  2115 */           else if ((localAccessor != null) && (((Accessor)localObject3).defineType != localAccessor.defineType))
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*  2120 */             j = 1;
/*       */           }
/*  2122 */           if ((localObject3 != null) && (localObject2 != this.theOutBinder) && (((Accessor)localObject3).defineType != ((Binder)localObject2).type))
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2131 */             if ((!this.connection.permitTimestampDateMismatch) || (((Binder)localObject2).type != 180) || (((Accessor)localObject3).defineType != 12))
/*       */             {
/*       */ 
/*       */ 
/*  2135 */               m = 1;
/*       */             }
/*       */             
/*       */           }
/*       */           
/*       */         }
/*       */         
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  2146 */         for (i = 0; i < this.numberOfBindPositions; i++)
/*       */         {
/*  2148 */           localObject1 = this.currentRowBinders[i];
/*       */           
/*  2150 */           if (localObject1 == null)
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2156 */             if ((this.clearParameters) && (arrayOfBinder[i] != this.theOutBinder))
/*       */             {
/*  2158 */               localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 41, Integer.valueOf(i + 1));
/*  2159 */               ((SQLException)localObject2).fillInStackTrace();
/*  2160 */               throw ((Throwable)localObject2);
/*       */             }
/*       */             
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2170 */             localObject1 = arrayOfBinder[i];
/*  2171 */             this.currentRowBinders[i] = localObject1;
/*  2172 */             this.currentRowCharLens[i] = -1;
/*       */             
/*  2174 */             if (localObject1 != this.theOutBinder)
/*       */             {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2180 */               k = 1;
/*       */             }
/*       */           }
/*  2183 */           if (this.currentRowBindAccessors[i] == null)
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*  2188 */             this.currentRowBindAccessors[i] = this.currentBatchBindAccessors[i];
/*       */           }
/*       */         }
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2200 */       if ((k != 0) && (n != 0)) {
/*  2201 */         this.lastBoundNeeded = true;
/*       */       }
/*       */     }
/*  2204 */     if (j != 0)
/*       */     {
/*       */ 
/*       */ 
/*  2208 */       if (n == 0)
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2214 */         if (this.m_batchStyle == 2)
/*       */         {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2221 */           pushBatch(false);
/*       */ 
/*       */ 
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/*       */ 
/*       */ 
/*  2230 */           int i2 = this.validRows;
/*       */           
/*  2232 */           this.prematureBatchCount = sendBatch();
/*  2233 */           this.validRows = i2;
/*       */           
/*       */ 
/*       */ 
/*       */ 
/*  2238 */           for (i = 0; i < this.numberOfBindPositions; i++) {
/*  2239 */             this.currentRowBinders[i].lastBoundValueCleanup(this, i);
/*       */           }
/*       */           
/*       */ 
/*  2243 */           if (k != 0) {
/*  2244 */             this.lastBoundNeeded = true;
/*       */           }
/*       */         }
/*       */       }
/*       */       
/*       */ 
/*  2250 */       this.needToParse = true;
/*       */       
/*       */ 
/*       */ 
/*  2254 */       this.currentRowNeedToPrepareBinds = true;
/*       */       
/*       */ 
/*  2257 */       this.needToPrepareDefineBuffer = true;
/*       */     }
/*  2259 */     else if (paramBoolean)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2265 */       pushBatch(false);
/*       */       
/*       */ 
/*       */ 
/*  2269 */       this.needToParse = false;
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2277 */       this.currentBatchNeedToPrepareBinds = false;
/*       */     }
/*       */     
/*  2280 */     if (m != 0)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2287 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12);
/*  2288 */       localSQLException1.fillInStackTrace();
/*  2289 */       throw localSQLException1;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2301 */     for (int i = 0; i < this.numberOfBindPositions; i++)
/*       */     {
/*  2303 */       int i3 = this.currentRowCharLens[i];
/*       */       
/*  2305 */       if ((i3 == -1) && (this.currentRank == this.firstRowInBatch)) {
/*  2306 */         i3 = this.lastBoundCharLens[i];
/*       */       }
/*  2308 */       if (this.currentBatchCharLens[i] < i3) {
/*  2309 */         this.currentBatchCharLens[i] = i3;
/*       */       }
/*  2311 */       this.currentRowCharLens[i] = 0;
/*  2312 */       this.currentBatchFormOfUse[i] = this.currentRowFormOfUse[i];
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  2317 */     if (this.currentRowNeedToPrepareBinds) {
/*  2318 */       this.currentBatchNeedToPrepareBinds = true;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2327 */     if (this.currentRowBindAccessors != null)
/*       */     {
/*  2329 */       Accessor[] arrayOfAccessor = this.currentBatchBindAccessors;
/*       */       
/*  2331 */       this.currentBatchBindAccessors = this.currentRowBindAccessors;
/*       */       
/*  2333 */       if (arrayOfAccessor == null) {
/*  2334 */         arrayOfAccessor = new Accessor[this.numberOfBindPositions];
/*       */       } else {
/*  2336 */         for (i = 0; i < this.numberOfBindPositions; i++)
/*  2337 */           arrayOfAccessor[i] = null;
/*       */       }
/*  2339 */       this.currentRowBindAccessors = arrayOfAccessor;
/*       */     }
/*       */     
/*  2342 */     int i4 = this.currentRank + 1;
/*       */     
/*  2344 */     if (i4 < paramInt)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*  2349 */       if (i4 >= this.numberOfBindRowsAllocated)
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2355 */         int i5 = this.numberOfBindRowsAllocated << 1;
/*       */         
/*  2357 */         if (i5 <= i4) {
/*  2358 */           i5 = i4 + 1;
/*       */         }
/*  2360 */         growBinds(i5);
/*       */         
/*  2362 */         this.currentBatchNeedToPrepareBinds = true;
/*       */         
/*  2364 */         if (this.pushedBatches != null) {
/*  2365 */           this.pushedBatches.current_batch_need_to_prepare_binds = true;
/*       */         }
/*       */       }
/*       */       
/*  2369 */       this.currentRowBinders = this.binders[i4];
/*       */ 
/*       */ 
/*       */ 
/*       */     }
/*       */     else
/*       */     {
/*       */ 
/*       */ 
/*  2378 */       setupBindBuffers(0, paramInt);
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2385 */       this.currentRowBinders = this.binders[0];
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  2390 */     this.currentRowNeedToPrepareBinds = false;
/*       */     
/*  2392 */     this.clearParameters = false;
/*       */   }
/*       */   
/*       */ 
/*       */   void processPlsqlIndexTabBinds(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  2399 */     int i = 0;
/*  2400 */     int j = 0;
/*  2401 */     int k = 0;
/*  2402 */     int m = 0;
/*       */     
/*  2404 */     Binder[] arrayOfBinder = this.binders[paramInt];
/*  2405 */     PlsqlIbtBindInfo[] arrayOfPlsqlIbtBindInfo = this.parameterPlsqlIbt == null ? null : this.parameterPlsqlIbt[paramInt];
/*       */     
/*       */     Accessor localAccessor3;
/*       */     
/*  2409 */     for (Accessor localAccessor1 = 0; localAccessor1 < this.numberOfBindPositions; localAccessor1++)
/*       */     {
/*  2411 */       Binder localBinder1 = arrayOfBinder[localAccessor1];
/*  2412 */       localAccessor3 = this.currentBatchBindAccessors == null ? null : this.currentBatchBindAccessors[localAccessor1];
/*       */       
/*  2414 */       PlsqlIndexTableAccessor localPlsqlIndexTableAccessor1 = (localAccessor3 == null) || (localAccessor3.defineType != 998) ? null : (PlsqlIndexTableAccessor)localAccessor3;
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2420 */       if (localBinder1.type == 998)
/*       */       {
/*  2422 */         PlsqlIbtBindInfo localPlsqlIbtBindInfo1 = arrayOfPlsqlIbtBindInfo[localAccessor1];
/*       */         
/*  2424 */         if (localPlsqlIndexTableAccessor1 != null)
/*       */         {
/*  2426 */           if (localPlsqlIbtBindInfo1.element_internal_type != localPlsqlIndexTableAccessor1.elementInternalType)
/*       */           {
/*       */ 
/*  2429 */             SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12);
/*  2430 */             localSQLException.fillInStackTrace();
/*  2431 */             throw localSQLException;
/*       */           }
/*       */           
/*  2434 */           if (localPlsqlIbtBindInfo1.maxLen < localPlsqlIndexTableAccessor1.maxNumberOfElements) {
/*  2435 */             localPlsqlIbtBindInfo1.maxLen = localPlsqlIndexTableAccessor1.maxNumberOfElements;
/*       */           }
/*  2437 */           if (localPlsqlIbtBindInfo1.elemMaxLen < localPlsqlIndexTableAccessor1.elementMaxLen) {
/*  2438 */             localPlsqlIbtBindInfo1.elemMaxLen = localPlsqlIndexTableAccessor1.elementMaxLen;
/*       */           }
/*  2440 */           if (localPlsqlIbtBindInfo1.ibtByteLength > 0) {
/*  2441 */             localPlsqlIbtBindInfo1.ibtByteLength = (localPlsqlIbtBindInfo1.elemMaxLen * localPlsqlIbtBindInfo1.maxLen);
/*       */           }
/*       */           else {
/*  2444 */             localPlsqlIbtBindInfo1.ibtCharLength = (localPlsqlIbtBindInfo1.elemMaxLen * localPlsqlIbtBindInfo1.maxLen);
/*       */           }
/*       */         }
/*       */         
/*  2448 */         i++;
/*  2449 */         k += localPlsqlIbtBindInfo1.ibtByteLength;
/*  2450 */         m += localPlsqlIbtBindInfo1.ibtCharLength;
/*  2451 */         j += localPlsqlIbtBindInfo1.maxLen;
/*       */       }
/*  2453 */       else if (localPlsqlIndexTableAccessor1 != null)
/*       */       {
/*  2455 */         i++;
/*  2456 */         k += localPlsqlIndexTableAccessor1.ibtByteLength;
/*  2457 */         m += localPlsqlIndexTableAccessor1.ibtCharLength;
/*  2458 */         j += localPlsqlIndexTableAccessor1.maxNumberOfElements;
/*       */       }
/*       */     }
/*       */     
/*  2462 */     if (i == 0) {
/*  2463 */       return;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2490 */     this.ibtBindIndicatorSize = (6 + i * 8 + j * 2);
/*       */     
/*       */ 
/*  2493 */     this.ibtBindIndicators = new short[this.ibtBindIndicatorSize];
/*  2494 */     this.ibtBindIndicatorOffset = 0;
/*       */     
/*  2496 */     if (k > 0)
/*  2497 */       this.ibtBindBytes = new byte[k];
/*  2498 */     this.ibtBindByteOffset = 0;
/*       */     
/*  2500 */     if (m > 0)
/*  2501 */       this.ibtBindChars = new char[m];
/*  2502 */     this.ibtBindCharOffset = 0;
/*       */     
/*  2504 */     localAccessor1 = this.ibtBindByteOffset;
/*  2505 */     Accessor localAccessor2 = this.ibtBindCharOffset;
/*       */     
/*  2507 */     int n = this.ibtBindIndicatorOffset;
/*  2508 */     int i1 = n + 6 + i * 8;
/*       */     
/*  2510 */     this.ibtBindIndicators[(n++)] = ((short)(i >> 16));
/*  2511 */     this.ibtBindIndicators[(n++)] = ((short)(i & 0xFFFF));
/*       */     
/*  2513 */     this.ibtBindIndicators[(n++)] = ((short)(k >> 16));
/*  2514 */     this.ibtBindIndicators[(n++)] = ((short)(k & 0xFFFF));
/*  2515 */     this.ibtBindIndicators[(n++)] = ((short)(m >> 16));
/*  2516 */     this.ibtBindIndicators[(n++)] = ((short)(m & 0xFFFF));
/*       */     
/*       */ 
/*  2519 */     for (int i2 = 0; i2 < this.numberOfBindPositions; i2++)
/*       */     {
/*  2521 */       Binder localBinder2 = arrayOfBinder[i2];
/*  2522 */       Accessor localAccessor4 = this.currentBatchBindAccessors == null ? null : this.currentBatchBindAccessors[i2];
/*       */       
/*  2524 */       PlsqlIndexTableAccessor localPlsqlIndexTableAccessor2 = (localAccessor4 == null) || (localAccessor4.defineType != 998) ? null : (PlsqlIndexTableAccessor)localAccessor4;
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2530 */       if (localBinder2.type == 998)
/*       */       {
/*  2532 */         PlsqlIbtBindInfo localPlsqlIbtBindInfo2 = arrayOfPlsqlIbtBindInfo[i2];
/*  2533 */         int i4 = localPlsqlIbtBindInfo2.maxLen;
/*       */         
/*  2535 */         this.ibtBindIndicators[(n++)] = ((short)localPlsqlIbtBindInfo2.element_internal_type);
/*       */         
/*  2537 */         this.ibtBindIndicators[(n++)] = ((short)localPlsqlIbtBindInfo2.elemMaxLen);
/*  2538 */         this.ibtBindIndicators[(n++)] = ((short)(i4 >> 16));
/*  2539 */         this.ibtBindIndicators[(n++)] = ((short)(i4 & 0xFFFF));
/*  2540 */         this.ibtBindIndicators[(n++)] = ((short)(localPlsqlIbtBindInfo2.curLen >> 16));
/*  2541 */         this.ibtBindIndicators[(n++)] = ((short)(localPlsqlIbtBindInfo2.curLen & 0xFFFF));
/*       */         
/*  2543 */         if (localPlsqlIbtBindInfo2.ibtByteLength > 0)
/*       */         {
/*  2545 */           localAccessor3 = localAccessor1;
/*  2546 */           localAccessor1 += localPlsqlIbtBindInfo2.ibtByteLength;
/*       */         }
/*       */         else
/*       */         {
/*  2550 */           localAccessor3 = localAccessor2;
/*  2551 */           localAccessor2 += localPlsqlIbtBindInfo2.ibtCharLength;
/*       */         }
/*       */         
/*  2554 */         this.ibtBindIndicators[(n++)] = ((short)(localAccessor3 >> 16));
/*  2555 */         this.ibtBindIndicators[(n++)] = ((short)(localAccessor3 & 0xFFFF));
/*  2556 */         localPlsqlIbtBindInfo2.ibtValueIndex = localAccessor3;
/*       */         
/*  2558 */         localPlsqlIbtBindInfo2.ibtIndicatorIndex = i1;
/*  2559 */         localPlsqlIbtBindInfo2.ibtLengthIndex = (i1 + i4);
/*       */         
/*  2561 */         if (localPlsqlIndexTableAccessor2 != null)
/*       */         {
/*  2563 */           localPlsqlIndexTableAccessor2.ibtIndicatorIndex = localPlsqlIbtBindInfo2.ibtIndicatorIndex;
/*  2564 */           localPlsqlIndexTableAccessor2.ibtLengthIndex = localPlsqlIbtBindInfo2.ibtLengthIndex;
/*  2565 */           localPlsqlIndexTableAccessor2.ibtMetaIndex = (n - 8);
/*  2566 */           localPlsqlIndexTableAccessor2.ibtValueIndex = localAccessor3;
/*       */         }
/*       */         
/*  2569 */         i1 += 2 * i4;
/*       */       }
/*  2571 */       else if (localPlsqlIndexTableAccessor2 != null)
/*       */       {
/*       */ 
/*  2574 */         int i3 = localPlsqlIndexTableAccessor2.maxNumberOfElements;
/*       */         
/*  2576 */         this.ibtBindIndicators[(n++)] = ((short)localPlsqlIndexTableAccessor2.elementInternalType);
/*       */         
/*  2578 */         this.ibtBindIndicators[(n++)] = ((short)localPlsqlIndexTableAccessor2.elementMaxLen);
/*  2579 */         this.ibtBindIndicators[(n++)] = ((short)(i3 >> 16));
/*  2580 */         this.ibtBindIndicators[(n++)] = ((short)(i3 & 0xFFFF));
/*  2581 */         this.ibtBindIndicators[(n++)] = 0;
/*  2582 */         this.ibtBindIndicators[(n++)] = 0;
/*       */         
/*  2584 */         if (localPlsqlIndexTableAccessor2.ibtByteLength > 0)
/*       */         {
/*  2586 */           localAccessor3 = localAccessor1;
/*  2587 */           localAccessor1 += localPlsqlIndexTableAccessor2.ibtByteLength;
/*       */         }
/*       */         else
/*       */         {
/*  2591 */           localAccessor3 = localAccessor2;
/*  2592 */           localAccessor2 += localPlsqlIndexTableAccessor2.ibtCharLength;
/*       */         }
/*       */         
/*  2595 */         this.ibtBindIndicators[(n++)] = ((short)(localAccessor3 >> 16));
/*  2596 */         this.ibtBindIndicators[(n++)] = ((short)(localAccessor3 & 0xFFFF));
/*  2597 */         localPlsqlIndexTableAccessor2.ibtValueIndex = localAccessor3;
/*       */         
/*  2599 */         localPlsqlIndexTableAccessor2.ibtIndicatorIndex = i1;
/*  2600 */         localPlsqlIndexTableAccessor2.ibtLengthIndex = (i1 + i3);
/*  2601 */         localPlsqlIndexTableAccessor2.ibtMetaIndex = (n - 8);
/*       */         
/*  2603 */         i1 += 2 * i3;
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void initializeBindSubRanges(int paramInt1, int paramInt2)
/*       */   {
/*  2628 */     this.bindByteSubRange = 0;
/*  2629 */     this.bindCharSubRange = 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   int calculateIndicatorSubRangeSize()
/*       */   {
/*  2637 */     return 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   short getInoutIndicator(int paramInt)
/*       */   {
/*  2644 */     return 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void initializeIndicatorSubRange()
/*       */   {
/*  2651 */     this.bindIndicatorSubRange = calculateIndicatorSubRangeSize();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void prepareBindPreambles(int paramInt1, int paramInt2) {}
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setupBindBuffers(int paramInt1, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*       */     try
/*       */     {
/*  2714 */       if (this.numberOfBindPositions == 0)
/*       */       {
/*  2716 */         if (paramInt2 != 0)
/*       */         {
/*  2718 */           if (this.bindIndicators == null) { allocBinds(paramInt2);
/*       */           }
/*  2720 */           this.numberOfBoundRows = paramInt2;
/*  2721 */           this.bindIndicators[(this.bindIndicatorSubRange + 3)] = ((short)((this.numberOfBoundRows & 0xFFFF0000) >> 16));
/*       */           
/*       */ 
/*  2724 */           this.bindIndicators[(this.bindIndicatorSubRange + 4)] = ((short)(this.numberOfBoundRows & 0xFFFF));
/*       */         }
/*       */         
/*       */ 
/*       */ 
/*  2729 */         return;
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2736 */       this.preparedAllBinds = this.currentBatchNeedToPrepareBinds;
/*  2737 */       this.preparedCharBinds = false;
/*       */       
/*       */ 
/*       */ 
/*  2741 */       this.currentBatchNeedToPrepareBinds = false;
/*       */       
/*       */ 
/*  2744 */       this.numberOfBoundRows = paramInt2;
/*  2745 */       this.bindIndicators[(this.bindIndicatorSubRange + 3)] = ((short)((this.numberOfBoundRows & 0xFFFF0000) >> 16));
/*       */       
/*       */ 
/*  2748 */       this.bindIndicators[(this.bindIndicatorSubRange + 4)] = ((short)(this.numberOfBoundRows & 0xFFFF));
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2756 */       int j = this.bindBufferCapacity;
/*       */       
/*  2758 */       if (this.numberOfBoundRows > this.bindBufferCapacity)
/*       */       {
/*  2760 */         j = this.numberOfBoundRows;
/*  2761 */         this.preparedAllBinds = true;
/*       */       }
/*       */       
/*  2764 */       if (this.currentBatchBindAccessors != null)
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2771 */         if (this.outBindAccessors == null) {
/*  2772 */           this.outBindAccessors = new Accessor[this.numberOfBindPositions];
/*       */         }
/*  2774 */         for (i = 0; i < this.numberOfBindPositions; i++)
/*       */         {
/*  2776 */           Accessor localAccessor1 = this.currentBatchBindAccessors[i];
/*       */           
/*  2778 */           this.outBindAccessors[i] = localAccessor1;
/*       */           
/*  2780 */           if (localAccessor1 != null)
/*       */           {
/*  2782 */             m = localAccessor1.charLength;
/*       */             
/*  2784 */             if ((m == 0) || (this.currentBatchCharLens[i] < m))
/*       */             {
/*       */ 
/*       */ 
/*       */ 
/*  2789 */               this.currentBatchCharLens[i] = m;
/*       */             }
/*       */           }
/*       */         }
/*       */       }
/*       */       
/*  2795 */       int k = 0;
/*  2796 */       int m = 0;
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2802 */       int n = this.bindIndicatorSubRange + 5;
/*       */       
/*  2804 */       int i1 = n;
/*       */       
/*  2806 */       if (this.preparedAllBinds)
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*  2811 */         this.preparedCharBinds = true;
/*       */         
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2817 */         Binder[] arrayOfBinder = this.binders[paramInt1];
/*       */         
/*  2819 */         for (i = 0; i < this.numberOfBindPositions; i++)
/*       */         {
/*  2821 */           Binder localBinder = arrayOfBinder[i];
/*       */           
/*       */ 
/*  2824 */           i6 = this.currentBatchCharLens[i];
/*       */           
/*  2826 */           if (localBinder == this.theOutBinder)
/*       */           {
/*  2828 */             Accessor localAccessor2 = this.currentBatchBindAccessors[i];
/*  2829 */             i5 = localAccessor2.byteLength;
/*  2830 */             i4 = (short)localAccessor2.defineType;
/*       */           }
/*       */           else
/*       */           {
/*  2834 */             i5 = localBinder.bytelen;
/*  2835 */             i4 = localBinder.type;
/*       */           }
/*       */           
/*       */ 
/*  2839 */           if ((localBinder == this.theRawNullBinder) && (this.sqlKind == OracleStatement.SqlKind.PLSQL_BLOCK))
/*       */           {
/*  2841 */             i5 = 32767;
/*       */           }
/*       */           
/*  2844 */           m += i5;
/*  2845 */           k += i6;
/*  2846 */           this.bindIndicators[(i1 + 0)] = i4;
/*  2847 */           this.bindIndicators[(i1 + 1)] = ((short)i5);
/*       */           
/*  2849 */           this.bindIndicators[(i1 + 2)] = ((short)i6);
/*       */           
/*  2851 */           this.bindIndicators[(i1 + 9)] = this.currentBatchFormOfUse[i];
/*       */           
/*  2853 */           i1 += 10;
/*       */         }
/*       */       } else {
/*  2856 */         if (this.preparedCharBinds)
/*       */         {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2862 */           for (i = 0; i < this.numberOfBindPositions; i++)
/*       */           {
/*  2864 */             i2 = this.currentBatchCharLens[i];
/*       */             
/*  2866 */             k += i2;
/*  2867 */             this.bindIndicators[(i1 + 2)] = ((short)i2);
/*       */             
/*  2869 */             i1 += 10;
/*       */           }
/*       */         }
/*       */         
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2884 */         for (i = 0; i < this.numberOfBindPositions; i++)
/*       */         {
/*  2886 */           i2 = i1 + 2;
/*  2887 */           i3 = this.currentBatchCharLens[i];
/*  2888 */           i4 = this.bindIndicators[i2];
/*  2889 */           i5 = (this.bindIndicators[(i1 + 5)] << 16) + (this.bindIndicators[(i1 + 6)] & 0xFFFF);
/*       */           
/*       */ 
/*  2892 */           i6 = this.bindIndicators[i5] == -1 ? 1 : 0;
/*       */           
/*       */ 
/*       */ 
/*  2896 */           if ((i6 != 0) && (i3 > 1))
/*       */           {
/*  2898 */             this.preparedCharBinds = true;
/*       */           }
/*       */           
/*  2901 */           if ((i4 >= i3) && (!this.preparedCharBinds))
/*       */           {
/*  2903 */             this.currentBatchCharLens[i] = i4;
/*  2904 */             k += i4;
/*       */           }
/*       */           else
/*       */           {
/*  2908 */             this.bindIndicators[i2] = ((short)i3);
/*  2909 */             k += i3;
/*  2910 */             this.preparedCharBinds = true;
/*       */           }
/*       */           
/*  2913 */           i1 += 10;
/*       */         }
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2925 */       if (this.preparedCharBinds) {
/*  2926 */         initializeBindSubRanges(this.numberOfBoundRows, j);
/*       */       }
/*  2928 */       if (this.preparedAllBinds)
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2934 */         i2 = this.bindByteSubRange + m * j;
/*       */         
/*       */ 
/*       */ 
/*       */ 
/*  2939 */         if ((this.lastBoundNeeded) || (i2 > this.totalBindByteLength))
/*       */         {
/*       */ 
/*       */ 
/*  2943 */           this.bindByteOffset = 0;
/*  2944 */           this.bindBytes = this.connection.getByteBuffer(i2);
/*       */           
/*       */ 
/*  2947 */           this.totalBindByteLength = i2;
/*       */         }
/*       */         
/*       */ 
/*  2951 */         this.bindBufferCapacity = j;
/*       */         
/*       */ 
/*  2954 */         this.bindIndicators[(this.bindIndicatorSubRange + 1)] = ((short)((this.bindBufferCapacity & 0xFFFF0000) >> 16));
/*       */         
/*       */ 
/*  2957 */         this.bindIndicators[(this.bindIndicatorSubRange + 2)] = ((short)(this.bindBufferCapacity & 0xFFFF));
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*  2963 */       if (this.preparedCharBinds)
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  2969 */         i2 = this.bindCharSubRange + k * this.bindBufferCapacity;
/*       */         
/*       */ 
/*       */ 
/*  2973 */         if ((this.lastBoundNeeded) || (i2 > this.totalBindCharLength))
/*       */         {
/*       */ 
/*       */ 
/*  2977 */           this.bindCharOffset = 0;
/*  2978 */           this.bindChars = this.connection.getCharBuffer(i2);
/*       */           
/*       */ 
/*  2981 */           this.totalBindCharLength = i2;
/*       */         }
/*       */         
/*       */ 
/*       */ 
/*  2986 */         this.bindByteSubRange += this.bindByteOffset;
/*  2987 */         this.bindCharSubRange += this.bindCharOffset;
/*       */       }
/*       */       
/*       */ 
/*  2991 */       int i2 = this.bindByteSubRange;
/*  2992 */       int i3 = this.bindCharSubRange;
/*  2993 */       int i4 = this.indicatorsOffset;
/*  2994 */       int i5 = this.valueLengthsOffset;
/*       */       
/*  2996 */       i1 = n;
/*       */       
/*  2998 */       if (this.preparedCharBinds)
/*       */       {
/*  3000 */         if (this.currentBatchBindAccessors == null)
/*       */         {
/*       */ 
/*       */ 
/*  3004 */           for (i = 0; i < this.numberOfBindPositions; i++)
/*       */           {
/*       */ 
/*       */ 
/*  3008 */             i6 = this.bindIndicators[(i1 + 1)];
/*       */             
/*  3010 */             i7 = this.currentBatchCharLens[i];
/*       */             
/*  3012 */             i8 = i7 == 0 ? i2 : i3;
/*       */             
/*       */ 
/*  3015 */             this.bindIndicators[(i1 + 3)] = ((short)(i8 >> 16));
/*       */             
/*       */ 
/*  3018 */             this.bindIndicators[(i1 + 4)] = ((short)(i8 & 0xFFFF));
/*       */             
/*       */ 
/*       */ 
/*       */ 
/*  3023 */             i2 += i6 * this.bindBufferCapacity;
/*  3024 */             i3 += i7 * this.bindBufferCapacity;
/*  3025 */             i1 += 10;
/*       */           }
/*       */         }
/*       */         
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3034 */         for (i = 0; i < this.numberOfBindPositions; i++)
/*       */         {
/*       */ 
/*       */ 
/*  3038 */           i6 = this.bindIndicators[(i1 + 1)];
/*       */           
/*  3040 */           i7 = this.currentBatchCharLens[i];
/*       */           
/*  3042 */           i8 = i7 == 0 ? i2 : i3;
/*       */           
/*       */ 
/*  3045 */           this.bindIndicators[(i1 + 3)] = ((short)(i8 >> 16));
/*       */           
/*       */ 
/*  3048 */           this.bindIndicators[(i1 + 4)] = ((short)(i8 & 0xFFFF));
/*       */           
/*       */ 
/*       */ 
/*       */ 
/*  3053 */           localObject = this.currentBatchBindAccessors[i];
/*       */           
/*  3055 */           if (localObject != null)
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/*  3060 */             if (i7 > 0)
/*       */             {
/*  3062 */               ((Accessor)localObject).columnIndex = i3;
/*  3063 */               ((Accessor)localObject).charLength = i7;
/*       */             }
/*       */             else
/*       */             {
/*  3067 */               ((Accessor)localObject).columnIndex = i2;
/*  3068 */               ((Accessor)localObject).byteLength = i6;
/*       */             }
/*       */             
/*  3071 */             ((Accessor)localObject).lengthIndex = i5;
/*  3072 */             ((Accessor)localObject).indicatorIndex = i4;
/*  3073 */             ((Accessor)localObject).rowSpaceByte = this.bindBytes;
/*  3074 */             ((Accessor)localObject).rowSpaceChar = this.bindChars;
/*  3075 */             ((Accessor)localObject).rowSpaceIndicator = this.bindIndicators;
/*       */             
/*  3077 */             if ((((Accessor)localObject).defineType == 109) || (((Accessor)localObject).defineType == 111))
/*       */             {
/*       */ 
/*       */ 
/*       */ 
/*  3082 */               ((Accessor)localObject).setOffsets(this.bindBufferCapacity);
/*       */             }
/*       */           }
/*       */           
/*       */ 
/*  3087 */           i2 += i6 * this.bindBufferCapacity;
/*  3088 */           i3 += i7 * this.bindBufferCapacity;
/*  3089 */           i4 += this.numberOfBindRowsAllocated;
/*  3090 */           i5 += this.numberOfBindRowsAllocated;
/*  3091 */           i1 += 10;
/*       */         }
/*       */         
/*       */ 
/*       */ 
/*       */ 
/*  3097 */         i2 = this.bindByteSubRange;
/*  3098 */         i3 = this.bindCharSubRange;
/*  3099 */         i4 = this.indicatorsOffset;
/*  3100 */         i5 = this.valueLengthsOffset;
/*  3101 */         i1 = n;
/*       */       }
/*       */       
/*       */ 
/*  3105 */       int i6 = this.bindBufferCapacity - this.numberOfBoundRows;
/*  3106 */       int i7 = this.numberOfBoundRows - 1;
/*  3107 */       int i8 = i7 + paramInt1;
/*  3108 */       Object localObject = this.binders[i8];
/*       */       
/*  3110 */       if (this.parameterOtype != null)
/*       */       {
/*  3112 */         System.arraycopy(this.parameterDatum[i8], 0, this.lastBoundTypeBytes, 0, this.numberOfBindPositions);
/*       */         
/*  3114 */         System.arraycopy(this.parameterOtype[i8], 0, this.lastBoundTypeOtypes, 0, this.numberOfBindPositions);
/*       */       }
/*       */       
/*       */ 
/*  3118 */       if (this.hasIbtBind) {
/*  3119 */         processPlsqlIndexTabBinds(paramInt1);
/*       */       }
/*  3121 */       if ((this.numReturnParams > 0) && ((this.returnParamAccessors == null) || (this.returnParamAccessors.length < this.numReturnParams)))
/*       */       {
/*       */ 
/*       */ 
/*  3125 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 173);
/*  3126 */         localSQLException2.fillInStackTrace();
/*  3127 */         throw localSQLException2;
/*       */       }
/*       */       
/*  3130 */       if (this.returnParamAccessors != null) { processDmlReturningBind();
/*       */       }
/*  3132 */       boolean bool = (!this.sqlKind.isPlsqlOrCall()) || (this.currentRowBindAccessors == null);
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3142 */       for (int i = 0; i < this.numberOfBindPositions; i++)
/*       */       {
/*       */ 
/*       */ 
/*  3146 */         int i9 = this.bindIndicators[(i1 + 1)];
/*       */         
/*  3148 */         int i10 = this.currentBatchCharLens[i];
/*       */         
/*       */ 
/*  3151 */         this.lastBinders[i] = localObject[i];
/*  3152 */         this.lastBoundByteLens[i] = i9;
/*       */         
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3161 */         for (int i11 = 0; i11 < this.numberOfBoundRows; 
/*  3162 */             i11++)
/*       */         {
/*  3164 */           int i12 = paramInt1 + i11;
/*       */           
/*  3166 */           this.binders[i12][i].bind(this, i, i11, i12, this.bindBytes, this.bindChars, this.bindIndicators, i9, i10, i2, i3, i5 + i11, i4 + i11, bool);
/*       */           
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3173 */           this.binders[i12][i] = null;
/*  3174 */           if (this.userStream != null) { this.userStream[i11][i] = null;
/*       */           }
/*  3176 */           i2 += i9;
/*  3177 */           i3 += i10;
/*       */         }
/*       */         
/*       */ 
/*  3181 */         this.lastBoundByteOffsets[i] = (i2 - i9);
/*  3182 */         this.lastBoundCharOffsets[i] = (i3 - i10);
/*  3183 */         this.lastBoundInds[i] = this.bindIndicators[(i4 + i7)];
/*  3184 */         this.lastBoundLens[i] = this.bindIndicators[(i5 + i7)];
/*       */         
/*       */ 
/*       */ 
/*       */ 
/*  3189 */         this.lastBoundCharLens[i] = 0;
/*       */         
/*       */ 
/*  3192 */         i2 += i6 * i9;
/*  3193 */         i3 += i6 * i10;
/*  3194 */         i4 += this.numberOfBindRowsAllocated;
/*  3195 */         i5 += this.numberOfBindRowsAllocated;
/*  3196 */         i1 += 10;
/*       */       }
/*       */       
/*       */ 
/*  3200 */       this.lastBoundBytes = this.bindBytes;
/*  3201 */       this.lastBoundByteOffset = this.bindByteOffset;
/*  3202 */       this.lastBoundChars = this.bindChars;
/*  3203 */       this.lastBoundCharOffset = this.bindCharOffset;
/*  3204 */       if (this.parameterStream != null) {
/*  3205 */         this.lastBoundStream = this.parameterStream[(paramInt1 + this.numberOfBoundRows - 1)];
/*       */       }
/*       */       
/*       */ 
/*  3209 */       int[] arrayOfInt = this.currentBatchCharLens;
/*       */       
/*  3211 */       this.currentBatchCharLens = this.lastBoundCharLens;
/*  3212 */       this.lastBoundCharLens = arrayOfInt;
/*       */       
/*       */ 
/*  3215 */       this.lastBoundNeeded = false;
/*       */       
/*       */ 
/*       */ 
/*  3219 */       prepareBindPreambles(this.numberOfBoundRows, this.bindBufferCapacity);
/*       */ 
/*       */ 
/*       */     }
/*       */     catch (NullPointerException localNullPointerException)
/*       */     {
/*       */ 
/*  3226 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 89);
/*  3227 */       localSQLException1.fillInStackTrace();
/*  3228 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void releaseBuffers()
/*       */   {
/*  3240 */     this.cachedBindCharSize = (this.bindChars != null ? this.bindChars.length : 0);
/*  3241 */     if (this.bindChars != this.lastBoundChars) this.connection.cacheBuffer(this.lastBoundChars);
/*  3242 */     this.lastBoundChars = null;
/*  3243 */     this.connection.cacheBuffer(this.bindChars);
/*  3244 */     this.bindChars = null;
/*       */     
/*  3246 */     this.cachedBindByteSize = (this.bindBytes != null ? this.bindBytes.length : 0);
/*  3247 */     if (this.bindBytes != this.lastBoundBytes) this.connection.cacheBuffer(this.lastBoundBytes);
/*  3248 */     this.lastBoundBytes = null;
/*  3249 */     this.connection.cacheBuffer(this.bindBytes);
/*  3250 */     this.bindBytes = null;
/*       */     
/*  3252 */     super.releaseBuffers();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void enterImplicitCache()
/*       */     throws SQLException
/*       */   {
/*  3270 */     alwaysOnClose();
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  3275 */     if (!this.connection.isClosed())
/*       */     {
/*  3277 */       cleanAllTempLobs();
/*       */     }
/*       */     
/*  3280 */     if (this.connection.clearStatementMetaData)
/*       */     {
/*  3282 */       this.lastBoundBytes = null;
/*  3283 */       this.lastBoundChars = null;
/*       */     }
/*       */     
/*  3286 */     clearParameters();
/*       */     
/*       */ 
/*       */ 
/*  3290 */     this.cacheState = 2;
/*  3291 */     this.creationState = 1;
/*       */     
/*       */ 
/*       */ 
/*  3295 */     this.currentResultSet = null;
/*  3296 */     this.lastIndex = 0;
/*       */     
/*  3298 */     this.queryTimeout = 0;
/*  3299 */     this.autoRollback = 2;
/*       */     
/*       */ 
/*  3302 */     this.rowPrefetchChanged = false;
/*  3303 */     this.currentRank = 0;
/*  3304 */     this.currentRow = -1;
/*  3305 */     this.validRows = 0;
/*  3306 */     this.maxRows = 0;
/*  3307 */     this.totalRowsVisited = 0;
/*  3308 */     this.maxFieldSize = 0;
/*  3309 */     this.gotLastBatch = false;
/*  3310 */     this.clearParameters = true;
/*  3311 */     this.scrollRset = null;
/*  3312 */     this.defaultFetchDirection = 1000;
/*  3313 */     this.defaultTimeZone = null;
/*  3314 */     this.defaultCalendar = null;
/*  3315 */     this.checkSum = 0L;
/*  3316 */     this.checkSumComputationFailure = false;
/*       */     
/*  3318 */     if (this.sqlKind.isOTHER())
/*       */     {
/*  3320 */       this.needToParse = true;
/*  3321 */       this.needToPrepareDefineBuffer = true;
/*  3322 */       this.columnsDefinedByUser = false;
/*       */     }
/*       */     
/*  3325 */     releaseBuffers();
/*       */     
/*       */ 
/*  3328 */     this.definedColumnType = null;
/*  3329 */     this.definedColumnSize = null;
/*  3330 */     this.definedColumnFormOfUse = null;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  3335 */     if (this.accessors != null)
/*       */     {
/*  3337 */       int i = this.accessors.length;
/*       */       
/*  3339 */       for (int j = 0; j < i; j++)
/*       */       {
/*  3341 */         if (this.accessors[j] != null)
/*       */         {
/*  3343 */           this.accessors[j].rowSpaceByte = null;
/*  3344 */           this.accessors[j].rowSpaceChar = null;
/*  3345 */           this.accessors[j].rowSpaceIndicator = null;
/*  3346 */           if (this.columnsDefinedByUser) {
/*  3347 */             this.accessors[j].externalType = 0;
/*       */           }
/*       */         }
/*       */       }
/*       */     }
/*       */     
/*       */ 
/*  3354 */     this.fixedString = this.connection.getDefaultFixedString();
/*  3355 */     this.defaultRowPrefetch = this.rowPrefetch;
/*  3356 */     this.rowPrefetchInLastFetch = -1;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3362 */     if (this.connection.clearStatementMetaData)
/*       */     {
/*       */ 
/*       */ 
/*  3366 */       this.sqlStringChanged = true;
/*  3367 */       this.needToParse = true;
/*  3368 */       this.needToPrepareDefineBuffer = true;
/*  3369 */       this.columnsDefinedByUser = false;
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*  3374 */       if (this.userRsetType == 0)
/*       */       {
/*  3376 */         this.userRsetType = 1;
/*  3377 */         this.realRsetType = 1;
/*       */       }
/*  3379 */       this.currentRowNeedToPrepareBinds = true;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void enterExplicitCache()
/*       */     throws SQLException
/*       */   {
/*  3397 */     this.cacheState = 2;
/*  3398 */     this.creationState = 2;
/*  3399 */     this.defaultTimeZone = null;
/*       */     
/*  3401 */     alwaysOnClose();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void exitImplicitCacheToActive()
/*       */     throws SQLException
/*       */   {
/*  3416 */     this.cacheState = 1;
/*  3417 */     this.closed = false;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3424 */     if (this.rowPrefetch != this.connection.getDefaultRowPrefetch())
/*       */     {
/*  3426 */       if (this.streamList == null)
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3434 */         this.rowPrefetch = this.connection.getDefaultRowPrefetch();
/*  3435 */         this.defaultRowPrefetch = this.rowPrefetch;
/*       */         
/*       */ 
/*  3438 */         this.rowPrefetchChanged = true;
/*       */       }
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3447 */     if (this.batch != this.connection.getDefaultExecuteBatch())
/*       */     {
/*  3449 */       resetBatch();
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3457 */     this.processEscapes = this.connection.processEscapes;
/*       */     
/*  3459 */     if (this.cachedDefineIndicatorSize != 0)
/*       */     {
/*  3461 */       this.defineBytes = this.connection.getByteBuffer(this.cachedDefineByteSize);
/*  3462 */       this.defineChars = this.connection.getCharBuffer(this.cachedDefineCharSize);
/*  3463 */       this.defineIndicators = new short[this.cachedDefineIndicatorSize];
/*  3464 */       if (this.accessors != null)
/*       */       {
/*  3466 */         int i = this.accessors.length;
/*       */         
/*  3468 */         for (int j = 0; j < i; j++)
/*       */         {
/*  3470 */           if (this.accessors[j] != null)
/*       */           {
/*  3472 */             this.accessors[j].rowSpaceByte = this.defineBytes;
/*  3473 */             this.accessors[j].rowSpaceChar = this.defineChars;
/*  3474 */             this.accessors[j].rowSpaceIndicator = this.defineIndicators;
/*       */           }
/*       */         }
/*  3477 */         doInitializationAfterDefineBufferRestore();
/*       */       }
/*       */     }
/*       */     
/*  3481 */     if ((this.cachedBindCharSize != 0) || (this.cachedBindByteSize != 0))
/*       */     {
/*  3483 */       if (this.cachedBindByteSize > 0)
/*  3484 */         this.bindBytes = this.connection.getByteBuffer(this.cachedBindByteSize);
/*  3485 */       if (this.cachedBindCharSize > 0)
/*  3486 */         this.bindChars = this.connection.getCharBuffer(this.cachedBindCharSize);
/*  3487 */       doLocalInitialization();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void doLocalInitialization() {}
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void doInitializationAfterDefineBufferRestore() {}
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void exitExplicitCacheToActive()
/*       */     throws SQLException
/*       */   {
/*  3519 */     this.cacheState = 1;
/*  3520 */     this.closed = false;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void exitImplicitCacheToClose()
/*       */     throws SQLException
/*       */   {
/*  3535 */     this.cacheState = 0;
/*  3536 */     this.closed = false;
/*       */     
/*  3538 */     synchronized (this.connection) {
/*  3539 */       hardClose();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void exitExplicitCacheToClose()
/*       */     throws SQLException
/*       */   {
/*  3555 */     this.cacheState = 0;
/*  3556 */     this.closed = false;
/*       */     
/*  3558 */     synchronized (this.connection) {
/*  3559 */       hardClose();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void closeWithKey(String paramString)
/*       */     throws SQLException
/*       */   {
/*  3575 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*  3580 */       closeOrCache(paramString);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   int executeInternal()
/*       */     throws SQLException
/*       */   {
/*  3588 */     this.noMoreUpdateCounts = false;
/*  3589 */     this.checkSum = 0L;
/*  3590 */     this.checkSumComputationFailure = false;
/*       */     
/*  3592 */     ensureOpen();
/*       */     
/*       */ 
/*  3595 */     if ((this.currentRank > 0) && (this.m_batchStyle == 2))
/*       */     {
/*       */ 
/*  3598 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 81, "batch must be either executed or cleared");
/*  3599 */       localSQLException.fillInStackTrace();
/*  3600 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*  3604 */     int i = this.userRsetType == 1 ? 1 : 0;
/*       */     
/*  3606 */     prepareForNewResults(true, false);
/*       */     
/*  3608 */     processCompletedBindRow(this.sqlKind.isSELECT() ? 1 : this.batch, false);
/*       */     
/*  3610 */     if ((i == 0) && (!this.scrollRsetTypeSolved)) {
/*  3611 */       return doScrollPstmtExecuteUpdate() + this.prematureBatchCount;
/*       */     }
/*  3613 */     doExecuteWithTimeout();
/*       */     
/*  3615 */     int j = (this.prematureBatchCount != 0) && (this.validRows > 0) ? 1 : 0;
/*       */     
/*  3617 */     if (i == 0)
/*       */     {
/*  3619 */       this.currentResultSet = new OracleResultSetImpl(this.connection, this);
/*  3620 */       this.scrollRset = ResultSetUtil.createScrollResultSet(this, this.currentResultSet, this.realRsetType);
/*       */       
/*       */ 
/*       */ 
/*  3624 */       if (!this.connection.accumulateBatchResult) {
/*  3625 */         j = 0;
/*       */       }
/*       */     }
/*  3628 */     if (j != 0)
/*       */     {
/*       */ 
/*  3631 */       this.validRows += this.prematureBatchCount;
/*  3632 */       this.prematureBatchCount = 0;
/*       */     }
/*  3634 */     if (this.sqlKind.isOTHER()) this.needToParse = true;
/*  3635 */     return this.validRows;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public ResultSet executeQuery()
/*       */     throws SQLException
/*       */   {
/*  3649 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3655 */       this.executionType = 1;
/*       */       
/*  3657 */       executeInternal();
/*       */       
/*  3659 */       if (this.userRsetType == 1)
/*       */       {
/*  3661 */         this.currentResultSet = new OracleResultSetImpl(this.connection, this);
/*       */         
/*  3663 */         return this.currentResultSet;
/*       */       }
/*       */       
/*       */ 
/*  3667 */       if (this.scrollRset == null)
/*       */       {
/*  3669 */         this.currentResultSet = new OracleResultSetImpl(this.connection, this);
/*  3670 */         this.scrollRset = this.currentResultSet;
/*       */       }
/*       */       
/*  3673 */       return this.scrollRset;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int executeUpdate()
/*       */     throws SQLException
/*       */   {
/*  3687 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*  3692 */       this.executionType = 2;
/*       */       
/*  3694 */       return executeInternal();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public boolean execute()
/*       */     throws SQLException
/*       */   {
/*  3707 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*  3712 */       this.executionType = 3;
/*       */       
/*  3714 */       executeInternal();
/*       */       
/*  3716 */       return this.sqlKind.isSELECT();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void slideDownCurrentRow(int paramInt)
/*       */   {
/*  3734 */     if (this.binders != null)
/*       */     {
/*  3736 */       this.binders[paramInt] = this.binders[0];
/*  3737 */       this.binders[0] = this.currentRowBinders;
/*       */     }
/*       */     
/*       */     Object localObject;
/*  3741 */     if (this.parameterInt != null)
/*       */     {
/*  3743 */       localObject = this.parameterInt[0];
/*       */       
/*  3745 */       this.parameterInt[0] = this.parameterInt[paramInt];
/*  3746 */       this.parameterInt[paramInt] = localObject;
/*       */     }
/*       */     
/*  3749 */     if (this.parameterLong != null)
/*       */     {
/*  3751 */       localObject = this.parameterLong[0];
/*       */       
/*  3753 */       this.parameterLong[0] = this.parameterLong[paramInt];
/*  3754 */       this.parameterLong[paramInt] = localObject;
/*       */     }
/*       */     
/*  3757 */     if (this.parameterFloat != null)
/*       */     {
/*  3759 */       localObject = this.parameterFloat[0];
/*       */       
/*  3761 */       this.parameterFloat[0] = this.parameterFloat[paramInt];
/*  3762 */       this.parameterFloat[paramInt] = localObject;
/*       */     }
/*       */     
/*  3765 */     if (this.parameterDouble != null)
/*       */     {
/*  3767 */       localObject = this.parameterDouble[0];
/*       */       
/*  3769 */       this.parameterDouble[0] = this.parameterDouble[paramInt];
/*  3770 */       this.parameterDouble[paramInt] = localObject;
/*       */     }
/*       */     
/*  3773 */     if (this.parameterBigDecimal != null)
/*       */     {
/*  3775 */       localObject = this.parameterBigDecimal[0];
/*       */       
/*  3777 */       this.parameterBigDecimal[0] = this.parameterBigDecimal[paramInt];
/*  3778 */       this.parameterBigDecimal[paramInt] = localObject;
/*       */     }
/*       */     
/*  3781 */     if (this.parameterString != null)
/*       */     {
/*  3783 */       localObject = this.parameterString[0];
/*       */       
/*  3785 */       this.parameterString[0] = this.parameterString[paramInt];
/*  3786 */       this.parameterString[paramInt] = localObject;
/*       */     }
/*       */     
/*  3789 */     if (this.parameterDate != null)
/*       */     {
/*  3791 */       localObject = this.parameterDate[0];
/*       */       
/*  3793 */       this.parameterDate[0] = this.parameterDate[paramInt];
/*  3794 */       this.parameterDate[paramInt] = localObject;
/*       */     }
/*       */     
/*  3797 */     if (this.parameterTime != null)
/*       */     {
/*  3799 */       localObject = this.parameterTime[0];
/*       */       
/*  3801 */       this.parameterTime[0] = this.parameterTime[paramInt];
/*  3802 */       this.parameterTime[paramInt] = localObject;
/*       */     }
/*       */     
/*  3805 */     if (this.parameterTimestamp != null)
/*       */     {
/*  3807 */       localObject = this.parameterTimestamp[0];
/*       */       
/*  3809 */       this.parameterTimestamp[0] = this.parameterTimestamp[paramInt];
/*  3810 */       this.parameterTimestamp[paramInt] = localObject;
/*       */     }
/*       */     
/*  3813 */     if (this.parameterDatum != null)
/*       */     {
/*  3815 */       localObject = this.parameterDatum[0];
/*       */       
/*  3817 */       this.parameterDatum[0] = this.parameterDatum[paramInt];
/*  3818 */       this.parameterDatum[paramInt] = localObject;
/*       */     }
/*       */     
/*  3821 */     if (this.parameterOtype != null)
/*       */     {
/*  3823 */       localObject = this.parameterOtype[0];
/*       */       
/*  3825 */       this.parameterOtype[0] = this.parameterOtype[paramInt];
/*  3826 */       this.parameterOtype[paramInt] = localObject;
/*       */     }
/*       */     
/*  3829 */     if (this.parameterStream != null)
/*       */     {
/*  3831 */       localObject = this.parameterStream[0];
/*       */       
/*  3833 */       this.parameterStream[0] = this.parameterStream[paramInt];
/*  3834 */       this.parameterStream[paramInt] = localObject;
/*       */     }
/*       */     
/*  3837 */     if (this.userStream != null)
/*       */     {
/*  3839 */       localObject = this.userStream[0];
/*       */       
/*  3841 */       this.userStream[0] = this.userStream[paramInt];
/*  3842 */       this.userStream[paramInt] = localObject;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void resetBatch()
/*       */   {
/*  3850 */     this.batch = this.connection.getDefaultExecuteBatch();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int sendBatch()
/*       */     throws SQLException
/*       */   {
/*  3880 */     if (isJdbcBatchStyle())
/*       */     {
/*  3882 */       return 0;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  3887 */     synchronized (this.connection)
/*       */     {
/*  3889 */       if ((!this.connection.isUsable()) || (this.bsendBatchInProgress))
/*       */       {
/*  3891 */         clearBatch();
/*  3892 */         this.bsendBatchInProgress = false;
/*  3893 */         return 0;
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       try
/*       */       {
/*  3905 */         ensureOpen();
/*       */         
/*       */ 
/*       */ 
/*  3909 */         if (this.currentRank <= 0) {
/*  3910 */           i = this.connection.accumulateBatchResult ? 0 : this.validRows;
/*       */           
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3961 */           this.currentRank = 0;
/*  3962 */           this.bsendBatchInProgress = false;return i;
/*       */         }
/*  3918 */         int i = this.batch;
/*  3919 */         this.bsendBatchInProgress = true;
/*       */         
/*       */         try
/*       */         {
/*  3923 */           j = this.currentRank;
/*       */           
/*  3925 */           if (this.batch != this.currentRank) {
/*  3926 */             this.batch = this.currentRank;
/*       */           }
/*  3928 */           setupBindBuffers(0, this.currentRank);
/*       */           
/*  3930 */           this.currentRank -= 1;
/*       */           
/*  3932 */           doExecuteWithTimeout();
/*       */           
/*       */ 
/*       */ 
/*  3936 */           slideDownCurrentRow(j);
/*       */ 
/*       */         }
/*       */         finally
/*       */         {
/*  3941 */           if (this.batch != i) {
/*  3942 */             this.batch = i;
/*       */           }
/*       */         }
/*       */         
/*       */ 
/*  3947 */         if (this.connection.accumulateBatchResult)
/*       */         {
/*       */ 
/*  3950 */           this.validRows += this.prematureBatchCount;
/*  3951 */           this.prematureBatchCount = 0;
/*       */         }
/*       */         
/*  3954 */         int j = this.validRows;
/*       */         
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  3961 */         this.currentRank = 0;
/*  3962 */         this.bsendBatchInProgress = false;return j;
/*       */       }
/*       */       finally
/*       */       {
/*  3961 */         this.currentRank = 0;
/*  3962 */         this.bsendBatchInProgress = false;
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setExecuteBatch(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  4003 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  4006 */       setOracleBatchStyle();
/*  4007 */       set_execute_batch(paramInt);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void set_execute_batch(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  4018 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  4021 */       if (paramInt <= 0)
/*       */       {
/*  4023 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 42);
/*  4024 */         localSQLException.fillInStackTrace();
/*  4025 */         throw localSQLException;
/*       */       }
/*       */       
/*  4028 */       if (paramInt == this.batch) {
/*  4029 */         return;
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*  4035 */       if (this.currentRank > 0)
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*  4040 */         i = this.validRows;
/*       */         
/*  4042 */         this.prematureBatchCount = sendBatch();
/*  4043 */         this.validRows = i;
/*       */       }
/*       */       
/*  4046 */       int i = this.batch;
/*       */       
/*  4048 */       this.batch = paramInt;
/*       */       
/*  4050 */       if (this.numberOfBindRowsAllocated < this.batch) {
/*  4051 */         growBinds(this.batch);
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public final int getExecuteBatch()
/*       */   {
/*  4065 */     return this.batch;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void defineParameterTypeBytes(int paramInt1, int paramInt2, int paramInt3)
/*       */     throws SQLException
/*       */   {
/*  4097 */     synchronized (this.connection)
/*       */     {
/*       */       SQLException localSQLException;
/*  4100 */       if (paramInt3 < 0)
/*       */       {
/*  4102 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 53);
/*  4103 */         localSQLException.fillInStackTrace();
/*  4104 */         throw localSQLException;
/*       */       }
/*       */       
/*  4107 */       if (paramInt1 < 1)
/*       */       {
/*  4109 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  4110 */         localSQLException.fillInStackTrace();
/*  4111 */         throw localSQLException;
/*       */       }
/*       */       
/*  4114 */       switch (paramInt2)
/*       */       {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       case -7: 
/*       */       case -6: 
/*       */       case -5: 
/*       */       case 2: 
/*       */       case 3: 
/*       */       case 4: 
/*       */       case 5: 
/*       */       case 6: 
/*       */       case 7: 
/*       */       case 8: 
/*  4139 */         paramInt2 = 6;
/*       */         
/*  4141 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case 1: 
/*  4146 */         paramInt2 = 96;
/*       */         
/*  4148 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case 12: 
/*  4153 */         paramInt2 = 1;
/*       */         
/*  4155 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */       case 91: 
/*       */       case 92: 
/*  4162 */         paramInt2 = 12;
/*       */         
/*  4164 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case -103: 
/*  4169 */         paramInt2 = 182;
/*       */         
/*  4171 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case -104: 
/*  4176 */         paramInt2 = 183;
/*       */         
/*  4178 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */       case -100: 
/*       */       case 93: 
/*  4185 */         paramInt2 = 180;
/*       */         
/*  4187 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case -101: 
/*  4192 */         paramInt2 = 181;
/*       */         
/*  4194 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case -102: 
/*  4199 */         paramInt2 = 231;
/*       */         
/*  4201 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */       case -3: 
/*       */       case -2: 
/*  4208 */         paramInt2 = 23;
/*       */         
/*  4210 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case 100: 
/*  4215 */         paramInt2 = 100;
/*       */         
/*  4217 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case 101: 
/*  4222 */         paramInt2 = 101;
/*       */         
/*  4224 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case -8: 
/*  4229 */         paramInt2 = 104;
/*       */         
/*  4231 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case 2004: 
/*  4236 */         paramInt2 = 113;
/*       */         
/*  4238 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case 2005: 
/*  4243 */         paramInt2 = 112;
/*       */         
/*  4245 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case -13: 
/*  4250 */         paramInt2 = 114;
/*       */         
/*  4252 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case -10: 
/*  4257 */         paramInt2 = 102;
/*       */         
/*  4259 */         break;
/*       */       
/*       */ 
/*       */ 
/*       */       case 0: 
/*  4264 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/*  4265 */         localSQLException.fillInStackTrace();
/*  4266 */         throw localSQLException;
/*       */       
/*       */ 
/*       */ 
/*       */       default: 
/*  4271 */         localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  4272 */         localSQLException.fillInStackTrace();
/*  4273 */         throw localSQLException;
/*       */       }
/*       */       
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void defineParameterTypeChars(int paramInt1, int paramInt2, int paramInt3)
/*       */     throws SQLException
/*       */   {
/*  4311 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*  4316 */       int i = this.connection.getNlsRatio();
/*       */       
/*  4318 */       if ((paramInt2 == 1) || (paramInt2 == 12)) {
/*  4319 */         defineParameterTypeBytes(paramInt1, paramInt2, paramInt3 * i);
/*       */       } else {
/*  4321 */         defineParameterTypeBytes(paramInt1, paramInt2, paramInt3);
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void defineParameterType(int paramInt1, int paramInt2, int paramInt3)
/*       */     throws SQLException
/*       */   {
/*  4335 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*       */ 
/*  4339 */       defineParameterTypeBytes(paramInt1, paramInt2, paramInt3);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public ResultSetMetaData getMetaData()
/*       */     throws SQLException
/*       */   {
/*  4356 */     if (this.sqlObject.getSqlKind().isSELECT()) {
/*  4357 */       return new OracleResultSetMetaData(this.connection, this);
/*       */     }
/*  4359 */     return null;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNull(int paramInt1, int paramInt2, String paramString)
/*       */     throws SQLException
/*       */   {
/*  4398 */     setNullInternal(paramInt1, paramInt2, paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setNullInternal(int paramInt1, int paramInt2, String paramString)
/*       */     throws SQLException
/*       */   {
/*  4406 */     int i = paramInt1 - 1;
/*       */     
/*  4408 */     if ((i < 0) || (paramInt1 > this.numberOfBindPositions))
/*       */     {
/*  4410 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  4411 */       localSQLException.fillInStackTrace();
/*  4412 */       throw localSQLException;
/*       */     }
/*       */     
/*  4415 */     if ((paramInt2 == 2002) || (paramInt2 == 2008) || (paramInt2 == 2003) || (paramInt2 == 2007) || (paramInt2 == 2009) || (paramInt2 == 2006))
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  4426 */       synchronized (this.connection) {
/*  4427 */         setNullCritical(i, paramInt2, paramString);
/*       */         
/*  4429 */         this.currentRowCharLens[i] = 0;
/*       */       }
/*       */       
/*       */     }
/*       */     else
/*       */     {
/*  4435 */       setNullInternal(paramInt1, paramInt2);
/*       */       
/*  4437 */       return;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setNullInternal(int paramInt1, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  4445 */     synchronized (this.connection)
/*       */     {
/*  4447 */       setNullCritical(paramInt1, paramInt2);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setNullCritical(int paramInt1, int paramInt2, String paramString)
/*       */     throws SQLException
/*       */   {
/*  4456 */     Object localObject1 = null;
/*  4457 */     Binder localBinder = this.theNamedTypeNullBinder;
/*       */     Object localObject2;
/*  4459 */     switch (paramInt2)
/*       */     {
/*       */ 
/*       */     case 2006: 
/*  4463 */       localBinder = this.theRefTypeNullBinder;
/*       */     
/*       */ 
/*       */ 
/*       */     case 2002: 
/*       */     case 2008: 
/*  4469 */       localObject2 = StructDescriptor.createDescriptor(paramString, this.connection);
/*       */       
/*       */ 
/*  4472 */       localObject1 = ((StructDescriptor)localObject2).getOracleTypeADT();
/*       */       
/*  4474 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */     case 2003: 
/*  4479 */       localObject2 = ArrayDescriptor.createDescriptor(paramString, this.connection);
/*       */       
/*       */ 
/*  4482 */       localObject1 = ((ArrayDescriptor)localObject2).getOracleTypeCOLLECTION();
/*       */       
/*  4484 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     case 2007: 
/*       */     case 2009: 
/*  4494 */       localObject2 = OpaqueDescriptor.createDescriptor(paramString, this.connection);
/*       */       
/*       */ 
/*  4497 */       localObject1 = (OracleTypeADT)((OpaqueDescriptor)localObject2).getPickler();
/*       */       
/*  4499 */       break;
/*       */     }
/*       */     
/*       */     
/*  4503 */     this.currentRowBinders[paramInt1] = localBinder;
/*       */     
/*  4505 */     if (this.parameterDatum == null) {
/*  4506 */       this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  4511 */     this.parameterDatum[this.currentRank][paramInt1] = null;
/*       */     
/*  4513 */     if (localObject1 != null) {
/*  4514 */       ((OracleTypeADT)localObject1).getTOID();
/*       */     }
/*  4516 */     if (this.parameterOtype == null) {
/*  4517 */       this.parameterOtype = new OracleTypeADT[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*  4520 */     this.parameterOtype[this.currentRank][paramInt1] = localObject1;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNullAtName(String paramString1, int paramInt, String paramString2)
/*       */     throws SQLException
/*       */   {
/*  4539 */     String str = paramString1.intern();
/*  4540 */     String[] arrayOfString = this.sqlObject.getParameterList();
/*  4541 */     int i = 0;
/*  4542 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/*  4544 */     for (int k = 0; k < j; k++) {
/*  4545 */       if (arrayOfString[k] == str)
/*       */       {
/*  4547 */         setNullInternal(k + 1, paramInt, paramString2);
/*       */         
/*  4549 */         i = 1;
/*       */       }
/*       */     }
/*  4552 */     if (i == 0)
/*       */     {
/*  4554 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString1);
/*  4555 */       localSQLException.fillInStackTrace();
/*  4556 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNull(int paramInt1, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  4575 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  4578 */       setNullCritical(paramInt1, paramInt2);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setNullCritical(int paramInt1, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  4586 */     int i = paramInt1 - 1;
/*       */     
/*  4588 */     if ((i < 0) || (paramInt1 > this.numberOfBindPositions))
/*       */     {
/*  4590 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  4591 */       ((SQLException)localObject).fillInStackTrace();
/*  4592 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*  4595 */     Object localObject = null;
/*  4596 */     int j = getInternalType(paramInt2);
/*       */     SQLException localSQLException;
/*  4598 */     switch (j)
/*       */     {
/*       */ 
/*       */     case 6: 
/*  4602 */       localObject = this.theVarnumNullBinder;
/*       */       
/*  4604 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */     case 1: 
/*       */     case 8: 
/*       */     case 96: 
/*       */     case 995: 
/*  4613 */       localObject = this.theVarcharNullBinder;
/*  4614 */       this.currentRowCharLens[i] = 1;
/*       */       
/*  4616 */       break;
/*       */     
/*       */     case 999: 
/*  4619 */       localObject = this.theFixedCHARNullBinder;
/*       */       
/*  4621 */       break;
/*       */     
/*       */     case 12: 
/*  4624 */       localObject = this.theDateNullBinder;
/*       */       
/*  4626 */       break;
/*       */     
/*       */ 
/*       */     case 180: 
/*  4630 */       localObject = this.theTimestampNullBinder;
/*       */       
/*  4632 */       break;
/*       */     
/*       */     case 181: 
/*  4635 */       localObject = this.theTSTZNullBinder;
/*       */       
/*  4637 */       break;
/*       */     
/*       */     case 231: 
/*  4640 */       localObject = this.theTSLTZNullBinder;
/*       */       
/*  4642 */       break;
/*       */     
/*       */     case 104: 
/*  4645 */       localObject = getRowidNullBinder(i);
/*       */       
/*  4647 */       break;
/*       */     
/*       */     case 183: 
/*  4650 */       localObject = this.theIntervalDSNullBinder;
/*       */       
/*  4652 */       break;
/*       */     
/*       */     case 182: 
/*  4655 */       localObject = this.theIntervalYMNullBinder;
/*       */       
/*  4657 */       break;
/*       */     
/*       */ 
/*       */     case 23: 
/*       */     case 24: 
/*  4662 */       localObject = this.theRawNullBinder;
/*       */       
/*  4664 */       break;
/*       */     
/*       */     case 100: 
/*  4667 */       localObject = this.theBinaryFloatNullBinder;
/*       */       
/*  4669 */       break;
/*       */     
/*       */     case 101: 
/*  4672 */       localObject = this.theBinaryDoubleNullBinder;
/*       */       
/*  4674 */       break;
/*       */     
/*       */     case 113: 
/*  4677 */       localObject = this.theBlobNullBinder;
/*       */       
/*  4679 */       break;
/*       */     
/*       */     case 112: 
/*  4682 */       localObject = this.theClobNullBinder;
/*       */       
/*  4684 */       break;
/*       */     
/*       */     case 114: 
/*  4687 */       localObject = this.theBfileNullBinder;
/*       */       
/*  4689 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */     case 109: 
/*       */     case 111: 
/*  4695 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "sqlType=" + paramInt2);
/*  4696 */       localSQLException.fillInStackTrace();
/*  4697 */       throw localSQLException;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     case 102: 
/*       */     case 998: 
/*       */     default: 
/*  4708 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23, "sqlType=" + paramInt2);
/*  4709 */       localSQLException.fillInStackTrace();
/*  4710 */       throw localSQLException;
/*       */     }
/*       */     
/*       */     
/*  4714 */     this.currentRowBinders[i] = localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   Binder getRowidNullBinder(int paramInt)
/*       */   {
/*  4721 */     return this.theRowidNullBinder;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNullAtName(String paramString, int paramInt)
/*       */     throws SQLException
/*       */   {
/*  4737 */     String str = paramString.intern();
/*  4738 */     String[] arrayOfString = this.sqlObject.getParameterList();
/*  4739 */     int i = 0;
/*  4740 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/*  4742 */     for (int k = 0; k < j; k++) {
/*  4743 */       if (arrayOfString[k] == str)
/*       */       {
/*  4745 */         setNull(k + 1, paramInt);
/*       */         
/*  4747 */         i = 1;
/*       */       }
/*       */     }
/*  4750 */     if (i == 0)
/*       */     {
/*  4752 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/*  4753 */       localSQLException.fillInStackTrace();
/*  4754 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBoolean(int paramInt, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  4770 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  4773 */       setBooleanInternal(paramInt, paramBoolean);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setBooleanInternal(int paramInt, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  4781 */     int i = paramInt - 1;
/*       */     
/*  4783 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  4785 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  4786 */       localSQLException.fillInStackTrace();
/*  4787 */       throw localSQLException;
/*       */     }
/*       */     
/*  4790 */     this.currentRowCharLens[i] = 0;
/*       */     
/*  4792 */     this.currentRowBinders[i] = this.theBooleanBinder;
/*       */     
/*  4794 */     if (this.parameterInt == null) {
/*  4795 */       this.parameterInt = new int[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*       */ 
/*  4799 */     this.parameterInt[this.currentRank][i] = (paramBoolean ? 1 : 0);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setByte(int paramInt, byte paramByte)
/*       */     throws SQLException
/*       */   {
/*  4812 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  4815 */       setByteInternal(paramInt, paramByte);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setByteInternal(int paramInt, byte paramByte)
/*       */     throws SQLException
/*       */   {
/*  4823 */     int i = paramInt - 1;
/*       */     
/*  4825 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  4827 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  4828 */       localSQLException.fillInStackTrace();
/*  4829 */       throw localSQLException;
/*       */     }
/*       */     
/*  4832 */     this.currentRowCharLens[i] = 0;
/*       */     
/*  4834 */     this.currentRowBinders[i] = this.theByteBinder;
/*       */     
/*  4836 */     if (this.parameterInt == null) {
/*  4837 */       this.parameterInt = new int[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*       */ 
/*  4841 */     this.parameterInt[this.currentRank][i] = paramByte;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setShort(int paramInt, short paramShort)
/*       */     throws SQLException
/*       */   {
/*  4855 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  4858 */       setShortInternal(paramInt, paramShort);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setShortInternal(int paramInt, short paramShort)
/*       */     throws SQLException
/*       */   {
/*  4866 */     int i = paramInt - 1;
/*       */     
/*  4868 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  4870 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  4871 */       localSQLException.fillInStackTrace();
/*  4872 */       throw localSQLException;
/*       */     }
/*       */     
/*  4875 */     this.currentRowCharLens[i] = 0;
/*       */     
/*  4877 */     this.currentRowBinders[i] = this.theShortBinder;
/*       */     
/*  4879 */     if (this.parameterInt == null) {
/*  4880 */       this.parameterInt = new int[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*       */ 
/*  4884 */     this.parameterInt[this.currentRank][i] = paramShort;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setInt(int paramInt1, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  4898 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  4901 */       setIntInternal(paramInt1, paramInt2);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setIntInternal(int paramInt1, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  4909 */     int i = paramInt1 - 1;
/*       */     
/*  4911 */     if ((i < 0) || (paramInt1 > this.numberOfBindPositions))
/*       */     {
/*  4913 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  4914 */       localSQLException.fillInStackTrace();
/*  4915 */       throw localSQLException;
/*       */     }
/*       */     
/*  4918 */     this.currentRowCharLens[i] = 0;
/*       */     
/*  4920 */     this.currentRowBinders[i] = this.theIntBinder;
/*       */     
/*  4922 */     if (this.parameterInt == null) {
/*  4923 */       this.parameterInt = new int[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*       */ 
/*  4927 */     this.parameterInt[this.currentRank][i] = paramInt2;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setLong(int paramInt, long paramLong)
/*       */     throws SQLException
/*       */   {
/*  4940 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  4943 */       setLongInternal(paramInt, paramLong);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setLongInternal(int paramInt, long paramLong)
/*       */     throws SQLException
/*       */   {
/*  4951 */     int i = paramInt - 1;
/*       */     
/*  4953 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  4955 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  4956 */       localSQLException.fillInStackTrace();
/*  4957 */       throw localSQLException;
/*       */     }
/*       */     
/*  4960 */     this.currentRowCharLens[i] = 0;
/*       */     
/*  4962 */     this.currentRowBinders[i] = this.theLongBinder;
/*       */     
/*  4964 */     if (this.parameterLong == null) {
/*  4965 */       this.parameterLong = new long[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  4970 */     this.parameterLong[this.currentRank][i] = paramLong;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setFloat(int paramInt, float paramFloat)
/*       */     throws SQLException
/*       */   {
/*  4984 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  4987 */       setFloatInternal(paramInt, paramFloat);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setFloatInternal(int paramInt, float paramFloat)
/*       */     throws SQLException
/*       */   {
/*  4995 */     int i = paramInt - 1;
/*       */     
/*  4997 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  4999 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  5000 */       localSQLException.fillInStackTrace();
/*  5001 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  5009 */     if (!this.connection.setFloatAndDoubleUseBinary)
/*       */     {
/*  5011 */       if (Float.isNaN(paramFloat)) {
/*  5012 */         throw new IllegalArgumentException("NaN");
/*       */       }
/*       */     }
/*       */     
/*  5016 */     if (this.theFloatBinder == null) {
/*  5017 */       this.theFloatBinder = theStaticFloatBinder;
/*  5018 */       if (this.connection.setFloatAndDoubleUseBinary) {
/*  5019 */         this.theFloatBinder = theStaticBinaryFloatBinder;
/*       */       }
/*       */     }
/*  5022 */     this.currentRowCharLens[i] = 0;
/*       */     
/*  5024 */     this.currentRowBinders[i] = this.theFloatBinder;
/*       */     
/*  5026 */     if (this.theFloatBinder == theStaticFloatBinder)
/*       */     {
/*  5028 */       if (this.parameterDouble == null) {
/*  5029 */         this.parameterDouble = new double[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  5034 */       this.parameterDouble[this.currentRank][i] = paramFloat;
/*       */     }
/*       */     else
/*       */     {
/*  5038 */       if (this.parameterFloat == null) {
/*  5039 */         this.parameterFloat = new float[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  5044 */       this.parameterFloat[this.currentRank][i] = paramFloat;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBinaryFloat(int paramInt, float paramFloat)
/*       */     throws SQLException
/*       */   {
/*  5060 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  5063 */       setBinaryFloatInternal(paramInt, paramFloat);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setBinaryFloatInternal(int paramInt, float paramFloat)
/*       */     throws SQLException
/*       */   {
/*  5071 */     int i = paramInt - 1;
/*       */     
/*  5073 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  5075 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  5076 */       localSQLException.fillInStackTrace();
/*  5077 */       throw localSQLException;
/*       */     }
/*       */     
/*  5080 */     this.currentRowCharLens[i] = 0;
/*       */     
/*  5082 */     this.currentRowBinders[i] = this.theBinaryFloatBinder;
/*       */     
/*  5084 */     if (this.parameterFloat == null) {
/*  5085 */       this.parameterFloat = new float[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  5090 */     this.parameterFloat[this.currentRank][i] = paramFloat;
/*       */   }
/*       */   
/*       */ 
/*       */   public void setBinaryFloat(int paramInt, BINARY_FLOAT paramBINARY_FLOAT)
/*       */     throws SQLException
/*       */   {
/*  5097 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  5100 */       setBinaryFloatInternal(paramInt, paramBINARY_FLOAT);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setBinaryFloatInternal(int paramInt, BINARY_FLOAT paramBINARY_FLOAT)
/*       */     throws SQLException
/*       */   {
/*  5109 */     int i = paramInt - 1;
/*       */     
/*  5111 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  5113 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  5114 */       localSQLException.fillInStackTrace();
/*  5115 */       throw localSQLException;
/*       */     }
/*       */     
/*  5118 */     if (paramBINARY_FLOAT == null)
/*       */     {
/*  5120 */       this.currentRowBinders[i] = this.theBINARY_FLOATNullBinder;
/*       */     }
/*       */     else
/*       */     {
/*  5124 */       this.currentRowBinders[i] = this.theBINARY_FLOATBinder;
/*       */       
/*  5126 */       if (this.parameterDatum == null)
/*       */       {
/*  5128 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  5133 */       this.parameterDatum[this.currentRank][i] = paramBINARY_FLOAT.getBytes();
/*       */     }
/*       */     
/*  5136 */     this.currentRowCharLens[i] = 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBinaryDouble(int paramInt, double paramDouble)
/*       */     throws SQLException
/*       */   {
/*  5151 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  5154 */       setBinaryDoubleInternal(paramInt, paramDouble);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setBinaryDoubleInternal(int paramInt, double paramDouble)
/*       */     throws SQLException
/*       */   {
/*  5162 */     int i = paramInt - 1;
/*       */     
/*  5164 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  5166 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  5167 */       localSQLException.fillInStackTrace();
/*  5168 */       throw localSQLException;
/*       */     }
/*       */     
/*  5171 */     this.currentRowBinders[i] = this.theBinaryDoubleBinder;
/*       */     
/*  5173 */     if (this.parameterDouble == null) {
/*  5174 */       this.parameterDouble = new double[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*  5177 */     this.currentRowCharLens[i] = 0;
/*       */     
/*       */ 
/*       */ 
/*  5181 */     this.parameterDouble[this.currentRank][i] = paramDouble;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBinaryDouble(int paramInt, BINARY_DOUBLE paramBINARY_DOUBLE)
/*       */     throws SQLException
/*       */   {
/*  5197 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  5200 */       setBinaryDoubleInternal(paramInt, paramBINARY_DOUBLE);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setBinaryDoubleInternal(int paramInt, BINARY_DOUBLE paramBINARY_DOUBLE)
/*       */     throws SQLException
/*       */   {
/*  5209 */     int i = paramInt - 1;
/*       */     
/*  5211 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  5213 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  5214 */       localSQLException.fillInStackTrace();
/*  5215 */       throw localSQLException;
/*       */     }
/*       */     
/*  5218 */     if (paramBINARY_DOUBLE == null)
/*       */     {
/*  5220 */       this.currentRowBinders[i] = this.theBINARY_DOUBLENullBinder;
/*       */     }
/*       */     else
/*       */     {
/*  5224 */       this.currentRowBinders[i] = this.theBINARY_DOUBLEBinder;
/*       */       
/*  5226 */       if (this.parameterDatum == null)
/*       */       {
/*  5228 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  5233 */       this.parameterDatum[this.currentRank][i] = paramBINARY_DOUBLE.getBytes();
/*       */     }
/*       */     
/*  5236 */     this.currentRowCharLens[i] = 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setDouble(int paramInt, double paramDouble)
/*       */     throws SQLException
/*       */   {
/*  5250 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  5253 */       setDoubleInternal(paramInt, paramDouble);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setDoubleInternal(int paramInt, double paramDouble)
/*       */     throws SQLException
/*       */   {
/*  5261 */     int i = paramInt - 1;
/*       */     
/*  5263 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  5265 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  5266 */       localSQLException.fillInStackTrace();
/*  5267 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  5275 */     if (!this.connection.setFloatAndDoubleUseBinary) {
/*  5276 */       if (Double.isNaN(paramDouble)) {
/*  5277 */         throw new IllegalArgumentException("NaN");
/*       */       }
/*       */       
/*  5280 */       double d = Math.abs(paramDouble);
/*  5281 */       if ((d != 0.0D) && (d < 1.0E-130D)) {
/*  5282 */         throw new IllegalArgumentException("Underflow");
/*       */       }
/*  5284 */       if (d >= 1.0E126D) {
/*  5285 */         throw new IllegalArgumentException("Overflow");
/*       */       }
/*       */     }
/*       */     
/*  5289 */     if (this.theDoubleBinder == null) {
/*  5290 */       this.theDoubleBinder = theStaticDoubleBinder;
/*  5291 */       if (this.connection.setFloatAndDoubleUseBinary) {
/*  5292 */         this.theDoubleBinder = theStaticBinaryDoubleBinder;
/*       */       }
/*       */     }
/*  5295 */     this.currentRowCharLens[i] = 0;
/*       */     
/*  5297 */     this.currentRowBinders[i] = this.theDoubleBinder;
/*       */     
/*  5299 */     if (this.parameterDouble == null) {
/*  5300 */       this.parameterDouble = new double[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  5305 */     this.parameterDouble[this.currentRank][i] = paramDouble;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBigDecimal(int paramInt, BigDecimal paramBigDecimal)
/*       */     throws SQLException
/*       */   {
/*  5319 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  5322 */       setBigDecimalInternal(paramInt, paramBigDecimal);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setBigDecimalInternal(int paramInt, BigDecimal paramBigDecimal)
/*       */     throws SQLException
/*       */   {
/*  5330 */     int i = paramInt - 1;
/*       */     
/*  5332 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  5334 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  5335 */       localSQLException.fillInStackTrace();
/*  5336 */       throw localSQLException;
/*       */     }
/*       */     
/*  5339 */     if (paramBigDecimal == null) {
/*  5340 */       this.currentRowBinders[i] = this.theVarnumNullBinder;
/*       */     }
/*       */     else {
/*  5343 */       this.currentRowBinders[i] = this.theBigDecimalBinder;
/*       */       
/*  5345 */       if (this.parameterBigDecimal == null) {
/*  5346 */         this.parameterBigDecimal = new BigDecimal[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  5351 */       this.parameterBigDecimal[this.currentRank][i] = paramBigDecimal;
/*       */     }
/*       */     
/*  5354 */     this.currentRowCharLens[i] = 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*  5359 */   int SetBigStringTryClob = 0;
/*       */   
/*       */ 
/*       */   static final int BSTYLE_UNKNOWN = 0;
/*       */   
/*       */ 
/*       */   static final int BSTYLE_ORACLE = 1;
/*       */   
/*       */ 
/*       */   static final int BSTYLE_JDBC = 2;
/*       */   
/*       */ 
/*       */   public void setString(int paramInt, String paramString)
/*       */     throws SQLException
/*       */   {
/*  5374 */     setStringInternal(paramInt, paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setStringInternal(int paramInt, String paramString)
/*       */     throws SQLException
/*       */   {
/*  5382 */     int i = paramInt - 1;
/*  5383 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  5385 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  5386 */       localSQLException.fillInStackTrace();
/*  5387 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*  5391 */     int j = paramString != null ? paramString.length() : 0;
/*       */     
/*  5393 */     if (j == 0) {
/*  5394 */       setNull(paramInt, 12);
/*       */     } else {
/*       */       int k;
/*  5397 */       if (this.currentRowFormOfUse[(paramInt - 1)] == 1) {
/*  5398 */         if (this.sqlKind.isPlsqlOrCall()) {
/*  5399 */           if ((j > this.maxVcsBytesPlsql) || ((j > this.maxVcsCharsPlsql) && (this.isServerCharSetFixedWidth)))
/*       */           {
/*       */ 
/*  5402 */             setStringForClobCritical(paramInt, paramString);
/*       */           }
/*  5404 */           else if (j > this.maxVcsCharsPlsql)
/*       */           {
/*  5406 */             k = this.connection.conversion.encodedByteLength(paramString, false);
/*       */             
/*  5408 */             if (k > this.maxVcsBytesPlsql) {
/*  5409 */               setStringForClobCritical(paramInt, paramString);
/*       */             }
/*       */             else {
/*  5412 */               basicBindString(paramInt, paramString);
/*       */             }
/*       */           }
/*       */           else {
/*  5416 */             basicBindString(paramInt, paramString);
/*       */           }
/*       */           
/*       */         }
/*  5420 */         else if (j <= this.maxVcsCharsSql) {
/*  5421 */           basicBindString(paramInt, paramString);
/*       */         }
/*  5423 */         else if (j <= this.maxStreamCharsSql)
/*       */         {
/*  5425 */           basicBindCharacterStream(paramInt, new StringReader(paramString), j, true);
/*       */         }
/*       */         else
/*       */         {
/*  5429 */           setStringForClobCritical(paramInt, paramString);
/*       */         }
/*       */         
/*       */ 
/*       */       }
/*  5434 */       else if (this.sqlKind.isPlsqlOrCall()) {
/*  5435 */         if ((j > this.maxVcsBytesPlsql) || ((j > this.maxVcsNCharsPlsql) && (this.isServerNCharSetFixedWidth)))
/*       */         {
/*       */ 
/*  5438 */           setStringForClobCritical(paramInt, paramString);
/*       */         }
/*  5440 */         else if (j > this.maxVcsNCharsPlsql)
/*       */         {
/*  5442 */           k = this.connection.conversion.encodedByteLength(paramString, true);
/*       */           
/*  5444 */           if (k > this.maxVcsBytesPlsql) {
/*  5445 */             setStringForClobCritical(paramInt, paramString);
/*       */           }
/*       */           else {
/*  5448 */             basicBindString(paramInt, paramString);
/*       */           }
/*       */         }
/*       */         else {
/*  5452 */           basicBindString(paramInt, paramString);
/*       */         }
/*       */         
/*       */       }
/*  5456 */       else if (j <= this.maxVcsCharsSql) {
/*  5457 */         basicBindString(paramInt, paramString);
/*       */       }
/*       */       else {
/*  5460 */         setStringForClobCritical(paramInt, paramString);
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void basicBindNullString(int paramInt)
/*       */     throws SQLException
/*       */   {
/*  5470 */     synchronized (this.connection)
/*       */     {
/*  5472 */       int i = paramInt - 1;
/*  5473 */       this.currentRowBinders[i] = this.theVarcharNullBinder;
/*       */       
/*  5475 */       if (this.sqlKind.isPlsqlOrCall()) {
/*  5476 */         this.currentRowCharLens[i] = this.minVcsBindSize;
/*       */       } else {
/*  5478 */         this.currentRowCharLens[i] = 1;
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */   void basicBindString(int paramInt, String paramString)
/*       */     throws SQLException
/*       */   {
/*  5486 */     synchronized (this.connection)
/*       */     {
/*  5488 */       int i = paramInt - 1;
/*  5489 */       this.currentRowBinders[i] = this.theStringBinder;
/*  5490 */       int j = paramString.length();
/*       */       
/*  5492 */       if (this.sqlKind.isPlsqlOrCall())
/*       */       {
/*  5494 */         int k = this.connection.minVcsBindSize;
/*  5495 */         int m = j + 1;
/*       */         
/*  5497 */         this.currentRowCharLens[i] = (m < k ? k : m);
/*       */       }
/*       */       else {
/*  5500 */         this.currentRowCharLens[i] = (j + 1);
/*       */       }
/*  5502 */       if (this.parameterString == null) {
/*  5503 */         this.parameterString = new String[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */       }
/*       */       
/*  5506 */       this.parameterString[this.currentRank][i] = paramString;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setStringForClob(int paramInt, String paramString)
/*       */     throws SQLException
/*       */   {
/*  5525 */     if (paramString == null)
/*       */     {
/*  5527 */       setNull(paramInt, 1);
/*  5528 */       return;
/*       */     }
/*  5530 */     int i = paramString.length();
/*  5531 */     if (i == 0)
/*       */     {
/*  5533 */       setNull(paramInt, 1);
/*  5534 */       return;
/*       */     }
/*       */     
/*  5537 */     if (this.sqlKind.isPlsqlOrCall())
/*       */     {
/*  5539 */       if (i <= this.maxVcsCharsPlsql)
/*       */       {
/*  5541 */         setStringInternal(paramInt, paramString);
/*       */       }
/*       */       else
/*       */       {
/*  5545 */         setStringForClobCritical(paramInt, paramString);
/*       */       }
/*       */       
/*       */ 
/*       */     }
/*  5550 */     else if (i <= this.maxVcsCharsSql)
/*       */     {
/*  5552 */       setStringInternal(paramInt, paramString);
/*       */     }
/*       */     else
/*       */     {
/*  5556 */       setStringForClobCritical(paramInt, paramString);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setStringForClobCritical(int paramInt, String paramString)
/*       */     throws SQLException
/*       */   {
/*  5565 */     synchronized (this.connection) {
/*  5566 */       CLOB localCLOB = CLOB.createTemporary(this.connection, true, 10, this.currentRowFormOfUse[(paramInt - 1)]);
/*       */       
/*       */ 
/*  5569 */       localCLOB.setString(1L, paramString);
/*  5570 */       addToTempLobsToFree(localCLOB);
/*  5571 */       this.lastBoundClobs[(paramInt - 1)] = localCLOB;
/*  5572 */       setCLOBInternal(paramInt, localCLOB);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void setReaderContentsForClobCritical(int paramInt, Reader paramReader, long paramLong, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  5582 */     synchronized (this.connection)
/*       */     {
/*       */       try
/*       */       {
/*  5586 */         if ((paramReader = isReaderEmpty(paramReader)) == null)
/*       */         {
/*  5588 */           if (paramBoolean)
/*       */           {
/*  5590 */             throw new IOException(paramLong + " char of CLOB data cannot be read");
/*       */           }
/*  5592 */           setCLOBInternal(paramInt, null);
/*  5593 */           return;
/*       */         }
/*       */         
/*       */       }
/*       */       catch (IOException localIOException1)
/*       */       {
/*  5599 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException1);
/*  5600 */         ((SQLException)localObject1).fillInStackTrace();
/*  5601 */         throw ((Throwable)localObject1);
/*       */       }
/*       */       
/*       */ 
/*  5605 */       CLOB localCLOB = CLOB.createTemporary(this.connection, true, 10, this.currentRowFormOfUse[(paramInt - 1)]);
/*       */       
/*       */ 
/*  5608 */       Object localObject1 = (OracleClobWriter)localCLOB.setCharacterStream(1L);
/*  5609 */       int i = localCLOB.getBufferSize();
/*  5610 */       char[] arrayOfChar = new char[i];
/*  5611 */       long l = 0L;
/*  5612 */       int j = 0;
/*       */       
/*       */ 
/*  5615 */       l = paramBoolean ? paramLong : Long.MAX_VALUE;
/*       */       
/*       */       try
/*       */       {
/*  5619 */         while (l > 0L)
/*       */         {
/*  5621 */           if (l >= i) {
/*  5622 */             j = paramReader.read(arrayOfChar);
/*       */           } else {
/*  5624 */             j = paramReader.read(arrayOfChar, 0, (int)l);
/*       */           }
/*  5626 */           if (j == -1)
/*       */           {
/*  5628 */             if (!paramBoolean)
/*       */               break;
/*  5630 */             throw new IOException(l + " char of CLOB data cannot be read");
/*       */           }
/*       */           
/*       */ 
/*       */ 
/*       */ 
/*  5636 */           ((OracleClobWriter)localObject1).write(arrayOfChar, 0, j);
/*       */           
/*  5638 */           l -= j;
/*       */         }
/*  5640 */         ((OracleClobWriter)localObject1).flush();
/*       */ 
/*       */       }
/*       */       catch (IOException localIOException2)
/*       */       {
/*  5645 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException2);
/*  5646 */         localSQLException.fillInStackTrace();
/*  5647 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*  5651 */       addToTempLobsToFree(localCLOB);
/*  5652 */       this.lastBoundClobs[(paramInt - 1)] = localCLOB;
/*  5653 */       setCLOBInternal(paramInt, localCLOB);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void setAsciiStreamContentsForClobCritical(int paramInt, InputStream paramInputStream, long paramLong, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  5663 */     synchronized (this.connection)
/*       */     {
/*       */       try
/*       */       {
/*  5667 */         if ((paramInputStream = isInputStreamEmpty(paramInputStream)) == null)
/*       */         {
/*  5669 */           if (paramBoolean)
/*       */           {
/*  5671 */             throw new IOException(paramLong + " byte of CLOB data cannot be read");
/*       */           }
/*  5673 */           setCLOBInternal(paramInt, null);
/*  5674 */           return;
/*       */         }
/*       */         
/*       */       }
/*       */       catch (IOException localIOException1)
/*       */       {
/*  5680 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException1);
/*  5681 */         ((SQLException)localObject1).fillInStackTrace();
/*  5682 */         throw ((Throwable)localObject1);
/*       */       }
/*       */       
/*  5685 */       CLOB localCLOB = CLOB.createTemporary(this.connection, true, 10, this.currentRowFormOfUse[(paramInt - 1)]);
/*       */       
/*       */ 
/*  5688 */       Object localObject1 = (OracleClobWriter)localCLOB.setCharacterStream(1L);
/*  5689 */       int i = localCLOB.getBufferSize();
/*  5690 */       byte[] arrayOfByte = new byte[i];
/*  5691 */       char[] arrayOfChar = new char[i];
/*  5692 */       int j = 0;
/*       */       
/*  5694 */       long l = paramBoolean ? paramLong : Long.MAX_VALUE;
/*       */       
/*       */       try
/*       */       {
/*  5698 */         while (l > 0L)
/*       */         {
/*  5700 */           if (l >= i) {
/*  5701 */             j = paramInputStream.read(arrayOfByte);
/*       */           } else {
/*  5703 */             j = paramInputStream.read(arrayOfByte, 0, (int)l);
/*       */           }
/*  5705 */           if (j == -1)
/*       */           {
/*  5707 */             if (!paramBoolean)
/*       */               break;
/*  5709 */             throw new IOException(l + " byte of CLOB data cannot be read");
/*       */           }
/*       */           
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  5716 */           DBConversion.asciiBytesToJavaChars(arrayOfByte, j, arrayOfChar);
/*  5717 */           ((OracleClobWriter)localObject1).write(arrayOfChar, 0, j);
/*       */           
/*  5719 */           l -= j;
/*       */         }
/*  5721 */         ((OracleClobWriter)localObject1).flush();
/*       */ 
/*       */       }
/*       */       catch (IOException localIOException2)
/*       */       {
/*  5726 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException2);
/*  5727 */         localSQLException.fillInStackTrace();
/*  5728 */         throw localSQLException;
/*       */       }
/*       */       
/*  5731 */       addToTempLobsToFree(localCLOB);
/*  5732 */       this.lastBoundClobs[(paramInt - 1)] = localCLOB;
/*  5733 */       setCLOBInternal(paramInt, localCLOB);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setStringForClobAtName(String paramString1, String paramString2)
/*       */     throws SQLException
/*       */   {
/*  5751 */     String str = paramString1.intern();
/*  5752 */     String[] arrayOfString = this.sqlObject.getParameterList();
/*  5753 */     int i = 0;
/*  5754 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/*  5756 */     for (int k = 0; k < j; k++) {
/*  5757 */       if (arrayOfString[k] == str)
/*       */       {
/*  5759 */         setStringForClob(k + 1, paramString2);
/*       */         
/*  5761 */         i = 1;
/*       */       }
/*       */     }
/*  5764 */     if (i == 0)
/*       */     {
/*  5766 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString1);
/*  5767 */       localSQLException.fillInStackTrace();
/*  5768 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setFixedCHAR(int paramInt, String paramString)
/*       */     throws SQLException
/*       */   {
/*  5786 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  5789 */       setFixedCHARInternal(paramInt, paramString);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setFixedCHARInternal(int paramInt, String paramString)
/*       */     throws SQLException
/*       */   {
/*  5797 */     int i = paramInt - 1;
/*       */     
/*  5799 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  5801 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  5802 */       localSQLException1.fillInStackTrace();
/*  5803 */       throw localSQLException1;
/*       */     }
/*       */     
/*  5806 */     int j = 0;
/*       */     
/*  5808 */     if (paramString != null) {
/*  5809 */       j = paramString.length();
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  5814 */     if (j > 32766)
/*       */     {
/*  5816 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 157);
/*  5817 */       localSQLException2.fillInStackTrace();
/*  5818 */       throw localSQLException2;
/*       */     }
/*       */     
/*  5821 */     if (paramString == null)
/*       */     {
/*  5823 */       this.currentRowBinders[i] = this.theFixedCHARNullBinder;
/*  5824 */       this.currentRowCharLens[i] = 1;
/*       */     }
/*       */     else
/*       */     {
/*  5828 */       this.currentRowBinders[i] = this.theFixedCHARBinder;
/*  5829 */       this.currentRowCharLens[i] = (j + 1);
/*       */       
/*  5831 */       if (this.parameterString == null) {
/*  5832 */         this.parameterString = new String[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */       }
/*       */       
/*  5835 */       this.parameterString[this.currentRank][i] = paramString;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   /**
/*       */    * @deprecated
/*       */    */
/*       */   public void setCursor(int paramInt, ResultSet paramResultSet)
/*       */     throws SQLException
/*       */   {
/*  5855 */     synchronized (this.connection)
/*       */     {
/*  5857 */       setCursorInternal(paramInt, paramResultSet);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setCursorInternal(int paramInt, ResultSet paramResultSet)
/*       */     throws SQLException
/*       */   {
/*  5866 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  5867 */     localSQLException.fillInStackTrace();
/*  5868 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setROWID(int paramInt, ROWID paramROWID)
/*       */     throws SQLException
/*       */   {
/*  5886 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  5889 */       setROWIDInternal(paramInt, paramROWID);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setROWIDInternal(int paramInt, ROWID paramROWID)
/*       */     throws SQLException
/*       */   {
/*  5897 */     if (this.sqlKind == OracleStatement.SqlKind.CALL_BLOCK)
/*       */     {
/*  5899 */       if (paramROWID == null)
/*       */       {
/*  5901 */         setNull(paramInt, 12);
/*       */       }
/*       */       else {
/*  5904 */         setStringInternal(paramInt, paramROWID.stringValue());
/*       */       }
/*       */       
/*  5907 */       return;
/*       */     }
/*       */     
/*  5910 */     int i = paramInt - 1;
/*       */     
/*  5912 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  5914 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  5915 */       localSQLException.fillInStackTrace();
/*  5916 */       throw localSQLException;
/*       */     }
/*       */     
/*  5919 */     if ((paramROWID == null) || (paramROWID.shareBytes() == null))
/*       */     {
/*  5921 */       this.currentRowBinders[i] = this.theRowidNullBinder;
/*       */     }
/*       */     else
/*       */     {
/*  5925 */       this.currentRowBinders[i] = (T4CRowidAccessor.isUROWID(paramROWID.shareBytes(), 0) ? this.theURowidBinder : this.theRowidBinder);
/*       */       
/*  5927 */       if (this.parameterDatum == null)
/*       */       {
/*  5929 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  5934 */       this.parameterDatum[this.currentRank][i] = paramROWID.getBytes();
/*       */     }
/*       */     
/*  5937 */     this.currentRowCharLens[i] = 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setArray(int paramInt, Array paramArray)
/*       */     throws SQLException
/*       */   {
/*  5956 */     setARRAYInternal(paramInt, (ARRAY)paramArray);
/*       */   }
/*       */   
/*       */ 
/*       */   void setArrayInternal(int paramInt, Array paramArray)
/*       */     throws SQLException
/*       */   {
/*  5963 */     setARRAYInternal(paramInt, (ARRAY)paramArray);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setARRAY(int paramInt, ARRAY paramARRAY)
/*       */     throws SQLException
/*       */   {
/*  5981 */     setARRAYInternal(paramInt, paramARRAY);
/*       */   }
/*       */   
/*       */ 
/*       */   void setARRAYInternal(int paramInt, ARRAY paramARRAY)
/*       */     throws SQLException
/*       */   {
/*  5988 */     int i = paramInt - 1;
/*       */     SQLException localSQLException;
/*  5990 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  5992 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  5993 */       localSQLException.fillInStackTrace();
/*  5994 */       throw localSQLException;
/*       */     }
/*       */     
/*  5997 */     if (paramARRAY == null)
/*       */     {
/*       */ 
/*  6000 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  6001 */       localSQLException.fillInStackTrace();
/*  6002 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6007 */     synchronized (this.connection) {
/*  6008 */       setArrayCritical(i, paramARRAY);
/*       */       
/*  6010 */       this.currentRowCharLens[i] = 0;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setArrayCritical(int paramInt, ARRAY paramARRAY)
/*       */     throws SQLException
/*       */   {
/*  6027 */     ArrayDescriptor localArrayDescriptor = paramARRAY.getDescriptor();
/*       */     
/*  6029 */     if (localArrayDescriptor == null)
/*       */     {
/*       */ 
/*       */ 
/*  6033 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 61);
/*  6034 */       ((SQLException)localObject).fillInStackTrace();
/*  6035 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6040 */     this.currentRowBinders[paramInt] = this.theNamedTypeBinder;
/*       */     
/*  6042 */     if (this.parameterDatum == null)
/*       */     {
/*  6044 */       this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6049 */     this.parameterDatum[this.currentRank][paramInt] = paramARRAY.toBytes();
/*       */     
/*  6051 */     Object localObject = localArrayDescriptor.getOracleTypeCOLLECTION();
/*       */     
/*  6053 */     ((OracleTypeADT)localObject).getTOID();
/*       */     
/*  6055 */     if (this.parameterOtype == null)
/*       */     {
/*  6057 */       this.parameterOtype = new OracleTypeADT[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*       */ 
/*  6061 */     this.parameterOtype[this.currentRank][paramInt] = localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setOPAQUE(int paramInt, OPAQUE paramOPAQUE)
/*       */     throws SQLException
/*       */   {
/*  6079 */     setOPAQUEInternal(paramInt, paramOPAQUE);
/*       */   }
/*       */   
/*       */ 
/*       */   void setOPAQUEInternal(int paramInt, OPAQUE paramOPAQUE)
/*       */     throws SQLException
/*       */   {
/*  6086 */     int i = paramInt - 1;
/*       */     SQLException localSQLException;
/*  6088 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  6090 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  6091 */       localSQLException.fillInStackTrace();
/*  6092 */       throw localSQLException;
/*       */     }
/*       */     
/*  6095 */     if (paramOPAQUE == null)
/*       */     {
/*       */ 
/*  6098 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  6099 */       localSQLException.fillInStackTrace();
/*  6100 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6105 */     synchronized (this.connection) {
/*  6106 */       setOPAQUECritical(i, paramOPAQUE);
/*       */       
/*  6108 */       this.currentRowCharLens[i] = 0;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setOPAQUECritical(int paramInt, OPAQUE paramOPAQUE)
/*       */     throws SQLException
/*       */   {
/*  6125 */     OpaqueDescriptor localOpaqueDescriptor = paramOPAQUE.getDescriptor();
/*       */     
/*  6127 */     if (localOpaqueDescriptor == null)
/*       */     {
/*       */ 
/*       */ 
/*  6131 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 61);
/*  6132 */       ((SQLException)localObject).fillInStackTrace();
/*  6133 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6138 */     this.currentRowBinders[paramInt] = this.theNamedTypeBinder;
/*       */     
/*  6140 */     if (this.parameterDatum == null)
/*       */     {
/*  6142 */       this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6147 */     this.parameterDatum[this.currentRank][paramInt] = paramOPAQUE.toBytes();
/*       */     
/*  6149 */     Object localObject = (OracleTypeADT)localOpaqueDescriptor.getPickler();
/*       */     
/*  6151 */     ((OracleTypeADT)localObject).getTOID();
/*       */     
/*  6153 */     if (this.parameterOtype == null)
/*       */     {
/*  6155 */       this.parameterOtype = new OracleTypeADT[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*       */ 
/*  6159 */     this.parameterOtype[this.currentRank][paramInt] = localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setSQLXMLInternal(int paramInt, SQLXML paramSQLXML)
/*       */     throws SQLException
/*       */   {
/*  6179 */     if (paramSQLXML == null)
/*       */     {
/*       */ 
/*  6182 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  6183 */       localSQLException.fillInStackTrace();
/*  6184 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6189 */     setOPAQUEInternal(paramInt, (OPAQUE)paramSQLXML);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setStructDescriptor(int paramInt, StructDescriptor paramStructDescriptor)
/*       */     throws SQLException
/*       */   {
/*  6210 */     setStructDescriptorInternal(paramInt, paramStructDescriptor);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setStructDescriptorInternal(int paramInt, StructDescriptor paramStructDescriptor)
/*       */     throws SQLException
/*       */   {
/*  6218 */     int i = paramInt - 1;
/*       */     SQLException localSQLException;
/*  6220 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  6222 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  6223 */       localSQLException.fillInStackTrace();
/*  6224 */       throw localSQLException;
/*       */     }
/*       */     
/*  6227 */     if (paramStructDescriptor == null)
/*       */     {
/*  6229 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  6230 */       localSQLException.fillInStackTrace();
/*  6231 */       throw localSQLException;
/*       */     }
/*       */     
/*  6234 */     synchronized (this.connection) {
/*  6235 */       setStructDescriptorCritical(i, paramStructDescriptor);
/*       */       
/*  6237 */       this.currentRowCharLens[i] = 0;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setStructDescriptorCritical(int paramInt, StructDescriptor paramStructDescriptor)
/*       */     throws SQLException
/*       */   {
/*  6253 */     this.currentRowBinders[paramInt] = this.theNamedTypeBinder;
/*       */     
/*  6255 */     if (this.parameterDatum == null) {
/*  6256 */       this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */     }
/*       */     
/*  6259 */     OracleTypeADT localOracleTypeADT = paramStructDescriptor.getOracleTypeADT();
/*       */     
/*  6261 */     localOracleTypeADT.getTOID();
/*       */     
/*  6263 */     if (this.parameterOtype == null) {
/*  6264 */       this.parameterOtype = new OracleTypeADT[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*       */ 
/*  6268 */     this.parameterOtype[this.currentRank][paramInt] = localOracleTypeADT;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setStructDescriptorAtName(String paramString, StructDescriptor paramStructDescriptor)
/*       */     throws SQLException
/*       */   {
/*  6287 */     String str = paramString.intern();
/*  6288 */     String[] arrayOfString = this.sqlObject.getParameterList();
/*  6289 */     int i = 0;
/*  6290 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/*  6292 */     for (int k = 0; k < j; k++) {
/*  6293 */       if (arrayOfString[k] == str)
/*       */       {
/*  6295 */         setStructDescriptorInternal(k + 1, paramStructDescriptor);
/*       */         
/*  6297 */         i = 1;
/*       */       }
/*       */     }
/*  6300 */     if (i == 0)
/*       */     {
/*  6302 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/*  6303 */       localSQLException.fillInStackTrace();
/*  6304 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setPreBindsCompelete()
/*       */     throws SQLException
/*       */   {}
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setSTRUCT(int paramInt, STRUCT paramSTRUCT)
/*       */     throws SQLException
/*       */   {
/*  6333 */     setSTRUCTInternal(paramInt, paramSTRUCT);
/*       */   }
/*       */   
/*       */ 
/*       */   void setSTRUCTInternal(int paramInt, STRUCT paramSTRUCT)
/*       */     throws SQLException
/*       */   {
/*  6340 */     int i = paramInt - 1;
/*       */     SQLException localSQLException;
/*  6342 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  6344 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  6345 */       localSQLException.fillInStackTrace();
/*  6346 */       throw localSQLException;
/*       */     }
/*       */     
/*  6349 */     if (paramSTRUCT == null)
/*       */     {
/*       */ 
/*  6352 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  6353 */       localSQLException.fillInStackTrace();
/*  6354 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6359 */     synchronized (this.connection) {
/*  6360 */       setSTRUCTCritical(i, paramSTRUCT);
/*       */       
/*  6362 */       this.currentRowCharLens[i] = 0;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setSTRUCTCritical(int paramInt, STRUCT paramSTRUCT)
/*       */     throws SQLException
/*       */   {
/*  6380 */     StructDescriptor localStructDescriptor = paramSTRUCT.getDescriptor();
/*       */     
/*  6382 */     if (localStructDescriptor == null)
/*       */     {
/*       */ 
/*       */ 
/*  6386 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 61);
/*  6387 */       ((SQLException)localObject).fillInStackTrace();
/*  6388 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6393 */     this.currentRowBinders[paramInt] = this.theNamedTypeBinder;
/*       */     
/*  6395 */     if (this.parameterDatum == null)
/*       */     {
/*  6397 */       this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  6402 */     this.parameterDatum[this.currentRank][paramInt] = paramSTRUCT.toBytes();
/*       */     
/*       */ 
/*       */ 
/*  6406 */     Object localObject = localStructDescriptor.getOracleTypeADT();
/*       */     
/*  6408 */     ((OracleTypeADT)localObject).getTOID();
/*       */     
/*  6410 */     if (this.parameterOtype == null)
/*       */     {
/*  6412 */       this.parameterOtype = new OracleTypeADT[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*       */ 
/*  6416 */     this.parameterOtype[this.currentRank][paramInt] = localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setRAW(int paramInt, RAW paramRAW)
/*       */     throws SQLException
/*       */   {
/*  6434 */     setRAWInternal(paramInt, paramRAW);
/*       */   }
/*       */   
/*       */ 
/*       */   void setRAWInternal(int paramInt, RAW paramRAW)
/*       */     throws SQLException
/*       */   {
/*  6441 */     int i = 0;
/*  6442 */     synchronized (this.connection) {
/*  6443 */       int j = paramInt - 1;
/*       */       
/*  6445 */       if ((j < 0) || (paramInt > this.numberOfBindPositions))
/*       */       {
/*  6447 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  6448 */         localSQLException.fillInStackTrace();
/*  6449 */         throw localSQLException;
/*       */       }
/*       */       
/*  6452 */       this.currentRowCharLens[j] = 0;
/*       */       
/*  6454 */       if (paramRAW == null) {
/*  6455 */         this.currentRowBinders[j] = this.theRawNullBinder;
/*       */       } else
/*  6457 */         i = 1;
/*       */     }
/*  6459 */     if (i != 0) {
/*  6460 */       setBytesInternal(paramInt, paramRAW.getBytes());
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCHAR(int paramInt, CHAR paramCHAR)
/*       */     throws SQLException
/*       */   {
/*  6476 */     synchronized (this.connection)
/*       */     {
/*  6478 */       setCHARInternal(paramInt, paramCHAR);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setCHARInternal(int paramInt, CHAR paramCHAR)
/*       */     throws SQLException
/*       */   {
/*  6486 */     int i = paramInt - 1;
/*       */     
/*  6488 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  6490 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  6491 */       localSQLException.fillInStackTrace();
/*  6492 */       throw localSQLException;
/*       */     }
/*       */     
/*  6495 */     if ((paramCHAR == null) || (paramCHAR.getLength() == 0L))
/*       */     {
/*       */ 
/*  6498 */       this.currentRowBinders[i] = this.theSetCHARNullBinder;
/*  6499 */       this.currentRowCharLens[i] = 1;
/*       */     }
/*       */     else
/*       */     {
/*  6503 */       int j = (short)paramCHAR.oracleId();
/*  6504 */       this.currentRowBinders[i] = this.theSetCHARBinder;
/*       */       
/*  6506 */       if (this.parameterDatum == null)
/*       */       {
/*  6508 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*       */ 
/*  6512 */       CharacterSet localCharacterSet = this.currentRowFormOfUse[i] == 2 ? this.connection.setCHARNCharSetObj : this.connection.setCHARCharSetObj;
/*       */       
/*       */ 
/*       */ 
/*       */       byte[] arrayOfByte1;
/*       */       
/*       */ 
/*  6519 */       if ((localCharacterSet != null) && (localCharacterSet.getOracleId() != j))
/*       */       {
/*  6521 */         byte[] arrayOfByte2 = paramCHAR.shareBytes();
/*       */         
/*  6523 */         arrayOfByte1 = localCharacterSet.convert(paramCHAR.getCharacterSet(), arrayOfByte2, 0, arrayOfByte2.length);
/*       */       }
/*       */       else {
/*  6526 */         arrayOfByte1 = paramCHAR.getBytes();
/*       */       }
/*  6528 */       this.parameterDatum[this.currentRank][i] = arrayOfByte1;
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  6534 */       this.currentRowCharLens[i] = ((arrayOfByte1.length + 1 >> 1) + 1);
/*       */     }
/*       */     
/*       */ 
/*  6538 */     if (this.sqlKind.isPlsqlOrCall())
/*       */     {
/*       */ 
/*       */ 
/*  6542 */       if (this.currentRowCharLens[i] < this.minVcsBindSize) {
/*  6543 */         this.currentRowCharLens[i] = this.minVcsBindSize;
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setDATE(int paramInt, DATE paramDATE)
/*       */     throws SQLException
/*       */   {
/*  6566 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  6569 */       setDATEInternal(paramInt, paramDATE);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setDATEInternal(int paramInt, DATE paramDATE)
/*       */     throws SQLException
/*       */   {
/*  6577 */     int i = paramInt - 1;
/*       */     
/*  6579 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  6581 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  6582 */       localSQLException.fillInStackTrace();
/*  6583 */       throw localSQLException;
/*       */     }
/*       */     
/*  6586 */     this.currentRowCharLens[i] = 0;
/*       */     
/*  6588 */     if (paramDATE == null)
/*       */     {
/*  6590 */       this.currentRowBinders[i] = this.theDateNullBinder;
/*       */     }
/*       */     else
/*       */     {
/*  6594 */       this.currentRowBinders[i] = this.theOracleDateBinder;
/*       */       
/*  6596 */       if (this.parameterDatum == null)
/*       */       {
/*  6598 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  6603 */       this.parameterDatum[this.currentRank][i] = paramDATE.getBytes();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNUMBER(int paramInt, NUMBER paramNUMBER)
/*       */     throws SQLException
/*       */   {
/*  6620 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  6623 */       setNUMBERInternal(paramInt, paramNUMBER);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setNUMBERInternal(int paramInt, NUMBER paramNUMBER)
/*       */     throws SQLException
/*       */   {
/*  6631 */     int i = paramInt - 1;
/*       */     
/*  6633 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  6635 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  6636 */       localSQLException.fillInStackTrace();
/*  6637 */       throw localSQLException;
/*       */     }
/*       */     
/*  6640 */     this.currentRowCharLens[i] = 0;
/*       */     
/*  6642 */     if (paramNUMBER == null)
/*       */     {
/*  6644 */       this.currentRowBinders[i] = this.theVarnumNullBinder;
/*       */     }
/*       */     else
/*       */     {
/*  6648 */       this.currentRowBinders[i] = this.theOracleNumberBinder;
/*       */       
/*  6650 */       if (this.parameterDatum == null)
/*       */       {
/*  6652 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  6657 */       this.parameterDatum[this.currentRank][i] = paramNUMBER.getBytes();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBLOB(int paramInt, BLOB paramBLOB)
/*       */     throws SQLException
/*       */   {
/*  6674 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  6677 */       setBLOBInternal(paramInt, paramBLOB);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setBLOBInternal(int paramInt, BLOB paramBLOB)
/*       */     throws SQLException
/*       */   {
/*  6685 */     int i = paramInt - 1;
/*       */     
/*  6687 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  6689 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  6690 */       localSQLException.fillInStackTrace();
/*  6691 */       throw localSQLException;
/*       */     }
/*       */     
/*  6694 */     this.currentRowCharLens[i] = 0;
/*       */     
/*  6696 */     if (paramBLOB == null) {
/*  6697 */       this.currentRowBinders[i] = this.theBlobNullBinder;
/*       */     }
/*       */     else {
/*  6700 */       this.currentRowBinders[i] = this.theBlobBinder;
/*       */       
/*  6702 */       if (this.parameterDatum == null) {
/*  6703 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  6708 */       this.parameterDatum[this.currentRank][i] = paramBLOB.getBytes();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBlob(int paramInt, Blob paramBlob)
/*       */     throws SQLException
/*       */   {
/*  6725 */     synchronized (this.connection)
/*       */     {
/*  6727 */       setBLOBInternal(paramInt, (BLOB)paramBlob);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setBlobInternal(int paramInt, Blob paramBlob)
/*       */     throws SQLException
/*       */   {
/*  6735 */     setBLOBInternal(paramInt, (BLOB)paramBlob);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCLOB(int paramInt, CLOB paramCLOB)
/*       */     throws SQLException
/*       */   {
/*  6752 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  6755 */       setCLOBInternal(paramInt, paramCLOB);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setCLOBInternal(int paramInt, CLOB paramCLOB)
/*       */     throws SQLException
/*       */   {
/*  6763 */     int i = paramInt - 1;
/*       */     
/*  6765 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  6767 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  6768 */       localSQLException.fillInStackTrace();
/*  6769 */       throw localSQLException;
/*       */     }
/*       */     
/*  6772 */     this.currentRowCharLens[i] = 0;
/*       */     
/*  6774 */     if (paramCLOB == null) {
/*  6775 */       this.currentRowBinders[i] = this.theClobNullBinder;
/*       */     }
/*       */     else {
/*  6778 */       this.currentRowBinders[i] = this.theClobBinder;
/*       */       
/*  6780 */       if (this.parameterDatum == null) {
/*  6781 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  6786 */       this.parameterDatum[this.currentRank][i] = paramCLOB.getBytes();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setClob(int paramInt, Clob paramClob)
/*       */     throws SQLException
/*       */   {
/*  6803 */     synchronized (this.connection)
/*       */     {
/*  6805 */       setCLOBInternal(paramInt, (CLOB)paramClob);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setClobInternal(int paramInt, Clob paramClob)
/*       */     throws SQLException
/*       */   {
/*  6813 */     setCLOBInternal(paramInt, (CLOB)paramClob);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBFILE(int paramInt, BFILE paramBFILE)
/*       */     throws SQLException
/*       */   {
/*  6829 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  6832 */       setBFILEInternal(paramInt, paramBFILE);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setBFILEInternal(int paramInt, BFILE paramBFILE)
/*       */     throws SQLException
/*       */   {
/*  6840 */     int i = paramInt - 1;
/*       */     
/*  6842 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  6844 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  6845 */       localSQLException.fillInStackTrace();
/*  6846 */       throw localSQLException;
/*       */     }
/*       */     
/*  6849 */     this.currentRowCharLens[i] = 0;
/*       */     
/*  6851 */     if (paramBFILE == null) {
/*  6852 */       this.currentRowBinders[i] = this.theBfileNullBinder;
/*       */     }
/*       */     else {
/*  6855 */       this.currentRowBinders[i] = this.theBfileBinder;
/*       */       
/*  6857 */       if (this.parameterDatum == null) {
/*  6858 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  6863 */       this.parameterDatum[this.currentRank][i] = paramBFILE.getBytes();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBfile(int paramInt, BFILE paramBFILE)
/*       */     throws SQLException
/*       */   {
/*  6880 */     synchronized (this.connection)
/*       */     {
/*  6882 */       setBFILEInternal(paramInt, paramBFILE);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setBfileInternal(int paramInt, BFILE paramBFILE)
/*       */     throws SQLException
/*       */   {
/*  6890 */     setBFILEInternal(paramInt, paramBFILE);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBytes(int paramInt, byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  6906 */     setBytesInternal(paramInt, paramArrayOfByte);
/*       */   }
/*       */   
/*       */ 
/*       */   void setBytesInternal(int paramInt, byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  6913 */     int i = paramInt - 1;
/*       */     
/*  6915 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  6917 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  6918 */       localSQLException.fillInStackTrace();
/*  6919 */       throw localSQLException;
/*       */     }
/*  6921 */     int j = paramArrayOfByte != null ? paramArrayOfByte.length : 0;
/*  6922 */     if (j == 0)
/*       */     {
/*  6924 */       setNullInternal(paramInt, -2);
/*       */ 
/*       */ 
/*       */     }
/*  6928 */     else if (this.sqlKind == OracleStatement.SqlKind.PLSQL_BLOCK)
/*       */     {
/*  6930 */       if (j > this.maxRawBytesPlsql) {
/*  6931 */         setBytesForBlobCritical(paramInt, paramArrayOfByte);
/*       */       } else {
/*  6933 */         basicBindBytes(paramInt, paramArrayOfByte);
/*       */       }
/*  6935 */     } else if (this.sqlKind == OracleStatement.SqlKind.CALL_BLOCK)
/*       */     {
/*  6937 */       if (j > this.maxRawBytesPlsql) {
/*  6938 */         setBytesForBlobCritical(paramInt, paramArrayOfByte);
/*       */       } else {
/*  6940 */         basicBindBytes(paramInt, paramArrayOfByte);
/*       */       }
/*       */       
/*       */     }
/*  6944 */     else if (j > this.maxRawBytesSql)
/*       */     {
/*  6946 */       bindBytesAsStream(paramInt, paramArrayOfByte);
/*       */     }
/*       */     else {
/*  6949 */       basicBindBytes(paramInt, paramArrayOfByte);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void bindBytesAsStream(int paramInt, byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  6958 */     int i = paramArrayOfByte.length;
/*  6959 */     byte[] arrayOfByte = new byte[i];
/*  6960 */     System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, i);
/*  6961 */     set_execute_batch(1);
/*  6962 */     basicBindBinaryStream(paramInt, new ByteArrayInputStream(arrayOfByte), i, true);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void basicBindBytes(int paramInt, byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  6970 */     synchronized (this.connection)
/*       */     {
/*  6972 */       int i = paramInt - 1;
/*  6973 */       Binder localBinder = this.sqlKind.isPlsqlOrCall() ? this.thePlsqlRawBinder : this.theRawBinder;
/*  6974 */       this.currentRowBinders[i] = localBinder;
/*       */       
/*  6976 */       if (this.parameterDatum == null) {
/*  6977 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*  6980 */       this.parameterDatum[this.currentRank][i] = paramArrayOfByte;
/*  6981 */       this.currentRowCharLens[i] = 0;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void basicBindBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  6991 */     basicBindBinaryStream(paramInt1, paramInputStream, paramInt2, false);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void basicBindBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  6999 */     synchronized (this.connection)
/*       */     {
/*  7001 */       int i = paramInt1 - 1;
/*       */       
/*  7003 */       if (paramBoolean) {
/*  7004 */         this.currentRowBinders[i] = this.theLongRawStreamForBytesBinder;
/*       */       } else {
/*  7006 */         this.currentRowBinders[i] = this.theLongRawStreamBinder;
/*       */       }
/*  7008 */       if (this.parameterStream == null) {
/*  7009 */         this.parameterStream = new InputStream[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */       }
/*       */       
/*  7012 */       this.parameterStream[this.currentRank][i] = (paramBoolean ? this.connection.conversion.ConvertStreamInternal(paramInputStream, 6, paramInt2) : this.connection.conversion.ConvertStream(paramInputStream, 6, paramInt2));
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*  7017 */       this.currentRowCharLens[i] = 0;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBytesForBlob(int paramInt, byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  7037 */     if (paramArrayOfByte == null)
/*       */     {
/*  7039 */       setNull(paramInt, -2);
/*  7040 */       return;
/*       */     }
/*  7042 */     int i = paramArrayOfByte.length;
/*  7043 */     if (i == 0)
/*       */     {
/*  7045 */       setNull(paramInt, -2);
/*  7046 */       return;
/*       */     }
/*  7048 */     if (this.sqlKind.isPlsqlOrCall())
/*       */     {
/*  7050 */       if (i <= this.maxRawBytesPlsql)
/*       */       {
/*  7052 */         setBytes(paramInt, paramArrayOfByte);
/*       */       }
/*       */       else
/*       */       {
/*  7056 */         setBytesForBlobCritical(paramInt, paramArrayOfByte);
/*       */       }
/*       */       
/*       */ 
/*       */     }
/*  7061 */     else if (i <= this.maxRawBytesSql)
/*       */     {
/*  7063 */       setBytes(paramInt, paramArrayOfByte);
/*       */ 
/*       */ 
/*       */ 
/*       */     }
/*       */     else
/*       */     {
/*       */ 
/*       */ 
/*  7072 */       setBytesForBlobCritical(paramInt, paramArrayOfByte);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void setBytesForBlobCritical(int paramInt, byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  7082 */     BLOB localBLOB = BLOB.createTemporary(this.connection, true, 10);
/*       */     
/*  7084 */     localBLOB.putBytes(1L, paramArrayOfByte);
/*  7085 */     addToTempLobsToFree(localBLOB);
/*  7086 */     this.lastBoundBlobs[(paramInt - 1)] = localBLOB;
/*  7087 */     setBLOBInternal(paramInt, localBLOB);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setBinaryStreamContentsForBlobCritical(int paramInt, InputStream paramInputStream, long paramLong, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  7098 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*       */       try
/*       */       {
/*  7103 */         if ((paramInputStream = isInputStreamEmpty(paramInputStream)) == null)
/*       */         {
/*  7105 */           if (paramBoolean)
/*       */           {
/*  7107 */             throw new IOException(paramLong + " byte of BLOB data cannot be read");
/*       */           }
/*  7109 */           setBLOBInternal(paramInt, null);
/*  7110 */           return;
/*       */         }
/*       */         
/*       */       }
/*       */       catch (IOException localIOException1)
/*       */       {
/*  7116 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException1);
/*  7117 */         ((SQLException)localObject1).fillInStackTrace();
/*  7118 */         throw ((Throwable)localObject1);
/*       */       }
/*       */       
/*       */ 
/*  7122 */       BLOB localBLOB = BLOB.createTemporary(this.connection, true, 10);
/*       */       
/*       */ 
/*  7125 */       Object localObject1 = (OracleBlobOutputStream)localBLOB.setBinaryStream(1L);
/*       */       
/*  7127 */       int i = localBLOB.getBufferSize();
/*  7128 */       byte[] arrayOfByte = new byte[i];
/*  7129 */       long l = 0L;
/*  7130 */       int j = 0;
/*       */       
/*       */ 
/*  7133 */       l = paramBoolean ? paramLong : Long.MAX_VALUE;
/*       */       
/*       */       try
/*       */       {
/*  7137 */         while (l > 0L)
/*       */         {
/*  7139 */           if (l >= i) {
/*  7140 */             j = paramInputStream.read(arrayOfByte);
/*       */           } else {
/*  7142 */             j = paramInputStream.read(arrayOfByte, 0, (int)l);
/*       */           }
/*  7144 */           if (j == -1)
/*       */           {
/*  7146 */             if (!paramBoolean)
/*       */               break;
/*  7148 */             throw new IOException(l + " byte of BLOB data cannot be read");
/*       */           }
/*       */           
/*       */ 
/*       */ 
/*       */ 
/*  7154 */           ((OracleBlobOutputStream)localObject1).write(arrayOfByte, 0, j);
/*  7155 */           l -= j;
/*       */         }
/*  7157 */         ((OracleBlobOutputStream)localObject1).flush();
/*       */ 
/*       */       }
/*       */       catch (IOException localIOException2)
/*       */       {
/*  7162 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException2);
/*  7163 */         localSQLException.fillInStackTrace();
/*  7164 */         throw localSQLException;
/*       */       }
/*       */       
/*  7167 */       addToTempLobsToFree(localBLOB);
/*  7168 */       this.lastBoundBlobs[(paramInt - 1)] = localBLOB;
/*  7169 */       setBLOBInternal(paramInt, localBLOB);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBytesForBlobAtName(String paramString, byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/*  7187 */     String str = paramString.intern();
/*  7188 */     String[] arrayOfString = this.sqlObject.getParameterList();
/*  7189 */     int i = 0;
/*  7190 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/*  7192 */     for (int k = 0; k < j; k++) {
/*  7193 */       if (arrayOfString[k] == str)
/*       */       {
/*  7195 */         setBytesForBlob(k + 1, paramArrayOfByte);
/*       */         
/*  7197 */         i = 1;
/*       */       }
/*       */     }
/*  7200 */     if (i == 0)
/*       */     {
/*  7202 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/*  7203 */       localSQLException.fillInStackTrace();
/*  7204 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setInternalBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  7218 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  7221 */       setInternalBytesInternal(paramInt1, paramArrayOfByte, paramInt2);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setInternalBytesInternal(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  7233 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  7234 */     localSQLException.fillInStackTrace();
/*  7235 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setDate(int paramInt, Date paramDate)
/*       */     throws SQLException
/*       */   {
/*  7250 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  7253 */       setDATEInternal(paramInt, paramDate == null ? null : new DATE(paramDate, getDefaultCalendar()));
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void setDateInternal(int paramInt, Date paramDate)
/*       */     throws SQLException
/*       */   {
/*  7263 */     setDATEInternal(paramInt, paramDate == null ? null : new DATE(paramDate, getDefaultCalendar()));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTime(int paramInt, Time paramTime)
/*       */     throws SQLException
/*       */   {
/*  7279 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  7282 */       setTimeInternal(paramInt, paramTime);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setTimeInternal(int paramInt, Time paramTime)
/*       */     throws SQLException
/*       */   {
/*  7290 */     int i = paramInt - 1;
/*       */     
/*  7292 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  7294 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  7295 */       localSQLException.fillInStackTrace();
/*  7296 */       throw localSQLException;
/*       */     }
/*       */     
/*  7299 */     if (paramTime == null) {
/*  7300 */       this.currentRowBinders[i] = this.theDateNullBinder;
/*       */     }
/*       */     else {
/*  7303 */       this.currentRowBinders[i] = this.theTimeBinder;
/*       */       
/*  7305 */       if (this.parameterTime == null) {
/*  7306 */         this.parameterTime = new Time[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  7311 */       this.parameterTime[this.currentRank][i] = paramTime;
/*       */     }
/*       */     
/*  7314 */     this.currentRowCharLens[i] = 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTimestamp(int paramInt, Timestamp paramTimestamp)
/*       */     throws SQLException
/*       */   {
/*  7329 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  7332 */       setTimestampInternal(paramInt, paramTimestamp);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void setTimestampInternal(int paramInt, Timestamp paramTimestamp)
/*       */     throws SQLException
/*       */   {
/*  7342 */     int i = paramInt - 1;
/*       */     
/*  7344 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  7346 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*       */       
/*  7348 */       localSQLException.fillInStackTrace();
/*  7349 */       throw localSQLException;
/*       */     }
/*       */     
/*  7352 */     if (paramTimestamp == null) {
/*  7353 */       this.currentRowBinders[i] = this.theTimestampNullBinder;
/*       */     }
/*       */     else {
/*  7356 */       this.currentRowBinders[i] = this.theTimestampBinder;
/*       */       
/*  7358 */       if (this.parameterTimestamp == null) {
/*  7359 */         this.parameterTimestamp = new Timestamp[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  7364 */       this.parameterTimestamp[this.currentRank][i] = paramTimestamp;
/*       */     }
/*       */     
/*  7367 */     this.currentRowCharLens[i] = 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setINTERVALYM(int paramInt, INTERVALYM paramINTERVALYM)
/*       */     throws SQLException
/*       */   {
/*  7388 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  7391 */       setINTERVALYMInternal(paramInt, paramINTERVALYM);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setINTERVALYMInternal(int paramInt, INTERVALYM paramINTERVALYM)
/*       */     throws SQLException
/*       */   {
/*  7399 */     int i = paramInt - 1;
/*       */     
/*  7401 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  7403 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  7404 */       localSQLException.fillInStackTrace();
/*  7405 */       throw localSQLException;
/*       */     }
/*       */     
/*  7408 */     if (paramINTERVALYM == null)
/*       */     {
/*  7410 */       this.currentRowBinders[i] = this.theIntervalYMNullBinder;
/*       */     }
/*       */     else
/*       */     {
/*  7414 */       this.currentRowBinders[i] = this.theIntervalYMBinder;
/*       */       
/*  7416 */       if (this.parameterDatum == null)
/*       */       {
/*  7418 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  7423 */       this.parameterDatum[this.currentRank][i] = paramINTERVALYM.getBytes();
/*       */     }
/*       */     
/*  7426 */     this.currentRowCharLens[i] = 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setINTERVALDS(int paramInt, INTERVALDS paramINTERVALDS)
/*       */     throws SQLException
/*       */   {
/*  7447 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  7450 */       setINTERVALDSInternal(paramInt, paramINTERVALDS);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setINTERVALDSInternal(int paramInt, INTERVALDS paramINTERVALDS)
/*       */     throws SQLException
/*       */   {
/*  7458 */     int i = paramInt - 1;
/*       */     
/*  7460 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  7462 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  7463 */       localSQLException.fillInStackTrace();
/*  7464 */       throw localSQLException;
/*       */     }
/*       */     
/*  7467 */     if (paramINTERVALDS == null)
/*       */     {
/*  7469 */       this.currentRowBinders[i] = this.theIntervalDSNullBinder;
/*       */     }
/*       */     else
/*       */     {
/*  7473 */       this.currentRowBinders[i] = this.theIntervalDSBinder;
/*       */       
/*  7475 */       if (this.parameterDatum == null)
/*       */       {
/*  7477 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  7482 */       this.parameterDatum[this.currentRank][i] = paramINTERVALDS.getBytes();
/*       */     }
/*       */     
/*  7485 */     this.currentRowCharLens[i] = 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTIMESTAMP(int paramInt, TIMESTAMP paramTIMESTAMP)
/*       */     throws SQLException
/*       */   {
/*  7506 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  7509 */       setTIMESTAMPInternal(paramInt, paramTIMESTAMP);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setTIMESTAMPInternal(int paramInt, TIMESTAMP paramTIMESTAMP)
/*       */     throws SQLException
/*       */   {
/*  7517 */     int i = paramInt - 1;
/*       */     
/*  7519 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  7521 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  7522 */       localSQLException.fillInStackTrace();
/*  7523 */       throw localSQLException;
/*       */     }
/*       */     
/*  7526 */     if (paramTIMESTAMP == null)
/*       */     {
/*  7528 */       this.currentRowBinders[i] = this.theTimestampNullBinder;
/*       */     }
/*       */     else
/*       */     {
/*  7532 */       this.currentRowBinders[i] = this.theOracleTimestampBinder;
/*       */       
/*  7534 */       if (this.parameterDatum == null)
/*       */       {
/*  7536 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  7541 */       this.parameterDatum[this.currentRank][i] = paramTIMESTAMP.getBytes();
/*       */     }
/*       */     
/*  7544 */     this.currentRowCharLens[i] = 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTIMESTAMPTZ(int paramInt, TIMESTAMPTZ paramTIMESTAMPTZ)
/*       */     throws SQLException
/*       */   {
/*  7565 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  7568 */       setTIMESTAMPTZInternal(paramInt, paramTIMESTAMPTZ);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setTIMESTAMPTZInternal(int paramInt, TIMESTAMPTZ paramTIMESTAMPTZ)
/*       */     throws SQLException
/*       */   {
/*  7577 */     int i = paramInt - 1;
/*       */     
/*  7579 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  7581 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  7582 */       localSQLException.fillInStackTrace();
/*  7583 */       throw localSQLException;
/*       */     }
/*       */     
/*  7586 */     if (paramTIMESTAMPTZ == null)
/*       */     {
/*  7588 */       this.currentRowBinders[i] = this.theTSTZNullBinder;
/*       */     }
/*       */     else
/*       */     {
/*  7592 */       this.currentRowBinders[i] = this.theTSTZBinder;
/*       */       
/*  7594 */       if (this.parameterDatum == null)
/*       */       {
/*  7596 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  7601 */       this.parameterDatum[this.currentRank][i] = paramTIMESTAMPTZ.getBytes();
/*       */     }
/*       */     
/*  7604 */     this.currentRowCharLens[i] = 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTIMESTAMPLTZ(int paramInt, TIMESTAMPLTZ paramTIMESTAMPLTZ)
/*       */     throws SQLException
/*       */   {
/*  7629 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  7632 */       setTIMESTAMPLTZInternal(paramInt, paramTIMESTAMPLTZ);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setTIMESTAMPLTZInternal(int paramInt, TIMESTAMPLTZ paramTIMESTAMPLTZ)
/*       */     throws SQLException
/*       */   {
/*  7641 */     if (this.connection.getSessionTimeZone() == null)
/*       */     {
/*       */ 
/*  7644 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 105);
/*  7645 */       localSQLException1.fillInStackTrace();
/*  7646 */       throw localSQLException1;
/*       */     }
/*       */     
/*       */ 
/*  7650 */     int i = paramInt - 1;
/*       */     
/*  7652 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  7654 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  7655 */       localSQLException2.fillInStackTrace();
/*  7656 */       throw localSQLException2;
/*       */     }
/*       */     
/*  7659 */     if (paramTIMESTAMPLTZ == null)
/*       */     {
/*  7661 */       this.currentRowBinders[i] = this.theTSLTZNullBinder;
/*       */     }
/*       */     else
/*       */     {
/*  7665 */       this.currentRowBinders[i] = this.theTSLTZBinder;
/*       */       
/*  7667 */       if (this.parameterDatum == null)
/*       */       {
/*  7669 */         this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  7674 */       this.parameterDatum[this.currentRank][i] = paramTIMESTAMPLTZ.getBytes();
/*       */     }
/*       */     
/*  7677 */     this.currentRowCharLens[i] = 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   private Reader isReaderEmpty(Reader paramReader)
/*       */     throws IOException
/*       */   {
/*  7688 */     if (!paramReader.markSupported())
/*  7689 */       paramReader = new BufferedReader(paramReader, 5);
/*  7690 */     paramReader.mark(100);
/*  7691 */     int i; if ((i = paramReader.read()) == -1) {
/*  7692 */       return null;
/*       */     }
/*       */     
/*  7695 */     paramReader.reset();
/*  7696 */     return paramReader;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   private InputStream isInputStreamEmpty(InputStream paramInputStream)
/*       */     throws IOException
/*       */   {
/*  7704 */     if (!paramInputStream.markSupported())
/*  7705 */       paramInputStream = new BufferedInputStream(paramInputStream, 5);
/*  7706 */     paramInputStream.mark(100);
/*  7707 */     int i; if ((i = paramInputStream.read()) == -1) {
/*  7708 */       return null;
/*       */     }
/*       */     
/*  7711 */     paramInputStream.reset();
/*  7712 */     return paramInputStream;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  7738 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  7741 */       setAsciiStreamInternal(paramInt1, paramInputStream, paramInt2);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setAsciiStreamInternal(int paramInt1, InputStream paramInputStream, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  7750 */     setAsciiStreamInternal(paramInt1, paramInputStream, paramInt2, true);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setAsciiStreamInternal(int paramInt, InputStream paramInputStream, long paramLong, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  7758 */     int i = paramInt - 1;
/*       */     SQLException localSQLException;
/*  7760 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  7762 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  7763 */       localSQLException.fillInStackTrace();
/*  7764 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  7770 */     set_execute_batch(1);
/*  7771 */     checkUserStreamForDuplicates(paramInputStream, i);
/*  7772 */     if (paramInputStream == null) {
/*  7773 */       basicBindNullString(paramInt);
/*  7774 */     } else { if ((this.userRsetType != 1) && ((paramLong > this.maxVcsCharsSql) || (!paramBoolean)))
/*       */       {
/*  7776 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 169);
/*  7777 */         localSQLException.fillInStackTrace();
/*  7778 */         throw localSQLException;
/*       */       }
/*  7780 */       if (!paramBoolean) {
/*  7781 */         setAsciiStreamContentsForClobCritical(paramInt, paramInputStream, paramLong, paramBoolean);
/*  7782 */       } else if (this.currentRowFormOfUse[i] == 1)
/*       */       {
/*  7784 */         if (this.sqlKind.isPlsqlOrCall())
/*       */         {
/*  7786 */           if ((paramLong <= this.maxVcsCharsPlsql) && (!this.connection.retainV9BindBehavior))
/*       */           {
/*  7788 */             setAsciiStreamContentsForStringInternal(paramInt, paramInputStream, (int)paramLong);
/*       */ 
/*       */           }
/*       */           else
/*       */           {
/*  7793 */             setAsciiStreamContentsForClobCritical(paramInt, paramInputStream, paramLong, paramBoolean);
/*       */ 
/*       */           }
/*       */           
/*       */ 
/*       */         }
/*  7799 */         else if ((paramLong <= this.maxVcsCharsSql) && (!this.connection.retainV9BindBehavior))
/*       */         {
/*  7801 */           setAsciiStreamContentsForStringInternal(paramInt, paramInputStream, (int)paramLong);
/*       */ 
/*       */         }
/*  7804 */         else if (paramLong > 2147483647L)
/*       */         {
/*  7806 */           setAsciiStreamContentsForClobCritical(paramInt, paramInputStream, paramLong, paramBoolean);
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/*  7811 */           basicBindAsciiStream(paramInt, paramInputStream, (int)paramLong);
/*       */ 
/*       */         }
/*       */         
/*       */ 
/*       */       }
/*  7817 */       else if (this.sqlKind.isPlsqlOrCall())
/*       */       {
/*  7819 */         if ((paramLong <= this.maxVcsNCharsPlsql) && (!this.connection.retainV9BindBehavior))
/*       */         {
/*  7821 */           setAsciiStreamContentsForStringInternal(paramInt, paramInputStream, (int)paramLong);
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/*  7826 */           setAsciiStreamContentsForClobCritical(paramInt, paramInputStream, paramLong, paramBoolean);
/*       */ 
/*       */         }
/*       */         
/*       */ 
/*       */       }
/*  7832 */       else if ((paramLong <= this.maxVcsNCharsSql) && (!this.connection.retainV9BindBehavior))
/*       */       {
/*  7834 */         setAsciiStreamContentsForStringInternal(paramInt, paramInputStream, (int)paramLong);
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  7839 */         setAsciiStreamContentsForClobCritical(paramInt, paramInputStream, paramLong, paramBoolean);
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void basicBindAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  7851 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  7854 */       if (this.userRsetType != 1)
/*       */       {
/*  7856 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 169);
/*  7857 */         localSQLException.fillInStackTrace();
/*  7858 */         throw localSQLException;
/*       */       }
/*  7860 */       int i = paramInt1 - 1;
/*  7861 */       this.currentRowBinders[i] = this.theLongStreamBinder;
/*       */       
/*  7863 */       if (this.parameterStream == null) {
/*  7864 */         this.parameterStream = new InputStream[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */       }
/*       */       
/*  7867 */       this.parameterStream[this.currentRank][i] = this.connection.conversion.ConvertStream(paramInputStream, 5, paramInt2);
/*       */       
/*       */ 
/*       */ 
/*  7871 */       this.currentRowCharLens[i] = 0;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void setAsciiStreamContentsForStringInternal(int paramInt1, InputStream paramInputStream, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  7881 */     byte[] arrayOfByte = new byte[paramInt2];
/*  7882 */     int i = 0;
/*  7883 */     int j = paramInt2;
/*       */     
/*       */     try
/*       */     {
/*       */       int k;
/*       */       
/*  7889 */       while ((j > 0) && ((k = paramInputStream.read(arrayOfByte, i, j)) != -1)) {
/*  7890 */         i += k;
/*  7891 */         j -= k;
/*       */       }
/*       */       
/*       */     }
/*       */     catch (IOException localIOException)
/*       */     {
/*  7897 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  7898 */       localSQLException.fillInStackTrace();
/*  7899 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  7904 */     char[] arrayOfChar = new char[paramInt2];
/*  7905 */     DBConversion.asciiBytesToJavaChars(arrayOfByte, i, arrayOfChar);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  7910 */     basicBindString(paramInt1, new String(arrayOfChar));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  7937 */     setBinaryStreamInternal(paramInt1, paramInputStream, paramInt2);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setBinaryStreamInternal(int paramInt1, InputStream paramInputStream, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  7945 */     setBinaryStreamInternal(paramInt1, paramInputStream, paramInt2, true);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void checkUserStreamForDuplicates(Object paramObject, int paramInt)
/*       */     throws SQLException
/*       */   {
/*  7953 */     if (paramObject == null)
/*       */     {
/*  7955 */       return;
/*       */     }
/*  7957 */     if (this.userStream != null)
/*       */     {
/*  7959 */       for (Object[] arrayOfObject1 : this.userStream)
/*       */       {
/*  7961 */         for (Object localObject : arrayOfObject1)
/*       */         {
/*  7963 */           if (localObject == paramObject)
/*       */           {
/*       */ 
/*  7966 */             SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 270, Integer.valueOf(paramInt + 1));
/*  7967 */             localSQLException.fillInStackTrace();
/*  7968 */             throw localSQLException;
/*       */           }
/*       */           
/*       */         }
/*       */         
/*       */       }
/*       */       
/*       */     } else {
/*  7976 */       this.userStream = new Object[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*  7978 */     this.userStream[this.currentRank][paramInt] = paramObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setBinaryStreamInternal(int paramInt, InputStream paramInputStream, long paramLong, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  7986 */     synchronized (this.connection)
/*       */     {
/*  7988 */       int i = paramInt - 1;
/*       */       SQLException localSQLException;
/*  7990 */       if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */       {
/*  7992 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  7993 */         localSQLException.fillInStackTrace();
/*  7994 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  7999 */       set_execute_batch(1);
/*       */       
/*  8001 */       checkUserStreamForDuplicates(paramInputStream, i);
/*       */       
/*  8003 */       if (paramInputStream == null) {
/*  8004 */         setRAWInternal(paramInt, null);
/*  8005 */       } else { if ((this.userRsetType != 1) && ((paramLong > this.maxRawBytesSql) || (!paramBoolean)))
/*       */         {
/*       */ 
/*  8008 */           localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 169);
/*  8009 */           localSQLException.fillInStackTrace();
/*  8010 */           throw localSQLException;
/*       */         }
/*  8012 */         if (!paramBoolean) {
/*  8013 */           setBinaryStreamContentsForBlobCritical(paramInt, paramInputStream, paramLong, paramBoolean);
/*       */         }
/*  8015 */         else if (this.sqlKind.isPlsqlOrCall())
/*       */         {
/*  8017 */           if (paramLong > this.maxRawBytesPlsql)
/*       */           {
/*  8019 */             setBinaryStreamContentsForBlobCritical(paramInt, paramInputStream, paramLong, paramBoolean);
/*       */ 
/*       */           }
/*       */           else
/*       */           {
/*  8024 */             setBinaryStreamContentsForByteArrayInternal(paramInt, paramInputStream, (int)paramLong);
/*       */ 
/*       */           }
/*       */           
/*       */ 
/*       */         }
/*  8030 */         else if (paramLong > 2147483647L)
/*       */         {
/*  8032 */           setBinaryStreamContentsForBlobCritical(paramInt, paramInputStream, paramLong, paramBoolean);
/*       */ 
/*       */         }
/*  8035 */         else if (paramLong > this.maxRawBytesSql)
/*       */         {
/*  8037 */           basicBindBinaryStream(paramInt, paramInputStream, (int)paramLong);
/*       */         }
/*       */         else
/*       */         {
/*  8041 */           setBinaryStreamContentsForByteArrayInternal(paramInt, paramInputStream, (int)paramLong);
/*       */         }
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setBinaryStreamContentsForByteArrayInternal(int paramInt1, InputStream paramInputStream, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  8055 */     Object localObject = new byte[paramInt2];
/*  8056 */     int i = 0;
/*  8057 */     int j = paramInt2;
/*       */     
/*       */ 
/*       */     try
/*       */     {
/*       */       int k;
/*       */       
/*  8064 */       while ((j > 0) && ((k = paramInputStream.read((byte[])localObject, i, j)) != -1))
/*       */       {
/*  8066 */         i += k;
/*  8067 */         j -= k;
/*       */       }
/*       */       
/*       */     }
/*       */     catch (IOException localIOException)
/*       */     {
/*  8073 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  8074 */       localSQLException.fillInStackTrace();
/*  8075 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*  8079 */     if (i != paramInt2)
/*       */     {
/*  8081 */       byte[] arrayOfByte = new byte[i];
/*       */       
/*  8083 */       System.arraycopy(localObject, 0, arrayOfByte, 0, i);
/*       */       
/*  8085 */       localObject = arrayOfByte;
/*       */     }
/*       */     
/*  8088 */     setBytesInternal(paramInt1, (byte[])localObject);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   /**
/*       */    * @deprecated
/*       */    */
/*       */   public void setUnicodeStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  8119 */     setUnicodeStreamInternal(paramInt1, paramInputStream, paramInt2);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setUnicodeStreamInternal(int paramInt1, InputStream paramInputStream, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  8127 */     synchronized (this.connection)
/*       */     {
/*  8129 */       int i = paramInt1 - 1;
/*       */       Object localObject1;
/*  8131 */       if ((i < 0) || (paramInt1 > this.numberOfBindPositions))
/*       */       {
/*  8133 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  8134 */         ((SQLException)localObject1).fillInStackTrace();
/*  8135 */         throw ((Throwable)localObject1);
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*  8141 */       set_execute_batch(1);
/*  8142 */       checkUserStreamForDuplicates(paramInputStream, i);
/*  8143 */       if (paramInputStream == null) {
/*  8144 */         setStringInternal(paramInt1, null);
/*  8145 */       } else { if ((this.userRsetType != 1) && (paramInt2 > this.maxVcsCharsSql))
/*       */         {
/*  8147 */           localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 169);
/*  8148 */           ((SQLException)localObject1).fillInStackTrace();
/*  8149 */           throw ((Throwable)localObject1);
/*       */         }
/*  8151 */         if ((this.sqlKind.isPlsqlOrCall()) || (paramInt2 <= this.maxVcsCharsSql))
/*       */         {
/*  8153 */           localObject1 = new byte[paramInt2];
/*  8154 */           int j = 0;
/*  8155 */           int k = paramInt2;
/*       */           
/*       */ 
/*       */           try
/*       */           {
/*       */             int m;
/*       */             
/*  8162 */             while ((k > 0) && ((m = paramInputStream.read((byte[])localObject1, j, k)) != -1))
/*       */             {
/*  8164 */               j += m;
/*  8165 */               k -= m;
/*       */             }
/*       */             
/*       */           }
/*       */           catch (IOException localIOException)
/*       */           {
/*  8171 */             SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  8172 */             localSQLException.fillInStackTrace();
/*  8173 */             throw localSQLException;
/*       */           }
/*       */           
/*       */ 
/*  8177 */           char[] arrayOfChar = new char[j >> 1];
/*       */           
/*  8179 */           DBConversion.ucs2BytesToJavaChars((byte[])localObject1, j, arrayOfChar);
/*       */           
/*       */ 
/*  8182 */           setStringInternal(paramInt1, new String(arrayOfChar));
/*       */         }
/*       */         else
/*       */         {
/*  8186 */           this.currentRowBinders[i] = this.theLongStreamBinder;
/*       */           
/*  8188 */           if (this.parameterStream == null) {
/*  8189 */             this.parameterStream = new InputStream[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */           }
/*       */           
/*  8192 */           this.parameterStream[this.currentRank][i] = this.connection.conversion.ConvertStream(paramInputStream, 4, paramInt2);
/*       */           
/*       */ 
/*       */ 
/*  8196 */           this.currentRowCharLens[i] = 0;
/*       */         }
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   /**
/*       */    * @deprecated
/*       */    */
/*       */   public void setCustomDatum(int paramInt, CustomDatum paramCustomDatum)
/*       */     throws SQLException
/*       */   {
/*  8216 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  8222 */       setObjectInternal(paramInt, this.connection.toDatum(paramCustomDatum));
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setCustomDatumInternal(int paramInt, CustomDatum paramCustomDatum)
/*       */     throws SQLException
/*       */   {
/*  8230 */     synchronized (this.connection)
/*       */     {
/*  8232 */       Datum localDatum = this.connection.toDatum(paramCustomDatum);
/*  8233 */       int i = sqlTypeForObject(localDatum);
/*       */       
/*  8235 */       setObjectCritical(paramInt, localDatum, i, 0);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setORAData(int paramInt, ORAData paramORAData)
/*       */     throws SQLException
/*       */   {
/*  8255 */     setORADataInternal(paramInt, paramORAData);
/*       */   }
/*       */   
/*       */   void setORADataInternal(int paramInt, ORAData paramORAData)
/*       */     throws SQLException
/*       */   {
/*  8261 */     synchronized (this.connection)
/*       */     {
/*  8263 */       Datum localDatum = paramORAData.toDatum(this.connection);
/*  8264 */       int i = sqlTypeForObject(localDatum);
/*       */       
/*  8266 */       setObjectCritical(paramInt, localDatum, i, 0);
/*       */       
/*  8268 */       if ((i == 2002) || (i == 2008) || (i == 2003))
/*       */       {
/*       */ 
/*       */ 
/*  8272 */         this.currentRowCharLens[(paramInt - 1)] = 0;
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */   void setOracleDataInternal(int paramInt, OracleData paramOracleData)
/*       */     throws SQLException
/*       */   {
/*  8280 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  8283 */       Object localObject1 = paramOracleData.toJDBCObject(this.connection);
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  8293 */       if ((localObject1 instanceof OracleProxy))
/*       */       {
/*  8295 */         final OracleProxy localOracleProxy = (OracleProxy)localObject1;
/*  8296 */         localObject1 = AccessController.doPrivileged(new PrivilegedAction()
/*       */         {
/*       */ 
/*       */           public Object run()
/*       */           {
/*  8301 */             return ProxyFactory.extractDelegate(localOracleProxy);
/*       */           }
/*       */         });
/*       */       }
/*       */       
/*       */ 
/*  8307 */       int i = sqlTypeForObject(localObject1);
/*       */       
/*  8309 */       setObjectCritical(paramInt, localObject1, i, 0);
/*       */       
/*  8311 */       if ((i == 2002) || (i == 2008) || (i == 2003))
/*       */       {
/*       */ 
/*       */ 
/*  8315 */         this.currentRowCharLens[(paramInt - 1)] = 0;
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setObject(int paramInt1, Object paramObject, int paramInt2, int paramInt3)
/*       */     throws SQLException
/*       */   {
/*  8345 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*  8348 */       setObjectInternal(paramInt1, paramObject, paramInt2, paramInt3);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setObjectInternal(int paramInt1, Object paramObject, int paramInt2, int paramInt3)
/*       */     throws SQLException
/*       */   {
/*  8360 */     if ((paramObject == null) && (paramInt2 != 2002) && (paramInt2 != 2008) && (paramInt2 != 2003) && (paramInt2 != 2007) && (paramInt2 != 2006) && (paramInt2 != 2009))
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  8373 */       setNullInternal(paramInt1, paramInt2);
/*       */ 
/*       */ 
/*       */     }
/*  8377 */     else if ((paramInt2 == 2002) || (paramInt2 == 2008) || (paramInt2 == 2003) || (paramInt2 == 2009))
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  8388 */       setObjectCritical(paramInt1, paramObject, paramInt2, paramInt3);
/*       */       
/*  8390 */       this.currentRowCharLens[(paramInt1 - 1)] = 0;
/*       */ 
/*       */     }
/*       */     else
/*       */     {
/*       */ 
/*  8396 */       setObjectCritical(paramInt1, paramObject, paramInt2, paramInt3);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setObjectCritical(int paramInt1, Object paramObject, int paramInt2, int paramInt3)
/*       */     throws SQLException
/*       */   {
/*       */     SQLException localSQLException;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  8421 */     switch (paramInt2)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     case -15: 
/*  8428 */       setFormOfUse(paramInt1, (short)2);
/*       */     
/*       */ 
/*       */     case 1: 
/*  8432 */       if ((paramObject instanceof CHAR)) {
/*  8433 */         setCHARInternal(paramInt1, (CHAR)paramObject);
/*  8434 */       } else if ((paramObject instanceof String)) {
/*  8435 */         setStringInternal(paramInt1, (String)paramObject);
/*  8436 */       } else if ((paramObject instanceof Boolean)) {
/*  8437 */         setStringInternal(paramInt1, "" + (((Boolean)paramObject).booleanValue() ? 1 : 0));
/*       */       }
/*  8439 */       else if ((paramObject instanceof Integer)) {
/*  8440 */         setStringInternal(paramInt1, "" + ((Integer)paramObject).intValue());
/*  8441 */       } else if ((paramObject instanceof Long)) {
/*  8442 */         setStringInternal(paramInt1, "" + ((Long)paramObject).longValue());
/*  8443 */       } else if ((paramObject instanceof Float)) {
/*  8444 */         setStringInternal(paramInt1, "" + ((Float)paramObject).floatValue());
/*  8445 */       } else if ((paramObject instanceof Double)) {
/*  8446 */         setStringInternal(paramInt1, "" + ((Double)paramObject).doubleValue());
/*  8447 */       } else if ((paramObject instanceof BigDecimal)) {
/*  8448 */         setStringInternal(paramInt1, ((BigDecimal)paramObject).toString());
/*  8449 */       } else if ((paramObject instanceof Date)) {
/*  8450 */         setStringInternal(paramInt1, "" + ((Date)paramObject).toString());
/*  8451 */       } else if ((paramObject instanceof Time)) {
/*  8452 */         setStringInternal(paramInt1, "" + ((Time)paramObject).toString());
/*  8453 */       } else if ((paramObject instanceof Timestamp)) {
/*  8454 */         setStringInternal(paramInt1, "" + ((Timestamp)paramObject).toString());
/*       */       }
/*       */       else {
/*  8457 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8459 */         localSQLException.fillInStackTrace();
/*  8460 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */       break;
/*       */     case -9: 
/*  8469 */       setFormOfUse(paramInt1, (short)2);
/*       */     
/*       */ 
/*       */     case 12: 
/*  8473 */       if ((paramObject instanceof String)) {
/*  8474 */         setStringInternal(paramInt1, (String)paramObject);
/*  8475 */       } else if ((paramObject instanceof Boolean)) {
/*  8476 */         setStringInternal(paramInt1, "" + (((Boolean)paramObject).booleanValue() ? 1 : 0));
/*       */       }
/*  8478 */       else if ((paramObject instanceof Integer)) {
/*  8479 */         setStringInternal(paramInt1, "" + ((Integer)paramObject).intValue());
/*  8480 */       } else if ((paramObject instanceof Long)) {
/*  8481 */         setStringInternal(paramInt1, "" + ((Long)paramObject).longValue());
/*  8482 */       } else if ((paramObject instanceof Float)) {
/*  8483 */         setStringInternal(paramInt1, "" + ((Float)paramObject).floatValue());
/*  8484 */       } else if ((paramObject instanceof Double)) {
/*  8485 */         setStringInternal(paramInt1, "" + ((Double)paramObject).doubleValue());
/*  8486 */       } else if ((paramObject instanceof BigDecimal)) {
/*  8487 */         setStringInternal(paramInt1, ((BigDecimal)paramObject).toString());
/*  8488 */       } else if ((paramObject instanceof Date)) {
/*  8489 */         setStringInternal(paramInt1, "" + ((Date)paramObject).toString());
/*  8490 */       } else if ((paramObject instanceof Time)) {
/*  8491 */         setStringInternal(paramInt1, "" + ((Time)paramObject).toString());
/*  8492 */       } else if ((paramObject instanceof Timestamp)) {
/*  8493 */         setStringInternal(paramInt1, "" + ((Timestamp)paramObject).toString());
/*       */       }
/*       */       else {
/*  8496 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8498 */         localSQLException.fillInStackTrace();
/*  8499 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */       break;
/*       */     case 999: 
/*  8505 */       setFixedCHARInternal(paramInt1, (String)paramObject);
/*       */       
/*  8507 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */     case -16: 
/*  8513 */       setFormOfUse(paramInt1, (short)2);
/*       */     
/*       */ 
/*       */     case -1: 
/*  8517 */       if ((paramObject instanceof String)) {
/*  8518 */         setStringInternal(paramInt1, (String)paramObject);
/*  8519 */       } else if ((paramObject instanceof Boolean)) {
/*  8520 */         setStringInternal(paramInt1, "" + (((Boolean)paramObject).booleanValue() ? 1 : 0));
/*       */ 
/*       */       }
/*  8523 */       else if ((paramObject instanceof Integer)) {
/*  8524 */         setStringInternal(paramInt1, "" + ((Integer)paramObject).intValue());
/*       */       }
/*  8526 */       else if ((paramObject instanceof Long)) {
/*  8527 */         setStringInternal(paramInt1, "" + ((Long)paramObject).longValue());
/*       */       }
/*  8529 */       else if ((paramObject instanceof Float)) {
/*  8530 */         setStringInternal(paramInt1, "" + ((Float)paramObject).floatValue());
/*       */       }
/*  8532 */       else if ((paramObject instanceof Double)) {
/*  8533 */         setStringInternal(paramInt1, "" + ((Double)paramObject).doubleValue());
/*       */       }
/*  8535 */       else if ((paramObject instanceof BigDecimal)) {
/*  8536 */         setStringInternal(paramInt1, ((BigDecimal)paramObject).toString());
/*  8537 */       } else if ((paramObject instanceof Date)) {
/*  8538 */         setStringInternal(paramInt1, "" + ((Date)paramObject).toString());
/*  8539 */       } else if ((paramObject instanceof Time)) {
/*  8540 */         setStringInternal(paramInt1, "" + ((Time)paramObject).toString());
/*  8541 */       } else if ((paramObject instanceof Timestamp)) {
/*  8542 */         setStringInternal(paramInt1, "" + ((Timestamp)paramObject).toString());
/*       */       }
/*       */       else
/*       */       {
/*  8546 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8548 */         localSQLException.fillInStackTrace();
/*  8549 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*       */       break;
/*       */     case 2: 
/*  8556 */       if ((paramObject instanceof NUMBER)) {
/*  8557 */         setNUMBERInternal(paramInt1, (NUMBER)paramObject);
/*  8558 */       } else if ((paramObject instanceof Integer)) {
/*  8559 */         setIntInternal(paramInt1, ((Integer)paramObject).intValue());
/*  8560 */       } else if ((paramObject instanceof Long)) {
/*  8561 */         setLongInternal(paramInt1, ((Long)paramObject).longValue());
/*  8562 */       } else if ((paramObject instanceof Float)) {
/*  8563 */         setFloatInternal(paramInt1, ((Float)paramObject).floatValue());
/*  8564 */       } else if ((paramObject instanceof Double)) {
/*  8565 */         setDoubleInternal(paramInt1, ((Double)paramObject).doubleValue());
/*  8566 */       } else if ((paramObject instanceof BigDecimal)) {
/*  8567 */         setBigDecimalInternal(paramInt1, (BigDecimal)paramObject);
/*  8568 */       } else if ((paramObject instanceof BigInteger)) {
/*  8569 */         setBigDecimalInternal(paramInt1, new BigDecimal((BigInteger)paramObject));
/*  8570 */       } else if ((paramObject instanceof String)) {
/*  8571 */         setNUMBERInternal(paramInt1, new NUMBER((String)paramObject, paramInt3));
/*  8572 */       } else if ((paramObject instanceof Boolean)) {
/*  8573 */         setIntInternal(paramInt1, ((Boolean)paramObject).booleanValue() ? 1 : 0);
/*  8574 */       } else if ((paramObject instanceof Short)) {
/*  8575 */         setShortInternal(paramInt1, ((Short)paramObject).shortValue());
/*  8576 */       } else if ((paramObject instanceof Byte)) {
/*  8577 */         setByteInternal(paramInt1, ((Byte)paramObject).byteValue());
/*       */       }
/*       */       else {
/*  8580 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8582 */         localSQLException.fillInStackTrace();
/*  8583 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */       break;
/*       */     case 3: 
/*  8589 */       if ((paramObject instanceof BigDecimal)) {
/*  8590 */         setBigDecimalInternal(paramInt1, (BigDecimal)paramObject);
/*  8591 */       } else if ((paramObject instanceof Number)) {
/*  8592 */         setBigDecimalInternal(paramInt1, new BigDecimal(((Number)paramObject).doubleValue()));
/*       */       }
/*  8594 */       else if ((paramObject instanceof NUMBER)) {
/*  8595 */         setBigDecimalInternal(paramInt1, ((NUMBER)paramObject).bigDecimalValue());
/*  8596 */       } else if ((paramObject instanceof String)) {
/*  8597 */         setBigDecimalInternal(paramInt1, new BigDecimal((String)paramObject));
/*  8598 */       } else if ((paramObject instanceof Boolean)) {
/*  8599 */         setBigDecimalInternal(paramInt1, new BigDecimal(((Boolean)paramObject).booleanValue() ? 1.0D : 0.0D));
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  8604 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8606 */         localSQLException.fillInStackTrace();
/*  8607 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */       break;
/*       */     case -7: 
/*  8613 */       if ((paramObject instanceof Boolean)) {
/*  8614 */         setByteInternal(paramInt1, (byte)(((Boolean)paramObject).booleanValue() ? 1 : 0));
/*       */       }
/*  8616 */       else if ((paramObject instanceof String)) {
/*  8617 */         setByteInternal(paramInt1, (byte)(("true".equalsIgnoreCase((String)paramObject)) || ("1".equals(paramObject)) ? 1 : 0));
/*       */ 
/*       */ 
/*       */       }
/*  8621 */       else if ((paramObject instanceof Number)) {
/*  8622 */         setIntInternal(paramInt1, ((Number)paramObject).byteValue() != 0 ? 1 : 0);
/*       */       }
/*       */       else {
/*  8625 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8627 */         localSQLException.fillInStackTrace();
/*  8628 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */       break;
/*       */     case -6: 
/*  8636 */       if ((paramObject instanceof Number)) {
/*  8637 */         setByteInternal(paramInt1, ((Number)paramObject).byteValue());
/*  8638 */       } else if ((paramObject instanceof String)) {
/*  8639 */         setByteInternal(paramInt1, Byte.parseByte((String)paramObject));
/*  8640 */       } else if ((paramObject instanceof Boolean)) {
/*  8641 */         setByteInternal(paramInt1, (byte)(((Boolean)paramObject).booleanValue() ? 1 : 0));
/*       */       }
/*       */       else
/*       */       {
/*  8645 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8647 */         localSQLException.fillInStackTrace();
/*  8648 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */       break;
/*       */     case 5: 
/*  8656 */       if ((paramObject instanceof Number)) {
/*  8657 */         setShortInternal(paramInt1, ((Number)paramObject).shortValue());
/*  8658 */       } else if ((paramObject instanceof String)) {
/*  8659 */         setShortInternal(paramInt1, Short.parseShort((String)paramObject));
/*  8660 */       } else if ((paramObject instanceof Boolean)) {
/*  8661 */         setShortInternal(paramInt1, (short)(((Boolean)paramObject).booleanValue() ? 1 : 0));
/*       */       }
/*       */       else
/*       */       {
/*  8665 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8667 */         localSQLException.fillInStackTrace();
/*  8668 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */       break;
/*       */     case 4: 
/*  8674 */       if ((paramObject instanceof Number)) {
/*  8675 */         setIntInternal(paramInt1, ((Number)paramObject).intValue());
/*  8676 */       } else if ((paramObject instanceof String)) {
/*  8677 */         setIntInternal(paramInt1, Integer.parseInt((String)paramObject));
/*  8678 */       } else if ((paramObject instanceof Boolean)) {
/*  8679 */         setIntInternal(paramInt1, ((Boolean)paramObject).booleanValue() ? 1 : 0);
/*       */       }
/*       */       else {
/*  8682 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8684 */         localSQLException.fillInStackTrace();
/*  8685 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */       break;
/*       */     case -5: 
/*  8691 */       if ((paramObject instanceof Number)) {
/*  8692 */         setLongInternal(paramInt1, ((Number)paramObject).longValue());
/*  8693 */       } else if ((paramObject instanceof String)) {
/*  8694 */         setLongInternal(paramInt1, Long.parseLong((String)paramObject));
/*  8695 */       } else if ((paramObject instanceof Boolean)) {
/*  8696 */         setLongInternal(paramInt1, ((Boolean)paramObject).booleanValue() ? 1L : 0L);
/*       */       }
/*       */       else {
/*  8699 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8701 */         localSQLException.fillInStackTrace();
/*  8702 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */       break;
/*       */     case 7: 
/*  8708 */       if ((paramObject instanceof Number)) {
/*  8709 */         setFloatInternal(paramInt1, ((Number)paramObject).floatValue());
/*  8710 */       } else if ((paramObject instanceof String)) {
/*  8711 */         setFloatInternal(paramInt1, Float.valueOf((String)paramObject).floatValue());
/*  8712 */       } else if ((paramObject instanceof Boolean)) {
/*  8713 */         setFloatInternal(paramInt1, ((Boolean)paramObject).booleanValue() ? 1.0F : 0.0F);
/*       */       }
/*       */       else {
/*  8716 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8718 */         localSQLException.fillInStackTrace();
/*  8719 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*       */       break;
/*       */     case 6: 
/*       */     case 8: 
/*  8727 */       if ((paramObject instanceof Number)) {
/*  8728 */         setDoubleInternal(paramInt1, ((Number)paramObject).doubleValue());
/*  8729 */       } else if ((paramObject instanceof String)) {
/*  8730 */         setDoubleInternal(paramInt1, Double.valueOf((String)paramObject).doubleValue());
/*       */       }
/*  8732 */       else if ((paramObject instanceof Boolean)) {
/*  8733 */         setDoubleInternal(paramInt1, ((Boolean)paramObject).booleanValue() ? 1.0D : 0.0D);
/*       */       }
/*       */       else
/*       */       {
/*  8737 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8739 */         localSQLException.fillInStackTrace();
/*  8740 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */       break;
/*       */     case -2: 
/*  8746 */       if ((paramObject instanceof RAW)) {
/*  8747 */         setRAWInternal(paramInt1, (RAW)paramObject);
/*       */       } else {
/*  8749 */         setBytesInternal(paramInt1, (byte[])paramObject);
/*       */       }
/*  8751 */       break;
/*       */     
/*       */     case -3: 
/*  8754 */       setBytesInternal(paramInt1, (byte[])paramObject);
/*       */       
/*  8756 */       break;
/*       */     
/*       */     case -4: 
/*  8759 */       setBytesInternal(paramInt1, (byte[])paramObject);
/*       */       
/*  8761 */       break;
/*       */     
/*       */     case 91: 
/*  8764 */       if ((paramObject instanceof DATE)) {
/*  8765 */         setDATEInternal(paramInt1, (DATE)paramObject);
/*  8766 */       } else if ((paramObject instanceof Date)) {
/*  8767 */         setDATEInternal(paramInt1, new DATE(paramObject, getDefaultCalendar()));
/*  8768 */       } else if ((paramObject instanceof Timestamp)) {
/*  8769 */         setDATEInternal(paramInt1, new DATE((Timestamp)paramObject));
/*  8770 */       } else if ((paramObject instanceof String)) {
/*  8771 */         setDateInternal(paramInt1, Date.valueOf((String)paramObject));
/*       */       }
/*       */       else {
/*  8774 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8776 */         localSQLException.fillInStackTrace();
/*  8777 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */       break;
/*       */     case 92: 
/*  8783 */       if ((paramObject instanceof Time)) {
/*  8784 */         setTimeInternal(paramInt1, (Time)paramObject);
/*  8785 */       } else if ((paramObject instanceof Timestamp)) {
/*  8786 */         setTimeInternal(paramInt1, new Time(((Timestamp)paramObject).getTime()));
/*       */       }
/*  8788 */       else if ((paramObject instanceof Date)) {
/*  8789 */         setTimeInternal(paramInt1, new Time(((Date)paramObject).getTime()));
/*  8790 */       } else if ((paramObject instanceof String)) {
/*  8791 */         setTimeInternal(paramInt1, Time.valueOf((String)paramObject));
/*       */       }
/*       */       else {
/*  8794 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8796 */         localSQLException.fillInStackTrace();
/*  8797 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */       break;
/*       */     case 93: 
/*  8803 */       if ((paramObject instanceof TIMESTAMP)) {
/*  8804 */         setTIMESTAMPInternal(paramInt1, (TIMESTAMP)paramObject);
/*  8805 */       } else if ((paramObject instanceof Timestamp)) {
/*  8806 */         setTimestampInternal(paramInt1, (Timestamp)paramObject);
/*  8807 */       } else if ((paramObject instanceof Date)) {
/*  8808 */         setTIMESTAMPInternal(paramInt1, new TIMESTAMP((Date)paramObject));
/*  8809 */       } else if ((paramObject instanceof DATE)) {
/*  8810 */         setTIMESTAMPInternal(paramInt1, new TIMESTAMP(((DATE)paramObject).timestampValue()));
/*  8811 */       } else if ((paramObject instanceof String)) {
/*  8812 */         setTimestampInternal(paramInt1, Timestamp.valueOf((String)paramObject));
/*       */       }
/*       */       else {
/*  8815 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/*       */         
/*  8817 */         localSQLException.fillInStackTrace();
/*  8818 */         throw localSQLException;
/*       */       }
/*       */       
/*       */ 
/*       */       break;
/*       */     case -100: 
/*  8824 */       setTIMESTAMPInternal(paramInt1, (TIMESTAMP)paramObject);
/*       */       
/*  8826 */       break;
/*       */     
/*       */     case -101: 
/*  8829 */       setTIMESTAMPTZInternal(paramInt1, (TIMESTAMPTZ)paramObject);
/*       */       
/*  8831 */       break;
/*       */     
/*       */     case -102: 
/*  8834 */       setTIMESTAMPLTZInternal(paramInt1, (TIMESTAMPLTZ)paramObject);
/*       */       
/*  8836 */       break;
/*       */     
/*       */     case -103: 
/*  8839 */       setINTERVALYMInternal(paramInt1, (INTERVALYM)paramObject);
/*       */       
/*  8841 */       break;
/*       */     
/*       */     case -104: 
/*  8844 */       setINTERVALDSInternal(paramInt1, (INTERVALDS)paramObject);
/*       */       
/*  8846 */       break;
/*       */     
/*       */     case -8: 
/*  8849 */       setROWIDInternal(paramInt1, (ROWID)paramObject);
/*       */       
/*  8851 */       break;
/*       */     
/*       */     case 100: 
/*  8854 */       setBinaryFloatInternal(paramInt1, (BINARY_FLOAT)paramObject);
/*       */       
/*  8856 */       break;
/*       */     
/*       */     case 101: 
/*  8859 */       setBinaryDoubleInternal(paramInt1, (BINARY_DOUBLE)paramObject);
/*       */       
/*  8861 */       break;
/*       */     
/*       */     case 2004: 
/*  8864 */       setBLOBInternal(paramInt1, (BLOB)paramObject);
/*       */       
/*  8866 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     case 2005: 
/*       */     case 2011: 
/*  8874 */       setCLOBInternal(paramInt1, (CLOB)paramObject);
/*  8875 */       if (((CLOB)paramObject).isNCLOB())
/*       */       {
/*  8877 */         setFormOfUse(paramInt1, (short)2);
/*       */       }
/*       */       
/*       */       break;
/*       */     case -13: 
/*  8882 */       setBFILEInternal(paramInt1, (BFILE)paramObject);
/*       */       
/*  8884 */       break;
/*       */     
/*       */ 
/*       */     case 2002: 
/*       */     case 2008: 
/*  8889 */       setSTRUCTInternal(paramInt1, STRUCT.toSTRUCT(paramObject, this.connection));
/*       */       
/*  8891 */       break;
/*       */     
/*       */     case 2003: 
/*  8894 */       setARRAYInternal(paramInt1, ARRAY.toARRAY(paramObject, this.connection));
/*       */       
/*       */ 
/*  8897 */       break;
/*       */     
/*       */     case 2007: 
/*  8900 */       setOPAQUEInternal(paramInt1, (OPAQUE)paramObject);
/*       */       
/*  8902 */       break;
/*       */     
/*       */     case 2006: 
/*  8905 */       setREFInternal(paramInt1, (REF)paramObject);
/*       */       
/*  8907 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */     case 2009: 
/*  8913 */       setSQLXMLInternal(paramInt1, (SQLXML)paramObject);
/*       */       
/*  8915 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */     default: 
/*  8920 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/*  8921 */       localSQLException.fillInStackTrace();
/*  8922 */       throw localSQLException;
/*       */     }
/*       */     
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setObjectAtName(String paramString, Object paramObject, int paramInt1, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  8943 */     String str = paramString.intern();
/*  8944 */     String[] arrayOfString = this.sqlObject.getParameterList();
/*  8945 */     int i = 0;
/*  8946 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/*  8948 */     for (int k = 0; k < j; k++) {
/*  8949 */       if (arrayOfString[k] == str)
/*       */       {
/*  8951 */         setObjectInternal(k + 1, paramObject);
/*       */         
/*  8953 */         i = 1;
/*       */       }
/*       */     }
/*  8956 */     if (i == 0)
/*       */     {
/*  8958 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/*  8959 */       localSQLException.fillInStackTrace();
/*  8960 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setObject(int paramInt1, Object paramObject, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  8980 */     setObjectInternal(paramInt1, paramObject, paramInt2, 0);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void setObjectInternal(int paramInt1, Object paramObject, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  8989 */     setObjectInternal(paramInt1, paramObject, paramInt2, 0);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setRefType(int paramInt, REF paramREF)
/*       */     throws SQLException
/*       */   {
/*  9005 */     setREFInternal(paramInt, paramREF);
/*       */   }
/*       */   
/*       */ 
/*       */   void setRefTypeInternal(int paramInt, REF paramREF)
/*       */     throws SQLException
/*       */   {
/*  9012 */     setREFInternal(paramInt, paramREF);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setRef(int paramInt, Ref paramRef)
/*       */     throws SQLException
/*       */   {
/*  9029 */     setREFInternal(paramInt, (REF)paramRef);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setRefInternal(int paramInt, Ref paramRef)
/*       */     throws SQLException
/*       */   {
/*  9037 */     setREFInternal(paramInt, (REF)paramRef);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setREF(int paramInt, REF paramREF)
/*       */     throws SQLException
/*       */   {
/*  9054 */     setREFInternal(paramInt, paramREF);
/*       */   }
/*       */   
/*       */ 
/*       */   void setREFInternal(int paramInt, REF paramREF)
/*       */     throws SQLException
/*       */   {
/*  9061 */     int i = paramInt - 1;
/*       */     SQLException localSQLException;
/*  9063 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  9065 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  9066 */       localSQLException.fillInStackTrace();
/*  9067 */       throw localSQLException;
/*       */     }
/*       */     
/*  9070 */     if (paramREF == null)
/*       */     {
/*       */ 
/*  9073 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  9074 */       localSQLException.fillInStackTrace();
/*  9075 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  9081 */     setREFCritical(i, paramREF);
/*       */     
/*  9083 */     this.currentRowCharLens[i] = 0;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setREFCritical(int paramInt, REF paramREF)
/*       */     throws SQLException
/*       */   {
/*  9100 */     StructDescriptor localStructDescriptor = paramREF.getDescriptor();
/*       */     
/*       */ 
/*  9103 */     if (localStructDescriptor == null)
/*       */     {
/*  9105 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 52);
/*  9106 */       ((SQLException)localObject).fillInStackTrace();
/*  9107 */       throw ((Throwable)localObject);
/*       */     }
/*       */     
/*       */ 
/*  9111 */     this.currentRowBinders[paramInt] = this.theRefTypeBinder;
/*       */     
/*  9113 */     if (this.parameterDatum == null)
/*       */     {
/*  9115 */       this.parameterDatum = new byte[this.numberOfBindRowsAllocated][this.numberOfBindPositions][];
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  9120 */     this.parameterDatum[this.currentRank][paramInt] = paramREF.getBytes();
/*       */     
/*  9122 */     Object localObject = localStructDescriptor.getOracleTypeADT();
/*       */     
/*  9124 */     ((OracleTypeADT)localObject).getTOID();
/*       */     
/*  9126 */     if (this.parameterOtype == null)
/*       */     {
/*  9128 */       this.parameterOtype = new OracleTypeADT[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*       */ 
/*  9132 */     this.parameterOtype[this.currentRank][paramInt] = localObject;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setObject(int paramInt, Object paramObject)
/*       */     throws SQLException
/*       */   {
/*  9153 */     setObjectInternal(paramInt, paramObject);
/*       */   }
/*       */   
/*       */ 
/*       */   void setObjectInternal(int paramInt, Object paramObject)
/*       */     throws SQLException
/*       */   {
/*  9160 */     if ((paramObject instanceof ORAData))
/*       */     {
/*  9162 */       setORADataInternal(paramInt, (ORAData)paramObject);
/*       */     }
/*  9164 */     else if ((paramObject instanceof CustomDatum))
/*       */     {
/*  9166 */       setCustomDatumInternal(paramInt, (CustomDatum)paramObject);
/*       */     }
/*  9168 */     else if ((paramObject instanceof OracleData))
/*       */     {
/*  9170 */       setOracleDataInternal(paramInt, (OracleData)paramObject);
/*       */     }
/*       */     else
/*       */     {
/*  9174 */       int i = sqlTypeForObject(paramObject);
/*       */       
/*  9176 */       setObjectInternal(paramInt, paramObject, i, 0);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setOracleObject(int paramInt, Datum paramDatum)
/*       */     throws SQLException
/*       */   {
/*  9194 */     setObjectInternal(paramInt, paramDatum);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setOracleObjectInternal(int paramInt, Datum paramDatum)
/*       */     throws SQLException
/*       */   {
/*  9202 */     setObjectInternal(paramInt, paramDatum);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setPlsqlIndexTable(int paramInt1, Object paramObject, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*       */     throws SQLException
/*       */   {
/*  9229 */     synchronized (this.connection)
/*       */     {
/*  9231 */       setPlsqlIndexTableInternal(paramInt1, paramObject, paramInt2, paramInt3, paramInt4, paramInt5);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setPlsqlIndexTableInternal(int paramInt1, Object paramObject, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*       */     throws SQLException
/*       */   {
/*  9245 */     int i = paramInt1 - 1;
/*       */     SQLException localSQLException;
/*  9247 */     if ((i < 0) || (paramInt1 > this.numberOfBindPositions))
/*       */     {
/*  9249 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  9250 */       localSQLException.fillInStackTrace();
/*  9251 */       throw localSQLException;
/*       */     }
/*       */     
/*  9254 */     if (paramObject == null)
/*       */     {
/*  9256 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 271);
/*  9257 */       localSQLException.fillInStackTrace();
/*  9258 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*  9263 */     int j = getInternalType(paramInt4);
/*       */     
/*  9265 */     Object localObject1 = null;
/*       */     
/*       */     Object localObject2;
/*  9268 */     switch (j)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */     case 1: 
/*       */     case 96: 
/*  9275 */       localObject2 = null;
/*  9276 */       int k = 0;
/*       */       
/*       */       Object localObject3;
/*  9279 */       if ((paramObject instanceof CHAR[]))
/*       */       {
/*  9281 */         localObject3 = (CHAR[])paramObject;
/*  9282 */         k = localObject3.length;
/*       */         
/*  9284 */         localObject2 = new String[k];
/*       */         
/*  9286 */         for (int n = 0; n < k; n++)
/*       */         {
/*  9288 */           Object localObject5 = localObject3[n];
/*  9289 */           if (localObject5 != null) {
/*  9290 */             localObject2[n] = ((CHAR)localObject5).getString();
/*       */           }
/*       */         }
/*  9293 */       } else if ((paramObject instanceof String[]))
/*       */       {
/*  9295 */         localObject2 = (String[])paramObject;
/*  9296 */         k = localObject2.length;
/*       */       }
/*       */       else {
/*  9299 */         localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 97);
/*  9300 */         ((SQLException)localObject3).fillInStackTrace();
/*  9301 */         throw ((Throwable)localObject3);
/*       */       }
/*       */       
/*       */ 
/*  9305 */       if ((paramInt5 == 0) && (localObject2 != null)) {
/*  9306 */         for (int m = 0; m < k; m++)
/*       */         {
/*  9308 */           Object localObject4 = localObject2[m];
/*  9309 */           if ((localObject4 != null) && (paramInt5 < ((String)localObject4).length()))
/*  9310 */             paramInt5 = ((String)localObject4).length();
/*       */         }
/*       */       }
/*  9313 */       localObject1 = localObject2;
/*       */       
/*  9315 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */     case 2: 
/*       */     case 6: 
/*  9322 */       localObject1 = OracleTypeNUMBER.toNUMBERArray(paramObject, this.connection, 1L, paramInt3);
/*       */       
/*       */ 
/*  9325 */       if ((paramInt5 == 0) && (localObject1 != null))
/*       */       {
/*  9327 */         paramInt5 = 22;
/*       */       }
/*       */       
/*  9330 */       this.currentRowCharLens[i] = 0;
/*       */       
/*  9332 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     default: 
/*  9351 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 97);
/*  9352 */       ((SQLException)localObject2).fillInStackTrace();
/*  9353 */       throw ((Throwable)localObject2);
/*       */     }
/*       */     
/*       */     
/*  9357 */     if ((localObject1.length == 0) && (paramInt2 == 0))
/*       */     {
/*  9359 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 272);
/*  9360 */       ((SQLException)localObject2).fillInStackTrace();
/*  9361 */       throw ((Throwable)localObject2);
/*       */     }
/*       */     
/*  9364 */     this.currentRowBinders[i] = this.thePlsqlIbtBinder;
/*       */     
/*  9366 */     if (this.parameterPlsqlIbt == null) {
/*  9367 */       this.parameterPlsqlIbt = new PlsqlIbtBindInfo[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */     }
/*       */     
/*  9370 */     this.parameterPlsqlIbt[this.currentRank][i] = new PlsqlIbtBindInfo((Object[])localObject1, paramInt2, paramInt3, j, paramInt5);
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  9377 */     this.hasIbtBind = true;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setPlsqlIndexTableAtName(String paramString, Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*       */     throws SQLException
/*       */   {
/*  9400 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*       */ 
/*  9404 */       String str = paramString.intern();
/*  9405 */       String[] arrayOfString = this.sqlObject.getParameterList();
/*  9406 */       int i = 0;
/*  9407 */       int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */       
/*  9409 */       for (int k = 0; k < j; k++) {
/*  9410 */         if (arrayOfString[k] == str)
/*       */         {
/*  9412 */           setPlsqlIndexTableInternal(k + 1, paramObject, paramInt1, paramInt2, paramInt3, paramInt4);
/*       */           
/*       */ 
/*  9415 */           i = 1;
/*       */         }
/*       */       }
/*  9418 */       if (i == 0)
/*       */       {
/*  9420 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/*  9421 */         localSQLException.fillInStackTrace();
/*  9422 */         throw localSQLException;
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void endOfResultSet(boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  9444 */     if (!paramBoolean)
/*       */     {
/*       */ 
/*       */ 
/*  9448 */       prepareForNewResults(false, false); }
/*  9449 */     this.rowPrefetchInLastFetch = -1;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int sqlTypeForObject(Object paramObject)
/*       */   {
/*  9461 */     if (paramObject == null)
/*       */     {
/*  9463 */       return 0;
/*       */     }
/*       */     
/*  9466 */     if (!(paramObject instanceof Datum))
/*       */     {
/*  9468 */       if ((paramObject instanceof String))
/*       */       {
/*       */ 
/*  9471 */         return this.fixedString ? 999 : 12;
/*       */       }
/*  9473 */       if ((paramObject instanceof BigDecimal)) {
/*  9474 */         return 2;
/*       */       }
/*  9476 */       if ((paramObject instanceof BigInteger)) {
/*  9477 */         return 2;
/*       */       }
/*  9479 */       if ((paramObject instanceof Boolean)) {
/*  9480 */         return -7;
/*       */       }
/*  9482 */       if ((paramObject instanceof Integer)) {
/*  9483 */         return 4;
/*       */       }
/*  9485 */       if ((paramObject instanceof Long)) {
/*  9486 */         return -5;
/*       */       }
/*  9488 */       if ((paramObject instanceof Float)) {
/*  9489 */         return 7;
/*       */       }
/*  9491 */       if ((paramObject instanceof Double)) {
/*  9492 */         return 8;
/*       */       }
/*  9494 */       if ((paramObject instanceof byte[])) {
/*  9495 */         return -3;
/*       */       }
/*       */       
/*       */ 
/*  9499 */       if ((paramObject instanceof Short)) {
/*  9500 */         return 5;
/*       */       }
/*  9502 */       if ((paramObject instanceof Byte)) {
/*  9503 */         return -6;
/*       */       }
/*  9505 */       if ((paramObject instanceof Date)) {
/*  9506 */         return 91;
/*       */       }
/*  9508 */       if ((paramObject instanceof Time)) {
/*  9509 */         return 92;
/*       */       }
/*  9511 */       if ((paramObject instanceof Timestamp)) {
/*  9512 */         return 93;
/*       */       }
/*  9514 */       if ((paramObject instanceof SQLData)) {
/*  9515 */         return 2002;
/*       */       }
/*  9517 */       if ((paramObject instanceof ObjectData)) {
/*  9518 */         return 2002;
/*       */       }
/*       */     }
/*       */     else {
/*  9522 */       if ((paramObject instanceof BINARY_FLOAT)) {
/*  9523 */         return 100;
/*       */       }
/*  9525 */       if ((paramObject instanceof BINARY_DOUBLE)) {
/*  9526 */         return 101;
/*       */       }
/*  9528 */       if ((paramObject instanceof BLOB)) {
/*  9529 */         return 2004;
/*       */       }
/*  9531 */       if ((paramObject instanceof CLOB)) {
/*  9532 */         return 2005;
/*       */       }
/*  9534 */       if ((paramObject instanceof BFILE)) {
/*  9535 */         return -13;
/*       */       }
/*  9537 */       if ((paramObject instanceof ROWID)) {
/*  9538 */         return -8;
/*       */       }
/*  9540 */       if ((paramObject instanceof NUMBER)) {
/*  9541 */         return 2;
/*       */       }
/*  9543 */       if ((paramObject instanceof DATE)) {
/*  9544 */         return 91;
/*       */       }
/*  9546 */       if ((paramObject instanceof TIMESTAMP)) {
/*  9547 */         return 93;
/*       */       }
/*  9549 */       if ((paramObject instanceof TIMESTAMPTZ)) {
/*  9550 */         return -101;
/*       */       }
/*  9552 */       if ((paramObject instanceof TIMESTAMPLTZ)) {
/*  9553 */         return -102;
/*       */       }
/*  9555 */       if ((paramObject instanceof REF)) {
/*  9556 */         return 2006;
/*       */       }
/*  9558 */       if ((paramObject instanceof CHAR)) {
/*  9559 */         return 1;
/*       */       }
/*  9561 */       if ((paramObject instanceof RAW)) {
/*  9562 */         return -2;
/*       */       }
/*  9564 */       if ((paramObject instanceof ARRAY)) {
/*  9565 */         return 2003;
/*       */       }
/*  9567 */       if ((paramObject instanceof STRUCT)) {
/*  9568 */         return 2002;
/*       */       }
/*  9570 */       if ((paramObject instanceof OPAQUE)) {
/*  9571 */         return 2007;
/*       */       }
/*  9573 */       if ((paramObject instanceof INTERVALYM)) {
/*  9574 */         return -103;
/*       */       }
/*  9576 */       if ((paramObject instanceof INTERVALDS)) {
/*  9577 */         return -104;
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*  9582 */       if ((paramObject instanceof SQLXML)) {
/*  9583 */         return 2009;
/*       */       }
/*       */     }
/*       */     
/*  9587 */     return 1111;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void clearParameters()
/*       */     throws SQLException
/*       */   {
/*  9600 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  9606 */       this.clearParameters = true;
/*       */       
/*  9608 */       for (int i = 0; i < this.numberOfBindPositions; i++) {
/*  9609 */         this.currentRowBinders[i] = null;
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void printByteArray(byte[] paramArrayOfByte)
/*       */   {
/*  9620 */     if (paramArrayOfByte != null)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  9627 */       int j = paramArrayOfByte.length;
/*       */       
/*  9629 */       for (int i = 0; i < j; i++)
/*       */       {
/*  9631 */         int k = paramArrayOfByte[i] & 0xFF;
/*       */         
/*  9633 */         if (k >= 16) {}
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCharacterStream(int paramInt1, Reader paramReader, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  9680 */     setCharacterStreamInternal(paramInt1, paramReader, paramInt2);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void setCharacterStreamInternal(int paramInt1, Reader paramReader, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  9689 */     setCharacterStreamInternal(paramInt1, paramReader, paramInt2, true);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setCharacterStreamInternal(int paramInt, Reader paramReader, long paramLong, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  9697 */     int i = paramInt - 1;
/*       */     SQLException localSQLException;
/*  9699 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/*  9701 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  9702 */       localSQLException.fillInStackTrace();
/*  9703 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*  9709 */     set_execute_batch(1);
/*  9710 */     checkUserStreamForDuplicates(paramReader, i);
/*  9711 */     if (paramReader == null)
/*       */     {
/*  9713 */       basicBindNullString(paramInt);
/*       */     } else {
/*  9715 */       if ((this.userRsetType != 1) && ((paramLong > this.maxVcsCharsSql) || (!paramBoolean)))
/*       */       {
/*       */ 
/*       */ 
/*  9719 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 169);
/*  9720 */         localSQLException.fillInStackTrace();
/*  9721 */         throw localSQLException;
/*       */       }
/*       */       
/*  9724 */       if (!paramBoolean)
/*       */       {
/*  9726 */         setReaderContentsForClobCritical(paramInt, paramReader, paramLong, paramBoolean);
/*       */ 
/*       */       }
/*  9729 */       else if (this.currentRowFormOfUse[i] == 1)
/*       */       {
/*  9731 */         if (this.sqlKind.isPlsqlOrCall())
/*       */         {
/*  9733 */           if ((paramLong > this.maxVcsBytesPlsql) || ((paramLong > this.maxVcsCharsPlsql) && (this.isServerCharSetFixedWidth)))
/*       */           {
/*       */ 
/*       */ 
/*  9737 */             setReaderContentsForClobCritical(paramInt, paramReader, paramLong, paramBoolean);
/*       */ 
/*       */           }
/*  9740 */           else if ((paramLong <= this.maxVcsCharsPlsql) && (!this.connection.retainV9BindBehavior))
/*       */           {
/*       */ 
/*  9743 */             setReaderContentsForStringInternal(paramInt, paramReader, (int)paramLong);
/*       */ 
/*       */ 
/*       */           }
/*       */           else
/*       */           {
/*       */ 
/*       */ 
/*  9751 */             setReaderContentsForStringOrClobInVariableWidthCase(paramInt, paramReader, (int)paramLong, false);
/*       */ 
/*       */ 
/*       */           }
/*       */           
/*       */ 
/*       */ 
/*       */         }
/*  9759 */         else if ((paramLong <= this.maxVcsCharsSql) && (!this.connection.retainV9BindBehavior))
/*       */         {
/*  9761 */           setReaderContentsForStringInternal(paramInt, paramReader, (int)paramLong);
/*       */ 
/*       */         }
/*  9764 */         else if (paramLong > 2147483647L)
/*       */         {
/*  9766 */           setReaderContentsForClobCritical(paramInt, paramReader, paramLong, paramBoolean);
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/*  9771 */           basicBindCharacterStream(paramInt, paramReader, (int)paramLong, false);
/*       */ 
/*       */         }
/*       */         
/*       */ 
/*       */       }
/*  9777 */       else if (this.sqlKind.isPlsqlOrCall())
/*       */       {
/*  9779 */         if ((paramLong > this.maxVcsBytesPlsql) || ((paramLong > this.maxVcsNCharsPlsql) && (this.isServerCharSetFixedWidth)))
/*       */         {
/*       */ 
/*       */ 
/*  9783 */           setReaderContentsForClobCritical(paramInt, paramReader, paramLong, paramBoolean);
/*       */ 
/*       */         }
/*  9786 */         else if ((paramLong <= this.maxVcsNCharsPlsql) && (!this.connection.retainV9BindBehavior))
/*       */         {
/*       */ 
/*  9789 */           setReaderContentsForStringInternal(paramInt, paramReader, (int)paramLong);
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/*  9794 */           setReaderContentsForStringOrClobInVariableWidthCase(paramInt, paramReader, (int)paramLong, true);
/*       */ 
/*       */ 
/*       */         }
/*       */         
/*       */ 
/*       */ 
/*       */       }
/*  9802 */       else if ((paramLong <= this.maxVcsNCharsSql) && (!this.connection.retainV9BindBehavior))
/*       */       {
/*  9804 */         setReaderContentsForStringInternal(paramInt, paramReader, (int)paramLong);
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  9809 */         setReaderContentsForClobCritical(paramInt, paramReader, paramLong, paramBoolean);
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void basicBindCharacterStream(int paramInt1, Reader paramReader, int paramInt2, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  9822 */     synchronized (this.connection)
/*       */     {
/*  9824 */       int i = paramInt1 - 1;
/*       */       
/*  9826 */       if (paramBoolean)
/*       */       {
/*  9828 */         this.currentRowBinders[i] = this.theLongStreamForStringBinder;
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*  9833 */         this.currentRowBinders[i] = this.theLongStreamBinder;
/*       */       }
/*       */       
/*  9836 */       if (this.parameterStream == null) {
/*  9837 */         this.parameterStream = new InputStream[this.numberOfBindRowsAllocated][this.numberOfBindPositions];
/*       */       }
/*       */       
/*  9840 */       this.parameterStream[this.currentRank][i] = (paramBoolean ? this.connection.conversion.ConvertStreamInternal(paramReader, 7, paramInt2, this.currentRowFormOfUse[i]) : this.connection.conversion.ConvertStream(paramReader, 7, paramInt2, this.currentRowFormOfUse[i]));
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*  9849 */       this.currentRowCharLens[i] = 0;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void setReaderContentsForStringOrClobInVariableWidthCase(int paramInt1, Reader paramReader, int paramInt2, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/*  9869 */     Object localObject = new char[paramInt2];
/*  9870 */     int i = 0;
/*  9871 */     int j = paramInt2;
/*       */     
/*       */ 
/*       */     try
/*       */     {
/*       */       int k;
/*       */       
/*  9878 */       while ((j > 0) && ((k = paramReader.read((char[])localObject, i, j)) != -1))
/*       */       {
/*  9880 */         i += k;
/*  9881 */         j -= k;
/*       */       }
/*       */       
/*       */     }
/*       */     catch (IOException localIOException)
/*       */     {
/*  9887 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  9888 */       localSQLException.fillInStackTrace();
/*  9889 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*  9893 */     if (i != paramInt2)
/*       */     {
/*  9895 */       char[] arrayOfChar = new char[i];
/*       */       
/*  9897 */       System.arraycopy(localObject, 0, arrayOfChar, 0, i);
/*       */       
/*  9899 */       localObject = arrayOfChar;
/*       */     }
/*  9901 */     int m = this.connection.conversion.encodedByteLength((char[])localObject, paramBoolean);
/*       */     
/*  9903 */     if ((m < this.maxVcsBytesPlsql) && (!this.connection.retainV9BindBehavior))
/*       */     {
/*       */ 
/*  9906 */       setStringInternal(paramInt1, new String((char[])localObject));
/*       */     }
/*       */     else
/*       */     {
/*  9910 */       setStringForClobCritical(paramInt1, new String((char[])localObject));
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void setReaderContentsForStringInternal(int paramInt1, Reader paramReader, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*  9920 */     Object localObject = new char[paramInt2];
/*  9921 */     int i = 0;
/*  9922 */     int j = paramInt2;
/*       */     
/*       */ 
/*       */     try
/*       */     {
/*       */       int k;
/*       */       
/*  9929 */       while ((j > 0) && ((k = paramReader.read((char[])localObject, i, j)) != -1))
/*       */       {
/*  9931 */         i += k;
/*  9932 */         j -= k;
/*       */       }
/*       */       
/*       */     }
/*       */     catch (IOException localIOException)
/*       */     {
/*  9938 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  9939 */       localSQLException.fillInStackTrace();
/*  9940 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/*  9944 */     if (i != paramInt2)
/*       */     {
/*  9946 */       char[] arrayOfChar = new char[i];
/*       */       
/*  9948 */       System.arraycopy(localObject, 0, arrayOfChar, 0, i);
/*       */       
/*  9950 */       localObject = arrayOfChar;
/*       */     }
/*  9952 */     setStringInternal(paramInt1, new String((char[])localObject));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setDate(int paramInt, Date paramDate, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9970 */     setDATEInternal(paramInt, paramDate == null ? null : new DATE(paramDate, paramCalendar));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setDateInternal(int paramInt, Date paramDate, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9978 */     setDATEInternal(paramInt, paramDate == null ? null : new DATE(paramDate, paramCalendar));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTime(int paramInt, Time paramTime, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/*  9995 */     setDATEInternal(paramInt, paramTime == null ? null : new DATE(paramTime, paramCalendar));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setTimeInternal(int paramInt, Time paramTime, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/* 10003 */     setDATEInternal(paramInt, paramTime == null ? null : new DATE(paramTime, paramCalendar));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTimestamp(int paramInt, Timestamp paramTimestamp, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/* 10020 */     setTimestampInternal(paramInt, paramTimestamp, paramCalendar);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setTimestampInternal(int paramInt, Timestamp paramTimestamp, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/* 10028 */     setTIMESTAMPInternal(paramInt, paramTimestamp == null ? null : new TIMESTAMP(paramTimestamp, paramCalendar));
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCheckBindTypes(boolean paramBoolean)
/*       */   {
/* 10045 */     this.checkBindTypes = paramBoolean;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/* 10074 */   int m_batchStyle = 0;
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   final void setOracleBatchStyle()
/*       */     throws SQLException
/*       */   {
/* 10085 */     if (this.m_batchStyle == 2)
/*       */     {
/*       */ 
/* 10088 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "operation cannot be mixed with JDBC-2.0-style batching");
/* 10089 */       localSQLException.fillInStackTrace();
/* 10090 */       throw localSQLException;
/*       */     }
/*       */     
/* 10093 */     if (this.m_batchStyle == 0) {}
/*       */     
/*       */ 
/*       */ 
/*       */ 
/* 10098 */     this.m_batchStyle = 1;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   boolean isOracleBatchStyle()
/*       */   {
/* 10105 */     return this.m_batchStyle == 1;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   final void setJdbcBatchStyle()
/*       */     throws SQLException
/*       */   {
/* 10118 */     if (this.m_batchStyle == 1)
/*       */     {
/*       */ 
/* 10121 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "operation cannot be mixed with Oracle-style batching");
/* 10122 */       localSQLException.fillInStackTrace();
/* 10123 */       throw localSQLException;
/*       */     }
/*       */     
/*       */ 
/* 10127 */     this.m_batchStyle = 2;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   final void checkIfJdbcBatchExists()
/*       */     throws SQLException
/*       */   {
/* 10143 */     if (doesJdbcBatchExist())
/*       */     {
/*       */ 
/* 10146 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 81, "batch must be either executed or cleared");
/* 10147 */       localSQLException.fillInStackTrace();
/* 10148 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   boolean doesJdbcBatchExist()
/*       */   {
/* 10157 */     if ((this.currentRank > 0) && (this.m_batchStyle == 2)) {
/* 10158 */       return true;
/*       */     }
/* 10160 */     return false;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   boolean isJdbcBatchStyle()
/*       */   {
/* 10167 */     return this.m_batchStyle == 2;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void addBatch()
/*       */     throws SQLException
/*       */   {
/* 10188 */     synchronized (this.connection)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/* 10203 */       setJdbcBatchStyle();
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/* 10213 */       processCompletedBindRow(this.currentRank + 2, (this.currentRank > 0) && (this.sqlKind.isPlsqlOrCall()));
/*       */       
/*       */ 
/*       */ 
/* 10217 */       this.currentRank += 1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public void addBatch(String paramString)
/*       */     throws SQLException
/*       */   {
/* 10227 */     synchronized (this.connection)
/*       */     {
/*       */ 
/* 10230 */       SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10231 */       localSQLException.fillInStackTrace();
/* 10232 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void clearBatch()
/*       */     throws SQLException
/*       */   {
/* 10251 */     synchronized (this.connection)
/*       */     {
/* 10253 */       for (int i = this.currentRank - 1; i >= 0; i--) {
/* 10254 */         for (int j = 0; j < this.numberOfBindPositions; j++)
/* 10255 */           this.binders[i][j] = null;
/*       */       }
/* 10257 */       this.currentRank = 0;
/*       */       
/* 10259 */       if (this.binders != null) {
/* 10260 */         this.currentRowBinders = this.binders[0];
/*       */       }
/* 10262 */       this.pushedBatches = null;
/* 10263 */       this.pushedBatchesTail = null;
/* 10264 */       this.firstRowInBatch = 0;
/*       */       
/* 10266 */       this.clearParameters = true;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void executeForRowsWithTimeout(boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/* 10279 */     if (this.queryTimeout > 0)
/*       */     {
/*       */       try
/*       */       {
/* 10283 */         this.connection.getTimeout().setTimeout(this.queryTimeout * 1000, this);
/* 10284 */         this.cancelLock.enterExecuting();
/* 10285 */         executeForRows(paramBoolean);
/*       */       }
/*       */       finally
/*       */       {
/* 10289 */         this.connection.getTimeout().cancelTimeout();
/* 10290 */         this.cancelLock.exitExecuting();
/*       */       }
/*       */       
/*       */     }
/*       */     else {
/*       */       try
/*       */       {
/* 10297 */         this.cancelLock.enterExecuting();
/* 10298 */         executeForRows(paramBoolean);
/*       */       }
/*       */       finally
/*       */       {
/* 10302 */         this.cancelLock.exitExecuting();
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int[] executeBatch()
/*       */     throws SQLException
/*       */   {
/* 10329 */     synchronized (this.connection)
/*       */     {
/*       */ 
/* 10332 */       int[] arrayOfInt = new int[this.currentRank];
/* 10333 */       this.checkSum = 0L;
/* 10334 */       this.checkSumComputationFailure = false;
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/* 10340 */       int i = 0;
/*       */       
/* 10342 */       cleanOldTempLobs();
/* 10343 */       setJdbcBatchStyle();
/*       */       
/* 10345 */       if (this.currentRank > 0)
/*       */       {
/*       */ 
/*       */ 
/* 10349 */         ensureOpen();
/*       */         
/*       */ 
/* 10352 */         prepareForNewResults(true, true);
/*       */         
/* 10354 */         if (this.sqlKind.isSELECT())
/*       */         {
/*       */ 
/* 10357 */           BatchUpdateException localBatchUpdateException1 = DatabaseError.createBatchUpdateException(80, 0, null);
/* 10358 */           localBatchUpdateException1.fillInStackTrace();
/* 10359 */           throw localBatchUpdateException1;
/*       */         }
/*       */         
/*       */ 
/*       */ 
/* 10364 */         this.noMoreUpdateCounts = false;
/*       */         
/* 10366 */         int j = 0;
/*       */         
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */         try
/*       */         {
/* 10378 */           this.connection.registerHeartbeat();
/*       */           
/* 10380 */           this.connection.needLine();
/*       */           
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/* 10386 */           if (!this.isOpen)
/*       */           {
/* 10388 */             this.connection.open(this);
/*       */             
/* 10390 */             this.isOpen = true;
/*       */           }
/*       */           
/*       */ 
/*       */ 
/*       */ 
/* 10396 */           int k = this.currentRank;
/*       */           
/* 10398 */           if (this.pushedBatches == null)
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/* 10403 */             setupBindBuffers(0, this.currentRank);
/* 10404 */             executeForRowsWithTimeout(false);
/*       */ 
/*       */ 
/*       */           }
/*       */           else
/*       */           {
/*       */ 
/*       */ 
/* 10412 */             if (this.currentRank > this.firstRowInBatch)
/*       */             {
/*       */ 
/*       */ 
/* 10416 */               pushBatch(true);
/*       */             }
/* 10418 */             boolean bool = this.needToParse;
/*       */             
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */             do
/*       */             {
/* 10426 */               localObject1 = this.pushedBatches;
/*       */               
/* 10428 */               this.currentBatchCharLens = ((PushedBatch)localObject1).currentBatchCharLens;
/* 10429 */               this.lastBoundCharLens = ((PushedBatch)localObject1).lastBoundCharLens;
/* 10430 */               this.lastBoundNeeded = ((PushedBatch)localObject1).lastBoundNeeded;
/* 10431 */               this.currentBatchBindAccessors = ((PushedBatch)localObject1).currentBatchBindAccessors;
/* 10432 */               this.needToParse = ((PushedBatch)localObject1).need_to_parse;
/* 10433 */               this.currentBatchNeedToPrepareBinds = ((PushedBatch)localObject1).current_batch_need_to_prepare_binds;
/*       */               
/* 10435 */               this.firstRowInBatch = ((PushedBatch)localObject1).first_row_in_batch;
/*       */               
/* 10437 */               setupBindBuffers(((PushedBatch)localObject1).first_row_in_batch, ((PushedBatch)localObject1).number_of_rows_to_be_bound);
/*       */               
/*       */ 
/*       */ 
/*       */ 
/* 10442 */               this.currentRank = ((PushedBatch)localObject1).number_of_rows_to_be_bound;
/*       */               
/* 10444 */               executeForRowsWithTimeout(false);
/*       */               
/* 10446 */               j += this.validRows;
/* 10447 */               if (this.sqlKind.isPlsqlOrCall())
/*       */               {
/* 10449 */                 arrayOfInt[(i++)] = this.validRows;
/*       */               }
/*       */               
/* 10452 */               this.pushedBatches = ((PushedBatch)localObject1).next;
/*       */ 
/*       */             }
/* 10455 */             while (this.pushedBatches != null);
/*       */             
/*       */ 
/* 10458 */             this.pushedBatchesTail = null;
/* 10459 */             this.firstRowInBatch = 0;
/*       */             
/* 10461 */             this.needToParse = bool;
/*       */           }
/*       */           
/*       */ 
/*       */ 
/* 10466 */           slideDownCurrentRow(k);
/*       */ 
/*       */ 
/*       */         }
/*       */         catch (SQLException localSQLException)
/*       */         {
/*       */ 
/*       */ 
/* 10474 */           int m = this.currentRank;
/* 10475 */           clearBatch();
/* 10476 */           this.needToParse = true;
/*       */           
/* 10478 */           if (!this.sqlKind.isPlsqlOrCall())
/*       */           {
/*       */ 
/*       */ 
/*       */ 
/* 10483 */             if ((this.numberOfExecutedElementsInBatch != -1) && (this.numberOfExecutedElementsInBatch != m))
/*       */             {
/*       */ 
/*       */ 
/*       */ 
/* 10488 */               arrayOfInt = new int[this.numberOfExecutedElementsInBatch];
/* 10489 */               for (i = 0; i < this.numberOfExecutedElementsInBatch;) {
/* 10490 */                 arrayOfInt[i] = -2;i++; continue;
/*       */                 
/*       */ 
/* 10493 */                 for (i = 0; i < arrayOfInt.length; i++)
/* 10494 */                   arrayOfInt[i] = -3;
/*       */               } } }
/* 10496 */           resetCurrentRowBinders();
/*       */           
/*       */ 
/* 10499 */           Object localObject1 = DatabaseError.createBatchUpdateException(localSQLException, this.sqlKind.isPlsqlOrCall() ? i : arrayOfInt.length, arrayOfInt);
/* 10500 */           ((BatchUpdateException)localObject1).fillInStackTrace();
/* 10501 */           throw ((Throwable)localObject1);
/*       */ 
/*       */         }
/*       */         finally
/*       */         {
/* 10506 */           if ((this.sqlKind.isPlsqlOrCall()) || (j > this.validRows)) {
/* 10507 */             this.validRows = j;
/*       */           }
/* 10509 */           checkValidRowsStatus();
/*       */           
/* 10511 */           this.currentRank = 0;
/*       */         }
/*       */         
/* 10514 */         if (this.validRows < 0)
/*       */         {
/* 10516 */           for (i = 0; i < arrayOfInt.length; i++) {
/* 10517 */             arrayOfInt[i] = -3;
/*       */           }
/*       */           
/* 10520 */           BatchUpdateException localBatchUpdateException2 = DatabaseError.createBatchUpdateException(81, 0, arrayOfInt);
/* 10521 */           localBatchUpdateException2.fillInStackTrace();
/* 10522 */           throw localBatchUpdateException2;
/*       */         }
/*       */         
/* 10525 */         if (!this.sqlKind.isPlsqlOrCall())
/*       */         {
/* 10527 */           for (i = 0; i < arrayOfInt.length; i++) {
/* 10528 */             arrayOfInt[i] = -2;
/*       */           }
/*       */         }
/*       */       }
/*       */       
/*       */ 
/*       */ 
/*       */ 
/* 10536 */       this.connection.registerHeartbeat();
/*       */       
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/* 10542 */       return arrayOfInt;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void pushBatch(boolean paramBoolean)
/*       */   {
/* 10550 */     PushedBatch localPushedBatch = new PushedBatch();
/*       */     
/* 10552 */     localPushedBatch.currentBatchCharLens = new int[this.numberOfBindPositions];
/*       */     
/* 10554 */     System.arraycopy(this.currentBatchCharLens, 0, localPushedBatch.currentBatchCharLens, 0, this.numberOfBindPositions);
/*       */     
/*       */ 
/* 10557 */     localPushedBatch.lastBoundCharLens = new int[this.numberOfBindPositions];
/*       */     
/* 10559 */     System.arraycopy(this.lastBoundCharLens, 0, localPushedBatch.lastBoundCharLens, 0, this.numberOfBindPositions);
/*       */     
/*       */ 
/* 10562 */     if (this.currentBatchBindAccessors != null)
/*       */     {
/* 10564 */       localPushedBatch.currentBatchBindAccessors = new Accessor[this.numberOfBindPositions];
/*       */       
/* 10566 */       System.arraycopy(this.currentBatchBindAccessors, 0, localPushedBatch.currentBatchBindAccessors, 0, this.numberOfBindPositions);
/*       */     }
/*       */     
/*       */ 
/* 10570 */     localPushedBatch.lastBoundNeeded = this.lastBoundNeeded;
/* 10571 */     localPushedBatch.need_to_parse = this.needToParse;
/* 10572 */     localPushedBatch.current_batch_need_to_prepare_binds = this.currentBatchNeedToPrepareBinds;
/* 10573 */     localPushedBatch.first_row_in_batch = this.firstRowInBatch;
/* 10574 */     localPushedBatch.number_of_rows_to_be_bound = (this.currentRank - this.firstRowInBatch);
/*       */     
/* 10576 */     if (this.pushedBatches == null) {
/* 10577 */       this.pushedBatches = localPushedBatch;
/*       */     } else {
/* 10579 */       this.pushedBatchesTail.next = localPushedBatch;
/*       */     }
/* 10581 */     this.pushedBatchesTail = localPushedBatch;
/*       */     
/* 10583 */     if (!paramBoolean)
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/* 10589 */       int[] arrayOfInt = this.currentBatchCharLens;
/*       */       
/* 10591 */       this.currentBatchCharLens = this.lastBoundCharLens;
/* 10592 */       this.lastBoundCharLens = arrayOfInt;
/*       */       
/* 10594 */       this.lastBoundNeeded = false;
/*       */       
/* 10596 */       for (int i = 0; i < this.numberOfBindPositions; i++) {
/* 10597 */         this.currentBatchCharLens[i] = 0;
/*       */       }
/* 10599 */       this.firstRowInBatch = this.currentRank;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int doScrollPstmtExecuteUpdate()
/*       */     throws SQLException
/*       */   {
/* 10613 */     doScrollExecuteCommon();
/*       */     
/* 10615 */     if (this.sqlKind.isSELECT()) {
/* 10616 */       this.scrollRsetTypeSolved = true;
/*       */     }
/* 10618 */     return this.validRows;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public int copyBinds(Statement paramStatement, int paramInt)
/*       */     throws SQLException
/*       */   {
/* 10633 */     if (this.numberOfBindPositions > 0)
/*       */     {
/* 10635 */       OraclePreparedStatement localOraclePreparedStatement = (OraclePreparedStatement)paramStatement;
/*       */       
/* 10637 */       int i = this.bindIndicatorSubRange + 5;
/*       */       
/* 10639 */       int j = this.bindByteSubRange;
/* 10640 */       int k = this.bindCharSubRange;
/* 10641 */       int m = this.indicatorsOffset;
/* 10642 */       int n = this.valueLengthsOffset;
/*       */       
/* 10644 */       for (int i1 = 0; i1 < this.numberOfBindPositions; i1++)
/*       */       {
/* 10646 */         short s = this.bindIndicators[(i + 0)];
/*       */         
/* 10648 */         int i2 = this.bindIndicators[(i + 1)];
/*       */         
/* 10650 */         int i3 = this.bindIndicators[(i + 2)];
/*       */         
/*       */ 
/* 10653 */         int i4 = i1 + paramInt;
/*       */         
/*       */ 
/*       */ 
/* 10657 */         if (localOraclePreparedStatement.parameterDatum == null) {
/* 10658 */           localOraclePreparedStatement.parameterDatum = new byte[localOraclePreparedStatement.numberOfBindRowsAllocated][localOraclePreparedStatement.numberOfBindPositions][];
/*       */         }
/*       */         
/* 10661 */         if (localOraclePreparedStatement.parameterOtype == null) {
/* 10662 */           localOraclePreparedStatement.parameterOtype = new OracleTypeADT[localOraclePreparedStatement.numberOfBindRowsAllocated][localOraclePreparedStatement.numberOfBindPositions];
/*       */         }
/*       */         
/* 10665 */         if (this.bindIndicators[m] == -1)
/*       */         {
/* 10667 */           localOraclePreparedStatement.currentRowBinders[i4] = copiedNullBinder(s, i2);
/*       */           
/* 10669 */           if (i3 > 0)
/* 10670 */             localOraclePreparedStatement.currentRowCharLens[i4] = 1;
/*       */         } else { Object localObject;
/* 10672 */           if ((s == 109) || (s == 111))
/*       */           {
/* 10674 */             localOraclePreparedStatement.currentRowBinders[i4] = (s == 109 ? this.theNamedTypeBinder : this.theRefTypeBinder);
/*       */             
/*       */ 
/*       */ 
/*       */ 
/* 10679 */             localObject = this.parameterDatum[0][i1];
/* 10680 */             int i5 = localObject.length;
/* 10681 */             byte[] arrayOfByte = new byte[i5];
/*       */             
/* 10683 */             localOraclePreparedStatement.parameterDatum[0][i4] = arrayOfByte;
/*       */             
/* 10685 */             System.arraycopy(localObject, 0, arrayOfByte, 0, i5);
/*       */             
/* 10687 */             localOraclePreparedStatement.parameterOtype[0][i4] = this.parameterOtype[0][i1];
/*       */           }
/* 10689 */           else if (i2 > 0)
/*       */           {
/* 10691 */             localOraclePreparedStatement.currentRowBinders[i4] = copiedByteBinder(s, this.bindBytes, j, i2, this.bindIndicators[n]);
/*       */ 
/*       */           }
/* 10694 */           else if (i3 > 0)
/*       */           {
/* 10696 */             localOraclePreparedStatement.currentRowBinders[i4] = copiedCharBinder(s, this.bindChars, k, i3, this.bindIndicators[n], getInoutIndicator(i1));
/*       */             
/* 10698 */             localOraclePreparedStatement.currentRowCharLens[i4] = i3;
/*       */           }
/*       */           else
/*       */           {
/* 10702 */             localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 89, "copyBinds doesn't understand type " + s);
/* 10703 */             ((SQLException)localObject).fillInStackTrace();
/* 10704 */             throw ((Throwable)localObject);
/*       */           }
/*       */         }
/* 10707 */         j += this.bindBufferCapacity * i2;
/* 10708 */         k += this.bindBufferCapacity * i3;
/* 10709 */         m += this.numberOfBindRowsAllocated;
/* 10710 */         n += this.numberOfBindRowsAllocated;
/* 10711 */         i += 10;
/*       */       }
/*       */     }
/*       */     
/* 10715 */     return this.numberOfBindPositions;
/*       */   }
/*       */   
/*       */ 
/*       */   Binder copiedNullBinder(short paramShort, int paramInt)
/*       */     throws SQLException
/*       */   {
/* 10722 */     return new CopiedNullBinder(paramShort, paramInt);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   Binder copiedByteBinder(short paramShort1, byte[] paramArrayOfByte, int paramInt1, int paramInt2, short paramShort2)
/*       */     throws SQLException
/*       */   {
/* 10730 */     byte[] arrayOfByte = new byte[paramInt2];
/*       */     
/* 10732 */     System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
/*       */     
/* 10734 */     return new CopiedByteBinder(paramShort1, paramInt2, arrayOfByte, paramShort2);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   Binder copiedCharBinder(short paramShort1, char[] paramArrayOfChar, int paramInt1, int paramInt2, short paramShort2, short paramShort3)
/*       */     throws SQLException
/*       */   {
/* 10742 */     char[] arrayOfChar = new char[paramInt2];
/*       */     
/* 10744 */     System.arraycopy(paramArrayOfChar, paramInt1, arrayOfChar, 0, paramInt2);
/*       */     
/* 10746 */     return new CopiedCharBinder(paramShort1, arrayOfChar, paramShort2, paramShort3);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   protected void hardClose()
/*       */     throws SQLException
/*       */   {
/* 10754 */     super.hardClose();
/*       */     
/* 10756 */     this.connection.cacheBuffer(this.bindBytes);
/* 10757 */     this.bindBytes = null;
/* 10758 */     this.connection.cacheBuffer(this.bindChars);
/* 10759 */     this.bindChars = null;
/* 10760 */     this.bindIndicators = null;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/* 10765 */     if (!this.connection.isClosed())
/*       */     {
/* 10767 */       cleanAllTempLobs();
/*       */     }
/*       */     
/* 10770 */     this.lastBoundBytes = null;
/* 10771 */     this.lastBoundChars = null;
/*       */     
/* 10773 */     clearParameters();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   protected void alwaysOnClose()
/*       */     throws SQLException
/*       */   {
/* 10785 */     if (this.currentRank > 0)
/*       */     {
/* 10787 */       if (this.m_batchStyle == 2) {
/* 10788 */         clearBatch();
/*       */ 
/*       */       }
/*       */       else
/*       */       {
/*       */ 
/* 10794 */         int i = this.validRows;
/*       */         
/* 10796 */         this.prematureBatchCount = sendBatch();
/* 10797 */         this.validRows = i;
/*       */       }
/*       */     }
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/* 10812 */     if (this.sqlKind.isSELECT()) {
/* 10813 */       Object localObject = this.children;
/*       */       
/* 10815 */       while (localObject != null) {
/* 10816 */         OracleStatement localOracleStatement = ((OracleStatement)localObject).nextChild;
/*       */         
/*       */ 
/* 10819 */         if (((OracleStatement)localObject).serverCursor) {
/* 10820 */           ((OracleStatement)localObject).cursorId = 0;
/*       */         }
/* 10822 */         localObject = localOracleStatement;
/*       */       }
/*       */     }
/*       */     
/* 10826 */     super.alwaysOnClose();
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setDisableStmtCaching(boolean paramBoolean)
/*       */   {
/* 10839 */     synchronized (this.connection)
/*       */     {
/* 10841 */       if (paramBoolean == true) {
/* 10842 */         this.cacheState = 3;
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setFormOfUse(int paramInt, short paramShort)
/*       */   {
/* 10852 */     synchronized (this.connection)
/*       */     {
/*       */ 
/* 10855 */       int i = paramInt - 1;
/*       */       
/* 10857 */       if (this.currentRowFormOfUse[i] != paramShort)
/*       */       {
/* 10859 */         this.currentRowFormOfUse[i] = paramShort;
/*       */         
/*       */         Accessor localAccessor;
/*       */         
/* 10863 */         if (this.currentRowBindAccessors != null)
/*       */         {
/* 10865 */           localAccessor = this.currentRowBindAccessors[i];
/*       */           
/* 10867 */           if (localAccessor != null) {
/* 10868 */             localAccessor.setFormOfUse(paramShort);
/*       */           }
/*       */         }
/*       */         
/* 10872 */         if (this.returnParamAccessors != null)
/*       */         {
/* 10874 */           localAccessor = this.returnParamAccessors[i];
/*       */           
/* 10876 */           if (localAccessor != null) {
/* 10877 */             localAccessor.setFormOfUse(paramShort);
/*       */           }
/*       */         }
/*       */       }
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setURL(int paramInt, URL paramURL)
/*       */     throws SQLException
/*       */   {
/* 10904 */     setURLInternal(paramInt, paramURL);
/*       */   }
/*       */   
/*       */ 
/*       */   void setURLInternal(int paramInt, URL paramURL)
/*       */     throws SQLException
/*       */   {
/* 10911 */     setStringInternal(paramInt, paramURL.toString());
/*       */   }
/*       */   
/*       */ 
/*       */   public ParameterMetaData getParameterMetaData()
/*       */     throws SQLException
/*       */   {
/* 10918 */     return new OracleParameterMetaData(this.sqlObject.getParameterCount());
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public oracle.jdbc.OracleParameterMetaData OracleGetParameterMetaData()
/*       */     throws SQLException
/*       */   {
/* 10941 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 10942 */     localSQLException.fillInStackTrace();
/* 10943 */     throw localSQLException;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   public void registerReturnParameter(int paramInt1, int paramInt2)
/*       */     throws SQLException
/*       */   {
/*       */     SQLException localSQLException1;
/*       */     
/* 10953 */     if (this.numberOfBindPositions <= 0)
/*       */     {
/* 10955 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90);
/* 10956 */       localSQLException1.fillInStackTrace();
/* 10957 */       throw localSQLException1;
/*       */     }
/*       */     
/* 10960 */     if (this.numReturnParams <= 0)
/*       */     {
/* 10962 */       this.numReturnParams = this.sqlObject.getReturnParameterCount();
/* 10963 */       if (this.numReturnParams <= 0)
/*       */       {
/* 10965 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90);
/* 10966 */         localSQLException1.fillInStackTrace();
/* 10967 */         throw localSQLException1;
/*       */       }
/*       */     }
/*       */     
/* 10971 */     int i = paramInt1 - 1;
/* 10972 */     if ((i < this.numberOfBindPositions - this.numReturnParams) || (paramInt1 > this.numberOfBindPositions))
/*       */     {
/*       */ 
/* 10975 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 10976 */       localSQLException2.fillInStackTrace();
/* 10977 */       throw localSQLException2;
/*       */     }
/*       */     
/* 10980 */     int j = getInternalTypeForDmlReturning(paramInt2);
/*       */     
/* 10982 */     short s = 0;
/* 10983 */     if ((this.currentRowFormOfUse != null) && (this.currentRowFormOfUse[i] != 0)) {
/* 10984 */       s = this.currentRowFormOfUse[i];
/*       */     }
/* 10986 */     registerReturnParameterInternal(i, j, paramInt2, -1, s, null);
/*       */     
/*       */ 
/* 10989 */     this.currentRowBinders[i] = this.theReturnParamBinder;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void registerReturnParameter(int paramInt1, int paramInt2, int paramInt3)
/*       */     throws SQLException
/*       */   {
/* 11000 */     if (this.numberOfBindPositions <= 0)
/*       */     {
/* 11002 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90);
/* 11003 */       localSQLException1.fillInStackTrace();
/* 11004 */       throw localSQLException1;
/*       */     }
/*       */     
/* 11007 */     int i = paramInt1 - 1;
/* 11008 */     SQLException localSQLException2; if ((i < 0) || (paramInt1 > this.numberOfBindPositions))
/*       */     {
/* 11010 */       localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 11011 */       localSQLException2.fillInStackTrace();
/* 11012 */       throw localSQLException2;
/*       */     }
/*       */     
/* 11015 */     if ((paramInt2 != 1) && (paramInt2 != 12) && (paramInt2 != -1) && (paramInt2 != -2) && (paramInt2 != -3) && (paramInt2 != -4) && (paramInt2 != 12))
/*       */     {
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/* 11024 */       localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 11025 */       localSQLException2.fillInStackTrace();
/* 11026 */       throw localSQLException2;
/*       */     }
/*       */     
/*       */ 
/* 11030 */     if (paramInt3 <= 0)
/*       */     {
/*       */ 
/* 11033 */       localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 11034 */       localSQLException2.fillInStackTrace();
/* 11035 */       throw localSQLException2;
/*       */     }
/*       */     
/*       */ 
/* 11039 */     int j = getInternalTypeForDmlReturning(paramInt2);
/*       */     
/* 11041 */     short s = 0;
/* 11042 */     if ((this.currentRowFormOfUse != null) && (this.currentRowFormOfUse[i] != 0)) {
/* 11043 */       s = this.currentRowFormOfUse[i];
/*       */     }
/* 11045 */     registerReturnParameterInternal(i, j, paramInt2, paramInt3, s, null);
/*       */     
/*       */ 
/* 11048 */     this.currentRowBinders[i] = this.theReturnParamBinder;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void registerReturnParameter(int paramInt1, int paramInt2, String paramString)
/*       */     throws SQLException
/*       */   {
/* 11059 */     if (this.numberOfBindPositions <= 0)
/*       */     {
/* 11061 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90);
/* 11062 */       localSQLException1.fillInStackTrace();
/* 11063 */       throw localSQLException1;
/*       */     }
/*       */     
/* 11066 */     int i = paramInt1 - 1;
/* 11067 */     if ((i < 0) || (paramInt1 > this.numberOfBindPositions))
/*       */     {
/* 11069 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 11070 */       localSQLException2.fillInStackTrace();
/* 11071 */       throw localSQLException2;
/*       */     }
/*       */     
/* 11074 */     int j = getInternalTypeForDmlReturning(paramInt2);
/* 11075 */     if ((j != 111) && (j != 109))
/*       */     {
/*       */ 
/*       */ 
/* 11079 */       SQLException localSQLException3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 11080 */       localSQLException3.fillInStackTrace();
/* 11081 */       throw localSQLException3;
/*       */     }
/*       */     
/*       */ 
/* 11085 */     registerReturnParameterInternal(i, j, paramInt2, -1, (short)0, paramString);
/*       */     
/*       */ 
/* 11088 */     this.currentRowBinders[i] = this.theReturnParamBinder;
/*       */   }
/*       */   
/*       */ 
/*       */   public ResultSet getReturnResultSet()
/*       */     throws SQLException
/*       */   {
/*       */     SQLException localSQLException;
/* 11096 */     if (this.closed)
/*       */     {
/* 11098 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 11099 */       localSQLException.fillInStackTrace();
/* 11100 */       throw localSQLException;
/*       */     }
/*       */     
/* 11103 */     if ((this.returnParamAccessors == null) || (this.numReturnParams == 0))
/*       */     {
/* 11105 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 144);
/* 11106 */       localSQLException.fillInStackTrace();
/* 11107 */       throw localSQLException;
/*       */     }
/*       */     
/* 11110 */     if ((this.returnResultSet == null) || (this.numReturnParams == 0) || (!this.isOpen))
/*       */     {
/*       */ 
/*       */ 
/* 11114 */       this.returnResultSet = new OracleReturnResultSet(this);
/*       */     }
/*       */     
/* 11117 */     return this.returnResultSet;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   int getInternalTypeForDmlReturning(int paramInt)
/*       */     throws SQLException
/*       */   {
/* 11131 */     int i = 0;
/*       */     
/* 11133 */     switch (paramInt)
/*       */     {
/*       */     case -7: 
/*       */     case -6: 
/*       */     case -5: 
/*       */     case 2: 
/*       */     case 3: 
/*       */     case 4: 
/*       */     case 5: 
/*       */     case 6: 
/*       */     case 7: 
/*       */     case 8: 
/* 11145 */       i = 6;
/* 11146 */       break;
/*       */     
/*       */     case 100: 
/* 11149 */       i = 100;
/* 11150 */       break;
/*       */     
/*       */     case 101: 
/* 11153 */       i = 101;
/* 11154 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     case -15: 
/*       */     case 1: 
/* 11162 */       i = 96;
/* 11163 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     case -9: 
/*       */     case 12: 
/* 11171 */       i = 1;
/* 11172 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     case -16: 
/*       */     case -1: 
/* 11180 */       i = 8;
/* 11181 */       break;
/*       */     
/*       */     case 91: 
/*       */     case 92: 
/* 11185 */       i = 12;
/* 11186 */       break;
/*       */     
/*       */     case 93: 
/* 11189 */       i = 180;
/* 11190 */       break;
/*       */     
/*       */     case -101: 
/* 11193 */       i = 181;
/* 11194 */       break;
/*       */     
/*       */     case -102: 
/* 11197 */       i = 231;
/* 11198 */       break;
/*       */     
/*       */     case -103: 
/* 11201 */       i = 182;
/* 11202 */       break;
/*       */     
/*       */     case -104: 
/* 11205 */       i = 183;
/* 11206 */       break;
/*       */     
/*       */     case -3: 
/*       */     case -2: 
/* 11210 */       i = 23;
/* 11211 */       break;
/*       */     
/*       */     case -4: 
/* 11214 */       i = 24;
/* 11215 */       break;
/*       */     
/*       */     case -8: 
/* 11218 */       i = 104;
/* 11219 */       break;
/*       */     
/*       */     case 2004: 
/* 11222 */       i = 113;
/* 11223 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     case 2005: 
/*       */     case 2011: 
/* 11231 */       i = 112;
/* 11232 */       break;
/*       */     
/*       */     case -13: 
/* 11235 */       i = 114;
/* 11236 */       break;
/*       */     
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */     case 2002: 
/*       */     case 2003: 
/*       */     case 2007: 
/*       */     case 2008: 
/*       */     case 2009: 
/* 11247 */       i = 109;
/* 11248 */       break;
/*       */     
/*       */     case 2006: 
/* 11251 */       i = 111;
/* 11252 */       break;
/*       */     
/*       */     case 70: 
/* 11255 */       i = 1;
/* 11256 */       break;
/*       */     
/*       */ 
/*       */     default: 
/* 11260 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 11261 */       localSQLException.fillInStackTrace();
/* 11262 */       throw localSQLException;
/*       */     }
/*       */     
/*       */     
/*       */ 
/* 11267 */     return i;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void registerReturnParamsForAutoKey()
/*       */     throws SQLException
/*       */   {
/* 11275 */     int[] arrayOfInt1 = this.autoKeyInfo.returnTypes;
/* 11276 */     short[] arrayOfShort = this.autoKeyInfo.tableFormOfUses;
/* 11277 */     int[] arrayOfInt2 = this.autoKeyInfo.columnIndexes;
/*       */     
/* 11279 */     int i = arrayOfInt1.length;
/*       */     
/*       */ 
/* 11282 */     int j = this.numberOfBindPositions - i;
/*       */     
/*       */ 
/* 11285 */     for (int k = 0; k < i; k++)
/*       */     {
/* 11287 */       int m = j + k;
/* 11288 */       this.currentRowBinders[m] = this.theReturnParamBinder;
/*       */       
/* 11290 */       short s = this.connection.defaultnchar ? 2 : 1;
/*       */       
/*       */ 
/* 11293 */       if ((arrayOfShort != null) && (arrayOfInt2 != null))
/*       */       {
/* 11295 */         if (arrayOfShort[(arrayOfInt2[k] - 1)] == 2)
/*       */         {
/*       */ 
/* 11298 */           s = 2;
/* 11299 */           setFormOfUse(m + 1, s);
/*       */         }
/*       */       }
/*       */       
/* 11303 */       checkTypeForAutoKey(arrayOfInt1[k]);
/*       */       
/* 11305 */       String str = null;
/* 11306 */       if (arrayOfInt1[k] == 111) {
/* 11307 */         str = this.autoKeyInfo.tableTypeNames[(arrayOfInt2[k] - 1)];
/*       */       }
/* 11309 */       registerReturnParameterInternal(m, arrayOfInt1[k], arrayOfInt1[k], -1, s, str);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void cleanOldTempLobs()
/*       */   {
/* 11319 */     if ((this.m_batchStyle != 1) || (this.currentRank == this.batch - 1))
/*       */     {
/* 11321 */       super.cleanOldTempLobs();
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void resetOnExceptionDuringExecute()
/*       */   {
/* 11328 */     super.resetOnExceptionDuringExecute();
/* 11329 */     this.currentRank = 0;
/* 11330 */     this.currentBatchNeedToPrepareBinds = true;
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   void resetCurrentRowBinders()
/*       */   {
/* 11339 */     Binder[] arrayOfBinder = this.currentRowBinders;
/* 11340 */     if ((this.binders != null) && (this.currentRowBinders != null) && (arrayOfBinder != this.binders[0]))
/*       */     {
/*       */ 
/*       */ 
/* 11344 */       this.currentRowBinders = this.binders[0];
/* 11345 */       this.binders[this.numberOfBoundRows] = arrayOfBinder;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setAsciiStream(int paramInt, InputStream paramInputStream)
/*       */     throws SQLException
/*       */   {
/* 11364 */     setAsciiStreamInternal(paramInt, paramInputStream);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setAsciiStream(int paramInt, InputStream paramInputStream, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11379 */     setAsciiStreamInternal(paramInt, paramInputStream, paramLong);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBinaryStream(int paramInt, InputStream paramInputStream)
/*       */     throws SQLException
/*       */   {
/* 11393 */     setBinaryStreamInternal(paramInt, paramInputStream);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBinaryStream(int paramInt, InputStream paramInputStream, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11408 */     setBinaryStreamInternal(paramInt, paramInputStream, paramLong);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBlob(int paramInt, InputStream paramInputStream)
/*       */     throws SQLException
/*       */   {
/* 11421 */     setBlobInternal(paramInt, paramInputStream);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBlob(int paramInt, InputStream paramInputStream, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11435 */     if (paramLong < 0L)
/*       */     {
/* 11437 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "length for setBlob() cannot be negative");
/* 11438 */       localSQLException.fillInStackTrace();
/* 11439 */       throw localSQLException;
/*       */     }
/* 11441 */     setBlobInternal(paramInt, paramInputStream, paramLong);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCharacterStream(int paramInt, Reader paramReader)
/*       */     throws SQLException
/*       */   {
/* 11455 */     setCharacterStreamInternal(paramInt, paramReader);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11470 */     setCharacterStreamInternal(paramInt, paramReader, paramLong);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setClob(int paramInt, Reader paramReader, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11484 */     if (paramLong < 0L)
/*       */     {
/* 11486 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "length for setClob() cannot be negative");
/* 11487 */       localSQLException.fillInStackTrace();
/* 11488 */       throw localSQLException;
/*       */     }
/* 11490 */     setClobInternal(paramInt, paramReader, paramLong);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setClob(int paramInt, Reader paramReader)
/*       */     throws SQLException
/*       */   {
/* 11504 */     setClobInternal(paramInt, paramReader);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setRowId(int paramInt, RowId paramRowId)
/*       */     throws SQLException
/*       */   {
/* 11519 */     setRowIdInternal(paramInt, paramRowId);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNCharacterStream(int paramInt, Reader paramReader)
/*       */     throws SQLException
/*       */   {
/* 11535 */     setNCharacterStreamInternal(paramInt, paramReader);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11551 */     setNCharacterStreamInternal(paramInt, paramReader, paramLong);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNClob(int paramInt, NClob paramNClob)
/*       */     throws SQLException
/*       */   {
/* 11566 */     setNClobInternal(paramInt, paramNClob);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNClob(int paramInt, Reader paramReader, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11582 */     setNClobInternal(paramInt, paramReader, paramLong);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNClob(int paramInt, Reader paramReader)
/*       */     throws SQLException
/*       */   {
/* 11597 */     setNClobInternal(paramInt, paramReader);
/*       */   }
/*       */   
/*       */ 
/*       */   public void setSQLXML(int paramInt, SQLXML paramSQLXML)
/*       */     throws SQLException
/*       */   {
/* 11604 */     setSQLXMLInternal(paramInt, paramSQLXML);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNString(int paramInt, String paramString)
/*       */     throws SQLException
/*       */   {
/* 11619 */     setNStringInternal(paramInt, paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setAsciiStreamInternal(int paramInt, InputStream paramInputStream)
/*       */     throws SQLException
/*       */   {
/* 11627 */     setAsciiStreamInternal(paramInt, paramInputStream, 0L, false);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setAsciiStreamInternal(int paramInt, InputStream paramInputStream, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11635 */     setAsciiStreamInternal(paramInt, paramInputStream, paramLong, true);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setBinaryStreamInternal(int paramInt, InputStream paramInputStream)
/*       */     throws SQLException
/*       */   {
/* 11643 */     setBinaryStreamInternal(paramInt, paramInputStream, 0L, false);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setBinaryStreamInternal(int paramInt, InputStream paramInputStream, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11651 */     setBinaryStreamInternal(paramInt, paramInputStream, paramLong, true);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */   void setBlobInternal(int paramInt, InputStream paramInputStream, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11660 */     int i = paramInt - 1;
/*       */     
/* 11662 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/* 11664 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 11665 */       localSQLException.fillInStackTrace();
/* 11666 */       throw localSQLException;
/*       */     }
/*       */     
/* 11669 */     if (paramInputStream == null) {
/* 11670 */       setNullInternal(paramInt, 2004);
/*       */     } else {
/* 11672 */       setBinaryStreamContentsForBlobCritical(paramInt, paramInputStream, paramLong, paramLong != -1L);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setBlobInternal(int paramInt, InputStream paramInputStream)
/*       */     throws SQLException
/*       */   {
/* 11681 */     setBlobInternal(paramInt, paramInputStream, -1L);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setCharacterStreamInternal(int paramInt, Reader paramReader)
/*       */     throws SQLException
/*       */   {
/* 11689 */     setCharacterStreamInternal(paramInt, paramReader, 0L, false);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setCharacterStreamInternal(int paramInt, Reader paramReader, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11697 */     setCharacterStreamInternal(paramInt, paramReader, paramLong, true);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setClobInternal(int paramInt, Reader paramReader)
/*       */     throws SQLException
/*       */   {
/* 11705 */     setClobInternal(paramInt, paramReader, -1L);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setClobInternal(int paramInt, Reader paramReader, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11713 */     int i = paramInt - 1;
/*       */     
/* 11715 */     if ((i < 0) || (paramInt > this.numberOfBindPositions))
/*       */     {
/* 11717 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 11718 */       localSQLException.fillInStackTrace();
/* 11719 */       throw localSQLException;
/*       */     }
/*       */     
/* 11722 */     if (paramReader == null) {
/* 11723 */       setNullInternal(paramInt, 2005);
/*       */     } else {
/* 11725 */       setReaderContentsForClobCritical(paramInt, paramReader, paramLong, paramLong != -1L);
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */   void setNCharacterStreamInternal(int paramInt, Reader paramReader)
/*       */     throws SQLException
/*       */   {
/* 11733 */     setFormOfUse(paramInt, (short)2);
/* 11734 */     setCharacterStreamInternal(paramInt, paramReader, 0L, false);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setNCharacterStreamInternal(int paramInt, Reader paramReader, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11742 */     setFormOfUse(paramInt, (short)2);
/* 11743 */     setCharacterStreamInternal(paramInt, paramReader, paramLong);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setNClobInternal(int paramInt, NClob paramNClob)
/*       */     throws SQLException
/*       */   {
/* 11751 */     setFormOfUse(paramInt, (short)2);
/* 11752 */     setClobInternal(paramInt, paramNClob);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setNClobInternal(int paramInt, Reader paramReader)
/*       */     throws SQLException
/*       */   {
/* 11760 */     setFormOfUse(paramInt, (short)2);
/* 11761 */     setClobInternal(paramInt, paramReader);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setNClobInternal(int paramInt, Reader paramReader, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 11769 */     setFormOfUse(paramInt, (short)2);
/* 11770 */     setClobInternal(paramInt, paramReader, paramLong);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setNStringInternal(int paramInt, String paramString)
/*       */     throws SQLException
/*       */   {
/* 11778 */     setFormOfUse(paramInt, (short)2);
/* 11779 */     setStringInternal(paramInt, paramString);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */   void setRowIdInternal(int paramInt, RowId paramRowId)
/*       */     throws SQLException
/*       */   {
/* 11787 */     setROWIDInternal(paramInt, (ROWID)paramRowId);
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setArrayAtName(String paramString, Array paramArray)
/*       */     throws SQLException
/*       */   {
/* 11814 */     String str = paramString.intern();
/* 11815 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 11816 */     int i = 0;
/* 11817 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 11819 */     for (int k = 0; k < j; k++) {
/* 11820 */       if (arrayOfString[k] == str)
/*       */       {
/* 11822 */         setArray(k + 1, paramArray);
/*       */         
/* 11824 */         i = 1;
/*       */       }
/*       */     }
/* 11827 */     if (i == 0)
/*       */     {
/* 11829 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 11830 */       localSQLException.fillInStackTrace();
/* 11831 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBigDecimalAtName(String paramString, BigDecimal paramBigDecimal)
/*       */     throws SQLException
/*       */   {
/* 11851 */     String str = paramString.intern();
/* 11852 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 11853 */     int i = 0;
/* 11854 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 11856 */     for (int k = 0; k < j; k++) {
/* 11857 */       if (arrayOfString[k] == str)
/*       */       {
/* 11859 */         setBigDecimal(k + 1, paramBigDecimal);
/*       */         
/* 11861 */         i = 1;
/*       */       }
/*       */     }
/* 11864 */     if (i == 0)
/*       */     {
/* 11866 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 11867 */       localSQLException.fillInStackTrace();
/* 11868 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBlobAtName(String paramString, Blob paramBlob)
/*       */     throws SQLException
/*       */   {
/* 11888 */     String str = paramString.intern();
/* 11889 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 11890 */     int i = 0;
/* 11891 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 11893 */     for (int k = 0; k < j; k++) {
/* 11894 */       if (arrayOfString[k] == str)
/*       */       {
/* 11896 */         setBlob(k + 1, paramBlob);
/*       */         
/* 11898 */         i = 1;
/*       */       }
/*       */     }
/* 11901 */     if (i == 0)
/*       */     {
/* 11903 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 11904 */       localSQLException.fillInStackTrace();
/* 11905 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBooleanAtName(String paramString, boolean paramBoolean)
/*       */     throws SQLException
/*       */   {
/* 11925 */     String str = paramString.intern();
/* 11926 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 11927 */     int i = 0;
/* 11928 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 11930 */     for (int k = 0; k < j; k++) {
/* 11931 */       if (arrayOfString[k] == str)
/*       */       {
/* 11933 */         setBoolean(k + 1, paramBoolean);
/*       */         
/* 11935 */         i = 1;
/*       */       }
/*       */     }
/* 11938 */     if (i == 0)
/*       */     {
/* 11940 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 11941 */       localSQLException.fillInStackTrace();
/* 11942 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setByteAtName(String paramString, byte paramByte)
/*       */     throws SQLException
/*       */   {
/* 11962 */     String str = paramString.intern();
/* 11963 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 11964 */     int i = 0;
/* 11965 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 11967 */     for (int k = 0; k < j; k++) {
/* 11968 */       if (arrayOfString[k] == str)
/*       */       {
/* 11970 */         setByte(k + 1, paramByte);
/*       */         
/* 11972 */         i = 1;
/*       */       }
/*       */     }
/* 11975 */     if (i == 0)
/*       */     {
/* 11977 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 11978 */       localSQLException.fillInStackTrace();
/* 11979 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBytesAtName(String paramString, byte[] paramArrayOfByte)
/*       */     throws SQLException
/*       */   {
/* 11999 */     String str = paramString.intern();
/* 12000 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12001 */     int i = 0;
/* 12002 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12004 */     for (int k = 0; k < j; k++) {
/* 12005 */       if (arrayOfString[k] == str)
/*       */       {
/* 12007 */         setBytes(k + 1, paramArrayOfByte);
/*       */         
/* 12009 */         i = 1;
/*       */       }
/*       */     }
/* 12012 */     if (i == 0)
/*       */     {
/* 12014 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12015 */       localSQLException.fillInStackTrace();
/* 12016 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setClobAtName(String paramString, Clob paramClob)
/*       */     throws SQLException
/*       */   {
/* 12036 */     String str = paramString.intern();
/* 12037 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12038 */     int i = 0;
/* 12039 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12041 */     for (int k = 0; k < j; k++) {
/* 12042 */       if (arrayOfString[k] == str)
/*       */       {
/* 12044 */         setClob(k + 1, paramClob);
/*       */         
/* 12046 */         i = 1;
/*       */       }
/*       */     }
/* 12049 */     if (i == 0)
/*       */     {
/* 12051 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12052 */       localSQLException.fillInStackTrace();
/* 12053 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setDateAtName(String paramString, Date paramDate)
/*       */     throws SQLException
/*       */   {
/* 12073 */     String str = paramString.intern();
/* 12074 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12075 */     int i = 0;
/* 12076 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12078 */     for (int k = 0; k < j; k++) {
/* 12079 */       if (arrayOfString[k] == str)
/*       */       {
/* 12081 */         setDate(k + 1, paramDate);
/*       */         
/* 12083 */         i = 1;
/*       */       }
/*       */     }
/* 12086 */     if (i == 0)
/*       */     {
/* 12088 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12089 */       localSQLException.fillInStackTrace();
/* 12090 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setDateAtName(String paramString, Date paramDate, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/* 12110 */     String str = paramString.intern();
/* 12111 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12112 */     int i = 0;
/* 12113 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12115 */     for (int k = 0; k < j; k++) {
/* 12116 */       if (arrayOfString[k] == str)
/*       */       {
/* 12118 */         setDate(k + 1, paramDate, paramCalendar);
/*       */         
/* 12120 */         i = 1;
/*       */       }
/*       */     }
/* 12123 */     if (i == 0)
/*       */     {
/* 12125 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12126 */       localSQLException.fillInStackTrace();
/* 12127 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setDoubleAtName(String paramString, double paramDouble)
/*       */     throws SQLException
/*       */   {
/* 12147 */     String str = paramString.intern();
/* 12148 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12149 */     int i = 0;
/* 12150 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12152 */     for (int k = 0; k < j; k++) {
/* 12153 */       if (arrayOfString[k] == str)
/*       */       {
/* 12155 */         setDouble(k + 1, paramDouble);
/*       */         
/* 12157 */         i = 1;
/*       */       }
/*       */     }
/* 12160 */     if (i == 0)
/*       */     {
/* 12162 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12163 */       localSQLException.fillInStackTrace();
/* 12164 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setFloatAtName(String paramString, float paramFloat)
/*       */     throws SQLException
/*       */   {
/* 12184 */     String str = paramString.intern();
/* 12185 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12186 */     int i = 0;
/* 12187 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12189 */     for (int k = 0; k < j; k++) {
/* 12190 */       if (arrayOfString[k] == str)
/*       */       {
/* 12192 */         setFloat(k + 1, paramFloat);
/*       */         
/* 12194 */         i = 1;
/*       */       }
/*       */     }
/* 12197 */     if (i == 0)
/*       */     {
/* 12199 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12200 */       localSQLException.fillInStackTrace();
/* 12201 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setIntAtName(String paramString, int paramInt)
/*       */     throws SQLException
/*       */   {
/* 12221 */     String str = paramString.intern();
/* 12222 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12223 */     int i = 0;
/* 12224 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12226 */     for (int k = 0; k < j; k++) {
/* 12227 */       if (arrayOfString[k] == str)
/*       */       {
/* 12229 */         setInt(k + 1, paramInt);
/*       */         
/* 12231 */         i = 1;
/*       */       }
/*       */     }
/* 12234 */     if (i == 0)
/*       */     {
/* 12236 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12237 */       localSQLException.fillInStackTrace();
/* 12238 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setLongAtName(String paramString, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 12258 */     String str = paramString.intern();
/* 12259 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12260 */     int i = 0;
/* 12261 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12263 */     for (int k = 0; k < j; k++) {
/* 12264 */       if (arrayOfString[k] == str)
/*       */       {
/* 12266 */         setLong(k + 1, paramLong);
/*       */         
/* 12268 */         i = 1;
/*       */       }
/*       */     }
/* 12271 */     if (i == 0)
/*       */     {
/* 12273 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12274 */       localSQLException.fillInStackTrace();
/* 12275 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNClobAtName(String paramString, NClob paramNClob)
/*       */     throws SQLException
/*       */   {
/* 12295 */     String str = paramString.intern();
/* 12296 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12297 */     int i = 0;
/* 12298 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12300 */     for (int k = 0; k < j; k++) {
/* 12301 */       if (arrayOfString[k] == str)
/*       */       {
/* 12303 */         setNClob(k + 1, paramNClob);
/*       */         
/* 12305 */         i = 1;
/*       */       }
/*       */     }
/* 12308 */     if (i == 0)
/*       */     {
/* 12310 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12311 */       localSQLException.fillInStackTrace();
/* 12312 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNStringAtName(String paramString1, String paramString2)
/*       */     throws SQLException
/*       */   {
/* 12332 */     String str = paramString1.intern();
/* 12333 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12334 */     int i = 0;
/* 12335 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12337 */     for (int k = 0; k < j; k++) {
/* 12338 */       if (arrayOfString[k] == str)
/*       */       {
/* 12340 */         setNString(k + 1, paramString2);
/*       */         
/* 12342 */         i = 1;
/*       */       }
/*       */     }
/* 12345 */     if (i == 0)
/*       */     {
/* 12347 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString1);
/* 12348 */       localSQLException.fillInStackTrace();
/* 12349 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setObjectAtName(String paramString, Object paramObject)
/*       */     throws SQLException
/*       */   {
/* 12369 */     String str = paramString.intern();
/* 12370 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12371 */     int i = 0;
/* 12372 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12374 */     for (int k = 0; k < j; k++) {
/* 12375 */       if (arrayOfString[k] == str)
/*       */       {
/* 12377 */         setObject(k + 1, paramObject);
/*       */         
/* 12379 */         i = 1;
/*       */       }
/*       */     }
/* 12382 */     if (i == 0)
/*       */     {
/* 12384 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12385 */       localSQLException.fillInStackTrace();
/* 12386 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setObjectAtName(String paramString, Object paramObject, int paramInt)
/*       */     throws SQLException
/*       */   {
/* 12406 */     String str = paramString.intern();
/* 12407 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12408 */     int i = 0;
/* 12409 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12411 */     for (int k = 0; k < j; k++) {
/* 12412 */       if (arrayOfString[k] == str)
/*       */       {
/* 12414 */         setObject(k + 1, paramObject, paramInt);
/*       */         
/* 12416 */         i = 1;
/*       */       }
/*       */     }
/* 12419 */     if (i == 0)
/*       */     {
/* 12421 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12422 */       localSQLException.fillInStackTrace();
/* 12423 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setRefAtName(String paramString, Ref paramRef)
/*       */     throws SQLException
/*       */   {
/* 12443 */     String str = paramString.intern();
/* 12444 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12445 */     int i = 0;
/* 12446 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12448 */     for (int k = 0; k < j; k++) {
/* 12449 */       if (arrayOfString[k] == str)
/*       */       {
/* 12451 */         setRef(k + 1, paramRef);
/*       */         
/* 12453 */         i = 1;
/*       */       }
/*       */     }
/* 12456 */     if (i == 0)
/*       */     {
/* 12458 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12459 */       localSQLException.fillInStackTrace();
/* 12460 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setRowIdAtName(String paramString, RowId paramRowId)
/*       */     throws SQLException
/*       */   {
/* 12480 */     String str = paramString.intern();
/* 12481 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12482 */     int i = 0;
/* 12483 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12485 */     for (int k = 0; k < j; k++) {
/* 12486 */       if (arrayOfString[k] == str)
/*       */       {
/* 12488 */         setRowId(k + 1, paramRowId);
/*       */         
/* 12490 */         i = 1;
/*       */       }
/*       */     }
/* 12493 */     if (i == 0)
/*       */     {
/* 12495 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12496 */       localSQLException.fillInStackTrace();
/* 12497 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setShortAtName(String paramString, short paramShort)
/*       */     throws SQLException
/*       */   {
/* 12517 */     String str = paramString.intern();
/* 12518 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12519 */     int i = 0;
/* 12520 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12522 */     for (int k = 0; k < j; k++) {
/* 12523 */       if (arrayOfString[k] == str)
/*       */       {
/* 12525 */         setShort(k + 1, paramShort);
/*       */         
/* 12527 */         i = 1;
/*       */       }
/*       */     }
/* 12530 */     if (i == 0)
/*       */     {
/* 12532 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12533 */       localSQLException.fillInStackTrace();
/* 12534 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setSQLXMLAtName(String paramString, SQLXML paramSQLXML)
/*       */     throws SQLException
/*       */   {
/* 12554 */     String str = paramString.intern();
/* 12555 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12556 */     int i = 0;
/* 12557 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12559 */     for (int k = 0; k < j; k++) {
/* 12560 */       if (arrayOfString[k] == str)
/*       */       {
/* 12562 */         setSQLXML(k + 1, paramSQLXML);
/*       */         
/* 12564 */         i = 1;
/*       */       }
/*       */     }
/* 12567 */     if (i == 0)
/*       */     {
/* 12569 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12570 */       localSQLException.fillInStackTrace();
/* 12571 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setStringAtName(String paramString1, String paramString2)
/*       */     throws SQLException
/*       */   {
/* 12591 */     String str = paramString1.intern();
/* 12592 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12593 */     int i = 0;
/* 12594 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12596 */     for (int k = 0; k < j; k++) {
/* 12597 */       if (arrayOfString[k] == str)
/*       */       {
/* 12599 */         setString(k + 1, paramString2);
/*       */         
/* 12601 */         i = 1;
/*       */       }
/*       */     }
/* 12604 */     if (i == 0)
/*       */     {
/* 12606 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString1);
/* 12607 */       localSQLException.fillInStackTrace();
/* 12608 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTimeAtName(String paramString, Time paramTime)
/*       */     throws SQLException
/*       */   {
/* 12628 */     String str = paramString.intern();
/* 12629 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12630 */     int i = 0;
/* 12631 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12633 */     for (int k = 0; k < j; k++) {
/* 12634 */       if (arrayOfString[k] == str)
/*       */       {
/* 12636 */         setTime(k + 1, paramTime);
/*       */         
/* 12638 */         i = 1;
/*       */       }
/*       */     }
/* 12641 */     if (i == 0)
/*       */     {
/* 12643 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12644 */       localSQLException.fillInStackTrace();
/* 12645 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTimeAtName(String paramString, Time paramTime, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/* 12665 */     String str = paramString.intern();
/* 12666 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12667 */     int i = 0;
/* 12668 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12670 */     for (int k = 0; k < j; k++) {
/* 12671 */       if (arrayOfString[k] == str)
/*       */       {
/* 12673 */         setTime(k + 1, paramTime, paramCalendar);
/*       */         
/* 12675 */         i = 1;
/*       */       }
/*       */     }
/* 12678 */     if (i == 0)
/*       */     {
/* 12680 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12681 */       localSQLException.fillInStackTrace();
/* 12682 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTimestampAtName(String paramString, Timestamp paramTimestamp)
/*       */     throws SQLException
/*       */   {
/* 12702 */     String str = paramString.intern();
/* 12703 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12704 */     int i = 0;
/* 12705 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12707 */     for (int k = 0; k < j; k++) {
/* 12708 */       if (arrayOfString[k] == str)
/*       */       {
/* 12710 */         setTimestamp(k + 1, paramTimestamp);
/*       */         
/* 12712 */         i = 1;
/*       */       }
/*       */     }
/* 12715 */     if (i == 0)
/*       */     {
/* 12717 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12718 */       localSQLException.fillInStackTrace();
/* 12719 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTimestampAtName(String paramString, Timestamp paramTimestamp, Calendar paramCalendar)
/*       */     throws SQLException
/*       */   {
/* 12739 */     String str = paramString.intern();
/* 12740 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12741 */     int i = 0;
/* 12742 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12744 */     for (int k = 0; k < j; k++) {
/* 12745 */       if (arrayOfString[k] == str)
/*       */       {
/* 12747 */         setTimestamp(k + 1, paramTimestamp, paramCalendar);
/*       */         
/* 12749 */         i = 1;
/*       */       }
/*       */     }
/* 12752 */     if (i == 0)
/*       */     {
/* 12754 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12755 */       localSQLException.fillInStackTrace();
/* 12756 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setURLAtName(String paramString, URL paramURL)
/*       */     throws SQLException
/*       */   {
/* 12776 */     String str = paramString.intern();
/* 12777 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12778 */     int i = 0;
/* 12779 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12781 */     for (int k = 0; k < j; k++) {
/* 12782 */       if (arrayOfString[k] == str)
/*       */       {
/* 12784 */         setURL(k + 1, paramURL);
/*       */         
/* 12786 */         i = 1;
/*       */       }
/*       */     }
/* 12789 */     if (i == 0)
/*       */     {
/* 12791 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12792 */       localSQLException.fillInStackTrace();
/* 12793 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setARRAYAtName(String paramString, ARRAY paramARRAY)
/*       */     throws SQLException
/*       */   {
/* 12813 */     String str = paramString.intern();
/* 12814 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12815 */     int i = 0;
/* 12816 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12818 */     for (int k = 0; k < j; k++) {
/* 12819 */       if (arrayOfString[k] == str)
/*       */       {
/* 12821 */         setARRAY(k + 1, paramARRAY);
/*       */         
/* 12823 */         i = 1;
/*       */       }
/*       */     }
/* 12826 */     if (i == 0)
/*       */     {
/* 12828 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12829 */       localSQLException.fillInStackTrace();
/* 12830 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBFILEAtName(String paramString, BFILE paramBFILE)
/*       */     throws SQLException
/*       */   {
/* 12850 */     String str = paramString.intern();
/* 12851 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12852 */     int i = 0;
/* 12853 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12855 */     for (int k = 0; k < j; k++) {
/* 12856 */       if (arrayOfString[k] == str)
/*       */       {
/* 12858 */         setBFILE(k + 1, paramBFILE);
/*       */         
/* 12860 */         i = 1;
/*       */       }
/*       */     }
/* 12863 */     if (i == 0)
/*       */     {
/* 12865 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12866 */       localSQLException.fillInStackTrace();
/* 12867 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBfileAtName(String paramString, BFILE paramBFILE)
/*       */     throws SQLException
/*       */   {
/* 12887 */     String str = paramString.intern();
/* 12888 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12889 */     int i = 0;
/* 12890 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12892 */     for (int k = 0; k < j; k++) {
/* 12893 */       if (arrayOfString[k] == str)
/*       */       {
/* 12895 */         setBfile(k + 1, paramBFILE);
/*       */         
/* 12897 */         i = 1;
/*       */       }
/*       */     }
/* 12900 */     if (i == 0)
/*       */     {
/* 12902 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12903 */       localSQLException.fillInStackTrace();
/* 12904 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBinaryFloatAtName(String paramString, float paramFloat)
/*       */     throws SQLException
/*       */   {
/* 12924 */     String str = paramString.intern();
/* 12925 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12926 */     int i = 0;
/* 12927 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12929 */     for (int k = 0; k < j; k++) {
/* 12930 */       if (arrayOfString[k] == str)
/*       */       {
/* 12932 */         setBinaryFloat(k + 1, paramFloat);
/*       */         
/* 12934 */         i = 1;
/*       */       }
/*       */     }
/* 12937 */     if (i == 0)
/*       */     {
/* 12939 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12940 */       localSQLException.fillInStackTrace();
/* 12941 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBinaryFloatAtName(String paramString, BINARY_FLOAT paramBINARY_FLOAT)
/*       */     throws SQLException
/*       */   {
/* 12961 */     String str = paramString.intern();
/* 12962 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 12963 */     int i = 0;
/* 12964 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 12966 */     for (int k = 0; k < j; k++) {
/* 12967 */       if (arrayOfString[k] == str)
/*       */       {
/* 12969 */         setBinaryFloat(k + 1, paramBINARY_FLOAT);
/*       */         
/* 12971 */         i = 1;
/*       */       }
/*       */     }
/* 12974 */     if (i == 0)
/*       */     {
/* 12976 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 12977 */       localSQLException.fillInStackTrace();
/* 12978 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBinaryDoubleAtName(String paramString, double paramDouble)
/*       */     throws SQLException
/*       */   {
/* 12998 */     String str = paramString.intern();
/* 12999 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13000 */     int i = 0;
/* 13001 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13003 */     for (int k = 0; k < j; k++) {
/* 13004 */       if (arrayOfString[k] == str)
/*       */       {
/* 13006 */         setBinaryDouble(k + 1, paramDouble);
/*       */         
/* 13008 */         i = 1;
/*       */       }
/*       */     }
/* 13011 */     if (i == 0)
/*       */     {
/* 13013 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13014 */       localSQLException.fillInStackTrace();
/* 13015 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBinaryDoubleAtName(String paramString, BINARY_DOUBLE paramBINARY_DOUBLE)
/*       */     throws SQLException
/*       */   {
/* 13035 */     String str = paramString.intern();
/* 13036 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13037 */     int i = 0;
/* 13038 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13040 */     for (int k = 0; k < j; k++) {
/* 13041 */       if (arrayOfString[k] == str)
/*       */       {
/* 13043 */         setBinaryDouble(k + 1, paramBINARY_DOUBLE);
/*       */         
/* 13045 */         i = 1;
/*       */       }
/*       */     }
/* 13048 */     if (i == 0)
/*       */     {
/* 13050 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13051 */       localSQLException.fillInStackTrace();
/* 13052 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBLOBAtName(String paramString, BLOB paramBLOB)
/*       */     throws SQLException
/*       */   {
/* 13072 */     String str = paramString.intern();
/* 13073 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13074 */     int i = 0;
/* 13075 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13077 */     for (int k = 0; k < j; k++) {
/* 13078 */       if (arrayOfString[k] == str)
/*       */       {
/* 13080 */         setBLOB(k + 1, paramBLOB);
/*       */         
/* 13082 */         i = 1;
/*       */       }
/*       */     }
/* 13085 */     if (i == 0)
/*       */     {
/* 13087 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13088 */       localSQLException.fillInStackTrace();
/* 13089 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCHARAtName(String paramString, CHAR paramCHAR)
/*       */     throws SQLException
/*       */   {
/* 13109 */     String str = paramString.intern();
/* 13110 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13111 */     int i = 0;
/* 13112 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13114 */     for (int k = 0; k < j; k++) {
/* 13115 */       if (arrayOfString[k] == str)
/*       */       {
/* 13117 */         setCHAR(k + 1, paramCHAR);
/*       */         
/* 13119 */         i = 1;
/*       */       }
/*       */     }
/* 13122 */     if (i == 0)
/*       */     {
/* 13124 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13125 */       localSQLException.fillInStackTrace();
/* 13126 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCLOBAtName(String paramString, CLOB paramCLOB)
/*       */     throws SQLException
/*       */   {
/* 13146 */     String str = paramString.intern();
/* 13147 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13148 */     int i = 0;
/* 13149 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13151 */     for (int k = 0; k < j; k++) {
/* 13152 */       if (arrayOfString[k] == str)
/*       */       {
/* 13154 */         setCLOB(k + 1, paramCLOB);
/*       */         
/* 13156 */         i = 1;
/*       */       }
/*       */     }
/* 13159 */     if (i == 0)
/*       */     {
/* 13161 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13162 */       localSQLException.fillInStackTrace();
/* 13163 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCursorAtName(String paramString, ResultSet paramResultSet)
/*       */     throws SQLException
/*       */   {
/* 13183 */     String str = paramString.intern();
/* 13184 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13185 */     int i = 0;
/* 13186 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13188 */     for (int k = 0; k < j; k++) {
/* 13189 */       if (arrayOfString[k] == str)
/*       */       {
/* 13191 */         setCursor(k + 1, paramResultSet);
/*       */         
/* 13193 */         i = 1;
/*       */       }
/*       */     }
/* 13196 */     if (i == 0)
/*       */     {
/* 13198 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13199 */       localSQLException.fillInStackTrace();
/* 13200 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCustomDatumAtName(String paramString, CustomDatum paramCustomDatum)
/*       */     throws SQLException
/*       */   {
/* 13220 */     String str = paramString.intern();
/* 13221 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13222 */     int i = 0;
/* 13223 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13225 */     for (int k = 0; k < j; k++) {
/* 13226 */       if (arrayOfString[k] == str)
/*       */       {
/* 13228 */         setCustomDatum(k + 1, paramCustomDatum);
/*       */         
/* 13230 */         i = 1;
/*       */       }
/*       */     }
/* 13233 */     if (i == 0)
/*       */     {
/* 13235 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13236 */       localSQLException.fillInStackTrace();
/* 13237 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setDATEAtName(String paramString, DATE paramDATE)
/*       */     throws SQLException
/*       */   {
/* 13257 */     String str = paramString.intern();
/* 13258 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13259 */     int i = 0;
/* 13260 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13262 */     for (int k = 0; k < j; k++) {
/* 13263 */       if (arrayOfString[k] == str)
/*       */       {
/* 13265 */         setDATE(k + 1, paramDATE);
/*       */         
/* 13267 */         i = 1;
/*       */       }
/*       */     }
/* 13270 */     if (i == 0)
/*       */     {
/* 13272 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13273 */       localSQLException.fillInStackTrace();
/* 13274 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setFixedCHARAtName(String paramString1, String paramString2)
/*       */     throws SQLException
/*       */   {
/* 13294 */     String str = paramString1.intern();
/* 13295 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13296 */     int i = 0;
/* 13297 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13299 */     for (int k = 0; k < j; k++) {
/* 13300 */       if (arrayOfString[k] == str)
/*       */       {
/* 13302 */         setFixedCHAR(k + 1, paramString2);
/*       */         
/* 13304 */         i = 1;
/*       */       }
/*       */     }
/* 13307 */     if (i == 0)
/*       */     {
/* 13309 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString1);
/* 13310 */       localSQLException.fillInStackTrace();
/* 13311 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setINTERVALDSAtName(String paramString, INTERVALDS paramINTERVALDS)
/*       */     throws SQLException
/*       */   {
/* 13331 */     String str = paramString.intern();
/* 13332 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13333 */     int i = 0;
/* 13334 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13336 */     for (int k = 0; k < j; k++) {
/* 13337 */       if (arrayOfString[k] == str)
/*       */       {
/* 13339 */         setINTERVALDS(k + 1, paramINTERVALDS);
/*       */         
/* 13341 */         i = 1;
/*       */       }
/*       */     }
/* 13344 */     if (i == 0)
/*       */     {
/* 13346 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13347 */       localSQLException.fillInStackTrace();
/* 13348 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setINTERVALYMAtName(String paramString, INTERVALYM paramINTERVALYM)
/*       */     throws SQLException
/*       */   {
/* 13368 */     String str = paramString.intern();
/* 13369 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13370 */     int i = 0;
/* 13371 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13373 */     for (int k = 0; k < j; k++) {
/* 13374 */       if (arrayOfString[k] == str)
/*       */       {
/* 13376 */         setINTERVALYM(k + 1, paramINTERVALYM);
/*       */         
/* 13378 */         i = 1;
/*       */       }
/*       */     }
/* 13381 */     if (i == 0)
/*       */     {
/* 13383 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13384 */       localSQLException.fillInStackTrace();
/* 13385 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNUMBERAtName(String paramString, NUMBER paramNUMBER)
/*       */     throws SQLException
/*       */   {
/* 13405 */     String str = paramString.intern();
/* 13406 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13407 */     int i = 0;
/* 13408 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13410 */     for (int k = 0; k < j; k++) {
/* 13411 */       if (arrayOfString[k] == str)
/*       */       {
/* 13413 */         setNUMBER(k + 1, paramNUMBER);
/*       */         
/* 13415 */         i = 1;
/*       */       }
/*       */     }
/* 13418 */     if (i == 0)
/*       */     {
/* 13420 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13421 */       localSQLException.fillInStackTrace();
/* 13422 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setOPAQUEAtName(String paramString, OPAQUE paramOPAQUE)
/*       */     throws SQLException
/*       */   {
/* 13442 */     String str = paramString.intern();
/* 13443 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13444 */     int i = 0;
/* 13445 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13447 */     for (int k = 0; k < j; k++) {
/* 13448 */       if (arrayOfString[k] == str)
/*       */       {
/* 13450 */         setOPAQUE(k + 1, paramOPAQUE);
/*       */         
/* 13452 */         i = 1;
/*       */       }
/*       */     }
/* 13455 */     if (i == 0)
/*       */     {
/* 13457 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13458 */       localSQLException.fillInStackTrace();
/* 13459 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setOracleObjectAtName(String paramString, Datum paramDatum)
/*       */     throws SQLException
/*       */   {
/* 13479 */     String str = paramString.intern();
/* 13480 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13481 */     int i = 0;
/* 13482 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13484 */     for (int k = 0; k < j; k++) {
/* 13485 */       if (arrayOfString[k] == str)
/*       */       {
/* 13487 */         setOracleObject(k + 1, paramDatum);
/*       */         
/* 13489 */         i = 1;
/*       */       }
/*       */     }
/* 13492 */     if (i == 0)
/*       */     {
/* 13494 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13495 */       localSQLException.fillInStackTrace();
/* 13496 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setORADataAtName(String paramString, ORAData paramORAData)
/*       */     throws SQLException
/*       */   {
/* 13516 */     String str = paramString.intern();
/* 13517 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13518 */     int i = 0;
/* 13519 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13521 */     for (int k = 0; k < j; k++) {
/* 13522 */       if (arrayOfString[k] == str)
/*       */       {
/* 13524 */         setORAData(k + 1, paramORAData);
/*       */         
/* 13526 */         i = 1;
/*       */       }
/*       */     }
/* 13529 */     if (i == 0)
/*       */     {
/* 13531 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13532 */       localSQLException.fillInStackTrace();
/* 13533 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setRAWAtName(String paramString, RAW paramRAW)
/*       */     throws SQLException
/*       */   {
/* 13553 */     String str = paramString.intern();
/* 13554 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13555 */     int i = 0;
/* 13556 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13558 */     for (int k = 0; k < j; k++) {
/* 13559 */       if (arrayOfString[k] == str)
/*       */       {
/* 13561 */         setRAW(k + 1, paramRAW);
/*       */         
/* 13563 */         i = 1;
/*       */       }
/*       */     }
/* 13566 */     if (i == 0)
/*       */     {
/* 13568 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13569 */       localSQLException.fillInStackTrace();
/* 13570 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setREFAtName(String paramString, REF paramREF)
/*       */     throws SQLException
/*       */   {
/* 13590 */     String str = paramString.intern();
/* 13591 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13592 */     int i = 0;
/* 13593 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13595 */     for (int k = 0; k < j; k++) {
/* 13596 */       if (arrayOfString[k] == str)
/*       */       {
/* 13598 */         setREF(k + 1, paramREF);
/*       */         
/* 13600 */         i = 1;
/*       */       }
/*       */     }
/* 13603 */     if (i == 0)
/*       */     {
/* 13605 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13606 */       localSQLException.fillInStackTrace();
/* 13607 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setRefTypeAtName(String paramString, REF paramREF)
/*       */     throws SQLException
/*       */   {
/* 13627 */     String str = paramString.intern();
/* 13628 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13629 */     int i = 0;
/* 13630 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13632 */     for (int k = 0; k < j; k++) {
/* 13633 */       if (arrayOfString[k] == str)
/*       */       {
/* 13635 */         setRefType(k + 1, paramREF);
/*       */         
/* 13637 */         i = 1;
/*       */       }
/*       */     }
/* 13640 */     if (i == 0)
/*       */     {
/* 13642 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13643 */       localSQLException.fillInStackTrace();
/* 13644 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setROWIDAtName(String paramString, ROWID paramROWID)
/*       */     throws SQLException
/*       */   {
/* 13664 */     String str = paramString.intern();
/* 13665 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13666 */     int i = 0;
/* 13667 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13669 */     for (int k = 0; k < j; k++) {
/* 13670 */       if (arrayOfString[k] == str)
/*       */       {
/* 13672 */         setROWID(k + 1, paramROWID);
/*       */         
/* 13674 */         i = 1;
/*       */       }
/*       */     }
/* 13677 */     if (i == 0)
/*       */     {
/* 13679 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13680 */       localSQLException.fillInStackTrace();
/* 13681 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setSTRUCTAtName(String paramString, STRUCT paramSTRUCT)
/*       */     throws SQLException
/*       */   {
/* 13701 */     String str = paramString.intern();
/* 13702 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13703 */     int i = 0;
/* 13704 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13706 */     for (int k = 0; k < j; k++) {
/* 13707 */       if (arrayOfString[k] == str)
/*       */       {
/* 13709 */         setSTRUCT(k + 1, paramSTRUCT);
/*       */         
/* 13711 */         i = 1;
/*       */       }
/*       */     }
/* 13714 */     if (i == 0)
/*       */     {
/* 13716 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13717 */       localSQLException.fillInStackTrace();
/* 13718 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTIMESTAMPLTZAtName(String paramString, TIMESTAMPLTZ paramTIMESTAMPLTZ)
/*       */     throws SQLException
/*       */   {
/* 13738 */     String str = paramString.intern();
/* 13739 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13740 */     int i = 0;
/* 13741 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13743 */     for (int k = 0; k < j; k++) {
/* 13744 */       if (arrayOfString[k] == str)
/*       */       {
/* 13746 */         setTIMESTAMPLTZ(k + 1, paramTIMESTAMPLTZ);
/*       */         
/* 13748 */         i = 1;
/*       */       }
/*       */     }
/* 13751 */     if (i == 0)
/*       */     {
/* 13753 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13754 */       localSQLException.fillInStackTrace();
/* 13755 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTIMESTAMPTZAtName(String paramString, TIMESTAMPTZ paramTIMESTAMPTZ)
/*       */     throws SQLException
/*       */   {
/* 13775 */     String str = paramString.intern();
/* 13776 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13777 */     int i = 0;
/* 13778 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13780 */     for (int k = 0; k < j; k++) {
/* 13781 */       if (arrayOfString[k] == str)
/*       */       {
/* 13783 */         setTIMESTAMPTZ(k + 1, paramTIMESTAMPTZ);
/*       */         
/* 13785 */         i = 1;
/*       */       }
/*       */     }
/* 13788 */     if (i == 0)
/*       */     {
/* 13790 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13791 */       localSQLException.fillInStackTrace();
/* 13792 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setTIMESTAMPAtName(String paramString, TIMESTAMP paramTIMESTAMP)
/*       */     throws SQLException
/*       */   {
/* 13812 */     String str = paramString.intern();
/* 13813 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13814 */     int i = 0;
/* 13815 */     int j = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/*       */     
/* 13817 */     for (int k = 0; k < j; k++) {
/* 13818 */       if (arrayOfString[k] == str)
/*       */       {
/* 13820 */         setTIMESTAMP(k + 1, paramTIMESTAMP);
/*       */         
/* 13822 */         i = 1;
/*       */       }
/*       */     }
/* 13825 */     if (i == 0)
/*       */     {
/* 13827 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13828 */       localSQLException.fillInStackTrace();
/* 13829 */       throw localSQLException;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBlobAtName(String paramString, InputStream paramInputStream)
/*       */     throws SQLException
/*       */   {
/* 13851 */     String str = paramString.intern();
/* 13852 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13853 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 13854 */     int j = 1;
/*       */     
/* 13856 */     for (int k = 0; k < i; k++)
/*       */     {
/* 13858 */       if (arrayOfString[k] == str)
/*       */       {
/* 13860 */         if (j != 0)
/*       */         {
/* 13862 */           setBlob(k + 1, paramInputStream);
/*       */           
/* 13864 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 13869 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 13870 */           localSQLException2.fillInStackTrace();
/* 13871 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 13876 */     if (j != 0)
/*       */     {
/* 13878 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13879 */       localSQLException1.fillInStackTrace();
/* 13880 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBlobAtName(String paramString, InputStream paramInputStream, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 13900 */     String str = paramString.intern();
/* 13901 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13902 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 13903 */     int j = 1;
/*       */     
/* 13905 */     for (int k = 0; k < i; k++)
/*       */     {
/* 13907 */       if (arrayOfString[k] == str)
/*       */       {
/* 13909 */         if (j != 0)
/*       */         {
/* 13911 */           setBlob(k + 1, paramInputStream, paramLong);
/*       */           
/* 13913 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 13918 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 13919 */           localSQLException2.fillInStackTrace();
/* 13920 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 13925 */     if (j != 0)
/*       */     {
/* 13927 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13928 */       localSQLException1.fillInStackTrace();
/* 13929 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setClobAtName(String paramString, Reader paramReader)
/*       */     throws SQLException
/*       */   {
/* 13949 */     String str = paramString.intern();
/* 13950 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 13951 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 13952 */     int j = 1;
/*       */     
/* 13954 */     for (int k = 0; k < i; k++)
/*       */     {
/* 13956 */       if (arrayOfString[k] == str)
/*       */       {
/* 13958 */         if (j != 0)
/*       */         {
/* 13960 */           setClob(k + 1, paramReader);
/*       */           
/* 13962 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 13967 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 13968 */           localSQLException2.fillInStackTrace();
/* 13969 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 13974 */     if (j != 0)
/*       */     {
/* 13976 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 13977 */       localSQLException1.fillInStackTrace();
/* 13978 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setClobAtName(String paramString, Reader paramReader, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 13998 */     String str = paramString.intern();
/* 13999 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14000 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14001 */     int j = 1;
/*       */     
/* 14003 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14005 */       if (arrayOfString[k] == str)
/*       */       {
/* 14007 */         if (j != 0)
/*       */         {
/* 14009 */           setClob(k + 1, paramReader, paramLong);
/*       */           
/* 14011 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14016 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14017 */           localSQLException2.fillInStackTrace();
/* 14018 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14023 */     if (j != 0)
/*       */     {
/* 14025 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14026 */       localSQLException1.fillInStackTrace();
/* 14027 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNClobAtName(String paramString, Reader paramReader)
/*       */     throws SQLException
/*       */   {
/* 14047 */     String str = paramString.intern();
/* 14048 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14049 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14050 */     int j = 1;
/*       */     
/* 14052 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14054 */       if (arrayOfString[k] == str)
/*       */       {
/* 14056 */         if (j != 0)
/*       */         {
/* 14058 */           setNClob(k + 1, paramReader);
/*       */           
/* 14060 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14065 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14066 */           localSQLException2.fillInStackTrace();
/* 14067 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14072 */     if (j != 0)
/*       */     {
/* 14074 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14075 */       localSQLException1.fillInStackTrace();
/* 14076 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNClobAtName(String paramString, Reader paramReader, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 14096 */     String str = paramString.intern();
/* 14097 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14098 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14099 */     int j = 1;
/*       */     
/* 14101 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14103 */       if (arrayOfString[k] == str)
/*       */       {
/* 14105 */         if (j != 0)
/*       */         {
/* 14107 */           setNClob(k + 1, paramReader, paramLong);
/*       */           
/* 14109 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14114 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14115 */           localSQLException2.fillInStackTrace();
/* 14116 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14121 */     if (j != 0)
/*       */     {
/* 14123 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14124 */       localSQLException1.fillInStackTrace();
/* 14125 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setAsciiStreamAtName(String paramString, InputStream paramInputStream)
/*       */     throws SQLException
/*       */   {
/* 14145 */     String str = paramString.intern();
/* 14146 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14147 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14148 */     int j = 1;
/*       */     
/* 14150 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14152 */       if (arrayOfString[k] == str)
/*       */       {
/* 14154 */         if (j != 0)
/*       */         {
/* 14156 */           setAsciiStream(k + 1, paramInputStream);
/*       */           
/* 14158 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14163 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14164 */           localSQLException2.fillInStackTrace();
/* 14165 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14170 */     if (j != 0)
/*       */     {
/* 14172 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14173 */       localSQLException1.fillInStackTrace();
/* 14174 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setAsciiStreamAtName(String paramString, InputStream paramInputStream, int paramInt)
/*       */     throws SQLException
/*       */   {
/* 14194 */     String str = paramString.intern();
/* 14195 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14196 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14197 */     int j = 1;
/*       */     
/* 14199 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14201 */       if (arrayOfString[k] == str)
/*       */       {
/* 14203 */         if (j != 0)
/*       */         {
/* 14205 */           setAsciiStream(k + 1, paramInputStream, paramInt);
/*       */           
/* 14207 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14212 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14213 */           localSQLException2.fillInStackTrace();
/* 14214 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14219 */     if (j != 0)
/*       */     {
/* 14221 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14222 */       localSQLException1.fillInStackTrace();
/* 14223 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setAsciiStreamAtName(String paramString, InputStream paramInputStream, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 14243 */     String str = paramString.intern();
/* 14244 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14245 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14246 */     int j = 1;
/*       */     
/* 14248 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14250 */       if (arrayOfString[k] == str)
/*       */       {
/* 14252 */         if (j != 0)
/*       */         {
/* 14254 */           setAsciiStream(k + 1, paramInputStream, paramLong);
/*       */           
/* 14256 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14261 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14262 */           localSQLException2.fillInStackTrace();
/* 14263 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14268 */     if (j != 0)
/*       */     {
/* 14270 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14271 */       localSQLException1.fillInStackTrace();
/* 14272 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBinaryStreamAtName(String paramString, InputStream paramInputStream)
/*       */     throws SQLException
/*       */   {
/* 14292 */     String str = paramString.intern();
/* 14293 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14294 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14295 */     int j = 1;
/*       */     
/* 14297 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14299 */       if (arrayOfString[k] == str)
/*       */       {
/* 14301 */         if (j != 0)
/*       */         {
/* 14303 */           setBinaryStream(k + 1, paramInputStream);
/*       */           
/* 14305 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14310 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14311 */           localSQLException2.fillInStackTrace();
/* 14312 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14317 */     if (j != 0)
/*       */     {
/* 14319 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14320 */       localSQLException1.fillInStackTrace();
/* 14321 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBinaryStreamAtName(String paramString, InputStream paramInputStream, int paramInt)
/*       */     throws SQLException
/*       */   {
/* 14341 */     String str = paramString.intern();
/* 14342 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14343 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14344 */     int j = 1;
/*       */     
/* 14346 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14348 */       if (arrayOfString[k] == str)
/*       */       {
/* 14350 */         if (j != 0)
/*       */         {
/* 14352 */           setBinaryStream(k + 1, paramInputStream, paramInt);
/*       */           
/* 14354 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14359 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14360 */           localSQLException2.fillInStackTrace();
/* 14361 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14366 */     if (j != 0)
/*       */     {
/* 14368 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14369 */       localSQLException1.fillInStackTrace();
/* 14370 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setBinaryStreamAtName(String paramString, InputStream paramInputStream, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 14390 */     String str = paramString.intern();
/* 14391 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14392 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14393 */     int j = 1;
/*       */     
/* 14395 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14397 */       if (arrayOfString[k] == str)
/*       */       {
/* 14399 */         if (j != 0)
/*       */         {
/* 14401 */           setBinaryStream(k + 1, paramInputStream, paramLong);
/*       */           
/* 14403 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14408 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14409 */           localSQLException2.fillInStackTrace();
/* 14410 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14415 */     if (j != 0)
/*       */     {
/* 14417 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14418 */       localSQLException1.fillInStackTrace();
/* 14419 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCharacterStreamAtName(String paramString, Reader paramReader)
/*       */     throws SQLException
/*       */   {
/* 14439 */     String str = paramString.intern();
/* 14440 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14441 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14442 */     int j = 1;
/*       */     
/* 14444 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14446 */       if (arrayOfString[k] == str)
/*       */       {
/* 14448 */         if (j != 0)
/*       */         {
/* 14450 */           setCharacterStream(k + 1, paramReader);
/*       */           
/* 14452 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14457 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14458 */           localSQLException2.fillInStackTrace();
/* 14459 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14464 */     if (j != 0)
/*       */     {
/* 14466 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14467 */       localSQLException1.fillInStackTrace();
/* 14468 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCharacterStreamAtName(String paramString, Reader paramReader, int paramInt)
/*       */     throws SQLException
/*       */   {
/* 14488 */     String str = paramString.intern();
/* 14489 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14490 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14491 */     int j = 1;
/*       */     
/* 14493 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14495 */       if (arrayOfString[k] == str)
/*       */       {
/* 14497 */         if (j != 0)
/*       */         {
/* 14499 */           setCharacterStream(k + 1, paramReader, paramInt);
/*       */           
/* 14501 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14506 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14507 */           localSQLException2.fillInStackTrace();
/* 14508 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14513 */     if (j != 0)
/*       */     {
/* 14515 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14516 */       localSQLException1.fillInStackTrace();
/* 14517 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setCharacterStreamAtName(String paramString, Reader paramReader, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 14537 */     String str = paramString.intern();
/* 14538 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14539 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14540 */     int j = 1;
/*       */     
/* 14542 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14544 */       if (arrayOfString[k] == str)
/*       */       {
/* 14546 */         if (j != 0)
/*       */         {
/* 14548 */           setCharacterStream(k + 1, paramReader, paramLong);
/*       */           
/* 14550 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14555 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14556 */           localSQLException2.fillInStackTrace();
/* 14557 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14562 */     if (j != 0)
/*       */     {
/* 14564 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14565 */       localSQLException1.fillInStackTrace();
/* 14566 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNCharacterStreamAtName(String paramString, Reader paramReader)
/*       */     throws SQLException
/*       */   {
/* 14586 */     String str = paramString.intern();
/* 14587 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14588 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14589 */     int j = 1;
/*       */     
/* 14591 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14593 */       if (arrayOfString[k] == str)
/*       */       {
/* 14595 */         if (j != 0)
/*       */         {
/* 14597 */           setNCharacterStream(k + 1, paramReader);
/*       */           
/* 14599 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14604 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14605 */           localSQLException2.fillInStackTrace();
/* 14606 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14611 */     if (j != 0)
/*       */     {
/* 14613 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14614 */       localSQLException1.fillInStackTrace();
/* 14615 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setNCharacterStreamAtName(String paramString, Reader paramReader, long paramLong)
/*       */     throws SQLException
/*       */   {
/* 14635 */     String str = paramString.intern();
/* 14636 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14637 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14638 */     int j = 1;
/*       */     
/* 14640 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14642 */       if (arrayOfString[k] == str)
/*       */       {
/* 14644 */         if (j != 0)
/*       */         {
/* 14646 */           setNCharacterStream(k + 1, paramReader, paramLong);
/*       */           
/* 14648 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14653 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14654 */           localSQLException2.fillInStackTrace();
/* 14655 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14660 */     if (j != 0)
/*       */     {
/* 14662 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14663 */       localSQLException1.fillInStackTrace();
/* 14664 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */   public void setUnicodeStreamAtName(String paramString, InputStream paramInputStream, int paramInt)
/*       */     throws SQLException
/*       */   {
/* 14684 */     String str = paramString.intern();
/* 14685 */     String[] arrayOfString = this.sqlObject.getParameterList();
/* 14686 */     int i = Math.min(this.sqlObject.getParameterCount(), arrayOfString.length);
/* 14687 */     int j = 1;
/*       */     
/* 14689 */     for (int k = 0; k < i; k++)
/*       */     {
/* 14691 */       if (arrayOfString[k] == str)
/*       */       {
/* 14693 */         if (j != 0)
/*       */         {
/* 14695 */           setUnicodeStream(k + 1, paramInputStream, paramInt);
/*       */           
/* 14697 */           j = 0;
/*       */ 
/*       */         }
/*       */         else
/*       */         {
/* 14702 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 135);
/* 14703 */           localSQLException2.fillInStackTrace();
/* 14704 */           throw localSQLException2;
/*       */         }
/*       */       }
/*       */     }
/*       */     
/* 14709 */     if (j != 0)
/*       */     {
/* 14711 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 14712 */       localSQLException1.fillInStackTrace();
/* 14713 */       throw localSQLException1;
/*       */     }
/*       */   }
/*       */   
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/*       */ 
/* 14722 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*       */   public static final boolean TRACE = false;
/*       */   
/*       */   class PushedBatch
/*       */   {
/*       */     int[] currentBatchCharLens;
/*       */     int[] lastBoundCharLens;
/*       */     Accessor[] currentBatchBindAccessors;
/*       */     boolean lastBoundNeeded;
/*       */     boolean need_to_parse;
/*       */     boolean current_batch_need_to_prepare_binds;
/*       */     int first_row_in_batch;
/*       */     int number_of_rows_to_be_bound;
/*       */     PushedBatch next;
/*       */     
/*       */     PushedBatch() {}
/*       */   }
/*       */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OraclePreparedStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */