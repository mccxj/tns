package oracle.net.jndi;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
public class CustomSSLSocketFactory
  extends SSLSocketFactory
{
  private static final boolean DEBUG = false;
/*  74 */   private static SSLSocketFactory ossl = null;
  
  protected static boolean isFactorySet()
  {
/*  83 */     return null != ossl;
  }
  
  protected static void setFactory(SSLSocketFactory paramSSLSocketFactory)
  {
/*  92 */     if (null == ossl) {
/*  93 */       ossl = paramSSLSocketFactory;
    }
  }
  
  protected void setDefaultFactory()
  {
/* 106 */     setFactory((SSLSocketFactory)SSLSocketFactory.getDefault());
  }
  
  public static SocketFactory getDefault()
  {
/* 133 */     Object localObject = null;
    
    try
    {
/* 143 */       localObject = new TrustManagerSSLSocketFactory();
    }
    catch (Exception localException)
    {
/* 150 */       localObject = new CustomSSLSocketFactory();
    }
    
/* 155 */     ((CustomSSLSocketFactory)localObject).setDefaultFactory();
/* 156 */     return (SocketFactory)localObject;
  }
  
  public Socket createSocket(String paramString, int paramInt)
    throws IOException
  {
/* 166 */     SSLSocket localSSLSocket = (SSLSocket)ossl.createSocket(paramString, paramInt);
/* 167 */     return init(localSSLSocket);
  }
  
  public Socket createSocket(InetAddress paramInetAddress, int paramInt)
    throws IOException
  {
/* 173 */     SSLSocket localSSLSocket = (SSLSocket)ossl.createSocket(paramInetAddress, paramInt);
/* 174 */     return init(localSSLSocket);
  }
  
  public Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2)
    throws IOException
  {
/* 180 */     SSLSocket localSSLSocket = (SSLSocket)ossl.createSocket(paramString, paramInt1, paramInetAddress, paramInt2);
/* 181 */     return init(localSSLSocket);
  }
  
  public Socket createSocket(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2)
    throws IOException
  {
/* 188 */     SSLSocket localSSLSocket = (SSLSocket)ossl.createSocket(paramInetAddress1, paramInt1, paramInetAddress2, paramInt2);
/* 189 */     return init(localSSLSocket);
  }
  
  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException
  {
/* 196 */     SSLSocket localSSLSocket = (SSLSocket)ossl.createSocket(paramSocket, paramString, paramInt, paramBoolean);
/* 197 */     return init(localSSLSocket);
  }
  
  public String[] getDefaultCipherSuites()
  {
/* 202 */     return ossl.getDefaultCipherSuites();
  }
  
  public String[] getSupportedCipherSuites()
  {
/* 207 */     return ossl.getSupportedCipherSuites();
  }
  
  private SSLSocket init(SSLSocket paramSSLSocket)
    throws IOException
  {
/* 213 */     paramSSLSocket.setUseClientMode(true);
    
/* 215 */     String[] arrayOfString = { "SSL_DH_anon_WITH_3DES_EDE_CBC_SHA" };
    
/* 220 */     paramSSLSocket.setEnabledCipherSuites(arrayOfString);
/* 221 */     paramSSLSocket.startHandshake();
/* 222 */     return paramSSLSocket;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/jndi/CustomSSLSocketFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */