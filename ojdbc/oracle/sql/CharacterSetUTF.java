package oracle.sql;
import java.sql.SQLException;
class CharacterSetUTF
  extends CharacterSet
  implements CharacterRepConstants
{
/*  52 */   private static int[] m_byteLen = { 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 2, 3, 0 };
  
  CharacterSetUTF(int paramInt)
  {
/*  64 */     super(paramInt);
    
/*  67 */     this.rep = 2;
  }
  
  public boolean isLossyFrom(CharacterSet paramCharacterSet)
  {
/*  76 */     return !paramCharacterSet.isUnicode();
  }
  
  public boolean isConvertibleFrom(CharacterSet paramCharacterSet)
  {
/*  85 */     boolean bool = paramCharacterSet.rep <= 1024;
    
/*  87 */     return bool;
  }
  
  public boolean isUnicode()
  {
/*  96 */     return true;
  }
  
  public String toStringWithReplacement(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
/* 126 */       char[] arrayOfChar = new char[paramArrayOfByte.length];
/* 127 */       int[] arrayOfInt = new int[1];
      
/* 129 */       arrayOfInt[0] = paramInt2;
      
/* 131 */       int i = CharacterSet.convertUTFBytesToJavaChars(paramArrayOfByte, paramInt1, arrayOfChar, 0, arrayOfInt, true);
      
/* 134 */       return new String(arrayOfChar, 0, i);
    }
    catch (SQLException localSQLException) {}
    
/* 140 */     return "";
  }
  
  public String toString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
    try
    {
/* 173 */       char[] arrayOfChar = new char[paramArrayOfByte.length];
/* 174 */       int[] arrayOfInt = new int[1];
      
/* 176 */       arrayOfInt[0] = paramInt2;
      
/* 178 */       int i = CharacterSet.convertUTFBytesToJavaChars(paramArrayOfByte, paramInt1, arrayOfChar, 0, arrayOfInt, false);
      
/* 181 */       return new String(arrayOfChar, 0, i);
    }
    catch (SQLException localSQLException)
    {
/* 185 */       failUTFConversion();
    }
/* 187 */     return "";
  }
  
  public byte[] convertWithReplacement(String paramString)
  {
/* 213 */     return stringToUTF(paramString);
  }
  
  public byte[] convert(String paramString)
    throws SQLException
  {
/* 237 */     return stringToUTF(paramString);
  }
  
  public byte[] convert(CharacterSet paramCharacterSet, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
    byte[] arrayOfByte;
    
/* 261 */     if (paramCharacterSet.rep == 2)
    {
/* 263 */       arrayOfByte = useOrCopy(paramArrayOfByte, paramInt1, paramInt2);
    }
    else
    {
/* 268 */       String str = paramCharacterSet.toString(paramArrayOfByte, paramInt1, paramInt2);
      
/* 270 */       arrayOfByte = stringToUTF(str);
    }
    
/* 273 */     return arrayOfByte;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
    throws SQLException
  {
/* 299 */     byte[] arrayOfByte = paramCharacterWalker.bytes;
/* 300 */     int i = paramCharacterWalker.next;
/* 301 */     int j = paramCharacterWalker.end;
    
/* 303 */     if (i >= j)
    {
/* 305 */       failUTFConversion();
    }
    
/* 308 */     int k = arrayOfByte[i];
/* 309 */     int m = getUTFByteLength((byte)k);
    
/* 311 */     if ((m == 0) || (i + (m - 1) >= j))
    {
/* 313 */       failUTFConversion();
    }
    
/* 317 */     if ((m == 3) && (isHiSurrogate((byte)k, arrayOfByte[(i + 1)])) && (i + 5 < j))
    {
/* 320 */       m = 6;
    }
    
    try
    {
/* 326 */       char[] arrayOfChar = new char[2];
/* 327 */       int[] arrayOfInt = new int[1];
      
/* 329 */       arrayOfInt[0] = m;
      
/* 331 */       int n = CharacterSet.convertUTFBytesToJavaChars(arrayOfByte, i, arrayOfChar, 0, arrayOfInt, false);
      
/* 334 */       paramCharacterWalker.next += m;
      
/* 336 */       if (n == 1)
      {
/* 339 */         return arrayOfChar[0];
      }
      
/* 344 */       return arrayOfChar[0] << '\020' | arrayOfChar[1];
    }
    catch (SQLException localSQLException)
    {
/* 349 */       failUTFConversion();
    }
    
/* 352 */     return 0;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
    char[] arrayOfChar;
    
    int i;
    
/* 377 */     if ((paramInt & 0xFFFF0000) != 0)
    {
/* 379 */       need(paramCharacterBuffer, 6);
      
/* 381 */       arrayOfChar = new char[] { (char)(paramInt >>> 16), (char)paramInt };
      
/* 385 */       i = CharacterSet.convertJavaCharsToUTFBytes(arrayOfChar, 0, paramCharacterBuffer.bytes, paramCharacterBuffer.next, 2);
    }
    else
    {
/* 390 */       need(paramCharacterBuffer, 3);
      
/* 392 */       arrayOfChar = new char[] { (char)paramInt };
      
/* 396 */       i = CharacterSet.convertJavaCharsToUTFBytes(arrayOfChar, 0, paramCharacterBuffer.bytes, paramCharacterBuffer.next, 1);
    }
    
/* 400 */     paramCharacterBuffer.next += i;
  }
  
  private static int getUTFByteLength(byte paramByte)
  {
/* 420 */     return m_byteLen[(paramByte >>> 4 & 0xF)];
  }
  
  private static boolean isHiSurrogate(byte paramByte1, byte paramByte2)
  {
/* 427 */     return (paramByte1 == -19) && (paramByte2 >= -96);
  }
  
  public int encodedByteLength(String paramString)
  {
/* 442 */     return CharacterSet.stringUTFLength(paramString);
  }
  
  public int encodedByteLength(char[] paramArrayOfChar)
  {
/* 457 */     return CharacterSet.charArrayUTF8Length(paramArrayOfChar);
  }
  
/* 461 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetUTF.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */