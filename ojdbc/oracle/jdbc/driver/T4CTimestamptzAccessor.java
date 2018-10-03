package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.Properties;
import java.util.TimeZone;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class T4CTimestamptzAccessor
  extends TimestamptzAccessor
{
  T4CMAREngine mare;
/*  40 */   boolean underlyingLongRaw = false;
  
  T4CTimestamptzAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  46 */     super(paramOracleStatement, paramInt1, paramShort, paramInt2, paramBoolean);
    
/*  48 */     this.mare = paramT4CMAREngine;
  }
  
  T4CTimestamptzAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, int paramInt7, int paramInt8, T4CMAREngine paramT4CMAREngine)
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
  
  String getString(int paramInt)
    throws SQLException
  {
/* 278 */     String str = super.getString(paramInt);
    
/* 280 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
    {
/* 282 */       str = str.substring(0, this.definedColumnSize);
    }
/* 284 */     return str;
  }
  
  String toText(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, String paramString)
    throws SQLException
  {
/* 308 */     if ((this.definedColumnType == 0) || (this.definedColumnType == -101))
    {
/* 311 */       return super.toText(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramBoolean, paramString);
    }
    
/* 319 */     String str = (String)this.statement.connection.sessionProperties.get("AUTH_NLS_LXCSTZNFM");
/* 320 */     return nlsFormatToText(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramBoolean, paramString, str);
  }
  
  private static final String nlsFormatToText(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, boolean paramBoolean, String paramString1, String paramString2)
    throws SQLException
  {
/* 346 */     char[] arrayOfChar = (paramString2 + "      ").toCharArray();
/* 347 */     int i = paramString2.length();
    
/* 349 */     StringBuffer localStringBuffer = new StringBuffer(i + 25);
/* 350 */     String[] arrayOfString1 = null;
/* 351 */     String[] arrayOfString2 = null;
/* 352 */     TimeZone localTimeZone = null;
    
/* 355 */     for (int j = 0; j < i; j++)
    {
/* 357 */       switch (arrayOfChar[j])
      {
      case 'R': 
      case 'r': 
/* 361 */         if ((arrayOfChar[(j + 1)] == 'R') || (arrayOfChar[(j + 1)] == 'r'))
        {
/* 363 */           if (((arrayOfChar[(j + 2)] == 'R') || (arrayOfChar[(j + 2)] == 'r')) && ((arrayOfChar[(j + 3)] == 'R') || (arrayOfChar[(j + 3)] == 'r')))
          {
/* 366 */             if (paramInt1 < 1000) {
/* 367 */               localStringBuffer.append("0" + paramInt1);
/* 368 */             } else if (paramInt1 < 100) {
/* 369 */               localStringBuffer.append("00" + paramInt1);
/* 370 */             } else if (paramInt1 < 10) {
/* 371 */               localStringBuffer.append("000" + paramInt1);
            } else {
/* 373 */               localStringBuffer.append(paramInt1);
            }
/* 375 */             j += 3;
          }
          else
          {
/* 379 */             if (paramInt1 >= 100) {
/* 380 */               paramInt1 %= 100;
            }
/* 382 */             if (paramInt1 < 10) {
/* 383 */               localStringBuffer.append("0" + paramInt1);
            } else {
/* 385 */               localStringBuffer.append(paramInt1);
            }
/* 387 */             j++;
          }
        }
        
        break;
      case 'Y': 
      case 'y': 
/* 394 */         if ((arrayOfChar[(j + 1)] == 'Y') || (arrayOfChar[(j + 1)] == 'y'))
        {
/* 396 */           if (((arrayOfChar[(j + 2)] == 'Y') || (arrayOfChar[(j + 2)] == 'y')) && ((arrayOfChar[(j + 3)] == 'Y') || (arrayOfChar[(j + 3)] == 'y')))
          {
/* 399 */             if (paramInt1 < 1000) {
/* 400 */               localStringBuffer.append("0" + paramInt1);
/* 401 */             } else if (paramInt1 < 100) {
/* 402 */               localStringBuffer.append("00" + paramInt1);
/* 403 */             } else if (paramInt1 < 10) {
/* 404 */               localStringBuffer.append("000" + paramInt1);
            } else {
/* 406 */               localStringBuffer.append(paramInt1);
            }
/* 408 */             j += 3;
          }
          else
          {
/* 412 */             if (paramInt1 >= 100) {
/* 413 */               paramInt1 %= 100;
            }
/* 415 */             if (paramInt1 < 10) {
/* 416 */               localStringBuffer.append("0" + paramInt1);
            } else {
/* 418 */               localStringBuffer.append(paramInt1);
            }
/* 420 */             j++;
          }
        }
        
        break;
      case 'D': 
      case 'd': 
/* 427 */         if ((arrayOfChar[(j + 1)] == 'D') || (arrayOfChar[(j + 1)] == 'd'))
        {
/* 429 */           localStringBuffer.append((paramInt3 < 10 ? "0" : "") + paramInt3);
/* 430 */           j++;
        }
        
        break;
      case 'M': 
      case 'm': 
/* 436 */         if ((arrayOfChar[(j + 1)] == 'M') || (arrayOfChar[(j + 1)] == 'm'))
        {
/* 438 */           localStringBuffer.append((paramInt2 < 10 ? "0" : "") + paramInt2);
/* 439 */           j++;
        }
/* 441 */         else if ((arrayOfChar[(j + 1)] == 'I') || (arrayOfChar[(j + 1)] == 'i'))
        {
/* 443 */           localStringBuffer.append((paramInt5 < 10 ? "0" : "") + paramInt5);
/* 444 */           j++;
        }
/* 446 */         else if (((arrayOfChar[(j + 1)] == 'O') || (arrayOfChar[(j + 1)] == 'o')) && ((arrayOfChar[(j + 2)] == 'N') || (arrayOfChar[(j + 2)] == 'n')))
        {
/* 449 */           if (((arrayOfChar[(j + 3)] == 'T') || (arrayOfChar[(j + 3)] == 't')) && ((arrayOfChar[(j + 4)] == 'H') || (arrayOfChar[(j + 4)] == 'h')))
          {
/* 453 */             if (arrayOfString2 == null) {
/* 454 */               arrayOfString2 = new DateFormatSymbols().getMonths();
            }
            
/* 461 */             if (arrayOfChar[j] == 'm')
            {
/* 464 */               localStringBuffer.append(arrayOfString2[(paramInt2 - 1)].toLowerCase());
            }
/* 466 */             else if (arrayOfChar[(j + 1)] == 'O')
            {
/* 469 */               localStringBuffer.append(arrayOfString2[(paramInt2 - 1)].toUpperCase());
            }
            else
            {
/* 474 */               localStringBuffer.append(arrayOfString2[(paramInt2 - 1)]);
            }
/* 476 */             j += 4;
          }
          else
          {
/* 481 */             if (arrayOfString1 == null) {
/* 482 */               arrayOfString1 = new DateFormatSymbols().getShortMonths();
            }
            
/* 489 */             if (arrayOfChar[j] == 'm')
            {
/* 492 */               localStringBuffer.append(arrayOfString1[(paramInt2 - 1)].toLowerCase());
            }
/* 494 */             else if (arrayOfChar[(j + 1)] == 'O')
            {
/* 497 */               localStringBuffer.append(arrayOfString1[(paramInt2 - 1)].toUpperCase());
            }
            else
            {
/* 502 */               localStringBuffer.append(arrayOfString1[(paramInt2 - 1)]);
            }
/* 504 */             j += 2;
          }
        }
        
        break;
      case 'H': 
      case 'h': 
/* 511 */         if ((arrayOfChar[(j + 1)] == 'H') || (arrayOfChar[(j + 1)] == 'h'))
        {
/* 513 */           if ((arrayOfChar[(j + 2)] == '2') || (arrayOfChar[(j + 3)] == '4'))
          {
/* 515 */             localStringBuffer.append((paramInt4 < 10 ? "0" : "") + paramInt4);
/* 516 */             j += 3;
          }
          else {
/* 519 */             if (paramInt4 > 12)
/* 520 */               paramInt4 -= 12;
/* 521 */             localStringBuffer.append((paramInt4 < 10 ? "0" : "") + paramInt4);
/* 522 */             j++;
          }
        }
        
        break;
      case 'S': 
      case 's': 
/* 529 */         if ((arrayOfChar[(j + 1)] == 'S') || (arrayOfChar[(j + 1)] == 's'))
        {
/* 531 */           localStringBuffer.append((paramInt6 < 10 ? "0" : "") + paramInt6);
/* 532 */           j++;
/* 533 */           if (((arrayOfChar[(j + 1)] == 'X') || (arrayOfChar[(j + 1)] == 'x')) && ((arrayOfChar[(j + 2)] == 'F') || (arrayOfChar[(j + 2)] == 'f')) && ((arrayOfChar[(j + 3)] == 'F') || (arrayOfChar[(j + 3)] == 'f')))
          {
/* 537 */             localStringBuffer.append(".");
/* 538 */             j++;
          }
        }
        
        break;
      case 'F': 
      case 'f': 
/* 546 */         if ((arrayOfChar[(j + 1)] == 'F') || (arrayOfChar[(j + 1)] == 'f'))
        {
/* 548 */           if (paramInt7 >= 0)
          {
/* 550 */             localStringBuffer.append(paramInt7);
          }
          else
          {
/* 554 */             localStringBuffer.append(0);
          }
/* 556 */           j++;
        }
        
        break;
      case 'T': 
      case 't': 
/* 562 */         if ((arrayOfChar[(j + 1)] == 'Z') || (arrayOfChar[(j + 1)] == 'z'))
        {
/* 564 */           if ((arrayOfChar[(j + 2)] == 'R') || (arrayOfChar[(j + 2)] == 'r'))
          {
/* 566 */             if ((paramString1.length() > 3) && (paramString1.startsWith("GMT")))
            {
/* 568 */               localStringBuffer.append(paramString1.substring(3));
            }
            else
            {
/* 573 */               localStringBuffer.append(paramString1.toUpperCase());
            }
/* 575 */             j += 2; } else { long l;
/* 576 */             if ((arrayOfChar[(j + 2)] == 'H') || (arrayOfChar[(j + 2)] == 'h'))
            {
/* 578 */               if (localTimeZone == null)
/* 579 */                 localTimeZone = TimeZone.getTimeZone(paramString1);
/* 580 */               l = localTimeZone.getRawOffset() / 3600000;
/* 581 */               localStringBuffer.append(l);
/* 582 */               j += 2;
/* 583 */             } else if ((arrayOfChar[(j + 2)] == 'M') || (arrayOfChar[(j + 2)] == 'm'))
            {
/* 585 */               if (localTimeZone == null)
/* 586 */                 localTimeZone = TimeZone.getTimeZone(paramString1);
/* 587 */               l = Math.abs(localTimeZone.getRawOffset()) % 3600000 / 60000;
/* 588 */               localStringBuffer.append((l < 10L ? "0" : "") + l);
/* 589 */               j += 2; } } }
/* 590 */         break;
      
      case 'A': 
      case 'P': 
      case 'a': 
      case 'p': 
/* 598 */         if ((arrayOfChar[(j + 1)] == 'M') || (arrayOfChar[(j + 1)] == 'm'))
        {
/* 600 */           localStringBuffer.append(paramBoolean ? "AM" : "PM");
/* 601 */           j++;
        }
        break;
      case 'B': case 'C': case 'E': case 'G': case 'I': case 'J': case 'K': case 'L': case 'N': case 'O': case 'Q': case 'U': case 'V': case 'W': case 'X': case 'Z': case '[': case '\\': case ']': 
      case '^': case '_': case '`': case 'b': case 'c': case 'e': case 'g': case 'i': case 'j': case 'k': case 'l': case 'n': case 'o': case 'q': case 'u': case 'v': case 'w': case 'x': default: 
/* 606 */         localStringBuffer.append(arrayOfChar[j]);
      }
      
    }
    
/* 612 */     return localStringBuffer.substring(0, localStringBuffer.length());
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 622 */     if (this.definedColumnType == 0) {
/* 623 */       return super.getObject(paramInt);
    }
    
/* 626 */     Object localObject = null;
    SQLException localSQLException;
/* 628 */     if (this.rowSpaceIndicator == null)
    {
/* 630 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 631 */       localSQLException.fillInStackTrace();
/* 632 */       throw localSQLException;
    }
    
/* 635 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 637 */       switch (this.definedColumnType)
      {
      case -15: 
      case -9: 
      case -1: 
      case 1: 
      case 12: 
/* 651 */         return getString(paramInt);
      
      case 93: 
/* 654 */         return getTimestamp(paramInt);
      
      case -101: 
/* 657 */         return getTIMESTAMPTZ(paramInt);
      
      case 91: 
/* 660 */         return getDate(paramInt);
      
      case 92: 
/* 663 */         return getTime(paramInt);
      
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
  
/* 688 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTimestamptzAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */