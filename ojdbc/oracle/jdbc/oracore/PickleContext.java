/*      */ package oracle.jdbc.oracore;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.sql.SQLException;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class PickleContext
/*      */ {
/*      */   private PickleOutputStream outStream;
/*      */   byte[] image;
/*      */   int imageOffset;
/*      */   private byte[] lengthBuffer;
/*   45 */   static short KOPI20_LN_ELNL = 255;
/*   46 */   static short KOPI20_LN_5BLN = 254;
/*   47 */   static short KOPI20_LN_ATMN = 253;
/*   48 */   static short KOPI20_LN_IEMN = 252;
/*   49 */   static short KOPI20_LN_MAXV = 245;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   55 */   static short KOPI20_IF_IS81 = 128;
/*   56 */   static short KOPI20_IF_CMSB = 64;
/*   57 */   static short KOPI20_IF_CLSB = 32;
/*   58 */   static short KOPI20_IF_DEGN = 16;
/*   59 */   static short KOPI20_IF_COLL = 8;
/*   60 */   static short KOPI20_IF_NOPS = 4;
/*   61 */   static short KOPI20_IF_ANY = 2;
/*   62 */   static short KOPI20_IF_NONL = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   69 */   static short KOPI20_CF_CMSB = 64;
/*   70 */   static short KOPI20_CF_CLSB = 32;
/*   71 */   static short KOPI20_CF_INDX = 16;
/*   72 */   static short KOPI20_CF_NOLN = 8;
/*      */   
/*   74 */   static short KOPI20_VERSION = 1;
/*      */   
/*      */ 
/*      */   static final byte KOPUP_INLINE_COLL = 1;
/*      */   
/*      */   static final byte KOPUP_TYPEINFO_NONE = 0;
/*      */   
/*      */   static final byte KOPUP_TYPEINFO_TOID = 4;
/*      */   
/*      */   static final byte KOPUP_TYPEINFO_TOBJN = 8;
/*      */   
/*      */   static final byte KOPUP_TYPEINFO_TDS = 12;
/*      */   
/*      */   static final byte KOPUP_VSN_PRESENT = 16;
/*      */   
/*      */ 
/*      */   public PickleContext()
/*      */   {
/*   92 */     this.lengthBuffer = new byte[5];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public PickleContext(byte[] paramArrayOfByte)
/*      */   {
/*   99 */     this.lengthBuffer = new byte[5];
/*  100 */     this.image = paramArrayOfByte;
/*  101 */     this.imageOffset = 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public PickleContext(byte[] paramArrayOfByte, long paramLong)
/*      */   {
/*  108 */     this.lengthBuffer = new byte[5];
/*  109 */     this.image = paramArrayOfByte;
/*  110 */     this.imageOffset = ((int)paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void initStream(int paramInt)
/*      */   {
/*  121 */     this.outStream = new PickleOutputStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void initStream()
/*      */   {
/*  131 */     this.outStream = new PickleOutputStream();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int lengthInBytes(int paramInt)
/*      */   {
/*  143 */     return paramInt <= KOPI20_LN_MAXV ? 1 : 5;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int writeElementNull()
/*      */     throws SQLException
/*      */   {
/*  154 */     this.outStream.write(KOPI20_LN_ELNL);
/*      */     
/*  156 */     return 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int writeAtomicNull()
/*      */     throws SQLException
/*      */   {
/*  167 */     this.outStream.write(KOPI20_LN_ATMN);
/*      */     
/*  169 */     return 1;
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
/*      */   public int writeImmediatelyEmbeddedElementNull(byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  183 */     this.lengthBuffer[0] = ((byte)KOPI20_LN_IEMN);
/*  184 */     this.lengthBuffer[1] = paramByte;
/*      */     
/*  186 */     this.outStream.write(this.lengthBuffer, 0, 2);
/*      */     
/*  188 */     return 2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int writeSB2(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  200 */     this.lengthBuffer[0] = ((byte)(paramInt >> 8 & 0xFF));
/*  201 */     this.lengthBuffer[1] = ((byte)(paramInt & 0xFF));
/*  202 */     this.outStream.write(this.lengthBuffer, 0, 2);
/*      */     
/*  204 */     return 2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int writeLength(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  215 */     if (paramInt <= KOPI20_LN_MAXV)
/*      */     {
/*  217 */       this.outStream.write((byte)paramInt);
/*      */       
/*  219 */       return 1;
/*      */     }
/*      */     
/*      */ 
/*  223 */     this.lengthBuffer[0] = ((byte)KOPI20_LN_5BLN);
/*  224 */     this.lengthBuffer[1] = ((byte)(paramInt >> 24));
/*  225 */     paramInt &= 0xFFFFFF;
/*  226 */     this.lengthBuffer[2] = ((byte)(paramInt >> 16));
/*  227 */     paramInt &= 0xFFFF;
/*  228 */     this.lengthBuffer[3] = ((byte)(paramInt >> 8));
/*  229 */     paramInt &= 0xFF;
/*  230 */     this.lengthBuffer[4] = ((byte)paramInt);
/*      */     
/*      */     try
/*      */     {
/*  234 */       this.outStream.write(this.lengthBuffer);
/*      */ 
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*      */ 
/*  241 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  242 */       localSQLException.fillInStackTrace();
/*  243 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  247 */     return 5;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int writeLength(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  259 */     if (!paramBoolean)
/*  260 */       return writeLength(paramInt);
/*  261 */     if (paramInt <= KOPI20_LN_MAXV - 1)
/*      */     {
/*  263 */       this.outStream.write((byte)paramInt + 1);
/*      */       
/*  265 */       return 1;
/*      */     }
/*      */     
/*      */ 
/*  269 */     paramInt += 5;
/*  270 */     this.lengthBuffer[0] = ((byte)KOPI20_LN_5BLN);
/*  271 */     this.lengthBuffer[1] = ((byte)(paramInt >> 24));
/*  272 */     paramInt &= 0xFFFFFF;
/*  273 */     this.lengthBuffer[2] = ((byte)(paramInt >> 16));
/*  274 */     paramInt &= 0xFFFF;
/*  275 */     this.lengthBuffer[3] = ((byte)(paramInt >> 8));
/*  276 */     paramInt &= 0xFF;
/*  277 */     this.lengthBuffer[4] = ((byte)paramInt);
/*      */     
/*      */     try
/*      */     {
/*  281 */       this.outStream.write(this.lengthBuffer);
/*      */ 
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*      */ 
/*  288 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  289 */       localSQLException.fillInStackTrace();
/*  290 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  294 */     return 5;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] to5bLengthBytes_pctx(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  306 */     this.lengthBuffer[0] = ((byte)KOPI20_LN_5BLN);
/*  307 */     this.lengthBuffer[1] = ((byte)(paramInt >> 24));
/*  308 */     paramInt &= 0xFFFFFF;
/*  309 */     this.lengthBuffer[2] = ((byte)(paramInt >> 16));
/*  310 */     paramInt &= 0xFFFF;
/*  311 */     this.lengthBuffer[3] = ((byte)(paramInt >> 8));
/*  312 */     paramInt &= 0xFF;
/*  313 */     this.lengthBuffer[4] = ((byte)paramInt);
/*      */     
/*  315 */     return this.lengthBuffer;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int writeData(byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  326 */     this.outStream.write(paramByte);
/*      */     
/*  328 */     return 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int writeData(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/*  340 */       this.outStream.write(paramArrayOfByte);
/*      */ 
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*      */ 
/*  347 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  348 */       localSQLException.fillInStackTrace();
/*  349 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  353 */     return paramArrayOfByte.length;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void patchImageLen(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  363 */     byte[] arrayOfByte = to5bLengthBytes_pctx(paramInt2);
/*      */     
/*  365 */     this.outStream.overwrite(paramInt1, arrayOfByte, 0, arrayOfByte.length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int writeImageHeader(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  378 */     return writeImageHeader(KOPI20_LN_MAXV + 1, paramBoolean);
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
/*      */   public int writeOpaqueImageHeader(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  393 */     int i = 2;
/*      */     
/*  395 */     this.lengthBuffer[0] = ((byte)(KOPI20_IF_IS81 | KOPI20_IF_NOPS | KOPI20_IF_NONL));
/*  396 */     this.lengthBuffer[1] = ((byte)KOPI20_VERSION);
/*      */     
/*  398 */     this.outStream.write(this.lengthBuffer, 0, 2);
/*      */     
/*  400 */     i += writeLength(paramInt + 2, true);
/*      */     
/*  402 */     return i;
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
/*      */   public int writeImageHeader(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  417 */     int i = 2;
/*      */     
/*  419 */     if (paramBoolean) {
/*  420 */       this.lengthBuffer[0] = ((byte)KOPI20_IF_IS81);
/*      */     } else {
/*  422 */       this.lengthBuffer[0] = ((byte)(KOPI20_IF_IS81 | KOPI20_IF_NOPS));
/*      */     }
/*  424 */     this.lengthBuffer[1] = ((byte)KOPI20_VERSION);
/*      */     
/*  426 */     this.outStream.write(this.lengthBuffer, 0, 2);
/*      */     
/*  428 */     i += writeLength(paramInt);
/*      */     
/*  430 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int writeCollImageHeader(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  443 */     return writeCollImageHeader(KOPI20_LN_MAXV + 1, paramInt1, paramInt2);
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
/*      */   public int writeCollImageHeader(int paramInt1, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  459 */     int i = 5;
/*      */     
/*  461 */     this.lengthBuffer[0] = ((byte)(KOPI20_IF_IS81 | KOPI20_IF_COLL));
/*  462 */     this.lengthBuffer[1] = ((byte)KOPI20_VERSION);
/*      */     
/*  464 */     this.outStream.write(this.lengthBuffer, 0, 2);
/*      */     
/*  466 */     i += writeLength(paramInt1);
/*      */     
/*  468 */     this.lengthBuffer[0] = 1;
/*      */     
/*  470 */     this.lengthBuffer[1] = 17;
/*      */     
/*  472 */     if (paramInt3 > KOPI20_LN_MAXV)
/*      */     {
/*  474 */       int tmp77_76 = 0; byte[] tmp77_73 = this.lengthBuffer;tmp77_73[tmp77_76] = ((byte)(tmp77_73[tmp77_76] + 5));
/*  475 */       i += 5;
/*      */       
/*      */ 
/*  478 */       this.outStream.write(this.lengthBuffer, 0, 2);
/*  479 */       writeLength(paramInt3);
/*      */     }
/*      */     else
/*      */     {
/*  483 */       int tmp113_112 = 0; byte[] tmp113_109 = this.lengthBuffer;tmp113_109[tmp113_112] = ((byte)(tmp113_109[tmp113_112] + 2));
/*  484 */       i += 2;
/*      */       
/*  486 */       this.outStream.write(this.lengthBuffer, 0, 2);
/*  487 */       writeSB2(paramInt3);
/*      */     }
/*      */     
/*  490 */     this.lengthBuffer[0] = 0;
/*      */     
/*  492 */     this.outStream.write(this.lengthBuffer, 0, 1);
/*      */     
/*  494 */     i += writeLength(paramInt2);
/*      */     
/*  496 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */   public int writeCollImageHeader(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  503 */     return writeCollImageHeader(KOPI20_LN_MAXV + 1, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int writeCollImageHeader(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  511 */     int i = paramArrayOfByte.length;
/*      */     
/*      */ 
/*  514 */     int j = 3 + i;
/*      */     
/*  516 */     this.lengthBuffer[0] = ((byte)(KOPI20_IF_IS81 | KOPI20_IF_DEGN));
/*  517 */     this.lengthBuffer[1] = ((byte)KOPI20_VERSION);
/*      */     
/*  519 */     this.outStream.write(this.lengthBuffer, 0, 2);
/*      */     
/*  521 */     j += writeLength(paramInt);
/*  522 */     j += writeLength(i + 1);
/*      */     
/*  524 */     this.lengthBuffer[0] = 0;
/*      */     
/*  526 */     this.outStream.write(this.lengthBuffer, 0, 1);
/*  527 */     this.outStream.write(paramArrayOfByte, 0, i);
/*      */     
/*  529 */     return j;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] stream2Bytes()
/*      */     throws SQLException
/*      */   {
/*  539 */     return this.outStream.toByteArray();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte readByte()
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/*  553 */       return this.image[this.imageOffset];
/*      */     }
/*      */     finally
/*      */     {
/*  557 */       this.imageOffset += 1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean readAndCheckVersion()
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/*  572 */       return (this.image[this.imageOffset] & 0xFF) <= KOPI20_VERSION;
/*      */     }
/*      */     finally
/*      */     {
/*  576 */       this.imageOffset += 1;
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
/*      */   public int readLength()
/*      */     throws SQLException
/*      */   {
/*  593 */     int i = this.image[this.imageOffset] & 0xFF;
/*      */     
/*  595 */     if (i > KOPI20_LN_MAXV)
/*      */     {
/*  597 */       if (i == KOPI20_LN_ELNL)
/*      */       {
/*      */ 
/*  600 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Invalid null flag read");
/*  601 */         localSQLException.fillInStackTrace();
/*  602 */         throw localSQLException;
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
/*  619 */       i = (((this.image[(this.imageOffset + 1)] & 0xFF) * 256 + (this.image[(this.imageOffset + 2)] & 0xFF)) * 256 + (this.image[(this.imageOffset + 3)] & 0xFF)) * 256 + (this.image[(this.imageOffset + 4)] & 0xFF);
/*      */       
/*      */ 
/*      */ 
/*  623 */       this.imageOffset += 5;
/*      */     }
/*      */     else
/*      */     {
/*  627 */       this.imageOffset += 1; }
/*  628 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */   public void skipLength()
/*      */     throws SQLException
/*      */   {
/*  635 */     int i = this.image[this.imageOffset] & 0xFF;
/*      */     
/*  637 */     if (i > KOPI20_LN_MAXV) {
/*  638 */       this.imageOffset += 5;
/*      */     } else {
/*  640 */       this.imageOffset += 1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int readRestOfLength(byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  652 */     if ((paramByte & 0xFF) != KOPI20_LN_5BLN) {
/*  653 */       return paramByte & 0xFF;
/*      */     }
/*      */     try
/*      */     {
/*  657 */       return (((this.image[this.imageOffset] & 0xFF) * 256 + (this.image[(this.imageOffset + 1)] & 0xFF)) * 256 + (this.image[(this.imageOffset + 2)] & 0xFF)) * 256 + (this.image[(this.imageOffset + 3)] & 0xFF);
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*  662 */       this.imageOffset += 4;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void skipRestOfLength(byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  670 */     if ((paramByte & 0xFF) > KOPI20_LN_MAXV)
/*      */     {
/*  672 */       if ((paramByte & 0xFF) == KOPI20_LN_5BLN) {
/*  673 */         this.imageOffset += 4;
/*      */       }
/*      */       else {
/*  676 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Invalid first length byte");
/*  677 */         localSQLException.fillInStackTrace();
/*  678 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int readLength(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  689 */     int i = this.image[this.imageOffset] & 0xFF;
/*      */     
/*  691 */     if (i > KOPI20_LN_MAXV)
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
/*  708 */       i = (((this.image[(this.imageOffset + 1)] & 0xFF) * 256 + (this.image[(this.imageOffset + 2)] & 0xFF)) * 256 + (this.image[(this.imageOffset + 3)] & 0xFF)) * 256 + (this.image[(this.imageOffset + 4)] & 0xFF);
/*      */       
/*      */ 
/*      */ 
/*  712 */       if (paramBoolean) {
/*  713 */         i -= 5;
/*      */       }
/*  715 */       this.imageOffset += 5;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  720 */       if (paramBoolean) {
/*  721 */         i--;
/*      */       }
/*  723 */       this.imageOffset += 1;
/*      */     }
/*      */     
/*  726 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] readPrefixSegment()
/*      */     throws SQLException
/*      */   {
/*  737 */     byte[] arrayOfByte = new byte[readLength()];
/*      */     
/*  739 */     System.arraycopy(this.image, this.imageOffset, arrayOfByte, 0, arrayOfByte.length);
/*      */     
/*  741 */     this.imageOffset += arrayOfByte.length;
/*  742 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] readDataValue()
/*      */     throws SQLException
/*      */   {
/*  754 */     int i = this.image[this.imageOffset] & 0xFF;
/*      */     
/*      */ 
/*  757 */     if (i == KOPI20_LN_ELNL)
/*      */     {
/*  759 */       this.imageOffset += 1;
/*      */       
/*  761 */       return null;
/*      */     }
/*      */     
/*  764 */     if (i > KOPI20_LN_MAXV)
/*      */     {
/*  766 */       i = (((this.image[(this.imageOffset + 1)] & 0xFF) * 256 + (this.image[(this.imageOffset + 2)] & 0xFF)) * 256 + (this.image[(this.imageOffset + 3)] & 0xFF)) * 256 + (this.image[(this.imageOffset + 4)] & 0xFF);
/*      */       
/*      */ 
/*      */ 
/*  770 */       this.imageOffset += 5;
/*      */     }
/*      */     else {
/*  773 */       this.imageOffset += 1;
/*      */     }
/*      */     
/*  776 */     byte[] arrayOfByte = new byte[i];
/*      */     
/*  778 */     System.arraycopy(this.image, this.imageOffset, arrayOfByte, 0, arrayOfByte.length);
/*      */     
/*  780 */     this.imageOffset += arrayOfByte.length;
/*      */     
/*  782 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */   public byte[] readBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  789 */     byte[] arrayOfByte = new byte[paramInt];
/*      */     
/*  791 */     System.arraycopy(this.image, this.imageOffset, arrayOfByte, 0, paramInt);
/*      */     
/*  793 */     this.imageOffset += paramInt;
/*  794 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] read1ByteDataValue()
/*      */     throws SQLException
/*      */   {
/*  807 */     if ((this.image[this.imageOffset] & 0xFF) == KOPI20_LN_ELNL) {
/*  808 */       return null;
/*      */     }
/*      */     
/*      */ 
/*  812 */     byte[] arrayOfByte = new byte[this.image[this.imageOffset] & 0xFF];
/*      */     
/*  814 */     System.arraycopy(this.image, this.imageOffset + 1, arrayOfByte, 0, arrayOfByte.length);
/*      */     
/*  816 */     this.imageOffset += arrayOfByte.length + 1;
/*  817 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] readDataValue(byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  829 */     byte[] arrayOfByte = new byte[readRestOfLength(paramByte)];
/*      */     
/*  831 */     System.arraycopy(this.image, this.imageOffset, arrayOfByte, 0, arrayOfByte.length);
/*      */     
/*  833 */     this.imageOffset += arrayOfByte.length;
/*      */     
/*  835 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] readDataValue(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  845 */     byte[] arrayOfByte = new byte[paramInt];
/*      */     
/*  847 */     System.arraycopy(this.image, this.imageOffset, arrayOfByte, 0, paramInt);
/*      */     
/*  849 */     this.imageOffset += paramInt;
/*  850 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public long readUB4()
/*      */     throws SQLException
/*      */   {
/*  858 */     long l = this.image[(this.imageOffset++)] << 24 & 0xFFFFFFFFFF000000 | this.image[(this.imageOffset++)] << 16 & 0xFF0000 | this.image[(this.imageOffset++)] << 8 & 0xFF00 | this.image[(this.imageOffset++)] & 0xFF;
/*      */     
/*      */ 
/*      */ 
/*  862 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */   public int readUB2()
/*      */     throws SQLException
/*      */   {
/*  869 */     int i = this.image[(this.imageOffset++)] << 8 & 0xFF00 | this.image[(this.imageOffset++)] & 0xFF;
/*      */     
/*  871 */     return i;
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
/*      */   public void skipDataValue()
/*      */     throws SQLException
/*      */   {
/*  885 */     if ((this.image[this.imageOffset] & 0xFF) == KOPI20_LN_ELNL) {
/*  886 */       this.imageOffset += 1;
/*      */     } else {
/*  888 */       skipBytes(readLength());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void skipDataValue(byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  900 */     skipBytes(readRestOfLength(paramByte));
/*      */   }
/*      */   
/*      */ 
/*      */   public void skipBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  907 */     if (paramInt > 0) {
/*  908 */       this.imageOffset += paramInt;
/*      */     }
/*      */   }
/*      */   
/*      */   public int offset()
/*      */     throws SQLException
/*      */   {
/*  915 */     if (this.outStream != null) {
/*  916 */       return this.outStream.offset();
/*      */     }
/*  918 */     return this.imageOffset;
/*      */   }
/*      */   
/*      */ 
/*      */   public int absoluteOffset()
/*      */     throws SQLException
/*      */   {
/*  925 */     return this.imageOffset;
/*      */   }
/*      */   
/*      */ 
/*      */   public void skipTo(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  932 */     if (paramLong > this.imageOffset) {
/*  933 */       this.imageOffset = ((int)paramLong);
/*      */     }
/*      */   }
/*      */   
/*      */   public byte[] image()
/*      */     throws SQLException
/*      */   {
/*  940 */     return this.image;
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
/*      */   public static boolean is81format(byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  954 */     return (paramByte & 0xFF & KOPI20_IF_IS81) != 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isCollectionImage_pctx(byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  965 */     return (paramByte & 0xFF & KOPI20_IF_COLL) != 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isDegenerateImage_pctx(byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  976 */     return (paramByte & 0xFF & KOPI20_IF_DEGN) != 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean hasPrefix(byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  987 */     return (paramByte & 0xFF & KOPI20_IF_NOPS) == 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isAtomicNull(byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  997 */     return (paramByte & 0xFF) == KOPI20_LN_ATMN;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isImmediatelyEmbeddedNull(byte paramByte)
/*      */     throws SQLException
/*      */   {
/* 1007 */     return (paramByte & 0xFF) == KOPI20_LN_IEMN;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isElementNull(byte paramByte)
/*      */     throws SQLException
/*      */   {
/* 1017 */     return (paramByte & 0xFF) == KOPI20_LN_ELNL;
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
/* 1032 */     return null;
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
/* 1068 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/PickleContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */