package oracle.net.resolver;
import java.util.HashMap;
import oracle.net.ns.NetException;
public class NameResolverFactory
{
/*  57 */   private static HashMap resolverMap = new HashMap();
  
  private static final String TNS_ADMIN_PROPERTY = "oracle.net.tns_admin";
  
  private static final boolean DEBUG = false;
  
  public static NameResolver getNameResolver(String paramString1, String paramString2, String paramString3)
    throws NetException
  {
/*  80 */     if (paramString1 != null) {
/*  81 */       paramString1 = paramString1.trim();
    }
    
/*  84 */     if ((paramString1 != null) && (paramString1.length() == 0)) {
/*  85 */       throw new NetException(119);
    }
    
/*  90 */     synchronized (NameResolverFactory.class)
    {
/*  92 */       NameResolver localNameResolver = (NameResolver)resolverMap.get(paramString1);
/*  93 */       if (localNameResolver == null)
      {
/*  95 */         localNameResolver = new NameResolver(paramString1, paramString2, paramString3);
/*  96 */         resolverMap.put(paramString1, localNameResolver);
      }
      
/* 105 */       return localNameResolver;
    }
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/resolver/NameResolverFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */