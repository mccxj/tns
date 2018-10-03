package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.internal.OracleDatumWithConnection;
import oracle.jdbc.replay.driver.NonTxnReplayableArray;
import oracle.sql.ArrayDescriptor;
import oracle.sql.Datum;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableArray$2oracle$1jdbc$1internal$1OracleArray$$$Proxy
  extends NonTxnReplayableArray
  implements oracle.jdbc.internal.OracleArray, _Proxy_
{
  private oracle.jdbc.internal.OracleArray delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject12952;
  private static Method methodObject12884;
  private static Method methodObject12870;
  private static Method methodObject12892;
  private static Method methodObject12898;
  private static Method methodObject12875;
  private static Method methodObject12943;
  private static Method methodObject12920;
  private static Method methodObject12909;
  private static Method methodObject12940;
  private static Method methodObject12897;
  private static Method methodObject12901;
  private static Method methodObject12933;
  private static Method methodObject12929;
  private static Method methodObject12932;
  private static Method methodObject12871;
  private static Method methodObject12919;
  private static Method methodObject12947;
  private static Method methodObject12905;
  private static Method methodObject12934;
  private static Method methodObject12918;
  private static Method methodObject12913;
  private static Method methodObject12890;
  private static Method methodObject12888;
  private static Method methodObject12946;
  private static Method methodObject12922;
  private static Method methodObject12938;
  private static Method methodObject12885;
  private static Method methodObject12895;
  private static Method methodObject12910;
  private static Method methodObject12939;
  private static Method methodObject12927;
  private static Method methodObject12903;
  private static Method methodObject12872;
  private static Method methodObject12882;
  private static Method methodObject12894;
  private static Method methodObject12936;
  private static Method methodObject12891;
  private static Method methodObject12950;
  private static Method methodObject12896;
  private static Method methodObject12906;
  private static Method methodObject12877;
  private static Method methodObject12880;
  private static Method methodObject12878;
  private static Method methodObject12914;
  private static Method methodObject12928;
  private static Method methodObject12944;
  private static Method methodObject12926;
  private static Method methodObject12868;
  private static Method methodObject12874;
  private static Method methodObject12942;
  private static Method methodObject12881;
  private static Method methodObject12900;
  private static Method methodObject12893;
  private static Method methodObject12869;
  private static Method methodObject12941;
  private static Method methodObject12923;
  private static Method methodObject12886;
  private static Method methodObject12873;
  private static Method methodObject12911;
  private static Method methodObject12937;
  private static Method methodObject12925;
  private static Method methodObject12930;
  private static Method methodObject12948;
  private static Method methodObject12883;
  private static Method methodObject12935;
  private static Method methodObject12916;
  private static Method methodObject12879;
  private static Method methodObject12915;
  private static Method methodObject12908;
  private static Method methodObject12931;
  private static Method methodObject12951;
  private static Method methodObject12945;
  private static Method methodObject12887;
  private static Method methodObject12902;
  private static Method methodObject12921;
  private static Method methodObject12889;
  private static Method methodObject12904;
  private static Method methodObject12876;
  private static Method methodObject12912;
  private static Method methodObject12917;
  private static Method methodObject12899;
  private static Method methodObject12949;
  private static Method methodObject12907;
  
  public ResultSet getResultSet(long arg0, int arg1, Map arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12952, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      return (ResultSet)postForAll(methodObject12952, this.proxyFactory.proxyForCreate((Object)this.delegate.getResultSet(arg0, arg1, arg2), this, this.proxyCache, methodObject12952));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject12952, onErrorForAll(methodObject12952, e));
    }
  }
  
  public boolean isInline()
  {
    super.preForAll(methodObject12884, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject12884, Boolean.valueOf(this.delegate.isInline()))).booleanValue();
  }
  
  public long getOffset(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12870, this, new Object[] { Long.valueOf(arg0) });
      return ((Long)postForAll(methodObject12870, Long.valueOf(this.delegate.getOffset(arg0)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject12870, onErrorForAll(methodObject12870, e))).longValue();
    }
  }
  
  public void setIndexOffset(long arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12892, this, new Object[] { Long.valueOf(arg0), Long.valueOf(arg1) });
      this.delegate.setIndexOffset(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject12892, e);
    }
  }
  
  public long getImageLength()
  {
    super.preForAll(methodObject12898, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject12898, Long.valueOf(this.delegate.getImageLength()))).longValue();
  }
  
  public Datum[] getOracleArray(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12875, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (Datum[])postForAll(methodObject12875, (Object)this.delegate.getOracleArray(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Datum[])postForAll(methodObject12875, onErrorForAll(methodObject12875, e));
    }
  }
  
  public Object getArray(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12943, this, new Object[] { arg0 });
      return postForAll(methodObject12943, this.proxyFactory.proxyForCache(this.delegate.getArray(arg0), this, this.proxyCache, methodObject12943));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject12943, onErrorForAll(methodObject12943, e));
    }
  }
  
  public Timestamp timestampValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12920, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject12920, (Object)this.delegate.timestampValue(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject12920, onErrorForAll(methodObject12920, e));
    }
  }
  
  public Connection getJavaSqlConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12909, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject12909, this.proxyFactory.proxyForCache((Object)this.delegate.getJavaSqlConnection(), this, this.proxyCache, methodObject12909));
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject12909, onErrorForAll(methodObject12909, e));
    }
  }
  
  public float[] getFloatArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12940, this, zeroLengthObjectArray);
      return (float[])postForAll(methodObject12940, (Object)this.delegate.getFloatArray());
    }
    catch (SQLException e)
    {
      return (float[])postForAll(methodObject12940, onErrorForAll(methodObject12940, e));
    }
  }
  
  public long getImageOffset()
  {
    super.preForAll(methodObject12897, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject12897, Long.valueOf(this.delegate.getImageOffset()))).longValue();
  }
  
  public boolean booleanValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12901, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject12901, Boolean.valueOf(this.delegate.booleanValue()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject12901, onErrorForAll(methodObject12901, e))).booleanValue();
    }
  }
  
  public int[] getIntArray(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12933, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (int[])postForAll(methodObject12933, (Object)this.delegate.getIntArray(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (int[])postForAll(methodObject12933, onErrorForAll(methodObject12933, e));
    }
  }
  
  public String getSQLTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12929, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject12929, (Object)this.delegate.getSQLTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject12929, onErrorForAll(methodObject12929, e));
    }
  }
  
  public int[] getIntArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12932, this, zeroLengthObjectArray);
      return (int[])postForAll(methodObject12932, (Object)this.delegate.getIntArray());
    }
    catch (SQLException e)
    {
      return (int[])postForAll(methodObject12932, onErrorForAll(methodObject12932, e));
    }
  }
  
  public Map getMap()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12871, this, zeroLengthObjectArray);
      return (Map)postForAll(methodObject12871, (Object)this.delegate.getMap());
    }
    catch (SQLException e)
    {
      return (Map)postForAll(methodObject12871, onErrorForAll(methodObject12871, e));
    }
  }
  
  public Timestamp timestampValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12919, this, zeroLengthObjectArray);
      return (Timestamp)postForAll(methodObject12919, (Object)this.delegate.timestampValue());
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject12919, onErrorForAll(methodObject12919, e));
    }
  }
  
  public int getBaseType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12947, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject12947, Integer.valueOf(this.delegate.getBaseType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject12947, onErrorForAll(methodObject12947, e))).intValue();
    }
  }
  
  public int intValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12905, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject12905, Integer.valueOf(this.delegate.intValue()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject12905, onErrorForAll(methodObject12905, e))).intValue();
    }
  }
  
  public double[] getDoubleArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12934, this, zeroLengthObjectArray);
      return (double[])postForAll(methodObject12934, (Object)this.delegate.getDoubleArray());
    }
    catch (SQLException e)
    {
      return (double[])postForAll(methodObject12934, onErrorForAll(methodObject12934, e));
    }
  }
  
  public Time timeValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12918, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject12918, (Object)this.delegate.timeValue(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject12918, onErrorForAll(methodObject12918, e));
    }
  }
  
  public String stringValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12913, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject12913, (Object)this.delegate.stringValue());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject12913, onErrorForAll(methodObject12913, e));
    }
  }
  
  public int getAccessDirection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12890, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject12890, Integer.valueOf(this.delegate.getAccessDirection()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject12890, onErrorForAll(methodObject12890, e))).intValue();
    }
  }
  
  public void setAutoIndexing(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12888, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setAutoIndexing(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject12888, e);
    }
  }
  
  public void free()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12946, this, zeroLengthObjectArray);
      this.delegate.free();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject12946, e);
    }
  }
  
  public InputStream asciiStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12922, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject12922, (Object)this.delegate.asciiStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject12922, onErrorForAll(methodObject12922, e));
    }
  }
  
  public long[] getLongArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12938, this, zeroLengthObjectArray);
      return (long[])postForAll(methodObject12938, (Object)this.delegate.getLongArray());
    }
    catch (SQLException e)
    {
      return (long[])postForAll(methodObject12938, onErrorForAll(methodObject12938, e));
    }
  }
  
  public void setAutoBuffering(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12885, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setAutoBuffering(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject12885, e);
    }
  }
  
  public void setImage(byte[] arg0, long arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12895, this, new Object[] { arg0, Long.valueOf(arg1), Long.valueOf(arg2) });
      this.delegate.setImage(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject12895, e);
    }
  }
  
  public byte[] shareBytes()
  {
    super.preForAll(methodObject12910, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject12910, (Object)this.delegate.shareBytes());
  }
  
  public long[] getLongArray(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12939, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (long[])postForAll(methodObject12939, (Object)this.delegate.getLongArray(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (long[])postForAll(methodObject12939, onErrorForAll(methodObject12939, e));
    }
  }
  
  public void setPhysicalConnectionOf(Connection arg0)
  {
    super.preForAll(methodObject12927, this, new Object[] { arg0 });
    this.delegate.setPhysicalConnectionOf((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0);
  }
  
  public double doubleValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12903, this, zeroLengthObjectArray);
      return ((Double)postForAll(methodObject12903, Double.valueOf(this.delegate.doubleValue()))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject12903, onErrorForAll(methodObject12903, e))).doubleValue();
    }
  }
  
  public boolean isConvertibleTo(Class arg0)
  {
    super.preForAll(methodObject12872, this, new Object[] { arg0 });
    return ((Boolean)postForAll(methodObject12872, Boolean.valueOf(this.delegate.isConvertibleTo(arg0)))).booleanValue();
  }
  
  public byte[] getLocator()
  {
    super.preForAll(methodObject12882, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject12882, (Object)this.delegate.getLocator());
  }
  
  public long getLastOffset()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12894, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject12894, Long.valueOf(this.delegate.getLastOffset()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject12894, onErrorForAll(methodObject12894, e))).longValue();
    }
  }
  
  public short[] getShortArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12936, this, zeroLengthObjectArray);
      return (short[])postForAll(methodObject12936, (Object)this.delegate.getShortArray());
    }
    catch (SQLException e)
    {
      return (short[])postForAll(methodObject12936, onErrorForAll(methodObject12936, e));
    }
  }
  
  public void setLastIndexOffset(long arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12891, this, new Object[] { Long.valueOf(arg0), Long.valueOf(arg1) });
      this.delegate.setLastIndexOffset(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject12891, e);
    }
  }
  
  public ResultSet getResultSet(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12950, this, new Object[] { arg0 });
      return (ResultSet)postForAll(methodObject12950, this.proxyFactory.proxyForCreate((Object)this.delegate.getResultSet(arg0), this, this.proxyCache, methodObject12950));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject12950, onErrorForAll(methodObject12950, e));
    }
  }
  
  public void setImageLength(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12896, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.setImageLength(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject12896, e);
    }
  }
  
  public long longValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12906, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject12906, Long.valueOf(this.delegate.longValue()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject12906, onErrorForAll(methodObject12906, e))).longValue();
    }
  }
  
  public void setDatumArray(Datum[] arg0)
  {
    super.preForAll(methodObject12877, this, new Object[] { arg0 });
    this.delegate.setDatumArray(arg0);
  }
  
  public void setPrefixSegment(byte[] arg0)
  {
    super.preForAll(methodObject12880, this, new Object[] { arg0 });
    this.delegate.setPrefixSegment(arg0);
  }
  
  public void setObjArray(Object arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12878, this, new Object[] { arg0 });
      this.delegate.setObjArray((arg0 instanceof _Proxy_) ? (Object)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject12878, e);
    }
  }
  
  public String stringValue(Connection arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12914, this, new Object[] { arg0 });
      return (String)postForAll(methodObject12914, (Object)this.delegate.stringValue((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject12914, onErrorForAll(methodObject12914, e));
    }
  }
  
  public int length()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12928, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject12928, Integer.valueOf(this.delegate.length()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject12928, onErrorForAll(methodObject12928, e))).intValue();
    }
  }
  
  public Object getArray(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12944, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return postForAll(methodObject12944, this.proxyFactory.proxyForCache(this.delegate.getArray(arg0, arg1), this, this.proxyCache, methodObject12944));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject12944, onErrorForAll(methodObject12944, e));
    }
  }
  
  public oracle.jdbc.internal.OracleConnection getInternalConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12926, this, zeroLengthObjectArray);
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject12926, this.proxyFactory.proxyForCache((Object)this.delegate.getInternalConnection(), this, this.proxyCache, methodObject12926));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject12926, onErrorForAll(methodObject12926, e));
    }
  }
  
  public ArrayDescriptor getDescriptor()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12868, this, zeroLengthObjectArray);
      return (ArrayDescriptor)postForAll(methodObject12868, (Object)this.delegate.getDescriptor());
    }
    catch (SQLException e)
    {
      return (ArrayDescriptor)postForAll(methodObject12868, onErrorForAll(methodObject12868, e));
    }
  }
  
  public Datum[] getOracleArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12874, this, zeroLengthObjectArray);
      return (Datum[])postForAll(methodObject12874, (Object)this.delegate.getOracleArray());
    }
    catch (SQLException e)
    {
      return (Datum[])postForAll(methodObject12874, onErrorForAll(methodObject12874, e));
    }
  }
  
  public Object getArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12942, this, zeroLengthObjectArray);
      return postForAll(methodObject12942, this.proxyFactory.proxyForCache(this.delegate.getArray(), this, this.proxyCache, methodObject12942));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject12942, onErrorForAll(methodObject12942, e));
    }
  }
  
  public void setPrefixFlag(byte arg0)
  {
    super.preForAll(methodObject12881, this, new Object[] { Byte.valueOf(arg0) });
    this.delegate.setPrefixFlag(arg0);
  }
  
  public byte[] getBytes()
  {
    super.preForAll(methodObject12900, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject12900, (Object)this.delegate.getBytes());
  }
  
  public long getLastIndex()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12893, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject12893, Long.valueOf(this.delegate.getLastIndex()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject12893, onErrorForAll(methodObject12893, e))).longValue();
    }
  }
  
  public void setLength(int arg0)
  {
    super.preForAll(methodObject12869, this, new Object[] { Integer.valueOf(arg0) });
    this.delegate.setLength(arg0);
  }
  
  public float[] getFloatArray(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12941, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (float[])postForAll(methodObject12941, (Object)this.delegate.getFloatArray(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (float[])postForAll(methodObject12941, onErrorForAll(methodObject12941, e));
    }
  }
  
  public InputStream binaryStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12923, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject12923, (Object)this.delegate.binaryStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject12923, onErrorForAll(methodObject12923, e));
    }
  }
  
  public boolean getAutoBuffering()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12886, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject12886, Boolean.valueOf(this.delegate.getAutoBuffering()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject12886, onErrorForAll(methodObject12886, e))).booleanValue();
    }
  }
  
  public Object makeJdbcArray(int arg0)
  {
    super.preForAll(methodObject12873, this, new Object[] { Integer.valueOf(arg0) });
    return postForAll(methodObject12873, this.proxyFactory.proxyForCache(this.delegate.makeJdbcArray(arg0), this, this.proxyCache, methodObject12873));
  }
  
  public void setShareBytes(byte[] arg0)
  {
    super.preForAll(methodObject12911, this, new Object[] { arg0 });
    this.delegate.setShareBytes(arg0);
  }
  
  public short[] getShortArray(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12937, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (short[])postForAll(methodObject12937, (Object)this.delegate.getShortArray(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (short[])postForAll(methodObject12937, onErrorForAll(methodObject12937, e));
    }
  }
  
  public oracle.jdbc.OracleConnection getOracleConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12925, this, zeroLengthObjectArray);
      return (oracle.jdbc.OracleConnection)postForAll(methodObject12925, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleConnection(), this, this.proxyCache, methodObject12925));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.OracleConnection)postForAll(methodObject12925, onErrorForAll(methodObject12925, e));
    }
  }
  
  public Object toJdbc()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12930, this, zeroLengthObjectArray);
      return postForAll(methodObject12930, this.proxyFactory.proxyForCache(this.delegate.toJdbc(), this, this.proxyCache, methodObject12930));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject12930, onErrorForAll(methodObject12930, e));
    }
  }
  
  public String getBaseTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12948, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject12948, (Object)this.delegate.getBaseTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject12948, onErrorForAll(methodObject12948, e));
    }
  }
  
  public boolean hasDataSeg()
  {
    super.preForAll(methodObject12883, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject12883, Boolean.valueOf(this.delegate.hasDataSeg()))).booleanValue();
  }
  
  public double[] getDoubleArray(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12935, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (double[])postForAll(methodObject12935, (Object)this.delegate.getDoubleArray(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (double[])postForAll(methodObject12935, onErrorForAll(methodObject12935, e));
    }
  }
  
  public Date dateValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12916, this, zeroLengthObjectArray);
      return (Date)postForAll(methodObject12916, (Object)this.delegate.dateValue());
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject12916, onErrorForAll(methodObject12916, e));
    }
  }
  
  public void setLocator(byte[] arg0)
  {
    super.preForAll(methodObject12879, this, new Object[] { arg0 });
    this.delegate.setLocator(arg0);
  }
  
  public BigDecimal bigDecimalValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12915, this, zeroLengthObjectArray);
      return (BigDecimal)postForAll(methodObject12915, (Object)this.delegate.bigDecimalValue());
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject12915, onErrorForAll(methodObject12915, e));
    }
  }
  
  public void setBytes(byte[] arg0)
  {
    super.preForAll(methodObject12908, this, new Object[] { arg0 });
    this.delegate.setBytes(arg0);
  }
  
  public OracleTypeMetaData getOracleMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12931, this, zeroLengthObjectArray);
      return (OracleTypeMetaData)postForAll(methodObject12931, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleMetaData(), this, this.proxyCache, methodObject12931));
    }
    catch (SQLException e)
    {
      return (OracleTypeMetaData)postForAll(methodObject12931, onErrorForAll(methodObject12931, e));
    }
  }
  
  public ResultSet getResultSet(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12951, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (ResultSet)postForAll(methodObject12951, this.proxyFactory.proxyForCreate((Object)this.delegate.getResultSet(arg0, arg1), this, this.proxyCache, methodObject12951));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject12951, onErrorForAll(methodObject12951, e));
    }
  }
  
  public Object getArray(long arg0, int arg1, Map arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12945, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      return postForAll(methodObject12945, this.proxyFactory.proxyForCache(this.delegate.getArray(arg0, arg1, arg2), this, this.proxyCache, methodObject12945));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject12945, onErrorForAll(methodObject12945, e));
    }
  }
  
  public void setAutoIndexing(boolean arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12887, this, new Object[] { Boolean.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.setAutoIndexing(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject12887, e);
    }
  }
  
  public byte byteValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12902, this, zeroLengthObjectArray);
      return ((Byte)postForAll(methodObject12902, Byte.valueOf(this.delegate.byteValue()))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject12902, onErrorForAll(methodObject12902, e))).byteValue();
    }
  }
  
  public Reader characterStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12921, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject12921, (Object)this.delegate.characterStreamValue());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject12921, onErrorForAll(methodObject12921, e));
    }
  }
  
  public boolean getAutoIndexing()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12889, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject12889, Boolean.valueOf(this.delegate.getAutoIndexing()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject12889, onErrorForAll(methodObject12889, e))).booleanValue();
    }
  }
  
  public float floatValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12904, this, zeroLengthObjectArray);
      return ((Float)postForAll(methodObject12904, Float.valueOf(this.delegate.floatValue()))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject12904, onErrorForAll(methodObject12904, e))).floatValue();
    }
  }
  
  public byte[] toBytes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12876, this, zeroLengthObjectArray);
      return (byte[])postForAll(methodObject12876, (Object)this.delegate.toBytes());
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject12876, onErrorForAll(methodObject12876, e));
    }
  }
  
  public InputStream getStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12912, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject12912, (Object)this.delegate.getStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject12912, onErrorForAll(methodObject12912, e));
    }
  }
  
  public Time timeValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12917, this, zeroLengthObjectArray);
      return (Time)postForAll(methodObject12917, (Object)this.delegate.timeValue());
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject12917, onErrorForAll(methodObject12917, e));
    }
  }
  
  public long getLength()
  {
    super.preForAll(methodObject12899, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject12899, Long.valueOf(this.delegate.getLength()))).longValue();
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12949, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject12949, this.proxyFactory.proxyForCreate((Object)this.delegate.getResultSet(), this, this.proxyCache, methodObject12949));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject12949, onErrorForAll(methodObject12949, e));
    }
  }
  
  public oracle.jdbc.driver.OracleConnection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12907, this, zeroLengthObjectArray);
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject12907, (Object)this.delegate.getConnection());
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject12907, onErrorForAll(methodObject12907, e));
    }
  }
  
  public oracle.jdbc.internal.OracleArray _getDelegate_()
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
      methodObject12952 = Array.class.getDeclaredMethod("getResultSet", new Class[] { Long.TYPE, Integer.TYPE, Map.class });
      methodObject12884 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("isInline", new Class[0]);
      methodObject12870 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("getOffset", new Class[] { Long.TYPE });
      methodObject12892 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("setIndexOffset", new Class[] { Long.TYPE, Long.TYPE });
      methodObject12898 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("getImageLength", new Class[0]);
      methodObject12875 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("getOracleArray", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12943 = Array.class.getDeclaredMethod("getArray", new Class[] { Map.class });
      methodObject12920 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[] { Calendar.class });
      methodObject12909 = OracleDatumWithConnection.class.getDeclaredMethod("getJavaSqlConnection", new Class[0]);
      methodObject12940 = oracle.jdbc.OracleArray.class.getDeclaredMethod("getFloatArray", new Class[0]);
      methodObject12897 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("getImageOffset", new Class[0]);
      methodObject12901 = OracleDatumWithConnection.class.getDeclaredMethod("booleanValue", new Class[0]);
      methodObject12933 = oracle.jdbc.OracleArray.class.getDeclaredMethod("getIntArray", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12929 = oracle.jdbc.OracleArray.class.getDeclaredMethod("getSQLTypeName", new Class[0]);
      methodObject12932 = oracle.jdbc.OracleArray.class.getDeclaredMethod("getIntArray", new Class[0]);
      methodObject12871 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("getMap", new Class[0]);
      methodObject12919 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[0]);
      methodObject12947 = Array.class.getDeclaredMethod("getBaseType", new Class[0]);
      methodObject12905 = OracleDatumWithConnection.class.getDeclaredMethod("intValue", new Class[0]);
      methodObject12934 = oracle.jdbc.OracleArray.class.getDeclaredMethod("getDoubleArray", new Class[0]);
      methodObject12918 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[] { Calendar.class });
      methodObject12913 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[0]);
      methodObject12890 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("getAccessDirection", new Class[0]);
      methodObject12888 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("setAutoIndexing", new Class[] { Boolean.TYPE });
      methodObject12946 = Array.class.getDeclaredMethod("free", new Class[0]);
      methodObject12922 = OracleDatumWithConnection.class.getDeclaredMethod("asciiStreamValue", new Class[0]);
      methodObject12938 = oracle.jdbc.OracleArray.class.getDeclaredMethod("getLongArray", new Class[0]);
      methodObject12885 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("setAutoBuffering", new Class[] { Boolean.TYPE });
      methodObject12895 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("setImage", new Class[] { byte[].class, Long.TYPE, Long.TYPE });
      methodObject12910 = OracleDatumWithConnection.class.getDeclaredMethod("shareBytes", new Class[0]);
      methodObject12939 = oracle.jdbc.OracleArray.class.getDeclaredMethod("getLongArray", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12927 = OracleDatumWithConnection.class.getDeclaredMethod("setPhysicalConnectionOf", new Class[] { Connection.class });
      methodObject12903 = OracleDatumWithConnection.class.getDeclaredMethod("doubleValue", new Class[0]);
      methodObject12872 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("isConvertibleTo", new Class[] { Class.class });
      methodObject12882 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("getLocator", new Class[0]);
      methodObject12894 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("getLastOffset", new Class[0]);
      methodObject12936 = oracle.jdbc.OracleArray.class.getDeclaredMethod("getShortArray", new Class[0]);
      methodObject12891 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("setLastIndexOffset", new Class[] { Long.TYPE, Long.TYPE });
      methodObject12950 = Array.class.getDeclaredMethod("getResultSet", new Class[] { Map.class });
      methodObject12896 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("setImageLength", new Class[] { Long.TYPE });
      methodObject12906 = OracleDatumWithConnection.class.getDeclaredMethod("longValue", new Class[0]);
      methodObject12877 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("setDatumArray", new Class[] { Datum[].class });
      methodObject12880 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("setPrefixSegment", new Class[] { byte[].class });
      methodObject12878 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("setObjArray", new Class[] { Object.class });
      methodObject12914 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[] { Connection.class });
      methodObject12928 = oracle.jdbc.OracleArray.class.getDeclaredMethod("length", new Class[0]);
      methodObject12944 = Array.class.getDeclaredMethod("getArray", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12926 = OracleDatumWithConnection.class.getDeclaredMethod("getInternalConnection", new Class[0]);
      methodObject12868 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("getDescriptor", new Class[0]);
      methodObject12874 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("getOracleArray", new Class[0]);
      methodObject12942 = Array.class.getDeclaredMethod("getArray", new Class[0]);
      methodObject12881 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("setPrefixFlag", new Class[] { Byte.TYPE });
      methodObject12900 = OracleDatumWithConnection.class.getDeclaredMethod("getBytes", new Class[0]);
      methodObject12893 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("getLastIndex", new Class[0]);
      methodObject12869 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("setLength", new Class[] { Integer.TYPE });
      methodObject12941 = oracle.jdbc.OracleArray.class.getDeclaredMethod("getFloatArray", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12923 = OracleDatumWithConnection.class.getDeclaredMethod("binaryStreamValue", new Class[0]);
      methodObject12886 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("getAutoBuffering", new Class[0]);
      methodObject12873 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("makeJdbcArray", new Class[] { Integer.TYPE });
      methodObject12911 = OracleDatumWithConnection.class.getDeclaredMethod("setShareBytes", new Class[] { byte[].class });
      methodObject12937 = oracle.jdbc.OracleArray.class.getDeclaredMethod("getShortArray", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12925 = OracleDatumWithConnection.class.getDeclaredMethod("getOracleConnection", new Class[0]);
      methodObject12930 = oracle.jdbc.OracleArray.class.getDeclaredMethod("toJdbc", new Class[0]);
      methodObject12948 = Array.class.getDeclaredMethod("getBaseTypeName", new Class[0]);
      methodObject12883 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("hasDataSeg", new Class[0]);
      methodObject12935 = oracle.jdbc.OracleArray.class.getDeclaredMethod("getDoubleArray", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12916 = OracleDatumWithConnection.class.getDeclaredMethod("dateValue", new Class[0]);
      methodObject12879 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("setLocator", new Class[] { byte[].class });
      methodObject12915 = OracleDatumWithConnection.class.getDeclaredMethod("bigDecimalValue", new Class[0]);
      methodObject12908 = OracleDatumWithConnection.class.getDeclaredMethod("setBytes", new Class[] { byte[].class });
      methodObject12931 = oracle.jdbc.OracleArray.class.getDeclaredMethod("getOracleMetaData", new Class[0]);
      methodObject12951 = Array.class.getDeclaredMethod("getResultSet", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12945 = Array.class.getDeclaredMethod("getArray", new Class[] { Long.TYPE, Integer.TYPE, Map.class });
      methodObject12887 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("setAutoIndexing", new Class[] { Boolean.TYPE, Integer.TYPE });
      methodObject12902 = OracleDatumWithConnection.class.getDeclaredMethod("byteValue", new Class[0]);
      methodObject12921 = OracleDatumWithConnection.class.getDeclaredMethod("characterStreamValue", new Class[0]);
      methodObject12889 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("getAutoIndexing", new Class[0]);
      methodObject12904 = OracleDatumWithConnection.class.getDeclaredMethod("floatValue", new Class[0]);
      methodObject12876 = oracle.jdbc.internal.OracleArray.class.getDeclaredMethod("toBytes", new Class[0]);
      methodObject12912 = OracleDatumWithConnection.class.getDeclaredMethod("getStream", new Class[0]);
      methodObject12917 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[0]);
      methodObject12899 = OracleDatumWithConnection.class.getDeclaredMethod("getLength", new Class[0]);
      methodObject12949 = Array.class.getDeclaredMethod("getResultSet", new Class[0]);
      methodObject12907 = OracleDatumWithConnection.class.getDeclaredMethod("getConnection", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableArray$2oracle$1jdbc$1internal$1OracleArray$$$Proxy(oracle.jdbc.internal.OracleArray paramOracleArray, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleArray;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableArray$2oracle$1jdbc$1internal$1OracleArray$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */