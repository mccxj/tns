package oracle.sql;
import java.math.BigDecimal;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
public class BINARY_DOUBLE
  extends Datum
{
  static final long serialVersionUID = 6718338151847341754L;
  
  public BINARY_DOUBLE() {}
  
  public BINARY_DOUBLE(byte[] paramArrayOfByte)
  {
/*  50 */     super(paramArrayOfByte);
  }
  
  public BINARY_DOUBLE(double paramDouble)
  {
/*  64 */     super(doubleToCanonicalFormatBytes(paramDouble));
  }
  
  public BINARY_DOUBLE(Double paramDouble)
  {
/*  78 */     super(doubleToCanonicalFormatBytes(paramDouble.doubleValue()));
  }
  
  public BINARY_DOUBLE(String paramString)
    throws SQLException
  {
/*  93 */     this(stringToDouble(paramString));
  }
  
  public BINARY_DOUBLE(Boolean paramBoolean)
  {
/* 107 */     this(paramBoolean.booleanValue() ? 1 : 0);
  }
  
  public Object toJdbc()
    throws SQLException
  {
/* 124 */     return new Double(canonicalFormatBytesToDouble(getBytes()));
  }
  
  public boolean isConvertibleTo(Class paramClass)
  {
/* 142 */     String str = paramClass.getName();
    
/* 144 */     return (str.compareTo("java.lang.String") == 0) || (str.compareTo("java.lang.Double") == 0);
  }
  
  public String stringValue()
  {
/* 157 */     String str = Double.toString(canonicalFormatBytesToDouble(getBytes()));
/* 158 */     return str;
  }
  
  public double doubleValue()
    throws SQLException
  {
/* 171 */     return canonicalFormatBytesToDouble(getBytes());
  }
  
  public BigDecimal bigDecimalValue()
    throws SQLException
  {
/* 184 */     return new BigDecimal(canonicalFormatBytesToDouble(getBytes()));
  }
  
  public Object makeJdbcArray(int paramInt)
  {
/* 195 */     return new Double[paramInt];
  }
  
  private static double stringToDouble(String paramString)
    throws SQLException
  {
    try
    {
/* 206 */       return Double.valueOf(paramString).doubleValue();
    }
    catch (NumberFormatException localNumberFormatException)
    {
/* 211 */       SQLException localSQLException = DatabaseError.createSqlException(null, 59);
/* 212 */       localSQLException.fillInStackTrace();
/* 213 */       throw localSQLException;
    }
  }
  
  static byte[] doubleToCanonicalFormatBytes(double paramDouble)
  {
/* 221 */     double d = paramDouble;
    
/* 223 */     if (d == 0.0D) {
/* 224 */       d = 0.0D;
/* 225 */     } else if (d != d) {
/* 226 */       d = NaN.0D;
    }
/* 228 */     long l = Double.doubleToLongBits(d);
/* 229 */     byte[] arrayOfByte = new byte[8];
/* 230 */     int i = (int)l;
/* 231 */     int j = (int)(l >> 32);
    
/* 233 */     int k = i;
    
/* 236 */     i >>= 8;
    
/* 238 */     int m = i;
    
/* 240 */     i >>= 8;
    
/* 242 */     int n = i;
    
/* 244 */     i >>= 8;
    
/* 246 */     int i1 = i;
    
/* 248 */     int i2 = j;
    
/* 250 */     j >>= 8;
    
/* 252 */     int i3 = j;
    
/* 254 */     j >>= 8;
    
/* 256 */     int i4 = j;
    
/* 258 */     j >>= 8;
    
/* 260 */     int i5 = j;
    
/* 262 */     if ((i5 & 0x80) == 0)
    {
/* 264 */       i5 |= 0x80;
    }
    else
    {
/* 268 */       i5 ^= 0xFFFFFFFF;
/* 269 */       i4 ^= 0xFFFFFFFF;
/* 270 */       i3 ^= 0xFFFFFFFF;
/* 271 */       i2 ^= 0xFFFFFFFF;
/* 272 */       i1 ^= 0xFFFFFFFF;
/* 273 */       n ^= 0xFFFFFFFF;
/* 274 */       m ^= 0xFFFFFFFF;
/* 275 */       k ^= 0xFFFFFFFF;
    }
    
/* 278 */     arrayOfByte[7] = ((byte)k);
/* 279 */     arrayOfByte[6] = ((byte)m);
/* 280 */     arrayOfByte[5] = ((byte)n);
/* 281 */     arrayOfByte[4] = ((byte)i1);
/* 282 */     arrayOfByte[3] = ((byte)i2);
/* 283 */     arrayOfByte[2] = ((byte)i3);
/* 284 */     arrayOfByte[1] = ((byte)i4);
/* 285 */     arrayOfByte[0] = ((byte)i5);
    
/* 287 */     return arrayOfByte;
  }
  
  static double canonicalFormatBytesToDouble(byte[] paramArrayOfByte)
  {
/* 294 */     int i = paramArrayOfByte[0];
/* 295 */     int j = paramArrayOfByte[1];
/* 296 */     int k = paramArrayOfByte[2];
/* 297 */     int m = paramArrayOfByte[3];
/* 298 */     int n = paramArrayOfByte[4];
/* 299 */     int i1 = paramArrayOfByte[5];
/* 300 */     int i2 = paramArrayOfByte[6];
/* 301 */     int i3 = paramArrayOfByte[7];
    
/* 303 */     if ((i & 0x80) != 0)
    {
/* 305 */       i &= 0x7F;
/* 306 */       j &= 0xFF;
/* 307 */       k &= 0xFF;
/* 308 */       m &= 0xFF;
/* 309 */       n &= 0xFF;
/* 310 */       i1 &= 0xFF;
/* 311 */       i2 &= 0xFF;
/* 312 */       i3 &= 0xFF;
    }
    else
    {
/* 316 */       i = (i ^ 0xFFFFFFFF) & 0xFF;
/* 317 */       j = (j ^ 0xFFFFFFFF) & 0xFF;
/* 318 */       k = (k ^ 0xFFFFFFFF) & 0xFF;
/* 319 */       m = (m ^ 0xFFFFFFFF) & 0xFF;
/* 320 */       n = (n ^ 0xFFFFFFFF) & 0xFF;
/* 321 */       i1 = (i1 ^ 0xFFFFFFFF) & 0xFF;
/* 322 */       i2 = (i2 ^ 0xFFFFFFFF) & 0xFF;
/* 323 */       i3 = (i3 ^ 0xFFFFFFFF) & 0xFF;
    }
    
/* 326 */     int i4 = i << 24 | j << 16 | k << 8 | m;
/* 327 */     int i5 = n << 24 | i1 << 16 | i2 << 8 | i3;
/* 328 */     long l = i4 << 32 | i5 & 0xFFFFFFFF;
    
/* 330 */     return Double.longBitsToDouble(l);
  }
  
/* 335 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/BINARY_DOUBLE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */