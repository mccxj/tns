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
/*      */ class T2CPreparedStatement
/*      */   extends OraclePreparedStatement
/*      */ {
/*   40 */   T2CConnection connection = null;
/*   41 */   int userResultSetType = -1;
/*   42 */   int userResultSetConcur = -1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   48 */   static int T2C_EXTEND_BUFFER = -3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   63 */   long[] t2cOutput = new long[10];
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
/*      */   T2CPreparedStatement(T2CConnection paramT2CConnection, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/*  102 */     super(paramT2CConnection, paramString, paramInt1, paramInt2, paramInt3, paramInt4);
/*      */     
/*      */ 
/*  105 */     this.userResultSetType = paramInt3;
/*  106 */     this.userResultSetConcur = paramInt4;
/*      */     
/*      */ 
/*  109 */     this.connection = paramT2CConnection;
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
/*  125 */     byte[] arrayOfByte = new byte[paramInt2];
/*      */     
/*  127 */     System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
/*      */     
/*  129 */     return this.connection.conversion.CharBytesToString(arrayOfByte, paramInt2);
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
/*  145 */     this.described = true;
/*  146 */     this.describedWithNames = true;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  157 */     if ((this.accessors == null) || (this.numberOfDefinePositions > this.accessors.length)) {
/*  158 */       this.accessors = new Accessor[this.numberOfDefinePositions];
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
/*  174 */     int i = this.connection.queryMetaData1Offset;
/*  175 */     int j = this.connection.queryMetaData2Offset;
/*  176 */     short[] arrayOfShort = this.connection.queryMetaData1;
/*  177 */     byte[] arrayOfByte = this.connection.queryMetaData2;
/*      */     
/*  179 */     for (int k = 0; k < this.numberOfDefinePositions; 
/*  180 */         i += 13)
/*      */     {
/*  182 */       int m = arrayOfShort[(i + 0)];
/*  183 */       int n = arrayOfShort[(i + 1)];
/*  184 */       int i1 = arrayOfShort[(i + 11)];
/*  185 */       boolean bool = arrayOfShort[(i + 2)] != 0;
/*  186 */       int i2 = arrayOfShort[(i + 3)];
/*  187 */       int i3 = arrayOfShort[(i + 4)];
/*  188 */       int i4 = 0;
/*  189 */       int i5 = 0;
/*  190 */       int i6 = 0;
/*  191 */       short s = arrayOfShort[(i + 5)];
/*  192 */       int i7 = arrayOfShort[(i + 6)];
/*  193 */       String str1 = bytes2String(arrayOfByte, j, i7);
/*  194 */       int i8 = arrayOfShort[(i + 12)];
/*  195 */       String str2 = null;
/*  196 */       OracleTypeADT localOracleTypeADT = null;
/*      */       
/*  198 */       j += i7;
/*      */       
/*  200 */       if (i8 > 0)
/*      */       {
/*  202 */         str2 = bytes2String(arrayOfByte, j, i8);
/*  203 */         j += i8;
/*  204 */         localOracleTypeADT = new OracleTypeADT(str2, this.connection);
/*  205 */         localOracleTypeADT.tdoCState = ((arrayOfShort[(i + 7)] & 0xFFFF) << 48 | (arrayOfShort[(i + 8)] & 0xFFFF) << 32 | (arrayOfShort[(i + 9)] & 0xFFFF) << 16 | arrayOfShort[(i + 10)] & 0xFFFF);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  213 */       Object localObject = this.accessors[k];
/*      */       
/*  215 */       if ((localObject != null) && (!((Accessor)localObject).useForDescribeIfPossible(m, n, bool, i4, i2, i3, i5, i6, s, str2)))
/*      */       {
/*      */ 
/*      */ 
/*  219 */         localObject = null;
/*      */       }
/*      */       
/*  222 */       if (localObject == null)
/*      */       {
/*  224 */         switch (m)
/*      */         {
/*      */ 
/*      */         case 1: 
/*  228 */           localObject = new VarcharAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  232 */           if (i1 > 0) {
/*  233 */             ((Accessor)localObject).setDisplaySize(i1);
/*      */           }
/*      */           
/*      */           break;
/*      */         case 96: 
/*  238 */           localObject = new CharAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  242 */           if (i1 > 0) {
/*  243 */             ((Accessor)localObject).setDisplaySize(i1);
/*      */           }
/*      */           
/*      */           break;
/*      */         case 2: 
/*  248 */           localObject = new NumberAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  252 */           break;
/*      */         
/*      */         case 23: 
/*  255 */           localObject = new RawAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  259 */           break;
/*      */         
/*      */         case 100: 
/*  262 */           localObject = new BinaryFloatAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  266 */           break;
/*      */         
/*      */         case 101: 
/*  269 */           localObject = new BinaryDoubleAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  273 */           break;
/*      */         
/*      */         case 8: 
/*  276 */           localObject = new LongAccessor(this, k + 1, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  283 */           this.rowPrefetch = 1;
/*      */           
/*  285 */           break;
/*      */         
/*      */         case 24: 
/*  288 */           localObject = new LongRawAccessor(this, k + 1, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  295 */           this.rowPrefetch = 1;
/*      */           
/*  297 */           break;
/*      */         
/*      */         case 104: 
/*  300 */           localObject = new RowidAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  304 */           break;
/*      */         
/*      */ 
/*      */         case 102: 
/*      */         case 116: 
/*  309 */           localObject = new T2CResultSetAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  313 */           break;
/*      */         
/*      */         case 12: 
/*  316 */           localObject = new DateAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  320 */           break;
/*      */         
/*      */         case 180: 
/*  323 */           localObject = new TimestampAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  327 */           break;
/*      */         
/*      */         case 181: 
/*  330 */           localObject = new TimestamptzAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  334 */           break;
/*      */         
/*      */         case 231: 
/*  337 */           localObject = new TimestampltzAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  341 */           break;
/*      */         
/*      */         case 182: 
/*  344 */           localObject = new IntervalymAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  348 */           break;
/*      */         
/*      */         case 183: 
/*  351 */           localObject = new IntervaldsAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  355 */           break;
/*      */         
/*      */         case 112: 
/*  358 */           localObject = new ClobAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  362 */           break;
/*      */         
/*      */         case 113: 
/*  365 */           localObject = new BlobAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  369 */           break;
/*      */         
/*      */         case 114: 
/*  372 */           localObject = new BfileAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  376 */           break;
/*      */         
/*      */         case 109: 
/*  379 */           localObject = new NamedTypeAccessor(this, n, bool, i4, i2, i3, i5, i6, s, str2, localOracleTypeADT);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  384 */           break;
/*      */         
/*      */         case 111: 
/*  387 */           localObject = new RefTypeAccessor(this, n, bool, i4, i2, i3, i5, i6, s, str2, localOracleTypeADT);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  392 */           break;
/*      */         
/*      */ 
/*      */         default: 
/*  396 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Unknown or unimplemented accessor type: " + m);
/*      */           
/*  398 */           localSQLException.fillInStackTrace();
/*  399 */           throw localSQLException;
/*      */         }
/*      */         
/*      */         
/*      */ 
/*  404 */         this.accessors[k] = localObject;
/*      */       }
/*  406 */       else if (localOracleTypeADT != null)
/*      */       {
/*      */ 
/*      */ 
/*  410 */         ((Accessor)localObject).describeOtype = localOracleTypeADT;
/*  411 */         ((Accessor)localObject).initMetadata();
/*      */       }
/*      */       
/*  414 */       ((Accessor)localObject).columnName = str1;k++;
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
/*  455 */     this.t2cOutput[0] = 0L;
/*  456 */     this.t2cOutput[2] = 0L;
/*      */     
/*      */ 
/*      */ 
/*  460 */     this.lobPrefetchMetaData = null;
/*      */     
/*  462 */     boolean bool1 = !this.described;
/*  463 */     boolean bool2 = false;
/*      */     
/*      */     int i;
/*      */     do
/*      */     {
/*  468 */       i = 0;
/*      */       
/*      */ 
/*  471 */       if (this.connection.endToEndAnyChanged)
/*      */       {
/*  473 */         pushEndToEndValues();
/*      */         
/*  475 */         this.connection.endToEndAnyChanged = false;
/*      */       }
/*      */       
/*      */ 
/*  479 */       byte[] arrayOfByte = this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals);
/*      */       
/*  481 */       int j = 0;
/*      */       try
/*      */       {
/*  484 */         j = T2CStatement.t2cParseExecuteDescribe(this, this.c_state, this.numberOfBindPositions, this.numberOfBindRowsAllocated, this.firstRowInBatch, false, this.needToParse, bool1, bool2, arrayOfByte, arrayOfByte.length, T2CStatement.convertSqlKindEnumToByte(this.sqlKind), this.rowPrefetch, this.batch, this.bindIndicators, this.bindIndicatorOffset, this.bindBytes, this.bindChars, this.bindByteOffset, this.bindCharOffset, this.ibtBindIndicators, this.ibtBindIndicatorOffset, this.ibtBindIndicatorSize, this.ibtBindBytes, this.ibtBindChars, this.ibtBindByteOffset, this.ibtBindCharOffset, this.returnParamMeta, this.connection.queryMetaData1, this.connection.queryMetaData2, this.connection.queryMetaData1Offset, this.connection.queryMetaData2Offset, this.connection.queryMetaData1Size, this.connection.queryMetaData2Size, this.preparedAllBinds, this.preparedCharBinds, this.accessors, this.parameterDatum, this.t2cOutput, this.defineBytes, this.accessorByteOffset, this.defineChars, this.accessorCharOffset, this.defineIndicators, this.accessorShortOffset, this.connection.plsqlCompilerWarnings);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  534 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 266);
/*  535 */         localSQLException.fillInStackTrace();
/*  536 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  541 */       this.validRows = ((int)this.t2cOutput[1]);
/*      */       
/*      */ 
/*  544 */       if ((j == -1) || (j == -4))
/*      */       {
/*  546 */         this.connection.checkError(j);
/*      */       }
/*  548 */       else if (j == T2C_EXTEND_BUFFER)
/*      */       {
/*  550 */         j = this.connection.queryMetaData1Size * 2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  555 */       if (this.t2cOutput[3] != 0L)
/*      */       {
/*  557 */         foundPlsqlCompilerWarning();
/*      */       }
/*  559 */       else if (this.t2cOutput[2] != 0L)
/*      */       {
/*  561 */         this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  566 */       this.connection.endToEndECIDSequenceNumber = ((short)(int)this.t2cOutput[4]);
/*      */       
/*      */ 
/*  569 */       this.needToParse = false;
/*  570 */       bool2 = true;
/*      */       
/*  572 */       if (this.sqlKind.isSELECT())
/*      */       {
/*  574 */         this.numberOfDefinePositions = j;
/*      */         
/*  576 */         if (this.numberOfDefinePositions > this.connection.queryMetaData1Size)
/*      */         {
/*  578 */           i = 1;
/*  579 */           bool2 = true;
/*      */           
/*      */ 
/*  582 */           this.connection.reallocateQueryMetaData(this.numberOfDefinePositions, this.numberOfDefinePositions * 8);
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  589 */         this.numberOfDefinePositions = 0;
/*  590 */         this.validRows = j;
/*      */       }
/*      */       
/*  593 */     } while (i != 0);
/*      */     
/*  595 */     processDescribeData();
/*      */   }
/*      */   
/*      */ 
/*      */   void pushEndToEndValues()
/*      */     throws SQLException
/*      */   {
/*  602 */     T2CConnection localT2CConnection = this.connection;
/*  603 */     byte[] arrayOfByte1 = new byte[0];
/*  604 */     byte[] arrayOfByte2 = new byte[0];
/*  605 */     byte[] arrayOfByte3 = new byte[0];
/*  606 */     byte[] arrayOfByte4 = new byte[0];
/*      */     
/*  608 */     if (localT2CConnection.endToEndValues != null) {
/*      */       String str;
/*  610 */       if (localT2CConnection.endToEndHasChanged[0] != 0)
/*      */       {
/*  612 */         str = localT2CConnection.endToEndValues[0];
/*      */         
/*  614 */         if (str != null) {
/*  615 */           arrayOfByte1 = DBConversion.stringToDriverCharBytes(str, localT2CConnection.m_clientCharacterSet);
/*      */         }
/*      */         
/*  618 */         localT2CConnection.endToEndHasChanged[0] = false;
/*      */       }
/*      */       
/*  621 */       if (localT2CConnection.endToEndHasChanged[1] != 0)
/*      */       {
/*  623 */         str = localT2CConnection.endToEndValues[1];
/*      */         
/*  625 */         if (str != null) {
/*  626 */           arrayOfByte2 = DBConversion.stringToDriverCharBytes(str, localT2CConnection.m_clientCharacterSet);
/*      */         }
/*      */         
/*  629 */         localT2CConnection.endToEndHasChanged[1] = false;
/*      */       }
/*      */       
/*  632 */       if (localT2CConnection.endToEndHasChanged[2] != 0)
/*      */       {
/*  634 */         str = localT2CConnection.endToEndValues[2];
/*      */         
/*  636 */         if (str != null) {
/*  637 */           arrayOfByte3 = DBConversion.stringToDriverCharBytes(str, localT2CConnection.m_clientCharacterSet);
/*      */         }
/*      */         
/*  640 */         localT2CConnection.endToEndHasChanged[2] = false;
/*      */       }
/*      */       
/*  643 */       if (localT2CConnection.endToEndHasChanged[3] != 0)
/*      */       {
/*  645 */         str = localT2CConnection.endToEndValues[3];
/*      */         
/*  647 */         if (str != null) {
/*  648 */           arrayOfByte4 = DBConversion.stringToDriverCharBytes(str, localT2CConnection.m_clientCharacterSet);
/*      */         }
/*      */         
/*  651 */         localT2CConnection.endToEndHasChanged[3] = false;
/*      */       }
/*      */       
/*  654 */       T2CStatement.t2cEndToEndUpdate(this.c_state, arrayOfByte1, arrayOfByte1.length, arrayOfByte2, arrayOfByte2.length, arrayOfByte3, arrayOfByte3.length, arrayOfByte4, arrayOfByte4.length, localT2CConnection.endToEndECIDSequenceNumber);
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
/*  707 */     if (this.connection.endToEndAnyChanged)
/*      */     {
/*  709 */       pushEndToEndValues();
/*      */       
/*  711 */       this.connection.endToEndAnyChanged = false;
/*      */     }
/*      */     
/*      */ 
/*  715 */     if (!paramBoolean)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  723 */       if (this.numberOfDefinePositions > 0)
/*      */       {
/*  725 */         doDefineExecuteFetch();
/*      */       }
/*      */       else
/*      */       {
/*  729 */         executeForDescribe();
/*      */       }
/*      */     }
/*  732 */     else if (this.numberOfDefinePositions > 0) {
/*  733 */       doDefineFetch();
/*      */     }
/*      */     
/*      */ 
/*  737 */     this.needToPrepareDefineBuffer = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void setupForDefine()
/*      */     throws SQLException
/*      */   {
/*  747 */     if (this.numberOfDefinePositions > this.connection.queryMetaData1Size)
/*      */     {
/*  749 */       int i = this.numberOfDefinePositions / 100 + 1;
/*      */       
/*  751 */       this.connection.reallocateQueryMetaData(this.connection.queryMetaData1Size * i, this.connection.queryMetaData2Size * i * 8);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  756 */     short[] arrayOfShort = this.connection.queryMetaData1;
/*  757 */     int j = this.connection.queryMetaData1Offset;
/*      */     
/*      */ 
/*  760 */     for (int k = 0; k < this.numberOfDefinePositions; 
/*  761 */         j += 13)
/*      */     {
/*  763 */       Accessor localAccessor = this.accessors[k];
/*      */       
/*  765 */       if (localAccessor == null)
/*      */       {
/*  767 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  768 */         localSQLException.fillInStackTrace();
/*  769 */         throw localSQLException;
/*      */       }
/*      */       
/*  772 */       arrayOfShort[(j + 0)] = ((short)localAccessor.defineType);
/*      */       
/*  774 */       arrayOfShort[(j + 11)] = ((short)localAccessor.charLength);
/*      */       
/*  776 */       arrayOfShort[(j + 1)] = ((short)localAccessor.byteLength);
/*      */       
/*  778 */       arrayOfShort[(j + 5)] = localAccessor.formOfUse;
/*      */       
/*      */ 
/*  781 */       if (localAccessor.internalOtype != null)
/*      */       {
/*  783 */         long l = ((OracleTypeADT)localAccessor.internalOtype).getTdoCState();
/*      */         
/*      */ 
/*  786 */         arrayOfShort[(j + 7)] = ((short)(int)((l & 0xFFFF000000000000) >> 48));
/*      */         
/*  788 */         arrayOfShort[(j + 8)] = ((short)(int)((l & 0xFFFF00000000) >> 32));
/*      */         
/*  790 */         arrayOfShort[(j + 9)] = ((short)(int)((l & 0xFFFF0000) >> 16));
/*      */         
/*  792 */         arrayOfShort[(j + 10)] = ((short)(int)(l & 0xFFFF));
/*      */       }
/*      */       
/*      */ 
/*  796 */       switch (localAccessor.internalType)
/*      */       {
/*      */ 
/*      */ 
/*      */       case 112: 
/*      */       case 113: 
/*  802 */         if (localAccessor.lobPrefetchSizeForThisColumn == -1) {
/*  803 */           localAccessor.lobPrefetchSizeForThisColumn = this.defaultLobPrefetchSize;
/*      */         }
/*      */         
/*  806 */         arrayOfShort[(j + 7)] = ((short)localAccessor.lobPrefetchSizeForThisColumn);
/*      */       }
/*      */       
/*  761 */       k++;
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
/*  826 */     Object[] arrayOfObject = null;
/*  827 */     Object localObject = null;
/*  828 */     int[] arrayOfInt = null;
/*  829 */     int i = 0;
/*  830 */     int j = 0;
/*      */     int k;
/*  832 */     if (this.accessors != null) {
/*  833 */       for (k = 0; k < this.numberOfDefinePositions; k++)
/*      */       {
/*  835 */         switch (this.accessors[k].internalType)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */         case 8: 
/*      */         case 24: 
/*  842 */           j = k;
/*  843 */           break;
/*      */         
/*      */ 
/*      */ 
/*      */         case 112: 
/*      */         case 113: 
/*  849 */           if (arrayOfInt == null)
/*      */           {
/*  851 */             arrayOfInt = new int[this.accessors.length];
/*      */           }
/*      */           
/*  854 */           if (this.accessors[k].lobPrefetchSizeForThisColumn != -1)
/*      */           {
/*  856 */             i++;
/*      */             
/*  858 */             arrayOfInt[k] = this.accessors[k].lobPrefetchSizeForThisColumn;
/*      */           }
/*      */           else
/*      */           {
/*  862 */             arrayOfInt[k] = -1;
/*      */           }
/*      */           
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*  870 */     if (i > 0)
/*      */     {
/*  872 */       if (arrayOfObject == null)
/*      */       {
/*  874 */         arrayOfObject = new Object[] { null, new long[this.rowPrefetch * i], new byte[this.accessors.length], new int[this.accessors.length], new Object[this.rowPrefetch * i] };
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
/*  896 */       for (k = 0; k < j; k++)
/*      */       {
/*  898 */         switch (this.accessors[k].internalType)
/*      */         {
/*      */         case 112: 
/*      */         case 113: 
/*  902 */           this.accessors[k].lobPrefetchSizeForThisColumn = -1;
/*  903 */           arrayOfInt[k] = -1;
/*      */         }
/*      */         
/*      */       }
/*      */       
/*  908 */       arrayOfObject[0] = arrayOfInt;
/*      */     }
/*      */     
/*      */ 
/*  912 */     return arrayOfObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void processLobPrefetchMetaData(Object[] paramArrayOfObject)
/*      */   {
/*  919 */     int i = 0;
/*  920 */     int j = this.validRows == -2 ? 1 : this.validRows;
/*      */     
/*  922 */     byte[] arrayOfByte = (byte[])paramArrayOfObject[2];
/*  923 */     int[] arrayOfInt1 = (int[])paramArrayOfObject[3];
/*  924 */     long[] arrayOfLong = (long[])paramArrayOfObject[1];
/*  925 */     Object[] arrayOfObject = (Object[])paramArrayOfObject[4];
/*  926 */     int[] arrayOfInt2 = (int[])paramArrayOfObject[0];
/*      */     
/*  928 */     if (this.accessors != null) {
/*  929 */       for (int k = 0; k < this.numberOfDefinePositions; k++)
/*      */       {
/*  931 */         switch (this.accessors[k].internalType)
/*      */         {
/*      */ 
/*      */         case 112: 
/*      */         case 113: 
/*  936 */           if (this.accessors[k].lobPrefetchSizeForThisColumn >= 0)
/*      */           {
/*  938 */             Accessor localAccessor = this.accessors[k];
/*      */             
/*  940 */             if ((localAccessor.prefetchedLobDataL == null) || (localAccessor.prefetchedLobDataL.length < this.rowPrefetch))
/*      */             {
/*      */ 
/*  943 */               if (localAccessor.internalType == 112) {
/*  944 */                 localAccessor.prefetchedLobCharData = new char[this.rowPrefetch][];
/*      */               } else {
/*  946 */                 localAccessor.prefetchedLobData = new byte[this.rowPrefetch][];
/*      */               }
/*  948 */               localAccessor.prefetchedLobChunkSize = new int[this.rowPrefetch];
/*  949 */               localAccessor.prefetchedClobFormOfUse = new byte[this.rowPrefetch];
/*  950 */               localAccessor.prefetchedLobDataL = new int[this.rowPrefetch];
/*  951 */               localAccessor.prefetchedLobSize = new long[this.rowPrefetch];
/*      */             }
/*      */             
/*  954 */             int m = j * i;
/*  955 */             for (int n = 0; n < j; n++)
/*      */             {
/*  957 */               localAccessor.prefetchedLobChunkSize[n] = arrayOfInt1[k];
/*      */               
/*  959 */               localAccessor.prefetchedClobFormOfUse[n] = arrayOfByte[k];
/*      */               
/*      */ 
/*  962 */               localAccessor.prefetchedLobSize[n] = arrayOfLong[(m + n)];
/*      */               
/*      */ 
/*  965 */               localAccessor.prefetchedLobDataL[n] = 0;
/*  966 */               if ((arrayOfInt2[k] > 0) && (arrayOfLong[(m + n)] > 0L))
/*      */               {
/*      */ 
/*  969 */                 if (localAccessor.internalType == 112)
/*      */                 {
/*  971 */                   localAccessor.prefetchedLobCharData[n] = ((char[])(char[])arrayOfObject[(m + n)]);
/*      */                   
/*  973 */                   if (localAccessor.prefetchedLobCharData[n] != null) {
/*  974 */                     localAccessor.prefetchedLobDataL[n] = localAccessor.prefetchedLobCharData[n].length;
/*      */                   }
/*      */                 }
/*      */                 else
/*      */                 {
/*  979 */                   localAccessor.prefetchedLobData[n] = ((byte[])(byte[])arrayOfObject[(m + n)]);
/*      */                   
/*  981 */                   if (localAccessor.prefetchedLobData[n] != null) {
/*  982 */                     localAccessor.prefetchedLobDataL[n] = localAccessor.prefetchedLobData[n].length;
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*  987 */             i++;
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
/* 1000 */     if (!this.needToPrepareDefineBuffer) {
/* 1001 */       throw new Error("doDefineFetch called when needToPrepareDefineBuffer=false " + this.sqlObject.getSql(this.processEscapes, this.convertNcharLiterals));
/*      */     }
/*      */     
/* 1004 */     setupForDefine();
/*      */     
/* 1006 */     this.t2cOutput[2] = 0L;
/* 1007 */     this.t2cOutput[5] = (this.connection.useNio ? 1 : 0);
/* 1008 */     this.t2cOutput[6] = this.defaultLobPrefetchSize;
/* 1009 */     if (this.connection.useNio) {
/* 1010 */       resetNioAttributesBeforeFetch();
/* 1011 */       allocateNioBuffersIfRequired(this.defineChars == null ? 0 : this.defineChars.length, this.defineBytes == null ? 0 : this.defineBytes.length, this.defineIndicators == null ? 0 : this.defineIndicators.length);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1017 */     if (this.lobPrefetchMetaData == null) {
/* 1018 */       this.lobPrefetchMetaData = getLobPrefetchMetaData();
/*      */     }
/* 1020 */     this.validRows = T2CStatement.t2cDefineFetch(this, this.c_state, this.rowPrefetch, this.connection.queryMetaData1, this.connection.queryMetaData2, this.connection.queryMetaData1Offset, this.connection.queryMetaData2Offset, this.accessors, this.defineBytes, this.accessorByteOffset, this.defineChars, this.accessorCharOffset, this.defineIndicators, this.accessorShortOffset, this.t2cOutput, this.nioBuffers, this.lobPrefetchMetaData);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1033 */     if ((this.validRows == -1) || (this.validRows == -4)) {
/* 1034 */       this.connection.checkError(this.validRows);
/*      */     }
/*      */     
/* 1037 */     if (this.t2cOutput[2] != 0L)
/*      */     {
/* 1039 */       this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */     }
/*      */     
/*      */ 
/* 1043 */     if ((this.connection.useNio) && ((this.validRows > 0) || (this.validRows == -2)))
/*      */     {
/* 1045 */       extractNioDefineBuffers(0);
/*      */     }
/* 1047 */     if (this.lobPrefetchMetaData != null)
/*      */     {
/* 1049 */       processLobPrefetchMetaData(this.lobPrefetchMetaData);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void allocateNioBuffersIfRequired(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1059 */     if (this.nioBuffers == null) {
/* 1060 */       this.nioBuffers = new ByteBuffer[4];
/*      */     }
/* 1062 */     if (paramInt2 > 0)
/*      */     {
/* 1064 */       if ((this.nioBuffers[0] == null) || (this.nioBuffers[0].capacity() < paramInt2))
/*      */       {
/*      */ 
/* 1067 */         this.nioBuffers[0] = ByteBuffer.allocateDirect(paramInt2);
/* 1068 */       } else if (this.nioBuffers[0] != null)
/*      */       {
/* 1070 */         this.nioBuffers[0].rewind();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1077 */     paramInt1 *= 2;
/* 1078 */     if (paramInt1 > 0)
/*      */     {
/* 1080 */       if ((this.nioBuffers[1] == null) || (this.nioBuffers[1].capacity() < paramInt1))
/*      */       {
/*      */ 
/* 1083 */         this.nioBuffers[1] = ByteBuffer.allocateDirect(paramInt1);
/* 1084 */       } else if (this.nioBuffers[1] != null)
/*      */       {
/* 1086 */         this.nioBuffers[1].rewind();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1093 */     paramInt3 *= 2;
/* 1094 */     if (paramInt3 > 0)
/*      */     {
/* 1096 */       if ((this.nioBuffers[2] == null) || (this.nioBuffers[2].capacity() < paramInt3))
/*      */       {
/*      */ 
/* 1099 */         this.nioBuffers[2] = ByteBuffer.allocateDirect(paramInt3);
/* 1100 */       } else if (this.nioBuffers[2] != null)
/*      */       {
/* 1102 */         this.nioBuffers[2].rewind();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void doDefineExecuteFetch()
/*      */     throws SQLException
/*      */   {
/* 1111 */     short[] arrayOfShort = null;
/*      */     
/* 1113 */     if ((this.needToPrepareDefineBuffer) || (this.needToParse))
/*      */     {
/* 1115 */       setupForDefine();
/*      */       
/* 1117 */       arrayOfShort = this.connection.queryMetaData1;
/*      */     }
/*      */     
/* 1120 */     this.t2cOutput[0] = 0L;
/* 1121 */     this.t2cOutput[2] = 0L;
/*      */     
/* 1123 */     byte[] arrayOfByte = this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals);
/* 1124 */     this.t2cOutput[5] = (this.connection.useNio ? 1 : 0);
/* 1125 */     this.t2cOutput[6] = this.defaultLobPrefetchSize;
/* 1126 */     if (this.connection.useNio) {
/* 1127 */       resetNioAttributesBeforeFetch();
/* 1128 */       allocateNioBuffersIfRequired(this.defineChars == null ? 0 : this.defineChars.length, this.defineBytes == null ? 0 : this.defineBytes.length, this.defineIndicators == null ? 0 : this.defineIndicators.length);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1134 */     if (this.lobPrefetchMetaData == null) {
/* 1135 */       this.lobPrefetchMetaData = getLobPrefetchMetaData();
/*      */     }
/*      */     try {
/* 1138 */       this.validRows = T2CStatement.t2cDefineExecuteFetch(this, this.c_state, this.numberOfDefinePositions, this.numberOfBindPositions, this.numberOfBindRowsAllocated, this.firstRowInBatch, false, this.needToParse, arrayOfByte, arrayOfByte.length, T2CStatement.convertSqlKindEnumToByte(this.sqlKind), this.rowPrefetch, this.batch, this.bindIndicators, this.bindIndicatorOffset, this.bindBytes, this.bindChars, this.bindByteOffset, this.bindCharOffset, arrayOfShort, this.connection.queryMetaData2, this.connection.queryMetaData1Offset, this.connection.queryMetaData2Offset, this.preparedAllBinds, this.preparedCharBinds, this.accessors, this.parameterDatum, this.t2cOutput, this.defineBytes, this.accessorByteOffset, this.defineChars, this.accessorCharOffset, this.defineIndicators, this.accessorShortOffset, this.nioBuffers, this.lobPrefetchMetaData);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1178 */       this.validRows = 0;
/*      */       
/* 1180 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1181 */       localSQLException.fillInStackTrace();
/* 1182 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1186 */     if (this.validRows == -1) {
/* 1187 */       this.connection.checkError(this.validRows);
/*      */     }
/* 1189 */     if (this.t2cOutput[2] != 0L) {
/* 1190 */       this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1195 */     this.connection.endToEndECIDSequenceNumber = ((short)(int)this.t2cOutput[4]);
/*      */     
/* 1197 */     if ((this.connection.useNio) && ((this.validRows > 0) || (this.validRows == -2)))
/*      */     {
/* 1199 */       extractNioDefineBuffers(0);
/*      */     }
/* 1201 */     if (this.lobPrefetchMetaData != null)
/*      */     {
/* 1203 */       processLobPrefetchMetaData(this.lobPrefetchMetaData);
/*      */     }
/*      */     
/* 1206 */     this.needToParse = false;
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
/* 1249 */     if (this.numberOfDefinePositions > 0)
/*      */     {
/* 1251 */       if (this.needToPrepareDefineBuffer) {
/* 1252 */         doDefineFetch();
/*      */       }
/*      */       else {
/* 1255 */         this.t2cOutput[2] = 0L;
/* 1256 */         this.t2cOutput[5] = (this.connection.useNio ? 1 : 0);
/* 1257 */         this.t2cOutput[6] = this.defaultLobPrefetchSize;
/* 1258 */         if (this.connection.useNio) {
/* 1259 */           resetNioAttributesBeforeFetch();
/* 1260 */           allocateNioBuffersIfRequired(this.defineChars == null ? 0 : this.defineChars.length, this.defineBytes == null ? 0 : this.defineBytes.length, this.defineIndicators == null ? 0 : this.defineIndicators.length);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1267 */         if (this.lobPrefetchMetaData == null) {
/* 1268 */           this.lobPrefetchMetaData = getLobPrefetchMetaData();
/*      */         }
/* 1270 */         this.validRows = T2CStatement.t2cFetch(this.c_state, this.needToPrepareDefineBuffer, this.rowPrefetch, this.accessors, this.defineBytes, this.accessorByteOffset, this.defineChars, this.accessorCharOffset, this.defineIndicators, this.accessorShortOffset, this.t2cOutput, this.nioBuffers, this.lobPrefetchMetaData);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1278 */         if ((this.validRows == -1) || (this.validRows == -4)) {
/* 1279 */           this.connection.checkError(this.validRows);
/*      */         }
/* 1281 */         if (this.t2cOutput[2] != 0L)
/*      */         {
/* 1283 */           this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */         }
/*      */         
/* 1286 */         if (this.lobPrefetchMetaData != null)
/*      */         {
/* 1288 */           processLobPrefetchMetaData(this.lobPrefetchMetaData);
/*      */         }
/* 1290 */         if ((this.connection.useNio) && ((this.validRows > 0) || (this.validRows == -2)))
/*      */         {
/* 1292 */           extractNioDefineBuffers(0);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void resetNioAttributesBeforeFetch()
/*      */   {
/* 1301 */     this.extractedCharOffset = 0;
/* 1302 */     this.extractedByteOffset = 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void extractNioDefineBuffers(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1311 */     if ((this.accessors == null) || (this.defineIndicators == null) || (paramInt == this.numberOfDefinePositions))
/*      */     {
/*      */ 
/* 1314 */       return;
/*      */     }
/* 1316 */     int i = 0;
/* 1317 */     int j = 0;
/* 1318 */     int k = 0;
/* 1319 */     int m = 0;
/* 1320 */     int n = 0;
/*      */     
/*      */ 
/* 1323 */     if (!this.hasStream)
/*      */     {
/* 1325 */       i = this.defineBytes != null ? this.defineBytes.length : 0;
/* 1326 */       j = this.defineChars != null ? this.defineChars.length : 0;
/* 1327 */       k = this.defineIndicators.length;
/*      */     }
/*      */     else
/*      */     {
/* 1331 */       if (this.numberOfDefinePositions > paramInt)
/*      */       {
/* 1333 */         n = this.accessors[paramInt].indicatorIndex;
/* 1334 */         m = this.accessors[paramInt].lengthIndex;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1339 */       for (int i1 = paramInt; i1 < this.numberOfDefinePositions; i1++)
/*      */       {
/* 1341 */         switch (this.accessors[i1].internalType)
/*      */         {
/*      */         case 8: 
/*      */         case 24: 
/*      */           break;
/*      */         default: 
/* 1347 */           i += this.accessors[i1].byteLength;
/* 1348 */           j += this.accessors[i1].charLength;
/* 1349 */           k++;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/* 1354 */     ByteBuffer localByteBuffer = this.nioBuffers[0];
/* 1355 */     if ((localByteBuffer != null) && (this.defineBytes != null))
/*      */     {
/* 1357 */       if (i > 0)
/*      */       {
/* 1359 */         localByteBuffer.position(this.extractedByteOffset);
/* 1360 */         localByteBuffer.get(this.defineBytes, this.extractedByteOffset, i);
/* 1361 */         this.extractedByteOffset += i;
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
/* 1376 */     if ((this.nioBuffers[1] != null) && (this.defineChars != null))
/*      */     {
/* 1378 */       localByteBuffer = this.nioBuffers[1].order(ByteOrder.LITTLE_ENDIAN);
/* 1379 */       localObject = localByteBuffer.asCharBuffer();
/*      */       
/* 1381 */       if (j > 0)
/*      */       {
/* 1383 */         ((CharBuffer)localObject).position(this.extractedCharOffset);
/* 1384 */         ((CharBuffer)localObject).get(this.defineChars, this.extractedCharOffset, j);
/* 1385 */         this.extractedCharOffset += j;
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
/* 1404 */     if (this.nioBuffers[2] != null) {
/* 1405 */       localByteBuffer = this.nioBuffers[2].order(ByteOrder.LITTLE_ENDIAN);
/* 1406 */       localObject = localByteBuffer.asShortBuffer();
/* 1407 */       if (this.hasStream)
/*      */       {
/* 1409 */         if (k > 0)
/*      */         {
/* 1411 */           ((ShortBuffer)localObject).position(n);
/* 1412 */           ((ShortBuffer)localObject).get(this.defineIndicators, n, k);
/* 1413 */           ((ShortBuffer)localObject).position(m);
/* 1414 */           ((ShortBuffer)localObject).get(this.defineIndicators, m, k);
/*      */         }
/*      */       }
/*      */       else {
/* 1418 */         ((ShortBuffer)localObject).get(this.defineIndicators);
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
/* 1466 */     if (this.defineBytes != null)
/*      */     {
/* 1468 */       this.defineBytes = null;
/* 1469 */       this.accessorByteOffset = 0;
/*      */     }
/*      */     
/* 1472 */     if (this.defineChars != null)
/*      */     {
/* 1474 */       this.defineChars = null;
/* 1475 */       this.accessorCharOffset = 0;
/*      */     }
/*      */     
/* 1478 */     if (this.defineIndicators != null)
/*      */     {
/* 1480 */       this.defineIndicators = null;
/* 1481 */       this.accessorShortOffset = 0;
/*      */     }
/*      */     
/*      */ 
/* 1485 */     int i = T2CStatement.t2cCloseStatement(this.c_state);
/*      */     
/* 1487 */     this.nioBuffers = null;
/*      */     
/* 1489 */     if (i != 0) {
/* 1490 */       this.connection.checkError(i);
/*      */     }
/* 1492 */     this.t2cOutput = null;
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
/* 1510 */     if (this.streamList != null)
/*      */     {
/* 1512 */       while (this.nextStream != null)
/*      */       {
/*      */         try
/*      */         {
/* 1516 */           this.nextStream.close();
/*      */ 
/*      */         }
/*      */         catch (IOException localIOException)
/*      */         {
/* 1521 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1522 */           localSQLException.fillInStackTrace();
/* 1523 */           throw localSQLException;
/*      */         }
/*      */         
/*      */ 
/* 1527 */         this.nextStream = this.nextStream.nextStream;
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
/* 1544 */     if ((paramInt1 == 116) || (paramInt1 == 102))
/*      */     {
/*      */ 
/* 1547 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 1549 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 1550 */         localSQLException.fillInStackTrace();
/* 1551 */         throw localSQLException;
/*      */       }
/*      */       
/* 1554 */       T2CResultSetAccessor localT2CResultSetAccessor = new T2CResultSetAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */       
/*      */ 
/* 1557 */       return localT2CResultSetAccessor;
/*      */     }
/*      */     
/* 1560 */     return super.allocateAccessor(paramInt1, paramInt2, paramInt3, paramInt4, paramShort, paramString, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */   void closeUsedStreams(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/* 1568 */     while ((this.nextStream != null) && (this.nextStream.columnIndex < paramInt))
/*      */     {
/*      */ 
/*      */       try
/*      */       {
/*      */ 
/* 1574 */         this.nextStream.close();
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException1)
/*      */       {
/* 1579 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException1);
/* 1580 */         localSQLException.fillInStackTrace();
/* 1581 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1585 */       this.nextStream = this.nextStream.nextStream;
/*      */     }
/*      */     
/* 1588 */     if (this.nextStream != null) {
/*      */       try
/*      */       {
/* 1591 */         this.nextStream.needBytes();
/*      */       }
/*      */       catch (IOException localIOException2)
/*      */       {
/* 1595 */         interalCloseOnIOException(localIOException2);
/*      */         
/* 1597 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException2);
/* 1598 */         localSQLException.fillInStackTrace();
/* 1599 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void interalCloseOnIOException(IOException paramIOException)
/*      */     throws SQLException
/*      */   {
/* 1608 */     this.closed = true;
/*      */     
/* 1610 */     if (this.currentResultSet != null) {
/* 1611 */       this.currentResultSet.closed = true;
/*      */     }
/* 1613 */     doClose();
/*      */   }
/*      */   
/*      */ 
/*      */   void fetchDmlReturnParams()
/*      */     throws SQLException
/*      */   {
/* 1620 */     this.rowsDmlReturned = T2CStatement.t2cGetRowsDmlReturned(this.c_state);
/*      */     
/* 1622 */     if (this.rowsDmlReturned != 0)
/*      */     {
/* 1624 */       allocateDmlReturnStorage();
/*      */       
/* 1626 */       int i = T2CStatement.t2cFetchDmlReturnParams(this.c_state, this.returnParamAccessors, this.returnParamBytes, this.returnParamChars, this.returnParamIndicators);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1634 */       if ((i == -1) || (i == -4)) {
/* 1635 */         this.connection.checkError(i);
/*      */       }
/*      */       
/* 1638 */       if (this.t2cOutput[2] != 0L)
/*      */       {
/* 1640 */         this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */       }
/*      */       
/*      */ 
/* 1644 */       if ((this.connection.useNio) && ((i > 0) || (i == -2)))
/*      */       {
/* 1646 */         extractNioDefineBuffers(0);
/*      */       }
/*      */     }
/* 1649 */     this.returnParamsFetched = true;
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
/* 1660 */   static int PREAMBLE_PER_POSITION = 5;
/*      */   
/*      */ 
/*      */ 
/*      */   void initializeIndicatorSubRange()
/*      */   {
/* 1666 */     this.bindIndicatorSubRange = (this.numberOfBindPositions * PREAMBLE_PER_POSITION);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   int calculateIndicatorSubRangeSize()
/*      */   {
/* 1673 */     return this.numberOfBindPositions * PREAMBLE_PER_POSITION;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   short getInoutIndicator(int paramInt)
/*      */   {
/* 1680 */     return this.bindIndicators[(paramInt * PREAMBLE_PER_POSITION)];
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
/* 1693 */     int i = calculateIndicatorSubRangeSize();
/* 1694 */     int j = this.bindIndicatorSubRange - i;
/* 1695 */     OracleTypeADT[] arrayOfOracleTypeADT = this.parameterOtype == null ? null : this.parameterOtype[this.firstRowInBatch];
/*      */     
/*      */ 
/*      */ 
/* 1699 */     for (int k = 0; k < this.numberOfBindPositions; k++)
/*      */     {
/*      */ 
/* 1702 */       Binder localBinder = this.lastBinders[k];
/*      */       OracleTypeADT localOracleTypeADT;
/*      */       short s;
/* 1705 */       if (localBinder == this.theReturnParamBinder)
/*      */       {
/* 1707 */         localOracleTypeADT = (OracleTypeADT)this.returnParamAccessors[k].internalOtype;
/* 1708 */         s = 0;
/*      */       }
/*      */       else
/*      */       {
/* 1712 */         localOracleTypeADT = arrayOfOracleTypeADT == null ? null : arrayOfOracleTypeADT[k];
/*      */         
/* 1714 */         if (this.outBindAccessors == null) {
/* 1715 */           s = 0;
/*      */         }
/*      */         else {
/* 1718 */           Accessor localAccessor = this.outBindAccessors[k];
/*      */           
/* 1720 */           if (localAccessor == null) {
/* 1721 */             s = 0;
/* 1722 */           } else if (localBinder == this.theOutBinder)
/*      */           {
/* 1724 */             s = 1;
/*      */             
/* 1726 */             if (localOracleTypeADT == null) {
/* 1727 */               localOracleTypeADT = (OracleTypeADT)localAccessor.internalOtype;
/*      */             }
/*      */           } else {
/* 1730 */             s = 2;
/*      */           } }
/* 1732 */         s = localBinder.updateInoutIndicatorValue(s);
/*      */       }
/*      */       
/* 1735 */       this.bindIndicators[(j++)] = s;
/*      */       
/* 1737 */       if (localOracleTypeADT != null)
/*      */       {
/* 1739 */         long l = localOracleTypeADT.getTdoCState();
/*      */         
/* 1741 */         this.bindIndicators[(j + 0)] = ((short)(int)(l >> 48 & 0xFFFF));
/*      */         
/* 1743 */         this.bindIndicators[(j + 1)] = ((short)(int)(l >> 32 & 0xFFFF));
/*      */         
/* 1745 */         this.bindIndicators[(j + 2)] = ((short)(int)(l >> 16 & 0xFFFF));
/*      */         
/* 1747 */         this.bindIndicators[(j + 3)] = ((short)(int)(l & 0xFFFF));
/*      */       }
/*      */       
/* 1750 */       j += 4;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void releaseBuffers()
/*      */   {
/* 1758 */     super.releaseBuffers();
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
/* 1777 */     if (this.closed)
/*      */     {
/* 1779 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1780 */       localSQLException.fillInStackTrace();
/* 1781 */       throw localSQLException;
/*      */     }
/*      */     
/* 1784 */     if (this.described == true)
/*      */     {
/* 1786 */       return;
/*      */     }
/*      */     
/* 1789 */     if (!this.isOpen)
/*      */     {
/*      */ 
/*      */ 
/* 1793 */       this.connection.open(this);
/* 1794 */       this.isOpen = true;
/*      */     }
/*      */     
/*      */ 
/*      */     int i;
/*      */     
/*      */     do
/*      */     {
/* 1802 */       i = 0;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1811 */       boolean bool = (this.sqlKind.isSELECT()) && (this.needToParse) && ((!this.described) || (!this.serverCursor));
/* 1812 */       byte[] arrayOfByte = bool ? this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals) : PhysicalConnection.EMPTY_BYTE_ARRAY;
/* 1813 */       this.numberOfDefinePositions = T2CStatement.t2cDescribe(this.c_state, this.connection.queryMetaData1, this.connection.queryMetaData2, this.connection.queryMetaData1Offset, this.connection.queryMetaData2Offset, this.connection.queryMetaData1Size, this.connection.queryMetaData2Size, arrayOfByte, arrayOfByte.length, bool);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1824 */       if (!this.described) {
/* 1825 */         this.described = true;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1832 */       if (this.numberOfDefinePositions == -1)
/*      */       {
/* 1834 */         this.connection.checkError(this.numberOfDefinePositions);
/*      */       }
/*      */       
/*      */ 
/* 1838 */       if (this.numberOfDefinePositions == T2C_EXTEND_BUFFER)
/*      */       {
/* 1840 */         i = 1;
/*      */         
/*      */ 
/*      */ 
/* 1844 */         this.connection.reallocateQueryMetaData(this.connection.queryMetaData1Size * 2, this.connection.queryMetaData2Size * 2);
/*      */       }
/*      */       
/*      */     }
/* 1848 */     while (i != 0);
/*      */     
/* 1850 */     processDescribeData();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 1856 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T2CPreparedStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */