package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.sql.Wrapper;
import java.util.Map;
import java.util.Properties;
import oracle.jdbc.replay.driver.NonTxnReplayableConnection;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableConnection$2java$1sql$1Connection$$$Proxy
  extends NonTxnReplayableConnection
  implements Connection, _Proxy_
{
  private Connection delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject27266;
  private static Method methodObject27240;
  private static Method methodObject27261;
  private static Method methodObject27244;
  private static Method methodObject27221;
  private static Method methodObject27236;
  private static Method methodObject27225;
  private static Method methodObject27227;
  private static Method methodObject27232;
  private static Method methodObject27241;
  private static Method methodObject27226;
  private static Method methodObject27220;
  private static Method methodObject27233;
  private static Method methodObject27265;
  private static Method methodObject27252;
  private static Method methodObject27256;
  private static Method methodObject27263;
  private static Method methodObject27249;
  private static Method methodObject27228;
  private static Method methodObject27224;
  private static Method methodObject27234;
  private static Method methodObject27253;
  private static Method methodObject27229;
  private static Method methodObject27243;
  private static Method methodObject27255;
  private static Method methodObject27222;
  private static Method methodObject27237;
  private static Method methodObject27231;
  private static Method methodObject27267;
  private static Method methodObject27248;
  private static Method methodObject27259;
  private static Method methodObject27238;
  private static Method methodObject27245;
  private static Method methodObject27230;
  private static Method methodObject27242;
  private static Method methodObject27260;
  private static Method methodObject27239;
  private static Method methodObject27262;
  private static Method methodObject27235;
  private static Method methodObject27268;
  private static Method methodObject27246;
  private static Method methodObject27258;
  private static Method methodObject27264;
  private static Method methodObject27247;
  private static Method methodObject27251;
  private static Method methodObject27254;
  private static Method methodObject27257;
  private static Method methodObject27223;
  private static Method methodObject27250;
  
  public void setTypeMap(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27266, this, new Object[] { arg0 });
      this.delegate.setTypeMap(arg0);
      postForAll(methodObject27266);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27266, e);
    }
  }
  
  public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27240, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return (CallableStatement)postForAll(methodObject27240, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareCall(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject27240));
    }
    catch (SQLException e)
    {
      return (CallableStatement)postForAll(methodObject27240, onErrorForAll(methodObject27240, e));
    }
  }
  
  public void setCatalog(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27261, this, new Object[] { arg0 });
      this.delegate.setCatalog(arg0);
      postForAll(methodObject27261);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27261, e);
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27244, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return (PreparedStatement)postForAll(methodObject27244, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1), this, this.proxyCache, methodObject27244));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27244, onErrorForAll(methodObject27244, e));
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForClosure(methodObject27221, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClosure(methodObject27221);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27221, e);
    }
  }
  
  public Statement createStatement(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27236, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      return (Statement)postForAll(methodObject27236, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createStatement(arg0, arg1), this, this.proxyCache, methodObject27236));
    }
    catch (SQLException e)
    {
      return (Statement)postForAll(methodObject27236, onErrorForAll(methodObject27236, e));
    }
  }
  
  public void rollback(Savepoint arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27225, this, new Object[] { arg0 });
      this.delegate.rollback((arg0 instanceof _Proxy_) ? (Savepoint)((_Proxy_)arg0)._getDelegate_() : arg0);
      postForAll(methodObject27225);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27225, e);
    }
  }
  
  public Savepoint setSavepoint(String arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27227, this, new Object[] { arg0 });
      return (Savepoint)postForAll(methodObject27227, this.proxyFactory.proxyForCreate((Object)this.delegate.setSavepoint(arg0), this, this.proxyCache, methodObject27227));
    }
    catch (SQLException e)
    {
      return (Savepoint)postForAll(methodObject27227, onErrorForAll(methodObject27227, e));
    }
  }
  
  public Clob createClob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27232, this, zeroLengthObjectArray);
      return (Clob)postForAll(methodObject27232, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createClob(), this, this.proxyCache, methodObject27232));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject27232, onErrorForAll(methodObject27232, e));
    }
  }
  
  public PreparedStatement prepareStatement(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27241, this, new Object[] { arg0 });
      return (PreparedStatement)postForAll(methodObject27241, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0), this, this.proxyCache, methodObject27241));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27241, onErrorForAll(methodObject27241, e));
    }
  }
  
  public Savepoint setSavepoint()
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27226, this, zeroLengthObjectArray);
      return (Savepoint)postForAll(methodObject27226, this.proxyFactory.proxyForCreate((Object)this.delegate.setSavepoint(), this, this.proxyCache, methodObject27226));
    }
    catch (SQLException e)
    {
      return (Savepoint)postForAll(methodObject27226, onErrorForAll(methodObject27226, e));
    }
  }
  
  public void setReadOnly(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27220, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setReadOnly(arg0);
      postForAll(methodObject27220);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27220, e);
    }
  }
  
  public NClob createNClob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27233, this, zeroLengthObjectArray);
      return (NClob)postForAll(methodObject27233, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createNClob(), this, this.proxyCache, methodObject27233));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject27233, onErrorForAll(methodObject27233, e));
    }
  }
  
  public void setTransactionIsolation(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27265, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setTransactionIsolation(arg0);
      postForAll(methodObject27265);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27265, e);
    }
  }
  
  public boolean getAutoCommit()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27252, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27252, Boolean.valueOf(this.delegate.getAutoCommit()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27252, onErrorForAll(methodObject27252, e))).booleanValue();
    }
  }
  
  public int getTransactionIsolation()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27256, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27256, Integer.valueOf(this.delegate.getTransactionIsolation()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27256, onErrorForAll(methodObject27256, e))).intValue();
    }
  }
  
  public void setClientInfo(Properties arg0)
    throws SQLClientInfoException
  {
    super.preForAll(methodObject27263, this, new Object[] { arg0 });
    this.delegate.setClientInfo(arg0);
    postForAll(methodObject27263);
  }
  
  public int getHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27249, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27249, Integer.valueOf(this.delegate.getHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27249, onErrorForAll(methodObject27249, e))).intValue();
    }
  }
  
  public void releaseSavepoint(Savepoint arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27228, this, new Object[] { arg0 });
      this.delegate.releaseSavepoint((arg0 instanceof _Proxy_) ? (Savepoint)((_Proxy_)arg0)._getDelegate_() : arg0);
      postForAll(methodObject27228);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27228, e);
    }
  }
  
  public void rollback()
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27224, this, zeroLengthObjectArray);
      this.delegate.rollback();
      postForAll(methodObject27224);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27224, e);
    }
  }
  
  public SQLXML createSQLXML()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27234, this, zeroLengthObjectArray);
      return (SQLXML)postForAll(methodObject27234, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createSQLXML(), this, this.proxyCache, methodObject27234));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject27234, onErrorForAll(methodObject27234, e));
    }
  }
  
  public String getCatalog()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27253, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27253, (Object)this.delegate.getCatalog());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27253, onErrorForAll(methodObject27253, e));
    }
  }
  
  public Array createArrayOf(String arg0, Object[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27229, this, new Object[] { arg0, arg1 });
      return (Array)postForAll(methodObject27229, this.proxyFactory.proxyForCreateCache((Object)super.createArrayOf(arg0, arg1), this, this.proxyCache, methodObject27229));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject27229, onErrorForAll(methodObject27229, e));
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27243, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return (PreparedStatement)postForAll(methodObject27243, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject27243));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27243, onErrorForAll(methodObject27243, e));
    }
  }
  
  public Properties getClientInfo()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27255, this, zeroLengthObjectArray);
      return (Properties)postForAll(methodObject27255, (Object)this.delegate.getClientInfo());
    }
    catch (SQLException e)
    {
      return (Properties)postForAll(methodObject27255, onErrorForAll(methodObject27255, e));
    }
  }
  
  public boolean isReadOnly()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27222, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27222, Boolean.valueOf(this.delegate.isReadOnly()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27222, onErrorForAll(methodObject27222, e))).booleanValue();
    }
  }
  
  public Statement createStatement(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27237, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      return (Statement)postForAll(methodObject27237, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createStatement(arg0, arg1, arg2), this, this.proxyCache, methodObject27237));
    }
    catch (SQLException e)
    {
      return (Statement)postForAll(methodObject27237, onErrorForAll(methodObject27237, e));
    }
  }
  
  public Blob createBlob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27231, this, zeroLengthObjectArray);
      return (Blob)postForAll(methodObject27231, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createBlob(), this, this.proxyCache, methodObject27231));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject27231, onErrorForAll(methodObject27231, e));
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27267, this, new Object[] { arg0 });
      return postForAll(methodObject27267, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject27267, onErrorForAll(methodObject27267, e));
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27248, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      postForAll(methodObject27248);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27248, e);
    }
  }
  
  public String nativeSQL(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27259, this, new Object[] { arg0 });
      return (String)postForAll(methodObject27259, (Object)this.delegate.nativeSQL(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27259, onErrorForAll(methodObject27259, e));
    }
  }
  
  public CallableStatement prepareCall(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27238, this, new Object[] { arg0 });
      return (CallableStatement)postForAll(methodObject27238, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareCall(arg0), this, this.proxyCache, methodObject27238));
    }
    catch (SQLException e)
    {
      return (CallableStatement)postForAll(methodObject27238, onErrorForAll(methodObject27238, e));
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27245, this, new Object[] { arg0, arg1 });
      return (PreparedStatement)postForAll(methodObject27245, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1), this, this.proxyCache, methodObject27245));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27245, onErrorForAll(methodObject27245, e));
    }
  }
  
  public Struct createStruct(String arg0, Object[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27230, this, new Object[] { arg0, arg1 });
      return (Struct)postForAll(methodObject27230, this.proxyFactory.proxyForCreateCache((Object)super.createStruct(arg0, arg1), this, this.proxyCache, methodObject27230));
    }
    catch (SQLException e)
    {
      return (Struct)postForAll(methodObject27230, onErrorForAll(methodObject27230, e));
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27242, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2) });
      return (PreparedStatement)postForAll(methodObject27242, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1, arg2), this, this.proxyCache, methodObject27242));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27242, onErrorForAll(methodObject27242, e));
    }
  }
  
  public void setAutoCommit(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27260, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setAutoCommit(arg0);
      postForAll(methodObject27260);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27260, e);
    }
  }
  
  public CallableStatement prepareCall(String arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27239, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2) });
      return (CallableStatement)postForAll(methodObject27239, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareCall(arg0, arg1, arg2), this, this.proxyCache, methodObject27239));
    }
    catch (SQLException e)
    {
      return (CallableStatement)postForAll(methodObject27239, onErrorForAll(methodObject27239, e));
    }
  }
  
  public void setClientInfo(String arg0, String arg1)
    throws SQLClientInfoException
  {
    super.preForAll(methodObject27262, this, new Object[] { arg0, arg1 });
    this.delegate.setClientInfo(arg0, arg1);
    postForAll(methodObject27262);
  }
  
  public Statement createStatement()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27235, this, zeroLengthObjectArray);
      return (Statement)postForAll(methodObject27235, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createStatement(), this, this.proxyCache, methodObject27235));
    }
    catch (SQLException e)
    {
      return (Statement)postForAll(methodObject27235, onErrorForAll(methodObject27235, e));
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27268, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject27268, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27268, onErrorForAll(methodObject27268, e))).booleanValue();
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27246, this, new Object[] { arg0, arg1 });
      return (PreparedStatement)postForAll(methodObject27246, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1), this, this.proxyCache, methodObject27246));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27246, onErrorForAll(methodObject27246, e));
    }
  }
  
  public boolean isValid(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27258, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject27258, Boolean.valueOf(this.delegate.isValid(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27258, onErrorForAll(methodObject27258, e))).booleanValue();
    }
  }
  
  public void setHoldability(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27264, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setHoldability(arg0);
      postForAll(methodObject27264);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27264, e);
    }
  }
  
  public DatabaseMetaData getMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27247, this, zeroLengthObjectArray);
      return (DatabaseMetaData)postForAll(methodObject27247, this.proxyFactory.proxyForCache((Object)this.delegate.getMetaData(), this, this.proxyCache, methodObject27247));
    }
    catch (SQLException e)
    {
      return (DatabaseMetaData)postForAll(methodObject27247, onErrorForAll(methodObject27247, e));
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27251, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27251, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27251, onErrorForAll(methodObject27251, e))).booleanValue();
    }
  }
  
  public String getClientInfo(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27254, this, new Object[] { arg0 });
      return (String)postForAll(methodObject27254, (Object)this.delegate.getClientInfo(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27254, onErrorForAll(methodObject27254, e));
    }
  }
  
  public Map getTypeMap()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27257, this, zeroLengthObjectArray);
      return (Map)postForAll(methodObject27257, (Object)this.delegate.getTypeMap());
    }
    catch (SQLException e)
    {
      return (Map)postForAll(methodObject27257, onErrorForAll(methodObject27257, e));
    }
  }
  
  public void commit()
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27223, this, zeroLengthObjectArray);
      this.delegate.commit();
      postForAll(methodObject27223);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27223, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27250, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject27250, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject27250, onErrorForAll(methodObject27250, e));
    }
  }
  
  public Connection _getDelegate_()
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
      methodObject27266 = Connection.class.getDeclaredMethod("setTypeMap", new Class[] { Map.class });
      methodObject27240 = Connection.class.getDeclaredMethod("prepareCall", new Class[] { String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject27261 = Connection.class.getDeclaredMethod("setCatalog", new Class[] { String.class });
      methodObject27244 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, Integer.TYPE });
      methodObject27221 = Connection.class.getDeclaredMethod("close", new Class[0]);
      methodObject27236 = Connection.class.getDeclaredMethod("createStatement", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject27225 = Connection.class.getDeclaredMethod("rollback", new Class[] { Savepoint.class });
      methodObject27227 = Connection.class.getDeclaredMethod("setSavepoint", new Class[] { String.class });
      methodObject27232 = Connection.class.getDeclaredMethod("createClob", new Class[0]);
      methodObject27241 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class });
      methodObject27226 = Connection.class.getDeclaredMethod("setSavepoint", new Class[0]);
      methodObject27220 = Connection.class.getDeclaredMethod("setReadOnly", new Class[] { Boolean.TYPE });
      methodObject27233 = Connection.class.getDeclaredMethod("createNClob", new Class[0]);
      methodObject27265 = Connection.class.getDeclaredMethod("setTransactionIsolation", new Class[] { Integer.TYPE });
      methodObject27252 = Connection.class.getDeclaredMethod("getAutoCommit", new Class[0]);
      methodObject27256 = Connection.class.getDeclaredMethod("getTransactionIsolation", new Class[0]);
      methodObject27263 = Connection.class.getDeclaredMethod("setClientInfo", new Class[] { Properties.class });
      methodObject27249 = Connection.class.getDeclaredMethod("getHoldability", new Class[0]);
      methodObject27228 = Connection.class.getDeclaredMethod("releaseSavepoint", new Class[] { Savepoint.class });
      methodObject27224 = Connection.class.getDeclaredMethod("rollback", new Class[0]);
      methodObject27234 = Connection.class.getDeclaredMethod("createSQLXML", new Class[0]);
      methodObject27253 = Connection.class.getDeclaredMethod("getCatalog", new Class[0]);
      methodObject27229 = Connection.class.getDeclaredMethod("createArrayOf", new Class[] { String.class, Object[].class });
      methodObject27243 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject27255 = Connection.class.getDeclaredMethod("getClientInfo", new Class[0]);
      methodObject27222 = Connection.class.getDeclaredMethod("isReadOnly", new Class[0]);
      methodObject27237 = Connection.class.getDeclaredMethod("createStatement", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject27231 = Connection.class.getDeclaredMethod("createBlob", new Class[0]);
      methodObject27267 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject27248 = Connection.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject27259 = Connection.class.getDeclaredMethod("nativeSQL", new Class[] { String.class });
      methodObject27238 = Connection.class.getDeclaredMethod("prepareCall", new Class[] { String.class });
      methodObject27245 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, int[].class });
      methodObject27230 = Connection.class.getDeclaredMethod("createStruct", new Class[] { String.class, Object[].class });
      methodObject27242 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, Integer.TYPE, Integer.TYPE });
      methodObject27260 = Connection.class.getDeclaredMethod("setAutoCommit", new Class[] { Boolean.TYPE });
      methodObject27239 = Connection.class.getDeclaredMethod("prepareCall", new Class[] { String.class, Integer.TYPE, Integer.TYPE });
      methodObject27262 = Connection.class.getDeclaredMethod("setClientInfo", new Class[] { String.class, String.class });
      methodObject27235 = Connection.class.getDeclaredMethod("createStatement", new Class[0]);
      methodObject27268 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject27246 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, String[].class });
      methodObject27258 = Connection.class.getDeclaredMethod("isValid", new Class[] { Integer.TYPE });
      methodObject27264 = Connection.class.getDeclaredMethod("setHoldability", new Class[] { Integer.TYPE });
      methodObject27247 = Connection.class.getDeclaredMethod("getMetaData", new Class[0]);
      methodObject27251 = Connection.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject27254 = Connection.class.getDeclaredMethod("getClientInfo", new Class[] { String.class });
      methodObject27257 = Connection.class.getDeclaredMethod("getTypeMap", new Class[0]);
      methodObject27223 = Connection.class.getDeclaredMethod("commit", new Class[0]);
      methodObject27250 = Connection.class.getDeclaredMethod("getWarnings", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableConnection$2java$1sql$1Connection$$$Proxy(Connection paramConnection, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramConnection;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableConnection$2java$1sql$1Connection$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */