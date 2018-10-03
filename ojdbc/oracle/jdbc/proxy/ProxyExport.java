package oracle.jdbc.proxy;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
public class ProxyExport
{
  public static void main(String[] paramArrayOfString)
    throws ClassNotFoundException, IOException
  {
/*  42 */     if (0 == paramArrayOfString.length)
    {
/*  44 */       System.out.println("Usage:");
/*  45 */       System.out.println("java -classpath ojdbc6.jar oracle.jdbc.proxy.ProxyExport [-d dir] class1 class2 class3 ...");
/*  46 */       System.out.println("  dir - directory to store exported proxy classes");
/*  47 */       System.out.println("  class1 class2 class3 ... - superclasses equipped with @ProxyFor annotation");
/*  48 */       return;
    }
    
/*  51 */     int i = 0;
/*  52 */     String str1 = "";
    
/*  54 */     if ("-d".equals(paramArrayOfString[0]))
    {
/*  56 */       if (paramArrayOfString.length < 2)
      {
/*  58 */         System.out.println("wrong directory");
/*  59 */         return;
      }
      
/*  62 */       str1 = paramArrayOfString[1];
/*  63 */       i = 2;
      
/*  65 */       if (!new File(str1).exists())
      {
/*  67 */         System.out.println("target directory does not exist");
/*  68 */         return;
      }
      
/*  71 */       if ((0 != str1.length()) && (!str1.endsWith(File.separator))) {
/*  72 */         str1 = str1 + File.separator;
      }
    }
/*  75 */     ArrayList localArrayList = new ArrayList();
/*  76 */     for (; i < paramArrayOfString.length; i++) {
/*  77 */       localArrayList.add(Class.forName(paramArrayOfString[i]));
    }
/*  79 */     ProxyFactory localProxyFactory = ProxyFactory.createProxyFactory((Class[])localArrayList.toArray(new Class[0]));
    
/*  82 */     AnnotationsRegistry localAnnotationsRegistry = localProxyFactory.annotationsRegistry;
    
/*  85 */     for (AnnotationsRegistry.Value localValue : localAnnotationsRegistry.values())
    {
/*  87 */       localClass1 = localValue.getSuperclass();
/*  88 */       for (Class localClass2 : localValue.getIfacesToProxy())
      {
/*  90 */         GeneratedProxiesRegistry.Key localKey = new GeneratedProxiesRegistry.Key(localClass2, localClass1);
        
/*  93 */         byte[] arrayOfByte = ClassGenerator.generateBytecode(localKey, localProxyFactory.annotationsRegistry);
        
/*  97 */         String str2 = localKey.makePathname();
        
/*  99 */         int j = str2.lastIndexOf(File.separator);
/* 100 */         if (-1 != j)
        {
/* 102 */           localObject = str2.substring(0, j);
/* 103 */           new File(str1 + (String)localObject).mkdirs();
        }
        
/* 106 */         Object localObject = new BufferedOutputStream(new FileOutputStream(str1 + str2));
        
/* 110 */         ((BufferedOutputStream)localObject).write(arrayOfByte);
/* 111 */         ((BufferedOutputStream)localObject).close();
      }
    }
    Class localClass1;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/ProxyExport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */