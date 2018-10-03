package oracle.sql;
import java.sql.SQLException;
class CharacterSetAL16UTF16LE
  extends CharacterSet
  implements CharacterRepConstants
{
  CharacterSetAL16UTF16LE(int paramInt)
  {
/*  50 */     super(paramInt);
    
/*  53 */     this.rep = 5;
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
/*  90 */       char[] arrayOfChar = new char[Math.min(paramArrayOfByte.length - paramInt1 >>> 1, paramInt2 >>> 1)];
      
/*  92 */       int i = CharacterSet.convertAL16UTF16LEBytesToJavaChars(paramArrayOfByte, paramInt1, arrayOfChar, 0, paramInt2, true);
      
/*  95 */       return new String(arrayOfChar, 0, i);
    }
    catch (SQLException localSQLException) {}
    
/* 101 */     return "";
  }
  
  public String toString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
    try
    {
/* 116 */       char[] arrayOfChar = new char[Math.min(paramArrayOfByte.length - paramInt1 >>> 1, paramInt2 >>> 1)];
      
/* 118 */       int i = CharacterSet.convertAL16UTF16LEBytesToJavaChars(paramArrayOfByte, paramInt1, arrayOfChar, 0, paramInt2, false);
      
/* 121 */       return new String(arrayOfChar, 0, i);
    }
    catch (SQLException localSQLException)
    {
/* 125 */       failUTFConversion();
    }
/* 127 */     return "";
  }
  
  public byte[] convert(String paramString)
    throws SQLException
  {
/* 135 */     return stringToAL16UTF16LEBytes(paramString);
  }
  
  public byte[] convertWithReplacement(String paramString)
  {
/* 142 */     return stringToAL16UTF16LEBytes(paramString);
  }
  
  public byte[] convert(CharacterSet paramCharacterSet, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
    byte[] arrayOfByte;
    
/* 152 */     if (paramCharacterSet.rep == 5)
    {
/* 154 */       arrayOfByte = useOrCopy(paramArrayOfByte, paramInt1, paramInt2);
    }
    else
    {
/* 158 */       String str = paramCharacterSet.toString(paramArrayOfByte, paramInt1, paramInt2);
      
/* 160 */       arrayOfByte = stringToAL16UTF16LEBytes(str);
    }
    
/* 163 */     return arrayOfByte;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
    throws SQLException
  {
/* 177 */     byte[] arrayOfByte = paramCharacterWalker.bytes;
/* 178 */     int k = paramCharacterWalker.next;
/* 179 */     int m = paramCharacterWalker.end;
    
/* 182 */     if (k + 2 >= m)
    {
/* 184 */       failUTFConversion();
    }
    
/* 187 */     int i = arrayOfByte[(k++)];
/* 188 */     int j = arrayOfByte[(k++)];
/* 189 */     int n = i << 8 & 0xFF00 | j;
/* 190 */     paramCharacterWalker.next = k;
    
/* 192 */     return n;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
/* 199 */     if (paramInt > 65535)
    {
/* 201 */       failUTFConversion();
    }
    else
    {
/* 205 */       need(paramCharacterBuffer, 2);
      
/* 207 */       paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = ((byte)(paramInt >> 8 & 0xFF));
/* 208 */       paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = ((byte)(paramInt & 0xFF));
    }
  }
  
/* 214 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetAL16UTF16LE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */