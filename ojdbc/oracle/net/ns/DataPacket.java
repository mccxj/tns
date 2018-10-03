package oracle.net.ns;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
public class DataPacket
  extends Packet
  implements SQLnetDef
{
  static final boolean DEBUG2 = false;
  protected int pktOffset;
  protected int dataFlags;
/*  60 */   protected boolean isBufferFull = false;
/*  61 */   protected boolean isBufferEmpty = false;
/*  62 */   protected int availableBytesToSend = 0;
/*  63 */   protected int availableBytesToRead = 0;
/*  64 */   protected int sessionIdSize = 0;
  
  public DataPacket(SessionAtts paramSessionAtts, int paramInt)
  {
/*  71 */     super(paramSessionAtts, paramInt, 6, paramSessionAtts.poolEnabled ? 1 : 0);
    
/*  75 */     initialize(paramInt);
  }
  
  public DataPacket(SessionAtts paramSessionAtts)
  {
/*  88 */     this(paramSessionAtts, paramSessionAtts.getSDU());
  }
  
  protected void receive()
    throws IOException, NetException
  {
/* 106 */     super.receive();
    
/* 108 */     this.dataOff = (this.pktOffset = 10);
/* 109 */     this.dataLen = (this.length - this.dataOff - (this.sAtts.poolEnabled ? 16 : 0));
/* 110 */     this.dataFlags = (this.buffer[8] & 0xFF);
/* 111 */     this.dataFlags <<= 8;
/* 112 */     this.dataFlags |= this.buffer[9] & 0xFF;
    
/* 116 */     if ((this.type == 6) && ((this.dataFlags & 0x40) != 0)) {
/* 117 */       this.sAtts.dataEOF = true;
    }
    
/* 120 */     if ((this.type == 6) && (0 == this.dataLen))
    {
/* 122 */       this.type = 7;
    }
    
/* 126 */     if (this.sAtts.poolEnabled) {
/* 127 */       this.sAtts.timestampLastIO = System.currentTimeMillis();
    }
  }
  
  protected void send()
    throws IOException
  {
/* 137 */     send(0);
  }
  
  protected void send(int paramInt)
    throws IOException
  {
/* 147 */     this.buffer[8] = ((byte)(paramInt / 256));
/* 148 */     this.buffer[9] = ((byte)(paramInt % 256));
    
/* 152 */     setBufferLength(this.pktOffset);
    
/* 154 */     synchronized (this.sAtts.ntOutputStream)
    {
/* 158 */       if (this.sAtts.poolEnabled)
      {
/* 161 */         int j = 20;
        int i;
        do
        {
/* 165 */           i = 0;
          
          try
          {
/* 170 */             this.sAtts.ntOutputStream.write(this.buffer, 0, this.pktOffset);
          }
          catch (SocketException localSocketException)
          {
/* 186 */             if (localSocketException.getMessage().startsWith("Connection reset")) { j--; if (j > 0)
              {
/* 189 */                 if (!this.sAtts.attemptingReconnect)
                {
/* 195 */                   this.sAtts.ns.reconnectIfRequired(false);
                }
                
/* 198 */                 i = 1; continue;
              }
            }
            
/* 202 */             throw localSocketException;
          }
          
/* 205 */         } while (i != 0);
      }
      else
      {
/* 210 */         this.sAtts.ntOutputStream.write(this.buffer, 0, this.pktOffset);
      }
    }
    
/* 215 */     this.pktOffset = 10;
/* 216 */     this.availableBytesToSend = 0;
/* 217 */     this.isBufferFull = false;
/* 218 */     if (this.sAtts.poolEnabled) {
/* 219 */       this.sAtts.timestampLastIO = System.currentTimeMillis();
    }
  }
  
  protected void putDataInBuffer(byte paramByte)
    throws IOException
  {
/* 230 */     this.buffer[(this.pktOffset++)] = paramByte;
/* 231 */     this.isBufferFull = (this.pktOffset == this.buffer.length - this.sessionIdSize);
/* 232 */     this.availableBytesToSend += 1;
  }
  
  protected int putDataInBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
/* 242 */     int i = this.buffer.length - this.sessionIdSize - this.pktOffset <= paramInt2 ? this.buffer.length - this.sessionIdSize - this.pktOffset : paramInt2;
    
/* 261 */     if (i > 0)
    {
/* 263 */       System.arraycopy(paramArrayOfByte, paramInt1, this.buffer, this.pktOffset, i);
/* 264 */       this.pktOffset += i;
      
/* 266 */       this.isBufferFull = (this.pktOffset == this.buffer.length - this.sessionIdSize);
      
/* 268 */       this.availableBytesToSend = (this.dataOff < this.pktOffset ? this.pktOffset - this.dataOff : 0);
    }
    
/* 283 */     return i;
  }
  
  public long readLongLSB(int paramInt)
    throws BreakNetException, NetException, IOException
  {
/* 291 */     long l = 0L;
    
/* 293 */     int i = paramInt; for (int j = 0; i > 0; this.availableBytesToRead -= 1) {
/* 294 */       l |= (this.buffer[this.pktOffset] & 0xFF) << 8 * j;i--;j++;this.pktOffset += 1;
    }
/* 296 */     return l;
  }
  
  public long readLongMSB(int paramInt)
    throws BreakNetException, NetException, IOException
  {
/* 303 */     long l = 0L;
    
/* 305 */     for (int i = paramInt; i > 0; this.availableBytesToRead -= 1) {
/* 306 */       l |= (this.buffer[this.pktOffset] & 0xFF) << 8 * (i - 1);i--;this.pktOffset += 1;
    }
/* 308 */     return l;
  }
  
  protected int getDataFromBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws NetException
  {
/* 318 */     int i = this.availableBytesToRead <= paramInt2 ? this.availableBytesToRead : paramInt2;
    
/* 335 */     if (i > 0)
    {
/* 337 */       System.arraycopy(this.buffer, this.pktOffset, paramArrayOfByte, paramInt1, i);
/* 338 */       this.pktOffset += i;
      
/* 340 */       this.isBufferEmpty = (this.pktOffset == this.length);
      
/* 342 */       this.availableBytesToRead -= i;
    }
    
/* 356 */     return i;
  }
  
  protected byte get1ByteDataFromBuffer()
    throws NetException
  {
/* 370 */     byte b = this.buffer[this.pktOffset];
/* 371 */     this.pktOffset += 1;
/* 372 */     if (this.pktOffset >= this.length) {
/* 373 */       this.isBufferEmpty = true;
    } else
/* 375 */       this.isBufferEmpty = false;
/* 376 */     this.availableBytesToRead -= 1;
/* 377 */     return b;
  }
  
  protected void setBufferLength(int paramInt)
    throws NetException
  {
/* 471 */     if (this.sAtts.poolEnabled)
    {
/* 479 */       System.arraycopy(this.sAtts.sessionId, 0, this.buffer, this.pktOffset, 16);
      
/* 481 */       paramInt += 16;
/* 482 */       this.pktOffset += 16;
    }
    
/* 487 */     this.buffer[0] = ((byte)(paramInt / 256));
/* 488 */     this.buffer[1] = ((byte)(paramInt % 256));
  }
  
  protected void initialize(int paramInt)
  {
/* 494 */     this.dataOff = (this.pktOffset = 10);
/* 495 */     this.dataLen = (paramInt - this.dataOff);
/* 496 */     this.dataFlags = 0;
/* 497 */     this.sessionIdSize = (this.sAtts.poolEnabled ? 16 : 0);
  }
  
/* 501 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/DataPacket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */