package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableNClob;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableNClob$2java$1sql$1NClob$$$Proxy
  extends NonTxnReplayableNClob
  implements NClob, _Proxy_
{
  private NClob delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject23885;
  private static Method methodObject23878;
  private static Method methodObject23874;
  private static Method methodObject23877;
  private static Method methodObject23883;
  private static Method methodObject23886;
  private static Method methodObject23884;
  private static Method methodObject23875;
  private static Method methodObject23882;
  private static Method methodObject23880;
  private static Method methodObject23881;
  private static Method methodObject23879;
  private static Method methodObject23876;
  
  public InputStream getAsciiStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23885, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23885, (Object)this.delegate.getAsciiStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23885, onErrorForAll(methodObject23885, e));
    }
  }
  
  public OutputStream setAsciiStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23878, this, new Object[] { Long.valueOf(arg0) });
      return (OutputStream)postForAll(methodObject23878, (Object)this.delegate.setAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject23878, onErrorForAll(methodObject23878, e));
    }
  }
  
  public long length()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23874, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject23874, Long.valueOf(this.delegate.length()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23874, onErrorForAll(methodObject23874, e))).longValue();
    }
  }
  
  public void free()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23877, this, zeroLengthObjectArray);
      this.delegate.free();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23877, e);
    }
  }
  
  public Reader getCharacterStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23883, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject23883, (Object)this.delegate.getCharacterStream());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject23883, onErrorForAll(methodObject23883, e));
    }
  }
  
  public String getSubString(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23886, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (String)postForAll(methodObject23886, (Object)this.delegate.getSubString(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject23886, onErrorForAll(methodObject23886, e));
    }
  }
  
  public Reader getCharacterStream(long arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23884, this, new Object[] { Long.valueOf(arg0), Long.valueOf(arg1) });
      return (Reader)postForAll(methodObject23884, (Object)this.delegate.getCharacterStream(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject23884, onErrorForAll(methodObject23884, e));
    }
  }
  
  public long position(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23875, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject23875, Long.valueOf(this.delegate.position(arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23875, onErrorForAll(methodObject23875, e))).longValue();
    }
  }
  
  public int setString(long arg0, String arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject23882, this, new Object[] { Long.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return ((Integer)postForAll(methodObject23882, Integer.valueOf(this.delegate.setString(arg0, arg1, arg2, arg3)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23882, onErrorForAll(methodObject23882, e))).intValue();
    }
  }
  
  public void truncate(long arg0)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject23880, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.truncate(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23880, e);
    }
  }
  
  public int setString(long arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject23881, this, new Object[] { Long.valueOf(arg0), arg1 });
      return ((Integer)postForAll(methodObject23881, Integer.valueOf(this.delegate.setString(arg0, arg1)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23881, onErrorForAll(methodObject23881, e))).intValue();
    }
  }
  
  public Writer setCharacterStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23879, this, new Object[] { Long.valueOf(arg0) });
      return (Writer)postForAll(methodObject23879, (Object)this.delegate.setCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Writer)postForAll(methodObject23879, onErrorForAll(methodObject23879, e));
    }
  }
  
  public long position(Clob arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23876, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject23876, Long.valueOf(this.delegate.position((arg0 instanceof _Proxy_) ? (Clob)((_Proxy_)arg0)._getDelegate_() : arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23876, onErrorForAll(methodObject23876, e))).longValue();
    }
  }
  
  public NClob _getDelegate_()
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
      methodObject23885 = Clob.class.getDeclaredMethod("getAsciiStream", new Class[0]);
      methodObject23878 = Clob.class.getDeclaredMethod("setAsciiStream", new Class[] { Long.TYPE });
      methodObject23874 = Clob.class.getDeclaredMethod("length", new Class[0]);
      methodObject23877 = Clob.class.getDeclaredMethod("free", new Class[0]);
      methodObject23883 = Clob.class.getDeclaredMethod("getCharacterStream", new Class[0]);
      methodObject23886 = Clob.class.getDeclaredMethod("getSubString", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject23884 = Clob.class.getDeclaredMethod("getCharacterStream", new Class[] { Long.TYPE, Long.TYPE });
      methodObject23875 = Clob.class.getDeclaredMethod("position", new Class[] { String.class, Long.TYPE });
      methodObject23882 = Clob.class.getDeclaredMethod("setString", new Class[] { Long.TYPE, String.class, Integer.TYPE, Integer.TYPE });
      methodObject23880 = Clob.class.getDeclaredMethod("truncate", new Class[] { Long.TYPE });
      methodObject23881 = Clob.class.getDeclaredMethod("setString", new Class[] { Long.TYPE, String.class });
      methodObject23879 = Clob.class.getDeclaredMethod("setCharacterStream", new Class[] { Long.TYPE });
      methodObject23876 = Clob.class.getDeclaredMethod("position", new Class[] { Clob.class, Long.TYPE });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableNClob$2java$1sql$1NClob$$$Proxy(NClob paramNClob, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramNClob;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableNClob$2java$1sql$1NClob$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */