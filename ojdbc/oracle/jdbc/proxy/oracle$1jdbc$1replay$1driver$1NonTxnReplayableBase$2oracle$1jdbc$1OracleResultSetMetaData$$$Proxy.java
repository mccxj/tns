package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Map;
import oracle.jdbc.OracleResultSetMetaData;
import oracle.jdbc.OracleResultSetMetaData.SecurityAttribute;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleResultSetMetaData$$$Proxy
  extends NonTxnReplayableBase
  implements OracleResultSetMetaData, _Proxy_
{
  private OracleResultSetMetaData delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32545;
  private static Method methodObject32543;
  private static Method methodObject32560;
  private static Method methodObject32559;
  private static Method methodObject32546;
  private static Method methodObject32551;
  private static Method methodObject32547;
  private static Method methodObject32542;
  private static Method methodObject32561;
  private static Method methodObject32544;
  private static Method methodObject32563;
  private static Method methodObject32549;
  private static Method methodObject32558;
  private static Method methodObject32550;
  private static Method methodObject32548;
  private static Method methodObject32552;
  private static Method methodObject32564;
  private static Method methodObject32540;
  private static Method methodObject32556;
  private static Method methodObject32541;
  private static Method methodObject32562;
  private static Method methodObject32555;
  private static Method methodObject32553;
  private static Method methodObject32557;
  private static Method methodObject32554;
  
  public int isNullable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32545, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32545, Integer.valueOf(this.delegate.isNullable(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32545, onErrorForAll(methodObject32545, e))).intValue();
    }
  }
  
  public int getPrecision(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32543, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32543, Integer.valueOf(this.delegate.getPrecision(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32543, onErrorForAll(methodObject32543, e))).intValue();
    }
  }
  
  public boolean isDefinitelyWritable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32560, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32560, Boolean.valueOf(this.delegate.isDefinitelyWritable(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32560, onErrorForAll(methodObject32560, e))).booleanValue();
    }
  }
  
  public boolean isCurrency(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32559, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32559, Boolean.valueOf(this.delegate.isCurrency(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32559, onErrorForAll(methodObject32559, e))).booleanValue();
    }
  }
  
  public boolean isSigned(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32546, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32546, Boolean.valueOf(this.delegate.isSigned(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32546, onErrorForAll(methodObject32546, e))).booleanValue();
    }
  }
  
  public String getColumnLabel(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32551, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32551, (Object)this.delegate.getColumnLabel(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32551, onErrorForAll(methodObject32551, e));
    }
  }
  
  public String getCatalogName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32547, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32547, (Object)this.delegate.getCatalogName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32547, onErrorForAll(methodObject32547, e));
    }
  }
  
  public boolean isReadOnly(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32542, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32542, Boolean.valueOf(this.delegate.isReadOnly(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32542, onErrorForAll(methodObject32542, e))).booleanValue();
    }
  }
  
  public boolean isSearchable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32561, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32561, Boolean.valueOf(this.delegate.isSearchable(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32561, onErrorForAll(methodObject32561, e))).booleanValue();
    }
  }
  
  public int getScale(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32544, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32544, Integer.valueOf(this.delegate.getScale(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32544, onErrorForAll(methodObject32544, e))).intValue();
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32563, this, new Object[] { arg0 });
      return postForAll(methodObject32563, this.proxyFactory.proxyForCache(this.delegate.unwrap(arg0), this, this.proxyCache, methodObject32563));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject32563, onErrorForAll(methodObject32563, e));
    }
  }
  
  public int getColumnCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32549, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32549, Integer.valueOf(this.delegate.getColumnCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32549, onErrorForAll(methodObject32549, e))).intValue();
    }
  }
  
  public boolean isCaseSensitive(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32558, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32558, Boolean.valueOf(this.delegate.isCaseSensitive(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32558, onErrorForAll(methodObject32558, e))).booleanValue();
    }
  }
  
  public int getColumnDisplaySize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32550, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32550, Integer.valueOf(this.delegate.getColumnDisplaySize(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32550, onErrorForAll(methodObject32550, e))).intValue();
    }
  }
  
  public String getColumnClassName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32548, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32548, (Object)this.delegate.getColumnClassName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32548, onErrorForAll(methodObject32548, e));
    }
  }
  
  public String getColumnName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32552, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32552, (Object)this.delegate.getColumnName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32552, onErrorForAll(methodObject32552, e));
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32564, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject32564, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32564, onErrorForAll(methodObject32564, e))).booleanValue();
    }
  }
  
  public boolean isNCHAR(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32540, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32540, Boolean.valueOf(this.delegate.isNCHAR(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32540, onErrorForAll(methodObject32540, e))).booleanValue();
    }
  }
  
  public String getTableName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32556, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32556, (Object)this.delegate.getTableName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32556, onErrorForAll(methodObject32556, e));
    }
  }
  
  public OracleResultSetMetaData.SecurityAttribute getSecurityAttribute(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32541, this, new Object[] { Integer.valueOf(arg0) });
      return (OracleResultSetMetaData.SecurityAttribute)postForAll(methodObject32541, (Object)this.delegate.getSecurityAttribute(arg0));
    }
    catch (SQLException e)
    {
      return (OracleResultSetMetaData.SecurityAttribute)postForAll(methodObject32541, onErrorForAll(methodObject32541, e));
    }
  }
  
  public boolean isWritable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32562, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32562, Boolean.valueOf(this.delegate.isWritable(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32562, onErrorForAll(methodObject32562, e))).booleanValue();
    }
  }
  
  public String getSchemaName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32555, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32555, (Object)this.delegate.getSchemaName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32555, onErrorForAll(methodObject32555, e));
    }
  }
  
  public int getColumnType(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32553, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32553, Integer.valueOf(this.delegate.getColumnType(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32553, onErrorForAll(methodObject32553, e))).intValue();
    }
  }
  
  public boolean isAutoIncrement(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32557, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32557, Boolean.valueOf(this.delegate.isAutoIncrement(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32557, onErrorForAll(methodObject32557, e))).booleanValue();
    }
  }
  
  public String getColumnTypeName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32554, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32554, (Object)this.delegate.getColumnTypeName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32554, onErrorForAll(methodObject32554, e));
    }
  }
  
  public OracleResultSetMetaData _getDelegate_()
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
      methodObject32545 = ResultSetMetaData.class.getDeclaredMethod("isNullable", new Class[] { Integer.TYPE });
      methodObject32543 = ResultSetMetaData.class.getDeclaredMethod("getPrecision", new Class[] { Integer.TYPE });
      methodObject32560 = ResultSetMetaData.class.getDeclaredMethod("isDefinitelyWritable", new Class[] { Integer.TYPE });
      methodObject32559 = ResultSetMetaData.class.getDeclaredMethod("isCurrency", new Class[] { Integer.TYPE });
      methodObject32546 = ResultSetMetaData.class.getDeclaredMethod("isSigned", new Class[] { Integer.TYPE });
      methodObject32551 = ResultSetMetaData.class.getDeclaredMethod("getColumnLabel", new Class[] { Integer.TYPE });
      methodObject32547 = ResultSetMetaData.class.getDeclaredMethod("getCatalogName", new Class[] { Integer.TYPE });
      methodObject32542 = ResultSetMetaData.class.getDeclaredMethod("isReadOnly", new Class[] { Integer.TYPE });
      methodObject32561 = ResultSetMetaData.class.getDeclaredMethod("isSearchable", new Class[] { Integer.TYPE });
      methodObject32544 = ResultSetMetaData.class.getDeclaredMethod("getScale", new Class[] { Integer.TYPE });
      methodObject32563 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject32549 = ResultSetMetaData.class.getDeclaredMethod("getColumnCount", new Class[0]);
      methodObject32558 = ResultSetMetaData.class.getDeclaredMethod("isCaseSensitive", new Class[] { Integer.TYPE });
      methodObject32550 = ResultSetMetaData.class.getDeclaredMethod("getColumnDisplaySize", new Class[] { Integer.TYPE });
      methodObject32548 = ResultSetMetaData.class.getDeclaredMethod("getColumnClassName", new Class[] { Integer.TYPE });
      methodObject32552 = ResultSetMetaData.class.getDeclaredMethod("getColumnName", new Class[] { Integer.TYPE });
      methodObject32564 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject32540 = OracleResultSetMetaData.class.getDeclaredMethod("isNCHAR", new Class[] { Integer.TYPE });
      methodObject32556 = ResultSetMetaData.class.getDeclaredMethod("getTableName", new Class[] { Integer.TYPE });
      methodObject32541 = OracleResultSetMetaData.class.getDeclaredMethod("getSecurityAttribute", new Class[] { Integer.TYPE });
      methodObject32562 = ResultSetMetaData.class.getDeclaredMethod("isWritable", new Class[] { Integer.TYPE });
      methodObject32555 = ResultSetMetaData.class.getDeclaredMethod("getSchemaName", new Class[] { Integer.TYPE });
      methodObject32553 = ResultSetMetaData.class.getDeclaredMethod("getColumnType", new Class[] { Integer.TYPE });
      methodObject32557 = ResultSetMetaData.class.getDeclaredMethod("isAutoIncrement", new Class[] { Integer.TYPE });
      methodObject32554 = ResultSetMetaData.class.getDeclaredMethod("getColumnTypeName", new Class[] { Integer.TYPE });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleResultSetMetaData$$$Proxy(OracleResultSetMetaData paramOracleResultSetMetaData, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleResultSetMetaData;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1OracleResultSetMetaData$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */