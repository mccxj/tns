/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.sql.BatchUpdateException;
/*      */ import java.sql.Connection;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.Statement;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Iterator;
/*      */ import java.util.Locale;
/*      */ import java.util.TimeZone;
/*      */ import java.util.Vector;
/*      */ import oracle.jdbc.dcn.DatabaseChangeRegistration;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.internal.OracleStatement.SqlKind;
/*      */ import oracle.sql.BLOB;
/*      */ import oracle.sql.CLOB;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class OracleStatement
/*      */   implements oracle.jdbc.internal.OracleStatement, ScrollRsetStatement
/*      */ {
/*      */   static final int PLAIN_STMT = 0;
/*      */   static final int PREP_STMT = 1;
/*      */   static final int CALL_STMT = 2;
/*      */   int cursorId;
/*      */   int numberOfDefinePositions;
/*      */   int definesBatchSize;
/*      */   Accessor[] accessors;
/*      */   int defineByteSubRange;
/*      */   int defineCharSubRange;
/*      */   int defineIndicatorSubRange;
/*      */   int defineLengthSubRange;
/*      */   byte[] defineBytes;
/*      */   char[] defineChars;
/*      */   short[] defineIndicators;
/*      */   
/*      */   abstract void doDescribe(boolean paramBoolean)
/*      */     throws SQLException;
/*      */   
/*      */   abstract void executeForDescribe()
/*      */     throws SQLException;
/*      */   
/*      */   abstract void executeForRows(boolean paramBoolean)
/*      */     throws SQLException;
/*      */   
/*      */   abstract void fetch()
/*      */     throws SQLException;
/*      */   
/*      */   void continueReadRow(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  292 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "continueReadRow is only implemented by the T4C statements.");
/*  293 */     localSQLException.fillInStackTrace();
/*  294 */     throw localSQLException;
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
/*      */   abstract void doClose()
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
/*      */   abstract void closeQuery()
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
/*      */   public int cursorIfRefCursor()
/*      */     throws SQLException
/*      */   {
/*  330 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "cursorIfRefCursor not implemented");
/*  331 */     localSQLException.fillInStackTrace();
/*  332 */     throw localSQLException;
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
/*      */   void closeCursorOnPlainStatement()
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
/*      */ 
/*      */ 
/*      */ 
/*  365 */   boolean described = false;
/*  366 */   boolean describedWithNames = false;
/*      */   
/*      */ 
/*      */   byte[] defineMetaData;
/*      */   
/*      */ 
/*      */   int defineMetaDataSubRange;
/*      */   
/*      */ 
/*      */   static final int METADATALENGTH = 1;
/*      */   
/*      */ 
/*      */   int rowsProcessed;
/*      */   
/*      */ 
/*  381 */   int cachedDefineByteSize = 0;
/*  382 */   int cachedDefineCharSize = 0;
/*  383 */   int cachedDefineIndicatorSize = 0;
/*  384 */   int cachedDefineMetaDataSize = 0;
/*      */   
/*      */ 
/*  387 */   OracleStatement children = null;
/*  388 */   OracleStatement parent = null;
/*      */   
/*      */ 
/*  391 */   OracleStatement nextChild = null;
/*      */   
/*      */ 
/*      */   OracleStatement next;
/*      */   
/*      */ 
/*      */   OracleStatement prev;
/*      */   
/*      */ 
/*      */   long c_state;
/*      */   
/*      */ 
/*      */   int numberOfBindPositions;
/*      */   
/*      */ 
/*      */   byte[] bindBytes;
/*      */   
/*      */ 
/*      */   char[] bindChars;
/*      */   
/*      */ 
/*      */   short[] bindIndicators;
/*      */   
/*      */   int bindByteOffset;
/*      */   
/*      */   int bindCharOffset;
/*      */   
/*      */   int bindIndicatorOffset;
/*      */   
/*      */   int bindByteSubRange;
/*      */   
/*      */   int bindCharSubRange;
/*      */   
/*      */   int bindIndicatorSubRange;
/*      */   
/*      */   Accessor[] outBindAccessors;
/*      */   
/*      */   InputStream[][] parameterStream;
/*      */   
/*      */   Object[][] userStream;
/*      */   
/*      */   int firstRowInBatch;
/*      */   
/*  434 */   boolean hasIbtBind = false;
/*      */   
/*      */ 
/*      */   byte[] ibtBindBytes;
/*      */   
/*      */ 
/*      */   char[] ibtBindChars;
/*      */   
/*      */ 
/*      */   short[] ibtBindIndicators;
/*      */   
/*      */ 
/*      */   int ibtBindByteOffset;
/*      */   
/*      */ 
/*      */   int ibtBindCharOffset;
/*      */   
/*      */ 
/*      */   int ibtBindIndicatorOffset;
/*      */   
/*      */ 
/*      */   int ibtBindIndicatorSize;
/*      */   
/*  457 */   ByteBuffer[] nioBuffers = null;
/*  458 */   Object[] lobPrefetchMetaData = null;
/*      */   
/*      */ 
/*      */ 
/*      */   boolean hasStream;
/*      */   
/*      */ 
/*      */ 
/*      */   byte[] tmpByteArray;
/*      */   
/*      */ 
/*  469 */   int sizeTmpByteArray = 0;
/*      */   
/*      */ 
/*      */ 
/*      */   byte[] tmpBindsByteArray;
/*      */   
/*      */ 
/*      */ 
/*  477 */   boolean needToSendOalToFetch = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  484 */   int[] definedColumnType = null;
/*  485 */   int[] definedColumnSize = null;
/*  486 */   int[] definedColumnFormOfUse = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  492 */   T4CTTIoac[] oacdefSent = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  499 */   int[] nbPostPonedColumns = null;
/*  500 */   int[][] indexOfPostPonedColumn = (int[][])null;
/*      */   
/*      */ 
/*      */ 
/*  504 */   boolean aFetchWasDoneDuringDescribe = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  511 */   boolean implicitDefineForLobPrefetchDone = false;
/*      */   
/*  513 */   long checkSum = 0L;
/*  514 */   boolean checkSumComputationFailure = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  536 */   int accessorByteOffset = 0;
/*  537 */   int accessorCharOffset = 0;
/*  538 */   int accessorShortOffset = 0;
/*      */   
/*      */ 
/*      */ 
/*      */   static final int VALID_ROWS_UNINIT = -999;
/*      */   
/*      */ 
/*      */ 
/*      */   PhysicalConnection connection;
/*      */   
/*      */ 
/*      */   OracleInputStream streamList;
/*      */   
/*      */ 
/*      */   OracleInputStream nextStream;
/*      */   
/*      */ 
/*      */   OracleResultSetImpl currentResultSet;
/*      */   
/*      */ 
/*      */   boolean processEscapes;
/*      */   
/*      */ 
/*      */   boolean convertNcharLiterals;
/*      */   
/*      */ 
/*      */   int queryTimeout;
/*      */   
/*      */ 
/*      */   int batch;
/*      */   
/*      */ 
/*  570 */   int numberOfExecutedElementsInBatch = -1;
/*      */   int currentRank;
/*  572 */   boolean bsendBatchInProgress = false;
/*      */   
/*      */ 
/*      */   int currentRow;
/*      */   
/*      */   int validRows;
/*      */   
/*      */   int maxFieldSize;
/*      */   
/*      */   int maxRows;
/*      */   
/*      */   int totalRowsVisited;
/*      */   
/*      */   int rowPrefetch;
/*      */   
/*  587 */   int rowPrefetchInLastFetch = -1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   int defaultRowPrefetch;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean rowPrefetchChanged;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   int defaultLobPrefetchSize;
/*      */   
/*      */ 
/*      */ 
/*      */   boolean gotLastBatch;
/*      */   
/*      */ 
/*      */ 
/*      */   boolean clearParameters;
/*      */   
/*      */ 
/*      */ 
/*      */   boolean closed;
/*      */   
/*      */ 
/*      */ 
/*      */   boolean sqlStringChanged;
/*      */   
/*      */ 
/*      */ 
/*      */   OracleSql sqlObject;
/*      */   
/*      */ 
/*      */ 
/*      */   boolean needToParse;
/*      */   
/*      */ 
/*      */ 
/*      */   boolean needToPrepareDefineBuffer;
/*      */   
/*      */ 
/*      */ 
/*      */   boolean columnsDefinedByUser;
/*      */   
/*      */ 
/*      */ 
/*  638 */   OracleStatement.SqlKind sqlKind = OracleStatement.SqlKind.SELECT;
/*  639 */   byte sqlKindByte = 1;
/*      */   
/*      */ 
/*      */ 
/*      */   int autoRollback;
/*      */   
/*      */ 
/*      */ 
/*      */   int defaultFetchDirection;
/*      */   
/*      */ 
/*      */ 
/*      */   boolean serverCursor;
/*      */   
/*      */ 
/*      */ 
/*  655 */   boolean fixedString = false;
/*      */   
/*      */ 
/*  658 */   boolean noMoreUpdateCounts = false;
/*      */   
/*  660 */   protected CancelLock cancelLock = new CancelLock();
/*      */   
/*      */   OracleStatementWrapper wrapper;
/*      */   
/*      */   static final byte EXECUTE_NONE = -1;
/*      */   
/*      */   static final byte EXECUTE_QUERY = 1;
/*      */   
/*      */   static final byte EXECUTE_UPDATE = 2;
/*      */   
/*      */   static final byte EXECUTE_NORMAL = 3;
/*  671 */   byte executionType = -1;
/*      */   
/*      */ 
/*      */   OracleResultSet scrollRset;
/*      */   
/*      */ 
/*      */   oracle.jdbc.OracleResultSetCache rsetCache;
/*      */   
/*      */   int userRsetType;
/*      */   
/*      */   int realRsetType;
/*      */   
/*      */   boolean needToAddIdentifier;
/*      */   
/*      */   SQLWarning sqlWarning;
/*      */   
/*  687 */   int cacheState = 3;
/*      */   
/*      */ 
/*  690 */   int creationState = 0;
/*      */   
/*  692 */   boolean isOpen = false;
/*      */   
/*      */ 
/*      */ 
/*  696 */   int statementType = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  702 */   boolean columnSetNull = false;
/*      */   
/*      */   int[] returnParamMeta;
/*      */   
/*      */   static final int DMLR_METADATA_PREFIX_SIZE = 3;
/*      */   
/*      */   static final int DMLR_METADATA_NUM_OF_RETURN_PARAMS = 0;
/*      */   
/*      */   static final int DMLR_METADATA_ROW_BIND_BYTES = 1;
/*      */   
/*      */   static final int DMLR_METADATA_ROW_BIND_CHARS = 2;
/*      */   
/*      */   static final int DMLR_METADATA_TYPE_OFFSET = 0;
/*      */   
/*      */   static final int DMLR_METADATA_IS_CHAR_TYPE_OFFSET = 1;
/*      */   
/*      */   static final int DMLR_METADATA_BIND_SIZE_OFFSET = 2;
/*      */   
/*      */   static final int DMLR_METADATA_FORM_OF_USE_OFFSET = 3;
/*      */   
/*      */   static final int DMLR_METADATA_PER_POSITION_SIZE = 4;
/*      */   
/*      */   Accessor[] returnParamAccessors;
/*      */   
/*      */   boolean returnParamsFetched;
/*      */   
/*      */   int rowsDmlReturned;
/*      */   
/*      */   int numReturnParams;
/*      */   
/*      */   byte[] returnParamBytes;
/*      */   char[] returnParamChars;
/*      */   short[] returnParamIndicators;
/*      */   int returnParamRowBytes;
/*      */   int returnParamRowChars;
/*      */   OracleReturnResultSet returnResultSet;
/*      */   boolean isAutoGeneratedKey;
/*      */   AutoKeyInfo autoKeyInfo;
/*  740 */   TimeZone defaultTimeZone = null;
/*  741 */   String defaultTimeZoneName = null;
/*      */   
/*  743 */   Calendar defaultCalendar = null;
/*  744 */   Calendar gmtCalendar = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  756 */   long inScn = 0L;
/*      */   
/*      */   public void setSnapshotSCN(long paramLong) throws SQLException
/*      */   {
/*  760 */     doSetSnapshotSCN(paramLong); }
/*      */   
/*      */ 
/*      */   void doSetSnapshotSCN(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  766 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  767 */     localSQLException.fillInStackTrace();
/*  768 */     throw localSQLException;
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
/*      */   OracleStatement(PhysicalConnection paramPhysicalConnection, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  790 */     this(paramPhysicalConnection, paramInt1, paramInt2, -1, -1);
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
/*      */   OracleStatement(PhysicalConnection paramPhysicalConnection, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/*  805 */     this.connection = paramPhysicalConnection;
/*      */     
/*  807 */     this.connection.needLine();
/*      */     
/*      */ 
/*      */ 
/*  811 */     this.connection.registerHeartbeat();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  816 */     this.connection.addStatement(this);
/*      */     
/*  818 */     this.sqlObject = new OracleSql(this.connection.conversion);
/*      */     
/*      */ 
/*      */ 
/*  822 */     this.processEscapes = this.connection.processEscapes;
/*  823 */     this.convertNcharLiterals = this.connection.convertNcharLiterals;
/*  824 */     this.autoRollback = 2;
/*  825 */     this.gotLastBatch = false;
/*  826 */     this.closed = false;
/*  827 */     this.clearParameters = true;
/*  828 */     this.serverCursor = false;
/*  829 */     this.needToAddIdentifier = false;
/*  830 */     this.defaultFetchDirection = 1000;
/*  831 */     this.fixedString = this.connection.getDefaultFixedString();
/*  832 */     this.rowPrefetchChanged = false;
/*  833 */     this.rowPrefetch = paramInt2;
/*  834 */     this.defaultRowPrefetch = paramInt2;
/*  835 */     if (this.connection.getVersionNumber() >= 11000) {
/*  836 */       this.defaultLobPrefetchSize = this.connection.defaultLobPrefetchSize;
/*      */     } else
/*  838 */       this.defaultLobPrefetchSize = -1;
/*  839 */     this.batch = paramInt1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  845 */     this.sqlStringChanged = true;
/*  846 */     this.needToParse = true;
/*  847 */     this.needToPrepareDefineBuffer = true;
/*  848 */     this.columnsDefinedByUser = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  856 */     if ((paramInt3 != -1) || (paramInt4 != -1))
/*      */     {
/*  858 */       this.realRsetType = 0;
/*  859 */       this.userRsetType = ResultSetUtil.getRsetTypeCode(paramInt3, paramInt4);
/*      */       
/*      */ 
/*  862 */       this.needToAddIdentifier = ResultSetUtil.needIdentifier(this.userRsetType);
/*      */     }
/*      */     else
/*      */     {
/*  866 */       this.userRsetType = 1;
/*  867 */       this.realRsetType = 1;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void initializeDefineSubRanges()
/*      */   {
/*  890 */     this.defineByteSubRange = 0;
/*  891 */     this.defineCharSubRange = 0;
/*  892 */     this.defineIndicatorSubRange = 0;
/*  893 */     this.defineMetaDataSubRange = 0;
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
/*      */   void prepareDefinePreambles() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void prepareAccessors()
/*      */     throws SQLException
/*      */   {
/*  926 */     byte[] arrayOfByte1 = null;
/*  927 */     char[] arrayOfChar = null;
/*  928 */     short[] arrayOfShort = null;
/*  929 */     boolean bool = false;
/*  930 */     byte[] arrayOfByte2 = null;
/*      */     
/*  932 */     if (this.accessors == null)
/*      */     {
/*  934 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  935 */       localSQLException1.fillInStackTrace();
/*  936 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  942 */     int i = 0;
/*  943 */     int j = 0;
/*  944 */     int k = 0;
/*  945 */     Accessor localAccessor; for (int m = 0; m < this.numberOfDefinePositions; m++)
/*      */     {
/*  947 */       localAccessor = this.accessors[m];
/*      */       
/*  949 */       if (localAccessor == null)
/*      */       {
/*  951 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  952 */         localSQLException2.fillInStackTrace();
/*  953 */         throw localSQLException2;
/*      */       }
/*  955 */       switch (localAccessor.internalType)
/*      */       {
/*      */       case 8: 
/*      */       case 24: 
/*  959 */         this.hasStream = true;
/*      */       }
/*      */       
/*      */       
/*  963 */       i += localAccessor.byteLength;
/*  964 */       j += localAccessor.charLength;
/*  965 */       k += 1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  970 */     if ((this.streamList != null) && (!this.connection.useFetchSizeWithLongColumn)) {
/*  971 */       this.rowPrefetch = 1;
/*      */     }
/*  973 */     m = this.rowPrefetch;
/*      */     
/*  975 */     this.definesBatchSize = m;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  983 */     initializeDefineSubRanges();
/*      */     
/*      */ 
/*  986 */     int n = k * m;
/*  987 */     if ((this.defineMetaData == null) || (this.defineMetaData.length < n))
/*      */     {
/*  989 */       if (this.defineMetaData != null)
/*  990 */         arrayOfByte2 = this.defineMetaData;
/*  991 */       this.defineMetaData = new byte[n];
/*      */     }
/*      */     
/*      */ 
/*  995 */     this.cachedDefineByteSize = (this.defineByteSubRange + i * m);
/*      */     
/*  997 */     if ((this.defineBytes == null) || (this.defineBytes.length < this.cachedDefineByteSize))
/*      */     {
/*  999 */       if (this.defineBytes != null) arrayOfByte1 = this.defineBytes;
/* 1000 */       this.defineBytes = this.connection.getByteBuffer(this.cachedDefineByteSize);
/*      */     }
/*      */     
/* 1003 */     this.defineByteSubRange += this.accessorByteOffset;
/*      */     
/*      */ 
/* 1006 */     this.cachedDefineCharSize = (this.defineCharSubRange + j * m);
/*      */     
/* 1008 */     if (((this.defineChars == null) || (this.defineChars.length < this.cachedDefineCharSize)) && (this.cachedDefineCharSize > 0))
/*      */     {
/*      */ 
/* 1011 */       if (this.defineChars != null) { arrayOfChar = this.defineChars;
/*      */       }
/* 1013 */       this.defineChars = this.connection.getCharBuffer(this.cachedDefineCharSize);
/*      */     }
/*      */     
/* 1016 */     this.defineCharSubRange += this.accessorCharOffset;
/*      */     
/*      */ 
/*      */ 
/* 1020 */     int i1 = this.numberOfDefinePositions * m;
/* 1021 */     int i2 = this.defineIndicatorSubRange + i1 + i1;
/*      */     
/*      */ 
/* 1024 */     if ((this.defineIndicators == null) || (this.defineIndicators.length < i2))
/*      */     {
/*      */ 
/* 1027 */       if (this.defineIndicators != null) arrayOfShort = this.defineIndicators;
/* 1028 */       this.defineIndicators = new short[i2];
/* 1029 */     } else if (this.defineIndicators.length >= i2)
/*      */     {
/* 1031 */       bool = true;
/* 1032 */       arrayOfShort = this.defineIndicators;
/*      */     }
/*      */     
/* 1035 */     this.defineIndicatorSubRange += this.accessorShortOffset;
/*      */     
/* 1037 */     int i3 = this.defineIndicatorSubRange + i1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1043 */     for (int i4 = 0; i4 < this.numberOfDefinePositions; i4++)
/*      */     {
/* 1045 */       localAccessor = this.accessors[i4];
/*      */       
/* 1047 */       localAccessor.lengthIndexLastRow = localAccessor.lengthIndex;
/* 1048 */       localAccessor.indicatorIndexLastRow = localAccessor.indicatorIndex;
/* 1049 */       localAccessor.columnIndexLastRow = localAccessor.columnIndex;
/*      */       
/* 1051 */       localAccessor.setOffsets(m);
/*      */       
/* 1053 */       localAccessor.lengthIndex = i3;
/* 1054 */       localAccessor.indicatorIndex = this.defineIndicatorSubRange;
/* 1055 */       localAccessor.metaDataIndex = this.defineMetaDataSubRange;
/* 1056 */       localAccessor.rowSpaceByte = this.defineBytes;
/* 1057 */       localAccessor.rowSpaceChar = this.defineChars;
/* 1058 */       localAccessor.rowSpaceIndicator = this.defineIndicators;
/* 1059 */       localAccessor.rowSpaceMetaData = this.defineMetaData;
/* 1060 */       this.defineIndicatorSubRange += m;
/* 1061 */       i3 += m;
/* 1062 */       this.defineMetaDataSubRange += m * 1;
/*      */     }
/*      */     
/* 1065 */     prepareDefinePreambles();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1073 */     if ((this.rowPrefetchInLastFetch != -1) && (this.rowPrefetch != this.rowPrefetchInLastFetch))
/*      */     {
/* 1075 */       if (arrayOfChar == null) arrayOfChar = this.defineChars;
/* 1076 */       if (arrayOfByte1 == null) arrayOfByte1 = this.defineBytes;
/* 1077 */       if (arrayOfShort == null) arrayOfShort = this.defineIndicators;
/* 1078 */       saveDefineBuffersIfRequired(arrayOfChar, arrayOfByte1, arrayOfShort, bool);
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
/*      */ 
/*      */   boolean checkAccessorsUsable()
/*      */     throws SQLException
/*      */   {
/* 1094 */     int i = this.accessors.length;
/*      */     
/* 1096 */     if (i < this.numberOfDefinePositions) {
/* 1097 */       return false;
/*      */     }
/* 1099 */     int j = 1;
/* 1100 */     int k = 0;
/* 1101 */     boolean bool = false;
/*      */     
/* 1103 */     for (int m = 0; m < this.numberOfDefinePositions; m++)
/*      */     {
/* 1105 */       Accessor localAccessor = this.accessors[m];
/*      */       
/* 1107 */       if ((localAccessor == null) || (localAccessor.externalType == 0)) {
/* 1108 */         j = 0;
/*      */       } else {
/* 1110 */         k = 1;
/*      */       }
/*      */     }
/* 1113 */     if (j != 0)
/*      */     {
/*      */ 
/* 1116 */       bool = true;
/* 1117 */     } else { if (k != 0)
/*      */       {
/*      */ 
/*      */ 
/* 1121 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 1122 */         localSQLException.fillInStackTrace();
/* 1123 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1129 */       this.columnsDefinedByUser = false;
/*      */     }
/* 1131 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void executeMaybeDescribe()
/*      */     throws SQLException
/*      */   {
/* 1140 */     int i = 1;
/*      */     
/* 1142 */     if (this.rowPrefetchChanged)
/*      */     {
/* 1144 */       if ((this.streamList == null) && (this.rowPrefetch != this.definesBatchSize)) {
/* 1145 */         this.needToPrepareDefineBuffer = true;
/*      */       }
/* 1147 */       this.rowPrefetchChanged = false;
/*      */     }
/*      */     
/* 1150 */     if (!this.needToPrepareDefineBuffer)
/*      */     {
/*      */ 
/*      */ 
/* 1154 */       if (this.accessors == null)
/*      */       {
/*      */ 
/*      */ 
/* 1158 */         this.needToPrepareDefineBuffer = true;
/* 1159 */       } else if (this.columnsDefinedByUser) {
/* 1160 */         this.needToPrepareDefineBuffer = (!checkAccessorsUsable());
/*      */       }
/*      */     }
/* 1163 */     boolean bool = false;
/*      */     
/*      */ 
/*      */     try
/*      */     {
/* 1168 */       this.cancelLock.enterExecuting();
/*      */       
/* 1170 */       if (this.needToPrepareDefineBuffer)
/*      */       {
/*      */ 
/* 1173 */         if (!this.columnsDefinedByUser)
/*      */         {
/* 1175 */           executeForDescribe();
/*      */           
/* 1177 */           bool = true;
/*      */           
/*      */ 
/*      */ 
/* 1181 */           if (this.aFetchWasDoneDuringDescribe) {
/* 1182 */             i = 0;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1188 */         if (this.needToPrepareDefineBuffer)
/*      */         {
/*      */ 
/*      */ 
/* 1192 */           prepareAccessors();
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1197 */       int j = this.accessors.length;
/*      */       
/* 1199 */       for (int k = this.numberOfDefinePositions; k < j; k++)
/*      */       {
/* 1201 */         Accessor localAccessor = this.accessors[k];
/*      */         
/* 1203 */         if (localAccessor != null)
/* 1204 */           localAccessor.rowSpaceIndicator = null;
/*      */       }
/* 1206 */       if (i != 0) {
/* 1207 */         executeForRows(bool);
/*      */       }
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/* 1212 */       this.needToParse = true;
/* 1213 */       throw localSQLException;
/*      */     }
/*      */     finally
/*      */     {
/* 1217 */       this.cancelLock.exitExecuting();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void adjustGotLastBatch() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void doExecuteWithTimeout()
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 1253 */       cleanOldTempLobs();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1260 */       this.connection.registerHeartbeat();
/*      */       
/*      */ 
/* 1263 */       this.rowsProcessed = 0;
/*      */       SQLException localSQLException1;
/* 1265 */       if (this.sqlKind.isSELECT())
/*      */       {
/* 1267 */         if ((this.connection.j2ee13Compliant) && (this.executionType == 2))
/*      */         {
/* 1269 */           localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 129);
/* 1270 */           localSQLException1.fillInStackTrace();
/* 1271 */           throw localSQLException1;
/*      */         }
/*      */         
/* 1274 */         this.connection.needLine();
/*      */         
/* 1276 */         if (!this.isOpen)
/*      */         {
/* 1278 */           this.connection.open(this);
/*      */           
/* 1280 */           this.isOpen = true;
/*      */         }
/*      */         
/* 1283 */         if (this.queryTimeout != 0)
/*      */         {
/*      */           try
/*      */           {
/* 1287 */             this.connection.getTimeout().setTimeout(this.queryTimeout * 1000, this);
/* 1288 */             executeMaybeDescribe();
/*      */           }
/*      */           finally
/*      */           {
/* 1292 */             this.connection.getTimeout().cancelTimeout();
/*      */           }
/*      */           
/*      */         } else {
/* 1296 */           executeMaybeDescribe();
/*      */         }
/* 1298 */         checkValidRowsStatus();
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1303 */         if (this.serverCursor) {
/* 1304 */           adjustGotLastBatch();
/*      */         }
/*      */       }
/*      */       else {
/* 1308 */         if ((this.connection.j2ee13Compliant) && (!this.sqlKind.isPlsqlOrCall()) && (this.executionType == 1))
/*      */         {
/*      */ 
/* 1311 */           localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 128);
/* 1312 */           localSQLException1.fillInStackTrace();
/* 1313 */           throw localSQLException1;
/*      */         }
/*      */         
/* 1316 */         this.currentRank += 1;
/*      */         
/* 1318 */         if (this.currentRank >= this.batch)
/*      */         {
/*      */           try
/*      */           {
/* 1322 */             this.connection.needLine();
/*      */             
/* 1324 */             this.cancelLock.enterExecuting();
/*      */             
/* 1326 */             if (!this.isOpen)
/*      */             {
/* 1328 */               this.connection.open(this);
/*      */               
/* 1330 */               this.isOpen = true;
/*      */             }
/*      */             
/* 1333 */             if (this.queryTimeout != 0) {
/* 1334 */               this.connection.getTimeout().setTimeout(this.queryTimeout * 1000, this);
/*      */             }
/* 1336 */             executeForRows(false);
/*      */           }
/*      */           catch (SQLException localSQLException2)
/*      */           {
/* 1340 */             this.needToParse = true;
/* 1341 */             if (this.batch > 1)
/*      */             {
/* 1343 */               clearBatch();
/*      */               
/*      */               int[] arrayOfInt;
/*      */               
/*      */               int i;
/*      */               
/* 1349 */               if ((this.numberOfExecutedElementsInBatch != -1) && (this.numberOfExecutedElementsInBatch < this.batch))
/*      */               {
/*      */ 
/* 1352 */                 arrayOfInt = new int[this.numberOfExecutedElementsInBatch];
/* 1353 */                 for (i = 0; i < arrayOfInt.length; i++) {
/* 1354 */                   arrayOfInt[i] = -2;
/*      */                 }
/*      */               }
/*      */               else {
/* 1358 */                 arrayOfInt = new int[this.batch];
/* 1359 */                 for (i = 0; i < arrayOfInt.length; i++) {
/* 1360 */                   arrayOfInt[i] = -3;
/*      */                 }
/*      */               }
/* 1363 */               BatchUpdateException localBatchUpdateException = DatabaseError.createBatchUpdateException(localSQLException2, arrayOfInt.length, arrayOfInt);
/* 1364 */               localBatchUpdateException.fillInStackTrace();
/* 1365 */               throw localBatchUpdateException;
/*      */             }
/*      */             
/* 1368 */             resetCurrentRowBinders();
/*      */             
/* 1370 */             throw localSQLException2;
/*      */           }
/*      */           finally
/*      */           {
/* 1374 */             if (this.queryTimeout != 0) {
/* 1375 */               this.connection.getTimeout().cancelTimeout();
/*      */             }
/* 1377 */             this.currentRank = 0;
/* 1378 */             this.cancelLock.exitExecuting();
/*      */             
/* 1380 */             checkValidRowsStatus();
/*      */ 
/*      */ 
/*      */           }
/*      */           
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */     }
/*      */     catch (SQLException localSQLException3)
/*      */     {
/*      */ 
/*      */ 
/* 1398 */       resetOnExceptionDuringExecute();
/* 1399 */       throw localSQLException3;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1409 */     this.connection.registerHeartbeat();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void resetOnExceptionDuringExecute()
/*      */   {
/* 1418 */     this.needToParse = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void resetCurrentRowBinders() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void open()
/*      */     throws SQLException
/*      */   {
/* 1440 */     if (!this.isOpen)
/*      */     {
/* 1442 */       this.connection.needLine();
/* 1443 */       this.connection.open(this);
/*      */       
/* 1445 */       this.isOpen = true;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ResultSet executeQuery(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1464 */     synchronized (this.connection)
/*      */     {
/* 1466 */       Object localObject1 = null;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       try
/*      */       {
/* 1474 */         this.executionType = 1;
/*      */         
/*      */ 
/*      */ 
/* 1478 */         this.noMoreUpdateCounts = false;
/*      */         
/* 1480 */         ensureOpen();
/* 1481 */         checkIfJdbcBatchExists();
/*      */         
/* 1483 */         sendBatch();
/*      */         
/*      */ 
/* 1486 */         this.hasStream = false;
/*      */         
/*      */ 
/* 1489 */         this.sqlObject.initialize(paramString);
/*      */         
/* 1491 */         this.sqlKind = this.sqlObject.getSqlKind();
/* 1492 */         this.needToParse = true;
/*      */         
/* 1494 */         prepareForNewResults(true, true);
/*      */         
/* 1496 */         if (this.userRsetType == 1)
/*      */         {
/* 1498 */           doExecuteWithTimeout();
/*      */           
/* 1500 */           this.currentResultSet = new OracleResultSetImpl(this.connection, this);
/* 1501 */           localObject1 = this.currentResultSet;
/*      */         }
/*      */         else
/*      */         {
/* 1505 */           localObject1 = doScrollStmtExecuteQuery();
/*      */           
/* 1507 */           if (localObject1 == null)
/*      */           {
/* 1509 */             this.currentResultSet = new OracleResultSetImpl(this.connection, this);
/* 1510 */             localObject1 = this.currentResultSet;
/*      */           }
/*      */         }
/*      */       }
/*      */       finally
/*      */       {
/* 1516 */         this.executionType = -1;
/*      */       }
/*      */       
/* 1519 */       return (ResultSet)localObject1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void closeWithKey(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1532 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 1533 */     localSQLException.fillInStackTrace();
/* 1534 */     throw localSQLException;
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
/*      */ 
/*      */   public void close()
/*      */     throws SQLException
/*      */   {
/* 1566 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 1570 */       closeOrCache(null);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected void closeOrCache(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1578 */     if (this.closed) {
/* 1579 */       return;
/*      */     }
/*      */     
/* 1582 */     if (this.connection.lifecycle == 2) {
/* 1583 */       this.connection.needLineUnchecked();
/*      */     } else {
/* 1585 */       this.connection.needLine();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1594 */     if ((this.statementType != 0) && (this.cacheState != 0) && (this.cacheState != 3) && (this.connection.isStatementCacheInitialized()))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1605 */       if (paramString == null)
/*      */       {
/* 1607 */         if (this.connection.getImplicitCachingEnabled())
/*      */         {
/* 1609 */           this.connection.cacheImplicitStatement((OraclePreparedStatement)this, this.sqlObject.getOriginalSql(), this.statementType, this.userRsetType);
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*      */ 
/* 1618 */           this.cacheState = 0;
/*      */           
/* 1620 */           hardClose();
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */       }
/* 1626 */       else if (this.connection.getExplicitCachingEnabled())
/*      */       {
/* 1628 */         this.connection.cacheExplicitStatement((OraclePreparedStatement)this, paramString);
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/* 1636 */         this.cacheState = 0;
/*      */         
/* 1638 */         hardClose();
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1647 */       hardClose();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void hardClose()
/*      */     throws SQLException
/*      */   {
/* 1657 */     hardClose(true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void hardClose(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1668 */     alwaysOnClose();
/*      */     
/* 1670 */     this.describedWithNames = false;
/* 1671 */     this.described = false;
/*      */     
/*      */ 
/* 1674 */     this.connection.removeStatement(this);
/*      */     
/* 1676 */     cleanupDefines();
/*      */     
/* 1678 */     if ((this.isOpen) && (paramBoolean) && ((this.connection.lifecycle == 1) || (this.connection.lifecycle == 16) || (this.connection.lifecycle == 2)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1687 */       this.connection.registerHeartbeat();
/*      */       
/*      */ 
/* 1690 */       doClose();
/*      */       
/* 1692 */       this.isOpen = false;
/*      */     }
/*      */     
/* 1695 */     this.sqlObject = null;
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
/*      */   protected void alwaysOnClose()
/*      */     throws SQLException
/*      */   {
/* 1712 */     Object localObject = this.children;
/*      */     
/* 1714 */     while (localObject != null)
/*      */     {
/* 1716 */       OracleStatement localOracleStatement = ((OracleStatement)localObject).nextChild;
/*      */       
/* 1718 */       ((OracleStatement)localObject).close();
/*      */       
/* 1720 */       localObject = localOracleStatement;
/*      */     }
/*      */     
/* 1723 */     if (this.parent != null)
/*      */     {
/* 1725 */       this.parent.removeChild(this);
/*      */     }
/*      */     
/* 1728 */     this.closed = true;
/*      */     
/*      */ 
/* 1731 */     if ((this.connection.lifecycle == 1) || (this.connection.lifecycle == 2))
/*      */     {
/*      */ 
/*      */ 
/* 1735 */       if (this.currentResultSet != null)
/*      */       {
/* 1737 */         this.currentResultSet.internal_close(false);
/*      */         
/* 1739 */         this.currentResultSet = null;
/*      */       }
/*      */       
/* 1742 */       if (this.scrollRset != null)
/*      */       {
/* 1744 */         this.scrollRset.close();
/*      */         
/* 1746 */         this.scrollRset = null;
/*      */       }
/*      */       
/* 1749 */       if (this.returnResultSet != null)
/*      */       {
/* 1751 */         this.returnResultSet.close();
/* 1752 */         this.returnResultSet = null;
/*      */       }
/*      */     }
/*      */     
/* 1756 */     clearWarnings();
/*      */     
/* 1758 */     this.m_batchItems = null;
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
/*      */   void closeLeaveCursorOpen()
/*      */     throws SQLException
/*      */   {
/* 1774 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1780 */       if (this.closed)
/*      */       {
/* 1782 */         return;
/*      */       }
/*      */       
/* 1785 */       hardClose(false);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int executeUpdate(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1804 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1809 */       setNonAutoKey();
/* 1810 */       return executeUpdateInternal(paramString);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   int executeUpdateInternal(String paramString)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 1821 */       if (this.executionType == -1) {
/* 1822 */         this.executionType = 2;
/*      */       }
/*      */       
/* 1825 */       this.noMoreUpdateCounts = false;
/*      */       
/* 1827 */       ensureOpen();
/* 1828 */       checkIfJdbcBatchExists();
/*      */       
/* 1830 */       sendBatch();
/*      */       
/*      */ 
/* 1833 */       this.hasStream = false;
/*      */       
/*      */ 
/* 1836 */       this.sqlObject.initialize(paramString);
/*      */       
/* 1838 */       this.sqlKind = this.sqlObject.getSqlKind();
/* 1839 */       this.needToParse = true;
/*      */       
/* 1841 */       prepareForNewResults(true, true);
/*      */       
/* 1843 */       if (this.userRsetType == 1)
/*      */       {
/* 1845 */         doExecuteWithTimeout();
/*      */       }
/*      */       else
/*      */       {
/* 1849 */         doScrollStmtExecuteQuery();
/*      */       }
/*      */       
/* 1852 */       return this.validRows;
/*      */     }
/*      */     finally
/*      */     {
/* 1856 */       this.executionType = -1;
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
/*      */ 
/*      */   public boolean execute(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1872 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1877 */       setNonAutoKey();
/* 1878 */       return executeInternal(paramString);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   boolean executeInternal(String paramString)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 1889 */       this.executionType = 3;
/*      */       
/* 1891 */       this.checkSum = 0L;
/* 1892 */       this.checkSumComputationFailure = false;
/*      */       
/*      */ 
/* 1895 */       this.noMoreUpdateCounts = false;
/*      */       
/* 1897 */       ensureOpen();
/* 1898 */       checkIfJdbcBatchExists();
/*      */       
/* 1900 */       sendBatch();
/*      */       
/*      */ 
/*      */ 
/* 1904 */       this.hasStream = false;
/*      */       
/*      */ 
/* 1907 */       this.sqlObject.initialize(paramString);
/*      */       
/* 1909 */       this.sqlKind = this.sqlObject.getSqlKind();
/* 1910 */       this.needToParse = true;
/*      */       
/* 1912 */       prepareForNewResults(true, true);
/*      */       
/* 1914 */       if (this.userRsetType == 1)
/*      */       {
/* 1916 */         doExecuteWithTimeout();
/*      */       }
/*      */       else
/*      */       {
/* 1920 */         doScrollStmtExecuteQuery();
/*      */       }
/*      */       
/* 1923 */       return this.sqlKind.isSELECT();
/*      */     }
/*      */     finally
/*      */     {
/* 1927 */       this.executionType = -1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   int getNumberOfColumns()
/*      */     throws SQLException
/*      */   {
/* 1935 */     ensureOpen();
/* 1936 */     if (!this.described)
/*      */     {
/* 1938 */       synchronized (this.connection) {
/* 1939 */         doDescribe(false);
/*      */         
/* 1941 */         this.described = true;
/*      */       }
/*      */     }
/*      */     
/* 1945 */     return this.numberOfDefinePositions;
/*      */   }
/*      */   
/*      */ 
/*      */   Accessor[] getDescription()
/*      */     throws SQLException
/*      */   {
/* 1952 */     ensureOpen();
/* 1953 */     if (!this.described)
/*      */     {
/* 1955 */       synchronized (this.connection) {
/* 1956 */         doDescribe(false);
/*      */         
/* 1958 */         this.described = true;
/*      */       }
/*      */     }
/*      */     
/* 1962 */     return this.accessors;
/*      */   }
/*      */   
/*      */ 
/*      */   Accessor[] getDescriptionWithNames()
/*      */     throws SQLException
/*      */   {
/* 1969 */     ensureOpen();
/* 1970 */     if (!this.describedWithNames)
/*      */     {
/* 1972 */       synchronized (this.connection) {
/* 1973 */         doDescribe(true);
/*      */         
/* 1975 */         this.described = true;
/* 1976 */         this.describedWithNames = true;
/*      */       }
/*      */     }
/*      */     
/* 1980 */     return this.accessors;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleStatement.SqlKind getSqlKind()
/*      */     throws SQLException
/*      */   {
/* 1993 */     return this.sqlObject.getSqlKind();
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
/*      */   public void clearDefines()
/*      */     throws SQLException
/*      */   {
/* 2013 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 2016 */       freeLine();
/*      */       
/* 2018 */       this.streamList = null;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2025 */       this.columnsDefinedByUser = false;
/* 2026 */       this.needToPrepareDefineBuffer = true;
/*      */       
/*      */ 
/* 2029 */       this.numberOfDefinePositions = 0;
/* 2030 */       this.definesBatchSize = 0;
/*      */       
/* 2032 */       this.described = false;
/* 2033 */       this.describedWithNames = false;
/*      */       
/* 2035 */       cleanupDefines();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void reparseOnRedefineIfNeeded()
/*      */     throws SQLException
/*      */   {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void defineColumnTypeInternal(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, String paramString)
/*      */     throws SQLException
/*      */   {
/* 2057 */     defineColumnTypeInternal(paramInt1, paramInt2, paramInt3, (short)1, paramBoolean, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void defineColumnTypeInternal(int paramInt1, int paramInt2, int paramInt3, short paramShort, boolean paramBoolean, String paramString)
/*      */     throws SQLException
/*      */   {
/* 2068 */     if (this.connection.disableDefinecolumntype) {
/*      */       return;
/*      */     }
/*      */     
/*      */     SQLException localSQLException;
/* 2073 */     if (paramInt1 < 1)
/*      */     {
/* 2075 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2076 */       localSQLException.fillInStackTrace();
/* 2077 */       throw localSQLException;
/*      */     }
/*      */     
/* 2080 */     if (paramInt2 == 0)
/*      */     {
/* 2082 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 2083 */       localSQLException.fillInStackTrace();
/* 2084 */       throw localSQLException;
/*      */     }
/*      */     
/* 2087 */     int i = paramInt1 - 1;
/* 2088 */     int j = this.maxFieldSize > 0 ? this.maxFieldSize : -1;
/*      */     Object localObject1;
/* 2090 */     if (paramBoolean)
/*      */     {
/*      */ 
/*      */ 
/* 2094 */       if ((paramInt2 == 1) || (paramInt2 == 12) || (paramInt2 == -15) || (paramInt2 == -9))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2101 */         this.sqlWarning = DatabaseError.addSqlWarning(this.sqlWarning, 108);
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 2108 */       if (paramInt3 < 0)
/*      */       {
/* 2110 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 53);
/* 2111 */         ((SQLException)localObject1).fillInStackTrace();
/* 2112 */         throw ((Throwable)localObject1);
/*      */       }
/*      */       
/* 2115 */       if (((j == -1) && (paramInt3 > 0)) || ((j > 0) && (paramInt3 < j)))
/*      */       {
/* 2117 */         j = paramInt3;
/*      */       }
/*      */     }
/* 2120 */     if ((this.currentResultSet != null) && (!this.currentResultSet.closed))
/*      */     {
/* 2122 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 28);
/* 2123 */       ((SQLException)localObject1).fillInStackTrace();
/* 2124 */       throw ((Throwable)localObject1);
/*      */     }
/*      */     
/* 2127 */     if (!this.columnsDefinedByUser)
/*      */     {
/*      */ 
/*      */ 
/* 2131 */       clearDefines();
/*      */       
/* 2133 */       this.columnsDefinedByUser = true;
/*      */     }
/*      */     
/* 2136 */     if (this.numberOfDefinePositions < paramInt1)
/*      */     {
/* 2138 */       if ((this.accessors == null) || (this.accessors.length < paramInt1))
/*      */       {
/* 2140 */         localObject1 = new Accessor[paramInt1 << 1];
/*      */         
/* 2142 */         if (this.accessors != null) {
/* 2143 */           System.arraycopy(this.accessors, 0, localObject1, 0, this.numberOfDefinePositions);
/*      */         }
/* 2145 */         this.accessors = ((Accessor[])localObject1);
/*      */       }
/*      */       
/* 2148 */       this.numberOfDefinePositions = paramInt1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2155 */     switch (paramInt2) {
/*      */     case -16: 
/*      */     case -15: 
/*      */     case -9: 
/*      */     case 2011: 
/* 2160 */       paramShort = 2;
/* 2161 */       break;
/*      */     case 2009: 
/* 2163 */       paramString = "SYS.XMLTYPE";
/* 2164 */       break;
/*      */     }
/*      */     
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2171 */     int k = getInternalType(paramInt2);
/*      */     
/* 2173 */     if (((k == 109) || (k == 111)) && ((paramString == null) || (paramString.equals(""))))
/*      */     {
/*      */ 
/* 2176 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 60, "Invalid arguments");
/* 2177 */       ((SQLException)localObject2).fillInStackTrace();
/* 2178 */       throw ((Throwable)localObject2);
/*      */     }
/*      */     
/* 2181 */     Object localObject2 = this.accessors[i];
/* 2182 */     int m = 1;
/*      */     
/* 2184 */     if (localObject2 != null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2189 */       int n = ((Accessor)localObject2).useForDataAccessIfPossible(k, paramInt2, j, paramString);
/*      */       
/*      */ 
/* 2192 */       if (n == 0)
/*      */       {
/* 2194 */         paramShort = ((Accessor)localObject2).formOfUse;
/* 2195 */         localObject2 = null;
/*      */         
/* 2197 */         reparseOnRedefineIfNeeded();
/*      */       }
/* 2199 */       else if (n == 1)
/*      */       {
/* 2201 */         localObject2 = null;
/*      */         
/* 2203 */         reparseOnRedefineIfNeeded();
/*      */       }
/* 2205 */       else if (n == 2) {
/* 2206 */         m = 0;
/*      */       }
/*      */     }
/* 2209 */     if (m != 0) {
/* 2210 */       this.needToPrepareDefineBuffer = true;
/*      */     }
/* 2212 */     if (localObject2 == null)
/*      */     {
/* 2214 */       this.accessors[i] = allocateAccessor(k, paramInt2, paramInt1, j, paramShort, paramString, false);
/*      */       
/* 2216 */       this.described = false;
/* 2217 */       this.describedWithNames = false;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   Accessor allocateAccessor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, short paramShort, String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*      */     Object localObject;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2259 */     switch (paramInt1)
/*      */     {
/*      */ 
/*      */     case 96: 
/* 2263 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2265 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2266 */         localSQLException.fillInStackTrace();
/* 2267 */         throw localSQLException;
/*      */       }
/*      */       
/* 2270 */       return new CharAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */     
/*      */     case 8: 
/* 2273 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2275 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2276 */         localSQLException.fillInStackTrace();
/* 2277 */         throw localSQLException;
/*      */       }
/*      */       
/* 2280 */       if (!paramBoolean) {
/* 2281 */         return new LongAccessor(this, paramInt3, paramInt4, paramShort, paramInt2);
/*      */       }
/*      */     
/*      */ 
/*      */     case 1: 
/* 2286 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2288 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2289 */         localSQLException.fillInStackTrace();
/* 2290 */         throw localSQLException;
/*      */       }
/*      */       
/* 2293 */       return new VarcharAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */     
/*      */     case 2: 
/* 2296 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2298 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2299 */         localSQLException.fillInStackTrace();
/* 2300 */         throw localSQLException;
/*      */       }
/*      */       
/* 2303 */       return new NumberAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */     
/*      */     case 6: 
/* 2306 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2308 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2309 */         localSQLException.fillInStackTrace();
/* 2310 */         throw localSQLException;
/*      */       }
/*      */       
/* 2313 */       return new VarnumAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */     
/*      */     case 24: 
/* 2316 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2318 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2319 */         localSQLException.fillInStackTrace();
/* 2320 */         throw localSQLException;
/*      */       }
/*      */       
/* 2323 */       if (!paramBoolean) {
/* 2324 */         return new LongRawAccessor(this, paramInt3, paramInt4, paramShort, paramInt2);
/*      */       }
/*      */     
/*      */ 
/*      */ 
/*      */     case 23: 
/* 2330 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2332 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2333 */         localSQLException.fillInStackTrace();
/* 2334 */         throw localSQLException;
/*      */       }
/*      */       
/* 2337 */       if (paramBoolean) {
/* 2338 */         return new OutRawAccessor(this, paramInt4, paramShort, paramInt2);
/*      */       }
/* 2340 */       return new RawAccessor(this, paramInt4, paramShort, paramInt2, false);
/*      */     
/*      */     case 100: 
/* 2343 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2345 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2346 */         localSQLException.fillInStackTrace();
/* 2347 */         throw localSQLException;
/*      */       }
/*      */       
/* 2350 */       return new BinaryFloatAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */     
/*      */ 
/*      */     case 101: 
/* 2354 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2356 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2357 */         localSQLException.fillInStackTrace();
/* 2358 */         throw localSQLException;
/*      */       }
/*      */       
/* 2361 */       return new BinaryDoubleAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */     
/*      */ 
/*      */     case 104: 
/* 2365 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2367 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2368 */         localSQLException.fillInStackTrace();
/* 2369 */         throw localSQLException;
/*      */       }
/* 2371 */       if (this.sqlKind == OracleStatement.SqlKind.CALL_BLOCK)
/*      */       {
/* 2373 */         paramInt4 = 18;
/* 2374 */         localObject = new VarcharAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/* 2375 */         ((Accessor)localObject).definedColumnType = -8;
/* 2376 */         return (Accessor)localObject;
/*      */       }
/* 2378 */       return new RowidAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */     
/*      */     case 102: 
/* 2381 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2383 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2384 */         localSQLException.fillInStackTrace();
/* 2385 */         throw localSQLException;
/*      */       }
/*      */       
/* 2388 */       return new ResultSetAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */     
/*      */     case 12: 
/* 2391 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2393 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2394 */         localSQLException.fillInStackTrace();
/* 2395 */         throw localSQLException;
/*      */       }
/*      */       
/* 2398 */       return new DateAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */     
/*      */     case 113: 
/* 2401 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2403 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2404 */         localSQLException.fillInStackTrace();
/* 2405 */         throw localSQLException;
/*      */       }
/*      */       
/* 2408 */       localObject = new BlobAccessor(this, -1, paramShort, paramInt2, paramBoolean);
/* 2409 */       if (!paramBoolean)
/* 2410 */         ((Accessor)localObject).lobPrefetchSizeForThisColumn = paramInt4;
/* 2411 */       return (Accessor)localObject;
/*      */     
/*      */     case 112: 
/* 2414 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2416 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2417 */         localSQLException.fillInStackTrace();
/* 2418 */         throw localSQLException;
/*      */       }
/*      */       
/* 2421 */       localObject = new ClobAccessor(this, -1, paramShort, paramInt2, paramBoolean);
/* 2422 */       if (!paramBoolean)
/* 2423 */         ((Accessor)localObject).lobPrefetchSizeForThisColumn = paramInt4;
/* 2424 */       return (Accessor)localObject;
/*      */     
/*      */     case 114: 
/* 2427 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2429 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2430 */         localSQLException.fillInStackTrace();
/* 2431 */         throw localSQLException;
/*      */       }
/*      */       
/* 2434 */       localObject = new BfileAccessor(this, -1, paramShort, paramInt2, paramBoolean);
/*      */       
/*      */ 
/*      */ 
/* 2438 */       return (Accessor)localObject;
/*      */     
/*      */     case 109: 
/* 2441 */       if (paramString == null) {
/* 2442 */         if (paramBoolean)
/*      */         {
/* 2444 */           localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2445 */           localSQLException.fillInStackTrace();
/* 2446 */           throw localSQLException;
/*      */         }
/*      */         
/*      */ 
/* 2450 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 60, "Unable to resolve type \"null\"");
/* 2451 */         localSQLException.fillInStackTrace();
/* 2452 */         throw localSQLException;
/*      */       }
/*      */       
/* 2455 */       localObject = new NamedTypeAccessor(this, paramString, paramShort, paramInt2, paramBoolean);
/*      */       
/*      */ 
/* 2458 */       ((Accessor)localObject).initMetadata();
/*      */       
/* 2460 */       return (Accessor)localObject;
/*      */     
/*      */     case 111: 
/* 2463 */       if (paramString == null) {
/* 2464 */         if (paramBoolean)
/*      */         {
/* 2466 */           localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2467 */           localSQLException.fillInStackTrace();
/* 2468 */           throw localSQLException;
/*      */         }
/*      */         
/*      */ 
/* 2472 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 60, "Unable to resolve type \"null\"");
/* 2473 */         localSQLException.fillInStackTrace();
/* 2474 */         throw localSQLException;
/*      */       }
/*      */       
/* 2477 */       localObject = new RefTypeAccessor(this, paramString, paramShort, paramInt2, paramBoolean);
/*      */       
/*      */ 
/* 2480 */       ((Accessor)localObject).initMetadata();
/*      */       
/* 2482 */       return (Accessor)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 180: 
/* 2487 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2489 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2490 */         localSQLException.fillInStackTrace();
/* 2491 */         throw localSQLException;
/*      */       }
/*      */       
/* 2494 */       return new TimestampAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */     
/*      */ 
/*      */     case 181: 
/* 2498 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2500 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2501 */         localSQLException.fillInStackTrace();
/* 2502 */         throw localSQLException;
/*      */       }
/*      */       
/* 2505 */       return new TimestamptzAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */     
/*      */ 
/*      */     case 231: 
/* 2509 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2511 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2512 */         localSQLException.fillInStackTrace();
/* 2513 */         throw localSQLException;
/*      */       }
/*      */       
/* 2516 */       return new TimestampltzAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */     
/*      */ 
/*      */     case 182: 
/* 2520 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2522 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2523 */         localSQLException.fillInStackTrace();
/* 2524 */         throw localSQLException;
/*      */       }
/*      */       
/* 2527 */       return new IntervalymAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */     
/*      */ 
/*      */     case 183: 
/* 2531 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 2533 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 2534 */         localSQLException.fillInStackTrace();
/* 2535 */         throw localSQLException;
/*      */       }
/*      */       
/* 2538 */       return new IntervaldsAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 995: 
/* 2557 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 89);
/* 2558 */       localSQLException.fillInStackTrace();
/* 2559 */       throw localSQLException;
/*      */     }
/*      */     
/*      */     
/*      */ 
/* 2564 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 2565 */     localSQLException.fillInStackTrace();
/* 2566 */     throw localSQLException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void defineColumnType(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2618 */     synchronized (this.connection)
/*      */     {
/* 2620 */       defineColumnTypeInternal(paramInt1, paramInt2, -1, true, null);
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
/*      */ 
/*      */   public void defineColumnType(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 2636 */     defineColumnTypeInternal(paramInt1, paramInt2, paramInt3, false, null);
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
/*      */   public void defineColumnType(int paramInt1, int paramInt2, int paramInt3, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 2655 */     defineColumnTypeInternal(paramInt1, paramInt2, paramInt3, paramShort, false, null);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   int lastIndex;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void defineColumnTypeBytes(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 2726 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 2729 */       defineColumnTypeInternal(paramInt1, paramInt2, paramInt3, false, null);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void defineColumnTypeChars(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 2800 */     defineColumnTypeInternal(paramInt1, paramInt2, paramInt3, false, null);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void defineColumnType(int paramInt1, int paramInt2, String paramString)
/*      */     throws SQLException
/*      */   {
/* 2840 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2846 */       defineColumnTypeInternal(paramInt1, paramInt2, -1, true, paramString);
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
/*      */ 
/*      */   void setCursorId(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2862 */     this.cursorId = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void setPrefetchInternal(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2877 */     if (paramBoolean1)
/*      */     {
/* 2879 */       if (paramInt <= 0)
/*      */       {
/* 2881 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 20);
/* 2882 */         localSQLException.fillInStackTrace();
/* 2883 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 2888 */       if (paramInt < 0)
/*      */       {
/* 2890 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "setFetchSize");
/* 2891 */         localSQLException.fillInStackTrace();
/* 2892 */         throw localSQLException;
/*      */       }
/* 2894 */       if (paramInt == 0) {
/* 2895 */         paramInt = this.connection.getDefaultRowPrefetch();
/*      */       }
/*      */     }
/*      */     
/* 2899 */     if (paramBoolean2)
/*      */     {
/* 2901 */       if (paramInt != this.defaultRowPrefetch)
/*      */       {
/* 2903 */         this.defaultRowPrefetch = paramInt;
/*      */         
/*      */ 
/*      */ 
/* 2907 */         if ((this.currentResultSet == null) || (this.currentResultSet.closed)) {
/* 2908 */           this.rowPrefetchChanged = true;
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */ 
/*      */     }
/* 2915 */     else if ((paramInt != this.rowPrefetch) && (this.streamList == null))
/*      */     {
/*      */ 
/* 2918 */       this.rowPrefetch = paramInt;
/* 2919 */       this.rowPrefetchChanged = true;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRowPrefetch(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2946 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 2950 */       setPrefetchInternal(paramInt, true, true);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLobPrefetchSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2963 */     synchronized (this.connection)
/*      */     {
/* 2965 */       if (paramInt < -1)
/*      */       {
/* 2967 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 267);
/* 2968 */         localSQLException.fillInStackTrace();
/* 2969 */         throw localSQLException;
/*      */       }
/* 2971 */       this.defaultLobPrefetchSize = paramInt;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getLobPrefetchSize()
/*      */   {
/* 2979 */     return this.defaultLobPrefetchSize;
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
/*      */   int getPrefetchInternal(boolean paramBoolean)
/*      */   {
/* 2996 */     int i = paramBoolean ? this.defaultRowPrefetch : this.rowPrefetch;
/*      */     
/* 2998 */     return i;
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public int getRowPrefetch()
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 71	oracle/jdbc/driver/OracleStatement:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iconst_1
/*      */     //   9: invokevirtual 289	oracle/jdbc/driver/OracleStatement:getPrefetchInternal	(Z)I
/*      */     //   12: aload_1
/*      */     //   13: monitorexit
/*      */     //   14: ireturn
/*      */     //   15: astore_2
/*      */     //   16: aload_1
/*      */     //   17: monitorexit
/*      */     //   18: aload_2
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #3013	-> byte code offset #0
/*      */     //   Java source line #3016	-> byte code offset #7
/*      */     //   Java source line #3018	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	OracleStatement
/*      */     //   5	12	1	Ljava/lang/Object;	Object
/*      */     //   15	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   public void setFixedString(boolean paramBoolean)
/*      */   {
/* 3043 */     this.fixedString = paramBoolean;
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
/*      */   public boolean getFixedString()
/*      */   {
/* 3068 */     return this.fixedString;
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
/*      */   void check_row_prefetch_changed()
/*      */     throws SQLException
/*      */   {
/* 3083 */     if (this.rowPrefetchChanged)
/*      */     {
/* 3085 */       if (this.streamList == null)
/*      */       {
/* 3087 */         prepareAccessors();
/*      */         
/* 3089 */         this.needToPrepareDefineBuffer = true;
/*      */       }
/*      */       
/* 3092 */       this.rowPrefetchChanged = false;
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
/*      */   void setDefinesInitialized(boolean paramBoolean) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void printState(String paramString)
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
/*      */   void checkValidRowsStatus()
/*      */     throws SQLException
/*      */   {
/* 3129 */     if (this.validRows == -2)
/*      */     {
/*      */ 
/*      */ 
/* 3133 */       this.validRows = 1;
/* 3134 */       this.connection.holdLine(this);
/*      */       
/*      */ 
/* 3137 */       OracleInputStream localOracleInputStream = this.streamList;
/*      */       
/* 3139 */       while (localOracleInputStream != null)
/*      */       {
/* 3141 */         if (localOracleInputStream.hasBeenOpen) {
/* 3142 */           localOracleInputStream = localOracleInputStream.accessor.initForNewRow();
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3149 */         localOracleInputStream.closed = false;
/* 3150 */         localOracleInputStream.hasBeenOpen = true;
/*      */         
/*      */ 
/*      */ 
/* 3154 */         localOracleInputStream = localOracleInputStream.nextStream;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 3159 */       this.nextStream = this.streamList;
/*      */ 
/*      */     }
/* 3162 */     else if (this.sqlKind.isSELECT())
/*      */     {
/* 3164 */       if (this.validRows < this.rowPrefetch) {
/* 3165 */         this.gotLastBatch = true;
/*      */       }
/* 3167 */     } else if (!this.sqlKind.isPlsqlOrCall())
/*      */     {
/* 3169 */       this.rowsProcessed = this.validRows;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void cleanupDefines()
/*      */   {
/* 3177 */     if (this.accessors != null) {
/* 3178 */       for (int i = 0; i < this.accessors.length; i++)
/* 3179 */         this.accessors[i] = null;
/*      */     }
/* 3181 */     this.accessors = null;
/*      */     
/*      */ 
/* 3184 */     this.connection.cacheBuffer(this.defineBytes);
/* 3185 */     this.defineBytes = null;
/* 3186 */     this.connection.cacheBuffer(this.defineChars);
/* 3187 */     this.defineChars = null;
/* 3188 */     this.defineIndicators = null;
/* 3189 */     this.defineMetaData = null;
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public int getMaxFieldSize()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 71	oracle/jdbc/driver/OracleStatement:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 211	oracle/jdbc/driver/OracleStatement:maxFieldSize	I
/*      */     //   11: aload_1
/*      */     //   12: monitorexit
/*      */     //   13: ireturn
/*      */     //   14: astore_2
/*      */     //   15: aload_1
/*      */     //   16: monitorexit
/*      */     //   17: aload_2
/*      */     //   18: athrow
/*      */     // Line number table:
/*      */     //   Java source line #3196	-> byte code offset #0
/*      */     //   Java source line #3198	-> byte code offset #7
/*      */     //   Java source line #3200	-> byte code offset #14
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	19	0	this	OracleStatement
/*      */     //   5	11	1	Ljava/lang/Object;	Object
/*      */     //   14	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	13	14	finally
/*      */     //   14	17	14	finally
/*      */   }
/*      */   
/*      */   public void setMaxFieldSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3205 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 3208 */       if (paramInt < 0)
/*      */       {
/* 3210 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 3211 */         localSQLException.fillInStackTrace();
/* 3212 */         throw localSQLException;
/*      */       }
/*      */       
/* 3215 */       this.maxFieldSize = paramInt;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public int getMaxRows()
/*      */     throws SQLException
/*      */   {
/* 3223 */     return this.maxRows;
/*      */   }
/*      */   
/*      */   public void setMaxRows(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3229 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 3232 */       if (paramInt < 0)
/*      */       {
/* 3234 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 3235 */         localSQLException.fillInStackTrace();
/* 3236 */         throw localSQLException;
/*      */       }
/*      */       
/* 3239 */       this.maxRows = paramInt;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setEscapeProcessing(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 3247 */     synchronized (this.connection)
/*      */     {
/* 3249 */       this.processEscapes = paramBoolean;
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public int getQueryTimeout()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 71	oracle/jdbc/driver/OracleStatement:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 154	oracle/jdbc/driver/OracleStatement:queryTimeout	I
/*      */     //   11: aload_1
/*      */     //   12: monitorexit
/*      */     //   13: ireturn
/*      */     //   14: astore_2
/*      */     //   15: aload_1
/*      */     //   16: monitorexit
/*      */     //   17: aload_2
/*      */     //   18: athrow
/*      */     // Line number table:
/*      */     //   Java source line #3263	-> byte code offset #0
/*      */     //   Java source line #3265	-> byte code offset #7
/*      */     //   Java source line #3267	-> byte code offset #14
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	19	0	this	OracleStatement
/*      */     //   5	11	1	Ljava/lang/Object;	Object
/*      */     //   14	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	13	14	finally
/*      */     //   14	17	14	finally
/*      */   }
/*      */   
/*      */   public void setQueryTimeout(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3280 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 3283 */       if (paramInt < 0)
/*      */       {
/* 3285 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 3286 */         localSQLException.fillInStackTrace();
/* 3287 */         throw localSQLException;
/*      */       }
/*      */       
/* 3290 */       this.queryTimeout = paramInt;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void cancel()
/*      */     throws SQLException
/*      */   {
/* 3303 */     doCancel();
/*      */   }
/*      */   
/*      */ 
/*      */   boolean doCancel()
/*      */     throws SQLException
/*      */   {
/* 3310 */     boolean bool = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3315 */     if (this.closed) {
/* 3316 */       return bool;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3328 */     if (this.connection.statementHoldingLine != null) {
/* 3329 */       freeLine();
/* 3330 */     } else if (this.cancelLock.enterCanceling()) {
/*      */       try
/*      */       {
/* 3333 */         bool = true;
/* 3334 */         this.connection.cancelOperationOnServer(true);
/*      */       }
/*      */       finally {
/* 3337 */         this.cancelLock.exitCanceling();
/*      */       }
/*      */       
/*      */     } else {
/* 3341 */       return bool;
/*      */     }
/* 3343 */     OracleStatement localOracleStatement = this.children;
/* 3344 */     while (localOracleStatement != null) {
/* 3345 */       bool = (bool) || (localOracleStatement.doCancel());
/* 3346 */       localOracleStatement = localOracleStatement.nextChild;
/*      */     }
/*      */     
/* 3349 */     this.connection.releaseLineForCancel();
/* 3350 */     return bool;
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
/*      */   public SQLWarning getWarnings()
/*      */     throws SQLException
/*      */   {
/* 3367 */     return this.sqlWarning;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearWarnings()
/*      */     throws SQLException
/*      */   {
/* 3377 */     this.sqlWarning = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void foundPlsqlCompilerWarning()
/*      */     throws SQLException
/*      */   {
/* 3385 */     SQLWarning localSQLWarning = DatabaseError.addSqlWarning(this.sqlWarning, "Found Plsql compiler warnings.", 24439);
/*      */     
/* 3387 */     if (this.sqlWarning != null)
/*      */     {
/* 3389 */       this.sqlWarning.setNextWarning(localSQLWarning);
/*      */     }
/*      */     else
/*      */     {
/* 3393 */       this.sqlWarning = localSQLWarning;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCursorName(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3405 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 3406 */     localSQLException.fillInStackTrace();
/* 3407 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ResultSet getResultSet()
/*      */     throws SQLException
/*      */   {
/* 3420 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 3423 */       if (this.userRsetType == 1)
/*      */       {
/* 3425 */         if (this.sqlKind.isSELECT())
/*      */         {
/* 3427 */           if (this.currentResultSet == null) {
/* 3428 */             this.currentResultSet = new OracleResultSetImpl(this.connection, this);
/*      */           }
/* 3430 */           return this.currentResultSet;
/*      */         }
/*      */         
/*      */       }
/*      */       else {
/* 3435 */         return this.scrollRset;
/*      */       }
/*      */       
/* 3438 */       return null;
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
/*      */ 
/*      */ 
/*      */   public int getUpdateCount()
/*      */     throws SQLException
/*      */   {
/* 3455 */     synchronized (this.connection)
/*      */     {
/* 3457 */       int i = -1;
/*      */       
/* 3459 */       switch (this.sqlKind)
/*      */       {
/*      */       case UNINITIALIZED: 
/*      */       case SELECT_FOR_UPDATE: 
/*      */       case SELECT: 
/*      */         break;
/*      */       
/*      */ 
/*      */ 
/*      */       case ALTER_SESSION: 
/*      */       case OTHER: 
/* 3470 */         if (!this.noMoreUpdateCounts) {
/* 3471 */           i = this.rowsProcessed;
/*      */         }
/* 3473 */         this.noMoreUpdateCounts = true;
/*      */         
/* 3475 */         break;
/*      */       
/*      */ 
/*      */ 
/*      */       case PLSQL_BLOCK: 
/*      */       case CALL_BLOCK: 
/* 3481 */         this.noMoreUpdateCounts = true;
/*      */         
/* 3483 */         break;
/*      */       
/*      */ 
/*      */ 
/*      */       case DELETE: 
/*      */       case INSERT: 
/*      */       case MERGE: 
/*      */       case UPDATE: 
/* 3491 */         if (!this.noMoreUpdateCounts) {
/* 3492 */           i = this.rowsProcessed;
/*      */         }
/* 3494 */         this.noMoreUpdateCounts = true;
/*      */       }
/*      */       
/*      */       
/*      */ 
/* 3499 */       return i;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getMoreResults()
/*      */     throws SQLException
/*      */   {
/* 3511 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int sendBatch()
/*      */     throws SQLException
/*      */   {
/* 3522 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void prepareForNewResults(boolean paramBoolean1, boolean paramBoolean2)
/*      */     throws SQLException
/*      */   {
/* 3533 */     clearWarnings();
/*      */     
/* 3535 */     if (this.streamList != null)
/*      */     {
/*      */       Object localObject;
/*      */       
/* 3539 */       while (this.nextStream != null)
/*      */       {
/*      */         try
/*      */         {
/* 3543 */           this.nextStream.close();
/*      */ 
/*      */         }
/*      */         catch (IOException localIOException)
/*      */         {
/* 3548 */           localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3549 */           ((SQLException)localObject).fillInStackTrace();
/* 3550 */           throw ((Throwable)localObject);
/*      */         }
/*      */         
/*      */ 
/* 3554 */         this.nextStream = this.nextStream.nextStream;
/*      */       }
/*      */       
/* 3557 */       if (paramBoolean2)
/*      */       {
/*      */ 
/* 3560 */         OracleInputStream localOracleInputStream = this.streamList;
/* 3561 */         localObject = null;
/*      */         
/* 3563 */         this.streamList = null;
/*      */         
/* 3565 */         while (localOracleInputStream != null)
/*      */         {
/* 3567 */           if (!localOracleInputStream.hasBeenOpen)
/*      */           {
/* 3569 */             if (localObject == null) {
/* 3570 */               this.streamList = localOracleInputStream;
/*      */             } else {
/* 3572 */               ((OracleInputStream)localObject).nextStream = localOracleInputStream;
/*      */             }
/* 3574 */             localObject = localOracleInputStream;
/*      */           }
/*      */           
/* 3577 */           localOracleInputStream = localOracleInputStream.nextStream;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 3582 */     if (this.currentResultSet != null)
/*      */     {
/* 3584 */       this.currentResultSet.internal_close(true);
/*      */       
/* 3586 */       this.currentResultSet = null;
/*      */     }
/*      */     
/* 3589 */     this.currentRow = -1;
/* 3590 */     this.checkSum = 0L;
/* 3591 */     this.checkSumComputationFailure = false;
/* 3592 */     this.validRows = 0;
/* 3593 */     if (paramBoolean1)
/* 3594 */       this.totalRowsVisited = 0;
/* 3595 */     this.gotLastBatch = false;
/*      */     
/* 3597 */     if ((this.needToParse) && (!this.columnsDefinedByUser))
/*      */     {
/* 3599 */       if ((paramBoolean2) && (this.numberOfDefinePositions != 0)) {
/* 3600 */         this.numberOfDefinePositions = 0;
/*      */       }
/* 3602 */       this.needToPrepareDefineBuffer = true;
/*      */     }
/*      */     
/*      */ 
/* 3606 */     if ((paramBoolean1) && (this.rowPrefetch != this.defaultRowPrefetch) && (this.streamList == null))
/*      */     {
/*      */ 
/*      */ 
/* 3610 */       this.rowPrefetch = this.defaultRowPrefetch;
/* 3611 */       this.rowPrefetchChanged = true;
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
/*      */   void reopenStreams()
/*      */     throws SQLException
/*      */   {
/* 3625 */     OracleInputStream localOracleInputStream = this.streamList;
/*      */     
/* 3627 */     while (localOracleInputStream != null)
/*      */     {
/* 3629 */       if (localOracleInputStream.hasBeenOpen) {
/* 3630 */         localOracleInputStream = localOracleInputStream.accessor.initForNewRow();
/*      */       }
/* 3632 */       localOracleInputStream.closed = false;
/* 3633 */       localOracleInputStream.hasBeenOpen = true;
/* 3634 */       localOracleInputStream = localOracleInputStream.nextStream;
/*      */     }
/*      */     
/* 3637 */     this.nextStream = this.streamList;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void endOfResultSet(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 3649 */     if (!paramBoolean)
/*      */     {
/*      */ 
/* 3652 */       prepareForNewResults(false, false);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3657 */     clearDefines();
/* 3658 */     this.rowPrefetchInLastFetch = -1;
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
/*      */   boolean wasNullValue()
/*      */     throws SQLException
/*      */   {
/* 3683 */     if (this.lastIndex == 0)
/*      */     {
/* 3685 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 24);
/* 3686 */       localSQLException.fillInStackTrace();
/* 3687 */       throw localSQLException;
/*      */     }
/*      */     
/* 3690 */     if (this.sqlKind.isSELECT()) {
/* 3691 */       return this.accessors[(this.lastIndex - 1)].isNull(this.currentRow);
/*      */     }
/* 3693 */     return this.outBindAccessors[(this.lastIndex - 1)].isNull(this.currentRank);
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
/*      */   int getColumnIndex(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3707 */     ensureOpen();
/* 3708 */     if (!this.describedWithNames)
/*      */     {
/* 3710 */       synchronized (this.connection) {
/* 3711 */         doDescribe(true);
/*      */         
/* 3713 */         this.described = true;
/* 3714 */         this.describedWithNames = true;
/*      */       }
/*      */     }
/*      */     
/* 3718 */     for (int i = 0; i < this.numberOfDefinePositions; i++) {
/* 3719 */       if (this.accessors[i].columnName.equalsIgnoreCase(paramString)) {
/* 3720 */         return i + 1;
/*      */       }
/*      */     }
/* 3723 */     ??? = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3724 */     ((SQLException)???).fillInStackTrace();
/* 3725 */     throw ((Throwable)???);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   int getJDBCType(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3733 */     int i = 0;
/* 3734 */     switch (paramInt)
/*      */     {
/*      */     case 6: 
/* 3737 */       i = 2;
/* 3738 */       break;
/*      */     case 100: 
/* 3740 */       i = 100;
/* 3741 */       break;
/*      */     case 101: 
/* 3743 */       i = 101;
/* 3744 */       break;
/*      */     case 999: 
/* 3746 */       i = 999;
/* 3747 */       break;
/*      */     case 96: 
/* 3749 */       i = 1;
/* 3750 */       break;
/*      */     case 1: 
/* 3752 */       i = 12;
/* 3753 */       break;
/*      */     case 8: 
/* 3755 */       i = -1;
/* 3756 */       break;
/*      */     case 12: 
/* 3758 */       i = 91;
/* 3759 */       break;
/*      */     case 180: 
/* 3761 */       i = 93;
/* 3762 */       break;
/*      */     case 181: 
/* 3764 */       i = -101;
/* 3765 */       break;
/*      */     case 231: 
/* 3767 */       i = -102;
/* 3768 */       break;
/*      */     case 182: 
/* 3770 */       i = -103;
/* 3771 */       break;
/*      */     case 183: 
/* 3773 */       i = -104;
/* 3774 */       break;
/*      */     case 23: 
/* 3776 */       i = -2;
/* 3777 */       break;
/*      */     case 24: 
/* 3779 */       i = -4;
/* 3780 */       break;
/*      */     case 104: 
/* 3782 */       i = -8;
/* 3783 */       break;
/*      */     case 113: 
/* 3785 */       i = 2004;
/* 3786 */       break;
/*      */     case 112: 
/* 3788 */       i = 2005;
/* 3789 */       break;
/*      */     case 114: 
/* 3791 */       i = -13;
/* 3792 */       break;
/*      */     case 102: 
/* 3794 */       i = -10;
/* 3795 */       break;
/*      */     case 109: 
/* 3797 */       i = 2002;
/* 3798 */       break;
/*      */     case 111: 
/* 3800 */       i = 2006;
/* 3801 */       break;
/*      */     case 998: 
/* 3803 */       i = -14;
/* 3804 */       break;
/*      */     case 995: 
/* 3806 */       i = 0;
/* 3807 */       break;
/*      */     default: 
/* 3809 */       i = paramInt;
/*      */     }
/*      */     
/* 3812 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */   int getInternalType(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3819 */     int i = 0;
/*      */     
/* 3821 */     switch (paramInt)
/*      */     {
/*      */     case -7: 
/*      */     case -6: 
/*      */     case -5: 
/*      */     case 2: 
/*      */     case 3: 
/*      */     case 4: 
/*      */     case 5: 
/*      */     case 6: 
/*      */     case 7: 
/*      */     case 8: 
/* 3833 */       i = 6;
/* 3834 */       break;
/*      */     
/*      */     case 100: 
/* 3837 */       i = 100;
/* 3838 */       break;
/*      */     
/*      */     case 101: 
/* 3841 */       i = 101;
/* 3842 */       break;
/*      */     
/*      */     case 999: 
/* 3845 */       i = 999;
/* 3846 */       break;
/*      */     
/*      */     case 1: 
/* 3849 */       i = 96;
/* 3850 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case -15: 
/*      */     case -9: 
/*      */     case 12: 
/* 3859 */       i = 1;
/* 3860 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case -16: 
/*      */     case -1: 
/* 3868 */       i = 8;
/* 3869 */       break;
/*      */     
/*      */     case 91: 
/*      */     case 92: 
/* 3873 */       i = 12;
/* 3874 */       break;
/*      */     
/*      */     case -100: 
/*      */     case 93: 
/* 3878 */       i = 180;
/* 3879 */       break;
/*      */     
/*      */     case -101: 
/* 3882 */       i = 181;
/* 3883 */       break;
/*      */     
/*      */     case -102: 
/* 3886 */       i = 231;
/* 3887 */       break;
/*      */     
/*      */ 
/*      */     case -103: 
/* 3891 */       i = 182;
/* 3892 */       break;
/*      */     
/*      */     case -104: 
/* 3895 */       i = 183;
/* 3896 */       break;
/*      */     
/*      */     case -3: 
/*      */     case -2: 
/* 3900 */       i = 23;
/* 3901 */       break;
/*      */     
/*      */     case -4: 
/* 3904 */       i = 24;
/* 3905 */       break;
/*      */     
/*      */     case -8: 
/* 3908 */       i = 104;
/* 3909 */       break;
/*      */     
/*      */     case 2004: 
/* 3912 */       i = 113;
/* 3913 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 2005: 
/*      */     case 2011: 
/* 3921 */       i = 112;
/* 3922 */       break;
/*      */     
/*      */     case -13: 
/* 3925 */       i = 114;
/* 3926 */       break;
/*      */     
/*      */     case -10: 
/* 3929 */       i = 102;
/* 3930 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 2002: 
/*      */     case 2003: 
/*      */     case 2007: 
/*      */     case 2008: 
/*      */     case 2009: 
/* 3941 */       i = 109;
/* 3942 */       break;
/*      */     
/*      */     case 2006: 
/* 3945 */       i = 111;
/* 3946 */       break;
/*      */     
/*      */     case -14: 
/* 3949 */       i = 998;
/* 3950 */       break;
/*      */     
/*      */     case 70: 
/* 3953 */       i = 1;
/* 3954 */       break;
/*      */     
/*      */     case 0: 
/* 3957 */       i = 995;
/* 3958 */       break;
/*      */     
/*      */ 
/*      */     default: 
/* 3962 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, Integer.toString(paramInt));
/* 3963 */       localSQLException.fillInStackTrace();
/* 3964 */       throw localSQLException;
/*      */     }
/*      */     
/*      */     
/* 3968 */     return i;
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
/*      */   void describe()
/*      */     throws SQLException
/*      */   {
/* 3986 */     synchronized (this.connection)
/*      */     {
/* 3988 */       ensureOpen();
/* 3989 */       if (!this.described)
/*      */       {
/* 3991 */         doDescribe(false);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void freeLine()
/*      */     throws SQLException
/*      */   {
/* 4000 */     if (this.streamList != null)
/*      */     {
/*      */ 
/*      */ 
/* 4004 */       while (this.nextStream != null)
/*      */       {
/*      */         try
/*      */         {
/* 4008 */           this.nextStream.close();
/*      */ 
/*      */         }
/*      */         catch (IOException localIOException)
/*      */         {
/* 4013 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 4014 */           localSQLException.fillInStackTrace();
/* 4015 */           throw localSQLException;
/*      */         }
/*      */         
/*      */ 
/* 4019 */         this.nextStream = this.nextStream.nextStream;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void closeUsedStreams(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4032 */     while ((this.nextStream != null) && (this.nextStream.columnIndex < paramInt))
/*      */     {
/*      */ 
/*      */       try
/*      */       {
/*      */ 
/* 4038 */         this.nextStream.close();
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/* 4043 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 4044 */         localSQLException.fillInStackTrace();
/* 4045 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 4049 */       this.nextStream = this.nextStream.nextStream;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   final void ensureOpen()
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/* 4061 */     if (this.connection.lifecycle != 1)
/*      */     {
/* 4063 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 4064 */       localSQLException.fillInStackTrace();
/* 4065 */       throw localSQLException;
/*      */     }
/* 4067 */     if (this.closed)
/*      */     {
/* 4069 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4070 */       localSQLException.fillInStackTrace();
/* 4071 */       throw localSQLException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void allocateTmpByteArray() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
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
/* 4105 */     synchronized (this.connection)
/*      */     {
/* 4107 */       if (paramInt == 1000)
/*      */       {
/*      */ 
/* 4110 */         this.defaultFetchDirection = paramInt;
/*      */       }
/* 4112 */       else if ((paramInt == 1001) || (paramInt == 1002))
/*      */       {
/*      */ 
/* 4115 */         this.defaultFetchDirection = 1000;
/* 4116 */         this.sqlWarning = DatabaseError.addSqlWarning(this.sqlWarning, 87);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 4122 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "setFetchDirection");
/* 4123 */         localSQLException.fillInStackTrace();
/* 4124 */         throw localSQLException;
/*      */       }
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
/* 4147 */     return this.defaultFetchDirection;
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
/*      */   public void setFetchSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4166 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 4169 */       setPrefetchInternal(paramInt, false, true);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getFetchSize()
/*      */     throws SQLException
/*      */   {
/* 4192 */     return getPrefetchInternal(true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getResultSetConcurrency()
/*      */     throws SQLException
/*      */   {
/* 4204 */     return ResultSetUtil.getUpdateConcurrency(this.userRsetType);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getResultSetType()
/*      */     throws SQLException
/*      */   {
/* 4216 */     return ResultSetUtil.getScrollType(this.userRsetType);
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
/*      */   public Connection getConnection()
/*      */     throws SQLException
/*      */   {
/* 4231 */     return this.connection.getWrapper();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setResultSetCache(oracle.jdbc.OracleResultSetCache paramOracleResultSetCache)
/*      */     throws SQLException
/*      */   {
/* 4244 */     synchronized (this.connection)
/*      */     {
/*      */       try
/*      */       {
/* 4248 */         if (paramOracleResultSetCache == null)
/*      */         {
/* 4250 */           SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 4251 */           localSQLException1.fillInStackTrace();
/* 4252 */           throw localSQLException1;
/*      */         }
/*      */         
/* 4255 */         if (this.rsetCache != null) {
/* 4256 */           this.rsetCache.close();
/*      */         }
/* 4258 */         this.rsetCache = paramOracleResultSetCache;
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/* 4263 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 4264 */         localSQLException2.fillInStackTrace();
/* 4265 */         throw localSQLException2;
/*      */       }
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
/*      */ 
/*      */   public void setResultSetCache(OracleResultSetCache paramOracleResultSetCache)
/*      */     throws SQLException
/*      */   {
/* 4282 */     synchronized (this.connection)
/*      */     {
/* 4284 */       setResultSetCache(paramOracleResultSetCache);
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public OracleResultSetCache getResultSetCache()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 71	oracle/jdbc/driver/OracleStatement:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 327	oracle/jdbc/driver/OracleStatement:rsetCache	Loracle/jdbc/OracleResultSetCache;
/*      */     //   11: checkcast 330	oracle/jdbc/driver/OracleResultSetCache
/*      */     //   14: aload_1
/*      */     //   15: monitorexit
/*      */     //   16: areturn
/*      */     //   17: astore_2
/*      */     //   18: aload_1
/*      */     //   19: monitorexit
/*      */     //   20: aload_2
/*      */     //   21: athrow
/*      */     // Line number table:
/*      */     //   Java source line #4295	-> byte code offset #0
/*      */     //   Java source line #4297	-> byte code offset #7
/*      */     //   Java source line #4299	-> byte code offset #17
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	22	0	this	OracleStatement
/*      */     //   5	14	1	Ljava/lang/Object;	Object
/*      */     //   17	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	16	17	finally
/*      */     //   17	20	17	finally
/*      */   }
/*      */   
/*      */   boolean isOracleBatchStyle()
/*      */   {
/* 4306 */     return false;
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
/* 4330 */   Vector m_batchItems = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void initBatch() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   int getBatchSize()
/*      */   {
/* 4342 */     if (this.m_batchItems == null)
/*      */     {
/* 4344 */       return 0;
/*      */     }
/*      */     
/*      */ 
/* 4348 */     return this.m_batchItems.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void addBatchItem(String paramString)
/*      */   {
/* 4356 */     if (this.m_batchItems == null)
/*      */     {
/* 4358 */       this.m_batchItems = new Vector();
/*      */     }
/* 4360 */     this.m_batchItems.addElement(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   String getBatchItem(int paramInt)
/*      */   {
/* 4367 */     return (String)this.m_batchItems.elementAt(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void clearBatchItems()
/*      */   {
/* 4374 */     this.m_batchItems.removeAllElements();
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
/*      */   void checkIfJdbcBatchExists()
/*      */     throws SQLException
/*      */   {
/* 4390 */     if (getBatchSize() > 0)
/*      */     {
/*      */ 
/* 4393 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 81, "batch must be either executed or cleared");
/* 4394 */       localSQLException.fillInStackTrace();
/* 4395 */       throw localSQLException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addBatch(String paramString)
/*      */     throws SQLException
/*      */   {
/* 4421 */     synchronized (this.connection)
/*      */     {
/* 4423 */       addBatchItem(paramString);
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
/*      */   public void clearBatch()
/*      */     throws SQLException
/*      */   {
/* 4438 */     synchronized (this.connection)
/*      */     {
/* 4440 */       clearBatchItems();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int[] executeBatch()
/*      */     throws SQLException
/*      */   {
/* 4471 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 4475 */       cleanOldTempLobs();
/* 4476 */       int i = 0;
/* 4477 */       int j = getBatchSize();
/* 4478 */       this.checkSum = 0L;
/* 4479 */       this.checkSumComputationFailure = false;
/*      */       
/*      */ 
/*      */ 
/* 4483 */       if (j <= 0)
/*      */       {
/*      */ 
/*      */ 
/* 4487 */         return new int[0];
/*      */       }
/*      */       
/* 4490 */       int[] arrayOfInt = new int[j];
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4496 */       ensureOpen();
/*      */       
/*      */ 
/* 4499 */       prepareForNewResults(true, true);
/*      */       
/* 4501 */       int k = this.numberOfDefinePositions;
/* 4502 */       String str = this.sqlObject.getOriginalSql();
/* 4503 */       OracleStatement.SqlKind localSqlKind = this.sqlKind;
/*      */       
/*      */ 
/* 4506 */       this.noMoreUpdateCounts = false;
/*      */       
/* 4508 */       int m = 0;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       try
/*      */       {
/* 4517 */         this.connection.registerHeartbeat();
/*      */         
/* 4519 */         this.connection.needLine();
/*      */         
/* 4521 */         for (i = 0; i < j; i++)
/*      */         {
/* 4523 */           this.sqlObject.initialize(getBatchItem(i));
/*      */           
/* 4525 */           this.sqlKind = this.sqlObject.getSqlKind();
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4531 */           this.needToParse = true;
/* 4532 */           this.numberOfDefinePositions = 0;
/*      */           
/*      */ 
/* 4535 */           this.rowsProcessed = 0;
/* 4536 */           this.currentRank = 1;
/*      */           
/* 4538 */           if (this.sqlKind.isSELECT())
/*      */           {
/*      */ 
/* 4541 */             BatchUpdateException localBatchUpdateException1 = DatabaseError.createBatchUpdateException(80, "invalid SELECT batch command " + i, i, arrayOfInt);
/*      */             
/* 4543 */             localBatchUpdateException1.fillInStackTrace();
/* 4544 */             throw localBatchUpdateException1;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 4549 */           if (!this.isOpen)
/*      */           {
/* 4551 */             this.connection.open(this);
/*      */             
/* 4553 */             this.isOpen = true;
/*      */           }
/*      */           
/* 4556 */           int n = -1;
/*      */           
/*      */           try
/*      */           {
/* 4560 */             if (this.queryTimeout != 0) {
/* 4561 */               this.connection.getTimeout().setTimeout(this.queryTimeout * 1000, this);
/*      */             }
/* 4563 */             this.cancelLock.enterExecuting();
/*      */             
/* 4565 */             executeForRows(false);
/*      */             
/* 4567 */             if (this.validRows > 0) {
/* 4568 */               m += this.validRows;
/*      */             }
/* 4570 */             n = this.validRows;
/*      */           }
/*      */           catch (SQLException localSQLException2)
/*      */           {
/* 4574 */             this.needToParse = true;
/* 4575 */             resetCurrentRowBinders();
/* 4576 */             throw localSQLException2;
/*      */           }
/*      */           finally
/*      */           {
/* 4580 */             if (this.queryTimeout != 0) {
/* 4581 */               this.connection.getTimeout().cancelTimeout();
/*      */             }
/* 4583 */             this.validRows = m;
/*      */             
/* 4585 */             this.cancelLock.exitExecuting();
/*      */             
/* 4587 */             checkValidRowsStatus();
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 4592 */           arrayOfInt[i] = n;
/*      */           
/* 4594 */           if (arrayOfInt[i] < 0)
/*      */           {
/*      */ 
/* 4597 */             localBatchUpdateException2 = DatabaseError.createBatchUpdateException(81, "command return value " + arrayOfInt[i], i, arrayOfInt);
/*      */             
/* 4599 */             localBatchUpdateException2.fillInStackTrace();
/* 4600 */             throw localBatchUpdateException2;
/*      */           }
/*      */           
/*      */         }
/*      */       }
/*      */       catch (SQLException localSQLException1)
/*      */       {
/* 4607 */         if ((localSQLException1 instanceof BatchUpdateException))
/*      */         {
/* 4609 */           throw localSQLException1;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 4614 */         BatchUpdateException localBatchUpdateException2 = DatabaseError.createBatchUpdateException(81, localSQLException1.getMessage(), i, arrayOfInt);
/* 4615 */         localBatchUpdateException2.fillInStackTrace();
/* 4616 */         throw localBatchUpdateException2;
/*      */ 
/*      */       }
/*      */       finally
/*      */       {
/*      */ 
/* 4622 */         clearBatchItems();
/*      */         
/* 4624 */         this.numberOfDefinePositions = k;
/*      */         
/* 4626 */         if (str != null)
/*      */         {
/* 4628 */           this.sqlObject.initialize(str);
/*      */           
/* 4630 */           this.sqlKind = localSqlKind;
/*      */         }
/*      */         
/* 4633 */         this.currentRank = 0;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4640 */       this.connection.registerHeartbeat();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4646 */       return arrayOfInt;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int copyBinds(Statement paramStatement, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4667 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void notifyCloseRset()
/*      */     throws SQLException
/*      */   {
/* 4678 */     this.scrollRset = null;
/* 4679 */     endOfResultSet(false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getOriginalSql()
/*      */     throws SQLException
/*      */   {
/* 4689 */     return this.sqlObject.getOriginalSql();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void doScrollExecuteCommon()
/*      */     throws SQLException
/*      */   {
/* 4699 */     if (this.scrollRset != null)
/*      */     {
/* 4701 */       this.scrollRset.close();
/*      */       
/* 4703 */       this.scrollRset = null;
/*      */     }
/*      */     
/*      */ 
/* 4707 */     if (!this.sqlKind.isSELECT())
/*      */     {
/* 4709 */       doExecuteWithTimeout();
/*      */       
/* 4711 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 4717 */     if (!this.needToAddIdentifier)
/*      */     {
/*      */ 
/*      */ 
/* 4721 */       doExecuteWithTimeout();
/*      */       
/* 4723 */       this.currentResultSet = new OracleResultSetImpl(this.connection, this);
/* 4724 */       this.realRsetType = this.userRsetType;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */       try
/*      */       {
/* 4732 */         this.sqlObject.setIncludeRowid(true);
/*      */         
/* 4734 */         this.needToParse = true;
/*      */         
/*      */ 
/*      */ 
/* 4738 */         prepareForNewResults(true, false);
/*      */         
/* 4740 */         if (this.columnsDefinedByUser)
/*      */         {
/* 4742 */           Accessor[] arrayOfAccessor = this.accessors;
/*      */           
/* 4744 */           if ((this.accessors == null) || (this.accessors.length <= this.numberOfDefinePositions)) {
/* 4745 */             this.accessors = new Accessor[this.numberOfDefinePositions + 1];
/*      */           }
/* 4747 */           if (arrayOfAccessor != null) {
/* 4748 */             for (i = this.numberOfDefinePositions; i > 0; i--)
/*      */             {
/* 4750 */               localAccessor = arrayOfAccessor[(i - 1)];
/*      */               
/* 4752 */               this.accessors[i] = localAccessor;
/*      */               
/* 4754 */               if (localAccessor.isColumnNumberAware)
/*      */               {
/*      */ 
/*      */ 
/*      */ 
/* 4759 */                 localAccessor.updateColumnNumber(i);
/*      */               }
/*      */             }
/*      */           }
/*      */           
/* 4764 */           allocateRowidAccessor();
/*      */           
/* 4766 */           this.numberOfDefinePositions += 1;
/*      */         }
/*      */         
/* 4769 */         doExecuteWithTimeout();
/*      */         
/* 4771 */         this.currentResultSet = new OracleResultSetImpl(this.connection, this);
/* 4772 */         this.realRsetType = this.userRsetType;
/*      */       }
/*      */       catch (SQLException localSQLException)
/*      */       {
/*      */         int i;
/*      */         
/*      */ 
/*      */         Accessor localAccessor;
/*      */         
/* 4781 */         if (this.userRsetType > 3) {
/* 4782 */           this.realRsetType = 3;
/*      */         } else {
/* 4784 */           this.realRsetType = 1;
/*      */         }
/* 4786 */         this.sqlObject.setIncludeRowid(false);
/*      */         
/* 4788 */         this.needToParse = true;
/*      */         
/*      */ 
/*      */ 
/* 4792 */         prepareForNewResults(true, false);
/*      */         
/* 4794 */         if (this.columnsDefinedByUser)
/*      */         {
/* 4796 */           this.needToPrepareDefineBuffer = true;
/* 4797 */           this.numberOfDefinePositions -= 1;
/*      */           
/* 4799 */           System.arraycopy(this.accessors, 1, this.accessors, 0, this.numberOfDefinePositions);
/*      */           
/* 4801 */           this.accessors[this.numberOfDefinePositions] = null;
/*      */           
/* 4803 */           for (i = 0; i < this.numberOfDefinePositions; i++)
/*      */           {
/* 4805 */             localAccessor = this.accessors[i];
/*      */             
/* 4807 */             if (localAccessor.isColumnNumberAware)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/* 4812 */               localAccessor.updateColumnNumber(i);
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 4819 */         moveAllTempLobsToFree();
/* 4820 */         doExecuteWithTimeout();
/*      */         
/* 4822 */         this.currentResultSet = new OracleResultSetImpl(this.connection, this);
/* 4823 */         this.sqlWarning = DatabaseError.addSqlWarning(this.sqlWarning, 91, localSQLException.getMessage());
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4831 */     this.scrollRset = ResultSetUtil.createScrollResultSet(this, this.currentResultSet, this.realRsetType);
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
/*      */   void allocateRowidAccessor()
/*      */     throws SQLException
/*      */   {
/* 4845 */     this.accessors[0] = new RowidAccessor(this, 128, 1, -8, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   OracleResultSet doScrollStmtExecuteQuery()
/*      */     throws SQLException
/*      */   {
/* 4854 */     doScrollExecuteCommon();
/*      */     
/* 4856 */     return this.scrollRset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void processDmlReturningBind()
/*      */     throws SQLException
/*      */   {
/* 4866 */     if (this.returnResultSet != null) { this.returnResultSet.close();
/*      */     }
/* 4868 */     this.returnParamsFetched = false;
/* 4869 */     this.returnParamRowBytes = 0;
/* 4870 */     this.returnParamRowChars = 0;
/*      */     
/* 4872 */     int i = 0;
/* 4873 */     for (int j = 0; j < this.numberOfBindPositions; j++)
/*      */     {
/* 4875 */       Accessor localAccessor = this.returnParamAccessors[j];
/*      */       
/* 4877 */       if (localAccessor != null)
/*      */       {
/* 4879 */         i++;
/*      */         
/* 4881 */         if (localAccessor.charLength > 0)
/*      */         {
/* 4883 */           this.returnParamRowChars += localAccessor.charLength;
/*      */         }
/*      */         else
/*      */         {
/* 4887 */           this.returnParamRowBytes += localAccessor.byteLength;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 4892 */     if (this.isAutoGeneratedKey)
/*      */     {
/* 4894 */       this.numReturnParams = i;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 4899 */       if (this.numReturnParams <= 0) {
/* 4900 */         this.numReturnParams = this.sqlObject.getReturnParameterCount();
/*      */       }
/* 4902 */       if (this.numReturnParams != i)
/*      */       {
/* 4904 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 173);
/* 4905 */         localSQLException.fillInStackTrace();
/* 4906 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 4911 */     this.returnParamMeta[0] = this.numReturnParams;
/* 4912 */     this.returnParamMeta[1] = this.returnParamRowBytes;
/* 4913 */     this.returnParamMeta[2] = this.returnParamRowChars;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void allocateDmlReturnStorage()
/*      */   {
/* 4921 */     if (this.rowsDmlReturned == 0) { return;
/*      */     }
/* 4923 */     int i = this.returnParamRowBytes * this.rowsDmlReturned;
/* 4924 */     int j = this.returnParamRowChars * this.rowsDmlReturned;
/* 4925 */     int k = 2 * this.numReturnParams * this.rowsDmlReturned;
/*      */     
/* 4927 */     this.returnParamBytes = new byte[i];
/* 4928 */     this.returnParamChars = new char[j];
/* 4929 */     this.returnParamIndicators = new short[k];
/*      */     
/*      */ 
/* 4932 */     for (int m = 0; m < this.numberOfBindPositions; m++)
/*      */     {
/* 4934 */       Accessor localAccessor = this.returnParamAccessors[m];
/*      */       
/*      */ 
/* 4937 */       if ((localAccessor != null) && ((localAccessor.internalType == 111) || (localAccessor.internalType == 109)))
/*      */       {
/*      */ 
/*      */ 
/* 4941 */         TypeAccessor localTypeAccessor = (TypeAccessor)localAccessor;
/* 4942 */         if ((localTypeAccessor.pickledBytes == null) || (localTypeAccessor.pickledBytes.length < this.rowsDmlReturned))
/*      */         {
/* 4944 */           localTypeAccessor.pickledBytes = new byte[this.rowsDmlReturned][];
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void fetchDmlReturnParams()
/*      */     throws SQLException
/*      */   {
/* 4958 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 4959 */     localSQLException.fillInStackTrace();
/* 4960 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void setupReturnParamAccessors()
/*      */   {
/* 4970 */     if (this.rowsDmlReturned == 0) { return;
/*      */     }
/* 4972 */     int i = 0;
/* 4973 */     int j = 0;
/* 4974 */     int k = 0;
/* 4975 */     int m = this.numReturnParams * this.rowsDmlReturned;
/*      */     
/* 4977 */     for (int n = 0; n < this.numberOfBindPositions; n++)
/*      */     {
/* 4979 */       Accessor localAccessor = this.returnParamAccessors[n];
/*      */       
/* 4981 */       if (localAccessor != null)
/*      */       {
/* 4983 */         if (localAccessor.charLength > 0)
/*      */         {
/* 4985 */           localAccessor.rowSpaceChar = this.returnParamChars;
/* 4986 */           localAccessor.columnIndex = j;
/* 4987 */           j += this.rowsDmlReturned * localAccessor.charLength;
/*      */         }
/*      */         else
/*      */         {
/* 4991 */           localAccessor.rowSpaceByte = this.returnParamBytes;
/* 4992 */           localAccessor.columnIndex = i;
/* 4993 */           i += this.rowsDmlReturned * localAccessor.byteLength;
/*      */         }
/*      */         
/* 4996 */         localAccessor.rowSpaceIndicator = this.returnParamIndicators;
/* 4997 */         localAccessor.indicatorIndex = k;
/* 4998 */         k += this.rowsDmlReturned;
/* 4999 */         localAccessor.lengthIndex = m;
/* 5000 */         m += this.rowsDmlReturned;
/*      */       }
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
/*      */   void registerReturnParameterInternal(int paramInt1, int paramInt2, int paramInt3, int paramInt4, short paramShort, String paramString)
/*      */     throws SQLException
/*      */   {
/* 5016 */     if (this.returnParamAccessors == null) {
/* 5017 */       this.returnParamAccessors = new Accessor[this.numberOfBindPositions];
/*      */     }
/* 5019 */     if (this.returnParamMeta == null)
/*      */     {
/* 5021 */       this.returnParamMeta = new int[3 + this.numberOfBindPositions * 4];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5030 */     switch (paramInt3) {
/*      */     case -16: 
/*      */     case -15: 
/*      */     case -9: 
/*      */     case 2011: 
/* 5035 */       paramShort = 2;
/* 5036 */       break;
/*      */     case 2009: 
/* 5038 */       paramString = "SYS.XMLTYPE";
/* 5039 */       break;
/*      */     }
/*      */     
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5048 */     Accessor localAccessor = allocateAccessor(paramInt2, paramInt3, paramInt1 + 1, paramInt4, paramShort, paramString, true);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5056 */     localAccessor.isDMLReturnedParam = true;
/* 5057 */     this.returnParamAccessors[paramInt1] = localAccessor;
/*      */     
/* 5059 */     int i = localAccessor.charLength > 0 ? 1 : 0;
/*      */     
/*      */ 
/* 5062 */     this.returnParamMeta[(3 + paramInt1 * 4 + 0)] = localAccessor.defineType;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 5067 */     this.returnParamMeta[(3 + paramInt1 * 4 + 1)] = (i != 0 ? 1 : 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 5072 */     this.returnParamMeta[(3 + paramInt1 * 4 + 2)] = (i != 0 ? localAccessor.charLength : localAccessor.byteLength);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 5077 */     this.returnParamMeta[(3 + paramInt1 * 4 + 3)] = paramShort;
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public int creationState()
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 71	oracle/jdbc/driver/OracleStatement:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 51	oracle/jdbc/driver/OracleStatement:creationState	I
/*      */     //   11: aload_1
/*      */     //   12: monitorexit
/*      */     //   13: ireturn
/*      */     //   14: astore_2
/*      */     //   15: aload_1
/*      */     //   16: monitorexit
/*      */     //   17: aload_2
/*      */     //   18: athrow
/*      */     // Line number table:
/*      */     //   Java source line #5098	-> byte code offset #0
/*      */     //   Java source line #5100	-> byte code offset #7
/*      */     //   Java source line #5102	-> byte code offset #14
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	19	0	this	OracleStatement
/*      */     //   5	11	1	Ljava/lang/Object;	Object
/*      */     //   14	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	13	14	finally
/*      */     //   14	17	14	finally
/*      */   }
/*      */   
/*      */   public boolean isColumnSetNull(int paramInt)
/*      */   {
/* 5119 */     return this.columnSetNull;
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
/*      */   public boolean isNCHAR(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 5137 */     if (!this.described) {
/* 5138 */       describe();
/*      */     }
/* 5140 */     int i = paramInt - 1;
/* 5141 */     if ((i < 0) || (i >= this.numberOfDefinePositions))
/*      */     {
/* 5143 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 5144 */       localSQLException.fillInStackTrace();
/* 5145 */       throw localSQLException;
/*      */     }
/*      */     
/* 5148 */     boolean bool = this.accessors[i].formOfUse == 2;
/*      */     
/*      */ 
/* 5151 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void addChild(OracleStatement paramOracleStatement)
/*      */   {
/* 5159 */     paramOracleStatement.nextChild = this.children;
/* 5160 */     this.children = paramOracleStatement;
/* 5161 */     paramOracleStatement.parent = this;
/*      */   }
/*      */   
/*      */ 
/*      */   void removeChild(OracleStatement paramOracleStatement)
/*      */   {
/* 5167 */     if (paramOracleStatement == this.children) {
/* 5168 */       this.children = paramOracleStatement.nextChild;
/*      */     }
/*      */     else {
/* 5171 */       OracleStatement localOracleStatement = this.children;
/* 5172 */       while (localOracleStatement.nextChild != paramOracleStatement) {
/* 5173 */         localOracleStatement = localOracleStatement.nextChild;
/*      */       }
/* 5175 */       localOracleStatement.nextChild = paramOracleStatement.nextChild;
/*      */     }
/* 5177 */     paramOracleStatement.parent = null;
/* 5178 */     paramOracleStatement.nextChild = null;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getMoreResults(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 5215 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 5216 */     localSQLException.fillInStackTrace();
/* 5217 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ResultSet getGeneratedKeys()
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5238 */     if (this.closed)
/*      */     {
/* 5240 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 5241 */       localSQLException.fillInStackTrace();
/* 5242 */       throw localSQLException;
/*      */     }
/*      */     
/* 5245 */     if (!this.isAutoGeneratedKey)
/*      */     {
/* 5247 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90);
/* 5248 */       localSQLException.fillInStackTrace();
/* 5249 */       throw localSQLException;
/*      */     }
/*      */     
/* 5252 */     if ((this.returnParamAccessors == null) || (this.numReturnParams == 0))
/*      */     {
/* 5254 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 144);
/* 5255 */       localSQLException.fillInStackTrace();
/* 5256 */       throw localSQLException;
/*      */     }
/*      */     
/* 5259 */     if (this.returnResultSet == null)
/*      */     {
/* 5261 */       this.returnResultSet = new OracleReturnResultSet(this);
/*      */     }
/*      */     
/* 5264 */     return this.returnResultSet;
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
/*      */ 
/*      */ 
/*      */   public int executeUpdate(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 5297 */     this.autoKeyInfo = new AutoKeyInfo(paramString);
/* 5298 */     if ((paramInt == 2) || (!this.autoKeyInfo.isInsertSqlStmt()))
/*      */     {
/*      */ 
/* 5301 */       this.autoKeyInfo = null;
/* 5302 */       return executeUpdate(paramString);
/*      */     }
/*      */     
/* 5305 */     if (paramInt != 1)
/*      */     {
/* 5307 */       this.autoKeyInfo = null;
/*      */       
/* 5309 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 5310 */       localSQLException.fillInStackTrace();
/* 5311 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 5315 */     synchronized (this.connection) {
/* 5316 */       this.isAutoGeneratedKey = true;
/* 5317 */       String str = this.autoKeyInfo.getNewSql();
/* 5318 */       this.numberOfBindPositions = 1;
/*      */       
/*      */ 
/* 5321 */       autoKeyRegisterReturnParams();
/*      */       
/* 5323 */       processDmlReturningBind();
/*      */       
/* 5325 */       return executeUpdateInternal(str);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int executeUpdate(String paramString, int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/* 5354 */     if ((paramArrayOfInt == null) || (paramArrayOfInt.length == 0))
/*      */     {
/* 5356 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 5357 */       localSQLException.fillInStackTrace();
/* 5358 */       throw localSQLException;
/*      */     }
/*      */     
/* 5361 */     this.autoKeyInfo = new AutoKeyInfo(paramString, paramArrayOfInt);
/* 5362 */     if (!this.autoKeyInfo.isInsertSqlStmt())
/*      */     {
/* 5364 */       this.autoKeyInfo = null;
/* 5365 */       return executeUpdate(paramString);
/*      */     }
/*      */     
/* 5368 */     synchronized (this.connection) {
/* 5369 */       this.isAutoGeneratedKey = true;
/*      */       
/*      */ 
/* 5372 */       this.connection.doDescribeTable(this.autoKeyInfo);
/*      */       
/* 5374 */       String str = this.autoKeyInfo.getNewSql();
/* 5375 */       this.numberOfBindPositions = paramArrayOfInt.length;
/*      */       
/*      */ 
/* 5378 */       autoKeyRegisterReturnParams();
/*      */       
/* 5380 */       processDmlReturningBind();
/*      */       
/* 5382 */       return executeUpdateInternal(str);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int executeUpdate(String paramString, String[] paramArrayOfString)
/*      */     throws SQLException
/*      */   {
/* 5410 */     if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
/*      */     {
/* 5412 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 5413 */       localSQLException.fillInStackTrace();
/* 5414 */       throw localSQLException;
/*      */     }
/*      */     
/* 5417 */     this.autoKeyInfo = new AutoKeyInfo(paramString, paramArrayOfString);
/* 5418 */     if (!this.autoKeyInfo.isInsertSqlStmt())
/*      */     {
/* 5420 */       this.autoKeyInfo = null;
/* 5421 */       return executeUpdate(paramString);
/*      */     }
/*      */     
/* 5424 */     synchronized (this.connection) {
/* 5425 */       this.isAutoGeneratedKey = true;
/*      */       
/*      */ 
/* 5428 */       this.connection.doDescribeTable(this.autoKeyInfo);
/*      */       
/* 5430 */       String str = this.autoKeyInfo.getNewSql();
/* 5431 */       this.numberOfBindPositions = paramArrayOfString.length;
/*      */       
/*      */ 
/* 5434 */       autoKeyRegisterReturnParams();
/*      */       
/* 5436 */       processDmlReturningBind();
/*      */       
/* 5438 */       return executeUpdateInternal(str);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean execute(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 5485 */     this.autoKeyInfo = new AutoKeyInfo(paramString);
/* 5486 */     if ((paramInt == 2) || (!this.autoKeyInfo.isInsertSqlStmt()))
/*      */     {
/*      */ 
/* 5489 */       this.autoKeyInfo = null;
/* 5490 */       return execute(paramString);
/*      */     }
/*      */     
/* 5493 */     if (paramInt != 1)
/*      */     {
/* 5495 */       this.autoKeyInfo = null;
/*      */       
/* 5497 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 5498 */       localSQLException.fillInStackTrace();
/* 5499 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 5503 */     synchronized (this.connection) {
/* 5504 */       this.isAutoGeneratedKey = true;
/* 5505 */       String str = this.autoKeyInfo.getNewSql();
/* 5506 */       this.numberOfBindPositions = 1;
/*      */       
/*      */ 
/* 5509 */       autoKeyRegisterReturnParams();
/*      */       
/* 5511 */       processDmlReturningBind();
/*      */       
/* 5513 */       return executeInternal(str);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean execute(String paramString, int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/* 5559 */     if ((paramArrayOfInt == null) || (paramArrayOfInt.length == 0))
/*      */     {
/* 5561 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 5562 */       localSQLException.fillInStackTrace();
/* 5563 */       throw localSQLException;
/*      */     }
/*      */     
/* 5566 */     this.autoKeyInfo = new AutoKeyInfo(paramString, paramArrayOfInt);
/* 5567 */     if (!this.autoKeyInfo.isInsertSqlStmt())
/*      */     {
/* 5569 */       this.autoKeyInfo = null;
/* 5570 */       return execute(paramString);
/*      */     }
/*      */     
/* 5573 */     synchronized (this.connection) {
/* 5574 */       this.isAutoGeneratedKey = true;
/*      */       
/*      */ 
/* 5577 */       this.connection.doDescribeTable(this.autoKeyInfo);
/*      */       
/* 5579 */       String str = this.autoKeyInfo.getNewSql();
/* 5580 */       this.numberOfBindPositions = paramArrayOfInt.length;
/*      */       
/*      */ 
/* 5583 */       autoKeyRegisterReturnParams();
/*      */       
/* 5585 */       processDmlReturningBind();
/*      */       
/* 5587 */       return executeInternal(str);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean execute(String paramString, String[] paramArrayOfString)
/*      */     throws SQLException
/*      */   {
/* 5634 */     if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
/*      */     {
/* 5636 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 5637 */       localSQLException.fillInStackTrace();
/* 5638 */       throw localSQLException;
/*      */     }
/*      */     
/* 5641 */     this.autoKeyInfo = new AutoKeyInfo(paramString, paramArrayOfString);
/* 5642 */     if (!this.autoKeyInfo.isInsertSqlStmt())
/*      */     {
/* 5644 */       this.autoKeyInfo = null;
/* 5645 */       return execute(paramString);
/*      */     }
/*      */     
/* 5648 */     synchronized (this.connection) {
/* 5649 */       this.isAutoGeneratedKey = true;
/*      */       
/*      */ 
/* 5652 */       this.connection.doDescribeTable(this.autoKeyInfo);
/*      */       
/* 5654 */       String str = this.autoKeyInfo.getNewSql();
/* 5655 */       this.numberOfBindPositions = paramArrayOfString.length;
/*      */       
/*      */ 
/* 5658 */       autoKeyRegisterReturnParams();
/*      */       
/* 5660 */       processDmlReturningBind();
/*      */       
/* 5662 */       return executeInternal(str);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getResultSetHoldability()
/*      */     throws SQLException
/*      */   {
/* 5682 */     return 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getcacheState()
/*      */   {
/* 5689 */     return this.cacheState;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getstatementType()
/*      */   {
/* 5696 */     return this.statementType;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean getserverCursor()
/*      */   {
/* 5703 */     return this.serverCursor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void initializeIndicatorSubRange()
/*      */   {
/* 5711 */     this.bindIndicatorSubRange = 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void autoKeyRegisterReturnParams()
/*      */     throws SQLException
/*      */   {
/* 5722 */     initializeIndicatorSubRange();
/*      */     
/* 5724 */     int i = this.bindIndicatorSubRange + 5 + this.numberOfBindPositions * 10;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5730 */     int j = i + 2 * this.numberOfBindPositions;
/*      */     
/*      */ 
/*      */ 
/* 5734 */     this.bindIndicators = new short[j];
/*      */     
/* 5736 */     int k = this.bindIndicatorSubRange;
/*      */     
/* 5738 */     this.bindIndicators[(k + 0)] = ((short)this.numberOfBindPositions);
/*      */     
/*      */ 
/*      */ 
/* 5742 */     this.bindIndicators[(k + 1)] = 0;
/*      */     
/*      */ 
/*      */ 
/* 5746 */     this.bindIndicators[(k + 2)] = 1;
/*      */     
/*      */ 
/*      */ 
/* 5750 */     this.bindIndicators[(k + 3)] = 0;
/*      */     
/*      */ 
/*      */ 
/* 5754 */     this.bindIndicators[(k + 4)] = 1;
/*      */     
/*      */ 
/*      */ 
/* 5758 */     k += 5;
/*      */     
/*      */ 
/* 5761 */     short[] arrayOfShort = this.autoKeyInfo.tableFormOfUses;
/* 5762 */     int[] arrayOfInt = this.autoKeyInfo.columnIndexes;
/*      */     
/* 5764 */     for (int m = 0; m < this.numberOfBindPositions; m++)
/*      */     {
/* 5766 */       this.bindIndicators[(k + 0)] = 994;
/*      */       
/*      */ 
/*      */ 
/* 5770 */       short s = this.connection.defaultnchar ? 2 : 1;
/*      */       
/*      */ 
/* 5773 */       if ((arrayOfShort != null) && (arrayOfInt != null))
/*      */       {
/* 5775 */         if (arrayOfShort[(arrayOfInt[m] - 1)] == 2)
/*      */         {
/*      */ 
/* 5778 */           s = 2;
/* 5779 */           this.bindIndicators[(k + 9)] = s;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 5785 */       k += 10;
/*      */       
/*      */ 
/* 5788 */       checkTypeForAutoKey(this.autoKeyInfo.returnTypes[m]);
/*      */       
/* 5790 */       String str = null;
/* 5791 */       if (this.autoKeyInfo.returnTypes[m] == 111) {
/* 5792 */         str = this.autoKeyInfo.tableTypeNames[(arrayOfInt[m] - 1)];
/*      */       }
/*      */       
/* 5795 */       registerReturnParameterInternal(m, this.autoKeyInfo.returnTypes[m], this.autoKeyInfo.returnTypes[m], -1, s, str);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void setNonAutoKey()
/*      */   {
/* 5805 */     this.isAutoGeneratedKey = false;
/* 5806 */     this.numberOfBindPositions = 0;
/* 5807 */     this.bindIndicators = null;
/* 5808 */     this.returnParamMeta = null;
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
/*      */   void saveDefineBuffersIfRequired(char[] paramArrayOfChar, byte[] paramArrayOfByte, short[] paramArrayOfShort, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 5825 */     if (paramArrayOfChar != this.defineChars) this.connection.cacheBuffer(paramArrayOfChar);
/* 5826 */     if (paramArrayOfByte != this.defineBytes) { this.connection.cacheBuffer(paramArrayOfByte);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   final void checkTypeForAutoKey(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 5834 */     if (paramInt == 109)
/*      */     {
/* 5836 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 5);
/* 5837 */       localSQLException.fillInStackTrace();
/* 5838 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/* 5843 */   ArrayList tempClobsToFree = null;
/* 5844 */   ArrayList tempBlobsToFree = null;
/*      */   
/* 5846 */   ArrayList oldTempClobsToFree = null;
/* 5847 */   ArrayList oldTempBlobsToFree = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void moveAllTempLobsToFree()
/*      */   {
/* 5859 */     if (this.oldTempClobsToFree != null)
/*      */     {
/* 5861 */       if (this.tempClobsToFree == null) {
/* 5862 */         this.tempClobsToFree = this.oldTempClobsToFree;
/*      */       } else {
/* 5864 */         this.tempClobsToFree.add(this.oldTempClobsToFree);
/*      */       }
/* 5866 */       this.oldTempClobsToFree = null;
/*      */     }
/*      */     
/* 5869 */     if (this.oldTempBlobsToFree != null)
/*      */     {
/* 5871 */       if (this.tempBlobsToFree == null) {
/* 5872 */         this.tempBlobsToFree = this.oldTempBlobsToFree;
/*      */       } else {
/* 5874 */         this.tempBlobsToFree.add(this.oldTempBlobsToFree);
/*      */       }
/* 5876 */       this.oldTempBlobsToFree = null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void moveTempLobsToFree(CLOB paramCLOB)
/*      */   {
/*      */     int i;
/*      */     
/* 5886 */     if ((this.oldTempClobsToFree != null) && 
/* 5887 */       ((i = this.oldTempClobsToFree.indexOf(paramCLOB)) != -1))
/*      */     {
/* 5889 */       addToTempLobsToFree(paramCLOB);
/* 5890 */       this.oldTempClobsToFree.remove(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void moveTempLobsToFree(BLOB paramBLOB)
/*      */   {
/*      */     int i;
/*      */     
/* 5900 */     if ((this.oldTempBlobsToFree != null) && 
/* 5901 */       ((i = this.oldTempBlobsToFree.indexOf(paramBLOB)) != -1))
/*      */     {
/* 5903 */       addToTempLobsToFree(paramBLOB);
/* 5904 */       this.oldTempBlobsToFree.remove(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void addToTempLobsToFree(CLOB paramCLOB)
/*      */   {
/* 5913 */     if (this.tempClobsToFree == null)
/* 5914 */       this.tempClobsToFree = new ArrayList();
/* 5915 */     this.tempClobsToFree.add(paramCLOB);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void addToTempLobsToFree(BLOB paramBLOB)
/*      */   {
/* 5922 */     if (this.tempBlobsToFree == null)
/* 5923 */       this.tempBlobsToFree = new ArrayList();
/* 5924 */     this.tempBlobsToFree.add(paramBLOB);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void addToOldTempLobsToFree(CLOB paramCLOB)
/*      */   {
/* 5931 */     if (this.oldTempClobsToFree == null)
/* 5932 */       this.oldTempClobsToFree = new ArrayList();
/* 5933 */     this.oldTempClobsToFree.add(paramCLOB);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void addToOldTempLobsToFree(BLOB paramBLOB)
/*      */   {
/* 5940 */     if (this.oldTempBlobsToFree == null)
/* 5941 */       this.oldTempBlobsToFree = new ArrayList();
/* 5942 */     this.oldTempBlobsToFree.add(paramBLOB);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void cleanAllTempLobs()
/*      */   {
/* 5949 */     cleanTempClobs(this.tempClobsToFree);
/* 5950 */     this.tempClobsToFree = null;
/* 5951 */     cleanTempBlobs(this.tempBlobsToFree);
/* 5952 */     this.tempBlobsToFree = null;
/* 5953 */     cleanTempClobs(this.oldTempClobsToFree);
/* 5954 */     this.oldTempClobsToFree = null;
/* 5955 */     cleanTempBlobs(this.oldTempBlobsToFree);
/* 5956 */     this.oldTempBlobsToFree = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void cleanOldTempLobs()
/*      */   {
/* 5963 */     cleanTempClobs(this.oldTempClobsToFree);
/* 5964 */     cleanTempBlobs(this.oldTempBlobsToFree);
/* 5965 */     this.oldTempClobsToFree = this.tempClobsToFree;
/* 5966 */     this.tempClobsToFree = null;
/* 5967 */     this.oldTempBlobsToFree = this.tempBlobsToFree;
/* 5968 */     this.tempBlobsToFree = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void cleanTempClobs(ArrayList paramArrayList)
/*      */   {
/* 5975 */     if (paramArrayList != null)
/*      */     {
/* 5977 */       Iterator localIterator = paramArrayList.iterator();
/*      */       
/* 5979 */       while (localIterator.hasNext())
/*      */       {
/*      */         try
/*      */         {
/* 5983 */           ((CLOB)localIterator.next()).freeTemporary();
/*      */         }
/*      */         catch (SQLException localSQLException) {}
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void cleanTempBlobs(ArrayList paramArrayList)
/*      */   {
/* 5998 */     if (paramArrayList != null)
/*      */     {
/* 6000 */       Iterator localIterator = paramArrayList.iterator();
/*      */       
/* 6002 */       while (localIterator.hasNext())
/*      */       {
/*      */         try
/*      */         {
/* 6006 */           ((BLOB)localIterator.next()).freeTemporary();
/*      */         }
/*      */         catch (SQLException localSQLException) {}
/*      */       }
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
/*      */ 
/*      */ 
/*      */   TimeZone getDefaultTimeZone()
/*      */     throws SQLException
/*      */   {
/* 6026 */     return getDefaultTimeZone(false);
/*      */   }
/*      */   
/*      */   TimeZone getDefaultTimeZone(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 6032 */     if (this.defaultTimeZone == null)
/*      */     {
/*      */       try {
/* 6035 */         this.defaultTimeZone = this.connection.getDefaultTimeZone();
/*      */       }
/*      */       catch (SQLException localSQLException) {}
/*      */       
/*      */ 
/* 6040 */       if (this.defaultTimeZone == null) {
/* 6041 */         this.defaultTimeZone = TimeZone.getDefault();
/*      */       }
/*      */     }
/* 6044 */     return this.defaultTimeZone;
/*      */   }
/*      */   
/*      */ 
/* 6048 */   NTFDCNRegistration registration = null;
/* 6049 */   String[] dcnTableName = null;
/* 6050 */   long dcnQueryId = -1L;
/*      */   
/*      */   public void setDatabaseChangeRegistration(DatabaseChangeRegistration paramDatabaseChangeRegistration)
/*      */     throws SQLException
/*      */   {
/* 6055 */     this.registration = ((NTFDCNRegistration)paramDatabaseChangeRegistration);
/*      */   }
/*      */   
/*      */   public String[] getRegisteredTableNames() throws SQLException
/*      */   {
/* 6060 */     return this.dcnTableName;
/*      */   }
/*      */   
/*      */   public long getRegisteredQueryId() throws SQLException {
/* 6064 */     return this.dcnQueryId;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   Calendar getDefaultCalendar()
/*      */     throws SQLException
/*      */   {
/* 6072 */     if (this.defaultCalendar == null)
/*      */     {
/* 6074 */       this.defaultCalendar = Calendar.getInstance(getDefaultTimeZone(), Locale.US);
/*      */     }
/*      */     
/* 6077 */     return this.defaultCalendar;
/*      */   }
/*      */   
/*      */ 
/*      */   void releaseBuffers()
/*      */   {
/* 6083 */     this.cachedDefineIndicatorSize = (this.defineIndicators != null ? this.defineIndicators.length : 0);
/* 6084 */     this.cachedDefineMetaDataSize = (this.defineMetaData != null ? this.defineMetaData.length : 0);
/* 6085 */     this.connection.cacheBuffer(this.defineChars);
/* 6086 */     this.defineChars = null;
/* 6087 */     this.connection.cacheBuffer(this.defineBytes);
/* 6088 */     this.defineBytes = null;
/* 6089 */     this.defineIndicators = null;
/* 6090 */     this.defineMetaData = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isClosed()
/*      */     throws SQLException
/*      */   {
/* 6098 */     return false;
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
/* 6110 */     if (this.closed)
/*      */     {
/* 6112 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 6113 */       localSQLException.fillInStackTrace();
/* 6114 */       throw localSQLException;
/*      */     }
/*      */     
/* 6117 */     return this.cacheState != 3;
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
/*      */   public void setPoolable(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 6133 */     if (this.closed)
/*      */     {
/* 6135 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 6136 */       localSQLException.fillInStackTrace();
/* 6137 */       throw localSQLException;
/*      */     }
/*      */     
/* 6140 */     if (paramBoolean) {
/* 6141 */       this.cacheState = 1;
/*      */     }
/*      */     else {
/* 6144 */       this.cacheState = 3;
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
/*      */ 
/*      */   public boolean isWrapperFor(Class<?> paramClass)
/*      */     throws SQLException
/*      */   {
/* 6160 */     if (paramClass.isInterface()) { return paramClass.isInstance(this);
/*      */     }
/* 6162 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 6163 */     localSQLException.fillInStackTrace();
/* 6164 */     throw localSQLException;
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
/* 6181 */     if ((paramClass.isInterface()) && (paramClass.isInstance(this))) { return this;
/*      */     }
/* 6183 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 6184 */     localSQLException.fillInStackTrace();
/* 6185 */     throw localSQLException;
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
/* 6202 */     return this.connection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   Calendar getGMTCalendar()
/*      */   {
/* 6213 */     if (this.gmtCalendar == null)
/*      */     {
/* 6215 */       this.gmtCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.US);
/*      */     }
/*      */     
/*      */ 
/* 6219 */     return this.gmtCalendar;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void extractNioDefineBuffers(int paramInt)
/*      */     throws SQLException
/*      */   {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void processLobPrefetchMetaData(Object[] paramArrayOfObject) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void internalClose()
/*      */     throws SQLException
/*      */   {
/* 6244 */     this.closed = true;
/* 6245 */     if (this.currentResultSet != null) {
/* 6246 */       this.currentResultSet.closed = true;
/*      */     }
/* 6248 */     cleanupDefines();
/* 6249 */     this.bindBytes = null;
/* 6250 */     this.bindChars = null;
/* 6251 */     this.bindIndicators = null;
/*      */     
/* 6253 */     this.outBindAccessors = null;
/* 6254 */     this.parameterStream = ((InputStream[][])null);
/* 6255 */     this.userStream = ((Object[][])null);
/*      */     
/* 6257 */     this.ibtBindBytes = null;
/* 6258 */     this.ibtBindChars = null;
/* 6259 */     this.ibtBindIndicators = null;
/*      */     
/* 6261 */     this.lobPrefetchMetaData = null;
/* 6262 */     this.tmpByteArray = null;
/*      */     
/* 6264 */     this.definedColumnType = null;
/* 6265 */     this.definedColumnSize = null;
/* 6266 */     this.definedColumnFormOfUse = null;
/*      */     
/*      */ 
/*      */ 
/* 6270 */     if (this.wrapper != null) {
/* 6271 */       this.wrapper.close();
/*      */     }
/*      */   }
/*      */   
/* 6275 */   long _checkSum = 0L;
/*      */   static final byte IS_UNINITIALIZED = 0;
/*      */   
/* 6278 */   void calculateCheckSum() throws SQLException { if (!this.connection.calculateChecksum) {
/* 6279 */       return;
/*      */     }
/* 6281 */     this._checkSum = this.checkSum;
/* 6282 */     if (this.accessors != null) {
/* 6283 */       accessorChecksum(this.accessors);
/*      */     }
/* 6285 */     if (this.outBindAccessors != null) {
/* 6286 */       accessorChecksum(this.outBindAccessors);
/*      */     }
/* 6288 */     if ((this.returnParamAccessors != null) && (this.returnParamsFetched)) {
/* 6289 */       accessorChecksum(this.returnParamAccessors);
/*      */     }
/* 6291 */     this._checkSum = CRC64.updateChecksum(this._checkSum, this.validRows);
/* 6292 */     this.checkSum = this._checkSum;
/* 6293 */     this._checkSum = 0L;
/*      */   }
/*      */   
/*      */   void accessorChecksum(Accessor[] paramArrayOfAccessor) throws SQLException
/*      */   {
/* 6298 */     int i = 0;
/* 6299 */     int j = 0;
/*      */     
/*      */ 
/* 6302 */     for (Accessor localAccessor : paramArrayOfAccessor)
/*      */     {
/* 6304 */       if (localAccessor != null)
/*      */       {
/* 6306 */         switch (localAccessor.internalType)
/*      */         {
/*      */         case 112: 
/*      */         case 113: 
/*      */         case 114: 
/* 6311 */           if (i == 0) {
/* 6312 */             j = 1;
/*      */           }
/*      */           
/*      */ 
/*      */           break;
/*      */         case 8: 
/*      */         case 24: 
/* 6319 */           j = 0;
/*      */           
/* 6321 */           break;
/*      */         
/*      */         default: 
/* 6324 */           j = 0;
/*      */           
/*      */ 
/*      */ 
/* 6328 */           i++;
/* 6329 */           for (int n = 0; n < this.validRows; n++)
/*      */           {
/* 6331 */             if (localAccessor.rowSpaceIndicator != null)
/* 6332 */               this._checkSum = localAccessor.updateChecksum(this._checkSum, n);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 6337 */     if (j != 0) {
/* 6338 */       this.checkSumComputationFailure = true;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public long getChecksum()
/*      */     throws SQLException
/*      */   {
/* 6346 */     if (this.checkSumComputationFailure)
/*      */     {
/* 6348 */       SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 6349 */       localSQLException.fillInStackTrace();
/* 6350 */       throw localSQLException;
/*      */     }
/*      */     
/* 6353 */     return this.checkSum;
/*      */   }
/*      */   
/*      */ 
/*      */   static final byte IS_SELECT = 1;
/*      */   
/*      */   static final byte IS_DELETE = 2;
/*      */   static final byte IS_INSERT = 4;
/*      */   static final byte IS_MERGE = 8;
/*      */   static final byte IS_UPDATE = 16;
/*      */   static final byte IS_PLSQL_BLOCK = 32;
/*      */   static final byte IS_CALL_BLOCK = 64;
/*      */   static final byte IS_OTHER = -128;
/*      */   static final byte IS_DML = 30;
/*      */   static final byte IS_PLSQL = 96;
/*      */   static final byte convertSqlKindEnumToByte(OracleStatement.SqlKind paramSqlKind)
/*      */   {
/* 6370 */     switch (paramSqlKind)
/*      */     {
/*      */     case DELETE: 
/* 6373 */       return 2;
/*      */     
/*      */     case INSERT: 
/* 6376 */       return 4;
/*      */     
/*      */     case MERGE: 
/* 6379 */       return 8;
/*      */     
/*      */     case UPDATE: 
/* 6382 */       return 16;
/*      */     
/*      */     case ALTER_SESSION: 
/*      */     case OTHER: 
/* 6386 */       return Byte.MIN_VALUE;
/*      */     
/*      */     case PLSQL_BLOCK: 
/* 6389 */       return 32;
/*      */     
/*      */     case CALL_BLOCK: 
/* 6392 */       return 64;
/*      */     
/*      */     case SELECT_FOR_UPDATE: 
/*      */     case SELECT: 
/* 6396 */       return 1;
/*      */     }
/*      */     
/* 6399 */     if (paramSqlKind.isPlsqlOrCall())
/* 6400 */       return 96;
/* 6401 */     if (paramSqlKind.isDML()) {
/* 6402 */       return 30;
/*      */     }
/* 6404 */     return 0;
/*      */   }
/*      */   
/*      */   static final OracleStatement.SqlKind convertSqlKindByteToEnum(byte paramByte)
/*      */   {
/* 6409 */     switch (paramByte)
/*      */     {
/*      */     case 2: 
/* 6412 */       return OracleStatement.SqlKind.DELETE;
/*      */     
/*      */     case 4: 
/* 6415 */       return OracleStatement.SqlKind.INSERT;
/*      */     
/*      */     case 8: 
/* 6418 */       return OracleStatement.SqlKind.MERGE;
/*      */     
/*      */     case 16: 
/* 6421 */       return OracleStatement.SqlKind.UPDATE;
/*      */     
/*      */     case -128: 
/* 6424 */       return OracleStatement.SqlKind.OTHER;
/*      */     
/*      */     case 32: 
/* 6427 */       return OracleStatement.SqlKind.PLSQL_BLOCK;
/*      */     
/*      */     case 64: 
/* 6430 */       return OracleStatement.SqlKind.CALL_BLOCK;
/*      */     
/*      */     case 1: 
/* 6433 */       return OracleStatement.SqlKind.SELECT;
/*      */     }
/*      */     
/*      */     
/* 6437 */     return OracleStatement.SqlKind.UNINITIALIZED;
/*      */   }
/*      */   
/*      */ 
/* 6441 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */