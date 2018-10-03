package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
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
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2java$1sql$1CallableStatement$$$Proxy
  extends NonTxnReplayableStatement
  implements CallableStatement, _Proxy_
{
  private CallableStatement delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject24112;
  private static Method methodObject24046;
  private static Method methodObject24159;
  private static Method methodObject24018;
  private static Method methodObject24072;
  private static Method methodObject24067;
  private static Method methodObject23997;
  private static Method methodObject24134;
  private static Method methodObject24047;
  private static Method methodObject23992;
  private static Method methodObject24171;
  private static Method methodObject23985;
  private static Method methodObject24036;
  private static Method methodObject24183;
  private static Method methodObject23991;
  private static Method methodObject24187;
  private static Method methodObject24093;
  private static Method methodObject24048;
  private static Method methodObject24184;
  private static Method methodObject24182;
  private static Method methodObject24089;
  private static Method methodObject24103;
  private static Method methodObject24095;
  private static Method methodObject24154;
  private static Method methodObject24109;
  private static Method methodObject24111;
  private static Method methodObject24158;
  private static Method methodObject24161;
  private static Method methodObject24049;
  private static Method methodObject24181;
  private static Method methodObject24061;
  private static Method methodObject24164;
  private static Method methodObject24104;
  private static Method methodObject24126;
  private static Method methodObject24190;
  private static Method methodObject24129;
  private static Method methodObject24165;
  private static Method methodObject24135;
  private static Method methodObject24001;
  private static Method methodObject24092;
  private static Method methodObject24119;
  private static Method methodObject24068;
  private static Method methodObject24074;
  private static Method methodObject24169;
  private static Method methodObject23987;
  private static Method methodObject24174;
  private static Method methodObject24069;
  private static Method methodObject23988;
  private static Method methodObject24015;
  private static Method methodObject24042;
  private static Method methodObject24166;
  private static Method methodObject24021;
  private static Method methodObject24084;
  private static Method methodObject24133;
  private static Method methodObject24085;
  private static Method methodObject24062;
  private static Method methodObject24037;
  private static Method methodObject24117;
  private static Method methodObject24055;
  private static Method methodObject24147;
  private static Method methodObject24009;
  private static Method methodObject24123;
  private static Method methodObject23998;
  private static Method methodObject24043;
  private static Method methodObject24019;
  private static Method methodObject24124;
  private static Method methodObject24002;
  private static Method methodObject24006;
  private static Method methodObject24040;
  private static Method methodObject24057;
  private static Method methodObject24008;
  private static Method methodObject24189;
  private static Method methodObject24070;
  private static Method methodObject24029;
  private static Method methodObject24071;
  private static Method methodObject24014;
  private static Method methodObject24180;
  private static Method methodObject24058;
  private static Method methodObject24032;
  private static Method methodObject24077;
  private static Method methodObject23989;
  private static Method methodObject24053;
  private static Method methodObject24121;
  private static Method methodObject24157;
  private static Method methodObject24066;
  private static Method methodObject24086;
  private static Method methodObject24172;
  private static Method methodObject24153;
  private static Method methodObject24045;
  private static Method methodObject24039;
  private static Method methodObject24003;
  private static Method methodObject24155;
  private static Method methodObject24050;
  private static Method methodObject24026;
  private static Method methodObject24115;
  private static Method methodObject24156;
  private static Method methodObject24028;
  private static Method methodObject24016;
  private static Method methodObject24102;
  private static Method methodObject24128;
  private static Method methodObject24030;
  private static Method methodObject24081;
  private static Method methodObject24011;
  private static Method methodObject24186;
  private static Method methodObject24131;
  private static Method methodObject24083;
  private static Method methodObject24116;
  private static Method methodObject24148;
  private static Method methodObject24143;
  private static Method methodObject24060;
  private static Method methodObject24177;
  private static Method methodObject24020;
  private static Method methodObject24000;
  private static Method methodObject24098;
  private static Method methodObject24033;
  private static Method methodObject24138;
  private static Method methodObject23996;
  private static Method methodObject24013;
  private static Method methodObject24176;
  private static Method methodObject24107;
  private static Method methodObject24130;
  private static Method methodObject24054;
  private static Method methodObject24113;
  private static Method methodObject24122;
  private static Method methodObject24132;
  private static Method methodObject24178;
  private static Method methodObject24179;
  private static Method methodObject24136;
  private static Method methodObject24088;
  private static Method methodObject24051;
  private static Method methodObject24091;
  private static Method methodObject24038;
  private static Method methodObject24110;
  private static Method methodObject24118;
  private static Method methodObject24191;
  private static Method methodObject24145;
  private static Method methodObject24031;
  private static Method methodObject24108;
  private static Method methodObject24075;
  private static Method methodObject24114;
  private static Method methodObject24170;
  private static Method methodObject24168;
  private static Method methodObject24137;
  private static Method methodObject24151;
  private static Method methodObject24162;
  private static Method methodObject24163;
  private static Method methodObject24100;
  private static Method methodObject24080;
  private static Method methodObject24017;
  private static Method methodObject24152;
  private static Method methodObject24150;
  private static Method methodObject24035;
  private static Method methodObject23999;
  private static Method methodObject24010;
  private static Method methodObject24141;
  private static Method methodObject24056;
  private static Method methodObject24023;
  private static Method methodObject24052;
  private static Method methodObject23984;
  private static Method methodObject24099;
  private static Method methodObject24101;
  private static Method methodObject24185;
  private static Method methodObject24073;
  private static Method methodObject24127;
  private static Method methodObject24034;
  private static Method methodObject24173;
  private static Method methodObject24007;
  private static Method methodObject24106;
  private static Method methodObject24096;
  private static Method methodObject24079;
  private static Method methodObject24064;
  private static Method methodObject24142;
  private static Method methodObject24167;
  private static Method methodObject24076;
  private static Method methodObject24139;
  private static Method methodObject24012;
  private static Method methodObject23986;
  private static Method methodObject23995;
  private static Method methodObject24025;
  private static Method methodObject24146;
  private static Method methodObject24063;
  private static Method methodObject24160;
  private static Method methodObject24005;
  private static Method methodObject24027;
  private static Method methodObject24082;
  private static Method methodObject24044;
  private static Method methodObject24041;
  private static Method methodObject24105;
  private static Method methodObject23994;
  private static Method methodObject24120;
  private static Method methodObject24188;
  private static Method methodObject24144;
  private static Method methodObject24149;
  private static Method methodObject24125;
  private static Method methodObject24087;
  private static Method methodObject24094;
  private static Method methodObject23990;
  private static Method methodObject24097;
  private static Method methodObject24140;
  private static Method methodObject24175;
  private static Method methodObject24004;
  private static Method methodObject24090;
  private static Method methodObject24022;
  private static Method methodObject24059;
  private static Method methodObject24024;
  private static Method methodObject24065;
  private static Method methodObject24078;
  private static Method methodObject23993;
  
  public void setBinaryStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24112, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBinaryStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24112, e);
    }
  }
  
  public SQLXML getSQLXML(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24046, this, new Object[] { arg0 });
      return (SQLXML)postForAll(methodObject24046, this.proxyFactory.proxyForCreate((Object)this.delegate.getSQLXML(arg0), this, this.proxyCache, methodObject24046));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject24046, onErrorForAll(methodObject24046, e));
    }
  }
  
  public int executeUpdate(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24159, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject24159, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24159, ((Integer)onErrorForAll(methodObject24159, e)).intValue());
    }
  }
  
  public Ref getRef(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24018, this, new Object[] { arg0 });
      return (Ref)postForAll(methodObject24018, this.proxyFactory.proxyForCreate((Object)this.delegate.getRef(arg0), this, this.proxyCache, methodObject24018));
    }
    catch (SQLException e)
    {
      return (Ref)postForAll(methodObject24018, onErrorForAll(methodObject24018, e));
    }
  }
  
  public void setObject(String arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24072, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24072, e);
    }
  }
  
  public void setTime(String arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24067, this, new Object[] { arg0, arg1 });
      this.delegate.setTime(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24067, e);
    }
  }
  
  public long getLong(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23997, this, new Object[] { arg0 });
      return ((Long)postForAll(methodObject23997, Long.valueOf(this.delegate.getLong(arg0)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23997, onErrorForAll(methodObject23997, e))).longValue();
    }
  }
  
  public void setBlob(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24134, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24134, e);
    }
  }
  
  public void setBytes(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24047, this, new Object[] { arg0, arg1 });
      this.delegate.setBytes(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24047, e);
    }
  }
  
  public short getShort(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23992, this, new Object[] { Integer.valueOf(arg0) });
      return ((Short)postForAll(methodObject23992, Short.valueOf(this.delegate.getShort(arg0)))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject23992, onErrorForAll(methodObject23992, e))).shortValue();
    }
  }
  
  public void setFetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24171, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24171, e);
    }
  }
  
  public Object getObject(int arg0, Map arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23985, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject23985, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject23985));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject23985, onErrorForAll(methodObject23985, e));
    }
  }
  
  public void setNCharacterStream(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24036, this, new Object[] { arg0, arg1 });
      this.delegate.setNCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24036, e);
    }
  }
  
  public boolean isPoolable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24183, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24183, Boolean.valueOf(this.delegate.isPoolable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24183, onErrorForAll(methodObject24183, e))).booleanValue();
    }
  }
  
  public byte getByte(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23991, this, new Object[] { arg0 });
      return ((Byte)postForAll(methodObject23991, Byte.valueOf(this.delegate.getByte(arg0)))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject23991, onErrorForAll(methodObject23991, e))).byteValue();
    }
  }
  
  public void setMaxRows(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24187, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxRows(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24187, e);
    }
  }
  
  public void setRowId(String arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24093, this, new Object[] { arg0, arg1 });
      this.delegate.setRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24093, e);
    }
  }
  
  public void setString(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24048, this, new Object[] { arg0, arg1 });
      this.delegate.setString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24048, e);
    }
  }
  
  public void setCursorName(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24184, this, new Object[] { arg0 });
      this.delegate.setCursorName(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24184, e);
    }
  }
  
  public int getUpdateCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24182, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24182, Integer.valueOf(this.delegate.getUpdateCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24182, onErrorForAll(methodObject24182, e))).intValue();
    }
  }
  
  public void setNClob(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24089, this, new Object[] { arg0, arg1 });
      this.delegate.setNClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24089, e);
    }
  }
  
  public void setTimestamp(int arg0, Timestamp arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24103, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setTimestamp(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24103, e);
    }
  }
  
  public void setBoolean(int arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24095, this, new Object[] { Integer.valueOf(arg0), Boolean.valueOf(arg1) });
      this.delegate.setBoolean(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24095, e);
    }
  }
  
  public boolean execute(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24154, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject24154, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24154, onErrorForExecute(methodObject24154, e));
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24109, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setAsciiStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24109, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24111, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24111, e);
    }
  }
  
  public int executeUpdate(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24158, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecuteUpdate(methodObject24158, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24158, ((Integer)onErrorForAll(methodObject24158, e)).intValue());
    }
  }
  
  public int[] executeBatch()
    throws SQLException
  {
    try
    {
      super.preForExecuteBatch(methodObject24161, this, zeroLengthObjectArray);
      return (int[])postForAll(methodObject24161, (Object)this.delegate.executeBatch());
    }
    catch (SQLException e)
    {
      return (int[])postForAll(methodObject24161, onErrorForAll(methodObject24161, e));
    }
  }
  
  public boolean wasNull()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24049, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24049, Boolean.valueOf(this.delegate.wasNull()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24049, onErrorForAll(methodObject24049, e))).booleanValue();
    }
  }
  
  public int getResultSetType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24181, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24181, Integer.valueOf(this.delegate.getResultSetType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24181, onErrorForAll(methodObject24181, e))).intValue();
    }
  }
  
  public Time getTime(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24061, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject24061, (Object)this.delegate.getTime(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject24061, onErrorForAll(methodObject24061, e));
    }
  }
  
  public int getResultSetHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24164, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24164, Integer.valueOf(this.delegate.getResultSetHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24164, onErrorForAll(methodObject24164, e))).intValue();
    }
  }
  
  public void setURL(int arg0, URL arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24104, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setURL(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24104, e);
    }
  }
  
  public void setDate(int arg0, Date arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24126, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setDate(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24126, e);
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24190, this, new Object[] { arg0 });
      return postForAll(methodObject24190, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24190, onErrorForAll(methodObject24190, e));
    }
  }
  
  public void setObject(int arg0, Object arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24129, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24129, e);
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24165, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24165, e);
    }
  }
  
  public void setClob(int arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24135, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24135, e);
    }
  }
  
  public double getDouble(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24001, this, new Object[] { arg0 });
      return ((Double)postForAll(methodObject24001, Double.valueOf(this.delegate.getDouble(arg0)))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject24001, onErrorForAll(methodObject24001, e))).doubleValue();
    }
  }
  
  public void setNull(String arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24092, this, new Object[] { arg0, Integer.valueOf(arg1), arg2 });
      this.delegate.setNull(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24092, e);
    }
  }
  
  public ResultSetMetaData getMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24119, this, zeroLengthObjectArray);
      return (ResultSetMetaData)postForAll(methodObject24119, this.proxyFactory.proxyForCreate((Object)this.delegate.getMetaData(), this, this.proxyCache, methodObject24119));
    }
    catch (SQLException e)
    {
      return (ResultSetMetaData)postForAll(methodObject24119, onErrorForAll(methodObject24119, e));
    }
  }
  
  public void setTime(String arg0, Time arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24068, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setTime(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24068, e);
    }
  }
  
  public void registerOutParameter(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24074, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.registerOutParameter(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24074, e);
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24169, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24169, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24169, onErrorForAll(methodObject24169, e))).booleanValue();
    }
  }
  
  public Object getObject(String arg0, Map arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23987, this, new Object[] { arg0, arg1 });
      return postForAll(methodObject23987, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject23987));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject23987, onErrorForAll(methodObject23987, e));
    }
  }
  
  public ResultSet getGeneratedKeys()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24174, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject24174, this.proxyFactory.proxyForCache((Object)this.delegate.getGeneratedKeys(), this, this.proxyCache, methodObject24174));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject24174, onErrorForAll(methodObject24174, e));
    }
  }
  
  public void setDate(String arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24069, this, new Object[] { arg0, arg1 });
      this.delegate.setDate(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24069, e);
    }
  }
  
  public boolean getBoolean(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23988, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject23988, Boolean.valueOf(this.delegate.getBoolean(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23988, onErrorForAll(methodObject23988, e))).booleanValue();
    }
  }
  
  public void setTimestamp(String arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24015, this, new Object[] { arg0, arg1 });
      this.delegate.setTimestamp(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24015, e);
    }
  }
  
  public NClob getNClob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24042, this, new Object[] { arg0 });
      return (NClob)postForAll(methodObject24042, this.proxyFactory.proxyForCreate((Object)this.delegate.getNClob(arg0), this, this.proxyCache, methodObject24042));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject24042, onErrorForAll(methodObject24042, e));
    }
  }
  
  public int getFetchDirection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24166, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24166, Integer.valueOf(this.delegate.getFetchDirection()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24166, onErrorForAll(methodObject24166, e))).intValue();
    }
  }
  
  public void setURL(String arg0, URL arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24021, this, new Object[] { arg0, arg1 });
      this.delegate.setURL(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24021, e);
    }
  }
  
  public void setClob(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24084, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24084, e);
    }
  }
  
  public void setBlob(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24133, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setBlob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24133, e);
    }
  }
  
  public void setClob(String arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24085, this, new Object[] { arg0, arg1 });
      this.delegate.setClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24085, e);
    }
  }
  
  public Time getTime(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24062, this, new Object[] { arg0, arg1 });
      return (Time)postForAll(methodObject24062, (Object)this.delegate.getTime(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject24062, onErrorForAll(methodObject24062, e));
    }
  }
  
  public Blob getBlob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24037, this, new Object[] { Integer.valueOf(arg0) });
      return (Blob)postForAll(methodObject24037, this.proxyFactory.proxyForCreate((Object)this.delegate.getBlob(arg0), this, this.proxyCache, methodObject24037));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject24037, onErrorForAll(methodObject24037, e));
    }
  }
  
  public void setNCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24117, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24117, e);
    }
  }
  
  public Reader getNCharacterStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24055, this, new Object[] { Integer.valueOf(arg0) });
      return (Reader)postForAll(methodObject24055, (Object)this.delegate.getNCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject24055, onErrorForAll(methodObject24055, e));
    }
  }
  
  public void setArray(int arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24147, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setArray(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24147, e);
    }
  }
  
  public void setByte(String arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24009, this, new Object[] { arg0, Byte.valueOf(arg1) });
      this.delegate.setByte(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24009, e);
    }
  }
  
  public void setTime(int arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24123, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTime(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24123, e);
    }
  }
  
  public float getFloat(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23998, this, new Object[] { Integer.valueOf(arg0) });
      return ((Float)postForAll(methodObject23998, Float.valueOf(this.delegate.getFloat(arg0)))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject23998, onErrorForAll(methodObject23998, e))).floatValue();
    }
  }
  
  public RowId getRowId(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24043, this, new Object[] { Integer.valueOf(arg0) });
      return (RowId)postForAll(methodObject24043, this.proxyFactory.proxyForCreate((Object)this.delegate.getRowId(arg0), this, this.proxyCache, methodObject24043));
    }
    catch (SQLException e)
    {
      return (RowId)postForAll(methodObject24043, onErrorForAll(methodObject24043, e));
    }
  }
  
  public String getString(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24019, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject24019, (Object)this.delegate.getString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject24019, onErrorForAll(methodObject24019, e));
    }
  }
  
  public void setTime(int arg0, Time arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24124, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setTime(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24124, e);
    }
  }
  
  public byte[] getBytes(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24002, this, new Object[] { Integer.valueOf(arg0) });
      return (byte[])postForAll(methodObject24002, (Object)this.delegate.getBytes(arg0));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject24002, onErrorForAll(methodObject24002, e));
    }
  }
  
  public URL getURL(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24006, this, new Object[] { Integer.valueOf(arg0) });
      return (URL)postForAll(methodObject24006, (Object)this.delegate.getURL(arg0));
    }
    catch (SQLException e)
    {
      return (URL)postForAll(methodObject24006, onErrorForAll(methodObject24006, e));
    }
  }
  
  public Clob getClob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24040, this, new Object[] { arg0 });
      return (Clob)postForAll(methodObject24040, this.proxyFactory.proxyForCreate((Object)this.delegate.getClob(arg0), this, this.proxyCache, methodObject24040));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject24040, onErrorForAll(methodObject24040, e));
    }
  }
  
  public String getNString(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24057, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject24057, (Object)this.delegate.getNString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject24057, onErrorForAll(methodObject24057, e));
    }
  }
  
  public void setBoolean(String arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24008, this, new Object[] { arg0, Boolean.valueOf(arg1) });
      this.delegate.setBoolean(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24008, e);
    }
  }
  
  public void setQueryTimeout(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24189, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setQueryTimeout(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24189, e);
    }
  }
  
  public void setDate(String arg0, Date arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24070, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setDate(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24070, e);
    }
  }
  
  public void setBinaryStream(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24029, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24029, e);
    }
  }
  
  public void setObject(String arg0, Object arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24071, this, new Object[] { arg0, arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24071, e);
    }
  }
  
  public void setShort(String arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24014, this, new Object[] { arg0, Short.valueOf(arg1) });
      this.delegate.setShort(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24014, e);
    }
  }
  
  public int getResultSetConcurrency()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24180, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24180, Integer.valueOf(this.delegate.getResultSetConcurrency()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24180, onErrorForAll(methodObject24180, e))).intValue();
    }
  }
  
  public String getNString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24058, this, new Object[] { arg0 });
      return (String)postForAll(methodObject24058, (Object)this.delegate.getNString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject24058, onErrorForAll(methodObject24058, e));
    }
  }
  
  public void setCharacterStream(String arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24032, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24032, e);
    }
  }
  
  public void registerOutParameter(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24077, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.registerOutParameter(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24077, e);
    }
  }
  
  public boolean getBoolean(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23989, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject23989, Boolean.valueOf(this.delegate.getBoolean(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject23989, onErrorForAll(methodObject23989, e))).booleanValue();
    }
  }
  
  public BigDecimal getBigDecimal(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24053, this, new Object[] { Integer.valueOf(arg0) });
      return (BigDecimal)postForAll(methodObject24053, (Object)this.delegate.getBigDecimal(arg0));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject24053, onErrorForAll(methodObject24053, e));
    }
  }
  
  public void setBytes(int arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24121, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBytes(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24121, e);
    }
  }
  
  public int executeUpdate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24157, this, new Object[] { arg0 });
      return postForExecuteUpdate(methodObject24157, this.delegate.executeUpdate(arg0));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24157, ((Integer)onErrorForAll(methodObject24157, e)).intValue());
    }
  }
  
  public Timestamp getTimestamp(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24066, this, new Object[] { arg0, arg1 });
      return (Timestamp)postForAll(methodObject24066, (Object)this.delegate.getTimestamp(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject24066, onErrorForAll(methodObject24066, e));
    }
  }
  
  public void setClob(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24086, this, new Object[] { arg0, arg1 });
      this.delegate.setClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24086, e);
    }
  }
  
  public void addBatch(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24172, this, new Object[] { arg0 });
      this.delegate.addBatch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24172, e);
    }
  }
  
  public boolean execute(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24153, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject24153, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24153, onErrorForExecute(methodObject24153, e));
    }
  }
  
  public SQLXML getSQLXML(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24045, this, new Object[] { Integer.valueOf(arg0) });
      return (SQLXML)postForAll(methodObject24045, this.proxyFactory.proxyForCreate((Object)this.delegate.getSQLXML(arg0), this, this.proxyCache, methodObject24045));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject24045, onErrorForAll(methodObject24045, e));
    }
  }
  
  public Clob getClob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24039, this, new Object[] { Integer.valueOf(arg0) });
      return (Clob)postForAll(methodObject24039, this.proxyFactory.proxyForCreate((Object)this.delegate.getClob(arg0), this, this.proxyCache, methodObject24039));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject24039, onErrorForAll(methodObject24039, e));
    }
  }
  
  public byte[] getBytes(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24003, this, new Object[] { arg0 });
      return (byte[])postForAll(methodObject24003, (Object)this.delegate.getBytes(arg0));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject24003, onErrorForAll(methodObject24003, e));
    }
  }
  
  public void cancel()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24155, this, zeroLengthObjectArray);
      this.delegate.cancel();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24155, e);
    }
  }
  
  public Reader getCharacterStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24050, this, new Object[] { Integer.valueOf(arg0) });
      return (Reader)postForAll(methodObject24050, (Object)this.delegate.getCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject24050, onErrorForAll(methodObject24050, e));
    }
  }
  
  public void setAsciiStream(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24026, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24026, e);
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24115, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24115, e);
    }
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24156, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject24156, this.proxyFactory.proxyForCache((Object)this.delegate.getResultSet(), this, this.proxyCache, methodObject24156));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject24156, onErrorForAll(methodObject24156, e));
    }
  }
  
  public void setAsciiStream(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24028, this, new Object[] { arg0, arg1 });
      this.delegate.setAsciiStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24028, e);
    }
  }
  
  public void setTimestamp(String arg0, Timestamp arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24016, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setTimestamp(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24016, e);
    }
  }
  
  public void setTimestamp(int arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24102, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTimestamp(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24102, e);
    }
  }
  
  public void setObject(int arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24128, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24128, e);
    }
  }
  
  public void setBinaryStream(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24030, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24030, e);
    }
  }
  
  public void setBlob(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24081, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setBlob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24081, e);
    }
  }
  
  public void setFloat(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24011, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.setFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24011, e);
    }
  }
  
  public void setMaxFieldSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24186, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxFieldSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24186, e);
    }
  }
  
  public void setBigDecimal(int arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24131, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBigDecimal(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24131, e);
    }
  }
  
  public void setBlob(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24083, this, new Object[] { arg0, arg1 });
      this.delegate.setBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24083, e);
    }
  }
  
  public void setNCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24116, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setNCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24116, e);
    }
  }
  
  public void setRef(int arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24148, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRef(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24148, e);
    }
  }
  
  public void setNull(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24143, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.setNull(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24143, e);
    }
  }
  
  public Time getTime(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24060, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Time)postForAll(methodObject24060, (Object)this.delegate.getTime(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject24060, onErrorForAll(methodObject24060, e));
    }
  }
  
  public boolean getMoreResults()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24177, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24177, Boolean.valueOf(this.delegate.getMoreResults()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24177, onErrorForAll(methodObject24177, e))).booleanValue();
    }
  }
  
  public String getString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24020, this, new Object[] { arg0 });
      return (String)postForAll(methodObject24020, (Object)this.delegate.getString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject24020, onErrorForAll(methodObject24020, e));
    }
  }
  
  public double getDouble(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24000, this, new Object[] { Integer.valueOf(arg0) });
      return ((Double)postForAll(methodObject24000, Double.valueOf(this.delegate.getDouble(arg0)))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject24000, onErrorForAll(methodObject24000, e))).doubleValue();
    }
  }
  
  public void setFloat(int arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24098, this, new Object[] { Integer.valueOf(arg0), Float.valueOf(arg1) });
      this.delegate.setFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24098, e);
    }
  }
  
  public void setCharacterStream(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24033, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24033, e);
    }
  }
  
  public void setNClob(int arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24138, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24138, e);
    }
  }
  
  public long getLong(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23996, this, new Object[] { Integer.valueOf(arg0) });
      return ((Long)postForAll(methodObject23996, Long.valueOf(this.delegate.getLong(arg0)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject23996, onErrorForAll(methodObject23996, e))).longValue();
    }
  }
  
  public void setLong(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24013, this, new Object[] { arg0, Long.valueOf(arg1) });
      this.delegate.setLong(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24013, e);
    }
  }
  
  public int getMaxRows()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24176, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24176, Integer.valueOf(this.delegate.getMaxRows()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24176, onErrorForAll(methodObject24176, e))).intValue();
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24107, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24107, e);
    }
  }
  
  public void addBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24130, this, zeroLengthObjectArray);
      this.delegate.addBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24130, e);
    }
  }
  
  public BigDecimal getBigDecimal(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24054, this, new Object[] { arg0 });
      return (BigDecimal)postForAll(methodObject24054, (Object)this.delegate.getBigDecimal(arg0));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject24054, onErrorForAll(methodObject24054, e));
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24113, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24113, e);
    }
  }
  
  public void setString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24122, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24122, e);
    }
  }
  
  public void setBlob(int arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24132, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24132, e);
    }
  }
  
  public boolean getMoreResults(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24178, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject24178, Boolean.valueOf(this.delegate.getMoreResults(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24178, onErrorForAll(methodObject24178, e))).booleanValue();
    }
  }
  
  public int getQueryTimeout()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24179, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24179, Integer.valueOf(this.delegate.getQueryTimeout()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24179, onErrorForAll(methodObject24179, e))).intValue();
    }
  }
  
  public void setClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24136, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24136, e);
    }
  }
  
  public void setNClob(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24088, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setNClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24088, e);
    }
  }
  
  public Reader getCharacterStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24051, this, new Object[] { arg0 });
      return (Reader)postForAll(methodObject24051, (Object)this.delegate.getCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject24051, onErrorForAll(methodObject24051, e));
    }
  }
  
  public void setNull(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24091, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.setNull(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24091, e);
    }
  }
  
  public Blob getBlob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24038, this, new Object[] { arg0 });
      return (Blob)postForAll(methodObject24038, this.proxyFactory.proxyForCreate((Object)this.delegate.getBlob(arg0), this, this.proxyCache, methodObject24038));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject24038, onErrorForAll(methodObject24038, e));
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24110, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24110, e);
    }
  }
  
  public ResultSet executeQuery()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24118, this, zeroLengthObjectArray);
      return postForExecuteQuery(methodObject24118, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(), this, this.proxyCache, methodObject24118));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject24118, (ResultSet)onErrorForAll(methodObject24118, e));
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24191, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject24191, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24191, onErrorForAll(methodObject24191, e))).booleanValue();
    }
  }
  
  public void setSQLXML(int arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24145, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24145, e);
    }
  }
  
  public void setBinaryStream(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24031, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24031, e);
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24108, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24108, e);
    }
  }
  
  public void registerOutParameter(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24075, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.registerOutParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24075, e);
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24114, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24114, e);
    }
  }
  
  public void setFetchDirection(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24170, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchDirection(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24170, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24168, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject24168, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject24168, onErrorForAll(methodObject24168, e));
    }
  }
  
  public void setClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24137, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24137, e);
    }
  }
  
  public boolean execute(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24151, this, new Object[] { arg0 });
      return postForExecute(methodObject24151, this.delegate.execute(arg0));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24151, onErrorForExecute(methodObject24151, e));
    }
  }
  
  public ResultSet executeQuery(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24162, this, new Object[] { arg0 });
      return postForExecuteQuery(methodObject24162, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(arg0), this, this.proxyCache, methodObject24162));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject24162, (ResultSet)onErrorForAll(methodObject24162, e));
    }
  }
  
  public Connection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24163, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject24163, (Object)super.getConnection());
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject24163, onErrorForAll(methodObject24163, e));
    }
  }
  
  public void setLong(int arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24100, this, new Object[] { Integer.valueOf(arg0), Long.valueOf(arg1) });
      this.delegate.setLong(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24100, e);
    }
  }
  
  public void setBigDecimal(String arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24080, this, new Object[] { arg0, arg1 });
      this.delegate.setBigDecimal(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24080, e);
    }
  }
  
  public Ref getRef(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24017, this, new Object[] { Integer.valueOf(arg0) });
      return (Ref)postForAll(methodObject24017, this.proxyFactory.proxyForCreate((Object)this.delegate.getRef(arg0), this, this.proxyCache, methodObject24017));
    }
    catch (SQLException e)
    {
      return (Ref)postForAll(methodObject24017, onErrorForAll(methodObject24017, e));
    }
  }
  
  public boolean execute(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24152, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecute(methodObject24152, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24152, onErrorForExecute(methodObject24152, e));
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24150, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClose(methodObject24150);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForClose(methodObject24150, e);
    }
  }
  
  public void setNCharacterStream(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24035, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setNCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24035, e);
    }
  }
  
  public float getFloat(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23999, this, new Object[] { arg0 });
      return ((Float)postForAll(methodObject23999, Float.valueOf(this.delegate.getFloat(arg0)))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject23999, onErrorForAll(methodObject23999, e))).floatValue();
    }
  }
  
  public void setDouble(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24010, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.setDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24010, e);
    }
  }
  
  public void setNString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24141, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24141, e);
    }
  }
  
  public Reader getNCharacterStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24056, this, new Object[] { arg0 });
      return (Reader)postForAll(methodObject24056, (Object)this.delegate.getNCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject24056, onErrorForAll(methodObject24056, e));
    }
  }
  
  public Date getDate(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24023, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Date)postForAll(methodObject24023, (Object)this.delegate.getDate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject24023, onErrorForAll(methodObject24023, e));
    }
  }
  
  public BigDecimal getBigDecimal(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24052, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      return (BigDecimal)postForAll(methodObject24052, (Object)this.delegate.getBigDecimal(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject24052, onErrorForAll(methodObject24052, e));
    }
  }
  
  public Object getObject(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23984, this, new Object[] { Integer.valueOf(arg0) });
      return postForAll(methodObject23984, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0), this, this.proxyCache, methodObject23984));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject23984, onErrorForAll(methodObject23984, e));
    }
  }
  
  public void setInt(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24099, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.setInt(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24099, e);
    }
  }
  
  public void setShort(int arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24101, this, new Object[] { Integer.valueOf(arg0), Short.valueOf(arg1) });
      this.delegate.setShort(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24101, e);
    }
  }
  
  public void setEscapeProcessing(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24185, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setEscapeProcessing(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24185, e);
    }
  }
  
  public void setObject(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24073, this, new Object[] { arg0, arg1 });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24073, e);
    }
  }
  
  public void setObject(int arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24127, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24127, e);
    }
  }
  
  public void setCharacterStream(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24034, this, new Object[] { arg0, arg1 });
      this.delegate.setCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24034, e);
    }
  }
  
  public void clearBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24173, this, zeroLengthObjectArray);
      this.delegate.clearBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24173, e);
    }
  }
  
  public URL getURL(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24007, this, new Object[] { arg0 });
      return (URL)postForAll(methodObject24007, (Object)this.delegate.getURL(arg0));
    }
    catch (SQLException e)
    {
      return (URL)postForAll(methodObject24007, onErrorForAll(methodObject24007, e));
    }
  }
  
  public int executeUpdate()
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24106, this, zeroLengthObjectArray);
      return postForExecuteUpdate(methodObject24106, this.delegate.executeUpdate());
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24106, ((Integer)onErrorForAll(methodObject24106, e)).intValue());
    }
  }
  
  public void setByte(int arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24096, this, new Object[] { Integer.valueOf(arg0), Byte.valueOf(arg1) });
      this.delegate.setByte(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24096, e);
    }
  }
  
  public void registerOutParameter(String arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24079, this, new Object[] { arg0, Integer.valueOf(arg1), arg2 });
      this.delegate.registerOutParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24079, e);
    }
  }
  
  public Timestamp getTimestamp(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24064, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Timestamp)postForAll(methodObject24064, (Object)this.delegate.getTimestamp(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject24064, onErrorForAll(methodObject24064, e));
    }
  }
  
  public void setNull(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24142, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.setNull(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24142, e);
    }
  }
  
  public int getFetchSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24167, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24167, Integer.valueOf(this.delegate.getFetchSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24167, onErrorForAll(methodObject24167, e))).intValue();
    }
  }
  
  public void registerOutParameter(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24076, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.registerOutParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24076, e);
    }
  }
  
  public void setNClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24139, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setNClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24139, e);
    }
  }
  
  public void setInt(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24012, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.setInt(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24012, e);
    }
  }
  
  public Object getObject(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23986, this, new Object[] { arg0 });
      return postForAll(methodObject23986, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0), this, this.proxyCache, methodObject23986));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject23986, onErrorForAll(methodObject23986, e));
    }
  }
  
  public int getInt(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23995, this, new Object[] { arg0 });
      return ((Integer)postForAll(methodObject23995, Integer.valueOf(this.delegate.getInt(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23995, onErrorForAll(methodObject23995, e))).intValue();
    }
  }
  
  public Date getDate(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24025, this, new Object[] { arg0, arg1 });
      return (Date)postForAll(methodObject24025, (Object)this.delegate.getDate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject24025, onErrorForAll(methodObject24025, e));
    }
  }
  
  public void clearParameters()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24146, this, zeroLengthObjectArray);
      this.delegate.clearParameters();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24146, e);
    }
  }
  
  public Timestamp getTimestamp(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24063, this, new Object[] { Integer.valueOf(arg0) });
      return (Timestamp)postForAll(methodObject24063, (Object)this.delegate.getTimestamp(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject24063, onErrorForAll(methodObject24063, e));
    }
  }
  
  public int executeUpdate(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24160, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject24160, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24160, ((Integer)onErrorForAll(methodObject24160, e)).intValue());
    }
  }
  
  public Array getArray(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24005, this, new Object[] { arg0 });
      return (Array)postForAll(methodObject24005, this.proxyFactory.proxyForCreate((Object)this.delegate.getArray(arg0), this, this.proxyCache, methodObject24005));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject24005, onErrorForAll(methodObject24005, e));
    }
  }
  
  public void setAsciiStream(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24027, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24027, e);
    }
  }
  
  public void setBlob(String arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24082, this, new Object[] { arg0, arg1 });
      this.delegate.setBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24082, e);
    }
  }
  
  public RowId getRowId(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24044, this, new Object[] { arg0 });
      return (RowId)postForAll(methodObject24044, this.proxyFactory.proxyForCreate((Object)this.delegate.getRowId(arg0), this, this.proxyCache, methodObject24044));
    }
    catch (SQLException e)
    {
      return (RowId)postForAll(methodObject24044, onErrorForAll(methodObject24044, e));
    }
  }
  
  public NClob getNClob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24041, this, new Object[] { Integer.valueOf(arg0) });
      return (NClob)postForAll(methodObject24041, this.proxyFactory.proxyForCreate((Object)this.delegate.getNClob(arg0), this, this.proxyCache, methodObject24041));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject24041, onErrorForAll(methodObject24041, e));
    }
  }
  
  public boolean execute()
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24105, this, zeroLengthObjectArray);
      return postForExecute(methodObject24105, this.delegate.execute());
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24105, onErrorForExecute(methodObject24105, e));
    }
  }
  
  public int getInt(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23994, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject23994, Integer.valueOf(this.delegate.getInt(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject23994, onErrorForAll(methodObject23994, e))).intValue();
    }
  }
  
  public ParameterMetaData getParameterMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24120, this, zeroLengthObjectArray);
      return (ParameterMetaData)postForAll(methodObject24120, this.proxyFactory.proxyForCreate((Object)this.delegate.getParameterMetaData(), this, this.proxyCache, methodObject24120));
    }
    catch (SQLException e)
    {
      return (ParameterMetaData)postForAll(methodObject24120, onErrorForAll(methodObject24120, e));
    }
  }
  
  public void setPoolable(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24188, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setPoolable(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24188, e);
    }
  }
  
  public void setRowId(int arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24144, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24144, e);
    }
  }
  
  public void setUnicodeStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24149, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setUnicodeStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24149, e);
    }
  }
  
  public void setDate(int arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24125, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setDate(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24125, e);
    }
  }
  
  public void setNClob(String arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24087, this, new Object[] { arg0, arg1 });
      this.delegate.setNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24087, e);
    }
  }
  
  public void setSQLXML(String arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24094, this, new Object[] { arg0, arg1 });
      this.delegate.setSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24094, e);
    }
  }
  
  public byte getByte(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23990, this, new Object[] { Integer.valueOf(arg0) });
      return ((Byte)postForAll(methodObject23990, Byte.valueOf(this.delegate.getByte(arg0)))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject23990, onErrorForAll(methodObject23990, e))).byteValue();
    }
  }
  
  public void setDouble(int arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24097, this, new Object[] { Integer.valueOf(arg0), Double.valueOf(arg1) });
      this.delegate.setDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24097, e);
    }
  }
  
  public void setNClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24140, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24140, e);
    }
  }
  
  public int getMaxFieldSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24175, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24175, Integer.valueOf(this.delegate.getMaxFieldSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24175, onErrorForAll(methodObject24175, e))).intValue();
    }
  }
  
  public Array getArray(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24004, this, new Object[] { Integer.valueOf(arg0) });
      return (Array)postForAll(methodObject24004, this.proxyFactory.proxyForCreate((Object)this.delegate.getArray(arg0), this, this.proxyCache, methodObject24004));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject24004, onErrorForAll(methodObject24004, e));
    }
  }
  
  public void setNString(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24090, this, new Object[] { arg0, arg1 });
      this.delegate.setNString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24090, e);
    }
  }
  
  public Date getDate(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24022, this, new Object[] { Integer.valueOf(arg0) });
      return (Date)postForAll(methodObject24022, (Object)this.delegate.getDate(arg0));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject24022, onErrorForAll(methodObject24022, e));
    }
  }
  
  public Time getTime(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24059, this, new Object[] { Integer.valueOf(arg0) });
      return (Time)postForAll(methodObject24059, (Object)this.delegate.getTime(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject24059, onErrorForAll(methodObject24059, e));
    }
  }
  
  public Date getDate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24024, this, new Object[] { arg0 });
      return (Date)postForAll(methodObject24024, (Object)this.delegate.getDate(arg0));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject24024, onErrorForAll(methodObject24024, e));
    }
  }
  
  public Timestamp getTimestamp(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24065, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject24065, (Object)this.delegate.getTimestamp(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject24065, onErrorForAll(methodObject24065, e));
    }
  }
  
  public void registerOutParameter(String arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24078, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.registerOutParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24078, e);
    }
  }
  
  public short getShort(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject23993, this, new Object[] { arg0 });
      return ((Short)postForAll(methodObject23993, Short.valueOf(this.delegate.getShort(arg0)))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject23993, onErrorForAll(methodObject23993, e))).shortValue();
    }
  }
  
  public CallableStatement _getDelegate_()
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
      methodObject24112 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject24046 = CallableStatement.class.getDeclaredMethod("getSQLXML", new Class[] { String.class });
      methodObject24159 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, int[].class });
      methodObject24018 = CallableStatement.class.getDeclaredMethod("getRef", new Class[] { String.class });
      methodObject24072 = CallableStatement.class.getDeclaredMethod("setObject", new Class[] { String.class, Object.class, Integer.TYPE });
      methodObject24067 = CallableStatement.class.getDeclaredMethod("setTime", new Class[] { String.class, Time.class });
      methodObject23997 = CallableStatement.class.getDeclaredMethod("getLong", new Class[] { String.class });
      methodObject24134 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, InputStream.class });
      methodObject24047 = CallableStatement.class.getDeclaredMethod("setBytes", new Class[] { String.class, byte[].class });
      methodObject23992 = CallableStatement.class.getDeclaredMethod("getShort", new Class[] { Integer.TYPE });
      methodObject24171 = Statement.class.getDeclaredMethod("setFetchSize", new Class[] { Integer.TYPE });
      methodObject23985 = CallableStatement.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE, Map.class });
      methodObject24036 = CallableStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { String.class, Reader.class });
      methodObject24183 = Statement.class.getDeclaredMethod("isPoolable", new Class[0]);
      methodObject23991 = CallableStatement.class.getDeclaredMethod("getByte", new Class[] { String.class });
      methodObject24187 = Statement.class.getDeclaredMethod("setMaxRows", new Class[] { Integer.TYPE });
      methodObject24093 = CallableStatement.class.getDeclaredMethod("setRowId", new Class[] { String.class, RowId.class });
      methodObject24048 = CallableStatement.class.getDeclaredMethod("setString", new Class[] { String.class, String.class });
      methodObject24184 = Statement.class.getDeclaredMethod("setCursorName", new Class[] { String.class });
      methodObject24182 = Statement.class.getDeclaredMethod("getUpdateCount", new Class[0]);
      methodObject24089 = CallableStatement.class.getDeclaredMethod("setNClob", new Class[] { String.class, Reader.class });
      methodObject24103 = PreparedStatement.class.getDeclaredMethod("setTimestamp", new Class[] { Integer.TYPE, Timestamp.class, Calendar.class });
      methodObject24095 = PreparedStatement.class.getDeclaredMethod("setBoolean", new Class[] { Integer.TYPE, Boolean.TYPE });
      methodObject24154 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, String[].class });
      methodObject24109 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject24111 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject24158 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, Integer.TYPE });
      methodObject24161 = Statement.class.getDeclaredMethod("executeBatch", new Class[0]);
      methodObject24049 = CallableStatement.class.getDeclaredMethod("wasNull", new Class[0]);
      methodObject24181 = Statement.class.getDeclaredMethod("getResultSetType", new Class[0]);
      methodObject24061 = CallableStatement.class.getDeclaredMethod("getTime", new Class[] { String.class });
      methodObject24164 = Statement.class.getDeclaredMethod("getResultSetHoldability", new Class[0]);
      methodObject24104 = PreparedStatement.class.getDeclaredMethod("setURL", new Class[] { Integer.TYPE, URL.class });
      methodObject24126 = PreparedStatement.class.getDeclaredMethod("setDate", new Class[] { Integer.TYPE, Date.class, Calendar.class });
      methodObject24190 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject24129 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE, Integer.TYPE });
      methodObject24165 = Statement.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject24135 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Clob.class });
      methodObject24001 = CallableStatement.class.getDeclaredMethod("getDouble", new Class[] { String.class });
      methodObject24092 = CallableStatement.class.getDeclaredMethod("setNull", new Class[] { String.class, Integer.TYPE, String.class });
      methodObject24119 = PreparedStatement.class.getDeclaredMethod("getMetaData", new Class[0]);
      methodObject24068 = CallableStatement.class.getDeclaredMethod("setTime", new Class[] { String.class, Time.class, Calendar.class });
      methodObject24074 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24169 = Statement.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject23987 = CallableStatement.class.getDeclaredMethod("getObject", new Class[] { String.class, Map.class });
      methodObject24174 = Statement.class.getDeclaredMethod("getGeneratedKeys", new Class[0]);
      methodObject24069 = CallableStatement.class.getDeclaredMethod("setDate", new Class[] { String.class, Date.class });
      methodObject23988 = CallableStatement.class.getDeclaredMethod("getBoolean", new Class[] { Integer.TYPE });
      methodObject24015 = CallableStatement.class.getDeclaredMethod("setTimestamp", new Class[] { String.class, Timestamp.class });
      methodObject24042 = CallableStatement.class.getDeclaredMethod("getNClob", new Class[] { String.class });
      methodObject24166 = Statement.class.getDeclaredMethod("getFetchDirection", new Class[0]);
      methodObject24021 = CallableStatement.class.getDeclaredMethod("setURL", new Class[] { String.class, URL.class });
      methodObject24084 = CallableStatement.class.getDeclaredMethod("setClob", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24133 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject24085 = CallableStatement.class.getDeclaredMethod("setClob", new Class[] { String.class, Clob.class });
      methodObject24062 = CallableStatement.class.getDeclaredMethod("getTime", new Class[] { String.class, Calendar.class });
      methodObject24037 = CallableStatement.class.getDeclaredMethod("getBlob", new Class[] { Integer.TYPE });
      methodObject24117 = PreparedStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject24055 = CallableStatement.class.getDeclaredMethod("getNCharacterStream", new Class[] { Integer.TYPE });
      methodObject24147 = PreparedStatement.class.getDeclaredMethod("setArray", new Class[] { Integer.TYPE, Array.class });
      methodObject24009 = CallableStatement.class.getDeclaredMethod("setByte", new Class[] { String.class, Byte.TYPE });
      methodObject24123 = PreparedStatement.class.getDeclaredMethod("setTime", new Class[] { Integer.TYPE, Time.class });
      methodObject23998 = CallableStatement.class.getDeclaredMethod("getFloat", new Class[] { Integer.TYPE });
      methodObject24043 = CallableStatement.class.getDeclaredMethod("getRowId", new Class[] { Integer.TYPE });
      methodObject24019 = CallableStatement.class.getDeclaredMethod("getString", new Class[] { Integer.TYPE });
      methodObject24124 = PreparedStatement.class.getDeclaredMethod("setTime", new Class[] { Integer.TYPE, Time.class, Calendar.class });
      methodObject24002 = CallableStatement.class.getDeclaredMethod("getBytes", new Class[] { Integer.TYPE });
      methodObject24006 = CallableStatement.class.getDeclaredMethod("getURL", new Class[] { Integer.TYPE });
      methodObject24040 = CallableStatement.class.getDeclaredMethod("getClob", new Class[] { String.class });
      methodObject24057 = CallableStatement.class.getDeclaredMethod("getNString", new Class[] { Integer.TYPE });
      methodObject24008 = CallableStatement.class.getDeclaredMethod("setBoolean", new Class[] { String.class, Boolean.TYPE });
      methodObject24189 = Statement.class.getDeclaredMethod("setQueryTimeout", new Class[] { Integer.TYPE });
      methodObject24070 = CallableStatement.class.getDeclaredMethod("setDate", new Class[] { String.class, Date.class, Calendar.class });
      methodObject24029 = CallableStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject24071 = CallableStatement.class.getDeclaredMethod("setObject", new Class[] { String.class, Object.class, Integer.TYPE, Integer.TYPE });
      methodObject24014 = CallableStatement.class.getDeclaredMethod("setShort", new Class[] { String.class, Short.TYPE });
      methodObject24180 = Statement.class.getDeclaredMethod("getResultSetConcurrency", new Class[0]);
      methodObject24058 = CallableStatement.class.getDeclaredMethod("getNString", new Class[] { String.class });
      methodObject24032 = CallableStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { String.class, Reader.class, Integer.TYPE });
      methodObject24077 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { String.class, Integer.TYPE });
      methodObject23989 = CallableStatement.class.getDeclaredMethod("getBoolean", new Class[] { String.class });
      methodObject24053 = CallableStatement.class.getDeclaredMethod("getBigDecimal", new Class[] { Integer.TYPE });
      methodObject24121 = PreparedStatement.class.getDeclaredMethod("setBytes", new Class[] { Integer.TYPE, byte[].class });
      methodObject24157 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class });
      methodObject24066 = CallableStatement.class.getDeclaredMethod("getTimestamp", new Class[] { String.class, Calendar.class });
      methodObject24086 = CallableStatement.class.getDeclaredMethod("setClob", new Class[] { String.class, Reader.class });
      methodObject24172 = Statement.class.getDeclaredMethod("addBatch", new Class[] { String.class });
      methodObject24153 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, int[].class });
      methodObject24045 = CallableStatement.class.getDeclaredMethod("getSQLXML", new Class[] { Integer.TYPE });
      methodObject24039 = CallableStatement.class.getDeclaredMethod("getClob", new Class[] { Integer.TYPE });
      methodObject24003 = CallableStatement.class.getDeclaredMethod("getBytes", new Class[] { String.class });
      methodObject24155 = Statement.class.getDeclaredMethod("cancel", new Class[0]);
      methodObject24050 = CallableStatement.class.getDeclaredMethod("getCharacterStream", new Class[] { Integer.TYPE });
      methodObject24026 = CallableStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject24115 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject24156 = Statement.class.getDeclaredMethod("getResultSet", new Class[0]);
      methodObject24028 = CallableStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { String.class, InputStream.class });
      methodObject24016 = CallableStatement.class.getDeclaredMethod("setTimestamp", new Class[] { String.class, Timestamp.class, Calendar.class });
      methodObject24102 = PreparedStatement.class.getDeclaredMethod("setTimestamp", new Class[] { Integer.TYPE, Timestamp.class });
      methodObject24128 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class });
      methodObject24030 = CallableStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject24081 = CallableStatement.class.getDeclaredMethod("setBlob", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject24011 = CallableStatement.class.getDeclaredMethod("setFloat", new Class[] { String.class, Float.TYPE });
      methodObject24186 = Statement.class.getDeclaredMethod("setMaxFieldSize", new Class[] { Integer.TYPE });
      methodObject24131 = PreparedStatement.class.getDeclaredMethod("setBigDecimal", new Class[] { Integer.TYPE, BigDecimal.class });
      methodObject24083 = CallableStatement.class.getDeclaredMethod("setBlob", new Class[] { String.class, InputStream.class });
      methodObject24116 = PreparedStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24148 = PreparedStatement.class.getDeclaredMethod("setRef", new Class[] { Integer.TYPE, Ref.class });
      methodObject24143 = PreparedStatement.class.getDeclaredMethod("setNull", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject24060 = CallableStatement.class.getDeclaredMethod("getTime", new Class[] { Integer.TYPE, Calendar.class });
      methodObject24177 = Statement.class.getDeclaredMethod("getMoreResults", new Class[0]);
      methodObject24020 = CallableStatement.class.getDeclaredMethod("getString", new Class[] { String.class });
      methodObject24000 = CallableStatement.class.getDeclaredMethod("getDouble", new Class[] { Integer.TYPE });
      methodObject24098 = PreparedStatement.class.getDeclaredMethod("setFloat", new Class[] { Integer.TYPE, Float.TYPE });
      methodObject24033 = CallableStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24138 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, NClob.class });
      methodObject23996 = CallableStatement.class.getDeclaredMethod("getLong", new Class[] { Integer.TYPE });
      methodObject24013 = CallableStatement.class.getDeclaredMethod("setLong", new Class[] { String.class, Long.TYPE });
      methodObject24176 = Statement.class.getDeclaredMethod("getMaxRows", new Class[0]);
      methodObject24107 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject24130 = PreparedStatement.class.getDeclaredMethod("addBatch", new Class[0]);
      methodObject24054 = CallableStatement.class.getDeclaredMethod("getBigDecimal", new Class[] { String.class });
      methodObject24113 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class, Integer.TYPE });
      methodObject24122 = PreparedStatement.class.getDeclaredMethod("setString", new Class[] { Integer.TYPE, String.class });
      methodObject24132 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, Blob.class });
      methodObject24178 = Statement.class.getDeclaredMethod("getMoreResults", new Class[] { Integer.TYPE });
      methodObject24179 = Statement.class.getDeclaredMethod("getQueryTimeout", new Class[0]);
      methodObject24136 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24088 = CallableStatement.class.getDeclaredMethod("setNClob", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24051 = CallableStatement.class.getDeclaredMethod("getCharacterStream", new Class[] { String.class });
      methodObject24091 = CallableStatement.class.getDeclaredMethod("setNull", new Class[] { String.class, Integer.TYPE });
      methodObject24038 = CallableStatement.class.getDeclaredMethod("getBlob", new Class[] { String.class });
      methodObject24110 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject24118 = PreparedStatement.class.getDeclaredMethod("executeQuery", new Class[0]);
      methodObject24191 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject24145 = PreparedStatement.class.getDeclaredMethod("setSQLXML", new Class[] { Integer.TYPE, SQLXML.class });
      methodObject24031 = CallableStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { String.class, InputStream.class });
      methodObject24108 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject24075 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24114 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24170 = Statement.class.getDeclaredMethod("setFetchDirection", new Class[] { Integer.TYPE });
      methodObject24168 = Statement.class.getDeclaredMethod("getWarnings", new Class[0]);
      methodObject24137 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject24151 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class });
      methodObject24162 = Statement.class.getDeclaredMethod("executeQuery", new Class[] { String.class });
      methodObject24163 = Statement.class.getDeclaredMethod("getConnection", new Class[0]);
      methodObject24100 = PreparedStatement.class.getDeclaredMethod("setLong", new Class[] { Integer.TYPE, Long.TYPE });
      methodObject24080 = CallableStatement.class.getDeclaredMethod("setBigDecimal", new Class[] { String.class, BigDecimal.class });
      methodObject24017 = CallableStatement.class.getDeclaredMethod("getRef", new Class[] { Integer.TYPE });
      methodObject24152 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, Integer.TYPE });
      methodObject24150 = Statement.class.getDeclaredMethod("close", new Class[0]);
      methodObject24035 = CallableStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject23999 = CallableStatement.class.getDeclaredMethod("getFloat", new Class[] { String.class });
      methodObject24010 = CallableStatement.class.getDeclaredMethod("setDouble", new Class[] { String.class, Double.TYPE });
      methodObject24141 = PreparedStatement.class.getDeclaredMethod("setNString", new Class[] { Integer.TYPE, String.class });
      methodObject24056 = CallableStatement.class.getDeclaredMethod("getNCharacterStream", new Class[] { String.class });
      methodObject24023 = CallableStatement.class.getDeclaredMethod("getDate", new Class[] { Integer.TYPE, Calendar.class });
      methodObject24052 = CallableStatement.class.getDeclaredMethod("getBigDecimal", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject23984 = CallableStatement.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE });
      methodObject24099 = PreparedStatement.class.getDeclaredMethod("setInt", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24101 = PreparedStatement.class.getDeclaredMethod("setShort", new Class[] { Integer.TYPE, Short.TYPE });
      methodObject24185 = Statement.class.getDeclaredMethod("setEscapeProcessing", new Class[] { Boolean.TYPE });
      methodObject24073 = CallableStatement.class.getDeclaredMethod("setObject", new Class[] { String.class, Object.class });
      methodObject24127 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE });
      methodObject24034 = CallableStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { String.class, Reader.class });
      methodObject24173 = Statement.class.getDeclaredMethod("clearBatch", new Class[0]);
      methodObject24007 = CallableStatement.class.getDeclaredMethod("getURL", new Class[] { String.class });
      methodObject24106 = PreparedStatement.class.getDeclaredMethod("executeUpdate", new Class[0]);
      methodObject24096 = PreparedStatement.class.getDeclaredMethod("setByte", new Class[] { Integer.TYPE, Byte.TYPE });
      methodObject24079 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { String.class, Integer.TYPE, String.class });
      methodObject24064 = CallableStatement.class.getDeclaredMethod("getTimestamp", new Class[] { Integer.TYPE, Calendar.class });
      methodObject24142 = PreparedStatement.class.getDeclaredMethod("setNull", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24167 = Statement.class.getDeclaredMethod("getFetchSize", new Class[0]);
      methodObject24076 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject24139 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24012 = CallableStatement.class.getDeclaredMethod("setInt", new Class[] { String.class, Integer.TYPE });
      methodObject23986 = CallableStatement.class.getDeclaredMethod("getObject", new Class[] { String.class });
      methodObject23995 = CallableStatement.class.getDeclaredMethod("getInt", new Class[] { String.class });
      methodObject24025 = CallableStatement.class.getDeclaredMethod("getDate", new Class[] { String.class, Calendar.class });
      methodObject24146 = PreparedStatement.class.getDeclaredMethod("clearParameters", new Class[0]);
      methodObject24063 = CallableStatement.class.getDeclaredMethod("getTimestamp", new Class[] { Integer.TYPE });
      methodObject24160 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, String[].class });
      methodObject24005 = CallableStatement.class.getDeclaredMethod("getArray", new Class[] { String.class });
      methodObject24027 = CallableStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject24082 = CallableStatement.class.getDeclaredMethod("setBlob", new Class[] { String.class, Blob.class });
      methodObject24044 = CallableStatement.class.getDeclaredMethod("getRowId", new Class[] { String.class });
      methodObject24041 = CallableStatement.class.getDeclaredMethod("getNClob", new Class[] { Integer.TYPE });
      methodObject24105 = PreparedStatement.class.getDeclaredMethod("execute", new Class[0]);
      methodObject23994 = CallableStatement.class.getDeclaredMethod("getInt", new Class[] { Integer.TYPE });
      methodObject24120 = PreparedStatement.class.getDeclaredMethod("getParameterMetaData", new Class[0]);
      methodObject24188 = Statement.class.getDeclaredMethod("setPoolable", new Class[] { Boolean.TYPE });
      methodObject24144 = PreparedStatement.class.getDeclaredMethod("setRowId", new Class[] { Integer.TYPE, RowId.class });
      methodObject24149 = PreparedStatement.class.getDeclaredMethod("setUnicodeStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject24125 = PreparedStatement.class.getDeclaredMethod("setDate", new Class[] { Integer.TYPE, Date.class });
      methodObject24087 = CallableStatement.class.getDeclaredMethod("setNClob", new Class[] { String.class, NClob.class });
      methodObject24094 = CallableStatement.class.getDeclaredMethod("setSQLXML", new Class[] { String.class, SQLXML.class });
      methodObject23990 = CallableStatement.class.getDeclaredMethod("getByte", new Class[] { Integer.TYPE });
      methodObject24097 = PreparedStatement.class.getDeclaredMethod("setDouble", new Class[] { Integer.TYPE, Double.TYPE });
      methodObject24140 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject24175 = Statement.class.getDeclaredMethod("getMaxFieldSize", new Class[0]);
      methodObject24004 = CallableStatement.class.getDeclaredMethod("getArray", new Class[] { Integer.TYPE });
      methodObject24090 = CallableStatement.class.getDeclaredMethod("setNString", new Class[] { String.class, String.class });
      methodObject24022 = CallableStatement.class.getDeclaredMethod("getDate", new Class[] { Integer.TYPE });
      methodObject24059 = CallableStatement.class.getDeclaredMethod("getTime", new Class[] { Integer.TYPE });
      methodObject24024 = CallableStatement.class.getDeclaredMethod("getDate", new Class[] { String.class });
      methodObject24065 = CallableStatement.class.getDeclaredMethod("getTimestamp", new Class[] { String.class });
      methodObject24078 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { String.class, Integer.TYPE, Integer.TYPE });
      methodObject23993 = CallableStatement.class.getDeclaredMethod("getShort", new Class[] { String.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2java$1sql$1CallableStatement$$$Proxy(CallableStatement paramCallableStatement, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramCallableStatement;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2java$1sql$1CallableStatement$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */