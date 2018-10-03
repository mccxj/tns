package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.LargeObjectAccessMode;
import oracle.jdbc.OracleBlob;
import oracle.jdbc.replay.driver.NonTxnReplayableBlob;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBlob$2oracle$1jdbc$1OracleBlob$$$Proxy
  extends NonTxnReplayableBlob
  implements OracleBlob, _Proxy_
{
  private OracleBlob delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject23140;
  private static Method methodObject23128;
  private static Method methodObject23131;
  private static Method methodObject23132;
  private static Method methodObject23134;
  private static Method methodObject23137;
  private static Method methodObject23122;
  private static Method methodObject23125;
  private static Method methodObject23139;
  private static Method methodObject23129;
  private static Method methodObject23133;
  private static Method methodObject23135;
  private static Method methodObject23123;
  private static Method methodObject23127;
  private static Method methodObject23138;
  private static Method methodObject23136;
  private static Method methodObject23130;
  private static Method methodObject23126;
  private static Method methodObject23124;
  
  public InputStream getBinaryStream(long arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23140, this, new Object[] { Long.valueOf(arg0), Long.valueOf(arg1) });
      return (InputStream)postForAll(methodObject23140, (Object)this.delegate.getBinaryStream(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23140, onErrorForAll(methodObject23140, e));
    }
  }
  
  public boolean isSecureFile()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23128, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23128, Boolean.valueOf(this.delegate.isSecureFile()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23128, onErrorForAll(methodObject23128, e))).booleanValue();
    }
  }
  
  public long length()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23131, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject23131, Long.valueOf(this.delegate.length()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23131, onErrorForAll(methodObject23131, e))).longValue();
    }
  }
  
  public long position(byte[] arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23132, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject23132, Long.valueOf(this.delegate.position(arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23132, onErrorForAll(methodObject23132, e))).longValue();
    }
  }
  
  public void free()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23134, this, zeroLengthObjectArray);
      this.delegate.free();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23134, e);
    }
  }
  
  public int setBytes(long arg0, byte[] arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForBlobWrites(methodObject23137, this, new Object[] { Long.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return ((Integer)postForAll(methodObject23137, Integer.valueOf(this.delegate.setBytes(arg0, arg1, arg2, arg3)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23137, onErrorForAll(methodObject23137, e))).intValue();
    }
  }
  
  public int getBytes(long arg0, int arg1, byte[] arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23122, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      return ((Integer)postForAll(methodObject23122, Integer.valueOf(this.delegate.getBytes(arg0, arg1, arg2)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23122, onErrorForAll(methodObject23122, e))).intValue();
    }
  }
  
  public boolean isOpen()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23125, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23125, Boolean.valueOf(this.delegate.isOpen()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23125, onErrorForAll(methodObject23125, e))).booleanValue();
    }
  }
  
  public InputStream getBinaryStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23139, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23139, (Object)this.delegate.getBinaryStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23139, onErrorForAll(methodObject23139, e));
    }
  }
  
  public boolean isTemporary()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23129, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23129, Boolean.valueOf(this.delegate.isTemporary()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23129, onErrorForAll(methodObject23129, e))).booleanValue();
    }
  }
  
  public long position(Blob arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23133, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject23133, Long.valueOf(this.delegate.position((arg0 instanceof _Proxy_) ? (Blob)((_Proxy_)arg0)._getDelegate_() : arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23133, onErrorForAll(methodObject23133, e))).longValue();
    }
  }
  
  public OutputStream setBinaryStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23135, this, new Object[] { Long.valueOf(arg0) });
      return (OutputStream)postForAll(methodObject23135, (Object)this.delegate.setBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject23135, onErrorForAll(methodObject23135, e));
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23123, this, zeroLengthObjectArray);
      this.delegate.close();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23123, e);
    }
  }
  
  public boolean isEmptyLob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23127, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject23127, Boolean.valueOf(this.delegate.isEmptyLob()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23127, onErrorForAll(methodObject23127, e))).booleanValue();
    }
  }
  
  public void truncate(long arg0)
    throws SQLException
  {
    try
    {
      super.preForBlobWrites(methodObject23138, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.truncate(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23138, e);
    }
  }
  
  public int setBytes(long arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForBlobWrites(methodObject23136, this, new Object[] { Long.valueOf(arg0), arg1 });
      return ((Integer)postForAll(methodObject23136, Integer.valueOf(this.delegate.setBytes(arg0, arg1)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23136, onErrorForAll(methodObject23136, e))).intValue();
    }
  }
  
  public byte[] getBytes(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23130, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (byte[])postForAll(methodObject23130, (Object)this.delegate.getBytes(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject23130, onErrorForAll(methodObject23130, e));
    }
  }
  
  public InputStream getBinaryStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23126, this, new Object[] { Long.valueOf(arg0) });
      return (InputStream)postForAll(methodObject23126, (Object)this.delegate.getBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23126, onErrorForAll(methodObject23126, e));
    }
  }
  
  public void open(LargeObjectAccessMode arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23124, this, new Object[] { arg0 });
      this.delegate.open(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23124, e);
    }
  }
  
  public OracleBlob _getDelegate_()
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
      methodObject23140 = Blob.class.getDeclaredMethod("getBinaryStream", new Class[] { Long.TYPE, Long.TYPE });
      methodObject23128 = OracleBlob.class.getDeclaredMethod("isSecureFile", new Class[0]);
      methodObject23131 = Blob.class.getDeclaredMethod("length", new Class[0]);
      methodObject23132 = Blob.class.getDeclaredMethod("position", new Class[] { byte[].class, Long.TYPE });
      methodObject23134 = Blob.class.getDeclaredMethod("free", new Class[0]);
      methodObject23137 = Blob.class.getDeclaredMethod("setBytes", new Class[] { Long.TYPE, byte[].class, Integer.TYPE, Integer.TYPE });
      methodObject23122 = OracleBlob.class.getDeclaredMethod("getBytes", new Class[] { Long.TYPE, Integer.TYPE, byte[].class });
      methodObject23125 = OracleBlob.class.getDeclaredMethod("isOpen", new Class[0]);
      methodObject23139 = Blob.class.getDeclaredMethod("getBinaryStream", new Class[0]);
      methodObject23129 = OracleBlob.class.getDeclaredMethod("isTemporary", new Class[0]);
      methodObject23133 = Blob.class.getDeclaredMethod("position", new Class[] { Blob.class, Long.TYPE });
      methodObject23135 = Blob.class.getDeclaredMethod("setBinaryStream", new Class[] { Long.TYPE });
      methodObject23123 = OracleBlob.class.getDeclaredMethod("close", new Class[0]);
      methodObject23127 = OracleBlob.class.getDeclaredMethod("isEmptyLob", new Class[0]);
      methodObject23138 = Blob.class.getDeclaredMethod("truncate", new Class[] { Long.TYPE });
      methodObject23136 = Blob.class.getDeclaredMethod("setBytes", new Class[] { Long.TYPE, byte[].class });
      methodObject23130 = Blob.class.getDeclaredMethod("getBytes", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject23126 = OracleBlob.class.getDeclaredMethod("getBinaryStream", new Class[] { Long.TYPE });
      methodObject23124 = OracleBlob.class.getDeclaredMethod("open", new Class[] { LargeObjectAccessMode.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBlob$2oracle$1jdbc$1OracleBlob$$$Proxy(OracleBlob paramOracleBlob, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleBlob;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBlob$2oracle$1jdbc$1OracleBlob$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */