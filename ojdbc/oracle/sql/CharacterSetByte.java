package oracle.sql;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
class CharacterSetByte
  extends CharacterSet
  implements CharacterRepConstants
{
  CharacterSetByte(int paramInt)
  {
/* 275 */     super(paramInt);
    
/* 277 */     this.rep = 1;
  }
  
  public boolean isLossyFrom(CharacterSet paramCharacterSet)
  {
/* 284 */     return paramCharacterSet.rep != 1;
  }
  
  public boolean isConvertibleFrom(CharacterSet paramCharacterSet)
  {
/* 291 */     return paramCharacterSet.rep <= 1024;
  }
  
  private String toString(byte[] paramArrayOfByte, int paramInt1, int paramInt2, char paramChar)
    throws SQLException
  {
    try
    {
/* 301 */       return new String(paramArrayOfByte, paramInt1, paramInt2, "ASCII");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
/* 308 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 183);
/* 309 */       localSQLException.fillInStackTrace();
/* 310 */       throw localSQLException;
    }
  }
  
  public String toStringWithReplacement(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
/* 321 */       return toString(paramArrayOfByte, paramInt1, paramInt2, '?');
    }
    catch (SQLException localSQLException)
    {
/* 327 */       throw new Error("CharacterSetByte.toString");
    }
  }
  
  public String toString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 336 */     return toString(paramArrayOfByte, paramInt1, paramInt2, '\000');
  }
  
  public byte[] convert(String paramString)
    throws SQLException
  {
/* 343 */     int i = paramString.length();
/* 344 */     char[] arrayOfChar = new char[paramString.length()];
    
/* 346 */     paramString.getChars(0, i, arrayOfChar, 0);
    
/* 348 */     return charsToBytes(arrayOfChar, (byte)0);
  }
  
  public byte[] convertWithReplacement(String paramString)
  {
/* 355 */     int i = paramString.length();
/* 356 */     char[] arrayOfChar = new char[paramString.length()];
    
/* 358 */     paramString.getChars(0, i, arrayOfChar, 0);
    
    try
    {
/* 362 */       return charsToBytes(arrayOfChar, (byte)63);
    }
    catch (SQLException localSQLException) {}
    
/* 368 */     return new byte[0];
  }
  
  public byte[] convert(CharacterSet paramCharacterSet, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
    byte[] arrayOfByte;
    
/* 379 */     if (paramCharacterSet.rep == 1)
    {
/* 381 */       arrayOfByte = useOrCopy(paramArrayOfByte, paramInt1, paramInt2);
    } else { Object localObject;
/* 383 */       if (paramCharacterSet.rep == 2)
      {
/* 385 */         localObject = CharacterSetUTF.UTFToJavaChar(paramArrayOfByte, paramInt1, paramInt2);
        
/* 387 */         arrayOfByte = charsToBytes((char[])localObject, (byte)0);
      }
      else
      {
/* 391 */         localObject = paramCharacterSet.toString(paramArrayOfByte, paramInt1, paramInt2);
/* 392 */         char[] arrayOfChar = ((String)localObject).toCharArray();
        
/* 394 */         arrayOfByte = charsToBytes(arrayOfChar, (byte)0);
      }
    }
/* 397 */     return arrayOfByte;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
  {
/* 404 */     int i = paramCharacterWalker.bytes[paramCharacterWalker.next] & 0xFF;
    
/* 406 */     paramCharacterWalker.next += 1;
    
/* 408 */     return i;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
/* 415 */     need(paramCharacterBuffer, 1);
    
/* 417 */     if (paramInt < 256)
    {
/* 419 */       paramCharacterBuffer.bytes[paramCharacterBuffer.next] = ((byte)paramInt);
/* 420 */       paramCharacterBuffer.next += 1;
    }
  }
  
  static byte[] charsToBytes(char[] paramArrayOfChar, byte paramByte)
    throws SQLException
  {
/* 433 */     byte[] arrayOfByte = new byte[paramArrayOfChar.length];
    
/* 435 */     for (int i = 0; i < paramArrayOfChar.length; i++)
    {
/* 437 */       if (paramArrayOfChar[i] > 'ÿ')
      {
/* 439 */         arrayOfByte[i] = paramByte;
        
/* 441 */         if (paramByte == 0)
        {
/* 443 */           failCharacterConversion(CharacterSet.make(31));
        }
      }
      else
      {
/* 448 */         arrayOfByte[i] = ((byte)paramArrayOfChar[i]);
      }
    }
    
/* 452 */     return arrayOfByte;
  }
  
/* 456 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetByte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */