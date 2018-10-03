package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import oracle.jdbc.internal.OracleDatumWithConnection;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1internal$1OracleDatumWithConnection$$$Proxy
  extends NonTxnReplayableBase
  implements OracleDatumWithConnection, _Proxy_
{
  private OracleDatumWithConnection delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32622;
  private static Method methodObject32636;
  private static Method methodObject32617;
  private static Method methodObject32608;
  private static Method methodObject32628;
  private static Method methodObject32609;
  private static Method methodObject32631;
  private static Method methodObject32634;
  private static Method methodObject32619;
  private static Method methodObject32627;
  private static Method methodObject32613;
  private static Method methodObject32635;
  private static Method methodObject32621;
  private static Method methodObject32626;
  private static Method methodObject32633;
  private static Method methodObject32630;
  private static Method methodObject32618;
  private static Method methodObject32624;
  private static Method methodObject32637;
  private static Method methodObject32611;
  private static Method methodObject32632;
  private static Method methodObject32623;
  private static Method methodObject32616;
  private static Method methodObject32614;
  private static Method methodObject32610;
  private static Method methodObject32612;
  private static Method methodObject32629;
  private static Method methodObject32620;
  private static Method methodObject32607;
  private static Method methodObject32625;
  private static Method methodObject32615;
  
  public String stringValue(Connection arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32622, this, new Object[] { arg0 });
      return (String)postForAll(methodObject32622, (Object)this.delegate.stringValue((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32622, onErrorForAll(methodObject32622, e));
    }
  }
  
  public oracle.jdbc.internal.OracleConnection getInternalConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32636, this, zeroLengthObjectArray);
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject32636, this.proxyFactory.proxyForCache((Object)this.delegate.getInternalConnection(), this, this.proxyCache, methodObject32636));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject32636, onErrorForAll(methodObject32636, e));
    }
  }
  
  public Connection getJavaSqlConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32617, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject32617, this.proxyFactory.proxyForCache((Object)this.delegate.getJavaSqlConnection(), this, this.proxyCache, methodObject32617));
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject32617, onErrorForAll(methodObject32617, e));
    }
  }
  
  public byte[] getBytes()
  {
    super.preForAll(methodObject32608, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject32608, (Object)this.delegate.getBytes());
  }
  
  public Timestamp timestampValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32628, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject32628, (Object)this.delegate.timestampValue(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject32628, onErrorForAll(methodObject32628, e));
    }
  }
  
  public boolean booleanValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32609, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32609, Boolean.valueOf(this.delegate.booleanValue()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32609, onErrorForAll(methodObject32609, e))).booleanValue();
    }
  }
  
  public InputStream binaryStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32631, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject32631, (Object)this.delegate.binaryStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject32631, onErrorForAll(methodObject32631, e));
    }
  }
  
  public Object makeJdbcArray(int arg0)
  {
    super.preForAll(methodObject32634, this, new Object[] { Integer.valueOf(arg0) });
    return postForAll(methodObject32634, this.proxyFactory.proxyForCache(this.delegate.makeJdbcArray(arg0), this, this.proxyCache, methodObject32634));
  }
  
  public void setShareBytes(byte[] arg0)
  {
    super.preForAll(methodObject32619, this, new Object[] { arg0 });
    this.delegate.setShareBytes(arg0);
    postForAll(methodObject32619);
  }
  
  public Timestamp timestampValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32627, this, zeroLengthObjectArray);
      return (Timestamp)postForAll(methodObject32627, (Object)this.delegate.timestampValue());
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject32627, onErrorForAll(methodObject32627, e));
    }
  }
  
  public int intValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32613, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32613, Integer.valueOf(this.delegate.intValue()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32613, onErrorForAll(methodObject32613, e))).intValue();
    }
  }
  
  public oracle.jdbc.OracleConnection getOracleConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32635, this, zeroLengthObjectArray);
      return (oracle.jdbc.OracleConnection)postForAll(methodObject32635, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleConnection(), this, this.proxyCache, methodObject32635));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.OracleConnection)postForAll(methodObject32635, onErrorForAll(methodObject32635, e));
    }
  }
  
  public String stringValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32621, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32621, (Object)this.delegate.stringValue());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32621, onErrorForAll(methodObject32621, e));
    }
  }
  
  public Time timeValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32626, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject32626, (Object)this.delegate.timeValue(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject32626, onErrorForAll(methodObject32626, e));
    }
  }
  
  public Object toJdbc()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32633, this, zeroLengthObjectArray);
      return postForAll(methodObject32633, this.proxyFactory.proxyForCache(this.delegate.toJdbc(), this, this.proxyCache, methodObject32633));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject32633, onErrorForAll(methodObject32633, e));
    }
  }
  
  public InputStream asciiStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32630, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject32630, (Object)this.delegate.asciiStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject32630, onErrorForAll(methodObject32630, e));
    }
  }
  
  public byte[] shareBytes()
  {
    super.preForAll(methodObject32618, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject32618, (Object)this.delegate.shareBytes());
  }
  
  public Date dateValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32624, this, zeroLengthObjectArray);
      return (Date)postForAll(methodObject32624, (Object)this.delegate.dateValue());
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject32624, onErrorForAll(methodObject32624, e));
    }
  }
  
  public void setPhysicalConnectionOf(Connection arg0)
  {
    super.preForAll(methodObject32637, this, new Object[] { arg0 });
    this.delegate.setPhysicalConnectionOf((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0);
    postForAll(methodObject32637);
  }
  
  public double doubleValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32611, this, zeroLengthObjectArray);
      return ((Double)postForAll(methodObject32611, Double.valueOf(this.delegate.doubleValue()))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject32611, onErrorForAll(methodObject32611, e))).doubleValue();
    }
  }
  
  public boolean isConvertibleTo(Class arg0)
  {
    super.preForAll(methodObject32632, this, new Object[] { arg0 });
    return ((Boolean)postForAll(methodObject32632, Boolean.valueOf(this.delegate.isConvertibleTo(arg0)))).booleanValue();
  }
  
  public BigDecimal bigDecimalValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32623, this, zeroLengthObjectArray);
      return (BigDecimal)postForAll(methodObject32623, (Object)this.delegate.bigDecimalValue());
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject32623, onErrorForAll(methodObject32623, e));
    }
  }
  
  public void setBytes(byte[] arg0)
  {
    super.preForAll(methodObject32616, this, new Object[] { arg0 });
    this.delegate.setBytes(arg0);
    postForAll(methodObject32616);
  }
  
  public long longValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32614, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject32614, Long.valueOf(this.delegate.longValue()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject32614, onErrorForAll(methodObject32614, e))).longValue();
    }
  }
  
  public byte byteValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32610, this, zeroLengthObjectArray);
      return ((Byte)postForAll(methodObject32610, Byte.valueOf(this.delegate.byteValue()))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject32610, onErrorForAll(methodObject32610, e))).byteValue();
    }
  }
  
  public float floatValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32612, this, zeroLengthObjectArray);
      return ((Float)postForAll(methodObject32612, Float.valueOf(this.delegate.floatValue()))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject32612, onErrorForAll(methodObject32612, e))).floatValue();
    }
  }
  
  public Reader characterStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32629, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject32629, (Object)this.delegate.characterStreamValue());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject32629, onErrorForAll(methodObject32629, e));
    }
  }
  
  public InputStream getStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32620, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject32620, (Object)this.delegate.getStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject32620, onErrorForAll(methodObject32620, e));
    }
  }
  
  public long getLength()
  {
    super.preForAll(methodObject32607, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject32607, Long.valueOf(this.delegate.getLength()))).longValue();
  }
  
  public Time timeValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32625, this, zeroLengthObjectArray);
      return (Time)postForAll(methodObject32625, (Object)this.delegate.timeValue());
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject32625, onErrorForAll(methodObject32625, e));
    }
  }
  
  public oracle.jdbc.driver.OracleConnection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32615, this, zeroLengthObjectArray);
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject32615, (Object)this.delegate.getConnection());
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject32615, onErrorForAll(methodObject32615, e));
    }
  }
  
  public OracleDatumWithConnection _getDelegate_()
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
      methodObject32622 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[] { Connection.class });
      methodObject32636 = OracleDatumWithConnection.class.getDeclaredMethod("getInternalConnection", new Class[0]);
      methodObject32617 = OracleDatumWithConnection.class.getDeclaredMethod("getJavaSqlConnection", new Class[0]);
      methodObject32608 = OracleDatumWithConnection.class.getDeclaredMethod("getBytes", new Class[0]);
      methodObject32628 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[] { Calendar.class });
      methodObject32609 = OracleDatumWithConnection.class.getDeclaredMethod("booleanValue", new Class[0]);
      methodObject32631 = OracleDatumWithConnection.class.getDeclaredMethod("binaryStreamValue", new Class[0]);
      methodObject32634 = OracleDatumWithConnection.class.getDeclaredMethod("makeJdbcArray", new Class[] { Integer.TYPE });
      methodObject32619 = OracleDatumWithConnection.class.getDeclaredMethod("setShareBytes", new Class[] { byte[].class });
      methodObject32627 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[0]);
      methodObject32613 = OracleDatumWithConnection.class.getDeclaredMethod("intValue", new Class[0]);
      methodObject32635 = OracleDatumWithConnection.class.getDeclaredMethod("getOracleConnection", new Class[0]);
      methodObject32621 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[0]);
      methodObject32626 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[] { Calendar.class });
      methodObject32633 = OracleDatumWithConnection.class.getDeclaredMethod("toJdbc", new Class[0]);
      methodObject32630 = OracleDatumWithConnection.class.getDeclaredMethod("asciiStreamValue", new Class[0]);
      methodObject32618 = OracleDatumWithConnection.class.getDeclaredMethod("shareBytes", new Class[0]);
      methodObject32624 = OracleDatumWithConnection.class.getDeclaredMethod("dateValue", new Class[0]);
      methodObject32637 = OracleDatumWithConnection.class.getDeclaredMethod("setPhysicalConnectionOf", new Class[] { Connection.class });
      methodObject32611 = OracleDatumWithConnection.class.getDeclaredMethod("doubleValue", new Class[0]);
      methodObject32632 = OracleDatumWithConnection.class.getDeclaredMethod("isConvertibleTo", new Class[] { Class.class });
      methodObject32623 = OracleDatumWithConnection.class.getDeclaredMethod("bigDecimalValue", new Class[0]);
      methodObject32616 = OracleDatumWithConnection.class.getDeclaredMethod("setBytes", new Class[] { byte[].class });
      methodObject32614 = OracleDatumWithConnection.class.getDeclaredMethod("longValue", new Class[0]);
      methodObject32610 = OracleDatumWithConnection.class.getDeclaredMethod("byteValue", new Class[0]);
      methodObject32612 = OracleDatumWithConnection.class.getDeclaredMethod("floatValue", new Class[0]);
      methodObject32629 = OracleDatumWithConnection.class.getDeclaredMethod("characterStreamValue", new Class[0]);
      methodObject32620 = OracleDatumWithConnection.class.getDeclaredMethod("getStream", new Class[0]);
      methodObject32607 = OracleDatumWithConnection.class.getDeclaredMethod("getLength", new Class[0]);
      methodObject32625 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[0]);
      methodObject32615 = OracleDatumWithConnection.class.getDeclaredMethod("getConnection", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1internal$1OracleDatumWithConnection$$$Proxy(OracleDatumWithConnection paramOracleDatumWithConnection, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleDatumWithConnection;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1internal$1OracleDatumWithConnection$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */