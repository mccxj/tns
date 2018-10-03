/*      */ package oracle.sql;
/*      */ 
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.Reader;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Connection;
/*      */ import java.sql.SQLException;
/*      */ import oracle.jdbc.LargeObjectAccessMode;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.internal.OracleBlob;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BLOB
/*      */   extends DatumWithConnection
/*      */   implements OracleBlob
/*      */ {
/*      */   public static final int MAX_CHUNK_SIZE = 32768;
/*      */   public static final int DURATION_SESSION = 10;
/*      */   public static final int DURATION_CALL = 12;
/*      */   static final int OLD_WRONG_DURATION_SESSION = 1;
/*      */   static final int OLD_WRONG_DURATION_CALL = 2;
/*      */   public static final int MODE_READONLY = 0;
/*      */   public static final int MODE_READWRITE = 1;
/*      */   BlobDBAccess dbaccess;
/*   60 */   int dbChunkSize = -1;
/*   61 */   boolean isFree = false;
/*      */   
/*      */ 
/*   64 */   boolean fromObject = false;
/*      */   
/*      */ 
/*   67 */   private long cachedLobLength = -1L;
/*      */   private byte[] prefetchData;
/*   69 */   private int prefetchDataSize = 0;
/*   70 */   private boolean activePrefetch = false;
/*      */   
/*      */ 
/*      */   static final int KDLCTLSIZE = 16;
/*      */   
/*      */ 
/*      */   static final int KDF_FLAG = 88;
/*      */   
/*      */ 
/*      */   static final int KDLIDDAT = 8;
/*      */   
/*      */ 
/*      */   protected BLOB() {}
/*      */   
/*      */ 
/*      */   public BLOB(oracle.jdbc.OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*   88 */     this(paramOracleConnection, null);
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
/*      */   public BLOB(oracle.jdbc.OracleConnection paramOracleConnection, byte[] paramArrayOfByte, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  103 */     this(paramOracleConnection, paramArrayOfByte);
/*      */     
/*  105 */     this.fromObject = paramBoolean;
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
/*      */   public BLOB(oracle.jdbc.OracleConnection paramOracleConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  119 */     super(paramArrayOfByte);
/*      */     
/*  121 */     assertNotNull(paramOracleConnection);
/*  122 */     setPhysicalConnectionOf(paramOracleConnection);
/*      */     
/*  124 */     this.dbaccess = getPhysicalConnection().createBlobDBAccess();
/*      */     
/*      */ 
/*  127 */     this.dbaccess.incrementTempLobReferenceCount(paramArrayOfByte);
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
/*      */   public long length()
/*      */     throws SQLException
/*      */   {
/*  142 */     if (this.isFree) {
/*  143 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  144 */       localSQLException.fillInStackTrace();
/*  145 */       throw localSQLException;
/*      */     }
/*  147 */     long l = -1L;
/*      */     
/*      */ 
/*      */ 
/*  151 */     if ((this.activePrefetch) && (this.cachedLobLength != -1L)) {
/*  152 */       l = this.cachedLobLength;
/*  153 */     } else if (canReadBasicLobDataInLocator()) {
/*  154 */       l = dilLength();
/*      */     } else
/*  156 */       l = getDBAccess().length(this);
/*  157 */     return l;
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
/*      */   public byte[] getBytes(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  181 */     if (this.isFree) {
/*  182 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  183 */       ((SQLException)localObject).fillInStackTrace();
/*  184 */       throw ((Throwable)localObject);
/*      */     }
/*  186 */     if ((paramInt < 0) || (paramLong < 1L))
/*      */     {
/*      */ 
/*  189 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "getBytes()");
/*  190 */       ((SQLException)localObject).fillInStackTrace();
/*  191 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  195 */     if (canReadBasicLobDataInLocator())
/*      */     {
/*  197 */       return dilGetBytes(paramLong, paramInt);
/*      */     }
/*      */     
/*  200 */     Object localObject = null;
/*      */     
/*  202 */     if (paramInt == 0) {
/*  203 */       return new byte[0];
/*      */     }
/*  205 */     if ((this.activePrefetch) && ((this.cachedLobLength == 0L) || ((this.cachedLobLength > 0L) && (paramLong - 1L >= this.cachedLobLength))))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  211 */       localObject = null;
/*      */     }
/*      */     else {
/*  214 */       long l = 0L;
/*      */       byte[] arrayOfByte;
/*  216 */       if ((this.activePrefetch) && (this.cachedLobLength != -1L)) {
/*  217 */         arrayOfByte = new byte[Math.min((int)this.cachedLobLength, paramInt)];
/*      */       } else {
/*  219 */         arrayOfByte = new byte[paramInt];
/*      */       }
/*      */       
/*  222 */       l = getBytes(paramLong, paramInt, arrayOfByte);
/*      */       
/*  224 */       if (l > 0L)
/*      */       {
/*  226 */         if (l == paramInt)
/*      */         {
/*  228 */           localObject = arrayOfByte;
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*  235 */           localObject = new byte[(int)l];
/*      */           
/*  237 */           System.arraycopy(arrayOfByte, 0, localObject, 0, (int)l);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  242 */     return (byte[])localObject;
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
/*      */   public InputStream getBinaryStream()
/*      */     throws SQLException
/*      */   {
/*  256 */     if (this.isFree) {
/*  257 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  258 */       localSQLException.fillInStackTrace();
/*  259 */       throw localSQLException;
/*      */     }
/*  261 */     if (canReadBasicLobDataInLocator())
/*      */     {
/*  263 */       return dilGetBinaryStream(1L);
/*      */     }
/*  265 */     return getDBAccess().newInputStream(this, getBufferSize(), 0L);
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
/*      */   public long position(byte[] paramArrayOfByte, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  283 */     if (this.isFree) {
/*  284 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  285 */       localSQLException.fillInStackTrace();
/*  286 */       throw localSQLException;
/*      */     }
/*  288 */     return getDBAccess().position(this, paramArrayOfByte, paramLong);
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
/*      */   public long position(Blob paramBlob, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  306 */     if (this.isFree) {
/*  307 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  308 */       localSQLException.fillInStackTrace();
/*  309 */       throw localSQLException;
/*      */     }
/*  311 */     return getDBAccess().position(this, (BLOB)paramBlob, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getBytes(long paramLong, int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  331 */     if (this.isFree) {
/*  332 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  333 */       localSQLException.fillInStackTrace();
/*  334 */       throw localSQLException;
/*      */     }
/*  336 */     if ((paramInt < 0) || (paramLong < 0L))
/*      */     {
/*      */ 
/*  339 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "getBytes()");
/*  340 */       localSQLException.fillInStackTrace();
/*  341 */       throw localSQLException;
/*      */     }
/*      */     
/*  344 */     if (paramArrayOfByte.length < paramInt)
/*      */     {
/*  346 */       paramInt = paramArrayOfByte.length;
/*      */     }
/*  348 */     return getDBAccess().getBytes(this, paramLong, paramInt, paramArrayOfByte);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public int putBytes(long paramLong, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  367 */     if (this.isFree) {
/*  368 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  369 */       localSQLException.fillInStackTrace();
/*  370 */       throw localSQLException;
/*      */     }
/*  372 */     return setBytes(paramLong, paramArrayOfByte);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public int putBytes(long paramLong, byte[] paramArrayOfByte, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  394 */     if (this.isFree) {
/*  395 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  396 */       localSQLException.fillInStackTrace();
/*  397 */       throw localSQLException;
/*      */     }
/*  399 */     return setBytes(paramLong, paramArrayOfByte, 0, paramInt);
/*      */   }
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
/*      */   public OutputStream getBinaryOutputStream()
/*      */     throws SQLException
/*      */   {
/*  415 */     if (this.isFree) {
/*  416 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  417 */       localSQLException.fillInStackTrace();
/*  418 */       throw localSQLException;
/*      */     }
/*  420 */     return setBinaryStream(1L);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] getLocator()
/*      */   {
/*  431 */     return getBytes();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLocator(byte[] paramArrayOfByte)
/*      */   {
/*  442 */     setBytes(paramArrayOfByte);
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
/*      */   public int getChunkSize()
/*      */     throws SQLException
/*      */   {
/*  456 */     if (this.isFree) {
/*  457 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  458 */       localSQLException.fillInStackTrace();
/*  459 */       throw localSQLException;
/*      */     }
/*  461 */     if (this.dbChunkSize <= 0)
/*      */     {
/*  463 */       this.dbChunkSize = getDBAccess().getChunkSize(this);
/*      */     }
/*      */     
/*  466 */     return this.dbChunkSize;
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
/*      */   public int getBufferSize()
/*      */     throws SQLException
/*      */   {
/*  480 */     if (this.isFree) {
/*  481 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  482 */       localSQLException.fillInStackTrace();
/*  483 */       throw localSQLException;
/*      */     }
/*  485 */     int i = getChunkSize();
/*  486 */     int j = i;
/*      */     
/*  488 */     if ((i >= 32768) || (i <= 0))
/*      */     {
/*  490 */       j = 32768;
/*      */     }
/*      */     else
/*      */     {
/*  494 */       j = 32768 / i * i;
/*      */     }
/*      */     
/*  497 */     return j;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static BLOB empty_lob()
/*      */     throws SQLException
/*      */   {
/*  514 */     return getEmptyBLOB();
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
/*      */   public static BLOB getEmptyBLOB()
/*      */     throws SQLException
/*      */   {
/*  545 */     byte[] arrayOfByte = new byte[86];
/*      */     
/*  547 */     arrayOfByte[1] = 84;
/*  548 */     arrayOfByte[5] = 24;
/*      */     
/*      */ 
/*      */ 
/*  552 */     BLOB localBLOB = new BLOB();
/*      */     
/*  554 */     localBLOB.setShareBytes(arrayOfByte);
/*      */     
/*  556 */     return localBLOB;
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
/*      */   public boolean isEmptyLob()
/*      */     throws SQLException
/*      */   {
/*  578 */     if (this.isFree) {
/*  579 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  580 */       localSQLException.fillInStackTrace();
/*  581 */       throw localSQLException;
/*      */     }
/*  583 */     boolean bool = (shareBytes()[5] & 0x10) != 0;
/*      */     
/*  585 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isSecureFile()
/*      */     throws SQLException
/*      */   {
/*  597 */     if (this.isFree) {
/*  598 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  599 */       localSQLException.fillInStackTrace();
/*  600 */       throw localSQLException;
/*      */     }
/*  602 */     boolean bool = (shareBytes()[7] & 0xFFFFFF80) != 0;
/*  603 */     return bool;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public OutputStream getBinaryOutputStream(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  620 */     if (this.isFree) {
/*  621 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  622 */       localSQLException.fillInStackTrace();
/*  623 */       throw localSQLException;
/*      */     }
/*  625 */     return getDBAccess().newOutputStream(this, getBufferSize(), paramLong, false);
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
/*      */   public InputStream getBinaryStream(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  640 */     if (this.isFree) {
/*  641 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  642 */       localSQLException.fillInStackTrace();
/*  643 */       throw localSQLException;
/*      */     }
/*  645 */     if (canReadBasicLobDataInLocator())
/*      */     {
/*  647 */       return dilGetBinaryStream(paramLong);
/*      */     }
/*  649 */     return getDBAccess().newInputStream(this, getBufferSize(), paramLong);
/*      */   }
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
/*      */   public void trim(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  665 */     if (this.isFree) {
/*  666 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  667 */       localSQLException.fillInStackTrace();
/*  668 */       throw localSQLException;
/*      */     }
/*  670 */     truncate(paramLong);
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
/*      */   public static BLOB createTemporary(Connection paramConnection, boolean paramBoolean, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  687 */     int i = paramInt;
/*      */     
/*  689 */     if (paramInt == 1) {
/*  690 */       i = 10;
/*      */     }
/*  692 */     if (paramInt == 2) {
/*  693 */       i = 12;
/*      */     }
/*  695 */     if ((paramConnection == null) || ((i != 10) && (i != 12)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  700 */       localObject = DatabaseError.createSqlException(null, 68, "'conn' should not be null and 'duration' should either be equal to DURATION_SESSION or to DURATION_CALL");
/*      */       
/*      */ 
/*  703 */       ((SQLException)localObject).fillInStackTrace();
/*  704 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  708 */     Object localObject = ((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin();
/*      */     
/*      */ 
/*  711 */     return getDBAccess((Connection)localObject).createTemporaryBlob((Connection)localObject, paramBoolean, i);
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
/*      */   public static void freeTemporary(BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/*  725 */     if (paramBLOB == null) {
/*  726 */       return;
/*      */     }
/*  728 */     paramBLOB.freeTemporary();
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
/*      */   public static boolean isTemporary(BLOB paramBLOB)
/*      */     throws SQLException
/*      */   {
/*  744 */     if (paramBLOB == null) {
/*  745 */       return false;
/*      */     }
/*  747 */     return paramBLOB.isTemporary();
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
/*      */   public void freeTemporary()
/*      */     throws SQLException
/*      */   {
/*  761 */     if (this.isFree) {
/*  762 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  763 */       localSQLException.fillInStackTrace();
/*  764 */       throw localSQLException;
/*      */     }
/*      */     
/*  767 */     int i = getDBAccess().decrementTempLobReferenceCount(shareBytes());
/*      */     
/*  769 */     if (i == 0) {
/*  770 */       getDBAccess().freeTemporary(this, this.fromObject);
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
/*      */   public boolean isTemporary()
/*      */     throws SQLException
/*      */   {
/*  785 */     if (this.isFree) {
/*  786 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  787 */       localSQLException.fillInStackTrace();
/*  788 */       throw localSQLException;
/*      */     }
/*  790 */     return getDBAccess().isTemporary(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void open(LargeObjectAccessMode paramLargeObjectAccessMode)
/*      */     throws SQLException
/*      */   {
/*  799 */     open(paramLargeObjectAccessMode.getCode());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void open(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  810 */     if (this.isFree) {
/*  811 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  812 */       localSQLException.fillInStackTrace();
/*  813 */       throw localSQLException;
/*      */     }
/*  815 */     getDBAccess().open(this, paramInt);
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
/*  826 */     if (this.isFree) {
/*  827 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  828 */       localSQLException.fillInStackTrace();
/*  829 */       throw localSQLException;
/*      */     }
/*  831 */     getDBAccess().close(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isOpen()
/*      */     throws SQLException
/*      */   {
/*  843 */     if (this.isFree) {
/*  844 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  845 */       localSQLException.fillInStackTrace();
/*  846 */       throw localSQLException;
/*      */     }
/*  848 */     return getDBAccess().isOpen(this);
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
/*      */   public int setBytes(long paramLong, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  874 */     if (this.isFree) {
/*  875 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  876 */       localSQLException.fillInStackTrace();
/*  877 */       throw localSQLException;
/*      */     }
/*      */     
/*  880 */     return getDBAccess().putBytes(this, paramLong, paramArrayOfByte, 0, paramArrayOfByte != null ? paramArrayOfByte.length : 0);
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
/*      */   public int setBytes(long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  911 */     if (this.isFree) {
/*  912 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  913 */       localSQLException.fillInStackTrace();
/*  914 */       throw localSQLException;
/*      */     }
/*  916 */     return getDBAccess().putBytes(this, paramLong, paramArrayOfByte, paramInt1, paramInt2);
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
/*      */   public OutputStream setBinaryStream(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  939 */     if (this.isFree) {
/*  940 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  941 */       localSQLException.fillInStackTrace();
/*  942 */       throw localSQLException;
/*      */     }
/*  944 */     return getDBAccess().newOutputStream(this, getBufferSize(), paramLong, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void truncate(long paramLong)
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  963 */     if (this.isFree) {
/*  964 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  965 */       localSQLException.fillInStackTrace();
/*  966 */       throw localSQLException;
/*      */     }
/*  968 */     if (paramLong < 0L)
/*      */     {
/*      */ 
/*  971 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "'len' should be >= 0. ");
/*  972 */       localSQLException.fillInStackTrace();
/*  973 */       throw localSQLException;
/*      */     }
/*      */     
/*  976 */     getDBAccess().trim(this, paramLong);
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
/*      */   public Object toJdbc()
/*      */     throws SQLException
/*      */   {
/* 1000 */     if (this.isFree) {
/* 1001 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1002 */       localSQLException.fillInStackTrace();
/* 1003 */       throw localSQLException;
/*      */     }
/* 1005 */     return this;
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
/*      */   public boolean isConvertibleTo(Class paramClass)
/*      */   {
/* 1025 */     String str = paramClass.getName();
/*      */     
/* 1027 */     return (str.compareTo("java.io.InputStream") == 0) || (str.compareTo("java.io.Reader") == 0);
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
/*      */   public Reader characterStreamValue()
/*      */     throws SQLException
/*      */   {
/* 1042 */     getInternalConnection();return getDBAccess().newConversionReader(this, 8);
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
/*      */   public InputStream asciiStreamValue()
/*      */     throws SQLException
/*      */   {
/* 1057 */     getInternalConnection();return getDBAccess().newConversionInputStream(this, 2);
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
/*      */   public InputStream binaryStreamValue()
/*      */     throws SQLException
/*      */   {
/* 1072 */     return getBinaryStream();
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
/*      */   public Object makeJdbcArray(int paramInt)
/*      */   {
/* 1094 */     return new BLOB[paramInt];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public BlobDBAccess getDBAccess()
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/* 1105 */     if (this.dbaccess == null)
/*      */     {
/* 1107 */       if (isEmptyLob())
/*      */       {
/*      */ 
/* 1110 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 98);
/* 1111 */         localSQLException.fillInStackTrace();
/* 1112 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1116 */       this.dbaccess = getInternalConnection().createBlobDBAccess();
/*      */     }
/*      */     
/* 1119 */     if (getPhysicalConnection().isClosed())
/*      */     {
/* 1121 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 1122 */       localSQLException.fillInStackTrace();
/* 1123 */       throw localSQLException;
/*      */     }
/*      */     
/* 1126 */     return this.dbaccess;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static BlobDBAccess getDBAccess(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/* 1137 */     return ((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin().createBlobDBAccess();
/*      */   }
/*      */   
/*      */ 
/*      */   public Connection getJavaSqlConnection()
/*      */     throws SQLException
/*      */   {
/* 1144 */     if (this.isFree) {
/* 1145 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1146 */       localSQLException.fillInStackTrace();
/* 1147 */       throw localSQLException;
/*      */     }
/* 1149 */     return super.getJavaSqlConnection();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final void setLength(long paramLong)
/*      */   {
/* 1160 */     this.cachedLobLength = paramLong;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final void setChunkSize(int paramInt)
/*      */   {
/* 1170 */     this.dbChunkSize = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final void setPrefetchedData(byte[] paramArrayOfByte)
/*      */   {
/* 1181 */     if (paramArrayOfByte == null) {
/* 1182 */       setPrefetchedData(null, 0);
/*      */     } else {
/* 1184 */       setPrefetchedData(paramArrayOfByte, paramArrayOfByte.length);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final void setPrefetchedData(byte[] paramArrayOfByte, int paramInt)
/*      */   {
/* 1195 */     this.prefetchData = paramArrayOfByte;
/* 1196 */     this.prefetchDataSize = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final byte[] getPrefetchedData()
/*      */   {
/* 1206 */     return this.prefetchData;
/*      */   }
/*      */   
/*      */ 
/*      */   public final int getPrefetchedDataSize()
/*      */   {
/* 1212 */     return this.prefetchDataSize;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final void setActivePrefetch(boolean paramBoolean)
/*      */   {
/* 1222 */     if ((this.activePrefetch) && (!paramBoolean))
/* 1223 */       clearCachedData();
/* 1224 */     this.activePrefetch = paramBoolean;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final void clearCachedData()
/*      */   {
/* 1234 */     this.cachedLobLength = -1L;
/* 1235 */     this.prefetchData = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final boolean isActivePrefetch()
/*      */   {
/* 1246 */     return this.activePrefetch;
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
/*      */   boolean canReadBasicLobDataInLocator()
/*      */     throws SQLException
/*      */   {
/* 1262 */     byte[] arrayOfByte = shareBytes();
/* 1263 */     if ((arrayOfByte == null) || (arrayOfByte.length < 102))
/*      */     {
/* 1265 */       return false;
/*      */     }
/* 1267 */     if (!getPhysicalConnection().isDataInLocatorEnabled())
/*      */     {
/* 1269 */       return false;
/*      */     }
/* 1271 */     int i = arrayOfByte[6] & 0xFF;
/* 1272 */     int j = arrayOfByte[7] & 0xFF;
/* 1273 */     int k = (i & 0x8) == 8 ? 1 : 0;
/* 1274 */     int m = (j & 0xFFFFFF80) == -128 ? 1 : 0;
/* 1275 */     int n = 0;
/* 1276 */     if ((k != 0) && (m == 0))
/*      */     {
/* 1278 */       i1 = arrayOfByte[88] & 0xFF;
/* 1279 */       n = (i1 & 0x8) == 8 ? 1 : 0;
/*      */     }
/* 1281 */     int i1 = (k != 0) && (m == 0) && (n != 0) ? 1 : 0;
/* 1282 */     return i1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   int dilLength()
/*      */   {
/* 1289 */     return shareBytes().length - 86 - 16;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   byte[] dilGetBytes(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1297 */     if (paramInt == 0)
/*      */     {
/* 1299 */       return new byte[0];
/*      */     }
/*      */     
/* 1302 */     if (dilLength() == 0)
/*      */     {
/* 1304 */       return null;
/*      */     }
/* 1306 */     int i = (int)Math.min(paramInt, dilLength() - (paramLong - 1L));
/*      */     
/* 1308 */     if (i <= 0)
/*      */     {
/* 1310 */       return null;
/*      */     }
/*      */     
/* 1313 */     byte[] arrayOfByte = new byte[i];
/* 1314 */     System.arraycopy(shareBytes(), (int)(paramLong - 1L) + 86 + 16, arrayOfByte, 0, i);
/* 1315 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   InputStream dilGetBinaryStream(long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1323 */     if (paramLong < 0L)
/*      */     {
/* 1325 */       throw new IllegalArgumentException("Illegal Arguments");
/*      */     }
/*      */     
/* 1328 */     byte[] arrayOfByte = dilGetBytes(paramLong, dilLength());
/*      */     
/* 1330 */     if (arrayOfByte == null)
/*      */     {
/* 1332 */       arrayOfByte = new byte[0];
/*      */     }
/*      */     
/* 1335 */     return new ByteArrayInputStream(arrayOfByte);
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
/*      */   public void free()
/*      */     throws SQLException
/*      */   {
/* 1354 */     if (this.isFree) return;
/* 1355 */     if (isOpen()) close();
/* 1356 */     if (isTemporary()) freeTemporary();
/* 1357 */     this.isFree = true;
/* 1358 */     this.dbaccess = null;
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
/*      */   public InputStream getBinaryStream(long paramLong1, long paramLong2)
/*      */     throws SQLException
/*      */   {
/* 1375 */     if (this.isFree) {
/* 1376 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1377 */       localSQLException1.fillInStackTrace();
/* 1378 */       throw localSQLException1;
/*      */     }
/* 1380 */     if (canReadBasicLobDataInLocator())
/*      */     {
/* 1382 */       return dilGetBinaryStream(paramLong1, paramLong2);
/*      */     }
/* 1384 */     long l = length();
/* 1385 */     if ((paramLong1 < 1L) || (paramLong2 < 0L) || (paramLong1 > l) || (paramLong1 - 1L + paramLong2 > l))
/*      */     {
/* 1387 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 1388 */       localSQLException2.fillInStackTrace();
/* 1389 */       throw localSQLException2;
/*      */     }
/* 1391 */     return getDBAccess().newInputStream(this, getChunkSize(), paramLong1, paramLong2);
/*      */   }
/*      */   
/*      */   InputStream dilGetBinaryStream(long paramLong1, long paramLong2)
/*      */     throws SQLException
/*      */   {
/* 1397 */     int i = dilLength();
/* 1398 */     if ((paramLong1 < 1L) || (paramLong2 < 0L) || (paramLong1 > i) || (paramLong1 - 1L + paramLong2 > i))
/*      */     {
/* 1400 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 1401 */       ((SQLException)localObject).fillInStackTrace();
/* 1402 */       throw ((Throwable)localObject);
/*      */     }
/* 1404 */     Object localObject = dilGetBytes(paramLong1, i - (int)(paramLong1 - 1L));
/* 1405 */     return new ByteArrayInputStream((byte[])localObject, 0, (int)paramLong2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 1411 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/BLOB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */