package oracle.jdbc.driver;
import java.util.Vector;
class OracleResultSetCacheImpl
  implements OracleResultSetCache
{
/*  24 */   private static int DEFAULT_WIDTH = 5;
/*  25 */   private static int DEFAULT_SIZE = 5;
  
/*  29 */   Vector cachedRows = null;
  
  int nbOfColumnsInRow;
  
  OracleResultSetCacheImpl()
  {
/*  36 */     this(DEFAULT_WIDTH);
  }
  
  OracleResultSetCacheImpl(int paramInt)
  {
/*  45 */     if (paramInt > 0) {
/*  46 */       this.nbOfColumnsInRow = paramInt;
    }
/*  48 */     this.cachedRows = new Vector(DEFAULT_SIZE);
  }
  
  public void put(int paramInt1, int paramInt2, Object paramObject)
  {
/*  56 */     Vector localVector = null;
    
/*  60 */     while (this.cachedRows.size() < paramInt1)
    {
/*  62 */       localVector = new Vector(this.nbOfColumnsInRow);
/*  63 */       this.cachedRows.addElement(localVector);
    }
    
/*  66 */     localVector = (Vector)this.cachedRows.elementAt(paramInt1 - 1);
    
/*  68 */     while (localVector.size() < paramInt2) {
/*  69 */       localVector.addElement(null);
    }
/*  71 */     localVector.setElementAt(paramObject, paramInt2 - 1);
  }
  
  public Object get(int paramInt1, int paramInt2)
  {
/*  79 */     Vector localVector = (Vector)this.cachedRows.elementAt(paramInt1 - 1);
    
/*  81 */     return localVector.elementAt(paramInt2 - 1);
  }
  
  public void remove(int paramInt)
  {
/*  89 */     this.cachedRows.removeElementAt(paramInt - 1);
  }
  
  public void remove(int paramInt1, int paramInt2)
  {
/*  96 */     this.cachedRows.removeElementAt(paramInt1 - 1);
  }
  
  public void clear() {}
  
  public void close() {}
  
  public int getLength()
  {
/* 109 */     return 0;
  }
  
/* 114 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleResultSetCacheImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */