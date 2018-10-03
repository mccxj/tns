package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.LargeObjectAccessMode;
import oracle.jdbc.OracleClob;
import oracle.jdbc.replay.driver.NonTxnReplayableClob;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableClob$2oracle$1jdbc$1OracleClob$$$Proxy
  extends NonTxnReplayableClob
  implements OracleClob, _Proxy_
{
  private OracleClob delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject30818;
  private static Method methodObject30811;
  private static Method methodObject30805;
  private static Method methodObject30807;
  private static Method methodObject30810;
  private static Method methodObject30803;
  private static Method methodObject30806;
  private static Method methodObject30816;
  private static Method methodObject30819;
  private static Method methodObject30801;
  private static Method methodObject30817;
  private static Method methodObject30808;
  private static Method methodObject30815;
  private static Method methodObject30804;
  private static Method methodObject30814;
  private static Method methodObject30813;
  private static Method methodObject30812;
  private static Method methodObject30802;
  private static Method methodObject30809;
  
  public InputStream getAsciiStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30818, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject30818, (Object)this.delegate.getAsciiStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject30818, onErrorForAll(methodObject30818, e));
    }
  }
  
  public OutputStream setAsciiStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30811, this, new Object[] { Long.valueOf(arg0) });
      return (OutputStream)postForAll(methodObject30811, (Object)this.delegate.setAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject30811, onErrorForAll(methodObject30811, e));
    }
  }
  
  public boolean isSecureFile()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30805, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject30805, Boolean.valueOf(this.delegate.isSecureFile()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject30805, onErrorForAll(methodObject30805, e))).booleanValue();
    }
  }
  
  public long length()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30807, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject30807, Long.valueOf(this.delegate.length()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject30807, onErrorForAll(methodObject30807, e))).longValue();
    }
  }
  
  public void free()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30810, this, zeroLengthObjectArray);
      this.delegate.free();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30810, e);
    }
  }
  
  public boolean isOpen()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30803, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject30803, Boolean.valueOf(this.delegate.isOpen()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject30803, onErrorForAll(methodObject30803, e))).booleanValue();
    }
  }
  
  public boolean isTemporary()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30806, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject30806, Boolean.valueOf(this.delegate.isTemporary()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject30806, onErrorForAll(methodObject30806, e))).booleanValue();
    }
  }
  
  public Reader getCharacterStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30816, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject30816, (Object)this.delegate.getCharacterStream());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject30816, onErrorForAll(methodObject30816, e));
    }
  }
  
  public String getSubString(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30819, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (String)postForAll(methodObject30819, (Object)this.delegate.getSubString(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject30819, onErrorForAll(methodObject30819, e));
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30801, this, zeroLengthObjectArray);
      this.delegate.close();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30801, e);
    }
  }
  
  public Reader getCharacterStream(long arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30817, this, new Object[] { Long.valueOf(arg0), Long.valueOf(arg1) });
      return (Reader)postForAll(methodObject30817, (Object)this.delegate.getCharacterStream(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject30817, onErrorForAll(methodObject30817, e));
    }
  }
  
  public long position(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30808, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject30808, Long.valueOf(this.delegate.position(arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject30808, onErrorForAll(methodObject30808, e))).longValue();
    }
  }
  
  public int setString(long arg0, String arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject30815, this, new Object[] { Long.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return ((Integer)postForAll(methodObject30815, Integer.valueOf(this.delegate.setString(arg0, arg1, arg2, arg3)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject30815, onErrorForAll(methodObject30815, e))).intValue();
    }
  }
  
  public boolean isEmptyLob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30804, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject30804, Boolean.valueOf(this.delegate.isEmptyLob()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject30804, onErrorForAll(methodObject30804, e))).booleanValue();
    }
  }
  
  public int setString(long arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject30814, this, new Object[] { Long.valueOf(arg0), arg1 });
      return ((Integer)postForAll(methodObject30814, Integer.valueOf(this.delegate.setString(arg0, arg1)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject30814, onErrorForAll(methodObject30814, e))).intValue();
    }
  }
  
  public void truncate(long arg0)
    throws SQLException
  {
    try
    {
      super.preForClobWrites(methodObject30813, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.truncate(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30813, e);
    }
  }
  
  public Writer setCharacterStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30812, this, new Object[] { Long.valueOf(arg0) });
      return (Writer)postForAll(methodObject30812, (Object)this.delegate.setCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Writer)postForAll(methodObject30812, onErrorForAll(methodObject30812, e));
    }
  }
  
  public void open(LargeObjectAccessMode arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30802, this, new Object[] { arg0 });
      this.delegate.open(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30802, e);
    }
  }
  
  public long position(Clob arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30809, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject30809, Long.valueOf(this.delegate.position((arg0 instanceof _Proxy_) ? (Clob)((_Proxy_)arg0)._getDelegate_() : arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject30809, onErrorForAll(methodObject30809, e))).longValue();
    }
  }
  
  public OracleClob _getDelegate_()
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
      methodObject30818 = Clob.class.getDeclaredMethod("getAsciiStream", new Class[0]);
      methodObject30811 = Clob.class.getDeclaredMethod("setAsciiStream", new Class[] { Long.TYPE });
      methodObject30805 = OracleClob.class.getDeclaredMethod("isSecureFile", new Class[0]);
      methodObject30807 = Clob.class.getDeclaredMethod("length", new Class[0]);
      methodObject30810 = Clob.class.getDeclaredMethod("free", new Class[0]);
      methodObject30803 = OracleClob.class.getDeclaredMethod("isOpen", new Class[0]);
      methodObject30806 = OracleClob.class.getDeclaredMethod("isTemporary", new Class[0]);
      methodObject30816 = Clob.class.getDeclaredMethod("getCharacterStream", new Class[0]);
      methodObject30819 = Clob.class.getDeclaredMethod("getSubString", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject30801 = OracleClob.class.getDeclaredMethod("close", new Class[0]);
      methodObject30817 = Clob.class.getDeclaredMethod("getCharacterStream", new Class[] { Long.TYPE, Long.TYPE });
      methodObject30808 = Clob.class.getDeclaredMethod("position", new Class[] { String.class, Long.TYPE });
      methodObject30815 = Clob.class.getDeclaredMethod("setString", new Class[] { Long.TYPE, String.class, Integer.TYPE, Integer.TYPE });
      methodObject30804 = OracleClob.class.getDeclaredMethod("isEmptyLob", new Class[0]);
      methodObject30814 = Clob.class.getDeclaredMethod("setString", new Class[] { Long.TYPE, String.class });
      methodObject30813 = Clob.class.getDeclaredMethod("truncate", new Class[] { Long.TYPE });
      methodObject30812 = Clob.class.getDeclaredMethod("setCharacterStream", new Class[] { Long.TYPE });
      methodObject30802 = OracleClob.class.getDeclaredMethod("open", new Class[] { LargeObjectAccessMode.class });
      methodObject30809 = Clob.class.getDeclaredMethod("position", new Class[] { Clob.class, Long.TYPE });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableClob$2oracle$1jdbc$1OracleClob$$$Proxy(OracleClob paramOracleClob, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleClob;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableClob$2oracle$1jdbc$1OracleClob$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */