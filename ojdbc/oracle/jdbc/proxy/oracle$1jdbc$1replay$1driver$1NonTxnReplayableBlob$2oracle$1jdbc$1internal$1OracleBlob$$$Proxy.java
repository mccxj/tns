package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import oracle.jdbc.LargeObjectAccessMode;
import oracle.jdbc.internal.OracleDatumWithConnection;
import oracle.jdbc.replay.driver.NonTxnReplayableBlob;
import oracle.sql.BlobDBAccess;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBlob$2oracle$1jdbc$1internal$1OracleBlob$$$Proxy
  extends NonTxnReplayableBlob
  implements oracle.jdbc.internal.OracleBlob, _Proxy_
{
  private oracle.jdbc.internal.OracleBlob delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject23164;
  private static Method methodObject23153;
  private static Method methodObject23198;
  private static Method methodObject23141;
  private static Method methodObject23192;
  private static Method methodObject23143;
  private static Method methodObject23187;
  private static Method methodObject23169;
  private static Method methodObject23196;
  private static Method methodObject23199;
  private static Method methodObject23186;
  private static Method methodObject23159;
  private static Method methodObject23155;
  private static Method methodObject23173;
  private static Method methodObject23180;
  private static Method methodObject23185;
  private static Method methodObject23197;
  private static Method methodObject23203;
  private static Method methodObject23145;
  private static Method methodObject23177;
  private static Method methodObject23190;
  private static Method methodObject23161;
  private static Method methodObject23171;
  private static Method methodObject23158;
  private static Method methodObject23147;
  private static Method methodObject23151;
  private static Method methodObject23165;
  private static Method methodObject23174;
  private static Method methodObject23205;
  private static Method methodObject23195;
  private static Method methodObject23162;
  private static Method methodObject23181;
  private static Method methodObject23209;
  private static Method methodObject23201;
  private static Method methodObject23200;
  private static Method methodObject23189;
  private static Method methodObject23208;
  private static Method methodObject23202;
  private static Method methodObject23152;
  private static Method methodObject23168;
  private static Method methodObject23163;
  private static Method methodObject23146;
  private static Method methodObject23154;
  private static Method methodObject23149;
  private static Method methodObject23178;
  private static Method methodObject23188;
  private static Method methodObject23160;
  private static Method methodObject23148;
  private static Method methodObject23157;
  private static Method methodObject23206;
  private static Method methodObject23194;
  private static Method methodObject23191;
  private static Method methodObject23142;
  private static Method methodObject23183;
  private static Method methodObject23156;
  private static Method methodObject23150;
  private static Method methodObject23182;
  private static Method methodObject23204;
  private static Method methodObject23176;
  private static Method methodObject23166;
  private static Method methodObject23207;
  private static Method methodObject23170;
  private static Method methodObject23144;
  private static Method methodObject23172;
  private static Method methodObject23179;
  private static Method methodObject23193;
  private static Method methodObject23167;
  private static Method methodObject23184;
  private static Method methodObject23175;
  
  public void setActivePrefetch(boolean arg0)
  {
    super.preForAll(methodObject23164, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setActivePrefetch(arg0);
  }
  
  public int putBytes(long arg0, byte[] arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23153, this, new Object[] { Long.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      return ((Integer)postForAll(methodObject23153, Integer.valueOf(this.delegate.putBytes(arg0, arg1, arg2)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23153, onErrorForAll(methodObject23153, e))).intValue();
    }
  }
  
  public boolean isTemporary()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23198, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23198, Boolean.valueOf(this.delegate.isTemporary()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23198, onErrorForAll(methodObject23198, e))).booleanValue();
    }
  }
  
  public void trim(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23141, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.trim(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23141, e);
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23192, this, zeroLengthObjectArray);
      this.delegate.close();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23192, e);
    }
  }
  
  public Connection getJavaSqlConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23143, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject23143, this.proxyFactory.proxyForCache((Object)this.delegate.getJavaSqlConnection(), this, this.proxyCache, methodObject23143));
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject23143, onErrorForAll(methodObject23143, e));
    }
  }
  
  public Timestamp timestampValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23187, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject23187, (Object)this.delegate.timestampValue(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject23187, onErrorForAll(methodObject23187, e));
    }
  }
  
  public boolean booleanValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23169, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23169, Boolean.valueOf(this.delegate.booleanValue()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23169, onErrorForAll(methodObject23169, e))).booleanValue();
    }
  }
  
  public boolean isEmptyLob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23196, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23196, Boolean.valueOf(this.delegate.isEmptyLob()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23196, onErrorForAll(methodObject23196, e))).booleanValue();
    }
  }
  
  public byte[] getBytes(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23199, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (byte[])postForAll(methodObject23199, (Object)this.delegate.getBytes(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject23199, onErrorForAll(methodObject23199, e));
    }
  }
  
  public Timestamp timestampValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23186, this, zeroLengthObjectArray);
      return (Timestamp)postForAll(methodObject23186, (Object)this.delegate.timestampValue());
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject23186, onErrorForAll(methodObject23186, e));
    }
  }
  
  public void setChunkSize(int arg0)
  {
    super.preForAll(methodObject23159, this, new Object[] { Integer.valueOf(arg0) });
    this.delegate.setChunkSize(arg0);
  }
  
  public OutputStream getBinaryOutputStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23155, this, new Object[] { Long.valueOf(arg0) });
      return (OutputStream)postForAll(methodObject23155, (Object)this.delegate.getBinaryOutputStream(arg0));
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject23155, onErrorForAll(methodObject23155, e));
    }
  }
  
  public int intValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23173, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject23173, Integer.valueOf(this.delegate.intValue()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23173, onErrorForAll(methodObject23173, e))).intValue();
    }
  }
  
  public String stringValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23180, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject23180, (Object)this.delegate.stringValue());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject23180, onErrorForAll(methodObject23180, e));
    }
  }
  
  public Time timeValue(Calendar arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23185, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject23185, (Object)this.delegate.timeValue(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject23185, onErrorForAll(methodObject23185, e));
    }
  }
  
  public boolean isSecureFile()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23197, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23197, Boolean.valueOf(this.delegate.isSecureFile()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23197, onErrorForAll(methodObject23197, e))).booleanValue();
    }
  }
  
  public void free()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23203, this, zeroLengthObjectArray);
      this.delegate.free();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23203, e);
    }
  }
  
  public InputStream asciiStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23145, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23145, (Object)this.delegate.asciiStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23145, onErrorForAll(methodObject23145, e));
    }
  }
  
  public byte[] shareBytes()
  {
    super.preForAll(methodObject23177, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject23177, (Object)this.delegate.shareBytes());
  }
  
  public void setPhysicalConnectionOf(Connection arg0)
  {
    super.preForAll(methodObject23190, this, new Object[] { arg0 });
    this.delegate.setPhysicalConnectionOf((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0);
  }
  
  public void setPrefetchedData(byte[] arg0, int arg1)
  {
    super.preForAll(methodObject23161, this, new Object[] { arg0, Integer.valueOf(arg1) });
    this.delegate.setPrefetchedData(arg0, arg1);
  }
  
  public double doubleValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23171, this, zeroLengthObjectArray);
      return ((Double)postForAll(methodObject23171, Double.valueOf(this.delegate.doubleValue()))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject23171, onErrorForAll(methodObject23171, e))).doubleValue();
    }
  }
  
  public BlobDBAccess getDBAccess()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23158, this, zeroLengthObjectArray);
      return (BlobDBAccess)postForAll(methodObject23158, (Object)this.delegate.getDBAccess());
    }
    catch (SQLException e)
    {
      return (BlobDBAccess)postForAll(methodObject23158, onErrorForAll(methodObject23158, e));
    }
  }
  
  public boolean isConvertibleTo(Class arg0)
  {
    super.preForAll(methodObject23147, this, new Object[] { arg0 });
    return ((Boolean)postForAll(methodObject23147, Boolean.valueOf(this.delegate.isConvertibleTo(arg0)))).booleanValue();
  }
  
  public byte[] getLocator()
  {
    super.preForAll(methodObject23151, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject23151, (Object)this.delegate.getLocator());
  }
  
  public void clearCachedData()
  {
    super.preForAll(methodObject23165, this, zeroLengthObjectArray);
    this.delegate.clearCachedData();
  }
  
  public long longValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23174, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject23174, Long.valueOf(this.delegate.longValue()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23174, onErrorForAll(methodObject23174, e))).longValue();
    }
  }
  
  public int setBytes(long arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForBlobWrites(methodObject23205, this, new Object[] { Long.valueOf(arg0), arg1 });
      return ((Integer)postForAll(methodObject23205, Integer.valueOf(this.delegate.setBytes(arg0, arg1)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23205, onErrorForAll(methodObject23205, e))).intValue();
    }
  }
  
  public InputStream getBinaryStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23195, this, new Object[] { Long.valueOf(arg0) });
      return (InputStream)postForAll(methodObject23195, (Object)this.delegate.getBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23195, onErrorForAll(methodObject23195, e));
    }
  }
  
  public byte[] getPrefetchedData()
  {
    super.preForAll(methodObject23162, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject23162, (Object)this.delegate.getPrefetchedData());
  }
  
  public String stringValue(Connection arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23181, this, new Object[] { arg0 });
      return (String)postForAll(methodObject23181, (Object)this.delegate.stringValue((arg0 instanceof _Proxy_) ? (Connection)((_Proxy_)arg0)._getDelegate_() : arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject23181, onErrorForAll(methodObject23181, e));
    }
  }
  
  public InputStream getBinaryStream(long arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23209, this, new Object[] { Long.valueOf(arg0), Long.valueOf(arg1) });
      return (InputStream)postForAll(methodObject23209, (Object)this.delegate.getBinaryStream(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23209, onErrorForAll(methodObject23209, e));
    }
  }
  
  public long position(byte[] arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23201, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject23201, Long.valueOf(this.delegate.position(arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23201, onErrorForAll(methodObject23201, e))).longValue();
    }
  }
  
  public long length()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23200, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject23200, Long.valueOf(this.delegate.length()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23200, onErrorForAll(methodObject23200, e))).longValue();
    }
  }
  
  public oracle.jdbc.internal.OracleConnection getInternalConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23189, this, zeroLengthObjectArray);
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject23189, this.proxyFactory.proxyForCache((Object)this.delegate.getInternalConnection(), this, this.proxyCache, methodObject23189));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject23189, onErrorForAll(methodObject23189, e));
    }
  }
  
  public InputStream getBinaryStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23208, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23208, (Object)this.delegate.getBinaryStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23208, onErrorForAll(methodObject23208, e));
    }
  }
  
  public long position(Blob arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23202, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject23202, Long.valueOf(this.delegate.position((arg0 instanceof _Proxy_) ? (Blob)((_Proxy_)arg0)._getDelegate_() : arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23202, onErrorForAll(methodObject23202, e))).longValue();
    }
  }
  
  public int putBytes(long arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23152, this, new Object[] { Long.valueOf(arg0), arg1 });
      return ((Integer)postForAll(methodObject23152, Integer.valueOf(this.delegate.putBytes(arg0, arg1)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23152, onErrorForAll(methodObject23152, e))).intValue();
    }
  }
  
  public byte[] getBytes()
  {
    super.preForAll(methodObject23168, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject23168, (Object)this.delegate.getBytes());
  }
  
  public int getPrefetchedDataSize()
  {
    super.preForAll(methodObject23163, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject23163, Integer.valueOf(this.delegate.getPrefetchedDataSize()))).intValue();
  }
  
  public InputStream binaryStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23146, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23146, (Object)this.delegate.binaryStreamValue());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23146, onErrorForAll(methodObject23146, e));
    }
  }
  
  public OutputStream getBinaryOutputStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23154, this, zeroLengthObjectArray);
      return (OutputStream)postForAll(methodObject23154, (Object)this.delegate.getBinaryOutputStream());
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject23154, onErrorForAll(methodObject23154, e));
    }
  }
  
  public Object makeJdbcArray(int arg0)
  {
    super.preForAll(methodObject23149, this, new Object[] { Integer.valueOf(arg0) });
    return postForAll(methodObject23149, this.proxyFactory.proxyForCache(this.delegate.makeJdbcArray(arg0), this, this.proxyCache, methodObject23149));
  }
  
  public void setShareBytes(byte[] arg0)
  {
    super.preForAll(methodObject23178, this, new Object[] { arg0 });
    this.delegate.setShareBytes(arg0);
  }
  
  public oracle.jdbc.OracleConnection getOracleConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23188, this, zeroLengthObjectArray);
      return (oracle.jdbc.OracleConnection)postForAll(methodObject23188, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleConnection(), this, this.proxyCache, methodObject23188));
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.OracleConnection)postForAll(methodObject23188, onErrorForAll(methodObject23188, e));
    }
  }
  
  public void setPrefetchedData(byte[] arg0)
  {
    super.preForAll(methodObject23160, this, new Object[] { arg0 });
    this.delegate.setPrefetchedData(arg0);
  }
  
  public Object toJdbc()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23148, this, zeroLengthObjectArray);
      return postForAll(methodObject23148, this.proxyFactory.proxyForCache(this.delegate.toJdbc(), this, this.proxyCache, methodObject23148));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject23148, onErrorForAll(methodObject23148, e));
    }
  }
  
  public int getBufferSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23157, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject23157, Integer.valueOf(this.delegate.getBufferSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23157, onErrorForAll(methodObject23157, e))).intValue();
    }
  }
  
  public int setBytes(long arg0, byte[] arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForBlobWrites(methodObject23206, this, new Object[] { Long.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return ((Integer)postForAll(methodObject23206, Integer.valueOf(this.delegate.setBytes(arg0, arg1, arg2, arg3)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23206, onErrorForAll(methodObject23206, e))).intValue();
    }
  }
  
  public boolean isOpen()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23194, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23194, Boolean.valueOf(this.delegate.isOpen()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23194, onErrorForAll(methodObject23194, e))).booleanValue();
    }
  }
  
  public int getBytes(long arg0, int arg1, byte[] arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23191, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      return ((Integer)postForAll(methodObject23191, Integer.valueOf(this.delegate.getBytes(arg0, arg1, arg2)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23191, onErrorForAll(methodObject23191, e))).intValue();
    }
  }
  
  public void setLength(long arg0)
  {
    super.preForAll(methodObject23142, this, new Object[] { Long.valueOf(arg0) });
    this.delegate.setLength(arg0);
  }
  
  public Date dateValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23183, this, zeroLengthObjectArray);
      return (Date)postForAll(methodObject23183, (Object)this.delegate.dateValue());
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject23183, onErrorForAll(methodObject23183, e));
    }
  }
  
  public int getChunkSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23156, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject23156, Integer.valueOf(this.delegate.getChunkSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23156, onErrorForAll(methodObject23156, e))).intValue();
    }
  }
  
  public void setLocator(byte[] arg0)
  {
    super.preForAll(methodObject23150, this, new Object[] { arg0 });
    this.delegate.setLocator(arg0);
  }
  
  public BigDecimal bigDecimalValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23182, this, zeroLengthObjectArray);
      return (BigDecimal)postForAll(methodObject23182, (Object)this.delegate.bigDecimalValue());
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject23182, onErrorForAll(methodObject23182, e));
    }
  }
  
  public OutputStream setBinaryStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23204, this, new Object[] { Long.valueOf(arg0) });
      return (OutputStream)postForAll(methodObject23204, (Object)this.delegate.setBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject23204, onErrorForAll(methodObject23204, e));
    }
  }
  
  public void setBytes(byte[] arg0)
  {
    super.preForAll(methodObject23176, this, new Object[] { arg0 });
    this.delegate.setBytes(arg0);
  }
  
  public boolean isActivePrefetch()
  {
    super.preForAll(methodObject23166, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject23166, Boolean.valueOf(this.delegate.isActivePrefetch()))).booleanValue();
  }
  
  public void truncate(long arg0)
    throws SQLException
  {
    try
    {
      super.preForBlobWrites(methodObject23207, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.truncate(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23207, e);
    }
  }
  
  public byte byteValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23170, this, zeroLengthObjectArray);
      return ((Byte)postForAll(methodObject23170, Byte.valueOf(this.delegate.byteValue()))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject23170, onErrorForAll(methodObject23170, e))).byteValue();
    }
  }
  
  public Reader characterStreamValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23144, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject23144, (Object)this.delegate.characterStreamValue());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject23144, onErrorForAll(methodObject23144, e));
    }
  }
  
  public float floatValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23172, this, zeroLengthObjectArray);
      return ((Float)postForAll(methodObject23172, Float.valueOf(this.delegate.floatValue()))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject23172, onErrorForAll(methodObject23172, e))).floatValue();
    }
  }
  
  public InputStream getStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23179, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23179, (Object)this.delegate.getStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23179, onErrorForAll(methodObject23179, e));
    }
  }
  
  public void open(LargeObjectAccessMode arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23193, this, new Object[] { arg0 });
      this.delegate.open(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23193, e);
    }
  }
  
  public long getLength()
  {
    super.preForAll(methodObject23167, this, zeroLengthObjectArray);
    return ((Long)postForAll(methodObject23167, Long.valueOf(this.delegate.getLength()))).longValue();
  }
  
  public Time timeValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23184, this, zeroLengthObjectArray);
      return (Time)postForAll(methodObject23184, (Object)this.delegate.timeValue());
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject23184, onErrorForAll(methodObject23184, e));
    }
  }
  
  public oracle.jdbc.driver.OracleConnection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23175, this, zeroLengthObjectArray);
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject23175, (Object)this.delegate.getConnection());
    }
    catch (SQLException e)
    {
      return (oracle.jdbc.driver.OracleConnection)postForAll(methodObject23175, onErrorForAll(methodObject23175, e));
    }
  }
  
  public oracle.jdbc.internal.OracleBlob _getDelegate_()
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
      methodObject23164 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("setActivePrefetch", new Class[] { Boolean.TYPE });
      methodObject23153 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("putBytes", new Class[] { Long.TYPE, byte[].class, Integer.TYPE });
      methodObject23198 = oracle.jdbc.OracleBlob.class.getDeclaredMethod("isTemporary", new Class[0]);
      methodObject23141 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("trim", new Class[] { Long.TYPE });
      methodObject23192 = oracle.jdbc.OracleBlob.class.getDeclaredMethod("close", new Class[0]);
      methodObject23143 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("getJavaSqlConnection", new Class[0]);
      methodObject23187 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[] { Calendar.class });
      methodObject23169 = OracleDatumWithConnection.class.getDeclaredMethod("booleanValue", new Class[0]);
      methodObject23196 = oracle.jdbc.OracleBlob.class.getDeclaredMethod("isEmptyLob", new Class[0]);
      methodObject23199 = Blob.class.getDeclaredMethod("getBytes", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject23186 = OracleDatumWithConnection.class.getDeclaredMethod("timestampValue", new Class[0]);
      methodObject23159 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("setChunkSize", new Class[] { Integer.TYPE });
      methodObject23155 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("getBinaryOutputStream", new Class[] { Long.TYPE });
      methodObject23173 = OracleDatumWithConnection.class.getDeclaredMethod("intValue", new Class[0]);
      methodObject23180 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[0]);
      methodObject23185 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[] { Calendar.class });
      methodObject23197 = oracle.jdbc.OracleBlob.class.getDeclaredMethod("isSecureFile", new Class[0]);
      methodObject23203 = Blob.class.getDeclaredMethod("free", new Class[0]);
      methodObject23145 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("asciiStreamValue", new Class[0]);
      methodObject23177 = OracleDatumWithConnection.class.getDeclaredMethod("shareBytes", new Class[0]);
      methodObject23190 = OracleDatumWithConnection.class.getDeclaredMethod("setPhysicalConnectionOf", new Class[] { Connection.class });
      methodObject23161 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("setPrefetchedData", new Class[] { byte[].class, Integer.TYPE });
      methodObject23171 = OracleDatumWithConnection.class.getDeclaredMethod("doubleValue", new Class[0]);
      methodObject23158 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("getDBAccess", new Class[0]);
      methodObject23147 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("isConvertibleTo", new Class[] { Class.class });
      methodObject23151 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("getLocator", new Class[0]);
      methodObject23165 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("clearCachedData", new Class[0]);
      methodObject23174 = OracleDatumWithConnection.class.getDeclaredMethod("longValue", new Class[0]);
      methodObject23205 = Blob.class.getDeclaredMethod("setBytes", new Class[] { Long.TYPE, byte[].class });
      methodObject23195 = oracle.jdbc.OracleBlob.class.getDeclaredMethod("getBinaryStream", new Class[] { Long.TYPE });
      methodObject23162 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("getPrefetchedData", new Class[0]);
      methodObject23181 = OracleDatumWithConnection.class.getDeclaredMethod("stringValue", new Class[] { Connection.class });
      methodObject23209 = Blob.class.getDeclaredMethod("getBinaryStream", new Class[] { Long.TYPE, Long.TYPE });
      methodObject23201 = Blob.class.getDeclaredMethod("position", new Class[] { byte[].class, Long.TYPE });
      methodObject23200 = Blob.class.getDeclaredMethod("length", new Class[0]);
      methodObject23189 = OracleDatumWithConnection.class.getDeclaredMethod("getInternalConnection", new Class[0]);
      methodObject23208 = Blob.class.getDeclaredMethod("getBinaryStream", new Class[0]);
      methodObject23202 = Blob.class.getDeclaredMethod("position", new Class[] { Blob.class, Long.TYPE });
      methodObject23152 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("putBytes", new Class[] { Long.TYPE, byte[].class });
      methodObject23168 = OracleDatumWithConnection.class.getDeclaredMethod("getBytes", new Class[0]);
      methodObject23163 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("getPrefetchedDataSize", new Class[0]);
      methodObject23146 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("binaryStreamValue", new Class[0]);
      methodObject23154 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("getBinaryOutputStream", new Class[0]);
      methodObject23149 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("makeJdbcArray", new Class[] { Integer.TYPE });
      methodObject23178 = OracleDatumWithConnection.class.getDeclaredMethod("setShareBytes", new Class[] { byte[].class });
      methodObject23188 = OracleDatumWithConnection.class.getDeclaredMethod("getOracleConnection", new Class[0]);
      methodObject23160 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("setPrefetchedData", new Class[] { byte[].class });
      methodObject23148 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("toJdbc", new Class[0]);
      methodObject23157 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("getBufferSize", new Class[0]);
      methodObject23206 = Blob.class.getDeclaredMethod("setBytes", new Class[] { Long.TYPE, byte[].class, Integer.TYPE, Integer.TYPE });
      methodObject23194 = oracle.jdbc.OracleBlob.class.getDeclaredMethod("isOpen", new Class[0]);
      methodObject23191 = oracle.jdbc.OracleBlob.class.getDeclaredMethod("getBytes", new Class[] { Long.TYPE, Integer.TYPE, byte[].class });
      methodObject23142 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("setLength", new Class[] { Long.TYPE });
      methodObject23183 = OracleDatumWithConnection.class.getDeclaredMethod("dateValue", new Class[0]);
      methodObject23156 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("getChunkSize", new Class[0]);
      methodObject23150 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("setLocator", new Class[] { byte[].class });
      methodObject23182 = OracleDatumWithConnection.class.getDeclaredMethod("bigDecimalValue", new Class[0]);
      methodObject23204 = Blob.class.getDeclaredMethod("setBinaryStream", new Class[] { Long.TYPE });
      methodObject23176 = OracleDatumWithConnection.class.getDeclaredMethod("setBytes", new Class[] { byte[].class });
      methodObject23166 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("isActivePrefetch", new Class[0]);
      methodObject23207 = Blob.class.getDeclaredMethod("truncate", new Class[] { Long.TYPE });
      methodObject23170 = OracleDatumWithConnection.class.getDeclaredMethod("byteValue", new Class[0]);
      methodObject23144 = oracle.jdbc.internal.OracleBlob.class.getDeclaredMethod("characterStreamValue", new Class[0]);
      methodObject23172 = OracleDatumWithConnection.class.getDeclaredMethod("floatValue", new Class[0]);
      methodObject23179 = OracleDatumWithConnection.class.getDeclaredMethod("getStream", new Class[0]);
      methodObject23193 = oracle.jdbc.OracleBlob.class.getDeclaredMethod("open", new Class[] { LargeObjectAccessMode.class });
      methodObject23167 = OracleDatumWithConnection.class.getDeclaredMethod("getLength", new Class[0]);
      methodObject23184 = OracleDatumWithConnection.class.getDeclaredMethod("timeValue", new Class[0]);
      methodObject23175 = OracleDatumWithConnection.class.getDeclaredMethod("getConnection", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBlob$2oracle$1jdbc$1internal$1OracleBlob$$$Proxy(oracle.jdbc.internal.OracleBlob paramOracleBlob, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleBlob;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBlob$2oracle$1jdbc$1internal$1OracleBlob$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */