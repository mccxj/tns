package oracle.net.nt;
import java.io.IOException;
import javax.net.ssl.SSLSocket;
import oracle.net.jdbc.nl.NLException;
import oracle.net.jdbc.nl.NVFactory;
import oracle.net.jdbc.nl.NVPair;
import oracle.net.ns.NetException;
public class TcpsConfigure
{
  static final boolean DEBUG = false;
/*  58 */   public static final String[] VALID_SSL_VERSION_STRINGS = { "0", "undetermined", "2", "2.0", "version 2", "3", "3.0", "version 3 only", "1", "1.0", "version 1 only", "1 or 3", "1.0 or 3.0", "version 1 or version 3" };
  
/*  79 */   public static final String[][] TABLE_ENABLED_SSL_PROTOCOLS = { { "TLSv1", "SSLv3", "SSLv2Hello" }, { "SSLv2Hello" }, { "SSLv3" }, { "TLSv1" }, { "TLSv1", "SSLv3" } };
  
/*  91 */   public static final int[] VALID_SSL_STRING_TO_PROTOCOLS_MAP = { 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
  
  public static void configureVersion(SSLSocket paramSSLSocket, String paramString)
    throws NetException, IOException
  {
/* 135 */     if (paramString == null) {
/* 136 */       paramString = System.getProperty("oracle.net.ssl_version");
    }
    
/* 141 */     int i = 0;
/* 142 */     if (paramString != null)
    {
/* 146 */       if ((paramString.startsWith("(")) && (paramString.endsWith(")"))) {
/* 147 */         paramString = "(ssl_version=" + paramString.substring(1);
      } else
/* 149 */         paramString = "(ssl_version=" + paramString + ")";
      try {
/* 151 */         NVPair localNVPair = new NVFactory().createNVPair(paramString);
/* 152 */         String str = localNVPair.getAtom();
        
/* 155 */         for (int j = 0; j < VALID_SSL_VERSION_STRINGS.length; j++)
        {
/* 157 */           if (str.equalsIgnoreCase(VALID_SSL_VERSION_STRINGS[j]))
          {
/* 159 */             i = j;
/* 160 */             break;
          }
        }
      }
      catch (NLException localNLException)
      {
/* 166 */         throw ((NetException)new NetException(400, paramString).initCause(localNLException));
      }
    }
    
/* 170 */     if (i >= VALID_SSL_VERSION_STRINGS.length) {
/* 171 */       throw new NetException(400);
    }
/* 173 */     String[] arrayOfString = TABLE_ENABLED_SSL_PROTOCOLS[VALID_SSL_STRING_TO_PROTOCOLS_MAP[i]];
    
    try
    {
/* 177 */       paramSSLSocket.setEnabledProtocols(arrayOfString);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
/* 181 */       throw ((NetException)new NetException(401).initCause(localIllegalArgumentException));
    }
  }
  
  public static void configureCipherSuites(SSLSocket paramSSLSocket, String paramString)
    throws NetException, IOException
  {
/* 207 */     if (paramString == null)
/* 208 */       paramString = System.getProperty("oracle.net.ssl_cipher_suites");
/* 209 */     if (paramString == null) {
/* 210 */       return;
    }
    
/* 215 */     if ((paramString.startsWith("(")) && (paramString.endsWith(")"))) {
/* 216 */       paramString = "(cipher_suites=" + paramString + ")";
    } else
/* 218 */       paramString = "(cipher_suites=(" + paramString + "))";
    try {
/* 220 */       NVPair localNVPair = new NVFactory().createNVPair(paramString);
/* 221 */       String[] arrayOfString = new String[localNVPair.getListSize()];
/* 222 */       if ((localNVPair.getRHSType() == NVPair.LIST_COMMASEP) || (localNVPair.getRHSType() == NVPair.RHS_LIST))
      {
/* 227 */         for (int i = 0; i < localNVPair.getListSize(); i++) {
/* 228 */           arrayOfString[i] = localNVPair.getListElement(i).getName();
        }
        
      } else {
/* 232 */         throw new NetException(403, paramString);
      }
      
/* 235 */       paramSSLSocket.setEnabledCipherSuites(arrayOfString);
    }
    catch (NLException localNLException)
    {
/* 239 */       throw new NetException(403, paramString);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
/* 245 */       throw ((NetException)new NetException(404).initCause(localIllegalArgumentException));
    }
  }
  
  public static boolean matchServerDN(String paramString1, String paramString2, boolean paramBoolean)
  {
/* 264 */     paramString1 = normalizeDN(paramString1);
/* 265 */     if (paramString1 == null)
/* 266 */       return false;
/* 267 */     if (paramBoolean)
    {
/* 269 */       paramString2 = normalizeDN(paramString2);
/* 270 */       if (paramString2 == null)
/* 271 */         return false;
/* 272 */       if (paramString2.equals(paramString1)) {
/* 273 */         return true;
      }
      
/* 276 */       paramString1 = reverseDN(paramString1);
/* 277 */       if (paramString2.equals(paramString1)) {
/* 278 */         return true;
      }
/* 280 */       return false;
    }
/* 282 */     int i = paramString1.indexOf("CN=");
/* 283 */     if (i != -1)
    {
/* 285 */       if (paramString1.indexOf(',', i) != -1) {
/* 286 */         paramString1 = paramString1.substring(i, paramString1.indexOf(',', i));
      }
      else
/* 289 */         paramString1 = paramString1.substring(i);
/* 290 */       if (paramString2.equals(paramString1))
/* 291 */         return true;
    }
/* 293 */     return false;
  }
  
  public static String normalizeDN(String paramString)
  {
/* 312 */     StringBuffer localStringBuffer = new StringBuffer();
/* 313 */     String str1 = null;
/* 314 */     String str2 = null;
/* 315 */     int i = 0;
/* 316 */     int j = 0;
/* 317 */     paramString = paramString.trim();
    
/* 321 */     while ((i = paramString.indexOf('=', i)) != -1)
    {
/* 323 */       str1 = paramString.substring(j, i);
/* 324 */       str1 = str1.trim();
/* 325 */       localStringBuffer.append(str1.toUpperCase());
/* 326 */       localStringBuffer.append('=');
/* 327 */       j = i;
/* 328 */       if (j >= paramString.length() - 1)
/* 329 */         return null;
/* 330 */       i = paramString.indexOf(',', i);
/* 331 */       if (i == -1)
      {
/* 333 */         str2 = paramString.substring(j + 1);
/* 334 */         localStringBuffer.append(str2.trim());
/* 335 */         break;
      }
/* 337 */       str2 = paramString.substring(j + 1, i);
/* 338 */       localStringBuffer.append(str2.trim());
/* 339 */       localStringBuffer.append(',');
/* 340 */       if (i >= paramString.length() - 1) {
/* 341 */         return null;
      }
/* 343 */       j = i + 1;
    }
/* 345 */     return localStringBuffer.toString();
  }
  
  public static String reverseDN(String paramString)
  {
/* 362 */     StringBuffer localStringBuffer = new StringBuffer();
/* 363 */     String str = null;
/* 364 */     int i = paramString.length();
/* 365 */     int j = i;
    do
    {
/* 368 */       i = paramString.lastIndexOf(',', i);
/* 369 */       if (i == -1) {
/* 370 */         localStringBuffer.append(paramString.substring(0, j));
/* 371 */         break;
      }
/* 373 */       str = paramString.substring(i + 1, j);
/* 374 */       localStringBuffer.append(str);
/* 375 */       localStringBuffer.append(',');
/* 376 */       j = i;
/* 377 */       i--;
/* 378 */     } while (i != -1);
/* 379 */     return localStringBuffer.toString();
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/nt/TcpsConfigure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */