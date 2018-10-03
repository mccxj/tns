package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.OracleTypeMetaData.Array;
import oracle.jdbc.OracleTypeMetaData.ArrayStorage;
import oracle.jdbc.OracleTypeMetaData.Kind;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
import oracle.sql.SQLName;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleTypeMetaData$Array$$$Proxy
  extends NonTxnReplayableBase
  implements OracleTypeMetaData.Array, _Proxy_
{
  private OracleTypeMetaData.Array delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32578;
  private static Method methodObject32576;
  private static Method methodObject32573;
  private static Method methodObject32574;
  private static Method methodObject32579;
  private static Method methodObject32577;
  private static Method methodObject32580;
  private static Method methodObject32572;
  private static Method methodObject32575;
  private static Method methodObject32571;
  
  public OracleTypeMetaData.Kind getKind()
  {
    super.preForAll(methodObject32578, this, zeroLengthObjectArray);
    return (OracleTypeMetaData.Kind)postForAll(methodObject32578, (Object)this.delegate.getKind());
  }
  
  public int getTypeCode()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32576, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32576, Integer.valueOf(this.delegate.getTypeCode()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32576, onErrorForAll(methodObject32576, e))).intValue();
    }
  }
  
  public OracleTypeMetaData.ArrayStorage getArrayStorage()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32573, this, zeroLengthObjectArray);
      return (OracleTypeMetaData.ArrayStorage)postForAll(methodObject32573, (Object)this.delegate.getArrayStorage());
    }
    catch (SQLException e)
    {
      return (OracleTypeMetaData.ArrayStorage)postForAll(methodObject32573, onErrorForAll(methodObject32573, e));
    }
  }
  
  public long getMaxLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32574, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject32574, Long.valueOf(this.delegate.getMaxLength()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject32574, onErrorForAll(methodObject32574, e))).longValue();
    }
  }
  
  public SQLName getSQLName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32579, this, zeroLengthObjectArray);
      return (SQLName)postForAll(methodObject32579, (Object)this.delegate.getSQLName());
    }
    catch (SQLException e)
    {
      return (SQLName)postForAll(methodObject32579, onErrorForAll(methodObject32579, e));
    }
  }
  
  public String getSchemaName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32577, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32577, (Object)this.delegate.getSchemaName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32577, onErrorForAll(methodObject32577, e));
    }
  }
  
  public String getTypeCodeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32580, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32580, (Object)this.delegate.getTypeCodeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32580, onErrorForAll(methodObject32580, e));
    }
  }
  
  public String getBaseName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32572, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32572, (Object)this.delegate.getBaseName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32572, onErrorForAll(methodObject32572, e));
    }
  }
  
  public String getName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32575, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32575, (Object)this.delegate.getName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32575, onErrorForAll(methodObject32575, e));
    }
  }
  
  public int getBaseType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32571, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32571, Integer.valueOf(this.delegate.getBaseType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32571, onErrorForAll(methodObject32571, e))).intValue();
    }
  }
  
  public OracleTypeMetaData.Array _getDelegate_()
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
      methodObject32578 = OracleTypeMetaData.class.getDeclaredMethod("getKind", new Class[0]);
      methodObject32576 = OracleTypeMetaData.class.getDeclaredMethod("getTypeCode", new Class[0]);
      methodObject32573 = OracleTypeMetaData.Array.class.getDeclaredMethod("getArrayStorage", new Class[0]);
      methodObject32574 = OracleTypeMetaData.Array.class.getDeclaredMethod("getMaxLength", new Class[0]);
      methodObject32579 = OracleTypeMetaData.class.getDeclaredMethod("getSQLName", new Class[0]);
      methodObject32577 = OracleTypeMetaData.class.getDeclaredMethod("getSchemaName", new Class[0]);
      methodObject32580 = OracleTypeMetaData.class.getDeclaredMethod("getTypeCodeName", new Class[0]);
      methodObject32572 = OracleTypeMetaData.Array.class.getDeclaredMethod("getBaseName", new Class[0]);
      methodObject32575 = OracleTypeMetaData.class.getDeclaredMethod("getName", new Class[0]);
      methodObject32571 = OracleTypeMetaData.Array.class.getDeclaredMethod("getBaseType", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleTypeMetaData$Array$$$Proxy(OracleTypeMetaData.Array paramArray, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramArray;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleTypeMetaData$Array$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */