package oracle.jdbc.proxy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class ProxyFactory
{
/*  32 */   final AnnotationsRegistry annotationsRegistry = new AnnotationsRegistry();
/*  33 */   private final GeneratedProxiesRegistry generatedRegistry = new GeneratedProxiesRegistry();
/*  34 */   private Map<Class, Class> delegateClassToProxyClass = Collections.synchronizedMap(new HashMap());
/*  35 */   private final Map<Object, WeakReference<Object>> delegateToProxy = Collections.synchronizedMap(new WeakIdentityHashMap());
/*  36 */   private final Map<Class, Class> delegateToMostSuitableIface = Collections.synchronizedMap(new HashMap());
  
/*  38 */   private static final Object STALE_DELEGATE = new Object();
  
/*  40 */   private static final Class EMPTY_VALUE = EMPTY_CLASS.class;
  
  public static ProxyFactory createProxyFactory(Class... paramVarArgs)
  {
/*  51 */     ProxyFactory localProxyFactory = new ProxyFactory();
/*  52 */     localProxyFactory.annotationsRegistry.register(paramVarArgs);
/*  53 */     return localProxyFactory;
  }
  
  public static ProxyFactory createJDBCProxyFactory(Class... paramVarArgs)
  {
/*  67 */     ProxyFactory localProxyFactory = new ProxyFactory();
/*  68 */     localProxyFactory.annotationsRegistry.register(new Class[] { NullProxy.class });
/*  69 */     localProxyFactory.annotationsRegistry.register(paramVarArgs);
/*  70 */     return localProxyFactory;
  }
  
  public final boolean isProxied(Class paramClass)
  {
/*  80 */     return this.annotationsRegistry.containsKey(paramClass);
  }
  
  public final <T> T proxyFor(T paramT)
  {
/*  92 */     return (T)proxyFor(paramT, this);
  }
  
  public final <T> T proxyFor(T paramT, Object paramObject)
  {
/* 106 */     return (T)proxyForCache(paramT, paramObject, null, null);
  }
  
  public final <T> T proxyForCreate(T paramT, Object paramObject, Map<Object, WeakReference<Object>> paramMap, Method paramMethod)
  {
/* 139 */     if (null == paramT) {
/* 140 */       return null;
    }
/* 142 */     Class localClass1 = paramT.getClass();
/* 143 */     Class localClass2 = findMostSuitableIface(localClass1);
    
/* 146 */     if ((null != paramMethod) && (null != localClass2) && 
/* 147 */       (!paramMethod.getReturnType().isAssignableFrom(localClass2))) {
/* 148 */       return paramT;
    }
/* 150 */     AnnotationsRegistry.Value localValue = this.annotationsRegistry.get(localClass2);
/* 151 */     if (null == localValue) {
/* 152 */       return paramT;
    }
/* 154 */     if (null == paramMap) {
/* 155 */       paramMap = localValue.isProxyLocale() ? new WeakIdentityHashMap() : this.delegateToProxy;
    }
    
/* 159 */     Class localClass3 = getProxyClass(localClass2, localClass1);
/* 160 */     if (null == localClass3) {
/* 161 */       return (T)createProxy(localClass2, paramT, paramObject, paramMap);
    }
    try
    {
/* 165 */       return (T)localClass3.getConstructor(new Class[] { localClass2, Object.class, ProxyFactory.class, Map.class }).newInstance(new Object[] { paramT, paramObject, this, paramMap });
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
/* 174 */       throw new RuntimeException(localNoSuchMethodException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
/* 178 */       throw new RuntimeException(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
/* 182 */       throw new RuntimeException(localInvocationTargetException);
    }
    catch (InstantiationException localInstantiationException)
    {
/* 186 */       throw new RuntimeException(localInstantiationException);
    }
  }
  
  public final <T> T proxyForCache(T paramT, Object paramObject, Map<Object, WeakReference<Object>> paramMap, Method paramMethod)
  {
/* 220 */     if (null == paramT) {
/* 221 */       return null;
    }
/* 223 */     Class localClass1 = paramT.getClass();
/* 224 */     Class localClass2 = findMostSuitableIface(localClass1);
    
/* 227 */     if ((null != paramMethod) && (null != localClass2) && 
/* 228 */       (!paramMethod.getReturnType().isAssignableFrom(localClass2))) {
/* 229 */       return paramT;
    }
/* 231 */     AnnotationsRegistry.Value localValue = this.annotationsRegistry.get(localClass2);
/* 232 */     if (null == localValue) {
/* 233 */       return paramT;
    }
/* 235 */     if (null == paramMap) {
/* 236 */       paramMap = localValue.isProxyLocale() ? new WeakIdentityHashMap() : this.delegateToProxy;
    }
    
/* 240 */     WeakReference localWeakReference = (WeakReference)paramMap.get(paramT);
/* 241 */     if (null != localWeakReference)
    {
/* 243 */       localObject1 = localWeakReference.get();
/* 244 */       if (null != localObject1)
      {
/* 246 */         if (STALE_DELEGATE == localObject1) {
/* 247 */           throw new RuntimeException("stale delegate");
        }
/* 249 */         return (T)localObject1;
      }
    }
    
/* 253 */     Object localObject1 = getProxyClass(localClass2, localClass1);
/* 254 */     Object localObject2; if (null == localObject1)
    {
/* 256 */       localObject2 = createProxy(localClass2, paramT, paramObject, paramMap);
/* 257 */       paramMap.put(paramT, new WeakReference(localObject2));
/* 258 */       return (T)localObject2;
    }
    
    try
    {
/* 263 */       localObject2 = ((Class)localObject1).getConstructor(new Class[] { localClass2, Object.class, ProxyFactory.class, Map.class }).newInstance(new Object[] { paramT, paramObject, this, paramMap });
      
/* 270 */       paramMap.put(paramT, new WeakReference(localObject2));
/* 271 */       return (T)localObject2;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
/* 275 */       throw new RuntimeException(localNoSuchMethodException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
/* 279 */       throw new RuntimeException(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
/* 283 */       throw new RuntimeException(localInvocationTargetException);
    }
    catch (InstantiationException localInstantiationException)
    {
/* 287 */       throw new RuntimeException(localInstantiationException);
    }
  }
  
  public final <T> T proxyForCreateCache(T paramT, Object paramObject, Map<Object, WeakReference<Object>> paramMap, Method paramMethod)
  {
/* 322 */     if (null == paramT) {
/* 323 */       return null;
    }
/* 325 */     Class localClass1 = paramT.getClass();
/* 326 */     Class localClass2 = findMostSuitableIface(localClass1);
    
/* 329 */     if ((null != paramMethod) && (null != localClass2) && 
/* 330 */       (!paramMethod.getReturnType().isAssignableFrom(localClass2))) {
/* 331 */       return paramT;
    }
/* 333 */     AnnotationsRegistry.Value localValue = this.annotationsRegistry.get(localClass2);
/* 334 */     if (null == localValue) {
/* 335 */       return paramT;
    }
/* 337 */     if (null == paramMap) {
/* 338 */       paramMap = localValue.isProxyLocale() ? new WeakIdentityHashMap() : this.delegateToProxy;
    }
    
/* 342 */     Class localClass3 = getProxyClass(localClass2, localClass1);
/* 343 */     Object localObject; if (null == localClass3)
    {
/* 345 */       localObject = createProxy(localClass2, paramT, paramObject, paramMap);
/* 346 */       paramMap.put(paramT, new WeakReference(localObject));
/* 347 */       return (T)localObject;
    }
    
    try
    {
/* 352 */       localObject = localClass3.getConstructor(new Class[] { localClass2, Object.class, ProxyFactory.class, Map.class }).newInstance(new Object[] { paramT, paramObject, this, paramMap });
      
/* 359 */       paramMap.put(paramT, new WeakReference(localObject));
/* 360 */       return (T)localObject;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
/* 364 */       throw new RuntimeException(localNoSuchMethodException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
/* 368 */       throw new RuntimeException(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
/* 372 */       throw new RuntimeException(localInvocationTargetException);
    }
    catch (InstantiationException localInstantiationException)
    {
/* 376 */       throw new RuntimeException(localInstantiationException);
    }
  }
  
  public <T> void updateDelegate(Object paramObject, T paramT1, T paramT2)
  {
/* 399 */     this.delegateToProxy.put(paramT1, new WeakReference(STALE_DELEGATE));
/* 400 */     this.delegateToProxy.put(paramT2, new WeakReference(paramObject));
  }
  
/* 403 */   private static final ExtractDelegatePermission EXTRACT_DELEGATE_PERMISSION = new ExtractDelegatePermission();
  
  public static final Object extractDelegate(OracleProxy paramOracleProxy)
  {
/* 417 */     SecurityManager localSecurityManager = System.getSecurityManager();
/* 418 */     if (null != localSecurityManager) {
/* 419 */       localSecurityManager.checkPermission(EXTRACT_DELEGATE_PERMISSION);
    }
    
    _Proxy_ local_Proxy_;
    try
    {
/* 425 */       local_Proxy_ = (_Proxy_)paramOracleProxy;
    }
    catch (ClassCastException localClassCastException)
    {
/* 429 */       throw new IllegalArgumentException();
    }
    
/* 432 */     return local_Proxy_._getDelegate_();
  }
  
  private <T> T createProxy(Class paramClass, T paramT, Object paramObject, Map<Object, WeakReference<Object>> paramMap)
  {
/* 449 */     if (null == paramClass) {
/* 450 */       return paramT;
    }
/* 452 */     AnnotationsRegistry.Value localValue = this.annotationsRegistry.get(paramClass);
    
/* 454 */     Class localClass = localValue.getSuperclass();
    
/* 456 */     GeneratedProxiesRegistry.Value localValue1 = this.generatedRegistry.get(paramClass, localClass);
    
/* 459 */     Constructor localConstructor = null == localValue1 ? prepareProxy(paramClass, localClass) : localValue1.getConstructor();
    
    try
    {
/* 466 */       return (T)localConstructor.newInstance(new Object[] { paramT, paramObject, this, paramMap });
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
/* 470 */       throw new RuntimeException(localInvocationTargetException.getTargetException());
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
/* 474 */       throw new RuntimeException(localIllegalAccessException);
    }
    catch (InstantiationException localInstantiationException)
    {
/* 478 */       throw new RuntimeException(localInstantiationException);
    }
  }
  
  private Constructor prepareProxy(Class paramClass1, Class paramClass2)
  {
/* 486 */     Class localClass = null;
    
    try
    {
/* 491 */       localClass = Class.forName(new GeneratedProxiesRegistry.Key(paramClass1, paramClass2).toString());
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
/* 497 */       localClass = ClassGenerator.generate(paramClass1, paramClass2, this.annotationsRegistry);
    }
    
    Constructor localConstructor;
    
    try
    {
/* 507 */       localConstructor = localClass.getConstructor(new Class[] { paramClass1, Object.class, ProxyFactory.class, Map.class });
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
/* 515 */       throw new RuntimeException(localNoSuchMethodException);
    }
    
/* 518 */     this.generatedRegistry.put(paramClass1, paramClass2, new GeneratedProxiesRegistry.Value(null, null, localClass, localConstructor));
    
/* 525 */     return localConstructor;
  }
  
  private Class getProxyClass(Class paramClass1, Class paramClass2)
  {
/* 532 */     if (null == paramClass2) {
/* 533 */       return null;
    }
    
/* 537 */     Object localObject = (Class)this.delegateClassToProxyClass.get(paramClass2);
/* 538 */     if (null != localObject) {
/* 539 */       return (Class)(EMPTY_VALUE != localObject ? localObject : null);
    }
    
/* 542 */     if (null == paramClass1) {
/* 543 */       return null;
    }
/* 545 */     localObject = this.generatedRegistry.get(paramClass1, this.annotationsRegistry.get(paramClass1).getSuperclass());
    
/* 550 */     if (null == localObject) {
/* 551 */       return null;
    }
/* 553 */     Class localClass = ((GeneratedProxiesRegistry.Value)localObject).getClazz();
    
/* 556 */     this.delegateClassToProxyClass.put(paramClass2, null != localClass ? localClass : EMPTY_VALUE);
    
/* 558 */     return localClass;
  }
  
  private Class findMostSuitableIface(Class paramClass)
  {
/* 570 */     if (null == paramClass) {
/* 571 */       return null;
    }
    
/* 576 */     Class localClass1 = (Class)this.delegateToMostSuitableIface.get(paramClass);
/* 577 */     if (null != localClass1) {
/* 578 */       return EMPTY_VALUE != localClass1 ? localClass1 : null;
    }
    
/* 581 */     int i = -1;
/* 582 */     Object localObject = null;
/* 583 */     for (Class localClass2 : this.annotationsRegistry.keySet())
    {
/* 585 */       int j = intersectionCardinality(paramClass, localClass2);
/* 586 */       if ((j >= 1) && 
      
/* 588 */         (j > i))
      {
/* 592 */         i = j;
/* 593 */         localObject = localClass2;
      }
    }
    
/* 598 */     this.delegateToMostSuitableIface.put(paramClass, null != localObject ? localObject : EMPTY_VALUE);
    
/* 600 */     return (Class)localObject;
  }
  
  private int intersectionCardinality(Class paramClass1, Class paramClass2)
  {
/* 607 */     HashSet localHashSet1 = new HashSet();
    
/* 610 */     collectIfaces(paramClass2, localHashSet1);
    
/* 612 */     HashSet localHashSet2 = new HashSet();
    
/* 615 */     collectIfaces(paramClass1, localHashSet2);
    
/* 617 */     int i = localHashSet1.size();
/* 618 */     localHashSet1.removeAll(localHashSet2);
    
/* 620 */     if (localHashSet1.size() > 0) {
/* 621 */       return -1;
    }
/* 623 */     return i;
  }
  
  private void collectIfaces(Class paramClass, Set<Class> paramSet)
  {
/* 630 */     if (paramClass.isInterface()) {
/* 631 */       paramSet.add(paramClass);
    }
/* 633 */     for (Class localClass : paramClass.getInterfaces()) {
/* 634 */       collectIfaces(localClass, paramSet);
    }
/* 636 */     ??? = paramClass.getSuperclass();
/* 637 */     if (null != ???) {
/* 638 */       collectIfaces((Class)???, paramSet);
    }
  }
  
  private static final class EMPTY_CLASS {}
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/ProxyFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */