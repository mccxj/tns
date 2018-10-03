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
import oracle.jdbc.LargeObjectAccessMode;
import oracle.jdbc.internal.OracleDatumWithConnection;
import oracle.jdbc.replay.driver.NonTxnReplayableBfile;
import oracle.sql.BFILE;
import oracle.sql.BfileDBAccess;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBfile$2oracle$1jdbc$1internal$1OracleBfile$$$Proxy
  extends NonTxnReplayableBfile
  implements oracle.jdbc.internal.OracleBfile, _Proxy_
{
  private oracle.jdbc.internal.OracleBfile delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject10990;
  private static Method methodObject10992;
  private static Method methodObject10984;
  private static Method methodObject10944;
  private static Method methodObject10974;
  private static Method methodObject10956;
  private static Method methodObject10979;
  private static Method methodObject10973;
  private static Method methodObject10960;
  private static Method methodObject10967;
  private static Method methodObject10972;
  private static Method methodObject10989;
  private static Method methodObject10946;
  private static Method methodObject10964;
  private static Method methodObject10977;
  private static Method methodObject10958;
  private static Method methodObject10991;
  private static Method methodObject10948;
  private static Method methodObject10953;
  private static Method methodObject10952;
  private static Method methodObject10961;
  private static Method methodObject10988;
  private static Method methodObject10942;
  private static Method methodObject10968;
  private static Method methodObject10981;
  private static Method methodObject10982;
  private static Method methodObject10976;
  private static Method methodObject10987;
  private static Method methodObject10955;
  private static Method methodObject10947;
  private static Method methodObject10950;
  private static Method methodObject10965;
  private static Method methodObject10983;
  private static Method methodObject10975;
  private static Method methodObject10949;
  private static Method methodObject10980;
  private static Method methodObject10986;
  private static Method methodObject10943;
  private static Method methodObject10970;
  private static Method methodObject10951;
  private static Method methodObject10969;
  private static Method methodObject10963;
  private static Method methodObject10993;
  private static Method methodObject10957;
  private static Method methodObject10959;
  private static Method methodObject10945;
  private static Method methodObject10966;
  private static Method methodObject10954;
  private static Method methodObject10971;
  private static Method methodObject10978;
  private static Method methodObject10985;
  private static Method methodObject10962;
  
  public void openFile()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10990, this, zeroLengthObjectArray);
      this.delegate.openFile();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject10990, e);
    }
  }
  
  public boolean fileExists()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10992, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject10992, Boolean.valueOf(this.delegate.fileExists()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject10992, onErrorForAll(methodObject10992, e))).booleanValue();
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10984, this, zeroLengthObjectArray);
      this.delegate.close();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject10984, e);
    }
  }
  
  public Connection getJavaSqlConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10944, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject10944, this.proxyFactory.proxyForCache((Object)this.delegate.getJavaSqlConnection(), this, this.proxyCache, methodObject10944));
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject10944, onErrorForAll(methodObject10944, e));
    }
  }
  
  public Timestamp timestampValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10974, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject10974, (Object)this.delegate.timestampValue(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject10974, onErrorForAll(methodObject10974, e));
    }
  }
  
  public boolean booleanValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10956, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject10956, Boolean.valueOf(this.delegate.booleanValue()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject10956, onErrorForAll(methodObject10956, e))).booleanValue();
    }
  }
  
  public byte[] getBytes(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10979, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (byte[])postForAll(methodObject10979, (Object)this.delegate.getBytes(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject10979, onErrorForAll(methodObject10979, e));
    }
  }
  
  public Timestamp timestampValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10973, this, zeroLengthObjectArray);
      return (Timestamp)postForAll(methodObject10973, (Object)this.delegate.timestampValue());
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject10973, onErrorForAll(methodObject10973, e));
    }
  }
  
  public int intValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10960, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject10960, Integer.valueOf(this.delegate.intValue()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject10960, onErrorForAll(methodObject10960, e))).intValue();
    }
  }
  
  public String stringValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10967, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject10967, (Object)this.delegate.stringValue());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject10967, onErrorForAll(methodObject10967, e));
    }
  }
  
  public Time timeValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10972, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject10972, (Object)this.delegate.timeValue(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject10972, onErrorForAll(methodObject10972, e));
    }
  }
  
  public String getDirAlias()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10989, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject10989, (Object)this.delegate.getDirAlias());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject10989, onErrorForAll(methodObject10989, e));
    }
  }
  
  public InputStream asciiStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10946, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject10946, (Object)this.delegate.asciiStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject10946, onErrorForAll(methodObject10946, e));
    }
  }
  
  public byte[] shareBytes()
  {
    super.preForAll(methodObject10964, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject10964, (Object)this.delegate.shareBytes());
  }
  
  public void setPhysicalConnectionOf(Connection arg0)
  {
    super.preForAll(methodObject10977, this, new Object[] { arg0 });
    this.delegate.setPhysicalConnectionOf((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0);
  }
  
  public double doubleValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10958, this, zeroLengthObjectArray);
      return ((Double)postForAll(methodObject10958, Double.valueOf(this.delegate.doubleValue()))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject10958, onErrorForAll(methodObject10958, e))).doubleValue();
    }
  }
  
  public boolean isFileOpen()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10991, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject10991, Boolean.valueOf(this.delegate.isFileOpen()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject10991, onErrorForAll(methodObject10991, e))).booleanValue();
    }
  }
  
  public boolean isConvertibleTo(Class arg0)
  {
    super.preForAll(methodObject10948, this, new Object[] { arg0 });
    return ((Boolean)postForAll(methodObject10948, Boolean.valueOf(this.delegate.isConvertibleTo(arg0)))).booleanValue();
  }
  
  public BfileDBAccess getDBAccess()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10953, this, zeroLengthObjectArray);
      return (BfileDBAccess)postForAll(methodObject10953, (Object)this.delegate.getDBAccess());
    }
    catch (SQLException e)
    {
      return (BfileDBAccess)postForAll(methodObject10953, onErrorForAll(methodObject10953, e));
    }
  }
  
  public byte[] getLocator()
  {
    super.preForAll(methodObject10952, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject10952, (Object)this.delegate.getLocator());
  }
  
  public long longValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10961, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject10961, Long.valueOf(this.delegate.longValue()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject10961, onErrorForAll(methodObject10961, e))).longValue();
    }
  }
  
  public InputStream getBinaryStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10988, this, new Object[] { Long.valueOf(arg0) });
      return (InputStream)postForAll(methodObject10988, (Object)this.delegate.getBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject10988, onErrorForAll(methodObject10988, e));
    }
  }
  
  public long position(BFILE arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10942, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject10942, Long.valueOf(this.delegate.position(arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject10942, onErrorForAll(methodObject10942, e))).longValue();
    }
  }
  
  public String stringValue(Connection arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10968, this, new Object[] { arg0 });
      return (String)postForAll(methodObject10968, (Object)this.delegate.stringValue((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject10968, onErrorForAll(methodObject10968, e));
    }
  }
  
  public long length()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10981, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject10981, Long.valueOf(this.delegate.length()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject10981, onErrorForAll(methodObject10981, e))).longValue();
    }
  }
  
  public long position(byte[] arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10982, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject10982, Long.valueOf(this.delegate.position(arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject10982, onErrorForAll(methodObject10982, e))).longValue();
    }
  }
  
  public oracle.jdbc.internal.OracleConnection getInternalConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10976, this, zeroLengthObjectArray);
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject10976, this.proxyFactory.proxyForCache((Object)this.delegate.getInternalConnection(), this, this.proxyCache, methodObject10976));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject10976, onErrorForAll(methodObject10976, e));
    }
  }
  
  public InputStream getBinaryStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10987, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject10987, (Object)this.delegate.getBinaryStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject10987, onErrorForAll(methodObject10987, e));
    }
  }
  
  public byte[] getBytes()
  {
    super.preForAll(methodObject10955, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject10955, (Object)this.delegate.getBytes());
  }
  
  public InputStream binaryStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10947, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject10947, (Object)this.delegate.binaryStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject10947, onErrorForAll(methodObject10947, e));
    }
  }
  
  public Object makeJdbcArray(int arg0)
  {
    super.preForAll(methodObject10950, this, new Object[] { Integer.valueOf(arg0) });
    return postForAll(methodObject10950, this.proxyFactory.proxyForCache(this.delegate.makeJdbcArray(arg0), this, this.proxyCache, methodObject10950));
  }
  
  public void setShareBytes(byte[] arg0)
  {
    super.preForAll(methodObject10965, this, new Object[] { arg0 });
    this.delegate.setShareBytes(arg0);
  }
  
  public long position(oracle.jdbc.OracleBfile arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10983, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject10983, Long.valueOf(this.delegate.position((arg0 instanceof _Proxy_) ? (oracle.jdbc.OracleBfile)((_Proxy_)arg0)._getDelegate_() : arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject10983, onErrorForAll(methodObject10983, e))).longValue();
    }
  }
  
  public oracle.jdbc.OracleConnection getOracleConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10975, this, zeroLengthObjectArray);
      return (oracle.jdbc.OracleConnection)postForAll(methodObject10975, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleConnection(), this, this.proxyCache, methodObject10975));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.OracleConnection)postForAll(methodObject10975, onErrorForAll(methodObject10975, e));
    }
  }
  
  public Object toJdbc()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10949, this, zeroLengthObjectArray);
      return postForAll(methodObject10949, this.proxyFactory.proxyForCache(this.delegate.toJdbc(), this, this.proxyCache, methodObject10949));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject10949, onErrorForAll(methodObject10949, e));
    }
  }
  
  public int getBytes(long arg0, int arg1, byte[] arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10980, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      return ((Integer)postForAll(methodObject10980, Integer.valueOf(this.delegate.getBytes(arg0, arg1, arg2)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject10980, onErrorForAll(methodObject10980, e))).intValue();
    }
  }
  
  public boolean isOpen()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10986, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject10986, Boolean.valueOf(this.delegate.isOpen()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject10986, onErrorForAll(methodObject10986, e))).booleanValue();
    }
  }
  
  public void setLength(long arg0)
  {
    super.preForAll(methodObject10943, this, new Object[] { Long.valueOf(arg0) });
    this.delegate.setLength(arg0);
  }
  
  public Date dateValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10970, this, zeroLengthObjectArray);
      return (Date)postForAll(methodObject10970, (Object)this.delegate.dateValue());
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject10970, onErrorForAll(methodObject10970, e));
    }
  }
  
  public void setLocator(byte[] arg0)
  {
    super.preForAll(methodObject10951, this, new Object[] { arg0 });
    this.delegate.setLocator(arg0);
  }
  
  public BigDecimal bigDecimalValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10969, this, zeroLengthObjectArray);
      return (BigDecimal)postForAll(methodObject10969, (Object)this.delegate.bigDecimalValue());
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject10969, onErrorForAll(methodObject10969, e));
    }
  }
  
  public void setBytes(byte[] arg0)
  {
    super.preForAll(methodObject10963, this, new Object[] { arg0 });
    this.delegate.setBytes(arg0);
  }
  
  public void closeFile()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10993, this, zeroLengthObjectArray);
      this.delegate.closeFile();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject10993, e);
    }
  }
  
  public byte byteValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10957, this, zeroLengthObjectArray);
      return ((Byte)postForAll(methodObject10957, Byte.valueOf(this.delegate.byteValue()))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject10957, onErrorForAll(methodObject10957, e))).byteValue();
    }
  }
  
  public float floatValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10959, this, zeroLengthObjectArray);
      return ((Float)postForAll(methodObject10959, Float.valueOf(this.delegate.floatValue()))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject10959, onErrorForAll(methodObject10959, e))).floatValue();
    }
  }
  
  public Reader characterStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10945, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject10945, (Object)this.delegate.characterStreamValue());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject10945, onErrorForAll(methodObject10945, e));
    }
  }
  
  public InputStream getStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10966, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject10966, (Object)this.delegate.getStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject10966, onErrorForAll(methodObject10966, e));
    }
  }
  
  public long getLength()
  {
    super.preForAll(methodObject10954, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject10954, Long.valueOf(this.delegate.getLength()))).longValue();
  }
  
  public Time timeValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10971, this, zeroLengthObjectArray);
      return (Time)postForAll(methodObject10971, (Object)this.delegate.timeValue());
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject10971, onErrorForAll(methodObject10971, e));
    }
  }
  
  public String getName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10978, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject10978, (Object)this.delegate.getName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject10978, onErrorForAll(methodObject10978, e));
    }
  }
  
  public void open(LargeObjectAccessMode arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10985, this, new Object[] { arg0 });
      this.delegate.open(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject10985, e);
    }
  }
  
  public oracle.jdbc.driver.OracleConnection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10962, this, zeroLengthObjectArray);
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject10962, (Object)this.delegate.getConnection());
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject10962, onErrorForAll(methodObject10962, e));
    }
  }
  
  public oracle.jdbc.internal.OracleBfile _getDelegate_()
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
      methodObject10990 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("openFile", new Class[0]);
      methodObject10992 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("fileExists", new Class[0]);
      methodObject10984 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("close", new Class[0]);
      methodObject10944 = oracle.jdbc.internal.OracleBfile.class.getDeclaredMethod("getJavaSqlConnection", new Class[0]);
      methodObject10974 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[] { Calendar.class });
      methodObject10956 = OracleDatumWithConnection.class.getDeclaredMethod("booleanValue", new Class[0]);
      methodObject10979 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("getBytes", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject10973 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[0]);
      methodObject10960 = OracleDatumWithConnection.class.getDeclaredMethod("intValue", new Class[0]);
      methodObject10967 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[0]);
      methodObject10972 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[] { Calendar.class });
      methodObject10989 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("getDirAlias", new Class[0]);
      methodObject10946 = oracle.jdbc.internal.OracleBfile.class.getDeclaredMethod("asciiStreamValue", new Class[0]);
      methodObject10964 = OracleDatumWithConnection.class.getDeclaredMethod("shareBytes", new Class[0]);
      methodObject10977 = OracleDatumWithConnection.class.getDeclaredMethod("setPhysicalConnectionOf", new Class[] { Connection.class });
      methodObject10958 = OracleDatumWithConnection.class.getDeclaredMethod("doubleValue", new Class[0]);
      methodObject10991 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("isFileOpen", new Class[0]);
      methodObject10948 = oracle.jdbc.internal.OracleBfile.class.getDeclaredMethod("isConvertibleTo", new Class[] { Class.class });
      methodObject10953 = oracle.jdbc.internal.OracleBfile.class.getDeclaredMethod("getDBAccess", new Class[0]);
      methodObject10952 = oracle.jdbc.internal.OracleBfile.class.getDeclaredMethod("getLocator", new Class[0]);
      methodObject10961 = OracleDatumWithConnection.class.getDeclaredMethod("longValue", new Class[0]);
      methodObject10988 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("getBinaryStream", new Class[] { Long.TYPE });
      methodObject10942 = oracle.jdbc.internal.OracleBfile.class.getDeclaredMethod("position", new Class[] { BFILE.class, Long.TYPE });
      methodObject10968 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[] { Connection.class });
      methodObject10981 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("length", new Class[0]);
      methodObject10982 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("position", new Class[] { byte[].class, Long.TYPE });
      methodObject10976 = OracleDatumWithConnection.class.getDeclaredMethod("getInternalConnection", new Class[0]);
      methodObject10987 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("getBinaryStream", new Class[0]);
      methodObject10955 = OracleDatumWithConnection.class.getDeclaredMethod("getBytes", new Class[0]);
      methodObject10947 = oracle.jdbc.internal.OracleBfile.class.getDeclaredMethod("binaryStreamValue", new Class[0]);
      methodObject10950 = oracle.jdbc.internal.OracleBfile.class.getDeclaredMethod("makeJdbcArray", new Class[] { Integer.TYPE });
      methodObject10965 = OracleDatumWithConnection.class.getDeclaredMethod("setShareBytes", new Class[] { byte[].class });
      methodObject10983 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("position", new Class[] { oracle.jdbc.OracleBfile.class, Long.TYPE });
      methodObject10975 = OracleDatumWithConnection.class.getDeclaredMethod("getOracleConnection", new Class[0]);
      methodObject10949 = oracle.jdbc.internal.OracleBfile.class.getDeclaredMethod("toJdbc", new Class[0]);
      methodObject10980 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("getBytes", new Class[] { Long.TYPE, Integer.TYPE, byte[].class });
      methodObject10986 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("isOpen", new Class[0]);
      methodObject10943 = oracle.jdbc.internal.OracleBfile.class.getDeclaredMethod("setLength", new Class[] { Long.TYPE });
      methodObject10970 = OracleDatumWithConnection.class.getDeclaredMethod("dateValue", new Class[0]);
      methodObject10951 = oracle.jdbc.internal.OracleBfile.class.getDeclaredMethod("setLocator", new Class[] { byte[].class });
      methodObject10969 = OracleDatumWithConnection.class.getDeclaredMethod("bigDecimalValue", new Class[0]);
      methodObject10963 = OracleDatumWithConnection.class.getDeclaredMethod("setBytes", new Class[] { byte[].class });
      methodObject10993 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("closeFile", new Class[0]);
      methodObject10957 = OracleDatumWithConnection.class.getDeclaredMethod("byteValue", new Class[0]);
      methodObject10959 = OracleDatumWithConnection.class.getDeclaredMethod("floatValue", new Class[0]);
      methodObject10945 = oracle.jdbc.internal.OracleBfile.class.getDeclaredMethod("characterStreamValue", new Class[0]);
      methodObject10966 = OracleDatumWithConnection.class.getDeclaredMethod("getStream", new Class[0]);
      methodObject10954 = OracleDatumWithConnection.class.getDeclaredMethod("getLength", new Class[0]);
      methodObject10971 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[0]);
      methodObject10978 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("getName", new Class[0]);
      methodObject10985 = oracle.jdbc.OracleBfile.class.getDeclaredMethod("open", new Class[] { LargeObjectAccessMode.class });
      methodObject10962 = OracleDatumWithConnection.class.getDeclaredMethod("getConnection", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBfile$2oracle$1jdbc$1internal$1OracleBfile$$$Proxy(oracle.jdbc.internal.OracleBfile paramOracleBfile, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleBfile;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBfile$2oracle$1jdbc$1internal$1OracleBfile$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */