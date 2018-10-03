package oracle.sql;
import java.sql.SQLException;
class CharacterSetAL16UTF16
  extends CharacterSet
  implements CharacterRepConstants
{
  CharacterSetAL16UTF16(int paramInt)
  {
/*  51 */     super(paramInt);
    
/*  53 */     this.rep = 4;
  }
  
  public boolean isLossyFrom(CharacterSet paramCharacterSet)
  {
/*  62 */     return !paramCharacterSet.isUnicode();
  }
  
  public boolean isConvertibleFrom(CharacterSet paramCharacterSet)
  {
/*  71 */     boolean bool = paramCharacterSet.rep <= 1024;
    
/*  73 */     return bool;
  }
  
  public boolean isUnicode()
  {
/*  80 */     return true;
  }
  
  public String toStringWithReplacement(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
/*  94 */       char[] arrayOfChar = new char[paramInt2 >>> 1];
/*  95 */       int i = CharacterSet.convertAL16UTF16BytesToJavaChars(paramArrayOfByte, paramInt1, arrayOfChar, 0, paramInt2, true);
      
/*  98 */       return new String(arrayOfChar, 0, i);
    }
    catch (SQLException localSQLException) {}
    
/* 104 */     return "";
  }
  
  public String toString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
    try
    {
/* 118 */       char[] arrayOfChar = new char[paramInt2 >>> 1];
      
/* 123 */       int i = CharacterSet.convertAL16UTF16BytesToJavaChars(paramArrayOfByte, paramInt1, arrayOfChar, 0, paramInt2, false);
      
/* 126 */       return new String(arrayOfChar, 0, i);
    }
    catch (SQLException localSQLException)
    {
/* 130 */       failUTFConversion();
    }
/* 132 */     return "";
  }
  
  public byte[] convert(String paramString)
    throws SQLException
  {
/* 140 */     return stringToAL16UTF16Bytes(paramString);
  }
  
  public byte[] convertWithReplacement(String paramString)
  {
/* 147 */     return stringToAL16UTF16Bytes(paramString);
  }
  
  public byte[] convert(CharacterSet paramCharacterSet, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
    byte[] arrayOfByte;
    
/* 157 */     if (paramCharacterSet.rep == 4)
    {
/* 159 */       arrayOfByte = useOrCopy(paramArrayOfByte, paramInt1, paramInt2);
    }
    else
    {
/* 163 */       String str = paramCharacterSet.toString(paramArrayOfByte, paramInt1, paramInt2);
      
/* 165 */       arrayOfByte = stringToAL16UTF16Bytes(str);
    }
    
/* 168 */     return arrayOfByte;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
    throws SQLException
  {
/* 182 */     byte[] arrayOfByte = paramCharacterWalker.bytes;
/* 183 */     int k = paramCharacterWalker.next;
/* 184 */     int m = paramCharacterWalker.end;
    
/* 187 */     if (k + 2 >= m)
    {
/* 189 */       failUTFConversion();
    }
    
/* 192 */     int i = arrayOfByte[(k++)];
/* 193 */     int j = arrayOfByte[(k++)];
/* 194 */     int n = i << 8 & 0xFF00 | j;
/* 195 */     paramCharacterWalker.next = k;
    
/* 197 */     return n;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
/* 204 */     if (paramInt > 65535)
    {
/* 206 */       failUTFConversion();
    }
    else
    {
/* 210 */       need(paramCharacterBuffer, 2);
      
/* 212 */       paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = ((byte)(paramInt >> 8 & 0xFF));
/* 213 */       paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = ((byte)(paramInt & 0xFF));
    }
  }
  
/* 219 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetAL16UTF16.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */