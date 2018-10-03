package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.RowId;
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1RowId$$$Proxy
  extends NonTxnReplayableBase
  implements RowId, _Proxy_
{
  private RowId delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32304;
  private static Method methodObject32305;
  private static Method methodObject32306;
  private static Method methodObject32307;
  
  public boolean equals(Object arg0)
  {
    super.preForAll(methodObject32304, this, new Object[] { arg0 });
    return ((Boolean)postForAll(methodObject32304, Boolean.valueOf(this.delegate.equals((arg0 instanceof _Proxy_) ? (Object)((_Proxy_)arg0)._getDelegate_() : arg0)))).booleanValue();
  }
  
  public String toString()
  {
    super.preForAll(methodObject32305, this, zeroLengthObjectArray);
    return (String)postForAll(methodObject32305, (Object)this.delegate.toString());
  }
  
  public int hashCode()
  {
    super.preForAll(methodObject32306, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject32306, Integer.valueOf(this.delegate.hashCode()))).intValue();
  }
  
  public byte[] getBytes()
  {
    super.preForAll(methodObject32307, this, zeroLengthObjectArray);
    return (byte[])postForAll(methodObject32307, (Object)this.delegate.getBytes());
  }
  
  public RowId _getDelegate_()
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
      methodObject32304 = RowId.class.getDeclaredMethod("equals", new Class[] { Object.class });
      methodObject32305 = RowId.class.getDeclaredMethod("toString", new Class[0]);
      methodObject32306 = RowId.class.getDeclaredMethod("hashCode", new Class[0]);
      methodObject32307 = RowId.class.getDeclaredMethod("getBytes", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1RowId$$$Proxy(RowId paramRowId, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramRowId;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1RowId$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */