package oracle.jdbc.proxy;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
class GeneratedProxiesRegistry
{
  static class Key
  {
/*  35 */     private final String PREFIX = "oracle.jdbc.proxy.";
/*  36 */     private final String SUFFIX = "$$$Proxy";
    
    private final Class iface;
    
    private final Class superclass;
    
    Key(Class paramClass1, Class paramClass2)
    {
/*  46 */       this.iface = paramClass1;
/*  47 */       this.superclass = paramClass2;
    }
    
    Key(String paramString)
    {
/*  52 */       this.iface = parseIface(paramString);
/*  53 */       this.superclass = parseSuperclass(paramString);
    }
    
    public boolean equals(Object paramObject)
    {
/*  58 */       if (null == paramObject) {
/*  59 */         return false;
      }
      try
      {
/*  63 */         Key localKey = (Key)paramObject;
/*  64 */         return (this.iface.equals(localKey.iface)) && (this.superclass.equals(localKey.superclass));
      }
      catch (ClassCastException localClassCastException) {}
      
/*  68 */       return false;
    }
    
/*  71 */     private Integer hashCode = null;
    
    public int hashCode()
    {
/*  75 */       if (null == this.hashCode)
      {
/*  77 */         this.hashCode = Integer.valueOf(23);
/*  78 */         this.hashCode = Integer.valueOf(HashUtil.hash(this.hashCode.intValue(), this.iface));
/*  79 */         this.hashCode = Integer.valueOf(HashUtil.hash(this.hashCode.intValue(), this.superclass));
      }
      
/*  82 */       return this.hashCode.intValue();
    }
    
    public String toString()
    {
/*  87 */       return "oracle.jdbc.proxy." + this.superclass.getName().replace(".", "$1") + "$2" + this.iface.getName().replace(".", "$1") + "$$$Proxy";
    }
    
    private Class parseSuperclass(String paramString)
    {
      try
      {
/*  98 */         String str1 = paramString.substring("oracle.jdbc.proxy.".length());
/*  99 */         String str2 = str1.replaceAll("\\$1", ".");
/* 100 */         String str3 = str2.substring(0, str2.indexOf("$2"));
/* 101 */         return Class.forName(str3);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
/* 105 */         throw new RuntimeException(localClassNotFoundException);
      }
    }
    
    private Class parseIface(String paramString)
    {
      try
      {
/* 113 */         String str1 = paramString.substring("oracle.jdbc.proxy.".length());
/* 114 */         String str2 = str1.replaceAll("\\$1", ".");
/* 115 */         String str3 = str2.substring(str2.indexOf("$2") + 2, str2.indexOf("$$$Proxy"));
/* 116 */         return Class.forName(str3);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
/* 120 */         throw new RuntimeException(localClassNotFoundException);
      }
    }
    
    public Class getIface()
    {
/* 129 */       return this.iface;
    }
    
    public Class getSuperclass()
    {
/* 137 */       return this.superclass;
    }
    
    public String makePathname()
    {
/* 142 */       return toString().replace(".", "/") + ".class";
    }
  }
  
  static class Value
  {
    private final String name;
    
    private final String source;
    
    private final Class clazz;
    
    private final Constructor constructor;
    
    Value(String paramString1, String paramString2, Class paramClass, Constructor paramConstructor)
    {
/* 170 */       this.name = paramString1;
/* 171 */       this.source = paramString2;
/* 172 */       this.clazz = paramClass;
/* 173 */       this.constructor = paramConstructor;
    }
    
    String getName()
    {
/* 182 */       return this.name;
    }
    
    String getSource()
    {
/* 191 */       return this.source;
    }
    
    Class getClazz()
    {
/* 200 */       return this.clazz;
    }
    
    Constructor getConstructor()
    {
/* 209 */       return this.constructor;
    }
  }
  
/* 213 */   private Map<Key, Value> registry = Collections.synchronizedMap(new HashMap());
  
  void put(Class paramClass1, Class paramClass2, Value paramValue)
  {
/* 223 */     this.registry.put(new Key(paramClass1, paramClass2), paramValue);
  }
  
  Value get(Class paramClass1, Class paramClass2)
  {
/* 234 */     return (Value)this.registry.get(new Key(paramClass1, paramClass2));
  }
  
  int size()
  {
/* 243 */     return this.registry.size();
  }
  
  Set<Key> keySet()
  {
/* 252 */     return this.registry.keySet();
  }
  
  Collection<Value> values()
  {
/* 261 */     return this.registry.values();
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/GeneratedProxiesRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */