package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1ParameterMetaData$$$Proxy
  extends NonTxnReplayableBase
  implements ParameterMetaData, _Proxy_
{
  private ParameterMetaData delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32277;
  private static Method methodObject32273;
  private static Method methodObject32271;
  private static Method methodObject32274;
  private static Method methodObject32275;
  private static Method methodObject32272;
  private static Method methodObject32270;
  private static Method methodObject32278;
  private static Method methodObject32280;
  private static Method methodObject32276;
  private static Method methodObject32279;
  
  public int isNullable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32277, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32277, Integer.valueOf(this.delegate.isNullable(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32277, onErrorForAll(methodObject32277, e))).intValue();
    }
  }
  
  public int getParameterType(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32273, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32273, Integer.valueOf(this.delegate.getParameterType(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32273, onErrorForAll(methodObject32273, e))).intValue();
    }
  }
  
  public int getParameterCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32271, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32271, Integer.valueOf(this.delegate.getParameterCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32271, onErrorForAll(methodObject32271, e))).intValue();
    }
  }
  
  public String getParameterTypeName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32274, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32274, (Object)this.delegate.getParameterTypeName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32274, onErrorForAll(methodObject32274, e));
    }
  }
  
  public int getPrecision(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32275, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32275, Integer.valueOf(this.delegate.getPrecision(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32275, onErrorForAll(methodObject32275, e))).intValue();
    }
  }
  
  public int getParameterMode(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32272, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32272, Integer.valueOf(this.delegate.getParameterMode(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32272, onErrorForAll(methodObject32272, e))).intValue();
    }
  }
  
  public String getParameterClassName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32270, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32270, (Object)this.delegate.getParameterClassName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32270, onErrorForAll(methodObject32270, e));
    }
  }
  
  public boolean isSigned(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32278, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32278, Boolean.valueOf(this.delegate.isSigned(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32278, onErrorForAll(methodObject32278, e))).booleanValue();
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32280, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject32280, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32280, onErrorForAll(methodObject32280, e))).booleanValue();
    }
  }
  
  public int getScale(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32276, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32276, Integer.valueOf(this.delegate.getScale(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32276, onErrorForAll(methodObject32276, e))).intValue();
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32279, this, new Object[] { arg0 });
      return postForAll(methodObject32279, this.proxyFactory.proxyForCache(this.delegate.unwrap(arg0), this, this.proxyCache, methodObject32279));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject32279, onErrorForAll(methodObject32279, e));
    }
  }
  
  public ParameterMetaData _getDelegate_()
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
      methodObject32277 = ParameterMetaData.class.getDeclaredMethod("isNullable", new Class[] { Integer.TYPE });
      methodObject32273 = ParameterMetaData.class.getDeclaredMethod("getParameterType", new Class[] { Integer.TYPE });
      methodObject32271 = ParameterMetaData.class.getDeclaredMethod("getParameterCount", new Class[0]);
      methodObject32274 = ParameterMetaData.class.getDeclaredMethod("getParameterTypeName", new Class[] { Integer.TYPE });
      methodObject32275 = ParameterMetaData.class.getDeclaredMethod("getPrecision", new Class[] { Integer.TYPE });
      methodObject32272 = ParameterMetaData.class.getDeclaredMethod("getParameterMode", new Class[] { Integer.TYPE });
      methodObject32270 = ParameterMetaData.class.getDeclaredMethod("getParameterClassName", new Class[] { Integer.TYPE });
      methodObject32278 = ParameterMetaData.class.getDeclaredMethod("isSigned", new Class[] { Integer.TYPE });
      methodObject32280 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject32276 = ParameterMetaData.class.getDeclaredMethod("getScale", new Class[] { Integer.TYPE });
      methodObject32279 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1ParameterMetaData$$$Proxy(ParameterMetaData paramParameterMetaData, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramParameterMetaData;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1ParameterMetaData$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */