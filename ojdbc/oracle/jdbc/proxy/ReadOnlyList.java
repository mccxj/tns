/*    */ package oracle.jdbc.proxy;
/*    */ import java.util.AbstractList;
/*    */ import java.util.Collection;
/*    */ abstract class ReadOnlyList<E>
/*    */   extends AbstractList<E>
/*    */ {
/* 25 */   private final RuntimeException e = new RuntimeException("read only");
/*    */   
/* 27 */   public final boolean add(E paramE) { throw this.e; }
/* 28 */   public final void add(int paramInt, E paramE) { throw this.e; }
/* 29 */   public final boolean addAll(int paramInt, Collection<? extends E> paramCollection) { throw this.e; }
/* 30 */   public final void clear() { throw this.e; }
/* 31 */   public final E remove(int paramInt) { throw this.e; }
/* 32 */   public final void removeRange(int paramInt1, int paramInt2) { throw this.e; }
/* 33 */   public final E set(int paramInt, E paramE) { throw this.e; }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/ReadOnlyList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */