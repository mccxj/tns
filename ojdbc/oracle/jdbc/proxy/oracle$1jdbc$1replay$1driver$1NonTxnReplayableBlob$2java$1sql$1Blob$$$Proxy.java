package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableBlob;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBlob$2java$1sql$1Blob$$$Proxy
  extends NonTxnReplayableBlob
  implements Blob, _Proxy_
{
  private Blob delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject23121;
  private static Method methodObject23115;
  private static Method methodObject23113;
  private static Method methodObject23112;
  private static Method methodObject23118;
  private static Method methodObject23119;
  private static Method methodObject23117;
  private static Method methodObject23120;
  private static Method methodObject23114;
  private static Method methodObject23111;
  private static Method methodObject23116;
  
  public InputStream getBinaryStream(long arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23121, this, new Object[] { Long.valueOf(arg0), Long.valueOf(arg1) });
      return (InputStream)postForAll(methodObject23121, (Object)this.delegate.getBinaryStream(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23121, onErrorForAll(methodObject23121, e));
    }
  }
  
  public void free()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23115, this, zeroLengthObjectArray);
      this.delegate.free();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23115, e);
    }
  }
  
  public long position(byte[] arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23113, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject23113, Long.valueOf(this.delegate.position(arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23113, onErrorForAll(methodObject23113, e))).longValue();
    }
  }
  
  public long length()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23112, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject23112, Long.valueOf(this.delegate.length()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23112, onErrorForAll(methodObject23112, e))).longValue();
    }
  }
  
  public int setBytes(long arg0, byte[] arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForBlobWrites(methodObject23118, this, new Object[] { Long.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return ((Integer)postForAll(methodObject23118, Integer.valueOf(this.delegate.setBytes(arg0, arg1, arg2, arg3)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23118, onErrorForAll(methodObject23118, e))).intValue();
    }
  }
  
  public void truncate(long arg0)
    throws SQLException
  {
    try
    {
      super.preForBlobWrites(methodObject23119, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.truncate(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23119, e);
    }
  }
  
  public int setBytes(long arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForBlobWrites(methodObject23117, this, new Object[] { Long.valueOf(arg0), arg1 });
      return ((Integer)postForAll(methodObject23117, Integer.valueOf(this.delegate.setBytes(arg0, arg1)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23117, onErrorForAll(methodObject23117, e))).intValue();
    }
  }
  
  public InputStream getBinaryStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23120, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23120, (Object)this.delegate.getBinaryStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23120, onErrorForAll(methodObject23120, e));
    }
  }
  
  public long position(Blob arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23114, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject23114, Long.valueOf(this.delegate.position((arg0 instanceof _Proxy_) ? (Blob)((_Proxy_)arg0)._getDelegate_() : arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23114, onErrorForAll(methodObject23114, e))).longValue();
    }
  }
  
  public byte[] getBytes(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23111, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (byte[])postForAll(methodObject23111, (Object)this.delegate.getBytes(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject23111, onErrorForAll(methodObject23111, e));
    }
  }
  
  public OutputStream setBinaryStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23116, this, new Object[] { Long.valueOf(arg0) });
      return (OutputStream)postForAll(methodObject23116, (Object)this.delegate.setBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject23116, onErrorForAll(methodObject23116, e));
    }
  }
  
  public Blob _getDelegate_()
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
      methodObject23121 = Blob.class.getDeclaredMethod("getBinaryStream", new Class[] { Long.TYPE, Long.TYPE });
      methodObject23115 = Blob.class.getDeclaredMethod("free", new Class[0]);
      methodObject23113 = Blob.class.getDeclaredMethod("position", new Class[] { byte[].class, Long.TYPE });
      methodObject23112 = Blob.class.getDeclaredMethod("length", new Class[0]);
      methodObject23118 = Blob.class.getDeclaredMethod("setBytes", new Class[] { Long.TYPE, byte[].class, Integer.TYPE, Integer.TYPE });
      methodObject23119 = Blob.class.getDeclaredMethod("truncate", new Class[] { Long.TYPE });
      methodObject23117 = Blob.class.getDeclaredMethod("setBytes", new Class[] { Long.TYPE, byte[].class });
      methodObject23120 = Blob.class.getDeclaredMethod("getBinaryStream", new Class[0]);
      methodObject23114 = Blob.class.getDeclaredMethod("position", new Class[] { Blob.class, Long.TYPE });
      methodObject23111 = Blob.class.getDeclaredMethod("getBytes", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject23116 = Blob.class.getDeclaredMethod("setBinaryStream", new Class[] { Long.TYPE });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBlob$2java$1sql$1Blob$$$Proxy(Blob paramBlob, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramBlob;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBlob$2java$1sql$1Blob$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */