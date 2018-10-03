package oracle.jdbc.driver;
import java.lang.ref.SoftReference;
import java.lang.reflect.Array;
import oracle.jdbc.internal.OracleConnection.BufferCacheStatistics;
class BufferCache<T>
{
/* 144 */   private static final double ln2 = Math.log(2.0D);
  
  private static final int BUFFERS_PER_BUCKET = 8;
  
  private static final int MIN_INDEX = 12;
  
  private final InternalStatistics stats;
  
  private final int[] bufferSize;
  
  private final SoftReference<T>[][] buckets;
  
  private final int[] top;
  
  BufferCache(int paramInt)
  {
    int i;
    
/* 170 */     if (paramInt < 31) {
/* 171 */       i = paramInt;
    }
    else
    {
/* 176 */       i = (int)Math.ceil(Math.log(paramInt) / ln2);
    }
    
/* 179 */     int j = Math.max(0, i - 12 + 1);
    
/* 181 */     this.buckets = ((SoftReference[][])new SoftReference[j][8]);
/* 182 */     this.top = new int[j];
    
/* 184 */     this.bufferSize = new int[j];
/* 185 */     int k = 4096;
/* 186 */     for (int m = 0; m < this.bufferSize.length; m++) {
/* 187 */       this.bufferSize[m] = k;
/* 188 */       k <<= 1;
    }
/* 190 */     this.stats = new InternalStatistics(this.bufferSize);
  }
  
  T get(Class<?> paramClass, int paramInt)
  {
/* 208 */     int i = bufferIndex(paramInt);
    
/* 210 */     if (i >= this.buckets.length) {
/* 211 */       this.stats.requestTooBig();
/* 212 */       return (T)Array.newInstance(paramClass, paramInt);
    }
    
/* 215 */     while (this.top[i] > 0) {
/* 216 */       SoftReference localSoftReference = this.buckets[i][(this.top[i] -= 1)];
/* 217 */       this.buckets[i][this.top[i]] = null;
/* 218 */       Object localObject = localSoftReference.get();
/* 219 */       if (localObject != null) {
/* 220 */         this.stats.cacheHit(i);
/* 221 */         return (T)localObject;
      }
    }
    
/* 225 */     this.stats.cacheMiss(i);
/* 226 */     return (T)Array.newInstance(paramClass, this.bufferSize[i]);
  }
  
  void put(T paramT)
  {
/* 240 */     int i = Array.getLength(paramT);
    
/* 242 */     int j = bufferIndex(i);
    
/* 245 */     if ((j >= this.buckets.length) || (i != this.bufferSize[j])) {
/* 246 */       this.stats.cacheTooBig();
/* 247 */       return;
    }
    
/* 250 */     if (this.top[j] < 8) {
/* 251 */       this.stats.bufferCached(j); int 
/* 252 */         tmp68_67 = j; int[] tmp68_64 = this.top; int tmp70_69 = tmp68_64[tmp68_67];tmp68_64[tmp68_67] = (tmp70_69 + 1);this.buckets[j][tmp70_69] = new SoftReference(paramT);
    }
    else
    {
/* 258 */       for (int k = this.top[j]; k > 0;) {
/* 259 */         if (this.buckets[j][(--k)].get() == null)
        {
/* 261 */           this.stats.refCleared(j);
/* 262 */           this.buckets[j][k] = new SoftReference(paramT);
/* 263 */           return;
        }
      }
/* 266 */       this.stats.bucketFull(j);
    }
  }
  
  OracleConnection.BufferCacheStatistics getStatistics()
  {
/* 274 */     return this.stats;
  }
  
  private int bufferIndex(int paramInt)
  {
/* 282 */     for (int i = 0; i < this.bufferSize.length; i++) {
/* 283 */       if (paramInt <= this.bufferSize[i]) return i;
    }
/* 285 */     return Integer.MAX_VALUE;
  }
  
  private static final class InternalStatistics implements OracleConnection.BufferCacheStatistics
  {
/* 290 */     private static int CACHE_COUNT = 0;
    
/* 292 */     private final int cacheId = ++CACHE_COUNT;
    
    private final int[] sizes;
    
    private final int[] nCacheHit;
    private final int[] nCacheMiss;
    private int nRequestTooBig;
    private final int[] nBufferCached;
    private final int[] nBucketFull;
    private final int[] nRefCleared;
    private int nCacheTooBig;
    
    InternalStatistics(int[] paramArrayOfInt)
    {
/* 306 */       this.sizes = paramArrayOfInt;
/* 307 */       int i = paramArrayOfInt.length;
/* 308 */       this.nCacheHit = new int[i];
/* 309 */       this.nCacheMiss = new int[i];
/* 310 */       this.nRequestTooBig = 0;
/* 311 */       this.nBufferCached = new int[i];
/* 312 */       this.nBucketFull = new int[i];
/* 313 */       this.nRefCleared = new int[i];
/* 314 */       this.nCacheTooBig = 0;
    }
    
/* 317 */     void cacheHit(int paramInt) { this.nCacheHit[paramInt] += 1; }
/* 318 */     void cacheMiss(int paramInt) { this.nCacheMiss[paramInt] += 1; }
/* 319 */     void requestTooBig() { this.nRequestTooBig += 1; }
/* 320 */     void bufferCached(int paramInt) { this.nBufferCached[paramInt] += 1; }
/* 321 */     void bucketFull(int paramInt) { this.nBucketFull[paramInt] += 1; }
/* 322 */     void refCleared(int paramInt) { this.nRefCleared[paramInt] += 1; }
/* 323 */     void cacheTooBig() { this.nCacheTooBig += 1; }
    
/* 326 */     public int getId() { return this.cacheId; }
    
/* 328 */     public int[] getBufferSizes() { int[] arrayOfInt = new int[this.sizes.length];
/* 329 */       System.arraycopy(this.sizes, 0, arrayOfInt, 0, this.sizes.length);
/* 330 */       return arrayOfInt; }
    
/* 332 */     public int getCacheHits(int paramInt) { return this.nCacheHit[paramInt]; }
/* 333 */     public int getCacheMisses(int paramInt) { return this.nCacheMiss[paramInt]; }
/* 334 */     public int getRequestsTooBig() { return this.nRequestTooBig; }
/* 335 */     public int getBuffersCached(int paramInt) { return this.nBufferCached[paramInt]; }
/* 336 */     public int getBucketsFull(int paramInt) { return this.nBucketFull[paramInt]; }
/* 337 */     public int getReferencesCleared(int paramInt) { return this.nRefCleared[paramInt]; }
/* 338 */     public int getTooBigToCache() { return this.nCacheTooBig; }
    
    public String toString() {
/* 341 */       int i = 0;
/* 342 */       int j = 0;
/* 343 */       int k = 0;
/* 344 */       int m = 0;
/* 345 */       int n = 0;
/* 346 */       for (int i1 = 0; i1 < this.sizes.length; i1++) {
/* 347 */         i += this.nCacheHit[i1];
/* 348 */         j += this.nCacheMiss[i1];
/* 349 */         k += this.nBufferCached[i1];
/* 350 */         m += this.nBucketFull[i1];
/* 351 */         n += this.nRefCleared[i1];
      }
/* 353 */       String str = "oracle.jdbc.driver.BufferCache<" + this.cacheId + ">\n" + "\tTotal Hits   :\t" + i + "\n" + "\tTotal Misses :\t" + (j + this.nRequestTooBig) + "\n" + "\tTotal Cached :\t" + k + "\n" + "\tTotal Dropped:\t" + (m + this.nCacheTooBig) + "\n" + "\tTotal Cleared:\t" + n + "\n";
      
/* 359 */       return str;
    }
  }
  
/* 364 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/BufferCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */