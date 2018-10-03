package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Ref;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.internal.OracleDatumWithConnection;
import oracle.jdbc.replay.driver.NonTxnReplayableRef;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableRef$2oracle$1jdbc$1internal$1OracleRef$$$Proxy
  extends NonTxnReplayableRef
  implements oracle.jdbc.internal.OracleRef, _Proxy_
{
  private oracle.jdbc.internal.OracleRef delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject30932;
  private static Method methodObject30943;
  private static Method methodObject30910;
  private static Method methodObject30946;
  private static Method methodObject30947;
  private static Method methodObject30906;
  private static Method methodObject30938;
  private static Method methodObject30918;
  private static Method methodObject30927;
  private static Method methodObject30949;
  private static Method methodObject30919;
  private static Method methodObject30941;
  private static Method methodObject30912;
  private static Method methodObject30915;
  private static Method methodObject30929;
  private static Method methodObject30937;
  private static Method methodObject30923;
  private static Method methodObject30942;
  private static Method methodObject30936;
  private static Method methodObject30931;
  private static Method methodObject30914;
  private static Method methodObject30948;
  private static Method methodObject30905;
  private static Method methodObject30940;
  private static Method methodObject30909;
  private static Method methodObject30934;
  private static Method methodObject30928;
  private static Method methodObject30944;
  private static Method methodObject30916;
  private static Method methodObject30907;
  private static Method methodObject30921;
  private static Method methodObject30933;
  private static Method methodObject30913;
  private static Method methodObject30926;
  private static Method methodObject30911;
  private static Method methodObject30945;
  private static Method methodObject30924;
  private static Method methodObject30920;
  private static Method methodObject30939;
  private static Method methodObject30908;
  private static Method methodObject30922;
  private static Method methodObject30930;
  private static Method methodObject30935;
  private static Method methodObject30917;
  private static Method methodObject30925;
  
  public String stringValue(Connection arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30932, this, new Object[] { arg0 });
      return (String)postForAll(methodObject30932, (Object)this.delegate.stringValue((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject30932, onErrorForAll(methodObject30932, e));
    }
  }
  
  public oracle.jdbc.internal.OracleConnection getInternalConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30943, this, zeroLengthObjectArray);
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject30943, this.proxyFactory.proxyForCache((Object)this.delegate.getInternalConnection(), this, this.proxyCache, methodObject30943));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject30943, onErrorForAll(methodObject30943, e));
    }
  }
  
  public StructDescriptor getDescriptor()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30910, this, zeroLengthObjectArray);
      return (StructDescriptor)postForAll(methodObject30910, (Object)this.delegate.getDescriptor());
    }
    catch (SQLException e)
    {
      return (StructDescriptor)postForAll(methodObject30910, onErrorForAll(methodObject30910, e));
    }
  }
  
  public Object getObject(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30946, this, new Object[] { arg0 });
      return postForAll(methodObject30946, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0), this, this.proxyCache, methodObject30946));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject30946, onErrorForAll(methodObject30946, e));
    }
  }
  
  public Object getObject()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30947, this, zeroLengthObjectArray);
      return postForAll(methodObject30947, this.proxyFactory.proxyForCache(this.delegate.getObject(), this, this.proxyCache, methodObject30947));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject30947, onErrorForAll(methodObject30947, e));
    }
  }
  
  public int hashCode()
  {
    super.preForAll(methodObject30906, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject30906, Integer.valueOf(this.delegate.hashCode()))).intValue();
  }
  
  public Timestamp timestampValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30938, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject30938, (Object)this.delegate.timestampValue(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject30938, onErrorForAll(methodObject30938, e));
    }
  }
  
  public byte[] getBytes()
  {
    super.preForAll(methodObject30918, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject30918, (Object)this.delegate.getBytes());
  }
  
  public Connection getJavaSqlConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30927, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject30927, this.proxyFactory.proxyForCache((Object)this.delegate.getJavaSqlConnection(), this, this.proxyCache, methodObject30927));
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject30927, onErrorForAll(methodObject30927, e));
    }
  }
  
  public void setObject(Object arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30949, this, new Object[] { arg0 });
      this.delegate.setObject((arg0 instanceof _Proxy_) ? (Object)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30949, e);
    }
  }
  
  public boolean booleanValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30919, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject30919, Boolean.valueOf(this.delegate.booleanValue()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject30919, onErrorForAll(methodObject30919, e))).booleanValue();
    }
  }
  
  public InputStream binaryStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30941, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject30941, (Object)this.delegate.binaryStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject30941, onErrorForAll(methodObject30941, e));
    }
  }
  
  public String getSQLTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30912, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject30912, (Object)this.delegate.getSQLTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject30912, onErrorForAll(methodObject30912, e));
    }
  }
  
  public Object makeJdbcArray(int arg0)
  {
    super.preForAll(methodObject30915, this, new Object[] { Integer.valueOf(arg0) });
    return postForAll(methodObject30915, this.proxyFactory.proxyForCache(this.delegate.makeJdbcArray(arg0), this, this.proxyCache, methodObject30915));
  }
  
  public void setShareBytes(byte[] arg0)
  {
    super.preForAll(methodObject30929, this, new Object[] { arg0 });
    this.delegate.setShareBytes(arg0);
  }
  
  public Timestamp timestampValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30937, this, zeroLengthObjectArray);
      return (Timestamp)postForAll(methodObject30937, (Object)this.delegate.timestampValue());
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject30937, onErrorForAll(methodObject30937, e));
    }
  }
  
  public int intValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30923, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject30923, Integer.valueOf(this.delegate.intValue()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject30923, onErrorForAll(methodObject30923, e))).intValue();
    }
  }
  
  public oracle.jdbc.OracleConnection getOracleConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30942, this, zeroLengthObjectArray);
      return (oracle.jdbc.OracleConnection)postForAll(methodObject30942, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleConnection(), this, this.proxyCache, methodObject30942));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.OracleConnection)postForAll(methodObject30942, onErrorForAll(methodObject30942, e));
    }
  }
  
  public Time timeValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30936, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject30936, (Object)this.delegate.timeValue(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject30936, onErrorForAll(methodObject30936, e));
    }
  }
  
  public String stringValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30931, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject30931, (Object)this.delegate.stringValue());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject30931, onErrorForAll(methodObject30931, e));
    }
  }
  
  public Object toJdbc()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30914, this, zeroLengthObjectArray);
      return postForAll(methodObject30914, this.proxyFactory.proxyForCache(this.delegate.toJdbc(), this, this.proxyCache, methodObject30914));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject30914, onErrorForAll(methodObject30914, e));
    }
  }
  
  public String getBaseTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30948, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject30948, (Object)this.delegate.getBaseTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject30948, onErrorForAll(methodObject30948, e));
    }
  }
  
  public boolean equals(Object arg0)
  {
    super.preForAll(methodObject30905, this, new Object[] { arg0 });
    return ((Boolean)postForAll(methodObject30905, Boolean.valueOf(this.delegate.equals((arg0 instanceof _Proxy_) ? (Object)((_Proxy_)arg0)._getDelegate_() : arg0)))).booleanValue();
  }
  
  public InputStream asciiStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30940, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject30940, (Object)this.delegate.asciiStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject30940, onErrorForAll(methodObject30940, e));
    }
  }
  
  public Object getValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30909, this, zeroLengthObjectArray);
      return postForAll(methodObject30909, this.proxyFactory.proxyForCache(this.delegate.getValue(), this, this.proxyCache, methodObject30909));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject30909, onErrorForAll(methodObject30909, e));
    }
  }
  
  public Date dateValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30934, this, zeroLengthObjectArray);
      return (Date)postForAll(methodObject30934, (Object)this.delegate.dateValue());
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject30934, onErrorForAll(methodObject30934, e));
    }
  }
  
  public byte[] shareBytes()
  {
    super.preForAll(methodObject30928, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject30928, (Object)this.delegate.shareBytes());
  }
  
  public void setPhysicalConnectionOf(Connection arg0)
  {
    super.preForAll(methodObject30944, this, new Object[] { arg0 });
    this.delegate.setPhysicalConnectionOf((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0);
  }
  
  public STRUCT getSTRUCT()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30916, this, zeroLengthObjectArray);
      return (STRUCT)postForAll(methodObject30916, (Object)this.delegate.getSTRUCT());
    }
    catch (SQLException e)
    {
      return (STRUCT)postForAll(methodObject30916, onErrorForAll(methodObject30916, e));
    }
  }
  
  public Object clone()
    throws CloneNotSupportedException
  {
    super.preForAll(methodObject30907, this, zeroLengthObjectArray);
    return postForAll(methodObject30907, this.proxyFactory.proxyForCache(this.delegate.clone(), this, this.proxyCache, methodObject30907));
  }
  
  public double doubleValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30921, this, zeroLengthObjectArray);
      return ((Double)postForAll(methodObject30921, Double.valueOf(this.delegate.doubleValue()))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject30921, onErrorForAll(methodObject30921, e))).doubleValue();
    }
  }
  
  public BigDecimal bigDecimalValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30933, this, zeroLengthObjectArray);
      return (BigDecimal)postForAll(methodObject30933, (Object)this.delegate.bigDecimalValue());
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject30933, onErrorForAll(methodObject30933, e));
    }
  }
  
  public boolean isConvertibleTo(Class arg0)
  {
    super.preForAll(methodObject30913, this, new Object[] { arg0 });
    return ((Boolean)postForAll(methodObject30913, Boolean.valueOf(this.delegate.isConvertibleTo(arg0)))).booleanValue();
  }
  
  public void setBytes(byte[] arg0)
  {
    super.preForAll(methodObject30926, this, new Object[] { arg0 });
    this.delegate.setBytes(arg0);
  }
  
  public void setValue(Object arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30911, this, new Object[] { arg0 });
      this.delegate.setValue((arg0 instanceof _Proxy_) ? (Object)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30911, e);
    }
  }
  
  public OracleTypeMetaData getOracleMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30945, this, zeroLengthObjectArray);
      return (OracleTypeMetaData)postForAll(methodObject30945, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleMetaData(), this, this.proxyCache, methodObject30945));
    }
    catch (SQLException e)
    {
      return (OracleTypeMetaData)postForAll(methodObject30945, onErrorForAll(methodObject30945, e));
    }
  }
  
  public long longValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30924, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject30924, Long.valueOf(this.delegate.longValue()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject30924, onErrorForAll(methodObject30924, e))).longValue();
    }
  }
  
  public byte byteValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30920, this, zeroLengthObjectArray);
      return ((Byte)postForAll(methodObject30920, Byte.valueOf(this.delegate.byteValue()))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject30920, onErrorForAll(methodObject30920, e))).byteValue();
    }
  }
  
  public Reader characterStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30939, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject30939, (Object)this.delegate.characterStreamValue());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject30939, onErrorForAll(methodObject30939, e));
    }
  }
  
  public Object getValue(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30908, this, new Object[] { arg0 });
      return postForAll(methodObject30908, this.proxyFactory.proxyForCache(this.delegate.getValue(arg0), this, this.proxyCache, methodObject30908));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject30908, onErrorForAll(methodObject30908, e));
    }
  }
  
  public float floatValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30922, this, zeroLengthObjectArray);
      return ((Float)postForAll(methodObject30922, Float.valueOf(this.delegate.floatValue()))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject30922, onErrorForAll(methodObject30922, e))).floatValue();
    }
  }
  
  public InputStream getStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30930, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject30930, (Object)this.delegate.getStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject30930, onErrorForAll(methodObject30930, e));
    }
  }
  
  public Time timeValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30935, this, zeroLengthObjectArray);
      return (Time)postForAll(methodObject30935, (Object)this.delegate.timeValue());
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject30935, onErrorForAll(methodObject30935, e));
    }
  }
  
  public long getLength()
  {
    super.preForAll(methodObject30917, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject30917, Long.valueOf(this.delegate.getLength()))).longValue();
  }
  
  public oracle.jdbc.driver.OracleConnection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30925, this, zeroLengthObjectArray);
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject30925, (Object)this.delegate.getConnection());
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject30925, onErrorForAll(methodObject30925, e));
    }
  }
  
  public oracle.jdbc.internal.OracleRef _getDelegate_()
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
      methodObject30932 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[] { Connection.class });
      methodObject30943 = OracleDatumWithConnection.class.getDeclaredMethod("getInternalConnection", new Class[0]);
      methodObject30910 = oracle.jdbc.internal.OracleRef.class.getDeclaredMethod("getDescriptor", new Class[0]);
      methodObject30946 = Ref.class.getDeclaredMethod("getObject", new Class[] { Map.class });
      methodObject30947 = Ref.class.getDeclaredMethod("getObject", new Class[0]);
      methodObject30906 = oracle.jdbc.internal.OracleRef.class.getDeclaredMethod("hashCode", new Class[0]);
      methodObject30938 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[] { Calendar.class });
      methodObject30918 = OracleDatumWithConnection.class.getDeclaredMethod("getBytes", new Class[0]);
      methodObject30927 = OracleDatumWithConnection.class.getDeclaredMethod("getJavaSqlConnection", new Class[0]);
      methodObject30949 = Ref.class.getDeclaredMethod("setObject", new Class[] { Object.class });
      methodObject30919 = OracleDatumWithConnection.class.getDeclaredMethod("booleanValue", new Class[0]);
      methodObject30941 = OracleDatumWithConnection.class.getDeclaredMethod("binaryStreamValue", new Class[0]);
      methodObject30912 = oracle.jdbc.internal.OracleRef.class.getDeclaredMethod("getSQLTypeName", new Class[0]);
      methodObject30915 = oracle.jdbc.internal.OracleRef.class.getDeclaredMethod("makeJdbcArray", new Class[] { Integer.TYPE });
      methodObject30929 = OracleDatumWithConnection.class.getDeclaredMethod("setShareBytes", new Class[] { byte[].class });
      methodObject30937 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[0]);
      methodObject30923 = OracleDatumWithConnection.class.getDeclaredMethod("intValue", new Class[0]);
      methodObject30942 = OracleDatumWithConnection.class.getDeclaredMethod("getOracleConnection", new Class[0]);
      methodObject30936 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[] { Calendar.class });
      methodObject30931 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[0]);
      methodObject30914 = oracle.jdbc.internal.OracleRef.class.getDeclaredMethod("toJdbc", new Class[0]);
      methodObject30948 = Ref.class.getDeclaredMethod("getBaseTypeName", new Class[0]);
      methodObject30905 = oracle.jdbc.internal.OracleRef.class.getDeclaredMethod("equals", new Class[] { Object.class });
      methodObject30940 = OracleDatumWithConnection.class.getDeclaredMethod("asciiStreamValue", new Class[0]);
      methodObject30909 = oracle.jdbc.internal.OracleRef.class.getDeclaredMethod("getValue", new Class[0]);
      methodObject30934 = OracleDatumWithConnection.class.getDeclaredMethod("dateValue", new Class[0]);
      methodObject30928 = OracleDatumWithConnection.class.getDeclaredMethod("shareBytes", new Class[0]);
      methodObject30944 = OracleDatumWithConnection.class.getDeclaredMethod("setPhysicalConnectionOf", new Class[] { Connection.class });
      methodObject30916 = oracle.jdbc.internal.OracleRef.class.getDeclaredMethod("getSTRUCT", new Class[0]);
      methodObject30907 = oracle.jdbc.internal.OracleRef.class.getDeclaredMethod("clone", new Class[0]);
      methodObject30921 = OracleDatumWithConnection.class.getDeclaredMethod("doubleValue", new Class[0]);
      methodObject30933 = OracleDatumWithConnection.class.getDeclaredMethod("bigDecimalValue", new Class[0]);
      methodObject30913 = oracle.jdbc.internal.OracleRef.class.getDeclaredMethod("isConvertibleTo", new Class[] { Class.class });
      methodObject30926 = OracleDatumWithConnection.class.getDeclaredMethod("setBytes", new Class[] { byte[].class });
      methodObject30911 = oracle.jdbc.internal.OracleRef.class.getDeclaredMethod("setValue", new Class[] { Object.class });
      methodObject30945 = oracle.jdbc.OracleRef.class.getDeclaredMethod("getOracleMetaData", new Class[0]);
      methodObject30924 = OracleDatumWithConnection.class.getDeclaredMethod("longValue", new Class[0]);
      methodObject30920 = OracleDatumWithConnection.class.getDeclaredMethod("byteValue", new Class[0]);
      methodObject30939 = OracleDatumWithConnection.class.getDeclaredMethod("characterStreamValue", new Class[0]);
      methodObject30908 = oracle.jdbc.internal.OracleRef.class.getDeclaredMethod("getValue", new Class[] { Map.class });
      methodObject30922 = OracleDatumWithConnection.class.getDeclaredMethod("floatValue", new Class[0]);
      methodObject30930 = OracleDatumWithConnection.class.getDeclaredMethod("getStream", new Class[0]);
      methodObject30935 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[0]);
      methodObject30917 = OracleDatumWithConnection.class.getDeclaredMethod("getLength", new Class[0]);
      methodObject30925 = OracleDatumWithConnection.class.getDeclaredMethod("getConnection", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableRef$2oracle$1jdbc$1internal$1OracleRef$$$Proxy(oracle.jdbc.internal.OracleRef paramOracleRef, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleRef;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableRef$2oracle$1jdbc$1internal$1OracleRef$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */