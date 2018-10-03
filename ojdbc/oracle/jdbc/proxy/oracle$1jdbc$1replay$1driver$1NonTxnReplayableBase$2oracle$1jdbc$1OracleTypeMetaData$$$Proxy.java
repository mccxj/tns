package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.OracleTypeMetaData.Kind;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
import oracle.sql.SQLName;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleTypeMetaData$$$Proxy
  extends NonTxnReplayableBase
  implements OracleTypeMetaData, _Proxy_
{
  private OracleTypeMetaData delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32568;
  private static Method methodObject32566;
  private static Method methodObject32569;
  private static Method methodObject32567;
  private static Method methodObject32570;
  private static Method methodObject32565;
  
  public OracleTypeMetaData.Kind getKind()
  {
    super.preForAll(methodObject32568, this, zeroLengthObjectArray);
    return (OracleTypeMetaData.Kind)postForAll(methodObject32568, (Object)this.delegate.getKind());
  }
  
  public int getTypeCode()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32566, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32566, Integer.valueOf(this.delegate.getTypeCode()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32566, onErrorForAll(methodObject32566, e))).intValue();
    }
  }
  
  public SQLName getSQLName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32569, this, zeroLengthObjectArray);
      return (SQLName)postForAll(methodObject32569, (Object)this.delegate.getSQLName());
    }
    catch (SQLException e)
    {
      return (SQLName)postForAll(methodObject32569, onErrorForAll(methodObject32569, e));
    }
  }
  
  public String getSchemaName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32567, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32567, (Object)this.delegate.getSchemaName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32567, onErrorForAll(methodObject32567, e));
    }
  }
  
  public String getTypeCodeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32570, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32570, (Object)this.delegate.getTypeCodeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32570, onErrorForAll(methodObject32570, e));
    }
  }
  
  public String getName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32565, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32565, (Object)this.delegate.getName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32565, onErrorForAll(methodObject32565, e));
    }
  }
  
  public OracleTypeMetaData _getDelegate_()
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
      methodObject32568 = OracleTypeMetaData.class.getDeclaredMethod("getKind", new Class[0]);
      methodObject32566 = OracleTypeMetaData.class.getDeclaredMethod("getTypeCode", new Class[0]);
      methodObject32569 = OracleTypeMetaData.class.getDeclaredMethod("getSQLName", new Class[0]);
      methodObject32567 = OracleTypeMetaData.class.getDeclaredMethod("getSchemaName", new Class[0]);
      methodObject32570 = OracleTypeMetaData.class.getDeclaredMethod("getTypeCodeName", new Class[0]);
      methodObject32565 = OracleTypeMetaData.class.getDeclaredMethod("getName", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleTypeMetaData$$$Proxy(OracleTypeMetaData paramOracleTypeMetaData, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleTypeMetaData;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleTypeMetaData$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */