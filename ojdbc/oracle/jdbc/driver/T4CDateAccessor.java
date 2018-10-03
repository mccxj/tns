package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.Properties;
import java.util.TimeZone;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class T4CDateAccessor
  extends DateAccessor
{
  T4CMAREngine mare;
/*  40 */   boolean underlyingLongRaw = false;
  
  T4CDateAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  46 */     super(paramOracleStatement, paramInt1, paramShort, paramInt2, paramBoolean);
    
/*  48 */     this.mare = paramT4CMAREngine;
  }
  
  T4CDateAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, int paramInt7, int paramInt8, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  57 */     super(paramOracleStatement, paramInt1 == -1 ? paramInt8 : paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort);
    
/*  60 */     this.mare = paramT4CMAREngine;
    
/*  62 */     if ((paramOracleStatement != null) && (paramOracleStatement.implicitDefineForLobPrefetchDone))
    {
/*  64 */       this.definedColumnType = 0;
/*  65 */       this.definedColumnSize = 0;
    }
    else
    {
/*  69 */       this.definedColumnType = paramInt7;
/*  70 */       this.definedColumnSize = paramInt8;
    }
    
/*  73 */     if (paramInt1 == -1) {
/*  74 */       this.underlyingLongRaw = true;
    }
  }
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/*  81 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/*  88 */       this.mare.unmarshalUB2();
/*  89 */       this.mare.unmarshalUB2();
    }
/*  91 */     else if (this.statement.connection.versionNumber < 9200)
    {
/*  95 */       this.mare.unmarshalSB2();
      
/*  97 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/*  98 */         this.mare.unmarshalSB2();
      }
/* 100 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/* 102 */       this.mare.processIndicator(paramInt <= 0, paramInt);
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
/* 138 */       byte[] arrayOfByte = new byte['㺀'];
      
/* 140 */       this.mare.unmarshalCLR(arrayOfByte, 0, this.meta);
/* 141 */       processIndicator(this.meta[0]);
      
/* 143 */       this.lastRowProcessed += 1;
      
/* 145 */       return false;
    }
    
/* 149 */     int i = this.indicatorIndex + this.lastRowProcessed;
/* 150 */     int j = this.lengthIndex + this.lastRowProcessed;
    
/* 154 */     if (this.isNullByDescribe)
    {
/* 156 */       this.rowSpaceIndicator[i] = -1;
/* 157 */       this.rowSpaceIndicator[j] = 0;
/* 158 */       this.lastRowProcessed += 1;
      
/* 160 */       if (this.statement.connection.versionNumber < 9200) {
/* 161 */         processIndicator(0);
      }
/* 163 */       return false;
    }
    
/* 166 */     int k = this.columnIndex + this.lastRowProcessed * this.byteLength;
    
/* 170 */     this.mare.unmarshalCLR(this.rowSpaceByte, k, this.meta, this.byteLength);
    
/* 172 */     processIndicator(this.meta[0]);
    
/* 174 */     if (this.meta[0] == 0)
    {
/* 178 */       this.rowSpaceIndicator[i] = -1;
/* 179 */       this.rowSpaceIndicator[j] = 0;
    }
    else
    {
/* 183 */       this.rowSpaceIndicator[j] = ((short)this.meta[0]);
/* 184 */       this.rowSpaceIndicator[i] = 0;
    }
    
/* 187 */     this.lastRowProcessed += 1;
    
/* 189 */     return false;
  }
  
  void copyRow()
    throws SQLException, IOException
  {
    int i;
    
/* 199 */     if (this.lastRowProcessed == 0) {
/* 200 */       i = this.statement.rowPrefetchInLastFetch - 1;
    } else {
/* 202 */       i = this.lastRowProcessed - 1;
    }
    
/* 205 */     int j = this.columnIndex + this.lastRowProcessed * this.byteLength;
/* 206 */     int k = this.columnIndex + i * this.byteLength;
/* 207 */     int m = this.indicatorIndex + this.lastRowProcessed;
/* 208 */     int n = this.indicatorIndex + i;
/* 209 */     int i1 = this.lengthIndex + this.lastRowProcessed;
/* 210 */     int i2 = this.lengthIndex + i;
/* 211 */     int i3 = this.rowSpaceIndicator[i2];
/* 212 */     int i4 = this.metaDataIndex + this.lastRowProcessed * 1;
    
/* 214 */     int i5 = this.metaDataIndex + i * 1;
    
/* 218 */     this.rowSpaceIndicator[i1] = ((short)i3);
/* 219 */     this.rowSpaceIndicator[m] = this.rowSpaceIndicator[n];
    
/* 222 */     if (!this.isNullByDescribe)
    {
/* 224 */       System.arraycopy(this.rowSpaceByte, k, this.rowSpaceByte, j, i3);
    }
    
/* 229 */     System.arraycopy(this.rowSpaceMetaData, i5, this.rowSpaceMetaData, i4, 1);
    
/* 232 */     this.lastRowProcessed += 1;
  }
  
  void saveDataFromOldDefineBuffers(byte[] paramArrayOfByte, char[] paramArrayOfChar, short[] paramArrayOfShort, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 244 */     int i = this.columnIndex + (paramInt2 - 1) * this.byteLength;
    
/* 246 */     int j = this.columnIndexLastRow + (paramInt1 - 1) * this.byteLength;
    
/* 248 */     int k = this.indicatorIndex + paramInt2 - 1;
/* 249 */     int m = this.indicatorIndexLastRow + paramInt1 - 1;
/* 250 */     int n = this.lengthIndex + paramInt2 - 1;
/* 251 */     int i1 = this.lengthIndexLastRow + paramInt1 - 1;
/* 252 */     int i2 = paramArrayOfShort[i1];
    
/* 254 */     this.rowSpaceIndicator[n] = ((short)i2);
/* 255 */     this.rowSpaceIndicator[k] = paramArrayOfShort[m];
    
/* 258 */     if (i2 != 0)
    {
/* 260 */       System.arraycopy(paramArrayOfByte, j, this.rowSpaceByte, i, i2);
    }
  }
  
  String toText(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, String paramString)
    throws SQLException
  {
/* 288 */     if ((this.definedColumnType == 0) || (this.definedColumnType == 91))
    {
/* 291 */       return super.toText(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramBoolean, paramString);
    }
    
/* 299 */     String str = (String)this.statement.connection.sessionProperties.get("AUTH_NLS_LXCDATEFM");
/* 300 */     return nlsFormatToText(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramBoolean, paramString, str);
  }
  
  private static final String nlsFormatToText(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, String paramString1, String paramString2)
    throws SQLException
  {
/* 326 */     char[] arrayOfChar = (paramString2 + "      ").toCharArray();
/* 327 */     int i = paramString2.length();
    
/* 329 */     StringBuffer localStringBuffer = new StringBuffer(i + 25);
/* 330 */     String[] arrayOfString1 = null;
/* 331 */     String[] arrayOfString2 = null;
/* 332 */     TimeZone localTimeZone = null;
    
/* 335 */     for (int j = 0; j < i; j++)
    {
/* 337 */       switch (arrayOfChar[j])
      {
      case 'R': 
      case 'r': 
/* 341 */         if ((arrayOfChar[(j + 1)] == 'R') || (arrayOfChar[(j + 1)] == 'r'))
        {
/* 343 */           if (((arrayOfChar[(j + 2)] == 'R') || (arrayOfChar[(j + 2)] == 'r')) && ((arrayOfChar[(j + 3)] == 'R') || (arrayOfChar[(j + 3)] == 'r')))
          {
/* 346 */             if (paramInt1 < 1000) {
/* 347 */               localStringBuffer.append("0" + paramInt1);
/* 348 */             } else if (paramInt1 < 100) {
/* 349 */               localStringBuffer.append("00" + paramInt1);
/* 350 */             } else if (paramInt1 < 10) {
/* 351 */               localStringBuffer.append("000" + paramInt1);
            } else {
/* 353 */               localStringBuffer.append(paramInt1);
            }
/* 355 */             j += 3;
          }
          else
          {
/* 359 */             if (paramInt1 >= 100) {
/* 360 */               paramInt1 %= 100;
            }
/* 362 */             if (paramInt1 < 10) {
/* 363 */               localStringBuffer.append("0" + paramInt1);
            } else {
/* 365 */               localStringBuffer.append(paramInt1);
            }
/* 367 */             j++;
          }
        }
        
        break;
      case 'Y': 
      case 'y': 
/* 374 */         if ((arrayOfChar[(j + 1)] == 'Y') || (arrayOfChar[(j + 1)] == 'y'))
        {
/* 376 */           if (((arrayOfChar[(j + 2)] == 'Y') || (arrayOfChar[(j + 2)] == 'y')) && ((arrayOfChar[(j + 3)] == 'Y') || (arrayOfChar[(j + 3)] == 'y')))
          {
/* 379 */             if (paramInt1 < 1000) {
/* 380 */               localStringBuffer.append("0" + paramInt1);
/* 381 */             } else if (paramInt1 < 100) {
/* 382 */               localStringBuffer.append("00" + paramInt1);
/* 383 */             } else if (paramInt1 < 10) {
/* 384 */               localStringBuffer.append("000" + paramInt1);
            } else {
/* 386 */               localStringBuffer.append(paramInt1);
            }
/* 388 */             j += 3;
          }
          else
          {
/* 392 */             if (paramInt1 >= 100) {
/* 393 */               paramInt1 %= 100;
            }
/* 395 */             if (paramInt1 < 10) {
/* 396 */               localStringBuffer.append("0" + paramInt1);
            } else {
/* 398 */               localStringBuffer.append(paramInt1);
            }
/* 400 */             j++;
          }
        }
        
        break;
      case 'D': 
      case 'd': 
/* 407 */         if ((arrayOfChar[(j + 1)] == 'D') || (arrayOfChar[(j + 1)] == 'd'))
        {
/* 409 */           localStringBuffer.append((paramInt3 < 10 ? "0" : "") + paramInt3);
/* 410 */           j++;
        }
        
        break;
      case 'M': 
      case 'm': 
/* 416 */         if ((arrayOfChar[(j + 1)] == 'M') || (arrayOfChar[(j + 1)] == 'm'))
        {
/* 418 */           localStringBuffer.append((paramInt2 < 10 ? "0" : "") + paramInt2);
/* 419 */           j++;
        }
/* 421 */         else if ((arrayOfChar[(j + 1)] == 'I') || (arrayOfChar[(j + 1)] == 'i'))
        {
/* 423 */           localStringBuffer.append((paramInt5 < 10 ? "0" : "") + paramInt5);
/* 424 */           j++;
        }
/* 426 */         else if (((arrayOfChar[(j + 1)] == 'O') || (arrayOfChar[(j + 1)] == 'o')) && ((arrayOfChar[(j + 2)] == 'N') || (arrayOfChar[(j + 2)] == 'n')))
        {
/* 429 */           if (((arrayOfChar[(j + 3)] == 'T') || (arrayOfChar[(j + 3)] == 't')) && ((arrayOfChar[(j + 4)] == 'H') || (arrayOfChar[(j + 4)] == 'h')))
          {
/* 433 */             if (arrayOfString2 == null) {
/* 434 */               arrayOfString2 = new DateFormatSymbols().getMonths();
            }
            
/* 441 */             if (arrayOfChar[j] == 'm')
            {
/* 444 */               localStringBuffer.append(arrayOfString2[(paramInt2 - 1)].toLowerCase());
            }
/* 446 */             else if (arrayOfChar[(j + 1)] == 'O')
            {
/* 449 */               localStringBuffer.append(arrayOfString2[(paramInt2 - 1)].toUpperCase());
            }
            else
            {
/* 454 */               localStringBuffer.append(arrayOfString2[(paramInt2 - 1)]);
            }
/* 456 */             j += 4;
          }
          else
          {
/* 461 */             if (arrayOfString1 == null) {
/* 462 */               arrayOfString1 = new DateFormatSymbols().getShortMonths();
            }
            
/* 469 */             if (arrayOfChar[j] == 'm')
            {
/* 472 */               localStringBuffer.append(arrayOfString1[(paramInt2 - 1)].toLowerCase());
            }
/* 474 */             else if (arrayOfChar[(j + 1)] == 'O')
            {
/* 477 */               localStringBuffer.append(arrayOfString1[(paramInt2 - 1)].toUpperCase());
            }
            else
            {
/* 482 */               localStringBuffer.append(arrayOfString1[(paramInt2 - 1)]);
            }
/* 484 */             j += 2;
          }
        }
        
        break;
      case 'H': 
      case 'h': 
/* 491 */         if ((arrayOfChar[(j + 1)] == 'H') || (arrayOfChar[(j + 1)] == 'h'))
        {
/* 493 */           if ((arrayOfChar[(j + 2)] == '2') || (arrayOfChar[(j + 3)] == '4'))
          {
/* 495 */             localStringBuffer.append((paramInt4 < 10 ? "0" : "") + paramInt4);
/* 496 */             j += 3;
          }
          else {
/* 499 */             if (paramInt4 > 12)
/* 500 */               paramInt4 -= 12;
/* 501 */             localStringBuffer.append((paramInt4 < 10 ? "0" : "") + paramInt4);
/* 502 */             j++;
          }
        }
        
        break;
      case 'S': 
      case 's': 
/* 509 */         if ((arrayOfChar[(j + 1)] == 'S') || (arrayOfChar[(j + 1)] == 's'))
        {
/* 511 */           localStringBuffer.append((paramInt6 < 10 ? "0" : "") + paramInt6);
/* 512 */           j++;
/* 513 */           if (((arrayOfChar[(j + 1)] == 'X') || (arrayOfChar[(j + 1)] == 'x')) && ((arrayOfChar[(j + 2)] == 'F') || (arrayOfChar[(j + 2)] == 'f')) && ((arrayOfChar[(j + 3)] == 'F') || (arrayOfChar[(j + 3)] == 'f')))
          {
/* 517 */             localStringBuffer.append(".");
/* 518 */             j++;
          }
        }
        
        break;
      case 'F': 
      case 'f': 
/* 526 */         if ((arrayOfChar[(j + 1)] == 'F') || (arrayOfChar[(j + 1)] == 'f'))
        {
/* 528 */           if (paramInt7 >= 0)
          {
/* 530 */             localStringBuffer.append(paramInt7);
          }
          else
          {
/* 534 */             localStringBuffer.append(0);
          }
/* 536 */           j++;
        }
        
        break;
      case 'T': 
      case 't': 
/* 542 */         if ((arrayOfChar[(j + 1)] == 'Z') || (arrayOfChar[(j + 1)] == 'z'))
        {
/* 544 */           if ((arrayOfChar[(j + 2)] == 'R') || (arrayOfChar[(j + 2)] == 'r'))
          {
/* 546 */             if ((paramString1.length() > 3) && (paramString1.startsWith("GMT")))
            {
/* 548 */               localStringBuffer.append(paramString1.substring(3));
            }
            else
            {
/* 553 */               localStringBuffer.append(paramString1.toUpperCase());
            }
/* 555 */             j += 2; } else { long l;
/* 556 */             if ((arrayOfChar[(j + 2)] == 'H') || (arrayOfChar[(j + 2)] == 'h'))
            {
/* 558 */               if (localTimeZone == null)
/* 559 */                 localTimeZone = TimeZone.getTimeZone(paramString1);
/* 560 */               l = localTimeZone.getRawOffset() / 3600000;
/* 561 */               localStringBuffer.append(l);
/* 562 */               j += 2;
/* 563 */             } else if ((arrayOfChar[(j + 2)] == 'M') || (arrayOfChar[(j + 2)] == 'm'))
            {
/* 565 */               if (localTimeZone == null)
/* 566 */                 localTimeZone = TimeZone.getTimeZone(paramString1);
/* 567 */               l = Math.abs(localTimeZone.getRawOffset()) % 3600000 / 60000;
/* 568 */               localStringBuffer.append((l < 10L ? "0" : "") + l);
/* 569 */               j += 2; } } }
/* 570 */         break;
      
      case 'A': 
      case 'P': 
      case 'a': 
      case 'p': 
/* 578 */         if ((arrayOfChar[(j + 1)] == 'M') || (arrayOfChar[(j + 1)] == 'm'))
        {
/* 580 */           localStringBuffer.append(paramBoolean ? "AM" : "PM");
/* 581 */           j++;
        }
        break;
      case 'B': case 'C': case 'E': case 'G': case 'I': case 'J': case 'K': case 'L': case 'N': case 'O': case 'Q': case 'U': case 'V': case 'W': case 'X': case 'Z': case '[': case '\\': case ']': 
      case '^': case '_': case '`': case 'b': case 'c': case 'e': case 'g': case 'i': case 'j': case 'k': case 'l': case 'n': case 'o': case 'q': case 'u': case 'v': case 'w': case 'x': default: 
/* 586 */         localStringBuffer.append(arrayOfChar[j]);
      }
      
    }
    
/* 592 */     return localStringBuffer.substring(0, localStringBuffer.length());
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 607 */     String str = null;
    
/* 609 */     if (this.definedColumnType == 0)
    {
/* 611 */       str = super.getString(paramInt);
    }
    else
    {
/* 615 */       if (this.rowSpaceIndicator == null)
      {
/* 619 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 620 */         localSQLException.fillInStackTrace();
/* 621 */         throw localSQLException;
      }
      
/* 626 */       if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
      {
/* 628 */         int i = this.columnIndex + this.byteLength * paramInt;
/* 629 */         int j = oracleYear(i);
/* 630 */         int k = 0;
/* 631 */         str = toText(j, this.rowSpaceByte[(2 + i)], this.rowSpaceByte[(3 + i)], k = this.rowSpaceByte[(4 + i)] - 1, this.rowSpaceByte[(5 + i)] - 1, this.rowSpaceByte[(6 + i)] - 1, -1, k < 12, null);
      }
    }
    
/* 643 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
    {
/* 645 */       str = str.substring(0, this.definedColumnSize);
    }
/* 647 */     return str;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 653 */     if (this.definedColumnType == 0) {
/* 654 */       return super.getObject(paramInt);
    }
    
/* 657 */     Object localObject = null;
    SQLException localSQLException;
/* 659 */     if (this.rowSpaceIndicator == null)
    {
/* 661 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 662 */       localSQLException.fillInStackTrace();
/* 663 */       throw localSQLException;
    }
    
/* 666 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 668 */       switch (this.definedColumnType)
      {
      case -15: 
      case -9: 
      case -1: 
      case 1: 
      case 12: 
/* 682 */         return getString(paramInt);
      
      case 91: 
/* 685 */         return getDate(paramInt);
      
      case 92: 
/* 688 */         return getTime(paramInt);
      
      case 93: 
/* 691 */         return getTimestamp(paramInt);
      
      case -4: 
      case -3: 
      case -2: 
/* 698 */         return getBytes(paramInt);
      }
      
      
/* 702 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 703 */       localSQLException.fillInStackTrace();
/* 704 */       throw localSQLException;
    }
    
/* 710 */     return localObject;
  }
  
/* 715 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CDateAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */