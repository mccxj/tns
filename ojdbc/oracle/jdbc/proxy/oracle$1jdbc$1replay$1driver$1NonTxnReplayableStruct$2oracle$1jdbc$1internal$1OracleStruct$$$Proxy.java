package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.internal.OracleDatumWithConnection;
import oracle.jdbc.replay.driver.NonTxnReplayableStruct;
import oracle.sql.Datum;
import oracle.sql.ORADataFactory;
import oracle.sql.StructDescriptor;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableStruct$2oracle$1jdbc$1internal$1OracleStruct$$$Proxy
  extends NonTxnReplayableStruct
  implements oracle.jdbc.internal.OracleStruct, _Proxy_
{
  private oracle.jdbc.internal.OracleStruct delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject23228;
  private static Method methodObject23267;
  private static Method methodObject23234;
  private static Method methodObject23221;
  private static Method methodObject23259;
  private static Method methodObject23241;
  private static Method methodObject23233;
  private static Method methodObject23269;
  private static Method methodObject23220;
  private static Method methodObject23258;
  private static Method methodObject23245;
  private static Method methodObject23252;
  private static Method methodObject23257;
  private static Method methodObject23261;
  private static Method methodObject23229;
  private static Method methodObject23231;
  private static Method methodObject23249;
  private static Method methodObject23265;
  private static Method methodObject23243;
  private static Method methodObject23222;
  private static Method methodObject23236;
  private static Method methodObject23232;
  private static Method methodObject23246;
  private static Method methodObject23227;
  private static Method methodObject23268;
  private static Method methodObject23253;
  private static Method methodObject23217;
  private static Method methodObject23264;
  private static Method methodObject23224;
  private static Method methodObject23240;
  private static Method methodObject23219;
  private static Method methodObject23235;
  private static Method methodObject23230;
  private static Method methodObject23262;
  private static Method methodObject23237;
  private static Method methodObject23225;
  private static Method methodObject23250;
  private static Method methodObject23218;
  private static Method methodObject23263;
  private static Method methodObject23223;
  private static Method methodObject23238;
  private static Method methodObject23255;
  private static Method methodObject23254;
  private static Method methodObject23248;
  private static Method methodObject23266;
  private static Method methodObject23242;
  private static Method methodObject23244;
  private static Method methodObject23260;
  private static Method methodObject23226;
  private static Method methodObject23251;
  private static Method methodObject23239;
  private static Method methodObject23256;
  private static Method methodObject23247;
  
  public void setObjArray(Object[] arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23228, this, new Object[] { arg0 });
      this.delegate.setObjArray(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23228, e);
    }
  }
  
  public Object[] getAttributes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23267, this, zeroLengthObjectArray);
      return (Object[])postForAll(methodObject23267, (Object)this.delegate.getAttributes());
    }
    catch (SQLException e)
    {
      return (Object[])postForAll(methodObject23267, onErrorForAll(methodObject23267, e));
    }
  }
  
  public long getImageLength()
  {
    super.preForAll(methodObject23234, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject23234, Long.valueOf(this.delegate.getImageLength()))).longValue();
  }
  
  public Connection getJavaSqlConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23221, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject23221, (Object)super.getJavaSqlConnection());
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject23221, onErrorForAll(methodObject23221, e));
    }
  }
  
  public Timestamp timestampValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23259, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject23259, (Object)this.delegate.timestampValue(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject23259, onErrorForAll(methodObject23259, e));
    }
  }
  
  public boolean booleanValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23241, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23241, Boolean.valueOf(this.delegate.booleanValue()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23241, onErrorForAll(methodObject23241, e))).booleanValue();
    }
  }
  
  public long getImageOffset()
  {
    super.preForAll(methodObject23233, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject23233, Long.valueOf(this.delegate.getImageOffset()))).longValue();
  }
  
  public String getSQLTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23269, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject23269, (Object)this.delegate.getSQLTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject23269, onErrorForAll(methodObject23269, e));
    }
  }
  
  public Map getMap()
  {
    super.preForAll(methodObject23220, this, zeroLengthObjectArray);
    return (Map)postForAll(methodObject23220, (Object)this.delegate.getMap());
  }
  
  public Timestamp timestampValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23258, this, zeroLengthObjectArray);
      return (Timestamp)postForAll(methodObject23258, (Object)this.delegate.timestampValue());
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject23258, onErrorForAll(methodObject23258, e));
    }
  }
  
  public int intValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23245, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject23245, Integer.valueOf(this.delegate.intValue()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23245, onErrorForAll(methodObject23245, e))).intValue();
    }
  }
  
  public String stringValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23252, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject23252, (Object)this.delegate.stringValue());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject23252, onErrorForAll(methodObject23252, e));
    }
  }
  
  public Time timeValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23257, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject23257, (Object)this.delegate.timeValue(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject23257, onErrorForAll(methodObject23257, e));
    }
  }
  
  public InputStream asciiStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23261, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23261, (Object)this.delegate.asciiStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23261, onErrorForAll(methodObject23261, e));
    }
  }
  
  public void setAutoBuffering(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23229, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setAutoBuffering(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23229, e);
    }
  }
  
  public void setImage(byte[] arg0, long arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23231, this, new Object[] { arg0, Long.valueOf(arg1), Long.valueOf(arg2) });
      this.delegate.setImage(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23231, e);
    }
  }
  
  public byte[] shareBytes()
  {
    super.preForAll(methodObject23249, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject23249, (Object)this.delegate.shareBytes());
  }
  
  public void setPhysicalConnectionOf(Connection arg0)
  {
    super.preForAll(methodObject23265, this, new Object[] { arg0 });
    this.delegate.setPhysicalConnectionOf((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0);
  }
  
  public double doubleValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23243, this, zeroLengthObjectArray);
      return ((Double)postForAll(methodObject23243, Double.valueOf(this.delegate.doubleValue()))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject23243, onErrorForAll(methodObject23243, e))).doubleValue();
    }
  }
  
  public boolean isConvertibleTo(Class arg0)
  {
    super.preForAll(methodObject23222, this, new Object[] { arg0 });
    return ((Boolean)postForAll(methodObject23222, Boolean.valueOf(this.delegate.isConvertibleTo(arg0)))).booleanValue();
  }
  
  public void setDescriptor(StructDescriptor arg0)
  {
    super.preForAll(methodObject23236, this, new Object[] { arg0 });
    this.delegate.setDescriptor(arg0);
  }
  
  public void setImageLength(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23232, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.setImageLength(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23232, e);
    }
  }
  
  public long longValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23246, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject23246, Long.valueOf(this.delegate.longValue()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23246, onErrorForAll(methodObject23246, e))).longValue();
    }
  }
  
  public void setDatumArray(Datum[] arg0)
  {
    super.preForAll(methodObject23227, this, new Object[] { arg0 });
    this.delegate.setDatumArray(arg0);
  }
  
  public Object[] getAttributes(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23268, this, new Object[] { arg0 });
      return (Object[])postForAll(methodObject23268, (Object)this.delegate.getAttributes(arg0));
    }
    catch (SQLException e)
    {
      return (Object[])postForAll(methodObject23268, onErrorForAll(methodObject23268, e));
    }
  }
  
  public String stringValue(Connection arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23253, this, new Object[] { arg0 });
      return (String)postForAll(methodObject23253, (Object)this.delegate.stringValue((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject23253, onErrorForAll(methodObject23253, e));
    }
  }
  
  public StructDescriptor getDescriptor()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23217, this, zeroLengthObjectArray);
      return (StructDescriptor)postForAll(methodObject23217, (Object)this.delegate.getDescriptor());
    }
    catch (SQLException e)
    {
      return (StructDescriptor)postForAll(methodObject23217, onErrorForAll(methodObject23217, e));
    }
  }
  
  public oracle.jdbc.internal.OracleConnection getInternalConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23264, this, zeroLengthObjectArray);
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject23264, this.proxyFactory.proxyForCache((Object)this.delegate.getInternalConnection(), this, this.proxyCache, methodObject23264));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject23264, onErrorForAll(methodObject23264, e));
    }
  }
  
  public Object toJdbc(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23224, this, new Object[] { arg0 });
      return postForAll(methodObject23224, this.proxyFactory.proxyForCache(this.delegate.toJdbc(arg0), this, this.proxyCache, methodObject23224));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject23224, onErrorForAll(methodObject23224, e));
    }
  }
  
  public byte[] getBytes()
  {
    super.preForAll(methodObject23240, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject23240, (Object)this.delegate.getBytes());
  }
  
  public Object toClass(Class arg0, Map arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23219, this, new Object[] { arg0, arg1 });
      return postForAll(methodObject23219, this.proxyFactory.proxyForCache(this.delegate.toClass(arg0, arg1), this, this.proxyCache, methodObject23219));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject23219, onErrorForAll(methodObject23219, e));
    }
  }
  
  public boolean isInHierarchyOf(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23235, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject23235, Boolean.valueOf(this.delegate.isInHierarchyOf(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23235, onErrorForAll(methodObject23235, e))).booleanValue();
    }
  }
  
  public boolean getAutoBuffering()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23230, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23230, Boolean.valueOf(this.delegate.getAutoBuffering()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23230, onErrorForAll(methodObject23230, e))).booleanValue();
    }
  }
  
  public InputStream binaryStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23262, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23262, (Object)this.delegate.binaryStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23262, onErrorForAll(methodObject23262, e));
    }
  }
  
  public Datum[] getOracleAttributes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23237, this, zeroLengthObjectArray);
      return (Datum[])postForAll(methodObject23237, (Object)this.delegate.getOracleAttributes());
    }
    catch (SQLException e)
    {
      return (Datum[])postForAll(methodObject23237, onErrorForAll(methodObject23237, e));
    }
  }
  
  public Object makeJdbcArray(int arg0)
  {
    super.preForAll(methodObject23225, this, new Object[] { Integer.valueOf(arg0) });
    return postForAll(methodObject23225, this.proxyFactory.proxyForCache(this.delegate.makeJdbcArray(arg0), this, this.proxyCache, methodObject23225));
  }
  
  public void setShareBytes(byte[] arg0)
  {
    super.preForAll(methodObject23250, this, new Object[] { arg0 });
    this.delegate.setShareBytes(arg0);
  }
  
  public Object toClass(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23218, this, new Object[] { arg0 });
      return postForAll(methodObject23218, this.proxyFactory.proxyForCache(this.delegate.toClass(arg0), this, this.proxyCache, methodObject23218));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject23218, onErrorForAll(methodObject23218, e));
    }
  }
  
  public oracle.jdbc.OracleConnection getOracleConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23263, this, zeroLengthObjectArray);
      return (oracle.jdbc.OracleConnection)postForAll(methodObject23263, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleConnection(), this, this.proxyCache, methodObject23263));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.OracleConnection)postForAll(methodObject23263, onErrorForAll(methodObject23263, e));
    }
  }
  
  public Object toJdbc()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23223, this, zeroLengthObjectArray);
      return postForAll(methodObject23223, this.proxyFactory.proxyForCache(this.delegate.toJdbc(), this, this.proxyCache, methodObject23223));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject23223, onErrorForAll(methodObject23223, e));
    }
  }
  
  public ORADataFactory getORADataFactory(Hashtable arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23238, this, new Object[] { arg0, arg1 });
      return (ORADataFactory)postForAll(methodObject23238, (Object)this.delegate.getORADataFactory(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (ORADataFactory)postForAll(methodObject23238, onErrorForAll(methodObject23238, e));
    }
  }
  
  public Date dateValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23255, this, zeroLengthObjectArray);
      return (Date)postForAll(methodObject23255, (Object)this.delegate.dateValue());
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject23255, onErrorForAll(methodObject23255, e));
    }
  }
  
  public BigDecimal bigDecimalValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23254, this, zeroLengthObjectArray);
      return (BigDecimal)postForAll(methodObject23254, (Object)this.delegate.bigDecimalValue());
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject23254, onErrorForAll(methodObject23254, e));
    }
  }
  
  public void setBytes(byte[] arg0)
  {
    super.preForAll(methodObject23248, this, new Object[] { arg0 });
    this.delegate.setBytes(arg0);
  }
  
  public OracleTypeMetaData getOracleMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23266, this, zeroLengthObjectArray);
      return (OracleTypeMetaData)postForAll(methodObject23266, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleMetaData(), this, this.proxyCache, methodObject23266));
    }
    catch (SQLException e)
    {
      return (OracleTypeMetaData)postForAll(methodObject23266, onErrorForAll(methodObject23266, e));
    }
  }
  
  public byte byteValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23242, this, zeroLengthObjectArray);
      return ((Byte)postForAll(methodObject23242, Byte.valueOf(this.delegate.byteValue()))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject23242, onErrorForAll(methodObject23242, e))).byteValue();
    }
  }
  
  public float floatValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23244, this, zeroLengthObjectArray);
      return ((Float)postForAll(methodObject23244, Float.valueOf(this.delegate.floatValue()))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject23244, onErrorForAll(methodObject23244, e))).floatValue();
    }
  }
  
  public Reader characterStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23260, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject23260, (Object)this.delegate.characterStreamValue());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject23260, onErrorForAll(methodObject23260, e));
    }
  }
  
  public byte[] toBytes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23226, this, zeroLengthObjectArray);
      return (byte[])postForAll(methodObject23226, (Object)this.delegate.toBytes());
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject23226, onErrorForAll(methodObject23226, e));
    }
  }
  
  public InputStream getStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23251, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23251, (Object)this.delegate.getStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23251, onErrorForAll(methodObject23251, e));
    }
  }
  
  public long getLength()
  {
    super.preForAll(methodObject23239, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject23239, Long.valueOf(this.delegate.getLength()))).longValue();
  }
  
  public Time timeValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23256, this, zeroLengthObjectArray);
      return (Time)postForAll(methodObject23256, (Object)this.delegate.timeValue());
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject23256, onErrorForAll(methodObject23256, e));
    }
  }
  
  public oracle.jdbc.driver.OracleConnection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23247, this, zeroLengthObjectArray);
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject23247, (Object)this.delegate.getConnection());
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject23247, onErrorForAll(methodObject23247, e));
    }
  }
  
  public oracle.jdbc.internal.OracleStruct _getDelegate_()
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
      methodObject23228 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("setObjArray", new Class[] { Object[].class });
      methodObject23267 = Struct.class.getDeclaredMethod("getAttributes", new Class[0]);
      methodObject23234 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("getImageLength", new Class[0]);
      methodObject23221 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("getJavaSqlConnection", new Class[0]);
      methodObject23259 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[] { Calendar.class });
      methodObject23241 = OracleDatumWithConnection.class.getDeclaredMethod("booleanValue", new Class[0]);
      methodObject23233 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("getImageOffset", new Class[0]);
      methodObject23269 = Struct.class.getDeclaredMethod("getSQLTypeName", new Class[0]);
      methodObject23220 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("getMap", new Class[0]);
      methodObject23258 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[0]);
      methodObject23245 = OracleDatumWithConnection.class.getDeclaredMethod("intValue", new Class[0]);
      methodObject23252 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[0]);
      methodObject23257 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[] { Calendar.class });
      methodObject23261 = OracleDatumWithConnection.class.getDeclaredMethod("asciiStreamValue", new Class[0]);
      methodObject23229 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("setAutoBuffering", new Class[] { Boolean.TYPE });
      methodObject23231 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("setImage", new Class[] { byte[].class, Long.TYPE, Long.TYPE });
      methodObject23249 = OracleDatumWithConnection.class.getDeclaredMethod("shareBytes", new Class[0]);
      methodObject23265 = OracleDatumWithConnection.class.getDeclaredMethod("setPhysicalConnectionOf", new Class[] { Connection.class });
      methodObject23243 = OracleDatumWithConnection.class.getDeclaredMethod("doubleValue", new Class[0]);
      methodObject23222 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("isConvertibleTo", new Class[] { Class.class });
      methodObject23236 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("setDescriptor", new Class[] { StructDescriptor.class });
      methodObject23232 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("setImageLength", new Class[] { Long.TYPE });
      methodObject23246 = OracleDatumWithConnection.class.getDeclaredMethod("longValue", new Class[0]);
      methodObject23227 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("setDatumArray", new Class[] { Datum[].class });
      methodObject23268 = Struct.class.getDeclaredMethod("getAttributes", new Class[] { Map.class });
      methodObject23253 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[] { Connection.class });
      methodObject23217 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("getDescriptor", new Class[0]);
      methodObject23264 = OracleDatumWithConnection.class.getDeclaredMethod("getInternalConnection", new Class[0]);
      methodObject23224 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("toJdbc", new Class[] { Map.class });
      methodObject23240 = OracleDatumWithConnection.class.getDeclaredMethod("getBytes", new Class[0]);
      methodObject23219 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("toClass", new Class[] { Class.class, Map.class });
      methodObject23235 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("isInHierarchyOf", new Class[] { String.class });
      methodObject23230 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("getAutoBuffering", new Class[0]);
      methodObject23262 = OracleDatumWithConnection.class.getDeclaredMethod("binaryStreamValue", new Class[0]);
      methodObject23237 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("getOracleAttributes", new Class[0]);
      methodObject23225 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("makeJdbcArray", new Class[] { Integer.TYPE });
      methodObject23250 = OracleDatumWithConnection.class.getDeclaredMethod("setShareBytes", new Class[] { byte[].class });
      methodObject23218 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("toClass", new Class[] { Class.class });
      methodObject23263 = OracleDatumWithConnection.class.getDeclaredMethod("getOracleConnection", new Class[0]);
      methodObject23223 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("toJdbc", new Class[0]);
      methodObject23238 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("getORADataFactory", new Class[] { Hashtable.class, String.class });
      methodObject23255 = OracleDatumWithConnection.class.getDeclaredMethod("dateValue", new Class[0]);
      methodObject23254 = OracleDatumWithConnection.class.getDeclaredMethod("bigDecimalValue", new Class[0]);
      methodObject23248 = OracleDatumWithConnection.class.getDeclaredMethod("setBytes", new Class[] { byte[].class });
      methodObject23266 = oracle.jdbc.OracleStruct.class.getDeclaredMethod("getOracleMetaData", new Class[0]);
      methodObject23242 = OracleDatumWithConnection.class.getDeclaredMethod("byteValue", new Class[0]);
      methodObject23244 = OracleDatumWithConnection.class.getDeclaredMethod("floatValue", new Class[0]);
      methodObject23260 = OracleDatumWithConnection.class.getDeclaredMethod("characterStreamValue", new Class[0]);
      methodObject23226 = oracle.jdbc.internal.OracleStruct.class.getDeclaredMethod("toBytes", new Class[0]);
      methodObject23251 = OracleDatumWithConnection.class.getDeclaredMethod("getStream", new Class[0]);
      methodObject23239 = OracleDatumWithConnection.class.getDeclaredMethod("getLength", new Class[0]);
      methodObject23256 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[0]);
      methodObject23247 = OracleDatumWithConnection.class.getDeclaredMethod("getConnection", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableStruct$2oracle$1jdbc$1internal$1OracleStruct$$$Proxy(oracle.jdbc.internal.OracleStruct paramOracleStruct, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleStruct;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableStruct$2oracle$1jdbc$1internal$1OracleStruct$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */