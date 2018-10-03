package oracle.sql;
import java.math.BigDecimal;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
public class BINARY_FLOAT
  extends Datum
{
  static final long serialVersionUID = -4231112037190700631L;
  
  public BINARY_FLOAT() {}
  
  public BINARY_FLOAT(byte[] paramArrayOfByte)
  {
/*  49 */     super(paramArrayOfByte);
  }
  
  public BINARY_FLOAT(float paramFloat)
  {
/*  63 */     super(floatToCanonicalFormatBytes(paramFloat));
  }
  
  public BINARY_FLOAT(Float paramFloat)
  {
/*  77 */     super(floatToCanonicalFormatBytes(paramFloat.floatValue()));
  }
  
  public BINARY_FLOAT(String paramString)
    throws SQLException
  {
/*  92 */     this(stringToFloat(paramString));
  }
  
  public BINARY_FLOAT(Boolean paramBoolean)
  {
/* 106 */     this(paramBoolean.booleanValue() ? 1 : 0);
  }
  
  public Object toJdbc()
    throws SQLException
  {
/* 123 */     return new Float(canonicalFormatBytesToFloat(getBytes()));
  }
  
  public boolean isConvertibleTo(Class paramClass)
  {
/* 141 */     String str = paramClass.getName();
    
/* 143 */     return (str.compareTo("java.lang.String") == 0) || (str.compareTo("java.lang.Float") == 0);
  }
  
  public String stringValue()
  {
/* 157 */     String str = Float.toString(canonicalFormatBytesToFloat(getBytes()));
/* 158 */     return str;
  }
  
  public float floatValue()
    throws SQLException
  {
/* 172 */     return canonicalFormatBytesToFloat(getBytes());
  }
  
  public double doubleValue()
    throws SQLException
  {
/* 185 */     return floatValue();
  }
  
  public BigDecimal bigDecimalValue()
    throws SQLException
  {
/* 198 */     return new BigDecimal(floatValue());
  }
  
  private static float stringToFloat(String paramString)
    throws SQLException
  {
    try
    {
/* 209 */       return Float.valueOf(paramString).floatValue();
    }
    catch (NumberFormatException localNumberFormatException)
    {
/* 214 */       SQLException localSQLException = DatabaseError.createSqlException(null, 59);
/* 215 */       localSQLException.fillInStackTrace();
/* 216 */       throw localSQLException;
    }
  }
  
  public Object makeJdbcArray(int paramInt)
  {
/* 228 */     return new Float[paramInt];
  }
  
  static byte[] floatToCanonicalFormatBytes(float paramFloat)
  {
/* 235 */     float f = paramFloat;
    
/* 237 */     if (f == 0.0F) {
/* 238 */       f = 0.0F;
/* 239 */     } else if (f != f) {
/* 240 */       f = NaN.0F;
    }
/* 242 */     int i = Float.floatToIntBits(f);
/* 243 */     byte[] arrayOfByte = new byte[4];
    
/* 245 */     int j = i;
    
/* 247 */     i >>= 8;
    
/* 249 */     int k = i;
    
/* 251 */     i >>= 8;
    
/* 253 */     int m = i;
    
/* 255 */     i >>= 8;
    
/* 257 */     int n = i;
    
/* 259 */     if ((n & 0x80) == 0) {
/* 260 */       n |= 0x80;
    }
    else {
/* 263 */       n ^= 0xFFFFFFFF;
/* 264 */       m ^= 0xFFFFFFFF;
/* 265 */       k ^= 0xFFFFFFFF;
/* 266 */       j ^= 0xFFFFFFFF;
    }
    
/* 269 */     arrayOfByte[3] = ((byte)j);
/* 270 */     arrayOfByte[2] = ((byte)k);
/* 271 */     arrayOfByte[1] = ((byte)m);
/* 272 */     arrayOfByte[0] = ((byte)n);
    
/* 274 */     return arrayOfByte;
  }
  
  static float canonicalFormatBytesToFloat(byte[] paramArrayOfByte)
  {
/* 282 */     int i = paramArrayOfByte[0];
/* 283 */     int j = paramArrayOfByte[1];
/* 284 */     int k = paramArrayOfByte[2];
/* 285 */     int m = paramArrayOfByte[3];
    
/* 287 */     if ((i & 0x80) != 0)
    {
/* 289 */       i &= 0x7F;
/* 290 */       j &= 0xFF;
/* 291 */       k &= 0xFF;
/* 292 */       m &= 0xFF;
    }
    else
    {
/* 296 */       i = (i ^ 0xFFFFFFFF) & 0xFF;
/* 297 */       j = (j ^ 0xFFFFFFFF) & 0xFF;
/* 298 */       k = (k ^ 0xFFFFFFFF) & 0xFF;
/* 299 */       m = (m ^ 0xFFFFFFFF) & 0xFF;
    }
    
/* 302 */     int n = i << 24 | j << 16 | k << 8 | m;
    
/* 304 */     return Float.intBitsToFloat(n);
  }
  
/* 309 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/BINARY_FLOAT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */