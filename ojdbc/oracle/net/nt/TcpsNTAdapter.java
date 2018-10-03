package oracle.net.nt;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.Properties;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import oracle.net.jdbc.nl.NLException;
import oracle.net.ns.NetException;
public class TcpsNTAdapter
  extends TcpNTAdapter
{
  String matchSSLServerCertDNWith;
  boolean fullDNMatch;
  SSLSocketFactory l_sslSockFac;
/*  73 */   Socket underlyingSocket = null;
  
  SSLSocket socketWithListener;
  
  public TcpsNTAdapter(String paramString, Properties paramProperties)
    throws NLException
  {
/*  96 */     super(paramString, paramProperties);
  }
  
  public void connect()
    throws IOException
  {
/* 119 */     this.l_sslSockFac = CustomSSLSocketFactory.getSSLSocketFactory(this.socketOptions);
    
/* 126 */     this.underlyingSocket = new Socket();
    
/* 128 */     String str2 = (String)this.socketOptions.get(Integer.valueOf(2));
    
/* 132 */     int i = str2 == null ? 0 : Integer.parseInt(str2);
    
/* 135 */     boolean bool = Boolean.parseBoolean((String)this.socketOptions.get(Integer.valueOf(18)));
    
/* 138 */     InetAddress[] arrayOfInetAddress = InetAddress.getAllByName(this.host);
    
/* 140 */     if ((bool) && (arrayOfInetAddress.length > 1))
    {
/* 142 */       arrayOfInetAddress = getAddressesInCircularOrder(this.host, arrayOfInetAddress);
    }
    
/* 145 */     int j = arrayOfInetAddress.length;
    
/* 147 */     int k = 0;
    
    do
    {
/* 151 */       InetAddress localInetAddress = arrayOfInetAddress[k];
/* 152 */       InetSocketAddress localInetSocketAddress = new InetSocketAddress(localInetAddress, this.port);
/* 153 */       k++;
/* 154 */       j--;
/* 155 */       this.underlyingSocket = new Socket();
      
      try
      {
/* 160 */         this.underlyingSocket.connect(localInetSocketAddress, i);
        
/* 167 */         this.socket = this.l_sslSockFac.createSocket(this.underlyingSocket, this.host, this.port, true);
        
        String str1;
/* 170 */         if ((str1 = (String)this.socketOptions.get(Integer.valueOf(3))) != null)
        {
/* 173 */           setOption(3, str1);
        }
        
/* 176 */         setSSLSocketOptions();
      }
      catch (IOException localIOException)
      {
        try
        {
/* 183 */           if (this.underlyingSocket != null) {
/* 184 */             this.underlyingSocket.close();
          }
/* 186 */           if (this.socket != null) {
/* 187 */             this.socket.close();
          }
        }
        catch (Exception localException) {}
        
/* 194 */         if (j <= 0)
        {
/* 200 */           throw localIOException;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
/* 205 */         throw new NetException(505);
      }
      
    }
/* 210 */     while (k < arrayOfInetAddress.length);
  }
  
  public void renegotiateSession()
    throws IOException
  {
/* 223 */     this.socketWithListener = ((SSLSocket)this.socket);
    
/* 227 */     this.socket = this.l_sslSockFac.createSocket(this.underlyingSocket, this.host, this.port, true);
    
    String str;
/* 230 */     if ((str = (String)this.socketOptions.get(Integer.valueOf(3))) != null)
    {
/* 232 */       setOption(3, str);
    }
    
/* 235 */     setSSLSocketOptions();
  }
  
  public void setSSLSocketOptions()
    throws IOException
  {
/* 251 */     super.setSocketOptions();
/* 252 */     SSLSocket localSSLSocket = (SSLSocket)this.socket;
    
/* 254 */     localSSLSocket.setUseClientMode(true);
    
/* 257 */     TcpsConfigure.configureVersion(localSSLSocket, (String)this.socketOptions.get(Integer.valueOf(6)));
    
/* 262 */     TcpsConfigure.configureCipherSuites(localSSLSocket, (String)this.socketOptions.get(Integer.valueOf(7)));
  }
  
  public void setOption(int paramInt, Object paramObject)
    throws IOException, NetException
  {
/* 281 */     SSLSocket localSSLSocket = (SSLSocket)this.socket;
/* 282 */     switch (paramInt)
    {
    case 8: 
/* 285 */       setServerDNMatchValue((String[])paramObject);
/* 286 */       break;
    
    case 7: 
/* 289 */       if (((String)paramObject).equalsIgnoreCase("TRUE")) {
/* 290 */         this.fullDNMatch = true;
      } else
/* 292 */         this.fullDNMatch = false;
/* 293 */       break;
    
    default: 
/* 296 */       super.setOption(paramInt, paramObject);
    }
    
  }
  
  public Object getOption(int paramInt)
    throws IOException, NetException
  {
/* 317 */     SSLSocket localSSLSocket = (SSLSocket)this.socket;
/* 318 */     switch (paramInt)
    {
    case 2: 
/* 321 */       String str1 = localSSLSocket.getSession().getCipherSuite();
      
/* 325 */       if ((str1 != null) && (str1.indexOf("NULL") == -1))
/* 326 */         return "TRUE";
/* 327 */       return "FALSE";
    
    case 5: 
/* 330 */       return localSSLSocket.getSession().getCipherSuite();
    
    case 3: 
/* 333 */       X509Certificate localX509Certificate1 = (X509Certificate)localSSLSocket.getSession().getPeerCertificates()[0];
      
/* 335 */       return localX509Certificate1.getSubjectDN().getName();
    
    case 4: 
/* 338 */       return localSSLSocket.getSession().getPeerCertificateChain();
    
    case 6: 
/* 341 */       String str2 = (String)this.socketOptions.get(Integer.valueOf(4));
      
/* 346 */       if (str2 == null) {
/* 347 */         str2 = System.getProperty("oracle.net.ssl_server_dn_match", "false");
      }
      
/* 350 */       if ((str2.equalsIgnoreCase("YES")) || (str2.equalsIgnoreCase("ON")) || (str2.equalsIgnoreCase("TRUE")))
      {
/* 354 */         X509Certificate localX509Certificate2 = (X509Certificate)localSSLSocket.getSession().getPeerCertificates()[0];
        
/* 357 */         String str3 = localX509Certificate2.getSubjectDN().getName();
        
/* 359 */         if (TcpsConfigure.matchServerDN(str3, this.matchSSLServerCertDNWith, this.fullDNMatch))
        {
/* 361 */           return "TRUE";
        }
/* 363 */         return "FALSE";
      }
/* 365 */       return "TRUE";
    }
    
/* 368 */     return super.getOption(paramInt);
  }
  
  public void setServerDNMatchValue(String[] paramArrayOfString)
  {
/* 383 */     String str1 = paramArrayOfString[0];
/* 384 */     String str2 = paramArrayOfString[1];
/* 385 */     String str3 = paramArrayOfString[2];
/* 386 */     if (str1 != null)
    {
/* 388 */       this.matchSSLServerCertDNWith = str1;
/* 389 */       this.fullDNMatch = true;
    }
/* 391 */     else if (str2 != null)
    {
/* 393 */       if (str2.indexOf('.') != -1) {
/* 394 */         this.matchSSLServerCertDNWith = ("CN=" + str2.substring(0, str2.indexOf('.')));
      }
      else
/* 397 */         this.matchSSLServerCertDNWith = ("CN=" + str2.trim());
/* 398 */     } else if (str3 != null)
    {
/* 400 */       if (str3.indexOf('.') != -1) {
/* 401 */         this.matchSSLServerCertDNWith = ("CN=" + str3.substring(0, str3.indexOf('.')));
      }
      else {
/* 404 */         this.matchSSLServerCertDNWith = ("CN=" + str3.trim());
      }
    }
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/nt/TcpsNTAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */