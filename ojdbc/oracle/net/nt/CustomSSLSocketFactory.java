package oracle.net.nt;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.Security;
import java.util.Properties;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import oracle.net.jdbc.nl.NVFactory;
import oracle.net.jdbc.nl.NVNavigator;
import oracle.net.jdbc.nl.NVPair;
import oracle.net.ns.NetException;
public class CustomSSLSocketFactory
{
  static final boolean DEBUG = false;
  public static final String DEFAULT_SSO_WALLET_FILE_NAME = "cwallet.sso";
  public static final String DEFAULT_PKCS12_WALLET_FILE_NAME = "ewallet.p12";
  public static final String SSO_WALLET_TYPE = "SSO";
  public static final String PKCS12_WALLET_TYPE = "PKCS12";
  public static final String SUPPORTED_METHOD_TYPE = "FILE";
  public static SSLSocketFactory defSSLFactory;
/*  81 */   public static String defPropString = ;
  
  public static boolean initDefFactory;
  
  public static SSLSocketFactory getSSLSocketFactory(Properties paramProperties)
    throws IOException
  {
    String str1;
    
    Object localObject2;
    
/* 120 */     Object localObject1 = localObject2 = str1 = null;
    String str2;
/* 122 */     Object localObject4; Object localObject3 = localObject4 = str2 = null;
/* 123 */     String str3 = null;
/* 124 */     String str4 = null;
/* 125 */     String str5 = null;
/* 126 */     String str6 = null;
/* 127 */     String str7 = null;
/* 128 */     String str8 = null;
/* 129 */     SSLSocketFactory localSSLSocketFactory = null;
    
/* 135 */     str7 = (String)paramProperties.get(Integer.valueOf(5));
    
/* 137 */     str8 = (String)paramProperties.get(Integer.valueOf(16));
    
    Object localObject5;
    
/* 147 */     if (str7 == null)
    {
/* 149 */       localObject1 = (String)paramProperties.get(Integer.valueOf(8));
      
/* 151 */       if (localObject1 != null)
      {
/* 153 */         str1 = (String)paramProperties.get(Integer.valueOf(9));
        
/* 155 */         if (str1 == null)
/* 156 */           str1 = KeyStore.getDefaultType();
/* 157 */         localObject2 = (String)paramProperties.get(Integer.valueOf(10));
        
/* 159 */         if (localObject2 == null) {
/* 160 */           localObject2 = "";
        }
/* 162 */         str3 = (String)paramProperties.get(Integer.valueOf(14));
        
/* 164 */         if (str3 == null) {
/* 165 */           str3 = Security.getProperty("ssl.keyManagerFactory.algorithm");
        }
/* 167 */         if (str3 == null) {
/* 168 */           str3 = KeyManagerFactory.getDefaultAlgorithm();
        }
      }
/* 171 */       localObject3 = (String)paramProperties.get(Integer.valueOf(11));
      
/* 173 */       if (localObject3 != null)
      {
/* 175 */         str2 = (String)paramProperties.get(Integer.valueOf(12));
        
/* 177 */         if (str2 == null) {
/* 178 */           str2 = KeyStore.getDefaultType();
        }
/* 180 */         localObject4 = (String)paramProperties.get(Integer.valueOf(13));
        
/* 182 */         if (localObject4 == null) {
/* 183 */           localObject4 = "";
        }
/* 185 */         str4 = (String)paramProperties.get(Integer.valueOf(15));
        
/* 187 */         if (str4 == null) {
/* 188 */           str4 = Security.getProperty("ssl.trustManagerFactory.algorithm");
        }
/* 190 */         if (str4 == null) {
/* 191 */           str4 = TrustManagerFactory.getDefaultAlgorithm();
        }
      }
/* 194 */       str5 = (String)localObject1 + "#" + str1 + "#" + (String)localObject2 + "#" + (String)localObject3 + "#" + str2 + "#" + (String)localObject4 + "#" + str3 + "#" + str4;
    }
    else
    {
/* 203 */       int i = 0;
      
/* 206 */       if (str7.startsWith("(")) {
/* 207 */         str6 = processWalletLocation(str7);
/* 208 */       } else if (str7.startsWith("file:"))
      {
/* 210 */         str6 = str7.substring("file:".length());
/* 211 */         localObject5 = new File(str6);
/* 212 */         if (!((File)localObject5).exists())
/* 213 */           throw new NetException(407, "Couldn't find file at " + str6);
/* 214 */         if (!((File)localObject5).isDirectory()) {
/* 215 */           i = 1;
        }
      }
      else {
/* 219 */         throw new NetException(412, "Location: " + str7);
      }
      
/* 224 */       if (str8 == null)
      {
/* 227 */         if (i == 0) {
/* 228 */           localObject1 = str6 + System.getProperty("file.separator") + "cwallet.sso";
        }
        else {
/* 231 */           localObject1 = str6;
        }
/* 233 */         str1 = "SSO";
/* 234 */         localObject2 = "";
/* 235 */         str3 = KeyManagerFactory.getDefaultAlgorithm();
        
/* 237 */         localObject3 = localObject1;
/* 238 */         str2 = "SSO";
/* 239 */         localObject4 = "";
/* 240 */         str4 = TrustManagerFactory.getDefaultAlgorithm();
      }
      else
      {
/* 245 */         if (i == 0) {
/* 246 */           localObject1 = str6 + System.getProperty("file.separator") + "ewallet.p12";
        }
        else {
/* 249 */           localObject1 = str6;
        }
/* 251 */         str1 = "PKCS12";
/* 252 */         localObject2 = str8;
/* 253 */         str3 = KeyManagerFactory.getDefaultAlgorithm();
        
/* 255 */         localObject3 = localObject1;
/* 256 */         str2 = "PKCS12";
/* 257 */         localObject4 = str8;
/* 258 */         str4 = TrustManagerFactory.getDefaultAlgorithm();
      }
      
/* 261 */       str5 = str7 + "#" + str3 + "#" + str4;
    }
    
    Object localObject6;
/* 265 */     if (str5.equals(defPropString))
    {
/* 267 */       if (initDefFactory) {
/* 268 */         return defSSLFactory;
      }
/* 270 */       synchronized (CustomSSLSocketFactory.class)
      {
/* 272 */         if (!initDefFactory) {
          try
          {
/* 275 */             localObject5 = null;
/* 276 */             localObject6 = null;
            
/* 278 */             if (localObject1 != null) {
/* 279 */               localObject5 = getKeyManagerArray((String)localObject1, (String)localObject2, str1, str3);
            }
            
/* 282 */             if (localObject3 != null) {
/* 283 */               localObject6 = getTrustManagerArray((String)localObject3, (String)localObject4, str2, str4);
            }
            
/* 286 */             SSLContext localSSLContext = SSLContext.getInstance("SSL");
/* 287 */             localSSLContext.init((KeyManager[])localObject5, (TrustManager[])localObject6, null);
/* 288 */             defSSLFactory = localSSLContext.getSocketFactory();
/* 289 */             if (defSSLFactory != null) {
/* 290 */               initDefFactory = true;
            }
            
          }
          catch (Exception localException2)
          {
/* 296 */             throw ((NetException)new NetException(410).initCause(localException2));
          }
        }
/* 299 */         return defSSLFactory;
      }
    }
    try
    {
/* 304 */       ??? = null;
/* 305 */       TrustManager[] arrayOfTrustManager = null;
      
/* 307 */       if (localObject1 != null) {
/* 308 */         ??? = getKeyManagerArray((String)localObject1, (String)localObject2, str1, str3);
      }
/* 310 */       if (localObject3 != null) {
/* 311 */         arrayOfTrustManager = getTrustManagerArray((String)localObject3, (String)localObject4, str2, str4);
      }
/* 313 */       localObject6 = SSLContext.getInstance("SSL");
/* 314 */       ((SSLContext)localObject6).init((KeyManager[])???, arrayOfTrustManager, null);
      
/* 316 */       return ((SSLContext)localObject6).getSocketFactory();
    }
    catch (Exception localException1)
    {
/* 327 */       throw ((NetException)new NetException(410).initCause(localException1));
    }
  }
  
  public static KeyManager[] getKeyManagerArray(String paramString1, String paramString2, String paramString3, String paramString4)
    throws IOException
  {
/* 349 */     FileInputStream localFileInputStream = null;
    
    try
    {
/* 357 */       KeyStore localKeyStore = KeyStore.getInstance(paramString3);
/* 358 */       localFileInputStream = new FileInputStream(paramString1);
      
/* 360 */       localKeyStore.load(localFileInputStream, paramString2.toCharArray());
/* 361 */       KeyManagerFactory localKeyManagerFactory = KeyManagerFactory.getInstance(paramString4);
      
/* 363 */       localKeyManagerFactory.init(localKeyStore, paramString2.toCharArray());
      
/* 365 */       return localKeyManagerFactory.getKeyManagers();
    }
    catch (Exception localException)
    {
/* 369 */       throw ((NetException)new NetException(408).initCause(localException));
    } finally {
/* 371 */       if (localFileInputStream != null) {
/* 372 */         localFileInputStream.close();
      }
    }
  }
  
  public static TrustManager[] getTrustManagerArray(String paramString1, String paramString2, String paramString3, String paramString4)
    throws IOException
  {
/* 394 */     FileInputStream localFileInputStream = null;
    
    try
    {
/* 403 */       KeyStore localKeyStore = KeyStore.getInstance(paramString3);
/* 404 */       localFileInputStream = new FileInputStream(paramString1);
      
/* 406 */       localKeyStore.load(localFileInputStream, paramString2.toCharArray());
/* 407 */       TrustManagerFactory localTrustManagerFactory = TrustManagerFactory.getInstance(paramString4);
      
/* 409 */       localTrustManagerFactory.init(localKeyStore);
/* 410 */       return localTrustManagerFactory.getTrustManagers();
    }
    catch (Exception localException)
    {
/* 415 */       throw ((NetException)new NetException(409).initCause(localException));
    }
    finally {
/* 418 */       if (localFileInputStream != null) {
/* 419 */         localFileInputStream.close();
      }
    }
  }
  
  public static String processWalletLocation(String paramString)
    throws NetException
  {
/* 440 */     String str1 = null;
    try {
/* 442 */       NVNavigator localNVNavigator = new NVNavigator();
/* 443 */       NVPair localNVPair1 = new NVFactory().createNVPair(paramString);
      
/* 445 */       NVPair localNVPair2 = localNVNavigator.findNVPair(localNVPair1, "METHOD");
/* 446 */       NVPair localNVPair3 = localNVNavigator.findNVPair(localNVPair1, "METHOD_DATA");
/* 447 */       NVPair localNVPair4 = localNVNavigator.findNVPair(localNVPair3, "DIRECTORY");
/* 448 */       str1 = localNVPair2.getAtom();
      
/* 454 */       if (str1.equalsIgnoreCase("FILE")) {
/* 455 */         return localNVPair4.getAtom();
      }
      
/* 459 */       throw new NetException(412, str1);
    }
    catch (Exception localException)
    {
/* 465 */       throw ((NetException)new NetException(407).initCause(localException));
    }
  }
  
  public static String getDefaultPropertiesString()
  {
/* 479 */     Object localObject1 = null;
/* 480 */     String str1 = null;
/* 481 */     Object localObject2 = null;
/* 482 */     String str2 = null;
/* 483 */     String str3 = null;
    
/* 487 */     str1 = System.getProperty("oracle.net.wallet_location");
    
/* 490 */     if (str1 != null) {
/* 491 */       localObject1 = str1;
    } else {
/* 493 */       localObject1 = System.getProperty("javax.net.ssl.keyStore", "") + "#" + System.getProperty("javax.net.ssl.keyStoreType", KeyStore.getDefaultType()) + "#" + System.getProperty("javax.net.ssl.keyStorePassword", "") + "#" + System.getProperty("javax.net.ssl.trustStore", "") + "#" + System.getProperty("javax.net.ssl.trustStoreType", KeyStore.getDefaultType()) + "#" + System.getProperty("javax.net.ssl.trustStorePassword", "");
    }
    
/* 508 */     if (str2 == null)
/* 509 */       str2 = KeyManagerFactory.getDefaultAlgorithm();
/* 510 */     if (str3 == null)
/* 511 */       str3 = TrustManagerFactory.getDefaultAlgorithm();
/* 512 */     return (String)localObject1 + "#" + str2 + "#" + str3;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/nt/CustomSSLSocketFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */