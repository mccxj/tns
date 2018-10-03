package oracle.jdbc.proxy;
import java.lang.reflect.Array;
class HashUtil
{
  static final int SEED = 23;
  private static final int ODD_PRIME_NUMBER = 37;
  
  static int hash(int paramInt, boolean paramBoolean)
  {
/*  39 */     return firstTerm(paramInt) + (paramBoolean ? 1 : 0);
  }
  
  static int hash(int paramInt, char paramChar)
  {
/*  50 */     return firstTerm(paramInt) + paramChar;
  }
  
  static int hash(int paramInt1, int paramInt2)
  {
/*  61 */     return firstTerm(paramInt1) + paramInt2;
  }
  
  static int hash(int paramInt, long paramLong)
  {
/*  72 */     return firstTerm(paramInt) + (int)(paramLong ^ paramLong >>> 32);
  }
  
  static int hash(int paramInt, float paramFloat)
  {
/*  83 */     return hash(paramInt, Float.floatToIntBits(paramFloat));
  }
  
  static int hash(int paramInt, double paramDouble)
  {
/*  94 */     return hash(paramInt, Double.doubleToLongBits(paramDouble));
  }
  
  static int hash(int paramInt, Object paramObject)
  {
/* 105 */     int i = paramInt;
/* 106 */     if (paramObject == null) {
/* 107 */       i = hash(i, 0);
/* 108 */     } else if (!paramObject.getClass().isArray()) {
/* 109 */       i = hash(i, paramObject.hashCode());
    }
    else {
/* 112 */       int j = Array.getLength(paramObject);
/* 113 */       for (int k = 0; k < j; k++)
      {
/* 115 */         Object localObject = Array.get(paramObject, k);
/* 116 */         i = hash(i, localObject);
      }
    }
/* 119 */     return i;
  }
  
  private static int firstTerm(int paramInt)
  {
/* 126 */     return 37 * paramInt;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/HashUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */