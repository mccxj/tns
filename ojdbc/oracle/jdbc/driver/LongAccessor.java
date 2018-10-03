package oracle.jdbc.driver;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.SQLException;
class LongAccessor
  extends CharCommonAccessor
{
  OracleInputStream stream;
/*  28 */   int columnPosition = 0;
  
  LongAccessor(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, short paramShort, int paramInt3)
    throws SQLException
  {
/*  34 */     init(paramOracleStatement, 8, 8, paramShort, false);
    
/*  36 */     this.columnPosition = paramInt1;
    
/*  38 */     initForDataAccess(paramInt3, paramInt2, null);
  }
  
  LongAccessor(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, short paramShort)
    throws SQLException
  {
/*  47 */     init(paramOracleStatement, 8, 8, paramShort, false);
    
/*  49 */     this.columnPosition = paramInt1;
    
/*  51 */     initForDescribe(8, paramInt2, paramBoolean, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramShort, null);
    
/*  54 */     int i = paramOracleStatement.maxFieldSize;
    
/*  56 */     if ((i > 0) && ((paramInt2 == 0) || (i < paramInt2))) {
/*  57 */       paramInt2 = i;
    }
/*  59 */     initForDataAccess(0, paramInt2, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  67 */     if (paramInt1 != 0) {
/*  68 */       this.externalType = paramInt1;
    }
/*  70 */     this.isStream = true;
/*  71 */     this.isColumnNumberAware = true;
/*  72 */     this.internalTypeMaxLength = Integer.MAX_VALUE;
    
/*  74 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*  75 */       this.internalTypeMaxLength = paramInt2;
    }
    
/*  78 */     this.charLength = 0;
    
/*  81 */     this.stream = this.statement.connection.driverExtension.createInputStream(this.statement, this.columnPosition, this);
  }
  
  OracleInputStream initForNewRow()
    throws SQLException
  {
/*  91 */     this.stream = this.statement.connection.driverExtension.createInputStream(this.statement, this.columnPosition, this);
    
/*  94 */     return this.stream;
  }
  
  void updateColumnNumber(int paramInt)
  {
/* 103 */     paramInt++;
    
/* 106 */     this.columnPosition = paramInt;
    
/* 108 */     if (this.stream != null) {
/* 109 */       this.stream.columnIndex = paramInt;
    }
  }
  
  byte[] getBytes(int paramInt)
    throws SQLException
  {
/* 126 */     return getBytesInternal(paramInt);
  }
  
  byte[] getBytesInternal(int paramInt)
    throws SQLException
  {
/* 132 */     byte[] arrayOfByte1 = null;
    Object localObject;
/* 134 */     if (this.rowSpaceIndicator == null)
    {
/* 138 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 139 */       ((SQLException)localObject).fillInStackTrace();
/* 140 */       throw ((Throwable)localObject);
    }
    
/* 145 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 147 */       if (this.stream != null)
      {
/* 153 */         if (this.stream.closed)
        {
/* 155 */           localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 27);
/* 156 */           ((SQLException)localObject).fillInStackTrace();
/* 157 */           throw ((Throwable)localObject);
        }
        
/* 160 */         localObject = new ByteArrayOutputStream(1024);
/* 161 */         byte[] arrayOfByte2 = new byte['Ѐ'];
        
        try
        {
          int i;
/* 166 */           while ((i = this.stream.read(arrayOfByte2)) != -1)
          {
/* 168 */             ((ByteArrayOutputStream)localObject).write(arrayOfByte2, 0, i);
          }
          
        }
        catch (IOException localIOException)
        {
/* 174 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 175 */           localSQLException.fillInStackTrace();
/* 176 */           throw localSQLException;
        }
        
/* 180 */         arrayOfByte1 = ((ByteArrayOutputStream)localObject).toByteArray();
      }
    }
    
/* 184 */     return arrayOfByte1;
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 199 */     String str = null;
    
/* 201 */     byte[] arrayOfByte = getBytes(paramInt);
    
/* 203 */     if (arrayOfByte != null)
    {
/* 205 */       int i = Math.min(arrayOfByte.length, this.internalTypeMaxLength);
      
/* 209 */       if (i == 0)
      {
/* 214 */         str = "";
      }
/* 217 */       else if (this.formOfUse == 2) {
/* 218 */         str = this.statement.connection.conversion.NCharBytesToString(arrayOfByte, i);
      }
      else {
/* 221 */         str = this.statement.connection.conversion.CharBytesToString(arrayOfByte, i);
      }
    }
    
/* 225 */     return str;
  }
  
  InputStream getAsciiStream(int paramInt)
    throws SQLException
  {
/* 240 */     InputStream localInputStream = null;
    Object localObject;
/* 242 */     if (this.rowSpaceIndicator == null)
    {
/* 246 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 247 */       ((SQLException)localObject).fillInStackTrace();
/* 248 */       throw ((Throwable)localObject);
    }
    
/* 253 */     if ((this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) && (this.stream != null))
    {
/* 255 */       if (this.stream.closed)
      {
/* 257 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 27);
/* 258 */         ((SQLException)localObject).fillInStackTrace();
/* 259 */         throw ((Throwable)localObject);
      }
      
/* 262 */       localObject = this.statement.connection;
      
/* 264 */       localInputStream = ((PhysicalConnection)localObject).conversion.ConvertStream(this.stream, 0);
    }
    
/* 267 */     return localInputStream;
  }
  
  InputStream getUnicodeStream(int paramInt)
    throws SQLException
  {
/* 282 */     InputStream localInputStream = null;
    Object localObject;
/* 284 */     if (this.rowSpaceIndicator == null)
    {
/* 288 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 289 */       ((SQLException)localObject).fillInStackTrace();
/* 290 */       throw ((Throwable)localObject);
    }
    
/* 295 */     if ((this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) && (this.stream != null))
    {
/* 297 */       if (this.stream.closed)
      {
/* 299 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 27);
/* 300 */         ((SQLException)localObject).fillInStackTrace();
/* 301 */         throw ((Throwable)localObject);
      }
      
/* 304 */       localObject = this.statement.connection;
      
/* 306 */       localInputStream = ((PhysicalConnection)localObject).conversion.ConvertStream(this.stream, 1);
    }
    
/* 309 */     return localInputStream;
  }
  
  Reader getCharacterStream(int paramInt)
    throws SQLException
  {
/* 324 */     Reader localReader = null;
    Object localObject;
/* 326 */     if (this.rowSpaceIndicator == null)
    {
/* 330 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 331 */       ((SQLException)localObject).fillInStackTrace();
/* 332 */       throw ((Throwable)localObject);
    }
    
/* 337 */     if ((this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) && (this.stream != null))
    {
/* 339 */       if (this.stream.closed)
      {
/* 341 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 27);
/* 342 */         ((SQLException)localObject).fillInStackTrace();
/* 343 */         throw ((Throwable)localObject);
      }
      
/* 346 */       localObject = this.statement.connection;
      
/* 348 */       localReader = ((PhysicalConnection)localObject).conversion.ConvertCharacterStream(this.stream, 9, this.formOfUse);
    }
    
/* 352 */     return localReader;
  }
  
  InputStream getBinaryStream(int paramInt)
    throws SQLException
  {
/* 367 */     InputStream localInputStream = null;
    Object localObject;
/* 369 */     if (this.rowSpaceIndicator == null)
    {
/* 373 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 374 */       ((SQLException)localObject).fillInStackTrace();
/* 375 */       throw ((Throwable)localObject);
    }
    
/* 380 */     if ((this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1) && (this.stream != null))
    {
/* 382 */       if (this.stream.closed)
      {
/* 384 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 27);
/* 385 */         ((SQLException)localObject).fillInStackTrace();
/* 386 */         throw ((Throwable)localObject);
      }
      
/* 389 */       localObject = this.statement.connection;
      
/* 391 */       localInputStream = ((PhysicalConnection)localObject).conversion.ConvertStream(this.stream, 6);
    }
    
/* 394 */     return localInputStream;
  }
  
  public String toString()
  {
/* 402 */     return "LongAccessor@" + Integer.toHexString(hashCode()) + "{columnPosition = " + this.columnPosition + "}";
  }
  
/* 410 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/LongAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */