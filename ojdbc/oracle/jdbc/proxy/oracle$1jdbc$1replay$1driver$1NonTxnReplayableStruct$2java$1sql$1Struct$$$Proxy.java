package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableStruct;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableStruct$2java$1sql$1Struct$$$Proxy
  extends NonTxnReplayableStruct
  implements Struct, _Proxy_
{
  private Struct delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject23212;
  private static Method methodObject23210;
  private static Method methodObject23211;
  
  public String getSQLTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23212, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject23212, (Object)this.delegate.getSQLTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject23212, onErrorForAll(methodObject23212, e));
    }
  }
  
  public Object[] getAttributes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23210, this, zeroLengthObjectArray);
      return (Object[])postForAll(methodObject23210, (Object)this.delegate.getAttributes());
    }
    catch (SQLException e)
    {
      return (Object[])postForAll(methodObject23210, onErrorForAll(methodObject23210, e));
    }
  }
  
  public Object[] getAttributes(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23211, this, new Object[] { arg0 });
      return (Object[])postForAll(methodObject23211, (Object)this.delegate.getAttributes(arg0));
    }
    catch (SQLException e)
    {
      return (Object[])postForAll(methodObject23211, onErrorForAll(methodObject23211, e));
    }
  }
  
  public Struct _getDelegate_()
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
      methodObject23212 = Struct.class.getDeclaredMethod("getSQLTypeName", new Class[0]);
      methodObject23210 = Struct.class.getDeclaredMethod("getAttributes", new Class[0]);
      methodObject23211 = Struct.class.getDeclaredMethod("getAttributes", new Class[] { Map.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableStruct$2java$1sql$1Struct$$$Proxy(Struct paramStruct, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramStruct;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableStruct$2java$1sql$1Struct$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */