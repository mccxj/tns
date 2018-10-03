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
/*      */ class T2CStatement
/*      */   extends OracleStatement
/*      */ {
/*   36 */   T2CConnection connection = null;
/*   37 */   int userResultSetType = -1;
/*   38 */   int userResultSetConcur = -1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   44 */   static int T2C_EXTEND_BUFFER = -3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   59 */   long[] t2cOutput = new long[10];
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
/*      */   T2CStatement(T2CConnection paramT2CConnection, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*   98 */     this(paramT2CConnection, paramInt1, paramInt2, -1, -1);
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
/*      */   T2CStatement(T2CConnection paramT2CConnection, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     throws SQLException
/*      */   {
/*  115 */     super(paramT2CConnection, paramInt1, paramInt2, paramInt3, paramInt4);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  121 */     this.userResultSetType = paramInt3;
/*  122 */     this.userResultSetConcur = paramInt4;
/*      */     
/*  124 */     this.connection = paramT2CConnection;
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
/*      */   static native int t2cParseExecuteDescribe(OracleStatement paramOracleStatement, long paramLong, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, byte[] paramArrayOfByte1, int paramInt4, byte paramByte, int paramInt5, int paramInt6, short[] paramArrayOfShort1, int paramInt7, byte[] paramArrayOfByte2, char[] paramArrayOfChar1, int paramInt8, int paramInt9, short[] paramArrayOfShort2, int paramInt10, int paramInt11, byte[] paramArrayOfByte3, char[] paramArrayOfChar2, int paramInt12, int paramInt13, int[] paramArrayOfInt, short[] paramArrayOfShort3, byte[] paramArrayOfByte4, int paramInt14, int paramInt15, int paramInt16, int paramInt17, boolean paramBoolean5, boolean paramBoolean6, Accessor[] paramArrayOfAccessor, byte[][][] paramArrayOfByte, long[] paramArrayOfLong, byte[] paramArrayOfByte5, int paramInt18, char[] paramArrayOfChar3, int paramInt19, short[] paramArrayOfShort4, int paramInt20, boolean paramBoolean7)
/*      */     throws IOException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static native int t2cDefineExecuteFetch(OracleStatement paramOracleStatement, long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2, byte[] paramArrayOfByte1, int paramInt5, byte paramByte, int paramInt6, int paramInt7, short[] paramArrayOfShort1, int paramInt8, byte[] paramArrayOfByte2, char[] paramArrayOfChar1, int paramInt9, int paramInt10, short[] paramArrayOfShort2, byte[] paramArrayOfByte3, int paramInt11, int paramInt12, boolean paramBoolean3, boolean paramBoolean4, Accessor[] paramArrayOfAccessor, byte[][][] paramArrayOfByte, long[] paramArrayOfLong, byte[] paramArrayOfByte4, int paramInt13, char[] paramArrayOfChar2, int paramInt14, short[] paramArrayOfShort3, int paramInt15, ByteBuffer[] paramArrayOfByteBuffer, Object[] paramArrayOfObject)
/*      */     throws IOException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static native int t2cDescribe(long paramLong, short[] paramArrayOfShort, byte[] paramArrayOfByte1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte2, int paramInt5, boolean paramBoolean);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static native int t2cDefineFetch(OracleStatement paramOracleStatement, long paramLong, int paramInt1, short[] paramArrayOfShort1, byte[] paramArrayOfByte1, int paramInt2, int paramInt3, Accessor[] paramArrayOfAccessor, byte[] paramArrayOfByte2, int paramInt4, char[] paramArrayOfChar, int paramInt5, short[] paramArrayOfShort2, int paramInt6, long[] paramArrayOfLong, ByteBuffer[] paramArrayOfByteBuffer, Object[] paramArrayOfObject);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static native int t2cFetch(long paramLong, boolean paramBoolean, int paramInt1, Accessor[] paramArrayOfAccessor, byte[] paramArrayOfByte, int paramInt2, char[] paramArrayOfChar, int paramInt3, short[] paramArrayOfShort, int paramInt4, long[] paramArrayOfLong, ByteBuffer[] paramArrayOfByteBuffer, Object[] paramArrayOfObject);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static native int t2cCloseStatement(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static native int t2cEndToEndUpdate(long paramLong, byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, byte[] paramArrayOfByte3, int paramInt3, byte[] paramArrayOfByte4, int paramInt4, int paramInt5);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static native int t2cGetRowsDmlReturned(long paramLong);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static native int t2cFetchDmlReturnParams(long paramLong, Accessor[] paramArrayOfAccessor, byte[] paramArrayOfByte, char[] paramArrayOfChar, short[] paramArrayOfShort);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  344 */     byte[] arrayOfByte = new byte[paramInt2];
/*      */     
/*  346 */     System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
/*      */     
/*  348 */     return this.connection.conversion.CharBytesToString(arrayOfByte, paramInt2);
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
/*  364 */     this.described = true;
/*  365 */     this.describedWithNames = true;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  376 */     if ((this.accessors == null) || (this.numberOfDefinePositions > this.accessors.length)) {
/*  377 */       this.accessors = new Accessor[this.numberOfDefinePositions];
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
/*  393 */     int i = this.connection.queryMetaData1Offset;
/*  394 */     int j = this.connection.queryMetaData2Offset;
/*  395 */     short[] arrayOfShort = this.connection.queryMetaData1;
/*  396 */     byte[] arrayOfByte = this.connection.queryMetaData2;
/*      */     
/*  398 */     for (int k = 0; k < this.numberOfDefinePositions; 
/*  399 */         i += 13)
/*      */     {
/*  401 */       int m = arrayOfShort[(i + 0)];
/*  402 */       int n = arrayOfShort[(i + 1)];
/*  403 */       int i1 = arrayOfShort[(i + 11)];
/*  404 */       boolean bool = arrayOfShort[(i + 2)] != 0;
/*  405 */       int i2 = arrayOfShort[(i + 3)];
/*  406 */       int i3 = arrayOfShort[(i + 4)];
/*  407 */       int i4 = 0;
/*  408 */       int i5 = 0;
/*  409 */       int i6 = 0;
/*  410 */       short s = arrayOfShort[(i + 5)];
/*  411 */       int i7 = arrayOfShort[(i + 6)];
/*  412 */       String str1 = bytes2String(arrayOfByte, j, i7);
/*  413 */       int i8 = arrayOfShort[(i + 12)];
/*  414 */       String str2 = null;
/*  415 */       OracleTypeADT localOracleTypeADT = null;
/*      */       
/*  417 */       j += i7;
/*      */       
/*  419 */       if (i8 > 0)
/*      */       {
/*  421 */         str2 = bytes2String(arrayOfByte, j, i8);
/*  422 */         j += i8;
/*  423 */         localOracleTypeADT = new OracleTypeADT(str2, this.connection);
/*  424 */         localOracleTypeADT.tdoCState = ((arrayOfShort[(i + 7)] & 0xFFFF) << 48 | (arrayOfShort[(i + 8)] & 0xFFFF) << 32 | (arrayOfShort[(i + 9)] & 0xFFFF) << 16 | arrayOfShort[(i + 10)] & 0xFFFF);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  432 */       Object localObject = this.accessors[k];
/*      */       
/*  434 */       if ((localObject != null) && (!((Accessor)localObject).useForDescribeIfPossible(m, n, bool, i4, i2, i3, i5, i6, s, str2)))
/*      */       {
/*      */ 
/*      */ 
/*  438 */         localObject = null;
/*      */       }
/*      */       
/*  441 */       if (localObject == null)
/*      */       {
/*  443 */         switch (m)
/*      */         {
/*      */ 
/*      */         case 1: 
/*  447 */           localObject = new VarcharAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  451 */           if (i1 > 0) {
/*  452 */             ((Accessor)localObject).setDisplaySize(i1);
/*      */           }
/*      */           
/*      */           break;
/*      */         case 96: 
/*  457 */           localObject = new CharAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  461 */           if (i1 > 0) {
/*  462 */             ((Accessor)localObject).setDisplaySize(i1);
/*      */           }
/*      */           
/*      */           break;
/*      */         case 2: 
/*  467 */           localObject = new NumberAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  471 */           break;
/*      */         
/*      */         case 23: 
/*  474 */           localObject = new RawAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  478 */           break;
/*      */         
/*      */         case 100: 
/*  481 */           localObject = new BinaryFloatAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  485 */           break;
/*      */         
/*      */         case 101: 
/*  488 */           localObject = new BinaryDoubleAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  492 */           break;
/*      */         
/*      */         case 8: 
/*  495 */           localObject = new LongAccessor(this, k + 1, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  502 */           this.rowPrefetch = 1;
/*      */           
/*  504 */           break;
/*      */         
/*      */         case 24: 
/*  507 */           localObject = new LongRawAccessor(this, k + 1, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  514 */           this.rowPrefetch = 1;
/*      */           
/*  516 */           break;
/*      */         
/*      */         case 104: 
/*  519 */           localObject = new RowidAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  523 */           break;
/*      */         
/*      */ 
/*      */         case 102: 
/*      */         case 116: 
/*  528 */           localObject = new T2CResultSetAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  532 */           break;
/*      */         
/*      */         case 12: 
/*  535 */           localObject = new DateAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  539 */           break;
/*      */         
/*      */         case 180: 
/*  542 */           localObject = new TimestampAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  546 */           break;
/*      */         
/*      */         case 181: 
/*  549 */           localObject = new TimestamptzAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  553 */           break;
/*      */         
/*      */         case 231: 
/*  556 */           localObject = new TimestampltzAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  560 */           break;
/*      */         
/*      */         case 182: 
/*  563 */           localObject = new IntervalymAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  567 */           break;
/*      */         
/*      */         case 183: 
/*  570 */           localObject = new IntervaldsAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  574 */           break;
/*      */         
/*      */         case 112: 
/*  577 */           localObject = new ClobAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  581 */           break;
/*      */         
/*      */         case 113: 
/*  584 */           localObject = new BlobAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  588 */           break;
/*      */         
/*      */         case 114: 
/*  591 */           localObject = new BfileAccessor(this, n, bool, i4, i2, i3, i5, i6, s);
/*      */           
/*      */ 
/*      */ 
/*  595 */           break;
/*      */         
/*      */         case 109: 
/*  598 */           localObject = new NamedTypeAccessor(this, n, bool, i4, i2, i3, i5, i6, s, str2, localOracleTypeADT);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  603 */           break;
/*      */         
/*      */         case 111: 
/*  606 */           localObject = new RefTypeAccessor(this, n, bool, i4, i2, i3, i5, i6, s, str2, localOracleTypeADT);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  611 */           break;
/*      */         
/*      */ 
/*      */         default: 
/*  615 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Unknown or unimplemented accessor type: " + m);
/*      */           
/*  617 */           localSQLException.fillInStackTrace();
/*  618 */           throw localSQLException;
/*      */         }
/*      */         
/*      */         
/*      */ 
/*  623 */         this.accessors[k] = localObject;
/*      */       }
/*  625 */       else if (localOracleTypeADT != null)
/*      */       {
/*      */ 
/*      */ 
/*  629 */         ((Accessor)localObject).describeOtype = localOracleTypeADT;
/*  630 */         ((Accessor)localObject).initMetadata();
/*      */       }
/*      */       
/*  633 */       ((Accessor)localObject).columnName = str1;k++;
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
/*  674 */     this.t2cOutput[0] = 0L;
/*  675 */     this.t2cOutput[2] = 0L;
/*      */     
/*      */ 
/*      */ 
/*  679 */     this.lobPrefetchMetaData = null;
/*      */     
/*  681 */     boolean bool1 = !this.described;
/*  682 */     boolean bool2 = false;
/*      */     
/*      */     int i;
/*      */     do
/*      */     {
/*  687 */       i = 0;
/*      */       
/*      */ 
/*  690 */       if (this.connection.endToEndAnyChanged)
/*      */       {
/*  692 */         pushEndToEndValues();
/*      */         
/*  694 */         this.connection.endToEndAnyChanged = false;
/*      */       }
/*      */       
/*      */ 
/*  698 */       byte[] arrayOfByte = this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals);
/*      */       
/*  700 */       int j = 0;
/*      */       try
/*      */       {
/*  703 */         j = t2cParseExecuteDescribe(this, this.c_state, this.numberOfBindPositions, 0, 0, false, this.needToParse, bool1, bool2, arrayOfByte, arrayOfByte.length, convertSqlKindEnumToByte(this.sqlKind), this.rowPrefetch, this.batch, this.bindIndicators, this.bindIndicatorOffset, this.bindBytes, this.bindChars, this.bindByteOffset, this.bindCharOffset, this.ibtBindIndicators, this.ibtBindIndicatorOffset, this.ibtBindIndicatorSize, this.ibtBindBytes, this.ibtBindChars, this.ibtBindByteOffset, this.ibtBindCharOffset, this.returnParamMeta, this.connection.queryMetaData1, this.connection.queryMetaData2, this.connection.queryMetaData1Offset, this.connection.queryMetaData2Offset, this.connection.queryMetaData1Size, this.connection.queryMetaData2Size, true, true, this.accessors, (byte[][][])null, this.t2cOutput, this.defineBytes, this.accessorByteOffset, this.defineChars, this.accessorCharOffset, this.defineIndicators, this.accessorShortOffset, this.connection.plsqlCompilerWarnings);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  753 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 266);
/*  754 */         localSQLException.fillInStackTrace();
/*  755 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  760 */       this.validRows = ((int)this.t2cOutput[1]);
/*      */       
/*      */ 
/*  763 */       if ((j == -1) || (j == -4))
/*      */       {
/*  765 */         this.connection.checkError(j);
/*      */       }
/*  767 */       else if (j == T2C_EXTEND_BUFFER)
/*      */       {
/*  769 */         j = this.connection.queryMetaData1Size * 2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  774 */       if (this.t2cOutput[3] != 0L)
/*      */       {
/*  776 */         foundPlsqlCompilerWarning();
/*      */       }
/*  778 */       else if (this.t2cOutput[2] != 0L)
/*      */       {
/*  780 */         this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  785 */       this.connection.endToEndECIDSequenceNumber = ((short)(int)this.t2cOutput[4]);
/*      */       
/*      */ 
/*  788 */       this.needToParse = false;
/*  789 */       bool2 = true;
/*      */       
/*  791 */       if (this.sqlKind.isSELECT())
/*      */       {
/*  793 */         this.numberOfDefinePositions = j;
/*      */         
/*  795 */         if (this.numberOfDefinePositions > this.connection.queryMetaData1Size)
/*      */         {
/*  797 */           i = 1;
/*  798 */           bool2 = true;
/*      */           
/*      */ 
/*  801 */           this.connection.reallocateQueryMetaData(this.numberOfDefinePositions, this.numberOfDefinePositions * 8);
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  808 */         this.numberOfDefinePositions = 0;
/*  809 */         this.validRows = j;
/*      */       }
/*      */       
/*  812 */     } while (i != 0);
/*      */     
/*  814 */     processDescribeData();
/*      */   }
/*      */   
/*      */ 
/*      */   void pushEndToEndValues()
/*      */     throws SQLException
/*      */   {
/*  821 */     T2CConnection localT2CConnection = this.connection;
/*  822 */     byte[] arrayOfByte1 = new byte[0];
/*  823 */     byte[] arrayOfByte2 = new byte[0];
/*  824 */     byte[] arrayOfByte3 = new byte[0];
/*  825 */     byte[] arrayOfByte4 = new byte[0];
/*      */     
/*  827 */     if (localT2CConnection.endToEndValues != null) {
/*      */       String str;
/*  829 */       if (localT2CConnection.endToEndHasChanged[0] != 0)
/*      */       {
/*  831 */         str = localT2CConnection.endToEndValues[0];
/*      */         
/*  833 */         if (str != null) {
/*  834 */           arrayOfByte1 = DBConversion.stringToDriverCharBytes(str, localT2CConnection.m_clientCharacterSet);
/*      */         }
/*      */         
/*  837 */         localT2CConnection.endToEndHasChanged[0] = false;
/*      */       }
/*      */       
/*  840 */       if (localT2CConnection.endToEndHasChanged[1] != 0)
/*      */       {
/*  842 */         str = localT2CConnection.endToEndValues[1];
/*      */         
/*  844 */         if (str != null) {
/*  845 */           arrayOfByte2 = DBConversion.stringToDriverCharBytes(str, localT2CConnection.m_clientCharacterSet);
/*      */         }
/*      */         
/*  848 */         localT2CConnection.endToEndHasChanged[1] = false;
/*      */       }
/*      */       
/*  851 */       if (localT2CConnection.endToEndHasChanged[2] != 0)
/*      */       {
/*  853 */         str = localT2CConnection.endToEndValues[2];
/*      */         
/*  855 */         if (str != null) {
/*  856 */           arrayOfByte3 = DBConversion.stringToDriverCharBytes(str, localT2CConnection.m_clientCharacterSet);
/*      */         }
/*      */         
/*  859 */         localT2CConnection.endToEndHasChanged[2] = false;
/*      */       }
/*      */       
/*  862 */       if (localT2CConnection.endToEndHasChanged[3] != 0)
/*      */       {
/*  864 */         str = localT2CConnection.endToEndValues[3];
/*      */         
/*  866 */         if (str != null) {
/*  867 */           arrayOfByte4 = DBConversion.stringToDriverCharBytes(str, localT2CConnection.m_clientCharacterSet);
/*      */         }
/*      */         
/*  870 */         localT2CConnection.endToEndHasChanged[3] = false;
/*      */       }
/*      */       
/*  873 */       t2cEndToEndUpdate(this.c_state, arrayOfByte1, arrayOfByte1.length, arrayOfByte2, arrayOfByte2.length, arrayOfByte3, arrayOfByte3.length, arrayOfByte4, arrayOfByte4.length, localT2CConnection.endToEndECIDSequenceNumber);
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
/*  926 */     if (this.connection.endToEndAnyChanged)
/*      */     {
/*  928 */       pushEndToEndValues();
/*      */       
/*  930 */       this.connection.endToEndAnyChanged = false;
/*      */     }
/*      */     
/*      */ 
/*  934 */     if (!paramBoolean)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  942 */       if (this.numberOfDefinePositions > 0)
/*      */       {
/*  944 */         doDefineExecuteFetch();
/*      */       }
/*      */       else
/*      */       {
/*  948 */         executeForDescribe();
/*      */       }
/*      */     }
/*  951 */     else if (this.numberOfDefinePositions > 0) {
/*  952 */       doDefineFetch();
/*      */     }
/*      */     
/*      */ 
/*  956 */     this.needToPrepareDefineBuffer = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void setupForDefine()
/*      */     throws SQLException
/*      */   {
/*  966 */     if (this.numberOfDefinePositions > this.connection.queryMetaData1Size)
/*      */     {
/*  968 */       int i = this.numberOfDefinePositions / 100 + 1;
/*      */       
/*  970 */       this.connection.reallocateQueryMetaData(this.connection.queryMetaData1Size * i, this.connection.queryMetaData2Size * i * 8);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  975 */     short[] arrayOfShort = this.connection.queryMetaData1;
/*  976 */     int j = this.connection.queryMetaData1Offset;
/*      */     
/*      */ 
/*  979 */     for (int k = 0; k < this.numberOfDefinePositions; 
/*  980 */         j += 13)
/*      */     {
/*  982 */       Accessor localAccessor = this.accessors[k];
/*      */       
/*  984 */       if (localAccessor == null)
/*      */       {
/*  986 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  987 */         localSQLException.fillInStackTrace();
/*  988 */         throw localSQLException;
/*      */       }
/*      */       
/*  991 */       arrayOfShort[(j + 0)] = ((short)localAccessor.defineType);
/*      */       
/*  993 */       arrayOfShort[(j + 11)] = ((short)localAccessor.charLength);
/*      */       
/*  995 */       arrayOfShort[(j + 1)] = ((short)localAccessor.byteLength);
/*      */       
/*  997 */       arrayOfShort[(j + 5)] = localAccessor.formOfUse;
/*      */       
/*      */ 
/* 1000 */       if (localAccessor.internalOtype != null)
/*      */       {
/* 1002 */         long l = ((OracleTypeADT)localAccessor.internalOtype).getTdoCState();
/*      */         
/*      */ 
/* 1005 */         arrayOfShort[(j + 7)] = ((short)(int)((l & 0xFFFF000000000000) >> 48));
/*      */         
/* 1007 */         arrayOfShort[(j + 8)] = ((short)(int)((l & 0xFFFF00000000) >> 32));
/*      */         
/* 1009 */         arrayOfShort[(j + 9)] = ((short)(int)((l & 0xFFFF0000) >> 16));
/*      */         
/* 1011 */         arrayOfShort[(j + 10)] = ((short)(int)(l & 0xFFFF));
/*      */       }
/*      */       
/*      */ 
/* 1015 */       switch (localAccessor.internalType)
/*      */       {
/*      */ 
/*      */ 
/*      */       case 112: 
/*      */       case 113: 
/* 1021 */         if (localAccessor.lobPrefetchSizeForThisColumn == -1) {
/* 1022 */           localAccessor.lobPrefetchSizeForThisColumn = this.defaultLobPrefetchSize;
/*      */         }
/*      */         
/* 1025 */         arrayOfShort[(j + 7)] = ((short)localAccessor.lobPrefetchSizeForThisColumn);
/*      */       }
/*      */       
/*  980 */       k++;
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
/* 1045 */     Object[] arrayOfObject = null;
/* 1046 */     Object localObject = null;
/* 1047 */     int[] arrayOfInt = null;
/* 1048 */     int i = 0;
/* 1049 */     int j = 0;
/*      */     int k;
/* 1051 */     if (this.accessors != null) {
/* 1052 */       for (k = 0; k < this.numberOfDefinePositions; k++)
/*      */       {
/* 1054 */         switch (this.accessors[k].internalType)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */         case 8: 
/*      */         case 24: 
/* 1061 */           j = k;
/* 1062 */           break;
/*      */         
/*      */ 
/*      */ 
/*      */         case 112: 
/*      */         case 113: 
/* 1068 */           if (arrayOfInt == null)
/*      */           {
/* 1070 */             arrayOfInt = new int[this.accessors.length];
/*      */           }
/*      */           
/* 1073 */           if (this.accessors[k].lobPrefetchSizeForThisColumn != -1)
/*      */           {
/* 1075 */             i++;
/*      */             
/* 1077 */             arrayOfInt[k] = this.accessors[k].lobPrefetchSizeForThisColumn;
/*      */           }
/*      */           else
/*      */           {
/* 1081 */             arrayOfInt[k] = -1;
/*      */           }
/*      */           
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/* 1089 */     if (i > 0)
/*      */     {
/* 1091 */       if (arrayOfObject == null)
/*      */       {
/* 1093 */         arrayOfObject = new Object[] { null, new long[this.rowPrefetch * i], new byte[this.accessors.length], new int[this.accessors.length], new Object[this.rowPrefetch * i] };
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
/* 1115 */       for (k = 0; k < j; k++)
/*      */       {
/* 1117 */         switch (this.accessors[k].internalType)
/*      */         {
/*      */         case 112: 
/*      */         case 113: 
/* 1121 */           this.accessors[k].lobPrefetchSizeForThisColumn = -1;
/* 1122 */           arrayOfInt[k] = -1;
/*      */         }
/*      */         
/*      */       }
/*      */       
/* 1127 */       arrayOfObject[0] = arrayOfInt;
/*      */     }
/*      */     
/*      */ 
/* 1131 */     return arrayOfObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void processLobPrefetchMetaData(Object[] paramArrayOfObject)
/*      */   {
/* 1138 */     int i = 0;
/* 1139 */     int j = this.validRows == -2 ? 1 : this.validRows;
/*      */     
/* 1141 */     byte[] arrayOfByte = (byte[])paramArrayOfObject[2];
/* 1142 */     int[] arrayOfInt1 = (int[])paramArrayOfObject[3];
/* 1143 */     long[] arrayOfLong = (long[])paramArrayOfObject[1];
/* 1144 */     Object[] arrayOfObject = (Object[])paramArrayOfObject[4];
/* 1145 */     int[] arrayOfInt2 = (int[])paramArrayOfObject[0];
/*      */     
/* 1147 */     if (this.accessors != null) {
/* 1148 */       for (int k = 0; k < this.numberOfDefinePositions; k++)
/*      */       {
/* 1150 */         switch (this.accessors[k].internalType)
/*      */         {
/*      */ 
/*      */         case 112: 
/*      */         case 113: 
/* 1155 */           if (this.accessors[k].lobPrefetchSizeForThisColumn >= 0)
/*      */           {
/* 1157 */             Accessor localAccessor = this.accessors[k];
/*      */             
/* 1159 */             if ((localAccessor.prefetchedLobDataL == null) || (localAccessor.prefetchedLobDataL.length < this.rowPrefetch))
/*      */             {
/*      */ 
/* 1162 */               if (localAccessor.internalType == 112) {
/* 1163 */                 localAccessor.prefetchedLobCharData = new char[this.rowPrefetch][];
/*      */               } else {
/* 1165 */                 localAccessor.prefetchedLobData = new byte[this.rowPrefetch][];
/*      */               }
/* 1167 */               localAccessor.prefetchedLobChunkSize = new int[this.rowPrefetch];
/* 1168 */               localAccessor.prefetchedClobFormOfUse = new byte[this.rowPrefetch];
/* 1169 */               localAccessor.prefetchedLobDataL = new int[this.rowPrefetch];
/* 1170 */               localAccessor.prefetchedLobSize = new long[this.rowPrefetch];
/*      */             }
/*      */             
/* 1173 */             int m = j * i;
/* 1174 */             for (int n = 0; n < j; n++)
/*      */             {
/* 1176 */               localAccessor.prefetchedLobChunkSize[n] = arrayOfInt1[k];
/*      */               
/* 1178 */               localAccessor.prefetchedClobFormOfUse[n] = arrayOfByte[k];
/*      */               
/*      */ 
/* 1181 */               localAccessor.prefetchedLobSize[n] = arrayOfLong[(m + n)];
/*      */               
/*      */ 
/* 1184 */               localAccessor.prefetchedLobDataL[n] = 0;
/* 1185 */               if ((arrayOfInt2[k] > 0) && (arrayOfLong[(m + n)] > 0L))
/*      */               {
/*      */ 
/* 1188 */                 if (localAccessor.internalType == 112)
/*      */                 {
/* 1190 */                   localAccessor.prefetchedLobCharData[n] = ((char[])(char[])arrayOfObject[(m + n)]);
/*      */                   
/* 1192 */                   if (localAccessor.prefetchedLobCharData[n] != null) {
/* 1193 */                     localAccessor.prefetchedLobDataL[n] = localAccessor.prefetchedLobCharData[n].length;
/*      */                   }
/*      */                 }
/*      */                 else
/*      */                 {
/* 1198 */                   localAccessor.prefetchedLobData[n] = ((byte[])(byte[])arrayOfObject[(m + n)]);
/*      */                   
/* 1200 */                   if (localAccessor.prefetchedLobData[n] != null) {
/* 1201 */                     localAccessor.prefetchedLobDataL[n] = localAccessor.prefetchedLobData[n].length;
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/* 1206 */             i++;
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
/* 1219 */     if (!this.needToPrepareDefineBuffer) {
/* 1220 */       throw new Error("doDefineFetch called when needToPrepareDefineBuffer=false " + this.sqlObject.getSql(this.processEscapes, this.convertNcharLiterals));
/*      */     }
/*      */     
/* 1223 */     setupForDefine();
/*      */     
/* 1225 */     this.t2cOutput[2] = 0L;
/* 1226 */     this.t2cOutput[5] = (this.connection.useNio ? 1 : 0);
/* 1227 */     this.t2cOutput[6] = this.defaultLobPrefetchSize;
/* 1228 */     if (this.connection.useNio) {
/* 1229 */       resetNioAttributesBeforeFetch();
/* 1230 */       allocateNioBuffersIfRequired(this.defineChars == null ? 0 : this.defineChars.length, this.defineBytes == null ? 0 : this.defineBytes.length, this.defineIndicators == null ? 0 : this.defineIndicators.length);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1236 */     if (this.lobPrefetchMetaData == null) {
/* 1237 */       this.lobPrefetchMetaData = getLobPrefetchMetaData();
/*      */     }
/* 1239 */     this.validRows = t2cDefineFetch(this, this.c_state, this.rowPrefetch, this.connection.queryMetaData1, this.connection.queryMetaData2, this.connection.queryMetaData1Offset, this.connection.queryMetaData2Offset, this.accessors, this.defineBytes, this.accessorByteOffset, this.defineChars, this.accessorCharOffset, this.defineIndicators, this.accessorShortOffset, this.t2cOutput, this.nioBuffers, this.lobPrefetchMetaData);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1252 */     if ((this.validRows == -1) || (this.validRows == -4)) {
/* 1253 */       this.connection.checkError(this.validRows);
/*      */     }
/*      */     
/* 1256 */     if (this.t2cOutput[2] != 0L)
/*      */     {
/* 1258 */       this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */     }
/*      */     
/*      */ 
/* 1262 */     if ((this.connection.useNio) && ((this.validRows > 0) || (this.validRows == -2)))
/*      */     {
/* 1264 */       extractNioDefineBuffers(0);
/*      */     }
/* 1266 */     if (this.lobPrefetchMetaData != null)
/*      */     {
/* 1268 */       processLobPrefetchMetaData(this.lobPrefetchMetaData);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void allocateNioBuffersIfRequired(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1278 */     if (this.nioBuffers == null) {
/* 1279 */       this.nioBuffers = new ByteBuffer[4];
/*      */     }
/* 1281 */     if (paramInt2 > 0)
/*      */     {
/* 1283 */       if ((this.nioBuffers[0] == null) || (this.nioBuffers[0].capacity() < paramInt2))
/*      */       {
/*      */ 
/* 1286 */         this.nioBuffers[0] = ByteBuffer.allocateDirect(paramInt2);
/* 1287 */       } else if (this.nioBuffers[0] != null)
/*      */       {
/* 1289 */         this.nioBuffers[0].rewind();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1296 */     paramInt1 *= 2;
/* 1297 */     if (paramInt1 > 0)
/*      */     {
/* 1299 */       if ((this.nioBuffers[1] == null) || (this.nioBuffers[1].capacity() < paramInt1))
/*      */       {
/*      */ 
/* 1302 */         this.nioBuffers[1] = ByteBuffer.allocateDirect(paramInt1);
/* 1303 */       } else if (this.nioBuffers[1] != null)
/*      */       {
/* 1305 */         this.nioBuffers[1].rewind();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1312 */     paramInt3 *= 2;
/* 1313 */     if (paramInt3 > 0)
/*      */     {
/* 1315 */       if ((this.nioBuffers[2] == null) || (this.nioBuffers[2].capacity() < paramInt3))
/*      */       {
/*      */ 
/* 1318 */         this.nioBuffers[2] = ByteBuffer.allocateDirect(paramInt3);
/* 1319 */       } else if (this.nioBuffers[2] != null)
/*      */       {
/* 1321 */         this.nioBuffers[2].rewind();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void doDefineExecuteFetch()
/*      */     throws SQLException
/*      */   {
/* 1330 */     short[] arrayOfShort = null;
/*      */     
/* 1332 */     if ((this.needToPrepareDefineBuffer) || (this.needToParse))
/*      */     {
/* 1334 */       setupForDefine();
/*      */       
/* 1336 */       arrayOfShort = this.connection.queryMetaData1;
/*      */     }
/*      */     
/* 1339 */     this.t2cOutput[0] = 0L;
/* 1340 */     this.t2cOutput[2] = 0L;
/*      */     
/* 1342 */     byte[] arrayOfByte = this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals);
/* 1343 */     this.t2cOutput[5] = (this.connection.useNio ? 1 : 0);
/* 1344 */     this.t2cOutput[6] = this.defaultLobPrefetchSize;
/* 1345 */     if (this.connection.useNio) {
/* 1346 */       resetNioAttributesBeforeFetch();
/* 1347 */       allocateNioBuffersIfRequired(this.defineChars == null ? 0 : this.defineChars.length, this.defineBytes == null ? 0 : this.defineBytes.length, this.defineIndicators == null ? 0 : this.defineIndicators.length);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1353 */     if (this.lobPrefetchMetaData == null) {
/* 1354 */       this.lobPrefetchMetaData = getLobPrefetchMetaData();
/*      */     }
/*      */     try {
/* 1357 */       this.validRows = t2cDefineExecuteFetch(this, this.c_state, this.numberOfDefinePositions, this.numberOfBindPositions, 0, 0, false, this.needToParse, arrayOfByte, arrayOfByte.length, convertSqlKindEnumToByte(this.sqlKind), this.rowPrefetch, this.batch, this.bindIndicators, this.bindIndicatorOffset, this.bindBytes, this.bindChars, this.bindByteOffset, this.bindCharOffset, arrayOfShort, this.connection.queryMetaData2, this.connection.queryMetaData1Offset, this.connection.queryMetaData2Offset, true, true, this.accessors, (byte[][][])null, this.t2cOutput, this.defineBytes, this.accessorByteOffset, this.defineChars, this.accessorCharOffset, this.defineIndicators, this.accessorShortOffset, this.nioBuffers, this.lobPrefetchMetaData);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1397 */       this.validRows = 0;
/*      */       
/* 1399 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1400 */       localSQLException.fillInStackTrace();
/* 1401 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1405 */     if (this.validRows == -1) {
/* 1406 */       this.connection.checkError(this.validRows);
/*      */     }
/* 1408 */     if (this.t2cOutput[2] != 0L) {
/* 1409 */       this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1414 */     this.connection.endToEndECIDSequenceNumber = ((short)(int)this.t2cOutput[4]);
/*      */     
/* 1416 */     if ((this.connection.useNio) && ((this.validRows > 0) || (this.validRows == -2)))
/*      */     {
/* 1418 */       extractNioDefineBuffers(0);
/*      */     }
/* 1420 */     if (this.lobPrefetchMetaData != null)
/*      */     {
/* 1422 */       processLobPrefetchMetaData(this.lobPrefetchMetaData);
/*      */     }
/*      */     
/* 1425 */     this.needToParse = false;
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
/* 1468 */     if (this.numberOfDefinePositions > 0)
/*      */     {
/* 1470 */       if (this.needToPrepareDefineBuffer) {
/* 1471 */         doDefineFetch();
/*      */       }
/*      */       else {
/* 1474 */         this.t2cOutput[2] = 0L;
/* 1475 */         this.t2cOutput[5] = (this.connection.useNio ? 1 : 0);
/* 1476 */         this.t2cOutput[6] = this.defaultLobPrefetchSize;
/* 1477 */         if (this.connection.useNio) {
/* 1478 */           resetNioAttributesBeforeFetch();
/* 1479 */           allocateNioBuffersIfRequired(this.defineChars == null ? 0 : this.defineChars.length, this.defineBytes == null ? 0 : this.defineBytes.length, this.defineIndicators == null ? 0 : this.defineIndicators.length);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1486 */         if (this.lobPrefetchMetaData == null) {
/* 1487 */           this.lobPrefetchMetaData = getLobPrefetchMetaData();
/*      */         }
/* 1489 */         this.validRows = t2cFetch(this.c_state, this.needToPrepareDefineBuffer, this.rowPrefetch, this.accessors, this.defineBytes, this.accessorByteOffset, this.defineChars, this.accessorCharOffset, this.defineIndicators, this.accessorShortOffset, this.t2cOutput, this.nioBuffers, this.lobPrefetchMetaData);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1497 */         if ((this.validRows == -1) || (this.validRows == -4)) {
/* 1498 */           this.connection.checkError(this.validRows);
/*      */         }
/* 1500 */         if (this.t2cOutput[2] != 0L)
/*      */         {
/* 1502 */           this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */         }
/*      */         
/* 1505 */         if (this.lobPrefetchMetaData != null)
/*      */         {
/* 1507 */           processLobPrefetchMetaData(this.lobPrefetchMetaData);
/*      */         }
/* 1509 */         if ((this.connection.useNio) && ((this.validRows > 0) || (this.validRows == -2)))
/*      */         {
/* 1511 */           extractNioDefineBuffers(0);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void resetNioAttributesBeforeFetch()
/*      */   {
/* 1520 */     this.extractedCharOffset = 0;
/* 1521 */     this.extractedByteOffset = 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void extractNioDefineBuffers(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1530 */     if ((this.accessors == null) || (this.defineIndicators == null) || (paramInt == this.numberOfDefinePositions))
/*      */     {
/*      */ 
/* 1533 */       return;
/*      */     }
/* 1535 */     int i = 0;
/* 1536 */     int j = 0;
/* 1537 */     int k = 0;
/* 1538 */     int m = 0;
/* 1539 */     int n = 0;
/*      */     
/*      */ 
/* 1542 */     if (!this.hasStream)
/*      */     {
/* 1544 */       i = this.defineBytes != null ? this.defineBytes.length : 0;
/* 1545 */       j = this.defineChars != null ? this.defineChars.length : 0;
/* 1546 */       k = this.defineIndicators.length;
/*      */     }
/*      */     else
/*      */     {
/* 1550 */       if (this.numberOfDefinePositions > paramInt)
/*      */       {
/* 1552 */         n = this.accessors[paramInt].indicatorIndex;
/* 1553 */         m = this.accessors[paramInt].lengthIndex;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1558 */       for (int i1 = paramInt; i1 < this.numberOfDefinePositions; i1++)
/*      */       {
/* 1560 */         switch (this.accessors[i1].internalType)
/*      */         {
/*      */         case 8: 
/*      */         case 24: 
/*      */           break;
/*      */         default: 
/* 1566 */           i += this.accessors[i1].byteLength;
/* 1567 */           j += this.accessors[i1].charLength;
/* 1568 */           k++;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/* 1573 */     ByteBuffer localByteBuffer = this.nioBuffers[0];
/* 1574 */     if ((localByteBuffer != null) && (this.defineBytes != null))
/*      */     {
/* 1576 */       if (i > 0)
/*      */       {
/* 1578 */         localByteBuffer.position(this.extractedByteOffset);
/* 1579 */         localByteBuffer.get(this.defineBytes, this.extractedByteOffset, i);
/* 1580 */         this.extractedByteOffset += i;
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
/* 1595 */     if ((this.nioBuffers[1] != null) && (this.defineChars != null))
/*      */     {
/* 1597 */       localByteBuffer = this.nioBuffers[1].order(ByteOrder.LITTLE_ENDIAN);
/* 1598 */       localObject = localByteBuffer.asCharBuffer();
/*      */       
/* 1600 */       if (j > 0)
/*      */       {
/* 1602 */         ((CharBuffer)localObject).position(this.extractedCharOffset);
/* 1603 */         ((CharBuffer)localObject).get(this.defineChars, this.extractedCharOffset, j);
/* 1604 */         this.extractedCharOffset += j;
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
/* 1623 */     if (this.nioBuffers[2] != null) {
/* 1624 */       localByteBuffer = this.nioBuffers[2].order(ByteOrder.LITTLE_ENDIAN);
/* 1625 */       localObject = localByteBuffer.asShortBuffer();
/* 1626 */       if (this.hasStream)
/*      */       {
/* 1628 */         if (k > 0)
/*      */         {
/* 1630 */           ((ShortBuffer)localObject).position(n);
/* 1631 */           ((ShortBuffer)localObject).get(this.defineIndicators, n, k);
/* 1632 */           ((ShortBuffer)localObject).position(m);
/* 1633 */           ((ShortBuffer)localObject).get(this.defineIndicators, m, k);
/*      */         }
/*      */       }
/*      */       else {
/* 1637 */         ((ShortBuffer)localObject).get(this.defineIndicators);
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
/* 1685 */     if (this.defineBytes != null)
/*      */     {
/* 1687 */       this.defineBytes = null;
/* 1688 */       this.accessorByteOffset = 0;
/*      */     }
/*      */     
/* 1691 */     if (this.defineChars != null)
/*      */     {
/* 1693 */       this.defineChars = null;
/* 1694 */       this.accessorCharOffset = 0;
/*      */     }
/*      */     
/* 1697 */     if (this.defineIndicators != null)
/*      */     {
/* 1699 */       this.defineIndicators = null;
/* 1700 */       this.accessorShortOffset = 0;
/*      */     }
/*      */     
/*      */ 
/* 1704 */     int i = t2cCloseStatement(this.c_state);
/*      */     
/* 1706 */     this.nioBuffers = null;
/*      */     
/* 1708 */     if (i != 0) {
/* 1709 */       this.connection.checkError(i);
/*      */     }
/* 1711 */     this.t2cOutput = null;
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
/* 1729 */     if (this.streamList != null)
/*      */     {
/* 1731 */       while (this.nextStream != null)
/*      */       {
/*      */         try
/*      */         {
/* 1735 */           this.nextStream.close();
/*      */ 
/*      */         }
/*      */         catch (IOException localIOException)
/*      */         {
/* 1740 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1741 */           localSQLException.fillInStackTrace();
/* 1742 */           throw localSQLException;
/*      */         }
/*      */         
/*      */ 
/* 1746 */         this.nextStream = this.nextStream.nextStream;
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
/* 1763 */     if ((paramInt1 == 116) || (paramInt1 == 102))
/*      */     {
/*      */ 
/* 1766 */       if ((paramBoolean) && (paramString != null))
/*      */       {
/* 1768 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 12, "sqlType=" + paramInt2);
/* 1769 */         localSQLException.fillInStackTrace();
/* 1770 */         throw localSQLException;
/*      */       }
/*      */       
/* 1773 */       T2CResultSetAccessor localT2CResultSetAccessor = new T2CResultSetAccessor(this, paramInt4, paramShort, paramInt2, paramBoolean);
/*      */       
/*      */ 
/* 1776 */       return localT2CResultSetAccessor;
/*      */     }
/*      */     
/* 1779 */     return super.allocateAccessor(paramInt1, paramInt2, paramInt3, paramInt4, paramShort, paramString, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */   void closeUsedStreams(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/* 1787 */     while ((this.nextStream != null) && (this.nextStream.columnIndex < paramInt))
/*      */     {
/*      */ 
/*      */       try
/*      */       {
/*      */ 
/* 1793 */         this.nextStream.close();
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException1)
/*      */       {
/* 1798 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException1);
/* 1799 */         localSQLException.fillInStackTrace();
/* 1800 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1804 */       this.nextStream = this.nextStream.nextStream;
/*      */     }
/*      */     
/* 1807 */     if (this.nextStream != null) {
/*      */       try
/*      */       {
/* 1810 */         this.nextStream.needBytes();
/*      */       }
/*      */       catch (IOException localIOException2)
/*      */       {
/* 1814 */         interalCloseOnIOException(localIOException2);
/*      */         
/* 1816 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException2);
/* 1817 */         localSQLException.fillInStackTrace();
/* 1818 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void interalCloseOnIOException(IOException paramIOException)
/*      */     throws SQLException
/*      */   {
/* 1827 */     this.closed = true;
/*      */     
/* 1829 */     if (this.currentResultSet != null) {
/* 1830 */       this.currentResultSet.closed = true;
/*      */     }
/* 1832 */     doClose();
/*      */   }
/*      */   
/*      */ 
/*      */   void fetchDmlReturnParams()
/*      */     throws SQLException
/*      */   {
/* 1839 */     this.rowsDmlReturned = t2cGetRowsDmlReturned(this.c_state);
/*      */     
/* 1841 */     if (this.rowsDmlReturned != 0)
/*      */     {
/* 1843 */       allocateDmlReturnStorage();
/*      */       
/* 1845 */       int i = t2cFetchDmlReturnParams(this.c_state, this.returnParamAccessors, this.returnParamBytes, this.returnParamChars, this.returnParamIndicators);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1853 */       if ((i == -1) || (i == -4)) {
/* 1854 */         this.connection.checkError(i);
/*      */       }
/*      */       
/* 1857 */       if (this.t2cOutput[2] != 0L)
/*      */       {
/* 1859 */         this.sqlWarning = this.connection.checkError(1, this.sqlWarning);
/*      */       }
/*      */       
/*      */ 
/* 1863 */       if ((this.connection.useNio) && ((i > 0) || (i == -2)))
/*      */       {
/* 1865 */         extractNioDefineBuffers(0);
/*      */       }
/*      */     }
/* 1868 */     this.returnParamsFetched = true;
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
/* 1879 */   static int PREAMBLE_PER_POSITION = 5;
/*      */   
/*      */ 
/*      */ 
/*      */   void initializeIndicatorSubRange()
/*      */   {
/* 1885 */     this.bindIndicatorSubRange = (this.numberOfBindPositions * PREAMBLE_PER_POSITION);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   int calculateIndicatorSubRangeSize()
/*      */   {
/* 1892 */     return this.numberOfBindPositions * PREAMBLE_PER_POSITION;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   short getInoutIndicator(int paramInt)
/*      */   {
/* 1899 */     return this.bindIndicators[(paramInt * PREAMBLE_PER_POSITION)];
/*      */   }
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
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1918 */     if (this.closed)
/*      */     {
/* 1920 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 9);
/* 1921 */       localSQLException.fillInStackTrace();
/* 1922 */       throw localSQLException;
/*      */     }
/*      */     
/* 1925 */     if (this.described == true)
/*      */     {
/* 1927 */       return;
/*      */     }
/*      */     
/* 1930 */     if (!this.isOpen)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1935 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 144);
/* 1936 */       localSQLException.fillInStackTrace();
/* 1937 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */     int i;
/*      */     
/*      */ 
/*      */     do
/*      */     {
/* 1946 */       i = 0;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1955 */       boolean bool = (this.sqlKind.isSELECT()) && (this.needToParse) && ((!this.described) || (!this.serverCursor));
/* 1956 */       byte[] arrayOfByte = bool ? this.sqlObject.getSqlBytes(this.processEscapes, this.convertNcharLiterals) : PhysicalConnection.EMPTY_BYTE_ARRAY;
/* 1957 */       this.numberOfDefinePositions = t2cDescribe(this.c_state, this.connection.queryMetaData1, this.connection.queryMetaData2, this.connection.queryMetaData1Offset, this.connection.queryMetaData2Offset, this.connection.queryMetaData1Size, this.connection.queryMetaData2Size, arrayOfByte, arrayOfByte.length, bool);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1968 */       if (!this.described) {
/* 1969 */         this.described = true;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1976 */       if (this.numberOfDefinePositions == -1)
/*      */       {
/* 1978 */         this.connection.checkError(this.numberOfDefinePositions);
/*      */       }
/*      */       
/*      */ 
/* 1982 */       if (this.numberOfDefinePositions == T2C_EXTEND_BUFFER)
/*      */       {
/* 1984 */         i = 1;
/*      */         
/*      */ 
/*      */ 
/* 1988 */         this.connection.reallocateQueryMetaData(this.connection.queryMetaData1Size * 2, this.connection.queryMetaData2Size * 2);
/*      */       }
/*      */       
/*      */     }
/* 1992 */     while (i != 0);
/*      */     
/* 1994 */     processDescribeData();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2000 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T2CStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */