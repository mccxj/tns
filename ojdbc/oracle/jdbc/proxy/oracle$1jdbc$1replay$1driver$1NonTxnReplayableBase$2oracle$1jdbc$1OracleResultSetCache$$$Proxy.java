package oracle.jdbc.proxy;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import oracle.jdbc.OracleResultSetCache;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleResultSetCache$$$Proxy
  extends NonTxnReplayableBase
  implements OracleResultSetCache, _Proxy_
{
  private OracleResultSetCache delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32538;
  private static Method methodObject32535;
  private static Method methodObject32534;
  private static Method methodObject32537;
  private static Method methodObject32536;
  private static Method methodObject32539;
  
  public void remove(int arg0, int arg1)
    throws IOException
  {
    super.preForAll(methodObject32538, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
    this.delegate.remove(arg0, arg1);
    postForAll(methodObject32538);
  }
  
  public void put(int arg0, int arg1, Object arg2)
    throws IOException
  {
    super.preForAll(methodObject32535, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
    this.delegate.put(arg0, arg1, (arg2 instanceof _Proxy_) ? (Object)((_Proxy_)arg2)._getDelegate_() : arg2);
    postForAll(methodObject32535);
  }
  
  public Object get(int arg0, int arg1)
    throws IOException
  {
    super.preForAll(methodObject32534, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
    return postForAll(methodObject32534, this.proxyFactory.proxyForCache(this.delegate.get(arg0, arg1), this, this.proxyCache, methodObject32534));
  }
  
  public void remove(int arg0)
    throws IOException
  {
    super.preForAll(methodObject32537, this, new Object[] { Integer.valueOf(arg0) });
    this.delegate.remove(arg0);
    postForAll(methodObject32537);
  }
  
  public void clear()
    throws IOException
  {
    super.preForAll(methodObject32536, this, zeroLengthObjectArray);
    this.delegate.clear();
    postForAll(methodObject32536);
  }
  
  public void close()
    throws IOException
  {
    super.preForAll(methodObject32539, this, zeroLengthObjectArray);
    this.delegate.close();
    postForAll(methodObject32539);
  }
  
  public OracleResultSetCache _getDelegate_()
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
      methodObject32538 = OracleResultSetCache.class.getDeclaredMethod("remove", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject32535 = OracleResultSetCache.class.getDeclaredMethod("put", new Class[] { Integer.TYPE, Integer.TYPE, Object.class });
      methodObject32534 = OracleResultSetCache.class.getDeclaredMethod("get", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject32537 = OracleResultSetCache.class.getDeclaredMethod("remove", new Class[] { Integer.TYPE });
      methodObject32536 = OracleResultSetCache.class.getDeclaredMethod("clear", new Class[0]);
      methodObject32539 = OracleResultSetCache.class.getDeclaredMethod("close", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleResultSetCache$$$Proxy(OracleResultSetCache paramOracleResultSetCache, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleResultSetCache;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleResultSetCache$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */