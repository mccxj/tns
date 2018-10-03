package oracle.net.ns;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import oracle.net.ano.Ano;
import oracle.net.nt.ConnOption;
import oracle.net.nt.NTAdapter;
import oracle.net.nt.TcpsNTAdapter;
public class SessionAtts
  implements SQLnetDef
{
  private int sdu;
  private int tdu;
  protected NSProtocol ns;
  protected NTAdapter nt;
  protected InputStream ntInputStream;
  protected OutputStream ntOutputStream;
  protected NetInputStream nsInputStream;
  protected NetOutputStream nsOutputStream;
  protected ConnOption cOption;
  protected boolean dataEOF;
  protected boolean connected;
  public boolean onBreakReset;
  public ClientProfile profile;
  public Ano ano;
  public boolean anoEnabled;
  public boolean isEncryptionActive;
  public boolean isChecksumActive;
  public boolean areEncryptionAndChecksumActive;
  boolean noAnoServices;
  int negotiatedOptions;
/*  86 */   public boolean poolEnabled = false;
  
  protected byte[] sessionId;
  
  protected int timeout;
  
  protected int tick;
  
  protected byte[] reconnectAddress;
  
  protected long timestampLastIO;
  
/* 100 */   protected boolean attemptingReconnect = false;
  
/* 103 */   boolean enableJavaNetFastPath = false;
  
  public boolean anoActive;
  
  String traceId;
  
  public SessionAtts(NSProtocol paramNSProtocol, int paramInt1, int paramInt2)
  {
/* 113 */     this.sdu = paramInt1;
/* 114 */     this.tdu = paramInt2;
/* 115 */     this.ns = paramNSProtocol;
/* 116 */     this.anoActive = false;
  }
  
  public void setSDU(int paramInt)
  {
/* 124 */     if (paramInt <= 0) {
/* 125 */       this.sdu = 8192;
/* 126 */     } else if (paramInt > 65535) {
/* 127 */       this.sdu = 65535;
/* 128 */     } else if (paramInt < 512) {
/* 129 */       this.sdu = 512;
    } else {
/* 131 */       this.sdu = paramInt;
    }
  }
  
  public int getSDU()
  {
/* 139 */     return this.sdu;
  }
  
  public void setTDU(int paramInt)
  {
/* 147 */     if (paramInt <= 0) {
/* 148 */       this.tdu = 32767;
/* 149 */     } else if (paramInt > 65535) {
/* 150 */       this.tdu = 65535;
/* 151 */     } else if (paramInt < 255) {
/* 152 */       this.tdu = 255;
    } else {
/* 154 */       this.tdu = paramInt;
    }
  }
  
  public int getTDU()
  {
/* 162 */     return this.tdu;
  }
  
  public NTAdapter getNTAdapter()
  {
/* 170 */     return this.nt;
  }
  
  void renegotiateSSLSession()
    throws IOException
  {
/* 180 */     ((TcpsNTAdapter)this.nt).renegotiateSession();
/* 181 */     this.ntInputStream = this.nt.getInputStream();
/* 182 */     this.ntOutputStream = this.nt.getOutputStream();
  }
  
  public String toString()
  {
/* 192 */     return "Session Attributes: \nsdu=" + this.sdu + ", tdu=" + this.tdu + "\nnt: " + this.nt + "\n\nntInputStream : " + this.ntInputStream + "\nntOutputStream: " + this.ntOutputStream + "\nnsInputStream : " + this.nsInputStream + "\nnsOutputStream: " + this.nsOutputStream + "\n\nClient Profile: " + this.profile + "\n\nConnection Options: " + this.cOption + "\n\nonBreakReset=" + this.onBreakReset + ", dataEOF=" + this.dataEOF + ", negotiatedOptions=0x" + Integer.toHexString(this.negotiatedOptions) + ", connected=" + this.connected;
  }
  
  public void turnEncryptionOn(NetInputStream paramNetInputStream, NetOutputStream paramNetOutputStream)
    throws NetException
  {
/* 216 */     if ((paramNetInputStream != null) && (paramNetOutputStream != null))
    {
/* 218 */       this.nsInputStream = paramNetInputStream;
/* 219 */       this.nsOutputStream = paramNetOutputStream;
/* 220 */       this.anoActive = true;
    }
    else {
/* 223 */       throw new NetException(300);
    }
  }
  
  public int getANOFlags()
  {
/* 230 */     int i = 1;
    
/* 232 */     if (this.ano != null) {
/* 233 */       i = this.ano.getNAFlags();
    }
/* 235 */     return i;
  }
  
  public OutputStream getOutputStream()
  {
/* 243 */     return this.nsOutputStream;
  }
  
  public InputStream getInputStream()
  {
/* 251 */     return this.nsInputStream;
  }
  
  public void setNegotiatedOptions(int paramInt)
  {
/* 277 */     this.negotiatedOptions = paramInt;
  }
  
  public int getNegotiatedOptions() {
/* 281 */     return this.negotiatedOptions;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/SessionAtts.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */