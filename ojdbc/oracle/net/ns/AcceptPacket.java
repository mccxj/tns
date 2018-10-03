package oracle.net.ns;
import java.io.IOException;
public class AcceptPacket
  extends Packet
  implements SQLnetDef
{
  protected int version;
  protected int options;
  protected int sduSize;
  protected int tduSize;
  protected int myHWByteOrder;
  protected int flag0;
  protected int flag1;
  
  public AcceptPacket(Packet paramPacket)
    throws IOException, NetException
  {
/*  72 */     super(paramPacket);
    
/*  75 */     this.version = (this.buffer[8] & 0xFF);
/*  76 */     this.version <<= 8;
/*  77 */     this.version |= this.buffer[9] & 0xFF;
    
/*  79 */     this.options = (this.buffer[10] & 0xFF);
/*  80 */     this.options <<= 8;
/*  81 */     this.options |= this.buffer[11] & 0xFF;
    
/*  83 */     this.sduSize = (this.buffer[12] & 0xFF);
/*  84 */     this.sduSize <<= 8;
/*  85 */     this.sduSize |= this.buffer[13] & 0xFF;
    
/*  87 */     this.tduSize = (this.buffer[14] & 0xFF);
/*  88 */     this.tduSize <<= 8;
/*  89 */     this.tduSize |= this.buffer[15] & 0xFF;
    
/*  91 */     this.myHWByteOrder = (this.buffer[16] & 0xFF);
/*  92 */     this.myHWByteOrder <<= 8;
/*  93 */     this.myHWByteOrder |= this.buffer[17] & 0xFF;
    
/*  95 */     this.dataLen = (this.buffer[18] & 0xFF);
/*  96 */     this.dataLen <<= 8;
/*  97 */     this.dataLen |= this.buffer[19] & 0xFF;
    
/*  99 */     this.dataOff = (this.buffer[20] & 0xFF);
/* 100 */     this.dataOff <<= 8;
/* 101 */     this.dataOff |= this.buffer[21] & 0xFF;
    
/* 103 */     this.flag0 = this.buffer[22];
/* 104 */     this.flag1 = this.buffer[23];
/* 105 */     this.sAtts.noAnoServices = ((this.flag1 & 0x8) == 8);
/* 106 */     if (!this.sAtts.noAnoServices) {
/* 107 */       this.sAtts.noAnoServices = ((this.flag0 & 0x4) == 4);
    }
/* 109 */     this.sAtts.timeout = toUb2(this.buffer, 24);
    
/* 111 */     this.sAtts.tick = toUb2(this.buffer, 26);
/* 112 */     this.sAtts.timeout -= this.sAtts.tick;
/* 113 */     this.sAtts.timeout *= 10;
    
/* 115 */     int i = toUb2(this.buffer, 28);
/* 116 */     int j = toUb2(this.buffer, 30);
/* 117 */     if (i > 0)
    {
/* 119 */       this.sAtts.reconnectAddress = new byte[i];
/* 120 */       System.arraycopy(this.buffer, j, this.sAtts.reconnectAddress, 0, i);
    }
    
/* 124 */     if (this.sAtts.timeout > 0)
    {
/* 127 */       int k = this.dataLen + this.dataOff;
/* 128 */       this.sAtts.poolEnabled = true;
/* 129 */       this.sAtts.sessionId = new byte[16];
/* 130 */       System.arraycopy(this.buffer, k, this.sAtts.sessionId, 0, 16);
/* 131 */       this.sAtts.nsInputStream.poolEnabled(true);
/* 132 */       this.sAtts.nsOutputStream.poolEnabled(true);
/* 133 */       this.sAtts.timestampLastIO = System.currentTimeMillis();
    }
    
/* 136 */     extractData();
    
/* 140 */     this.sAtts.setSDU(this.sduSize);
/* 141 */     this.sAtts.setTDU(this.tduSize);
    
/* 143 */     if (this.tduSize < this.sduSize) {
/* 144 */       this.sAtts.setSDU(this.tduSize);
    }
/* 146 */     this.sAtts.setNegotiatedOptions(this.options);
  }
  
  public String toString()
  {
/* 153 */     return "version=" + this.version + ", options=" + this.options + ", sduSize=" + this.sduSize + ", tduSize=" + this.tduSize + "\nmyHWByteOrder=" + this.myHWByteOrder + ", dataLen=" + this.dataLen + ", flag0=" + this.flag0 + ", flag1=" + this.flag1 + "\n" + (this.sAtts.poolEnabled ? "timeout=" + this.sAtts.timeout + ",re-connect: " + this.sAtts.reconnectAddress + "\n(byte)Dumping session-id:\n" + dumpBytes(this.sAtts.sessionId, 0, 16) : "") + "\n\n";
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/AcceptPacket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */