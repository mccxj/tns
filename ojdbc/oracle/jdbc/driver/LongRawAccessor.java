package oracle.jdbc.driver;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.SQLException;
class LongRawAccessor
  extends RawCommonAccessor
{
  OracleInputStream stream;
/*  29 */   int columnPosition = 0;
  
  LongRawAccessor(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, short paramShort, int paramInt3)
    throws SQLException
  {
/*  36 */     init(paramOracleStatement, 24, 24, paramShort, false);
    
/*  38 */     this.columnPosition = paramInt1;
    
/*  40 */     initForDataAccess(paramInt3, paramInt2, null);
  }
  
  LongRawAccessor(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, short paramShort)
    throws SQLException
  {
/*  49 */     init(paramOracleStatement, 24, 24, paramShort, false);
    
/*  51 */     this.columnPosition = paramInt1;
    
/*  53 */     initForDescribe(24, paramInt2, paramBoolean, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramShort, null);
    
/*  56 */     int i = paramOracleStatement.maxFieldSize;
    
/*  58 */     if ((i > 0) && ((paramInt2 == 0) || (i < paramInt2))) {
/*  59 */       paramInt2 = i;
    }
/*  61 */     initForDataAccess(0, paramInt2, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  69 */     if (paramInt1 != 0) {
/*  70 */       this.externalType = paramInt1;
    }
/*  72 */     this.isStream = true;
/*  73 */     this.isColumnNumberAware = true;
/*  74 */     this.internalTypeMaxLength = Integer.MAX_VALUE;
    
/*  76 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*  77 */       this.internalTypeMaxLength = paramInt2;
    }
/*  79 */     this.byteLength = 0;
    
/*  82 */     this.stream = this.statement.connection.driverExtension.createInputStream(this.statement, this.columnPosition, this);
  }
  
  OracleInputStream initForNewRow()
    throws SQLException
  {
/*  97 */     this.stream = this.statement.connection.driverExtension.createInputStream(this.statement, this.columnPosition, this);
    
/* 102 */     return this.stream;
  }
  
  void updateColumnNumber(int paramInt)
  {
/* 111 */     paramInt++;
    
/* 114 */     this.columnPosition = paramInt;
    
/* 116 */     if (this.stream != null) {
/* 117 */       this.stream.columnIndex = paramInt;
    }
  }
  
  byte[] getBytes(int paramInt)
    throws SQLException
  {
/* 138 */     byte[] arrayOfByte1 = null;
    Object localObject;
/* 140 */     if (this.rowSpaceIndicator == null)
    {
/* 144 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 145 */       ((SQLException)localObject).fillInStackTrace();
/* 146 */       throw ((Throwable)localObject);
    }
    
/* 151 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 153 */       if (this.stream != null)
      {
/* 159 */         if (this.stream.closed)
        {
/* 161 */           localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 27);
/* 162 */           ((SQLException)localObject).fillInStackTrace();
/* 163 */           throw ((Throwable)localObject);
        }
        
/* 166 */         localObject = new ByteArrayOutputStream(1024);
/* 167 */         byte[] arrayOfByte2 = new byte['Ѐ'];
        
        try
        {
          int i;
/* 172 */           while ((i = this.stream.read(arrayOfByte2)) != -1)
          {
/* 174 */             ((ByteArrayOutputStream)localObject).write(arrayOfByte2, 0, i);
          }
          
        }
        catch (IOException localIOException)
        {
/* 180 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 181 */           localSQLException.fillInStackTrace();
/* 182 */           throw localSQLException;
        }
        
/* 186 */         arrayOfByte1 = ((ByteArrayOutputStream)localObject).toByteArray();
      }
    }
    
/* 192 */     return arrayOfByte1;
  }
  
  InputStream getAsciiStream(int paramInt)
    throws SQLException
  {
/* 211 */     InputStream localInputStream = null;
    Object localObject;
/* 213 */     if (this.rowSpaceIndicator == null)
    {
/* 217 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 218 */       ((SQLException)localObject).fillInStackTrace();
/* 219 */       throw ((Throwable)localObject);
    }
    
/* 224 */     if ((this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) && (this.stream != null))
    {
/* 226 */       if (this.stream.closed)
      {
/* 228 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 27);
/* 229 */         ((SQLException)localObject).fillInStackTrace();
/* 230 */         throw ((Throwable)localObject);
      }
      
/* 233 */       localObject = this.statement.connection;
      
/* 235 */       localInputStream = ((PhysicalConnection)localObject).conversion.ConvertStream(this.stream, 2);
    }
    
/* 240 */     return localInputStream;
  }
  
  InputStream getUnicodeStream(int paramInt)
    throws SQLException
  {
/* 257 */     InputStream localInputStream = null;
    Object localObject;
/* 259 */     if (this.rowSpaceIndicator == null)
    {
/* 263 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 264 */       ((SQLException)localObject).fillInStackTrace();
/* 265 */       throw ((Throwable)localObject);
    }
    
/* 270 */     if ((this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) && (this.stream != null))
    {
/* 272 */       if (this.stream.closed)
      {
/* 274 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 27);
/* 275 */         ((SQLException)localObject).fillInStackTrace();
/* 276 */         throw ((Throwable)localObject);
      }
      
/* 279 */       localObject = this.statement.connection;
      
/* 281 */       localInputStream = ((PhysicalConnection)localObject).conversion.ConvertStream(this.stream, 3);
    }
    
/* 286 */     return localInputStream;
  }
  
  Reader getCharacterStream(int paramInt)
    throws SQLException
  {
/* 303 */     Reader localReader = null;
    Object localObject;
/* 305 */     if (this.rowSpaceIndicator == null)
    {
/* 309 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 310 */       ((SQLException)localObject).fillInStackTrace();
/* 311 */       throw ((Throwable)localObject);
    }
    
/* 316 */     if ((this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) && (this.stream != null))
    {
/* 318 */       if (this.stream.closed)
      {
/* 320 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 27);
/* 321 */         ((SQLException)localObject).fillInStackTrace();
/* 322 */         throw ((Throwable)localObject);
      }
      
/* 325 */       localObject = this.statement.connection;
      
/* 327 */       localReader = ((PhysicalConnection)localObject).conversion.ConvertCharacterStream(this.stream, 8);
    }
    
/* 333 */     return localReader;
  }
  
  InputStream getBinaryStream(int paramInt)
    throws SQLException
  {
/* 350 */     InputStream localInputStream = null;
    Object localObject;
/* 352 */     if (this.rowSpaceIndicator == null)
    {
/* 356 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 357 */       ((SQLException)localObject).fillInStackTrace();
/* 358 */       throw ((Throwable)localObject);
    }
    
/* 363 */     if ((this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) && (this.stream != null))
    {
/* 365 */       if (this.stream.closed)
      {
/* 367 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 27);
/* 368 */         ((SQLException)localObject).fillInStackTrace();
/* 369 */         throw ((Throwable)localObject);
      }
      
/* 372 */       localObject = this.statement.connection;
      
/* 374 */       localInputStream = ((PhysicalConnection)localObject).conversion.ConvertStream(this.stream, 6);
    }
    
/* 379 */     return localInputStream;
  }
  
  public String toString()
  {
/* 387 */     return "LongRawAccessor@" + Integer.toHexString(hashCode()) + "{columnPosition = " + this.columnPosition + "}";
  }
  
/* 394 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/LongRawAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */