package oracle.sql;
import java.sql.SQLException;
import oracle.core.lmx.CoreException;
class LnxLibThinFormat
{
  public void parseFormat(String paramString)
    throws SQLException
  {
/*  59 */     int i = 0;
/*  60 */     int j = 0;
/*  61 */     int k = 0;
/*  62 */     int m = 0;
/*  63 */     int n = 0;
    
/*  65 */     int i2 = 0;
/*  66 */     int i3 = 0;
    
/*  68 */     int i4 = 0;
/*  69 */     int i5 = 0;
/*  70 */     int i6 = 0;
/*  71 */     int i7 = 0;
/*  72 */     int i8 = 0;
/*  73 */     int i9 = 39;
/*  74 */     int i10 = 0;
    
/*  76 */     i3 = paramString.length();
    
/*  78 */     char[] arrayOfChar = paramString.toCharArray();
    
/*  80 */     this.LNXNFFIL = true;
    
/*  82 */     while (i3 != 0)
    {
/*  84 */       int i1 = Character.toLowerCase(arrayOfChar[i4]);
      
/*  87 */       switch (i1)
      {
      case 48: 
      case 53: 
      case 57: 
      case 120: 
/*  95 */         if (this.LNXNFFSN)
        {
/*  97 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 102 */         if ((i2 == 120) && (i1 != 120))
        {
/* 104 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 108 */         if (i1 == 53)
        {
/* 110 */           if (i3 == 2)
          {
/* 112 */             i6 = Character.toLowerCase(arrayOfChar[(i4 + 1)]);
          }
/* 114 */           else if (i3 == 3)
          {
/* 116 */             i6 = Character.toLowerCase(arrayOfChar[(i4 + 1)]);
/* 117 */             i7 = Character.toLowerCase(arrayOfChar[(i4 + 2)]);
          }
          
/* 120 */           if ((!this.LNXNFF05) && ((i3 == 1) || ((i3 == 2) && (i6 == 115)) || (i6 == 99) || (i6 == 108) || (i6 == 117) || ((i3 == 3) && (((i6 == 112) && (i7 == 114)) || ((i6 == 112) && (i7 == 116)) || ((i6 == 109) && (i7 == 105))))))
          {
/* 130 */             this.LNXNFF05 = true;
          }
          else
          {
/* 134 */             throw new SQLException(CoreException.getMessage((byte)5));
          }
        }
        
/* 140 */         if (i1 == 120)
        {
/* 143 */           if ((i2 == 0) || (i2 == 109) || (i2 == 48) || (i2 == 120))
          {
/* 147 */             this.LNXNFFHX = true;
            
/* 149 */             if (arrayOfChar[i4] == 'x')
            {
/* 151 */               this.LNXNFFLC = true;
            }
          }
          else
          {
/* 156 */             throw new SQLException(CoreException.getMessage((byte)5));
          }
        }
        
/* 161 */         i++;
/* 162 */         if (i1 != 48) {
          break label2099;
        }
        
/* 166 */         if ((k == 0) && (j != 0))
          break label2099;
/* 168 */         j = i; break;
      
      case 103: 
/* 175 */         if ((this.LNXNFFSN) || (this.LNXNFFHX) || (k != 0) || (i8 == i9) || (i10 > 0) || (i == 0))
        {
/* 182 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 185 */         i10 = -1;
        
/* 187 */         this.lnxnfgps[i8] = ((byte)(0x80 | i));
/* 188 */         i8++;
/* 189 */         break;
      
      case 44: 
/* 193 */         if ((this.LNXNFFSN) || (this.LNXNFFHX) || (k != 0) || (i8 == i9) || (i10 < 0) || (i == 0))
        {
/* 200 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 205 */         m = 1;
/* 206 */         this.lnxnfgps[i8] = ((byte)i);
/* 207 */         i8++;
/* 208 */         i10 = 1;
/* 209 */         break;
      
      case 99: 
      case 108: 
      case 117: 
/* 214 */         if ((this.LNXNFFCH) || (this.LNXNFFCT) || (this.LNXNFFRC) || (this.LNXNFFSN) || (this.LNXNFFDS) || (this.LNXNFFHX))
        {
/* 216 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 219 */         if (i1 == 99)
        {
/* 221 */           n += 7;
/* 222 */           this.LNXNFFIC = true;
        }
/* 224 */         else if (i1 == 108)
        {
/* 226 */           n += 10;
        }
        else
        {
/* 230 */           n += 10;
/* 231 */           this.LNXNFFUN = true;
        }
/* 233 */         if (i4 == i5)
        {
/* 235 */           this.LNXNFFCH = true;
          
          break label2099;
        }
        
/* 242 */         if (i3 == 2)
        {
/* 244 */           i6 = Character.toLowerCase(arrayOfChar[(i4 + 1)]);
        }
/* 246 */         else if (i3 == 3)
        {
/* 248 */           i6 = Character.toLowerCase(arrayOfChar[(i4 + 1)]);
/* 249 */           i7 = Character.toLowerCase(arrayOfChar[(i4 + 2)]);
        }
        
/* 253 */         if ((i3 == 1) || ((i3 == 2) && (i6 == 115)) || ((i3 == 3) && (((i6 == 112) && (i7 == 114)) || ((i6 == 112) && (i7 == 116)) || ((i6 == 109) && (i7 == 105)))))
        {
/* 259 */           this.LNXNFFCT = true;
          
          break label2099;
        }
/* 263 */         if (this.LNXNFF05)
        {
/* 266 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 271 */         this.LNXNFFRC = true;
      
      case 100: 
/* 275 */         if ((i10 > 0) || (this.LNXNFFHX))
        {
/* 277 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 280 */         i10 = -1;
      
      case 118: 
/* 284 */         if (i1 == 118)
        {
/* 286 */           this.LNXNFNRD = true;
        }
      
      case 46: 
/* 292 */         if ((this.LNXNFFSN) || (this.LNXNFFHX) || (k != 0))
        {
/* 294 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 297 */         k = 1;
/* 298 */         this.lnxnflhd = i;
/* 299 */         if (j != 0)
        {
/* 301 */           this.lnxnfzld = (i - j + 1);
/* 302 */           j = 0;
        }
        else
        {
/* 306 */           this.lnxnfzld = 0;
        }
        
/* 309 */         i = 0;
/* 310 */         if ((i1 != 46) && (i1 != 100))
          break label2099;
/* 312 */         n++;
        
/* 314 */         if (i1 != 46) {
          break label2099;
        }
/* 317 */         if (i10 < 0)
        {
/* 319 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 322 */         i10 = 1;
/* 323 */         this.LNXNFRDX = true; break;
      
      case 98: 
/* 330 */         if ((this.LNXNFFSN) || (this.LNXNFFBL) || (this.LNXNFFHX))
        {
/* 332 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 335 */         this.LNXNFFBL = true;
/* 336 */         break;
      
      case 101: 
/* 341 */         if ((this.LNXNFFSN) || (this.LNXNFF05) || (this.LNXNFFHX) || (m != 0))
        {
/* 343 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 348 */         this.LNXNFFSN = true;
        
/* 351 */         if ((i3 < 4) || (arrayOfChar[i4] != arrayOfChar[(i4 + 1)]) || (arrayOfChar[i4] != arrayOfChar[(i4 + 2)]) || (arrayOfChar[i4] != arrayOfChar[(i4 + 3)]))
        {
/* 356 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 359 */         i4 += 3;
/* 360 */         i3 -= 3;
/* 361 */         n += 5;
/* 362 */         break;
      
      case 36: 
/* 365 */         if ((this.LNXNFFSN) || (this.LNXNFFDS) || (this.LNXNFFCH) || (this.LNXNFFCT) || (this.LNXNFFRC) || (this.LNXNFFHX))
        {
/* 367 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 371 */         this.LNXNFFDS = true;
/* 372 */         n++;
/* 373 */         break;
      
      case 114: 
/* 377 */         if ((i4 != i5) || (i3 != 2))
        {
/* 379 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 382 */         this.LNXNFFRN = true;
        
/* 384 */         if (arrayOfChar[i4] == 'r')
        {
/* 386 */           this.LNXNFFLC = true;
        }
/* 388 */         this.lnxnfsiz = 15;
        
/* 390 */         this.LNXNFFVF = true;
/* 391 */         return;
      case 102: 
/* 393 */         if ((i4 != i5) || (!this.LNXNFFIL))
        {
/* 395 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 398 */         this.LNXNFFIL = false;
/* 399 */         i4++;
/* 400 */         if (Character.toLowerCase(arrayOfChar[i4]) == 'm')
        {
/* 402 */           i3--;
          
/* 404 */           i5 = i4 + 1;
/* 405 */           i1 = 109;
          
          break label2099;
        }
/* 409 */         throw new SQLException(CoreException.getMessage((byte)5));
      
      case 112: 
/* 414 */         if ((this.LNXNFFSH) || (this.LNXNFFST) || (this.LNXNFFHX))
        {
/* 416 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 419 */         n++;
        
/* 421 */         i3--;
/* 422 */         if (i3 > 1)
        {
/* 424 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 428 */         i4++;
/* 429 */         if (Character.toLowerCase(arrayOfChar[i4]) == 'r')
        {
/* 431 */           this.LNXNFFPR = true;
          
          break label2099;
        }
/* 435 */         if (Character.toLowerCase(arrayOfChar[i4]) == 't')
        {
/* 437 */           this.LNXNFFPT = true;
          
          break label2099;
        }
/* 441 */         throw new SQLException(CoreException.getMessage((byte)5));
      
      case 109: 
/* 445 */         if ((this.LNXNFFSH) || (this.LNXNFFST) || (this.LNXNFFHX))
        {
/* 447 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 451 */         this.LNXNFFMI = true;
        
/* 454 */         i4++;
/* 455 */         if (Character.toLowerCase(arrayOfChar[i4]) == 'i')
        {
/* 458 */           i3--;
/* 459 */           if (i3 <= 1)
            break label2099;
/* 461 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 467 */         throw new SQLException(CoreException.getMessage((byte)5));
      
      case 115: 
/* 473 */         if ((this.LNXNFFSH) || (this.LNXNFFHX))
        {
/* 475 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 478 */         if (i4 == i5)
        {
/* 480 */           this.LNXNFFSH = true;
          
/* 482 */           i5++;
          break label2099;
        }
/* 485 */         if (i3 == 1)
        {
/* 487 */           this.LNXNFFST = true;
          
          break label2099;
        }
/* 491 */         throw new SQLException(CoreException.getMessage((byte)5));
      
      case 116: 
/* 496 */         if ((i4 != i5) || (i3 < 2) || (i3 > 3))
        {
/* 498 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 502 */         if (Character.toLowerCase(arrayOfChar[(i4 + 1)]) != 'm')
        {
/* 504 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 507 */         this.LNXNFFTM = true;
/* 508 */         this.LNXNFFIL = false;
/* 509 */         switch (i3 == 3 ? Character.toLowerCase(arrayOfChar[(i4 + 2)]) : 57)
        {
        case '9': 
          break;
        
        case 'e': 
/* 515 */           this.LNXNFFSN = true;
/* 516 */           break;
        default: 
/* 518 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
/* 521 */         this.lnxnflhd = 0;
/* 522 */         this.lnxnfrhd = 0;
/* 523 */         this.lnxnfsiz = 64;
/* 524 */         this.lnxnfzld = 0;
/* 525 */         this.lnxnfztr = 0;
        
/* 527 */         this.LNXNFFVF = true;
/* 528 */         return;
      }
      
/* 531 */       throw new SQLException(CoreException.getMessage((byte)5));
      
      label2099:
      
/* 536 */       i2 = i1;
      
/* 539 */       i4++;
/* 540 */       i3--;
    }
    
/* 544 */     if (k != 0)
    {
/* 546 */       this.lnxnfrhd = i;
/* 547 */       this.lnxnfztr = ((this.LNXNFFIL) || (this.LNXNFNRD) ? i : j);
    }
    else
    {
/* 551 */       this.lnxnflhd = i;
/* 552 */       this.lnxnfzld = (j != 0 ? i - j + 1 : 0);
/* 553 */       this.lnxnfrhd = 0;
/* 554 */       this.lnxnfztr = 0;
/* 555 */       this.LNXNFNRD = true;
    }
    
/* 560 */     if (this.LNXNFFSN)
    {
/* 566 */       if (this.lnxnflhd <= 1)
      {
/* 569 */         if (this.lnxnflhd == 0)
        {
/* 571 */           throw new SQLException(CoreException.getMessage((byte)5));
        }
        
      }
      else {
/* 577 */         this.lnxnflhd = 1;
      }
      
/* 580 */       if (this.lnxnfzld > 1)
      {
/* 582 */         this.lnxnfzld = 1;
      }
    }
    
/* 588 */     n += this.lnxnflhd;
/* 589 */     n += this.lnxnfrhd;
/* 590 */     n += i8 + 1;
    
/* 593 */     if (n > 64)
    {
/* 595 */       throw new SQLException(CoreException.getMessage((byte)5));
    }
    
/* 599 */     this.lnxnfsiz = n;
  }
  
/* 605 */   boolean LNXNFFMI = false;
/* 606 */   boolean LNXNFFDS = false;
/* 607 */   boolean LNXNFFPR = false;
/* 608 */   boolean LNXNFFBL = false;
/* 609 */   boolean LNXNFFDA = false;
/* 610 */   boolean LNXNFFED = false;
/* 611 */   boolean LNXNFFSN = false;
/* 612 */   boolean LNXNFFVF = false;
  
/* 614 */   boolean LNXNFFSH = false;
/* 615 */   boolean LNXNFFST = false;
/* 616 */   boolean LNXNFFCH = false;
/* 617 */   boolean LNXNFFCT = false;
/* 618 */   boolean LNXNFFRC = false;
/* 619 */   boolean LNXNFFRN = false;
/* 620 */   boolean LNXNFFLC = false;
/* 621 */   boolean LNXNFFIC = false;
  
/* 623 */   boolean LNXNFNRD = false;
/* 624 */   boolean LNXNFRDX = false;
/* 625 */   boolean LNXNFFIL = false;
  
/* 627 */   boolean LNXNFFPT = false;
/* 628 */   boolean LNXNFF05 = false;
  
/* 630 */   boolean LNXNFFHX = false;
/* 631 */   boolean LNXNFFTM = false;
/* 632 */   boolean LNXNFFUN = false;
  
/* 634 */   byte[] lnxnfgps = new byte[40];
/* 635 */   int lnxnflhd = 0;
/* 636 */   int lnxnfrhd = 0;
/* 637 */   int lnxnfsiz = 0;
/* 638 */   int lnxnfzld = 0;
/* 639 */   int lnxnfztr = 0;
  private static final int LNXPFL_US = 1;
  private static final int LNXPFL_NLS = -1;
  private static final int LXM_LILCURR = 11;
  private static final int LXM_LIUCURR = 11;
  private static final int LXM_LIICURR = 8;
  private static final int LXM_ROMOUT = 15;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/LnxLibThinFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */