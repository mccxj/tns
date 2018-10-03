package oracle.net.nt;
import java.io.IOException;
import java.net.SocketException;
import java.util.Properties;
import oracle.net.jdbc.nl.NLException;
import oracle.net.jdbc.nl.NVFactory;
import oracle.net.jdbc.nl.NVNavigator;
import oracle.net.jdbc.nl.NVPair;
import oracle.net.ns.NetException;
public class ConnOption
{
  public NTAdapter nt;
  public int port;
  public int tdu;
  public int sdu;
  public String protocol;
  public String host;
  public String sid;
  public String addr;
  public String service_name;
  public String instance_name;
/*  67 */   public StringBuffer conn_data = new StringBuffer();
  
  public String sslServerCertDN;
  
  public String origSSLServerCertDN;
  
  public String origServiceName;
  
  public String origSid;
  
  public boolean done;
  
  private NTAdapter getNT(Properties paramProperties)
    throws NetException
  {
    try
    {
/*  84 */       if (this.protocol.equalsIgnoreCase("tcp")) {
/*  85 */         this.nt = new TcpNTAdapter(this.addr, paramProperties);
/*  86 */         this.origServiceName = this.service_name;
/*  87 */         this.origSid = this.sid;
      }
/*  89 */       else if (this.protocol.equalsIgnoreCase("tcps")) {
/*  90 */         this.nt = new TcpsNTAdapter(this.addr, paramProperties);
/*  91 */         this.origSSLServerCertDN = this.sslServerCertDN;
/*  92 */         this.origServiceName = this.service_name;
/*  93 */         this.origSid = this.sid;
/*  94 */         String[] arrayOfString = { this.origSSLServerCertDN, this.origServiceName, this.origSid };
/*  95 */         this.nt.setOption(8, arrayOfString);
      }
/*  98 */       else if (this.protocol.equalsIgnoreCase("sdp")) {
/*  99 */         this.nt = new SdpNTAdapter(this.addr, paramProperties);
/* 100 */         this.origServiceName = this.service_name;
/* 101 */         this.origSid = this.sid;
      }
      else {
/* 104 */         throw new NetException(21);
      }
    }
    catch (NLException localNLException) {
/* 108 */       throw new NetException(501);
    } catch (Exception localException) {
/* 110 */       throw new NetException(21);
    }
/* 112 */     return this.nt;
  }
  
  public void connect(Properties paramProperties)
    throws IOException
  {
    try
    {
/* 125 */       populateProtocol();
/* 126 */       if (this.protocol == null)
/* 127 */         throw new NetException(501);
    } catch (NLException localNLException) {
/* 129 */       throw new NetException(501);
    }
    
/* 132 */     this.nt = getNT(paramProperties);
/* 133 */     this.nt.connect();
  }
  
  private void populateProtocol()
    throws NLException
  {
/* 150 */     NVPair localNVPair1 = null;
/* 151 */     NVNavigator localNVNavigator = new NVNavigator();
    
/* 153 */     NVPair localNVPair2 = new NVFactory().createNVPair(this.addr);
/* 154 */     localNVPair1 = localNVNavigator.findNVPair(localNVPair2, "PROTOCOL");
/* 155 */     if (localNVPair1 != null) {
/* 156 */       this.protocol = localNVPair1.getAtom();
    } else {
/* 158 */       throw new NLException("NoNVPair-04614", "PROTOCOL");
    }
  }
  
  public void restoreFromOrigCoption(ConnOption paramConnOption)
    throws IOException
  {
/* 174 */     this.origSSLServerCertDN = paramConnOption.origSSLServerCertDN;
/* 175 */     this.origServiceName = paramConnOption.origServiceName;
/* 176 */     this.origSid = paramConnOption.origSid;
/* 177 */     this.conn_data = paramConnOption.conn_data;
    
/* 179 */     if (this.protocol.equalsIgnoreCase("tcps")) {
/* 180 */       String[] arrayOfString = { this.origSSLServerCertDN, this.origServiceName, this.origSid };
/* 181 */       this.nt.setOption(8, arrayOfString);
    }
  }
  
  public String toString()
  {
/* 188 */     return "host=" + this.host + ", port=" + this.port + ", sid=" + this.sid + ", protocol=" + this.protocol + ", service_name=" + this.service_name + "\naddr=" + this.addr + "\nconn_data=" + this.conn_data + "\nsslServerCertDN=" + this.sslServerCertDN + ", origSSLServerCertDN=" + this.origSSLServerCertDN + ", origServiceName=" + this.origServiceName + ", origSid=" + this.origSid + ", done=" + this.done;
  }
  
  boolean isConnectionSocketKeepAlive()
    throws SocketException
  {
/* 210 */     return this.nt.isConnectionSocketKeepAlive();
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/nt/ConnOption.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */