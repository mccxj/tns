package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.OracleTypeMetaData.Kind;
import oracle.jdbc.OracleTypeMetaData.Opaque;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
import oracle.sql.SQLName;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleTypeMetaData$Opaque$$$Proxy
  extends NonTxnReplayableBase
  implements OracleTypeMetaData.Opaque, _Proxy_
{
  private OracleTypeMetaData.Opaque delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32583;
  private static Method methodObject32589;
  private static Method methodObject32582;
  private static Method methodObject32587;
  private static Method methodObject32585;
  private static Method methodObject32584;
  private static Method methodObject32581;
  private static Method methodObject32590;
  private static Method methodObject32588;
  private static Method methodObject32591;
  private static Method methodObject32586;
  
  public boolean isModeledInC()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32583, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32583, Boolean.valueOf(this.delegate.isModeledInC()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32583, onErrorForAll(methodObject32583, e))).booleanValue();
    }
  }
  
  public OracleTypeMetaData.Kind getKind()
  {
    super.preForAll(methodObject32589, this, zeroLengthObjectArray);
    return (OracleTypeMetaData.Kind)postForAll(methodObject32589, (Object)this.delegate.getKind());
  }
  
  public boolean isTrustedLibrary()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32582, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32582, Boolean.valueOf(this.delegate.isTrustedLibrary()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32582, onErrorForAll(methodObject32582, e))).booleanValue();
    }
  }
  
  public int getTypeCode()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32587, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32587, Integer.valueOf(this.delegate.getTypeCode()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32587, onErrorForAll(methodObject32587, e))).intValue();
    }
  }
  
  public boolean hasFixedSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32585, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32585, Boolean.valueOf(this.delegate.hasFixedSize()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32585, onErrorForAll(methodObject32585, e))).booleanValue();
    }
  }
  
  public boolean hasUnboundedSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32584, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32584, Boolean.valueOf(this.delegate.hasUnboundedSize()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32584, onErrorForAll(methodObject32584, e))).booleanValue();
    }
  }
  
  public long getMaxLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32581, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject32581, Long.valueOf(this.delegate.getMaxLength()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject32581, onErrorForAll(methodObject32581, e))).longValue();
    }
  }
  
  public SQLName getSQLName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32590, this, zeroLengthObjectArray);
      return (SQLName)postForAll(methodObject32590, (Object)this.delegate.getSQLName());
    }
    catch (SQLException e)
    {
      return (SQLName)postForAll(methodObject32590, onErrorForAll(methodObject32590, e));
    }
  }
  
  public String getSchemaName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32588, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32588, (Object)this.delegate.getSchemaName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32588, onErrorForAll(methodObject32588, e));
    }
  }
  
  public String getTypeCodeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32591, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32591, (Object)this.delegate.getTypeCodeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32591, onErrorForAll(methodObject32591, e));
    }
  }
  
  public String getName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32586, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32586, (Object)this.delegate.getName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32586, onErrorForAll(methodObject32586, e));
    }
  }
  
  public OracleTypeMetaData.Opaque _getDelegate_()
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
      methodObject32583 = OracleTypeMetaData.Opaque.class.getDeclaredMethod("isModeledInC", new Class[0]);
      methodObject32589 = OracleTypeMetaData.class.getDeclaredMethod("getKind", new Class[0]);
      methodObject32582 = OracleTypeMetaData.Opaque.class.getDeclaredMethod("isTrustedLibrary", new Class[0]);
      methodObject32587 = OracleTypeMetaData.class.getDeclaredMethod("getTypeCode", new Class[0]);
      methodObject32585 = OracleTypeMetaData.Opaque.class.getDeclaredMethod("hasFixedSize", new Class[0]);
      methodObject32584 = OracleTypeMetaData.Opaque.class.getDeclaredMethod("hasUnboundedSize", new Class[0]);
      methodObject32581 = OracleTypeMetaData.Opaque.class.getDeclaredMethod("getMaxLength", new Class[0]);
      methodObject32590 = OracleTypeMetaData.class.getDeclaredMethod("getSQLName", new Class[0]);
      methodObject32588 = OracleTypeMetaData.class.getDeclaredMethod("getSchemaName", new Class[0]);
      methodObject32591 = OracleTypeMetaData.class.getDeclaredMethod("getTypeCodeName", new Class[0]);
      methodObject32586 = OracleTypeMetaData.class.getDeclaredMethod("getName", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleTypeMetaData$Opaque$$$Proxy(OracleTypeMetaData.Opaque paramOpaque, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOpaque;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleTypeMetaData$Opaque$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */