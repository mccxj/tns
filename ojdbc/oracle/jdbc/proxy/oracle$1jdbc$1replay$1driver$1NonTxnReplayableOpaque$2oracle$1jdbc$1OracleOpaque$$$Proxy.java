package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.OracleOpaque;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.replay.driver.NonTxnReplayableOpaque;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableOpaque$2oracle$1jdbc$1OracleOpaque$$$Proxy
  extends NonTxnReplayableOpaque
  implements OracleOpaque, _Proxy_
{
  private OracleOpaque delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject26586;
  private static Method methodObject26585;
  private static Method methodObject26584;
  
  public OracleTypeMetaData getOracleMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26586, this, zeroLengthObjectArray);
      return (OracleTypeMetaData)postForAll(methodObject26586, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleMetaData(), this, this.proxyCache, methodObject26586));
    }
    catch (SQLException e)
    {
      return (OracleTypeMetaData)postForAll(methodObject26586, onErrorForAll(methodObject26586, e));
    }
  }
  
  public String getSQLTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26585, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject26585, (Object)this.delegate.getSQLTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject26585, onErrorForAll(methodObject26585, e));
    }
  }
  
  public Object getValue()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject26584, this, zeroLengthObjectArray);
      return postForAll(methodObject26584, this.proxyFactory.proxyForCache(this.delegate.getValue(), this, this.proxyCache, methodObject26584));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject26584, onErrorForAll(methodObject26584, e));
    }
  }
  
  public OracleOpaque _getDelegate_()
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
      methodObject26586 = OracleOpaque.class.getDeclaredMethod("getOracleMetaData", new Class[0]);
      methodObject26585 = OracleOpaque.class.getDeclaredMethod("getSQLTypeName", new Class[0]);
      methodObject26584 = OracleOpaque.class.getDeclaredMethod("getValue", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableOpaque$2oracle$1jdbc$1OracleOpaque$$$Proxy(OracleOpaque paramOracleOpaque, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleOpaque;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableOpaque$2oracle$1jdbc$1OracleOpaque$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */