/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.sql.SQLException;
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
/*      */ class T4CStatement
/*      */   extends OracleStatement
/*      */ {
/*   24 */   static final byte[][][] parameterDatum = (byte[][][])null;
/*   25 */   static final OracleTypeADT[][] parameterOtype = (OracleTypeADT[][])null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   35 */   static final byte[] EMPTY_BYTE = new byte[0];
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
/*   55 */     if ((paramBoolean1) || (paramBoolean4) || (!paramBoolean2)) {
/*   56 */       this.oacdefSent = null;
/*      */     }
/*   58 */     this.t4Connection.assertLoggedOn("oracle.jdbc.driver.T4CStatement.doOall8");
/*      */     
/*   60 */     if (this.sqlKind == OracleStatement.SqlKind.UNINITIALIZED)
/*      */     {
/*      */ 
/*      */ 
/*   64 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 439, "sqlKind = " + this.sqlKind);
/*   65 */       localSQLException1.fillInStackTrace();
/*   66 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*   70 */     if (paramBoolean3) {
/*   71 */       this.rowPrefetchInLastFetch = this.rowPrefetch;
/*      */     }
/*   73 */     int i = this.numberOfDefinePositions;
/*      */     
/*   75 */     if (this.sqlKind.isDML()) {
/*   76 */       i = 0;
/*      */     }
/*      */     int j;
/*   79 */     if (this.accessors != null)
/*   80 */       for (j = 0; j < this.accessors.length; j++)
/*   81 */         if (this.accessors[j] != null)
/*   82 */           this.accessors[j].lastRowProcessed = 0;
/*   83 */     if (this.outBindAccessors != null)
/*   84 */       for (j = 0; j < this.outBindAccessors.length; j++)
/*   85 */         if (this.outBindAccessors[j] != null)
/*   86 */           this.outBindAccessors[j].lastRowProcessed = 0;
/*   87 */     if (this.returnParamAccessors != null) {
/*   88 */       for (j = 0; j < this.returnParamAccessors.length; j++) {
/*   89 */         if (this.returnParamAccessors[j] != null) {
/*   90 */           this.returnParamAccessors[j].lastRowProcessed = 0;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     int i1;
/*      */     int i2;
/*   97 */     if (this.bindIndicators != null)
/*      */     {
/*   99 */       j = ((this.bindIndicators[(this.bindIndicatorSubRange + 3)] & 0xFFFF) << 16) + (this.bindIndicators[(this.bindIndicatorSubRange + 4)] & 0xFFFF);
/*      */       
/*      */ 
/*  102 */       int k = 0;
/*      */       
/*  104 */       if (this.ibtBindChars != null) {
/*  105 */         k = this.ibtBindChars.length * this.connection.conversion.cMaxCharSize;
/*      */       }
/*  107 */       for (int m = 0; m < this.numberOfBindPositions; m++)
/*      */       {
/*  109 */         int n = this.bindIndicatorSubRange + 5 + 10 * m;
/*      */         
/*      */ 
/*      */ 
/*  113 */         i1 = this.bindIndicators[(n + 2)] & 0xFFFF;
/*      */         
/*      */ 
/*      */ 
/*  117 */         if (i1 != 0)
/*      */         {
/*      */ 
/*  120 */           i2 = this.bindIndicators[(n + 9)] & 0xFFFF;
/*      */           
/*      */ 
/*      */ 
/*  124 */           if (i2 == 2)
/*      */           {
/*  126 */             k = Math.max(i1 * this.connection.conversion.maxNCharSize, k);
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*  131 */             k = Math.max(i1 * this.connection.conversion.cMaxCharSize, k);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  137 */       if (this.tmpBindsByteArray == null)
/*      */       {
/*  139 */         this.tmpBindsByteArray = new byte[k];
/*      */       }
/*  141 */       else if (this.tmpBindsByteArray.length < k)
/*      */       {
/*  143 */         this.tmpBindsByteArray = null;
/*  144 */         this.tmpBindsByteArray = new byte[k];
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
/*  156 */       this.tmpBindsByteArray = null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  161 */     int[] arrayOfInt1 = this.definedColumnType;
/*  162 */     int[] arrayOfInt2 = this.definedColumnSize;
/*  163 */     int[] arrayOfInt3 = this.definedColumnFormOfUse;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  169 */     if ((paramBoolean5) && (paramBoolean4) && (this.sqlObject.includeRowid))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  174 */       arrayOfInt1 = new int[this.definedColumnType.length + 1];
/*  175 */       System.arraycopy(this.definedColumnType, 0, arrayOfInt1, 1, this.definedColumnType.length);
/*  176 */       arrayOfInt1[0] = -8;
/*  177 */       arrayOfInt2 = new int[this.definedColumnSize.length + 1];
/*  178 */       System.arraycopy(this.definedColumnSize, 0, arrayOfInt2, 1, this.definedColumnSize.length);
/*  179 */       arrayOfInt3 = new int[this.definedColumnFormOfUse.length + 1];
/*  180 */       System.arraycopy(this.definedColumnFormOfUse, 0, arrayOfInt3, 1, this.definedColumnFormOfUse.length);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  186 */     allocateTmpByteArray();
/*      */     
/*  188 */     T4C8Oall localT4C8Oall = this.t4Connection.all8;
/*      */     
/*  190 */     this.t4Connection.sendPiggyBackedMessages();
/*      */     
/*      */ 
/*      */     try
/*      */     {
/*  195 */       localT4C8Oall.doOALL(paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramBoolean5, this.sqlKind, this.cursorId, this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals), this.rowPrefetch, this.outBindAccessors, this.numberOfBindPositions, this.accessors, i, this.bindBytes, this.bindChars, this.bindIndicators, this.bindIndicatorSubRange, this.connection.conversion, this.tmpBindsByteArray, this.parameterStream, parameterDatum, parameterOtype, this, this.ibtBindBytes, this.ibtBindChars, this.ibtBindIndicators, this.oacdefSent, arrayOfInt1, arrayOfInt2, arrayOfInt3, this.registration);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  207 */       i1 = localT4C8Oall.getCursorId();
/*  208 */       if (i1 != 0) {
/*  209 */         this.cursorId = i1;
/*      */       }
/*  211 */       this.oacdefSent = localT4C8Oall.oacdefBindsSent;
/*      */     }
/*      */     catch (SQLException localSQLException2)
/*      */     {
/*  215 */       i2 = localT4C8Oall.getCursorId();
/*  216 */       if (i2 != 0) {
/*  217 */         this.cursorId = i2;
/*      */       }
/*  219 */       if (localSQLException2.getErrorCode() == DatabaseError.getVendorCode(110))
/*      */       {
/*      */ 
/*  222 */         this.sqlWarning = DatabaseError.addSqlWarning(this.sqlWarning, 110);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  227 */         throw localSQLException2;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void allocateTmpByteArray()
/*      */   {
/*  237 */     if (this.tmpByteArray == null)
/*      */     {
/*      */ 
/*  240 */       this.tmpByteArray = new byte[this.sizeTmpByteArray];
/*      */     }
/*  242 */     else if (this.sizeTmpByteArray > this.tmpByteArray.length)
/*      */     {
/*      */ 
/*      */ 
/*  246 */       this.tmpByteArray = new byte[this.sizeTmpByteArray];
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
/*  258 */     super.releaseBuffers();
/*  259 */     this.tmpByteArray = null;
/*  260 */     this.tmpBindsByteArray = null;
/*      */     
/*  262 */     this.t4Connection.all8.bindChars = null;
/*  263 */     this.t4Connection.all8.bindBytes = null;
/*  264 */     this.t4Connection.all8.tmpBindsByteArray = null;
/*      */   }
/*      */   
/*      */ 
/*      */   void allocateRowidAccessor()
/*      */     throws SQLException
/*      */   {
/*  271 */     this.accessors[0] = new T4CRowidAccessor(this, 128, 1, -8, false, this.t4Connection.mare);
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
/*  284 */     this.needToParse = true;
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
/*  295 */     if (this.connection.disableDefinecolumntype)
/*      */     {
/*      */ 
/*  298 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  303 */     if ((paramInt2 == -15) || (paramInt2 == -9) || (paramInt2 == -16))
/*      */     {
/*  305 */       paramShort = 2;
/*      */     }
/*      */     
/*      */ 
/*      */     SQLException localSQLException;
/*      */     
/*  311 */     if (paramInt1 < 1)
/*      */     {
/*  313 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/*  314 */       localSQLException.fillInStackTrace();
/*  315 */       throw localSQLException;
/*      */     }
/*  317 */     if (paramBoolean)
/*      */     {
/*      */ 
/*      */ 
/*  321 */       if ((paramInt2 == 1) || (paramInt2 == 12) || (paramInt2 == -15) || (paramInt2 == -9))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  327 */         this.sqlWarning = DatabaseError.addSqlWarning(this.sqlWarning, 108);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*  333 */     else if (paramInt3 < 0)
/*      */     {
/*  335 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 53);
/*  336 */       localSQLException.fillInStackTrace();
/*  337 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  341 */     if ((this.currentResultSet != null) && (!this.currentResultSet.closed))
/*      */     {
/*  343 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 28);
/*  344 */       localSQLException.fillInStackTrace();
/*  345 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  352 */     int i = paramInt1 - 1;
/*      */     int[] arrayOfInt;
/*  354 */     if ((this.definedColumnType == null) || (this.definedColumnType.length <= i))
/*      */     {
/*  356 */       if (this.definedColumnType == null)
/*      */       {
/*  358 */         this.definedColumnType = new int[(i + 1) * 4];
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
/*  370 */         arrayOfInt = new int[(i + 1) * 4];
/*      */         
/*  372 */         System.arraycopy(this.definedColumnType, 0, arrayOfInt, 0, this.definedColumnType.length);
/*      */         
/*      */ 
/*  375 */         this.definedColumnType = arrayOfInt;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  381 */     this.definedColumnType[i] = paramInt2;
/*      */     
/*  383 */     if ((this.definedColumnSize == null) || (this.definedColumnSize.length <= i))
/*      */     {
/*  385 */       if (this.definedColumnSize == null) {
/*  386 */         this.definedColumnSize = new int[(i + 1) * 4];
/*      */       }
/*      */       else {
/*  389 */         arrayOfInt = new int[(i + 1) * 4];
/*      */         
/*  391 */         System.arraycopy(this.definedColumnSize, 0, arrayOfInt, 0, this.definedColumnSize.length);
/*      */         
/*      */ 
/*  394 */         this.definedColumnSize = arrayOfInt;
/*      */       }
/*      */     }
/*      */     
/*  398 */     this.definedColumnSize[i] = paramInt3;
/*      */     
/*  400 */     if ((this.definedColumnFormOfUse == null) || (this.definedColumnFormOfUse.length <= i))
/*      */     {
/*  402 */       if (this.definedColumnFormOfUse == null) {
/*  403 */         this.definedColumnFormOfUse = new int[(i + 1) * 4];
/*      */       }
/*      */       else {
/*  406 */         arrayOfInt = new int[(i + 1) * 4];
/*      */         
/*  408 */         System.arraycopy(this.definedColumnFormOfUse, 0, arrayOfInt, 0, this.definedColumnFormOfUse.length);
/*      */         
/*      */ 
/*  411 */         this.definedColumnFormOfUse = arrayOfInt;
/*      */       }
/*      */     }
/*      */     
/*  415 */     this.definedColumnFormOfUse[i] = paramShort;
/*      */     
/*  417 */     if ((this.accessors != null) && (i < this.accessors.length) && (this.accessors[i] != null))
/*      */     {
/*  419 */       this.accessors[i].definedColumnSize = paramInt3;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  424 */       if (((this.accessors[i].internalType == 96) || (this.accessors[i].internalType == 1)) && ((paramInt2 == 1) || (paramInt2 == 12)))
/*      */       {
/*      */ 
/*      */ 
/*  428 */         if (paramInt3 <= this.accessors[i].oacmxl)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  434 */           this.needToPrepareDefineBuffer = true;
/*  435 */           this.columnsDefinedByUser = true;
/*      */           
/*  437 */           this.accessors[i].initForDataAccess(paramInt2, paramInt3, null);
/*  438 */           this.accessors[i].calculateSizeTmpByteArray();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void clearDefines()
/*      */     throws SQLException
/*      */   {
/*  447 */     synchronized (this.connection)
/*      */     {
/*  449 */       super.clearDefines();
/*  450 */       this.definedColumnType = null;
/*  451 */       this.definedColumnSize = null;
/*  452 */       this.definedColumnFormOfUse = null;
/*  453 */       this.t4Connection.all8.definesAccessors = null;
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
/*  471 */     int i = this.rowPrefetchInLastFetch < this.rowPrefetch ? 1 : 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  500 */     if (paramBoolean)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  509 */       paramArrayOfShort = new short[this.defineIndicators.length];
/*  510 */       j = this.accessors[0].lengthIndexLastRow;
/*  511 */       int k = this.accessors[0].indicatorIndexLastRow;
/*      */       
/*  513 */       int i1 = i != 0 ? this.accessors.length : 1;
/*  514 */       for (; i != 0 ? i1 >= 1 : i1 <= this.accessors.length; 
/*  515 */           i1 += (i != 0 ? -1 : 1))
/*      */       {
/*  517 */         int m = j + this.rowPrefetchInLastFetch * i1 - 1;
/*  518 */         int n = k + this.rowPrefetchInLastFetch * i1 - 1;
/*  519 */         paramArrayOfShort[n] = this.defineIndicators[n];
/*  520 */         paramArrayOfShort[m] = this.defineIndicators[m];
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  527 */     int j = i != 0 ? this.accessors.length - 1 : 0;
/*  528 */     for (; i != 0 ? j > -1 : j < this.accessors.length; 
/*  529 */         j += (i != 0 ? -1 : 1))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  536 */       this.accessors[j].saveDataFromOldDefineBuffers(paramArrayOfByte, paramArrayOfChar, paramArrayOfShort, this.rowPrefetchInLastFetch != -1 ? this.rowPrefetchInLastFetch : this.rowPrefetch, this.rowPrefetch);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  543 */     super.saveDefineBuffersIfRequired(paramArrayOfChar, paramArrayOfByte, paramArrayOfShort, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void doSetSnapshotSCN(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  553 */     this.inScn = paramLong;
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
/*  573 */     Object localObject = null;
/*      */     SQLException localSQLException;
/*  575 */     switch (paramInt1)
/*      */     {
/*      */ 
/*      */     case 96: 
/*  579 */       localObject = new T4CCharAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  582 */       break;
/*      */     
/*      */     case 8: 
/*  585 */       if (!paramBoolean)
/*      */       {
/*  587 */         localObject = new T4CLongAccessor(this, paramInt3, paramInt4, paramShort, paramInt2, this.t4Connection.mare);
/*      */       }
/*      */       
/*  590 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 1: 
/*  595 */       localObject = new T4CVarcharAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  598 */       break;
/*      */     
/*      */     case 2: 
/*  601 */       localObject = new T4CNumberAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  604 */       break;
/*      */     
/*      */     case 6: 
/*  607 */       localObject = new T4CVarnumAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  610 */       break;
/*      */     
/*      */     case 24: 
/*  613 */       if (!paramBoolean)
/*      */       {
/*  615 */         localObject = new T4CLongRawAccessor(this, paramInt3, paramInt4, paramShort, paramInt2, this.t4Connection.mare);
/*      */       }
/*      */       
/*  618 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 23: 
/*  623 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/*  625 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/*  626 */         localSQLException.fillInStackTrace();
/*  627 */         throw localSQLException;
/*      */       }
/*      */       
/*  630 */       if (paramBoolean) {
/*  631 */         localObject = new T4COutRawAccessor(this, paramInt4, paramShort, paramInt2, this.t4Connection.mare);
/*      */       }
/*      */       else {
/*  634 */         localObject = new T4CRawAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       }
/*      */       
/*  637 */       break;
/*      */     
/*      */     case 100: 
/*  640 */       localObject = new T4CBinaryFloatAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  643 */       break;
/*      */     
/*      */     case 101: 
/*  646 */       localObject = new T4CBinaryDoubleAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  649 */       break;
/*      */     
/*      */     case 104: 
/*  652 */       if (this.sqlKind == OracleStatement.SqlKind.CALL_BLOCK)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  658 */         localObject = new T4CVarcharAccessor(this, 18, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */         
/*      */ 
/*      */ 
/*  662 */         ((Accessor)localObject).definedColumnType = -8;
/*      */       }
/*      */       else {
/*  665 */         localObject = new T4CRowidAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       }
/*      */       
/*      */ 
/*  669 */       break;
/*      */     
/*      */     case 102: 
/*  672 */       localObject = new T4CResultSetAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  675 */       break;
/*      */     
/*      */     case 12: 
/*  678 */       localObject = new T4CDateAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  681 */       break;
/*      */     
/*      */     case 113: 
/*  684 */       localObject = new T4CBlobAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  687 */       break;
/*      */     
/*      */     case 112: 
/*  690 */       localObject = new T4CClobAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  693 */       break;
/*      */     
/*      */     case 114: 
/*  696 */       localObject = new T4CBfileAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  699 */       break;
/*      */     
/*      */     case 109: 
/*  702 */       localObject = new T4CNamedTypeAccessor(this, paramString, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  705 */       ((Accessor)localObject).initMetadata();
/*      */       
/*  707 */       break;
/*      */     
/*      */     case 111: 
/*  710 */       localObject = new T4CRefTypeAccessor(this, paramString, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  713 */       ((Accessor)localObject).initMetadata();
/*      */       
/*  715 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 180: 
/*  720 */       localObject = new T4CTimestampAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  723 */       break;
/*      */     
/*      */     case 181: 
/*  726 */       localObject = new T4CTimestamptzAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  729 */       break;
/*      */     
/*      */     case 231: 
/*  732 */       localObject = new T4CTimestampltzAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  735 */       break;
/*      */     
/*      */     case 182: 
/*  738 */       localObject = new T4CIntervalymAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  741 */       break;
/*      */     
/*      */     case 183: 
/*  744 */       localObject = new T4CIntervaldsAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean, this.t4Connection.mare);
/*      */       
/*      */ 
/*  747 */       break;
/*      */     
/*      */ 
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
/*  760 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 89);
/*  761 */       localSQLException.fillInStackTrace();
/*  762 */       throw localSQLException;
/*      */     }
/*      */     
/*      */     
/*  766 */     return (Accessor)localObject;
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
/*  793 */     if (!this.isOpen)
/*      */     {
/*      */ 
/*      */ 
/*  797 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 144);
/*  798 */       localSQLException1.fillInStackTrace();
/*  799 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  806 */       this.t4Connection.needLine();
/*  807 */       this.t4Connection.sendPiggyBackedMessages();
/*  808 */       this.t4Connection.describe.doODNY(this, 0, this.accessors, this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals));
/*  809 */       this.accessors = this.t4Connection.describe.getAccessors();
/*      */       
/*  811 */       this.numberOfDefinePositions = this.t4Connection.describe.numuds;
/*      */       
/*  813 */       for (int i = 0; i < this.numberOfDefinePositions; i++) {
/*  814 */         this.accessors[i].initMetadata();
/*      */       }
/*      */     }
/*      */     catch (IOException localIOException) {
/*  818 */       ((T4CConnection)this.connection).handleIOException(localIOException);
/*      */       
/*      */ 
/*  821 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  822 */       localSQLException2.fillInStackTrace();
/*  823 */       throw localSQLException2;
/*      */     }
/*      */     
/*      */ 
/*  827 */     this.describedWithNames = true;
/*  828 */     this.described = true;
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
/*  863 */     this.t4Connection.assertLoggedOn("oracle.jdbc.driver.T4CStatement.execute_for_describe");
/*      */     try
/*      */     {
/*  866 */       if (this.t4Connection.useFetchSizeWithLongColumn)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  872 */         doOall8(true, true, true, true, false);
/*      */       }
/*      */       else
/*      */       {
/*  876 */         doOall8(true, true, false, true, this.definedColumnType != null);
/*      */       }
/*      */       
/*      */     }
/*      */     catch (SQLException localSQLException1)
/*      */     {
/*  882 */       throw localSQLException1;
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*  886 */       ((T4CConnection)this.connection).handleIOException(localIOException);
/*      */       
/*  888 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  889 */       localSQLException2.fillInStackTrace();
/*  890 */       throw localSQLException2;
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*  895 */       this.rowsProcessed = this.t4Connection.all8.rowsProcessed;
/*  896 */       this.validRows = this.t4Connection.all8.getNumRows();
/*      */     }
/*      */     
/*  899 */     this.needToParse = false;
/*      */     
/*      */ 
/*  902 */     if (this.connection.calculateChecksum) {
/*  903 */       if (this.validRows > 0) {
/*  904 */         calculateCheckSum();
/*  905 */       } else if (this.rowsProcessed > 0) {
/*  906 */         long l = CRC64.updateChecksum(this.checkSum, this.rowsProcessed);
/*      */         
/*  908 */         this.checkSum = l;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  918 */     if (this.definedColumnType == null) {
/*  919 */       this.implicitDefineForLobPrefetchDone = false;
/*      */     }
/*  921 */     this.aFetchWasDoneDuringDescribe = false;
/*  922 */     if (this.t4Connection.all8.aFetchWasDone)
/*      */     {
/*  924 */       this.aFetchWasDoneDuringDescribe = true;
/*  925 */       this.rowPrefetchInLastFetch = this.rowPrefetch;
/*      */     }
/*      */     
/*      */ 
/*  929 */     for (int i = 0; i < this.numberOfDefinePositions; i++) {
/*  930 */       this.accessors[i].initMetadata();
/*      */     }
/*  932 */     this.needToPrepareDefineBuffer = false;
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
/*  974 */         boolean bool = false;
/*  975 */         if (this.columnsDefinedByUser) {
/*  976 */           this.needToPrepareDefineBuffer = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  996 */         else if ((this.t4Connection.useLobPrefetch) && (this.accessors != null) && (this.defaultLobPrefetchSize != -1) && (!this.implicitDefineForLobPrefetchDone) && (!this.aFetchWasDoneDuringDescribe) && (this.definedColumnType == null))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1004 */           int i = 0;
/* 1005 */           int[] arrayOfInt1 = new int[this.accessors.length];
/* 1006 */           int[] arrayOfInt2 = new int[this.accessors.length];
/*      */           
/* 1008 */           for (int j = 0; j < this.accessors.length; j++)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/* 1013 */             arrayOfInt1[j] = getJDBCType(this.accessors[j].internalType);
/* 1014 */             if ((this.accessors[j].internalType == 113) || (this.accessors[j].internalType == 112) || (this.accessors[j].internalType == 114))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1020 */               i = 1;
/* 1021 */               this.accessors[j].lobPrefetchSizeForThisColumn = this.defaultLobPrefetchSize;
/* 1022 */               arrayOfInt2[j] = this.defaultLobPrefetchSize;
/*      */             }
/*      */           }
/*      */           
/* 1026 */           if (i != 0)
/*      */           {
/* 1028 */             this.definedColumnType = arrayOfInt1;
/* 1029 */             this.definedColumnSize = arrayOfInt2;
/* 1030 */             bool = true;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1036 */         doOall8(this.needToParse, !paramBoolean, true, false, bool);
/*      */         
/* 1038 */         this.needToParse = false;
/* 1039 */         if (bool) {
/* 1040 */           this.implicitDefineForLobPrefetchDone = true;
/*      */         }
/*      */       }
/*      */       finally {
/* 1044 */         this.validRows = this.t4Connection.all8.getNumRows();
/*      */       }
/*      */     }
/*      */     catch (SQLException localSQLException1)
/*      */     {
/* 1049 */       throw localSQLException1;
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 1053 */       ((T4CConnection)this.connection).handleIOException(localIOException);
/* 1054 */       calculateCheckSum();
/*      */       
/* 1056 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1057 */       localSQLException2.fillInStackTrace();
/* 1058 */       throw localSQLException2;
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
/* 1085 */     if (this.streamList != null)
/*      */     {
/*      */ 
/*      */ 
/* 1089 */       while (this.nextStream != null)
/*      */       {
/*      */         try
/*      */         {
/* 1093 */           this.nextStream.close();
/*      */         }
/*      */         catch (IOException localIOException1)
/*      */         {
/* 1097 */           ((T4CConnection)this.connection).handleIOException(localIOException1);
/*      */           
/* 1099 */           localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException1);
/* 1100 */           localSQLException.fillInStackTrace();
/* 1101 */           throw localSQLException;
/*      */         }
/*      */         
/*      */ 
/* 1105 */         this.nextStream = this.nextStream.nextStream;
/*      */       }
/*      */     }
/*      */     
/*      */     try
/*      */     {
/* 1111 */       doOall8(false, false, true, false, false);
/*      */       
/* 1113 */       this.validRows = this.t4Connection.all8.getNumRows();
/*      */     }
/*      */     catch (IOException localIOException2)
/*      */     {
/* 1117 */       ((T4CConnection)this.connection).handleIOException(localIOException2);
/*      */       
/* 1119 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException2);
/* 1120 */       localSQLException.fillInStackTrace();
/* 1121 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1127 */     calculateCheckSum();
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
/* 1142 */       if (!this.connection.useFetchSizeWithLongColumn)
/*      */       {
/* 1144 */         T4C8Oall localT4C8Oall = this.t4Connection.all8;
/*      */         
/* 1146 */         localT4C8Oall.continueReadRow(paramInt, this);
/*      */       }
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 1151 */       ((T4CConnection)this.connection).handleIOException(localIOException);
/*      */       
/* 1153 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1154 */       localSQLException2.fillInStackTrace();
/* 1155 */       throw localSQLException2;
/*      */ 
/*      */     }
/*      */     catch (SQLException localSQLException1)
/*      */     {
/* 1160 */       if (localSQLException1.getErrorCode() == DatabaseError.getVendorCode(110))
/*      */       {
/*      */ 
/* 1163 */         this.sqlWarning = DatabaseError.addSqlWarning(this.sqlWarning, 110);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1168 */         throw localSQLException1;
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
/* 1192 */     this.t4Connection.assertLoggedOn("oracle.jdbc.driver.T4CStatement.do_close");
/*      */     
/* 1194 */     if (this.cursorId != 0) {
/* 1195 */       this.t4Connection.closeCursor(this.cursorId);
/*      */     }
/*      */     
/* 1198 */     this.tmpByteArray = null;
/* 1199 */     this.tmpBindsByteArray = null;
/* 1200 */     this.definedColumnType = null;
/* 1201 */     this.definedColumnSize = null;
/* 1202 */     this.definedColumnFormOfUse = null;
/* 1203 */     this.oacdefSent = null;
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
/* 1223 */     this.t4Connection.assertLoggedOn("oracle.jdbc.driver.T4CStatement.closeQuery");
/*      */     
/* 1225 */     if (this.streamList != null)
/*      */     {
/* 1227 */       while (this.nextStream != null) {
/*      */         try {
/* 1229 */           this.nextStream.close();
/*      */         }
/*      */         catch (IOException localIOException) {
/* 1232 */           ((T4CConnection)this.connection).handleIOException(localIOException);
/*      */           
/* 1234 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1235 */           localSQLException.fillInStackTrace();
/* 1236 */           throw localSQLException;
/*      */         }
/*      */         
/*      */ 
/* 1240 */         this.nextStream = this.nextStream.nextStream;
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
/*      */   T4CStatement(PhysicalConnection paramPhysicalConnection, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1255 */     super(paramPhysicalConnection, 1, paramPhysicalConnection.defaultRowPrefetch, paramInt1, paramInt2);
/*      */     
/* 1257 */     this.nbPostPonedColumns = new int[1];
/* 1258 */     this.nbPostPonedColumns[0] = 0;
/* 1259 */     this.indexOfPostPonedColumn = new int[1][3];
/* 1260 */     this.t4Connection = ((T4CConnection)paramPhysicalConnection);
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
/*      */   void closeCursorOnPlainStatement()
/*      */     throws SQLException
/*      */   {
/* 1275 */     if ((this.cursorId != 0) && (this.t4Connection.isLoggedOn())) {
/* 1276 */       this.t4Connection.closeCursor(this.cursorId);
/* 1277 */       setCursorId(0);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1283 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */