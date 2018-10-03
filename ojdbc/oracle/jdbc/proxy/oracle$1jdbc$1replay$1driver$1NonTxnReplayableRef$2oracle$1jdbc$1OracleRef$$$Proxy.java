package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.Ref;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.OracleRef;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.replay.driver.NonTxnReplayableRef;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableRef$2oracle$1jdbc$1OracleRef$$$Proxy
  extends NonTxnReplayableRef
  implements OracleRef, _Proxy_
{
  private OracleRef delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject30904;
  private static Method methodObject30900;
  private static Method methodObject30903;
  private static Method methodObject30901;
  private static Method methodObject30902;
  
  public void setObject(Object arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30904, this, new Object[] { arg0 });
      this.delegate.setObject((arg0 instanceof _Proxy_) ? (Object)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject30904, e);
    }
  }
  
  public OracleTypeMetaData getOracleMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30900, this, zeroLengthObjectArray);
      return (OracleTypeMetaData)postForAll(methodObject30900, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleMetaData(), this, this.proxyCache, methodObject30900));
    }
    catch (SQLException e)
    {
      return (OracleTypeMetaData)postForAll(methodObject30900, onErrorForAll(methodObject30900, e));
    }
  }
  
  public String getBaseTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30903, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject30903, (Object)this.delegate.getBaseTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject30903, onErrorForAll(methodObject30903, e));
    }
  }
  
  public Object getObject(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30901, this, new Object[] { arg0 });
      return postForAll(methodObject30901, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0), this, this.proxyCache, methodObject30901));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject30901, onErrorForAll(methodObject30901, e));
    }
  }
  
  public Object getObject()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject30902, this, zeroLengthObjectArray);
      return postForAll(methodObject30902, this.proxyFactory.proxyForCache(this.delegate.getObject(), this, this.proxyCache, methodObject30902));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject30902, onErrorForAll(methodObject30902, e));
    }
  }
  
  public OracleRef _getDelegate_()
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
      methodObject30904 = Ref.class.getDeclaredMethod("setObject", new Class[] { Object.class });
      methodObject30900 = OracleRef.class.getDeclaredMethod("getOracleMetaData", new Class[0]);
      methodObject30903 = Ref.class.getDeclaredMethod("getBaseTypeName", new Class[0]);
      methodObject30901 = Ref.class.getDeclaredMethod("getObject", new Class[] { Map.class });
      methodObject30902 = Ref.class.getDeclaredMethod("getObject", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableRef$2oracle$1jdbc$1OracleRef$$$Proxy(OracleRef paramOracleRef, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleRef;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableRef$2oracle$1jdbc$1OracleRef$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */