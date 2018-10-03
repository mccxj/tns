/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.nio.CharBuffer;
/*      */ import java.nio.ShortBuffer;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class T2CCallableStatement
/*      */   extends OracleCallableStatement
/*      */ {
/*   42 */   T2CConnection connection = null;
/*   43 */   int userResultSetType = -1;
/*   44 */   int userResultSetConcur = -1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   50 */   static int T2C_EXTEND_BUFFER = -3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   65 */   long[] t2cOutput = new long[10];
/*      */   
/*      */ 
/*      */ 
/*      */   static final int T2C_OUTPUT_USE_NIO = 5;
/*      */   
/*      */ 
/*      */ 
/*      */   static final int T2C_OUTPUT_STMT_LOB_PREFETCH_SIZE = 6;
/*      */   
/*      */ 
/*      */ 
/*      */   int extractedCharOffset;
/*      */   
/*      */ 
/*      */ 
/*      */   int extractedByteOffset;
/*      */   
/*      */ 
/*      */ 
/*      */   static final byte T2C_LOB_PREFETCH_SIZE_THIS_COLUMN_OFFSET = 0;
/*      */   
/*      */ 
/*      */   static final byte T2C_LOB_PREFETCH_LOB_LENGTH_OFFSET = 1;
/*      */   
/*      */ 
/*      */   static final byte T2C_LOB_PREFETCH_FORM_OFFSET = 2;
/*      */   
/*      */ 
/*      */   static final byte T2C_LOB_PREFETCH_CHUNK_OFFSET = 3;
/*      */   
/*      */ 
/*      */   static final byte T2C_LOB_PREFETCH_DATA_OFFSET = 4;
/*      */   
/*      */ 
/*      */ 
/*      */   T2CCallableStatement(T2CConnection paramT2CConnection, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/*  104 */     super(paramT2CConnection, paramString, paramInt1, paramInt2, paramInt3, paramInt4);
/*      */     
/*      */ 
/*  107 */     this.userResultSetType = paramInt3;
/*  108 */     this.userResultSetConcur = paramInt4;
/*      */     
/*      */ 
/*  111 */     this.connection = paramT2CConnection;
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
/*      */   String bytes2String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  127 */     byte[] arrayOfByte = new byte[paramInt2];
/*      */     
/*  129 */     System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
/*      */     
/*  131 */     return this.connection.conversion.CharBytesToString(arrayOfByte, paramInt2);
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
/*      */   void processDescribeData()
/*      */     throws SQLException
/*      */   {
/*  147 */     this.described = true;
/*  148 */     this.describedWithNames = true;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  159 */     if ((this.accessors == null) || (this.numberOfDefinePositions > this.accessors.length)) {
/*  160 */       this.accessors = new Accessor[this.numberOfDefinePositions];
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  176 */     int i = this.connection.queryMetaData1Offset;
/*  177 */     int j = this.connection.queryMetaData2Offset;
/*  178 */     short[] arrayOfShort = this.connection.queryMetaData1;
/*  179 */     byte[] arrayOfByte = this.connection.queryMetaData2;
/*      */     
/*  181 */     for (int k = 0; k < this.numberOfDefinePositions; 
/*  182 */         i += 13)
/*      */     {
/*  184 */       int m = arrayOfShort[(i + 0)];
/*  185 */       int n = arrayOfShort[(i + 1)];
/*  186 */       int i1 = arrayOfShort[(i + 11)];
/*  187 */       boolean bool = arrayOfShort[(i + 2)] != 0;
/*  188 */       int i2 = arrayOfShort[(i + 3)];
/*  189 */       int i3 = arrayOfShort[(i + 4)];
/*  190 */       int i4 = 0;
/*  191 */       int i5 = 0;
/*  192 */       int i6 = 0;
/*  193 */       short s = arrayOfShort[(i + 5)];
/*  194 */       int i7 = arrayOfShort[(i + 6)];
/*  195 */       String str1 = bytes2String(arrayOfByte, j, i7);
/*  196 */       int i8 = arrayOfShort[(i + 12)];
/*  197 */       String str2 = null;
/*  198 */       OracleTypeADT localOracleTypeADT = null;
/*      */       
/*  200 */       j += i7;
/*      */       
/*  202 */       if (i8 > 0)
/*      */       {
/*  204 */         str2 = bytes2String(arrayOfByte, j, i8);
/*  205 */         j += i8;
/*  206 */         localOracleTypeADT = new OracleTypeADT(str2, this.connection);
/*  207 */         localOracleTypeADT.tdoCState = ((arrayOfShort[(i + 7)] & 0xFFFF) << 48 | (arrayOfShort[(i + 8)] & 0xFFFF) << 32 | (arrayOfShort[(i + 9)] & 0xFFFF) << 16 | arrayOfShort[(i + 10)] & 0xFFFF);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  215 */       Object localObject = this.accessors[k];
/*      */       
/*  217 */       if ((localObject != null) && (!((Accessor)localObject).useForDescribeIfPossible(m, n, bool, i4, i2, i3, i5, i6, s, str2)))
/*      */       {
/*      */ 
/*      */ 
/*  221 */         localObject = null;
/*      */       }
/*      */       
/*  224 */       if (localObject == null)
/*      */       {
/*  226 */         switch (m)
/*      */         {
/*      */ 
/*      */         case 1: 
/*  230 */           localObject = new VarcharAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  234 */           if (i1 > 0) {
/*  235 */             ((Accessor)localObject).setDisplaySize(i1);
/*      */           }
/*      */           
/*      */           break;
/*      */         case 96: 
/*  240 */           localObject = new CharAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  244 */           if (i1 > 0) {
/*  245 */             ((Accessor)localObject).setDisplaySize(i1);
/*      */           }
/*      */           
/*      */           break;
/*      */         case 2: 
/*  250 */           localObject = new NumberAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  254 */           break;
/*      */         
/*      */         case 23: 
/*  257 */           localObject = new RawAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  261 */           break;
/*      */         
/*      */         case 100: 
/*  264 */           localObject = new BinaryFloatAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  268 */           break;
/*      */         
/*      */         case 101: 
/*  271 */           localObject = new BinaryDoubleAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  275 */           break;
/*      */         
/*      */         case 8: 
/*  278 */           localObject = new LongAccessor(this, k + 1, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  285 */           this.rowPrefetch = 1;
/*      */           
/*  287 */           break;
/*      */         
/*      */         case 24: 
/*  290 */           localObject = new LongRawAccessor(this, k + 1, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  297 */           this.rowPrefetch = 1;
/*      */           
/*  299 */           break;
/*      */         
/*      */         case 104: 
/*  302 */           localObject = new RowidAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  306 */           break;
/*      */         
/*      */ 
/*      */         case 102: 
/*      */         case 116: 
/*  311 */           localObject = new T2CResultSetAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  315 */           break;
/*      */         
/*      */         case 12: 
/*  318 */           localObject = new DateAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  322 */           break;
/*      */         
/*      */         case 180: 
/*  325 */           localObject = new TimestampAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  329 */           break;
/*      */         
/*      */         case 181: 
/*  332 */           localObject = new TimestamptzAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  336 */           break;
/*      */         
/*      */         case 231: 
/*  339 */           localObject = new TimestampltzAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  343 */           break;
/*      */         
/*      */         case 182: 
/*  346 */           localObject = new IntervalymAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  350 */           break;
/*      */         
/*      */         case 183: 
/*  353 */           localObject = new IntervaldsAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  357 */           break;
/*      */         
/*      */         case 112: 
/*  360 */           localObject = new ClobAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  364 */           break;
/*      */         
/*      */         case 113: 
/*  367 */           localObject = new BlobAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  371 */           break;
/*      */         
/*      */         case 114: 
/*  374 */           localObject = new BfileAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  378 */           break;
/*      */         
/*      */         case 109: 
/*  381 */           localObject = new NamedTypeAccessor(this, n, bool, i4, i2, i3, i5, i6, s, str2, localOracleTypeADT);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  386 */           break;
/*      */         
/*      */         case 111: 
/*  389 */           localObject = new RefTypeAccessor(this, n, bool, i4, i2, i3, i5, i6, s, str2, localOracleTypeADT);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  394 */           break;
/*      */         
/*      */ 
/*      */         default: 
/*  398 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Unknown or unimplemented accessor type: " + m);
/*      */           
/*  400 */           localSQLException.fillInStackTrace();
/*  401 */           throw localSQLException;
/*      */         }
/*      */         
/*      */         
/*      */ 
/*  406 */         this.accessors[k] = localObject;
/*      */       }
/*  408 */       else if (localOracleTypeADT != null)
/*      */       {
/*      */ 
/*      */ 
/*  412 */         ((Accessor)localObject).describeOtype = localOracleTypeADT;
/*  413 */         ((Accessor)localObject).initMetadata();
/*      */       }
/*      */       
/*  416 */       ((Accessor)localObject).columnName = str1;k++;
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
/*      */   void executeForDescribe()
/*      */     throws SQLException
/*      */   {
/*  457 */     this.t2cOutput[0] = 0L;
/*  458 */     this.t2cOutput[2] = 0L;
/*      */     
/*      */ 
/*      */ 
/*  462 */     this.lobPrefetchMetaData = null;
/*      */     
/*  464 */     boolean bool1 = !this.described;
/*  465 */     boolean bool2 = false;
/*      */     
/*      */     int i;
/*      */     do
/*      */     {
/*  470 */       i = 0;
/*      */       
/*      */ 
/*  473 */       if (this.connection.endToEndAnyChanged)
/*      */       {
/*  475 */         pushEndToEndValues();
/*      */         
/*  477 */         this.connection.endToEndAnyChanged = false;
/*      */       }
/*      */       
/*      */ 
/*  481 */       byte[] arrayOfByte = this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals);
/*      */       
/*  483 */       int j = 0;
/*      */       try
/*      */       {
/*  486 */         j = T2CStatement.t2cParseExecuteDescribe(this, this.c_state, this.numberOfBindPositions, this.numberOfBindRowsAllocated, this.firstRowInBatch, this.currentRowBindAccessors != null, this.needToParse, bool1, bool2, arrayOfByte, arrayOfByte.length, T2CStatement.convertSqlKindEnumToByte(this.sqlKind), this.rowPrefetch, this.batch, this.bindIndicators, this.bindIndicatorOffset, this.bindBytes, this.bindChars, this.bindByteOffset, this.bindCharOffset, this.ibtBindIndicators, this.ibtBindIndicatorOffset, this.ibtBindIndicatorSize, this.ibtBindBytes, this.ibtBindChars, this.ibtBindByteOffset, this.ibtBindCharOffset, this.returnParamMeta, this.connection.queryMetaData1, this.connection.queryMetaData2, this.connection.queryMetaData1Offset, this.connection.queryMetaData2Offset, this.connection.queryMetaData1Size, this.connection.queryMetaData2Size, this.preparedAllBinds, this.preparedCharBinds, this.outBindAccessors, this.parameterDatum, this.t2cOutput, this.defineBytes, this.accessorByteOffset, this.defineChars, this.accessorCharOffset, this.defineIndicators, this.accessorShortOffset, this.connection.plsqlCompilerWarnings);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  536 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 266);
/*  537 */         localSQLException.fillInStackTrace();
/*  538 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  543 */       this.validRows = ((int)this.t2cOutput[1]);
/*      */       
/*      */ 
/*  546 */       if ((j == -1) || (j == -4))
/*      */       {
/*  548 */         this.connection.checkError(j);
/*      */       }
/*  550 */       else if (j == T2C_EXTEND_BUFFER)
/*      */       {
/*  552 */         j = this.connection.queryMetaData1Size * 2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  557 */       if (this.t2cOutput[3] != 0L)
/*      */       {
/*  559 */         foundPlsqlCompilerWarning();
/*      */       }
/*  561 */       else if (this.t2cOutput[2] != 0L)
/*      */       {
/*  563 */         this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  568 */       this.connection.endToEndECIDSequenceNumber = ((short)(int)this.t2cOutput[4]);
/*      */       
/*      */ 
/*  571 */       this.needToParse = false;
/*  572 */       bool2 = true;
/*      */       
/*  574 */       if (this.sqlKind.isSELECT())
/*      */       {
/*  576 */         this.numberOfDefinePositions = j;
/*      */         
/*  578 */         if (this.numberOfDefinePositions > this.connection.queryMetaData1Size)
/*      */         {
/*  580 */           i = 1;
/*  581 */           bool2 = true;
/*      */           
/*      */ 
/*  584 */           this.connection.reallocateQueryMetaData(this.numberOfDefinePositions, this.numberOfDefinePositions * 8);
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  591 */         this.numberOfDefinePositions = 0;
/*  592 */         this.validRows = j;
/*      */       }
/*      */       
/*  595 */     } while (i != 0);
/*      */     
/*  597 */     processDescribeData();
/*      */   }
/*      */   
/*      */ 
/*      */   void pushEndToEndValues()
/*      */     throws SQLException
/*      */   {
/*  604 */     T2CConnection localT2CConnection = this.connection;
/*  605 */     byte[] arrayOfByte1 = new byte[0];
/*  606 */     byte[] arrayOfByte2 = new byte[0];
/*  607 */     byte[] arrayOfByte3 = new byte[0];
/*  608 */     byte[] arrayOfByte4 = new byte[0];
/*      */     
/*  610 */     if (localT2CConnection.endToEndValues != null) {
/*      */       String str;
/*  612 */       if (localT2CConnection.endToEndHasChanged[0] != 0)
/*      */       {
/*  614 */         str = localT2CConnection.endToEndValues[0];
/*      */         
/*  616 */         if (str != null) {
/*  617 */           arrayOfByte1 = DBConversion.stringToDriverCharBytes(str, localT2CConnection.m_clientCharacterSet);
/*      */         }
/*      */         
/*  620 */         localT2CConnection.endToEndHasChanged[0] = false;
/*      */       }
/*      */       
/*  623 */       if (localT2CConnection.endToEndHasChanged[1] != 0)
/*      */       {
/*  625 */         str = localT2CConnection.endToEndValues[1];
/*      */         
/*  627 */         if (str != null) {
/*  628 */           arrayOfByte2 = DBConversion.stringToDriverCharBytes(str, localT2CConnection.m_clientCharacterSet);
/*      */         }
/*      */         
/*  631 */         localT2CConnection.endToEndHasChanged[1] = false;
/*      */       }
/*      */       
/*  634 */       if (localT2CConnection.endToEndHasChanged[2] != 0)
/*      */       {
/*  636 */         str = localT2CConnection.endToEndValues[2];
/*      */         
/*  638 */         if (str != null) {
/*  639 */           arrayOfByte3 = DBConversion.stringToDriverCharBytes(str, localT2CConnection.m_clientCharacterSet);
/*      */         }
/*      */         
/*  642 */         localT2CConnection.endToEndHasChanged[2] = false;
/*      */       }
/*      */       
/*  645 */       if (localT2CConnection.endToEndHasChanged[3] != 0)
/*      */       {
/*  647 */         str = localT2CConnection.endToEndValues[3];
/*      */         
/*  649 */         if (str != null) {
/*  650 */           arrayOfByte4 = DBConversion.stringToDriverCharBytes(str, localT2CConnection.m_clientCharacterSet);
/*      */         }
/*      */         
/*  653 */         localT2CConnection.endToEndHasChanged[3] = false;
/*      */       }
/*      */       
/*  656 */       T2CStatement.t2cEndToEndUpdate(this.c_state, arrayOfByte1, arrayOfByte1.length, arrayOfByte2, arrayOfByte2.length, arrayOfByte3, arrayOfByte3.length, arrayOfByte4, arrayOfByte4.length, localT2CConnection.endToEndECIDSequenceNumber);
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
/*      */   void executeForRows(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  709 */     if (this.connection.endToEndAnyChanged)
/*      */     {
/*  711 */       pushEndToEndValues();
/*      */       
/*  713 */       this.connection.endToEndAnyChanged = false;
/*      */     }
/*      */     
/*      */ 
/*  717 */     if (!paramBoolean)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  725 */       if (this.numberOfDefinePositions > 0)
/*      */       {
/*  727 */         doDefineExecuteFetch();
/*      */       }
/*      */       else
/*      */       {
/*  731 */         executeForDescribe();
/*      */       }
/*      */     }
/*  734 */     else if (this.numberOfDefinePositions > 0) {
/*  735 */       doDefineFetch();
/*      */     }
/*      */     
/*      */ 
/*  739 */     this.needToPrepareDefineBuffer = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void setupForDefine()
/*      */     throws SQLException
/*      */   {
/*  749 */     if (this.numberOfDefinePositions > this.connection.queryMetaData1Size)
/*      */     {
/*  751 */       int i = this.numberOfDefinePositions / 100 + 1;
/*      */       
/*  753 */       this.connection.reallocateQueryMetaData(this.connection.queryMetaData1Size * i, this.connection.queryMetaData2Size * i * 8);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  758 */     short[] arrayOfShort = this.connection.queryMetaData1;
/*  759 */     int j = this.connection.queryMetaData1Offset;
/*      */     
/*      */ 
/*  762 */     for (int k = 0; k < this.numberOfDefinePositions; 
/*  763 */         j += 13)
/*      */     {
/*  765 */       Accessor localAccessor = this.accessors[k];
/*      */       
/*  767 */       if (localAccessor == null)
/*      */       {
/*  769 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  770 */         localSQLException.fillInStackTrace();
/*  771 */         throw localSQLException;
/*      */       }
/*      */       
/*  774 */       arrayOfShort[(j + 0)] = ((short)localAccessor.defineType);
/*      */       
/*  776 */       arrayOfShort[(j + 11)] = ((short)localAccessor.charLength);
/*      */       
/*  778 */       arrayOfShort[(j + 1)] = ((short)localAccessor.byteLength);
/*      */       
/*  780 */       arrayOfShort[(j + 5)] = localAccessor.formOfUse;
/*      */       
/*      */ 
/*  783 */       if (localAccessor.internalOtype != null)
/*      */       {
/*  785 */         long l = ((OracleTypeADT)localAccessor.internalOtype).getTdoCState();
/*      */         
/*      */ 
/*  788 */         arrayOfShort[(j + 7)] = ((short)(int)((l & 0xFFFF000000000000) >> 48));
/*      */         
/*  790 */         arrayOfShort[(j + 8)] = ((short)(int)((l & 0xFFFF00000000) >> 32));
/*      */         
/*  792 */         arrayOfShort[(j + 9)] = ((short)(int)((l & 0xFFFF0000) >> 16));
/*      */         
/*  794 */         arrayOfShort[(j + 10)] = ((short)(int)(l & 0xFFFF));
/*      */       }
/*      */       
/*      */ 
/*  798 */       switch (localAccessor.internalType)
/*      */       {
/*      */ 
/*      */ 
/*      */       case 112: 
/*      */       case 113: 
/*  804 */         if (localAccessor.lobPrefetchSizeForThisColumn == -1) {
/*  805 */           localAccessor.lobPrefetchSizeForThisColumn = this.defaultLobPrefetchSize;
/*      */         }
/*      */         
/*  808 */         arrayOfShort[(j + 7)] = ((short)localAccessor.lobPrefetchSizeForThisColumn);
/*      */       }
/*      */       
/*  763 */       k++;
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
/*      */   Object[] getLobPrefetchMetaData()
/*      */   {
/*  828 */     Object[] arrayOfObject = null;
/*  829 */     Object localObject = null;
/*  830 */     int[] arrayOfInt = null;
/*  831 */     int i = 0;
/*  832 */     int j = 0;
/*      */     int k;
/*  834 */     if (this.accessors != null) {
/*  835 */       for (k = 0; k < this.numberOfDefinePositions; k++)
/*      */       {
/*  837 */         switch (this.accessors[k].internalType)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */         case 8: 
/*      */         case 24: 
/*  844 */           j = k;
/*  845 */           break;
/*      */         
/*      */ 
/*      */ 
/*      */         case 112: 
/*      */         case 113: 
/*  851 */           if (arrayOfInt == null)
/*      */           {
/*  853 */             arrayOfInt = new int[this.accessors.length];
/*      */           }
/*      */           
/*  856 */           if (this.accessors[k].lobPrefetchSizeForThisColumn != -1)
/*      */           {
/*  858 */             i++;
/*      */             
/*  860 */             arrayOfInt[k] = this.accessors[k].lobPrefetchSizeForThisColumn;
/*      */           }
/*      */           else
/*      */           {
/*  864 */             arrayOfInt[k] = -1;
/*      */           }
/*      */           
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*  872 */     if (i > 0)
/*      */     {
/*  874 */       if (arrayOfObject == null)
/*      */       {
/*  876 */         arrayOfObject = new Object[] { null, new long[this.rowPrefetch * i], new byte[this.accessors.length], new int[this.accessors.length], new Object[this.rowPrefetch * i] };
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  898 */       for (k = 0; k < j; k++)
/*      */       {
/*  900 */         switch (this.accessors[k].internalType)
/*      */         {
/*      */         case 112: 
/*      */         case 113: 
/*  904 */           this.accessors[k].lobPrefetchSizeForThisColumn = -1;
/*  905 */           arrayOfInt[k] = -1;
/*      */         }
/*      */         
/*      */       }
/*      */       
/*  910 */       arrayOfObject[0] = arrayOfInt;
/*      */     }
/*      */     
/*      */ 
/*  914 */     return arrayOfObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void processLobPrefetchMetaData(Object[] paramArrayOfObject)
/*      */   {
/*  921 */     int i = 0;
/*  922 */     int j = this.validRows == -2 ? 1 : this.validRows;
/*      */     
/*  924 */     byte[] arrayOfByte = (byte[])paramArrayOfObject[2];
/*  925 */     int[] arrayOfInt1 = (int[])paramArrayOfObject[3];
/*  926 */     long[] arrayOfLong = (long[])paramArrayOfObject[1];
/*  927 */     Object[] arrayOfObject = (Object[])paramArrayOfObject[4];
/*  928 */     int[] arrayOfInt2 = (int[])paramArrayOfObject[0];
/*      */     
/*  930 */     if (this.accessors != null) {
/*  931 */       for (int k = 0; k < this.numberOfDefinePositions; k++)
/*      */       {
/*  933 */         switch (this.accessors[k].internalType)
/*      */         {
/*      */ 
/*      */         case 112: 
/*      */         case 113: 
/*  938 */           if (this.accessors[k].lobPrefetchSizeForThisColumn >= 0)
/*      */           {
/*  940 */             Accessor localAccessor = this.accessors[k];
/*      */             
/*  942 */             if ((localAccessor.prefetchedLobDataL == null) || (localAccessor.prefetchedLobDataL.length < this.rowPrefetch))
/*      */             {
/*      */ 
/*  945 */               if (localAccessor.internalType == 112) {
/*  946 */                 localAccessor.prefetchedLobCharData = new char[this.rowPrefetch][];
/*      */               } else {
/*  948 */                 localAccessor.prefetchedLobData = new byte[this.rowPrefetch][];
/*      */               }
/*  950 */               localAccessor.prefetchedLobChunkSize = new int[this.rowPrefetch];
/*  951 */               localAccessor.prefetchedClobFormOfUse = new byte[this.rowPrefetch];
/*  952 */               localAccessor.prefetchedLobDataL = new int[this.rowPrefetch];
/*  953 */               localAccessor.prefetchedLobSize = new long[this.rowPrefetch];
/*      */             }
/*      */             
/*  956 */             int m = j * i;
/*  957 */             for (int n = 0; n < j; n++)
/*      */             {
/*  959 */               localAccessor.prefetchedLobChunkSize[n] = arrayOfInt1[k];
/*      */               
/*  961 */               localAccessor.prefetchedClobFormOfUse[n] = arrayOfByte[k];
/*      */               
/*      */ 
/*  964 */               localAccessor.prefetchedLobSize[n] = arrayOfLong[(m + n)];
/*      */               
/*      */ 
/*  967 */               localAccessor.prefetchedLobDataL[n] = 0;
/*  968 */               if ((arrayOfInt2[k] > 0) && (arrayOfLong[(m + n)] > 0L))
/*      */               {
/*      */ 
/*  971 */                 if (localAccessor.internalType == 112)
/*      */                 {
/*  973 */                   localAccessor.prefetchedLobCharData[n] = ((char[])(char[])arrayOfObject[(m + n)]);
/*      */                   
/*  975 */                   if (localAccessor.prefetchedLobCharData[n] != null) {
/*  976 */                     localAccessor.prefetchedLobDataL[n] = localAccessor.prefetchedLobCharData[n].length;
/*      */                   }
/*      */                 }
/*      */                 else
/*      */                 {
/*  981 */                   localAccessor.prefetchedLobData[n] = ((byte[])(byte[])arrayOfObject[(m + n)]);
/*      */                   
/*  983 */                   if (localAccessor.prefetchedLobData[n] != null) {
/*  984 */                     localAccessor.prefetchedLobDataL[n] = localAccessor.prefetchedLobData[n].length;
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*  989 */             i++;
/*      */           }
/*      */           
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   void doDefineFetch()
/*      */     throws SQLException
/*      */   {
/* 1002 */     if (!this.needToPrepareDefineBuffer) {
/* 1003 */       throw new Error("doDefineFetch called when needToPrepareDefineBuffer=false " + this.sqlObject.getSql(this.processEscapes, this.convertNcharLiterals));
/*      */     }
/*      */     
/* 1006 */     setupForDefine();
/*      */     
/* 1008 */     this.t2cOutput[2] = 0L;
/* 1009 */     this.t2cOutput[5] = (this.connection.useNio ? 1 : 0);
/* 1010 */     this.t2cOutput[6] = this.defaultLobPrefetchSize;
/* 1011 */     if (this.connection.useNio) {
/* 1012 */       resetNioAttributesBeforeFetch();
/* 1013 */       allocateNioBuffersIfRequired(this.defineChars == null ? 0 : this.defineChars.length, this.defineBytes == null ? 0 : this.defineBytes.length, this.defineIndicators == null ? 0 : this.defineIndicators.length);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1019 */     if (this.lobPrefetchMetaData == null) {
/* 1020 */       this.lobPrefetchMetaData = getLobPrefetchMetaData();
/*      */     }
/* 1022 */     this.validRows = T2CStatement.t2cDefineFetch(this, this.c_state, this.rowPrefetch, this.connection.queryMetaData1, this.connection.queryMetaData2, this.connection.queryMetaData1Offset, this.connection.queryMetaData2Offset, this.accessors, this.defineBytes, this.accessorByteOffset, this.defineChars, this.accessorCharOffset, this.defineIndicators, this.accessorShortOffset, this.t2cOutput, this.nioBuffers, this.lobPrefetchMetaData);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1035 */     if ((this.validRows == -1) || (this.validRows == -4)) {
/* 1036 */       this.connection.checkError(this.validRows);
/*      */     }
/*      */     
/* 1039 */     if (this.t2cOutput[2] != 0L)
/*      */     {
/* 1041 */       this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */     }
/*      */     
/*      */ 
/* 1045 */     if ((this.connection.useNio) && ((this.validRows > 0) || (this.validRows == -2)))
/*      */     {
/* 1047 */       extractNioDefineBuffers(0);
/*      */     }
/* 1049 */     if (this.lobPrefetchMetaData != null)
/*      */     {
/* 1051 */       processLobPrefetchMetaData(this.lobPrefetchMetaData);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void allocateNioBuffersIfRequired(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1061 */     if (this.nioBuffers == null) {
/* 1062 */       this.nioBuffers = new ByteBuffer[4];
/*      */     }
/* 1064 */     if (paramInt2 > 0)
/*      */     {
/* 1066 */       if ((this.nioBuffers[0] == null) || (this.nioBuffers[0].capacity() < paramInt2))
/*      */       {
/*      */ 
/* 1069 */         this.nioBuffers[0] = ByteBuffer.allocateDirect(paramInt2);
/* 1070 */       } else if (this.nioBuffers[0] != null)
/*      */       {
/* 1072 */         this.nioBuffers[0].rewind();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1079 */     paramInt1 *= 2;
/* 1080 */     if (paramInt1 > 0)
/*      */     {
/* 1082 */       if ((this.nioBuffers[1] == null) || (this.nioBuffers[1].capacity() < paramInt1))
/*      */       {
/*      */ 
/* 1085 */         this.nioBuffers[1] = ByteBuffer.allocateDirect(paramInt1);
/* 1086 */       } else if (this.nioBuffers[1] != null)
/*      */       {
/* 1088 */         this.nioBuffers[1].rewind();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1095 */     paramInt3 *= 2;
/* 1096 */     if (paramInt3 > 0)
/*      */     {
/* 1098 */       if ((this.nioBuffers[2] == null) || (this.nioBuffers[2].capacity() < paramInt3))
/*      */       {
/*      */ 
/* 1101 */         this.nioBuffers[2] = ByteBuffer.allocateDirect(paramInt3);
/* 1102 */       } else if (this.nioBuffers[2] != null)
/*      */       {
/* 1104 */         this.nioBuffers[2].rewind();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void doDefineExecuteFetch()
/*      */     throws SQLException
/*      */   {
/* 1113 */     short[] arrayOfShort = null;
/*      */     
/* 1115 */     if ((this.needToPrepareDefineBuffer) || (this.needToParse))
/*      */     {
/* 1117 */       setupForDefine();
/*      */       
/* 1119 */       arrayOfShort = this.connection.queryMetaData1;
/*      */     }
/*      */     
/* 1122 */     this.t2cOutput[0] = 0L;
/* 1123 */     this.t2cOutput[2] = 0L;
/*      */     
/* 1125 */     byte[] arrayOfByte = this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals);
/* 1126 */     this.t2cOutput[5] = (this.connection.useNio ? 1 : 0);
/* 1127 */     this.t2cOutput[6] = this.defaultLobPrefetchSize;
/* 1128 */     if (this.connection.useNio) {
/* 1129 */       resetNioAttributesBeforeFetch();
/* 1130 */       allocateNioBuffersIfRequired(this.defineChars == null ? 0 : this.defineChars.length, this.defineBytes == null ? 0 : this.defineBytes.length, this.defineIndicators == null ? 0 : this.defineIndicators.length);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1136 */     if (this.lobPrefetchMetaData == null) {
/* 1137 */       this.lobPrefetchMetaData = getLobPrefetchMetaData();
/*      */     }
/*      */     try {
/* 1140 */       this.validRows = T2CStatement.t2cDefineExecuteFetch(this, this.c_state, this.numberOfDefinePositions, this.numberOfBindPositions, this.numberOfBindRowsAllocated, this.firstRowInBatch, this.currentRowBindAccessors != null, this.needToParse, arrayOfByte, arrayOfByte.length, T2CStatement.convertSqlKindEnumToByte(this.sqlKind), this.rowPrefetch, this.batch, this.bindIndicators, this.bindIndicatorOffset, this.bindBytes, this.bindChars, this.bindByteOffset, this.bindCharOffset, arrayOfShort, this.connection.queryMetaData2, this.connection.queryMetaData1Offset, this.connection.queryMetaData2Offset, this.preparedAllBinds, this.preparedCharBinds, this.outBindAccessors, this.parameterDatum, this.t2cOutput, this.defineBytes, this.accessorByteOffset, this.defineChars, this.accessorCharOffset, this.defineIndicators, this.accessorShortOffset, this.nioBuffers, this.lobPrefetchMetaData);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1180 */       this.validRows = 0;
/*      */       
/* 1182 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1183 */       localSQLException.fillInStackTrace();
/* 1184 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1188 */     if (this.validRows == -1) {
/* 1189 */       this.connection.checkError(this.validRows);
/*      */     }
/* 1191 */     if (this.t2cOutput[2] != 0L) {
/* 1192 */       this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1197 */     this.connection.endToEndECIDSequenceNumber = ((short)(int)this.t2cOutput[4]);
/*      */     
/* 1199 */     if ((this.connection.useNio) && ((this.validRows > 0) || (this.validRows == -2)))
/*      */     {
/* 1201 */       extractNioDefineBuffers(0);
/*      */     }
/* 1203 */     if (this.lobPrefetchMetaData != null)
/*      */     {
/* 1205 */       processLobPrefetchMetaData(this.lobPrefetchMetaData);
/*      */     }
/*      */     
/* 1208 */     this.needToParse = false;
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
/*      */   void fetch()
/*      */     throws SQLException
/*      */   {
/* 1251 */     if (this.numberOfDefinePositions > 0)
/*      */     {
/* 1253 */       if (this.needToPrepareDefineBuffer) {
/* 1254 */         doDefineFetch();
/*      */       }
/*      */       else {
/* 1257 */         this.t2cOutput[2] = 0L;
/* 1258 */         this.t2cOutput[5] = (this.connection.useNio ? 1 : 0);
/* 1259 */         this.t2cOutput[6] = this.defaultLobPrefetchSize;
/* 1260 */         if (this.connection.useNio) {
/* 1261 */           resetNioAttributesBeforeFetch();
/* 1262 */           allocateNioBuffersIfRequired(this.defineChars == null ? 0 : this.defineChars.length, this.defineBytes == null ? 0 : this.defineBytes.length, this.defineIndicators == null ? 0 : this.defineIndicators.length);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1269 */         if (this.lobPrefetchMetaData == null) {
/* 1270 */           this.lobPrefetchMetaData = getLobPrefetchMetaData();
/*      */         }
/* 1272 */         this.validRows = T2CStatement.t2cFetch(this.c_state, this.needToPrepareDefineBuffer, this.rowPrefetch, this.accessors, this.defineBytes, this.accessorByteOffset, this.defineChars, this.accessorCharOffset, this.defineIndicators, this.accessorShortOffset, this.t2cOutput, this.nioBuffers, this.lobPrefetchMetaData);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1280 */         if ((this.validRows == -1) || (this.validRows == -4)) {
/* 1281 */           this.connection.checkError(this.validRows);
/*      */         }
/* 1283 */         if (this.t2cOutput[2] != 0L)
/*      */         {
/* 1285 */           this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */         }
/*      */         
/* 1288 */         if (this.lobPrefetchMetaData != null)
/*      */         {
/* 1290 */           processLobPrefetchMetaData(this.lobPrefetchMetaData);
/*      */         }
/* 1292 */         if ((this.connection.useNio) && ((this.validRows > 0) || (this.validRows == -2)))
/*      */         {
/* 1294 */           extractNioDefineBuffers(0);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void resetNioAttributesBeforeFetch()
/*      */   {
/* 1303 */     this.extractedCharOffset = 0;
/* 1304 */     this.extractedByteOffset = 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void extractNioDefineBuffers(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1313 */     if ((this.accessors == null) || (this.defineIndicators == null) || (paramInt == this.numberOfDefinePositions))
/*      */     {
/*      */ 
/* 1316 */       return;
/*      */     }
/* 1318 */     int i = 0;
/* 1319 */     int j = 0;
/* 1320 */     int k = 0;
/* 1321 */     int m = 0;
/* 1322 */     int n = 0;
/*      */     
/*      */ 
/* 1325 */     if (!this.hasStream)
/*      */     {
/* 1327 */       i = this.defineBytes != null ? this.defineBytes.length : 0;
/* 1328 */       j = this.defineChars != null ? this.defineChars.length : 0;
/* 1329 */       k = this.defineIndicators.length;
/*      */     }
/*      */     else
/*      */     {
/* 1333 */       if (this.numberOfDefinePositions > paramInt)
/*      */       {
/* 1335 */         n = this.accessors[paramInt].indicatorIndex;
/* 1336 */         m = this.accessors[paramInt].lengthIndex;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1341 */       for (int i1 = paramInt; i1 < this.numberOfDefinePositions; i1++)
/*      */       {
/* 1343 */         switch (this.accessors[i1].internalType)
/*      */         {
/*      */         case 8: 
/*      */         case 24: 
/*      */           break;
/*      */         default: 
/* 1349 */           i += this.accessors[i1].byteLength;
/* 1350 */           j += this.accessors[i1].charLength;
/* 1351 */           k++;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/* 1356 */     ByteBuffer localByteBuffer = this.nioBuffers[0];
/* 1357 */     if ((localByteBuffer != null) && (this.defineBytes != null))
/*      */     {
/* 1359 */       if (i > 0)
/*      */       {
/* 1361 */         localByteBuffer.position(this.extractedByteOffset);
/* 1362 */         localByteBuffer.get(this.defineBytes, this.extractedByteOffset, i);
/* 1363 */         this.extractedByteOffset += i;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     Object localObject;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1378 */     if ((this.nioBuffers[1] != null) && (this.defineChars != null))
/*      */     {
/* 1380 */       localByteBuffer = this.nioBuffers[1].order(ByteOrder.LITTLE_ENDIAN);
/* 1381 */       localObject = localByteBuffer.asCharBuffer();
/*      */       
/* 1383 */       if (j > 0)
/*      */       {
/* 1385 */         ((CharBuffer)localObject).position(this.extractedCharOffset);
/* 1386 */         ((CharBuffer)localObject).get(this.defineChars, this.extractedCharOffset, j);
/* 1387 */         this.extractedCharOffset += j;
/*      */       }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1406 */     if (this.nioBuffers[2] != null) {
/* 1407 */       localByteBuffer = this.nioBuffers[2].order(ByteOrder.LITTLE_ENDIAN);
/* 1408 */       localObject = localByteBuffer.asShortBuffer();
/* 1409 */       if (this.hasStream)
/*      */       {
/* 1411 */         if (k > 0)
/*      */         {
/* 1413 */           ((ShortBuffer)localObject).position(n);
/* 1414 */           ((ShortBuffer)localObject).get(this.defineIndicators, n, k);
/* 1415 */           ((ShortBuffer)localObject).position(m);
/* 1416 */           ((ShortBuffer)localObject).get(this.defineIndicators, m, k);
/*      */         }
/*      */       }
/*      */       else {
/* 1420 */         ((ShortBuffer)localObject).get(this.defineIndicators);
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
/*      */   void doClose()
/*      */     throws SQLException
/*      */   {
/* 1468 */     if (this.defineBytes != null)
/*      */     {
/* 1470 */       this.defineBytes = null;
/* 1471 */       this.accessorByteOffset = 0;
/*      */     }
/*      */     
/* 1474 */     if (this.defineChars != null)
/*      */     {
/* 1476 */       this.defineChars = null;
/* 1477 */       this.accessorCharOffset = 0;
/*      */     }
/*      */     
/* 1480 */     if (this.defineIndicators != null)
/*      */     {
/* 1482 */       this.defineIndicators = null;
/* 1483 */       this.accessorShortOffset = 0;
/*      */     }
/*      */     
/*      */ 
/* 1487 */     int i = T2CStatement.t2cCloseStatement(this.c_state);
/*      */     
/* 1489 */     this.nioBuffers = null;
/*      */     
/* 1491 */     if (i != 0) {
/* 1492 */       this.connection.checkError(i);
/*      */     }
/* 1494 */     this.t2cOutput = null;
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
/*      */   void closeQuery()
/*      */     throws SQLException
/*      */   {
/* 1512 */     if (this.streamList != null)
/*      */     {
/* 1514 */       while (this.nextStream != null)
/*      */       {
/*      */         try
/*      */         {
/* 1518 */           this.nextStream.close();
/*      */ 
/*      */         }
/*      */         catch (IOException localIOException)
/*      */         {
/* 1523 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1524 */           localSQLException.fillInStackTrace();
/* 1525 */           throw localSQLException;
/*      */         }
/*      */         
/*      */ 
/* 1529 */         this.nextStream = this.nextStream.nextStream;
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
/*      */   Accessor allocateAccessor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, short paramShort, String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1546 */     if ((paramInt1 == 116) || (paramInt1 == 102))
/*      */     {
/*      */ 
/* 1549 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 1551 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 1552 */         localSQLException.fillInStackTrace();
/* 1553 */         throw localSQLException;
/*      */       }
/*      */       
/* 1556 */       T2CResultSetAccessor localT2CResultSetAccessor = new T2CResultSetAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */       
/*      */ 
/* 1559 */       return localT2CResultSetAccessor;
/*      */     }
/*      */     
/* 1562 */     return super.allocateAccessor(paramInt1, paramInt2, paramInt3, paramInt4, paramShort, paramString, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */   void closeUsedStreams(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/* 1570 */     while ((this.nextStream != null) && (this.nextStream.columnIndex < paramInt))
/*      */     {
/*      */ 
/*      */       try
/*      */       {
/*      */ 
/* 1576 */         this.nextStream.close();
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException1)
/*      */       {
/* 1581 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException1);
/* 1582 */         localSQLException.fillInStackTrace();
/* 1583 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1587 */       this.nextStream = this.nextStream.nextStream;
/*      */     }
/*      */     
/* 1590 */     if (this.nextStream != null) {
/*      */       try
/*      */       {
/* 1593 */         this.nextStream.needBytes();
/*      */       }
/*      */       catch (IOException localIOException2)
/*      */       {
/* 1597 */         interalCloseOnIOException(localIOException2);
/*      */         
/* 1599 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException2);
/* 1600 */         localSQLException.fillInStackTrace();
/* 1601 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void interalCloseOnIOException(IOException paramIOException)
/*      */     throws SQLException
/*      */   {
/* 1610 */     this.closed = true;
/*      */     
/* 1612 */     if (this.currentResultSet != null) {
/* 1613 */       this.currentResultSet.closed = true;
/*      */     }
/* 1615 */     doClose();
/*      */   }
/*      */   
/*      */ 
/*      */   void fetchDmlReturnParams()
/*      */     throws SQLException
/*      */   {
/* 1622 */     this.rowsDmlReturned = T2CStatement.t2cGetRowsDmlReturned(this.c_state);
/*      */     
/* 1624 */     if (this.rowsDmlReturned != 0)
/*      */     {
/* 1626 */       allocateDmlReturnStorage();
/*      */       
/* 1628 */       int i = T2CStatement.t2cFetchDmlReturnParams(this.c_state, this.returnParamAccessors, this.returnParamBytes, this.returnParamChars, this.returnParamIndicators);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1636 */       if ((i == -1) || (i == -4)) {
/* 1637 */         this.connection.checkError(i);
/*      */       }
/*      */       
/* 1640 */       if (this.t2cOutput[2] != 0L)
/*      */       {
/* 1642 */         this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */       }
/*      */       
/*      */ 
/* 1646 */       if ((this.connection.useNio) && ((i > 0) || (i == -2)))
/*      */       {
/* 1648 */         extractNioDefineBuffers(0);
/*      */       }
/*      */     }
/* 1651 */     this.returnParamsFetched = true;
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
/* 1662 */   static int PREAMBLE_PER_POSITION = 5;
/*      */   
/*      */ 
/*      */ 
/*      */   void initializeIndicatorSubRange()
/*      */   {
/* 1668 */     this.bindIndicatorSubRange = (this.numberOfBindPositions * PREAMBLE_PER_POSITION);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   int calculateIndicatorSubRangeSize()
/*      */   {
/* 1675 */     return this.numberOfBindPositions * PREAMBLE_PER_POSITION;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   short getInoutIndicator(int paramInt)
/*      */   {
/* 1682 */     return this.bindIndicators[(paramInt * PREAMBLE_PER_POSITION)];
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
/*      */   void prepareBindPreambles(int paramInt1, int paramInt2)
/*      */   {
/* 1695 */     int i = calculateIndicatorSubRangeSize();
/* 1696 */     int j = this.bindIndicatorSubRange - i;
/* 1697 */     OracleTypeADT[] arrayOfOracleTypeADT = this.parameterOtype == null ? null : this.parameterOtype[this.firstRowInBatch];
/*      */     
/*      */ 
/*      */ 
/* 1701 */     for (int k = 0; k < this.numberOfBindPositions; k++)
/*      */     {
/*      */ 
/* 1704 */       Binder localBinder = this.lastBinders[k];
/*      */       OracleTypeADT localOracleTypeADT;
/*      */       short s;
/* 1707 */       if (localBinder == this.theReturnParamBinder)
/*      */       {
/* 1709 */         localOracleTypeADT = (OracleTypeADT)this.returnParamAccessors[k].internalOtype;
/* 1710 */         s = 0;
/*      */       }
/*      */       else
/*      */       {
/* 1714 */         localOracleTypeADT = arrayOfOracleTypeADT == null ? null : arrayOfOracleTypeADT[k];
/*      */         
/* 1716 */         if (this.outBindAccessors == null) {
/* 1717 */           s = 0;
/*      */         }
/*      */         else {
/* 1720 */           Accessor localAccessor = this.outBindAccessors[k];
/*      */           
/* 1722 */           if (localAccessor == null) {
/* 1723 */             s = 0;
/* 1724 */           } else if (localBinder == this.theOutBinder)
/*      */           {
/* 1726 */             s = 1;
/*      */             
/* 1728 */             if (localOracleTypeADT == null) {
/* 1729 */               localOracleTypeADT = (OracleTypeADT)localAccessor.internalOtype;
/*      */             }
/*      */           } else {
/* 1732 */             s = 2;
/*      */           } }
/* 1734 */         s = localBinder.updateInoutIndicatorValue(s);
/*      */       }
/*      */       
/* 1737 */       this.bindIndicators[(j++)] = s;
/*      */       
/* 1739 */       if (localOracleTypeADT != null)
/*      */       {
/* 1741 */         long l = localOracleTypeADT.getTdoCState();
/*      */         
/* 1743 */         this.bindIndicators[(j + 0)] = ((short)(int)(l >> 48 & 0xFFFF));
/*      */         
/* 1745 */         this.bindIndicators[(j + 1)] = ((short)(int)(l >> 32 & 0xFFFF));
/*      */         
/* 1747 */         this.bindIndicators[(j + 2)] = ((short)(int)(l >> 16 & 0xFFFF));
/*      */         
/* 1749 */         this.bindIndicators[(j + 3)] = ((short)(int)(l & 0xFFFF));
/*      */       }
/*      */       
/* 1752 */       j += 4;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void releaseBuffers()
/*      */   {
/* 1760 */     super.releaseBuffers();
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
/*      */   void doDescribe(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1779 */     if (this.closed)
/*      */     {
/* 1781 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1782 */       localSQLException.fillInStackTrace();
/* 1783 */       throw localSQLException;
/*      */     }
/*      */     
/* 1786 */     if (this.described == true)
/*      */     {
/* 1788 */       return;
/*      */     }
/*      */     
/* 1791 */     if (!this.isOpen)
/*      */     {
/*      */ 
/*      */ 
/* 1795 */       this.connection.open(this);
/* 1796 */       this.isOpen = true;
/*      */     }
/*      */     
/*      */ 
/*      */     int i;
/*      */     
/*      */     do
/*      */     {
/* 1804 */       i = 0;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1813 */       boolean bool = (this.sqlKind.isSELECT()) && (this.needToParse) && ((!this.described) || (!this.serverCursor));
/* 1814 */       byte[] arrayOfByte = bool ? this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals) : PhysicalConnection.EMPTY_BYTE_ARRAY;
/* 1815 */       this.numberOfDefinePositions = T2CStatement.t2cDescribe(this.c_state, this.connection.queryMetaData1, this.connection.queryMetaData2, this.connection.queryMetaData1Offset, this.connection.queryMetaData2Offset, this.connection.queryMetaData1Size, this.connection.queryMetaData2Size, arrayOfByte, arrayOfByte.length, bool);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1826 */       if (!this.described) {
/* 1827 */         this.described = true;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1834 */       if (this.numberOfDefinePositions == -1)
/*      */       {
/* 1836 */         this.connection.checkError(this.numberOfDefinePositions);
/*      */       }
/*      */       
/*      */ 
/* 1840 */       if (this.numberOfDefinePositions == T2C_EXTEND_BUFFER)
/*      */       {
/* 1842 */         i = 1;
/*      */         
/*      */ 
/*      */ 
/* 1846 */         this.connection.reallocateQueryMetaData(this.connection.queryMetaData1Size * 2, this.connection.queryMetaData2Size * 2);
/*      */       }
/*      */       
/*      */     }
/* 1850 */     while (i != 0);
/*      */     
/* 1852 */     processDescribeData();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void registerOutParameterInternal(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString)
/*      */     throws SQLException
/*      */   {
/* 1861 */     int i = paramInt1 - 1;
/*      */     
/* 1863 */     if ((i < 0) || (paramInt1 > this.numberOfBindPositions))
/*      */     {
/* 1865 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 1866 */       localSQLException1.fillInStackTrace();
/* 1867 */       throw localSQLException1;
/*      */     }
/*      */     
/* 1870 */     int j = getInternalType(paramInt2);
/*      */     
/* 1872 */     if (j == 995)
/*      */     {
/* 1874 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 1875 */       localSQLException2.fillInStackTrace();
/* 1876 */       throw localSQLException2;
/*      */     }
/*      */     
/* 1879 */     resetBatch();
/*      */     
/* 1881 */     this.currentRowNeedToPrepareBinds = true;
/*      */     
/* 1883 */     if (this.currentRowBindAccessors == null) {
/* 1884 */       this.currentRowBindAccessors = new Accessor[this.numberOfBindPositions];
/*      */     }
/*      */     
/* 1887 */     switch (paramInt2)
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
/* 1903 */       this.currentRowFormOfUse[i] = 2;
/* 1904 */       break;
/*      */     case 2011: 
/* 1906 */       paramInt4 = 0;
/* 1907 */       this.currentRowFormOfUse[i] = 2;
/* 1908 */       break;
/*      */     case 2009: 
/* 1910 */       paramInt4 = 0;
/* 1911 */       paramString = "SYS.XMLTYPE";
/* 1912 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     default: 
/* 1917 */       paramInt4 = 0;
/*      */     }
/*      */     
/*      */     
/* 1921 */     this.currentRowBindAccessors[i] = allocateAccessor(j, paramInt2, paramInt1, paramInt4, this.currentRowFormOfUse[i], paramString, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 1927 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T2CCallableStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */