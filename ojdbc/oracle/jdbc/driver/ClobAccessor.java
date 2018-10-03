package oracle.jdbc.driver;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Map;
import oracle.sql.CLOB;
import oracle.sql.Datum;
import oracle.sql.NCLOB;
class ClobAccessor
  extends Accessor
{
  static final int maxLength = 4000;
  
  ClobAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
    throws SQLException
  {
/*  32 */     init(paramOracleStatement, 112, 112, paramShort, paramBoolean);
/*  33 */     initForDataAccess(paramInt2, paramInt1, null);
  }
  
  ClobAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
    throws SQLException
  {
/*  42 */     init(paramOracleStatement, 112, 112, paramShort, false);
/*  43 */     initForDescribe(112, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, null);
    
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
/*  82 */     return getCLOB(paramInt);
  }
  
  Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
/*  99 */     return getCLOB(paramInt);
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 121 */     return getCLOB(paramInt);
  }
  
  CLOB getCLOB(int paramInt)
    throws SQLException
  {
/* 139 */     Object localObject = null;
    
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
      
/* 160 */       System.arraycopy(this.rowSpaceByte, i, arrayOfByte, 0, j);
      
/* 163 */       if (this.formOfUse == 1) {
/* 164 */         localObject = new CLOB(this.statement.connection, arrayOfByte, this.formOfUse);
      }
      else {
/* 167 */         localObject = new NCLOB(this.statement.connection, arrayOfByte);
      }
      
/* 174 */       if ((this.lobPrefetchSizeForThisColumn != -1) && (this.prefetchedLobSize != null))
      {
/* 178 */         ((CLOB)localObject).setActivePrefetch(true);
/* 179 */         ((CLOB)localObject).setLength(this.prefetchedLobSize[paramInt]);
/* 180 */         ((CLOB)localObject).setChunkSize(this.prefetchedLobChunkSize[paramInt]);
        
/* 183 */         if ((this.prefetchedLobDataL != null) && (this.prefetchedLobDataL[paramInt] > 0))
        {
/* 185 */           initializeClobForPrefetch(paramInt, (CLOB)localObject);
        }
        else {
/* 188 */           ((CLOB)localObject).setPrefetchedData(null);
        }
      }
    }
/* 192 */     return (CLOB)localObject;
  }
  
  NCLOB getNCLOB(int paramInt)
    throws SQLException
  {
/* 198 */     if (this.formOfUse != 2)
    {
/* 200 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 132);
/* 201 */       localSQLException.fillInStackTrace();
/* 202 */       throw localSQLException;
    }
    
/* 205 */     return (NCLOB)getCLOB(paramInt);
  }
  
  void initializeClobForPrefetch(int paramInt, CLOB paramCLOB)
    throws SQLException
  {
/* 211 */     paramCLOB.setPrefetchedData(this.prefetchedLobCharData[paramInt]);
  }
  
  InputStream getAsciiStream(int paramInt)
    throws SQLException
  {
/* 226 */     CLOB localCLOB = getCLOB(paramInt);
    
/* 228 */     if (localCLOB == null) {
/* 229 */       return null;
    }
/* 231 */     return localCLOB.getAsciiStream();
  }
  
  Reader getCharacterStream(int paramInt)
    throws SQLException
  {
/* 247 */     CLOB localCLOB = getCLOB(paramInt);
    
/* 249 */     if (localCLOB == null) {
/* 250 */       return null;
    }
/* 252 */     return localCLOB.getCharacterStream();
  }
  
  InputStream getBinaryStream(int paramInt)
    throws SQLException
  {
/* 268 */     CLOB localCLOB = getCLOB(paramInt);
    
/* 270 */     if (localCLOB == null) {
/* 271 */       return null;
    }
/* 273 */     return localCLOB.getAsciiStream();
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 290 */     CLOB localCLOB = getCLOB(paramInt);
    
/* 292 */     if (localCLOB == null) {
/* 293 */       return null;
    }
/* 295 */     Reader localReader = localCLOB.getCharacterStream();
/* 296 */     int i = localCLOB.getBufferSize();
/* 297 */     int j = 0;
/* 298 */     StringWriter localStringWriter = new StringWriter(i);
/* 299 */     char[] arrayOfChar = new char[i];
    
    try
    {
/* 303 */       while ((j = localReader.read(arrayOfChar)) != -1)
      {
/* 305 */         localStringWriter.write(arrayOfChar, 0, j);
      }
      
    }
    catch (IOException localIOException)
    {
/* 311 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 312 */       localSQLException.fillInStackTrace();
/* 313 */       throw localSQLException;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
/* 319 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 151);
/* 320 */       localSQLException.fillInStackTrace();
/* 321 */       throw localSQLException;
    }
    
/* 325 */     if (localCLOB.isTemporary()) this.statement.addToTempLobsToFree(localCLOB);
/* 326 */     return localStringWriter.getBuffer().substring(0);
  }
  
  byte[] privateGetBytes(int paramInt)
    throws SQLException
  {
/* 340 */     return super.getBytes(paramInt);
  }
  
  byte[] getBytes(int paramInt)
    throws SQLException
  {
/* 358 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 359 */     localSQLException.fillInStackTrace();
/* 360 */     throw localSQLException;
  }
  
  long checksum(long paramLong, int paramInt)
    throws SQLException
  {
/* 368 */     unimpl("checksum");
/* 369 */     return -1L;
  }
  
/* 374 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/ClobAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */