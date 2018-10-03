package oracle.net.ns;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PrintStream;
import oracle.net.jdbc.nl.RepConversion;
public class Packet
  implements SQLnetDef
{
  private int buffer2send;
  protected int sdu;
  protected int tdu;
  protected int length;
  public int type;
  protected int flags;
  protected int dataLen;
  protected int dataOff;
  protected String data;
  protected byte[] buffer;
/* 115 */   protected int leftOverFromPreviousRead = 0;
  
/* 121 */   protected int nextPacketOffset = 0;
  
  public SessionAtts sAtts;
  
  public Packet(SessionAtts paramSessionAtts)
  {
/* 135 */     this.sAtts = paramSessionAtts;
/* 136 */     this.sdu = paramSessionAtts.getSDU();
/* 137 */     this.tdu = paramSessionAtts.getTDU();
  }
  
  public Packet(SessionAtts paramSessionAtts, int paramInt)
  {
/* 149 */     this(paramSessionAtts);
/* 150 */     createBuffer(paramInt);
  }
  
  public Packet(SessionAtts paramSessionAtts, int paramInt1, int paramInt2, int paramInt3)
  {
/* 160 */     this(paramSessionAtts);
/* 161 */     createBuffer(paramInt1, paramInt2, paramInt3);
  }
  
  public Packet(Packet paramPacket)
  {
/* 171 */     this(paramPacket.sAtts);
    
/* 173 */     this.length = paramPacket.length;
/* 174 */     this.type = paramPacket.type;
/* 175 */     this.flags = paramPacket.flags;
/* 176 */     this.dataLen = paramPacket.dataLen;
/* 177 */     this.dataOff = paramPacket.dataOff;
/* 178 */     this.buffer = paramPacket.buffer;
  }
  
  protected void createBuffer(int paramInt)
  {
/* 190 */     this.buffer = new byte[paramInt];
    
/* 192 */     this.buffer[0] = ((byte)(paramInt / 256));
/* 193 */     this.buffer[1] = ((byte)(paramInt % 256));
  }
  
  protected void createBuffer(int paramInt1, int paramInt2, int paramInt3)
  {
/* 204 */     createBuffer(paramInt1);
/* 205 */     this.buffer[5] = ((byte)paramInt3);
/* 206 */     this.buffer[4] = ((byte)paramInt2);
  }
  
  protected void receive()
    throws IOException, NetException
  {
/* 249 */     int i = 0;
/* 250 */     int j = 0;
/* 251 */     int k = this.sdu;
    
/* 258 */     if ((this.sAtts.enableJavaNetFastPath) && (!this.sAtts.anoActive)) {
/* 259 */       k -= 37;
    }
/* 261 */     int m = 1;
    
/* 263 */     while (k > 0)
    {
/* 265 */       if (this.leftOverFromPreviousRead > 0)
      {
/* 267 */         System.arraycopy(this.buffer, this.nextPacketOffset, this.buffer, 0, this.leftOverFromPreviousRead);
        
/* 271 */         i = this.leftOverFromPreviousRead;
        
/* 273 */         if (i >= 8)
        {
/* 276 */           processHeader();
/* 277 */           m = 0;
          
/* 279 */           if (this.length >= this.leftOverFromPreviousRead)
          {
/* 281 */             k = this.length - this.leftOverFromPreviousRead;
/* 282 */             this.leftOverFromPreviousRead = 0;
/* 283 */             this.nextPacketOffset = 0;
          }
          else
          {
/* 287 */             this.leftOverFromPreviousRead -= this.length;
/* 288 */             k = 0;
/* 289 */             this.nextPacketOffset = this.length;
          }
          
        }
        else
        {
/* 295 */           k -= i;
/* 296 */           this.leftOverFromPreviousRead -= i;
          
/* 300 */           this.nextPacketOffset = 0;
        }
      }
      
      try
      {
/* 306 */         if (k > 0)
        {
/* 308 */           if ((j = this.sAtts.ntInputStream.read(this.buffer, i, k)) <= 0)
          {
/* 311 */             throw new NetException(0);
          }
/* 313 */           i += j;
        }
        
/* 316 */         if ((m != 0) && (i >= 8))
        {
/* 318 */           m = 0;
          
/* 320 */           processHeader();
/* 321 */           k = this.length;
          
/* 323 */           if (i > this.length)
          {
/* 327 */             this.leftOverFromPreviousRead = (i - this.length);
/* 328 */             k = 0;
/* 329 */             this.nextPacketOffset = this.length;
          }
          else
          {
/* 333 */             this.leftOverFromPreviousRead = 0;
/* 334 */             this.nextPacketOffset = 0;
/* 335 */             k -= i;
          }
          
        }
        else
        {
/* 341 */           k -= j;
        }
        
      }
      catch (InterruptedIOException localInterruptedIOException)
      {
/* 347 */         throw new NetException(504);
      }
    }
  }
  
  void processHeader()
    throws IOException, NetException
  {
/* 374 */     this.length = (this.buffer[0] & 0xFF);
/* 375 */     this.length <<= 8;
/* 376 */     this.length |= this.buffer[1] & 0xFF;
    
/* 378 */     this.type = this.buffer[4];
/* 379 */     this.flags = this.buffer[5];
    
/* 382 */     if (this.type > 19)
/* 383 */       throw new NetException(204);
/* 384 */     if ((this.length > 65535) || (this.length > this.sdu))
/* 385 */       throw new NetException(203);
/* 386 */     if (this.length < 8) {
/* 387 */       throw new NetException(207);
    }
  }
  
  protected void send()
    throws IOException
  {
/* 407 */     synchronized (this.sAtts.ntOutputStream)
    {
/* 411 */       this.sAtts.ntOutputStream.write(this.buffer, 0, this.buffer.length);
    }
  }
  
  protected void extractData()
    throws IOException, NetException
  {
/* 432 */     if (this.dataLen <= 0)
    {
/* 434 */       this.data = new String();
    }
/* 436 */     else if (this.length > this.dataOff)
    {
/* 439 */       this.data = new String(this.buffer, 0, this.dataOff, this.dataLen);
    }
    else
    {
/* 446 */       byte[] arrayOfByte1 = new byte[this.dataLen + 10];
/* 447 */       byte[] arrayOfByte2 = new byte[this.dataLen];
      
/* 449 */       int i = readLocal(arrayOfByte1, 0, this.dataLen + 10);
      
/* 451 */       if (i != 0)
      {
/* 454 */         if (i >= 10)
        {
/* 456 */           i -= 10;
/* 457 */           System.arraycopy(arrayOfByte1, 10, arrayOfByte2, 0, i);
        }
        else
        {
/* 461 */           throw new NetException(207);
        }
      }
      
/* 465 */       if (i < this.dataLen)
      {
/* 473 */         if (this.sAtts.nsInputStream.read(arrayOfByte2, i, this.dataLen - i) < 0)
        {
/* 476 */           throw new NetException(0); }
      }
/* 478 */       this.data = new String(arrayOfByte2, 0);
    }
  }
  
  protected String getData()
  {
/* 490 */     return this.data;
  }
  
  void setFlags(int paramInt)
    throws NetException
  {
/* 496 */     this.flags = paramInt;
/* 497 */     this.buffer[5] = ((byte)this.flags);
  }
  
  void reinitialize(SessionAtts paramSessionAtts)
    throws NetException
  {
/* 503 */     this.sAtts = paramSessionAtts;
  }
  
  public static final int toUb2(byte[] paramArrayOfByte, int paramInt)
  {
/* 508 */     int i = 0;
/* 509 */     i = paramArrayOfByte[paramInt] & 0xFF;
/* 510 */     i <<= 8;
/* 511 */     i |= paramArrayOfByte[(paramInt + 1)] & 0xFF;
    
/* 513 */     return i;
  }
  
  public static final void setUb2ToBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
/* 518 */     paramArrayOfByte[paramInt1] = ((byte)(paramInt2 << 8 & 0xFF));
/* 519 */     paramArrayOfByte[(paramInt1 + 1)] = ((byte)(paramInt2 & 0xFF));
  }
  
  int available()
  {
/* 524 */     return this.leftOverFromPreviousRead;
  }
  
  int readLocal(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws NetException
  {
/* 534 */     int i = 0;
/* 535 */     if (this.leftOverFromPreviousRead > 0)
    {
/* 537 */       if (this.leftOverFromPreviousRead > paramInt2)
      {
/* 542 */         i = paramInt2;
/* 543 */         System.arraycopy(this.buffer, this.nextPacketOffset, paramArrayOfByte, paramInt1, i);
        
/* 546 */         this.leftOverFromPreviousRead -= i;
        
/* 548 */         this.nextPacketOffset += i;
      }
      else
      {
/* 554 */         i = this.leftOverFromPreviousRead;
/* 555 */         System.arraycopy(this.buffer, this.nextPacketOffset, paramArrayOfByte, paramInt1, i);
        
/* 558 */         this.leftOverFromPreviousRead -= i;
/* 559 */         this.nextPacketOffset = 0;
      }
    }
    
/* 563 */     return i;
  }
  
/* 566 */   StringBuilder sb = null;
/* 567 */   StringBuilder tmpBuf = null;
  
  protected String dumpBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
/* 572 */     if (this.sb == null)
    {
/* 574 */       this.sb = new StringBuilder(16384);
/* 575 */       this.tmpBuf = new StringBuilder(80);
    }
/* 577 */     return dumpBytes(paramArrayOfByte, paramInt1, paramInt2, 8, this.sb, this.tmpBuf);
  }
  
  public String dumpBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, StringBuilder paramStringBuilder1, StringBuilder paramStringBuilder2)
  {
/* 592 */     if (paramArrayOfByte == null) {
/* 593 */       return "NULL";
    }
/* 595 */     paramStringBuilder1.delete(0, paramStringBuilder1.length());
/* 596 */     paramStringBuilder2.delete(0, paramStringBuilder2.length());
    
/* 599 */     int j = paramInt1 + paramInt2;
/* 600 */     int k = paramInt1;
    
/* 602 */     int m = 0;
/* 603 */     int n = 0;
/* 604 */     for (; k < j; k++) {
/* 605 */       int i = paramArrayOfByte[k] & 0xFF;
/* 606 */       paramStringBuilder1.append(toHex[i]);
/* 607 */       paramStringBuilder2.append(toChar[i]);
      
/* 609 */       n++;
      
/* 612 */       if (n == paramInt3) {
/* 613 */         paramStringBuilder1.append("     |");
/* 614 */         paramStringBuilder1.append(paramStringBuilder2.substring(0, paramStringBuilder2.length()));
/* 615 */         paramStringBuilder1.append("|\n");
/* 616 */         paramStringBuilder2.delete(0, paramStringBuilder2.length());
/* 617 */         n = 0;
      }
    }
    
/* 622 */     if (n > 0) {
/* 623 */       int i1 = paramInt3 - n - 1;
      
/* 625 */       for (int i2 = 0; i2 <= i1; i2++) {
/* 626 */         paramStringBuilder1.append("   ");
      }
/* 628 */       paramStringBuilder1.append("     |");
/* 629 */       paramStringBuilder1.append(paramStringBuilder2.substring(0, paramStringBuilder2.length()));
/* 630 */       for (i2 = 0; i2 <= i1; i2++)
/* 631 */         paramStringBuilder1.append(" ");
/* 632 */       paramStringBuilder1.append("|\n");
/* 633 */       paramStringBuilder2.delete(0, paramStringBuilder2.length());
    }
    
/* 637 */     return paramStringBuilder1.substring(0, paramStringBuilder1.length());
  }
  
/* 640 */   public static final String[] toHex = { " 00", " 01", " 02", " 03", " 04", " 05", " 06", " 07", " 08", " 09", " 0A", " 0B", " 0C", " 0D", " 0E", " 0F", " 10", " 11", " 12", " 13", " 14", " 15", " 16", " 17", " 18", " 19", " 1A", " 1B", " 1C", " 1D", " 1E", " 1F", " 20", " 21", " 22", " 23", " 24", " 25", " 26", " 27", " 28", " 29", " 2A", " 2B", " 2C", " 2D", " 2E", " 2F", " 30", " 31", " 32", " 33", " 34", " 35", " 36", " 37", " 38", " 39", " 3A", " 3B", " 3C", " 3D", " 3E", " 3F", " 40", " 41", " 42", " 43", " 44", " 45", " 46", " 47", " 48", " 49", " 4A", " 4B", " 4C", " 4D", " 4E", " 4F", " 50", " 51", " 52", " 53", " 54", " 55", " 56", " 57", " 58", " 59", " 5A", " 5B", " 5C", " 5D", " 5E", " 5F", " 60", " 61", " 62", " 63", " 64", " 65", " 66", " 67", " 68", " 69", " 6A", " 6B", " 6C", " 6D", " 6E", " 6F", " 70", " 71", " 72", " 73", " 74", " 75", " 76", " 77", " 78", " 79", " 7A", " 7B", " 7C", " 7D", " 7E", " 7F", " 80", " 81", " 82", " 83", " 84", " 85", " 86", " 87", " 88", " 89", " 8A", " 8B", " 8C", " 8D", " 8E", " 8F", " 90", " 91", " 92", " 93", " 94", " 95", " 96", " 97", " 98", " 99", " 9A", " 9B", " 9C", " 9D", " 9E", " 9F", " A0", " A1", " A2", " A3", " A4", " A5", " A6", " A7", " A8", " A9", " AA", " AB", " AC", " AD", " AE", " AF", " B0", " B1", " B2", " B3", " B4", " B5", " B6", " B7", " B8", " B9", " BA", " BB", " BC", " BD", " BE", " BF", " C0", " C1", " C2", " C3", " C4", " C5", " C6", " C7", " C8", " C9", " CA", " CB", " CC", " CD", " CE", " CF", " D0", " D1", " D2", " D3", " D4", " D5", " D6", " D7", " D8", " D9", " DA", " DB", " DC", " DD", " DE", " DF", " E0", " E1", " E2", " E3", " E4", " E5", " E6", " E7", " E8", " E9", " EA", " EB", " EC", " ED", " EE", " EF", " F0", " F1", " F2", " F3", " F4", " F5", " F6", " F7", " F8", " F9", " FA", " FB", " FC", " FD", " FE", " FF" };
  
/* 666 */   public static final char[] toChar = { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.' };
  
  public static final String DIVIDER = "     |";
  
  public static final String BLANK_SPACE = "   ";
  
  protected void dump(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
/* 695 */     int i = 0;
/* 696 */     System.out.println("Packet dump");
/* 697 */     System.out.println("buffer.length=" + paramArrayOfByte.length);
/* 698 */     System.out.println("offset       =" + paramInt1);
/* 699 */     System.out.println("len          =" + paramInt2);
    
/* 701 */     for (int j = paramInt1; j < paramInt2; j += 8)
    {
/* 703 */       System.out.print("|");
/* 704 */       for (int k = 0; (k < 8) && (i < paramInt2 - 1); k++)
      {
/* 707 */         i = j + k;
/* 708 */         RepConversion.printInHex(paramArrayOfByte[i]);
/* 709 */         System.out.print(" ");
      }
      
/* 712 */       System.out.println("|");
    }
/* 714 */     System.out.println("finish dump");
  }
  
/* 719 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/Packet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */