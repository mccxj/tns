package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.Map;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1SQLXML$$$Proxy
  extends NonTxnReplayableBase
  implements SQLXML, _Proxy_
{
  private SQLXML delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32341;
  private static Method methodObject32343;
  private static Method methodObject32340;
  private static Method methodObject32344;
  private static Method methodObject32345;
  private static Method methodObject32342;
  private static Method methodObject32346;
  private static Method methodObject32339;
  private static Method methodObject32338;
  
  public OutputStream setBinaryStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32341, this, zeroLengthObjectArray);
      return (OutputStream)postForAll(methodObject32341, (Object)this.delegate.setBinaryStream());
    }
    catch (SQLException e)
    {
      return (OutputStream)postForAll(methodObject32341, onErrorForAll(methodObject32341, e));
    }
  }
  
  public void setString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32343, this, new Object[] { arg0 });
      this.delegate.setString(arg0);
      postForAll(methodObject32343);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject32343, e);
    }
  }
  
  public void free()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32340, this, zeroLengthObjectArray);
      this.delegate.free();
      postForAll(methodObject32340);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject32340, e);
    }
  }
  
  public InputStream getBinaryStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32344, this, zeroLengthObjectArray);
      return (InputStream)postForAll(methodObject32344, (Object)this.delegate.getBinaryStream());
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject32344, onErrorForAll(methodObject32344, e));
    }
  }
  
  public Reader getCharacterStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32345, this, zeroLengthObjectArray);
      return (Reader)postForAll(methodObject32345, (Object)this.delegate.getCharacterStream());
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject32345, onErrorForAll(methodObject32345, e));
    }
  }
  
  public Writer setCharacterStream()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32342, this, zeroLengthObjectArray);
      return (Writer)postForAll(methodObject32342, (Object)this.delegate.setCharacterStream());
    }
    catch (SQLException e)
    {
      return (Writer)postForAll(methodObject32342, onErrorForAll(methodObject32342, e));
    }
  }
  
  public Result setResult(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32346, this, new Object[] { arg0 });
      return (Result)postForAll(methodObject32346, (Object)this.delegate.setResult(arg0));
    }
    catch (SQLException e)
    {
      return (Result)postForAll(methodObject32346, onErrorForAll(methodObject32346, e));
    }
  }
  
  public Source getSource(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32339, this, new Object[] { arg0 });
      return (Source)postForAll(methodObject32339, (Object)this.delegate.getSource(arg0));
    }
    catch (SQLException e)
    {
      return (Source)postForAll(methodObject32339, onErrorForAll(methodObject32339, e));
    }
  }
  
  public String getString()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32338, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32338, (Object)this.delegate.getString());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32338, onErrorForAll(methodObject32338, e));
    }
  }
  
  public SQLXML _getDelegate_()
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
      methodObject32341 = SQLXML.class.getDeclaredMethod("setBinaryStream", new Class[0]);
      methodObject32343 = SQLXML.class.getDeclaredMethod("setString", new Class[] { String.class });
      methodObject32340 = SQLXML.class.getDeclaredMethod("free", new Class[0]);
      methodObject32344 = SQLXML.class.getDeclaredMethod("getBinaryStream", new Class[0]);
      methodObject32345 = SQLXML.class.getDeclaredMethod("getCharacterStream", new Class[0]);
      methodObject32342 = SQLXML.class.getDeclaredMethod("setCharacterStream", new Class[0]);
      methodObject32346 = SQLXML.class.getDeclaredMethod("setResult", new Class[] { Class.class });
      methodObject32339 = SQLXML.class.getDeclaredMethod("getSource", new Class[] { Class.class });
      methodObject32338 = SQLXML.class.getDeclaredMethod("getString", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1SQLXML$$$Proxy(SQLXML paramSQLXML, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramSQLXML;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1SQLXML$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */