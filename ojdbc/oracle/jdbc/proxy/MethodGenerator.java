package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import oracle.jdbc.proxy.annotation.ProxyResultPolicy;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
class MethodGenerator
{
/*  70 */   private static long ids = 0L;
/*  71 */   private final String methodObject = "methodObject" + ids++;
  private final String proxyName;
  private final String ifaceName;
  private final String superclassName;
  private final String proxyType;
  private final String ifaceType;
  private final Method method;
  private final ClassGenerator.AnnotationsForIface annotationsForIface;
  private final boolean callDelegate;
  private final boolean returns;
  private final Class[] parameterTypes;
  private final Class[] exceptionTypes;
  private final Class returnType;
  private final String methodName;
  private final String signature;
  private final String[] throwables;
  
  MethodGenerator(ClassGenerator paramClassGenerator, Method paramMethod, boolean paramBoolean) {
/*  89 */     this.proxyName = paramClassGenerator.getProxyName();
/*  90 */     this.ifaceName = paramClassGenerator.getIfaceName();
/*  91 */     this.superclassName = paramClassGenerator.getSuperclassName();
/*  92 */     this.ifaceType = paramClassGenerator.getIfaceType();
/*  93 */     this.proxyType = paramClassGenerator.getProxyType();
/*  94 */     this.method = paramMethod;
/*  95 */     this.annotationsForIface = paramClassGenerator.getAnnotationsForIface();
/*  96 */     this.callDelegate = paramBoolean;
/*  97 */     this.parameterTypes = paramMethod.getParameterTypes();
/*  98 */     this.exceptionTypes = paramMethod.getExceptionTypes();
/*  99 */     this.returnType = paramMethod.getReturnType();
/* 100 */     this.returns = (!"void".equals(this.returnType.getName()));
/* 101 */     this.methodName = paramMethod.getName();
/* 102 */     this.signature = Utils.makeSignature(this.parameterTypes, this.returnType);
/* 103 */     this.throwables = Utils.makeThrowables(this.exceptionTypes);
  }
  
  String getMethodObject()
  {
/* 111 */     return this.methodObject;
  }
  
/* 114 */   private final List<Class> exceptionsToCatch = new ReadOnlyList()
  {
    public Class get(int paramAnonymousInt)
    {
/* 119 */       return 0 == paramAnonymousInt ? RuntimeException.class : MethodGenerator.this.exceptionTypes[(paramAnonymousInt - 1)];
    }
    
    public int size()
    {
/* 126 */       return MethodGenerator.this.exceptionTypes.length + 1;
    }
  };
  
  private Method getMethodPre()
  {
/* 132 */     AnnotationsRegistry.Value localValue = this.annotationsForIface.getValue();
    
/* 135 */     if (null == localValue) {
/* 136 */       return null;
    }
/* 138 */     return localValue.getMethodPre(this.annotationsForIface.getIface(), new MethodSignature(this.method));
  }
  
  private boolean isResultProxied()
  {
/* 145 */     if (!hasAssignableProxyForReturnType(this.returnType, this.annotationsForIface.getRegistry().keySet())) {
/* 146 */       return false;
    }
/* 148 */     AnnotationsRegistry.Value localValue = this.annotationsForIface.getValue();
    
/* 151 */     if (null == localValue) {
/* 152 */       return false;
    }
/* 154 */     boolean bool = localValue.belongsToIfaceToProxy(this.annotationsForIface.getIface(), new MethodSignature(this.method));
    
/* 159 */     return (bool) && (ProxyResultPolicy.MANUAL != getProxyResultPolicy());
  }
  
  private final boolean hasAssignableProxyForReturnType(Class paramClass, Set<Class> paramSet)
  {
/* 164 */     for (Class localClass : paramSet) {
/* 165 */       if (paramClass.isAssignableFrom(localClass)) return true;
    }
/* 167 */     return false;
  }
  
  private boolean isMethodPreDefined()
  {
/* 172 */     return null != getMethodPre();
  }
  
  private Method getMethodVoidPost()
  {
/* 177 */     AnnotationsRegistry.Value localValue = this.annotationsForIface.getValue();
    
/* 180 */     if (null == localValue) {
/* 181 */       return null;
    }
/* 183 */     return localValue.getMethodVoidPost(this.annotationsForIface.getIface(), new MethodSignature(this.method));
  }
  
  private boolean isMethodVoidPostDefined()
  {
/* 190 */     return null != getMethodVoidPost();
  }
  
  private Method getMethodReturningPost()
  {
/* 195 */     AnnotationsRegistry.Value localValue = this.annotationsForIface.getValue();
    
/* 198 */     if (null == localValue) {
/* 199 */       return null;
    }
/* 201 */     return localValue.getMethodReturningPost(this.annotationsForIface.getIface(), new MethodSignature(this.method));
  }
  
  private boolean isMethodReturningPostDefined()
  {
/* 208 */     return null != getMethodReturningPost();
  }
  
  private Method getMethodVoidOnError(Class paramClass)
  {
/* 214 */     AnnotationsRegistry.Value localValue = this.annotationsForIface.getValue();
    
/* 217 */     if (null == localValue) {
/* 218 */       return null;
    }
/* 220 */     Map localMap = localValue.getMapVoidOnError(this.annotationsForIface.getIface(), new MethodSignature(this.method));
    
/* 225 */     if (null == localMap) {
/* 226 */       return null;
    }
/* 228 */     return (Method)localMap.get(paramClass);
  }
  
  private boolean isMethodVoidOnErrorDefined()
  {
/* 233 */     boolean bool = false;
    
/* 235 */     for (Class localClass : this.exceptionsToCatch) {
/* 236 */       if (null != getMethodVoidOnError(localClass))
/* 237 */         bool = true;
    }
/* 239 */     return bool;
  }
  
  private Method getMethodReturningOnError(Class paramClass)
  {
/* 245 */     AnnotationsRegistry.Value localValue = this.annotationsForIface.getValue();
    
/* 248 */     if (null == localValue) {
/* 249 */       return null;
    }
/* 251 */     Map localMap = localValue.getMapReturningOnError(this.annotationsForIface.getIface(), new MethodSignature(this.method));
    
/* 256 */     if (null == localMap) {
/* 257 */       return null;
    }
/* 259 */     return (Method)localMap.get(paramClass);
  }
  
  private boolean isMethodReturningOnErrorDefined()
  {
/* 264 */     boolean bool = false;
    
/* 266 */     for (Class localClass : this.exceptionsToCatch) {
/* 267 */       if (null != getMethodReturningOnError(localClass))
/* 268 */         bool = true;
    }
/* 270 */     return bool;
  }
  
  private boolean isMethodOnErrorDefined()
  {
/* 275 */     return this.returns ? isMethodReturningOnErrorDefined() : isMethodVoidOnErrorDefined();
  }
  
  private boolean isMethodPostDefined()
  {
/* 282 */     return this.returns ? isMethodReturningPostDefined() : isMethodVoidPostDefined();
  }
  
  final Method getMethodPost()
  {
/* 288 */     return this.returns ? getMethodReturningPost() : getMethodVoidPost();
  }
  
  boolean isAnyInterceptorDefined()
  {
/* 295 */     return (isMethodPreDefined()) || (isMethodVoidPostDefined()) || (isMethodReturningPostDefined()) || (isMethodVoidOnErrorDefined()) || (isMethodReturningOnErrorDefined());
  }
  
  ProxyResultPolicy getProxyResultPolicy()
  {
/* 304 */     AnnotationsRegistry.Value localValue = this.annotationsForIface.getValue();
    
/* 307 */     if (null == localValue) {
/* 308 */       return ProxyResultPolicy.CACHE;
    }
/* 310 */     return localValue.getProxyResultPolicy(this.method);
  }
  
  void generate(ClassWriter paramClassWriter)
  {
/* 349 */     MethodVisitor localMethodVisitor = paramClassWriter.visitMethod(this.method.isVarArgs() ? 129 : 1, this.methodName, this.signature, null, this.throwables);
    
/* 356 */     localMethodVisitor.visitCode();
    
/* 358 */     Label localLabel1 = new Label();
/* 359 */     Label localLabel2 = new Label();
/* 360 */     Label localLabel3 = new Label();
/* 361 */     Label localLabel4 = new Label();
    
/* 369 */     int j = 1;
    Object localObject2;
/* 371 */     for (localObject2 : this.parameterTypes) {
/* 372 */       j += Utils.varSize((Class)localObject2);
    }
/* 374 */     int i = j;
    Label[] arrayOfLabel1;
    int k;
    Object localObject1;
/* 378 */     if (isMethodOnErrorDefined())
    {
/* 380 */       j = this.exceptionsToCatch.size();
/* 381 */       arrayOfLabel1 = new Label[j];
      
/* 383 */       for (k = 0; k < j; k++)
      {
/* 385 */         Class localClass = (Class)this.exceptionsToCatch.get(k);
        
/* 387 */         localObject1 = this.returns ? getMethodReturningOnError(localClass) : getMethodVoidOnError(localClass);
        
/* 391 */         if (null != localObject1)
        {
/* 394 */           localMethodVisitor.visitTryCatchBlock(localLabel3, localLabel4, arrayOfLabel1[k] = new Label(), Utils.makeSlashed((Class)this.exceptionsToCatch.get(k)));
        }
        
      }
      
    }
    else
    {
/* 402 */       arrayOfLabel1 = null;
    }
/* 404 */     localMethodVisitor.visitLabel(localLabel1);
/* 405 */     localMethodVisitor.visitLabel(localLabel3);
    
/* 408 */     if (isMethodPreDefined())
    {
/* 410 */       localMethodVisitor.visitVarInsn(25, 0);
      
/* 412 */       localMethodVisitor.visitFieldInsn(178, this.proxyName, getMethodObject(), "Ljava/lang/reflect/Method;");
      
/* 418 */       localMethodVisitor.visitVarInsn(25, 0);
      
/* 421 */       j = this.parameterTypes.length;
      
/* 423 */       if ((this.method.isVarArgs()) && (1 == j)) {
/* 424 */         localMethodVisitor.visitVarInsn(25, 1);
/* 425 */       } else if (0 == j) {
/* 426 */         localMethodVisitor.visitFieldInsn(178, this.proxyName, "zeroLengthObjectArray", "[Ljava/lang/Object;");
      }
      else
      {
/* 433 */         Utils.loadConst(localMethodVisitor, j);
/* 434 */         localMethodVisitor.visitTypeInsn(189, "java/lang/Object");
        
/* 436 */         k = 1; for (i1 = 0; i1 < j; i1++)
        {
/* 438 */           localObject1 = this.parameterTypes[i1];
          
/* 440 */           localMethodVisitor.visitInsn(89);
/* 441 */           Utils.loadConst(localMethodVisitor, i1);
/* 442 */           localMethodVisitor.visitVarInsn(Utils.loadOpcode((Class)localObject1), k);
/* 443 */           Utils.autoBox(localMethodVisitor, (Class)localObject1);
/* 444 */           localMethodVisitor.visitInsn(83);
/* 445 */           k += Utils.varSize((Class)localObject1);
        }
      }
      
/* 450 */       localMethodVisitor.visitMethodInsn(183, this.superclassName, getMethodPre().getName(), "(Ljava/lang/reflect/Method;Ljava/lang/Object;[Ljava/lang/Object;)V");
    }
    
/* 467 */     if (isMethodPostDefined())
    {
/* 469 */       localMethodVisitor.visitVarInsn(25, 0);
      
/* 471 */       localMethodVisitor.visitFieldInsn(178, this.proxyName, getMethodObject(), "Ljava/lang/reflect/Method;");
    }
    
/* 478 */     boolean bool = isResultProxied();
    
/* 480 */     if ((this.returns) && (bool))
    {
/* 482 */       localMethodVisitor.visitVarInsn(25, 0);
/* 483 */       localMethodVisitor.visitFieldInsn(180, this.proxyName, "proxyFactory", Utils.makeType(ProxyFactory.class.getName()));
    }
    
/* 490 */     localMethodVisitor.visitVarInsn(25, 0);
    
/* 492 */     if (this.callDelegate) {
/* 493 */       localMethodVisitor.visitFieldInsn(180, this.proxyName, "delegate", this.ifaceType);
    }
    
/* 499 */     loadDelegateParams(localMethodVisitor);
    
/* 501 */     localMethodVisitor.visitMethodInsn(this.callDelegate ? 185 : 183, this.callDelegate ? this.ifaceName : this.superclassName, this.methodName, this.signature);
    
/* 507 */     if ((this.returns) && (bool))
    {
/* 509 */       Utils.cast(localMethodVisitor, this.returnType, Object.class);
      
/* 511 */       localMethodVisitor.visitVarInsn(25, 0);
      
/* 513 */       localMethodVisitor.visitVarInsn(25, 0);
/* 514 */       localMethodVisitor.visitFieldInsn(180, this.proxyName, "proxyCache", "Ljava/util/Map;");
      
/* 520 */       localMethodVisitor.visitFieldInsn(178, this.proxyName, getMethodObject(), "Ljava/lang/reflect/Method;");
      
      String str;
      
/* 527 */       switch (getProxyResultPolicy()) {
      case CREATE: 
/* 529 */         str = "proxyForCreate"; break;
/* 530 */       case CACHE:  str = "proxyForCache"; break;
/* 531 */       case CREATE_CACHE:  str = "proxyForCreateCache"; break;
/* 532 */       default:  throw new RuntimeException("internal error");
      }
      
/* 535 */       localMethodVisitor.visitMethodInsn(182, Utils.makeSlashed(ProxyFactory.class.getName()), str, "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Map;Ljava/lang/reflect/Method;)Ljava/lang/Object;");
    }
    
/* 542 */     if (isMethodPostDefined())
    {
/* 544 */       if (this.returns) {
/* 545 */         Utils.cast(localMethodVisitor, bool ? Object.class : this.returnType, getMethodPost().getParameterTypes()[1]);
      }
      
/* 550 */       localMethodVisitor.visitMethodInsn(182, this.superclassName, getMethodPost().getName(), "(Ljava/lang/reflect/Method;" + (this.returns ? Utils.makeType(getMethodPost().getParameterTypes()[1]) : "") + ")" + Utils.makeType(getMethodPost().getReturnType()));
      
/* 558 */       if (this.returns) {
/* 559 */         Utils.cast(localMethodVisitor, getMethodPost().getReturnType(), this.returnType);
      }
/* 561 */     } else if (this.returns) {
/* 562 */       Utils.cast(localMethodVisitor, bool ? Object.class : this.returnType, this.returnType);
    }
    
/* 567 */     localMethodVisitor.visitLabel(localLabel4);
/* 568 */     localMethodVisitor.visitInsn(Utils.returnOpcode(this.returnType));
    Label[] arrayOfLabel2;
    Label[] arrayOfLabel3;
/* 571 */     if (isMethodOnErrorDefined())
    {
/* 573 */       m = this.exceptionsToCatch.size();
/* 574 */       arrayOfLabel2 = new Label[m];
/* 575 */       arrayOfLabel3 = new Label[m];
      
/* 577 */       for (i1 = 0; i1 < m; i1++)
      {
/* 579 */         localObject1 = (Class)this.exceptionsToCatch.get(i1);
        
/* 581 */         localObject2 = this.returns ? getMethodReturningOnError((Class)localObject1) : getMethodVoidOnError((Class)localObject1);
        
/* 585 */         if (null != localObject2)
        {
/* 588 */           localMethodVisitor.visitLabel(arrayOfLabel1[i1]);
          
/* 590 */           localMethodVisitor.visitFrame(4, 0, null, 1, new Object[] { Utils.makeSlashed((Class)localObject1) });
          
/* 597 */           localMethodVisitor.visitVarInsn(58, i);
          
/* 599 */           localMethodVisitor.visitLabel(arrayOfLabel2[i1] = new Label());
          
/* 608 */           localMethodVisitor.visitVarInsn(25, 0);
          
/* 610 */           localMethodVisitor.visitFieldInsn(178, this.proxyName, getMethodObject(), "Ljava/lang/reflect/Method;");
          
/* 616 */           if ((this.returns) && (isMethodPostDefined()))
          {
/* 618 */             localMethodVisitor.visitVarInsn(25, 0);
            
/* 620 */             localMethodVisitor.visitFieldInsn(178, this.proxyName, getMethodObject(), "Ljava/lang/reflect/Method;");
          }
          
/* 627 */           localMethodVisitor.visitVarInsn(25, i);
          
/* 629 */           localMethodVisitor.visitMethodInsn(182, this.superclassName, ((Method)localObject2).getName(), "(Ljava/lang/reflect/Method;" + Utils.makeType(localObject2.getParameterTypes()[1].getName()) + ")" + Utils.makeType(((Method)localObject2).getReturnType().getName()));
          
/* 637 */           if ((this.returns) && (isMethodPostDefined()))
          {
/* 639 */             Utils.cast(localMethodVisitor, ((Method)localObject2).getReturnType(), getMethodPost().getParameterTypes()[1]);
            
/* 644 */             localMethodVisitor.visitMethodInsn(182, this.superclassName, getMethodPost().getName(), "(Ljava/lang/reflect/Method;" + Utils.makeType(getMethodPost().getParameterTypes()[1].getName()) + ")" + Utils.makeType(getMethodPost().getReturnType().getName()));
            
/* 652 */             Utils.cast(localMethodVisitor, getMethodPost().getReturnType(), this.returnType);
          }
          else {
/* 655 */             Utils.cast(localMethodVisitor, ((Method)localObject2).getReturnType(), this.returnType);
          }
/* 657 */           localMethodVisitor.visitInsn(Utils.returnOpcode(this.returnType));
          
/* 659 */           localMethodVisitor.visitLabel(arrayOfLabel3[i1] = new Label());
        }
      }
    }
    else {
/* 664 */       arrayOfLabel2 = arrayOfLabel3 = null;
    }
/* 666 */     localMethodVisitor.visitLabel(localLabel2);
    
/* 669 */     int m = 0;
    
/* 671 */     localMethodVisitor.visitLocalVariable("this", this.proxyType, null, localLabel1, localLabel2, m++);
    
/* 679 */     for (int i1 = 0; 
/* 680 */         i1 < this.parameterTypes.length; 
/* 681 */         i1++) {
/* 682 */       localMethodVisitor.visitLocalVariable("arg" + i1, Utils.makeType(this.parameterTypes[i1]), null, localLabel1, localLabel2, m);m += Utils.varSize(this.parameterTypes[i1]);
    }
    
/* 690 */     if (isMethodOnErrorDefined())
    {
/* 692 */       if (i != m) {
/* 693 */         throw new RuntimeException("wrong exception index");
      }
/* 695 */       i1 = this.exceptionsToCatch.size();
/* 696 */       for (int i3 = 0; i3 < i1; i3++)
      {
/* 698 */         localObject2 = (Class)this.exceptionsToCatch.get(i3);
        
/* 700 */         Method localMethod = this.returns ? getMethodReturningOnError((Class)localObject2) : getMethodVoidOnError((Class)localObject2);
        
/* 704 */         if (null != localMethod)
        {
/* 707 */           localMethodVisitor.visitLocalVariable("e", Utils.makeType((Class)this.exceptionsToCatch.get(i3)), null, arrayOfLabel2[i3], arrayOfLabel3[i3], i);
        }
      }
    }
    
/* 717 */     localMethodVisitor.visitMaxs(0, 0);
/* 718 */     localMethodVisitor.visitEnd();
  }
  
  private void loadDelegateParams(MethodVisitor paramMethodVisitor)
  {
/* 723 */     String str1 = Utils.makeSlashed(_Proxy_.class.getName());
    
/* 725 */     int i = 1; for (int j = 0; 
/* 726 */         j < this.parameterTypes.length; 
/* 727 */         j++)
    {
/* 729 */       Class localClass1 = this.parameterTypes[j];
/* 730 */       String str2 = Utils.makeSlashed(localClass1.getName());
/* 731 */       int k = 0;
      
/* 733 */       for (Object localObject1 = this.annotationsForIface.getRegistry().values().iterator(); ((Iterator)localObject1).hasNext();) { localObject2 = (AnnotationsRegistry.Value)((Iterator)localObject1).next();
/* 734 */         for (Class localClass2 : ((AnnotationsRegistry.Value)localObject2).getIfacesToProxy())
/* 735 */           if (localClass1.isAssignableFrom(localClass2))
          {
/* 737 */             k = 1;
/* 738 */             break;
          } }
      Object localObject2;
/* 741 */       if (k != 0)
      {
/* 743 */         paramMethodVisitor.visitVarInsn(Utils.loadOpcode(localClass1), i);
/* 744 */         paramMethodVisitor.visitTypeInsn(193, str1);
/* 745 */         localObject1 = new Label();
/* 746 */         paramMethodVisitor.visitJumpInsn(153, (Label)localObject1);
/* 747 */         paramMethodVisitor.visitVarInsn(Utils.loadOpcode(localClass1), i);
/* 748 */         paramMethodVisitor.visitTypeInsn(192, str1);
/* 749 */         paramMethodVisitor.visitMethodInsn(185, str1, "_getDelegate_", "()Ljava/lang/Object;");
        
/* 754 */         paramMethodVisitor.visitTypeInsn(192, str2);
/* 755 */         localObject2 = new Label();
/* 756 */         paramMethodVisitor.visitJumpInsn(167, (Label)localObject2);
/* 757 */         paramMethodVisitor.visitLabel((Label)localObject1);
/* 758 */         paramMethodVisitor.visitFrame(3, 0, null, 0, null);
/* 759 */         paramMethodVisitor.visitVarInsn(Utils.loadOpcode(localClass1), i);
/* 760 */         paramMethodVisitor.visitLabel((Label)localObject2);
/* 761 */         paramMethodVisitor.visitFrame(4, 0, null, 1, new Object[] { str2 });
      }
      else {
/* 764 */         paramMethodVisitor.visitVarInsn(Utils.loadOpcode(localClass1), i);
      }
/* 727 */       i += Utils.varSize(this.parameterTypes[j]);
    }
  }
  
  void initializeMethodObject(MethodVisitor paramMethodVisitor)
  {
/* 770 */     int i = this.parameterTypes.length;
/* 771 */     paramMethodVisitor.visitLdcInsn(Type.getType(Utils.makeType(this.method.getDeclaringClass().getName())));
/* 772 */     paramMethodVisitor.visitLdcInsn(this.methodName);
/* 773 */     Utils.loadConst(paramMethodVisitor, i);
/* 774 */     paramMethodVisitor.visitTypeInsn(189, "java/lang/Class");
    
/* 776 */     for (int j = 0; j < i; j++)
    {
/* 778 */       paramMethodVisitor.visitInsn(89);
/* 779 */       Utils.loadConst(paramMethodVisitor, j);
/* 780 */       Utils.loadClass(paramMethodVisitor, this.parameterTypes[j]);
/* 781 */       paramMethodVisitor.visitInsn(83);
    }
    
/* 784 */     paramMethodVisitor.visitMethodInsn(182, "java/lang/Class", "getDeclaredMethod", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;");
    
/* 790 */     paramMethodVisitor.visitFieldInsn(179, this.proxyName, getMethodObject(), "Ljava/lang/reflect/Method;");
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/MethodGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */