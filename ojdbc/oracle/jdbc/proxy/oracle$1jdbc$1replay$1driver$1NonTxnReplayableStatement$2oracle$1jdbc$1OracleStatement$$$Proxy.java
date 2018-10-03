package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Wrapper;
import java.util.Map;
import oracle.jdbc.OracleResultSetCache;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import oracle.jdbc.replay.driver.NonTxnReplayableStatement;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1OracleStatement$$$Proxy
  extends NonTxnReplayableStatement
  implements OracleStatement, _Proxy_
{
  private OracleStatement delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject25033;
  private static Method methodObject25005;
  private static Method methodObject24999;
  private static Method methodObject25018;
  private static Method methodObject25011;
  private static Method methodObject25009;
  private static Method methodObject25025;
  private static Method methodObject25004;
  private static Method methodObject25008;
  private static Method methodObject24997;
  private static Method methodObject24995;
  private static Method methodObject25045;
  private static Method methodObject24994;
  private static Method methodObject25044;
  private static Method methodObject25030;
  private static Method methodObject24996;
  private static Method methodObject25032;
  private static Method methodObject25042;
  private static Method methodObject24998;
  private static Method methodObject25036;
  private static Method methodObject25046;
  private static Method methodObject24991;
  private static Method methodObject25043;
  private static Method methodObject25041;
  private static Method methodObject24992;
  private static Method methodObject25013;
  private static Method methodObject25026;
  private static Method methodObject25035;
  private static Method methodObject25017;
  private static Method methodObject25020;
  private static Method methodObject25040;
  private static Method methodObject25048;
  private static Method methodObject25037;
  private static Method methodObject25023;
  private static Method methodObject25038;
  private static Method methodObject25007;
  private static Method methodObject25006;
  private static Method methodObject25019;
  private static Method methodObject25049;
  private static Method methodObject25039;
  private static Method methodObject25024;
  private static Method methodObject25002;
  private static Method methodObject25047;
  private static Method methodObject25050;
  private static Method methodObject24993;
  private static Method methodObject25016;
  private static Method methodObject25003;
  private static Method methodObject25031;
  private static Method methodObject25012;
  private static Method methodObject25028;
  private static Method methodObject25034;
  private static Method methodObject25001;
  private static Method methodObject25029;
  private static Method methodObject25014;
  private static Method methodObject25000;
  private static Method methodObject25027;
  private static Method methodObject25010;
  private static Method methodObject25021;
  private static Method methodObject25015;
  private static Method methodObject25022;
  
  public ResultSet getGeneratedKeys()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25033, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject25033, this.proxyFactory.proxyForCache((Object)this.delegate.getGeneratedKeys(), this, this.proxyCache, methodObject25033));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject25033, onErrorForAll(methodObject25033, e));
    }
  }
  
  public int creationState()
  {
    super.preForAll(methodObject25005, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25005, Integer.valueOf(this.delegate.creationState()))).intValue();
  }
  
  public int getRowPrefetch()
  {
    super.preForAll(methodObject24999, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject24999, Integer.valueOf(this.delegate.getRowPrefetch()))).intValue();
  }
  
  public int executeUpdate(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25018, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject25018, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25018, ((Integer)onErrorForAll(methodObject25018, e)).intValue());
    }
  }
  
  public boolean execute(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25011, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecute(methodObject25011, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25011, onErrorForExecute(methodObject25011, e));
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25009, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClose(methodObject25009);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForClose(methodObject25009, e);
    }
  }
  
  public int getFetchDirection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25025, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25025, Integer.valueOf(this.delegate.getFetchDirection()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25025, onErrorForAll(methodObject25025, e))).intValue();
    }
  }
  
  public void closeWithKey(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25004, this, new Object[] { arg0 });
      this.delegate.closeWithKey(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25004, e);
    }
  }
  
  public long getRegisteredQueryId()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25008, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject25008, Long.valueOf(this.delegate.getRegisteredQueryId()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject25008, onErrorForAll(methodObject25008, e))).longValue();
    }
  }
  
  public void defineColumnTypeBytes(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24997, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnTypeBytes(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24997, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, int arg2, short arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24995, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2), Short.valueOf(arg3) });
      this.delegate.defineColumnType(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24995, e);
    }
  }
  
  public void setMaxFieldSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25045, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxFieldSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25045, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24994, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24994, e);
    }
  }
  
  public void setEscapeProcessing(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25044, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setEscapeProcessing(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25044, e);
    }
  }
  
  public void setFetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25030, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25030, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24996, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.defineColumnType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24996, e);
    }
  }
  
  public void clearBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25032, this, zeroLengthObjectArray);
      this.delegate.clearBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25032, e);
    }
  }
  
  public boolean isPoolable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25042, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject25042, Boolean.valueOf(this.delegate.isPoolable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25042, onErrorForAll(methodObject25042, e))).booleanValue();
    }
  }
  
  public void defineColumnTypeChars(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24998, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnTypeChars(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24998, e);
    }
  }
  
  public boolean getMoreResults()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25036, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject25036, Boolean.valueOf(this.delegate.getMoreResults()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25036, onErrorForAll(methodObject25036, e))).booleanValue();
    }
  }
  
  public void setMaxRows(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25046, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxRows(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25046, e);
    }
  }
  
  public boolean isNCHAR(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24991, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject24991, Boolean.valueOf(this.delegate.isNCHAR(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24991, onErrorForAll(methodObject24991, e))).booleanValue();
    }
  }
  
  public void setCursorName(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25043, this, new Object[] { arg0 });
      this.delegate.setCursorName(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25043, e);
    }
  }
  
  public int getUpdateCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25041, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25041, Integer.valueOf(this.delegate.getUpdateCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25041, onErrorForAll(methodObject25041, e))).intValue();
    }
  }
  
  public void clearDefines()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24992, this, zeroLengthObjectArray);
      this.delegate.clearDefines();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24992, e);
    }
  }
  
  public boolean execute(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25013, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject25013, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25013, onErrorForExecute(methodObject25013, e));
    }
  }
  
  public int getFetchSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25026, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25026, Integer.valueOf(this.delegate.getFetchSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25026, onErrorForAll(methodObject25026, e))).intValue();
    }
  }
  
  public int getMaxRows()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25035, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25035, Integer.valueOf(this.delegate.getMaxRows()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25035, onErrorForAll(methodObject25035, e))).intValue();
    }
  }
  
  public int executeUpdate(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25017, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecuteUpdate(methodObject25017, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25017, ((Integer)onErrorForAll(methodObject25017, e)).intValue());
    }
  }
  
  public int[] executeBatch()
    throws SQLException
  {
    try
    {
      super.preForExecuteBatch(methodObject25020, this, zeroLengthObjectArray);
      return (int[])postForAll(methodObject25020, (Object)this.delegate.executeBatch());
    }
    catch (SQLException e)
    {
      return (int[])postForAll(methodObject25020, onErrorForAll(methodObject25020, e));
    }
  }
  
  public int getResultSetType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25040, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25040, Integer.valueOf(this.delegate.getResultSetType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25040, onErrorForAll(methodObject25040, e))).intValue();
    }
  }
  
  public void setQueryTimeout(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25048, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setQueryTimeout(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25048, e);
    }
  }
  
  public boolean getMoreResults(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25037, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject25037, Boolean.valueOf(this.delegate.getMoreResults(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25037, onErrorForAll(methodObject25037, e))).booleanValue();
    }
  }
  
  public int getResultSetHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25023, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25023, Integer.valueOf(this.delegate.getResultSetHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25023, onErrorForAll(methodObject25023, e))).intValue();
    }
  }
  
  public int getQueryTimeout()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25038, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25038, Integer.valueOf(this.delegate.getQueryTimeout()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25038, onErrorForAll(methodObject25038, e))).intValue();
    }
  }
  
  public String[] getRegisteredTableNames()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25007, this, zeroLengthObjectArray);
      return (String[])postForAll(methodObject25007, (Object)this.delegate.getRegisteredTableNames());
    }
    catch (SQLException e)
    {
      return (String[])postForAll(methodObject25007, onErrorForAll(methodObject25007, e));
    }
  }
  
  public void setDatabaseChangeRegistration(DatabaseChangeRegistration arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25006, this, new Object[] { arg0 });
      this.delegate.setDatabaseChangeRegistration(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25006, e);
    }
  }
  
  public int executeUpdate(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25019, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject25019, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25019, ((Integer)onErrorForAll(methodObject25019, e)).intValue());
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25049, this, new Object[] { arg0 });
      return postForAll(methodObject25049, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject25049, onErrorForAll(methodObject25049, e));
    }
  }
  
  public int getResultSetConcurrency()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25039, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25039, Integer.valueOf(this.delegate.getResultSetConcurrency()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25039, onErrorForAll(methodObject25039, e))).intValue();
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25024, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25024, e);
    }
  }
  
  public int getLobPrefetchSize()
  {
    super.preForAll(methodObject25002, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25002, Integer.valueOf(this.delegate.getLobPrefetchSize()))).intValue();
  }
  
  public void setPoolable(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25047, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setPoolable(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25047, e);
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25050, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject25050, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25050, onErrorForAll(methodObject25050, e))).booleanValue();
    }
  }
  
  public void defineColumnType(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24993, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.defineColumnType(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24993, e);
    }
  }
  
  public int executeUpdate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25016, this, new Object[] { arg0 });
      return postForExecuteUpdate(methodObject25016, this.delegate.executeUpdate(arg0));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25016, ((Integer)onErrorForAll(methodObject25016, e)).intValue());
    }
  }
  
  public void setLobPrefetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25003, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setLobPrefetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25003, e);
    }
  }
  
  public void addBatch(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25031, this, new Object[] { arg0 });
      this.delegate.addBatch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25031, e);
    }
  }
  
  public boolean execute(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25012, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject25012, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25012, onErrorForExecute(methodObject25012, e));
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25028, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject25028, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25028, onErrorForAll(methodObject25028, e))).booleanValue();
    }
  }
  
  public int getMaxFieldSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25034, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25034, Integer.valueOf(this.delegate.getMaxFieldSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25034, onErrorForAll(methodObject25034, e))).intValue();
    }
  }
  
  public void setRowPrefetch(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25001, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setRowPrefetch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25001, e);
    }
  }
  
  public void setFetchDirection(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25029, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchDirection(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25029, e);
    }
  }
  
  public void cancel()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25014, this, zeroLengthObjectArray);
      this.delegate.cancel();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25014, e);
    }
  }
  
  public void setResultSetCache(OracleResultSetCache arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25000, this, new Object[] { arg0 });
      this.delegate.setResultSetCache((arg0 instanceof _Proxy_) ? (OracleResultSetCache)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25000, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25027, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject25027, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject25027, onErrorForAll(methodObject25027, e));
    }
  }
  
  public boolean execute(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25010, this, new Object[] { arg0 });
      return postForExecute(methodObject25010, this.delegate.execute(arg0));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25010, onErrorForExecute(methodObject25010, e));
    }
  }
  
  public ResultSet executeQuery(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25021, this, new Object[] { arg0 });
      return postForExecuteQuery(methodObject25021, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(arg0), this, this.proxyCache, methodObject25021));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject25021, (ResultSet)onErrorForAll(methodObject25021, e));
    }
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25015, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject25015, this.proxyFactory.proxyForCache((Object)this.delegate.getResultSet(), this, this.proxyCache, methodObject25015));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject25015, onErrorForAll(methodObject25015, e));
    }
  }
  
  public Connection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25022, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject25022, (Object)super.getConnection());
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject25022, onErrorForAll(methodObject25022, e));
    }
  }
  
  public OracleStatement _getDelegate_()
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
      methodObject25033 = Statement.class.getDeclaredMethod("getGeneratedKeys", new Class[0]);
      methodObject25005 = OracleStatement.class.getDeclaredMethod("creationState", new Class[0]);
      methodObject24999 = OracleStatement.class.getDeclaredMethod("getRowPrefetch", new Class[0]);
      methodObject25018 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, int[].class });
      methodObject25011 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, Integer.TYPE });
      methodObject25009 = Statement.class.getDeclaredMethod("close", new Class[0]);
      methodObject25025 = Statement.class.getDeclaredMethod("getFetchDirection", new Class[0]);
      methodObject25004 = OracleStatement.class.getDeclaredMethod("closeWithKey", new Class[] { String.class });
      methodObject25008 = OracleStatement.class.getDeclaredMethod("getRegisteredQueryId", new Class[0]);
      methodObject24997 = OracleStatement.class.getDeclaredMethod("defineColumnTypeBytes", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24995 = OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Short.TYPE });
      methodObject25045 = Statement.class.getDeclaredMethod("setMaxFieldSize", new Class[] { Integer.TYPE });
      methodObject24994 = OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25044 = Statement.class.getDeclaredMethod("setEscapeProcessing", new Class[] { Boolean.TYPE });
      methodObject25030 = Statement.class.getDeclaredMethod("setFetchSize", new Class[] { Integer.TYPE });
      methodObject24996 = OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject25032 = Statement.class.getDeclaredMethod("clearBatch", new Class[0]);
      methodObject25042 = Statement.class.getDeclaredMethod("isPoolable", new Class[0]);
      methodObject24998 = OracleStatement.class.getDeclaredMethod("defineColumnTypeChars", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25036 = Statement.class.getDeclaredMethod("getMoreResults", new Class[0]);
      methodObject25046 = Statement.class.getDeclaredMethod("setMaxRows", new Class[] { Integer.TYPE });
      methodObject24991 = OracleStatement.class.getDeclaredMethod("isNCHAR", new Class[] { Integer.TYPE });
      methodObject25043 = Statement.class.getDeclaredMethod("setCursorName", new Class[] { String.class });
      methodObject25041 = Statement.class.getDeclaredMethod("getUpdateCount", new Class[0]);
      methodObject24992 = OracleStatement.class.getDeclaredMethod("clearDefines", new Class[0]);
      methodObject25013 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, String[].class });
      methodObject25026 = Statement.class.getDeclaredMethod("getFetchSize", new Class[0]);
      methodObject25035 = Statement.class.getDeclaredMethod("getMaxRows", new Class[0]);
      methodObject25017 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, Integer.TYPE });
      methodObject25020 = Statement.class.getDeclaredMethod("executeBatch", new Class[0]);
      methodObject25040 = Statement.class.getDeclaredMethod("getResultSetType", new Class[0]);
      methodObject25048 = Statement.class.getDeclaredMethod("setQueryTimeout", new Class[] { Integer.TYPE });
      methodObject25037 = Statement.class.getDeclaredMethod("getMoreResults", new Class[] { Integer.TYPE });
      methodObject25023 = Statement.class.getDeclaredMethod("getResultSetHoldability", new Class[0]);
      methodObject25038 = Statement.class.getDeclaredMethod("getQueryTimeout", new Class[0]);
      methodObject25007 = OracleStatement.class.getDeclaredMethod("getRegisteredTableNames", new Class[0]);
      methodObject25006 = OracleStatement.class.getDeclaredMethod("setDatabaseChangeRegistration", new Class[] { DatabaseChangeRegistration.class });
      methodObject25019 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, String[].class });
      methodObject25049 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject25039 = Statement.class.getDeclaredMethod("getResultSetConcurrency", new Class[0]);
      methodObject25024 = Statement.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject25002 = OracleStatement.class.getDeclaredMethod("getLobPrefetchSize", new Class[0]);
      methodObject25047 = Statement.class.getDeclaredMethod("setPoolable", new Class[] { Boolean.TYPE });
      methodObject25050 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject24993 = OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject25016 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class });
      methodObject25003 = OracleStatement.class.getDeclaredMethod("setLobPrefetchSize", new Class[] { Integer.TYPE });
      methodObject25031 = Statement.class.getDeclaredMethod("addBatch", new Class[] { String.class });
      methodObject25012 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, int[].class });
      methodObject25028 = Statement.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject25034 = Statement.class.getDeclaredMethod("getMaxFieldSize", new Class[0]);
      methodObject25001 = OracleStatement.class.getDeclaredMethod("setRowPrefetch", new Class[] { Integer.TYPE });
      methodObject25029 = Statement.class.getDeclaredMethod("setFetchDirection", new Class[] { Integer.TYPE });
      methodObject25014 = Statement.class.getDeclaredMethod("cancel", new Class[0]);
      methodObject25000 = OracleStatement.class.getDeclaredMethod("setResultSetCache", new Class[] { OracleResultSetCache.class });
      methodObject25027 = Statement.class.getDeclaredMethod("getWarnings", new Class[0]);
      methodObject25010 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class });
      methodObject25021 = Statement.class.getDeclaredMethod("executeQuery", new Class[] { String.class });
      methodObject25015 = Statement.class.getDeclaredMethod("getResultSet", new Class[0]);
      methodObject25022 = Statement.class.getDeclaredMethod("getConnection", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1OracleStatement$$$Proxy(OracleStatement paramOracleStatement, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleStatement;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1OracleStatement$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */