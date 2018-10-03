package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.util.Arrays;
class MethodSignature
{
  private final String name;
  private final Class[] parameterTypes;
  private final Class returnType;
  
  MethodSignature(Method paramMethod)
  {
/*  41 */     this.name = paramMethod.getName();
/*  42 */     this.parameterTypes = paramMethod.getParameterTypes();
/*  43 */     this.returnType = paramMethod.getReturnType();
  }
  
  MethodSignature(String paramString, Class[] paramArrayOfClass, Class paramClass)
  {
/*  57 */     this.name = paramString;
/*  58 */     this.parameterTypes = paramArrayOfClass;
/*  59 */     this.returnType = paramClass;
  }
  
  public boolean equals(Object paramObject)
  {
/*  64 */     if (null == paramObject) {
/*  65 */       return false;
    }
/*  67 */     if (!(paramObject instanceof MethodSignature)) {
/*  68 */       return false;
    }
/*  70 */     MethodSignature localMethodSignature = (MethodSignature)paramObject;
/*  71 */     if (this == localMethodSignature) {
/*  72 */       return true;
    }
/*  74 */     if (!this.name.equals(localMethodSignature.name)) {
/*  75 */       return false;
    }
/*  77 */     if (!Arrays.deepEquals(this.parameterTypes, localMethodSignature.parameterTypes)) {
/*  78 */       return false;
    }
/*  80 */     if ((null != this.returnType) && (null != localMethodSignature.returnType) && (!this.returnType.equals(localMethodSignature.returnType)))
    {
/*  83 */       throw new RuntimeException("methods \"" + this.name + "\" have the same signature \"" + this.parameterTypes + "\" but different return types: \"" + this.returnType + "\" and \"" + localMethodSignature.returnType + '"');
    }
    
/*  89 */     return true;
  }
  
/*  92 */   private Integer hashCode = null;
  
  public int hashCode()
  {
/*  96 */     if (null == this.hashCode)
    {
/*  98 */       this.hashCode = new Integer(23);
/*  99 */       this.hashCode = Integer.valueOf(HashUtil.hash(this.hashCode.intValue(), this.name));
/* 100 */       this.hashCode = Integer.valueOf(HashUtil.hash(this.hashCode.intValue(), this.parameterTypes));
    }
/* 102 */     return this.hashCode.intValue();
  }
  
  String getName()
  {
/* 110 */     return this.name;
  }
  
  Class[] getParameterTypes()
  {
/* 118 */     return this.parameterTypes;
  }
  
  Class getReturnType()
  {
/* 126 */     return this.returnType;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/MethodSignature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */