package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableClob;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableClob$2java$1sql$1Clob$$$Proxy
  extends NonTxnReplayableClob
  implements Clob, _Proxy_
{
  private Clob delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject30799;
  private static Method methodObject30792;
  private static Method methodObject30788;
  private static Method methodObject30791;
  private static Method methodObject30797;
  private static Method methodObject30800;
  private static Method methodObject30798;
  private static Method methodObject30789;
  private static Method methodObject30796;
  private static Method methodObject30794;
  private static Method methodObject30795;
  private static Method methodObject30793;
  private static Method methodObject30790;
  
  public InputStream getAsciiStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30799, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject30799, (Object)this.delegate.getAsciiStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject30799, onErrorForAll(methodObject30799, e));
    }
  }
  
  public OutputStream setAsciiStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30792, this, new Object[] { Long.valueOf(arg0) });
      return (OutputStream)postForAll(methodObject30792, (Object)this.delegate.setAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject30792, onErrorForAll(methodObject30792, e));
    }
  }
  
  public long length()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30788, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject30788, Long.valueOf(this.delegate.length()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject30788, onErrorForAll(methodObject30788, e))).longValue();
    }
  }
  
  public void free()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30791, this, zeroLengthObjectArray);
      this.delegate.free();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30791, e);
    }
  }
  
  public Reader getCharacterStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30797, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject30797, (Object)this.delegate.getCharacterStream());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject30797, onErrorForAll(methodObject30797, e));
    }
  }
  
  public String getSubString(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30800, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (String)postForAll(methodObject30800, (Object)this.delegate.getSubString(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject30800, onErrorForAll(methodObject30800, e));
    }
  }
  
  public Reader getCharacterStream(long arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30798, this, new Object[] { Long.valueOf(arg0), Long.valueOf(arg1) });
      return (Reader)postForAll(methodObject30798, (Object)this.delegate.getCharacterStream(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject30798, onErrorForAll(methodObject30798, e));
    }
  }
  
  public long position(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30789, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject30789, Long.valueOf(this.delegate.position(arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject30789, onErrorForAll(methodObject30789, e))).longValue();
    }
  }
  
  public int setString(long arg0, String arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject30796, this, new Object[] { Long.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return ((Integer)postForAll(methodObject30796, Integer.valueOf(this.delegate.setString(arg0, arg1, arg2, arg3)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject30796, onErrorForAll(methodObject30796, e))).intValue();
    }
  }
  
  public void truncate(long arg0)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject30794, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.truncate(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30794, e);
    }
  }
  
  public int setString(long arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject30795, this, new Object[] { Long.valueOf(arg0), arg1 });
      return ((Integer)postForAll(methodObject30795, Integer.valueOf(this.delegate.setString(arg0, arg1)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject30795, onErrorForAll(methodObject30795, e))).intValue();
    }
  }
  
  public Writer setCharacterStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30793, this, new Object[] { Long.valueOf(arg0) });
      return (Writer)postForAll(methodObject30793, (Object)this.delegate.setCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Writer)postForAll(methodObject30793, onErrorForAll(methodObject30793, e));
    }
  }
  
  public long position(Clob arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30790, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject30790, Long.valueOf(this.delegate.position((arg0 instanceof _Proxy_) ? (Clob)((_Proxy_)arg0)._getDelegate_() : arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject30790, onErrorForAll(methodObject30790, e))).longValue();
    }
  }
  
  public Clob _getDelegate_()
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
      methodObject30799 = Clob.class.getDeclaredMethod("getAsciiStream", new Class[0]);
      methodObject30792 = Clob.class.getDeclaredMethod("setAsciiStream", new Class[] { Long.TYPE });
      methodObject30788 = Clob.class.getDeclaredMethod("length", new Class[0]);
      methodObject30791 = Clob.class.getDeclaredMethod("free", new Class[0]);
      methodObject30797 = Clob.class.getDeclaredMethod("getCharacterStream", new Class[0]);
      methodObject30800 = Clob.class.getDeclaredMethod("getSubString", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject30798 = Clob.class.getDeclaredMethod("getCharacterStream", new Class[] { Long.TYPE, Long.TYPE });
      methodObject30789 = Clob.class.getDeclaredMethod("position", new Class[] { String.class, Long.TYPE });
      methodObject30796 = Clob.class.getDeclaredMethod("setString", new Class[] { Long.TYPE, String.class, Integer.TYPE, Integer.TYPE });
      methodObject30794 = Clob.class.getDeclaredMethod("truncate", new Class[] { Long.TYPE });
      methodObject30795 = Clob.class.getDeclaredMethod("setString", new Class[] { Long.TYPE, String.class });
      methodObject30793 = Clob.class.getDeclaredMethod("setCharacterStream", new Class[] { Long.TYPE });
      methodObject30790 = Clob.class.getDeclaredMethod("position", new Class[] { Clob.class, Long.TYPE });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableClob$2java$1sql$1Clob$$$Proxy(Clob paramClob, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramClob;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableClob$2java$1sql$1Clob$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */