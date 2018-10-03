package oracle.sql;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.TimeZone;
public class TIMEZONETAB
{
/*  62 */   private static HashMap<Integer, TIMEZONETAB> instanceCache = null;
/*  63 */   private int instanceCount = 0;
/*  64 */   private int versionNumber = 0;
  
  private TIMEZONETAB(int paramInt)
    throws SQLException
  {
/*  69 */     this.versionNumber = paramInt;
  }
  
  public static TIMEZONETAB getInstance(int paramInt)
    throws SQLException
  {
/*  76 */     if (instanceCache == null)
    {
/*  78 */       synchronized (TIMEZONETAB.class) {
/*  79 */         if (instanceCache == null) {
/*  80 */           instanceCache = new HashMap(5);
        }
      }
    }
    
/*  85 */     ??? = (TIMEZONETAB)instanceCache.get(Integer.valueOf(paramInt));
/*  86 */     if (??? == null)
    {
/*  88 */       synchronized (TIMEZONETAB.class) {
/*  89 */         ??? = (TIMEZONETAB)instanceCache.get(Integer.valueOf(paramInt));
/*  90 */         if (??? == null)
        {
/*  92 */           ??? = new TIMEZONETAB(paramInt);
        }
      }
    }
    
/*  97 */     return ((TIMEZONETAB)???).returnInstance();
  }
  
  private synchronized TIMEZONETAB returnInstance()
  {
/* 102 */     this.instanceCount += 1;
/* 103 */     instanceCache.put(Integer.valueOf(this.versionNumber), this);
/* 104 */     return this;
  }
  
  public synchronized void freeInstance()
    throws SQLException
  {
/* 110 */     this.instanceCount -= 1;
/* 111 */     if (this.instanceCount < 1) {
/* 112 */       instanceCache.remove(Integer.valueOf(this.versionNumber));
    }
  }
  
  public void addTrans(byte[] paramArrayOfByte, int paramInt)
  {
/* 127 */     int[] arrayOfInt = new int[BYTE_SIZE];
    
/* 129 */     int i = paramArrayOfByte[0] & 0xFF;
    
/* 131 */     OffsetDST[] arrayOfOffsetDST = new OffsetDST[i];
/* 132 */     int j = 0;
    
/* 135 */     for (int k = 1; k < i * BYTE_SIZE; k += BYTE_SIZE)
    {
/* 137 */       for (int m = 0; m < BYTE_SIZE; m++) {
/* 138 */         arrayOfInt[m] = (paramArrayOfByte[(m + k)] & 0xFF);
      }
      
/* 143 */       m = (arrayOfInt[0] - 100) * 100 + (arrayOfInt[1] - 100);
      
/* 146 */       Calendar localCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.US);
      
/* 149 */       localCalendar.set(1, m);
/* 150 */       localCalendar.set(2, arrayOfInt[2] - 1);
/* 151 */       localCalendar.set(5, arrayOfInt[3]);
/* 152 */       localCalendar.set(11, arrayOfInt[4] - 1);
/* 153 */       localCalendar.set(12, arrayOfInt[5] - 1);
/* 154 */       localCalendar.set(13, arrayOfInt[6] - 1);
/* 155 */       localCalendar.set(14, 0);
      
/* 158 */       long l = localCalendar.getTime().getTime();
      
/* 161 */       int n = (arrayOfInt[7] - OFFSET_HOUR) * HOUR_MILLISECOND + (arrayOfInt[8] - OFFSET_MINUTE) * MINUTE_MILLISECOND;
      
/* 165 */       byte b = (byte)arrayOfInt[9];
      
/* 168 */       arrayOfOffsetDST[(j++)] = new OffsetDST(new Timestamp(l), n, b);
    }
    
/* 173 */     this.zonetab.put(Integer.valueOf(paramInt & 0x1FF), arrayOfOffsetDST);
  }
  
  public byte getLocalOffset(Calendar paramCalendar, int paramInt, OffsetDST paramOffsetDST)
    throws SQLException
  {
/* 189 */     int k = 0;
/* 190 */     int m = 0;
    
/* 192 */     byte b = 0;
    
/* 196 */     Calendar localCalendar1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.US);
    
/* 198 */     Calendar localCalendar2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.US);
    
/* 202 */     Calendar localCalendar3 = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.US);
    
/* 210 */     localCalendar3.set(1, paramCalendar.get(1));
/* 211 */     localCalendar3.set(2, paramCalendar.get(2));
/* 212 */     localCalendar3.set(5, paramCalendar.get(5));
/* 213 */     localCalendar3.set(11, paramCalendar.get(11));
/* 214 */     localCalendar3.set(12, paramCalendar.get(12));
/* 215 */     localCalendar3.set(13, paramCalendar.get(13));
/* 216 */     localCalendar3.set(14, paramCalendar.get(14));
    
/* 219 */     Calendar localCalendar4 = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.US);
    
/* 222 */     localCalendar4.set(1, localCalendar3.get(1));
/* 223 */     localCalendar4.set(2, localCalendar3.get(2));
/* 224 */     localCalendar4.set(5, 1);
/* 225 */     localCalendar4.set(11, 0);
/* 226 */     localCalendar4.set(12, 0);
/* 227 */     localCalendar4.set(13, 0);
/* 228 */     localCalendar4.set(14, 0);
    
/* 231 */     OffsetDST[] arrayOfOffsetDST = (OffsetDST[])this.zonetab.get(Integer.valueOf(paramInt & 0x1FF));
    
/* 234 */     int i2 = findCloseMatch(arrayOfOffsetDST, localCalendar4.getTimeInMillis());
    
    int n;
    
    int i1;
    
    for (;;)
    {
/* 259 */       localCalendar1.setTime(arrayOfOffsetDST[i2].getTimestamp());
      
/* 261 */       int i = arrayOfOffsetDST[i2].getOFFSET();
      
/* 264 */       localCalendar1.add(10, i / HOUR_MILLISECOND);
      
/* 266 */       localCalendar1.add(12, i % HOUR_MILLISECOND / MINUTE_MILLISECOND);
      
/* 270 */       n = arrayOfOffsetDST[i2].getDSTFLAG();
      
/* 272 */       if (localCalendar3.equals(localCalendar1))
      {
/* 275 */         paramOffsetDST.setOFFSET(arrayOfOffsetDST[i2].getOFFSET());
/* 276 */         paramOffsetDST.setDSTFLAG(arrayOfOffsetDST[i2].getDSTFLAG());
        
/* 278 */         b = 0;
/* 279 */         if (i2 <= 0) {
          break label719;
        }
/* 282 */         i1 = arrayOfOffsetDST[(i2 - 1)].getDSTFLAG();
        
/* 285 */         if ((n != 0) || (i1 != 1)) {
          break label719;
        }
/* 288 */         b = 1;
        
        break label719;
      }
      
/* 293 */       if (localCalendar3.before(localCalendar1))
      {
/* 295 */         if (i2 == 0)
        {
/* 298 */           paramOffsetDST.setOFFSET(0);
/* 299 */           paramOffsetDST.setDSTFLAG((byte)0);
/* 300 */           b = 0;
          
/* 302 */           if (n != 1) {
            break label719;
          }
          
/* 308 */           localCalendar1.add(10, -1);
/* 309 */           if (localCalendar3.before(localCalendar1))
            break label719;
/* 311 */           throw new SQLException("Illegal local time.");
        }
        
/* 318 */         i2 -= 1;
        
/* 320 */         if (i2 >= 0)
        {
/* 322 */           i1 = arrayOfOffsetDST[i2].getDSTFLAG();
          
/* 324 */           if ((n == 1) && (i1 == 0))
          {
/* 329 */             localCalendar1.add(10, -1);
/* 330 */             if (!localCalendar3.before(localCalendar1))
            {
/* 332 */               throw new SQLException("Illegal local time.");
            }
            
          }
          
        }
        
      }
      else
      {
/* 344 */         if (i2 == arrayOfOffsetDST.length - 1) {
          break;
        }
        
/* 350 */         localCalendar2.setTime(arrayOfOffsetDST[(i2 + 1)].getTimestamp());
        
/* 352 */         int j = arrayOfOffsetDST[(i2 + 1)].getOFFSET();
        
/* 355 */         localCalendar2.add(10, j / HOUR_MILLISECOND);
        
/* 357 */         localCalendar2.add(12, j % HOUR_MILLISECOND / MINUTE_MILLISECOND);
        
/* 360 */         if (localCalendar3.before(localCalendar2)) {
          break;
        }
/* 363 */         i2 += 1;
      }
    }
    
/* 369 */     paramOffsetDST.setOFFSET(arrayOfOffsetDST[i2].getOFFSET());
/* 370 */     paramOffsetDST.setDSTFLAG(arrayOfOffsetDST[i2].getDSTFLAG());
    
/* 372 */     b = 0;
/* 373 */     if (n == 0)
    {
/* 375 */       if (i2 > 0)
      {
/* 378 */         i1 = arrayOfOffsetDST[(i2 - 1)].getDSTFLAG();
        
/* 380 */         if (i1 == 1)
        {
/* 386 */           localCalendar1.add(10, 1);
/* 387 */           if (localCalendar3.before(localCalendar1))
          {
/* 389 */             b = 1;
          }
        }
      }
      
/* 394 */       if (i2 != arrayOfOffsetDST.length - 1)
      {
/* 396 */         i1 = arrayOfOffsetDST[(i2 + 1)].getDSTFLAG();
        
/* 398 */         if (i1 == 1)
        {
/* 405 */           localCalendar2.add(10, -1);
/* 406 */           if (!localCalendar3.before(localCalendar2))
          {
/* 408 */             throw new SQLException("Illegal local time.");
          }
        }
      }
    }
    
    label719:
    
/* 417 */     return b;
  }
  
  public int getOffset(Calendar paramCalendar, int paramInt)
    throws SQLException
  {
/* 431 */     OffsetDST[] arrayOfOffsetDST = (OffsetDST[])this.zonetab.get(Integer.valueOf(paramInt & 0x1FF));
    
/* 433 */     return getOffset(paramCalendar, arrayOfOffsetDST);
  }
  
  public int getOffset(Calendar paramCalendar, OffsetDST[] paramArrayOfOffsetDST)
    throws SQLException
  {
/* 440 */     int i = 0;
    
/* 443 */     Timestamp localTimestamp = new Timestamp(paramCalendar.getTime().getTime());
    
/* 446 */     int j = findCloseMatch(paramArrayOfOffsetDST, localTimestamp.getTime());
    
/* 449 */     return paramArrayOfOffsetDST[j].getOFFSET();
  }
  
  public boolean isDST(Calendar paramCalendar, OffsetDST[] paramArrayOfOffsetDST)
    throws SQLException
  {
/* 456 */     int i = 0;
    
/* 459 */     Timestamp localTimestamp = new Timestamp(paramCalendar.getTime().getTime());
    
/* 462 */     int j = findCloseMatch(paramArrayOfOffsetDST, localTimestamp.getTime());
    
/* 465 */     return paramArrayOfOffsetDST[j].getDSTFLAG() == 1;
  }
  
  public OffsetDST[] getOffsetDST(int paramInt)
  {
/* 470 */     OffsetDST[] arrayOfOffsetDST = (OffsetDST[])this.zonetab.get(Integer.valueOf(paramInt & 0x1FF));
    
/* 472 */     return arrayOfOffsetDST;
  }
  
  final int findCloseMatch(OffsetDST[] paramArrayOfOffsetDST, long paramLong)
  {
/* 480 */     int i = paramArrayOfOffsetDST.length;
/* 481 */     int j = 0;
/* 482 */     int k = i / 2;
/* 483 */     int m = k;
    
/* 485 */     if (paramLong < paramArrayOfOffsetDST[j].getTime())
    {
/* 488 */       int n = 0;
      
/* 490 */       while ((paramArrayOfOffsetDST[n].getDSTFLAG() == 1) && (n < paramArrayOfOffsetDST.length))
      {
/* 493 */         n++;
      }
      
/* 496 */       return n < paramArrayOfOffsetDST.length ? n : 0;
    }
    
/* 499 */     while (k > 0)
    {
/* 501 */       if (paramLong > paramArrayOfOffsetDST[k].getTime()) {
/* 502 */         j = k;
/* 503 */       } else if (paramLong < paramArrayOfOffsetDST[k].getTime())
/* 504 */         i = k; else {
/* 505 */         if (k == j)
          break;
      }
/* 508 */       k = j + (i - j) / 2;
      
/* 512 */       if (m == k) {
        break;
      }
/* 515 */       m = k;
    }
    
/* 518 */     return k;
  }
  
  public void displayTable(int paramInt)
  {
/* 536 */     OffsetDST[] arrayOfOffsetDST = (OffsetDST[])this.zonetab.get(Integer.valueOf(paramInt));
    
/* 538 */     for (int i = 0; i < arrayOfOffsetDST.length; i++)
    {
/* 540 */       System.out.print(arrayOfOffsetDST[i].getTimestamp().toString());
/* 541 */       System.out.print("    " + arrayOfOffsetDST[i].getOFFSET());
/* 542 */       System.out.println("    " + arrayOfOffsetDST[i].getDSTFLAG());
    }
  }
  
  public boolean checkID(int paramInt)
  {
/* 561 */     return this.zonetab.get(Integer.valueOf(paramInt & 0x1FF)) == null;
  }
  
  public void updateTable(Connection paramConnection, int paramInt)
    throws SQLException, NullPointerException
  {
/* 577 */     byte[] arrayOfByte = TRANSDUMP.getTransitions(paramConnection, paramInt);
    
/* 579 */     if (arrayOfByte == null) {
/* 580 */       throw new NullPointerException();
    }
    
/* 583 */     addTrans(arrayOfByte, paramInt);
  }
  
/* 586 */   private Hashtable zonetab = new Hashtable();
  
/* 589 */   private static int OFFSET_HOUR = 20;
/* 590 */   private static int OFFSET_MINUTE = 60;
  
/* 593 */   private static int HOUR_MILLISECOND = 3600000;
  
/* 596 */   private static int MINUTE_MILLISECOND = 60000;
  
/* 599 */   private static int BYTE_SIZE = 10;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/TIMEZONETAB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */