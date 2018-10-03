package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Map;
import oracle.jdbc.OracleSavepoint;
import oracle.jdbc.replay.driver.NonTxnReplayableOthers;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableOthers$2oracle$1jdbc$1OracleSavepoint$$$Proxy
  extends NonTxnReplayableOthers
  implements OracleSavepoint, _Proxy_
{
  private OracleSavepoint delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject25901;
  private static Method methodObject25902;
  
  public int getSavepointId()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25901, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25901, Integer.valueOf(this.delegate.getSavepointId()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25901, onErrorForAll(methodObject25901, e))).intValue();
    }
  }
  
  public String getSavepointName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25902, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject25902, (Object)this.delegate.getSavepointName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject25902, onErrorForAll(methodObject25902, e));
    }
  }
  
  public OracleSavepoint _getDelegate_()
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
      methodObject25901 = Savepoint.class.getDeclaredMethod("getSavepointId", new Class[0]);
      methodObject25902 = Savepoint.class.getDeclaredMethod("getSavepointName", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableOthers$2oracle$1jdbc$1OracleSavepoint$$$Proxy(OracleSavepoint paramOracleSavepoint, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleSavepoint;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableOthers$2oracle$1jdbc$1OracleSavepoint$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */