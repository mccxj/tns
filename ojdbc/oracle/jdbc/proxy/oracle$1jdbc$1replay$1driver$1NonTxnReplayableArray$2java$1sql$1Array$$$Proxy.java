package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableArray;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableArray$2java$1sql$1Array$$$Proxy
  extends NonTxnReplayableArray
  implements Array, _Proxy_
{
  private Array delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject12842;
  private static Method methodObject12838;
  private static Method methodObject12840;
  private static Method methodObject12841;
  private static Method methodObject12836;
  private static Method methodObject12834;
  private static Method methodObject12835;
  private static Method methodObject12832;
  private static Method methodObject12837;
  private static Method methodObject12839;
  private static Method methodObject12833;
  
  public ResultSet getResultSet(long arg0, int arg1, Map arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12842, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      return (ResultSet)postForAll(methodObject12842, this.proxyFactory.proxyForCreate((Object)this.delegate.getResultSet(arg0, arg1, arg2), this, this.proxyCache, methodObject12842));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject12842, onErrorForAll(methodObject12842, e));
    }
  }
  
  public String getBaseTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12838, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject12838, (Object)this.delegate.getBaseTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject12838, onErrorForAll(methodObject12838, e));
    }
  }
  
  public ResultSet getResultSet(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12840, this, new Object[] { arg0 });
      return (ResultSet)postForAll(methodObject12840, this.proxyFactory.proxyForCreate((Object)this.delegate.getResultSet(arg0), this, this.proxyCache, methodObject12840));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject12840, onErrorForAll(methodObject12840, e));
    }
  }
  
  public ResultSet getResultSet(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12841, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (ResultSet)postForAll(methodObject12841, this.proxyFactory.proxyForCreate((Object)this.delegate.getResultSet(arg0, arg1), this, this.proxyCache, methodObject12841));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject12841, onErrorForAll(methodObject12841, e));
    }
  }
  
  public void free()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12836, this, zeroLengthObjectArray);
      this.delegate.free();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject12836, e);
    }
  }
  
  public Object getArray(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12834, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return postForAll(methodObject12834, this.proxyFactory.proxyForCache(this.delegate.getArray(arg0, arg1), this, this.proxyCache, methodObject12834));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject12834, onErrorForAll(methodObject12834, e));
    }
  }
  
  public Object getArray(long arg0, int arg1, Map arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12835, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      return postForAll(methodObject12835, this.proxyFactory.proxyForCache(this.delegate.getArray(arg0, arg1, arg2), this, this.proxyCache, methodObject12835));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject12835, onErrorForAll(methodObject12835, e));
    }
  }
  
  public Object getArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12832, this, zeroLengthObjectArray);
      return postForAll(methodObject12832, this.proxyFactory.proxyForCache(this.delegate.getArray(), this, this.proxyCache, methodObject12832));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject12832, onErrorForAll(methodObject12832, e));
    }
  }
  
  public int getBaseType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12837, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject12837, Integer.valueOf(this.delegate.getBaseType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject12837, onErrorForAll(methodObject12837, e))).intValue();
    }
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12839, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject12839, this.proxyFactory.proxyForCreate((Object)this.delegate.getResultSet(), this, this.proxyCache, methodObject12839));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject12839, onErrorForAll(methodObject12839, e));
    }
  }
  
  public Object getArray(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12833, this, new Object[] { arg0 });
      return postForAll(methodObject12833, this.proxyFactory.proxyForCache(this.delegate.getArray(arg0), this, this.proxyCache, methodObject12833));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject12833, onErrorForAll(methodObject12833, e));
    }
  }
  
  public Array _getDelegate_()
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
      methodObject12842 = Array.class.getDeclaredMethod("getResultSet", new Class[] { Long.TYPE, Integer.TYPE, Map.class });
      methodObject12838 = Array.class.getDeclaredMethod("getBaseTypeName", new Class[0]);
      methodObject12840 = Array.class.getDeclaredMethod("getResultSet", new Class[] { Map.class });
      methodObject12841 = Array.class.getDeclaredMethod("getResultSet", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12836 = Array.class.getDeclaredMethod("free", new Class[0]);
      methodObject12834 = Array.class.getDeclaredMethod("getArray", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12835 = Array.class.getDeclaredMethod("getArray", new Class[] { Long.TYPE, Integer.TYPE, Map.class });
      methodObject12832 = Array.class.getDeclaredMethod("getArray", new Class[0]);
      methodObject12837 = Array.class.getDeclaredMethod("getBaseType", new Class[0]);
      methodObject12839 = Array.class.getDeclaredMethod("getResultSet", new Class[0]);
      methodObject12833 = Array.class.getDeclaredMethod("getArray", new Class[] { Map.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableArray$2java$1sql$1Array$$$Proxy(Array paramArray, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramArray;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableArray$2java$1sql$1Array$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */