package oracle.net.nt;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
public class SdpNTAdapter
  implements NTAdapter
{
  static final boolean DEBUG = false;
  static final String SDP_SOCKET_CLASS_NAME = "com.oracle.net.Sdp";
/*  38 */   static Method OPEN_SOCKET = null;
  int port;
  String host;
  
  private static Socket getSDPSocket() throws IOException {
/*  43 */     if (OPEN_SOCKET == null) {
      try {
/*  45 */         Class localClass = Class.forName("com.oracle.net.Sdp");
/*  46 */         OPEN_SOCKET = localClass.getMethod("openSocket", new Class[0]);
      }
      catch (ClassNotFoundException localClassNotFoundException) {
/*  49 */         throw new IOException("SDP enabled, but SDP socket class not in classpath", localClassNotFoundException);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
/*  58 */         throw new IOException("SDP enabled but unable to get SDP socket class", localNoSuchMethodException);
      }
    }
    
    try
    {
/*  68 */       return (Socket)OPEN_SOCKET.invoke(null, new Object[0]);
    }
    catch (IllegalAccessException localIllegalAccessException) {
/*  71 */       throw new IOException("SDP enabled, but SDP.openSocket could not be accessed", localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
/*  80 */       throw new IOException("SDP enabled, but SDP.openSocket raised an exception", localInvocationTargetException);
    }
  }
  
  public SdpNTAdapter(String paramString, Properties paramProperties)
    throws NLException
  {
/* 102 */     this.socketOptions = paramProperties;
    
/* 104 */     NVNavigator localNVNavigator = new NVNavigator();
/* 105 */     NVPair localNVPair1 = new NVFactory().createNVPair(paramString);
    
/* 107 */     NVPair localNVPair2 = localNVNavigator.findNVPair(localNVPair1, "HOST");
/* 108 */     NVPair localNVPair3 = localNVNavigator.findNVPair(localNVPair1, "PORT");
    
/* 111 */     if (localNVPair2 == null)
/* 112 */       throw new NLException("NoNVPair-04614", "HOST");
/* 113 */     this.host = localNVPair2.getAtom();
    
/* 115 */     if (localNVPair3 != null) {
      try
      {
/* 118 */         this.port = Integer.parseInt(localNVPair3.getAtom());
      } catch (Exception localException) {
/* 120 */         throw ((NLException)new NLException(new NetException(116).getMessage()).initCause(localException));
      }
      
    } else {
/* 124 */       this.port = 1521;
    }
/* 126 */     if ((this.port < 0) || (this.port > 65535)) {
/* 127 */       throw new NLException(new NetException(116).getMessage());
    }
  }
  
  protected Socket socket;
  
  protected int sockTimeout;
  
  protected Properties socketOptions;
  
  public void connect()
    throws IOException
  {
/* 157 */     boolean bool1 = Boolean.parseBoolean((String)this.socketOptions.get(Integer.valueOf(19)));
    
/* 159 */     if (!bool1) {
/* 160 */       throw new IOException("Attempt to use SDP protocol without enabling SDP support.");
    }
    
/* 163 */     String str = (String)this.socketOptions.get(Integer.valueOf(2));
    
/* 167 */     boolean bool2 = Boolean.parseBoolean((String)this.socketOptions.get(Integer.valueOf(18)));
    
/* 171 */     InetAddress[] arrayOfInetAddress = InetAddress.getAllByName(this.host);
/* 172 */     if ((bool2) && (arrayOfInetAddress.length > 1))
    {
/* 175 */       arrayOfInetAddress = getAddressesInCircularOrder(this.host, arrayOfInetAddress);
    }
    
/* 178 */     int i = arrayOfInetAddress.length;
    
/* 180 */     int j = 0;
    
    do
    {
/* 184 */       InetAddress localInetAddress = arrayOfInetAddress[j];
/* 185 */       j++;
/* 186 */       i--;
/* 187 */       this.socket = getSDPSocket();
      
      try
      {
/* 200 */         this.socket.connect(new InetSocketAddress(localInetAddress, this.port), Integer.parseInt(str));
      }
      catch (IOException localIOException)
      {
        try
        {
/* 215 */           if (this.socket != null) {
/* 216 */             this.socket.close();
          }
        }
        catch (Exception localException) {}
        
/* 230 */         if (i <= 0)
        {
/* 236 */           throw localIOException;
        }
        
      }
      
    }
/* 242 */     while (j < arrayOfInetAddress.length);
    
/* 244 */     setOption(3, str);
    
/* 246 */     setSocketOptions();
  }
  
  public void setSocketOptions()
    throws IOException
  {
    String str;
    
/* 266 */     if ((str = (String)this.socketOptions.get(Integer.valueOf(0))) != null)
    {
/* 268 */       setOption(0, str); }
/* 269 */     if ((str = (String)this.socketOptions.get(Integer.valueOf(1))) != null)
    {
/* 271 */       setOption(1, str);
    }
  }
  
  public void disconnect()
    throws IOException
  {
    try
    {
/* 282 */       this.socket.close();
    } finally {
/* 284 */       this.socket = null;
    }
  }
  
  public InputStream getInputStream()
    throws IOException
  {
/* 303 */     return this.socket.getInputStream();
  }
  
  public OutputStream getOutputStream()
    throws IOException
  {
/* 313 */     return this.socket.getOutputStream();
  }
  
  public void setOption(int paramInt, Object paramObject)
    throws IOException, NetException
  {
    String str;
    
/* 322 */     switch (paramInt)
    {
    case 0: 
/* 326 */       str = (String)paramObject;
/* 327 */       this.socket.setTcpNoDelay(str.equals("YES"));
      
/* 329 */       break;
    
    case 1: 
/* 333 */       str = (String)paramObject;
/* 334 */       if (str.equals("YES")) {
/* 335 */         this.socket.setKeepAlive(true);
      }
/* 337 */       break;
    
    case 3: 
/* 340 */       this.sockTimeout = Integer.parseInt((String)paramObject);
/* 341 */       this.socket.setSoTimeout(this.sockTimeout);
/* 342 */       break;
    }
    
  }
  
  public Object getOption(int paramInt)
    throws IOException, NetException
  {
/* 355 */     switch (paramInt)
    {
    case 1: 
/* 358 */       return "" + this.sockTimeout;
    }
/* 360 */     return null;
  }
  
  public void abort()
    throws NetException, IOException
  {
    try
    {
/* 373 */       this.socket.setSoLinger(true, 0);
    }
    catch (Exception localException) {}
    
/* 378 */     this.socket.close();
  }
  
  public void sendUrgentByte(int paramInt)
    throws IOException
  {
/* 387 */     this.socket.sendUrgentData(paramInt);
  }
  
  public boolean isCharacteristicUrgentSupported() throws IOException
  {
    try {
/* 393 */       return !this.socket.getOOBInline();
    }
    catch (IOException localIOException) {}
    
/* 397 */     return false;
  }
  
  public void setReadTimeoutIfRequired(Properties paramProperties)
    throws IOException, NetException
  {
/* 414 */     String str = (String)paramProperties.get("oracle.net.READ_TIMEOUT");
/* 415 */     if (str == null) {
/* 416 */       str = "0";
    }
/* 418 */     setOption(3, str);
  }
  
  public String toString()
  {
/* 423 */     return "host=" + this.host + ", port=" + this.port + "\n    socket_timeout=" + this.sockTimeout + ", socketOptions=" + this.socketOptions.toString() + "\n    socket=" + this.socket;
  }
  
/* 430 */   private static Hashtable<String, InetAddress[]> inetaddressesCache = new Hashtable();
/* 431 */   private static Hashtable<String, Integer> circularOffsets = new Hashtable();
  
  private static final synchronized InetAddress[] getAddressesInCircularOrder(String paramString, InetAddress[] paramArrayOfInetAddress)
  {
/* 457 */     InetAddress[] arrayOfInetAddress1 = (InetAddress[])inetaddressesCache.get(paramString);
/* 458 */     Integer localInteger = (Integer)circularOffsets.get(paramString);
/* 459 */     if ((arrayOfInetAddress1 == null) || (!areEquals(arrayOfInetAddress1, paramArrayOfInetAddress)))
    {
/* 461 */       localInteger = new Integer(0);
/* 462 */       arrayOfInetAddress1 = paramArrayOfInetAddress;
/* 463 */       inetaddressesCache.put(paramString, paramArrayOfInetAddress);
/* 464 */       circularOffsets.put(paramString, localInteger);
    }
/* 466 */     InetAddress[] arrayOfInetAddress2 = getCopyAddresses(arrayOfInetAddress1, localInteger.intValue());
/* 467 */     circularOffsets.put(paramString, new Integer((localInteger.intValue() + 1) % arrayOfInetAddress1.length));
/* 468 */     return arrayOfInetAddress2;
  }
  
  private static final boolean areEquals(InetAddress[] paramArrayOfInetAddress1, InetAddress[] paramArrayOfInetAddress2)
  {
/* 477 */     if (paramArrayOfInetAddress1.length != paramArrayOfInetAddress2.length)
/* 478 */       return false;
/* 479 */     for (int i = 0; i < paramArrayOfInetAddress1.length; i++)
/* 480 */       if (!paramArrayOfInetAddress1[i].equals(paramArrayOfInetAddress2[i]))
/* 481 */         return false;
/* 482 */     return true;
  }
  
  private static final InetAddress[] getCopyAddresses(InetAddress[] paramArrayOfInetAddress, int paramInt)
  {
/* 491 */     InetAddress[] arrayOfInetAddress = new InetAddress[paramArrayOfInetAddress.length];
/* 492 */     for (int i = 0; i < paramArrayOfInetAddress.length; i++)
/* 493 */       arrayOfInetAddress[i] = paramArrayOfInetAddress[((i + paramInt) % paramArrayOfInetAddress.length)];
/* 494 */     return arrayOfInetAddress;
  }
  
  public boolean isConnectionSocketKeepAlive()
    throws SocketException
  {
/* 505 */     return this.socket.getKeepAlive();
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/nt/SdpNTAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */