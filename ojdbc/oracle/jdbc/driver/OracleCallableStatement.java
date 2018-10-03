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
/*      */ import oracle.sql.ANYDATA;
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
/*      */ abstract class OracleCallableStatement
/*      */   extends OraclePreparedStatement
/*      */   implements oracle.jdbc.internal.OracleCallableStatement
/*      */ {
/*   56 */   boolean atLeastOneOrdinalParameter = false;
/*   57 */   boolean atLeastOneNamedParameter = false;
/*      */   
/*      */ 
/*   60 */   String[] namedParameters = new String[8];
/*      */   
/*      */ 
/*   63 */   int parameterCount = 0;
/*      */   
/*      */ 
/*   66 */   final String errMsgMixedBind = "Ordinal binding and Named binding cannot be combined!";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   OracleCallableStatement(PhysicalConnection paramPhysicalConnection, String paramString, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*   87 */     this(paramPhysicalConnection, paramString, paramInt1, paramInt2, 1003, 1007);
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
/*      */   OracleCallableStatement(PhysicalConnection paramPhysicalConnection, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/*  105 */     super(paramPhysicalConnection, paramString, 1, paramInt2, paramInt3, paramInt4);
/*      */     
/*      */ 
/*  108 */     this.statementType = 2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void registerOutParameterInternal(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString)
/*      */     throws SQLException
/*      */   {
/*  121 */     int i = paramInt1 - 1;
/*  122 */     SQLException localSQLException; if ((i < 0) || (paramInt1 > this.numberOfBindPositions))
/*      */     {
/*  124 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  125 */       localSQLException.fillInStackTrace();
/*  126 */       throw localSQLException;
/*      */     }
/*      */     
/*  129 */     if (paramInt2 == 0)
/*      */     {
/*  131 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/*  132 */       localSQLException.fillInStackTrace();
/*  133 */       throw localSQLException;
/*      */     }
/*  135 */     int j = getInternalType(paramInt2);
/*      */     
/*  137 */     resetBatch();
/*  138 */     this.currentRowNeedToPrepareBinds = true;
/*      */     
/*  140 */     if (this.currentRowBindAccessors == null) {
/*  141 */       this.currentRowBindAccessors = new Accessor[this.numberOfBindPositions];
/*      */     }
/*      */     
/*  144 */     switch (paramInt2)
/*      */     {
/*      */     case -4: 
/*      */     case -3: 
/*      */     case -1: 
/*      */     case 1: 
/*      */     case 12: 
/*      */     case 70: 
/*      */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case -16: 
/*      */     case -15: 
/*      */     case -9: 
/*  160 */       this.currentRowFormOfUse[i] = 2;
/*  161 */       break;
/*      */     case 2011: 
/*  163 */       paramInt4 = 0;
/*  164 */       this.currentRowFormOfUse[i] = 2;
/*  165 */       break;
/*      */     case 2009: 
/*  167 */       paramInt4 = 0;
/*  168 */       paramString = "SYS.XMLTYPE";
/*  169 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     default: 
/*  174 */       paramInt4 = 0;
/*      */     }
/*      */     
/*      */     
/*  178 */     this.currentRowBindAccessors[i] = allocateAccessor(j, paramInt2, i + 1, paramInt4, this.currentRowFormOfUse[i], paramString, true);
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
/*      */   public void registerOutParameter(int paramInt1, int paramInt2, String paramString)
/*      */     throws SQLException
/*      */   {
/*  212 */     if ((paramString == null) || (paramString.length() == 0))
/*      */     {
/*  214 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 60, "empty Object name");
/*  215 */       localSQLException.fillInStackTrace();
/*  216 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  222 */     synchronized (this.connection) {
/*  223 */       registerOutParameterInternal(paramInt1, paramInt2, 0, 0, paramString);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void registerOutParameterBytes(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/*  252 */     synchronized (this.connection)
/*      */     {
/*  254 */       registerOutParameterInternal(paramInt1, paramInt2, paramInt3, paramInt4, null);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void registerOutParameterChars(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/*  283 */     synchronized (this.connection)
/*      */     {
/*  285 */       registerOutParameterInternal(paramInt1, paramInt2, paramInt3, paramInt4, null);
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
/*      */   public void registerOutParameter(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/*  302 */     synchronized (this.connection)
/*      */     {
/*  304 */       registerOutParameterInternal(paramInt1, paramInt2, paramInt3, paramInt4, null);
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
/*      */   public void registerOutParameter(String paramString, int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  321 */     synchronized (this.connection)
/*      */     {
/*  323 */       registerOutParameterInternal(paramString, paramInt1, paramInt2, paramInt3, null);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean isOracleBatchStyle()
/*      */   {
/*  332 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void resetBatch()
/*      */   {
/*  342 */     this.batch = 1;
/*      */   }
/*      */   
/*      */   public void setExecuteBatch(int paramInt)
/*      */     throws SQLException
/*      */   {}
/*      */   
/*      */   /* Error */
/*      */   public int sendBatch()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 26	oracle/jdbc/driver/OracleCallableStatement:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 30	oracle/jdbc/driver/OracleCallableStatement:validRows	I
/*      */     //   11: aload_1
/*      */     //   12: monitorexit
/*      */     //   13: ireturn
/*      */     //   14: astore_2
/*      */     //   15: aload_1
/*      */     //   16: monitorexit
/*      */     //   17: aload_2
/*      */     //   18: athrow
/*      */     // Line number table:
/*      */     //   Java source line #371	-> byte code offset #0
/*      */     //   Java source line #374	-> byte code offset #7
/*      */     //   Java source line #376	-> byte code offset #14
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	19	0	this	OracleCallableStatement
/*      */     //   5	11	1	Ljava/lang/Object;	Object
/*      */     //   14	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	13	14	finally
/*      */     //   14	17	14	finally
/*      */   }
/*      */   
/*      */   public void registerOutParameter(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  393 */     registerOutParameter(paramInt1, paramInt2, 0, -1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void registerOutParameter(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  405 */     registerOutParameter(paramInt1, paramInt2, paramInt3, -1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean wasNull()
/*      */     throws SQLException
/*      */   {
/*  413 */     return wasNullValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getString(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  423 */     if (this.closed)
/*      */     {
/*  425 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  426 */       ((SQLException)localObject).fillInStackTrace();
/*  427 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  430 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/*  433 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/*  434 */       ((SQLException)localObject).fillInStackTrace();
/*  435 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  439 */     Object localObject = null;
/*  440 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  445 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  446 */       localSQLException.fillInStackTrace();
/*  447 */       throw localSQLException;
/*      */     }
/*      */     
/*  450 */     this.lastIndex = paramInt;
/*      */     
/*  452 */     if (this.streamList != null) {
/*  453 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/*  456 */     return ((Accessor)localObject).getString(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Datum getOracleObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  466 */     if (this.closed)
/*      */     {
/*  468 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  469 */       ((SQLException)localObject).fillInStackTrace();
/*  470 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  473 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/*  476 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/*  477 */       ((SQLException)localObject).fillInStackTrace();
/*  478 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  482 */     Object localObject = null;
/*  483 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  488 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  489 */       localSQLException.fillInStackTrace();
/*  490 */       throw localSQLException;
/*      */     }
/*      */     
/*  493 */     this.lastIndex = paramInt;
/*      */     
/*  495 */     if (this.streamList != null) {
/*  496 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/*  499 */     return ((Accessor)localObject).getOracleObject(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ROWID getROWID(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  509 */     if (this.closed)
/*      */     {
/*  511 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  512 */       ((SQLException)localObject).fillInStackTrace();
/*  513 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  516 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/*  519 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/*  520 */       ((SQLException)localObject).fillInStackTrace();
/*  521 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  525 */     Object localObject = null;
/*  526 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  531 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  532 */       localSQLException.fillInStackTrace();
/*  533 */       throw localSQLException;
/*      */     }
/*      */     
/*  536 */     this.lastIndex = paramInt;
/*      */     
/*  538 */     if (this.streamList != null) {
/*  539 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/*  542 */     return ((Accessor)localObject).getROWID(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER getNUMBER(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  552 */     if (this.closed)
/*      */     {
/*  554 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  555 */       ((SQLException)localObject).fillInStackTrace();
/*  556 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  559 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/*  562 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/*  563 */       ((SQLException)localObject).fillInStackTrace();
/*  564 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  568 */     Object localObject = null;
/*  569 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  574 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  575 */       localSQLException.fillInStackTrace();
/*  576 */       throw localSQLException;
/*      */     }
/*      */     
/*  579 */     this.lastIndex = paramInt;
/*      */     
/*  581 */     if (this.streamList != null) {
/*  582 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/*  585 */     return ((Accessor)localObject).getNUMBER(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DATE getDATE(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  595 */     if (this.closed)
/*      */     {
/*  597 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  598 */       ((SQLException)localObject).fillInStackTrace();
/*  599 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  602 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/*  605 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/*  606 */       ((SQLException)localObject).fillInStackTrace();
/*  607 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  611 */     Object localObject = null;
/*  612 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  617 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  618 */       localSQLException.fillInStackTrace();
/*  619 */       throw localSQLException;
/*      */     }
/*      */     
/*  622 */     this.lastIndex = paramInt;
/*      */     
/*  624 */     if (this.streamList != null) {
/*  625 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/*  628 */     return ((Accessor)localObject).getDATE(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public INTERVALYM getINTERVALYM(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  638 */     if (this.closed)
/*      */     {
/*  640 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  641 */       ((SQLException)localObject).fillInStackTrace();
/*  642 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  645 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/*  648 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/*  649 */       ((SQLException)localObject).fillInStackTrace();
/*  650 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  654 */     Object localObject = null;
/*  655 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  660 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  661 */       localSQLException.fillInStackTrace();
/*  662 */       throw localSQLException;
/*      */     }
/*      */     
/*  665 */     this.lastIndex = paramInt;
/*      */     
/*  667 */     if (this.streamList != null) {
/*  668 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/*  671 */     return ((Accessor)localObject).getINTERVALYM(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public INTERVALDS getINTERVALDS(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  681 */     if (this.closed)
/*      */     {
/*  683 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  684 */       ((SQLException)localObject).fillInStackTrace();
/*  685 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  688 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/*  691 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/*  692 */       ((SQLException)localObject).fillInStackTrace();
/*  693 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  697 */     Object localObject = null;
/*  698 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  703 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  704 */       localSQLException.fillInStackTrace();
/*  705 */       throw localSQLException;
/*      */     }
/*      */     
/*  708 */     this.lastIndex = paramInt;
/*      */     
/*  710 */     if (this.streamList != null) {
/*  711 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/*  714 */     return ((Accessor)localObject).getINTERVALDS(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMP getTIMESTAMP(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  724 */     if (this.closed)
/*      */     {
/*  726 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  727 */       ((SQLException)localObject).fillInStackTrace();
/*  728 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  731 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/*  734 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/*  735 */       ((SQLException)localObject).fillInStackTrace();
/*  736 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  740 */     Object localObject = null;
/*  741 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  746 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  747 */       localSQLException.fillInStackTrace();
/*  748 */       throw localSQLException;
/*      */     }
/*      */     
/*  751 */     this.lastIndex = paramInt;
/*      */     
/*  753 */     if (this.streamList != null) {
/*  754 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/*  757 */     return ((Accessor)localObject).getTIMESTAMP(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPTZ getTIMESTAMPTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  767 */     if (this.closed)
/*      */     {
/*  769 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  770 */       ((SQLException)localObject).fillInStackTrace();
/*  771 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  774 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/*  777 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/*  778 */       ((SQLException)localObject).fillInStackTrace();
/*  779 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  783 */     Object localObject = null;
/*  784 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  789 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  790 */       localSQLException.fillInStackTrace();
/*  791 */       throw localSQLException;
/*      */     }
/*      */     
/*  794 */     this.lastIndex = paramInt;
/*      */     
/*  796 */     if (this.streamList != null) {
/*  797 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/*  800 */     return ((Accessor)localObject).getTIMESTAMPTZ(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TIMESTAMPLTZ getTIMESTAMPLTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  810 */     if (this.closed)
/*      */     {
/*  812 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  813 */       ((SQLException)localObject).fillInStackTrace();
/*  814 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  817 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/*  820 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/*  821 */       ((SQLException)localObject).fillInStackTrace();
/*  822 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  826 */     Object localObject = null;
/*  827 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  832 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  833 */       localSQLException.fillInStackTrace();
/*  834 */       throw localSQLException;
/*      */     }
/*      */     
/*  837 */     this.lastIndex = paramInt;
/*      */     
/*  839 */     if (this.streamList != null) {
/*  840 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/*  843 */     return ((Accessor)localObject).getTIMESTAMPLTZ(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public REF getREF(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  853 */     if (this.closed)
/*      */     {
/*  855 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  856 */       ((SQLException)localObject).fillInStackTrace();
/*  857 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  860 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/*  863 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/*  864 */       ((SQLException)localObject).fillInStackTrace();
/*  865 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  869 */     Object localObject = null;
/*  870 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  875 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  876 */       localSQLException.fillInStackTrace();
/*  877 */       throw localSQLException;
/*      */     }
/*      */     
/*  880 */     this.lastIndex = paramInt;
/*      */     
/*  882 */     if (this.streamList != null) {
/*  883 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/*  886 */     return ((Accessor)localObject).getREF(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ARRAY getARRAY(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  896 */     if (this.closed)
/*      */     {
/*  898 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  899 */       ((SQLException)localObject).fillInStackTrace();
/*  900 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  903 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/*  906 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/*  907 */       ((SQLException)localObject).fillInStackTrace();
/*  908 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  912 */     Object localObject = null;
/*  913 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  918 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  919 */       localSQLException.fillInStackTrace();
/*  920 */       throw localSQLException;
/*      */     }
/*      */     
/*  923 */     this.lastIndex = paramInt;
/*      */     
/*  925 */     if (this.streamList != null) {
/*  926 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/*  929 */     return ((Accessor)localObject).getARRAY(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public STRUCT getSTRUCT(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  939 */     if (this.closed)
/*      */     {
/*  941 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  942 */       ((SQLException)localObject).fillInStackTrace();
/*  943 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  946 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/*  949 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/*  950 */       ((SQLException)localObject).fillInStackTrace();
/*  951 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  955 */     Object localObject = null;
/*  956 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  961 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  962 */       localSQLException.fillInStackTrace();
/*  963 */       throw localSQLException;
/*      */     }
/*      */     
/*  966 */     this.lastIndex = paramInt;
/*      */     
/*  968 */     if (this.streamList != null) {
/*  969 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/*  972 */     return ((Accessor)localObject).getSTRUCT(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OPAQUE getOPAQUE(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  982 */     if (this.closed)
/*      */     {
/*  984 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/*  985 */       ((SQLException)localObject).fillInStackTrace();
/*  986 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  989 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/*  992 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/*  993 */       ((SQLException)localObject).fillInStackTrace();
/*  994 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  998 */     Object localObject = null;
/*  999 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1004 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1005 */       localSQLException.fillInStackTrace();
/* 1006 */       throw localSQLException;
/*      */     }
/*      */     
/* 1009 */     this.lastIndex = paramInt;
/*      */     
/* 1011 */     if (this.streamList != null) {
/* 1012 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1015 */     return ((Accessor)localObject).getOPAQUE(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CHAR getCHAR(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1025 */     if (this.closed)
/*      */     {
/* 1027 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1028 */       ((SQLException)localObject).fillInStackTrace();
/* 1029 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1032 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1035 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1036 */       ((SQLException)localObject).fillInStackTrace();
/* 1037 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1041 */     Object localObject = null;
/* 1042 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1047 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1048 */       localSQLException.fillInStackTrace();
/* 1049 */       throw localSQLException;
/*      */     }
/*      */     
/* 1052 */     this.lastIndex = paramInt;
/*      */     
/* 1054 */     if (this.streamList != null) {
/* 1055 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1058 */     return ((Accessor)localObject).getCHAR(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader getCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1069 */     if (this.closed)
/*      */     {
/* 1071 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1072 */       ((SQLException)localObject).fillInStackTrace();
/* 1073 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1076 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1079 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1080 */       ((SQLException)localObject).fillInStackTrace();
/* 1081 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1085 */     Object localObject = null;
/* 1086 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1091 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1092 */       localSQLException.fillInStackTrace();
/* 1093 */       throw localSQLException;
/*      */     }
/*      */     
/* 1096 */     this.lastIndex = paramInt;
/*      */     
/* 1098 */     if (this.streamList != null) {
/* 1099 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1102 */     return ((Accessor)localObject).getCharacterStream(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RAW getRAW(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1112 */     if (this.closed)
/*      */     {
/* 1114 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1115 */       ((SQLException)localObject).fillInStackTrace();
/* 1116 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1119 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1122 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1123 */       ((SQLException)localObject).fillInStackTrace();
/* 1124 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1128 */     Object localObject = null;
/* 1129 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1134 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1135 */       localSQLException.fillInStackTrace();
/* 1136 */       throw localSQLException;
/*      */     }
/*      */     
/* 1139 */     this.lastIndex = paramInt;
/*      */     
/* 1141 */     if (this.streamList != null) {
/* 1142 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1145 */     return ((Accessor)localObject).getRAW(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BLOB getBLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1156 */     if (this.closed)
/*      */     {
/* 1158 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1159 */       ((SQLException)localObject).fillInStackTrace();
/* 1160 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1163 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1166 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1167 */       ((SQLException)localObject).fillInStackTrace();
/* 1168 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1172 */     Object localObject = null;
/* 1173 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1178 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1179 */       localSQLException.fillInStackTrace();
/* 1180 */       throw localSQLException;
/*      */     }
/*      */     
/* 1183 */     this.lastIndex = paramInt;
/*      */     
/* 1185 */     if (this.streamList != null) {
/* 1186 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1189 */     return ((Accessor)localObject).getBLOB(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CLOB getCLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1199 */     if (this.closed)
/*      */     {
/* 1201 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1202 */       ((SQLException)localObject).fillInStackTrace();
/* 1203 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1206 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1209 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1210 */       ((SQLException)localObject).fillInStackTrace();
/* 1211 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1215 */     Object localObject = null;
/* 1216 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1221 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1222 */       localSQLException.fillInStackTrace();
/* 1223 */       throw localSQLException;
/*      */     }
/*      */     
/* 1226 */     this.lastIndex = paramInt;
/*      */     
/* 1228 */     if (this.streamList != null) {
/* 1229 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1232 */     return ((Accessor)localObject).getCLOB(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BFILE getBFILE(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1242 */     if (this.closed)
/*      */     {
/* 1244 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1245 */       ((SQLException)localObject).fillInStackTrace();
/* 1246 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1249 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1252 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1253 */       ((SQLException)localObject).fillInStackTrace();
/* 1254 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1258 */     Object localObject = null;
/* 1259 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1264 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1265 */       localSQLException.fillInStackTrace();
/* 1266 */       throw localSQLException;
/*      */     }
/*      */     
/* 1269 */     this.lastIndex = paramInt;
/*      */     
/* 1271 */     if (this.streamList != null) {
/* 1272 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1275 */     return ((Accessor)localObject).getBFILE(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BFILE getBfile(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1285 */     if (this.closed)
/*      */     {
/* 1287 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1288 */       ((SQLException)localObject).fillInStackTrace();
/* 1289 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1292 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1295 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1296 */       ((SQLException)localObject).fillInStackTrace();
/* 1297 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1301 */     Object localObject = null;
/* 1302 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1307 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1308 */       localSQLException.fillInStackTrace();
/* 1309 */       throw localSQLException;
/*      */     }
/*      */     
/* 1312 */     this.lastIndex = paramInt;
/*      */     
/* 1314 */     if (this.streamList != null) {
/* 1315 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1318 */     return ((Accessor)localObject).getBFILE(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getBoolean(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1328 */     if (this.closed)
/*      */     {
/* 1330 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1331 */       ((SQLException)localObject).fillInStackTrace();
/* 1332 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1335 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1338 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1339 */       ((SQLException)localObject).fillInStackTrace();
/* 1340 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1344 */     Object localObject = null;
/* 1345 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1350 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1351 */       localSQLException.fillInStackTrace();
/* 1352 */       throw localSQLException;
/*      */     }
/*      */     
/* 1355 */     this.lastIndex = paramInt;
/*      */     
/* 1357 */     if (this.streamList != null) {
/* 1358 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1361 */     return ((Accessor)localObject).getBoolean(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte getByte(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1371 */     if (this.closed)
/*      */     {
/* 1373 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1374 */       ((SQLException)localObject).fillInStackTrace();
/* 1375 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1378 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1381 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1382 */       ((SQLException)localObject).fillInStackTrace();
/* 1383 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1387 */     Object localObject = null;
/* 1388 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1393 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1394 */       localSQLException.fillInStackTrace();
/* 1395 */       throw localSQLException;
/*      */     }
/*      */     
/* 1398 */     this.lastIndex = paramInt;
/*      */     
/* 1400 */     if (this.streamList != null) {
/* 1401 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1404 */     return ((Accessor)localObject).getByte(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short getShort(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1414 */     if (this.closed)
/*      */     {
/* 1416 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1417 */       ((SQLException)localObject).fillInStackTrace();
/* 1418 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1421 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1424 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1425 */       ((SQLException)localObject).fillInStackTrace();
/* 1426 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1430 */     Object localObject = null;
/* 1431 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1436 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1437 */       localSQLException.fillInStackTrace();
/* 1438 */       throw localSQLException;
/*      */     }
/*      */     
/* 1441 */     this.lastIndex = paramInt;
/*      */     
/* 1443 */     if (this.streamList != null) {
/* 1444 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1447 */     return ((Accessor)localObject).getShort(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getInt(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1457 */     if (this.closed)
/*      */     {
/* 1459 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1460 */       ((SQLException)localObject).fillInStackTrace();
/* 1461 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1464 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1467 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1468 */       ((SQLException)localObject).fillInStackTrace();
/* 1469 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1473 */     Object localObject = null;
/* 1474 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1479 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1480 */       localSQLException.fillInStackTrace();
/* 1481 */       throw localSQLException;
/*      */     }
/*      */     
/* 1484 */     this.lastIndex = paramInt;
/*      */     
/* 1486 */     if (this.streamList != null) {
/* 1487 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1490 */     return ((Accessor)localObject).getInt(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getLong(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1500 */     if (this.closed)
/*      */     {
/* 1502 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1503 */       ((SQLException)localObject).fillInStackTrace();
/* 1504 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1507 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1510 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1511 */       ((SQLException)localObject).fillInStackTrace();
/* 1512 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1516 */     Object localObject = null;
/* 1517 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1522 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1523 */       localSQLException.fillInStackTrace();
/* 1524 */       throw localSQLException;
/*      */     }
/*      */     
/* 1527 */     this.lastIndex = paramInt;
/*      */     
/* 1529 */     if (this.streamList != null) {
/* 1530 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1533 */     return ((Accessor)localObject).getLong(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float getFloat(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1543 */     if (this.closed)
/*      */     {
/* 1545 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1546 */       ((SQLException)localObject).fillInStackTrace();
/* 1547 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1550 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1553 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1554 */       ((SQLException)localObject).fillInStackTrace();
/* 1555 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1559 */     Object localObject = null;
/* 1560 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1565 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1566 */       localSQLException.fillInStackTrace();
/* 1567 */       throw localSQLException;
/*      */     }
/*      */     
/* 1570 */     this.lastIndex = paramInt;
/*      */     
/* 1572 */     if (this.streamList != null) {
/* 1573 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1576 */     return ((Accessor)localObject).getFloat(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDouble(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1586 */     if (this.closed)
/*      */     {
/* 1588 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1589 */       ((SQLException)localObject).fillInStackTrace();
/* 1590 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1593 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1596 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1597 */       ((SQLException)localObject).fillInStackTrace();
/* 1598 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1602 */     Object localObject = null;
/* 1603 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1608 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1609 */       localSQLException.fillInStackTrace();
/* 1610 */       throw localSQLException;
/*      */     }
/*      */     
/* 1613 */     this.lastIndex = paramInt;
/*      */     
/* 1615 */     if (this.streamList != null) {
/* 1616 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1619 */     return ((Accessor)localObject).getDouble(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1629 */     if (this.closed)
/*      */     {
/* 1631 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1632 */       ((SQLException)localObject).fillInStackTrace();
/* 1633 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1636 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1639 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1640 */       ((SQLException)localObject).fillInStackTrace();
/* 1641 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1645 */     Object localObject = null;
/* 1646 */     if ((paramInt1 <= 0) || (paramInt1 > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt1 - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1651 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1652 */       localSQLException.fillInStackTrace();
/* 1653 */       throw localSQLException;
/*      */     }
/*      */     
/* 1656 */     this.lastIndex = paramInt1;
/*      */     
/* 1658 */     if (this.streamList != null) {
/* 1659 */       closeUsedStreams(paramInt1);
/*      */     }
/*      */     
/* 1662 */     return ((Accessor)localObject).getBigDecimal(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] getBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1672 */     if (this.closed)
/*      */     {
/* 1674 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1675 */       ((SQLException)localObject).fillInStackTrace();
/* 1676 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1679 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1682 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1683 */       ((SQLException)localObject).fillInStackTrace();
/* 1684 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1688 */     Object localObject = null;
/* 1689 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1694 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1695 */       localSQLException.fillInStackTrace();
/* 1696 */       throw localSQLException;
/*      */     }
/*      */     
/* 1699 */     this.lastIndex = paramInt;
/*      */     
/* 1701 */     if (this.streamList != null) {
/* 1702 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1705 */     return ((Accessor)localObject).getBytes(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] privateGetBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1715 */     if (this.closed)
/*      */     {
/* 1717 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1718 */       ((SQLException)localObject).fillInStackTrace();
/* 1719 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1722 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1725 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1726 */       ((SQLException)localObject).fillInStackTrace();
/* 1727 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1731 */     Object localObject = null;
/* 1732 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1737 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1738 */       localSQLException.fillInStackTrace();
/* 1739 */       throw localSQLException;
/*      */     }
/*      */     
/* 1742 */     this.lastIndex = paramInt;
/*      */     
/* 1744 */     if (this.streamList != null) {
/* 1745 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1748 */     return ((Accessor)localObject).privateGetBytes(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Date getDate(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1758 */     if (this.closed)
/*      */     {
/* 1760 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1761 */       ((SQLException)localObject).fillInStackTrace();
/* 1762 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1765 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1768 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1769 */       ((SQLException)localObject).fillInStackTrace();
/* 1770 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1774 */     Object localObject = null;
/* 1775 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1780 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1781 */       localSQLException.fillInStackTrace();
/* 1782 */       throw localSQLException;
/*      */     }
/*      */     
/* 1785 */     this.lastIndex = paramInt;
/*      */     
/* 1787 */     if (this.streamList != null) {
/* 1788 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1791 */     return ((Accessor)localObject).getDate(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time getTime(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1801 */     if (this.closed)
/*      */     {
/* 1803 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1804 */       ((SQLException)localObject).fillInStackTrace();
/* 1805 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1808 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1811 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1812 */       ((SQLException)localObject).fillInStackTrace();
/* 1813 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1817 */     Object localObject = null;
/* 1818 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1823 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1824 */       localSQLException.fillInStackTrace();
/* 1825 */       throw localSQLException;
/*      */     }
/*      */     
/* 1828 */     this.lastIndex = paramInt;
/*      */     
/* 1830 */     if (this.streamList != null) {
/* 1831 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1834 */     return ((Accessor)localObject).getTime(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1844 */     if (this.closed)
/*      */     {
/* 1846 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1847 */       ((SQLException)localObject).fillInStackTrace();
/* 1848 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1851 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1854 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1855 */       ((SQLException)localObject).fillInStackTrace();
/* 1856 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1860 */     Object localObject = null;
/* 1861 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1866 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1867 */       localSQLException.fillInStackTrace();
/* 1868 */       throw localSQLException;
/*      */     }
/*      */     
/* 1871 */     this.lastIndex = paramInt;
/*      */     
/* 1873 */     if (this.streamList != null) {
/* 1874 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1877 */     return ((Accessor)localObject).getTimestamp(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getAsciiStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1887 */     if (this.closed)
/*      */     {
/* 1889 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1890 */       ((SQLException)localObject).fillInStackTrace();
/* 1891 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1894 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1897 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1898 */       ((SQLException)localObject).fillInStackTrace();
/* 1899 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1903 */     Object localObject = null;
/* 1904 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1909 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1910 */       localSQLException.fillInStackTrace();
/* 1911 */       throw localSQLException;
/*      */     }
/*      */     
/* 1914 */     this.lastIndex = paramInt;
/*      */     
/* 1916 */     if (this.streamList != null) {
/* 1917 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1920 */     return ((Accessor)localObject).getAsciiStream(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getUnicodeStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1930 */     if (this.closed)
/*      */     {
/* 1932 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1933 */       ((SQLException)localObject).fillInStackTrace();
/* 1934 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1937 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1940 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1941 */       ((SQLException)localObject).fillInStackTrace();
/* 1942 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1946 */     Object localObject = null;
/* 1947 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1952 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1953 */       localSQLException.fillInStackTrace();
/* 1954 */       throw localSQLException;
/*      */     }
/*      */     
/* 1957 */     this.lastIndex = paramInt;
/*      */     
/* 1959 */     if (this.streamList != null) {
/* 1960 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 1963 */     return ((Accessor)localObject).getUnicodeStream(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getBinaryStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1973 */     if (this.closed)
/*      */     {
/* 1975 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1976 */       ((SQLException)localObject).fillInStackTrace();
/* 1977 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1980 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 1983 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 1984 */       ((SQLException)localObject).fillInStackTrace();
/* 1985 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1989 */     Object localObject = null;
/* 1990 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1995 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1996 */       localSQLException.fillInStackTrace();
/* 1997 */       throw localSQLException;
/*      */     }
/*      */     
/* 2000 */     this.lastIndex = paramInt;
/*      */     
/* 2002 */     if (this.streamList != null) {
/* 2003 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2006 */     return ((Accessor)localObject).getBinaryStream(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2016 */     if (this.closed)
/*      */     {
/* 2018 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2019 */       ((SQLException)localObject).fillInStackTrace();
/* 2020 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2023 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2026 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2027 */       ((SQLException)localObject).fillInStackTrace();
/* 2028 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2032 */     Object localObject = null;
/* 2033 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2038 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2039 */       localSQLException.fillInStackTrace();
/* 2040 */       throw localSQLException;
/*      */     }
/*      */     
/* 2043 */     this.lastIndex = paramInt;
/*      */     
/* 2045 */     if (this.streamList != null) {
/* 2046 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2049 */     return ((Accessor)localObject).getObject(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getAnyDataEmbeddedObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2058 */     Object localObject1 = null;
/* 2059 */     Object localObject2 = getObject(paramInt);
/* 2060 */     if ((localObject2 instanceof ANYDATA))
/*      */     {
/* 2062 */       Datum localDatum = ((ANYDATA)localObject2).accessDatum();
/* 2063 */       if (localDatum != null) localObject1 = localDatum.toJdbc();
/*      */     }
/* 2065 */     return localObject1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getCustomDatum(int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */     throws SQLException
/*      */   {
/* 2075 */     if (this.closed)
/*      */     {
/* 2077 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2078 */       ((SQLException)localObject).fillInStackTrace();
/* 2079 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2082 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2085 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2086 */       ((SQLException)localObject).fillInStackTrace();
/* 2087 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2091 */     Object localObject = null;
/* 2092 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2097 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2098 */       localSQLException.fillInStackTrace();
/* 2099 */       throw localSQLException;
/*      */     }
/*      */     
/* 2102 */     this.lastIndex = paramInt;
/*      */     
/* 2104 */     if (this.streamList != null) {
/* 2105 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2108 */     return ((Accessor)localObject).getCustomDatum(this.currentRank, paramCustomDatumFactory);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getObject(int paramInt, OracleDataFactory paramOracleDataFactory)
/*      */     throws SQLException
/*      */   {
/* 2117 */     if (this.closed)
/*      */     {
/* 2119 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2120 */       ((SQLException)localObject).fillInStackTrace();
/* 2121 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2124 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2127 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2128 */       ((SQLException)localObject).fillInStackTrace();
/* 2129 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2133 */     Object localObject = null;
/* 2134 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2139 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2140 */       localSQLException.fillInStackTrace();
/* 2141 */       throw localSQLException;
/*      */     }
/*      */     
/* 2144 */     this.lastIndex = paramInt;
/*      */     
/* 2146 */     if (this.streamList != null) {
/* 2147 */       closeUsedStreams(paramInt);
/*      */     }
/* 2149 */     return ((Accessor)localObject).getObject(this.currentRank, paramOracleDataFactory);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getORAData(int paramInt, ORADataFactory paramORADataFactory)
/*      */     throws SQLException
/*      */   {
/* 2159 */     if (this.closed)
/*      */     {
/* 2161 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2162 */       ((SQLException)localObject).fillInStackTrace();
/* 2163 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2166 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2169 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2170 */       ((SQLException)localObject).fillInStackTrace();
/* 2171 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2175 */     Object localObject = null;
/* 2176 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2181 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2182 */       localSQLException.fillInStackTrace();
/* 2183 */       throw localSQLException;
/*      */     }
/*      */     
/* 2186 */     this.lastIndex = paramInt;
/*      */     
/* 2188 */     if (this.streamList != null) {
/* 2189 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2192 */     return ((Accessor)localObject).getORAData(this.currentRank, paramORADataFactory);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ResultSet getCursor(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2202 */     if (this.closed)
/*      */     {
/* 2204 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2205 */       ((SQLException)localObject).fillInStackTrace();
/* 2206 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2209 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2212 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2213 */       ((SQLException)localObject).fillInStackTrace();
/* 2214 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2218 */     Object localObject = null;
/* 2219 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2224 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2225 */       localSQLException.fillInStackTrace();
/* 2226 */       throw localSQLException;
/*      */     }
/*      */     
/* 2229 */     this.lastIndex = paramInt;
/*      */     
/* 2231 */     if (this.streamList != null) {
/* 2232 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2235 */     return ((Accessor)localObject).getCursor(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */   public void clearParameters()
/*      */     throws SQLException
/*      */   {
/* 2242 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 2245 */       super.clearParameters();
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
/*      */   public Object getObject(int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 2266 */     if (this.closed)
/*      */     {
/* 2268 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2269 */       ((SQLException)localObject).fillInStackTrace();
/* 2270 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2273 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2276 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2277 */       ((SQLException)localObject).fillInStackTrace();
/* 2278 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2282 */     Object localObject = null;
/* 2283 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2288 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2289 */       localSQLException.fillInStackTrace();
/* 2290 */       throw localSQLException;
/*      */     }
/*      */     
/* 2293 */     this.lastIndex = paramInt;
/*      */     
/* 2295 */     if (this.streamList != null) {
/* 2296 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2299 */     return ((Accessor)localObject).getObject(this.currentRank, paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Ref getRef(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2309 */     if (this.closed)
/*      */     {
/* 2311 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2312 */       ((SQLException)localObject).fillInStackTrace();
/* 2313 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2316 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2319 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2320 */       ((SQLException)localObject).fillInStackTrace();
/* 2321 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2325 */     Object localObject = null;
/* 2326 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2331 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2332 */       localSQLException.fillInStackTrace();
/* 2333 */       throw localSQLException;
/*      */     }
/*      */     
/* 2336 */     this.lastIndex = paramInt;
/*      */     
/* 2338 */     if (this.streamList != null) {
/* 2339 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2342 */     return ((Accessor)localObject).getREF(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Blob getBlob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2352 */     if (this.closed)
/*      */     {
/* 2354 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2355 */       ((SQLException)localObject).fillInStackTrace();
/* 2356 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2359 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2362 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2363 */       ((SQLException)localObject).fillInStackTrace();
/* 2364 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2368 */     Object localObject = null;
/* 2369 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2374 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2375 */       localSQLException.fillInStackTrace();
/* 2376 */       throw localSQLException;
/*      */     }
/*      */     
/* 2379 */     this.lastIndex = paramInt;
/*      */     
/* 2381 */     if (this.streamList != null) {
/* 2382 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2385 */     return ((Accessor)localObject).getBLOB(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Clob getClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2395 */     if (this.closed)
/*      */     {
/* 2397 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2398 */       ((SQLException)localObject).fillInStackTrace();
/* 2399 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2402 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2405 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2406 */       ((SQLException)localObject).fillInStackTrace();
/* 2407 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2411 */     Object localObject = null;
/* 2412 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2417 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2418 */       localSQLException.fillInStackTrace();
/* 2419 */       throw localSQLException;
/*      */     }
/*      */     
/* 2422 */     this.lastIndex = paramInt;
/*      */     
/* 2424 */     if (this.streamList != null) {
/* 2425 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2428 */     return ((Accessor)localObject).getCLOB(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Array getArray(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2438 */     if (this.closed)
/*      */     {
/* 2440 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2441 */       ((SQLException)localObject).fillInStackTrace();
/* 2442 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2445 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2448 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2449 */       ((SQLException)localObject).fillInStackTrace();
/* 2450 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2454 */     Object localObject = null;
/* 2455 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2460 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2461 */       localSQLException.fillInStackTrace();
/* 2462 */       throw localSQLException;
/*      */     }
/*      */     
/* 2465 */     this.lastIndex = paramInt;
/*      */     
/* 2467 */     if (this.streamList != null) {
/* 2468 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2471 */     return ((Accessor)localObject).getARRAY(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2481 */     if (this.closed)
/*      */     {
/* 2483 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2484 */       ((SQLException)localObject).fillInStackTrace();
/* 2485 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2488 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2491 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2492 */       ((SQLException)localObject).fillInStackTrace();
/* 2493 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2497 */     Object localObject = null;
/* 2498 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2503 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2504 */       localSQLException.fillInStackTrace();
/* 2505 */       throw localSQLException;
/*      */     }
/*      */     
/* 2508 */     this.lastIndex = paramInt;
/*      */     
/* 2510 */     if (this.streamList != null) {
/* 2511 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2514 */     return ((Accessor)localObject).getBigDecimal(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Date getDate(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 2524 */     if (this.closed)
/*      */     {
/* 2526 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2527 */       ((SQLException)localObject).fillInStackTrace();
/* 2528 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2531 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2534 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2535 */       ((SQLException)localObject).fillInStackTrace();
/* 2536 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2540 */     Object localObject = null;
/* 2541 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2546 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2547 */       localSQLException.fillInStackTrace();
/* 2548 */       throw localSQLException;
/*      */     }
/*      */     
/* 2551 */     this.lastIndex = paramInt;
/*      */     
/* 2553 */     if (this.streamList != null) {
/* 2554 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2557 */     return ((Accessor)localObject).getDate(this.currentRank, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time getTime(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 2567 */     if (this.closed)
/*      */     {
/* 2569 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2570 */       ((SQLException)localObject).fillInStackTrace();
/* 2571 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2574 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2577 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2578 */       ((SQLException)localObject).fillInStackTrace();
/* 2579 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2583 */     Object localObject = null;
/* 2584 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2589 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2590 */       localSQLException.fillInStackTrace();
/* 2591 */       throw localSQLException;
/*      */     }
/*      */     
/* 2594 */     this.lastIndex = paramInt;
/*      */     
/* 2596 */     if (this.streamList != null) {
/* 2597 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2600 */     return ((Accessor)localObject).getTime(this.currentRank, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 2610 */     if (this.closed)
/*      */     {
/* 2612 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2613 */       ((SQLException)localObject).fillInStackTrace();
/* 2614 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2617 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2620 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2621 */       ((SQLException)localObject).fillInStackTrace();
/* 2622 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2626 */     Object localObject = null;
/* 2627 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2632 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2633 */       localSQLException.fillInStackTrace();
/* 2634 */       throw localSQLException;
/*      */     }
/*      */     
/* 2637 */     this.lastIndex = paramInt;
/*      */     
/* 2639 */     if (this.streamList != null) {
/* 2640 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2643 */     return ((Accessor)localObject).getTimestamp(this.currentRank, paramCalendar);
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
/*      */   public void addBatch()
/*      */     throws SQLException
/*      */   {
/* 2689 */     if (this.currentRowBindAccessors != null)
/*      */     {
/*      */ 
/* 2692 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Stored procedure with out or inout parameters cannot be batched");
/* 2693 */       localSQLException.fillInStackTrace();
/* 2694 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 2698 */     super.addBatch();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void alwaysOnClose()
/*      */     throws SQLException
/*      */   {
/* 2708 */     this.sqlObject.resetNamedParameters();
/*      */     
/*      */ 
/* 2711 */     this.namedParameters = new String[8];
/* 2712 */     this.parameterCount = 0;
/* 2713 */     this.atLeastOneOrdinalParameter = false;
/* 2714 */     this.atLeastOneNamedParameter = false;
/*      */     
/* 2716 */     super.alwaysOnClose();
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
/*      */   public void registerOutParameter(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2753 */     registerOutParameterInternal(paramString, paramInt, 0, -1, null);
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
/*      */   public void registerOutParameter(String paramString, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2786 */     registerOutParameterInternal(paramString, paramInt1, paramInt2, -1, null);
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
/*      */   public void registerOutParameter(String paramString1, int paramInt, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 2831 */     registerOutParameterInternal(paramString1, paramInt, 0, -1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void registerOutParameterInternal(String paramString1, int paramInt1, int paramInt2, int paramInt3, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 2839 */     int i = addNamedPara(paramString1);
/* 2840 */     registerOutParameterInternal(i, paramInt1, paramInt2, paramInt3, paramString2);
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
/*      */   public URL getURL(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2865 */     if (this.closed)
/*      */     {
/* 2867 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 2868 */       ((SQLException)localObject).fillInStackTrace();
/* 2869 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2872 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 2875 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 2876 */       ((SQLException)localObject).fillInStackTrace();
/* 2877 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 2881 */     Object localObject = null;
/* 2882 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2887 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 2888 */       localSQLException.fillInStackTrace();
/* 2889 */       throw localSQLException;
/*      */     }
/*      */     
/* 2892 */     this.lastIndex = paramInt;
/*      */     
/* 2894 */     if (this.streamList != null) {
/* 2895 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 2898 */     return ((Accessor)localObject).getURL(this.currentRank);
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
/*      */   public void setStringForClob(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 2925 */     int i = addNamedPara(paramString1);
/* 2926 */     if ((paramString2 == null) || (paramString2.length() == 0))
/*      */     {
/* 2928 */       setNull(i, 2005);
/* 2929 */       return;
/*      */     }
/* 2931 */     setStringForClob(i, paramString2);
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
/*      */   public void setStringForClob(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 2950 */     if ((paramString == null) || (paramString.length() == 0))
/*      */     {
/* 2952 */       setNull(paramInt, 2005);
/* 2953 */       return;
/*      */     }
/* 2955 */     synchronized (this.connection) {
/* 2956 */       setStringForClobCritical(paramInt, paramString);
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
/*      */   public void setBytesForBlob(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 2981 */     int i = addNamedPara(paramString);
/* 2982 */     setBytesForBlob(i, paramArrayOfByte);
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
/*      */   public void setBytesForBlob(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 3000 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
/*      */     {
/* 3002 */       setNull(paramInt, 2004);
/* 3003 */       return;
/*      */     }
/* 3005 */     synchronized (this.connection) {
/* 3006 */       setBytesForBlobCritical(paramInt, paramArrayOfByte);
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
/*      */   public String getString(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3035 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3038 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3039 */       ((SQLException)localObject).fillInStackTrace();
/* 3040 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3044 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3047 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3049 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3052 */     i++;
/*      */     
/* 3054 */     Accessor localAccessor = null;
/* 3055 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3060 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3061 */       localSQLException.fillInStackTrace();
/* 3062 */       throw localSQLException;
/*      */     }
/*      */     
/* 3065 */     this.lastIndex = i;
/*      */     
/* 3067 */     if (this.streamList != null) {
/* 3068 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3071 */     return localAccessor.getString(this.currentRank);
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
/*      */   public boolean getBoolean(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3092 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3095 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3096 */       ((SQLException)localObject).fillInStackTrace();
/* 3097 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3101 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3104 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3106 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3109 */     i++;
/*      */     
/* 3111 */     Accessor localAccessor = null;
/* 3112 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3117 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3118 */       localSQLException.fillInStackTrace();
/* 3119 */       throw localSQLException;
/*      */     }
/*      */     
/* 3122 */     this.lastIndex = i;
/*      */     
/* 3124 */     if (this.streamList != null) {
/* 3125 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3128 */     return localAccessor.getBoolean(this.currentRank);
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
/*      */   public byte getByte(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3149 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3152 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3153 */       ((SQLException)localObject).fillInStackTrace();
/* 3154 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3158 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3161 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3163 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3166 */     i++;
/*      */     
/* 3168 */     Accessor localAccessor = null;
/* 3169 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3174 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3175 */       localSQLException.fillInStackTrace();
/* 3176 */       throw localSQLException;
/*      */     }
/*      */     
/* 3179 */     this.lastIndex = i;
/*      */     
/* 3181 */     if (this.streamList != null) {
/* 3182 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3185 */     return localAccessor.getByte(this.currentRank);
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
/*      */   public short getShort(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3206 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3209 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3210 */       ((SQLException)localObject).fillInStackTrace();
/* 3211 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3215 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3218 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3220 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3223 */     i++;
/*      */     
/* 3225 */     Accessor localAccessor = null;
/* 3226 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3231 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3232 */       localSQLException.fillInStackTrace();
/* 3233 */       throw localSQLException;
/*      */     }
/*      */     
/* 3236 */     this.lastIndex = i;
/*      */     
/* 3238 */     if (this.streamList != null) {
/* 3239 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3242 */     return localAccessor.getShort(this.currentRank);
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
/*      */   public int getInt(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3264 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3267 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3268 */       ((SQLException)localObject).fillInStackTrace();
/* 3269 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3273 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3276 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3278 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3281 */     i++;
/*      */     
/* 3283 */     Accessor localAccessor = null;
/* 3284 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3289 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3290 */       localSQLException.fillInStackTrace();
/* 3291 */       throw localSQLException;
/*      */     }
/*      */     
/* 3294 */     this.lastIndex = i;
/*      */     
/* 3296 */     if (this.streamList != null) {
/* 3297 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3300 */     return localAccessor.getInt(this.currentRank);
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
/*      */   public long getLong(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3322 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3325 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3326 */       ((SQLException)localObject).fillInStackTrace();
/* 3327 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3331 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3334 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3336 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3339 */     i++;
/*      */     
/* 3341 */     Accessor localAccessor = null;
/* 3342 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3347 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3348 */       localSQLException.fillInStackTrace();
/* 3349 */       throw localSQLException;
/*      */     }
/*      */     
/* 3352 */     this.lastIndex = i;
/*      */     
/* 3354 */     if (this.streamList != null) {
/* 3355 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3358 */     return localAccessor.getLong(this.currentRank);
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
/*      */   public float getFloat(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3379 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3382 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3383 */       ((SQLException)localObject).fillInStackTrace();
/* 3384 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3388 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3391 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3393 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3396 */     i++;
/*      */     
/* 3398 */     Accessor localAccessor = null;
/* 3399 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3404 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3405 */       localSQLException.fillInStackTrace();
/* 3406 */       throw localSQLException;
/*      */     }
/*      */     
/* 3409 */     this.lastIndex = i;
/*      */     
/* 3411 */     if (this.streamList != null) {
/* 3412 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3415 */     return localAccessor.getFloat(this.currentRank);
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
/*      */   public double getDouble(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3436 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3439 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3440 */       ((SQLException)localObject).fillInStackTrace();
/* 3441 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3445 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3448 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3450 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3453 */     i++;
/*      */     
/* 3455 */     Accessor localAccessor = null;
/* 3456 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3461 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3462 */       localSQLException.fillInStackTrace();
/* 3463 */       throw localSQLException;
/*      */     }
/*      */     
/* 3466 */     this.lastIndex = i;
/*      */     
/* 3468 */     if (this.streamList != null) {
/* 3469 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3472 */     return localAccessor.getDouble(this.currentRank);
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
/*      */   public byte[] getBytes(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3494 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3497 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3498 */       ((SQLException)localObject).fillInStackTrace();
/* 3499 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3503 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3506 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3508 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3511 */     i++;
/*      */     
/* 3513 */     Accessor localAccessor = null;
/* 3514 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3519 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3520 */       localSQLException.fillInStackTrace();
/* 3521 */       throw localSQLException;
/*      */     }
/*      */     
/* 3524 */     this.lastIndex = i;
/*      */     
/* 3526 */     if (this.streamList != null) {
/* 3527 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3530 */     return localAccessor.getBytes(this.currentRank);
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
/*      */   public Date getDate(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3551 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3554 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3555 */       ((SQLException)localObject).fillInStackTrace();
/* 3556 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3560 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3563 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3565 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3568 */     i++;
/*      */     
/* 3570 */     Accessor localAccessor = null;
/* 3571 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3576 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3577 */       localSQLException.fillInStackTrace();
/* 3578 */       throw localSQLException;
/*      */     }
/*      */     
/* 3581 */     this.lastIndex = i;
/*      */     
/* 3583 */     if (this.streamList != null) {
/* 3584 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3587 */     return localAccessor.getDate(this.currentRank);
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
/*      */   public Time getTime(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3608 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3611 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3612 */       ((SQLException)localObject).fillInStackTrace();
/* 3613 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3617 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3620 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3622 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3625 */     i++;
/*      */     
/* 3627 */     Accessor localAccessor = null;
/* 3628 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3633 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3634 */       localSQLException.fillInStackTrace();
/* 3635 */       throw localSQLException;
/*      */     }
/*      */     
/* 3638 */     this.lastIndex = i;
/*      */     
/* 3640 */     if (this.streamList != null) {
/* 3641 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3644 */     return localAccessor.getTime(this.currentRank);
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
/*      */   public Timestamp getTimestamp(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3665 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3668 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3669 */       ((SQLException)localObject).fillInStackTrace();
/* 3670 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3674 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3677 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3679 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3682 */     i++;
/*      */     
/* 3684 */     Accessor localAccessor = null;
/* 3685 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3690 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3691 */       localSQLException.fillInStackTrace();
/* 3692 */       throw localSQLException;
/*      */     }
/*      */     
/* 3695 */     this.lastIndex = i;
/*      */     
/* 3697 */     if (this.streamList != null) {
/* 3698 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3701 */     return localAccessor.getTimestamp(this.currentRank);
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
/*      */   public Object getObject(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3729 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3732 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3733 */       ((SQLException)localObject).fillInStackTrace();
/* 3734 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3738 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3741 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3743 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3746 */     i++;
/*      */     
/* 3748 */     Accessor localAccessor = null;
/* 3749 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3754 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3755 */       localSQLException.fillInStackTrace();
/* 3756 */       throw localSQLException;
/*      */     }
/*      */     
/* 3759 */     this.lastIndex = i;
/*      */     
/* 3761 */     if (this.streamList != null) {
/* 3762 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3765 */     return localAccessor.getObject(this.currentRank);
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
/*      */   public BigDecimal getBigDecimal(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3787 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3790 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3791 */       ((SQLException)localObject).fillInStackTrace();
/* 3792 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3796 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3799 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3801 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3804 */     i++;
/*      */     
/* 3806 */     Accessor localAccessor = null;
/* 3807 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3812 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3813 */       localSQLException.fillInStackTrace();
/* 3814 */       throw localSQLException;
/*      */     }
/*      */     
/* 3817 */     this.lastIndex = i;
/*      */     
/* 3819 */     if (this.streamList != null) {
/* 3820 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3823 */     return localAccessor.getBigDecimal(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3832 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3835 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3836 */       ((SQLException)localObject).fillInStackTrace();
/* 3837 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3841 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3844 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3846 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3849 */     i++;
/*      */     
/* 3851 */     Accessor localAccessor = null;
/* 3852 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3857 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3858 */       localSQLException.fillInStackTrace();
/* 3859 */       throw localSQLException;
/*      */     }
/*      */     
/* 3862 */     this.lastIndex = i;
/*      */     
/* 3864 */     if (this.streamList != null) {
/* 3865 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3868 */     return localAccessor.getBigDecimal(this.currentRank, paramInt);
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
/*      */   public Object getObject(String paramString, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 3896 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3899 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3900 */       ((SQLException)localObject).fillInStackTrace();
/* 3901 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3905 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3908 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3910 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3913 */     i++;
/*      */     
/* 3915 */     Accessor localAccessor = null;
/* 3916 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3921 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3922 */       localSQLException.fillInStackTrace();
/* 3923 */       throw localSQLException;
/*      */     }
/*      */     
/* 3926 */     this.lastIndex = i;
/*      */     
/* 3928 */     if (this.streamList != null) {
/* 3929 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3932 */     return localAccessor.getObject(this.currentRank, paramMap);
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
/*      */   public Ref getRef(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3954 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 3957 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 3958 */       ((SQLException)localObject).fillInStackTrace();
/* 3959 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 3963 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 3966 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 3968 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 3971 */     i++;
/*      */     
/* 3973 */     Accessor localAccessor = null;
/* 3974 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3979 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 3980 */       localSQLException.fillInStackTrace();
/* 3981 */       throw localSQLException;
/*      */     }
/*      */     
/* 3984 */     this.lastIndex = i;
/*      */     
/* 3986 */     if (this.streamList != null) {
/* 3987 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 3990 */     return localAccessor.getREF(this.currentRank);
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
/*      */   public Blob getBlob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 4012 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 4015 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 4016 */       ((SQLException)localObject).fillInStackTrace();
/* 4017 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 4021 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 4024 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 4026 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 4029 */     i++;
/*      */     
/* 4031 */     Accessor localAccessor = null;
/* 4032 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 4037 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 4038 */       localSQLException.fillInStackTrace();
/* 4039 */       throw localSQLException;
/*      */     }
/*      */     
/* 4042 */     this.lastIndex = i;
/*      */     
/* 4044 */     if (this.streamList != null) {
/* 4045 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 4048 */     return localAccessor.getBLOB(this.currentRank);
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
/*      */   public Clob getClob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 4069 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 4072 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 4073 */       ((SQLException)localObject).fillInStackTrace();
/* 4074 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 4078 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 4081 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 4083 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 4086 */     i++;
/*      */     
/* 4088 */     Accessor localAccessor = null;
/* 4089 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 4094 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 4095 */       localSQLException.fillInStackTrace();
/* 4096 */       throw localSQLException;
/*      */     }
/*      */     
/* 4099 */     this.lastIndex = i;
/*      */     
/* 4101 */     if (this.streamList != null) {
/* 4102 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 4105 */     return localAccessor.getCLOB(this.currentRank);
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
/*      */   public Array getArray(String paramString)
/*      */     throws SQLException
/*      */   {
/* 4127 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 4130 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 4131 */       ((SQLException)localObject).fillInStackTrace();
/* 4132 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 4136 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 4139 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 4141 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 4144 */     i++;
/*      */     
/* 4146 */     Accessor localAccessor = null;
/* 4147 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 4152 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 4153 */       localSQLException.fillInStackTrace();
/* 4154 */       throw localSQLException;
/*      */     }
/*      */     
/* 4157 */     this.lastIndex = i;
/*      */     
/* 4159 */     if (this.streamList != null) {
/* 4160 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 4163 */     return localAccessor.getARRAY(this.currentRank);
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
/*      */   public Date getDate(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 4193 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 4196 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 4197 */       ((SQLException)localObject).fillInStackTrace();
/* 4198 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 4202 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 4205 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 4207 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 4210 */     i++;
/*      */     
/* 4212 */     Accessor localAccessor = null;
/* 4213 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 4218 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 4219 */       localSQLException.fillInStackTrace();
/* 4220 */       throw localSQLException;
/*      */     }
/*      */     
/* 4223 */     this.lastIndex = i;
/*      */     
/* 4225 */     if (this.streamList != null) {
/* 4226 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 4229 */     return localAccessor.getDate(this.currentRank, paramCalendar);
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
/*      */   public Time getTime(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 4259 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 4262 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 4263 */       ((SQLException)localObject).fillInStackTrace();
/* 4264 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 4268 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 4271 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 4273 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 4276 */     i++;
/*      */     
/* 4278 */     Accessor localAccessor = null;
/* 4279 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 4284 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 4285 */       localSQLException.fillInStackTrace();
/* 4286 */       throw localSQLException;
/*      */     }
/*      */     
/* 4289 */     this.lastIndex = i;
/*      */     
/* 4291 */     if (this.streamList != null) {
/* 4292 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 4295 */     return localAccessor.getTime(this.currentRank, paramCalendar);
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
/*      */   public Timestamp getTimestamp(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 4326 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 4329 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 4330 */       ((SQLException)localObject).fillInStackTrace();
/* 4331 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 4335 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 4338 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 4340 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 4343 */     i++;
/*      */     
/* 4345 */     Accessor localAccessor = null;
/* 4346 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 4351 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 4352 */       localSQLException.fillInStackTrace();
/* 4353 */       throw localSQLException;
/*      */     }
/*      */     
/* 4356 */     this.lastIndex = i;
/*      */     
/* 4358 */     if (this.streamList != null) {
/* 4359 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 4362 */     return localAccessor.getTimestamp(this.currentRank, paramCalendar);
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
/*      */   public URL getURL(String paramString)
/*      */     throws SQLException
/*      */   {
/* 4386 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 4389 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 4390 */       ((SQLException)localObject).fillInStackTrace();
/* 4391 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 4395 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 4398 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 4400 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 4403 */     i++;
/*      */     
/* 4405 */     Accessor localAccessor = null;
/* 4406 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 4411 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 4412 */       localSQLException.fillInStackTrace();
/* 4413 */       throw localSQLException;
/*      */     }
/*      */     
/* 4416 */     this.lastIndex = i;
/*      */     
/* 4418 */     if (this.streamList != null) {
/* 4419 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 4422 */     return localAccessor.getURL(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public InputStream getAsciiStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 4432 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 4433 */     localSQLException.fillInStackTrace();
/* 4434 */     throw localSQLException;
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
/*      */   public void registerIndexTableOutParameter(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/* 4473 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 4476 */       int i = paramInt1 - 1;
/* 4477 */       if ((i < 0) || (paramInt1 > this.numberOfBindPositions))
/*      */       {
/* 4479 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 4480 */         localSQLException.fillInStackTrace();
/* 4481 */         throw localSQLException;
/*      */       }
/*      */       
/* 4484 */       int j = getInternalType(paramInt3);
/*      */       
/* 4486 */       resetBatch();
/* 4487 */       this.currentRowNeedToPrepareBinds = true;
/*      */       
/* 4489 */       if (this.currentRowBindAccessors == null) {
/* 4490 */         this.currentRowBindAccessors = new Accessor[this.numberOfBindPositions];
/*      */       }
/* 4492 */       this.currentRowBindAccessors[i] = allocateIndexTableAccessor(paramInt3, j, paramInt4, paramInt2, this.currentRowFormOfUse[i], true);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4500 */       this.hasIbtBind = true;
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
/*      */   PlsqlIndexTableAccessor allocateIndexTableAccessor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, short paramShort, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 4517 */     return new PlsqlIndexTableAccessor(this, paramInt1, paramInt2, paramInt3, paramInt4, paramShort, paramBoolean);
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
/*      */   public Object getPlsqlIndexTable(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4541 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 4544 */       Datum[] arrayOfDatum = getOraclePlsqlIndexTable(paramInt);
/*      */       
/* 4546 */       PlsqlIndexTableAccessor localPlsqlIndexTableAccessor = (PlsqlIndexTableAccessor)this.outBindAccessors[(paramInt - 1)];
/*      */       
/*      */ 
/* 4549 */       int i = localPlsqlIndexTableAccessor.elementInternalType;
/*      */       
/* 4551 */       Object localObject1 = null;
/*      */       
/* 4553 */       switch (i)
/*      */       {
/*      */       case 9: 
/* 4556 */         localObject1 = new String[arrayOfDatum.length];
/* 4557 */         break;
/*      */       case 6: 
/* 4559 */         localObject1 = new BigDecimal[arrayOfDatum.length];
/* 4560 */         break;
/*      */       
/*      */       default: 
/* 4563 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Invalid column type");
/* 4564 */         localSQLException.fillInStackTrace();
/* 4565 */         throw localSQLException;
/*      */       }
/*      */       
/*      */       
/* 4569 */       for (int j = 0; j < localObject1.length; j++) {
/* 4570 */         localObject1[j] = ((arrayOfDatum[j] != null) && (arrayOfDatum[j].getLength() != 0L) ? arrayOfDatum[j].toJdbc() : null);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 4575 */       return localObject1;
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
/*      */   public Object getPlsqlIndexTable(int paramInt, Class paramClass)
/*      */     throws SQLException
/*      */   {
/* 4595 */     synchronized (this.connection)
/*      */     {
/* 4597 */       Datum[] arrayOfDatum = getOraclePlsqlIndexTable(paramInt);
/*      */       
/* 4599 */       if ((paramClass == null) || (!paramClass.isPrimitive()))
/*      */       {
/* 4601 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 4602 */         ((SQLException)localObject1).fillInStackTrace();
/* 4603 */         throw ((Throwable)localObject1);
/*      */       }
/*      */       
/* 4606 */       Object localObject1 = paramClass.getName();
/*      */       int i;
/* 4608 */       if (((String)localObject1).equals("byte"))
/*      */       {
/* 4610 */         localObject2 = new byte[arrayOfDatum.length];
/* 4611 */         for (i = 0; i < arrayOfDatum.length; i++)
/* 4612 */           localObject2[i] = (arrayOfDatum[i] != null ? arrayOfDatum[i].byteValue() : 0);
/* 4613 */         return localObject2;
/*      */       }
/* 4615 */       if (((String)localObject1).equals("char"))
/*      */       {
/* 4617 */         localObject2 = new char[arrayOfDatum.length];
/* 4618 */         for (i = 0; i < arrayOfDatum.length; i++) {
/* 4619 */           localObject2[i] = ((arrayOfDatum[i] != null) && (arrayOfDatum[i].getLength() != 0L) ? (char)arrayOfDatum[i].intValue() : 0);
/*      */         }
/* 4621 */         return localObject2;
/*      */       }
/* 4623 */       if (((String)localObject1).equals("double"))
/*      */       {
/* 4625 */         localObject2 = new double[arrayOfDatum.length];
/* 4626 */         for (i = 0; i < arrayOfDatum.length; i++) {
/* 4627 */           localObject2[i] = ((arrayOfDatum[i] != null) && (arrayOfDatum[i].getLength() != 0L) ? arrayOfDatum[i].doubleValue() : 0.0D);
/*      */         }
/* 4629 */         return localObject2;
/*      */       }
/* 4631 */       if (((String)localObject1).equals("float"))
/*      */       {
/* 4633 */         localObject2 = new float[arrayOfDatum.length];
/* 4634 */         for (i = 0; i < arrayOfDatum.length; i++) {
/* 4635 */           localObject2[i] = ((arrayOfDatum[i] != null) && (arrayOfDatum[i].getLength() != 0L) ? arrayOfDatum[i].floatValue() : 0.0F);
/*      */         }
/* 4637 */         return localObject2;
/*      */       }
/* 4639 */       if (((String)localObject1).equals("int"))
/*      */       {
/* 4641 */         localObject2 = new int[arrayOfDatum.length];
/* 4642 */         for (i = 0; i < arrayOfDatum.length; i++) {
/* 4643 */           localObject2[i] = ((arrayOfDatum[i] != null) && (arrayOfDatum[i].getLength() != 0L) ? arrayOfDatum[i].intValue() : 0);
/*      */         }
/* 4645 */         return localObject2;
/*      */       }
/* 4647 */       if (((String)localObject1).equals("long"))
/*      */       {
/* 4649 */         localObject2 = new long[arrayOfDatum.length];
/* 4650 */         for (i = 0; i < arrayOfDatum.length; i++) {
/* 4651 */           localObject2[i] = ((arrayOfDatum[i] != null) && (arrayOfDatum[i].getLength() != 0L) ? arrayOfDatum[i].longValue() : 0L);
/*      */         }
/* 4653 */         return localObject2;
/*      */       }
/* 4655 */       if (((String)localObject1).equals("short"))
/*      */       {
/* 4657 */         localObject2 = new short[arrayOfDatum.length];
/* 4658 */         for (i = 0; i < arrayOfDatum.length; i++) {
/* 4659 */           localObject2[i] = ((arrayOfDatum[i] != null) && (arrayOfDatum[i].getLength() != 0L) ? (short)arrayOfDatum[i].intValue() : 0);
/*      */         }
/* 4661 */         return localObject2;
/*      */       }
/* 4663 */       if (((String)localObject1).equals("boolean"))
/*      */       {
/* 4665 */         localObject2 = new boolean[arrayOfDatum.length];
/* 4666 */         for (i = 0; i < arrayOfDatum.length; i++) {
/* 4667 */           localObject2[i] = ((arrayOfDatum[i] != null) && (arrayOfDatum[i].getLength() != 0L) ? arrayOfDatum[i].booleanValue() : 0);
/*      */         }
/* 4669 */         return localObject2;
/*      */       }
/*      */       
/*      */ 
/* 4673 */       Object localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23);
/* 4674 */       ((SQLException)localObject2).fillInStackTrace();
/* 4675 */       throw ((Throwable)localObject2);
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
/*      */   public Datum[] getOraclePlsqlIndexTable(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4693 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 4697 */       if (this.closed)
/*      */       {
/* 4699 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 4700 */         ((SQLException)localObject1).fillInStackTrace();
/* 4701 */         throw ((Throwable)localObject1);
/*      */       }
/*      */       
/* 4704 */       if (this.atLeastOneNamedParameter)
/*      */       {
/*      */ 
/* 4707 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 4708 */         ((SQLException)localObject1).fillInStackTrace();
/* 4709 */         throw ((Throwable)localObject1);
/*      */       }
/*      */       
/*      */ 
/* 4713 */       Object localObject1 = null;
/* 4714 */       if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject1 = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 4719 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 4720 */         localSQLException.fillInStackTrace();
/* 4721 */         throw localSQLException;
/*      */       }
/*      */       
/* 4724 */       this.lastIndex = paramInt;
/*      */       
/* 4726 */       if (this.streamList != null) {
/* 4727 */         closeUsedStreams(paramInt);
/*      */       }
/*      */       
/* 4730 */       return ((Accessor)localObject1).getOraclePlsqlIndexTable(this.currentRank);
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
/*      */   public boolean execute()
/*      */     throws SQLException
/*      */   {
/* 4745 */     synchronized (this.connection) {
/* 4746 */       ensureOpen();
/* 4747 */       if ((this.atLeastOneNamedParameter) && (this.atLeastOneOrdinalParameter))
/*      */       {
/* 4749 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 4750 */         localSQLException.fillInStackTrace();
/* 4751 */         throw localSQLException;
/*      */       }
/* 4753 */       if (this.sqlObject.setNamedParameters(this.parameterCount, this.namedParameters))
/* 4754 */         this.needToParse = true;
/* 4755 */       return super.execute();
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
/*      */   public int executeUpdate()
/*      */     throws SQLException
/*      */   {
/* 4769 */     synchronized (this.connection)
/*      */     {
/* 4771 */       ensureOpen();
/* 4772 */       if ((this.atLeastOneNamedParameter) && (this.atLeastOneOrdinalParameter))
/*      */       {
/* 4774 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 4775 */         localSQLException.fillInStackTrace();
/* 4776 */         throw localSQLException;
/*      */       }
/* 4778 */       if (this.sqlObject.setNamedParameters(this.parameterCount, this.namedParameters))
/* 4779 */         this.needToParse = true;
/* 4780 */       return super.executeUpdate();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void releaseBuffers()
/*      */   {
/* 4787 */     if (this.outBindAccessors != null)
/*      */     {
/* 4789 */       int i = this.outBindAccessors.length;
/*      */       
/* 4791 */       for (int j = 0; j < i; j++)
/*      */       {
/* 4793 */         if (this.outBindAccessors[j] != null)
/*      */         {
/* 4795 */           this.outBindAccessors[j].rowSpaceByte = null;
/* 4796 */           this.outBindAccessors[j].rowSpaceChar = null;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 4801 */     super.releaseBuffers();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void doLocalInitialization()
/*      */   {
/* 4808 */     if (this.outBindAccessors != null)
/*      */     {
/* 4810 */       int i = this.outBindAccessors.length;
/*      */       
/* 4812 */       for (int j = 0; j < i; j++)
/*      */       {
/* 4814 */         if (this.outBindAccessors[j] != null)
/*      */         {
/* 4816 */           this.outBindAccessors[j].rowSpaceByte = this.bindBytes;
/* 4817 */           this.outBindAccessors[j].rowSpaceChar = this.bindChars;
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
/*      */ 
/*      */   public void setArray(int paramInt, Array paramArray)
/*      */     throws SQLException
/*      */   {
/* 4832 */     this.atLeastOneOrdinalParameter = true;
/* 4833 */     setArrayInternal(paramInt, paramArray);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBigDecimal(int paramInt, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/* 4840 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 4844 */       this.atLeastOneOrdinalParameter = true;
/* 4845 */       setBigDecimalInternal(paramInt, paramBigDecimal);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBlob(int paramInt, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/* 4853 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 4857 */       this.atLeastOneOrdinalParameter = true;
/* 4858 */       setBlobInternal(paramInt, paramBlob);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBoolean(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 4866 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 4870 */       this.atLeastOneOrdinalParameter = true;
/* 4871 */       setBooleanInternal(paramInt, paramBoolean);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setByte(int paramInt, byte paramByte)
/*      */     throws SQLException
/*      */   {
/* 4879 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 4883 */       this.atLeastOneOrdinalParameter = true;
/* 4884 */       setByteInternal(paramInt, paramByte);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBytes(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 4892 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 4896 */       this.atLeastOneOrdinalParameter = true;
/* 4897 */       setBytesInternal(paramInt, paramArrayOfByte);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setClob(int paramInt, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/* 4905 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 4909 */       this.atLeastOneOrdinalParameter = true;
/* 4910 */       setClobInternal(paramInt, paramClob);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setDate(int paramInt, Date paramDate)
/*      */     throws SQLException
/*      */   {
/* 4918 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 4922 */       this.atLeastOneOrdinalParameter = true;
/* 4923 */       setDateInternal(paramInt, paramDate);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setDate(int paramInt, Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 4931 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 4935 */       this.atLeastOneOrdinalParameter = true;
/* 4936 */       setDateInternal(paramInt, paramDate, paramCalendar);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setDouble(int paramInt, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 4944 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 4948 */       this.atLeastOneOrdinalParameter = true;
/* 4949 */       setDoubleInternal(paramInt, paramDouble);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setFloat(int paramInt, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 4957 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 4961 */       this.atLeastOneOrdinalParameter = true;
/* 4962 */       setFloatInternal(paramInt, paramFloat);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setInt(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 4970 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 4974 */       this.atLeastOneOrdinalParameter = true;
/* 4975 */       setIntInternal(paramInt1, paramInt2);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setLong(int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 4983 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 4987 */       this.atLeastOneOrdinalParameter = true;
/* 4988 */       setLongInternal(paramInt, paramLong);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNClob(int paramInt, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/* 4996 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5000 */       this.atLeastOneOrdinalParameter = true;
/* 5001 */       setNClobInternal(paramInt, paramNClob);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 5009 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5013 */       this.atLeastOneOrdinalParameter = true;
/* 5014 */       setNStringInternal(paramInt, paramString);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setObject(int paramInt, Object paramObject)
/*      */     throws SQLException
/*      */   {
/* 5023 */     this.atLeastOneOrdinalParameter = true;
/* 5024 */     setObjectInternal(paramInt, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setObject(int paramInt1, Object paramObject, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 5032 */     this.atLeastOneOrdinalParameter = true;
/* 5033 */     setObjectInternal(paramInt1, paramObject, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRef(int paramInt, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/* 5041 */     this.atLeastOneOrdinalParameter = true;
/* 5042 */     setRefInternal(paramInt, paramRef);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setRowId(int paramInt, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/* 5049 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5053 */       this.atLeastOneOrdinalParameter = true;
/* 5054 */       setRowIdInternal(paramInt, paramRowId);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setShort(int paramInt, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 5062 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5066 */       this.atLeastOneOrdinalParameter = true;
/* 5067 */       setShortInternal(paramInt, paramShort);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setSQLXML(int paramInt, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/* 5075 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5079 */       this.atLeastOneOrdinalParameter = true;
/* 5080 */       setSQLXMLInternal(paramInt, paramSQLXML);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 5088 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5092 */       this.atLeastOneOrdinalParameter = true;
/* 5093 */       setStringInternal(paramInt, paramString);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTime(int paramInt, Time paramTime)
/*      */     throws SQLException
/*      */   {
/* 5101 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5105 */       this.atLeastOneOrdinalParameter = true;
/* 5106 */       setTimeInternal(paramInt, paramTime);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTime(int paramInt, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 5114 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5118 */       this.atLeastOneOrdinalParameter = true;
/* 5119 */       setTimeInternal(paramInt, paramTime, paramCalendar);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTimestamp(int paramInt, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/* 5127 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5131 */       this.atLeastOneOrdinalParameter = true;
/* 5132 */       setTimestampInternal(paramInt, paramTimestamp);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTimestamp(int paramInt, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 5140 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5144 */       this.atLeastOneOrdinalParameter = true;
/* 5145 */       setTimestampInternal(paramInt, paramTimestamp, paramCalendar);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setURL(int paramInt, URL paramURL)
/*      */     throws SQLException
/*      */   {
/* 5153 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5157 */       this.atLeastOneOrdinalParameter = true;
/* 5158 */       setURLInternal(paramInt, paramURL);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setARRAY(int paramInt, ARRAY paramARRAY)
/*      */     throws SQLException
/*      */   {
/* 5167 */     this.atLeastOneOrdinalParameter = true;
/* 5168 */     setARRAYInternal(paramInt, paramARRAY);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBFILE(int paramInt, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 5175 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5179 */       this.atLeastOneOrdinalParameter = true;
/* 5180 */       setBFILEInternal(paramInt, paramBFILE);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBfile(int paramInt, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 5188 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5192 */       this.atLeastOneOrdinalParameter = true;
/* 5193 */       setBfileInternal(paramInt, paramBFILE);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBinaryFloat(int paramInt, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 5201 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5205 */       this.atLeastOneOrdinalParameter = true;
/* 5206 */       setBinaryFloatInternal(paramInt, paramFloat);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBinaryFloat(int paramInt, BINARY_FLOAT paramBINARY_FLOAT)
/*      */     throws SQLException
/*      */   {
/* 5214 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5218 */       this.atLeastOneOrdinalParameter = true;
/* 5219 */       setBinaryFloatInternal(paramInt, paramBINARY_FLOAT);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBinaryDouble(int paramInt, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 5227 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5231 */       this.atLeastOneOrdinalParameter = true;
/* 5232 */       setBinaryDoubleInternal(paramInt, paramDouble);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBinaryDouble(int paramInt, BINARY_DOUBLE paramBINARY_DOUBLE)
/*      */     throws SQLException
/*      */   {
/* 5240 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5244 */       this.atLeastOneOrdinalParameter = true;
/* 5245 */       setBinaryDoubleInternal(paramInt, paramBINARY_DOUBLE);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBLOB(int paramInt, BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 5253 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5257 */       this.atLeastOneOrdinalParameter = true;
/* 5258 */       setBLOBInternal(paramInt, paramBLOB);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setCHAR(int paramInt, CHAR paramCHAR)
/*      */     throws SQLException
/*      */   {
/* 5266 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5270 */       this.atLeastOneOrdinalParameter = true;
/* 5271 */       setCHARInternal(paramInt, paramCHAR);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setCLOB(int paramInt, CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 5279 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5283 */       this.atLeastOneOrdinalParameter = true;
/* 5284 */       setCLOBInternal(paramInt, paramCLOB);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setCursor(int paramInt, ResultSet paramResultSet)
/*      */     throws SQLException
/*      */   {
/* 5292 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5296 */       this.atLeastOneOrdinalParameter = true;
/* 5297 */       setCursorInternal(paramInt, paramResultSet);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setCustomDatum(int paramInt, CustomDatum paramCustomDatum)
/*      */     throws SQLException
/*      */   {
/* 5306 */     this.atLeastOneOrdinalParameter = true;
/* 5307 */     setCustomDatumInternal(paramInt, paramCustomDatum);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setDATE(int paramInt, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/* 5314 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5318 */       this.atLeastOneOrdinalParameter = true;
/* 5319 */       setDATEInternal(paramInt, paramDATE);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setFixedCHAR(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 5327 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5331 */       this.atLeastOneOrdinalParameter = true;
/* 5332 */       setFixedCHARInternal(paramInt, paramString);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setINTERVALDS(int paramInt, INTERVALDS paramINTERVALDS)
/*      */     throws SQLException
/*      */   {
/* 5340 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5344 */       this.atLeastOneOrdinalParameter = true;
/* 5345 */       setINTERVALDSInternal(paramInt, paramINTERVALDS);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setINTERVALYM(int paramInt, INTERVALYM paramINTERVALYM)
/*      */     throws SQLException
/*      */   {
/* 5353 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5357 */       this.atLeastOneOrdinalParameter = true;
/* 5358 */       setINTERVALYMInternal(paramInt, paramINTERVALYM);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNUMBER(int paramInt, NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 5366 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5370 */       this.atLeastOneOrdinalParameter = true;
/* 5371 */       setNUMBERInternal(paramInt, paramNUMBER);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setOPAQUE(int paramInt, OPAQUE paramOPAQUE)
/*      */     throws SQLException
/*      */   {
/* 5380 */     this.atLeastOneOrdinalParameter = true;
/* 5381 */     setOPAQUEInternal(paramInt, paramOPAQUE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setOracleObject(int paramInt, Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/* 5389 */     this.atLeastOneOrdinalParameter = true;
/* 5390 */     setOracleObjectInternal(paramInt, paramDatum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setORAData(int paramInt, ORAData paramORAData)
/*      */     throws SQLException
/*      */   {
/* 5398 */     this.atLeastOneOrdinalParameter = true;
/* 5399 */     setORADataInternal(paramInt, paramORAData);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setRAW(int paramInt, RAW paramRAW)
/*      */     throws SQLException
/*      */   {
/* 5406 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5410 */       this.atLeastOneOrdinalParameter = true;
/* 5411 */       setRAWInternal(paramInt, paramRAW);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setREF(int paramInt, REF paramREF)
/*      */     throws SQLException
/*      */   {
/* 5420 */     this.atLeastOneOrdinalParameter = true;
/* 5421 */     setREFInternal(paramInt, paramREF);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setRefType(int paramInt, REF paramREF)
/*      */     throws SQLException
/*      */   {
/* 5429 */     this.atLeastOneOrdinalParameter = true;
/* 5430 */     setRefTypeInternal(paramInt, paramREF);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setROWID(int paramInt, ROWID paramROWID)
/*      */     throws SQLException
/*      */   {
/* 5437 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5441 */       this.atLeastOneOrdinalParameter = true;
/* 5442 */       setROWIDInternal(paramInt, paramROWID);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSTRUCT(int paramInt, STRUCT paramSTRUCT)
/*      */     throws SQLException
/*      */   {
/* 5451 */     this.atLeastOneOrdinalParameter = true;
/* 5452 */     setSTRUCTInternal(paramInt, paramSTRUCT);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTIMESTAMPLTZ(int paramInt, TIMESTAMPLTZ paramTIMESTAMPLTZ)
/*      */     throws SQLException
/*      */   {
/* 5459 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5463 */       this.atLeastOneOrdinalParameter = true;
/* 5464 */       setTIMESTAMPLTZInternal(paramInt, paramTIMESTAMPLTZ);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTIMESTAMPTZ(int paramInt, TIMESTAMPTZ paramTIMESTAMPTZ)
/*      */     throws SQLException
/*      */   {
/* 5472 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5476 */       this.atLeastOneOrdinalParameter = true;
/* 5477 */       setTIMESTAMPTZInternal(paramInt, paramTIMESTAMPTZ);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTIMESTAMP(int paramInt, TIMESTAMP paramTIMESTAMP)
/*      */     throws SQLException
/*      */   {
/* 5485 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5489 */       this.atLeastOneOrdinalParameter = true;
/* 5490 */       setTIMESTAMPInternal(paramInt, paramTIMESTAMP);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBlob(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 5498 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5502 */       this.atLeastOneOrdinalParameter = true;
/* 5503 */       setBlobInternal(paramInt, paramInputStream);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBlob(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5511 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 5514 */       if (paramLong < 0L)
/*      */       {
/* 5516 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "length for setBlob() cannot be negative");
/* 5517 */         localSQLException.fillInStackTrace();
/* 5518 */         throw localSQLException;
/*      */       }
/*      */       
/* 5521 */       this.atLeastOneOrdinalParameter = true;
/* 5522 */       setBlobInternal(paramInt, paramInputStream, paramLong);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 5530 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5534 */       this.atLeastOneOrdinalParameter = true;
/* 5535 */       setClobInternal(paramInt, paramReader);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5543 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5547 */       if (paramLong < 0L)
/*      */       {
/* 5549 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "length for setClob() cannot be negative");
/* 5550 */         localSQLException.fillInStackTrace();
/* 5551 */         throw localSQLException;
/*      */       }
/* 5553 */       this.atLeastOneOrdinalParameter = true;
/* 5554 */       setClobInternal(paramInt, paramReader, paramLong);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 5562 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5566 */       this.atLeastOneOrdinalParameter = true;
/* 5567 */       setNClobInternal(paramInt, paramReader);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5575 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5579 */       this.atLeastOneOrdinalParameter = true;
/* 5580 */       setNClobInternal(paramInt, paramReader, paramLong);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setAsciiStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 5588 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5592 */       this.atLeastOneOrdinalParameter = true;
/* 5593 */       setAsciiStreamInternal(paramInt, paramInputStream);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 5601 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5605 */       this.atLeastOneOrdinalParameter = true;
/* 5606 */       setAsciiStreamInternal(paramInt1, paramInputStream, paramInt2);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setAsciiStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5614 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5618 */       this.atLeastOneOrdinalParameter = true;
/* 5619 */       setAsciiStreamInternal(paramInt, paramInputStream, paramLong);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBinaryStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 5627 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5631 */       this.atLeastOneOrdinalParameter = true;
/* 5632 */       setBinaryStreamInternal(paramInt, paramInputStream);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 5640 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5644 */       this.atLeastOneOrdinalParameter = true;
/* 5645 */       setBinaryStreamInternal(paramInt1, paramInputStream, paramInt2);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setBinaryStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5653 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5657 */       this.atLeastOneOrdinalParameter = true;
/* 5658 */       setBinaryStreamInternal(paramInt, paramInputStream, paramLong);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 5666 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5670 */       this.atLeastOneOrdinalParameter = true;
/* 5671 */       setCharacterStreamInternal(paramInt, paramReader);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setCharacterStream(int paramInt1, Reader paramReader, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 5679 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5683 */       this.atLeastOneOrdinalParameter = true;
/* 5684 */       setCharacterStreamInternal(paramInt1, paramReader, paramInt2);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5692 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5696 */       this.atLeastOneOrdinalParameter = true;
/* 5697 */       setCharacterStreamInternal(paramInt, paramReader, paramLong);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 5705 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5709 */       this.atLeastOneOrdinalParameter = true;
/* 5710 */       setNCharacterStreamInternal(paramInt, paramReader);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setNCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5718 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5722 */       this.atLeastOneOrdinalParameter = true;
/* 5723 */       setNCharacterStreamInternal(paramInt, paramReader, paramLong);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setUnicodeStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 5731 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/* 5735 */       this.atLeastOneOrdinalParameter = true;
/* 5736 */       setUnicodeStreamInternal(paramInt1, paramInputStream, paramInt2);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setArray(String paramString, Array paramArray)
/*      */     throws SQLException
/*      */   {
/* 5748 */     int i = addNamedPara(paramString);
/* 5749 */     setArrayInternal(i, paramArray);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBigDecimal(String paramString, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/* 5759 */     int i = addNamedPara(paramString);
/* 5760 */     setBigDecimalInternal(i, paramBigDecimal);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/* 5770 */     int i = addNamedPara(paramString);
/* 5771 */     setBlobInternal(i, paramBlob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBoolean(String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 5781 */     int i = addNamedPara(paramString);
/* 5782 */     setBooleanInternal(i, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setByte(String paramString, byte paramByte)
/*      */     throws SQLException
/*      */   {
/* 5792 */     int i = addNamedPara(paramString);
/* 5793 */     setByteInternal(i, paramByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBytes(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 5803 */     int i = addNamedPara(paramString);
/* 5804 */     setBytesInternal(i, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/* 5814 */     int i = addNamedPara(paramString);
/* 5815 */     setClobInternal(i, paramClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(String paramString, Date paramDate)
/*      */     throws SQLException
/*      */   {
/* 5825 */     int i = addNamedPara(paramString);
/* 5826 */     setDateInternal(i, paramDate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(String paramString, Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 5836 */     int i = addNamedPara(paramString);
/* 5837 */     setDateInternal(i, paramDate, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDouble(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 5847 */     int i = addNamedPara(paramString);
/* 5848 */     setDoubleInternal(i, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFloat(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 5858 */     int i = addNamedPara(paramString);
/* 5859 */     setFloatInternal(i, paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setInt(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 5869 */     int i = addNamedPara(paramString);
/* 5870 */     setIntInternal(i, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLong(String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5880 */     int i = addNamedPara(paramString);
/* 5881 */     setLongInternal(i, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/* 5891 */     int i = addNamedPara(paramString);
/* 5892 */     setNClobInternal(i, paramNClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 5902 */     int i = addNamedPara(paramString1);
/* 5903 */     setNStringInternal(i, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(String paramString, Object paramObject)
/*      */     throws SQLException
/*      */   {
/* 5913 */     int i = addNamedPara(paramString);
/* 5914 */     setObjectInternal(i, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(String paramString, Object paramObject, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 5924 */     int i = addNamedPara(paramString);
/* 5925 */     setObjectInternal(i, paramObject, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRef(String paramString, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/* 5935 */     int i = addNamedPara(paramString);
/* 5936 */     setRefInternal(i, paramRef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRowId(String paramString, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/* 5946 */     int i = addNamedPara(paramString);
/* 5947 */     setRowIdInternal(i, paramRowId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShort(String paramString, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 5957 */     int i = addNamedPara(paramString);
/* 5958 */     setShortInternal(i, paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSQLXML(String paramString, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/* 5968 */     int i = addNamedPara(paramString);
/* 5969 */     setSQLXMLInternal(i, paramSQLXML);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 5979 */     int i = addNamedPara(paramString1);
/* 5980 */     setStringInternal(i, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(String paramString, Time paramTime)
/*      */     throws SQLException
/*      */   {
/* 5990 */     int i = addNamedPara(paramString);
/* 5991 */     setTimeInternal(i, paramTime);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(String paramString, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 6001 */     int i = addNamedPara(paramString);
/* 6002 */     setTimeInternal(i, paramTime, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimestamp(String paramString, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/* 6012 */     int i = addNamedPara(paramString);
/* 6013 */     setTimestampInternal(i, paramTimestamp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimestamp(String paramString, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 6023 */     int i = addNamedPara(paramString);
/* 6024 */     setTimestampInternal(i, paramTimestamp, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setURL(String paramString, URL paramURL)
/*      */     throws SQLException
/*      */   {
/* 6034 */     int i = addNamedPara(paramString);
/* 6035 */     setURLInternal(i, paramURL);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setARRAY(String paramString, ARRAY paramARRAY)
/*      */     throws SQLException
/*      */   {
/* 6045 */     int i = addNamedPara(paramString);
/* 6046 */     setARRAYInternal(i, paramARRAY);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBFILE(String paramString, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 6056 */     int i = addNamedPara(paramString);
/* 6057 */     setBFILEInternal(i, paramBFILE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBfile(String paramString, BFILE paramBFILE)
/*      */     throws SQLException
/*      */   {
/* 6067 */     int i = addNamedPara(paramString);
/* 6068 */     setBfileInternal(i, paramBFILE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryFloat(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 6078 */     int i = addNamedPara(paramString);
/* 6079 */     setBinaryFloatInternal(i, paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryFloat(String paramString, BINARY_FLOAT paramBINARY_FLOAT)
/*      */     throws SQLException
/*      */   {
/* 6089 */     int i = addNamedPara(paramString);
/* 6090 */     setBinaryFloatInternal(i, paramBINARY_FLOAT);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryDouble(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 6100 */     int i = addNamedPara(paramString);
/* 6101 */     setBinaryDoubleInternal(i, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryDouble(String paramString, BINARY_DOUBLE paramBINARY_DOUBLE)
/*      */     throws SQLException
/*      */   {
/* 6111 */     int i = addNamedPara(paramString);
/* 6112 */     setBinaryDoubleInternal(i, paramBINARY_DOUBLE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBLOB(String paramString, BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/* 6122 */     int i = addNamedPara(paramString);
/* 6123 */     setBLOBInternal(i, paramBLOB);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCHAR(String paramString, CHAR paramCHAR)
/*      */     throws SQLException
/*      */   {
/* 6133 */     int i = addNamedPara(paramString);
/* 6134 */     setCHARInternal(i, paramCHAR);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCLOB(String paramString, CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 6144 */     int i = addNamedPara(paramString);
/* 6145 */     setCLOBInternal(i, paramCLOB);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCursor(String paramString, ResultSet paramResultSet)
/*      */     throws SQLException
/*      */   {
/* 6155 */     int i = addNamedPara(paramString);
/* 6156 */     setCursorInternal(i, paramResultSet);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCustomDatum(String paramString, CustomDatum paramCustomDatum)
/*      */     throws SQLException
/*      */   {
/* 6166 */     int i = addNamedPara(paramString);
/* 6167 */     setCustomDatumInternal(i, paramCustomDatum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDATE(String paramString, DATE paramDATE)
/*      */     throws SQLException
/*      */   {
/* 6177 */     int i = addNamedPara(paramString);
/* 6178 */     setDATEInternal(i, paramDATE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFixedCHAR(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 6188 */     int i = addNamedPara(paramString1);
/* 6189 */     setFixedCHARInternal(i, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setINTERVALDS(String paramString, INTERVALDS paramINTERVALDS)
/*      */     throws SQLException
/*      */   {
/* 6199 */     int i = addNamedPara(paramString);
/* 6200 */     setINTERVALDSInternal(i, paramINTERVALDS);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setINTERVALYM(String paramString, INTERVALYM paramINTERVALYM)
/*      */     throws SQLException
/*      */   {
/* 6210 */     int i = addNamedPara(paramString);
/* 6211 */     setINTERVALYMInternal(i, paramINTERVALYM);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNUMBER(String paramString, NUMBER paramNUMBER)
/*      */     throws SQLException
/*      */   {
/* 6221 */     int i = addNamedPara(paramString);
/* 6222 */     setNUMBERInternal(i, paramNUMBER);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setOPAQUE(String paramString, OPAQUE paramOPAQUE)
/*      */     throws SQLException
/*      */   {
/* 6232 */     int i = addNamedPara(paramString);
/* 6233 */     setOPAQUEInternal(i, paramOPAQUE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setOracleObject(String paramString, Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/* 6243 */     int i = addNamedPara(paramString);
/* 6244 */     setOracleObjectInternal(i, paramDatum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setORAData(String paramString, ORAData paramORAData)
/*      */     throws SQLException
/*      */   {
/* 6254 */     int i = addNamedPara(paramString);
/* 6255 */     setORADataInternal(i, paramORAData);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRAW(String paramString, RAW paramRAW)
/*      */     throws SQLException
/*      */   {
/* 6265 */     int i = addNamedPara(paramString);
/* 6266 */     setRAWInternal(i, paramRAW);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setREF(String paramString, REF paramREF)
/*      */     throws SQLException
/*      */   {
/* 6276 */     int i = addNamedPara(paramString);
/* 6277 */     setREFInternal(i, paramREF);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRefType(String paramString, REF paramREF)
/*      */     throws SQLException
/*      */   {
/* 6287 */     int i = addNamedPara(paramString);
/* 6288 */     setRefTypeInternal(i, paramREF);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setROWID(String paramString, ROWID paramROWID)
/*      */     throws SQLException
/*      */   {
/* 6298 */     int i = addNamedPara(paramString);
/* 6299 */     setROWIDInternal(i, paramROWID);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSTRUCT(String paramString, STRUCT paramSTRUCT)
/*      */     throws SQLException
/*      */   {
/* 6309 */     int i = addNamedPara(paramString);
/* 6310 */     setSTRUCTInternal(i, paramSTRUCT);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPLTZ(String paramString, TIMESTAMPLTZ paramTIMESTAMPLTZ)
/*      */     throws SQLException
/*      */   {
/* 6320 */     int i = addNamedPara(paramString);
/* 6321 */     setTIMESTAMPLTZInternal(i, paramTIMESTAMPLTZ);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMPTZ(String paramString, TIMESTAMPTZ paramTIMESTAMPTZ)
/*      */     throws SQLException
/*      */   {
/* 6331 */     int i = addNamedPara(paramString);
/* 6332 */     setTIMESTAMPTZInternal(i, paramTIMESTAMPTZ);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTIMESTAMP(String paramString, TIMESTAMP paramTIMESTAMP)
/*      */     throws SQLException
/*      */   {
/* 6342 */     int i = addNamedPara(paramString);
/* 6343 */     setTIMESTAMPInternal(i, paramTIMESTAMP);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 6353 */     int i = addNamedPara(paramString);
/* 6354 */     setBlobInternal(i, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 6363 */     if (paramLong < 0L)
/*      */     {
/* 6365 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "length for setBlob() cannot be negative");
/* 6366 */       localSQLException.fillInStackTrace();
/* 6367 */       throw localSQLException;
/*      */     }
/*      */     
/* 6370 */     int i = addNamedPara(paramString);
/* 6371 */     setBlobInternal(i, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 6381 */     int i = addNamedPara(paramString);
/* 6382 */     setClobInternal(i, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 6392 */     if (paramLong < 0L)
/*      */     {
/* 6394 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "length for setClob() cannot be negative");
/* 6395 */       localSQLException.fillInStackTrace();
/* 6396 */       throw localSQLException;
/*      */     }
/* 6398 */     int i = addNamedPara(paramString);
/* 6399 */     setClobInternal(i, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 6409 */     int i = addNamedPara(paramString);
/* 6410 */     setNClobInternal(i, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 6420 */     int i = addNamedPara(paramString);
/* 6421 */     setNClobInternal(i, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 6431 */     int i = addNamedPara(paramString);
/* 6432 */     setAsciiStreamInternal(i, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 6442 */     int i = addNamedPara(paramString);
/* 6443 */     setAsciiStreamInternal(i, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 6453 */     int i = addNamedPara(paramString);
/* 6454 */     setAsciiStreamInternal(i, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 6464 */     int i = addNamedPara(paramString);
/* 6465 */     setBinaryStreamInternal(i, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 6475 */     int i = addNamedPara(paramString);
/* 6476 */     setBinaryStreamInternal(i, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 6486 */     int i = addNamedPara(paramString);
/* 6487 */     setBinaryStreamInternal(i, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 6497 */     int i = addNamedPara(paramString);
/* 6498 */     setCharacterStreamInternal(i, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 6508 */     int i = addNamedPara(paramString);
/* 6509 */     setCharacterStreamInternal(i, paramReader, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 6519 */     int i = addNamedPara(paramString);
/* 6520 */     setCharacterStreamInternal(i, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 6530 */     int i = addNamedPara(paramString);
/* 6531 */     setNCharacterStreamInternal(i, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 6541 */     int i = addNamedPara(paramString);
/* 6542 */     setNCharacterStreamInternal(i, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setUnicodeStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 6552 */     int i = addNamedPara(paramString);
/* 6553 */     setUnicodeStreamInternal(i, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNull(String paramString1, int paramInt, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 6564 */     int i = addNamedPara(paramString1);
/* 6565 */     setNullInternal(i, paramInt, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNull(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 6576 */     int i = addNamedPara(paramString);
/* 6577 */     setNullInternal(i, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setStructDescriptor(String paramString, StructDescriptor paramStructDescriptor)
/*      */     throws SQLException
/*      */   {
/* 6586 */     int i = addNamedPara(paramString);
/* 6587 */     setStructDescriptorInternal(i, paramStructDescriptor);
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
/* 6598 */     int i = addNamedPara(paramString);
/* 6599 */     setObjectInternal(i, paramObject, paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPlsqlIndexTable(int paramInt1, Object paramObject, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*      */     throws SQLException
/*      */   {
/* 6611 */     synchronized (this.connection)
/*      */     {
/* 6613 */       this.atLeastOneOrdinalParameter = true;
/* 6614 */       setPlsqlIndexTableInternal(paramInt1, paramObject, paramInt2, paramInt3, paramInt4, paramInt5);
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
/*      */   int addNamedPara(String paramString)
/*      */     throws SQLException
/*      */   {
/* 6632 */     if (this.closed)
/*      */     {
/* 6634 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 6635 */       ((SQLException)localObject).fillInStackTrace();
/* 6636 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 6639 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/* 6641 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 6643 */       if (localObject == this.namedParameters[i]) {
/* 6644 */         return i + 1;
/*      */       }
/*      */     }
/* 6647 */     if (this.parameterCount >= this.namedParameters.length)
/*      */     {
/* 6649 */       String[] arrayOfString = new String[this.namedParameters.length * 2];
/* 6650 */       System.arraycopy(this.namedParameters, 0, arrayOfString, 0, this.namedParameters.length);
/* 6651 */       this.namedParameters = arrayOfString;
/*      */     }
/*      */     
/* 6654 */     this.namedParameters[(this.parameterCount++)] = localObject;
/*      */     
/* 6656 */     this.atLeastOneNamedParameter = true;
/* 6657 */     return this.parameterCount;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader getCharacterStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 6667 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 6670 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 6671 */       ((SQLException)localObject).fillInStackTrace();
/* 6672 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 6676 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 6679 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 6681 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 6684 */     i++;
/*      */     
/* 6686 */     Accessor localAccessor = null;
/* 6687 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 6692 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 6693 */       localSQLException.fillInStackTrace();
/* 6694 */       throw localSQLException;
/*      */     }
/*      */     
/* 6697 */     this.lastIndex = i;
/*      */     
/* 6699 */     if (this.streamList != null) {
/* 6700 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 6703 */     return localAccessor.getCharacterStream(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public InputStream getUnicodeStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 6711 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 6714 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 6715 */       ((SQLException)localObject).fillInStackTrace();
/* 6716 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 6720 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 6723 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 6725 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 6728 */     i++;
/*      */     
/* 6730 */     Accessor localAccessor = null;
/* 6731 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 6736 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 6737 */       localSQLException.fillInStackTrace();
/* 6738 */       throw localSQLException;
/*      */     }
/*      */     
/* 6741 */     this.lastIndex = i;
/*      */     
/* 6743 */     if (this.streamList != null) {
/* 6744 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 6747 */     return localAccessor.getUnicodeStream(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public InputStream getBinaryStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 6755 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 6758 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 6759 */       ((SQLException)localObject).fillInStackTrace();
/* 6760 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 6764 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 6767 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 6769 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 6772 */     i++;
/*      */     
/* 6774 */     Accessor localAccessor = null;
/* 6775 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 6780 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 6781 */       localSQLException.fillInStackTrace();
/* 6782 */       throw localSQLException;
/*      */     }
/*      */     
/* 6785 */     this.lastIndex = i;
/*      */     
/* 6787 */     if (this.streamList != null) {
/* 6788 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 6791 */     return localAccessor.getBinaryStream(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RowId getRowId(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 6804 */     if (this.closed)
/*      */     {
/* 6806 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 6807 */       ((SQLException)localObject).fillInStackTrace();
/* 6808 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 6811 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 6814 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 6815 */       ((SQLException)localObject).fillInStackTrace();
/* 6816 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 6820 */     Object localObject = null;
/* 6821 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 6826 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 6827 */       localSQLException.fillInStackTrace();
/* 6828 */       throw localSQLException;
/*      */     }
/*      */     
/* 6831 */     this.lastIndex = paramInt;
/*      */     
/* 6833 */     if (this.streamList != null) {
/* 6834 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 6837 */     return ((Accessor)localObject).getROWID(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public RowId getRowId(String paramString)
/*      */     throws SQLException
/*      */   {
/* 6845 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 6848 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 6849 */       ((SQLException)localObject).fillInStackTrace();
/* 6850 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 6854 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 6857 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 6859 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 6862 */     i++;
/*      */     
/* 6864 */     Accessor localAccessor = null;
/* 6865 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 6870 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 6871 */       localSQLException.fillInStackTrace();
/* 6872 */       throw localSQLException;
/*      */     }
/*      */     
/* 6875 */     this.lastIndex = i;
/*      */     
/* 6877 */     if (this.streamList != null) {
/* 6878 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 6881 */     return localAccessor.getROWID(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public NClob getNClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 6890 */     if (this.closed)
/*      */     {
/* 6892 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 6893 */       ((SQLException)localObject).fillInStackTrace();
/* 6894 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 6897 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 6900 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 6901 */       ((SQLException)localObject).fillInStackTrace();
/* 6902 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 6906 */     Object localObject = null;
/* 6907 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 6912 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 6913 */       localSQLException.fillInStackTrace();
/* 6914 */       throw localSQLException;
/*      */     }
/*      */     
/* 6917 */     this.lastIndex = paramInt;
/*      */     
/* 6919 */     if (this.streamList != null) {
/* 6920 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 6923 */     return ((Accessor)localObject).getNClob(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public NClob getNClob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 6931 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 6934 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 6935 */       ((SQLException)localObject).fillInStackTrace();
/* 6936 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 6940 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 6943 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 6945 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 6948 */     i++;
/*      */     
/* 6950 */     Accessor localAccessor = null;
/* 6951 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 6956 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 6957 */       localSQLException.fillInStackTrace();
/* 6958 */       throw localSQLException;
/*      */     }
/*      */     
/* 6961 */     this.lastIndex = i;
/*      */     
/* 6963 */     if (this.streamList != null) {
/* 6964 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 6967 */     return localAccessor.getNClob(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public SQLXML getSQLXML(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 6975 */     if (this.closed)
/*      */     {
/* 6977 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 6978 */       ((SQLException)localObject).fillInStackTrace();
/* 6979 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 6982 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 6985 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 6986 */       ((SQLException)localObject).fillInStackTrace();
/* 6987 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 6991 */     Object localObject = null;
/* 6992 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 6997 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 6998 */       localSQLException.fillInStackTrace();
/* 6999 */       throw localSQLException;
/*      */     }
/*      */     
/* 7002 */     this.lastIndex = paramInt;
/*      */     
/* 7004 */     if (this.streamList != null) {
/* 7005 */       closeUsedStreams(paramInt);
/*      */     }
/* 7007 */     return ((Accessor)localObject).getSQLXML(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public SQLXML getSQLXML(String paramString)
/*      */     throws SQLException
/*      */   {
/* 7015 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 7018 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 7019 */       ((SQLException)localObject).fillInStackTrace();
/* 7020 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 7024 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 7027 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 7029 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 7032 */     i++;
/*      */     
/* 7034 */     Accessor localAccessor = null;
/* 7035 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 7040 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 7041 */       localSQLException.fillInStackTrace();
/* 7042 */       throw localSQLException;
/*      */     }
/*      */     
/* 7045 */     this.lastIndex = i;
/*      */     
/* 7047 */     if (this.streamList != null) {
/* 7048 */       closeUsedStreams(i);
/*      */     }
/* 7050 */     return localAccessor.getSQLXML(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getNString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 7059 */     if (this.closed)
/*      */     {
/* 7061 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 7062 */       ((SQLException)localObject).fillInStackTrace();
/* 7063 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 7066 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 7069 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 7070 */       ((SQLException)localObject).fillInStackTrace();
/* 7071 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 7075 */     Object localObject = null;
/* 7076 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 7081 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 7082 */       localSQLException.fillInStackTrace();
/* 7083 */       throw localSQLException;
/*      */     }
/*      */     
/* 7086 */     this.lastIndex = paramInt;
/*      */     
/* 7088 */     if (this.streamList != null) {
/* 7089 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 7092 */     return ((Accessor)localObject).getNString(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getNString(String paramString)
/*      */     throws SQLException
/*      */   {
/* 7100 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 7103 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 7104 */       ((SQLException)localObject).fillInStackTrace();
/* 7105 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 7109 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 7112 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 7114 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 7117 */     i++;
/*      */     
/* 7119 */     Accessor localAccessor = null;
/* 7120 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 7125 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 7126 */       localSQLException.fillInStackTrace();
/* 7127 */       throw localSQLException;
/*      */     }
/*      */     
/* 7130 */     this.lastIndex = i;
/*      */     
/* 7132 */     if (this.streamList != null) {
/* 7133 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 7136 */     return localAccessor.getNString(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader getNCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 7145 */     if (this.closed)
/*      */     {
/* 7147 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 7148 */       ((SQLException)localObject).fillInStackTrace();
/* 7149 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 7152 */     if (this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 7155 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 7156 */       ((SQLException)localObject).fillInStackTrace();
/* 7157 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 7161 */     Object localObject = null;
/* 7162 */     if ((paramInt <= 0) || (paramInt > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localObject = this.outBindAccessors[(paramInt - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 7167 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 7168 */       localSQLException.fillInStackTrace();
/* 7169 */       throw localSQLException;
/*      */     }
/*      */     
/* 7172 */     this.lastIndex = paramInt;
/*      */     
/* 7174 */     if (this.streamList != null) {
/* 7175 */       closeUsedStreams(paramInt);
/*      */     }
/*      */     
/* 7178 */     return ((Accessor)localObject).getNCharacterStream(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Reader getNCharacterStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 7186 */     if (!this.atLeastOneNamedParameter)
/*      */     {
/*      */ 
/* 7189 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90, "Ordinal binding and Named binding cannot be combined!");
/* 7190 */       ((SQLException)localObject).fillInStackTrace();
/* 7191 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 7195 */     Object localObject = paramString.toUpperCase().intern();
/*      */     
/*      */ 
/* 7198 */     for (int i = 0; i < this.parameterCount; i++)
/*      */     {
/* 7200 */       if (localObject == this.namedParameters[i])
/*      */         break;
/*      */     }
/* 7203 */     i++;
/*      */     
/* 7205 */     Accessor localAccessor = null;
/* 7206 */     if ((i <= 0) || (i > this.numberOfBindPositions) || (this.outBindAccessors == null) || ((localAccessor = this.outBindAccessors[(i - 1)]) == null))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 7211 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6);
/* 7212 */       localSQLException.fillInStackTrace();
/* 7213 */       throw localSQLException;
/*      */     }
/*      */     
/* 7216 */     this.lastIndex = i;
/*      */     
/* 7218 */     if (this.streamList != null) {
/* 7219 */       closeUsedStreams(i);
/*      */     }
/*      */     
/* 7222 */     return localAccessor.getNCharacterStream(this.currentRank);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 7229 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleCallableStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */