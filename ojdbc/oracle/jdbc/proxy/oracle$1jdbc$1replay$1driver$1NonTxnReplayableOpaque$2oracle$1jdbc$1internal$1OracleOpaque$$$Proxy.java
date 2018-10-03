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
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.internal.OracleDatumWithConnection;
import oracle.jdbc.replay.driver.NonTxnReplayableOpaque;
import oracle.sql.OpaqueDescriptor;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableOpaque$2oracle$1jdbc$1internal$1OracleOpaque$$$Proxy
  extends NonTxnReplayableOpaque
  implements oracle.jdbc.internal.OracleOpaque, _Proxy_
{
  private oracle.jdbc.internal.OracleOpaque delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject26618;
  private static Method methodObject26629;
  private static Method methodObject26587;
  private static Method methodObject26601;
  private static Method methodObject26595;
  private static Method methodObject26624;
  private static Method methodObject26592;
  private static Method methodObject26589;
  private static Method methodObject26605;
  private static Method methodObject26600;
  private static Method methodObject26606;
  private static Method methodObject26632;
  private static Method methodObject26627;
  private static Method methodObject26590;
  private static Method methodObject26596;
  private static Method methodObject26615;
  private static Method methodObject26591;
  private static Method methodObject26623;
  private static Method methodObject26588;
  private static Method methodObject26610;
  private static Method methodObject26628;
  private static Method methodObject26622;
  private static Method methodObject26617;
  private static Method methodObject26594;
  private static Method methodObject26626;
  private static Method methodObject26631;
  private static Method methodObject26620;
  private static Method methodObject26614;
  private static Method methodObject26598;
  private static Method methodObject26602;
  private static Method methodObject26630;
  private static Method methodObject26608;
  private static Method methodObject26619;
  private static Method methodObject26593;
  private static Method methodObject26603;
  private static Method methodObject26613;
  private static Method methodObject26633;
  private static Method methodObject26599;
  private static Method methodObject26611;
  private static Method methodObject26607;
  private static Method methodObject26625;
  private static Method methodObject26609;
  private static Method methodObject26616;
  private static Method methodObject26597;
  private static Method methodObject26621;
  private static Method methodObject26604;
  private static Method methodObject26612;
  
  public String stringValue(Connection arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26618, this, new Object[] { arg0 });
      return (String)postForAll(methodObject26618, (Object)this.delegate.stringValue((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject26618, onErrorForAll(methodObject26618, e));
    }
  }
  
  public oracle.jdbc.internal.OracleConnection getInternalConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26629, this, zeroLengthObjectArray);
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject26629, this.proxyFactory.proxyForCache((Object)this.delegate.getInternalConnection(), this, this.proxyCache, methodObject26629));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject26629, onErrorForAll(methodObject26629, e));
    }
  }
  
  public OpaqueDescriptor getDescriptor()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26587, this, zeroLengthObjectArray);
      return (OpaqueDescriptor)postForAll(methodObject26587, (Object)this.delegate.getDescriptor());
    }
    catch (SQLException e)
    {
      return (OpaqueDescriptor)postForAll(methodObject26587, onErrorForAll(methodObject26587, e));
    }
  }
  
  public long getImageLength()
  {
    super.preForAll(methodObject26601, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject26601, Long.valueOf(this.delegate.getImageLength()))).longValue();
  }
  
  public Object toJdbc(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26595, this, new Object[] { arg0 });
      return postForAll(methodObject26595, this.proxyFactory.proxyForCache(this.delegate.toJdbc(arg0), this, this.proxyCache, methodObject26595));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject26595, onErrorForAll(methodObject26595, e));
    }
  }
  
  public Timestamp timestampValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26624, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject26624, (Object)this.delegate.timestampValue(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject26624, onErrorForAll(methodObject26624, e));
    }
  }
  
  public Connection getJavaSqlConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26592, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject26592, (Object)super.getJavaSqlConnection());
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject26592, onErrorForAll(methodObject26592, e));
    }
  }
  
  public Object toClass(Class arg0, Map arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26589, this, new Object[] { arg0, arg1 });
      return postForAll(methodObject26589, this.proxyFactory.proxyForCache(this.delegate.toClass(arg0, arg1), this, this.proxyCache, methodObject26589));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject26589, onErrorForAll(methodObject26589, e));
    }
  }
  
  public byte[] getBytes()
  {
    super.preForAll(methodObject26605, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject26605, (Object)this.delegate.getBytes());
  }
  
  public long getImageOffset()
  {
    super.preForAll(methodObject26600, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject26600, Long.valueOf(this.delegate.getImageOffset()))).longValue();
  }
  
  public boolean booleanValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26606, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject26606, Boolean.valueOf(this.delegate.booleanValue()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject26606, onErrorForAll(methodObject26606, e))).booleanValue();
    }
  }
  
  public String getSQLTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26632, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject26632, (Object)this.delegate.getSQLTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject26632, onErrorForAll(methodObject26632, e));
    }
  }
  
  public InputStream binaryStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26627, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject26627, (Object)this.delegate.binaryStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject26627, onErrorForAll(methodObject26627, e));
    }
  }
  
  public void setValue(byte[] arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26590, this, new Object[] { arg0 });
      this.delegate.setValue(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject26590, e);
    }
  }
  
  public Object makeJdbcArray(int arg0)
  {
    super.preForAll(methodObject26596, this, new Object[] { Integer.valueOf(arg0) });
    return postForAll(methodObject26596, this.proxyFactory.proxyForCache(this.delegate.makeJdbcArray(arg0), this, this.proxyCache, methodObject26596));
  }
  
  public void setShareBytes(byte[] arg0)
  {
    super.preForAll(methodObject26615, this, new Object[] { arg0 });
    this.delegate.setShareBytes(arg0);
  }
  
  public Map getMap()
  {
    super.preForAll(methodObject26591, this, zeroLengthObjectArray);
    return (Map)postForAll(methodObject26591, (Object)this.delegate.getMap());
  }
  
  public Timestamp timestampValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26623, this, zeroLengthObjectArray);
      return (Timestamp)postForAll(methodObject26623, (Object)this.delegate.timestampValue());
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject26623, onErrorForAll(methodObject26623, e));
    }
  }
  
  public Object toClass(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26588, this, new Object[] { arg0 });
      return postForAll(methodObject26588, this.proxyFactory.proxyForCache(this.delegate.toClass(arg0), this, this.proxyCache, methodObject26588));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject26588, onErrorForAll(methodObject26588, e));
    }
  }
  
  public int intValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26610, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject26610, Integer.valueOf(this.delegate.intValue()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject26610, onErrorForAll(methodObject26610, e))).intValue();
    }
  }
  
  public oracle.jdbc.OracleConnection getOracleConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26628, this, zeroLengthObjectArray);
      return (oracle.jdbc.OracleConnection)postForAll(methodObject26628, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleConnection(), this, this.proxyCache, methodObject26628));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.OracleConnection)postForAll(methodObject26628, onErrorForAll(methodObject26628, e));
    }
  }
  
  public Time timeValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26622, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject26622, (Object)this.delegate.timeValue(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject26622, onErrorForAll(methodObject26622, e));
    }
  }
  
  public String stringValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26617, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject26617, (Object)this.delegate.stringValue());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject26617, onErrorForAll(methodObject26617, e));
    }
  }
  
  public Object toJdbc()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26594, this, zeroLengthObjectArray);
      return postForAll(methodObject26594, this.proxyFactory.proxyForCache(this.delegate.toJdbc(), this, this.proxyCache, methodObject26594));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject26594, onErrorForAll(methodObject26594, e));
    }
  }
  
  public InputStream asciiStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26626, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject26626, (Object)this.delegate.asciiStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject26626, onErrorForAll(methodObject26626, e));
    }
  }
  
  public Object getValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26631, this, zeroLengthObjectArray);
      return postForAll(methodObject26631, this.proxyFactory.proxyForCache(this.delegate.getValue(), this, this.proxyCache, methodObject26631));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject26631, onErrorForAll(methodObject26631, e));
    }
  }
  
  public Date dateValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26620, this, zeroLengthObjectArray);
      return (Date)postForAll(methodObject26620, (Object)this.delegate.dateValue());
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject26620, onErrorForAll(methodObject26620, e));
    }
  }
  
  public byte[] shareBytes()
  {
    super.preForAll(methodObject26614, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject26614, (Object)this.delegate.shareBytes());
  }
  
  public void setImage(byte[] arg0, long arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26598, this, new Object[] { arg0, Long.valueOf(arg1), Long.valueOf(arg2) });
      this.delegate.setImage(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject26598, e);
    }
  }
  
  public void setDescriptor(OpaqueDescriptor arg0)
  {
    super.preForAll(methodObject26602, this, new Object[] { arg0 });
    this.delegate.setDescriptor(arg0);
  }
  
  public void setPhysicalConnectionOf(Connection arg0)
  {
    super.preForAll(methodObject26630, this, new Object[] { arg0 });
    this.delegate.setPhysicalConnectionOf((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0);
  }
  
  public double doubleValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26608, this, zeroLengthObjectArray);
      return ((Double)postForAll(methodObject26608, Double.valueOf(this.delegate.doubleValue()))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject26608, onErrorForAll(methodObject26608, e))).doubleValue();
    }
  }
  
  public BigDecimal bigDecimalValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26619, this, zeroLengthObjectArray);
      return (BigDecimal)postForAll(methodObject26619, (Object)this.delegate.bigDecimalValue());
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject26619, onErrorForAll(methodObject26619, e));
    }
  }
  
  public boolean isConvertibleTo(Class arg0)
  {
    super.preForAll(methodObject26593, this, new Object[] { arg0 });
    return ((Boolean)postForAll(methodObject26593, Boolean.valueOf(this.delegate.isConvertibleTo(arg0)))).booleanValue();
  }
  
  public byte[] getBytesValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26603, this, zeroLengthObjectArray);
      return (byte[])postForAll(methodObject26603, (Object)this.delegate.getBytesValue());
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject26603, onErrorForAll(methodObject26603, e));
    }
  }
  
  public void setBytes(byte[] arg0)
  {
    super.preForAll(methodObject26613, this, new Object[] { arg0 });
    this.delegate.setBytes(arg0);
  }
  
  public OracleTypeMetaData getOracleMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26633, this, zeroLengthObjectArray);
      return (OracleTypeMetaData)postForAll(methodObject26633, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleMetaData(), this, this.proxyCache, methodObject26633));
    }
    catch (SQLException e)
    {
      return (OracleTypeMetaData)postForAll(methodObject26633, onErrorForAll(methodObject26633, e));
    }
  }
  
  public void setImageLength(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26599, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.setImageLength(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject26599, e);
    }
  }
  
  public long longValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26611, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject26611, Long.valueOf(this.delegate.longValue()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject26611, onErrorForAll(methodObject26611, e))).longValue();
    }
  }
  
  public byte byteValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26607, this, zeroLengthObjectArray);
      return ((Byte)postForAll(methodObject26607, Byte.valueOf(this.delegate.byteValue()))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject26607, onErrorForAll(methodObject26607, e))).byteValue();
    }
  }
  
  public Reader characterStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26625, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject26625, (Object)this.delegate.characterStreamValue());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject26625, onErrorForAll(methodObject26625, e));
    }
  }
  
  public float floatValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26609, this, zeroLengthObjectArray);
      return ((Float)postForAll(methodObject26609, Float.valueOf(this.delegate.floatValue()))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject26609, onErrorForAll(methodObject26609, e))).floatValue();
    }
  }
  
  public InputStream getStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26616, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject26616, (Object)this.delegate.getStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject26616, onErrorForAll(methodObject26616, e));
    }
  }
  
  public byte[] toBytes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26597, this, zeroLengthObjectArray);
      return (byte[])postForAll(methodObject26597, (Object)this.delegate.toBytes());
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject26597, onErrorForAll(methodObject26597, e));
    }
  }
  
  public Time timeValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26621, this, zeroLengthObjectArray);
      return (Time)postForAll(methodObject26621, (Object)this.delegate.timeValue());
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject26621, onErrorForAll(methodObject26621, e));
    }
  }
  
  public long getLength()
  {
    super.preForAll(methodObject26604, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject26604, Long.valueOf(this.delegate.getLength()))).longValue();
  }
  
  public oracle.jdbc.driver.OracleConnection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26612, this, zeroLengthObjectArray);
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject26612, (Object)this.delegate.getConnection());
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject26612, onErrorForAll(methodObject26612, e));
    }
  }
  
  public oracle.jdbc.internal.OracleOpaque _getDelegate_()
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
      methodObject26618 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[] { Connection.class });
      methodObject26629 = OracleDatumWithConnection.class.getDeclaredMethod("getInternalConnection", new Class[0]);
      methodObject26587 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("getDescriptor", new Class[0]);
      methodObject26601 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("getImageLength", new Class[0]);
      methodObject26595 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("toJdbc", new Class[] { Map.class });
      methodObject26624 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[] { Calendar.class });
      methodObject26592 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("getJavaSqlConnection", new Class[0]);
      methodObject26589 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("toClass", new Class[] { Class.class, Map.class });
      methodObject26605 = OracleDatumWithConnection.class.getDeclaredMethod("getBytes", new Class[0]);
      methodObject26600 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("getImageOffset", new Class[0]);
      methodObject26606 = OracleDatumWithConnection.class.getDeclaredMethod("booleanValue", new Class[0]);
      methodObject26632 = oracle.jdbc.OracleOpaque.class.getDeclaredMethod("getSQLTypeName", new Class[0]);
      methodObject26627 = OracleDatumWithConnection.class.getDeclaredMethod("binaryStreamValue", new Class[0]);
      methodObject26590 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("setValue", new Class[] { byte[].class });
      methodObject26596 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("makeJdbcArray", new Class[] { Integer.TYPE });
      methodObject26615 = OracleDatumWithConnection.class.getDeclaredMethod("setShareBytes", new Class[] { byte[].class });
      methodObject26591 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("getMap", new Class[0]);
      methodObject26623 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[0]);
      methodObject26588 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("toClass", new Class[] { Class.class });
      methodObject26610 = OracleDatumWithConnection.class.getDeclaredMethod("intValue", new Class[0]);
      methodObject26628 = OracleDatumWithConnection.class.getDeclaredMethod("getOracleConnection", new Class[0]);
      methodObject26622 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[] { Calendar.class });
      methodObject26617 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[0]);
      methodObject26594 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("toJdbc", new Class[0]);
      methodObject26626 = OracleDatumWithConnection.class.getDeclaredMethod("asciiStreamValue", new Class[0]);
      methodObject26631 = oracle.jdbc.OracleOpaque.class.getDeclaredMethod("getValue", new Class[0]);
      methodObject26620 = OracleDatumWithConnection.class.getDeclaredMethod("dateValue", new Class[0]);
      methodObject26614 = OracleDatumWithConnection.class.getDeclaredMethod("shareBytes", new Class[0]);
      methodObject26598 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("setImage", new Class[] { byte[].class, Long.TYPE, Long.TYPE });
      methodObject26602 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("setDescriptor", new Class[] { OpaqueDescriptor.class });
      methodObject26630 = OracleDatumWithConnection.class.getDeclaredMethod("setPhysicalConnectionOf", new Class[] { Connection.class });
      methodObject26608 = OracleDatumWithConnection.class.getDeclaredMethod("doubleValue", new Class[0]);
      methodObject26619 = OracleDatumWithConnection.class.getDeclaredMethod("bigDecimalValue", new Class[0]);
      methodObject26593 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("isConvertibleTo", new Class[] { Class.class });
      methodObject26603 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("getBytesValue", new Class[0]);
      methodObject26613 = OracleDatumWithConnection.class.getDeclaredMethod("setBytes", new Class[] { byte[].class });
      methodObject26633 = oracle.jdbc.OracleOpaque.class.getDeclaredMethod("getOracleMetaData", new Class[0]);
      methodObject26599 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("setImageLength", new Class[] { Long.TYPE });
      methodObject26611 = OracleDatumWithConnection.class.getDeclaredMethod("longValue", new Class[0]);
      methodObject26607 = OracleDatumWithConnection.class.getDeclaredMethod("byteValue", new Class[0]);
      methodObject26625 = OracleDatumWithConnection.class.getDeclaredMethod("characterStreamValue", new Class[0]);
      methodObject26609 = OracleDatumWithConnection.class.getDeclaredMethod("floatValue", new Class[0]);
      methodObject26616 = OracleDatumWithConnection.class.getDeclaredMethod("getStream", new Class[0]);
      methodObject26597 = oracle.jdbc.internal.OracleOpaque.class.getDeclaredMethod("toBytes", new Class[0]);
      methodObject26621 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[0]);
      methodObject26604 = OracleDatumWithConnection.class.getDeclaredMethod("getLength", new Class[0]);
      methodObject26612 = OracleDatumWithConnection.class.getDeclaredMethod("getConnection", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableOpaque$2oracle$1jdbc$1internal$1OracleOpaque$$$Proxy(oracle.jdbc.internal.OracleOpaque paramOracleOpaque, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleOpaque;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableOpaque$2oracle$1jdbc$1internal$1OracleOpaque$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */