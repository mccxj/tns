package oracle.net.ns;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
public class NetInputStream
  extends InputStream
  implements SQLnetDef
{
  protected DataPacket daPkt;
  protected DataDescriptorPacket ddPkt;
  protected MarkerPacket mkPkt;
  protected SessionAtts sAtts;
/*  68 */   private byte[] tmpBuf = new byte[1];
  
  public NetInputStream() {}
  
  public NetInputStream(SessionAtts paramSessionAtts)
  {
/*  79 */     this.sAtts = paramSessionAtts;
/*  80 */     this.daPkt = new DataPacket(paramSessionAtts);
/*  81 */     this.ddPkt = new DataDescriptorPacket(this.daPkt, paramSessionAtts);
  }
  
  public int read()
    throws IOException, NetException, BreakNetException
  {
/*  93 */     if ((this.sAtts.enableJavaNetFastPath) && (!this.sAtts.anoActive))
    {
/*  97 */       while ((this.daPkt == null) || (this.daPkt.availableBytesToRead <= 0) || (this.daPkt.type == 7))
      {
/*  99 */         getNextPacket();
      }
      
/* 103 */       return this.daPkt.get1ByteDataFromBuffer() & 0xFF;
    }
    
/* 107 */     return read(this.tmpBuf) < 0 ? -1 : this.tmpBuf[0] & 0xFF;
  }
  
  public int readB1()
    throws IOException, NetException, BreakNetException
  {
/* 117 */     return read();
  }
  
  public long readLongLSB(int paramInt)
    throws BreakNetException, NetException, IOException
  {
/* 125 */     long l = 0L;
/* 126 */     int i = 0;
    
/* 128 */     if ((paramInt & 0x80) > 0)
    {
/* 130 */       paramInt &= 0x7F;
/* 131 */       i = 1;
    }
/* 133 */     if ((this.sAtts.anoActive) || (this.daPkt.availableBytesToRead < paramInt))
    {
/* 135 */       int j = paramInt; for (int k = 0; j > 0; k++)
      {
/* 137 */         l |= (read() & 0xFF) << 8 * k;j--;
      }
    }
    else
    {
/* 142 */       l = this.daPkt.readLongLSB(paramInt);
    }
    
/* 145 */     return (i != 0 ? -1 : 1) * l;
  }
  
  public long readLongMSB(int paramInt)
    throws BreakNetException, NetException, IOException
  {
/* 153 */     long l = 0L;
/* 154 */     int i = 0;
    
/* 157 */     if ((paramInt & 0x80) > 0)
    {
/* 159 */       paramInt &= 0x7F;
/* 160 */       i = 1;
    }
    
/* 163 */     if ((this.sAtts.anoActive) || (this.daPkt.availableBytesToRead < paramInt))
    {
/* 165 */       for (int j = paramInt; j > 0; j--)
      {
/* 167 */         l |= (read() & 0xFF) << 8 * (j - 1);
      }
      
    }
    else {
/* 172 */       l = this.daPkt.readLongMSB(paramInt);
    }
    
/* 175 */     return (i != 0 ? -1 : 1) * l;
  }
  
  public int read(byte[] paramArrayOfByte)
    throws IOException, NetException, BreakNetException
  {
/* 190 */     return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public boolean readZeroCopyIO(byte[] paramArrayOfByte, int paramInt, int[] paramArrayOfInt)
    throws IOException, NetException, BreakNetException
  {
/* 204 */     boolean bool = false;
    
/* 206 */     if (this.sAtts.nsOutputStream.available() > 0) {
/* 207 */       this.sAtts.nsOutputStream.flush();
    }
    
/* 210 */     this.ddPkt.receive();
/* 211 */     int i = this.ddPkt.totalDataLength;
/* 212 */     if ((this.ddPkt.descriptorFLaG & 0x1) != 0) {
/* 213 */       bool = true;
    }
    
/* 216 */     if (paramArrayOfByte.length < paramInt + i)
    {
/* 219 */       throw new IOException("Assertion Failed");
    }
    
/* 222 */     int j = this.ddPkt.packet.readLocal(paramArrayOfByte, paramInt, i);
    
/* 225 */     while (j < i)
    {
      try
      {
/* 229 */         if (j += this.sAtts.ntInputStream.read(paramArrayOfByte, j + paramInt, i - j) <= 0)
        {
/* 232 */           throw new NetException(0);
        }
      }
      catch (InterruptedIOException localInterruptedIOException) {
/* 236 */         throw new NetException(504);
      }
    }
/* 239 */     paramArrayOfInt[0] = j;
/* 240 */     return bool;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, NetException, BreakNetException
  {
/* 257 */     int i = 0;
    
    try
    {
      do
      {
/* 266 */         while ((this.daPkt == null) || (this.daPkt.availableBytesToRead <= 0) || (this.daPkt.type == 7))
        {
/* 268 */           getNextPacket();
        }
        
/* 271 */         i += this.daPkt.getDataFromBuffer(paramArrayOfByte, paramInt1 + i, paramInt2 - i);
      }
/* 273 */       while (i < paramInt2);
    }
    catch (NetException localNetException) {
/* 276 */       if (localNetException.getErrorNumber() == 0) {
/* 277 */         return -1;
      }
/* 279 */       throw localNetException;
    }
/* 281 */     return i;
  }
  
  public int available()
    throws IOException
  {
/* 293 */     return this.daPkt.availableBytesToRead;
  }
  
  protected void getNextPacket()
    throws IOException, NetException, BreakNetException
  {
/* 316 */     if (this.sAtts.dataEOF) {
/* 317 */       throw new NetException(202);
    }
    
/* 320 */     if (this.sAtts.nsOutputStream.available() > 0) {
/* 321 */       this.sAtts.nsOutputStream.flush();
    }
    
/* 324 */     this.daPkt.receive();
    
/* 328 */     switch (this.daPkt.type)
    {
    case 6: 
/* 331 */       this.daPkt.availableBytesToRead = this.daPkt.dataLen;
/* 332 */       break;
    case 12: 
/* 334 */       this.mkPkt = new MarkerPacket(this.daPkt);
      
/* 337 */       this.daPkt.availableBytesToRead = 0;
/* 338 */       this.sAtts.onBreakReset = this.mkPkt.isBreakPkt();
/* 339 */       processMarker();
/* 340 */       throw new BreakNetException(500);
    case 7: 
/* 342 */       this.daPkt.availableBytesToRead = this.daPkt.dataLen;
/* 343 */       break;
    
    default: 
/* 346 */       throw new NetException(205);
    }
    
  }
  
  protected void processMarker()
    throws IOException, NetException, BreakNetException
  {}
  
  void poolEnabled(boolean paramBoolean)
    throws IOException, NetException, BreakNetException
  {
/* 379 */     if (paramBoolean) {
/* 380 */       this.daPkt.setFlags(1);
    }
  }
  
  void reinitialize(SessionAtts paramSessionAtts) throws NetException
  {
/* 386 */     this.sAtts = paramSessionAtts;
  }
  
  Packet getCurrentPacket()
    throws IOException, NetException
  {
/* 396 */     return this.daPkt;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/NetInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */