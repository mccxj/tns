/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.Writer;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.sql.Array;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Date;
/*      */ import java.sql.NClob;
/*      */ import java.sql.PreparedStatement;
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
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import oracle.jdbc.OracleData;
/*      */ import oracle.jdbc.OracleResultSet.AuthorizationIndicator;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.proxy.OracleProxy;
/*      */ import oracle.jdbc.proxy.ProxyFactory;
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
/*      */ import oracle.sql.NCLOB;
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
/*      */ class UpdatableResultSet
/*      */   extends BaseResultSet
/*      */ {
/*      */   static final int concurrencyType = 1008;
/*      */   static final int BEGIN_COLUMN_INDEX = 1;
/*      */   static final int MAX_CHAR_BUFFER_SIZE = 1024;
/*      */   static final int MAX_BYTE_BUFFER_SIZE = 1024;
/*      */   PhysicalConnection connection;
/*      */   OracleResultSet resultSet;
/*      */   boolean isCachedRset;
/*      */   ScrollRsetStatement scrollStmt;
/*      */   ResultSetMetaData rsetMetaData;
/*      */   private int rsetType;
/*      */   private int columnCount;
/*      */   private OraclePreparedStatement deleteStmt;
/*      */   private OraclePreparedStatement insertStmt;
/*      */   private OraclePreparedStatement updateStmt;
/*      */   private int[] indexColsChanged;
/*      */   private Object[] rowBuffer;
/*      */   private boolean[] m_nullIndicator;
/*      */   private int[][] typeInfo;
/*      */   private boolean isInserting;
/*      */   private boolean isUpdating;
/*      */   private int wasNull;
/*      */   private static final int VALUE_NULL = 1;
/*      */   private static final int VALUE_NOT_NULL = 2;
/*      */   private static final int VALUE_UNKNOWN = 3;
/*      */   private static final int VALUE_IN_RSET = 4;
/*      */   private static final int ASCII_STREAM = 1;
/*      */   private static final int BINARY_STREAM = 2;
/*      */   private static final int UNICODE_STREAM = 3;
/*  110 */   private static int _MIN_STREAM_SIZE = 4000;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   UpdatableResultSet(ScrollRsetStatement paramScrollRsetStatement, ScrollableResultSet paramScrollableResultSet, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  123 */     init(paramScrollRsetStatement, paramScrollableResultSet, paramInt1, paramInt2);
/*      */     
/*      */ 
/*  126 */     paramScrollableResultSet.resetBeginColumnIndex();
/*  127 */     getInternalMetadata();
/*      */     
/*  129 */     this.isCachedRset = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   UpdatableResultSet(ScrollRsetStatement paramScrollRsetStatement, OracleResultSetImpl paramOracleResultSetImpl, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  140 */     init(paramScrollRsetStatement, paramOracleResultSetImpl, paramInt1, paramInt2);
/*  141 */     getInternalMetadata();
/*      */     
/*  143 */     this.isCachedRset = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void init(ScrollRsetStatement paramScrollRsetStatement, OracleResultSet paramOracleResultSet, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  155 */     if ((paramScrollRsetStatement == null) || (paramOracleResultSet == null) || (paramInt2 != 1008))
/*      */     {
/*  157 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  158 */       localSQLException.fillInStackTrace();
/*  159 */       throw localSQLException;
/*      */     }
/*      */     
/*  162 */     this.connection = ((OracleStatement)paramScrollRsetStatement).connection;
/*  163 */     this.resultSet = paramOracleResultSet;
/*  164 */     this.scrollStmt = paramScrollRsetStatement;
/*  165 */     this.rsetType = paramInt1;
/*  166 */     this.deleteStmt = null;
/*  167 */     this.insertStmt = null;
/*  168 */     this.updateStmt = null;
/*  169 */     this.indexColsChanged = null;
/*  170 */     this.rowBuffer = null;
/*  171 */     this.m_nullIndicator = null;
/*  172 */     this.typeInfo = ((int[][])null);
/*  173 */     this.isInserting = false;
/*  174 */     this.isUpdating = false;
/*  175 */     this.wasNull = -1;
/*  176 */     this.rsetMetaData = null;
/*  177 */     this.columnCount = 0;
/*      */   }
/*      */   
/*      */   void ensureOpen() throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*  183 */     if (this.closed)
/*      */     {
/*  185 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  186 */       localSQLException.fillInStackTrace();
/*  187 */       throw localSQLException;
/*      */     }
/*      */     
/*  190 */     if ((this.resultSet == null) || (this.scrollStmt == null) || (((OracleStatement)this.scrollStmt).closed))
/*      */     {
/*      */ 
/*  193 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  194 */       localSQLException.fillInStackTrace();
/*  195 */       throw localSQLException;
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
/*      */   public void close()
/*      */     throws SQLException
/*      */   {
/*  209 */     if (this.closed) return;
/*  210 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  213 */       super.close();
/*      */       
/*  215 */       if (this.resultSet != null)
/*  216 */         this.resultSet.close();
/*  217 */       if (this.insertStmt != null)
/*  218 */         this.insertStmt.close();
/*  219 */       if (this.updateStmt != null)
/*  220 */         this.updateStmt.close();
/*  221 */       if (this.deleteStmt != null)
/*  222 */         this.deleteStmt.close();
/*  223 */       if (this.scrollStmt != null) {
/*  224 */         this.scrollStmt.notifyCloseRset();
/*      */       }
/*  226 */       cancelRowInserts();
/*      */       
/*  228 */       this.connection = LogicalConnection.closedConnection;
/*  229 */       this.resultSet = null;
/*  230 */       this.scrollStmt = null;
/*  231 */       this.rsetMetaData = null;
/*  232 */       this.scrollStmt = null;
/*  233 */       this.deleteStmt = null;
/*  234 */       this.insertStmt = null;
/*  235 */       this.updateStmt = null;
/*  236 */       this.indexColsChanged = null;
/*  237 */       this.rowBuffer = null;
/*  238 */       this.m_nullIndicator = null;
/*  239 */       this.typeInfo = ((int[][])null);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean wasNull()
/*      */     throws SQLException
/*      */   {
/*  246 */     synchronized (this.connection)
/*      */     {
/*  248 */       ensureOpen();
/*  249 */       switch (this.wasNull)
/*      */       {
/*      */ 
/*      */       case 1: 
/*  253 */         return true;
/*      */       
/*      */       case 2: 
/*  256 */         return false;
/*      */       
/*      */       case 4: 
/*  259 */         return this.resultSet.wasNull();
/*      */       }
/*      */       
/*      */       
/*      */ 
/*      */ 
/*  265 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 24);
/*  266 */       localSQLException.fillInStackTrace();
/*  267 */       throw localSQLException;
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
/*      */   int getFirstUserColumnIndex()
/*      */   {
/*  286 */     return 1;
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public Statement getStatement()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 13	oracle/jdbc/driver/UpdatableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 15	oracle/jdbc/driver/UpdatableResultSet:scrollStmt	Loracle/jdbc/driver/ScrollRsetStatement;
/*      */     //   11: checkcast 40	java/sql/Statement
/*      */     //   14: aload_1
/*      */     //   15: monitorexit
/*      */     //   16: areturn
/*      */     //   17: astore_2
/*      */     //   18: aload_1
/*      */     //   19: monitorexit
/*      */     //   20: aload_2
/*      */     //   21: athrow
/*      */     // Line number table:
/*      */     //   Java source line #293	-> byte code offset #0
/*      */     //   Java source line #295	-> byte code offset #7
/*      */     //   Java source line #297	-> byte code offset #17
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	22	0	this	UpdatableResultSet
/*      */     //   5	14	1	Ljava/lang/Object;	Object
/*      */     //   17	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	16	17	finally
/*      */     //   17	20	17	finally
/*      */   }
/*      */   
/*      */   public SQLWarning getWarnings()
/*      */     throws SQLException
/*      */   {
/*  303 */     SQLWarning localSQLWarning1 = this.resultSet.getWarnings();
/*      */     
/*  305 */     if (this.sqlWarning == null) {
/*  306 */       return localSQLWarning1;
/*      */     }
/*      */     
/*  309 */     SQLWarning localSQLWarning2 = this.sqlWarning;
/*      */     
/*  311 */     while (localSQLWarning2.getNextWarning() != null) {
/*  312 */       localSQLWarning2 = localSQLWarning2.getNextWarning();
/*      */     }
/*  314 */     localSQLWarning2.setNextWarning(localSQLWarning1);
/*      */     
/*      */ 
/*  317 */     return this.sqlWarning;
/*      */   }
/*      */   
/*      */ 
/*      */   public void clearWarnings()
/*      */     throws SQLException
/*      */   {
/*  324 */     this.sqlWarning = null;
/*      */     
/*  326 */     this.resultSet.clearWarnings();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean next()
/*      */     throws SQLException
/*      */   {
/*  336 */     synchronized (this.connection)
/*      */     {
/*  338 */       ensureOpen();
/*  339 */       cancelRowChanges();
/*      */       
/*  341 */       return this.resultSet.next();
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isBeforeFirst()
/*      */     throws SQLException
/*      */   {
/*  348 */     synchronized (this.connection)
/*      */     {
/*  350 */       ensureOpen();
/*  351 */       return this.resultSet.isBeforeFirst();
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isAfterLast()
/*      */     throws SQLException
/*      */   {
/*  358 */     synchronized (this.connection)
/*      */     {
/*  360 */       ensureOpen();
/*  361 */       return this.resultSet.isAfterLast();
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isFirst()
/*      */     throws SQLException
/*      */   {
/*  368 */     synchronized (this.connection)
/*      */     {
/*  370 */       ensureOpen();
/*  371 */       return this.resultSet.isFirst();
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isLast()
/*      */     throws SQLException
/*      */   {
/*  378 */     synchronized (this.connection)
/*      */     {
/*  380 */       ensureOpen();
/*  381 */       return this.resultSet.isLast();
/*      */     }
/*      */   }
/*      */   
/*      */   public void beforeFirst()
/*      */     throws SQLException
/*      */   {
/*  388 */     synchronized (this.connection)
/*      */     {
/*  390 */       ensureOpen();
/*  391 */       cancelRowChanges();
/*  392 */       this.resultSet.beforeFirst();
/*      */     }
/*      */   }
/*      */   
/*      */   public void afterLast()
/*      */     throws SQLException
/*      */   {
/*  399 */     synchronized (this.connection)
/*      */     {
/*  401 */       ensureOpen();
/*  402 */       cancelRowChanges();
/*  403 */       this.resultSet.afterLast();
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean first()
/*      */     throws SQLException
/*      */   {
/*  410 */     synchronized (this.connection)
/*      */     {
/*  412 */       ensureOpen();
/*  413 */       cancelRowChanges();
/*      */       
/*  415 */       return this.resultSet.first();
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean last()
/*      */     throws SQLException
/*      */   {
/*  422 */     synchronized (this.connection)
/*      */     {
/*  424 */       ensureOpen();
/*  425 */       cancelRowChanges();
/*      */       
/*  427 */       return this.resultSet.last();
/*      */     }
/*      */   }
/*      */   
/*      */   public int getRow()
/*      */     throws SQLException
/*      */   {
/*  434 */     synchronized (this.connection)
/*      */     {
/*  436 */       ensureOpen();
/*  437 */       return this.resultSet.getRow();
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean absolute(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  444 */     synchronized (this.connection)
/*      */     {
/*  446 */       cancelRowChanges();
/*      */       
/*  448 */       return this.resultSet.absolute(paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean relative(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  455 */     synchronized (this.connection)
/*      */     {
/*  457 */       ensureOpen();
/*  458 */       cancelRowChanges();
/*      */       
/*  460 */       return this.resultSet.relative(paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean previous()
/*      */     throws SQLException
/*      */   {
/*  467 */     synchronized (this.connection)
/*      */     {
/*  469 */       ensureOpen();
/*  470 */       cancelRowChanges();
/*      */       
/*  472 */       return this.resultSet.previous();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Datum getOracleObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  483 */     synchronized (this.connection)
/*      */     {
/*  485 */       ensureOpen();
/*  486 */       Datum localDatum = null;
/*      */       
/*  488 */       setIsNull(3);
/*      */       
/*  490 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  493 */         setIsNull(localDatum == null);
/*      */         
/*  495 */         localDatum = getRowBufferDatumAt(paramInt);
/*      */       }
/*      */       else
/*      */       {
/*  499 */         setIsNull(4);
/*      */         
/*  501 */         localDatum = this.resultSet.getOracleObject(paramInt + 1);
/*      */       }
/*      */       
/*  504 */       return localDatum;
/*      */     }
/*      */   }
/*      */   
/*      */   public String getString(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  511 */     synchronized (this.connection)
/*      */     {
/*  513 */       ensureOpen();
/*  514 */       String str = null;
/*      */       
/*  516 */       setIsNull(3);
/*      */       
/*  518 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  521 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/*  523 */         setIsNull(localDatum == null);
/*      */         
/*  525 */         if (localDatum != null) {
/*  526 */           str = localDatum.stringValue(this.connection);
/*      */         }
/*      */       }
/*      */       else {
/*  530 */         setIsNull(4);
/*      */         
/*  532 */         str = this.resultSet.getString(paramInt + 1);
/*      */       }
/*      */       
/*  535 */       return str;
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean getBoolean(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  542 */     synchronized (this.connection)
/*      */     {
/*  544 */       ensureOpen();
/*  545 */       boolean bool = false;
/*      */       
/*  547 */       setIsNull(3);
/*      */       
/*  549 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  552 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/*  554 */         setIsNull(localDatum == null);
/*      */         
/*  556 */         if (localDatum != null) {
/*  557 */           bool = localDatum.booleanValue();
/*      */         }
/*      */       }
/*      */       else {
/*  561 */         setIsNull(4);
/*      */         
/*  563 */         bool = this.resultSet.getBoolean(paramInt + 1);
/*      */       }
/*      */       
/*  566 */       return bool;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public OracleResultSet.AuthorizationIndicator getAuthorizationIndicator(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  574 */     return this.resultSet.getAuthorizationIndicator(paramInt + 1);
/*      */   }
/*      */   
/*      */   public byte getByte(int paramInt) throws SQLException
/*      */   {
/*  579 */     synchronized (this.connection)
/*      */     {
/*  581 */       ensureOpen();
/*  582 */       byte b = 0;
/*      */       
/*  584 */       setIsNull(3);
/*      */       
/*  586 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  589 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/*  591 */         setIsNull(localDatum == null);
/*      */         
/*  593 */         if (localDatum != null) {
/*  594 */           b = localDatum.byteValue();
/*      */         }
/*      */       }
/*      */       else {
/*  598 */         setIsNull(4);
/*      */         
/*  600 */         b = this.resultSet.getByte(paramInt + 1);
/*      */       }
/*      */       
/*  603 */       return b;
/*      */     }
/*      */   }
/*      */   
/*      */   public short getShort(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  610 */     synchronized (this.connection)
/*      */     {
/*  612 */       ensureOpen();
/*  613 */       short s = 0;
/*      */       
/*  615 */       setIsNull(3);
/*      */       
/*  617 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  620 */         long l = getLong(paramInt);
/*      */         
/*  622 */         if ((l > 65537L) || (l < -65538L))
/*      */         {
/*  624 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 26, "getShort");
/*  625 */           localSQLException.fillInStackTrace();
/*  626 */           throw localSQLException;
/*      */         }
/*      */         
/*  629 */         s = (short)(int)l;
/*      */       }
/*      */       else
/*      */       {
/*  633 */         setIsNull(4);
/*      */         
/*  635 */         s = this.resultSet.getShort(paramInt + 1);
/*      */       }
/*      */       
/*  638 */       return s;
/*      */     }
/*      */   }
/*      */   
/*      */   public int getInt(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  645 */     synchronized (this.connection)
/*      */     {
/*  647 */       ensureOpen();
/*  648 */       int i = 0;
/*      */       
/*  650 */       setIsNull(3);
/*      */       
/*  652 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  655 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/*  657 */         setIsNull(localDatum == null);
/*      */         
/*  659 */         if (localDatum != null) {
/*  660 */           i = localDatum.intValue();
/*      */         }
/*      */       }
/*      */       else {
/*  664 */         setIsNull(4);
/*      */         
/*  666 */         i = this.resultSet.getInt(paramInt + 1);
/*      */       }
/*      */       
/*  669 */       return i;
/*      */     }
/*      */   }
/*      */   
/*      */   public long getLong(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  676 */     synchronized (this.connection)
/*      */     {
/*  678 */       ensureOpen();
/*  679 */       long l = 0L;
/*      */       
/*  681 */       setIsNull(3);
/*      */       
/*  683 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  686 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/*  688 */         setIsNull(localDatum == null);
/*      */         
/*  690 */         if (localDatum != null) {
/*  691 */           l = localDatum.longValue();
/*      */         }
/*      */       }
/*      */       else {
/*  695 */         setIsNull(4);
/*      */         
/*  697 */         l = this.resultSet.getLong(paramInt + 1);
/*      */       }
/*      */       
/*  700 */       return l;
/*      */     }
/*      */   }
/*      */   
/*      */   public float getFloat(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  707 */     synchronized (this.connection)
/*      */     {
/*  709 */       ensureOpen();
/*  710 */       float f = 0.0F;
/*      */       
/*  712 */       setIsNull(3);
/*      */       
/*  714 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  717 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/*  719 */         setIsNull(localDatum == null);
/*      */         
/*  721 */         if (localDatum != null) {
/*  722 */           f = localDatum.floatValue();
/*      */         }
/*      */       }
/*      */       else {
/*  726 */         setIsNull(4);
/*      */         
/*  728 */         f = this.resultSet.getFloat(paramInt + 1);
/*      */       }
/*      */       
/*  731 */       return f;
/*      */     }
/*      */   }
/*      */   
/*      */   public double getDouble(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  738 */     synchronized (this.connection)
/*      */     {
/*  740 */       ensureOpen();
/*  741 */       double d = 0.0D;
/*      */       
/*  743 */       setIsNull(3);
/*      */       
/*  745 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  748 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/*  750 */         setIsNull(localDatum == null);
/*      */         
/*  752 */         if (localDatum != null) {
/*  753 */           d = localDatum.doubleValue();
/*      */         }
/*      */       }
/*      */       else {
/*  757 */         setIsNull(4);
/*      */         
/*  759 */         d = this.resultSet.getDouble(paramInt + 1);
/*      */       }
/*      */       
/*  762 */       return d;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  770 */     synchronized (this.connection)
/*      */     {
/*  772 */       ensureOpen();
/*  773 */       BigDecimal localBigDecimal = null;
/*      */       
/*  775 */       setIsNull(3);
/*      */       
/*  777 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt1))))
/*      */       {
/*      */ 
/*  780 */         Datum localDatum = getRowBufferDatumAt(paramInt1);
/*      */         
/*  782 */         setIsNull(localDatum == null);
/*      */         
/*  784 */         if (localDatum != null) {
/*  785 */           localBigDecimal = localDatum.bigDecimalValue();
/*      */         }
/*      */       }
/*      */       else {
/*  789 */         setIsNull(4);
/*      */         
/*  791 */         localBigDecimal = this.resultSet.getBigDecimal(paramInt1 + 1);
/*      */       }
/*      */       
/*  794 */       return localBigDecimal;
/*      */     }
/*      */   }
/*      */   
/*      */   public byte[] getBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  801 */     synchronized (this.connection)
/*      */     {
/*  803 */       ensureOpen();
/*  804 */       byte[] arrayOfByte = null;
/*      */       
/*  806 */       setIsNull(3);
/*      */       
/*  808 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  811 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/*  813 */         setIsNull(localDatum == null);
/*      */         
/*  815 */         if (localDatum != null) {
/*  816 */           arrayOfByte = localDatum.getBytes();
/*      */         }
/*      */       }
/*      */       else {
/*  820 */         setIsNull(4);
/*      */         
/*  822 */         arrayOfByte = this.resultSet.getBytes(paramInt + 1);
/*      */       }
/*      */       
/*  825 */       return arrayOfByte;
/*      */     }
/*      */   }
/*      */   
/*      */   public Date getDate(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  832 */     synchronized (this.connection)
/*      */     {
/*  834 */       ensureOpen();
/*  835 */       Date localDate = null;
/*      */       
/*  837 */       setIsNull(3);
/*      */       
/*  839 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  842 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/*  844 */         setIsNull(localDatum == null);
/*      */         
/*  846 */         if (localDatum != null) {
/*  847 */           localDate = localDatum.dateValue();
/*      */         }
/*      */       }
/*      */       else {
/*  851 */         setIsNull(4);
/*      */         
/*  853 */         localDate = this.resultSet.getDate(paramInt + 1);
/*      */       }
/*      */       
/*  856 */       return localDate;
/*      */     }
/*      */   }
/*      */   
/*      */   public Time getTime(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  863 */     synchronized (this.connection)
/*      */     {
/*  865 */       ensureOpen();
/*  866 */       Time localTime = null;
/*      */       
/*  868 */       setIsNull(3);
/*      */       
/*  870 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  873 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/*  875 */         setIsNull(localDatum == null);
/*      */         
/*  877 */         if (localDatum != null) {
/*  878 */           localTime = localDatum.timeValue();
/*      */         }
/*      */       }
/*      */       else {
/*  882 */         setIsNull(4);
/*      */         
/*  884 */         localTime = this.resultSet.getTime(paramInt + 1);
/*      */       }
/*      */       
/*  887 */       return localTime;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  895 */     synchronized (this.connection)
/*      */     {
/*  897 */       ensureOpen();
/*  898 */       Timestamp localTimestamp = null;
/*      */       
/*  900 */       setIsNull(3);
/*      */       
/*  902 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  905 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/*  907 */         setIsNull(localDatum == null);
/*      */         
/*  909 */         if (localDatum != null) {
/*  910 */           localTimestamp = localDatum.timestampValue();
/*      */         }
/*      */       }
/*      */       else {
/*  914 */         setIsNull(4);
/*      */         
/*  916 */         localTimestamp = this.resultSet.getTimestamp(paramInt + 1);
/*      */       }
/*      */       
/*  919 */       return localTimestamp;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public InputStream getAsciiStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  927 */     synchronized (this.connection)
/*      */     {
/*  929 */       ensureOpen();
/*  930 */       InputStream localInputStream = null;
/*      */       
/*  932 */       setIsNull(3);
/*      */       
/*  934 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  937 */         Object localObject1 = getRowBufferAt(paramInt);
/*      */         
/*  939 */         setIsNull(localObject1 == null);
/*      */         
/*  941 */         if (localObject1 != null)
/*      */         {
/*  943 */           if ((localObject1 instanceof InputStream))
/*      */           {
/*  945 */             localInputStream = (InputStream)localObject1;
/*      */           }
/*      */           else
/*      */           {
/*  949 */             Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */             
/*  951 */             localInputStream = localDatum.asciiStreamValue();
/*      */           }
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  957 */         setIsNull(4);
/*      */         
/*  959 */         localInputStream = this.resultSet.getAsciiStream(paramInt + 1);
/*      */       }
/*      */       
/*  962 */       return localInputStream;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public InputStream getUnicodeStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  970 */     synchronized (this.connection)
/*      */     {
/*  972 */       ensureOpen();
/*  973 */       InputStream localInputStream = null;
/*      */       
/*  975 */       setIsNull(3);
/*      */       
/*  977 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/*  980 */         Object localObject1 = getRowBufferAt(paramInt);
/*      */         
/*  982 */         setIsNull(localObject1 == null);
/*      */         
/*  984 */         if (localObject1 != null)
/*      */         {
/*  986 */           if ((localObject1 instanceof InputStream))
/*      */           {
/*  988 */             localInputStream = (InputStream)localObject1;
/*      */           }
/*      */           else
/*      */           {
/*  992 */             Datum localDatum = getRowBufferDatumAt(paramInt);
/*  993 */             DBConversion localDBConversion = this.connection.conversion;
/*  994 */             byte[] arrayOfByte = localDatum.shareBytes();
/*      */             
/*  996 */             if ((localDatum instanceof RAW))
/*      */             {
/*  998 */               localInputStream = localDBConversion.ConvertStream(new ByteArrayInputStream(arrayOfByte), 3);
/*      */ 
/*      */             }
/* 1001 */             else if ((localDatum instanceof CHAR))
/*      */             {
/* 1003 */               localInputStream = localDBConversion.ConvertStream(new ByteArrayInputStream(arrayOfByte), 1);
/*      */ 
/*      */             }
/*      */             else
/*      */             {
/* 1008 */               SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getUnicodeStream");
/* 1009 */               localSQLException.fillInStackTrace();
/* 1010 */               throw localSQLException;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1017 */         setIsNull(4);
/*      */         
/* 1019 */         localInputStream = this.resultSet.getUnicodeStream(paramInt + 1);
/*      */       }
/*      */       
/* 1022 */       return localInputStream;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public InputStream getBinaryStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1030 */     synchronized (this.connection)
/*      */     {
/* 1032 */       ensureOpen();
/* 1033 */       InputStream localInputStream = null;
/*      */       
/* 1035 */       setIsNull(3);
/*      */       
/* 1037 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1040 */         Object localObject1 = getRowBufferAt(paramInt);
/*      */         
/* 1042 */         setIsNull(localObject1 == null);
/*      */         
/* 1044 */         if (localObject1 != null)
/*      */         {
/* 1046 */           if ((localObject1 instanceof InputStream))
/*      */           {
/* 1048 */             localInputStream = (InputStream)localObject1;
/*      */           }
/*      */           else
/*      */           {
/* 1052 */             Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */             
/* 1054 */             localInputStream = localDatum.binaryStreamValue();
/*      */           }
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1060 */         setIsNull(4);
/*      */         
/* 1062 */         localInputStream = this.resultSet.getBinaryStream(paramInt + 1);
/*      */       }
/*      */       
/* 1065 */       return localInputStream;
/*      */     }
/*      */   }
/*      */   
/*      */   public Object getObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1072 */     synchronized (this.connection)
/*      */     {
/* 1074 */       ensureOpen();
/* 1075 */       Object localObject1 = null;
/*      */       
/* 1077 */       setIsNull(3);
/*      */       
/* 1079 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1082 */         Datum localDatum = getOracleObject(paramInt);
/*      */         
/* 1084 */         setIsNull(localDatum == null);
/*      */         
/* 1086 */         if (localDatum != null) {
/* 1087 */           localObject1 = localDatum.toJdbc();
/*      */         }
/*      */       }
/*      */       else {
/* 1091 */         setIsNull(4);
/*      */         
/* 1093 */         localObject1 = this.resultSet.getObject(paramInt + 1);
/*      */       }
/*      */       
/* 1096 */       return localObject1;
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public Object getObject(int paramInt, oracle.jdbc.OracleDataFactory paramOracleDataFactory)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 13	oracle/jdbc/driver/UpdatableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_3
/*      */     //   6: monitorenter
/*      */     //   7: aload_2
/*      */     //   8: aload_0
/*      */     //   9: iload_1
/*      */     //   10: invokevirtual 118	oracle/jdbc/driver/UpdatableResultSet:getObject	(I)Ljava/lang/Object;
/*      */     //   13: iconst_0
/*      */     //   14: invokeinterface 119 3 0
/*      */     //   19: aload_3
/*      */     //   20: monitorexit
/*      */     //   21: areturn
/*      */     //   22: astore 4
/*      */     //   24: aload_3
/*      */     //   25: monitorexit
/*      */     //   26: aload 4
/*      */     //   28: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1104	-> byte code offset #0
/*      */     //   Java source line #1107	-> byte code offset #7
/*      */     //   Java source line #1109	-> byte code offset #22
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	29	0	this	UpdatableResultSet
/*      */     //   0	29	1	paramInt	int
/*      */     //   0	29	2	paramOracleDataFactory	oracle.jdbc.OracleDataFactory
/*      */     //   5	20	3	Ljava/lang/Object;	Object
/*      */     //   22	5	4	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	21	22	finally
/*      */     //   22	26	22	finally
/*      */   }
/*      */   
/*      */   public Reader getCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1117 */     synchronized (this.connection)
/*      */     {
/* 1119 */       ensureOpen();
/* 1120 */       Reader localReader = null;
/*      */       
/* 1122 */       setIsNull(3);
/*      */       
/* 1124 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1127 */         Object localObject1 = getRowBufferAt(paramInt);
/*      */         
/* 1129 */         setIsNull(localObject1 == null);
/*      */         
/* 1131 */         if (localObject1 != null)
/*      */         {
/* 1133 */           if ((localObject1 instanceof Reader))
/*      */           {
/* 1135 */             localReader = (Reader)localObject1;
/*      */           }
/*      */           else
/*      */           {
/* 1139 */             Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */             
/* 1141 */             localReader = localDatum.characterStreamValue();
/*      */           }
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1147 */         setIsNull(4);
/*      */         
/* 1149 */         localReader = this.resultSet.getCharacterStream(paramInt + 1);
/*      */       }
/*      */       
/* 1152 */       return localReader;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1161 */     synchronized (this.connection)
/*      */     {
/* 1163 */       ensureOpen();
/* 1164 */       BigDecimal localBigDecimal = null;
/*      */       
/* 1166 */       setIsNull(3);
/*      */       
/* 1168 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1171 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 1173 */         setIsNull(localDatum == null);
/*      */         
/* 1175 */         if (localDatum != null) {
/* 1176 */           localBigDecimal = localDatum.bigDecimalValue();
/*      */         }
/*      */       }
/*      */       else {
/* 1180 */         setIsNull(4);
/*      */         
/* 1182 */         localBigDecimal = this.resultSet.getBigDecimal(paramInt + 1);
/*      */       }
/*      */       
/* 1185 */       return localBigDecimal;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getObject(int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1194 */     synchronized (this.connection)
/*      */     {
/* 1196 */       ensureOpen();
/* 1197 */       Object localObject1 = null;
/*      */       
/* 1199 */       setIsNull(3);
/*      */       
/* 1201 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1204 */         Datum localDatum = getOracleObject(paramInt);
/*      */         
/* 1206 */         setIsNull(localDatum == null);
/*      */         
/* 1208 */         if (localDatum != null)
/*      */         {
/* 1210 */           if ((localDatum instanceof STRUCT)) {
/* 1211 */             localObject1 = ((STRUCT)localDatum).toJdbc(paramMap);
/*      */           } else {
/* 1213 */             localObject1 = localDatum.toJdbc();
/*      */           }
/*      */         }
/*      */       }
/*      */       else {
/* 1218 */         setIsNull(4);
/*      */         
/* 1220 */         localObject1 = this.resultSet.getObject(paramInt + 1, paramMap);
/*      */       }
/*      */       
/* 1223 */       return localObject1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Ref getRef(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1231 */     synchronized (this.connection)
/*      */     {
/* 1233 */       ensureOpen();
/* 1234 */       return getREF(paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Blob getBlob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1242 */     synchronized (this.connection)
/*      */     {
/* 1244 */       ensureOpen();
/* 1245 */       return getBLOB(paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Clob getClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1253 */     synchronized (this.connection)
/*      */     {
/* 1255 */       ensureOpen();
/* 1256 */       return getCLOB(paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Array getArray(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1265 */     synchronized (this.connection)
/*      */     {
/* 1267 */       ensureOpen();
/* 1268 */       return getARRAY(paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Date getDate(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1277 */     synchronized (this.connection)
/*      */     {
/* 1279 */       ensureOpen();
/* 1280 */       Date localDate = null;
/*      */       
/* 1282 */       setIsNull(3);
/*      */       
/* 1284 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1287 */         Datum localDatum = getOracleObject(paramInt);
/*      */         
/* 1289 */         setIsNull(localDatum == null);
/*      */         
/* 1291 */         if (localDatum != null)
/*      */         {
/* 1293 */           if ((localDatum instanceof DATE)) {
/* 1294 */             localDate = ((DATE)localDatum).dateValue(paramCalendar); } else { Object localObject1;
/* 1295 */             if ((localDatum instanceof TIMESTAMP))
/*      */             {
/* 1297 */               localObject1 = ((TIMESTAMP)localDatum).timestampValue(paramCalendar);
/* 1298 */               long l = ((Timestamp)localObject1).getTime();
/* 1299 */               localDate = new Date(l);
/*      */ 
/*      */             }
/*      */             else
/*      */             {
/*      */ 
/* 1305 */               localObject1 = new DATE(localDatum.stringValue(this.connection));
/*      */               
/* 1307 */               if (localObject1 != null) {
/* 1308 */                 localDate = ((DATE)localObject1).dateValue(paramCalendar);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       } else {
/* 1314 */         setIsNull(4);
/*      */         
/* 1316 */         localDate = this.resultSet.getDate(paramInt + 1, paramCalendar);
/*      */       }
/*      */       
/* 1319 */       return localDate;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Time getTime(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1328 */     synchronized (this.connection)
/*      */     {
/* 1330 */       ensureOpen();
/* 1331 */       Time localTime = null;
/*      */       
/* 1333 */       setIsNull(3);
/*      */       
/* 1335 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1338 */         Datum localDatum = getOracleObject(paramInt);
/*      */         
/* 1340 */         setIsNull(localDatum == null);
/*      */         
/* 1342 */         if (localDatum != null)
/*      */         {
/* 1344 */           if ((localDatum instanceof DATE)) {
/* 1345 */             localTime = ((DATE)localDatum).timeValue(paramCalendar); } else { Object localObject1;
/* 1346 */             if ((localDatum instanceof TIMESTAMP))
/*      */             {
/* 1348 */               localObject1 = ((TIMESTAMP)localDatum).timestampValue(paramCalendar);
/* 1349 */               long l = ((Timestamp)localObject1).getTime();
/* 1350 */               localTime = new Time(l);
/*      */ 
/*      */             }
/*      */             else
/*      */             {
/* 1355 */               localObject1 = new DATE(localDatum.stringValue(this.connection));
/*      */               
/* 1357 */               if (localObject1 != null) {
/* 1358 */                 localTime = ((DATE)localObject1).timeValue(paramCalendar);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       } else {
/* 1364 */         setIsNull(4);
/*      */         
/* 1366 */         localTime = this.resultSet.getTime(paramInt + 1, paramCalendar);
/*      */       }
/*      */       
/* 1369 */       return localTime;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1378 */     synchronized (this.connection)
/*      */     {
/* 1380 */       ensureOpen();
/* 1381 */       Timestamp localTimestamp = null;
/*      */       
/* 1383 */       setIsNull(3);
/*      */       
/* 1385 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1388 */         Datum localDatum = getOracleObject(paramInt);
/*      */         
/* 1390 */         setIsNull(localDatum == null);
/*      */         
/* 1392 */         if (localDatum != null)
/*      */         {
/* 1394 */           if ((localDatum instanceof DATE)) {
/* 1395 */             localTimestamp = ((DATE)localDatum).timestampValue(paramCalendar);
/* 1396 */           } else if ((localDatum instanceof TIMESTAMP)) {
/* 1397 */             localTimestamp = ((TIMESTAMP)localDatum).timestampValue(paramCalendar);
/*      */           }
/*      */           else {
/* 1400 */             DATE localDATE = new DATE(localDatum.stringValue(this.connection));
/*      */             
/* 1402 */             if (localDATE != null) {
/* 1403 */               localTimestamp = localDATE.timestampValue(paramCalendar);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       else {
/* 1409 */         setIsNull(4);
/*      */         
/* 1411 */         localTimestamp = this.resultSet.getTimestamp(paramInt + 1, paramCalendar);
/*      */       }
/*      */       
/* 1414 */       return localTimestamp;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public URL getURL(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1422 */     synchronized (this.connection)
/*      */     {
/* 1424 */       ensureOpen();
/*      */       
/* 1426 */       URL localURL = null;
/*      */       
/* 1428 */       int i = getInternalMetadata().getColumnType(paramInt + 1);
/* 1429 */       int j = SQLUtil.getInternalType(i);
/*      */       
/*      */ 
/* 1432 */       if ((j == 96) || (j == 1) || (j == 8))
/*      */       {
/*      */ 
/*      */         try
/*      */         {
/* 1437 */           String str = getString(paramInt);
/* 1438 */           if (str == null) localURL = null; else {
/* 1439 */             localURL = new URL(str);
/*      */           }
/*      */         }
/*      */         catch (MalformedURLException localMalformedURLException)
/*      */         {
/* 1444 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 136);
/* 1445 */           localSQLException2.fillInStackTrace();
/* 1446 */           throw localSQLException2;
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1453 */         SQLException localSQLException1 = DatabaseError.createUnsupportedFeatureSqlException();
/* 1454 */         localSQLException1.fillInStackTrace();
/* 1455 */         throw localSQLException1;
/*      */       }
/*      */       
/*      */ 
/* 1459 */       return localURL;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public ResultSet getCursor(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1467 */     synchronized (this.connection)
/*      */     {
/* 1469 */       ensureOpen();
/* 1470 */       ResultSet localResultSet = null;
/*      */       
/* 1472 */       setIsNull(3);
/*      */       
/* 1474 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1477 */         Datum localDatum = getOracleObject(paramInt);
/*      */         
/* 1479 */         setIsNull(localDatum == null);
/*      */         
/* 1481 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getCursor");
/* 1482 */         localSQLException.fillInStackTrace();
/* 1483 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1488 */       setIsNull(4);
/*      */       
/* 1490 */       localResultSet = this.resultSet.getCursor(paramInt + 1);
/*      */       
/*      */ 
/* 1493 */       return localResultSet;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public ROWID getROWID(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1501 */     synchronized (this.connection)
/*      */     {
/* 1503 */       ensureOpen();
/* 1504 */       ROWID localROWID = null;
/*      */       
/* 1506 */       setIsNull(3);
/*      */       
/* 1508 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1511 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 1513 */         setIsNull(localDatum == null);
/*      */         
/* 1515 */         if ((localDatum != null) && (!(localDatum instanceof ROWID)))
/*      */         {
/* 1517 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getROWID");
/* 1518 */           localSQLException.fillInStackTrace();
/* 1519 */           throw localSQLException;
/*      */         }
/*      */         
/* 1522 */         localROWID = (ROWID)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 1526 */         setIsNull(4);
/*      */         
/* 1528 */         localROWID = this.resultSet.getROWID(paramInt + 1);
/*      */       }
/*      */       
/* 1531 */       return localROWID;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public NUMBER getNUMBER(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1539 */     synchronized (this.connection)
/*      */     {
/* 1541 */       ensureOpen();
/* 1542 */       NUMBER localNUMBER = null;
/*      */       
/* 1544 */       setIsNull(3);
/*      */       
/* 1546 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1549 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 1551 */         setIsNull(localDatum == null);
/*      */         
/* 1553 */         if ((localDatum != null) && (!(localDatum instanceof NUMBER)))
/*      */         {
/* 1555 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getNUMBER");
/* 1556 */           localSQLException.fillInStackTrace();
/* 1557 */           throw localSQLException;
/*      */         }
/*      */         
/* 1560 */         localNUMBER = (NUMBER)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 1564 */         setIsNull(4);
/*      */         
/* 1566 */         localNUMBER = this.resultSet.getNUMBER(paramInt + 1);
/*      */       }
/*      */       
/* 1569 */       return localNUMBER;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public DATE getDATE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1577 */     synchronized (this.connection)
/*      */     {
/* 1579 */       ensureOpen();
/* 1580 */       DATE localDATE = null;
/*      */       
/* 1582 */       setIsNull(3);
/*      */       
/* 1584 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1587 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 1589 */         if (localDatum != null)
/*      */         {
/* 1591 */           if ((localDatum instanceof DATE)) { localDATE = (DATE)localDatum; } else { Object localObject1;
/* 1592 */             if ((localDatum instanceof TIMESTAMP))
/*      */             {
/* 1594 */               localObject1 = ((TIMESTAMP)localDatum).timestampValue();
/* 1595 */               localDATE = new DATE((Timestamp)localObject1);
/*      */ 
/*      */             }
/*      */             else
/*      */             {
/* 1600 */               localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getDATE");
/* 1601 */               ((SQLException)localObject1).fillInStackTrace();
/* 1602 */               throw ((Throwable)localObject1);
/*      */             }
/*      */             
/*      */           }
/*      */         }
/*      */         else {
/* 1608 */           setIsNull(localDatum == null);
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/* 1614 */         setIsNull(4);
/*      */         
/* 1616 */         localDATE = this.resultSet.getDATE(paramInt + 1);
/*      */       }
/*      */       
/* 1619 */       return localDATE;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public TIMESTAMP getTIMESTAMP(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1627 */     synchronized (this.connection)
/*      */     {
/* 1629 */       ensureOpen();
/* 1630 */       TIMESTAMP localTIMESTAMP = null;
/*      */       
/* 1632 */       setIsNull(3);
/*      */       
/* 1634 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1637 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 1639 */         setIsNull(localDatum == null);
/*      */         
/* 1641 */         if ((localDatum != null) && (!(localDatum instanceof TIMESTAMP)))
/*      */         {
/* 1643 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getTIMESTAMP");
/* 1644 */           localSQLException.fillInStackTrace();
/* 1645 */           throw localSQLException;
/*      */         }
/*      */         
/* 1648 */         localTIMESTAMP = (TIMESTAMP)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 1652 */         setIsNull(4);
/*      */         
/* 1654 */         localTIMESTAMP = this.resultSet.getTIMESTAMP(paramInt + 1);
/*      */       }
/*      */       
/* 1657 */       return localTIMESTAMP;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public TIMESTAMPTZ getTIMESTAMPTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1665 */     synchronized (this.connection)
/*      */     {
/* 1667 */       ensureOpen();
/* 1668 */       TIMESTAMPTZ localTIMESTAMPTZ = null;
/*      */       
/* 1670 */       setIsNull(3);
/*      */       
/* 1672 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1675 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 1677 */         setIsNull(localDatum == null);
/*      */         
/* 1679 */         if ((localDatum != null) && (!(localDatum instanceof TIMESTAMPTZ)))
/*      */         {
/* 1681 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getTIMESTAMPTZ");
/* 1682 */           localSQLException.fillInStackTrace();
/* 1683 */           throw localSQLException;
/*      */         }
/*      */         
/* 1686 */         localTIMESTAMPTZ = (TIMESTAMPTZ)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 1690 */         setIsNull(4);
/*      */         
/* 1692 */         localTIMESTAMPTZ = this.resultSet.getTIMESTAMPTZ(paramInt + 1);
/*      */       }
/*      */       
/* 1695 */       return localTIMESTAMPTZ;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public TIMESTAMPLTZ getTIMESTAMPLTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1703 */     synchronized (this.connection)
/*      */     {
/* 1705 */       ensureOpen();
/* 1706 */       TIMESTAMPLTZ localTIMESTAMPLTZ = null;
/*      */       
/* 1708 */       setIsNull(3);
/*      */       
/* 1710 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1713 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 1715 */         setIsNull(localDatum == null);
/*      */         
/* 1717 */         if ((localDatum != null) && (!(localDatum instanceof TIMESTAMPLTZ)))
/*      */         {
/* 1719 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getTIMESTAMPLTZ");
/* 1720 */           localSQLException.fillInStackTrace();
/* 1721 */           throw localSQLException;
/*      */         }
/*      */         
/* 1724 */         localTIMESTAMPLTZ = (TIMESTAMPLTZ)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 1728 */         setIsNull(4);
/*      */         
/* 1730 */         localTIMESTAMPLTZ = this.resultSet.getTIMESTAMPLTZ(paramInt + 1);
/*      */       }
/*      */       
/* 1733 */       return localTIMESTAMPLTZ;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public INTERVALDS getINTERVALDS(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1741 */     synchronized (this.connection)
/*      */     {
/* 1743 */       ensureOpen();
/* 1744 */       INTERVALDS localINTERVALDS = null;
/*      */       
/* 1746 */       setIsNull(3);
/*      */       
/* 1748 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1751 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 1753 */         setIsNull(localDatum == null);
/*      */         
/* 1755 */         if ((localDatum != null) && (!(localDatum instanceof INTERVALDS)))
/*      */         {
/* 1757 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getINTERVALDS");
/* 1758 */           localSQLException.fillInStackTrace();
/* 1759 */           throw localSQLException;
/*      */         }
/*      */         
/* 1762 */         localINTERVALDS = (INTERVALDS)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 1766 */         setIsNull(4);
/*      */         
/* 1768 */         localINTERVALDS = this.resultSet.getINTERVALDS(paramInt + 1);
/*      */       }
/*      */       
/* 1771 */       return localINTERVALDS;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public INTERVALYM getINTERVALYM(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1779 */     synchronized (this.connection)
/*      */     {
/* 1781 */       ensureOpen();
/* 1782 */       INTERVALYM localINTERVALYM = null;
/*      */       
/* 1784 */       setIsNull(3);
/*      */       
/* 1786 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1789 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 1791 */         setIsNull(localDatum == null);
/*      */         
/* 1793 */         if ((localDatum != null) && (!(localDatum instanceof INTERVALYM)))
/*      */         {
/* 1795 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getINTERVALYM");
/* 1796 */           localSQLException.fillInStackTrace();
/* 1797 */           throw localSQLException;
/*      */         }
/*      */         
/* 1800 */         localINTERVALYM = (INTERVALYM)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 1804 */         setIsNull(4);
/*      */         
/* 1806 */         localINTERVALYM = this.resultSet.getINTERVALYM(paramInt + 1);
/*      */       }
/*      */       
/* 1809 */       return localINTERVALYM;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public ARRAY getARRAY(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1817 */     synchronized (this.connection)
/*      */     {
/* 1819 */       ensureOpen();
/* 1820 */       ARRAY localARRAY = null;
/*      */       
/* 1822 */       setIsNull(3);
/*      */       
/* 1824 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1827 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 1829 */         setIsNull(localDatum == null);
/*      */         
/* 1831 */         if ((localDatum != null) && (!(localDatum instanceof ARRAY)))
/*      */         {
/* 1833 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getARRAY");
/* 1834 */           localSQLException.fillInStackTrace();
/* 1835 */           throw localSQLException;
/*      */         }
/*      */         
/* 1838 */         localARRAY = (ARRAY)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 1842 */         setIsNull(4);
/*      */         
/* 1844 */         localARRAY = this.resultSet.getARRAY(paramInt + 1);
/*      */       }
/*      */       
/* 1847 */       return localARRAY;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public STRUCT getSTRUCT(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1855 */     synchronized (this.connection)
/*      */     {
/* 1857 */       ensureOpen();
/* 1858 */       STRUCT localSTRUCT = null;
/*      */       
/* 1860 */       setIsNull(3);
/*      */       
/* 1862 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1865 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 1867 */         setIsNull(localDatum == null);
/*      */         
/* 1869 */         if ((localDatum != null) && (!(localDatum instanceof STRUCT)))
/*      */         {
/* 1871 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getSTRUCT");
/* 1872 */           localSQLException.fillInStackTrace();
/* 1873 */           throw localSQLException;
/*      */         }
/*      */         
/* 1876 */         localSTRUCT = (STRUCT)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 1880 */         setIsNull(4);
/*      */         
/* 1882 */         localSTRUCT = this.resultSet.getSTRUCT(paramInt + 1);
/*      */       }
/*      */       
/* 1885 */       return localSTRUCT;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public OPAQUE getOPAQUE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1893 */     synchronized (this.connection)
/*      */     {
/* 1895 */       ensureOpen();
/* 1896 */       OPAQUE localOPAQUE = null;
/*      */       
/* 1898 */       setIsNull(3);
/*      */       
/* 1900 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1903 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 1905 */         setIsNull(localDatum == null);
/*      */         
/* 1907 */         if ((localDatum != null) && (!(localDatum instanceof OPAQUE)))
/*      */         {
/* 1909 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getOPAQUE");
/* 1910 */           localSQLException.fillInStackTrace();
/* 1911 */           throw localSQLException;
/*      */         }
/*      */         
/* 1914 */         localOPAQUE = (OPAQUE)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 1918 */         setIsNull(4);
/*      */         
/* 1920 */         localOPAQUE = this.resultSet.getOPAQUE(paramInt + 1);
/*      */       }
/*      */       
/* 1923 */       return localOPAQUE;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public REF getREF(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1931 */     synchronized (this.connection)
/*      */     {
/* 1933 */       ensureOpen();
/* 1934 */       REF localREF = null;
/*      */       
/* 1936 */       setIsNull(3);
/*      */       
/* 1938 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1941 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 1943 */         setIsNull(localDatum == null);
/*      */         
/* 1945 */         if ((localDatum != null) && (!(localDatum instanceof REF)))
/*      */         {
/* 1947 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getREF");
/* 1948 */           localSQLException.fillInStackTrace();
/* 1949 */           throw localSQLException;
/*      */         }
/*      */         
/* 1952 */         localREF = (REF)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 1956 */         setIsNull(4);
/*      */         
/* 1958 */         localREF = this.resultSet.getREF(paramInt + 1);
/*      */       }
/*      */       
/* 1961 */       return localREF;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public CHAR getCHAR(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1969 */     synchronized (this.connection)
/*      */     {
/* 1971 */       ensureOpen();
/* 1972 */       CHAR localCHAR = null;
/*      */       
/* 1974 */       setIsNull(3);
/*      */       
/* 1976 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 1979 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 1981 */         setIsNull(localDatum == null);
/*      */         
/* 1983 */         if ((localDatum != null) && (!(localDatum instanceof CHAR)))
/*      */         {
/* 1985 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getCHAR");
/* 1986 */           localSQLException.fillInStackTrace();
/* 1987 */           throw localSQLException;
/*      */         }
/*      */         
/* 1990 */         localCHAR = (CHAR)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 1994 */         setIsNull(4);
/*      */         
/* 1996 */         localCHAR = this.resultSet.getCHAR(paramInt + 1);
/*      */       }
/*      */       
/* 1999 */       return localCHAR;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public RAW getRAW(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2007 */     synchronized (this.connection)
/*      */     {
/* 2009 */       ensureOpen();
/* 2010 */       RAW localRAW = null;
/*      */       
/* 2012 */       setIsNull(3);
/*      */       
/* 2014 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 2017 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 2019 */         setIsNull(localDatum == null);
/*      */         
/* 2021 */         if ((localDatum != null) && (!(localDatum instanceof RAW)))
/*      */         {
/* 2023 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getRAW");
/* 2024 */           localSQLException.fillInStackTrace();
/* 2025 */           throw localSQLException;
/*      */         }
/*      */         
/* 2028 */         localRAW = (RAW)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 2032 */         setIsNull(4);
/*      */         
/* 2034 */         localRAW = this.resultSet.getRAW(paramInt + 1);
/*      */       }
/*      */       
/* 2037 */       return localRAW;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public BLOB getBLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2045 */     synchronized (this.connection)
/*      */     {
/* 2047 */       ensureOpen();
/* 2048 */       BLOB localBLOB = null;
/*      */       
/* 2050 */       setIsNull(3);
/*      */       
/* 2052 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 2055 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 2057 */         setIsNull(localDatum == null);
/*      */         
/* 2059 */         if ((localDatum != null) && (!(localDatum instanceof BLOB)))
/*      */         {
/* 2061 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getBLOB");
/* 2062 */           localSQLException.fillInStackTrace();
/* 2063 */           throw localSQLException;
/*      */         }
/*      */         
/* 2066 */         localBLOB = (BLOB)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 2070 */         setIsNull(4);
/*      */         
/* 2072 */         localBLOB = this.resultSet.getBLOB(paramInt + 1);
/*      */       }
/*      */       
/* 2075 */       return localBLOB;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NCLOB getNCLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2086 */     synchronized (this.connection)
/*      */     {
/* 2088 */       ensureOpen();
/* 2089 */       NCLOB localNCLOB = null;
/*      */       
/* 2091 */       setIsNull(3);
/*      */       
/* 2093 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 2096 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 2098 */         setIsNull(localDatum == null);
/*      */         
/* 2100 */         if ((localDatum != null) && (!(localDatum instanceof NCLOB)))
/*      */         {
/* 2102 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getCLOB");
/* 2103 */           localSQLException.fillInStackTrace();
/* 2104 */           throw localSQLException;
/*      */         }
/*      */         
/* 2107 */         localNCLOB = (NCLOB)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 2111 */         setIsNull(4);
/*      */         
/* 2113 */         localNCLOB = (NCLOB)this.resultSet.getNClob(paramInt + 1);
/*      */       }
/*      */       
/* 2116 */       return localNCLOB;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public CLOB getCLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2124 */     synchronized (this.connection)
/*      */     {
/* 2126 */       ensureOpen();
/* 2127 */       CLOB localCLOB = null;
/*      */       
/* 2129 */       setIsNull(3);
/*      */       
/* 2131 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 2134 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 2136 */         setIsNull(localDatum == null);
/*      */         
/* 2138 */         if ((localDatum != null) && (!(localDatum instanceof CLOB)))
/*      */         {
/* 2140 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getCLOB");
/* 2141 */           localSQLException.fillInStackTrace();
/* 2142 */           throw localSQLException;
/*      */         }
/*      */         
/* 2145 */         localCLOB = (CLOB)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 2149 */         setIsNull(4);
/*      */         
/* 2151 */         localCLOB = this.resultSet.getCLOB(paramInt + 1);
/*      */       }
/*      */       
/* 2154 */       return localCLOB;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public BFILE getBFILE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2162 */     synchronized (this.connection)
/*      */     {
/* 2164 */       ensureOpen();
/* 2165 */       BFILE localBFILE = null;
/*      */       
/* 2167 */       setIsNull(3);
/*      */       
/* 2169 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 2172 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 2174 */         setIsNull(localDatum == null);
/*      */         
/* 2176 */         if ((localDatum != null) && (!(localDatum instanceof BFILE)))
/*      */         {
/* 2178 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getBFILE");
/* 2179 */           localSQLException.fillInStackTrace();
/* 2180 */           throw localSQLException;
/*      */         }
/*      */         
/* 2183 */         localBFILE = (BFILE)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 2187 */         setIsNull(4);
/*      */         
/* 2189 */         localBFILE = this.resultSet.getBFILE(paramInt + 1);
/*      */       }
/*      */       
/* 2192 */       return localBFILE;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public BFILE getBfile(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2200 */     synchronized (this.connection)
/*      */     {
/* 2202 */       ensureOpen();
/* 2203 */       return getBFILE(paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public CustomDatum getCustomDatum(int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */     throws SQLException
/*      */   {
/* 2212 */     synchronized (this.connection)
/*      */     {
/* 2214 */       ensureOpen();
/* 2215 */       if (paramCustomDatumFactory == null)
/*      */       {
/* 2217 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 2218 */         ((SQLException)localObject1).fillInStackTrace();
/* 2219 */         throw ((Throwable)localObject1);
/*      */       }
/*      */       
/* 2222 */       Object localObject1 = null;
/*      */       
/* 2224 */       setIsNull(3);
/*      */       
/* 2226 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 2229 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 2231 */         setIsNull(localDatum == null);
/*      */         
/* 2233 */         localObject1 = paramCustomDatumFactory.create(localDatum, 0);
/*      */       }
/*      */       else
/*      */       {
/* 2237 */         setIsNull(4);
/*      */         
/* 2239 */         localObject1 = this.resultSet.getCustomDatum(paramInt + 1, paramCustomDatumFactory);
/*      */       }
/*      */       
/* 2242 */       return (CustomDatum)localObject1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ORAData getORAData(int paramInt, ORADataFactory paramORADataFactory)
/*      */     throws SQLException
/*      */   {
/* 2251 */     synchronized (this.connection)
/*      */     {
/* 2253 */       ensureOpen();
/* 2254 */       if (paramORADataFactory == null)
/*      */       {
/* 2256 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 2257 */         ((SQLException)localObject1).fillInStackTrace();
/* 2258 */         throw ((Throwable)localObject1);
/*      */       }
/*      */       
/* 2261 */       Object localObject1 = null;
/*      */       
/* 2263 */       setIsNull(3);
/*      */       
/* 2265 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 2268 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 2270 */         setIsNull(localDatum == null);
/*      */         
/* 2272 */         localObject1 = paramORADataFactory.create(localDatum, 0);
/*      */       }
/*      */       else
/*      */       {
/* 2276 */         setIsNull(4);
/*      */         
/* 2278 */         localObject1 = this.resultSet.getORAData(paramInt + 1, paramORADataFactory);
/*      */       }
/*      */       
/* 2281 */       return (ORAData)localObject1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NClob getNClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2293 */     ensureOpen();
/* 2294 */     NCLOB localNCLOB = getNCLOB(paramInt);
/*      */     
/* 2296 */     if (localNCLOB == null) { return null;
/*      */     }
/* 2298 */     if (!(localNCLOB instanceof NClob))
/*      */     {
/* 2300 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 184);
/* 2301 */       localSQLException.fillInStackTrace();
/* 2302 */       throw localSQLException;
/*      */     }
/*      */     
/* 2305 */     return localNCLOB;
/*      */   }
/*      */   
/*      */ 
/*      */   public String getNString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2312 */     ensureOpen();
/* 2313 */     return getString(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */   public Reader getNCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2320 */     ensureOpen();
/* 2321 */     return getCharacterStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */   public RowId getRowId(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2328 */     ensureOpen();
/* 2329 */     return getROWID(paramInt);
/*      */   }
/*      */   
/*      */   public SQLXML getSQLXML(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2335 */     synchronized (this.connection)
/*      */     {
/* 2337 */       ensureOpen();
/* 2338 */       SQLXML localSQLXML = null;
/*      */       
/* 2340 */       setIsNull(3);
/*      */       
/* 2342 */       if ((isOnInsertRow()) || ((isUpdatingRow()) && (isRowBufferUpdatedAt(paramInt))))
/*      */       {
/*      */ 
/* 2345 */         Datum localDatum = getRowBufferDatumAt(paramInt);
/*      */         
/* 2347 */         setIsNull(localDatum == null);
/*      */         
/* 2349 */         if ((localDatum != null) && (!(localDatum instanceof SQLXML)))
/*      */         {
/* 2351 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getSQLXML");
/* 2352 */           localSQLException.fillInStackTrace();
/* 2353 */           throw localSQLException;
/*      */         }
/*      */         
/* 2356 */         localSQLXML = (SQLXML)localDatum;
/*      */       }
/*      */       else
/*      */       {
/* 2360 */         setIsNull(4);
/*      */         
/* 2362 */         localSQLXML = this.resultSet.getSQLXML(paramInt + 1);
/*      */       }
/*      */       
/* 2365 */       return localSQLXML;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateRowId(int paramInt, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/* 2375 */     updateROWID(paramInt, (ROWID)paramRowId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2384 */     updateCharacterStream(paramInt, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateNCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 2392 */     updateCharacterStream(paramInt, paramReader);
/*      */   }
/*      */   
/*      */   public void updateSQLXML(int paramInt, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/* 2398 */     updateOracleObject(paramInt, (Datum)paramSQLXML);
/*      */   }
/*      */   
/*      */   public void updateNString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 2404 */     updateString(paramInt, paramString);
/*      */   }
/*      */   
/*      */   public void updateNClob(int paramInt, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/* 2410 */     updateClob(paramInt, paramNClob);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateAsciiStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2417 */     updateAsciiStream(paramInt, paramInputStream, (int)paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateAsciiStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 2424 */     updateAsciiStream(paramInt, paramInputStream, Integer.MAX_VALUE);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateBinaryStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2431 */     updateBinaryStream(paramInt, paramInputStream, (int)paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateBinaryStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 2438 */     updateBinaryStream(paramInt, paramInputStream, Integer.MAX_VALUE);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2445 */     updateCharacterStream(paramInt, paramReader, (int)paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 2452 */     updateCharacterStream(paramInt, paramReader, Integer.MAX_VALUE);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateBlob(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 2459 */     Blob localBlob = this.connection.createBlob();
/* 2460 */     addToTempLobsToFree(localBlob);
/* 2461 */     int i = ((BLOB)localBlob).getBufferSize();
/* 2462 */     OutputStream localOutputStream = localBlob.setBinaryStream(1L);
/* 2463 */     byte[] arrayOfByte = new byte[i];
/*      */     try
/*      */     {
/*      */       for (;;)
/*      */       {
/* 2468 */         int j = paramInputStream.read(arrayOfByte);
/* 2469 */         if (j == -1) break;
/* 2470 */         localOutputStream.write(arrayOfByte, 0, j);
/*      */       }
/* 2472 */       localOutputStream.close();
/* 2473 */       updateBlob(paramInt, localBlob);
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2477 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2478 */       localSQLException.fillInStackTrace();
/* 2479 */       throw localSQLException;
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
/*      */   public void updateBlob(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2494 */     Blob localBlob = this.connection.createBlob();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2499 */     addToTempLobsToFree(localBlob);
/* 2500 */     int i = ((BLOB)localBlob).getBufferSize();
/* 2501 */     OutputStream localOutputStream = localBlob.setBinaryStream(1L);
/* 2502 */     byte[] arrayOfByte = new byte[i];
/* 2503 */     long l = paramLong;
/*      */     try
/*      */     {
/* 2506 */       while (l > 0L)
/*      */       {
/* 2508 */         int j = paramInputStream.read(arrayOfByte, 0, Math.min(i, (int)l));
/* 2509 */         if (j == -1) break;
/* 2510 */         localOutputStream.write(arrayOfByte, 0, j);
/*      */         
/* 2512 */         l -= j;
/*      */       }
/* 2514 */       localOutputStream.close();
/* 2515 */       updateBlob(paramInt, localBlob);
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2519 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2520 */       localSQLException.fillInStackTrace();
/* 2521 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2533 */     updateClob(paramInt, paramReader, paramLong, (short)1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void updateClob(int paramInt, Reader paramReader, long paramLong, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 2541 */     NClob localNClob = paramShort == 1 ? this.connection.createClob() : this.connection.createNClob();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2549 */     addToTempLobsToFree(localNClob);
/* 2550 */     int i = ((CLOB)localNClob).getBufferSize();
/* 2551 */     Writer localWriter = localNClob.setCharacterStream(1L);
/* 2552 */     char[] arrayOfChar = new char[i];
/* 2553 */     long l = paramLong;
/*      */     try
/*      */     {
/* 2556 */       while (l > 0L)
/*      */       {
/* 2558 */         int j = paramReader.read(arrayOfChar, 0, Math.min(i, (int)l));
/* 2559 */         if (j == -1) break;
/* 2560 */         localWriter.write(arrayOfChar, 0, j);
/*      */         
/* 2562 */         l -= j;
/*      */       }
/* 2564 */       localWriter.close();
/* 2565 */       updateClob(paramInt, localNClob);
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2569 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2570 */       localSQLException.fillInStackTrace();
/* 2571 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 2584 */     Clob localClob = this.connection.createClob();
/* 2585 */     addToTempLobsToFree(localClob);
/* 2586 */     int i = ((CLOB)localClob).getBufferSize();
/* 2587 */     Writer localWriter = localClob.setCharacterStream(1L);
/* 2588 */     char[] arrayOfChar = new char[i];
/*      */     try
/*      */     {
/*      */       for (;;)
/*      */       {
/* 2593 */         int j = paramReader.read(arrayOfChar);
/* 2594 */         if (j == -1) break;
/* 2595 */         localWriter.write(arrayOfChar, 0, j);
/*      */       }
/* 2597 */       localWriter.close();
/* 2598 */       updateClob(paramInt, localClob);
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2602 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2603 */       localSQLException.fillInStackTrace();
/* 2604 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void updateClob(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2615 */     updateClob(paramInt1, paramInputStream, paramInt2, (short)1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void updateClob(int paramInt1, InputStream paramInputStream, int paramInt2, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 2625 */     NClob localNClob = paramShort == 1 ? this.connection.createClob() : this.connection.createNClob();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2633 */     addToTempLobsToFree(localNClob);
/* 2634 */     int i = ((CLOB)localNClob).getBufferSize();
/* 2635 */     OutputStream localOutputStream = localNClob.setAsciiStream(1L);
/* 2636 */     byte[] arrayOfByte = new byte[i];
/* 2637 */     long l = paramInt2;
/*      */     try
/*      */     {
/* 2640 */       while (l > 0L)
/*      */       {
/* 2642 */         int j = paramInputStream.read(arrayOfByte, 0, Math.min(i, (int)l));
/* 2643 */         if (j == -1) break;
/* 2644 */         localOutputStream.write(arrayOfByte, 0, j);
/*      */         
/* 2646 */         l -= j;
/*      */       }
/* 2648 */       localOutputStream.close();
/* 2649 */       updateClob(paramInt1, localNClob);
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2653 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2654 */       localSQLException.fillInStackTrace();
/* 2655 */       throw localSQLException;
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
/*      */   void updateNClob(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2669 */     NClob localNClob = this.connection.createNClob();
/* 2670 */     addToTempLobsToFree(localNClob);
/* 2671 */     int i = ((NCLOB)localNClob).getBufferSize();
/* 2672 */     OutputStream localOutputStream = localNClob.setAsciiStream(1L);
/* 2673 */     byte[] arrayOfByte = new byte[i];
/* 2674 */     long l = paramInt2;
/*      */     try
/*      */     {
/* 2677 */       while (l > 0L)
/*      */       {
/* 2679 */         int j = paramInputStream.read(arrayOfByte, 0, Math.min(i, (int)l));
/* 2680 */         if (j == -1) break;
/* 2681 */         localOutputStream.write(arrayOfByte, 0, j);
/*      */         
/* 2683 */         l -= j;
/*      */       }
/* 2685 */       localOutputStream.close();
/* 2686 */       updateNClob(paramInt1, localNClob);
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2690 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2691 */       localSQLException.fillInStackTrace();
/* 2692 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2702 */     NClob localNClob = this.connection.createNClob();
/* 2703 */     addToTempLobsToFree(localNClob);
/* 2704 */     int i = ((CLOB)localNClob).getBufferSize();
/* 2705 */     Writer localWriter = localNClob.setCharacterStream(1L);
/* 2706 */     char[] arrayOfChar = new char[i];
/* 2707 */     long l = paramLong;
/*      */     try
/*      */     {
/* 2710 */       while (l > 0L)
/*      */       {
/* 2712 */         int j = paramReader.read(arrayOfChar, 0, Math.min(i, (int)l));
/* 2713 */         if (j == -1) break;
/* 2714 */         localWriter.write(arrayOfChar, 0, j);
/*      */         
/* 2716 */         l -= j;
/*      */       }
/* 2718 */       localWriter.close();
/* 2719 */       updateNClob(paramInt, localNClob);
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2723 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2724 */       localSQLException.fillInStackTrace();
/* 2725 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 2735 */     NClob localNClob = this.connection.createNClob();
/* 2736 */     addToTempLobsToFree(localNClob);
/* 2737 */     int i = ((CLOB)localNClob).getBufferSize();
/* 2738 */     Writer localWriter = localNClob.setCharacterStream(1L);
/* 2739 */     char[] arrayOfChar = new char[i];
/*      */     try
/*      */     {
/*      */       for (;;)
/*      */       {
/* 2744 */         int j = paramReader.read(arrayOfChar);
/* 2745 */         if (j == -1) break;
/* 2746 */         localWriter.write(arrayOfChar, 0, j);
/*      */       }
/* 2748 */       localWriter.close();
/* 2749 */       updateNClob(paramInt, localNClob);
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2753 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2754 */       localSQLException.fillInStackTrace();
/* 2755 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 2761 */   ArrayList tempClobsToFree = null;
/* 2762 */   ArrayList tempBlobsToFree = null;
/*      */   
/*      */ 
/*      */   void addToTempLobsToFree(Clob paramClob)
/*      */   {
/* 2767 */     if (this.tempClobsToFree == null)
/* 2768 */       this.tempClobsToFree = new ArrayList();
/* 2769 */     this.tempClobsToFree.add(paramClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void addToTempLobsToFree(Blob paramBlob)
/*      */   {
/* 2776 */     if (this.tempBlobsToFree == null)
/* 2777 */       this.tempBlobsToFree = new ArrayList();
/* 2778 */     this.tempBlobsToFree.add(paramBlob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void cleanTempLobs()
/*      */   {
/* 2785 */     cleanTempClobs(this.tempClobsToFree);
/* 2786 */     cleanTempBlobs(this.tempBlobsToFree);
/* 2787 */     this.tempClobsToFree = null;
/* 2788 */     this.tempBlobsToFree = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void cleanTempBlobs(ArrayList paramArrayList)
/*      */   {
/* 2795 */     if (paramArrayList != null)
/*      */     {
/* 2797 */       Iterator localIterator = paramArrayList.iterator();
/*      */       
/* 2799 */       while (localIterator.hasNext())
/*      */       {
/*      */         try
/*      */         {
/* 2803 */           ((BLOB)localIterator.next()).freeTemporary();
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
/*      */   void cleanTempClobs(ArrayList paramArrayList)
/*      */   {
/* 2817 */     if (paramArrayList != null)
/*      */     {
/* 2819 */       Iterator localIterator = paramArrayList.iterator();
/*      */       
/* 2821 */       while (localIterator.hasNext())
/*      */       {
/*      */         try
/*      */         {
/* 2825 */           ((CLOB)localIterator.next()).freeTemporary();
/*      */         }
/*      */         catch (SQLException localSQLException) {}
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public ResultSetMetaData getMetaData()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 15	oracle/jdbc/driver/UpdatableResultSet:scrollStmt	Loracle/jdbc/driver/ScrollRsetStatement;
/*      */     //   4: checkcast 11	oracle/jdbc/driver/OracleStatement
/*      */     //   7: getfield 31	oracle/jdbc/driver/OracleStatement:closed	Z
/*      */     //   10: ifeq +23 -> 33
/*      */     //   13: aload_0
/*      */     //   14: invokevirtual 8	oracle/jdbc/driver/UpdatableResultSet:getConnectionDuringExceptionHandling	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   17: bipush 9
/*      */     //   19: ldc_w 266
/*      */     //   22: invokestatic 80	oracle/jdbc/driver/DatabaseError:createSqlException	(Loracle/jdbc/internal/OracleConnection;ILjava/lang/Object;)Ljava/sql/SQLException;
/*      */     //   25: astore_1
/*      */     //   26: aload_1
/*      */     //   27: invokevirtual 10	java/sql/SQLException:fillInStackTrace	()Ljava/lang/Throwable;
/*      */     //   30: pop
/*      */     //   31: aload_1
/*      */     //   32: athrow
/*      */     //   33: aload_0
/*      */     //   34: getfield 13	oracle/jdbc/driver/UpdatableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   37: dup
/*      */     //   38: astore_1
/*      */     //   39: monitorenter
/*      */     //   40: new 267	oracle/jdbc/driver/OracleResultSetMetaData
/*      */     //   43: dup
/*      */     //   44: aload_0
/*      */     //   45: getfield 13	oracle/jdbc/driver/UpdatableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   48: aload_0
/*      */     //   49: getfield 15	oracle/jdbc/driver/UpdatableResultSet:scrollStmt	Loracle/jdbc/driver/ScrollRsetStatement;
/*      */     //   52: checkcast 11	oracle/jdbc/driver/OracleStatement
/*      */     //   55: iconst_1
/*      */     //   56: invokespecial 268	oracle/jdbc/driver/OracleResultSetMetaData:<init>	(Loracle/jdbc/driver/PhysicalConnection;Loracle/jdbc/driver/OracleStatement;I)V
/*      */     //   59: aload_1
/*      */     //   60: monitorexit
/*      */     //   61: areturn
/*      */     //   62: astore_2
/*      */     //   63: aload_1
/*      */     //   64: monitorexit
/*      */     //   65: aload_2
/*      */     //   66: athrow
/*      */     // Line number table:
/*      */     //   Java source line #2843	-> byte code offset #0
/*      */     //   Java source line #2845	-> byte code offset #13
/*      */     //   Java source line #2846	-> byte code offset #26
/*      */     //   Java source line #2847	-> byte code offset #31
/*      */     //   Java source line #2850	-> byte code offset #33
/*      */     //   Java source line #2851	-> byte code offset #40
/*      */     //   Java source line #2854	-> byte code offset #62
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	67	0	this	UpdatableResultSet
/*      */     //   25	7	1	localSQLException	SQLException
/*      */     //   38	26	1	Ljava/lang/Object;	Object
/*      */     //   62	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   40	61	62	finally
/*      */     //   62	65	62	finally
/*      */   }
/*      */   
/*      */   public int findColumn(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2860 */     synchronized (this.connection)
/*      */     {
/* 2862 */       ensureOpen();
/* 2863 */       return this.resultSet.findColumn(paramString) - 1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFetchDirection(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2874 */     synchronized (this.connection)
/*      */     {
/* 2876 */       ensureOpen();
/* 2877 */       this.resultSet.setFetchDirection(paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */   public int getFetchDirection()
/*      */     throws SQLException
/*      */   {
/* 2884 */     synchronized (this.connection)
/*      */     {
/* 2886 */       ensureOpen();
/* 2887 */       return this.resultSet.getFetchDirection();
/*      */     }
/*      */   }
/*      */   
/*      */   public void setFetchSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2894 */     synchronized (this.connection)
/*      */     {
/* 2896 */       ensureOpen();
/* 2897 */       this.resultSet.setFetchSize(paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */   public int getFetchSize()
/*      */     throws SQLException
/*      */   {
/* 2904 */     synchronized (this.connection)
/*      */     {
/* 2906 */       ensureOpen();
/* 2907 */       return this.resultSet.getFetchSize();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public int getType()
/*      */     throws SQLException
/*      */   {
/* 2915 */     return this.rsetType;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getConcurrency()
/*      */     throws SQLException
/*      */   {
/* 2922 */     return 1008;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean rowUpdated()
/*      */     throws SQLException
/*      */   {
/* 2933 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean rowInserted()
/*      */     throws SQLException
/*      */   {
/* 2940 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean rowDeleted()
/*      */     throws SQLException
/*      */   {
/* 2947 */     return false;
/*      */   }
/*      */   
/*      */   public void insertRow()
/*      */     throws SQLException
/*      */   {
/* 2953 */     synchronized (this.connection)
/*      */     {
/* 2955 */       ensureOpen();
/* 2956 */       if (!isOnInsertRow())
/*      */       {
/* 2958 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 83);
/* 2959 */         localSQLException.fillInStackTrace();
/* 2960 */         throw localSQLException;
/*      */       }
/*      */       
/* 2963 */       prepareInsertRowStatement();
/* 2964 */       prepareInsertRowBinds();
/* 2965 */       executeInsertRow();
/*      */     }
/*      */   }
/*      */   
/*      */   public void updateRow()
/*      */     throws SQLException
/*      */   {
/* 2972 */     synchronized (this.connection)
/*      */     {
/* 2974 */       ensureOpen();
/*      */       
/*      */ 
/* 2977 */       if (isOnInsertRow())
/*      */       {
/* 2979 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 84);
/* 2980 */         localSQLException.fillInStackTrace();
/* 2981 */         throw localSQLException;
/*      */       }
/*      */       
/* 2984 */       int i = getNumColumnsChanged();
/*      */       
/* 2986 */       if (i > 0)
/*      */       {
/* 2988 */         prepareUpdateRowStatement(i);
/* 2989 */         prepareUpdateRowBinds(i);
/* 2990 */         executeUpdateRow();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void deleteRow()
/*      */     throws SQLException
/*      */   {
/* 2998 */     synchronized (this.connection)
/*      */     {
/* 3000 */       ensureOpen();
/*      */       
/*      */ 
/* 3003 */       if (isOnInsertRow())
/*      */       {
/* 3005 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 84);
/* 3006 */         localSQLException.fillInStackTrace();
/* 3007 */         throw localSQLException;
/*      */       }
/*      */       
/* 3010 */       prepareDeleteRowStatement();
/* 3011 */       prepareDeleteRowBinds();
/* 3012 */       executeDeleteRow();
/*      */     }
/*      */   }
/*      */   
/*      */   public void refreshRow()
/*      */     throws SQLException
/*      */   {
/* 3019 */     synchronized (this.connection)
/*      */     {
/* 3021 */       ensureOpen();
/*      */       
/* 3023 */       if (isOnInsertRow())
/*      */       {
/* 3025 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 84);
/* 3026 */         localSQLException.fillInStackTrace();
/* 3027 */         throw localSQLException;
/*      */       }
/*      */       
/* 3030 */       this.resultSet.refreshRow();
/*      */     }
/*      */   }
/*      */   
/*      */   public void cancelRowUpdates()
/*      */     throws SQLException
/*      */   {
/* 3037 */     synchronized (this.connection)
/*      */     {
/* 3039 */       ensureOpen();
/* 3040 */       if (this.isUpdating)
/*      */       {
/* 3042 */         this.isUpdating = false;
/*      */         
/* 3044 */         clearRowBuffer();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void moveToInsertRow()
/*      */     throws SQLException
/*      */   {
/* 3052 */     synchronized (this.connection)
/*      */     {
/* 3054 */       ensureOpen();
/* 3055 */       if (isOnInsertRow()) {
/* 3056 */         return;
/*      */       }
/* 3058 */       this.isInserting = true;
/*      */       
/*      */ 
/* 3061 */       if (this.rowBuffer == null) {
/* 3062 */         this.rowBuffer = new Object[getColumnCount()];
/*      */       }
/* 3064 */       if (this.m_nullIndicator == null) {
/* 3065 */         this.m_nullIndicator = new boolean[getColumnCount()];
/*      */       }
/* 3067 */       clearRowBuffer();
/*      */     }
/*      */   }
/*      */   
/*      */   public void moveToCurrentRow()
/*      */     throws SQLException
/*      */   {
/* 3074 */     synchronized (this.connection)
/*      */     {
/* 3076 */       ensureOpen();
/* 3077 */       cancelRowInserts();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 3085 */     synchronized (this.connection)
/*      */     {
/* 3087 */       if ((paramString == null) || (paramString.length() == 0)) {
/* 3088 */         updateNull(paramInt);
/*      */       } else {
/* 3090 */         updateObject(paramInt, paramString);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void updateNull(int paramInt) throws SQLException
/*      */   {
/* 3097 */     synchronized (this.connection)
/*      */     {
/* 3099 */       setRowBufferAt(paramInt, null);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateBoolean(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 3107 */     updateObject(paramInt, Boolean.valueOf(paramBoolean));
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateByte(int paramInt, byte paramByte)
/*      */     throws SQLException
/*      */   {
/* 3114 */     updateObject(paramInt, Integer.valueOf(paramByte));
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateShort(int paramInt, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 3121 */     updateObject(paramInt, Integer.valueOf(paramShort));
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateInt(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3128 */     updateObject(paramInt1, Integer.valueOf(paramInt2));
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateLong(int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3135 */     updateObject(paramInt, Long.valueOf(paramLong));
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateFloat(int paramInt, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 3142 */     updateObject(paramInt, Float.valueOf(paramFloat));
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateDouble(int paramInt, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 3149 */     updateObject(paramInt, Double.valueOf(paramDouble));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBigDecimal(int paramInt, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/* 3157 */     updateObject(paramInt, paramBigDecimal);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateBytes(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 3164 */     updateObject(paramInt, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateDate(int paramInt, Date paramDate)
/*      */     throws SQLException
/*      */   {
/* 3171 */     updateObject(paramInt, paramDate);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateTime(int paramInt, Time paramTime)
/*      */     throws SQLException
/*      */   {
/* 3178 */     updateObject(paramInt, paramTime);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateTimestamp(int paramInt, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/* 3186 */     updateObject(paramInt, paramTimestamp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3195 */     ensureOpen();
/* 3196 */     OracleResultSetMetaData localOracleResultSetMetaData = (OracleResultSetMetaData)getInternalMetadata();
/* 3197 */     int i = localOracleResultSetMetaData.getColumnType(1 + paramInt1);
/*      */     
/* 3199 */     if ((paramInputStream != null) && (paramInt2 > 0))
/*      */     {
/* 3201 */       switch (i)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */       case 2005: 
/* 3207 */         updateClob(paramInt1, paramInputStream, paramInt2);
/* 3208 */         break;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       case 2011: 
/* 3214 */         updateNClob(paramInt1, paramInputStream, paramInt2);
/* 3215 */         break;
/*      */       
/*      */ 
/*      */       case 2004: 
/* 3219 */         updateBlob(paramInt1, paramInputStream, paramInt2);
/* 3220 */         break;
/*      */       
/*      */       case -1: 
/* 3223 */         int[] arrayOfInt = { paramInt2, 1 };
/*      */         
/*      */ 
/*      */ 
/* 3227 */         setRowBufferAt(paramInt1, paramInputStream, arrayOfInt);
/* 3228 */         break;
/*      */       
/*      */       default: 
/*      */         try
/*      */         {
/* 3233 */           int j = 0;
/* 3234 */           int k = paramInt2;
/* 3235 */           byte[] arrayOfByte = new byte['Ѐ'];
/* 3236 */           char[] arrayOfChar = new char['Ѐ'];
/* 3237 */           StringBuilder localStringBuilder = new StringBuilder(1024);
/*      */           
/* 3239 */           while (k > 0)
/*      */           {
/* 3241 */             if (k >= 1024) {
/* 3242 */               j = paramInputStream.read(arrayOfByte);
/*      */             } else {
/* 3244 */               j = paramInputStream.read(arrayOfByte, 0, k);
/*      */             }
/*      */             
/*      */ 
/* 3248 */             if (j == -1) {
/*      */               break;
/*      */             }
/* 3251 */             DBConversion.asciiBytesToJavaChars(arrayOfByte, j, arrayOfChar);
/*      */             
/* 3253 */             localStringBuilder.append(arrayOfChar, 0, j);
/* 3254 */             k -= j;
/*      */           }
/*      */           
/* 3257 */           paramInputStream.close();
/* 3258 */           if (k == paramInt2)
/*      */           {
/* 3260 */             updateNull(paramInt1);
/* 3261 */             return;
/*      */           }
/*      */           
/* 3264 */           updateString(paramInt1, localStringBuilder.toString());
/*      */ 
/*      */         }
/*      */         catch (IOException localIOException)
/*      */         {
/* 3269 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3270 */           localSQLException.fillInStackTrace();
/* 3271 */           throw localSQLException;
/*      */         }
/*      */       
/*      */ 
/*      */       }
/*      */       
/*      */     } else {
/* 3278 */       setRowBufferAt(paramInt1, null);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3287 */     ensureOpen();
/* 3288 */     int i = getInternalMetadata().getColumnType(1 + paramInt1);
/*      */     
/* 3290 */     if ((paramInputStream != null) && (paramInt2 > 0))
/*      */     {
/* 3292 */       switch (i)
/*      */       {
/*      */       case 2004: 
/* 3295 */         updateBlob(paramInt1, paramInputStream, paramInt2);
/* 3296 */         break;
/*      */       
/*      */       case -4: 
/* 3299 */         int[] arrayOfInt = { paramInt2, 2 };
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 3304 */         setRowBufferAt(paramInt1, paramInputStream, arrayOfInt);
/* 3305 */         break;
/*      */       
/*      */       default: 
/*      */         try
/*      */         {
/* 3310 */           int j = 0;
/* 3311 */           int k = paramInt2;
/* 3312 */           byte[] arrayOfByte = new byte['Ѐ'];
/* 3313 */           ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(1024);
/*      */           
/*      */ 
/* 3316 */           while (k > 0)
/*      */           {
/* 3318 */             if (k >= 1024) {
/* 3319 */               j = paramInputStream.read(arrayOfByte);
/*      */             } else {
/* 3321 */               j = paramInputStream.read(arrayOfByte, 0, k);
/*      */             }
/*      */             
/*      */ 
/* 3325 */             if (j == -1) {
/*      */               break;
/*      */             }
/* 3328 */             localByteArrayOutputStream.write(arrayOfByte, 0, j);
/* 3329 */             k -= j;
/*      */           }
/*      */           
/* 3332 */           paramInputStream.close();
/* 3333 */           if (k == paramInt2)
/*      */           {
/* 3335 */             updateNull(paramInt1);
/* 3336 */             return;
/*      */           }
/*      */           
/* 3339 */           updateBytes(paramInt1, localByteArrayOutputStream.toByteArray());
/*      */ 
/*      */         }
/*      */         catch (IOException localIOException)
/*      */         {
/* 3344 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3345 */           localSQLException.fillInStackTrace();
/* 3346 */           throw localSQLException;
/*      */         }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       
/*      */     } else {
/* 3356 */       setRowBufferAt(paramInt1, null);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateCharacterStream(int paramInt1, Reader paramReader, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3364 */     int i = 0;int j = paramInt2;
/*      */     
/*      */ 
/* 3367 */     ensureOpen();
/* 3368 */     OracleResultSetMetaData localOracleResultSetMetaData = (OracleResultSetMetaData)getInternalMetadata();
/* 3369 */     int k = localOracleResultSetMetaData.getColumnType(1 + paramInt1);
/*      */     
/* 3371 */     if ((paramReader != null) && (paramInt2 > 0))
/*      */     {
/* 3373 */       switch (k)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */       case 2005: 
/* 3379 */         updateClob(paramInt1, paramReader, paramInt2);
/* 3380 */         break;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       case 2011: 
/* 3386 */         updateNClob(paramInt1, paramReader, paramInt2);
/* 3387 */         break;
/*      */       
/*      */ 
/*      */       case -1: 
/* 3391 */         int[] arrayOfInt = { paramInt2 };
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 3396 */         setRowBufferAt(paramInt1, paramReader, arrayOfInt);
/* 3397 */         break;
/*      */       
/*      */       default: 
/*      */         try
/*      */         {
/* 3402 */           char[] arrayOfChar = new char['Ѐ'];
/* 3403 */           localObject = new StringBuilder(1024);
/*      */           
/* 3405 */           while (j > 0)
/*      */           {
/* 3407 */             if (j >= 1024) {
/* 3408 */               i = paramReader.read(arrayOfChar);
/*      */             } else {
/* 3410 */               i = paramReader.read(arrayOfChar, 0, j);
/*      */             }
/*      */             
/*      */ 
/* 3414 */             if (i == -1) {
/*      */               break;
/*      */             }
/* 3417 */             ((StringBuilder)localObject).append(arrayOfChar, 0, i);
/* 3418 */             j -= i;
/*      */           }
/*      */           
/* 3421 */           paramReader.close();
/* 3422 */           if (j == paramInt2)
/*      */           {
/* 3424 */             updateNull(paramInt1);
/* 3425 */             return;
/*      */           }
/*      */           
/* 3428 */           updateString(paramInt1, ((StringBuilder)localObject).toString());
/*      */ 
/*      */         }
/*      */         catch (IOException localIOException)
/*      */         {
/* 3433 */           Object localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3434 */           ((SQLException)localObject).fillInStackTrace();
/* 3435 */           throw ((Throwable)localObject);
/*      */         }
/*      */       
/*      */ 
/*      */ 
/*      */       }
/*      */       
/*      */     } else {
/* 3443 */       setRowBufferAt(paramInt1, null);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateObject(int paramInt1, Object paramObject, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3452 */     updateObject(paramInt1, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateObject(int paramInt, Object paramObject)
/*      */     throws SQLException
/*      */   {
/* 3459 */     synchronized (this.connection)
/*      */     {
/* 3461 */       ensureOpen();
/* 3462 */       Datum localDatum = null;
/* 3463 */       if (paramObject != null) {
/*      */         Object localObject1;
/* 3465 */         if ((paramObject instanceof OracleData))
/*      */         {
/* 3467 */           localObject1 = ((OracleData)paramObject).toJDBCObject(this.connection);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3475 */           if ((localObject1 instanceof OracleProxy))
/*      */           {
/* 3477 */             final OracleProxy localOracleProxy = (OracleProxy)localObject1;
/* 3478 */             localObject1 = AccessController.doPrivileged(new PrivilegedAction()
/*      */             {
/*      */ 
/*      */               public Object run()
/*      */               {
/* 3483 */                 return ProxyFactory.extractDelegate(localOracleProxy);
/*      */               }
/*      */             });
/*      */           }
/*      */           
/* 3488 */           paramObject = localObject1;
/*      */         }
/* 3490 */         if ((paramObject instanceof Datum))
/*      */         {
/* 3492 */           localDatum = (Datum)paramObject;
/*      */         }
/*      */         else
/*      */         {
/* 3496 */           localObject1 = (OracleResultSetMetaData)getInternalMetadata();
/* 3497 */           int i = paramInt + 1;
/* 3498 */           if (((OracleResultSetMetaData)localObject1).getColumnType(i) == 96) {
/* 3499 */             int j = ((OracleResultSetMetaData)localObject1).getColumnDisplaySize(i);
/* 3500 */             String str = (String)paramObject;
/* 3501 */             for (int k = str.length(); k < j; k++) str = str + " ";
/*      */           }
/* 3503 */           localDatum = SQLUtil.makeOracleDatum(this.connection, paramObject, ((OracleResultSetMetaData)localObject1).getColumnType(i), null, ((OracleResultSetMetaData)localObject1).isNCHAR(i));
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 3508 */       setRowBufferAt(paramInt, localDatum);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateOracleObject(int paramInt, Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/* 3516 */     synchronized (this.connection)
/*      */     {
/* 3518 */       setRowBufferAt(paramInt, paramDatum);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateROWID(int paramInt, ROWID paramROWID)
/*      */     throws SQLException
/*      */   {
/* 3526 */     updateOracleObject(paramInt, paramROWID);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateNUMBER(int paramInt, NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 3533 */     updateOracleObject(paramInt, paramNUMBER);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateDATE(int paramInt, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/* 3540 */     updateOracleObject(paramInt, paramDATE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateINTERVALYM(int paramInt, INTERVALYM paramINTERVALYM)
/*      */     throws SQLException
/*      */   {
/* 3548 */     updateOracleObject(paramInt, paramINTERVALYM);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateINTERVALDS(int paramInt, INTERVALDS paramINTERVALDS)
/*      */     throws SQLException
/*      */   {
/* 3556 */     updateOracleObject(paramInt, paramINTERVALDS);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateTIMESTAMP(int paramInt, TIMESTAMP paramTIMESTAMP)
/*      */     throws SQLException
/*      */   {
/* 3563 */     updateOracleObject(paramInt, paramTIMESTAMP);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateTIMESTAMPTZ(int paramInt, TIMESTAMPTZ paramTIMESTAMPTZ)
/*      */     throws SQLException
/*      */   {
/* 3571 */     updateOracleObject(paramInt, paramTIMESTAMPTZ);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateTIMESTAMPLTZ(int paramInt, TIMESTAMPLTZ paramTIMESTAMPLTZ)
/*      */     throws SQLException
/*      */   {
/* 3579 */     updateOracleObject(paramInt, paramTIMESTAMPLTZ);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateARRAY(int paramInt, ARRAY paramARRAY)
/*      */     throws SQLException
/*      */   {
/* 3586 */     updateOracleObject(paramInt, paramARRAY);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateSTRUCT(int paramInt, STRUCT paramSTRUCT)
/*      */     throws SQLException
/*      */   {
/* 3593 */     updateOracleObject(paramInt, paramSTRUCT);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateOPAQUE(int paramInt, OPAQUE paramOPAQUE)
/*      */     throws SQLException
/*      */   {
/* 3600 */     updateOracleObject(paramInt, paramOPAQUE);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateREF(int paramInt, REF paramREF)
/*      */     throws SQLException
/*      */   {
/* 3607 */     updateOracleObject(paramInt, paramREF);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateCHAR(int paramInt, CHAR paramCHAR)
/*      */     throws SQLException
/*      */   {
/* 3614 */     updateOracleObject(paramInt, paramCHAR);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateRAW(int paramInt, RAW paramRAW)
/*      */     throws SQLException
/*      */   {
/* 3621 */     updateOracleObject(paramInt, paramRAW);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateBLOB(int paramInt, BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 3628 */     updateOracleObject(paramInt, paramBLOB);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateCLOB(int paramInt, CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 3635 */     updateOracleObject(paramInt, paramCLOB);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateBFILE(int paramInt, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 3642 */     updateOracleObject(paramInt, paramBFILE);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateBfile(int paramInt, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 3649 */     updateOracleObject(paramInt, paramBFILE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void updateCustomDatum(int paramInt, CustomDatum paramCustomDatum)
/*      */     throws SQLException
/*      */   {
/* 3657 */     throw new Error("wanna do datum = ((CustomDatum) x).toDatum(m_comm)");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateORAData(int paramInt, ORAData paramORAData)
/*      */     throws SQLException
/*      */   {
/* 3667 */     Datum localDatum = paramORAData.toDatum(this.connection);
/*      */     
/* 3669 */     updateOracleObject(paramInt, localDatum);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateRef(int paramInt, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/* 3676 */     updateREF(paramInt, (REF)paramRef);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateBlob(int paramInt, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/* 3683 */     updateBLOB(paramInt, (BLOB)paramBlob);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateClob(int paramInt, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/* 3690 */     updateCLOB(paramInt, (CLOB)paramClob);
/*      */   }
/*      */   
/*      */ 
/*      */   public void updateArray(int paramInt, Array paramArray)
/*      */     throws SQLException
/*      */   {
/* 3697 */     updateARRAY(paramInt, (ARRAY)paramArray);
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
/*      */   int getColumnCount()
/*      */     throws SQLException
/*      */   {
/* 3711 */     if (this.columnCount == 0)
/*      */     {
/* 3713 */       if ((this.resultSet instanceof OracleResultSetImpl))
/*      */       {
/* 3715 */         if (((OracleResultSetImpl)this.resultSet).statement.accessors != null) {
/* 3716 */           this.columnCount = ((OracleResultSetImpl)this.resultSet).statement.numberOfDefinePositions;
/*      */         }
/*      */         else {
/* 3719 */           this.columnCount = getInternalMetadata().getColumnCount();
/*      */         }
/*      */       } else {
/* 3722 */         this.columnCount = ((ScrollableResultSet)this.resultSet).getColumnCount();
/*      */       }
/*      */     }
/* 3725 */     return this.columnCount;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   ResultSetMetaData getInternalMetadata()
/*      */     throws SQLException
/*      */   {
/* 3735 */     if (this.rsetMetaData == null) {
/* 3736 */       this.rsetMetaData = this.resultSet.getMetaData();
/*      */     }
/* 3738 */     return this.rsetMetaData;
/*      */   }
/*      */   
/*      */   private void cancelRowChanges()
/*      */     throws SQLException
/*      */   {
/* 3744 */     synchronized (this.connection)
/*      */     {
/* 3746 */       if (this.isInserting) {
/* 3747 */         cancelRowInserts();
/*      */       }
/* 3749 */       if (this.isUpdating) {
/* 3750 */         cancelRowUpdates();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean isOnInsertRow()
/*      */   {
/* 3761 */     return this.isInserting;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void cancelRowInserts()
/*      */   {
/* 3771 */     if (this.isInserting)
/*      */     {
/* 3773 */       this.isInserting = false;
/*      */       
/* 3775 */       clearRowBuffer();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean isUpdatingRow()
/*      */   {
/* 3786 */     return this.isUpdating;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void clearRowBuffer()
/*      */   {
/*      */     int i;
/*      */     
/*      */ 
/* 3796 */     if (this.rowBuffer != null)
/*      */     {
/* 3798 */       for (i = 0; i < this.rowBuffer.length; i++) {
/* 3799 */         this.rowBuffer[i] = null;
/*      */       }
/*      */     }
/* 3802 */     if (this.m_nullIndicator != null)
/*      */     {
/* 3804 */       for (i = 0; i < this.m_nullIndicator.length; i++) {
/* 3805 */         this.m_nullIndicator[i] = false;
/*      */       }
/*      */     }
/* 3808 */     if (this.typeInfo != null)
/*      */     {
/* 3810 */       for (i = 0; i < this.typeInfo.length; i++) {
/* 3811 */         if (this.typeInfo[i] != null) {
/* 3812 */           for (int j = 0; j < this.typeInfo[i].length; j++) {
/* 3813 */             this.typeInfo[i][j] = 0;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 3819 */     cleanTempLobs();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void setRowBufferAt(int paramInt, Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/* 3830 */     setRowBufferAt(paramInt, paramDatum, (int[])null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void setRowBufferAt(int paramInt, Object paramObject, int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/* 3841 */     if (!this.isInserting)
/*      */     {
/* 3843 */       if ((isBeforeFirst()) || (isAfterLast()) || (getRow() == 0))
/*      */       {
/* 3845 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 82);
/* 3846 */         localSQLException.fillInStackTrace();
/* 3847 */         throw localSQLException;
/*      */       }
/*      */       
/* 3850 */       this.isUpdating = true;
/*      */     }
/*      */     
/* 3853 */     if ((paramInt < 1) || (paramInt > getColumnCount() - 1))
/*      */     {
/* 3855 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "setRowBufferAt");
/* 3856 */       localSQLException.fillInStackTrace();
/* 3857 */       throw localSQLException;
/*      */     }
/*      */     
/* 3860 */     if (this.rowBuffer == null) {
/* 3861 */       this.rowBuffer = new Object[getColumnCount()];
/*      */     }
/* 3863 */     if (this.m_nullIndicator == null)
/*      */     {
/* 3865 */       this.m_nullIndicator = new boolean[getColumnCount()];
/*      */       
/* 3867 */       for (int i = 0; i < getColumnCount(); i++) {
/* 3868 */         this.m_nullIndicator[i] = false;
/*      */       }
/*      */     }
/* 3871 */     if (paramArrayOfInt != null)
/*      */     {
/* 3873 */       if (this.typeInfo == null)
/*      */       {
/* 3875 */         this.typeInfo = new int[getColumnCount()][];
/*      */       }
/*      */       
/* 3878 */       this.typeInfo[paramInt] = paramArrayOfInt;
/*      */     }
/*      */     
/* 3881 */     this.rowBuffer[paramInt] = paramObject;
/* 3882 */     this.m_nullIndicator[paramInt] = (paramObject == null ? 1 : false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Datum getRowBufferDatumAt(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3892 */     if ((paramInt < 1) || (paramInt > getColumnCount() - 1))
/*      */     {
/* 3894 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "getRowBufferDatumAt");
/* 3895 */       ((SQLException)localObject1).fillInStackTrace();
/* 3896 */       throw ((Throwable)localObject1);
/*      */     }
/*      */     
/* 3899 */     Object localObject1 = null;
/*      */     
/* 3901 */     if (this.rowBuffer != null)
/*      */     {
/* 3903 */       Object localObject2 = this.rowBuffer[paramInt];
/*      */       
/* 3905 */       if (localObject2 != null)
/*      */       {
/* 3907 */         if ((localObject2 instanceof Datum))
/*      */         {
/* 3909 */           localObject1 = (Datum)localObject2;
/*      */         }
/*      */         else
/*      */         {
/* 3913 */           OracleResultSetMetaData localOracleResultSetMetaData = (OracleResultSetMetaData)getInternalMetadata();
/* 3914 */           int i = paramInt + 1;
/* 3915 */           localObject1 = SQLUtil.makeOracleDatum(this.connection, localObject2, localOracleResultSetMetaData.getColumnType(i), null, localOracleResultSetMetaData.isNCHAR(i));
/*      */           
/*      */ 
/*      */ 
/* 3919 */           this.rowBuffer[paramInt] = localObject1;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 3924 */     return (Datum)localObject1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Object getRowBufferAt(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3934 */     if ((paramInt < 1) || (paramInt > getColumnCount() - 1))
/*      */     {
/* 3936 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "getRowBufferDatumAt");
/* 3937 */       localSQLException.fillInStackTrace();
/* 3938 */       throw localSQLException;
/*      */     }
/*      */     
/* 3941 */     if (this.rowBuffer != null)
/*      */     {
/* 3943 */       return this.rowBuffer[paramInt];
/*      */     }
/*      */     
/* 3946 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean isRowBufferUpdatedAt(int paramInt)
/*      */   {
/* 3953 */     if (this.rowBuffer == null) {
/* 3954 */       return false;
/*      */     }
/* 3956 */     return (this.rowBuffer[paramInt] != null) || (this.m_nullIndicator[paramInt] != 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void prepareInsertRowStatement()
/*      */     throws SQLException
/*      */   {
/* 3966 */     if (this.insertStmt == null)
/*      */     {
/*      */ 
/* 3969 */       PreparedStatement localPreparedStatement = this.connection.prepareStatement(((OracleStatement)this.scrollStmt).sqlObject.getInsertSqlForUpdatableResultSet(this));
/*      */       
/*      */ 
/*      */ 
/* 3973 */       this.insertStmt = ((OraclePreparedStatement)((OraclePreparedStatementWrapper)localPreparedStatement).preparedStatement);
/* 3974 */       this.insertStmt.setQueryTimeout(((Statement)this.scrollStmt).getQueryTimeout());
/* 3975 */       if (((OracleStatement)this.scrollStmt).sqlObject.generatedSqlNeedEscapeProcessing()) {
/* 3976 */         this.insertStmt.setEscapeProcessing(true);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void prepareInsertRowBinds()
/*      */     throws SQLException
/*      */   {
/* 3988 */     int i = 1;
/*      */     
/*      */ 
/* 3991 */     i = prepareSubqueryBinds(this.insertStmt, i);
/*      */     
/* 3993 */     OracleResultSetMetaData localOracleResultSetMetaData = (OracleResultSetMetaData)getInternalMetadata();
/*      */     
/* 3995 */     for (int j = 1; j < getColumnCount(); j++)
/*      */     {
/* 3997 */       Object localObject = getRowBufferAt(j);
/*      */       
/* 3999 */       if (localObject != null)
/*      */       {
/* 4001 */         if ((localObject instanceof Reader))
/*      */         {
/* 4003 */           if (localOracleResultSetMetaData.isNCHAR(j + 1))
/* 4004 */             this.insertStmt.setFormOfUse(i, (short)2);
/* 4005 */           this.insertStmt.setCharacterStream(i + j - 1, (Reader)localObject, this.typeInfo[j][0]);
/*      */ 
/*      */         }
/* 4008 */         else if ((localObject instanceof InputStream))
/*      */         {
/* 4010 */           if (this.typeInfo[j][1] == 2) {
/* 4011 */             this.insertStmt.setBinaryStream(i + j - 1, (InputStream)localObject, this.typeInfo[j][0]);
/*      */ 
/*      */           }
/* 4014 */           else if (this.typeInfo[j][1] == 1) {
/* 4015 */             this.insertStmt.setAsciiStream(i + j - 1, (InputStream)localObject, this.typeInfo[j][0]);
/*      */           }
/*      */           
/*      */         }
/*      */         else
/*      */         {
/* 4021 */           Datum localDatum = getRowBufferDatumAt(j);
/*      */           
/* 4023 */           if (localOracleResultSetMetaData.isNCHAR(j + 1))
/* 4024 */             this.insertStmt.setFormOfUse(i + j - 1, (short)2);
/* 4025 */           this.insertStmt.setOracleObject(i + j - 1, localDatum);
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/* 4031 */         int k = getInternalMetadata().getColumnType(j + 1);
/*      */         
/* 4033 */         if ((k == 2006) || (k == 2002) || (k == 2008) || (k == 2007) || (k == 2003) || (k == 2009))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4043 */           this.insertStmt.setNull(i + j - 1, k, getInternalMetadata().getColumnTypeName(j + 1));
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/* 4048 */           this.insertStmt.setNull(i + j - 1, k);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void executeInsertRow()
/*      */     throws SQLException
/*      */   {
/* 4061 */     if (this.insertStmt.executeUpdate() != 1)
/*      */     {
/* 4063 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 85);
/* 4064 */       localSQLException.fillInStackTrace();
/* 4065 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int getNumColumnsChanged()
/*      */     throws SQLException
/*      */   {
/* 4078 */     int i = 0;
/*      */     
/* 4080 */     if (this.indexColsChanged == null) {
/* 4081 */       this.indexColsChanged = new int[getColumnCount()];
/*      */     }
/* 4083 */     if (this.rowBuffer != null)
/*      */     {
/* 4085 */       for (int j = 1; j < getColumnCount(); j++)
/*      */       {
/* 4087 */         if ((this.rowBuffer[j] != null) || ((this.rowBuffer[j] == null) && (this.m_nullIndicator[j] != 0)))
/*      */         {
/*      */ 
/* 4090 */           this.indexColsChanged[(i++)] = j;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 4095 */     return i;
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
/*      */   private void prepareUpdateRowStatement(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4109 */     if (this.updateStmt != null) {
/* 4110 */       this.updateStmt.close();
/*      */     }
/* 4112 */     PreparedStatement localPreparedStatement = this.connection.prepareStatement(((OracleStatement)this.scrollStmt).sqlObject.getUpdateSqlForUpdatableResultSet(this, paramInt, this.rowBuffer, this.indexColsChanged));
/*      */     
/*      */ 
/*      */ 
/* 4116 */     this.updateStmt = ((OraclePreparedStatement)((OraclePreparedStatementWrapper)localPreparedStatement).preparedStatement);
/* 4117 */     this.updateStmt.setQueryTimeout(((Statement)this.scrollStmt).getQueryTimeout());
/* 4118 */     if (((OracleStatement)this.scrollStmt).sqlObject.generatedSqlNeedEscapeProcessing()) {
/* 4119 */       this.updateStmt.setEscapeProcessing(true);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void prepareUpdateRowBinds(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4129 */     int i = 1;
/*      */     
/*      */ 
/* 4132 */     i = prepareSubqueryBinds(this.updateStmt, i);
/*      */     
/* 4134 */     OracleResultSetMetaData localOracleResultSetMetaData = (OracleResultSetMetaData)getInternalMetadata();
/*      */     
/* 4136 */     for (int j = 0; j < paramInt; j++)
/*      */     {
/* 4138 */       int k = this.indexColsChanged[j];
/* 4139 */       Object localObject = getRowBufferAt(k);
/*      */       
/* 4141 */       if (localObject != null)
/*      */       {
/* 4143 */         if ((localObject instanceof Reader))
/*      */         {
/* 4145 */           if (localOracleResultSetMetaData.isNCHAR(k + 1))
/* 4146 */             this.updateStmt.setFormOfUse(i, (short)2);
/* 4147 */           this.updateStmt.setCharacterStream(i++, (Reader)localObject, this.typeInfo[k][0]);
/*      */ 
/*      */         }
/* 4150 */         else if ((localObject instanceof InputStream))
/*      */         {
/* 4152 */           if (this.typeInfo[k][1] == 2)
/*      */           {
/*      */ 
/* 4155 */             this.updateStmt.setBinaryStream(i++, (InputStream)localObject, this.typeInfo[k][0]);
/*      */           }
/* 4157 */           else if (this.typeInfo[k][1] == 1)
/*      */           {
/* 4159 */             this.updateStmt.setAsciiStream(i++, (InputStream)localObject, this.typeInfo[k][0]);
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/* 4164 */           Datum localDatum = getRowBufferDatumAt(k);
/*      */           
/* 4166 */           if (localOracleResultSetMetaData.isNCHAR(k + 1))
/* 4167 */             this.updateStmt.setFormOfUse(i, (short)2);
/* 4168 */           this.updateStmt.setOracleObject(i++, localDatum);
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/* 4174 */         int m = getInternalMetadata().getColumnType(k + 1);
/*      */         
/* 4176 */         if ((m == 2006) || (m == 2002) || (m == 2008) || (m == 2007) || (m == 2003) || (m == 2009))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4185 */           this.updateStmt.setNull(i++, m, getInternalMetadata().getColumnTypeName(k + 1));
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/* 4190 */           if (localOracleResultSetMetaData.isNCHAR(k + 1))
/* 4191 */             this.updateStmt.setFormOfUse(i, (short)2);
/* 4192 */           this.updateStmt.setNull(i++, m);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 4198 */     prepareCompareSelfBinds(this.updateStmt, i);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void executeUpdateRow()
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 4210 */       if (this.updateStmt.executeUpdate() == 0)
/*      */       {
/* 4212 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 85);
/* 4213 */         localSQLException.fillInStackTrace();
/* 4214 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 4219 */       if (this.isCachedRset)
/*      */       {
/*      */ 
/* 4222 */         ((ScrollableResultSet)this.resultSet).refreshRowsInCache(getRow(), 1, 1000);
/*      */         
/* 4224 */         cancelRowUpdates();
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*      */ 
/* 4234 */       if (this.updateStmt != null)
/*      */       {
/* 4236 */         this.updateStmt.close();
/*      */         
/* 4238 */         this.updateStmt = null;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void prepareDeleteRowStatement()
/*      */     throws SQLException
/*      */   {
/* 4250 */     if (this.deleteStmt == null)
/*      */     {
/* 4252 */       PreparedStatement localPreparedStatement = this.connection.prepareStatement(((OracleStatement)this.scrollStmt).sqlObject.getDeleteSqlForUpdatableResultSet(this));
/*      */       
/*      */ 
/* 4255 */       this.deleteStmt = ((OraclePreparedStatement)((OraclePreparedStatementWrapper)localPreparedStatement).preparedStatement);
/* 4256 */       this.deleteStmt.setQueryTimeout(((Statement)this.scrollStmt).getQueryTimeout());
/* 4257 */       if (((OracleStatement)this.scrollStmt).sqlObject.generatedSqlNeedEscapeProcessing()) {
/* 4258 */         this.deleteStmt.setEscapeProcessing(true);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void prepareDeleteRowBinds()
/*      */     throws SQLException
/*      */   {
/* 4269 */     int i = 1;
/*      */     
/*      */ 
/* 4272 */     i = prepareSubqueryBinds(this.deleteStmt, i);
/*      */     
/*      */ 
/* 4275 */     prepareCompareSelfBinds(this.deleteStmt, i);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void executeDeleteRow()
/*      */     throws SQLException
/*      */   {
/* 4287 */     if (this.deleteStmt.executeUpdate() == 0)
/*      */     {
/* 4289 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 85);
/* 4290 */       localSQLException.fillInStackTrace();
/* 4291 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4296 */     if (this.isCachedRset) {
/* 4297 */       ((ScrollableResultSet)this.resultSet).removeRowInCache(getRow());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private int prepareCompareSelfBinds(OraclePreparedStatement paramOraclePreparedStatement, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4306 */     paramOraclePreparedStatement.setOracleObject(paramInt, this.resultSet.getOracleObject(1));
/*      */     
/* 4308 */     return paramInt + 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private int prepareSubqueryBinds(OraclePreparedStatement paramOraclePreparedStatement, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4316 */     int i = this.scrollStmt.copyBinds(paramOraclePreparedStatement, paramInt - 1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4331 */     return i + 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void setIsNull(int paramInt)
/*      */   {
/* 4338 */     this.wasNull = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void setIsNull(boolean paramBoolean)
/*      */   {
/* 4345 */     if (paramBoolean) {
/* 4346 */       this.wasNull = 1;
/*      */     } else {
/* 4348 */       this.wasNull = 2;
/*      */     }
/*      */   }
/*      */   
/*      */   public String getCursorName() throws SQLException
/*      */   {
/* 4354 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 4357 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23, "getCursorName");
/* 4358 */       localSQLException.fillInStackTrace();
/* 4359 */       throw localSQLException;
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
/*      */   protected OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 4376 */     return this.connection;
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
/* 4387 */     return this.resultSet == null ? null : this.resultSet.getOracleStatement();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 4392 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/UpdatableResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */