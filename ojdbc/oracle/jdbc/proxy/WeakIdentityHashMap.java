/*      */ package oracle.jdbc.proxy;
/*      */ 
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.ReferenceQueue;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractMap.SimpleEntry;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WeakIdentityHashMap<K, V>
/*      */   implements Map<K, V>
/*      */ {
/*      */   private static final int DEFAULT_INITIAL_CAPACITY = 16;
/*      */   private static final int MAXIMUM_CAPACITY = 1073741824;
/*      */   private static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*      */   Entry<K, V>[] table;
/*      */   private int size;
/*      */   private int threshold;
/*      */   private final float loadFactor;
/*  171 */   private final ReferenceQueue<Object> queue = new ReferenceQueue();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   volatile int modCount;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Entry<K, V>[] newTable(int paramInt)
/*      */   {
/*  186 */     return (Entry[])new Entry[paramInt];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public WeakIdentityHashMap(int paramInt, float paramFloat)
/*      */   {
/*  199 */     if (paramInt < 0) {
/*  200 */       throw new IllegalArgumentException("Illegal Initial Capacity: " + paramInt);
/*      */     }
/*  202 */     if (paramInt > 1073741824) {
/*  203 */       paramInt = 1073741824;
/*      */     }
/*  205 */     if ((paramFloat <= 0.0F) || (Float.isNaN(paramFloat))) {
/*  206 */       throw new IllegalArgumentException("Illegal Load factor: " + paramFloat);
/*      */     }
/*  208 */     int i = 1;
/*  209 */     while (i < paramInt)
/*  210 */       i <<= 1;
/*  211 */     this.table = newTable(i);
/*  212 */     this.loadFactor = paramFloat;
/*  213 */     this.threshold = ((int)(i * paramFloat));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public WeakIdentityHashMap(int paramInt)
/*      */   {
/*  224 */     this(paramInt, 0.75F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public WeakIdentityHashMap()
/*      */   {
/*  232 */     this.loadFactor = 0.75F;
/*  233 */     this.threshold = 16;
/*  234 */     this.table = newTable(16);
/*      */   }
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
/*      */   public WeakIdentityHashMap(Map<? extends K, ? extends V> paramMap)
/*      */   {
/*  248 */     this(Math.max((int)(paramMap.size() / 0.75F) + 1, 16), 0.75F);
/*      */     
/*  250 */     putAll(paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  258 */   private static final Object NULL_KEY = new Object();
/*      */   
/*      */ 
/*      */ 
/*      */   private static Object maskNull(Object paramObject)
/*      */   {
/*  264 */     return paramObject == null ? NULL_KEY : paramObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static Object unmaskNull(Object paramObject)
/*      */   {
/*  271 */     return paramObject == NULL_KEY ? null : paramObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static boolean eq(Object paramObject1, Object paramObject2)
/*      */   {
/*  278 */     return paramObject1 == paramObject2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static int indexFor(int paramInt1, int paramInt2)
/*      */   {
/*  285 */     return paramInt1 & paramInt2 - 1;
/*      */   }
/*      */   
/*      */ 
/*      */   private void expungeStaleEntries()
/*      */   {
/*      */     Reference localReference;
/*  292 */     while ((localReference = this.queue.poll()) != null) {
/*  293 */       synchronized (this.queue)
/*      */       {
/*  295 */         Entry localEntry1 = (Entry)localReference;
/*  296 */         int i = indexFor(localEntry1.hash, this.table.length);
/*      */         
/*  298 */         Object localObject1 = this.table[i];
/*  299 */         Object localObject2 = localObject1;
/*  300 */         while (localObject2 != null) {
/*  301 */           Entry localEntry2 = ((Entry)localObject2).next;
/*  302 */           if (localObject2 == localEntry1) {
/*  303 */             if (localObject1 == localEntry1) {
/*  304 */               this.table[i] = localEntry2;
/*      */             } else {
/*  306 */               ((Entry)localObject1).next = localEntry2;
/*      */             }
/*      */             
/*  309 */             localEntry1.value = null;
/*  310 */             this.size -= 1;
/*  311 */             break;
/*      */           }
/*  313 */           localObject1 = localObject2;
/*  314 */           localObject2 = localEntry2;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private Entry<K, V>[] getTable()
/*      */   {
/*  324 */     expungeStaleEntries();
/*  325 */     return this.table;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/*  335 */     if (this.size == 0)
/*  336 */       return 0;
/*  337 */     expungeStaleEntries();
/*  338 */     return this.size;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isEmpty()
/*      */   {
/*  348 */     return size() == 0;
/*      */   }
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
/*      */   public V get(Object paramObject)
/*      */   {
/*  369 */     Object localObject = maskNull(paramObject);
/*  370 */     int i = System.identityHashCode(localObject);
/*  371 */     Entry[] arrayOfEntry = getTable();
/*  372 */     int j = indexFor(i, arrayOfEntry.length);
/*  373 */     Entry localEntry = arrayOfEntry[j];
/*  374 */     while (localEntry != null) {
/*  375 */       if ((localEntry.hash == i) && (eq(localObject, localEntry.get())))
/*  376 */         return (V)localEntry.value;
/*  377 */       localEntry = localEntry.next;
/*      */     }
/*  379 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean containsKey(Object paramObject)
/*      */   {
/*  391 */     return getEntry(paramObject) != null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Entry<K, V> getEntry(Object paramObject)
/*      */   {
/*  399 */     Object localObject = maskNull(paramObject);
/*  400 */     int i = System.identityHashCode(localObject);
/*  401 */     Entry[] arrayOfEntry = getTable();
/*  402 */     int j = indexFor(i, arrayOfEntry.length);
/*  403 */     Entry localEntry = arrayOfEntry[j];
/*  404 */     while ((localEntry != null) && ((localEntry.hash != i) || (!eq(localObject, localEntry.get()))))
/*  405 */       localEntry = localEntry.next;
/*  406 */     return localEntry;
/*      */   }
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
/*      */   public V put(K paramK, V paramV)
/*      */   {
/*  422 */     Object localObject1 = maskNull(paramK);
/*  423 */     int i = System.identityHashCode(localObject1);
/*  424 */     Entry[] arrayOfEntry = getTable();
/*  425 */     int j = indexFor(i, arrayOfEntry.length);
/*      */     
/*  427 */     for (Entry localEntry = arrayOfEntry[j]; localEntry != null; localEntry = localEntry.next) {
/*  428 */       if ((i == localEntry.hash) && (eq(localObject1, localEntry.get()))) {
/*  429 */         Object localObject2 = localEntry.value;
/*  430 */         if (paramV != localObject2)
/*  431 */           localEntry.value = paramV;
/*  432 */         return (V)localObject2;
/*      */       }
/*      */     }
/*      */     
/*  436 */     this.modCount += 1;
/*  437 */     localEntry = arrayOfEntry[j];
/*  438 */     arrayOfEntry[j] = new Entry(localObject1, paramV, this.queue, i, localEntry);
/*  439 */     if (++this.size >= this.threshold)
/*  440 */       resize(arrayOfEntry.length * 2);
/*  441 */     return null;
/*      */   }
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
/*      */   void resize(int paramInt)
/*      */   {
/*  459 */     Entry[] arrayOfEntry1 = getTable();
/*  460 */     int i = arrayOfEntry1.length;
/*  461 */     if (i == 1073741824) {
/*  462 */       this.threshold = Integer.MAX_VALUE;
/*  463 */       return;
/*      */     }
/*      */     
/*  466 */     Entry[] arrayOfEntry2 = newTable(paramInt);
/*  467 */     transfer(arrayOfEntry1, arrayOfEntry2);
/*  468 */     this.table = arrayOfEntry2;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  475 */     if (this.size >= this.threshold / 2) {
/*  476 */       this.threshold = ((int)(paramInt * this.loadFactor));
/*      */     } else {
/*  478 */       expungeStaleEntries();
/*  479 */       transfer(arrayOfEntry2, arrayOfEntry1);
/*  480 */       this.table = arrayOfEntry1;
/*      */     }
/*      */   }
/*      */   
/*      */   private void transfer(Entry<K, V>[] paramArrayOfEntry1, Entry<K, V>[] paramArrayOfEntry2)
/*      */   {
/*  486 */     for (int i = 0; i < paramArrayOfEntry1.length; i++) {
/*  487 */       Object localObject1 = paramArrayOfEntry1[i];
/*  488 */       paramArrayOfEntry1[i] = null;
/*  489 */       while (localObject1 != null) {
/*  490 */         Entry localEntry = ((Entry)localObject1).next;
/*  491 */         Object localObject2 = ((Entry)localObject1).get();
/*  492 */         if (localObject2 == null) {
/*  493 */           ((Entry)localObject1).next = null;
/*  494 */           ((Entry)localObject1).value = null;
/*  495 */           this.size -= 1;
/*      */         } else {
/*  497 */           int j = indexFor(((Entry)localObject1).hash, paramArrayOfEntry2.length);
/*  498 */           ((Entry)localObject1).next = paramArrayOfEntry2[j];
/*  499 */           paramArrayOfEntry2[j] = localObject1;
/*      */         }
/*  501 */         localObject1 = localEntry;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void putAll(Map<? extends K, ? extends V> paramMap)
/*      */   {
/*  515 */     int i = paramMap.size();
/*  516 */     if (i == 0) {
/*  517 */       return;
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
/*  528 */     if (i > this.threshold) {
/*  529 */       int j = (int)(i / this.loadFactor + 1.0F);
/*  530 */       if (j > 1073741824)
/*  531 */         j = 1073741824;
/*  532 */       int k = this.table.length;
/*  533 */       while (k < j)
/*  534 */         k <<= 1;
/*  535 */       if (k > this.table.length) {
/*  536 */         resize(k);
/*      */       }
/*      */     }
/*  539 */     for (Map.Entry localEntry : paramMap.entrySet()) {
/*  540 */       put(localEntry.getKey(), localEntry.getValue());
/*      */     }
/*      */   }
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
/*      */   public V remove(Object paramObject)
/*      */   {
/*  564 */     Object localObject1 = maskNull(paramObject);
/*  565 */     int i = System.identityHashCode(localObject1);
/*  566 */     Entry[] arrayOfEntry = getTable();
/*  567 */     int j = indexFor(i, arrayOfEntry.length);
/*  568 */     Object localObject2 = arrayOfEntry[j];
/*  569 */     Object localObject3 = localObject2;
/*      */     
/*  571 */     while (localObject3 != null) {
/*  572 */       Entry localEntry = ((Entry)localObject3).next;
/*  573 */       if ((i == ((Entry)localObject3).hash) && (eq(localObject1, ((Entry)localObject3).get()))) {
/*  574 */         this.modCount += 1;
/*  575 */         this.size -= 1;
/*  576 */         if (localObject2 == localObject3) {
/*  577 */           arrayOfEntry[j] = localEntry;
/*      */         } else
/*  579 */           ((Entry)localObject2).next = localEntry;
/*  580 */         return (V)((Entry)localObject3).value;
/*      */       }
/*  582 */       localObject2 = localObject3;
/*  583 */       localObject3 = localEntry;
/*      */     }
/*      */     
/*  586 */     return null;
/*      */   }
/*      */   
/*      */   boolean removeMapping(Object paramObject)
/*      */   {
/*  591 */     if (!(paramObject instanceof Map.Entry))
/*  592 */       return false;
/*  593 */     Entry[] arrayOfEntry = getTable();
/*  594 */     Map.Entry localEntry = (Map.Entry)paramObject;
/*  595 */     Object localObject1 = maskNull(localEntry.getKey());
/*  596 */     int i = System.identityHashCode(localObject1);
/*  597 */     int j = indexFor(i, arrayOfEntry.length);
/*  598 */     Object localObject2 = arrayOfEntry[j];
/*  599 */     Object localObject3 = localObject2;
/*      */     
/*  601 */     while (localObject3 != null) {
/*  602 */       Entry localEntry1 = ((Entry)localObject3).next;
/*  603 */       if ((i == ((Entry)localObject3).hash) && (((Entry)localObject3).equals(localEntry))) {
/*  604 */         this.modCount += 1;
/*  605 */         this.size -= 1;
/*  606 */         if (localObject2 == localObject3) {
/*  607 */           arrayOfEntry[j] = localEntry1;
/*      */         } else
/*  609 */           ((Entry)localObject2).next = localEntry1;
/*  610 */         return true;
/*      */       }
/*  612 */       localObject2 = localObject3;
/*  613 */       localObject3 = localEntry1;
/*      */     }
/*      */     
/*  616 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clear()
/*      */   {
/*  626 */     while (this.queue.poll() != null) {}
/*      */     
/*      */ 
/*  629 */     this.modCount += 1;
/*  630 */     Arrays.fill(this.table, null);
/*  631 */     this.size = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  636 */     while (this.queue.poll() != null) {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean containsValue(Object paramObject)
/*      */   {
/*  649 */     if (paramObject == null) {
/*  650 */       return containsNullValue();
/*      */     }
/*  652 */     Entry[] arrayOfEntry = getTable();
/*  653 */     for (int i = arrayOfEntry.length; i-- > 0;)
/*  654 */       for (Entry localEntry = arrayOfEntry[i]; localEntry != null; localEntry = localEntry.next)
/*  655 */         if (paramObject.equals(localEntry.value))
/*  656 */           return true;
/*  657 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean containsNullValue()
/*      */   {
/*  664 */     Entry[] arrayOfEntry = getTable();
/*  665 */     for (int i = arrayOfEntry.length; i-- > 0;)
/*  666 */       for (Entry localEntry = arrayOfEntry[i]; localEntry != null; localEntry = localEntry.next)
/*  667 */         if (localEntry.value == null)
/*  668 */           return true;
/*  669 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static class Entry<K, V>
/*      */     extends WeakReference<Object>
/*      */     implements Map.Entry<K, V>
/*      */   {
/*      */     V value;
/*      */     
/*      */     final int hash;
/*      */     
/*      */     Entry<K, V> next;
/*      */     
/*      */ 
/*      */     Entry(Object paramObject, V paramV, ReferenceQueue<Object> paramReferenceQueue, int paramInt, Entry<K, V> paramEntry)
/*      */     {
/*  687 */       super(paramReferenceQueue);
/*  688 */       this.value = paramV;
/*  689 */       this.hash = paramInt;
/*  690 */       this.next = paramEntry;
/*      */     }
/*      */     
/*      */     public K getKey()
/*      */     {
/*  695 */       return (K)WeakIdentityHashMap.unmaskNull(get());
/*      */     }
/*      */     
/*      */     public V getValue() {
/*  699 */       return (V)this.value;
/*      */     }
/*      */     
/*      */     public V setValue(V paramV) {
/*  703 */       Object localObject = this.value;
/*  704 */       this.value = paramV;
/*  705 */       return (V)localObject;
/*      */     }
/*      */     
/*      */     public boolean equals(Object paramObject) {
/*  709 */       if (!(paramObject instanceof Map.Entry))
/*  710 */         return false;
/*  711 */       Map.Entry localEntry = (Map.Entry)paramObject;
/*  712 */       Object localObject1 = getKey();
/*  713 */       Object localObject2 = localEntry.getKey();
/*  714 */       if (localObject1 == localObject2) {
/*  715 */         Object localObject3 = getValue();
/*  716 */         Object localObject4 = localEntry.getValue();
/*  717 */         if ((localObject3 == localObject4) || ((localObject3 != null) && (localObject3.equals(localObject4))))
/*  718 */           return true;
/*      */       }
/*  720 */       return false;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  724 */       Object localObject1 = getKey();
/*  725 */       Object localObject2 = getValue();
/*  726 */       return (localObject1 == null ? 0 : System.identityHashCode(localObject1)) ^ (localObject2 == null ? 0 : localObject2.hashCode());
/*      */     }
/*      */     
/*      */     public String toString()
/*      */     {
/*  731 */       return getKey() + "=" + getValue();
/*      */     }
/*      */   }
/*      */   
/*      */   private abstract class HashIterator<T> implements Iterator<T> {
/*      */     private int index;
/*  737 */     private WeakIdentityHashMap.Entry<K, V> entry = null;
/*  738 */     private WeakIdentityHashMap.Entry<K, V> lastReturned = null;
/*  739 */     private int expectedModCount = WeakIdentityHashMap.this.modCount;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  745 */     private Object nextKey = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  751 */     private Object currentKey = null;
/*      */     
/*      */     HashIterator() {
/*  754 */       this.index = (WeakIdentityHashMap.this.isEmpty() ? 0 : WeakIdentityHashMap.this.table.length);
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  758 */       WeakIdentityHashMap.Entry[] arrayOfEntry = WeakIdentityHashMap.this.table;
/*      */       
/*  760 */       while (this.nextKey == null) {
/*  761 */         WeakIdentityHashMap.Entry localEntry = this.entry;
/*  762 */         int i = this.index;
/*  763 */         while ((localEntry == null) && (i > 0))
/*  764 */           localEntry = arrayOfEntry[(--i)];
/*  765 */         this.entry = localEntry;
/*  766 */         this.index = i;
/*  767 */         if (localEntry == null) {
/*  768 */           this.currentKey = null;
/*  769 */           return false;
/*      */         }
/*  771 */         this.nextKey = localEntry.get();
/*  772 */         if (this.nextKey == null)
/*  773 */           this.entry = this.entry.next;
/*      */       }
/*  775 */       return true;
/*      */     }
/*      */     
/*      */     protected WeakIdentityHashMap.Entry<K, V> nextEntry()
/*      */     {
/*  780 */       if (WeakIdentityHashMap.this.modCount != this.expectedModCount)
/*  781 */         throw new ConcurrentModificationException();
/*  782 */       if ((this.nextKey == null) && (!hasNext())) {
/*  783 */         throw new NoSuchElementException();
/*      */       }
/*  785 */       this.lastReturned = this.entry;
/*  786 */       this.entry = this.entry.next;
/*  787 */       this.currentKey = this.nextKey;
/*  788 */       this.nextKey = null;
/*  789 */       return this.lastReturned;
/*      */     }
/*      */     
/*      */     public void remove() {
/*  793 */       if (this.lastReturned == null)
/*  794 */         throw new IllegalStateException();
/*  795 */       if (WeakIdentityHashMap.this.modCount != this.expectedModCount) {
/*  796 */         throw new ConcurrentModificationException();
/*      */       }
/*  798 */       WeakIdentityHashMap.this.remove(this.currentKey);
/*  799 */       this.expectedModCount = WeakIdentityHashMap.this.modCount;
/*  800 */       this.lastReturned = null;
/*  801 */       this.currentKey = null;
/*      */     }
/*      */   }
/*      */   
/*      */   private class ValueIterator extends WeakIdentityHashMap<K, V>.HashIterator<V> {
/*  806 */     private ValueIterator() { super(); }
/*      */     
/*  808 */     public V next() { return (V)nextEntry().value; }
/*      */   }
/*      */   
/*      */   private class KeyIterator extends WeakIdentityHashMap<K, V>.HashIterator<K> {
/*  812 */     private KeyIterator() { super(); }
/*      */     
/*  814 */     public K next() { return (K)nextEntry().getKey(); }
/*      */   }
/*      */   
/*      */   private class EntryIterator extends WeakIdentityHashMap<K, V>.HashIterator<Map.Entry<K, V>> {
/*  818 */     private EntryIterator() { super(); }
/*      */     
/*  820 */     public Map.Entry<K, V> next() { return nextEntry(); }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  826 */   private transient Set<Map.Entry<K, V>> entrySet = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  833 */   volatile transient Set<K> keySet = null;
/*  834 */   volatile transient Collection<V> values = null;
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
/*      */   public Set<K> keySet()
/*      */   {
/*  850 */     Set localSet = this.keySet;
/*  851 */     return localSet != null ? localSet : (this.keySet = new KeySet(null));
/*      */   }
/*      */   
/*      */   private class KeySet extends AbstractSet<K> { private KeySet() {}
/*      */     
/*  856 */     public Iterator<K> iterator() { return new WeakIdentityHashMap.KeyIterator(WeakIdentityHashMap.this, null); }
/*      */     
/*      */     public int size()
/*      */     {
/*  860 */       return WeakIdentityHashMap.this.size();
/*      */     }
/*      */     
/*      */     public boolean contains(Object paramObject) {
/*  864 */       return WeakIdentityHashMap.this.containsKey(paramObject);
/*      */     }
/*      */     
/*      */     public boolean remove(Object paramObject) {
/*  868 */       if (WeakIdentityHashMap.this.containsKey(paramObject)) {
/*  869 */         WeakIdentityHashMap.this.remove(paramObject);
/*  870 */         return true;
/*      */       }
/*      */       
/*  873 */       return false;
/*      */     }
/*      */     
/*      */     public void clear() {
/*  877 */       WeakIdentityHashMap.this.clear();
/*      */     }
/*      */   }
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
/*      */   public Collection<V> values()
/*      */   {
/*  895 */     Collection localCollection = this.values;
/*  896 */     return localCollection != null ? localCollection : (this.values = new Values(null));
/*      */   }
/*      */   
/*      */   private class Values extends AbstractCollection<V> { private Values() {}
/*      */     
/*  901 */     public Iterator<V> iterator() { return new WeakIdentityHashMap.ValueIterator(WeakIdentityHashMap.this, null); }
/*      */     
/*      */     public int size()
/*      */     {
/*  905 */       return WeakIdentityHashMap.this.size();
/*      */     }
/*      */     
/*      */     public boolean contains(Object paramObject) {
/*  909 */       return WeakIdentityHashMap.this.containsValue(paramObject);
/*      */     }
/*      */     
/*      */     public void clear() {
/*  913 */       WeakIdentityHashMap.this.clear();
/*      */     }
/*      */   }
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
/*      */   public Set<Map.Entry<K, V>> entrySet()
/*      */   {
/*  932 */     Set localSet = this.entrySet;
/*  933 */     return localSet != null ? localSet : (this.entrySet = new EntrySet(null));
/*      */   }
/*      */   
/*      */   private class EntrySet extends AbstractSet<Map.Entry<K, V>> { private EntrySet() {}
/*      */     
/*  938 */     public Iterator<Map.Entry<K, V>> iterator() { return new WeakIdentityHashMap.EntryIterator(WeakIdentityHashMap.this, null); }
/*      */     
/*      */     public boolean contains(Object paramObject)
/*      */     {
/*  942 */       if (!(paramObject instanceof Map.Entry))
/*  943 */         return false;
/*  944 */       Map.Entry localEntry = (Map.Entry)paramObject;
/*  945 */       WeakIdentityHashMap.Entry localEntry1 = WeakIdentityHashMap.this.getEntry(localEntry.getKey());
/*  946 */       return (localEntry1 != null) && (localEntry1.equals(localEntry));
/*      */     }
/*      */     
/*      */     public boolean remove(Object paramObject) {
/*  950 */       return WeakIdentityHashMap.this.removeMapping(paramObject);
/*      */     }
/*      */     
/*      */     public int size() {
/*  954 */       return WeakIdentityHashMap.this.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  958 */       WeakIdentityHashMap.this.clear();
/*      */     }
/*      */     
/*      */     private List<Map.Entry<K, V>> deepCopy() {
/*  962 */       ArrayList localArrayList = new ArrayList(size());
/*      */       
/*  964 */       for (Map.Entry localEntry : this)
/*  965 */         localArrayList.add(new AbstractMap.SimpleEntry(localEntry));
/*  966 */       return localArrayList;
/*      */     }
/*      */     
/*      */     public Object[] toArray() {
/*  970 */       return deepCopy().toArray();
/*      */     }
/*      */     
/*      */     public <T> T[] toArray(T[] paramArrayOfT) {
/*  974 */       return deepCopy().toArray(paramArrayOfT);
/*      */     }
/*      */   }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object paramObject)
/*      */   {
/* 1002 */     if (paramObject == this) {
/* 1003 */       return true;
/*      */     }
/* 1005 */     if (!(paramObject instanceof Map))
/* 1006 */       return false;
/* 1007 */     Map localMap = (Map)paramObject;
/* 1008 */     if (localMap.size() != size()) {
/* 1009 */       return false;
/*      */     }
/*      */     try {
/* 1012 */       Iterator localIterator = entrySet().iterator();
/* 1013 */       while (localIterator.hasNext()) {
/* 1014 */         Map.Entry localEntry = (Map.Entry)localIterator.next();
/* 1015 */         Object localObject1 = localEntry.getKey();
/* 1016 */         Object localObject2 = localEntry.getValue();
/* 1017 */         if (localObject2 == null) {
/* 1018 */           if ((localMap.get(localObject1) != null) || (!localMap.containsKey(localObject1))) {
/* 1019 */             return false;
/*      */           }
/* 1021 */         } else if (!localObject2.equals(localMap.get(localObject1))) {
/* 1022 */           return false;
/*      */         }
/*      */       }
/*      */     } catch (ClassCastException localClassCastException) {
/* 1026 */       return false;
/*      */     } catch (NullPointerException localNullPointerException) {
/* 1028 */       return false;
/*      */     }
/*      */     
/* 1031 */     return true;
/*      */   }
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
/*      */   public int hashCode()
/*      */   {
/* 1052 */     int i = 0;
/* 1053 */     Iterator localIterator = entrySet().iterator();
/* 1054 */     while (localIterator.hasNext())
/* 1055 */       i += ((Map.Entry)localIterator.next()).hashCode();
/* 1056 */     return i;
/*      */   }
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
/*      */   public String toString()
/*      */   {
/* 1072 */     Iterator localIterator = entrySet().iterator();
/* 1073 */     if (!localIterator.hasNext()) {
/* 1074 */       return "{}";
/*      */     }
/* 1076 */     StringBuilder localStringBuilder = new StringBuilder();
/* 1077 */     localStringBuilder.append('{');
/*      */     for (;;) {
/* 1079 */       Map.Entry localEntry = (Map.Entry)localIterator.next();
/* 1080 */       Object localObject1 = localEntry.getKey();
/* 1081 */       Object localObject2 = localEntry.getValue();
/* 1082 */       localStringBuilder.append(localObject1 == this ? "(this Map)" : localObject1);
/* 1083 */       localStringBuilder.append('=');
/* 1084 */       localStringBuilder.append(localObject2 == this ? "(this Map)" : localObject2);
/* 1085 */       if (!localIterator.hasNext())
/* 1086 */         return '}';
/* 1087 */       localStringBuilder.append(", ");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Object clone()
/*      */     throws CloneNotSupportedException
/*      */   {
/* 1098 */     WeakIdentityHashMap localWeakIdentityHashMap = (WeakIdentityHashMap)super.clone();
/* 1099 */     localWeakIdentityHashMap.keySet = null;
/* 1100 */     localWeakIdentityHashMap.values = null;
/* 1101 */     return localWeakIdentityHashMap;
/*      */   }
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/WeakIdentityHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */