package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.OracleTypeMetaData.Kind;
import oracle.jdbc.OracleTypeMetaData.Struct;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
import oracle.sql.SQLName;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleTypeMetaData$Struct$$$Proxy
  extends NonTxnReplayableBase
  implements OracleTypeMetaData.Struct, _Proxy_
{
  private OracleTypeMetaData.Struct delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32600;
  private static Method methodObject32605;
  private static Method methodObject32606;
  private static Method methodObject32594;
  private static Method methodObject32604;
  private static Method methodObject32596;
  private static Method methodObject32602;
  private static Method methodObject32597;
  private static Method methodObject32598;
  private static Method methodObject32603;
  private static Method methodObject32593;
  private static Method methodObject32595;
  private static Method methodObject32592;
  private static Method methodObject32601;
  private static Method methodObject32599;
  
  public String[] getSubtypeNames()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32600, this, zeroLengthObjectArray);
      return (String[])postForAll(methodObject32600, (Object)this.delegate.getSubtypeNames());
    }
    catch (SQLException e)
    {
      return (String[])postForAll(methodObject32600, onErrorForAll(methodObject32600, e));
    }
  }
  
  public SQLName getSQLName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32605, this, zeroLengthObjectArray);
      return (SQLName)postForAll(methodObject32605, (Object)this.delegate.getSQLName());
    }
    catch (SQLException e)
    {
      return (SQLName)postForAll(methodObject32605, onErrorForAll(methodObject32605, e));
    }
  }
  
  public String getTypeCodeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32606, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32606, (Object)this.delegate.getTypeCodeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32606, onErrorForAll(methodObject32606, e));
    }
  }
  
  public ResultSetMetaData getMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32594, this, zeroLengthObjectArray);
      return (ResultSetMetaData)postForAll(methodObject32594, this.proxyFactory.proxyForCache((Object)this.delegate.getMetaData(), this, this.proxyCache, methodObject32594));
    }
    catch (SQLException e)
    {
      return (ResultSetMetaData)postForAll(methodObject32594, onErrorForAll(methodObject32594, e));
    }
  }
  
  public OracleTypeMetaData.Kind getKind()
  {
    super.preForAll(methodObject32604, this, zeroLengthObjectArray);
    return (OracleTypeMetaData.Kind)postForAll(methodObject32604, (Object)this.delegate.getKind());
  }
  
  public boolean isFinalType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32596, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32596, Boolean.valueOf(this.delegate.isFinalType()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32596, onErrorForAll(methodObject32596, e))).booleanValue();
    }
  }
  
  public int getTypeCode()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32602, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32602, Integer.valueOf(this.delegate.getTypeCode()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32602, onErrorForAll(methodObject32602, e))).intValue();
    }
  }
  
  public boolean isSubtype()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32597, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32597, Boolean.valueOf(this.delegate.isSubtype()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32597, onErrorForAll(methodObject32597, e))).booleanValue();
    }
  }
  
  public String getSupertypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32598, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32598, (Object)this.delegate.getSupertypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32598, onErrorForAll(methodObject32598, e));
    }
  }
  
  public String getSchemaName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32603, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32603, (Object)this.delegate.getSchemaName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32603, onErrorForAll(methodObject32603, e));
    }
  }
  
  public boolean isInstantiable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32593, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32593, Boolean.valueOf(this.delegate.isInstantiable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32593, onErrorForAll(methodObject32593, e))).booleanValue();
    }
  }
  
  public int getTypeVersion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32595, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32595, Integer.valueOf(this.delegate.getTypeVersion()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32595, onErrorForAll(methodObject32595, e))).intValue();
    }
  }
  
  public int getLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32592, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32592, Integer.valueOf(this.delegate.getLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32592, onErrorForAll(methodObject32592, e))).intValue();
    }
  }
  
  public String getName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32601, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32601, (Object)this.delegate.getName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32601, onErrorForAll(methodObject32601, e));
    }
  }
  
  public int getLocalAttributeCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32599, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32599, Integer.valueOf(this.delegate.getLocalAttributeCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32599, onErrorForAll(methodObject32599, e))).intValue();
    }
  }
  
  public OracleTypeMetaData.Struct _getDelegate_()
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
      methodObject32600 = OracleTypeMetaData.Struct.class.getDeclaredMethod("getSubtypeNames", new Class[0]);
      methodObject32605 = OracleTypeMetaData.class.getDeclaredMethod("getSQLName", new Class[0]);
      methodObject32606 = OracleTypeMetaData.class.getDeclaredMethod("getTypeCodeName", new Class[0]);
      methodObject32594 = OracleTypeMetaData.Struct.class.getDeclaredMethod("getMetaData", new Class[0]);
      methodObject32604 = OracleTypeMetaData.class.getDeclaredMethod("getKind", new Class[0]);
      methodObject32596 = OracleTypeMetaData.Struct.class.getDeclaredMethod("isFinalType", new Class[0]);
      methodObject32602 = OracleTypeMetaData.class.getDeclaredMethod("getTypeCode", new Class[0]);
      methodObject32597 = OracleTypeMetaData.Struct.class.getDeclaredMethod("isSubtype", new Class[0]);
      methodObject32598 = OracleTypeMetaData.Struct.class.getDeclaredMethod("getSupertypeName", new Class[0]);
      methodObject32603 = OracleTypeMetaData.class.getDeclaredMethod("getSchemaName", new Class[0]);
      methodObject32593 = OracleTypeMetaData.Struct.class.getDeclaredMethod("isInstantiable", new Class[0]);
      methodObject32595 = OracleTypeMetaData.Struct.class.getDeclaredMethod("getTypeVersion", new Class[0]);
      methodObject32592 = OracleTypeMetaData.Struct.class.getDeclaredMethod("getLength", new Class[0]);
      methodObject32601 = OracleTypeMetaData.class.getDeclaredMethod("getName", new Class[0]);
      methodObject32599 = OracleTypeMetaData.Struct.class.getDeclaredMethod("getLocalAttributeCount", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleTypeMetaData$Struct$$$Proxy(OracleTypeMetaData.Struct paramStruct, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramStruct;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleTypeMetaData$Struct$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */