package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1SQLData$$$Proxy
  extends NonTxnReplayableBase
  implements SQLData, _Proxy_
{
  private SQLData delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32308;
  private static Method methodObject32310;
  private static Method methodObject32309;
  
  public String getSQLTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32308, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32308, (Object)this.delegate.getSQLTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32308, onErrorForAll(methodObject32308, e));
    }
  }
  
  public void writeSQL(SQLOutput arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32310, this, new Object[] { arg0 });
      this.delegate.writeSQL((arg0 instanceof _Proxy_) ? (SQLOutput)((_Proxy_)arg0)._getDelegate_() : arg0);
      postForAll(methodObject32310);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject32310, e);
    }
  }
  
  public void readSQL(SQLInput arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32309, this, new Object[] { arg0, arg1 });
      this.delegate.readSQL((arg0 instanceof _Proxy_) ? (SQLInput)((_Proxy_)arg0)._getDelegate_() : arg0, arg1);
      postForAll(methodObject32309);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject32309, e);
    }
  }
  
  public SQLData _getDelegate_()
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
      methodObject32308 = SQLData.class.getDeclaredMethod("getSQLTypeName", new Class[0]);
      methodObject32310 = SQLData.class.getDeclaredMethod("writeSQL", new Class[] { SQLOutput.class });
      methodObject32309 = SQLData.class.getDeclaredMethod("readSQL", new Class[] { SQLInput.class, String.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1SQLData$$$Proxy(SQLData paramSQLData, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramSQLData;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1SQLData$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */