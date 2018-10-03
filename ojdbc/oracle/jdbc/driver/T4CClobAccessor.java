package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import oracle.jdbc.internal.OracleStatement.SqlKind;
import oracle.sql.CLOB;
import oracle.sql.CharacterSet;
class T4CClobAccessor
  extends ClobAccessor
{
  T4CMAREngine mare;
/*  28 */   short[] prefetchedClobCharset = null;
  
/*  31 */   boolean[] prefetchedClobDBVary = null;
  
  T4CClobAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  54 */     super(paramOracleStatement, 4000, paramShort, paramInt2, paramBoolean);
    
/*  56 */     this.mare = paramT4CMAREngine;
  }
  
  T4CClobAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, int paramInt7, int paramInt8, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  66 */     super(paramOracleStatement, 4000, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort);
    
/*  70 */     this.mare = paramT4CMAREngine;
/*  71 */     this.definedColumnType = paramInt7;
/*  72 */     this.definedColumnSize = paramInt8;
  }
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/*  80 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/*  87 */       this.mare.unmarshalUB2();
/*  88 */       this.mare.unmarshalUB2();
    }
/*  90 */     else if (this.statement.connection.versionNumber < 9200)
    {
/*  94 */       this.mare.unmarshalSB2();
      
/*  96 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/*  97 */         this.mare.unmarshalSB2();
      }
/*  99 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/* 101 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
/* 110 */   final int[] meta = new int[1];
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/* 125 */     if (this.isUseLess)
    {
/* 127 */       this.lastRowProcessed += 1;
      
/* 129 */       return false;
    }
    
/* 134 */     if (this.rowSpaceIndicator == null)
    {
/* 136 */       i = (int)this.mare.unmarshalUB4();
      
/* 138 */       if (i == 0)
      {
/* 140 */         this.meta[0] = -1;
        
/* 142 */         processIndicator(0);
        
/* 144 */         this.lastRowProcessed += 1;
        
/* 146 */         return false;
      }
      
/* 150 */       byte[] arrayOfByte = new byte['㺀'];
      
/* 152 */       this.mare.unmarshalCLR(arrayOfByte, 0, this.meta);
/* 153 */       processIndicator(this.meta[0]);
      
/* 155 */       this.lastRowProcessed += 1;
      
/* 157 */       return false;
    }
    
/* 160 */     int i = this.columnIndex + this.lastRowProcessed * this.byteLength;
/* 161 */     int j = this.indicatorIndex + this.lastRowProcessed;
/* 162 */     int k = this.lengthIndex + this.lastRowProcessed;
    
/* 166 */     if (this.isNullByDescribe)
    {
/* 168 */       this.rowSpaceIndicator[j] = -1;
/* 169 */       this.rowSpaceIndicator[k] = 0;
/* 170 */       this.lastRowProcessed += 1;
      
/* 172 */       if (this.statement.connection.versionNumber < 9200) {
/* 173 */         processIndicator(0);
      }
/* 175 */       return false;
    }
    
/* 178 */     int m = (int)this.mare.unmarshalUB4();
    
/* 180 */     if (m == 0)
    {
/* 182 */       this.meta[0] = -1;
      
/* 184 */       processIndicator(0);
      
/* 187 */       this.rowSpaceIndicator[j] = -1;
/* 188 */       this.rowSpaceIndicator[k] = 0;
      
/* 190 */       this.lastRowProcessed += 1;
      
/* 192 */       return false;
    }
/* 194 */     if (this.lobPrefetchSizeForThisColumn != -1)
/* 195 */       handlePrefetch();
/* 196 */     this.mare.unmarshalCLR(this.rowSpaceByte, i, this.meta, this.byteLength);
    
/* 198 */     processIndicator(this.meta[0]);
    
/* 200 */     if (this.meta[0] == 0)
    {
/* 204 */       this.rowSpaceIndicator[j] = -1;
/* 205 */       this.rowSpaceIndicator[k] = 0;
    }
    else
    {
/* 209 */       this.rowSpaceIndicator[k] = ((short)this.meta[0]);
/* 210 */       this.rowSpaceIndicator[j] = 0;
    }
    
/* 213 */     this.lastRowProcessed += 1;
    
/* 215 */     return false;
  }
  
  void copyRow()
    throws SQLException, IOException
  {
    int i;
    
/* 225 */     if (this.lastRowProcessed == 0) {
/* 226 */       i = this.statement.rowPrefetchInLastFetch - 1;
    } else {
/* 228 */       i = this.lastRowProcessed - 1;
    }
    
/* 231 */     int j = this.columnIndex + this.lastRowProcessed * this.byteLength;
/* 232 */     int k = this.columnIndex + i * this.byteLength;
/* 233 */     int m = this.indicatorIndex + this.lastRowProcessed;
/* 234 */     int n = this.indicatorIndex + i;
/* 235 */     int i1 = this.lengthIndex + this.lastRowProcessed;
/* 236 */     int i2 = this.lengthIndex + i;
/* 237 */     int i3 = this.rowSpaceIndicator[i2];
/* 238 */     int i4 = this.metaDataIndex + this.lastRowProcessed * 1;
    
/* 240 */     int i5 = this.metaDataIndex + i * 1;
    
/* 244 */     this.rowSpaceIndicator[i1] = ((short)i3);
/* 245 */     this.rowSpaceIndicator[m] = this.rowSpaceIndicator[n];
    
/* 248 */     if (!this.isNullByDescribe)
    {
/* 250 */       System.arraycopy(this.rowSpaceByte, k, this.rowSpaceByte, j, i3);
    }
    
/* 255 */     System.arraycopy(this.rowSpaceMetaData, i5, this.rowSpaceMetaData, i4, 1);
    
/* 258 */     this.lastRowProcessed += 1;
  }
  
  void saveDataFromOldDefineBuffers(byte[] paramArrayOfByte, char[] paramArrayOfChar, short[] paramArrayOfShort, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 271 */     int i = this.columnIndex + (paramInt2 - 1) * this.byteLength;
    
/* 273 */     int j = this.columnIndexLastRow + (paramInt1 - 1) * this.byteLength;
    
/* 275 */     int k = this.indicatorIndex + paramInt2 - 1;
/* 276 */     int m = this.indicatorIndexLastRow + paramInt1 - 1;
/* 277 */     int n = this.lengthIndex + paramInt2 - 1;
/* 278 */     int i1 = this.lengthIndexLastRow + paramInt1 - 1;
/* 279 */     int i2 = paramArrayOfShort[i1];
    
/* 281 */     this.rowSpaceIndicator[n] = ((short)i2);
/* 282 */     this.rowSpaceIndicator[k] = paramArrayOfShort[m];
    
/* 285 */     if (i2 != 0)
    {
/* 287 */       System.arraycopy(paramArrayOfByte, j, this.rowSpaceByte, i, i2);
    }
  }
  
  byte[][] checkAndAllocateLobPrefetchMemory(byte[][] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
/* 303 */     Object localObject = paramArrayOfByte;
/* 304 */     if (localObject == null)
    {
/* 306 */       localObject = new byte[Math.max(paramInt1, paramInt2 + 1)][];
/* 307 */       localObject[paramInt2] = new byte[paramInt3];
    }
    else
    {
/* 311 */       if (localObject.length < paramInt2 + 1)
      {
/* 314 */         byte[][] arrayOfByte = new byte[(paramInt2 + 1) * 2][];
/* 315 */         System.arraycopy(localObject, 0, arrayOfByte, 0, localObject.length);
/* 316 */         localObject = arrayOfByte;
      }
      
/* 319 */       if ((localObject[paramInt2] == null) || (localObject[paramInt2].length < paramInt3))
      {
/* 323 */         localObject[paramInt2] = new byte[paramInt3]; }
    }
/* 325 */     return (byte[][])localObject;
  }
  
  char[][] checkAndAllocateLobPrefetchMemory(char[][] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
  {
/* 333 */     Object localObject = paramArrayOfChar;
/* 334 */     if (localObject == null)
    {
/* 336 */       localObject = new char[Math.max(paramInt1, paramInt2 + 1)][];
/* 337 */       localObject[paramInt2] = new char[paramInt3];
    }
    else
    {
/* 341 */       if (localObject.length < paramInt2 + 1)
      {
/* 344 */         char[][] arrayOfChar = new char[(paramInt2 + 1) * 2][];
/* 345 */         System.arraycopy(localObject, 0, arrayOfChar, 0, localObject.length);
/* 346 */         localObject = arrayOfChar;
      }
      
/* 349 */       if ((localObject[paramInt2] == null) || (localObject[paramInt2].length < paramInt3))
      {
/* 353 */         localObject[paramInt2] = new char[paramInt3]; }
    }
/* 355 */     return (char[][])localObject;
  }
  
  long[] checkAndAllocateLobPrefetchMemory(long[] paramArrayOfLong, int paramInt1, int paramInt2)
  {
/* 360 */     Object localObject = paramArrayOfLong;
/* 361 */     if (localObject == null) {
/* 362 */       localObject = new long[Math.max(paramInt1, paramInt2 + 1)];
/* 363 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 365 */       long[] arrayOfLong = new long[(paramInt2 + 1) * 2];
/* 366 */       System.arraycopy(localObject, 0, arrayOfLong, 0, localObject.length);
/* 367 */       localObject = arrayOfLong;
    }
/* 369 */     return (long[])localObject;
  }
  
  int[] checkAndAllocateLobPrefetchMemory(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
/* 374 */     Object localObject = paramArrayOfInt;
/* 375 */     if (localObject == null) {
/* 376 */       localObject = new int[Math.max(paramInt1, paramInt2 + 1)];
/* 377 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 379 */       int[] arrayOfInt = new int[(paramInt2 + 1) * 2];
/* 380 */       System.arraycopy(localObject, 0, arrayOfInt, 0, localObject.length);
/* 381 */       localObject = arrayOfInt;
    }
/* 383 */     return (int[])localObject;
  }
  
  short[] checkAndAllocateLobPrefetchMemory(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
/* 388 */     Object localObject = paramArrayOfShort;
/* 389 */     if (localObject == null) {
/* 390 */       localObject = new short[Math.max(paramInt1, paramInt2 + 1)];
/* 391 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 393 */       short[] arrayOfShort = new short[(paramInt2 + 1) * 2];
/* 394 */       System.arraycopy(localObject, 0, arrayOfShort, 0, localObject.length);
/* 395 */       localObject = arrayOfShort;
    }
/* 397 */     return (short[])localObject;
  }
  
  byte[] checkAndAllocateLobPrefetchMemory(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
/* 402 */     Object localObject = paramArrayOfByte;
/* 403 */     if (localObject == null) {
/* 404 */       localObject = new byte[Math.max(paramInt1, paramInt2 + 1)];
/* 405 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 407 */       byte[] arrayOfByte = new byte[(paramInt2 + 1) * 2];
/* 408 */       System.arraycopy(localObject, 0, arrayOfByte, 0, localObject.length);
/* 409 */       localObject = arrayOfByte;
    }
/* 411 */     return (byte[])localObject;
  }
  
  boolean[] checkAndAllocateLobPrefetchMemory(boolean[] paramArrayOfBoolean, int paramInt1, int paramInt2)
  {
/* 416 */     Object localObject = paramArrayOfBoolean;
/* 417 */     if (localObject == null) {
/* 418 */       localObject = new boolean[Math.max(paramInt1, paramInt2 + 1)];
/* 419 */     } else if (localObject.length < paramInt2 + 1)
    {
/* 421 */       boolean[] arrayOfBoolean = new boolean[(paramInt2 + 1) * 2];
/* 422 */       System.arraycopy(localObject, 0, arrayOfBoolean, 0, localObject.length);
/* 423 */       localObject = arrayOfBoolean;
    }
/* 425 */     return (boolean[])localObject;
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 436 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] == -1) {
/* 437 */       return null;
    }
    
/* 440 */     if ((this.lobPrefetchSizeForThisColumn != -1) && (this.prefetchedLobSize != null))
    {
/* 443 */       if (this.prefetchedLobSize[paramInt] > 2147483647L)
      {
/* 449 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 151);
/* 450 */         ((SQLException)localObject).fillInStackTrace();
/* 451 */         throw ((Throwable)localObject);
      }
      
/* 456 */       if ((this.prefetchedLobCharData != null) && (this.lobPrefetchSizeForThisColumn >= this.prefetchedLobSize[paramInt]))
      {
/* 462 */         localObject = new String(this.prefetchedLobCharData[paramInt], 0, this.prefetchedLobDataL[paramInt]);
        
/* 465 */         return (String)localObject;
      }
      
/* 471 */       Object localObject = getCLOB(paramInt);
/* 472 */       if (localObject == null)
/* 473 */         return null;
/* 474 */       return ((CLOB)localObject).getSubString(1L, (int)this.prefetchedLobSize[paramInt]);
    }
    
/* 479 */     return super.getString(paramInt);
  }
  
  void handlePrefetch()
    throws SQLException, IOException
  {
/* 487 */     this.prefetchedLobSize = checkAndAllocateLobPrefetchMemory(this.prefetchedLobSize, this.statement.rowPrefetchInLastFetch, this.lastRowProcessed);
    
/* 489 */     this.prefetchedLobSize[this.lastRowProcessed] = this.mare.unmarshalSB8();
    
/* 491 */     this.prefetchedLobChunkSize = checkAndAllocateLobPrefetchMemory(this.prefetchedLobChunkSize, this.statement.rowPrefetchInLastFetch, this.lastRowProcessed);
    
/* 493 */     this.prefetchedLobChunkSize[this.lastRowProcessed] = ((int)this.mare.unmarshalUB4());
    
/* 497 */     if (this.lobPrefetchSizeForThisColumn > 0)
    {
/* 501 */       this.prefetchedClobDBVary = checkAndAllocateLobPrefetchMemory(this.prefetchedClobDBVary, this.statement.rowPrefetchInLastFetch, this.lastRowProcessed);
      
/* 503 */       int i = (byte)this.mare.unmarshalUB1();
/* 504 */       if (i == 1) {
/* 505 */         this.prefetchedClobDBVary[this.lastRowProcessed] = true;
      } else {
/* 507 */         this.prefetchedClobDBVary[this.lastRowProcessed] = false;
      }
      
/* 510 */       this.prefetchedClobCharset = checkAndAllocateLobPrefetchMemory(this.prefetchedClobCharset, this.statement.rowPrefetchInLastFetch, this.lastRowProcessed);
      
/* 512 */       if (this.prefetchedClobDBVary[this.lastRowProcessed] != 0) {
/* 513 */         this.prefetchedClobCharset[this.lastRowProcessed] = ((short)this.mare.unmarshalUB2());
      } else {
/* 515 */         this.prefetchedClobCharset[this.lastRowProcessed] = 0;
      }
      
/* 518 */       this.prefetchedClobFormOfUse = checkAndAllocateLobPrefetchMemory(this.prefetchedClobFormOfUse, this.statement.rowPrefetchInLastFetch, this.lastRowProcessed);
      
/* 520 */       this.prefetchedClobFormOfUse[this.lastRowProcessed] = ((byte)this.mare.unmarshalUB1());
      
/* 522 */       this.prefetchedLobDataL = checkAndAllocateLobPrefetchMemory(this.prefetchedLobDataL, this.statement.rowPrefetchInLastFetch, this.lastRowProcessed);
      
/* 528 */       int j = this.lobPrefetchSizeForThisColumn;
      
/* 530 */       int k = -1;
/* 531 */       if (this.prefetchedClobDBVary[this.lastRowProcessed] != 0) {
/* 532 */         k = j * 2;
      }
      else {
/* 535 */         k = j * 3;
      }
/* 537 */       this.prefetchedLobData = checkAndAllocateLobPrefetchMemory(this.prefetchedLobData, this.statement.rowPrefetchInLastFetch, this.lastRowProcessed, k);
      
/* 541 */       this.mare.unmarshalCLR(this.prefetchedLobData[this.lastRowProcessed], 0, this.meta);
/* 542 */       int m = this.meta[0];
      
/* 548 */       disablePrefetchBufferForPreviousCLOBs(this.lastRowProcessed);
      
/* 550 */       this.prefetchedLobCharData = checkAndAllocateLobPrefetchMemory(this.prefetchedLobCharData, this.statement.rowPrefetchInLastFetch, this.lastRowProcessed, j);
      
/* 553 */       char[] arrayOfChar = this.prefetchedLobCharData[this.lastRowProcessed];
/* 554 */       byte[] arrayOfByte = this.prefetchedLobData[this.lastRowProcessed];
      
/* 558 */       int n = -1;
/* 559 */       if (i == 1) {
/* 560 */         if (this.prefetchedClobCharset[this.lastRowProcessed] == 2000) {
/* 561 */           n = CharacterSet.convertAL16UTF16BytesToJavaChars(arrayOfByte, 0, arrayOfChar, 0, m, true);
        }
        else {
/* 564 */           n = CharacterSet.convertAL16UTF16LEBytesToJavaChars(arrayOfByte, 0, arrayOfChar, 0, m, true);
        }
      }
      else {
/* 568 */         int[] arrayOfInt = { m };
/* 569 */         if (this.formOfUse == 1) {
/* 570 */           n = this.statement.connection.conversion.CHARBytesToJavaChars(arrayOfByte, 0, arrayOfChar, 0, arrayOfInt, arrayOfChar.length);
        }
        else {
/* 573 */           n = this.statement.connection.conversion.NCHARBytesToJavaChars(arrayOfByte, 0, arrayOfChar, 0, arrayOfInt, arrayOfChar.length);
        }
      }
/* 576 */       this.prefetchedLobDataL[this.lastRowProcessed] = n;
    }
  }
  
/* 581 */   ArrayList<LinkedList<CLOB>> registeredCLOBs = new ArrayList(10);
  
  private void saveCLOBReference(int paramInt, CLOB paramCLOB)
  {
/* 590 */     LinkedList localLinkedList = null;
/* 591 */     if (this.registeredCLOBs.size() > paramInt)
    {
/* 593 */       localLinkedList = (LinkedList)this.registeredCLOBs.get(paramInt);
    }
    else
    {
/* 597 */       localLinkedList = new LinkedList();
/* 598 */       while (this.registeredCLOBs.size() < paramInt)
/* 599 */         this.registeredCLOBs.add(new LinkedList());
/* 600 */       this.registeredCLOBs.add(paramInt, localLinkedList);
    }
    
/* 603 */     if (localLinkedList == null) localLinkedList = new LinkedList();
/* 604 */     localLinkedList.add(paramCLOB);
  }
  
  private void disablePrefetchBufferForPreviousCLOBs(int paramInt)
  {
/* 616 */     if (this.registeredCLOBs.size() > paramInt)
    {
/* 618 */       LinkedList localLinkedList = (LinkedList)this.registeredCLOBs.get(paramInt);
/* 619 */       if ((localLinkedList != null) && (!localLinkedList.isEmpty()))
      {
/* 621 */         ListIterator localListIterator = localLinkedList.listIterator();
/* 622 */         while (localListIterator.hasNext()) ((CLOB)localListIterator.next()).setPrefetchedData(null);
/* 623 */         localLinkedList.clear();
      }
    }
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 631 */     if (this.definedColumnType == 0) {
/* 632 */       return super.getObject(paramInt);
    }
    
/* 635 */     Object localObject = null;
    SQLException localSQLException;
/* 637 */     if (this.rowSpaceIndicator == null)
    {
/* 639 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 640 */       localSQLException.fillInStackTrace();
/* 641 */       throw localSQLException;
    }
    
/* 644 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 646 */       switch (this.definedColumnType)
      {
      case 2011: 
/* 653 */         return getNCLOB(paramInt);
      
      case 2005: 
/* 656 */         return getCLOB(paramInt);
      
      case -1: 
      case 1: 
      case 12: 
/* 663 */         return getString(paramInt);
      
      case -4: 
      case -3: 
      case -2: 
/* 670 */         return getBytes(paramInt);
      }
      
      
/* 674 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 675 */       localSQLException.fillInStackTrace();
/* 676 */       throw localSQLException;
    }
    
/* 682 */     return localObject;
  }
  
  void initializeClobForPrefetch(int paramInt, CLOB paramCLOB)
    throws SQLException
  {
/* 695 */     if (paramInt >= 0)
    {
/* 697 */       saveCLOBReference(paramInt, paramCLOB);
/* 698 */       paramCLOB.setPrefetchedData(this.prefetchedLobCharData[paramInt], this.prefetchedLobDataL[paramInt]);
    }
  }
  
/* 704 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CClobAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */