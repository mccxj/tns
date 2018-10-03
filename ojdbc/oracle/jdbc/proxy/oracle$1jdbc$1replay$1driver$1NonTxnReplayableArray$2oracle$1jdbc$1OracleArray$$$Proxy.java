package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.OracleArray;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.replay.driver.NonTxnReplayableArray;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableArray$2oracle$1jdbc$1OracleArray$$$Proxy
  extends NonTxnReplayableArray
  implements OracleArray, _Proxy_
{
  private OracleArray delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject12867;
  private static Method methodObject12843;
  private static Method methodObject12859;
  private static Method methodObject12857;
  private static Method methodObject12858;
  private static Method methodObject12855;
  private static Method methodObject12856;
  private static Method methodObject12848;
  private static Method methodObject12844;
  private static Method methodObject12847;
  private static Method methodObject12852;
  private static Method methodObject12862;
  private static Method methodObject12849;
  private static Method methodObject12845;
  private static Method methodObject12863;
  private static Method methodObject12850;
  private static Method methodObject12861;
  private static Method methodObject12853;
  private static Method methodObject12854;
  private static Method methodObject12851;
  private static Method methodObject12846;
  private static Method methodObject12865;
  private static Method methodObject12866;
  private static Method methodObject12860;
  private static Method methodObject12864;
  
  public ResultSet getResultSet(long arg0, int arg1, Map arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12867, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      return (ResultSet)postForAll(methodObject12867, this.proxyFactory.proxyForCreate((Object)this.delegate.getResultSet(arg0, arg1, arg2), this, this.proxyCache, methodObject12867));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject12867, onErrorForAll(methodObject12867, e));
    }
  }
  
  public int length()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12843, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject12843, Integer.valueOf(this.delegate.length()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject12843, onErrorForAll(methodObject12843, e))).intValue();
    }
  }
  
  public Object getArray(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12859, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return postForAll(methodObject12859, this.proxyFactory.proxyForCache(this.delegate.getArray(arg0, arg1), this, this.proxyCache, methodObject12859));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject12859, onErrorForAll(methodObject12859, e));
    }
  }
  
  public Object getArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12857, this, zeroLengthObjectArray);
      return postForAll(methodObject12857, this.proxyFactory.proxyForCache(this.delegate.getArray(), this, this.proxyCache, methodObject12857));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject12857, onErrorForAll(methodObject12857, e));
    }
  }
  
  public Object getArray(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12858, this, new Object[] { arg0 });
      return postForAll(methodObject12858, this.proxyFactory.proxyForCache(this.delegate.getArray(arg0), this, this.proxyCache, methodObject12858));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject12858, onErrorForAll(methodObject12858, e));
    }
  }
  
  public float[] getFloatArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12855, this, zeroLengthObjectArray);
      return (float[])postForAll(methodObject12855, (Object)this.delegate.getFloatArray());
    }
    catch (SQLException e)
    {
      return (float[])postForAll(methodObject12855, onErrorForAll(methodObject12855, e));
    }
  }
  
  public float[] getFloatArray(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12856, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (float[])postForAll(methodObject12856, (Object)this.delegate.getFloatArray(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (float[])postForAll(methodObject12856, onErrorForAll(methodObject12856, e));
    }
  }
  
  public int[] getIntArray(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12848, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (int[])postForAll(methodObject12848, (Object)this.delegate.getIntArray(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (int[])postForAll(methodObject12848, onErrorForAll(methodObject12848, e));
    }
  }
  
  public String getSQLTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12844, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject12844, (Object)this.delegate.getSQLTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject12844, onErrorForAll(methodObject12844, e));
    }
  }
  
  public int[] getIntArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12847, this, zeroLengthObjectArray);
      return (int[])postForAll(methodObject12847, (Object)this.delegate.getIntArray());
    }
    catch (SQLException e)
    {
      return (int[])postForAll(methodObject12847, onErrorForAll(methodObject12847, e));
    }
  }
  
  public short[] getShortArray(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12852, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (short[])postForAll(methodObject12852, (Object)this.delegate.getShortArray(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (short[])postForAll(methodObject12852, onErrorForAll(methodObject12852, e));
    }
  }
  
  public int getBaseType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12862, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject12862, Integer.valueOf(this.delegate.getBaseType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject12862, onErrorForAll(methodObject12862, e))).intValue();
    }
  }
  
  public double[] getDoubleArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12849, this, zeroLengthObjectArray);
      return (double[])postForAll(methodObject12849, (Object)this.delegate.getDoubleArray());
    }
    catch (SQLException e)
    {
      return (double[])postForAll(methodObject12849, onErrorForAll(methodObject12849, e));
    }
  }
  
  public Object toJdbc()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12845, this, zeroLengthObjectArray);
      return postForAll(methodObject12845, this.proxyFactory.proxyForCache(this.delegate.toJdbc(), this, this.proxyCache, methodObject12845));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject12845, onErrorForAll(methodObject12845, e));
    }
  }
  
  public String getBaseTypeName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12863, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject12863, (Object)this.delegate.getBaseTypeName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject12863, onErrorForAll(methodObject12863, e));
    }
  }
  
  public double[] getDoubleArray(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12850, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (double[])postForAll(methodObject12850, (Object)this.delegate.getDoubleArray(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (double[])postForAll(methodObject12850, onErrorForAll(methodObject12850, e));
    }
  }
  
  public void free()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12861, this, zeroLengthObjectArray);
      this.delegate.free();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject12861, e);
    }
  }
  
  public long[] getLongArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12853, this, zeroLengthObjectArray);
      return (long[])postForAll(methodObject12853, (Object)this.delegate.getLongArray());
    }
    catch (SQLException e)
    {
      return (long[])postForAll(methodObject12853, onErrorForAll(methodObject12853, e));
    }
  }
  
  public long[] getLongArray(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12854, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (long[])postForAll(methodObject12854, (Object)this.delegate.getLongArray(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (long[])postForAll(methodObject12854, onErrorForAll(methodObject12854, e));
    }
  }
  
  public short[] getShortArray()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12851, this, zeroLengthObjectArray);
      return (short[])postForAll(methodObject12851, (Object)this.delegate.getShortArray());
    }
    catch (SQLException e)
    {
      return (short[])postForAll(methodObject12851, onErrorForAll(methodObject12851, e));
    }
  }
  
  public OracleTypeMetaData getOracleMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12846, this, zeroLengthObjectArray);
      return (OracleTypeMetaData)postForAll(methodObject12846, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleMetaData(), this, this.proxyCache, methodObject12846));
    }
    catch (SQLException e)
    {
      return (OracleTypeMetaData)postForAll(methodObject12846, onErrorForAll(methodObject12846, e));
    }
  }
  
  public ResultSet getResultSet(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12865, this, new Object[] { arg0 });
      return (ResultSet)postForAll(methodObject12865, this.proxyFactory.proxyForCreate((Object)this.delegate.getResultSet(arg0), this, this.proxyCache, methodObject12865));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject12865, onErrorForAll(methodObject12865, e));
    }
  }
  
  public ResultSet getResultSet(long arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12866, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1) });
      return (ResultSet)postForAll(methodObject12866, this.proxyFactory.proxyForCreate((Object)this.delegate.getResultSet(arg0, arg1), this, this.proxyCache, methodObject12866));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject12866, onErrorForAll(methodObject12866, e));
    }
  }
  
  public Object getArray(long arg0, int arg1, Map arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12860, this, new Object[] { Long.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      return postForAll(methodObject12860, this.proxyFactory.proxyForCache(this.delegate.getArray(arg0, arg1, arg2), this, this.proxyCache, methodObject12860));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject12860, onErrorForAll(methodObject12860, e));
    }
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject12864, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject12864, this.proxyFactory.proxyForCreate((Object)this.delegate.getResultSet(), this, this.proxyCache, methodObject12864));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject12864, onErrorForAll(methodObject12864, e));
    }
  }
  
  public OracleArray _getDelegate_()
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
      methodObject12867 = Array.class.getDeclaredMethod("getResultSet", new Class[] { Long.TYPE, Integer.TYPE, Map.class });
      methodObject12843 = OracleArray.class.getDeclaredMethod("length", new Class[0]);
      methodObject12859 = Array.class.getDeclaredMethod("getArray", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12857 = Array.class.getDeclaredMethod("getArray", new Class[0]);
      methodObject12858 = Array.class.getDeclaredMethod("getArray", new Class[] { Map.class });
      methodObject12855 = OracleArray.class.getDeclaredMethod("getFloatArray", new Class[0]);
      methodObject12856 = OracleArray.class.getDeclaredMethod("getFloatArray", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12848 = OracleArray.class.getDeclaredMethod("getIntArray", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12844 = OracleArray.class.getDeclaredMethod("getSQLTypeName", new Class[0]);
      methodObject12847 = OracleArray.class.getDeclaredMethod("getIntArray", new Class[0]);
      methodObject12852 = OracleArray.class.getDeclaredMethod("getShortArray", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12862 = Array.class.getDeclaredMethod("getBaseType", new Class[0]);
      methodObject12849 = OracleArray.class.getDeclaredMethod("getDoubleArray", new Class[0]);
      methodObject12845 = OracleArray.class.getDeclaredMethod("toJdbc", new Class[0]);
      methodObject12863 = Array.class.getDeclaredMethod("getBaseTypeName", new Class[0]);
      methodObject12850 = OracleArray.class.getDeclaredMethod("getDoubleArray", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12861 = Array.class.getDeclaredMethod("free", new Class[0]);
      methodObject12853 = OracleArray.class.getDeclaredMethod("getLongArray", new Class[0]);
      methodObject12854 = OracleArray.class.getDeclaredMethod("getLongArray", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12851 = OracleArray.class.getDeclaredMethod("getShortArray", new Class[0]);
      methodObject12846 = OracleArray.class.getDeclaredMethod("getOracleMetaData", new Class[0]);
      methodObject12865 = Array.class.getDeclaredMethod("getResultSet", new Class[] { Map.class });
      methodObject12866 = Array.class.getDeclaredMethod("getResultSet", new Class[] { Long.TYPE, Integer.TYPE });
      methodObject12860 = Array.class.getDeclaredMethod("getArray", new Class[] { Long.TYPE, Integer.TYPE, Map.class });
      methodObject12864 = Array.class.getDeclaredMethod("getResultSet", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableArray$2oracle$1jdbc$1OracleArray$$$Proxy(OracleArray paramOracleArray, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleArray;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableArray$2oracle$1jdbc$1OracleArray$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */