package oracle.jdbc.driver;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Map;
import oracle.sql.BLOB;
import oracle.sql.Datum;
class BlobAccessor
  extends Accessor
{
  static final int maxLength = 4000;
  
  BlobAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
    throws SQLException
  {
/*  32 */     init(paramOracleStatement, 113, 113, paramShort, paramBoolean);
/*  33 */     initForDataAccess(paramInt2, paramInt1, null);
  }
  
  BlobAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
    throws SQLException
  {
/*  42 */     init(paramOracleStatement, 113, 113, paramShort, false);
/*  43 */     initForDescribe(113, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, null);
    
/*  45 */     initForDataAccess(0, paramInt1, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  53 */     if (paramInt1 != 0) {
/*  54 */       this.externalType = paramInt1;
    }
/*  56 */     this.internalTypeMaxLength = 4000;
    
/*  58 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*  59 */       this.internalTypeMaxLength = paramInt2;
    }
/*  61 */     this.byteLength = this.internalTypeMaxLength;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/*  82 */     return getBLOB(paramInt);
  }
  
  Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
/*  99 */     return getBLOB(paramInt);
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 121 */     return getBLOB(paramInt);
  }
  
  BLOB getBLOB(int paramInt)
    throws SQLException
  {
/* 139 */     BLOB localBLOB = null;
    
/* 141 */     if (this.rowSpaceIndicator == null)
    {
/* 145 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 146 */       localSQLException.fillInStackTrace();
/* 147 */       throw localSQLException;
    }
    
/* 153 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 155 */       int i = this.columnIndex + this.byteLength * paramInt;
/* 156 */       int j = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
      
/* 158 */       byte[] arrayOfByte = new byte[j];
/* 159 */       System.arraycopy(this.rowSpaceByte, i, arrayOfByte, 0, j);
      
/* 162 */       localBLOB = new BLOB(this.statement.connection, arrayOfByte);
      
/* 168 */       if ((this.lobPrefetchSizeForThisColumn != -1) && (this.prefetchedLobSize != null))
      {
/* 172 */         localBLOB.setActivePrefetch(true);
/* 173 */         localBLOB.setLength(this.prefetchedLobSize[paramInt]);
/* 174 */         localBLOB.setChunkSize(this.prefetchedLobChunkSize[paramInt]);
        
/* 176 */         if ((this.prefetchedLobDataL != null) && (this.prefetchedLobDataL[paramInt] > 0))
        {
/* 178 */           initializeBlobForPrefetch(paramInt, localBLOB);
        }
        else {
/* 181 */           localBLOB.setPrefetchedData(null);
        }
      }
    }
/* 185 */     return localBLOB;
  }
  
  void initializeBlobForPrefetch(int paramInt, BLOB paramBLOB)
    throws SQLException
  {
/* 197 */     int i = this.prefetchedLobDataL[paramInt];
/* 198 */     byte[] arrayOfByte1 = this.prefetchedLobData[paramInt];
/* 199 */     byte[] arrayOfByte2 = new byte[i];
/* 200 */     System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, i);
/* 201 */     paramBLOB.setPrefetchedData(arrayOfByte2);
  }
  
  InputStream getAsciiStream(int paramInt)
    throws SQLException
  {
/* 216 */     BLOB localBLOB = getBLOB(paramInt);
    
/* 218 */     if (localBLOB == null) {
/* 219 */       return null;
    }
/* 221 */     return localBLOB.asciiStreamValue();
  }
  
  Reader getCharacterStream(int paramInt)
    throws SQLException
  {
/* 237 */     BLOB localBLOB = getBLOB(paramInt);
    
/* 239 */     if (localBLOB == null) {
/* 240 */       return null;
    }
/* 242 */     return localBLOB.characterStreamValue();
  }
  
  InputStream getBinaryStream(int paramInt)
    throws SQLException
  {
/* 258 */     BLOB localBLOB = getBLOB(paramInt);
    
/* 260 */     if (localBLOB == null) {
/* 261 */       return null;
    }
/* 263 */     return localBLOB.getBinaryStream();
  }
  
  byte[] privateGetBytes(int paramInt)
    throws SQLException
  {
/* 277 */     return super.getBytes(paramInt);
  }
  
  byte[] getBytes(int paramInt)
    throws SQLException
  {
/* 294 */     BLOB localBLOB = getBLOB(paramInt);
    
/* 296 */     if (localBLOB == null) {
/* 297 */       return null;
    }
/* 299 */     InputStream localInputStream = localBLOB.getBinaryStream();
/* 300 */     int i = localBLOB.getBufferSize();
/* 301 */     int j = 0;
/* 302 */     ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(i);
/* 303 */     byte[] arrayOfByte = new byte[i];
    
    try
    {
/* 307 */       while ((j = localInputStream.read(arrayOfByte)) != -1)
      {
/* 309 */         localByteArrayOutputStream.write(arrayOfByte, 0, j);
      }
      
    }
    catch (IOException localIOException)
    {
/* 315 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 316 */       localSQLException.fillInStackTrace();
/* 317 */       throw localSQLException;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
/* 323 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 151);
/* 324 */       localSQLException.fillInStackTrace();
/* 325 */       throw localSQLException;
    }
    
/* 328 */     if (localBLOB.isTemporary()) this.statement.addToTempLobsToFree(localBLOB);
/* 329 */     return localByteArrayOutputStream.toByteArray();
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 335 */     unimpl("getString");
/* 336 */     return null;
  }
  
  String getNString(int paramInt)
    throws SQLException
  {
/* 342 */     unimpl("getNString");
/* 343 */     return null;
  }
  
  long checksum(long paramLong, int paramInt)
    throws SQLException
  {
/* 351 */     unimpl("checksum");
/* 352 */     return -1L;
  }
  
/* 357 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/BlobAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */