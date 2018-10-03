package oracle.net.ns;
import java.io.IOException;
import oracle.net.nt.ConnOption;
import oracle.net.nt.NTAdapter;
public class ConnectPacket
  extends Packet
  implements SQLnetDef
{
  private boolean connDataOflow;
  private byte[] connBytes;
  
  public ConnectPacket(SessionAtts paramSessionAtts, boolean paramBoolean1, boolean paramBoolean2)
    throws IOException
  {
/*  73 */     super(paramSessionAtts);
    
/*  76 */     this.data = paramSessionAtts.cOption.conn_data.toString();
    
/*  78 */     if (this.data != null)
    {
/*  80 */       this.connBytes = this.data.getBytes();
/*  81 */       this.dataLen = this.connBytes.length;
    }
    else
    {
/*  85 */       this.connBytes = null;
/*  86 */       this.dataLen = 0;
    }
    
/*  90 */     this.connDataOflow = (this.dataLen > 230);
    
/* 112 */     int i = !this.connDataOflow ? 58 + this.dataLen : 58;
    
/* 114 */     createBuffer(i, 1, 0);
    
/* 117 */     this.buffer[8] = 1;
    
/* 120 */     this.buffer[9] = 54;
/* 121 */     this.buffer[10] = 1;
/* 122 */     this.buffer[11] = 44;
    
/* 124 */     int j = 1;
    
/* 126 */     if ((paramBoolean1) && (paramSessionAtts.nt.isCharacteristicUrgentSupported()))
    {
/* 128 */       j |= 0xC00;
    }
    
/* 141 */     if (paramBoolean2) {
/* 142 */       j |= 0x40;
    }
/* 144 */     this.buffer[12] = ((byte)(j >> 8 & 0xFF));
/* 145 */     this.buffer[13] = ((byte)(j & 0xFF));
/* 146 */     this.buffer[14] = ((byte)(this.sdu / 256));
/* 147 */     this.buffer[15] = ((byte)(this.sdu % 256));
/* 148 */     this.buffer[16] = ((byte)(this.tdu / 256));
/* 149 */     this.buffer[17] = ((byte)(this.tdu % 256));
    
/* 152 */     this.buffer[18] = 79;
/* 153 */     this.buffer[19] = -104;
    
/* 155 */     this.buffer[22] = 0;
/* 156 */     this.buffer[23] = 1;
/* 157 */     this.buffer[24] = ((byte)(this.dataLen / 256));
/* 158 */     this.buffer[25] = ((byte)(this.dataLen % 256));
/* 159 */     this.buffer[27] = 58;
    
/* 161 */     if (!paramSessionAtts.anoEnabled)
    {
/* 163 */       this.buffer[32] = (this.buffer[33] = 4);
    }
    else
    {
/* 168 */       this.buffer[32] = (this.buffer[33] = (byte)paramSessionAtts.getANOFlags());
    }
    
/* 174 */     byte[] tmp401_396 = this.buffer;tmp401_396[32] = ((byte)(tmp401_396[32] | 0x80)); byte[] 
/* 175 */       tmp415_410 = this.buffer;tmp415_410[33] = ((byte)(tmp415_410[33] | 0x80));
    
/* 179 */     setUb2ToBytes(this.buffer, 50, 0);
/* 180 */     setUb2ToBytes(this.buffer, 52, 0);
/* 181 */     setUb2ToBytes(this.buffer, 54, 0);
/* 182 */     setUb2ToBytes(this.buffer, 56, 0);
    
/* 185 */     if ((!this.connDataOflow) && (this.connBytes != null) && (this.connBytes.length > 0))
    {
/* 187 */       System.arraycopy(this.connBytes, 0, this.buffer, 58, this.connBytes.length);
    }
  }
  
  protected void send()
    throws IOException
  {
/* 216 */     super.send();
    
/* 222 */     if (this.connDataOflow)
    {
/* 224 */       byte[] arrayOfByte = new byte[this.connBytes.length];
      
/* 233 */       System.arraycopy(this.connBytes, 0, arrayOfByte, 0, this.connBytes.length);
      
/* 235 */       this.sAtts.nsOutputStream.write(arrayOfByte);
/* 236 */       this.sAtts.nsOutputStream.flush();
    }
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/ConnectPacket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */