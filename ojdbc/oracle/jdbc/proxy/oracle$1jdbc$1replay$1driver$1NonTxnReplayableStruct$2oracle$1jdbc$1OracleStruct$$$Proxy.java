package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.Map;
import oracle.jdbc.OracleStruct;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.replay.driver.NonTxnReplayableStruct;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableStruct$2oracle$1jdbc$1OracleStruct$$$Proxy
  extends NonTxnReplayableStruct
  implements OracleStruct, _Proxy_
{
  private OracleStruct delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject23213;
  private static Method methodObject23216;
  private static Method methodObject23214;
  private static Method methodObject23215;
  
  public OracleTypeMetaData getOracleMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23213, this, zeroLengthObjectArray);
      return (OracleTypeMetaData)postForAll(methodObject23213, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleMetaData(), this, this.proxyCache, methodObject23213));
    }
    catch (SQLException e)
    {
      return (OracleTypeMetaData)postForAll(methodObject23213, onErrorForAll(methodObject23213, e));
    }
  }
  
  public String getSQLTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23216, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject23216, (Object)this.delegate.getSQLTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject23216, onErrorForAll(methodObject23216, e));
    }
  }
  
  public Object[] getAttributes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23214, this, zeroLengthObjectArray);
      return (Object[])postForAll(methodObject23214, (Object)this.delegate.getAttributes());
    }
    catch (SQLException e)
    {
      return (Object[])postForAll(methodObject23214, onErrorForAll(methodObject23214, e));
    }
  }
  
  public Object[] getAttributes(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23215, this, new Object[] { arg0 });
      return (Object[])postForAll(methodObject23215, (Object)this.delegate.getAttributes(arg0));
    }
    catch (SQLException e)
    {
      return (Object[])postForAll(methodObject23215, onErrorForAll(methodObject23215, e));
    }
  }
  
  public OracleStruct _getDelegate_()
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
      methodObject23213 = OracleStruct.class.getDeclaredMethod("getOracleMetaData", new Class[0]);
      methodObject23216 = Struct.class.getDeclaredMethod("getSQLTypeName", new Class[0]);
      methodObject23214 = Struct.class.getDeclaredMethod("getAttributes", new Class[0]);
      methodObject23215 = Struct.class.getDeclaredMethod("getAttributes", new Class[] { Map.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableStruct$2oracle$1jdbc$1OracleStruct$$$Proxy(OracleStruct paramOracleStruct, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleStruct;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableStruct$2oracle$1jdbc$1OracleStruct$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */