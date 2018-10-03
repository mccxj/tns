package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import oracle.jdbc.LargeObjectAccessMode;
import oracle.jdbc.internal.OracleDatumWithConnection;
import oracle.jdbc.internal.OracleNClob;
import oracle.jdbc.replay.driver.NonTxnReplayableNClob;
import oracle.sql.ClobDBAccess;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableNClob$2oracle$1jdbc$1internal$1OracleNClob$$$Proxy
  extends NonTxnReplayableNClob
  implements OracleNClob, _Proxy_
{
  private OracleNClob delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject23967;
  private static Method methodObject23983;
  private static Method methodObject23945;
  private static Method methodObject23978;
  private static Method methodObject23921;
  private static Method methodObject23947;
  private static Method methodObject23902;
  private static Method methodObject23944;
  private static Method methodObject23981;
  private static Method methodObject23975;
  private static Method methodObject23964;
  private static Method methodObject23920;
  private static Method methodObject23936;
  private static Method methodObject23962;
  private static Method methodObject23906;
  private static Method methodObject23919;
  private static Method methodObject23949;
  private static Method methodObject23935;
  private static Method methodObject23982;
  private static Method methodObject23934;
  private static Method methodObject23951;
  private static Method methodObject23911;
  private static Method methodObject23930;
  private static Method methodObject23904;
  private static Method methodObject23961;
  private static Method methodObject23953;
  private static Method methodObject23958;
  private static Method methodObject23968;
  private static Method methodObject23971;
  private static Method methodObject23932;
  private static Method methodObject23907;
  private static Method methodObject23956;
  private static Method methodObject23973;
  private static Method methodObject23965;
  private static Method methodObject23915;
  private static Method methodObject23931;
  private static Method methodObject23929;
  private static Method methodObject23977;
  private static Method methodObject23940;
  private static Method methodObject23901;
  private static Method methodObject23974;
  private static Method methodObject23966;
  private static Method methodObject23952;
  private static Method methodObject23955;
  private static Method methodObject23912;
  private static Method methodObject23928;
  private static Method methodObject23954;
  private static Method methodObject23942;
  private static Method methodObject23960;
  private static Method methodObject23980;
  private static Method methodObject23946;
  private static Method methodObject23917;
  private static Method methodObject23959;
  private static Method methodObject23957;
  private static Method methodObject23916;
  private static Method methodObject23943;
  private static Method methodObject23909;
  private static Method methodObject23941;
  private static Method methodObject23963;
  private static Method methodObject23948;
  private static Method methodObject23969;
  private static Method methodObject23939;
  private static Method methodObject23970;
  private static Method methodObject23937;
  private static Method methodObject23938;
  private static Method methodObject23976;
  private static Method methodObject23903;
  private static Method methodObject23972;
  private static Method methodObject23950;
  private static Method methodObject23905;
  private static Method methodObject23913;
  private static Method methodObject23979;
  private static Method methodObject23918;
  private static Method methodObject23900;
  private static Method methodObject23933;
  private static Method methodObject23908;
  
  public void setActivePrefetch(boolean arg0)
  {
    super.preForAll(methodObject23967, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setActivePrefetch(arg0);
  }
  
  public boolean isTemporary()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23983, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23983, Boolean.valueOf(this.delegate.isTemporary()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23983, onErrorForAll(methodObject23983, e))).booleanValue();
    }
  }
  
  public void trim(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23945, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.trim(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23945, e);
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23978, this, zeroLengthObjectArray);
      this.delegate.close();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23978, e);
    }
  }
  
  public Timestamp timestampValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23921, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject23921, (Object)this.delegate.timestampValue(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject23921, onErrorForAll(methodObject23921, e));
    }
  }
  
  public Connection getJavaSqlConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23947, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject23947, this.proxyFactory.proxyForCache((Object)this.delegate.getJavaSqlConnection(), this, this.proxyCache, methodObject23947));
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject23947, onErrorForAll(methodObject23947, e));
    }
  }
  
  public boolean booleanValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23902, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23902, Boolean.valueOf(this.delegate.booleanValue()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23902, onErrorForAll(methodObject23902, e))).booleanValue();
    }
  }
  
  public int getChars(long arg0, int arg1, char[] arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23944, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      return ((Integer)postForAll(methodObject23944, Integer.valueOf(this.delegate.getChars(arg0, arg1, arg2)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23944, onErrorForAll(methodObject23944, e))).intValue();
    }
  }
  
  public boolean isEmptyLob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23981, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23981, Boolean.valueOf(this.delegate.isEmptyLob()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23981, onErrorForAll(methodObject23981, e))).booleanValue();
    }
  }
  
  public int putChars(long arg0, char[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23975, this, new Object[] { Long.valueOf(arg0), arg1 });
      return ((Integer)postForAll(methodObject23975, Integer.valueOf(this.delegate.putChars(arg0, arg1)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23975, onErrorForAll(methodObject23975, e))).intValue();
    }
  }
  
  public void setPrefetchedData(char[] arg0, int arg1)
  {
    super.preForAll(methodObject23964, this, new Object[] { arg0, Integer.valueOf(arg1) });
    this.delegate.setPrefetchedData(arg0, arg1);
  }
  
  public Timestamp timestampValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23920, this, zeroLengthObjectArray);
      return (Timestamp)postForAll(methodObject23920, (Object)this.delegate.timestampValue());
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject23920, onErrorForAll(methodObject23920, e));
    }
  }
  
  public Writer setCharacterStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23936, this, new Object[] { Long.valueOf(arg0) });
      return (Writer)postForAll(methodObject23936, (Object)this.delegate.setCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Writer)postForAll(methodObject23936, onErrorForAll(methodObject23936, e));
    }
  }
  
  public void setChunkSize(int arg0)
  {
    super.preForAll(methodObject23962, this, new Object[] { Integer.valueOf(arg0) });
    this.delegate.setChunkSize(arg0);
  }
  
  public int intValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23906, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject23906, Integer.valueOf(this.delegate.intValue()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23906, onErrorForAll(methodObject23906, e))).intValue();
    }
  }
  
  public Time timeValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23919, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject23919, (Object)this.delegate.timeValue(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject23919, onErrorForAll(methodObject23919, e));
    }
  }
  
  public String stringValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23949, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject23949, (Object)this.delegate.stringValue());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject23949, onErrorForAll(methodObject23949, e));
    }
  }
  
  public OutputStream setAsciiStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23935, this, new Object[] { Long.valueOf(arg0) });
      return (OutputStream)postForAll(methodObject23935, (Object)this.delegate.setAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject23935, onErrorForAll(methodObject23935, e));
    }
  }
  
  public boolean isSecureFile()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23982, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23982, Boolean.valueOf(this.delegate.isSecureFile()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23982, onErrorForAll(methodObject23982, e))).booleanValue();
    }
  }
  
  public void free()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23934, this, zeroLengthObjectArray);
      this.delegate.free();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23934, e);
    }
  }
  
  public InputStream asciiStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23951, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23951, (Object)this.delegate.asciiStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23951, onErrorForAll(methodObject23951, e));
    }
  }
  
  public byte[] shareBytes()
  {
    super.preForAll(methodObject23911, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject23911, (Object)this.delegate.shareBytes());
  }
  
  public void setPhysicalConnectionOf(Connection arg0)
  {
    super.preForAll(methodObject23930, this, new Object[] { arg0 });
    this.delegate.setPhysicalConnectionOf((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0);
  }
  
  public double doubleValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23904, this, zeroLengthObjectArray);
      return ((Double)postForAll(methodObject23904, Double.valueOf(this.delegate.doubleValue()))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject23904, onErrorForAll(methodObject23904, e))).doubleValue();
    }
  }
  
  public ClobDBAccess getDBAccess()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23961, this, zeroLengthObjectArray);
      return (ClobDBAccess)postForAll(methodObject23961, (Object)this.delegate.getDBAccess());
    }
    catch (SQLException e)
    {
      return (ClobDBAccess)postForAll(methodObject23961, onErrorForAll(methodObject23961, e));
    }
  }
  
  public boolean isConvertibleTo(Class arg0)
  {
    super.preForAll(methodObject23953, this, new Object[] { arg0 });
    return ((Boolean)postForAll(methodObject23953, Boolean.valueOf(this.delegate.isConvertibleTo(arg0)))).booleanValue();
  }
  
  public byte[] getLocator()
  {
    super.preForAll(methodObject23958, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject23958, (Object)this.delegate.getLocator());
  }
  
  public void clearCachedData()
  {
    super.preForAll(methodObject23968, this, zeroLengthObjectArray);
    this.delegate.clearCachedData();
  }
  
  public Writer getCharacterOutputStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23971, this, new Object[] { Long.valueOf(arg0) });
      return (Writer)postForAll(methodObject23971, (Object)this.delegate.getCharacterOutputStream(arg0));
    }
    catch (SQLException e)
    {
      return (Writer)postForAll(methodObject23971, onErrorForAll(methodObject23971, e));
    }
  }
  
  public long position(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23932, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject23932, Long.valueOf(this.delegate.position(arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23932, onErrorForAll(methodObject23932, e))).longValue();
    }
  }
  
  public long longValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23907, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject23907, Long.valueOf(this.delegate.longValue()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23907, onErrorForAll(methodObject23907, e))).longValue();
    }
  }
  
  public InputStream getAsciiStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23956, this, new Object[] { Long.valueOf(arg0) });
      return (InputStream)postForAll(methodObject23956, (Object)this.delegate.getAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23956, onErrorForAll(methodObject23956, e));
    }
  }
  
  public OutputStream getAsciiOutputStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23973, this, new Object[] { Long.valueOf(arg0) });
      return (OutputStream)postForAll(methodObject23973, (Object)this.delegate.getAsciiOutputStream(arg0));
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject23973, onErrorForAll(methodObject23973, e));
    }
  }
  
  public char[] getPrefetchedData()
  {
    super.preForAll(methodObject23965, this, zeroLengthObjectArray);
    return (char[])postForAll(methodObject23965, (Object)this.delegate.getPrefetchedData());
  }
  
  public String stringValue(Connection arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23915, this, new Object[] { arg0 });
      return (String)postForAll(methodObject23915, (Object)this.delegate.stringValue((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject23915, onErrorForAll(methodObject23915, e));
    }
  }
  
  public long length()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23931, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject23931, Long.valueOf(this.delegate.length()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23931, onErrorForAll(methodObject23931, e))).longValue();
    }
  }
  
  public oracle.jdbc.internal.OracleConnection getInternalConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23929, this, zeroLengthObjectArray);
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject23929, this.proxyFactory.proxyForCache((Object)this.delegate.getInternalConnection(), this, this.proxyCache, methodObject23929));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject23929, onErrorForAll(methodObject23929, e));
    }
  }
  
  public int putString(long arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23977, this, new Object[] { Long.valueOf(arg0), arg1 });
      return ((Integer)postForAll(methodObject23977, Integer.valueOf(this.delegate.putString(arg0, arg1)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23977, onErrorForAll(methodObject23977, e))).intValue();
    }
  }
  
  public Reader getCharacterStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23940, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject23940, (Object)this.delegate.getCharacterStream());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject23940, onErrorForAll(methodObject23940, e));
    }
  }
  
  public byte[] getBytes()
  {
    super.preForAll(methodObject23901, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject23901, (Object)this.delegate.getBytes());
  }
  
  public OutputStream getAsciiOutputStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23974, this, zeroLengthObjectArray);
      return (OutputStream)postForAll(methodObject23974, (Object)this.delegate.getAsciiOutputStream());
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject23974, onErrorForAll(methodObject23974, e));
    }
  }
  
  public int getPrefetchedDataSize()
  {
    super.preForAll(methodObject23966, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject23966, Integer.valueOf(this.delegate.getPrefetchedDataSize()))).intValue();
  }
  
  public InputStream binaryStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23952, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23952, (Object)this.delegate.binaryStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23952, onErrorForAll(methodObject23952, e));
    }
  }
  
  public Object makeJdbcArray(int arg0)
  {
    super.preForAll(methodObject23955, this, new Object[] { Integer.valueOf(arg0) });
    return postForAll(methodObject23955, this.proxyFactory.proxyForCache(this.delegate.makeJdbcArray(arg0), this, this.proxyCache, methodObject23955));
  }
  
  public void setShareBytes(byte[] arg0)
  {
    super.preForAll(methodObject23912, this, new Object[] { arg0 });
    this.delegate.setShareBytes(arg0);
  }
  
  public oracle.jdbc.OracleConnection getOracleConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23928, this, zeroLengthObjectArray);
      return (oracle.jdbc.OracleConnection)postForAll(methodObject23928, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleConnection(), this, this.proxyCache, methodObject23928));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.OracleConnection)postForAll(methodObject23928, onErrorForAll(methodObject23928, e));
    }
  }
  
  public Object toJdbc()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23954, this, zeroLengthObjectArray);
      return postForAll(methodObject23954, this.proxyFactory.proxyForCache(this.delegate.toJdbc(), this, this.proxyCache, methodObject23954));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject23954, onErrorForAll(methodObject23954, e));
    }
  }
  
  public InputStream getAsciiStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23942, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23942, (Object)this.delegate.getAsciiStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23942, onErrorForAll(methodObject23942, e));
    }
  }
  
  public int getBufferSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23960, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject23960, Integer.valueOf(this.delegate.getBufferSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23960, onErrorForAll(methodObject23960, e))).intValue();
    }
  }
  
  public boolean isOpen()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23980, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23980, Boolean.valueOf(this.delegate.isOpen()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23980, onErrorForAll(methodObject23980, e))).booleanValue();
    }
  }
  
  public void setLength(long arg0)
  {
    super.preForAll(methodObject23946, this, new Object[] { Long.valueOf(arg0) });
    this.delegate.setLength(arg0);
  }
  
  public Date dateValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23917, this, zeroLengthObjectArray);
      return (Date)postForAll(methodObject23917, (Object)this.delegate.dateValue());
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject23917, onErrorForAll(methodObject23917, e));
    }
  }
  
  public int getChunkSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23959, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject23959, Integer.valueOf(this.delegate.getChunkSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23959, onErrorForAll(methodObject23959, e))).intValue();
    }
  }
  
  public void setLocator(byte[] arg0)
  {
    super.preForAll(methodObject23957, this, new Object[] { arg0 });
    this.delegate.setLocator(arg0);
  }
  
  public BigDecimal bigDecimalValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23916, this, zeroLengthObjectArray);
      return (BigDecimal)postForAll(methodObject23916, (Object)this.delegate.bigDecimalValue());
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject23916, onErrorForAll(methodObject23916, e));
    }
  }
  
  public String getSubString(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23943, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (String)postForAll(methodObject23943, (Object)this.delegate.getSubString(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject23943, onErrorForAll(methodObject23943, e));
    }
  }
  
  public void setBytes(byte[] arg0)
  {
    super.preForAll(methodObject23909, this, new Object[] { arg0 });
    this.delegate.setBytes(arg0);
  }
  
  public Reader getCharacterStream(long arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23941, this, new Object[] { Long.valueOf(arg0), Long.valueOf(arg1) });
      return (Reader)postForAll(methodObject23941, (Object)this.delegate.getCharacterStream(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject23941, onErrorForAll(methodObject23941, e));
    }
  }
  
  public void setPrefetchedData(char[] arg0)
  {
    super.preForAll(methodObject23963, this, new Object[] { arg0 });
    this.delegate.setPrefetchedData(arg0);
  }
  
  public Reader getCharacterStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23948, this, new Object[] { Long.valueOf(arg0) });
      return (Reader)postForAll(methodObject23948, (Object)this.delegate.getCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject23948, onErrorForAll(methodObject23948, e));
    }
  }
  
  public boolean isActivePrefetch()
  {
    super.preForAll(methodObject23969, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject23969, Boolean.valueOf(this.delegate.isActivePrefetch()))).booleanValue();
  }
  
  public int setString(long arg0, String arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject23939, this, new Object[] { Long.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return ((Integer)postForAll(methodObject23939, Integer.valueOf(this.delegate.setString(arg0, arg1, arg2, arg3)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23939, onErrorForAll(methodObject23939, e))).intValue();
    }
  }
  
  public boolean isNCLOB()
  {
    super.preForAll(methodObject23970, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject23970, Boolean.valueOf(this.delegate.isNCLOB()))).booleanValue();
  }
  
  public void truncate(long arg0)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject23937, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.truncate(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23937, e);
    }
  }
  
  public int setString(long arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject23938, this, new Object[] { Long.valueOf(arg0), arg1 });
      return ((Integer)postForAll(methodObject23938, Integer.valueOf(this.delegate.setString(arg0, arg1)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23938, onErrorForAll(methodObject23938, e))).intValue();
    }
  }
  
  public int putChars(long arg0, char[] arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23976, this, new Object[] { Long.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return ((Integer)postForAll(methodObject23976, Integer.valueOf(this.delegate.putChars(arg0, arg1, arg2, arg3)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23976, onErrorForAll(methodObject23976, e))).intValue();
    }
  }
  
  public byte byteValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23903, this, zeroLengthObjectArray);
      return ((Byte)postForAll(methodObject23903, Byte.valueOf(this.delegate.byteValue()))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject23903, onErrorForAll(methodObject23903, e))).byteValue();
    }
  }
  
  public Writer getCharacterOutputStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23972, this, zeroLengthObjectArray);
      return (Writer)postForAll(methodObject23972, (Object)this.delegate.getCharacterOutputStream());
    }
    catch (SQLException e)
    {
      return (Writer)postForAll(methodObject23972, onErrorForAll(methodObject23972, e));
    }
  }
  
  public Reader characterStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23950, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject23950, (Object)this.delegate.characterStreamValue());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject23950, onErrorForAll(methodObject23950, e));
    }
  }
  
  public float floatValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23905, this, zeroLengthObjectArray);
      return ((Float)postForAll(methodObject23905, Float.valueOf(this.delegate.floatValue()))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject23905, onErrorForAll(methodObject23905, e))).floatValue();
    }
  }
  
  public InputStream getStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23913, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23913, (Object)this.delegate.getStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23913, onErrorForAll(methodObject23913, e));
    }
  }
  
  public void open(LargeObjectAccessMode arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23979, this, new Object[] { arg0 });
      this.delegate.open(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23979, e);
    }
  }
  
  public Time timeValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23918, this, zeroLengthObjectArray);
      return (Time)postForAll(methodObject23918, (Object)this.delegate.timeValue());
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject23918, onErrorForAll(methodObject23918, e));
    }
  }
  
  public long getLength()
  {
    super.preForAll(methodObject23900, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject23900, Long.valueOf(this.delegate.getLength()))).longValue();
  }
  
  public long position(Clob arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23933, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject23933, Long.valueOf(this.delegate.position((arg0 instanceof _Proxy_) ? (Clob)((_Proxy_)arg0)._getDelegate_() : arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23933, onErrorForAll(methodObject23933, e))).longValue();
    }
  }
  
  public oracle.jdbc.driver.OracleConnection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23908, this, zeroLengthObjectArray);
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject23908, (Object)this.delegate.getConnection());
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject23908, onErrorForAll(methodObject23908, e));
    }
  }
  
  public OracleNClob _getDelegate_()
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
      methodObject23967 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("setActivePrefetch", new Class[] { Boolean.TYPE });
      methodObject23983 = oracle.jdbc.OracleClob.class.getDeclaredMethod("isTemporary", new Class[0]);
      methodObject23945 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("trim", new Class[] { Long.TYPE });
      methodObject23978 = oracle.jdbc.OracleClob.class.getDeclaredMethod("close", new Class[0]);
      methodObject23921 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[] { Calendar.class });
      methodObject23947 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getJavaSqlConnection", new Class[0]);
      methodObject23902 = OracleDatumWithConnection.class.getDeclaredMethod("booleanValue", new Class[0]);
      methodObject23944 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getChars", new Class[] { Long.TYPE, Integer.TYPE, char[].class });
      methodObject23981 = oracle.jdbc.OracleClob.class.getDeclaredMethod("isEmptyLob", new Class[0]);
      methodObject23975 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("putChars", new Class[] { Long.TYPE, char[].class });
      methodObject23964 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("setPrefetchedData", new Class[] { char[].class, Integer.TYPE });
      methodObject23920 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[0]);
      methodObject23936 = Clob.class.getDeclaredMethod("setCharacterStream", new Class[] { Long.TYPE });
      methodObject23962 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("setChunkSize", new Class[] { Integer.TYPE });
      methodObject23906 = OracleDatumWithConnection.class.getDeclaredMethod("intValue", new Class[0]);
      methodObject23919 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[] { Calendar.class });
      methodObject23949 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("stringValue", new Class[0]);
      methodObject23935 = Clob.class.getDeclaredMethod("setAsciiStream", new Class[] { Long.TYPE });
      methodObject23982 = oracle.jdbc.OracleClob.class.getDeclaredMethod("isSecureFile", new Class[0]);
      methodObject23934 = Clob.class.getDeclaredMethod("free", new Class[0]);
      methodObject23951 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("asciiStreamValue", new Class[0]);
      methodObject23911 = OracleDatumWithConnection.class.getDeclaredMethod("shareBytes", new Class[0]);
      methodObject23930 = OracleDatumWithConnection.class.getDeclaredMethod("setPhysicalConnectionOf", new Class[] { Connection.class });
      methodObject23904 = OracleDatumWithConnection.class.getDeclaredMethod("doubleValue", new Class[0]);
      methodObject23961 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getDBAccess", new Class[0]);
      methodObject23953 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("isConvertibleTo", new Class[] { Class.class });
      methodObject23958 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getLocator", new Class[0]);
      methodObject23968 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("clearCachedData", new Class[0]);
      methodObject23971 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getCharacterOutputStream", new Class[] { Long.TYPE });
      methodObject23932 = Clob.class.getDeclaredMethod("position", new Class[] { String.class, Long.TYPE });
      methodObject23907 = OracleDatumWithConnection.class.getDeclaredMethod("longValue", new Class[0]);
      methodObject23956 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getAsciiStream", new Class[] { Long.TYPE });
      methodObject23973 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getAsciiOutputStream", new Class[] { Long.TYPE });
      methodObject23965 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getPrefetchedData", new Class[0]);
      methodObject23915 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[] { Connection.class });
      methodObject23931 = Clob.class.getDeclaredMethod("length", new Class[0]);
      methodObject23929 = OracleDatumWithConnection.class.getDeclaredMethod("getInternalConnection", new Class[0]);
      methodObject23977 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("putString", new Class[] { Long.TYPE, String.class });
      methodObject23940 = Clob.class.getDeclaredMethod("getCharacterStream", new Class[0]);
      methodObject23901 = OracleDatumWithConnection.class.getDeclaredMethod("getBytes", new Class[0]);
      methodObject23974 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getAsciiOutputStream", new Class[0]);
      methodObject23966 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getPrefetchedDataSize", new Class[0]);
      methodObject23952 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("binaryStreamValue", new Class[0]);
      methodObject23955 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("makeJdbcArray", new Class[] { Integer.TYPE });
      methodObject23912 = OracleDatumWithConnection.class.getDeclaredMethod("setShareBytes", new Class[] { byte[].class });
      methodObject23928 = OracleDatumWithConnection.class.getDeclaredMethod("getOracleConnection", new Class[0]);
      methodObject23954 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("toJdbc", new Class[0]);
      methodObject23942 = Clob.class.getDeclaredMethod("getAsciiStream", new Class[0]);
      methodObject23960 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getBufferSize", new Class[0]);
      methodObject23980 = oracle.jdbc.OracleClob.class.getDeclaredMethod("isOpen", new Class[0]);
      methodObject23946 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("setLength", new Class[] { Long.TYPE });
      methodObject23917 = OracleDatumWithConnection.class.getDeclaredMethod("dateValue", new Class[0]);
      methodObject23959 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getChunkSize", new Class[0]);
      methodObject23957 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("setLocator", new Class[] { byte[].class });
      methodObject23916 = OracleDatumWithConnection.class.getDeclaredMethod("bigDecimalValue", new Class[0]);
      methodObject23943 = Clob.class.getDeclaredMethod("getSubString", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject23909 = OracleDatumWithConnection.class.getDeclaredMethod("setBytes", new Class[] { byte[].class });
      methodObject23941 = Clob.class.getDeclaredMethod("getCharacterStream", new Class[] { Long.TYPE, Long.TYPE });
      methodObject23963 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("setPrefetchedData", new Class[] { char[].class });
      methodObject23948 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getCharacterStream", new Class[] { Long.TYPE });
      methodObject23969 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("isActivePrefetch", new Class[0]);
      methodObject23939 = Clob.class.getDeclaredMethod("setString", new Class[] { Long.TYPE, String.class, Integer.TYPE, Integer.TYPE });
      methodObject23970 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("isNCLOB", new Class[0]);
      methodObject23937 = Clob.class.getDeclaredMethod("truncate", new Class[] { Long.TYPE });
      methodObject23938 = Clob.class.getDeclaredMethod("setString", new Class[] { Long.TYPE, String.class });
      methodObject23976 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("putChars", new Class[] { Long.TYPE, char[].class, Integer.TYPE, Integer.TYPE });
      methodObject23903 = OracleDatumWithConnection.class.getDeclaredMethod("byteValue", new Class[0]);
      methodObject23972 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getCharacterOutputStream", new Class[0]);
      methodObject23950 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("characterStreamValue", new Class[0]);
      methodObject23905 = OracleDatumWithConnection.class.getDeclaredMethod("floatValue", new Class[0]);
      methodObject23913 = OracleDatumWithConnection.class.getDeclaredMethod("getStream", new Class[0]);
      methodObject23979 = oracle.jdbc.OracleClob.class.getDeclaredMethod("open", new Class[] { LargeObjectAccessMode.class });
      methodObject23918 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[0]);
      methodObject23900 = OracleDatumWithConnection.class.getDeclaredMethod("getLength", new Class[0]);
      methodObject23933 = Clob.class.getDeclaredMethod("position", new Class[] { Clob.class, Long.TYPE });
      methodObject23908 = OracleDatumWithConnection.class.getDeclaredMethod("getConnection", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableNClob$2oracle$1jdbc$1internal$1OracleNClob$$$Proxy(OracleNClob paramOracleNClob, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleNClob;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableNClob$2oracle$1jdbc$1internal$1OracleNClob$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */