package oracle.net.ns;
import java.io.IOException;
import java.io.OutputStream;
public class NetOutputStream
  extends OutputStream
  implements SQLnetDef
{
  protected DataPacket daPkt;
  protected DataDescriptorPacket ddPkt;
  protected SessionAtts sAtts;
/*  64 */   private byte[] tmpBuf = new byte[1];
  
  public NetOutputStream() {}
  
  public NetOutputStream(SessionAtts paramSessionAtts)
  {
/*  75 */     this.sAtts = paramSessionAtts;
/*  76 */     this.daPkt = new DataPacket(paramSessionAtts);
/*  77 */     this.ddPkt = new DataDescriptorPacket(paramSessionAtts);
  }
  
  public NetOutputStream(SessionAtts paramSessionAtts, int paramInt)
  {
/*  85 */     this.sAtts = paramSessionAtts;
/*  86 */     this.daPkt = new DataPacket(paramSessionAtts, paramInt);
/*  87 */     this.ddPkt = new DataDescriptorPacket(paramSessionAtts);
  }
  
  public void writeZeroCopyIO(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, NetException, BreakNetException
  {
/*  93 */     int i = paramInt2;
    
/*  95 */     boolean bool = false;
/*  96 */     while (i > 0) {
      int j;
/*  98 */       if (i > 1703910) {
/*  99 */         j = 1703910;
      }
      else {
/* 102 */         j = i;
/* 103 */         bool = true;
      }
/* 105 */       this.ddPkt.send(j, bool);
/* 106 */       synchronized (this.sAtts.ntOutputStream)
      {
/* 109 */         this.sAtts.ntOutputStream.write(paramArrayOfByte, paramInt1, j);
      }
/* 111 */       paramInt1 += j;
/* 112 */       i -= j;
    }
  }
  
  public void write(int paramInt)
    throws IOException
  {
/* 124 */     if ((this.sAtts.enableJavaNetFastPath) && (!this.sAtts.anoActive))
    {
/* 128 */       if (this.daPkt.isBufferFull)
      {
/* 131 */         this.daPkt.send(32);
      }
/* 133 */       this.daPkt.putDataInBuffer((byte)paramInt);
    }
    else
    {
/* 137 */       this.tmpBuf[0] = ((byte)paramInt);
/* 138 */       write(this.tmpBuf);
    }
  }
  
  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
/* 151 */     write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
/* 166 */     int i = 0;
/* 167 */     int j = 0;
    
/* 182 */     while (paramInt2 > i)
    {
/* 185 */       i += this.daPkt.putDataInBuffer(paramArrayOfByte, paramInt1 + i, paramInt2 - i);
      
/* 190 */       if (this.daPkt.isBufferFull)
      {
/* 192 */         j = paramInt2 > i ? 32 : 0;
        
/* 195 */         this.daPkt.send(j);
      }
    }
  }
  
  public int available()
    throws IOException
  {
/* 220 */     return this.daPkt.availableBytesToSend;
  }
  
  public void flush()
    throws IOException
  {
/* 229 */     if (this.daPkt.availableBytesToSend > 0) {
/* 230 */       this.daPkt.send(0);
    }
  }
  
  public void close()
    throws IOException
  {
/* 248 */     this.daPkt.send(64);
  }
  
  void poolEnabled(boolean paramBoolean)
    throws IOException, NetException, BreakNetException
  {
/* 262 */     if (paramBoolean) {
/* 263 */       this.daPkt.setFlags(1);
    }
  }
  
  void reinitialize(SessionAtts paramSessionAtts) throws NetException
  {
/* 269 */     this.sAtts = paramSessionAtts;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/NetOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */