package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.net.ns.NetInputStream;
import oracle.net.ns.NetOutputStream;
class T4C8TTILobd
  extends T4CTTIMsg
{
  static final int LOBD_STATE0 = 0;
  static final int LOBD_STATE1 = 1;
  static final int LOBD_STATE2 = 2;
  static final int LOBD_STATE3 = 3;
  static final int LOBD_STATE_EXIT = 4;
  static final short TTCG_LNG = 254;
  static final short LOBDATALENGTH = 252;
/* 127 */   static byte[] ucs2Char = new byte[2];
  
  T4C8TTILobd(T4CConnection paramT4CConnection)
  {
/* 140 */     super(paramT4CConnection, (byte)14);
  }
  
  void marshalLobData(byte[] paramArrayOfByte, long paramLong1, long paramLong2, boolean paramBoolean)
    throws IOException
  {
/* 178 */     long l1 = paramLong2;
    
/* 181 */     marshalTTCcode();
/* 182 */     if (paramBoolean)
    {
/* 184 */       this.meg.outStream.flush();
/* 185 */       this.meg.outStream.writeZeroCopyIO(paramArrayOfByte, (int)paramLong1, (int)paramLong2);
    }
    else
    {
/* 189 */       int i = 0;
      
/* 191 */       if (l1 > 252L)
      {
/* 193 */         i = 1;
        
/* 195 */         this.meg.marshalUB1((short)254);
      }
      
/* 199 */       long l2 = 0L;
/* 201 */       for (; 
/* 201 */           l1 > 252L; l1 -= 252L)
      {
/* 203 */         this.meg.marshalUB1((short)252);
/* 204 */         this.meg.marshalB1Array(paramArrayOfByte, (int)(paramLong1 + l2 * 252L), 252);l2 += 1L;
      }
      
/* 208 */       if (l1 > 0L)
      {
/* 210 */         this.meg.marshalUB1((short)(int)l1);
/* 211 */         this.meg.marshalB1Array(paramArrayOfByte, (int)(paramLong1 + l2 * 252L), (int)l1);
      }
      
/* 216 */       if (i == 1) {
/* 217 */         this.meg.marshalUB1((short)0);
      }
    }
  }
  
  void marshalClobUB2_For9iDB(byte[] paramArrayOfByte, long paramLong1, long paramLong2)
    throws IOException
  {
/* 232 */     long l1 = paramLong2;
/* 233 */     int i = 0;
    
/* 235 */     marshalTTCcode();
    
/* 238 */     if (l1 > 84L)
    {
/* 240 */       i = 1;
      
/* 242 */       this.meg.marshalUB1((short)254);
    }
    
/* 248 */     long l2 = 0L;
/* 250 */     for (; 
/* 250 */         l1 > 84L; l1 -= 84L)
    {
/* 254 */       this.meg.marshalUB1((short)252);
      
/* 259 */       for (int j = 0; j < 84; j++)
      {
/* 263 */         this.meg.marshalUB1((short)2);
        
/* 266 */         this.meg.marshalB1Array(paramArrayOfByte, (int)(paramLong1 + l2 * 168L + j * 2), 2);
      }
/* 250 */       l2 += 1L;
    }
    
/* 272 */     if (l1 > 0L)
    {
/* 276 */       long l3 = l1 * 3L;
      
/* 278 */       this.meg.marshalUB1((short)(int)l3);
      
/* 283 */       for (int k = 0; k < l1; k++)
      {
/* 287 */         this.meg.marshalUB1((short)2);
        
/* 290 */         this.meg.marshalB1Array(paramArrayOfByte, (int)(paramLong1 + l2 * 168L + k * 2), 2);
      }
    }
    
/* 297 */     if (i == 1) {
/* 298 */       this.meg.marshalUB1((short)0);
    }
  }
  
  long unmarshalLobData(byte[] paramArrayOfByte, int paramInt, boolean paramBoolean)
    throws SQLException, IOException
  {
/* 361 */     int i = 0;
/* 362 */     int j; boolean bool; if (paramBoolean)
    {
/* 364 */       j = 0;
      
/* 369 */       int[] arrayOfInt = new int[1];
      
/* 372 */       bool = false;
/* 373 */       while (!bool)
      {
/* 375 */         bool = this.meg.inStream.readZeroCopyIO(paramArrayOfByte, paramInt + j, arrayOfInt);
        
/* 379 */         j += arrayOfInt[0];
      }
/* 381 */       i = j;
    }
    else
    {
/* 386 */       j = paramInt;
/* 387 */       int k = 0;
      
/* 390 */       bool = false;
      
/* 393 */       while (bool != true) { int m;
        byte[] arrayOfByte;
/* 395 */         int n; switch (bool)
        {
        case 0: 
/* 400 */           k = this.meg.unmarshalUB1();
          
/* 405 */           if (k == 254) {
/* 406 */             m = 2;
          }
          else
          {
/* 412 */             m = 1;
          }
/* 414 */           break;
        
        case 1: 
/* 424 */           if (paramArrayOfByte.length >= j + k)
          {
/* 426 */             this.meg.getNBytes(paramArrayOfByte, j, k);
          }
          else
          {
/* 430 */             arrayOfByte = new byte[k];
/* 431 */             this.meg.getNBytes(arrayOfByte, 0, k);
/* 432 */             n = Math.min(paramArrayOfByte.length - j, k);
/* 433 */             System.arraycopy(arrayOfByte, 0, paramArrayOfByte, j, n);
          }
          
/* 436 */           i += k;
/* 437 */           m = 4;
          
/* 439 */           break;
        
        case 2: 
/* 448 */           k = this.meg.unmarshalUB1();
          
/* 451 */           if (k > 0) {
/* 452 */             m = 3;
          }
          else
          {
/* 457 */             m = 4;
          }
/* 459 */           break;
        
        case 3: 
/* 473 */           if (paramArrayOfByte.length >= j + k)
          {
/* 476 */             this.meg.getNBytes(paramArrayOfByte, j, k);
          }
          else
          {
/* 491 */             arrayOfByte = new byte[k];
/* 492 */             this.meg.getNBytes(arrayOfByte, 0, k);
/* 493 */             n = Math.min(paramArrayOfByte.length - j, k);
/* 494 */             System.arraycopy(arrayOfByte, 0, paramArrayOfByte, j, n);
          }
          
/* 499 */           i += k;
          
/* 502 */           j += k;
          
/* 507 */           m = 2;
        }
        
      }
    }
    
/* 523 */     return i;
  }
  
  long unmarshalClobUB2_For9iDB(byte[] paramArrayOfByte, int paramInt)
    throws SQLException, IOException
  {
/* 557 */     long l1 = 0L;
/* 558 */     long l2 = paramInt;
/* 559 */     int i = 0;
/* 560 */     int j = 0;
/* 561 */     int k = 0;
    
/* 564 */     int m = 0;
    
/* 567 */     while (m != 4)
    {
/* 569 */       switch (m)
      {
      case 0: 
/* 574 */         i = this.meg.unmarshalUB1();
        
/* 579 */         if (i == 254) {
/* 580 */           m = 2;
        }
        else
        {
/* 586 */           m = 1;
        }
/* 588 */         break;
      
      case 1: 
/* 596 */         for (j = 0; j < i; l1 += 2L)
        {
/* 598 */           k = this.meg.unmarshalUCS2(paramArrayOfByte, l2);j += k;l2 += 2L;
        }
        
/* 601 */         m = 4;
        
/* 603 */         break;
      
      case 2: 
/* 612 */         i = this.meg.unmarshalUB1();
        
/* 615 */         if (i > 0) {
/* 616 */           m = 3;
        }
        else
        {
/* 621 */           m = 4;
        }
/* 623 */         break;
      
      case 3: 
/* 633 */         for (j = 0; j < i; l1 += 2L)
        {
/* 635 */           k = this.meg.unmarshalUCS2(paramArrayOfByte, l2);j += k;l2 += 2L;
        }
        
/* 642 */         m = 2;
      }
      
    }
    
/* 655 */     return l1;
  }
  
/* 660 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4C8TTILobd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */