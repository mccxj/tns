package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Map;
import oracle.jdbc.OracleParameterMetaData;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleParameterMetaData$$$Proxy
  extends NonTxnReplayableBase
  implements OracleParameterMetaData, _Proxy_
{
  private OracleParameterMetaData delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32530;
  private static Method methodObject32526;
  private static Method methodObject32524;
  private static Method methodObject32527;
  private static Method methodObject32528;
  private static Method methodObject32525;
  private static Method methodObject32523;
  private static Method methodObject32531;
  private static Method methodObject32533;
  private static Method methodObject32529;
  private static Method methodObject32532;
  
  public int isNullable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32530, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32530, Integer.valueOf(this.delegate.isNullable(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32530, onErrorForAll(methodObject32530, e))).intValue();
    }
  }
  
  public int getParameterType(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32526, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32526, Integer.valueOf(this.delegate.getParameterType(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32526, onErrorForAll(methodObject32526, e))).intValue();
    }
  }
  
  public int getParameterCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32524, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32524, Integer.valueOf(this.delegate.getParameterCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32524, onErrorForAll(methodObject32524, e))).intValue();
    }
  }
  
  public String getParameterTypeName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32527, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32527, (Object)this.delegate.getParameterTypeName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32527, onErrorForAll(methodObject32527, e));
    }
  }
  
  public int getPrecision(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32528, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32528, Integer.valueOf(this.delegate.getPrecision(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32528, onErrorForAll(methodObject32528, e))).intValue();
    }
  }
  
  public int getParameterMode(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32525, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32525, Integer.valueOf(this.delegate.getParameterMode(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32525, onErrorForAll(methodObject32525, e))).intValue();
    }
  }
  
  public String getParameterClassName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32523, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32523, (Object)this.delegate.getParameterClassName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32523, onErrorForAll(methodObject32523, e));
    }
  }
  
  public boolean isSigned(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32531, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32531, Boolean.valueOf(this.delegate.isSigned(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32531, onErrorForAll(methodObject32531, e))).booleanValue();
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32533, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject32533, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32533, onErrorForAll(methodObject32533, e))).booleanValue();
    }
  }
  
  public int getScale(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32529, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32529, Integer.valueOf(this.delegate.getScale(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32529, onErrorForAll(methodObject32529, e))).intValue();
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32532, this, new Object[] { arg0 });
      return postForAll(methodObject32532, this.proxyFactory.proxyForCache(this.delegate.unwrap(arg0), this, this.proxyCache, methodObject32532));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject32532, onErrorForAll(methodObject32532, e));
    }
  }
  
  public OracleParameterMetaData _getDelegate_()
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
      methodObject32530 = ParameterMetaData.class.getDeclaredMethod("isNullable", new Class[] { Integer.TYPE });
      methodObject32526 = ParameterMetaData.class.getDeclaredMethod("getParameterType", new Class[] { Integer.TYPE });
      methodObject32524 = ParameterMetaData.class.getDeclaredMethod("getParameterCount", new Class[0]);
      methodObject32527 = ParameterMetaData.class.getDeclaredMethod("getParameterTypeName", new Class[] { Integer.TYPE });
      methodObject32528 = ParameterMetaData.class.getDeclaredMethod("getPrecision", new Class[] { Integer.TYPE });
      methodObject32525 = ParameterMetaData.class.getDeclaredMethod("getParameterMode", new Class[] { Integer.TYPE });
      methodObject32523 = ParameterMetaData.class.getDeclaredMethod("getParameterClassName", new Class[] { Integer.TYPE });
      methodObject32531 = ParameterMetaData.class.getDeclaredMethod("isSigned", new Class[] { Integer.TYPE });
      methodObject32533 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject32529 = ParameterMetaData.class.getDeclaredMethod("getScale", new Class[] { Integer.TYPE });
      methodObject32532 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleParameterMetaData$$$Proxy(OracleParameterMetaData paramOracleParameterMetaData, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleParameterMetaData;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleParameterMetaData$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */