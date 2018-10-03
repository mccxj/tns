package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1SQLInput$$$Proxy
  extends NonTxnReplayableBase
  implements SQLInput, _Proxy_
{
  private SQLInput delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32336;
  private static Method methodObject32317;
  private static Method methodObject32316;
  private static Method methodObject32315;
  private static Method methodObject32322;
  private static Method methodObject32337;
  private static Method methodObject32324;
  private static Method methodObject32326;
  private static Method methodObject32311;
  private static Method methodObject32330;
  private static Method methodObject32319;
  private static Method methodObject32313;
  private static Method methodObject32321;
  private static Method methodObject32333;
  private static Method methodObject32325;
  private static Method methodObject32318;
  private static Method methodObject32331;
  private static Method methodObject32312;
  private static Method methodObject32323;
  private static Method methodObject32320;
  private static Method methodObject32314;
  private static Method methodObject32328;
  private static Method methodObject32327;
  private static Method methodObject32332;
  private static Method methodObject32329;
  private static Method methodObject32334;
  private static Method methodObject32335;
  
  public URL readURL()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32336, this, zeroLengthObjectArray);
      return (URL)postForAll(methodObject32336, (Object)this.delegate.readURL());
    }
    catch (SQLException e)
    {
      return (URL)postForAll(methodObject32336, onErrorForAll(methodObject32336, e));
    }
  }
  
  public float readFloat()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32317, this, zeroLengthObjectArray);
      return ((Float)postForAll(methodObject32317, Float.valueOf(this.delegate.readFloat()))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject32317, onErrorForAll(methodObject32317, e))).floatValue();
    }
  }
  
  public short readShort()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32316, this, zeroLengthObjectArray);
      return ((Short)postForAll(methodObject32316, Short.valueOf(this.delegate.readShort()))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject32316, onErrorForAll(methodObject32316, e))).shortValue();
    }
  }
  
  public long readLong()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32315, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject32315, Long.valueOf(this.delegate.readLong()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject32315, onErrorForAll(methodObject32315, e))).longValue();
    }
  }
  
  public Blob readBlob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32322, this, zeroLengthObjectArray);
      return (Blob)postForAll(methodObject32322, this.proxyFactory.proxyForCache((Object)this.delegate.readBlob(), this, this.proxyCache, methodObject32322));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject32322, onErrorForAll(methodObject32322, e));
    }
  }
  
  public boolean wasNull()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32337, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32337, Boolean.valueOf(this.delegate.wasNull()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32337, onErrorForAll(methodObject32337, e))).booleanValue();
    }
  }
  
  public Reader readCharacterStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32324, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject32324, (Object)this.delegate.readCharacterStream());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject32324, onErrorForAll(methodObject32324, e));
    }
  }
  
  public Date readDate()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32326, this, zeroLengthObjectArray);
      return (Date)postForAll(methodObject32326, (Object)this.delegate.readDate());
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject32326, onErrorForAll(methodObject32326, e));
    }
  }
  
  public int readInt()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32311, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32311, Integer.valueOf(this.delegate.readInt()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32311, onErrorForAll(methodObject32311, e))).intValue();
    }
  }
  
  public Ref readRef()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32330, this, zeroLengthObjectArray);
      return (Ref)postForAll(methodObject32330, this.proxyFactory.proxyForCache((Object)this.delegate.readRef(), this, this.proxyCache, methodObject32330));
    }
    catch (SQLException e)
    {
      return (Ref)postForAll(methodObject32330, onErrorForAll(methodObject32330, e));
    }
  }
  
  public InputStream readAsciiStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32319, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject32319, (Object)this.delegate.readAsciiStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject32319, onErrorForAll(methodObject32319, e));
    }
  }
  
  public byte[] readBytes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32313, this, zeroLengthObjectArray);
      return (byte[])postForAll(methodObject32313, (Object)this.delegate.readBytes());
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject32313, onErrorForAll(methodObject32313, e));
    }
  }
  
  public InputStream readBinaryStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32321, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject32321, (Object)this.delegate.readBinaryStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject32321, onErrorForAll(methodObject32321, e));
    }
  }
  
  public String readString()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32333, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32333, (Object)this.delegate.readString());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32333, onErrorForAll(methodObject32333, e));
    }
  }
  
  public Clob readClob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32325, this, zeroLengthObjectArray);
      return (Clob)postForAll(methodObject32325, this.proxyFactory.proxyForCache((Object)this.delegate.readClob(), this, this.proxyCache, methodObject32325));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject32325, onErrorForAll(methodObject32325, e));
    }
  }
  
  public Array readArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32318, this, zeroLengthObjectArray);
      return (Array)postForAll(methodObject32318, this.proxyFactory.proxyForCache((Object)this.delegate.readArray(), this, this.proxyCache, methodObject32318));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject32318, onErrorForAll(methodObject32318, e));
    }
  }
  
  public RowId readRowId()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32331, this, zeroLengthObjectArray);
      return (RowId)postForAll(methodObject32331, this.proxyFactory.proxyForCache((Object)this.delegate.readRowId(), this, this.proxyCache, methodObject32331));
    }
    catch (SQLException e)
    {
      return (RowId)postForAll(methodObject32331, onErrorForAll(methodObject32331, e));
    }
  }
  
  public Object readObject()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32312, this, zeroLengthObjectArray);
      return postForAll(methodObject32312, this.proxyFactory.proxyForCache(this.delegate.readObject(), this, this.proxyCache, methodObject32312));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject32312, onErrorForAll(methodObject32312, e));
    }
  }
  
  public boolean readBoolean()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32323, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32323, Boolean.valueOf(this.delegate.readBoolean()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32323, onErrorForAll(methodObject32323, e))).booleanValue();
    }
  }
  
  public BigDecimal readBigDecimal()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32320, this, zeroLengthObjectArray);
      return (BigDecimal)postForAll(methodObject32320, (Object)this.delegate.readBigDecimal());
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject32320, onErrorForAll(methodObject32320, e));
    }
  }
  
  public byte readByte()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32314, this, zeroLengthObjectArray);
      return ((Byte)postForAll(methodObject32314, Byte.valueOf(this.delegate.readByte()))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject32314, onErrorForAll(methodObject32314, e))).byteValue();
    }
  }
  
  public NClob readNClob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32328, this, zeroLengthObjectArray);
      return (NClob)postForAll(methodObject32328, this.proxyFactory.proxyForCache((Object)this.delegate.readNClob(), this, this.proxyCache, methodObject32328));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject32328, onErrorForAll(methodObject32328, e));
    }
  }
  
  public double readDouble()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32327, this, zeroLengthObjectArray);
      return ((Double)postForAll(methodObject32327, Double.valueOf(this.delegate.readDouble()))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject32327, onErrorForAll(methodObject32327, e))).doubleValue();
    }
  }
  
  public SQLXML readSQLXML()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32332, this, zeroLengthObjectArray);
      return (SQLXML)postForAll(methodObject32332, this.proxyFactory.proxyForCache((Object)this.delegate.readSQLXML(), this, this.proxyCache, methodObject32332));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject32332, onErrorForAll(methodObject32332, e));
    }
  }
  
  public String readNString()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32329, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32329, (Object)this.delegate.readNString());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32329, onErrorForAll(methodObject32329, e));
    }
  }
  
  public Time readTime()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32334, this, zeroLengthObjectArray);
      return (Time)postForAll(methodObject32334, (Object)this.delegate.readTime());
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject32334, onErrorForAll(methodObject32334, e));
    }
  }
  
  public Timestamp readTimestamp()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32335, this, zeroLengthObjectArray);
      return (Timestamp)postForAll(methodObject32335, (Object)this.delegate.readTimestamp());
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject32335, onErrorForAll(methodObject32335, e));
    }
  }
  
  public SQLInput _getDelegate_()
  {
    return this.delegate;
  }
  
  public Object getDelegate()
  {
    return this.delegate;
  }
  
  public void setDelegate(Object delegate)
  {
    this.proxyFactory.updateDelegate(this, this.delegate, delegate);
    this.delegate = delegate;
  }
  
  public Object getCreator()
  {
    return this.creator;
  }
  
  static
  {
    try
    {
      methodObject32336 = SQLInput.class.getDeclaredMethod("readURL", new Class[0]);
      methodObject32317 = SQLInput.class.getDeclaredMethod("readFloat", new Class[0]);
      methodObject32316 = SQLInput.class.getDeclaredMethod("readShort", new Class[0]);
      methodObject32315 = SQLInput.class.getDeclaredMethod("readLong", new Class[0]);
      methodObject32322 = SQLInput.class.getDeclaredMethod("readBlob", new Class[0]);
      methodObject32337 = SQLInput.class.getDeclaredMethod("wasNull", new Class[0]);
      methodObject32324 = SQLInput.class.getDeclaredMethod("readCharacterStream", new Class[0]);
      methodObject32326 = SQLInput.class.getDeclaredMethod("readDate", new Class[0]);
      methodObject32311 = SQLInput.class.getDeclaredMethod("readInt", new Class[0]);
      methodObject32330 = SQLInput.class.getDeclaredMethod("readRef", new Class[0]);
      methodObject32319 = SQLInput.class.getDeclaredMethod("readAsciiStream", new Class[0]);
      methodObject32313 = SQLInput.class.getDeclaredMethod("readBytes", new Class[0]);
      methodObject32321 = SQLInput.class.getDeclaredMethod("readBinaryStream", new Class[0]);
      methodObject32333 = SQLInput.class.getDeclaredMethod("readString", new Class[0]);
      methodObject32325 = SQLInput.class.getDeclaredMethod("readClob", new Class[0]);
      methodObject32318 = SQLInput.class.getDeclaredMethod("readArray", new Class[0]);
      methodObject32331 = SQLInput.class.getDeclaredMethod("readRowId", new Class[0]);
      methodObject32312 = SQLInput.class.getDeclaredMethod("readObject", new Class[0]);
      methodObject32323 = SQLInput.class.getDeclaredMethod("readBoolean", new Class[0]);
      methodObject32320 = SQLInput.class.getDeclaredMethod("readBigDecimal", new Class[0]);
      methodObject32314 = SQLInput.class.getDeclaredMethod("readByte", new Class[0]);
      methodObject32328 = SQLInput.class.getDeclaredMethod("readNClob", new Class[0]);
      methodObject32327 = SQLInput.class.getDeclaredMethod("readDouble", new Class[0]);
      methodObject32332 = SQLInput.class.getDeclaredMethod("readSQLXML", new Class[0]);
      methodObject32329 = SQLInput.class.getDeclaredMethod("readNString", new Class[0]);
      methodObject32334 = SQLInput.class.getDeclaredMethod("readTime", new Class[0]);
      methodObject32335 = SQLInput.class.getDeclaredMethod("readTimestamp", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1SQLInput$$$Proxy(SQLInput paramSQLInput, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramSQLInput;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1SQLInput$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */