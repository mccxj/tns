package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.Ref;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableRef;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableRef$2java$1sql$1Ref$$$Proxy
  extends NonTxnReplayableRef
  implements Ref, _Proxy_
{
  private Ref delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject30899;
  private static Method methodObject30898;
  private static Method methodObject30896;
  private static Method methodObject30897;
  
  public void setObject(Object arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30899, this, new Object[] { arg0 });
      this.delegate.setObject((arg0 instanceof _Proxy_) ? (Object)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30899, e);
    }
  }
  
  public String getBaseTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30898, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject30898, (Object)this.delegate.getBaseTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject30898, onErrorForAll(methodObject30898, e));
    }
  }
  
  public Object getObject(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30896, this, new Object[] { arg0 });
      return postForAll(methodObject30896, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0), this, this.proxyCache, methodObject30896));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject30896, onErrorForAll(methodObject30896, e));
    }
  }
  
  public Object getObject()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30897, this, zeroLengthObjectArray);
      return postForAll(methodObject30897, this.proxyFactory.proxyForCache(this.delegate.getObject(), this, this.proxyCache, methodObject30897));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject30897, onErrorForAll(methodObject30897, e));
    }
  }
  
  public Ref _getDelegate_()
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
      methodObject30899 = Ref.class.getDeclaredMethod("setObject", new Class[] { Object.class });
      methodObject30898 = Ref.class.getDeclaredMethod("getBaseTypeName", new Class[0]);
      methodObject30896 = Ref.class.getDeclaredMethod("getObject", new Class[] { Map.class });
      methodObject30897 = Ref.class.getDeclaredMethod("getObject", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableRef$2java$1sql$1Ref$$$Proxy(Ref paramRef, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramRef;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableRef$2java$1sql$1Ref$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */