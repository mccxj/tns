package oracle.net.jndi;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import oracle.net.ns.NetException;
public class JndiAttrs
{
  private Properties env;
  private static final String nFactory = "java.naming.factory.initial";
  private static final String nProvider = "java.naming.provider.url";
  private static final String nProfile = "ora-net-profile";
  private static final String default_nFactory = "com.sun.jndi.ldap.LdapCtxFactory";
  DirContext ctx;
  
  public JndiAttrs(Properties paramProperties)
    throws NetException
  {
/*  72 */     String str = null;
    
/*  74 */     this.env = new Properties();
    
/*  76 */     if (paramProperties.containsKey("java.naming.factory.initial")) {
/*  77 */       this.env.put("java.naming.factory.initial", paramProperties.getProperty("java.naming.factory.initial"));
    } else {
/*  79 */       this.env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
    }
/*  81 */     if (paramProperties.containsKey("java.naming.provider.url"))
    {
/*  83 */       str = paramProperties.getProperty("java.naming.provider.url");
      
/*  87 */       if (str.startsWith("ldaps"))
      {
/*  89 */         this.env.put("java.naming.ldap.factory.socket", "oracle.net.jndi.CustomSSLSocketFactory");
        
/*  91 */         this.env.put("java.naming.security.protocol", "ssl");
/*  92 */         str = "ldap:" + str.substring(6);
      }
/*  94 */       this.env.put("java.naming.provider.url", str);
    }
    
/*  97 */     if (paramProperties.containsKey("oracle.net.profile")) {
/*  98 */       this.env.put("ora-net-profile", paramProperties.getProperty("oracle.net.profile"));
    }
    try {
/* 101 */       Class localClass1 = Class.forName("javax.naming.directory.InitialDirContext");
      
/* 105 */       if (this.env.get("java.naming.factory.initial") == null)
      {
/* 107 */         this.env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
      }
      
/* 110 */       if (this.env.get("java.naming.provider.url") == null) {
/* 111 */         this.env.put("java.naming.provider.url", str);
      }
    }
    catch (Exception localException1) {
/* 115 */       throw new NetException(110, localException1.toString());
    }
    
    Object localObject;
    
/* 127 */     if ((localObject = paramProperties.get("java.naming.security.authentication")) != null)
    {
/* 129 */       this.env.put("java.naming.security.authentication", localObject);
    }
    
/* 132 */     if ((localObject = paramProperties.get("java.naming.security.principal")) != null)
    {
/* 134 */       this.env.put("java.naming.security.principal", localObject);
    }
    
/* 137 */     if ((localObject = paramProperties.get("java.naming.security.credentials")) != null)
    {
/* 139 */       this.env.put("java.naming.security.credentials", localObject);
    }
    
/* 142 */     for (int i = 0; i < 3; i++) {
      try
      {
/* 145 */         this.ctx = new InitialDirContext(this.env);
      }
      catch (NamingException localNamingException)
      {
/* 149 */         Class localClass2 = null;
        try {
/* 151 */           localClass2 = Class.forName("javax.net.ssl.SSLException");
        }
        catch (Exception localException2)
        {
/* 155 */           throw new NetException(108, localNamingException.toString());
        }
        
/* 158 */         if ((!localClass2.isInstance(localNamingException.getRootCause())) || (i >= 3))
        {
/* 161 */           throw new NetException(108, localNamingException.toString());
        }
      }
    }
  }
  
  public Vector getProfileAttrs(String paramString)
    throws NetException
  {
/* 177 */     String str = "cn=";
/* 178 */     Attributes localAttributes = null;
    try {
/* 180 */       str = str.concat(paramString);
/* 181 */       localAttributes = this.ctx.getAttributes(str);
    } catch (NamingException localNamingException) {
/* 183 */       throw new NetException(108, localNamingException.toString());
    }
    
/* 187 */     return setAttrs(localAttributes);
  }
  
  public Vector getAttrs(String paramString, String[] paramArrayOfString)
    throws NetException
  {
/* 200 */     String str = "cn=";
/* 201 */     Attributes localAttributes = null;
    try {
/* 203 */       if (!paramString.startsWith(str)) {
/* 204 */         str = str.concat(paramString);
      } else
/* 206 */         str = paramString;
/* 207 */       localAttributes = this.ctx.getAttributes(str, paramArrayOfString);
    }
    catch (NamingException localNamingException) {
/* 210 */       throw new NetException(108, localNamingException.toString());
    }
    
/* 214 */     return setAttrs(localAttributes);
  }
  
  private Vector setAttrs(Attributes paramAttributes)
    throws NetException
  {
/* 223 */     Vector localVector = new Vector(1, 1);
    
/* 225 */     if (paramAttributes == null) {
/* 226 */       System.out.println("No attributes");
    }
    else {
      try
      {
/* 231 */         NamingEnumeration localNamingEnumeration1 = paramAttributes.getAll();
/* 232 */         while ((localNamingEnumeration1 != null) && (localNamingEnumeration1.hasMoreElements())) {
/* 233 */           Attribute localAttribute = (Attribute)localNamingEnumeration1.next();
          
/* 235 */           NamingEnumeration localNamingEnumeration2 = localAttribute.getAll();
/* 236 */           while (localNamingEnumeration2.hasMoreElements()) {
/* 237 */             localVector.addElement(localNamingEnumeration2.nextElement());
          }
        }
      } catch (NamingException localNamingException) {
/* 241 */         throw new NetException(108, localNamingException.toString());
      }
    }
    
/* 245 */     return localVector;
  }
  
  public void setEnv(String paramString1, String paramString2)
  {
/* 254 */     this.env.put(paramString1, paramString2);
  }
  
  public void close()
    throws NetException
  {
    try
    {
/* 264 */       if (null != this.ctx) {
/* 265 */         this.ctx.close();
      }
    } catch (NamingException localNamingException) {
/* 268 */       throw new NetException(108, localNamingException.toString());
    }
  }
  
  public final String getLdapUrlUsed()
    throws NetException
  {
/* 279 */     String str = null;
    try
    {
/* 282 */       str = (String)this.ctx.getEnvironment().get("java.naming.provider.url");
    }
    catch (NamingException localNamingException)
    {
/* 286 */       throw new NetException(108, localNamingException.toString());
    }
    
/* 289 */     return str;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/jndi/JndiAttrs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */