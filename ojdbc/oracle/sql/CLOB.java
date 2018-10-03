/*      */ package oracle.sql;
/*      */ 
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.CharArrayReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.StringWriter;
/*      */ import java.io.Writer;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Connection;
/*      */ import java.sql.SQLException;
/*      */ import oracle.jdbc.LargeObjectAccessMode;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.internal.OracleClob;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CLOB
/*      */   extends DatumWithConnection
/*      */   implements OracleClob
/*      */ {
/*      */   public static final int MAX_CHUNK_SIZE = 32768;
/*      */   public static final int DURATION_SESSION = 10;
/*      */   public static final int DURATION_CALL = 12;
/*      */   static final int OLD_WRONG_DURATION_SESSION = 1;
/*      */   static final int OLD_WRONG_DURATION_CALL = 2;
/*      */   public static final int MODE_READONLY = 0;
/*      */   public static final int MODE_READWRITE = 1;
/*      */   ClobDBAccess dbaccess;
/*   57 */   private int dbChunkSize = -1;
/*      */   private short csform;
/*   59 */   boolean isFree = false;
/*      */   
/*      */ 
/*      */ 
/*   63 */   boolean fromObject = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   68 */   long cachedLengthOfClobInChars = -1L;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*   73 */   char[] prefetchData = null;
/*      */   
/*   75 */   int prefetchDataSize = 0;
/*      */   
/*      */ 
/*      */ 
/*   79 */   boolean activePrefetch = false;
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
/*      */   protected CLOB() {}
/*      */   
/*      */ 
/*      */   public CLOB(oracle.jdbc.OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*   97 */     this(paramOracleConnection, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CLOB(oracle.jdbc.OracleConnection paramOracleConnection, byte[] paramArrayOfByte, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  109 */     this(paramOracleConnection, paramArrayOfByte);
/*      */     
/*  111 */     this.fromObject = paramBoolean;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final short getFormOfUseFromLocator(byte[] paramArrayOfByte)
/*      */   {
/*  121 */     short s = -1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  131 */     if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 5))
/*      */     {
/*  133 */       if (((paramArrayOfByte[5] & 0x40) != 0) && ((paramArrayOfByte[5] & 0xFFFFFF80) == 0))
/*      */       {
/*  135 */         s = 2;
/*      */       } else {
/*  137 */         s = 1;
/*      */       }
/*      */     }
/*  140 */     return s;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CLOB(oracle.jdbc.OracleConnection paramOracleConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  150 */     super(paramArrayOfByte);
/*      */     
/*      */ 
/*  153 */     if (paramArrayOfByte != null)
/*      */     {
/*  155 */       this.csform = getFormOfUseFromLocator(paramArrayOfByte);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  160 */     assertNotNull(paramOracleConnection);
/*  161 */     setPhysicalConnectionOf(paramOracleConnection);
/*      */     
/*  163 */     this.dbaccess = ((oracle.jdbc.internal.OracleConnection)paramOracleConnection).createClobDBAccess();
/*      */     
/*      */ 
/*      */ 
/*  167 */     this.dbaccess.incrementTempLobReferenceCount(paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public CLOB(oracle.jdbc.OracleConnection paramOracleConnection, byte[] paramArrayOfByte, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  175 */     this(paramOracleConnection, paramArrayOfByte);
/*      */     
/*  177 */     short s = getFormOfUseFromLocator(paramArrayOfByte);
/*  178 */     if (s != -1)
/*      */     {
/*  180 */       if (paramShort != s)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  185 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 184);
/*  186 */         localSQLException.fillInStackTrace();
/*  187 */         throw localSQLException;
/*      */       }
/*      */       
/*  190 */       this.csform = s;
/*      */     }
/*      */     else {
/*  193 */       this.csform = paramShort;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isNCLOB()
/*      */   {
/*  204 */     return this.csform == 2;
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
/*      */   public long length()
/*      */     throws SQLException
/*      */   {
/*  223 */     if (this.isFree) {
/*  224 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  225 */       localSQLException.fillInStackTrace();
/*  226 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  231 */     long l = -1L;
/*  232 */     if ((this.activePrefetch) && (this.cachedLengthOfClobInChars != -1L)) {
/*  233 */       l = this.cachedLengthOfClobInChars;
/*  234 */     } else if (canReadBasicLobDataInLocator())
/*      */     {
/*  236 */       l = dilGetChars().length;
/*      */     }
/*      */     else
/*  239 */       l = getDBAccess().length(this);
/*  240 */     return l;
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
/*      */   public String getSubString(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  269 */     if (this.isFree) {
/*  270 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  271 */       ((SQLException)localObject).fillInStackTrace();
/*  272 */       throw ((Throwable)localObject);
/*      */     }
/*  274 */     if ((paramInt < 0) || (paramLong < 1L))
/*      */     {
/*      */ 
/*  277 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  278 */       ((SQLException)localObject).fillInStackTrace();
/*  279 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  283 */     if (canReadBasicLobDataInLocator())
/*      */     {
/*  285 */       return dilGetSubString(paramLong, paramInt);
/*      */     }
/*      */     
/*  288 */     Object localObject = null;
/*      */     
/*  290 */     if ((paramInt == 0) || ((this.activePrefetch) && ((this.cachedLengthOfClobInChars == 0L) || ((this.cachedLengthOfClobInChars > 0L) && (paramLong - 1L >= this.cachedLengthOfClobInChars)))))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  298 */       localObject = new String();
/*      */ 
/*      */     }
/*  301 */     else if ((this.prefetchData != null) && (this.prefetchDataSize > 0) && (this.cachedLengthOfClobInChars == this.prefetchDataSize) && (paramLong + paramInt - 1L <= this.cachedLengthOfClobInChars))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  309 */       localObject = new String(this.prefetchData, (int)paramLong - 1, paramInt);
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/*  317 */       char[] arrayOfChar = getDBAccess().getCharBufferSync(paramInt);
/*      */       
/*      */ 
/*      */ 
/*  321 */       int i = getChars(paramLong, paramInt, arrayOfChar);
/*      */       
/*  323 */       if (i > 0)
/*      */       {
/*  325 */         localObject = new String(arrayOfChar, 0, i);
/*      */       }
/*      */       else
/*      */       {
/*  329 */         localObject = new String();
/*      */       }
/*      */       
/*  332 */       getDBAccess().cacheBufferSync(arrayOfChar);
/*      */     }
/*      */     
/*  335 */     return (String)localObject;
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
/*      */   public Reader getCharacterStream()
/*      */     throws SQLException
/*      */   {
/*  351 */     if (this.isFree) {
/*  352 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  353 */       localSQLException.fillInStackTrace();
/*  354 */       throw localSQLException;
/*      */     }
/*  356 */     if (canReadBasicLobDataInLocator())
/*      */     {
/*  358 */       return dilGetCharacterStream(1L);
/*      */     }
/*      */     
/*  361 */     return getDBAccess().newReader(this, getBufferSize(), 0L);
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
/*      */   public InputStream getAsciiStream()
/*      */     throws SQLException
/*      */   {
/*  377 */     if (this.isFree) {
/*  378 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  379 */       localSQLException.fillInStackTrace();
/*  380 */       throw localSQLException;
/*      */     }
/*  382 */     if (canReadBasicLobDataInLocator())
/*      */     {
/*  384 */       return dilGetAsciiStream(1L);
/*      */     }
/*      */     
/*  387 */     return getDBAccess().newInputStream(this, getBufferSize(), 0L);
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
/*      */   public long position(String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  406 */     if (this.isFree) {
/*  407 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  408 */       localSQLException.fillInStackTrace();
/*  409 */       throw localSQLException;
/*      */     }
/*  411 */     return getDBAccess().position(this, paramString, paramLong);
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
/*      */   public long position(Clob paramClob, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  429 */     if (this.isFree) {
/*  430 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  431 */       localSQLException.fillInStackTrace();
/*  432 */       throw localSQLException;
/*      */     }
/*  434 */     return getDBAccess().position(this, (CLOB)paramClob, paramLong);
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
/*      */   public int getChars(long paramLong, int paramInt, char[] paramArrayOfChar)
/*      */     throws SQLException
/*      */   {
/*  454 */     if (this.isFree) {
/*  455 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  456 */       localSQLException.fillInStackTrace();
/*  457 */       throw localSQLException;
/*      */     }
/*  459 */     return getDBAccess().getChars(this, paramLong, paramInt, paramArrayOfChar);
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
/*      */   public Writer getCharacterOutputStream()
/*      */     throws SQLException
/*      */   {
/*  476 */     if (this.isFree) {
/*  477 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  478 */       localSQLException.fillInStackTrace();
/*  479 */       throw localSQLException;
/*      */     }
/*  481 */     return setCharacterStream(1L);
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
/*      */   public OutputStream getAsciiOutputStream()
/*      */     throws SQLException
/*      */   {
/*  497 */     if (this.isFree) {
/*  498 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  499 */       localSQLException.fillInStackTrace();
/*  500 */       throw localSQLException;
/*      */     }
/*  502 */     return setAsciiStream(1L);
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
/*  513 */     return getBytes();
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
/*  524 */     setBytes(paramArrayOfByte);
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
/*      */   public int putChars(long paramLong, char[] paramArrayOfChar)
/*      */     throws SQLException
/*      */   {
/*  542 */     if (this.isFree) {
/*  543 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  544 */       localSQLException.fillInStackTrace();
/*  545 */       throw localSQLException;
/*      */     }
/*  547 */     return getDBAccess().putChars(this, paramLong, paramArrayOfChar, 0, paramArrayOfChar != null ? paramArrayOfChar.length : 0);
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
/*      */   public int putChars(long paramLong, char[] paramArrayOfChar, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  569 */     if (this.isFree) {
/*  570 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  571 */       localSQLException.fillInStackTrace();
/*  572 */       throw localSQLException;
/*      */     }
/*  574 */     return getDBAccess().putChars(this, paramLong, paramArrayOfChar, 0, paramInt);
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
/*      */   public int putChars(long paramLong, char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  594 */     if (this.isFree) {
/*  595 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  596 */       localSQLException.fillInStackTrace();
/*  597 */       throw localSQLException;
/*      */     }
/*  599 */     return getDBAccess().putChars(this, paramLong, paramArrayOfChar, paramInt1, paramInt2);
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
/*      */   public int putString(long paramLong, String paramString)
/*      */     throws SQLException
/*      */   {
/*  616 */     if (this.isFree) {
/*  617 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  618 */       localSQLException.fillInStackTrace();
/*  619 */       throw localSQLException;
/*      */     }
/*  621 */     return setString(paramLong, paramString);
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
/*  635 */     if (this.isFree) {
/*  636 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  637 */       localSQLException.fillInStackTrace();
/*  638 */       throw localSQLException;
/*      */     }
/*  640 */     if (this.dbChunkSize <= 0)
/*      */     {
/*  642 */       this.dbChunkSize = getDBAccess().getChunkSize(this);
/*      */     }
/*      */     
/*  645 */     return this.dbChunkSize;
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
/*  659 */     if (this.isFree) {
/*  660 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  661 */       localSQLException.fillInStackTrace();
/*  662 */       throw localSQLException;
/*      */     }
/*  664 */     int i = getChunkSize();
/*  665 */     int j = 0;
/*      */     
/*  667 */     if ((i >= 32768) || (i <= 0))
/*      */     {
/*  669 */       j = 32768;
/*      */     }
/*      */     else
/*      */     {
/*  673 */       j = 32768 / i * i;
/*      */     }
/*  675 */     return j;
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
/*      */   public static CLOB empty_lob()
/*      */     throws SQLException
/*      */   {
/*  692 */     return getEmptyCLOB();
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
/*      */   public static CLOB getEmptyCLOB()
/*      */     throws SQLException
/*      */   {
/*  722 */     byte[] arrayOfByte = new byte[86];
/*      */     
/*  724 */     arrayOfByte[1] = 84;
/*  725 */     arrayOfByte[5] = 24;
/*      */     
/*  727 */     CLOB localCLOB = new CLOB();
/*      */     
/*  729 */     localCLOB.setShareBytes(arrayOfByte);
/*      */     
/*  731 */     return localCLOB;
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
/*      */   public boolean isEmptyLob()
/*      */     throws SQLException
/*      */   {
/*  751 */     if (this.isFree) {
/*  752 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  753 */       localSQLException.fillInStackTrace();
/*  754 */       throw localSQLException;
/*      */     }
/*  756 */     return (shareBytes()[5] & 0x10) != 0;
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
/*  768 */     if (this.isFree) {
/*  769 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  770 */       localSQLException.fillInStackTrace();
/*  771 */       throw localSQLException;
/*      */     }
/*  773 */     boolean bool = (shareBytes()[7] & 0xFFFFFF80) != 0;
/*  774 */     return bool;
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
/*      */   public OutputStream getAsciiOutputStream(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  790 */     if (this.isFree) {
/*  791 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  792 */       localSQLException.fillInStackTrace();
/*  793 */       throw localSQLException;
/*      */     }
/*  795 */     return getDBAccess().newOutputStream(this, getBufferSize(), paramLong, false);
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
/*      */   public Writer getCharacterOutputStream(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  811 */     if (this.isFree) {
/*  812 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  813 */       localSQLException.fillInStackTrace();
/*  814 */       throw localSQLException;
/*      */     }
/*  816 */     return getDBAccess().newWriter(this, getBufferSize(), paramLong, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getAsciiStream(long paramLong)
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*      */ 
/*  830 */     if (this.isFree)
/*      */     {
/*  832 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  833 */       localSQLException.fillInStackTrace();
/*  834 */       throw localSQLException;
/*      */     }
/*      */     
/*  837 */     if (paramLong == 0L)
/*      */     {
/*  839 */       if (!getPhysicalConnection().isLobStreamPosStandardCompliant())
/*      */       {
/*  841 */         paramLong = 1L;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  846 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  847 */         localSQLException.fillInStackTrace();
/*  848 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  853 */     if (canReadBasicLobDataInLocator())
/*      */     {
/*  855 */       return dilGetAsciiStream(paramLong);
/*      */     }
/*      */     
/*  858 */     return getDBAccess().newInputStream(this, getBufferSize(), paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader getCharacterStream(long paramLong)
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  873 */     if (this.isFree)
/*      */     {
/*  875 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  876 */       localSQLException.fillInStackTrace();
/*  877 */       throw localSQLException;
/*      */     }
/*      */     
/*  880 */     if (paramLong == 0L)
/*      */     {
/*  882 */       if (!getPhysicalConnection().isLobStreamPosStandardCompliant())
/*      */       {
/*  884 */         paramLong = 1L;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  889 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  890 */         localSQLException.fillInStackTrace();
/*  891 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  896 */     if (canReadBasicLobDataInLocator())
/*      */     {
/*  898 */       return dilGetCharacterStream(paramLong);
/*      */     }
/*      */     
/*  901 */     return getDBAccess().newReader(this, getBufferSize(), paramLong);
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
/*  917 */     if (this.isFree) {
/*  918 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/*  919 */       localSQLException.fillInStackTrace();
/*  920 */       throw localSQLException;
/*      */     }
/*  922 */     truncate(paramLong);
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
/*      */   public static CLOB createTemporary(Connection paramConnection, boolean paramBoolean, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  939 */     return createTemporary(paramConnection, paramBoolean, paramInt, (short)1);
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
/*      */   public static CLOB createTemporary(Connection paramConnection, boolean paramBoolean, int paramInt, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  959 */     int i = paramInt;
/*      */     
/*  961 */     if (paramInt == 1) {
/*  962 */       i = 10;
/*      */     }
/*  964 */     if (paramInt == 2) {
/*  965 */       i = 12;
/*      */     }
/*  967 */     if ((paramConnection == null) || ((i != 10) && (i != 12)))
/*      */     {
/*      */ 
/*      */ 
/*  971 */       localObject = DatabaseError.createSqlException(null, 68, "'conn' should not be null and 'duration' should either be equal to DURATION_SESSION or DURATION_CALL");
/*      */       
/*      */ 
/*  974 */       ((SQLException)localObject).fillInStackTrace();
/*  975 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  979 */     Object localObject = ((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin();
/*      */     
/*      */ 
/*  982 */     CLOB localCLOB = getDBAccess((Connection)localObject).createTemporaryClob((Connection)localObject, paramBoolean, i, paramShort);
/*      */     
/*  984 */     localCLOB.csform = getFormOfUseFromLocator(localCLOB.shareBytes());
/*      */     
/*  986 */     return localCLOB;
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
/*      */   public static void freeTemporary(CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 1000 */     if (paramCLOB == null)
/* 1001 */       return;
/* 1002 */     paramCLOB.freeTemporary();
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
/*      */   public static boolean isTemporary(CLOB paramCLOB)
/*      */     throws SQLException
/*      */   {
/* 1018 */     if (paramCLOB == null) {
/* 1019 */       return false;
/*      */     }
/* 1021 */     return paramCLOB.isTemporary();
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
/* 1035 */     if (this.isFree) {
/* 1036 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1037 */       localSQLException.fillInStackTrace();
/* 1038 */       throw localSQLException;
/*      */     }
/*      */     
/* 1041 */     int i = getDBAccess().decrementTempLobReferenceCount(shareBytes());
/*      */     
/* 1043 */     if (i == 0) {
/* 1044 */       getDBAccess().freeTemporary(this, this.fromObject);
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
/* 1059 */     if (this.isFree) {
/* 1060 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1061 */       localSQLException.fillInStackTrace();
/* 1062 */       throw localSQLException;
/*      */     }
/* 1064 */     return getDBAccess().isTemporary(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void open(LargeObjectAccessMode paramLargeObjectAccessMode)
/*      */     throws SQLException
/*      */   {
/* 1073 */     open(paramLargeObjectAccessMode.getCode());
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
/* 1084 */     if (this.isFree) {
/* 1085 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1086 */       localSQLException.fillInStackTrace();
/* 1087 */       throw localSQLException;
/*      */     }
/* 1089 */     getDBAccess().open(this, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void close()
/*      */     throws SQLException
/*      */   {
/* 1101 */     if (this.isFree) {
/* 1102 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1103 */       localSQLException.fillInStackTrace();
/* 1104 */       throw localSQLException;
/*      */     }
/* 1106 */     getDBAccess().close(this);
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
/* 1118 */     if (this.isFree) {
/* 1119 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1120 */       localSQLException.fillInStackTrace();
/* 1121 */       throw localSQLException;
/*      */     }
/* 1123 */     return getDBAccess().isOpen(this);
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
/*      */   public int setString(long paramLong, String paramString)
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
/*      */ 
/* 1150 */     if (this.isFree) {
/* 1151 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1152 */       localSQLException.fillInStackTrace();
/* 1153 */       throw localSQLException;
/*      */     }
/* 1155 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 1158 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "'pos' should not be < 1");
/* 1159 */       localSQLException.fillInStackTrace();
/* 1160 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1164 */     int i = 0;
/*      */     
/* 1166 */     if ((paramString != null) && (paramString.length() != 0))
/* 1167 */       i = putChars(paramLong, paramString.toCharArray());
/* 1168 */     return i;
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
/*      */   public int setString(long paramLong, String paramString, int paramInt1, int paramInt2)
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
/*      */ 
/*      */ 
/*      */ 
/* 1199 */     if (this.isFree) {
/* 1200 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1201 */       localSQLException.fillInStackTrace();
/* 1202 */       throw localSQLException;
/*      */     }
/* 1204 */     if (paramLong < 1L)
/*      */     {
/*      */ 
/* 1207 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "'pos' should not be < 1");
/* 1208 */       localSQLException.fillInStackTrace();
/* 1209 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1213 */     if (paramInt1 < 0)
/*      */     {
/*      */ 
/* 1216 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "'offset' should not be < 0");
/* 1217 */       localSQLException.fillInStackTrace();
/* 1218 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1222 */     if (paramInt1 + paramInt2 > paramString.length())
/*      */     {
/*      */ 
/*      */ 
/* 1226 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, " 'offset + len' should not be exceed string length. ");
/* 1227 */       localSQLException.fillInStackTrace();
/* 1228 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1232 */     int i = 0;
/*      */     
/* 1234 */     if ((paramString != null) && (paramString.length() != 0))
/* 1235 */       i = putChars(paramLong, paramString.toCharArray(), paramInt1, paramInt2);
/* 1236 */     return i;
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
/*      */   public OutputStream setAsciiStream(long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1257 */     if (this.isFree) {
/* 1258 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1259 */       localSQLException.fillInStackTrace();
/* 1260 */       throw localSQLException;
/*      */     }
/* 1262 */     return getDBAccess().newOutputStream(this, getBufferSize(), paramLong, true);
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
/*      */   public Writer setCharacterStream(long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1284 */     if (this.isFree) {
/* 1285 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1286 */       localSQLException.fillInStackTrace();
/* 1287 */       throw localSQLException;
/*      */     }
/* 1289 */     return getDBAccess().newWriter(this, getBufferSize(), paramLong, true);
/*      */   }
/*      */   
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
/* 1309 */     if (this.isFree) {
/* 1310 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1311 */       localSQLException.fillInStackTrace();
/* 1312 */       throw localSQLException;
/*      */     }
/* 1314 */     if (paramLong < 0L)
/*      */     {
/*      */ 
/* 1317 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, " 'len' should not be < 0");
/* 1318 */       localSQLException.fillInStackTrace();
/* 1319 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1323 */     getDBAccess().trim(this, paramLong);
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
/*      */   public Object toJdbc()
/*      */     throws SQLException
/*      */   {
/* 1345 */     if (this.isFree) {
/* 1346 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1347 */       localSQLException.fillInStackTrace();
/* 1348 */       throw localSQLException;
/*      */     }
/* 1350 */     return this;
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
/*      */   public boolean isConvertibleTo(Class paramClass)
/*      */   {
/* 1368 */     String str = paramClass.getName();
/*      */     
/* 1370 */     return (str.compareTo("java.io.InputStream") == 0) || (str.compareTo("java.io.Reader") == 0);
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
/* 1385 */     return getCharacterStream();
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
/*      */   public InputStream asciiStreamValue()
/*      */     throws SQLException
/*      */   {
/* 1399 */     return getAsciiStream();
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
/*      */   public InputStream binaryStreamValue()
/*      */     throws SQLException
/*      */   {
/* 1413 */     return getAsciiStream();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String stringValue()
/*      */     throws SQLException
/*      */   {
/* 1426 */     Reader localReader = getCharacterStream();
/* 1427 */     int i = getBufferSize();
/* 1428 */     int j = 0;
/* 1429 */     StringWriter localStringWriter = new StringWriter(i);
/* 1430 */     char[] arrayOfChar = new char[i];
/*      */     
/*      */     try
/*      */     {
/* 1434 */       while ((j = localReader.read(arrayOfChar)) != -1)
/*      */       {
/* 1436 */         localStringWriter.write(arrayOfChar, 0, j);
/*      */       }
/*      */       
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 1442 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 1443 */       localSQLException.fillInStackTrace();
/* 1444 */       throw localSQLException;
/*      */ 
/*      */     }
/*      */     catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
/*      */     {
/*      */ 
/* 1450 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 151);
/* 1451 */       localSQLException.fillInStackTrace();
/* 1452 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1456 */     return localStringWriter.getBuffer().substring(0);
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
/*      */   public Object makeJdbcArray(int paramInt)
/*      */   {
/* 1480 */     return new CLOB[paramInt];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ClobDBAccess getDBAccess()
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/* 1492 */     if (this.dbaccess == null)
/*      */     {
/* 1494 */       if (isEmptyLob())
/*      */       {
/*      */ 
/*      */ 
/* 1498 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 98);
/* 1499 */         localSQLException.fillInStackTrace();
/* 1500 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1504 */       this.dbaccess = getInternalConnection().createClobDBAccess();
/*      */     }
/*      */     
/* 1507 */     if (getPhysicalConnection().isClosed())
/*      */     {
/* 1509 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 1510 */       localSQLException.fillInStackTrace();
/* 1511 */       throw localSQLException;
/*      */     }
/*      */     
/* 1514 */     return this.dbaccess;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ClobDBAccess getDBAccess(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/* 1525 */     return ((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin().createClobDBAccess();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Connection getJavaSqlConnection()
/*      */     throws SQLException
/*      */   {
/* 1534 */     if (this.isFree) {
/* 1535 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1536 */       localSQLException.fillInStackTrace();
/* 1537 */       throw localSQLException;
/*      */     }
/* 1539 */     return super.getJavaSqlConnection();
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
/* 1550 */     this.cachedLengthOfClobInChars = paramLong;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final void setChunkSize(int paramInt)
/*      */   {
/* 1560 */     this.dbChunkSize = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final void setPrefetchedData(char[] paramArrayOfChar)
/*      */   {
/* 1571 */     if (paramArrayOfChar == null) {
/* 1572 */       setPrefetchedData(null, 0);
/*      */     } else {
/* 1574 */       setPrefetchedData(paramArrayOfChar, paramArrayOfChar.length);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final void setPrefetchedData(char[] paramArrayOfChar, int paramInt)
/*      */   {
/* 1585 */     this.prefetchData = paramArrayOfChar;
/* 1586 */     this.prefetchDataSize = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final char[] getPrefetchedData()
/*      */   {
/* 1596 */     return this.prefetchData;
/*      */   }
/*      */   
/*      */ 
/*      */   public final int getPrefetchedDataSize()
/*      */   {
/* 1602 */     return this.prefetchDataSize;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final void setActivePrefetch(boolean paramBoolean)
/*      */   {
/* 1612 */     if ((this.activePrefetch) && (!paramBoolean))
/* 1613 */       clearCachedData();
/* 1614 */     this.activePrefetch = paramBoolean;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final void clearCachedData()
/*      */   {
/* 1624 */     this.cachedLengthOfClobInChars = -1L;
/* 1625 */     this.prefetchData = null;
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
/* 1636 */     return this.activePrefetch;
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
/* 1649 */   transient CharacterSet dilCharacterSet = null;
/*      */   
/*      */   boolean canReadBasicLobDataInLocator()
/*      */     throws SQLException
/*      */   {
/* 1654 */     byte[] arrayOfByte = shareBytes();
/* 1655 */     if ((arrayOfByte == null) || (arrayOfByte.length < 102))
/*      */     {
/* 1657 */       return false;
/*      */     }
/* 1659 */     if (!getPhysicalConnection().isDataInLocatorEnabled())
/*      */     {
/* 1661 */       return false;
/*      */     }
/* 1663 */     int i = arrayOfByte[6] & 0xFF;
/* 1664 */     int j = arrayOfByte[7] & 0xFF;
/* 1665 */     int k = (i & 0x8) == 8 ? 1 : 0;
/* 1666 */     int m = (j & 0xFFFFFF80) == -128 ? 1 : 0;
/* 1667 */     int n = 0;
/* 1668 */     if ((k != 0) && (m == 0))
/*      */     {
/* 1670 */       i1 = arrayOfByte[88] & 0xFF;
/* 1671 */       n = (i1 & 0x8) == 8 ? 1 : 0;
/*      */     }
/* 1673 */     int i1 = (k != 0) && (m == 0) && (n != 0) ? 1 : 0;
/* 1674 */     boolean bool = false;
/* 1675 */     if (i1 != 0)
/*      */     {
/* 1677 */       dilGetCharacterSet();
/* 1678 */       bool = !this.dilCharacterSet.isUnknown();
/*      */     }
/* 1680 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   int dilGetCharSetId()
/*      */     throws SQLException
/*      */   {
/* 1688 */     int i = shareBytes()[32];
/* 1689 */     int j = shareBytes()[33];
/* 1690 */     int k = (i & 0xFF) << 8 | j & 0xFF;
/* 1691 */     return k;
/*      */   }
/*      */   
/*      */ 
/*      */   boolean isMigratedAL16UTF16LE()
/*      */   {
/* 1697 */     int i = shareBytes()[7] & 0xFF;
/* 1698 */     return (i & 0x40) == 64;
/*      */   }
/*      */   
/*      */   boolean isVariableWidth()
/*      */   {
/* 1703 */     int i = shareBytes()[6] & 0xFF;
/* 1704 */     int j = 128;
/* 1705 */     return (i & j) == j;
/*      */   }
/*      */   
/*      */   void dilGetCharacterSet()
/*      */     throws SQLException
/*      */   {
/* 1711 */     if (this.dilCharacterSet == null)
/*      */     {
/* 1713 */       if (isMigratedAL16UTF16LE())
/*      */       {
/* 1715 */         this.dilCharacterSet = CharacterSet.make(2002);
/*      */       }
/* 1717 */       else if (isVariableWidth())
/*      */       {
/* 1719 */         this.dilCharacterSet = CharacterSet.make(2000);
/*      */       }
/*      */       else
/*      */       {
/* 1723 */         int i = dilGetCharSetId();
/* 1724 */         this.dilCharacterSet = CharacterSet.make(i);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   int dilLength()
/*      */   {
/* 1733 */     return shareBytes().length - 86 - 16;
/*      */   }
/*      */   
/*      */ 
/*      */   char[] dilGetChars()
/*      */     throws SQLException
/*      */   {
/* 1740 */     int i = dilLength();
/* 1741 */     byte[] arrayOfByte = new byte[i];
/* 1742 */     System.arraycopy(shareBytes(), 102, arrayOfByte, 0, i);
/* 1743 */     String str = this.dilCharacterSet.toStringWithReplacement(arrayOfByte, 0, i);
/* 1744 */     char[] arrayOfChar = str.toCharArray();
/* 1745 */     return arrayOfChar;
/*      */   }
/*      */   
/*      */ 
/*      */   InputStream dilGetAsciiStream(long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1752 */     char[] arrayOfChar = dilGetChars();
/*      */     byte[] arrayOfByte;
/* 1754 */     if (paramLong - 1L > arrayOfChar.length)
/*      */     {
/* 1756 */       arrayOfByte = new byte[0];
/* 1757 */       return new ByteArrayInputStream(arrayOfByte);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1762 */     if (dilGetCharSetId() == 1)
/*      */     {
/* 1764 */       arrayOfByte = new byte[arrayOfChar.length];
/* 1765 */       for (int i = 0; i < arrayOfChar.length; i++) {
/* 1766 */         arrayOfByte[i] = ((byte)arrayOfChar[i]);
/*      */       }
/*      */     }
/*      */     else {
/* 1770 */       CharacterSet localCharacterSet = CharacterSet.make(1);
/* 1771 */       arrayOfByte = localCharacterSet.convertWithReplacement(new String(arrayOfChar));
/*      */     }
/* 1773 */     return new ByteArrayInputStream(arrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */   Reader dilGetCharacterStream(long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1780 */     char[] arrayOfChar1 = dilGetChars();
/* 1781 */     int i = arrayOfChar1.length;
/*      */     
/* 1783 */     if (paramLong - 1L > i)
/*      */     {
/* 1785 */       char[] arrayOfChar2 = new char[0];
/* 1786 */       return new CharArrayReader(arrayOfChar2);
/*      */     }
/*      */     
/* 1789 */     return new CharArrayReader(arrayOfChar1, (int)(paramLong - 1L), Integer.MAX_VALUE);
/*      */   }
/*      */   
/*      */ 
/*      */   String dilGetSubString(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1796 */     char[] arrayOfChar = dilGetChars();
/* 1797 */     if ((int)paramLong > arrayOfChar.length)
/*      */     {
/* 1799 */       return "";
/*      */     }
/* 1801 */     int i = (int)Math.min(paramInt, arrayOfChar.length - (paramLong - 1L));
/* 1802 */     if (i == 0)
/*      */     {
/* 1804 */       return "";
/*      */     }
/* 1806 */     return new String(arrayOfChar, (int)(paramLong - 1L), i);
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
/*      */   public void free()
/*      */     throws SQLException
/*      */   {
/* 1821 */     if (this.isFree) return;
/* 1822 */     if (isOpen()) close();
/* 1823 */     if (isTemporary()) freeTemporary();
/* 1824 */     this.isFree = true;
/* 1825 */     this.dbaccess = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader getCharacterStream(long paramLong1, long paramLong2)
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1842 */     if (this.isFree)
/*      */     {
/* 1844 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 192);
/* 1845 */       localSQLException1.fillInStackTrace();
/* 1846 */       throw localSQLException1;
/*      */     }
/*      */     
/* 1849 */     if (paramLong1 == 0L)
/*      */     {
/* 1851 */       if (!getPhysicalConnection().isLobStreamPosStandardCompliant())
/*      */       {
/* 1853 */         paramLong1 = 1L;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1858 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 1859 */         localSQLException1.fillInStackTrace();
/* 1860 */         throw localSQLException1;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1865 */     if (canReadBasicLobDataInLocator())
/*      */     {
/* 1867 */       return dilGetCharacterStream(paramLong1, paramLong2);
/*      */     }
/*      */     
/* 1870 */     long l = length();
/* 1871 */     if ((paramLong1 < 1L) || (paramLong2 < 0L) || (paramLong1 > l) || (paramLong1 - 1L + paramLong2 > l))
/*      */     {
/* 1873 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 1874 */       localSQLException2.fillInStackTrace();
/* 1875 */       throw localSQLException2;
/*      */     }
/* 1877 */     return getDBAccess().newReader(this, getChunkSize(), paramLong1, paramLong2);
/*      */   }
/*      */   
/*      */   Reader dilGetCharacterStream(long paramLong1, long paramLong2)
/*      */     throws SQLException
/*      */   {
/* 1883 */     if ((paramLong1 < 1L) || (paramLong2 < 0L))
/*      */     {
/* 1885 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 1886 */       ((SQLException)localObject).fillInStackTrace();
/* 1887 */       throw ((Throwable)localObject);
/*      */     }
/* 1889 */     Object localObject = dilGetChars();
/* 1890 */     long l = localObject.length;
/* 1891 */     if ((paramLong1 < 1L) || (paramLong2 < 0L) || (paramLong1 > l) || (paramLong1 - 1L + paramLong2 > l))
/*      */     {
/* 1893 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 1894 */       localSQLException.fillInStackTrace();
/* 1895 */       throw localSQLException;
/*      */     }
/* 1897 */     return new CharArrayReader((char[])localObject, (int)(paramLong1 - 1L), (int)paramLong2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1905 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CLOB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */