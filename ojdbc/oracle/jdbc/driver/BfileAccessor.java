package oracle.jdbc.driver;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Map;
import oracle.sql.BFILE;
import oracle.sql.Datum;
class BfileAccessor
  extends Accessor
{
  static final int maxLength = 530;
  
  BfileAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
    throws SQLException
  {
/*  31 */     init(paramOracleStatement, 114, 114, paramShort, paramBoolean);
/*  32 */     initForDataAccess(paramInt2, paramInt1, null);
  }
  
  BfileAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
    throws SQLException
  {
/*  41 */     init(paramOracleStatement, 114, 114, paramShort, false);
/*  42 */     initForDescribe(114, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, null);
    
/*  44 */     initForDataAccess(0, paramInt1, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  52 */     if (paramInt1 != 0) {
/*  53 */       this.externalType = paramInt1;
    }
/*  55 */     this.internalTypeMaxLength = 530;
    
/*  57 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*  58 */       this.internalTypeMaxLength = paramInt2;
    }
/*  60 */     this.byteLength = this.internalTypeMaxLength;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/*  81 */     return getBFILE(paramInt);
  }
  
  Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
/*  98 */     return getBFILE(paramInt);
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 120 */     return getBFILE(paramInt);
  }
  
  BFILE getBFILE(int paramInt)
    throws SQLException
  {
/* 138 */     BFILE localBFILE = null;
    
/* 140 */     if (this.rowSpaceIndicator == null)
    {
/* 144 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 145 */       localSQLException.fillInStackTrace();
/* 146 */       throw localSQLException;
    }
    
/* 152 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 154 */       int i = this.columnIndex + this.byteLength * paramInt;
/* 155 */       int j = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
      
/* 157 */       byte[] arrayOfByte = new byte[j];
      
/* 159 */       System.arraycopy(this.rowSpaceByte, i, arrayOfByte, 0, j);
      
/* 162 */       localBFILE = new BFILE(this.statement.connection, arrayOfByte);
/* 163 */       if (this.lobPrefetchSizeForThisColumn != -1) {
/* 164 */         localBFILE.setLength(this.prefetchedLobSize[paramInt]);
      }
    }
/* 167 */     return localBFILE;
  }
  
  InputStream getAsciiStream(int paramInt)
    throws SQLException
  {
/* 183 */     BFILE localBFILE = getBFILE(paramInt);
    
/* 185 */     if (localBFILE == null) {
/* 186 */       return null;
    }
/* 188 */     return localBFILE.asciiStreamValue();
  }
  
  Reader getCharacterStream(int paramInt)
    throws SQLException
  {
/* 204 */     BFILE localBFILE = getBFILE(paramInt);
    
/* 206 */     if (localBFILE == null) {
/* 207 */       return null;
    }
/* 209 */     return localBFILE.characterStreamValue();
  }
  
  InputStream getBinaryStream(int paramInt)
    throws SQLException
  {
/* 225 */     BFILE localBFILE = getBFILE(paramInt);
    
/* 227 */     if (localBFILE == null) {
/* 228 */       return null;
    }
/* 230 */     return localBFILE.getBinaryStream();
  }
  
  byte[] privateGetBytes(int paramInt)
    throws SQLException
  {
/* 243 */     return super.getBytes(paramInt);
  }
  
  byte[] getBytes(int paramInt)
    throws SQLException
  {
/* 260 */     BFILE localBFILE = getBFILE(paramInt);
    
/* 262 */     if (localBFILE == null) {
/* 263 */       return null;
    }
/* 265 */     InputStream localInputStream = localBFILE.getBinaryStream();
/* 266 */     int i = 4096;
/* 267 */     int j = 0;
/* 268 */     ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(i);
/* 269 */     byte[] arrayOfByte = new byte[i];
    
    try
    {
/* 273 */       while ((j = localInputStream.read(arrayOfByte)) != -1)
      {
/* 275 */         localByteArrayOutputStream.write(arrayOfByte, 0, j);
      }
      
    }
    catch (IOException localIOException)
    {
/* 281 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 282 */       localSQLException.fillInStackTrace();
/* 283 */       throw localSQLException;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
/* 289 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 151);
/* 290 */       localSQLException.fillInStackTrace();
/* 291 */       throw localSQLException;
    }
    
/* 295 */     return localByteArrayOutputStream.toByteArray();
  }
  
/* 300 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/BfileAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */