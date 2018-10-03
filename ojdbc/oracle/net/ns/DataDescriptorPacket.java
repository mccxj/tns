package oracle.net.ns;
import java.io.IOException;
import java.io.OutputStream;
public class DataDescriptorPacket
  extends Packet
  implements SQLnetDef
{
  int totalDataLength;
  int descriptorFLaG;
/*  30 */   int[] sdd = new int[26];
  
  Packet packet;
  
/*  35 */   boolean useLongDescriptor = false;
  
/*  37 */   private static final byte[] STANDARD_SDD_MAX_DD = { 0, 72, 0, 0, 15, 0, 0, 0, 0, 0, 0, 2, 0, 25, -1, -26, 0, 0, 0, 26, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
  
  public DataDescriptorPacket(SessionAtts paramSessionAtts)
  {
/*  53 */     super(paramSessionAtts, 72, 15, 0);
  }
  
  public DataDescriptorPacket(Packet paramPacket, SessionAtts paramSessionAtts)
  {
/*  58 */     super(paramSessionAtts);
/*  59 */     this.packet = paramPacket;
/*  60 */     this.buffer = this.packet.buffer;
  }
  
  protected void receive()
    throws IOException, NetException
  {
/*  67 */     this.packet.receive();
/*  68 */     this.descriptorFLaG = ((this.buffer[8] & 0xFF) << 24 | (this.buffer[9] & 0xFF) << 16 | (this.buffer[10] & 0xFF) << 8 | this.buffer[11] & 0xFF);
    
/*  72 */     if ((this.descriptorFLaG & 0x2) != 0) {
/*  73 */       this.useLongDescriptor = false;
    } else {
/*  75 */       this.useLongDescriptor = true;
    }
    
/*  78 */     this.totalDataLength = ((this.buffer[12] & 0xFF) << 24 | (this.buffer[13] & 0xFF) << 16 | (this.buffer[14] & 0xFF) << 8 | this.buffer[15] & 0xFF);
  }
  
  protected void send(int paramInt, boolean paramBoolean)
    throws IOException
  {
/* 109 */     if ((paramInt == 1703910) && (!paramBoolean))
    {
/* 113 */       synchronized (this.sAtts.ntOutputStream)
      {
/* 117 */         this.sAtts.ntOutputStream.write(STANDARD_SDD_MAX_DD, 0, STANDARD_SDD_MAX_DD.length);
      }
      
    }
    else
    {
/* 123 */       this.useLongDescriptor = false;
/* 124 */       this.descriptorFLaG = 2;
/* 125 */       if (paramBoolean) {
/* 126 */         this.descriptorFLaG |= 0x1;
      }
/* 128 */       int i = 0;
/* 129 */       int j = paramInt;
/* 130 */       while (j > 0)
      {
/* 132 */         if (j > 65535) {
/* 133 */           this.sdd[i] = 65535;
        } else
/* 135 */           this.sdd[i] = j;
/* 136 */         j -= this.sdd[i];
/* 137 */         i++;
      }
      
/* 140 */       writeB4ToBuffer(this.buffer, 8, this.descriptorFLaG);
/* 141 */       writeB4ToBuffer(this.buffer, 12, paramInt);
/* 142 */       writeB4ToBuffer(this.buffer, 16, i);
/* 143 */       for (int k = 0; k < i; k++)
/* 144 */         writeB2ToBuffer(this.buffer, 20 + k * 2, this.sdd[k]);
/* 145 */       for (k = i; k < 26; k++) {
/* 146 */         writeB2ToBuffer(this.buffer, 20 + k * 2, 0);
      }
      
/* 149 */       synchronized (this.sAtts.ntOutputStream)
      {
/* 153 */         this.sAtts.ntOutputStream.write(this.buffer, 0, 72);
      }
    }
  }
  
  void writeB4ToBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
/* 161 */     int i = (byte)((paramInt2 & 0xFF000000) >>> 24 & 0xFF);
/* 162 */     int j = (byte)((paramInt2 & 0xFF0000) >>> 16 & 0xFF);
/* 163 */     int k = (byte)((paramInt2 & 0xFF00) >>> 8 & 0xFF);
/* 164 */     int m = (byte)(paramInt2 & 0xFF);
/* 165 */     paramArrayOfByte[paramInt1] = i;
/* 166 */     paramArrayOfByte[(paramInt1 + 1)] = j;
/* 167 */     paramArrayOfByte[(paramInt1 + 2)] = k;
/* 168 */     paramArrayOfByte[(paramInt1 + 3)] = m;
  }
  
  void writeB2ToBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
/* 172 */     int i = (byte)((paramInt2 & 0xFF00) >>> 8 & 0xFF);
/* 173 */     int j = (byte)(paramInt2 & 0xFF);
/* 174 */     paramArrayOfByte[paramInt1] = i;
/* 175 */     paramArrayOfByte[(paramInt1 + 1)] = j;
  }
  
/* 179 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/DataDescriptorPacket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */