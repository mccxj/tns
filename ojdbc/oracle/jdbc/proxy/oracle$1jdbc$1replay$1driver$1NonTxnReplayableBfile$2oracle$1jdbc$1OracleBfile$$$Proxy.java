package oracle.jdbc.proxy;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.LargeObjectAccessMode;
import oracle.jdbc.OracleBfile;
import oracle.jdbc.replay.driver.NonTxnReplayableBfile;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBfile$2oracle$1jdbc$1OracleBfile$$$Proxy
  extends NonTxnReplayableBfile
  implements OracleBfile, _Proxy_
{
  private OracleBfile delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject10929;
  private static Method methodObject10930;
  private static Method methodObject10928;
  private static Method methodObject10934;
  private static Method methodObject10935;
  private static Method methodObject10938;
  private static Method methodObject10939;
  private static Method methodObject10940;
  private static Method methodObject10932;
  private static Method methodObject10941;
  private static Method methodObject10927;
  private static Method methodObject10936;
  private static Method methodObject10931;
  private static Method methodObject10926;
  private static Method methodObject10933;
  private static Method methodObject10937;
  
  public long length()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10929, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject10929, Long.valueOf(this.delegate.length()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject10929, onErrorForAll(methodObject10929, e))).longValue();
    }
  }
  
  public long position(byte[] arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10930, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject10930, Long.valueOf(this.delegate.position(arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject10930, onErrorForAll(methodObject10930, e))).longValue();
    }
  }
  
  public int getBytes(long arg0, int arg1, byte[] arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10928, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      return ((Integer)postForAll(methodObject10928, Integer.valueOf(this.delegate.getBytes(arg0, arg1, arg2)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject10928, onErrorForAll(methodObject10928, e))).intValue();
    }
  }
  
  public boolean isOpen()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10934, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject10934, Boolean.valueOf(this.delegate.isOpen()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject10934, onErrorForAll(methodObject10934, e))).booleanValue();
    }
  }
  
  public InputStream getBinaryStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10935, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject10935, (Object)this.delegate.getBinaryStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject10935, onErrorForAll(methodObject10935, e));
    }
  }
  
  public void openFile()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10938, this, zeroLengthObjectArray);
      this.delegate.openFile();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject10938, e);
    }
  }
  
  public boolean isFileOpen()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10939, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject10939, Boolean.valueOf(this.delegate.isFileOpen()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject10939, onErrorForAll(methodObject10939, e))).booleanValue();
    }
  }
  
  public boolean fileExists()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10940, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject10940, Boolean.valueOf(this.delegate.fileExists()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject10940, onErrorForAll(methodObject10940, e))).booleanValue();
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10932, this, zeroLengthObjectArray);
      this.delegate.close();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject10932, e);
    }
  }
  
  public void closeFile()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10941, this, zeroLengthObjectArray);
      this.delegate.closeFile();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject10941, e);
    }
  }
  
  public byte[] getBytes(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10927, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (byte[])postForAll(methodObject10927, (Object)this.delegate.getBytes(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject10927, onErrorForAll(methodObject10927, e));
    }
  }
  
  public InputStream getBinaryStream(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10936, this, new Object[] { Long.valueOf(arg0) });
      return (InputStream)postForAll(methodObject10936, (Object)this.delegate.getBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject10936, onErrorForAll(methodObject10936, e));
    }
  }
  
  public long position(OracleBfile arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10931, this, new Object[] { arg0, Long.valueOf(arg1) });
      return ((Long)postForAll(methodObject10931, Long.valueOf(this.delegate.position((arg0 instanceof _Proxy_) ? (OracleBfile)((_Proxy_)arg0)._getDelegate_() : arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject10931, onErrorForAll(methodObject10931, e))).longValue();
    }
  }
  
  public String getName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10926, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject10926, (Object)this.delegate.getName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject10926, onErrorForAll(methodObject10926, e));
    }
  }
  
  public void open(LargeObjectAccessMode arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10933, this, new Object[] { arg0 });
      this.delegate.open(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject10933, e);
    }
  }
  
  public String getDirAlias()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject10937, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject10937, (Object)this.delegate.getDirAlias());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject10937, onErrorForAll(methodObject10937, e));
    }
  }
  
  public OracleBfile _getDelegate_()
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
      methodObject10929 = OracleBfile.class.getDeclaredMethod("length", new Class[0]);
      methodObject10930 = OracleBfile.class.getDeclaredMethod("position", new Class[] { byte[].class, Long.TYPE });
      methodObject10928 = OracleBfile.class.getDeclaredMethod("getBytes", new Class[] { Long.TYPE, Integer.TYPE, byte[].class });
      methodObject10934 = OracleBfile.class.getDeclaredMethod("isOpen", new Class[0]);
      methodObject10935 = OracleBfile.class.getDeclaredMethod("getBinaryStream", new Class[0]);
      methodObject10938 = OracleBfile.class.getDeclaredMethod("openFile", new Class[0]);
      methodObject10939 = OracleBfile.class.getDeclaredMethod("isFileOpen", new Class[0]);
      methodObject10940 = OracleBfile.class.getDeclaredMethod("fileExists", new Class[0]);
      methodObject10932 = OracleBfile.class.getDeclaredMethod("close", new Class[0]);
      methodObject10941 = OracleBfile.class.getDeclaredMethod("closeFile", new Class[0]);
      methodObject10927 = OracleBfile.class.getDeclaredMethod("getBytes", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject10936 = OracleBfile.class.getDeclaredMethod("getBinaryStream", new Class[] { Long.TYPE });
      methodObject10931 = OracleBfile.class.getDeclaredMethod("position", new Class[] { OracleBfile.class, Long.TYPE });
      methodObject10926 = OracleBfile.class.getDeclaredMethod("getName", new Class[0]);
      methodObject10933 = OracleBfile.class.getDeclaredMethod("open", new Class[] { LargeObjectAccessMode.class });
      methodObject10937 = OracleBfile.class.getDeclaredMethod("getDirAlias", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBfile$2oracle$1jdbc$1OracleBfile$$$Proxy(OracleBfile paramOracleBfile, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleBfile;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBfile$2oracle$1jdbc$1OracleBfile$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */