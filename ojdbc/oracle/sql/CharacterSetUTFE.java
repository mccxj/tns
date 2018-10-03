package oracle.sql;
import java.sql.SQLException;
class CharacterSetUTFE
  extends CharacterSet
  implements CharacterRepConstants
{
  static final int MAXBYTEPERCHAR = 4;
/*  50 */   static byte[][] utf8m2utfe = { { 0, 1, 2, 3, 55, 45, 46, 47, 22, 5, 21, 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 60, 61, 50, 38, 24, 25, 63, 39, 28, 29, 30, 31 }, { 64, 90, Byte.MAX_VALUE, 123, 91, 108, 80, 125, 77, 93, 92, 78, 107, 96, 75, 97 }, { -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, 122, 94, 76, 126, 110, 111 }, { 124, -63, -62, -61, -60, -59, -58, -57, -56, -55, -47, -46, -45, -44, -43, -42 }, { -41, -40, -39, -30, -29, -28, -27, -26, -25, -24, -23, -83, -32, -67, 95, 109 }, { 121, -127, -126, -125, -124, -123, -122, -121, -120, -119, -111, -110, -109, -108, -107, -106 }, { -105, -104, -103, -94, -93, -92, -91, -90, -89, -88, -87, -64, 79, -48, -95, 7 }, { 32, 33, 34, 35, 36, 37, 6, 23, 40, 41, 42, 43, 44, 9, 10, 27 }, { 48, 49, 26, 51, 52, 53, 54, 8, 56, 57, 58, 59, 4, 20, 62, -1 }, { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 81, 82, 83, 84, 85, 86 }, { 87, 88, 89, 98, 99, 100, 101, 102, 103, 104, 105, 106, 112, 113, 114, 115 }, { 116, 117, 118, 119, 120, Byte.MIN_VALUE, -118, -117, -116, -115, -114, -113, -112, -102, -101, -100 }, { -99, -98, -97, -96, -86, -85, -84, -82, -81, -80, -79, -78, -77, -76, -75, -74 }, { -73, -72, -71, -70, -69, -68, -66, -65, -54, -53, -52, -51, -50, -49, -38, -37 }, { -36, -35, -34, -33, -31, -22, -21, -20, -19, -18, -17, -6, -5, -4, -3, -2 } };
  
/* 148 */   static byte[][] utfe2utf8m = { { 0, 1, 2, 3, -100, 9, -122, Byte.MAX_VALUE, -105, -115, -114, 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, -99, 10, 8, -121, 24, 25, -110, -113, 28, 29, 30, 31 }, { Byte.MIN_VALUE, -127, -126, -125, -124, -123, 23, 27, -120, -119, -118, -117, -116, 5, 6, 7 }, { -112, -111, 22, -109, -108, -107, -106, 4, -104, -103, -102, -101, 20, 21, -98, 26 }, { 32, -96, -95, -94, -93, -92, -91, -90, -89, -88, -87, 46, 60, 40, 43, 124 }, { 38, -86, -85, -84, -83, -82, -81, -80, -79, -78, 33, 36, 42, 41, 59, 94 }, { 45, 47, -77, -76, -75, -74, -73, -72, -71, -70, -69, 44, 37, 95, 62, 63 }, { -68, -67, -66, -65, -64, -63, -62, -61, -60, 96, 58, 35, 64, 39, 61, 34 }, { -59, 97, 98, 99, 100, 101, 102, 103, 104, 105, -58, -57, -56, -55, -54, -53 }, { -52, 106, 107, 108, 109, 110, 111, 112, 113, 114, -51, -50, -49, -48, -47, -46 }, { -45, 126, 115, 116, 117, 118, 119, 120, 121, 122, -44, -43, -42, 88, -41, -40 }, { -39, -38, -37, -36, -35, -34, -33, -32, -31, -30, -29, -28, -27, 93, -26, -25 }, { 123, 65, 66, 67, 68, 69, 70, 71, 72, 73, -24, -23, -22, -21, -20, -19 }, { 13, 74, 75, 76, 77, 78, 79, 80, 81, 82, -18, -17, -16, -15, -14, -13 }, { 92, -12, 83, 84, 85, 86, 87, 88, 89, 90, -11, -10, -9, -8, -7, -6 }, { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, -5, -4, -3, -2, -1, -97 } };
  
/* 247 */   private static int[] m_byteLen = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2, 2, 3, 4 };
  
  CharacterSetUTFE(int paramInt)
  {
/* 254 */     super(paramInt);
    
/* 257 */     this.rep = 3;
  }
  
  public boolean isLossyFrom(CharacterSet paramCharacterSet)
  {
/* 266 */     return !paramCharacterSet.isUnicode();
  }
  
  public boolean isConvertibleFrom(CharacterSet paramCharacterSet)
  {
/* 275 */     boolean bool = paramCharacterSet.rep <= 1024;
    
/* 277 */     return bool;
  }
  
  public boolean isUnicode()
  {
/* 285 */     return true;
  }
  
  public String toString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 293 */     char[] arrayOfChar = new char[paramArrayOfByte.length];
/* 294 */     int i = UTFEToJavaChar(paramArrayOfByte, paramInt1, paramInt2, arrayOfChar, CharacterSet.CharacterConverterBehavior.REPORT_ERROR);
    
/* 297 */     return new String(arrayOfChar, 0, i);
  }
  
  public String toStringWithReplacement(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
/* 308 */       char[] arrayOfChar = new char[paramArrayOfByte.length];
/* 309 */       int i = UTFEToJavaChar(paramArrayOfByte, paramInt1, paramInt2, arrayOfChar, CharacterSet.CharacterConverterBehavior.REPLACEMENT);
      
/* 312 */       return new String(arrayOfChar, 0, i);
    }
    catch (SQLException localSQLException)
    {
/* 318 */       throw new IllegalStateException(localSQLException.getMessage());
    }
  }
  
  int UTFEToJavaChar(byte[] paramArrayOfByte, int paramInt1, int paramInt2, char[] paramArrayOfChar, CharacterSet.CharacterConverterBehavior paramCharacterConverterBehavior)
    throws SQLException
  {
/* 328 */     int i = paramInt1;
/* 329 */     int j = paramInt1 + paramInt2;
/* 330 */     int k = 0;
    
/* 336 */     while (i < j)
    {
/* 338 */       int m = utfe2utf8m[high(paramArrayOfByte[i])][low(paramArrayOfByte[(i++)])];
      byte b1;
/* 340 */       byte b2; switch (m >>> 4 & 0xF)
      {
      case 0: 
      case 1: 
      case 2: 
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      case 7: 
/* 359 */         paramArrayOfChar[(k++)] = ((char)(m & 0x7F));
        
/* 361 */         break;
      
      case 8: 
      case 9: 
/* 367 */         paramArrayOfChar[(k++)] = ((char)(m & 0x1F));
        
/* 369 */         break;
      
      case 12: 
      case 13: 
/* 376 */         if (i >= j)
        {
/* 378 */           paramCharacterConverterBehavior.onFailConversion();
          
/* 380 */           i = j;
        }
        else
        {
/* 385 */           m = (byte)(m & 0x1F);
/* 386 */           b1 = utfe2utf8m[high(paramArrayOfByte[i])][low(paramArrayOfByte[(i++)])];
          
/* 389 */           if (!is101xxxxx(b1))
          {
/* 391 */             paramCharacterConverterBehavior.onFailConversion();
            
/* 393 */             paramArrayOfChar[(k++)] = 65533;
          }
          else
          {
/* 399 */             paramArrayOfChar[(k++)] = ((char)(m << 5 | b1 & 0x1F));
          } }
/* 401 */         break;
      
      case 14: 
/* 406 */         if (i + 1 >= j)
        {
/* 408 */           paramCharacterConverterBehavior.onFailConversion();
          
/* 410 */           i = j;
        }
        else
        {
/* 415 */           m = (byte)(m & 0xF);
/* 416 */           b1 = utfe2utf8m[high(paramArrayOfByte[i])][low(paramArrayOfByte[(i++)])];
/* 417 */           b2 = utfe2utf8m[high(paramArrayOfByte[i])][low(paramArrayOfByte[(i++)])];
          
/* 420 */           if ((!is101xxxxx(b1)) || (!is101xxxxx(b2)))
          {
/* 422 */             paramCharacterConverterBehavior.onFailConversion();
            
/* 424 */             paramArrayOfChar[(k++)] = 65533;
          }
          else
          {
/* 430 */             paramArrayOfChar[(k++)] = ((char)(m << 10 | (b1 & 0x1F) << 5 | b2 & 0x1F));
          }
        }
/* 433 */         break;
      
      case 15: 
/* 438 */         if (i + 2 >= j)
        {
/* 440 */           paramCharacterConverterBehavior.onFailConversion();
          
/* 442 */           i = j;
        }
        else
        {
/* 447 */           m = (byte)(m & 0x1);
/* 448 */           b1 = utfe2utf8m[high(paramArrayOfByte[i])][low(paramArrayOfByte[(i++)])];
/* 449 */           b2 = utfe2utf8m[high(paramArrayOfByte[i])][low(paramArrayOfByte[(i++)])];
/* 450 */           byte b3 = utfe2utf8m[high(paramArrayOfByte[i])][low(paramArrayOfByte[(i++)])];
          
/* 453 */           if ((!is101xxxxx(b1)) || (!is101xxxxx(b2)) || (!is101xxxxx(b3)))
          {
/* 455 */             paramCharacterConverterBehavior.onFailConversion();
            
/* 457 */             paramArrayOfChar[(k++)] = 65533;
          }
          else
          {
/* 463 */             paramArrayOfChar[(k++)] = ((char)(m << 15 | (b1 & 0x1F) << 10 | (b2 & 0x1F) << 5 | b3 & 0x1F));
          }
        }
/* 466 */         break;
      
      case 10: 
      case 11: 
      default: 
/* 471 */         paramCharacterConverterBehavior.onFailConversion();
        
/* 473 */         paramArrayOfChar[(k++)] = 65533;
      }
      
    }
    
/* 480 */     return k;
  }
  
  public byte[] convertWithReplacement(String paramString)
  {
/* 495 */     char[] arrayOfChar = paramString.toCharArray();
/* 496 */     byte[] arrayOfByte1 = new byte[arrayOfChar.length * 4];
    
/* 498 */     int i = javaCharsToUTFE(arrayOfChar, 0, arrayOfChar.length, arrayOfByte1, 0);
/* 499 */     byte[] arrayOfByte2 = new byte[i];
    
/* 501 */     System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, i);
    
/* 503 */     return arrayOfByte2;
  }
  
  public byte[] convert(String paramString)
    throws SQLException
  {
/* 520 */     return convertWithReplacement(paramString);
  }
  
  public byte[] convert(CharacterSet paramCharacterSet, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
    byte[] arrayOfByte;
    
/* 531 */     if (paramCharacterSet.rep == 3)
    {
/* 533 */       arrayOfByte = useOrCopy(paramArrayOfByte, paramInt1, paramInt2);
    }
    else
    {
/* 538 */       String str = paramCharacterSet.toString(paramArrayOfByte, paramInt1, paramInt2);
      
/* 540 */       arrayOfByte = convert(str);
    }
    
/* 543 */     return arrayOfByte;
  }
  
  int javaCharsToUTFE(char[] paramArrayOfChar, int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3)
  {
/* 552 */     int i = paramInt1 + paramInt2;
    
/* 554 */     int k = 0;
    
/* 556 */     for (int m = paramInt1; m < i; m++)
    {
/* 559 */       int n = paramArrayOfChar[m];
      int j;
/* 561 */       if (n <= 31)
      {
/* 565 */         j = n | 0x80;
/* 566 */         paramArrayOfByte[(k++)] = utf8m2utfe[high(j)][low(j)];
      }
/* 568 */       else if (n <= 127)
      {
/* 572 */         paramArrayOfByte[(k++)] = utf8m2utfe[high(n)][low(n)];
      }
/* 574 */       else if (n <= 1023)
      {
/* 578 */         j = (n & 0x3E0) >> 5 | 0xC0;
/* 579 */         paramArrayOfByte[(k++)] = utf8m2utfe[high(j)][low(j)];
/* 580 */         j = n & 0x1F | 0xA0;
/* 581 */         paramArrayOfByte[(k++)] = utf8m2utfe[high(j)][low(j)];
      }
/* 583 */       else if (n <= 16383)
      {
/* 587 */         j = (n & 0x3C00) >> 10 | 0xE0;
/* 588 */         paramArrayOfByte[(k++)] = utf8m2utfe[high(j)][low(j)];
/* 589 */         j = (n & 0x3E0) >> 5 | 0xA0;
/* 590 */         paramArrayOfByte[(k++)] = utf8m2utfe[high(j)][low(j)];
/* 591 */         j = n & 0x1F | 0xA0;
/* 592 */         paramArrayOfByte[(k++)] = utf8m2utfe[high(j)][low(j)];
      }
      else
      {
/* 598 */         j = (n & 0x8000) >> 15 | 0xF0;
/* 599 */         paramArrayOfByte[(k++)] = utf8m2utfe[high(j)][low(j)];
/* 600 */         j = (n & 0x7C00) >> 10 | 0xA0;
/* 601 */         paramArrayOfByte[(k++)] = utf8m2utfe[high(j)][low(j)];
/* 602 */         j = (n & 0x3E0) >> 5 | 0xA0;
/* 603 */         paramArrayOfByte[(k++)] = utf8m2utfe[high(j)][low(j)];
/* 604 */         j = n & 0x1F | 0xA0;
/* 605 */         paramArrayOfByte[(k++)] = utf8m2utfe[high(j)][low(j)];
      }
    }
    
/* 609 */     return k;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
    throws SQLException
  {
/* 617 */     byte[] arrayOfByte = paramCharacterWalker.bytes;
/* 618 */     int i = paramCharacterWalker.next;
/* 619 */     int j = paramCharacterWalker.end;
/* 620 */     int k = 0;
    
/* 622 */     if (i >= j)
    {
/* 624 */       failUTFConversion();
    }
    
/* 627 */     int m = arrayOfByte[i];
/* 628 */     int n = getUTFByteLength((byte)m);
    
/* 630 */     if ((n == 0) || (i + (n - 1) >= j))
    {
/* 632 */       failUTFConversion();
    }
    
    try
    {
/* 638 */       char[] arrayOfChar = new char[2];
/* 639 */       int i1 = UTFEToJavaChar(arrayOfByte, i, n, arrayOfChar, CharacterSet.CharacterConverterBehavior.REPORT_ERROR);
      
/* 642 */       paramCharacterWalker.next += n;
      
/* 644 */       if (i1 == 1)
      {
/* 647 */         return arrayOfChar[0];
      }
      
/* 652 */       return arrayOfChar[0] << '\020' | arrayOfChar[1];
    }
    catch (SQLException localSQLException)
    {
/* 657 */       failUTFConversion();
      
/* 660 */       paramCharacterWalker.next = i;
    }
/* 662 */     return k;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
/* 672 */     if ((paramInt & 0xFFFF0000) != 0)
    {
/* 674 */       failUTFConversion();
    }
    else {
      int i;
/* 678 */       if (paramInt <= 31)
      {
/* 682 */         need(paramCharacterBuffer, 1);
        
/* 684 */         i = paramInt | 0x80;
/* 685 */         paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = utf8m2utfe[high(i)][low(i)];
      }
/* 687 */       else if (paramInt <= 127)
      {
/* 691 */         need(paramCharacterBuffer, 1);
        
/* 693 */         paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = utf8m2utfe[high(paramInt)][low(paramInt)];
      }
/* 695 */       else if (paramInt <= 1023)
      {
/* 699 */         need(paramCharacterBuffer, 2);
        
/* 701 */         i = (paramInt & 0x3E0) >> 5 | 0xC0;
/* 702 */         paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = utf8m2utfe[high(i)][low(i)];
/* 703 */         i = paramInt & 0x1F | 0xA0;
/* 704 */         paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = utf8m2utfe[high(i)][low(i)];
      }
/* 706 */       else if (paramInt <= 16383)
      {
/* 710 */         need(paramCharacterBuffer, 3);
        
/* 712 */         i = (paramInt & 0x3C00) >> 10 | 0xE0;
/* 713 */         paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = utf8m2utfe[high(i)][low(i)];
/* 714 */         i = (paramInt & 0x3E0) >> 5 | 0xA0;
/* 715 */         paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = utf8m2utfe[high(i)][low(i)];
/* 716 */         i = paramInt & 0x1F | 0xA0;
/* 717 */         paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = utf8m2utfe[high(i)][low(i)];
      }
      else
      {
/* 723 */         need(paramCharacterBuffer, 4);
        
/* 725 */         i = (paramInt & 0x8000) >> 15 | 0xF0;
/* 726 */         paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = utf8m2utfe[high(i)][low(i)];
/* 727 */         i = (paramInt & 0x7C00) >> 10 | 0xA0;
/* 728 */         paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = utf8m2utfe[high(i)][low(i)];
/* 729 */         i = (paramInt & 0x3E0) >> 5 | 0xA0;
/* 730 */         paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = utf8m2utfe[high(i)][low(i)];
/* 731 */         i = paramInt & 0x1F | 0xA0;
/* 732 */         paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = utf8m2utfe[high(i)][low(i)];
      }
    }
  }
  
  private static int high(int paramInt)
  {
/* 742 */     return paramInt >> 4 & 0xF;
  }
  
  private static int low(int paramInt)
  {
/* 750 */     return paramInt & 0xF;
  }
  
  private static boolean is101xxxxx(byte paramByte)
  {
/* 758 */     return (paramByte & 0xFFFFFFE0) == -96;
  }
  
  private static int getUTFByteLength(byte paramByte)
  {
/* 778 */     return m_byteLen[(utfe2utf8m[high(paramByte)][low(paramByte)] >>> 4 & 0xF)];
  }
  
/* 782 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetUTFE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */