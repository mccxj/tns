package oracle.jdbc.driver;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Locale;
import oracle.sql.OPAQUE;
public class ClassRef
{
/*  42 */   static final XMLTypeClassRef XMLTYPE = ;
/*  43 */   public static final Locale LOCALE = Locale.newInstance();
  
  private final String className;
  
  static final ClassRef newInstance(String paramString)
    throws ClassNotFoundException
  {
/*  53 */     return new ClassRef(paramString);
  }
  
  private ClassRef(String paramString)
    throws ClassNotFoundException
  {
/*  72 */     this.className = paramString;
  }
  
  Class get()
  {
    try
    {
/*  92 */       return Class.forName(this.className, true, Thread.currentThread().getContextClassLoader());
    }
    catch (ClassNotFoundException localClassNotFoundException) {
/*  95 */       NoClassDefFoundError localNoClassDefFoundError = new NoClassDefFoundError(localClassNotFoundException.getMessage());
/*  96 */       localNoClassDefFoundError.initCause(localClassNotFoundException);
/*  97 */       throw localNoClassDefFoundError;
    }
  }
  
  static class XMLTypeClassRef extends ClassRef
  {
    protected final Method CREATEXML;
    
    static final XMLTypeClassRef newInstance()
    {
      try
      {
/* 109 */         return new XMLTypeClassRef();
      }
      catch (ClassNotFoundException localClassNotFoundException) {}catch (NoClassDefFoundError localNoClassDefFoundError) {}
      
/* 113 */       return null;
    }
    
    private XMLTypeClassRef() throws ClassNotFoundException {
/* 117 */       super(null);
/* 118 */       Method localMethod = null;
      try {
/* 120 */         localMethod = get().getDeclaredMethod("createXML", new Class[] { OPAQUE.class });
      }
      catch (NoSuchMethodException localNoSuchMethodException) {}
/* 123 */       this.CREATEXML = localMethod;
    }
    
    OPAQUE createXML(OPAQUE paramOPAQUE) throws SQLException
    {
/* 128 */       get();
      try {
/* 130 */         return (OPAQUE)this.CREATEXML.invoke(null, new Object[] { paramOPAQUE });
      }
      catch (IllegalAccessException localIllegalAccessException) {}catch (InvocationTargetException localInvocationTargetException) {}
      
/* 134 */       return null;
    }
  }
  
  public static class LocaleCategoryClassRef
    extends ClassRef
  {
    static final LocaleCategoryClassRef newInstance()
    {
      try
      {
/* 149 */         return new LocaleCategoryClassRef();
      }
      catch (ClassNotFoundException localClassNotFoundException) {}catch (NoClassDefFoundError localNoClassDefFoundError) {}
      
/* 153 */       return null;
    }
    
    private LocaleCategoryClassRef() throws ClassNotFoundException {
/* 157 */       super(null);
    }
  }
  
  public static class Locale
  {
    protected final Method localeJDK7getDefault;
    
    protected final Object localeCategoryEnumFORMAT;
    
    static final Locale newInstance()
    {
      try
      {
/* 174 */         return new Locale();
      }
      catch (Exception localException) {}
/* 177 */       return null;
    }
    
    private Locale()
    {
/* 182 */       ClassRef.LocaleCategoryClassRef localLocaleCategoryClassRef = ClassRef.LocaleCategoryClassRef.newInstance();
/* 183 */       Method localMethod = null;
/* 184 */       Object localObject1 = null;
      
/* 186 */       if (localLocaleCategoryClassRef != null)
      {
        try
        {
/* 191 */           Object[] arrayOfObject1 = localLocaleCategoryClassRef.get().getEnumConstants();
/* 192 */           for (Object localObject2 : arrayOfObject1) if (((Enum)localObject2).name() == "FORMAT") { localObject1 = localObject2; break; }
/* 193 */           localMethod = Locale.class.getDeclaredMethod("getDefault", new Class[] { localLocaleCategoryClassRef.get() });
        }
        catch (Throwable localThrowable) {
/* 196 */           localMethod = null;
/* 197 */           localObject1 = null;
        }
      }
/* 200 */       this.localeJDK7getDefault = localMethod;
/* 201 */       this.localeCategoryEnumFORMAT = localObject1;
    }
    
    public Locale getDefault() {
      try {
/* 206 */         if (this.localeJDK7getDefault == null) {
/* 207 */           return Locale.getDefault();
        }
/* 209 */         return (Locale)this.localeJDK7getDefault.invoke(null, new Object[] { this.localeCategoryEnumFORMAT });
      }
      catch (IllegalAccessException localIllegalAccessException) {}catch (InvocationTargetException localInvocationTargetException) {}
      
/* 213 */       return null;
    }
  }
  
/* 218 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/ClassRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */