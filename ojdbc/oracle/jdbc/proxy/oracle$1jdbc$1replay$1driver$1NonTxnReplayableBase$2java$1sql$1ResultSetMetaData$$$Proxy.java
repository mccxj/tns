package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1ResultSetMetaData$$$Proxy
  extends NonTxnReplayableBase
  implements ResultSetMetaData, _Proxy_
{
  private ResultSetMetaData delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32297;
  private static Method methodObject32284;
  private static Method methodObject32289;
  private static Method methodObject32282;
  private static Method methodObject32299;
  private static Method methodObject32298;
  private static Method methodObject32285;
  private static Method methodObject32287;
  private static Method methodObject32303;
  private static Method methodObject32291;
  private static Method methodObject32290;
  private static Method methodObject32286;
  private static Method methodObject32281;
  private static Method methodObject32295;
  private static Method methodObject32300;
  private static Method methodObject32301;
  private static Method methodObject32294;
  private static Method methodObject32296;
  private static Method methodObject32292;
  private static Method methodObject32283;
  private static Method methodObject32302;
  private static Method methodObject32293;
  private static Method methodObject32288;
  
  public boolean isCaseSensitive(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32297, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32297, Boolean.valueOf(this.delegate.isCaseSensitive(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32297, onErrorForAll(methodObject32297, e))).booleanValue();
    }
  }
  
  public int isNullable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32284, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32284, Integer.valueOf(this.delegate.isNullable(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32284, onErrorForAll(methodObject32284, e))).intValue();
    }
  }
  
  public int getColumnDisplaySize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32289, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32289, Integer.valueOf(this.delegate.getColumnDisplaySize(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32289, onErrorForAll(methodObject32289, e))).intValue();
    }
  }
  
  public int getPrecision(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32282, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32282, Integer.valueOf(this.delegate.getPrecision(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32282, onErrorForAll(methodObject32282, e))).intValue();
    }
  }
  
  public boolean isDefinitelyWritable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32299, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32299, Boolean.valueOf(this.delegate.isDefinitelyWritable(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32299, onErrorForAll(methodObject32299, e))).booleanValue();
    }
  }
  
  public boolean isCurrency(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32298, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32298, Boolean.valueOf(this.delegate.isCurrency(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32298, onErrorForAll(methodObject32298, e))).booleanValue();
    }
  }
  
  public boolean isSigned(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32285, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32285, Boolean.valueOf(this.delegate.isSigned(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32285, onErrorForAll(methodObject32285, e))).booleanValue();
    }
  }
  
  public String getColumnClassName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32287, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32287, (Object)this.delegate.getColumnClassName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32287, onErrorForAll(methodObject32287, e));
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32303, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject32303, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32303, onErrorForAll(methodObject32303, e))).booleanValue();
    }
  }
  
  public String getColumnName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32291, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32291, (Object)this.delegate.getColumnName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32291, onErrorForAll(methodObject32291, e));
    }
  }
  
  public String getColumnLabel(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32290, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32290, (Object)this.delegate.getColumnLabel(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32290, onErrorForAll(methodObject32290, e));
    }
  }
  
  public String getCatalogName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32286, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32286, (Object)this.delegate.getCatalogName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32286, onErrorForAll(methodObject32286, e));
    }
  }
  
  public boolean isReadOnly(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32281, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32281, Boolean.valueOf(this.delegate.isReadOnly(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32281, onErrorForAll(methodObject32281, e))).booleanValue();
    }
  }
  
  public String getTableName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32295, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32295, (Object)this.delegate.getTableName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32295, onErrorForAll(methodObject32295, e));
    }
  }
  
  public boolean isSearchable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32300, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32300, Boolean.valueOf(this.delegate.isSearchable(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32300, onErrorForAll(methodObject32300, e))).booleanValue();
    }
  }
  
  public boolean isWritable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32301, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32301, Boolean.valueOf(this.delegate.isWritable(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32301, onErrorForAll(methodObject32301, e))).booleanValue();
    }
  }
  
  public String getSchemaName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32294, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32294, (Object)this.delegate.getSchemaName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32294, onErrorForAll(methodObject32294, e));
    }
  }
  
  public boolean isAutoIncrement(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32296, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32296, Boolean.valueOf(this.delegate.isAutoIncrement(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32296, onErrorForAll(methodObject32296, e))).booleanValue();
    }
  }
  
  public int getColumnType(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32292, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32292, Integer.valueOf(this.delegate.getColumnType(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32292, onErrorForAll(methodObject32292, e))).intValue();
    }
  }
  
  public int getScale(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32283, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32283, Integer.valueOf(this.delegate.getScale(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32283, onErrorForAll(methodObject32283, e))).intValue();
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32302, this, new Object[] { arg0 });
      return postForAll(methodObject32302, this.proxyFactory.proxyForCache(this.delegate.unwrap(arg0), this, this.proxyCache, methodObject32302));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject32302, onErrorForAll(methodObject32302, e));
    }
  }
  
  public String getColumnTypeName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32293, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32293, (Object)this.delegate.getColumnTypeName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32293, onErrorForAll(methodObject32293, e));
    }
  }
  
  public int getColumnCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32288, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32288, Integer.valueOf(this.delegate.getColumnCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32288, onErrorForAll(methodObject32288, e))).intValue();
    }
  }
  
  public ResultSetMetaData _getDelegate_()
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
      methodObject32297 = ResultSetMetaData.class.getDeclaredMethod("isCaseSensitive", new Class[] { Integer.TYPE });
      methodObject32284 = ResultSetMetaData.class.getDeclaredMethod("isNullable", new Class[] { Integer.TYPE });
      methodObject32289 = ResultSetMetaData.class.getDeclaredMethod("getColumnDisplaySize", new Class[] { Integer.TYPE });
      methodObject32282 = ResultSetMetaData.class.getDeclaredMethod("getPrecision", new Class[] { Integer.TYPE });
      methodObject32299 = ResultSetMetaData.class.getDeclaredMethod("isDefinitelyWritable", new Class[] { Integer.TYPE });
      methodObject32298 = ResultSetMetaData.class.getDeclaredMethod("isCurrency", new Class[] { Integer.TYPE });
      methodObject32285 = ResultSetMetaData.class.getDeclaredMethod("isSigned", new Class[] { Integer.TYPE });
      methodObject32287 = ResultSetMetaData.class.getDeclaredMethod("getColumnClassName", new Class[] { Integer.TYPE });
      methodObject32303 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject32291 = ResultSetMetaData.class.getDeclaredMethod("getColumnName", new Class[] { Integer.TYPE });
      methodObject32290 = ResultSetMetaData.class.getDeclaredMethod("getColumnLabel", new Class[] { Integer.TYPE });
      methodObject32286 = ResultSetMetaData.class.getDeclaredMethod("getCatalogName", new Class[] { Integer.TYPE });
      methodObject32281 = ResultSetMetaData.class.getDeclaredMethod("isReadOnly", new Class[] { Integer.TYPE });
      methodObject32295 = ResultSetMetaData.class.getDeclaredMethod("getTableName", new Class[] { Integer.TYPE });
      methodObject32300 = ResultSetMetaData.class.getDeclaredMethod("isSearchable", new Class[] { Integer.TYPE });
      methodObject32301 = ResultSetMetaData.class.getDeclaredMethod("isWritable", new Class[] { Integer.TYPE });
      methodObject32294 = ResultSetMetaData.class.getDeclaredMethod("getSchemaName", new Class[] { Integer.TYPE });
      methodObject32296 = ResultSetMetaData.class.getDeclaredMethod("isAutoIncrement", new Class[] { Integer.TYPE });
      methodObject32292 = ResultSetMetaData.class.getDeclaredMethod("getColumnType", new Class[] { Integer.TYPE });
      methodObject32283 = ResultSetMetaData.class.getDeclaredMethod("getScale", new Class[] { Integer.TYPE });
      methodObject32302 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject32293 = ResultSetMetaData.class.getDeclaredMethod("getColumnTypeName", new Class[] { Integer.TYPE });
      methodObject32288 = ResultSetMetaData.class.getDeclaredMethod("getColumnCount", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1ResultSetMetaData$$$Proxy(ResultSetMetaData paramResultSetMetaData, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramResultSetMetaData;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1ResultSetMetaData$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */