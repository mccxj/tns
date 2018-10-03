/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.sql.SQLException;
/*      */ import java.util.Properties;
/*      */ import java.util.Vector;
/*      */ import oracle.jdbc.internal.KeywordValue;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.internal.OracleStatement.SqlKind;
/*      */ import oracle.jdbc.oracore.OracleTypeADT;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ final class T4C8Oall
/*      */   extends T4CTTIfun
/*      */ {
/*  124 */   Vector<IOException> nonFatalIOExceptions = null;
/*      */   
/*  126 */   static final byte[] EMPTY_BYTES = new byte[0];
/*      */   
/*      */ 
/*      */   static final int UOPF_PRS = 1;
/*      */   
/*      */ 
/*      */   static final int UOPF_BND = 8;
/*      */   
/*      */   static final int UOPF_EXE = 32;
/*      */   
/*      */   static final int UOPF_FEX = 512;
/*      */   
/*      */   static final int UOPF_FCH = 64;
/*      */   
/*      */   static final int UOPF_CAN = 128;
/*      */   
/*      */   static final int UOPF_COM = 256;
/*      */   
/*      */   static final int UOPF_DSY = 8192;
/*      */   
/*      */   static final int UOPF_SIO = 1024;
/*      */   
/*      */   static final int UOPF_NPL = 32768;
/*      */   
/*      */   static final int UOPF_DFN = 16;
/*      */   
/*      */   static final int EXE_COMMIT_ON_SUCCESS = 1;
/*      */   
/*      */   static final int EXE_LEAVE_CUR_MAPPED = 2;
/*      */   
/*      */   static final int EXE_BATCH_DML_ERRORS = 4;
/*      */   
/*      */   static final int EXE_SCROL_READ_ONLY = 8;
/*      */   
/*      */   static final int AL8KW_MAXLANG = 63;
/*      */   
/*      */   static final int AL8KW_TIMEZONE = 163;
/*      */   
/*      */   static final int AL8KW_ERR_OVLAP = 164;
/*      */   
/*      */   static final int AL8KW_SESSION_ID = 165;
/*      */   
/*      */   static final int AL8KW_SERIAL_NUM = 166;
/*      */   
/*      */   static final int AL8KW_TAG_FOUND = 167;
/*      */   
/*      */   static final int AL8KW_SCHEMA_NAME = 168;
/*      */   
/*      */   static final int AL8KW_SCHEMA_ID = 169;
/*      */   
/*      */   static final int AL8KW_ENABLED_ROLES = 170;
/*      */   
/*      */   static final int AL8KW_AUX_SESSSTATE = 171;
/*      */   
/*  180 */   static final String[] NLS_KEYS = { "AUTH_NLS_LXCCURRENCY", "AUTH_NLS_LXCISOCURR", "AUTH_NLS_LXCNUMERICS", null, null, null, null, "AUTH_NLS_LXCDATEFM", "AUTH_NLS_LXCDATELANG", "AUTH_NLS_LXCTERRITORY", "SESSION_NLS_LXCCHARSET", "AUTH_NLS_LXCSORT", "AUTH_NLS_LXCCALENDAR", null, null, null, "AUTH_NLS_LXLAN", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "AUTH_NLS_LXCSORT", null, "AUTH_NLS_LXCUNIONCUR", null, null, null, null, "AUTH_NLS_LXCTIMEFM", "AUTH_NLS_LXCSTMPFM", "AUTH_NLS_LXCTTZNFM", "AUTH_NLS_LXCSTZNFM", "SESSION_NLS_LXCNLSLENSEM", "SESSION_NLS_LXCNCHAREXCP", "SESSION_NLS_LXCNCHARIMP" };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int LDIREGIDFLAG = 120;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int LDIREGIDSET = 181;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int LDIMAXTIMEFIELD = 60;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   int rowsProcessed;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   int numberOfDefinePositions;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   long options;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   int cursor;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  267 */   byte[] sqlStmt = new byte[0];
/*  268 */   final long[] al8i4 = new long[13];
/*      */   
/*      */ 
/*  271 */   boolean plsql = false;
/*      */   
/*      */ 
/*      */   Accessor[] definesAccessors;
/*      */   
/*      */ 
/*      */   int definesLength;
/*      */   
/*      */ 
/*      */   Accessor[] outBindAccessors;
/*      */   
/*      */ 
/*      */   int numberOfBindPositions;
/*      */   
/*      */ 
/*      */   InputStream[][] parameterStream;
/*      */   
/*      */   byte[][][] parameterDatum;
/*      */   
/*      */   OracleTypeADT[][] parameterOtype;
/*      */   
/*      */   short[] bindIndicators;
/*      */   
/*      */   byte[] bindBytes;
/*      */   
/*      */   char[] bindChars;
/*      */   
/*      */   int bindIndicatorSubRange;
/*      */   
/*      */   byte[] tmpBindsByteArray;
/*      */   
/*      */   DBConversion conversion;
/*      */   
/*      */   byte[] ibtBindBytes;
/*      */   
/*      */   char[] ibtBindChars;
/*      */   
/*      */   short[] ibtBindIndicators;
/*      */   
/*  310 */   boolean sendBindsDefinition = false;
/*      */   
/*      */   OracleStatement oracleStatement;
/*      */   
/*      */   short dbCharSet;
/*      */   
/*      */   short NCharSet;
/*      */   
/*      */   T4CTTIrxd rxd;
/*      */   
/*      */   T4C8TTIrxh rxh;
/*      */   
/*      */   T4CTTIdcb dcb;
/*      */   
/*      */   OracleStatement.SqlKind typeOfStatement;
/*  325 */   int defCols = 0;
/*      */   
/*      */ 
/*      */   int rowsToFetch;
/*      */   
/*      */ 
/*  331 */   boolean aFetchWasDone = false;
/*      */   
/*      */   T4CTTIoac[] oacdefBindsSent;
/*      */   
/*      */   T4CTTIoac[] oacdefDefines;
/*      */   
/*      */   int[] definedColumnSize;
/*      */   
/*      */   int[] definedColumnType;
/*      */   
/*      */   int[] definedColumnFormOfUse;
/*  342 */   NTFDCNRegistration registration = null;
/*      */   
/*      */   static final int AL8TXCUR = 1;
/*      */   
/*      */   static final int AL8TXDON = 2;
/*      */   static final int AL8TXRON = 4;
/*      */   
/*      */   T4C8Oall(T4CConnection paramT4CConnection)
/*      */   {
/*  351 */     super(paramT4CConnection, (byte)3);
/*      */     
/*  353 */     setFunCode((short)94);
/*  354 */     this.rxh = new T4C8TTIrxh(paramT4CConnection);
/*  355 */     this.rxd = new T4CTTIrxd(paramT4CConnection);
/*  356 */     this.dcb = new T4CTTIdcb(paramT4CConnection);
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
/*      */   void doOALL(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, OracleStatement.SqlKind paramSqlKind, int paramInt1, byte[] paramArrayOfByte1, int paramInt2, Accessor[] paramArrayOfAccessor1, int paramInt3, Accessor[] paramArrayOfAccessor2, int paramInt4, byte[] paramArrayOfByte2, char[] paramArrayOfChar1, short[] paramArrayOfShort1, int paramInt5, DBConversion paramDBConversion, byte[] paramArrayOfByte3, InputStream[][] paramArrayOfInputStream, byte[][][] paramArrayOfByte, OracleTypeADT[][] paramArrayOfOracleTypeADT, OracleStatement paramOracleStatement, byte[] paramArrayOfByte4, char[] paramArrayOfChar2, short[] paramArrayOfShort2, T4CTTIoac[] paramArrayOfT4CTTIoac, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, NTFDCNRegistration paramNTFDCNRegistration)
/*      */     throws SQLException, IOException
/*      */   {
/*  381 */     this.typeOfStatement = paramSqlKind;
/*  382 */     this.cursor = paramInt1;
/*  383 */     this.sqlStmt = (paramBoolean1 ? paramArrayOfByte1 : EMPTY_BYTES);
/*  384 */     this.rowsToFetch = paramInt2;
/*  385 */     this.outBindAccessors = paramArrayOfAccessor1;
/*  386 */     this.numberOfBindPositions = paramInt3;
/*  387 */     this.definesAccessors = paramArrayOfAccessor2;
/*  388 */     this.definesLength = paramInt4;
/*  389 */     this.bindBytes = paramArrayOfByte2;
/*  390 */     this.bindChars = paramArrayOfChar1;
/*  391 */     this.bindIndicators = paramArrayOfShort1;
/*  392 */     this.bindIndicatorSubRange = paramInt5;
/*  393 */     this.conversion = paramDBConversion;
/*  394 */     this.tmpBindsByteArray = paramArrayOfByte3;
/*  395 */     this.parameterStream = paramArrayOfInputStream;
/*  396 */     this.parameterDatum = paramArrayOfByte;
/*  397 */     this.parameterOtype = paramArrayOfOracleTypeADT;
/*  398 */     this.oracleStatement = paramOracleStatement;
/*  399 */     this.ibtBindBytes = paramArrayOfByte4;
/*  400 */     this.ibtBindChars = paramArrayOfChar2;
/*  401 */     this.ibtBindIndicators = paramArrayOfShort2;
/*  402 */     this.oacdefBindsSent = paramArrayOfT4CTTIoac;
/*  403 */     this.definedColumnType = paramArrayOfInt1;
/*  404 */     this.definedColumnSize = paramArrayOfInt2;
/*  405 */     this.definedColumnFormOfUse = paramArrayOfInt3;
/*  406 */     this.registration = paramNTFDCNRegistration;
/*      */     
/*      */ 
/*  409 */     this.dbCharSet = paramDBConversion.getServerCharSetId();
/*  410 */     this.NCharSet = paramDBConversion.getNCharSetId();
/*      */     
/*      */ 
/*  413 */     int i = 0;
/*  414 */     if (this.bindIndicators != null) {
/*  415 */       i = ((this.bindIndicators[(this.bindIndicatorSubRange + 3)] & 0xFFFF) << 16) + (this.bindIndicators[(this.bindIndicatorSubRange + 4)] & 0xFFFF);
/*      */     }
/*      */     
/*      */     SQLException localSQLException1;
/*  419 */     if (paramArrayOfByte1 == null)
/*      */     {
/*      */ 
/*  422 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 431);
/*  423 */       localSQLException1.fillInStackTrace();
/*  424 */       throw localSQLException1;
/*      */     }
/*      */     
/*  427 */     if ((!this.typeOfStatement.isDML()) && (i > 1))
/*      */     {
/*      */ 
/*      */ 
/*  431 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 433);
/*  432 */       localSQLException1.fillInStackTrace();
/*  433 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  438 */     this.rowsProcessed = 0;
/*  439 */     this.options = 0L;
/*  440 */     this.plsql = this.typeOfStatement.isPlsqlOrCall();
/*  441 */     this.sendBindsDefinition = false;
/*      */     
/*      */ 
/*  444 */     if (this.receiveState != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  449 */       this.receiveState = 0;
/*      */     }
/*      */     
/*  452 */     this.rxh.init();
/*  453 */     this.rxd.init();
/*  454 */     this.oer.init();
/*      */     
/*      */ 
/*  457 */     if (paramBoolean5) {
/*  458 */       initDefinesDefinition();
/*      */     }
/*  460 */     if ((this.numberOfBindPositions > 0) && (this.bindIndicators != null))
/*      */     {
/*  462 */       if (this.oacdefBindsSent == null)
/*  463 */         this.oacdefBindsSent = new T4CTTIoac[this.numberOfBindPositions];
/*  464 */       this.sendBindsDefinition = initBindsDefinition(this.oacdefBindsSent);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  474 */     this.options = setOptions(paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean5);
/*      */     
/*  476 */     if ((this.options & 1L) > 0L) {
/*  477 */       this.al8i4[0] = 1L;
/*      */     } else {
/*  479 */       this.al8i4[0] = 0L;
/*      */     }
/*      */     
/*  482 */     if ((this.plsql) || (this.typeOfStatement.isOTHER())) {
/*  483 */       this.al8i4[1] = 1L;
/*  484 */     } else if (paramBoolean4)
/*      */     {
/*  486 */       if ((paramBoolean3) && (this.oracleStatement.connection.useFetchSizeWithLongColumn)) {
/*  487 */         this.al8i4[1] = this.rowsToFetch;
/*      */       } else {
/*  489 */         this.al8i4[1] = 0L;
/*      */       }
/*  491 */     } else if (this.typeOfStatement.isDML())
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  497 */       this.al8i4[1] = (i == 0 ? this.oracleStatement.batch : i);
/*      */     }
/*  499 */     else if ((paramBoolean3) && (!paramBoolean4))
/*      */     {
/*  501 */       this.al8i4[1] = this.rowsToFetch;
/*      */     } else {
/*  503 */       this.al8i4[1] = 0L;
/*      */     }
/*      */     
/*  506 */     if (this.typeOfStatement.isSELECT()) {
/*  507 */       this.al8i4[7] = 1L;
/*      */     } else {
/*  509 */       this.al8i4[7] = 0L;
/*      */     }
/*  511 */     long l = this.oracleStatement.inScn;
/*  512 */     int j = (int)l;
/*  513 */     int k = (int)(l >> 32);
/*  514 */     this.al8i4[5] = j;
/*  515 */     this.al8i4[6] = k;
/*      */     
/*  517 */     this.rowsProcessed = 0;
/*  518 */     this.aFetchWasDone = false;
/*      */     
/*      */ 
/*  521 */     this.rxd.setNumberOfColumns(this.definesLength);
/*      */     
/*  523 */     if (((this.options & 0x40) != 0L) && ((this.options & 0x20) == 0L) && ((this.options & 1L) == 0L) && ((this.options & 0x8) == 0L) && ((this.options & 0x10) == 0L) && (!this.oracleStatement.needToSendOalToFetch))
/*      */     {
/*      */ 
/*  526 */       setFunCode((short)5);
/*      */     } else {
/*  528 */       setFunCode((short)94);
/*      */     }
/*  530 */     this.nonFatalIOExceptions = null;
/*  531 */     doRPC();
/*      */     
/*      */ 
/*      */ 
/*  535 */     if ((this.options & 0x20) != 0L) {
/*  536 */       this.oracleStatement.inScn = 0L;
/*      */     }
/*      */     
/*  539 */     this.ibtBindIndicators = null;
/*  540 */     this.ibtBindChars = null;
/*  541 */     this.ibtBindBytes = null;
/*  542 */     this.tmpBindsByteArray = null;
/*  543 */     this.outBindAccessors = null;
/*  544 */     this.bindBytes = null;
/*  545 */     this.bindChars = null;
/*  546 */     this.bindIndicators = null;
/*  547 */     this.oracleStatement = null;
/*      */     
/*  549 */     if (this.nonFatalIOExceptions != null)
/*      */     {
/*  551 */       IOException localIOException = (IOException)this.nonFatalIOExceptions.get(0);
/*      */       try
/*      */       {
/*  554 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 266);
/*  555 */         localSQLException2.fillInStackTrace();
/*  556 */         throw localSQLException2;
/*      */ 
/*      */ 
/*      */       }
/*      */       catch (SQLException localSQLException3)
/*      */       {
/*      */ 
/*      */ 
/*  564 */         localSQLException3.initCause(localIOException);
/*  565 */         throw localSQLException3;
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
/*      */   void readBVC()
/*      */     throws IOException, SQLException
/*      */   {
/*  579 */     int i = this.meg.unmarshalUB2();
/*      */     
/*      */ 
/*  582 */     this.rxd.unmarshalBVC(i);
/*      */   }
/*      */   
/*      */ 
/*      */   void readIOV()
/*      */     throws IOException, SQLException
/*      */   {
/*  589 */     T4CTTIiov localT4CTTIiov = new T4CTTIiov(this.connection, this.rxh, this.rxd);
/*      */     
/*  591 */     localT4CTTIiov.init();
/*  592 */     localT4CTTIiov.unmarshalV10();
/*      */     
/*      */ 
/*  595 */     if ((this.oracleStatement.returnParamAccessors == null) && (!localT4CTTIiov.isIOVectorEmpty()))
/*      */     {
/*      */ 
/*  598 */       byte[] arrayOfByte = localT4CTTIiov.getIOVector();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  603 */       this.outBindAccessors = localT4CTTIiov.processRXD(this.outBindAccessors, this.numberOfBindPositions, this.bindBytes, this.bindChars, this.bindIndicators, this.bindIndicatorSubRange, this.conversion, this.tmpBindsByteArray, arrayOfByte, this.parameterStream, this.parameterDatum, this.parameterOtype, this.oracleStatement, null, null, null);
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
/*      */   void readRXH()
/*      */     throws IOException, SQLException
/*      */   {
/*  619 */     this.rxh.init();
/*  620 */     this.rxh.unmarshalV10(this.rxd);
/*      */     SQLException localSQLException1;
/*  622 */     if (this.rxh.uacBufLength > 0)
/*      */     {
/*      */ 
/*  625 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 405);
/*  626 */       localSQLException1.fillInStackTrace();
/*  627 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*  631 */     if ((this.rxh.rxhflg & 0x8) == 8)
/*      */     {
/*      */ 
/*      */ 
/*  635 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 449);
/*  636 */       localSQLException1.fillInStackTrace();
/*  637 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*  641 */     if ((this.rxh.rxhflg & 0x10) == 16)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  647 */       for (int i = 0; i < this.definesAccessors.length; i++)
/*      */       {
/*  649 */         if ((this.definesAccessors[i].udskpos >= 0) && (this.definesAccessors[i].udskpos != i))
/*      */         {
/*      */ 
/*  652 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 450);
/*  653 */           localSQLException2.fillInStackTrace();
/*  654 */           throw localSQLException2;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void processSLG()
/*      */     throws IOException, SQLException
/*      */   {
/*  727 */     readRXH();
/*      */     
/*      */ 
/*  730 */     int[] arrayOfInt = new int[this.numberOfBindPositions];
/*  731 */     for (int i = 0; i < this.numberOfBindPositions; i++) {
/*  732 */       arrayOfInt[i] = this.oacdefBindsSent[i].oacmxl;
/*      */     }
/*      */     
/*  735 */     this.nonFatalIOExceptions = marshalBinds(arrayOfInt, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean readRXD()
/*      */     throws IOException, SQLException
/*      */   {
/*  748 */     this.aFetchWasDone = true;
/*      */     
/*      */ 
/*  751 */     if ((this.oracleStatement.returnParamAccessors != null) && (this.numberOfBindPositions > 0))
/*      */     {
/*      */ 
/*  754 */       int i = 0;
/*  755 */       for (int j = 0; j < this.oracleStatement.numberOfBindPositions; j++)
/*      */       {
/*  757 */         Accessor localAccessor = this.oracleStatement.returnParamAccessors[j];
/*  758 */         if (localAccessor != null)
/*      */         {
/*  760 */           int k = (int)this.meg.unmarshalUB4();
/*  761 */           if (i == 0)
/*      */           {
/*  763 */             this.oracleStatement.rowsDmlReturned = k;
/*  764 */             this.oracleStatement.allocateDmlReturnStorage();
/*  765 */             this.oracleStatement.setupReturnParamAccessors();
/*  766 */             i = 1;
/*      */           }
/*      */           
/*  769 */           for (int m = 0; m < k; m++)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*  774 */             localAccessor.unmarshalOneRow(); }
/*      */         }
/*      */       }
/*  777 */       this.oracleStatement.returnParamsFetched = true;
/*      */ 
/*      */     }
/*  780 */     else if ((this.iovProcessed) || ((this.outBindAccessors != null) && (this.definesAccessors == null)))
/*      */     {
/*      */ 
/*      */ 
/*  784 */       if (this.rxd.unmarshal(this.outBindAccessors, this.numberOfBindPositions))
/*      */       {
/*  786 */         return true;
/*      */       }
/*      */       
/*      */     }
/*  790 */     else if (this.rxd.unmarshal(this.definesAccessors, this.definesLength))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  796 */       return true;
/*      */     }
/*      */     
/*  799 */     return false;
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
/*      */   void readRPA()
/*      */     throws IOException, SQLException
/*      */   {
/*  825 */     int i = this.meg.unmarshalUB2();
/*  826 */     int[] arrayOfInt = new int[i];
/*      */     
/*  828 */     for (int j = 0; j < i; j++) {
/*  829 */       arrayOfInt[j] = ((int)this.meg.unmarshalUB4());
/*      */     }
/*  831 */     j = arrayOfInt[0];
/*  832 */     int k = arrayOfInt[1];
/*  833 */     long l1 = j & 0xFFFFFFFF | (k & 0xFFFFFFFF) << 32;
/*      */     
/*      */ 
/*  836 */     if (l1 != 0L) {
/*  837 */       this.oracleStatement.connection.outScn = l1;
/*      */     }
/*      */     
/*      */ 
/*  841 */     this.cursor = arrayOfInt[2];
/*      */     
/*      */ 
/*  844 */     int m = this.meg.unmarshalUB2();
/*      */     
/*  846 */     byte[] arrayOfByte1 = null;
/*  847 */     if (m > 0) {
/*  848 */       arrayOfByte1 = this.meg.unmarshalNBytes(m);
/*      */     }
/*      */     
/*  851 */     int n = this.meg.unmarshalUB2();
/*      */     
/*  853 */     KeywordValue[] arrayOfKeywordValue = new KeywordValue[n];
/*  854 */     for (String str1 = 0; str1 < n; str1++) {
/*  855 */       arrayOfKeywordValue[str1] = KeywordValueI.unmarshal(this.meg);
/*      */     }
/*  857 */     this.connection.updateSessionProperties(arrayOfKeywordValue);
/*      */     
/*      */ 
/*  860 */     this.oracleStatement.dcnQueryId = -1L;
/*  861 */     this.oracleStatement.dcnTableName = null;
/*  862 */     if (this.connection.getTTCVersion() >= 4)
/*      */     {
/*  864 */       str1 = (int)this.meg.unmarshalUB4();
/*  865 */       byte[] arrayOfByte2 = this.meg.unmarshalNBytes(str1);
/*  866 */       if ((str1 > 0) && (this.registration != null))
/*      */       {
/*  868 */         int i1 = 0;
/*  869 */         Properties localProperties = this.registration.getRegistrationOptions();
/*  870 */         if (localProperties != null)
/*      */         {
/*  872 */           str2 = localProperties.getProperty("DCN_QUERY_CHANGE_NOTIFICATION");
/*  873 */           if ((str2 != null) && (str2.compareToIgnoreCase("true") == 0))
/*  874 */             i1 = 1;
/*      */         }
/*  876 */         String str2 = str1;
/*  877 */         int i2; if (i1 != 0) {
/*  878 */           i2 = str1 - 8;
/*      */         }
/*  880 */         String str3 = new String(arrayOfByte2, 0, i2);
/*  881 */         char[] arrayOfChar = { '\000' };
/*  882 */         String[] arrayOfString = str3.split(new String(arrayOfChar));
/*  883 */         this.registration.addTablesName(arrayOfString, arrayOfString.length);
/*      */         
/*  885 */         this.oracleStatement.dcnTableName = arrayOfString;
/*      */         
/*  887 */         if (i1 != 0)
/*      */         {
/*      */ 
/*  890 */           int i3 = arrayOfByte2[(str1 - 1)] & 0xFF | (arrayOfByte2[(str1 - 2)] & 0xFF) << 8 | (arrayOfByte2[(str1 - 3)] & 0xFF) << 16 | (arrayOfByte2[(str1 - 4)] & 0xFF) << 24;
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  895 */           int i4 = arrayOfByte2[(str1 - 5)] & 0xFF | (arrayOfByte2[(str1 - 6)] & 0xFF) << 8 | (arrayOfByte2[(str1 - 7)] & 0xFF) << 16 | (arrayOfByte2[(str1 - 8)] & 0xFF) << 24;
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  900 */           long l2 = i4 & 0xFFFFFFFF | (i3 & 0xFFFFFFFF) << 32;
/*  901 */           this.oracleStatement.dcnQueryId = l2;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void readDCB()
/*      */     throws IOException, SQLException
/*      */   {
/*  914 */     this.dcb.init(this.oracleStatement, 0);
/*      */     
/*  916 */     this.definesAccessors = this.dcb.receive(this.definesAccessors);
/*  917 */     this.numberOfDefinePositions = this.dcb.numuds;
/*  918 */     this.definesLength = this.numberOfDefinePositions;
/*      */     
/*  920 */     this.rxd.setNumberOfColumns(this.numberOfDefinePositions);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void processError()
/*      */     throws SQLException
/*      */   {
/*  932 */     this.cursor = this.oer.currCursorID;
/*      */     
/*      */ 
/*      */ 
/*  936 */     this.rowsProcessed = this.oer.getCurRowNumber();
/*      */     
/*      */ 
/*  939 */     if ((this.typeOfStatement.isSELECT()) && (this.oer.retCode == 1403))
/*      */     {
/*  941 */       this.aFetchWasDone = true;
/*      */     }
/*  943 */     if ((!this.typeOfStatement.isSELECT()) || ((this.typeOfStatement.isSELECT()) && (this.oer.retCode != 1403)))
/*      */     {
/*      */ 
/*  946 */       if ((this.oracleStatement.connection.calculateChecksum) && (this.oer.retCode != 0))
/*      */       {
/*  948 */         long l = this.oer.updateChecksum(this.oracleStatement.checkSum);
/*  949 */         this.oracleStatement.checkSum = l;
/*      */       }
/*  951 */       this.oer.processError(this.oracleStatement);
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
/*      */   int getCursorId()
/*      */   {
/*  964 */     return this.cursor;
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
/*      */   void continueReadRow(int paramInt, OracleStatement paramOracleStatement)
/*      */     throws SQLException, IOException
/*      */   {
/*      */     try
/*      */     {
/*  981 */       this.oracleStatement = paramOracleStatement;
/*      */       
/*      */ 
/*      */ 
/*  985 */       this.receiveState = 2;
/*      */       
/*  987 */       if (this.rxd.unmarshal(this.definesAccessors, paramInt, this.definesLength))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  993 */         this.receiveState = 3;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*  999 */         resumeReceive();
/*      */       }
/*      */     }
/*      */     finally {
/* 1003 */       this.oracleStatement = null;
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
/*      */   int getNumRows()
/*      */   {
/* 1017 */     int i = 0;
/*      */     
/* 1019 */     if (this.typeOfStatement == null)
/* 1020 */       return i;
/* 1021 */     if (this.receiveState == 3) {
/* 1022 */       i = -2;
/* 1023 */     } else if (this.typeOfStatement != null)
/*      */     {
/*      */ 
/* 1026 */       switch (this.typeOfStatement)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       case DELETE: 
/*      */       case INSERT: 
/*      */       case MERGE: 
/*      */       case UPDATE: 
/*      */       case ALTER_SESSION: 
/*      */       case OTHER: 
/*      */       case PLSQL_BLOCK: 
/*      */       case CALL_BLOCK: 
/* 1040 */         i = this.rowsProcessed;
/*      */         
/* 1042 */         break;
/*      */       
/*      */       case SELECT_FOR_UPDATE: 
/*      */       case SELECT: 
/* 1046 */         i = (this.definesAccessors != null) && (this.definesLength > 0) ? this.definesAccessors[0].lastRowProcessed : 0;
/*      */       }
/*      */       
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1053 */     return i;
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
/*      */   void marshal()
/*      */     throws IOException
/*      */   {
/* 1067 */     if (getFunCode() == 5)
/*      */     {
/* 1069 */       this.meg.marshalSWORD(this.cursor);
/* 1070 */       this.meg.marshalSWORD((int)this.al8i4[1]);
/*      */     }
/*      */     else
/*      */     {
/* 1074 */       if (this.oracleStatement.needToSendOalToFetch) {
/* 1075 */         this.oracleStatement.needToSendOalToFetch = false;
/*      */       }
/* 1077 */       marshalPisdef();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1083 */       this.meg.marshalCHR(this.sqlStmt);
/*      */       
/*      */ 
/* 1086 */       this.meg.marshalUB4Array(this.al8i4);
/*      */       
/*      */ 
/* 1089 */       int[] arrayOfInt = new int[this.numberOfBindPositions];
/*      */       
/* 1091 */       for (int i = 0; i < this.numberOfBindPositions; i++)
/*      */       {
/* 1093 */         arrayOfInt[i] = this.oacdefBindsSent[i].oacmxl;
/*      */       }
/*      */       
/*      */ 
/* 1097 */       if (((this.options & 0x8) != 0L) && (this.numberOfBindPositions > 0) && (this.bindIndicators != null) && (this.sendBindsDefinition))
/*      */       {
/* 1099 */         marshalBindsTypes(this.oacdefBindsSent);
/*      */       }
/*      */       
/*      */ 
/* 1103 */       if ((this.connection.getTTCVersion() >= 2) && ((this.options & 0x10) != 0L))
/*      */       {
/* 1105 */         for (i = 0; i < this.defCols; i++) {
/* 1106 */           this.oacdefDefines[i].marshal();
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1112 */       if (((this.options & 0x20) != 0L) && (this.numberOfBindPositions > 0) && (this.bindIndicators != null))
/*      */       {
/* 1114 */         this.nonFatalIOExceptions = marshalBinds(arrayOfInt, false);
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
/*      */   void marshalPisdef()
/*      */     throws IOException
/*      */   {
/* 1134 */     this.meg.marshalUB4(this.options);
/*      */     
/*      */ 
/* 1137 */     this.meg.marshalSWORD(this.cursor);
/*      */     
/*      */ 
/*      */ 
/* 1141 */     if (this.sqlStmt.length == 0) {
/* 1142 */       this.meg.marshalNULLPTR();
/*      */     } else {
/* 1144 */       this.meg.marshalPTR();
/*      */     }
/*      */     
/* 1147 */     this.meg.marshalSWORD(this.sqlStmt.length);
/*      */     
/*      */ 
/*      */ 
/* 1151 */     if (this.al8i4.length == 0) {
/* 1152 */       this.meg.marshalNULLPTR();
/*      */     } else {
/* 1154 */       this.meg.marshalPTR();
/*      */     }
/*      */     
/* 1157 */     this.meg.marshalSWORD(this.al8i4.length);
/*      */     
/*      */ 
/*      */ 
/* 1161 */     this.meg.marshalNULLPTR();
/*      */     
/*      */ 
/* 1164 */     this.meg.marshalNULLPTR();
/*      */     
/*      */ 
/* 1167 */     if (((this.options & 0x40) == 0L) && ((this.options & 0x20) != 0L) && ((this.options & 1L) != 0L) && (this.typeOfStatement.isSELECT()))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1173 */       this.meg.marshalUB4(Long.MAX_VALUE);
/* 1174 */       this.meg.marshalUB4(this.rowsToFetch);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1179 */       this.meg.marshalUB4(0L);
/*      */       
/* 1181 */       this.meg.marshalUB4(0L);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1186 */     if (!this.typeOfStatement.isPlsqlOrCall()) {
/* 1187 */       this.meg.marshalUB4(2147483647L);
/*      */     } else {
/* 1189 */       this.meg.marshalUB4(32760L);
/*      */     }
/*      */     
/* 1192 */     if (((this.options & 0x8) != 0L) && (this.numberOfBindPositions > 0) && (this.sendBindsDefinition))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1197 */       this.meg.marshalPTR();
/*      */       
/*      */ 
/* 1200 */       this.meg.marshalSWORD(this.numberOfBindPositions);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 1206 */       this.meg.marshalNULLPTR();
/*      */       
/*      */ 
/* 1209 */       this.meg.marshalSWORD(0);
/*      */     }
/*      */     
/*      */ 
/* 1213 */     this.meg.marshalNULLPTR();
/*      */     
/*      */ 
/* 1216 */     this.meg.marshalNULLPTR();
/*      */     
/*      */ 
/* 1219 */     this.meg.marshalNULLPTR();
/*      */     
/*      */ 
/* 1222 */     this.meg.marshalNULLPTR();
/*      */     
/*      */ 
/* 1225 */     this.meg.marshalNULLPTR();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1230 */     if (this.connection.getTTCVersion() >= 2)
/*      */     {
/* 1232 */       if ((this.defCols > 0) && ((this.options & 0x10) != 0L))
/*      */       {
/*      */ 
/*      */ 
/* 1236 */         this.meg.marshalPTR();
/*      */         
/*      */ 
/* 1239 */         this.meg.marshalSWORD(this.defCols);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 1245 */         this.meg.marshalNULLPTR();
/*      */         
/*      */ 
/* 1248 */         this.meg.marshalSWORD(0);
/*      */       }
/*      */     }
/*      */     
/* 1252 */     if (this.connection.getTTCVersion() >= 4)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1261 */       int i = 0;
/* 1262 */       int j = 0;
/* 1263 */       if (this.registration != null)
/*      */       {
/* 1265 */         long l = this.registration.getRegId();
/* 1266 */         i = (int)(l & 0xFFFFFFFFFFFFFFFF);
/* 1267 */         j = (int)((l & 0xFFFFFFFF00000000) >> 32);
/*      */       }
/*      */       
/*      */ 
/* 1271 */       this.meg.marshalUB4(i);
/*      */       
/* 1273 */       this.meg.marshalNULLPTR();
/* 1274 */       this.meg.marshalPTR();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1295 */       if (this.connection.getTTCVersion() >= 5)
/*      */       {
/* 1297 */         this.meg.marshalNULLPTR();
/* 1298 */         this.meg.marshalUB4(0L);
/* 1299 */         this.meg.marshalNULLPTR();
/* 1300 */         this.meg.marshalUB4(0L);
/*      */         
/* 1302 */         this.meg.marshalUB4(j);
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
/*      */   boolean initBindsDefinition(T4CTTIoac[] paramArrayOfT4CTTIoac)
/*      */     throws SQLException, IOException
/*      */   {
/* 1317 */     boolean bool = false;
/*      */     
/* 1319 */     if (paramArrayOfT4CTTIoac.length != this.numberOfBindPositions)
/*      */     {
/* 1321 */       bool = true;
/* 1322 */       paramArrayOfT4CTTIoac = new T4CTTIoac[this.numberOfBindPositions];
/*      */     }
/*      */     
/*      */ 
/* 1326 */     short[] arrayOfShort = this.bindIndicators;
/*      */     
/*      */ 
/* 1329 */     int j = 0;
/*      */     
/*      */ 
/* 1332 */     int m = 0;
/*      */     
/*      */ 
/* 1335 */     for (int n = 0; n < this.numberOfBindPositions; n++)
/*      */     {
/* 1337 */       T4CTTIoac localT4CTTIoac = new T4CTTIoac(this.connection);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1342 */       int i = this.bindIndicatorSubRange + 5 + 10 * n;
/*      */       
/*      */ 
/*      */ 
/* 1346 */       short s = arrayOfShort[(i + 9)];
/*      */       
/*      */ 
/* 1349 */       int k = arrayOfShort[(i + 0)] & 0xFFFF;
/*      */       
/*      */ 
/*      */       Object localObject;
/*      */       
/* 1354 */       switch (k)
/*      */       {
/*      */ 
/*      */       case 8: 
/*      */       case 24: 
/* 1359 */         if (this.plsql) {
/* 1360 */           j = 32760;
/*      */         } else {
/* 1362 */           j = Integer.MAX_VALUE;
/*      */         }
/* 1364 */         localT4CTTIoac.init((short)k, j);
/* 1365 */         localT4CTTIoac.setFormOfUse(s);
/* 1366 */         localT4CTTIoac.setCharset(s == 2 ? this.NCharSet : this.dbCharSet);
/*      */         
/* 1368 */         break;
/*      */       
/*      */ 
/*      */ 
/*      */       case 998: 
/* 1373 */         if ((this.outBindAccessors != null) && (this.outBindAccessors[n] != null))
/*      */         {
/* 1375 */           PlsqlIndexTableAccessor localPlsqlIndexTableAccessor = (PlsqlIndexTableAccessor)this.outBindAccessors[n];
/* 1376 */           localT4CTTIoac.init((short)localPlsqlIndexTableAccessor.elementInternalType, localPlsqlIndexTableAccessor.elementMaxLen);
/* 1377 */           localT4CTTIoac.setMal(localPlsqlIndexTableAccessor.maxNumberOfElements);
/* 1378 */           localT4CTTIoac.addFlg((short)64);
/* 1379 */           m++;
/*      */         }
/* 1381 */         else if (this.ibtBindIndicators[(6 + m * 8)] != 0)
/*      */         {
/* 1383 */           int i1 = this.ibtBindIndicators[(6 + m * 8)];
/* 1384 */           int i3 = (this.ibtBindIndicators[(6 + m * 8 + 2)] & 0xFFFF) << 16 | this.ibtBindIndicators[(6 + m * 8 + 3)] & 0xFFFF;
/*      */           
/*      */ 
/*      */ 
/* 1388 */           j = this.ibtBindIndicators[(6 + m * 8 + 1)] * this.conversion.sMaxCharSize;
/*      */           
/* 1390 */           localT4CTTIoac.init((short)i1, j);
/* 1391 */           localT4CTTIoac.setMal(i3);
/* 1392 */           localT4CTTIoac.addFlg((short)64);
/* 1393 */           m++;
/*      */         }
/*      */         else
/*      */         {
/* 1397 */           localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), "INTERNAL ERROR: Binding PLSQL index-by table but no type defined", -1);
/* 1398 */           ((SQLException)localObject).fillInStackTrace();
/* 1399 */           throw ((Throwable)localObject);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */         break;
/*      */       case 109: 
/*      */       case 111: 
/* 1407 */         if ((this.outBindAccessors != null) && (this.outBindAccessors[n] != null))
/*      */         {
/* 1409 */           if (this.outBindAccessors[n].internalOtype != null)
/*      */           {
/* 1411 */             localT4CTTIoac.init((short)k, k == 109 ? 11 : 4000);
/*      */             
/* 1413 */             localT4CTTIoac.setADT((OracleTypeADT)((TypeAccessor)this.outBindAccessors[n]).internalOtype);
/*      */           }
/*      */           
/*      */         }
/* 1417 */         else if ((this.parameterOtype != null) && (this.parameterOtype[this.oracleStatement.firstRowInBatch] != null))
/*      */         {
/*      */ 
/* 1420 */           localT4CTTIoac.init((short)k, k == 109 ? 11 : 4000);
/* 1421 */           localT4CTTIoac.setADT(this.parameterOtype[this.oracleStatement.firstRowInBatch][n]);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 1427 */           localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), "INTERNAL ERROR: Binding NAMED_TYPE but no type defined", -1);
/* 1428 */           ((SQLException)localObject).fillInStackTrace();
/* 1429 */           throw ((Throwable)localObject);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */         break;
/*      */       case 994: 
/* 1437 */         localObject = this.oracleStatement.returnParamMeta;
/* 1438 */         k = localObject[(3 + n * 4 + 0)];
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1443 */         j = localObject[(3 + n * 4 + 2)];
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1448 */         s = (short)localObject[(3 + n * 4 + 3)];
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1453 */         if ((k == 109) || (k == 111))
/*      */         {
/* 1455 */           TypeAccessor localTypeAccessor = (TypeAccessor)this.oracleStatement.returnParamAccessors[n];
/*      */           
/*      */ 
/* 1458 */           localT4CTTIoac.init((short)k, k == 109 ? 11 : 4000);
/*      */           
/* 1460 */           localT4CTTIoac.setADT((OracleTypeADT)localTypeAccessor.internalOtype);
/*      */         }
/*      */         else
/*      */         {
/* 1464 */           localT4CTTIoac.init((short)k, j);
/* 1465 */           localT4CTTIoac.setFormOfUse(s);
/* 1466 */           localT4CTTIoac.setCharset(s == 2 ? this.NCharSet : this.dbCharSet);
/*      */         }
/*      */         
/*      */ 
/* 1470 */         break;
/*      */       
/*      */       case 180: 
/* 1473 */         j = arrayOfShort[(i + 1)] & 0xFFFF;
/*      */         
/*      */ 
/*      */ 
/* 1477 */         localT4CTTIoac.init((short)k, j);
/* 1478 */         localT4CTTIoac.addFlg2(134217728);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1486 */         localT4CTTIoac.setTimestampFractionalSecondsPrecision((short)9);
/*      */         
/* 1488 */         int i2 = ((this.bindIndicators[(this.bindIndicatorSubRange + 3)] & 0xFFFF) << 16) + (this.bindIndicators[(this.bindIndicatorSubRange + 4)] & 0xFFFF);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1493 */         if (i2 == 1)
/*      */         {
/* 1495 */           int i4 = ((arrayOfShort[(i + 7)] & 0xFFFF) << 16) + (arrayOfShort[(i + 8)] & 0xFFFF);
/*      */           
/*      */ 
/* 1498 */           int i5 = arrayOfShort[i4];
/*      */           
/* 1500 */           if (i5 == 7)
/*      */           {
/* 1502 */             localT4CTTIoac.setTimestampFractionalSecondsPrecision((short)0);
/*      */           }
/*      */         }
/*      */         
/* 1506 */         break;
/*      */       
/*      */       case 182: 
/*      */       case 183: 
/* 1510 */         j = arrayOfShort[(i + 1)] & 0xFFFF;
/*      */         
/*      */ 
/*      */ 
/* 1514 */         localT4CTTIoac.init((short)k, j);
/* 1515 */         localT4CTTIoac.setFormOfUse(s);
/* 1516 */         localT4CTTIoac.setCharset(s == 2 ? this.NCharSet : this.dbCharSet);
/*      */         
/* 1518 */         localT4CTTIoac.setPrecision((short)9);
/*      */         
/* 1520 */         break;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       default: 
/* 1527 */         j = arrayOfShort[(i + 1)] & 0xFFFF;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1534 */         if (j == 0)
/*      */         {
/* 1536 */           j = arrayOfShort[(i + 2)] & 0xFFFF;
/*      */           
/*      */ 
/*      */ 
/* 1540 */           if (k == 996) {
/* 1541 */             j *= 2;
/*      */           }
/* 1543 */           else if (j > 1) {
/* 1544 */             j--;
/*      */           }
/*      */           
/* 1547 */           if (s == 2) {
/* 1548 */             j *= this.conversion.maxNCharSize;
/*      */           }
/*      */           
/*      */ 
/* 1552 */           if ((this.typeOfStatement == OracleStatement.SqlKind.PLSQL_BLOCK) || ((this.connection.versionNumber >= 10200) && (this.typeOfStatement == OracleStatement.SqlKind.CALL_BLOCK)))
/*      */           {
/*      */ 
/*      */ 
/* 1556 */             if (j == 0) {
/* 1557 */               j = 32766;
/*      */             } else {
/* 1559 */               j *= this.conversion.sMaxCharSize;
/*      */             }
/* 1561 */           } else if (this.typeOfStatement == OracleStatement.SqlKind.CALL_BLOCK)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1568 */             if (j < 4001) {
/* 1569 */               j = 4001;
/*      */             }
/*      */             
/*      */ 
/*      */           }
/* 1574 */           else if (s != 2)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1585 */             if ((((T4CConnection)this.oracleStatement.connection).retainV9BindBehavior) && (j <= 4000))
/*      */             {
/*      */ 
/*      */ 
/* 1589 */               j = Math.min(j * this.conversion.sMaxCharSize, 4000);
/*      */ 
/*      */             }
/*      */             else
/*      */             {
/* 1594 */               j *= this.conversion.sMaxCharSize;
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/* 1600 */           if (j == 0) {
/* 1601 */             j = 32;
/*      */           }
/*      */         }
/* 1604 */         localT4CTTIoac.init((short)k, j);
/* 1605 */         localT4CTTIoac.setFormOfUse(s);
/* 1606 */         localT4CTTIoac.setCharset(s == 2 ? this.NCharSet : this.dbCharSet);
/*      */       }
/*      */       
/*      */       
/*      */ 
/* 1611 */       if ((paramArrayOfT4CTTIoac[n] == null) || (!localT4CTTIoac.isOldSufficient(paramArrayOfT4CTTIoac[n])))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1618 */         paramArrayOfT4CTTIoac[n] = localT4CTTIoac;
/* 1619 */         bool = true;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1625 */     if (bool) {
/* 1626 */       this.oracleStatement.nbPostPonedColumns[0] = 0;
/*      */     }
/* 1628 */     return bool;
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
/*      */   void initDefinesDefinition()
/*      */     throws SQLException, IOException
/*      */   {
/* 1642 */     this.defCols = 0;
/*      */     
/* 1644 */     for (int i = 0; i < this.definedColumnType.length; i++)
/*      */     {
/* 1646 */       if (this.definedColumnType[i] == 0)
/*      */         break;
/* 1648 */       this.defCols += 1;
/*      */     }
/* 1650 */     this.oacdefDefines = new T4CTTIoac[this.defCols];
/* 1651 */     i = 0;
/* 1652 */     int j = 0;
/* 1653 */     int k = 0;
/* 1654 */     short s1 = 0;
/* 1655 */     for (int m = 0; m < this.oacdefDefines.length; m++)
/*      */     {
/* 1657 */       this.oacdefDefines[m] = new T4CTTIoac(this.connection);
/* 1658 */       s1 = (short)this.oracleStatement.getInternalType(this.definedColumnType[m]);
/* 1659 */       j = Integer.MAX_VALUE;
/* 1660 */       i = 0;
/* 1661 */       k = 0;
/* 1662 */       short s2 = 1;
/* 1663 */       if ((this.definedColumnFormOfUse != null) && (this.definedColumnFormOfUse.length > m) && (this.definedColumnFormOfUse[m] == 2))
/*      */       {
/*      */ 
/*      */ 
/* 1667 */         s2 = 2;
/*      */       }
/* 1669 */       if (s1 == 8) {
/* 1670 */         s1 = 1;
/* 1671 */       } else if (s1 == 24) {
/* 1672 */         s1 = 23;
/* 1673 */       } else if ((s1 == 1) || (s1 == 96))
/*      */       {
/*      */ 
/* 1676 */         s1 = 1;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1682 */         j = 4000 * this.conversion.sMaxCharSize;
/*      */         
/* 1684 */         if ((this.definedColumnSize != null) && (this.definedColumnSize.length > m) && (this.definedColumnSize[m] > 0))
/*      */         {
/*      */ 
/*      */ 
/* 1688 */           j = this.definedColumnSize[m] * this.conversion.sMaxCharSize;
/*      */         }
/* 1690 */       } else if ((this.connection.useLobPrefetch) && ((s1 == 113) || (s1 == 112) || (s1 == 114)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 1695 */         j = 0;
/* 1696 */         i = 33554432;
/* 1697 */         if ((this.definedColumnSize != null) && (this.definedColumnSize.length > m) && (this.definedColumnSize[m] > 0))
/*      */         {
/*      */ 
/*      */ 
/* 1701 */           k = this.definedColumnSize[m];
/*      */         }
/* 1703 */       } else if (s1 == 23) {
/* 1704 */         j = 4000;
/*      */       }
/* 1706 */       this.oacdefDefines[m].init(s1, j);
/* 1707 */       this.oacdefDefines[m].addFlg2(i);
/* 1708 */       this.oacdefDefines[m].setMxlc(k);
/* 1709 */       this.oacdefDefines[m].setFormOfUse(s2);
/* 1710 */       this.oacdefDefines[m].setCharset(s2 == 2 ? this.NCharSet : this.dbCharSet);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void marshalBindsTypes(T4CTTIoac[] paramArrayOfT4CTTIoac)
/*      */     throws IOException
/*      */   {
/* 1720 */     if (paramArrayOfT4CTTIoac == null) {
/* 1721 */       return;
/*      */     }
/* 1723 */     for (int i = 0; i < paramArrayOfT4CTTIoac.length; i++)
/*      */     {
/* 1725 */       paramArrayOfT4CTTIoac[i].marshal();
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
/*      */   Vector<IOException> marshalBinds(int[] paramArrayOfInt, boolean paramBoolean)
/*      */     throws IOException
/*      */   {
/* 1742 */     Vector localVector1 = null;
/* 1743 */     int i = ((this.bindIndicators[(this.bindIndicatorSubRange + 3)] & 0xFFFF) << 16) + (this.bindIndicators[(this.bindIndicatorSubRange + 4)] & 0xFFFF);
/*      */     
/*      */ 
/*      */     int j;
/*      */     
/*      */ 
/*      */     boolean bool;
/*      */     
/* 1751 */     if (paramBoolean)
/*      */     {
/* 1753 */       j = this.rxh.iterNum;
/* 1754 */       bool = true;
/*      */     }
/*      */     else
/*      */     {
/* 1758 */       j = 0;
/* 1759 */       bool = false;
/*      */     }
/* 1763 */     for (; 
/*      */         
/* 1763 */         j < i; j++)
/*      */     {
/* 1765 */       int k = this.oracleStatement.firstRowInBatch + j;
/* 1766 */       InputStream[] arrayOfInputStream = null;
/* 1767 */       if (this.parameterStream != null)
/* 1768 */         arrayOfInputStream = this.parameterStream[k];
/* 1769 */       byte[][] arrayOfByte = (byte[][])null;
/* 1770 */       if (this.parameterDatum != null)
/* 1771 */         arrayOfByte = this.parameterDatum[k];
/* 1772 */       OracleTypeADT[] arrayOfOracleTypeADT = null;
/* 1773 */       if (this.parameterOtype != null) {
/* 1774 */         arrayOfOracleTypeADT = this.parameterOtype[k];
/*      */       }
/*      */       
/* 1777 */       Vector localVector2 = this.rxd.marshal(this.bindBytes, this.bindChars, this.bindIndicators, this.bindIndicatorSubRange, this.tmpBindsByteArray, this.conversion, arrayOfInputStream, arrayOfByte, arrayOfOracleTypeADT, this.ibtBindBytes, this.ibtBindChars, this.ibtBindIndicators, null, j, paramArrayOfInt, this.plsql, this.oracleStatement.returnParamMeta, this.oracleStatement.nbPostPonedColumns, this.oracleStatement.indexOfPostPonedColumn, bool);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1787 */       bool = false;
/*      */       
/* 1789 */       if (localVector2 != null)
/*      */       {
/* 1791 */         if (localVector1 == null)
/* 1792 */           localVector1 = new Vector();
/* 1793 */         localVector1.addAll(localVector2);
/*      */       }
/*      */     }
/* 1796 */     return localVector1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   long setOptions(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
/*      */     throws SQLException
/*      */   {
/* 1805 */     long l = 0L;
/*      */     
/*      */ 
/* 1808 */     if ((paramBoolean1) && (!paramBoolean2) && (!paramBoolean3)) {
/* 1809 */       l |= 1L;
/* 1810 */     } else if ((paramBoolean1) && (paramBoolean2) && (!paramBoolean3)) {
/* 1811 */       l = 32801L;
/* 1812 */     } else { if ((paramBoolean2) && (paramBoolean3))
/*      */       {
/* 1814 */         if (paramBoolean1) {
/* 1815 */           l |= 1L;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1820 */       switch (this.typeOfStatement)
/*      */       {
/*      */ 
/*      */       case SELECT_FOR_UPDATE: 
/*      */       case SELECT: 
/* 1825 */         l |= 0x8060;
/*      */         
/* 1827 */         break;
/*      */       
/*      */ 
/*      */       case PLSQL_BLOCK: 
/*      */       case CALL_BLOCK: 
/* 1832 */         if (this.numberOfBindPositions > 0)
/*      */         {
/*      */ 
/* 1835 */           l |= 0x420 | (this.oracleStatement.connection.autocommit ? 256 : 0);
/*      */           
/*      */ 
/* 1838 */           if (this.sendBindsDefinition) {
/* 1839 */             l |= 0x8;
/*      */           }
/*      */         } else {
/* 1842 */           l |= 0x20 | (this.oracleStatement.connection.autocommit ? 256 : 0);
/*      */         }
/*      */         
/*      */ 
/* 1846 */         break;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       case DELETE: 
/*      */       case INSERT: 
/*      */       case MERGE: 
/*      */       case UPDATE: 
/*      */       case ALTER_SESSION: 
/*      */       case OTHER: 
/* 1857 */         if (this.oracleStatement.returnParamAccessors != null) {
/* 1858 */           l |= 0x420 | (this.oracleStatement.connection.autocommit ? 256 : 0);
/*      */         }
/*      */         else {
/* 1861 */           l |= 0x8020 | (this.oracleStatement.connection.autocommit ? 256 : 0);
/*      */         }
/*      */         
/* 1864 */         break;
/*      */       
/*      */ 
/*      */ 
/*      */       default: 
/* 1869 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 432);
/* 1870 */         localSQLException.fillInStackTrace();
/* 1871 */         throw localSQLException;
/*      */         
/*      */ 
/*      */ 
/* 1875 */         if ((!paramBoolean1) && (!paramBoolean2) && (paramBoolean3)) {
/* 1876 */           l = 32832L;
/*      */         }
/*      */         else
/*      */         {
/* 1880 */           localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 432);
/* 1881 */           localSQLException.fillInStackTrace();
/* 1882 */           throw localSQLException;
/*      */         }
/*      */         break; }
/*      */       
/*      */     }
/* 1887 */     if (!this.typeOfStatement.isPlsqlOrCall())
/*      */     {
/*      */ 
/*      */ 
/* 1891 */       if ((paramBoolean1) || (paramBoolean2) || (!paramBoolean3))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1898 */         if ((this.numberOfBindPositions > 0) && (this.sendBindsDefinition)) {
/* 1899 */           l |= 0x8;
/*      */         }
/*      */       }
/* 1902 */       if ((this.connection.versionNumber >= 9000) && (paramBoolean4))
/*      */       {
/* 1904 */         l |= 0x10;
/*      */       }
/*      */     }
/*      */     
/* 1908 */     l &= 0xFFFFFFFFFFFFFFFF;
/*      */     
/*      */ 
/* 1911 */     return l;
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
/* 1926 */     return this.connection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1931 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4C8Oall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */