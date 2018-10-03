package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Wrapper;
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableStatement;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2java$1sql$1Statement$$$Proxy
  extends NonTxnReplayableStatement
  implements Statement, _Proxy_
{
  private Statement delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject24293;
  private static Method methodObject24306;
  private static Method methodObject24313;
  private static Method methodObject24315;
  private static Method methodObject24297;
  private static Method methodObject24300;
  private static Method methodObject24298;
  private static Method methodObject24320;
  private static Method methodObject24291;
  private static Method methodObject24289;
  private static Method methodObject24328;
  private static Method methodObject24305;
  private static Method methodObject24317;
  private static Method methodObject24318;
  private static Method methodObject24303;
  private static Method methodObject24299;
  private static Method methodObject24329;
  private static Method methodObject24319;
  private static Method methodObject24304;
  private static Method methodObject24325;
  private static Method methodObject24327;
  private static Method methodObject24324;
  private static Method methodObject24310;
  private static Method methodObject24312;
  private static Method methodObject24330;
  private static Method methodObject24322;
  private static Method methodObject24316;
  private static Method methodObject24296;
  private static Method methodObject24326;
  private static Method methodObject24311;
  private static Method methodObject24323;
  private static Method methodObject24292;
  private static Method methodObject24308;
  private static Method methodObject24321;
  private static Method methodObject24314;
  private static Method methodObject24309;
  private static Method methodObject24294;
  private static Method methodObject24307;
  private static Method methodObject24301;
  private static Method methodObject24290;
  private static Method methodObject24295;
  private static Method methodObject24302;
  
  public boolean execute(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24293, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject24293, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24293, onErrorForExecute(methodObject24293, e));
    }
  }
  
  public int getFetchSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24306, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24306, Integer.valueOf(this.delegate.getFetchSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24306, onErrorForAll(methodObject24306, e))).intValue();
    }
  }
  
  public ResultSet getGeneratedKeys()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24313, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject24313, this.proxyFactory.proxyForCache((Object)this.delegate.getGeneratedKeys(), this, this.proxyCache, methodObject24313));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject24313, onErrorForAll(methodObject24313, e));
    }
  }
  
  public int getMaxRows()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24315, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24315, Integer.valueOf(this.delegate.getMaxRows()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24315, onErrorForAll(methodObject24315, e))).intValue();
    }
  }
  
  public int executeUpdate(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24297, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecuteUpdate(methodObject24297, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24297, ((Integer)onErrorForAll(methodObject24297, e)).intValue());
    }
  }
  
  public int[] executeBatch()
    throws SQLException
  {
    try
    {
      super.preForExecuteBatch(methodObject24300, this, zeroLengthObjectArray);
      return (int[])postForAll(methodObject24300, (Object)this.delegate.executeBatch());
    }
    catch (SQLException e)
    {
      return (int[])postForAll(methodObject24300, onErrorForAll(methodObject24300, e));
    }
  }
  
  public int executeUpdate(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24298, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject24298, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24298, ((Integer)onErrorForAll(methodObject24298, e)).intValue());
    }
  }
  
  public int getResultSetType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24320, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24320, Integer.valueOf(this.delegate.getResultSetType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24320, onErrorForAll(methodObject24320, e))).intValue();
    }
  }
  
  public boolean execute(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24291, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecute(methodObject24291, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24291, onErrorForExecute(methodObject24291, e));
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24289, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClose(methodObject24289);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForClose(methodObject24289, e);
    }
  }
  
  public void setQueryTimeout(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24328, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setQueryTimeout(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24328, e);
    }
  }
  
  public int getFetchDirection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24305, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24305, Integer.valueOf(this.delegate.getFetchDirection()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24305, onErrorForAll(methodObject24305, e))).intValue();
    }
  }
  
  public boolean getMoreResults(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24317, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject24317, Boolean.valueOf(this.delegate.getMoreResults(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24317, onErrorForAll(methodObject24317, e))).booleanValue();
    }
  }
  
  public int getQueryTimeout()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24318, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24318, Integer.valueOf(this.delegate.getQueryTimeout()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24318, onErrorForAll(methodObject24318, e))).intValue();
    }
  }
  
  public int getResultSetHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24303, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24303, Integer.valueOf(this.delegate.getResultSetHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24303, onErrorForAll(methodObject24303, e))).intValue();
    }
  }
  
  public int executeUpdate(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24299, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject24299, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24299, ((Integer)onErrorForAll(methodObject24299, e)).intValue());
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24329, this, new Object[] { arg0 });
      return postForAll(methodObject24329, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24329, onErrorForAll(methodObject24329, e));
    }
  }
  
  public int getResultSetConcurrency()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24319, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24319, Integer.valueOf(this.delegate.getResultSetConcurrency()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24319, onErrorForAll(methodObject24319, e))).intValue();
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24304, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24304, e);
    }
  }
  
  public void setMaxFieldSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24325, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxFieldSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24325, e);
    }
  }
  
  public void setPoolable(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24327, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setPoolable(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24327, e);
    }
  }
  
  public void setEscapeProcessing(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24324, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setEscapeProcessing(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24324, e);
    }
  }
  
  public void setFetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24310, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24310, e);
    }
  }
  
  public void clearBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24312, this, zeroLengthObjectArray);
      this.delegate.clearBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24312, e);
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24330, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject24330, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24330, onErrorForAll(methodObject24330, e))).booleanValue();
    }
  }
  
  public boolean isPoolable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24322, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24322, Boolean.valueOf(this.delegate.isPoolable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24322, onErrorForAll(methodObject24322, e))).booleanValue();
    }
  }
  
  public boolean getMoreResults()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24316, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24316, Boolean.valueOf(this.delegate.getMoreResults()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24316, onErrorForAll(methodObject24316, e))).booleanValue();
    }
  }
  
  public int executeUpdate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24296, this, new Object[] { arg0 });
      return postForExecuteUpdate(methodObject24296, this.delegate.executeUpdate(arg0));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24296, ((Integer)onErrorForAll(methodObject24296, e)).intValue());
    }
  }
  
  public void setMaxRows(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24326, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxRows(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24326, e);
    }
  }
  
  public void addBatch(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24311, this, new Object[] { arg0 });
      this.delegate.addBatch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24311, e);
    }
  }
  
  public void setCursorName(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24323, this, new Object[] { arg0 });
      this.delegate.setCursorName(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24323, e);
    }
  }
  
  public boolean execute(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24292, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject24292, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24292, onErrorForExecute(methodObject24292, e));
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24308, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24308, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24308, onErrorForAll(methodObject24308, e))).booleanValue();
    }
  }
  
  public int getUpdateCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24321, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24321, Integer.valueOf(this.delegate.getUpdateCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24321, onErrorForAll(methodObject24321, e))).intValue();
    }
  }
  
  public int getMaxFieldSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24314, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24314, Integer.valueOf(this.delegate.getMaxFieldSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24314, onErrorForAll(methodObject24314, e))).intValue();
    }
  }
  
  public void setFetchDirection(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24309, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchDirection(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24309, e);
    }
  }
  
  public void cancel()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24294, this, zeroLengthObjectArray);
      this.delegate.cancel();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24294, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24307, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject24307, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject24307, onErrorForAll(methodObject24307, e));
    }
  }
  
  public ResultSet executeQuery(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24301, this, new Object[] { arg0 });
      return postForExecuteQuery(methodObject24301, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(arg0), this, this.proxyCache, methodObject24301));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject24301, (ResultSet)onErrorForAll(methodObject24301, e));
    }
  }
  
  public boolean execute(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24290, this, new Object[] { arg0 });
      return postForExecute(methodObject24290, this.delegate.execute(arg0));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24290, onErrorForExecute(methodObject24290, e));
    }
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24295, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject24295, this.proxyFactory.proxyForCache((Object)this.delegate.getResultSet(), this, this.proxyCache, methodObject24295));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject24295, onErrorForAll(methodObject24295, e));
    }
  }
  
  public Connection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24302, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject24302, (Object)super.getConnection());
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject24302, onErrorForAll(methodObject24302, e));
    }
  }
  
  public Statement _getDelegate_()
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
      methodObject24293 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, String[].class });
      methodObject24306 = Statement.class.getDeclaredMethod("getFetchSize", new Class[0]);
      methodObject24313 = Statement.class.getDeclaredMethod("getGeneratedKeys", new Class[0]);
      methodObject24315 = Statement.class.getDeclaredMethod("getMaxRows", new Class[0]);
      methodObject24297 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, Integer.TYPE });
      methodObject24300 = Statement.class.getDeclaredMethod("executeBatch", new Class[0]);
      methodObject24298 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, int[].class });
      methodObject24320 = Statement.class.getDeclaredMethod("getResultSetType", new Class[0]);
      methodObject24291 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, Integer.TYPE });
      methodObject24289 = Statement.class.getDeclaredMethod("close", new Class[0]);
      methodObject24328 = Statement.class.getDeclaredMethod("setQueryTimeout", new Class[] { Integer.TYPE });
      methodObject24305 = Statement.class.getDeclaredMethod("getFetchDirection", new Class[0]);
      methodObject24317 = Statement.class.getDeclaredMethod("getMoreResults", new Class[] { Integer.TYPE });
      methodObject24318 = Statement.class.getDeclaredMethod("getQueryTimeout", new Class[0]);
      methodObject24303 = Statement.class.getDeclaredMethod("getResultSetHoldability", new Class[0]);
      methodObject24299 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, String[].class });
      methodObject24329 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject24319 = Statement.class.getDeclaredMethod("getResultSetConcurrency", new Class[0]);
      methodObject24304 = Statement.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject24325 = Statement.class.getDeclaredMethod("setMaxFieldSize", new Class[] { Integer.TYPE });
      methodObject24327 = Statement.class.getDeclaredMethod("setPoolable", new Class[] { Boolean.TYPE });
      methodObject24324 = Statement.class.getDeclaredMethod("setEscapeProcessing", new Class[] { Boolean.TYPE });
      methodObject24310 = Statement.class.getDeclaredMethod("setFetchSize", new Class[] { Integer.TYPE });
      methodObject24312 = Statement.class.getDeclaredMethod("clearBatch", new Class[0]);
      methodObject24330 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject24322 = Statement.class.getDeclaredMethod("isPoolable", new Class[0]);
      methodObject24316 = Statement.class.getDeclaredMethod("getMoreResults", new Class[0]);
      methodObject24296 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class });
      methodObject24326 = Statement.class.getDeclaredMethod("setMaxRows", new Class[] { Integer.TYPE });
      methodObject24311 = Statement.class.getDeclaredMethod("addBatch", new Class[] { String.class });
      methodObject24323 = Statement.class.getDeclaredMethod("setCursorName", new Class[] { String.class });
      methodObject24292 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, int[].class });
      methodObject24308 = Statement.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject24321 = Statement.class.getDeclaredMethod("getUpdateCount", new Class[0]);
      methodObject24314 = Statement.class.getDeclaredMethod("getMaxFieldSize", new Class[0]);
      methodObject24309 = Statement.class.getDeclaredMethod("setFetchDirection", new Class[] { Integer.TYPE });
      methodObject24294 = Statement.class.getDeclaredMethod("cancel", new Class[0]);
      methodObject24307 = Statement.class.getDeclaredMethod("getWarnings", new Class[0]);
      methodObject24301 = Statement.class.getDeclaredMethod("executeQuery", new Class[] { String.class });
      methodObject24290 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class });
      methodObject24295 = Statement.class.getDeclaredMethod("getResultSet", new Class[0]);
      methodObject24302 = Statement.class.getDeclaredMethod("getConnection", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2java$1sql$1Statement$$$Proxy(Statement paramStatement, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramStatement;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2java$1sql$1Statement$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */