package oracle.sql;
import java.util.StringTokenizer;
public class INTERVALDS
  extends Datum
{
  public INTERVALDS()
  {
/*  99 */     super(_initIntervalDS());
  }
  
  public INTERVALDS(byte[] paramArrayOfByte)
  {
/* 115 */     super(paramArrayOfByte);
  }
  
  public INTERVALDS(String paramString)
  {
/* 131 */     super(toBytes(paramString));
  }
  
  public byte[] toBytes()
  {
/* 145 */     return getBytes();
  }
  
  public static byte[] toBytes(String paramString)
  {
/* 160 */     int j = 0;int k = 0;int m = 0;int n = 0;int i1 = 0;
/* 161 */     byte[] arrayOfByte = new byte[INTERVALDSMAXLENGTH];
    
/* 163 */     String str5 = null;
    
/* 166 */     String str6 = paramString.trim();
    
/* 169 */     int i2 = str6.charAt(0);
    int i;
/* 171 */     if ((i2 != 45) && (i2 != 43)) {
/* 172 */       i = 0;
    } else {
/* 174 */       i = 1;
    }
    
/* 177 */     str6 = str6.substring(i);
    
/* 180 */     int i3 = str6.indexOf(' ');
    
/* 182 */     String str1 = str6.substring(0, i3);
    
/* 184 */     if (str1.length() > MAXLEADPREC) {
/* 185 */       throw new NumberFormatException();
    }
    
/* 188 */     String str7 = str6.substring(i3 + 1);
    
/* 191 */     StringTokenizer localStringTokenizer = new StringTokenizer(str7, ":.");
    
/* 193 */     if (localStringTokenizer.hasMoreTokens()) {
      String str2;
      String str3;
      String str4;
/* 197 */       try { str2 = localStringTokenizer.nextToken();
/* 198 */         str3 = localStringTokenizer.nextToken();
/* 199 */         str4 = localStringTokenizer.nextToken();
/* 200 */         str5 = localStringTokenizer.nextToken();
      }
      catch (Exception localException)
      {
/* 204 */         throw new NumberFormatException();
      }
      
/* 209 */       j = Integer.valueOf(str1).intValue();
/* 210 */       k = Integer.valueOf(str2).intValue();
/* 211 */       m = Integer.valueOf(str3).intValue();
/* 212 */       n = Integer.valueOf(str4).intValue();
      
/* 215 */       if (k > MAXHOUR) {
/* 216 */         throw new NumberFormatException();
      }
/* 218 */       if (m > MAXMINUTE) {
/* 219 */         throw new NumberFormatException();
      }
/* 221 */       if (n > MAXSECOND) {
/* 222 */         throw new NumberFormatException();
      }
/* 224 */       if (str5.length() <= MAXLEADPREC)
      {
/* 226 */         if (str5.length() < MAXLEADPREC)
        {
/* 229 */           char[] arrayOfChar = new char[MAXLEADPREC];
          
/* 231 */           for (int i4 = 0; i4 < str5.length(); i4++)
          {
/* 233 */             arrayOfChar[i4] = str5.charAt(i4);
          }
/* 235 */           for (int i5 = i4; i5 < MAXLEADPREC; i5++)
          {
/* 237 */             arrayOfChar[i5] = '0';
          }
          
/* 240 */           String str8 = new String(arrayOfChar);
/* 241 */           i1 = Integer.valueOf(str8).intValue();
        }
        else
        {
/* 245 */           i1 = Integer.valueOf(str5).intValue();
        }
      }
      else {
/* 249 */         throw new NumberFormatException();
      }
    }
    else {
/* 253 */       throw new NumberFormatException();
    }
    
/* 256 */     if (i2 == 45)
    {
/* 258 */       j = -j;
/* 259 */       k = -k;
/* 260 */       m = -m;
/* 261 */       n = -n;
/* 262 */       i1 = -i1;
    }
    
/* 267 */     j += INTERVALDAYOFFSET;
    
/* 271 */     arrayOfByte[0] = utilpack.RIGHTSHIFTFIRSTNIBBLE(j);
/* 272 */     arrayOfByte[1] = utilpack.RIGHTSHIFTSECONDNIBBLE(j);
/* 273 */     arrayOfByte[2] = utilpack.RIGHTSHIFTTHIRDNIBBLE(j);
/* 274 */     arrayOfByte[3] = utilpack.RIGHTSHIFTFOURTHNIBBLE(j);
    
/* 277 */     arrayOfByte[4] = ((byte)(k + INTERVALDSOFFSET));
    
/* 280 */     arrayOfByte[5] = ((byte)(m + INTERVALDSOFFSET));
    
/* 283 */     arrayOfByte[6] = ((byte)(n + INTERVALDSOFFSET));
    
/* 289 */     i1 += INTERVALDAYOFFSET;
    
/* 291 */     arrayOfByte[7] = utilpack.RIGHTSHIFTFIRSTNIBBLE(i1);
/* 292 */     arrayOfByte[8] = utilpack.RIGHTSHIFTSECONDNIBBLE(i1);
/* 293 */     arrayOfByte[9] = utilpack.RIGHTSHIFTTHIRDNIBBLE(i1);
/* 294 */     arrayOfByte[10] = utilpack.RIGHTSHIFTFOURTHNIBBLE(i1);
    
/* 296 */     return arrayOfByte;
  }
  
  public static String toString(byte[] paramArrayOfByte)
  {
/* 314 */     int i = 1;
    
/* 316 */     int i1 = 0;
    
/* 320 */     int j = utilpack.LEFTSHIFTFIRSTNIBBLE(paramArrayOfByte[0]);
/* 321 */     j |= utilpack.LEFTSHIFTSECONDNIBBLE(paramArrayOfByte[1]);
/* 322 */     j |= utilpack.LEFTSHIFTTHIRDNIBBLE(paramArrayOfByte[2]);
/* 323 */     j |= paramArrayOfByte[3] & 0xFF;
    
/* 326 */     j -= INTERVALDAYOFFSET;
    
/* 328 */     int k = paramArrayOfByte[4] - INTERVALDSOFFSET;
    
/* 330 */     int m = paramArrayOfByte[5] - INTERVALDSOFFSET;
    
/* 332 */     int n = paramArrayOfByte[6] - INTERVALDSOFFSET;
    
/* 334 */     i1 = utilpack.LEFTSHIFTFIRSTNIBBLE(paramArrayOfByte[7]);
/* 335 */     i1 |= utilpack.LEFTSHIFTSECONDNIBBLE(paramArrayOfByte[8]);
/* 336 */     i1 |= utilpack.LEFTSHIFTTHIRDNIBBLE(paramArrayOfByte[9]);
/* 337 */     i1 |= paramArrayOfByte[10] & 0xFF;
    
/* 342 */     i1 -= INTERVALDAYOFFSET;
    
/* 344 */     if (j < 0)
    {
/* 346 */       i = 0;
/* 347 */       j = -j;
/* 348 */       k = -k;
/* 349 */       m = -m;
/* 350 */       n = -n;
/* 351 */       i1 = -i1;
    }
/* 353 */     else if (k < 0)
    {
/* 355 */       i = 0;
/* 356 */       k = -k;
/* 357 */       m = -m;
/* 358 */       n = -n;
/* 359 */       i1 = -i1;
    }
/* 361 */     else if (m < 0)
    {
/* 363 */       i = 0;
/* 364 */       m = -m;
/* 365 */       n = -n;
/* 366 */       i1 = -i1;
    }
/* 368 */     else if (n < 0)
    {
/* 370 */       i = 0;
/* 371 */       n = -n;
/* 372 */       i1 = -i1;
    }
/* 374 */     else if (i1 < 0)
    {
/* 376 */       i = 0;
/* 377 */       i1 = -i1;
    }
    
/* 380 */     String str = String.format("%09d", new Object[] { Integer.valueOf(i1) });
    
/* 382 */     char[] arrayOfChar = str.toCharArray();
/* 383 */     int i2 = arrayOfChar.length;
    
/* 385 */     while ((i2 > 1) && (arrayOfChar[(i2 - 1)] == '0')) {
/* 386 */       i2--;
    }
/* 388 */     str = str.substring(0, i2);
    
/* 390 */     if (i != 0) {
/* 391 */       return j + " " + k + ":" + m + ":" + n + "." + str;
    }
    
/* 394 */     return "-" + j + " " + k + ":" + m + ":" + n + "." + str;
  }
  
  public Object toJdbc()
  {
/* 409 */     return this;
  }
  
  public String stringValue()
  {
/* 420 */     return toString(getBytes());
  }
  
  public String toString()
  {
/* 433 */     return toString(getBytes());
  }
  
  public boolean isConvertibleTo(Class paramClass)
  {
/* 446 */     if (paramClass.getName().compareTo("java.lang.String") == 0) {
/* 447 */       return true;
    }
/* 449 */     return false;
  }
  
  public Object makeJdbcArray(int paramInt)
  {
/* 461 */     INTERVALDS[] arrayOfINTERVALDS = new INTERVALDS[paramInt];
    
/* 463 */     return arrayOfINTERVALDS;
  }
  
  private static byte[] _initIntervalDS()
  {
/* 474 */     byte[] arrayOfByte = new byte[INTERVALDSMAXLENGTH];
    
/* 478 */     int i = 0;
/* 479 */     int j = 0;
/* 480 */     int k = 0;
/* 481 */     int m = 0;
/* 482 */     int n = 0;
    
/* 486 */     i += INTERVALDAYOFFSET;
    
/* 491 */     arrayOfByte[0] = utilpack.RIGHTSHIFTFIRSTNIBBLE(i);
/* 492 */     arrayOfByte[1] = utilpack.RIGHTSHIFTSECONDNIBBLE(i);
/* 493 */     arrayOfByte[2] = utilpack.RIGHTSHIFTTHIRDNIBBLE(i);
/* 494 */     arrayOfByte[3] = utilpack.RIGHTSHIFTFOURTHNIBBLE(i);
    
/* 499 */     arrayOfByte[4] = ((byte)(j + INTERVALDSOFFSET));
/* 500 */     arrayOfByte[5] = ((byte)(k + INTERVALDSOFFSET));
/* 501 */     arrayOfByte[6] = ((byte)(m + INTERVALDSOFFSET));
    
/* 503 */     n += INTERVALDAYOFFSET;
    
/* 505 */     arrayOfByte[7] = utilpack.RIGHTSHIFTFIRSTNIBBLE(n);
/* 506 */     arrayOfByte[8] = utilpack.RIGHTSHIFTSECONDNIBBLE(n);
/* 507 */     arrayOfByte[9] = utilpack.RIGHTSHIFTTHIRDNIBBLE(n);
/* 508 */     arrayOfByte[10] = utilpack.RIGHTSHIFTFOURTHNIBBLE(n);
    
/* 512 */     return arrayOfByte;
  }
  
/* 515 */   private static int MAXLEADPREC = 9;
/* 516 */   private static int MAXHOUR = 23;
/* 517 */   private static int MAXMINUTE = 59;
/* 518 */   private static int MAXSECOND = 59;
/* 519 */   private static int INTERVALDSMAXLENGTH = 11;
/* 520 */   private static int INTERVALDSOFFSET = 60;
/* 521 */   private static int INTERVALDAYOFFSET = Integer.MIN_VALUE;
  static final long serialVersionUID = 7164731704878764759L;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/INTERVALDS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */