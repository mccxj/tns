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
import oracle.jdbc.replay.driver.NonTxnReplayableClob;
import oracle.sql.ClobDBAccess;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableClob$2oracle$1jdbc$1internal$1OracleClob$$$Proxy
  extends NonTxnReplayableClob
  implements oracle.jdbc.internal.OracleClob, _Proxy_
{
  private oracle.jdbc.internal.OracleClob delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject30843;
  private static Method methodObject30882;
  private static Method methodObject30821;
  private static Method methodObject30877;
  private static Method methodObject30873;
  private static Method methodObject30823;
  private static Method methodObject30856;
  private static Method methodObject30820;
  private static Method methodObject30880;
  private static Method methodObject30840;
  private static Method methodObject30851;
  private static Method methodObject30888;
  private static Method methodObject30872;
  private static Method methodObject30838;
  private static Method methodObject30860;
  private static Method methodObject30871;
  private static Method methodObject30825;
  private static Method methodObject30887;
  private static Method methodObject30881;
  private static Method methodObject30886;
  private static Method methodObject30827;
  private static Method methodObject30864;
  private static Method methodObject30876;
  private static Method methodObject30858;
  private static Method methodObject30837;
  private static Method methodObject30829;
  private static Method methodObject30834;
  private static Method methodObject30844;
  private static Method methodObject30847;
  private static Method methodObject30884;
  private static Method methodObject30861;
  private static Method methodObject30832;
  private static Method methodObject30849;
  private static Method methodObject30841;
  private static Method methodObject30867;
  private static Method methodObject30883;
  private static Method methodObject30875;
  private static Method methodObject30892;
  private static Method methodObject30853;
  private static Method methodObject30855;
  private static Method methodObject30850;
  private static Method methodObject30842;
  private static Method methodObject30828;
  private static Method methodObject30831;
  private static Method methodObject30865;
  private static Method methodObject30874;
  private static Method methodObject30894;
  private static Method methodObject30830;
  private static Method methodObject30836;
  private static Method methodObject30879;
  private static Method methodObject30822;
  private static Method methodObject30869;
  private static Method methodObject30835;
  private static Method methodObject30833;
  private static Method methodObject30868;
  private static Method methodObject30895;
  private static Method methodObject30863;
  private static Method methodObject30893;
  private static Method methodObject30839;
  private static Method methodObject30824;
  private static Method methodObject30891;
  private static Method methodObject30845;
  private static Method methodObject30846;
  private static Method methodObject30890;
  private static Method methodObject30889;
  private static Method methodObject30852;
  private static Method methodObject30857;
  private static Method methodObject30848;
  private static Method methodObject30826;
  private static Method methodObject30859;
  private static Method methodObject30866;
  private static Method methodObject30885;
  private static Method methodObject30878;
  private static Method methodObject30870;
  private static Method methodObject30854;
  private static Method methodObject30862;
  
  public void setActivePrefetch(boolean arg0)
  {
    super.preForAll(methodObject30843, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setActivePrefetch(arg0);
  }
  
  public boolean isTemporary()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30882, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject30882, Boolean.valueOf(this.delegate.isTemporary()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject30882, onErrorForAll(methodObject30882, e))).booleanValue();
    }
  }
  
  public void trim(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30821, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.trim(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30821, e);
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30877, this, zeroLengthObjectArray);
      this.delegate.close();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30877, e);
    }
  }
  
  public Timestamp timestampValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30873, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject30873, (Object)this.delegate.timestampValue(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject30873, onErrorForAll(methodObject30873, e));
    }
  }
  
  public Connection getJavaSqlConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30823, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject30823, this.proxyFactory.proxyForCache((Object)this.delegate.getJavaSqlConnection(), this, this.proxyCache, methodObject30823));
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject30823, onErrorForAll(methodObject30823, e));
    }
  }
  
  public boolean booleanValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30856, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject30856, Boolean.valueOf(this.delegate.booleanValue()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject30856, onErrorForAll(methodObject30856, e))).booleanValue();
    }
  }
  
  public int getChars(long arg0, int arg1, char[] arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30820, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      return ((Integer)postForAll(methodObject30820, Integer.valueOf(this.delegate.getChars(arg0, arg1, arg2)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject30820, onErrorForAll(methodObject30820, e))).intValue();
    }
  }
  
  public boolean isEmptyLob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30880, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject30880, Boolean.valueOf(this.delegate.isEmptyLob()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject30880, onErrorForAll(methodObject30880, e))).booleanValue();
    }
  }
  
  public void setPrefetchedData(char[] arg0, int arg1)
  {
    super.preForAll(methodObject30840, this, new Object[] { arg0, Integer.valueOf(arg1) });
    this.delegate.setPrefetchedData(arg0, arg1);
  }
  
  public int putChars(long arg0, char[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30851, this, new Object[] { Long.valueOf(arg0), arg1 });
      return ((Integer)postForAll(methodObject30851, Integer.valueOf(this.delegate.putChars(arg0, arg1)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject30851, onErrorForAll(methodObject30851, e))).intValue();
    }
  }
  
  public Writer setCharacterStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30888, this, new Object[] { Long.valueOf(arg0) });
      return (Writer)postForAll(methodObject30888, (Object)this.delegate.setCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Writer)postForAll(methodObject30888, onErrorForAll(methodObject30888, e));
    }
  }
  
  public Timestamp timestampValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30872, this, zeroLengthObjectArray);
      return (Timestamp)postForAll(methodObject30872, (Object)this.delegate.timestampValue());
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject30872, onErrorForAll(methodObject30872, e));
    }
  }
  
  public void setChunkSize(int arg0)
  {
    super.preForAll(methodObject30838, this, new Object[] { Integer.valueOf(arg0) });
    this.delegate.setChunkSize(arg0);
  }
  
  public int intValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30860, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject30860, Integer.valueOf(this.delegate.intValue()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject30860, onErrorForAll(methodObject30860, e))).intValue();
    }
  }
  
  public Time timeValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30871, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject30871, (Object)this.delegate.timeValue(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject30871, onErrorForAll(methodObject30871, e));
    }
  }
  
  public String stringValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30825, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject30825, (Object)this.delegate.stringValue());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject30825, onErrorForAll(methodObject30825, e));
    }
  }
  
  public OutputStream setAsciiStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30887, this, new Object[] { Long.valueOf(arg0) });
      return (OutputStream)postForAll(methodObject30887, (Object)this.delegate.setAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject30887, onErrorForAll(methodObject30887, e));
    }
  }
  
  public boolean isSecureFile()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30881, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject30881, Boolean.valueOf(this.delegate.isSecureFile()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject30881, onErrorForAll(methodObject30881, e))).booleanValue();
    }
  }
  
  public void free()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30886, this, zeroLengthObjectArray);
      this.delegate.free();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30886, e);
    }
  }
  
  public InputStream asciiStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30827, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject30827, (Object)this.delegate.asciiStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject30827, onErrorForAll(methodObject30827, e));
    }
  }
  
  public byte[] shareBytes()
  {
    super.preForAll(methodObject30864, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject30864, (Object)this.delegate.shareBytes());
  }
  
  public void setPhysicalConnectionOf(Connection arg0)
  {
    super.preForAll(methodObject30876, this, new Object[] { arg0 });
    this.delegate.setPhysicalConnectionOf((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0);
  }
  
  public double doubleValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30858, this, zeroLengthObjectArray);
      return ((Double)postForAll(methodObject30858, Double.valueOf(this.delegate.doubleValue()))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject30858, onErrorForAll(methodObject30858, e))).doubleValue();
    }
  }
  
  public ClobDBAccess getDBAccess()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30837, this, zeroLengthObjectArray);
      return (ClobDBAccess)postForAll(methodObject30837, (Object)this.delegate.getDBAccess());
    }
    catch (SQLException e)
    {
      return (ClobDBAccess)postForAll(methodObject30837, onErrorForAll(methodObject30837, e));
    }
  }
  
  public boolean isConvertibleTo(Class arg0)
  {
    super.preForAll(methodObject30829, this, new Object[] { arg0 });
    return ((Boolean)postForAll(methodObject30829, Boolean.valueOf(this.delegate.isConvertibleTo(arg0)))).booleanValue();
  }
  
  public byte[] getLocator()
  {
    super.preForAll(methodObject30834, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject30834, (Object)this.delegate.getLocator());
  }
  
  public void clearCachedData()
  {
    super.preForAll(methodObject30844, this, zeroLengthObjectArray);
    this.delegate.clearCachedData();
  }
  
  public Writer getCharacterOutputStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30847, this, new Object[] { Long.valueOf(arg0) });
      return (Writer)postForAll(methodObject30847, (Object)this.delegate.getCharacterOutputStream(arg0));
    }
    catch (SQLException e)
    {
      return (Writer)postForAll(methodObject30847, onErrorForAll(methodObject30847, e));
    }
  }
  
  public long position(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30884, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject30884, Long.valueOf(this.delegate.position(arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject30884, onErrorForAll(methodObject30884, e))).longValue();
    }
  }
  
  public long longValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30861, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject30861, Long.valueOf(this.delegate.longValue()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject30861, onErrorForAll(methodObject30861, e))).longValue();
    }
  }
  
  public InputStream getAsciiStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30832, this, new Object[] { Long.valueOf(arg0) });
      return (InputStream)postForAll(methodObject30832, (Object)this.delegate.getAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject30832, onErrorForAll(methodObject30832, e));
    }
  }
  
  public OutputStream getAsciiOutputStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30849, this, new Object[] { Long.valueOf(arg0) });
      return (OutputStream)postForAll(methodObject30849, (Object)this.delegate.getAsciiOutputStream(arg0));
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject30849, onErrorForAll(methodObject30849, e));
    }
  }
  
  public char[] getPrefetchedData()
  {
    super.preForAll(methodObject30841, this, zeroLengthObjectArray);
    return (char[])postForAll(methodObject30841, (Object)this.delegate.getPrefetchedData());
  }
  
  public String stringValue(Connection arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30867, this, new Object[] { arg0 });
      return (String)postForAll(methodObject30867, (Object)this.delegate.stringValue((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject30867, onErrorForAll(methodObject30867, e));
    }
  }
  
  public long length()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30883, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject30883, Long.valueOf(this.delegate.length()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject30883, onErrorForAll(methodObject30883, e))).longValue();
    }
  }
  
  public oracle.jdbc.internal.OracleConnection getInternalConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30875, this, zeroLengthObjectArray);
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject30875, this.proxyFactory.proxyForCache((Object)this.delegate.getInternalConnection(), this, this.proxyCache, methodObject30875));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject30875, onErrorForAll(methodObject30875, e));
    }
  }
  
  public Reader getCharacterStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30892, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject30892, (Object)this.delegate.getCharacterStream());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject30892, onErrorForAll(methodObject30892, e));
    }
  }
  
  public int putString(long arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30853, this, new Object[] { Long.valueOf(arg0), arg1 });
      return ((Integer)postForAll(methodObject30853, Integer.valueOf(this.delegate.putString(arg0, arg1)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject30853, onErrorForAll(methodObject30853, e))).intValue();
    }
  }
  
  public byte[] getBytes()
  {
    super.preForAll(methodObject30855, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject30855, (Object)this.delegate.getBytes());
  }
  
  public OutputStream getAsciiOutputStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30850, this, zeroLengthObjectArray);
      return (OutputStream)postForAll(methodObject30850, (Object)this.delegate.getAsciiOutputStream());
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject30850, onErrorForAll(methodObject30850, e));
    }
  }
  
  public int getPrefetchedDataSize()
  {
    super.preForAll(methodObject30842, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject30842, Integer.valueOf(this.delegate.getPrefetchedDataSize()))).intValue();
  }
  
  public InputStream binaryStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30828, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject30828, (Object)this.delegate.binaryStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject30828, onErrorForAll(methodObject30828, e));
    }
  }
  
  public Object makeJdbcArray(int arg0)
  {
    super.preForAll(methodObject30831, this, new Object[] { Integer.valueOf(arg0) });
    return postForAll(methodObject30831, this.proxyFactory.proxyForCache(this.delegate.makeJdbcArray(arg0), this, this.proxyCache, methodObject30831));
  }
  
  public void setShareBytes(byte[] arg0)
  {
    super.preForAll(methodObject30865, this, new Object[] { arg0 });
    this.delegate.setShareBytes(arg0);
  }
  
  public oracle.jdbc.OracleConnection getOracleConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30874, this, zeroLengthObjectArray);
      return (oracle.jdbc.OracleConnection)postForAll(methodObject30874, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleConnection(), this, this.proxyCache, methodObject30874));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.OracleConnection)postForAll(methodObject30874, onErrorForAll(methodObject30874, e));
    }
  }
  
  public InputStream getAsciiStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30894, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject30894, (Object)this.delegate.getAsciiStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject30894, onErrorForAll(methodObject30894, e));
    }
  }
  
  public Object toJdbc()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30830, this, zeroLengthObjectArray);
      return postForAll(methodObject30830, this.proxyFactory.proxyForCache(this.delegate.toJdbc(), this, this.proxyCache, methodObject30830));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject30830, onErrorForAll(methodObject30830, e));
    }
  }
  
  public int getBufferSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30836, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject30836, Integer.valueOf(this.delegate.getBufferSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject30836, onErrorForAll(methodObject30836, e))).intValue();
    }
  }
  
  public boolean isOpen()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30879, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject30879, Boolean.valueOf(this.delegate.isOpen()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject30879, onErrorForAll(methodObject30879, e))).booleanValue();
    }
  }
  
  public void setLength(long arg0)
  {
    super.preForAll(methodObject30822, this, new Object[] { Long.valueOf(arg0) });
    this.delegate.setLength(arg0);
  }
  
  public Date dateValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30869, this, zeroLengthObjectArray);
      return (Date)postForAll(methodObject30869, (Object)this.delegate.dateValue());
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject30869, onErrorForAll(methodObject30869, e));
    }
  }
  
  public int getChunkSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30835, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject30835, Integer.valueOf(this.delegate.getChunkSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject30835, onErrorForAll(methodObject30835, e))).intValue();
    }
  }
  
  public void setLocator(byte[] arg0)
  {
    super.preForAll(methodObject30833, this, new Object[] { arg0 });
    this.delegate.setLocator(arg0);
  }
  
  public BigDecimal bigDecimalValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30868, this, zeroLengthObjectArray);
      return (BigDecimal)postForAll(methodObject30868, (Object)this.delegate.bigDecimalValue());
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject30868, onErrorForAll(methodObject30868, e));
    }
  }
  
  public String getSubString(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30895, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (String)postForAll(methodObject30895, (Object)this.delegate.getSubString(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject30895, onErrorForAll(methodObject30895, e));
    }
  }
  
  public void setBytes(byte[] arg0)
  {
    super.preForAll(methodObject30863, this, new Object[] { arg0 });
    this.delegate.setBytes(arg0);
  }
  
  public Reader getCharacterStream(long arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30893, this, new Object[] { Long.valueOf(arg0), Long.valueOf(arg1) });
      return (Reader)postForAll(methodObject30893, (Object)this.delegate.getCharacterStream(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject30893, onErrorForAll(methodObject30893, e));
    }
  }
  
  public void setPrefetchedData(char[] arg0)
  {
    super.preForAll(methodObject30839, this, new Object[] { arg0 });
    this.delegate.setPrefetchedData(arg0);
  }
  
  public Reader getCharacterStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30824, this, new Object[] { Long.valueOf(arg0) });
      return (Reader)postForAll(methodObject30824, (Object)this.delegate.getCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject30824, onErrorForAll(methodObject30824, e));
    }
  }
  
  public int setString(long arg0, String arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject30891, this, new Object[] { Long.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return ((Integer)postForAll(methodObject30891, Integer.valueOf(this.delegate.setString(arg0, arg1, arg2, arg3)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject30891, onErrorForAll(methodObject30891, e))).intValue();
    }
  }
  
  public boolean isActivePrefetch()
  {
    super.preForAll(methodObject30845, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject30845, Boolean.valueOf(this.delegate.isActivePrefetch()))).booleanValue();
  }
  
  public boolean isNCLOB()
  {
    super.preForAll(methodObject30846, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject30846, Boolean.valueOf(this.delegate.isNCLOB()))).booleanValue();
  }
  
  public int setString(long arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject30890, this, new Object[] { Long.valueOf(arg0), arg1 });
      return ((Integer)postForAll(methodObject30890, Integer.valueOf(this.delegate.setString(arg0, arg1)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject30890, onErrorForAll(methodObject30890, e))).intValue();
    }
  }
  
  public void truncate(long arg0)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject30889, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.truncate(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30889, e);
    }
  }
  
  public int putChars(long arg0, char[] arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30852, this, new Object[] { Long.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return ((Integer)postForAll(methodObject30852, Integer.valueOf(this.delegate.putChars(arg0, arg1, arg2, arg3)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject30852, onErrorForAll(methodObject30852, e))).intValue();
    }
  }
  
  public byte byteValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30857, this, zeroLengthObjectArray);
      return ((Byte)postForAll(methodObject30857, Byte.valueOf(this.delegate.byteValue()))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject30857, onErrorForAll(methodObject30857, e))).byteValue();
    }
  }
  
  public Writer getCharacterOutputStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30848, this, zeroLengthObjectArray);
      return (Writer)postForAll(methodObject30848, (Object)this.delegate.getCharacterOutputStream());
    }
    catch (SQLException e)
    {
      return (Writer)postForAll(methodObject30848, onErrorForAll(methodObject30848, e));
    }
  }
  
  public Reader characterStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30826, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject30826, (Object)this.delegate.characterStreamValue());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject30826, onErrorForAll(methodObject30826, e));
    }
  }
  
  public float floatValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30859, this, zeroLengthObjectArray);
      return ((Float)postForAll(methodObject30859, Float.valueOf(this.delegate.floatValue()))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject30859, onErrorForAll(methodObject30859, e))).floatValue();
    }
  }
  
  public InputStream getStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30866, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject30866, (Object)this.delegate.getStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject30866, onErrorForAll(methodObject30866, e));
    }
  }
  
  public long position(Clob arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30885, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject30885, Long.valueOf(this.delegate.position((arg0 instanceof _Proxy_) ? (Clob)((_Proxy_)arg0)._getDelegate_() : arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject30885, onErrorForAll(methodObject30885, e))).longValue();
    }
  }
  
  public void open(LargeObjectAccessMode arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30878, this, new Object[] { arg0 });
      this.delegate.open(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30878, e);
    }
  }
  
  public Time timeValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30870, this, zeroLengthObjectArray);
      return (Time)postForAll(methodObject30870, (Object)this.delegate.timeValue());
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject30870, onErrorForAll(methodObject30870, e));
    }
  }
  
  public long getLength()
  {
    super.preForAll(methodObject30854, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject30854, Long.valueOf(this.delegate.getLength()))).longValue();
  }
  
  public oracle.jdbc.driver.OracleConnection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30862, this, zeroLengthObjectArray);
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject30862, (Object)this.delegate.getConnection());
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject30862, onErrorForAll(methodObject30862, e));
    }
  }
  
  public oracle.jdbc.internal.OracleClob _getDelegate_()
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
      methodObject30843 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("setActivePrefetch", new Class[] { Boolean.TYPE });
      methodObject30882 = oracle.jdbc.OracleClob.class.getDeclaredMethod("isTemporary", new Class[0]);
      methodObject30821 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("trim", new Class[] { Long.TYPE });
      methodObject30877 = oracle.jdbc.OracleClob.class.getDeclaredMethod("close", new Class[0]);
      methodObject30873 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[] { Calendar.class });
      methodObject30823 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getJavaSqlConnection", new Class[0]);
      methodObject30856 = OracleDatumWithConnection.class.getDeclaredMethod("booleanValue", new Class[0]);
      methodObject30820 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getChars", new Class[] { Long.TYPE, Integer.TYPE, char[].class });
      methodObject30880 = oracle.jdbc.OracleClob.class.getDeclaredMethod("isEmptyLob", new Class[0]);
      methodObject30840 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("setPrefetchedData", new Class[] { char[].class, Integer.TYPE });
      methodObject30851 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("putChars", new Class[] { Long.TYPE, char[].class });
      methodObject30888 = Clob.class.getDeclaredMethod("setCharacterStream", new Class[] { Long.TYPE });
      methodObject30872 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[0]);
      methodObject30838 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("setChunkSize", new Class[] { Integer.TYPE });
      methodObject30860 = OracleDatumWithConnection.class.getDeclaredMethod("intValue", new Class[0]);
      methodObject30871 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[] { Calendar.class });
      methodObject30825 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("stringValue", new Class[0]);
      methodObject30887 = Clob.class.getDeclaredMethod("setAsciiStream", new Class[] { Long.TYPE });
      methodObject30881 = oracle.jdbc.OracleClob.class.getDeclaredMethod("isSecureFile", new Class[0]);
      methodObject30886 = Clob.class.getDeclaredMethod("free", new Class[0]);
      methodObject30827 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("asciiStreamValue", new Class[0]);
      methodObject30864 = OracleDatumWithConnection.class.getDeclaredMethod("shareBytes", new Class[0]);
      methodObject30876 = OracleDatumWithConnection.class.getDeclaredMethod("setPhysicalConnectionOf", new Class[] { Connection.class });
      methodObject30858 = OracleDatumWithConnection.class.getDeclaredMethod("doubleValue", new Class[0]);
      methodObject30837 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getDBAccess", new Class[0]);
      methodObject30829 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("isConvertibleTo", new Class[] { Class.class });
      methodObject30834 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getLocator", new Class[0]);
      methodObject30844 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("clearCachedData", new Class[0]);
      methodObject30847 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getCharacterOutputStream", new Class[] { Long.TYPE });
      methodObject30884 = Clob.class.getDeclaredMethod("position", new Class[] { String.class, Long.TYPE });
      methodObject30861 = OracleDatumWithConnection.class.getDeclaredMethod("longValue", new Class[0]);
      methodObject30832 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getAsciiStream", new Class[] { Long.TYPE });
      methodObject30849 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getAsciiOutputStream", new Class[] { Long.TYPE });
      methodObject30841 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getPrefetchedData", new Class[0]);
      methodObject30867 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[] { Connection.class });
      methodObject30883 = Clob.class.getDeclaredMethod("length", new Class[0]);
      methodObject30875 = OracleDatumWithConnection.class.getDeclaredMethod("getInternalConnection", new Class[0]);
      methodObject30892 = Clob.class.getDeclaredMethod("getCharacterStream", new Class[0]);
      methodObject30853 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("putString", new Class[] { Long.TYPE, String.class });
      methodObject30855 = OracleDatumWithConnection.class.getDeclaredMethod("getBytes", new Class[0]);
      methodObject30850 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getAsciiOutputStream", new Class[0]);
      methodObject30842 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getPrefetchedDataSize", new Class[0]);
      methodObject30828 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("binaryStreamValue", new Class[0]);
      methodObject30831 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("makeJdbcArray", new Class[] { Integer.TYPE });
      methodObject30865 = OracleDatumWithConnection.class.getDeclaredMethod("setShareBytes", new Class[] { byte[].class });
      methodObject30874 = OracleDatumWithConnection.class.getDeclaredMethod("getOracleConnection", new Class[0]);
      methodObject30894 = Clob.class.getDeclaredMethod("getAsciiStream", new Class[0]);
      methodObject30830 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("toJdbc", new Class[0]);
      methodObject30836 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getBufferSize", new Class[0]);
      methodObject30879 = oracle.jdbc.OracleClob.class.getDeclaredMethod("isOpen", new Class[0]);
      methodObject30822 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("setLength", new Class[] { Long.TYPE });
      methodObject30869 = OracleDatumWithConnection.class.getDeclaredMethod("dateValue", new Class[0]);
      methodObject30835 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getChunkSize", new Class[0]);
      methodObject30833 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("setLocator", new Class[] { byte[].class });
      methodObject30868 = OracleDatumWithConnection.class.getDeclaredMethod("bigDecimalValue", new Class[0]);
      methodObject30895 = Clob.class.getDeclaredMethod("getSubString", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject30863 = OracleDatumWithConnection.class.getDeclaredMethod("setBytes", new Class[] { byte[].class });
      methodObject30893 = Clob.class.getDeclaredMethod("getCharacterStream", new Class[] { Long.TYPE, Long.TYPE });
      methodObject30839 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("setPrefetchedData", new Class[] { char[].class });
      methodObject30824 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getCharacterStream", new Class[] { Long.TYPE });
      methodObject30891 = Clob.class.getDeclaredMethod("setString", new Class[] { Long.TYPE, String.class, Integer.TYPE, Integer.TYPE });
      methodObject30845 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("isActivePrefetch", new Class[0]);
      methodObject30846 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("isNCLOB", new Class[0]);
      methodObject30890 = Clob.class.getDeclaredMethod("setString", new Class[] { Long.TYPE, String.class });
      methodObject30889 = Clob.class.getDeclaredMethod("truncate", new Class[] { Long.TYPE });
      methodObject30852 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("putChars", new Class[] { Long.TYPE, char[].class, Integer.TYPE, Integer.TYPE });
      methodObject30857 = OracleDatumWithConnection.class.getDeclaredMethod("byteValue", new Class[0]);
      methodObject30848 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("getCharacterOutputStream", new Class[0]);
      methodObject30826 = oracle.jdbc.internal.OracleClob.class.getDeclaredMethod("characterStreamValue", new Class[0]);
      methodObject30859 = OracleDatumWithConnection.class.getDeclaredMethod("floatValue", new Class[0]);
      methodObject30866 = OracleDatumWithConnection.class.getDeclaredMethod("getStream", new Class[0]);
      methodObject30885 = Clob.class.getDeclaredMethod("position", new Class[] { Clob.class, Long.TYPE });
      methodObject30878 = oracle.jdbc.OracleClob.class.getDeclaredMethod("open", new Class[] { LargeObjectAccessMode.class });
      methodObject30870 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[0]);
      methodObject30854 = OracleDatumWithConnection.class.getDeclaredMethod("getLength", new Class[0]);
      methodObject30862 = OracleDatumWithConnection.class.getDeclaredMethod("getConnection", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableClob$2oracle$1jdbc$1internal$1OracleClob$$$Proxy(oracle.jdbc.internal.OracleClob paramOracleClob, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleClob;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableClob$2oracle$1jdbc$1internal$1OracleClob$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */