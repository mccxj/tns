package oracle.net.nt;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Hashtable;
import java.util.Properties;
import oracle.net.jdbc.nl.NLException;
import oracle.net.jdbc.nl.NVFactory;
import oracle.net.jdbc.nl.NVNavigator;
import oracle.net.jdbc.nl.NVPair;
import oracle.net.ns.NetException;
public class TcpNTAdapter
  implements NTAdapter
{
  static final boolean DEBUG = false;
  int port;
  String host;
  protected Socket socket;
  protected int sockTimeout;
  protected Properties socketOptions;
  
  public TcpNTAdapter(String paramString, Properties paramProperties)
    throws NLException
  {
/*  50 */     this.socketOptions = paramProperties;
    
/*  52 */     NVNavigator localNVNavigator = new NVNavigator();
/*  53 */     NVPair localNVPair1 = new NVFactory().createNVPair(paramString);
    
/*  55 */     NVPair localNVPair2 = localNVNavigator.findNVPair(localNVPair1, "HOST");
/*  56 */     NVPair localNVPair3 = localNVNavigator.findNVPair(localNVPair1, "PORT");
    
/*  59 */     if (localNVPair2 == null)
/*  60 */       throw new NLException("NoNVPair-04614", "HOST");
/*  61 */     this.host = localNVPair2.getAtom();
    
/*  63 */     if (localNVPair3 != null) {
      try
      {
/*  66 */         this.port = Integer.parseInt(localNVPair3.getAtom());
      } catch (Exception localException) {
/*  68 */         throw ((NLException)new NLException(new NetException(116).getMessage()).initCause(localException));
      }
      
    } else {
/*  72 */       this.port = 1521;
    }
/*  74 */     if ((this.port < 0) || (this.port > 65535)) {
/*  75 */       throw new NLException(new NetException(116).getMessage());
    }
  }
  
  public void connect()
    throws IOException
  {
/* 105 */     String str = (String)this.socketOptions.get(Integer.valueOf(2));
    
/* 109 */     boolean bool = Boolean.parseBoolean((String)this.socketOptions.get(Integer.valueOf(18)));
    
/* 117 */     InetAddress[] arrayOfInetAddress = InetAddress.getAllByName(this.host);
    
/* 119 */     if ((bool) && (arrayOfInetAddress.length > 1))
    {
/* 122 */       arrayOfInetAddress = getAddressesInCircularOrder(this.host, arrayOfInetAddress);
    }
    
/* 125 */     int i = arrayOfInetAddress.length;
    
/* 127 */     int j = 0;
    
    do
    {
/* 131 */       InetAddress localInetAddress = arrayOfInetAddress[j];
/* 132 */       j++;
/* 133 */       i--;
      
/* 137 */       this.socket = new Socket();
      
      try
      {
/* 149 */         this.socket.connect(new InetSocketAddress(localInetAddress, this.port), Integer.parseInt(str));
      }
      catch (IOException localIOException)
      {
        try
        {
/* 164 */           if (this.socket != null) {
/* 165 */             this.socket.close();
          }
        }
        catch (Exception localException) {}
        
/* 179 */         if (i <= 0)
        {
/* 185 */           throw localIOException;
        }
        
      }
      
    }
/* 192 */     while (j < arrayOfInetAddress.length);
    
/* 194 */     setOption(3, str);
    
/* 196 */     setSocketOptions();
  }
  
  public void setSocketOptions()
    throws IOException
  {
    String str;
    
/* 216 */     if ((str = (String)this.socketOptions.get(Integer.valueOf(0))) != null)
    {
/* 218 */       setOption(0, str); }
/* 219 */     if ((str = (String)this.socketOptions.get(Integer.valueOf(1))) != null)
    {
/* 221 */       setOption(1, str);
    }
  }
  
  public void disconnect()
    throws IOException
  {
    try
    {
/* 232 */       this.socket.close();
    } finally {
/* 234 */       this.socket = null;
    }
  }
  
  public InputStream getInputStream()
    throws IOException
  {
/* 253 */     return this.socket.getInputStream();
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
/* 263 */     return this.socket.getOutputStream();
  }
  
  public void setOption(int paramInt, Object paramObject)
    throws IOException, NetException
  {
    String str;
    
/* 272 */     switch (paramInt)
    {
    case 0: 
/* 276 */       str = (String)paramObject;
/* 277 */       this.socket.setTcpNoDelay(str.equals("YES"));
      
/* 279 */       break;
    
    case 1: 
/* 283 */       str = (String)paramObject;
/* 284 */       if (str.equals("YES")) {
/* 285 */         this.socket.setKeepAlive(true);
      }
/* 287 */       break;
    
    case 3: 
/* 290 */       this.sockTimeout = Integer.parseInt((String)paramObject);
/* 291 */       this.socket.setSoTimeout(this.sockTimeout);
/* 292 */       break;
    }
    
  }
  
  public Object getOption(int paramInt)
    throws IOException, NetException
  {
/* 305 */     switch (paramInt)
    {
    case 1: 
/* 308 */       return "" + this.sockTimeout;
    }
/* 310 */     return null;
  }
  
  public void abort()
    throws NetException, IOException
  {
    try
    {
/* 323 */       this.socket.setSoLinger(true, 0);
    }
    catch (Exception localException) {}
    
/* 328 */     this.socket.close();
  }
  
  public void sendUrgentByte(int paramInt)
    throws IOException
  {
/* 337 */     this.socket.sendUrgentData(paramInt);
  }
  
  public boolean isCharacteristicUrgentSupported() throws IOException
  {
    try {
/* 343 */       return !this.socket.getOOBInline();
    }
    catch (IOException localIOException) {}
    
/* 347 */     return false;
  }
  
  public void setReadTimeoutIfRequired(Properties paramProperties)
    throws IOException, NetException
  {
/* 364 */     String str = (String)paramProperties.get("oracle.net.READ_TIMEOUT");
/* 365 */     if (str == null) {
/* 366 */       str = "0";
    }
/* 368 */     setOption(3, str);
  }
  
  public String toString()
  {
/* 373 */     return "host=" + this.host + ", port=" + this.port + "\n    socket_timeout=" + this.sockTimeout + ", socketOptions=" + this.socketOptions.toString() + "\n    socket=" + this.socket;
  }
  
/* 380 */   private static Hashtable<String, InetAddress[]> inetaddressesCache = new Hashtable();
/* 381 */   private static Hashtable<String, Integer> circularOffsets = new Hashtable();
  
  static final synchronized InetAddress[] getAddressesInCircularOrder(String paramString, InetAddress[] paramArrayOfInetAddress)
  {
/* 407 */     InetAddress[] arrayOfInetAddress1 = (InetAddress[])inetaddressesCache.get(paramString);
/* 408 */     Integer localInteger = (Integer)circularOffsets.get(paramString);
/* 409 */     if ((arrayOfInetAddress1 == null) || (!areEquals(arrayOfInetAddress1, paramArrayOfInetAddress)))
    {
/* 411 */       localInteger = new Integer(0);
/* 412 */       arrayOfInetAddress1 = paramArrayOfInetAddress;
/* 413 */       inetaddressesCache.put(paramString, paramArrayOfInetAddress);
/* 414 */       circularOffsets.put(paramString, localInteger);
    }
/* 416 */     InetAddress[] arrayOfInetAddress2 = getCopyAddresses(arrayOfInetAddress1, localInteger.intValue());
/* 417 */     circularOffsets.put(paramString, new Integer((localInteger.intValue() + 1) % arrayOfInetAddress1.length));
/* 418 */     return arrayOfInetAddress2;
  }
  
  private static final boolean areEquals(InetAddress[] paramArrayOfInetAddress1, InetAddress[] paramArrayOfInetAddress2)
  {
/* 427 */     if (paramArrayOfInetAddress1.length != paramArrayOfInetAddress2.length)
/* 428 */       return false;
/* 429 */     for (int i = 0; i < paramArrayOfInetAddress1.length; i++)
/* 430 */       if (!paramArrayOfInetAddress1[i].equals(paramArrayOfInetAddress2[i]))
/* 431 */         return false;
/* 432 */     return true;
  }
  
  private static final InetAddress[] getCopyAddresses(InetAddress[] paramArrayOfInetAddress, int paramInt)
  {
/* 441 */     InetAddress[] arrayOfInetAddress = new InetAddress[paramArrayOfInetAddress.length];
/* 442 */     for (int i = 0; i < paramArrayOfInetAddress.length; i++)
/* 443 */       arrayOfInetAddress[i] = paramArrayOfInetAddress[((i + paramInt) % paramArrayOfInetAddress.length)];
/* 444 */     return arrayOfInetAddress;
  }
  
  public boolean isConnectionSocketKeepAlive()
    throws SocketException
  {
/* 454 */     return this.socket.getKeepAlive();
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/nt/TcpNTAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */