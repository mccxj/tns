package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import oracle.jdbc.internal.OracleStatement.SqlKind;
import oracle.sql.BLOB;
class T4CBlobAccessor
  extends BlobAccessor
{
  T4CMAREngine mare;
  
  T4CBlobAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  48 */     super(paramOracleStatement, 4000, paramShort, paramInt2, paramBoolean);
    
/*  50 */     this.mare = paramT4CMAREngine;
  }
  
  T4CBlobAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, int paramInt7, int paramInt8, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  60 */     super(paramOracleStatement, 4000, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort);
    
/*  64 */     this.mare = paramT4CMAREngine;
/*  65 */     this.definedColumnType = paramInt7;
/*  66 */     this.definedColumnSize = paramInt8;
  }
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/*  74 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/*  81 */       this.mare.unmarshalUB2();
/*  82 */       this.mare.unmarshalUB2();
    }
/*  84 */     else if (this.statement.connection.versionNumber < 9200)
    {
/*  88 */       this.mare.unmarshalSB2();
      
/*  90 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/*  91 */         this.mare.unmarshalSB2();
      }
/*  93 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/*  95 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
/* 104 */   final int[] meta = new int[1];
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/* 119 */     if (this.isUseLess)
    {
/* 121 */       this.lastRowProcessed += 1;
      
/* 123 */       return false;
    }
    
/* 128 */     if (this.rowSpaceIndicator == null)
    {
/* 130 */       i = (int)this.mare.unmarshalUB4();
      
/* 132 */       if (i == 0)
      {
/* 134 */         this.meta[0] = -1;
        
/* 136 */         processIndicator(0);
        
/* 138 */         this.lastRowProcessed += 1;
        
/* 140 */         return false;
      }
      
/* 144 */       byte[] arrayOfByte = new byte['㺀'];
      
/* 146 */       this.mare.unmarshalCLR(arrayOfByte, 0, this.meta);
/* 147 */       processIndicator(this.meta[0]);
      
/* 149 */       this.lastRowProcessed += 1;
      
/* 151 */       return false;
    }
    
/* 154 */     int i = this.columnIndex + this.lastRowProcessed * this.byteLength;
/* 155 */     int j = this.indicatorIndex + this.lastRowProcessed;
/* 156 */     int k = this.lengthIndex + this.lastRowProcessed;
    
/* 160 */     if (this.isNullByDescribe)
    {
/* 162 */       this.rowSpaceIndicator[j] = -1;
/* 163 */       this.rowSpaceIndicator[k] = 0;
/* 164 */       this.lastRowProcessed += 1;
      
/* 166 */       if (this.statement.connection.versionNumber < 9200) {
/* 167 */         processIndicator(0);
      }
/* 169 */       return false;
    }
    
/* 172 */     int m = (int)this.mare.unmarshalUB4();
    
/* 174 */     if (m == 0)
    {
/* 176 */       this.meta[0] = -1;
      
/* 178 */       processIndicator(0);
      
/* 181 */       this.rowSpaceIndicator[j] = -1;
/* 182 */       this.rowSpaceIndicator[k] = 0;
      
/* 184 */       this.lastRowProcessed += 1;
      
/* 186 */       return false;
    }
/* 188 */     if (this.lobPrefetchSizeForThisColumn != -1)
/* 189 */       handlePrefetch();
/* 190 */     this.mare.unmarshalCLR(this.rowSpaceByte, i, this.meta, this.byteLength);
    
/* 192 */     processIndicator(this.meta[0]);
    
/* 194 */     if (this.meta[0] == 0)
    {
/* 198 */       this.rowSpaceIndicator[j] = -1;
/* 199 */       this.rowSpaceIndicator[k] = 0;
    }
    else
    {
/* 203 */       this.rowSpaceIndicator[k] = ((short)this.meta[0]);
/* 204 */       this.rowSpaceIndicator[j] = 0;
    }
    
/* 207 */     this.lastRowProcessed += 1;
    
/* 209 */     return false;
  }
  
  void copyRow()
    throws SQLException, IOException
  {
    int i;
    
/* 219 */     if (this.lastRowProcessed == 0) {
/* 220 */       i = this.statement.rowPrefetchInLastFetch - 1;
    } else {
/* 222 */       i = this.lastRowProcessed - 1;
    }
    
/* 225 */     int j = this.columnIndex + this.lastRowProcessed * this.byteLength;
/* 226 */     int k = this.columnIndex + i * this.byteLength;
/* 227 */     int m = this.indicatorIndex + this.lastRowProcessed;
/* 228 */     int n = this.indicatorIndex + i;
/* 229 */     int i1 = this.lengthIndex + this.lastRowProcessed;
/* 230 */     int i2 = this.lengthIndex + i;
/* 231 */     int i3 = this.rowSpaceIndicator[i2];
/* 232 */     int i4 = this.metaDataIndex + this.lastRowProcessed * 1;
    
/* 234 */     int i5 = this.metaDataIndex + i * 1;
    
/* 238 */     this.rowSpaceIndicator[i1] = ((short)i3);
/* 239 */     this.rowSpaceIndicator[m] = this.rowSpaceIndicator[n];
    
/* 242 */     if (!this.isNullByDescribe)
    {
/* 244 */       System.arraycopy(this.rowSpaceByte, k, this.rowSpaceByte, j, i3);
    }
    
/* 249 */     System.arraycopy(this.rowSpaceMetaData, i5, this.rowSpaceMetaData, i4, 1);
    
/* 252 */     this.lastRowProcessed += 1;
  }
  
  void saveDataFromOldDefineBuffers(byte[] paramArrayOfByte, char[] paramArrayOfChar, short[] paramArrayOfShort, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 265 */     int i = this.columnIndex + (paramInt2 - 1) * this.byteLength;
    
/* 267 */     int j = this.columnIndexLastRow + (paramInt1 - 1) * this.byteLength;
    
/* 269 */     int k = this.indicatorIndex + paramInt2 - 1;
/* 270 */     int m = this.indicatorIndexLastRow + paramInt1 - 1;
/* 271 */     int n = this.lengthIndex + paramInt2 - 1;
/* 272 */     int i1 = this.lengthIndexLastRow + paramInt1 - 1;
/* 273 */     int i2 = paramArrayOfShort[i1];
    
/* 275 */     this.rowSpaceIndicator[n] = ((short)i2);
/* 276 */     this.rowSpaceIndicator[k] = paramArrayOfShort[m];
    
/* 279 */     if (i2 != 0)
    {
/* 281 */       System.arraycopy(paramArrayOfByte, j, this.rowSpaceByte, i, i2);
    }
  }
  
  byte[][] checkAndAllocateLobPrefetchMemory(byte[][] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
/* 297 */     Object localObject = paramArrayOfByte;
/* 298 */     if (localObject == null)
    {
/* 300 */       localObject = new byte[Math.max(paramInt1, paramInt2 + 1)][];
/* 301 */       localObject[paramInt2] = new byte[paramInt3];
    }
    else
    {
/* 305 */       if (localObject.length < paramInt2 + 1)
      {
/* 308 */         byte[][] arrayOfByte = new byte[(paramInt2 + 1) * 2][];
/* 309 */         System.arraycopy(localObject, 0, arrayOfByte, 0, localObject.length);
/* 310 */         localObject = arrayOfByte;
      }
      
/* 313 */       if ((localObject[paramInt2] == null) || (localObject[paramInt2].length < paramInt3))
      {
/* 317 */         localObject[paramInt2] = new byte[paramInt3]; }
    }
/* 319 */     return (byte[][])localObject;
  }
  
  char[][] checkAndAllocateLobPrefetchMemory(char[][] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
  {
/* 327 */     Object localObject = paramArrayOfChar;
/* 328 */     if (localObject == null)
    {
/* 330 */       localObject = new char[Math.max(paramInt1, paramInt2 + 1)][];
/* 331 */       localObject[paramInt2] = new char[paramInt3];
    }
    else
    {
/* 335 */       if (localObject.length < paramInt2 + 1)
      {
/* 338 */         char[][] arrayOfChar = new char[(paramInt2 + 1) * 2][];
/* 339 */         System.arraycopy(localObject, 0, arrayOfChar, 0, localObject.length);
/* 340 */         localObject = arrayOfChar;
      }
      
/* 343 */       if ((localObject[paramInt2] == null) || (localObject[paramInt2].length < paramInt3))
      {
/* 347 */         localObject[paramInt2] = new char[paramInt3]; }
    }
/* 349 */     return (char[][])localObject;
  }
  
  long[] checkAndAllocateLobPrefetchMemory(long[] paramArrayOfLong, int paramInt1, int paramInt2)
  {
/* 354 */     Object localObject = paramArrayOfLong;
/* 355 */     if (localObject == null) {
/* 356 */       localObject = new long[Math.max(paramInt1, paramInt2 + 1)];
/* 357 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 359 */       long[] arrayOfLong = new long[(paramInt2 + 1) * 2];
/* 360 */       System.arraycopy(localObject, 0, arrayOfLong, 0, localObject.length);
/* 361 */       localObject = arrayOfLong;
    }
/* 363 */     return (long[])localObject;
  }
  
  int[] checkAndAllocateLobPrefetchMemory(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
/* 368 */     Object localObject = paramArrayOfInt;
/* 369 */     if (localObject == null) {
/* 370 */       localObject = new int[Math.max(paramInt1, paramInt2 + 1)];
/* 371 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 373 */       int[] arrayOfInt = new int[(paramInt2 + 1) * 2];
/* 374 */       System.arraycopy(localObject, 0, arrayOfInt, 0, localObject.length);
/* 375 */       localObject = arrayOfInt;
    }
/* 377 */     return (int[])localObject;
  }
  
  short[] checkAndAllocateLobPrefetchMemory(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
/* 382 */     Object localObject = paramArrayOfShort;
/* 383 */     if (localObject == null) {
/* 384 */       localObject = new short[Math.max(paramInt1, paramInt2 + 1)];
/* 385 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 387 */       short[] arrayOfShort = new short[(paramInt2 + 1) * 2];
/* 388 */       System.arraycopy(localObject, 0, arrayOfShort, 0, localObject.length);
/* 389 */       localObject = arrayOfShort;
    }
/* 391 */     return (short[])localObject;
  }
  
  byte[] checkAndAllocateLobPrefetchMemory(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
/* 396 */     Object localObject = paramArrayOfByte;
/* 397 */     if (localObject == null) {
/* 398 */       localObject = new byte[Math.max(paramInt1, paramInt2 + 1)];
/* 399 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 401 */       byte[] arrayOfByte = new byte[(paramInt2 + 1) * 2];
/* 402 */       System.arraycopy(localObject, 0, arrayOfByte, 0, localObject.length);
/* 403 */       localObject = arrayOfByte;
    }
/* 405 */     return (byte[])localObject;
  }
  
  boolean[] checkAndAllocateLobPrefetchMemory(boolean[] paramArrayOfBoolean, int paramInt1, int paramInt2)
  {
/* 410 */     Object localObject = paramArrayOfBoolean;
/* 411 */     if (localObject == null) {
/* 412 */       localObject = new boolean[Math.max(paramInt1, paramInt2 + 1)];
/* 413 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 415 */       boolean[] arrayOfBoolean = new boolean[(paramInt2 + 1) * 2];
/* 416 */       System.arraycopy(localObject, 0, arrayOfBoolean, 0, localObject.length);
/* 417 */       localObject = arrayOfBoolean;
    }
/* 419 */     return (boolean[])localObject;
  }
  
  byte[] getBytes(int paramInt)
    throws SQLException
  {
/* 429 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 430 */       return null;
    }
    
/* 433 */     if ((this.lobPrefetchSizeForThisColumn != -1) && (this.prefetchedLobSize != null))
    {
/* 436 */       if (this.prefetchedLobSize[paramInt] > 2147483647L)
      {
/* 442 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 151);
/* 443 */         ((SQLException)localObject).fillInStackTrace();
/* 444 */         throw ((Throwable)localObject);
      }
      
/* 447 */       if ((this.prefetchedLobData != null) && (this.lobPrefetchSizeForThisColumn >= this.prefetchedLobSize[paramInt]))
      {
/* 451 */         localObject = new byte[(int)this.prefetchedLobSize[paramInt]];
/* 452 */         System.arraycopy(this.prefetchedLobData[paramInt], 0, localObject, 0, (int)this.prefetchedLobSize[paramInt]);
/* 453 */         return (byte[])localObject;
      }
      
/* 459 */       Object localObject = getBLOB(paramInt);
/* 460 */       if (localObject == null)
/* 461 */         return null;
/* 462 */       return ((BLOB)localObject).getBytes(1L, (int)this.prefetchedLobSize[paramInt]);
    }
    
/* 467 */     return super.getBytes(paramInt);
  }
  
  void handlePrefetch()
    throws SQLException, IOException
  {
/* 476 */     this.prefetchedLobSize = checkAndAllocateLobPrefetchMemory(this.prefetchedLobSize, this.statement.rowPrefetchInLastFetch, this.lastRowProcessed);
    
/* 478 */     this.prefetchedLobSize[this.lastRowProcessed] = this.mare.unmarshalSB8();
    
/* 480 */     this.prefetchedLobChunkSize = checkAndAllocateLobPrefetchMemory(this.prefetchedLobChunkSize, this.statement.rowPrefetchInLastFetch, this.lastRowProcessed);
    
/* 482 */     this.prefetchedLobChunkSize[this.lastRowProcessed] = ((int)this.mare.unmarshalUB4());
    
/* 486 */     if (this.lobPrefetchSizeForThisColumn > 0)
    {
/* 488 */       this.prefetchedLobDataL = checkAndAllocateLobPrefetchMemory(this.prefetchedLobDataL, this.statement.rowPrefetchInLastFetch, this.lastRowProcessed);
      
/* 500 */       int i = this.lobPrefetchSizeForThisColumn;
      
/* 502 */       this.prefetchedLobData = checkAndAllocateLobPrefetchMemory(this.prefetchedLobData, this.statement.rowPrefetchInLastFetch, this.lastRowProcessed, i);
      
/* 507 */       disablePrefetchBufferForPreviousBLOBs(this.lastRowProcessed);
/* 508 */       this.mare.unmarshalCLR(this.prefetchedLobData[this.lastRowProcessed], 0, this.meta);
/* 509 */       this.prefetchedLobDataL[this.lastRowProcessed] = this.meta[0];
    }
  }
  
  void initializeBlobForPrefetch(int paramInt, BLOB paramBLOB)
    throws SQLException
  {
/* 525 */     if (paramInt >= 0)
    {
/* 527 */       saveBLOBReference(paramInt, paramBLOB);
/* 528 */       paramBLOB.setPrefetchedData(this.prefetchedLobData[paramInt], this.prefetchedLobDataL[paramInt]);
    }
  }
  
/* 533 */   ArrayList<LinkedList<BLOB>> registeredBLOBs = new ArrayList(10);
  
  private void saveBLOBReference(int paramInt, BLOB paramBLOB)
  {
/* 541 */     LinkedList localLinkedList = null;
/* 542 */     if (this.registeredBLOBs.size() > paramInt)
    {
/* 544 */       localLinkedList = (LinkedList)this.registeredBLOBs.get(paramInt);
    }
    else
    {
/* 548 */       localLinkedList = new LinkedList();
/* 549 */       while (this.registeredBLOBs.size() < paramInt)
/* 550 */         this.registeredBLOBs.add(new LinkedList());
/* 551 */       this.registeredBLOBs.add(paramInt, localLinkedList);
    }
    
/* 554 */     if (localLinkedList == null) localLinkedList = new LinkedList();
/* 555 */     localLinkedList.add(paramBLOB);
  }
  
  private void disablePrefetchBufferForPreviousBLOBs(int paramInt)
  {
/* 567 */     if (this.registeredBLOBs.size() > paramInt)
    {
/* 569 */       LinkedList localLinkedList = (LinkedList)this.registeredBLOBs.get(paramInt);
/* 570 */       if ((localLinkedList != null) && (!localLinkedList.isEmpty()))
      {
/* 572 */         ListIterator localListIterator = localLinkedList.listIterator();
/* 573 */         while (localListIterator.hasNext()) ((BLOB)localListIterator.next()).setPrefetchedData(null);
/* 574 */         localLinkedList.clear();
      }
    }
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 582 */     if (this.definedColumnType == 0) {
/* 583 */       return super.getObject(paramInt);
    }
    
/* 586 */     Object localObject = null;
    SQLException localSQLException;
/* 588 */     if (this.rowSpaceIndicator == null)
    {
/* 590 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 591 */       localSQLException.fillInStackTrace();
/* 592 */       throw localSQLException;
    }
    
/* 595 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 597 */       switch (this.definedColumnType)
      {
      case 2004: 
/* 601 */         return getBLOB(paramInt);
      }
      
      
/* 605 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 606 */       localSQLException.fillInStackTrace();
/* 607 */       throw localSQLException;
    }
    
/* 613 */     return localObject;
  }
  
/* 618 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CBlobAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */