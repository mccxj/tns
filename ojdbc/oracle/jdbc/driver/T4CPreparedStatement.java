/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.sql.SQLException;
/*      */ import oracle.jdbc.internal.OracleStatement.SqlKind;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class T4CPreparedStatement
/*      */   extends OraclePreparedStatement
/*      */ {
/*      */   T4CPreparedStatement(PhysicalConnection paramPhysicalConnection, String paramString, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*   30 */     super(paramPhysicalConnection, paramString, paramPhysicalConnection.defaultExecuteBatch, paramPhysicalConnection.defaultRowPrefetch, paramInt1, paramInt2);
/*      */     
/*      */ 
/*   33 */     this.nbPostPonedColumns = new int[1];
/*   34 */     this.nbPostPonedColumns[0] = 0;
/*   35 */     this.indexOfPostPonedColumn = new int[1][3];
/*   36 */     this.t4Connection = ((T4CConnection)paramPhysicalConnection);
/*      */     
/*   38 */     this.theRowidBinder = theStaticT4CRowidBinder;
/*   39 */     this.theRowidNullBinder = theStaticT4CRowidNullBinder;
/*   40 */     this.theURowidBinder = theStaticT4CURowidBinder;
/*   41 */     this.theURowidNullBinder = theStaticT4CURowidNullBinder;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   48 */   static final byte[] EMPTY_BYTE = new byte[0];
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   T4CConnection t4Connection;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void doOall8(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5)
/*      */     throws SQLException, IOException
/*      */   {
/*   68 */     if ((paramBoolean1) || (paramBoolean4) || (!paramBoolean2)) {
/*   69 */       this.oacdefSent = null;
/*      */     }
/*   71 */     this.t4Connection.assertLoggedOn("oracle.jdbc.driver.T4CPreparedStatement.doOall8");
/*      */     
/*   73 */     if (this.sqlKind == OracleStatement.SqlKind.UNINITIALIZED)
/*      */     {
/*      */ 
/*      */ 
/*   77 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 439, "sqlKind = " + this.sqlKind);
/*   78 */       localSQLException1.fillInStackTrace();
/*   79 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*   83 */     if (paramBoolean3) {
/*   84 */       this.rowPrefetchInLastFetch = this.rowPrefetch;
/*      */     }
/*   86 */     int i = this.numberOfDefinePositions;
/*      */     
/*   88 */     if (this.sqlKind.isDML()) {
/*   89 */       i = 0;
/*      */     }
/*      */     int j;
/*   92 */     if (this.accessors != null)
/*   93 */       for (j = 0; j < this.accessors.length; j++)
/*   94 */         if (this.accessors[j] != null)
/*   95 */           this.accessors[j].lastRowProcessed = 0;
/*   96 */     if (this.outBindAccessors != null)
/*   97 */       for (j = 0; j < this.outBindAccessors.length; j++)
/*   98 */         if (this.outBindAccessors[j] != null)
/*   99 */           this.outBindAccessors[j].lastRowProcessed = 0;
/*  100 */     if (this.returnParamAccessors != null) {
/*  101 */       for (j = 0; j < this.returnParamAccessors.length; j++) {
/*  102 */         if (this.returnParamAccessors[j] != null) {
/*  103 */           this.returnParamAccessors[j].lastRowProcessed = 0;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     int i1;
/*      */     int i2;
/*  110 */     if (this.bindIndicators != null)
/*      */     {
/*  112 */       j = ((this.bindIndicators[(this.bindIndicatorSubRange + 3)] & 0xFFFF) << 16) + (this.bindIndicators[(this.bindIndicatorSubRange + 4)] & 0xFFFF);
/*      */       
/*      */ 
/*  115 */       int k = 0;
/*      */       
/*  117 */       if (this.ibtBindChars != null) {
/*  118 */         k = this.ibtBindChars.length * this.connection.conversion.cMaxCharSize;
/*      */       }
/*  120 */       for (int m = 0; m < this.numberOfBindPositions; m++)
/*      */       {
/*  122 */         int n = this.bindIndicatorSubRange + 5 + 10 * m;
/*      */         
/*      */ 
/*      */ 
/*  126 */         i1 = this.bindIndicators[(n + 2)] & 0xFFFF;
/*      */         
/*      */ 
/*      */ 
/*  130 */         if (i1 != 0)
/*      */         {
/*      */ 
/*  133 */           i2 = this.bindIndicators[(n + 9)] & 0xFFFF;
/*      */           
/*      */ 
/*      */ 
/*  137 */           if (i2 == 2)
/*      */           {
/*  139 */             k = Math.max(i1 * this.connection.conversion.maxNCharSize, k);
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*  144 */             k = Math.max(i1 * this.connection.conversion.cMaxCharSize, k);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  150 */       if (this.tmpBindsByteArray == null)
/*      */       {
/*  152 */         this.tmpBindsByteArray = new byte[k];
/*      */       }
/*  154 */       else if (this.tmpBindsByteArray.length < k)
/*      */       {
/*  156 */         this.tmpBindsByteArray = null;
/*  157 */         this.tmpBindsByteArray = new byte[k];
/*      */ 
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/*  169 */       this.tmpBindsByteArray = null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  174 */     int[] arrayOfInt1 = this.definedColumnType;
/*  175 */     int[] arrayOfInt2 = this.definedColumnSize;
/*  176 */     int[] arrayOfInt3 = this.definedColumnFormOfUse;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  182 */     if ((paramBoolean5) && (paramBoolean4) && (this.sqlObject.includeRowid))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  187 */       arrayOfInt1 = new int[this.definedColumnType.length + 1];
/*  188 */       System.arraycopy(this.definedColumnType, 0, arrayOfInt1, 1, this.definedColumnType.length);
/*  189 */       arrayOfInt1[0] = -8;
/*  190 */       arrayOfInt2 = new int[this.definedColumnSize.length + 1];
/*  191 */       System.arraycopy(this.definedColumnSize, 0, arrayOfInt2, 1, this.definedColumnSize.length);
/*  192 */       arrayOfInt3 = new int[this.definedColumnFormOfUse.length + 1];
/*  193 */       System.arraycopy(this.definedColumnFormOfUse, 0, arrayOfInt3, 1, this.definedColumnFormOfUse.length);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  199 */     allocateTmpByteArray();
/*      */     
/*  201 */     T4C8Oall localT4C8Oall = this.t4Connection.all8;
/*      */     
/*  203 */     this.t4Connection.sendPiggyBackedMessages();
/*      */     
/*      */ 
/*      */     try
/*      */     {
/*  208 */       localT4C8Oall.doOALL(paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5, this.sqlKind, this.cursorId, this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals), this.rowPrefetch, this.outBindAccessors, this.numberOfBindPositions, this.accessors, i, this.bindBytes, this.bindChars, this.bindIndicators, this.bindIndicatorSubRange, this.connection.conversion, this.tmpBindsByteArray, this.parameterStream, this.parameterDatum, this.parameterOtype, this, this.ibtBindBytes, this.ibtBindChars, this.ibtBindIndicators, this.oacdefSent, arrayOfInt1, arrayOfInt2, arrayOfInt3, this.registration);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  220 */       i1 = localT4C8Oall.getCursorId();
/*  221 */       if (i1 != 0) {
/*  222 */         this.cursorId = i1;
/*      */       }
/*  224 */       this.oacdefSent = localT4C8Oall.oacdefBindsSent;
/*      */     }
/*      */     catch (SQLException localSQLException2)
/*      */     {
/*  228 */       i2 = localT4C8Oall.getCursorId();
/*  229 */       if (i2 != 0) {
/*  230 */         this.cursorId = i2;
/*      */       }
/*  232 */       if (localSQLException2.getErrorCode() == DatabaseError.getVendorCode(110))
/*      */       {
/*      */ 
/*  235 */         this.sqlWarning = DatabaseError.addSqlWarning(this.sqlWarning, 110);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  240 */         throw localSQLException2;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void allocateTmpByteArray()
/*      */   {
/*  250 */     if (this.tmpByteArray == null)
/*      */     {
/*      */ 
/*  253 */       this.tmpByteArray = new byte[this.sizeTmpByteArray];
/*      */     }
/*  255 */     else if (this.sizeTmpByteArray > this.tmpByteArray.length)
/*      */     {
/*      */ 
/*      */ 
/*  259 */       this.tmpByteArray = new byte[this.sizeTmpByteArray];
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void releaseBuffers()
/*      */   {
/*  271 */     super.releaseBuffers();
/*  272 */     this.tmpByteArray = null;
/*  273 */     this.tmpBindsByteArray = null;
/*      */     
/*  275 */     this.t4Connection.all8.bindChars = null;
/*  276 */     this.t4Connection.all8.bindBytes = null;
/*  277 */     this.t4Connection.all8.tmpBindsByteArray = null;
/*      */   }
/*      */   
/*      */ 
/*      */   void allocateRowidAccessor()
/*      */     throws SQLException
/*      */   {
/*  284 */     this.accessors[0] = new T4CRowidAccessor(this, 128, 1, -8, false, this.t4Connection.mare);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void reparseOnRedefineIfNeeded()
/*      */     throws SQLException
/*      */   {
/*  297 */     this.needToParse = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void defineColumnTypeInternal(int paramInt1, int paramInt2, int paramInt3, short paramShort, boolean paramBoolean, String paramString)
/*      */     throws SQLException
/*      */   {
/*  308 */     if (this.connection.disableDefinecolumntype)
/*      */     {
/*      */ 
/*  311 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  316 */     if ((paramInt2 == -15) || (paramInt2 == -9) || (paramInt2 == -16))
/*      */     {
/*  318 */       paramShort = 2;
/*      */     }
/*      */     
/*      */ 
/*      */     SQLException localSQLException;
/*      */     
/*  324 */     if (paramInt1 < 1)
/*      */     {
/*  326 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  327 */       localSQLException.fillInStackTrace();
/*  328 */       throw localSQLException;
/*      */     }
/*  330 */     if (paramBoolean)
/*      */     {
/*      */ 
/*      */ 
/*  334 */       if ((paramInt2 == 1) || (paramInt2 == 12) || (paramInt2 == -15) || (paramInt2 == -9))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  340 */         this.sqlWarning = DatabaseError.addSqlWarning(this.sqlWarning, 108);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*  346 */     else if (paramInt3 < 0)
/*      */     {
/*  348 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 53);
/*  349 */       localSQLException.fillInStackTrace();
/*  350 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  354 */     if ((this.currentResultSet != null) && (!this.currentResultSet.closed))
/*      */     {
/*  356 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 28);
/*  357 */       localSQLException.fillInStackTrace();
/*  358 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  365 */     int i = paramInt1 - 1;
/*      */     int[] arrayOfInt;
/*  367 */     if ((this.definedColumnType == null) || (this.definedColumnType.length <= i))
/*      */     {
/*  369 */       if (this.definedColumnType == null)
/*      */       {
/*  371 */         this.definedColumnType = new int[(i + 1) * 4];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  383 */         arrayOfInt = new int[(i + 1) * 4];
/*      */         
/*  385 */         System.arraycopy(this.definedColumnType, 0, arrayOfInt, 0, this.definedColumnType.length);
/*      */         
/*      */ 
/*  388 */         this.definedColumnType = arrayOfInt;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  394 */     this.definedColumnType[i] = paramInt2;
/*      */     
/*  396 */     if ((this.definedColumnSize == null) || (this.definedColumnSize.length <= i))
/*      */     {
/*  398 */       if (this.definedColumnSize == null) {
/*  399 */         this.definedColumnSize = new int[(i + 1) * 4];
/*      */       }
/*      */       else {
/*  402 */         arrayOfInt = new int[(i + 1) * 4];
/*      */         
/*  404 */         System.arraycopy(this.definedColumnSize, 0, arrayOfInt, 0, this.definedColumnSize.length);
/*      */         
/*      */ 
/*  407 */         this.definedColumnSize = arrayOfInt;
/*      */       }
/*      */     }
/*      */     
/*  411 */     this.definedColumnSize[i] = paramInt3;
/*      */     
/*  413 */     if ((this.definedColumnFormOfUse == null) || (this.definedColumnFormOfUse.length <= i))
/*      */     {
/*  415 */       if (this.definedColumnFormOfUse == null) {
/*  416 */         this.definedColumnFormOfUse = new int[(i + 1) * 4];
/*      */       }
/*      */       else {
/*  419 */         arrayOfInt = new int[(i + 1) * 4];
/*      */         
/*  421 */         System.arraycopy(this.definedColumnFormOfUse, 0, arrayOfInt, 0, this.definedColumnFormOfUse.length);
/*      */         
/*      */ 
/*  424 */         this.definedColumnFormOfUse = arrayOfInt;
/*      */       }
/*      */     }
/*      */     
/*  428 */     this.definedColumnFormOfUse[i] = paramShort;
/*      */     
/*  430 */     if ((this.accessors != null) && (i < this.accessors.length) && (this.accessors[i] != null))
/*      */     {
/*  432 */       this.accessors[i].definedColumnSize = paramInt3;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  437 */       if (((this.accessors[i].internalType == 96) || (this.accessors[i].internalType == 1)) && ((paramInt2 == 1) || (paramInt2 == 12)))
/*      */       {
/*      */ 
/*      */ 
/*  441 */         if (paramInt3 <= this.accessors[i].oacmxl)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  447 */           this.needToPrepareDefineBuffer = true;
/*  448 */           this.columnsDefinedByUser = true;
/*      */           
/*  450 */           this.accessors[i].initForDataAccess(paramInt2, paramInt3, null);
/*  451 */           this.accessors[i].calculateSizeTmpByteArray();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void clearDefines()
/*      */     throws SQLException
/*      */   {
/*  460 */     synchronized (this.connection)
/*      */     {
/*  462 */       super.clearDefines();
/*  463 */       this.definedColumnType = null;
/*  464 */       this.definedColumnSize = null;
/*  465 */       this.definedColumnFormOfUse = null;
/*  466 */       this.t4Connection.all8.definesAccessors = null;
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
/*      */   void saveDefineBuffersIfRequired(char[] paramArrayOfChar, byte[] paramArrayOfByte, short[] paramArrayOfShort, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  484 */     int i = this.rowPrefetchInLastFetch < this.rowPrefetch ? 1 : 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  513 */     if (paramBoolean)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  522 */       paramArrayOfShort = new short[this.defineIndicators.length];
/*  523 */       j = this.accessors[0].lengthIndexLastRow;
/*  524 */       int k = this.accessors[0].indicatorIndexLastRow;
/*      */       
/*  526 */       int i1 = i != 0 ? this.accessors.length : 1;
/*  527 */       for (; i != 0 ? i1 >= 1 : i1 <= this.accessors.length; 
/*  528 */           i1 += (i != 0 ? -1 : 1))
/*      */       {
/*  530 */         int m = j + this.rowPrefetchInLastFetch * i1 - 1;
/*  531 */         int n = k + this.rowPrefetchInLastFetch * i1 - 1;
/*  532 */         paramArrayOfShort[n] = this.defineIndicators[n];
/*  533 */         paramArrayOfShort[m] = this.defineIndicators[m];
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  540 */     int j = i != 0 ? this.accessors.length - 1 : 0;
/*  541 */     for (; i != 0 ? j > -1 : j < this.accessors.length; 
/*  542 */         j += (i != 0 ? -1 : 1))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  549 */       this.accessors[j].saveDataFromOldDefineBuffers(paramArrayOfByte, paramArrayOfChar, paramArrayOfShort, this.rowPrefetchInLastFetch != -1 ? this.rowPrefetchInLastFetch : this.rowPrefetch, this.rowPrefetch);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  556 */     super.saveDefineBuffersIfRequired(paramArrayOfChar, paramArrayOfByte, paramArrayOfShort, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void doSetSnapshotSCN(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  566 */     this.inScn = paramLong;
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
/*      */   Accessor allocateAccessor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, short paramShort, String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  586 */     Object localObject = null;
/*      */     SQLException localSQLException;
/*  588 */     switch (paramInt1)
/*      */     {
/*      */ 
/*      */     case 96: 
/*  592 */       localObject = new T4CCharAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  595 */       break;
/*      */     
/*      */     case 8: 
/*  598 */       if (!paramBoolean)
/*      */       {
/*  600 */         localObject = new T4CLongAccessor(this, paramInt3, paramInt4, paramShort, paramInt2, this.t4Connection.mare);
/*      */       }
/*      */       
/*  603 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 1: 
/*  608 */       localObject = new T4CVarcharAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  611 */       break;
/*      */     
/*      */     case 2: 
/*  614 */       localObject = new T4CNumberAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  617 */       break;
/*      */     
/*      */     case 6: 
/*  620 */       localObject = new T4CVarnumAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  623 */       break;
/*      */     
/*      */     case 24: 
/*  626 */       if (!paramBoolean)
/*      */       {
/*  628 */         localObject = new T4CLongRawAccessor(this, paramInt3, paramInt4, paramShort, paramInt2, this.t4Connection.mare);
/*      */       }
/*      */       
/*  631 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 23: 
/*  636 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/*  638 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/*  639 */         localSQLException.fillInStackTrace();
/*  640 */         throw localSQLException;
/*      */       }
/*      */       
/*  643 */       if (paramBoolean) {
/*  644 */         localObject = new T4COutRawAccessor(this, paramInt4, paramShort, paramInt2, this.t4Connection.mare);
/*      */       }
/*      */       else {
/*  647 */         localObject = new T4CRawAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       }
/*      */       
/*  650 */       break;
/*      */     
/*      */     case 100: 
/*  653 */       localObject = new T4CBinaryFloatAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  656 */       break;
/*      */     
/*      */     case 101: 
/*  659 */       localObject = new T4CBinaryDoubleAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  662 */       break;
/*      */     
/*      */     case 104: 
/*  665 */       if (this.sqlKind == OracleStatement.SqlKind.CALL_BLOCK)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  671 */         localObject = new T4CVarcharAccessor(this, 18, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */         
/*      */ 
/*      */ 
/*  675 */         ((Accessor)localObject).definedColumnType = -8;
/*      */       }
/*      */       else {
/*  678 */         localObject = new T4CRowidAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       }
/*      */       
/*      */ 
/*  682 */       break;
/*      */     
/*      */     case 102: 
/*  685 */       localObject = new T4CResultSetAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  688 */       break;
/*      */     
/*      */     case 12: 
/*  691 */       localObject = new T4CDateAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  694 */       break;
/*      */     
/*      */     case 113: 
/*  697 */       localObject = new T4CBlobAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  700 */       break;
/*      */     
/*      */     case 112: 
/*  703 */       localObject = new T4CClobAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  706 */       break;
/*      */     
/*      */     case 114: 
/*  709 */       localObject = new T4CBfileAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  712 */       break;
/*      */     
/*      */     case 109: 
/*  715 */       localObject = new T4CNamedTypeAccessor(this, paramString, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  718 */       ((Accessor)localObject).initMetadata();
/*      */       
/*  720 */       break;
/*      */     
/*      */     case 111: 
/*  723 */       localObject = new T4CRefTypeAccessor(this, paramString, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  726 */       ((Accessor)localObject).initMetadata();
/*      */       
/*  728 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 180: 
/*  733 */       localObject = new T4CTimestampAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  736 */       break;
/*      */     
/*      */     case 181: 
/*  739 */       localObject = new T4CTimestamptzAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  742 */       break;
/*      */     
/*      */     case 231: 
/*  745 */       localObject = new T4CTimestampltzAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  748 */       break;
/*      */     
/*      */     case 182: 
/*  751 */       localObject = new T4CIntervalymAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  754 */       break;
/*      */     
/*      */     case 183: 
/*  757 */       localObject = new T4CIntervaldsAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  760 */       break;
/*      */     
/*      */ 
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
/*  773 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 89);
/*  774 */       localSQLException.fillInStackTrace();
/*  775 */       throw localSQLException;
/*      */     }
/*      */     
/*      */     
/*  779 */     return (Accessor)localObject;
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
/*      */   void doDescribe(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  806 */     if (!this.isOpen)
/*      */     {
/*      */ 
/*  809 */       this.connection.open(this);
/*  810 */       this.isOpen = true;
/*      */     }
/*      */     
/*      */ 
/*      */     try
/*      */     {
/*  816 */       this.t4Connection.needLine();
/*  817 */       this.t4Connection.sendPiggyBackedMessages();
/*  818 */       this.t4Connection.describe.doODNY(this, 0, this.accessors, this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals));
/*  819 */       this.accessors = this.t4Connection.describe.getAccessors();
/*      */       
/*  821 */       this.numberOfDefinePositions = this.t4Connection.describe.numuds;
/*      */       
/*  823 */       for (int i = 0; i < this.numberOfDefinePositions; i++) {
/*  824 */         this.accessors[i].initMetadata();
/*      */       }
/*      */     }
/*      */     catch (IOException localIOException) {
/*  828 */       ((T4CConnection)this.connection).handleIOException(localIOException);
/*      */       
/*      */ 
/*  831 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  832 */       localSQLException.fillInStackTrace();
/*  833 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  837 */     this.describedWithNames = true;
/*  838 */     this.described = true;
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
/*      */   void executeForDescribe()
/*      */     throws SQLException
/*      */   {
/*  873 */     this.t4Connection.assertLoggedOn("oracle.jdbc.driver.T4CPreparedStatement.execute_for_describe");
/*      */     try
/*      */     {
/*  876 */       if (this.t4Connection.useFetchSizeWithLongColumn)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  882 */         doOall8(true, true, true, true, false);
/*      */       }
/*      */       else
/*      */       {
/*  886 */         doOall8(true, true, false, true, this.definedColumnType != null);
/*      */       }
/*      */       
/*      */     }
/*      */     catch (SQLException localSQLException1)
/*      */     {
/*  892 */       throw localSQLException1;
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*  896 */       ((T4CConnection)this.connection).handleIOException(localIOException);
/*      */       
/*  898 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  899 */       localSQLException2.fillInStackTrace();
/*  900 */       throw localSQLException2;
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*  905 */       this.rowsProcessed = this.t4Connection.all8.rowsProcessed;
/*  906 */       this.validRows = this.t4Connection.all8.getNumRows();
/*      */     }
/*      */     
/*  909 */     this.needToParse = false;
/*      */     
/*      */ 
/*  912 */     if (this.connection.calculateChecksum) {
/*  913 */       if (this.validRows > 0) {
/*  914 */         calculateCheckSum();
/*  915 */       } else if (this.rowsProcessed > 0) {
/*  916 */         long l = CRC64.updateChecksum(this.checkSum, this.rowsProcessed);
/*      */         
/*  918 */         this.checkSum = l;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  928 */     if (this.definedColumnType == null) {
/*  929 */       this.implicitDefineForLobPrefetchDone = false;
/*      */     }
/*  931 */     this.aFetchWasDoneDuringDescribe = false;
/*  932 */     if (this.t4Connection.all8.aFetchWasDone)
/*      */     {
/*  934 */       this.aFetchWasDoneDuringDescribe = true;
/*  935 */       this.rowPrefetchInLastFetch = this.rowPrefetch;
/*      */     }
/*      */     
/*      */ 
/*  939 */     for (int i = 0; i < this.numberOfDefinePositions; i++) {
/*  940 */       this.accessors[i].initMetadata();
/*      */     }
/*  942 */     this.needToPrepareDefineBuffer = false;
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
/*      */   void executeForRows(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/*      */       try
/*      */       {
/*  984 */         boolean bool = false;
/*  985 */         if (this.columnsDefinedByUser) {
/*  986 */           this.needToPrepareDefineBuffer = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/* 1006 */         else if ((this.t4Connection.useLobPrefetch) && (this.accessors != null) && (this.defaultLobPrefetchSize != -1) && (!this.implicitDefineForLobPrefetchDone) && (!this.aFetchWasDoneDuringDescribe) && (this.definedColumnType == null))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1014 */           int i = 0;
/* 1015 */           int[] arrayOfInt1 = new int[this.accessors.length];
/* 1016 */           int[] arrayOfInt2 = new int[this.accessors.length];
/*      */           
/* 1018 */           for (int j = 0; j < this.accessors.length; j++)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/* 1023 */             arrayOfInt1[j] = getJDBCType(this.accessors[j].internalType);
/* 1024 */             if ((this.accessors[j].internalType == 113) || (this.accessors[j].internalType == 112) || (this.accessors[j].internalType == 114))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1030 */               i = 1;
/* 1031 */               this.accessors[j].lobPrefetchSizeForThisColumn = this.defaultLobPrefetchSize;
/* 1032 */               arrayOfInt2[j] = this.defaultLobPrefetchSize;
/*      */             }
/*      */           }
/*      */           
/* 1036 */           if (i != 0)
/*      */           {
/* 1038 */             this.definedColumnType = arrayOfInt1;
/* 1039 */             this.definedColumnSize = arrayOfInt2;
/* 1040 */             bool = true;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1046 */         doOall8(this.needToParse, !paramBoolean, true, false, bool);
/*      */         
/* 1048 */         this.needToParse = false;
/* 1049 */         if (bool) {
/* 1050 */           this.implicitDefineForLobPrefetchDone = true;
/*      */         }
/*      */       }
/*      */       finally {
/* 1054 */         this.validRows = this.t4Connection.all8.getNumRows();
/*      */       }
/*      */     }
/*      */     catch (SQLException localSQLException1)
/*      */     {
/* 1059 */       throw localSQLException1;
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 1063 */       ((T4CConnection)this.connection).handleIOException(localIOException);
/* 1064 */       calculateCheckSum();
/*      */       
/* 1066 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1067 */       localSQLException2.fillInStackTrace();
/* 1068 */       throw localSQLException2;
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
/*      */   void fetch()
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
/*      */ 
/*      */ 
/* 1095 */     if (this.streamList != null)
/*      */     {
/*      */ 
/*      */ 
/* 1099 */       while (this.nextStream != null)
/*      */       {
/*      */         try
/*      */         {
/* 1103 */           this.nextStream.close();
/*      */         }
/*      */         catch (IOException localIOException1)
/*      */         {
/* 1107 */           ((T4CConnection)this.connection).handleIOException(localIOException1);
/*      */           
/* 1109 */           localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException1);
/* 1110 */           localSQLException.fillInStackTrace();
/* 1111 */           throw localSQLException;
/*      */         }
/*      */         
/*      */ 
/* 1115 */         this.nextStream = this.nextStream.nextStream;
/*      */       }
/*      */     }
/*      */     
/*      */     try
/*      */     {
/* 1121 */       doOall8(false, false, true, false, false);
/*      */       
/* 1123 */       this.validRows = this.t4Connection.all8.getNumRows();
/*      */     }
/*      */     catch (IOException localIOException2)
/*      */     {
/* 1127 */       ((T4CConnection)this.connection).handleIOException(localIOException2);
/*      */       
/* 1129 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException2);
/* 1130 */       localSQLException.fillInStackTrace();
/* 1131 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1137 */     calculateCheckSum();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void continueReadRow(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 1152 */       if (!this.connection.useFetchSizeWithLongColumn)
/*      */       {
/* 1154 */         T4C8Oall localT4C8Oall = this.t4Connection.all8;
/*      */         
/* 1156 */         localT4C8Oall.continueReadRow(paramInt, this);
/*      */       }
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 1161 */       ((T4CConnection)this.connection).handleIOException(localIOException);
/*      */       
/* 1163 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1164 */       localSQLException2.fillInStackTrace();
/* 1165 */       throw localSQLException2;
/*      */ 
/*      */     }
/*      */     catch (SQLException localSQLException1)
/*      */     {
/* 1170 */       if (localSQLException1.getErrorCode() == DatabaseError.getVendorCode(110))
/*      */       {
/*      */ 
/* 1173 */         this.sqlWarning = DatabaseError.addSqlWarning(this.sqlWarning, 110);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1178 */         throw localSQLException1;
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
/*      */   void doClose()
/*      */     throws SQLException
/*      */   {
/* 1202 */     this.t4Connection.assertLoggedOn("oracle.jdbc.driver.T4CPreparedStatement.do_close");
/*      */     
/* 1204 */     if (this.cursorId != 0) {
/* 1205 */       this.t4Connection.closeCursor(this.cursorId);
/*      */     }
/*      */     
/* 1208 */     this.tmpByteArray = null;
/* 1209 */     this.tmpBindsByteArray = null;
/* 1210 */     this.definedColumnType = null;
/* 1211 */     this.definedColumnSize = null;
/* 1212 */     this.definedColumnFormOfUse = null;
/* 1213 */     this.oacdefSent = null;
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
/*      */   void closeQuery()
/*      */     throws SQLException
/*      */   {
/* 1233 */     this.t4Connection.assertLoggedOn("oracle.jdbc.driver.T4CPreparedStatement.closeQuery");
/*      */     
/* 1235 */     if (this.streamList != null)
/*      */     {
/* 1237 */       while (this.nextStream != null) {
/*      */         try {
/* 1239 */           this.nextStream.close();
/*      */         }
/*      */         catch (IOException localIOException) {
/* 1242 */           ((T4CConnection)this.connection).handleIOException(localIOException);
/*      */           
/* 1244 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1245 */           localSQLException.fillInStackTrace();
/* 1246 */           throw localSQLException;
/*      */         }
/*      */         
/*      */ 
/* 1250 */         this.nextStream = this.nextStream.nextStream;
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
/*      */   Binder getRowidNullBinder(int paramInt)
/*      */   {
/* 1263 */     if (this.sqlKind == OracleStatement.SqlKind.CALL_BLOCK)
/*      */     {
/*      */ 
/* 1266 */       this.currentRowCharLens[paramInt] = 1;
/* 1267 */       return this.theVarcharNullBinder;
/*      */     }
/*      */     
/* 1270 */     return this.theRowidNullBinder;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void doLocalInitialization()
/*      */   {
/* 1279 */     super.doLocalInitialization();
/*      */     
/* 1281 */     this.t4Connection.all8.bindChars = this.bindChars;
/* 1282 */     this.t4Connection.all8.bindBytes = this.bindBytes;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 1288 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CPreparedStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */