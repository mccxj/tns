package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class T4CBfileAccessor
  extends BfileAccessor
{
  static final int maxLength = 530;
  T4CMAREngine mare;
  
  T4CBfileAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  47 */     super(paramOracleStatement, 530, paramShort, paramInt2, paramBoolean);
    
/*  49 */     this.mare = paramT4CMAREngine;
  }
  
  T4CBfileAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, int paramInt7, int paramInt8, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  59 */     super(paramOracleStatement, 530, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort);
    
/*  63 */     this.mare = paramT4CMAREngine;
/*  64 */     this.definedColumnType = paramInt7;
/*  65 */     this.definedColumnSize = paramInt8;
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/*  77 */     String str = super.getString(paramInt);
    
/*  79 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
    {
/*  81 */       str = str.substring(0, this.definedColumnSize);
    }
/*  83 */     return str;
  }
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/*  92 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/*  99 */       this.mare.unmarshalUB2();
/* 100 */       this.mare.unmarshalUB2();
    }
/* 102 */     else if (this.statement.connection.versionNumber < 9200)
    {
/* 106 */       this.mare.unmarshalSB2();
      
/* 108 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/* 109 */         this.mare.unmarshalSB2();
      }
/* 111 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/* 113 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
/* 122 */   final int[] meta = new int[1];
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/* 137 */     if (this.isUseLess)
    {
/* 139 */       this.lastRowProcessed += 1;
      
/* 141 */       return false;
    }
    
/* 146 */     if (this.rowSpaceIndicator == null)
    {
/* 148 */       i = (int)this.mare.unmarshalUB4();
      
/* 150 */       if (i == 0)
      {
/* 152 */         this.meta[0] = -1;
        
/* 154 */         processIndicator(0);
        
/* 156 */         this.lastRowProcessed += 1;
        
/* 158 */         return false;
      }
      
/* 162 */       byte[] arrayOfByte = new byte['㺀'];
      
/* 164 */       this.mare.unmarshalCLR(arrayOfByte, 0, this.meta);
/* 165 */       processIndicator(this.meta[0]);
      
/* 167 */       this.lastRowProcessed += 1;
      
/* 169 */       return false;
    }
    
/* 172 */     int i = this.columnIndex + this.lastRowProcessed * this.byteLength;
/* 173 */     int j = this.indicatorIndex + this.lastRowProcessed;
/* 174 */     int k = this.lengthIndex + this.lastRowProcessed;
    
/* 178 */     if (this.isNullByDescribe)
    {
/* 180 */       this.rowSpaceIndicator[j] = -1;
/* 181 */       this.rowSpaceIndicator[k] = 0;
/* 182 */       this.lastRowProcessed += 1;
      
/* 184 */       if (this.statement.connection.versionNumber < 9200) {
/* 185 */         processIndicator(0);
      }
/* 187 */       return false;
    }
    
/* 190 */     int m = (int)this.mare.unmarshalUB4();
    
/* 192 */     if (m == 0)
    {
/* 194 */       this.meta[0] = -1;
      
/* 196 */       processIndicator(0);
      
/* 199 */       this.rowSpaceIndicator[j] = -1;
/* 200 */       this.rowSpaceIndicator[k] = 0;
      
/* 202 */       this.lastRowProcessed += 1;
      
/* 204 */       return false;
    }
/* 206 */     if (this.lobPrefetchSizeForThisColumn != -1)
/* 207 */       handlePrefetch();
/* 208 */     this.mare.unmarshalCLR(this.rowSpaceByte, i, this.meta, this.byteLength);
    
/* 210 */     processIndicator(this.meta[0]);
    
/* 212 */     if (this.meta[0] == 0)
    {
/* 216 */       this.rowSpaceIndicator[j] = -1;
/* 217 */       this.rowSpaceIndicator[k] = 0;
    }
    else
    {
/* 221 */       this.rowSpaceIndicator[k] = ((short)this.meta[0]);
/* 222 */       this.rowSpaceIndicator[j] = 0;
    }
    
/* 225 */     this.lastRowProcessed += 1;
    
/* 227 */     return false;
  }
  
  void copyRow()
    throws SQLException, IOException
  {
    int i;
    
/* 237 */     if (this.lastRowProcessed == 0) {
/* 238 */       i = this.statement.rowPrefetchInLastFetch - 1;
    } else {
/* 240 */       i = this.lastRowProcessed - 1;
    }
    
/* 243 */     int j = this.columnIndex + this.lastRowProcessed * this.byteLength;
/* 244 */     int k = this.columnIndex + i * this.byteLength;
/* 245 */     int m = this.indicatorIndex + this.lastRowProcessed;
/* 246 */     int n = this.indicatorIndex + i;
/* 247 */     int i1 = this.lengthIndex + this.lastRowProcessed;
/* 248 */     int i2 = this.lengthIndex + i;
/* 249 */     int i3 = this.rowSpaceIndicator[i2];
/* 250 */     int i4 = this.metaDataIndex + this.lastRowProcessed * 1;
    
/* 252 */     int i5 = this.metaDataIndex + i * 1;
    
/* 256 */     this.rowSpaceIndicator[i1] = ((short)i3);
/* 257 */     this.rowSpaceIndicator[m] = this.rowSpaceIndicator[n];
    
/* 260 */     if (!this.isNullByDescribe)
    {
/* 262 */       System.arraycopy(this.rowSpaceByte, k, this.rowSpaceByte, j, i3);
    }
    
/* 267 */     System.arraycopy(this.rowSpaceMetaData, i5, this.rowSpaceMetaData, i4, 1);
    
/* 270 */     this.lastRowProcessed += 1;
  }
  
  void saveDataFromOldDefineBuffers(byte[] paramArrayOfByte, char[] paramArrayOfChar, short[] paramArrayOfShort, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 283 */     int i = this.columnIndex + (paramInt2 - 1) * this.byteLength;
    
/* 285 */     int j = this.columnIndexLastRow + (paramInt1 - 1) * this.byteLength;
    
/* 287 */     int k = this.indicatorIndex + paramInt2 - 1;
/* 288 */     int m = this.indicatorIndexLastRow + paramInt1 - 1;
/* 289 */     int n = this.lengthIndex + paramInt2 - 1;
/* 290 */     int i1 = this.lengthIndexLastRow + paramInt1 - 1;
/* 291 */     int i2 = paramArrayOfShort[i1];
    
/* 293 */     this.rowSpaceIndicator[n] = ((short)i2);
/* 294 */     this.rowSpaceIndicator[k] = paramArrayOfShort[m];
    
/* 297 */     if (i2 != 0)
    {
/* 299 */       System.arraycopy(paramArrayOfByte, j, this.rowSpaceByte, i, i2);
    }
  }
  
  byte[][] checkAndAllocateLobPrefetchMemory(byte[][] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
/* 315 */     Object localObject = paramArrayOfByte;
/* 316 */     if (localObject == null)
    {
/* 318 */       localObject = new byte[Math.max(paramInt1, paramInt2 + 1)][];
/* 319 */       localObject[paramInt2] = new byte[paramInt3];
    }
    else
    {
/* 323 */       if (localObject.length < paramInt2 + 1)
      {
/* 326 */         byte[][] arrayOfByte = new byte[(paramInt2 + 1) * 2][];
/* 327 */         System.arraycopy(localObject, 0, arrayOfByte, 0, localObject.length);
/* 328 */         localObject = arrayOfByte;
      }
      
/* 331 */       if ((localObject[paramInt2] == null) || (localObject[paramInt2].length < paramInt3))
      {
/* 335 */         localObject[paramInt2] = new byte[paramInt3]; }
    }
/* 337 */     return (byte[][])localObject;
  }
  
  char[][] checkAndAllocateLobPrefetchMemory(char[][] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
  {
/* 345 */     Object localObject = paramArrayOfChar;
/* 346 */     if (localObject == null)
    {
/* 348 */       localObject = new char[Math.max(paramInt1, paramInt2 + 1)][];
/* 349 */       localObject[paramInt2] = new char[paramInt3];
    }
    else
    {
/* 353 */       if (localObject.length < paramInt2 + 1)
      {
/* 356 */         char[][] arrayOfChar = new char[(paramInt2 + 1) * 2][];
/* 357 */         System.arraycopy(localObject, 0, arrayOfChar, 0, localObject.length);
/* 358 */         localObject = arrayOfChar;
      }
      
/* 361 */       if ((localObject[paramInt2] == null) || (localObject[paramInt2].length < paramInt3))
      {
/* 365 */         localObject[paramInt2] = new char[paramInt3]; }
    }
/* 367 */     return (char[][])localObject;
  }
  
  long[] checkAndAllocateLobPrefetchMemory(long[] paramArrayOfLong, int paramInt1, int paramInt2)
  {
/* 372 */     Object localObject = paramArrayOfLong;
/* 373 */     if (localObject == null) {
/* 374 */       localObject = new long[Math.max(paramInt1, paramInt2 + 1)];
/* 375 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 377 */       long[] arrayOfLong = new long[(paramInt2 + 1) * 2];
/* 378 */       System.arraycopy(localObject, 0, arrayOfLong, 0, localObject.length);
/* 379 */       localObject = arrayOfLong;
    }
/* 381 */     return (long[])localObject;
  }
  
  int[] checkAndAllocateLobPrefetchMemory(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
/* 386 */     Object localObject = paramArrayOfInt;
/* 387 */     if (localObject == null) {
/* 388 */       localObject = new int[Math.max(paramInt1, paramInt2 + 1)];
/* 389 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 391 */       int[] arrayOfInt = new int[(paramInt2 + 1) * 2];
/* 392 */       System.arraycopy(localObject, 0, arrayOfInt, 0, localObject.length);
/* 393 */       localObject = arrayOfInt;
    }
/* 395 */     return (int[])localObject;
  }
  
  short[] checkAndAllocateLobPrefetchMemory(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
/* 400 */     Object localObject = paramArrayOfShort;
/* 401 */     if (localObject == null) {
/* 402 */       localObject = new short[Math.max(paramInt1, paramInt2 + 1)];
/* 403 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 405 */       short[] arrayOfShort = new short[(paramInt2 + 1) * 2];
/* 406 */       System.arraycopy(localObject, 0, arrayOfShort, 0, localObject.length);
/* 407 */       localObject = arrayOfShort;
    }
/* 409 */     return (short[])localObject;
  }
  
  byte[] checkAndAllocateLobPrefetchMemory(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
/* 414 */     Object localObject = paramArrayOfByte;
/* 415 */     if (localObject == null) {
/* 416 */       localObject = new byte[Math.max(paramInt1, paramInt2 + 1)];
/* 417 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 419 */       byte[] arrayOfByte = new byte[(paramInt2 + 1) * 2];
/* 420 */       System.arraycopy(localObject, 0, arrayOfByte, 0, localObject.length);
/* 421 */       localObject = arrayOfByte;
    }
/* 423 */     return (byte[])localObject;
  }
  
  boolean[] checkAndAllocateLobPrefetchMemory(boolean[] paramArrayOfBoolean, int paramInt1, int paramInt2)
  {
/* 428 */     Object localObject = paramArrayOfBoolean;
/* 429 */     if (localObject == null) {
/* 430 */       localObject = new boolean[Math.max(paramInt1, paramInt2 + 1)];
/* 431 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 433 */       boolean[] arrayOfBoolean = new boolean[(paramInt2 + 1) * 2];
/* 434 */       System.arraycopy(localObject, 0, arrayOfBoolean, 0, localObject.length);
/* 435 */       localObject = arrayOfBoolean;
    }
/* 437 */     return (boolean[])localObject;
  }
  
  void handlePrefetch()
    throws SQLException, IOException
  {
/* 448 */     this.prefetchedLobSize = checkAndAllocateLobPrefetchMemory(this.prefetchedLobSize, this.statement.rowPrefetchInLastFetch, this.lastRowProcessed);
    
/* 450 */     this.prefetchedLobSize[this.lastRowProcessed] = ((int)this.mare.unmarshalSB8());
    
/* 453 */     if (this.lobPrefetchSizeForThisColumn > 0)
/* 454 */       this.mare.unmarshalUB1();
  }
  
  Object getObject(int paramInt) throws SQLException {
/* 458 */     if (this.definedColumnType == 0) {
/* 459 */       return super.getObject(paramInt);
    }
    
/* 462 */     Object localObject = null;
    SQLException localSQLException;
/* 464 */     if (this.rowSpaceIndicator == null)
    {
/* 466 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 467 */       localSQLException.fillInStackTrace();
/* 468 */       throw localSQLException;
    }
    
/* 471 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 473 */       switch (this.definedColumnType)
      {
      case -13: 
/* 477 */         return getBFILE(paramInt);
      }
      
      
/* 481 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 482 */       localSQLException.fillInStackTrace();
/* 483 */       throw localSQLException;
    }
    
/* 489 */     return localObject;
  }
  
/* 494 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CBfileAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */