package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.OracleNClob;
import oracle.jdbc.replay.driver.NonTxnReplayableNClob;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableNClob$2oracle$1jdbc$1OracleNClob$$$Proxy
  extends NonTxnReplayableNClob
  implements OracleNClob, _Proxy_
{
  private OracleNClob delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject23898;
  private static Method methodObject23891;
  private static Method methodObject23887;
  private static Method methodObject23890;
  private static Method methodObject23896;
  private static Method methodObject23899;
  private static Method methodObject23897;
  private static Method methodObject23888;
  private static Method methodObject23895;
  private static Method methodObject23893;
  private static Method methodObject23894;
  private static Method methodObject23892;
  private static Method methodObject23889;
  
  public InputStream getAsciiStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23898, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject23898, (Object)this.delegate.getAsciiStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject23898, onErrorForAll(methodObject23898, e));
    }
  }
  
  public OutputStream setAsciiStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23891, this, new Object[] { Long.valueOf(arg0) });
      return (OutputStream)postForAll(methodObject23891, (Object)this.delegate.setAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject23891, onErrorForAll(methodObject23891, e));
    }
  }
  
  public long length()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23887, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject23887, Long.valueOf(this.delegate.length()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23887, onErrorForAll(methodObject23887, e))).longValue();
    }
  }
  
  public void free()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23890, this, zeroLengthObjectArray);
      this.delegate.free();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23890, e);
    }
  }
  
  public Reader getCharacterStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23896, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject23896, (Object)this.delegate.getCharacterStream());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject23896, onErrorForAll(methodObject23896, e));
    }
  }
  
  public String getSubString(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23899, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (String)postForAll(methodObject23899, (Object)this.delegate.getSubString(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject23899, onErrorForAll(methodObject23899, e));
    }
  }
  
  public Reader getCharacterStream(long arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23897, this, new Object[] { Long.valueOf(arg0), Long.valueOf(arg1) });
      return (Reader)postForAll(methodObject23897, (Object)this.delegate.getCharacterStream(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject23897, onErrorForAll(methodObject23897, e));
    }
  }
  
  public long position(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23888, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject23888, Long.valueOf(this.delegate.position(arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23888, onErrorForAll(methodObject23888, e))).longValue();
    }
  }
  
  public int setString(long arg0, String arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject23895, this, new Object[] { Long.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return ((Integer)postForAll(methodObject23895, Integer.valueOf(this.delegate.setString(arg0, arg1, arg2, arg3)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23895, onErrorForAll(methodObject23895, e))).intValue();
    }
  }
  
  public void truncate(long arg0)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject23893, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.truncate(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject23893, e);
    }
  }
  
  public int setString(long arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject23894, this, new Object[] { Long.valueOf(arg0), arg1 });
      return ((Integer)postForAll(methodObject23894, Integer.valueOf(this.delegate.setString(arg0, arg1)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23894, onErrorForAll(methodObject23894, e))).intValue();
    }
  }
  
  public Writer setCharacterStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23892, this, new Object[] { Long.valueOf(arg0) });
      return (Writer)postForAll(methodObject23892, (Object)this.delegate.setCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Writer)postForAll(methodObject23892, onErrorForAll(methodObject23892, e));
    }
  }
  
  public long position(Clob arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23889, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject23889, Long.valueOf(this.delegate.position((arg0 instanceof _Proxy_) ? (Clob)((_Proxy_)arg0)._getDelegate_() : arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23889, onErrorForAll(methodObject23889, e))).longValue();
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
      methodObject23898 = Clob.class.getDeclaredMethod("getAsciiStream", new Class[0]);
      methodObject23891 = Clob.class.getDeclaredMethod("setAsciiStream", new Class[] { Long.TYPE });
      methodObject23887 = Clob.class.getDeclaredMethod("length", new Class[0]);
      methodObject23890 = Clob.class.getDeclaredMethod("free", new Class[0]);
      methodObject23896 = Clob.class.getDeclaredMethod("getCharacterStream", new Class[0]);
      methodObject23899 = Clob.class.getDeclaredMethod("getSubString", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject23897 = Clob.class.getDeclaredMethod("getCharacterStream", new Class[] { Long.TYPE, Long.TYPE });
      methodObject23888 = Clob.class.getDeclaredMethod("position", new Class[] { String.class, Long.TYPE });
      methodObject23895 = Clob.class.getDeclaredMethod("setString", new Class[] { Long.TYPE, String.class, Integer.TYPE, Integer.TYPE });
      methodObject23893 = Clob.class.getDeclaredMethod("truncate", new Class[] { Long.TYPE });
      methodObject23894 = Clob.class.getDeclaredMethod("setString", new Class[] { Long.TYPE, String.class });
      methodObject23892 = Clob.class.getDeclaredMethod("setCharacterStream", new Class[] { Long.TYPE });
      methodObject23889 = Clob.class.getDeclaredMethod("position", new Class[] { Clob.class, Long.TYPE });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableNClob$2oracle$1jdbc$1OracleNClob$$$Proxy(OracleNClob paramOracleNClob, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleNClob;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableNClob$2oracle$1jdbc$1OracleNClob$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */