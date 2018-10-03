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
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import oracle.jdbc.internal.OracleStatement.SqlKind;
import oracle.jdbc.replay.driver.NonTxnReplayableStatement;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1internal$1OracleStatement$$$Proxy
  extends NonTxnReplayableStatement
  implements oracle.jdbc.internal.OracleStatement, _Proxy_
{
  private oracle.jdbc.internal.OracleStatement delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject25804;
  private static Method methodObject25776;
  private static Method methodObject25770;
  private static Method methodObject25789;
  private static Method methodObject25782;
  private static Method methodObject25780;
  private static Method methodObject25796;
  private static Method methodObject25775;
  private static Method methodObject25759;
  private static Method methodObject25757;
  private static Method methodObject25779;
  private static Method methodObject25768;
  private static Method methodObject25766;
  private static Method methodObject25755;
  private static Method methodObject25754;
  private static Method methodObject25816;
  private static Method methodObject25756;
  private static Method methodObject25765;
  private static Method methodObject25815;
  private static Method methodObject25801;
  private static Method methodObject25767;
  private static Method methodObject25753;
  private static Method methodObject25803;
  private static Method methodObject25813;
  private static Method methodObject25807;
  private static Method methodObject25769;
  private static Method methodObject25817;
  private static Method methodObject25762;
  private static Method methodObject25814;
  private static Method methodObject25812;
  private static Method methodObject25763;
  private static Method methodObject25760;
  private static Method methodObject25784;
  private static Method methodObject25797;
  private static Method methodObject25806;
  private static Method methodObject25788;
  private static Method methodObject25791;
  private static Method methodObject25811;
  private static Method methodObject25819;
  private static Method methodObject25808;
  private static Method methodObject25809;
  private static Method methodObject25794;
  private static Method methodObject25761;
  private static Method methodObject25778;
  private static Method methodObject25777;
  private static Method methodObject25790;
  private static Method methodObject25820;
  private static Method methodObject25810;
  private static Method methodObject25795;
  private static Method methodObject25773;
  private static Method methodObject25818;
  private static Method methodObject25821;
  private static Method methodObject25764;
  private static Method methodObject25787;
  private static Method methodObject25774;
  private static Method methodObject25802;
  private static Method methodObject25783;
  private static Method methodObject25799;
  private static Method methodObject25805;
  private static Method methodObject25772;
  private static Method methodObject25800;
  private static Method methodObject25785;
  private static Method methodObject25771;
  private static Method methodObject25798;
  private static Method methodObject25758;
  private static Method methodObject25781;
  private static Method methodObject25792;
  private static Method methodObject25786;
  private static Method methodObject25793;
  
  public ResultSet getGeneratedKeys()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25804, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject25804, this.proxyFactory.proxyForCache((Object)this.delegate.getGeneratedKeys(), this, this.proxyCache, methodObject25804));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject25804, onErrorForAll(methodObject25804, e));
    }
  }
  
  public int creationState()
  {
    super.preForAll(methodObject25776, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25776, Integer.valueOf(this.delegate.creationState()))).intValue();
  }
  
  public int getRowPrefetch()
  {
    super.preForAll(methodObject25770, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25770, Integer.valueOf(this.delegate.getRowPrefetch()))).intValue();
  }
  
  public int executeUpdate(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25789, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject25789, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25789, ((Integer)onErrorForAll(methodObject25789, e)).intValue());
    }
  }
  
  public boolean execute(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25782, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecute(methodObject25782, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25782, onErrorForExecute(methodObject25782, e));
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25780, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClose(methodObject25780);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForClose(methodObject25780, e);
    }
  }
  
  public int getFetchDirection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25796, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25796, Integer.valueOf(this.delegate.getFetchDirection()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25796, onErrorForAll(methodObject25796, e))).intValue();
    }
  }
  
  public void closeWithKey(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25775, this, new Object[] { arg0 });
      this.delegate.closeWithKey(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25775, e);
    }
  }
  
  public boolean getserverCursor()
  {
    super.preForAll(methodObject25759, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject25759, Boolean.valueOf(this.delegate.getserverCursor()))).booleanValue();
  }
  
  public boolean getFixedString()
  {
    super.preForAll(methodObject25757, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject25757, Boolean.valueOf(this.delegate.getFixedString()))).booleanValue();
  }
  
  public long getRegisteredQueryId()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25779, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject25779, Long.valueOf(this.delegate.getRegisteredQueryId()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject25779, onErrorForAll(methodObject25779, e))).longValue();
    }
  }
  
  public void defineColumnTypeBytes(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25768, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnTypeBytes(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25768, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, int arg2, short arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25766, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2), Short.valueOf(arg3) });
      this.delegate.defineColumnType(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25766, e);
    }
  }
  
  public void setSnapshotSCN(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25755, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.setSnapshotSCN(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25755, e);
    }
  }
  
  public long getChecksum()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25754, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject25754, Long.valueOf(this.delegate.getChecksum()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject25754, onErrorForAll(methodObject25754, e))).longValue();
    }
  }
  
  public void setMaxFieldSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25816, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxFieldSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25816, e);
    }
  }
  
  public void setFixedString(boolean arg0)
  {
    super.preForAll(methodObject25756, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setFixedString(arg0);
  }
  
  public void defineColumnType(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25765, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25765, e);
    }
  }
  
  public void setEscapeProcessing(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25815, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setEscapeProcessing(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25815, e);
    }
  }
  
  public void setFetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25801, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25801, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25767, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.defineColumnType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25767, e);
    }
  }
  
  public OracleStatement.SqlKind getSqlKind()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25753, this, zeroLengthObjectArray);
      return (OracleStatement.SqlKind)postForAll(methodObject25753, (Object)this.delegate.getSqlKind());
    }
    catch (SQLException e)
    {
      return (OracleStatement.SqlKind)postForAll(methodObject25753, onErrorForAll(methodObject25753, e));
    }
  }
  
  public void clearBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25803, this, zeroLengthObjectArray);
      this.delegate.clearBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25803, e);
    }
  }
  
  public boolean isPoolable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25813, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject25813, Boolean.valueOf(this.delegate.isPoolable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25813, onErrorForAll(methodObject25813, e))).booleanValue();
    }
  }
  
  public boolean getMoreResults()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25807, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject25807, Boolean.valueOf(this.delegate.getMoreResults()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25807, onErrorForAll(methodObject25807, e))).booleanValue();
    }
  }
  
  public void defineColumnTypeChars(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25769, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnTypeChars(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25769, e);
    }
  }
  
  public void setMaxRows(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25817, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxRows(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25817, e);
    }
  }
  
  public boolean isNCHAR(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25762, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject25762, Boolean.valueOf(this.delegate.isNCHAR(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25762, onErrorForAll(methodObject25762, e))).booleanValue();
    }
  }
  
  public void setCursorName(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25814, this, new Object[] { arg0 });
      this.delegate.setCursorName(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25814, e);
    }
  }
  
  public int getUpdateCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25812, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25812, Integer.valueOf(this.delegate.getUpdateCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25812, onErrorForAll(methodObject25812, e))).intValue();
    }
  }
  
  public void clearDefines()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25763, this, zeroLengthObjectArray);
      this.delegate.clearDefines();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25763, e);
    }
  }
  
  public int getcacheState()
  {
    super.preForAll(methodObject25760, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25760, Integer.valueOf(this.delegate.getcacheState()))).intValue();
  }
  
  public boolean execute(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25784, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject25784, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25784, onErrorForExecute(methodObject25784, e));
    }
  }
  
  public int getFetchSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25797, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25797, Integer.valueOf(this.delegate.getFetchSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25797, onErrorForAll(methodObject25797, e))).intValue();
    }
  }
  
  public int getMaxRows()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25806, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25806, Integer.valueOf(this.delegate.getMaxRows()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25806, onErrorForAll(methodObject25806, e))).intValue();
    }
  }
  
  public int executeUpdate(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25788, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecuteUpdate(methodObject25788, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25788, ((Integer)onErrorForAll(methodObject25788, e)).intValue());
    }
  }
  
  public int[] executeBatch()
    throws SQLException
  {
    try
    {
      super.preForExecuteBatch(methodObject25791, this, zeroLengthObjectArray);
      return (int[])postForAll(methodObject25791, (Object)this.delegate.executeBatch());
    }
    catch (SQLException e)
    {
      return (int[])postForAll(methodObject25791, onErrorForAll(methodObject25791, e));
    }
  }
  
  public int getResultSetType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25811, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25811, Integer.valueOf(this.delegate.getResultSetType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25811, onErrorForAll(methodObject25811, e))).intValue();
    }
  }
  
  public void setQueryTimeout(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25819, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setQueryTimeout(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25819, e);
    }
  }
  
  public boolean getMoreResults(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25808, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject25808, Boolean.valueOf(this.delegate.getMoreResults(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25808, onErrorForAll(methodObject25808, e))).booleanValue();
    }
  }
  
  public int getQueryTimeout()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25809, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25809, Integer.valueOf(this.delegate.getQueryTimeout()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25809, onErrorForAll(methodObject25809, e))).intValue();
    }
  }
  
  public int getResultSetHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25794, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25794, Integer.valueOf(this.delegate.getResultSetHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25794, onErrorForAll(methodObject25794, e))).intValue();
    }
  }
  
  public int getstatementType()
  {
    super.preForAll(methodObject25761, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25761, Integer.valueOf(this.delegate.getstatementType()))).intValue();
  }
  
  public String[] getRegisteredTableNames()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25778, this, zeroLengthObjectArray);
      return (String[])postForAll(methodObject25778, (Object)this.delegate.getRegisteredTableNames());
    }
    catch (SQLException e)
    {
      return (String[])postForAll(methodObject25778, onErrorForAll(methodObject25778, e));
    }
  }
  
  public void setDatabaseChangeRegistration(DatabaseChangeRegistration arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25777, this, new Object[] { arg0 });
      this.delegate.setDatabaseChangeRegistration(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25777, e);
    }
  }
  
  public int executeUpdate(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25790, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject25790, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25790, ((Integer)onErrorForAll(methodObject25790, e)).intValue());
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25820, this, new Object[] { arg0 });
      return postForAll(methodObject25820, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject25820, onErrorForAll(methodObject25820, e));
    }
  }
  
  public int getResultSetConcurrency()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25810, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25810, Integer.valueOf(this.delegate.getResultSetConcurrency()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25810, onErrorForAll(methodObject25810, e))).intValue();
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25795, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25795, e);
    }
  }
  
  public int getLobPrefetchSize()
  {
    super.preForAll(methodObject25773, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25773, Integer.valueOf(this.delegate.getLobPrefetchSize()))).intValue();
  }
  
  public void setPoolable(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25818, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setPoolable(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25818, e);
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25821, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject25821, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25821, onErrorForAll(methodObject25821, e))).booleanValue();
    }
  }
  
  public void defineColumnType(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25764, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.defineColumnType(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25764, e);
    }
  }
  
  public int executeUpdate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25787, this, new Object[] { arg0 });
      return postForExecuteUpdate(methodObject25787, this.delegate.executeUpdate(arg0));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25787, ((Integer)onErrorForAll(methodObject25787, e)).intValue());
    }
  }
  
  public void setLobPrefetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25774, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setLobPrefetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25774, e);
    }
  }
  
  public void addBatch(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25802, this, new Object[] { arg0 });
      this.delegate.addBatch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25802, e);
    }
  }
  
  public boolean execute(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25783, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject25783, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25783, onErrorForExecute(methodObject25783, e));
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25799, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject25799, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25799, onErrorForAll(methodObject25799, e))).booleanValue();
    }
  }
  
  public int getMaxFieldSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25805, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25805, Integer.valueOf(this.delegate.getMaxFieldSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25805, onErrorForAll(methodObject25805, e))).intValue();
    }
  }
  
  public void setRowPrefetch(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25772, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setRowPrefetch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25772, e);
    }
  }
  
  public void setFetchDirection(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25800, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchDirection(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25800, e);
    }
  }
  
  public void cancel()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25785, this, zeroLengthObjectArray);
      this.delegate.cancel();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25785, e);
    }
  }
  
  public void setResultSetCache(OracleResultSetCache arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25771, this, new Object[] { arg0 });
      this.delegate.setResultSetCache((arg0 instanceof _Proxy_) ? (OracleResultSetCache)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25771, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25798, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject25798, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject25798, onErrorForAll(methodObject25798, e));
    }
  }
  
  public int sendBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25758, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25758, Integer.valueOf(this.delegate.sendBatch()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25758, onErrorForAll(methodObject25758, e))).intValue();
    }
  }
  
  public boolean execute(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25781, this, new Object[] { arg0 });
      return postForExecute(methodObject25781, this.delegate.execute(arg0));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25781, onErrorForExecute(methodObject25781, e));
    }
  }
  
  public ResultSet executeQuery(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25792, this, new Object[] { arg0 });
      return postForExecuteQuery(methodObject25792, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(arg0), this, this.proxyCache, methodObject25792));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject25792, (ResultSet)onErrorForAll(methodObject25792, e));
    }
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25786, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject25786, this.proxyFactory.proxyForCache((Object)this.delegate.getResultSet(), this, this.proxyCache, methodObject25786));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject25786, onErrorForAll(methodObject25786, e));
    }
  }
  
  public Connection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25793, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject25793, (Object)super.getConnection());
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject25793, onErrorForAll(methodObject25793, e));
    }
  }
  
  public oracle.jdbc.internal.OracleStatement _getDelegate_()
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
      methodObject25804 = Statement.class.getDeclaredMethod("getGeneratedKeys", new Class[0]);
      methodObject25776 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("creationState", new Class[0]);
      methodObject25770 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("getRowPrefetch", new Class[0]);
      methodObject25789 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, int[].class });
      methodObject25782 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, Integer.TYPE });
      methodObject25780 = Statement.class.getDeclaredMethod("close", new Class[0]);
      methodObject25796 = Statement.class.getDeclaredMethod("getFetchDirection", new Class[0]);
      methodObject25775 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("closeWithKey", new Class[] { String.class });
      methodObject25759 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getserverCursor", new Class[0]);
      methodObject25757 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getFixedString", new Class[0]);
      methodObject25779 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("getRegisteredQueryId", new Class[0]);
      methodObject25768 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnTypeBytes", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25766 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Short.TYPE });
      methodObject25755 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("setSnapshotSCN", new Class[] { Long.TYPE });
      methodObject25754 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getChecksum", new Class[0]);
      methodObject25816 = Statement.class.getDeclaredMethod("setMaxFieldSize", new Class[] { Integer.TYPE });
      methodObject25756 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("setFixedString", new Class[] { Boolean.TYPE });
      methodObject25765 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25815 = Statement.class.getDeclaredMethod("setEscapeProcessing", new Class[] { Boolean.TYPE });
      methodObject25801 = Statement.class.getDeclaredMethod("setFetchSize", new Class[] { Integer.TYPE });
      methodObject25767 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject25753 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getSqlKind", new Class[0]);
      methodObject25803 = Statement.class.getDeclaredMethod("clearBatch", new Class[0]);
      methodObject25813 = Statement.class.getDeclaredMethod("isPoolable", new Class[0]);
      methodObject25807 = Statement.class.getDeclaredMethod("getMoreResults", new Class[0]);
      methodObject25769 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnTypeChars", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25817 = Statement.class.getDeclaredMethod("setMaxRows", new Class[] { Integer.TYPE });
      methodObject25762 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("isNCHAR", new Class[] { Integer.TYPE });
      methodObject25814 = Statement.class.getDeclaredMethod("setCursorName", new Class[] { String.class });
      methodObject25812 = Statement.class.getDeclaredMethod("getUpdateCount", new Class[0]);
      methodObject25763 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("clearDefines", new Class[0]);
      methodObject25760 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getcacheState", new Class[0]);
      methodObject25784 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, String[].class });
      methodObject25797 = Statement.class.getDeclaredMethod("getFetchSize", new Class[0]);
      methodObject25806 = Statement.class.getDeclaredMethod("getMaxRows", new Class[0]);
      methodObject25788 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, Integer.TYPE });
      methodObject25791 = Statement.class.getDeclaredMethod("executeBatch", new Class[0]);
      methodObject25811 = Statement.class.getDeclaredMethod("getResultSetType", new Class[0]);
      methodObject25819 = Statement.class.getDeclaredMethod("setQueryTimeout", new Class[] { Integer.TYPE });
      methodObject25808 = Statement.class.getDeclaredMethod("getMoreResults", new Class[] { Integer.TYPE });
      methodObject25809 = Statement.class.getDeclaredMethod("getQueryTimeout", new Class[0]);
      methodObject25794 = Statement.class.getDeclaredMethod("getResultSetHoldability", new Class[0]);
      methodObject25761 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getstatementType", new Class[0]);
      methodObject25778 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("getRegisteredTableNames", new Class[0]);
      methodObject25777 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("setDatabaseChangeRegistration", new Class[] { DatabaseChangeRegistration.class });
      methodObject25790 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, String[].class });
      methodObject25820 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject25810 = Statement.class.getDeclaredMethod("getResultSetConcurrency", new Class[0]);
      methodObject25795 = Statement.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject25773 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("getLobPrefetchSize", new Class[0]);
      methodObject25818 = Statement.class.getDeclaredMethod("setPoolable", new Class[] { Boolean.TYPE });
      methodObject25821 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject25764 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject25787 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class });
      methodObject25774 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("setLobPrefetchSize", new Class[] { Integer.TYPE });
      methodObject25802 = Statement.class.getDeclaredMethod("addBatch", new Class[] { String.class });
      methodObject25783 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, int[].class });
      methodObject25799 = Statement.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject25805 = Statement.class.getDeclaredMethod("getMaxFieldSize", new Class[0]);
      methodObject25772 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("setRowPrefetch", new Class[] { Integer.TYPE });
      methodObject25800 = Statement.class.getDeclaredMethod("setFetchDirection", new Class[] { Integer.TYPE });
      methodObject25785 = Statement.class.getDeclaredMethod("cancel", new Class[0]);
      methodObject25771 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("setResultSetCache", new Class[] { OracleResultSetCache.class });
      methodObject25798 = Statement.class.getDeclaredMethod("getWarnings", new Class[0]);
      methodObject25758 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("sendBatch", new Class[0]);
      methodObject25781 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class });
      methodObject25792 = Statement.class.getDeclaredMethod("executeQuery", new Class[] { String.class });
      methodObject25786 = Statement.class.getDeclaredMethod("getResultSet", new Class[0]);
      methodObject25793 = Statement.class.getDeclaredMethod("getConnection", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1internal$1OracleStatement$$$Proxy(oracle.jdbc.internal.OracleStatement paramOracleStatement, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleStatement;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1internal$1OracleStatement$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */