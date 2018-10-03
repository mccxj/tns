package oracle.jdbc.util;
import java.io.PrintStream;
public class RepConversion
{
  public static void printInHex(byte paramByte)
  {
/*  36 */     System.out.print((char)nibbleToHex((byte)((paramByte & 0xF0) >> 4)));
/*  37 */     System.out.print((char)nibbleToHex((byte)(paramByte & 0xF)));
  }
  
  public static byte nibbleToHex(byte paramByte)
  {
/*  52 */     paramByte = (byte)(paramByte & 0xF);
    
/*  54 */     return (byte)(paramByte < 10 ? paramByte + 48 : paramByte - 10 + 65);
  }
  
  public static byte asciiHexToNibble(byte paramByte)
  {
    byte b;
    
/*  75 */     if ((paramByte >= 97) && (paramByte <= 102)) {
/*  76 */       b = (byte)(paramByte - 97 + 10);
    }
/*  78 */     else if ((paramByte >= 65) && (paramByte <= 70)) {
/*  79 */       b = (byte)(paramByte - 65 + 10);
    }
/*  81 */     else if ((paramByte >= 48) && (paramByte <= 57)) {
/*  82 */       b = (byte)(paramByte - 48);
    }
    else {
/*  85 */       b = paramByte;
    }
/*  87 */     return b;
  }
  
  public static void bArray2Nibbles(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
/* 101 */     for (int i = 0; i < paramArrayOfByte1.length; i++)
    {
/* 103 */       paramArrayOfByte2[(i * 2)] = nibbleToHex((byte)((paramArrayOfByte1[i] & 0xF0) >> 4));
/* 104 */       paramArrayOfByte2[(i * 2 + 1)] = nibbleToHex((byte)(paramArrayOfByte1[i] & 0xF));
    }
  }
  
  public static String bArray2String(byte[] paramArrayOfByte)
  {
/* 118 */     StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 2);
    
/* 120 */     for (int i = 0; i < paramArrayOfByte.length; i++)
    {
/* 122 */       localStringBuffer.append((char)nibbleToHex((byte)((paramArrayOfByte[i] & 0xF0) >> 4)));
/* 123 */       localStringBuffer.append((char)nibbleToHex((byte)(paramArrayOfByte[i] & 0xF)));
    }
    
/* 126 */     return localStringBuffer.toString();
  }
  
  public static byte[] nibbles2bArray(byte[] paramArrayOfByte)
  {
/* 141 */     byte[] arrayOfByte = new byte[paramArrayOfByte.length / 2];
    
/* 144 */     for (int i = 0; i < arrayOfByte.length; i++)
    {
/* 146 */       arrayOfByte[i] = ((byte)(asciiHexToNibble(paramArrayOfByte[(i * 2)]) << 4)); int 
/* 147 */         tmp31_30 = i; byte[] tmp31_29 = arrayOfByte;tmp31_29[tmp31_30] = ((byte)(tmp31_29[tmp31_30] | asciiHexToNibble(paramArrayOfByte[(i * 2 + 1)])));
    }
    
/* 150 */     return arrayOfByte;
  }
  
  public static void printInHex(long paramLong)
  {
/* 156 */     byte[] arrayOfByte = toHex(paramLong);
    
/* 158 */     System.out.print(new String(arrayOfByte, 0));
  }
  
  public static void printInHex(int paramInt)
  {
/* 164 */     byte[] arrayOfByte = toHex(paramInt);
    
/* 166 */     System.out.print(new String(arrayOfByte, 0));
  }
  
  public static void printInHex(short paramShort)
  {
/* 172 */     byte[] arrayOfByte = toHex(paramShort);
    
/* 174 */     System.out.print(new String(arrayOfByte, 0));
  }
  
  public static byte[] toHex(long paramLong)
  {
/* 180 */     int i = 16;
/* 181 */     byte[] arrayOfByte = new byte[i];
    
/* 183 */     for (int j = i - 1; j >= 0; j--)
    {
/* 185 */       arrayOfByte[j] = nibbleToHex((byte)(int)(paramLong & 0xF));
/* 186 */       paramLong >>= 4;
    }
    
/* 189 */     return arrayOfByte;
  }
  
  public static byte[] toHex(int paramInt)
  {
/* 195 */     int i = 8;
/* 196 */     byte[] arrayOfByte = new byte[i];
    
/* 198 */     for (int j = i - 1; j >= 0; j--)
    {
/* 200 */       arrayOfByte[j] = nibbleToHex((byte)(paramInt & 0xF));
/* 201 */       paramInt >>= 4;
    }
    
/* 204 */     return arrayOfByte;
  }
  
  public static byte[] toHex(short paramShort)
  {
/* 210 */     int i = 4;
/* 211 */     byte[] arrayOfByte = new byte[i];
    
/* 213 */     for (int j = i - 1; j >= 0; j--)
    {
/* 215 */       arrayOfByte[j] = nibbleToHex((byte)(paramShort & 0xF));
/* 216 */       paramShort = (short)(paramShort >> 4);
    }
    
/* 219 */     return arrayOfByte;
  }
  
/* 224 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/util/RepConversion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */