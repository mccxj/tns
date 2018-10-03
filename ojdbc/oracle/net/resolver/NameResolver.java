package oracle.net.resolver;
import java.util.ArrayList;
import java.util.Hashtable;
import oracle.net.jdbc.nl.InvalidSyntaxException;
import oracle.net.jdbc.nl.NLException;
import oracle.net.jdbc.nl.NVFactory;
import oracle.net.jdbc.nl.NVPair;
import oracle.net.ns.NetException;
public class NameResolver
{
  private String tnsAdmin;
  private String[] readPath;
  private Hashtable adapterHash;
  String osuser;
  String programName;
  private static final boolean DEBUG = false;
/*  73 */   private static final String[] DEFAULT_SEARCH_PATH = { "TNSNAMES", "HOSTNAME" };
  
  private static final String TNS_ADMIN_PROPERTY = "oracle.net.tns_admin";
  
  private static final String READ_PATH_PROPERTY = "oracle.net.names.directory_path";
  
  private NameResolver() {}
  
  protected NameResolver(String paramString1, String paramString2, String paramString3)
    throws NetException
  {
/*  93 */     this.tnsAdmin = paramString1;
/*  94 */     bootNameResolver();
/*  95 */     this.osuser = paramString2;
/*  96 */     this.programName = paramString3;
  }
  
  public String resolveName(String paramString)
    throws NetException
  {
/* 114 */     if (paramString == null) {
/* 115 */       throw new NetException(120);
    }
/* 117 */     String str1 = paramString.trim();
/* 118 */     if (str1.length() == 0) {
/* 119 */       throw new NetException(120);
    }
/* 121 */     String str2 = null;
    
/* 130 */     if (this.tnsAdmin == null)
    {
/* 132 */       localObject = new HostnameNamingAdapter(this.osuser, this.programName);
/* 133 */       str2 = ((HostnameNamingAdapter)localObject).resolve(str1);
/* 134 */       return str2;
    }
    
/* 148 */     Object localObject = null;
    
/* 150 */     for (int i = 0; (str2 == null) && (i < this.readPath.length); 
/* 151 */         i++)
    {
/* 160 */       localObject = (NamingAdapterInterface)this.adapterHash.get(this.readPath[i]);
      try
      {
/* 163 */         str2 = ((NamingAdapterInterface)localObject).resolve(str1);
      }
      catch (NetException localNetException) {}
    }
    
/* 179 */     if (str2 == null)
    {
/* 181 */       throw new NetException(122, "\"" + paramString + "\"");
    }
    
/* 188 */     return str2;
  }
  
  private void addAdapters()
  {
/* 196 */     if (this.adapterHash == null) {
/* 197 */       this.adapterHash = new Hashtable();
    }
/* 199 */     this.adapterHash.put("TNSNAMES", new TNSNamesNamingAdapter(this.tnsAdmin));
/* 200 */     this.adapterHash.put("HOSTNAME", new HostnameNamingAdapter(this.osuser, this.programName));
  }
  
  private void bootNameResolver()
    throws NetException
  {
/* 216 */     if (this.tnsAdmin != null)
    {
/* 218 */       setReadPath();
/* 219 */       addAdapters();
    }
  }
  
  private boolean checkForValidAdapter(String paramString)
  {
/* 229 */     int j = DEFAULT_SEARCH_PATH.length;
    
/* 231 */     for (int i = 0; i < j; i++)
    {
/* 233 */       if (DEFAULT_SEARCH_PATH[i].equalsIgnoreCase(paramString))
/* 234 */         return true;
    }
/* 236 */     return false;
  }
  
  private void setDefaultPath()
  {
/* 243 */     this.readPath = DEFAULT_SEARCH_PATH;
  }
  
  private void setReadPath()
    throws NetException
  {
/* 252 */     int i = 0;
    
/* 259 */     String[] arrayOfString = getUserReadPath();
    
/* 261 */     if (arrayOfString == null)
    {
/* 267 */       setDefaultPath();
/* 268 */       return;
    }
    
/* 271 */     ArrayList localArrayList = new ArrayList();
    
/* 273 */     for (i = 0; i < arrayOfString.length; i++)
    {
/* 275 */       if (checkForValidAdapter(arrayOfString[i]) == true)
      {
/* 277 */         String str = arrayOfString[i];
/* 278 */         str = str.toUpperCase();
/* 279 */         if (!localArrayList.contains(str)) {
/* 280 */           localArrayList.add(arrayOfString[i].toUpperCase());
        }
      }
    }
    
/* 294 */     int j = localArrayList.size();
    
/* 297 */     if (j == 0)
    {
/* 306 */       throw new NetException(121, " The Read path did not contain any valid adapters.");
    }
    
/* 310 */     this.readPath = new String[j];
/* 311 */     localArrayList.toArray(this.readPath);
  }
  
  private String[] getUserReadPath()
    throws NetException
  {
/* 328 */     Object localObject = null;
    
/* 331 */     String str1 = System.getProperty("oracle.net.names.directory_path");
    
/* 333 */     if (str1 == null)
    {
/* 339 */       return null;
    }
    
/* 350 */     str1 = str1.trim();
/* 351 */     if (str1.length() == 0) {
/* 352 */       return null;
    }
/* 354 */     StringBuffer localStringBuffer = new StringBuffer(str1);
    
/* 359 */     if (localStringBuffer.charAt(0) == '(') {
/* 360 */       localStringBuffer.insert(0, "(path=").append(')');
    } else {
/* 362 */       localStringBuffer.insert(0, "(path=(").append("))");
    }
/* 364 */     String str2 = localStringBuffer.toString();
    
/* 366 */     NVFactory localNVFactory = new NVFactory();
/* 367 */     NVPair localNVPair = null;
    
    try
    {
/* 374 */       localNVPair = localNVFactory.createNVPair(str2);
/* 375 */       if ((localNVPair.getRHSType() != NVPair.RHS_LIST) || (localNVPair.getListType() != NVPair.LIST_COMMASEP))
      {
/* 378 */         throw new NetException(121, " Read path specified is " + str1);
      }
      
/* 382 */       int i = localNVPair.getListSize();
/* 383 */       String[] arrayOfString = new String[i];
      
/* 386 */       for (int j = 0; j < i; j++)
      {
/* 388 */         arrayOfString[j] = localNVPair.getListElement(j).getName();
      }
/* 390 */       localObject = arrayOfString;
    }
    catch (InvalidSyntaxException localInvalidSyntaxException)
    {
/* 402 */       throw new NetException(121, " Read path specified is " + str1);
    }
    catch (NLException localNLException)
    {
/* 411 */       throw new NetException(121, " Read path specified is " + str2);
    }
    
/* 414 */     return (String[])localObject;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/resolver/NameResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */