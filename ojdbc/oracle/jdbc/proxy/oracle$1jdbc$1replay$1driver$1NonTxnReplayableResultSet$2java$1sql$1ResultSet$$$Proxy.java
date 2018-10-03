package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
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
import oracle.jdbc.replay.driver.NonTxnReplayableResultSet;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableResultSet$2java$1sql$1ResultSet$$$Proxy
  extends NonTxnReplayableResultSet
  implements ResultSet, _Proxy_
{
  private ResultSet delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject27919;
  private static Method methodObject27927;
  private static Method methodObject27869;
  private static Method methodObject27907;
  private static Method methodObject27922;
  private static Method methodObject27799;
  private static Method methodObject27821;
  private static Method methodObject27838;
  private static Method methodObject27934;
  private static Method methodObject27811;
  private static Method methodObject27813;
  private static Method methodObject27889;
  private static Method methodObject27781;
  private static Method methodObject27909;
  private static Method methodObject27765;
  private static Method methodObject27816;
  private static Method methodObject27820;
  private static Method methodObject27936;
  private static Method methodObject27840;
  private static Method methodObject27808;
  private static Method methodObject27892;
  private static Method methodObject27760;
  private static Method methodObject27878;
  private static Method methodObject27871;
  private static Method methodObject27754;
  private static Method methodObject27810;
  private static Method methodObject27926;
  private static Method methodObject27912;
  private static Method methodObject27835;
  private static Method methodObject27857;
  private static Method methodObject27849;
  private static Method methodObject27911;
  private static Method methodObject27783;
  private static Method methodObject27768;
  private static Method methodObject27759;
  private static Method methodObject27872;
  private static Method methodObject27914;
  private static Method methodObject27901;
  private static Method methodObject27803;
  private static Method methodObject27879;
  private static Method methodObject27920;
  private static Method methodObject27764;
  private static Method methodObject27806;
  private static Method methodObject27913;
  private static Method methodObject27828;
  private static Method methodObject27903;
  private static Method methodObject27887;
  private static Method methodObject27844;
  private static Method methodObject27924;
  private static Method methodObject27827;
  private static Method methodObject27906;
  private static Method methodObject27856;
  private static Method methodObject27802;
  private static Method methodObject27809;
  private static Method methodObject27864;
  private static Method methodObject27866;
  private static Method methodObject27831;
  private static Method methodObject27939;
  private static Method methodObject27791;
  private static Method methodObject27836;
  private static Method methodObject27824;
  private static Method methodObject27940;
  private static Method methodObject27904;
  private static Method methodObject27769;
  private static Method methodObject27874;
  private static Method methodObject27789;
  private static Method methodObject27833;
  private static Method methodObject27897;
  private static Method methodObject27868;
  private static Method methodObject27902;
  private static Method methodObject27755;
  private static Method methodObject27877;
  private static Method methodObject27826;
  private static Method methodObject27928;
  private static Method methodObject27885;
  private static Method methodObject27865;
  private static Method methodObject27880;
  private static Method methodObject27899;
  private static Method methodObject27886;
  private static Method methodObject27819;
  private static Method methodObject27812;
  private static Method methodObject27825;
  private static Method methodObject27882;
  private static Method methodObject27876;
  private static Method methodObject27823;
  private static Method methodObject27805;
  private static Method methodObject27881;
  private static Method methodObject27756;
  private static Method methodObject27846;
  private static Method methodObject27780;
  private static Method methodObject27896;
  private static Method methodObject27777;
  private static Method methodObject27795;
  private static Method methodObject27767;
  private static Method methodObject27847;
  private static Method methodObject27851;
  private static Method methodObject27784;
  private static Method methodObject27893;
  private static Method methodObject27787;
  private static Method methodObject27858;
  private static Method methodObject27779;
  private static Method methodObject27841;
  private static Method methodObject27790;
  private static Method methodObject27916;
  private static Method methodObject27888;
  private static Method methodObject27752;
  private static Method methodObject27850;
  private static Method methodObject27822;
  private static Method methodObject27891;
  private static Method methodObject27863;
  private static Method methodObject27917;
  private static Method methodObject27931;
  private static Method methodObject27929;
  private static Method methodObject27910;
  private static Method methodObject27898;
  private static Method methodObject27918;
  private static Method methodObject27774;
  private static Method methodObject27776;
  private static Method methodObject27766;
  private static Method methodObject27937;
  private static Method methodObject27930;
  private static Method methodObject27796;
  private static Method methodObject27778;
  private static Method methodObject27807;
  private static Method methodObject27782;
  private static Method methodObject27829;
  private static Method methodObject27935;
  private static Method methodObject27923;
  private static Method methodObject27854;
  private static Method methodObject27770;
  private static Method methodObject27775;
  private static Method methodObject27839;
  private static Method methodObject27908;
  private static Method methodObject27861;
  private static Method methodObject27801;
  private static Method methodObject27832;
  private static Method methodObject27793;
  private static Method methodObject27848;
  private static Method methodObject27932;
  private static Method methodObject27921;
  private static Method methodObject27852;
  private static Method methodObject27842;
  private static Method methodObject27870;
  private static Method methodObject27905;
  private static Method methodObject27817;
  private static Method methodObject27938;
  private static Method methodObject27788;
  private static Method methodObject27763;
  private static Method methodObject27753;
  private static Method methodObject27818;
  private static Method methodObject27859;
  private static Method methodObject27837;
  private static Method methodObject27915;
  private static Method methodObject27853;
  private static Method methodObject27773;
  private static Method methodObject27925;
  private static Method methodObject27883;
  private static Method methodObject27797;
  private static Method methodObject27794;
  private static Method methodObject27762;
  private static Method methodObject27815;
  private static Method methodObject27867;
  private static Method methodObject27834;
  private static Method methodObject27757;
  private static Method methodObject27843;
  private static Method methodObject27845;
  private static Method methodObject27814;
  private static Method methodObject27862;
  private static Method methodObject27894;
  private static Method methodObject27758;
  private static Method methodObject27890;
  private static Method methodObject27884;
  private static Method methodObject27798;
  private static Method methodObject27792;
  private static Method methodObject27772;
  private static Method methodObject27895;
  private static Method methodObject27771;
  private static Method methodObject27873;
  private static Method methodObject27875;
  private static Method methodObject27800;
  private static Method methodObject27785;
  private static Method methodObject27830;
  private static Method methodObject27855;
  private static Method methodObject27786;
  private static Method methodObject27860;
  private static Method methodObject27900;
  private static Method methodObject27761;
  private static Method methodObject27933;
  private static Method methodObject27804;
  
  public void updateNull(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27919, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.updateNull(arg0);
      postForAll(methodObject27919);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27919, e);
    }
  }
  
  public void updateRowId(int arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27927, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27927);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27927, e);
    }
  }
  
  public boolean isLast()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27869, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27869, Boolean.valueOf(this.delegate.isLast()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27869, onErrorForAll(methodObject27869, e))).booleanValue();
    }
  }
  
  public void updateInt(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27907, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.updateInt(arg0, arg1);
      postForAll(methodObject27907);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27907, e);
    }
  }
  
  public void updateObject(int arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27922, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27922);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27922, e);
    }
  }
  
  public SQLXML getSQLXML(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27799, this, new Object[] { arg0 });
      return (SQLXML)postForAll(methodObject27799, this.proxyFactory.proxyForCreate((Object)this.delegate.getSQLXML(arg0), this, this.proxyCache, methodObject27799));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject27799, onErrorForAll(methodObject27799, e));
    }
  }
  
  public void updateNCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27821, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateNCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject27821);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27821, e);
    }
  }
  
  public boolean first()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27838, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27838, Boolean.valueOf(this.delegate.first()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27838, onErrorForAll(methodObject27838, e))).booleanValue();
    }
  }
  
  public void updateString(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27934, this, new Object[] { arg0, arg1 });
      this.delegate.updateString(arg0, arg1);
      postForAll(methodObject27934);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27934, e);
    }
  }
  
  public void updateBinaryStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27811, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateBinaryStream(arg0, arg1, arg2);
      postForAll(methodObject27811);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27811, e);
    }
  }
  
  public void updateBinaryStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27813, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBinaryStream(arg0, arg1);
      postForAll(methodObject27813);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27813, e);
    }
  }
  
  public void updateBoolean(int arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27889, this, new Object[] { Integer.valueOf(arg0), Boolean.valueOf(arg1) });
      this.delegate.updateBoolean(arg0, arg1);
      postForAll(methodObject27889);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27889, e);
    }
  }
  
  public Ref getRef(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27781, this, new Object[] { arg0 });
      return (Ref)postForAll(methodObject27781, this.proxyFactory.proxyForCreate((Object)this.delegate.getRef(arg0), this, this.proxyCache, methodObject27781));
    }
    catch (SQLException e)
    {
      return (Ref)postForAll(methodObject27781, onErrorForAll(methodObject27781, e));
    }
  }
  
  public void updateLong(int arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27909, this, new Object[] { Integer.valueOf(arg0), Long.valueOf(arg1) });
      this.delegate.updateLong(arg0, arg1);
      postForAll(methodObject27909);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27909, e);
    }
  }
  
  public long getLong(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27765, this, new Object[] { arg0 });
      return ((Long)postForAll(methodObject27765, Long.valueOf(this.delegate.getLong(arg0)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject27765, onErrorForAll(methodObject27765, e))).longValue();
    }
  }
  
  public void updateCharacterStream(String arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27816, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.updateCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject27816);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27816, e);
    }
  }
  
  public void updateCharacterStream(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27820, this, new Object[] { arg0, arg1 });
      this.delegate.updateCharacterStream(arg0, arg1);
      postForAll(methodObject27820);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27820, e);
    }
  }
  
  public void updateTime(String arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27936, this, new Object[] { arg0, arg1 });
      this.delegate.updateTime(arg0, arg1);
      postForAll(methodObject27936);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27936, e);
    }
  }
  
  public InputStream getAsciiStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27840, this, new Object[] { arg0 });
      return (InputStream)postForAll(methodObject27840, (Object)this.delegate.getAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject27840, onErrorForAll(methodObject27840, e));
    }
  }
  
  public void updateAsciiStream(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27808, this, new Object[] { arg0, arg1 });
      this.delegate.updateAsciiStream(arg0, arg1);
      postForAll(methodObject27808);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27808, e);
    }
  }
  
  public void updateByte(String arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27892, this, new Object[] { arg0, Byte.valueOf(arg1) });
      this.delegate.updateByte(arg0, arg1);
      postForAll(methodObject27892);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27892, e);
    }
  }
  
  public short getShort(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27760, this, new Object[] { Integer.valueOf(arg0) });
      return ((Short)postForAll(methodObject27760, Short.valueOf(this.delegate.getShort(arg0)))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject27760, onErrorForAll(methodObject27760, e))).shortValue();
    }
  }
  
  public void setFetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27878, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchSize(arg0);
      postForAll(methodObject27878);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27878, e);
    }
  }
  
  public void moveToInsertRow()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27871, this, zeroLengthObjectArray);
      this.delegate.moveToInsertRow();
      postForAll(methodObject27871);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27871, e);
    }
  }
  
  public Object getObject(int arg0, Map arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27754, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject27754, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject27754));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject27754, onErrorForAll(methodObject27754, e));
    }
  }
  
  public void updateBinaryStream(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27810, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.updateBinaryStream(arg0, arg1, arg2);
      postForAll(methodObject27810);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27810, e);
    }
  }
  
  public void updateRef(String arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27926, this, new Object[] { arg0, arg1 });
      this.delegate.updateRef(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27926);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27926, e);
    }
  }
  
  public void updateNClob(String arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27912, this, new Object[] { arg0, arg1 });
      this.delegate.updateNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27912);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27912, e);
    }
  }
  
  public void cancelRowUpdates()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27835, this, zeroLengthObjectArray);
      this.delegate.cancelRowUpdates();
      postForAll(methodObject27835);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27835, e);
    }
  }
  
  public Time getTime(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27857, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Time)postForAll(methodObject27857, (Object)this.delegate.getTime(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject27857, onErrorForAll(methodObject27857, e));
    }
  }
  
  public int getHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27849, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27849, Integer.valueOf(this.delegate.getHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27849, onErrorForAll(methodObject27849, e))).intValue();
    }
  }
  
  public void updateNClob(int arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27911, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27911);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27911, e);
    }
  }
  
  public String getString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27783, this, new Object[] { arg0 });
      return (String)postForAll(methodObject27783, (Object)this.delegate.getString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27783, onErrorForAll(methodObject27783, e));
    }
  }
  
  public double getDouble(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27768, this, new Object[] { Integer.valueOf(arg0) });
      return ((Double)postForAll(methodObject27768, Double.valueOf(this.delegate.getDouble(arg0)))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject27768, onErrorForAll(methodObject27768, e))).doubleValue();
    }
  }
  
  public byte getByte(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27759, this, new Object[] { arg0 });
      return ((Byte)postForAll(methodObject27759, Byte.valueOf(this.delegate.getByte(arg0)))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject27759, onErrorForAll(methodObject27759, e))).byteValue();
    }
  }
  
  public void refreshRow()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27872, this, zeroLengthObjectArray);
      this.delegate.refreshRow();
      postForAll(methodObject27872);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27872, e);
    }
  }
  
  public void updateNClob(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27914, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateNClob(arg0, arg1, arg2);
      postForAll(methodObject27914);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27914, e);
    }
  }
  
  public void updateDate(int arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27901, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateDate(arg0, arg1);
      postForAll(methodObject27901);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27901, e);
    }
  }
  
  public void updateAsciiStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27803, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.updateAsciiStream(arg0, arg1, arg2);
      postForAll(methodObject27803);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27803, e);
    }
  }
  
  public void updateArray(int arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27879, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateArray(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27879);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27879, e);
    }
  }
  
  public void updateNull(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27920, this, new Object[] { arg0 });
      this.delegate.updateNull(arg0);
      postForAll(methodObject27920);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27920, e);
    }
  }
  
  public long getLong(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27764, this, new Object[] { Integer.valueOf(arg0) });
      return ((Long)postForAll(methodObject27764, Long.valueOf(this.delegate.getLong(arg0)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject27764, onErrorForAll(methodObject27764, e))).longValue();
    }
  }
  
  public void updateAsciiStream(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27806, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateAsciiStream(arg0, arg1, arg2);
      postForAll(methodObject27806);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27806, e);
    }
  }
  
  public void updateNClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27913, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateNClob(arg0, arg1, arg2);
      postForAll(methodObject27913);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27913, e);
    }
  }
  
  public InputStream getBinaryStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27828, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject27828, (Object)this.delegate.getBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject27828, onErrorForAll(methodObject27828, e));
    }
  }
  
  public void updateDouble(int arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27903, this, new Object[] { Integer.valueOf(arg0), Double.valueOf(arg1) });
      this.delegate.updateDouble(arg0, arg1);
      postForAll(methodObject27903);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27903, e);
    }
  }
  
  public void updateBlob(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27887, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBlob(arg0, arg1);
      postForAll(methodObject27887);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27887, e);
    }
  }
  
  public BigDecimal getBigDecimal(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27844, this, new Object[] { arg0 });
      return (BigDecimal)postForAll(methodObject27844, (Object)this.delegate.getBigDecimal(arg0));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject27844, onErrorForAll(methodObject27844, e));
    }
  }
  
  public void updateObject(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27924, this, new Object[] { arg0, arg1 });
      this.delegate.updateObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27924);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27924, e);
    }
  }
  
  public boolean wasNull()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27827, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27827, Boolean.valueOf(this.delegate.wasNull()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27827, onErrorForAll(methodObject27827, e))).booleanValue();
    }
  }
  
  public void updateFloat(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27906, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.updateFloat(arg0, arg1);
      postForAll(methodObject27906);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27906, e);
    }
  }
  
  public Time getTime(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27856, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject27856, (Object)this.delegate.getTime(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject27856, onErrorForAll(methodObject27856, e));
    }
  }
  
  public void updateRow()
    throws SQLException
  {
    try
    {
      super.preForRowUpdates(methodObject27802, this, zeroLengthObjectArray);
      this.delegate.updateRow();
      postForAll(methodObject27802);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27802, e);
    }
  }
  
  public void updateBinaryStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27809, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.updateBinaryStream(arg0, arg1, arg2);
      postForAll(methodObject27809);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27809, e);
    }
  }
  
  public InputStream getUnicodeStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27864, this, new Object[] { arg0 });
      return (InputStream)postForAll(methodObject27864, (Object)this.delegate.getUnicodeStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject27864, onErrorForAll(methodObject27864, e));
    }
  }
  
  public boolean isAfterLast()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27866, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27866, Boolean.valueOf(this.delegate.isAfterLast()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27866, onErrorForAll(methodObject27866, e))).booleanValue();
    }
  }
  
  public Reader getCharacterStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27831, this, new Object[] { arg0 });
      return (Reader)postForAll(methodObject27831, (Object)this.delegate.getCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject27831, onErrorForAll(methodObject27831, e));
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27939, this, new Object[] { arg0 });
      return postForAll(methodObject27939, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject27939, onErrorForAll(methodObject27939, e));
    }
  }
  
  public Blob getBlob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27791, this, new Object[] { arg0 });
      return (Blob)postForAll(methodObject27791, this.proxyFactory.proxyForCreate((Object)this.delegate.getBlob(arg0), this, this.proxyCache, methodObject27791));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject27791, onErrorForAll(methodObject27791, e));
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27836, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      postForAll(methodObject27836);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27836, e);
    }
  }
  
  public void updateNCharacterStream(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27824, this, new Object[] { arg0, arg1 });
      this.delegate.updateNCharacterStream(arg0, arg1);
      postForAll(methodObject27824);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27824, e);
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27940, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject27940, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27940, onErrorForAll(methodObject27940, e))).booleanValue();
    }
  }
  
  public void updateDouble(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27904, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.updateDouble(arg0, arg1);
      postForAll(methodObject27904);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27904, e);
    }
  }
  
  public double getDouble(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27769, this, new Object[] { arg0 });
      return ((Double)postForAll(methodObject27769, Double.valueOf(this.delegate.getDouble(arg0)))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject27769, onErrorForAll(methodObject27769, e))).doubleValue();
    }
  }
  
  public boolean rowDeleted()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27874, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27874, Boolean.valueOf(this.delegate.rowDeleted()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27874, onErrorForAll(methodObject27874, e))).booleanValue();
    }
  }
  
  public ResultSetMetaData getMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27789, this, zeroLengthObjectArray);
      return (ResultSetMetaData)postForAll(methodObject27789, this.proxyFactory.proxyForCreate((Object)this.delegate.getMetaData(), this, this.proxyCache, methodObject27789));
    }
    catch (SQLException e)
    {
      return (ResultSetMetaData)postForAll(methodObject27789, onErrorForAll(methodObject27789, e));
    }
  }
  
  public void afterLast()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27833, this, zeroLengthObjectArray);
      this.delegate.afterLast();
      postForAll(methodObject27833);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27833, e);
    }
  }
  
  public void updateClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27897, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateClob(arg0, arg1, arg2);
      postForAll(methodObject27897);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27897, e);
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27868, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27868, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27868, onErrorForAll(methodObject27868, e))).booleanValue();
    }
  }
  
  public void updateDate(String arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27902, this, new Object[] { arg0, arg1 });
      this.delegate.updateDate(arg0, arg1);
      postForAll(methodObject27902);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27902, e);
    }
  }
  
  public Object getObject(String arg0, Map arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27755, this, new Object[] { arg0, arg1 });
      return postForAll(methodObject27755, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject27755));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject27755, onErrorForAll(methodObject27755, e));
    }
  }
  
  public void setFetchDirection(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27877, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchDirection(arg0);
      postForAll(methodObject27877);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27877, e);
    }
  }
  
  public Statement getStatement()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27826, this, zeroLengthObjectArray);
      return (Statement)postForAll(methodObject27826, (Object)super.getStatement());
    }
    catch (SQLException e)
    {
      return (Statement)postForAll(methodObject27826, onErrorForAll(methodObject27826, e));
    }
  }
  
  public void updateRowId(String arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27928, this, new Object[] { arg0, arg1 });
      this.delegate.updateRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27928);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27928, e);
    }
  }
  
  public void updateBlob(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27885, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateBlob(arg0, arg1, arg2);
      postForAll(methodObject27885);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27885, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27865, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject27865, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject27865, onErrorForAll(methodObject27865, e));
    }
  }
  
  public void updateArray(String arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27880, this, new Object[] { arg0, arg1 });
      this.delegate.updateArray(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27880);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27880, e);
    }
  }
  
  public void updateClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27899, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateClob(arg0, arg1);
      postForAll(methodObject27899);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27899, e);
    }
  }
  
  public void updateBlob(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27886, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateBlob(arg0, arg1, arg2);
      postForAll(methodObject27886);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27886, e);
    }
  }
  
  public void updateCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27819, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateCharacterStream(arg0, arg1);
      postForAll(methodObject27819);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27819, e);
    }
  }
  
  public void updateBinaryStream(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27812, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateBinaryStream(arg0, arg1, arg2);
      postForAll(methodObject27812);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27812, e);
    }
  }
  
  public boolean last()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27825, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27825, Boolean.valueOf(this.delegate.last()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27825, Boolean.valueOf(onErrorForLast(methodObject27825, e)))).booleanValue();
    }
  }
  
  public void updateBigDecimal(String arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27882, this, new Object[] { arg0, arg1 });
      this.delegate.updateBigDecimal(arg0, arg1);
      postForAll(methodObject27882);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27882, e);
    }
  }
  
  public boolean rowUpdated()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27876, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27876, Boolean.valueOf(this.delegate.rowUpdated()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27876, onErrorForAll(methodObject27876, e))).booleanValue();
    }
  }
  
  public void updateNCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27823, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateNCharacterStream(arg0, arg1);
      postForAll(methodObject27823);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27823, e);
    }
  }
  
  public void updateAsciiStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27805, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateAsciiStream(arg0, arg1, arg2);
      postForAll(methodObject27805);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27805, e);
    }
  }
  
  public void updateBigDecimal(int arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27881, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBigDecimal(arg0, arg1);
      postForAll(methodObject27881);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27881, e);
    }
  }
  
  public boolean getBoolean(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27756, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject27756, Boolean.valueOf(this.delegate.getBoolean(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27756, onErrorForAll(methodObject27756, e))).booleanValue();
    }
  }
  
  public String getCursorName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27846, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27846, (Object)this.delegate.getCursorName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27846, onErrorForAll(methodObject27846, e));
    }
  }
  
  public Ref getRef(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27780, this, new Object[] { Integer.valueOf(arg0) });
      return (Ref)postForAll(methodObject27780, this.proxyFactory.proxyForCreate((Object)this.delegate.getRef(arg0), this, this.proxyCache, methodObject27780));
    }
    catch (SQLException e)
    {
      return (Ref)postForAll(methodObject27780, onErrorForAll(methodObject27780, e));
    }
  }
  
  public void updateClob(String arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27896, this, new Object[] { arg0, arg1 });
      this.delegate.updateClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27896);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27896, e);
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27777, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClose(methodObject27777);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27777, e);
    }
  }
  
  public NClob getNClob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27795, this, new Object[] { arg0 });
      return (NClob)postForAll(methodObject27795, this.proxyFactory.proxyForCreate((Object)this.delegate.getNClob(arg0), this, this.proxyCache, methodObject27795));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject27795, onErrorForAll(methodObject27795, e));
    }
  }
  
  public float getFloat(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27767, this, new Object[] { arg0 });
      return ((Float)postForAll(methodObject27767, Float.valueOf(this.delegate.getFloat(arg0)))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject27767, onErrorForAll(methodObject27767, e))).floatValue();
    }
  }
  
  public int getFetchDirection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27847, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27847, Integer.valueOf(this.delegate.getFetchDirection()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27847, onErrorForAll(methodObject27847, e))).intValue();
    }
  }
  
  public Reader getNCharacterStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27851, this, new Object[] { arg0 });
      return (Reader)postForAll(methodObject27851, (Object)this.delegate.getNCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject27851, onErrorForAll(methodObject27851, e));
    }
  }
  
  public boolean isFirst()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27784, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27784, Boolean.valueOf(this.delegate.isFirst()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27784, onErrorForAll(methodObject27784, e))).booleanValue();
    }
  }
  
  public void updateBytes(int arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27893, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBytes(arg0, arg1);
      postForAll(methodObject27893);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27893, e);
    }
  }
  
  public Date getDate(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27787, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Date)postForAll(methodObject27787, (Object)this.delegate.getDate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject27787, onErrorForAll(methodObject27787, e));
    }
  }
  
  public Time getTime(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27858, this, new Object[] { arg0, arg1 });
      return (Time)postForAll(methodObject27858, (Object)this.delegate.getTime(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject27858, onErrorForAll(methodObject27858, e));
    }
  }
  
  public boolean previous()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27779, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27779, Boolean.valueOf(this.delegate.previous()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27779, onErrorForAll(methodObject27779, e))).booleanValue();
    }
  }
  
  public BigDecimal getBigDecimal(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27841, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      return (BigDecimal)postForAll(methodObject27841, (Object)this.delegate.getBigDecimal(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject27841, onErrorForAll(methodObject27841, e));
    }
  }
  
  public Blob getBlob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27790, this, new Object[] { Integer.valueOf(arg0) });
      return (Blob)postForAll(methodObject27790, this.proxyFactory.proxyForCreate((Object)this.delegate.getBlob(arg0), this, this.proxyCache, methodObject27790));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject27790, onErrorForAll(methodObject27790, e));
    }
  }
  
  public void updateNClob(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27916, this, new Object[] { arg0, arg1 });
      this.delegate.updateNClob(arg0, arg1);
      postForAll(methodObject27916);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27916, e);
    }
  }
  
  public void updateBlob(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27888, this, new Object[] { arg0, arg1 });
      this.delegate.updateBlob(arg0, arg1);
      postForAll(methodObject27888);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27888, e);
    }
  }
  
  public Object getObject(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27752, this, new Object[] { Integer.valueOf(arg0) });
      return postForAll(methodObject27752, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0), this, this.proxyCache, methodObject27752));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject27752, onErrorForAll(methodObject27752, e));
    }
  }
  
  public Reader getNCharacterStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27850, this, new Object[] { Integer.valueOf(arg0) });
      return (Reader)postForAll(methodObject27850, (Object)this.delegate.getNCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject27850, onErrorForAll(methodObject27850, e));
    }
  }
  
  public void updateNCharacterStream(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27822, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateNCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject27822);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27822, e);
    }
  }
  
  public void updateByte(int arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27891, this, new Object[] { Integer.valueOf(arg0), Byte.valueOf(arg1) });
      this.delegate.updateByte(arg0, arg1);
      postForAll(methodObject27891);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27891, e);
    }
  }
  
  public InputStream getUnicodeStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27863, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject27863, (Object)this.delegate.getUnicodeStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject27863, onErrorForAll(methodObject27863, e));
    }
  }
  
  public void updateNString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27917, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateNString(arg0, arg1);
      postForAll(methodObject27917);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27917, e);
    }
  }
  
  public void updateShort(int arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27931, this, new Object[] { Integer.valueOf(arg0), Short.valueOf(arg1) });
      this.delegate.updateShort(arg0, arg1);
      postForAll(methodObject27931);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27931, e);
    }
  }
  
  public void updateSQLXML(int arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27929, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27929);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27929, e);
    }
  }
  
  public void updateLong(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27910, this, new Object[] { arg0, Long.valueOf(arg1) });
      this.delegate.updateLong(arg0, arg1);
      postForAll(methodObject27910);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27910, e);
    }
  }
  
  public void updateClob(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27898, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateClob(arg0, arg1, arg2);
      postForAll(methodObject27898);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27898, e);
    }
  }
  
  public void updateNString(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27918, this, new Object[] { arg0, arg1 });
      this.delegate.updateNString(arg0, arg1);
      postForAll(methodObject27918);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27918, e);
    }
  }
  
  public boolean next()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27774, this, zeroLengthObjectArray);
      return postForNext(methodObject27774, this.delegate.next());
    }
    catch (SQLException e)
    {
      return postForNext(methodObject27774, ((Boolean)onErrorForAll(methodObject27774, e)).booleanValue());
    }
  }
  
  public URL getURL(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27776, this, new Object[] { arg0 });
      return (URL)postForAll(methodObject27776, (Object)this.delegate.getURL(arg0));
    }
    catch (SQLException e)
    {
      return (URL)postForAll(methodObject27776, onErrorForAll(methodObject27776, e));
    }
  }
  
  public float getFloat(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27766, this, new Object[] { Integer.valueOf(arg0) });
      return ((Float)postForAll(methodObject27766, Float.valueOf(this.delegate.getFloat(arg0)))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject27766, onErrorForAll(methodObject27766, e))).floatValue();
    }
  }
  
  public void updateTimestamp(int arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27937, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateTimestamp(arg0, arg1);
      postForAll(methodObject27937);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27937, e);
    }
  }
  
  public void updateSQLXML(String arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27930, this, new Object[] { arg0, arg1 });
      this.delegate.updateSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27930);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27930, e);
    }
  }
  
  public RowId getRowId(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27796, this, new Object[] { Integer.valueOf(arg0) });
      return (RowId)postForAll(methodObject27796, this.proxyFactory.proxyForCreate((Object)this.delegate.getRowId(arg0), this, this.proxyCache, methodObject27796));
    }
    catch (SQLException e)
    {
      return (RowId)postForAll(methodObject27796, onErrorForAll(methodObject27796, e));
    }
  }
  
  public int getType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27778, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27778, Integer.valueOf(this.delegate.getType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27778, onErrorForAll(methodObject27778, e))).intValue();
    }
  }
  
  public void updateAsciiStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27807, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateAsciiStream(arg0, arg1);
      postForAll(methodObject27807);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27807, e);
    }
  }
  
  public String getString(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27782, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject27782, (Object)this.delegate.getString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27782, onErrorForAll(methodObject27782, e));
    }
  }
  
  public InputStream getBinaryStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27829, this, new Object[] { arg0 });
      return (InputStream)postForAll(methodObject27829, (Object)this.delegate.getBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject27829, onErrorForAll(methodObject27829, e));
    }
  }
  
  public void updateTime(int arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27935, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateTime(arg0, arg1);
      postForAll(methodObject27935);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27935, e);
    }
  }
  
  public void updateObject(String arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27923, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.updateObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      postForAll(methodObject27923);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27923, e);
    }
  }
  
  public int getRow()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27854, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27854, Integer.valueOf(this.delegate.getRow()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27854, onErrorForAll(methodObject27854, e))).intValue();
    }
  }
  
  public byte[] getBytes(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27770, this, new Object[] { Integer.valueOf(arg0) });
      return (byte[])postForAll(methodObject27770, (Object)this.delegate.getBytes(arg0));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject27770, onErrorForAll(methodObject27770, e));
    }
  }
  
  public URL getURL(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27775, this, new Object[] { Integer.valueOf(arg0) });
      return (URL)postForAll(methodObject27775, (Object)this.delegate.getURL(arg0));
    }
    catch (SQLException e)
    {
      return (URL)postForAll(methodObject27775, onErrorForAll(methodObject27775, e));
    }
  }
  
  public InputStream getAsciiStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27839, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject27839, (Object)this.delegate.getAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject27839, onErrorForAll(methodObject27839, e));
    }
  }
  
  public void updateInt(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27908, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.updateInt(arg0, arg1);
      postForAll(methodObject27908);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27908, e);
    }
  }
  
  public Timestamp getTimestamp(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27861, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Timestamp)postForAll(methodObject27861, (Object)this.delegate.getTimestamp(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject27861, onErrorForAll(methodObject27861, e));
    }
  }
  
  public void insertRow()
    throws SQLException
  {
    try
    {
      super.preForRowUpdates(methodObject27801, this, zeroLengthObjectArray);
      this.delegate.insertRow();
      postForAll(methodObject27801);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27801, e);
    }
  }
  
  public boolean absolute(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27832, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject27832, Boolean.valueOf(this.delegate.absolute(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27832, onErrorForAll(methodObject27832, e))).booleanValue();
    }
  }
  
  public Clob getClob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27793, this, new Object[] { arg0 });
      return (Clob)postForAll(methodObject27793, this.proxyFactory.proxyForCreate((Object)this.delegate.getClob(arg0), this, this.proxyCache, methodObject27793));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject27793, onErrorForAll(methodObject27793, e));
    }
  }
  
  public int getFetchSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27848, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27848, Integer.valueOf(this.delegate.getFetchSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27848, onErrorForAll(methodObject27848, e))).intValue();
    }
  }
  
  public void updateShort(String arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27932, this, new Object[] { arg0, Short.valueOf(arg1) });
      this.delegate.updateShort(arg0, arg1);
      postForAll(methodObject27932);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27932, e);
    }
  }
  
  public void updateObject(int arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27921, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.updateObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      postForAll(methodObject27921);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27921, e);
    }
  }
  
  public String getNString(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27852, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject27852, (Object)this.delegate.getNString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27852, onErrorForAll(methodObject27852, e));
    }
  }
  
  public BigDecimal getBigDecimal(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27842, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return (BigDecimal)postForAll(methodObject27842, (Object)this.delegate.getBigDecimal(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject27842, onErrorForAll(methodObject27842, e));
    }
  }
  
  public void moveToCurrentRow()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27870, this, zeroLengthObjectArray);
      this.delegate.moveToCurrentRow();
      postForAll(methodObject27870);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27870, e);
    }
  }
  
  public void updateFloat(int arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27905, this, new Object[] { Integer.valueOf(arg0), Float.valueOf(arg1) });
      this.delegate.updateFloat(arg0, arg1);
      postForAll(methodObject27905);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27905, e);
    }
  }
  
  public void updateCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27817, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject27817);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27817, e);
    }
  }
  
  public void updateTimestamp(String arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27938, this, new Object[] { arg0, arg1 });
      this.delegate.updateTimestamp(arg0, arg1);
      postForAll(methodObject27938);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27938, e);
    }
  }
  
  public Date getDate(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27788, this, new Object[] { arg0, arg1 });
      return (Date)postForAll(methodObject27788, (Object)this.delegate.getDate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject27788, onErrorForAll(methodObject27788, e));
    }
  }
  
  public int getInt(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27763, this, new Object[] { arg0 });
      return ((Integer)postForAll(methodObject27763, Integer.valueOf(this.delegate.getInt(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27763, onErrorForAll(methodObject27763, e))).intValue();
    }
  }
  
  public Object getObject(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27753, this, new Object[] { arg0 });
      return postForAll(methodObject27753, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0), this, this.proxyCache, methodObject27753));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject27753, onErrorForAll(methodObject27753, e));
    }
  }
  
  public void updateCharacterStream(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27818, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject27818);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27818, e);
    }
  }
  
  public Timestamp getTimestamp(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27859, this, new Object[] { Integer.valueOf(arg0) });
      return (Timestamp)postForAll(methodObject27859, (Object)this.delegate.getTimestamp(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject27859, onErrorForAll(methodObject27859, e));
    }
  }
  
  public int findColumn(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27837, this, new Object[] { arg0 });
      return ((Integer)postForAll(methodObject27837, Integer.valueOf(this.delegate.findColumn(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27837, onErrorForAll(methodObject27837, e))).intValue();
    }
  }
  
  public void updateNClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27915, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateNClob(arg0, arg1);
      postForAll(methodObject27915);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27915, e);
    }
  }
  
  public String getNString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27853, this, new Object[] { arg0 });
      return (String)postForAll(methodObject27853, (Object)this.delegate.getNString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27853, onErrorForAll(methodObject27853, e));
    }
  }
  
  public Array getArray(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27773, this, new Object[] { arg0 });
      return (Array)postForAll(methodObject27773, this.proxyFactory.proxyForCreate((Object)this.delegate.getArray(arg0), this, this.proxyCache, methodObject27773));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject27773, onErrorForAll(methodObject27773, e));
    }
  }
  
  public void updateRef(int arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27925, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateRef(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27925);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27925, e);
    }
  }
  
  public void updateBlob(int arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27883, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27883);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27883, e);
    }
  }
  
  public RowId getRowId(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27797, this, new Object[] { arg0 });
      return (RowId)postForAll(methodObject27797, this.proxyFactory.proxyForCreate((Object)this.delegate.getRowId(arg0), this, this.proxyCache, methodObject27797));
    }
    catch (SQLException e)
    {
      return (RowId)postForAll(methodObject27797, onErrorForAll(methodObject27797, e));
    }
  }
  
  public NClob getNClob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27794, this, new Object[] { Integer.valueOf(arg0) });
      return (NClob)postForAll(methodObject27794, this.proxyFactory.proxyForCreate((Object)this.delegate.getNClob(arg0), this, this.proxyCache, methodObject27794));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject27794, onErrorForAll(methodObject27794, e));
    }
  }
  
  public int getInt(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27762, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject27762, Integer.valueOf(this.delegate.getInt(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27762, onErrorForAll(methodObject27762, e))).intValue();
    }
  }
  
  public void updateCharacterStream(int arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27815, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.updateCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject27815);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27815, e);
    }
  }
  
  public boolean isBeforeFirst()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27867, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27867, Boolean.valueOf(this.delegate.isBeforeFirst()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27867, onErrorForAll(methodObject27867, e))).booleanValue();
    }
  }
  
  public void beforeFirst()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27834, this, zeroLengthObjectArray);
      this.delegate.beforeFirst();
      postForAll(methodObject27834);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27834, e);
    }
  }
  
  public boolean getBoolean(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27757, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject27757, Boolean.valueOf(this.delegate.getBoolean(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27757, onErrorForAll(methodObject27757, e))).booleanValue();
    }
  }
  
  public BigDecimal getBigDecimal(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27843, this, new Object[] { Integer.valueOf(arg0) });
      return (BigDecimal)postForAll(methodObject27843, (Object)this.delegate.getBigDecimal(arg0));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject27843, onErrorForAll(methodObject27843, e));
    }
  }
  
  public int getConcurrency()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27845, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27845, Integer.valueOf(this.delegate.getConcurrency()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27845, onErrorForAll(methodObject27845, e))).intValue();
    }
  }
  
  public void updateBinaryStream(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27814, this, new Object[] { arg0, arg1 });
      this.delegate.updateBinaryStream(arg0, arg1);
      postForAll(methodObject27814);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27814, e);
    }
  }
  
  public Timestamp getTimestamp(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27862, this, new Object[] { arg0, arg1 });
      return (Timestamp)postForAll(methodObject27862, (Object)this.delegate.getTimestamp(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject27862, onErrorForAll(methodObject27862, e));
    }
  }
  
  public void updateBytes(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27894, this, new Object[] { arg0, arg1 });
      this.delegate.updateBytes(arg0, arg1);
      postForAll(methodObject27894);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27894, e);
    }
  }
  
  public byte getByte(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27758, this, new Object[] { Integer.valueOf(arg0) });
      return ((Byte)postForAll(methodObject27758, Byte.valueOf(this.delegate.getByte(arg0)))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject27758, onErrorForAll(methodObject27758, e))).byteValue();
    }
  }
  
  public void updateBoolean(String arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27890, this, new Object[] { arg0, Boolean.valueOf(arg1) });
      this.delegate.updateBoolean(arg0, arg1);
      postForAll(methodObject27890);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27890, e);
    }
  }
  
  public void updateBlob(String arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27884, this, new Object[] { arg0, arg1 });
      this.delegate.updateBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27884);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27884, e);
    }
  }
  
  public SQLXML getSQLXML(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27798, this, new Object[] { Integer.valueOf(arg0) });
      return (SQLXML)postForAll(methodObject27798, this.proxyFactory.proxyForCreate((Object)this.delegate.getSQLXML(arg0), this, this.proxyCache, methodObject27798));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject27798, onErrorForAll(methodObject27798, e));
    }
  }
  
  public Clob getClob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27792, this, new Object[] { Integer.valueOf(arg0) });
      return (Clob)postForAll(methodObject27792, this.proxyFactory.proxyForCreate((Object)this.delegate.getClob(arg0), this, this.proxyCache, methodObject27792));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject27792, onErrorForAll(methodObject27792, e));
    }
  }
  
  public Array getArray(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27772, this, new Object[] { Integer.valueOf(arg0) });
      return (Array)postForAll(methodObject27772, this.proxyFactory.proxyForCreate((Object)this.delegate.getArray(arg0), this, this.proxyCache, methodObject27772));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject27772, onErrorForAll(methodObject27772, e));
    }
  }
  
  public void updateClob(int arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27895, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27895);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27895, e);
    }
  }
  
  public byte[] getBytes(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27771, this, new Object[] { arg0 });
      return (byte[])postForAll(methodObject27771, (Object)this.delegate.getBytes(arg0));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject27771, onErrorForAll(methodObject27771, e));
    }
  }
  
  public boolean relative(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27873, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject27873, Boolean.valueOf(this.delegate.relative(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27873, onErrorForAll(methodObject27873, e))).booleanValue();
    }
  }
  
  public boolean rowInserted()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27875, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27875, Boolean.valueOf(this.delegate.rowInserted()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27875, onErrorForAll(methodObject27875, e))).booleanValue();
    }
  }
  
  public void deleteRow()
    throws SQLException
  {
    try
    {
      super.preForRowUpdates(methodObject27800, this, zeroLengthObjectArray);
      this.delegate.deleteRow();
      postForAll(methodObject27800);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27800, e);
    }
  }
  
  public Date getDate(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27785, this, new Object[] { Integer.valueOf(arg0) });
      return (Date)postForAll(methodObject27785, (Object)this.delegate.getDate(arg0));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject27785, onErrorForAll(methodObject27785, e));
    }
  }
  
  public Reader getCharacterStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27830, this, new Object[] { Integer.valueOf(arg0) });
      return (Reader)postForAll(methodObject27830, (Object)this.delegate.getCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject27830, onErrorForAll(methodObject27830, e));
    }
  }
  
  public Time getTime(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27855, this, new Object[] { Integer.valueOf(arg0) });
      return (Time)postForAll(methodObject27855, (Object)this.delegate.getTime(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject27855, onErrorForAll(methodObject27855, e));
    }
  }
  
  public Date getDate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27786, this, new Object[] { arg0 });
      return (Date)postForAll(methodObject27786, (Object)this.delegate.getDate(arg0));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject27786, onErrorForAll(methodObject27786, e));
    }
  }
  
  public Timestamp getTimestamp(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27860, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject27860, (Object)this.delegate.getTimestamp(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject27860, onErrorForAll(methodObject27860, e));
    }
  }
  
  public void updateClob(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27900, this, new Object[] { arg0, arg1 });
      this.delegate.updateClob(arg0, arg1);
      postForAll(methodObject27900);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27900, e);
    }
  }
  
  public short getShort(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27761, this, new Object[] { arg0 });
      return ((Short)postForAll(methodObject27761, Short.valueOf(this.delegate.getShort(arg0)))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject27761, onErrorForAll(methodObject27761, e))).shortValue();
    }
  }
  
  public void updateString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27933, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateString(arg0, arg1);
      postForAll(methodObject27933);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27933, e);
    }
  }
  
  public void updateAsciiStream(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject27804, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.updateAsciiStream(arg0, arg1, arg2);
      postForAll(methodObject27804);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27804, e);
    }
  }
  
  public ResultSet _getDelegate_()
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
      methodObject27919 = ResultSet.class.getDeclaredMethod("updateNull", new Class[] { Integer.TYPE });
      methodObject27927 = ResultSet.class.getDeclaredMethod("updateRowId", new Class[] { Integer.TYPE, RowId.class });
      methodObject27869 = ResultSet.class.getDeclaredMethod("isLast", new Class[0]);
      methodObject27907 = ResultSet.class.getDeclaredMethod("updateInt", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject27922 = ResultSet.class.getDeclaredMethod("updateObject", new Class[] { Integer.TYPE, Object.class });
      methodObject27799 = ResultSet.class.getDeclaredMethod("getSQLXML", new Class[] { String.class });
      methodObject27821 = ResultSet.class.getDeclaredMethod("updateNCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject27838 = ResultSet.class.getDeclaredMethod("first", new Class[0]);
      methodObject27934 = ResultSet.class.getDeclaredMethod("updateString", new Class[] { String.class, String.class });
      methodObject27811 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject27813 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject27889 = ResultSet.class.getDeclaredMethod("updateBoolean", new Class[] { Integer.TYPE, Boolean.TYPE });
      methodObject27781 = ResultSet.class.getDeclaredMethod("getRef", new Class[] { String.class });
      methodObject27909 = ResultSet.class.getDeclaredMethod("updateLong", new Class[] { Integer.TYPE, Long.TYPE });
      methodObject27765 = ResultSet.class.getDeclaredMethod("getLong", new Class[] { String.class });
      methodObject27816 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { String.class, Reader.class, Integer.TYPE });
      methodObject27820 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { String.class, Reader.class });
      methodObject27936 = ResultSet.class.getDeclaredMethod("updateTime", new Class[] { String.class, Time.class });
      methodObject27840 = ResultSet.class.getDeclaredMethod("getAsciiStream", new Class[] { String.class });
      methodObject27808 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { String.class, InputStream.class });
      methodObject27892 = ResultSet.class.getDeclaredMethod("updateByte", new Class[] { String.class, Byte.TYPE });
      methodObject27760 = ResultSet.class.getDeclaredMethod("getShort", new Class[] { Integer.TYPE });
      methodObject27878 = ResultSet.class.getDeclaredMethod("setFetchSize", new Class[] { Integer.TYPE });
      methodObject27871 = ResultSet.class.getDeclaredMethod("moveToInsertRow", new Class[0]);
      methodObject27754 = ResultSet.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE, Map.class });
      methodObject27810 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject27926 = ResultSet.class.getDeclaredMethod("updateRef", new Class[] { String.class, Ref.class });
      methodObject27912 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { String.class, NClob.class });
      methodObject27835 = ResultSet.class.getDeclaredMethod("cancelRowUpdates", new Class[0]);
      methodObject27857 = ResultSet.class.getDeclaredMethod("getTime", new Class[] { Integer.TYPE, Calendar.class });
      methodObject27849 = ResultSet.class.getDeclaredMethod("getHoldability", new Class[0]);
      methodObject27911 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { Integer.TYPE, NClob.class });
      methodObject27783 = ResultSet.class.getDeclaredMethod("getString", new Class[] { String.class });
      methodObject27768 = ResultSet.class.getDeclaredMethod("getDouble", new Class[] { Integer.TYPE });
      methodObject27759 = ResultSet.class.getDeclaredMethod("getByte", new Class[] { String.class });
      methodObject27872 = ResultSet.class.getDeclaredMethod("refreshRow", new Class[0]);
      methodObject27914 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject27901 = ResultSet.class.getDeclaredMethod("updateDate", new Class[] { Integer.TYPE, Date.class });
      methodObject27803 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject27879 = ResultSet.class.getDeclaredMethod("updateArray", new Class[] { Integer.TYPE, Array.class });
      methodObject27920 = ResultSet.class.getDeclaredMethod("updateNull", new Class[] { String.class });
      methodObject27764 = ResultSet.class.getDeclaredMethod("getLong", new Class[] { Integer.TYPE });
      methodObject27806 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject27913 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject27828 = ResultSet.class.getDeclaredMethod("getBinaryStream", new Class[] { Integer.TYPE });
      methodObject27903 = ResultSet.class.getDeclaredMethod("updateDouble", new Class[] { Integer.TYPE, Double.TYPE });
      methodObject27887 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { Integer.TYPE, InputStream.class });
      methodObject27844 = ResultSet.class.getDeclaredMethod("getBigDecimal", new Class[] { String.class });
      methodObject27924 = ResultSet.class.getDeclaredMethod("updateObject", new Class[] { String.class, Object.class });
      methodObject27827 = ResultSet.class.getDeclaredMethod("wasNull", new Class[0]);
      methodObject27906 = ResultSet.class.getDeclaredMethod("updateFloat", new Class[] { String.class, Float.TYPE });
      methodObject27856 = ResultSet.class.getDeclaredMethod("getTime", new Class[] { String.class });
      methodObject27802 = ResultSet.class.getDeclaredMethod("updateRow", new Class[0]);
      methodObject27809 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject27864 = ResultSet.class.getDeclaredMethod("getUnicodeStream", new Class[] { String.class });
      methodObject27866 = ResultSet.class.getDeclaredMethod("isAfterLast", new Class[0]);
      methodObject27831 = ResultSet.class.getDeclaredMethod("getCharacterStream", new Class[] { String.class });
      methodObject27939 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject27791 = ResultSet.class.getDeclaredMethod("getBlob", new Class[] { String.class });
      methodObject27836 = ResultSet.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject27824 = ResultSet.class.getDeclaredMethod("updateNCharacterStream", new Class[] { String.class, Reader.class });
      methodObject27940 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject27904 = ResultSet.class.getDeclaredMethod("updateDouble", new Class[] { String.class, Double.TYPE });
      methodObject27769 = ResultSet.class.getDeclaredMethod("getDouble", new Class[] { String.class });
      methodObject27874 = ResultSet.class.getDeclaredMethod("rowDeleted", new Class[0]);
      methodObject27789 = ResultSet.class.getDeclaredMethod("getMetaData", new Class[0]);
      methodObject27833 = ResultSet.class.getDeclaredMethod("afterLast", new Class[0]);
      methodObject27897 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject27868 = ResultSet.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject27902 = ResultSet.class.getDeclaredMethod("updateDate", new Class[] { String.class, Date.class });
      methodObject27755 = ResultSet.class.getDeclaredMethod("getObject", new Class[] { String.class, Map.class });
      methodObject27877 = ResultSet.class.getDeclaredMethod("setFetchDirection", new Class[] { Integer.TYPE });
      methodObject27826 = ResultSet.class.getDeclaredMethod("getStatement", new Class[0]);
      methodObject27928 = ResultSet.class.getDeclaredMethod("updateRowId", new Class[] { String.class, RowId.class });
      methodObject27885 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject27865 = ResultSet.class.getDeclaredMethod("getWarnings", new Class[0]);
      methodObject27880 = ResultSet.class.getDeclaredMethod("updateArray", new Class[] { String.class, Array.class });
      methodObject27899 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject27886 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject27819 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject27812 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject27825 = ResultSet.class.getDeclaredMethod("last", new Class[0]);
      methodObject27882 = ResultSet.class.getDeclaredMethod("updateBigDecimal", new Class[] { String.class, BigDecimal.class });
      methodObject27876 = ResultSet.class.getDeclaredMethod("rowUpdated", new Class[0]);
      methodObject27823 = ResultSet.class.getDeclaredMethod("updateNCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject27805 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject27881 = ResultSet.class.getDeclaredMethod("updateBigDecimal", new Class[] { Integer.TYPE, BigDecimal.class });
      methodObject27756 = ResultSet.class.getDeclaredMethod("getBoolean", new Class[] { Integer.TYPE });
      methodObject27846 = ResultSet.class.getDeclaredMethod("getCursorName", new Class[0]);
      methodObject27780 = ResultSet.class.getDeclaredMethod("getRef", new Class[] { Integer.TYPE });
      methodObject27896 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { String.class, Clob.class });
      methodObject27777 = ResultSet.class.getDeclaredMethod("close", new Class[0]);
      methodObject27795 = ResultSet.class.getDeclaredMethod("getNClob", new Class[] { String.class });
      methodObject27767 = ResultSet.class.getDeclaredMethod("getFloat", new Class[] { String.class });
      methodObject27847 = ResultSet.class.getDeclaredMethod("getFetchDirection", new Class[0]);
      methodObject27851 = ResultSet.class.getDeclaredMethod("getNCharacterStream", new Class[] { String.class });
      methodObject27784 = ResultSet.class.getDeclaredMethod("isFirst", new Class[0]);
      methodObject27893 = ResultSet.class.getDeclaredMethod("updateBytes", new Class[] { Integer.TYPE, byte[].class });
      methodObject27787 = ResultSet.class.getDeclaredMethod("getDate", new Class[] { Integer.TYPE, Calendar.class });
      methodObject27858 = ResultSet.class.getDeclaredMethod("getTime", new Class[] { String.class, Calendar.class });
      methodObject27779 = ResultSet.class.getDeclaredMethod("previous", new Class[0]);
      methodObject27841 = ResultSet.class.getDeclaredMethod("getBigDecimal", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject27790 = ResultSet.class.getDeclaredMethod("getBlob", new Class[] { Integer.TYPE });
      methodObject27916 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { String.class, Reader.class });
      methodObject27888 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { String.class, InputStream.class });
      methodObject27752 = ResultSet.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE });
      methodObject27850 = ResultSet.class.getDeclaredMethod("getNCharacterStream", new Class[] { Integer.TYPE });
      methodObject27822 = ResultSet.class.getDeclaredMethod("updateNCharacterStream", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject27891 = ResultSet.class.getDeclaredMethod("updateByte", new Class[] { Integer.TYPE, Byte.TYPE });
      methodObject27863 = ResultSet.class.getDeclaredMethod("getUnicodeStream", new Class[] { Integer.TYPE });
      methodObject27917 = ResultSet.class.getDeclaredMethod("updateNString", new Class[] { Integer.TYPE, String.class });
      methodObject27931 = ResultSet.class.getDeclaredMethod("updateShort", new Class[] { Integer.TYPE, Short.TYPE });
      methodObject27929 = ResultSet.class.getDeclaredMethod("updateSQLXML", new Class[] { Integer.TYPE, SQLXML.class });
      methodObject27910 = ResultSet.class.getDeclaredMethod("updateLong", new Class[] { String.class, Long.TYPE });
      methodObject27898 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject27918 = ResultSet.class.getDeclaredMethod("updateNString", new Class[] { String.class, String.class });
      methodObject27774 = ResultSet.class.getDeclaredMethod("next", new Class[0]);
      methodObject27776 = ResultSet.class.getDeclaredMethod("getURL", new Class[] { String.class });
      methodObject27766 = ResultSet.class.getDeclaredMethod("getFloat", new Class[] { Integer.TYPE });
      methodObject27937 = ResultSet.class.getDeclaredMethod("updateTimestamp", new Class[] { Integer.TYPE, Timestamp.class });
      methodObject27930 = ResultSet.class.getDeclaredMethod("updateSQLXML", new Class[] { String.class, SQLXML.class });
      methodObject27796 = ResultSet.class.getDeclaredMethod("getRowId", new Class[] { Integer.TYPE });
      methodObject27778 = ResultSet.class.getDeclaredMethod("getType", new Class[0]);
      methodObject27807 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject27782 = ResultSet.class.getDeclaredMethod("getString", new Class[] { Integer.TYPE });
      methodObject27829 = ResultSet.class.getDeclaredMethod("getBinaryStream", new Class[] { String.class });
      methodObject27935 = ResultSet.class.getDeclaredMethod("updateTime", new Class[] { Integer.TYPE, Time.class });
      methodObject27923 = ResultSet.class.getDeclaredMethod("updateObject", new Class[] { String.class, Object.class, Integer.TYPE });
      methodObject27854 = ResultSet.class.getDeclaredMethod("getRow", new Class[0]);
      methodObject27770 = ResultSet.class.getDeclaredMethod("getBytes", new Class[] { Integer.TYPE });
      methodObject27775 = ResultSet.class.getDeclaredMethod("getURL", new Class[] { Integer.TYPE });
      methodObject27839 = ResultSet.class.getDeclaredMethod("getAsciiStream", new Class[] { Integer.TYPE });
      methodObject27908 = ResultSet.class.getDeclaredMethod("updateInt", new Class[] { String.class, Integer.TYPE });
      methodObject27861 = ResultSet.class.getDeclaredMethod("getTimestamp", new Class[] { Integer.TYPE, Calendar.class });
      methodObject27801 = ResultSet.class.getDeclaredMethod("insertRow", new Class[0]);
      methodObject27832 = ResultSet.class.getDeclaredMethod("absolute", new Class[] { Integer.TYPE });
      methodObject27793 = ResultSet.class.getDeclaredMethod("getClob", new Class[] { String.class });
      methodObject27848 = ResultSet.class.getDeclaredMethod("getFetchSize", new Class[0]);
      methodObject27932 = ResultSet.class.getDeclaredMethod("updateShort", new Class[] { String.class, Short.TYPE });
      methodObject27921 = ResultSet.class.getDeclaredMethod("updateObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE });
      methodObject27852 = ResultSet.class.getDeclaredMethod("getNString", new Class[] { Integer.TYPE });
      methodObject27842 = ResultSet.class.getDeclaredMethod("getBigDecimal", new Class[] { String.class, Integer.TYPE });
      methodObject27870 = ResultSet.class.getDeclaredMethod("moveToCurrentRow", new Class[0]);
      methodObject27905 = ResultSet.class.getDeclaredMethod("updateFloat", new Class[] { Integer.TYPE, Float.TYPE });
      methodObject27817 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject27938 = ResultSet.class.getDeclaredMethod("updateTimestamp", new Class[] { String.class, Timestamp.class });
      methodObject27788 = ResultSet.class.getDeclaredMethod("getDate", new Class[] { String.class, Calendar.class });
      methodObject27763 = ResultSet.class.getDeclaredMethod("getInt", new Class[] { String.class });
      methodObject27753 = ResultSet.class.getDeclaredMethod("getObject", new Class[] { String.class });
      methodObject27818 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject27859 = ResultSet.class.getDeclaredMethod("getTimestamp", new Class[] { Integer.TYPE });
      methodObject27837 = ResultSet.class.getDeclaredMethod("findColumn", new Class[] { String.class });
      methodObject27915 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject27853 = ResultSet.class.getDeclaredMethod("getNString", new Class[] { String.class });
      methodObject27773 = ResultSet.class.getDeclaredMethod("getArray", new Class[] { String.class });
      methodObject27925 = ResultSet.class.getDeclaredMethod("updateRef", new Class[] { Integer.TYPE, Ref.class });
      methodObject27883 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { Integer.TYPE, Blob.class });
      methodObject27797 = ResultSet.class.getDeclaredMethod("getRowId", new Class[] { String.class });
      methodObject27794 = ResultSet.class.getDeclaredMethod("getNClob", new Class[] { Integer.TYPE });
      methodObject27762 = ResultSet.class.getDeclaredMethod("getInt", new Class[] { Integer.TYPE });
      methodObject27815 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { Integer.TYPE, Reader.class, Integer.TYPE });
      methodObject27867 = ResultSet.class.getDeclaredMethod("isBeforeFirst", new Class[0]);
      methodObject27834 = ResultSet.class.getDeclaredMethod("beforeFirst", new Class[0]);
      methodObject27757 = ResultSet.class.getDeclaredMethod("getBoolean", new Class[] { String.class });
      methodObject27843 = ResultSet.class.getDeclaredMethod("getBigDecimal", new Class[] { Integer.TYPE });
      methodObject27845 = ResultSet.class.getDeclaredMethod("getConcurrency", new Class[0]);
      methodObject27814 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { String.class, InputStream.class });
      methodObject27862 = ResultSet.class.getDeclaredMethod("getTimestamp", new Class[] { String.class, Calendar.class });
      methodObject27894 = ResultSet.class.getDeclaredMethod("updateBytes", new Class[] { String.class, byte[].class });
      methodObject27758 = ResultSet.class.getDeclaredMethod("getByte", new Class[] { Integer.TYPE });
      methodObject27890 = ResultSet.class.getDeclaredMethod("updateBoolean", new Class[] { String.class, Boolean.TYPE });
      methodObject27884 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { String.class, Blob.class });
      methodObject27798 = ResultSet.class.getDeclaredMethod("getSQLXML", new Class[] { Integer.TYPE });
      methodObject27792 = ResultSet.class.getDeclaredMethod("getClob", new Class[] { Integer.TYPE });
      methodObject27772 = ResultSet.class.getDeclaredMethod("getArray", new Class[] { Integer.TYPE });
      methodObject27895 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { Integer.TYPE, Clob.class });
      methodObject27771 = ResultSet.class.getDeclaredMethod("getBytes", new Class[] { String.class });
      methodObject27873 = ResultSet.class.getDeclaredMethod("relative", new Class[] { Integer.TYPE });
      methodObject27875 = ResultSet.class.getDeclaredMethod("rowInserted", new Class[0]);
      methodObject27800 = ResultSet.class.getDeclaredMethod("deleteRow", new Class[0]);
      methodObject27785 = ResultSet.class.getDeclaredMethod("getDate", new Class[] { Integer.TYPE });
      methodObject27830 = ResultSet.class.getDeclaredMethod("getCharacterStream", new Class[] { Integer.TYPE });
      methodObject27855 = ResultSet.class.getDeclaredMethod("getTime", new Class[] { Integer.TYPE });
      methodObject27786 = ResultSet.class.getDeclaredMethod("getDate", new Class[] { String.class });
      methodObject27860 = ResultSet.class.getDeclaredMethod("getTimestamp", new Class[] { String.class });
      methodObject27900 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { String.class, Reader.class });
      methodObject27761 = ResultSet.class.getDeclaredMethod("getShort", new Class[] { String.class });
      methodObject27933 = ResultSet.class.getDeclaredMethod("updateString", new Class[] { Integer.TYPE, String.class });
      methodObject27804 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { String.class, InputStream.class, Integer.TYPE });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableResultSet$2java$1sql$1ResultSet$$$Proxy(ResultSet paramResultSet, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramResultSet;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableResultSet$2java$1sql$1ResultSet$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */