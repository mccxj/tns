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
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLXML;
/*      */ import java.sql.Statement;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.Map;
/*      */ import oracle.jdbc.OracleDataFactory;
/*      */ import oracle.jdbc.OracleResultSet.AuthorizationIndicator;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.internal.OracleStatement.SqlKind;
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
/*      */ class OracleResultSetImpl
/*      */   extends BaseResultSet
/*      */ {
/*      */   PhysicalConnection connection;
/*      */   OracleStatement statement;
/*      */   boolean explicitly_closed;
/*      */   boolean m_emptyRset;
/*   64 */   boolean isServerCursorPeeked = false;
/*      */   
/*      */ 
/*      */   OracleResultSetImpl(PhysicalConnection paramPhysicalConnection, OracleStatement paramOracleStatement)
/*      */     throws SQLException
/*      */   {
/*   70 */     this.connection = paramPhysicalConnection;
/*   71 */     this.statement = paramOracleStatement;
/*   72 */     this.close_statement_on_close = false;
/*   73 */     this.explicitly_closed = false;
/*   74 */     this.m_emptyRset = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getCursorName()
/*      */     throws SQLException
/*      */   {
/*   84 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*   87 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23, "getCursorName");
/*   88 */       localSQLException.fillInStackTrace();
/*   89 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void close()
/*      */     throws SQLException
/*      */   {
/*  101 */     synchronized (this.connection)
/*      */     {
/*  103 */       internal_close(false);
/*  104 */       this.statement.totalRowsVisited = 0;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  111 */       this.statement.closeCursorOnPlainStatement();
/*      */       
/*      */ 
/*  114 */       if (this.close_statement_on_close)
/*      */       {
/*      */         try
/*      */         {
/*  118 */           this.statement.close();
/*      */         }
/*      */         catch (SQLException localSQLException) {}
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  125 */       this.explicitly_closed = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean wasNull()
/*      */     throws SQLException
/*      */   {
/*  132 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException;
/*  135 */       if (this.explicitly_closed)
/*      */       {
/*  137 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10, "wasNull");
/*  138 */         localSQLException.fillInStackTrace();
/*  139 */         throw localSQLException;
/*      */       }
/*      */       
/*  142 */       if (this.statement.closed)
/*      */       {
/*  144 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9, "wasNull");
/*  145 */         localSQLException.fillInStackTrace();
/*  146 */         throw localSQLException;
/*      */       }
/*      */       
/*  149 */       return this.statement.wasNullValue();
/*      */     }
/*      */   }
/*      */   
/*      */   public ResultSetMetaData getMetaData()
/*      */     throws SQLException
/*      */   {
/*  156 */     synchronized (this.connection) {
/*      */       SQLException localSQLException;
/*  158 */       if (this.explicitly_closed)
/*      */       {
/*  160 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10, "getMetaData");
/*  161 */         localSQLException.fillInStackTrace();
/*  162 */         throw localSQLException;
/*      */       }
/*      */       
/*  165 */       if (this.statement.closed)
/*      */       {
/*  167 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9, "getMetaData");
/*  168 */         localSQLException.fillInStackTrace();
/*  169 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  175 */       if (!this.statement.isOpen)
/*      */       {
/*  177 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 144, "getMetaData");
/*  178 */         localSQLException.fillInStackTrace();
/*  179 */         throw localSQLException;
/*      */       }
/*      */       
/*  182 */       return new OracleResultSetMetaData(this.connection, this.statement);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Statement getStatement()
/*      */     throws SQLException
/*      */   {
/*  190 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/*  194 */       return this.statement.wrapper == null ? this.statement : this.statement.wrapper;
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   OracleStatement getOracleStatement()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	oracle/jdbc/driver/OracleResultSetImpl:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 4	oracle/jdbc/driver/OracleResultSetImpl:statement	Loracle/jdbc/driver/OracleStatement;
/*      */     //   11: aload_1
/*      */     //   12: monitorexit
/*      */     //   13: areturn
/*      */     //   14: astore_2
/*      */     //   15: aload_1
/*      */     //   16: monitorexit
/*      */     //   17: aload_2
/*      */     //   18: athrow
/*      */     // Line number table:
/*      */     //   Java source line #205	-> byte code offset #0
/*      */     //   Java source line #207	-> byte code offset #7
/*      */     //   Java source line #209	-> byte code offset #14
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	19	0	this	OracleResultSetImpl
/*      */     //   5	11	1	Ljava/lang/Object;	Object
/*      */     //   14	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	13	14	finally
/*      */     //   14	17	14	finally
/*      */   }
/*      */   
/*      */   public boolean next()
/*      */     throws SQLException
/*      */   {
/*  226 */     synchronized (this.connection)
/*      */     {
/*  228 */       boolean bool = true;
/*      */       
/*  230 */       this.isServerCursorPeeked = true;
/*      */       
/*      */ 
/*  233 */       PhysicalConnection localPhysicalConnection = this.statement.connection;
/*      */       SQLException localSQLException;
/*  235 */       if (this.explicitly_closed)
/*      */       {
/*  237 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10, "next");
/*  238 */         localSQLException.fillInStackTrace();
/*  239 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  244 */       if ((localPhysicalConnection == null) || (localPhysicalConnection.lifecycle != 1))
/*      */       {
/*  246 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8, "next");
/*  247 */         localSQLException.fillInStackTrace();
/*  248 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  253 */       if (this.statement.closed)
/*      */       {
/*  255 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9, "next");
/*  256 */         localSQLException.fillInStackTrace();
/*  257 */         throw localSQLException;
/*      */       }
/*      */       
/*  260 */       if (this.statement.sqlKind.isPlsqlOrCall())
/*      */       {
/*  262 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 166, "next");
/*  263 */         localSQLException.fillInStackTrace();
/*  264 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  269 */       if (this.closed) {
/*  270 */         return false;
/*      */       }
/*  272 */       this.statement.currentRow += 1;
/*  273 */       this.statement.totalRowsVisited += 1;
/*      */       
/*  275 */       if ((this.statement.maxRows != 0) && (this.statement.totalRowsVisited > this.statement.maxRows))
/*      */       {
/*      */ 
/*  278 */         internal_close(false);
/*  279 */         this.statement.currentRow -= 1;
/*  280 */         this.statement.totalRowsVisited = 0;
/*      */         
/*  282 */         this.sqlWarning = DatabaseError.addSqlWarning(this.sqlWarning, 275);
/*      */         
/*  284 */         return false;
/*      */       }
/*      */       
/*  287 */       if (this.statement.currentRow >= this.statement.validRows) {
/*  288 */         bool = close_or_fetch_from_next(false);
/*      */       }
/*  290 */       if ((bool) && (localPhysicalConnection.useFetchSizeWithLongColumn)) {
/*  291 */         this.statement.reopenStreams();
/*      */       }
/*  293 */       if (!bool)
/*      */       {
/*  295 */         this.statement.currentRow -= 1;
/*  296 */         this.statement.totalRowsVisited = 0;
/*      */       }
/*      */       
/*  299 */       return bool;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean close_or_fetch_from_next(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  310 */     if (paramBoolean)
/*      */     {
/*  312 */       internal_close(false);
/*      */       
/*  314 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  321 */     if (this.statement.gotLastBatch)
/*      */     {
/*  323 */       internal_close(false);
/*      */       
/*  325 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  332 */     this.statement.check_row_prefetch_changed();
/*      */     
/*  334 */     PhysicalConnection localPhysicalConnection = this.statement.connection;
/*      */     
/*  336 */     if (localPhysicalConnection.protocolId == 3) {
/*  337 */       this.sqlWarning = null;
/*      */     }
/*      */     else
/*      */     {
/*  341 */       if (this.statement.streamList != null)
/*      */       {
/*      */ 
/*      */ 
/*  345 */         while (this.statement.nextStream != null)
/*      */         {
/*      */           try
/*      */           {
/*  349 */             this.statement.nextStream.close();
/*      */ 
/*      */           }
/*      */           catch (IOException localIOException)
/*      */           {
/*  354 */             SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  355 */             localSQLException.fillInStackTrace();
/*  356 */             throw localSQLException;
/*      */           }
/*      */           
/*      */ 
/*  360 */           this.statement.nextStream = this.statement.nextStream.nextStream;
/*      */         }
/*      */       }
/*      */       
/*  364 */       clearWarnings();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  373 */       localPhysicalConnection.registerHeartbeat();
/*      */       
/*  375 */       localPhysicalConnection.needLine();
/*      */     }
/*      */     
/*      */ 
/*  379 */     synchronized (localPhysicalConnection)
/*      */     {
/*      */       try
/*      */       {
/*  383 */         this.statement.cancelLock.enterExecuting();
/*  384 */         this.statement.fetch();
/*      */       }
/*      */       finally {
/*  387 */         this.statement.cancelLock.exitExecuting();
/*      */       }
/*      */     }
/*      */     
/*  391 */     if (this.statement.validRows == 0)
/*      */     {
/*  393 */       internal_close(false);
/*      */       
/*  395 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  399 */     this.statement.currentRow = 0;
/*      */     
/*  401 */     this.statement.checkValidRowsStatus();
/*      */     
/*  403 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isBeforeFirst()
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*  415 */     if (this.explicitly_closed)
/*      */     {
/*  417 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  418 */       localSQLException.fillInStackTrace();
/*  419 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  423 */     if ((this.statement.connection.protocolId == 3) && (this.statement.serverCursor))
/*      */     {
/*  425 */       localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  426 */       localSQLException.fillInStackTrace();
/*  427 */       throw localSQLException;
/*      */     }
/*      */     
/*  430 */     return (!isEmptyResultSet()) && (this.statement.currentRow == -1) && (!this.closed);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isAfterLast()
/*      */     throws SQLException
/*      */   {
/*  437 */     if (this.explicitly_closed)
/*      */     {
/*  439 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  440 */       localSQLException.fillInStackTrace();
/*  441 */       throw localSQLException;
/*      */     }
/*      */     
/*  444 */     return (!isEmptyResultSet()) && (this.closed);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isFirst()
/*      */     throws SQLException
/*      */   {
/*  451 */     if (this.explicitly_closed)
/*      */     {
/*  453 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  454 */       localSQLException.fillInStackTrace();
/*  455 */       throw localSQLException;
/*      */     }
/*  457 */     return getRow() == 1;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isLast()
/*      */     throws SQLException
/*      */   {
/*  464 */     if (this.explicitly_closed)
/*      */     {
/*  466 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  467 */       localSQLException.fillInStackTrace();
/*  468 */       throw localSQLException;
/*      */     }
/*      */     
/*  471 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 75, "isLast");
/*  472 */     localSQLException.fillInStackTrace();
/*  473 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getRow()
/*      */     throws SQLException
/*      */   {
/*  481 */     if (this.explicitly_closed)
/*      */     {
/*  483 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/*  484 */       localSQLException.fillInStackTrace();
/*  485 */       throw localSQLException;
/*      */     }
/*  487 */     return this.statement.totalRowsVisited;
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public java.sql.Array getArray(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	oracle/jdbc/driver/OracleResultSetImpl:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 62	oracle/jdbc/driver/OracleResultSetImpl:getARRAY	(I)Loracle/sql/ARRAY;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #505	-> byte code offset #0
/*      */     //   Java source line #507	-> byte code offset #7
/*      */     //   Java source line #509	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	OracleResultSetImpl
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   public BigDecimal getBigDecimal(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  515 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/*  518 */       if (this.closed)
/*      */       {
/*  520 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/*  521 */         localSQLException1.fillInStackTrace();
/*  522 */         throw localSQLException1;
/*      */       }
/*      */       
/*  525 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/*  527 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  528 */         localSQLException1.fillInStackTrace();
/*  529 */         throw localSQLException1;
/*      */       }
/*      */       
/*  532 */       int i = this.statement.currentRow;
/*      */       
/*  534 */       if (i < 0)
/*      */       {
/*  536 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/*  537 */         localSQLException2.fillInStackTrace();
/*  538 */         throw localSQLException2;
/*      */       }
/*      */       
/*  541 */       this.statement.lastIndex = paramInt;
/*      */       
/*  543 */       if (this.statement.streamList != null)
/*      */       {
/*  545 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/*  548 */       return this.statement.accessors[(paramInt - 1)].getBigDecimal(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  556 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/*  559 */       if (this.closed)
/*      */       {
/*  561 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/*  562 */         localSQLException1.fillInStackTrace();
/*  563 */         throw localSQLException1;
/*      */       }
/*      */       
/*  566 */       if ((paramInt1 <= 0) || (paramInt1 > this.statement.numberOfDefinePositions))
/*      */       {
/*  568 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  569 */         localSQLException1.fillInStackTrace();
/*  570 */         throw localSQLException1;
/*      */       }
/*      */       
/*  573 */       int i = this.statement.currentRow;
/*      */       
/*  575 */       if (i < 0)
/*      */       {
/*  577 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/*  578 */         localSQLException2.fillInStackTrace();
/*  579 */         throw localSQLException2;
/*      */       }
/*      */       
/*  582 */       this.statement.lastIndex = paramInt1;
/*      */       
/*  584 */       if (this.statement.streamList != null)
/*      */       {
/*  586 */         this.statement.closeUsedStreams(paramInt1);
/*      */       }
/*      */       
/*  589 */       return this.statement.accessors[(paramInt1 - 1)].getBigDecimal(i, paramInt2);
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public java.sql.Blob getBlob(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	oracle/jdbc/driver/OracleResultSetImpl:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 69	oracle/jdbc/driver/OracleResultSetImpl:getBLOB	(I)Loracle/sql/BLOB;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #597	-> byte code offset #0
/*      */     //   Java source line #599	-> byte code offset #7
/*      */     //   Java source line #601	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	OracleResultSetImpl
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   public boolean getBoolean(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  607 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/*  610 */       if (this.closed)
/*      */       {
/*  612 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/*  613 */         localSQLException1.fillInStackTrace();
/*  614 */         throw localSQLException1;
/*      */       }
/*      */       
/*  617 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/*  619 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  620 */         localSQLException1.fillInStackTrace();
/*  621 */         throw localSQLException1;
/*      */       }
/*      */       
/*  624 */       int i = this.statement.currentRow;
/*      */       
/*  626 */       if (i < 0)
/*      */       {
/*  628 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/*  629 */         localSQLException2.fillInStackTrace();
/*  630 */         throw localSQLException2;
/*      */       }
/*      */       
/*  633 */       this.statement.lastIndex = paramInt;
/*      */       
/*  635 */       if (this.statement.streamList != null)
/*      */       {
/*  637 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/*  640 */       return this.statement.accessors[(paramInt - 1)].getBoolean(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public byte getByte(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  648 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/*  651 */       if (this.closed)
/*      */       {
/*  653 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/*  654 */         localSQLException1.fillInStackTrace();
/*  655 */         throw localSQLException1;
/*      */       }
/*      */       
/*  658 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/*  660 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  661 */         localSQLException1.fillInStackTrace();
/*  662 */         throw localSQLException1;
/*      */       }
/*      */       
/*  665 */       int i = this.statement.currentRow;
/*      */       
/*  667 */       if (i < 0)
/*      */       {
/*  669 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/*  670 */         localSQLException2.fillInStackTrace();
/*  671 */         throw localSQLException2;
/*      */       }
/*      */       
/*  674 */       this.statement.lastIndex = paramInt;
/*      */       
/*  676 */       if (this.statement.streamList != null)
/*      */       {
/*  678 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/*  681 */       return this.statement.accessors[(paramInt - 1)].getByte(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public byte[] getBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  689 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/*  692 */       if (this.closed)
/*      */       {
/*  694 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/*  695 */         localSQLException1.fillInStackTrace();
/*  696 */         throw localSQLException1;
/*      */       }
/*      */       
/*  699 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/*  701 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  702 */         localSQLException1.fillInStackTrace();
/*  703 */         throw localSQLException1;
/*      */       }
/*      */       
/*  706 */       int i = this.statement.currentRow;
/*      */       
/*  708 */       if (i < 0)
/*      */       {
/*  710 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/*  711 */         localSQLException2.fillInStackTrace();
/*  712 */         throw localSQLException2;
/*      */       }
/*      */       
/*  715 */       this.statement.lastIndex = paramInt;
/*      */       
/*  717 */       if (this.statement.streamList != null)
/*      */       {
/*  719 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/*  722 */       return this.statement.accessors[(paramInt - 1)].getBytes(i);
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public java.sql.Clob getClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	oracle/jdbc/driver/OracleResultSetImpl:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 73	oracle/jdbc/driver/OracleResultSetImpl:getCLOB	(I)Loracle/sql/CLOB;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #730	-> byte code offset #0
/*      */     //   Java source line #732	-> byte code offset #7
/*      */     //   Java source line #734	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	OracleResultSetImpl
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   public Date getDate(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  740 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/*  743 */       if (this.closed)
/*      */       {
/*  745 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/*  746 */         localSQLException1.fillInStackTrace();
/*  747 */         throw localSQLException1;
/*      */       }
/*      */       
/*  750 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/*  752 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  753 */         localSQLException1.fillInStackTrace();
/*  754 */         throw localSQLException1;
/*      */       }
/*      */       
/*  757 */       int i = this.statement.currentRow;
/*      */       
/*  759 */       if (i < 0)
/*      */       {
/*  761 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/*  762 */         localSQLException2.fillInStackTrace();
/*  763 */         throw localSQLException2;
/*      */       }
/*      */       
/*  766 */       this.statement.lastIndex = paramInt;
/*      */       
/*  768 */       if (this.statement.streamList != null)
/*      */       {
/*  770 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/*  773 */       return this.statement.accessors[(paramInt - 1)].getDate(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Date getDate(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/*  781 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/*  784 */       if (this.closed)
/*      */       {
/*  786 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/*  787 */         localSQLException1.fillInStackTrace();
/*  788 */         throw localSQLException1;
/*      */       }
/*      */       
/*  791 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/*  793 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  794 */         localSQLException1.fillInStackTrace();
/*  795 */         throw localSQLException1;
/*      */       }
/*      */       
/*  798 */       int i = this.statement.currentRow;
/*      */       
/*  800 */       if (i < 0)
/*      */       {
/*  802 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/*  803 */         localSQLException2.fillInStackTrace();
/*  804 */         throw localSQLException2;
/*      */       }
/*      */       
/*  807 */       this.statement.lastIndex = paramInt;
/*      */       
/*  809 */       if (this.statement.streamList != null)
/*      */       {
/*  811 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/*  814 */       return this.statement.accessors[(paramInt - 1)].getDate(i, paramCalendar);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public double getDouble(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  822 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/*  825 */       if (this.closed)
/*      */       {
/*  827 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/*  828 */         localSQLException1.fillInStackTrace();
/*  829 */         throw localSQLException1;
/*      */       }
/*      */       
/*  832 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/*  834 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  835 */         localSQLException1.fillInStackTrace();
/*  836 */         throw localSQLException1;
/*      */       }
/*      */       
/*  839 */       int i = this.statement.currentRow;
/*      */       
/*  841 */       if (i < 0)
/*      */       {
/*  843 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/*  844 */         localSQLException2.fillInStackTrace();
/*  845 */         throw localSQLException2;
/*      */       }
/*      */       
/*  848 */       this.statement.lastIndex = paramInt;
/*      */       
/*  850 */       if (this.statement.streamList != null)
/*      */       {
/*  852 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/*  855 */       return this.statement.accessors[(paramInt - 1)].getDouble(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public float getFloat(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  863 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/*  866 */       if (this.closed)
/*      */       {
/*  868 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/*  869 */         localSQLException1.fillInStackTrace();
/*  870 */         throw localSQLException1;
/*      */       }
/*      */       
/*  873 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/*  875 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  876 */         localSQLException1.fillInStackTrace();
/*  877 */         throw localSQLException1;
/*      */       }
/*      */       
/*  880 */       int i = this.statement.currentRow;
/*      */       
/*  882 */       if (i < 0)
/*      */       {
/*  884 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/*  885 */         localSQLException2.fillInStackTrace();
/*  886 */         throw localSQLException2;
/*      */       }
/*      */       
/*  889 */       this.statement.lastIndex = paramInt;
/*      */       
/*  891 */       if (this.statement.streamList != null)
/*      */       {
/*  893 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/*  896 */       return this.statement.accessors[(paramInt - 1)].getFloat(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getInt(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  909 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/*  912 */       if (this.closed)
/*      */       {
/*  914 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/*  915 */         localSQLException1.fillInStackTrace();
/*  916 */         throw localSQLException1;
/*      */       }
/*      */       
/*  919 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/*  921 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  922 */         localSQLException1.fillInStackTrace();
/*  923 */         throw localSQLException1;
/*      */       }
/*      */       
/*  926 */       int i = this.statement.currentRow;
/*      */       
/*  928 */       if (i < 0)
/*      */       {
/*  930 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/*  931 */         localSQLException2.fillInStackTrace();
/*  932 */         throw localSQLException2;
/*      */       }
/*      */       
/*  935 */       this.statement.lastIndex = paramInt;
/*      */       
/*  937 */       if (this.statement.streamList != null)
/*      */       {
/*  939 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/*  942 */       return this.statement.accessors[(paramInt - 1)].getInt(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getLong(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  952 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/*  955 */       if (this.closed)
/*      */       {
/*  957 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/*  958 */         localSQLException1.fillInStackTrace();
/*  959 */         throw localSQLException1;
/*      */       }
/*      */       
/*  962 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/*  964 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  965 */         localSQLException1.fillInStackTrace();
/*  966 */         throw localSQLException1;
/*      */       }
/*      */       
/*  969 */       int i = this.statement.currentRow;
/*      */       
/*  971 */       if (i < 0)
/*      */       {
/*  973 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/*  974 */         localSQLException2.fillInStackTrace();
/*  975 */         throw localSQLException2;
/*      */       }
/*      */       
/*  978 */       this.statement.lastIndex = paramInt;
/*      */       
/*  980 */       if (this.statement.streamList != null)
/*      */       {
/*  982 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/*  985 */       return this.statement.accessors[(paramInt - 1)].getLong(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public NClob getNClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  993 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/*  996 */       if (this.closed)
/*      */       {
/*  998 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/*  999 */         localSQLException1.fillInStackTrace();
/* 1000 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1003 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1005 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1006 */         localSQLException1.fillInStackTrace();
/* 1007 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1010 */       int i = this.statement.currentRow;
/*      */       
/* 1012 */       if (i < 0)
/*      */       {
/* 1014 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1015 */         localSQLException2.fillInStackTrace();
/* 1016 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1019 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1021 */       if (this.statement.streamList != null)
/*      */       {
/* 1023 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1026 */       return this.statement.accessors[(paramInt - 1)].getNClob(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public String getNString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1034 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1037 */       if (this.closed)
/*      */       {
/* 1039 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1040 */         localSQLException1.fillInStackTrace();
/* 1041 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1044 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1046 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1047 */         localSQLException1.fillInStackTrace();
/* 1048 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1051 */       int i = this.statement.currentRow;
/*      */       
/* 1053 */       if (i < 0)
/*      */       {
/* 1055 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1056 */         localSQLException2.fillInStackTrace();
/* 1057 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1060 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1062 */       if (this.statement.streamList != null)
/*      */       {
/* 1064 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1067 */       return this.statement.accessors[(paramInt - 1)].getNString(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Object getObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1075 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1078 */       if (this.closed)
/*      */       {
/* 1080 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1081 */         localSQLException1.fillInStackTrace();
/* 1082 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1085 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1087 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1088 */         localSQLException1.fillInStackTrace();
/* 1089 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1092 */       int i = this.statement.currentRow;
/*      */       
/* 1094 */       if (i < 0)
/*      */       {
/* 1096 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1097 */         localSQLException2.fillInStackTrace();
/* 1098 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1101 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1103 */       if (this.statement.streamList != null)
/*      */       {
/* 1105 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1108 */       return this.statement.accessors[(paramInt - 1)].getObject(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Object getObject(int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1116 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1119 */       if (this.closed)
/*      */       {
/* 1121 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1122 */         localSQLException1.fillInStackTrace();
/* 1123 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1126 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1128 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1129 */         localSQLException1.fillInStackTrace();
/* 1130 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1133 */       int i = this.statement.currentRow;
/*      */       
/* 1135 */       if (i < 0)
/*      */       {
/* 1137 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1138 */         localSQLException2.fillInStackTrace();
/* 1139 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1142 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1144 */       if (this.statement.streamList != null)
/*      */       {
/* 1146 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1149 */       return this.statement.accessors[(paramInt - 1)].getObject(i, paramMap);
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public java.sql.Ref getRef(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	oracle/jdbc/driver/OracleResultSetImpl:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 84	oracle/jdbc/driver/OracleResultSetImpl:getREF	(I)Loracle/sql/REF;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1157	-> byte code offset #0
/*      */     //   Java source line #1159	-> byte code offset #7
/*      */     //   Java source line #1161	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	OracleResultSetImpl
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
/*      */   public java.sql.RowId getRowId(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	oracle/jdbc/driver/OracleResultSetImpl:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 85	oracle/jdbc/driver/OracleResultSetImpl:getROWID	(I)Loracle/sql/ROWID;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1167	-> byte code offset #0
/*      */     //   Java source line #1169	-> byte code offset #7
/*      */     //   Java source line #1171	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	OracleResultSetImpl
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   public short getShort(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1177 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1180 */       if (this.closed)
/*      */       {
/* 1182 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1183 */         localSQLException1.fillInStackTrace();
/* 1184 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1187 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1189 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1190 */         localSQLException1.fillInStackTrace();
/* 1191 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1194 */       int i = this.statement.currentRow;
/*      */       
/* 1196 */       if (i < 0)
/*      */       {
/* 1198 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1199 */         localSQLException2.fillInStackTrace();
/* 1200 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1203 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1205 */       if (this.statement.streamList != null)
/*      */       {
/* 1207 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1210 */       return this.statement.accessors[(paramInt - 1)].getShort(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public SQLXML getSQLXML(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1218 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1221 */       if (this.closed)
/*      */       {
/* 1223 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1224 */         localSQLException1.fillInStackTrace();
/* 1225 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1228 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1230 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1231 */         localSQLException1.fillInStackTrace();
/* 1232 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1235 */       int i = this.statement.currentRow;
/*      */       
/* 1237 */       if (i < 0)
/*      */       {
/* 1239 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1240 */         localSQLException2.fillInStackTrace();
/* 1241 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1244 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1246 */       if (this.statement.streamList != null)
/*      */       {
/* 1248 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1251 */       return this.statement.accessors[(paramInt - 1)].getSQLXML(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1264 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1267 */       if (this.closed)
/*      */       {
/* 1269 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1270 */         localSQLException1.fillInStackTrace();
/* 1271 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1274 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1276 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1277 */         localSQLException1.fillInStackTrace();
/* 1278 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1281 */       int i = this.statement.currentRow;
/*      */       
/* 1283 */       if (i < 0)
/*      */       {
/* 1285 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1286 */         localSQLException2.fillInStackTrace();
/* 1287 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1290 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1292 */       if (this.statement.streamList != null)
/*      */       {
/* 1294 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1297 */       return this.statement.accessors[(paramInt - 1)].getString(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time getTime(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1307 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1310 */       if (this.closed)
/*      */       {
/* 1312 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1313 */         localSQLException1.fillInStackTrace();
/* 1314 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1317 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1319 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1320 */         localSQLException1.fillInStackTrace();
/* 1321 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1324 */       int i = this.statement.currentRow;
/*      */       
/* 1326 */       if (i < 0)
/*      */       {
/* 1328 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1329 */         localSQLException2.fillInStackTrace();
/* 1330 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1333 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1335 */       if (this.statement.streamList != null)
/*      */       {
/* 1337 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1340 */       return this.statement.accessors[(paramInt - 1)].getTime(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Time getTime(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1348 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1351 */       if (this.closed)
/*      */       {
/* 1353 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1354 */         localSQLException1.fillInStackTrace();
/* 1355 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1358 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1360 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1361 */         localSQLException1.fillInStackTrace();
/* 1362 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1365 */       int i = this.statement.currentRow;
/*      */       
/* 1367 */       if (i < 0)
/*      */       {
/* 1369 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1370 */         localSQLException2.fillInStackTrace();
/* 1371 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1374 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1376 */       if (this.statement.streamList != null)
/*      */       {
/* 1378 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1381 */       return this.statement.accessors[(paramInt - 1)].getTime(i, paramCalendar);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1389 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1392 */       if (this.closed)
/*      */       {
/* 1394 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1395 */         localSQLException1.fillInStackTrace();
/* 1396 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1399 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1401 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1402 */         localSQLException1.fillInStackTrace();
/* 1403 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1406 */       int i = this.statement.currentRow;
/*      */       
/* 1408 */       if (i < 0)
/*      */       {
/* 1410 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1411 */         localSQLException2.fillInStackTrace();
/* 1412 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1415 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1417 */       if (this.statement.streamList != null)
/*      */       {
/* 1419 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1422 */       return this.statement.accessors[(paramInt - 1)].getTimestamp(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1430 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1433 */       if (this.closed)
/*      */       {
/* 1435 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1436 */         localSQLException1.fillInStackTrace();
/* 1437 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1440 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1442 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1443 */         localSQLException1.fillInStackTrace();
/* 1444 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1447 */       int i = this.statement.currentRow;
/*      */       
/* 1449 */       if (i < 0)
/*      */       {
/* 1451 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1452 */         localSQLException2.fillInStackTrace();
/* 1453 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1456 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1458 */       if (this.statement.streamList != null)
/*      */       {
/* 1460 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1463 */       return this.statement.accessors[(paramInt - 1)].getTimestamp(i, paramCalendar);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public URL getURL(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1471 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1474 */       if (this.closed)
/*      */       {
/* 1476 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1477 */         localSQLException1.fillInStackTrace();
/* 1478 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1481 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1483 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1484 */         localSQLException1.fillInStackTrace();
/* 1485 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1488 */       int i = this.statement.currentRow;
/*      */       
/* 1490 */       if (i < 0)
/*      */       {
/* 1492 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1493 */         localSQLException2.fillInStackTrace();
/* 1494 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1497 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1499 */       if (this.statement.streamList != null)
/*      */       {
/* 1501 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1504 */       return this.statement.accessors[(paramInt - 1)].getURL(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public ARRAY getARRAY(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1512 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1515 */       if (this.closed)
/*      */       {
/* 1517 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1518 */         localSQLException1.fillInStackTrace();
/* 1519 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1522 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1524 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1525 */         localSQLException1.fillInStackTrace();
/* 1526 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1529 */       int i = this.statement.currentRow;
/*      */       
/* 1531 */       if (i < 0)
/*      */       {
/* 1533 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1534 */         localSQLException2.fillInStackTrace();
/* 1535 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1538 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1540 */       if (this.statement.streamList != null)
/*      */       {
/* 1542 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1545 */       return this.statement.accessors[(paramInt - 1)].getARRAY(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public BFILE getBFILE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1553 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1556 */       if (this.closed)
/*      */       {
/* 1558 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1559 */         localSQLException1.fillInStackTrace();
/* 1560 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1563 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1565 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1566 */         localSQLException1.fillInStackTrace();
/* 1567 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1570 */       int i = this.statement.currentRow;
/*      */       
/* 1572 */       if (i < 0)
/*      */       {
/* 1574 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1575 */         localSQLException2.fillInStackTrace();
/* 1576 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1579 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1581 */       if (this.statement.streamList != null)
/*      */       {
/* 1583 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1586 */       return this.statement.accessors[(paramInt - 1)].getBFILE(i);
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public BFILE getBfile(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	oracle/jdbc/driver/OracleResultSetImpl:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 96	oracle/jdbc/driver/OracleResultSetImpl:getBFILE	(I)Loracle/sql/BFILE;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1594	-> byte code offset #0
/*      */     //   Java source line #1596	-> byte code offset #7
/*      */     //   Java source line #1598	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	OracleResultSetImpl
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   public BLOB getBLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1604 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1607 */       if (this.closed)
/*      */       {
/* 1609 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1610 */         localSQLException1.fillInStackTrace();
/* 1611 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1614 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1616 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1617 */         localSQLException1.fillInStackTrace();
/* 1618 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1621 */       int i = this.statement.currentRow;
/*      */       
/* 1623 */       if (i < 0)
/*      */       {
/* 1625 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1626 */         localSQLException2.fillInStackTrace();
/* 1627 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1630 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1632 */       if (this.statement.streamList != null)
/*      */       {
/* 1634 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1637 */       return this.statement.accessors[(paramInt - 1)].getBLOB(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public CHAR getCHAR(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1645 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1648 */       if (this.closed)
/*      */       {
/* 1650 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1651 */         localSQLException1.fillInStackTrace();
/* 1652 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1655 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1657 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1658 */         localSQLException1.fillInStackTrace();
/* 1659 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1662 */       int i = this.statement.currentRow;
/*      */       
/* 1664 */       if (i < 0)
/*      */       {
/* 1666 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1667 */         localSQLException2.fillInStackTrace();
/* 1668 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1671 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1673 */       if (this.statement.streamList != null)
/*      */       {
/* 1675 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1678 */       return this.statement.accessors[(paramInt - 1)].getCHAR(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public CLOB getCLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1686 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1689 */       if (this.closed)
/*      */       {
/* 1691 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1692 */         localSQLException1.fillInStackTrace();
/* 1693 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1696 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1698 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1699 */         localSQLException1.fillInStackTrace();
/* 1700 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1703 */       int i = this.statement.currentRow;
/*      */       
/* 1705 */       if (i < 0)
/*      */       {
/* 1707 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1708 */         localSQLException2.fillInStackTrace();
/* 1709 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1712 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1714 */       if (this.statement.streamList != null)
/*      */       {
/* 1716 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1719 */       return this.statement.accessors[(paramInt - 1)].getCLOB(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public ResultSet getCursor(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1727 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1730 */       if (this.closed)
/*      */       {
/* 1732 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1733 */         localSQLException1.fillInStackTrace();
/* 1734 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1737 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1739 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1740 */         localSQLException1.fillInStackTrace();
/* 1741 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1744 */       int i = this.statement.currentRow;
/*      */       
/* 1746 */       if (i < 0)
/*      */       {
/* 1748 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1749 */         localSQLException2.fillInStackTrace();
/* 1750 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1753 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1755 */       if (this.statement.streamList != null)
/*      */       {
/* 1757 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1760 */       return this.statement.accessors[(paramInt - 1)].getCursor(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public CustomDatum getCustomDatum(int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */     throws SQLException
/*      */   {
/* 1768 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1771 */       if (this.closed)
/*      */       {
/* 1773 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1774 */         localSQLException1.fillInStackTrace();
/* 1775 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1778 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1780 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1781 */         localSQLException1.fillInStackTrace();
/* 1782 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1785 */       int i = this.statement.currentRow;
/*      */       
/* 1787 */       if (i < 0)
/*      */       {
/* 1789 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1790 */         localSQLException2.fillInStackTrace();
/* 1791 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1794 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1796 */       if (this.statement.streamList != null)
/*      */       {
/* 1798 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1801 */       return this.statement.accessors[(paramInt - 1)].getCustomDatum(i, paramCustomDatumFactory);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public DATE getDATE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1809 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1812 */       if (this.closed)
/*      */       {
/* 1814 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1815 */         localSQLException1.fillInStackTrace();
/* 1816 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1819 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1821 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1822 */         localSQLException1.fillInStackTrace();
/* 1823 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1826 */       int i = this.statement.currentRow;
/*      */       
/* 1828 */       if (i < 0)
/*      */       {
/* 1830 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1831 */         localSQLException2.fillInStackTrace();
/* 1832 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1835 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1837 */       if (this.statement.streamList != null)
/*      */       {
/* 1839 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1842 */       return this.statement.accessors[(paramInt - 1)].getDATE(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public INTERVALDS getINTERVALDS(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1850 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1853 */       if (this.closed)
/*      */       {
/* 1855 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1856 */         localSQLException1.fillInStackTrace();
/* 1857 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1860 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1862 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1863 */         localSQLException1.fillInStackTrace();
/* 1864 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1867 */       int i = this.statement.currentRow;
/*      */       
/* 1869 */       if (i < 0)
/*      */       {
/* 1871 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1872 */         localSQLException2.fillInStackTrace();
/* 1873 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1876 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1878 */       if (this.statement.streamList != null)
/*      */       {
/* 1880 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1883 */       return this.statement.accessors[(paramInt - 1)].getINTERVALDS(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public INTERVALYM getINTERVALYM(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1891 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1894 */       if (this.closed)
/*      */       {
/* 1896 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1897 */         localSQLException1.fillInStackTrace();
/* 1898 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1901 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1903 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1904 */         localSQLException1.fillInStackTrace();
/* 1905 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1908 */       int i = this.statement.currentRow;
/*      */       
/* 1910 */       if (i < 0)
/*      */       {
/* 1912 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1913 */         localSQLException2.fillInStackTrace();
/* 1914 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1917 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1919 */       if (this.statement.streamList != null)
/*      */       {
/* 1921 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1924 */       return this.statement.accessors[(paramInt - 1)].getINTERVALYM(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public NUMBER getNUMBER(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1932 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1935 */       if (this.closed)
/*      */       {
/* 1937 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1938 */         localSQLException1.fillInStackTrace();
/* 1939 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1942 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1944 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1945 */         localSQLException1.fillInStackTrace();
/* 1946 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1949 */       int i = this.statement.currentRow;
/*      */       
/* 1951 */       if (i < 0)
/*      */       {
/* 1953 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1954 */         localSQLException2.fillInStackTrace();
/* 1955 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1958 */       this.statement.lastIndex = paramInt;
/*      */       
/* 1960 */       if (this.statement.streamList != null)
/*      */       {
/* 1962 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 1965 */       return this.statement.accessors[(paramInt - 1)].getNUMBER(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public OPAQUE getOPAQUE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1973 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 1976 */       if (this.closed)
/*      */       {
/* 1978 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 1979 */         localSQLException1.fillInStackTrace();
/* 1980 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1983 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 1985 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1986 */         localSQLException1.fillInStackTrace();
/* 1987 */         throw localSQLException1;
/*      */       }
/*      */       
/* 1990 */       int i = this.statement.currentRow;
/*      */       
/* 1992 */       if (i < 0)
/*      */       {
/* 1994 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 1995 */         localSQLException2.fillInStackTrace();
/* 1996 */         throw localSQLException2;
/*      */       }
/*      */       
/* 1999 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2001 */       if (this.statement.streamList != null)
/*      */       {
/* 2003 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2006 */       return this.statement.accessors[(paramInt - 1)].getOPAQUE(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Datum getOracleObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2014 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2017 */       if (this.closed)
/*      */       {
/* 2019 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2020 */         localSQLException1.fillInStackTrace();
/* 2021 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2024 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2026 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2027 */         localSQLException1.fillInStackTrace();
/* 2028 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2031 */       int i = this.statement.currentRow;
/*      */       
/* 2033 */       if (i < 0)
/*      */       {
/* 2035 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2036 */         localSQLException2.fillInStackTrace();
/* 2037 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2040 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2042 */       if (this.statement.streamList != null)
/*      */       {
/* 2044 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2047 */       return this.statement.accessors[(paramInt - 1)].getOracleObject(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public ORAData getORAData(int paramInt, ORADataFactory paramORADataFactory)
/*      */     throws SQLException
/*      */   {
/* 2055 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2058 */       if (this.closed)
/*      */       {
/* 2060 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2061 */         localSQLException1.fillInStackTrace();
/* 2062 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2065 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2067 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2068 */         localSQLException1.fillInStackTrace();
/* 2069 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2072 */       int i = this.statement.currentRow;
/*      */       
/* 2074 */       if (i < 0)
/*      */       {
/* 2076 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2077 */         localSQLException2.fillInStackTrace();
/* 2078 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2081 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2083 */       if (this.statement.streamList != null)
/*      */       {
/* 2085 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2088 */       return this.statement.accessors[(paramInt - 1)].getORAData(i, paramORADataFactory);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Object getObject(int paramInt, OracleDataFactory paramOracleDataFactory)
/*      */     throws SQLException
/*      */   {
/* 2096 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2099 */       if (this.closed)
/*      */       {
/* 2101 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2102 */         localSQLException1.fillInStackTrace();
/* 2103 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2106 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2108 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2109 */         localSQLException1.fillInStackTrace();
/* 2110 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2113 */       int i = this.statement.currentRow;
/*      */       
/* 2115 */       if (i < 0)
/*      */       {
/* 2117 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2118 */         localSQLException2.fillInStackTrace();
/* 2119 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2122 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2124 */       if (this.statement.streamList != null)
/*      */       {
/* 2126 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2129 */       return this.statement.accessors[(paramInt - 1)].getObject(i, paramOracleDataFactory);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public RAW getRAW(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2137 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2140 */       if (this.closed)
/*      */       {
/* 2142 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2143 */         localSQLException1.fillInStackTrace();
/* 2144 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2147 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2149 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2150 */         localSQLException1.fillInStackTrace();
/* 2151 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2154 */       int i = this.statement.currentRow;
/*      */       
/* 2156 */       if (i < 0)
/*      */       {
/* 2158 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2159 */         localSQLException2.fillInStackTrace();
/* 2160 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2163 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2165 */       if (this.statement.streamList != null)
/*      */       {
/* 2167 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2170 */       return this.statement.accessors[(paramInt - 1)].getRAW(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public REF getREF(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2178 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2181 */       if (this.closed)
/*      */       {
/* 2183 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2184 */         localSQLException1.fillInStackTrace();
/* 2185 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2188 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2190 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2191 */         localSQLException1.fillInStackTrace();
/* 2192 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2195 */       int i = this.statement.currentRow;
/*      */       
/* 2197 */       if (i < 0)
/*      */       {
/* 2199 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2200 */         localSQLException2.fillInStackTrace();
/* 2201 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2204 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2206 */       if (this.statement.streamList != null)
/*      */       {
/* 2208 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2211 */       return this.statement.accessors[(paramInt - 1)].getREF(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public ROWID getROWID(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2219 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2222 */       if (this.closed)
/*      */       {
/* 2224 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2225 */         localSQLException1.fillInStackTrace();
/* 2226 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2229 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2231 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2232 */         localSQLException1.fillInStackTrace();
/* 2233 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2236 */       int i = this.statement.currentRow;
/*      */       
/* 2238 */       if (i < 0)
/*      */       {
/* 2240 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2241 */         localSQLException2.fillInStackTrace();
/* 2242 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2245 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2247 */       if (this.statement.streamList != null)
/*      */       {
/* 2249 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2252 */       return this.statement.accessors[(paramInt - 1)].getROWID(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public STRUCT getSTRUCT(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2260 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2263 */       if (this.closed)
/*      */       {
/* 2265 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2266 */         localSQLException1.fillInStackTrace();
/* 2267 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2270 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2272 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2273 */         localSQLException1.fillInStackTrace();
/* 2274 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2277 */       int i = this.statement.currentRow;
/*      */       
/* 2279 */       if (i < 0)
/*      */       {
/* 2281 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2282 */         localSQLException2.fillInStackTrace();
/* 2283 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2286 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2288 */       if (this.statement.streamList != null)
/*      */       {
/* 2290 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2293 */       return this.statement.accessors[(paramInt - 1)].getSTRUCT(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public TIMESTAMPLTZ getTIMESTAMPLTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2301 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2304 */       if (this.closed)
/*      */       {
/* 2306 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2307 */         localSQLException1.fillInStackTrace();
/* 2308 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2311 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2313 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2314 */         localSQLException1.fillInStackTrace();
/* 2315 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2318 */       int i = this.statement.currentRow;
/*      */       
/* 2320 */       if (i < 0)
/*      */       {
/* 2322 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2323 */         localSQLException2.fillInStackTrace();
/* 2324 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2327 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2329 */       if (this.statement.streamList != null)
/*      */       {
/* 2331 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2334 */       return this.statement.accessors[(paramInt - 1)].getTIMESTAMPLTZ(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public TIMESTAMPTZ getTIMESTAMPTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2342 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2345 */       if (this.closed)
/*      */       {
/* 2347 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2348 */         localSQLException1.fillInStackTrace();
/* 2349 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2352 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2354 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2355 */         localSQLException1.fillInStackTrace();
/* 2356 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2359 */       int i = this.statement.currentRow;
/*      */       
/* 2361 */       if (i < 0)
/*      */       {
/* 2363 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2364 */         localSQLException2.fillInStackTrace();
/* 2365 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2368 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2370 */       if (this.statement.streamList != null)
/*      */       {
/* 2372 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2375 */       return this.statement.accessors[(paramInt - 1)].getTIMESTAMPTZ(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public TIMESTAMP getTIMESTAMP(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2383 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2386 */       if (this.closed)
/*      */       {
/* 2388 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2389 */         localSQLException1.fillInStackTrace();
/* 2390 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2393 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2395 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2396 */         localSQLException1.fillInStackTrace();
/* 2397 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2400 */       int i = this.statement.currentRow;
/*      */       
/* 2402 */       if (i < 0)
/*      */       {
/* 2404 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2405 */         localSQLException2.fillInStackTrace();
/* 2406 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2409 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2411 */       if (this.statement.streamList != null)
/*      */       {
/* 2413 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2416 */       return this.statement.accessors[(paramInt - 1)].getTIMESTAMP(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public InputStream getAsciiStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2424 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2427 */       if (this.closed)
/*      */       {
/* 2429 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2430 */         localSQLException1.fillInStackTrace();
/* 2431 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2434 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2436 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2437 */         localSQLException1.fillInStackTrace();
/* 2438 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2441 */       int i = this.statement.currentRow;
/*      */       
/* 2443 */       if (i < 0)
/*      */       {
/* 2445 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2446 */         localSQLException2.fillInStackTrace();
/* 2447 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2450 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2452 */       if (this.statement.streamList != null)
/*      */       {
/* 2454 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2457 */       return this.statement.accessors[(paramInt - 1)].getAsciiStream(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public InputStream getBinaryStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2465 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2468 */       if (this.closed)
/*      */       {
/* 2470 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2471 */         localSQLException1.fillInStackTrace();
/* 2472 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2475 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2477 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2478 */         localSQLException1.fillInStackTrace();
/* 2479 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2482 */       int i = this.statement.currentRow;
/*      */       
/* 2484 */       if (i < 0)
/*      */       {
/* 2486 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2487 */         localSQLException2.fillInStackTrace();
/* 2488 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2491 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2493 */       if (this.statement.streamList != null)
/*      */       {
/* 2495 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2498 */       return this.statement.accessors[(paramInt - 1)].getBinaryStream(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Reader getCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2506 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2509 */       if (this.closed)
/*      */       {
/* 2511 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2512 */         localSQLException1.fillInStackTrace();
/* 2513 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2516 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2518 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2519 */         localSQLException1.fillInStackTrace();
/* 2520 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2523 */       int i = this.statement.currentRow;
/*      */       
/* 2525 */       if (i < 0)
/*      */       {
/* 2527 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2528 */         localSQLException2.fillInStackTrace();
/* 2529 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2532 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2534 */       if (this.statement.streamList != null)
/*      */       {
/* 2536 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2539 */       return this.statement.accessors[(paramInt - 1)].getCharacterStream(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Reader getNCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2547 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2550 */       if (this.closed)
/*      */       {
/* 2552 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2553 */         localSQLException1.fillInStackTrace();
/* 2554 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2557 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2559 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2560 */         localSQLException1.fillInStackTrace();
/* 2561 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2564 */       int i = this.statement.currentRow;
/*      */       
/* 2566 */       if (i < 0)
/*      */       {
/* 2568 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2569 */         localSQLException2.fillInStackTrace();
/* 2570 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2573 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2575 */       if (this.statement.streamList != null)
/*      */       {
/* 2577 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2580 */       return this.statement.accessors[(paramInt - 1)].getNCharacterStream(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public InputStream getUnicodeStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2588 */     synchronized (this.connection)
/*      */     {
/*      */       SQLException localSQLException1;
/* 2591 */       if (this.closed)
/*      */       {
/* 2593 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2594 */         localSQLException1.fillInStackTrace();
/* 2595 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2598 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2600 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2601 */         localSQLException1.fillInStackTrace();
/* 2602 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2605 */       int i = this.statement.currentRow;
/*      */       
/* 2607 */       if (i < 0)
/*      */       {
/* 2609 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2610 */         localSQLException2.fillInStackTrace();
/* 2611 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2614 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2616 */       if (this.statement.streamList != null)
/*      */       {
/* 2618 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 2621 */       return this.statement.accessors[(paramInt - 1)].getUnicodeStream(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleResultSet.AuthorizationIndicator getAuthorizationIndicator(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2633 */     synchronized (this.connection) {
/*      */       SQLException localSQLException1;
/* 2635 */       if (this.closed)
/*      */       {
/* 2637 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2638 */         localSQLException1.fillInStackTrace();
/* 2639 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2642 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2644 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2645 */         localSQLException1.fillInStackTrace();
/* 2646 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2649 */       int i = this.statement.currentRow;
/*      */       
/* 2651 */       if (i < 0)
/*      */       {
/* 2653 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2654 */         localSQLException2.fillInStackTrace();
/* 2655 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2658 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2660 */       if (this.statement.streamList != null)
/*      */       {
/* 2662 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/* 2664 */       return this.statement.accessors[(paramInt - 1)].getAuthorizationIndicator(i);
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
/*      */   byte[] privateGetBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2681 */     synchronized (this.connection) {
/*      */       SQLException localSQLException1;
/* 2683 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2685 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2686 */         localSQLException1.fillInStackTrace();
/* 2687 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2690 */       if (this.closed)
/*      */       {
/* 2692 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 2693 */         localSQLException1.fillInStackTrace();
/* 2694 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2697 */       if ((paramInt <= 0) || (paramInt > this.statement.numberOfDefinePositions))
/*      */       {
/* 2699 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2700 */         localSQLException1.fillInStackTrace();
/* 2701 */         throw localSQLException1;
/*      */       }
/*      */       
/* 2704 */       int i = this.statement.currentRow;
/*      */       
/* 2706 */       if (i < 0)
/*      */       {
/* 2708 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14);
/* 2709 */         localSQLException2.fillInStackTrace();
/* 2710 */         throw localSQLException2;
/*      */       }
/*      */       
/* 2713 */       this.statement.lastIndex = paramInt;
/*      */       
/* 2715 */       if (this.statement.streamList != null)
/*      */       {
/* 2717 */         this.statement.closeUsedStreams(paramInt);
/*      */       }
/*      */       
/*      */ 
/* 2721 */       return this.statement.accessors[(paramInt - 1)].privateGetBytes(i);
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
/*      */   public void setFetchSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2735 */     this.statement.setPrefetchInternal(paramInt, false, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getFetchSize()
/*      */     throws SQLException
/*      */   {
/* 2743 */     return this.statement.getPrefetchInternal(false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void internal_close(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 2754 */     if (this.closed) { return;
/*      */     }
/* 2756 */     super.close();
/*      */     
/* 2758 */     if ((this.statement.gotLastBatch) && (this.statement.validRows == 0)) {
/* 2759 */       this.m_emptyRset = true;
/*      */     }
/*      */     
/* 2762 */     PhysicalConnection localPhysicalConnection = this.statement.connection;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/* 2773 */       localPhysicalConnection.registerHeartbeat();
/*      */       
/*      */ 
/* 2776 */       localPhysicalConnection.needLine();
/*      */       
/* 2778 */       synchronized (localPhysicalConnection)
/*      */       {
/*      */ 
/* 2781 */         this.statement.closeQuery();
/*      */       }
/*      */     }
/*      */     catch (SQLException localSQLException) {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2790 */     this.statement.endOfResultSet(paramBoolean);
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public int findColumn(String paramString)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	oracle/jdbc/driver/OracleResultSetImpl:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 4	oracle/jdbc/driver/OracleResultSetImpl:statement	Loracle/jdbc/driver/OracleStatement;
/*      */     //   11: aload_1
/*      */     //   12: invokevirtual 129	oracle/jdbc/driver/OracleStatement:getColumnIndex	(Ljava/lang/String;)I
/*      */     //   15: aload_2
/*      */     //   16: monitorexit
/*      */     //   17: ireturn
/*      */     //   18: astore_3
/*      */     //   19: aload_2
/*      */     //   20: monitorexit
/*      */     //   21: aload_3
/*      */     //   22: athrow
/*      */     // Line number table:
/*      */     //   Java source line #2797	-> byte code offset #0
/*      */     //   Java source line #2799	-> byte code offset #7
/*      */     //   Java source line #2801	-> byte code offset #18
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	23	0	this	OracleResultSetImpl
/*      */     //   0	23	1	paramString	String
/*      */     //   5	15	2	Ljava/lang/Object;	Object
/*      */     //   18	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	17	18	finally
/*      */     //   18	21	18	finally
/*      */   }
/*      */   
/*      */   boolean isEmptyResultSet()
/*      */     throws SQLException
/*      */   {
/* 2816 */     if ((this.statement != null) && (!this.statement.closed) && (this.statement.serverCursor) && (this.statement.connection.protocolId != 3) && (!this.isServerCursorPeeked) && (!this.closed))
/*      */     {
/*      */ 
/*      */ 
/* 2820 */       close_or_fetch_from_next(false);
/*      */       
/* 2822 */       if (this.statement.validRows > 0) {
/* 2823 */         this.statement.currentRow = -1;
/*      */       } else {
/* 2825 */         this.m_emptyRset = true;
/*      */       }
/* 2827 */       this.isServerCursorPeeked = true;
/*      */     }
/*      */     
/* 2830 */     return (this.m_emptyRset) || ((!this.m_emptyRset) && (this.statement.gotLastBatch) && (this.statement.validRows == 0));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   int getValidRows()
/*      */   {
/* 2839 */     return this.statement.validRows;
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
/* 2854 */     return this.connection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 2859 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleResultSetImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */