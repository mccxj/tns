package oracle.net.nt;
import java.io.IOException;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import oracle.net.ns.NetException;
public class ConnStrategy
{
  static final boolean DEBUG = false;
/*  91 */   private boolean optFound = false;
/*  92 */   public boolean reuseOpt = false;
  private ConnOption copt;
  public int sdu;
/*  95 */   public int tdu; public int nextOptToTry; public Properties socketOptions = new Properties();
  
  private String osuser;
  
  private String programName;
/* 100 */   public int connectTimeout = -1;
/* 101 */   public int retryCount = 0;
  
  private int lastRetryCounter;
/* 104 */   public Vector cOpts = new Vector(10, 10);
  
  public ConnStrategy(Properties paramProperties)
  {
/* 112 */     this.nextOptToTry = 0;
/* 113 */     this.lastRetryCounter = 0;
/* 114 */     this.osuser = paramProperties.getProperty("oracle.jdbc.v$session.osuser");
/* 115 */     this.programName = paramProperties.getProperty("oracle.jdbc.v$session.program");
/* 116 */     createSocketOptions(paramProperties);
  }
  
  public String getOSUsername()
  {
/* 121 */     return this.osuser;
  }
  
  public String getProgramName() {
/* 125 */     return this.programName;
  }
  
  public void createSocketOptions(Properties paramProperties) {
/* 129 */     String str1 = null;
/* 130 */     String str2 = null;
/* 131 */     int i = 0;
    
/* 137 */     Enumeration localEnumeration = paramProperties.keys();
/* 138 */     while (localEnumeration.hasMoreElements())
    {
/* 140 */       str1 = (String)localEnumeration.nextElement();
      
/* 142 */       if (str1.equalsIgnoreCase("TCP.NODELAY"))
      {
/* 144 */         i = 1;
/* 145 */         str2 = paramProperties.getProperty("TCP.NODELAY").toUpperCase();
/* 146 */         if (str2.equals("NO")) {
/* 147 */           this.socketOptions.put(Integer.valueOf(0), "NO");
        } else {
/* 149 */           this.socketOptions.put(Integer.valueOf(0), "YES");
        }
/* 151 */       } else if (str1.equalsIgnoreCase("oracle.net.READ_TIMEOUT"))
      {
/* 153 */         str2 = paramProperties.getProperty("oracle.net.READ_TIMEOUT");
/* 154 */         this.socketOptions.put(Integer.valueOf(3), str2);
      }
/* 156 */       else if (str1.equalsIgnoreCase("oracle.net.CONNECT_TIMEOUT"))
      {
/* 158 */         str2 = paramProperties.getProperty("oracle.net.CONNECT_TIMEOUT");
/* 159 */         this.socketOptions.put(Integer.valueOf(2), str2);
      }
/* 161 */       else if (str1.equalsIgnoreCase("oracle.net.ssl_server_dn_match"))
      {
/* 163 */         str2 = paramProperties.getProperty("oracle.net.ssl_server_dn_match");
/* 164 */         this.socketOptions.put(Integer.valueOf(4), str2);
      }
/* 167 */       else if (str1.equalsIgnoreCase("oracle.net.wallet_location"))
      {
/* 170 */         str2 = paramProperties.getProperty("oracle.net.wallet_location");
/* 171 */         this.socketOptions.put(Integer.valueOf(5), str2);
      }
/* 175 */       else if (str1.equalsIgnoreCase("oracle.net.wallet_password"))
      {
/* 178 */         str2 = paramProperties.getProperty("oracle.net.wallet_password");
/* 179 */         this.socketOptions.put(Integer.valueOf(16), str2);
      }
/* 183 */       else if (str1.equalsIgnoreCase("oracle.net.ssl_version"))
      {
/* 185 */         str2 = paramProperties.getProperty("oracle.net.ssl_version");
/* 186 */         this.socketOptions.put(Integer.valueOf(6), str2);
      }
/* 189 */       else if (str1.equalsIgnoreCase("oracle.net.ssl_cipher_suites"))
      {
/* 191 */         str2 = paramProperties.getProperty("oracle.net.ssl_cipher_suites");
/* 192 */         this.socketOptions.put(Integer.valueOf(7), str2);
      }
/* 195 */       else if (str1.equalsIgnoreCase("javax.net.ssl.keyStore"))
      {
/* 198 */         str2 = paramProperties.getProperty("javax.net.ssl.keyStore");
/* 199 */         this.socketOptions.put(Integer.valueOf(8), str2);
      }
/* 202 */       else if (str1.equalsIgnoreCase("javax.net.ssl.keyStoreType"))
      {
/* 205 */         str2 = paramProperties.getProperty("javax.net.ssl.keyStoreType");
/* 206 */         this.socketOptions.put(Integer.valueOf(9), str2);
      }
/* 210 */       else if (str1.equalsIgnoreCase("javax.net.ssl.keyStorePassword"))
      {
/* 213 */         str2 = paramProperties.getProperty("javax.net.ssl.keyStorePassword");
        
/* 215 */         this.socketOptions.put(Integer.valueOf(10), str2);
      }
/* 219 */       else if (str1.equalsIgnoreCase("javax.net.ssl.trustStore"))
      {
/* 222 */         str2 = paramProperties.getProperty("javax.net.ssl.trustStore");
/* 223 */         this.socketOptions.put(Integer.valueOf(11), str2);
      }
/* 226 */       else if (str1.equalsIgnoreCase("javax.net.ssl.trustStoreType"))
      {
/* 229 */         str2 = paramProperties.getProperty("javax.net.ssl.trustStoreType");
        
/* 231 */         this.socketOptions.put(Integer.valueOf(12), str2);
      }
/* 235 */       else if (str1.equalsIgnoreCase("javax.net.ssl.trustStorePassword"))
      {
/* 238 */         str2 = paramProperties.getProperty("javax.net.ssl.trustStorePassword");
        
/* 240 */         this.socketOptions.put(Integer.valueOf(13), str2);
      }
/* 244 */       else if (str1.equalsIgnoreCase("ssl.keyManagerFactory.algorithm"))
      {
/* 247 */         str2 = paramProperties.getProperty("ssl.keyManagerFactory.algorithm");
        
/* 249 */         this.socketOptions.put(Integer.valueOf(14), str2);
      }
/* 253 */       else if (str1.equalsIgnoreCase("FORCE_DNS_LOAD_BALANCING"))
      {
/* 256 */         str2 = paramProperties.getProperty("FORCE_DNS_LOAD_BALANCING");
        
/* 258 */         this.socketOptions.put(Integer.valueOf(18), str2);
      }
/* 262 */       else if (str1.equalsIgnoreCase("oracle.net.SDP"))
      {
/* 264 */         str2 = paramProperties.getProperty("oracle.net.SDP");
/* 265 */         this.socketOptions.put(Integer.valueOf(19), str2);
      }
/* 268 */       else if (str1.equalsIgnoreCase("oracle.net.keepAlive"))
      {
/* 270 */         str2 = paramProperties.getProperty("oracle.net.keepAlive");
        
/* 276 */         if (Boolean.parseBoolean(str2)) {
/* 277 */           this.socketOptions.put(Integer.valueOf(1), "YES");
        } else {
/* 279 */           this.socketOptions.put(Integer.valueOf(1), "NO");
        }
      }
    }
    
/* 288 */     if ((i == 0) && (!this.reuseOpt)) {
/* 289 */       this.socketOptions.put(Integer.valueOf(0), "YES");
    }
  }
  
  public void addSocketOptions(boolean paramBoolean)
  {
/* 298 */     if (paramBoolean) {
/* 299 */       this.socketOptions.put(Integer.valueOf(1), "YES");
/* 300 */     } else if (!this.reuseOpt) {
/* 301 */       this.socketOptions.put(Integer.valueOf(1), "NO");
    }
  }
  
  public void addOption(ConnOption paramConnOption)
  {
/* 313 */     this.cOpts.addElement(paramConnOption);
  }
  
  public boolean hasMoreOptions()
  {
/* 324 */     if (this.nextOptToTry < this.cOpts.size()) {
/* 325 */       return true;
    }
/* 327 */     return false;
  }
  
  public ConnOption execute()
    throws NetException
  {
/* 353 */     Object localObject = null;
    
/* 355 */     if (this.connectTimeout == -1)
    {
/* 359 */       if (this.socketOptions.get(Integer.valueOf(2)) == null)
      {
/* 362 */         this.socketOptions.put(Integer.valueOf(2), Integer.toString(60000));
      }
      
    }
    else
    {
/* 371 */       this.socketOptions.put(Integer.valueOf(2), Integer.toString(this.connectTimeout));
    }
    
/* 375 */     if (this.retryCount < 0)
    {
/* 378 */       this.retryCount = 0;
    }
    
/* 381 */     this.socketOptions.put(Integer.valueOf(17), Integer.toString(this.retryCount));
    
/* 390 */     for (int i = this.lastRetryCounter; i <= this.retryCount; i++)
    {
/* 392 */       while (this.nextOptToTry < this.cOpts.size())
      {
        try
        {
/* 396 */           this.copt = ((ConnOption)this.cOpts.elementAt(this.nextOptToTry));
          
/* 405 */           this.copt.connect(this.socketOptions);
          
/* 410 */           this.copt.sdu = this.sdu;
/* 411 */           this.copt.tdu = this.tdu;
/* 412 */           this.optFound = true;
          
/* 415 */           this.nextOptToTry += 1;
/* 416 */           this.lastRetryCounter = i;
          
/* 418 */           return this.copt;
        }
        catch (IOException localIOException)
        {
/* 425 */           this.nextOptToTry += 1;
/* 426 */           localObject = localIOException;
        }
      }
/* 429 */       this.nextOptToTry = 0;
    }
    
/* 433 */     if (localObject == null)
    {
/* 435 */       throw new NetException(20);
    }
    
/* 439 */     throw ((NetException)new NetException(20).initCause((Throwable)localObject));
  }
  
  public boolean optAvailable()
  {
/* 445 */     return this.optFound;
  }
  
  public void clearElements() {
/* 449 */     this.cOpts.removeAllElements();
  }
  
  public ConnOption getOption()
  {
/* 454 */     return this.copt;
  }
  
  public boolean isConnectionSocketKeepAlive()
    throws SocketException
  {
/* 464 */     return this.copt.isConnectionSocketKeepAlive();
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/nt/ConnStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */