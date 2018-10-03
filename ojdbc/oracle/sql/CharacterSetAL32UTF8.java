package oracle.sql;
import java.sql.SQLException;
class CharacterSetAL32UTF8
  extends CharacterSet
  implements CharacterRepConstants
{
/*  55 */   private static int[] m_byteLen = { 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 2, 3, 4 };
  
  CharacterSetAL32UTF8(int paramInt)
  {
/*  62 */     super(paramInt);
    
/*  64 */     this.rep = 6;
  }
  
  public boolean isLossyFrom(CharacterSet paramCharacterSet)
  {
/*  73 */     return !paramCharacterSet.isUnicode();
  }
  
  public boolean isConvertibleFrom(CharacterSet paramCharacterSet)
  {
/*  82 */     boolean bool = paramCharacterSet.rep <= 1024;
    
/*  84 */     return bool;
  }
  
  public boolean isUnicode()
  {
/*  92 */     return true;
  }
  
  public String toStringWithReplacement(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
/* 122 */       char[] arrayOfChar = new char[paramArrayOfByte.length];
      
/* 128 */       int[] arrayOfInt = new int[1];
      
/* 130 */       arrayOfInt[0] = paramInt2;
      
/* 132 */       int i = CharacterSet.convertAL32UTF8BytesToJavaChars(paramArrayOfByte, paramInt1, arrayOfChar, 0, arrayOfInt, true);
      
/* 135 */       return new String(arrayOfChar, 0, i);
    }
    catch (SQLException localSQLException) {}
    
/* 141 */     return "";
  }
  
  public String toString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
    try
    {
/* 174 */       char[] arrayOfChar = new char[paramArrayOfByte.length];
/* 175 */       int[] arrayOfInt = new int[1];
      
/* 177 */       arrayOfInt[0] = paramInt2;
      
/* 179 */       int i = CharacterSet.convertAL32UTF8BytesToJavaChars(paramArrayOfByte, paramInt1, arrayOfChar, 0, arrayOfInt, false);
      
/* 182 */       return new String(arrayOfChar, 0, i);
    }
    catch (SQLException localSQLException)
    {
/* 186 */       failUTFConversion();
    }
/* 188 */     return "";
  }
  
  public byte[] convertWithReplacement(String paramString)
  {
/* 214 */     return stringToAL32UTF8(paramString);
  }
  
  public byte[] convert(String paramString)
    throws SQLException
  {
/* 238 */     return stringToAL32UTF8(paramString);
  }
  
  public byte[] convert(CharacterSet paramCharacterSet, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
    byte[] arrayOfByte;
    
/* 262 */     if (paramCharacterSet.rep == 6)
    {
/* 264 */       arrayOfByte = useOrCopy(paramArrayOfByte, paramInt1, paramInt2);
    }
    else
    {
/* 269 */       String str = paramCharacterSet.toString(paramArrayOfByte, paramInt1, paramInt2);
      
/* 271 */       arrayOfByte = stringToAL32UTF8(str);
    }
    
/* 274 */     return arrayOfByte;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
    throws SQLException
  {
/* 300 */     byte[] arrayOfByte = paramCharacterWalker.bytes;
/* 301 */     int i = paramCharacterWalker.next;
/* 302 */     int j = paramCharacterWalker.end;
    
/* 304 */     if (i >= j)
    {
/* 306 */       failUTFConversion();
    }
    
/* 309 */     int k = arrayOfByte[i];
/* 310 */     int m = getUTFByteLength((byte)k);
    
/* 312 */     if ((m == 0) || (i + (m - 1) >= j))
    {
/* 314 */       failUTFConversion();
    }
    
    try
    {
/* 320 */       char[] arrayOfChar = new char[2];
/* 321 */       int[] arrayOfInt = new int[1];
      
/* 323 */       arrayOfInt[0] = m;
      
/* 325 */       int n = CharacterSet.convertAL32UTF8BytesToJavaChars(arrayOfByte, i, arrayOfChar, 0, arrayOfInt, false);
      
/* 328 */       paramCharacterWalker.next += m;
      
/* 330 */       if (n == 1)
      {
/* 333 */         return arrayOfChar[0];
      }
      
/* 338 */       return arrayOfChar[0] << '\020' | arrayOfChar[1];
    }
    catch (SQLException localSQLException)
    {
/* 343 */       failUTFConversion();
    }
    
/* 346 */     return 0;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
    char[] arrayOfChar;
    
    int i;
    
/* 371 */     if ((paramInt & 0xFFFF0000) != 0)
    {
/* 373 */       need(paramCharacterBuffer, 4);
      
/* 375 */       arrayOfChar = new char[] { (char)(paramInt >>> 16), (char)paramInt };
      
/* 379 */       i = CharacterSet.convertJavaCharsToAL32UTF8Bytes(arrayOfChar, 0, paramCharacterBuffer.bytes, paramCharacterBuffer.next, 2);
    }
    else
    {
/* 384 */       need(paramCharacterBuffer, 3);
      
/* 386 */       arrayOfChar = new char[] { (char)paramInt };
      
/* 390 */       i = CharacterSet.convertJavaCharsToAL32UTF8Bytes(arrayOfChar, 0, paramCharacterBuffer.bytes, paramCharacterBuffer.next, 1);
    }
    
/* 394 */     paramCharacterBuffer.next += i;
  }
  
  private static int getUTFByteLength(byte paramByte)
  {
/* 414 */     return m_byteLen[(paramByte >>> 4 & 0xF)];
  }
  
  public int encodedByteLength(String paramString)
  {
/* 429 */     return CharacterSet.string32UTF8Length(paramString);
  }
  
  public int encodedByteLength(char[] paramArrayOfChar)
  {
/* 443 */     return CharacterSet.charArray32UTF8Length(paramArrayOfChar);
  }
  
/* 447 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetAL32UTF8.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */