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
/*      */ class T4CCallableStatement
/*      */   extends OracleCallableStatement
/*      */ {
/*      */   T4CCallableStatement(PhysicalConnection paramPhysicalConnection, String paramString, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*   29 */     super(paramPhysicalConnection, paramString, paramPhysicalConnection.defaultExecuteBatch, paramPhysicalConnection.defaultRowPrefetch, paramInt1, paramInt2);
/*      */     
/*   31 */     this.t4Connection = ((T4CConnection)paramPhysicalConnection);
/*   32 */     this.nbPostPonedColumns = new int[1];
/*   33 */     this.nbPostPonedColumns[0] = 0;
/*   34 */     this.indexOfPostPonedColumn = new int[1][3];
/*      */     
/*   36 */     this.theRowidBinder = theStaticT4CRowidBinder;
/*   37 */     this.theRowidNullBinder = theStaticT4CRowidNullBinder;
/*   38 */     this.theURowidBinder = theStaticT4CURowidBinder;
/*   39 */     this.theURowidNullBinder = theStaticT4CURowidNullBinder;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   45 */   static final byte[] EMPTY_BYTE = new byte[0];
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
/*   65 */     if ((paramBoolean1) || (paramBoolean4) || (!paramBoolean2)) {
/*   66 */       this.oacdefSent = null;
/*      */     }
/*   68 */     this.t4Connection.assertLoggedOn("oracle.jdbc.driver.T4CCallableStatement.doOall8");
/*      */     
/*   70 */     if (this.sqlKind == OracleStatement.SqlKind.UNINITIALIZED)
/*      */     {
/*      */ 
/*      */ 
/*   74 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 439, "sqlKind = " + this.sqlKind);
/*   75 */       localSQLException1.fillInStackTrace();
/*   76 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*   80 */     if (paramBoolean3) {
/*   81 */       this.rowPrefetchInLastFetch = this.rowPrefetch;
/*      */     }
/*   83 */     int i = this.numberOfDefinePositions;
/*      */     
/*   85 */     if (this.sqlKind.isDML()) {
/*   86 */       i = 0;
/*      */     }
/*      */     int j;
/*   89 */     if (this.accessors != null)
/*   90 */       for (j = 0; j < this.accessors.length; j++)
/*   91 */         if (this.accessors[j] != null)
/*   92 */           this.accessors[j].lastRowProcessed = 0;
/*   93 */     if (this.outBindAccessors != null)
/*   94 */       for (j = 0; j < this.outBindAccessors.length; j++)
/*   95 */         if (this.outBindAccessors[j] != null)
/*   96 */           this.outBindAccessors[j].lastRowProcessed = 0;
/*   97 */     if (this.returnParamAccessors != null) {
/*   98 */       for (j = 0; j < this.returnParamAccessors.length; j++) {
/*   99 */         if (this.returnParamAccessors[j] != null) {
/*  100 */           this.returnParamAccessors[j].lastRowProcessed = 0;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     int i1;
/*      */     int i2;
/*  107 */     if (this.bindIndicators != null)
/*      */     {
/*  109 */       j = ((this.bindIndicators[(this.bindIndicatorSubRange + 3)] & 0xFFFF) << 16) + (this.bindIndicators[(this.bindIndicatorSubRange + 4)] & 0xFFFF);
/*      */       
/*      */ 
/*  112 */       int k = 0;
/*      */       
/*  114 */       if (this.ibtBindChars != null) {
/*  115 */         k = this.ibtBindChars.length * this.connection.conversion.cMaxCharSize;
/*      */       }
/*  117 */       for (int m = 0; m < this.numberOfBindPositions; m++)
/*      */       {
/*  119 */         int n = this.bindIndicatorSubRange + 5 + 10 * m;
/*      */         
/*      */ 
/*      */ 
/*  123 */         i1 = this.bindIndicators[(n + 2)] & 0xFFFF;
/*      */         
/*      */ 
/*      */ 
/*  127 */         if (i1 != 0)
/*      */         {
/*      */ 
/*  130 */           i2 = this.bindIndicators[(n + 9)] & 0xFFFF;
/*      */           
/*      */ 
/*      */ 
/*  134 */           if (i2 == 2)
/*      */           {
/*  136 */             k = Math.max(i1 * this.connection.conversion.maxNCharSize, k);
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*  141 */             k = Math.max(i1 * this.connection.conversion.cMaxCharSize, k);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  147 */       if (this.tmpBindsByteArray == null)
/*      */       {
/*  149 */         this.tmpBindsByteArray = new byte[k];
/*      */       }
/*  151 */       else if (this.tmpBindsByteArray.length < k)
/*      */       {
/*  153 */         this.tmpBindsByteArray = null;
/*  154 */         this.tmpBindsByteArray = new byte[k];
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
/*  166 */       this.tmpBindsByteArray = null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  171 */     int[] arrayOfInt1 = this.definedColumnType;
/*  172 */     int[] arrayOfInt2 = this.definedColumnSize;
/*  173 */     int[] arrayOfInt3 = this.definedColumnFormOfUse;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  179 */     if ((paramBoolean5) && (paramBoolean4) && (this.sqlObject.includeRowid))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  184 */       arrayOfInt1 = new int[this.definedColumnType.length + 1];
/*  185 */       System.arraycopy(this.definedColumnType, 0, arrayOfInt1, 1, this.definedColumnType.length);
/*  186 */       arrayOfInt1[0] = -8;
/*  187 */       arrayOfInt2 = new int[this.definedColumnSize.length + 1];
/*  188 */       System.arraycopy(this.definedColumnSize, 0, arrayOfInt2, 1, this.definedColumnSize.length);
/*  189 */       arrayOfInt3 = new int[this.definedColumnFormOfUse.length + 1];
/*  190 */       System.arraycopy(this.definedColumnFormOfUse, 0, arrayOfInt3, 1, this.definedColumnFormOfUse.length);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  196 */     allocateTmpByteArray();
/*      */     
/*  198 */     T4C8Oall localT4C8Oall = this.t4Connection.all8;
/*      */     
/*  200 */     this.t4Connection.sendPiggyBackedMessages();
/*      */     
/*      */ 
/*      */     try
/*      */     {
/*  205 */       localT4C8Oall.doOALL(paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5, this.sqlKind, this.cursorId, this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals), this.rowPrefetch, this.outBindAccessors, this.numberOfBindPositions, this.accessors, i, this.bindBytes, this.bindChars, this.bindIndicators, this.bindIndicatorSubRange, this.connection.conversion, this.tmpBindsByteArray, this.parameterStream, this.parameterDatum, this.parameterOtype, this, this.ibtBindBytes, this.ibtBindChars, this.ibtBindIndicators, this.oacdefSent, arrayOfInt1, arrayOfInt2, arrayOfInt3, this.registration);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  217 */       i1 = localT4C8Oall.getCursorId();
/*  218 */       if (i1 != 0) {
/*  219 */         this.cursorId = i1;
/*      */       }
/*  221 */       this.oacdefSent = localT4C8Oall.oacdefBindsSent;
/*      */     }
/*      */     catch (SQLException localSQLException2)
/*      */     {
/*  225 */       i2 = localT4C8Oall.getCursorId();
/*  226 */       if (i2 != 0) {
/*  227 */         this.cursorId = i2;
/*      */       }
/*  229 */       if (localSQLException2.getErrorCode() == DatabaseError.getVendorCode(110))
/*      */       {
/*      */ 
/*  232 */         this.sqlWarning = DatabaseError.addSqlWarning(this.sqlWarning, 110);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  237 */         throw localSQLException2;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void allocateTmpByteArray()
/*      */   {
/*  247 */     if (this.tmpByteArray == null)
/*      */     {
/*      */ 
/*  250 */       this.tmpByteArray = new byte[this.sizeTmpByteArray];
/*      */     }
/*  252 */     else if (this.sizeTmpByteArray > this.tmpByteArray.length)
/*      */     {
/*      */ 
/*      */ 
/*  256 */       this.tmpByteArray = new byte[this.sizeTmpByteArray];
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
/*  268 */     super.releaseBuffers();
/*  269 */     this.tmpByteArray = null;
/*  270 */     this.tmpBindsByteArray = null;
/*      */     
/*  272 */     this.t4Connection.all8.bindChars = null;
/*  273 */     this.t4Connection.all8.bindBytes = null;
/*  274 */     this.t4Connection.all8.tmpBindsByteArray = null;
/*      */   }
/*      */   
/*      */ 
/*      */   void allocateRowidAccessor()
/*      */     throws SQLException
/*      */   {
/*  281 */     this.accessors[0] = new T4CRowidAccessor(this, 128, 1, -8, false, this.t4Connection.mare);
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
/*  294 */     this.needToParse = true;
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
/*  305 */     if (this.connection.disableDefinecolumntype)
/*      */     {
/*      */ 
/*  308 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  313 */     if ((paramInt2 == -15) || (paramInt2 == -9) || (paramInt2 == -16))
/*      */     {
/*  315 */       paramShort = 2;
/*      */     }
/*      */     
/*      */ 
/*      */     SQLException localSQLException;
/*      */     
/*  321 */     if (paramInt1 < 1)
/*      */     {
/*  323 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  324 */       localSQLException.fillInStackTrace();
/*  325 */       throw localSQLException;
/*      */     }
/*  327 */     if (paramBoolean)
/*      */     {
/*      */ 
/*      */ 
/*  331 */       if ((paramInt2 == 1) || (paramInt2 == 12) || (paramInt2 == -15) || (paramInt2 == -9))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  337 */         this.sqlWarning = DatabaseError.addSqlWarning(this.sqlWarning, 108);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*  343 */     else if (paramInt3 < 0)
/*      */     {
/*  345 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 53);
/*  346 */       localSQLException.fillInStackTrace();
/*  347 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  351 */     if ((this.currentResultSet != null) && (!this.currentResultSet.closed))
/*      */     {
/*  353 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 28);
/*  354 */       localSQLException.fillInStackTrace();
/*  355 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  362 */     int i = paramInt1 - 1;
/*      */     int[] arrayOfInt;
/*  364 */     if ((this.definedColumnType == null) || (this.definedColumnType.length <= i))
/*      */     {
/*  366 */       if (this.definedColumnType == null)
/*      */       {
/*  368 */         this.definedColumnType = new int[(i + 1) * 4];
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
/*  380 */         arrayOfInt = new int[(i + 1) * 4];
/*      */         
/*  382 */         System.arraycopy(this.definedColumnType, 0, arrayOfInt, 0, this.definedColumnType.length);
/*      */         
/*      */ 
/*  385 */         this.definedColumnType = arrayOfInt;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  391 */     this.definedColumnType[i] = paramInt2;
/*      */     
/*  393 */     if ((this.definedColumnSize == null) || (this.definedColumnSize.length <= i))
/*      */     {
/*  395 */       if (this.definedColumnSize == null) {
/*  396 */         this.definedColumnSize = new int[(i + 1) * 4];
/*      */       }
/*      */       else {
/*  399 */         arrayOfInt = new int[(i + 1) * 4];
/*      */         
/*  401 */         System.arraycopy(this.definedColumnSize, 0, arrayOfInt, 0, this.definedColumnSize.length);
/*      */         
/*      */ 
/*  404 */         this.definedColumnSize = arrayOfInt;
/*      */       }
/*      */     }
/*      */     
/*  408 */     this.definedColumnSize[i] = paramInt3;
/*      */     
/*  410 */     if ((this.definedColumnFormOfUse == null) || (this.definedColumnFormOfUse.length <= i))
/*      */     {
/*  412 */       if (this.definedColumnFormOfUse == null) {
/*  413 */         this.definedColumnFormOfUse = new int[(i + 1) * 4];
/*      */       }
/*      */       else {
/*  416 */         arrayOfInt = new int[(i + 1) * 4];
/*      */         
/*  418 */         System.arraycopy(this.definedColumnFormOfUse, 0, arrayOfInt, 0, this.definedColumnFormOfUse.length);
/*      */         
/*      */ 
/*  421 */         this.definedColumnFormOfUse = arrayOfInt;
/*      */       }
/*      */     }
/*      */     
/*  425 */     this.definedColumnFormOfUse[i] = paramShort;
/*      */     
/*  427 */     if ((this.accessors != null) && (i < this.accessors.length) && (this.accessors[i] != null))
/*      */     {
/*  429 */       this.accessors[i].definedColumnSize = paramInt3;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  434 */       if (((this.accessors[i].internalType == 96) || (this.accessors[i].internalType == 1)) && ((paramInt2 == 1) || (paramInt2 == 12)))
/*      */       {
/*      */ 
/*      */ 
/*  438 */         if (paramInt3 <= this.accessors[i].oacmxl)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  444 */           this.needToPrepareDefineBuffer = true;
/*  445 */           this.columnsDefinedByUser = true;
/*      */           
/*  447 */           this.accessors[i].initForDataAccess(paramInt2, paramInt3, null);
/*  448 */           this.accessors[i].calculateSizeTmpByteArray();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void clearDefines()
/*      */     throws SQLException
/*      */   {
/*  457 */     synchronized (this.connection)
/*      */     {
/*  459 */       super.clearDefines();
/*  460 */       this.definedColumnType = null;
/*  461 */       this.definedColumnSize = null;
/*  462 */       this.definedColumnFormOfUse = null;
/*  463 */       this.t4Connection.all8.definesAccessors = null;
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
/*  481 */     int i = this.rowPrefetchInLastFetch < this.rowPrefetch ? 1 : 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  510 */     if (paramBoolean)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  519 */       paramArrayOfShort = new short[this.defineIndicators.length];
/*  520 */       j = this.accessors[0].lengthIndexLastRow;
/*  521 */       int k = this.accessors[0].indicatorIndexLastRow;
/*      */       
/*  523 */       int i1 = i != 0 ? this.accessors.length : 1;
/*  524 */       for (; i != 0 ? i1 >= 1 : i1 <= this.accessors.length; 
/*  525 */           i1 += (i != 0 ? -1 : 1))
/*      */       {
/*  527 */         int m = j + this.rowPrefetchInLastFetch * i1 - 1;
/*  528 */         int n = k + this.rowPrefetchInLastFetch * i1 - 1;
/*  529 */         paramArrayOfShort[n] = this.defineIndicators[n];
/*  530 */         paramArrayOfShort[m] = this.defineIndicators[m];
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  537 */     int j = i != 0 ? this.accessors.length - 1 : 0;
/*  538 */     for (; i != 0 ? j > -1 : j < this.accessors.length; 
/*  539 */         j += (i != 0 ? -1 : 1))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  546 */       this.accessors[j].saveDataFromOldDefineBuffers(paramArrayOfByte, paramArrayOfChar, paramArrayOfShort, this.rowPrefetchInLastFetch != -1 ? this.rowPrefetchInLastFetch : this.rowPrefetch, this.rowPrefetch);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  553 */     super.saveDefineBuffersIfRequired(paramArrayOfChar, paramArrayOfByte, paramArrayOfShort, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void doSetSnapshotSCN(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  563 */     this.inScn = paramLong;
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
/*  583 */     Object localObject = null;
/*      */     SQLException localSQLException;
/*  585 */     switch (paramInt1)
/*      */     {
/*      */ 
/*      */     case 96: 
/*  589 */       localObject = new T4CCharAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  592 */       break;
/*      */     
/*      */     case 8: 
/*  595 */       if (!paramBoolean)
/*      */       {
/*  597 */         localObject = new T4CLongAccessor(this, paramInt3, paramInt4, paramShort, paramInt2, this.t4Connection.mare);
/*      */       }
/*      */       
/*  600 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 1: 
/*  605 */       localObject = new T4CVarcharAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  608 */       break;
/*      */     
/*      */     case 2: 
/*  611 */       localObject = new T4CNumberAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  614 */       break;
/*      */     
/*      */     case 6: 
/*  617 */       localObject = new T4CVarnumAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  620 */       break;
/*      */     
/*      */     case 24: 
/*  623 */       if (!paramBoolean)
/*      */       {
/*  625 */         localObject = new T4CLongRawAccessor(this, paramInt3, paramInt4, paramShort, paramInt2, this.t4Connection.mare);
/*      */       }
/*      */       
/*  628 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 23: 
/*  633 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/*  635 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/*  636 */         localSQLException.fillInStackTrace();
/*  637 */         throw localSQLException;
/*      */       }
/*      */       
/*  640 */       if (paramBoolean) {
/*  641 */         localObject = new T4COutRawAccessor(this, paramInt4, paramShort, paramInt2, this.t4Connection.mare);
/*      */       }
/*      */       else {
/*  644 */         localObject = new T4CRawAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       }
/*      */       
/*  647 */       break;
/*      */     
/*      */     case 100: 
/*  650 */       localObject = new T4CBinaryFloatAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  653 */       break;
/*      */     
/*      */     case 101: 
/*  656 */       localObject = new T4CBinaryDoubleAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  659 */       break;
/*      */     
/*      */     case 104: 
/*  662 */       if (this.sqlKind == OracleStatement.SqlKind.CALL_BLOCK)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  668 */         localObject = new T4CVarcharAccessor(this, 18, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */         
/*      */ 
/*      */ 
/*  672 */         ((Accessor)localObject).definedColumnType = -8;
/*      */       }
/*      */       else {
/*  675 */         localObject = new T4CRowidAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       }
/*      */       
/*      */ 
/*  679 */       break;
/*      */     
/*      */     case 102: 
/*  682 */       localObject = new T4CResultSetAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  685 */       break;
/*      */     
/*      */     case 12: 
/*  688 */       localObject = new T4CDateAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  691 */       break;
/*      */     
/*      */     case 113: 
/*  694 */       localObject = new T4CBlobAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  697 */       break;
/*      */     
/*      */     case 112: 
/*  700 */       localObject = new T4CClobAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  703 */       break;
/*      */     
/*      */     case 114: 
/*  706 */       localObject = new T4CBfileAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  709 */       break;
/*      */     
/*      */     case 109: 
/*  712 */       localObject = new T4CNamedTypeAccessor(this, paramString, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  715 */       ((Accessor)localObject).initMetadata();
/*      */       
/*  717 */       break;
/*      */     
/*      */     case 111: 
/*  720 */       localObject = new T4CRefTypeAccessor(this, paramString, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  723 */       ((Accessor)localObject).initMetadata();
/*      */       
/*  725 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 180: 
/*  730 */       localObject = new T4CTimestampAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  733 */       break;
/*      */     
/*      */     case 181: 
/*  736 */       localObject = new T4CTimestamptzAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  739 */       break;
/*      */     
/*      */     case 231: 
/*  742 */       localObject = new T4CTimestampltzAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  745 */       break;
/*      */     
/*      */     case 182: 
/*  748 */       localObject = new T4CIntervalymAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  751 */       break;
/*      */     
/*      */     case 183: 
/*  754 */       localObject = new T4CIntervaldsAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  757 */       break;
/*      */     
/*      */ 
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
/*  770 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 89);
/*  771 */       localSQLException.fillInStackTrace();
/*  772 */       throw localSQLException;
/*      */     }
/*      */     
/*      */     
/*  776 */     return (Accessor)localObject;
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
/*  803 */     if (!this.isOpen)
/*      */     {
/*      */ 
/*  806 */       this.connection.open(this);
/*  807 */       this.isOpen = true;
/*      */     }
/*      */     
/*      */ 
/*      */     try
/*      */     {
/*  813 */       this.t4Connection.needLine();
/*  814 */       this.t4Connection.sendPiggyBackedMessages();
/*  815 */       this.t4Connection.describe.doODNY(this, 0, this.accessors, this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals));
/*  816 */       this.accessors = this.t4Connection.describe.getAccessors();
/*      */       
/*  818 */       this.numberOfDefinePositions = this.t4Connection.describe.numuds;
/*      */       
/*  820 */       for (int i = 0; i < this.numberOfDefinePositions; i++) {
/*  821 */         this.accessors[i].initMetadata();
/*      */       }
/*      */     }
/*      */     catch (IOException localIOException) {
/*  825 */       ((T4CConnection)this.connection).handleIOException(localIOException);
/*      */       
/*      */ 
/*  828 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  829 */       localSQLException.fillInStackTrace();
/*  830 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  834 */     this.describedWithNames = true;
/*  835 */     this.described = true;
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
/*  870 */     this.t4Connection.assertLoggedOn("oracle.jdbc.driver.T4CCallableStatement.execute_for_describe");
/*      */     try
/*      */     {
/*  873 */       if (this.t4Connection.useFetchSizeWithLongColumn)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  879 */         doOall8(true, true, true, true, false);
/*      */       }
/*      */       else
/*      */       {
/*  883 */         doOall8(true, true, false, true, this.definedColumnType != null);
/*      */       }
/*      */       
/*      */     }
/*      */     catch (SQLException localSQLException1)
/*      */     {
/*  889 */       throw localSQLException1;
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*  893 */       ((T4CConnection)this.connection).handleIOException(localIOException);
/*      */       
/*  895 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  896 */       localSQLException2.fillInStackTrace();
/*  897 */       throw localSQLException2;
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*  902 */       this.rowsProcessed = this.t4Connection.all8.rowsProcessed;
/*  903 */       this.validRows = this.t4Connection.all8.getNumRows();
/*      */     }
/*      */     
/*  906 */     this.needToParse = false;
/*      */     
/*      */ 
/*  909 */     if (this.connection.calculateChecksum) {
/*  910 */       if (this.validRows > 0) {
/*  911 */         calculateCheckSum();
/*  912 */       } else if (this.rowsProcessed > 0) {
/*  913 */         long l = CRC64.updateChecksum(this.checkSum, this.rowsProcessed);
/*      */         
/*  915 */         this.checkSum = l;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  925 */     if (this.definedColumnType == null) {
/*  926 */       this.implicitDefineForLobPrefetchDone = false;
/*      */     }
/*  928 */     this.aFetchWasDoneDuringDescribe = false;
/*  929 */     if (this.t4Connection.all8.aFetchWasDone)
/*      */     {
/*  931 */       this.aFetchWasDoneDuringDescribe = true;
/*  932 */       this.rowPrefetchInLastFetch = this.rowPrefetch;
/*      */     }
/*      */     
/*      */ 
/*  936 */     for (int i = 0; i < this.numberOfDefinePositions; i++) {
/*  937 */       this.accessors[i].initMetadata();
/*      */     }
/*  939 */     this.needToPrepareDefineBuffer = false;
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
/*  981 */         boolean bool = false;
/*  982 */         if (this.columnsDefinedByUser) {
/*  983 */           this.needToPrepareDefineBuffer = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1003 */         else if ((this.t4Connection.useLobPrefetch) && (this.accessors != null) && (this.defaultLobPrefetchSize != -1) && (!this.implicitDefineForLobPrefetchDone) && (!this.aFetchWasDoneDuringDescribe) && (this.definedColumnType == null))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1011 */           int i = 0;
/* 1012 */           int[] arrayOfInt1 = new int[this.accessors.length];
/* 1013 */           int[] arrayOfInt2 = new int[this.accessors.length];
/*      */           
/* 1015 */           for (int j = 0; j < this.accessors.length; j++)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/* 1020 */             arrayOfInt1[j] = getJDBCType(this.accessors[j].internalType);
/* 1021 */             if ((this.accessors[j].internalType == 113) || (this.accessors[j].internalType == 112) || (this.accessors[j].internalType == 114))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1027 */               i = 1;
/* 1028 */               this.accessors[j].lobPrefetchSizeForThisColumn = this.defaultLobPrefetchSize;
/* 1029 */               arrayOfInt2[j] = this.defaultLobPrefetchSize;
/*      */             }
/*      */           }
/*      */           
/* 1033 */           if (i != 0)
/*      */           {
/* 1035 */             this.definedColumnType = arrayOfInt1;
/* 1036 */             this.definedColumnSize = arrayOfInt2;
/* 1037 */             bool = true;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1043 */         doOall8(this.needToParse, !paramBoolean, true, false, bool);
/*      */         
/* 1045 */         this.needToParse = false;
/* 1046 */         if (bool) {
/* 1047 */           this.implicitDefineForLobPrefetchDone = true;
/*      */         }
/*      */       }
/*      */       finally {
/* 1051 */         this.validRows = this.t4Connection.all8.getNumRows();
/*      */       }
/*      */     }
/*      */     catch (SQLException localSQLException1)
/*      */     {
/* 1056 */       throw localSQLException1;
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 1060 */       ((T4CConnection)this.connection).handleIOException(localIOException);
/* 1061 */       calculateCheckSum();
/*      */       
/* 1063 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1064 */       localSQLException2.fillInStackTrace();
/* 1065 */       throw localSQLException2;
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
/* 1092 */     if (this.streamList != null)
/*      */     {
/*      */ 
/*      */ 
/* 1096 */       while (this.nextStream != null)
/*      */       {
/*      */         try
/*      */         {
/* 1100 */           this.nextStream.close();
/*      */         }
/*      */         catch (IOException localIOException1)
/*      */         {
/* 1104 */           ((T4CConnection)this.connection).handleIOException(localIOException1);
/*      */           
/* 1106 */           localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException1);
/* 1107 */           localSQLException.fillInStackTrace();
/* 1108 */           throw localSQLException;
/*      */         }
/*      */         
/*      */ 
/* 1112 */         this.nextStream = this.nextStream.nextStream;
/*      */       }
/*      */     }
/*      */     
/*      */     try
/*      */     {
/* 1118 */       doOall8(false, false, true, false, false);
/*      */       
/* 1120 */       this.validRows = this.t4Connection.all8.getNumRows();
/*      */     }
/*      */     catch (IOException localIOException2)
/*      */     {
/* 1124 */       ((T4CConnection)this.connection).handleIOException(localIOException2);
/*      */       
/* 1126 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException2);
/* 1127 */       localSQLException.fillInStackTrace();
/* 1128 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1134 */     calculateCheckSum();
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
/* 1149 */       if (!this.connection.useFetchSizeWithLongColumn)
/*      */       {
/* 1151 */         T4C8Oall localT4C8Oall = this.t4Connection.all8;
/*      */         
/* 1153 */         localT4C8Oall.continueReadRow(paramInt, this);
/*      */       }
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 1158 */       ((T4CConnection)this.connection).handleIOException(localIOException);
/*      */       
/* 1160 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1161 */       localSQLException2.fillInStackTrace();
/* 1162 */       throw localSQLException2;
/*      */ 
/*      */     }
/*      */     catch (SQLException localSQLException1)
/*      */     {
/* 1167 */       if (localSQLException1.getErrorCode() == DatabaseError.getVendorCode(110))
/*      */       {
/*      */ 
/* 1170 */         this.sqlWarning = DatabaseError.addSqlWarning(this.sqlWarning, 110);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1175 */         throw localSQLException1;
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
/* 1199 */     this.t4Connection.assertLoggedOn("oracle.jdbc.driver.T4CCallableStatement.do_close");
/*      */     
/* 1201 */     if (this.cursorId != 0) {
/* 1202 */       this.t4Connection.closeCursor(this.cursorId);
/*      */     }
/*      */     
/* 1205 */     this.tmpByteArray = null;
/* 1206 */     this.tmpBindsByteArray = null;
/* 1207 */     this.definedColumnType = null;
/* 1208 */     this.definedColumnSize = null;
/* 1209 */     this.definedColumnFormOfUse = null;
/* 1210 */     this.oacdefSent = null;
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
/* 1230 */     this.t4Connection.assertLoggedOn("oracle.jdbc.driver.T4CCallableStatement.closeQuery");
/*      */     
/* 1232 */     if (this.streamList != null)
/*      */     {
/* 1234 */       while (this.nextStream != null) {
/*      */         try {
/* 1236 */           this.nextStream.close();
/*      */         }
/*      */         catch (IOException localIOException) {
/* 1239 */           ((T4CConnection)this.connection).handleIOException(localIOException);
/*      */           
/* 1241 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1242 */           localSQLException.fillInStackTrace();
/* 1243 */           throw localSQLException;
/*      */         }
/*      */         
/*      */ 
/* 1247 */         this.nextStream = this.nextStream.nextStream;
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
/* 1260 */     if (this.sqlKind == OracleStatement.SqlKind.CALL_BLOCK)
/*      */     {
/*      */ 
/* 1263 */       this.currentRowCharLens[paramInt] = 1;
/* 1264 */       return this.theVarcharNullBinder;
/*      */     }
/*      */     
/* 1267 */     return this.theRowidNullBinder;
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
/*      */   PlsqlIndexTableAccessor allocateIndexTableAccessor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, short paramShort, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1281 */     return new T4CPlsqlIndexTableAccessor(this, paramInt1, paramInt2, paramInt3, paramInt4, paramShort, paramBoolean, this.t4Connection.mare);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1291 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CCallableStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */