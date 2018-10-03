/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.sql.Date;
/*      */ import java.sql.NClob;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.RowId;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLXML;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.Map;
/*      */ import java.util.Vector;
/*      */ import oracle.jdbc.OracleResultSet.AuthorizationIndicator;
/*      */ import oracle.jdbc.internal.OracleConnection;
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
/*      */ class ScrollableResultSet
/*      */   extends BaseResultSet
/*      */ {
/*      */   PhysicalConnection connection;
/*      */   OracleResultSetImpl resultSet;
/*      */   ScrollRsetStatement scrollStmt;
/*      */   ResultSetMetaData metadata;
/*      */   private int rsetType;
/*      */   private int rsetConcurency;
/*      */   private int beginColumnIndex;
/*      */   private int columnCount;
/*      */   private int wasNull;
/*      */   OracleResultSetCache rsetCache;
/*      */   int currentRow;
/*      */   private int numRowsCached;
/*      */   private boolean allRowsCached;
/*      */   private int lastRefetchSz;
/*      */   private Vector refetchRowids;
/*      */   private OraclePreparedStatement refetchStmt;
/*      */   private int usrFetchDirection;
/*      */   private static final ClassRef XMLTYPE_CLASS;
/*      */   
/*      */   ScrollableResultSet(ScrollRsetStatement paramScrollRsetStatement, OracleResultSetImpl paramOracleResultSetImpl, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*   77 */     this.connection = ((OracleStatement)paramScrollRsetStatement).connection;
/*   78 */     this.resultSet = paramOracleResultSetImpl;
/*   79 */     this.metadata = null;
/*   80 */     this.scrollStmt = paramScrollRsetStatement;
/*   81 */     this.rsetType = paramInt1;
/*   82 */     this.rsetConcurency = paramInt2;
/*   83 */     this.beginColumnIndex = (needIdentifier(paramInt1, paramInt2) ? 1 : 0);
/*   84 */     this.columnCount = 0;
/*   85 */     this.wasNull = -1;
/*   86 */     this.rsetCache = paramScrollRsetStatement.getResultSetCache();
/*      */     
/*   88 */     if (this.rsetCache == null)
/*      */     {
/*   90 */       this.rsetCache = new OracleResultSetCacheImpl();
/*      */     }
/*      */     else
/*      */     {
/*      */       try
/*      */       {
/*   96 */         this.rsetCache.clear();
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/*  101 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  102 */         localSQLException.fillInStackTrace();
/*  103 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  108 */     this.currentRow = 0;
/*  109 */     this.numRowsCached = 0;
/*  110 */     this.allRowsCached = false;
/*  111 */     this.lastRefetchSz = 0;
/*  112 */     this.refetchRowids = null;
/*  113 */     this.refetchStmt = null;
/*  114 */     this.usrFetchDirection = 1000;
/*  115 */     getInternalMetadata();
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
/*  128 */     synchronized (this.connection)
/*      */     {
/*  130 */       if (this.closed) return;
/*  131 */       super.close();
/*  132 */       if (this.resultSet != null)
/*  133 */         this.resultSet.close();
/*  134 */       if (this.refetchStmt != null)
/*  135 */         this.refetchStmt.close();
/*  136 */       if (this.scrollStmt != null)
/*  137 */         this.scrollStmt.notifyCloseRset();
/*  138 */       if (this.refetchRowids != null) {
/*  139 */         this.refetchRowids.removeAllElements();
/*      */       }
/*  141 */       this.resultSet = null;
/*  142 */       this.scrollStmt = null;
/*  143 */       this.refetchStmt = null;
/*  144 */       this.refetchRowids = null;
/*  145 */       this.metadata = null;
/*      */       
/*      */       try
/*      */       {
/*  149 */         if (this.rsetCache != null)
/*      */         {
/*  151 */           this.rsetCache.clear();
/*  152 */           this.rsetCache.close();
/*      */         }
/*      */         
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/*  158 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  159 */         localSQLException.fillInStackTrace();
/*  160 */         throw localSQLException;
/*      */       }
/*      */       finally
/*      */       {
/*  164 */         this.rsetCache = null;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean wasNull()
/*      */     throws SQLException
/*      */   {
/*  172 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  175 */       if (this.wasNull == -1)
/*      */       {
/*  177 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 24);
/*  178 */         localSQLException.fillInStackTrace();
/*  179 */         throw localSQLException;
/*      */       }
/*      */       
/*  182 */       return this.wasNull == 1;
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
/*      */   void resetBeginColumnIndex()
/*      */   {
/*  203 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  206 */       this.beginColumnIndex = 0;
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
/*      */   int removeRowInCache(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  226 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  229 */       if ((!isEmptyResultSet()) && (isValidRow(paramInt)))
/*      */       {
/*  231 */         removeCachedRowAt(paramInt);
/*      */         
/*  233 */         this.numRowsCached -= 1;
/*      */         
/*  235 */         if (paramInt >= this.currentRow) {
/*  236 */           this.currentRow -= 1;
/*      */         }
/*  238 */         return 1;
/*      */       }
/*      */       
/*  241 */       return 0;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   int refreshRowsInCache(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  252 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  255 */       OracleResultSetImpl localOracleResultSetImpl = null;
/*  256 */       int i = 0;
/*      */       
/*      */ 
/*  259 */       i = get_refetch_size(paramInt1, paramInt2, paramInt3);
/*      */       
/*      */       try
/*      */       {
/*  263 */         if (i > 0)
/*      */         {
/*      */ 
/*      */ 
/*  267 */           if (i != this.lastRefetchSz)
/*      */           {
/*  269 */             if (this.refetchStmt != null) {
/*  270 */               this.refetchStmt.close();
/*      */             }
/*  272 */             this.refetchStmt = prepare_refetch_statement(i);
/*  273 */             this.refetchStmt.setQueryTimeout(((OracleStatement)this.scrollStmt).getQueryTimeout());
/*  274 */             this.lastRefetchSz = i;
/*      */           }
/*      */           
/*      */ 
/*  278 */           prepare_refetch_binds(this.refetchStmt, i);
/*      */           
/*      */ 
/*  281 */           localOracleResultSetImpl = (OracleResultSetImpl)this.refetchStmt.executeQuery();
/*      */           
/*      */ 
/*  284 */           save_refetch_results(localOracleResultSetImpl, paramInt1, i, paramInt3);
/*      */         }
/*      */         
/*      */       }
/*      */       finally
/*      */       {
/*  290 */         if (localOracleResultSetImpl != null) {
/*  291 */           localOracleResultSetImpl.close();
/*      */         }
/*      */       }
/*  294 */       return i;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean next()
/*      */     throws SQLException
/*      */   {
/*  305 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  308 */       if (this.closed)
/*      */       {
/*  310 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  311 */         localSQLException.fillInStackTrace();
/*  312 */         throw localSQLException;
/*      */       }
/*      */       
/*  315 */       if (isEmptyResultSet())
/*      */       {
/*  317 */         return false;
/*      */       }
/*      */       
/*      */ 
/*  321 */       if (this.currentRow < 1)
/*      */       {
/*  323 */         this.currentRow = 1;
/*      */       }
/*      */       else
/*      */       {
/*  327 */         this.currentRow += 1;
/*      */       }
/*      */       
/*  330 */       return isValidRow(this.currentRow);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isBeforeFirst()
/*      */     throws SQLException
/*      */   {
/*  338 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  341 */       if (this.closed)
/*      */       {
/*  343 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  344 */         localSQLException.fillInStackTrace();
/*  345 */         throw localSQLException;
/*      */       }
/*  347 */       return (!isEmptyResultSet()) && (this.currentRow < 1);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isAfterLast()
/*      */     throws SQLException
/*      */   {
/*  354 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  357 */       if (this.closed)
/*      */       {
/*  359 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  360 */         localSQLException.fillInStackTrace();
/*  361 */         throw localSQLException;
/*      */       }
/*  363 */       return (!isEmptyResultSet()) && (this.currentRow > 0) && (!isValidRow(this.currentRow));
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isFirst()
/*      */     throws SQLException
/*      */   {
/*  370 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  373 */       if (this.closed)
/*      */       {
/*  375 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  376 */         localSQLException.fillInStackTrace();
/*  377 */         throw localSQLException;
/*      */       }
/*  379 */       return this.currentRow == 1;
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isLast()
/*      */     throws SQLException
/*      */   {
/*  386 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  389 */       if (this.closed)
/*      */       {
/*  391 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  392 */         localSQLException.fillInStackTrace();
/*  393 */         throw localSQLException;
/*      */       }
/*  395 */       return (!isEmptyResultSet()) && (isValidRow(this.currentRow)) && (!isValidRow(this.currentRow + 1));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void beforeFirst()
/*      */     throws SQLException
/*      */   {
/*  403 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  406 */       if (this.closed)
/*      */       {
/*  408 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  409 */         localSQLException.fillInStackTrace();
/*  410 */         throw localSQLException;
/*      */       }
/*  412 */       if (!isEmptyResultSet()) {
/*  413 */         this.currentRow = 0;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void afterLast() throws SQLException
/*      */   {
/*  420 */     synchronized (this.connection) {
/*  421 */       if (this.closed)
/*      */       {
/*  423 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  424 */         localSQLException.fillInStackTrace();
/*  425 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  429 */       if (!isEmptyResultSet()) {
/*  430 */         this.currentRow = (getLastRow() + 1);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean first() throws SQLException
/*      */   {
/*  437 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  440 */       if (this.closed)
/*      */       {
/*  442 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  443 */         localSQLException.fillInStackTrace();
/*  444 */         throw localSQLException;
/*      */       }
/*  446 */       if (isEmptyResultSet())
/*      */       {
/*  448 */         return false;
/*      */       }
/*      */       
/*      */ 
/*  452 */       this.currentRow = 1;
/*      */       
/*  454 */       return isValidRow(this.currentRow);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean last()
/*      */     throws SQLException
/*      */   {
/*  462 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  465 */       if (this.closed)
/*      */       {
/*  467 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  468 */         localSQLException.fillInStackTrace();
/*  469 */         throw localSQLException;
/*      */       }
/*  471 */       if (isEmptyResultSet())
/*      */       {
/*  473 */         return false;
/*      */       }
/*      */       
/*      */ 
/*  477 */       this.currentRow = getLastRow();
/*      */       
/*  479 */       return isValidRow(this.currentRow);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public int getRow()
/*      */     throws SQLException
/*      */   {
/*  487 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  490 */       if (this.closed)
/*      */       {
/*  492 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  493 */         localSQLException.fillInStackTrace();
/*  494 */         throw localSQLException;
/*      */       }
/*  496 */       if (isValidRow(this.currentRow)) {
/*  497 */         return this.currentRow;
/*      */       }
/*  499 */       return 0;
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean absolute(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  506 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException;
/*  509 */       if (this.closed)
/*      */       {
/*  511 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  512 */         localSQLException.fillInStackTrace();
/*  513 */         throw localSQLException;
/*      */       }
/*  515 */       if (paramInt == 0)
/*      */       {
/*  517 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "absolute(" + paramInt + ")");
/*  518 */         localSQLException.fillInStackTrace();
/*  519 */         throw localSQLException;
/*      */       }
/*      */       
/*  522 */       if (isEmptyResultSet())
/*      */       {
/*  524 */         return false;
/*      */       }
/*  526 */       if (paramInt > 0)
/*      */       {
/*  528 */         this.currentRow = paramInt;
/*      */       }
/*  530 */       else if (paramInt < 0)
/*      */       {
/*  532 */         this.currentRow = (getLastRow() + 1 + paramInt);
/*      */       }
/*      */       
/*  535 */       return isValidRow(this.currentRow);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean relative(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  542 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  545 */       if (this.closed)
/*      */       {
/*  547 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  548 */         localSQLException.fillInStackTrace();
/*  549 */         throw localSQLException;
/*      */       }
/*  551 */       if (isEmptyResultSet())
/*      */       {
/*  553 */         return false;
/*      */       }
/*  555 */       if (isValidRow(this.currentRow))
/*      */       {
/*  557 */         this.currentRow += paramInt;
/*      */         
/*  559 */         return isValidRow(this.currentRow);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  564 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 82, "relative");
/*  565 */       localSQLException.fillInStackTrace();
/*  566 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean previous()
/*      */     throws SQLException
/*      */   {
/*  575 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  578 */       if (this.closed)
/*      */       {
/*  580 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  581 */         localSQLException.fillInStackTrace();
/*  582 */         throw localSQLException;
/*      */       }
/*  584 */       if (isEmptyResultSet())
/*      */       {
/*  586 */         return false;
/*      */       }
/*  588 */       if (isAfterLast())
/*      */       {
/*  590 */         this.currentRow = getLastRow();
/*      */       }
/*      */       else
/*      */       {
/*  594 */         this.currentRow -= 1;
/*      */       }
/*      */       
/*  597 */       return isValidRow(this.currentRow);
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
/*  608 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  611 */       if (this.closed)
/*      */       {
/*  613 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  614 */         ((SQLException)localObject1).fillInStackTrace();
/*  615 */         throw ((Throwable)localObject1);
/*      */       }
/*      */       
/*  618 */       this.wasNull = -1;
/*      */       
/*  620 */       if (!isValidRow(this.currentRow))
/*      */       {
/*  622 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/*  623 */         ((SQLException)localObject1).fillInStackTrace();
/*  624 */         throw ((Throwable)localObject1);
/*      */       }
/*      */       
/*  627 */       if ((paramInt < 1) || (paramInt > getColumnCount()))
/*      */       {
/*  629 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  630 */         ((SQLException)localObject1).fillInStackTrace();
/*  631 */         throw ((Throwable)localObject1);
/*      */       }
/*      */       
/*  634 */       Object localObject1 = getCachedDatumValueAt(this.currentRow, paramInt + this.beginColumnIndex);
/*      */       
/*      */ 
/*  637 */       this.wasNull = (localObject1 == null ? 1 : 0);
/*      */       
/*  639 */       return (Datum)localObject1;
/*      */     }
/*      */   }
/*      */   
/*      */   public String getString(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  646 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  649 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  651 */       if (localDatum != null)
/*      */       {
/*  653 */         switch (getInternalMetadata().getColumnType(paramInt + this.beginColumnIndex))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         case 2005: 
/*      */         case 2011: 
/*  663 */           CLOB localCLOB = (CLOB)localDatum;
/*  664 */           return localCLOB.getSubString(1L, (int)localCLOB.length());
/*      */         
/*      */ 
/*      */ 
/*      */         case 91: 
/*  669 */           if (this.connection.mapDateToTimestamp) {
/*  670 */             return localDatum.toJdbc().toString();
/*      */           }
/*  672 */           return localDatum.dateValue().toString();
/*      */         
/*      */ 
/*      */ 
/*      */         case 93: 
/*  677 */           if ((localDatum instanceof DATE))
/*      */           {
/*  679 */             if (this.connection.mapDateToTimestamp) {
/*  680 */               return localDatum.toJdbc().toString();
/*      */             }
/*  682 */             return localDatum.dateValue().toString();
/*      */           }
/*      */           
/*      */ 
/*  686 */           if (this.connection.j2ee13Compliant)
/*      */           {
/*      */ 
/*  689 */             return localDatum.toJdbc().toString();
/*      */           }
/*      */           
/*      */ 
/*  693 */           return localDatum.stringValue(this.connection);
/*      */         }
/*      */         
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  700 */         return localDatum.stringValue(this.connection);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  705 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean getBoolean(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  712 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  715 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  717 */       if (localDatum != null)
/*      */       {
/*  719 */         return localDatum.booleanValue();
/*      */       }
/*      */       
/*  722 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */   public OracleResultSet.AuthorizationIndicator getAuthorizationIndicator(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  729 */     synchronized (this.connection)
/*      */     {
/*  731 */       if (!isValidRow(this.currentRow))
/*      */       {
/*  733 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/*  734 */         ((SQLException)localObject1).fillInStackTrace();
/*  735 */         throw ((Throwable)localObject1);
/*      */       }
/*      */       
/*  738 */       if ((paramInt < 1) || (paramInt > getColumnCount()))
/*      */       {
/*  740 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  741 */         ((SQLException)localObject1).fillInStackTrace();
/*  742 */         throw ((Throwable)localObject1);
/*      */       }
/*      */       
/*  745 */       Object localObject1 = null;
/*  746 */       OracleResultSet.AuthorizationIndicator localAuthorizationIndicator = null;
/*      */       
/*      */       try
/*      */       {
/*  750 */         localObject1 = (CachedRowElement)this.rsetCache.get(this.currentRow, paramInt);
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/*  755 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  756 */         localSQLException.fillInStackTrace();
/*  757 */         throw localSQLException;
/*      */       }
/*      */       
/*  760 */       if (localObject1 != null) {
/*  761 */         localAuthorizationIndicator = ((CachedRowElement)localObject1).getIndicator();
/*      */       }
/*  763 */       return localAuthorizationIndicator;
/*      */     }
/*      */   }
/*      */   
/*      */   public byte getByte(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  770 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  773 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  775 */       if (localDatum != null) {
/*  776 */         return localDatum.byteValue();
/*      */       }
/*  778 */       return 0;
/*      */     }
/*      */   }
/*      */   
/*      */   public short getShort(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  785 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  788 */       long l = getLong(paramInt);
/*      */       
/*  790 */       if ((l > 65537L) || (l < -65538L))
/*      */       {
/*  792 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 26, "getShort");
/*  793 */         localSQLException.fillInStackTrace();
/*  794 */         throw localSQLException;
/*      */       }
/*      */       
/*  797 */       return (short)(int)l;
/*      */     }
/*      */   }
/*      */   
/*      */   public int getInt(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  804 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  807 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  809 */       if (localDatum != null)
/*      */       {
/*  811 */         return localDatum.intValue();
/*      */       }
/*      */       
/*  814 */       return 0;
/*      */     }
/*      */   }
/*      */   
/*      */   public long getLong(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  821 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  824 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  826 */       if (localDatum != null)
/*      */       {
/*  828 */         return localDatum.longValue();
/*      */       }
/*      */       
/*  831 */       return 0L;
/*      */     }
/*      */   }
/*      */   
/*      */   public float getFloat(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  838 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  841 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  843 */       if (localDatum != null)
/*      */       {
/*  845 */         return localDatum.floatValue();
/*      */       }
/*      */       
/*  848 */       return 0.0F;
/*      */     }
/*      */   }
/*      */   
/*      */   public double getDouble(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  855 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  858 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  860 */       if (localDatum != null)
/*      */       {
/*  862 */         return localDatum.doubleValue();
/*      */       }
/*      */       
/*  865 */       return 0.0D;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  873 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  876 */       Datum localDatum = getOracleObject(paramInt1);
/*      */       
/*  878 */       if (localDatum != null)
/*      */       {
/*  880 */         return localDatum.bigDecimalValue();
/*      */       }
/*      */       
/*  883 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public byte[] getBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  890 */     synchronized (this.connection)
/*      */     {
/*  892 */       byte[] arrayOfByte = null;
/*  893 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  895 */       if (localDatum != null)
/*      */       {
/*  897 */         if ((localDatum instanceof RAW)) {
/*  898 */           arrayOfByte = ((RAW)localDatum).shareBytes();
/*  899 */         } else if ((localDatum instanceof BLOB))
/*      */         {
/*  901 */           BLOB localBLOB = (BLOB)localDatum;
/*  902 */           long l = localBLOB.length();
/*  903 */           if (l > 2147483647L)
/*      */           {
/*      */ 
/*  906 */             SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 151);
/*  907 */             localSQLException.fillInStackTrace();
/*  908 */             throw localSQLException;
/*      */           }
/*      */           
/*  911 */           arrayOfByte = localBLOB.getBytes(1L, (int)l);
/*  912 */           if (localBLOB.isTemporary()) this.resultSet.statement.addToTempLobsToFree(localBLOB);
/*      */         }
/*      */         else
/*      */         {
/*  916 */           arrayOfByte = localDatum.getBytes();
/*      */         }
/*      */       }
/*  919 */       return arrayOfByte;
/*      */     }
/*      */   }
/*      */   
/*      */   public Date getDate(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  926 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  929 */       Datum localDatum = getOracleObject(paramInt);
/*  930 */       Date localDate = null;
/*      */       
/*  932 */       if (localDatum != null)
/*      */       {
/*  934 */         ResultSetMetaData localResultSetMetaData = getInternalMetadata();
/*  935 */         switch (localResultSetMetaData.getColumnType(paramInt + this.beginColumnIndex))
/*      */         {
/*      */ 
/*      */ 
/*      */         case -101: 
/*  940 */           localDate = ((TIMESTAMPTZ)localDatum).dateValue(this.connection);
/*  941 */           break;
/*      */         
/*      */         case -102: 
/*  944 */           localDate = ((TIMESTAMPLTZ)localDatum).dateValue(this.connection);
/*  945 */           break;
/*      */         
/*      */         default: 
/*  948 */           localDate = localDatum.dateValue();
/*      */         }
/*      */         
/*      */       }
/*      */       
/*  953 */       return localDate;
/*      */     }
/*      */   }
/*      */   
/*      */   public Time getTime(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  960 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  963 */       Datum localDatum = getOracleObject(paramInt);
/*  964 */       Time localTime = null;
/*      */       
/*  966 */       if (localDatum != null)
/*      */       {
/*  968 */         ResultSetMetaData localResultSetMetaData = getInternalMetadata();
/*  969 */         switch (localResultSetMetaData.getColumnType(paramInt + this.beginColumnIndex))
/*      */         {
/*      */         case -101: 
/*  972 */           localTime = ((TIMESTAMPTZ)localDatum).timeValue(this.connection);
/*  973 */           break;
/*      */         
/*      */         case -102: 
/*  976 */           localTime = ((TIMESTAMPLTZ)localDatum).timeValue(this.connection, this.connection.getDbTzCalendar());
/*      */           
/*  978 */           break;
/*      */         
/*      */         default: 
/*  981 */           localTime = localDatum.timeValue();
/*      */         }
/*      */         
/*      */       }
/*      */       
/*  986 */       return localTime;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  994 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  997 */       Datum localDatum = getOracleObject(paramInt);
/*  998 */       Timestamp localTimestamp = null;
/*      */       
/* 1000 */       if (localDatum != null)
/*      */       {
/* 1002 */         ResultSetMetaData localResultSetMetaData = getInternalMetadata();
/* 1003 */         switch (localResultSetMetaData.getColumnType(paramInt + this.beginColumnIndex))
/*      */         {
/*      */         case -101: 
/* 1006 */           localTimestamp = ((TIMESTAMPTZ)localDatum).timestampValue(this.connection);
/* 1007 */           break;
/*      */         
/*      */         case -102: 
/* 1010 */           localTimestamp = ((TIMESTAMPLTZ)localDatum).timestampValue(this.connection, this.connection.getDbTzCalendar());
/*      */           
/* 1012 */           break;
/*      */         
/*      */         default: 
/* 1015 */           localTimestamp = localDatum.timestampValue();
/*      */         }
/*      */         
/*      */       }
/*      */       
/* 1020 */       return localTimestamp;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public InputStream getAsciiStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1028 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1031 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1033 */       if (localDatum != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 1038 */         int i = getInternalMetadata().getColumnType(paramInt + this.beginColumnIndex);
/*      */         
/*      */ 
/* 1041 */         if (((localDatum instanceof CHAR)) && ((i == -15) || (i == -9)))
/*      */         {
/*      */ 
/* 1044 */           DBConversion localDBConversion = this.connection.conversion;
/* 1045 */           byte[] arrayOfByte = localDatum.shareBytes();
/*      */           
/* 1047 */           return localDBConversion.ConvertStream(new ByteArrayInputStream(arrayOfByte), 12);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1052 */         return localDatum.asciiStreamValue();
/*      */       }
/*      */       
/* 1055 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public InputStream getUnicodeStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1063 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1066 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1068 */       if (localDatum != null)
/*      */       {
/* 1070 */         DBConversion localDBConversion = this.connection.conversion;
/* 1071 */         byte[] arrayOfByte = localDatum.shareBytes();
/*      */         
/* 1073 */         if ((localDatum instanceof RAW))
/*      */         {
/* 1075 */           return localDBConversion.ConvertStream(new ByteArrayInputStream(arrayOfByte), 3);
/*      */         }
/*      */         
/* 1078 */         if ((localDatum instanceof CHAR))
/*      */         {
/* 1080 */           return localDBConversion.ConvertStream(new ByteArrayInputStream(arrayOfByte), 1);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1085 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getUnicodeStream");
/* 1086 */         localSQLException.fillInStackTrace();
/* 1087 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1091 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public InputStream getBinaryStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1099 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1102 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1104 */       if (localDatum != null)
/*      */       {
/* 1106 */         return localDatum.binaryStreamValue();
/*      */       }
/*      */       
/* 1109 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1119 */     return getObject(paramInt, this.connection.getTypeMap());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Reader getCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1127 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1130 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1132 */       if (localDatum != null)
/*      */       {
/* 1134 */         return localDatum.characterStreamValue();
/*      */       }
/*      */       
/* 1137 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1147 */     return getBigDecimal(paramInt, 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static
/*      */   {
/* 1156 */     ClassRef localClassRef = null;
/*      */     try {
/* 1158 */       localClassRef = ClassRef.newInstance("oracle.xdb.XMLType");
/*      */     }
/*      */     catch (ClassNotFoundException localClassNotFoundException) {}
/* 1161 */     XMLTYPE_CLASS = localClassRef;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getObject(int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1169 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1172 */       Object localObject1 = null;
/* 1173 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1175 */       if (localDatum != null)
/*      */       {
/* 1177 */         int i = getInternalMetadata().getColumnType(paramInt + this.beginColumnIndex);
/*      */         
/* 1179 */         switch (i)
/*      */         {
/*      */         case 2002: 
/* 1182 */           localObject1 = ((STRUCT)localDatum).toJdbc(paramMap);
/* 1183 */           break;
/*      */         
/*      */         case 91: 
/* 1186 */           if (this.connection.mapDateToTimestamp) {
/* 1187 */             localObject1 = localDatum.toJdbc();
/*      */           } else
/* 1189 */             localObject1 = localDatum.dateValue();
/* 1190 */           break;
/*      */         case 93: 
/* 1192 */           if ((localDatum instanceof DATE))
/*      */           {
/* 1194 */             if (this.connection.mapDateToTimestamp) {
/* 1195 */               localObject1 = localDatum.toJdbc();
/*      */             } else {
/* 1197 */               localObject1 = localDatum.dateValue();
/*      */             }
/*      */             
/*      */           }
/* 1201 */           else if (this.connection.j2ee13Compliant)
/*      */           {
/*      */ 
/* 1204 */             localObject1 = localDatum.toJdbc();
/*      */           }
/*      */           else
/*      */           {
/* 1208 */             localObject1 = localDatum;
/*      */           }
/*      */           
/* 1211 */           break;
/*      */         case -102: 
/*      */         case -101: 
/* 1214 */           localObject1 = localDatum;
/* 1215 */           break;
/*      */         
/*      */ 
/*      */ 
/*      */         case 2007: 
/* 1220 */           localObject1 = ((OPAQUE)localDatum).toJdbc(paramMap);
/* 1221 */           break;
/*      */         
/*      */         default: 
/* 1224 */           localObject1 = localDatum.toJdbc();
/*      */         }
/*      */         
/*      */       }
/* 1228 */       return localObject1;
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
/*      */   public Date getDate(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1282 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1285 */       Datum localDatum = getOracleObject(paramInt);
/* 1286 */       Date localDate = null;
/*      */       
/* 1288 */       if (localDatum != null)
/*      */       {
/* 1290 */         ResultSetMetaData localResultSetMetaData = getInternalMetadata();
/* 1291 */         switch (localResultSetMetaData.getColumnType(paramInt + this.beginColumnIndex))
/*      */         {
/*      */         case -101: 
/* 1294 */           localDate = new Date(((TIMESTAMPTZ)localDatum).timestampValue(this.connection).getTime());
/*      */           
/* 1296 */           break;
/*      */         
/*      */         case -102: 
/* 1299 */           Calendar localCalendar = this.connection.getDbTzCalendar();
/*      */           
/* 1301 */           localDate = new Date(((TIMESTAMPLTZ)localDatum).timestampValue(this.connection, localCalendar == null ? paramCalendar : localCalendar).getTime());
/*      */           
/*      */ 
/* 1304 */           break;
/*      */         
/*      */         default: 
/* 1307 */           localDate = new Date(localDatum.timestampValue(paramCalendar).getTime());
/*      */         }
/*      */         
/*      */       }
/* 1311 */       return localDate;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Time getTime(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1320 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1323 */       Datum localDatum = getOracleObject(paramInt);
/* 1324 */       Time localTime = null;
/*      */       
/* 1326 */       if (localDatum != null)
/*      */       {
/* 1328 */         ResultSetMetaData localResultSetMetaData = getInternalMetadata();
/* 1329 */         switch (localResultSetMetaData.getColumnType(paramInt + this.beginColumnIndex))
/*      */         {
/*      */         case -101: 
/* 1332 */           localTime = new Time(((TIMESTAMPTZ)localDatum).timestampValue(this.connection).getTime());
/* 1333 */           break;
/*      */         
/*      */         case -102: 
/* 1336 */           Calendar localCalendar = this.connection.getDbTzCalendar();
/* 1337 */           localTime = new Time(((TIMESTAMPLTZ)localDatum).timestampValue(this.connection, localCalendar == null ? paramCalendar : localCalendar).getTime());
/*      */           
/* 1339 */           break;
/*      */         
/*      */         default: 
/* 1342 */           localTime = new Time(localDatum.timestampValue(paramCalendar).getTime());
/*      */         }
/*      */         
/*      */       }
/* 1346 */       return localTime;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1355 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1358 */       Datum localDatum = getOracleObject(paramInt);
/* 1359 */       Timestamp localTimestamp = null;
/*      */       
/* 1361 */       if (localDatum != null)
/*      */       {
/* 1363 */         ResultSetMetaData localResultSetMetaData = getInternalMetadata();
/* 1364 */         switch (localResultSetMetaData.getColumnType(paramInt + this.beginColumnIndex))
/*      */         {
/*      */         case -101: 
/* 1367 */           localTimestamp = ((TIMESTAMPTZ)localDatum).timestampValue(this.connection);
/* 1368 */           break;
/*      */         
/*      */         case -102: 
/* 1371 */           Calendar localCalendar = this.connection.getDbTzCalendar();
/* 1372 */           localTimestamp = ((TIMESTAMPLTZ)localDatum).timestampValue(this.connection, localCalendar == null ? paramCalendar : localCalendar);
/*      */           
/* 1374 */           break;
/*      */         
/*      */         default: 
/* 1377 */           localTimestamp = localDatum.timestampValue(paramCalendar);
/*      */         }
/*      */         
/*      */       }
/* 1381 */       return localTimestamp;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public URL getURL(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1389 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1392 */       URL localURL = null;
/*      */       
/* 1394 */       int i = getInternalMetadata().getColumnType(paramInt + this.beginColumnIndex);
/* 1395 */       int j = SQLUtil.getInternalType(i);
/*      */       
/*      */ 
/* 1398 */       if ((j == 96) || (j == 1) || (j == 8))
/*      */       {
/*      */ 
/*      */         try
/*      */         {
/* 1403 */           String str = getString(paramInt);
/* 1404 */           if (str == null) localURL = null; else {
/* 1405 */             localURL = new URL(str);
/*      */           }
/*      */         }
/*      */         catch (MalformedURLException localMalformedURLException)
/*      */         {
/* 1410 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 136);
/* 1411 */           localSQLException2.fillInStackTrace();
/* 1412 */           throw localSQLException2;
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1419 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Conversion to java.net.URL not supported.");
/* 1420 */         localSQLException1.fillInStackTrace();
/* 1421 */         throw localSQLException1;
/*      */       }
/*      */       
/*      */ 
/* 1425 */       return localURL;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public ResultSet getCursor(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1433 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 1437 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getCursor");
/* 1438 */       localSQLException.fillInStackTrace();
/* 1439 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ROWID getROWID(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1449 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1452 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1454 */       if (localDatum != null)
/*      */       {
/* 1456 */         if ((localDatum instanceof ROWID)) {
/* 1457 */           return (ROWID)localDatum;
/*      */         }
/*      */         
/* 1460 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getROWID");
/* 1461 */         localSQLException.fillInStackTrace();
/* 1462 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1466 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public NUMBER getNUMBER(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1474 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1477 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1479 */       if (localDatum != null)
/*      */       {
/* 1481 */         if ((localDatum instanceof NUMBER)) {
/* 1482 */           return (NUMBER)localDatum;
/*      */         }
/*      */         
/* 1485 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getNUMBER");
/* 1486 */         localSQLException.fillInStackTrace();
/* 1487 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1491 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public DATE getDATE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1499 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1502 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1504 */       if (localDatum != null)
/*      */       {
/* 1506 */         if ((localDatum instanceof DATE))
/* 1507 */           return (DATE)localDatum;
/* 1508 */         if ((localDatum instanceof TIMESTAMP))
/* 1509 */           return TIMESTAMP.toDATE(localDatum.getBytes());
/* 1510 */         if ((localDatum instanceof TIMESTAMPLTZ))
/* 1511 */           return TIMESTAMPLTZ.toDATE(this.connection, localDatum.getBytes());
/* 1512 */         if ((localDatum instanceof TIMESTAMPTZ)) {
/* 1513 */           return TIMESTAMPTZ.toDATE(this.connection, localDatum.getBytes());
/*      */         }
/*      */         
/* 1516 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getDATE");
/* 1517 */         localSQLException.fillInStackTrace();
/* 1518 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1522 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public TIMESTAMP getTIMESTAMP(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1530 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1533 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1535 */       if (localDatum != null)
/*      */       {
/* 1537 */         if ((localDatum instanceof TIMESTAMP))
/* 1538 */           return (TIMESTAMP)localDatum;
/* 1539 */         if ((localDatum instanceof TIMESTAMPLTZ))
/* 1540 */           return TIMESTAMPLTZ.toTIMESTAMP(this.connection, localDatum.getBytes());
/* 1541 */         if ((localDatum instanceof TIMESTAMPTZ))
/* 1542 */           return TIMESTAMPTZ.toTIMESTAMP(this.connection, localDatum.getBytes());
/* 1543 */         if ((localDatum instanceof DATE)) {
/* 1544 */           return new TIMESTAMP((DATE)localDatum);
/*      */         }
/*      */         
/* 1547 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getTIMESTAMP");
/* 1548 */         localSQLException.fillInStackTrace();
/* 1549 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1553 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public TIMESTAMPTZ getTIMESTAMPTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1561 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1564 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1566 */       if (localDatum != null)
/*      */       {
/* 1568 */         if ((localDatum instanceof TIMESTAMPTZ))
/* 1569 */           return (TIMESTAMPTZ)localDatum;
/* 1570 */         if ((localDatum instanceof TIMESTAMPLTZ)) {
/* 1571 */           return TIMESTAMPLTZ.toTIMESTAMPTZ(this.connection, localDatum.getBytes());
/*      */         }
/*      */         
/* 1574 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getTIMESTAMPTZ");
/* 1575 */         localSQLException.fillInStackTrace();
/* 1576 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1580 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public TIMESTAMPLTZ getTIMESTAMPLTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1588 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1591 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1593 */       if (localDatum != null)
/*      */       {
/* 1595 */         if ((localDatum instanceof TIMESTAMPLTZ)) {
/* 1596 */           return (TIMESTAMPLTZ)localDatum;
/*      */         }
/*      */         
/* 1599 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getTIMESTAMPLTZ");
/* 1600 */         localSQLException.fillInStackTrace();
/* 1601 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1605 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public INTERVALDS getINTERVALDS(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1613 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1616 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1618 */       if (localDatum != null)
/*      */       {
/* 1620 */         if ((localDatum instanceof INTERVALDS)) {
/* 1621 */           return (INTERVALDS)localDatum;
/*      */         }
/*      */         
/* 1624 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getINTERVALDS");
/* 1625 */         localSQLException.fillInStackTrace();
/* 1626 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1630 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public INTERVALYM getINTERVALYM(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1638 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1641 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1643 */       if (localDatum != null)
/*      */       {
/* 1645 */         if ((localDatum instanceof INTERVALYM)) {
/* 1646 */           return (INTERVALYM)localDatum;
/*      */         }
/*      */         
/* 1649 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getINTERVALYM");
/* 1650 */         localSQLException.fillInStackTrace();
/* 1651 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1655 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public ARRAY getARRAY(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1663 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1666 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1668 */       if (localDatum != null)
/*      */       {
/* 1670 */         if ((localDatum instanceof ARRAY)) {
/* 1671 */           return (ARRAY)localDatum;
/*      */         }
/*      */         
/* 1674 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getARRAY");
/* 1675 */         localSQLException.fillInStackTrace();
/* 1676 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1680 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public STRUCT getSTRUCT(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1688 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1691 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1693 */       if (localDatum != null)
/*      */       {
/* 1695 */         if ((localDatum instanceof STRUCT)) {
/* 1696 */           return (STRUCT)localDatum;
/*      */         }
/*      */         
/* 1699 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getSTRUCT");
/* 1700 */         localSQLException.fillInStackTrace();
/* 1701 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1705 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public OPAQUE getOPAQUE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1713 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1716 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1718 */       if (localDatum != null)
/*      */       {
/* 1720 */         if ((localDatum instanceof OPAQUE)) {
/* 1721 */           return (OPAQUE)localDatum;
/*      */         }
/*      */         
/* 1724 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getOPAQUE");
/* 1725 */         localSQLException.fillInStackTrace();
/* 1726 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1730 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public REF getREF(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1738 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1741 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1743 */       if (localDatum != null)
/*      */       {
/* 1745 */         if ((localDatum instanceof REF)) {
/* 1746 */           return (REF)localDatum;
/*      */         }
/*      */         
/* 1749 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getREF");
/* 1750 */         localSQLException.fillInStackTrace();
/* 1751 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1755 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public CHAR getCHAR(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1763 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1766 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1768 */       if (localDatum != null)
/*      */       {
/* 1770 */         if ((localDatum instanceof CHAR)) {
/* 1771 */           return (CHAR)localDatum;
/*      */         }
/*      */         
/* 1774 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getCHAR");
/* 1775 */         localSQLException.fillInStackTrace();
/* 1776 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1780 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public RAW getRAW(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1788 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1791 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1793 */       if (localDatum != null)
/*      */       {
/* 1795 */         if ((localDatum instanceof RAW)) {
/* 1796 */           return (RAW)localDatum;
/*      */         }
/*      */         
/* 1799 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getRAW");
/* 1800 */         localSQLException.fillInStackTrace();
/* 1801 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1805 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public BLOB getBLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1813 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1816 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1818 */       if (localDatum != null)
/*      */       {
/* 1820 */         if ((localDatum instanceof BLOB)) {
/* 1821 */           return (BLOB)localDatum;
/*      */         }
/*      */         
/* 1824 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getBLOB");
/* 1825 */         localSQLException.fillInStackTrace();
/* 1826 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1830 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public CLOB getCLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1838 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1841 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1843 */       if (localDatum != null)
/*      */       {
/* 1845 */         if ((localDatum instanceof CLOB)) {
/* 1846 */           return (CLOB)localDatum;
/*      */         }
/*      */         
/* 1849 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getCLOB");
/* 1850 */         localSQLException.fillInStackTrace();
/* 1851 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1855 */       return null;
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
/* 1866 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1869 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1871 */       if (localDatum != null)
/*      */       {
/* 1873 */         if ((localDatum instanceof NCLOB)) {
/* 1874 */           return (NCLOB)localDatum;
/*      */         }
/*      */         
/* 1877 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getCLOB");
/* 1878 */         localSQLException.fillInStackTrace();
/* 1879 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1883 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BFILE getBFILE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1892 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1895 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1897 */       if (localDatum != null)
/*      */       {
/* 1899 */         if ((localDatum instanceof BFILE)) {
/* 1900 */           return (BFILE)localDatum;
/*      */         }
/*      */         
/* 1903 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getBFILE");
/* 1904 */         localSQLException.fillInStackTrace();
/* 1905 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1909 */       return null;
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
/*      */   public CustomDatum getCustomDatum(int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */     throws SQLException
/*      */   {
/* 1930 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1933 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1938 */       return paramCustomDatumFactory.create(localDatum, 0);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ORAData getORAData(int paramInt, ORADataFactory paramORADataFactory)
/*      */     throws SQLException
/*      */   {
/* 1947 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1950 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1955 */       return paramORADataFactory.create(localDatum, 0);
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
/*      */   public NClob getNClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1983 */     NCLOB localNCLOB = getNCLOB(paramInt);
/*      */     
/* 1985 */     if (localNCLOB == null) { return null;
/*      */     }
/* 1987 */     if (!(localNCLOB instanceof NClob))
/*      */     {
/* 1989 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 184);
/* 1990 */       localSQLException.fillInStackTrace();
/* 1991 */       throw localSQLException;
/*      */     }
/*      */     
/* 1994 */     return localNCLOB;
/*      */   }
/*      */   
/*      */ 
/*      */   public String getNString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2001 */     return getString(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */   public Reader getNCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2008 */     return getCharacterStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */   public RowId getRowId(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2015 */     return getROWID(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */   public SQLXML getSQLXML(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2022 */     Datum localDatum = getOracleObject(paramInt);
/*      */     
/* 2024 */     if (localDatum != null)
/*      */     {
/* 2026 */       if ((localDatum instanceof SQLXML)) {
/* 2027 */         return (SQLXML)localDatum;
/*      */       }
/*      */       
/* 2030 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getSQLXML");
/* 2031 */       localSQLException.fillInStackTrace();
/* 2032 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 2036 */     return null;
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
/*      */   public int findColumn(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2060 */     synchronized (this.connection)
/*      */     {
/* 2062 */       if (this.closed)
/*      */       {
/* 2064 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/* 2065 */         localSQLException.fillInStackTrace();
/* 2066 */         throw localSQLException;
/*      */       }
/* 2068 */       return this.resultSet.findColumn(paramString) - this.beginColumnIndex;
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
/* 2079 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 2082 */       if (paramInt == 1000)
/*      */       {
/* 2084 */         this.usrFetchDirection = paramInt;
/*      */       }
/* 2086 */       else if ((paramInt == 1001) || (paramInt == 1002))
/*      */       {
/*      */ 
/* 2089 */         this.usrFetchDirection = paramInt;
/*      */         
/* 2091 */         this.sqlWarning = DatabaseError.addSqlWarning(this.sqlWarning, 87);
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 2098 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "setFetchDirection");
/* 2099 */         localSQLException.fillInStackTrace();
/* 2100 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getFetchDirection()
/*      */     throws SQLException
/*      */   {
/* 2112 */     return 1000;
/*      */   }
/*      */   
/*      */   public void setFetchSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2118 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 2121 */       this.resultSet.setFetchSize(paramInt);
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
/*      */   public int getType()
/*      */     throws SQLException
/*      */   {
/* 2140 */     return this.rsetType;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getConcurrency()
/*      */     throws SQLException
/*      */   {
/* 2148 */     return this.rsetConcurency;
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
/*      */   public void refreshRow()
/*      */     throws SQLException
/*      */   {
/* 2163 */     if (!needIdentifier(this.rsetType, this.rsetConcurency))
/*      */     {
/* 2165 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23, "refreshRow");
/* 2166 */       localSQLException1.fillInStackTrace();
/* 2167 */       throw localSQLException1;
/*      */     }
/*      */     
/* 2170 */     if (isValidRow(this.currentRow))
/*      */     {
/* 2172 */       int i = getFetchDirection();
/*      */       
/*      */       try
/*      */       {
/* 2176 */         refreshRowsInCache(this.currentRow, getFetchSize(), i);
/*      */ 
/*      */       }
/*      */       catch (SQLException localSQLException3)
/*      */       {
/* 2181 */         SQLException localSQLException4 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localSQLException3, 90, "Unsupported syntax for refreshRow()");
/* 2182 */         localSQLException4.fillInStackTrace();
/* 2183 */         throw localSQLException4;
/*      */       }
/*      */       
/*      */     }
/*      */     else
/*      */     {
/* 2189 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 82, "refreshRow");
/* 2190 */       localSQLException2.fillInStackTrace();
/* 2191 */       throw localSQLException2;
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
/*      */   private boolean isEmptyResultSet()
/*      */     throws SQLException
/*      */   {
/* 2207 */     if (this.numRowsCached != 0)
/*      */     {
/* 2209 */       return false;
/*      */     }
/* 2211 */     if ((this.numRowsCached == 0) && (this.allRowsCached))
/*      */     {
/* 2213 */       return true;
/*      */     }
/*      */     
/*      */ 
/* 2217 */     return !isValidRow(1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean isValidRow(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2230 */     if ((paramInt > 0) && (paramInt <= this.numRowsCached))
/*      */     {
/* 2232 */       return true;
/*      */     }
/* 2234 */     if (paramInt <= 0)
/*      */     {
/* 2236 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2240 */     return cacheRowAt(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void cacheCurrentRow(OracleResultSetImpl paramOracleResultSetImpl, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2251 */     for (int i = 0; i < getColumnCount(); i++)
/*      */     {
/*      */ 
/* 2254 */       byte[] arrayOfByte = paramOracleResultSetImpl.privateGetBytes(i + 1);
/* 2255 */       OracleResultSet.AuthorizationIndicator localAuthorizationIndicator = paramOracleResultSetImpl.getAuthorizationIndicator(i + 1);
/*      */       
/* 2257 */       CachedRowElement localCachedRowElement = new CachedRowElement(paramInt, i + 1, localAuthorizationIndicator, arrayOfByte);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 2262 */       int j = paramOracleResultSetImpl.statement.accessors[i].internalType;
/*      */       Object localObject;
/* 2264 */       if (j == 112)
/*      */       {
/* 2266 */         localObject = paramOracleResultSetImpl.getCLOB(i + 1);
/* 2267 */         localCachedRowElement.setData((Datum)localObject);
/*      */       }
/* 2269 */       else if (j == 113)
/*      */       {
/* 2271 */         localObject = paramOracleResultSetImpl.getBLOB(i + 1);
/* 2272 */         localCachedRowElement.setData((Datum)localObject);
/*      */       }
/*      */       
/* 2275 */       putCachedValueAt(paramInt, i + 1, localCachedRowElement);
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
/*      */   private boolean cacheRowAt(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2291 */     while ((this.numRowsCached < paramInt) && (this.resultSet.next())) {
/* 2292 */       cacheCurrentRow(this.resultSet, ++this.numRowsCached);
/*      */     }
/* 2294 */     if (this.numRowsCached < paramInt)
/*      */     {
/* 2296 */       this.allRowsCached = true;
/*      */       
/* 2298 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 2302 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int cacheAllRows()
/*      */     throws SQLException
/*      */   {
/* 2314 */     while (this.resultSet.next()) {
/* 2315 */       cacheCurrentRow(this.resultSet, ++this.numRowsCached);
/*      */     }
/* 2317 */     this.allRowsCached = true;
/*      */     
/* 2319 */     return this.numRowsCached;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   int getColumnCount()
/*      */     throws SQLException
/*      */   {
/* 2330 */     if (this.columnCount == 0)
/*      */     {
/*      */ 
/*      */ 
/* 2334 */       int i = this.resultSet.statement.numberOfDefinePositions;
/*      */       
/* 2336 */       if ((this.resultSet.statement.accessors != null) && (i > 0)) {
/* 2337 */         this.columnCount = i;
/*      */       } else {
/* 2339 */         this.columnCount = getInternalMetadata().getColumnCount();
/*      */       }
/*      */     }
/* 2342 */     return this.columnCount;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private ResultSetMetaData getInternalMetadata()
/*      */     throws SQLException
/*      */   {
/* 2353 */     if (this.metadata == null) {
/* 2354 */       this.metadata = this.resultSet.getMetaData();
/*      */     }
/* 2356 */     return this.metadata;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int getLastRow()
/*      */     throws SQLException
/*      */   {
/* 2367 */     if (!this.allRowsCached) {
/* 2368 */       cacheAllRows();
/*      */     }
/* 2370 */     return this.numRowsCached;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int get_refetch_size(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 2383 */     int i = paramInt3 == 1001 ? -1 : 1;
/*      */     
/*      */ 
/* 2386 */     int j = 0;
/*      */     
/* 2388 */     if (this.refetchRowids == null) {
/* 2389 */       this.refetchRowids = new Vector(10);
/*      */     } else {
/* 2391 */       this.refetchRowids.removeAllElements();
/*      */     }
/*      */     
/* 2394 */     while ((j < paramInt2) && (isValidRow(paramInt1 + j * i)))
/*      */     {
/* 2396 */       this.refetchRowids.addElement(getCachedDatumValueAt(paramInt1 + j * i, 1));
/*      */       
/*      */ 
/* 2399 */       j++;
/*      */     }
/*      */     
/* 2402 */     return j;
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
/*      */   private OraclePreparedStatement prepare_refetch_statement(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2419 */     if (paramInt < 1)
/*      */     {
/* 2421 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 2422 */       ((SQLException)localObject).fillInStackTrace();
/* 2423 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2426 */     Object localObject = this.connection.prepareStatement(((OracleStatement)this.scrollStmt).sqlObject.getRefetchSqlForScrollableResultSet(this, paramInt));
/*      */     
/*      */ 
/* 2429 */     return (OraclePreparedStatement)((OraclePreparedStatementWrapper)localObject).preparedStatement;
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
/*      */   private void prepare_refetch_binds(OraclePreparedStatement paramOraclePreparedStatement, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2443 */     int i = this.scrollStmt.copyBinds(paramOraclePreparedStatement, 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2463 */     for (int j = 0; j < paramInt; j++)
/*      */     {
/* 2465 */       paramOraclePreparedStatement.setROWID(i + j + 1, (ROWID)this.refetchRowids.elementAt(j));
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
/*      */   private void save_refetch_results(OracleResultSetImpl paramOracleResultSetImpl, int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 2480 */     int i = paramInt3 == 1001 ? -1 : 1;
/*      */     
/* 2482 */     while (paramOracleResultSetImpl.next())
/*      */     {
/* 2484 */       ROWID localROWID = paramOracleResultSetImpl.getROWID(1);
/*      */       
/* 2486 */       int j = 0;
/* 2487 */       int k = paramInt1;
/*      */       
/* 2489 */       while ((j == 0) && (k < paramInt1 + paramInt2 * i))
/*      */       {
/* 2491 */         if (((ROWID)getCachedDatumValueAt(k, 1)).stringValue(this.connection).equals(localROWID.stringValue(this.connection)))
/*      */         {
/* 2493 */           j = 1;
/*      */         } else {
/* 2495 */           k += i;
/*      */         }
/*      */       }
/* 2498 */       if (j != 0) {
/* 2499 */         cacheCurrentRow(paramOracleResultSetImpl, k);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private Datum getCachedDatumValueAt(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2508 */     CachedRowElement localCachedRowElement = null;
/*      */     Object localObject;
/*      */     try
/*      */     {
/* 2512 */       localCachedRowElement = (CachedRowElement)this.rsetCache.get(paramInt1, paramInt2);
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2517 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2518 */       ((SQLException)localObject).fillInStackTrace();
/* 2519 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2523 */     Datum localDatum = null;
/*      */     
/* 2525 */     if (localCachedRowElement != null)
/*      */     {
/* 2527 */       localDatum = localCachedRowElement.getDataAsDatum();
/* 2528 */       localObject = localCachedRowElement.getData();
/*      */       
/* 2530 */       if ((localDatum == null) && (localObject != null) && (localObject.length > 0))
/*      */       {
/*      */ 
/* 2533 */         int i = getInternalMetadata().getColumnType(paramInt2);
/* 2534 */         int j = getInternalMetadata().getColumnDisplaySize(paramInt2);
/*      */         
/* 2536 */         int k = ((OracleResultSetMetaData)getInternalMetadata()).getDescription()[(paramInt2 - 1)].internalType;
/*      */         
/*      */ 
/*      */ 
/* 2540 */         int m = this.scrollStmt.getMaxFieldSize();
/*      */         
/* 2542 */         if ((m > 0) && (m < j)) {
/* 2543 */           j = m;
/*      */         }
/* 2545 */         String str = null;
/*      */         
/* 2547 */         if ((i == 2006) || (i == 2002) || (i == 2008) || (i == 2007) || (i == 2003) || (i == 2009))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2554 */           str = getInternalMetadata().getColumnTypeName(paramInt2);
/*      */         }
/*      */         
/* 2557 */         short s = this.resultSet.statement.accessors[(paramInt2 - 1)].formOfUse;
/*      */         
/* 2559 */         if ((s == 2) && ((k == 96) || (k == 1) || (k == 8) || (k == 112)))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2568 */           localDatum = SQLUtil.makeNDatum(this.connection, (byte[])localObject, k, str, s, j);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 2574 */           localDatum = SQLUtil.makeDatum(this.connection, (byte[])localObject, k, str, j);
/*      */         }
/*      */         
/*      */ 
/* 2578 */         localCachedRowElement.setData(localDatum);
/*      */       }
/*      */     }
/*      */     
/* 2582 */     return localDatum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void putCachedValueAt(int paramInt1, int paramInt2, CachedRowElement paramCachedRowElement)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 2593 */       this.rsetCache.put(paramInt1, paramInt2, paramCachedRowElement);
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2598 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2599 */       localSQLException.fillInStackTrace();
/* 2600 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void removeCachedRowAt(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 2612 */       this.rsetCache.remove(paramInt);
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 2617 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 2618 */       localSQLException.fillInStackTrace();
/* 2619 */       throw localSQLException;
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
/*      */   public static boolean needIdentifier(int paramInt1, int paramInt2)
/*      */   {
/* 2632 */     if (((paramInt1 == 1003) && (paramInt2 == 1007)) || ((paramInt1 == 1004) && (paramInt2 == 1007)))
/*      */     {
/*      */ 
/* 2635 */       return false;
/*      */     }
/* 2637 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean needCache(int paramInt1, int paramInt2)
/*      */   {
/* 2646 */     if ((paramInt1 == 1003) || ((paramInt1 == 1004) && (paramInt2 == 1007)))
/*      */     {
/*      */ 
/* 2649 */       return false;
/*      */     }
/* 2651 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean supportRefreshRow(int paramInt1, int paramInt2)
/*      */   {
/* 2660 */     if ((paramInt1 == 1003) || ((paramInt1 == 1004) && (paramInt2 == 1007)))
/*      */     {
/*      */ 
/* 2663 */       return false;
/*      */     }
/* 2665 */     return true;
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
/*      */   int getFirstUserColumnIndex()
/*      */   {
/* 2684 */     return this.beginColumnIndex;
/*      */   }
/*      */   
/*      */   public String getCursorName()
/*      */     throws SQLException
/*      */   {
/* 2690 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 2693 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23, "getCursorName");
/* 2694 */       localSQLException.fillInStackTrace();
/* 2695 */       throw localSQLException;
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
/* 2712 */     return this.connection;
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
/* 2723 */     return this.resultSet == null ? null : this.resultSet.getOracleStatement();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 2728 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */   
/*      */   /* Error */
/*      */   public java.sql.Statement getStatement()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 4	oracle/jdbc/driver/ScrollableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 7	oracle/jdbc/driver/ScrollableResultSet:scrollStmt	Loracle/jdbc/driver/ScrollRsetStatement;
/*      */     //   11: checkcast 39	java/sql/Statement
/*      */     //   14: aload_1
/*      */     //   15: monitorexit
/*      */     //   16: areturn
/*      */     //   17: astore_2
/*      */     //   18: aload_1
/*      */     //   19: monitorexit
/*      */     //   20: aload_2
/*      */     //   21: athrow
/*      */     // Line number table:
/*      */     //   Java source line #190	-> byte code offset #0
/*      */     //   Java source line #193	-> byte code offset #7
/*      */     //   Java source line #195	-> byte code offset #17
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	22	0	this	ScrollableResultSet
/*      */     //   5	14	1	Ljava/lang/Object;	Object
/*      */     //   17	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	16	17	finally
/*      */     //   17	20	17	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   ResultSet getResultSet()
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 4	oracle/jdbc/driver/ScrollableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 5	oracle/jdbc/driver/ScrollableResultSet:resultSet	Loracle/jdbc/driver/OracleResultSetImpl;
/*      */     //   11: aload_1
/*      */     //   12: monitorexit
/*      */     //   13: areturn
/*      */     //   14: astore_2
/*      */     //   15: aload_1
/*      */     //   16: monitorexit
/*      */     //   17: aload_2
/*      */     //   18: athrow
/*      */     // Line number table:
/*      */     //   Java source line #216	-> byte code offset #0
/*      */     //   Java source line #219	-> byte code offset #7
/*      */     //   Java source line #221	-> byte code offset #14
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	19	0	this	ScrollableResultSet
/*      */     //   5	11	1	Ljava/lang/Object;	Object
/*      */     //   14	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	13	14	finally
/*      */     //   14	17	14	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public java.sql.Ref getRef(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 4	oracle/jdbc/driver/ScrollableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 132	oracle/jdbc/driver/ScrollableResultSet:getREF	(I)Loracle/sql/REF;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1236	-> byte code offset #0
/*      */     //   Java source line #1239	-> byte code offset #7
/*      */     //   Java source line #1241	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	ScrollableResultSet
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public java.sql.Blob getBlob(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 4	oracle/jdbc/driver/ScrollableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 133	oracle/jdbc/driver/ScrollableResultSet:getBLOB	(I)Loracle/sql/BLOB;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1247	-> byte code offset #0
/*      */     //   Java source line #1250	-> byte code offset #7
/*      */     //   Java source line #1252	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	ScrollableResultSet
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public java.sql.Clob getClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 4	oracle/jdbc/driver/ScrollableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 134	oracle/jdbc/driver/ScrollableResultSet:getCLOB	(I)Loracle/sql/CLOB;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1258	-> byte code offset #0
/*      */     //   Java source line #1261	-> byte code offset #7
/*      */     //   Java source line #1263	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	ScrollableResultSet
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public java.sql.Array getArray(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 4	oracle/jdbc/driver/ScrollableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 135	oracle/jdbc/driver/ScrollableResultSet:getARRAY	(I)Loracle/sql/ARRAY;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1270	-> byte code offset #0
/*      */     //   Java source line #1273	-> byte code offset #7
/*      */     //   Java source line #1275	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	ScrollableResultSet
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public BFILE getBfile(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 4	oracle/jdbc/driver/ScrollableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 182	oracle/jdbc/driver/ScrollableResultSet:getBFILE	(I)Loracle/sql/BFILE;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1918	-> byte code offset #0
/*      */     //   Java source line #1921	-> byte code offset #7
/*      */     //   Java source line #1923	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	ScrollableResultSet
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public Object getObject(int paramInt, oracle.jdbc.OracleDataFactory paramOracleDataFactory)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 4	oracle/jdbc/driver/ScrollableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_3
/*      */     //   6: monitorenter
/*      */     //   7: aload_2
/*      */     //   8: aload_0
/*      */     //   9: iload_1
/*      */     //   10: invokevirtual 185	oracle/jdbc/driver/ScrollableResultSet:getObject	(I)Ljava/lang/Object;
/*      */     //   13: iconst_0
/*      */     //   14: invokeinterface 186 3 0
/*      */     //   19: aload_3
/*      */     //   20: monitorexit
/*      */     //   21: areturn
/*      */     //   22: astore 4
/*      */     //   24: aload_3
/*      */     //   25: monitorexit
/*      */     //   26: aload 4
/*      */     //   28: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1963	-> byte code offset #0
/*      */     //   Java source line #1969	-> byte code offset #7
/*      */     //   Java source line #1971	-> byte code offset #22
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	29	0	this	ScrollableResultSet
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
/*      */   /* Error */
/*      */   public ResultSetMetaData getMetaData()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 4	oracle/jdbc/driver/ScrollableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: new 193	oracle/jdbc/driver/OracleResultSetMetaData
/*      */     //   10: dup
/*      */     //   11: aload_0
/*      */     //   12: getfield 4	oracle/jdbc/driver/ScrollableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   15: aload_0
/*      */     //   16: getfield 7	oracle/jdbc/driver/ScrollableResultSet:scrollStmt	Loracle/jdbc/driver/ScrollRsetStatement;
/*      */     //   19: checkcast 2	oracle/jdbc/driver/OracleStatement
/*      */     //   22: aload_0
/*      */     //   23: getfield 11	oracle/jdbc/driver/ScrollableResultSet:beginColumnIndex	I
/*      */     //   26: invokespecial 194	oracle/jdbc/driver/OracleResultSetMetaData:<init>	(Loracle/jdbc/driver/PhysicalConnection;Loracle/jdbc/driver/OracleStatement;I)V
/*      */     //   29: aload_1
/*      */     //   30: monitorexit
/*      */     //   31: areturn
/*      */     //   32: astore_2
/*      */     //   33: aload_1
/*      */     //   34: monitorexit
/*      */     //   35: aload_2
/*      */     //   36: athrow
/*      */     // Line number table:
/*      */     //   Java source line #2048	-> byte code offset #0
/*      */     //   Java source line #2051	-> byte code offset #7
/*      */     //   Java source line #2055	-> byte code offset #32
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	37	0	this	ScrollableResultSet
/*      */     //   5	29	1	Ljava/lang/Object;	Object
/*      */     //   32	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	31	32	finally
/*      */     //   32	35	32	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public int getFetchSize()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 4	oracle/jdbc/driver/ScrollableResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 5	oracle/jdbc/driver/ScrollableResultSet:resultSet	Loracle/jdbc/driver/OracleResultSetImpl;
/*      */     //   11: invokevirtual 200	oracle/jdbc/driver/OracleResultSetImpl:getFetchSize	()I
/*      */     //   14: aload_1
/*      */     //   15: monitorexit
/*      */     //   16: ireturn
/*      */     //   17: astore_2
/*      */     //   18: aload_1
/*      */     //   19: monitorexit
/*      */     //   20: aload_2
/*      */     //   21: athrow
/*      */     // Line number table:
/*      */     //   Java source line #2128	-> byte code offset #0
/*      */     //   Java source line #2131	-> byte code offset #7
/*      */     //   Java source line #2133	-> byte code offset #17
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	22	0	this	ScrollableResultSet
/*      */     //   5	14	1	Ljava/lang/Object;	Object
/*      */     //   17	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	16	17	finally
/*      */     //   17	20	17	finally
/*      */   }
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/ScrollableResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */