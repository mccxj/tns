package oracle.core.lmx;
import java.io.PrintStream;
public class LmxRepConversion
{
  public static void printInHex(byte paramByte)
  {
/*  28 */     System.out.print((char)nibbleToHex((byte)((paramByte & 0xF0) >> 4)));
/*  29 */     System.out.print((char)nibbleToHex((byte)(paramByte & 0xF)));
  }
  
  public static byte nibbleToHex(byte paramByte)
  {
/*  43 */     paramByte = (byte)(paramByte & 0xF);
/*  44 */     return (byte)(paramByte < 10 ? paramByte + 48 : paramByte - 10 + 65);
  }
  
  public static byte asciiHexToNibble(byte paramByte)
  {
    byte b;
    
/*  64 */     if ((paramByte >= 97) && (paramByte <= 102)) {
/*  65 */       b = (byte)(paramByte - 97 + 10);
    }
/*  67 */     else if ((paramByte >= 65) && (paramByte <= 70)) {
/*  68 */       b = (byte)(paramByte - 65 + 10);
    }
/*  70 */     else if ((paramByte >= 48) && (paramByte <= 57)) {
/*  71 */       b = (byte)(paramByte - 48);
    }
    else {
/*  74 */       b = paramByte;
    }
/*  76 */     return b;
  }
  
  public static void bArray2nibbles(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
/*  88 */     for (int i = 0; i < paramArrayOfByte1.length; i++)
    {
/*  90 */       paramArrayOfByte2[(i * 2)] = nibbleToHex((byte)((paramArrayOfByte1[i] & 0xF0) >> 4));
/*  91 */       paramArrayOfByte2[(i * 2 + 1)] = nibbleToHex((byte)(paramArrayOfByte1[i] & 0xF));
    }
  }
  
  public static String bArray2String(byte[] paramArrayOfByte)
  {
/* 104 */     StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 2);
/* 105 */     for (int i = 0; i < paramArrayOfByte.length; i++)
    {
/* 107 */       localStringBuffer.append((char)nibbleToHex((byte)((paramArrayOfByte[i] & 0xF0) >> 4)));
/* 108 */       localStringBuffer.append((char)nibbleToHex((byte)(paramArrayOfByte[i] & 0xF)));
    }
/* 110 */     return localStringBuffer.toString();
  }
  
  public static byte[] nibbles2bArray(byte[] paramArrayOfByte)
  {
/* 124 */     byte[] arrayOfByte = new byte[paramArrayOfByte.length / 2];
    
/* 127 */     for (int i = 0; i < arrayOfByte.length; i++)
    {
/* 129 */       arrayOfByte[i] = ((byte)(asciiHexToNibble(paramArrayOfByte[(i * 2)]) << 4)); int 
/* 130 */         tmp31_30 = i; byte[] tmp31_29 = arrayOfByte;tmp31_29[tmp31_30] = ((byte)(tmp31_29[tmp31_30] | asciiHexToNibble(paramArrayOfByte[(i * 2 + 1)])));
    }
/* 132 */     return arrayOfByte;
  }
  
  public static void printInHex(long paramLong)
  {
/* 137 */     byte[] arrayOfByte = toHex(paramLong);
/* 138 */     System.out.print(new String(arrayOfByte, 0));
  }
  
  public static void printInHex(int paramInt)
  {
/* 143 */     byte[] arrayOfByte = toHex(paramInt);
/* 144 */     System.out.print(new String(arrayOfByte, 0));
  }
  
  public static void printInHex(short paramShort)
  {
/* 149 */     byte[] arrayOfByte = toHex(paramShort);
/* 150 */     System.out.print(new String(arrayOfByte, 0));
  }
  
  public static byte[] toHex(long paramLong)
  {
/* 155 */     int i = 16;
/* 156 */     byte[] arrayOfByte = new byte[i];
    
/* 158 */     for (int j = i - 1; j >= 0; j--)
    {
/* 160 */       arrayOfByte[j] = nibbleToHex((byte)(int)(paramLong & 0xF));
/* 161 */       paramLong >>= 4;
    }
/* 163 */     return arrayOfByte;
  }
  
  public static byte[] toHex(int paramInt)
  {
/* 168 */     int i = 8;
/* 169 */     byte[] arrayOfByte = new byte[i];
    
/* 171 */     for (int j = i - 1; j >= 0; j--)
    {
/* 173 */       arrayOfByte[j] = nibbleToHex((byte)(paramInt & 0xF));
/* 174 */       paramInt >>= 4;
    }
/* 176 */     return arrayOfByte;
  }
  
  public static byte[] toHex(short paramShort)
  {
/* 181 */     int i = 4;
/* 182 */     byte[] arrayOfByte = new byte[i];
    
/* 184 */     for (int j = i - 1; j >= 0; j--)
    {
/* 186 */       arrayOfByte[j] = nibbleToHex((byte)(paramShort & 0xF));
/* 187 */       paramShort = (short)(paramShort >> 4);
    }
/* 189 */     return arrayOfByte;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/core/lmx/LmxRepConversion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */