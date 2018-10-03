package oracle.sql;
public class INTERVALYM
  extends Datum
{
  public INTERVALYM()
  {
/*  79 */     super(_initIntervalYM());
  }
  
  public INTERVALYM(byte[] paramArrayOfByte)
  {
/*  97 */     super(paramArrayOfByte);
  }
  
  public INTERVALYM(String paramString)
  {
/* 115 */     super(toBytes(paramString));
  }
  
  public byte[] toBytes()
  {
/* 130 */     return getBytes();
  }
  
  public static byte[] toBytes(String paramString)
  {
/* 147 */     byte[] arrayOfByte = new byte[INTERVALYMMAXLENGTH];
    
/* 151 */     String str1 = paramString.trim();
    
/* 154 */     int j = str1.charAt(0);
    int i;
/* 156 */     if ((j != 45) && (j != 43)) {
/* 157 */       i = 0;
    } else {
/* 159 */       i = 1;
    }
    
/* 162 */     str1 = str1.substring(i);
    
/* 164 */     int k = str1.indexOf('-');
    
/* 166 */     String str2 = str1.substring(0, k);
    
/* 168 */     if (str2.length() > MAXYEARPREC) {
/* 169 */       throw new NumberFormatException();
    }
/* 171 */     int m = Integer.valueOf(str2).intValue();
    
/* 175 */     String str3 = str1.substring(k + 1);
    
/* 177 */     int n = Integer.valueOf(str3).intValue();
    
/* 179 */     if (n >= MAXMONTH) {
/* 180 */       throw new NumberFormatException();
    }
    
/* 183 */     if (j == 45)
    {
/* 185 */       m = -1 * m;
/* 186 */       n = -1 * n;
    }
    
/* 193 */     m += INTYMYEAROFFSET;
    
/* 198 */     arrayOfByte[0] = utilpack.RIGHTSHIFTFIRSTNIBBLE(m);
/* 199 */     arrayOfByte[1] = utilpack.RIGHTSHIFTSECONDNIBBLE(m);
/* 200 */     arrayOfByte[2] = utilpack.RIGHTSHIFTTHIRDNIBBLE(m);
/* 201 */     arrayOfByte[3] = utilpack.RIGHTSHIFTFOURTHNIBBLE(m);
    
/* 205 */     arrayOfByte[4] = ((byte)(n + INTYMMONTHOFFSET));
    
/* 207 */     return arrayOfByte;
  }
  
  public static String toString(byte[] paramArrayOfByte)
  {
/* 225 */     int i = 1;
    
/* 232 */     int j = utilpack.LEFTSHIFTFIRSTNIBBLE(paramArrayOfByte[0]);
/* 233 */     j |= utilpack.LEFTSHIFTSECONDNIBBLE(paramArrayOfByte[1]);
/* 234 */     j |= utilpack.LEFTSHIFTTHIRDNIBBLE(paramArrayOfByte[2]);
/* 235 */     j |= paramArrayOfByte[3] & 0xFF;
    
/* 239 */     j -= INTYMYEAROFFSET;
    
/* 243 */     int k = paramArrayOfByte[4] - INTYMMONTHOFFSET;
    
/* 246 */     if (j < 0)
    {
/* 248 */       i = 0;
/* 249 */       j = -j;
    }
    
/* 253 */     if (k < 0)
    {
/* 255 */       i = 0;
/* 256 */       k = -k;
    }
    
    String str;
    
/* 261 */     if (i != 0) {
/* 262 */       str = j + "-" + k;
    } else {
/* 264 */       str = "-" + j + "-" + k;
    }
/* 266 */     return str;
  }
  
  public Object toJdbc()
  {
/* 282 */     return this;
  }
  
  public String stringValue()
  {
/* 293 */     return toString(getBytes());
  }
  
  public String toString()
  {
/* 306 */     return toString(getBytes());
  }
  
  public Object makeJdbcArray(int paramInt)
  {
/* 322 */     INTERVALYM[] arrayOfINTERVALYM = new INTERVALYM[paramInt];
    
/* 324 */     return arrayOfINTERVALYM;
  }
  
  public boolean isConvertibleTo(Class paramClass)
  {
/* 340 */     if (paramClass.getName().compareTo("java.lang.String") == 0) {
/* 341 */       return true;
    }
/* 343 */     return false;
  }
  
  private static byte[] _initIntervalYM()
  {
/* 356 */     byte[] arrayOfByte = new byte[INTERVALYMMAXLENGTH];
    
/* 360 */     int i = 0;
/* 361 */     int j = 0;
    
/* 365 */     i += INTYMYEAROFFSET;
    
/* 370 */     arrayOfByte[0] = utilpack.RIGHTSHIFTFIRSTNIBBLE(i);
/* 371 */     arrayOfByte[1] = utilpack.RIGHTSHIFTSECONDNIBBLE(i);
/* 372 */     arrayOfByte[2] = utilpack.RIGHTSHIFTTHIRDNIBBLE(i);
/* 373 */     arrayOfByte[3] = utilpack.RIGHTSHIFTFOURTHNIBBLE(i);
    
/* 378 */     arrayOfByte[4] = ((byte)(j + INTYMMONTHOFFSET));
    
/* 382 */     return arrayOfByte;
  }
  
/* 386 */   private static int MASKVAL = 255;
/* 387 */   private static int INTYMYEAROFFSET = Integer.MIN_VALUE;
/* 388 */   private static int INTYMMONTHOFFSET = 60;
/* 389 */   private static int INTERVALYMMAXLENGTH = 5;
/* 390 */   private static int MAXYEARPREC = 9;
/* 391 */   private static int MAXMONTH = 12;
  static final long serialVersionUID = 8393284561907159296L;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/INTERVALYM.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */