package oracle.net.ns;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
public class ClientProfile
  extends Properties
{
  private static final String profile_name = "ora-net-profile";
  private static final String shared_profile_name = "ora-shared-profile";
  
  public ClientProfile() {}
  
  public ClientProfile(Properties paramProperties)
  {
/*  81 */     if (paramProperties.containsKey("oracle.net.profile"))
    {
/*  83 */       put("oracle.net.profile", paramProperties.getProperty("oracle.net.profile"));
    }
    
/*  89 */     put("oracle.net.authentication_services", paramProperties.getProperty("oracle.net.authentication_services", "()"));
    
/*  92 */     put("oracle.net.encryption_client", paramProperties.getProperty("oracle.net.encryption_client", "ACCEPTED"));
    
/*  95 */     put("oracle.net.encryption_types_client", paramProperties.getProperty("oracle.net.encryption_types_client", "()"));
    
/*  98 */     put("oracle.net.crypto_checksum_client", paramProperties.getProperty("oracle.net.crypto_checksum_client", "ACCEPTED"));
    
/* 101 */     put("oracle.net.crypto_checksum_types_client", paramProperties.getProperty("oracle.net.crypto_checksum_types_client", "()"));
    
/* 104 */     put("oracle.net.crypto_seed", paramProperties.getProperty("oracle.net.crypto_seed", ""));
    
/* 108 */     put("oracle.net.kerberos5_mutual_authentication", paramProperties.getProperty("oracle.net.kerberos5_mutual_authentication", "false"));
    
/* 111 */     if (paramProperties.getProperty("oracle.net.kerberos5_cc_name") != null) {
/* 112 */       put("oracle.net.kerberos5_cc_name", paramProperties.getProperty("oracle.net.kerberos5_cc_name"));
    }
  }
  
  public String[] getAuthenticationServices()
  {
/* 127 */     return getServices((String)get("oracle.net.authentication_services"));
  }
  
  public String[] getEncryptionServices()
  {
/* 132 */     return getServices((String)get("oracle.net.encryption_types_client"));
  }
  
  public String[] getDataIntegrityServices()
  {
/* 137 */     return getServices((String)get("oracle.net.crypto_checksum_types_client"));
  }
  
  public String getEncryptionLevel()
  {
/* 142 */     return (String)get("oracle.net.encryption_client");
  }
  
  public int getEncryptionLevelNum()
  {
/* 149 */     return translateAnoValue(getEncryptionLevel());
  }
  
  public String getDataIntegrityLevel()
  {
/* 154 */     return (String)get("oracle.net.crypto_checksum_client");
  }
  
  public int getDataIntegrityLevelNum()
  {
/* 159 */     return translateAnoValue(getDataIntegrityLevel());
  }
  
  public void print()
  {
/* 167 */     System.out.println(" ----------------------------------------");
/* 168 */     System.out.println(" Displaying the content of ClientProfile ");
/* 169 */     System.out.println(" List:");
/* 170 */     list(System.out);
/* 171 */     Enumeration localEnumeration = propertyNames();
/* 172 */     System.out.println("Enumeration has elements ? " + localEnumeration.hasMoreElements());
/* 173 */     for (int i = 0; localEnumeration.hasMoreElements(); i++)
    {
/* 175 */       String str = (String)localEnumeration.nextElement();
/* 176 */       System.out.println("Key " + i + " = " + str);
/* 177 */       System.out.println("Value = " + getProperty(str));
    }
/* 179 */     System.out.println(" ----------------------------------------");
  }
  
  private String[] getServices(String paramString)
  {
/* 192 */     String str = removeParenths(paramString);
/* 193 */     StringTokenizer localStringTokenizer = new StringTokenizer(str, ",");
/* 194 */     int i = localStringTokenizer.countTokens();
/* 195 */     String[] arrayOfString = new String[i];
/* 196 */     for (int j = 0; j < i; j++) {
/* 197 */       arrayOfString[j] = localStringTokenizer.nextToken().trim();
    }
/* 199 */     return arrayOfString;
  }
  
  private String removeParenths(String paramString)
  {
/* 205 */     int i = paramString.indexOf('(');
/* 206 */     int j = i == -1 ? 0 : i + 1;
/* 207 */     int k = paramString.lastIndexOf(')');
/* 208 */     int m = k == -1 ? paramString.length() : k;
    
/* 210 */     String str = paramString.substring(j, m);
    
/* 212 */     return str.trim();
  }
  
  private int translateAnoValue(String paramString)
  {
/* 217 */     int i = 0;
/* 218 */     if (paramString.equalsIgnoreCase("ACCEPTED")) {
/* 219 */       i = 0;
/* 220 */     } else if (paramString.equalsIgnoreCase("REQUESTED")) {
/* 221 */       i = 2;
/* 222 */     } else if (paramString.equalsIgnoreCase("REQUIRED")) {
/* 223 */       i = 3;
/* 224 */     } else if (paramString.equalsIgnoreCase("REJECTED"))
/* 225 */       i = 1;
/* 226 */     return i;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/ClientProfile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */