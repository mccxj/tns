package oracle.jdbc.proxy;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import oracle.jdbc.proxy.annotation.ProxyFor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
class ClassGenerator
{
  private final AnnotationsForIface annotationsForIface;
  private final String proxyName;
  private final String superclassName;
  private final String ifaceName;
  private final String proxyType;
  private final String ifaceType;
  final Map<MethodSignature, MethodGenerator> members;
  
  static class AnnotationsForIface
  {
    private final AnnotationsRegistry registry;
    private final Class iface;
    private final AnnotationsRegistry.Value value;
    
    AnnotationsForIface(AnnotationsRegistry paramAnnotationsRegistry, Class paramClass, AnnotationsRegistry.Value paramValue)
    {
/*  80 */       this.registry = paramAnnotationsRegistry;
/*  81 */       this.iface = paramClass;
/*  82 */       this.value = paramValue;
    }
    
    AnnotationsRegistry getRegistry()
    {
/*  90 */       return this.registry;
    }
    
    Class getIface()
    {
/*  98 */       return this.iface;
    }
    
    AnnotationsRegistry.Value getValue()
    {
/* 106 */       return this.value;
    }
  }
  
  private ClassGenerator(AnnotationsForIface paramAnnotationsForIface, String paramString1, String paramString2, String paramString3, Map<MethodSignature, MethodGenerator> paramMap)
  {
/* 122 */     this.annotationsForIface = paramAnnotationsForIface;
/* 123 */     this.proxyName = Utils.makeSlashed(paramString1);
/* 124 */     this.proxyType = Utils.makeType(paramString1);
/* 125 */     this.superclassName = Utils.makeSlashed(paramString2);
/* 126 */     this.ifaceName = Utils.makeSlashed(paramString3);
/* 127 */     this.ifaceType = Utils.makeType(paramString3);
/* 128 */     this.members = paramMap;
  }
  
  String getProxyName()
  {
/* 136 */     return this.proxyName;
  }
  
  String getSuperclassName()
  {
/* 144 */     return this.superclassName;
  }
  
  String getIfaceName()
  {
/* 152 */     return this.ifaceName;
  }
  
  String getProxyType()
  {
/* 160 */     return this.proxyType;
  }
  
  String getIfaceType()
  {
/* 168 */     return this.ifaceType;
  }
  
  static <T> byte[] generateBytecode(GeneratedProxiesRegistry.Key paramKey, AnnotationsRegistry paramAnnotationsRegistry)
  {
/* 175 */     Class localClass1 = paramKey.getIface();
/* 176 */     Class localClass2 = paramKey.getSuperclass();
    
/* 178 */     if (!localClass1.isInterface()) {
/* 179 */       new RuntimeException("iface must be interface");
    }
/* 181 */     if (localClass2.isInterface()) {
/* 182 */       new RuntimeException("sclass must not be interface");
    }
/* 184 */     HashMap localHashMap1 = new HashMap();
    
/* 187 */     final HashMap localHashMap2 = new HashMap();
    
/* 190 */     AnnotationsForIface localAnnotationsForIface = new AnnotationsForIface(paramAnnotationsRegistry, localClass1, paramAnnotationsRegistry.get(localClass1));
    
/* 196 */     ClassGenerator localClassGenerator = new ClassGenerator(localAnnotationsForIface, paramKey.toString(), localClass2.getName(), localClass1.getName(), localHashMap1);
    
/* 204 */     new Runnable()
    {
/* 206 */       public void run() { traverse(this.val$superclass); }
      
      void traverse(Class paramAnonymousClass) {
/* 209 */         if (null == paramAnonymousClass) {
/* 210 */           return;
        }
/* 212 */         if (!paramAnonymousClass.isAnnotationPresent(ProxyFor.class)) {
/* 213 */           return;
        }
/* 215 */         for (Method localMethod : paramAnonymousClass.getDeclaredMethods()) {
/* 216 */           localHashMap2.put(new MethodSignature(localMethod), localMethod);
        }
/* 218 */         traverse(paramAnonymousClass.getSuperclass());
      }
    }.run();
    
/* 223 */     for (Method localMethod1 : localClass1.getMethods())
    {
/* 225 */       MethodSignature localMethodSignature = new MethodSignature(localMethod1);
      
/* 228 */       Method localMethod2 = (Method)localHashMap2.get(localMethodSignature);
      
/* 231 */       localHashMap1.put(localMethodSignature, new MethodGenerator(localClassGenerator, localMethod1, (null == localMethod2) || (Modifier.isAbstract(localMethod2.getModifiers()))));
    }
    
/* 239 */     ??? = new ClassWriter(3);
    
/* 242 */     localClassGenerator.generate((ClassWriter)???);
/* 243 */     return ((ClassWriter)???).toByteArray();
  }
  
  static <T> Class generate(Class<T> paramClass, Class paramClass1, AnnotationsRegistry paramAnnotationsRegistry)
  {
/* 251 */     GeneratedProxiesRegistry.Key localKey = new GeneratedProxiesRegistry.Key(paramClass, paramClass1);
    
/* 254 */     byte[] arrayOfByte = generateBytecode(localKey, paramAnnotationsRegistry);
    
/* 258 */     String str = System.getProperty("oracle.jdbc.proxy.asm.verify");
/* 259 */     if ((null != str) && (0 == "true".compareToIgnoreCase(str))) {
      try
      {
/* 262 */         Class localClass = Class.forName("org.objectweb.asm.util.CheckClassAdapter");
        
/* 265 */         Method localMethod = localClass.getMethod("verify", new Class[] { ClassReader.class, Boolean.TYPE, PrintWriter.class });
        
/* 272 */         localMethod.invoke(null, new Object[] { new ClassReader(arrayOfByte), Boolean.valueOf(true), new PrintWriter(new OutputStreamWriter(System.out)) });
      }
      catch (ClassNotFoundException localClassNotFoundException1) {}catch (NoSuchMethodException localNoSuchMethodException) {}catch (IllegalAccessException localIllegalAccessException) {}catch (InvocationTargetException localInvocationTargetException) {}
    }
    
    try
    {
/* 288 */       Class.forName(localKey.toString(), false, new ClassLoader()
      {
        protected Class findClass(String paramAnonymousString)
        {
/* 295 */           return defineClass(paramAnonymousString, this.val$bytecode, 0, this.val$bytecode.length);
        }
      });
    }
    catch (ClassNotFoundException localClassNotFoundException2)
    {
/* 301 */       throw new RuntimeException(localClassNotFoundException2);
    }
  }
  
  private void generate(ClassWriter paramClassWriter)
  {
/* 307 */     paramClassWriter.visit(50, 33, this.proxyName, null, this.superclassName, new String[] { this.ifaceName, Utils.makeSlashed(_Proxy_.class.getName()) });
    
/* 316 */     for (Object localObject1 = this.members.values().iterator(); ((Iterator)localObject1).hasNext();) { localObject2 = (MethodGenerator)((Iterator)localObject1).next();
/* 317 */       if (null != localObject2) {
/* 318 */         ((MethodGenerator)localObject2).generate(paramClassWriter);
      }
    }
    
/* 322 */     localObject1 = paramClassWriter.visitMethod(1, "_getDelegate_", "()" + this.ifaceType, null, null);
    
/* 330 */     ((MethodVisitor)localObject1).visitCode();
/* 331 */     ((MethodVisitor)localObject1).visitVarInsn(25, 0);
/* 332 */     ((MethodVisitor)localObject1).visitFieldInsn(180, this.proxyName, "delegate", this.ifaceType);
    
/* 338 */     ((MethodVisitor)localObject1).visitInsn(176);
/* 339 */     ((MethodVisitor)localObject1).visitMaxs(0, 0);
/* 340 */     ((MethodVisitor)localObject1).visitEnd();
    
/* 343 */     localObject1 = paramClassWriter.visitMethod(4161, "_getDelegate_", "()Ljava/lang/Object;", null, null);
    
/* 351 */     ((MethodVisitor)localObject1).visitCode();
/* 352 */     ((MethodVisitor)localObject1).visitVarInsn(25, 0);
/* 353 */     ((MethodVisitor)localObject1).visitMethodInsn(182, this.proxyName, "_getDelegate_", "()" + this.ifaceType);
    
/* 359 */     ((MethodVisitor)localObject1).visitInsn(176);
/* 360 */     ((MethodVisitor)localObject1).visitMaxs(0, 0);
/* 361 */     ((MethodVisitor)localObject1).visitEnd();
    
/* 366 */     localObject1 = this.annotationsForIface.getValue();
    Object localObject4;
    Object localObject5;
/* 369 */     Object localObject6; Object localObject7; Object localObject8; if (null != localObject1)
    {
/* 372 */       localObject2 = ((AnnotationsRegistry.Value)localObject1).getMethodGetDelegate();
      
/* 375 */       if (null != localObject2)
      {
/* 377 */         localObject3 = paramClassWriter.visitMethod(1, ((Method)localObject2).getName(), "()" + Utils.makeType(((Method)localObject2).getReturnType()), null, null);
        
/* 384 */         ((MethodVisitor)localObject3).visitCode();
/* 385 */         localObject4 = new Label();
/* 386 */         ((MethodVisitor)localObject3).visitLabel((Label)localObject4);
/* 387 */         ((MethodVisitor)localObject3).visitVarInsn(25, 0);
/* 388 */         ((MethodVisitor)localObject3).visitFieldInsn(180, this.proxyName, "delegate", this.ifaceType);
/* 389 */         ((MethodVisitor)localObject3).visitInsn(176);
/* 390 */         localObject5 = new Label();
/* 391 */         ((MethodVisitor)localObject3).visitLabel((Label)localObject5);
/* 392 */         ((MethodVisitor)localObject3).visitLocalVariable("this", this.proxyType, null, (Label)localObject4, (Label)localObject5, 0);
/* 393 */         ((MethodVisitor)localObject3).visitMaxs(0, 0);
/* 394 */         ((MethodVisitor)localObject3).visitEnd();
      }
      
/* 398 */       localObject3 = ((AnnotationsRegistry.Value)localObject1).getMethodSetDelegate();
      
/* 401 */       if (null != localObject3)
      {
/* 403 */         localObject4 = ((Method)localObject3).getParameterTypes();
        
/* 405 */         if (1 != localObject4.length) {
/* 406 */           throw new RuntimeException("wrong delegate setter signature");
        }
/* 408 */         localObject5 = paramClassWriter.visitMethod(1, ((Method)localObject3).getName(), "(" + Utils.makeType(localObject4[0]) + ")V", null, null);
        
/* 416 */         ((MethodVisitor)localObject5).visitCode();
        
/* 418 */         ((MethodVisitor)localObject5).visitLabel(localObject6 = new Label());
        
/* 420 */         ((MethodVisitor)localObject5).visitVarInsn(25, 0);
        
/* 422 */         ((MethodVisitor)localObject5).visitFieldInsn(180, this.proxyName, "proxyFactory", Utils.makeType(ProxyFactory.class));
        
/* 428 */         ((MethodVisitor)localObject5).visitVarInsn(25, 0);
/* 429 */         ((MethodVisitor)localObject5).visitVarInsn(25, 0);
        
/* 431 */         ((MethodVisitor)localObject5).visitFieldInsn(180, this.proxyName, "delegate", this.ifaceType);
        
/* 437 */         ((MethodVisitor)localObject5).visitVarInsn(25, 1);
        
/* 439 */         ((MethodVisitor)localObject5).visitMethodInsn(182, Utils.makeSlashed(ProxyFactory.class), "updateDelegate", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V");
        
/* 445 */         ((MethodVisitor)localObject5).visitVarInsn(25, 0);
/* 446 */         ((MethodVisitor)localObject5).visitVarInsn(25, 1);
        
/* 448 */         ((MethodVisitor)localObject5).visitFieldInsn(181, this.proxyName, "delegate", this.ifaceType);
        
/* 454 */         ((MethodVisitor)localObject5).visitInsn(177);
        
/* 456 */         ((MethodVisitor)localObject5).visitLabel(localObject7 = new Label());
        
/* 458 */         ((MethodVisitor)localObject5).visitLocalVariable("this", this.proxyType, null, (Label)localObject6, (Label)localObject7, 0);
        
/* 466 */         ((MethodVisitor)localObject5).visitLocalVariable("delegate", this.ifaceType, null, (Label)localObject6, (Label)localObject7, 1);
        
/* 474 */         ((MethodVisitor)localObject5).visitMaxs(0, 0);
/* 475 */         ((MethodVisitor)localObject5).visitEnd();
      }
      
/* 479 */       localObject4 = ((AnnotationsRegistry.Value)localObject1).getMethodGetCreator();
      
/* 482 */       if (null != localObject4)
      {
/* 484 */         localObject5 = paramClassWriter.visitMethod(1, ((Method)localObject4).getName(), "()" + Utils.makeType(((Method)localObject4).getReturnType()), null, null);
        
/* 492 */         ((MethodVisitor)localObject5).visitCode();
/* 493 */         localObject6 = new Label();
/* 494 */         ((MethodVisitor)localObject5).visitLabel((Label)localObject6);
/* 495 */         ((MethodVisitor)localObject5).visitVarInsn(25, 0);
/* 496 */         ((MethodVisitor)localObject5).visitFieldInsn(180, this.proxyName, "creator", "Ljava/lang/Object;");
/* 497 */         ((MethodVisitor)localObject5).visitInsn(176);
/* 498 */         localObject7 = new Label();
/* 499 */         ((MethodVisitor)localObject5).visitLabel((Label)localObject7);
/* 500 */         ((MethodVisitor)localObject5).visitLocalVariable("this", this.proxyType, null, (Label)localObject6, (Label)localObject7, 0);
/* 501 */         ((MethodVisitor)localObject5).visitMaxs(1, 1);
/* 502 */         ((MethodVisitor)localObject5).visitEnd();
      }
      
/* 506 */       localObject5 = ((AnnotationsRegistry.Value)localObject1).getMethodGetProxy();
      
/* 509 */       if (null != localObject5)
      {
/* 511 */         localObject6 = ((Method)localObject5).getParameterTypes();
        
/* 514 */         localObject7 = ((Method)localObject5).getReturnType();
        
/* 517 */         if ((!Arrays.deepEquals(new Class[] { Object.class, Object.class }, (Object[])localObject6)) || (!Object.class.equals(localObject7)))
        {
/* 519 */           throw new RuntimeException("internal error: wrong @GetProxy method");
        }
/* 521 */         localObject8 = paramClassWriter.visitMethod(1, ((Method)localObject5).getName(), "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<T:Ljava/lang/Object;>(TT;Ljava/lang/Object;)TT;", null);
        
/* 529 */         ((MethodVisitor)localObject8).visitCode();
/* 530 */         ((MethodVisitor)localObject8).visitVarInsn(25, 0);
        
/* 532 */         ((MethodVisitor)localObject8).visitFieldInsn(180, this.proxyName, "proxyFactory", Utils.makeType(ProxyFactory.class));
        
/* 538 */         ((MethodVisitor)localObject8).visitVarInsn(25, 1);
/* 539 */         ((MethodVisitor)localObject8).visitVarInsn(25, 2);
        
/* 541 */         ((MethodVisitor)localObject8).visitMethodInsn(182, Utils.makeSlashed(ProxyFactory.class), "proxyFor", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
        
/* 547 */         ((MethodVisitor)localObject8).visitInsn(176);
/* 548 */         ((MethodVisitor)localObject8).visitMaxs(3, 3);
/* 549 */         ((MethodVisitor)localObject8).visitEnd();
      }
    }
    
/* 555 */     paramClassWriter.visitField(2, "delegate", this.ifaceType, null, null).visitEnd();
    
/* 563 */     paramClassWriter.visitField(18, "creator", "Ljava/lang/Object;", null, null).visitEnd();
    
/* 571 */     paramClassWriter.visitField(18, "proxyFactory", Utils.makeType(ProxyFactory.class.getName()), null, null).visitEnd();
    
/* 579 */     paramClassWriter.visitField(18, "proxyCache", "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/Object;Ljava/lang/Object;>;", null).visitEnd();
    
/* 587 */     paramClassWriter.visitField(10, "zeroLengthObjectArray", "[Ljava/lang/Object;", null, null).visitEnd();
    
/* 595 */     int i = 0;
/* 596 */     for (Object localObject2 = this.members.values().iterator(); ((Iterator)localObject2).hasNext();) { localObject3 = (MethodGenerator)((Iterator)localObject2).next();
      
/* 598 */       paramClassWriter.visitField(10, ((MethodGenerator)localObject3).getMethodObject(), "Ljava/lang/reflect/Method;", null, null).visitEnd();
      
/* 605 */       i = 1;
    }
    
/* 610 */     localObject2 = paramClassWriter.visitMethod(8, "<clinit>", "()V", null, null);
    
/* 618 */     ((MethodVisitor)localObject2).visitCode();
    
/* 621 */     Utils.loadConst((MethodVisitor)localObject2, 0);
/* 622 */     ((MethodVisitor)localObject2).visitTypeInsn(189, "java/lang/Object");
/* 623 */     ((MethodVisitor)localObject2).visitFieldInsn(179, this.proxyName, "zeroLengthObjectArray", "[Ljava/lang/Object;");
    
/* 629 */     if (i != 0)
    {
/* 636 */       ((MethodVisitor)localObject2).visitTryCatchBlock(localObject3 = new Label(), localObject4 = new Label(), localObject5 = new Label(), "java/lang/Throwable");
      
/* 642 */       ((MethodVisitor)localObject2).visitLabel((Label)localObject3);
      
/* 644 */       for (localObject7 = this.members.values().iterator(); ((Iterator)localObject7).hasNext();) { localObject8 = (MethodGenerator)((Iterator)localObject7).next();
/* 645 */         ((MethodGenerator)localObject8).initializeMethodObject((MethodVisitor)localObject2);
      }
/* 647 */       ((MethodVisitor)localObject2).visitLabel((Label)localObject4);
/* 648 */       ((MethodVisitor)localObject2).visitJumpInsn(167, localObject6 = new Label());
/* 649 */       ((MethodVisitor)localObject2).visitLabel((Label)localObject5);
      
/* 651 */       ((MethodVisitor)localObject2).visitFrame(4, 0, null, 1, new Object[] { "java/lang/Throwable" });
      
/* 658 */       ((MethodVisitor)localObject2).visitVarInsn(58, 0);
      
/* 660 */       ((MethodVisitor)localObject2).visitTypeInsn(187, "java/lang/RuntimeException");
/* 661 */       ((MethodVisitor)localObject2).visitInsn(89);
/* 662 */       ((MethodVisitor)localObject2).visitVarInsn(25, 0);
      
/* 664 */       ((MethodVisitor)localObject2).visitMethodInsn(183, "java/lang/RuntimeException", "<init>", "(Ljava/lang/Throwable;)V");
      
/* 670 */       ((MethodVisitor)localObject2).visitInsn(191);
      
/* 672 */       ((MethodVisitor)localObject2).visitLabel((Label)localObject6);
/* 673 */       ((MethodVisitor)localObject2).visitFrame(3, 0, null, 0, null);
    }
    
/* 676 */     ((MethodVisitor)localObject2).visitInsn(177);
/* 677 */     ((MethodVisitor)localObject2).visitMaxs(0, 0);
/* 678 */     ((MethodVisitor)localObject2).visitEnd();
    
/* 683 */     localObject2 = "(" + this.ifaceType + "Ljava/lang/Object;" + Utils.makeType(ProxyFactory.class.getName()) + "Ljava/util/Map;" + ")V";
    
/* 691 */     Object localObject3 = paramClassWriter.visitMethod(1, "<init>", (String)localObject2, null, null);
    
/* 699 */     ((MethodVisitor)localObject3).visitCode();
/* 700 */     ((MethodVisitor)localObject3).visitVarInsn(25, 0);
    
/* 702 */     ((MethodVisitor)localObject3).visitMethodInsn(183, this.superclassName, "<init>", "()V");
    
/* 708 */     ((MethodVisitor)localObject3).visitVarInsn(25, 0);
/* 709 */     ((MethodVisitor)localObject3).visitVarInsn(25, 1);
    
/* 711 */     ((MethodVisitor)localObject3).visitFieldInsn(181, this.proxyName, "delegate", this.ifaceType);
    
/* 717 */     ((MethodVisitor)localObject3).visitVarInsn(25, 0);
/* 718 */     ((MethodVisitor)localObject3).visitVarInsn(25, 2);
    
/* 720 */     ((MethodVisitor)localObject3).visitFieldInsn(181, this.proxyName, "creator", "Ljava/lang/Object;");
    
/* 726 */     ((MethodVisitor)localObject3).visitVarInsn(25, 0);
/* 727 */     ((MethodVisitor)localObject3).visitVarInsn(25, 3);
    
/* 729 */     ((MethodVisitor)localObject3).visitFieldInsn(181, this.proxyName, "proxyFactory", Utils.makeType(ProxyFactory.class.getName()));
    
/* 735 */     ((MethodVisitor)localObject3).visitVarInsn(25, 0);
/* 736 */     ((MethodVisitor)localObject3).visitVarInsn(25, 4);
    
/* 738 */     ((MethodVisitor)localObject3).visitFieldInsn(181, this.proxyName, "proxyCache", "Ljava/util/Map;");
    
/* 744 */     ((MethodVisitor)localObject3).visitInsn(177);
/* 745 */     ((MethodVisitor)localObject3).visitMaxs(0, 0);
/* 746 */     ((MethodVisitor)localObject3).visitEnd();
    
/* 749 */     paramClassWriter.visitEnd();
  }
  
  AnnotationsForIface getAnnotationsForIface()
  {
/* 757 */     return this.annotationsForIface;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/ClassGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */