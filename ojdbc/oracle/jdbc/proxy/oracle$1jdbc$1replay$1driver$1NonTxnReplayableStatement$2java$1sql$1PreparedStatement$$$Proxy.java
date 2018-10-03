package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Wrapper;
import java.util.Calendar;
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableStatement;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2java$1sql$1PreparedStatement$$$Proxy
  extends NonTxnReplayableStatement
  implements PreparedStatement, _Proxy_
{
  private PreparedStatement delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject24209;
  private static Method methodObject24199;
  private static Method methodObject24256;
  private static Method methodObject24225;
  private static Method methodObject24231;
  private static Method methodObject24283;
  private static Method methodObject24228;
  private static Method methodObject24213;
  private static Method methodObject24268;
  private static Method methodObject24245;
  private static Method methodObject24240;
  private static Method methodObject24280;
  private static Method methodObject24274;
  private static Method methodObject24195;
  private static Method methodObject24284;
  private static Method methodObject24235;
  private static Method methodObject24281;
  private static Method methodObject24279;
  private static Method methodObject24200;
  private static Method methodObject24192;
  private static Method methodObject24251;
  private static Method methodObject24206;
  private static Method methodObject24208;
  private static Method methodObject24273;
  private static Method methodObject24227;
  private static Method methodObject24204;
  private static Method methodObject24255;
  private static Method methodObject24258;
  private static Method methodObject24229;
  private static Method methodObject24219;
  private static Method methodObject24210;
  private static Method methodObject24278;
  private static Method methodObject24275;
  private static Method methodObject24233;
  private static Method methodObject24261;
  private static Method methodObject24276;
  private static Method methodObject24223;
  private static Method methodObject24201;
  private static Method methodObject24226;
  private static Method methodObject24287;
  private static Method methodObject24262;
  private static Method methodObject24232;
  private static Method methodObject24207;
  private static Method methodObject24215;
  private static Method methodObject24288;
  private static Method methodObject24242;
  private static Method methodObject24205;
  private static Method methodObject24216;
  private static Method methodObject24266;
  private static Method methodObject24211;
  private static Method methodObject24267;
  private static Method methodObject24265;
  private static Method methodObject24234;
  private static Method methodObject24248;
  private static Method methodObject24259;
  private static Method methodObject24260;
  private static Method methodObject24271;
  private static Method methodObject24197;
  private static Method methodObject24249;
  private static Method methodObject24247;
  private static Method methodObject24238;
  private static Method methodObject24263;
  private static Method methodObject24230;
  private static Method methodObject24196;
  private static Method methodObject24214;
  private static Method methodObject24198;
  private static Method methodObject24244;
  private static Method methodObject24282;
  private static Method methodObject24224;
  private static Method methodObject24270;
  private static Method methodObject24220;
  private static Method methodObject24203;
  private static Method methodObject24193;
  private static Method methodObject24221;
  private static Method methodObject24239;
  private static Method methodObject24264;
  private static Method methodObject24236;
  private static Method methodObject24286;
  private static Method methodObject24243;
  private static Method methodObject24257;
  private static Method methodObject24277;
  private static Method methodObject24202;
  private static Method methodObject24217;
  private static Method methodObject24285;
  private static Method methodObject24241;
  private static Method methodObject24246;
  private static Method methodObject24222;
  private static Method methodObject24218;
  private static Method methodObject24254;
  private static Method methodObject24269;
  private static Method methodObject24194;
  private static Method methodObject24250;
  private static Method methodObject24237;
  private static Method methodObject24272;
  private static Method methodObject24252;
  private static Method methodObject24212;
  private static Method methodObject24253;
  
  public void setBinaryStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24209, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBinaryStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24209, e);
    }
  }
  
  public void setTimestamp(int arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24199, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTimestamp(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24199, e);
    }
  }
  
  public int executeUpdate(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24256, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject24256, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24256, ((Integer)onErrorForAll(methodObject24256, e)).intValue());
    }
  }
  
  public void setObject(int arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24225, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24225, e);
    }
  }
  
  public void setBlob(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24231, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24231, e);
    }
  }
  
  public void setMaxFieldSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24283, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxFieldSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24283, e);
    }
  }
  
  public void setBigDecimal(int arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24228, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBigDecimal(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24228, e);
    }
  }
  
  public void setNCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24213, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setNCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24213, e);
    }
  }
  
  public void setFetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24268, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24268, e);
    }
  }
  
  public void setRef(int arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24245, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRef(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24245, e);
    }
  }
  
  public void setNull(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24240, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.setNull(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24240, e);
    }
  }
  
  public boolean isPoolable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24280, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24280, Boolean.valueOf(this.delegate.isPoolable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24280, onErrorForAll(methodObject24280, e))).booleanValue();
    }
  }
  
  public boolean getMoreResults()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24274, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24274, Boolean.valueOf(this.delegate.getMoreResults()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24274, onErrorForAll(methodObject24274, e))).booleanValue();
    }
  }
  
  public void setFloat(int arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24195, this, new Object[] { Integer.valueOf(arg0), Float.valueOf(arg1) });
      this.delegate.setFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24195, e);
    }
  }
  
  public void setMaxRows(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24284, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxRows(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24284, e);
    }
  }
  
  public void setNClob(int arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24235, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24235, e);
    }
  }
  
  public void setCursorName(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24281, this, new Object[] { arg0 });
      this.delegate.setCursorName(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24281, e);
    }
  }
  
  public int getUpdateCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24279, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24279, Integer.valueOf(this.delegate.getUpdateCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24279, onErrorForAll(methodObject24279, e))).intValue();
    }
  }
  
  public void setTimestamp(int arg0, Timestamp arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24200, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setTimestamp(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24200, e);
    }
  }
  
  public void setBoolean(int arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24192, this, new Object[] { Integer.valueOf(arg0), Boolean.valueOf(arg1) });
      this.delegate.setBoolean(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24192, e);
    }
  }
  
  public boolean execute(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24251, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject24251, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24251, onErrorForExecute(methodObject24251, e));
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24206, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setAsciiStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24206, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24208, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24208, e);
    }
  }
  
  public int getMaxRows()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24273, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24273, Integer.valueOf(this.delegate.getMaxRows()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24273, onErrorForAll(methodObject24273, e))).intValue();
    }
  }
  
  public void addBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24227, this, zeroLengthObjectArray);
      this.delegate.addBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24227, e);
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24204, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24204, e);
    }
  }
  
  public int executeUpdate(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24255, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecuteUpdate(methodObject24255, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24255, ((Integer)onErrorForAll(methodObject24255, e)).intValue());
    }
  }
  
  public int[] executeBatch()
    throws SQLException
  {
    try
    {
      super.preForExecuteBatch(methodObject24258, this, zeroLengthObjectArray);
      return (int[])postForAll(methodObject24258, (Object)this.delegate.executeBatch());
    }
    catch (SQLException e)
    {
      return (int[])postForAll(methodObject24258, onErrorForAll(methodObject24258, e));
    }
  }
  
  public void setBlob(int arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24229, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24229, e);
    }
  }
  
  public void setString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24219, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24219, e);
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24210, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24210, e);
    }
  }
  
  public int getResultSetType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24278, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24278, Integer.valueOf(this.delegate.getResultSetType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24278, onErrorForAll(methodObject24278, e))).intValue();
    }
  }
  
  public boolean getMoreResults(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24275, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject24275, Boolean.valueOf(this.delegate.getMoreResults(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24275, onErrorForAll(methodObject24275, e))).booleanValue();
    }
  }
  
  public void setClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24233, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24233, e);
    }
  }
  
  public int getResultSetHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24261, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24261, Integer.valueOf(this.delegate.getResultSetHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24261, onErrorForAll(methodObject24261, e))).intValue();
    }
  }
  
  public int getQueryTimeout()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24276, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24276, Integer.valueOf(this.delegate.getQueryTimeout()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24276, onErrorForAll(methodObject24276, e))).intValue();
    }
  }
  
  public void setDate(int arg0, Date arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24223, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setDate(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24223, e);
    }
  }
  
  public void setURL(int arg0, URL arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24201, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setURL(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24201, e);
    }
  }
  
  public void setObject(int arg0, Object arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24226, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24226, e);
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24287, this, new Object[] { arg0 });
      return postForAll(methodObject24287, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24287, onErrorForAll(methodObject24287, e));
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24262, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24262, e);
    }
  }
  
  public void setClob(int arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24232, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24232, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24207, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24207, e);
    }
  }
  
  public ResultSet executeQuery()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24215, this, zeroLengthObjectArray);
      return postForExecuteQuery(methodObject24215, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(), this, this.proxyCache, methodObject24215));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject24215, (ResultSet)onErrorForAll(methodObject24215, e));
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24288, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject24288, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24288, onErrorForAll(methodObject24288, e))).booleanValue();
    }
  }
  
  public void setSQLXML(int arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24242, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24242, e);
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24205, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24205, e);
    }
  }
  
  public ResultSetMetaData getMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24216, this, zeroLengthObjectArray);
      return (ResultSetMetaData)postForAll(methodObject24216, this.proxyFactory.proxyForCreate((Object)this.delegate.getMetaData(), this, this.proxyCache, methodObject24216));
    }
    catch (SQLException e)
    {
      return (ResultSetMetaData)postForAll(methodObject24216, onErrorForAll(methodObject24216, e));
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24266, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24266, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24266, onErrorForAll(methodObject24266, e))).booleanValue();
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24211, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24211, e);
    }
  }
  
  public void setFetchDirection(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24267, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchDirection(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24267, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24265, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject24265, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject24265, onErrorForAll(methodObject24265, e));
    }
  }
  
  public void setClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24234, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24234, e);
    }
  }
  
  public boolean execute(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24248, this, new Object[] { arg0 });
      return postForExecute(methodObject24248, this.delegate.execute(arg0));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24248, onErrorForExecute(methodObject24248, e));
    }
  }
  
  public ResultSet executeQuery(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24259, this, new Object[] { arg0 });
      return postForExecuteQuery(methodObject24259, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(arg0), this, this.proxyCache, methodObject24259));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject24259, (ResultSet)onErrorForAll(methodObject24259, e));
    }
  }
  
  public Connection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24260, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject24260, (Object)super.getConnection());
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject24260, onErrorForAll(methodObject24260, e));
    }
  }
  
  public ResultSet getGeneratedKeys()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24271, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject24271, this.proxyFactory.proxyForCache((Object)this.delegate.getGeneratedKeys(), this, this.proxyCache, methodObject24271));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject24271, onErrorForAll(methodObject24271, e));
    }
  }
  
  public void setLong(int arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24197, this, new Object[] { Integer.valueOf(arg0), Long.valueOf(arg1) });
      this.delegate.setLong(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24197, e);
    }
  }
  
  public boolean execute(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24249, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecute(methodObject24249, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24249, onErrorForExecute(methodObject24249, e));
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24247, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClose(methodObject24247);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForClose(methodObject24247, e);
    }
  }
  
  public void setNString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24238, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24238, e);
    }
  }
  
  public int getFetchDirection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24263, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24263, Integer.valueOf(this.delegate.getFetchDirection()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24263, onErrorForAll(methodObject24263, e))).intValue();
    }
  }
  
  public void setBlob(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24230, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setBlob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24230, e);
    }
  }
  
  public void setInt(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24196, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.setInt(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24196, e);
    }
  }
  
  public void setNCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24214, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24214, e);
    }
  }
  
  public void setShort(int arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24198, this, new Object[] { Integer.valueOf(arg0), Short.valueOf(arg1) });
      this.delegate.setShort(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24198, e);
    }
  }
  
  public void setArray(int arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24244, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setArray(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24244, e);
    }
  }
  
  public void setEscapeProcessing(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24282, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setEscapeProcessing(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24282, e);
    }
  }
  
  public void setObject(int arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24224, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24224, e);
    }
  }
  
  public void clearBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24270, this, zeroLengthObjectArray);
      this.delegate.clearBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24270, e);
    }
  }
  
  public void setTime(int arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24220, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTime(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24220, e);
    }
  }
  
  public int executeUpdate()
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24203, this, zeroLengthObjectArray);
      return postForExecuteUpdate(methodObject24203, this.delegate.executeUpdate());
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24203, ((Integer)onErrorForAll(methodObject24203, e)).intValue());
    }
  }
  
  public void setByte(int arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24193, this, new Object[] { Integer.valueOf(arg0), Byte.valueOf(arg1) });
      this.delegate.setByte(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24193, e);
    }
  }
  
  public void setTime(int arg0, Time arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24221, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setTime(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24221, e);
    }
  }
  
  public void setNull(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24239, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.setNull(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24239, e);
    }
  }
  
  public int getFetchSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24264, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24264, Integer.valueOf(this.delegate.getFetchSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24264, onErrorForAll(methodObject24264, e))).intValue();
    }
  }
  
  public void setNClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24236, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setNClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24236, e);
    }
  }
  
  public void setQueryTimeout(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24286, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setQueryTimeout(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24286, e);
    }
  }
  
  public void clearParameters()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24243, this, zeroLengthObjectArray);
      this.delegate.clearParameters();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24243, e);
    }
  }
  
  public int executeUpdate(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24257, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject24257, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24257, ((Integer)onErrorForAll(methodObject24257, e)).intValue());
    }
  }
  
  public int getResultSetConcurrency()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24277, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24277, Integer.valueOf(this.delegate.getResultSetConcurrency()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24277, onErrorForAll(methodObject24277, e))).intValue();
    }
  }
  
  public boolean execute()
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24202, this, zeroLengthObjectArray);
      return postForExecute(methodObject24202, this.delegate.execute());
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24202, onErrorForExecute(methodObject24202, e));
    }
  }
  
  public ParameterMetaData getParameterMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24217, this, zeroLengthObjectArray);
      return (ParameterMetaData)postForAll(methodObject24217, this.proxyFactory.proxyForCreate((Object)this.delegate.getParameterMetaData(), this, this.proxyCache, methodObject24217));
    }
    catch (SQLException e)
    {
      return (ParameterMetaData)postForAll(methodObject24217, onErrorForAll(methodObject24217, e));
    }
  }
  
  public void setPoolable(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24285, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setPoolable(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24285, e);
    }
  }
  
  public void setRowId(int arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24241, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24241, e);
    }
  }
  
  public void setUnicodeStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24246, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setUnicodeStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24246, e);
    }
  }
  
  public void setDate(int arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24222, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setDate(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24222, e);
    }
  }
  
  public void setBytes(int arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24218, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBytes(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24218, e);
    }
  }
  
  public int executeUpdate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24254, this, new Object[] { arg0 });
      return postForExecuteUpdate(methodObject24254, this.delegate.executeUpdate(arg0));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24254, ((Integer)onErrorForAll(methodObject24254, e)).intValue());
    }
  }
  
  public void addBatch(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24269, this, new Object[] { arg0 });
      this.delegate.addBatch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24269, e);
    }
  }
  
  public void setDouble(int arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24194, this, new Object[] { Integer.valueOf(arg0), Double.valueOf(arg1) });
      this.delegate.setDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24194, e);
    }
  }
  
  public boolean execute(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24250, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject24250, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24250, onErrorForExecute(methodObject24250, e));
    }
  }
  
  public void setNClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24237, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24237, e);
    }
  }
  
  public int getMaxFieldSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24272, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24272, Integer.valueOf(this.delegate.getMaxFieldSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24272, onErrorForAll(methodObject24272, e))).intValue();
    }
  }
  
  public void cancel()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24252, this, zeroLengthObjectArray);
      this.delegate.cancel();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24252, e);
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24212, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24212, e);
    }
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24253, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject24253, this.proxyFactory.proxyForCache((Object)this.delegate.getResultSet(), this, this.proxyCache, methodObject24253));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject24253, onErrorForAll(methodObject24253, e));
    }
  }
  
  public PreparedStatement _getDelegate_()
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
      methodObject24209 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject24199 = PreparedStatement.class.getDeclaredMethod("setTimestamp", new Class[] { Integer.TYPE, Timestamp.class });
      methodObject24256 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, int[].class });
      methodObject24225 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class });
      methodObject24231 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, InputStream.class });
      methodObject24283 = Statement.class.getDeclaredMethod("setMaxFieldSize", new Class[] { Integer.TYPE });
      methodObject24228 = PreparedStatement.class.getDeclaredMethod("setBigDecimal", new Class[] { Integer.TYPE, BigDecimal.class });
      methodObject24213 = PreparedStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24268 = Statement.class.getDeclaredMethod("setFetchSize", new Class[] { Integer.TYPE });
      methodObject24245 = PreparedStatement.class.getDeclaredMethod("setRef", new Class[] { Integer.TYPE, Ref.class });
      methodObject24240 = PreparedStatement.class.getDeclaredMethod("setNull", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject24280 = Statement.class.getDeclaredMethod("isPoolable", new Class[0]);
      methodObject24274 = Statement.class.getDeclaredMethod("getMoreResults", new Class[0]);
      methodObject24195 = PreparedStatement.class.getDeclaredMethod("setFloat", new Class[] { Integer.TYPE, Float.TYPE });
      methodObject24284 = Statement.class.getDeclaredMethod("setMaxRows", new Class[] { Integer.TYPE });
      methodObject24235 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, NClob.class });
      methodObject24281 = Statement.class.getDeclaredMethod("setCursorName", new Class[] { String.class });
      methodObject24279 = Statement.class.getDeclaredMethod("getUpdateCount", new Class[0]);
      methodObject24200 = PreparedStatement.class.getDeclaredMethod("setTimestamp", new Class[] { Integer.TYPE, Timestamp.class, Calendar.class });
      methodObject24192 = PreparedStatement.class.getDeclaredMethod("setBoolean", new Class[] { Integer.TYPE, Boolean.TYPE });
      methodObject24251 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, String[].class });
      methodObject24206 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject24208 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject24273 = Statement.class.getDeclaredMethod("getMaxRows", new Class[0]);
      methodObject24227 = PreparedStatement.class.getDeclaredMethod("addBatch", new Class[0]);
      methodObject24204 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject24255 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, Integer.TYPE });
      methodObject24258 = Statement.class.getDeclaredMethod("executeBatch", new Class[0]);
      methodObject24229 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, Blob.class });
      methodObject24219 = PreparedStatement.class.getDeclaredMethod("setString", new Class[] { Integer.TYPE, String.class });
      methodObject24210 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class, Integer.TYPE });
      methodObject24278 = Statement.class.getDeclaredMethod("getResultSetType", new Class[0]);
      methodObject24275 = Statement.class.getDeclaredMethod("getMoreResults", new Class[] { Integer.TYPE });
      methodObject24233 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24261 = Statement.class.getDeclaredMethod("getResultSetHoldability", new Class[0]);
      methodObject24276 = Statement.class.getDeclaredMethod("getQueryTimeout", new Class[0]);
      methodObject24223 = PreparedStatement.class.getDeclaredMethod("setDate", new Class[] { Integer.TYPE, Date.class, Calendar.class });
      methodObject24201 = PreparedStatement.class.getDeclaredMethod("setURL", new Class[] { Integer.TYPE, URL.class });
      methodObject24226 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE, Integer.TYPE });
      methodObject24287 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject24262 = Statement.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject24232 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Clob.class });
      methodObject24207 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject24215 = PreparedStatement.class.getDeclaredMethod("executeQuery", new Class[0]);
      methodObject24288 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject24242 = PreparedStatement.class.getDeclaredMethod("setSQLXML", new Class[] { Integer.TYPE, SQLXML.class });
      methodObject24205 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject24216 = PreparedStatement.class.getDeclaredMethod("getMetaData", new Class[0]);
      methodObject24266 = Statement.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject24211 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24267 = Statement.class.getDeclaredMethod("setFetchDirection", new Class[] { Integer.TYPE });
      methodObject24265 = Statement.class.getDeclaredMethod("getWarnings", new Class[0]);
      methodObject24234 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject24248 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class });
      methodObject24259 = Statement.class.getDeclaredMethod("executeQuery", new Class[] { String.class });
      methodObject24260 = Statement.class.getDeclaredMethod("getConnection", new Class[0]);
      methodObject24271 = Statement.class.getDeclaredMethod("getGeneratedKeys", new Class[0]);
      methodObject24197 = PreparedStatement.class.getDeclaredMethod("setLong", new Class[] { Integer.TYPE, Long.TYPE });
      methodObject24249 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, Integer.TYPE });
      methodObject24247 = Statement.class.getDeclaredMethod("close", new Class[0]);
      methodObject24238 = PreparedStatement.class.getDeclaredMethod("setNString", new Class[] { Integer.TYPE, String.class });
      methodObject24263 = Statement.class.getDeclaredMethod("getFetchDirection", new Class[0]);
      methodObject24230 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject24196 = PreparedStatement.class.getDeclaredMethod("setInt", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24214 = PreparedStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject24198 = PreparedStatement.class.getDeclaredMethod("setShort", new Class[] { Integer.TYPE, Short.TYPE });
      methodObject24244 = PreparedStatement.class.getDeclaredMethod("setArray", new Class[] { Integer.TYPE, Array.class });
      methodObject24282 = Statement.class.getDeclaredMethod("setEscapeProcessing", new Class[] { Boolean.TYPE });
      methodObject24224 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE });
      methodObject24270 = Statement.class.getDeclaredMethod("clearBatch", new Class[0]);
      methodObject24220 = PreparedStatement.class.getDeclaredMethod("setTime", new Class[] { Integer.TYPE, Time.class });
      methodObject24203 = PreparedStatement.class.getDeclaredMethod("executeUpdate", new Class[0]);
      methodObject24193 = PreparedStatement.class.getDeclaredMethod("setByte", new Class[] { Integer.TYPE, Byte.TYPE });
      methodObject24221 = PreparedStatement.class.getDeclaredMethod("setTime", new Class[] { Integer.TYPE, Time.class, Calendar.class });
      methodObject24239 = PreparedStatement.class.getDeclaredMethod("setNull", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24264 = Statement.class.getDeclaredMethod("getFetchSize", new Class[0]);
      methodObject24236 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24286 = Statement.class.getDeclaredMethod("setQueryTimeout", new Class[] { Integer.TYPE });
      methodObject24243 = PreparedStatement.class.getDeclaredMethod("clearParameters", new Class[0]);
      methodObject24257 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, String[].class });
      methodObject24277 = Statement.class.getDeclaredMethod("getResultSetConcurrency", new Class[0]);
      methodObject24202 = PreparedStatement.class.getDeclaredMethod("execute", new Class[0]);
      methodObject24217 = PreparedStatement.class.getDeclaredMethod("getParameterMetaData", new Class[0]);
      methodObject24285 = Statement.class.getDeclaredMethod("setPoolable", new Class[] { Boolean.TYPE });
      methodObject24241 = PreparedStatement.class.getDeclaredMethod("setRowId", new Class[] { Integer.TYPE, RowId.class });
      methodObject24246 = PreparedStatement.class.getDeclaredMethod("setUnicodeStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject24222 = PreparedStatement.class.getDeclaredMethod("setDate", new Class[] { Integer.TYPE, Date.class });
      methodObject24218 = PreparedStatement.class.getDeclaredMethod("setBytes", new Class[] { Integer.TYPE, byte[].class });
      methodObject24254 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class });
      methodObject24269 = Statement.class.getDeclaredMethod("addBatch", new Class[] { String.class });
      methodObject24194 = PreparedStatement.class.getDeclaredMethod("setDouble", new Class[] { Integer.TYPE, Double.TYPE });
      methodObject24250 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, int[].class });
      methodObject24237 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject24272 = Statement.class.getDeclaredMethod("getMaxFieldSize", new Class[0]);
      methodObject24252 = Statement.class.getDeclaredMethod("cancel", new Class[0]);
      methodObject24212 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject24253 = Statement.class.getDeclaredMethod("getResultSet", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2java$1sql$1PreparedStatement$$$Proxy(PreparedStatement paramPreparedStatement, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramPreparedStatement;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2java$1sql$1PreparedStatement$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */