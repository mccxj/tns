/*      */ package oracle.jdbc.proxy;
/*      */ 
/*      */ import java.lang.reflect.Method;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import oracle.jdbc.proxy.annotation.GetCreator;
/*      */ import oracle.jdbc.proxy.annotation.GetDelegate;
/*      */ import oracle.jdbc.proxy.annotation.GetProxy;
/*      */ import oracle.jdbc.proxy.annotation.Methods;
/*      */ import oracle.jdbc.proxy.annotation.OnError;
/*      */ import oracle.jdbc.proxy.annotation.Post;
/*      */ import oracle.jdbc.proxy.annotation.Pre;
/*      */ import oracle.jdbc.proxy.annotation.ProxyFor;
/*      */ import oracle.jdbc.proxy.annotation.ProxyLocale;
/*      */ import oracle.jdbc.proxy.annotation.ProxyResult;
/*      */ import oracle.jdbc.proxy.annotation.ProxyResultPolicy;
/*      */ import oracle.jdbc.proxy.annotation.SetDelegate;
/*      */ import oracle.jdbc.proxy.annotation.Signature;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class AnnotationsRegistry
/*      */ {
/*      */   private static class SyntaxError
/*      */     extends RuntimeException
/*      */   {
/*      */     SyntaxError(String paramString)
/*      */     {
/*   51 */       super();
/*      */     }
/*      */     
/*   54 */     private static final SyntaxError onlyOneAllowed = new SyntaxError("only one @Pre/@Post/@OnError/@GetDelegate/@SetDelegate/@GetCreator/@GetProxy allowed");
/*      */     
/*      */ 
/*   57 */     private static final SyntaxError onlyOneMethodslessAllowed = new SyntaxError("only one @Methods-less @Pre/@Post/@OnError allowed");
/*      */     
/*      */ 
/*   60 */     private static final SyntaxError wrongMethodsContext = new SyntaxError("wrong context for @Methods");
/*      */     
/*      */ 
/*   63 */     private static final SyntaxError wrongPre = new SyntaxError("wrong @Pre");
/*      */     
/*      */ 
/*   66 */     private static final SyntaxError wrongPost = new SyntaxError("wrong @Post");
/*      */     
/*      */ 
/*   69 */     private static final SyntaxError wrongOnError = new SyntaxError("wrong @OnError");
/*      */     
/*      */ 
/*   72 */     private static final SyntaxError onlyOneOnErrorExceptionTypeAllowed = new SyntaxError("only one @OnError Exception type allowed for a given method");
/*      */     
/*      */ 
/*   75 */     private static final SyntaxError wrongGetCreator = new SyntaxError("wrong @GetCreator");
/*      */     
/*      */ 
/*   78 */     private static final SyntaxError wrongGetCreatorMustBeProtected = new SyntaxError("wrong @GetCreator: must be protected");
/*      */     
/*      */ 
/*   81 */     private static final SyntaxError wrongGetCreatorMustBeAbstract = new SyntaxError("wrong @GetCreator: must be abstract");
/*      */     
/*      */ 
/*   84 */     private static final SyntaxError wrongGetDelegate = new SyntaxError("wrong @GetDelegate");
/*      */     
/*      */ 
/*   87 */     private static final SyntaxError wrongGetDelegateMustBeProtected = new SyntaxError("wrong @GetDelegate: must be protected");
/*      */     
/*      */ 
/*   90 */     private static final SyntaxError wrongGetDelegateMustBeAbstract = new SyntaxError("wrong @GetDelegate: must be abstract");
/*      */     
/*      */ 
/*   93 */     private static final SyntaxError wrongGetProxy = new SyntaxError("wrong @GetProxy");
/*      */     
/*      */ 
/*   96 */     private static final SyntaxError wrongGetProxyMustBeProtected = new SyntaxError("wrong @GetProxy: must be protected");
/*      */     
/*      */ 
/*   99 */     private static final SyntaxError wrongGetProxyMustBeAbstract = new SyntaxError("wrong @GetProxy: must be abstract");
/*      */     
/*      */ 
/*  102 */     private static final SyntaxError wrongSetDelegate = new SyntaxError("wrong @SetDelegate");
/*      */     
/*      */ 
/*  105 */     private static final SyntaxError wrongSetDelegateMustBeProtected = new SyntaxError("wrong @SetDelegate: must be protected");
/*      */     
/*      */ 
/*  108 */     private static final SyntaxError wrongSetDelegateMustBeAbstract = new SyntaxError("wrong @SetDelegate: must be abstract");
/*      */     
/*      */ 
/*      */     private static SyntaxError mustBeClass(Class paramClass)
/*      */     {
/*  113 */       return new SyntaxError(paramClass.getName() + " must be an abstract or concrete class");
/*      */     }
/*      */     
/*      */ 
/*      */     private static SyntaxError mustBeIface(Class paramClass)
/*      */     {
/*  119 */       return new SyntaxError(paramClass.getName() + " must be an interface");
/*      */     }
/*      */     
/*      */ 
/*      */     private static SyntaxError annotationDefinedMoreThanOnce(String paramString)
/*      */     {
/*  125 */       return new SyntaxError(paramString + " is defined more than once for the same method");
/*      */     }
/*      */     
/*      */ 
/*      */     private static SyntaxError noProxyForClass(Class paramClass)
/*      */     {
/*  131 */       return new SyntaxError("no @ProxyFor for class " + paramClass.getName());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     private static SyntaxError returnTypeMismatch(Method paramMethod1, Method paramMethod2)
/*      */     {
/*  138 */       return new SyntaxError("interceptor " + paramMethod1.getName() + " and interceptee " + paramMethod2.getName() + ": have different return types (" + paramMethod1.getReturnType().getName() + " and " + paramMethod2.getReturnType().getName() + ")");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  148 */   private Map<Class, Value> ifacesToAnnotatedSuperclasses = new HashMap();
/*      */   
/*      */ 
/*      */   void register(Class... paramVarArgs)
/*      */   {
/*      */     Value localValue;
/*      */     
/*  155 */     for (Class localClass1 : paramVarArgs)
/*      */     {
/*  157 */       if (localClass1.isInterface()) {
/*  158 */         throw SyntaxError.mustBeClass(localClass1);
/*      */       }
/*  160 */       localValue = new Value(localClass1);
/*      */       
/*  162 */       for (Class localClass2 : localValue.getIfacesToProxy()) {
/*  163 */         this.ifacesToAnnotatedSuperclasses.put(localClass2, localValue);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   Value get(Class paramClass) {
/*  169 */     return (Value)this.ifacesToAnnotatedSuperclasses.get(paramClass);
/*      */   }
/*      */   
/*      */   Set<Class> keySet()
/*      */   {
/*  174 */     return this.ifacesToAnnotatedSuperclasses.keySet();
/*      */   }
/*      */   
/*      */   Collection<Value> values()
/*      */   {
/*  179 */     return this.ifacesToAnnotatedSuperclasses.values();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*  184 */   boolean containsKey(Object paramObject) { return this.ifacesToAnnotatedSuperclasses.containsKey(paramObject); }
/*      */   
/*      */   static class Value {
/*      */     private final Class superclass;
/*      */     
/*      */     private static class MethodSpecific<T> {
/*      */       static final class Pres extends AnnotationsRegistry.Value.MethodSpecific<Method> {
/*  191 */         Pres() { super(null); } }
/*  192 */       static final class VoidPosts extends AnnotationsRegistry.Value.MethodSpecific<Method> { VoidPosts() { super(null); } }
/*  193 */       static final class ReturningPosts extends AnnotationsRegistry.Value.MethodSpecific<Method> { ReturningPosts() { super(null); } }
/*  194 */       static final class VoidOnErrors extends AnnotationsRegistry.Value.MethodSpecific<Map<Class, Method>> { VoidOnErrors() { super(null); } }
/*  195 */       static final class ReturningOnErrors extends AnnotationsRegistry.Value.MethodSpecific<Map<Class, Method>> { ReturningOnErrors() { super(null); } }
/*      */       
/*  197 */       private final Map<MethodSignature, T> ref = new HashMap();
/*      */       
/*      */       private final String annotationType;
/*      */       
/*      */ 
/*      */       private MethodSpecific(String paramString)
/*      */       {
/*  204 */         this.annotationType = paramString;
/*      */       }
/*      */       
/*      */       void put(MethodSignature paramMethodSignature, T paramT)
/*      */       {
/*  209 */         if (null != this.ref.put(paramMethodSignature, paramT)) {
/*  210 */           throw AnnotationsRegistry.SyntaxError.access$200(this.annotationType);
/*      */         }
/*      */       }
/*      */       
/*      */       T get(MethodSignature paramMethodSignature) {
/*  215 */         return (T)this.ref.get(paramMethodSignature);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     private static class Rest
/*      */     {
/*      */       private final Method pre;
/*      */       
/*      */ 
/*      */       private final Method voidPost;
/*      */       
/*      */       private final Method returningPost;
/*      */       
/*      */       private final Map<Class, Method> voidOnErrorsMap;
/*      */       
/*      */       private final Map<Class, Method> returningOnErrorsMap;
/*      */       
/*      */ 
/*      */       Rest(Method paramMethod1, Method paramMethod2, Method paramMethod3, Map<Class, Method> paramMap1, Map<Class, Method> paramMap2)
/*      */       {
/*  237 */         this.pre = paramMethod1;
/*  238 */         this.voidPost = paramMethod2;
/*  239 */         this.returningPost = paramMethod3;
/*  240 */         this.voidOnErrorsMap = paramMap1;
/*  241 */         this.returningOnErrorsMap = paramMap2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       Method getPre()
/*      */       {
/*  249 */         return this.pre;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       Map<Class, Method> getReturningOnError()
/*      */       {
/*  257 */         return this.returningOnErrorsMap;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       Method getReturningPost()
/*      */       {
/*  265 */         return this.returningPost;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       Map<Class, Method> getVoidOnError()
/*      */       {
/*  273 */         return this.voidOnErrorsMap;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       Method getVoidPost()
/*      */       {
/*  281 */         return this.voidPost;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  286 */     private final List<Class> ifacesToProxy = new ArrayList();
/*      */     
/*  288 */     private final AnnotationsRegistry.Value.MethodSpecific.Pres pres = new AnnotationsRegistry.Value.MethodSpecific.Pres();
/*  289 */     private final AnnotationsRegistry.Value.MethodSpecific.VoidPosts voidPosts = new AnnotationsRegistry.Value.MethodSpecific.VoidPosts();
/*  290 */     private final AnnotationsRegistry.Value.MethodSpecific.ReturningPosts returningPosts = new AnnotationsRegistry.Value.MethodSpecific.ReturningPosts();
/*  291 */     private final AnnotationsRegistry.Value.MethodSpecific.VoidOnErrors voidOnErrors = new AnnotationsRegistry.Value.MethodSpecific.VoidOnErrors();
/*  292 */     private final AnnotationsRegistry.Value.MethodSpecific.ReturningOnErrors returningOnErrors = new AnnotationsRegistry.Value.MethodSpecific.ReturningOnErrors();
/*      */     
/*      */     private final Rest rest;
/*      */     
/*  296 */     private Method methodGetCreator = null;
/*  297 */     private Method methodGetDelegate = null;
/*  298 */     private Method methodGetProxy = null;
/*  299 */     private Method methodSetDelegate = null;
/*  300 */     private boolean isProxyLocale = false;
/*  301 */     private ProxyResultPolicy proxyResultPolicy = ProxyResultPolicy.CACHE;
/*      */     
/*      */     Value(Class paramClass)
/*      */     {
/*  305 */       this.superclass = paramClass;
/*  306 */       this.rest = parseAnnotations();
/*      */     }
/*      */     
/*  309 */     private Method pre = null; private Method voidPost = null; private Method returningPost = null;
/*  310 */     private Map<Class, Method> voidOnErrorsMap = new HashMap(); private Map<Class, Method> returningOnErrorsMap = new HashMap();
/*      */     
/*      */ 
/*      */ 
/*      */     private void parseAnnotationTypeProxyResult()
/*      */     {
/*  316 */       if (this.superclass.isAnnotationPresent(ProxyResult.class))
/*      */       {
/*  318 */         ProxyResult localProxyResult = (ProxyResult)this.superclass.getAnnotation(ProxyResult.class);
/*  319 */         this.proxyResultPolicy = localProxyResult.value();
/*      */       }
/*      */     }
/*      */     
/*      */     private void parseAnnotationProxyLocale()
/*      */     {
/*  325 */       if (this.superclass.isAnnotationPresent(ProxyLocale.class)) {
/*  326 */         this.isProxyLocale = true;
/*      */       }
/*      */     }
/*      */     
/*      */     private void parseAnnotationProxyFor() {
/*  331 */       if (this.superclass.isAnnotationPresent(ProxyFor.class))
/*      */       {
/*  333 */         ProxyFor localProxyFor = (ProxyFor)this.superclass.getAnnotation(ProxyFor.class);
/*      */         
/*  335 */         for (Class localClass : localProxyFor.value())
/*      */         {
/*  337 */           if (!localClass.isInterface()) {
/*  338 */             throw AnnotationsRegistry.SyntaxError.access$300(localClass);
/*      */           }
/*  340 */           this.ifacesToProxy.add(localClass);
/*      */         }
/*      */       }
/*      */       else {
/*  344 */         throw AnnotationsRegistry.SyntaxError.access$400(this.superclass);
/*      */       } }
/*      */     
/*  347 */     private static final Class[] listOfMethodOperators = { Pre.class, Post.class, OnError.class, GetCreator.class, GetDelegate.class, GetProxy.class, SetDelegate.class };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private void checkIsSingle(Method paramMethod, Class paramClass)
/*      */     {
/*  355 */       for (Class localClass : listOfMethodOperators) {
/*  356 */         if ((!localClass.equals(paramClass)) && 
/*  357 */           (paramMethod.isAnnotationPresent(localClass)))
/*  358 */           throw AnnotationsRegistry.SyntaxError.access$500();
/*      */       }
/*      */     }
/*      */     
/*      */     private void parseAnnotationPre(Method paramMethod) {
/*  363 */       if (paramMethod.isAnnotationPresent(Pre.class))
/*      */       {
/*  365 */         checkIsSingle(paramMethod, Pre.class);
/*      */         
/*  367 */         if (!Arrays.deepEquals(new Class[0], paramMethod.getExceptionTypes()))
/*      */         {
/*      */ 
/*  370 */           throw AnnotationsRegistry.SyntaxError.access$600();
/*      */         }
/*  372 */         if (!Arrays.deepEquals(new Class[] { Method.class, Object.class, Object[].class }, paramMethod.getParameterTypes()))
/*      */         {
/*      */ 
/*  375 */           throw AnnotationsRegistry.SyntaxError.access$600();
/*      */         }
/*  377 */         if (!Void.TYPE.equals(paramMethod.getReturnType())) {
/*  378 */           throw AnnotationsRegistry.SyntaxError.access$600();
/*      */         }
/*  380 */         if (paramMethod.isAnnotationPresent(Methods.class)) {
/*  381 */           for (Signature localSignature : ((Methods)paramMethod.getAnnotation(Methods.class)).signatures()) {
/*  382 */             this.pres.put(new MethodSignature(localSignature.name(), localSignature.args(), null), paramMethod);
/*      */           }
/*      */         } else {
/*  385 */           if (null != this.pre) throw AnnotationsRegistry.SyntaxError.access$700();
/*  386 */           this.pre = paramMethod;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private Class doAutoBoxing(Class paramClass)
/*      */     {
/*  393 */       if (Boolean.TYPE.equals(paramClass)) {
/*  394 */         return Boolean.class;
/*      */       }
/*  396 */       if (Character.TYPE.equals(paramClass)) {
/*  397 */         return Character.class;
/*      */       }
/*  399 */       if (Byte.TYPE.equals(paramClass)) {
/*  400 */         return Byte.class;
/*      */       }
/*  402 */       if (Short.TYPE.equals(paramClass)) {
/*  403 */         return Short.class;
/*      */       }
/*  405 */       if (Integer.TYPE.equals(paramClass)) {
/*  406 */         return Integer.class;
/*      */       }
/*  408 */       if (Long.TYPE.equals(paramClass)) {
/*  409 */         return Long.class;
/*      */       }
/*  411 */       if (Float.TYPE.equals(paramClass)) {
/*  412 */         return Float.class;
/*      */       }
/*  414 */       if (Double.TYPE.equals(paramClass)) {
/*  415 */         return Double.class;
/*      */       }
/*  417 */       return paramClass;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private void checkReturnTypesMismatch(MethodSignature paramMethodSignature, Method paramMethod)
/*      */     {
/*  430 */       Method localMethod = null;
/*      */       
/*  432 */       Class localClass1 = doAutoBoxing(paramMethod.getReturnType());
/*      */       
/*      */ 
/*  435 */       for (Class localClass2 : getIfacesToProxy()) {
/*      */         try
/*      */         {
/*  438 */           localMethod = localClass2.getDeclaredMethod(paramMethodSignature.getName(), paramMethodSignature.getParameterTypes());
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  443 */           Class localClass3 = doAutoBoxing(localMethod.getReturnType());
/*      */           
/*      */ 
/*  446 */           if (!Void.TYPE.equals(localClass3))
/*      */           {
/*      */ 
/*  449 */             localClass1.asSubclass(localClass3);
/*      */           }
/*      */           
/*      */ 
/*      */         }
/*      */         catch (NoSuchMethodException localNoSuchMethodException) {}catch (ClassCastException localClassCastException)
/*      */         {
/*      */ 
/*  457 */           throw AnnotationsRegistry.SyntaxError.access$800(paramMethod, localMethod);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private void checkReturnTypesMismatch(Method paramMethod)
/*      */     {
/*  472 */       Class localClass1 = doAutoBoxing(paramMethod.getReturnType());
/*      */       
/*      */ 
/*  475 */       for (Class localClass2 : getIfacesToProxy()) {
/*  476 */         for (Method localMethod : localClass2.getDeclaredMethods())
/*      */         {
/*  478 */           Class localClass3 = doAutoBoxing(localMethod.getReturnType());
/*      */           
/*      */ 
/*  481 */           if (!Void.TYPE.equals(localClass3))
/*      */           {
/*      */             try
/*      */             {
/*      */ 
/*  486 */               localClass3.asSubclass(localClass1);
/*      */             }
/*      */             catch (ClassCastException localClassCastException)
/*      */             {
/*  490 */               throw AnnotationsRegistry.SyntaxError.access$800(paramMethod, localMethod);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private void parseAnnotationPost(Method paramMethod)
/*      */     {
/*  499 */       if (paramMethod.isAnnotationPresent(Post.class))
/*      */       {
/*  501 */         checkIsSingle(paramMethod, Post.class);
/*      */         
/*  503 */         Class localClass = paramMethod.getReturnType();
/*  504 */         Class[] arrayOfClass1 = paramMethod.getParameterTypes();
/*  505 */         Class[] arrayOfClass2 = paramMethod.getExceptionTypes();
/*      */         
/*  507 */         if (!Arrays.deepEquals(new Class[0], arrayOfClass2))
/*  508 */           throw AnnotationsRegistry.SyntaxError.access$900();
/*      */         Signature localSignature;
/*  510 */         if (Void.TYPE.equals(localClass)) { if (Arrays.deepEquals(new Class[] { Method.class }, arrayOfClass1))
/*      */           {
/*      */ 
/*  513 */             if (paramMethod.isAnnotationPresent(Methods.class)) {
/*  514 */               for (localSignature : ((Methods)paramMethod.getAnnotation(Methods.class)).signatures())
/*  515 */                 this.voidPosts.put(new MethodSignature(localSignature.name(), localSignature.args(), null), paramMethod);
/*      */               return;
/*      */             }
/*  518 */             if (null != this.voidPost) throw AnnotationsRegistry.SyntaxError.access$700();
/*  519 */             this.voidPost = paramMethod; return;
/*      */           }
/*      */         }
/*  522 */         if (!Void.TYPE.equals(localClass)) { if (Arrays.deepEquals(new Class[] { Method.class, localClass }, arrayOfClass1))
/*      */           {
/*      */ 
/*  525 */             if (paramMethod.isAnnotationPresent(Methods.class)) {
/*  526 */               for (localSignature : ((Methods)paramMethod.getAnnotation(Methods.class)).signatures())
/*      */               {
/*  528 */                 MethodSignature localMethodSignature = new MethodSignature(localSignature.name(), localSignature.args(), null);
/*      */                 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  534 */                 checkReturnTypesMismatch(localMethodSignature, paramMethod);
/*  535 */                 this.returningPosts.put(localMethodSignature, paramMethod);
/*      */               }
/*      */               return;
/*      */             }
/*  539 */             checkReturnTypesMismatch(paramMethod);
/*      */             
/*  541 */             if (null != this.returningPost) throw AnnotationsRegistry.SyntaxError.access$700();
/*  542 */             this.returningPost = paramMethod; return;
/*      */           }
/*      */         }
/*      */         
/*  546 */         throw AnnotationsRegistry.SyntaxError.access$900();
/*      */       }
/*      */     }
/*      */     
/*      */     private void parseAnnotationOnError(Method paramMethod)
/*      */     {
/*  552 */       if (paramMethod.isAnnotationPresent(OnError.class))
/*      */       {
/*  554 */         checkIsSingle(paramMethod, OnError.class);
/*      */         
/*  556 */         Class localClass1 = paramMethod.getReturnType();
/*  557 */         Class[] arrayOfClass1 = paramMethod.getParameterTypes();
/*  558 */         Class[] arrayOfClass2 = paramMethod.getExceptionTypes();
/*      */         
/*  560 */         OnError localOnError = (OnError)paramMethod.getAnnotation(OnError.class);
/*  561 */         Class localClass2 = localOnError.value();
/*      */         Signature localSignature;
/*  563 */         MethodSignature localMethodSignature; Object localObject; if (Arrays.deepEquals(new Class[] { Method.class, localClass2 }, arrayOfClass1)) if ((Arrays.deepEquals(new Class[] { localClass2 }, arrayOfClass2)) && (Void.TYPE.equals(localClass1)))
/*      */           {
/*      */ 
/*      */ 
/*  567 */             if (paramMethod.isAnnotationPresent(Methods.class)) {
/*  568 */               for (localSignature : ((Methods)paramMethod.getAnnotation(Methods.class)).signatures())
/*      */               {
/*  570 */                 localMethodSignature = new MethodSignature(localSignature.name(), localSignature.args(), null);
/*      */                 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  576 */                 localObject = (Map)this.voidOnErrors.get(localMethodSignature);
/*  577 */                 if (null == localObject) {
/*  578 */                   this.voidOnErrors.put(localMethodSignature, localObject = new HashMap());
/*      */                 }
/*  580 */                 if (null != ((Map)localObject).put(localClass2, paramMethod))
/*  581 */                   throw AnnotationsRegistry.SyntaxError.access$1000();
/*      */               }
/*      */               return; }
/*  584 */             if (null == this.voidOnErrorsMap.put(localClass2, paramMethod)) return;
/*  585 */             throw AnnotationsRegistry.SyntaxError.access$700();
/*      */           }
/*  587 */         if (Arrays.deepEquals(new Class[] { Method.class, localClass2 }, arrayOfClass1)) { if ((Arrays.deepEquals(new Class[] { localClass2 }, arrayOfClass2)) && (!Void.TYPE.equals(localClass1)))
/*      */           {
/*      */ 
/*      */ 
/*  591 */             if (paramMethod.isAnnotationPresent(Methods.class)) {
/*  592 */               for (localSignature : ((Methods)paramMethod.getAnnotation(Methods.class)).signatures())
/*      */               {
/*  594 */                 localMethodSignature = new MethodSignature(localSignature.name(), localSignature.args(), null);
/*      */                 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  600 */                 checkReturnTypesMismatch(localMethodSignature, paramMethod);
/*      */                 
/*  602 */                 localObject = (Map)this.returningOnErrors.get(localMethodSignature);
/*  603 */                 if (null == localObject) {
/*  604 */                   this.returningOnErrors.put(localMethodSignature, localObject = new HashMap());
/*      */                 }
/*  606 */                 if (null != ((Map)localObject).put(localClass2, paramMethod))
/*  607 */                   throw AnnotationsRegistry.SyntaxError.access$1000();
/*      */               }
/*      */               return;
/*      */             }
/*  611 */             checkReturnTypesMismatch(paramMethod);
/*      */             
/*  613 */             if (null == this.returningOnErrorsMap.put(localClass2, paramMethod)) return;
/*  614 */             throw AnnotationsRegistry.SyntaxError.access$700();
/*      */           }
/*      */         }
/*      */         
/*  618 */         throw AnnotationsRegistry.SyntaxError.access$1100();
/*      */       }
/*      */     }
/*      */     
/*      */     private void parseAnnotationGetCreator(Method paramMethod)
/*      */     {
/*  624 */       if (paramMethod.isAnnotationPresent(GetCreator.class))
/*      */       {
/*  626 */         checkIsSingle(paramMethod, GetCreator.class);
/*      */         
/*  628 */         if (paramMethod.isAnnotationPresent(Methods.class)) {
/*  629 */           throw AnnotationsRegistry.SyntaxError.access$1200();
/*      */         }
/*  631 */         int i = paramMethod.getModifiers();
/*      */         
/*  633 */         if (!Modifier.isProtected(i)) {
/*  634 */           throw AnnotationsRegistry.SyntaxError.access$1300();
/*      */         }
/*  636 */         if (!Modifier.isAbstract(i)) {
/*  637 */           throw AnnotationsRegistry.SyntaxError.access$1400();
/*      */         }
/*  639 */         if (!Arrays.deepEquals(new Class[0], paramMethod.getParameterTypes())) {
/*  640 */           throw AnnotationsRegistry.SyntaxError.access$1500();
/*      */         }
/*  642 */         if (!Object.class.equals(paramMethod.getReturnType())) {
/*  643 */           throw AnnotationsRegistry.SyntaxError.access$1500();
/*      */         }
/*  645 */         this.methodGetCreator = paramMethod;
/*      */       }
/*      */     }
/*      */     
/*      */     private void parseAnnotationGetProxy(Method paramMethod)
/*      */     {
/*  651 */       if (paramMethod.isAnnotationPresent(GetProxy.class))
/*      */       {
/*  653 */         checkIsSingle(paramMethod, GetProxy.class);
/*      */         
/*  655 */         if (paramMethod.isAnnotationPresent(Methods.class)) {
/*  656 */           throw AnnotationsRegistry.SyntaxError.access$1200();
/*      */         }
/*  658 */         int i = paramMethod.getModifiers();
/*      */         
/*  660 */         if (!Modifier.isProtected(i)) {
/*  661 */           throw AnnotationsRegistry.SyntaxError.access$1600();
/*      */         }
/*  663 */         if (!Modifier.isAbstract(i)) {
/*  664 */           throw AnnotationsRegistry.SyntaxError.access$1700();
/*      */         }
/*  666 */         if (!Arrays.deepEquals(new Class[] { Object.class, Object.class }, paramMethod.getParameterTypes()))
/*      */         {
/*      */ 
/*  669 */           throw AnnotationsRegistry.SyntaxError.access$1800();
/*      */         }
/*  671 */         if (!Object.class.equals(paramMethod.getReturnType())) {
/*  672 */           throw AnnotationsRegistry.SyntaxError.access$1800();
/*      */         }
/*  674 */         this.methodGetProxy = paramMethod;
/*      */       }
/*      */     }
/*      */     
/*      */     private void parseAnnotationGetDelegate(Method paramMethod)
/*      */     {
/*  680 */       if (paramMethod.isAnnotationPresent(GetDelegate.class))
/*      */       {
/*  682 */         checkIsSingle(paramMethod, GetDelegate.class);
/*      */         
/*  684 */         if (paramMethod.isAnnotationPresent(Methods.class)) {
/*  685 */           throw AnnotationsRegistry.SyntaxError.access$1200();
/*      */         }
/*  687 */         int i = paramMethod.getModifiers();
/*      */         
/*  689 */         if (!Modifier.isProtected(i)) {
/*  690 */           throw AnnotationsRegistry.SyntaxError.access$1900();
/*      */         }
/*  692 */         if (!Modifier.isAbstract(i)) {
/*  693 */           throw AnnotationsRegistry.SyntaxError.access$2000();
/*      */         }
/*  695 */         if (!Arrays.deepEquals(new Class[0], paramMethod.getParameterTypes())) {
/*  696 */           throw AnnotationsRegistry.SyntaxError.access$2100();
/*      */         }
/*  698 */         if (Void.TYPE.equals(paramMethod.getReturnType())) {
/*  699 */           throw AnnotationsRegistry.SyntaxError.access$2100();
/*      */         }
/*  701 */         this.methodGetDelegate = paramMethod;
/*      */       }
/*      */     }
/*      */     
/*      */     private void parseAnnotationSetDelegate(Method paramMethod)
/*      */     {
/*  707 */       if (paramMethod.isAnnotationPresent(SetDelegate.class))
/*      */       {
/*  709 */         checkIsSingle(paramMethod, SetDelegate.class);
/*      */         
/*  711 */         if (paramMethod.isAnnotationPresent(Methods.class)) {
/*  712 */           throw AnnotationsRegistry.SyntaxError.access$1200();
/*      */         }
/*  714 */         int i = paramMethod.getModifiers();
/*      */         
/*  716 */         if (!Modifier.isProtected(i)) {
/*  717 */           throw AnnotationsRegistry.SyntaxError.access$2200();
/*      */         }
/*  719 */         if (!Modifier.isAbstract(i)) {
/*  720 */           throw AnnotationsRegistry.SyntaxError.access$2300();
/*      */         }
/*  722 */         if (1 != paramMethod.getParameterTypes().length) {
/*  723 */           throw AnnotationsRegistry.SyntaxError.access$2400();
/*      */         }
/*  725 */         if (!Void.TYPE.equals(paramMethod.getReturnType())) {
/*  726 */           throw AnnotationsRegistry.SyntaxError.access$2400();
/*      */         }
/*  728 */         this.methodSetDelegate = paramMethod;
/*      */       }
/*      */     }
/*      */     
/*      */     private Rest parseAnnotations()
/*      */     {
/*  734 */       parseAnnotationProxyFor();
/*  735 */       parseAnnotationProxyLocale();
/*  736 */       parseAnnotationTypeProxyResult();
/*      */       
/*  738 */       for (Method localMethod : this.superclass.getDeclaredMethods())
/*      */       {
/*  740 */         parseAnnotationPre(localMethod);
/*  741 */         parseAnnotationPost(localMethod);
/*  742 */         parseAnnotationOnError(localMethod);
/*  743 */         parseAnnotationGetCreator(localMethod);
/*  744 */         parseAnnotationGetProxy(localMethod);
/*  745 */         parseAnnotationGetDelegate(localMethod);
/*  746 */         parseAnnotationSetDelegate(localMethod);
/*      */       }
/*      */       
/*  749 */       return new Rest(this.pre, this.voidPost, this.returningPost, this.voidOnErrorsMap, this.returningOnErrorsMap);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     boolean belongsToIfaceToProxy(Class paramClass, MethodSignature paramMethodSignature)
/*      */     {
/*  764 */       for (Class localClass : this.ifacesToProxy) {
/*      */         try
/*      */         {
/*  767 */           paramClass.asSubclass(localClass);
/*      */           
/*  769 */           if (isMethodDeclared(localClass, paramMethodSignature)) {
/*  770 */             return true;
/*      */           }
/*      */         } catch (ClassCastException localClassCastException) {}
/*      */       }
/*  774 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     private boolean isMethodDeclared(Class paramClass, MethodSignature paramMethodSignature)
/*      */     {
/*      */       try
/*      */       {
/*  783 */         if (null != paramClass.getDeclaredMethod(paramMethodSignature.getName(), paramMethodSignature.getParameterTypes()))
/*      */         {
/*      */ 
/*  786 */           return true;
/*      */         }
/*      */       }
/*      */       catch (NoSuchMethodException localNoSuchMethodException) {}
/*  790 */       for (Class localClass : paramClass.getInterfaces()) {
/*  791 */         if (isMethodDeclared(localClass, paramMethodSignature))
/*  792 */           return true;
/*      */       }
/*  794 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     Method getMethodPre(Class paramClass, MethodSignature paramMethodSignature)
/*      */     {
/*  810 */       Method localMethod = (Method)this.pres.get(paramMethodSignature);
/*      */       
/*      */ 
/*  813 */       if (null != localMethod) {
/*  814 */         return localMethod;
/*      */       }
/*  816 */       return belongsToIfaceToProxy(paramClass, paramMethodSignature) ? this.rest.getPre() : null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     Method getMethodVoidPost(Class paramClass, MethodSignature paramMethodSignature)
/*      */     {
/*  834 */       Method localMethod = (Method)this.voidPosts.get(paramMethodSignature);
/*      */       
/*      */ 
/*  837 */       if (null != localMethod) {
/*  838 */         return localMethod;
/*      */       }
/*  840 */       return belongsToIfaceToProxy(paramClass, paramMethodSignature) ? this.rest.getVoidPost() : null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     Method getMethodReturningPost(Class paramClass, MethodSignature paramMethodSignature)
/*      */     {
/*  858 */       Method localMethod = (Method)this.returningPosts.get(paramMethodSignature);
/*      */       
/*      */ 
/*  861 */       if (null != localMethod) {
/*  862 */         return localMethod;
/*      */       }
/*  864 */       return belongsToIfaceToProxy(paramClass, paramMethodSignature) ? this.rest.getReturningPost() : null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     Map<Class, Method> getMapVoidOnError(Class paramClass, MethodSignature paramMethodSignature)
/*      */     {
/*  882 */       Map localMap = (Map)this.voidOnErrors.get(paramMethodSignature);
/*      */       
/*      */ 
/*  885 */       if (null != localMap) {
/*  886 */         return localMap;
/*      */       }
/*  888 */       return belongsToIfaceToProxy(paramClass, paramMethodSignature) ? this.rest.getVoidOnError() : null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     Map<Class, Method> getMapReturningOnError(Class paramClass, MethodSignature paramMethodSignature)
/*      */     {
/*  906 */       Map localMap = (Map)this.returningOnErrors.get(paramMethodSignature);
/*      */       
/*      */ 
/*  909 */       if (null != localMap) {
/*  910 */         return localMap;
/*      */       }
/*  912 */       return belongsToIfaceToProxy(paramClass, paramMethodSignature) ? this.rest.getReturningOnError() : null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     Method getMethodGetCreator()
/*      */     {
/*  922 */       return this.methodGetCreator;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     Method getMethodGetDelegate()
/*      */     {
/*  930 */       return this.methodGetDelegate;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     Method getMethodGetProxy()
/*      */     {
/*  938 */       return this.methodGetProxy;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     Method getMethodSetDelegate()
/*      */     {
/*  946 */       return this.methodSetDelegate;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     List<Class> getIfacesToProxy()
/*      */     {
/*  954 */       return this.ifacesToProxy;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     Class getSuperclass()
/*      */     {
/*  962 */       return this.superclass;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     boolean isProxyLocale()
/*      */     {
/*  970 */       return this.isProxyLocale;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     ProxyResultPolicy getProxyResultPolicy()
/*      */     {
/*  979 */       return this.proxyResultPolicy;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     ProxyResultPolicy getProxyResultPolicy(Method paramMethod)
/*      */     {
/*      */       Method localMethod;
/*      */       
/*      */ 
/*      */ 
/*      */       try
/*      */       {
/*  994 */         localMethod = this.superclass.getDeclaredMethod(paramMethod.getName(), paramMethod.getParameterTypes());
/*      */       }
/*      */       catch (NoSuchMethodException localNoSuchMethodException)
/*      */       {
/*  998 */         return this.proxyResultPolicy;
/*      */       }
/*      */       
/* 1001 */       if (localMethod.isAnnotationPresent(ProxyResult.class))
/*      */       {
/* 1003 */         ProxyResult localProxyResult = (ProxyResult)localMethod.getAnnotation(ProxyResult.class);
/* 1004 */         return localProxyResult.value();
/*      */       }
/*      */       
/* 1007 */       return this.proxyResultPolicy;
/*      */     }
/*      */   }
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/AnnotationsRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */