package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Map;
import oracle.jdbc.OracleResultSetMetaData.SecurityAttribute;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1internal$1OracleResultSetMetaData$$$Proxy
  extends NonTxnReplayableBase
  implements oracle.jdbc.internal.OracleResultSetMetaData, _Proxy_
{
  private oracle.jdbc.internal.OracleResultSetMetaData delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32649;
  private static Method methodObject32647;
  private static Method methodObject32664;
  private static Method methodObject32663;
  private static Method methodObject32650;
  private static Method methodObject32655;
  private static Method methodObject32651;
  private static Method methodObject32646;
  private static Method methodObject32665;
  private static Method methodObject32648;
  private static Method methodObject32667;
  private static Method methodObject32653;
  private static Method methodObject32662;
  private static Method methodObject32654;
  private static Method methodObject32652;
  private static Method methodObject32656;
  private static Method methodObject32668;
  private static Method methodObject32644;
  private static Method methodObject32660;
  private static Method methodObject32645;
  private static Method methodObject32666;
  private static Method methodObject32659;
  private static Method methodObject32657;
  private static Method methodObject32661;
  private static Method methodObject32658;
  
  public int isNullable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32649, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32649, Integer.valueOf(this.delegate.isNullable(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32649, onErrorForAll(methodObject32649, e))).intValue();
    }
  }
  
  public int getPrecision(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32647, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32647, Integer.valueOf(this.delegate.getPrecision(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32647, onErrorForAll(methodObject32647, e))).intValue();
    }
  }
  
  public boolean isDefinitelyWritable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32664, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32664, Boolean.valueOf(this.delegate.isDefinitelyWritable(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32664, onErrorForAll(methodObject32664, e))).booleanValue();
    }
  }
  
  public boolean isCurrency(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32663, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32663, Boolean.valueOf(this.delegate.isCurrency(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32663, onErrorForAll(methodObject32663, e))).booleanValue();
    }
  }
  
  public boolean isSigned(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32650, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32650, Boolean.valueOf(this.delegate.isSigned(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32650, onErrorForAll(methodObject32650, e))).booleanValue();
    }
  }
  
  public String getColumnLabel(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32655, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32655, (Object)this.delegate.getColumnLabel(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32655, onErrorForAll(methodObject32655, e));
    }
  }
  
  public String getCatalogName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32651, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32651, (Object)this.delegate.getCatalogName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32651, onErrorForAll(methodObject32651, e));
    }
  }
  
  public boolean isReadOnly(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32646, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32646, Boolean.valueOf(this.delegate.isReadOnly(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32646, onErrorForAll(methodObject32646, e))).booleanValue();
    }
  }
  
  public boolean isSearchable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32665, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32665, Boolean.valueOf(this.delegate.isSearchable(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32665, onErrorForAll(methodObject32665, e))).booleanValue();
    }
  }
  
  public int getScale(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32648, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32648, Integer.valueOf(this.delegate.getScale(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32648, onErrorForAll(methodObject32648, e))).intValue();
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32667, this, new Object[] { arg0 });
      return postForAll(methodObject32667, this.proxyFactory.proxyForCache(this.delegate.unwrap(arg0), this, this.proxyCache, methodObject32667));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject32667, onErrorForAll(methodObject32667, e));
    }
  }
  
  public int getColumnCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32653, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32653, Integer.valueOf(this.delegate.getColumnCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32653, onErrorForAll(methodObject32653, e))).intValue();
    }
  }
  
  public boolean isCaseSensitive(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32662, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32662, Boolean.valueOf(this.delegate.isCaseSensitive(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32662, onErrorForAll(methodObject32662, e))).booleanValue();
    }
  }
  
  public int getColumnDisplaySize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32654, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32654, Integer.valueOf(this.delegate.getColumnDisplaySize(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32654, onErrorForAll(methodObject32654, e))).intValue();
    }
  }
  
  public String getColumnClassName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32652, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32652, (Object)this.delegate.getColumnClassName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32652, onErrorForAll(methodObject32652, e));
    }
  }
  
  public String getColumnName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32656, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32656, (Object)this.delegate.getColumnName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32656, onErrorForAll(methodObject32656, e));
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32668, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject32668, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32668, onErrorForAll(methodObject32668, e))).booleanValue();
    }
  }
  
  public boolean isNCHAR(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32644, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32644, Boolean.valueOf(this.delegate.isNCHAR(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32644, onErrorForAll(methodObject32644, e))).booleanValue();
    }
  }
  
  public String getTableName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32660, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32660, (Object)this.delegate.getTableName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32660, onErrorForAll(methodObject32660, e));
    }
  }
  
  public OracleResultSetMetaData.SecurityAttribute getSecurityAttribute(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32645, this, new Object[] { Integer.valueOf(arg0) });
      return (OracleResultSetMetaData.SecurityAttribute)postForAll(methodObject32645, (Object)this.delegate.getSecurityAttribute(arg0));
    }
    catch (SQLException e)
    {
      return (OracleResultSetMetaData.SecurityAttribute)postForAll(methodObject32645, onErrorForAll(methodObject32645, e));
    }
  }
  
  public boolean isWritable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32666, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32666, Boolean.valueOf(this.delegate.isWritable(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32666, onErrorForAll(methodObject32666, e))).booleanValue();
    }
  }
  
  public String getSchemaName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32659, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32659, (Object)this.delegate.getSchemaName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32659, onErrorForAll(methodObject32659, e));
    }
  }
  
  public int getColumnType(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32657, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject32657, Integer.valueOf(this.delegate.getColumnType(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32657, onErrorForAll(methodObject32657, e))).intValue();
    }
  }
  
  public boolean isAutoIncrement(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32661, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32661, Boolean.valueOf(this.delegate.isAutoIncrement(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32661, onErrorForAll(methodObject32661, e))).booleanValue();
    }
  }
  
  public String getColumnTypeName(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32658, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject32658, (Object)this.delegate.getColumnTypeName(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32658, onErrorForAll(methodObject32658, e));
    }
  }
  
  public oracle.jdbc.internal.OracleResultSetMetaData _getDelegate_()
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
      methodObject32649 = ResultSetMetaData.class.getDeclaredMethod("isNullable", new Class[] { Integer.TYPE });
      methodObject32647 = ResultSetMetaData.class.getDeclaredMethod("getPrecision", new Class[] { Integer.TYPE });
      methodObject32664 = ResultSetMetaData.class.getDeclaredMethod("isDefinitelyWritable", new Class[] { Integer.TYPE });
      methodObject32663 = ResultSetMetaData.class.getDeclaredMethod("isCurrency", new Class[] { Integer.TYPE });
      methodObject32650 = ResultSetMetaData.class.getDeclaredMethod("isSigned", new Class[] { Integer.TYPE });
      methodObject32655 = ResultSetMetaData.class.getDeclaredMethod("getColumnLabel", new Class[] { Integer.TYPE });
      methodObject32651 = ResultSetMetaData.class.getDeclaredMethod("getCatalogName", new Class[] { Integer.TYPE });
      methodObject32646 = ResultSetMetaData.class.getDeclaredMethod("isReadOnly", new Class[] { Integer.TYPE });
      methodObject32665 = ResultSetMetaData.class.getDeclaredMethod("isSearchable", new Class[] { Integer.TYPE });
      methodObject32648 = ResultSetMetaData.class.getDeclaredMethod("getScale", new Class[] { Integer.TYPE });
      methodObject32667 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject32653 = ResultSetMetaData.class.getDeclaredMethod("getColumnCount", new Class[0]);
      methodObject32662 = ResultSetMetaData.class.getDeclaredMethod("isCaseSensitive", new Class[] { Integer.TYPE });
      methodObject32654 = ResultSetMetaData.class.getDeclaredMethod("getColumnDisplaySize", new Class[] { Integer.TYPE });
      methodObject32652 = ResultSetMetaData.class.getDeclaredMethod("getColumnClassName", new Class[] { Integer.TYPE });
      methodObject32656 = ResultSetMetaData.class.getDeclaredMethod("getColumnName", new Class[] { Integer.TYPE });
      methodObject32668 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject32644 = oracle.jdbc.OracleResultSetMetaData.class.getDeclaredMethod("isNCHAR", new Class[] { Integer.TYPE });
      methodObject32660 = ResultSetMetaData.class.getDeclaredMethod("getTableName", new Class[] { Integer.TYPE });
      methodObject32645 = oracle.jdbc.OracleResultSetMetaData.class.getDeclaredMethod("getSecurityAttribute", new Class[] { Integer.TYPE });
      methodObject32666 = ResultSetMetaData.class.getDeclaredMethod("isWritable", new Class[] { Integer.TYPE });
      methodObject32659 = ResultSetMetaData.class.getDeclaredMethod("getSchemaName", new Class[] { Integer.TYPE });
      methodObject32657 = ResultSetMetaData.class.getDeclaredMethod("getColumnType", new Class[] { Integer.TYPE });
      methodObject32661 = ResultSetMetaData.class.getDeclaredMethod("isAutoIncrement", new Class[] { Integer.TYPE });
      methodObject32658 = ResultSetMetaData.class.getDeclaredMethod("getColumnTypeName", new Class[] { Integer.TYPE });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1internal$1OracleResultSetMetaData$$$Proxy(oracle.jdbc.internal.OracleResultSetMetaData paramOracleResultSetMetaData, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleResultSetMetaData;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1internal$1OracleResultSetMetaData$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */